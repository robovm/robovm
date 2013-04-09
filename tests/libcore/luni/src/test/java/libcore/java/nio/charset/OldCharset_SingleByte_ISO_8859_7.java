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

public class OldCharset_SingleByte_ISO_8859_7 extends OldCharset_SingleByteAbstractTest {

    protected void setUp() throws Exception {
        charsetName = "ISO-8859-7";
        allChars = theseChars(new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63,
            64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
            80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
            96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111,
            112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 65533,
            65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533,
            65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533, 65533,
            160, 8216, 8217, 163, 65533, 65533, 166, 167, 168, 169, 65533, 171, 172, 173, 65533, 8213,
            176, 177, 178, 179, 900, 901, 902, 183, 904, 905, 906, 187, 908, 189, 910, 911,
            912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927,
            928, 929, 65533, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943,
            944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959,
            960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 65533});
        super.setUp();
    }

}
