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

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class OpenSSLECKeyFactory extends KeyFactorySpi {

    @Override
    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec == null) {
            throw new InvalidKeySpecException("keySpec == null");
        }

        if (keySpec instanceof ECPublicKeySpec) {
            return new OpenSSLECPublicKey((ECPublicKeySpec) keySpec);
        } else if (keySpec instanceof X509EncodedKeySpec) {
            return OpenSSLKey.getPublicKey((X509EncodedKeySpec) keySpec, NativeCrypto.EVP_PKEY_EC);
        }
        throw new InvalidKeySpecException("Must use ECPublicKeySpec or X509EncodedKeySpec; was "
                + keySpec.getClass().getName());
    }

    @Override
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec == null) {
            throw new InvalidKeySpecException("keySpec == null");
        }

        if (keySpec instanceof ECPrivateKeySpec) {
            return new OpenSSLECPrivateKey((ECPrivateKeySpec) keySpec);
        } else if (keySpec instanceof PKCS8EncodedKeySpec) {
            return OpenSSLKey.getPrivateKey((PKCS8EncodedKeySpec) keySpec,
                    NativeCrypto.EVP_PKEY_EC);
        }
        throw new InvalidKeySpecException("Must use ECPrivateKeySpec or PKCS8EncodedKeySpec; was "
                + keySpec.getClass().getName());
    }

    @Override
    protected <T extends KeySpec> T engineGetKeySpec(Key key, Class<T> keySpec)
            throws InvalidKeySpecException {
        if (key == null) {
            throw new InvalidKeySpecException("key == null");
        }

        if (keySpec == null) {
            throw new InvalidKeySpecException("keySpec == null");
        }

        if (!"EC".equals(key.getAlgorithm())) {
            throw new InvalidKeySpecException("Key must be an EC key");
        }

        if (key instanceof ECPublicKey && ECPublicKeySpec.class.isAssignableFrom(keySpec)) {
            ECPublicKey ecKey = (ECPublicKey) key;
            return (T) new ECPublicKeySpec(ecKey.getW(), ecKey.getParams());
        } else if (key instanceof PublicKey && ECPublicKeySpec.class.isAssignableFrom(keySpec)) {
            final byte[] encoded = key.getEncoded();
            if (!"X.509".equals(key.getFormat()) || encoded == null) {
                throw new InvalidKeySpecException("Not a valid X.509 encoding");
            }
            ECPublicKey ecKey = (ECPublicKey) engineGeneratePublic(new X509EncodedKeySpec(encoded));
            return (T) new ECPublicKeySpec(ecKey.getW(), ecKey.getParams());
        } else if (key instanceof ECPrivateKey
                && ECPrivateKeySpec.class.isAssignableFrom(keySpec)) {
            ECPrivateKey ecKey = (ECPrivateKey) key;
            return (T) new ECPrivateKeySpec(ecKey.getS(), ecKey.getParams());
        } else if (key instanceof PrivateKey && ECPrivateKeySpec.class.isAssignableFrom(keySpec)) {
            final byte[] encoded = key.getEncoded();
            if (!"PKCS#8".equals(key.getFormat()) || encoded == null) {
                throw new InvalidKeySpecException("Not a valid PKCS#8 encoding");
            }
            ECPrivateKey ecKey =
                    (ECPrivateKey) engineGeneratePrivate(new PKCS8EncodedKeySpec(encoded));
            return (T) new ECPrivateKeySpec(ecKey.getS(), ecKey.getParams());
        } else if (key instanceof PrivateKey
                && PKCS8EncodedKeySpec.class.isAssignableFrom(keySpec)) {
            final byte[] encoded = key.getEncoded();
            if (!"PKCS#8".equals(key.getFormat())) {
                throw new InvalidKeySpecException("Encoding type must be PKCS#8; was "
                        + key.getFormat());
            } else if (encoded == null) {
                throw new InvalidKeySpecException("Key is not encodable");
            }
            return (T) new PKCS8EncodedKeySpec(encoded);
        } else if (key instanceof PublicKey && X509EncodedKeySpec.class.isAssignableFrom(keySpec)) {
            final byte[] encoded = key.getEncoded();
            if (!"X.509".equals(key.getFormat())) {
                throw new InvalidKeySpecException("Encoding type must be X.509; was "
                        + key.getFormat());
            } else if (encoded == null) {
                throw new InvalidKeySpecException("Key is not encodable");
            }
            return (T) new X509EncodedKeySpec(encoded);
        } else {
            throw new InvalidKeySpecException("Unsupported key type and key spec combination; key="
                    + key.getClass().getName() + ", keySpec=" + keySpec.getName());
        }
    }

    @Override
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        if ((key instanceof OpenSSLECPublicKey) || (key instanceof OpenSSLECPrivateKey)) {
            return key;
        } else if (key instanceof ECPublicKey) {
            ECPublicKey ecKey = (ECPublicKey) key;

            ECPoint w = ecKey.getW();

            ECParameterSpec params = ecKey.getParams();

            try {
                return engineGeneratePublic(new ECPublicKeySpec(w, params));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else if (key instanceof ECPrivateKey) {
            ECPrivateKey ecKey = (ECPrivateKey) key;

            BigInteger s = ecKey.getS();

            ECParameterSpec params = ecKey.getParams();

            try {
                return engineGeneratePrivate(new ECPrivateKeySpec(s, params));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else if ((key instanceof PrivateKey) && ("PKCS#8".equals(key.getFormat()))) {
            byte[] encoded = key.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("Key does not support encoding");
            }
            try {
                return engineGeneratePrivate(new PKCS8EncodedKeySpec(encoded));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else if ((key instanceof PublicKey) && ("X.509".equals(key.getFormat()))) {
            byte[] encoded = key.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("Key does not support encoding");
            }
            try {
                return engineGeneratePublic(new X509EncodedKeySpec(encoded));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else {
            throw new InvalidKeyException("Key must be EC public or private key; was "
                    + key.getClass().getName());
        }
    }

}
