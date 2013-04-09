/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyAgreement;
import junit.framework.Assert;

public class KeyAgreementHelper extends TestHelper<KeyPair> {

    private final String algorithmName;

    public KeyAgreementHelper(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override public void test(KeyPair keyPair) {
        test(keyPair.getPrivate(), keyPair.getPublic());
    }

    void test(PrivateKey encryptKey, PublicKey decryptKey) {

        KeyAgreement keyAgreement = null;
        try {
            keyAgreement = KeyAgreement.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        }

        try {
            keyAgreement.init(encryptKey);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }
        try {
            keyAgreement.doPhase(decryptKey, true);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("generated secret is null", keyAgreement
                .generateSecret());

    }
}
