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

import junit.framework.TestCase;

public class CipherAesTest extends TestCase {
// 216 cases checked
    public void test_AesNoISO() {
        CipherSymmetricKeyThread aesNoISO = new CipherSymmetricKeyThread("AES",
                new int[] {128, 192, 256}, // Keysize must be 128, 192, 256.
                new String[] {
                        "ECB", "CBC", "CFB", "CFB8", "CFB16", "CFB24", "CFB32",
                        "CFB40", "CFB48", "CFB56", "CFB64", "CFB72", "CFB80",
                        "CFB88", "CFB96", "CFB104", "CFB112", "CFB120",
                        "CFB128", "OFB", "OFB8", "OFB16", "OFB24", "OFB32",
                        "OFB40", "OFB48", "OFB56", "OFB64", "OFB72", "OFB80",
                        "OFB88", "OFB96", "OFB104", "OFB112", "OFB120",
                        "OFB128"}, new String[] {"NoPadding", "PKCS5Padding"});

        aesNoISO.launcher();

        assertEquals(aesNoISO.getFailureMessages(), 0, aesNoISO
                .getTotalFailuresNumber());
    }

// 108 cases checked
    public void test_AesISO() {
        CipherSymmetricKeyThread aesISO = new CipherSymmetricKeyThread("AES",
                new int[] {128, 192, 256}, // Keysize must be 128, 192, 256.
                new String[] {
                        "ECB", "CBC", "CFB", "CFB8", "CFB16", "CFB24", "CFB32",
                        "CFB40", "CFB48", "CFB56", "CFB64", "CFB72", "CFB80",
                        "CFB88", "CFB96", "CFB104", "CFB112", "CFB120",
                        "CFB128", "OFB", "OFB8", "OFB16", "OFB24", "OFB32",
                        "OFB40", "OFB48", "OFB56", "OFB64", "OFB72", "OFB80",
                        "OFB88", "OFB96", "OFB104", "OFB112", "OFB120",
                        "OFB128"}, new String[] {"ISO10126PADDING"});

        aesISO.launcher();

        assertEquals(aesISO.getFailureMessages(), 0, aesISO
                .getTotalFailuresNumber());
    }
}
