/*
 * Copyright (C) 2007 The Android Open Source Project
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

package tests.api.java.lang;

import junit.framework.TestCase;

/**
 * Tests for the class {@link String}.
 */
public class StringTest extends TestCase {
    public void test_contains() {
        assertTrue("aabc".contains("abc"));
        assertTrue("abcd".contains("abc"));
        assertFalse("abcd".contains("cba"));
    }
    public void test_charAt() {
        assertTrue("abcd".charAt(0) == 'a');
        assertTrue("abcd".charAt(3) == 'd');
    }
    public void test_StartsWith() {
        assertTrue("abcd".startsWith("abc"));
        assertFalse("abcd".startsWith("aabc"));
    }
    public void test_EndsWith() {
        assertTrue("abcd".endsWith("bcd"));
        assertFalse("abcd".endsWith("bcde"));
    }

    public void test_CASE_INSENSITIVE_ORDER() {
        String  s1 = "ABCDEFG";
        String  s2 = "abcdefg";

        assertTrue(String.CASE_INSENSITIVE_ORDER.compare(s1, s2) == 0);
    }
}
