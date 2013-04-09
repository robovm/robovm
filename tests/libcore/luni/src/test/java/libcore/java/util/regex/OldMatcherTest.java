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

package libcore.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.TestCase;

public class OldMatcherTest extends TestCase {
    String[] groupPatterns = { "(a|b)*aabb", "((a)|b)*aabb", "((a|b)*)a(abb)",
            "(((a)|(b))*)aabb", "(((a)|(b))*)aa(b)b", "(((a)|(b))*)a(a(b)b)" };

    public void testAppendReplacement() {
        Pattern pat = Pattern.compile("XX");
        Matcher m = pat.matcher("Today is XX-XX-XX ...");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; m.find(); i++) {
            m.appendReplacement(sb, new Integer(i * 10 + i).toString());
        }
        m.appendTail(sb);
        assertEquals("Today is 0-11-22 ...", sb.toString());

        pat = Pattern.compile("cat");
        m = pat.matcher("one-cat-two-cats-in-the-yard");
        sb = new StringBuffer();
        Throwable t = null;
        m.find();
        try {
            m.appendReplacement(null, "dog");
        } catch (NullPointerException e) {
            t = e;
        }
        assertNotNull(t);
        t = null;
        m.find();
        try {
            m.appendReplacement(sb, null);
        } catch (NullPointerException e) {
            t = e;
        }
        assertNotNull(t);
    }

    public void test_resetLjava_lang_String() {
        String testPattern = "(abb)";
        String testString1 = "babbabbcccabbabbabbabbabb";
        String testString2 = "cddcddcddcddcddbbbb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString1);

        while (mat.find());
        assertEquals("Reset should return itself 1", mat, mat.reset(testString2));
        assertFalse("After reset matcher should not find pattern in given input", mat.find());
        assertEquals("Reset should return itself 2", mat, mat.reset(testString1));
        assertTrue("After reset matcher should find pattern in given input", mat.find());
    }

    public void testAppendTail() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one-cat-two-cats-in-the-yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        assertEquals("one-dog-two-dogs-in-the-yard", sb.toString());

        p = Pattern.compile("cat|yard");
        m = p.matcher("one-cat-two-cats-in-the-yard");
        sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        assertEquals("one-dog-two-dogs-in-the-dog", sb.toString());
        m.appendTail(sb);
        assertEquals("one-dog-two-dogs-in-the-dog", sb.toString());

        p = Pattern.compile("cat");
        m = p.matcher("one-cat-two-cats-in-the-yard");
        sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        Throwable t = null;
        try {
            m.appendTail(null);
        } catch (NullPointerException e) {
            t = e;
        }
        assertNotNull(t);
    }

    public void test_reset() {
        String testPattern = "(abb)";
        String testString = "babbabbcccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        while (mat.find());
        assertEquals("Reset should return itself", mat, mat.reset());
        assertTrue("After reset matcher should find pattern in given input", mat.find());
    }

    public void test_hasAnchoringBounds() {
        String testPattern = "abb";
        String testString = "abb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        assertTrue("Matcher uses anchoring bound by default",
                mat.hasAnchoringBounds());

        Matcher mu = mat.useAnchoringBounds(true);
        assertTrue("Incorrect value of anchoring bounds",
                mu.hasAnchoringBounds());

        mu = mat.useAnchoringBounds(false);
        assertFalse("Incorrect value of anchoring bounds",
                mu.hasAnchoringBounds());
    }

    public void test_hasTransparentBounds() {
        String testPattern = "abb";
        String testString = "ab\nb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        assertFalse("Matcher uses opaque bounds by default",
                mat.hasTransparentBounds());

        Matcher mu = mat.useTransparentBounds(true);
        assertTrue("Incorrect value of anchoring bounds",
                mu.hasTransparentBounds());

        mu = mat.useTransparentBounds(false);
        assertFalse("Incorrect value of anchoring bounds",
                mu.hasTransparentBounds());
    }

    public void test_startI() {
        String testPattern = "(((abb)a)(bb))";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int i, j;

        for (j = 0; j < 3; j++) {
            while (mat.find(start + j - 2)) {
                for (i = 0; i < 4; i++) {
                    assertEquals("Start is wrong for group " + i + " :" + mat.group(i), start, mat.start(i));
                }
                assertEquals("Start is wrong for group " + i + " :" + mat.group(i), start + 4, mat.start(i));

                start = end;
                end += 3;
            }
        }
    }

    public void test_endI() {
        String testPattern = "(((abb)a)(bb))";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int i, j;

        for (j = 0; j < 3; j++) {
            while (mat.find(start + j - 2)) {
                for (i = 0; i < 4; i++) {
                    assertEquals("End is wrong for group " + i + " :" + mat.group(i), start + mat.group(i).length(), mat.end(i));
                }
                assertEquals("End is wrong for group " + i + " :" + mat.group(i), start + 4 + mat.group(i).length(), mat.end(i));

                start = end;
                end += 3;
            }
        }
    }


    public void test_lookingAt() {
        String testPattern = "(((abb)a)(bb))";
        String testString1 = "babbabbcccabbabbabbabbabb";
        String testString2 = "abbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat1 = pat.matcher(testString1);
        Matcher mat2 = pat.matcher(testString2);

        assertFalse("Should not find given pattern in 1 string", mat1.lookingAt());
        mat1.region(1, 10);
        assertTrue("Should find given pattern in region of string", mat1.lookingAt());
        assertTrue("Should find given pattern in 2 string", mat2.lookingAt());
    }

    public void test_findI() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int j;

        for (j = 0; j < 3; j++) {
            while (mat.find(start + j - 2)) {
                assertEquals(start, mat.start(1));
                assertEquals(end, mat.end(1));

                start = end;
                end += 3;
            }
            start = 6;
            end = 9;
        }

        testPattern = "(\\d{1,3})";
        testString = "aaaa123456789045";

        Pattern pat2 = Pattern.compile(testPattern);
        Matcher mat2 = pat2.matcher(testString);
        start = 4;
        int length = 3;
        for (j = 0; j < length; j++) {
            for (int i = 4 + j; i < testString.length() - length; i += length) {
                mat2.find(i);
                assertEquals(testString.substring(i, i + length), mat2.group(1));
            }
        }

        // Starting index out of region
        Pattern pat3 = Pattern.compile("new");
        Matcher mat3 = pat3.matcher("Brave new world");

        assertTrue(mat3.find(-1));
        assertTrue(mat3.find(6));
        assertFalse(mat3.find(7));

        mat3.region(7, 10);

        assertFalse(mat3.find(3));
        assertFalse(mat3.find(6));
        assertFalse(mat3.find(7));
    }

    public void testSEOLsymbols() {
        Pattern pat = Pattern.compile("^a\\(bb\\[$");
        Matcher mat = pat.matcher("a(bb[");

        assertTrue(mat.matches());
    }

    public void test_start() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int j;

        for (j = 0; j < 3; j++) {
            while (mat.find()) {
                assertEquals("Start is wrong", start, mat.start());

                start = end;
                end += 3;
            }
        }
    }

    public void test_end() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);
        int start = 3;
        int end = 6;
        int j;

        for (j = 0; j < 3; j++) {
            while (mat.find()) {
                assertEquals("Start is wrong", end, mat.end());

                start = end;
                end += 3;
            }
        }
    }

    public void testGroupCount() {
        for (int i = 0; i < groupPatterns.length; i++) {
            Pattern test = Pattern.compile(groupPatterns[i]);
            Matcher mat = test.matcher("ababababbaaabb");
            mat.matches();
            assertEquals(i + 1, mat.groupCount());
        }
    }


    public void testRegion() {
        Pattern p = Pattern.compile("abba");
        Matcher m = p.matcher("Gabba gabba hey");

        m.region(0, 15);
        assertTrue(m.find());
        assertTrue(m.find());
        assertFalse(m.find());

        m.region(5, 15);
        assertTrue(m.find());
        assertFalse(m.find());

        m.region(10, 15);
        assertFalse(m.find());

        Throwable t = null;

        try {
            m.region(-1, 15);
        } catch (IndexOutOfBoundsException e) {
            t = e;
        }
        assertNotNull(t);

        t = null;
        try {
            m.region(0, 16);
        } catch (IndexOutOfBoundsException e) {
            t = e;
        }
        assertNotNull(t);
    }


    public void testMatchesURI() {
        Pattern pat = Pattern.
                compile("^(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?");
        Matcher mat = pat
                .matcher("file:/c:/workspace/api/build.win32/classes/META-INF/"
                        + "services/javax.xml.parsers.DocumentBuilderFactory");
        assertTrue(mat.matches());
    }


    public void testQuoteReplacement() {
        assertEquals("\\$dollar and slash\\\\", Matcher.quoteReplacement("$dollar and slash\\"));
    }

    public void testUnicode() {

        assertTrue(Pattern.compile("\\x61a").matcher("aa").matches());
//        assertTrue(Pattern.matches("\\u0061a", "aa"));
        assertTrue(Pattern.compile("\\0141a").matcher("aa").matches());
        assertTrue(Pattern.compile("\\0777").matcher("?7").matches());

    }

    public void testUnicodeCategory() {
        assertTrue(Pattern.compile("\\p{Ll}").matcher("k").matches()); // Unicode lower case
        assertTrue(Pattern.compile("\\P{Ll}").matcher("K").matches()); // Unicode non-lower
        // case
        assertTrue(Pattern.compile("\\p{Lu}").matcher("K").matches()); // Unicode upper case
        assertTrue(Pattern.compile("\\P{Lu}").matcher("k").matches()); // Unicode non-upper
        // case
        // combinations
        assertTrue(Pattern.compile("[\\p{L}&&[^\\p{Lu}]]").matcher("k").matches());
        assertTrue(Pattern.compile("[\\p{L}&&[^\\p{Ll}]]").matcher("K").matches());
        assertFalse(Pattern.compile("[\\p{L}&&[^\\p{Lu}]]").matcher("K").matches());
        assertFalse(Pattern.compile("[\\p{L}&&[^\\p{Ll}]]").matcher("k").matches());

        // category/character combinations
        assertFalse(Pattern.compile("[\\p{L}&&[^a-z]]").matcher("k").matches());
        assertTrue(Pattern.compile("[\\p{L}&&[^a-z]]").matcher("K").matches());

        assertTrue(Pattern.compile("[\\p{Lu}a-z]").matcher("k").matches());
        assertTrue(Pattern.compile("[a-z\\p{Lu}]").matcher("k").matches());

        assertFalse(Pattern.compile("[\\p{Lu}a-d]").matcher("k").matches());
        assertTrue(Pattern.compile("[a-d\\p{Lu}]").matcher("K").matches());

        //        assertTrue(Pattern.matches("[\\p{L}&&[^\\p{Lu}&&[^K]]]", "K"));
        assertFalse(Pattern.compile("[\\p{L}&&[^\\p{Lu}&&[^G]]]").matcher("K").matches());

    }

    // BEGIN android-note
    // Test took ages, now going in steps of 16 code points to speed things up.
    // END android-note
    public void testAllCodePoints() {
        // Regression for HARMONY-3145
        int[] codePoint = new int[1];
        Pattern p = Pattern.compile("(\\p{all})+");
        boolean res = true;
        int cnt = 0;
        String s;
        for (int i = 0; i < 0x110000; i = i + 0x10) {
            codePoint[0] = i;
            s = new String(codePoint, 0, 1);
            if (!s.matches(p.toString())) {
                cnt++;
                res = false;
            }
        }
        assertTrue(res);
        assertEquals(0, cnt);

        p = Pattern.compile("(\\P{all})+");
        res = true;
        cnt = 0;

        for (int i = 0; i < 0x110000; i = i + 0x10) {
            codePoint[0] = i;
            s = new String(codePoint, 0, 1);
            if (!s.matches(p.toString())) {
                cnt++;
                res = false;
            }
        }

        assertFalse(res);
        assertEquals(0x110000 / 0x10, cnt);
    }

    public void test_regionStart() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        assertEquals("Region sould start from 0 position", 0, mat.regionStart());
        mat.region(1, 10);
        assertEquals("Region sould start from 1 position after setting new region", 1, mat.regionStart());
        mat.reset();
        assertEquals("Region sould start from 0 position after reset", 0, mat.regionStart());
    }

    public void test_regionEnd() {
        String testPattern = "(abb)";
        String testString = "cccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        assertEquals("Region end value should be equal to string length", testString.length(), mat.regionEnd());
        mat.region(1, 10);
        assertEquals("Region end value should be equal to 10 after setting new region", 10, mat.regionEnd());
        mat.reset();
        assertEquals("Region end value should be equal to string length after reset", testString.length(), mat.regionEnd());
    }

    public void test_toMatchResult() {
        String testPattern = "(((abb)a)(bb))";
        String testString = "babbabbcccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        mat.region(1, 7);
        assertTrue("matcher should find pattern in given region", mat.matches());
        assertEquals("matched section should start from 1 position", 1, mat.toMatchResult().start());
        assertEquals("matched section for 2 group should start from 1 position", 1, mat.toMatchResult().start(2));
        assertEquals("matched section for whole pattern should end on 7 position", 7, mat.toMatchResult().end());
        assertEquals("matched section for 3 group should end at 4 position", 4, mat.toMatchResult().end(3));
        assertEquals("group not matched", "abbabb", mat.toMatchResult().group());
        assertEquals("3 group not matched", "abb", mat.toMatchResult().group(3));
        assertEquals("Total number of groups does not matched with given pattern", 4, mat.toMatchResult().groupCount());
   }

    public void test_usePatternLjava_util_regex_Pattern() {
        String testPattern1 = "(((abb)a)(bb))";
        String testPattern2 = "(abbabb)";
        String testPattern3 = "(babb)";
        String testString = "babbabbcccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern1);
        Matcher mat = pat.matcher(testString);

        mat.region(1, 7);
        assertTrue("matcher should find pattern in given region in case of groupe in pattern", mat.matches());
        assertEquals("", mat, mat.usePattern(Pattern.compile(testPattern2)));
        assertTrue("matcher should find pattern in given region", mat.matches());
        assertEquals("", mat, mat.usePattern(Pattern.compile(testPattern3)));
        assertFalse("matcher should not find pattern in given region", mat.matches());
   }

    public void test_anchoringBounds() {
        String testPattern = "^ro$";
        String testString = "android";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        mat.region(2, 5);
        mat.useAnchoringBounds(false);
        assertFalse("Shouldn't find pattern with non-anchoring bounds", mat.find(0));

        mat.region(2, 5);
        mat.useAnchoringBounds(true);
        assertFalse("Should find pattern with anchoring bounds", mat.find(0));
    }

    public void test_transparentBounds() {
        String testPattern = "and(?=roid)";
        String testString = "android";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        mat.region(0, 3);
        mat.useTransparentBounds(false);
        assertFalse("Shouldn't find pattern with opaque bounds", mat.matches());

        mat.useTransparentBounds(true);
        assertTrue("Should find pattern transparent bounds", mat.matches()); // ***

        testPattern = "and(?!roid)";
        testString = "android";
        pat = Pattern.compile(testPattern);
        mat = pat.matcher(testString);

        mat.region(0, 3);
        mat.useTransparentBounds(false);
        assertTrue("Should find pattern with opaque bounds", mat.matches());

        mat.useTransparentBounds(true);
        assertFalse("Shouldn't find pattern transparent bounds", mat.matches()); // ***
    }

    public void test_hitEnd() {
        String testPattern = "abb";
        String testString = "babbabbcccabbabbabbabbabb";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        while (mat.find()) {
            assertFalse("hitEnd should return false during parsing input", mat.hitEnd());
        }
        assertTrue("hitEnd should return true after finding last match", mat.hitEnd()); // ***
    }

    public void test_requireEnd() {
        String testPattern = "bba";
        String testString = "abbbbba";
        Pattern pat = Pattern.compile(testPattern);
        Matcher mat = pat.matcher(testString);

        assertTrue(mat.find());
        assertFalse(mat.requireEnd());

        testPattern = "bba$";
        testString = "abbbbba";
        pat = Pattern.compile(testPattern);
        mat = pat.matcher(testString);

        assertTrue(mat.find());
        assertTrue(mat.requireEnd());
    }

    /*
     * Regression test for HARMONY-674
     */
    public void testPatternMatcher() throws Exception {
        Pattern pattern = Pattern.compile("(?:\\d+)(?:pt)");
        assertTrue(pattern.matcher("14pt").matches());
    }

}
