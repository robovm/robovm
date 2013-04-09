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
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import junit.framework.Assert;

public abstract class CipherHelper<T> extends TestHelper<T> {

    private final String algorithmName;
    private final String plainData;
    private final int mode1;
    private final int mode2;

    public CipherHelper(String algorithmName, String plainData, int mode1, int mode2) {
        this.algorithmName = algorithmName;
        this.plainData = plainData;
        this.mode1 = mode1;
        this.mode2 = mode2;
    }

    public void test(Key encryptKey, Key decryptKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        } catch (NoSuchPaddingException e) {
            Assert.fail(e.getMessage());
        }
        try {
            cipher.init(mode1, encryptKey);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        byte[] encrypted = crypt(cipher, plainData.getBytes());

        try {
            cipher.init(mode2, decryptKey);
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        byte[] decrypted = crypt(cipher, encrypted);

        String decryptedString = new String(decrypted);

        Assert.assertEquals("transformed data does not match", plainData,
                decryptedString);
    }

    public byte[] crypt(Cipher cipher, byte[] input) {
        try {
            return cipher.doFinal(input);
        } catch (IllegalBlockSizeException e) {
            Assert.fail(e.getMessage());
        } catch (BadPaddingException e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }
}
