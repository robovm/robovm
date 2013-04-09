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

import java.text.CollationKey;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;


public class OldCollationKeyTest extends junit.framework.TestCase {

    public void test_toByteArray() {
        // Test for method byte [] java.text.CollationKey.toByteArray()
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);
        CollationKey key1 = collator.getCollationKey("abc");
        byte[] bytes = key1.toByteArray();
        assertTrue("Not enough bytes", bytes.length >= 3);

        try {
            collator = new RuleBasedCollator("= 1 , 2 ; 3 , 4 < 5 ; 6 , 7");
        } catch (ParseException e) {
            fail("ParseException");
            return;
        }
        /*
         * CollationElementIterator it =
         * ((RuleBasedCollator)collator).getCollationElementIterator("1234567");
         * int order; while ((order = it.next()) !=
         * CollationElementIterator.NULLORDER) {
         * System.out.println(Integer.toHexString(order)); } for (int i=0; i<bytes.length;
         * i+=2) { System.out.print(Integer.toHexString(bytes[i]) +
         * Integer.toHexString(bytes[i+1]) + " "); } System.out.println();
         */

        // The RI has a different algorithm to generate the collation keys.
        // bytes = collator.getCollationKey("1234567").toByteArray();
        // byte[] result = new byte[] { 0, 2, 0, 2, 0, 2, 0, 0, 0, 3, 0, 3, 0, 1,
        //         0, 2, 0, 2, 0, 0, 0, 4, 0, 4, 0, 1, 0, 1, 0, 2 };
        byte[] bytes1 = collator.getCollationKey("12").toByteArray();
        byte[] bytes2 = collator.getCollationKey("123").toByteArray();
        byte[] bytes3 = collator.getCollationKey("124").toByteArray();
        byte[] bytes4 = collator.getCollationKey("1245").toByteArray();
        byte[] bytes5 = collator.getCollationKey("1245").toByteArray();

        assertTrue("returned collation key does not sort correctly",
                compareUnsignedByteArrays(bytes1, bytes2) < 0);

        assertTrue("returned collation key does not sort correctly",
                compareUnsignedByteArrays(bytes2, bytes3) < 0);

        assertTrue("returned collation key does not sort correctly",
                compareUnsignedByteArrays(bytes3, bytes4) < 0);

        assertTrue("returned collation key does not sort correctly",
                compareUnsignedByteArrays(bytes4, bytes5) == 0);

    }

    private int compareUnsignedByteArrays(byte[] bytes1, byte[] bytes2) {
        int commonLength = Math.min(bytes1.length, bytes2.length);

        for (int i = 0; i < commonLength; i++) {
            int keyA = bytes1[i] & 0xFF;
            int keyB = bytes2[i] & 0xFF;
            if (keyA < keyB) {
                return -1;
            }
            if (keyA > keyB) {
                return 1;
            }
        }

        if (bytes1.length < bytes2.length) {
            return -1;
        } else if (bytes1.length > bytes2.length) {
            return 1;
        } else {
            return 0;
        }
    }
}
