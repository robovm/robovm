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

public class OldDoubleTest extends TestCase {

    public void test_byteValue() {
        Double d = new Double(Byte.MAX_VALUE);
        assertEquals("Returned incorrect byte value", Byte.MAX_VALUE, d.byteValue());
        d= new Double(Byte.MIN_VALUE);
        assertEquals("Returned incorrect byte value", Byte.MIN_VALUE, d.byteValue());
        d= new Double(Double.MAX_VALUE);
        assertEquals("Returned incorrect byte value", -1, d.byteValue());
    }

    public void test_doubleToLongBitsD() {
        assertEquals(0x7ff8000000000000L, Double.doubleToLongBits(Double.NaN));
        assertEquals(0x7ff0000000000000L, Double.doubleToLongBits(Double.POSITIVE_INFINITY));
        assertEquals(0xfff0000000000000L, Double.doubleToLongBits(Double.NEGATIVE_INFINITY));
    }

    public void test_doubleToRawLongBitsD() {
        assertEquals(0x7ff8000000000000L, Double.doubleToLongBits(Double.NaN));
        assertEquals(0x7ff0000000000000L, Double.doubleToLongBits(Double.POSITIVE_INFINITY));
        assertEquals(0xfff0000000000000L, Double.doubleToLongBits(Double.NEGATIVE_INFINITY));
    }

    public void test_doubleValue() {
        assertEquals(Double.POSITIVE_INFINITY, new Double("1.7976931348623159E308").doubleValue());
        assertEquals(Double.NEGATIVE_INFINITY, new Double("-1.7976931348623159E308").doubleValue());
        assertEquals(Double.MAX_VALUE, new Double("1.7976931348623157E308").doubleValue());
        assertEquals(Double.MIN_VALUE, new Double("4.9E-324").doubleValue());
    }

    public void test_floatValue() {
        assertEquals(Float.POSITIVE_INFINITY, new Double("3.4028236E38").floatValue());
        assertEquals(Float.NEGATIVE_INFINITY, new Double("-3.4028236E38").floatValue());
        assertEquals(Float.MAX_VALUE, new Double("3.4028235E38").floatValue());
        assertEquals(Float.MIN_VALUE, new Double("1.4E-45").floatValue());
    }

    public void test_intValue() {
        assertEquals("Returned incorrect int value", Integer.MAX_VALUE,
                                            new Double(2147483648d).intValue());
        assertEquals("Returned incorrect int value", Integer.MIN_VALUE,
                                           new Double(-2147483649d).intValue());
    }

    public void test_isNaND() {
        assertFalse("Doesn't return false value", Double.isNaN(new Double(Double.MAX_VALUE)));
    }

    // Regression test for hotfix in native code of double parser.
    public void test_parseDouble_LString_AndroidRegression() {
        // Android regression test
        long startTime = System.currentTimeMillis();
        double actual = Double.parseDouble("9e551027");
        assertTrue("parsing double 9e551027 took too long.",
                (System.currentTimeMillis() - startTime) < 1000);
        assertEquals("Returned incorrect value", Double.POSITIVE_INFINITY,
                actual, 0.0D);
    }
}
