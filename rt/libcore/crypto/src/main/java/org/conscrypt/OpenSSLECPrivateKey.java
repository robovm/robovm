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
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public final class OpenSSLECPrivateKey implements ECPrivateKey, OpenSSLKeyHolder {
    private static final long serialVersionUID = -4036633595001083922L;

    private static final String ALGORITHM = "EC";

    protected transient OpenSSLKey key;

    protected transient OpenSSLECGroupContext group;

    public OpenSSLECPrivateKey(OpenSSLECGroupContext group, OpenSSLKey key) {
        this.group = group;
        this.key = key;
    }

    public OpenSSLECPrivateKey(OpenSSLKey key) {
        final long origGroup = NativeCrypto.EC_KEY_get0_group(key.getPkeyContext());
        this.group = new OpenSSLECGroupContext(NativeCrypto.EC_GROUP_dup(origGroup));
        this.key = key;
    }

    public OpenSSLECPrivateKey(ECPrivateKeySpec ecKeySpec) throws InvalidKeySpecException {
        try {
            group = OpenSSLECGroupContext.getInstance(ecKeySpec.getParams());
            final BigInteger privKey = ecKeySpec.getS();
            key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_EC_KEY(group.getContext(), 0,
                    privKey.toByteArray()));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }
    }

    public static OpenSSLKey getInstance(ECPrivateKey ecPrivateKey) throws InvalidKeyException {
        try {
            OpenSSLECGroupContext group = OpenSSLECGroupContext.getInstance(ecPrivateKey
                    .getParams());
            final BigInteger privKey = ecPrivateKey.getS();
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_EC_KEY(group.getContext(), 0,
                    privKey.toByteArray()));
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override
    public String getAlgorithm() {
        return ALGORITHM;
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
    public ECParameterSpec getParams() {
        return group.getECParameterSpec();
    }

    @Override
    public BigInteger getS() {
        if (key.isEngineBased()) {
            throw new UnsupportedOperationException("private key value S cannot be extracted");
        }

        return getPrivateKey();
    }

    private BigInteger getPrivateKey() {
        return new BigInteger(NativeCrypto.EC_KEY_get_private_key(key.getPkeyContext()));
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

        if (o instanceof OpenSSLECPrivateKey) {
            OpenSSLECPrivateKey other = (OpenSSLECPrivateKey) o;
            return key.equals(other.key);
        }

        if (!(o instanceof ECPrivateKey)) {
            return false;
        }

        final ECPrivateKey other = (ECPrivateKey) o;
        if (!getPrivateKey().equals(other.getS())) {
            return false;
        }

        final ECParameterSpec spec = getParams();
        final ECParameterSpec otherSpec = other.getParams();

        return spec.getCurve().equals(otherSpec.getCurve())
                && spec.getGenerator().equals(otherSpec.getGenerator())
                && spec.getOrder().equals(otherSpec.getOrder())
                && spec.getCofactor() == otherSpec.getCofactor();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(NativeCrypto.i2d_PKCS8_PRIV_KEY_INFO(key.getPkeyContext()));
    }

    @Override
    public String toString() {
        return NativeCrypto.EVP_PKEY_print_private(key.getPkeyContext());
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        byte[] encoded = (byte[]) stream.readObject();

        key = new OpenSSLKey(NativeCrypto.d2i_PKCS8_PRIV_KEY_INFO(encoded));

        final long origGroup = NativeCrypto.EC_KEY_get0_group(key.getPkeyContext());
        group = new OpenSSLECGroupContext(NativeCrypto.EC_GROUP_dup(origGroup));
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        if (key.isEngineBased()) {
            throw new NotSerializableException("engine-based keys can not be serialized");
        }

        stream.defaultWriteObject();
        stream.writeObject(getEncoded());
    }
}
