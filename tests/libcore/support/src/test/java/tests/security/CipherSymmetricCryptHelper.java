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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CipherSymmetricCryptHelper extends CipherHelper<SecretKey/*, U*/> {

    private static final String plainData = "some data to encrypt and decrypt test";

    public CipherSymmetricCryptHelper(String algorithmName) {
        super(algorithmName, plainData, Cipher.ENCRYPT_MODE,
                Cipher.DECRYPT_MODE);
    }

    public void test(SecretKey key) {
        test(key, key);
    }
}
