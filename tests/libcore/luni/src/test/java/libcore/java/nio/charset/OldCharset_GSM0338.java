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

/** Note: this test is GSM specific */
public class OldCharset_GSM0338 extends OldCharset_AbstractTest {

    @Override
    protected void setUp() throws Exception {
//        charsetName = "GSM0338";
        charsetName = "x-gsm-03.38-2000";

        testChars = theseChars(new int[]{
10, 13, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98,
99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
115, 116, 117, 118, 119, 120, 121, 122, 161, 163, 164, 165, 167, 191, 196, 197,
198, 201, 209, 214, 216, 220, 223, 224, 228, 229, 230, 231, 232, 233, 236, 241,
242, 246, 248, 249, 252, 915, 916
            });

        testBytes = theseBytes(new int[]{
10, 13, 32, 33, 34, 35, 2, 37, 38, 39, 40, 41, 42, 43, 44, 45,
46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
62, 63, 0, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 17, 97, 98,
99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
115, 116, 117, 118, 119, 120, 121, 122, 64, 1, 36, 3, 95, 96, 91, 14,
28, 31, 93, 92, 11, 94, 30, 127, 123, 15, 29, 9, 4, 5, 7, 125,
8, 124, 12, 6, 126, 19, 16
            });

        super.setUp();
    }

    @Override
    public void test_CodecDynamic () throws CharacterCodingException {
    }

}
