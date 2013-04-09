/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.security;

import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.x509.BasicConstraints;
import com.android.org.bouncycastle.asn1.x509.GeneralName;
import com.android.org.bouncycastle.asn1.x509.GeneralNames;
import com.android.org.bouncycastle.asn1.x509.GeneralSubtree;
import com.android.org.bouncycastle.asn1.x509.KeyUsage;
import com.android.org.bouncycastle.asn1.x509.NameConstraints;
import com.android.org.bouncycastle.asn1.x509.X509Extensions;
import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.android.org.bouncycastle.x509.X509V3CertificateGenerator;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.security.auth.x500.X500Principal;
import junit.framework.Assert;
import libcore.javax.net.ssl.TestKeyManager;
import libcore.javax.net.ssl.TestTrustManager;

/**
 * TestKeyStore is a convenience class for other tests that
 * want a canned KeyStore with a variety of key pairs.
 *
 * Creating a key store is relatively slow, so a singleton instance is
 * accessible via TestKeyStore.get().
 */
public final class TestKeyStore extends Assert {

    private static TestKeyStore ROOT_CA;

    private static TestKeyStore SERVER;
    private static TestKeyStore CLIENT;
    private static TestKeyStore CLIENT_CERTIFICATE;

    private static TestKeyStore CLIENT_2;

    static {
        if (StandardNames.IS_RI) {
            // Needed to create BKS keystore but add at end so most
            // algorithm come from the default providers
            Security.insertProviderAt(new BouncyCastleProvider(),
                                      Security.getProviders().length+1);
        }
    }

    private static final boolean TEST_MANAGERS = true;

    public final KeyStore keyStore;
    public final char[] storePassword;
    public final char[] keyPassword;
    public final KeyManager[] keyManagers;
    public final TrustManager[] trustManagers;
    public final TestKeyManager keyManager;
    public final TestTrustManager trustManager;

    private TestKeyStore(KeyStore keyStore, char[] storePassword, char[] keyPassword) {
        this.keyStore = keyStore;
        this.storePassword = storePassword;
        this.keyPassword = keyPassword;
        this.keyManagers = createKeyManagers(keyStore, storePassword);
        this.trustManagers = createTrustManagers(keyStore);
        this.keyManager = (TestKeyManager)keyManagers[0];
        this.trustManager = (TestTrustManager)trustManagers[0];
    }

    public static KeyManager[] createKeyManagers(KeyStore keyStore, char[] storePassword) {
        try {
            String kmfa = KeyManagerFactory.getDefaultAlgorithm();
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(kmfa);
            kmf.init(keyStore, storePassword);
            return TestKeyManager.wrap(kmf.getKeyManagers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TrustManager[] createTrustManagers(final KeyStore keyStore) {
        try {
            String tmfa = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfa);
            tmf.init(keyStore);
            return TestTrustManager.wrap(tmf.getTrustManagers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lazily create shared test certificates.
     */
    private static synchronized void initCerts() {
        if (ROOT_CA != null) {
            return;
        }
        ROOT_CA = new Builder()
                .aliasPrefix("RootCA")
                .subject("CN=Test Root Certificate Authority")
                .ca(true)
                .build();
        TestKeyStore intermediateCa = new Builder()
                .aliasPrefix("IntermediateCA")
                .subject("CN=Test Intermediate Certificate Authority")
                .ca(true)
                .signer(ROOT_CA.getPrivateKey("RSA", "RSA"))
                .rootCa(ROOT_CA.getRootCertificate("RSA"))
                .build();
        SERVER = new Builder()
                .aliasPrefix("server")
                .signer(intermediateCa.getPrivateKey("RSA", "RSA"))
                .rootCa(intermediateCa.getRootCertificate("RSA"))
                .build();
        CLIENT = new TestKeyStore(createClient(intermediateCa.keyStore), null, null);
        CLIENT_CERTIFICATE = new Builder()
                .aliasPrefix("client")
                .subject("emailAddress=test@user")
                .signer(intermediateCa.getPrivateKey("RSA", "RSA"))
                .rootCa(intermediateCa.getRootCertificate("RSA"))
                .build();
        TestKeyStore rootCa2 = new Builder()
                .aliasPrefix("RootCA2")
                .subject("CN=Test Root Certificate Authority 2")
                .ca(true)
                .build();
        CLIENT_2 = new TestKeyStore(createClient(rootCa2.keyStore), null, null);
    }

    /**
     * Return a server keystore with a matched RSA certificate and
     * private key as well as a CA certificate.
     */
    public static TestKeyStore getServer() {
        initCerts();
        return SERVER;
    }

    /**
     * Return a keystore with a CA certificate
     */
    public static TestKeyStore getClient() {
        initCerts();
        return CLIENT;
    }

    /**
     * Return a client keystore with a matched RSA certificate and
     * private key as well as a CA certificate.
     */
    public static TestKeyStore getClientCertificate() {
        initCerts();
        return CLIENT_CERTIFICATE;
    }

    /**
     * Return a keystore with a second CA certificate that does not
     * trust the server certificate returned by getServer for negative
     * testing.
     */
    public static TestKeyStore getClientCA2() {
        initCerts();
        return CLIENT_2;
    }

    /**
     * Creates KeyStores containing the requested key types. Since key
     * generation can be expensive, most tests should reuse the RSA-only
     * singleton instance returned by TestKeyStore.get.
     */
    public static class Builder {
        private String[] keyAlgorithms = { "RSA" };
        private char[] storePassword;
        private char[] keyPassword;
        private String aliasPrefix;
        private X500Principal subject;
        private int keyUsage;
        private boolean ca;
        private PrivateKeyEntry signer;
        private Certificate rootCa;
        private final List<GeneralName> subjectAltNames = new ArrayList<GeneralName>();
        private final Vector<GeneralSubtree> permittedNameConstraints
                = new Vector<GeneralSubtree>();
        private final Vector<GeneralSubtree> excludedNameConstraints = new Vector<GeneralSubtree>();

        public Builder() {
            subject = localhost();
        }

        /**
         * Sets the requested key types to generate and include. The default is
         * RSA only.
         */
        public Builder keyAlgorithms(String... keyAlgorithms) {
            this.keyAlgorithms = keyAlgorithms;
            return this;
        }

        /** A unique prefix to identify the key aliases */
        public Builder aliasPrefix(String aliasPrefix) {
            this.aliasPrefix = aliasPrefix;
            return this;
        }

        /**
         * Sets the subject common name. The default is the local host's
         * canonical name.
         */
        public Builder subject(X500Principal subject) {
            this.subject = subject;
            return this;
        }

        public Builder subject(String commonName) {
            return subject(new X500Principal(commonName));
        }

        /** {@link KeyUsage} bit mask for 2.5.29.15 extension */
        public Builder keyUsage(int keyUsage) {
            this.keyUsage = keyUsage;
            return this;
        }

        /** true If the keys being created are for a CA */
        public Builder ca(boolean ca) {
            this.ca = ca;
            return this;
        }

        /** a private key entry to be used for signing, otherwise self-sign */
        public Builder signer(PrivateKeyEntry signer) {
            this.signer = signer;
            return this;
        }

        /** a root CA to include in the final store */
        public Builder rootCa(Certificate rootCa) {
            this.rootCa = rootCa;
            return this;
        }

        private Builder addSubjectAltName(GeneralName generalName) {
            subjectAltNames.add(generalName);
            return this;
        }

        public Builder addSubjectAltNameIpAddress(byte[] ipAddress) {
            return addSubjectAltName(
                    new GeneralName(GeneralName.iPAddress, new DEROctetString(ipAddress)));
        }

        private Builder addNameConstraint(boolean permitted, GeneralName generalName) {
            if (permitted) {
                permittedNameConstraints.add(new GeneralSubtree(generalName));
            } else {
                excludedNameConstraints.add(new GeneralSubtree(generalName));
            }
            return this;
        }

        public Builder addNameConstraint(boolean permitted, byte[] ipAddress) {
            return addNameConstraint(permitted,
                    new GeneralName(GeneralName.iPAddress, new DEROctetString(ipAddress)));
        }

        public TestKeyStore build() {
            try {
                if (StandardNames.IS_RI) {
                    // JKS does not allow null password
                    if (storePassword == null) {
                        storePassword = "password".toCharArray();
                    }
                    if (keyPassword == null) {
                        keyPassword = "password".toCharArray();
                    }
                }

                KeyStore keyStore = createKeyStore();
                for (String keyAlgorithm : keyAlgorithms) {
                    String publicAlias  = aliasPrefix + "-public-"  + keyAlgorithm;
                    String privateAlias = aliasPrefix + "-private-" + keyAlgorithm;
                    if (keyAlgorithm.equals("EC_RSA") && signer == null && rootCa == null) {
                        createKeys(keyStore, keyAlgorithm, publicAlias, privateAlias,
                                   privateKey(keyStore, keyPassword, "RSA", "RSA"));
                        continue;
                    }
                    createKeys(keyStore, keyAlgorithm, publicAlias, privateAlias, signer);
                }
                if (rootCa != null) {
                    keyStore.setCertificateEntry(aliasPrefix
                                                 + "-root-ca-"
                                                 + rootCa.getPublicKey().getAlgorithm(),
                                                 rootCa);
                }
                return new TestKeyStore(keyStore, storePassword, keyPassword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Add newly generated keys of a given key type to an existing
         * KeyStore. The PrivateKey will be stored under the specified
         * private alias name. The X509Certificate will be stored on the
         * public alias name and have the given subject distinguished
         * name.
         *
         * If a CA is provided, it will be used to sign the generated
         * certificate. Otherwise, the certificate will be self
         * signed. The certificate will be valid for one day before and
         * one day after the time of creation.
         *
         * Based on:
         * org.bouncycastle.jce.provider.test.SigTest
         * org.bouncycastle.jce.provider.test.CertTest
         */
        private KeyStore createKeys(KeyStore keyStore,
                String keyAlgorithm,
                String publicAlias,
                String privateAlias,
                PrivateKeyEntry signer) throws Exception {
            PrivateKey caKey;
            X509Certificate caCert;
            X509Certificate[] caCertChain;
            if (signer == null) {
                caKey = null;
                caCert = null;
                caCertChain = null;
            } else {
                caKey = signer.getPrivateKey();
                caCert = (X509Certificate)signer.getCertificate();
                caCertChain = (X509Certificate[])signer.getCertificateChain();
            }

            PrivateKey privateKey;
            X509Certificate x509c;
            if (publicAlias == null && privateAlias == null) {
                // don't want anything apparently
                privateKey = null;
                x509c = null;
            } else {
                // 1.) we make the keys
                int keySize;
                if (keyAlgorithm.equals("RSA")) {
                    // 512 breaks SSL_RSA_EXPORT_* on RI and TLS_ECDHE_RSA_WITH_RC4_128_SHA for us
                    keySize =  1024;
                } else if (keyAlgorithm.equals("DSA")) {
                    keySize = 512;
                } else if (keyAlgorithm.equals("EC")) {
                    keySize = 256;
                } else if (keyAlgorithm.equals("EC_RSA")) {
                    keySize = 256;
                    keyAlgorithm = "EC";
                } else {
                    throw new IllegalArgumentException("Unknown key algorithm " + keyAlgorithm);
                }

                KeyPairGenerator kpg = KeyPairGenerator.getInstance(keyAlgorithm);
                kpg.initialize(keySize, new SecureRandom());

                KeyPair kp = kpg.generateKeyPair();
                privateKey = kp.getPrivate();
                PublicKey publicKey  = kp.getPublic();

                // 2.) use keys to make certificate
                X500Principal issuer = ((caCert != null)
                                        ? caCert.getSubjectX500Principal()
                                        : subject);
                PrivateKey signingKey = (caKey == null) ? privateKey : caKey;
                x509c = createCertificate(publicKey, signingKey, subject, issuer, keyUsage, ca,
                                          subjectAltNames,
                                          permittedNameConstraints, excludedNameConstraints);
            }

            X509Certificate[] x509cc;
            if (privateAlias == null) {
                // don't need certificate chain
                x509cc = null;
            } else if (caCertChain == null) {
                x509cc = new X509Certificate[] { x509c };
            } else {
                x509cc = new X509Certificate[caCertChain.length+1];
                x509cc[0] = x509c;
                System.arraycopy(caCertChain, 0, x509cc, 1, caCertChain.length);
            }

            // 3.) put certificate and private key into the key store
            if (privateAlias != null) {
                keyStore.setKeyEntry(privateAlias, privateKey, keyPassword, x509cc);
            }
            if (publicAlias != null) {
                keyStore.setCertificateEntry(publicAlias, x509c);
            }
            return keyStore;
        }

        private X500Principal localhost() {
            try {
                return new X500Principal("CN=" + InetAddress.getLocalHost().getHostName());
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static X509Certificate createCa(PublicKey publicKey,
                                           PrivateKey privateKey,
                                           String subject)  {
        try {
            X500Principal principal = new X500Principal(subject);
            return createCertificate(publicKey, privateKey,
                                     principal, principal,
                                     0, true,
                                     new Vector<GeneralName>(),
                                     new Vector<GeneralSubtree>(),
                                     new Vector<GeneralSubtree>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static X509Certificate createCertificate(
            PublicKey publicKey,
            PrivateKey privateKey,
            X500Principal subject,
            X500Principal issuer,
            int keyUsage,
            boolean ca,
            List<GeneralName> subjectAltNames,
            Vector<GeneralSubtree> permittedNameConstraints,
            Vector<GeneralSubtree> excludedNameConstraints) throws Exception {
        // Note that there is no way to programmatically make a
        // Certificate using java.* or javax.* APIs. The
        // CertificateFactory interface assumes you want to read
        // in a stream of bytes, typically the X.509 factory would
        // allow ASN.1 DER encoded bytes and optionally some PEM
        // formats. Here we use Bouncy Castle's
        // X509V3CertificateGenerator and related classes.

        long millisPerDay = 24 * 60 * 60 * 1000;
        long now = System.currentTimeMillis();
        Date start = new Date(now - millisPerDay);
        Date end = new Date(now + millisPerDay);
        BigInteger serial = BigInteger.valueOf(1);

        String keyAlgorithm = privateKey.getAlgorithm();
        String signatureAlgorithm;
        if (keyAlgorithm.equals("RSA")) {
            signatureAlgorithm = "sha1WithRSA";
        } else if (keyAlgorithm.equals("DSA")) {
            signatureAlgorithm = "sha1WithDSA";
        } else if (keyAlgorithm.equals("EC")) {
            signatureAlgorithm = "sha1WithECDSA";
        } else if (keyAlgorithm.equals("EC_RSA")) {
            signatureAlgorithm = "sha1WithRSA";
        } else {
            throw new IllegalArgumentException("Unknown key algorithm " + keyAlgorithm);
        }

        X509V3CertificateGenerator x509cg = new X509V3CertificateGenerator();
        x509cg.setSubjectDN(subject);
        x509cg.setIssuerDN(issuer);
        x509cg.setNotBefore(start);
        x509cg.setNotAfter(end);
        x509cg.setPublicKey(publicKey);
        x509cg.setSignatureAlgorithm(signatureAlgorithm);
        x509cg.setSerialNumber(serial);
        if (keyUsage != 0) {
            x509cg.addExtension(X509Extensions.KeyUsage,
                                true,
                                new KeyUsage(keyUsage));
        }
        if (ca) {
            x509cg.addExtension(X509Extensions.BasicConstraints,
                                true,
                                new BasicConstraints(true));
        }
        for (GeneralName subjectAltName : subjectAltNames) {
            x509cg.addExtension(X509Extensions.SubjectAlternativeName,
                                false,
                                new GeneralNames(subjectAltName).getEncoded());
        }
        if (!permittedNameConstraints.isEmpty() || !excludedNameConstraints.isEmpty()) {
            x509cg.addExtension(X509Extensions.NameConstraints,
                                true,
                                new NameConstraints(permittedNameConstraints,
                                                    excludedNameConstraints));
        }

        if (privateKey instanceof ECPrivateKey) {
            /*
             * bouncycastle needs its own ECPrivateKey implementation
             */
            KeyFactory kf = KeyFactory.getInstance(keyAlgorithm, "BC");
            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            privateKey = kf.generatePrivate(ks);
        }
        X509Certificate x509c = x509cg.generateX509Certificate(privateKey);
        if (StandardNames.IS_RI) {
            /*
             * The RI can't handle the BC EC signature algorithm
             * string of "ECDSA", since it expects "...WITHEC...",
             * so convert from BC to RI X509Certificate
             * implementation via bytes.
             */
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bais = new ByteArrayInputStream(x509c.getEncoded());
            Certificate c = cf.generateCertificate(bais);
            x509c = (X509Certificate) c;
        }
        return x509c;
    }

    /**
     * Return the key algorithm for a possible compound algorithm
     * identifier containing an underscore. If not underscore is
     * present, the argument is returned unmodified. However for an
     * algorithm such as EC_RSA, return EC.
     */
    public static String keyAlgorithm(String algorithm) {
        int index = algorithm.indexOf('_');
        if (index == -1) {
            return algorithm;
        }
        return algorithm.substring(0, index);
    }


    /**
     * Return the signature algorithm for a possible compound
     * algorithm identifier containing an underscore. If not
     * underscore is present, the argument is returned
     * unmodified. However for an algorithm such as EC_RSA, return
     * RSA.
     */
    public static String signatureAlgorithm(String algorithm) {
        int index = algorithm.indexOf('_');
        if (index == -1) {
            return algorithm;
        }
        return algorithm.substring(index+1, algorithm.length());
    }

    /**
     * Create an empty KeyStore
     *
     * The KeyStore is optionally password protected by the
     * keyStorePassword argument, which can be null if a password is
     * not desired.
     */
    public static KeyStore createKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance(StandardNames.KEY_STORE_ALGORITHM);
            keyStore.load(null, null);
            return keyStore;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the only private key in a TestKeyStore for the given
     * algorithms. Throws IllegalStateException if there are are more
     * or less than one.
     */
    public PrivateKeyEntry getPrivateKey(String keyAlgorithm, String signatureAlgorithm) {
        return privateKey(keyStore, keyPassword, keyAlgorithm, signatureAlgorithm);
    }

    /**
     * Return the only private key in a keystore for the given
     * algorithms. Throws IllegalStateException if there are are more
     * or less than one.
     */
    public static PrivateKeyEntry privateKey(KeyStore keyStore, char[] keyPassword,
            String keyAlgorithm, String signatureAlgorithm) {
        try {
            PrivateKeyEntry found = null;
            PasswordProtection password = new PasswordProtection(keyPassword);
            for (String alias : Collections.list(keyStore.aliases())) {
                if (!keyStore.entryInstanceOf(alias, PrivateKeyEntry.class)) {
                    continue;
                }
                PrivateKeyEntry privateKey = (PrivateKeyEntry) keyStore.getEntry(alias, password);
                if (!privateKey.getPrivateKey().getAlgorithm().equals(keyAlgorithm)) {
                    continue;
                }
                X509Certificate certificate = (X509Certificate) privateKey.getCertificate();
                if (!certificate.getSigAlgName().contains(signatureAlgorithm)) {
                    continue;
                }
                if (found != null) {
                    throw new IllegalStateException("KeyStore has more than one private key for "
                                                    + " keyAlgorithm: " + keyAlgorithm
                                                    + " signatureAlgorithm: " + signatureAlgorithm
                                                    + "\nfirst: " + found.getPrivateKey()
                                                    + "\nsecond: " + privateKey.getPrivateKey() );
                }
                found = privateKey;
            }
            if (found == null) {
                throw new IllegalStateException("KeyStore contained no private key for "
                                                + " keyAlgorithm: " + keyAlgorithm
                                                + " signatureAlgorithm: " + signatureAlgorithm);
            }
            return found;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the issuing CA certificate of the given
     * certificate. Throws IllegalStateException if there are are more
     * or less than one.
     */
    public Certificate getIssuer(Certificate cert) throws Exception {
        return issuer(keyStore, cert);
    }

    /**
     * Return the issuing CA certificate of the given
     * certificate. Throws IllegalStateException if there are are more
     * or less than one.
     */
    public static Certificate issuer(KeyStore keyStore, Certificate c)
            throws Exception {
        if (!(c instanceof X509Certificate)) {
            throw new IllegalStateException("issuer requires an X509Certificate, found " + c);
        }
        X509Certificate cert = (X509Certificate) c;

        Certificate found = null;
        for (String alias : Collections.list(keyStore.aliases())) {
            if (!keyStore.entryInstanceOf(alias, TrustedCertificateEntry.class)) {
                continue;
            }
            TrustedCertificateEntry certificateEntry =
                    (TrustedCertificateEntry) keyStore.getEntry(alias, null);
            Certificate certificate = certificateEntry.getTrustedCertificate();
            if (!(certificate instanceof X509Certificate)) {
                continue;
            }
            X509Certificate x = (X509Certificate) certificate;
            if (!cert.getIssuerDN().equals(x.getSubjectDN())) {
                continue;
            }
            if (found != null) {
                throw new IllegalStateException("KeyStore has more than one issuing CA for "
                                                + cert
                                                + "\nfirst: " + found
                                                + "\nsecond: " + certificate );
            }
            found = certificate;
        }
        if (found == null) {
            throw new IllegalStateException("KeyStore contained no issuing CA for " + cert);
        }
        return found;
    }

    /**
     * Return the only self-signed root certificate in a TestKeyStore
     * for the given algorithm. Throws IllegalStateException if there
     * are are more or less than one.
     */
    public X509Certificate getRootCertificate(String algorithm)  {
        return rootCertificate(keyStore, algorithm);
    }

    /**
     * Return the only self-signed root certificate in a keystore for
     * the given algorithm. Throws IllegalStateException if there are
     * are more or less than one.
     */
    public static X509Certificate rootCertificate(KeyStore keyStore, String algorithm) {
        try {
            X509Certificate found = null;
            for (String alias : Collections.list(keyStore.aliases())) {
                if (!keyStore.entryInstanceOf(alias, TrustedCertificateEntry.class)) {
                    continue;
                }
                TrustedCertificateEntry certificateEntry =
                        (TrustedCertificateEntry) keyStore.getEntry(alias, null);
                Certificate certificate = certificateEntry.getTrustedCertificate();
                if (!certificate.getPublicKey().getAlgorithm().equals(algorithm)) {
                    continue;
                }
                if (!(certificate instanceof X509Certificate)) {
                    continue;
                }
                X509Certificate x = (X509Certificate) certificate;
                if (!x.getIssuerDN().equals(x.getSubjectDN())) {
                    continue;
                }
                if (found != null) {
                    throw new IllegalStateException("KeyStore has more than one root CA for "
                                                    + algorithm
                                                    + "\nfirst: " + found
                                                    + "\nsecond: " + certificate );
                }
                found = x;
            }
            if (found == null) {
                throw new IllegalStateException("KeyStore contained no root CA for " + algorithm);
            }
            return found;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a client key store that only contains self-signed certificates but no private keys
     */
    public static KeyStore createClient(KeyStore caKeyStore) {
        KeyStore clientKeyStore = createKeyStore();
        copySelfSignedCertificates(clientKeyStore, caKeyStore);
        return clientKeyStore;
    }

    /**
     * Copy self-signed certificates from one key store to another.
     * Returns true if successful, false if no match found.
     */
    public static boolean copySelfSignedCertificates(KeyStore dst, KeyStore src) {
        try {
            boolean copied = false;
            for (String alias : Collections.list(src.aliases())) {
                if (!src.isCertificateEntry(alias)) {
                    continue;
                }
                X509Certificate cert = (X509Certificate)src.getCertificate(alias);
                if (!cert.getSubjectDN().equals(cert.getIssuerDN())) {
                    continue;
                }
                dst.setCertificateEntry(alias, cert);
                copied = true;
            }
            return copied;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copy named certificates from one key store to another.
     * Returns true if successful, false if no match found.
     */
    public static boolean copyCertificate(Principal subject, KeyStore dst, KeyStore src)
            throws Exception {
        for (String alias : Collections.list(src.aliases())) {
            if (!src.isCertificateEntry(alias)) {
                continue;
            }
            X509Certificate cert = (X509Certificate)src.getCertificate(alias);
            if (!cert.getSubjectDN().equals(subject)) {
                continue;
            }
            dst.setCertificateEntry(alias, cert);
            return true;
        }
        return false;
    }

    /**
     * Dump a key store for debugging.
     */
    public static void dump(String context, KeyStore keyStore, char[] keyPassword)
            throws KeyStoreException, NoSuchAlgorithmException {
        PrintStream out = System.out;
        out.println("context=" + context);
        out.println("\tkeyStore=" + keyStore);
        out.println("\tkeyStore.type=" + keyStore.getType());
        out.println("\tkeyStore.provider=" + keyStore.getProvider());
        out.println("\tkeyPassword="
                    + ((keyPassword == null) ? null : new String(keyPassword)));
        out.println("\tsize=" + keyStore.size());
        for (String alias : Collections.list(keyStore.aliases())) {
            out.println("alias=" + alias);
            out.println("\tcreationDate=" + keyStore.getCreationDate(alias));
            if (keyStore.isCertificateEntry(alias)) {
                out.println("\tcertificate:");
                out.println("==========================================");
                out.println(keyStore.getCertificate(alias));
                out.println("==========================================");
                continue;
            }
            if (keyStore.isKeyEntry(alias)) {
                out.println("\tkey:");
                out.println("==========================================");
                String key;
                try {
                    key = ("Key retrieved using password\n"
                           + keyStore.getKey(alias, keyPassword));
                } catch (UnrecoverableKeyException e1) {
                    try {
                        key = ("Key retrieved without password\n"
                               + keyStore.getKey(alias, null));
                    } catch (UnrecoverableKeyException e2) {
                        key = "Key could not be retrieved";
                    }
                }
                out.println(key);
                out.println("==========================================");
                Certificate[] chain = keyStore.getCertificateChain(alias);
                if (chain == null) {
                    out.println("No certificate chain associated with key");
                    out.println("==========================================");
                } else {
                    for (int i = 0; i < chain.length; i++) {
                        out.println("Certificate chain element #" + i);
                        out.println(chain[i]);
                        out.println("==========================================");
                    }
                }
                continue;
            }
            out.println("\tunknown entry type");
        }
    }

    public static void assertChainLength(Object[] chain) {
        /*
         * Note chain is Object[] to support both
         * java.security.cert.X509Certificate and
         * javax.security.cert.X509Certificate
         */
        assertEquals(3, chain.length);
    }
}
