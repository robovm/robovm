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

package libcore.java.lang;

public class IntegerTest extends junit.framework.TestCase {
    public void test_compare() throws Exception {
        final int min = Integer.MIN_VALUE;
        final int zero = 0;
        final int max = Integer.MAX_VALUE;
        assertTrue(Integer.compare(max,  max)  == 0);
        assertTrue(Integer.compare(min,  min)  == 0);
        assertTrue(Integer.compare(zero, zero) == 0);
        assertTrue(Integer.compare(max,  zero) > 0);
        assertTrue(Integer.compare(max,  min)  > 0);
        assertTrue(Integer.compare(zero, max)  < 0);
        assertTrue(Integer.compare(zero, min)  > 0);
        assertTrue(Integer.compare(min,  zero) < 0);
        assertTrue(Integer.compare(min,  max)  < 0);
    }
}
