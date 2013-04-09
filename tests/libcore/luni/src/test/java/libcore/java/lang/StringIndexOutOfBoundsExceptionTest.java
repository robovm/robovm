/*
 * Copyright (C) 2010 The Android Open Source Project
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

public final class StringIndexOutOfBoundsExceptionTest extends TestCase {
    public void testCharAt() throws Exception {
        try {
            "hello".charAt(-1);
            fail();
        } catch (StringIndexOutOfBoundsException ex) {
            assertEquals("length=5; index=-1", ex.getMessage());
        }

        try {
            "hello".charAt(7);
            fail();
        } catch (StringIndexOutOfBoundsException ex) {
            assertEquals("length=5; index=7", ex.getMessage());
        }
    }

    public void testSubstring() throws Exception {
        try {
            "hello there".substring(9,14);
            fail();
        } catch (StringIndexOutOfBoundsException ex) {
            assertEquals("length=11; regionStart=9; regionLength=5",
                    ex.getMessage());
        }
    }
}
