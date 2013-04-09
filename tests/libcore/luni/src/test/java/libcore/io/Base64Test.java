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

package libcore.io;

import java.util.Arrays;
import junit.framework.TestCase;

public final class Base64Test extends TestCase {

    public void testDecodeEmpty() throws Exception {
        assertEquals("[]", Arrays.toString(Base64.decode(new byte[0])));
    }

    public void testEncode() throws Exception {
        assertEncoded("");
        assertEncoded("Eg==", 0x12);
        assertEncoded("EjQ=", 0x12, 0x34 );
        assertEncoded("EjRW", 0x12, 0x34, 0x56);
        assertEncoded("EjRWeA==", 0x12, 0x34, 0x56, 0x78);
        assertEncoded("EjRWeJo=", 0x12, 0x34, 0x56, 0x78, 0x9A);
        assertEncoded("EjRWeJq8", 0x12, 0x34, 0x56, 0x78, 0x9a, 0xbc);
    }

    public void testEncodeDoesNotWrap() {
        int[] data = new int[61];
        Arrays.fill(data, 0xff);
        String expected = "///////////////////////////////////////////////////////////////////////"
                + "//////////w=="; // 84 chars
        assertEncoded(expected, data);
    }

    public void assertEncoded(String expected , int... data) {
        byte[] dataBytes = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            dataBytes[i] = (byte) data[i];
        }
        assertEquals(expected, Base64.encode(dataBytes));
    }
}

