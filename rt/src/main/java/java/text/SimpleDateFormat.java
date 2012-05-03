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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import libcore.icu.LocaleData;
import libcore.icu.TimeZones;

/**
 * A concrete class for formatting and parsing dates in a locale-sensitive
 * manner. Formatting turns a {@link Date} into a {@link String}, and parsing turns a
 * {@code String} into a {@code Date}.
 *
 * <h4>Time Pattern Syntax</h4>
 * <p>You can supply a pattern describing what strings are produced/accepted, but almost all
 * callers should use {@link DateFormat#getDateInstance}, {@link DateFormat#getDateTimeInstance},
 * or {@link DateFormat#getTimeInstance} to get a ready-made instance suitable for the user's
 * locale.
 *
 * <p>The main reason you'd create an instance this class directly is because you need to
 * format/parse a specific machine-readable format, in which case you almost certainly want
 * to explicitly ask for {@link Locale#US} to ensure that you get ASCII digits (rather than,
 * say, Arabic digits).
 * (See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".)
 * The most useful non-localized pattern is {@code "yyyy-MM-dd HH:mm:ss.SSSZ"}, which corresponds
 * to the ISO 8601 international standard date format.
 *
 * <p>To specify the time format, use a <i>time pattern</i> string. In this
 * string, any character from {@code 'A'} to {@code 'Z'} or {@code 'a'} to {@code 'z'} is
 * treated specially. All other characters are passed through verbatim. The interpretation of each
 * of the ASCII letters is given in the table below. ASCII letters not appearing in the table are
 * reserved for future use, and it is an error to attempt to use them.
 *
 * <p><table BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 * <tr BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *      <td><B>Symbol</B></td> <td><B>Meaning</B></td> <td><B>Presentation</B></td> <td><B>Example</B></td> </tr>
 * <tr> <td>{@code D}</td> <td>day in year</td>             <td>(Number)</td>      <td>189</td> </tr>
 * <tr> <td>{@code E}</td> <td>day of week</td>             <td>(Text)</td>        <td>Tuesday</td> </tr>
 * <tr> <td>{@code F}</td> <td>day of week in month</td>    <td>(Number)</td>      <td>2 <i>(2nd Wed in July)</i></td> </tr>
 * <tr> <td>{@code G}</td> <td>era designator</td>          <td>(Text)</td>        <td>AD</td> </tr>
 * <tr> <td>{@code H}</td> <td>hour in day (0-23)</td>      <td>(Number)</td>      <td>0</td> </tr>
 * <tr> <td>{@code K}</td> <td>hour in am/pm (0-11)</td>    <td>(Number)</td>      <td>0</td> </tr>
 * <tr> <td>{@code L}</td> <td>stand-alone month</td>       <td>(Text/Number)</td> <td>July / 07</td> </tr>
 * <tr> <td>{@code M}</td> <td>month in year</td>           <td>(Text/Number)</td> <td>July / 07</td> </tr>
 * <tr> <td>{@code S}</td> <td>fractional seconds</td>      <td>(Number)</td>      <td>978</td> </tr>
 * <tr> <td>{@code W}</td> <td>week in month</td>           <td>(Number)</td>      <td>2</td> </tr>
 * <tr> <td>{@code Z}</td> <td>time zone (RFC 822)</td>     <td>(Timezone)</td>    <td>-0800</td> </tr>
 * <tr> <td>{@code a}</td> <td>am/pm marker</td>            <td>(Text)</td>        <td>PM</td> </tr>
 * <tr> <td>{@code c}</td> <td>stand-alone day of week</td> <td>(Text/Number)</td> <td>Tuesday / 2</td> </tr>
 * <tr> <td>{@code d}</td> <td>day in month</td>            <td>(Number)</td>      <td>10</td> </tr>
 * <tr> <td>{@code h}</td> <td>hour in am/pm (1-12)</td>    <td>(Number)</td>      <td>12</td> </tr>
 * <tr> <td>{@code k}</td> <td>hour in day (1-24)</td>      <td>(Number)</td>      <td>24</td> </tr>
 * <tr> <td>{@code m}</td> <td>minute in hour</td>          <td>(Number)</td>      <td>30</td> </tr>
 * <tr> <td>{@code s}</td> <td>second in minute</td>        <td>(Number)</td>      <td>55</td> </tr>
 * <tr> <td>{@code w}</td> <td>week in year</td>            <td>(Number)</td>      <td>27</td> </tr>
 * <tr> <td>{@code y}</td> <td>year</td>                    <td>(Number)</td>      <td>2010</td> </tr>
 * <tr> <td>{@code z}</td> <td>time zone</td>               <td>(Timezone)</td>    <td>Pacific Standard Time</td> </tr>
 * <tr> <td>{@code '}</td> <td>escape for text</td>         <td>(Delimiter)</td>   <td>'Date='</td> </tr>
 * <tr> <td>{@code ''}</td> <td>single quote</td>           <td>(Literal)</td>     <td>'o''clock'</td> </tr>
 * </table>
 *
 * <p>The number of consecutive copies (the "count") of a pattern character further influences
 * the format.
 * <ul>
 * <li><b>Text</b> if the count is 4 or more, use the full form; otherwise use a short or
 * abbreviated form if one exists. So {@code zzzz} might give {@code Pacific Standard Time}
 * whereas {@code z} might give {@code PST}. Note that the count does <i>not</i> specify the
 * exact width of the field.
 *
 * <li><b>Number</b> the count is the minimum number of digits. Shorter values are
 * zero-padded to this width, longer values overflow this width.
 *
 * <p>Years are handled specially: {@code yy} truncates to the last 2 digits, but any
 * other number of consecutive {@code y}s does not truncate. So where {@code yyyy} or
 * {@code y} might give {@code 2010}, {@code yy} would give {@code 10}.
 *
 * <p>Fractional seconds are also handled specially: they're zero-padded on the
 * <i>right</i>.
 *
 * <li><b>Text/Number</b>: if the count is 3 or more, use text; otherwise use a number.
 * So {@code MM} might give {@code 07} while {@code MMM} gives {@code July}.
 * </ul>
 *
 * <p>The two pattern characters {@code L} and {@code c} are ICU-compatible extensions, not
 * available in the RI. These are necessary for correct localization in languages such as Russian
 * that distinguish between, say, "June" and "June 2010".
 *
 * <p>When numeric fields are adjacent directly, with no intervening delimiter
 * characters, they constitute a run of adjacent numeric fields. Such runs are
 * parsed specially. For example, the format "HHmmss" parses the input text
 * "123456" to 12:34:56, parses the input text "12345" to 1:23:45, and fails to
 * parse "1234". In other words, the leftmost field of the run is flexible,
 * while the others keep a fixed width. If the parse fails anywhere in the run,
 * then the leftmost field is shortened by one character, and the entire run is
 * parsed again. This is repeated until either the parse succeeds or the
 * leftmost field is one character in length. If the parse still fails at that
 * point, the parse of the run fails.
 *
 * <p>See {@link #set2DigitYearStart} for more about handling two-digit years.
 *
 * <h4>Sample Code</h4>
 * <p>If you're formatting for human use, you should use an instance returned from
 * {@link DateFormat} as described above. This code:
 * <pre>
 * DateFormat[] formats = new DateFormat[] {
 *   DateFormat.getDateInstance(),
 *   DateFormat.getDateTimeInstance(),
 *   DateFormat.getTimeInstance(),
 * };
 * for (DateFormat df : formats) {
 *   System.err.println(df.format(new Date(0)));
 * }
 * </pre>
 *
 * <p>Produces this output when run on an {@code en_US} device in the PDT time zone:
 * <pre>
 * Dec 31, 1969
 * Dec 31, 1969 4:00:00 PM
 * 4:00:00 PM
 * </pre>
 * And will produce similarly appropriate localized human-readable output on any user's system.
 *
 * <p>If you're formatting for machine use, consider this code:
 * <pre>
 * String[] formats = new String[] {
 *   "yyyy-MM-dd",
 *   "yyyy-MM-dd HH:mm",
 *   "yyyy-MM-dd HH:mmZ",
 *   "yyyy-MM-dd HH:mm:ss.SSSZ",
 *   "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
 * };
 * for (String format : formats) {
 *   SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
 *   System.err.format("%30s %s\n", format, sdf.format(new Date(0)));
 *   sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
 *   System.err.format("%30s %s\n", format, sdf.format(new Date(0)));
 * }
 * </pre>
 *
 * <p>Which produces this output when run in the PDT time zone:
 * <pre>
 *                     yyyy-MM-dd 1969-12-31
 *                     yyyy-MM-dd 1970-01-01
 *               yyyy-MM-dd HH:mm 1969-12-31 16:00
 *               yyyy-MM-dd HH:mm 1970-01-01 00:00
 *              yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
 *              yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
 *       yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
 *       yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000
 * </pre>
 *
 * <p>As this example shows, each {@code SimpleDateFormat} instance has a {@link TimeZone}.
 * This is because it's called upon to format instances of {@code Date}, which represents an
 * absolute time in UTC. That is, {@code Date} does not carry time zone information.
 * By default, {@code SimpleDateFormat} will use the system's default time zone. This is
 * appropriate for human-readable output (for which, see the previous sample instead), but
 * generally inappropriate for machine-readable output, where ambiguity is a problem. Note that
 * in this example, the output that included a time but no time zone cannot be parsed back into
 * the original {@code Date}. For this
 * reason it is almost always necessary and desirable to include the timezone in the output.
 * It may also be desirable to set the formatter's time zone to UTC (to ease comparison, or to
 * make logs more readable, for example).
 *
 * <h4>Synchronization</h4>
 * {@code SimpleDateFormat} is not thread-safe. Users should create a separate instance for
 * each thread.
 *
 * @see java.util.Calendar
 * @see java.util.Date
 * @see java.util.TimeZone
 * @see java.text.DateFormat
 */
public class SimpleDateFormat extends DateFormat {

    private static final long serialVersionUID = 4774881970558875024L;

    // 'L' and 'c' are ICU-compatible extensions for stand-alone month and stand-alone weekday.
    static final String PATTERN_CHARS = "GyMdkHmsSEDFwWahKzZLc";

    // The index of 'Z' in the PATTERN_CHARS string. This pattern character is supported by the RI,
    // but has no corresponding public constant.
    private static final int RFC_822_TIMEZONE_FIELD = 18;

    // The index of 'L' (cf. 'M') in the PATTERN_CHARS string. This is an ICU-compatible extension
    // necessary for correct localization in various languages (http://b/2633414).
    private static final int STAND_ALONE_MONTH_FIELD = 19;
    // The index of 'c' (cf. 'E') in the PATTERN_CHARS string. This is an ICU-compatible extension
    // necessary for correct localization in various languages (http://b/2633414).
    private static final int STAND_ALONE_DAY_OF_WEEK_FIELD = 20;

    private String pattern;

    private DateFormatSymbols formatData;

    transient private int creationYear;

    private Date defaultCenturyStart;

    /**
     * Constructs a new {@code SimpleDateFormat} for formatting and parsing
     * dates and times in the {@code SHORT} style for the user's default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     */
    public SimpleDateFormat() {
        this(Locale.getDefault());
        this.pattern = defaultPattern();
        this.formatData = new DateFormatSymbols(Locale.getDefault());
    }

    /**
     * Constructs a new {@code SimpleDateFormat} using the specified
     * non-localized pattern and the {@code DateFormatSymbols} and {@code
     * Calendar} for the user's default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
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
        int index = PATTERN_CHARS.indexOf(format);
        if (index == -1) {
            throw new IllegalArgumentException("Unknown pattern character '" + format + "'");
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
            throw new IllegalArgumentException("Unterminated quote");
        }
    }

    /**
     * Constructs a new {@code SimpleDateFormat} using the specified
     * non-localized pattern and {@code DateFormatSymbols} and the {@code
     * Calendar} for the user's default locale.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
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
        pattern = template;
        formatData = (DateFormatSymbols) value.clone();
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
        pattern = template;
        formatData = new DateFormatSymbols(locale);
    }

    private SimpleDateFormat(Locale locale) {
        numberFormat = NumberFormat.getInstance(locale);
        numberFormat.setParseIntegerOnly(true);
        numberFormat.setGroupingUsed(false);
        calendar = new GregorianCalendar(locale);
        calendar.add(Calendar.YEAR, -80);
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
        pattern = convertPattern(template, formatData.getLocalPatternChars(), PATTERN_CHARS, true);
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
        pattern = template;
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
        return clone;
    }

    private static String defaultPattern() {
        LocaleData localeData = LocaleData.get(Locale.getDefault());
        return localeData.getDateFormat(SHORT) + " " + localeData.getTimeFormat(SHORT);
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
            return formatToCharacterIteratorImpl(new Date(((Number) object).longValue()));
        }
        throw new IllegalArgumentException();

    }

    private AttributedCharacterIterator formatToCharacterIteratorImpl(Date date) {
        StringBuffer buffer = new StringBuffer();
        ArrayList<FieldPosition> fields = new ArrayList<FieldPosition>();

        // format the date, and find fields
        formatImpl(date, buffer, null, fields);

        // create and AttributedString with the formatted buffer
        AttributedString as = new AttributedString(buffer.toString());

        // add DateFormat field attributes to the AttributedString
        for (FieldPosition pos : fields) {
            Format.Field attribute = pos.getFieldAttribute();
            as.addAttribute(attribute, attribute, pos.getBeginIndex(), pos.getEndIndex());
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
     * If the list {@code fields} is not null, find fields of this
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
     *            list used to store the FieldPositions for each field in this
     *            date
     * @return the formatted Date
     * @throws IllegalArgumentException
     *            if the object cannot be formatted by this Format.
     */
    private StringBuffer formatImpl(Date date, StringBuffer buffer,
            FieldPosition field, List<FieldPosition> fields) {

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
            List<FieldPosition> fields, char format, int count) {
        int field = -1;
        int index = PATTERN_CHARS.indexOf(format);
        if (index == -1) {
            throw new IllegalArgumentException("Unknown pattern character '" + format + "'");
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
                /*
                 * For 'y' and 'yyy', we're consistent with Unicode and previous releases
                 * of Android. But this means we're inconsistent with the RI.
                 *     http://unicode.org/reports/tr35/
                 */
                if (count == 2) {
                    appendNumber(buffer, 2, year % 100);
                } else {
                    appendNumber(buffer, count, year);
                }
                break;
            case STAND_ALONE_MONTH_FIELD: // L
                dateFormatField = Field.MONTH;
                appendMonth(buffer, count, formatData.longStandAloneMonths, formatData.shortStandAloneMonths);
                break;
            case MONTH_FIELD: // M
                dateFormatField = Field.MONTH;
                appendMonth(buffer, count, formatData.months, formatData.shortMonths);
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
            case STAND_ALONE_DAY_OF_WEEK_FIELD:
                dateFormatField = Field.DAY_OF_WEEK;
                appendDayOfWeek(buffer, count, formatData.longStandAloneWeekdays, formatData.shortStandAloneWeekdays);
                break;
            case DAY_OF_WEEK_FIELD:
                dateFormatField = Field.DAY_OF_WEEK;
                appendDayOfWeek(buffer, count, formatData.weekdays, formatData.shortWeekdays);
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
            case RFC_822_TIMEZONE_FIELD: // Z
                dateFormatField = Field.TIME_ZONE;
                appendNumericTimeZone(buffer, false);
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

    private void appendDayOfWeek(StringBuffer buffer, int count, String[] longs, String[] shorts) {
        boolean isLong = (count > 3);
        String[] days = isLong ? longs : shorts;
        buffer.append(days[calendar.get(Calendar.DAY_OF_WEEK)]);
    }

    private void appendMonth(StringBuffer buffer, int count, String[] longs, String[] shorts) {
        int month = calendar.get(Calendar.MONTH);
        if (count <= 2) {
            appendNumber(buffer, count, month + 1);
            return;
        }

        boolean isLong = (count > 3);
        String[] months = isLong ? longs : shorts;
        buffer.append(months[month]);
    }

    /**
     * Append a representation of the time zone of 'calendar' to 'buffer'.
     *
     * @param count the number of z or Z characters in the format string; "zzz" would be 3,
     * for example.
     * @param generalTimeZone true if we should use a display name ("PDT") if available;
     * false implies that we should use RFC 822 format ("-0800") instead. This corresponds to 'z'
     * versus 'Z' in the format string.
     */
    private void appendTimeZone(StringBuffer buffer, int count, boolean generalTimeZone) {
        if (generalTimeZone) {
            TimeZone tz = calendar.getTimeZone();
            boolean daylight = (calendar.get(Calendar.DST_OFFSET) != 0);
            int style = count < 4 ? TimeZone.SHORT : TimeZone.LONG;
            if (!formatData.customZoneStrings) {
                buffer.append(tz.getDisplayName(daylight, style, formatData.locale));
                return;
            }
            // We can't call TimeZone.getDisplayName() because it would not use
            // the custom DateFormatSymbols of this SimpleDateFormat.
            String custom = TimeZones.getDisplayName(formatData.zoneStrings, tz.getID(), daylight, style);
            if (custom != null) {
                buffer.append(custom);
                return;
            }
        }
        // We didn't find what we were looking for, so default to a numeric time zone.
        appendNumericTimeZone(buffer, generalTimeZone);
    }

    /**
     * @param generalTimeZone "GMT-08:00" rather than "-0800".
     */
    private void appendNumericTimeZone(StringBuffer buffer, boolean generalTimeZone) {
        int offset = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
        char sign = '+';
        if (offset < 0) {
            sign = '-';
            offset = -offset;
        }
        if (generalTimeZone) {
            buffer.append("GMT");
        }
        buffer.append(sign);
        appendNumber(buffer, 2, offset / 3600000);
        if (generalTimeZone) {
            buffer.append(':');
        }
        appendNumber(buffer, 2, (offset % 3600000) / 60000);
    }

    private void appendNumber(StringBuffer buffer, int count, int value) {
        // TODO: we could avoid using the NumberFormat in most cases for a significant speedup.
        // The only problem is that we expose the NumberFormat to third-party code, so we'd have
        // some work to do to work out when the optimization is valid.
        int minimumIntegerDigits = numberFormat.getMinimumIntegerDigits();
        numberFormat.setMinimumIntegerDigits(count);
        numberFormat.format(Integer.valueOf(value), buffer, new FieldPosition(0));
        numberFormat.setMinimumIntegerDigits(minimumIntegerDigits);
    }

    private Date error(ParsePosition position, int offset, TimeZone zone) {
        position.setErrorIndex(offset);
        calendar.setTimeZone(zone);
        return null;
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
    public StringBuffer format(Date date, StringBuffer buffer, FieldPosition fieldPos) {
        // Harmony delegates to ICU's SimpleDateFormat, we implement it directly
        return formatImpl(date, buffer, fieldPos, null);
    }

    /**
     * Returns the date which is the start of the one hundred year period for two-digit year values.
     * See {@link #set2DigitYearStart} for details.
     */
    public Date get2DigitYearStart() {
        return (Date) defaultCenturyStart.clone();
    }

    /**
     * Returns the {@code DateFormatSymbols} used by this simple date format.
     *
     * @return the {@code DateFormatSymbols} object.
     */
    public DateFormatSymbols getDateFormatSymbols() {
        return (DateFormatSymbols) formatData.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + pattern.hashCode() + formatData.hashCode() + creationYear;
    }

    private int parse(String string, int offset, char format, int count) {
        int index = PATTERN_CHARS.indexOf(format);
        if (index == -1) {
            throw new IllegalArgumentException("Unknown pattern character '" + format + "'");
        }
        int field = -1;
        // TODO: what's 'absolute' for? when is 'count' negative, and why?
        int absolute = 0;
        if (count < 0) {
            count = -count;
            absolute = count;
        }
        switch (index) {
            case ERA_FIELD:
                return parseText(string, offset, formatData.eras, Calendar.ERA);
            case YEAR_FIELD:
                if (count >= 3) {
                    field = Calendar.YEAR;
                } else {
                    ParsePosition position = new ParsePosition(offset);
                    Number result = parseNumber(absolute, string, position);
                    if (result == null) {
                        return -position.getErrorIndex() - 1;
                    }
                    int year = result.intValue();
                    // A two digit year must be exactly two digits, i.e. 01
                    if ((position.getIndex() - offset) == 2 && year >= 0) {
                        year += creationYear / 100 * 100;
                        if (year < creationYear) {
                            year += 100;
                        }
                    }
                    calendar.set(Calendar.YEAR, year);
                    return position.getIndex();
                }
                break;
            case STAND_ALONE_MONTH_FIELD: // L
                return parseMonth(string, offset, count, absolute,
                        formatData.longStandAloneMonths, formatData.shortStandAloneMonths);
            case MONTH_FIELD: // M
                return parseMonth(string, offset, count, absolute,
                        formatData.months, formatData.shortMonths);
            case DATE_FIELD:
                field = Calendar.DATE;
                break;
            case HOUR_OF_DAY1_FIELD: // k
                ParsePosition position = new ParsePosition(offset);
                Number result = parseNumber(absolute, string, position);
                if (result == null) {
                    return -position.getErrorIndex() - 1;
                }
                int hour = result.intValue();
                if (hour == 24) {
                    hour = 0;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                return position.getIndex();
            case HOUR_OF_DAY0_FIELD: // H
                field = Calendar.HOUR_OF_DAY;
                break;
            case MINUTE_FIELD:
                field = Calendar.MINUTE;
                break;
            case SECOND_FIELD:
                field = Calendar.SECOND;
                break;
            case MILLISECOND_FIELD:
                field = Calendar.MILLISECOND;
                break;
            case STAND_ALONE_DAY_OF_WEEK_FIELD:
                return parseDayOfWeek(string, offset, formatData.longStandAloneWeekdays, formatData.shortStandAloneWeekdays);
            case DAY_OF_WEEK_FIELD:
                return parseDayOfWeek(string, offset, formatData.weekdays, formatData.shortWeekdays);
            case DAY_OF_YEAR_FIELD:
                field = Calendar.DAY_OF_YEAR;
                break;
            case DAY_OF_WEEK_IN_MONTH_FIELD:
                field = Calendar.DAY_OF_WEEK_IN_MONTH;
                break;
            case WEEK_OF_YEAR_FIELD:
                field = Calendar.WEEK_OF_YEAR;
                break;
            case WEEK_OF_MONTH_FIELD:
                field = Calendar.WEEK_OF_MONTH;
                break;
            case AM_PM_FIELD:
                return parseText(string, offset, formatData.ampms, Calendar.AM_PM);
            case HOUR1_FIELD: // h
                position = new ParsePosition(offset);
                result = parseNumber(absolute, string, position);
                if (result == null) {
                    return -position.getErrorIndex() - 1;
                }
                hour = result.intValue();
                if (hour == 12) {
                    hour = 0;
                }
                calendar.set(Calendar.HOUR, hour);
                return position.getIndex();
            case HOUR0_FIELD: // K
                field = Calendar.HOUR;
                break;
            case TIMEZONE_FIELD: // z
                return parseTimeZone(string, offset);
            case RFC_822_TIMEZONE_FIELD: // Z
                return parseTimeZone(string, offset);
        }
        if (field != -1) {
            return parseNumber(absolute, string, offset, field, 0);
        }
        return offset;
    }

    private int parseDayOfWeek(String string, int offset, String[] longs, String[] shorts) {
        int index = parseText(string, offset, longs, Calendar.DAY_OF_WEEK);
        if (index < 0) {
            index = parseText(string, offset, shorts, Calendar.DAY_OF_WEEK);
        }
        return index;
    }

    private int parseMonth(String string, int offset, int count, int absolute, String[] longs, String[] shorts) {
        if (count <= 2) {
            return parseNumber(absolute, string, offset, Calendar.MONTH, -1);
        }
        int index = parseText(string, offset, longs, Calendar.MONTH);
        if (index < 0) {
            index = parseText(string, offset, shorts, Calendar.MONTH);
        }
        return index;
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
        // Harmony delegates to ICU's SimpleDateFormat, we implement it directly
        boolean quote = false;
        int next, last = -1, count = 0, offset = position.getIndex();
        int length = string.length();
        calendar.clear();
        TimeZone zone = calendar.getTimeZone();
        final int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            next = pattern.charAt(i);
            if (next == '\'') {
                if (count > 0) {
                    if ((offset = parse(string, offset, (char) last, count)) < 0) {
                        return error(position, -offset - 1, zone);
                    }
                    count = 0;
                }
                if (last == next) {
                    if (offset >= length || string.charAt(offset) != '\'') {
                        return error(position, offset, zone);
                    }
                    offset++;
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
                        if ((offset = parse(string, offset, (char) last, -count)) < 0) {
                            return error(position, -offset - 1, zone);
                        }
                    }
                    last = next;
                    count = 1;
                }
            } else {
                if (count > 0) {
                    if ((offset = parse(string, offset, (char) last, count)) < 0) {
                        return error(position, -offset - 1, zone);
                    }
                    count = 0;
                }
                last = -1;
                if (offset >= length || string.charAt(offset) != next) {
                    return error(position, offset, zone);
                }
                offset++;
            }
        }
        if (count > 0) {
            if ((offset = parse(string, offset, (char) last, count)) < 0) {
                return error(position, -offset - 1, zone);
            }
        }
        Date date;
        try {
            date = calendar.getTime();
        } catch (IllegalArgumentException e) {
            return error(position, offset, zone);
        }
        position.setIndex(offset);
        calendar.setTimeZone(zone);
        return date;
    }

    private Number parseNumber(int max, String string, ParsePosition position) {
        int digit, length = string.length(), result = 0;
        int index = position.getIndex();
        if (max > 0 && max < length - index) {
            length = index + max;
        }
        while (index < length
                && (string.charAt(index) == ' ' || string.charAt(index) == '\t')) {
            index++;
        }
        if (max == 0) {
            position.setIndex(index);
            return numberFormat.parse(string, position);
        }

        while (index < length
                && (digit = Character.digit(string.charAt(index), 10)) != -1) {
            index++;
            result = result * 10 + digit;
        }
        if (index == position.getIndex()) {
            position.setErrorIndex(index);
            return null;
        }
        position.setIndex(index);
        return Integer.valueOf(result);
    }

    private int parseNumber(int max, String string, int offset, int field, int skew) {
        ParsePosition position = new ParsePosition(offset);
        Number result = parseNumber(max, string, position);
        if (result == null) {
            return -position.getErrorIndex() - 1;
        }
        calendar.set(field, result.intValue() + skew);
        return position.getIndex();
    }

    private int parseText(String string, int offset, String[] text, int field) {
        int found = -1;
        for (int i = 0; i < text.length; i++) {
            if (text[i].isEmpty()) {
                continue;
            }
            if (string.regionMatches(true, offset, text[i], 0, text[i].length())) {
                // Search for the longest match, in case some fields are subsets
                if (found == -1 || text[i].length() > text[found].length()) {
                    found = i;
                }
            }
        }
        if (found != -1) {
            calendar.set(field, found);
            return offset + text[found].length();
        }
        return -offset - 1;
    }

    private int parseTimeZone(String string, int offset) {
        boolean foundGMT = string.regionMatches(offset, "GMT", 0, 3);
        if (foundGMT) {
            offset += 3;
        }
        char sign;
        if (offset < string.length()
                && ((sign = string.charAt(offset)) == '+' || sign == '-')) {
            ParsePosition position = new ParsePosition(offset + 1);
            Number result = numberFormat.parse(string, position);
            if (result == null) {
                return -position.getErrorIndex() - 1;
            }
            int hour = result.intValue();
            int raw = hour * 3600000;
            int index = position.getIndex();
            if (index < string.length() && string.charAt(index) == ':') {
                position.setIndex(index + 1);
                result = numberFormat.parse(string, position);
                if (result == null) {
                    return -position.getErrorIndex() - 1;
                }
                int minute = result.intValue();
                raw += minute * 60000;
            } else if (hour >= 24) {
                raw = (hour / 100 * 3600000) + (hour % 100 * 60000);
            }
            if (sign == '-') {
                raw = -raw;
            }
            calendar.setTimeZone(new SimpleTimeZone(raw, ""));
            return position.getIndex();
        }
        if (foundGMT) {
            calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
            return offset;
        }
        String[][] zones = formatData.internalZoneStrings();
        for (String[] element : zones) {
            for (int j = TimeZones.LONG_NAME; j < TimeZones.NAME_COUNT; j++) {
                if (string.regionMatches(true, offset, element[j], 0, element[j].length())) {
                    TimeZone zone = TimeZone.getTimeZone(element[TimeZones.OLSON_NAME]);
                    if (zone == null) {
                        return -offset - 1;
                    }
                    int raw = zone.getRawOffset();
                    if (j == TimeZones.LONG_NAME_DST || j == TimeZones.SHORT_NAME_DST) {
                        /*
                         * TODO, http://b/4723412
                         * We can't use TimeZone#getDSTSavings here because that
                         * will return 0 if the zone no longer uses DST. We
                         * should change this to use TimeZone.getOffset(long),
                         * which requires the complete date to be parsed first.
                         */
                        raw += 3600000;
                    }
                    calendar.setTimeZone(new SimpleTimeZone(raw, ""));
                    return offset + element[j].length();
                }
            }
        }
        return -offset - 1;
    }

    /**
     * Sets the date which is the start of the one hundred year period for two-digit year values.
     *
     * <p>When parsing a date string using the abbreviated year pattern {@code yy}, {@code
     * SimpleDateFormat} must interpret the abbreviated year relative to some
     * century. It does this by adjusting dates to be within 80 years before and 20
     * years after the time the {@code SimpleDateFormat} instance was created. For
     * example, using a pattern of {@code MM/dd/yy}, an
     * instance created on Jan 1, 1997 would interpret the string {@code "01/11/12"}
     * as Jan 11, 2012 but interpret the string {@code "05/04/64"} as May 4, 1964.
     * During parsing, only strings consisting of exactly two digits, as
     * defined by {@link java.lang.Character#isDigit(char)}, will be parsed into the
     * default century. Any other numeric string, such as a one digit string, a
     * three or more digit string, or a two digit string that isn't all digits (for
     * example, {@code "-1"}), is interpreted literally. So using the same pattern, both
     * {@code "01/02/3"} and {@code "01/02/003"} are parsed as Jan 2, 3 AD.
     * Similarly, {@code "01/02/-3"} is parsed as Jan 2, 4 BC.
     *
     * <p>If the year pattern does not have exactly two 'y' characters, the year is
     * interpreted literally, regardless of the number of digits. So using the
     * pattern {@code MM/dd/yyyy}, {@code "01/11/12"} is parsed as Jan 11, 12 A.D.
     */
    public void set2DigitYearStart(Date date) {
        defaultCenturyStart = (Date) date.clone();
        Calendar cal = new GregorianCalendar();
        cal.setTime(defaultCenturyStart);
        creationYear = cal.get(Calendar.YEAR);
    }

    /**
     * Sets the {@code DateFormatSymbols} used by this simple date format.
     *
     * @param value
     *            the new {@code DateFormatSymbols} object.
     */
    public void setDateFormatSymbols(DateFormatSymbols value) {
        formatData = (DateFormatSymbols) value.clone();
    }

    /**
     * Returns the pattern of this simple date format using localized pattern
     * characters.
     *
     * @return the localized pattern.
     */
    public String toLocalizedPattern() {
        return convertPattern(pattern, PATTERN_CHARS, formatData.getLocalPatternChars(), false);
    }

    private static String convertPattern(String template, String fromChars, String toChars, boolean check) {
        if (!check && fromChars.equals(toChars)) {
            return template;
        }
        boolean quote = false;
        StringBuilder output = new StringBuilder();
        int length = template.length();
        for (int i = 0; i < length; i++) {
            int index;
            char next = template.charAt(i);
            if (next == '\'') {
                quote = !quote;
            }
            if (!quote && (index = fromChars.indexOf(next)) != -1) {
                output.append(toChars.charAt(index));
            } else if (check && !quote && ((next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z'))) {
                throw new IllegalArgumentException("Invalid pattern character '" + next + "' in " + "'" + template + "'");
            } else {
                output.append(next);
            }
        }
        if (quote) {
            throw new IllegalArgumentException("Unterminated quote");
        }
        return output.toString();
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
        new ObjectStreamField("defaultCenturyStart", Date.class),
        new ObjectStreamField("formatData", DateFormatSymbols.class),
        new ObjectStreamField("pattern", String.class),
        new ObjectStreamField("serialVersionOnStream", int.class),
    };

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("defaultCenturyStart", defaultCenturyStart);
        fields.put("formatData", formatData);
        fields.put("pattern", pattern);
        fields.put("serialVersionOnStream", 1);
        stream.writeFields();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        int version = fields.get("serialVersionOnStream", 0);
        Date date;
        if (version > 0) {
            date = (Date) fields.get("defaultCenturyStart", new Date());
        } else {
            date = new Date();
        }
        set2DigitYearStart(date);
        formatData = (DateFormatSymbols) fields.get("formatData", null);
        pattern = (String) fields.get("pattern", "");
    }
}
