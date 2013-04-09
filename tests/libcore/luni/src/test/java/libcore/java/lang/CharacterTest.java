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

public class CharacterTest extends junit.framework.TestCase {
    public void test_valueOfC() {
        // The JLS requires caching for chars between "\u0000 to \u007f":
        // http://java.sun.com/docs/books/jls/third_edition/html/conversions.html#5.1.7
        // Harmony caches 0-512 and tests for this behavior, so we suppress that test and use this.
        for (char c = '\u0000'; c <= '\u007f'; ++c) {
            Character e = new Character(c);
            Character a = Character.valueOf(c);
            assertEquals(e, a);
            assertSame(Character.valueOf(c), Character.valueOf(c));
        }
        for (int c = '\u0080'; c <= Character.MAX_VALUE; ++c) {
            assertEquals(new Character((char) c), Character.valueOf((char) c));
        }
    }

    public void test_isBmpCodePoint() throws Exception {
        assertTrue(Character.isBmpCodePoint(0x0000));
        assertTrue(Character.isBmpCodePoint(0x0666));
        assertTrue(Character.isBmpCodePoint(0xffff));
        assertFalse(Character.isBmpCodePoint(0x10000));
        assertFalse(Character.isBmpCodePoint(-1));
        assertFalse(Character.isBmpCodePoint(Integer.MAX_VALUE));
        assertFalse(Character.isBmpCodePoint(Integer.MIN_VALUE));
    }

    public void test_isSurrogate() throws Exception {
        assertFalse(Character.isSurrogate('\u0000'));
        assertFalse(Character.isSurrogate('\u0666'));
        assertFalse(Character.isSurrogate((char) (Character.MIN_SURROGATE - 1)));
        for (char ch = Character.MIN_SURROGATE; ch <= Character.MAX_SURROGATE; ++ch) {
            assertTrue(Character.isSurrogate(ch));
        }
        assertFalse(Character.isSurrogate((char) (Character.MAX_SURROGATE + 1)));
    }

    public void test_highSurrogate() throws Exception {
        // The behavior for non-supplementary code points (like these two) is undefined.
        // These are the obvious results if you don't do anything special.
        assertEquals(0xd7c0, Character.highSurrogate(0x0000));
        assertEquals(0xd7c1, Character.highSurrogate(0x0666));
        // These two tests must pass, though.
        assertEquals(0xd800, Character.highSurrogate(0x010000));
        assertEquals(0xdbff, Character.highSurrogate(0x10ffff));
    }

    public void test_lowSurrogate() throws Exception {
        // The behavior for non-supplementary code points (like these two) is undefined.
        // These are the obvious results if you don't do anything special.
        assertEquals(0xdc00, Character.lowSurrogate(0x0000));
        assertEquals(0xde66, Character.lowSurrogate(0x0666));
        // These two tests must pass, though.
        assertEquals(0xdc00, Character.lowSurrogate(0x010000));
        assertEquals(0xdfff, Character.lowSurrogate(0x10ffff));
    }

    public void test_getName() throws Exception {
        // Character.getName requires the corresponding ICU data.
        assertEquals("NULL", Character.getName(0x0000));
        assertEquals("BELL", Character.getName(0x0007));
        assertEquals("LATIN SMALL LETTER L", Character.getName('l'));
        // This changed name from Unicode 1.0. Used to be "OPENING...".
        assertEquals("LEFT CURLY BRACKET", Character.getName('{'));
        assertEquals("ARABIC-INDIC DIGIT SIX", Character.getName(0x0666));
        assertEquals("LINEAR B SYLLABLE B008 A", Character.getName(0x010000));

        // Some private use code points.
        assertEquals("PRIVATE USE AREA E000", Character.getName(0xe000));
        assertEquals("SUPPLEMENTARY PRIVATE USE AREA A F0000", Character.getName(0xf0000));

        // An unassigned code point.
        assertNull(Character.getName(0x10ffff));

        try {
            Character.getName(-1);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            Character.getName(Integer.MAX_VALUE);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            Character.getName(Integer.MIN_VALUE);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void test_compare() throws Exception {
        assertEquals(0, Character.compare('a', 'a'));
        assertTrue(Character.compare('a', 'b') < 0);
        assertTrue(Character.compare('b', 'a') > 0);
    }
}
