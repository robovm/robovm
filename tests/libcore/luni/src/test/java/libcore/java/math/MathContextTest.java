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

package libcore.java.math;

import java.math.MathContext;
import java.math.RoundingMode;

public class MathContextTest extends junit.framework.TestCase {
    public void testConstructor() throws Exception {
        // All the rounding modes.
        for (RoundingMode rm : RoundingMode.values()) {
            MathContext mc = new MathContext("precision=1 roundingMode=" + rm);
            assertEquals(1, mc.getPrecision());
            assertEquals(rm, mc.getRoundingMode());
        }

        // A few precisions.
        for (int p = 0; p < 10; ++p) {
            MathContext mc = new MathContext("precision=" + p + " roundingMode=" + RoundingMode.UP);
            assertEquals(p, mc.getPrecision());
            assertEquals(RoundingMode.UP, mc.getRoundingMode());
        }

        // Case-sensitive keywords.
        new MathContext("precision=1 roundingMode=UP");
        try {
            new MathContext("Precision=1 roundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            new MathContext("precision=1 RoundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }

        // Case-sensitive rounding modes.
        try {
            new MathContext("precision=1 roundingMode=up");
            fail();
        } catch (IllegalArgumentException expected) {
        }

        // Exactly one space (U+0020).
        try {
            new MathContext("precision=1roundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            new MathContext("precision=1  roundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            new MathContext("precision=1\troundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }

        // No leading or trailing space.
        try {
            new MathContext(" precision=1 roundingMode=UP");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            new MathContext("precision=1 roundingMode=UP ");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
}
