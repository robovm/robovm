/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package libcore.java.lang;

import junit.framework.TestCase;

public class OldFloatTest extends TestCase {

    public void test_ConstructorLjava_lang_String() {
        try {
            new Float("900.89ff");
            fail("NumberFormatException is not thrown.");
        } catch(NumberFormatException nfe) {
           //expected
        }
    }

    public void test_ConstructorD() {
        Float f = new Float(Double.MAX_VALUE);
        assertTrue("Created incorrect float", f.floatValue() == Float.POSITIVE_INFINITY);
    }

    public void test_parseFloatLExceptions() {
        String [] incorrectStrings = {"", ";", "99999999EE999999", "99999l",
                 "0x1.f.ffffep127"};
        for(int i = 0; i < incorrectStrings.length; i++) {
            try {
                Float.parseFloat(incorrectStrings[i]);
                fail("NumberFormatException is not thrown for string: "
                        + incorrectStrings[i]);
            } catch(NumberFormatException nfe) {
                //expected
            }
        }
    }

    public void test_floatToIntBitsF() {
        assertEquals(0x7f800000, Float.floatToIntBits(Float.POSITIVE_INFINITY));
        assertEquals(0xff800000, Float.floatToIntBits(Float.NEGATIVE_INFINITY));
        assertEquals(0x7fc00000, Float.floatToIntBits(Float.NaN));
    }

    public void test_floatToRawIntBitsF() {
        assertEquals(0x7f800000, Float.floatToRawIntBits(Float.POSITIVE_INFINITY));
        assertEquals(0xff800000, Float.floatToRawIntBits(Float.NEGATIVE_INFINITY));
        assertEquals(0x7fc00000, Float.floatToRawIntBits(Float.NaN));
    }

    public void test_hashCode() {
        assertTrue(new Float(Float.MAX_VALUE).hashCode() != new Float(Float.MIN_VALUE).hashCode());
    }

    public void test_intBitsToFloatI() {
        assertEquals(Float.POSITIVE_INFINITY, Float.intBitsToFloat(0x7f800000));
        assertEquals(Float.NEGATIVE_INFINITY, Float.intBitsToFloat(0xff800000));

        assertEquals(Float.NaN, Float.intBitsToFloat(0x7f800001));
        assertEquals(Float.NaN, Float.intBitsToFloat(0x7fffffff));
        assertEquals(Float.NaN, Float.intBitsToFloat(0xff800001));
        assertEquals(Float.NaN, Float.intBitsToFloat(0xffffffff));
    }

    public void test_intValue() {
        assertEquals(Integer.MAX_VALUE, new Float(Float.MAX_VALUE).intValue());
        assertEquals(0, new Float(Float.MIN_VALUE).intValue());
    }

    public void test_isNaNF() {
        assertFalse(Float.isNaN(12.09f));
        assertFalse(Float.isNaN(Float.MAX_VALUE));
        assertFalse(Float.isNaN(Float.MIN_VALUE));
    }

    public void test_longValue() {
        assertEquals(Long.MAX_VALUE, new Float(Float.MAX_VALUE).longValue());
        assertEquals(0, new Float(Float.MIN_VALUE).longValue());
    }
}
