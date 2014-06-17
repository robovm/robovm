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
        private final String name;

        /**
         * Constructs a new {@code Subset}.
         */
        protected Subset(String name) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            this.name = name;
        }

        /**
         * Compares this character subset for identity with the specified object.
         */
        @Override public final boolean equals(Object object) {
            return object == this;
        }

        /**
         * Returns this subset's hash code, which is the hash code computed by
         *         {@link java.lang.Object#hashCode()}.
         */
        @Override public final int hashCode() {
            return super.hashCode();
        }

        /**
         * Returns this subset's name.
         */
        @Override public final String toString() {
            return name;
        }
    }

    /**
     * Represents a block of Unicode characters. This class provides constants for various
     * well-known blocks (but not all blocks) and methods for looking up a block
     * by name {@link #forName} or by code point {@link #of}.
     *
     * @since 1.2
     */
    public static final class UnicodeBlock extends Subset {
        /**
         * The Surrogates Area Unicode block.
         *
         * @deprecated As of Java 5, this block has been replaced by
         *             {@link #HIGH_SURROGATES},
         *             {@link #HIGH_PRIVATE_USE_SURROGATES} and
         *             {@link #LOW_SURROGATES}.
         */
        @Deprecated
        public static final UnicodeBlock SURROGATES_AREA = new UnicodeBlock("SURROGATES_AREA");

        /** The Basic Latin Unicode block. */
        public static final UnicodeBlock BASIC_LATIN = new UnicodeBlock("BASIC_LATIN");

        /** The Latin-1 Supplement Unicode block. */
        public static final UnicodeBlock LATIN_1_SUPPLEMENT = new UnicodeBlock("LATIN_1_SUPPLEMENT");

        /** The Latin Extended-A Unicode block. */
        public static final UnicodeBlock LATIN_EXTENDED_A = new UnicodeBlock("LATIN_EXTENDED_A");

        /** The Latin Extended-B Unicode block. */
        public static final UnicodeBlock LATIN_EXTENDED_B = new UnicodeBlock("LATIN_EXTENDED_B");

        /** The IPA Extensions Unicode block. */
        public static final UnicodeBlock IPA_EXTENSIONS = new UnicodeBlock("IPA_EXTENSIONS");

        /** The Spacing Modifier Letters Unicode block. */
        public static final UnicodeBlock SPACING_MODIFIER_LETTERS = new UnicodeBlock("SPACING_MODIFIER_LETTERS");

        /** The Combining Diacritical Marks Unicode block. */
        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS");

        /**
         * The Greek and Coptic Unicode block. Previously referred to as Greek.
         */
        public static final UnicodeBlock GREEK = new UnicodeBlock("GREEK");

        /** The Cyrillic Unicode block. */
        public static final UnicodeBlock CYRILLIC = new UnicodeBlock("CYRILLIC");

        /**
         * The Cyrillic Supplement Unicode block. Previously referred to as Cyrillic Supplementary.
         */
        public static final UnicodeBlock CYRILLIC_SUPPLEMENTARY = new UnicodeBlock("CYRILLIC_SUPPLEMENTARY");

        /** The Armenian Unicode block. */
        public static final UnicodeBlock ARMENIAN = new UnicodeBlock("ARMENIAN");

        /** The Hebrew Unicode block. */
        public static final UnicodeBlock HEBREW = new UnicodeBlock("HEBREW");

        /** The Arabic Unicode block. */
        public static final UnicodeBlock ARABIC = new UnicodeBlock("ARABIC");

        /** The Syriac Unicode block. */
        public static final UnicodeBlock SYRIAC = new UnicodeBlock("SYRIAC");

        /** The Thaana Unicode block. */
        public static final UnicodeBlock THAANA = new UnicodeBlock("THAANA");

        /** The Devanagari Unicode block. */
        public static final UnicodeBlock DEVANAGARI = new UnicodeBlock("DEVANAGARI");

        /** The Bengali Unicode block. */
        public static final UnicodeBlock BENGALI = new UnicodeBlock("BENGALI");

        /** The Gurmukhi Unicode block. */
        public static final UnicodeBlock GURMUKHI = new UnicodeBlock("GURMUKHI");

        /** The Gujarati Unicode block. */
        public static final UnicodeBlock GUJARATI = new UnicodeBlock("GUJARATI");

        /** The Oriya Unicode block. */
        public static final UnicodeBlock ORIYA = new UnicodeBlock("ORIYA");

        /** The Tamil Unicode block. */
        public static final UnicodeBlock TAMIL = new UnicodeBlock("TAMIL");

        /** The Telugu Unicode block. */
        public static final UnicodeBlock TELUGU = new UnicodeBlock("TELUGU");

        /** The Kannada Unicode block. */
        public static final UnicodeBlock KANNADA = new UnicodeBlock("KANNADA");

        /** The Malayalam Unicode block. */
        public static final UnicodeBlock MALAYALAM = new UnicodeBlock("MALAYALAM");

        /** The Sinhala Unicode block. */
        public static final UnicodeBlock SINHALA = new UnicodeBlock("SINHALA");

        /** The Thai Unicode block. */
        public static final UnicodeBlock THAI = new UnicodeBlock("THAI");

        /** The Lao Unicode block. */
        public static final UnicodeBlock LAO = new UnicodeBlock("LAO");

        /** The Tibetan Unicode block. */
        public static final UnicodeBlock TIBETAN = new UnicodeBlock("TIBETAN");

        /** The Myanmar Unicode block. */
        public static final UnicodeBlock MYANMAR = new UnicodeBlock("MYANMAR");

        /** The Georgian Unicode block. */
        public static final UnicodeBlock GEORGIAN = new UnicodeBlock("GEORGIAN");

        /** The Hangul Jamo Unicode block. */
        public static final UnicodeBlock HANGUL_JAMO = new UnicodeBlock("HANGUL_JAMO");

        /** The Ethiopic Unicode block. */
        public static final UnicodeBlock ETHIOPIC = new UnicodeBlock("ETHIOPIC");

        /** The Cherokee Unicode block. */
        public static final UnicodeBlock CHEROKEE = new UnicodeBlock("CHEROKEE");

        /** The Unified Canadian Aboriginal Syllabics Unicode block. */
        public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS");

        /** The Ogham Unicode block. */
        public static final UnicodeBlock OGHAM = new UnicodeBlock("OGHAM");

        /** The Runic Unicode block. */
        public static final UnicodeBlock RUNIC = new UnicodeBlock("RUNIC");

        /** The Tagalog Unicode block. */
        public static final UnicodeBlock TAGALOG = new UnicodeBlock("TAGALOG");

        /** The Hanunoo Unicode block. */
        public static final UnicodeBlock HANUNOO = new UnicodeBlock("HANUNOO");

        /** The Buhid Unicode block. */
        public static final UnicodeBlock BUHID = new UnicodeBlock("BUHID");

        /** The Tagbanwa Unicode block. */
        public static final UnicodeBlock TAGBANWA = new UnicodeBlock("TAGBANWA");

        /** The Khmer Unicode block. */
        public static final UnicodeBlock KHMER = new UnicodeBlock("KHMER");

        /** The Mongolian Unicode block. */
        public static final UnicodeBlock MONGOLIAN = new UnicodeBlock("MONGOLIAN");

        /** The Limbu Unicode block. */
        public static final UnicodeBlock LIMBU = new UnicodeBlock("LIMBU");

        /** The Tai Le Unicode block. */
        public static final UnicodeBlock TAI_LE = new UnicodeBlock("TAI_LE");

        /** The Khmer Symbols Unicode block. */
        public static final UnicodeBlock KHMER_SYMBOLS = new UnicodeBlock("KHMER_SYMBOLS");

        /** The Phonetic Extensions Unicode block. */
        public static final UnicodeBlock PHONETIC_EXTENSIONS = new UnicodeBlock("PHONETIC_EXTENSIONS");

        /** The Latin Extended Additional Unicode block. */
        public static final UnicodeBlock LATIN_EXTENDED_ADDITIONAL = new UnicodeBlock("LATIN_EXTENDED_ADDITIONAL");

        /** The Greek Extended Unicode block. */
        public static final UnicodeBlock GREEK_EXTENDED = new UnicodeBlock("GREEK_EXTENDED");

        /** The General Punctuation Unicode block. */
        public static final UnicodeBlock GENERAL_PUNCTUATION = new UnicodeBlock("GENERAL_PUNCTUATION");

        /** The Superscripts and Subscripts Unicode block. */
        public static final UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS = new UnicodeBlock("SUPERSCRIPTS_AND_SUBSCRIPTS");

        /** The Currency Symbols Unicode block. */
        public static final UnicodeBlock CURRENCY_SYMBOLS = new UnicodeBlock("CURRENCY_SYMBOLS");

        /**
         * The Combining Diacritical Marks for Symbols Unicode
         * Block. Previously referred to as Combining Marks for
         * Symbols.
         */
        public static final UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS = new UnicodeBlock("COMBINING_MARKS_FOR_SYMBOLS");

        /** The Letterlike Symbols Unicode block. */
        public static final UnicodeBlock LETTERLIKE_SYMBOLS = new UnicodeBlock("LETTERLIKE_SYMBOLS");

        /** The Number Forms Unicode block. */
        public static final UnicodeBlock NUMBER_FORMS = new UnicodeBlock("NUMBER_FORMS");

        /** The Arrows Unicode block. */
        public static final UnicodeBlock ARROWS = new UnicodeBlock("ARROWS");

        /** The Mathematical Operators Unicode block. */
        public static final UnicodeBlock MATHEMATICAL_OPERATORS = new UnicodeBlock("MATHEMATICAL_OPERATORS");

        /** The Miscellaneous Technical Unicode block. */
        public static final UnicodeBlock MISCELLANEOUS_TECHNICAL = new UnicodeBlock("MISCELLANEOUS_TECHNICAL");

        /** The Control Pictures Unicode block. */
        public static final UnicodeBlock CONTROL_PICTURES = new UnicodeBlock("CONTROL_PICTURES");

        /** The Optical Character Recognition Unicode block. */
        public static final UnicodeBlock OPTICAL_CHARACTER_RECOGNITION = new UnicodeBlock("OPTICAL_CHARACTER_RECOGNITION");

        /** The Enclosed Alphanumerics Unicode block. */
        public static final UnicodeBlock ENCLOSED_ALPHANUMERICS = new UnicodeBlock("ENCLOSED_ALPHANUMERICS");

        /** The Box Drawing Unicode block. */
        public static final UnicodeBlock BOX_DRAWING = new UnicodeBlock("BOX_DRAWING");

        /** The Block Elements Unicode block. */
        public static final UnicodeBlock BLOCK_ELEMENTS = new UnicodeBlock("BLOCK_ELEMENTS");

        /** The Geometric Shapes Unicode block. */
        public static final UnicodeBlock GEOMETRIC_SHAPES = new UnicodeBlock("GEOMETRIC_SHAPES");

        /** The Miscellaneous Symbols Unicode block. */
        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS");

        /** The Dingbats Unicode block. */
        public static final UnicodeBlock DINGBATS = new UnicodeBlock("DINGBATS");

        /** The Miscellaneous Mathematical Symbols-A Unicode block. */
        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A");

        /** The Supplemental Arrows-A Unicode block. */
        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_A = new UnicodeBlock("SUPPLEMENTAL_ARROWS_A");

        /** The Braille Patterns Unicode block. */
        public static final UnicodeBlock BRAILLE_PATTERNS = new UnicodeBlock("BRAILLE_PATTERNS");

        /** The Supplemental Arrows-B Unicode block. */
        public static final UnicodeBlock SUPPLEMENTAL_ARROWS_B = new UnicodeBlock("SUPPLEMENTAL_ARROWS_B");

        /** The Miscellaneous Mathematical Symbols-B Unicode block. */
        public static final UnicodeBlock MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B = new UnicodeBlock("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B");

        /** The Supplemental Mathematical Operators Unicode block. */
        public static final UnicodeBlock SUPPLEMENTAL_MATHEMATICAL_OPERATORS = new UnicodeBlock("SUPPLEMENTAL_MATHEMATICAL_OPERATORS");

        /** The Miscellaneous Symbols and Arrows Unicode block. */
        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_ARROWS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_ARROWS");

        /** The CJK Radicals Supplement Unicode block. */
        public static final UnicodeBlock CJK_RADICALS_SUPPLEMENT = new UnicodeBlock("CJK_RADICALS_SUPPLEMENT");

        /** The Kangxi Radicals Unicode block. */
        public static final UnicodeBlock KANGXI_RADICALS = new UnicodeBlock("KANGXI_RADICALS");

        /** The Ideographic Description Characters Unicode block. */
        public static final UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS = new UnicodeBlock("IDEOGRAPHIC_DESCRIPTION_CHARACTERS");

        /** The CJK Symbols and Punctuation Unicode block. */
        public static final UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("CJK_SYMBOLS_AND_PUNCTUATION");

        /** The Hiragana Unicode block. */
        public static final UnicodeBlock HIRAGANA = new UnicodeBlock("HIRAGANA");

        /** The Katakana Unicode block. */
        public static final UnicodeBlock KATAKANA = new UnicodeBlock("KATAKANA");

        /** The Bopomofo Unicode block. */
        public static final UnicodeBlock BOPOMOFO = new UnicodeBlock("BOPOMOFO");

        /** The Hangul Compatibility Jamo Unicode block. */
        public static final UnicodeBlock HANGUL_COMPATIBILITY_JAMO = new UnicodeBlock("HANGUL_COMPATIBILITY_JAMO");

        /** The Kanbun Unicode block. */
        public static final UnicodeBlock KANBUN = new UnicodeBlock("KANBUN");

        /** The Bopomofo Extended Unicode block. */
        public static final UnicodeBlock BOPOMOFO_EXTENDED = new UnicodeBlock("BOPOMOFO_EXTENDED");

        /** The Katakana Phonetic Extensions Unicode block. */
        public static final UnicodeBlock KATAKANA_PHONETIC_EXTENSIONS = new UnicodeBlock("KATAKANA_PHONETIC_EXTENSIONS");

        /** The Enclosed CJK Letters and Months Unicode block. */
        public static final UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS = new UnicodeBlock("ENCLOSED_CJK_LETTERS_AND_MONTHS");

        /** The CJK Compatibility Unicode block. */
        public static final UnicodeBlock CJK_COMPATIBILITY = new UnicodeBlock("CJK_COMPATIBILITY");

        /** The CJK Unified Ideographs Extension A Unicode block. */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A");

        /** The Yijing Hexagram Symbols Unicode block. */
        public static final UnicodeBlock YIJING_HEXAGRAM_SYMBOLS = new UnicodeBlock("YIJING_HEXAGRAM_SYMBOLS");

        /** The CJK Unified Ideographs Unicode block. */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS");

        /** The Yi Syllables Unicode block. */
        public static final UnicodeBlock YI_SYLLABLES = new UnicodeBlock("YI_SYLLABLES");

        /** The Yi Radicals Unicode block. */
        public static final UnicodeBlock YI_RADICALS = new UnicodeBlock("YI_RADICALS");

        /** The Hangul Syllables Unicode block. */
        public static final UnicodeBlock HANGUL_SYLLABLES = new UnicodeBlock("HANGUL_SYLLABLES");

        /**
         * The High Surrogates Unicode block. This block represents
         * code point values in the high surrogate range 0xD800 to 0xDB7F
         */
        public static final UnicodeBlock HIGH_SURROGATES = new UnicodeBlock("HIGH_SURROGATES");

        /**
         * The High Private Use Surrogates Unicode block. This block
         * represents code point values in the high surrogate range 0xDB80 to
         * 0xDBFF
         */
        public static final UnicodeBlock HIGH_PRIVATE_USE_SURROGATES = new UnicodeBlock("HIGH_PRIVATE_USE_SURROGATES");

        /**
         * The Low Surrogates Unicode block. This block represents
         * code point values in the low surrogate range 0xDC00 to 0xDFFF
         */
        public static final UnicodeBlock LOW_SURROGATES = new UnicodeBlock("LOW_SURROGATES");

        /** The Private Use Area Unicode block. */
        public static final UnicodeBlock PRIVATE_USE_AREA = new UnicodeBlock("PRIVATE_USE_AREA");

        /** The CJK Compatibility Ideographs Unicode block. */
        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS");

        /** The Alphabetic Presentation Forms Unicode block. */
        public static final UnicodeBlock ALPHABETIC_PRESENTATION_FORMS = new UnicodeBlock("ALPHABETIC_PRESENTATION_FORMS");

        /** The Arabic Presentation Forms-A Unicode block. */
        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_A = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_A");

        /** The Variation Selectors Unicode block. */
        public static final UnicodeBlock VARIATION_SELECTORS = new UnicodeBlock("VARIATION_SELECTORS");

        /** The Combining Half Marks Unicode block. */
        public static final UnicodeBlock COMBINING_HALF_MARKS = new UnicodeBlock("COMBINING_HALF_MARKS");

        /** The CJK Compatibility Forms Unicode block. */
        public static final UnicodeBlock CJK_COMPATIBILITY_FORMS = new UnicodeBlock("CJK_COMPATIBILITY_FORMS");

        /** The Small Form Variants Unicode block. */
        public static final UnicodeBlock SMALL_FORM_VARIANTS = new UnicodeBlock("SMALL_FORM_VARIANTS");

        /** The Arabic Presentation Forms-B Unicode block. */
        public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_B = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_B");

        /** The Halfwidth and Fullwidth Forms Unicode block. */
        public static final UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS = new UnicodeBlock("HALFWIDTH_AND_FULLWIDTH_FORMS");

        /** The Specials Unicode block. */
        public static final UnicodeBlock SPECIALS = new UnicodeBlock("SPECIALS");

        /** The Linear B Syllabary Unicode block. */
        public static final UnicodeBlock LINEAR_B_SYLLABARY = new UnicodeBlock("LINEAR_B_SYLLABARY");

        /** The Linear B Ideograms Unicode block. */
        public static final UnicodeBlock LINEAR_B_IDEOGRAMS = new UnicodeBlock("LINEAR_B_IDEOGRAMS");

        /** The Aegean Numbers Unicode block. */
        public static final UnicodeBlock AEGEAN_NUMBERS = new UnicodeBlock("AEGEAN_NUMBERS");

        /** The Old Italic Unicode block. */
        public static final UnicodeBlock OLD_ITALIC = new UnicodeBlock("OLD_ITALIC");

        /** The Gothic Unicode block. */
        public static final UnicodeBlock GOTHIC = new UnicodeBlock("GOTHIC");

        /** The Ugaritic Unicode block. */
        public static final UnicodeBlock UGARITIC = new UnicodeBlock("UGARITIC");

        /** The Deseret Unicode block. */
        public static final UnicodeBlock DESERET = new UnicodeBlock("DESERET");

        /** The Shavian Unicode block. */
        public static final UnicodeBlock SHAVIAN = new UnicodeBlock("SHAVIAN");

        /** The Osmanya Unicode block. */
        public static final UnicodeBlock OSMANYA = new UnicodeBlock("OSMANYA");

        /** The Cypriot Syllabary Unicode block. */
        public static final UnicodeBlock CYPRIOT_SYLLABARY = new UnicodeBlock("CYPRIOT_SYLLABARY");

        /** The Byzantine Musical Symbols Unicode block. */
        public static final UnicodeBlock BYZANTINE_MUSICAL_SYMBOLS = new UnicodeBlock("BYZANTINE_MUSICAL_SYMBOLS");

        /** The Musical Symbols Unicode block. */
        public static final UnicodeBlock MUSICAL_SYMBOLS = new UnicodeBlock("MUSICAL_SYMBOLS");

        /** The Tai Xuan Jing Symbols Unicode block. */
        public static final UnicodeBlock TAI_XUAN_JING_SYMBOLS = new UnicodeBlock("TAI_XUAN_JING_SYMBOLS");

        /** The Mathematical Alphanumeric Symbols Unicode block. */
        public static final UnicodeBlock MATHEMATICAL_ALPHANUMERIC_SYMBOLS = new UnicodeBlock("MATHEMATICAL_ALPHANUMERIC_SYMBOLS");

        /** The CJK Unified Ideographs Extension B Unicode block. */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B");

        /** The CJK Compatibility Ideographs Supplement Unicode block. */
        public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT");

        /** The Tags Unicode block. */
        public static final UnicodeBlock TAGS = new UnicodeBlock("TAGS");

        /** The Variation Selectors Supplement Unicode block. */
        public static final UnicodeBlock VARIATION_SELECTORS_SUPPLEMENT = new UnicodeBlock("VARIATION_SELECTORS_SUPPLEMENT");

        /** The Supplementary Private Use Area-A Unicode block. */
        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_A = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_A");

        /** The Supplementary Private Use Area-B Unicode block. */
        public static final UnicodeBlock SUPPLEMENTARY_PRIVATE_USE_AREA_B = new UnicodeBlock("SUPPLEMENTARY_PRIVATE_USE_AREA_B");

        // Unicode 4.1.

        /** The Ancient Greek Musical Notation Unicode 4.1 block. */
        public static final UnicodeBlock ANCIENT_GREEK_MUSICAL_NOTATION = new UnicodeBlock("ANCIENT_GREEK_MUSICAL_NOTATION");

        /** The Ancient Greek Numbers Unicode 4.1 block. */
        public static final UnicodeBlock ANCIENT_GREEK_NUMBERS = new UnicodeBlock("ANCIENT_GREEK_NUMBERS");

        /** The Arabic Supplement Unicode 4.1 block. */
        public static final UnicodeBlock ARABIC_SUPPLEMENT = new UnicodeBlock("ARABIC_SUPPLEMENT");

        /** The Buginese Unicode 4.1 block. */
        public static final UnicodeBlock BUGINESE = new UnicodeBlock("BUGINESE");

        /** The CJK Strokes Unicode 4.1 block. */
        public static final UnicodeBlock CJK_STROKES = new UnicodeBlock("CJK_STROKES");

        /** The Combining Diacritical Marks Supplement Unicode 4.1 block. */
        public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS_SUPPLEMENT = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS_SUPPLEMENT");

        /** The Coptic Unicode 4.1 block. */
        public static final UnicodeBlock COPTIC = new UnicodeBlock("COPTIC");

        /** The Ethiopic Extended Unicode 4.1 block. */
        public static final UnicodeBlock ETHIOPIC_EXTENDED = new UnicodeBlock("ETHIOPIC_EXTENDED");

        /** The Ethiopic Supplement Unicode 4.1 block. */
        public static final UnicodeBlock ETHIOPIC_SUPPLEMENT = new UnicodeBlock("ETHIOPIC_SUPPLEMENT");

        /** The Georgian Supplement Unicode 4.1 block. */
        public static final UnicodeBlock GEORGIAN_SUPPLEMENT = new UnicodeBlock("GEORGIAN_SUPPLEMENT");

        /** The Glagolitic Unicode 4.1 block. */
        public static final UnicodeBlock GLAGOLITIC = new UnicodeBlock("GLAGOLITIC");

        /** The Kharoshthi Unicode 4.1 block. */
        public static final UnicodeBlock KHAROSHTHI = new UnicodeBlock("KHAROSHTHI");

        /** The Modifier Tone Letters Unicode 4.1 block. */
        public static final UnicodeBlock MODIFIER_TONE_LETTERS = new UnicodeBlock("MODIFIER_TONE_LETTERS");

        /** The New Tai Lue Unicode 4.1 block. */
        public static final UnicodeBlock NEW_TAI_LUE = new UnicodeBlock("NEW_TAI_LUE");

        /** The Old Persian Unicode 4.1 block. */
        public static final UnicodeBlock OLD_PERSIAN = new UnicodeBlock("OLD_PERSIAN");

        /** The Phonetic Extensions Supplement Unicode 4.1 block. */
        public static final UnicodeBlock PHONETIC_EXTENSIONS_SUPPLEMENT = new UnicodeBlock("PHONETIC_EXTENSIONS_SUPPLEMENT");

        /** The Supplemental Punctuation Unicode 4.1 block. */
        public static final UnicodeBlock SUPPLEMENTAL_PUNCTUATION = new UnicodeBlock("SUPPLEMENTAL_PUNCTUATION");

        /** The Syloti Nagri Unicode 4.1 block. */
        public static final UnicodeBlock SYLOTI_NAGRI = new UnicodeBlock("SYLOTI_NAGRI");

        /** The Tifinagh Unicode 4.1 block. */
        public static final UnicodeBlock TIFINAGH = new UnicodeBlock("TIFINAGH");

        /** The Vertical Forms Unicode 4.1 block. */
        public static final UnicodeBlock VERTICAL_FORMS = new UnicodeBlock("VERTICAL_FORMS");

        // Unicode 5.0.

        /** The NKo Unicode 5.0 block. */
        public static final UnicodeBlock NKO = new UnicodeBlock("NKO");

        /** The Balinese Unicode 5.0 block. */
        public static final UnicodeBlock BALINESE = new UnicodeBlock("BALINESE");

        /** The Latin Extended C Unicode 5.0 block. */
        public static final UnicodeBlock LATIN_EXTENDED_C = new UnicodeBlock("LATIN_EXTENDED_C");

        /** The Latin Extended D Unicode 5.0 block. */
        public static final UnicodeBlock LATIN_EXTENDED_D = new UnicodeBlock("LATIN_EXTENDED_D");

        /** The Phags-pa Unicode 5.0 block. */
        public static final UnicodeBlock PHAGS_PA = new UnicodeBlock("PHAGS_PA");

        /** The Phoenician Unicode 5.0 block. */
        public static final UnicodeBlock PHOENICIAN = new UnicodeBlock("PHOENICIAN");

        /** The Cuneiform Unicode 5.0 block. */
        public static final UnicodeBlock CUNEIFORM = new UnicodeBlock("CUNEIFORM");

        /** The Cuneiform Numbers And Punctuation Unicode 5.0 block. */
        public static final UnicodeBlock CUNEIFORM_NUMBERS_AND_PUNCTUATION = new UnicodeBlock("CUNEIFORM_NUMBERS_AND_PUNCTUATION");

        /** The Counting Rod Numerals Unicode 5.0 block. */
        public static final UnicodeBlock COUNTING_ROD_NUMERALS = new UnicodeBlock("COUNTING_ROD_NUMERALS");

        // Unicode 5.1.

        /** The Sudanese Unicode 5.1 block. */
        public static final UnicodeBlock SUNDANESE = new UnicodeBlock("SUNDANESE");

        /** The Lepcha Unicode 5.1 block. */
        public static final UnicodeBlock LEPCHA = new UnicodeBlock("LEPCHA");

        /** The Ol Chiki Unicode 5.1 block. */
        public static final UnicodeBlock OL_CHIKI = new UnicodeBlock("OL_CHIKI");

        /** The Cyrillic Extended-A Unicode 5.1 block. */
        public static final UnicodeBlock CYRILLIC_EXTENDED_A = new UnicodeBlock("CYRILLIC_EXTENDED_A");

        /** The Vai Unicode 5.1 block. */
        public static final UnicodeBlock VAI = new UnicodeBlock("VAI");

        /** The Cyrillic Extended-B Unicode 5.1 block. */
        public static final UnicodeBlock CYRILLIC_EXTENDED_B = new UnicodeBlock("CYRILLIC_EXTENDED_B");

        /** The Saurashtra Unicode 5.1 block. */
        public static final UnicodeBlock SAURASHTRA = new UnicodeBlock("SAURASHTRA");

        /** The Kayah Li Unicode 5.1 block. */
        public static final UnicodeBlock KAYAH_LI = new UnicodeBlock("KAYAH_LI");

        /** The Rejang Unicode 5.1 block. */
        public static final UnicodeBlock REJANG = new UnicodeBlock("REJANG");

        /** The Cham Unicode 5.1 block. */
        public static final UnicodeBlock CHAM = new UnicodeBlock("CHAM");

        /** The Ancient Symbols Unicode 5.1 block. */
        public static final UnicodeBlock ANCIENT_SYMBOLS = new UnicodeBlock("ANCIENT_SYMBOLS");

        /** The Phaistos Disc Unicode 5.1 block. */
        public static final UnicodeBlock PHAISTOS_DISC = new UnicodeBlock("PHAISTOS_DISC");

        /** The Lycian Unicode 5.1 block. */
        public static final UnicodeBlock LYCIAN = new UnicodeBlock("LYCIAN");

        /** The Carian Unicode 5.1 block. */
        public static final UnicodeBlock CARIAN = new UnicodeBlock("CARIAN");

        /** The Lydian Unicode 5.1 block. */
        public static final UnicodeBlock LYDIAN = new UnicodeBlock("LYDIAN");

        /** The Mahjong Tiles Unicode 5.1 block. */
        public static final UnicodeBlock MAHJONG_TILES = new UnicodeBlock("MAHJONG_TILES");

        /** The Domino Tiles Unicode 5.1 block. */
        public static final UnicodeBlock DOMINO_TILES = new UnicodeBlock("DOMINO_TILES");

        // Unicode 5.2.

        /** The Samaritan Unicode 5.2 block. */
        public static final UnicodeBlock SAMARITAN = new UnicodeBlock("SAMARITAN");

        /** The Unified Canadian Aboriginal Syllabics Expanded Unicode 5.2 block. */
        public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED");

        /** The Tai Tham Unicode 5.2 block. */
        public static final UnicodeBlock TAI_THAM = new UnicodeBlock("TAI_THAM");

        /** The Vedic Extensions Unicode 5.2 block. */
        public static final UnicodeBlock VEDIC_EXTENSIONS = new UnicodeBlock("VEDIC_EXTENSIONS");

        /** The Lisu Extensions Unicode 5.2 block. */
        public static final UnicodeBlock LISU = new UnicodeBlock("LISU");

        /** The Bamum Extensions Unicode 5.2 block. */
        public static final UnicodeBlock BAMUM = new UnicodeBlock("BAMUM");

        /** The Common Indic Number Forms Unicode 5.2 block. */
        public static final UnicodeBlock COMMON_INDIC_NUMBER_FORMS = new UnicodeBlock("COMMON_INDIC_NUMBER_FORMS");

        /** The Devanagari Extended Unicode 5.2 block. */
        public static final UnicodeBlock DEVANAGARI_EXTENDED = new UnicodeBlock("DEVANAGARI_EXTENDED");

        /** The Hangul Jamo Extended-A Unicode 5.2 block. */
        public static final UnicodeBlock HANGUL_JAMO_EXTENDED_A = new UnicodeBlock("HANGUL_JAMO_EXTENDED_A");

        /** The Javanese Unicode 5.2 block. */
        public static final UnicodeBlock JAVANESE = new UnicodeBlock("JAVANESE");

        /** The Myanmar Extended-A Unicode 5.2 block. */
        public static final UnicodeBlock MYANMAR_EXTENDED_A = new UnicodeBlock("MYANMAR_EXTENDED_A");

        /** The Tai Viet Unicode 5.2 block. */
        public static final UnicodeBlock TAI_VIET = new UnicodeBlock("TAI_VIET");

        /** The Meetei Mayek Unicode 5.2 block. */
        public static final UnicodeBlock MEETEI_MAYEK = new UnicodeBlock("MEETEI_MAYEK");

        /** The Hangul Jamo Extended-B Unicode 5.2 block. */
        public static final UnicodeBlock HANGUL_JAMO_EXTENDED_B = new UnicodeBlock("HANGUL_JAMO_EXTENDED_B");

        /** The Imperial Aramaic Unicode 5.2 block. */
        public static final UnicodeBlock IMPERIAL_ARAMAIC = new UnicodeBlock("IMPERIAL_ARAMAIC");

        /** The Old South Arabian Unicode 5.2 block. */
        public static final UnicodeBlock OLD_SOUTH_ARABIAN = new UnicodeBlock("OLD_SOUTH_ARABIAN");

        /** The Avestan Unicode 5.2 block. */
        public static final UnicodeBlock AVESTAN = new UnicodeBlock("AVESTAN");

        /** The Inscriptional Pathian Unicode 5.2 block. */
        public static final UnicodeBlock INSCRIPTIONAL_PARTHIAN = new UnicodeBlock("INSCRIPTIONAL_PARTHIAN");

        /** The Inscriptional Pahlavi Unicode 5.2 block. */
        public static final UnicodeBlock INSCRIPTIONAL_PAHLAVI = new UnicodeBlock("INSCRIPTIONAL_PAHLAVI");

        /** The Old Turkic Unicode 5.2 block. */
        public static final UnicodeBlock OLD_TURKIC = new UnicodeBlock("OLD_TURKIC");

        /** The Rumi Numeral Symbols Unicode 5.2 block. */
        public static final UnicodeBlock RUMI_NUMERAL_SYMBOLS = new UnicodeBlock("RUMI_NUMERAL_SYMBOLS");

        /** The Kaithi Unicode 5.2 block. */
        public static final UnicodeBlock KAITHI = new UnicodeBlock("KAITHI");

        /** The Egyptian Hieroglyphs Unicode 5.2 block. */
        public static final UnicodeBlock EGYPTIAN_HIEROGLYPHS = new UnicodeBlock("EGYPTIAN_HIEROGLYPHS");

        /** The Enclosed Alphanumeric Supplement Unicode 5.2 block. */
        public static final UnicodeBlock ENCLOSED_ALPHANUMERIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_ALPHANUMERIC_SUPPLEMENT");

        /** The Enclosed Ideographic Supplement Unicode 5.2 block. */
        public static final UnicodeBlock ENCLOSED_IDEOGRAPHIC_SUPPLEMENT = new UnicodeBlock("ENCLOSED_IDEOGRAPHIC_SUPPLEMENT");

        /** The CJK Unified Ideographs Unicode 5.2 block. */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C");

        // Unicode 6.0.

        /** The Mandaic Unicode 6.0 block. */
        public static final UnicodeBlock MANDAIC = new UnicodeBlock("MANDAIC");

        /** The Batak Unicode 6.0 block. */
        public static final UnicodeBlock BATAK = new UnicodeBlock("BATAK");

        /** The Ethiopic Extended-A Unicode 6.0 block. */
        public static final UnicodeBlock ETHIOPIC_EXTENDED_A = new UnicodeBlock("ETHIOPIC_EXTENDED_A");

        /** The Brahmi Unicode 6.0 block. */
        public static final UnicodeBlock BRAHMI = new UnicodeBlock("BRAHMI");

        /** The Bamum Supplement Unicode 6.0 block. */
        public static final UnicodeBlock BAMUM_SUPPLEMENT = new UnicodeBlock("BAMUM_SUPPLEMENT");

        /** The Kana Supplement Unicode 6.0 block. */
        public static final UnicodeBlock KANA_SUPPLEMENT = new UnicodeBlock("KANA_SUPPLEMENT");

        /** The Playing Cards Supplement Unicode 6.0 block. */
        public static final UnicodeBlock PLAYING_CARDS = new UnicodeBlock("PLAYING_CARDS");

        /** The Miscellaneous Symbols And Pictographs Supplement Unicode 6.0 block. */
        public static final UnicodeBlock MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS");

        /** The Emoticons Unicode 6.0 block. */
        public static final UnicodeBlock EMOTICONS = new UnicodeBlock("EMOTICONS");

        /** The Transport And Map Symbols Unicode 6.0 block. */
        public static final UnicodeBlock TRANSPORT_AND_MAP_SYMBOLS = new UnicodeBlock("TRANSPORT_AND_MAP_SYMBOLS");

        /** The Alchemical Symbols Unicode 6.0 block. */
        public static final UnicodeBlock ALCHEMICAL_SYMBOLS = new UnicodeBlock("ALCHEMICAL_SYMBOLS");

        /** The CJK Unified Ideographs Extension-D Unicode 6.0 block. */
        public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D");

        /*
         * All of the UnicodeBlocks above, in the icu4c UBlock enum order.
         */
        private static UnicodeBlock[] BLOCKS = new UnicodeBlock[] {
            null, // icu4c numbers blocks starting at 1, so index 0 should be null.

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

            // Unicode 3.1.
            UnicodeBlock.OLD_ITALIC,
            UnicodeBlock.GOTHIC,
            UnicodeBlock.DESERET,
            UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS,
            UnicodeBlock.MUSICAL_SYMBOLS,
            UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B,
            UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT,
            UnicodeBlock.TAGS,

            // Unicode 3.2.
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

            // Unicode 4.0.
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
            UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT,

            // Unicode 4.1.
            UnicodeBlock.ANCIENT_GREEK_MUSICAL_NOTATION,
            UnicodeBlock.ANCIENT_GREEK_NUMBERS,
            UnicodeBlock.ARABIC_SUPPLEMENT,
            UnicodeBlock.BUGINESE,
            UnicodeBlock.CJK_STROKES,
            UnicodeBlock.COMBINING_DIACRITICAL_MARKS_SUPPLEMENT,
            UnicodeBlock.COPTIC,
            UnicodeBlock.ETHIOPIC_EXTENDED,
            UnicodeBlock.ETHIOPIC_SUPPLEMENT,
            UnicodeBlock.GEORGIAN_SUPPLEMENT,
            UnicodeBlock.GLAGOLITIC,
            UnicodeBlock.KHAROSHTHI,
            UnicodeBlock.MODIFIER_TONE_LETTERS,
            UnicodeBlock.NEW_TAI_LUE,
            UnicodeBlock.OLD_PERSIAN,
            UnicodeBlock.PHONETIC_EXTENSIONS_SUPPLEMENT,
            UnicodeBlock.SUPPLEMENTAL_PUNCTUATION,
            UnicodeBlock.SYLOTI_NAGRI,
            UnicodeBlock.TIFINAGH,
            UnicodeBlock.VERTICAL_FORMS,

            // Unicode 5.0.
            UnicodeBlock.NKO,
            UnicodeBlock.BALINESE,
            UnicodeBlock.LATIN_EXTENDED_C,
            UnicodeBlock.LATIN_EXTENDED_D,
            UnicodeBlock.PHAGS_PA,
            UnicodeBlock.PHOENICIAN,
            UnicodeBlock.CUNEIFORM,
            UnicodeBlock.CUNEIFORM_NUMBERS_AND_PUNCTUATION,
            UnicodeBlock.COUNTING_ROD_NUMERALS,

            // Unicode 5.1.
            UnicodeBlock.SUNDANESE,
            UnicodeBlock.LEPCHA,
            UnicodeBlock.OL_CHIKI,
            UnicodeBlock.CYRILLIC_EXTENDED_A,
            UnicodeBlock.VAI,
            UnicodeBlock.CYRILLIC_EXTENDED_B,
            UnicodeBlock.SAURASHTRA,
            UnicodeBlock.KAYAH_LI,
            UnicodeBlock.REJANG,
            UnicodeBlock.CHAM,
            UnicodeBlock.ANCIENT_SYMBOLS,
            UnicodeBlock.PHAISTOS_DISC,
            UnicodeBlock.LYCIAN,
            UnicodeBlock.CARIAN,
            UnicodeBlock.LYDIAN,
            UnicodeBlock.MAHJONG_TILES,
            UnicodeBlock.DOMINO_TILES,

            // Unicode 5.2.
            UnicodeBlock.SAMARITAN,
            UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED,
            UnicodeBlock.TAI_THAM,
            UnicodeBlock.VEDIC_EXTENSIONS,
            UnicodeBlock.LISU,
            UnicodeBlock.BAMUM,
            UnicodeBlock.COMMON_INDIC_NUMBER_FORMS,
            UnicodeBlock.DEVANAGARI_EXTENDED,
            UnicodeBlock.HANGUL_JAMO_EXTENDED_A,
            UnicodeBlock.JAVANESE,
            UnicodeBlock.MYANMAR_EXTENDED_A,
            UnicodeBlock.TAI_VIET,
            UnicodeBlock.MEETEI_MAYEK,
            UnicodeBlock.HANGUL_JAMO_EXTENDED_B,
            UnicodeBlock.IMPERIAL_ARAMAIC,
            UnicodeBlock.OLD_SOUTH_ARABIAN,
            UnicodeBlock.AVESTAN,
            UnicodeBlock.INSCRIPTIONAL_PARTHIAN,
            UnicodeBlock.INSCRIPTIONAL_PAHLAVI,
            UnicodeBlock.OLD_TURKIC,
            UnicodeBlock.RUMI_NUMERAL_SYMBOLS,
            UnicodeBlock.KAITHI,
            UnicodeBlock.EGYPTIAN_HIEROGLYPHS,
            UnicodeBlock.ENCLOSED_ALPHANUMERIC_SUPPLEMENT,
            UnicodeBlock.ENCLOSED_IDEOGRAPHIC_SUPPLEMENT,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C,

            // Unicode 6.0.
            UnicodeBlock.MANDAIC,
            UnicodeBlock.BATAK,
            UnicodeBlock.ETHIOPIC_EXTENDED_A,
            UnicodeBlock.BRAHMI,
            UnicodeBlock.BAMUM_SUPPLEMENT,
            UnicodeBlock.KANA_SUPPLEMENT,
            UnicodeBlock.PLAYING_CARDS,
            UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS,
            UnicodeBlock.EMOTICONS,
            UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS,
            UnicodeBlock.ALCHEMICAL_SYMBOLS,
            UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D,
        };

        /**
         * Returns the Unicode block for the given block name, or null if there is no
         * such block.
         *
         * <p>Block names may be one of the following:
         * <ul>
         * <li>Canonical block name, as defined by the Unicode specification;
         * case-insensitive.</li>
         * <li>Canonical block name without any spaces, as defined by the
         * Unicode specification; case-insensitive.</li>
         * <li>A {@code UnicodeBlock} constant identifier. This is determined by
         * converting the canonical name to uppercase and replacing all spaces and hyphens
         * with underscores.</li>
         * </ul>
         *
         * @throws NullPointerException
         *             if {@code blockName == null}.
         * @throws IllegalArgumentException
         *             if {@code blockName} is not the name of any known block.
         * @since 1.5
         */
        public static UnicodeBlock forName(String blockName) {
            if (blockName == null) {
                throw new NullPointerException("blockName == null");
            }
            int block = forNameImpl(blockName);
            if (block == -1) {
                throw new IllegalArgumentException("Unknown block: " + blockName);
            }
            return BLOCKS[block];
        }

        /**
         * Returns the Unicode block containing the given code point, or null if the
         * code point does not belong to any known block.
         */
        public static UnicodeBlock of(char c) {
            return of((int) c);
        }

        /**
         * Returns the Unicode block containing the given code point, or null if the
         * code point does not belong to any known block.
         */
        public static UnicodeBlock of(int codePoint) {
            checkValidCodePoint(codePoint);
            int block = ofImpl(codePoint);
            if (block == -1 || block >= BLOCKS.length) {
                return null;
            }
            return BLOCKS[block];
        }

        private UnicodeBlock(String blockName) {
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
     * Returns true if the given character is a high or low surrogate.
     * @since 1.7
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("dst == null");
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
            throw new NullPointerException("seq == null");
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
            throw new NullPointerException("seq == null");
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
     * Returns a human-readable name for the given code point,
     * or null if the code point is unassigned.
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
     * <p>Note that the exact strings returned will vary from release to release.
     *
     * @throws IllegalArgumentException if {@code codePoint} is not a valid code point.
     * @since 1.7
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
     */
    public static char highSurrogate(int codePoint) {
        return (char) ((codePoint >> 10) + 0xd7c0);
    }

    /**
     * Returns the low surrogate for the given code point. The result is meaningless if
     * the given code point is not a supplementary character.
     * @since 1.7
     */
    public static char lowSurrogate(int codePoint) {
        return (char) ((codePoint & 0x3ff) | 0xdc00);
    }

    /**
     * Returns true if the given code point is alphabetic. That is,
     * if it is in any of the Lu, Ll, Lt, Lm, Lo, Nl, or Other_Alphabetic categories.
     * @since 1.7
     */
    public static native boolean isAlphabetic(int codePoint);

    /**
     * Returns true if the given code point is in the Basic Multilingual Plane (BMP).
     * Such code points can be represented by a single {@code char}.
     * @since 1.7
     */
    public static boolean isBmpCodePoint(int codePoint) {
       return codePoint >= Character.MIN_VALUE && codePoint <= Character.MAX_VALUE;
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
     * Returns true if the given code point is a CJKV ideographic character.
     * @since 1.7
     */
    public static native boolean isIdeographic(int codePoint);

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
     * @deprecated Use {@link #isJavaIdentifierStart(char)} instead.
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
     * @deprecated Use {@link #isJavaIdentifierPart(char)} instead.
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
     * Use {@link #isWhitespace(char)} instead.
     * @deprecated Use {@link #isWhitespace(char)} instead.
     */
    @Deprecated
    public static boolean isSpace(char c) {
        return c == '\n' || c == '\t' || c == '\f' || c == '\r' || c == ' ';
    }

    /**
     * See {@link #isSpaceChar(int)}.
     */
    public static boolean isSpaceChar(char c) {
        return isSpaceChar((int) c);
    }

    /**
     * Returns true if the given code point is a Unicode space character.
     * The exact set of characters considered as whitespace varies with Unicode version.
     * Note that non-breaking spaces are considered whitespace.
     * Note also that line separators are not considered whitespace; see {@link #isWhitespace}
     * for an alternative.
     */
    public static boolean isSpaceChar(int codePoint) {
        // We don't just call into icu4c because of the JNI overhead. Ideally we'd fix that.
        // SPACE or NO-BREAK SPACE?
        if (codePoint == 0x20 || codePoint == 0xa0) {
            return true;
        }
        if (codePoint < 0x1000) {
            return false;
        }
        // OGHAM SPACE MARK or MONGOLIAN VOWEL SEPARATOR?
        if (codePoint == 0x1680 || codePoint == 0x180e) {
            return true;
        }
        if (codePoint < 0x2000) {
            return false;
        }
        if (codePoint <= 0xffff) {
            // Other whitespace from General Punctuation...
            return codePoint <= 0x200a || codePoint == 0x2028 || codePoint == 0x2029 || codePoint == 0x202f || codePoint == 0x205f ||
                codePoint == 0x3000; // ...or CJK Symbols and Punctuation?
        }
        // Let icu4c worry about non-BMP code points.
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
     * See {@link #isWhitespace(int)}.
     */
    public static boolean isWhitespace(char c) {
        return isWhitespace((int) c);
    }

    /**
     * Returns true if the given code point is a Unicode whitespace character.
     * The exact set of characters considered as whitespace varies with Unicode version.
     * Note that non-breaking spaces are not considered whitespace.
     * Note also that line separators are considered whitespace; see {@link #isSpaceChar}
     * for an alternative.
     */
    public static boolean isWhitespace(int codePoint) {
        // We don't just call into icu4c because of the JNI overhead. Ideally we'd fix that.
        // Any ASCII whitespace character?
        if ((codePoint >= 0x1c && codePoint <= 0x20) || (codePoint >= 0x09 && codePoint <= 0x0d)) {
            return true;
        }
        if (codePoint < 0x1000) {
            return false;
        }
        // OGHAM SPACE MARK or MONGOLIAN VOWEL SEPARATOR?
        if (codePoint == 0x1680 || codePoint == 0x180e) {
            return true;
        }
        if (codePoint < 0x2000) {
            return false;
        }
        // Exclude General Punctuation's non-breaking spaces (which includes FIGURE SPACE).
        if (codePoint == 0x2007 || codePoint == 0x202f) {
            return false;
        }
        if (codePoint <= 0xffff) {
            // Other whitespace from General Punctuation...
            return codePoint <= 0x200a || codePoint == 0x2028 || codePoint == 0x2029 || codePoint == 0x205f ||
                codePoint == 0x3000; // ...or CJK Symbols and Punctuation?
        }
        // Let icu4c worry about non-BMP code points.
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
