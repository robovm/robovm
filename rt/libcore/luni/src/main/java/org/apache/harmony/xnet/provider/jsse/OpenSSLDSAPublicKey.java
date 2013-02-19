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
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;

public class OpenSSLDSAPublicKey implements DSAPublicKey {
    private static final long serialVersionUID = 5238609500353792232L;

    private final OpenSSLKey key;

    private OpenSSLDSAParams params;

    OpenSSLDSAPublicKey(OpenSSLKey key) {
        this.key = key;
    }

    OpenSSLKey getOpenSSLKey() {
        return key;
    }

    OpenSSLDSAPublicKey(DSAPublicKeySpec dsaKeySpec) throws InvalidKeySpecException {
        try {
            key = new OpenSSLKey(NativeCrypto.EVP_PKEY_new_DSA(
                    dsaKeySpec.getP().toByteArray(),
                    dsaKeySpec.getQ().toByteArray(),
                    dsaKeySpec.getG().toByteArray(),
                    dsaKeySpec.getY().toByteArray(),
                    null));
        } catch (Exception e) {
            throw new InvalidKeySpecException(e);
        }
    }

    private void ensureReadParams() {
        if (params == null) {
            params = new OpenSSLDSAParams(key);
        }
    }

    static OpenSSLKey getInstance(DSAPublicKey dsaPublicKey) throws InvalidKeyException {
        try {
            final DSAParams dsaParams = dsaPublicKey.getParams();
            return new OpenSSLKey(NativeCrypto.EVP_PKEY_new_DSA(
                    dsaParams.getP().toByteArray(),
                    dsaParams.getQ().toByteArray(),
                    dsaParams.getG().toByteArray(),
                    dsaPublicKey.getY().toByteArray(),
                    null));
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
        return "X.509";
    }

    @Override
    public byte[] getEncoded() {
        return NativeCrypto.i2d_PUBKEY(key.getPkeyContext());
    }

    @Override
    public BigInteger getY() {
        ensureReadParams();
        return params.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OpenSSLDSAPublicKey) {
            OpenSSLDSAPublicKey other = (OpenSSLDSAPublicKey) o;

            /*
             * We can shortcut the true case, but it still may be equivalent but
             * different copies.
             */
            if (key.equals(other.getOpenSSLKey())) {
                return true;
            }
        }

        if (!(o instanceof DSAPublicKey)) {
            return false;
        }

        ensureReadParams();

        DSAPublicKey other = (DSAPublicKey) o;
        return params.getY().equals(other.getY()) && params.equals(other.getParams());
    }

    @Override
    public int hashCode() {
        ensureReadParams();

        return params.getY().hashCode() ^ params.hashCode();
    }

    @Override
    public String toString() {
        ensureReadParams();

        final StringBuilder sb = new StringBuilder("OpenSSLDSAPublicKey{");
        sb.append("Y=");
        sb.append(params.getY().toString(16));
        sb.append(',');
        sb.append("params=");
        sb.append(params.toString());
        sb.append('}');

        return sb.toString();
    }
}
