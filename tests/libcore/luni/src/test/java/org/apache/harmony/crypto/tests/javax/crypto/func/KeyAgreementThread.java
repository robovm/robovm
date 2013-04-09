/*
 * Copyright (C) 2008 The Android Open Source Project
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
package org.apache.harmony.crypto.tests.javax.crypto.func;

import com.android.org.bouncycastle.util.Arrays;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;

public class KeyAgreementThread extends TestThread {
    class KeyAgreementGen {
        private PrivateKey privateKey = null;
        private byte[] publicKeyBytes = null;

        KeyAgreementGen(DHParameterSpec parameterSpec)
                throws Exception {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
            keyGen.initialize(parameterSpec);
            KeyPair keypair = keyGen.generateKeyPair();

            privateKey     = keypair.getPrivate();
            publicKeyBytes = keypair.getPublic().getEncoded();
        }

        public byte[] getPublicKeyBytes () {
            return publicKeyBytes;
        }

        public byte[] getSecretKey(String alg, byte[] publicKey) throws Exception {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFact = KeyFactory.getInstance("DH");
            PublicKey pubKey = keyFact.generatePublic(x509KeySpec);

            KeyAgreement ka = KeyAgreement.getInstance("DH");
            ka.init(privateKey);
            ka.doPhase(pubKey, true);

            return ka.generateSecret();
        }
    }

    public KeyAgreementThread(String[] names) {
        super(names);
    }

    @Override
    public void test() throws Exception {
        AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance("DH");
        apg.init(1024, new SecureRandom());
        AlgorithmParameters ap = apg.generateParameters();
        DHParameterSpec ps = ap.getParameterSpec(DHParameterSpec.class);

        KeyAgreementGen kag1 = new KeyAgreementGen(ps);
        KeyAgreementGen kag2 = new KeyAgreementGen(ps);

        byte[] bArray1 = kag1.getPublicKeyBytes();
        byte[] bArray2 = kag2.getPublicKeyBytes();

        byte[] sk1 = kag1.getSecretKey(algName, bArray2);
        byte[] sk2 = kag2.getSecretKey(algName, bArray1);

        if (Arrays.areEqual(sk1, sk2) == false) {
            throw new Exception ("Generated keys are not the same");
        }
    }
}
