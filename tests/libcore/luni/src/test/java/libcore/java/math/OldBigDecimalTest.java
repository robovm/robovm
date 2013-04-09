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
import java.math.RoundingMode;

public class OldBigDecimalTest extends junit.framework.TestCase {
    BigInteger value = new BigInteger("12345908");

    /**
     * java.math.BigDecimal#BigDecimal(double)
     */
    public void test_ConstructorD() {
        //
        // These numbers have an exact representation as doubles:
        //
        BigDecimal big = new BigDecimal(123E04);
        assertTrue(
                "the BigDecimal value taking a double argument is not initialized properly",
                big.toString().equals("1230000"));
        big = new BigDecimal(123.375);
        assertTrue("init(D) failed for 123.375; became " + big,
                big.toString().equals("123.375") );
        big = new BigDecimal(Math.pow(2, -33));
        assertTrue("init(D) failed for 2^(-33) = 1.16415321826934814453125E-10; became " + big,
                big.toString().equals("1.16415321826934814453125E-10") );
        big = new BigDecimal(123456 * Math.pow(2, -33));
        assertTrue("init(D) failed for 123456 * 2^(-33) = 0.000014372169971466064453125; became " + big,
                big.toString().equals("0.000014372169971466064453125") );
        big = new BigDecimal(-123456 * Math.pow(2, -33));
        assertTrue("init(D) failed for 123456 * 2^(-33) = -0.000014372169971466064453125; became " + big,
                big.toString().equals("-0.000014372169971466064453125") );
    }

    /**
     * java.math.BigDecimal#BigDecimal(java.lang.String)
     */
    public void test_constructor_String_plus_exp() {
        /*
         * BigDecimal does not support a + sign in the exponent when converting
         * from a String.
         *
         * mc 081106: who says so?!?
         */
        BigDecimal bd;
        bd = new BigDecimal("+23e-0");
        assertEquals("incorrect value",  "23", bd.toString());
        bd = new BigDecimal("-23e+0");
        assertEquals("incorrect value",  "-23", bd.toString());
    }

    /**
     * java.math.BigDecimal#setScale(int, java.math.RoundingMode)
     */
    public void test_setScaleILjava_math_RoundingMode() {
        BigDecimal setScale1 = new BigDecimal(2.323E102);
        BigDecimal setScale2 = setScale1.setScale(4);
        assertTrue("the number 2.323E102 after setting scale is wrong",
                setScale2.scale() == 4);
        assertTrue("the representation of the number 2.323E102 is wrong",
                setScale2.doubleValue() == 2.323E102);

        setScale1 = new BigDecimal("-1.253E-12");
        setScale2 = setScale1.setScale(17, RoundingMode.CEILING);
        assertTrue("the scale of the number -1.253E-12 after setting scale is wrong",
                setScale2.scale() == 17);
        assertTrue(
                "the representation of the number -1.253E-12 after setting scale is wrong, " + setScale2.toString(),
                setScale2.toString().equals("-1.25300E-12"));

        // testing rounding Mode RoundingMode.CEILING
        setScale1 = new BigDecimal(value, 4);
        setScale2 = setScale1.setScale(1, RoundingMode.CEILING);
        assertTrue(
                "the number 1234.5908 after setting scale to 1/RoundingMode.CEILING is wrong",
                setScale2.toString().equals("1234.6") && setScale2.scale() == 1);
        BigDecimal setNeg = new BigDecimal(value.negate(), 4);
        setScale2 = setNeg.setScale(1, RoundingMode.CEILING);
        assertTrue(
                "the number -1234.5908 after setting scale to 1/RoundingMode.CEILING is wrong",
                setScale2.toString().equals("-1234.5")
                        && setScale2.scale() == 1);

        // testing rounding Mode RoundingMode.DOWN
        setScale2 = setNeg.setScale(1, RoundingMode.DOWN);
        assertTrue(
                "the number -1234.5908 after setting scale to 1/RoundingMode.DOWN is wrong",
                setScale2.toString().equals("-1234.5")
                        && setScale2.scale() == 1);
        setScale1 = new BigDecimal(value, 4);
        setScale2 = setScale1.setScale(1, RoundingMode.DOWN);
        assertTrue(
                "the number 1234.5908 after setting scale to 1/RoundingMode.DOWN is wrong",
                setScale2.toString().equals("1234.5") && setScale2.scale() == 1);

        // testing rounding Mode RoundingMode.FLOOR
        setScale2 = setScale1.setScale(1, RoundingMode.FLOOR);
        assertTrue(
                "the number 1234.5908 after setting scale to 1/RoundingMode.FLOOR is wrong",
                setScale2.toString().equals("1234.5") && setScale2.scale() == 1);
        setScale2 = setNeg.setScale(1, RoundingMode.FLOOR);
        assertTrue(
                "the number -1234.5908 after setting scale to 1/RoundingMode.FLOOR is wrong",
                setScale2.toString().equals("-1234.6")
                        && setScale2.scale() == 1);

        // testing rounding Mode RoundingMode.HALF_DOWN
        setScale2 = setScale1.setScale(3, RoundingMode.HALF_DOWN);
        assertTrue(
                "the number 1234.5908 after setting scale to 3/RoundingMode.HALF_DOWN is wrong",
                setScale2.toString().equals("1234.591")
                        && setScale2.scale() == 3);
        setScale1 = new BigDecimal(new BigInteger("12345000"), 5);
        setScale2 = setScale1.setScale(1, RoundingMode.HALF_DOWN);
        assertTrue(
                "the number 123.45908 after setting scale to 1/RoundingMode.HALF_DOWN is wrong",
                setScale2.toString().equals("123.4") && setScale2.scale() == 1);
        setScale2 = new BigDecimal("-1234.5000").setScale(0,
                RoundingMode.HALF_DOWN);
        assertTrue(
                "the number -1234.5908 after setting scale to 0/RoundingMode.HALF_DOWN is wrong",
                setScale2.toString().equals("-1234") && setScale2.scale() == 0);

        // testing rounding Mode RoundingMode.HALF_EVEN
        setScale1 = new BigDecimal(1.2345789D);
        setScale2 = setScale1.setScale(4, RoundingMode.HALF_EVEN);
        assertTrue(
                "the number 1.2345789 after setting scale to 4/RoundingMode.HALF_EVEN is wrong",
                setScale2.doubleValue() == 1.2346D && setScale2.scale() == 4);
        setNeg = new BigDecimal(-1.2335789D);
        setScale2 = setNeg.setScale(2, RoundingMode.HALF_EVEN);
        assertTrue(
                "the number -1.2335789 after setting scale to 2/RoundingMode.HALF_EVEN is wrong",
                setScale2.doubleValue() == -1.23D && setScale2.scale() == 2);
        setScale2 = new BigDecimal("1.2345000").setScale(3,
                RoundingMode.HALF_EVEN);
        assertTrue(
                "the number 1.2345789 after setting scale to 3/RoundingMode.HALF_EVEN is wrong",
                setScale2.doubleValue() == 1.234D && setScale2.scale() == 3);
        setScale2 = new BigDecimal("-1.2345000").setScale(3,
                RoundingMode.HALF_EVEN);
        assertTrue(
                "the number -1.2335789 after setting scale to 3/RoundingMode.HALF_EVEN is wrong",
                setScale2.doubleValue() == -1.234D && setScale2.scale() == 3);

        // testing rounding Mode RoundingMode.HALF_UP
        setScale1 = new BigDecimal("134567.34650");
        setScale2 = setScale1.setScale(3, RoundingMode.HALF_UP);
        assertTrue(
                "the number 134567.34658 after setting scale to 3/RoundingMode.HALF_UP is wrong",
                setScale2.toString().equals("134567.347")
                        && setScale2.scale() == 3);
        setNeg = new BigDecimal("-1234.4567");
        setScale2 = setNeg.setScale(0, RoundingMode.HALF_UP);
        assertTrue(
                "the number -1234.4567 after setting scale to 0/RoundingMode.HALF_UP is wrong",
                setScale2.toString().equals("-1234") && setScale2.scale() == 0);

        // testing rounding Mode RoundingMode.UNNECESSARY
        try {
            setScale1.setScale(3, RoundingMode.UNNECESSARY);
            fail("arithmetic Exception not caught for round unnecessary");
        } catch (ArithmeticException e) {
        }

        // testing rounding Mode RoundingMode.UP
        setScale1 = new BigDecimal("100000.374");
        setScale2 = setScale1.setScale(2, RoundingMode.UP);
        assertTrue(
                "the number 100000.374 after setting scale to 2/RoundingMode.UP is wrong",
                setScale2.toString().equals("100000.38")
                        && setScale2.scale() == 2);
        setNeg = new BigDecimal(-134.34589D);
        setScale2 = setNeg.setScale(2, RoundingMode.UP);
        assertTrue(
                "the number -134.34589 after setting scale to 2/RoundingMode.UP is wrong",
                setScale2.doubleValue() == -134.35D && setScale2.scale() == 2);

        // testing invalid rounding modes
        try {
            setScale2 = setScale1.setScale(0, -123);
            fail("IllegalArgumentException is not caught for wrong rounding mode");
        } catch (IllegalArgumentException e) {
        }
    }
}
