/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Locale;
import junit.framework.TestCase;

public class StringTest extends TestCase {
    public void testIsEmpty() {
        assertTrue("".isEmpty());
        assertFalse("x".isEmpty());
    }

    // The evil decoder keeps hold of the CharBuffer it wrote to.
    private static final class EvilCharsetDecoder extends CharsetDecoder {
        private static char[] chars;
        public EvilCharsetDecoder(Charset cs) {
            super(cs, 1.0f, 1.0f);
        }
        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
            chars = out.array();
            int inLength = in.remaining();
            for (int i = 0; i < inLength; ++i) {
                in.put((byte) 'X');
                out.put('Y');
            }
            return CoderResult.UNDERFLOW;
        }
        public static void corrupt() {
            for (int i = 0; i < chars.length; ++i) {
                chars[i] = '$';
            }
        }
    }

    // The evil encoder tries to write to the CharBuffer it was given to
    // read from.
    private static final class EvilCharsetEncoder extends CharsetEncoder {
        public EvilCharsetEncoder(Charset cs) {
            super(cs, 1.0f, 1.0f);
        }
        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
            int inLength = in.remaining();
            for (int i = 0; i < inLength; ++i) {
                in.put('x');
                out.put((byte) 'y');
            }
            return CoderResult.UNDERFLOW;
        }
    }

    private static final Charset EVIL_CHARSET = new Charset("evil", null) {
        public boolean contains(Charset charset) { return false; }
        public CharsetEncoder newEncoder() { return new EvilCharsetEncoder(this); }
        public CharsetDecoder newDecoder() { return new EvilCharsetDecoder(this); }
    };

    public void testGetBytes_MaliciousCharset() {
        try {
            String s = "hi";
            // Check that our encoder can't write to the input CharBuffer
            // it was given.
            s.getBytes(EVIL_CHARSET);
            fail(); // We shouldn't have got here!
        } catch (ReadOnlyBufferException expected) {
            // We caught you trying to be naughty!
        }
    }

    public void testStringFromCharset() {
        Charset cs = Charset.forName("UTF-8");
        byte[] bytes = new byte[] {(byte) 'h', (byte) 'i'};
        assertEquals("hi", new String(bytes, cs));
    }

    public void testStringFromCharset_MaliciousCharset() {
        Charset cs = EVIL_CHARSET;
        byte[] bytes = new byte[] {(byte) 'h', (byte) 'i'};
        final String result = new String(bytes, cs);
        assertEquals("YY", result); // (Our decoder always outputs 'Y's.)
        // Check that even if the decoder messes with the output CharBuffer
        // after we've created a string from it, it doesn't affect the string.
        EvilCharsetDecoder.corrupt();
        assertEquals("YY", result);
    }

    public void test_getBytes_bad() throws Exception {
        // Check that we use '?' as the replacement byte for invalid characters.
        assertEquals("[97, 63, 98]", Arrays.toString("a\u0666b".getBytes("US-ASCII")));
        assertEquals("[97, 63, 98]", Arrays.toString("a\u0666b".getBytes(Charset.forName("US-ASCII"))));
    }

    public void test_getBytes_UTF_8() {
        // We have a fast path implementation of String.getBytes for UTF-8.
        Charset cs = Charset.forName("UTF-8");

        // Test the empty string.
        assertEquals("[]", Arrays.toString("".getBytes(cs)));

        // Test one-byte characters.
        assertEquals("[0]", Arrays.toString("\u0000".getBytes(cs)));
        assertEquals("[127]", Arrays.toString("\u007f".getBytes(cs)));
        assertEquals("[104, 105]", Arrays.toString("hi".getBytes(cs)));

        // Test two-byte characters.
        assertEquals("[-62, -128]", Arrays.toString("\u0080".getBytes(cs)));
        assertEquals("[-39, -90]", Arrays.toString("\u0666".getBytes(cs)));
        assertEquals("[-33, -65]", Arrays.toString("\u07ff".getBytes(cs)));
        assertEquals("[104, -39, -90, 105]", Arrays.toString("h\u0666i".getBytes(cs)));

        // Test three-byte characters.
        assertEquals("[-32, -96, -128]", Arrays.toString("\u0800".getBytes(cs)));
        assertEquals("[-31, -120, -76]", Arrays.toString("\u1234".getBytes(cs)));
        assertEquals("[-17, -65, -65]", Arrays.toString("\uffff".getBytes(cs)));
        assertEquals("[104, -31, -120, -76, 105]", Arrays.toString("h\u1234i".getBytes(cs)));

        // Test supplementary characters.
        // Minimum supplementary character: U+10000
        assertEquals("[-16, -112, -128, -128]", Arrays.toString("\ud800\udc00".getBytes(cs)));
        // Random supplementary character: U+10381 Ugaritic letter beta
        assertEquals("[-16, -112, -114, -127]", Arrays.toString("\ud800\udf81".getBytes(cs)));
        // Maximum supplementary character: U+10FFFF
        assertEquals("[-12, -113, -65, -65]", Arrays.toString("\udbff\udfff".getBytes(cs)));
        // A high surrogate at end of string is an error replaced with '?'.
        assertEquals("[104, 63]", Arrays.toString("h\ud800".getBytes(cs)));
        // A high surrogate not followed by a low surrogate is an error replaced with '?'.
        assertEquals("[104, 63, 105]", Arrays.toString("h\ud800i".getBytes(cs)));
    }

    public void test_new_String_bad() throws Exception {
        // Check that we use U+FFFD as the replacement string for invalid bytes.
        assertEquals("a\ufffdb", new String(new byte[] { 97, -2, 98 }, "US-ASCII"));
        assertEquals("a\ufffdb", new String(new byte[] { 97, -2, 98 }, Charset.forName("US-ASCII")));
    }

    /**
     * Tests a widely assumed performance characteristic of String.substring():
     * that it reuses the original's backing array. Although behaviour should be
     * correct even if this test fails, many applications may suffer
     * significant performance degradation.
     */
    public void testSubstringSharesBackingArray() throws IllegalAccessException {
        String abcdefghij = "ABCDEFGHIJ";
        String cdefg = abcdefghij.substring(2, 7);
        assertSame(getBackingArray(abcdefghij), getBackingArray(cdefg));
    }

    /**
     * Tests a widely assumed performance characteristic of string's copy
     * constructor: that it ensures the backing array is the same length as the
     * string. Although behaviour should be correct even if this test fails,
     * many applications may suffer significant performance degradation.
     */
    public void testStringCopiesAvoidHeapRetention() throws IllegalAccessException {
        String abcdefghij = "ABCDEFGHIJ";
        assertSame(getBackingArray(abcdefghij), getBackingArray(new String(abcdefghij)));

        String cdefg = abcdefghij.substring(2, 7);
        assertSame(getBackingArray(abcdefghij), getBackingArray(cdefg));
        assertEquals(5, getBackingArray(new String(cdefg)).length);
    }

    /**
     * Uses reflection to return the char[] backing the given string. This
     * returns the actual backing array; which must not be modified.
     */
    private char[] getBackingArray(String string) throws IllegalAccessException {
        for (Field f : String.class.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers()) && f.getType() == char[].class) {
                f.setAccessible(true);
                return (char[]) f.get(string);
            }
        }
        throw new UnsupportedOperationException("No chars[] field on String!");
    }

    /**
     * Test that strings interned manually and then later loaded as literals
     * maintain reference equality. http://b/3098960
     */
    public void testInternBeforeLiteralIsLoaded() throws Exception{
        String programmatic = Arrays.asList("5058", "9962", "1563", "5744").toString().intern();
        String literal = (String) Class.forName("libcore.java.lang.StringTest$HasLiteral")
                .getDeclaredField("literal").get(null);
        assertEquals(System.identityHashCode(programmatic), System.identityHashCode(literal));
        assertSame(programmatic, literal);
    }

    static class HasLiteral {
        static String literal = "[5058, 9962, 1563, 5744]";
    }

    private static final String LATIN_CAPITAL_I = "I";
    private static final String LATIN_CAPITAL_I_WITH_DOT_ABOVE = "\u0130";
    private static final String LATIN_SMALL_I = "i";
    private static final String LATIN_SMALL_DOTLESS_I = "\u0131";

    private static final String[] LATIN_I_VARIANTS = {
        LATIN_SMALL_I,
        LATIN_SMALL_DOTLESS_I,
        LATIN_CAPITAL_I,
        LATIN_CAPITAL_I_WITH_DOT_ABOVE,
    };

    public void testCaseMapping_tr_TR() {
        Locale trTR = new Locale("tr", "TR");
        assertEquals(LATIN_SMALL_I, LATIN_SMALL_I.toLowerCase(trTR));
        assertEquals(LATIN_SMALL_I, LATIN_CAPITAL_I_WITH_DOT_ABOVE.toLowerCase(trTR));
        assertEquals(LATIN_SMALL_DOTLESS_I, LATIN_SMALL_DOTLESS_I.toLowerCase(trTR));

        assertEquals(LATIN_CAPITAL_I, LATIN_CAPITAL_I.toUpperCase(trTR));
        assertEquals(LATIN_CAPITAL_I_WITH_DOT_ABOVE, LATIN_CAPITAL_I_WITH_DOT_ABOVE.toUpperCase(trTR));
        assertEquals(LATIN_CAPITAL_I_WITH_DOT_ABOVE, LATIN_SMALL_I.toUpperCase(trTR));

        assertEquals(LATIN_CAPITAL_I, LATIN_SMALL_DOTLESS_I.toUpperCase(trTR));
        assertEquals(LATIN_SMALL_DOTLESS_I, LATIN_CAPITAL_I.toLowerCase(trTR));
    }

    public void testCaseMapping_en_US() {
        Locale enUs = new Locale("en", "US");
        assertEquals(LATIN_CAPITAL_I, LATIN_SMALL_I.toUpperCase(enUs));
        assertEquals(LATIN_CAPITAL_I, LATIN_CAPITAL_I.toUpperCase(enUs));
        assertEquals(LATIN_CAPITAL_I_WITH_DOT_ABOVE, LATIN_CAPITAL_I_WITH_DOT_ABOVE.toUpperCase(enUs));

        assertEquals(LATIN_SMALL_I, LATIN_SMALL_I.toLowerCase(enUs));
        assertEquals(LATIN_SMALL_I, LATIN_CAPITAL_I.toLowerCase(enUs));
        assertEquals(LATIN_SMALL_DOTLESS_I, LATIN_SMALL_DOTLESS_I.toLowerCase(enUs));

        assertEquals(LATIN_CAPITAL_I, LATIN_SMALL_DOTLESS_I.toUpperCase(enUs));
        // http://b/3325799: Android fails this with an extra combining "dot above".
        assertEquals(LATIN_SMALL_I, LATIN_CAPITAL_I_WITH_DOT_ABOVE.toLowerCase(enUs));
    }

    public void testEqualsIgnoreCase_tr_TR() {
        testEqualsIgnoreCase(new Locale("tr", "TR"));
    }

    public void testEqualsIgnoreCase_en_US() {
        testEqualsIgnoreCase(new Locale("en", "US"));
    }

    /**
     * String.equalsIgnoreCase should not depend on the locale.
     */
    private void testEqualsIgnoreCase(Locale locale) {
        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(locale);
        try {
            for (String a : LATIN_I_VARIANTS) {
                for (String b : LATIN_I_VARIANTS) {
                    if (!a.equalsIgnoreCase(b)) {
                        fail("Expected " + a + " to equal " + b + " in " +  locale);
                    }
                }
            }
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    public void testRegionMatches_ignoreCase_en_US() {
        testRegionMatches_ignoreCase(new Locale("en", "US"));
    }

    public void testRegionMatches_ignoreCase_tr_TR() {
        testRegionMatches_ignoreCase(new Locale("tr", "TR"));
    }

    private void testRegionMatches_ignoreCase(Locale locale) {
        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(locale);
        try {
            for (String a : LATIN_I_VARIANTS) {
                for (String b : LATIN_I_VARIANTS) {
                    if (!a.regionMatches(true, 0, b, 0, b.length())) {
                        fail("Expected " + a + " to equal " + b + " in " +  locale);
                    }
                }
            }
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    // http://code.google.com/p/android/issues/detail?id=15266
    public void test_replaceAll() throws Exception {
        assertEquals("project_Id", "projectId".replaceAll("(?!^)(\\p{Upper})(?!$)", "_$1"));
    }
}
