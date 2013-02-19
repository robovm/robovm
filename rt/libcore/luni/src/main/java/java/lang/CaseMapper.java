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

package java.lang;

import java.util.Locale;
import libcore.icu.ICU;

/**
 * Performs case operations as described by http://unicode.org/reports/tr21/tr21-5.html.
 */
class CaseMapper {
    private static final char[] upperValues = "SS\u0000\u02bcN\u0000J\u030c\u0000\u0399\u0308\u0301\u03a5\u0308\u0301\u0535\u0552\u0000H\u0331\u0000T\u0308\u0000W\u030a\u0000Y\u030a\u0000A\u02be\u0000\u03a5\u0313\u0000\u03a5\u0313\u0300\u03a5\u0313\u0301\u03a5\u0313\u0342\u1f08\u0399\u0000\u1f09\u0399\u0000\u1f0a\u0399\u0000\u1f0b\u0399\u0000\u1f0c\u0399\u0000\u1f0d\u0399\u0000\u1f0e\u0399\u0000\u1f0f\u0399\u0000\u1f08\u0399\u0000\u1f09\u0399\u0000\u1f0a\u0399\u0000\u1f0b\u0399\u0000\u1f0c\u0399\u0000\u1f0d\u0399\u0000\u1f0e\u0399\u0000\u1f0f\u0399\u0000\u1f28\u0399\u0000\u1f29\u0399\u0000\u1f2a\u0399\u0000\u1f2b\u0399\u0000\u1f2c\u0399\u0000\u1f2d\u0399\u0000\u1f2e\u0399\u0000\u1f2f\u0399\u0000\u1f28\u0399\u0000\u1f29\u0399\u0000\u1f2a\u0399\u0000\u1f2b\u0399\u0000\u1f2c\u0399\u0000\u1f2d\u0399\u0000\u1f2e\u0399\u0000\u1f2f\u0399\u0000\u1f68\u0399\u0000\u1f69\u0399\u0000\u1f6a\u0399\u0000\u1f6b\u0399\u0000\u1f6c\u0399\u0000\u1f6d\u0399\u0000\u1f6e\u0399\u0000\u1f6f\u0399\u0000\u1f68\u0399\u0000\u1f69\u0399\u0000\u1f6a\u0399\u0000\u1f6b\u0399\u0000\u1f6c\u0399\u0000\u1f6d\u0399\u0000\u1f6e\u0399\u0000\u1f6f\u0399\u0000\u1fba\u0399\u0000\u0391\u0399\u0000\u0386\u0399\u0000\u0391\u0342\u0000\u0391\u0342\u0399\u0391\u0399\u0000\u1fca\u0399\u0000\u0397\u0399\u0000\u0389\u0399\u0000\u0397\u0342\u0000\u0397\u0342\u0399\u0397\u0399\u0000\u0399\u0308\u0300\u0399\u0308\u0301\u0399\u0342\u0000\u0399\u0308\u0342\u03a5\u0308\u0300\u03a5\u0308\u0301\u03a1\u0313\u0000\u03a5\u0342\u0000\u03a5\u0308\u0342\u1ffa\u0399\u0000\u03a9\u0399\u0000\u038f\u0399\u0000\u03a9\u0342\u0000\u03a9\u0342\u0399\u03a9\u0399\u0000FF\u0000FI\u0000FL\u0000FFIFFLST\u0000ST\u0000\u0544\u0546\u0000\u0544\u0535\u0000\u0544\u053b\u0000\u054e\u0546\u0000\u0544\u053d\u0000".toCharArray();
    private static final char[] upperValues2 = "\u000b\u0000\f\u0000\r\u0000\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>\u0000\u0000?@A\u0000BC\u0000\u0000\u0000\u0000D\u0000\u0000\u0000\u0000\u0000EFG\u0000HI\u0000\u0000\u0000\u0000J\u0000\u0000\u0000\u0000\u0000KL\u0000\u0000MN\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000OPQ\u0000RS\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000TUV\u0000WX\u0000\u0000\u0000\u0000Y".toCharArray();

    private static final char LATIN_CAPITAL_I_WITH_DOT = '\u0130';
    private static final char GREEK_CAPITAL_SIGMA = '\u03a3';
    private static final char GREEK_SMALL_FINAL_SIGMA = '\u03c2';

    /**
     * Our current GC makes short-lived objects more expensive than we'd like. When that's fixed,
     * this class should be changed so that you instantiate it with the String and its value,
     * offset, and count fields.
     */
    private CaseMapper() {
    }

    /**
     * Implements String.toLowerCase. We need 's' so that we can return the original String instance
     * if nothing changes. We need 'value', 'offset', and 'count' because they're not otherwise
     * accessible.
     */
    public static String toLowerCase(Locale locale, String s, char[] value, int offset, int count) {
        // Punt hard cases to ICU4C.
        String languageCode = locale.getLanguage();
        if (languageCode.equals("tr") || languageCode.equals("az") || languageCode.equals("lt")) {
            return ICU.toLowerCase(s, locale.toString());
        }

        char[] newValue = null;
        int newCount = 0;
        for (int i = offset, end = offset + count; i < end; ++i) {
            char ch = value[i];
            char newCh;
            if (ch == LATIN_CAPITAL_I_WITH_DOT || Character.isHighSurrogate(ch)) {
                // Punt these hard cases.
                return ICU.toLowerCase(s, locale.toString());
            } else if (ch == GREEK_CAPITAL_SIGMA && isFinalSigma(value, offset, count, i)) {
                newCh = GREEK_SMALL_FINAL_SIGMA;
            } else {
                newCh = Character.toLowerCase(ch);
            }
            if (newValue == null && ch != newCh) {
                newValue = new char[count]; // The result can't be longer than the input.
                newCount = i - offset;
                System.arraycopy(value, offset, newValue, 0, newCount);
            }
            if (newValue != null) {
                newValue[newCount++] = newCh;
            }
        }
        return newValue != null ? new String(0, newCount, newValue) : s;
    }

    /**
     * True if 'index' is preceded by a sequence consisting of a cased letter and a case-ignorable
     * sequence, and 'index' is not followed by a sequence consisting of an ignorable sequence and
     * then a cased letter.
     */
    private static boolean isFinalSigma(char[] value, int offset, int count, int index) {
        // TODO: we don't skip case-ignorable sequences like we should.
        // TODO: we should add a more direct way to test for a cased letter.
        if (index <= offset) {
            return false;
        }
        char previous = value[index - 1];
        if (!(Character.isLowerCase(previous) || Character.isUpperCase(previous) || Character.isTitleCase(previous))) {
            return false;
        }
        if (index + 1 >= offset + count) {
            return true;
        }
        char next = value[index + 1];
        if (Character.isLowerCase(next) || Character.isUpperCase(next) || Character.isTitleCase(next)) {
            return false;
        }
        return true;
    }

    /**
     * Return the index of the specified character into the upperValues table.
     * The upperValues table contains three entries at each position. These
     * three characters are the upper case conversion. If only two characters
     * are used, the third character in the table is \u0000.
     * @return the index into the upperValues table, or -1
     */
    private static int upperIndex(int ch) {
        int index = -1;
        if (ch >= 0xdf) {
            if (ch <= 0x587) {
                switch (ch) {
                case 0xdf: return 0;
                case 0x149: return 1;
                case 0x1f0: return 2;
                case 0x390: return 3;
                case 0x3b0: return 4;
                case 0x587: return 5;
                }
            } else if (ch >= 0x1e96) {
                if (ch <= 0x1e9a) {
                    index = 6 + ch - 0x1e96;
                } else if (ch >= 0x1f50 && ch <= 0x1ffc) {
                    index = upperValues2[ch - 0x1f50];
                    if (index == 0) {
                        index = -1;
                    }
                } else if (ch >= 0xfb00) {
                    if (ch <= 0xfb06) {
                        index = 90 + ch - 0xfb00;
                    } else if (ch >= 0xfb13 && ch <= 0xfb17) {
                        index = 97 + ch - 0xfb13;
                    }
                }
            }
        }
        return index;
    }

    public static String toUpperCase(Locale locale, String s, char[] value, int offset, int count) {
        String languageCode = locale.getLanguage();
        if (languageCode.equals("tr") || languageCode.equals("az") || languageCode.equals("lt")) {
            return ICU.toUpperCase(s, locale.toString());
        }

        char[] output = null;
        int i = 0;
        for (int o = offset, end = offset + count; o < end; o++) {
            char ch = value[o];
            if (Character.isHighSurrogate(ch)) {
                return ICU.toUpperCase(s, locale.toString());
            }
            int index = upperIndex(ch);
            if (index == -1) {
                if (output != null && i >= output.length) {
                    char[] newoutput = new char[output.length + (count / 6) + 2];
                    System.arraycopy(output, 0, newoutput, 0, output.length);
                    output = newoutput;
                }
                char upch = Character.toUpperCase(ch);
                if (ch != upch) {
                    if (output == null) {
                        output = new char[count];
                        i = o - offset;
                        System.arraycopy(value, offset, output, 0, i);
                    }
                    output[i++] = upch;
                } else if (output != null) {
                    output[i++] = ch;
                }
            } else {
                int target = index * 3;
                char val3 = upperValues[target + 2];
                if (output == null) {
                    output = new char[count + (count / 6) + 2];
                    i = o - offset;
                    System.arraycopy(value, offset, output, 0, i);
                } else if (i + (val3 == 0 ? 1 : 2) >= output.length) {
                    char[] newoutput = new char[output.length + (count / 6) + 3];
                    System.arraycopy(output, 0, newoutput, 0, output.length);
                    output = newoutput;
                }

                char val = upperValues[target];
                output[i++] = val;
                val = upperValues[target + 1];
                output[i++] = val;
                if (val3 != 0) {
                    output[i++] = val3;
                }
            }
        }
        if (output == null) {
            return s;
        }
        return output.length == i || output.length - i < 8 ? new String(0, i, output) : new String(output, 0, i);
    }
}
