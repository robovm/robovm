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

public class CipherDESedeTest extends TestCase {
// 80 cases checked
    public void test_DESedeNoISO() {
        CipherSymmetricKeyThread DESedeNoISO = new CipherSymmetricKeyThread(
                "DESede", new int[] {112, 168},// Keysize must be 112 or 168.
                new String[] {
                        "ECB", "CBC", "CFB", "CFB8", "CFB16", "CFB24", "CFB32",
                        "CFB40", "CFB48", "CFB56", "CFB64", "OFB", "OFB8",
                        "OFB16", "OFB24", "OFB32", "OFB40", "OFB48", "OFB56",
                        "OFB64"}, new String[] {"NoPadding", "PKCS5Padding"});

        DESedeNoISO.launcher();

        assertEquals(DESedeNoISO.getFailureMessages(), 0, DESedeNoISO
                .getTotalFailuresNumber());
    }

// 40 cases checked
    public void test_DESedeISO() {
        CipherSymmetricKeyThread DESedeISO = new CipherSymmetricKeyThread(
                "DESede", new int[] {112, 168},// Keysize must be 112 or 168.
                new String[] {
                        "ECB", "CBC", "CFB", "CFB8", "CFB16", "CFB24", "CFB32",
                        "CFB40", "CFB48", "CFB56", "CFB64", "OFB", "OFB8",
                        "OFB16", "OFB24", "OFB32", "OFB40", "OFB48", "OFB56",
                        "OFB64"}, new String[] {"ISO10126PADDING"});

        DESedeISO.launcher();

        assertEquals(DESedeISO.getFailureMessages(), 0, DESedeISO
                .getTotalFailuresNumber());
    }
}
