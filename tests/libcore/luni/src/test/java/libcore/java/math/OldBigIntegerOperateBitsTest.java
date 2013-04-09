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

public class OldBigIntegerOperateBitsTest extends TestCase {


    /**
     * java.math.BigInteger#getLowestSetBit() getLowestSetBit for
     *        negative BigInteger
     */
    public void test_getLowestSetBitNeg() {
        byte aBytes[] = {
                -1, -128, 56, 100, -2, -76, 89, 45, 91, 3, -15, 35, 26
        };
        int aSign = -1;
        int iNumber = 1;
        BigInteger aNumber = new BigInteger(aSign, aBytes);
        int result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);
    }

    /**
     * java.math.BigInteger#getLowestSetBit() getLowestSetBit for
     *        positive BigInteger
     */
    public void test_getLowestSetBitPos() {
        byte aBytes[] = {
                -1, -128, 56, 100, -2, -76, 89, 45, 91, 3, -15, 35, 26
        };
        int aSign = 1;
        int iNumber = 1;
        BigInteger aNumber = new BigInteger(aSign, aBytes);
        int result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);

        byte[] aBytes_ = {
                127, 0, 3
        };
        iNumber = 0;
        aNumber = new BigInteger(aSign, aBytes_);
        result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);

        byte[] aBytes__ = {
                -128, 0, 0
        };
        iNumber = 23;
        aNumber = new BigInteger(aSign, aBytes__);
        result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);
    }

    /**
     * java.math.BigInteger#getLowestSetBit() getLowestSetBit for zero
     *        BigInteger
     */
    public void test_getLowestSetBitZero() {
        byte[] aBytes = {
            0
        };
        int aSign = 0;
        int iNumber = -1;
        BigInteger aNumber = new BigInteger(aSign, aBytes);
        int result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);

        byte[] aBytes_ = {
                0, 0, 0
        };
        iNumber = -1;
        aNumber = new BigInteger(aSign, aBytes_);
        result = aNumber.getLowestSetBit();
        assertTrue("incorrect value", result == iNumber);
    }

}
