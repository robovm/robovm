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

import java.math.BigInteger;
import java.security.spec.DSAParameterSpec;
import tests.security.AlgorithmParameterSignatureHelper;
import tests.security.AlgorithmParametersTest;

public class AlgorithmParametersTestDSA extends AlgorithmParametersTest {

    private static final byte[] P = new byte[] {
            (byte) 0x00, (byte) 0xB9, (byte) 0x53, (byte) 0xA4, (byte) 0xBB,
            (byte) 0xC8, (byte) 0xFD, (byte) 0x94, (byte) 0x4B, (byte) 0xC0,
            (byte) 0xD4, (byte) 0x6B, (byte) 0xA9, (byte) 0xAB, (byte) 0x5A,
            (byte) 0x1E, (byte) 0x85, (byte) 0x7E, (byte) 0x87, (byte) 0x38,
            (byte) 0x79, (byte) 0x1C, (byte) 0xBF, (byte) 0xCF, (byte) 0x32,
            (byte) 0x5A, (byte) 0x45, (byte) 0xF8, (byte) 0xE4, (byte) 0x08,
            (byte) 0x28, (byte) 0xA4, (byte) 0x12, (byte) 0x8A, (byte) 0x9D,
            (byte) 0x06, (byte) 0x53, (byte) 0x1C, (byte) 0xAA, (byte) 0x6A,
            (byte) 0x21, (byte) 0xC1, (byte) 0x95, (byte) 0xF8, (byte) 0xAA,
            (byte) 0xB2, (byte) 0xB8, (byte) 0x43, (byte) 0x38, (byte) 0x86,
            (byte) 0x15, (byte) 0x94, (byte) 0xCF, (byte) 0x40, (byte) 0xA5,
            (byte) 0x0D, (byte) 0xF3, (byte) 0x9A, (byte) 0x49, (byte) 0x12,
            (byte) 0x72, (byte) 0x64, (byte) 0x11, (byte) 0xDD, (byte) 0x85};
    private static final byte[] Q = new byte[] {
            (byte) 0x00, (byte) 0xF8, (byte) 0x51, (byte) 0x6A, (byte) 0x92,
            (byte) 0xCB, (byte) 0x47, (byte) 0x95, (byte) 0x18, (byte) 0x1F,
            (byte) 0x7E, (byte) 0xD8, (byte) 0x71, (byte) 0x05, (byte) 0xB6,
            (byte) 0x26, (byte) 0x4D, (byte) 0x52, (byte) 0x94, (byte) 0xFA,
            (byte) 0x5D};
    private static final byte[] G = new byte[] {
            (byte) 0x2B, (byte) 0xF5, (byte) 0x91, (byte) 0x47, (byte) 0xC8,
            (byte) 0xF1, (byte) 0x79, (byte) 0x75, (byte) 0x2A, (byte) 0x8E,
            (byte) 0x40, (byte) 0x7E, (byte) 0xF5, (byte) 0xA5, (byte) 0x14,
            (byte) 0x98, (byte) 0x97, (byte) 0xE8, (byte) 0xC5, (byte) 0x5E,
            (byte) 0x7A, (byte) 0x39, (byte) 0xFE, (byte) 0x3B, (byte) 0x68,
            (byte) 0x06, (byte) 0x85, (byte) 0xD4, (byte) 0xDC, (byte) 0xA5,
            (byte) 0xF1, (byte) 0xC1, (byte) 0x82, (byte) 0x45, (byte) 0x98,
            (byte) 0xD3, (byte) 0x06, (byte) 0xE2, (byte) 0x4A, (byte) 0x45,
            (byte) 0xD7, (byte) 0xF5, (byte) 0x57, (byte) 0x18, (byte) 0x55,
            (byte) 0x05, (byte) 0xD4, (byte) 0x74, (byte) 0x6A, (byte) 0x9E,
            (byte) 0x2D, (byte) 0x42, (byte) 0xDF, (byte) 0xC2, (byte) 0x88,
            (byte) 0x95, (byte) 0x97, (byte) 0xA8, (byte) 0x7D, (byte) 0x11,
            (byte) 0x55, (byte) 0x9C, (byte) 0x7C, (byte) 0x9F};


    public AlgorithmParametersTestDSA() {
        super("DSA", new AlgorithmParameterSignatureHelper<DSAParameterSpec>(
                "DSA", DSAParameterSpec.class), new DSAParameterSpec(
                new BigInteger(P), new BigInteger(Q), new BigInteger(G)));
    }

}
