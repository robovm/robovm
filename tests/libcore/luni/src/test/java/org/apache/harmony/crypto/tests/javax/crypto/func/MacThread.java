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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacThread extends TestThread {
    MacThread(String[] names) {
        super(names);
    }

    @Override
    public void test() throws Exception {
        int size = 256;
        byte[] src1 = new byte[size];
        byte[] src2 = new byte[size];
        byte[] src3 = new byte[size];
        int i;

        for (i = 0; i < size; i++) {
            src1[i] = (byte)i;
            src2[i] = (byte)i;
            src3[i] = (byte)(size - i - 1);
        }
        Mac m = Mac.getInstance(algName);
        byte[] b = {(byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
        SecretKeySpec sks = new SecretKeySpec(b, "SHA1");
        m.init(sks);

        byte[] res = m.doFinal(src1);
        String sign1 = new String(res);
        m.init(sks);
        res = m.doFinal(src2);
        String sign2 = new String(res);
        m.init(sks);
        res = m.doFinal(src3);
        String sign3 = new String(res);
        if (sign1.compareTo(sign2) != 0 || sign1.compareTo(sign3) == 0 ||
                sign2.compareTo(sign3) == 0) {
            throw new Exception ("Signature is not correct for algorithm " + algName);
        }
    }
}
