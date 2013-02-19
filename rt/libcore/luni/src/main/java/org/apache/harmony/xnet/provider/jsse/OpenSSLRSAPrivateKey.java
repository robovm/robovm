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

package org.apache.harmony.xnet.provider.jsse;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

public class OpenSSLRSAPrivateKey implements RSAPrivateKey {
    private static final long serialVersionUID = 4872170254439578735L;

    private final OpenSSLKey key;

    private boolean fetchedParams;

    private BigInteger modulus;

    private BigInteger privateExponent;

    OpenSSLRSAPrivateKey(OpenSSLKey key) {
        this.key = key;
    }

    OpenSSLRSAPrivateKey(OpenSSLKey key, byte[][] params) {
        this(key);
        readParams(params);
        fetchedParams = true;
    }

    final OpenSSLKey getOpenSSLKey() {
        return key;
    }

    public OpenSSLRSAPrivateKey(RSAPrivateKeySpec rsaKeySpec) throws InvalidKeySpecException {
        this(init(rsaKeySpec));
    }

    private static OpenSSLKey init(RSAPrivateKeySpec rsaKeySpec) throws InvalidKeySpecException {
        final BigInteger modulus = rsaKeySpec.getModulus();
        final BigInteger privateExponent = rsaKeySpec.getPrivateExponent();

        if (modulus == null) {
            throw new InvalidKeySpecException("modulus == null");
        } else if (privateExponent == null) {
            throw new InvalidKeySpecException("privateExponent == null");
        }

        try {
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(
                    modulus.toByteArray(),
                    null,
                    privateExponent.toByteArray(),
                    null,
                    null,
                    null,
                    null,
                    null));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }
    }

    static OpenSSLRSAPrivateKey getInstance(OpenSSLKey key) {
      byte[][] params = NativeCrypto.get_RSA_private_params(key.getPkeyContext());
      if (params[1] != null) {
          return new OpenSSLRSAPrivateCrtKey(key, params);
      }
      return new OpenSSLRSAPrivateKey(key, params);
    }

    static OpenSSLKey getInstance(RSAPrivateKey rsaPrivateKey) throws InvalidKeyException {
        final BigInteger modulus = rsaPrivateKey.getModulus();
        final BigInteger privateExponent = rsaPrivateKey.getPrivateExponent();

        if (modulus == null) {
            throw new InvalidKeyException("modulus == null");
        } else if (privateExponent == null) {
            throw new InvalidKeyException("privateExponent == null");
        }

        try {
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(
                    modulus.toByteArray(),
                    null,
                    privateExponent.toByteArray(),
                    null,
                    null,
                    null,
                    null,
                    null));
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    synchronized final void ensureReadParams() {
        if (fetchedParams) {
            return;
        }
        readParams(NativeCrypto.get_RSA_private_params(key.getPkeyContext()));
        fetchedParams = true;
    }

    void readParams(byte[][] params) {
        if (params[0] == null) {
            throw new NullPointerException("modulus == null");
        } else if (params[2] == null && !key.isEngineBased()) {
            throw new NullPointerException("privateExponent == null");
        }

        modulus = new BigInteger(params[0]);

        // ENGINE-based keys are not guaranteed to have a private exponent.
        if (params[2] != null) {
            privateExponent = new BigInteger(params[2]);
        }
    }

    @Override
    public final BigInteger getPrivateExponent() {
        ensureReadParams();
        return privateExponent;
    }

    @Override
    public final BigInteger getModulus() {
        ensureReadParams();
        return modulus;
    }

    @Override
    public final byte[] getEncoded() {
        /*
         * If we're using an OpenSSL ENGINE, there's no guarantee we can export
         * the key. Returning {@code null} tells the caller that there's no
         * encoded format.
         */
        if (key.isEngineBased()) {
            return null;
        }

        return NativeCrypto.i2d_PKCS8_PRIV_KEY_INFO(key.getPkeyContext());
    }

    public final String getFormat() {
        /*
         * If we're using an OpenSSL ENGINE, there's no guarantee we can export
         * the key. Returning {@code null} tells the caller that there's no
         * encoded format.
         */
        if (key.isEngineBased()) {
            return null;
        }

        return "PKCS#8";
    }

    @Override
    public final String getAlgorithm() {
        return "RSA";
    }

    public int getPkeyContext() {
        return key.getPkeyContext();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OpenSSLRSAPrivateKey) {
            OpenSSLRSAPrivateKey other = (OpenSSLRSAPrivateKey) o;

            /*
             * We can shortcut the true case, but it still may be equivalent but
             * different copies.
             */
            if (key.equals(other.getOpenSSLKey())) {
                return true;
            }
        }

        if (o instanceof RSAPrivateKey) {
            ensureReadParams();
            RSAPrivateKey other = (RSAPrivateKey) o;

            return modulus.equals(other.getModulus())
                    && privateExponent.equals(other.getPrivateExponent());
        }

        return false;
    }

    @Override
    public int hashCode() {
        ensureReadParams();
        int hash = 1;

        hash = hash * 3 + modulus.hashCode();
        if (privateExponent != null) {
            hash = hash * 7 + privateExponent.hashCode();
        }

        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpenSSLRSAPrivateKey{");

        if (key.isEngineBased()) {
            sb.append("key=");
            sb.append(key);
            sb.append('}');
            return sb.toString();
        }

        ensureReadParams();
        sb.append("modulus=");
        sb.append(modulus.toString(16));
        sb.append(',');

        sb.append("privateExponent=");
        sb.append(privateExponent.toString(16));
        sb.append(',');

        return sb.toString();
    }
}
