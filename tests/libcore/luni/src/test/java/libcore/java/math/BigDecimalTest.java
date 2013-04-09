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

package libcore.java.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import junit.framework.TestCase;

public final class BigDecimalTest extends TestCase {

    public void testGetPrecision() {
        assertPrecision(1, "0");
        assertPrecision(1, "0.9");
        assertPrecision(16, "0.9999999999999999");
        assertPrecision(16, "9999999999999999");
        assertPrecision(19, "1000000000000000000");
        assertPrecision(19, "1000000000000000001");
        assertPrecision(19, "-1000000000000000001");
        assertPrecision(19, "-1000000000000000000");

        String tenNines = "9999999999";
        String fiftyNines = tenNines + tenNines + tenNines + tenNines + tenNines;
        assertPrecision(10, "0." + tenNines);
        assertPrecision(50, "0." + fiftyNines);
        assertPrecision(250, "0." + fiftyNines + fiftyNines + fiftyNines + fiftyNines + fiftyNines);
        assertPrecision(10, tenNines);
        assertPrecision(50, fiftyNines);
        assertPrecision(250, fiftyNines + fiftyNines + fiftyNines + fiftyNines + fiftyNines);

        // test these special cases because we know precision() uses longs internally
        String maxLong = Long.toString(Long.MAX_VALUE);
        assertPrecision(maxLong.length(), maxLong);
        String minLong = Long.toString(Long.MIN_VALUE);
        assertPrecision(minLong.length() - 1, minLong);
    }

    private void assertPrecision(int expectedPrecision, String value) {
        BigDecimal parsed = new BigDecimal(value);
        assertEquals("Unexpected precision for parsed value " + value,
                expectedPrecision, parsed.precision());

        BigDecimal computed = parsed.divide(BigDecimal.ONE);
        assertEquals("Unexpected precision for computed value " + value,
                expectedPrecision, computed.precision());
    }

    public void testRound() {
        BigDecimal bigDecimal = new BigDecimal("0.999999999999999");
        BigDecimal rounded = bigDecimal.round(new MathContext(2, RoundingMode.FLOOR));
        assertEquals("0.99", rounded.toString());
    }
}
