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

import dalvik.annotation.AndroidOnly;

import junit.framework.TestCase;

public class CipherPBETest extends TestCase {

    public void test_PBEWithMD5AndDES() throws Exception {
        CipherPBEThread PBEWithMD5AndDES = new CipherPBEThread(
                "PBEWithMD5AndDES", new int[] {56}, new String[] {"CBC"},
                new String[] {"PKCS5Padding"});

        PBEWithMD5AndDES.launcher();

        assertEquals(PBEWithMD5AndDES.getFailureMessages(), 0, PBEWithMD5AndDES
                .getTotalFailuresNumber());
    }

    @AndroidOnly("Not supported by RI (maybe with a different name)")
    public void test_PBEWithSHAand3KeyTripleDES() throws Exception {
        CipherPBEThread PBEWITHSHAAND3KEYTRIPLEDESCBC = new CipherPBEThread(
                "PBEWITHSHAAND3-KEYTRIPLEDES-CBC", new int[] {112, 168},
                new String[] {"CBC"}, new String[] {"PKCS5Padding"});

        PBEWITHSHAAND3KEYTRIPLEDESCBC.launcher();

        assertEquals(PBEWITHSHAAND3KEYTRIPLEDESCBC.getFailureMessages(), 0,
                PBEWITHSHAAND3KEYTRIPLEDESCBC.getTotalFailuresNumber());
    }

    // Supported, but not part of mandatory components for Android
    public void disabled_test_PBEWithSHA1And40BitRC2() throws Exception {
        CipherPBEThread PBEWithSHA1AndRC2_40 = new CipherPBEThread(
                "PBEWITHSHAAND40BITRC2-CBC", new int[] {40},
                new String[] {"CBC"}, new String[] {"PKCS5Padding"});

        PBEWithSHA1AndRC2_40.launcher();

        assertEquals(PBEWithSHA1AndRC2_40.getFailureMessages(), 0,
                PBEWithSHA1AndRC2_40.getTotalFailuresNumber());
    }


    // Not supported by Android
    public void disabled_test_PBEWithMD5AndTripleDES() throws Exception {
        CipherPBEThread PBEWithMD5AndTripleDES = new CipherPBEThread(
                "PBEWithMD5AndTripleDES", new int[] {112, 168},
                new String[] {"CBC"}, new String[] {"PKCS5Padding"});

        PBEWithMD5AndTripleDES.launcher();

        assertEquals(PBEWithMD5AndTripleDES.getFailureMessages(), 0,
                PBEWithMD5AndTripleDES.getTotalFailuresNumber());
    }

    // Not supported by Android
    public void disabled_test_PBEWithSHA1AndDESede() throws Exception {
        CipherPBEThread PBEWithSHA1AndDESede = new CipherPBEThread(
                "PBEWithSHA1AndDESede", new int[] {112, 168},
                new String[] {"CBC"}, new String[] {"PKCS5Padding"});

        PBEWithSHA1AndDESede.launcher();

        assertEquals(PBEWithSHA1AndDESede.getFailureMessages(), 0,
                PBEWithSHA1AndDESede.getTotalFailuresNumber());
    }

    // Not supported by Android
    public void disabled_test_PBEWithSHA1AndRC2_40() throws Exception {
        CipherPBEThread PBEWithSHA1AndRC2_40 = new CipherPBEThread(
                "PBEWithSHA1AndRC2_40", new int[] {40}, new String[] {"CBC"},
                new String[] {"PKCS5Padding"});

        PBEWithSHA1AndRC2_40.launcher();

        assertEquals(PBEWithSHA1AndRC2_40.getFailureMessages(), 0,
                PBEWithSHA1AndRC2_40.getTotalFailuresNumber());
    }
}
