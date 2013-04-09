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

package libcore.java.lang;

import junit.framework.TestCase;

public class OldCharacterTest extends TestCase {

    public void test_codePointCountLjava_lang_CharArrayII() {

      assertEquals(1, Character.codePointCount("\uD800\uDC00".toCharArray(),
                                               0, 2));
      assertEquals(3, Character.codePointCount("a\uD800\uDC00b".toCharArray(),
                                               0, 4));
      assertEquals(4, Character.codePointCount("a\uD800\uDC00b\uD800".toCharArray(),
                                               0, 5));
      assertEquals(4, Character.codePointCount("ab\uD800\uDC00b\uD800".toCharArray(),
                                                1, 5));

      try {
          Character.codePointCount((char[]) null, 0, 1);
          fail("No NPE, null char sequence.");
      } catch (NullPointerException e) {
      }

      try {
          Character.codePointCount("abc".toCharArray(), -1, 1);
          fail("No IOOBE, negative start.");
      } catch (IndexOutOfBoundsException e) {
      }

      try {
          Character.codePointCount("abc".toCharArray(), 0, 4);
          fail("No IOOBE, end greater than length.");
      } catch (IndexOutOfBoundsException e) {
      }

      try {
          Character.codePointCount("abc".toCharArray(), 1, 3);
          fail("No IOOBE, end greater than start.");
      } catch (IndexOutOfBoundsException e) {
      }
    }

    public void test_getDirectionality() throws Exception {

        byte[] directionalities = {
                // BEGIN android-changed
                // Unicode 5.1 defines U+0370 to be Greek capital letter Heta.
                Character.DIRECTIONALITY_LEFT_TO_RIGHT,
                // END android-changed.

                Character.DIRECTIONALITY_LEFT_TO_RIGHT,
                Character.DIRECTIONALITY_RIGHT_TO_LEFT,

                // BEGIN android-changed
                // Unicode standard 5.1 changed category of unicode point 0x0600 from AL to AN
                Character.DIRECTIONALITY_ARABIC_NUMBER,
                // END android-changed.

                Character.DIRECTIONALITY_EUROPEAN_NUMBER,
                // Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR,
                Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR,
                Character.DIRECTIONALITY_ARABIC_NUMBER,
                Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR,
                Character.DIRECTIONALITY_NONSPACING_MARK,
                Character.DIRECTIONALITY_BOUNDARY_NEUTRAL,
                Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR,
                Character.DIRECTIONALITY_SEGMENT_SEPARATOR,
                Character.DIRECTIONALITY_WHITESPACE,
                Character.DIRECTIONALITY_OTHER_NEUTRALS,
                Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING,
                Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE,
                Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING,
                Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE,
                Character.DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
                };

        char[] characters = {
                // BEGIN android-changed
                // Unicode 5.1 defines U+0370 to be Greek capital letter Heta.
                '\u0370', // 1
                // END android-changed
                '\u00B5', // 0
                '\u05BE', // 1
                // BEGIN android-changed
                '\u0600', // 6
                // END android-changed
                '\u00B2', // 3
                // '', // No common char in this group on android and java.
                '\u00B1', // 5
                '\u0660', // 6
                '\u00A0', // 7
                '\u0300', // 8
                '\u009F', // 9
                '\u0085', // 10
                '\u001F', // 11
                '\u0020', // 12
                '\u00AB', // 13
                '\u202A', // 14
                '\u202D', // 15
                '\u202B', // 16
                '\u202E', // 17
                '\u202C' // 18
                };

        for(int i = 0; i < directionalities.length; i++) {
            assertEquals(directionalities[i],
                    Character.getDirectionality(characters[i]));
        }


    }

    public void test_digitCI() {
        assertEquals(-1, Character.digit('\uFFFF', 1));
    }

    public void test_isUpperCaseC() {
        assertFalse("Incorrect case value", Character.isUpperCase('1'));
        assertFalse("Incorrect case value", Character.isUpperCase('?'));
    }

    public void test_toLowerCaseC() {
        assertEquals("Failed to change case", 't', Character.toLowerCase('t'));
        assertEquals("Failed to change case", '1', Character.toLowerCase('1'));
    }

    public void test_toString() {
        assertEquals("Incorrect String returned", "T", new Character('T').toString());
        assertEquals("Incorrect String returned", "1", new Character('1').toString());
        assertEquals("Incorrect String returned", "$", new Character('$').toString());
    }

    public void test_toString_char() {
        assertEquals("Incorrect String returned", "T", Character.toString('T'));
    }
}
