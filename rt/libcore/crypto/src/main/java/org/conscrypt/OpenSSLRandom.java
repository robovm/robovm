/*
 * Copyright (C) 2012 The Android Open Source Project
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

package org.conscrypt;

import java.io.Serializable;
import java.security.SecureRandomSpi;

public class OpenSSLRandom extends SecureRandomSpi implements Serializable {
    private static final long serialVersionUID = 8506210602917522860L;

    @Override
    protected void engineSetSeed(byte[] seed) {
        NativeCrypto.RAND_seed(seed);
    }

    @Override
    protected void engineNextBytes(byte[] bytes) {
        NativeCrypto.RAND_bytes(bytes);
    }

    @Override
    protected byte[] engineGenerateSeed(int numBytes) {
        byte[] output = new byte[numBytes];
        NativeCrypto.RAND_bytes(output);
        return output;
    }
}
