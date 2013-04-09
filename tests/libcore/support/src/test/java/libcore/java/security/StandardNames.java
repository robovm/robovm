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

import java.security.Security;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHPrivateKeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import junit.framework.Assert;

/**
 * This class defines expected string names for protocols, key types,
 * client and server auth types, cipher suites.
 *
 * Initially based on "Appendix A: Standard Names" of
 * <a href="http://java.sun.com/j2se/1.5.0/docs/guide/security/jsse/JSSERefGuide.html#AppA">
 * Java &trade; Secure Socket Extension (JSSE) Reference Guide
 * for the Java &trade; 2 Platform Standard Edition 5
 * </a>.
 *
 * Updated based on the
 * <a href="http://download.oracle.com/javase/7/docs/technotes/guides/security/SunProviders.html">
 * Java &trade; Cryptography Architecture Oracle Providers Documentation
 * for Java &trade; Platform Standard Edition 7
 * </a>.
 * See also the
 * <a href="http://download.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html">
 * Java &trade; Cryptography Architecture Standard Algorithm Name Documentation
 * </a>.
 *
 * Further updates based on the
 * <a href=http://java.sun.com/javase/6/docs/technotes/guides/security/p11guide.html">
 * Java &trade; PKCS#11 Reference Guide
 * </a>.
 */
public final class StandardNames extends Assert {

//    public static final boolean IS_RI
//            = !"Dalvik Core Library".equals(System.getProperty("java.specification.name"));
    // RoboVM note: Changed to detect RoboVM properly
    public static final boolean IS_RI
            = !"RoboVM Core Library".equals(System.getProperty("java.specification.name"));
    public static final String JSSE_PROVIDER_NAME = (IS_RI) ? "SunJSSE" : "AndroidOpenSSL";
    public static final String SECURITY_PROVIDER_NAME = (IS_RI) ? "SUN" : "BC";

    public static final String KEY_MANAGER_FACTORY_DEFAULT = (IS_RI) ? "SunX509" : "X509";
    public static final String TRUST_MANAGER_FACTORY_DEFAULT = (IS_RI) ? "PKIX" : "X509";

    public static final String KEY_STORE_ALGORITHM = (IS_RI) ? "JKS" : "BKS";

    /**
     * RFC 5746's Signaling Cipher Suite Value to indicate a request for secure renegotiation
     */
    public static final String CIPHER_SUITE_SECURE_RENEGOTIATION
            = "TLS_EMPTY_RENEGOTIATION_INFO_SCSV";

    /**
     * A map from algorithm type (e.g. Cipher) to a set of algorithms (e.g. AES, DES, ...)
     */
    public static final Map<String,Set<String>> PROVIDER_ALGORITHMS
            = new HashMap<String,Set<String>>();
    private static void provide(String type, String algorithm) {
        Set<String> algorithms = PROVIDER_ALGORITHMS.get(type);
        if (algorithms == null) {
            algorithms = new HashSet();
            PROVIDER_ALGORITHMS.put(type, algorithms);
        }
        assertTrue("Duplicate " + type + " " + algorithm,
                   algorithms.add(algorithm.toUpperCase()));
    }
    private static void unprovide(String type, String algorithm) {
        Set<String> algorithms = PROVIDER_ALGORITHMS.get(type);
        assertNotNull(algorithms);
        assertTrue(algorithm, algorithms.remove(algorithm.toUpperCase()));
        if (algorithms.isEmpty()) {
            assertNotNull(PROVIDER_ALGORITHMS.remove(type));
        }
    }
    static {
        provide("AlgorithmParameterGenerator", "DSA");
        provide("AlgorithmParameterGenerator", "DiffieHellman");
        provide("AlgorithmParameters", "AES");
        provide("AlgorithmParameters", "Blowfish");
        provide("AlgorithmParameters", "DES");
        provide("AlgorithmParameters", "DESede");
        provide("AlgorithmParameters", "DSA");
        provide("AlgorithmParameters", "DiffieHellman");
        provide("AlgorithmParameters", "OAEP");
        provide("AlgorithmParameters", "PBEWithMD5AndDES");
        provide("AlgorithmParameters", "PBEWithMD5AndTripleDES");
        provide("AlgorithmParameters", "PBEWithSHA1AndDESede");
        provide("AlgorithmParameters", "PBEWithSHA1AndRC2_40");
        provide("AlgorithmParameters", "RC2");
        provide("CertPathBuilder", "PKIX");
        provide("CertPathValidator", "PKIX");
        provide("CertStore", "Collection");
        provide("CertStore", "LDAP");
        provide("CertificateFactory", "X.509");
        provide("Cipher", "AES");
        provide("Cipher", "AESWrap");
        provide("Cipher", "ARCFOUR");
        provide("Cipher", "Blowfish");
        provide("Cipher", "DES");
        provide("Cipher", "DESede");
        provide("Cipher", "DESedeWrap");
        provide("Cipher", "PBEWithMD5AndDES");
        provide("Cipher", "PBEWithMD5AndTripleDES");
        provide("Cipher", "PBEWithSHA1AndDESede");
        provide("Cipher", "PBEWithSHA1AndRC2_40");
        provide("Cipher", "RC2");
        provide("Cipher", "RSA");
        provide("Configuration", "JavaLoginConfig");
        provide("KeyAgreement", "DiffieHellman");
        provide("KeyFactory", "DSA");
        provide("KeyFactory", "DiffieHellman");
        provide("KeyFactory", "RSA");
        provide("KeyGenerator", "AES");
        provide("KeyGenerator", "ARCFOUR");
        provide("KeyGenerator", "Blowfish");
        provide("KeyGenerator", "DES");
        provide("KeyGenerator", "DESede");
        provide("KeyGenerator", "HmacMD5");
        provide("KeyGenerator", "HmacSHA1");
        provide("KeyGenerator", "HmacSHA256");
        provide("KeyGenerator", "HmacSHA384");
        provide("KeyGenerator", "HmacSHA512");
        provide("KeyGenerator", "RC2");
        provide("KeyInfoFactory", "DOM");
        provide("KeyManagerFactory", "SunX509");
        provide("KeyPairGenerator", "DSA");
        provide("KeyPairGenerator", "DiffieHellman");
        provide("KeyPairGenerator", "RSA");
        provide("KeyStore", "JCEKS");
        provide("KeyStore", "JKS");
        provide("KeyStore", "PKCS12");
        provide("Mac", "HmacMD5");
        provide("Mac", "HmacSHA1");
        provide("Mac", "HmacSHA256");
        provide("Mac", "HmacSHA384");
        provide("Mac", "HmacSHA512");
        provide("MessageDigest", "MD2");
        provide("MessageDigest", "MD5");
        provide("MessageDigest", "SHA-256");
        provide("MessageDigest", "SHA-384");
        provide("MessageDigest", "SHA-512");
        provide("Policy", "JavaPolicy");
        provide("SSLContext", "SSLv3");
        provide("SSLContext", "TLSv1");
        provide("SSLContext", "TLSv1.1");
        provide("SSLContext", "TLSv1.2");
        provide("SecretKeyFactory", "DES");
        provide("SecretKeyFactory", "DESede");
        provide("SecretKeyFactory", "PBEWithMD5AndDES");
        provide("SecretKeyFactory", "PBEWithMD5AndTripleDES");
        provide("SecretKeyFactory", "PBEWithSHA1AndDESede");
        provide("SecretKeyFactory", "PBEWithSHA1AndRC2_40");
        provide("SecretKeyFactory", "PBKDF2WithHmacSHA1");
        provide("SecureRandom", "SHA1PRNG");
        provide("Signature", "MD2withRSA");
        provide("Signature", "MD5withRSA");
        provide("Signature", "NONEwithDSA");
        provide("Signature", "SHA1withDSA");
        provide("Signature", "SHA1withRSA");
        provide("Signature", "SHA256withRSA");
        provide("Signature", "SHA384withRSA");
        provide("Signature", "SHA512withRSA");
        provide("TerminalFactory", "PC/SC");
        provide("TransformService", "http://www.w3.org/2000/09/xmldsig#base64");
        provide("TransformService", "http://www.w3.org/2000/09/xmldsig#enveloped-signature");
        provide("TransformService", "http://www.w3.org/2001/10/xml-exc-c14n#");
        provide("TransformService", "http://www.w3.org/2001/10/xml-exc-c14n#WithComments");
        provide("TransformService", "http://www.w3.org/2002/06/xmldsig-filter2");
        provide("TransformService", "http://www.w3.org/TR/1999/REC-xpath-19991116");
        provide("TransformService", "http://www.w3.org/TR/1999/REC-xslt-19991116");
        provide("TransformService", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
        provide("TransformService", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments");
        provide("TrustManagerFactory", "PKIX");
        provide("XMLSignatureFactory", "DOM");

        // Not clearly documented by RI
        provide("GssApiMechanism", "1.2.840.113554.1.2.2");
        provide("GssApiMechanism", "1.3.6.1.5.5.2");

        // Not correctly documented by RI which left off the Factory suffix
        provide("SaslClientFactory", "CRAM-MD5");
        provide("SaslClientFactory", "DIGEST-MD5");
        provide("SaslClientFactory", "EXTERNAL");
        provide("SaslClientFactory", "GSSAPI");
        provide("SaslClientFactory", "PLAIN");
        provide("SaslServerFactory", "CRAM-MD5");
        provide("SaslServerFactory", "DIGEST-MD5");
        provide("SaslServerFactory", "GSSAPI");

        // Documentation seems to list alias instead of actual name
        // provide("MessageDigest", "SHA-1");
        provide("MessageDigest", "SHA");

        // Mentioned in javadoc, not documentation
        provide("SSLContext", "Default");

        // Not documented as in RI 6 but mentioned in Standard Names
        provide("AlgorithmParameters", "PBE");
        provide("SSLContext", "SSL");
        provide("SSLContext", "TLS");

        // Not documented as in RI 6 but that exist in RI 6
        if (IS_RI) {
            provide("CertStore", "com.sun.security.IndexedCollection");
            provide("KeyGenerator", "SunTlsKeyMaterial");
            provide("KeyGenerator", "SunTlsMasterSecret");
            provide("KeyGenerator", "SunTlsPrf");
            provide("KeyGenerator", "SunTlsRsaPremasterSecret");
            provide("KeyManagerFactory", "NewSunX509");
            provide("KeyStore", "CaseExactJKS");
            provide("Mac", "HmacPBESHA1");
            provide("Mac", "SslMacMD5");
            provide("Mac", "SslMacSHA1");
            provide("SecureRandom", "NativePRNG");
            provide("Signature", "MD5andSHA1withRSA");
            provide("TrustManagerFactory", "SunX509");
        }

        // Only available with the SunPKCS11-NSS provider,
        // which seems to be enabled in OpenJDK 6 but not Oracle Java 6
        if (Security.getProvider("SunPKCS11-NSS") != null) {
            provide("AlgorithmParameters", "EC");
            provide("Cipher", "AES/CBC/NOPADDING");
            provide("Cipher", "DES/CBC/NOPADDING");
            provide("Cipher", "DESEDE/CBC/NOPADDING");
            provide("Cipher", "RSA/ECB/PKCS1PADDING");
            provide("KeyAgreement", "DH");
            provide("KeyAgreement", "ECDH");
            provide("KeyFactory", "DH");
            provide("KeyFactory", "EC");
            provide("KeyPairGenerator", "DH");
            provide("KeyPairGenerator", "EC");
            provide("KeyStore", "PKCS11");
            provide("MessageDigest", "SHA1");
            provide("SecretKeyFactory", "AES");
            provide("SecretKeyFactory", "ARCFOUR");
            provide("SecureRandom", "PKCS11");
            provide("Signature", "DSA");
            provide("Signature", "NONEWITHECDSA");
            provide("Signature", "RAWDSA");
            provide("Signature", "SHA1WITHECDSA");
            provide("Signature", "SHA256WITHECDSA");
            provide("Signature", "SHA384WITHECDSA");
            provide("Signature", "SHA512WITHECDSA");
        }

        // Fixups for dalvik
        if (!IS_RI) {

            // whole types that we do not provide
            PROVIDER_ALGORITHMS.remove("Configuration");
            PROVIDER_ALGORITHMS.remove("GssApiMechanism");
            PROVIDER_ALGORITHMS.remove("KeyInfoFactory");
            PROVIDER_ALGORITHMS.remove("Policy");
            PROVIDER_ALGORITHMS.remove("SaslClientFactory");
            PROVIDER_ALGORITHMS.remove("SaslServerFactory");
            PROVIDER_ALGORITHMS.remove("TerminalFactory");
            PROVIDER_ALGORITHMS.remove("TransformService");
            PROVIDER_ALGORITHMS.remove("XMLSignatureFactory");

            // different names Diffie-Hellman vs DH
            unprovide("AlgorithmParameterGenerator", "DiffieHellman");
            provide("AlgorithmParameterGenerator", "DH");
            unprovide("AlgorithmParameters", "DiffieHellman");
            provide("AlgorithmParameters", "DH");
            unprovide("KeyAgreement", "DiffieHellman");
            provide("KeyAgreement", "DH");
            unprovide("KeyFactory", "DiffieHellman");
            provide("KeyFactory", "DH");
            unprovide("KeyPairGenerator", "DiffieHellman");
            provide("KeyPairGenerator", "DH");

            // different names PBEWithSHA1AndDESede vs PBEWithSHAAnd3-KEYTripleDES-CBC
            unprovide("AlgorithmParameters", "PBEWithSHA1AndDESede");
            unprovide("Cipher", "PBEWithSHA1AndDESede");
            unprovide("SecretKeyFactory", "PBEWithSHA1AndDESede");
            provide("AlgorithmParameters", "PKCS12PBE");
            provide("Cipher", "PBEWithSHAAnd3-KEYTripleDES-CBC");
            provide("SecretKeyFactory", "PBEWithSHAAnd3-KEYTripleDES-CBC");

            // different names: dropped Sun
            unprovide("KeyManagerFactory", "SunX509");
            provide("KeyManagerFactory", "X509");

            // different names: BouncyCastle actually uses the Standard name of SHA-1 vs SHA
            unprovide("MessageDigest", "SHA");
            provide("MessageDigest", "SHA-1");

            // different names: added "Encryption" suffix
            unprovide("Signature", "MD5withRSA");
            provide("Signature", "MD5WithRSAEncryption");
            unprovide("Signature", "SHA1withRSA");
            provide("Signature", "SHA1WithRSAEncryption");
            unprovide("Signature", "SHA256WithRSA");
            provide("Signature", "SHA256WithRSAEncryption");
            unprovide("Signature", "SHA384WithRSA");
            provide("Signature", "SHA384WithRSAEncryption");
            unprovide("Signature", "SHA512WithRSA");
            provide("Signature", "SHA512WithRSAEncryption");

            // different names: JSSE Reference Guide says PKIX aka X509
            unprovide("TrustManagerFactory", "PKIX");
            provide("TrustManagerFactory", "X509");

            // different names: ARCFOUR vs ARC4
            unprovide("Cipher", "ARCFOUR");
            provide("Cipher", "ARC4");
            unprovide("KeyGenerator", "ARCFOUR");
            provide("KeyGenerator", "ARC4");

            // different case names: Blowfish vs BLOWFISH
            unprovide("AlgorithmParameters", "Blowfish");
            provide("AlgorithmParameters", "BLOWFISH");
            unprovide("Cipher", "Blowfish");
            provide("Cipher", "BLOWFISH");
            unprovide("KeyGenerator", "Blowfish");
            provide("KeyGenerator", "BLOWFISH");

            // Harmony has X.509, BouncyCastle X509
            // TODO remove one, probably Harmony's
            provide("CertificateFactory", "X509");

            // not just different names, but different binary formats
            unprovide("KeyStore", "JKS");
            provide("KeyStore", "BKS");
            unprovide("KeyStore", "JCEKS");
            provide("KeyStore", "BouncyCastle");

            // Noise to support KeyStore.PKCS12
            provide("Cipher", "PBEWITHMD5AND128BITAES-CBC-OPENSSL");
            provide("Cipher", "PBEWITHMD5AND192BITAES-CBC-OPENSSL");
            provide("Cipher", "PBEWITHMD5AND256BITAES-CBC-OPENSSL");
            provide("Cipher", "PBEWITHMD5ANDRC2");
            provide("Cipher", "PBEWITHSHA1ANDDES");
            provide("Cipher", "PBEWITHSHA1ANDRC2");
            provide("Cipher", "PBEWITHSHA256AND128BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHA256AND192BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHA256AND256BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHAAND128BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHAAND128BITRC2-CBC");
            provide("Cipher", "PBEWITHSHAAND128BITRC4");
            provide("Cipher", "PBEWITHSHAAND192BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            provide("Cipher", "PBEWITHSHAAND256BITAES-CBC-BC");
            provide("Cipher", "PBEWITHSHAAND40BITRC2-CBC");
            provide("Cipher", "PBEWITHSHAAND40BITRC4");
            provide("Cipher", "PBEWITHSHAANDTWOFISH-CBC");
            provide("Mac", "PBEWITHHMACSHA");
            provide("Mac", "PBEWITHHMACSHA1");
            provide("SecretKeyFactory", "PBEWITHHMACSHA1");
            provide("SecretKeyFactory", "PBEWITHMD5AND128BITAES-CBC-OPENSSL");
            provide("SecretKeyFactory", "PBEWITHMD5AND192BITAES-CBC-OPENSSL");
            provide("SecretKeyFactory", "PBEWITHMD5AND256BITAES-CBC-OPENSSL");
            provide("SecretKeyFactory", "PBEWITHMD5ANDRC2");
            provide("SecretKeyFactory", "PBEWITHSHA1ANDDES");
            provide("SecretKeyFactory", "PBEWITHSHA1ANDRC2");
            provide("SecretKeyFactory", "PBEWITHSHA256AND128BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHA256AND192BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHA256AND256BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHAAND128BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHAAND128BITRC2-CBC");
            provide("SecretKeyFactory", "PBEWITHSHAAND128BITRC4");
            provide("SecretKeyFactory", "PBEWITHSHAAND192BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            provide("SecretKeyFactory", "PBEWITHSHAAND256BITAES-CBC-BC");
            provide("SecretKeyFactory", "PBEWITHSHAAND40BITRC2-CBC");
            provide("SecretKeyFactory", "PBEWITHSHAAND40BITRC4");
            provide("SecretKeyFactory", "PBEWITHSHAANDTWOFISH-CBC");

            // removed LDAP
            unprovide("CertStore", "LDAP");

            // removed MD2
            unprovide("MessageDigest", "MD2");
            unprovide("Signature", "MD2withRSA");

            // removed RC2
            // NOTE the implementation remains to support PKCS12 keystores
            unprovide("AlgorithmParameters", "PBEWithSHA1AndRC2_40");
            unprovide("AlgorithmParameters", "RC2");
            unprovide("Cipher", "PBEWithSHA1AndRC2_40");
            unprovide("Cipher", "RC2");
            unprovide("KeyGenerator", "RC2");
            unprovide("SecretKeyFactory", "PBEWithSHA1AndRC2_40");

            // PBEWithMD5AndTripleDES is Sun proprietary
            unprovide("AlgorithmParameters", "PBEWithMD5AndTripleDES");
            unprovide("Cipher", "PBEWithMD5AndTripleDES");
            unprovide("SecretKeyFactory", "PBEWithMD5AndTripleDES");

            // missing from Bouncy Castle
            // Standard Names document says to use specific PBEWith*And*
            unprovide("AlgorithmParameters", "PBE");

            // missing from Bouncy Castle
            // TODO add to JDKAlgorithmParameters perhaps as wrapper on PBES2Parameters
            // For now, can use AlgorithmParametersSpec javax.crypto.spec.PBEParameterSpec instead
            unprovide("AlgorithmParameters", "PBEWithMD5AndDES"); // 1.2.840.113549.1.5.3

            // EC support
            // provide("AlgorithmParameters", "EC");
            provide("KeyAgreement", "ECDH");
            provide("KeyFactory", "EC");
            provide("KeyPairGenerator", "EC");
            provide("Signature", "NONEWITHECDSA");
            provide("Signature", "ECDSA"); // as opposed to SHA1WITHECDSA
            provide("Signature", "SHA256WITHECDSA");
            provide("Signature", "SHA384WITHECDSA");
            provide("Signature", "SHA512WITHECDSA");

            // Android's CA store
            provide("KeyStore", "AndroidCAStore");
        }
    }

    public static final String SSL_CONTEXT_PROTOCOLS_DEFAULT = "Default";
    public static final Set<String> SSL_CONTEXT_PROTOCOLS = new HashSet<String>(Arrays.asList(
        SSL_CONTEXT_PROTOCOLS_DEFAULT,
        "SSL",
        // "SSLv2",
        "SSLv3",
        "TLS",
        "TLSv1",
        "TLSv1.1",
        "TLSv1.2"));
    public static final String SSL_CONTEXT_PROTOCOL_DEFAULT = "TLS";

    public static final Set<String> KEY_TYPES = new HashSet<String>(Arrays.asList(
        "RSA",
        "DSA",
        // DH_* are specified by standard names, but do not seem to be supported by RI
        // "DH_RSA",
        // "DH_DSA",
        "EC",
        "EC_EC",
        "EC_RSA"));

    public static final Set<String> SSL_SOCKET_PROTOCOLS = new HashSet<String>(Arrays.asList(
        // "SSLv2",
        "SSLv3",
        "TLSv1",
        "TLSv1.1",
        "TLSv1.2"));
    static {
        if (IS_RI) {
            /* Even though we use OpenSSL's SSLv23_method which
             * supports sending SSLv2 client hello messages, the
             * OpenSSL implementation in s23_client_hello disables
             * this if SSL_OP_NO_SSLv2 is specified, which we always
             * do to disable general use of SSLv2.
             */
            SSL_SOCKET_PROTOCOLS.add("SSLv2Hello");
        }
    }

    public static final Set<String> SSL_SOCKET_PROTOCOLS_SSLENGINE = new HashSet<String>(SSL_SOCKET_PROTOCOLS);
    static {
        // No TLSv1.1 or TLSv1.2 support on SSLEngine based provider
        if (!IS_RI) {
            SSL_SOCKET_PROTOCOLS_SSLENGINE.remove("TLSv1.1");
            SSL_SOCKET_PROTOCOLS_SSLENGINE.remove("TLSv1.2");
        }
    }

    /**
     * Valid values for X509TrustManager.checkClientTrusted authType,
     * either the algorithm of the public key or UNKNOWN.
     */
    public static final Set<String> CLIENT_AUTH_TYPES = new HashSet<String>(Arrays.asList(
        "RSA",
        "DSA",
        "EC",
        "UNKNOWN"));

    /**
     * Valid values for X509TrustManager.checkServerTrusted authType,
     * either key exchange algorithm part of the cipher suite
     * or UNKNOWN.
     */
    public static final Set<String> SERVER_AUTH_TYPES = new HashSet<String>(Arrays.asList(
        "DHE_DSS",
        "DHE_DSS_EXPORT",
        "DHE_RSA",
        "DHE_RSA_EXPORT",
        "DH_DSS_EXPORT",
        "DH_RSA_EXPORT",
        "DH_anon",
        "DH_anon_EXPORT",
        "KRB5",
        "KRB5_EXPORT",
        "RSA",
        "RSA_EXPORT",
        "RSA_EXPORT1024",
        "ECDH_ECDSA",
        "ECDH_RSA",
        "ECDHE_ECDSA",
        "ECDHE_RSA",
        "UNKNOWN"));

    public static final String CIPHER_SUITE_INVALID = "SSL_NULL_WITH_NULL_NULL";

    public static final Set<String> CIPHER_SUITES_NEITHER = new HashSet<String>();

    public static final Set<String> CIPHER_SUITES_RI = new LinkedHashSet<String>();
    public static final Set<String> CIPHER_SUITES_OPENSSL = new LinkedHashSet<String>();

    public static final Set<String> CIPHER_SUITES;

    private static final void addRi(String cipherSuite) {
        CIPHER_SUITES_RI.add(cipherSuite);
    }

    private static final void addOpenSsl(String cipherSuite) {
        CIPHER_SUITES_OPENSSL.add(cipherSuite);
    }

    private static final void addBoth(String cipherSuite) {
        addRi(cipherSuite);
        addOpenSsl(cipherSuite);
    }

    private static final void addNeither(String cipherSuite) {
        CIPHER_SUITES_NEITHER.add(cipherSuite);
    }

    static {
        // Note these are added in priority order as defined by RI 7 documentation.
        // defaultCipherSuites
        addNeither("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
        addNeither("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        addNeither("TLS_RSA_WITH_AES_256_CBC_SHA256");
        addNeither("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
        addNeither("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
        addNeither("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
        addNeither("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
        addOpenSsl("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_RSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
        addRi(     "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_RSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
        addRi(     "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
        addBoth(   "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_RSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
        addBoth(   "TLS_ECDHE_RSA_WITH_RC4_128_SHA");
        addBoth(   "SSL_RSA_WITH_RC4_128_SHA");
        addBoth(   "TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
        addBoth(   "TLS_ECDH_RSA_WITH_RC4_128_SHA");
        addBoth(   "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "SSL_RSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "SSL_RSA_WITH_RC4_128_MD5");
        // RFC 5746's Signaling Cipher Suite Value to indicate a request for secure renegotiation
        addBoth(CIPHER_SUITE_SECURE_RENEGOTIATION);

        // non-defaultCipherSuites
        addNeither("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
        addOpenSsl("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
        addOpenSsl("TLS_DH_anon_WITH_AES_256_CBC_SHA");
        addRi(     "TLS_DH_anon_WITH_AES_128_CBC_SHA256");
        addBoth(   "TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_DH_anon_WITH_AES_128_CBC_SHA");
        addBoth(   "TLS_ECDH_anon_WITH_RC4_128_SHA");
        addBoth(   "SSL_DH_anon_WITH_RC4_128_MD5");
        addBoth(   "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
        addBoth(   "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
        addRi(     "TLS_RSA_WITH_NULL_SHA256");
        addBoth(   "TLS_ECDHE_ECDSA_WITH_NULL_SHA");
        addBoth(   "TLS_ECDHE_RSA_WITH_NULL_SHA");
        addBoth(   "SSL_RSA_WITH_NULL_SHA");
        addBoth(   "TLS_ECDH_ECDSA_WITH_NULL_SHA");
        addBoth(   "TLS_ECDH_RSA_WITH_NULL_SHA");
        addBoth(   "TLS_ECDH_anon_WITH_NULL_SHA");
        addBoth(   "SSL_RSA_WITH_NULL_MD5");
        addBoth(   "SSL_RSA_WITH_DES_CBC_SHA");
        addBoth(   "SSL_DHE_RSA_WITH_DES_CBC_SHA");
        addBoth(   "SSL_DHE_DSS_WITH_DES_CBC_SHA");
        addBoth(   "SSL_DH_anon_WITH_DES_CBC_SHA");
        addBoth(   "SSL_RSA_EXPORT_WITH_RC4_40_MD5");
        addBoth(   "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
        addBoth(   "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
        addBoth(   "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
        addBoth(   "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
        addBoth(   "SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");

        // Android does not have Keberos support
        addRi(     "TLS_KRB5_WITH_RC4_128_SHA");
        addRi(     "TLS_KRB5_WITH_RC4_128_MD5");
        addRi(     "TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
        addRi(     "TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
        addRi(     "TLS_KRB5_WITH_DES_CBC_SHA");
        addRi(     "TLS_KRB5_WITH_DES_CBC_MD5");
        addRi(     "TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
        addRi(     "TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
        addRi(     "TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
        addRi(     "TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");

        // Dropped
        addNeither("SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA");
        addNeither("SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA");

        // Old non standard exportable encryption
        addNeither("SSL_RSA_EXPORT1024_WITH_DES_CBC_SHA");
        addNeither("SSL_RSA_EXPORT1024_WITH_RC4_56_SHA");

        // No RC2
        addNeither("SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5");
        addNeither("TLS_KRB5_EXPORT_WITH_RC2_CBC_40_SHA");
        addNeither("TLS_KRB5_EXPORT_WITH_RC2_CBC_40_MD5");

        CIPHER_SUITES = (IS_RI) ? CIPHER_SUITES_RI : CIPHER_SUITES_OPENSSL;
    }

    public static final List<String> CIPHER_SUITES_DEFAULT = (IS_RI)
            ? Arrays.asList("TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                            "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
                            "SSL_RSA_WITH_RC4_128_SHA",
                            "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                            "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                            "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                            "TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
                            "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
                            "SSL_RSA_WITH_RC4_128_MD5",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                            "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
                            "SSL_RSA_WITH_3DES_EDE_CBC_SHA")
            : Arrays.asList("SSL_RSA_WITH_RC4_128_MD5",
                            "SSL_RSA_WITH_RC4_128_SHA",
                            "TLS_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
                            "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
                            "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
                            "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
                            "SSL_RSA_WITH_DES_CBC_SHA",
                            "SSL_DHE_RSA_WITH_DES_CBC_SHA",
                            "SSL_DHE_DSS_WITH_DES_CBC_SHA",
                            "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
                            "SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",
                            "SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
                            "SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA",
                            CIPHER_SUITE_SECURE_RENEGOTIATION);

    public static final Set<String> CIPHER_SUITES_SSLENGINE = new HashSet<String>(CIPHER_SUITES);
    static {
        // No Elliptic Curve support on SSLEngine based provider
        if (!IS_RI) {
            Iterator<String> i = CIPHER_SUITES_SSLENGINE.iterator();
            while (i.hasNext()) {
                String cs = i.next();
                if (cs.startsWith("TLS_EC") || cs.equals(CIPHER_SUITE_SECURE_RENEGOTIATION)) {
                    i.remove();
                }
            }
        }
    }

    public static final Map<String, Class<? extends KeySpec>> PRIVATE_KEY_SPEC_CLASSES;
    public static final Map<String, Class<? extends KeySpec>> PUBLIC_KEY_SPEC_CLASSES;
    public static final Map<String, Integer> MINIMUM_KEY_SIZE;
    static {
        PRIVATE_KEY_SPEC_CLASSES = new HashMap<String, Class<? extends KeySpec>>();
        PUBLIC_KEY_SPEC_CLASSES = new HashMap<String, Class<? extends KeySpec>>();
        MINIMUM_KEY_SIZE = new HashMap<String, Integer>();
        PRIVATE_KEY_SPEC_CLASSES.put("RSA", RSAPrivateCrtKeySpec.class);
        PUBLIC_KEY_SPEC_CLASSES.put("RSA", RSAPublicKeySpec.class);
        MINIMUM_KEY_SIZE.put("RSA", 256);
        PRIVATE_KEY_SPEC_CLASSES.put("DSA", DSAPrivateKeySpec.class);
        PUBLIC_KEY_SPEC_CLASSES.put("DSA", DSAPublicKeySpec.class);
        MINIMUM_KEY_SIZE.put("DSA", 512);
        PRIVATE_KEY_SPEC_CLASSES.put("DH", DHPrivateKeySpec.class);
        PUBLIC_KEY_SPEC_CLASSES.put("DH", DHPublicKeySpec.class);
        MINIMUM_KEY_SIZE.put("DH", 256);
        PRIVATE_KEY_SPEC_CLASSES.put("EC", ECPrivateKeySpec.class);
        PUBLIC_KEY_SPEC_CLASSES.put("EC", ECPublicKeySpec.class);
        MINIMUM_KEY_SIZE.put("EC", 256);
    }

    public static Class<? extends KeySpec> getPrivateKeySpecClass(String algName) {
        return PRIVATE_KEY_SPEC_CLASSES.get(algName);
    }

    public static Class<? extends KeySpec> getPublicKeySpecClass(String algName) {
        return PUBLIC_KEY_SPEC_CLASSES.get(algName);
    }

    public static int getMinimumKeySize(String algName) {
        return MINIMUM_KEY_SIZE.get(algName);
    }

    /**
     * Asserts that the cipher suites array is non-null and that it
     * all of its contents are cipher suites known to this
     * implementation. As a convenience, returns any unenabled cipher
     * suites in a test for those that want to verify separately that
     * all cipher suites were included.
     */
    public static Set<String> assertValidCipherSuites(Set<String> expected, String[] cipherSuites) {
        assertNotNull(cipherSuites);
        assertTrue(cipherSuites.length != 0);

        // Make sure all cipherSuites names are expected
        Set remainingCipherSuites = new HashSet<String>(expected);
        Set unknownCipherSuites = new HashSet<String>();
        for (String cipherSuite : cipherSuites) {
            boolean removed = remainingCipherSuites.remove(cipherSuite);
            if (!removed) {
                unknownCipherSuites.add(cipherSuite);
            }
        }
        assertEquals("Unknown cipher suites", Collections.EMPTY_SET, unknownCipherSuites);
        return remainingCipherSuites;
    }

    /**
     * After using assertValidCipherSuites on cipherSuites,
     * assertSupportedCipherSuites additionally verifies that all
     * supported cipher suites where in the input array.
     */
    public static void assertSupportedCipherSuites(Set<String> expected, String[] cipherSuites) {
        Set<String> remainingCipherSuites = assertValidCipherSuites(expected, cipherSuites);
        assertEquals("Missing cipher suites", Collections.EMPTY_SET, remainingCipherSuites);
        assertEquals(expected.size(), cipherSuites.length);
    }

    /**
     * Asserts that the protocols array is non-null and that it all of
     * its contents are protocols known to this implementation. As a
     * convenience, returns any unenabled protocols in a test for
     * those that want to verify separately that all protocols were
     * included.
     */
    public static Set<String> assertValidProtocols(Set<String> expected, String[] protocols) {
        assertNotNull(protocols);
        assertTrue(protocols.length != 0);

        // Make sure all protocols names are expected
        Set remainingProtocols = new HashSet<String>(expected);
        Set unknownProtocols = new HashSet<String>();
        for (String protocol : protocols) {
            if (!remainingProtocols.remove(protocol)) {
                unknownProtocols.add(protocol);
            }
        }
        assertEquals("Unknown protocols", Collections.EMPTY_SET, unknownProtocols);
        return remainingProtocols;
    }

    /**
     * After using assertValidProtocols on protocols,
     * assertSupportedProtocols additionally verifies that all
     * supported protocols where in the input array.
     */
    public static void assertSupportedProtocols(Set<String> expected, String[] protocols) {
        Set<String> remainingProtocols = assertValidProtocols(expected, protocols);
        assertEquals("Missing protocols", Collections.EMPTY_SET, remainingProtocols);
        assertEquals(expected.size(), protocols.length);
    }

    /**
     * Assert cipher suites match the default list in content and priority order.
     */
    public static void assertDefaultCipherSuites(String[] cipherSuites) {
        assertValidCipherSuites(CIPHER_SUITES, cipherSuites);
        assertEquals(CIPHER_SUITES_DEFAULT, Arrays.asList(cipherSuites));
    }
}
