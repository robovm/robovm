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
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class CipherWrapThread extends CipherThread {
    CipherWrapThread(String name, int[] keys, String[] modes, String[] paddings) {
        super(name, keys, modes, paddings);
    }

    @Override
    public void crypt() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(getAlgName().replace("Wrap", ""));
        kg.init(getKeyLength(), new SecureRandom());
        Key key = kg.generateKey();

        // ignore mode and padding
        Cipher cip = Cipher.getInstance(getAlgName());

        cip.init(Cipher.WRAP_MODE, key);
        byte[] output = cip.wrap(key);
        cip.init(Cipher.UNWRAP_MODE, key);
        Key decrypted = cip.unwrap(output, getAlgName(), Cipher.SECRET_KEY);

        checkEncodedData(key.getFormat().getBytes(), decrypted.getFormat().getBytes());
        checkEncodedData(key.getEncoded(), decrypted.getEncoded());
    }
}
