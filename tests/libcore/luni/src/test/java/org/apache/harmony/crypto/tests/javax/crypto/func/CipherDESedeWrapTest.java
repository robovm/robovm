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

public class CipherDESedeWrapTest extends TestCase {
//  2 cases checked.
    public void test_DESedeWrap() {
        CipherWrapThread DESedeWrap = new CipherWrapThread("DESedeWrap",
                new int[] {112, 168}, // Keysize must be 112 or 168.
                new String[] {"CBC"}, new String[] {"NoPadding"});

        DESedeWrap.launcher();

        assertEquals(DESedeWrap.getFailureMessages(), 0, DESedeWrap
                .getTotalFailuresNumber());
    }
}
