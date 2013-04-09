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

import java.nio.charset.CharacterCodingException;

public class OldCharset_SingleByte_ISO_8859_3 extends OldCharset_SingleByteAbstractTest {

    protected void setUp() throws Exception {
        charsetName = "ISO-8859-3";
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
            160, 294, 728, 163, 164, 65533, 292, 167, 168, 304, 350, 286, 308, 173, 65533, 379,
            176, 295, 178, 179, 180, 181, 293, 183, 184, 305, 351, 287, 309, 189, 65533, 380,
            192, 193, 194, 65533, 196, 266, 264, 199, 200, 201, 202, 203, 204, 205, 206, 207,
            65533, 209, 210, 211, 212, 288, 214, 215, 284, 217, 218, 219, 220, 364, 348, 223,
            224, 225, 226, 65533, 228, 267, 265, 231, 232, 233, 234, 235, 236, 237, 238, 239,
            65533, 241, 242, 243, 244, 289, 246, 247, 285, 249, 250, 251, 252, 365, 349, 729});
        super.setUp();
    }

    public static void test_Bytes_166() throws CharacterCodingException {
        decodeReplace(
                new byte[] {(byte)166},
                new char[] {292} );
//        ByteBuffer inputBB = ByteBuffer.wrap(new byte[] {(byte)166});
//        CharBuffer outputCB;
//        decoder.onMalformedInput(CodingErrorAction.REPLACE);
//        System.out.println("test_Bytes_166:");
//        outputCB = decoder.decode(inputBB);
//        outputCB.rewind();
//        assertEqualChars("Decoded charactes must match!",
//                new char[] {292},
//                outputCB.array());
    }

    public static void test_Bytes_195() throws CharacterCodingException {
        decodeReplace(
                new byte[] {(byte)195},
                new char[] {65533} );
    }

    public static void test_Bytes_165() throws CharacterCodingException {
        decodeReplace(
                new byte[] {(byte)165},
                new char[] {65533} );
    }

    public static void test_Bytes_165_any() throws CharacterCodingException {
        decodeReplace(
                new byte[] {(byte)165, 32},
                new char[] {65533, 32} );
    }
}
