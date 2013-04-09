/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package libcore.javax.crypto.spec;

import dalvik.annotation.BrokenTest;
import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;
import tests.security.AlgorithmParameterKeyAgreementHelper;
import tests.security.AlgorithmParametersTest;

public class AlgorithmParametersTestDH extends AlgorithmParametersTest {

    private static final byte[] P = new byte[] {
            (byte) 0x00, (byte) 0xB8, (byte) 0xA4, (byte) 0x06, (byte) 0x10,
            (byte) 0xA2, (byte) 0x8B, (byte) 0xD2, (byte) 0xC0, (byte) 0xB6,
            (byte) 0x87, (byte) 0xFF, (byte) 0x15, (byte) 0xBA, (byte) 0x18,
            (byte) 0xE9, (byte) 0x7D, (byte) 0x77, (byte) 0x9F, (byte) 0xAF,
            (byte) 0x6F, (byte) 0x0B, (byte) 0xA4, (byte) 0xB6, (byte) 0x2B,
            (byte) 0x35, (byte) 0xE2, (byte) 0x01, (byte) 0x66, (byte) 0x41,
            (byte) 0x05, (byte) 0xE7, (byte) 0x6A, (byte) 0x62, (byte) 0x19,
            (byte) 0x94, (byte) 0x18, (byte) 0x46, (byte) 0xBA, (byte) 0x60,
            (byte) 0x2E, (byte) 0x5A, (byte) 0x48, (byte) 0x6C, (byte) 0x4B,
            (byte) 0xBF, (byte) 0x8C, (byte) 0xBF, (byte) 0xB9, (byte) 0xEE,
            (byte) 0xCC, (byte) 0x35, (byte) 0x89, (byte) 0x18, (byte) 0x02,
            (byte) 0x18, (byte) 0xFE, (byte) 0xF4, (byte) 0x02, (byte) 0x3B,
            (byte) 0x5E, (byte) 0x8A, (byte) 0x42, (byte) 0xB3, (byte) 0x39};

    private static final byte[] Q = new byte[] {
            (byte) 0x00, (byte) 0x87, (byte) 0xE2, (byte) 0xD1, (byte) 0x8A,
            (byte) 0x23, (byte) 0x90, (byte) 0x3A, (byte) 0x0F, (byte) 0xC8,
            (byte) 0x38, (byte) 0xA8, (byte) 0x65, (byte) 0x35, (byte) 0x89,
            (byte) 0x4F, (byte) 0x4B, (byte) 0xB3, (byte) 0xBF, (byte) 0x18,
            (byte) 0x3C, (byte) 0x3B, (byte) 0xD8, (byte) 0x72, (byte) 0xC3,
            (byte) 0xCF, (byte) 0xC9, (byte) 0xA7, (byte) 0x39, (byte) 0x7E,
            (byte) 0x9C, (byte) 0x69, (byte) 0xDA, (byte) 0xDE, (byte) 0x8E,
            (byte) 0x96, (byte) 0x9D, (byte) 0x44, (byte) 0xC1, (byte) 0x1E,
            (byte) 0x58, (byte) 0xC7, (byte) 0xFC, (byte) 0x40, (byte) 0x1B,
            (byte) 0xE8, (byte) 0x23, (byte) 0xF3, (byte) 0x6B, (byte) 0x95,
            (byte) 0x68, (byte) 0x29, (byte) 0x93, (byte) 0x35, (byte) 0x05,
            (byte) 0xC5, (byte) 0xCB, (byte) 0xB8, (byte) 0x57, (byte) 0x8F,
            (byte) 0xB9, (byte) 0xC3, (byte) 0x36, (byte) 0x09, (byte) 0x51};

    private static final int l = 511;

    public AlgorithmParametersTestDH() {
        super("DH", new AlgorithmParameterKeyAgreementHelper("DH"),
                new DHParameterSpec(new BigInteger(P), new BigInteger(Q), l));

    }

    @BrokenTest("Suffers from DH slowness, disabling for now")
    public void testAlgorithmParameters() {
        super.testAlgorithmParameters();
    }

}
