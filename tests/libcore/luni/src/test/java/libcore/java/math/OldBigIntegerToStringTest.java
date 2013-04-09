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

public class OldBigIntegerToStringTest extends TestCase {

    public void test_toString1() {

        String s = "0000000000";
        BigInteger bi = new BigInteger(s);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, "0");
    }

    public void test_toString2() {
        String s = "1234567890987654321";
        BigInteger bi = new BigInteger(s);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
    }

    public void test_toString3() {
        String s = "-1234567890987654321";
        BigInteger bi = new BigInteger(s);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
    }

    public void test_toString4() {
        String s = "12345678901234";
        long l = 12345678901234L;
        BigInteger bi = BigInteger.valueOf(l);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
    }

    public void test_toString5() {
        String s = "-12345678901234";
        long l = -12345678901234L;
        BigInteger bi = BigInteger.valueOf(l);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
    }

    public void test_toString() {
        byte aBytes[] = {
                12, 56, 100, -2, -76, 89, 45, 91, 3, -15, 35, 26, 3, 91
        };
        String s = "247856948764430159964673417020251";
        BigInteger bi = new BigInteger(aBytes);
        String sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
        byte aBytes_[] = {
                -12, 56, 100, -2, -76, 89, 45, 91, 3, -15, 35, 26, 3, 91
        };
        s = "-238920881723209930210060613844133";
        bi = new BigInteger(aBytes_);
        sBI = bi.toString();
        assertEquals("toString method returns incorrect value instead of " + s, sBI, s);
    }
}
