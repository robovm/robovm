/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package libcore.java.text;

import java.text.Bidi;
import junit.framework.TestCase;

public class OldBidiTest extends TestCase {

    Bidi bd;

    public void testToString() {
        try {
            bd = new Bidi("bidi", 173);
            assertNotNull("Bidi representation is null", bd.toString());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void testCreateLineBidi_AndroidFailure() {
        // This is a difference between ICU4C and the RI. ICU4C insists that 'limit' is strictly
        // greater than 'start'. We have to paper over this in our Java code.
        Bidi bidi = new Bidi("str", Bidi.DIRECTION_RIGHT_TO_LEFT);
        bidi.createLineBidi(2, 2);
    }

    public void testGetRunLevelLInt() {
        bd = new Bidi("text", Bidi.DIRECTION_LEFT_TO_RIGHT);
        try {
            assertEquals(0, bd.getRunLevel(0));
            assertEquals(0, bd.getRunLevel(bd.getRunCount()));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        bd = new Bidi("text", Bidi.DIRECTION_RIGHT_TO_LEFT);
        try {
            assertEquals(2, bd.getRunLevel(0));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        bd = new Bidi("text", Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
        try {
            assertEquals(0, bd.getRunLevel(0));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    public void testGetRunStart() {
        bd = new Bidi(new char[] { 's', 's', 's' }, 0, new byte[] { (byte) -7,
                (byte) -2, (byte) 3 }, 0, 3,
                Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        assertEquals(0, bd.getRunStart(0));
        assertEquals(1, bd.getRunStart(1));
        assertEquals(2, bd.getRunStart(2));

        String LTR = "\u0061\u0062";
        String RTL = "\u05DC\u05DD";
        String newLine = "\n";
        String defText = LTR + newLine + RTL + LTR + RTL;

        int[][] expectedRuns = { { 0, 3 }, { 3, 5 }, { 5, 7 }, { 7, 9 }, };

        Bidi bi = new Bidi(defText, 0);

        final int count = bi.getRunCount();
        for (int i = 0; i < count; i++) {
            assertEquals(expectedRuns[i][0], bi.getRunStart(i));
        }
    }
}
