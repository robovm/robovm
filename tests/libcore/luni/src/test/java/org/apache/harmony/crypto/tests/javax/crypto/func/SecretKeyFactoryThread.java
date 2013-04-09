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

import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;

public class SecretKeyFactoryThread extends TestThread {
    SecretKeyFactoryThread(String[] names) {
        super(names);
    }

    @Override
    public void test() throws Exception {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(algName);
        byte[] b = new byte[24];
        KeySpec ks = (KeySpec) ((algName == "DES") ? new DESKeySpec(b) :
            (algName == "DESede") ? new DESedeKeySpec(b) :
            new PBEKeySpec("passw".toCharArray()));
        skf.generateSecret(ks);
    }
}
