/*
 * Copyright (C) 2013 The Android Open Source Project
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
import java.security.InvalidKeyException;
import java.util.Arrays;

import javax.crypto.SecretKey;

public class OpenSSLSecretKey implements SecretKey, OpenSSLKeyHolder {
    private static final long serialVersionUID = 1831053062911514589L;

    private final String algorithm;
    private final int type;
    private final byte[] encoded;

    private transient OpenSSLKey key;

    public OpenSSLSecretKey(String algorithm, byte[] encoded) {
        this.algorithm = algorithm;
        this.encoded = encoded;

        type = NativeCrypto.EVP_PKEY_HMAC;
        key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_mac_key(type, encoded));
    }

    public OpenSSLSecretKey(String algorithm, OpenSSLKey key) {
        this.algorithm = algorithm;
        this.key = key;

        type = NativeCrypto.EVP_PKEY_type(key.getPkeyContext());
        encoded = null;
    }

    public static OpenSSLKey getInstance(SecretKey key) throws InvalidKeyException {
        try {
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_mac_key(NativeCrypto.EVP_PKEY_HMAC,
                    key.getEncoded()));
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String getFormat() {
        if (key.isEngineBased()) {
            return null;
        }

        return "RAW";
    }

    @Override
    public byte[] getEncoded() {
        if (key.isEngineBased()) {
            return null;
        }

        return encoded;
    }

    @Override
    public OpenSSLKey getOpenSSLKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SecretKey)) {
            return false;
        }

        SecretKey other = (SecretKey) o;
        if (!algorithm.equals(other.getAlgorithm())) {
            return false;
        }

        if (o instanceof OpenSSLSecretKey) {
            OpenSSLSecretKey otherOpenSSL = (OpenSSLSecretKey) o;
            return key.equals(otherOpenSSL.getOpenSSLKey());
        } else if (key.isEngineBased()) {
            return false;
        }

        if (!getFormat().equals(other.getFormat())) {
            return false;
        }

        return Arrays.equals(encoded, other.getEncoded());
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_mac_key(type, encoded));
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (getOpenSSLKey().isEngineBased()) {
            throw new NotSerializableException("engine-based keys can not be serialized");
        }

        stream.defaultWriteObject();
    }
}
