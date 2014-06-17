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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class OpenSSLRSAPublicKey implements RSAPublicKey, OpenSSLKeyHolder {
    private static final long serialVersionUID = 123125005824688292L;

    private transient OpenSSLKey key;

    private BigInteger publicExponent;

    private BigInteger modulus;

    private transient boolean fetchedParams;

    OpenSSLRSAPublicKey(OpenSSLKey key) {
        this.key = key;
    }

    @Override
    public OpenSSLKey getOpenSSLKey() {
        return key;
    }

    OpenSSLRSAPublicKey(RSAPublicKeySpec spec) throws InvalidKeySpecException {
        try {
            key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(
                    spec.getModulus().toByteArray(),
                    spec.getPublicExponent().toByteArray(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }
    }

    static OpenSSLKey getInstance(RSAPublicKey rsaPublicKey) throws InvalidKeyException {
        try {
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(
                    rsaPublicKey.getModulus().toByteArray(),
                    rsaPublicKey.getPublicExponent().toByteArray(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null));
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override
    public String getAlgorithm() {
        return "RSA";
    }

    @Override
    public String getFormat() {
        return "X.509";
    }

    @Override
    public byte[] getEncoded() {
        return NativeCrypto.i2d_PUBKEY(key.getPkeyContext());
    }

    private void ensureReadParams() {
        if (fetchedParams) {
            return;
        }

        byte[][] params = NativeCrypto.get_RSA_public_params(key.getPkeyContext());
        modulus = new BigInteger(params[0]);
        publicExponent = new BigInteger(params[1]);

        fetchedParams = true;
    }

    @Override
    public BigInteger getModulus() {
        ensureReadParams();
        return modulus;
    }

    @Override
    public BigInteger getPublicExponent() {
        ensureReadParams();
        return publicExponent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OpenSSLRSAPublicKey) {
            OpenSSLRSAPublicKey other = (OpenSSLRSAPublicKey) o;

            /*
             * We can shortcut the true case, but it still may be equivalent but
             * different copies.
             */
            if (key.equals(other.getOpenSSLKey())) {
                return true;
            }
        }

        if (!(o instanceof RSAPublicKey)) {
            return false;
        }

        ensureReadParams();

        RSAPublicKey other = (RSAPublicKey) o;
        return modulus.equals(other.getModulus())
                && publicExponent.equals(other.getPublicExponent());
    }

    @Override
    public int hashCode() {
        ensureReadParams();

        return modulus.hashCode() ^ publicExponent.hashCode();
    }

    @Override
    public String toString() {
        ensureReadParams();

        final StringBuilder sb = new StringBuilder("OpenSSLRSAPublicKey{");
        sb.append("modulus=");
        sb.append(modulus.toString(16));
        sb.append(',');
        sb.append("publicExponent=");
        sb.append(publicExponent.toString(16));
        sb.append('}');

        return sb.toString();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_RSA(
                modulus.toByteArray(),
                publicExponent.toByteArray(),
                null,
                null,
                null,
                null,
                null,
                null));
        fetchedParams = true;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ensureReadParams();
        stream.defaultWriteObject();
    }
}
