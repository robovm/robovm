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

package libcore.java.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import junit.framework.TestCase;

public class OldBigDecimalArithmeticTest extends TestCase {

    public void testAddMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("123456789012345.678");
        b = new BigDecimal("100000000000000.009");
        assertEquals("incorrect value", "123456789012345.67",
                a.round(mc).toString());
        assertEquals("incorrect value", "100000000000000.00",
                b.round(mc).toString());
        assertEquals("incorrect value", "223456789012345.67",
                a.round(mc).add(b.round(mc)).toString());
        res = a.add(b, mc);
        assertEquals("incorrect value", "223456789012345.68", res.toString());

        mc = new MathContext(33, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("1000000000000000090000000000.0000005");
        res = a.add(b, mc);
        assertEquals("Incorrect value!", "2234567890123456879012345678.90124", res.toString());
        assertEquals("Incorrect scale!", 5, res.scale());
        assertEquals("Incorrect precision!", 33, res.precision());
    }

    public void testSubtractMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567.8");
        b = new BigDecimal("10000000000000000.9");
        assertEquals("incorrect value", "2345678901234567",
                a.round(mc).subtract(b.round(mc)).toString());
        res = a.subtract(b, mc);
        assertEquals("incorrect value", "2345678901234566.9", res.toString());
        assertEquals("Incorrect scale!", 1, res.scale());
        assertEquals("Incorrect precision!", 17, res.precision());

        mc = new MathContext(33, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("1000000000000000090000000000.0000005");
        res = a.subtract(b, mc);
        assertEquals("incorrect value", "234567890123456699012345678.901239", res.toString());
        assertEquals("Incorrect scale!", 6, res.scale());
        assertEquals("Incorrect precision!", 33, res.precision());
    }

    public void testMultiplyMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("92345678901234567.8");
        b = new BigDecimal("10000000000000000.9");
        res = a.round(mc).multiply(b.round(mc));
        assertEquals("incorrect value", "923456789012345670000000000000000", res.toString());
        res = res.round(mc);
        assertEquals("incorrect value", "9.2345678901234567E+32", res.toString());
        res = a.multiply(b, mc);
        assertEquals("incorrect value", "9.2345678901234576E+32", res.toString());
        assertEquals("Incorrect scale!", -16, res.scale());
        assertEquals("Incorrect precision!", 17, res.precision());
    }

    public void testPowNonTrivial() {
        BigDecimal a, b, res;

        a = new BigDecimal("100.9");
        try {
            res = a.pow(-1);
            fail("ArithmeticException is not thrown for negative exponent");
        } catch (ArithmeticException e) {
            // expected
        }
        try {
            res = a.pow(-103);
            fail("ArithmeticException is not thrown for negative exponent");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testPowMathContext() {
        String a = "123121247898748298842980";
        int aScale = 10;
        int exp = 10;
        String c = "8.0044E+130";
        int cScale = -126;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
        BigDecimal result = aNumber.pow(exp, mc);
        assertEquals("incorrect value", c, result.toString());
        assertEquals("incorrect scale", cScale, result.scale());
    }

    public void testPowMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(7, RoundingMode.FLOOR);
        a = new BigDecimal("1000000.9");
        assertEquals("incorrect value", "1.000000E+6000",
                a.round(mc).pow(1000).round(mc).toString());
        res = a.pow(1000, mc);
        assertEquals("incorrect value", "1.000900E+6000", res.toString());

        mc = new MathContext(4, RoundingMode.FLOOR);
        a = new BigDecimal("1000.9");
        assertEquals("incorrect value", "1.000E+3000",
                a.round(mc).pow(1000).round(mc).toString());
        res = a.pow(1000, mc);
        assertEquals("incorrect value", "2.458E+3000", res.toString());

        mc = new MathContext(2, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234");
        try {
            res = a.pow(-2, mc);
            fail("ArithmeticException is not thrown");
        } catch (ArithmeticException e) {
            // expected
        }

        a = new BigDecimal("100");
        mc = new MathContext(4, RoundingMode.UNNECESSARY);
        res = a.pow(-2, mc);
        assertEquals("incorrect value", "0.0001", res.toString());

        a = new BigDecimal("1000.9");
        try {
            mc = new MathContext(0, RoundingMode.FLOOR);
            res = a.pow(-1, mc);
            fail("ArithmeticException is not thrown for negative exponent and precision = 0");
        } catch (ArithmeticException e) {
            // expected
        }

        a = new BigDecimal("000.0001");
        try {
            mc = new MathContext(0, RoundingMode.FLOOR);
            res = a.pow(-1, mc);
            fail("ArithmeticException is not thrown for negative exponent and precision = 0");
        } catch (ArithmeticException e) {
            // expected
        }

        a = new BigDecimal("1E-400");
        mc = new MathContext(4, RoundingMode.UNNECESSARY);
        res = a.pow(-1, mc);
        assertEquals("incorrect value", "1E+400", res.toString());

//        Doesn't succeed against JDK of Sun!:
//        mc = new MathContext(3, RoundingMode.FLOOR);
//        a = new BigDecimal("100.9");
//        assertEquals("incorrect value", "1.00E+2000",
//                a.round(mc).pow(1000).round(mc).toString());
//        res = a.pow(1000).round(mc);
//        res = a.pow(1000, mc);
//        assertEquals("incorrect value", "7.783E+2003", res.toString());
    }

    public void testDivideINonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567E1234");
        b = new BigDecimal("1.23456789012345679");
        assertEquals("incorrect value", "1E+1250",
                a.round(mc).divide(b.round(mc)).toString());
        res = a.divide(b, BigDecimal.ROUND_FLOOR);
        assertEquals("incorrect value", "9.999999999999999E+1249", res.toString());

        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("6172839450617283945061728394.5061975");
        res = a.divide(b, BigDecimal.ROUND_UNNECESSARY);
        assertEquals("incorrect value", "0.2000000", res.toString());

        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("1000000000000000090000000000.0000005");
        try {
            res = a.divide(b, BigDecimal.ROUND_UNNECESSARY);
            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testDivideIINonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567E1234");
        b = new BigDecimal("1.23456789012345679");
        res = a.divide(b, -1220, BigDecimal.ROUND_FLOOR);
        assertEquals("incorrect value", "9.99999999999999927099999343899E+1249", res.toString());

        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("6172839450617283945061728394.5061975");
        res = a.divide(b, 1, BigDecimal.ROUND_UNNECESSARY);
        assertEquals("incorrect value", "0.2", res.toString());

        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("6172839450617283945061728394.5061975");
        try {
            res = a.divide(b, 0, BigDecimal.ROUND_UNNECESSARY);
            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    // Has a rounding problem. seems like the precision is
    // 1 too small and cuts off the last digit. also this test might
    // not be correct. The name implies that scale should be used.
    public void testDivideScaleRoundingModeNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567.1");
        b = new BigDecimal("12345678901234567.9");
        assertEquals("incorrect value", "1",
                a.round(mc).divide(b.round(mc)).toString());
        res = a.divide(b, mc);
        assertEquals("incorrect value", "0.99999999999999993", res.toString());

        mc = new MathContext(13, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("6172839450617283945061728394.5061975");
        res = a.divide(b, mc);
        assertEquals("incorrect value", "0.2", res.toString());

        mc = new MathContext(33, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("1000000000000000090000000000.0000005");
        try {
            res = a.divide(b, mc);
            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    // The same test and the same problem like testDivideScaleRoundingModeNonTrivial.
    public void testDivideMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

// FAILS AGAINST RI!:
//        mc = new MathContext(6, RoundingMode.FLOOR);
//        a = new BigDecimal("12345.1");
//        b = new BigDecimal("12345.9");
//        assertEquals("incorrect value", "1",
//                a.round(mc).divide(b.round(mc)).toString());
//        res = a.divide(b, mc);
//        assertEquals("incorrect value", "0.99993", res.toString());

        mc = new MathContext(5, RoundingMode.FLOOR);
        a = new BigDecimal("12345.1");
        b = new BigDecimal("12345.9");
        assertEquals("incorrect value", "1",
                a.round(mc).divide(b.round(mc)).toString());
        res = a.divide(b, mc);
        assertEquals("incorrect value", "0.99993", res.toString());

        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567.1");
        b = new BigDecimal("12345678901234567.9");
        assertEquals("incorrect value", "1",
                a.round(mc).divide(b.round(mc)).toString());
        res = a.divide(b, mc);
        assertEquals("incorrect value", "0.99999999999999993", res.toString());
        assertEquals("incorrect value", res.round(mc).toString(), res.toString());

        mc = new MathContext(13, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("6172839450617283945061728394.5061975");
        res = a.divide(b, mc);
        assertEquals("incorrect value", "0.2", res.toString());

        mc = new MathContext(33, RoundingMode.UNNECESSARY);
        a = new BigDecimal("1234567890123456789012345678.9012395");
        b = new BigDecimal("1000000000000000090000000000.0000005");
        try {
            res = a.divide(b, mc);
            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testDivideToIntegralValueByZero() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        int aScale = 45;
        String b = "0";
        int bScale = -70;
        String res = "277923185514690367474770683";
        int resScale = 0;
        String rem = "1.3032693871288309587558885943391070087960319452465789990E-15";
        int remScale = 70;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        try {
            BigDecimal result = aNumber.divideToIntegralValue(bNumber);
            fail("ArithmeticException not thrown for division by 0");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testDivideToIntegralValueMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        a = new BigDecimal("92345678901234567.8");
        b = new BigDecimal("43");
        res = a.multiply(b);
        assertEquals("incorrect value", "3970864192753086415.4", res.toString());

        mc = new MathContext(20, RoundingMode.DOWN);
        a = new BigDecimal("3970864192753086415.4");
        b = new BigDecimal("92345678901234567.8");
        b = new BigDecimal("92345678901234567.8001");
        assertEquals("incorrect value", "43",
                a.round(mc).divideToIntegralValue(b.round(mc)).toString());
        res = a.divideToIntegralValue(b, mc);
        assertEquals("incorrect value", "42", res.toString());

//        mc = new MathContext(1, RoundingMode.DOWN);
//        res = a.divideToIntegralValue(b, mc);
//        assertEquals("incorrect value", "42", res.toString());


        mc = new MathContext(17, RoundingMode.FLOOR);
        a = new BigDecimal("518518513851851830");
        b = new BigDecimal("12345678901234567.9");
        assertEquals("incorrect value", "42",
                a.round(mc).divideToIntegralValue(b.round(mc)).toString());
        res = a.divideToIntegralValue(b, mc);
        assertEquals("incorrect value", "41", res.toString());
    }

    /**
     * divideAndRemainder(BigDecimal)
     */
    public void testDivideAndRemainderByZero() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        int aScale = 45;
        String b = "0";
        int bScale = -70;
        String res = "277923185514690367474770683";
        int resScale = 0;
        String rem = "1.3032693871288309587558885943391070087960319452465789990E-15";
        int remScale = 70;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        try {
            BigDecimal result[] = aNumber.divideAndRemainder(bNumber);
            fail("ArithmeticException not thrown for division by 0");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testDivideAndRemainderMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res[];

        mc = new MathContext(13, RoundingMode.FLOOR);
        a = new BigDecimal("12345678901234567.1");
        b = new BigDecimal("12345678901234567.9");
        assertEquals("incorrect value", "0E+4",
                a.round(mc).divideAndRemainder(b.round(mc))[1].toString());
        res = a.divideAndRemainder(b, mc);
        assertEquals("incorrect value", "12345678901234567.1", res[1].toString());

        mc = new MathContext(1, RoundingMode.UNNECESSARY);
        a = new BigDecimal("6172839450617283945061728394.5061976");
        b = new BigDecimal("1234567890123456789012345678.9012395");
        res = a.divideAndRemainder(b, mc);
        assertEquals("incorrect value", "1E-7", res[1].toString());

        mc = new MathContext(3, RoundingMode.UNNECESSARY);
        a = new BigDecimal("6172839450617283945061728394.6000000");
        b = new BigDecimal("1234567890123456789012345678.9012395");
        try {
            res = a.divideAndRemainder(b, mc);
            assertEquals("incorrect value", "0.0938025", res[1].toString());
            assertEquals("incorrect value", "0.09", res[1].round(mc).toString());
//            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }
    public void testRemainderByZero() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        int aScale = 45;
        String b = "0";
        int bScale = -70;
        String res = "277923185514690367474770683";
        int resScale = 0;
        String rem = "1.3032693871288309587558885943391070087960319452465789990E-15";
        int remScale = 70;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        try {
            BigDecimal result = aNumber.remainder(bNumber);
            fail("ArithmeticException not thrown for division by 0");
        } catch (ArithmeticException e) {
            // expected
        }
    }

    public void testRemainderMathContextNonTrivial() {
        MathContext mc;
        BigDecimal a, b, res;

        mc = new MathContext(13, RoundingMode.DOWN);
        a = new BigDecimal("12345678901234567.1");
        b = new BigDecimal("12345678901234567.9");
        assertEquals("incorrect value", "0E+4",
                a.round(mc).divideAndRemainder(b.round(mc))[1].toString());
        res = a.remainder(b, mc);
        assertEquals("incorrect value", "12345678901234567.1", res.toString());

        mc = new MathContext(1, RoundingMode.UNNECESSARY);
        a = new BigDecimal("6172839450617283945061728394.5061976");
        b = new BigDecimal("1234567890123456789012345678.9012395");
        res = a.remainder(b, mc);
        assertEquals("incorrect value", "1E-7", res.toString());

        mc = new MathContext(3, RoundingMode.UNNECESSARY);
        a = new BigDecimal("6172839450617283945061728394.6000000");
        b = new BigDecimal("1234567890123456789012345678.9012395");
        try {
            res = a.remainder(b, mc);
            assertEquals("incorrect value", "0.0938025", res.toString());
            assertEquals("incorrect value", "0.09", res.round(mc).toString());
//            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
    }
    public void testRoundNonTrivial() {
        MathContext mc;
        String biStr = new String( "12345678901234567890123456789012345.0E+10");
        String nbiStr = new String("-12345678901234567890123456789012345.E+10");
        BigDecimal bd;

        mc = new MathContext(17, RoundingMode.FLOOR);
        bd = new BigDecimal(new BigInteger("123456789012345678"), 3, mc);
        assertEquals("incorrect value", "123456789012345.67", bd.toString());

        mc = new MathContext(31, RoundingMode.UP);
        bd = (new BigDecimal(biStr)).round(mc);
        assertEquals("incorrect value",  "1.234567890123456789012345678902E+44", bd.toString());
        bd = (new BigDecimal(nbiStr)).round(mc);
        assertEquals("incorrect value", "-1.234567890123456789012345678902E+44", bd.toString());

        mc = new MathContext(28, RoundingMode.DOWN);
        bd = (new BigDecimal(biStr)).round(mc);
        assertEquals("incorrect value",  "1.234567890123456789012345678E+44", bd.toString());
        bd = (new BigDecimal(nbiStr)).round(mc);
        assertEquals("incorrect value", "-1.234567890123456789012345678E+44", bd.toString());

        mc = new MathContext(33, RoundingMode.CEILING);
        bd = (new BigDecimal(biStr)).round(mc);
        assertEquals("incorrect value",  "1.23456789012345678901234567890124E+44", bd.toString());
        bd = (new BigDecimal(nbiStr)).round(mc);
        assertEquals("incorrect value", "-1.23456789012345678901234567890123E+44", bd.toString());

        mc = new MathContext(34, RoundingMode.UNNECESSARY);
        try {
            bd = (new BigDecimal(biStr)).round(mc);
            fail("No ArithmeticException for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }
        try {
            bd = (new BigDecimal(nbiStr)).round(mc);
            fail("No ArithmeticException for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException e) {
            // expected
        }

        mc = new MathContext(7, RoundingMode.FLOOR);
        bd = new BigDecimal("1000000.9", mc);
        assertEquals("incorrect value", "1000000", bd.toString());
    }
    /**
     * java.math.BigDecimal#add(java.math.BigDecimal)
     */
    public void test_addBigDecimal() {
        BigDecimal add1 = new BigDecimal("23.456");
        BigDecimal add2 = new BigDecimal("3849.235");
        BigDecimal sum = add1.add(add2);
        assertTrue("the sum of 23.456 + 3849.235 is wrong", sum.unscaledValue().toString().equals(
                "3872691")
                && sum.scale() == 3);
        assertTrue("the sum of 23.456 + 3849.235 is not printed correctly", sum.toString().equals(
                "3872.691"));
        BigDecimal add3 = new BigDecimal(12.34E02D);
        assertTrue("the sum of 23.456 + 12.34E02 is not printed correctly", (add1.add(add3))
                .toString().equals("1257.456"));
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.MathContext) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeUP() {
        String a = "-37361671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.UP;
        String c = "-1";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeDOWN() {
        String a = "-37361671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.DOWN;
        String c = "0";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeCEILING() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.CEILING;
        String c = "50000260373164286401361914";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeFLOOR() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.FLOOR;
        String c = "50000260373164286401361913";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeHALF_UP() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.HALF_UP;
        String c = "50000260373164286401361913";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeHALF_DOWN() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        int aScale = 5;
        String b = "74723342238476237823787879183470";
        int bScale = 15;
        int newScale = 7;
        RoundingMode rm = RoundingMode.HALF_DOWN;
        String c = "500002603731642864013619132621009722.1803810";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, newScale, rm);
        assertEquals("incorrect value", c, result.toString());
        assertEquals("incorrect scale", newScale, result.scale());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingModeHALF_EVEN() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.HALF_EVEN;
        String c = "50000260373164286401361913";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        BigDecimal result = aNumber.divide(bNumber, rm);
        assertEquals("incorrect value", c, result.toString());
    }

    /**
     * java.math.BigDecimal#divide(java.math.BigDecimal,
     *        java.math.RoundingMode) divide(BigDecimal, RoundingMode)
     */
    public void test_DivideBigDecimalRoundingExc() {
        String a = "3736186567876876578956958765675671119238118911893939591735";
        String b = "74723342238476237823787879183470";
        RoundingMode rm = RoundingMode.UNNECESSARY;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        BigDecimal bNumber = new BigDecimal(new BigInteger(b));
        try {
            aNumber.divide(bNumber, rm);
            fail("ArithmeticException is not thrown for RoundingMode.UNNECESSARY divider");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
        try {
            bNumber = new BigDecimal(0);
            aNumber.divide(bNumber, rm);
            fail("ArithmeticException is not thrown for zero divider");
        } catch (java.lang.ArithmeticException ae) {
            // expected
        }
    }
}
