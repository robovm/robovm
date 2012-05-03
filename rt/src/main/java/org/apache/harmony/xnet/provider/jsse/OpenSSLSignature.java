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

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Implements the subset of the JDK Signature interface needed for
 * signature verification using OpenSSL.
 */
public class OpenSSLSignature extends Signature {

    private static Map<String,Class<? extends OpenSSLSignature>> jdkToOpenSsl
            = new HashMap<String,Class<? extends OpenSSLSignature>>();
    private static void register(String algorithm, Class implementation) {
        jdkToOpenSsl.put(algorithm.toUpperCase(Locale.US), implementation);
    }
    private static Class lookup(String algorithm) {
        return jdkToOpenSsl.get(algorithm.toUpperCase(Locale.US));
    }

    static {
        // TODO Finish OpenSSLSignature implementation and move
        // registration information to the OpenSSLProvider
        register("MD5WithRSAEncryption", MD5RSA.class);
        register("MD5WithRSA", MD5RSA.class);
        register("MD5/RSA", MD5RSA.class);
        register("1.2.840.113549.1.1.4", MD5RSA.class);
        register("1.2.840.113549.2.5with1.2.840.113549.1.1.1", MD5RSA.class);

        register("SHA1WithRSAEncryption", SHA1RSA.class);
        register("SHA1WithRSA", SHA1RSA.class);
        register("SHA1/RSA", SHA1RSA.class);
        register("SHA-1/RSA", SHA1RSA.class);
        register("1.2.840.113549.1.1.5", SHA1RSA.class);
        register("1.3.14.3.2.26with1.2.840.113549.1.1.1", SHA1RSA.class);
        register("1.3.14.3.2.26with1.2.840.113549.1.1.5", SHA1RSA.class);
        register("1.3.14.3.2.29", SHA1RSA.class);

        register("SHA256WithRSAEncryption", SHA256RSA.class);
        register("SHA256WithRSA", SHA256RSA.class);
        register("1.2.840.113549.1.1.11", SHA256RSA.class);

        register("SHA384WithRSAEncryption", SHA384RSA.class);
        register("SHA384WithRSA", SHA384RSA.class);
        register("1.2.840.113549.1.1.12", SHA384RSA.class);

        register("SHA512WithRSAEncryption", SHA512RSA.class);
        register("SHA512WithRSA", SHA512RSA.class);
        register("1.2.840.113549.1.1.13", SHA512RSA.class);

        register("SHA1withDSA", SHA1DSA.class);
        register("SHA/DSA", SHA1DSA.class);
        register("DSA", SHA1DSA.class);
        register("1.3.14.3.2.26with1.2.840.10040.4.1", SHA1DSA.class);
        register("1.3.14.3.2.26with1.2.840.10040.4.3", SHA1DSA.class);
        register("DSAWithSHA1", SHA1DSA.class);
        register("1.2.840.10040.4.3", SHA1DSA.class);
    }

    /**
     * Holds a pointer to the native message digest context.
     */
    private int ctx;

    /**
     * Holds a pointer to the native DSA key.
     */
    private int dsa;

    /**
     * Holds a pointer to the native RSA key.
     */
    private int rsa;

    /**
     * Holds the OpenSSL name of the algorithm (lower case, no dashes).
     */
    private final String evpAlgorithm;

    /**
     * Holds a dummy buffer for writing single bytes to the digest.
     */
    private final byte[] singleByte = new byte[1];

    /**
     * Creates a new OpenSSLSignature instance for the given algorithm name.
     *
     * @param algorithm The name of the algorithm, e.g. "SHA1WithRSA".
     *
     * @return The new OpenSSLSignature instance.
     *
     * @throws RuntimeException In case of problems.
     */
    public static OpenSSLSignature getInstance(String algorithm) throws NoSuchAlgorithmException {
        // System.out.println("getInstance() invoked with " + algorithm);

        Class <? extends OpenSSLSignature> clazz = lookup(algorithm);
        if (clazz == null) {
            throw new NoSuchAlgorithmException(algorithm);
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new NoSuchAlgorithmException(algorithm, e);
        } catch (IllegalAccessException e) {
            throw new NoSuchAlgorithmException(algorithm, e);
        }
    }

    /**
     * Creates a new OpenSSLSignature instance for the given algorithm name.
     *
     * @param algorithm OpenSSL name of the algorithm, e.g. "RSA-SHA1".
     */
    private OpenSSLSignature(String algorithm) throws NoSuchAlgorithmException {
        super(algorithm);

        // We don't support MD2
        if ("RSA-MD2".equals(algorithm)) {
            throw new NoSuchAlgorithmException(algorithm);
        }

        this.evpAlgorithm = algorithm;
    }

    @Override
    protected void engineUpdate(byte input) {
        singleByte[0] = input;
        engineUpdate(singleByte, 0, 1);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        if (state == SIGN) {
            throw new UnsupportedOperationException();
        } else {
            NativeCrypto.EVP_VerifyUpdate(ctx, input, offset, len);
        }
    }

    @Override
    protected Object engineGetParameter(String param) throws InvalidParameterException {
        return null;
    }

    @Override
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        // System.out.println("engineInitVerify() invoked with "
        //                    + publicKey.getClass().getCanonicalName());

        if (publicKey instanceof DSAPublicKey) {
            try {
                DSAPublicKey dsaPublicKey = (DSAPublicKey)publicKey;
                DSAParams dsaParams = dsaPublicKey.getParams();
                dsa = NativeCrypto.EVP_PKEY_new_DSA(dsaParams.getP().toByteArray(),
                        dsaParams.getQ().toByteArray(), dsaParams.getG().toByteArray(),
                        dsaPublicKey.getY().toByteArray(), null);

            } catch (Exception e) {
                throw new InvalidKeyException(e);
            }
        } else if (publicKey instanceof RSAPublicKey) {
            try {
                RSAPublicKey rsaPublicKey = (RSAPublicKey)publicKey;
                rsa = NativeCrypto.EVP_PKEY_new_RSA(rsaPublicKey.getModulus().toByteArray(),
                        rsaPublicKey.getPublicExponent().toByteArray(), null, null, null);

            } catch (Exception e) {
                throw new InvalidKeyException(e);
            }
        } else {
            throw new InvalidKeyException("Need DSA or RSA public key");
        }

        try {
            ctx = NativeCrypto.EVP_VerifyInit(evpAlgorithm);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void engineSetParameter(String param, Object value) throws InvalidParameterException {
    }

    @Override
    protected byte[] engineSign() throws SignatureException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
        int handle = (rsa != 0) ? rsa : dsa;

        if (handle == 0) {
            // This can't actually happen, but you never know...
            throw new SignatureException("Need DSA or RSA public key");
        }

        try {
            int result = NativeCrypto.EVP_VerifyFinal(ctx, sigBytes, 0, sigBytes.length, handle);
            return result == 1;
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }

    }

    @Override protected void finalize() throws Throwable {
        try {
            if (dsa != 0) {
                NativeCrypto.EVP_PKEY_free(dsa);
            }

            if (rsa != 0) {
                NativeCrypto.EVP_PKEY_free(rsa);
            }

            if (ctx != 0) {
                NativeCrypto.EVP_MD_CTX_destroy(ctx);
            }
        } finally {
            super.finalize();
        }
    }

    public static final class MD5RSA extends OpenSSLSignature {
        public MD5RSA() throws NoSuchAlgorithmException {
            super("RSA-MD5");
        }
    }
    public static final class SHA1RSA extends OpenSSLSignature {
        public SHA1RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA1");
        }
    }
    public static final class SHA256RSA extends OpenSSLSignature {
        public SHA256RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA256");
        }
    }
    public static final class SHA384RSA extends OpenSSLSignature {
        public SHA384RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA384");
        }
    }
    public static final class SHA512RSA extends OpenSSLSignature {
        public SHA512RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA512");
        }
    }
    public static final class SHA1DSA extends OpenSSLSignature {
        public SHA1DSA() throws NoSuchAlgorithmException {
            super("DSA-SHA1");
        }
    }
}

