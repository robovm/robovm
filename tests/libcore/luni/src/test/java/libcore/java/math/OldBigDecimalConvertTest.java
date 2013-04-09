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
/**
 * @author Elena Semukhina
 * @version $Revision$
 */

package libcore.java.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import junit.framework.TestCase;

public class OldBigDecimalConvertTest extends TestCase {

    public void test_IntValueExactNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.intValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_IntValueExactPos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.intValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_IntValueExactFloatNeg() {
        BigDecimal aNumber = new BigDecimal("-2147483647.999");
        try {
            aNumber.intValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_IntValueExactFloatPos() {
        float a = 2147483646.99999F;
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.intValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_IntValueExactLongPos() {
        long a = 2147483647L;
        BigDecimal aNumber = new BigDecimal(a);
        int iNumber = aNumber.intValueExact();
        assertTrue("incorrect value", iNumber == a);
    }

    public void test_IntValueExactLongNeg() {
        long a = -2147483648L;
        BigDecimal aNumber = new BigDecimal(a);
        int iNumber = aNumber.intValueExact();
        assertTrue("incorrect value", iNumber == a);
    }

    public void test_LongValueExactNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.longValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling longValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_LongValueExactPos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.longValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling longValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_LongValueExactFloatNeg() {
        BigDecimal aNumber = new BigDecimal("-9223372036854775807.99999");
        try {
            aNumber.longValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling longValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    /**
     * java.math.BigDecimal#longValueExact() Long value of a positive
     *        BigDecimal
     */
    public void test_LongValueExactFloatPos() {
        float a = 9223372036854775806.99999F;
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.longValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling longValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_ByteValueExactPos() {
        int i = 127;
        BigDecimal bdNumber = new BigDecimal(i);
        byte bNumber = bdNumber.byteValueExact();
        assertTrue("incorrect byteValueExact", i == bNumber);
    }

    public void test_ByteValueExactNeg() {
        String sNumber = "-127.56789";
        int iNumber = -128;
        int iPresition = 3;
        MathContext mc = new MathContext(iPresition, RoundingMode.UP);
        BigDecimal bdNumber = new BigDecimal(sNumber, mc);
        byte bNumber = bdNumber.byteValueExact();
        assertTrue("incorrect byteValueExact", iNumber == bNumber);
    }

    public void test_ByteValueExactCharZero() {
        char[] cNumber = {
                '-', '0', '.', '0'
        };
        int iNumber = 0;
        int iPresition = 5;
        MathContext mc = new MathContext(iPresition, RoundingMode.HALF_DOWN);
        BigDecimal bdNumber = new BigDecimal(cNumber, mc);
        byte bNumber = bdNumber.byteValueExact();
        assertTrue("incorrect byteValueExact", iNumber == bNumber);
    }

    public void test_ByteValueExactStringZero() {
        String sNumber = "00000000000000";
        int iNumber = 0;
        int iPresition = 0;
        MathContext mc = new MathContext(iPresition, RoundingMode.HALF_UP);
        BigDecimal bdNumber = new BigDecimal(sNumber, mc);
        byte bNumber = bdNumber.byteValueExact();
        assertTrue("incorrect byteValueExact", iNumber == bNumber);
    }

    public void test_ByteValueExactDoubleMax() {
        double dNumber = Double.MAX_VALUE;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValueExactDoubleMin() {
        double dNumber = Double.MIN_VALUE;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValueExactFloatPos() {
        float fNumber = 123.5445F;
        BigDecimal bdNumber = new BigDecimal(fNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValueExactFloatNeg() {
        float fNumber = -12.987654321F;
        BigDecimal bdNumber = new BigDecimal(fNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValueExactDouble() {
        double dNumber = 123.0000D;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        byte bNumber = bdNumber.byteValueExact();
        assertTrue("incorrect byteValueExact", dNumber == bNumber);
    }

    public void test_ByteValueExactLongMin() {
        long lNumber = Long.MIN_VALUE;
        BigDecimal bdNumber = new BigDecimal(lNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValueExactIntMax() {
        int iNumber = Integer.MAX_VALUE;
        BigDecimal bdNumber = new BigDecimal(iNumber);
        try {
            bdNumber.byteValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling byteValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }

    public void test_ByteValuePos() {
        int i = 127;
        BigDecimal bdNumber = new BigDecimal(i);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", i == bNumber);
    }

    /**
     * @test java.math.BigDecimal#byteValue() Convert negative BigDesimal to
     *       byte type
     */
    public void test_ByteValueNeg() {
        String sNumber = "-127.56789";
        int iNumber = -128;
        int iPresition = 3;
        MathContext mc = new MathContext(iPresition, RoundingMode.UP);
        BigDecimal bdNumber = new BigDecimal(sNumber, mc);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValueExact", iNumber == bNumber);
    }

    public void test_ByteValueCharZero() {
        char[] cNumber = {
                '-', '0', '.', '0'
        };
        int iNumber = 0;
        int iPresition = 0;
        MathContext mc = new MathContext(iPresition, RoundingMode.HALF_UP);
        BigDecimal bdNumber = new BigDecimal(cNumber, mc);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", iNumber == bNumber);
    }

    public void test_ByteValueStringZero() {
        String sNumber = "00000";
        int iNumber = 0;
        int iPresition = 0;
        MathContext mc = new MathContext(iPresition, RoundingMode.HALF_UP);
        BigDecimal bdNumber = new BigDecimal(sNumber, mc);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", iNumber == bNumber);
    }

    public void test_ByteValueDoubleMax() {
        double dNumber = Double.MAX_VALUE;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        int result = 0;
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == result);
    }

    public void test_ByteValueDoubleMin() {
        double dNumber = Double.MIN_VALUE;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        int result = 0;
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == result);
    }

    public void test_ByteValueFloatNeg() {
        float fNumber = -12.987654321F;
        byte bValue = -12;
        BigDecimal bdNumber = new BigDecimal(fNumber);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == bValue);
    }

    public void test_ByteValueDouble() {
        double dNumber = 123.0000D;
        BigDecimal bdNumber = new BigDecimal(dNumber);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", dNumber == bNumber);
    }

    public void test_ByteValueLongMin() {
        long lNumber = Long.MIN_VALUE;
        int result = 0;
        BigDecimal bdNumber = new BigDecimal(lNumber);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == result);
    }

    public void test_ByteValueIntMin() {
        int iNumber = Integer.MIN_VALUE;
        int result = 0;
        BigDecimal bdNumber = new BigDecimal(iNumber);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == result);
    }

    public void test_ByteValueIntMax() {
        int iNumber = Integer.MAX_VALUE;
        int result = -1;
        BigDecimal bdNumber = new BigDecimal(iNumber);
        byte bNumber = bdNumber.byteValue();
        assertTrue("incorrect byteValue", bNumber == result);
    }

    public void test_ShortValueNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        int result = 23449;
        assertTrue("incorrect value", aNumber.shortValue() == result);
    }

    public void test_ShortValuePos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        int result = -23449;
        assertTrue("incorrect value", aNumber.shortValue() == result);
    }

    public void test_ShortValueExactNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.shortValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_ShortValueExactPos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.shortValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_ShortValueExactFloatNeg() {
        BigDecimal aNumber = new BigDecimal("-32766.99999");
        try {
            aNumber.shortValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_ShortValueExactFloatPos() {
        float a = 32767.99999F;
        BigDecimal aNumber = new BigDecimal(a);
        try {
            aNumber.shortValueExact();
            fail("java.lang.ArithmeticException isn't thrown after calling intValueExact");
        } catch (java.lang.ArithmeticException ae) {
            // expected;
        }
    }

    public void test_ShortValueExactLongPos() {
        long a = 12345L;
        BigDecimal aNumber = new BigDecimal(a);
        short shNumber = aNumber.shortValueExact();
        assertTrue("incorrect value", shNumber == a);
    }

    public void test_ShortValueExactLongNeg() {
        long a = -12345L;
        BigDecimal aNumber = new BigDecimal(a);
        int iNumber = aNumber.shortValueExact();
        assertTrue("incorrect value", iNumber == a);
    }

    public void test_stripTrailingZerosZeros() {

        BigDecimal bdNumber = new BigDecimal("0000000");
        BigDecimal result = bdNumber.stripTrailingZeros();
        assertEquals("incorrect value", result.unscaledValue(), bdNumber.unscaledValue());
        assertTrue("incorrect value", result.scale() == 0);

        bdNumber = new BigDecimal(0);
        result = bdNumber.stripTrailingZeros();
        assertEquals("incorrect value", result.unscaledValue(), bdNumber.unscaledValue());
        assertTrue("incorrect value", result.scale() == 0);

        bdNumber = new BigDecimal(0.000000);
        result = bdNumber.stripTrailingZeros();
        assertEquals("incorrect value", result.unscaledValue(), bdNumber.unscaledValue());
        assertTrue("incorrect value", result.scale() == 0);
    }

    public void test_stripTrailingZeros() {
        String s = "00000000100000000100000000.000000000100000000";
        int iScale = 10;
        BigDecimal bdValue = new BigDecimal("1000000001000000000000000001");
        BigDecimal bdNumber = new BigDecimal(s);
        BigDecimal bdResult = bdNumber.stripTrailingZeros();
        assertEquals("incorrect value", bdResult.unscaledValue(), bdValue.unscaledValue());
        assertTrue("incorrect value", bdResult.scale() == iScale);

        s = "1000.0";
        iScale = -3;
        BigDecimal bd = new BigDecimal("1");
        bdNumber = new BigDecimal(s);
        bdResult = bdNumber.stripTrailingZeros();
        assertEquals("incorrect value", bdResult.unscaledValue(), bd.unscaledValue());
        assertTrue("incorrect value", bdResult.scale() == iScale);
    }
}
