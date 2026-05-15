package io.grpc.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.internal.TimeProvider;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class OutlierDetectionLoadBalancer extends LoadBalancer {
    private static final Attributes.Key<AddressTracker> ADDRESS_TRACKER_ATTR_KEY = Attributes.Key.create("addressTrackerKey");
    private final LoadBalancer.Helper childHelper;
    private SynchronizationContext.ScheduledHandle detectionTimerHandle;
    private Long detectionTimerStartNanos;
    private final GracefulSwitchLoadBalancer switchLb;
    private final SynchronizationContext syncContext;
    private TimeProvider timeProvider;
    private final ScheduledExecutorService timeService;
    final AddressTrackerMap trackerMap = new AddressTrackerMap();

    public OutlierDetectionLoadBalancer(LoadBalancer.Helper helper, TimeProvider timeProvider) {
        this.childHelper = new ChildHelper((LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper"));
        this.switchLb = new GracefulSwitchLoadBalancer(this.childHelper);
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        this.timeService = (ScheduledExecutorService) Preconditions.checkNotNull(helper.getScheduledExecutorService(), "timeService");
        this.timeProvider = timeProvider;
    }

    @Override // io.grpc.LoadBalancer
    public boolean acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        Long initialDelayNanos;
        OutlierDetectionLoadBalancerConfig config = (OutlierDetectionLoadBalancerConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
        ArrayList<SocketAddress> addresses = new ArrayList<>();
        for (EquivalentAddressGroup addressGroup : resolvedAddresses.getAddresses()) {
            addresses.addAll(addressGroup.getAddresses());
        }
        this.trackerMap.keySet().retainAll(addresses);
        this.trackerMap.updateTrackerConfigs(config);
        this.trackerMap.putNewTrackers(config, addresses);
        this.switchLb.switchTo(config.childPolicy.getProvider());
        if (config.outlierDetectionEnabled()) {
            if (this.detectionTimerStartNanos == null) {
                initialDelayNanos = config.intervalNanos;
            } else {
                Long initialDelayNanos2 = config.intervalNanos;
                initialDelayNanos = Long.valueOf(Math.max(0L, initialDelayNanos2.longValue() - (this.timeProvider.currentTimeNanos() - this.detectionTimerStartNanos.longValue())));
            }
            if (this.detectionTimerHandle != null) {
                this.detectionTimerHandle.cancel();
                this.trackerMap.resetCallCounters();
            }
            this.detectionTimerHandle = this.syncContext.scheduleWithFixedDelay(new DetectionTimer(config), initialDelayNanos.longValue(), config.intervalNanos.longValue(), TimeUnit.NANOSECONDS, this.timeService);
        } else if (this.detectionTimerHandle != null) {
            this.detectionTimerHandle.cancel();
            this.detectionTimerStartNanos = null;
            this.trackerMap.cancelTracking();
        }
        this.switchLb.handleResolvedAddresses(resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(config.childPolicy.getConfig()).build());
        return true;
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status error) {
        this.switchLb.handleNameResolutionError(error);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        this.switchLb.shutdown();
    }

    class DetectionTimer implements Runnable {
        OutlierDetectionLoadBalancerConfig config;

        DetectionTimer(OutlierDetectionLoadBalancerConfig config) {
            this.config = config;
        }

        @Override // java.lang.Runnable
        public void run() {
            OutlierDetectionLoadBalancer.this.detectionTimerStartNanos = Long.valueOf(OutlierDetectionLoadBalancer.this.timeProvider.currentTimeNanos());
            OutlierDetectionLoadBalancer.this.trackerMap.swapCounters();
            for (OutlierEjectionAlgorithm algo : OutlierEjectionAlgorithm.forConfig(this.config)) {
                algo.ejectOutliers(OutlierDetectionLoadBalancer.this.trackerMap, OutlierDetectionLoadBalancer.this.detectionTimerStartNanos.longValue());
            }
            OutlierDetectionLoadBalancer.this.trackerMap.maybeUnejectOutliers(OutlierDetectionLoadBalancer.this.detectionTimerStartNanos);
        }
    }

    class ChildHelper extends ForwardingLoadBalancerHelper {
        private LoadBalancer.Helper delegate;

        ChildHelper(LoadBalancer.Helper delegate) {
            this.delegate = delegate;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        protected LoadBalancer.Helper delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs args) {
            OutlierDetectionSubchannel subchannel = OutlierDetectionLoadBalancer.this.new OutlierDetectionSubchannel(this.delegate.createSubchannel(args));
            List<EquivalentAddressGroup> addressGroups = args.getAddresses();
            if (OutlierDetectionLoadBalancer.hasSingleAddress(addressGroups) && OutlierDetectionLoadBalancer.this.trackerMap.containsKey(addressGroups.get(0).getAddresses().get(0))) {
                AddressTracker tracker = OutlierDetectionLoadBalancer.this.trackerMap.get(addressGroups.get(0).getAddresses().get(0));
                tracker.addSubchannel(subchannel);
                if (tracker.ejectionTimeNanos != null) {
                    subchannel.eject();
                }
            }
            return subchannel;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public void updateBalancingState(ConnectivityState newState, LoadBalancer.SubchannelPicker newPicker) {
            this.delegate.updateBalancingState(newState, OutlierDetectionLoadBalancer.this.new OutlierDetectionPicker(newPicker));
        }
    }

    class OutlierDetectionSubchannel extends ForwardingSubchannel {
        private AddressTracker addressTracker;
        private final LoadBalancer.Subchannel delegate;
        private boolean ejected;
        private ConnectivityStateInfo lastSubchannelState;
        private LoadBalancer.SubchannelStateListener subchannelStateListener;

        OutlierDetectionSubchannel(LoadBalancer.Subchannel delegate) {
            this.delegate = delegate;
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public void start(LoadBalancer.SubchannelStateListener listener) {
            this.subchannelStateListener = listener;
            super.start(new OutlierDetectionSubchannelStateListener(listener));
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public Attributes getAttributes() {
            if (this.addressTracker != null) {
                return this.delegate.getAttributes().toBuilder().set(OutlierDetectionLoadBalancer.ADDRESS_TRACKER_ATTR_KEY, this.addressTracker).build();
            }
            return this.delegate.getAttributes();
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public void updateAddresses(List<EquivalentAddressGroup> addressGroups) {
            if (!OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) || !OutlierDetectionLoadBalancer.hasSingleAddress(addressGroups)) {
                if (!OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) || OutlierDetectionLoadBalancer.hasSingleAddress(addressGroups)) {
                    if (!OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) && OutlierDetectionLoadBalancer.hasSingleAddress(addressGroups)) {
                        SocketAddress address = addressGroups.get(0).getAddresses().get(0);
                        if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(address)) {
                            OutlierDetectionLoadBalancer.this.trackerMap.get(address).addSubchannel(this);
                        }
                    }
                } else if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(getAddresses().getAddresses().get(0))) {
                    AddressTracker tracker = OutlierDetectionLoadBalancer.this.trackerMap.get(getAddresses().getAddresses().get(0));
                    tracker.removeSubchannel(this);
                    tracker.resetCallCounters();
                }
            } else {
                if (OutlierDetectionLoadBalancer.this.trackerMap.containsValue(this.addressTracker)) {
                    this.addressTracker.removeSubchannel(this);
                }
                SocketAddress address2 = addressGroups.get(0).getAddresses().get(0);
                if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(address2)) {
                    OutlierDetectionLoadBalancer.this.trackerMap.get(address2).addSubchannel(this);
                }
            }
            this.delegate.updateAddresses(addressGroups);
        }

        void setAddressTracker(AddressTracker addressTracker) {
            this.addressTracker = addressTracker;
        }

        void clearAddressTracker() {
            this.addressTracker = null;
        }

        void eject() {
            this.ejected = true;
            this.subchannelStateListener.onSubchannelState(ConnectivityStateInfo.forTransientFailure(Status.UNAVAILABLE));
        }

        void uneject() {
            this.ejected = false;
            if (this.lastSubchannelState != null) {
                this.subchannelStateListener.onSubchannelState(this.lastSubchannelState);
            }
        }

        boolean isEjected() {
            return this.ejected;
        }

        @Override // io.grpc.util.ForwardingSubchannel
        protected LoadBalancer.Subchannel delegate() {
            return this.delegate;
        }

        class OutlierDetectionSubchannelStateListener implements LoadBalancer.SubchannelStateListener {
            private final LoadBalancer.SubchannelStateListener delegate;

            OutlierDetectionSubchannelStateListener(LoadBalancer.SubchannelStateListener delegate) {
                this.delegate = delegate;
            }

            @Override // io.grpc.LoadBalancer.SubchannelStateListener
            public void onSubchannelState(ConnectivityStateInfo newState) {
                OutlierDetectionSubchannel.this.lastSubchannelState = newState;
                if (!OutlierDetectionSubchannel.this.ejected) {
                    this.delegate.onSubchannelState(newState);
                }
            }
        }
    }

    class OutlierDetectionPicker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.SubchannelPicker delegate;

        OutlierDetectionPicker(LoadBalancer.SubchannelPicker delegate) {
            this.delegate = delegate;
        }

        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            LoadBalancer.PickResult pickResult = this.delegate.pickSubchannel(args);
            LoadBalancer.Subchannel subchannel = pickResult.getSubchannel();
            if (subchannel != null) {
                return LoadBalancer.PickResult.withSubchannel(subchannel, new ResultCountingClientStreamTracerFactory((AddressTracker) subchannel.getAttributes().get(OutlierDetectionLoadBalancer.ADDRESS_TRACKER_ATTR_KEY)));
            }
            return pickResult;
        }

        class ResultCountingClientStreamTracerFactory extends ClientStreamTracer.Factory {
            private final AddressTracker tracker;

            ResultCountingClientStreamTracerFactory(AddressTracker tracker) {
                this.tracker = tracker;
            }

            @Override // io.grpc.ClientStreamTracer.Factory
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo info, Metadata headers) {
                return OutlierDetectionPicker.this.new ResultCountingClientStreamTracer(this.tracker);
            }
        }

        class ResultCountingClientStreamTracer extends ClientStreamTracer {
            AddressTracker tracker;

            public ResultCountingClientStreamTracer(AddressTracker tracker) {
                this.tracker = tracker;
            }

            @Override // io.grpc.StreamTracer
            public void streamClosed(Status status) {
                this.tracker.incrementCallCount(status.isOk());
            }
        }
    }

    static class AddressTracker {
        private volatile CallCounter activeCallCounter;
        private OutlierDetectionLoadBalancerConfig config;
        private int ejectionTimeMultiplier;
        private Long ejectionTimeNanos;
        private CallCounter inactiveCallCounter;
        private final Set<OutlierDetectionSubchannel> subchannels = new HashSet();

        AddressTracker(OutlierDetectionLoadBalancerConfig config) {
            this.activeCallCounter = new CallCounter();
            this.inactiveCallCounter = new CallCounter();
            this.config = config;
        }

        void setConfig(OutlierDetectionLoadBalancerConfig config) {
            this.config = config;
        }

        boolean addSubchannel(OutlierDetectionSubchannel subchannel) {
            if (subchannelsEjected() && !subchannel.isEjected()) {
                subchannel.eject();
            } else if (!subchannelsEjected() && subchannel.isEjected()) {
                subchannel.uneject();
            }
            subchannel.setAddressTracker(this);
            return this.subchannels.add(subchannel);
        }

        boolean removeSubchannel(OutlierDetectionSubchannel subchannel) {
            subchannel.clearAddressTracker();
            return this.subchannels.remove(subchannel);
        }

        boolean containsSubchannel(OutlierDetectionSubchannel subchannel) {
            return this.subchannels.contains(subchannel);
        }

        Set<OutlierDetectionSubchannel> getSubchannels() {
            return ImmutableSet.copyOf((Collection) this.subchannels);
        }

        void incrementCallCount(boolean success) {
            if (this.config.successRateEjection == null && this.config.failurePercentageEjection == null) {
                return;
            }
            if (success) {
                this.activeCallCounter.successCount.getAndIncrement();
            } else {
                this.activeCallCounter.failureCount.getAndIncrement();
            }
        }

        long activeVolume() {
            return this.activeCallCounter.successCount.get() + this.activeCallCounter.failureCount.get();
        }

        long inactiveVolume() {
            return this.inactiveCallCounter.successCount.get() + this.inactiveCallCounter.failureCount.get();
        }

        double successRate() {
            return this.inactiveCallCounter.successCount.get() / inactiveVolume();
        }

        double failureRate() {
            return this.inactiveCallCounter.failureCount.get() / inactiveVolume();
        }

        void resetCallCounters() {
            this.activeCallCounter.reset();
            this.inactiveCallCounter.reset();
        }

        void decrementEjectionTimeMultiplier() {
            this.ejectionTimeMultiplier = this.ejectionTimeMultiplier == 0 ? 0 : this.ejectionTimeMultiplier - 1;
        }

        void resetEjectionTimeMultiplier() {
            this.ejectionTimeMultiplier = 0;
        }

        void swapCounters() {
            this.inactiveCallCounter.reset();
            CallCounter tempCounter = this.activeCallCounter;
            this.activeCallCounter = this.inactiveCallCounter;
            this.inactiveCallCounter = tempCounter;
        }

        void ejectSubchannels(long ejectionTimeNanos) {
            this.ejectionTimeNanos = Long.valueOf(ejectionTimeNanos);
            this.ejectionTimeMultiplier++;
            for (OutlierDetectionSubchannel subchannel : this.subchannels) {
                subchannel.eject();
            }
        }

        void unejectSubchannels() {
            Preconditions.checkState(this.ejectionTimeNanos != null, "not currently ejected");
            this.ejectionTimeNanos = null;
            for (OutlierDetectionSubchannel subchannel : this.subchannels) {
                subchannel.uneject();
            }
        }

        boolean subchannelsEjected() {
            return this.ejectionTimeNanos != null;
        }

        public boolean maxEjectionTimeElapsed(long currentTimeNanos) {
            long maxEjectionDurationSecs = Math.max(this.config.baseEjectionTimeNanos.longValue(), this.config.maxEjectionTimeNanos.longValue());
            long maxEjectionTimeNanos = this.ejectionTimeNanos.longValue() + Math.min(this.config.baseEjectionTimeNanos.longValue() * ((long) this.ejectionTimeMultiplier), maxEjectionDurationSecs);
            return currentTimeNanos > maxEjectionTimeNanos;
        }

        private static class CallCounter {
            AtomicLong failureCount;
            AtomicLong successCount;

            private CallCounter() {
                this.successCount = new AtomicLong();
                this.failureCount = new AtomicLong();
            }

            void reset() {
                this.successCount.set(0L);
                this.failureCount.set(0L);
            }
        }
    }

    static class AddressTrackerMap extends ForwardingMap<SocketAddress, AddressTracker> {
        private final Map<SocketAddress, AddressTracker> trackerMap = new HashMap();

        AddressTrackerMap() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<SocketAddress, AddressTracker> delegate() {
            return this.trackerMap;
        }

        void updateTrackerConfigs(OutlierDetectionLoadBalancerConfig config) {
            for (AddressTracker tracker : this.trackerMap.values()) {
                tracker.setConfig(config);
            }
        }

        void putNewTrackers(OutlierDetectionLoadBalancerConfig config, Collection<SocketAddress> addresses) {
            for (SocketAddress address : addresses) {
                if (!this.trackerMap.containsKey(address)) {
                    this.trackerMap.put(address, new AddressTracker(config));
                }
            }
        }

        void resetCallCounters() {
            for (AddressTracker tracker : this.trackerMap.values()) {
                tracker.resetCallCounters();
            }
        }

        void cancelTracking() {
            for (AddressTracker tracker : this.trackerMap.values()) {
                if (tracker.subchannelsEjected()) {
                    tracker.unejectSubchannels();
                }
                tracker.resetEjectionTimeMultiplier();
            }
        }

        void swapCounters() {
            for (AddressTracker tracker : this.trackerMap.values()) {
                tracker.swapCounters();
            }
        }

        void maybeUnejectOutliers(Long detectionTimerStartNanos) {
            for (AddressTracker tracker : this.trackerMap.values()) {
                if (!tracker.subchannelsEjected()) {
                    tracker.decrementEjectionTimeMultiplier();
                }
                if (tracker.subchannelsEjected() && tracker.maxEjectionTimeElapsed(detectionTimerStartNanos.longValue())) {
                    tracker.unejectSubchannels();
                }
            }
        }

        double ejectionPercentage() {
            if (this.trackerMap.isEmpty()) {
                return 0.0d;
            }
            int totalAddresses = 0;
            int ejectedAddresses = 0;
            for (AddressTracker tracker : this.trackerMap.values()) {
                totalAddresses++;
                if (tracker.subchannelsEjected()) {
                    ejectedAddresses++;
                }
            }
            return (((double) ejectedAddresses) / ((double) totalAddresses)) * 100.0d;
        }
    }

    interface OutlierEjectionAlgorithm {
        void ejectOutliers(AddressTrackerMap addressTrackerMap, long j);

        @Nullable
        static List<OutlierEjectionAlgorithm> forConfig(OutlierDetectionLoadBalancerConfig config) {
            ImmutableList.Builder<OutlierEjectionAlgorithm> algoListBuilder = ImmutableList.builder();
            if (config.successRateEjection != null) {
                algoListBuilder.add(new SuccessRateOutlierEjectionAlgorithm(config));
            }
            if (config.failurePercentageEjection != null) {
                algoListBuilder.add(new FailurePercentageOutlierEjectionAlgorithm(config));
            }
            return algoListBuilder.build();
        }
    }

    static class SuccessRateOutlierEjectionAlgorithm implements OutlierEjectionAlgorithm {
        private final OutlierDetectionLoadBalancerConfig config;

        SuccessRateOutlierEjectionAlgorithm(OutlierDetectionLoadBalancerConfig config) {
            Preconditions.checkArgument(config.successRateEjection != null, "success rate ejection config is null");
            this.config = config;
        }

        @Override // io.grpc.util.OutlierDetectionLoadBalancer.OutlierEjectionAlgorithm
        public void ejectOutliers(AddressTrackerMap trackerMap, long ejectionTimeNanos) {
            List<AddressTracker> trackersWithVolume = OutlierDetectionLoadBalancer.trackersWithVolume(trackerMap, this.config.successRateEjection.requestVolume.intValue());
            if (trackersWithVolume.size() >= this.config.successRateEjection.minimumHosts.intValue() && trackersWithVolume.size() != 0) {
                List<Double> successRates = new ArrayList<>();
                Iterator<AddressTracker> it = trackersWithVolume.iterator();
                while (it.hasNext()) {
                    successRates.add(Double.valueOf(it.next().successRate()));
                }
                double mean = mean(successRates);
                double stdev = standardDeviation(successRates, mean);
                double requiredSuccessRate = mean - (((double) (this.config.successRateEjection.stdevFactor.intValue() / 1000.0f)) * stdev);
                for (AddressTracker tracker : trackersWithVolume) {
                    if (trackerMap.ejectionPercentage() < this.config.maxEjectionPercent.intValue()) {
                        if (tracker.successRate() < requiredSuccessRate && new Random().nextInt(100) < this.config.successRateEjection.enforcementPercentage.intValue()) {
                            tracker.ejectSubchannels(ejectionTimeNanos);
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        static double mean(Collection<Double> values) {
            double totalValue = 0.0d;
            Iterator<Double> it = values.iterator();
            while (it.hasNext()) {
                double value = it.next().doubleValue();
                totalValue += value;
            }
            return totalValue / ((double) values.size());
        }

        static double standardDeviation(Collection<Double> values, double mean) {
            double squaredDifferenceSum = 0.0d;
            Iterator<Double> it = values.iterator();
            while (it.hasNext()) {
                double value = it.next().doubleValue();
                double difference = value - mean;
                squaredDifferenceSum += difference * difference;
            }
            double variance = squaredDifferenceSum / ((double) values.size());
            return Math.sqrt(variance);
        }
    }

    static class FailurePercentageOutlierEjectionAlgorithm implements OutlierEjectionAlgorithm {
        private final OutlierDetectionLoadBalancerConfig config;

        FailurePercentageOutlierEjectionAlgorithm(OutlierDetectionLoadBalancerConfig config) {
            this.config = config;
        }

        @Override // io.grpc.util.OutlierDetectionLoadBalancer.OutlierEjectionAlgorithm
        public void ejectOutliers(AddressTrackerMap trackerMap, long ejectionTimeNanos) {
            List<AddressTracker> trackersWithVolume = OutlierDetectionLoadBalancer.trackersWithVolume(trackerMap, this.config.failurePercentageEjection.requestVolume.intValue());
            if (trackersWithVolume.size() < this.config.failurePercentageEjection.minimumHosts.intValue() || trackersWithVolume.size() == 0) {
                return;
            }
            for (AddressTracker tracker : trackersWithVolume) {
                if (trackerMap.ejectionPercentage() >= this.config.maxEjectionPercent.intValue()) {
                    return;
                }
                if (tracker.inactiveVolume() >= this.config.failurePercentageEjection.requestVolume.intValue()) {
                    double maxFailureRate = ((double) this.config.failurePercentageEjection.threshold.intValue()) / 100.0d;
                    if (tracker.failureRate() > maxFailureRate && new Random().nextInt(100) < this.config.failurePercentageEjection.enforcementPercentage.intValue()) {
                        tracker.ejectSubchannels(ejectionTimeNanos);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<AddressTracker> trackersWithVolume(AddressTrackerMap trackerMap, int volume) {
        List<AddressTracker> trackersWithVolume = new ArrayList<>();
        for (AddressTracker tracker : trackerMap.values()) {
            if (tracker.inactiveVolume() >= volume) {
                trackersWithVolume.add(tracker);
            }
        }
        return trackersWithVolume;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasSingleAddress(List<EquivalentAddressGroup> addressGroups) {
        int addressCount = 0;
        for (EquivalentAddressGroup addressGroup : addressGroups) {
            addressCount += addressGroup.getAddresses().size();
            if (addressCount > 1) {
                return false;
            }
        }
        return true;
    }

    public static final class OutlierDetectionLoadBalancerConfig {
        public final Long baseEjectionTimeNanos;
        public final ServiceConfigUtil.PolicySelection childPolicy;
        public final FailurePercentageEjection failurePercentageEjection;
        public final Long intervalNanos;
        public final Integer maxEjectionPercent;
        public final Long maxEjectionTimeNanos;
        public final SuccessRateEjection successRateEjection;

        private OutlierDetectionLoadBalancerConfig(Long intervalNanos, Long baseEjectionTimeNanos, Long maxEjectionTimeNanos, Integer maxEjectionPercent, SuccessRateEjection successRateEjection, FailurePercentageEjection failurePercentageEjection, ServiceConfigUtil.PolicySelection childPolicy) {
            this.intervalNanos = intervalNanos;
            this.baseEjectionTimeNanos = baseEjectionTimeNanos;
            this.maxEjectionTimeNanos = maxEjectionTimeNanos;
            this.maxEjectionPercent = maxEjectionPercent;
            this.successRateEjection = successRateEjection;
            this.failurePercentageEjection = failurePercentageEjection;
            this.childPolicy = childPolicy;
        }

        public static class Builder {
            ServiceConfigUtil.PolicySelection childPolicy;
            FailurePercentageEjection failurePercentageEjection;
            SuccessRateEjection successRateEjection;
            Long intervalNanos = 10000000000L;
            Long baseEjectionTimeNanos = 30000000000L;
            Long maxEjectionTimeNanos = 30000000000L;
            Integer maxEjectionPercent = 10;

            public Builder setIntervalNanos(Long intervalNanos) {
                Preconditions.checkArgument(intervalNanos != null);
                this.intervalNanos = intervalNanos;
                return this;
            }

            public Builder setBaseEjectionTimeNanos(Long baseEjectionTimeNanos) {
                Preconditions.checkArgument(baseEjectionTimeNanos != null);
                this.baseEjectionTimeNanos = baseEjectionTimeNanos;
                return this;
            }

            public Builder setMaxEjectionTimeNanos(Long maxEjectionTimeNanos) {
                Preconditions.checkArgument(maxEjectionTimeNanos != null);
                this.maxEjectionTimeNanos = maxEjectionTimeNanos;
                return this;
            }

            public Builder setMaxEjectionPercent(Integer maxEjectionPercent) {
                Preconditions.checkArgument(maxEjectionPercent != null);
                this.maxEjectionPercent = maxEjectionPercent;
                return this;
            }

            public Builder setSuccessRateEjection(SuccessRateEjection successRateEjection) {
                this.successRateEjection = successRateEjection;
                return this;
            }

            public Builder setFailurePercentageEjection(FailurePercentageEjection failurePercentageEjection) {
                this.failurePercentageEjection = failurePercentageEjection;
                return this;
            }

            public Builder setChildPolicy(ServiceConfigUtil.PolicySelection childPolicy) {
                Preconditions.checkState(childPolicy != null);
                this.childPolicy = childPolicy;
                return this;
            }

            public OutlierDetectionLoadBalancerConfig build() {
                Preconditions.checkState(this.childPolicy != null);
                return new OutlierDetectionLoadBalancerConfig(this.intervalNanos, this.baseEjectionTimeNanos, this.maxEjectionTimeNanos, this.maxEjectionPercent, this.successRateEjection, this.failurePercentageEjection, this.childPolicy);
            }
        }

        public static class SuccessRateEjection {
            public final Integer enforcementPercentage;
            public final Integer minimumHosts;
            public final Integer requestVolume;
            public final Integer stdevFactor;

            SuccessRateEjection(Integer stdevFactor, Integer enforcementPercentage, Integer minimumHosts, Integer requestVolume) {
                this.stdevFactor = stdevFactor;
                this.enforcementPercentage = enforcementPercentage;
                this.minimumHosts = minimumHosts;
                this.requestVolume = requestVolume;
            }

            public static final class Builder {
                Integer stdevFactor = 1900;
                Integer enforcementPercentage = 100;
                Integer minimumHosts = 5;
                Integer requestVolume = 100;

                public Builder setStdevFactor(Integer stdevFactor) {
                    Preconditions.checkArgument(stdevFactor != null);
                    this.stdevFactor = stdevFactor;
                    return this;
                }

                public Builder setEnforcementPercentage(Integer enforcementPercentage) {
                    Preconditions.checkArgument(enforcementPercentage != null);
                    Preconditions.checkArgument(enforcementPercentage.intValue() >= 0 && enforcementPercentage.intValue() <= 100);
                    this.enforcementPercentage = enforcementPercentage;
                    return this;
                }

                public Builder setMinimumHosts(Integer minimumHosts) {
                    Preconditions.checkArgument(minimumHosts != null);
                    Preconditions.checkArgument(minimumHosts.intValue() >= 0);
                    this.minimumHosts = minimumHosts;
                    return this;
                }

                public Builder setRequestVolume(Integer requestVolume) {
                    Preconditions.checkArgument(requestVolume != null);
                    Preconditions.checkArgument(requestVolume.intValue() >= 0);
                    this.requestVolume = requestVolume;
                    return this;
                }

                public SuccessRateEjection build() {
                    return new SuccessRateEjection(this.stdevFactor, this.enforcementPercentage, this.minimumHosts, this.requestVolume);
                }
            }
        }

        public static class FailurePercentageEjection {
            public final Integer enforcementPercentage;
            public final Integer minimumHosts;
            public final Integer requestVolume;
            public final Integer threshold;

            FailurePercentageEjection(Integer threshold, Integer enforcementPercentage, Integer minimumHosts, Integer requestVolume) {
                this.threshold = threshold;
                this.enforcementPercentage = enforcementPercentage;
                this.minimumHosts = minimumHosts;
                this.requestVolume = requestVolume;
            }

            public static class Builder {
                Integer threshold = 85;
                Integer enforcementPercentage = 100;
                Integer minimumHosts = 5;
                Integer requestVolume = 50;

                public Builder setThreshold(Integer threshold) {
                    Preconditions.checkArgument(threshold != null);
                    Preconditions.checkArgument(threshold.intValue() >= 0 && threshold.intValue() <= 100);
                    this.threshold = threshold;
                    return this;
                }

                public Builder setEnforcementPercentage(Integer enforcementPercentage) {
                    Preconditions.checkArgument(enforcementPercentage != null);
                    Preconditions.checkArgument(enforcementPercentage.intValue() >= 0 && enforcementPercentage.intValue() <= 100);
                    this.enforcementPercentage = enforcementPercentage;
                    return this;
                }

                public Builder setMinimumHosts(Integer minimumHosts) {
                    Preconditions.checkArgument(minimumHosts != null);
                    Preconditions.checkArgument(minimumHosts.intValue() >= 0);
                    this.minimumHosts = minimumHosts;
                    return this;
                }

                public Builder setRequestVolume(Integer requestVolume) {
                    Preconditions.checkArgument(requestVolume != null);
                    Preconditions.checkArgument(requestVolume.intValue() >= 0);
                    this.requestVolume = requestVolume;
                    return this;
                }

                public FailurePercentageEjection build() {
                    return new FailurePercentageEjection(this.threshold, this.enforcementPercentage, this.minimumHosts, this.requestVolume);
                }
            }
        }

        boolean outlierDetectionEnabled() {
            return (this.successRateEjection == null && this.failurePercentageEjection == null) ? false : true;
        }
    }
}
