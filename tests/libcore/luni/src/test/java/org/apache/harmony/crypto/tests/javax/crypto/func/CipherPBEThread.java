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


import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class CipherPBEThread extends CipherThread {

    CipherPBEThread(String name, int[] keys, String[] modes, String[] paddings) {
        super(name, keys, modes, paddings);
    }

    @Override
    public void crypt() throws Exception {
        byte[] output = new byte[128];
        byte[] decrypted = new byte[128];
        byte[] input  =  getData().getBytes();
        byte[] salt = new byte[8];
        SecureRandom sr = new SecureRandom();

        PBEKeySpec keySpec = new PBEKeySpec("top sicret password".toCharArray());
        SecretKeyFactory skf = SecretKeyFactory.getInstance(getAlgName());
        SecretKey key = skf.generateSecret(keySpec);

        Cipher cip = Cipher.getInstance(getAlgName() + "/" + getMode() + "/" +
                getPadding());

        sr.nextBytes(salt);
        PBEParameterSpec parSpec = new PBEParameterSpec(salt, getKeyLength());

        cip.init(Cipher.ENCRYPT_MODE, key, parSpec);
        cip.doFinal(input, 0, input.length, output);
        int outputSize = cip.getOutputSize(input.length);
        cip.init(Cipher.DECRYPT_MODE, key, parSpec);
        cip.doFinal(output, 0, outputSize, decrypted);

        checkEncodedData(getData().getBytes(), decrypted);
    }
}
