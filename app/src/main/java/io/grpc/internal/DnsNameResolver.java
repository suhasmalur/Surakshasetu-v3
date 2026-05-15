package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.SharedResourceHolder;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes10.dex */
public class DnsNameResolver extends NameResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long DEFAULT_NETWORK_CACHE_TTL_SECONDS = 30;
    static final String NETWORKADDRESS_CACHE_TTL_PROPERTY = "networkaddress.cache.ttl";
    private static final String SERVICE_CONFIG_NAME_PREFIX = "_grpc_config.";
    static final String SERVICE_CONFIG_PREFIX = "grpc_config=";
    private static String localHostname;
    private final String authority;
    private final long cacheTtlNanos;
    private Executor executor;
    private final SharedResourceHolder.Resource<Executor> executorResource;
    private final String host;
    private NameResolver.Listener2 listener;
    private final int port;
    final ProxyDetector proxyDetector;
    protected boolean resolved;
    private boolean resolving;
    private final NameResolver.ServiceConfigParser serviceConfigParser;
    private boolean shutdown;
    private final Stopwatch stopwatch;
    private final SynchronizationContext syncContext;
    private final boolean usingExecutorResource;
    private static final Logger logger = Logger.getLogger(DnsNameResolver.class.getName());
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY = "clientLanguage";
    private static final String SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY = "percentage";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY = "clientHostname";
    private static final String SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY = "serviceConfig";
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet(Arrays.asList(SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY)));
    private static final String JNDI_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "true");
    private static final String JNDI_LOCALHOST_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", "false");
    private static final String JNDI_TXT_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", "false");
    static boolean enableJndi = Boolean.parseBoolean(JNDI_PROPERTY);
    static boolean enableJndiLocalhost = Boolean.parseBoolean(JNDI_LOCALHOST_PROPERTY);
    protected static boolean enableTxt = Boolean.parseBoolean(JNDI_TXT_PROPERTY);
    private static final ResourceResolverFactory resourceResolverFactory = getResourceResolverFactory(DnsNameResolver.class.getClassLoader());
    private final Random random = new Random();
    protected volatile AddressResolver addressResolver = JdkAddressResolver.INSTANCE;
    private final AtomicReference<ResourceResolver> resourceResolver = new AtomicReference<>();

    public interface AddressResolver {
        List<InetAddress> resolveAddress(String str) throws Exception;
    }

    public interface ResourceResolver {
        List<SrvRecord> resolveSrv(String str) throws Exception;

        List<String> resolveTxt(String str) throws Exception;
    }

    interface ResourceResolverFactory {
        @Nullable
        ResourceResolver newResourceResolver();

        @Nullable
        Throwable unavailabilityCause();
    }

    protected DnsNameResolver(@Nullable String nsAuthority, String name, NameResolver.Args args, SharedResourceHolder.Resource<Executor> executorResource, Stopwatch stopwatch, boolean isAndroid) {
        Preconditions.checkNotNull(args, "args");
        this.executorResource = executorResource;
        URI nameUri = URI.create("//" + ((String) Preconditions.checkNotNull(name, "name")));
        Preconditions.checkArgument(nameUri.getHost() != null, "Invalid DNS name: %s", name);
        this.authority = (String) Preconditions.checkNotNull(nameUri.getAuthority(), "nameUri (%s) doesn't have an authority", nameUri);
        this.host = nameUri.getHost();
        if (nameUri.getPort() == -1) {
            this.port = args.getDefaultPort();
        } else {
            this.port = nameUri.getPort();
        }
        this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(args.getProxyDetector(), "proxyDetector");
        this.cacheTtlNanos = getNetworkAddressCacheTtlNanos(isAndroid);
        this.stopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(args.getSynchronizationContext(), "syncContext");
        this.executor = args.getOffloadExecutor();
        this.usingExecutorResource = this.executor == null;
        this.serviceConfigParser = (NameResolver.ServiceConfigParser) Preconditions.checkNotNull(args.getServiceConfigParser(), "serviceConfigParser");
    }

    @Override // io.grpc.NameResolver
    public String getServiceAuthority() {
        return this.authority;
    }

    protected String getHost() {
        return this.host;
    }

    @Override // io.grpc.NameResolver
    public void start(NameResolver.Listener2 listener) {
        Preconditions.checkState(this.listener == null, "already started");
        if (this.usingExecutorResource) {
            this.executor = (Executor) SharedResourceHolder.get(this.executorResource);
        }
        this.listener = (NameResolver.Listener2) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        resolve();
    }

    @Override // io.grpc.NameResolver
    public void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        resolve();
    }

    private List<EquivalentAddressGroup> resolveAddresses() {
        try {
            try {
                List<? extends InetAddress> addresses = this.addressResolver.resolveAddress(this.host);
                List<EquivalentAddressGroup> servers = new ArrayList<>(addresses.size());
                for (InetAddress inetAddr : addresses) {
                    servers.add(new EquivalentAddressGroup(new InetSocketAddress(inetAddr, this.port)));
                }
                return Collections.unmodifiableList(servers);
            } catch (Exception e) {
                Throwables.throwIfUnchecked(e);
                throw new RuntimeException(e);
            }
        } finally {
            if (0 != 0) {
                logger.log(Level.FINE, "Address resolution failure", (Throwable) null);
            }
        }
    }

    @Nullable
    private NameResolver.ConfigOrError resolveServiceConfig() throws Exception {
        List<String> txtRecords = Collections.emptyList();
        ResourceResolver resourceResolver = getResourceResolver();
        if (resourceResolver != null) {
            try {
                txtRecords = resourceResolver.resolveTxt(SERVICE_CONFIG_NAME_PREFIX + this.host);
            } catch (Exception e) {
                logger.log(Level.FINE, "ServiceConfig resolution failure", (Throwable) e);
            }
        }
        if (!txtRecords.isEmpty()) {
            NameResolver.ConfigOrError rawServiceConfig = parseServiceConfig(txtRecords, this.random, getLocalHostname());
            if (rawServiceConfig != null) {
                if (rawServiceConfig.getError() != null) {
                    return NameResolver.ConfigOrError.fromError(rawServiceConfig.getError());
                }
                Map<String, ?> verifiedRawServiceConfig = (Map) rawServiceConfig.getConfig();
                return this.serviceConfigParser.parseServiceConfig(verifiedRawServiceConfig);
            }
            return null;
        }
        logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{this.host});
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public EquivalentAddressGroup detectProxy() throws IOException {
        InetSocketAddress destination = InetSocketAddress.createUnresolved(this.host, this.port);
        ProxiedSocketAddress proxiedAddr = this.proxyDetector.proxyFor(destination);
        if (proxiedAddr != null) {
            return new EquivalentAddressGroup(proxiedAddr);
        }
        return null;
    }

    protected InternalResolutionResult doResolve(boolean forceTxt) {
        InternalResolutionResult result = new InternalResolutionResult();
        try {
            result.addresses = resolveAddresses();
        } catch (Exception e) {
            if (!forceTxt) {
                result.error = Status.UNAVAILABLE.withDescription("Unable to resolve host " + this.host).withCause(e);
                return result;
            }
        }
        if (enableTxt) {
            result.config = resolveServiceConfig();
        }
        return result;
    }

    private final class Resolve implements Runnable {
        private final NameResolver.Listener2 savedListener;

        Resolve(NameResolver.Listener2 savedListener) {
            this.savedListener = (NameResolver.Listener2) Preconditions.checkNotNull(savedListener, "savedListener");
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0088 A[DONT_GENERATE] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 312
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DnsNameResolver.Resolve.run():void");
        }
    }

    @Nullable
    static NameResolver.ConfigOrError parseServiceConfig(List<String> rawTxtRecords, Random random, String localHostname2) {
        try {
            List<Map<String, ?>> possibleServiceConfigChoices = parseTxtResults(rawTxtRecords);
            Map<String, ?> possibleServiceConfig = null;
            for (Map<String, ?> possibleServiceConfigChoice : possibleServiceConfigChoices) {
                try {
                    possibleServiceConfig = maybeChooseServiceConfig(possibleServiceConfigChoice, random, localHostname2);
                    if (possibleServiceConfig != null) {
                        break;
                    }
                } catch (RuntimeException e) {
                    return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to pick service config choice").withCause(e));
                }
            }
            if (possibleServiceConfig == null) {
                return null;
            }
            return NameResolver.ConfigOrError.fromConfig(possibleServiceConfig);
        } catch (IOException | RuntimeException e2) {
            return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("failed to parse TXT records").withCause(e2));
        }
    }

    private void resolve() {
        if (this.resolving || this.shutdown || !cacheRefreshRequired()) {
            return;
        }
        this.resolving = true;
        this.executor.execute(new Resolve(this.listener));
    }

    private boolean cacheRefreshRequired() {
        return !this.resolved || this.cacheTtlNanos == 0 || (this.cacheTtlNanos > 0 && this.stopwatch.elapsed(TimeUnit.NANOSECONDS) > this.cacheTtlNanos);
    }

    @Override // io.grpc.NameResolver
    public void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        if (this.executor != null && this.usingExecutorResource) {
            this.executor = (Executor) SharedResourceHolder.release(this.executorResource, this.executor);
        }
    }

    final int getPort() {
        return this.port;
    }

    static List<Map<String, ?>> parseTxtResults(List<String> txtRecords) throws IOException {
        List<Map<String, ?>> possibleServiceConfigChoices = new ArrayList<>();
        for (String txtRecord : txtRecords) {
            if (!txtRecord.startsWith(SERVICE_CONFIG_PREFIX)) {
                logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{txtRecord});
            } else {
                Object rawChoices = JsonParser.parse(txtRecord.substring(SERVICE_CONFIG_PREFIX.length()));
                if (!(rawChoices instanceof List)) {
                    throw new ClassCastException("wrong type " + rawChoices);
                }
                List<?> listChoices = (List) rawChoices;
                possibleServiceConfigChoices.addAll(JsonUtil.checkObjectList(listChoices));
            }
        }
        return possibleServiceConfigChoices;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getNumberAsDouble(serviceConfigChoice, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getListOfStrings(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY);
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, ?> serviceConfigChoice) {
        return JsonUtil.getListOfStrings(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY);
    }

    private static long getNetworkAddressCacheTtlNanos(boolean isAndroid) {
        if (isAndroid) {
            return 0L;
        }
        String cacheTtlPropertyValue = System.getProperty(NETWORKADDRESS_CACHE_TTL_PROPERTY);
        long cacheTtl = DEFAULT_NETWORK_CACHE_TTL_SECONDS;
        if (cacheTtlPropertyValue != null) {
            try {
                cacheTtl = Long.parseLong(cacheTtlPropertyValue);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{NETWORKADDRESS_CACHE_TTL_PROPERTY, cacheTtlPropertyValue, Long.valueOf(DEFAULT_NETWORK_CACHE_TTL_SECONDS)});
            }
        }
        return cacheTtl > 0 ? TimeUnit.SECONDS.toNanos(cacheTtl) : cacheTtl;
    }

    @Nullable
    static Map<String, ?> maybeChooseServiceConfig(Map<String, ?> choice, Random random, String hostname) {
        for (Map.Entry<String, ?> entry : choice.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", entry);
        }
        List<String> clientLanguages = getClientLanguagesFromChoice(choice);
        if (clientLanguages != null && !clientLanguages.isEmpty()) {
            boolean javaPresent = false;
            Iterator<String> it = clientLanguages.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String lang = it.next();
                if ("java".equalsIgnoreCase(lang)) {
                    javaPresent = true;
                    break;
                }
            }
            if (!javaPresent) {
                return null;
            }
        }
        Double percentage = getPercentageFromChoice(choice);
        if (percentage != null) {
            int pct = percentage.intValue();
            Verify.verify(pct >= 0 && pct <= 100, "Bad percentage: %s", percentage);
            if (random.nextInt(100) >= pct) {
                return null;
            }
        }
        List<String> clientHostnames = getHostnamesFromChoice(choice);
        if (clientHostnames != null && !clientHostnames.isEmpty()) {
            boolean hostnamePresent = false;
            Iterator<String> it2 = clientHostnames.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                String clientHostname = it2.next();
                if (clientHostname.equals(hostname)) {
                    hostnamePresent = true;
                    break;
                }
            }
            if (!hostnamePresent) {
                return null;
            }
        }
        Map<String, ?> sc = JsonUtil.getObject(choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY);
        if (sc == null) {
            throw new VerifyException(String.format("key '%s' missing in '%s'", choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY));
        }
        return sc;
    }

    protected static final class InternalResolutionResult {
        private List<EquivalentAddressGroup> addresses;
        public Attributes attributes;
        private NameResolver.ConfigOrError config;
        private Status error;

        private InternalResolutionResult() {
        }
    }

    public static final class SrvRecord {
        public final String host;
        public final int port;

        public SrvRecord(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public int hashCode() {
            return Objects.hashCode(this.host, Integer.valueOf(this.port));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SrvRecord that = (SrvRecord) obj;
            if (this.port == that.port && this.host.equals(that.host)) {
                return true;
            }
            return false;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("host", this.host).add("port", this.port).toString();
        }
    }

    protected void setAddressResolver(AddressResolver addressResolver) {
        this.addressResolver = addressResolver;
    }

    protected void setResourceResolver(ResourceResolver resourceResolver) {
        this.resourceResolver.set(resourceResolver);
    }

    private enum JdkAddressResolver implements AddressResolver {
        INSTANCE;

        @Override // io.grpc.internal.DnsNameResolver.AddressResolver
        public List<InetAddress> resolveAddress(String host) throws UnknownHostException {
            return Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(host)));
        }
    }

    @Nullable
    protected ResourceResolver getResourceResolver() {
        if (!shouldUseJndi(enableJndi, enableJndiLocalhost, this.host)) {
            return null;
        }
        ResourceResolver rr = this.resourceResolver.get();
        if (rr != null || resourceResolverFactory == null) {
            return rr;
        }
        if (resourceResolverFactory.unavailabilityCause() != null) {
            throw new AssertionError();
        }
        return resourceResolverFactory.newResourceResolver();
    }

    @Nullable
    static ResourceResolverFactory getResourceResolverFactory(ClassLoader loader) {
        try {
            try {
                Constructor<? extends ResourceResolverFactory> jndiCtor = Class.forName("io.grpc.internal.JndiResourceResolverFactory", true, loader).asSubclass(ResourceResolverFactory.class).getConstructor(new Class[0]);
                try {
                    ResourceResolverFactory rrf = (ResourceResolverFactory) jndiCtor.newInstance(new Object[0]);
                    if (rrf.unavailabilityCause() != null) {
                        logger.log(Level.FINE, "JndiResourceResolverFactory not available, skipping.", rrf.unavailabilityCause());
                        return null;
                    }
                    return rrf;
                } catch (Exception e) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", (Throwable) e);
                    return null;
                }
            } catch (Exception e2) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", (Throwable) e2);
                return null;
            }
        } catch (ClassCastException e3) {
            logger.log(Level.FINE, "Unable to cast JndiResourceResolverFactory, skipping.", (Throwable) e3);
            return null;
        } catch (ClassNotFoundException e4) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", (Throwable) e4);
            return null;
        }
    }

    private static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    protected static boolean shouldUseJndi(boolean jndiEnabled, boolean jndiLocalhostEnabled, String target) {
        if (!jndiEnabled) {
            return false;
        }
        if ("localhost".equalsIgnoreCase(target)) {
            return jndiLocalhostEnabled;
        }
        if (target.contains(":")) {
            return false;
        }
        boolean alldigits = true;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c != '.') {
                alldigits &= c >= '0' && c <= '9';
            }
        }
        return !alldigits;
    }
}
