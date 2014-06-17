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
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;

public class OpenSSLDSAPrivateKey implements DSAPrivateKey, OpenSSLKeyHolder {
    private static final long serialVersionUID = 6524734576187424628L;

    private transient OpenSSLKey key;

    private transient OpenSSLDSAParams params;

    OpenSSLDSAPrivateKey(OpenSSLKey key) {
        this.key = key;
    }

    @Override
    public OpenSSLKey getOpenSSLKey() {
        return key;
    }

    OpenSSLDSAPrivateKey(DSAPrivateKeySpec dsaKeySpec) throws InvalidKeySpecException {
        try {
            key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_DSA(
                    dsaKeySpec.getP().toByteArray(),
                    dsaKeySpec.getQ().toByteArray(),
                    dsaKeySpec.getG().toByteArray(),
                    null,
                    dsaKeySpec.getX().toByteArray()));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }
    }

    private void ensureReadParams() {
        if (params == null) {
            params = new OpenSSLDSAParams(key);
        }
    }

    static OpenSSLKey getInstance(DSAPrivateKey dsaPrivateKey) throws InvalidKeyException {
        try {
            DSAParams dsaParams = dsaPrivateKey.getParams();
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_DSA(
                    dsaParams.getP().toByteArray(),
                    dsaParams.getQ().toByteArray(),
                    dsaParams.getG().toByteArray(),
                    null,
                    dsaPrivateKey.getX().toByteArray()));
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override
    public DSAParams getParams() {
        ensureReadParams();
        return params;
    }

    @Override
    public String getAlgorithm() {
        return "DSA";
    }

    @Override
    public String getFormat() {
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
    public byte[] getEncoded() {
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

    @Override
    public BigInteger getX() {
        if (key.isEngineBased()) {
            throw new UnsupportedOperationException("private key value X cannot be extracted");
        }

        ensureReadParams();
        return params.getX();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OpenSSLDSAPrivateKey) {
            OpenSSLDSAPrivateKey other = (OpenSSLDSAPrivateKey) o;

            /*
             * We can shortcut the true case, but it still may be equivalent but
             * different copies.
             */
            if (key.equals(other.getOpenSSLKey())) {
                return true;
            }
        }

        if (!(o instanceof DSAPrivateKey)) {
            return false;
        }

        ensureReadParams();

        final BigInteger x = params.getX();
        if (x == null) {
            /*
             * If our X is null, we can't tell if these two private keys are
             * equivalent. This usually happens if this key is ENGINE-based. If
             * the other key was ENGINE-based, we should have caught it in the
             * OpenSSLDSAPrivateKey case.
             */
            return false;
        }

        final DSAPrivateKey other = (DSAPrivateKey) o;
        return x.equals(other.getX()) && params.equals(other.getParams());
    }

    @Override
    public int hashCode() {
        ensureReadParams();

        int hash = 1;

        final BigInteger x = getX();
        if (x != null) {
            hash = hash * 3 + x.hashCode();
        }

        hash = hash * 7 + params.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpenSSLDSAPrivateKey{");

        if (key.isEngineBased()) {
            sb.append("key=");
            sb.append(key);
            sb.append('}');
            return sb.toString();
        }

        ensureReadParams();
        sb.append("X=");
        sb.append(params.getX().toString(16));
        sb.append(',');
        sb.append("params=");
        sb.append(params.toString());
        sb.append('}');

        return sb.toString();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        final BigInteger g = (BigInteger) stream.readObject();
        final BigInteger p = (BigInteger) stream.readObject();
        final BigInteger q = (BigInteger) stream.readObject();
        final BigInteger x = (BigInteger) stream.readObject();

        key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_DSA(
                p.toByteArray(),
                q.toByteArray(),
                g.toByteArray(),
                null,
                x.toByteArray()));
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (getOpenSSLKey().isEngineBased()) {
            throw new NotSerializableException("engine-based keys can not be serialized");
        }

        stream.defaultWriteObject();

        ensureReadParams();
        stream.writeObject(params.getG());
        stream.writeObject(params.getP());
        stream.writeObject(params.getQ());
        stream.writeObject(params.getX());
    }
}
