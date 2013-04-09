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
import junit.framework.TestCase;

public class OldBigDecimalScaleOperationsTest extends TestCase {

    public void testMovePointRightEx() {
        BigDecimal a = new BigDecimal("12345.6789012345678901234567890123456789");
        BigDecimal res = a.movePointRight(10);
        assertEquals("incorrect scale", 24, res.scale());
        assertEquals("incorrect value", "123456789012345.678901234567890123456789", res.toString());
        res = a.movePointRight(-50);
        assertEquals("incorrect scale", 84, res.scale());
        assertEquals("incorrect value", "1.23456789012345678901234567890123456789E-46", res.toString());
        try {
            a.movePointRight(Integer.MIN_VALUE + 2);
            fail("ArithmeticException is not thrown");
        } catch (ArithmeticException expected) {
        }
    }

    // Throws OutOfMemoryError instead of ArithmeticException!
    public void testMovePointRightEx2() {
        BigDecimal a = new BigDecimal("123456789012345678901234567890123456789E25");
        try {
            a.movePointRight(Integer.MAX_VALUE - 2);
            fail("ArithmeticException is not thrown");
        } catch (ArithmeticException expected) {
        }
    }

    public void testScaleByPowerOfTenEx() {
        BigDecimal a = new BigDecimal("12345.6789012345678901234567890123456789");
        BigDecimal res = a.movePointRight(10);
        assertEquals("incorrect scale", 24, res.scale());
        assertEquals("incorrect value", "123456789012345.678901234567890123456789", res.toString());
        res = a.scaleByPowerOfTen(-50);
        assertEquals("incorrect scale", 84, res.scale());
        assertEquals("incorrect value", "1.23456789012345678901234567890123456789E-46", res.toString());
        res = a.scaleByPowerOfTen(50);
        assertEquals("incorrect scale", -16, res.scale());
        assertEquals("incorrect value", "1.23456789012345678901234567890123456789E+54", res.toString());
        try {
            a.scaleByPowerOfTen(Integer.MIN_VALUE + 2);
            fail("ArithmeticException is not thrown");
        } catch (ArithmeticException expected) {
        }
        a = new BigDecimal("123456789012345678901234567890123456789E25");
        try {
            a.scaleByPowerOfTen(Integer.MAX_VALUE - 2);
            fail("ArithmeticException is not thrown");
        } catch (ArithmeticException expected) {
        }
    }

    /**
     * check that setScale with a scale greater to the existing scale does not
     * change the value.
     */
    public void testSetScale() {
        BigDecimal x1 = new BigDecimal(1.23400);
        BigDecimal x2 = x1.setScale(75);

        assertEquals(0, x1.compareTo(x2));
        assertEquals(0, x2.compareTo(x1));

        x1.precision();

        assertEquals(0, x1.compareTo(x2));
        assertEquals(0, x2.compareTo(x1));

        x2.precision();

        assertEquals(0, x1.compareTo(x2));
        assertEquals(0, x2.compareTo(x1));
    }
}
