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

import junit.framework.TestCase;

public final class ClassNotFoundExceptionTest extends TestCase {
    public void testIllegalName() throws Exception {
        try {
            // There is no such thing as an array of void.
            Class.forName("[V");
            fail();
        } catch (ClassNotFoundException ex) {
            assertEquals("[V", ex.getMessage());
        }
    }

    public void testValidName() throws Exception {
        try {
            Class.forName("blort.Zorch");
            fail();
        } catch (ClassNotFoundException ex) {
            assertEquals("blort.Zorch", ex.getMessage());
        }
    }

    public void testValidArrayName() throws Exception {
        try {
            Class.forName("[[Lblort.Zorch;");
            fail();
        } catch (ClassNotFoundException ex) {
            assertEquals("[[Lblort.Zorch;", ex.getMessage());
        }
    }
}
