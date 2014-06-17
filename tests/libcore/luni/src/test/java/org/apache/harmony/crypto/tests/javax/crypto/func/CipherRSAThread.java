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


import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class CipherRSAThread extends CipherThread {

    CipherRSAThread(String name, int[] keys, String[] modes, String[] paddings) {
        super(name, keys, modes, paddings);
    }

    @Override
    public void crypt() throws Exception {
        byte[] output = new byte[256];
        byte[] decrypted = new byte[256];
        int dataBlock = 20;
        byte[] input  =  getData().substring(0, dataBlock).getBytes();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(getKeyLength());
        KeyPair kp = kpg.generateKeyPair();

        Cipher cip = Cipher.getInstance(getAlgName() + "/" + getMode() + "/" +
                getPadding());
        cip.init(Cipher.ENCRYPT_MODE, kp.getPublic());
        cip.doFinal(input, 0, input.length, output);
        int outputSize = cip.getOutputSize(input.length);
        cip.init(Cipher.DECRYPT_MODE, kp.getPrivate());
        cip.doFinal(output, 0, outputSize, decrypted);

        if ("NOPADDING".equals(getPadding())) {
            checkPaddedEncodedData(input, decrypted, outputSize - input.length);
        } else {
            checkEncodedData(input, decrypted);
        }
    }
}
