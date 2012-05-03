/*
 * Copyright (C) 2008 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLException;
import javax.security.auth.x500.X500Principal;
import libcore.io.Memory;

/**
 * Provides the Java side of our JNI glue for OpenSSL.
 */
public final class NativeCrypto {

    // --- OpenSSL library initialization --------------------------------------
    static {
        clinit();
    }

    private native static void clinit();

    // --- DSA/RSA public/private key handling functions -----------------------

    public static native int EVP_PKEY_new_DSA(byte[] p, byte[] q, byte[] g,
                                              byte[] priv_key, byte[] pub_key);

    public static native int EVP_PKEY_new_RSA(byte[] n, byte[] e, byte[] d, byte[] p, byte[] q);

    public static native void EVP_PKEY_free(int pkey);

    // --- Message digest functions --------------

    public static native int EVP_get_digestbyname(String name);

    public static native int EVP_MD_size(int evp_md);

    public static native int EVP_MD_block_size(int evp_md);

    // --- Message digest context functions --------------

    public static native void EVP_MD_CTX_destroy(int ctx);

    public static native int EVP_MD_CTX_copy(int ctx);

    // --- Digest handling functions -------------------------------------------

    public static native int EVP_DigestInit(int evp_md);

    public static native void EVP_DigestUpdate(int ctx, byte[] buffer, int offset, int length);

    public static native int EVP_DigestFinal(int ctx, byte[] hash, int offset);

    // --- Signature handling functions ----------------------------------------

    public static native int EVP_VerifyInit(String algorithm);

    public static native void EVP_VerifyUpdate(int ctx, byte[] buffer,
                                               int offset, int length);

    public static native int EVP_VerifyFinal(int ctx, byte[] signature,
                                             int offset, int length, int key);

    // --- RAND seeding --------------------------------------------------------

    public static final int RAND_SEED_LENGTH_IN_BYTES = 1024;

    public static native void RAND_seed(byte[] seed);

    public static native int RAND_load_file(String filename, long max_bytes);

    // --- X509_NAME -----------------------------------------------------------

    public static int X509_NAME_hash(X500Principal principal) {
        return X509_NAME_hash(principal, "SHA1");
    }
    public static int X509_NAME_hash_old(X500Principal principal) {
        return X509_NAME_hash(principal, "MD5");
    }
    private static int X509_NAME_hash(X500Principal principal, String algorithm) {
        try {
            byte[] digest = MessageDigest.getInstance(algorithm).digest(principal.getEncoded());
            return Memory.peekInt(digest, 0, ByteOrder.LITTLE_ENDIAN);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    // --- SSL handling --------------------------------------------------------

    private static final String SUPPORTED_PROTOCOL_SSLV3 = "SSLv3";
    private static final String SUPPORTED_PROTOCOL_TLSV1 = "TLSv1";

    public static final Map<String, String> OPENSSL_TO_STANDARD_CIPHER_SUITES
            = new HashMap<String, String>();
    public static final Map<String, String> STANDARD_TO_OPENSSL_CIPHER_SUITES
            = new LinkedHashMap<String, String>();

    private static void add(String standard, String openssl) {
        OPENSSL_TO_STANDARD_CIPHER_SUITES.put(openssl, standard);
        STANDARD_TO_OPENSSL_CIPHER_SUITES.put(standard, openssl);
    }

    /**
     * TLS_EMPTY_RENEGOTIATION_INFO_SCSV is RFC 5746's renegotiation
     * indication signaling cipher suite value. It is not a real
     * cipher suite. It is just an indication in the default and
     * supported cipher suite lists indicates that the implementation
     * supports secure renegotiation.
     *
     * In the RI, its presence means that the SCSV is sent in the
     * cipher suite list to indicate secure renegotiation support and
     * its absense means to send an empty TLS renegotiation info
     * extension instead.
     *
     * However, OpenSSL doesn't provide an API to give this level of
     * control, instead always sending the SCSV and always including
     * the empty renegotiation info if TLS is used (as opposed to
     * SSL). So we simply allow TLS_EMPTY_RENEGOTIATION_INFO_SCSV to
     * be passed for compatibility as to provide the hint that we
     * support secure renegotiation.
     */
    public static final String TLS_EMPTY_RENEGOTIATION_INFO_SCSV
            = "TLS_EMPTY_RENEGOTIATION_INFO_SCSV";

    static {
        // Note these are added in priority order
        add("SSL_RSA_WITH_RC4_128_MD5",              "RC4-MD5");
        add("SSL_RSA_WITH_RC4_128_SHA",              "RC4-SHA");
        add("TLS_RSA_WITH_AES_128_CBC_SHA",          "AES128-SHA");
        add("TLS_RSA_WITH_AES_256_CBC_SHA",          "AES256-SHA");
        add("TLS_ECDH_ECDSA_WITH_RC4_128_SHA",       "ECDH-ECDSA-RC4-SHA");
        add("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",   "ECDH-ECDSA-AES128-SHA");
        add("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",   "ECDH-ECDSA-AES256-SHA");
        add("TLS_ECDH_RSA_WITH_RC4_128_SHA",         "ECDH-RSA-RC4-SHA");
        add("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",     "ECDH-RSA-AES128-SHA");
        add("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",     "ECDH-RSA-AES256-SHA");
        add("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",      "ECDHE-ECDSA-RC4-SHA");
        add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",  "ECDHE-ECDSA-AES128-SHA");
        add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",  "ECDHE-ECDSA-AES256-SHA");
        add("TLS_ECDHE_RSA_WITH_RC4_128_SHA",        "ECDHE-RSA-RC4-SHA");
        add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",    "ECDHE-RSA-AES128-SHA");
        add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",    "ECDHE-RSA-AES256-SHA");
        add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA",      "DHE-RSA-AES128-SHA");
        add("TLS_DHE_RSA_WITH_AES_256_CBC_SHA",      "DHE-RSA-AES256-SHA");
        add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA",      "DHE-DSS-AES128-SHA");
        add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA",      "DHE-DSS-AES256-SHA");
        add("SSL_RSA_WITH_3DES_EDE_CBC_SHA",         "DES-CBC3-SHA");
        add("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",  "ECDH-ECDSA-DES-CBC3-SHA");
        add("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",    "ECDH-RSA-DES-CBC3-SHA");
        add("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "ECDHE-ECDSA-DES-CBC3-SHA");
        add("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",   "ECDHE-RSA-DES-CBC3-SHA");
        add("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",     "EDH-RSA-DES-CBC3-SHA");
        add("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",     "EDH-DSS-DES-CBC3-SHA");
        add("SSL_RSA_WITH_DES_CBC_SHA",              "DES-CBC-SHA");
        add("SSL_DHE_RSA_WITH_DES_CBC_SHA",          "EDH-RSA-DES-CBC-SHA");
        add("SSL_DHE_DSS_WITH_DES_CBC_SHA",          "EDH-DSS-DES-CBC-SHA");
        add("SSL_RSA_EXPORT_WITH_RC4_40_MD5",        "EXP-RC4-MD5");
        add("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA",     "EXP-DES-CBC-SHA");
        add("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", "EXP-EDH-RSA-DES-CBC-SHA");
        add("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", "EXP-EDH-DSS-DES-CBC-SHA");
        add("SSL_RSA_WITH_NULL_MD5",                 "NULL-MD5");
        add("SSL_RSA_WITH_NULL_SHA",                 "NULL-SHA");
        add("TLS_ECDH_ECDSA_WITH_NULL_SHA",          "ECDH-ECDSA-NULL-SHA");
        add("TLS_ECDH_RSA_WITH_NULL_SHA",            "ECDH-RSA-NULL-SHA");
        add("TLS_ECDHE_ECDSA_WITH_NULL_SHA",         "ECDHE-ECDSA-NULL-SHA");
        add("TLS_ECDHE_RSA_WITH_NULL_SHA",           "ECDHE-RSA-NULL-SHA");
        add("SSL_DH_anon_WITH_RC4_128_MD5",          "ADH-RC4-MD5");
        add("TLS_DH_anon_WITH_AES_128_CBC_SHA",      "ADH-AES128-SHA");
        add("TLS_DH_anon_WITH_AES_256_CBC_SHA",      "ADH-AES256-SHA");
        add("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",     "ADH-DES-CBC3-SHA");
        add("SSL_DH_anon_WITH_DES_CBC_SHA",          "ADH-DES-CBC-SHA");
        add("TLS_ECDH_anon_WITH_RC4_128_SHA",        "AECDH-RC4-SHA");
        add("TLS_ECDH_anon_WITH_AES_128_CBC_SHA",    "AECDH-AES128-SHA");
        add("TLS_ECDH_anon_WITH_AES_256_CBC_SHA",    "AECDH-AES256-SHA");
        add("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA",   "AECDH-DES-CBC3-SHA");
        add("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",    "EXP-ADH-RC4-MD5");
        add("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", "EXP-ADH-DES-CBC-SHA");
        add("TLS_ECDH_anon_WITH_NULL_SHA",           "AECDH-NULL-SHA");

        // No Kerberos in Android
        // add("TLS_KRB5_WITH_RC4_128_SHA",           "KRB5-RC4-SHA");
        // add("TLS_KRB5_WITH_RC4_128_MD5",           "KRB5-RC4-MD5");
        // add("TLS_KRB5_WITH_3DES_EDE_CBC_SHA",      "KRB5-DES-CBC3-SHA");
        // add("TLS_KRB5_WITH_3DES_EDE_CBC_MD5",      "KRB5-DES-CBC3-MD5");
        // add("TLS_KRB5_WITH_DES_CBC_SHA",           "KRB5-DES-CBC-SHA");
        // add("TLS_KRB5_WITH_DES_CBC_MD5",           "KRB5-DES-CBC-MD5");
        // add("TLS_KRB5_EXPORT_WITH_RC4_40_SHA",     "EXP-KRB5-RC4-SHA");
        // add("TLS_KRB5_EXPORT_WITH_RC4_40_MD5",     "EXP-KRB5-RC4-MD5");
        // add("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", "EXP-KRB5-DES-CBC-SHA");
        // add("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", "EXP-KRB5-DES-CBC-MD5");

        // not implemented by either RI or OpenSSL
        // add("SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA", null);
        // add("SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA", null);

        // EXPORT1024 suites were never standardized but were widely implemented.
        // OpenSSL 0.9.8c and later have disabled TLS1_ALLOW_EXPERIMENTAL_CIPHERSUITES
        // add("SSL_RSA_EXPORT1024_WITH_DES_CBC_SHA", "EXP1024-DES-CBC-SHA");
        // add("SSL_RSA_EXPORT1024_WITH_RC4_56_SHA",  "EXP1024-RC4-SHA");

        // No RC2
        // add("SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5",  "EXP-RC2-CBC-MD5");
        // add("TLS_KRB5_EXPORT_WITH_RC2_CBC_40_SHA", "EXP-KRB5-RC2-CBC-SHA");
        // add("TLS_KRB5_EXPORT_WITH_RC2_CBC_40_MD5", "EXP-KRB5-RC2-CBC-MD5");

        // PSK is Private Shared Key - didn't exist in Froyo's openssl - no JSSE equivalent
        // add(null, "PSK-3DES-EDE-CBC-SHA");
        // add(null, "PSK-AES128-CBC-SHA");
        // add(null, "PSK-AES256-CBC-SHA");
        // add(null, "PSK-RC4-SHA");

        // Signaling Cipher Suite Value for secure renegotiation handled as special case.
        // add("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", null);
    }

    private static final String[] SUPPORTED_CIPHER_SUITES;
    static {
        int size = STANDARD_TO_OPENSSL_CIPHER_SUITES.size();
        SUPPORTED_CIPHER_SUITES = new String[size + 1];
        STANDARD_TO_OPENSSL_CIPHER_SUITES.keySet().toArray(SUPPORTED_CIPHER_SUITES);
        SUPPORTED_CIPHER_SUITES[size] = TLS_EMPTY_RENEGOTIATION_INFO_SCSV;
    }

    // SSL mode from ssl.h
    public static final long SSL_MODE_HANDSHAKE_CUTTHROUGH = 0x00000040L;

    // SSL options from ssl.h
    public static final long SSL_OP_NO_TICKET      = 0x00004000L;
    public static final long SSL_OP_NO_COMPRESSION = 0x00020000L;
    public static final long SSL_OP_NO_SSLv3       = 0x02000000L;
    public static final long SSL_OP_NO_TLSv1       = 0x04000000L;

    public static native int SSL_CTX_new();

    public static String[] getDefaultCipherSuites() {
        return new String[] {
            "SSL_RSA_WITH_RC4_128_MD5",
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
            TLS_EMPTY_RENEGOTIATION_INFO_SCSV
        };
    }

    public static String[] getSupportedCipherSuites() {
        return SUPPORTED_CIPHER_SUITES.clone();
    }

    public static native void SSL_CTX_free(int ssl_ctx);

    public static native int SSL_new(int ssl_ctx) throws SSLException;

    public static byte[][] encodeCertificates(Certificate[] certificates)
            throws CertificateEncodingException {
        byte[][] certificateBytes = new byte[certificates.length][];
        for (int i = 0; i < certificates.length; i++) {
            certificateBytes[i] = certificates[i].getEncoded();
        }
        return certificateBytes;
    }

    public static native void SSL_use_certificate(int ssl, byte[][] asn1DerEncodedCertificateChain);

    public static native void SSL_use_PrivateKey(int ssl, byte[] pkcs8EncodedPrivateKey);

    public static native void SSL_check_private_key(int ssl) throws SSLException;

    public static byte[][] encodeIssuerX509Principals(X509Certificate[] certificates)
            throws CertificateEncodingException {
        byte[][] principalBytes = new byte[certificates.length][];
        for (int i = 0; i < certificates.length; i++) {
            principalBytes[i] = certificates[i].getIssuerX500Principal().getEncoded();
        }
        return principalBytes;
    }

    public static native void SSL_set_client_CA_list(int ssl, byte[][] asn1DerEncodedX500Principals);

    public static native long SSL_get_mode(int ssl);

    public static native long SSL_set_mode(int ssl, long mode);

    public static native long SSL_clear_mode(int ssl, long mode);

    public static native long SSL_get_options(int ssl);

    public static native long SSL_set_options(int ssl, long options);

    public static native long SSL_clear_options(int ssl, long options);

    public static String[] getSupportedProtocols() {
        return new String[] { SUPPORTED_PROTOCOL_SSLV3, SUPPORTED_PROTOCOL_TLSV1 };
    }

    public static void setEnabledProtocols(int ssl, String[] protocols) {
        checkEnabledProtocols(protocols);
        // openssl uses negative logic letting you disable protocols.
        // so first, assume we need to set all (disable all) and clear none (enable none).
        // in the loop, selectively move bits from set to clear (from disable to enable)
        long optionsToSet = (SSL_OP_NO_SSLv3 | SSL_OP_NO_TLSv1);
        long optionsToClear = 0;
        for (int i = 0; i < protocols.length; i++) {
            String protocol = protocols[i];
            if (protocol.equals(SUPPORTED_PROTOCOL_SSLV3)) {
                optionsToSet &= ~SSL_OP_NO_SSLv3;
                optionsToClear |= SSL_OP_NO_SSLv3;
            } else if (protocol.equals(SUPPORTED_PROTOCOL_TLSV1)) {
                optionsToSet &= ~SSL_OP_NO_TLSv1;
                optionsToClear |= SSL_OP_NO_TLSv1;
            } else {
                // error checked by checkEnabledProtocols
                throw new IllegalStateException();
            }
        }

        SSL_set_options(ssl, optionsToSet);
        SSL_clear_options(ssl, optionsToClear);
    }

    public static String[] checkEnabledProtocols(String[] protocols) {
        if (protocols == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        for (int i = 0; i < protocols.length; i++) {
            String protocol = protocols[i];
            if (protocol == null) {
                throw new IllegalArgumentException("protocols[" + i + "] == null");
            }
            if ((!protocol.equals(SUPPORTED_PROTOCOL_SSLV3))
                    && (!protocol.equals(SUPPORTED_PROTOCOL_TLSV1))) {
                throw new IllegalArgumentException("protocol " + protocol
                                                   + " is not supported");
            }
        }
        return protocols;
    }

    public static native void SSL_set_cipher_lists(int ssl, String[] ciphers);

    public static void setEnabledCipherSuites(int ssl, String[] cipherSuites) {
        checkEnabledCipherSuites(cipherSuites);
        List<String> opensslSuites = new ArrayList<String>();
        for (int i = 0; i < cipherSuites.length; i++) {
            String cipherSuite = cipherSuites[i];
            if (cipherSuite.equals(TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {
                continue;
            }
            String openssl = STANDARD_TO_OPENSSL_CIPHER_SUITES.get(cipherSuite);
            String cs = (openssl == null) ? cipherSuite : openssl;
            opensslSuites.add(cs);
        }
        SSL_set_cipher_lists(ssl, opensslSuites.toArray(new String[opensslSuites.size()]));
    }

    public static String[] checkEnabledCipherSuites(String[] cipherSuites) {
        if (cipherSuites == null) {
            throw new IllegalArgumentException("cipherSuites == null");
        }
        // makes sure all suites are valid, throwing on error
        for (int i = 0; i < cipherSuites.length; i++) {
            String cipherSuite = cipherSuites[i];
            if (cipherSuite == null) {
                throw new IllegalArgumentException("cipherSuites[" + i + "] == null");
            }
            if (cipherSuite.equals(TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {
                continue;
            }
            if (STANDARD_TO_OPENSSL_CIPHER_SUITES.containsKey(cipherSuite)) {
                continue;
            }
            if (OPENSSL_TO_STANDARD_CIPHER_SUITES.containsKey(cipherSuite)) {
                // TODO log warning about using backward compatability
                continue;
            }
            throw new IllegalArgumentException("cipherSuite " + cipherSuite + " is not supported.");
        }
        return cipherSuites;
    }

    private static final String SUPPORTED_COMPRESSION_METHOD_ZLIB = "ZLIB";
    private static final String SUPPORTED_COMPRESSION_METHOD_NULL = "NULL";

    private static final String[] SUPPORTED_COMPRESSION_METHODS
            = { SUPPORTED_COMPRESSION_METHOD_ZLIB, SUPPORTED_COMPRESSION_METHOD_NULL };

    public static String[] getSupportedCompressionMethods() {
        return SUPPORTED_COMPRESSION_METHODS.clone();
    }

    public static final String[] getDefaultCompressionMethods() {
        return new String[] { SUPPORTED_COMPRESSION_METHOD_NULL };
    }

    public static String[] checkEnabledCompressionMethods(String[] methods) {
        if (methods == null) {
            throw new IllegalArgumentException("methods == null");
        }
        if (methods.length < 1
                && !methods[methods.length-1].equals(SUPPORTED_COMPRESSION_METHOD_NULL)) {
            throw new IllegalArgumentException("last method must be NULL");
        }
        for (int i = 0; i < methods.length; i++) {
            String method = methods[i];
            if (method == null) {
                throw new IllegalArgumentException("methods[" + i + "] == null");
            }
            if (!method.equals(SUPPORTED_COMPRESSION_METHOD_ZLIB)
                    && !method.equals(SUPPORTED_COMPRESSION_METHOD_NULL)) {
                throw new IllegalArgumentException("method " + method
                                                   + " is not supported");
            }
        }
        return methods;
    }

    public static void setEnabledCompressionMethods(int ssl, String[] methods) {
        checkEnabledCompressionMethods(methods);
        // openssl uses negative logic letting you disable compression.
        // so first, assume we need to set all (disable all) and clear none (enable none).
        // in the loop, selectively move bits from set to clear (from disable to enable)
        long optionsToSet = (SSL_OP_NO_COMPRESSION);
        long optionsToClear = 0;
        for (int i = 0; i < methods.length; i++) {
            String method = methods[i];
            if (method.equals(SUPPORTED_COMPRESSION_METHOD_NULL)) {
                // nothing to do to support NULL
            } else if (method.equals(SUPPORTED_COMPRESSION_METHOD_ZLIB)) {
                optionsToSet &= ~SSL_OP_NO_COMPRESSION;
                optionsToClear |= SSL_OP_NO_COMPRESSION;
            } else {
                // error checked by checkEnabledCompressionMethods
                throw new IllegalStateException();
            }
        }

        SSL_set_options(ssl, optionsToSet);
        SSL_clear_options(ssl, optionsToClear);
    }

    /*
     * See the OpenSSL ssl.h header file for more information.
     */
    public static final int SSL_VERIFY_NONE =                 0x00;
    public static final int SSL_VERIFY_PEER =                 0x01;
    public static final int SSL_VERIFY_FAIL_IF_NO_PEER_CERT = 0x02;

    public static native void SSL_set_verify(int sslNativePointer, int mode);

    public static native void SSL_set_session(int sslNativePointer, int sslSessionNativePointer)
        throws SSLException;

    public static native void SSL_set_session_creation_enabled(
            int sslNativePointer, boolean creationEnabled) throws SSLException;

    public static native void SSL_set_tlsext_host_name(int sslNativePointer, String hostname)
            throws SSLException;
    public static native String SSL_get_servername(int sslNativePointer);

    /**
     * Returns the sslSessionNativePointer of the negotiated session
     */
    public static native int SSL_do_handshake(int sslNativePointer,
                                              FileDescriptor fd,
                                              SSLHandshakeCallbacks shc,
                                              int timeout,
                                              boolean client_mode)
        throws SSLException, SocketTimeoutException, CertificateException;

    /**
     * Currently only intended for forcing renegotiation for testing.
     * Not used within OpenSSLSocketImpl.
     */
    public static native void SSL_renegotiate(int sslNativePointer) throws SSLException;

    /**
     * Returns the local ASN.1 DER encoded X509 certificates.
     */
    public static native byte[][] SSL_get_certificate(int sslNativePointer);

    /**
     * Returns the peer ASN.1 DER encoded X509 certificates.
     */
    public static native byte[][] SSL_get_peer_cert_chain(int sslNativePointer);

    /**
     * Reads with the native SSL_read function from the encrypted data stream
     * @return -1 if error or the end of the stream is reached.
     */
    public static native int SSL_read(int sslNativePointer,
                                      FileDescriptor fd,
                                      SSLHandshakeCallbacks shc,
                                      byte[] b, int off, int len, int timeout)
        throws IOException;

    /**
     * Writes with the native SSL_write function to the encrypted data stream.
     */
    public static native void SSL_write(int sslNativePointer,
                                        FileDescriptor fd,
                                        SSLHandshakeCallbacks shc,
                                        byte[] b, int off, int len)
        throws IOException;

    public static native void SSL_interrupt(int sslNativePointer);
    public static native void SSL_shutdown(int sslNativePointer,
                                           FileDescriptor fd,
                                           SSLHandshakeCallbacks shc) throws IOException;

    public static native void SSL_free(int sslNativePointer);

    public static native byte[] SSL_SESSION_session_id(int sslSessionNativePointer);

    public static native long SSL_SESSION_get_time(int sslSessionNativePointer);

    public static native String SSL_SESSION_get_version(int sslSessionNativePointer);

    public static native String SSL_SESSION_cipher(int sslSessionNativePointer);

    public static native String SSL_SESSION_compress_meth(int sslCtxNativePointer,
                                                          int sslSessionNativePointer);

    public static native void SSL_SESSION_free(int sslSessionNativePointer);

    public static native byte[] i2d_SSL_SESSION(int sslSessionNativePointer);

    public static native int d2i_SSL_SESSION(byte[] data);

    /**
     * A collection of callbacks from the native OpenSSL code that are
     * related to the SSL handshake initiated by SSL_do_handshake.
     */
    public interface SSLHandshakeCallbacks {
        /**
         * Verify that we trust the certificate chain is trusted.
         *
         * @param asn1DerEncodedCertificateChain A chain of ASN.1 DER encoded certificates
         * @param authMethod auth algorithm name
         *
         * @throws CertificateException if the certificate is untrusted
         */
        public void verifyCertificateChain(byte[][] asn1DerEncodedCertificateChain, String authMethod)
            throws CertificateException;

        /**
         * Called on an SSL client when the server requests (or
         * requires a certificate). The client can respond by using
         * SSL_use_certificate and SSL_use_PrivateKey to set a
         * certificate if has an appropriate one available, similar to
         * how the server provides its certificate.
         *
         * @param keyTypes key types supported by the server,
         * convertible to strings with #keyType
         * @param asn1DerEncodedX500Principals CAs known to the server
         */
        public void clientCertificateRequested(byte[] keyTypes,
                                               byte[][] asn1DerEncodedX500Principals)
            throws CertificateEncodingException, SSLException;

        /**
         * Called when SSL handshake is completed. Note that this can
         * be after SSL_do_handshake returns when handshake cutthrough
         * is enabled.
         */
        public void handshakeCompleted();
    }
}
