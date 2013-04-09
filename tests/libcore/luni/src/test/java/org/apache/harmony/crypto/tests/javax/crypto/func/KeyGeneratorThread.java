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


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

public class KeyGeneratorThread extends TestThread {
    KeyGeneratorThread(String[] names) {
        super(names);
    }

    public void test() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(algName);
        Key k = kg.generateKey();
        if(kg.getAlgorithm().toLowerCase().equals(k.getAlgorithm().toLowerCase()) != true) {
            throw new Exception ("Algorithm names not matched for KeyGenerator" +
                    " and for Key objects");
        }
        if(kg.getAlgorithm().toLowerCase().equals(algName.toLowerCase()) != true) {
            throw new Exception ("Algorithm names not matched for KeyGenerator" +
            " and for Key objects");
        }
        byte[] array1 = k.getEncoded();
        k = kg.generateKey();
        byte[] array2 = k.getEncoded();
        int matches = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] == array2[i]) {
                matches++;
            }
        }
        if (matches > array1.length / 2) {
            throw new Exception("Generated keys are simular");
        }
        SecureRandom random = new SecureRandom();
        kg.init(random);
        matches = 0;
        k = kg.generateKey();
        array1 = k.getEncoded();
        random = new SecureRandom();
        kg.init(random);
        k = kg.generateKey();
        array2 = k.getEncoded();
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] == array2[i]) {
                matches++;
            }
        }
    }
}
