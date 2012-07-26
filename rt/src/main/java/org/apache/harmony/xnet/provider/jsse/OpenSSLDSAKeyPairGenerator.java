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
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGeneratorSpi;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;

public class OpenSSLDSAKeyPairGenerator extends KeyPairGeneratorSpi {

    private int primeBits = 1024;

    private SecureRandom random = null;

    private byte[] g;

    private byte[] p;

    private byte[] q;

    @Override
    public KeyPair generateKeyPair() {
        final byte[] seed;
        if (random == null) {
            seed = null;
        } else {
            seed = new byte[20];
            random.nextBytes(seed);
        }

        final OpenSSLKey key = new OpenSSLKey(NativeCrypto.DSA_generate_key(primeBits, seed, g, p,
                q));

        final OpenSSLDSAPrivateKey privKey = new OpenSSLDSAPrivateKey(key);
        final OpenSSLDSAPublicKey pubKey = new OpenSSLDSAPublicKey(key);

        return new KeyPair(pubKey, privKey);
    }

    @Override
    public void initialize(int keysize, SecureRandom random) {
        primeBits = keysize;
        this.random = random;
    }

    @Override
    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        this.random = random;

        if (params instanceof DSAParameterSpec) {
            DSAParameterSpec dsaParams = (DSAParameterSpec) params;

            BigInteger gInt = dsaParams.getG();
            if (gInt != null) {
                g = gInt.toByteArray();
            }

            BigInteger pInt = dsaParams.getP();
            if (pInt != null) {
                p = pInt.toByteArray();
            }

            BigInteger qInt = dsaParams.getQ();
            if (qInt != null) {
                q = qInt.toByteArray();
            }
        } else if (params != null) {
            throw new InvalidAlgorithmParameterException("Params must be DSAParameterSpec");
        }
    }
}
