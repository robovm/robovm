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

package java.lang;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The wrapper for the primitive type {@code char}. This class also provides a
 * number of utility methods for working with characters.
 *
 * <p>Character data is kept up to date as Unicode evolves.
 * See the <a href="../util/Locale.html#locale_data">Locale data</a> section of
 * the {@code Locale} documentation for details of the Unicode versions implemented by current
 * and historical Android releases.
 *
 * <p>The Unicode specification, character tables, and other information are available at
 * <a href="http://www.unicode.org/">http://www.unicode.org/</a>.
 *
 * <p>Unicode characters are referred to as <i>code points</i>. The range of valid
 * code points is U+0000 to U+10FFFF. The <i>Basic Multilingual Plane (BMP)</i>
 * is the code point range U+0000 to U+FFFF. Characters above the BMP are
 * referred to as <i>Supplementary Characters</i>. On the Java platform, UTF-16
 * encoding and {@code char} pairs are used to represent code points in the
 * supplementary range. A pair of {@code char} values that represent a
 * supplementary character are made up of a <i>high surrogate</i> with a value
 * range of 0xD800 to 0xDBFF and a <i>low surrogate</i> with a value range of
 * 0xDC00 to 0xDFFF.
 * <p>
 * On the Java platform a {@code char} value represents either a single BMP code
 * point or a UTF-16 unit that's part of a surrogate pair. The {@code int} type
 * is used to represent all Unicode code points.
 *
 * <a name="unicode_categories"><h3>Unicode categories</h3></a>
 * <p>Here's a list of the Unicode character categories and the corresponding Java constant,
 * grouped semantically to provide a convenient overview. This table is also useful in
 * conjunction with {@code \p} and {@code \P} in {@link java.util.regex.Pattern regular expressions}.
 * <span class="datatable">
 * <style type="text/css">
 * .datatable td { padding-right: 20px; }
 * </style>
 * <p><table>
 * <tr> <td> Cn </td> <td> Unassigned </td>  <td>{@link #UNASSIGNED}</td> </tr>
 * <tr> <td> Cc </td> <td> Control </td>     <td>{@link #CONTROL}</td> </tr>
 * <tr> <td> Cf </td> <td> Format </td>      <td>{@link #FORMAT}</td> </tr>
 * <tr> <td> Co </td> <td> Private use </td> <td>{@link #PRIVATE_USE}</td> </tr>
 * <tr> <td> Cs </td> <td> Surrogate </td>   <td>{@link #SURROGATE}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Lu </td> <td> Uppercase letter </td> <td>{@link #UPPERCASE_LETTER}</td> </tr>
 * <tr> <td> Ll </td> <td> Lowercase letter </td> <td>{@link #LOWERCASE_LETTER}</td> </tr>
 * <tr> <td> Lt </td> <td> Titlecase letter </td> <td>{@link #TITLECASE_LETTER}</td> </tr>
 * <tr> <td> Lm </td> <td> Modifier letter </td>  <td>{@link #MODIFIER_LETTER}</td> </tr>
 * <tr> <td> Lo </td> <td> Other letter </td>     <td>{@link #OTHER_LETTER}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Mn </td> <td> Non-spacing mark </td>       <td>{@link #NON_SPACING_MARK}</td> </tr>
 * <tr> <td> Me </td> <td> Enclosing mark </td>         <td>{@link #ENCLOSING_MARK}</td> </tr>
 * <tr> <td> Mc </td> <td> Combining spacing mark </td> <td>{@link #COMBINING_SPACING_MARK}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Nd </td> <td> Decimal digit number </td> <td>{@link #DECIMAL_DIGIT_NUMBER}</td> </tr>
 * <tr> <td> Nl </td> <td> Letter number </td>        <td>{@link #LETTER_NUMBER}</td> </tr>
 * <tr> <td> No </td> <td> Other number </td>         <td>{@link #OTHER_NUMBER}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Pd </td> <td> Dash punctuation </td>          <td>{@link #DASH_PUNCTUATION}</td> </tr>
 * <tr> <td> Ps </td> <td> Start punctuation </td>         <td>{@link #START_PUNCTUATION}</td> </tr>
 * <tr> <td> Pe </td> <td> End punctuation </td>           <td>{@link #END_PUNCTUATION}</td> </tr>
 * <tr> <td> Pc </td> <td> Connector punctuation </td>     <td>{@link #CONNECTOR_PUNCTUATION}</td> </tr>
 * <tr> <td> Pi </td> <td> Initial quote punctuation </td> <td>{@link #INITIAL_QUOTE_PUNCTUATION}</td> </tr>
 * <tr> <td> Pf </td> <td> Final quote punctuation </td>   <td>{@link #FINAL_QUOTE_PUNCTUATION}</td> </tr>
 * <tr> <td> Po </td> <td> Other punctuation </td>         <td>{@link #OTHER_PUNCTUATION}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Sm </td> <td> Math symbol </td>     <td>{@link #MATH_SYMBOL}</td> </tr>
 * <tr> <td> Sc </td> <td> Currency symbol </td> <td>{@link #CURRENCY_SYMBOL}</td> </tr>
 * <tr> <td> Sk </td> <td> Modifier symbol </td> <td>{@link #MODIFIER_SYMBOL}</td> </tr>
 * <tr> <td> So </td> <td> Other symbol </td>    <td>{@link #OTHER_SYMBOL}</td> </tr>
 * <tr> <td><br></td> </tr>
 * <tr> <td> Zs </td> <td> Space separator </td>     <td>{@link #SPACE_SEPARATOR}</td> </tr>
 * <tr> <td> Zl </td> <td> Line separator </td>      <td>{@link #LINE_SEPARATOR}</td> </tr>
 * <tr> <td> Zp </td> <td> Paragraph separator </td> <td>{@link #PARAGRAPH_SEPARATOR}</td> </tr>
 * </table>
 * </span>
 *
 * @since 1.0
 */
@FindBugsSuppressWarnings("DM_NUMBER_CTOR")
public final class Character implements Serializable, Comparable<Character> {
    private static final long serialVersionUID = 3786198910865385080L;

    private final char value;

    /**
     * The minimum {@code Character} value.
     */
    public static final char MIN_VALUE = '\u0000';

    /**
     * The maximum {@code Character} value.
     */
    public static final char MAX_VALUE = '\uffff';

    /**
     * The minimum radix used for conversions between characters and integers.
     */
    public static final int MIN_RADIX = 2;

    /**
     * The maximum radix used for conversions between characters and integers.
     */
    public static final int MAX_RADIX = 36;

    /**
     * The {@link Class} object that represents the primitive type {@code char}.
     */
    @SuppressWarnings("unchecked")
    public static final Class<Character> TYPE
            = (Class<Character>) char[].class.getComponentType();
    // Note: Character.TYPE can't be set to "char.class", since *that* is
    // defined to be "java.lang.Character.TYPE";

    /**
     * Unicode category constant Cn.
     */
    public static final byte UNASSIGNED = 0;

    /**
     * Unicode category constant Lu.
     */
    public static final byte UPPERCASE_LETTER = 1;

    /**
     * Unicode category constant Ll.
     */
    public static final byte LOWERCASE_LETTER = 2;

    /**
     * Unicode category constant Lt.
     */
    public static final byte TITLECASE_LETTER = 3;

    /**
     * Unicode category constant Lm.
     */
    public static final byte MODIFIER_LETTER = 4;

    /**
     * Unicode category constant Lo.
     */
    public static final byte OTHER_LETTER = 5;

    /**
     * Unicode category constant Mn.
     */
    public static final byte NON_SPACING_MARK = 6;

    /**
     * Unicode category constant Me.
     */
    public static final byte ENCLOSING_MARK = 7;

    /**
     * Unicode category constant Mc.
     */
    public static final byte COMBINING_SPACING_MARK = 8;

    /**
     * Unicode category constant Nd.
     */
    public static final byte DECIMAL_DIGIT_NUMBER = 9;

    /**
     * Unicode category constant Nl.
     */
    public static final byte LETTER_NUMBER = 10;

    /**
     * Unicode category constant No.
     */
    public static final byte OTHER_NUMBER = 11;

    /**
     * Unicode category constant Zs.
     */
    public static final byte SPACE_SEPARATOR = 12;

    /**
     * Unicode category constant Zl.
     */
    public static final byte LINE_SEPARATOR = 13;

    /**
     * Unicode category constant Zp.
     */
    public static final byte PARAGRAPH_SEPARATOR = 14;

    /**
     * Unicode category constant Cc.
     */
    public static final byte CONTROL = 15;

    /**
     * Unicode category constant Cf.
     */
    public static final byte FORMAT = 16;

    /**
     * Unicode category constant Co.
     */
    public static final byte PRIVATE_USE = 18;

    /**
     * Unicode category constant Cs.
     */
    public static final byte SURROGATE = 19;

    /**
     * Unicode category constant Pd.
     */
    public static final byte DASH_PUNCTUATION = 20;

    /**
     * Unicode category constant Ps.
     */
    public static final byte START_PUNCTUATION = 21;

    /**
     * Unicode category constant Pe.
     */
    public static final byte END_PUNCTUATION = 22;

    /**
     * Unicode category constant Pc.
     */
    public static final byte CONNECTOR_PUNCTUATION = 23;

    /**
     * Unicode category constant Po.
     */
    public static final byte OTHER_PUNCTUATION = 24;

    /**
     * Unicode category constant Sm.
     */
    public static final byte MATH_SYMBOL = 25;

    /**
     * Unicode category constant Sc.
     */
    public static final byte CURRENCY_SYMBOL = 26;

    /**
     * Unicode category constant Sk.
     */
    public static final byte MODIFIER_SYMBOL = 27;

    /**
     * Unicode category constant So.
     */
    public static final byte OTHER_SYMBOL = 28;

    /**
     * Unicode category constant Pi.
     *
     * @since 1.4
     */
    public static final byte INITIAL_QUOTE_PUNCTUATION = 29;

    /**
     * Unicode category constant Pf.
     *
     * @since 1.4
     */
    public static final byte FINAL_QUOTE_PUNCTUATION = 30;

    /**
     * Unicode bidirectional constant.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_UNDEFINED = -1;

    /**
     * Unicode bidirectional constant L.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

    /**
     * Unicode bidirectional constant R.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

    /**
     * Unicode bidirectional constant AL.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

    /**
     * Unicode bidirectional constant EN.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;

    /**
     * Unicode bidirectional constant ES.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;

    /**
     * Unicode bidirectional constant ET.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;

    /**
     * Unicode bidirectional constant AN.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_ARABIC_NUMBER = 6;

    /**
     * Unicode bidirectional constant CS.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;

    /**
     * Unicode bidirectional constant NSM.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_NONSPACING_MARK = 8;

    /**
     * Unicode bidirectional constant BN.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;

    /**
     * Unicode bidirectional constant B.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;

    /**
     * Unicode bidirectional constant S.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

    /**
     * Unicode bidirectional constant WS.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_WHITESPACE = 12;

    /**
     * Unicode bidirectional constant ON.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_OTHER_NEUTRALS = 13;

    /**
     * Unicode bidirectional constant LRE.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;

    /**
     * Unicode bidirectional constant LRO.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;

    /**
     * Unicode bidirectional constant RLE.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;

    /**
     * Unicode bidirectional constant RLO.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;

    /**
     * Unicode bidirectional constant PDF.
     *
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;

    /**
     * The minimum value of a high surrogate or leading surrogate unit in UTF-16
     * encoding, {@code '\uD800'}.
     *
     * @since 1.5
     */
    public static final char MIN_HIGH_SURROGATE = '\uD800';

    /**
     * The maximum value of a high surrogate or leading surrogate unit in UTF-16
     * encoding, {@code '\uDBFF'}.
     *
     * @since 1.5
     */
    public static final char MAX_HIGH_SURROGATE = '\uDBFF';

    /**
     * The minimum value of a low surrogate or trailing surrogate unit in UTF-16
     * encoding, {@code '\uDC00'}.
     *
     * @since 1.5
     */
    public static final char MIN_LOW_SURROGATE = '\uDC00';

    /**
     * The maximum value of a low surrogate or trailing surrogate unit in UTF-16
     * encoding, {@code '\uDFFF'}.
     *
     * @since 1.5
     */
    public static final char MAX_LOW_SURROGATE = '\uDFFF';

    /**
     * The minimum value of a surrogate unit in UTF-16 encoding, {@code '\uD800'}.
     *
     * @since 1.5
     */
    public static final char MIN_SURROGATE = '\uD800';

    /**
     * The maximum value of a surrogate unit in UTF-16 encoding, {@code '\uDFFF'}.
     *
     * @since 1.5
     */
    public static final char MAX_SURROGATE = '\uDFFF';

    /**
     * The minimum value of a supplementary code point, {@code U+010000}.
     *
     * @since 1.5
     */
    public static final int MIN_SUPPLEMENTARY_CODE_POINT = 0x10000;

    /**
     * The minimum code point value, {@code U+0000}.
     *
     * @since 1.5
     */
    public static final int MIN_CODE_POINT = 0x000000;

    /**
     * The maximum code point value, {@code U+10FFFF}.
     *
     * @since 1.5
     */
    public static final int MAX_CODE_POINT = 0x10FFFF;

    /**
     * The number of bits required to represent a {@code Character} value
     * unsigned form.
     *
     * @since 1.5
     */
    public static final int SIZE = 16;

    private static final byte[] DIRECTIONALITY = new byte[] {
            DIRECTIONALITY_LEFT_TO_RIGHT, DIRECTIONALITY_RIGHT_TO_LEFT,
            DIRECTIONALITY_EUROPEAN_NUMBER,
            DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR,
            DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR,
            DIRECTIONALITY_ARABIC_NUMBER,
            DIRECTIONALITY_COMMON_NUMBER_SEPARATOR,
            DIRECTIONALITY_PARAGRAPH_SEPARATOR,
            DIRECTIONALITY_SEGMENT_SEPARATOR, DIRECTIONALITY_WHITESPACE,
            DIRECTIONALITY_OTHER_NEUTRALS,
            DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING,
            DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE,
            DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC,
            DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING,
            DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE,
            DIRECTIONALITY_POP_DIRECTIONAL_FORMAT,
            DIRECTIONALITY_NONSPACING_MARK, DIRECTIONALITY_BOUNDARY_NEUTRAL };

    /*
     * Represents a subset of the Unicode character set.
     */
    public static class Subset {
        String name;

        /**
         * Constructs a new {@code Subset}.
         *
         * @param string
         *            this subset's name.
         */
        protected Subset(String string) {
            if (string == null) {
                throw new NullPointerException();
            }
            name = string;
        }

        /**
         * Compares this character subset with the specified object. Uses
         * {@link java.lang.Object#equals(Object)} to do the comparison.
         *
         * @param object
         *            the object to compare this character subset with.
         * @return {@code true} if {@code object} is this subset, that is, if
         *         {@code object == this}; {@code false} otherwise.
         */
        @Override
        public final boolean equals(Object object) {
            return super.equals(object);
        }

        /**
         * Returns the integer hash code for this character subset.
         *
         * @return this subset's hash code, which is the hash code computed by
         *         {@link java.lang.Object#hashCode()}.
         */
        @Override
        public final int hashCode() {
            return super.hashCode();
        }

        /**
         * Returns the string representation of this subset.
         *
         * @return this subset's name.
         */
        @Override
        public final String toString() {
            return name;
        }
    }

    /**
     * Represents a block of Unicode characters, as defined by the Unicode 4.0.1
     * specification.
     *
     * @since 1.2
     */
    public static final class UnicodeBlock extends Subset {
        /**
         * The &quot;Surrogates Area&quot; Unicode Block.
         *
         * @deprecated As of Java 5, this block has been replaced by
         *             {@link #HIGH_SURROGATES},
         *             {@link #HIGH_PRIVATE_USE_SURROGATES} and
         *             {@link #LOW_SURROGATES}.
         */
        @Deprecated
        public static final UnicodeBlock SURROGATES_AREA = new UnicodeBlock("SURROGATES_AREA", 0x0, 0x0);
        /**
         * The &quot;Basic Latin&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock BASIC_LATIN = new UnicodeBlock("BASIC_LATIN", 0x0, 0x7f);
        /**
         * The &quot;Latin-1 Supplement&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LATIN_1_SUPPLEMENT = new UnicodeBlock("LATIN_1_SUPPLEMENT", 0x80, 0xff);
        /**
         * The &quot;Latin Extended-A&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LATIN_EXTENDED_A = new UnicodeBlock("LATIN_EXTENDED_A", 0x100, 0x17f);
        /**
         * The &quot;Latin Extended-B&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LATIN_EXTENDED_B = new UnicodeBlock("LATIN_EXTENDED_B", 0x180, 0x24f);
        /**
         * The &quot;IPA Extensions&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock IPA_EXTENSIONS = new UnicodeBlock("IPA_EXTENSIONS", 0x250, 0x2af);
        /**
         * The &quot;Spacing Modifier Letters&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock SPACING_MODIFIER_LETTERS = new UnicodeBlock("SPACING_MODIFIER_LETTERS", 0x2b0, 0x2ff);
        /**
         * The &quot;Combining Diacritical Marks&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS", 0x300, 0x36f);
        /**
         * The &quot;Greek and Coptic&quot; Unicode Block. Previously referred
         * to as &quot;Greek&quot;.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GREEK = new UnicodeBlock("GREEK", 0x370, 0x3ff);
        /**
         * The &quot;Cyrillic&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CYRILLIC = new UnicodeBlock("CYRILLIC", 0x400, 0x4ff);
        /**
         * The &quot;Cyrillic Supplement&quot; Unicode Block. Previously
         * referred to as &quot;Cyrillic Supplementary&quot;.
         *
         * @since 1.5
         */
        public static final UnicodeBlock CYRILLIC_SUPPLEMENTARY = new UnicodeBlock("CYRILLIC_SUPPLEMENTARY", 0x500, 0x52f);
        /**
         * The &quot;Armenian&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ARMENIAN = new UnicodeBlock("ARMENIAN", 0x530, 0x58f);
        /**
         * The &quot;Hebrew&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HEBREW = new UnicodeBlock("HEBREW", 0x590, 0x5ff);
        /**
         * The &quot;Arabic&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ARABIC = new UnicodeBlock("ARABIC", 0x600, 0x6ff);
        /**
         * The &quot;Syriac&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock SYRIAC = new UnicodeBlock("SYRIAC", 0x700, 0x74f);
        /**
         * The &quot;Thaana&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock THAANA = new UnicodeBlock("THAANA", 0x780, 0x7bf);
        /**
         * The &quot;Devanagari&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock DEVANAGARI = new UnicodeBlock("DEVANAGARI", 0x900, 0x97f);
        /**
         * The &quot;Bengali&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock BENGALI = new UnicodeBlock("BENGALI", 0x980, 0x9ff);
        /**
         * The &quot;Gurmukhi&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GURMUKHI = new UnicodeBlock("GURMUKHI", 0xa00, 0xa7f);
        /**
         * The &quot;Gujarati&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GUJARATI = new UnicodeBlock("GUJARATI", 0xa80, 0xaff);
        /**
         * The &quot;Oriya&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ORIYA = new UnicodeBlock("ORIYA", 0xb00, 0xb7f);
        /**
         * The &quot;Tamil&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock TAMIL = new UnicodeBlock("TAMIL", 0xb80, 0xbff);
        /**
         * The &quot;Telugu&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock TELUGU = new UnicodeBlock("TELUGU", 0xc00, 0xc7f);
        /**
         * The &quot;Kannada&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock KANNADA = new UnicodeBlock("KANNADA", 0xc80, 0xcff);
        /**
         * The &quot;Malayalam&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock MALAYALAM = new UnicodeBlock("MALAYALAM", 0xd00, 0xd7f);
        /**
         * The &quot;Sinhala&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock SINHALA = new UnicodeBlock("SINHALA", 0xd80, 0xdff);
        /**
         * The &quot;Thai&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock THAI = new UnicodeBlock("THAI", 0xe00, 0xe7f);
        /**
         * The &quot;Lao&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LAO = new UnicodeBlock("LAO", 0xe80, 0xeff);
        /**
         * The &quot;Tibetan&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock TIBETAN = new UnicodeBlock("TIBETAN", 0xf00, 0xfff);
        /**
         * The &quot;Myanmar&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock MYANMAR = new UnicodeBlock("MYANMAR", 0x1000, 0x109f);
        /**
         * The &quot;Georgian&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GEORGIAN = new UnicodeBlock("GEORGIAN", 0x10a0, 0x10ff);
        /**
         * The &quot;Hangul Jamo&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HANGUL_JAMO = new UnicodeBlock("HANGUL_JAMO", 0x1100, 0x11ff);
        /**
         * The &quot;Ethiopic&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock ETHIOPIC = new UnicodeBlock("ETHIOPIC", 0x1200, 0x137f);
        /**
         * The &quot;Cherokee&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock CHEROKEE = new UnicodeBlock("CHEROKEE", 0x13a0, 0x13ff);
        /**
         * The &quot;Unified Canadian Aboriginal Syllabics&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS", 0x1400, 0x167f);
        /**
         * The &quot;Ogham&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock OGHAM = new UnicodeBlock("OGHAM", 0x1680, 0x169f);
        /**
         * The &quot;Runic&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock RUNIC = new UnicodeBlock("RUNIC", 0x16a0, 0x16ff);
        /**
         * The &quot;Tagalog&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock TAGALOG = new UnicodeBlock("TAGALOG", 0x1700, 0x171f);
        /**
         * The &quot;Hanunoo&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock HANUNOO = new UnicodeBlock("HANUNOO", 0x1720, 0x173f);
        /**
         * The &quot;Buhid&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock BUHID = new UnicodeBlock("BUHID", 0x1740, 0x175f);
        /**
         * The &quot;Tagbanwa&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock TAGBANWA = new UnicodeBlock("TAGBANWA", 0x1760, 0x177f);
        /**
         * The &quot;Khmer&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock KHMER = new UnicodeBlock("KHMER", 0x1780, 0x17ff);
        /**
         * The &quot;Mongolian&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock MONGOLIAN = new UnicodeBlock("MONGOLIAN", 0x1800, 0x18af);
        /**
         * The &quot;Limbu&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock LIMBU = new UnicodeBlock("LIMBU", 0x1900, 0x194f);
        /**
         * The &quot;Tai Le&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock TAI_LE = new UnicodeBlock("TAI_LE", 0x1950, 0x197f);
        /**
         * The &quot;Khmer Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock KHMER_SYMBOLS = new UnicodeBlock("KHMER_SYMBOLS", 0x19e0, 0x19ff);
        /**
         * The &quot;Phonetic Extensions&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock PHONETIC_EXTENSIONS = new UnicodeBlock("PHONETIC_EXTENSIONS", 0x1d00, 0x1d7f);
        /**
         * The &quot;Latin Extended Additional&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LATIN_EXTENDED_ADDITIONAL = new UnicodeBlock("LATIN_EXTENDED_ADDITIONAL", 0x1e00, 0x1eff);
        /**
         * The &quot;Greek Extended&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GREEK_EXTENDED = new UnicodeBlock("GREEK_EXTENDED", 0x1f00, 0x1fff);
        /**
         * The &quot;General Punctuation&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GENERAL_PUNCTUATION = new UnicodeBlock("GENERAL_PUNCTUATION", 0x2000, 0x206f);
        /**
         * The &quot;Superscripts and Subscripts&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS = new UnicodeBlock("SUPERSCRIPTS_AND_SUBSCRIPTS", 0x2070, 0x209f);
        /**
         * The &quot;Currency Symbols&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CURRENCY_SYMBOLS = new UnicodeBlock("CURRENCY_SYMBOLS", 0x20a0, 0x20cf);
        /**
         * The &quot;Combining Diacritical Marks for Symbols&quot; Unicode
         * Block. Previously referred to as &quot;Combining Marks for
         * Symbols&quot;.
         *
         * @since 1.2
         */
        public static final UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS = new UnicodeBlock("COMBINING_MARKS_FOR_SYMBOLS", 0x20d0, 0x20ff);
        /**
         * The &quot;Letterlike Symbols&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LETTERLIKE_SYMBOLS = new UnicodeBlock("LETTERLIKE_SYMBOLS", 0x2100, 0x214f);
        /**
         * The &quot;Number Forms&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock NUMBER_FORMS = new UnicodeBlock("NUMBER_FORMS", 0x2150, 0x218f);
        /**
         * The &quot;Arrows&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ARROWS = new UnicodeBlock("ARROWS", 0x2190, 0x21ff);
        /**
         * The &quot;Mathematical Operators&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock MATHEMATICAL_OPERATORS = new UnicodeBlock("MATHEMATICAL_OPERATORS", 0x2200, 0x22ff);
        /**
         * The &quot;Miscellaneous Technical&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock MISCELLANEOUS_TECHNICAL = new UnicodeBlock("MISCELLANEOUS_TECHNICAL", 0x2300, 0x23ff);
        /**
         * The &quot;Control Pictures&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CONTROL_PICTURES = new UnicodeBlock("CONTROL_PICTURES", 0x2400, 0x243f);
        /**
         * The &quot;Optical Character Recognition&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock OPTICAL_CHARACTER_RECOGNITION = new UnicodeBlock("OPTICAL_CHARACTER_RECOGNITION", 0x2440, 0x245f);
        /**
         * The &quot;Enclosed Alphanumerics&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ENCLOSED_ALPHANUMERICS = new UnicodeBlock("ENCLOSED_ALPHANUMERICS", 0x2460, 0x24ff);
        /**
         * The &quot;Box Drawing&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock BOX_DRAWING = new UnicodeBlock("BOX_DRAWING", 0x2500, 0x257f);
        /**
         * The &quot;Block Elements&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock BLOCK_ELEMENTS = new UnicodeBlock("BLOCK_ELEMENTS", 0x2580, 0x259f);
        /**
         * The &quot;Geometric Shapes&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock GEOMETRIC_SHAPES = new UnicodeBlock("GEOMETRIC_SHAPES", 0x25a0, 0x25ff);
        /**
         * The &quot;Miscellaneous Symbols&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS", 0x2600, 0x26ff);
        /**
         * The &quot;Dingbats&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock DINGBATS = new UnicodeBlock("DINGBATS", 0x2700, 0x27bf);
        /**
         * The &quot;Miscellaneous Mathematical Symbols-A&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A", 0x27c0, 0x27ef);
        /**
         * The &quot;Supplemental Arrows-A&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_A = new UnicodeBlock("SUPPLEMENTAL_ARROWS_A", 0x27f0, 0x27ff);
        /**
         * The &quot;Braille Patterns&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock BRAILLE_PATTERNS = new UnicodeBlock("BRAILLE_PATTERNS", 0x2800, 0x28ff);
        /**
         * The &quot;Supplemental Arrows-B&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_B = new UnicodeBlock("SUPPLEMENTAL_ARROWS_B", 0x2900, 0x297f);
        /**
         * The &quot;Miscellaneous Mathematical Symbols-B&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B", 0x2980, 0x29ff);
        /**
         * The &quot;Supplemental Mathematical Operators&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SUPPLEMENTAL_MATHEMATICAL_OPERATORS = new UnicodeBlock("SUPPLEMENTAL_MATHEMATICAL_OPERATORS", 0x2a00, 0x2aff);
        /**
         * The &quot;Miscellaneous Symbols and Arrows&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_ARROWS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_ARROWS", 0x2b00, 0x2bff);
        /**
         * The &quot;CJK Radicals Supplement&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock CJK_RADICALS_SUPPLEMENT = new UnicodeBlock("CJK_RADICALS_SUPPLEMENT", 0x2e80, 0x2eff);
        /**
         * The &quot;Kangxi Radicals&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock KANGXI_RADICALS = new UnicodeBlock("KANGXI_RADICALS", 0x2f00, 0x2fdf);
        /**
         * The &quot;Ideographic Description Characters&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS = new UnicodeBlock("IDEOGRAPHIC_DESCRIPTION_CHARACTERS", 0x2ff0, 0x2fff);
        /**
         * The &quot;CJK Symbols and Punctuation&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("CJK_SYMBOLS_AND_PUNCTUATION", 0x3000, 0x303f);
        /**
         * The &quot;Hiragana&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HIRAGANA = new UnicodeBlock("HIRAGANA", 0x3040, 0x309f);
        /**
         * The &quot;Katakana&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock KATAKANA = new UnicodeBlock("KATAKANA", 0x30a0, 0x30ff);
        /**
         * The &quot;Bopomofo&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock BOPOMOFO = new UnicodeBlock("BOPOMOFO", 0x3100, 0x312f);
        /**
         * The &quot;Hangul Compatibility Jamo&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HANGUL_COMPATIBILITY_JAMO = new UnicodeBlock("HANGUL_COMPATIBILITY_JAMO", 0x3130, 0x318f);
        /**
         * The &quot;Kanbun&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock KANBUN = new UnicodeBlock("KANBUN", 0x3190, 0x319f);
        /**
         * The &quot;Bopomofo Extended&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock BOPOMOFO_EXTENDED = new UnicodeBlock("BOPOMOFO_EXTENDED", 0x31a0, 0x31bf);
        /**
         * The &quot;Katakana Phonetic Extensions&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock KATAKANA_PHONETIC_EXTENSIONS = new UnicodeBlock("KATAKANA_PHONETIC_EXTENSIONS", 0x31f0, 0x31ff);
        /**
         * The &quot;Enclosed CJK Letters and Months&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS = new UnicodeBlock("ENCLOSED_CJK_LETTERS_AND_MONTHS", 0x3200, 0x32ff);
        /**
         * The &quot;CJK Compatibility&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CJK_COMPATIBILITY = new UnicodeBlock("CJK_COMPATIBILITY", 0x3300, 0x33ff);
        /**
         * The &quot;CJK Unified Ideographs Extension A&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A", 0x3400, 0x4dbf);
        /**
         * The &quot;Yijing Hexagram Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock YIJING_HEXAGRAM_SYMBOLS = new UnicodeBlock("YIJING_HEXAGRAM_SYMBOLS", 0x4dc0, 0x4dff);
        /**
         * The &quot;CJK Unified Ideographs&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS", 0x4e00, 0x9fff);
        /**
         * The &quot;Yi Syllables&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock YI_SYLLABLES = new UnicodeBlock("YI_SYLLABLES", 0xa000, 0xa48f);
        /**
         * The &quot;Yi Radicals&quot; Unicode Block.
         *
         * @since 1.4
         */
        public static final UnicodeBlock YI_RADICALS = new UnicodeBlock("YI_RADICALS", 0xa490, 0xa4cf);
        /**
         * The &quot;Hangul Syllables&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HANGUL_SYLLABLES = new UnicodeBlock("HANGUL_SYLLABLES", 0xac00, 0xd7af);
        /**
         * The &quot;High Surrogates&quot; Unicode Block. This block represents
         * code point values in the high surrogate range 0xD800 to 0xDB7F
         */
        public static final UnicodeBlock HIGH_SURROGATES = new UnicodeBlock("HIGH_SURROGATES", 0xd800, 0xdb7f);
        /**
         * The &quot;High Private Use Surrogates&quot; Unicode Block. This block
         * represents code point values in the high surrogate range 0xDB80 to
         * 0xDBFF
         */
        public static final UnicodeBlock HIGH_PRIVATE_USE_SURROGATES = new UnicodeBlock("HIGH_PRIVATE_USE_SURROGATES", 0xdb80, 0xdbff);
        /**
         * The &quot;Low Surrogates&quot; Unicode Block. This block represents
         * code point values in the low surrogate range 0xDC00 to 0xDFFF
         */
        public static final UnicodeBlock LOW_SURROGATES = new UnicodeBlock("LOW_SURROGATES", 0xdc00, 0xdfff);
        /**
         * The &quot;Private Use Area&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock PRIVATE_USE_AREA = new UnicodeBlock("PRIVATE_USE_AREA", 0xe000, 0xf8ff);
        /**
         * The &quot;CJK Compatibility Ideographs&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS", 0xf900, 0xfaff);
        /**
         * The &quot;Alphabetic Presentation Forms&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ALPHABETIC_PRESENTATION_FORMS = new UnicodeBlock("ALPHABETIC_PRESENTATION_FORMS", 0xfb00, 0xfb4f);
        /**
         * The &quot;Arabic Presentation Forms-A&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_A = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_A", 0xfb50, 0xfdff);
        /**
         * The &quot;Variation Selectors&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock VARIATION_SELECTORS = new UnicodeBlock("VARIATION_SELECTORS", 0xfe00, 0xfe0f);
        /**
         * The &quot;Combining Half Marks&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock COMBINING_HALF_MARKS = new UnicodeBlock("COMBINING_HALF_MARKS", 0xfe20, 0xfe2f);
        /**
         * The &quot;CJK Compatibility Forms&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock CJK_COMPATIBILITY_FORMS = new UnicodeBlock("CJK_COMPATIBILITY_FORMS", 0xfe30, 0xfe4f);
        /**
         * The &quot;Small Form Variants&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock SMALL_FORM_VARIANTS = new UnicodeBlock("SMALL_FORM_VARIANTS", 0xfe50, 0xfe6f);
        /**
         * The &quot;Arabic Presentation Forms-B&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_B = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_B", 0xfe70, 0xfeff);
        /**
         * The &quot;Halfwidth and Fullwidth Forms&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS = new UnicodeBlock("HALFWIDTH_AND_FULLWIDTH_FORMS", 0xff00, 0xffef);
        /**
         * The &quot;Specials&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock SPECIALS = new UnicodeBlock("SPECIALS", 0xfff0, 0xffff);
        /**
         * The &quot;Linear B Syllabary&quot; Unicode Block.
         *
         * @since 1.2
         */
        public static final UnicodeBlock LINEAR_B_SYLLABARY = new UnicodeBlock("LINEAR_B_SYLLABARY", 0x10000, 0x1007f);
        /**
         * The &quot;Linear B Ideograms&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock LINEAR_B_IDEOGRAMS = new UnicodeBlock("LINEAR_B_IDEOGRAMS", 0x10080, 0x100ff);
        /**
         * The &quot;Aegean Numbers&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock AEGEAN_NUMBERS = new UnicodeBlock("AEGEAN_NUMBERS", 0x10100, 0x1013f);
        /**
         * The &quot;Old Italic&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock OLD_ITALIC = new UnicodeBlock("OLD_ITALIC", 0x10300, 0x1032f);
        /**
         * The &quot;Gothic&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock GOTHIC = new UnicodeBlock("GOTHIC", 0x10330, 0x1034f);
        /**
         * The &quot;Ugaritic&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock UGARITIC = new UnicodeBlock("UGARITIC", 0x10380, 0x1039f);
        /**
         * The &quot;Deseret&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock DESERET = new UnicodeBlock("DESERET", 0x10400, 0x1044f);
        /**
         * The &quot;Shavian&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SHAVIAN = new UnicodeBlock("SHAVIAN", 0x10450, 0x1047f);
        /**
         * The &quot;Osmanya&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock OSMANYA = new UnicodeBlock("OSMANYA", 0x10480, 0x104af);
        /**
         * The &quot;Cypriot Syllabary&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock CYPRIOT_SYLLABARY = new UnicodeBlock("CYPRIOT_SYLLABARY", 0x10800, 0x1083f);
        /**
         * The &quot;Byzantine Musical Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock BYZANTINE_MUSICAL_SYMBOLS = new UnicodeBlock("BYZANTINE_MUSICAL_SYMBOLS", 0x1d000, 0x1d0ff);
        /**
         * The &quot;Musical Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock MUSICAL_SYMBOLS = new UnicodeBlock("MUSICAL_SYMBOLS", 0x1d100, 0x1d1ff);
        /**
         * The &quot;Tai Xuan Jing Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock TAI_XUAN_JING_SYMBOLS = new UnicodeBlock("TAI_XUAN_JING_SYMBOLS", 0x1d300, 0x1d35f);
        /**
         * The &quot;Mathematical Alphanumeric Symbols&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock MATHEMATICAL_ALPHANUMERIC_SYMBOLS = new UnicodeBlock("MATHEMATICAL_ALPHANUMERIC_SYMBOLS", 0x1d400, 0x1d7ff);
        /**
         * The &quot;CJK Unified Ideographs Extension B&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B", 0x20000, 0x2a6df);
        /**
         * The &quot;CJK Compatibility Ideographs Supplement&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT", 0x2f800, 0x2fa1f);
        /**
         * The &quot;Tags&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock TAGS = new UnicodeBlock("TAGS", 0xe0000, 0xe007f);
        /**
         * The &quot;Variation Selectors Supplement&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock VARIATION_SELECTORS_SUPPLEMENT = new UnicodeBlock("VARIATION_SELECTORS_SUPPLEMENT", 0xe0100, 0xe01ef);
        /**
         * The &quot;Supplementary Private Use Area-A&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_A = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_A", 0xf0000, 0xfffff);
        /**
         * The &quot;Supplementary Private Use Area-B&quot; Unicode Block.
         *
         * @since 1.5
         */
        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_B = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_B", 0x100000, 0x10ffff);

        /*
         * All of the UnicodeBlocks with valid ranges in ascending order.
         */
        private static UnicodeBlock[] BLOCKS = new UnicodeBlock[] {
            null,
            UnicodeBlock.BASIC_LATIN,
            UnicodeBlock.LATIN_1_SUPPLEMENT,
            UnicodeBlock.LATIN_EXTENDED_A,
            UnicodeBlock.LATIN_EXTENDED_B,
            UnicodeBlock.IPA_EXTENSIONS,
            UnicodeBlock.SPACING_MODIFIER_LETTERS,
            UnicodeBlock.COMBINING_DIACRITICAL_MARKS,
            UnicodeBlock.GREEK,
            UnicodeBlock.CYRILLIC,
            UnicodeBlock.ARMENIAN,
            UnicodeBlock.HEBREW,
            UnicodeBlock.ARABIC,
            UnicodeBlock.SYRIAC,
            UnicodeBlock.THAANA,
            UnicodeBlock.DEVANAGARI,
            UnicodeBlock.BENGALI,
            UnicodeBlock.GURMUKHI,
            UnicodeBlock.GUJARATI,
            UnicodeBlock.ORIYA,
            UnicodeBlock.TAMIL,
            UnicodeBlock.TELUGU,
            UnicodeBlock.KANNADA,
            UnicodeBlock.MALAYALAM,
            UnicodeBlock.SINHALA,
            UnicodeBlock.THAI,
            UnicodeBlock.LAO,
            UnicodeBlock.TIBETAN,
            UnicodeBlock.MYANMAR,
            UnicodeBlock.GEORGIAN,
            UnicodeBlock.HANGUL_JAMO,
            UnicodeBlock.ETHIOPIC,
            UnicodeBlock.CHEROKEE,
            UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS,
            UnicodeBlock.OGHAM,
            UnicodeBlock.RUNIC,
            UnicodeBlock.KHMER,
            UnicodeBlock.MONGOLIAN,
            UnicodeBlock.LATIN_EXTENDED_ADDITIONAL,
            UnicodeBlock.GREEK_EXTENDED,
            UnicodeBlock.GENERAL_PUNCTUATION,
            UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS,
            UnicodeBlock.CURRENCY_SYMBOLS,
            UnicodeBlock.COMBINING_MARKS_FOR_SYMBOLS,
            UnicodeBlock.LETTERLIKE_SYMBOLS,
            UnicodeBlock.NUMBER_FORMS,
            UnicodeBlock.ARROWS,
            UnicodeBlock.MATHEMATICAL_OPERATORS,
            UnicodeBlock.MISCELLANEOUS_TECHNICAL,
            UnicodeBlock.CONTROL_PICTURES,
            UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION,
            UnicodeBlock.ENCLOSED_ALPHANUMERICS,
            UnicodeBlock.BOX_DRAWING,
            UnicodeBlock.BLOCK_ELEMENTS,
            UnicodeBlock.GEOMETRIC_SHAPES,
            UnicodeBlock.MISCELLANEOUS_SYMBOLS,
            UnicodeBlock.DINGBATS,
            UnicodeBlock.BRAILLE_PATTERNS,
            UnicodeBlock.CJK_RADICALS_SUPPLEMENT,
            UnicodeBlock.KANGXI_RADICALS,
            UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS,
            UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION,
            UnicodeBlock.HIRAGANA,
            UnicodeBlock.KATAKANA,
            UnicodeBlock.BOPOMOFO,
            UnicodeBlock.HANGUL_COMPATIBILITY_JAMO,
            UnicodeBlock.KANBUN,
            UnicodeBlock.BOPOMOFO_EXTENDED,
            UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS,
            UnicodeBlock.CJK_COMPATIBILITY,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS,
            UnicodeBlock.YI_SYLLABLES,
            UnicodeBlock.YI_RADICALS,
            UnicodeBlock.HANGUL_SYLLABLES,
            UnicodeBlock.HIGH_SURROGATES,
            UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES,
            UnicodeBlock.LOW_SURROGATES,
            UnicodeBlock.PRIVATE_USE_AREA,
            UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS,
            UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS,
            UnicodeBlock.ARABIC_PRESENTATION_FORMS_A,
            UnicodeBlock.COMBINING_HALF_MARKS,
            UnicodeBlock.CJK_COMPATIBILITY_FORMS,
            UnicodeBlock.SMALL_FORM_VARIANTS,
            UnicodeBlock.ARABIC_PRESENTATION_FORMS_B,
            UnicodeBlock.SPECIALS,
            UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS,
            UnicodeBlock.OLD_ITALIC,
            UnicodeBlock.GOTHIC,
            UnicodeBlock.DESERET,
            UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS,
            UnicodeBlock.MUSICAL_SYMBOLS,
            UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B,
            UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT,
            UnicodeBlock.TAGS,
            UnicodeBlock.CYRILLIC_SUPPLEMENTARY,
            UnicodeBlock.TAGALOG,
            UnicodeBlock.HANUNOO,
            UnicodeBlock.BUHID,
            UnicodeBlock.TAGBANWA,
            UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A,
            UnicodeBlock.SUPPLEMENTAL_ARROWS_A,
            UnicodeBlock.SUPPLEMENTAL_ARROWS_B,
            UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B,
            UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS,
            UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS,
            UnicodeBlock.VARIATION_SELECTORS,
            UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A,
            UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B,
            UnicodeBlock.LIMBU,
            UnicodeBlock.TAI_LE,
            UnicodeBlock.KHMER_SYMBOLS,
            UnicodeBlock.PHONETIC_EXTENSIONS,
            UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS,
            UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS,
            UnicodeBlock.LINEAR_B_SYLLABARY,
            UnicodeBlock.LINEAR_B_IDEOGRAMS,
            UnicodeBlock.AEGEAN_NUMBERS,
            UnicodeBlock.UGARITIC,
            UnicodeBlock.SHAVIAN,
            UnicodeBlock.OSMANYA,
            UnicodeBlock.CYPRIOT_SYLLABARY,
            UnicodeBlock.TAI_XUAN_JING_SYMBOLS,
            UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT
        };

        /**
         * Retrieves the constant that corresponds to the specified block name.
         * The block names are defined by the Unicode 4.0.1 specification in the
         * {@code Blocks-4.0.1.txt} file.
         * <p>
         * Block names may be one of the following:
         * <ul>
         * <li>Canonical block name, as defined by the Unicode specification;
         * case-insensitive.</li>
         * <li>Canonical block name without any spaces, as defined by the
         * Unicode specification; case-insensitive.</li>
         * <li>{@code UnicodeBlock} constant identifier. This is determined by
         * uppercasing the canonical name and replacing all spaces and hyphens
         * with underscores.</li>
         * </ul>
         *
         * @param blockName
         *            the name of the block to retrieve.
         * @return the UnicodeBlock constant corresponding to {@code blockName}.
         * @throws NullPointerException
         *             if {@code blockName} is {@code null}.
         * @throws IllegalArgumentException
         *             if {@code blockName} is not a valid block name.
         * @since 1.5
         */
        public static UnicodeBlock forName(String blockName) {
            if (blockName == null) {
                throw new NullPointerException();
            }
            int block = forNameImpl(blockName);
            if (block == -1) {
                if (blockName.equals("SURROGATES_AREA")) {
                    return SURROGATES_AREA;
                } else if(blockName.equalsIgnoreCase("greek")) {
                    return GREEK;
                } else if(blockName.equals("COMBINING_MARKS_FOR_SYMBOLS") ||
                        blockName.equals("Combining Marks for Symbols") ||
                        blockName.equals("CombiningMarksforSymbols")) {
                    return COMBINING_MARKS_FOR_SYMBOLS;
                }
                throw new IllegalArgumentException("Bad block name: " + blockName);
            }
            return BLOCKS[block];
        }

        /**
         * Gets the constant for the Unicode block that contains the specified
         * character.
         *
         * @param c
         *            the character for which to get the {@code UnicodeBlock}
         *            constant.
         * @return the {@code UnicodeBlock} constant for the block that contains
         *         {@code c}, or {@code null} if {@code c} does not belong to
         *         any defined block.
         */
        public static UnicodeBlock of(char c) {
            return of((int) c);
        }

        /**
         * Gets the constant for the Unicode block that contains the specified
         * Unicode code point.
         *
         * @param codePoint
         *            the Unicode code point for which to get the
         *            {@code UnicodeBlock} constant.
         * @return the {@code UnicodeBlock} constant for the block that contains
         *         {@code codePoint}, or {@code null} if {@code codePoint} does
         *         not belong to any defined block.
         * @throws IllegalArgumentException if {@code codePoint} is not a valid code point.
         * @since 1.5
         */
        public static UnicodeBlock of(int codePoint) {
            checkValidCodePoint(codePoint);
            int block = ofImpl(codePoint);
            if (block == -1 || block >= BLOCKS.length) {
                return null;
            }
            return BLOCKS[block];
        }

        private UnicodeBlock(String blockName, int start, int end) {
            super(blockName);
        }
    }

    private static native int forNameImpl(String blockName);

    private static native int ofImpl(int codePoint);

    /**
     * Constructs a new {@code Character} with the specified primitive char
     * value.
     *
     * @param value
     *            the primitive char value to store in the new instance.
     */
    public Character(char value) {
        this.value = value;
    }

    /**
     * Gets the primitive value of this character.
     *
     * @return this object's primitive value.
     */
    public char charValue() {
        return value;
    }

    private static void checkValidCodePoint(int codePoint) {
        if (!isValidCodePoint(codePoint)) {
            throw new IllegalArgumentException("Invalid code point: " + codePoint);
        }
    }

    /**
     * Compares this object to the specified character object to determine their
     * relative order.
     *
     * @param c
     *            the character object to compare this object to.
     * @return {@code 0} if the value of this character and the value of
     *         {@code c} are equal; a positive value if the value of this
     *         character is greater than the value of {@code c}; a negative
     *         value if the value of this character is less than the value of
     *         {@code c}.
     * @see java.lang.Comparable
     * @since 1.2
     */
    public int compareTo(Character c) {
        return compare(value, c.value);
    }

    /**
     * Compares two {@code char} values.
     * @return 0 if lhs = rhs, less than 0 if lhs &lt; rhs, and greater than 0 if lhs &gt; rhs.
     * @since 1.7
     * @hide 1.7
     */
    public static int compare(char lhs, char rhs) {
        return lhs - rhs;
    }

    /**
     * Returns a {@code Character} instance for the {@code char} value passed.
     * <p>
     * If it is not necessary to get a new {@code Character} instance, it is
     * recommended to use this method instead of the constructor, since it
     * maintains a cache of instances which may result in better performance.
     *
     * @param c
     *            the char value for which to get a {@code Character} instance.
     * @return the {@code Character} instance for {@code c}.
     * @since 1.5
     */
    public static Character valueOf(char c) {
        return c < 128 ? SMALL_VALUES[c] : new Character(c);
    }

    /**
     * A cache of instances used by {@link #valueOf(char)} and auto-boxing
     */
    private static final Character[] SMALL_VALUES = new Character[128];

    static {
        for (int i = 0; i < 128; i++) {
            SMALL_VALUES[i] = new Character((char) i);
        }
    }
    /**
     * Indicates whether {@code codePoint} is a valid Unicode code point.
     *
     * @param codePoint
     *            the code point to test.
     * @return {@code true} if {@code codePoint} is a valid Unicode code point;
     *         {@code false} otherwise.
     * @since 1.5
     */
    public static boolean isValidCodePoint(int codePoint) {
        return (MIN_CODE_POINT <= codePoint && MAX_CODE_POINT >= codePoint);
    }

    /**
     * Indicates whether {@code codePoint} is within the supplementary code
     * point range.
     *
     * @param codePoint
     *            the code point to test.
     * @return {@code true} if {@code codePoint} is within the supplementary
     *         code point range; {@code false} otherwise.
     * @since 1.5
     */
    public static boolean isSupplementaryCodePoint(int codePoint) {
        return (MIN_SUPPLEMENTARY_CODE_POINT <= codePoint && MAX_CODE_POINT >= codePoint);
    }

    /**
     * Indicates whether {@code ch} is a high- (or leading-) surrogate code unit
     * that is used for representing supplementary characters in UTF-16
     * encoding.
     *
     * @param ch
     *            the character to test.
     * @return {@code true} if {@code ch} is a high-surrogate code unit;
     *         {@code false} otherwise.
     * @see #isLowSurrogate(char)
     * @since 1.5
     */
    public static boolean isHighSurrogate(char ch) {
        return (MIN_HIGH_SURROGATE <= ch && MAX_HIGH_SURROGATE >= ch);
    }

    /**
     * Indicates whether {@code ch} is a low- (or trailing-) surrogate code unit
     * that is used for representing supplementary characters in UTF-16
     * encoding.
     *
     * @param ch
     *            the character to test.
     * @return {@code true} if {@code ch} is a low-surrogate code unit;
     *         {@code false} otherwise.
     * @see #isHighSurrogate(char)
     * @since 1.5
     */
    public static boolean isLowSurrogate(char ch) {
        return (MIN_LOW_SURROGATE <= ch && MAX_LOW_SURROGATE >= ch);
    }

    /**
     * Tests whether the given character is a high or low surrogate.
     * @since 1.7
     * @hide 1.7
     */
    public static boolean isSurrogate(char ch) {
        return ch >= MIN_SURROGATE && ch <= MAX_SURROGATE;
    }

    /**
     * Indicates whether the specified character pair is a valid surrogate pair.
     *
     * @param high
     *            the high surrogate unit to test.
     * @param low
     *            the low surrogate unit to test.
     * @return {@code true} if {@code high} is a high-surrogate code unit and
     *         {@code low} is a low-surrogate code unit; {@code false}
     *         otherwise.
     * @see #isHighSurrogate(char)
     * @see #isLowSurrogate(char)
     * @since 1.5
     */
    public static boolean isSurrogatePair(char high, char low) {
        return (isHighSurrogate(high) && isLowSurrogate(low));
    }

    /**
     * Calculates the number of {@code char} values required to represent the
     * specified Unicode code point. This method checks if the {@code codePoint}
     * is greater than or equal to {@code 0x10000}, in which case {@code 2} is
     * returned, otherwise {@code 1}. To test if the code point is valid, use
     * the {@link #isValidCodePoint(int)} method.
     *
     * @param codePoint
     *            the code point for which to calculate the number of required
     *            chars.
     * @return {@code 2} if {@code codePoint >= 0x10000}; {@code 1} otherwise.
     * @see #isValidCodePoint(int)
     * @see #isSupplementaryCodePoint(int)
     * @since 1.5
     */
    public static int charCount(int codePoint) {
        return (codePoint >= 0x10000 ? 2 : 1);
    }

    /**
     * Converts a surrogate pair into a Unicode code point. This method assumes
     * that the pair are valid surrogates. If the pair are <i>not</i> valid
     * surrogates, then the result is indeterminate. The
     * {@link #isSurrogatePair(char, char)} method should be used prior to this
     * method to validate the pair.
     *
     * @param high
     *            the high surrogate unit.
     * @param low
     *            the low surrogate unit.
     * @return the Unicode code point corresponding to the surrogate unit pair.
     * @see #isSurrogatePair(char, char)
     * @since 1.5
     */
    public static int toCodePoint(char high, char low) {
        // See RFC 2781, Section 2.2
        // http://www.ietf.org/rfc/rfc2781.txt
        int h = (high & 0x3FF) << 10;
        int l = low & 0x3FF;
        return (h | l) + 0x10000;
    }

    /**
     * Returns the code point at {@code index} in the specified sequence of
     * character units. If the unit at {@code index} is a high-surrogate unit,
     * {@code index + 1} is less than the length of the sequence and the unit at
     * {@code index + 1} is a low-surrogate unit, then the supplementary code
     * point represented by the pair is returned; otherwise the {@code char}
     * value at {@code index} is returned.
     *
     * @param seq
     *            the source sequence of {@code char} units.
     * @param index
     *            the position in {@code seq} from which to retrieve the code
     *            point.
     * @return the Unicode code point or {@code char} value at {@code index} in
     *         {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is negative or greater than or equal to
     *             the length of {@code seq}.
     * @since 1.5
     */
    public static int codePointAt(CharSequence seq, int index) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length();
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }

        char high = seq.charAt(index++);
        if (index >= len) {
            return high;
        }
        char low = seq.charAt(index);
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return high;
    }

    /**
     * Returns the code point at {@code index} in the specified array of
     * character units. If the unit at {@code index} is a high-surrogate unit,
     * {@code index + 1} is less than the length of the array and the unit at
     * {@code index + 1} is a low-surrogate unit, then the supplementary code
     * point represented by the pair is returned; otherwise the {@code char}
     * value at {@code index} is returned.
     *
     * @param seq
     *            the source array of {@code char} units.
     * @param index
     *            the position in {@code seq} from which to retrieve the code
     *            point.
     * @return the Unicode code point or {@code char} value at {@code index} in
     *         {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is negative or greater than or equal to
     *             the length of {@code seq}.
     * @since 1.5
     */
    public static int codePointAt(char[] seq, int index) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length;
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }

        char high = seq[index++];
        if (index >= len) {
            return high;
        }
        char low = seq[index];
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return high;
    }

    /**
     * Returns the code point at {@code index} in the specified array of
     * character units, where {@code index} has to be less than {@code limit}.
     * If the unit at {@code index} is a high-surrogate unit, {@code index + 1}
     * is less than {@code limit} and the unit at {@code index + 1} is a
     * low-surrogate unit, then the supplementary code point represented by the
     * pair is returned; otherwise the {@code char} value at {@code index} is
     * returned.
     *
     * @param seq
     *            the source array of {@code char} units.
     * @param index
     *            the position in {@code seq} from which to get the code point.
     * @param limit
     *            the index after the last unit in {@code seq} that can be used.
     * @return the Unicode code point or {@code char} value at {@code index} in
     *         {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code index < 0}, {@code index >= limit},
     *             {@code limit < 0} or if {@code limit} is greater than the
     *             length of {@code seq}.
     * @since 1.5
     */
    public static int codePointAt(char[] seq, int index, int limit) {
        if (index < 0 || index >= limit || limit < 0 || limit > seq.length) {
            throw new IndexOutOfBoundsException();
        }

        char high = seq[index++];
        if (index >= limit) {
            return high;
        }
        char low = seq[index];
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return high;
    }

    /**
     * Returns the code point that precedes {@code index} in the specified
     * sequence of character units. If the unit at {@code index - 1} is a
     * low-surrogate unit, {@code index - 2} is not negative and the unit at
     * {@code index - 2} is a high-surrogate unit, then the supplementary code
     * point represented by the pair is returned; otherwise the {@code char}
     * value at {@code index - 1} is returned.
     *
     * @param seq
     *            the source sequence of {@code char} units.
     * @param index
     *            the position in {@code seq} following the code
     *            point that should be returned.
     * @return the Unicode code point or {@code char} value before {@code index}
     *         in {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is less than 1 or greater than the
     *             length of {@code seq}.
     * @since 1.5
     */
    public static int codePointBefore(CharSequence seq, int index) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length();
        if (index < 1 || index > len) {
            throw new IndexOutOfBoundsException();
        }

        char low = seq.charAt(--index);
        if (--index < 0) {
            return low;
        }
        char high = seq.charAt(index);
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return low;
    }

    /**
     * Returns the code point that precedes {@code index} in the specified
     * array of character units. If the unit at {@code index - 1} is a
     * low-surrogate unit, {@code index - 2} is not negative and the unit at
     * {@code index - 2} is a high-surrogate unit, then the supplementary code
     * point represented by the pair is returned; otherwise the {@code char}
     * value at {@code index - 1} is returned.
     *
     * @param seq
     *            the source array of {@code char} units.
     * @param index
     *            the position in {@code seq} following the code
     *            point that should be returned.
     * @return the Unicode code point or {@code char} value before {@code index}
     *         in {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if the {@code index} is less than 1 or greater than the
     *             length of {@code seq}.
     * @since 1.5
     */
    public static int codePointBefore(char[] seq, int index) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length;
        if (index < 1 || index > len) {
            throw new IndexOutOfBoundsException();
        }

        char low = seq[--index];
        if (--index < 0) {
            return low;
        }
        char high = seq[index];
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return low;
    }

    /**
     * Returns the code point that precedes the {@code index} in the specified
     * array of character units and is not less than {@code start}. If the unit
     * at {@code index - 1} is a low-surrogate unit, {@code index - 2} is not
     * less than {@code start} and the unit at {@code index - 2} is a
     * high-surrogate unit, then the supplementary code point represented by the
     * pair is returned; otherwise the {@code char} value at {@code index - 1}
     * is returned.
     *
     * @param seq
     *            the source array of {@code char} units.
     * @param index
     *            the position in {@code seq} following the code point that
     *            should be returned.
     * @param start
     *            the index of the first element in {@code seq}.
     * @return the Unicode code point or {@code char} value before {@code index}
     *         in {@code seq}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if the {@code index <= start}, {@code start < 0},
     *             {@code index} is greater than the length of {@code seq}, or
     *             if {@code start} is equal or greater than the length of
     *             {@code seq}.
     * @since 1.5
     */
    public static int codePointBefore(char[] seq, int index, int start) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length;
        if (index <= start || index > len || start < 0 || start >= len) {
            throw new IndexOutOfBoundsException();
        }

        char low = seq[--index];
        if (--index < start) {
            return low;
        }
        char high = seq[index];
        if (isSurrogatePair(high, low)) {
            return toCodePoint(high, low);
        }
        return low;
    }

    /**
     * Converts the specified Unicode code point into a UTF-16 encoded sequence
     * and copies the value(s) into the char array {@code dst}, starting at
     * index {@code dstIndex}.
     *
     * @param codePoint
     *            the Unicode code point to encode.
     * @param dst
     *            the destination array to copy the encoded value into.
     * @param dstIndex
     *            the index in {@code dst} from where to start copying.
     * @return the number of {@code char} value units copied into {@code dst}.
     * @throws IllegalArgumentException if {@code codePoint} is not a valid code point.
     * @throws NullPointerException
     *             if {@code dst} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code dstIndex} is negative, greater than or equal to
     *             {@code dst.length} or equals {@code dst.length - 1} when
     *             {@code codePoint} is a
     *             {@link #isSupplementaryCodePoint(int) supplementary code point}.
     * @since 1.5
     */
    public static int toChars(int codePoint, char[] dst, int dstIndex) {
        checkValidCodePoint(codePoint);
        if (dst == null) {
            throw new NullPointerException();
        }
        if (dstIndex < 0 || dstIndex >= dst.length) {
            throw new IndexOutOfBoundsException();
        }

        if (isSupplementaryCodePoint(codePoint)) {
            if (dstIndex == dst.length - 1) {
                throw new IndexOutOfBoundsException();
            }
            // See RFC 2781, Section 2.1
            // http://www.ietf.org/rfc/rfc2781.txt
            int cpPrime = codePoint - 0x10000;
            int high = 0xD800 | ((cpPrime >> 10) & 0x3FF);
            int low = 0xDC00 | (cpPrime & 0x3FF);
            dst[dstIndex] = (char) high;
            dst[dstIndex + 1] = (char) low;
            return 2;
        }

        dst[dstIndex] = (char) codePoint;
        return 1;
    }

    /**
     * Converts the specified Unicode code point into a UTF-16 encoded sequence
     * and returns it as a char array.
     *
     * @param codePoint
     *            the Unicode code point to encode.
     * @return the UTF-16 encoded char sequence. If {@code codePoint} is a
     *         {@link #isSupplementaryCodePoint(int) supplementary code point},
     *         then the returned array contains two characters, otherwise it
     *         contains just one character.
     * @throws IllegalArgumentException if {@code codePoint} is not a valid code point.
     * @since 1.5
     */
    public static char[] toChars(int codePoint) {
        checkValidCodePoint(codePoint);
        if (isSupplementaryCodePoint(codePoint)) {
            int cpPrime = codePoint - 0x10000;
            int high = 0xD800 | ((cpPrime >> 10) & 0x3FF);
            int low = 0xDC00 | (cpPrime & 0x3FF);
            return new char[] { (char) high, (char) low };
        }
        return new char[] { (char) codePoint };
    }

    /**
     * Counts the number of Unicode code points in the subsequence of the
     * specified character sequence, as delineated by {@code beginIndex} and
     * {@code endIndex}. Any surrogate values with missing pair values will be
     * counted as one code point.
     *
     * @param seq
     *            the {@code CharSequence} to look through.
     * @param beginIndex
     *            the inclusive index to begin counting at.
     * @param endIndex
     *            the exclusive index to stop counting at.
     * @return the number of Unicode code points.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code beginIndex < 0}, {@code beginIndex > endIndex} or
     *             if {@code endIndex} is greater than the length of {@code seq}.
     * @since 1.5
     */
    public static int codePointCount(CharSequence seq, int beginIndex,
            int endIndex) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length();
        if (beginIndex < 0 || endIndex > len || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }

        int result = 0;
        for (int i = beginIndex; i < endIndex; i++) {
            char c = seq.charAt(i);
            if (isHighSurrogate(c)) {
                if (++i < endIndex) {
                    c = seq.charAt(i);
                    if (!isLowSurrogate(c)) {
                        result++;
                    }
                }
            }
            result++;
        }
        return result;
    }

    /**
     * Counts the number of Unicode code points in the subsequence of the
     * specified char array, as delineated by {@code offset} and {@code count}.
     * Any surrogate values with missing pair values will be counted as one code
     * point.
     *
     * @param seq
     *            the char array to look through
     * @param offset
     *            the inclusive index to begin counting at.
     * @param count
     *            the number of {@code char} values to look through in
     *            {@code seq}.
     * @return the number of Unicode code points.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code offset < 0}, {@code count < 0} or if
     *             {@code offset + count} is greater than the length of
     *             {@code seq}.
     * @since 1.5
     */
    public static int codePointCount(char[] seq, int offset, int count) {
        Arrays.checkOffsetAndCount(seq.length, offset, count);
        int endIndex = offset + count;
        int result = 0;
        for (int i = offset; i < endIndex; i++) {
            char c = seq[i];
            if (isHighSurrogate(c)) {
                if (++i < endIndex) {
                    c = seq[i];
                    if (!isLowSurrogate(c)) {
                        result++;
                    }
                }
            }
            result++;
        }
        return result;
    }

    /**
     * Determines the index in the specified character sequence that is offset
     * {@code codePointOffset} code points from {@code index}.
     *
     * @param seq
     *            the character sequence to find the index in.
     * @param index
     *            the start index in {@code seq}.
     * @param codePointOffset
     *            the number of code points to look backwards or forwards; may
     *            be a negative or positive value.
     * @return the index in {@code seq} that is {@code codePointOffset} code
     *         points away from {@code index}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code index < 0}, {@code index} is greater than the
     *             length of {@code seq}, or if there are not enough values in
     *             {@code seq} to skip {@code codePointOffset} code points
     *             forwards or backwards (if {@code codePointOffset} is
     *             negative) from {@code index}.
     * @since 1.5
     */
    public static int offsetByCodePoints(CharSequence seq, int index, int codePointOffset) {
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length();
        if (index < 0 || index > len) {
            throw new IndexOutOfBoundsException();
        }

        if (codePointOffset == 0) {
            return index;
        }

        if (codePointOffset > 0) {
            int codePoints = codePointOffset;
            int i = index;
            while (codePoints > 0) {
                codePoints--;
                if (i >= len) {
                    throw new IndexOutOfBoundsException();
                }
                if (isHighSurrogate(seq.charAt(i))) {
                    int next = i + 1;
                    if (next < len && isLowSurrogate(seq.charAt(next))) {
                        i++;
                    }
                }
                i++;
            }
            return i;
        }

        int codePoints = -codePointOffset;
        int i = index;
        while (codePoints > 0) {
            codePoints--;
            i--;
            if (i < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (isLowSurrogate(seq.charAt(i))) {
                int prev = i - 1;
                if (prev >= 0 && isHighSurrogate(seq.charAt(prev))) {
                    i--;
                }
            }
        }
        return i;
    }

    /**
     * Determines the index in a subsequence of the specified character array
     * that is offset {@code codePointOffset} code points from {@code index}.
     * The subsequence is delineated by {@code start} and {@code count}.
     *
     * @param seq
     *            the character array to find the index in.
     * @param start
     *            the inclusive index that marks the beginning of the
     *            subsequence.
     * @param count
     *            the number of {@code char} values to include within the
     *            subsequence.
     * @param index
     *            the start index in the subsequence of the char array.
     * @param codePointOffset
     *            the number of code points to look backwards or forwards; may
     *            be a negative or positive value.
     * @return the index in {@code seq} that is {@code codePointOffset} code
     *         points away from {@code index}.
     * @throws NullPointerException
     *             if {@code seq} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0}, {@code count < 0},
     *             {@code index < start}, {@code index > start + count},
     *             {@code start + count} is greater than the length of
     *             {@code seq}, or if there are not enough values in
     *             {@code seq} to skip {@code codePointOffset} code points
     *             forward or backward (if {@code codePointOffset} is
     *             negative) from {@code index}.
     * @since 1.5
     */
    public static int offsetByCodePoints(char[] seq, int start, int count,
            int index, int codePointOffset) {
        Arrays.checkOffsetAndCount(seq.length, start, count);
        int end = start + count;
        if (index < start || index > end) {
            throw new IndexOutOfBoundsException();
        }

        if (codePointOffset == 0) {
            return index;
        }

        if (codePointOffset > 0) {
            int codePoints = codePointOffset;
            int i = index;
            while (codePoints > 0) {
                codePoints--;
                if (i >= end) {
                    throw new IndexOutOfBoundsException();
                }
                if (isHighSurrogate(seq[i])) {
                    int next = i + 1;
                    if (next < end && isLowSurrogate(seq[next])) {
                        i++;
                    }
                }
                i++;
            }
            return i;
        }

        int codePoints = -codePointOffset;
        int i = index;
        while (codePoints > 0) {
            codePoints--;
            i--;
            if (i < start) {
                throw new IndexOutOfBoundsException();
            }
            if (isLowSurrogate(seq[i])) {
                int prev = i - 1;
                if (prev >= start && isHighSurrogate(seq[prev])) {
                    i--;
                }
            }
        }
        return i;
    }

    /**
     * Convenience method to determine the value of the specified character
     * {@code c} in the supplied radix. The value of {@code radix} must be
     * between MIN_RADIX and MAX_RADIX.
     *
     * @param c
     *            the character to determine the value of.
     * @param radix
     *            the radix.
     * @return the value of {@code c} in {@code radix} if {@code radix} lies
     *         between {@link #MIN_RADIX} and {@link #MAX_RADIX}; -1 otherwise.
     */
    public static int digit(char c, int radix) {
        return digit((int) c, radix);
    }

    /**
     * Convenience method to determine the value of the character
     * {@code codePoint} in the supplied radix. The value of {@code radix} must
     * be between MIN_RADIX and MAX_RADIX.
     *
     * @param codePoint
     *            the character, including supplementary characters.
     * @param radix
     *            the radix.
     * @return if {@code radix} lies between {@link #MIN_RADIX} and
     *         {@link #MAX_RADIX} then the value of the character in the radix;
     *         -1 otherwise.
     */
    public static int digit(int codePoint, int radix) {
        if (radix < MIN_RADIX || radix > MAX_RADIX) {
            return -1;
        }
        if (codePoint < 128) {
            // Optimized for ASCII
            int result = -1;
            if ('0' <= codePoint && codePoint <= '9') {
                result = codePoint - '0';
            } else if ('a' <= codePoint && codePoint <= 'z') {
                result = 10 + (codePoint - 'a');
            } else if ('A' <= codePoint && codePoint <= 'Z') {
                result = 10 + (codePoint - 'A');
            }
            return result < radix ? result : -1;
        }
        return digitImpl(codePoint, radix);
    }

    private static native int digitImpl(int codePoint, int radix);

    /**
     * Compares this object with the specified object and indicates if they are
     * equal. In order to be equal, {@code object} must be an instance of
     * {@code Character} and have the same char value as this object.
     *
     * @param object
     *            the object to compare this double with.
     * @return {@code true} if the specified object is equal to this
     *         {@code Character}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object object) {
        return (object instanceof Character) && (((Character) object).value == value);
    }

    /**
     * Returns the character which represents the specified digit in the
     * specified radix. The {@code radix} must be between {@code MIN_RADIX} and
     * {@code MAX_RADIX} inclusive; {@code digit} must not be negative and
     * smaller than {@code radix}. If any of these conditions does not hold, 0
     * is returned.
     *
     * @param digit
     *            the integer value.
     * @param radix
     *            the radix.
     * @return the character which represents the {@code digit} in the
     *         {@code radix}.
     */
    public static char forDigit(int digit, int radix) {
        if (MIN_RADIX <= radix && radix <= MAX_RADIX) {
            if (digit >= 0 && digit < radix) {
                return (char) (digit < 10 ? digit + '0' : digit + 'a' - 10);
            }
        }
        return 0;
    }

    /**
     * Returns the name of the given code point, or null if the code point is unassigned.
     *
     * <p>As a fallback mechanism this method returns strings consisting of the Unicode
     * block name (with underscores replaced by spaces), a single space, and the uppercase
     * hex value of the code point, using as few digits as necessary.
     *
     * <p>Examples:
     * <ul>
     * <li>{@code Character.getName(0)} returns "NULL".
     * <li>{@code Character.getName('e')} returns "LATIN SMALL LETTER E".
     * <li>{@code Character.getName('\u0666')} returns "ARABIC-INDIC DIGIT SIX".
     * <li>{@code Character.getName(0xe000)} returns "PRIVATE USE AREA E000".
     * </ul>
     *
     * @throws IllegalArgumentException if {@code codePoint} is not a valid code point.
     * @since 1.7
     * @hide 1.7
     */
    public static String getName(int codePoint) {
        checkValidCodePoint(codePoint);
        if (getType(codePoint) == Character.UNASSIGNED) {
            return null;
        }
        String result = getNameImpl(codePoint);
        if (result == null) {
            String blockName = Character.UnicodeBlock.of(codePoint).toString().replace('_', ' ');
            result = blockName + " " + IntegralToString.intToHexString(codePoint, true, 0);
        }
        return result;
    }

    private static native String getNameImpl(int codePoint);

    /**
     * Returns the numeric value of the specified Unicode character.
     * See {@link #getNumericValue(int)}.
     *
     * @param c the character
     * @return a non-negative numeric integer value if a numeric value for
     *         {@code c} exists, -1 if there is no numeric value for {@code c},
     *         -2 if the numeric value can not be represented as an integer.
     */
    public static int getNumericValue(char c) {
        return getNumericValue((int) c);
    }

    /**
     * Gets the numeric value of the specified Unicode code point. For example,
     * the code point '\u216B' stands for the Roman number XII, which has the
     * numeric value 12.
     *
     * <p>There are two points of divergence between this method and the Unicode
     * specification. This method treats the letters a-z (in both upper and lower
     * cases, and their full-width variants) as numbers from 10 to 35. The
     * Unicode specification also supports the idea of code points with non-integer
     * numeric values; this method does not (except to the extent of returning -2
     * for such code points).
     *
     * @param codePoint the code point
     * @return a non-negative numeric integer value if a numeric value for
     *         {@code codePoint} exists, -1 if there is no numeric value for
     *         {@code codePoint}, -2 if the numeric value can not be
     *         represented with an integer.
     */
    public static int getNumericValue(int codePoint) {
        // This is both an optimization and papers over differences between Java and ICU.
        if (codePoint < 128) {
            if (codePoint >= '0' && codePoint <= '9') {
                return codePoint - '0';
            }
            if (codePoint >= 'a' && codePoint <= 'z') {
                return codePoint - ('a' - 10);
            }
            if (codePoint >= 'A' && codePoint <= 'Z') {
                return codePoint - ('A' - 10);
            }
            return -1;
        }
        // Full-width uppercase A-Z.
        if (codePoint >= 0xff21 && codePoint <= 0xff3a) {
            return codePoint - 0xff17;
        }
        // Full-width lowercase a-z.
        if (codePoint >= 0xff41 && codePoint <= 0xff5a) {
            return codePoint - 0xff37;
        }
        return getNumericValueImpl(codePoint);
    }

    private static native int getNumericValueImpl(int codePoint);

    /**
     * Gets the general Unicode category of the specified character.
     *
     * @param c
     *            the character to get the category of.
     * @return the Unicode category of {@code c}.
     */
    public static int getType(char c) {
        return getType((int) c);
    }

    /**
     * Gets the general Unicode category of the specified code point.
     *
     * @param codePoint
     *            the Unicode code point to get the category of.
     * @return the Unicode category of {@code codePoint}.
     */
    public static int getType(int codePoint) {
        int type = getTypeImpl(codePoint);
        // The type values returned by ICU are not RI-compatible. The RI skips the value 17.
        if (type <= Character.FORMAT) {
            return type;
        }
        return (type + 1);
    }

    private static native int getTypeImpl(int codePoint);

    /**
     * Gets the Unicode directionality of the specified character.
     *
     * @param c
     *            the character to get the directionality of.
     * @return the Unicode directionality of {@code c}.
     */
    public static byte getDirectionality(char c) {
        return getDirectionality((int)c);
    }

    /**
     * Gets the Unicode directionality of the specified character.
     *
     * @param codePoint
     *            the Unicode code point to get the directionality of.
     * @return the Unicode directionality of {@code codePoint}.
     */
    public static byte getDirectionality(int codePoint) {
        if (getType(codePoint) == Character.UNASSIGNED) {
            return Character.DIRECTIONALITY_UNDEFINED;
        }

        byte directionality = getDirectionalityImpl(codePoint);
        if (directionality == -1) {
            return -1;
        }
        return DIRECTIONALITY[directionality];
    }

    private static native byte getDirectionalityImpl(int codePoint);

    /**
     * Indicates whether the specified character is mirrored.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is mirrored; {@code false}
     *         otherwise.
     */
    public static boolean isMirrored(char c) {
        return isMirrored((int) c);
    }

    /**
     * Indicates whether the specified code point is mirrored.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is mirrored, {@code false}
     *         otherwise.
     */
    public static boolean isMirrored(int codePoint) {
        return isMirroredImpl(codePoint);
    }

    private static native boolean isMirroredImpl(int codePoint);

    @Override
    public int hashCode() {
        return value;
    }

    /**
     * Returns the high surrogate for the given code point. The result is meaningless if
     * the given code point is not a supplementary character.
     * @since 1.7
     * @hide 1.7
     */
    public static char highSurrogate(int codePoint) {
        return (char) ((codePoint >> 10) + 0xd7c0);
    }

    /**
     * Returns the low surrogate for the given code point. The result is meaningless if
     * the given code point is not a supplementary character.
     * @since 1.7
     * @hide 1.7
     */
    public static char lowSurrogate(int codePoint) {
        return (char) ((codePoint & 0x3ff) | 0xdc00);
    }

    /**
     * Tests whether the given code point is in the Basic Multilingual Plane (BMP).
     * Such code points can be represented by a single {@code char}.
     * @since 1.7
     * @hide 1.7
     */
    public static boolean isBmpCodePoint(int codePoint) {
        return codePoint >= 0 && codePoint <= 0xffff;
    }

    /**
     * Indicates whether the specified character is defined in the Unicode
     * specification.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if the general Unicode category of the character is
     *         not {@code UNASSIGNED}; {@code false} otherwise.
     */
    public static boolean isDefined(char c) {
        return isDefinedImpl(c);
    }

    /**
     * Indicates whether the specified code point is defined in the Unicode
     * specification.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if the general Unicode category of the code point is
     *         not {@code UNASSIGNED}; {@code false} otherwise.
     */
    public static boolean isDefined(int codePoint) {
        return isDefinedImpl(codePoint);
    }

    private static native boolean isDefinedImpl(int codePoint);

    /**
     * Indicates whether the specified character is a digit.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a digit; {@code false}
     *         otherwise.
     */
    public static boolean isDigit(char c) {
        return isDigit((int) c);
    }

    /**
     * Indicates whether the specified code point is a digit.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a digit; {@code false}
     *         otherwise.
     */
    public static boolean isDigit(int codePoint) {
        // Optimized case for ASCII
        if ('0' <= codePoint && codePoint <= '9') {
            return true;
        }
        if (codePoint < 1632) {
            return false;
        }
        return isDigitImpl(codePoint);
    }

    private static native boolean isDigitImpl(int codePoint);

    /**
     * Indicates whether the specified character is ignorable in a Java or
     * Unicode identifier.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is ignorable; {@code false} otherwise.
     */
    public static boolean isIdentifierIgnorable(char c) {
        return isIdentifierIgnorable((int) c);
    }

    /**
     * Indicates whether the specified code point is ignorable in a Java or
     * Unicode identifier.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is ignorable; {@code false}
     *         otherwise.
     */
    public static boolean isIdentifierIgnorable(int codePoint) {
        // This is both an optimization and papers over differences between Java and ICU.
        if (codePoint < 0x600) {
            return (codePoint >= 0 && codePoint <= 8) || (codePoint >= 0xe && codePoint <= 0x1b) ||
                    (codePoint >= 0x7f && codePoint <= 0x9f) || (codePoint == 0xad);
        }
        return isIdentifierIgnorableImpl(codePoint);
    }

    private static native boolean isIdentifierIgnorableImpl(int codePoint);

    /**
     * Indicates whether the specified character is an ISO control character.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is an ISO control character;
     *         {@code false} otherwise.
     */
    public static boolean isISOControl(char c) {
        return isISOControl((int) c);
    }

    /**
     * Indicates whether the specified code point is an ISO control character.
     *
     * @param c
     *            the code point to check.
     * @return {@code true} if {@code c} is an ISO control character;
     *         {@code false} otherwise.
     */
    public static boolean isISOControl(int c) {
        return (c >= 0 && c <= 0x1f) || (c >= 0x7f && c <= 0x9f);
    }

    /**
     * Indicates whether the specified character is a valid part of a Java
     * identifier other than the first character.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is valid as part of a Java identifier;
     *         {@code false} otherwise.
     */
    public static boolean isJavaIdentifierPart(char c) {
        return isJavaIdentifierPart((int) c);
    }

    /**
     * Indicates whether the specified code point is a valid part of a Java
     * identifier other than the first character.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code c} is valid as part of a Java identifier;
     *         {@code false} otherwise.
     */
    public static boolean isJavaIdentifierPart(int codePoint) {
        // Use precomputed bitmasks to optimize the ASCII range.
        if (codePoint < 64) {
            return (0x3ff00100fffc1ffL & (1L << codePoint)) != 0;
        } else if (codePoint < 128) {
            return (0x87fffffe87fffffeL & (1L << (codePoint - 64))) != 0;
        }
        int type = getType(codePoint);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == CURRENCY_SYMBOL || type == CONNECTOR_PUNCTUATION
                || (type >= DECIMAL_DIGIT_NUMBER && type <= LETTER_NUMBER)
                || type == COMBINING_SPACING_MARK || type == NON_SPACING_MARK
                || (codePoint >= 0 && codePoint <= 8) || (codePoint >= 0xe && codePoint <= 0x1b)
                || (codePoint >= 0x7f && codePoint <= 0x9f) || type == FORMAT;
    }

    /**
     * Indicates whether the specified character is a valid first character for
     * a Java identifier.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a valid first character of a Java
     *         identifier; {@code false} otherwise.
     */
    public static boolean isJavaIdentifierStart(char c) {
        return isJavaIdentifierStart((int) c);
    }

    /**
     * Indicates whether the specified code point is a valid first character for
     * a Java identifier.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a valid start of a Java
     *         identifier; {@code false} otherwise.
     */
    public static boolean isJavaIdentifierStart(int codePoint) {
        // Use precomputed bitmasks to optimize the ASCII range.
        if (codePoint < 64) {
            return (codePoint == '$'); // There's only one character in this range.
        } else if (codePoint < 128) {
            return (0x7fffffe87fffffeL & (1L << (codePoint - 64))) != 0;
        }
        int type = getType(codePoint);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER) || type == CURRENCY_SYMBOL
                || type == CONNECTOR_PUNCTUATION || type == LETTER_NUMBER;
    }

    /**
     * Indicates whether the specified character is a Java letter.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a Java letter; {@code false}
     *         otherwise.
     * @deprecated Use {@link #isJavaIdentifierStart(char)}
     */
    @Deprecated
    public static boolean isJavaLetter(char c) {
        return isJavaIdentifierStart(c);
    }

    /**
     * Indicates whether the specified character is a Java letter or digit
     * character.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a Java letter or digit;
     *         {@code false} otherwise.
     * @deprecated Use {@link #isJavaIdentifierPart(char)}
     */
    @Deprecated
    public static boolean isJavaLetterOrDigit(char c) {
        return isJavaIdentifierPart(c);
    }

    /**
     * Indicates whether the specified character is a letter.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a letter; {@code false} otherwise.
     */
    public static boolean isLetter(char c) {
        return isLetter((int) c);
    }

    /**
     * Indicates whether the specified code point is a letter.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a letter; {@code false}
     *         otherwise.
     */
    public static boolean isLetter(int codePoint) {
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        if (codePoint < 128) {
            return false;
        }
        return isLetterImpl(codePoint);
    }

    private static native boolean isLetterImpl(int codePoint);

    /**
     * Indicates whether the specified character is a letter or a digit.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a letter or a digit; {@code false}
     *         otherwise.
     */
    public static boolean isLetterOrDigit(char c) {
        return isLetterOrDigit((int) c);
    }

    /**
     * Indicates whether the specified code point is a letter or a digit.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a letter or a digit;
     *         {@code false} otherwise.
     */
    public static boolean isLetterOrDigit(int codePoint) {
        // Optimized case for ASCII
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        if ('0' <= codePoint && codePoint <= '9') {
            return true;
        }
        if (codePoint < 128) {
            return false;
        }
        return isLetterOrDigitImpl(codePoint);
    }

    private static native boolean isLetterOrDigitImpl(int codePoint);

    /**
     * Indicates whether the specified character is a lower case letter.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a lower case letter; {@code false}
     *         otherwise.
     */
    public static boolean isLowerCase(char c) {
        return isLowerCase((int) c);
    }

    /**
     * Indicates whether the specified code point is a lower case letter.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a lower case letter;
     *         {@code false} otherwise.
     */
    public static boolean isLowerCase(int codePoint) {
        // Optimized case for ASCII
        if ('a' <= codePoint && codePoint <= 'z') {
            return true;
        }
        if (codePoint < 128) {
            return false;
        }
        return isLowerCaseImpl(codePoint);
    }

    private static native boolean isLowerCaseImpl(int codePoint);

    /**
     * Indicates whether the specified character is a Java space.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a Java space; {@code false}
     *         otherwise.
     * @deprecated Use {@link #isWhitespace(char)}
     */
    @Deprecated
    public static boolean isSpace(char c) {
        return c == '\n' || c == '\t' || c == '\f' || c == '\r' || c == ' ';
    }

    /**
     * Indicates whether the specified character is a Unicode space character.
     * That is, if it is a member of one of the Unicode categories Space
     * Separator, Line Separator, or Paragraph Separator.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a Unicode space character,
     *         {@code false} otherwise.
     */
    public static boolean isSpaceChar(char c) {
        return isSpaceChar((int) c);
    }

    /**
     * Indicates whether the specified code point is a Unicode space character.
     * That is, if it is a member of one of the Unicode categories Space
     * Separator, Line Separator, or Paragraph Separator.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a Unicode space character,
     *         {@code false} otherwise.
     */
    public static boolean isSpaceChar(int codePoint) {
        if (codePoint == 0x20 || codePoint == 0xa0 || codePoint == 0x1680) {
            return true;
        }
        if (codePoint < 0x2000) {
            return false;
        }
        if (codePoint <= 0xffff) {
            return codePoint <= 0x200b || codePoint == 0x2028 || codePoint == 0x2029 ||
                    codePoint == 0x202f || codePoint == 0x3000;
        }
        return isSpaceCharImpl(codePoint);
    }

    private static native boolean isSpaceCharImpl(int codePoint);

    /**
     * Indicates whether the specified character is a titlecase character.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a titlecase character, {@code false}
     *         otherwise.
     */
    public static boolean isTitleCase(char c) {
        return isTitleCaseImpl(c);
    }

    /**
     * Indicates whether the specified code point is a titlecase character.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a titlecase character,
     *         {@code false} otherwise.
     */
    public static boolean isTitleCase(int codePoint) {
        return isTitleCaseImpl(codePoint);
    }

    private static native boolean isTitleCaseImpl(int codePoint);

    /**
     * Indicates whether the specified character is valid as part of a Unicode
     * identifier other than the first character.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is valid as part of a Unicode
     *         identifier; {@code false} otherwise.
     */
    public static boolean isUnicodeIdentifierPart(char c) {
        return isUnicodeIdentifierPartImpl(c);
    }

    /**
     * Indicates whether the specified code point is valid as part of a Unicode
     * identifier other than the first character.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is valid as part of a Unicode
     *         identifier; {@code false} otherwise.
     */
    public static boolean isUnicodeIdentifierPart(int codePoint) {
        return isUnicodeIdentifierPartImpl(codePoint);
    }

    private static native boolean isUnicodeIdentifierPartImpl(int codePoint);

    /**
     * Indicates whether the specified character is a valid initial character
     * for a Unicode identifier.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a valid first character for a
     *         Unicode identifier; {@code false} otherwise.
     */
    public static boolean isUnicodeIdentifierStart(char c) {
        return isUnicodeIdentifierStartImpl(c);
    }

    /**
     * Indicates whether the specified code point is a valid initial character
     * for a Unicode identifier.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a valid first character for
     *         a Unicode identifier; {@code false} otherwise.
     */
    public static boolean isUnicodeIdentifierStart(int codePoint) {
        return isUnicodeIdentifierStartImpl(codePoint);
    }

    private static native boolean isUnicodeIdentifierStartImpl(int codePoint);

    /**
     * Indicates whether the specified character is an upper case letter.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a upper case letter; {@code false}
     *         otherwise.
     */
    public static boolean isUpperCase(char c) {
        return isUpperCase((int) c);
    }

    /**
     * Indicates whether the specified code point is an upper case letter.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a upper case letter;
     *         {@code false} otherwise.
     */
    public static boolean isUpperCase(int codePoint) {
        // Optimized case for ASCII
        if ('A' <= codePoint && codePoint <= 'Z') {
            return true;
        }
        if (codePoint < 128) {
            return false;
        }
        return isUpperCaseImpl(codePoint);
    }

    private static native boolean isUpperCaseImpl(int codePoint);

    /**
     * Indicates whether the specified character is a whitespace character in
     * Java.
     *
     * @param c
     *            the character to check.
     * @return {@code true} if the supplied {@code c} is a whitespace character
     *         in Java; {@code false} otherwise.
     */
    public static boolean isWhitespace(char c) {
        return isWhitespace((int) c);
    }

    /**
     * Indicates whether the specified code point is a whitespace character in
     * Java.
     *
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if the supplied {@code c} is a whitespace character
     *         in Java; {@code false} otherwise.
     */
    public static boolean isWhitespace(int codePoint) {
        // This is both an optimization and papers over differences between Java and ICU.
        if ((codePoint >= 0x1c && codePoint <= 0x20) || (codePoint >= 0x9 && codePoint <= 0xd)) {
            return true;
        }
        if (codePoint == 0x1680) {
            return true;
        }
        if (codePoint < 0x2000 || codePoint == 0x2007) {
            return false;
        }
        if (codePoint <= 0xffff) {
            return codePoint <= 0x200b || codePoint == 0x2028 || codePoint == 0x2029 ||
                    codePoint == 0x3000;
        }
        return isWhitespaceImpl(codePoint);
    }

    private static native boolean isWhitespaceImpl(int codePoint);

    /**
     * Reverses the order of the first and second byte in the specified
     * character.
     *
     * @param c
     *            the character to reverse.
     * @return the character with reordered bytes.
     */
    public static char reverseBytes(char c) {
        return (char)((c<<8) | (c>>8));
    }

    /**
     * Returns the lower case equivalent for the specified character if the
     * character is an upper case letter. Otherwise, the specified character is
     * returned unchanged.
     *
     * @param c
     *            the character
     * @return if {@code c} is an upper case character then its lower case
     *         counterpart, otherwise just {@code c}.
     */
    public static char toLowerCase(char c) {
        return (char) toLowerCase((int) c);
    }

    /**
     * Returns the lower case equivalent for the specified code point if it is
     * an upper case letter. Otherwise, the specified code point is returned
     * unchanged.
     *
     * @param codePoint
     *            the code point to check.
     * @return if {@code codePoint} is an upper case character then its lower
     *         case counterpart, otherwise just {@code codePoint}.
     */
    public static int toLowerCase(int codePoint) {
        // Optimized case for ASCII
        if ('A' <= codePoint && codePoint <= 'Z') {
            return (char) (codePoint + ('a' - 'A'));
        }
        if (codePoint < 192) {
            return codePoint;
        }
        return toLowerCaseImpl(codePoint);
    }

    private static native int toLowerCaseImpl(int codePoint);

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Converts the specified character to its string representation.
     *
     * @param value
     *            the character to convert.
     * @return the character converted to a string.
     */
    public static String toString(char value) {
        return String.valueOf(value);
    }

    /**
     * Returns the title case equivalent for the specified character if it
     * exists. Otherwise, the specified character is returned unchanged.
     *
     * @param c
     *            the character to convert.
     * @return the title case equivalent of {@code c} if it exists, otherwise
     *         {@code c}.
     */
    public static char toTitleCase(char c) {
        return (char) toTitleCaseImpl(c);
    }

    /**
     * Returns the title case equivalent for the specified code point if it
     * exists. Otherwise, the specified code point is returned unchanged.
     *
     * @param codePoint
     *            the code point to convert.
     * @return the title case equivalent of {@code codePoint} if it exists,
     *         otherwise {@code codePoint}.
     */
    public static int toTitleCase(int codePoint) {
        return toTitleCaseImpl(codePoint);
    }

    private static native int toTitleCaseImpl(int codePoint);

    /**
     * Returns the upper case equivalent for the specified character if the
     * character is a lower case letter. Otherwise, the specified character is
     * returned unchanged.
     *
     * @param c
     *            the character to convert.
     * @return if {@code c} is a lower case character then its upper case
     *         counterpart, otherwise just {@code c}.
     */
    public static char toUpperCase(char c) {
        return (char) toUpperCase((int) c);
    }

    /**
     * Returns the upper case equivalent for the specified code point if the
     * code point is a lower case letter. Otherwise, the specified code point is
     * returned unchanged.
     *
     * @param codePoint
     *            the code point to convert.
     * @return if {@code codePoint} is a lower case character then its upper
     *         case counterpart, otherwise just {@code codePoint}.
     */
    public static int toUpperCase(int codePoint) {
        // Optimized case for ASCII
        if ('a' <= codePoint && codePoint <= 'z') {
            return (char) (codePoint - ('a' - 'A'));
        }
        if (codePoint < 181) {
            return codePoint;
        }
        return toUpperCaseImpl(codePoint);
    }

    private static native int toUpperCaseImpl(int codePoint);
}
