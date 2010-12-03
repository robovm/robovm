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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import org.apache.harmony.text.internal.nls.Messages;

/**
 * A concrete class for formatting and parsing dates in a locale-sensitive
 * manner. It allows for formatting (date to text), parsing (text to date) and
 * normalization.
 * <p>
 * {@code SimpleDateFormat} allows you to start by choosing any user-defined
 * patterns for date-time formatting. However, you are encouraged to create a
 * date-time formatter with either {@code getTimeInstance}, {@code
 * getDateInstance}, or {@code getDateTimeInstance} in {@code DateFormat}. Each
 * of these class methods can return a date/time formatter initialized with a
 * default format pattern. You may modify the format pattern using the {@code
 * applyPattern} methods as desired. For more information on using these
 * methods, see {@link DateFormat}.
 * <h4>Time Format Syntax</h4>
 * <p>
 * To specify the time format, use a <em>time pattern</em> string. In this
 * pattern, all ASCII letters are reserved as pattern letters, which are defined
 * as follows:
 * <table border=0 cellspacing=3 cellpadding=0>
 * <tr bgcolor="#ccccff">
 * <th>Symbol</th>
 * <th>Meaning</th>
 * <th>Presentation</th>
 * <th>Example</th>
 * </tr>
 * <tr valign=top>
 * <td>G</td>
 * <td>era designator</td>
 * <td>(Text)</td>
 * <td>AD</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>y</td>
 * <td>year</td>
 * <td>(Number)</td>
 * <td>1996</td>
 * </tr>
 * <tr valign=top>
 * <td>M</td>
 * <td>month in year</td>
 * <td>(Text &amp; Number)</td>
 * <td>July &amp; 07</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>d</td>
 * <td>day in month</td>
 * <td>(Number)</td>
 * <td>10</td>
 * </tr>
 * <tr valign=top>
 * <td>h</td>
 * <td>hour in am/pm (1&tilde;12)</td>
 * <td>(Number)</td>
 * <td>12</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>H</td>
 * <td>hour in day (0&tilde;23)</td>
 * <td>(Number)</td>
 * <td>0</td>
 * </tr>
 * <tr valign=top>
 * <td>m</td>
 * <td>minute in hour</td>
 * <td>(Number)</td>
 * <td>30</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>s</td>
 * <td>second in minute</td>
 * <td>(Number)</td>
 * <td>55</td>
 * </tr>
 * <tr valign=top>
 * <td>S</td>
 * <td>fractional second</td>
 * <td>(Number)</td>
 * <td>978</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>E</td>
 * <td>day of week</td>
 * <td>(Text)</td>
 * <td>Tuesday</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>D</td>
 * <td>day in year</td>
 * <td>(Number)</td>
 * <td>189</td>
 * </tr>
 * <tr valign=top>
 * <td>F</td>
 * <td>day of week in month</td>
 * <td>(Number)</td>
 * <td>2 (2nd Wed in July)</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>w</td>
 * <td>week in year</td>
 * <td>(Number)</td>
 * <td>27</td>
 * </tr>
 * <tr valign=top>
 * <td>W</td>
 * <td>week in month</td>
 * <td>(Number)</td>
 * <td>2</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>a</td>
 * <td>am/pm marker</td>
 * <td>(Text)</td>
 * <td>PM</td>
 * </tr>
 * <tr valign=top>
 * <td>k</td>
 * <td>hour in day (1&tilde;24)</td>
 * <td>(Number)</td>
 * <td>24</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>K</td>
 * <td>hour in am/pm (0&tilde;11)</td>
 * <td>(Number)</td>
 * <td>0</td>
 * </tr>
 * <tr valign=top>
 * <td>z</td>
 * <td>time zone</td>
 * <td>(Text)</td>
 * <td>Pacific Standard Time</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>Z</td>
 * <td>time zone (RFC 822)</td>
 * <td>(Number)</td>
 * <td>-0800</td>
 * </tr>
 * <tr valign=top>
 * <td>v</td>
 * <td>time zone (generic)</td>
 * <td>(Text)</td>
 * <td>Pacific Time</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>V</td>
 * <td>time zone (location)</td>
 * <td>(Text)</td>
 * <td>United States (Los Angeles)</td>
 * </tr>
 * <tr valign=top>
 * <td>'</td>
 * <td>escape for text</td>
 * <td>(Delimiter)</td>
 * <td>'Date='</td>
 * </tr>
 * <tr valign=top bgcolor="#eeeeff">
 * <td>''</td>
 * <td>single quote</td>
 * <td>(Literal)</td>
 * <td>'o''clock'</td>
 * </tr>
 * </table>
 * <p>
 * The count of pattern letters determines the format:
 * <p>
 * <strong>(Text)</strong>: 4 or more pattern letters &rarr; use the full form,
 * less than 4 pattern letters &rarr; use a short or abbreviated form if one
 * exists.
 * <p>
 * <strong>(Number)</strong>: the minimum number of digits. Shorter numbers are
 * zero-padded to this amount. Year is handled specially; that is, if the count
 * of 'y' is 2, the year will be truncated to 2 digits. (if "yyyy" produces
 * "1997", "yy" produces "97".) Unlike other fields, fractional seconds are
 * padded on the right with zero.
 * <p>
 * <strong>(Text & Number)</strong>: 3 or over, use text, otherwise use number.
 * <p>
 * Any characters in the pattern that are not in the ranges of ['a'..'z'] and
 * ['A'..'Z'] will be treated as quoted text. For instance, characters like ':',
 * '.', ' ', '#' and '@' will appear in the resulting time text even they are
 * not embraced within single quotes.
 * <p>
 * A pattern containing any invalid pattern letter will result in an exception
 * thrown during formatting or parsing.
 * <h4>Examples Using the US Locale</h4> <blockquote>
 * 
 * <pre>
 * Format Pattern                       Result
 * --------------                       -------
 * "yyyy.MM.dd G 'at' HH:mm:ss vvvv" &rarr;  1996.07.10 AD at 15:08:56 Pacific Time
 * "EEE, MMM d, ''yy"                &rarr;  Wed, July 10, '96
 * "h:mm a"                          &rarr;  12:08 PM
 * "hh 'o''clock' a, zzzz"           &rarr;  12 o'clock PM, Pacific Daylight Time
 * "K:mm a, vvv"                     &rarr;  0:00 PM, PT
 * "yyyyy.MMMMM.dd GGG hh:mm aaa"    &rarr;  01996.July.10 AD 12:08 PM
 * </pre>
 * 
 * </blockquote> <h4>Code Sample:</h4> <blockquote>
 * 
 * <pre>
 * SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, "PST");
 * pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
 * pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
 * 
 * // Format the current time.
 * SimpleDateFormat formatter = new SimpleDateFormat(
 *         "yyyy.MM.dd G 'at' hh:mm:ss a zzz");
 * Date currentTime_1 = new Date();
 * String dateString = formatter.format(currentTime_1);
 * 
 * // Parse the previous string back into a Date.
 * ParsePosition pos = new ParsePosition(0);
 * Date currentTime_2 = formatter.parse(dateString, pos);
 * </pre>
 * 
 * </blockquote>
 * <p>
 * In the example, the time value {@code currentTime_2} obtained from parsing
 * will be equal to {@code currentTime_1}. However, they may not be equal if the
 * am/pm marker 'a' is left out from the format pattern while the
 * "hour in am/pm" pattern symbol is used. This information loss can happen when
 * formatting the time in PM.
 * <p>
 * When parsing a date string using the abbreviated year pattern ("yy"), {@code
 * SimpleDateFormat} must interpret the abbreviated year relative to some
 * century. It does this by adjusting dates to be within 80 years before and 20
 * years after the time the {@code SimpleDateFormat} instance is created. For
 * example, using a pattern of "MM/dd/yy" and a {@code SimpleDateFormat}
 * instance created on Jan 1, 1997, the string "01/11/12" would be interpreted
 * as Jan 11, 2012 while the string "05/04/64" would be interpreted as May 4,
 * 1964. During parsing, only strings consisting of exactly two digits, as
 * defined by {@link java.lang.Character#isDigit(char)}, will be parsed into the
 * default century. Any other numeric string, such as a one digit string, a
 * three or more digit string, or a two digit string that isn't all digits (for
 * example, "-1"), is interpreted literally. So "01/02/3" or "01/02/003" are
 * parsed, using the same pattern, as Jan 2, 3 AD. Likewise, "01/02/-3" is
 * parsed as Jan 2, 4 BC.
 * <p>
 * If the year pattern does not have exactly two 'y' characters, the year is
 * interpreted literally, regardless of the number of digits. So using the
 * pattern "MM/dd/yyyy", "01/11/12" parses to Jan 11, 12 A.D.
 * <p>
 * When numeric fields are adjacent directly, with no intervening delimiter
 * characters, they constitute a run of adjacent numeric fields. Such runs are
 * parsed specially. For example, the format "HHmmss" parses the input text
 * "123456" to 12:34:56, parses the input text "12345" to 1:23:45, and fails to
 * parse "1234". In other words, the leftmost field of the run is flexible,
 * while the others keep a fixed width. If the parse fails anywhere in the run,
 * then the leftmost field is shortened by one character, and the entire run is
 * parsed again. This is repeated until either the parse succeeds or the
 * leftmost field is one character in length. If the parse still fails at that
 * point, the parse of the run fails.
 * <p>
 * For time zones that have no names, use the strings "GMT+hours:minutes" or
 * "GMT-hours:minutes".
 * <p>
 * The calendar defines the first day of the week, the first week of the year,
 * whether hours are zero based or not (0 vs. 12 or 24) and the time zone. There
 * is one common decimal format to handle all the numbers; the digit count is
 * handled programmatically according to the pattern.
 * <h4>Synchronization</h4> Date formats are not synchronized. It is recommended
 * to create separate format instances for each thread. If multiple threads
 * access a format concurrently, it must be synchronized externally.
 * 
 * @see Calendar
 * @see GregorianCalendar
 * @see java.util.TimeZone
 * @see DateFormat
 * @see DateFormatSymbols
 * @see DecimalFormat
 */
public class SimpleDateFormat extends DateFormat {

    private static final long serialVersionUID = 4774881970558875024L;

    private static final String patternChars = "GyMdkHmsSEDFwWahKzYeugAZvcLQqV"; //$NON-NLS-1$

    private String pattern;

    private DateFormatSymbols formatData;

    transient private int creationYear;

    private Date defaultCenturyStart;

    private transient String tzId;

    private transient com.ibm.icu.text.SimpleDateFormat icuFormat;

    /**
     * Constructs a new {@code SimpleDateFormat} for formatting and parsing
     * dates and times in the {@code SHORT} style for the default locale.
     */
    public SimpleDateFormat() {
        this(Locale.getDefault());
        icuFormat = new com.ibm.icu.text.SimpleDateFormat();
        icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        pattern = (String)getInternalField("pattern", icuFormat); //$NON-NLS-1$
        formatData = new DateFormatSymbols(Locale.getDefault());
    }

    /**
     * Constructs a new {@code SimpleDateFormat} using the specified
     * non-localized pattern and the {@code DateFormatSymbols} and {@code
     * Calendar} for the default locale.
     * 
     * @param pattern
     *            the pattern.
     * @throws NullPointerException
     *            if the pattern is {@code null}.
     * @throws IllegalArgumentException
     *            if {@code pattern} is not considered to be usable by this
     *            formatter.
     */
    public SimpleDateFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }
    
    /**
     * Validates the format character.
     *
     * @param format
     *            the format character
     *
     * @throws IllegalArgumentException
     *             when the format character is invalid
     */
    private void validateFormat(char format) {
        int index = patternChars.indexOf(format);
        if (index == -1) {
            // text.03=Unknown pattern character - '{0}'
            throw new IllegalArgumentException(Messages.getString(
                    "text.03", format)); //$NON-NLS-1$
        }
    }

    /**
     * Validates the pattern.
     *
     * @param template
     *            the pattern to validate.
     *
     * @throws NullPointerException
     *             if the pattern is null
     * @throws IllegalArgumentException
     *             if the pattern is invalid
     */
    private void validatePattern(String template) {
        boolean quote = false;
        int next, last = -1, count = 0;

        final int patternLength = template.length();
        for (int i = 0; i < patternLength; i++) {
            next = (template.charAt(i));
            if (next == '\'') {
                if (count > 0) {
                    validateFormat((char) last);
                    count = 0;
                }
                if (last == next) {
                    last = -1;
                } else {
                    last = next;
                }
                quote = !quote;
                continue;
            }
            if (!quote
                    && (last == next || (next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z'))) {
                if (last == next) {
                    count++;
                } else {
                    if (count > 0) {
                        validateFormat((char) last);
                    }
                    last = next;
                    count = 1;
                }
            } else {
                if (count > 0) {
                    validateFormat((char) last);
                    count = 0;
                }
                last = -1;
            }
        }
        if (count > 0) {
            validateFormat((char) last);
        }

        if (quote) {
            // text.04=Unterminated quote {0}
            throw new IllegalArgumentException(Messages.getString("text.04")); //$NON-NLS-1$
        }

    }
    
    /**
     * Constructs a new {@code SimpleDateFormat} using the specified
     * non-localized pattern and {@code DateFormatSymbols} and the {@code
     * Calendar} for the default locale.
     *
     * @param template
     *            the pattern.
     * @param value
     *            the DateFormatSymbols.
     * @throws NullPointerException
     *            if the pattern is {@code null}.
     * @throws IllegalArgumentException
     *            if the pattern is invalid.
     */
    public SimpleDateFormat(String template, DateFormatSymbols value) {
        this(Locale.getDefault());
        validatePattern(template);
        icuFormat = new com.ibm.icu.text.SimpleDateFormat(template, Locale.getDefault());
        icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        pattern = template;
        formatData = (DateFormatSymbols) value.clone();
    }

    private void copySymbols(DateFormatSymbols value, com.ibm.icu.text.DateFormatSymbols icuSymbols) {
        icuSymbols.setAmPmStrings(value.getAmPmStrings());
        icuSymbols.setEras(value.getEras());
        icuSymbols.setLocalPatternChars(value.getLocalPatternChars());
        icuSymbols.setMonths(value.getMonths());
        icuSymbols.setShortMonths(value.getShortMonths());
        icuSymbols.setShortWeekdays(value.getShortWeekdays());
        icuSymbols.setWeekdays(value.getWeekdays());
        icuSymbols.setZoneStrings(value.getZoneStrings());
    }
    
    /**
     * Constructs a new {@code SimpleDateFormat} using the specified
     * non-localized pattern and the {@code DateFormatSymbols} and {@code
     * Calendar} for the specified locale.
     * 
     * @param template
     *            the pattern.
     * @param locale
     *            the locale.
     * @throws NullPointerException
     *            if the pattern is {@code null}.
     * @throws IllegalArgumentException
     *            if the pattern is invalid.
     */
    public SimpleDateFormat(String template, Locale locale) {
        this(locale);
        validatePattern(template);
        icuFormat = new com.ibm.icu.text.SimpleDateFormat(template, locale);
        icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        pattern = template;
        formatData = new DateFormatSymbols(locale, icuFormat.getDateFormatSymbols());
    }

    SimpleDateFormat(Locale locale, com.ibm.icu.text.SimpleDateFormat icuFormat){
        this(locale);
        this.icuFormat = icuFormat;
        this.icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        pattern = (String)Format.getInternalField("pattern", icuFormat); //$NON-NLS-1$
        formatData = new DateFormatSymbols(locale);
    }
    
    private SimpleDateFormat(Locale locale) {
        numberFormat = NumberFormat.getInstance(locale);
        numberFormat.setParseIntegerOnly(true);
        numberFormat.setGroupingUsed(false);
        calendar = new GregorianCalendar(locale);
        calendar.add(Calendar.YEAR, -80);
        tzId = calendar.getTimeZone().getID();
        creationYear = calendar.get(Calendar.YEAR);
        defaultCenturyStart = calendar.getTime();
    }

    /**
     * Changes the pattern of this simple date format to the specified pattern
     * which uses localized pattern characters.
     * 
     * @param template
     *            the localized pattern.
     */
    public void applyLocalizedPattern(String template) {
        icuFormat.applyLocalizedPattern(template);
        pattern = icuFormat.toPattern();
    }

    /**
     * Changes the pattern of this simple date format to the specified pattern
     * which uses non-localized pattern characters.
     * 
     * @param template
     *            the non-localized pattern.
     * @throws NullPointerException
     *                if the pattern is {@code null}.
     * @throws IllegalArgumentException
     *                if the pattern is invalid.
     */
    public void applyPattern(String template) {
        validatePattern(template);
        /*
         * ICU spec explicitly mentions that "ICU interprets a single 'y'
         * differently than Java." We need to do a trick here to follow Java
         * spec.
         */
        String templateForICU = patternForICU(template);
        icuFormat.applyPattern(templateForICU);
        pattern = template;
    }

    /**
     * Converts the Java-spec pattern into an equivalent pattern used by ICU.
     * 
     * @param p
     *            the Java-spec style pattern.
     * @return the ICU-style pattern.
     */
    @SuppressWarnings("nls")
    private String patternForICU(String p) {
        String[] subPatterns = p.split("'");
        boolean quote = false;
        boolean first = true;
        StringBuilder result = new StringBuilder();
        for (String subPattern : subPatterns) {
            if (!quote) {
                // replace 'y' with 'yy' for ICU to follow Java spec
                result.append((first ? "" : "'")
                        + subPattern.replaceAll("(?<!y)y(?!y)", "yy"));
                first = false;
            } else {
                result.append("'" + subPattern);
            }
            quote = !quote;
        }
        if (p.endsWith("'")) {
            result.append("'");
        }
        return result.toString();
    }

    /**
     * Returns a new {@code SimpleDateFormat} with the same pattern and
     * properties as this simple date format.
     * 
     * @return a shallow copy of this simple date format.
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        SimpleDateFormat clone = (SimpleDateFormat) super.clone();
        clone.formatData = (DateFormatSymbols) formatData.clone();
        clone.defaultCenturyStart = new Date(defaultCenturyStart.getTime());
        clone.tzId = tzId;
        return clone;
    }

    /**
     * Compares the specified object with this simple date format and indicates
     * if they are equal. In order to be equal, {@code object} must be an
     * instance of {@code SimpleDateFormat} and have the same {@code DateFormat}
     * properties, pattern, {@code DateFormatSymbols} and creation year.
     * 
     * @param object
     *            the object to compare with this object.
     * @return {@code true} if the specified object is equal to this simple date
     *         format; {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SimpleDateFormat)) {
            return false;
        }
        SimpleDateFormat simple = (SimpleDateFormat) object;
        return super.equals(object) && pattern.equals(simple.pattern)
                && formatData.equals(simple.formatData);
    }

    /**
     * Formats the specified object using the rules of this simple date format
     * and returns an {@code AttributedCharacterIterator} with the formatted
     * date and attributes.
     * 
     * @param object
     *            the object to format.
     * @return an {@code AttributedCharacterIterator} with the formatted date
     *         and attributes.
     * @throws NullPointerException
     *            if the object is {@code null}.
     * @throws IllegalArgumentException
     *            if the object cannot be formatted by this simple date
     *            format.
     */
    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        if (object instanceof Date) {
            return formatToCharacterIteratorImpl((Date) object);
        }
        if (object instanceof Number) {
            return formatToCharacterIteratorImpl(new Date(((Number) object)
                    .longValue()));
        }
        throw new IllegalArgumentException();
        
    }
    
    private AttributedCharacterIterator formatToCharacterIteratorImpl(Date date) {
        StringBuffer buffer = new StringBuffer();
        Vector<FieldPosition> fields = new Vector<FieldPosition>();

        // format the date, and find fields
        formatImpl(date, buffer, null, fields);

        // create and AttributedString with the formatted buffer
        AttributedString as = new AttributedString(buffer.toString());

        // add DateFormat field attributes to the AttributedString
        for (int i = 0; i < fields.size(); i++) {
            FieldPosition pos = fields.elementAt(i);
            Format.Field attribute = pos.getFieldAttribute();
            as.addAttribute(attribute, attribute, pos.getBeginIndex(), pos
                    .getEndIndex());
        }

        // return the CharacterIterator from AttributedString
        return as.getIterator();
    }

    /**
     * Formats the date.
     * <p>
     * If the FieldPosition {@code field} is not null, and the field
     * specified by this FieldPosition is formatted, set the begin and end index
     * of the formatted field in the FieldPosition.
     * <p>
     * If the Vector {@code fields} is not null, find fields of this
     * date, set FieldPositions with these fields, and add them to the fields
     * vector.
     * 
     * @param date
     *            Date to Format
     * @param buffer
     *            StringBuffer to store the resulting formatted String
     * @param field
     *            FieldPosition to set begin and end index of the field
     *            specified, if it is part of the format for this date
     * @param fields
     *            Vector used to store the FieldPositions for each field in this
     *            date
     * @return the formatted Date
     * @throws IllegalArgumentException
     *            if the object cannot be formatted by this Format.
     */
    private StringBuffer formatImpl(Date date, StringBuffer buffer,
            FieldPosition field, Vector<FieldPosition> fields) {

        boolean quote = false;
        int next, last = -1, count = 0;
        calendar.setTime(date);
        if (field != null) {
            field.clear();
        }

        final int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            next = (pattern.charAt(i));
            if (next == '\'') {
                if (count > 0) {
                    append(buffer, field, fields, (char) last, count);
                    count = 0;
                }
                if (last == next) {
                    buffer.append('\'');
                    last = -1;
                } else {
                    last = next;
                }
                quote = !quote;
                continue;
            }
            if (!quote
                    && (last == next || (next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z'))) {
                if (last == next) {
                    count++;
                } else {
                    if (count > 0) {
                        append(buffer, field, fields, (char) last, count);
                    }
                    last = next;
                    count = 1;
                }
            } else {
                if (count > 0) {
                    append(buffer, field, fields, (char) last, count);
                    count = 0;
                }
                last = -1;
                buffer.append((char) next);
            }
        }
        if (count > 0) {
            append(buffer, field, fields, (char) last, count);
        }
        return buffer;
    }
    
    private void append(StringBuffer buffer, FieldPosition position,
            Vector<FieldPosition> fields, char format, int count) {
        int field = -1;
        int index = patternChars.indexOf(format);
        if (index == -1) {
            // text.03=Unknown pattern character - '{0}'
            throw new IllegalArgumentException(Messages.getString(
                    "text.03", format)); //$NON-NLS-1$
        }

        int beginPosition = buffer.length();
        Field dateFormatField = null;
        switch (index) {
            case ERA_FIELD:
                dateFormatField = Field.ERA;
                buffer.append(formatData.eras[calendar.get(Calendar.ERA)]);
                break;
            case YEAR_FIELD:
                dateFormatField = Field.YEAR;
                int year = calendar.get(Calendar.YEAR);
                if (count < 4) {
                    appendNumber(buffer, 2, year % 100);
                } else {
                    appendNumber(buffer, count, year);
                }
                break;
            case MONTH_FIELD:
                dateFormatField = Field.MONTH;
                int month = calendar.get(Calendar.MONTH);
                if (count <= 2) {
                    appendNumber(buffer, count, month + 1);
                } else if (count == 3) {
                    buffer.append(formatData.shortMonths[month]);
                } else {
                    buffer.append(formatData.months[month]);
                }
                break;
            case DATE_FIELD:
                dateFormatField = Field.DAY_OF_MONTH;
                field = Calendar.DATE;
                break;
            case HOUR_OF_DAY1_FIELD: // k
                dateFormatField = Field.HOUR_OF_DAY1;
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                appendNumber(buffer, count, hour == 0 ? 24 : hour);
                break;
            case HOUR_OF_DAY0_FIELD: // H
                dateFormatField = Field.HOUR_OF_DAY0;
                field = Calendar.HOUR_OF_DAY;
                break;
            case MINUTE_FIELD:
                dateFormatField = Field.MINUTE;
                field = Calendar.MINUTE;
                break;
            case SECOND_FIELD:
                dateFormatField = Field.SECOND;
                field = Calendar.SECOND;
                break;
            case MILLISECOND_FIELD:
                dateFormatField = Field.MILLISECOND;
                int value = calendar.get(Calendar.MILLISECOND);
                appendNumber(buffer, count, value);
                break;
            case DAY_OF_WEEK_FIELD:
                dateFormatField = Field.DAY_OF_WEEK;
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                if (count < 4) {
                    buffer.append(formatData.shortWeekdays[day]);
                } else {
                    buffer.append(formatData.weekdays[day]);
                }
                break;
            case DAY_OF_YEAR_FIELD:
                dateFormatField = Field.DAY_OF_YEAR;
                field = Calendar.DAY_OF_YEAR;
                break;
            case DAY_OF_WEEK_IN_MONTH_FIELD:
                dateFormatField = Field.DAY_OF_WEEK_IN_MONTH;
                field = Calendar.DAY_OF_WEEK_IN_MONTH;
                break;
            case WEEK_OF_YEAR_FIELD:
                dateFormatField = Field.WEEK_OF_YEAR;
                field = Calendar.WEEK_OF_YEAR;
                break;
            case WEEK_OF_MONTH_FIELD:
                dateFormatField = Field.WEEK_OF_MONTH;
                field = Calendar.WEEK_OF_MONTH;
                break;
            case AM_PM_FIELD:
                dateFormatField = Field.AM_PM;
                buffer.append(formatData.ampms[calendar.get(Calendar.AM_PM)]);
                break;
            case HOUR1_FIELD: // h
                dateFormatField = Field.HOUR1;
                hour = calendar.get(Calendar.HOUR);
                appendNumber(buffer, count, hour == 0 ? 12 : hour);
                break;
            case HOUR0_FIELD: // K
                dateFormatField = Field.HOUR0;
                field = Calendar.HOUR;
                break;
            case TIMEZONE_FIELD: // z
                dateFormatField = Field.TIME_ZONE;
                appendTimeZone(buffer, count, true);
                break;
            case com.ibm.icu.text.DateFormat.TIMEZONE_RFC_FIELD: // Z
                dateFormatField = Field.TIME_ZONE;
                appendTimeZone(buffer, count, false);
                break;
        }
        if (field != -1) {
            appendNumber(buffer, count, calendar.get(field));
        }

        if (fields != null) {
            position = new FieldPosition(dateFormatField);
            position.setBeginIndex(beginPosition);
            position.setEndIndex(buffer.length());
            fields.add(position);
        } else {
            // Set to the first occurrence
            if ((position.getFieldAttribute() == dateFormatField || (position
                    .getFieldAttribute() == null && position.getField() == index))
                    && position.getEndIndex() == 0) {
                position.setBeginIndex(beginPosition);
                position.setEndIndex(buffer.length());
            }
        }
    }
    
    private void appendTimeZone(StringBuffer buffer, int count,
            boolean generalTimezone) {
        // cannot call TimeZone.getDisplayName() because it would not use
        // the DateFormatSymbols of this SimpleDateFormat

        if (generalTimezone) {
            String id = calendar.getTimeZone().getID();
            String[][] zones = formatData.getZoneStrings();
            String[] zone = null;
            for (String[] element : zones) {
                if (id.equals(element[0])) {
                    zone = element;
                    break;
                }
            }
            if (zone == null) {
                int offset = calendar.get(Calendar.ZONE_OFFSET)
                        + calendar.get(Calendar.DST_OFFSET);
                char sign = '+';
                if (offset < 0) {
                    sign = '-';
                    offset = -offset;
                }
                buffer.append("GMT"); //$NON-NLS-1$
                buffer.append(sign);
                appendNumber(buffer, 2, offset / 3600000);
                buffer.append(':');
                appendNumber(buffer, 2, (offset % 3600000) / 60000);
            } else {
                int daylight = calendar.get(Calendar.DST_OFFSET) == 0 ? 0 : 2;
                if (count < 4) {
                    buffer.append(zone[2 + daylight]);
                } else {
                    buffer.append(zone[1 + daylight]);
                }
            }
        } else {
            int offset = calendar.get(Calendar.ZONE_OFFSET)
                    + calendar.get(Calendar.DST_OFFSET);
            char sign = '+';
            if (offset < 0) {
                sign = '-';
                offset = -offset;
            }
            buffer.append(sign);
            appendNumber(buffer, 2, offset / 3600000);
            appendNumber(buffer, 2, (offset % 3600000) / 60000);
        }
    }

    private void appendNumber(StringBuffer buffer, int count, int value) {
        int minimumIntegerDigits = numberFormat.getMinimumIntegerDigits();
        numberFormat.setMinimumIntegerDigits(count);
        numberFormat.format(new Integer(value), buffer, new FieldPosition(0));
        numberFormat.setMinimumIntegerDigits(minimumIntegerDigits);
    }
    

    /**
     * Formats the specified date as a string using the pattern of this date
     * format and appends the string to the specified string buffer.
     * <p>
     * If the {@code field} member of {@code field} contains a value specifying
     * a format field, then its {@code beginIndex} and {@code endIndex} members
     * will be updated with the position of the first occurrence of this field
     * in the formatted text.
     *
     * @param date
     *            the date to format.
     * @param buffer
     *            the target string buffer to append the formatted date/time to.
     * @param fieldPos
     *            on input: an optional alignment field; on output: the offsets
     *            of the alignment field in the formatted text.
     * @return the string buffer.
     * @throws IllegalArgumentException
     *             if there are invalid characters in the pattern.
     */
    @Override
    public StringBuffer format(Date date, StringBuffer buffer,
            FieldPosition fieldPos) {
        String id = calendar.getTimeZone().getID();
        if (!tzId.equals(id)) {
            tzId = id;
            icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        }
        // As ICU has its own implementation for DateFormat.Field, we need to
        // pass an ICU instance of DateFormat.Field to the FieldPosition to get
        // the begin and end index.
        StringBuffer result = null;
        Format.Field attribute = fieldPos.getFieldAttribute();
        if (attribute instanceof DateFormat.Field) {
            com.ibm.icu.text.DateFormat.Field icuAttribute = toICUField((DateFormat.Field) attribute);
            int field = fieldPos.getField();
            FieldPosition icuFieldPos = new FieldPosition(icuAttribute, field);
            result = icuFormat.format(date, buffer, icuFieldPos);
            fieldPos.setBeginIndex(icuFieldPos.getBeginIndex());
            fieldPos.setEndIndex(icuFieldPos.getEndIndex());
            return result;
        }
        return icuFormat.format(date, buffer, fieldPos);
    }

    /**
     * Maps the Java-spec date format field to the equivalent field in ICU.
     * 
     * @param attribute
     *            the Java-spec definition of a date format field.
     * @return the ICU definition of the same date format field.
     */
    private com.ibm.icu.text.DateFormat.Field toICUField(
            DateFormat.Field attribute) {
        com.ibm.icu.text.DateFormat.Field icuAttribute = null;

        if (attribute == DateFormat.Field.ERA) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.ERA;
        } else if (attribute == DateFormat.Field.YEAR) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.YEAR;
        } else if (attribute == DateFormat.Field.MONTH) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.MONTH;
        } else if (attribute == DateFormat.Field.HOUR_OF_DAY0) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.HOUR_OF_DAY0;
        } else if (attribute == DateFormat.Field.HOUR_OF_DAY1) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.HOUR_OF_DAY1;
        } else if (attribute == DateFormat.Field.MINUTE) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.MINUTE;
        } else if (attribute == DateFormat.Field.SECOND) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.SECOND;
        } else if (attribute == DateFormat.Field.MILLISECOND) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.MILLISECOND;
        } else if (attribute == DateFormat.Field.DAY_OF_WEEK) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.DAY_OF_WEEK;
        } else if (attribute == DateFormat.Field.DAY_OF_MONTH) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.DAY_OF_MONTH;
        } else if (attribute == DateFormat.Field.DAY_OF_YEAR) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.DAY_OF_YEAR;
        } else if (attribute == DateFormat.Field.DAY_OF_WEEK_IN_MONTH) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.DAY_OF_WEEK_IN_MONTH;
        } else if (attribute == DateFormat.Field.WEEK_OF_YEAR) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.WEEK_OF_YEAR;
        } else if (attribute == DateFormat.Field.WEEK_OF_MONTH) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.WEEK_OF_MONTH;
        } else if (attribute == DateFormat.Field.AM_PM) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.AM_PM;
        } else if (attribute == DateFormat.Field.HOUR0) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.HOUR0;
        } else if (attribute == DateFormat.Field.HOUR1) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.HOUR1;
        } else if (attribute == DateFormat.Field.TIME_ZONE) {
            icuAttribute = com.ibm.icu.text.DateFormat.Field.TIME_ZONE;
        }

        return icuAttribute;
    }

    /**
     * Answers the Date which is the start of the one hundred year period for
     * two digits year values.
     * 
     * @return a Date
     */
    public Date get2DigitYearStart() {
        return defaultCenturyStart;
    }

    /**
     * Returns the {@code DateFormatSymbols} used by this simple date format.
     *
     * @return the {@code DateFormatSymbols} object.
     */
    public DateFormatSymbols getDateFormatSymbols() {
        // Return a clone so the arrays in the ResourceBundle are not modified
        return (DateFormatSymbols) formatData.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + pattern.hashCode() + formatData.hashCode()
                + creationYear;
    }

    /**
     * Parses a date from the specified string starting at the index specified
     * by {@code position}. If the string is successfully parsed then the index
     * of the {@code ParsePosition} is updated to the index following the parsed
     * text. On error, the index is unchanged and the error index of {@code
     * ParsePosition} is set to the index where the error occurred.
     *
     * @param string
     *            the string to parse using the pattern of this simple date
     *            format.
     * @param position
     *            input/output parameter, specifies the start index in {@code
     *            string} from where to start parsing. If parsing is successful,
     *            it is updated with the index following the parsed text; on
     *            error, the index is unchanged and the error index is set to
     *            the index where the error occurred.
     * @return the date resulting from the parse, or {@code null} if there is an
     *         error.
     * @throws IllegalArgumentException
     *             if there are invalid characters in the pattern.
     */
    @Override
    public Date parse(String string, ParsePosition position) {
        String id = calendar.getTimeZone().getID();
        if (!tzId.equals(id)) {
            tzId = id;
            icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(tzId));
        }
        icuFormat.setLenient(calendar.isLenient());
        return icuFormat.parse(string,position);
    }

    /**
     * Sets the date which is the start of the one hundred year period for two
     * digits year values.
     * 
     * @param date
     *            the new date.
     */
    public void set2DigitYearStart(Date date) {
        icuFormat.set2DigitYearStart(date);
        defaultCenturyStart = date;
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        creationYear = cal.get(Calendar.YEAR);
    }

    /**
     * Sets the {@code DateFormatSymbols} used by this simple date format.
     * 
     * @param value
     *            the new {@code DateFormatSymbols} object.
     */
    public void setDateFormatSymbols(DateFormatSymbols value) {
        com.ibm.icu.text.DateFormatSymbols icuSymbols = new com.ibm.icu.text.DateFormatSymbols();
        copySymbols(value, icuSymbols);
        icuFormat.setDateFormatSymbols(icuSymbols);
        formatData = (DateFormatSymbols) value.clone();
    }

    /**
     * Returns the pattern of this simple date format using localized pattern
     * characters.
     * 
     * @return the localized pattern.
     */
    public String toLocalizedPattern() {
        return icuFormat.toLocalizedPattern();
    }

    /**
     * Returns the pattern of this simple date format using non-localized
     * pattern characters.
     * 
     * @return the non-localized pattern.
     */
    public String toPattern() {
        return pattern;
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("defaultCenturyStart", Date.class), //$NON-NLS-1$
            new ObjectStreamField("formatData", DateFormatSymbols.class), //$NON-NLS-1$
            new ObjectStreamField("pattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("serialVersionOnStream", Integer.TYPE), }; //$NON-NLS-1$

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("defaultCenturyStart", defaultCenturyStart); //$NON-NLS-1$
        fields.put("formatData", formatData); //$NON-NLS-1$
        fields.put("pattern", pattern); //$NON-NLS-1$
        fields.put("serialVersionOnStream", 1); //$NON-NLS-1$
        stream.writeFields();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        int version = fields.get("serialVersionOnStream", 0); //$NON-NLS-1$
        Date date;
        if (version > 0) {
            date = (Date) fields.get("defaultCenturyStart", new Date()); //$NON-NLS-1$
        } else {
            date = new Date();
        }
        set2DigitYearStart(date);
        formatData = (DateFormatSymbols) fields.get("formatData", null); //$NON-NLS-1$
        pattern = (String) fields.get("pattern", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
