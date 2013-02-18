/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Simple string test.
 */
public class Main {
    public static void main(String args[]) {
        basicTest();
        indexTest();
    }

    public static void basicTest() {
        String baseStr = "*** This is a very nice string!!!";
        String testStr;
        int i;

        testStr = baseStr.substring(4, baseStr.length() - 3);
        System.out.println("testStr is '" + testStr + "'");

        /* sloppy for loop */
        for (i = 0; i < testStr.length(); i++)
            System.out.print(testStr.charAt(i));
        System.out.print("\n");

        String testStr2 = "This is a very nice strinG";
        if (testStr.length() != testStr2.length())
            System.out.println("WARNING: stringTest length mismatch");

        System.out.println("Compare result is " + testStr.compareTo(testStr2));

        // expected: -65302
        String s1 = "\u0c6d\u0cb6\u0d00\u0000\u0080\u0080\u0080\u0000\u0002\u0002\u0002\u0000\u00e9\u00e9\u00e9";
        String s2 = "\u0c6d\u0cb6\u0d00\u0000\u0080\u0080\u0080\u0000\u0002\u0002\u0002\u0000\uffff\uffff\uffff\u00e9\u00e9\u00e9";
        System.out.println("Compare unicode: " + s1.compareTo(s2));

        try {
            testStr.charAt(500);
            System.out.println("GLITCH: expected exception");
        } catch (StringIndexOutOfBoundsException sioobe) {
            System.out.println("Got expected exception");
        }
    }

    public static void indexTest() {
        String baseStr = "The quick brown fox jumps over the lazy dog!";
        String subStr;

        subStr = baseStr.substring(5, baseStr.length() - 4);
        System.out.println("subStr is '" + subStr + "'");

        System.out.println("Indexes are: " +
            baseStr.indexOf('T') + ":" +
            subStr.indexOf('T') + ":" +
            subStr.indexOf('u') + ":" +
            baseStr.indexOf('!') + ":" +
            subStr.indexOf('y') + ":" +
            subStr.indexOf('d') + ":" +
            baseStr.indexOf('x') + ":" +
            subStr.indexOf('x', 0) + ":" +
            subStr.indexOf('x', -1) + ":" +
            subStr.indexOf('x', 200) + ":" +
            baseStr.indexOf('x', 17) + ":" +
            baseStr.indexOf('x', 18) + ":" +
            baseStr.indexOf('x', 19) + ":" +
            subStr.indexOf('x', 13) + ":" +
            subStr.indexOf('x', 14) + ":" +
            subStr.indexOf('&') + ":" +
            baseStr.indexOf(0x12341234));
    }
}
