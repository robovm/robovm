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
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Implements the subset of the JDK Signature interface needed for
 * signature verification using OpenSSL.
 */
public class OpenSSLSignature extends Signature {
    private static enum EngineType {
        RSA, DSA,
    };

    /**
     * Holds a pointer to the native message digest context.
     */
    private int ctx;

    /**
     * The current OpenSSL key we're operating on.
     */
    private OpenSSLKey key;

    /**
     * Holds the type of the Java algorithm.
     */
    private final EngineType engineType;

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
     * @param algorithm OpenSSL name of the algorithm, e.g. "RSA-SHA1".
     */
    private OpenSSLSignature(String algorithm, EngineType engineType)
            throws NoSuchAlgorithmException {
        super(algorithm);

        // We don't support MD2
        if ("RSA-MD2".equals(algorithm)) {
            throw new NoSuchAlgorithmException(algorithm);
        }

        this.engineType = engineType;
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
            if (ctx == 0) {
                try {
                    ctx = NativeCrypto.EVP_SignInit(evpAlgorithm);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            NativeCrypto.EVP_SignUpdate(ctx, input, offset, len);
        } else {
            if (ctx == 0) {
                try {
                    ctx = NativeCrypto.EVP_VerifyInit(evpAlgorithm);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            NativeCrypto.EVP_VerifyUpdate(ctx, input, offset, len);
        }
    }

    @Override
    protected Object engineGetParameter(String param) throws InvalidParameterException {
        return null;
    }

    @Override
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        destroyContextIfExists();

        if (privateKey instanceof OpenSSLDSAPrivateKey) {
            if (engineType != EngineType.DSA) {
                throw new InvalidKeyException("Signature not initialized as DSA");
            }

            OpenSSLDSAPrivateKey dsaPrivateKey = (OpenSSLDSAPrivateKey) privateKey;
            key = dsaPrivateKey.getOpenSSLKey();
        } else if (privateKey instanceof DSAPrivateKey) {
            if (engineType != EngineType.DSA) {
                throw new InvalidKeyException("Signature not initialized as DSA");
            }

            DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) privateKey;
            key = OpenSSLDSAPrivateKey.getInstance(dsaPrivateKey);
        } else if (privateKey instanceof OpenSSLRSAPrivateKey) {
            if (engineType != EngineType.RSA) {
                throw new InvalidKeyException("Signature not initialized as RSA");
            }

            OpenSSLRSAPrivateKey rsaPrivateKey = (OpenSSLRSAPrivateKey) privateKey;
            key = rsaPrivateKey.getOpenSSLKey();
        } else if (privateKey instanceof RSAPrivateCrtKey) {
            if (engineType != EngineType.RSA) {
                throw new InvalidKeyException("Signature not initialized as RSA");
            }

            RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey) privateKey;
            key = OpenSSLRSAPrivateCrtKey.getInstance(rsaPrivateKey);
        } else if (privateKey instanceof RSAPrivateKey) {
            if (engineType != EngineType.RSA) {
                throw new InvalidKeyException("Signature not initialized as RSA");
            }

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
            key = OpenSSLRSAPrivateKey.getInstance(rsaPrivateKey);
        } else {
            throw new InvalidKeyException("Need DSA or RSA private key");
        }
    }

    @Override
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        // If we had an existing context, destroy it first.
        destroyContextIfExists();

        if (publicKey instanceof OpenSSLDSAPublicKey) {
            if (engineType != EngineType.DSA) {
                throw new InvalidKeyException("Signature not initialized as DSA");
            }

            OpenSSLDSAPublicKey dsaPublicKey = (OpenSSLDSAPublicKey) publicKey;
            key = dsaPublicKey.getOpenSSLKey();
        } else if (publicKey instanceof DSAPublicKey) {
            if (engineType != EngineType.DSA) {
                throw new InvalidKeyException("Signature not initialized as DSA");
            }

            DSAPublicKey dsaPublicKey = (DSAPublicKey) publicKey;
            key = OpenSSLDSAPublicKey.getInstance(dsaPublicKey);
        } else if (publicKey instanceof OpenSSLRSAPublicKey) {
            if (engineType != EngineType.RSA) {
                throw new InvalidKeyException("Signature not initialized as RSA");
            }

            OpenSSLRSAPublicKey rsaPublicKey = (OpenSSLRSAPublicKey) publicKey;
            key = rsaPublicKey.getOpenSSLKey();
        } else if (publicKey instanceof RSAPublicKey) {
            if (engineType != EngineType.RSA) {
                throw new InvalidKeyException("Signature not initialized as RSA");
            }

            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            key = OpenSSLRSAPublicKey.getInstance(rsaPublicKey);
        } else {
            throw new InvalidKeyException("Need DSA or RSA public key");
        }
    }

    @Override
    protected void engineSetParameter(String param, Object value) throws InvalidParameterException {
    }

    @Override
    protected byte[] engineSign() throws SignatureException {
        if (key == null) {
            // This can't actually happen, but you never know...
            throw new SignatureException("Need DSA or RSA private key");
        }

        try {
            byte[] buffer = new byte[NativeCrypto.EVP_PKEY_size(key.getPkeyContext())];
            int bytesWritten = NativeCrypto.EVP_SignFinal(ctx, buffer, 0, key.getPkeyContext());

            byte[] signature = new byte[bytesWritten];
            System.arraycopy(buffer, 0, signature, 0, bytesWritten);

            return signature;
        } catch (Exception ex) {
            throw new SignatureException(ex);
        } finally {
            /*
             * Java expects the digest context to be reset completely after sign
             * calls.
             */
            destroyContextIfExists();
        }
    }

    @Override
    protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
        if (key == null) {
            // This can't actually happen, but you never know...
            throw new SignatureException("Need DSA or RSA public key");
        }

        try {
            int result = NativeCrypto.EVP_VerifyFinal(ctx, sigBytes, 0, sigBytes.length,
                    key.getPkeyContext());
            return result == 1;
        } catch (Exception ex) {
            throw new SignatureException(ex);
        } finally {
            /*
             * Java expects the digest context to be reset completely after
             * verify calls.
             */
            destroyContextIfExists();
        }
    }

    private void destroyContextIfExists() {
        if (ctx != 0) {
            NativeCrypto.EVP_MD_CTX_destroy(ctx);
            ctx = 0;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (ctx != 0) {
                NativeCrypto.EVP_MD_CTX_destroy(ctx);
            }
        } finally {
            super.finalize();
        }
    }

    public static final class MD5RSA extends OpenSSLSignature {
        public MD5RSA() throws NoSuchAlgorithmException {
            super("RSA-MD5", EngineType.RSA);
        }
    }
    public static final class SHA1RSA extends OpenSSLSignature {
        public SHA1RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA1", EngineType.RSA);
        }
    }
    public static final class SHA256RSA extends OpenSSLSignature {
        public SHA256RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA256", EngineType.RSA);
        }
    }
    public static final class SHA384RSA extends OpenSSLSignature {
        public SHA384RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA384", EngineType.RSA);
        }
    }
    public static final class SHA512RSA extends OpenSSLSignature {
        public SHA512RSA() throws NoSuchAlgorithmException {
            super("RSA-SHA512", EngineType.RSA);
        }
    }
    public static final class SHA1DSA extends OpenSSLSignature {
        public SHA1DSA() throws NoSuchAlgorithmException {
            super("DSA-SHA1", EngineType.DSA);
        }
    }
}

