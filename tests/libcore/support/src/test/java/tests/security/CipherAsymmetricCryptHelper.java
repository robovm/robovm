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

import java.security.KeyPair;
import javax.crypto.Cipher;

public class CipherAsymmetricCryptHelper extends CipherHelper<KeyPair> {

    private static final String plainData = "some data to encrypt and decrypt test";

    public CipherAsymmetricCryptHelper(String algorithmName) {
        super(algorithmName, plainData, Cipher.ENCRYPT_MODE,
                Cipher.DECRYPT_MODE);
    }

    public void test(KeyPair keyPair) {
        test(keyPair.getPrivate(), keyPair.getPublic());
    }
}
