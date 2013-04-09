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
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import junit.framework.TestCase;

public class OldBigDecimalCompareTest extends TestCase {

    public void testAbsMathContextNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        MathContext mc = new MathContext(34, RoundingMode.UP);
        assertEquals("incorrect value", "1.238096483923847545735673567457357E+53", aNumber.abs(mc).toString());

        mc = new MathContext(34, RoundingMode.DOWN);
        assertEquals("incorrect value", "1.238096483923847545735673567457356E+53", aNumber.abs(mc).toString());

        mc = new MathContext(34, RoundingMode.FLOOR);
        assertEquals("incorrect value", "1.238096483923847545735673567457356E+53", aNumber.abs(mc).toString());

        mc = new MathContext(34, RoundingMode.CEILING);
        assertEquals("incorrect value", "1.238096483923847545735673567457357E+53", aNumber.abs(mc).toString());

        mc = new MathContext(34, RoundingMode.UNNECESSARY);
        try {
            aNumber.abs(mc);
            fail("No ArithmeticException for RoundingMode.UNNECESSARY");
        } catch (ArithmeticException expected) {
        }
    }

    public void testNegateMathContextPositive() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       MathContext mc = new MathContext(37, RoundingMode.FLOOR);
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), 41);
       BigDecimal res = aNumber.negate(mc);
       assertEquals("incorrect value", "-929487820944884782312124789.8748298843", res.toString());
       assertEquals("incorrect scale", 10, res.scale());
    }
}
