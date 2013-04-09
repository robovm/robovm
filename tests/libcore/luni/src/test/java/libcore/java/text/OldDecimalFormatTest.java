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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.BitSet;
import java.util.Locale;
import junit.framework.TestCase;
import tests.support.Support_DecimalFormat;

public class OldDecimalFormatTest extends TestCase {

    public void test_formatToCharacterIterator() throws Exception {
        AttributedCharacterIterator iterator;
        int[] runStarts;
        int[] runLimits;
        String result;
        char current;

        // For BigDecimal with multiplier test.
        DecimalFormat df = new DecimalFormat();
        df.setMultiplier(10);
        iterator = df.formatToCharacterIterator(new BigDecimal("12345678901234567890"));
        result = "123,456,789,012,345,678,900";
        current = iterator.current();
        for (int i = 0; i < result.length(); i++) {
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }

        // For BigDecimal with multiplier test.
        df = new DecimalFormat();
        df.setMultiplier(-1);
        df.setMaximumFractionDigits(20);
        iterator = df.formatToCharacterIterator(new BigDecimal("1.23456789012345678901"));
        result = "-1.23456789012345678901";
        current = iterator.current();
        for (int i = 0; i < result.length(); i++) {
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }

        iterator = new DecimalFormat()
                .formatToCharacterIterator(new BigDecimal("1.23456789E1234"));
        runStarts = new int[] {0, 0, 2, 3, 3, 3, 6, 7, 7, 7, 10, 11, 11, 11, 14};
        runLimits = new int[] {2, 2, 3, 6, 6, 6, 7, 10, 10, 10, 11, 14, 14, 14, 15};
        result = "12,345,678,900,"; // 000,000,000,000....
        current = iterator.current();
        for (int i = 0; i < runStarts.length; i++) {
            assertEquals("wrong start @" + i, runStarts[i], iterator.getRunStart());
            assertEquals("wrong limit @" + i, runLimits[i], iterator.getRunLimit());
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }
        assertEquals(0, iterator.getBeginIndex());
        assertEquals(1646, iterator.getEndIndex());

        iterator = new DecimalFormat()
                .formatToCharacterIterator(new BigDecimal("1.23456789E301"));
        runStarts = new int[] {0, 0, 2, 3, 3, 3, 6, 7, 7, 7, 10, 11, 11, 11, 14};
        runLimits = new int[] {2, 2, 3, 6, 6, 6, 7, 10, 10, 10, 11, 14, 14, 14, 15};
        result = "12,345,678,900,"; // 000,000,000,000....
        current = iterator.current();
        for (int i = 0; i < runStarts.length; i++) {
            assertEquals("wrong start @" + i, runStarts[i], iterator.getRunStart());
            assertEquals("wrong limit @" + i, runLimits[i], iterator.getRunLimit());
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }
        assertEquals(0, iterator.getBeginIndex());
        assertEquals(402, iterator.getEndIndex());

        iterator = new DecimalFormat()
                .formatToCharacterIterator(new BigDecimal("1.2345678E4"));
        runStarts = new int[] {0, 0, 2, 3, 3, 3, 6, 7, 7, 7};
        runLimits = new int[] {2, 2, 3, 6, 6, 6, 7, 10, 10, 10};
        result = "12,345.678";
        current = iterator.current();
        for (int i = 0; i < runStarts.length; i++) {
            assertEquals("wrong start @" + i, runStarts[i], iterator.getRunStart());
            assertEquals("wrong limit @" + i, runLimits[i], iterator.getRunLimit());
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }
        assertEquals(0, iterator.getBeginIndex());
        assertEquals(10, iterator.getEndIndex());

        iterator = new DecimalFormat()
                .formatToCharacterIterator(new BigInteger("123456789"));
        runStarts = new int[] {0, 0, 0, 3, 4, 4, 4, 7, 8, 8, 8};
        runLimits = new int[] {3, 3, 3, 4, 7, 7, 7, 8, 11, 11, 11};
        result = "123,456,789";
        current = iterator.current();
        for (int i = 0; i < runStarts.length; i++) {
            assertEquals("wrong start @" + i, runStarts[i], iterator.getRunStart());
            assertEquals("wrong limit @" + i, runLimits[i], iterator.getRunLimit());
            assertEquals("wrong char @" + i, result.charAt(i), current);
            current = iterator.next();
        }
        assertEquals(0, iterator.getBeginIndex());
        assertEquals(11, iterator.getEndIndex());
    }

    /*
     * Test the getter and setter of parseBigDecimal and parseIntegerOnly and
     * test the default value of them.
     */
    public void test_isParseBigDecimalLjava_lang_Boolean_isParseIntegerOnlyLjava_lang_Boolean() {

        // parseBigDecimal default to false
        DecimalFormat form = (DecimalFormat) DecimalFormat
                .getInstance(Locale.US);
        assertFalse(form.isParseBigDecimal());
        form.setParseBigDecimal(true);
        assertTrue(form.isParseBigDecimal());

        try {
            Number result = form.parse("123.123");
            assertEquals(new BigDecimal("123.123"), result);
        } catch (ParseException e) {
            fail("ParseException was thrown.");
        }

        form.setParseBigDecimal(false);
        assertFalse(form.isParseBigDecimal());

        try {
            Number result = form.parse("123.123");
            assertFalse(result instanceof BigDecimal);
        } catch (ParseException e) {
            fail("ParseException was thrown.");
        }

        // parseIntegerOnly default to false
        assertFalse(form.isParseIntegerOnly());
    }

    public void test_isParseIntegerOnly() {

            DecimalFormat format = new DecimalFormat();
            assertFalse("Default value of isParseIntegerOnly is true",
                    format.isParseIntegerOnly());

            format.setParseIntegerOnly(true);
            assertTrue(format.isParseIntegerOnly());
            try {
                Number result = format.parse("123.123");
                assertEquals(new Long("123"), result);
            } catch (ParseException e) {
                fail("ParseException was thrown.");
            }

            format.setParseIntegerOnly(false);
            assertFalse(format.isParseIntegerOnly());
            try {
                Number result = format.parse("123.123");
                assertEquals(new Double("123.123"), result);
            } catch (ParseException e) {
                fail("ParseException was thrown.");
            }
    }

    public void test_isGroupingUsed() {
        String [] patterns = {"####.##", "######.######", "000000.000000",
                "######.000000", "000000.######", " ###.###", "$#####.######",
                "$$####.######"};

        for(String pattern:patterns) {
            DecimalFormat format = new DecimalFormat(pattern);
            assertFalse(format.isGroupingUsed());
        }

        DecimalFormat format = new DecimalFormat("###,####");
        assertTrue(format.isGroupingUsed());
    }

    // Test the type of the returned object
    public void test_parseLjava_lang_String_Ljava_text_ParsePosition() {
        DecimalFormat form = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        form.setParseIntegerOnly(true);
        form.setParseBigDecimal(true);

        final String doubleMax2 = "359,538,626,972,463,141,629,054,847,463,408,"
            + "713,596,141,135,051,689,993,197,834,953,606,314,521,560,057,077,"
            + "521,179,117,265,533,756,343,080,917,907,028,764,928,468,642,653,"
            + "778,928,365,536,935,093,407,075,033,972,099,821,153,102,564,152,"
            + "490,980,180,778,657,888,151,737,016,910,267,884,609,166,473,806,"
            + "445,896,331,617,118,664,246,696,549,595,652,408,289,446,337,476,"
            + "354,361,838,599,762,500,808,052,368,249,716,736";
        Number number = form.parse(doubleMax2, new ParsePosition(0));
        assertTrue(number instanceof BigDecimal);
        BigDecimal result = (BigDecimal)number;
        assertEquals(new BigDecimal(Double.MAX_VALUE).add(new BigDecimal(
                Double.MAX_VALUE)), result);
    }

    // AndroidOnly: Difference to RI
    public void test_getMaximumIntegerDigits_AndroidOnly() {
        final int maxIntDigit = 309;

        // When use default locale, in this case zh_CN
        // the returned instance of NumberFormat is a DecimalFormat
        DecimalFormat form = new DecimalFormat("00.###E0");
        NumberFormat nform = DecimalFormat.getInstance(Locale.US);
        nform = DecimalFormat.getInstance(Locale.US);
        form = null;
        if (nform instanceof DecimalFormat) {
            form = (DecimalFormat) nform;
        }
        // getMaximumIntegerDigits from NumberFormat default to 309
        // getMaximumIntegerDigits from DecimalFormat default to 309
        // the following 2 assertions will fail on RI implementation, since the
        // implementation of ICU and RI are not identical. RI does not give
        // DecimalFormat an initial bound about its maximumIntegerDigits
        // (default to Integer.MAX_VALUE: 2147483647 )
        assertEquals(maxIntDigit, nform.getMaximumIntegerDigits());
        assertEquals(maxIntDigit, form.getMaximumIntegerDigits());
    }

    // AndroidOnly: second 0 needs to be quoted in icu.
    // (quoting special characters in prefix and suffix necessary)
    public void test_getMaximumIntegerDigits2() {
        // regression test for HARMONY-878
        assertTrue(new DecimalFormat("0\t'0'").getMaximumIntegerDigits() > 0);
    }

    public void test_setPositivePrefixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getPositivePrefix());

        format.setPositivePrefix("PosPrf");
        assertEquals("PosPrf", format.getPositivePrefix());
        try {
            assertTrue(format.parse("PosPrf123.45").doubleValue() == 123.45);
        } catch(java.text.ParseException pe) {
            fail("ParseException was thrown.");
        }

        format.setPositivePrefix("");
        assertEquals("", format.getPositivePrefix());

        format.setPositivePrefix(null);
        assertNull(format.getPositivePrefix());
    }
    public void test_setPositiveSuffixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getPositiveSuffix());

        format.setPositiveSuffix("PosSfx");
        assertEquals("PosSfx", format.getPositiveSuffix());
        try {
            assertTrue(format.parse("123.45PosSfx").doubleValue() == 123.45);
        } catch(java.text.ParseException pe) {
            fail("ParseException was thrown.");
        }

        format.setPositiveSuffix("");
        assertEquals("", format.getPositiveSuffix());

        format.setPositiveSuffix(null);
        assertNull(format.getPositiveSuffix());
    }
    public void test_setNegativePrefixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("-", format.getNegativePrefix());

        format.setNegativePrefix("NegPrf");
        assertEquals("NegPrf", format.getNegativePrefix());
        try {
            assertTrue(format.parse("NegPrf123.45").doubleValue() == -123.45);
        } catch(java.text.ParseException pe) {
            fail("ParseException was thrown.");
        }
        format.setNegativePrefix("");
        assertEquals("", format.getNegativePrefix());

        format.setNegativePrefix(null);
        assertNull(format.getNegativePrefix());
    }
    public void test_setNegativeSuffixLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        assertEquals("", format.getNegativeSuffix());

        format.setNegativeSuffix("NegSfx");
        assertEquals("NegSfx", format.getNegativeSuffix());
        try {
            assertTrue(format.parse("123.45NegPfx").doubleValue() == 123.45);
        } catch(java.text.ParseException pe) {
            fail("ParseException was thrown.");
        }

        format.setNegativeSuffix("");
        assertEquals("", format.getNegativeSuffix());

        format.setNegativeSuffix(null);
        assertNull(format.getNegativeSuffix());
    }

    public void test_toLocalizedPattern() {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        try {
            format.applyLocalizedPattern("#.#");
            assertEquals("Wrong pattern 1", "#0.#", format.toLocalizedPattern());
            format.applyLocalizedPattern("#.");
            assertEquals("Wrong pattern 2", "#0.", format.toLocalizedPattern());
            format.applyLocalizedPattern("#");
            assertEquals("Wrong pattern 3", "#", format.toLocalizedPattern());
            format.applyLocalizedPattern(".#");
            assertEquals("Wrong pattern 4", "#.0", format.toLocalizedPattern());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_toPattern() {
        DecimalFormat format = new DecimalFormat();
        try {
            format.applyPattern("#.#");
            assertEquals("Wrong pattern 1", "#0.#", format.toPattern());
            format.applyPattern("#.");
            assertEquals("Wrong pattern 2", "#0.", format.toPattern());
            format.applyPattern("#");
            assertEquals("Wrong pattern 3", "#", format.toPattern());
            format.applyPattern(".#");
            assertEquals("Wrong pattern 4", "#.0", format.toPattern());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }
    public void test_setGroupingUse() {
        DecimalFormat format = new DecimalFormat();

        StringBuffer buf = new StringBuffer();
        format.setGroupingUsed(false);
        format.format(new Long(1970), buf, new FieldPosition(0));
        assertEquals("1970", buf.toString());
        assertFalse(format.isGroupingUsed());
        format.format(new Long(1970), buf, new FieldPosition(0));
        assertEquals("19701970", buf.toString());
        assertFalse(format.isGroupingUsed());

        format.setGroupingUsed(true);
        format.format(new Long(1970), buf, new FieldPosition(0));
        assertEquals("197019701,970", buf.toString());
        assertTrue(format.isGroupingUsed());
    }

    public void test_Constructor() {
        // Test for method java.text.DecimalFormat()
        // the constructor form that specifies a pattern is equal to the form
        // constructed with no pattern and applying that pattern using the
        // applyPattern call
        try {
            DecimalFormat format1 = new DecimalFormat();
            format1.applyPattern("'$'1000.0000");
            DecimalFormat format2 = new DecimalFormat();
            format2.applyPattern("'$'1000.0000");
            assertTrue(
                    "Constructed format did not match applied format object",
                    format2.equals(format1));
            DecimalFormat format3 = new DecimalFormat("'$'1000.0000");
            assertTrue(
                    "Constructed format did not match applied format object",
                    format3.equals(format1));
            DecimalFormat format4 = new DecimalFormat("'$'8000.0000");
            assertTrue(
                    "Constructed format did not match applied format object",
                    !format4.equals(format1));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_ConstructorLjava_lang_String() {
        // Test for method java.text.DecimalFormat(java.lang.String)
        // the constructor form that specifies a pattern is equal to the form
        // constructed with no pattern and applying that pattern using the
        // applyPattern call
        DecimalFormat format = new DecimalFormat("'$'0000.0000");
        DecimalFormat format1 = new DecimalFormat();
        format1.applyPattern("'$'0000.0000");
        assertTrue("Constructed format did not match applied format object",
                format.equals(format1));

        String [] patterns = {"####.##", "######.######", "000000.000000",
                "######.000000", "000000.######", " ###.###", "$#####.######",
                "$$####.######", "%#,##,###,####", "#,##0.00;(#,##0.00)"};

        for(String str:patterns) {
            new DecimalFormat(str);
        }

        try {
            new DecimalFormat(null);
            fail("NullPointerException wasn't thrown.");
        } catch(NullPointerException npe){
            //expected
        }

        String [] incPatterns = {"%#,##,###,####'", "#.##0.00"};
        for(String str:incPatterns) {
            try {
                new DecimalFormat(str);
                fail("IllegalArgumentException wasn't thrown for pattern: " + str);
            } catch(IllegalArgumentException iae){
                //expected
            }
        }
    }

    /**
     * Case 1: Try to construct object using correct pattern and fromat symbols.
     * Case 2: Try to construct object using null arguments.
     * Case 3: Try to construct object using incorrect pattern.
     */
    public void test_ConstructorLjava_lang_StringLjava_text_DecimalFormatSymbols() {
        try {
            // case 1: Try to construct object using correct pattern and fromat
            // symbols.
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.CANADA);
            DecimalFormat format1 = new DecimalFormat("'$'1000.0000", dfs);
            DecimalFormat format2 = new DecimalFormat();
            format2.applyPattern("'$'1000.0000");
            format2.setDecimalFormatSymbols(dfs);
            assertTrue(
                    "Constructed format did not match applied format object",
                    format2.equals(format1));
            assertTrue(
                    "Constructed format did not match applied format object",
                    !format1.equals(new DecimalFormat("'$'1000.0000",
                            new DecimalFormatSymbols(Locale.CHINA))));

            // case 2: Try to construct object using null arguments.
            try {
                new DecimalFormat("'$'1000.0000", (DecimalFormatSymbols) null);
                fail("Expected NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }
            try {
                new DecimalFormat(null, new DecimalFormatSymbols());
                fail("Expected NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }
            try {
                new DecimalFormat(null, (DecimalFormatSymbols) null);
                fail("Expected NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }

            // case 3: Try to construct object using incorrect pattern.
            try {
                new DecimalFormat("$'", new DecimalFormatSymbols());
                fail("Expected IllegalArgumentException was not thrown");
            } catch (IllegalArgumentException e) {
                // expected
            }
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * Case 1: Try to apply correct variants of pattern.
     * Case 2: Try to apply malformed patten. Case 3: Try to apply null pattern.
     */
    public void test_applyLocalizedPatternLjava_lang_String() {
        DecimalFormat format = new DecimalFormat();
        try {
            // case 1: Try to apply correct variants of pattern.
            format.applyLocalizedPattern("#.#");
            assertEquals("Wrong pattern 1", "#0.#", format.toLocalizedPattern());
            format.applyLocalizedPattern("#.");
            assertEquals("Wrong pattern 2", "#0.", format.toLocalizedPattern());
            format.applyLocalizedPattern("#");
            assertEquals("Wrong pattern 3", "#", format.toLocalizedPattern());
            format.applyLocalizedPattern(".#");
            assertEquals("Wrong pattern 4", "#.0", format.toLocalizedPattern());

            // case 2: Try to apply malformed patten.
            try {
                format.applyLocalizedPattern("'#,#:#0.0#;(#)");
                fail("Expected IllegalArgumentException was not thrown");
            } catch (IllegalArgumentException e) {
                // expected
            }

            // case 3: Try to apply null patern.
            try {
                format.applyLocalizedPattern((String) null);
                fail("Expected NullPointerException was not thrown");
            } catch (NullPointerException e) {
                // expected
            }
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_applyPatternLjava_lang_String() {
        DecimalFormat format = new DecimalFormat("#.#");
        assertEquals("Wrong pattern 1", "#0.#", format.toPattern());
        format = new DecimalFormat("#.");
        assertEquals("Wrong pattern 2", "#0.", format.toPattern());
        format = new DecimalFormat("#");
        assertEquals("Wrong pattern 3", "#", format.toPattern());
        format = new DecimalFormat(".#");
        assertEquals("Wrong pattern 4", "#.0", format.toPattern());

        DecimalFormat decFormat = new DecimalFormat("#.#");

        try {
            decFormat.applyPattern(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        String [] incPatterns = {"%#,##,###,####'", "#.##0.00"};
        for(String str:incPatterns) {
            try {
                decFormat.applyPattern(str);
                fail("IllegalArgumentException was not thrown for pattern: " +
                        str);
            } catch(IllegalArgumentException  iae) {
                //expected
            }
        }
    }

    // AndroidOnly: icu supports 2 grouping sizes
    public void test_applyPatternLjava_lang_String2() {
        DecimalFormat decFormat = new DecimalFormat("#.#");
        String [] patterns = {"####.##", "######.######", "000000.000000",
                "######.000000", "000000.######", " ###.###", "$#####.######",
                "$$####.######", "%#,##,###,####", "#,##0.00;(#,##0.00)",
                 "##.##-E"};

        String [] expResult = {"#0.##", "#0.######", "#000000.000000",
                "#.000000", "#000000.######", " #0.###", "$#0.######",
                "$$#0.######",
                "%#,###,####", // icu only. icu supports two grouping sizes
                "#,##0.00;(#,##0.00)",
                "#0.##-'E'"}; // icu only. E in the suffix does not need to be
                              // quoted. This is done automatically.

        for (int i = 0; i < patterns.length; i++) {
            decFormat.applyPattern(patterns[i]);
            String result = decFormat.toPattern();
            assertEquals("Failed to apply following pattern: " + patterns[i] +
                    " expected: " + expResult[i] + " returned: " + result,
                    expResult[i], result);
        }
    }

    public void test_clone() {
        DecimalFormat format = (DecimalFormat) DecimalFormat
                .getInstance(Locale.US);
        DecimalFormat cloned = (DecimalFormat) format.clone();
        assertEquals(cloned.getDecimalFormatSymbols(), format
                .getDecimalFormatSymbols());

        format = new DecimalFormat("'$'0000.0000");
        DecimalFormat format1 = (DecimalFormat) (format.clone());
        // make sure the objects are equal
        assertTrue("Object's clone isn't equal!", format.equals(format1));
        // change the content of the clone and make sure it's not equal anymore
        // verifies that it's data is now distinct from the original
        format1.applyPattern("'$'0000.####");
        assertTrue("Object's changed clone should not be equal!", !format
                .equals(format1));
    }

    private void compare(String testName, String format, String expected) {
        assertTrue(testName + " got: " + format + " expected: " + expected,
                format.equals(expected));
    }

    private boolean compare(int count, String format, String expected) {
        boolean result = format.equals(expected);
        if (!result)
            System.out.println("Failure test: " + count + " got: " + format
                    + " expected: " + expected);
        return result;
    }

    public void test_formatDLjava_lang_StringBufferLjava_text_FieldPosition() {
        new Support_DecimalFormat(
                "test_formatDLjava_lang_StringBufferLjava_text_FieldPosition")
                .t_format_with_FieldPosition();

        int failCount = 0;
        BitSet failures = new BitSet();

        final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);

        DecimalFormat df = new DecimalFormat("00.0#E0", dfs);
        compare("00.0#E0: 0.0", df.format(0.0), "00.0E0");
        compare("00.0#E0: 1.0", df.format(1.0), "10.0E-1");
        compare("00.0#E0: 12.0", df.format(12.0), "12.0E0");
        compare("00.0#E0: 123.0", df.format(123.0), "12.3E1");
        compare("00.0#E0: 1234.0", df.format(1234.0), "12.34E2");
        compare("00.0#E0: 12346.0", df.format(12346.0), "12.35E3");
        compare("00.0#E0: 99999.0", df.format(99999.0), "10.0E4");
        compare("00.0#E0: 1.2", df.format(1.2), "12.0E-1");
        compare("00.0#E0: 12.3", df.format(12.3), "12.3E0");
        compare("00.0#E0: 123.4", df.format(123.4), "12.34E1");
        compare("00.0#E0: 1234.6", df.format(1234.6), "12.35E2");
        compare("00.0#E0: 9999.9", df.format(9999.9), "10.0E3");
        compare("00.0#E0: 0.1", df.format(0.1), "10.0E-2");
        compare("00.0#E0: 0.12", df.format(0.12), "12.0E-2");
        compare("00.0#E0: 0.123", df.format(0.123), "12.3E-2");
        compare("00.0#E0: 0.1234", df.format(0.1234), "12.34E-2");
        compare("00.0#E0: 0.12346", df.format(0.12346), "12.35E-2");
        compare("00.0#E0: 0.99999", df.format(0.99999), "10.0E-1");
        compare("00.0#E0: -0.0", df.format(-0.0), "-00.0E0");
        compare("00.0#E0: -1.0", df.format(-1.0), "-10.0E-1");
        compare("00.0#E0: -12.0", df.format(-12.0), "-12.0E0");
        compare("00.0#E0: -123.0", df.format(-123.0), "-12.3E1");
        compare("00.0#E0: -1234.0", df.format(-1234.0), "-12.34E2");
        compare("00.0#E0: -12346.0", df.format(-12346.0), "-12.35E3");
        compare("00.0#E0: -99999.0", df.format(-99999.0), "-10.0E4");

        df = new DecimalFormat("##0.0E0", dfs);
        compare("##0.0E0: -0.0", df.format(-0.0), "-0.0E0");
        compare("##0.0E0: 0.0", df.format(0.0), "0.0E0");
        compare("##0.0E0: 1.0", df.format(1.0), "1.0E0");
        compare("##0.0E0: 12.0", df.format(12.0), "12E0");
        compare("##0.0E0: 123.0", df.format(123.0), "123E0");  // Android fails, here!
        compare("##0.0E0: 1234.0", df.format(1234.0), "1.234E3");
        compare("##0.0E0: 12346.0", df.format(12346.0), "12.35E3");
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(99999.0), "100E3"))
            failures.set(failCount);
        failCount++;
        compare("##0.0E0: 999999.0", df.format(999999.0), "1.0E6");

        df = new DecimalFormat("#00.0##E0", dfs);
        compare("#00.0##E0: 0.1", df.format(0.1), "100E-3");
        compare("#00.0##E0: 0.12", df.format(0.12), "120E-3");
        compare("#00.0##E0: 0.123", df.format(0.123), "123E-3");
        compare("#00.0##E0: 0.1234", df.format(0.1234), "123.4E-3");
        compare("#00.0##E0: 0.1234567", df.format(0.1234567), "123.457E-3");
        compare("#00.0##E0: 0.01", df.format(0.01), "10.0E-3");
        compare("#00.0##E0: 0.012", df.format(0.012), "12.0E-3");
        compare("#00.0##E0: 0.0123", df.format(0.0123), "12.3E-3");
        compare("#00.0##E0: 0.01234", df.format(0.01234), "12.34E-3");
        compare("#00.0##E0: 0.01234567", df.format(0.01234567), "12.3457E-3");
        compare("#00.0##E0: 0.001", df.format(0.001), "1.00E-3");
        compare("#00.0##E0: 0.0012", df.format(0.0012), "1.20E-3");
        compare("#00.0##E0: 0.00123", df.format(0.00123), "1.23E-3");
        compare("#00.0##E0: 0.001234", df.format(0.001234), "1.234E-3");
        compare("#00.0##E0: 0.001234567", df.format(0.001234567), "1.23457E-3");
        compare("#00.0##E0: 0.0001", df.format(0.0001), "100E-6");
        compare("#00.0##E0: 0.00012", df.format(0.00012), "120E-6");
        compare("#00.0##E0: 0.000123", df.format(0.000123), "123E-6");
        compare("#00.0##E0: 0.0001234", df.format(0.0001234), "123.4E-6");
        compare("#00.0##E0: 0.0001234567", df.format(0.0001234567),
                "123.457E-6");

        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.0), "0.00E0"))
            failures.set(failCount);
        failCount++;
        compare("#00.0##E0: 1.0", df.format(1.0), "1.00E0");
        compare("#00.0##E0: 12.0", df.format(12.0), "12.0E0");
        compare("#00.0##E0: 123.0", df.format(123.0), "123E0");
        compare("#00.0##E0: 1234.0", df.format(1234.0), "1.234E3");
        compare("#00.0##E0: 12345.0", df.format(12345.0), "12.345E3");
        compare("#00.0##E0: 123456.0", df.format(123456.0), "123.456E3");
        compare("#00.0##E0: 1234567.0", df.format(1234567.0), "1.23457E6");
        compare("#00.0##E0: 12345678.0", df.format(12345678.0), "12.3457E6");
        compare("#00.0##E0: 99999999.0", df.format(99999999.0), "100E6");

        df = new DecimalFormat("#.0E0", dfs);
        compare("#.0E0: -0.0", df.format(-0.0), "-.0E0");
        compare("#.0E0: 0.0", df.format(0.0), ".0E0");
        compare("#.0E0: 1.0", df.format(1.0), ".1E1");
        compare("#.0E0: 12.0", df.format(12.0), ".12E2");
        compare("#.0E0: 123.0", df.format(123.0), ".12E3");
        compare("#.0E0: 1234.0", df.format(1234.0), ".12E4");
        compare("#.0E0: 9999.0", df.format(9999.0), ".1E5");

        df = new DecimalFormat("0.#E0", dfs);
        compare("0.#E0: -0.0", df.format(-0.0), "-0E0");
        compare("0.#E0: 0.0", df.format(0.0), "0E0");
        compare("0.#E0: 1.0", df.format(1.0), "1E0");
        compare("0.#E0: 12.0", df.format(12.0), "1.2E1");
        compare("0.#E0: 123.0", df.format(123.0), "1.2E2");
        compare("0.#E0: 1234.0", df.format(1234.0), "1.2E3");
        compare("0.#E0: 9999.0", df.format(9999.0), "1E4");

        df = new DecimalFormat(".0E0", dfs);
        compare(".0E0: -0.0", df.format(-0.0), "-.0E0");
        compare(".0E0: 0.0", df.format(0.0), ".0E0");
        compare(".0E0: 1.0", df.format(1.0), ".1E1");
        compare(".0E0: 12.0", df.format(12.0), ".1E2");
        compare(".0E0: 123.0", df.format(123.0), ".1E3");
        compare(".0E0: 1234.0", df.format(1234.0), ".1E4");
        compare(".0E0: 9999.0", df.format(9999.0), ".1E5");

        df = new DecimalFormat("0.E0", dfs);
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.0), "0.E0"))
            failures.set(failCount);
        failCount++;
        if (!compare(failCount, df.format(1.0), "1.E0"))
            failures.set(failCount);
        failCount++;
        if (!compare(failCount, df.format(12.0), "1.E1"))
            failures.set(failCount);
        failCount++;
        if (!compare(failCount, df.format(123.0), "1.E2"))
            failures.set(failCount);
        failCount++;
        if (!compare(failCount, df.format(1234.0), "1.E3"))
            failures.set(failCount);
        failCount++;
        if (!compare(failCount, df.format(9999.0), "1.E4"))
            failures.set(failCount);
        failCount++;

        df = new DecimalFormat("##0.00#E0", dfs);
        compare("##0.00#E0: 0.1", df.format(0.1), "100E-3");
        compare("##0.00#E0: 0.1234567", df.format(0.1234567), "123.457E-3");
        compare("##0.00#E0: 0.9999999", df.format(0.9999999), "1.00E0");
        compare("##0.00#E0: 0.01", df.format(0.01), "10.0E-3");
        compare("##0.00#E0: 0.01234567", df.format(0.01234567), "12.3457E-3");
        compare("##0.00#E0: 0.09999999", df.format(0.09999999), "100E-3");
        compare("##0.00#E0: 0.001", df.format(0.001), "1.00E-3");
        compare("##0.00#E0: 0.001234567", df.format(0.001234567), "1.23457E-3");
        compare("##0.00#E0: 0.009999999", df.format(0.009999999), "10.0E-3");
        compare("##0.00#E0: 0.0001", df.format(0.0001), "100E-6");
        compare("##0.00#E0: 0.0001234567", df.format(0.0001234567),
                "123.457E-6");
        compare("##0.00#E0: 0.0009999999", df.format(0.0009999999), "1.00E-3");

        df = new DecimalFormat("###0.00#E0", dfs);
        compare("###0.00#E0: 0.1", df.format(0.1), "1000E-4");
        compare("###0.00#E0: 0.12345678", df.format(0.12345678), "1234.568E-4");
        compare("###0.00#E0: 0.99999999", df.format(0.99999999), "1.00E0");
        compare("###0.00#E0: 0.01", df.format(0.01), "100E-4");
        compare("###0.00#E0: 0.012345678", df.format(0.012345678),
                "123.4568E-4");
        compare("###0.00#E0: 0.099999999", df.format(0.099999999), "1000E-4");
        compare("###0.00#E0: 0.001", df.format(0.001), "10.0E-4");
        compare("###0.00#E0: 0.0012345678", df.format(0.0012345678),
                "12.34568E-4");
        compare("###0.00#E0: 0.0099999999", df.format(0.0099999999), "100E-4");
        compare("###0.00#E0: 0.0001", df.format(0.0001), "1.00E-4");
        compare("###0.00#E0: 0.00012345678", df.format(0.00012345678),
                "1.234568E-4");
        compare("###0.00#E0: 0.00099999999", df.format(0.00099999999),
                "10.0E-4");
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.00001), "1000E-8"))
            failures.set(failCount);
        failCount++;
        compare("###0.00#E0: 0.000012345678", df.format(0.000012345678),
                "1234.568E-8");
        compare("###0.00#E0: 0.000099999999", df.format(0.000099999999),
                "1.00E-4");

        df = new DecimalFormat("###0.0#E0", dfs);
        compare("###0.0#E0: 0.1", df.format(0.1), "1000E-4");
        compare("###0.0#E0: 0.1234567", df.format(0.1234567), "1234.57E-4");
        compare("###0.0#E0: 0.9999999", df.format(0.9999999), "1.0E0");
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.01), "100E-4"))
            failures.set(failCount);
        failCount++;
        compare("###0.0#E0: 0.01234567", df.format(0.01234567), "123.457E-4");
        compare("###0.0#E0: 0.09999999", df.format(0.09999999), "1000E-4");
        compare("###0.0#E0: 0.001", df.format(0.001), "10E-4");
        compare("###0.0#E0: 0.001234567", df.format(0.001234567), "12.3457E-4");
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.009999999), "100E-4"))
            failures.set(failCount);
        failCount++;
        compare("###0.0#E0: 0.0001", df.format(0.0001), "1.0E-4");
        compare("###0.0#E0: 0.0001234567", df.format(0.0001234567),
                "1.23457E-4");
        compare("###0.0#E0: 0.0009999999", df.format(0.0009999999), "10E-4");
        // Fails in JDK 1.2.2
        if (!compare(failCount, df.format(0.00001), "1000E-8"))
            failures.set(failCount);
        failCount++;
        compare("###0.0#E0: 0.00001234567", df.format(0.00001234567),
                "1234.57E-8");
        compare("###0.0#E0: 0.00009999999", df.format(0.00009999999), "1.0E-4");

        assertTrue("Failed " + failures + " of " + failCount,
                failures.length() == 0);

        String formatString = "##0.#";
        df = new DecimalFormat(formatString, dfs);
        df.setMinimumFractionDigits(30);
        compare(formatString + ": 0.000000000000000000000000000000", df
                .format(0.0), "0.000000000000000000000000000000");
        compare(formatString + ": -0.000000000000000000000000000000", df
                .format(-0.0), "-0.000000000000000000000000000000");
        compare(formatString + ": 1.000000000000000000000000000000", df
                .format(1.0), "1.000000000000000000000000000000");
        compare(formatString + ": -1.000000000000000000000000000000", df
                .format(-1.0), "-1.000000000000000000000000000000");

        df = new DecimalFormat(formatString);
        df.setMaximumFractionDigits(30);
        compare(formatString + ": 0", df.format(0.0), "0");
        compare(formatString + ": -0", df.format(-0.0), "-0");
        compare(formatString + ": 1", df.format(1.0), "1");
        compare(formatString + ": -1", df.format(-1.0), "-1");
    }

    public void test_formatD() {
        DecimalFormat format = (DecimalFormat) NumberFormat
                .getInstance(Locale.ENGLISH);
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(400);
        assertEquals("123456789012345", format.format(123456789012345.));
        assertEquals("1", "12345678901234.5", format.format(12345678901234.5));
        assertEquals("2", "1234567890123.25", format.format(1234567890123.25));
        assertEquals("3", "999999999999.375", format.format(999999999999.375));
        assertEquals("4", "99999999999.0625", format.format(99999999999.0625));
        assertEquals("5", "9999999999.03125", format.format(9999999999.03125));
        assertEquals("6", "999999999.015625", format.format(999999999.015625));
        assertEquals("7", "99999999.0078125", format.format(99999999.0078125));
        assertEquals("8", "9999999.00390625", format.format(9999999.00390625));
        assertEquals("9", "999999.001953125", format.format(999999.001953125));
        assertEquals("10", "9999.00048828125", format.format(9999.00048828125));
        assertEquals("11", "999.000244140625", format.format(999.000244140625));
        assertEquals("12", "99.0001220703125", format.format(99.0001220703125));
        assertEquals("13", "9.00006103515625", format.format(9.00006103515625));
        assertEquals("14", "0.000030517578125", format.format(0.000030517578125));
    }


    public void test_getNegativePrefix() {
        DecimalFormat df = new DecimalFormat();
        try {
            df.setNegativePrefix("--");
            assertTrue("Incorrect negative prefix", df.getNegativePrefix()
                    .equals("--"));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_getNegativeSuffix() {
        DecimalFormat df = new DecimalFormat();
        try {
            df.setNegativeSuffix("&");
            assertTrue("Incorrect negative suffix", df.getNegativeSuffix()
                    .equals("&"));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_getPositivePrefix() {
        DecimalFormat df = new DecimalFormat();
        try {
            df.setPositivePrefix("++");
            assertTrue("Incorrect positive prefix", df.getPositivePrefix()
                    .equals("++"));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_getPositiveSuffix() {
        DecimalFormat df = new DecimalFormat();
        try {
            df.setPositiveSuffix("%");
            assertTrue("Incorrect positive prefix", df.getPositiveSuffix()
                    .equals("%"));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_hashCode() {
        try {
            DecimalFormat df1 = new DecimalFormat();
            DecimalFormat df2 = (DecimalFormat) df1.clone();
            assertTrue("Hash codes of equals object are not equal", df2
                    .hashCode() == df1.hashCode());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    public void test_parseLjava_lang_StringLjava_text_ParsePosition() {
        DecimalFormat format = (DecimalFormat) NumberFormat
                .getNumberInstance(Locale.ENGLISH);
        ParsePosition pos = new ParsePosition(0);
        Number result = format.parse("9223372036854775807", pos);
        assertTrue("Wrong result type for Long.MAX_VALUE",
                result.getClass() == Long.class);
        assertEquals("Wrong result Long.MAX_VALUE",
                Long.MAX_VALUE, result.longValue());
        pos = new ParsePosition(0);
        result = format.parse("-9223372036854775808", pos);
        assertTrue("Wrong result type for Long.MIN_VALUE",
                result.getClass() == Long.class);
        assertTrue("Wrong result Long.MIN_VALUE: " + result.longValue(), result
                .longValue() == Long.MIN_VALUE);
        pos = new ParsePosition(0);
        result = format.parse("9223372036854775808", pos);
        assertTrue("Wrong result type for Long.MAX_VALUE+1",
                result.getClass() == Double.class);
        assertEquals("Wrong result Long.MAX_VALUE + 1",
                (double) Long.MAX_VALUE + 1, result.doubleValue());
        pos = new ParsePosition(0);
        result = format.parse("-9223372036854775809", pos);
        assertTrue("Wrong result type for Long.MIN_VALUE - 1",
                result.getClass() == Double.class);
        assertEquals("Wrong result Long.MIN_VALUE - 1",
                (double) Long.MIN_VALUE - 1, result.doubleValue());

        pos = new ParsePosition(0);
        result = format.parse("18446744073709551629", pos);
        assertTrue("Wrong result type for overflow",
                result.getClass() == Double.class);
        assertEquals("Wrong result for overflow",
                18446744073709551629d, result.doubleValue());

        pos = new ParsePosition(0);
        result = format.parse("42325917317067571199", pos);
        assertTrue("Wrong result type for overflow a: " + result, result
                .getClass() == Double.class);
        assertTrue("Wrong result for overflow a: " + result, result
                .doubleValue() == 42325917317067571199d);
        pos = new ParsePosition(0);
        result = format.parse("4232591731706757119E1", pos);
        assertTrue("Wrong result type for overflow b: " + result, result
                .getClass() == Double.class);
        assertEquals("Wrong result for overflow b: " + result,
                42325917317067571190d, result.doubleValue());
        pos = new ParsePosition(0);
        result = format.parse(".42325917317067571199E20", pos);
        assertTrue("Wrong result type for overflow c: " + result, result
                .getClass() == Double.class);
        assertTrue("Wrong result for overflow c: " + result, result
                .doubleValue() == 42325917317067571199d);
        pos = new ParsePosition(0);
        result = format.parse("922337203685477580.9E1", pos);
        assertTrue("Wrong result type for overflow d: " + result, result
                .getClass() == Double.class);
        assertTrue("Wrong result for overflow d: " + result, result
                .doubleValue() == 9223372036854775809d);
        pos = new ParsePosition(0);
        result = format.parse("9.223372036854775809E18", pos);
        assertTrue("Wrong result type for overflow e: " + result, result
                .getClass() == Double.class);
        assertTrue("Wrong result for overflow e: " + result, result
                .doubleValue() == 9223372036854775809d);

        // test parse with multipliers
        format.setMultiplier(100);
        result = format.parse("9223372036854775807", new ParsePosition(0));
        assertEquals("Wrong result type multiplier 100: " + result, Long.class, result.getClass());
        // RI on windows and linux both answer with a slightly rounded result
        assertTrue("Wrong result for multiplier 100: " + result, result
                .longValue() == 92233720368547760L);
        format.setMultiplier(1000);
        result = format.parse("9223372036854775807", new ParsePosition(0));
        assertTrue("Wrong result type multiplier 1000: " + result, result
                .getClass() == Long.class);
        assertTrue("Wrong result for multiplier 1000: " + result, result
                .longValue() == 9223372036854776L);

        format.setMultiplier(10000);
        result = format.parse("9223372036854775807", new ParsePosition(0));
        assertTrue("Wrong result type multiplier 10000: " + result, result
                .getClass() == Double.class);
        assertTrue("Wrong result for multiplier 10000: " + result, result
                .doubleValue() == 922337203685477.5807d);

    }
}
