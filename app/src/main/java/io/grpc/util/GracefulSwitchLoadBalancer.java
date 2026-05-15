package io.grpc.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public final class GracefulSwitchLoadBalancer extends ForwardingLoadBalancer {
    static final LoadBalancer.SubchannelPicker BUFFER_PICKER = new LoadBalancer.SubchannelPicker() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.2
        @Override // io.grpc.LoadBalancer.SubchannelPicker
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return "BUFFER_PICKER";
        }
    };

    @Nullable
    private LoadBalancer.Factory currentBalancerFactory;
    private boolean currentLbIsReady;
    private final LoadBalancer.Helper helper;

    @Nullable
    private LoadBalancer.Factory pendingBalancerFactory;
    private LoadBalancer.SubchannelPicker pendingPicker;
    private ConnectivityState pendingState;
    private final LoadBalancer defaultBalancer = new LoadBalancer() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.1
        @Override // io.grpc.LoadBalancer
        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            throw new IllegalStateException("GracefulSwitchLoadBalancer must switch to a load balancing policy before handling ResolvedAddresses");
        }

        @Override // io.grpc.LoadBalancer
        public void handleNameResolutionError(final Status error) {
            GracefulSwitchLoadBalancer.this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new LoadBalancer.SubchannelPicker() { // from class: io.grpc.util.GracefulSwitchLoadBalancer.1.1ErrorPicker
                @Override // io.grpc.LoadBalancer.SubchannelPicker
                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
                    return LoadBalancer.PickResult.withError(error);
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) C1ErrorPicker.class).add(Constants.IPC_BUNDLE_KEY_SEND_ERROR, error).toString();
                }
            });
        }

        @Override // io.grpc.LoadBalancer
        public void shutdown() {
        }
    };
    private LoadBalancer currentLb = this.defaultBalancer;
    private LoadBalancer pendingLb = this.defaultBalancer;

    public GracefulSwitchLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    public void switchTo(LoadBalancer.Factory newBalancerFactory) {
        Preconditions.checkNotNull(newBalancerFactory, "newBalancerFactory");
        if (newBalancerFactory.equals(this.pendingBalancerFactory)) {
            return;
        }
        this.pendingLb.shutdown();
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
        this.pendingState = ConnectivityState.CONNECTING;
        this.pendingPicker = BUFFER_PICKER;
        if (newBalancerFactory.equals(this.currentBalancerFactory)) {
            return;
        }
        C1PendingHelper pendingHelper = new C1PendingHelper();
        pendingHelper.lb = newBalancerFactory.newLoadBalancer(pendingHelper);
        this.pendingLb = pendingHelper.lb;
        this.pendingBalancerFactory = newBalancerFactory;
        if (!this.currentLbIsReady) {
            swap();
        }
    }

    /* JADX INFO: renamed from: io.grpc.util.GracefulSwitchLoadBalancer$1PendingHelper, reason: invalid class name */
    class C1PendingHelper extends ForwardingLoadBalancerHelper {
        LoadBalancer lb;

        C1PendingHelper() {
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper
        protected LoadBalancer.Helper delegate() {
            return GracefulSwitchLoadBalancer.this.helper;
        }

        @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
        public void updateBalancingState(ConnectivityState newState, LoadBalancer.SubchannelPicker newPicker) {
            if (this.lb == GracefulSwitchLoadBalancer.this.pendingLb) {
                Preconditions.checkState(GracefulSwitchLoadBalancer.this.currentLbIsReady, "there's pending lb while current lb has been out of READY");
                GracefulSwitchLoadBalancer.this.pendingState = newState;
                GracefulSwitchLoadBalancer.this.pendingPicker = newPicker;
                if (newState == ConnectivityState.READY) {
                    GracefulSwitchLoadBalancer.this.swap();
                    return;
                }
                return;
            }
            if (this.lb == GracefulSwitchLoadBalancer.this.currentLb) {
                GracefulSwitchLoadBalancer.this.currentLbIsReady = newState == ConnectivityState.READY;
                if (GracefulSwitchLoadBalancer.this.currentLbIsReady || GracefulSwitchLoadBalancer.this.pendingLb == GracefulSwitchLoadBalancer.this.defaultBalancer) {
                    GracefulSwitchLoadBalancer.this.helper.updateBalancingState(newState, newPicker);
                } else {
                    GracefulSwitchLoadBalancer.this.swap();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void swap() {
        this.helper.updateBalancingState(this.pendingState, this.pendingPicker);
        this.currentLb.shutdown();
        this.currentLb = this.pendingLb;
        this.currentBalancerFactory = this.pendingBalancerFactory;
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
    }

    @Override // io.grpc.util.ForwardingLoadBalancer
    protected LoadBalancer delegate() {
        return this.pendingLb == this.defaultBalancer ? this.currentLb : this.pendingLb;
    }

    @Override // io.grpc.util.ForwardingLoadBalancer, io.grpc.LoadBalancer
    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo stateInfo) {
        throw new UnsupportedOperationException("handleSubchannelState() is not supported by " + getClass().getName());
    }

    @Override // io.grpc.util.ForwardingLoadBalancer, io.grpc.LoadBalancer
    public void shutdown() {
        this.pendingLb.shutdown();
        this.currentLb.shutdown();
    }
}
