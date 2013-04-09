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

package libcore.java.lang;

import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

public class OldStringTest extends junit.framework.TestCase {

    String hw1 = "HelloWorld";

    String hw2 = "HelloWorld";

    String hwlc = "helloworld";

    String hwuc = "HELLOWORLD";

    String comp11 = "Test String";

    char[] buf = { 'W', 'o', 'r', 'l', 'd' };

    public void test_charAtI() {
        // Test for method char java.lang.String.charAt(int)
        assertTrue("Incorrect character returned", hw1.charAt(5) == 'W'
                && (hw1.charAt(1) != 'Z'));

        String testString = "Test String";
        try {
            testString.charAt(testString.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException iobe) {
            //expected
        }

        try {
            testString.charAt(Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException iobe) {
            //expected
        }

        try {
            testString.charAt(-1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException iobe) {
            //expected
        }
    }

    public void test_endsWithLjava_lang_String() {
        assertFalse("Doesn't return false value.", hw1.endsWith("ld "));
        assertFalse("Doesn't return false value.", hw1.endsWith(" "));
        assertTrue("Returned incorrect value for empty string.", hw1.endsWith(""));
        try {
            hw1.endsWith(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_equalsLjava_lang_Object() {
        assertTrue("String not equal", hw1.equals(hw2) && !(hw1.equals(comp11)));
    }

    public void test_equalsIgnoreCaseLjava_lang_String() {
        assertTrue("Returned false for equals strings.", hwlc
                .equalsIgnoreCase(hwlc));

        assertFalse("Returned true for different strings.", hwlc
                .equalsIgnoreCase(hwuc + " "));
    }

    @SuppressWarnings("deprecation")
    public void test_getBytesII$BI() {
        try {
            "Hello World".getBytes(-1, 1, null, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
        } catch (NullPointerException expected) {
        }

        try {
            "Hello World".getBytes(6, 2, null, 0);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException expected) {
        } catch (NullPointerException expected) {
        }

        try {
            "Hello World".getBytes(2, 10, new byte[10], 4);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException expected) {
        }
    }

    public void test_getCharsII$CI() {
        try {
            "Hello World".getChars(-1, 1, null, 0);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected) {
        } catch (NullPointerException expected) {
        }

        try {
            "Hello World".getChars(6, 2, null, 0);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException expected) {
        } catch (NullPointerException expected) {
        }

        try {
            "Hello World".getChars(2, 10, new char[10], 4);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch (IndexOutOfBoundsException expected) {
        }
    }

    public void test_regionMatchesILjava_lang_StringII() {
        assertFalse("Returned true for negative offset.", hw1.regionMatches(-1,
                hw2, 2, 5));
        assertFalse("Returned true for negative offset.", hw1.regionMatches(2,
                hw2, -1, 5));
        assertFalse("Returned true for toffset+len is greater than the length.",
                hw1.regionMatches(5, hw2, 2, 6));
        assertFalse("Returned true for ooffset+len is greater than the length.",
                hw1.regionMatches(2, hw2, 5, 6));
    }

    public void test_regionMatchesZILjava_lang_StringII() {
        String bogusString = "xxcedkedkleiorem lvvwr e''' 3r3r 23r";

        assertFalse("Returned true for negative offset.", hw1.regionMatches(true,
                -1, hw2, 2, 5));
        assertFalse("Returned true for negative offset.", hw1.regionMatches(false,
                2, hw2, -1, 5));
        assertFalse("Returned true for toffset+len is greater than the length.",
                hw1.regionMatches(true, 5, hw2, 2, 6));
        assertFalse("Returned true for ooffset+len is greater than the length.",
                hw1.regionMatches(false, 2, hw2, 5, 6));

        assertTrue("identical regions failed comparison", hwuc.regionMatches(
                true, 0, hwlc, 0, hwuc.length()));
        assertFalse("non identical regions failed comparison", hwuc.regionMatches(
                false, 0, hwlc, 0, hwuc.length()));
    }

    public void test_replaceCC() {
        assertEquals("Returned incorrect string.", hw1, hw1.replace("!", "."));
    }

    public void test_replaceAll() {
        String str = "!'123123.123HelloWorld!123123helloworld#";
        String [] patterns = {"[hw\\p{Upper}]", "(o|l){2,}", "([\'\"]?)(\\d+)",
                              "^!.*#$"};

        String [] results = {"!\'123123.123?ello?orld!123123?ello?orld#",
                             "!\'123123.123He?World!123123he?world#",
                             "!?.?HelloWorld!?helloworld#", "?"};

        for(int i = 0; i < patterns.length; i++) {
            assertEquals("Returned incorrect string",
                                  results[i], str.replaceAll(patterns[i], "?"));
        }

        try {
            str.replaceAll("[abc*", "?");
            fail("PatternSyntaxException is not thrown.");
        } catch(PatternSyntaxException pse) {
            //expected
        }
    }

    public void test_replaceFirst() {
        String str = "!'123123.123HelloWorld!123123helloworld#";
        String [] patterns = {"[hw\\p{Upper}]", "(o|l){2,}", "([\'\"]?)(\\d+)",
                              "^!.*#$"};

        String [] results = {"!'123123.123?elloWorld!123123helloworld#",
                             "!'123123.123He?World!123123helloworld#",
                             "!?.123HelloWorld!123123helloworld#", "?"};

        for(int i = 0; i < patterns.length; i++) {
            assertEquals("Returned incorrect string",
                                  results[i], str.replaceFirst(patterns[i], "?"));
        }

        try {
            str.replaceFirst("[abc*", "?");
            fail("PatternSyntaxException is not thrown.");
        } catch(PatternSyntaxException pse) {
            //expected
        }
    }

    public void test_splitLString() {
        String str = "!'123123.123HelloWorld!123123helloworld#";
        String [] patterns = {"[!.1]", "(\\d+).*e(l+)o.*orld"};
        String [][] results = {{"", "'","23", "23", "", "23HelloWorld", "", "23",
                               "23helloworld#"},
                               {"!'", "#"}};

        for(int i = 0; i < patterns.length; i++) {
            assertTrue("Returned incorrect string array for pattern: " +
                patterns[i], Arrays.equals(results[i], str.split(patterns[i])));
        }

        try {
            str.split("[a}");
            fail("PatternSyntaxException is not thrown.");
        } catch(PatternSyntaxException pse) {
            //expected
        }
    }

    public void test_splitLStringLint() {
        String str = "!'123123.123HelloWorld!123123helloworld#";
        String pattern = "[!.1]";
        String [][] results = {{"", "'","23", "23.123HelloWorld!123123helloworld#"},
                               {"", "'","23", "23", "", "23HelloWorld", "", "23",
                               "23helloworld#"}};

        assertTrue("Returned incorrect string array for limit 4",
                Arrays.equals(results[0], str.split(pattern, 4)));
        assertTrue("Returned incorrect string array for limit 9",
                Arrays.equals(results[1], str.split(pattern, 9)));
        assertTrue("Returned incorrect string array for limit 0",
                Arrays.equals(results[1], str.split(pattern, 0)));
        assertTrue("Returned incorrect string array for limit -1",
                Arrays.equals(results[1], str.split(pattern, -1)));
        assertTrue("Returned incorrect string array for limit 10",
                Arrays.equals(results[1], str.split(pattern, 10)));
        assertTrue("Returned incorrect string array for limit Integer.MAX_VALUE",
                Arrays.equals(results[1], str.split(pattern, Integer.MAX_VALUE)));

        try {
            str.split("[a}", 0);
            fail("PatternSyntaxException is not thrown.");
        } catch(PatternSyntaxException pse) {
            //expected
        }
    }

    public void test_replaceLjava_langCharSequenceLjava_langCharSequence() {
        assertEquals("Failed replace", "aaccdd", "aabbdd".replace(
            new StringBuffer("bb"), "cc"));
        assertEquals("Failed replace by bigger seq", "cccbccc", "aba".replace(
            "a", "ccc"));
        assertEquals("Failed replace by smaller seq", "$bba^",
            "$aaaaa^".replace(new StringBuilder("aa"), "b"));

        try {
            "".replace((CharSequence) null, "123".subSequence(0, 1));
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            "".replace("123".subSequence(0, 1), (CharSequence) null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_substringI() {
        try {
            hw1.substring(-1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.substring(hw1.length() + 1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.substring(Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_substringII() {
        try {
            hw1.substring(-1, hw1.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.substring(Integer.MAX_VALUE, hw1.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.substring(0, Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_subSequence() {
        // Test for method java.lang.String java.lang.String.substring(int, int)
        assertTrue("Incorrect substring returned", hw1.subSequence(0, 5).equals(
                      "Hello") && (hw1.subSequence(5, 10).equals("World")));
        assertTrue("not identical", hw1.subSequence(0, hw1.length()) == hw1);

        try {
            hw1.subSequence(0, Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.subSequence(Integer.MAX_VALUE, hw1.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            hw1.subSequence(-1, hw1.length());
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_trim() {
        assertEquals("Incorrect string returned", hw1, "  HelloWorld  ".trim());
        assertTrue("Incorrect string returned", "   ".trim().equals(""));
    }

    public void test_valueOf$C() {
        assertEquals("Returned incorrect String",
                "World", String.valueOf(buf));
        assertEquals("Returned incorrect String",
                "", String.valueOf(new char[]{}));
        try {
            String.valueOf(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_valueOf$CII() {
        char[] t = { 'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd' };

        try {
            String.valueOf(t, 0, t.length + 1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            String.valueOf(t, 0, -1);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }

        try {
            String.valueOf(t, 0, Integer.MAX_VALUE);
            fail("IndexOutOfBoundsException was not thrown.");
        } catch(IndexOutOfBoundsException ioobe) {
            //expected
        }
    }

    public void test_valueOfLjava_lang_Object() {
        assertEquals("Incorrect value was returned for null.",
                 "null", String.valueOf((Object) null));
    }

    @SuppressWarnings("boxing")
    public void test_format() {
        assertEquals("3 2 1 4 3 2 1", String.format(
                "%3$d %2$d %1$d %4$d %3$d %2$d %1$d", 1, 2, 3, 4));

        assertEquals("empty format", "", String.format("", 123, this));
        try {
            String.format(null);
            fail("NPE is expected on null format");
        } catch (NullPointerException ok){}

        try {
            String.format("%d%% of %s is 0x%x", "123");
            fail("IllegalFormatException was not thrown.");
        } catch(IllegalFormatException ife) {
            //expected
        }

        try {
            String.format("%tu", "123");
            fail("IllegalFormatException was not thrown.");
        } catch(IllegalFormatException ife) {
            //expected
        }

    }

    @SuppressWarnings("boxing")
    public void test_format_Locale() {
        Locale l = new Locale("UK");
        assertEquals("13% of sum is 0x11",
                String.format(l, "%d%% of %s is 0x%x", 13, "sum", 17));
        assertEquals("empty format", "", String.format("", 123, this));
        try {
            String.format(l, null, "");
            fail("NPE is expected on null format");
        } catch (NullPointerException ok){}

        try {
            String.format(l, "%d", "test");
            fail("IllegalFormatException wasn't thrown.");
        } catch(IllegalFormatException ife) {
            //expected
        }
    }

    public void test_matches() {
        String[] patterns = {
                "(a|b)*abb",
                "(1*2*3*4*)*567",
                "(a|b|c|d)*aab",
                "(1|2|3|4|5|6|7|8|9|0)(1|2|3|4|5|6|7|8|9|0)*",
                "(abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ)*",
                "(a|b)*(a|b)*A(a|b)*lice.*",
                "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)(a|b|c|d|e|f|g|h|"
                        + "i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)*(1|2|3|4|5|6|7|8|9|0)*|while|for|struct|if|do",

       };

        String[] patternsInc = {
                "(ac)*bb",
                "(1)*567",
                "(c)*ab",
                "(|8|9|0)(1|2|7|8|9|0)*",
                "(z)",
                "(a)*A(b)*lice.",
                "(a|b|c|d|e)",

       };

        String[][] strings = {
                { "abb", "ababb", "abababbababb", "abababbababbabababbbbbabb" },
                { "213567", "12324567", "1234567", "213213567",
                        "21312312312567", "444444567" },
                { "abcdaab", "aab", "abaab", "cdaab", "acbdadcbaab" },
                { "213234567", "3458", "0987654", "7689546432", "0398576",
                        "98432", "5" },
                {
                        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
                        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" },
                { "ababbaAabababblice", "ababbaAliceababab", "ababbAabliceaaa",
                        "abbbAbbbliceaaa", "Alice" },
                { "a123", "bnxnvgds156", "for", "while", "if", "struct" },
                { "xy" }, { "xy" }, { "xcy" }

        };

        for (int i = 0; i < patterns.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                assertTrue("Incorrect match: " + patterns[i] + " vs "
                        + strings[i][j], strings[i][j].matches(patterns[i]));
                assertFalse("" + i, strings[i][j].matches(patternsInc[i]));
            }
        }
    }

    public void test_indexOfI() {
        assertEquals("Doesn't return -1 if there is no such character.", -1,
                hw1.indexOf('q'));
    }

    public void test_indexOfII() {
        assertEquals("Doesn't return -1 if there is no such character.", -1,
                hw1.indexOf('H', 2));

    }

    public void test_indexOfLjava_lang_String() {
        assertEquals("Doesn't return -1 for unknown string.",
                -1, hw1.indexOf("Heo"));
    }
}
