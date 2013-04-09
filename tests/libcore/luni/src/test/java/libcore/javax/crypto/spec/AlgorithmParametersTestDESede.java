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

import javax.crypto.spec.IvParameterSpec;
import tests.security.AlgorithmParameterSymmetricHelper;
import tests.security.AlgorithmParametersTest;

public class AlgorithmParametersTestDESede extends AlgorithmParametersTest {

    private static final byte[] parameterData = new byte[] {
        (byte) 0x04, (byte) 0x08, (byte) 0x68, (byte) 0xC8,
        (byte) 0xFF, (byte) 0x64, (byte) 0x72, (byte) 0xF5 };

    public AlgorithmParametersTestDESede() {
        super("DESede", new AlgorithmParameterSymmetricHelper("DESede", "CBC/PKCS5PADDING", 112), new IvParameterSpec(parameterData));
    }

}
