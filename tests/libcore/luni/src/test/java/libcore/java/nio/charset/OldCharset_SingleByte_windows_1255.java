/* Licensed to the Apache Software Foundation (ASF) under one or more
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
package libcore.java.nio.charset;

public class OldCharset_SingleByte_windows_1255 extends OldCharset_SingleByteAbstractTest {

    protected void setUp() throws Exception {
        charsetName = "windows-1255";
        allChars = theseChars(new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
            64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
            80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
            96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111,
            112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127,
            8364, 65533, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 65533, 8249, 65533, 65533, 65533, 65533,
            65533, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 65533, 8250, 65533, 65533, 65533, 65533,
            160, 161, 162, 163, 8362, 165, 166, 167, 168, 169, 215, 171, 172, 173, 174, 175,
            176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 247, 187, 188, 189, 190, 191,
            1456, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1465, 65533, 1467, 1468, 1469, 1470, 1471,
            1472, 1473, 1474, 1475, 1520, 1521, 1522, 1523, 1524, 65533, 65533, 65533, 65533, 65533, 65533, 65533,
            1488, 1489, 1490, 1491, 1492, 1493, 1494, 1495, 1496, 1497, 1498, 1499, 1500, 1501, 1502, 1503,
            1504, 1505, 1506, 1507, 1508, 1509, 1510, 1511, 1512, 1513, 1514, 65533, 65533, 8206, 8207, 65533});
        super.setUp();
    }

}
