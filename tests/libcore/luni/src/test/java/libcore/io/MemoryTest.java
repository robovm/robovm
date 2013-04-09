/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.io;

import dalvik.system.VMRuntime;
import junit.framework.TestCase;

public class MemoryTest extends TestCase {
    public void testSetIntArray() {
        int[] values = { 3, 7, 31, 127, 8191, 131071, 524287, 2147483647 };
        int[] swappedValues = new int[values.length];
        for (int i = 0; i < values.length; ++i) {
            swappedValues[i] = Integer.reverseBytes(values[i]);
        }

        int scale = 4;
        VMRuntime runtime = VMRuntime.getRuntime();
        byte[] array = (byte[]) runtime.newNonMovableArray(byte.class, scale * values.length);
        int ptr = (int) runtime.addressOf(array);

        // Regular copy.
        Memory.pokeIntArray(ptr, values, 0, values.length, false);
        assertIntsEqual(values, ptr, false);
        assertIntsEqual(swappedValues, ptr, true);

        // Swapped copy.
        Memory.pokeIntArray(ptr, values, 0, values.length, true);
        assertIntsEqual(values, ptr, true);
        assertIntsEqual(swappedValues, ptr, false);

        // Swapped copies of slices (to ensure we test non-zero offsets).
        for (int i = 0; i < values.length; ++i) {
            Memory.pokeIntArray(ptr + i * scale, values, i, 1, true);
        }
        assertIntsEqual(values, ptr, true);
        assertIntsEqual(swappedValues, ptr, false);
    }

    private void assertIntsEqual(int[] expectedValues, int ptr, boolean swap) {
        for (int i = 0; i < expectedValues.length; ++i) {
            assertEquals(expectedValues[i], Memory.peekInt(ptr + 4 * i, swap));
        }
    }

    public void testSetShortArray() {
        short[] values = { 0x0001, 0x0020, 0x0300, 0x4000 };
        short[] swappedValues = { 0x0100, 0x2000, 0x0003, 0x0040 };

        int scale = 2;
        VMRuntime runtime = VMRuntime.getRuntime();
        byte[] array = (byte[]) runtime.newNonMovableArray(byte.class, scale * values.length);
        int ptr = (int) runtime.addressOf(array);

        // Regular copy. Memset first so we start from a known state.
        Memory.pokeShortArray(ptr, values, 0, values.length, false);
        assertShortsEqual(values, ptr, false);
        assertShortsEqual(swappedValues, ptr, true);

        // Swapped copy.
        Memory.pokeShortArray(ptr, values, 0, values.length, true);
        assertShortsEqual(values, ptr, true);
        assertShortsEqual(swappedValues, ptr, false);

        // Swapped copies of slices (to ensure we test non-zero offsets).
        for (int i = 0; i < values.length; ++i) {
            Memory.pokeShortArray(ptr + i * scale, values, i, 1, true);
        }
        assertShortsEqual(values, ptr, true);
        assertShortsEqual(swappedValues, ptr, false);
    }

    private void assertShortsEqual(short[] expectedValues, int ptr, boolean swap) {
        for (int i = 0; i < expectedValues.length; ++i) {
            assertEquals(expectedValues[i], Memory.peekShort(ptr + 2 * i, swap));
        }
    }
}
