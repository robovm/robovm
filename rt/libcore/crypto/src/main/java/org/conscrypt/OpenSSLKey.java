/*
 * Copyright (C) 2012 The Android Open Source Project
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

package org.conscrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.SecretKey;

public class OpenSSLKey {
    private final long ctx;

    private final OpenSSLEngine engine;

    private final String alias;

    public OpenSSLKey(long ctx) {
        this.ctx = ctx;
        engine = null;
        alias = null;
    }

    public OpenSSLKey(long ctx, OpenSSLEngine engine, String alias) {
        this.ctx = ctx;
        this.engine = engine;
        this.alias = alias;
    }

    /**
     * Returns the raw pointer to the EVP_PKEY context for use in JNI calls. The
     * life cycle of this native pointer is managed by the {@code OpenSSLKey}
     * instance and must not be destroyed or freed by users of this API.
     */
    public long getPkeyContext() {
        return ctx;
    }

    OpenSSLEngine getEngine() {
        return engine;
    }

    boolean isEngineBased() {
        return engine != null;
    }

    public String getAlias() {
        return alias;
    }

    public static OpenSSLKey fromPrivateKey(PrivateKey key) throws InvalidKeyException {
        if (key instanceof OpenSSLKeyHolder) {
            return ((OpenSSLKeyHolder) key).getOpenSSLKey();
        }

        if ("PKCS#8".equals(key.getFormat())) {
            return new OpenSSLKey(NativeCrypto.d2i_PKCS8_PRIV_KEY_INFO(key.getEncoded()));
        } else {
            throw new InvalidKeyException("Unknown key format " + key.getFormat());
        }
    }

    public PublicKey getPublicKey() throws NoSuchAlgorithmException {
        switch (NativeCrypto.EVP_PKEY_type(ctx)) {
            case NativeCrypto.EVP_PKEY_RSA:
                return new OpenSSLRSAPublicKey(this);
            case NativeCrypto.EVP_PKEY_DSA:
                return new OpenSSLDSAPublicKey(this);
            case NativeCrypto.EVP_PKEY_EC:
                return new OpenSSLECPublicKey(this);
            default:
                throw new NoSuchAlgorithmException("unknown PKEY type");
        }
    }

    static PublicKey getPublicKey(X509EncodedKeySpec keySpec, int type)
            throws InvalidKeySpecException {
        X509EncodedKeySpec x509KeySpec = (X509EncodedKeySpec) keySpec;

        final OpenSSLKey key;
        try {
            key = new OpenSSLKey(NativeCrypto.d2i_PUBKEY(x509KeySpec.getEncoded()));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }

        if (NativeCrypto.EVP_PKEY_type(key.getPkeyContext()) != type) {
            throw new InvalidKeySpecException("Unexpected key type");
        }

        try {
            return key.getPublicKey();
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidKeySpecException(e);
        }
    }

    public PrivateKey getPrivateKey() throws NoSuchAlgorithmException {
        switch (NativeCrypto.EVP_PKEY_type(ctx)) {
            case NativeCrypto.EVP_PKEY_RSA:
                return new OpenSSLRSAPrivateKey(this);
            case NativeCrypto.EVP_PKEY_DSA:
                return new OpenSSLDSAPrivateKey(this);
            case NativeCrypto.EVP_PKEY_EC:
                return new OpenSSLECPrivateKey(this);
            default:
                throw new NoSuchAlgorithmException("unknown PKEY type");
        }
    }

    static PrivateKey getPrivateKey(PKCS8EncodedKeySpec keySpec, int type)
            throws InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8KeySpec = (PKCS8EncodedKeySpec) keySpec;

        final OpenSSLKey key;
        try {
            key = new OpenSSLKey(NativeCrypto.d2i_PKCS8_PRIV_KEY_INFO(pkcs8KeySpec.getEncoded()));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }

        if (NativeCrypto.EVP_PKEY_type(key.getPkeyContext()) != type) {
            throw new InvalidKeySpecException("Unexpected key type");
        }

        try {
            return key.getPrivateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidKeySpecException(e);
        }
    }

    public SecretKey getSecretKey(String algorithm) throws NoSuchAlgorithmException {
        switch (NativeCrypto.EVP_PKEY_type(ctx)) {
            case NativeCrypto.EVP_PKEY_HMAC:
            case NativeCrypto.EVP_PKEY_CMAC:
                return new OpenSSLSecretKey(algorithm, this);
            default:
                throw new NoSuchAlgorithmException("unknown PKEY type");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (ctx != 0) {
                NativeCrypto.EVP_PKEY_free(ctx);
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof OpenSSLKey)) {
            return false;
        }

        OpenSSLKey other = (OpenSSLKey) o;
        if (ctx == other.getPkeyContext()) {
            return true;
        }

        /*
         * ENGINE-based keys must be checked in a special way.
         */
        if (engine == null) {
            if (other.getEngine() != null) {
                return false;
            }
        } else if (!engine.equals(other.getEngine())) {
            return false;
        } else {
            if (alias != null) {
                return alias.equals(other.getAlias());
            } else if (other.getAlias() != null) {
                return false;
            }
        }

        return NativeCrypto.EVP_PKEY_cmp(ctx, other.getPkeyContext()) == 1;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + (int) ctx;
        hash = hash * 31 + (int) (engine == null ? 0 : engine.getEngineContext());
        return hash;
    }
}
