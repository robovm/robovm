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
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.harmony.luni.util.BinarySearch;

import com.ibm.icu.lang.UCharacter;

/**
 * The wrapper for the primitive type {@code char}. This class also provides a
 * number of utility methods for working with characters.
 * <p>
 * Character data is based upon the Unicode Standard, 4.0. The Unicode
 * specification, character tables and other information are available at <a
 * href="http://www.unicode.org/">http://www.unicode.org/</a>.
 * <p>
 * Unicode characters are referred to as <i>code points</i>. The range of valid
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
 * @since 1.0
 */
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
    public static final Class<Character> TYPE = (Class<Character>) new char[0]
            .getClass().getComponentType();

    // Note: This can't be set to "char.class", since *that* is
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

    // Unicode 3.0.1 (same as Unicode 3.0.0)
    private static final String bidiKeys = "\u0000\t\f\u000e\u001c\u001f!#&+/1:<A[a{\u007f\u0085\u0087\u00a0\u00a2\u00a6\u00aa\u00ac\u00b0\u00b2\u00b4\u00b7\u00b9\u00bb\u00c0\u00d7\u00d9\u00f7\u00f9\u0222\u0250\u02b0\u02b9\u02bb\u02c2\u02d0\u02d2\u02e0\u02e5\u02ee\u0300\u0360\u0374\u037a\u037e\u0384\u0386\u0389\u038c\u038e\u03a3\u03d0\u03da\u0400\u0483\u0488\u048c\u04c7\u04cb\u04d0\u04f8\u0531\u0559\u0561\u0589\u0591\u05a3\u05bb\u05be\u05c2\u05d0\u05f0\u060c\u061b\u061f\u0621\u0640\u064b\u0660\u066a\u066c\u0670\u0672\u06d6\u06e5\u06e7\u06e9\u06eb\u06f0\u06fa\u0700\u070f\u0711\u0713\u0730\u0780\u07a6\u0901\u0903\u0905\u093c\u093e\u0941\u0949\u094d\u0950\u0952\u0958\u0962\u0964\u0981\u0983\u0985\u098f\u0993\u09aa\u09b2\u09b6\u09bc\u09be\u09c1\u09c7\u09cb\u09cd\u09d7\u09dc\u09df\u09e2\u09e6\u09f2\u09f4\u0a02\u0a05\u0a0f\u0a13\u0a2a\u0a32\u0a35\u0a38\u0a3c\u0a3e\u0a41\u0a47\u0a4b\u0a59\u0a5e\u0a66\u0a70\u0a72\u0a81\u0a83\u0a85\u0a8d\u0a8f\u0a93\u0aaa\u0ab2\u0ab5\u0abc\u0abe\u0ac1\u0ac7\u0ac9\u0acb\u0acd\u0ad0\u0ae0\u0ae6\u0b01\u0b03\u0b05\u0b0f\u0b13\u0b2a\u0b32\u0b36\u0b3c\u0b3e\u0b42\u0b47\u0b4b\u0b4d\u0b56\u0b5c\u0b5f\u0b66\u0b82\u0b85\u0b8e\u0b92\u0b99\u0b9c\u0b9e\u0ba3\u0ba8\u0bae\u0bb7\u0bbe\u0bc0\u0bc2\u0bc6\u0bca\u0bcd\u0bd7\u0be7\u0c01\u0c05\u0c0e\u0c12\u0c2a\u0c35\u0c3e\u0c41\u0c46\u0c4a\u0c55\u0c60\u0c66\u0c82\u0c85\u0c8e\u0c92\u0caa\u0cb5\u0cbe\u0cc1\u0cc6\u0cc8\u0cca\u0ccc\u0cd5\u0cde\u0ce0\u0ce6\u0d02\u0d05\u0d0e\u0d12\u0d2a\u0d3e\u0d41\u0d46\u0d4a\u0d4d\u0d57\u0d60\u0d66\u0d82\u0d85\u0d9a\u0db3\u0dbd\u0dc0\u0dca\u0dcf\u0dd2\u0dd6\u0dd8\u0df2\u0e01\u0e31\u0e33\u0e35\u0e3f\u0e41\u0e47\u0e4f\u0e81\u0e84\u0e87\u0e8a\u0e8d\u0e94\u0e99\u0ea1\u0ea5\u0ea7\u0eaa\u0ead\u0eb1\u0eb3\u0eb5\u0ebb\u0ebd\u0ec0\u0ec6\u0ec8\u0ed0\u0edc\u0f00\u0f18\u0f1a\u0f35\u0f3a\u0f3e\u0f49\u0f71\u0f7f\u0f81\u0f85\u0f87\u0f89\u0f90\u0f99\u0fbe\u0fc6\u0fc8\u0fcf\u1000\u1023\u1029\u102c\u102e\u1031\u1036\u1038\u1040\u1058\u10a0\u10d0\u10fb\u1100\u115f\u11a8\u1200\u1208\u1248\u124a\u1250\u1258\u125a\u1260\u1288\u128a\u1290\u12b0\u12b2\u12b8\u12c0\u12c2\u12c8\u12d0\u12d8\u12f0\u1310\u1312\u1318\u1320\u1348\u1361\u13a0\u1401\u1680\u1682\u169b\u16a0\u1780\u17b7\u17be\u17c6\u17c8\u17ca\u17d4\u17db\u17e0\u1800\u180b\u1810\u1820\u1880\u18a9\u1e00\u1ea0\u1f00\u1f18\u1f20\u1f48\u1f50\u1f59\u1f5b\u1f5d\u1f5f\u1f80\u1fb6\u1fbd\u1fc0\u1fc2\u1fc6\u1fcd\u1fd0\u1fd6\u1fdd\u1fe0\u1fed\u1ff2\u1ff6\u1ffd\u2000\u200b\u200e\u2010\u2028\u202a\u202c\u202e\u2030\u2035\u2048\u206a\u2070\u2074\u207a\u207c\u207f\u2081\u208a\u208c\u20a0\u20d0\u2100\u2102\u2104\u2107\u2109\u210b\u2114\u2117\u2119\u211e\u2124\u212b\u212e\u2130\u2132\u2134\u213a\u2153\u2160\u2190\u2200\u2212\u2214\u2300\u2336\u237b\u237d\u2395\u2397\u2400\u2440\u2460\u249c\u24ea\u2500\u25a0\u2600\u2619\u2701\u2706\u270c\u2729\u274d\u274f\u2756\u2758\u2761\u2776\u2798\u27b1\u2800\u2e80\u2e9b\u2f00\u2ff0\u3000\u3002\u3005\u3008\u3021\u302a\u3030\u3032\u3036\u3038\u303e\u3041\u3099\u309b\u309d\u30a1\u30fb\u30fd\u3105\u3131\u3190\u3200\u3220\u3260\u327f\u32c0\u32d0\u3300\u337b\u33e0\u3400\u4e00\ua000\ua490\ua4a4\ua4b5\ua4c2\ua4c6\uac00\ud800\ufb00\ufb13\ufb1d\ufb20\ufb29\ufb2b\ufb38\ufb3e\ufb40\ufb43\ufb46\ufb50\ufbd3\ufd3e\ufd50\ufd92\ufdf0\ufe20\ufe30\ufe49\ufe50\ufe54\ufe57\ufe5f\ufe61\ufe63\ufe65\ufe68\ufe6a\ufe70\ufe74\ufe76\ufeff\uff01\uff03\uff06\uff0b\uff0f\uff11\uff1a\uff1c\uff21\uff3b\uff41\uff5b\uff61\uff66\uffc2\uffca\uffd2\uffda\uffe0\uffe2\uffe5\uffe8\ufff9\ufffc";

    private static final char[] bidiValues = "\b\n\u000b\u0c0b\r\u0b0d\u001b\n\u001e\u000b \u0c0d\"\u000e%\u0006*\u000e.\u06080\u05049\u0004;\u0e08@\u000eZ\u0001`\u000ez\u0001~\u000e\u0084\n\u0086\u0b0a\u009f\n\u00a1\u0e08\u00a5\u0006\u00a9\u000e\u00ab\u0e01\u00af\u000e\u00b1\u0006\u00b3\u0004\u00b6\u010e\u00b8\u000e\u00ba\u0401\u00bf\u000e\u00d6\u0001\u00d8\u0e01\u00f6\u0001\u00f8\u0e01\u021f\u0001\u0233\u0001\u02ad\u0001\u02b8\u0001\u02ba\u000e\u02c1\u0001\u02cf\u000e\u02d1\u0001\u02df\u000e\u02e4\u0001\u02ed\u000e\u02ee\u0001\u034e\t\u0362\t\u0375\u000e\u037a\u0001\u037e\u000e\u0385\u000e\u0388\u0e01\u038a\u0001\u038c\u0001\u03a1\u0001\u03ce\u0001\u03d7\u0001\u03f3\u0001\u0482\u0001\u0486\t\u0489\t\u04c4\u0001\u04c8\u0001\u04cc\u0001\u04f5\u0001\u04f9\u0001\u0556\u0001\u055f\u0001\u0587\u0001\u058a\u010e\u05a1\t\u05b9\t\u05bd\t\u05c1\u0902\u05c4\u0209\u05ea\u0002\u05f4\u0002\u060c\b\u061b\u0300\u061f\u0300\u063a\u0003\u064a\u0003\u0655\t\u0669\u0007\u066b\u0706\u066d\u0307\u0671\u0309\u06d5\u0003\u06e4\t\u06e6\u0003\u06e8\t\u06ea\u0e09\u06ed\t\u06f9\u0004\u06fe\u0003\u070d\u0003\u0710\u0a03\u0712\u0903\u072c\u0003\u074a\t\u07a5\u0003\u07b0\t\u0902\t\u0903\u0100\u0939\u0001\u093d\u0109\u0940\u0001\u0948\t\u094c\u0001\u094d\u0900\u0951\u0901\u0954\t\u0961\u0001\u0963\t\u0970\u0001\u0982\u0901\u0983\u0100\u098c\u0001\u0990\u0001\u09a8\u0001\u09b0\u0001\u09b2\u0001\u09b9\u0001\u09bc\t\u09c0\u0001\u09c4\t\u09c8\u0001\u09cc\u0001\u09cd\u0900\u09d7\u0100\u09dd\u0001\u09e1\u0001\u09e3\t\u09f1\u0001\u09f3\u0006\u09fa\u0001\u0a02\t\u0a0a\u0001\u0a10\u0001\u0a28\u0001\u0a30\u0001\u0a33\u0001\u0a36\u0001\u0a39\u0001\u0a3c\t\u0a40\u0001\u0a42\t\u0a48\t\u0a4d\t\u0a5c\u0001\u0a5e\u0001\u0a6f\u0001\u0a71\t\u0a74\u0001\u0a82\t\u0a83\u0100\u0a8b\u0001\u0a8d\u0100\u0a91\u0001\u0aa8\u0001\u0ab0\u0001\u0ab3\u0001\u0ab9\u0001\u0abd\u0109\u0ac0\u0001\u0ac5\t\u0ac8\t\u0ac9\u0100\u0acc\u0001\u0acd\u0900\u0ad0\u0001\u0ae0\u0001\u0aef\u0001\u0b02\u0901\u0b03\u0100\u0b0c\u0001\u0b10\u0001\u0b28\u0001\u0b30\u0001\u0b33\u0001\u0b39\u0001\u0b3d\u0109\u0b41\u0901\u0b43\t\u0b48\u0001\u0b4c\u0001\u0b4d\u0900\u0b57\u0109\u0b5d\u0001\u0b61\u0001\u0b70\u0001\u0b83\u0109\u0b8a\u0001\u0b90\u0001\u0b95\u0001\u0b9a\u0001\u0b9c\u0001\u0b9f\u0001\u0ba4\u0001\u0baa\u0001\u0bb5\u0001\u0bb9\u0001\u0bbf\u0001\u0bc1\u0109\u0bc2\u0001\u0bc8\u0001\u0bcc\u0001\u0bcd\u0900\u0bd7\u0100\u0bf2\u0001\u0c03\u0001\u0c0c\u0001\u0c10\u0001\u0c28\u0001\u0c33\u0001\u0c39\u0001\u0c40\t\u0c44\u0001\u0c48\t\u0c4d\t\u0c56\t\u0c61\u0001\u0c6f\u0001\u0c83\u0001\u0c8c\u0001\u0c90\u0001\u0ca8\u0001\u0cb3\u0001\u0cb9\u0001\u0cc0\u0901\u0cc4\u0001\u0cc7\u0109\u0cc8\u0001\u0ccb\u0001\u0ccd\t\u0cd6\u0001\u0cde\u0001\u0ce1\u0001\u0cef\u0001\u0d03\u0001\u0d0c\u0001\u0d10\u0001\u0d28\u0001\u0d39\u0001\u0d40\u0001\u0d43\t\u0d48\u0001\u0d4c\u0001\u0d4d\u0900\u0d57\u0100\u0d61\u0001\u0d6f\u0001\u0d83\u0001\u0d96\u0001\u0db1\u0001\u0dbb\u0001\u0dbd\u0100\u0dc6\u0001\u0dca\t\u0dd1\u0001\u0dd4\t\u0dd6\t\u0ddf\u0001\u0df4\u0001\u0e30\u0001\u0e32\u0901\u0e34\u0109\u0e3a\t\u0e40\u0601\u0e46\u0001\u0e4e\t\u0e5b\u0001\u0e82\u0001\u0e84\u0001\u0e88\u0001\u0e8a\u0001\u0e8d\u0100\u0e97\u0001\u0e9f\u0001\u0ea3\u0001\u0ea5\u0100\u0ea7\u0100\u0eab\u0001\u0eb0\u0001\u0eb2\u0901\u0eb4\u0109\u0eb9\t\u0ebc\t\u0ebd\u0100\u0ec4\u0001\u0ec6\u0001\u0ecd\t\u0ed9\u0001\u0edd\u0001\u0f17\u0001\u0f19\t\u0f34\u0001\u0f39\u0901\u0f3d\u000e\u0f47\u0001\u0f6a\u0001\u0f7e\t\u0f80\u0109\u0f84\t\u0f86\u0109\u0f88\u0901\u0f8b\u0001\u0f97\t\u0fbc\t\u0fc5\u0001\u0fc7\u0109\u0fcc\u0001\u0fcf\u0100\u1021\u0001\u1027\u0001\u102a\u0001\u102d\u0901\u1030\t\u1032\u0109\u1037\t\u1039\u0901\u1057\u0001\u1059\t\u10c5\u0001\u10f6\u0001\u10fb\u0100\u1159\u0001\u11a2\u0001\u11f9\u0001\u1206\u0001\u1246\u0001\u1248\u0001\u124d\u0001\u1256\u0001\u1258\u0001\u125d\u0001\u1286\u0001\u1288\u0001\u128d\u0001\u12ae\u0001\u12b0\u0001\u12b5\u0001\u12be\u0001\u12c0\u0001\u12c5\u0001\u12ce\u0001\u12d6\u0001\u12ee\u0001\u130e\u0001\u1310\u0001\u1315\u0001\u131e\u0001\u1346\u0001\u135a\u0001\u137c\u0001\u13f4\u0001\u1676\u0001\u1681\u010d\u169a\u0001\u169c\u000e\u16f0\u0001\u17b6\u0001\u17bd\t\u17c5\u0001\u17c7\u0109\u17c9\u0901\u17d3\t\u17da\u0001\u17dc\u0601\u17e9\u0001\u180a\u000e\u180e\n\u1819\u0001\u1877\u0001\u18a8\u0001\u18a9\u0900\u1e9b\u0001\u1ef9\u0001\u1f15\u0001\u1f1d\u0001\u1f45\u0001\u1f4d\u0001\u1f57\u0001\u1f59\u0100\u1f5b\u0100\u1f5d\u0100\u1f7d\u0001\u1fb4\u0001\u1fbc\u0001\u1fbf\u0e01\u1fc1\u000e\u1fc4\u0001\u1fcc\u0001\u1fcf\u000e\u1fd3\u0001\u1fdb\u0001\u1fdf\u000e\u1fec\u0001\u1fef\u000e\u1ff4\u0001\u1ffc\u0001\u1ffe\u000e\u200a\r\u200d\n\u200f\u0201\u2027\u000e\u2029\u0b0d\u202b\u110f\u202d\u1013\u202f\u0d12\u2034\u0006\u2046\u000e\u204d\u000e\u206f\n\u2070\u0004\u2079\u0004\u207b\u0006\u207e\u000e\u2080\u0104\u2089\u0004\u208b\u0006\u208e\u000e\u20af\u0006\u20e3\t\u2101\u000e\u2103\u0e01\u2106\u000e\u2108\u010e\u210a\u0e01\u2113\u0001\u2116\u010e\u2118\u000e\u211d\u0001\u2123\u000e\u212a\u0e01\u212d\u0001\u212f\u0106\u2131\u0001\u2133\u010e\u2139\u0001\u213a\u000e\u215f\u000e\u2183\u0001\u21f3\u000e\u2211\u000e\u2213\u0006\u22f1\u000e\u2335\u000e\u237a\u0001\u237b\u0e00\u2394\u000e\u2396\u010e\u239a\u000e\u2426\u000e\u244a\u000e\u249b\u0004\u24e9\u0001\u24ea\u0004\u2595\u000e\u25f7\u000e\u2613\u000e\u2671\u000e\u2704\u000e\u2709\u000e\u2727\u000e\u274b\u000e\u274d\u0e00\u2752\u000e\u2756\u000e\u275e\u000e\u2767\u000e\u2794\u000e\u27af\u000e\u27be\u000e\u28ff\u000e\u2e99\u000e\u2ef3\u000e\u2fd5\u000e\u2ffb\u000e\u3001\u0e0d\u3004\u000e\u3007\u0001\u3020\u000e\u3029\u0001\u302f\t\u3031\u010e\u3035\u0001\u3037\u000e\u303a\u0001\u303f\u000e\u3094\u0001\u309a\t\u309c\u000e\u309e\u0001\u30fa\u0001\u30fc\u0e01\u30fe\u0001\u312c\u0001\u318e\u0001\u31b7\u0001\u321c\u0001\u3243\u0001\u327b\u0001\u32b0\u0001\u32cb\u0001\u32fe\u0001\u3376\u0001\u33dd\u0001\u33fe\u0001\u4db5\u0001\u9fa5\u0001\ua48c\u0001\ua4a1\u000e\ua4b3\u000e\ua4c0\u000e\ua4c4\u000e\ua4c6\u000e\ud7a3\u0001\ufa2d\u0001\ufb06\u0001\ufb17\u0001\ufb1f\u0209\ufb28\u0002\ufb2a\u0602\ufb36\u0002\ufb3c\u0002\ufb3e\u0002\ufb41\u0002\ufb44\u0002\ufb4f\u0002\ufbb1\u0003\ufd3d\u0003\ufd3f\u000e\ufd8f\u0003\ufdc7\u0003\ufdfb\u0003\ufe23\t\ufe44\u000e\ufe4f\u000e\ufe52\u0e08\ufe56\u080e\ufe5e\u000e\ufe60\u060e\ufe62\u0e06\ufe64\u060e\ufe66\u000e\ufe69\u060e\ufe6b\u0e06\ufe72\u0003\ufe74\u0003\ufefc\u0003\ufeff\u0a00\uff02\u000e\uff05\u0006\uff0a\u000e\uff0e\u0608\uff10\u0504\uff19\u0004\uff1b\u0e08\uff20\u000e\uff3a\u0001\uff40\u000e\uff5a\u0001\uff5e\u000e\uff65\u000e\uffbe\u0001\uffc7\u0001\uffcf\u0001\uffd7\u0001\uffdc\u0001\uffe1\u0006\uffe4\u000e\uffe6\u0006\uffee\u000e\ufffb\n\ufffd\u000e"
            .getValue();

    private static final char[] mirrored = "\u0000\u0000\u0300\u5000\u0000\u2800\u0000\u2800\u0000\u0000\u0800\u0800\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0600`\u0000\u0000\u6000\u6000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u3f1e\ubc62\uf857\ufa0f\u1fff\u803c\ucff5\uffff\u9fff\u0107\uffcc\uc1ff\u3e00\uffc3\u3fff\u0003\u0f00\u0000\u0603\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\uff00\u0ff3"
            .getValue();

    // Unicode 3.0.1 (same as Unicode 3.0.0)
    private static final String typeKeys = "\u0000 \"$&(*-/1:<?A[]_a{}\u007f\u00a0\u00a2\u00a6\u00a8\u00aa\u00ac\u00ae\u00b1\u00b3\u00b5\u00b7\u00b9\u00bb\u00bd\u00bf\u00c1\u00d7\u00d9\u00df\u00f7\u00f9\u0100\u0138\u0149\u0179\u017f\u0181\u0183\u0187\u018a\u018c\u018e\u0192\u0194\u0197\u0199\u019c\u019e\u01a0\u01a7\u01ab\u01af\u01b2\u01b4\u01b8\u01ba\u01bc\u01be\u01c0\u01c4\u01c6\u01c8\u01ca\u01cc\u01dd\u01f0\u01f2\u01f4\u01f7\u01f9\u0222\u0250\u02b0\u02b9\u02bb\u02c2\u02d0\u02d2\u02e0\u02e5\u02ee\u0300\u0360\u0374\u037a\u037e\u0384\u0386\u0389\u038c\u038e\u0390\u0392\u03a3\u03ac\u03d0\u03d2\u03d5\u03da\u03f0\u0400\u0430\u0460\u0482\u0484\u0488\u048c\u04c1\u04c7\u04cb\u04d0\u04f8\u0531\u0559\u055b\u0561\u0589\u0591\u05a3\u05bb\u05be\u05c2\u05d0\u05f0\u05f3\u060c\u061b\u061f\u0621\u0640\u0642\u064b\u0660\u066a\u0670\u0672\u06d4\u06d6\u06dd\u06df\u06e5\u06e7\u06e9\u06eb\u06f0\u06fa\u06fd\u0700\u070f\u0711\u0713\u0730\u0780\u07a6\u0901\u0903\u0905\u093c\u093e\u0941\u0949\u094d\u0950\u0952\u0958\u0962\u0964\u0966\u0970\u0981\u0983\u0985\u098f\u0993\u09aa\u09b2\u09b6\u09bc\u09be\u09c1\u09c7\u09cb\u09cd\u09d7\u09dc\u09df\u09e2\u09e6\u09f0\u09f2\u09f4\u09fa\u0a02\u0a05\u0a0f\u0a13\u0a2a\u0a32\u0a35\u0a38\u0a3c\u0a3e\u0a41\u0a47\u0a4b\u0a59\u0a5e\u0a66\u0a70\u0a72\u0a81\u0a83\u0a85\u0a8d\u0a8f\u0a93\u0aaa\u0ab2\u0ab5\u0abc\u0abe\u0ac1\u0ac7\u0ac9\u0acb\u0acd\u0ad0\u0ae0\u0ae6\u0b01\u0b03\u0b05\u0b0f\u0b13\u0b2a\u0b32\u0b36\u0b3c\u0b3e\u0b42\u0b47\u0b4b\u0b4d\u0b56\u0b5c\u0b5f\u0b66\u0b70\u0b82\u0b85\u0b8e\u0b92\u0b99\u0b9c\u0b9e\u0ba3\u0ba8\u0bae\u0bb7\u0bbe\u0bc0\u0bc2\u0bc6\u0bca\u0bcd\u0bd7\u0be7\u0bf0\u0c01\u0c05\u0c0e\u0c12\u0c2a\u0c35\u0c3e\u0c41\u0c46\u0c4a\u0c55\u0c60\u0c66\u0c82\u0c85\u0c8e\u0c92\u0caa\u0cb5\u0cbe\u0cc1\u0cc6\u0cc8\u0cca\u0ccc\u0cd5\u0cde\u0ce0\u0ce6\u0d02\u0d05\u0d0e\u0d12\u0d2a\u0d3e\u0d41\u0d46\u0d4a\u0d4d\u0d57\u0d60\u0d66\u0d82\u0d85\u0d9a\u0db3\u0dbd\u0dc0\u0dca\u0dcf\u0dd2\u0dd6\u0dd8\u0df2\u0df4\u0e01\u0e31\u0e33\u0e35\u0e3f\u0e41\u0e46\u0e48\u0e4f\u0e51\u0e5a\u0e81\u0e84\u0e87\u0e8a\u0e8d\u0e94\u0e99\u0ea1\u0ea5\u0ea7\u0eaa\u0ead\u0eb1\u0eb3\u0eb5\u0ebb\u0ebd\u0ec0\u0ec6\u0ec8\u0ed0\u0edc\u0f00\u0f02\u0f04\u0f13\u0f18\u0f1a\u0f20\u0f2a\u0f34\u0f3a\u0f3e\u0f40\u0f49\u0f71\u0f7f\u0f81\u0f85\u0f87\u0f89\u0f90\u0f99\u0fbe\u0fc6\u0fc8\u0fcf\u1000\u1023\u1029\u102c\u102e\u1031\u1036\u1038\u1040\u104a\u1050\u1056\u1058\u10a0\u10d0\u10fb\u1100\u115f\u11a8\u1200\u1208\u1248\u124a\u1250\u1258\u125a\u1260\u1288\u128a\u1290\u12b0\u12b2\u12b8\u12c0\u12c2\u12c8\u12d0\u12d8\u12f0\u1310\u1312\u1318\u1320\u1348\u1361\u1369\u1372\u13a0\u1401\u166d\u166f\u1680\u1682\u169b\u16a0\u16eb\u16ee\u1780\u17b4\u17b7\u17be\u17c6\u17c8\u17ca\u17d4\u17db\u17e0\u1800\u1806\u1808\u180b\u1810\u1820\u1843\u1845\u1880\u18a9\u1e00\u1e96\u1ea0\u1f00\u1f08\u1f10\u1f18\u1f20\u1f28\u1f30\u1f38\u1f40\u1f48\u1f50\u1f59\u1f5b\u1f5d\u1f5f\u1f61\u1f68\u1f70\u1f80\u1f88\u1f90\u1f98\u1fa0\u1fa8\u1fb0\u1fb6\u1fb8\u1fbc\u1fbe\u1fc0\u1fc2\u1fc6\u1fc8\u1fcc\u1fce\u1fd0\u1fd6\u1fd8\u1fdd\u1fe0\u1fe8\u1fed\u1ff2\u1ff6\u1ff8\u1ffc\u1ffe\u2000\u200c\u2010\u2016\u2018\u201a\u201c\u201e\u2020\u2028\u202a\u202f\u2031\u2039\u203b\u203f\u2041\u2044\u2046\u2048\u206a\u2070\u2074\u207a\u207d\u207f\u2081\u208a\u208d\u20a0\u20d0\u20dd\u20e1\u20e3\u2100\u2102\u2104\u2107\u2109\u210b\u210e\u2110\u2113\u2115\u2117\u2119\u211e\u2124\u212b\u212e\u2130\u2132\u2134\u2136\u2139\u2153\u2160\u2190\u2195\u219a\u219c\u21a0\u21a2\u21a5\u21a8\u21ae\u21b0\u21ce\u21d0\u21d2\u21d6\u2200\u2300\u2308\u230c\u2320\u2322\u2329\u232b\u237d\u2400\u2440\u2460\u249c\u24ea\u2500\u25a0\u25b7\u25b9\u25c1\u25c3\u2600\u2619\u266f\u2671\u2701\u2706\u270c\u2729\u274d\u274f\u2756\u2758\u2761\u2776\u2794\u2798\u27b1\u2800\u2e80\u2e9b\u2f00\u2ff0\u3000\u3002\u3004\u3006\u3008\u3012\u3014\u301c\u301e\u3020\u3022\u302a\u3030\u3032\u3036\u3038\u303e\u3041\u3099\u309b\u309d\u30a1\u30fb\u30fd\u3105\u3131\u3190\u3192\u3196\u31a0\u3200\u3220\u322a\u3260\u327f\u3281\u328a\u32c0\u32d0\u3300\u337b\u33e0\u3400\u4e00\ua000\ua490\ua4a4\ua4b5\ua4c2\ua4c6\uac00\ud800\ue000\uf900\ufb00\ufb13\ufb1d\ufb20\ufb29\ufb2b\ufb38\ufb3e\ufb40\ufb43\ufb46\ufbd3\ufd3e\ufd50\ufd92\ufdf0\ufe20\ufe30\ufe32\ufe34\ufe36\ufe49\ufe4d\ufe50\ufe54\ufe58\ufe5a\ufe5f\ufe62\ufe65\ufe68\ufe6b\ufe70\ufe74\ufe76\ufeff\uff01\uff04\uff06\uff08\uff0a\uff0d\uff0f\uff11\uff1a\uff1c\uff1f\uff21\uff3b\uff3d\uff3f\uff41\uff5b\uff5d\uff61\uff63\uff65\uff67\uff70\uff72\uff9e\uffa0\uffc2\uffca\uffd2\uffda\uffe0\uffe2\uffe4\uffe6\uffe8\uffea\uffed\ufff9\ufffc";

    private static final char[] typeValues = "\u001f\u000f!\u180c#\u0018%\u181a'\u0018)\u1615,\u1918.\u14180\u18099\t;\u0018>\u0019@\u0018Z\u0001\\\u1518^\u161b`\u171bz\u0002|\u1519~\u1619\u009f\u000f\u00a1\u180c\u00a5\u001a\u00a7\u001c\u00a9\u1c1b\u00ab\u1d02\u00ad\u1419\u00b0\u1b1c\u00b2\u190b\u00b4\u0b1b\u00b6\u021c\u00b8\u181b\u00ba\u0b02\u00bc\u1e0b\u00be\u000b\u00c0\u1801\u00d6\u0001\u00d8\u1901\u00de\u0001\u00f6\u0002\u00f8\u1902\u00ff\u0002\u0137\u0201\u0148\u0102\u0178\u0201\u017e\u0102\u0180\u0002\u0182\u0001\u0186\u0201\u0189\u0102\u018b\u0001\u018d\u0002\u0191\u0001\u0193\u0102\u0196\u0201\u0198\u0001\u019b\u0002\u019d\u0001\u019f\u0102\u01a6\u0201\u01aa\u0102\u01ae\u0201\u01b1\u0102\u01b3\u0001\u01b7\u0102\u01b9\u0201\u01bb\u0502\u01bd\u0201\u01bf\u0002\u01c3\u0005\u01c5\u0301\u01c7\u0102\u01c9\u0203\u01cb\u0301\u01dc\u0102\u01ef\u0201\u01f1\u0102\u01f3\u0203\u01f6\u0201\u01f8\u0001\u021f\u0201\u0233\u0201\u02ad\u0002\u02b8\u0004\u02ba\u001b\u02c1\u0004\u02cf\u001b\u02d1\u0004\u02df\u001b\u02e4\u0004\u02ed\u001b\u02ee\u0004\u034e\u0006\u0362\u0006\u0375\u001b\u037a\u0004\u037e\u0018\u0385\u001b\u0388\u1801\u038a\u0001\u038c\u0001\u038f\u0001\u0391\u0102\u03a1\u0001\u03ab\u0001\u03ce\u0002\u03d1\u0002\u03d4\u0001\u03d7\u0002\u03ef\u0201\u03f3\u0002\u042f\u0001\u045f\u0002\u0481\u0201\u0483\u061c\u0486\u0006\u0489\u0007\u04c0\u0201\u04c4\u0102\u04c8\u0102\u04cc\u0102\u04f5\u0201\u04f9\u0201\u0556\u0001\u055a\u0418\u055f\u0018\u0587\u0002\u058a\u1814\u05a1\u0006\u05b9\u0006\u05bd\u0006\u05c1\u0618\u05c4\u1806\u05ea\u0005\u05f2\u0005\u05f4\u0018\u060c\u0018\u061b\u1800\u061f\u1800\u063a\u0005\u0641\u0504\u064a\u0005\u0655\u0006\u0669\t\u066d\u0018\u0671\u0506\u06d3\u0005\u06d5\u0518\u06dc\u0006\u06de\u0007\u06e4\u0006\u06e6\u0004\u06e8\u0006\u06ea\u1c06\u06ed\u0006\u06f9\t\u06fc\u0005\u06fe\u001c\u070d\u0018\u0710\u1005\u0712\u0605\u072c\u0005\u074a\u0006\u07a5\u0005\u07b0\u0006\u0902\u0006\u0903\u0800\u0939\u0005\u093d\u0506\u0940\b\u0948\u0006\u094c\b\u094d\u0600\u0951\u0605\u0954\u0006\u0961\u0005\u0963\u0006\u0965\u0018\u096f\t\u0970\u0018\u0982\u0608\u0983\u0800\u098c\u0005\u0990\u0005\u09a8\u0005\u09b0\u0005\u09b2\u0005\u09b9\u0005\u09bc\u0006\u09c0\b\u09c4\u0006\u09c8\b\u09cc\b\u09cd\u0600\u09d7\u0800\u09dd\u0005\u09e1\u0005\u09e3\u0006\u09ef\t\u09f1\u0005\u09f3\u001a\u09f9\u000b\u09fa\u001c\u0a02\u0006\u0a0a\u0005\u0a10\u0005\u0a28\u0005\u0a30\u0005\u0a33\u0005\u0a36\u0005\u0a39\u0005\u0a3c\u0006\u0a40\b\u0a42\u0006\u0a48\u0006\u0a4d\u0006\u0a5c\u0005\u0a5e\u0005\u0a6f\t\u0a71\u0006\u0a74\u0005\u0a82\u0006\u0a83\u0800\u0a8b\u0005\u0a8d\u0500\u0a91\u0005\u0aa8\u0005\u0ab0\u0005\u0ab3\u0005\u0ab9\u0005\u0abd\u0506\u0ac0\b\u0ac5\u0006\u0ac8\u0006\u0ac9\u0800\u0acc\b\u0acd\u0600\u0ad0\u0005\u0ae0\u0005\u0aef\t\u0b02\u0608\u0b03\u0800\u0b0c\u0005\u0b10\u0005\u0b28\u0005\u0b30\u0005\u0b33\u0005\u0b39\u0005\u0b3d\u0506\u0b41\u0608\u0b43\u0006\u0b48\b\u0b4c\b\u0b4d\u0600\u0b57\u0806\u0b5d\u0005\u0b61\u0005\u0b6f\t\u0b70\u001c\u0b83\u0806\u0b8a\u0005\u0b90\u0005\u0b95\u0005\u0b9a\u0005\u0b9c\u0005\u0b9f\u0005\u0ba4\u0005\u0baa\u0005\u0bb5\u0005\u0bb9\u0005\u0bbf\b\u0bc1\u0806\u0bc2\b\u0bc8\b\u0bcc\b\u0bcd\u0600\u0bd7\u0800\u0bef\t\u0bf2\u000b\u0c03\b\u0c0c\u0005\u0c10\u0005\u0c28\u0005\u0c33\u0005\u0c39\u0005\u0c40\u0006\u0c44\b\u0c48\u0006\u0c4d\u0006\u0c56\u0006\u0c61\u0005\u0c6f\t\u0c83\b\u0c8c\u0005\u0c90\u0005\u0ca8\u0005\u0cb3\u0005\u0cb9\u0005\u0cc0\u0608\u0cc4\b\u0cc7\u0806\u0cc8\b\u0ccb\b\u0ccd\u0006\u0cd6\b\u0cde\u0005\u0ce1\u0005\u0cef\t\u0d03\b\u0d0c\u0005\u0d10\u0005\u0d28\u0005\u0d39\u0005\u0d40\b\u0d43\u0006\u0d48\b\u0d4c\b\u0d4d\u0600\u0d57\u0800\u0d61\u0005\u0d6f\t\u0d83\b\u0d96\u0005\u0db1\u0005\u0dbb\u0005\u0dbd\u0500\u0dc6\u0005\u0dca\u0006\u0dd1\b\u0dd4\u0006\u0dd6\u0006\u0ddf\b\u0df3\b\u0df4\u0018\u0e30\u0005\u0e32\u0605\u0e34\u0506\u0e3a\u0006\u0e40\u1a05\u0e45\u0005\u0e47\u0604\u0e4e\u0006\u0e50\u1809\u0e59\t\u0e5b\u0018\u0e82\u0005\u0e84\u0005\u0e88\u0005\u0e8a\u0005\u0e8d\u0500\u0e97\u0005\u0e9f\u0005\u0ea3\u0005\u0ea5\u0500\u0ea7\u0500\u0eab\u0005\u0eb0\u0005\u0eb2\u0605\u0eb4\u0506\u0eb9\u0006\u0ebc\u0006\u0ebd\u0500\u0ec4\u0005\u0ec6\u0004\u0ecd\u0006\u0ed9\t\u0edd\u0005\u0f01\u1c05\u0f03\u001c\u0f12\u0018\u0f17\u001c\u0f19\u0006\u0f1f\u001c\u0f29\t\u0f33\u000b\u0f39\u061c\u0f3d\u1615\u0f3f\b\u0f47\u0005\u0f6a\u0005\u0f7e\u0006\u0f80\u0806\u0f84\u0006\u0f86\u1806\u0f88\u0605\u0f8b\u0005\u0f97\u0006\u0fbc\u0006\u0fc5\u001c\u0fc7\u1c06\u0fcc\u001c\u0fcf\u1c00\u1021\u0005\u1027\u0005\u102a\u0005\u102d\u0608\u1030\u0006\u1032\u0806\u1037\u0006\u1039\u0608\u1049\t\u104f\u0018\u1055\u0005\u1057\b\u1059\u0006\u10c5\u0001\u10f6\u0005\u10fb\u1800\u1159\u0005\u11a2\u0005\u11f9\u0005\u1206\u0005\u1246\u0005\u1248\u0005\u124d\u0005\u1256\u0005\u1258\u0005\u125d\u0005\u1286\u0005\u1288\u0005\u128d\u0005\u12ae\u0005\u12b0\u0005\u12b5\u0005\u12be\u0005\u12c0\u0005\u12c5\u0005\u12ce\u0005\u12d6\u0005\u12ee\u0005\u130e\u0005\u1310\u0005\u1315\u0005\u131e\u0005\u1346\u0005\u135a\u0005\u1368\u0018\u1371\t\u137c\u000b\u13f4\u0005\u166c\u0005\u166e\u0018\u1676\u0005\u1681\u050c\u169a\u0005\u169c\u1516\u16ea\u0005\u16ed\u0018\u16f0\u000b\u17b3\u0005\u17b6\b\u17bd\u0006\u17c5\b\u17c7\u0806\u17c9\u0608\u17d3\u0006\u17da\u0018\u17dc\u1a18\u17e9\t\u1805\u0018\u1807\u1814\u180a\u0018\u180e\u0010\u1819\t\u1842\u0005\u1844\u0405\u1877\u0005\u18a8\u0005\u18a9\u0600\u1e95\u0201\u1e9b\u0002\u1ef9\u0201\u1f07\u0002\u1f0f\u0001\u1f15\u0002\u1f1d\u0001\u1f27\u0002\u1f2f\u0001\u1f37\u0002\u1f3f\u0001\u1f45\u0002\u1f4d\u0001\u1f57\u0002\u1f59\u0100\u1f5b\u0100\u1f5d\u0100\u1f60\u0102\u1f67\u0002\u1f6f\u0001\u1f7d\u0002\u1f87\u0002\u1f8f\u0003\u1f97\u0002\u1f9f\u0003\u1fa7\u0002\u1faf\u0003\u1fb4\u0002\u1fb7\u0002\u1fbb\u0001\u1fbd\u1b03\u1fbf\u1b02\u1fc1\u001b\u1fc4\u0002\u1fc7\u0002\u1fcb\u0001\u1fcd\u1b03\u1fcf\u001b\u1fd3\u0002\u1fd7\u0002\u1fdb\u0001\u1fdf\u001b\u1fe7\u0002\u1fec\u0001\u1fef\u001b\u1ff4\u0002\u1ff7\u0002\u1ffb\u0001\u1ffd\u1b03\u1ffe\u001b\u200b\f\u200f\u0010\u2015\u0014\u2017\u0018\u2019\u1e1d\u201b\u1d15\u201d\u1e1d\u201f\u1d15\u2027\u0018\u2029\u0e0d\u202e\u0010\u2030\u0c18\u2038\u0018\u203a\u1d1e\u203e\u0018\u2040\u0017\u2043\u0018\u2045\u1519\u2046\u0016\u204d\u0018\u206f\u0010\u2070\u000b\u2079\u000b\u207c\u0019\u207e\u1516\u2080\u020b\u2089\u000b\u208c\u0019\u208e\u1516\u20af\u001a\u20dc\u0006\u20e0\u0007\u20e2\u0607\u20e3\u0700\u2101\u001c\u2103\u1c01\u2106\u001c\u2108\u011c\u210a\u1c02\u210d\u0001\u210f\u0002\u2112\u0001\u2114\u021c\u2116\u011c\u2118\u001c\u211d\u0001\u2123\u001c\u212a\u1c01\u212d\u0001\u212f\u021c\u2131\u0001\u2133\u011c\u2135\u0502\u2138\u0005\u213a\u021c\u215f\u000b\u2183\n\u2194\u0019\u2199\u001c\u219b\u0019\u219f\u001c\u21a1\u1c19\u21a4\u191c\u21a7\u1c19\u21ad\u001c\u21af\u1c19\u21cd\u001c\u21cf\u0019\u21d1\u001c\u21d5\u1c19\u21f3\u001c\u22f1\u0019\u2307\u001c\u230b\u0019\u231f\u001c\u2321\u0019\u2328\u001c\u232a\u1516\u237b\u001c\u239a\u001c\u2426\u001c\u244a\u001c\u249b\u000b\u24e9\u001c\u24ea\u000b\u2595\u001c\u25b6\u001c\u25b8\u191c\u25c0\u001c\u25c2\u191c\u25f7\u001c\u2613\u001c\u266e\u001c\u2670\u191c\u2671\u1c00\u2704\u001c\u2709\u001c\u2727\u001c\u274b\u001c\u274d\u1c00\u2752\u001c\u2756\u001c\u275e\u001c\u2767\u001c\u2793\u000b\u2794\u001c\u27af\u001c\u27be\u001c\u28ff\u001c\u2e99\u001c\u2ef3\u001c\u2fd5\u001c\u2ffb\u001c\u3001\u180c\u3003\u0018\u3005\u041c\u3007\u0a05\u3011\u1615\u3013\u001c\u301b\u1615\u301d\u1514\u301f\u0016\u3021\u0a1c\u3029\n\u302f\u0006\u3031\u0414\u3035\u0004\u3037\u001c\u303a\n\u303f\u001c\u3094\u0005\u309a\u0006\u309c\u001b\u309e\u0004\u30fa\u0005\u30fc\u1704\u30fe\u0004\u312c\u0005\u318e\u0005\u3191\u001c\u3195\u000b\u319f\u001c\u31b7\u0005\u321c\u001c\u3229\u000b\u3243\u001c\u327b\u001c\u3280\u1c0b\u3289\u000b\u32b0\u001c\u32cb\u001c\u32fe\u001c\u3376\u001c\u33dd\u001c\u33fe\u001c\u4db5\u0005\u9fa5\u0005\ua48c\u0005\ua4a1\u001c\ua4b3\u001c\ua4c0\u001c\ua4c4\u001c\ua4c6\u001c\ud7a3\u0005\udfff\u0013\uf8ff\u0012\ufa2d\u0005\ufb06\u0002\ufb17\u0002\ufb1f\u0506\ufb28\u0005\ufb2a\u1905\ufb36\u0005\ufb3c\u0005\ufb3e\u0005\ufb41\u0005\ufb44\u0005\ufbb1\u0005\ufd3d\u0005\ufd3f\u1615\ufd8f\u0005\ufdc7\u0005\ufdfb\u0005\ufe23\u0006\ufe31\u1418\ufe33\u1714\ufe35\u1517\ufe44\u1516\ufe4c\u0018\ufe4f\u0017\ufe52\u0018\ufe57\u0018\ufe59\u1514\ufe5e\u1516\ufe61\u0018\ufe64\u1419\ufe66\u0019\ufe6a\u1a18\ufe6b\u1800\ufe72\u0005\ufe74\u0005\ufefc\u0005\ufeff\u1000\uff03\u0018\uff05\u181a\uff07\u0018\uff09\u1615\uff0c\u1918\uff0e\u1418\uff10\u1809\uff19\t\uff1b\u0018\uff1e\u0019\uff20\u0018\uff3a\u0001\uff3c\u1518\uff3e\u161b\uff40\u171b\uff5a\u0002\uff5c\u1519\uff5e\u1619\uff62\u1815\uff64\u1618\uff66\u1705\uff6f\u0005\uff71\u0504\uff9d\u0005\uff9f\u0004\uffbe\u0005\uffc7\u0005\uffcf\u0005\uffd7\u0005\uffdc\u0005\uffe1\u001a\uffe3\u1b19\uffe5\u1a1c\uffe6\u001a\uffe9\u191c\uffec\u0019\uffee\u001c\ufffb\u0010\ufffd\u001c"
            .getValue();

    private static final int[] typeValuesCache = {
    	15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 
    	15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 
    	12, 24, 24, 24, 26, 24, 24, 24, 21, 22, 24, 25, 24, 20, 24, 24, 
    	9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 24, 24, 25, 25, 25, 24, 
    	24, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 21, 24, 22, 27, 23, 
    	27, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 21, 25, 22, 25, 15, 
    	15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 
    	15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 
    	12, 24, 26, 26, 26, 26, 28, 28, 27, 28, 2, 29, 25, 16, 28, 27, 
    	28, 25, 11, 11, 27, 2, 28, 24, 27, 11, 2, 30, 11, 11, 11, 24, 
    	1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    	1, 1, 1, 1, 1, 1, 1, 25, 1, 1, 1, 1, 1, 1, 1, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 25, 2, 2, 2, 2, 2, 2, 2, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 
    	2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 
    	2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 
    	1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 1, 2, 1, 
    	1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 
    	2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 2, 5, 1, 2, 2, 2, 
    	5, 5, 5, 5, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 2, 1, 
    	2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	2, 1, 3, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
    	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
    	4, 4, 27, 27, 27, 27, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
    	4, 4, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
    	4, 4, 4, 4, 4, 27, 27, 27, 27, 27, 27, 27, 27, 27, 4, 27, 
    	27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 6, 6, 6, 
    	6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
    	0, 0, 0, 0, 27, 27, 0, 0, 0, 0, 4, 0, 0, 0, 24, 0, 
    	0, 0, 0, 0, 27, 27, 1, 24, 1, 1, 1, 0, 1, 0, 1, 1, 
    	2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
    	1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
    	2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 
    	2, 2, 1, 1, 1, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 
    	1, 2, 1, 2, 1, 2, 1, 2 };

    
    // Unicode 3.0.1 (same as Unicode 3.0.0)
    private static final String uppercaseKeys = "a\u00b5\u00e0\u00f8\u00ff\u0101\u0131\u0133\u013a\u014b\u017a\u017f\u0183\u0188\u018c\u0192\u0195\u0199\u01a1\u01a8\u01ad\u01b0\u01b4\u01b9\u01bd\u01bf\u01c5\u01c6\u01c8\u01c9\u01cb\u01cc\u01ce\u01dd\u01df\u01f2\u01f3\u01f5\u01f9\u0223\u0253\u0254\u0256\u0259\u025b\u0260\u0263\u0268\u0269\u026f\u0272\u0275\u0280\u0283\u0288\u028a\u0292\u0345\u03ac\u03ad\u03b1\u03c2\u03c3\u03cc\u03cd\u03d0\u03d1\u03d5\u03d6\u03db\u03f0\u03f1\u03f2\u0430\u0450\u0461\u048d\u04c2\u04c8\u04cc\u04d1\u04f9\u0561\u1e01\u1e9b\u1ea1\u1f00\u1f10\u1f20\u1f30\u1f40\u1f51\u1f60\u1f70\u1f72\u1f76\u1f78\u1f7a\u1f7c\u1f80\u1f90\u1fa0\u1fb0\u1fb3\u1fbe\u1fc3\u1fd0\u1fe0\u1fe5\u1ff3\u2170\u24d0\uff41";

    private static final char[] uppercaseValues = "z\uffe0\u00b5\u02e7\u00f6\uffe0\u00fe\uffe0\u00ffy\u812f\uffff\u0131\uff18\u8137\uffff\u8148\uffff\u8177\uffff\u817e\uffff\u017f\ufed4\u8185\uffff\u0188\uffff\u018c\uffff\u0192\uffff\u0195a\u0199\uffff\u81a5\uffff\u01a8\uffff\u01ad\uffff\u01b0\uffff\u81b6\uffff\u01b9\uffff\u01bd\uffff\u01bf8\u01c5\uffff\u01c6\ufffe\u01c8\uffff\u01c9\ufffe\u01cb\uffff\u01cc\ufffe\u81dc\uffff\u01dd\uffb1\u81ef\uffff\u01f2\uffff\u01f3\ufffe\u01f5\uffff\u821f\uffff\u8233\uffff\u0253\uff2e\u0254\uff32\u0257\uff33\u0259\uff36\u025b\uff35\u0260\uff33\u0263\uff31\u0268\uff2f\u0269\uff2d\u026f\uff2d\u0272\uff2b\u0275\uff2a\u0280\uff26\u0283\uff26\u0288\uff26\u028b\uff27\u0292\uff25\u0345T\u03ac\uffda\u03af\uffdb\u03c1\uffe0\u03c2\uffe1\u03cb\uffe0\u03cc\uffc0\u03ce\uffc1\u03d0\uffc2\u03d1\uffc7\u03d5\uffd1\u03d6\uffca\u83ef\uffff\u03f0\uffaa\u03f1\uffb0\u03f2\uffb1\u044f\uffe0\u045f\uffb0\u8481\uffff\u84bf\uffff\u84c4\uffff\u04c8\uffff\u04cc\uffff\u84f5\uffff\u04f9\uffff\u0586\uffd0\u9e95\uffff\u1e9b\uffc5\u9ef9\uffff\u1f07\b\u1f15\b\u1f27\b\u1f37\b\u1f45\b\u9f57\b\u1f67\b\u1f71J\u1f75V\u1f77d\u1f79\u0080\u1f7bp\u1f7d~\u1f87\b\u1f97\b\u1fa7\b\u1fb1\b\u1fb3\t\u1fbe\ue3db\u1fc3\t\u1fd1\b\u1fe1\b\u1fe5\u0007\u1ff3\t\u217f\ufff0\u24e9\uffe6\uff5a\uffe0"
            .getValue();

    private static final int[] uppercaseValuesCache = {
    	924, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 
    	197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 
    	213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 192, 193, 194, 195, 196, 
    	197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 
    	213, 214, 247, 216, 217, 218, 219, 220, 221, 222, 376, 256, 256, 258, 258, 260, 
    	260, 262, 262, 264, 264, 266, 266, 268, 268, 270, 270, 272, 272, 274, 274, 276, 
    	276, 278, 278, 280, 280, 282, 282, 284, 284, 286, 286, 288, 288, 290, 290, 292, 
    	292, 294, 294, 296, 296, 298, 298, 300, 300, 302, 302, 304, 73, 306, 306, 308, 
    	308, 310, 310, 312, 313, 313, 315, 315, 317, 317, 319, 319, 321, 321, 323, 323, 
    	325, 325, 327, 327, 329, 330, 330, 332, 332, 334, 334, 336, 336, 338, 338, 340, 
    	340, 342, 342, 344, 344, 346, 346, 348, 348, 350, 350, 352, 352, 354, 354, 356, 
    	356, 358, 358, 360, 360, 362, 362, 364, 364, 366, 366, 368, 368, 370, 370, 372, 
    	372, 374, 374, 376, 377, 377, 379, 379, 381, 381, 83, 384, 385, 386, 386, 388, 
    	388, 390, 391, 391, 393, 394, 395, 395, 397, 398, 399, 400, 401, 401, 403, 404, 
    	502, 406, 407, 408, 408, 410, 411, 412, 413, 544, 415, 416, 416, 418, 418, 420, 
    	420, 422, 423, 423, 425, 426, 427, 428, 428, 430, 431, 431, 433, 434, 435, 435, 
    	437, 437, 439, 440, 440, 442, 443, 444, 444, 446, 503, 448, 449, 450, 451, 452, 
    	452, 452, 455, 455, 455, 458, 458, 458, 461, 461, 463, 463, 465, 465, 467, 467, 
    	469, 469, 471, 471, 473, 473, 475, 475, 398, 478, 478, 480, 480, 482, 482, 484, 
    	484, 486, 486, 488, 488, 490, 490, 492, 492, 494, 494, 496, 497, 497, 497, 500, 
    	500, 502, 503, 504, 504, 506, 506, 508, 508, 510, 510, 512, 512, 514, 514, 516, 
    	516, 518, 518, 520, 520, 522, 522, 524, 524, 526, 526, 528, 528, 530, 530, 532, 
    	532, 534, 534, 536, 536, 538, 538, 540, 540, 542, 542, 544, 545, 546, 546, 548, 
    	548, 550, 550, 552, 552, 554, 554, 556, 556, 558, 558, 560, 560, 562, 562, 564, 
    	565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 
    	581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 385, 390, 
    	597, 393, 394, 600, 399, 602, 400, 604, 605, 606, 607, 403, 609, 610, 404, 612, 
    	613, 614, 615, 407, 406, 618, 619, 620, 621, 622, 412, 624, 625, 413, 627, 628, 
    	415, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 422, 641, 642, 425, 644, 
    	645, 646, 647, 430, 649, 433, 434, 652, 653, 654, 655, 656, 657, 439, 659, 660, 
    	661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 
    	677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 
    	693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 
    	709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 
    	725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 
    	741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 
    	757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 
    	773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 
    	789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 
    	805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 
    	821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 
    	921, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 
    	853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 
    	869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 
    	885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 
    	901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 
    	917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 
    	933, 934, 935, 936, 937, 938, 939, 902, 904, 905, 906, 944, 913, 914, 915, 916, 
    	917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 931, 931, 932, 
    	933, 934, 935, 936, 937, 938, 939, 908, 910, 911, 975, 914, 920, 978, 979, 980, 
    	934, 928, 983, 984, 984, 986, 986, 988, 988, 990, 990, 992, 992, 994, 994, 996, 
    	996, 998, 998} ;
    
    private static final String lowercaseKeys = "A\u00c0\u00d8\u0100\u0130\u0132\u0139\u014a\u0178\u0179\u0181\u0182\u0186\u0187\u0189\u018b\u018e\u018f\u0190\u0191\u0193\u0194\u0196\u0197\u0198\u019c\u019d\u019f\u01a0\u01a6\u01a7\u01a9\u01ac\u01ae\u01af\u01b1\u01b3\u01b7\u01b8\u01bc\u01c4\u01c5\u01c7\u01c8\u01ca\u01cb\u01de\u01f1\u01f2\u01f6\u01f7\u01f8\u0222\u0386\u0388\u038c\u038e\u0391\u03a3\u03da\u0400\u0410\u0460\u048c\u04c1\u04c7\u04cb\u04d0\u04f8\u0531\u1e00\u1ea0\u1f08\u1f18\u1f28\u1f38\u1f48\u1f59\u1f68\u1f88\u1f98\u1fa8\u1fb8\u1fba\u1fbc\u1fc8\u1fcc\u1fd8\u1fda\u1fe8\u1fea\u1fec\u1ff8\u1ffa\u1ffc\u2126\u212a\u212b\u2160\u24b6\uff21";

    private static final char[] lowercaseValues = "Z \u00d6 \u00de \u812e\u0001\u0130\uff39\u8136\u0001\u8147\u0001\u8176\u0001\u0178\uff87\u817d\u0001\u0181\u00d2\u8184\u0001\u0186\u00ce\u0187\u0001\u018a\u00cd\u018b\u0001\u018eO\u018f\u00ca\u0190\u00cb\u0191\u0001\u0193\u00cd\u0194\u00cf\u0196\u00d3\u0197\u00d1\u0198\u0001\u019c\u00d3\u019d\u00d5\u019f\u00d6\u81a4\u0001\u01a6\u00da\u01a7\u0001\u01a9\u00da\u01ac\u0001\u01ae\u00da\u01af\u0001\u01b2\u00d9\u81b5\u0001\u01b7\u00db\u01b8\u0001\u01bc\u0001\u01c4\u0002\u01c5\u0001\u01c7\u0002\u01c8\u0001\u01ca\u0002\u81db\u0001\u81ee\u0001\u01f1\u0002\u81f4\u0001\u01f6\uff9f\u01f7\uffc8\u821e\u0001\u8232\u0001\u0386&\u038a%\u038c@\u038f?\u03a1 \u03ab \u83ee\u0001\u040fP\u042f \u8480\u0001\u84be\u0001\u84c3\u0001\u04c7\u0001\u04cb\u0001\u84f4\u0001\u04f8\u0001\u05560\u9e94\u0001\u9ef8\u0001\u1f0f\ufff8\u1f1d\ufff8\u1f2f\ufff8\u1f3f\ufff8\u1f4d\ufff8\u9f5f\ufff8\u1f6f\ufff8\u1f8f\ufff8\u1f9f\ufff8\u1faf\ufff8\u1fb9\ufff8\u1fbb\uffb6\u1fbc\ufff7\u1fcb\uffaa\u1fcc\ufff7\u1fd9\ufff8\u1fdb\uff9c\u1fe9\ufff8\u1feb\uff90\u1fec\ufff9\u1ff9\uff80\u1ffb\uff82\u1ffc\ufff7\u2126\ue2a3\u212a\udf41\u212b\udfba\u216f\u0010\u24cf\u001a\uff3a "
            .getValue();

    private static final int[] lowercaseValuesCache = {
    	224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 
    	240, 241, 242, 243, 244, 245, 246, 215, 248, 249, 250, 251, 252, 253, 254, 223, 
    	224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 
    	240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 
    	257, 257, 259, 259, 261, 261, 263, 263, 265, 265, 267, 267, 269, 269, 271, 271, 
    	273, 273, 275, 275, 277, 277, 279, 279, 281, 281, 283, 283, 285, 285, 287, 287, 
    	289, 289, 291, 291, 293, 293, 295, 295, 297, 297, 299, 299, 301, 301, 303, 303, 
    	105, 305, 307, 307, 309, 309, 311, 311, 312, 314, 314, 316, 316, 318, 318, 320, 
    	320, 322, 322, 324, 324, 326, 326, 328, 328, 329, 331, 331, 333, 333, 335, 335, 
    	337, 337, 339, 339, 341, 341, 343, 343, 345, 345, 347, 347, 349, 349, 351, 351, 
    	353, 353, 355, 355, 357, 357, 359, 359, 361, 361, 363, 363, 365, 365, 367, 367, 
    	369, 369, 371, 371, 373, 373, 375, 375, 255, 378, 378, 380, 380, 382, 382, 383, 
    	384, 595, 387, 387, 389, 389, 596, 392, 392, 598, 599, 396, 396, 397, 477, 601, 
    	603, 402, 402, 608, 611, 405, 617, 616, 409, 409, 410, 411, 623, 626, 414, 629, 
    	417, 417, 419, 419, 421, 421, 640, 424, 424, 643, 426, 427, 429, 429, 648, 432, 
    	432, 650, 651, 436, 436, 438, 438, 658, 441, 441, 442, 443, 445, 445, 446, 447, 
    	448, 449, 450, 451, 454, 454, 454, 457, 457, 457, 460, 460, 460, 462, 462, 464, 
    	464, 466, 466, 468, 468, 470, 470, 472, 472, 474, 474, 476, 476, 477, 479, 479, 
    	481, 481, 483, 483, 485, 485, 487, 487, 489, 489, 491, 491, 493, 493, 495, 495, 
    	496, 499, 499, 499, 501, 501, 405, 447, 505, 505, 507, 507, 509, 509, 511, 511, 
    	513, 513, 515, 515, 517, 517, 519, 519, 521, 521, 523, 523, 525, 525, 527, 527, 
    	529, 529, 531, 531, 533, 533, 535, 535, 537, 537, 539, 539, 541, 541, 543, 543, 
    	414, 545, 547, 547, 549, 549, 551, 551, 553, 553, 555, 555, 557, 557, 559, 559, 
    	561, 561, 563, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 
    	576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 
    	592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 
    	608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 
    	624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 
    	640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 
    	656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 
    	672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 
    	688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 
    	704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 
    	720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 
    	736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 
    	752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 
    	768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 
    	784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 
    	800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 
    	816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 
    	832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 
    	848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 
    	864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 
    	880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 
    	896, 897, 898, 899, 900, 901, 940, 903, 941, 942, 943, 907, 972, 909, 973, 974, 
    	912, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 
    	960, 961, 930, 963, 964, 965, 966, 967, 968, 969, 970, 971, 940, 941, 942, 943, 
    	944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 
    	960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 
    	976, 977, 978, 979, 980, 981, 982, 983, 985, 985, 987, 987, 989, 989, 991, 991, 
    	993, 993, 995, 995, 997, 997, 999, 999};
    
    private static final String digitKeys = "0Aa\u0660\u06f0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be7\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1369\u17e0\u1810\uff10\uff21\uff41";

    private static final char[] digitValues = "90Z7zW\u0669\u0660\u06f9\u06f0\u096f\u0966\u09ef\u09e6\u0a6f\u0a66\u0aef\u0ae6\u0b6f\u0b66\u0bef\u0be6\u0c6f\u0c66\u0cef\u0ce6\u0d6f\u0d66\u0e59\u0e50\u0ed9\u0ed0\u0f29\u0f20\u1049\u1040\u1371\u1368\u17e9\u17e0\u1819\u1810\uff19\uff10\uff3a\uff17\uff5a\uff37"
            .getValue();

    private static final char[] typeTags = "\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0000\u0000\u0000\u0000\u0000\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0003\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0000\u0000\u0000\u0000\u0003\u0000\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0000\u0000\u0000\u0000\u0002"
            .getValue();
    
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

    private static final int ISJAVASTART = 1;

    private static final int ISJAVAPART = 2;

    // Unicode 3.0.1 (same as Unicode 3.0.0)
    private static final String titlecaseKeys = "\u01c4\u01c6\u01c7\u01c9\u01ca\u01cc\u01f1\u01f3";

    private static final char[] titlecaseValues = "\u01c5\u01c5\u01c8\u01c8\u01cb\u01cb\u01f2\u01f2"
            .getValue();

    // Unicode 3.0.0 (NOT the same as Unicode 3.0.1)
    private static final String numericKeys = "0Aa\u00b2\u00b9\u00bc\u0660\u06f0\u0966\u09e6\u09f4\u09f9\u0a66\u0ae6\u0b66\u0be7\u0bf1\u0bf2\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1369\u1373\u1374\u1375\u1376\u1377\u1378\u1379\u137a\u137b\u137c\u16ee\u17e0\u1810\u2070\u2074\u2080\u2153\u215f\u2160\u216c\u216d\u216e\u216f\u2170\u217c\u217d\u217e\u217f\u2180\u2181\u2182\u2460\u2474\u2488\u24ea\u2776\u2780\u278a\u3007\u3021\u3038\u3039\u303a\u3280\uff10\uff21\uff41";

    private static final char[] numericValues = "90Z7zW\u00b3\u00b0\u00b9\u00b8\u00be\u0000\u0669\u0660\u06f9\u06f0\u096f\u0966\u09ef\u09e6\u09f7\u09f3\u09f9\u09e9\u0a6f\u0a66\u0aef\u0ae6\u0b6f\u0b66\u0bf0\u0be6\u0bf1\u0b8d\u0bf2\u080a\u0c6f\u0c66\u0cef\u0ce6\u0d6f\u0d66\u0e59\u0e50\u0ed9\u0ed0\u0f29\u0f20\u1049\u1040\u1372\u1368\u1373\u135f\u1374\u1356\u1375\u134d\u1376\u1344\u1377\u133b\u1378\u1332\u1379\u1329\u137a\u1320\u137b\u1317\u137c\uec6c\u16f0\u16dd\u17e9\u17e0\u1819\u1810\u2070\u2070\u2079\u2070\u2089\u2080\u215e\u0000\u215f\u215e\u216b\u215f\u216c\u213a\u216d\u2109\u216e\u1f7a\u216f\u1d87\u217b\u216f\u217c\u214a\u217d\u2119\u217e\u1f8a\u217f\u1d97\u2180\u1d98\u2181\u0df9\u2182\ufa72\u2473\u245f\u2487\u2473\u249b\u2487\u24ea\u24ea\u277f\u2775\u2789\u277f\u2793\u2789\u3007\u3007\u3029\u3020\u3038\u302e\u3039\u3025\u303a\u301c\u3289\u327f\uff19\uff10\uff3a\uff17\uff5a\uff37"
            .getValue();

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
        private static final UnicodeBlock[] BLOCKS = { BASIC_LATIN,
                LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A, LATIN_EXTENDED_B,
                IPA_EXTENSIONS, SPACING_MODIFIER_LETTERS,
                COMBINING_DIACRITICAL_MARKS, GREEK, CYRILLIC,
                CYRILLIC_SUPPLEMENTARY, ARMENIAN, HEBREW, ARABIC, SYRIAC,
                THAANA, DEVANAGARI, BENGALI, GURMUKHI, GUJARATI, ORIYA, TAMIL,
                TELUGU, KANNADA, MALAYALAM, SINHALA, THAI, LAO, TIBETAN,
                MYANMAR, GEORGIAN, HANGUL_JAMO, ETHIOPIC, CHEROKEE,
                UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, OGHAM, RUNIC, TAGALOG,
                HANUNOO, BUHID, TAGBANWA, KHMER, MONGOLIAN, LIMBU, TAI_LE,
                KHMER_SYMBOLS, PHONETIC_EXTENSIONS, LATIN_EXTENDED_ADDITIONAL,
                GREEK_EXTENDED, GENERAL_PUNCTUATION,
                SUPERSCRIPTS_AND_SUBSCRIPTS, CURRENCY_SYMBOLS,
                COMBINING_MARKS_FOR_SYMBOLS, LETTERLIKE_SYMBOLS, NUMBER_FORMS,
                ARROWS, MATHEMATICAL_OPERATORS, MISCELLANEOUS_TECHNICAL,
                CONTROL_PICTURES, OPTICAL_CHARACTER_RECOGNITION,
                ENCLOSED_ALPHANUMERICS, BOX_DRAWING, BLOCK_ELEMENTS,
                GEOMETRIC_SHAPES, MISCELLANEOUS_SYMBOLS, DINGBATS,
                MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, SUPPLEMENTAL_ARROWS_A,
                BRAILLE_PATTERNS, SUPPLEMENTAL_ARROWS_B,
                MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B,
                SUPPLEMENTAL_MATHEMATICAL_OPERATORS,
                MISCELLANEOUS_SYMBOLS_AND_ARROWS, CJK_RADICALS_SUPPLEMENT,
                KANGXI_RADICALS, IDEOGRAPHIC_DESCRIPTION_CHARACTERS,
                CJK_SYMBOLS_AND_PUNCTUATION, HIRAGANA, KATAKANA, BOPOMOFO,
                HANGUL_COMPATIBILITY_JAMO, KANBUN, BOPOMOFO_EXTENDED,
                KATAKANA_PHONETIC_EXTENSIONS, ENCLOSED_CJK_LETTERS_AND_MONTHS,
                CJK_COMPATIBILITY, CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
                YIJING_HEXAGRAM_SYMBOLS, CJK_UNIFIED_IDEOGRAPHS, YI_SYLLABLES,
                YI_RADICALS, HANGUL_SYLLABLES, HIGH_SURROGATES,
                HIGH_PRIVATE_USE_SURROGATES, LOW_SURROGATES, PRIVATE_USE_AREA,
                CJK_COMPATIBILITY_IDEOGRAPHS, ALPHABETIC_PRESENTATION_FORMS,
                ARABIC_PRESENTATION_FORMS_A, VARIATION_SELECTORS,
                COMBINING_HALF_MARKS, CJK_COMPATIBILITY_FORMS,
                SMALL_FORM_VARIANTS, ARABIC_PRESENTATION_FORMS_B,
                HALFWIDTH_AND_FULLWIDTH_FORMS, SPECIALS, LINEAR_B_SYLLABARY,
                LINEAR_B_IDEOGRAMS, AEGEAN_NUMBERS, OLD_ITALIC, GOTHIC,
                UGARITIC, DESERET, SHAVIAN, OSMANYA, CYPRIOT_SYLLABARY,
                BYZANTINE_MUSICAL_SYMBOLS, MUSICAL_SYMBOLS,
                TAI_XUAN_JING_SYMBOLS, MATHEMATICAL_ALPHANUMERIC_SYMBOLS,
                CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B,
                CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, TAGS,
                VARIATION_SELECTORS_SUPPLEMENT,
                SUPPLEMENTARY_PRIVATE_USE_AREA_A,
                SUPPLEMENTARY_PRIVATE_USE_AREA_B };

        /*
         * A SortedMap (String.CASE_INSENSITIVE_ORDER) with keys that represents
         * valid block names and values of the UnicodeBlock constant they map
         * to.
         */
        private static final SortedMap<String, UnicodeBlock> BLOCKS_BY_NAME = new TreeMap<String, UnicodeBlock>(String.CASE_INSENSITIVE_ORDER);
        
        static {
            BLOCKS_BY_NAME.put("SURROGATES_AREA", SURROGATES_AREA);
            BLOCKS_BY_NAME.put("Basic Latin", BASIC_LATIN);
            BLOCKS_BY_NAME.put("BasicLatin", BASIC_LATIN);
            BLOCKS_BY_NAME.put("BASIC_LATIN", BASIC_LATIN);
            BLOCKS_BY_NAME.put("Latin-1 Supplement", LATIN_1_SUPPLEMENT);
            BLOCKS_BY_NAME.put("Latin-1Supplement", LATIN_1_SUPPLEMENT);
            BLOCKS_BY_NAME.put("LATIN_1_SUPPLEMENT", LATIN_1_SUPPLEMENT);
            BLOCKS_BY_NAME.put("Latin Extended-A", LATIN_EXTENDED_A);
            BLOCKS_BY_NAME.put("LatinExtended-A", LATIN_EXTENDED_A);
            BLOCKS_BY_NAME.put("LATIN_EXTENDED_A", LATIN_EXTENDED_A);
            BLOCKS_BY_NAME.put("Latin Extended-B", LATIN_EXTENDED_B);
            BLOCKS_BY_NAME.put("LatinExtended-B", LATIN_EXTENDED_B);
            BLOCKS_BY_NAME.put("LATIN_EXTENDED_B", LATIN_EXTENDED_B);
            BLOCKS_BY_NAME.put("IPA Extensions", IPA_EXTENSIONS);
            BLOCKS_BY_NAME.put("IPAExtensions", IPA_EXTENSIONS);
            BLOCKS_BY_NAME.put("IPA_EXTENSIONS", IPA_EXTENSIONS);
            BLOCKS_BY_NAME.put("Spacing Modifier Letters", SPACING_MODIFIER_LETTERS);
            BLOCKS_BY_NAME.put("SpacingModifierLetters", SPACING_MODIFIER_LETTERS);
            BLOCKS_BY_NAME.put("SPACING_MODIFIER_LETTERS", SPACING_MODIFIER_LETTERS);
            BLOCKS_BY_NAME.put("Combining Diacritical Marks", COMBINING_DIACRITICAL_MARKS);
            BLOCKS_BY_NAME.put("CombiningDiacriticalMarks", COMBINING_DIACRITICAL_MARKS);
            BLOCKS_BY_NAME.put("COMBINING_DIACRITICAL_MARKS", COMBINING_DIACRITICAL_MARKS);
            BLOCKS_BY_NAME.put("Greek and Coptic", GREEK);
            BLOCKS_BY_NAME.put("GreekandCoptic", GREEK);
            BLOCKS_BY_NAME.put("GREEK", GREEK);
            BLOCKS_BY_NAME.put("Greek", GREEK);
            BLOCKS_BY_NAME.put("Greek", GREEK);
            BLOCKS_BY_NAME.put("Cyrillic", CYRILLIC);
            BLOCKS_BY_NAME.put("Cyrillic Supplement", CYRILLIC_SUPPLEMENTARY);
            BLOCKS_BY_NAME.put("CyrillicSupplement", CYRILLIC_SUPPLEMENTARY);
            BLOCKS_BY_NAME.put("CYRILLIC_SUPPLEMENTARY", CYRILLIC_SUPPLEMENTARY);
            BLOCKS_BY_NAME.put("Cyrillic Supplementary", CYRILLIC_SUPPLEMENTARY);
            BLOCKS_BY_NAME.put("CyrillicSupplementary", CYRILLIC_SUPPLEMENTARY);
            BLOCKS_BY_NAME.put("Armenian", ARMENIAN);
            BLOCKS_BY_NAME.put("Hebrew", HEBREW);
            BLOCKS_BY_NAME.put("Arabic", ARABIC);
            BLOCKS_BY_NAME.put("Syriac", SYRIAC);
            BLOCKS_BY_NAME.put("Thaana", THAANA);
            BLOCKS_BY_NAME.put("Devanagari", DEVANAGARI);
            BLOCKS_BY_NAME.put("Bengali", BENGALI);
            BLOCKS_BY_NAME.put("Gurmukhi", GURMUKHI);
            BLOCKS_BY_NAME.put("Gujarati", GUJARATI);
            BLOCKS_BY_NAME.put("Oriya", ORIYA);
            BLOCKS_BY_NAME.put("Tamil", TAMIL);
            BLOCKS_BY_NAME.put("Telugu", TELUGU);
            BLOCKS_BY_NAME.put("Kannada", KANNADA);
            BLOCKS_BY_NAME.put("Malayalam", MALAYALAM);
            BLOCKS_BY_NAME.put("Sinhala", SINHALA);
            BLOCKS_BY_NAME.put("Thai", THAI);
            BLOCKS_BY_NAME.put("Lao", LAO);
            BLOCKS_BY_NAME.put("Tibetan", TIBETAN);
            BLOCKS_BY_NAME.put("Myanmar", MYANMAR);
            BLOCKS_BY_NAME.put("Georgian", GEORGIAN);
            BLOCKS_BY_NAME.put("Hangul Jamo", HANGUL_JAMO);
            BLOCKS_BY_NAME.put("HangulJamo", HANGUL_JAMO);
            BLOCKS_BY_NAME.put("HANGUL_JAMO", HANGUL_JAMO);
            BLOCKS_BY_NAME.put("Ethiopic", ETHIOPIC);
            BLOCKS_BY_NAME.put("Cherokee", CHEROKEE);
            BLOCKS_BY_NAME.put("Unified Canadian Aboriginal Syllabics", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS);
            BLOCKS_BY_NAME.put("UnifiedCanadianAboriginalSyllabics", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS);
            BLOCKS_BY_NAME.put("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS", UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS);
            BLOCKS_BY_NAME.put("Ogham", OGHAM);
            BLOCKS_BY_NAME.put("Runic", RUNIC);
            BLOCKS_BY_NAME.put("Tagalog", TAGALOG);
            BLOCKS_BY_NAME.put("Hanunoo", HANUNOO);
            BLOCKS_BY_NAME.put("Buhid", BUHID);
            BLOCKS_BY_NAME.put("Tagbanwa", TAGBANWA);
            BLOCKS_BY_NAME.put("Khmer", KHMER);
            BLOCKS_BY_NAME.put("Mongolian", MONGOLIAN);
            BLOCKS_BY_NAME.put("Limbu", LIMBU);
            BLOCKS_BY_NAME.put("Tai Le", TAI_LE);
            BLOCKS_BY_NAME.put("TaiLe", TAI_LE);
            BLOCKS_BY_NAME.put("TAI_LE", TAI_LE);
            BLOCKS_BY_NAME.put("Khmer Symbols", KHMER_SYMBOLS);
            BLOCKS_BY_NAME.put("KhmerSymbols", KHMER_SYMBOLS);
            BLOCKS_BY_NAME.put("KHMER_SYMBOLS", KHMER_SYMBOLS);
            BLOCKS_BY_NAME.put("Phonetic Extensions", PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("PhoneticExtensions", PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("PHONETIC_EXTENSIONS", PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("Latin Extended Additional", LATIN_EXTENDED_ADDITIONAL);
            BLOCKS_BY_NAME.put("LatinExtendedAdditional", LATIN_EXTENDED_ADDITIONAL);
            BLOCKS_BY_NAME.put("LATIN_EXTENDED_ADDITIONAL", LATIN_EXTENDED_ADDITIONAL);
            BLOCKS_BY_NAME.put("Greek Extended", GREEK_EXTENDED);
            BLOCKS_BY_NAME.put("GreekExtended", GREEK_EXTENDED);
            BLOCKS_BY_NAME.put("GREEK_EXTENDED", GREEK_EXTENDED);
            BLOCKS_BY_NAME.put("General Punctuation", GENERAL_PUNCTUATION);
            BLOCKS_BY_NAME.put("GeneralPunctuation", GENERAL_PUNCTUATION);
            BLOCKS_BY_NAME.put("GENERAL_PUNCTUATION", GENERAL_PUNCTUATION);
            BLOCKS_BY_NAME.put("Superscripts and Subscripts", SUPERSCRIPTS_AND_SUBSCRIPTS);
            BLOCKS_BY_NAME.put("SuperscriptsandSubscripts", SUPERSCRIPTS_AND_SUBSCRIPTS);
            BLOCKS_BY_NAME.put("SUPERSCRIPTS_AND_SUBSCRIPTS", SUPERSCRIPTS_AND_SUBSCRIPTS);
            BLOCKS_BY_NAME.put("Currency Symbols", CURRENCY_SYMBOLS);
            BLOCKS_BY_NAME.put("CurrencySymbols", CURRENCY_SYMBOLS);
            BLOCKS_BY_NAME.put("CURRENCY_SYMBOLS", CURRENCY_SYMBOLS);
            BLOCKS_BY_NAME.put("Combining Diacritical Marks for Symbols", COMBINING_MARKS_FOR_SYMBOLS);
            BLOCKS_BY_NAME.put("CombiningDiacriticalMarksforSymbols", COMBINING_MARKS_FOR_SYMBOLS);
            BLOCKS_BY_NAME.put("COMBINING_MARKS_FOR_SYMBOLS", COMBINING_MARKS_FOR_SYMBOLS);
            BLOCKS_BY_NAME.put("Combining Marks for Symbols", COMBINING_MARKS_FOR_SYMBOLS);
            BLOCKS_BY_NAME.put("CombiningMarksforSymbols", COMBINING_MARKS_FOR_SYMBOLS);
            BLOCKS_BY_NAME.put("Letterlike Symbols", LETTERLIKE_SYMBOLS);
            BLOCKS_BY_NAME.put("LetterlikeSymbols", LETTERLIKE_SYMBOLS);
            BLOCKS_BY_NAME.put("LETTERLIKE_SYMBOLS", LETTERLIKE_SYMBOLS);
            BLOCKS_BY_NAME.put("Number Forms", NUMBER_FORMS);
            BLOCKS_BY_NAME.put("NumberForms", NUMBER_FORMS);
            BLOCKS_BY_NAME.put("NUMBER_FORMS", NUMBER_FORMS);
            BLOCKS_BY_NAME.put("Arrows", ARROWS);
            BLOCKS_BY_NAME.put("Mathematical Operators", MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("MathematicalOperators", MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("MATHEMATICAL_OPERATORS", MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("Miscellaneous Technical", MISCELLANEOUS_TECHNICAL);
            BLOCKS_BY_NAME.put("MiscellaneousTechnical", MISCELLANEOUS_TECHNICAL);
            BLOCKS_BY_NAME.put("MISCELLANEOUS_TECHNICAL", MISCELLANEOUS_TECHNICAL);
            BLOCKS_BY_NAME.put("Control Pictures", CONTROL_PICTURES);
            BLOCKS_BY_NAME.put("ControlPictures", CONTROL_PICTURES);
            BLOCKS_BY_NAME.put("CONTROL_PICTURES", CONTROL_PICTURES);
            BLOCKS_BY_NAME.put("Optical Character Recognition", OPTICAL_CHARACTER_RECOGNITION);
            BLOCKS_BY_NAME.put("OpticalCharacterRecognition", OPTICAL_CHARACTER_RECOGNITION);
            BLOCKS_BY_NAME.put("OPTICAL_CHARACTER_RECOGNITION", OPTICAL_CHARACTER_RECOGNITION);
            BLOCKS_BY_NAME.put("Enclosed Alphanumerics", ENCLOSED_ALPHANUMERICS);
            BLOCKS_BY_NAME.put("EnclosedAlphanumerics", ENCLOSED_ALPHANUMERICS);
            BLOCKS_BY_NAME.put("ENCLOSED_ALPHANUMERICS", ENCLOSED_ALPHANUMERICS);
            BLOCKS_BY_NAME.put("Box Drawing", BOX_DRAWING);
            BLOCKS_BY_NAME.put("BoxDrawing", BOX_DRAWING);
            BLOCKS_BY_NAME.put("BOX_DRAWING", BOX_DRAWING);
            BLOCKS_BY_NAME.put("Block Elements", BLOCK_ELEMENTS);
            BLOCKS_BY_NAME.put("BlockElements", BLOCK_ELEMENTS);
            BLOCKS_BY_NAME.put("BLOCK_ELEMENTS", BLOCK_ELEMENTS);
            BLOCKS_BY_NAME.put("Geometric Shapes", GEOMETRIC_SHAPES);
            BLOCKS_BY_NAME.put("GeometricShapes", GEOMETRIC_SHAPES);
            BLOCKS_BY_NAME.put("GEOMETRIC_SHAPES", GEOMETRIC_SHAPES);
            BLOCKS_BY_NAME.put("Miscellaneous Symbols", MISCELLANEOUS_SYMBOLS);
            BLOCKS_BY_NAME.put("MiscellaneousSymbols", MISCELLANEOUS_SYMBOLS);
            BLOCKS_BY_NAME.put("MISCELLANEOUS_SYMBOLS", MISCELLANEOUS_SYMBOLS);
            BLOCKS_BY_NAME.put("Dingbats", DINGBATS);
            BLOCKS_BY_NAME.put("Miscellaneous Mathematical Symbols-A", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A);
            BLOCKS_BY_NAME.put("MiscellaneousMathematicalSymbols-A", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A);
            BLOCKS_BY_NAME.put("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A);
            BLOCKS_BY_NAME.put("Supplemental Arrows-A", SUPPLEMENTAL_ARROWS_A);
            BLOCKS_BY_NAME.put("SupplementalArrows-A", SUPPLEMENTAL_ARROWS_A);
            BLOCKS_BY_NAME.put("SUPPLEMENTAL_ARROWS_A", SUPPLEMENTAL_ARROWS_A);
            BLOCKS_BY_NAME.put("Braille Patterns", BRAILLE_PATTERNS);
            BLOCKS_BY_NAME.put("BraillePatterns", BRAILLE_PATTERNS);
            BLOCKS_BY_NAME.put("BRAILLE_PATTERNS", BRAILLE_PATTERNS);
            BLOCKS_BY_NAME.put("Supplemental Arrows-B", SUPPLEMENTAL_ARROWS_B);
            BLOCKS_BY_NAME.put("SupplementalArrows-B", SUPPLEMENTAL_ARROWS_B);
            BLOCKS_BY_NAME.put("SUPPLEMENTAL_ARROWS_B", SUPPLEMENTAL_ARROWS_B);
            BLOCKS_BY_NAME.put("Miscellaneous Mathematical Symbols-B", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B);
            BLOCKS_BY_NAME.put("MiscellaneousMathematicalSymbols-B", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B);
            BLOCKS_BY_NAME.put("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B", MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B);
            BLOCKS_BY_NAME.put("Supplemental Mathematical Operators", SUPPLEMENTAL_MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("SupplementalMathematicalOperators", SUPPLEMENTAL_MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("SUPPLEMENTAL_MATHEMATICAL_OPERATORS", SUPPLEMENTAL_MATHEMATICAL_OPERATORS);
            BLOCKS_BY_NAME.put("Miscellaneous Symbols and Arrows", MISCELLANEOUS_SYMBOLS_AND_ARROWS);
            BLOCKS_BY_NAME.put("MiscellaneousSymbolsandArrows", MISCELLANEOUS_SYMBOLS_AND_ARROWS);
            BLOCKS_BY_NAME.put("MISCELLANEOUS_SYMBOLS_AND_ARROWS", MISCELLANEOUS_SYMBOLS_AND_ARROWS);
            BLOCKS_BY_NAME.put("CJK Radicals Supplement", CJK_RADICALS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("CJKRadicalsSupplement", CJK_RADICALS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("CJK_RADICALS_SUPPLEMENT", CJK_RADICALS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("Kangxi Radicals", KANGXI_RADICALS);
            BLOCKS_BY_NAME.put("KangxiRadicals", KANGXI_RADICALS);
            BLOCKS_BY_NAME.put("KANGXI_RADICALS", KANGXI_RADICALS);
            BLOCKS_BY_NAME.put("Ideographic Description Characters", IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
            BLOCKS_BY_NAME.put("IdeographicDescriptionCharacters", IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
            BLOCKS_BY_NAME.put("IDEOGRAPHIC_DESCRIPTION_CHARACTERS", IDEOGRAPHIC_DESCRIPTION_CHARACTERS);
            BLOCKS_BY_NAME.put("CJK Symbols and Punctuation", CJK_SYMBOLS_AND_PUNCTUATION);
            BLOCKS_BY_NAME.put("CJKSymbolsandPunctuation", CJK_SYMBOLS_AND_PUNCTUATION);
            BLOCKS_BY_NAME.put("CJK_SYMBOLS_AND_PUNCTUATION", CJK_SYMBOLS_AND_PUNCTUATION);
            BLOCKS_BY_NAME.put("Hiragana", HIRAGANA);
            BLOCKS_BY_NAME.put("Katakana", KATAKANA);
            BLOCKS_BY_NAME.put("Bopomofo", BOPOMOFO);
            BLOCKS_BY_NAME.put("Hangul Compatibility Jamo", HANGUL_COMPATIBILITY_JAMO);
            BLOCKS_BY_NAME.put("HangulCompatibilityJamo", HANGUL_COMPATIBILITY_JAMO);
            BLOCKS_BY_NAME.put("HANGUL_COMPATIBILITY_JAMO", HANGUL_COMPATIBILITY_JAMO);
            BLOCKS_BY_NAME.put("Kanbun", KANBUN);
            BLOCKS_BY_NAME.put("Bopomofo Extended", BOPOMOFO_EXTENDED);
            BLOCKS_BY_NAME.put("BopomofoExtended", BOPOMOFO_EXTENDED);
            BLOCKS_BY_NAME.put("BOPOMOFO_EXTENDED", BOPOMOFO_EXTENDED);
            BLOCKS_BY_NAME.put("Katakana Phonetic Extensions", KATAKANA_PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("KatakanaPhoneticExtensions", KATAKANA_PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("KATAKANA_PHONETIC_EXTENSIONS", KATAKANA_PHONETIC_EXTENSIONS);
            BLOCKS_BY_NAME.put("Enclosed CJK Letters and Months", ENCLOSED_CJK_LETTERS_AND_MONTHS);
            BLOCKS_BY_NAME.put("EnclosedCJKLettersandMonths", ENCLOSED_CJK_LETTERS_AND_MONTHS);
            BLOCKS_BY_NAME.put("ENCLOSED_CJK_LETTERS_AND_MONTHS", ENCLOSED_CJK_LETTERS_AND_MONTHS);
            BLOCKS_BY_NAME.put("CJK Compatibility", CJK_COMPATIBILITY);
            BLOCKS_BY_NAME.put("CJKCompatibility", CJK_COMPATIBILITY);
            BLOCKS_BY_NAME.put("CJK_COMPATIBILITY", CJK_COMPATIBILITY);
            BLOCKS_BY_NAME.put("CJK Unified Ideographs Extension A", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
            BLOCKS_BY_NAME.put("CJKUnifiedIdeographsExtensionA", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
            BLOCKS_BY_NAME.put("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
            BLOCKS_BY_NAME.put("Yijing Hexagram Symbols", YIJING_HEXAGRAM_SYMBOLS);
            BLOCKS_BY_NAME.put("YijingHexagramSymbols", YIJING_HEXAGRAM_SYMBOLS);
            BLOCKS_BY_NAME.put("YIJING_HEXAGRAM_SYMBOLS", YIJING_HEXAGRAM_SYMBOLS);
            BLOCKS_BY_NAME.put("CJK Unified Ideographs", CJK_UNIFIED_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("CJKUnifiedIdeographs", CJK_UNIFIED_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("CJK_UNIFIED_IDEOGRAPHS", CJK_UNIFIED_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("Yi Syllables", YI_SYLLABLES);
            BLOCKS_BY_NAME.put("YiSyllables", YI_SYLLABLES);
            BLOCKS_BY_NAME.put("YI_SYLLABLES", YI_SYLLABLES);
            BLOCKS_BY_NAME.put("Yi Radicals", YI_RADICALS);
            BLOCKS_BY_NAME.put("YiRadicals", YI_RADICALS);
            BLOCKS_BY_NAME.put("YI_RADICALS", YI_RADICALS);
            BLOCKS_BY_NAME.put("Hangul Syllables", HANGUL_SYLLABLES);
            BLOCKS_BY_NAME.put("HangulSyllables", HANGUL_SYLLABLES);
            BLOCKS_BY_NAME.put("HANGUL_SYLLABLES", HANGUL_SYLLABLES);
            BLOCKS_BY_NAME.put("High Surrogates", HIGH_SURROGATES);
            BLOCKS_BY_NAME.put("HighSurrogates", HIGH_SURROGATES);
            BLOCKS_BY_NAME.put("HIGH_SURROGATES", HIGH_SURROGATES);
            BLOCKS_BY_NAME.put("High Private Use Surrogates", HIGH_PRIVATE_USE_SURROGATES);
            BLOCKS_BY_NAME.put("HighPrivateUseSurrogates", HIGH_PRIVATE_USE_SURROGATES);
            BLOCKS_BY_NAME.put("HIGH_PRIVATE_USE_SURROGATES", HIGH_PRIVATE_USE_SURROGATES);
            BLOCKS_BY_NAME.put("Low Surrogates", LOW_SURROGATES);
            BLOCKS_BY_NAME.put("LowSurrogates", LOW_SURROGATES);
            BLOCKS_BY_NAME.put("LOW_SURROGATES", LOW_SURROGATES);
            BLOCKS_BY_NAME.put("Private Use Area", PRIVATE_USE_AREA);
            BLOCKS_BY_NAME.put("PrivateUseArea", PRIVATE_USE_AREA);
            BLOCKS_BY_NAME.put("PRIVATE_USE_AREA", PRIVATE_USE_AREA);
            BLOCKS_BY_NAME.put("CJK Compatibility Ideographs", CJK_COMPATIBILITY_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("CJKCompatibilityIdeographs", CJK_COMPATIBILITY_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("CJK_COMPATIBILITY_IDEOGRAPHS", CJK_COMPATIBILITY_IDEOGRAPHS);
            BLOCKS_BY_NAME.put("Alphabetic Presentation Forms", ALPHABETIC_PRESENTATION_FORMS);
            BLOCKS_BY_NAME.put("AlphabeticPresentationForms", ALPHABETIC_PRESENTATION_FORMS);
            BLOCKS_BY_NAME.put("ALPHABETIC_PRESENTATION_FORMS", ALPHABETIC_PRESENTATION_FORMS);
            BLOCKS_BY_NAME.put("Arabic Presentation Forms-A", ARABIC_PRESENTATION_FORMS_A);
            BLOCKS_BY_NAME.put("ArabicPresentationForms-A", ARABIC_PRESENTATION_FORMS_A);
            BLOCKS_BY_NAME.put("ARABIC_PRESENTATION_FORMS_A", ARABIC_PRESENTATION_FORMS_A);
            BLOCKS_BY_NAME.put("Variation Selectors", VARIATION_SELECTORS);
            BLOCKS_BY_NAME.put("VariationSelectors", VARIATION_SELECTORS);
            BLOCKS_BY_NAME.put("VARIATION_SELECTORS", VARIATION_SELECTORS);
            BLOCKS_BY_NAME.put("Combining Half Marks", COMBINING_HALF_MARKS);
            BLOCKS_BY_NAME.put("CombiningHalfMarks", COMBINING_HALF_MARKS);
            BLOCKS_BY_NAME.put("COMBINING_HALF_MARKS", COMBINING_HALF_MARKS);
            BLOCKS_BY_NAME.put("CJK Compatibility Forms", CJK_COMPATIBILITY_FORMS);
            BLOCKS_BY_NAME.put("CJKCompatibilityForms", CJK_COMPATIBILITY_FORMS);
            BLOCKS_BY_NAME.put("CJK_COMPATIBILITY_FORMS", CJK_COMPATIBILITY_FORMS);
            BLOCKS_BY_NAME.put("Small Form Variants", SMALL_FORM_VARIANTS);
            BLOCKS_BY_NAME.put("SmallFormVariants", SMALL_FORM_VARIANTS);
            BLOCKS_BY_NAME.put("SMALL_FORM_VARIANTS", SMALL_FORM_VARIANTS);
            BLOCKS_BY_NAME.put("Arabic Presentation Forms-B", ARABIC_PRESENTATION_FORMS_B);
            BLOCKS_BY_NAME.put("ArabicPresentationForms-B", ARABIC_PRESENTATION_FORMS_B);
            BLOCKS_BY_NAME.put("ARABIC_PRESENTATION_FORMS_B", ARABIC_PRESENTATION_FORMS_B);
            BLOCKS_BY_NAME.put("Halfwidth and Fullwidth Forms", HALFWIDTH_AND_FULLWIDTH_FORMS);
            BLOCKS_BY_NAME.put("HalfwidthandFullwidthForms", HALFWIDTH_AND_FULLWIDTH_FORMS);
            BLOCKS_BY_NAME.put("HALFWIDTH_AND_FULLWIDTH_FORMS", HALFWIDTH_AND_FULLWIDTH_FORMS);
            BLOCKS_BY_NAME.put("Specials", SPECIALS);
            BLOCKS_BY_NAME.put("Linear B Syllabary", LINEAR_B_SYLLABARY);
            BLOCKS_BY_NAME.put("LinearBSyllabary", LINEAR_B_SYLLABARY);
            BLOCKS_BY_NAME.put("LINEAR_B_SYLLABARY", LINEAR_B_SYLLABARY);
            BLOCKS_BY_NAME.put("Linear B Ideograms", LINEAR_B_IDEOGRAMS);
            BLOCKS_BY_NAME.put("LinearBIdeograms", LINEAR_B_IDEOGRAMS);
            BLOCKS_BY_NAME.put("LINEAR_B_IDEOGRAMS", LINEAR_B_IDEOGRAMS);
            BLOCKS_BY_NAME.put("Aegean Numbers", AEGEAN_NUMBERS);
            BLOCKS_BY_NAME.put("AegeanNumbers", AEGEAN_NUMBERS);
            BLOCKS_BY_NAME.put("AEGEAN_NUMBERS", AEGEAN_NUMBERS);
            BLOCKS_BY_NAME.put("Old Italic", OLD_ITALIC);
            BLOCKS_BY_NAME.put("OldItalic", OLD_ITALIC);
            BLOCKS_BY_NAME.put("OLD_ITALIC", OLD_ITALIC);
            BLOCKS_BY_NAME.put("Gothic", GOTHIC);
            BLOCKS_BY_NAME.put("Ugaritic", UGARITIC);
            BLOCKS_BY_NAME.put("Deseret", DESERET);
            BLOCKS_BY_NAME.put("Shavian", SHAVIAN);
            BLOCKS_BY_NAME.put("Osmanya", OSMANYA);
            BLOCKS_BY_NAME.put("Cypriot Syllabary", CYPRIOT_SYLLABARY);
            BLOCKS_BY_NAME.put("CypriotSyllabary", CYPRIOT_SYLLABARY);
            BLOCKS_BY_NAME.put("CYPRIOT_SYLLABARY", CYPRIOT_SYLLABARY);
            BLOCKS_BY_NAME.put("Byzantine Musical Symbols", BYZANTINE_MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("ByzantineMusicalSymbols", BYZANTINE_MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("BYZANTINE_MUSICAL_SYMBOLS", BYZANTINE_MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("Musical Symbols", MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("MusicalSymbols", MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("MUSICAL_SYMBOLS", MUSICAL_SYMBOLS);
            BLOCKS_BY_NAME.put("Tai Xuan Jing Symbols", TAI_XUAN_JING_SYMBOLS);
            BLOCKS_BY_NAME.put("TaiXuanJingSymbols", TAI_XUAN_JING_SYMBOLS);
            BLOCKS_BY_NAME.put("TAI_XUAN_JING_SYMBOLS", TAI_XUAN_JING_SYMBOLS);
            BLOCKS_BY_NAME.put("Mathematical Alphanumeric Symbols", MATHEMATICAL_ALPHANUMERIC_SYMBOLS);
            BLOCKS_BY_NAME.put("MathematicalAlphanumericSymbols", MATHEMATICAL_ALPHANUMERIC_SYMBOLS);
            BLOCKS_BY_NAME.put("MATHEMATICAL_ALPHANUMERIC_SYMBOLS", MATHEMATICAL_ALPHANUMERIC_SYMBOLS);
            BLOCKS_BY_NAME.put("CJK Unified Ideographs Extension B", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
            BLOCKS_BY_NAME.put("CJKUnifiedIdeographsExtensionB", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
            BLOCKS_BY_NAME.put("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B", CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
            BLOCKS_BY_NAME.put("CJK Compatibility Ideographs Supplement", CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("CJKCompatibilityIdeographsSupplement", CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT", CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("Tags", TAGS);
            BLOCKS_BY_NAME.put("Variation Selectors Supplement", VARIATION_SELECTORS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("VariationSelectorsSupplement", VARIATION_SELECTORS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("VARIATION_SELECTORS_SUPPLEMENT", VARIATION_SELECTORS_SUPPLEMENT);
            BLOCKS_BY_NAME.put("Supplementary Private Use Area-A", SUPPLEMENTARY_PRIVATE_USE_AREA_A);
            BLOCKS_BY_NAME.put("SupplementaryPrivateUseArea-A", SUPPLEMENTARY_PRIVATE_USE_AREA_A);
            BLOCKS_BY_NAME.put("SUPPLEMENTARY_PRIVATE_USE_AREA_A", SUPPLEMENTARY_PRIVATE_USE_AREA_A);
            BLOCKS_BY_NAME.put("Supplementary Private Use Area-B", SUPPLEMENTARY_PRIVATE_USE_AREA_B);
            BLOCKS_BY_NAME.put("SupplementaryPrivateUseArea-B", SUPPLEMENTARY_PRIVATE_USE_AREA_B);
            BLOCKS_BY_NAME.put("SUPPLEMENTARY_PRIVATE_USE_AREA_B", SUPPLEMENTARY_PRIVATE_USE_AREA_B);
        }
        
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
            UnicodeBlock match = BLOCKS_BY_NAME.get(blockName);
            if (match == null) {
                throw new IllegalArgumentException();
            }
            return match;
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
         * @throws IllegalArgumentException
         *             if {@code codePoint} is not a valid Unicode code point.
         * @since 1.5
         */
        public static UnicodeBlock of(int codePoint) {
            if (!isValidCodePoint(codePoint)) {
                throw new IllegalArgumentException();
            }
            int low = 0;
            int mid = -1;
            int high = BLOCKS.length - 1;
            while (low <= high) {
                mid = (low + high) >>> 1;
                UnicodeBlock block = BLOCKS[mid];
                if (codePoint > block.end) {
                    low = mid + 1;
                } else if (codePoint >= block.start && codePoint <= block.end) {
                    return block;
                } else {
                    high = mid - 1;
                }
            }
            return null;
        }
        
        private int start;
        private int end;
        private UnicodeBlock(String name, int start, int end) {
            super(name);
            this.start = start;
            this.end = end;
        }
    }

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
        return value - c.value;
    }
    
    /**
     * Returns a {@code Character} instance for the {@code char} value passed.
     * For ASCII/Latin-1 characters (and generally all characters with a Unicode
     * value up to 512), this method should be used instead of the constructor,
     * as it maintains a cache of corresponding {@code Character} instances.
     *
     * @param c
     *            the char value for which to get a {@code Character} instance.
     * @return the {@code Character} instance for {@code c}.
     * @since 1.5
     */
    public static Character valueOf(char c) {
        if (c >= CACHE_LEN ) {
            return new Character(c);
        }
        return valueOfCache.CACHE[c];
    }

    private static final int CACHE_LEN = 512;

    static class valueOfCache {
        /*
        * Provides a cache for the 'valueOf' method. A size of 512 should cache the
        * first couple pages of Unicode, which includes the ASCII/Latin-1
        * characters, which other parts of this class are optimized for.
        */
        private static final Character[] CACHE = new Character[CACHE_LEN ];

        static {
            for(int i=0; i<CACHE.length; i++){
                CACHE[i] =  new Character((char)i);
            }
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
        // http://www.faqs.org/rfcs/rfc2781.html
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
     * Returns the code point that preceds {@code index} in the specified
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
     * Returns the code point that preceds {@code index} in the specified
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
     * Returns the code point that preceds the {@code index} in the specified
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
     * @throws IllegalArgumentException
     *             if {@code codePoint} is not a valid Unicode code point.
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
        if (!isValidCodePoint(codePoint)) {
            throw new IllegalArgumentException();
        }
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
            // http://www.faqs.org/rfcs/rfc2781.html
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
     * @throws IllegalArgumentException
     *             if {@code codePoint} is not a valid Unicode code point.
     * @since 1.5
     */
    public static char[] toChars(int codePoint) {
        if (!isValidCodePoint(codePoint)) {
            throw new IllegalArgumentException();
        }

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
        if (seq == null) {
            throw new NullPointerException();
        }
        int len = seq.length;
        int endIndex = offset + count;
        if (offset < 0 || count < 0 || endIndex > len) {
            throw new IndexOutOfBoundsException();
        }

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
    public static int offsetByCodePoints(CharSequence seq, int index,
            int codePointOffset) {
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

        assert codePointOffset < 0;
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
        if (seq == null) {
            throw new NullPointerException();
        }
        int end = start + count;
        if (start < 0 || count < 0 || end > seq.length || index < start
                || index > end) {
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

        assert codePointOffset < 0;
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
        if (radix >= MIN_RADIX && radix <= MAX_RADIX) {
            if (c < 128) {
                // Optimized for ASCII
                int result = -1;
                if ('0' <= c && c <= '9') {
                    result = c - '0';
                } else if ('a' <= c && c <= 'z') {
                    result = c - ('a' - 10);
                } else if ('A' <= c && c <= 'Z') {
                    result = c - ('A' - 10);
                }
                return result < radix ? result : -1;
            }
            int result = BinarySearch.binarySearchRange(digitKeys, c);
            if (result >= 0 && c <= digitValues[result * 2]) {
                int value = (char) (c - digitValues[result * 2 + 1]);
                if (value >= radix) {
                    return -1;
                }
                return value;
            }
        }
        return -1;
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
        return UCharacter.digit(codePoint, radix);
    }

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
        return (object instanceof Character)
                && (value == ((Character) object).value);
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
            if (0 <= digit && digit < radix) {
                return (char) (digit < 10 ? digit + '0' : digit + 'a' - 10);
            }
        }
        return 0;
    }

    /**
     * Gets the numeric value of the specified Unicode character.
     * 
     * @param c
     *            the Unicode character to get the numeric value of.
     * @return a non-negative numeric integer value if a numeric value for
     *         {@code c} exists, -1 if there is no numeric value for {@code c},
     *         -2 if the numeric value can not be represented with an integer.
     */
    public static int getNumericValue(char c) {
        if (c < 128) {
            // Optimized for ASCII
            if (c >= '0' && c <= '9') {
                return c - '0';
            }
            if (c >= 'a' && c <= 'z') {
                return c - ('a' - 10);
            }
            if (c >= 'A' && c <= 'Z') {
                return c - ('A' - 10);
            }
            return -1;
        }
        int result = BinarySearch.binarySearchRange(numericKeys, c);
        if (result >= 0 && c <= numericValues[result * 2]) {
            char difference = numericValues[result * 2 + 1];
            if (difference == 0) {
                return -2;
            }
            // Value is always positive, must be negative value
            if (difference > c) {
                return c - (short) difference;
            }
            return c - difference;
        }
        return -1;
    }
    
    /**
     * Gets the numeric value of the specified Unicode code point. For example,
     * the code point '\u216B' stands for the Roman number XII, which has the
     * numeric value 12.
     * 
     * @param codePoint
     *            the Unicode code point to get the numeric value of.
     * @return a non-negative numeric integer value if a numeric value for
     *         {@code codePoint} exists, -1 if there is no numeric value for
     *         {@code codePoint}, -2 if the numeric value can not be
     *         represented with an integer.
     */
    public static int getNumericValue(int codePoint) {
        return UCharacter.getNumericValue(codePoint);
    }

    /**
     * Gets the general Unicode category of the specified character.
     * 
     * @param c
     *            the character to get the category of.
     * @return the Unicode category of {@code c}.
     */
    public static int getType(char c) {
        if(c < 1000) {
            return typeValuesCache[(int)c];
        }
        int result = BinarySearch.binarySearchRange(typeKeys, c);
        int high = typeValues[result * 2];
        if (c <= high) {
            int code = typeValues[result * 2 + 1];
            if (code < 0x100) {
                return code;
            }
            return (c & 1) == 1 ? code >> 8 : code & 0xff;
        }
        return UNASSIGNED;
    }
    
    /**
     * Gets the general Unicode category of the specified code point.
     * 
     * @param codePoint
     *            the Unicode code point to get the category of.
     * @return the Unicode category of {@code codePoint}.
     */
    public static int getType(int codePoint) {
    	if (codePoint < 1000 && codePoint > 0) {
    		return typeValuesCache[codePoint];
    	} 
        int type = UCharacter.getType(codePoint);

        // the type values returned by UCharacter are not compatible with what
        // the spec says.RI's Character type values skip the value 17.
        if (type <= Character.FORMAT) {
            return type;
        }
        return (type + 1);
    }

    /**
     * Gets the Unicode directionality of the specified character.
     * 
     * @param c
     *            the character to get the directionality of.
     * @return the Unicode directionality of {@code c}.
     */
    public static byte getDirectionality(char c) {
        int result = BinarySearch.binarySearchRange(bidiKeys, c);
        int high = bidiValues[result * 2];
        if (c <= high) {
            int code = bidiValues[result * 2 + 1];
            if (code < 0x100) {
                return (byte) (code - 1);
            }
            return (byte) (((c & 1) == 1 ? code >> 8 : code & 0xff) - 1);
        }
        return DIRECTIONALITY_UNDEFINED;
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
        
        byte UCDirectionality = UCharacter.getDirectionality(codePoint);       
        if (UCDirectionality == -1) {
            return -1;
        }
        return DIRECTIONALITY[UCDirectionality];
    }

    /**
     * Indicates whether the specified character is mirrored.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is mirrored; {@code false}
     *         otherwise.
     */
    public static boolean isMirrored(char c) {
        int value = c / 16;
        if (value >= mirrored.length) {
            return false;
        }
        int bit = 1 << (c % 16);
        return (mirrored[value] & bit) != 0;
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
        return UCharacter.isMirrored(codePoint);
    }

    @Override
    public int hashCode() {
        return value;
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
        return getType(c) != UNASSIGNED;
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
        return UCharacter.isDefined(codePoint);
    }

    /**
     * Indicates whether the specified character is a digit.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a digit; {@code false}
     *         otherwise.
     */
    public static boolean isDigit(char c) {
        // Optimized case for ASCII
        if ('0' <= c && c <= '9') {
            return true;
        }
        if (c < 1632) {
            return false;
        }
        return getType(c) == DECIMAL_DIGIT_NUMBER;
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
        return UCharacter.isDigit(codePoint);
    }

    /**
     * Indicates whether the specified character is ignorable in a Java or
     * Unicode identifier.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is ignorable; {@code false} otherwise.
     */
    public static boolean isIdentifierIgnorable(char c) {
        return (c >= 0 && c <= 8) || (c >= 0xe && c <= 0x1b)
                || (c >= 0x7f && c <= 0x9f) || getType(c) == FORMAT;
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
        return UCharacter.isIdentifierIgnorable(codePoint);
    }

    /**
     * Indicates whether the specified character is an ISO control character.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is an ISO control character;
     *         {@code false} otherwise.
     */
    public static boolean isISOControl(char c) {
        return isISOControl((int)c);
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
        // Optimized case for ASCII
        if (c < 128) {
            return (typeTags[c] & ISJAVAPART) != 0;
        }

        int type = getType(c);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == CURRENCY_SYMBOL || type == CONNECTOR_PUNCTUATION
                || (type >= DECIMAL_DIGIT_NUMBER && type <= LETTER_NUMBER)
                || type == NON_SPACING_MARK || type == COMBINING_SPACING_MARK
                || (c >= 0x80 && c <= 0x9f) || type == FORMAT;
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
        int type = getType(codePoint);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == CURRENCY_SYMBOL || type == CONNECTOR_PUNCTUATION
                || (type >= DECIMAL_DIGIT_NUMBER && type <= LETTER_NUMBER)
                || type == COMBINING_SPACING_MARK || type == NON_SPACING_MARK 
                || isIdentifierIgnorable(codePoint);
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
        // Optimized case for ASCII
        if (c < 128) {
            return (typeTags[c] & ISJAVASTART) != 0;
        }

        int type = getType(c);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == CURRENCY_SYMBOL || type == CONNECTOR_PUNCTUATION
                || type == LETTER_NUMBER;
    }
    
    /**
     * Indicates whether the specified code point is a valid start for a Java
     * identifier.
     * 
     * @param codePoint
     *            the code point to check.
     * @return {@code true} if {@code codePoint} is a valid start of a Java
     *         identifier; {@code false} otherwise.
     */
    public static boolean isJavaIdentifierStart(int codePoint) {
        int type = getType(codePoint);
        return isLetter(codePoint) || type == CURRENCY_SYMBOL
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
        if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
            return true;
        }
        if (c < 128) {
            return false;
        }
        int type = getType(c);
        return type >= UPPERCASE_LETTER && type <= OTHER_LETTER;
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
        return UCharacter.isLetter(codePoint);
    }

    /**
     * Indicates whether the specified character is a letter or a digit.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a letter or a digit; {@code false}
     *         otherwise.
     */
    public static boolean isLetterOrDigit(char c) {
        int type = getType(c);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == DECIMAL_DIGIT_NUMBER;
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
        return UCharacter.isLetterOrDigit(codePoint);
    }

    /**
     * Indicates whether the specified character is a lower case letter.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a lower case letter; {@code false}
     *         otherwise.
     */
    public static boolean isLowerCase(char c) {
        // Optimized case for ASCII
        if ('a' <= c && c <= 'z') {
            return true;
        }
        if (c < 128) {
            return false;
        }

        return getType(c) == LOWERCASE_LETTER;
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
        return UCharacter.isLowerCase(codePoint);
    }

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
        if (c == 0x20 || c == 0xa0 || c == 0x1680) {
            return true;
        }
        if (c < 0x2000) {
            return false;
        }
        return c <= 0x200b || c == 0x2028 || c == 0x2029 || c == 0x202f
                || c == 0x3000;
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
        return UCharacter.isSpaceChar(codePoint);
    }

    /**
     * Indicates whether the specified character is a titlecase character.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a titlecase character, {@code false}
     *         otherwise.
     */
    public static boolean isTitleCase(char c) {
        if (c == '\u01c5' || c == '\u01c8' || c == '\u01cb' || c == '\u01f2') {
            return true;
        }
        if (c >= '\u1f88' && c <= '\u1ffc') {
            // 0x1f88 - 0x1f8f, 0x1f98 - 0x1f9f, 0x1fa8 - 0x1faf
            if (c > '\u1faf') {
                return c == '\u1fbc' || c == '\u1fcc' || c == '\u1ffc';
            }
            int last = c & 0xf;
            return last >= 8 && last <= 0xf;
        }
        return false;
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
        return UCharacter.isTitleCase(codePoint);
    }

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
        int type = getType(c);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == CONNECTOR_PUNCTUATION
                || (type >= DECIMAL_DIGIT_NUMBER && type <= LETTER_NUMBER)
                || type == NON_SPACING_MARK || type == COMBINING_SPACING_MARK
                || isIdentifierIgnorable(c);
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
        return UCharacter.isUnicodeIdentifierPart(codePoint);
    }

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
        int type = getType(c);
        return (type >= UPPERCASE_LETTER && type <= OTHER_LETTER)
                || type == LETTER_NUMBER;
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
        return UCharacter.isUnicodeIdentifierStart(codePoint);
    }

    /**
     * Indicates whether the specified character is an upper case letter.
     * 
     * @param c
     *            the character to check.
     * @return {@code true} if {@code c} is a upper case letter; {@code false}
     *         otherwise.
     */
    public static boolean isUpperCase(char c) {
        // Optimized case for ASCII
        if ('A' <= c && c <= 'Z') {
            return true;
        }
        if (c < 128) {
            return false;
        }

        return getType(c) == UPPERCASE_LETTER;
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
        return UCharacter.isUpperCase(codePoint);
    }

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
        // Optimized case for ASCII
        if ((c >= 0x1c && c <= 0x20) || (c >= 0x9 && c <= 0xd)) {
            return true;
        }
        if (c == 0x1680) {
            return true;
        }
        if (c < 0x2000 || c == 0x2007) {
            return false;
        }
        return c <= 0x200b || c == 0x2028 || c == 0x2029 || c == 0x3000;
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
        //FIXME depends on ICU when the codePoint is '\u2007'
        return UCharacter.isWhitespace(codePoint);
        
    }

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
        // Optimized case for ASCII
        if ('A' <= c && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        }
        if (c < 192) {// || c == 215 || (c > 222 && c < 256)) {
            return c;
        } 
        if (c<1000) {
            return (char)lowercaseValuesCache[c-192];
        }

        int result = BinarySearch.binarySearchRange(lowercaseKeys, c);
        if (result >= 0) {
            boolean by2 = false;
            char start = lowercaseKeys.charAt(result);
            char end = lowercaseValues[result * 2];
            if ((start & 0x8000) != (end & 0x8000)) {
                end ^= 0x8000;
                by2 = true;
            }
            if (c <= end) {
                if (by2 && (c & 1) != (start & 1)) {
                    return c;
                }
                char mapping = lowercaseValues[result * 2 + 1];
                return (char) (c + mapping);
            }
        }
        return c;
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
        return UCharacter.toLowerCase(codePoint);
    }

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
        if (isTitleCase(c)) {
            return c;
        }
        int result = BinarySearch.binarySearch(titlecaseKeys, c);
        if (result >= 0) {
            return titlecaseValues[result];
        }
        return toUpperCase(c);
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
        return UCharacter.toTitleCase(codePoint);
    }

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
        // Optimized case for ASCII
        if ('a' <= c && c <= 'z') {
            return (char) (c - ('a' - 'A'));
        }
        if (c < 181) {
            return c;
        }
        if (c<1000) {
            return (char)uppercaseValuesCache[(int)c-181];
        }
        int result = BinarySearch.binarySearchRange(uppercaseKeys, c);
        if (result >= 0) {
            boolean by2 = false;
            char start = uppercaseKeys.charAt(result);
            char end = uppercaseValues[result * 2];
            if ((start & 0x8000) != (end & 0x8000)) {
                end ^= 0x8000;
                by2 = true;
            }
            if (c <= end) {
                if (by2 && (c & 1) != (start & 1)) {
                    return c;
                }
                char mapping = uppercaseValues[result * 2 + 1];
                return (char) (c + mapping);
            }
        }
        return c;
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
        return UCharacter.toUpperCase(codePoint);
    }

}
