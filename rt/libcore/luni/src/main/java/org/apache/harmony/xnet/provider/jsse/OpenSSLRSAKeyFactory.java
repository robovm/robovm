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
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class OpenSSLRSAKeyFactory<T, S> extends KeyFactorySpi {

    @Override
    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof RSAPublicKeySpec) {
            RSAPublicKeySpec rsaKeySpec = (RSAPublicKeySpec) keySpec;

            return new OpenSSLRSAPublicKey(rsaKeySpec);
        } else if (keySpec instanceof X509EncodedKeySpec) {
            X509EncodedKeySpec x509KeySpec = (X509EncodedKeySpec) keySpec;

            try {
                final OpenSSLKey key = new OpenSSLKey(
                        NativeCrypto.d2i_PUBKEY(x509KeySpec.getEncoded()));
                return new OpenSSLRSAPublicKey(key);
            } catch (Exception e) {
                throw new InvalidKeySpecException(e);
            }
        }
        throw new InvalidKeySpecException("Must use RSAPublicKeySpec or X509EncodedKeySpec; was "
                + keySpec.getClass().getName());
    }

    @Override
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof RSAPrivateCrtKeySpec) {
            RSAPrivateCrtKeySpec rsaKeySpec = (RSAPrivateCrtKeySpec) keySpec;

            return new OpenSSLRSAPrivateCrtKey(rsaKeySpec);
        } else if (keySpec instanceof RSAPrivateKeySpec) {
            RSAPrivateKeySpec rsaKeySpec = (RSAPrivateKeySpec) keySpec;

            return new OpenSSLRSAPrivateKey(rsaKeySpec);
        } else if (keySpec instanceof PKCS8EncodedKeySpec) {
            PKCS8EncodedKeySpec pkcs8KeySpec = (PKCS8EncodedKeySpec) keySpec;

            try {
                final OpenSSLKey key = new OpenSSLKey(
                        NativeCrypto.d2i_PKCS8_PRIV_KEY_INFO(pkcs8KeySpec.getEncoded()));
                return OpenSSLRSAPrivateKey.getInstance(key);
            } catch (Exception e) {
                throw new InvalidKeySpecException(e);
            }
        }
        throw new InvalidKeySpecException("Must use RSAPublicKeySpec or PKCS8EncodedKeySpec; was "
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

        if (key instanceof RSAPublicKey) {
            RSAPublicKey rsaKey = (RSAPublicKey) key;

            if (RSAPublicKeySpec.class.equals(keySpec)) {
                BigInteger modulus = rsaKey.getModulus();
                BigInteger publicExponent = rsaKey.getPublicExponent();
                return (T) new RSAPublicKeySpec(modulus, publicExponent);
            } else if (X509EncodedKeySpec.class.equals(keySpec)) {
                return (T) new X509EncodedKeySpec(key.getEncoded());
            } else {
                throw new InvalidKeySpecException("Must be RSAPublicKeySpec or X509EncodedKeySpec");
            }
        } else if (key instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rsaKey = (RSAPrivateCrtKey) key;

            if (RSAPrivateKeySpec.class.equals(keySpec)) {
                BigInteger modulus = rsaKey.getModulus();
                BigInteger privateExponent = rsaKey.getPrivateExponent();
                return (T) new RSAPrivateKeySpec(modulus, privateExponent);
            } else if (RSAPrivateCrtKeySpec.class.equals(keySpec)) {
                BigInteger modulus = rsaKey.getModulus();
                BigInteger publicExponent = rsaKey.getPublicExponent();
                BigInteger privateExponent = rsaKey.getPrivateExponent();
                BigInteger primeP = rsaKey.getPrimeP();
                BigInteger primeQ = rsaKey.getPrimeQ();
                BigInteger primeExponentP = rsaKey.getPrimeExponentP();
                BigInteger primeExponentQ = rsaKey.getPrimeExponentQ();
                BigInteger crtCoefficient = rsaKey.getCrtCoefficient();
                return (T) new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent,
                        primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
            } else if (PKCS8EncodedKeySpec.class.equals(keySpec)) {
                return (T) new PKCS8EncodedKeySpec(rsaKey.getEncoded());
            } else {
                throw new InvalidKeySpecException(
                        "Must be RSAPrivateKeySpec or or RSAPrivateCrtKeySpec or PKCS8EncodedKeySpec");
            }
        } else if (key instanceof RSAPrivateKey) {
            RSAPrivateKey rsaKey = (RSAPrivateKey) key;

            if (RSAPrivateKeySpec.class.equals(keySpec)) {
                BigInteger modulus = rsaKey.getModulus();
                BigInteger privateExponent = rsaKey.getPrivateExponent();
                return (T) new RSAPrivateKeySpec(modulus, privateExponent);
            } else if (RSAPrivateCrtKeySpec.class.equals(keySpec)) {
                BigInteger modulus = rsaKey.getModulus();
                BigInteger privateExponent = rsaKey.getPrivateExponent();
                return (T) new RSAPrivateCrtKeySpec(modulus, null, privateExponent, null, null,
                        null, null, null);
            } else if (PKCS8EncodedKeySpec.class.equals(keySpec)) {
                return (T) new PKCS8EncodedKeySpec(rsaKey.getEncoded());
            } else {
                throw new InvalidKeySpecException(
                        "Must be RSAPrivateKeySpec or PKCS8EncodedKeySpec");
            }
        } else {
            throw new InvalidKeySpecException("Must be RSAPublicKey or RSAPrivateKey");
        }
    }

    @Override
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }

        if (key instanceof RSAPublicKey) {
            RSAPublicKey rsaKey = (RSAPublicKey) key;

            try {
                return engineGeneratePublic(new RSAPublicKeySpec(rsaKey.getModulus(),
                        rsaKey.getPublicExponent()));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else if (key instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rsaKey = (RSAPrivateCrtKey) key;
            BigInteger modulus = rsaKey.getModulus();
            BigInteger publicExponent = rsaKey.getPublicExponent();
            BigInteger privateExponent = rsaKey.getPrivateExponent();
            BigInteger primeP = rsaKey.getPrimeP();
            BigInteger primeQ = rsaKey.getPrimeQ();
            BigInteger primeExponentP = rsaKey.getPrimeExponentP();
            BigInteger primeExponentQ = rsaKey.getPrimeExponentQ();
            BigInteger crtCoefficient = rsaKey.getCrtCoefficient();

            try {
                return engineGeneratePrivate(new RSAPrivateCrtKeySpec(modulus, publicExponent,
                        privateExponent, primeP, primeQ, primeExponentP, primeExponentQ,
                        crtCoefficient));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else if (key instanceof RSAPrivateKey) {
            RSAPrivateKey rsaKey = (RSAPrivateKey) key;
            BigInteger modulus = rsaKey.getModulus();
            BigInteger privateExponent = rsaKey.getPrivateExponent();

            try {
                return engineGeneratePrivate(new RSAPrivateKeySpec(modulus, privateExponent));
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        } else {
            throw new InvalidKeyException(
                    "Key must be RSAPublicKey or RSAPrivateCrtKey or RSAPrivateKey");
        }
    }
}
