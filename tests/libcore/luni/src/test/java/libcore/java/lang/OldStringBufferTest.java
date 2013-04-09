/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package libcore.java.lang;

import junit.framework.TestCase;

public class OldStringBufferTest extends TestCase {

    StringBuffer testBuffer = new StringBuffer("This is a test buffer");

    public void test_deleteCharAtI() {
        try {
            testBuffer.deleteCharAt(testBuffer.length() + 1);
            fail("StringIndexOutOfBoundsException was not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.deleteCharAt(-1);
            fail("StringIndexOutOfBoundsException was not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_ensureCapacityI() {
        StringBuffer sb = new StringBuffer(10);
        sb.ensureCapacity(-2);
        assertEquals("Failed to increase capacity.", 10, sb.capacity());
    }

    public void test_getCharsII$CI() {
        StringBuffer buf2 = new StringBuffer("");
        try {
            buf2.getChars(-1, 0, new char[5], 2);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            buf2.getChars(0, -1, new char[5], 2);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            buf2.getChars(0, -1, new char[5], 2);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            buf2.getChars(2, 1, new char[5], 2);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            buf2.getChars(0, 6, new char[5], 2);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }

        try {
            buf2.getChars(0, 6, new char[10], 5);
            fail("IndexOutOfBoundsException is not thrown.");
        } catch (IndexOutOfBoundsException e) {
            //expected
        }
    }

    public void test_insertID() {
        try {
            testBuffer.insert(-1, Double.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, Double.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertIF() {
        try {
            testBuffer.insert(-1, Float.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, Float.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertII() {
        try {
            testBuffer.insert(-1, Integer.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, Integer.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertIJ() {
        try {
            testBuffer.insert(-1, Long.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, Long.MAX_VALUE);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertILjava_lang_Object() {
        Object obj1 = new Object();
        try {
            testBuffer.insert(-1, obj1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, obj1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertILjava_lang_String() {
        try {
            testBuffer.insert(-1, "");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(testBuffer.length() + 1, "");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_insertIZ() {
        try {
            testBuffer.insert(testBuffer.length() + 1, true);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.insert(-1, true);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_replaceIILjava_lang_String() {
        try {
            testBuffer.replace(-1, 0, "text");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.replace(0, -1, "text");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.replace(2, 1, "text");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }

        try {
            testBuffer.replace(testBuffer.length() + 1, testBuffer.length() + 1,
                    "text");
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException sioobe) {
            //expected
        }
    }

    public void test_setCharAtIC() {
        StringBuffer s = new StringBuffer("HelloWorld");
        try {
            s.setCharAt(-1, 'Z');
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
        try {
            s.setCharAt(s.length() + 1, 'Z');
            fail("IndexOutOfBoundsException is not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_substringI() {
        try {
            testBuffer.substring(testBuffer.length() + 1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException oobe) {
            //expected
        }

        try {
            testBuffer.substring(-1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException oobe) {
            //expected
        }
    }

    public void test_substringII() {
        try {
            testBuffer.substring(-1, testBuffer.length());
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException oobe) {
            //expected
        }

        try {
            testBuffer.substring(0, -1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException oobe) {
            //expected
        }

        try {
            testBuffer.substring(2, 1);
            fail("StringIndexOutOfBoundsException is not thrown.");
        } catch(StringIndexOutOfBoundsException oobe) {
            //expected
        }
    }

    public void test_subSequence() {
        assertEquals("Incorrect substring returned", " is",
                testBuffer.subSequence(4, 7));
        assertEquals("Incorrect substring returned", "test buffer",
                testBuffer.subSequence(10, 21));
        assertEquals("not identical", "This is a test buffer",
                testBuffer.subSequence(0, testBuffer.length()));

        try {
            testBuffer.subSequence(0, Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            testBuffer.subSequence(Integer.MAX_VALUE, testBuffer.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            testBuffer.subSequence(-1, testBuffer.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException ioobe) {
            //expected
        }
    }
}
