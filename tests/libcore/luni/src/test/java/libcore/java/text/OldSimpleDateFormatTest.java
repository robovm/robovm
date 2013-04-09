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
package libcore.java.text;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class OldSimpleDateFormatTest extends junit.framework.TestCase {

    SimpleDateFormat format = new SimpleDateFormat("", Locale.ENGLISH);

    SimpleDateFormat pFormat = new SimpleDateFormat("", Locale.ENGLISH);

    class FormatTester {
        boolean testsFailed = false;

        public void test(String pattern, Calendar cal, String expected, int field) {
            StringBuffer buffer = new StringBuffer();
            FieldPosition position = new FieldPosition(field);
            format.applyPattern(pattern);
            format.format(cal.getTime(), buffer, position);
            String result = buffer.toString();
            if (!result.equals(expected)) {
                System.out.println("Wrong format: \"" + pattern
                                + "\" expected: " + expected + " result: "
                                + result);
                testsFailed = true;
            }
        }

        public void parse(String pattern, String input, Date expected, int start, int end) {
            pFormat.applyPattern(pattern);
            ParsePosition position = new ParsePosition(start);
            Date result = pFormat.parse(input, position);
            assertTrue("Wrong result: " + pattern + " input: " + input
                    + " expected: " + expected + " result: " + result, expected
                    .equals(result));
            assertTrue("Wrong end position: " + pattern + " input: " + input,
                    position.getIndex() == end);
        }

        public void verifyFormatTimezone(String timeZoneId, String expected1,
                String expected2, Date date) {
            format.setTimeZone(SimpleTimeZone.getTimeZone(timeZoneId));
            format.applyPattern("z, zzzz");
            assertEquals("Test z for TimeZone : " + timeZoneId, expected1,
                    format.format(date));

            format.applyPattern("Z, ZZZZ");
            assertEquals("Test Z for TimeZone : " + timeZoneId, expected2,
                    format.format(date));
        }
    }

    /**
     * java.text.SimpleDateFormat#SimpleDateFormat(java.lang.String,
     *        java.text.DateFormatSymbols)
     */
    public void test_ConstructorLjava_lang_StringLjava_text_DateFormatSymbols() {
        // Test for method java.text.SimpleDateFormat(java.lang.String,
        // java.text.DateFormatSymbols)
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
        symbols.setEras(new String[] { "Before", "After" });
        SimpleDateFormat f2 = new SimpleDateFormat("y'y'yy", symbols);
        assertTrue("Wrong class", f2.getClass() == SimpleDateFormat.class);
        assertEquals("Wrong pattern", "y'y'yy", f2.toPattern());
        assertTrue("Wrong symbols", f2.getDateFormatSymbols().equals(symbols));
        assertTrue("Doesn't work",
                f2.format(new Date()).getClass() == String.class);

        try {
            new SimpleDateFormat(null, symbols);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            new SimpleDateFormat("eee", symbols);
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_ConstructorLjava_lang_StringLjava_util_Locale() {
        // Test for method java.text.SimpleDateFormat(java.lang.String,
        // java.util.Locale)
        SimpleDateFormat f2 = new SimpleDateFormat("'yyyy' MM yy",
                Locale.GERMAN);
        assertTrue("Wrong class", f2.getClass() == SimpleDateFormat.class);
        assertEquals("Wrong pattern", "'yyyy' MM yy", f2.toPattern());
        assertTrue("Wrong symbols", f2.getDateFormatSymbols().equals(
                new DateFormatSymbols(Locale.GERMAN)));
        assertTrue("Doesn't work",
                f2.format(new Date()).getClass() == String.class);

        try {
            new SimpleDateFormat(null, Locale.GERMAN);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
        try {
            new SimpleDateFormat("eee", Locale.GERMAN);
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_applyLocalizedPatternLjava_lang_String() {
        // Test for method void
        // java.text.SimpleDateFormat.applyLocalizedPattern(java.lang.String)
        SimpleDateFormat f2 = new SimpleDateFormat("y", new Locale("de", "CH"));
        // BEGIN android-removed
        // This test doesn't work like this. The cause lies inside of icu
        // that doesn't support localized pattern characters anymore. So this
        // test fails because the pattern template contains characters that are
        // not part of the standard pattern returned for every locale.
        // The default pattern characters are: GyMdkHmsSEDFwWahKzZ
        //
        // f2.applyLocalizedPattern("GuMtkHmsSEDFwWahKz");
        // String pattern = f2.toPattern();
        // assertTrue("Wrong pattern: " + pattern, pattern
        //         .equals("GyMdkHmsSEDFwWahKz"));
        //
        // test the new "Z" pattern char
        // f2 = new SimpleDateFormat("y", new Locale("de", "CH"));
        // f2.applyLocalizedPattern("G u M t Z");
        // pattern = f2.toPattern();
        // assertTrue("Wrong pattern: " + pattern, pattern.equals("G y M d Z"));
        // END android-removed

        // test invalid patterns
        try {
            f2.applyLocalizedPattern("b");
            fail("Expected IllegalArgumentException for pattern with invalid pattern letter: b");
        } catch (IllegalArgumentException e) {
        }

        try {
            // ICU only! this fails on the RI
            f2.applyLocalizedPattern("u");
            fail("Expected IllegalArgumentException for pattern with invalid pattern letter: u");
        } catch (IllegalArgumentException e) {
        }

        try {
            f2.applyLocalizedPattern("a '");
            fail("Expected IllegalArgumentException for pattern with unterminated quote: a '");
        } catch (IllegalArgumentException e) {
        }

        try {
            f2.applyLocalizedPattern(null);
            fail("Expected NullPointerException for null pattern");
        } catch (NullPointerException e) {
        }
    }

    /**
     * java.text.SimpleDateFormat#applyPattern(java.lang.String)
     */
    public void test_applyPatternLjava_lang_String() {
        // Test for method void
        // java.text.SimpleDateFormat.applyPattern(java.lang.String)
        SimpleDateFormat f2 = new SimpleDateFormat("y", new Locale("de", "CH"));
        // BEGIN android-changed
        f2.applyPattern("GyMdkHmsSEDFwWahKzZ");
        assertEquals("Wrong pattern", "GyMdkHmsSEDFwWahKzZ", f2.toPattern());
        // END android-changed

        // test invalid patterns
        try {
            f2.applyPattern("u");
            fail("Expected IllegalArgumentException for pattern with invalid patter letter: u");
        } catch (IllegalArgumentException e) {
        }
    }

    public void test_hashCode() {
        SimpleDateFormat format = (SimpleDateFormat) DateFormat.getInstance();
        SimpleDateFormat clone = (SimpleDateFormat) format.clone();
        assertTrue("clone has not equal hash code", clone.hashCode() == format
                .hashCode());
        format.format(new Date());
        assertTrue("clone has not equal hash code after format", clone
                .hashCode() == format.hashCode());
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
        symbols.setEras(new String[] { "Before", "After" });
        SimpleDateFormat format2 = new SimpleDateFormat("y'y'yy", symbols);
        assertFalse("objects has equal hash code", format2.hashCode() == format
                .hashCode());
    }

    public void test_formatLjava_util_DateLjava_lang_StringBufferLjava_text_FieldPosition() {
        FormatTester test = new FormatTester();

        Calendar cal = new GregorianCalendar(1999, Calendar.JUNE, 2, 15, 3, 6);
        test.test(" G", cal, " AD", DateFormat.ERA_FIELD);
        test.test(" GG", cal, " AD", DateFormat.ERA_FIELD);
        test.test(" GGG", cal, " AD", DateFormat.ERA_FIELD);
        test.test(" G", new GregorianCalendar(-1999, Calendar.JUNE, 2), " BC",
                DateFormat.ERA_FIELD);

        test.test(" M", cal, " 6", DateFormat.MONTH_FIELD);
        test.test(" M", new GregorianCalendar(1999, Calendar.NOVEMBER, 2),
                " 11", DateFormat.MONTH_FIELD);
        test.test(" MM", cal, " 06", DateFormat.MONTH_FIELD);
        test.test(" MMM", cal, " Jun", DateFormat.MONTH_FIELD);
        test.test(" MMMM", cal, " June", DateFormat.MONTH_FIELD);
        test.test(" MMMMM", cal, " June", DateFormat.MONTH_FIELD);

        test.test(" d", cal, " 2", DateFormat.DATE_FIELD);
        test.test(" d", new GregorianCalendar(1999, Calendar.NOVEMBER, 12),
                " 12", DateFormat.DATE_FIELD);
        test.test(" dd", cal, " 02", DateFormat.DATE_FIELD);
        test.test(" dddd", cal, " 0002", DateFormat.DATE_FIELD);

        test.test(" h", cal, " 3", DateFormat.HOUR1_FIELD);
        test.test(" h", new GregorianCalendar(1999, Calendar.NOVEMBER, 12),
                " 12", DateFormat.HOUR1_FIELD);
        test.test(" hh", cal, " 03", DateFormat.HOUR1_FIELD);
        test.test(" hhhh", cal, " 0003", DateFormat.HOUR1_FIELD);

        test.test(" H", cal, " 15", DateFormat.HOUR_OF_DAY0_FIELD);
        test.test(" H",
                new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 4, 0), " 4",
                DateFormat.HOUR_OF_DAY0_FIELD);
        test.test(" H", new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 12,
                0), " 12", DateFormat.HOUR_OF_DAY0_FIELD);
        test.test(" H", new GregorianCalendar(1999, Calendar.NOVEMBER, 12),
                " 0", DateFormat.HOUR_OF_DAY0_FIELD);
        test.test(" HH", cal, " 15", DateFormat.HOUR_OF_DAY0_FIELD);
        test.test(" HHHH", cal, " 0015", DateFormat.HOUR_OF_DAY0_FIELD);

        test.test(" m", cal, " 3", DateFormat.MINUTE_FIELD);
        test.test(" m", new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 4,
                47), " 47", DateFormat.MINUTE_FIELD);
        test.test(" mm", cal, " 03", DateFormat.MINUTE_FIELD);
        test.test(" mmmm", cal, " 0003", DateFormat.MINUTE_FIELD);

        test.test(" s", cal, " 6", DateFormat.SECOND_FIELD);
        test.test(" s", new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 4,
                47, 13), " 13", DateFormat.SECOND_FIELD);
        test.test(" ss", cal, " 06", DateFormat.SECOND_FIELD);
        test.test(" ssss", cal, " 0006", DateFormat.SECOND_FIELD);

        test.test(" S", cal, " 0", DateFormat.MILLISECOND_FIELD);
        Calendar temp = new GregorianCalendar();
        temp.set(Calendar.MILLISECOND, 961);

        test.test(" SS", temp, " 961", DateFormat.MILLISECOND_FIELD);
        test.test(" SSSS", cal, " 0000", DateFormat.MILLISECOND_FIELD);

        test.test(" SS", cal, " 00", DateFormat.MILLISECOND_FIELD);

        test.test(" E", cal, " Wed", DateFormat.DAY_OF_WEEK_FIELD);
        test.test(" EE", cal, " Wed", DateFormat.DAY_OF_WEEK_FIELD);
        test.test(" EEE", cal, " Wed", DateFormat.DAY_OF_WEEK_FIELD);
        test.test(" EEEE", cal, " Wednesday", DateFormat.DAY_OF_WEEK_FIELD);
        test.test(" EEEEE", cal, " Wednesday", DateFormat.DAY_OF_WEEK_FIELD);

        test.test(" D", cal, " 153", DateFormat.DAY_OF_YEAR_FIELD);
        test.test(" DD", cal, " 153", DateFormat.DAY_OF_YEAR_FIELD);
        test.test(" DDDD", cal, " 0153", DateFormat.DAY_OF_YEAR_FIELD);

        test.test(" F", cal, " 1", DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD);
        test.test(" F", new GregorianCalendar(1999, Calendar.NOVEMBER, 14),
                " 2", DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD);
        test.test(" FF", cal, " 01", DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD);
        test.test(" FFFF", cal, " 0001", DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD);

        test.test(" a", cal, " PM", DateFormat.AM_PM_FIELD);
        test.test(" a", new GregorianCalendar(1999, Calendar.NOVEMBER, 14),
                " AM", DateFormat.AM_PM_FIELD);
        test.test(" a", new GregorianCalendar(1999, Calendar.NOVEMBER, 14, 12,
                0), " PM", DateFormat.AM_PM_FIELD);
        test.test(" aa", cal, " PM", DateFormat.AM_PM_FIELD);
        test.test(" aaa", cal, " PM", DateFormat.AM_PM_FIELD);
        test.test(" aaaa", cal, " PM", DateFormat.AM_PM_FIELD);
        test.test(" aaaaa", cal, " PM", DateFormat.AM_PM_FIELD);

        test.test(" k", cal, " 15", DateFormat.HOUR_OF_DAY1_FIELD);
        test.test(" k",
                new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 4, 0), " 4",
                DateFormat.HOUR_OF_DAY1_FIELD);
        test.test(" k", new GregorianCalendar(1999, Calendar.NOVEMBER, 12, 12,
                0), " 12", DateFormat.HOUR_OF_DAY1_FIELD);
        test.test(" k", new GregorianCalendar(1999, Calendar.NOVEMBER, 12),
                " 24", DateFormat.HOUR_OF_DAY1_FIELD);
        test.test(" kk", cal, " 15", DateFormat.HOUR_OF_DAY1_FIELD);
        test.test(" kkkk", cal, " 0015", DateFormat.HOUR_OF_DAY1_FIELD);

        test.test(" K", cal, " 3", DateFormat.HOUR0_FIELD);
        test.test(" K", new GregorianCalendar(1999, Calendar.NOVEMBER, 12),
                " 0", DateFormat.HOUR0_FIELD);
        test.test(" KK", cal, " 03", DateFormat.HOUR0_FIELD);
        test.test(" KKKK", cal, " 0003", DateFormat.HOUR0_FIELD);

        format.applyPattern("'Mkz''':.@5");
        assertEquals("Wrong output", "Mkz':.@5", format.format(new Date()));

        //assertTrue("Tests failed", !test.testsFailed());

        // Test invalid args to format.
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        try {
            dateFormat.format(null, new StringBuffer(), new FieldPosition(1));
            fail("Expected test to throw NPE.");
        } catch (NullPointerException ex) {
            // expected
        } catch (Throwable ex) {
            fail("Expected test to throw NPE, not " + ex.getClass().getName());
        }

        assertFalse(test.testsFailed);
    }

    /**
     * This test assumes Unicode behavior where 'y' and 'yyy' don't truncate,
     * which means that it will fail on the RI.
     */
    public void testFormattingYear() {
        FormatTester test = new FormatTester();

        Calendar cal = new GregorianCalendar(1999, Calendar.JUNE, 2, 15, 3, 6);
        test.test(" y", cal, " 1999", DateFormat.YEAR_FIELD);
        test.test(" yy", cal, " 99", DateFormat.YEAR_FIELD);
        test.test(" yy", new GregorianCalendar(2001, Calendar.JUNE, 2), " 01",
                DateFormat.YEAR_FIELD);
        test.test(" yy", new GregorianCalendar(2000, Calendar.JUNE, 2), " 00",
                DateFormat.YEAR_FIELD);
        test.test(" yyy", new GregorianCalendar(2000, Calendar.JUNE, 2), " 2000",
                DateFormat.YEAR_FIELD);
        test.test(" yyy", cal, " 1999", DateFormat.YEAR_FIELD);
        test.test(" yyyy", cal, " 1999", DateFormat.YEAR_FIELD);
        test.test(" yyyyy", cal, " 01999", DateFormat.YEAR_FIELD);

        assertFalse(test.testsFailed);
    }

    public void testFormattingWeekOfYear() {
        FormatTester test = new FormatTester();
        Calendar cal = new GregorianCalendar(1999, Calendar.JUNE, 2, 15, 3, 6);
        cal.setMinimalDaysInFirstWeek(1);
        cal.setFirstDayOfWeek(1);

        test.test(" w", cal, " 23", DateFormat.WEEK_OF_YEAR_FIELD);
        test.test(" ww", cal, " 23", DateFormat.WEEK_OF_YEAR_FIELD);
        test.test(" wwww", cal, " 0023", DateFormat.WEEK_OF_YEAR_FIELD);

        test.test(" W", cal, " 1", DateFormat.WEEK_OF_MONTH_FIELD);
        test.test(" W", new GregorianCalendar(1999, Calendar.NOVEMBER, 14),
                " 3", DateFormat.WEEK_OF_MONTH_FIELD);
        test.test(" WW", cal, " 01", DateFormat.WEEK_OF_MONTH_FIELD);
        test.test(" WWWW", cal, " 0001", DateFormat.WEEK_OF_MONTH_FIELD);

        assertFalse(test.testsFailed);
    }

    public void testFormattingTimezones() {
        FormatTester test = new FormatTester();
        Calendar cal = new GregorianCalendar(1999, Calendar.JUNE, 2, 15, 3, 6);

        TimeZone tz0001 = new SimpleTimeZone(60000, "ONE MINUTE");
        TimeZone tz0130 = new SimpleTimeZone(5400000, "ONE HOUR, THIRTY");
        TimeZone tzMinus0130 = new SimpleTimeZone(-5400000, "NEG ONE HOUR, THIRTY");

        format.setTimeZone(tz0001);
        test.test(" Z", cal, " +0001", DateFormat.TIMEZONE_FIELD);
        test.test(" ZZZZ", cal, " +0001", DateFormat.TIMEZONE_FIELD);
        format.setTimeZone(tz0130);
        test.test(" Z", cal, " +0130", DateFormat.TIMEZONE_FIELD);
        format.setTimeZone(tzMinus0130);
        test.test(" Z", cal, " -0130", DateFormat.TIMEZONE_FIELD);

        format.setTimeZone(tz0001);
        test.test(" z", cal, " GMT+00:01", DateFormat.TIMEZONE_FIELD);
        test.test(" zzzz", cal, " GMT+00:01", DateFormat.TIMEZONE_FIELD);
        format.setTimeZone(tz0130);
        test.test(" z", cal, " GMT+01:30", DateFormat.TIMEZONE_FIELD);
        format.setTimeZone(tzMinus0130);
        test.test(" z", cal, " GMT-01:30", DateFormat.TIMEZONE_FIELD);

        format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        test.test(" z", cal, " EDT", DateFormat.TIMEZONE_FIELD);
        Calendar temp2 = new GregorianCalendar(1999, Calendar.JANUARY, 12);
        test.test(" z", temp2, " EST", DateFormat.TIMEZONE_FIELD);
        test.test(" zz", cal, " EDT", DateFormat.TIMEZONE_FIELD);
        test.test(" zzz", cal, " EDT", DateFormat.TIMEZONE_FIELD);
        test.test(" zzzz", cal, " Eastern Daylight Time", DateFormat.TIMEZONE_FIELD);
        test.test(" zzzz", temp2, " Eastern Standard Time", DateFormat.TIMEZONE_FIELD);
        test.test(" zzzzz", cal, " Eastern Daylight Time", DateFormat.TIMEZONE_FIELD);

        assertFalse(test.testsFailed);
    }

    /**
     * java.text.SimpleDateFormat#format(java.util.Date)
     */
    public void test_timeZoneFormatting() {
        // tests specific to formatting of timezones
        Date summerDate = new GregorianCalendar(1999, Calendar.JUNE, 2, 15, 3, 6).getTime();
        Date winterDate = new GregorianCalendar(1999, Calendar.JANUARY, 12).getTime();

        FormatTester test = new FormatTester();
        test.verifyFormatTimezone("GMT-7", "GMT-07:00, GMT-07:00", "-0700, -0700", summerDate);
        test.verifyFormatTimezone("GMT-7", "GMT-07:00, GMT-07:00", "-0700, -0700", winterDate);

        test.verifyFormatTimezone("GMT+14", "GMT+14:00, GMT+14:00", "+1400, +1400", summerDate);
        test.verifyFormatTimezone("GMT+14", "GMT+14:00, GMT+14:00", "+1400, +1400", winterDate);

        test.verifyFormatTimezone("America/Los_Angeles", "PDT, Pacific Daylight Time",
                "-0700, -0700", summerDate);
        test.verifyFormatTimezone("America/Los_Angeles", "PST, Pacific Standard Time",
                "-0800, -0800", winterDate);

        // this fails on the RI!
        test.verifyFormatTimezone("America/Detroit", "EDT, Eastern Daylight Time",
                "-0400, -0400", summerDate);
        test.verifyFormatTimezone("America/Detroit", "EST, Eastern Standard Time",
                "-0500, -0500", winterDate);

        assertFalse(test.testsFailed);
    }

    public void test_parseLjava_lang_StringLjava_text_ParsePosition_2() {
        try {
            format.parse("240 11 2002 March", null);
            fail("ParsePosition is null: NullPointerException was not thrown.");
        } catch(NullPointerException pe) {
            //expected
        }

        try {
            format.parse(null, new ParsePosition(0));
            fail("String is null: NullPointerException was not thrown.");
        } catch(NullPointerException pe) {
            //expected
        }
    }

    public void test_setDateFormatSymbolsLjava_text_DateFormatSymbols() {
        // Test for method void
        // java.text.SimpleDateFormat.setDateFormatSymbols(java.text.DateFormatSymbols)
        SimpleDateFormat f1 = new SimpleDateFormat("a");
        DateFormatSymbols symbols = new DateFormatSymbols();
        symbols.setAmPmStrings(new String[] { "morning", "night" });
        f1.setDateFormatSymbols(symbols);
        DateFormatSymbols newSym = f1.getDateFormatSymbols();
        assertTrue("Set incorrectly", newSym.equals(symbols));
        assertTrue("Not a clone", f1.getDateFormatSymbols() != symbols);
        String result = f1.format(new GregorianCalendar(1999, Calendar.JUNE,
                12, 3, 0).getTime());
        assertEquals("Incorrect symbols used", "morning", result);
        symbols.setEras(new String[] { "before", "after" });
        assertTrue("Identical symbols", !f1.getDateFormatSymbols().equals(
                symbols));

        try {
            f1.setDateFormatSymbols(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_toLocalizedPattern() {
        // BEGIN android-changed
        // Test for method java.lang.String
        // java.text.SimpleDateFormat.toLocalizedPattern()
        SimpleDateFormat f2 = new SimpleDateFormat("GyMdkHmsSEDFwWahKzZ",
                new Locale("de", "CH"));
        String pattern = f2.toLocalizedPattern();
        // the default localized pattern characters are the same for all locales
        // since icu has dropped support for this. the default pattern characters
        // are these: GyMdkHmsSEDFwWahKz
        // ICU only! this fails on the RI
        assertTrue("Wrong pattern: " + pattern, pattern
                .equals("GyMdkHmsSEDFwWahKzZ"));


        // test the new "Z" pattern char
        f2 = new SimpleDateFormat("G y M d Z", new Locale("de", "CH"));
        pattern = f2.toLocalizedPattern();
        // assertTrue("Wrong pattern: " + pattern, pattern.equals("G u M t Z"));
        assertTrue("Wrong pattern: " + pattern, pattern.equals("G y M d Z"));
        // END android-changed
    }

    public void test_toPattern() {
        String pattern = "yyyy mm dd";
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        assertEquals("Wrong pattern: " + pattern, pattern, f.toPattern());

        pattern = "GyMdkHmsSEDFwWahKz";
        f = new SimpleDateFormat("GyMdkHmsSEDFwWahKz", new Locale("de", "CH"));
        assertTrue("Wrong pattern: " + pattern, f.toPattern().equals(pattern));

        pattern = "G y M d Z";
        f = new SimpleDateFormat(pattern, new Locale("de", "CH"));
        pattern = f.toPattern();
        assertTrue("Wrong pattern: " + pattern, f.toPattern().equals(pattern));
    }
}
