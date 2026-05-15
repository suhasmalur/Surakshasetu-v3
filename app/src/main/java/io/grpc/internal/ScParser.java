package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.NameResolver;
import io.grpc.Status;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public final class ScParser extends NameResolver.ServiceConfigParser {
    private final AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory;
    private final int maxHedgedAttemptsLimit;
    private final int maxRetryAttemptsLimit;
    private final boolean retryEnabled;

    public ScParser(boolean retryEnabled, int maxRetryAttemptsLimit, int maxHedgedAttemptsLimit, AutoConfiguredLoadBalancerFactory autoLoadBalancerFactory) {
        this.retryEnabled = retryEnabled;
        this.maxRetryAttemptsLimit = maxRetryAttemptsLimit;
        this.maxHedgedAttemptsLimit = maxHedgedAttemptsLimit;
        this.autoLoadBalancerFactory = (AutoConfiguredLoadBalancerFactory) Preconditions.checkNotNull(autoLoadBalancerFactory, "autoLoadBalancerFactory");
    }

    @Override // io.grpc.NameResolver.ServiceConfigParser
    public NameResolver.ConfigOrError parseServiceConfig(Map<String, ?> rawServiceConfig) {
        Object loadBalancingPolicySelection;
        try {
            NameResolver.ConfigOrError choiceFromLoadBalancer = this.autoLoadBalancerFactory.parseLoadBalancerPolicy(rawServiceConfig);
            if (choiceFromLoadBalancer == null) {
                loadBalancingPolicySelection = null;
            } else {
                Object loadBalancingPolicySelection2 = choiceFromLoadBalancer.getError();
                if (loadBalancingPolicySelection2 != null) {
                    return NameResolver.ConfigOrError.fromError(choiceFromLoadBalancer.getError());
                }
                loadBalancingPolicySelection = choiceFromLoadBalancer.getConfig();
            }
            return NameResolver.ConfigOrError.fromConfig(ManagedChannelServiceConfig.fromServiceConfig(rawServiceConfig, this.retryEnabled, this.maxRetryAttemptsLimit, this.maxHedgedAttemptsLimit, loadBalancingPolicySelection));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse service config").withCause(e));
        }
    }
}
