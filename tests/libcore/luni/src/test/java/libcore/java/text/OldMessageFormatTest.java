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

import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import junit.framework.TestCase;
import tests.support.Support_MessageFormat;

public class OldMessageFormatTest extends TestCase {

    private MessageFormat format1;

    protected void setUp() {
        Locale.setDefault(Locale.US);

        // test with repeating formats and max argument index < max offset
        String pattern = "A {3, number, currency} B {2, time} C {0, number, percent} D {4}  E {1,choice,0#off|1#on} F {0, date}";
        format1 = new MessageFormat(pattern);
    }

    public void test_applyPatternLjava_lang_String_AndroidFailure() {
        MessageFormat format = new MessageFormat("test");
        format.setLocale(Locale.FRENCH); // use French since English has the
        // same LONG and FULL time patterns
        format.applyPattern("{0,time, Full}");
        assertEquals("Wrong full time pattern", "{0,time,full}", format
                .toPattern());
    }

    public void test_formatToCharacterIteratorLjava_lang_Object() {
        // Test for method formatToCharacterIterator(java.lang.Object)
        new Support_MessageFormat(
                "test_formatToCharacterIteratorLjava_lang_Object")
                .t_formatToCharacterIterator();

        try {
            new MessageFormat("{1, number}").formatToCharacterIterator(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            new MessageFormat("{0, time}").formatToCharacterIterator(new Object[]{""});
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_getLocale() {
        try {
            Locale[] l = {
                    Locale.FRANCE,
                    Locale.KOREA,
                    new Locale(Locale.FRANCE.getCountry(), Locale.FRANCE
                            .getLanguage()), new Locale("mk"),
                    new Locale("mk", "MK"), Locale.US,
                    new Locale("#ru", "@31230") };

            String pattern = "getLocale test {0,number,#,####}";
            MessageFormat mf;

            for (int i = 0; i < 0; i++) {
                mf = new MessageFormat(pattern, l[i]);
                Locale result = mf.getLocale();
                assertEquals("Returned local: " + result + " instead of "
                        + l[i], l[i], result);
                assertEquals("Returned language: " + result.getLanguage()
                        + " instead of " + l[i].getLanguage(), l[i]
                        .getLanguage(), result.getLanguage());
                assertEquals("Returned country: " + result.getCountry()
                        + " instead of " + l[i].getCountry(),
                        l[i].getCountry(), result.getCountry());
            }

            mf = new MessageFormat(pattern);
            mf.setLocale(null);
            Locale result = mf.getLocale();
            assertEquals("Returned local: " + result + " instead of null",
                    null, result);
        } catch (Exception e) {
            fail("unexpected exception " + e.toString());
        }
    }

    /**
     * java.text.MessageFormat#setFormat(int, Format) Test of method
     *        java.text.MessageFormat#setFormat(int, Format). Case 1: Compare
     *        getFormats() results after calls to setFormat(). Case 2: Try to
     *        call setFormat() using incorrect index.
     */
    public void test_setFormatILjava_text_Format() {
        try {
            // case 1: Compare getFormats() results after calls to setFormat()
            MessageFormat f1 = (MessageFormat) format1.clone();
            f1.setFormat(0, DateFormat.getTimeInstance());
            f1.setFormat(1, DateFormat.getTimeInstance());
            f1.setFormat(2, NumberFormat.getInstance());
            f1.setFormat(3, new ChoiceFormat("0#off|1#on"));
            f1.setFormat(4, new ChoiceFormat("1#few|2#ok|3#a lot"));
            f1.setFormat(5, DateFormat.getTimeInstance());

            Format[] formats = f1.getFormats();
            formats = f1.getFormats();

            Format[] correctFormats = new Format[] {
                    DateFormat.getTimeInstance(), DateFormat.getTimeInstance(),
                    NumberFormat.getInstance(), new ChoiceFormat("0#off|1#on"),
                    new ChoiceFormat("1#few|2#ok|3#a lot"),
                    DateFormat.getTimeInstance() };

            assertEquals("Test1A:Returned wrong number of formats:",
                    correctFormats.length, formats.length);
            for (int i = 0; i < correctFormats.length; i++) {
                assertEquals(
                        "Test1B:wrong format for pattern index " + i + ":",
                        correctFormats[i], formats[i]);
            }

            // case 2: Try to setFormat using incorrect index
            try {
                f1.setFormat(-1, DateFormat.getDateInstance());
                fail("Expected ArrayIndexOutOfBoundsException was not thrown");
                f1.setFormat(f1.getFormats().length, DateFormat
                        .getDateInstance());
                fail("Expected ArrayIndexOutOfBoundsException was not thrown");
            } catch (ArrayIndexOutOfBoundsException e) {
                // expected
            }
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_format$Ljava_lang_ObjectLjava_lang_StringBufferLjava_text_FieldPosition() {
        // Test for method java.lang.StringBuffer
        // java.text.MessageFormat.format(java.lang.Object [],
        // java.lang.StringBuffer, java.text.FieldPosition)
        MessageFormat format = new MessageFormat("{1,number,integer}");
        StringBuffer buffer = new StringBuffer();
        format.format(new Object[] { "0", new Double(53.863) }, buffer,
                new FieldPosition(MessageFormat.Field.ARGUMENT));
        assertEquals("Wrong result", "54", buffer.toString());

        format.format(new Object[] { "0", new Double(53.863) }, buffer,
                new FieldPosition(MessageFormat.Field.ARGUMENT));

        assertEquals("Wrong result", "5454", buffer.toString());

        buffer = new StringBuffer();
        format
                .applyPattern("{0,choice,0#zero|1#one '{1,choice,2#two {2,time}}'}");
        Date date = new Date();
        String expected = "one two "
                + DateFormat.getTimeInstance().format(date);
        format.format(new Object[] { new Double(1.6),
                new Integer(3), date }, buffer, new FieldPosition(MessageFormat
                        .Field.ARGUMENT));
        assertEquals("Choice not recursive:\n" + expected + "\n" + buffer,
                expected, buffer.toString());

        StringBuffer str = format.format(new Object[] { new Double(0.6),
                new Integer(3)}, buffer, null);

        assertEquals(expected + "zero", str.toString());
        assertEquals(expected + "zero", buffer.toString());

        try {
            format.format(new Object[] { "0", new Double(1), "" }, buffer,
                    new FieldPosition(MessageFormat.Field.ARGUMENT));
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }

        try {
            format.format(new Object[] { "",  new Integer(3)}, buffer,
                    new FieldPosition(MessageFormat.Field.ARGUMENT));
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_formatLjava_lang_StringLjava_lang_Object() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        int iCurrency = 123;
        int iInteger  = Integer.MIN_VALUE;

        Date     date     = new Date(12345678);
        Object[] args     = { date, iCurrency, iInteger };
        String   resStr   = "Date: Jan 1, 1970 Currency: $" + iCurrency
                    + ".00 Integer: -2,147,483,648";
        String   pattern  = "Date: {0,date} Currency: {1, number, currency} Integer: {2, number, integer}";
        String   sFormat  = MessageFormat.format(pattern, (Object[]) args);
        assertEquals(
                "format(String, Object[]) with valid parameters returns incorrect string: case 1",
                sFormat, resStr);

        pattern = "abc {4, number, integer} def {3,date} ghi {2,number} jkl {1,choice,0#low|1#high} mnop {0}";
        resStr  = "abc -2,147,483,648 def Jan 1, 1970 ghi -2,147,483,648 jkl high mnop -2,147,483,648";
        Object[] args_ = { iInteger, 1, iInteger, date, iInteger };
        sFormat = MessageFormat.format(pattern, args_);
        assertEquals(
                "format(String, Object[]) with valid parameters returns incorrect string: case 1",
                sFormat, resStr);

        try {
            args = null;
            MessageFormat.format(null, args);
            fail("Doesn't throw IllegalArgumentException: null, null");
        } catch (Exception e) {
            // expected
        }

        try {
            MessageFormat.format("Invalid {1,foobar} format descriptor!",
                    new Object[] {iInteger} );
            fail("Doesn't throw IllegalArgumentException with invalid pattern as a parameter: case 1");
        } catch (IllegalArgumentException ex) {
            // expected
        }

        try {
            MessageFormat.format(
                    "Invalid {1,date,invalid-spec} format descriptor!", new Object[]{""});
            fail("Doesn't throw IllegalArgumentException with invalid pattern as a parameter: case 2");
        } catch (IllegalArgumentException ex) {
            // expected
        }

        try {
            MessageFormat.format("{0,number,integer", new Object[] {iInteger});
            fail("Doesn't throw IllegalArgumentException, doesn't detect unmatched brackets");
        } catch (IllegalArgumentException ex) {
            // expected
        }

        try {
            MessageFormat.format(
                    "Valid {1, date} format {0, number} descriptor!", new Object[]{ "" } );
            fail("Doesn't throw IllegalArgumentException with invalid Object array");
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    public void test_formatLjava_lang_ObjectLjava_lang_StringBufferLjava_text_FieldPosition() {
        // Test for method java.lang.StringBuffer
        // java.text.MessageFormat.format(java.lang.Object,
        // java.lang.StringBuffer, java.text.FieldPosition)
        new Support_MessageFormat(
                "test_formatLjava_lang_ObjectLjava_lang_StringBufferLjava_text_FieldPosition")
                .t_format_with_FieldPosition();

        String pattern = "On {4,date} at {3,time}, he ate {2,number, integer} " +
                "hamburger{2,choice,1#|1<s}.";
        MessageFormat format = new MessageFormat(pattern, Locale.US);

        Object[] objects = new Object[] { "", new Integer(3), 8, ""};

        try {
            format.format(objects, new StringBuffer(),
                    new FieldPosition(DateFormat.Field.AM_PM));
            fail("IllegalArgumentException was not thrown.");
        } catch(IllegalArgumentException iae) {
            //expected
        }
    }

    public void test_setFormats$Ljava_text_Format() {
        try {
            MessageFormat f1 = (MessageFormat) format1.clone();

            // case 1: Test with repeating formats and max argument index < max
            // offset
            // compare getFormats() results after calls to setFormats(Format[])
            Format[] correctFormats = new Format[] {
                    DateFormat.getTimeInstance(),
                    new ChoiceFormat("0#off|1#on"),
                    DateFormat.getTimeInstance(),
                    NumberFormat.getCurrencyInstance(),
                    new ChoiceFormat("1#few|2#ok|3#a lot") };

            f1.setFormats(correctFormats);
            Format[] formats = f1.getFormats();

            assertTrue("Test1A:Returned wrong number of formats:",
                    correctFormats.length <= formats.length);
            for (int i = 0; i < correctFormats.length; i++) {
                assertEquals("Test1B:wrong format for argument index " + i
                        + ":", correctFormats[i], formats[i]);
            }

            // case 2: Try to pass null argument to setFormats().
            try {
                f1.setFormats(null);
                fail("Expected exception NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_parseLjava_lang_String() throws ParseException {
        String pattern = "A {3, number, currency} B {2, time} C {0, number, percent} D {4}  E {1,choice,0#off|1#on} F {0, date}";
        MessageFormat mf = new MessageFormat(pattern);
        String sToParse = "A $12,345.00 B 9:56:07 AM C 3,200% D 1/15/70 9:56 AM  E on F Jan 1, 1970";
        Object[] result;
        try {
            result = mf.parse(sToParse);

            assertTrue("No result: " + result.length, result.length == 5);
            assertTrue("Object 0 is not date", result[0] instanceof Date);
            assertEquals("Object 1 is not stringr", result[1].toString(), "1.0");
            assertTrue("Object 2 is not date", result[2] instanceof Date);
            assertEquals("Object 3 is not number", result[3].toString(),
                    "12345");
            assertEquals("Object 4 is not string", result[4].toString(),
                    "1/15/70 9:56 AM");

        } catch (java.text.ParseException pe) {
            fail("ParseException is thrown for incorrect string " + sToParse);
        }

        sToParse = "xxdate is Feb 28, 1999";
        try {
            result = format1.parse(sToParse);
            fail("ParseException is thrown for incorrect string " + sToParse);
        } catch (java.text.ParseException pe) {
            // expected
        }

        sToParse = "vm=Test, @3 4 6, 3   ";
        mf = new MessageFormat("vm={0},{1},{2}");
        try {
            result = mf.parse(sToParse);
            assertTrue("No result: " + result.length, result.length == 3);
            assertEquals("Object 0 is not string", result[0].toString(), "Test");
            assertEquals("Object 1 is not string", result[1].toString(),
                    " @3 4 6");
            assertEquals("Object 2 is not string", result[2].toString(),
                    " 3   ");
        } catch (java.text.ParseException pe) {
            fail("ParseException is thrown for correct string " + sToParse);
        }

        try {
            result = mf.parse(null);
            fail("ParseException is not thrown for null " + sToParse);
        } catch (java.text.ParseException pe) {
            // expected
        }
    }

    /**
     * java.text.MessageFormat#parseObject(java.lang.String,
     *        java.text.ParsePosition) Test of method
     *        java.text.MessageFormat#parseObject(java.lang.String,
     *        java.text.ParsePosition). Case 1: Parsing of correct data string.
     *        Case 2: Parsing of partial correct data string. Case 3: Try to use
     *        argument ParsePosition as null.
     */
    public void test_parseObjectLjava_lang_StringLjavajava_text_ParsePosition() {
        MessageFormat mf = new MessageFormat("{0,number,#.##}, {0,number,#.#}");
        try {
            // case 1: Try to parse correct data string.
            Object[] objs = { new Double(3.1415) };
            String result = mf.format(objs);
            // result now equals "3.14, 3.1"
            Object[] res = null;
            ParsePosition pp = new ParsePosition(0);
            int parseIndex = pp.getIndex();
            res = (Object[]) mf.parseObject(result, pp);
            assertTrue("Parse operation return null", res != null);
            assertTrue("parse operation return array with incorrect length",
                    1 == res.length);
            assertTrue("ParseIndex is incorrect", pp.getIndex() != parseIndex);
            assertTrue("Result object is incorrect", new Double(3.1)
                    .equals(res[0]));

            // case 2: Try to parse partially correct data string.
            pp.setIndex(0);
            char[] cur = result.toCharArray();
            cur[cur.length / 2] = 'Z';
            String partialCorrect = new String(cur);
            res = (Object[]) mf.parseObject(partialCorrect, pp);
            assertTrue("Parse operation return null", res == null);
            assertTrue("ParseIndex is incorrect", pp.getIndex() == 0);
            assertTrue("ParseErrorIndex is incorrect",
                    pp.getErrorIndex() == cur.length / 2);

            // case 3: Try to use argument ParsePosition as null.
            try {
                mf.parseObject(result, null);
                fail("Expected NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_format_Object() {
        // Regression for HARMONY-1875
        Locale.setDefault(Locale.CANADA);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String pat = "text here {0,date,yyyyyyyyy} and here";
        Calendar c = Calendar.getInstance();
        String etalon = "text here 00000" + c.get(Calendar.YEAR) + " and here";
        MessageFormat obj = new MessageFormat(pat);
        assertEquals(etalon, obj.format(new Object[] { new Date() }));
    }

    public void test_parseLjava_lang_StringLjava_text_ParsePosition() {
        ParsePosition pos = new ParsePosition(2);

        MessageFormat mf = new MessageFormat("{0}; {0}; {0}");
        String parse = "a; b; c";
        try {
            mf.parse(parse, null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            mf.parse(null, pos);
        } catch(NullPointerException npe) {
            fail("NullPointerException was thrown.");
        }
    }
}
