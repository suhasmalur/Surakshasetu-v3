package io.grpc.internal;

import com.google.common.base.Verify;
import io.grpc.internal.DnsNameResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/* JADX INFO: loaded from: classes10.dex */
final class JndiResourceResolverFactory implements DnsNameResolver.ResourceResolverFactory {

    @Nullable
    private static final Throwable JNDI_UNAVAILABILITY_CAUSE = initJndi();

    interface RecordFetcher {
        List<String> getAllRecords(String str, String str2) throws NamingException;
    }

    @Nullable
    private static Throwable initJndi() {
        try {
            Class.forName("javax.naming.directory.InitialDirContext");
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            return null;
        } catch (ClassNotFoundException e) {
            return e;
        } catch (Error e2) {
            return e2;
        } catch (RuntimeException e3) {
            return e3;
        }
    }

    @Override // io.grpc.internal.DnsNameResolver.ResourceResolverFactory
    @Nullable
    public DnsNameResolver.ResourceResolver newResourceResolver() {
        if (unavailabilityCause() != null) {
            return null;
        }
        return new JndiResourceResolver(new JndiRecordFetcher());
    }

    @Override // io.grpc.internal.DnsNameResolver.ResourceResolverFactory
    @Nullable
    public Throwable unavailabilityCause() {
        return JNDI_UNAVAILABILITY_CAUSE;
    }

    static final class JndiResourceResolver implements DnsNameResolver.ResourceResolver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final Logger logger = Logger.getLogger(JndiResourceResolver.class.getName());
        private static final Pattern whitespace = Pattern.compile("\\s+");
        private final RecordFetcher recordFetcher;

        public JndiResourceResolver(RecordFetcher recordFetcher) {
            this.recordFetcher = recordFetcher;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
        @Override // io.grpc.internal.DnsNameResolver.ResourceResolver
        public List<String> resolveTxt(String serviceConfigHostname) throws NamingException {
            if (logger.isLoggable(Level.FINER)) {
                logger.log(Level.FINER, "About to query TXT records for {0}", new Object[]{serviceConfigHostname});
            }
            List<String> serviceConfigRawTxtRecords = this.recordFetcher.getAllRecords("TXT", "dns:///" + serviceConfigHostname);
            if (logger.isLoggable(Level.FINER)) {
                logger.log(Level.FINER, "Found {0} TXT records", new Object[]{Integer.valueOf(serviceConfigRawTxtRecords.size())});
            }
            List<String> serviceConfigTxtRecords = new ArrayList<>(serviceConfigRawTxtRecords.size());
            for (String serviceConfigRawTxtRecord : serviceConfigRawTxtRecords) {
                serviceConfigTxtRecords.add(unquote(serviceConfigRawTxtRecord));
            }
            return Collections.unmodifiableList(serviceConfigTxtRecords);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
        @Override // io.grpc.internal.DnsNameResolver.ResourceResolver
        public List<DnsNameResolver.SrvRecord> resolveSrv(String host) throws Exception {
            if (logger.isLoggable(Level.FINER)) {
                logger.log(Level.FINER, "About to query SRV records for {0}", new Object[]{host});
            }
            List<String> rawSrvRecords = this.recordFetcher.getAllRecords("SRV", "dns:///" + host);
            if (logger.isLoggable(Level.FINER)) {
                logger.log(Level.FINER, "Found {0} SRV records", new Object[]{Integer.valueOf(rawSrvRecords.size())});
            }
            List<DnsNameResolver.SrvRecord> srvRecords = new ArrayList<>(rawSrvRecords.size());
            Exception first = null;
            Level level = Level.WARNING;
            for (String rawSrv : rawSrvRecords) {
                try {
                    String[] parts = whitespace.split(rawSrv, 5);
                    Verify.verify(parts.length == 4, "Bad SRV Record: %s", rawSrv);
                    if (!parts[3].endsWith(".")) {
                        throw new RuntimeException("Returned SRV host does not end in period: " + parts[3]);
                    }
                    srvRecords.add(new DnsNameResolver.SrvRecord(parts[3], Integer.parseInt(parts[2])));
                } catch (RuntimeException e) {
                    logger.log(level, "Failed to construct SRV record " + rawSrv, (Throwable) e);
                    if (first == null) {
                        first = e;
                        level = Level.FINE;
                    }
                }
            }
            if (srvRecords.isEmpty() && first != null) {
                throw first;
            }
            return Collections.unmodifiableList(srvRecords);
        }

        static String unquote(String txtRecord) {
            StringBuilder sb = new StringBuilder(txtRecord.length());
            boolean inquote = false;
            int i = 0;
            while (i < txtRecord.length()) {
                char c = txtRecord.charAt(i);
                if (!inquote) {
                    if (c != ' ') {
                        if (c == '\"') {
                            inquote = true;
                        } else {
                            sb.append(c);
                        }
                    }
                } else if (c == '\"') {
                    inquote = false;
                } else {
                    if (c == '\\' && (c = txtRecord.charAt((i = i + 1))) != '\"' && c != '\\') {
                        throw new AssertionError();
                    }
                    sb.append(c);
                }
                i++;
            }
            return sb.toString();
        }
    }

    static final class JndiRecordFetcher implements RecordFetcher {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        JndiRecordFetcher() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
        @Override // io.grpc.internal.JndiResourceResolverFactory.RecordFetcher
        public List<String> getAllRecords(String recordType, String name) throws NamingException {
            checkAvailable();
            String[] rrType = {recordType};
            List<String> records = new ArrayList<>();
            Hashtable<String, String> env = new Hashtable<>();
            env.put("com.sun.jndi.ldap.connect.timeout", "5000");
            env.put("com.sun.jndi.ldap.read.timeout", "5000");
            InitialDirContext initialDirContext = new InitialDirContext(env);
            try {
                Attributes attrs = initialDirContext.getAttributes(name, rrType);
                NamingEnumeration<? extends Attribute> rrGroups = attrs.getAll();
                while (rrGroups.hasMore()) {
                    try {
                        Attribute rrEntry = (Attribute) rrGroups.next();
                        if (!Arrays.asList(rrType).contains(rrEntry.getID())) {
                            throw new AssertionError();
                        }
                        NamingEnumeration<?> rrValues = rrEntry.getAll();
                        while (rrValues.hasMore()) {
                            try {
                                records.add(String.valueOf(rrValues.next()));
                            } catch (NamingException ne) {
                                closeThenThrow(rrValues, ne);
                            }
                        }
                        rrValues.close();
                    } catch (NamingException ne2) {
                        closeThenThrow(rrGroups, ne2);
                    }
                }
                rrGroups.close();
            } catch (NamingException ne3) {
                closeThenThrow((DirContext) initialDirContext, ne3);
            }
            initialDirContext.close();
            return records;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
        private static void closeThenThrow(NamingEnumeration<?> namingEnumeration, NamingException e) throws NamingException {
            try {
                namingEnumeration.close();
                throw e;
            } catch (NamingException e2) {
                throw e;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
        private static void closeThenThrow(DirContext ctx, NamingException e) throws NamingException {
            try {
                ctx.close();
                throw e;
            } catch (NamingException e2) {
                throw e;
            }
        }

        private static void checkAvailable() {
            if (JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE != null) {
                throw new UnsupportedOperationException("JNDI is not currently available", JndiResourceResolverFactory.JNDI_UNAVAILABILITY_CAUSE);
            }
        }
    }
}
