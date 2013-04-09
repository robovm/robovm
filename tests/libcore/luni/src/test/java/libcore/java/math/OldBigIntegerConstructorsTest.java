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

import java.math.BigInteger;
import junit.framework.TestCase;

public class OldBigIntegerConstructorsTest extends TestCase {

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrString1() {
        String s = "0";
        BigInteger bi_s = new BigInteger(s);
        assertTrue("the BigInteger value is not initialized properly", bi_s.intValue() == 0);
        assertEquals("the BigInteger value is not initialized properly", bi_s.toString(), s);
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrString2() {
        String s = "-2147483648";
        BigInteger bi_s = new BigInteger(s);
        assertTrue("the BigInteger value is not initialized properly",
                bi_s.intValue() == Integer.MIN_VALUE);
        assertEquals("the BigInteger value is not initialized properly", bi_s.toString(), s);
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrString3() {
        String s = "2147483647";
        BigInteger bi_s = new BigInteger(s);
        assertTrue("the BigInteger value is not initialized properly",
                bi_s.intValue() == Integer.MAX_VALUE);
        assertEquals("the BigInteger value is not initialized properly", bi_s.toString(), s);
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrStringExc1() {
        try {
            new BigInteger("01234 56");
            fail("NumberFormatException has not been caught");
        } catch (NumberFormatException e) {
        }
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrStringExc2() {
        try {
            new BigInteger("1234#56");
            fail("NumberFormatException has not been caught");
        } catch (NumberFormatException e) {
        }
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrStringExc3() {
        try {
            new BigInteger("1234.56");
            fail("NumberFormatException has not been caught");
        } catch (NumberFormatException e) {
        }
    }

    /**
     * java.math.BigInteger#BigInteger(java.lang.String)
     */
    public void test_ConstrStringExc4() {
        try {
            new BigInteger("1E+1");
            fail("NumberFormatException has not been caught");
        } catch (NumberFormatException e) {
        }
    }
}
