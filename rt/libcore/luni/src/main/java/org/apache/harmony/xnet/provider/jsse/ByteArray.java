/*
 * Copyright (C) 2011 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.util.Arrays;

/**
 * Byte array wrapper for hashtable use. Implements equals() and hashCode().
 */
final class ByteArray {
    private final byte[] bytes;
    private final int hashCode;

    ByteArray(byte[] bytes) {
        this.bytes = bytes;
        this.hashCode = Arrays.hashCode(bytes);
    }

    @Override public int hashCode() {
        return hashCode;
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof ByteArray)) {
            return false;
        }
        ByteArray lhs = (ByteArray) o;
        return Arrays.equals(bytes, lhs.bytes);
    }
}
