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

public class OldCharset_SingleByte_ISO_8859_4 extends OldCharset_SingleByteAbstractTest {

    protected void setUp() throws Exception {
        charsetName = "ISO-8859-4";
        allChars = theseChars(new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
            64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
            80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
            96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111,
            112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127,
            128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143,
            144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159,
            160, 260, 312, 342, 164, 296, 315, 167, 168, 352, 274, 290, 358, 173, 381, 175,
            176, 261, 731, 343, 180, 297, 316, 711, 184, 353, 275, 291, 359, 330, 382, 331,
            256, 193, 194, 195, 196, 197, 198, 302, 268, 201, 280, 203, 278, 205, 206, 298,
            272, 325, 332, 310, 212, 213, 214, 215, 216, 370, 218, 219, 220, 360, 362, 223,
            257, 225, 226, 227, 228, 229, 230, 303, 269, 233, 281, 235, 279, 237, 238, 299,
            273, 326, 333, 311, 244, 245, 246, 247, 248, 371, 250, 251, 252, 361, 363, 729});
        super.setUp();
    }

}
