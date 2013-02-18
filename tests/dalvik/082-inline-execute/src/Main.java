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
 * Test for Jit's handling of string inline-execute.  Should be tested
 * twice - once using self-cosimulation (if available) and once without.
 * The non-self-cosimulation test ensures that the answer computed the first
 * time through (via the interpreter) is the same after looping enough
 * to trigger translation.
 */

import junit.framework.Assert;

public class Main {
    public static void main(String args[]) {
        int i;
        stringLengthTest();
        stringCharAtTest();
        stringIndexOfTest();
        for (i = 0; i < 1000; i++)
            stringCompareToTest();
    }

    public static void stringLengthTest() {
        String str0 = "";
        String str1 = "x";
        String str80 = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
        int len0 = str0.length();
        int len1 = str1.length();
        int len80 = str80.length();
        int i;

        System.out.println("Length of " + str0 + " : " + len0);
        System.out.println("Length of " + str1 + " : " + len1);
        System.out.println("Length of " + str80 + " : " + len80);

        for (i = 0; i < 1000; i++) {
            assert(str0.length() == len0);
            assert(str1.length() == len1);
            assert(str80.length() == len80);
        }
    }

    public static void stringCharAtTest() {
        String testStr = "Now is the time";
        int under = -1;
        int over = testStr.length();
        int numThrown = 0;
        int numNotThrown = 0;
        int at0 = testStr.charAt(0);
        int at1 = testStr.charAt(1);
        int at10 = testStr.charAt(10);
        int atLast = testStr.charAt(testStr.length()-1);
        int i;

        System.out.println(testStr + "[0] = \"" + (char)at0 + "\"");
        System.out.println(testStr + "[1] = \"" + (char)at1 + "\"");
        System.out.println(testStr + "[10] = \"" + (char)at10 + "\"");
        System.out.println(testStr + "[last] = \"" + (char)atLast + "\"");

        for (i = 0; i < 1000; i++) {
            assert(at0 == testStr.charAt(0));
            assert(at1 == testStr.charAt(1));
            assert(at10 == testStr.charAt(10));
            assert(atLast == testStr.charAt(testStr.length()-1));
        }

        for (i = 0; i < 1000; i++) {
            try {
                testStr.charAt(under);
                numNotThrown++;
            } catch (StringIndexOutOfBoundsException sioobe) {
                numThrown++;
            }
            try {
                testStr.charAt(over);
                numNotThrown++;
            } catch (StringIndexOutOfBoundsException sioobe) {
                numThrown++;
            }
        }
        assert(numNotThrown == 0);
        System.out.println("Num throws " + numThrown);
    }


    public static void stringIndexOfTest() {
        String str0 = "";
        String str3 = "abc";
        String str10 = "abcdefghij";
        String str40 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabc";
        int i;

        for (i = 0; i < 1000; i++) {
            assert(str0.indexOf('a') == -1);
            assert(str3.indexOf('a') == 0);
            assert(str3.indexOf('b') == 1);
            assert(str3.indexOf('c') == 2);
            assert(str10.indexOf('j') == 9);
            assert(str40.indexOf('a') == 0);
            assert(str40.indexOf('b') == 38);
            assert(str40.indexOf('c') == 39);
            assert(str0.indexOf('a',20) == -1);
            assert(str0.indexOf('a',0) == -1);
            assert(str0.indexOf('a',-1) == -1);
            assert(str3.indexOf('a',0) == 0);
            assert(str3.indexOf('a',1) == -1);
            assert(str3.indexOf('a',1234) == -1);
            assert(str3.indexOf('b',0) == 1);
            assert(str3.indexOf('b',1) == 1);
            assert(str3.indexOf('c',2) == 2);
            assert(str10.indexOf('j',5) == 9);
            assert(str10.indexOf('j',9) == 9);
            assert(str40.indexOf('a',10) == 10);
            assert(str40.indexOf('b',40) == -1);
        }

    }

    public static void stringCompareToTest() {
        String test = "0123456789";
        String test1 = new String("0123456789");    // different object
        String test2 = new String("0123456780");    // different value
        String offset = new String("xxx0123456789yyy");
        String sub = offset.substring(3, 13);
        String str32 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String str33 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy";
        String lc = "abcdefg";
        String uc = "ABCDEFG";
        Object blah = new Object();

        for (int i = 0; i < 100; i++) {
            String y = lc.toUpperCase();
            Assert.assertTrue(y.equals(uc));
        }

        Assert.assertEquals(str32.compareTo(str33), -1);
        Assert.assertEquals(str33.compareTo(str32), 1);

        Assert.assertTrue(test.equals(test));
        Assert.assertTrue(test.equals(test1));
        Assert.assertFalse(test.equals(test2));

        Assert.assertEquals(test.compareTo(test1), 0);
        Assert.assertTrue(test1.compareTo(test2) > 0);
        Assert.assertTrue(test2.compareTo(test1) < 0);

        /* compare string with a nonzero offset, in left/right side */
        Assert.assertEquals(test.compareTo(sub), 0);
        Assert.assertEquals(sub.compareTo(test), 0);
        Assert.assertTrue(test.equals(sub));
        Assert.assertTrue(sub.equals(test));
        /* same base, one is a substring */
        Assert.assertFalse(offset.equals(sub));
        Assert.assertFalse(sub.equals(offset));
        /* wrong class */
        Assert.assertFalse(test.equals(blah));

        /* null ptr - throw */
        try {
            test.compareTo(null);
            Assert.fail("didn't get expected npe");
        } catch (NullPointerException npe) {
        }
        /* null ptr - ok */
        Assert.assertFalse(test.equals(null));

        test = test.substring(1);
        Assert.assertTrue(test.equals("123456789"));
        Assert.assertFalse(test.equals(test1));

        test = test.substring(1);
        Assert.assertTrue(test.equals("23456789"));

        test = test.substring(1);
        Assert.assertTrue(test.equals("3456789"));

        test = test.substring(1);
        Assert.assertTrue(test.equals("456789"));

        test = test.substring(3,5);
        Assert.assertTrue(test.equals("78"));

        test = "this/is/a/path";
        String[] strings = test.split("/");
        Assert.assertEquals(4, strings.length);

        Assert.assertEquals("this is a path", test.replaceAll("/", " "));
        Assert.assertEquals("this is a path", test.replace("/", " "));
    }

}
