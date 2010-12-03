/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package java.text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Currency;
import java.util.Locale;

/**
 * A concrete subclass of {@link NumberFormat} that formats decimal numbers. It
 * has a variety of features designed to make it possible to parse and format
 * numbers in any locale, including support for Western, Arabic, or Indic
 * digits. It also supports different flavors of numbers, including integers
 * ("123"), fixed-point numbers ("123.4"), scientific notation ("1.23E4"),
 * percentages ("12%"), and currency amounts ("$123"). All of these flavors can
 * be easily localized.
 * <p>
 * <strong>This is an enhanced version of {@code DecimalFormat} that is based on
 * the standard version in the RI. New or changed functionality is labeled
 * <strong><font color="red">NEW</font></strong>.</strong>
 * <p>
 * To obtain a {@link NumberFormat} for a specific locale (including the default
 * locale), call one of {@code NumberFormat}'s factory methods such as
 * {@code NumberFormat.getInstance}. Do not call the {@code DecimalFormat}
 * constructors directly, unless you know what you are doing, since the
 * {@link NumberFormat} factory methods may return subclasses other than
 * {@code DecimalFormat}. If you need to customize the format object, do
 * something like this: <blockquote>
 *
 * <pre>
 * NumberFormat f = NumberFormat.getInstance(loc);
 * if (f instanceof DecimalFormat) {
 *     ((DecimalFormat)f).setDecimalSeparatorAlwaysShown(true);
 * }
 * </pre>
 *
 * </blockquote>
 * <h5>Example:</h5>
 * <blockquote>
 *
 * <pre>
 * // Print out a number using the localized number, currency,
 * // and percent format for each locale
 * Locale[] locales = NumberFormat.getAvailableLocales();
 * double myNumber = -1234.56;
 * NumberFormat format;
 * for (int j = 0; j &lt; 3; ++j) {
 *     System.out.println(&quot;FORMAT&quot;);
 *     for (int i = 0; i &lt; locales.length; ++i) {
 *         if (locales[i].getCountry().length() == 0) {
 *             // Skip language-only locales
 *             continue;
 *         }
 *         System.out.print(locales[i].getDisplayName());
 *         switch (j) {
 *             case 0:
 *                 format = NumberFormat.getInstance(locales[i]);
 *                 break;
 *             case 1:
 *                 format = NumberFormat.getCurrencyInstance(locales[i]);
 *                 break;
 *             default:
 *                 format = NumberFormat.getPercentInstance(locales[i]);
 *                 break;
 *         }
 *         try {
 *             // Assume format is a DecimalFormat
 *             System.out.print(&quot;: &quot;; + ((DecimalFormat)format).toPattern() + &quot; -&gt; &quot;
 *                     + form.format(myNumber));
 *         } catch (Exception e) {
 *         }
 *         try {
 *             System.out.println(&quot; -&gt; &quot; + format.parse(form.format(myNumber)));
 *         } catch (ParseException e) {
 *         }
 *     }
 * }
 * </pre>
 *
 * </blockquote>
 * <h4>Patterns</h4>
 * <p>
 * A {@code DecimalFormat} consists of a <em>pattern</em> and a set of
 * <em>symbols</em>. The pattern may be set directly using
 * {@link #applyPattern(String)}, or indirectly using other API methods which
 * manipulate aspects of the pattern, such as the minimum number of integer
 * digits. The symbols are stored in a {@link DecimalFormatSymbols} object. When
 * using the {@link NumberFormat} factory methods, the pattern and symbols are
 * read from ICU's locale data.
 * <h4>Special Pattern Characters</h4>
 * <p>
 * Many characters in a pattern are taken literally; they are matched during
 * parsing and are written out unchanged during formatting. On the other hand,
 * special characters stand for other characters, strings, or classes of
 * characters. For example, the '#' character is replaced by a localized digit.
 * Often the replacement character is the same as the pattern character; in the
 * U.S. locale, the ',' grouping character is replaced by ','. However, the
 * replacement is still happening, and if the symbols are modified, the grouping
 * character changes. Some special characters affect the behavior of the
 * formatter by their presence; for example, if the percent character is seen,
 * then the value is multiplied by 100 before being displayed.
 * <p>
 * To insert a special character in a pattern as a literal, that is, without any
 * special meaning, the character must be quoted. There are some exceptions to
 * this which are noted below.
 * <p>
 * The characters listed here are used in non-localized patterns. Localized
 * patterns use the corresponding characters taken from this formatter's
 * {@link DecimalFormatSymbols} object instead, and these characters lose their
 * special status. Two exceptions are the currency sign and quote, which are not
 * localized.
 * <blockquote> <table border="0" cellspacing="3" cellpadding="0" summary="Chart
 * showing symbol, location, localized, and meaning.">
 * <tr bgcolor="#ccccff">
 * <th align="left">Symbol</th>
 * <th align="left">Location</th>
 * <th align="left">Localized?</th>
 * <th align="left">Meaning</th>
 * </tr>
 * <tr valign="top">
 * <td>{@code 0}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Digit.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code @}</td>
 * <td>Number</td>
 * <td>No</td>
 * <td><strong><font color="red">NEW</font>&nbsp;</strong> Significant
 * digit.</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code #}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Digit, leading zeroes are not shown.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code .}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Decimal separator or monetary decimal separator.</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code -}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Minus sign.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code ,}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Grouping separator.</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code E}</td>
 * <td>Number</td>
 * <td>Yes</td>
 * <td>Separates mantissa and exponent in scientific notation.
 * <em>Does not need to be quoted in prefix or suffix.</em></td>
 * </tr>
 * <tr valign="top">
 * <td>{@code +}</td>
 * <td>Exponent</td>
 * <td>Yes</td>
 * <td><strong><font color="red">NEW</font>&nbsp;</strong> Prefix
 * positive exponents with localized plus sign.
 * <em>Does not need to be quoted in prefix or suffix.</em></td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code ;}</td>
 * <td>Subpattern boundary</td>
 * <td>Yes</td>
 * <td>Separates positive and negative subpatterns.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code %}</td>
 * <td>Prefix or suffix</td>
 * <td>Yes</td>
 * <td>Multiply by 100 and show as percentage.</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code \u2030} ({@code &#92;u2030})</td>
 * <td>Prefix or suffix</td>
 * <td>Yes</td>
 * <td>Multiply by 1000 and show as per mille.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code &#164;} ({@code &#92;u00A4})</td>
 * <td>Prefix or suffix</td>
 * <td>No</td>
 * <td>Currency sign, replaced by currency symbol. If doubled, replaced by
 * international currency symbol. If present in a pattern, the monetary decimal
 * separator is used instead of the decimal separator.</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code '}</td>
 * <td>Prefix or suffix</td>
 * <td>No</td>
 * <td>Used to quote special characters in a prefix or suffix, for example,
 * {@code "'#'#"} formats 123 to {@code "#123"}. To create a single quote
 * itself, use two in a row: {@code "# o''clock"}.</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code *}</td>
 * <td>Prefix or suffix boundary</td>
 * <td>Yes</td>
 * <td><strong><font color="red">NEW</font>&nbsp;</strong> Pad escape,
 * precedes pad character. </td>
 * </tr>
 * </table> </blockquote>
 * <p>
 * A {@code DecimalFormat} pattern contains a postive and negative subpattern,
 * for example, "#,##0.00;(#,##0.00)". Each subpattern has a prefix, a numeric
 * part and a suffix. If there is no explicit negative subpattern, the negative
 * subpattern is the localized minus sign prefixed to the positive subpattern.
 * That is, "0.00" alone is equivalent to "0.00;-0.00". If there is an explicit
 * negative subpattern, it serves only to specify the negative prefix and
 * suffix; the number of digits, minimal digits, and other characteristics are
 * ignored in the negative subpattern. This means that "#,##0.0#;(#)" produces
 * precisely the same result as "#,##0.0#;(#,##0.0#)".
 * <p>
 * The prefixes, suffixes, and various symbols used for infinity, digits,
 * thousands separators, decimal separators, etc. may be set to arbitrary
 * values, and they will appear properly during formatting. However, care must
 * be taken that the symbols and strings do not conflict, or parsing will be
 * unreliable. For example, either the positive and negative prefixes or the
 * suffixes must be distinct for {@link #parse} to be able to distinguish
 * positive from negative values. Another example is that the decimal separator
 * and thousands separator should be distinct characters, or parsing will be
 * impossible.
 * <p>
 * The <em>grouping separator</em> is a character that separates clusters of
 * integer digits to make large numbers more legible. It is commonly used for
 * thousands, but in some locales it separates ten-thousands. The <em>grouping
 * size</em>
 * is the number of digits between the grouping separators, such as 3 for
 * "100,000,000" or 4 for "1 0000 0000". There are actually two different
 * grouping sizes: One used for the least significant integer digits, the
 * <em>primary grouping size</em>, and one used for all others, the
 * <em>secondary grouping size</em>. In most locales these are the same, but
 * sometimes they are different. For example, if the primary grouping interval
 * is 3, and the secondary is 2, then this corresponds to the pattern
 * "#,##,##0", and the number 123456789 is formatted as "12,34,56,789". If a
 * pattern contains multiple grouping separators, the interval between the last
 * one and the end of the integer defines the primary grouping size, and the
 * interval between the last two defines the secondary grouping size. All others
 * are ignored, so "#,##,###,####", "###,###,####" and "##,#,###,####" produce
 * the same result.
 * <p>
 * Illegal patterns, such as "#.#.#" or "#.###,###", will cause
 * {@code DecimalFormat} to throw an {@link IllegalArgumentException} with a
 * message that describes the problem.
 * <h4>Pattern BNF</h4>
 *
 * <pre>
 * pattern    := subpattern (';' subpattern)?
 * subpattern := prefix? number exponent? suffix?
 * number     := (integer ('.' fraction)?) | sigDigits
 * prefix     := '\\u0000'..'\\uFFFD' - specialCharacters
 * suffix     := '\\u0000'..'\\uFFFD' - specialCharacters
 * integer    := '#'* '0'* '0'
 * fraction   := '0'* '#'*
 * sigDigits  := '#'* '@' '@'* '#'*
 * exponent   := 'E' '+'? '0'* '0'
 * padSpec    := '*' padChar
 * padChar    := '\\u0000'..'\\uFFFD' - quote
 *
 * Notation:
 *   X*       0 or more instances of X
 *   X?       0 or 1 instances of X
 *   X|Y      either X or Y
 *   C..D     any character from C up to D, inclusive
 *   S-T      characters in S, except those in T
 * </pre>
 *
 * The first subpattern is for positive numbers. The second (optional)
 * subpattern is for negative numbers.
 * <p>
 * Not indicated in the BNF syntax above:
 * <ul>
 * <li>The grouping separator ',' can occur inside the integer and sigDigits
 * elements, between any two pattern characters of that element, as long as the
 * integer or sigDigits element is not followed by the exponent element.
 * <li><font color="red"><strong>NEW</strong>&nbsp;</font> Two
 * grouping intervals are recognized: The one between the decimal point and the
 * first grouping symbol and the one between the first and second grouping
 * symbols. These intervals are identical in most locales, but in some locales
 * they differ. For example, the pattern &quot;#,##,###&quot; formats the number
 * 123456789 as &quot;12,34,56,789&quot;.</li>
 * <li> <strong><font color="red">NEW</font>&nbsp;</strong> The pad
 * specifier {@code padSpec} may appear before the prefix, after the prefix,
 * before the suffix, after the suffix or not at all.
 * </ul>
 * <h4>Parsing</h4>
 * <p>
 * {@code DecimalFormat} parses all Unicode characters that represent decimal
 * digits, as defined by {@link Character#digit(int, int)}. In addition,
 * {@code DecimalFormat} also recognizes as digits the ten consecutive
 * characters starting with the localized zero digit defined in the
 * {@link DecimalFormatSymbols} object. During formatting, the
 * {@link DecimalFormatSymbols}-based digits are written out.
 * <p>
 * During parsing, grouping separators are ignored.
 * <p>
 * If {@link #parse(String, ParsePosition)} fails to parse a string, it returns
 * {@code null} and leaves the parse position unchanged.
 * <h4>Formatting</h4>
 * <p>
 * Formatting is guided by several parameters, all of which can be specified
 * either using a pattern or using the API. The following description applies to
 * formats that do not use <a href="#sci">scientific notation</a> or <a
 * href="#sigdig">significant digits</a>.
 * <ul>
 * <li>If the number of actual integer digits exceeds the
 * <em>maximum integer digits</em>, then only the least significant digits
 * are shown. For example, 1997 is formatted as "97" if maximum integer digits
 * is set to 2.
 * <li>If the number of actual integer digits is less than the
 * <em>minimum integer digits</em>, then leading zeros are added. For
 * example, 1997 is formatted as "01997" if minimum integer digits is set to 5.
 * <li>If the number of actual fraction digits exceeds the <em>maximum
 * fraction digits</em>,
 * then half-even rounding is performed to the maximum fraction digits. For
 * example, 0.125 is formatted as "0.12" if the maximum fraction digits is 2.
 * <li>If the number of actual fraction digits is less than the
 * <em>minimum fraction digits</em>, then trailing zeros are added. For
 * example, 0.125 is formatted as "0.1250" if the mimimum fraction digits is set
 * to 4.
 * <li>Trailing fractional zeros are not displayed if they occur <em>j</em>
 * positions after the decimal, where <em>j</em> is less than the maximum
 * fraction digits. For example, 0.10004 is formatted as "0.1" if the maximum
 * fraction digits is four or less.
 * </ul>
 * <p>
 * <strong>Special Values</strong>
 * <p>
 * {@code NaN} is represented as a single character, typically
 * {@code &#92;uFFFD}. This character is determined by the
 * {@link DecimalFormatSymbols} object. This is the only value for which the
 * prefixes and suffixes are not used.
 * <p>
 * Infinity is represented as a single character, typically {@code &#92;u221E},
 * with the positive or negative prefixes and suffixes applied. The infinity
 * character is determined by the {@link DecimalFormatSymbols} object. <a
 * name="sci">
 * <h4>Scientific Notation</h4>
 * </a>
 * <p>
 * Numbers in scientific notation are expressed as the product of a mantissa and
 * a power of ten, for example, 1234 can be expressed as 1.234 x 10<sup>3</sup>.
 * The mantissa is typically in the half-open interval [1.0, 10.0) or sometimes
 * [0.0, 1.0), but it does not need to be. {@code DecimalFormat} supports
 * arbitrary mantissas. {@code DecimalFormat} can be instructed to use
 * scientific notation through the API or through the pattern. In a pattern, the
 * exponent character immediately followed by one or more digit characters
 * indicates scientific notation. Example: "0.###E0" formats the number 1234 as
 * "1.234E3".
 * <ul>
 * <li>The number of digit characters after the exponent character gives the
 * minimum exponent digit count. There is no maximum. Negative exponents are
 * formatted using the localized minus sign, <em>not</em> the prefix and
 * suffix from the pattern. This allows patterns such as "0.###E0 m/s". To
 * prefix positive exponents with a localized plus sign, specify '+' between the
 * exponent and the digits: "0.###E+0" will produce formats "1E+1", "1E+0",
 * "1E-1", etc. (In localized patterns, use the localized plus sign rather than
 * '+'.)
 * <li>The minimum number of integer digits is achieved by adjusting the
 * exponent. Example: 0.00123 formatted with "00.###E0" yields "12.3E-4". This
 * only happens if there is no maximum number of integer digits. If there is a
 * maximum, then the minimum number of integer digits is fixed at one.
 * <li>The maximum number of integer digits, if present, specifies the exponent
 * grouping. The most common use of this is to generate <em>engineering
 * notation</em>,
 * in which the exponent is a multiple of three, e.g., "##0.###E0". The number
 * 12345 is formatted using "##0.###E0" as "12.345E3".
 * <li>When using scientific notation, the formatter controls the digit counts
 * using significant digits logic. The maximum number of significant digits
 * limits the total number of integer and fraction digits that will be shown in
 * the mantissa; it does not affect parsing. For example, 12345 formatted with
 * "##0.##E0" is "12.3E3". See the section on significant digits for more
 * details.
 * <li>The number of significant digits shown is determined as follows: If no
 * significant digits are used in the pattern then the minimum number of
 * significant digits shown is one, the maximum number of significant digits
 * shown is the sum of the <em>minimum integer</em> and
 * <em>maximum fraction</em> digits, and it is unaffected by the maximum
 * integer digits. If this sum is zero, then all significant digits are shown.
 * If significant digits are used in the pattern then the number of integer
 * digits is fixed at one and there is no exponent grouping.
 * <li>Exponential patterns may not contain grouping separators.
 * </ul>
 * <a name="sigdig">
 * <h4> <strong><font color="red">NEW</font>&nbsp;</strong> Significant
 * Digits</h4>
 * <p>
 * </a> {@code DecimalFormat} has two ways of controlling how many digits are
 * shown: (a) significant digit counts or (b) integer and fraction digit counts.
 * Integer and fraction digit counts are described above. When a formatter uses
 * significant digits counts, the number of integer and fraction digits is not
 * specified directly, and the formatter settings for these counts are ignored.
 * Instead, the formatter uses as many integer and fraction digits as required
 * to display the specified number of significant digits.
 * <h5>Examples:</h5>
 * <blockquote> <table border=0 cellspacing=3 cellpadding=0>
 * <tr bgcolor="#ccccff">
 * <th align="left">Pattern</th>
 * <th align="left">Minimum significant digits</th>
 * <th align="left">Maximum significant digits</th>
 * <th align="left">Number</th>
 * <th align="left">Output of format()</th>
 * </tr>
 * <tr valign="top">
 * <td>{@code @@@}
 * <td>3</td>
 * <td>3</td>
 * <td>12345</td>
 * <td>{@code 12300}</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code @@@}</td>
 * <td>3</td>
 * <td>3</td>
 * <td>0.12345</td>
 * <td>{@code 0.123}</td>
 * </tr>
 * <tr valign="top">
 * <td>{@code @@##}</td>
 * <td>2</td>
 * <td>4</td>
 * <td>3.14159</td>
 * <td>{@code 3.142}</td>
 * </tr>
 * <tr valign="top" bgcolor="#eeeeff">
 * <td>{@code @@##}</td>
 * <td>2</td>
 * <td>4</td>
 * <td>1.23004</td>
 * <td>{@code 1.23}</td>
 * </tr>
 * </table> </blockquote>
 * <ul>
 * <li>Significant digit counts may be expressed using patterns that specify a
 * minimum and maximum number of significant digits. These are indicated by the
 * {@code '@'} and {@code '#'} characters. The minimum number of significant
 * digits is the number of {@code '@'} characters. The maximum number of
 * significant digits is the number of {@code '@'} characters plus the number of
 * {@code '#'} characters following on the right. For example, the pattern
 * {@code "@@@"} indicates exactly 3 significant digits. The pattern
 * {@code "@##"} indicates from 1 to 3 significant digits. Trailing zero digits
 * to the right of the decimal separator are suppressed after the minimum number
 * of significant digits have been shown. For example, the pattern {@code "@##"}
 * formats the number 0.1203 as {@code "0.12"}.
 * <li>If a pattern uses significant digits, it may not contain a decimal
 * separator, nor the {@code '0'} pattern character. Patterns such as
 * {@code "@00"} or {@code "@.###"} are disallowed.
 * <li>Any number of {@code '#'} characters may be prepended to the left of the
 * leftmost {@code '@'} character. These have no effect on the minimum and
 * maximum significant digit counts, but may be used to position grouping
 * separators. For example, {@code "#,#@#"} indicates a minimum of one
 * significant digit, a maximum of two significant digits, and a grouping size
 * of three.
 * <li>In order to enable significant digits formatting, use a pattern
 * containing the {@code '@'} pattern character.
 * <li>In order to disable significant digits formatting, use a pattern that
 * does not contain the {@code '@'} pattern character.
 * <li>The number of significant digits has no effect on parsing.
 * <li>Significant digits may be used together with exponential notation. Such
 * patterns are equivalent to a normal exponential pattern with a minimum and
 * maximum integer digit count of one, a minimum fraction digit count of the
 * number of '@' characters in the pattern - 1, and a maximum fraction digit
 * count of the number of '@' and '#' characters in the pattern - 1. For
 * example, the pattern {@code "@@###E0"} is equivalent to {@code "0.0###E0"}.
 * <li>If signficant digits are in use then the integer and fraction digit
 * counts, as set via the API, are ignored.
 * </ul>
 * <h4> <strong><font color="red">NEW</font>&nbsp;</strong> Padding</h4>
 * <p>
 * {@code DecimalFormat} supports padding the result of {@code format} to a
 * specific width. Padding may be specified either through the API or through
 * the pattern syntax. In a pattern, the pad escape character followed by a
 * single pad character causes padding to be parsed and formatted. The pad
 * escape character is '*' in unlocalized patterns. For example,
 * {@code "$*x#,##0.00"} formats 123 to {@code "$xx123.00"}, and 1234 to
 * {@code "$1,234.00"}.
 * <ul>
 * <li>When padding is in effect, the width of the positive subpattern,
 * including prefix and suffix, determines the format width. For example, in the
 * pattern {@code "* #0 o''clock"}, the format width is 10.</li>
 * <li>The width is counted in 16-bit code units (Java {@code char}s).</li>
 * <li>Some parameters which usually do not matter have meaning when padding is
 * used, because the pattern width is significant with padding. In the pattern "*
 * ##,##,#,##0.##", the format width is 14. The initial characters "##,##," do
 * not affect the grouping size or maximum integer digits, but they do affect
 * the format width.</li>
 * <li>Padding may be inserted at one of four locations: before the prefix,
 * after the prefix, before the suffix or after the suffix. If padding is
 * specified in any other location, {@link #applyPattern} throws an {@link
 * IllegalArgumentException}. If there is no prefix, before the prefix and after
 * the prefix are equivalent, likewise for the suffix.</li>
 * <li>When specified in a pattern, the 16-bit {@code char} immediately
 * following the pad escape is the pad character. This may be any character,
 * including a special pattern character. That is, the pad escape
 * <em>escapes</em> the following character. If there is no character after
 * the pad escape, then the pattern is illegal.</li>
 * </ul>
 * <h4>Synchronization</h4>
 * <p>
 * {@code DecimalFormat} objects are not synchronized. Multiple threads should
 * not access one formatter concurrently.
 *
 * @see Format
 * @see NumberFormat
 */
public class DecimalFormat extends NumberFormat {

    private static final long serialVersionUID = 864413376551465018L;

    private transient boolean parseBigDecimal = false;

    private transient DecimalFormatSymbols symbols;

    private transient com.ibm.icu.text.DecimalFormat dform;

    private transient com.ibm.icu.text.DecimalFormatSymbols icuSymbols;

    private static final int CURRENT_SERIAL_VERTION = 3;

    private transient int serialVersionOnStream = 3;
    
    // set default RoundingMode to RoundingMode.HALF_EVEN
    private transient RoundingMode roundingMode = RoundingMode.HALF_EVEN;

    /**
     * Constructs a new {@code DecimalFormat} for formatting and parsing numbers
     * for the default locale.
     */
    public DecimalFormat() {
        Locale locale = Locale.getDefault();
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        symbols = new DecimalFormatSymbols(locale);
        dform = new com.ibm.icu.text.DecimalFormat();

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Constructs a new {@code DecimalFormat} using the specified non-localized
     * pattern and the {@code DecimalFormatSymbols} for the default Locale.
     * 
     * @param pattern
     *            the non-localized pattern.
     * @throws IllegalArgumentException
     *            if the pattern cannot be parsed.
     */
    public DecimalFormat(String pattern) {
        Locale locale = Locale.getDefault();
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        symbols = new DecimalFormatSymbols(locale);
        dform = new com.ibm.icu.text.DecimalFormat(pattern, icuSymbols);

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Constructs a new {@code DecimalFormat} using the specified non-localized
     * pattern and {@code DecimalFormatSymbols}.
     * 
     * @param pattern
     *            the non-localized pattern.
     * @param value
     *            the DecimalFormatSymbols.
     * @throws IllegalArgumentException
     *            if the pattern cannot be parsed.
     */
    public DecimalFormat(String pattern, DecimalFormatSymbols value) {
        symbols = (DecimalFormatSymbols) value.clone();
        Locale locale = symbols.getLocale();
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        copySymbols(icuSymbols, symbols);

        dform = new com.ibm.icu.text.DecimalFormat(pattern, icuSymbols);

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    DecimalFormat(String pattern, DecimalFormatSymbols value, com.ibm.icu.text.DecimalFormat icuFormat) {
        symbols = value;
        icuSymbols = value.getIcuSymbols();
        dform = icuFormat;

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Changes the pattern of this decimal format to the specified pattern which
     * uses localized pattern characters.
     * 
     * @param pattern
     *            the localized pattern.
     * @throws IllegalArgumentException
     *            if the pattern cannot be parsed.
     */
    public void applyLocalizedPattern(String pattern) {
        dform.applyLocalizedPattern(pattern);
        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
        if (roundingMode != RoundingMode.HALF_EVEN) {
            setRoundingIncrement(getMaximumFractionDigits());
        }
    }

    /**
     * Changes the pattern of this decimal format to the specified pattern which
     * uses non-localized pattern characters.
     * 
     * @param pattern
     *            the non-localized pattern.
     * @throws IllegalArgumentException
     *            if the pattern cannot be parsed.
     */
    public void applyPattern(String pattern) {

        dform.applyPattern(pattern);
        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
        if (roundingMode != RoundingMode.HALF_EVEN) {
            setRoundingIncrement(getMaximumFractionDigits());
        }
    }

    /**
     * Returns a new instance of {@code DecimalFormat} with the same pattern and
     * properties as this decimal format.
     * 
     * @return a shallow copy of this decimal format.
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        DecimalFormat clone = (DecimalFormat) super.clone();
        clone.dform = (com.ibm.icu.text.DecimalFormat) dform.clone();
        clone.symbols = (DecimalFormatSymbols) symbols.clone();
        return clone;
    }

    /**
     * Compares the specified object to this decimal format and indicates if
     * they are equal. In order to be equal, {@code object} must be an instance
     * of {@code DecimalFormat} with the same pattern and properties.
     * 
     * @param object
     *            the object to compare with this object.
     * @return {@code true} if the specified object is equal to this decimal
     *         format; {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DecimalFormat)) {
            return false;
        }
        DecimalFormat format = (DecimalFormat) object;
        return (this.dform == null ? format.dform == null : this.dform
                .equals(format.dform));
    }

    /**
     * Formats the specified object using the rules of this decimal format and
     * returns an {@code AttributedCharacterIterator} with the formatted number
     * and attributes.
     * 
     * @param object
     *            the object to format.
     * @return an AttributedCharacterIterator with the formatted number and
     *         attributes.
     * @throws IllegalArgumentException
     *             if {@code object} cannot be formatted by this format.
     * @throws NullPointerException
     *             if {@code object} is {@code null}.
     */
    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return dform.formatToCharacterIterator(object);
    }

    /**
     * Formats the specified double value as a string using the pattern of this
     * decimal format and appends the string to the specified string buffer.
     * <p>
     * If the {@code field} member of {@code position} contains a value
     * specifying a format field, then its {@code beginIndex} and
     * {@code endIndex} members will be updated with the position of the first
     * occurrence of this field in the formatted text.
     *
     * @param value
     *            the double to format.
     * @param buffer
     *            the target string buffer to append the formatted double value
     *            to.
     * @param position
     *            on input: an optional alignment field; on output: the offsets
     *            of the alignment field in the formatted text.
     * @return the string buffer.
     */
    @Override
    public StringBuffer format(double value, StringBuffer buffer,
            FieldPosition position) {
        return dform.format(value, buffer, position);
    }

    /**
     * Formats the specified long value as a string using the pattern of this
     * decimal format and appends the string to the specified string buffer.
     * <p>
     * If the {@code field} member of {@code position} contains a value
     * specifying a format field, then its {@code beginIndex} and
     * {@code endIndex} members will be updated with the position of the first
     * occurrence of this field in the formatted text.
     *
     * @param value
     *            the long to format.
     * @param buffer
     *            the target string buffer to append the formatted long value
     *            to.
     * @param position
     *            on input: an optional alignment field; on output: the offsets
     *            of the alignment field in the formatted text.
     * @return the string buffer.
     */
    @Override
    public StringBuffer format(long value, StringBuffer buffer,
            FieldPosition position) {
        return dform.format(value, buffer, position);
    }

    /**
     * Formats the specified object as a string using the pattern of this
     * decimal format and appends the string to the specified string buffer.
     * <p>
     * If the {@code field} member of {@code position} contains a value
     * specifying a format field, then its {@code beginIndex} and
     * {@code endIndex} members will be updated with the position of the first
     * occurrence of this field in the formatted text.
     *
     * @param number
     *            the object to format.
     * @param toAppendTo
     *            the target string buffer to append the formatted number to.
     * @param pos
     *            on input: an optional alignment field; on output: the offsets
     *            of the alignment field in the formatted text.
     * @return the string buffer.
     * @throws IllegalArgumentException
     *             if {@code number} is not an instance of {@code Number}.
     * @throws NullPointerException
     *             if {@code toAppendTo} or {@code pos} is {@code null}.
     */
    @Override
    public final StringBuffer format(Object number, StringBuffer toAppendTo,
            FieldPosition pos) {
        if (!(number instanceof Number)) {
            throw new IllegalArgumentException();
        }
        if (toAppendTo == null || pos == null) {
            throw new NullPointerException();
        }
        if (number instanceof BigInteger || number instanceof BigDecimal) {
            return dform.format(number, toAppendTo, pos);
        }
        return super.format(number, toAppendTo, pos);
    }

    /**
     * Returns the {@code DecimalFormatSymbols} used by this decimal format.
     * 
     * @return a copy of the {@code DecimalFormatSymbols} used by this decimal
     *         format.
     */
    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return (DecimalFormatSymbols) symbols.clone();
    }

    /**
     * Returns the currency used by this decimal format.
     * 
     * @return the currency used by this decimal format.
     * @see DecimalFormatSymbols#getCurrency()
     */
    @Override
    public Currency getCurrency() {
        final com.ibm.icu.util.Currency cur = dform.getCurrency();
        final String code = (cur == null) ? "XXX" : cur.getCurrencyCode(); //$NON-NLS-1$

        return Currency.getInstance(code);
    }

    /**
     * Returns the number of digits grouped together by the grouping separator.
     * This only allows to get the primary grouping size. There is no API to get
     * the secondary grouping size.
     *
     * @return the number of digits grouped together.
     */
    public int getGroupingSize() {
        return dform.getGroupingSize();
    }

    /**
     * Returns the multiplier which is applied to the number before formatting
     * or after parsing.
     * 
     * @return the multiplier.
     */
    public int getMultiplier() {
        return dform.getMultiplier();
    }

    /**
     * Returns the prefix which is formatted or parsed before a negative number.
     * 
     * @return the negative prefix.
     */
    public String getNegativePrefix() {
        return dform.getNegativePrefix();
    }

    /**
     * Returns the suffix which is formatted or parsed after a negative number.
     * 
     * @return the negative suffix.
     */
    public String getNegativeSuffix() {
        return dform.getNegativeSuffix();
    }

    /**
     * Returns the prefix which is formatted or parsed before a positive number.
     * 
     * @return the positive prefix.
     */
    public String getPositivePrefix() {
        return dform.getPositivePrefix();
    }

    /**
     * Returns the suffix which is formatted or parsed after a positive number.
     * 
     * @return the positive suffix.
     */
    public String getPositiveSuffix() {
        return dform.getPositiveSuffix();
    }

    @Override
    public int hashCode() {
        return dform.hashCode();
    }

    /**
     * Indicates whether the decimal separator is shown when there are no
     * fractional digits.
     * 
     * @return {@code true} if the decimal separator should always be formatted;
     *         {@code false} otherwise.
     */
    public boolean isDecimalSeparatorAlwaysShown() {
        return dform.isDecimalSeparatorAlwaysShown();
    }

    /**
     * This value indicates whether the return object of the parse operation is
     * of type {@code BigDecimal}. This value defaults to {@code false}.
     * 
     * @return {@code true} if parse always returns {@code BigDecimals},
     *         {@code false} if the type of the result is {@code Long} or
     *         {@code Double}.
     */
    public boolean isParseBigDecimal() {
        return this.parseBigDecimal;
    }

    /**
     * Sets the flag that indicates whether numbers will be parsed as integers.
     * When this decimal format is used for parsing and this value is set to
     * {@code true}, then the resulting numbers will be of type
     * {@code java.lang.Integer}. Special cases are NaN, positive and negative
     * infinity, which are still returned as {@code java.lang.Double}.
     * 
     *
     * @param value
     *            {@code true} that the resulting numbers of parse operations
     *            will be of type {@code java.lang.Integer} except for the
     *            special cases described above.
     */
    @Override
    public void setParseIntegerOnly(boolean value) {
        // In this implementation, com.ibm.icu.text.DecimalFormat is wrapped to
        // fulfill most of the format and parse feature. And this method is
        // delegated to the wrapped instance of com.ibm.icu.text.DecimalFormat.

        dform.setParseIntegerOnly(value);
    }

    /**
     * Indicates whether parsing with this decimal format will only
     * return numbers of type {@code java.lang.Integer}.
     * 
     * @return {@code true} if this {@code DecimalFormat}'s parse method only
     *         returns {@code java.lang.Integer}; {@code false} otherwise.
     */
    @Override
    public boolean isParseIntegerOnly() {
        return dform.isParseIntegerOnly();
    }

    private static final Double NEGATIVE_ZERO_DOUBLE = new Double(-0.0);

    /**
     * Parses a {@code Long} or {@code Double} from the specified string
     * starting at the index specified by {@code position}. If the string is
     * successfully parsed then the index of the {@code ParsePosition} is
     * updated to the index following the parsed text. On error, the index is
     * unchanged and the error index of {@code ParsePosition} is set to the
     * index where the error occurred.
     * 
     * @param string
     *            the string to parse.
     * @param position
     *            input/output parameter, specifies the start index in
     *            {@code string} from where to start parsing. If parsing is
     *            successful, it is updated with the index following the parsed
     *            text; on error, the index is unchanged and the error index is
     *            set to the index where the error occurred.
     * @return a {@code Long} or {@code Double} resulting from the parse or
     *         {@code null} if there is an error. The result will be a
     *         {@code Long} if the parsed number is an integer in the range of a
     *         long, otherwise the result is a {@code Double}. If
     *         {@code isParseBigDecimal} is {@code true} then it returns the
     *         result as a {@code BigDecimal}.
     */
    @Override
    public Number parse(String string, ParsePosition position) {
        Number number = dform.parse(string, position);
        if (null == number) {
            return null;
        }
        if (this.isParseBigDecimal()) {
            if (number instanceof Long) {
                return new BigDecimal(number.longValue());
            }
            if ((number instanceof Double) && !((Double) number).isInfinite()
                    && !((Double) number).isNaN()) {

                return new BigDecimal(number.doubleValue());
            }
            if (number instanceof BigInteger) {
                return new BigDecimal(number.doubleValue());
            }
            if (number instanceof com.ibm.icu.math.BigDecimal) {
                return new BigDecimal(number.toString());
            }
            return number;
        }
        if ((number instanceof com.ibm.icu.math.BigDecimal)
                || (number instanceof BigInteger)) {
            return new Double(number.doubleValue());
        }

        if (this.isParseIntegerOnly() && number.equals(NEGATIVE_ZERO_DOUBLE)) {
            return new Long(0);
        }
        return number;

    }

    /**
     * Sets the {@code DecimalFormatSymbols} used by this decimal format.
     * 
     * @param value
     *            the {@code DecimalFormatSymbols} to set.
     */
    public void setDecimalFormatSymbols(DecimalFormatSymbols value) {
        if (value != null) {
            symbols = (DecimalFormatSymbols) value.clone();
            icuSymbols = dform.getDecimalFormatSymbols();
            copySymbols(icuSymbols, symbols);
            dform.setDecimalFormatSymbols(icuSymbols);
        }
    }

    /**
     * Sets the currency used by this decimal format. The min and max fraction
     * digits remain the same.
     * 
     * @param currency
     *            the currency this {@code DecimalFormat} should use.
     * @see DecimalFormatSymbols#setCurrency(Currency)
     */
    @Override
    public void setCurrency(Currency currency) {
        dform.setCurrency(com.ibm.icu.util.Currency.getInstance(currency
                .getCurrencyCode()));
        symbols.setCurrency(currency);
    }

    /**
     * Sets whether the decimal separator is shown when there are no fractional
     * digits.
     * 
     * @param value
     *            {@code true} if the decimal separator should always be
     *            formatted; {@code false} otherwise.
     */
    public void setDecimalSeparatorAlwaysShown(boolean value) {
        dform.setDecimalSeparatorAlwaysShown(value);
    }

    /**
     * Sets the number of digits grouped together by the grouping separator.
     * This only allows to set the primary grouping size; the secondary grouping
     * size can only be set with a pattern.
     *
     * @param value
     *            the number of digits grouped together.
     */
    public void setGroupingSize(int value) {
        dform.setGroupingSize(value);
    }

    /**
     * Sets whether or not grouping will be used in this format. Grouping
     * affects both parsing and formatting.
     * 
     * @param value
     *            {@code true} if grouping is used; {@code false} otherwise.
     */
    @Override
    public void setGroupingUsed(boolean value) {
        dform.setGroupingUsed(value);
    }

    /**
     * Indicates whether grouping will be used in this format.
     * 
     * @return {@code true} if grouping is used; {@code false} otherwise.
     */
    @Override
    public boolean isGroupingUsed() {
        return dform.isGroupingUsed();
    }

    /**
     * Sets the maximum number of fraction digits that are printed when
     * formatting numbers other than {@code BigDecimal} and {@code BigInteger}.
     * If the maximum is less than the number of fraction digits, the least
     * significant digits are truncated. If the value passed is bigger than 340
     * then it is replaced by 340. If the value passed is negative then it is
     * replaced by 0.
     * 
     * @param value
     *            the maximum number of fraction digits.
     */
    @Override
    public void setMaximumFractionDigits(int value) {
        super.setMaximumFractionDigits(value);
        dform.setMaximumFractionDigits(value);
        
        // update the RoundingIncrement
		setRoundingIncrement(value);
    }

    /**
     * Sets the maximum number of integer digits that are printed when
     * formatting numbers other than {@code BigDecimal} and {@code BigInteger}.
     * If the maximum is less than the number of integer digits, the most
     * significant digits are truncated. If the value passed is bigger than 309
     * then it is replaced by 309. If the value passed is negative then it is
     * replaced by 0.
     * 
     * @param value
     *            the maximum number of integer digits.
     */
    @Override
    public void setMaximumIntegerDigits(int value) {
        super.setMaximumIntegerDigits(value);
        dform.setMaximumIntegerDigits(value);
    }

    /**
     * Sets the minimum number of fraction digits that are printed when
     * formatting numbers other than {@code BigDecimal} and {@code BigInteger}.
     * If the value passed is bigger than 340 then it is replaced by 340. If the
     * value passed is negative then it is replaced by 0.
     * 
     * @param value
     *            the minimum number of fraction digits.
     */
    @Override
    public void setMinimumFractionDigits(int value) {
        super.setMinimumFractionDigits(value);
        dform.setMinimumFractionDigits(value);
    }

    /**
     * Sets the minimum number of integer digits that are printed when
     * formatting numbers other than {@code BigDecimal} and {@code BigInteger}.
     * If the value passed is bigger than 309 then it is replaced by 309. If the
     * value passed is negative then it is replaced by 0.
     * 
     * @param value
     *            the minimum number of integer digits.
     */
    @Override
    public void setMinimumIntegerDigits(int value) {
        super.setMinimumIntegerDigits(value);
        dform.setMinimumIntegerDigits(value);
    }

    /**
     * Sets the multiplier which is applied to the number before formatting or
     * after parsing.
     * 
     * @param value
     *            the multiplier.
     */
    public void setMultiplier(int value) {
        dform.setMultiplier(value);
    }

    /**
     * Sets the prefix which is formatted or parsed before a negative number.
     * 
     * @param value
     *            the negative prefix.
     */
    public void setNegativePrefix(String value) {
        dform.setNegativePrefix(value);
    }

    /**
     * Sets the suffix which is formatted or parsed after a negative number.
     * 
     * @param value
     *            the negative suffix.
     */
    public void setNegativeSuffix(String value) {
        dform.setNegativeSuffix(value);
    }

    /**
     * Sets the prefix which is formatted or parsed before a positive number.
     * 
     * @param value
     *            the positive prefix.
     */
    public void setPositivePrefix(String value) {
        dform.setPositivePrefix(value);
    }

    /**
     * Sets the suffix which is formatted or parsed after a positive number.
     * 
     * @param value
     *            the positive suffix.
     */
    public void setPositiveSuffix(String value) {
        dform.setPositiveSuffix(value);
    }

    /**
     * Sets the behaviour of the parse method. If set to {@code true} then all
     * the returned objects will be of type {@code BigDecimal}.
     * 
     * @param newValue
     *            {@code true} if all the returned objects should be of type
     *            {@code BigDecimal}; {@code false} otherwise.
     */
    public void setParseBigDecimal(boolean newValue) {
        this.parseBigDecimal = newValue;
    }

    /**
     * Returns the pattern of this decimal format using localized pattern
     * characters.
     * 
     * @return the localized pattern.
     */
    public String toLocalizedPattern() {
        return dform.toLocalizedPattern();
    }

    /**
     * Returns the pattern of this decimal format using non-localized pattern
     * characters.
     * 
     * @return the non-localized pattern.
     */
    public String toPattern() {
        return dform.toPattern();
    }

    // the fields list to be serialized
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("positivePrefix", String.class), //$NON-NLS-1$
            new ObjectStreamField("positiveSuffix", String.class), //$NON-NLS-1$
            new ObjectStreamField("negativePrefix", String.class), //$NON-NLS-1$
            new ObjectStreamField("negativeSuffix", String.class), //$NON-NLS-1$
            new ObjectStreamField("posPrefixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("posSuffixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("negPrefixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("negSuffixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("multiplier", int.class), //$NON-NLS-1$
            new ObjectStreamField("groupingSize", byte.class), //$NON-NLS-1$
            new ObjectStreamField("decimalSeparatorAlwaysShown", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("parseBigDecimal", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("symbols", DecimalFormatSymbols.class), //$NON-NLS-1$
            new ObjectStreamField("useExponentialNotation", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("minExponentDigits", byte.class), //$NON-NLS-1$
            new ObjectStreamField("maximumIntegerDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("minimumIntegerDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("maximumFractionDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("minimumFractionDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("serialVersionOnStream", int.class), }; //$NON-NLS-1$

    /**
     * Writes serialized fields following serialized forms specified by Java
     * specification.
     * 
     * @param stream
     *            the output stream to write serialized bytes
     * @throws IOException
     *             if some I/O error occurs
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("nls")
    private void writeObject(ObjectOutputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("positivePrefix", dform.getPositivePrefix());
        fields.put("positiveSuffix", dform.getPositiveSuffix());
        fields.put("negativePrefix", dform.getNegativePrefix());
        fields.put("negativeSuffix", dform.getNegativeSuffix());
        String posPrefixPattern = (String) Format.getInternalField(
                "posPrefixPattern", dform);
        fields.put("posPrefixPattern", posPrefixPattern);
        String posSuffixPattern = (String) Format.getInternalField(
                "posSuffixPattern", dform);
        fields.put("posSuffixPattern", posSuffixPattern);
        String negPrefixPattern = (String) Format.getInternalField(
                "negPrefixPattern", dform);
        fields.put("negPrefixPattern", negPrefixPattern);
        String negSuffixPattern = (String) Format.getInternalField(
                "negSuffixPattern", dform);
        fields.put("negSuffixPattern", negSuffixPattern);
        fields.put("multiplier", dform.getMultiplier());
        fields.put("groupingSize", (byte) dform.getGroupingSize());
        fields.put("decimalSeparatorAlwaysShown", dform
                .isDecimalSeparatorAlwaysShown());
        fields.put("parseBigDecimal", parseBigDecimal);
        fields.put("symbols", symbols);
        boolean useExponentialNotation = ((Boolean) Format.getInternalField(
                "useExponentialNotation", dform)).booleanValue();
        fields.put("useExponentialNotation", useExponentialNotation);
        byte minExponentDigits = ((Byte) Format.getInternalField(
                "minExponentDigits", dform)).byteValue();
        fields.put("minExponentDigits", minExponentDigits);
        fields.put("maximumIntegerDigits", dform.getMaximumIntegerDigits());
        fields.put("minimumIntegerDigits", dform.getMinimumIntegerDigits());
        fields.put("maximumFractionDigits", dform.getMaximumFractionDigits());
        fields.put("minimumFractionDigits", dform.getMinimumFractionDigits());
        fields.put("serialVersionOnStream", CURRENT_SERIAL_VERTION);
        stream.writeFields();
    }

    /**
     * Reads serialized fields following serialized forms specified by Java
     * specification.
     * 
     * @param stream
     *            the input stream to read serialized bytes
     * @throws IOException
     *             if some I/O error occurs
     * @throws ClassNotFoundException
     *             if some class of serialized objects or fields cannot be found
     */
    @SuppressWarnings("nls")
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        ObjectInputStream.GetField fields = stream.readFields();
        String positivePrefix = (String) fields.get("positivePrefix", "");
        String positiveSuffix = (String) fields.get("positiveSuffix", "");
        String negativePrefix = (String) fields.get("negativePrefix", "-");
        String negativeSuffix = (String) fields.get("negativeSuffix", "");

        String posPrefixPattern = (String) fields.get("posPrefixPattern", "");
        String posSuffixPattern = (String) fields.get("posSuffixPattern", "");
        String negPrefixPattern = (String) fields.get("negPrefixPattern", "-");
        String negSuffixPattern = (String) fields.get("negSuffixPattern", "");

        int multiplier = fields.get("multiplier", 1);
        byte groupingSize = fields.get("groupingSize", (byte) 3);
        boolean decimalSeparatorAlwaysShown = fields.get(
                "decimalSeparatorAlwaysShown", false);
        boolean parseBigDecimal = fields.get("parseBigDecimal", false);
        symbols = (DecimalFormatSymbols) fields.get("symbols", null);

        boolean useExponentialNotation = fields.get("useExponentialNotation",
                false);
        byte minExponentDigits = fields.get("minExponentDigits", (byte) 0);

        int maximumIntegerDigits = fields.get("maximumIntegerDigits", 309);
        int minimumIntegerDigits = fields.get("minimumIntegerDigits", 309);
        int maximumFractionDigits = fields.get("maximumFractionDigits", 340);
        int minimumFractionDigits = fields.get("minimumFractionDigits", 340);
        this.serialVersionOnStream = fields.get("serialVersionOnStream", 0);

        Locale locale = (Locale) Format.getInternalField("locale", symbols);
        dform = new com.ibm.icu.text.DecimalFormat("",
                new com.ibm.icu.text.DecimalFormatSymbols(locale));
        setInternalField("useExponentialNotation", dform, Boolean
                .valueOf(useExponentialNotation));
        setInternalField("minExponentDigits", dform,
                new Byte(minExponentDigits));
        dform.setPositivePrefix(positivePrefix);
        dform.setPositiveSuffix(positiveSuffix);
        dform.setNegativePrefix(negativePrefix);
        dform.setNegativeSuffix(negativeSuffix);
        setInternalField("posPrefixPattern", dform, posPrefixPattern);
        setInternalField("posSuffixPattern", dform, posSuffixPattern);
        setInternalField("negPrefixPattern", dform, negPrefixPattern);
        setInternalField("negSuffixPattern", dform, negSuffixPattern);
        dform.setMultiplier(multiplier);
        dform.setGroupingSize(groupingSize);
        dform.setDecimalSeparatorAlwaysShown(decimalSeparatorAlwaysShown);
        dform.setMinimumIntegerDigits(minimumIntegerDigits);
        dform.setMaximumIntegerDigits(maximumIntegerDigits);
        dform.setMinimumFractionDigits(minimumFractionDigits);
        dform.setMaximumFractionDigits(maximumFractionDigits);
        this.setParseBigDecimal(parseBigDecimal);

        if (serialVersionOnStream < 3) {
            setMaximumIntegerDigits(super.getMaximumIntegerDigits());
            setMinimumIntegerDigits(super.getMinimumIntegerDigits());
            setMaximumFractionDigits(super.getMaximumFractionDigits());
            setMinimumFractionDigits(super.getMinimumFractionDigits());
        }
        if (serialVersionOnStream < 1) {
            this.setInternalField("useExponentialNotation", dform,
                    Boolean.FALSE);
        }
        serialVersionOnStream = 3;
    }

    /*
     * Copies decimal format symbols from text object to ICU one.
     * 
     * @param icu the object which receives the new values. @param dfs the
     * object which contains the new values.
     */
    private void copySymbols(final com.ibm.icu.text.DecimalFormatSymbols icu,
            final DecimalFormatSymbols dfs) {
        Currency currency = dfs.getCurrency();
        if (currency == null) {
            icu.setCurrency(com.ibm.icu.util.Currency.getInstance("XXX")); //$NON-NLS-1$
        } else {
            icu.setCurrency(com.ibm.icu.util.Currency.getInstance(dfs
                    .getCurrency().getCurrencyCode()));
        }
       
        icu.setCurrencySymbol(dfs.getCurrencySymbol());
        icu.setDecimalSeparator(dfs.getDecimalSeparator());
        icu.setDigit(dfs.getDigit());
        icu.setGroupingSeparator(dfs.getGroupingSeparator());
        icu.setInfinity(dfs.getInfinity());
        icu
                .setInternationalCurrencySymbol(dfs
                        .getInternationalCurrencySymbol());
        icu.setMinusSign(dfs.getMinusSign());
        icu.setMonetaryDecimalSeparator(dfs.getMonetaryDecimalSeparator());
        icu.setNaN(dfs.getNaN());
        icu.setPatternSeparator(dfs.getPatternSeparator());
        icu.setPercent(dfs.getPercent());
        icu.setPerMill(dfs.getPerMill());
        icu.setZeroDigit(dfs.getZeroDigit());
    }

    /*
     * Sets private field value by reflection.
     * 
     * @param fieldName the field name to be set @param target the object which
     * field to be set @param value the value to be set
     */
    private void setInternalField(final String fieldName, final Object target,
            final Object value) {
        AccessController
                .doPrivileged(new PrivilegedAction<java.lang.reflect.Field>() {
                    public java.lang.reflect.Field run() {
                        java.lang.reflect.Field field = null;
                        try {
                            field = target.getClass().getDeclaredField(
                                    fieldName);
                            field.setAccessible(true);
                            field.set(target, value);
                        } catch (Exception e) {
                            return null;
                        }
                        return field;
                    }
                });
    }
    
	/**
	 * Get the RoundingMode of this DecimalFormat
	 * 
	 * @return the RoundingMode
	 */
	public RoundingMode getRoundingMode() {
		// return the RoundingMode of this DecimalFormat
		return roundingMode;
	}

	/**
	 * Set the RoundingMode of this DecimalFormat
	 * 
	 * @param roundingMode
	 *            the given RoundingMode
	 */
	public void setRoundingMode(RoundingMode roundingMode) {
		if (roundingMode == null) {
			// when the given RoundingMode is null, throw NullPointerException
			throw new NullPointerException();
		}

		// update the RoundingMode with the given RoundingMode
		this.roundingMode = roundingMode;

		// update the RoundingMode of com.ibm.icu.text.DecimalFormat
		switch(roundingMode){
		case CEILING:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_CEILING);
			break;
		case DOWN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_DOWN);
			break;
		case UP:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_UP);
			break;
		case FLOOR:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_FLOOR);
			break;
		case HALF_DOWN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_DOWN);
			break;
		case HALF_UP:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_UP);
			break;
		case UNNECESSARY:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_UNNECESSARY);
			break;
		case HALF_EVEN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_EVEN);
			break;
		default:
			break;
		}
		
		// update the RoundingIncrement
		setRoundingIncrement(getMaximumFractionDigits());
	}

	/*
	 * Update the RoundingIncrement according to the given MaxFractionDigits
	 */
	private void setRoundingIncrement(int value) {
		int maxFractionDigits = value;
		// when value < 0, set maximumFractionDigits to 0 and update the
		// RoundingIncreament
		if (maxFractionDigits < 0) {
			maxFractionDigits = 0;
		}
		double roundingIncrement = 1.0 / Math.pow(10, maxFractionDigits);
		dform.setRoundingIncrement(roundingIncrement);
	}
}
