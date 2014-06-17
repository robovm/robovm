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

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGeneratorSpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

public class OpenSSLRSAKeyPairGenerator extends KeyPairGeneratorSpi {
    /**
     * Default modulus size is 0x10001 (65537)
     */
    private byte[] publicExponent = new byte[] {
            0x01, 0x00, 0x01
    };

    /**
     * Default RSA key size 2048 bits.
     */
    private int modulusBits = 2048;

    @Override
    public KeyPair generateKeyPair() {
        final OpenSSLKey key = new OpenSSLKey(NativeCrypto.RSA_generate_key_ex(modulusBits,
                publicExponent));

        PrivateKey privKey = OpenSSLRSAPrivateKey.getInstance(key);
        PublicKey pubKey = new OpenSSLRSAPublicKey(key);

        return new KeyPair(pubKey, privKey);
    }

    @Override
    public void initialize(int keysize, SecureRandom random) {
        this.modulusBits = keysize;
    }

    @Override
    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        if (!(params instanceof RSAKeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("Only RSAKeyGenParameterSpec supported");
        }

        RSAKeyGenParameterSpec spec = (RSAKeyGenParameterSpec) params;

        final BigInteger publicExponent = spec.getPublicExponent();
        if (publicExponent != null) {
            this.publicExponent = publicExponent.toByteArray();
        }

        this.modulusBits = spec.getKeysize();
    }
}
