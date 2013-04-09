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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import junit.framework.Assert;

public class AlgorithmParameterSymmetricHelper extends TestHelper<AlgorithmParameters> {

    private static final String plainData = "some data to encrypt and decrypt";
    private final String algorithmName;
    private final int keySize;
    private String blockmode;

    public AlgorithmParameterSymmetricHelper(String algorithmName, int keySize) {
        this.algorithmName = algorithmName;
        this.keySize = keySize;
    }

    public AlgorithmParameterSymmetricHelper(String algorithmName, String blockmode, int keySize) {
        this(algorithmName, keySize);
        this.blockmode = blockmode;
    }

    @Override
    public void test(AlgorithmParameters parameters) {

        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        }

        generator.init(keySize);

        Key key = generator.generateKey();


        Cipher cipher = null;
        try {
            String transformation = algorithmName;
            if (blockmode != null)
            {
                transformation += "/" + blockmode;
            }
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        } catch (NoSuchPaddingException e) {
            Assert.fail(e.getMessage());
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, parameters);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            Assert.fail(e.getMessage());
        }

        byte[] bs = null;
        try {
            bs = cipher.doFinal(plainData.getBytes());
        } catch (IllegalBlockSizeException e) {
            Assert.fail(e.getMessage());
        } catch (BadPaddingException e) {
            Assert.fail(e.getMessage());
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, key, parameters);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            Assert.fail(e.getMessage());
        }

        byte[] decrypted = null;
        try {
            decrypted = cipher.doFinal(bs);
        } catch (IllegalBlockSizeException e) {
            Assert.fail(e.getMessage());
        } catch (BadPaddingException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(Arrays.equals(plainData.getBytes(), decrypted));
    }
}
