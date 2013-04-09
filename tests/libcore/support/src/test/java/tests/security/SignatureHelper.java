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
import java.security.Signature;
import java.security.SignatureException;
import junit.framework.Assert;

public class SignatureHelper extends TestHelper<KeyPair> {

    private final String algorithmName;
    private final String plainData = "some data do sign and verify";

    public SignatureHelper(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public void test(KeyPair keyPair) {
        test(keyPair.getPrivate(), keyPair.getPublic());
    }

    public void test(PrivateKey encryptKey, PublicKey decryptKey) {

        Signature signature = null;
        try {
            signature = Signature.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.initSign(encryptKey);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.update(plainData.getBytes());
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        byte[] signed = null;
        try {
            signed = signature.sign();
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.initVerify(decryptKey);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.update(plainData.getBytes());
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        try {
            Assert.assertTrue("signature could not be verified", signature
                    .verify(signed));
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }
    }
}
