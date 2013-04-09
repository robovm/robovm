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

package libcore.java.util;

import java.io.InvalidObjectException;
import java.util.EnumSet;
import junit.framework.TestCase;
import libcore.util.SerializationTester;

public final class EnumSetTest extends TestCase {
    public void testSmallEnumSetSerialization() {
        assertTrue(Roshambo.values().length <= 64);
        String s = "aced0005737200246a6176612e7574696c2e456e756d5365742453657269616c"
                + "697a6174696f6e50726f78790507d3db7654cad10200024c000b656c656d656e7"
                + "4547970657400114c6a6176612f6c616e672f436c6173733b5b0008656c656d65"
                + "6e74737400115b4c6a6176612f6c616e672f456e756d3b7870767200266c69626"
                + "36f72652e6a6176612e7574696c2e456e756d5365745465737424526f7368616d"
                + "626f00000000000000001200007872000e6a6176612e6c616e672e456e756d000"
                + "00000000000001200007870757200115b4c6a6176612e6c616e672e456e756d3b"
                + "a88dea2d33d22f980200007870000000027e71007e0004740004524f434b7e710"
                + "07e000474000853434953534f5253";
        EnumSet<Roshambo> set = EnumSet.of(Roshambo.ROCK, Roshambo.SCISSORS);
        new SerializationTester<EnumSet<Roshambo>>(set, s).test();
    }

    public void testLargeEnumSetSerialization() {
        assertTrue(Element.values().length > 64);
        String s = "aced0005737200246a6176612e7574696c2e456e756d5365742453657269616c"
                + "697a6174696f6e50726f78790507d3db7654cad10200024c000b656c656d656e7"
                + "4547970657400114c6a6176612f6c616e672f436c6173733b5b0008656c656d65"
                + "6e74737400115b4c6a6176612f6c616e672f456e756d3b7870767200256c69626"
                + "36f72652e6a6176612e7574696c2e456e756d5365745465737424456c656d656e"
                + "7400000000000000001200007872000e6a6176612e6c616e672e456e756d00000"
                + "000000000001200007870757200115b4c6a6176612e6c616e672e456e756d3ba8"
                + "8dea2d33d22f980200007870000000047e71007e0004740001487e71007e00047"
                + "4000254427e71007e000474000244597e71007e000474000355554f";
        EnumSet<Element> set = EnumSet.of(Element.H, Element.TB, Element.DY, Element.UUO);
        new SerializationTester<EnumSet<Element>>(set, s).test();
    }

    public void testDeserializeRemovedValue() throws Exception {
        /*
         * enum Roshambo {
         *     ROCK, PAPER, SCISSORS, LIZARD, SPOCK
         * }
         * EnumSet<Roshambo> set = EnumSet.of(Roshambo.SPOCK);
         */
        String s = "aced0005737200246a6176612e7574696c2e456e756d5365742453657269616c"
                + "697a6174696f6e50726f78790507d3db7654cad10200024c000b656c656d656e7"
                + "4547970657400114c6a6176612f6c616e672f436c6173733b5b0008656c656d65"
                + "6e74737400115b4c6a6176612f6c616e672f456e756d3b7870767200266c69626"
                + "36f72652e6a6176612e7574696c2e456e756d5365745465737424526f7368616d"
                + "626f00000000000000001200007872000e6a6176612e6c616e672e456e756d000"
                + "00000000000001200007870757200115b4c6a6176612e6c616e672e456e756d3b"
                + "a88dea2d33d22f980200007870000000017e71007e000474000553504f434b";
        try {
            SerializationTester.deserializeHex(s);
            fail();
        } catch (InvalidObjectException expected) {
        }
    }

    public void testDeserializeShuffledOrdinals() throws Exception {
        /*
         * enum Roshambo {
         *     SCISSORS, ROCK, PAPER
         * }
         * EnumSet<Roshambo> set = EnumSet.of(Roshambo.SCISSORS, Roshambo.ROCK)
         */
        String s = "aced0005737200246a6176612e7574696c2e456e756d5365742453657269616c"
                + "697a6174696f6e50726f78790507d3db7654cad10200024c000b656c656d656e7"
                + "4547970657400114c6a6176612f6c616e672f436c6173733b5b0008656c656d65"
                + "6e74737400115b4c6a6176612f6c616e672f456e756d3b7870767200266c69626"
                + "36f72652e6a6176612e7574696c2e456e756d5365745465737424526f7368616d"
                + "626f00000000000000001200007872000e6a6176612e6c616e672e456e756d000"
                + "00000000000001200007870757200115b4c6a6176612e6c616e672e456e756d3b"
                + "a88dea2d33d22f980200007870000000027e71007e000474000853434953534f5"
                + "2537e71007e0004740004524f434b";
        assertEquals(EnumSet.of(Roshambo.ROCK, Roshambo.SCISSORS),
                SerializationTester.deserializeHex(s));
    }

    enum Roshambo {
        ROCK, PAPER, SCISSORS
    }

    enum Element {
        H, HE, LI, BE, B, C, N, O, F, NE, NA, MG, AL, SI, P, S, CL, AR, K, CA, SC, TI, V, CR, MN,
        FE, CO, NI, CU, ZN, GA, GE, AS, SE, BR, KR, RB, SR, Y, ZR, NB, MO, TC, RU, RH, PD, AG, CD,
        IN, SN, SB, TE, I, XE, CS, BA, LA, CE, PR, ND, PM, SM, EU, GD, TB, DY, HO, ER, TM, YB, LU,
        HF, TA, W, RE, OS, IR, PT, AU, HG, TL, PB, BI, PO, AT, RN, FR, RA, AC, TH, PA, U, NP, PU,
        AM, CM, BK, CF, ES, FM, MD, NO, LR, RF, DB, SG, BH, HS, MT, DS, RG, CN, UUT, UUQ, UUP, UUH,
        UUS, UUO
    }
}
