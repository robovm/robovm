/*
 * Copyright (C) 2007 The Android Open Source Project
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

package org.apache.harmony.security.tests.java.security;

import java.security.KeyRep;
import java.util.Arrays;

import junit.framework.TestCase;

public class KeyRepTypeTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * java.security.KeyRep.Type#valueOf(String)
     */
    public void testValueOf() {
        try {
            KeyRep.Type.valueOf("type");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            KeyRep.Type.valueOf(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
        assertEquals(KeyRep.Type.PRIVATE, KeyRep.Type
                .valueOf(KeyRep.Type.PRIVATE.toString()));
        assertEquals(KeyRep.Type.PUBLIC, KeyRep.Type.valueOf(KeyRep.Type.PUBLIC
                .toString()));
        assertEquals(KeyRep.Type.SECRET, KeyRep.Type.valueOf(KeyRep.Type.SECRET
                .toString()));
    }

    /**
     * java.security.KeyRep.Type#values()
     */
    public void testValues() {
        KeyRep.Type[] types = new KeyRep.Type[] { KeyRep.Type.SECRET,
                KeyRep.Type.PUBLIC, KeyRep.Type.PRIVATE };
        try {
            assertTrue(Arrays.equals(types, KeyRep.Type.values()));
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

}
