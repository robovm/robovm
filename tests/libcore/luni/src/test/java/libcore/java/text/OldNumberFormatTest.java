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

import dalvik.annotation.BrokenTest;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;
import junit.framework.TestCase;
import tests.support.Support_Locale;

public class OldNumberFormatTest extends TestCase {

    public void test_getIntegerInstanceLjava_util_Locale() throws ParseException {
        DecimalFormat format = (DecimalFormat) NumberFormat.getIntegerInstance(Locale.US);
        assertEquals("#,##0", format.toPattern());
        assertEquals("-36", format.format(-35.76));
        assertEquals(new Long(-36), format.parse("-36"));
        assertEquals(new Long(-36), format.parseObject("-36"));
        assertEquals(0, format.getMaximumFractionDigits());
        assertTrue(format.isParseIntegerOnly());

        // try with a locale that has a different integer pattern
        Locale chLocale = new Locale("de", "CH");
        if (Support_Locale.isLocaleAvailable(chLocale)) {
            format = (DecimalFormat) NumberFormat.getIntegerInstance(chLocale);
            assertEquals("#,##0", format.toPattern());
            assertEquals("-36", format.format(-35.76));
            assertEquals(new Long(-36), format.parse("-36"));
            assertEquals(new Long(-36), format.parseObject("-36"));
            assertEquals(0, format.getMaximumFractionDigits());
            assertTrue(format.isParseIntegerOnly());
        }
        Locale arLocale = new Locale("ar", "AE");
        if (Support_Locale.isLocaleAvailable(arLocale)) {
            format = (DecimalFormat) NumberFormat.getIntegerInstance(arLocale);
            assertEquals("#,##0;#,##0-", format.toPattern());
            assertEquals("\u0666-", format.format(-6));
            assertEquals(new Long(-36), format.parse("36-"));
            assertEquals(new Long(-36), format.parseObject("36-"));
            assertEquals(0, format.getMaximumFractionDigits());
            assertTrue(format.isParseIntegerOnly());
        }
    }

    public void test_setMaximumIntegerDigits() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumIntegerDigits(2);
        assertEquals("Wrong result: case 1", "23", format.format(123));

        format.setMaximumIntegerDigits(Integer.MIN_VALUE);
        assertEquals("Wrong result: case 2", "0", format.format(123));
    }

    public void test_setCurrencyLjava_util_Currency() {
        // Test for method void setCurrency(java.util.Currency)
        // a subclass that supports currency formatting
        Currency currA = Currency.getInstance("ARS");
        NumberFormat format = NumberFormat.getInstance(new Locale("hu", "HU"));
        format.setCurrency(currA);
        assertSame("Returned incorrect currency", currA, format.getCurrency());

        // a subclass that doesn't support currency formatting
        ChoiceFormat cformat = new ChoiceFormat(
                "0#Less than one|1#one|1<Between one and two|2<Greater than two");
        try {
            ((NumberFormat) cformat).setCurrency(currA);
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }

        try {
            NumberFormat.getInstance().setCurrency(null);
            fail("NullPointerException was thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            NumberFormat.getIntegerInstance().setCurrency(null);
            fail("NullPointerException was thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_parseObjectLjava_lang_StringLjava_text_ParsePosition() {
        // regression test for HARMONY-1003
        assertNull(NumberFormat.getInstance().parseObject("0",
                new ParsePosition(-1)));

        parseObjectTest(NumberFormat.getInstance(), "123.123",
                new ParsePosition(1), new Double(23.123), 7, true);

        parseObjectTest(NumberFormat.getInstance(), "123.123abc123",
                new ParsePosition(3), new Double(0.123), 7, true);

        if (Support_Locale.isLocaleAvailable(Locale.FRANCE)) {
            parseObjectTest(NumberFormat.getInstance(Locale.FRANCE),
                "asd123,123abc123",
                new ParsePosition(3), new Double(123.123), 10, true);

            parseObjectTest(NumberFormat.getInstance(Locale.FRANCE),
                "test test",
                new ParsePosition(0), null, 0, false);
        }

        parseObjectTest(NumberFormat.getIntegerInstance(),
                "asd123.123abc123",
                new ParsePosition(3), new Long(123), 6, true);

        parseObjectTest(NumberFormat.getNumberInstance(),
                "$-123,123.123#",
                new ParsePosition(1), new Double(-123123.123), 13, true);
        parseObjectTest(NumberFormat.getNumberInstance(),
                "$-123,123.123#",
                new ParsePosition(0), null, 0, false);
        parseObjectTest(NumberFormat.getNumberInstance(),
                "$-123,123.123#",
                new ParsePosition(13), null, 13, false);
        parseObjectTest(NumberFormat.getPercentInstance(),
                "%20.123#",
                new ParsePosition(0), new Double(20.123), 0, false);
        parseObjectTest(NumberFormat.getPercentInstance(),
                "%-200,123.123#",
                new ParsePosition(0), null, 0, false);


        // Regression for HARMONY-1685
        try {
            NumberFormat.getInstance().parseObject("test", null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }

    void parseObjectTest(NumberFormat nf, String sourseStr, ParsePosition position,
            Object resultObj, int outIndex, boolean isSuccess) {
        int indexBefore = position.getIndex();
        Object result = nf.parseObject(sourseStr, position);
        if(isSuccess) {
            assertEquals(resultObj, result);
            assertEquals(outIndex, position.getIndex());
        } else {
            assertNull(result);
            assertEquals(indexBefore, position.getIndex());
            assertEquals(outIndex, position.getErrorIndex());
        }
    }

    public void test_clone() {

        int max_digits = 100;
        NumberFormat nf1 = NumberFormat.getInstance();
        nf1.setMaximumIntegerDigits(max_digits);

        NumberFormat nf2 = (NumberFormat) nf1.clone();
        NumberFormat nf3 = (NumberFormat) nf1.clone();

        assertTrue("Clonned object is not equal to object", nf2.equals(nf1));
        assertTrue("Two clonned objects are not equal", nf2.equals(nf3));

        assertTrue("Max digits value is incorrect for clonned object", nf2
                .getMaximumIntegerDigits() == max_digits);

        nf1.setMaximumIntegerDigits(10);
        assertTrue(
                "Max digits value is incorrect for clonned object after changing this value for object",
                nf2.getMaximumIntegerDigits() == max_digits);
    }

    public void test_equals() {

        NumberFormat nf1 = NumberFormat.getInstance();
        NumberFormat nf2 = NumberFormat.getInstance();

        assertTrue("Objects are not equal", nf1.equals(nf2));
        assertTrue("THe same Objects are not equal", nf1.equals(nf1));

        nf2.setMaximumIntegerDigits(100);
        assertFalse("Different NumberFormat are equal", nf1.equals(nf2));

        nf2.setMaximumIntegerDigits(nf1.getMaximumIntegerDigits());
        assertTrue("THe same Objects are not equal", nf1.equals(nf2));

        nf1 = NumberFormat.getIntegerInstance();
        nf2 = NumberFormat.getIntegerInstance(Locale.CHINA);
        assertFalse("Different NumberFormat are equal", nf1.equals(nf2));

        assertFalse("Object is equal null", nf1.equals(null));
    }

    public void test_formatLdouble() {
        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);

        String out = nf1.format(1234567890.0123456789);
        assertEquals("Wrong result for double : " + out, "1,234,567,890.012",
                out.toString());

        out = nf1.format(-1234567890.0123456789);
        assertEquals("Wrong result for double : " + out, "-1,234,567,890.012",
                out.toString());

        Locale deLocale = new Locale("de", "CH");
        if (Support_Locale.isLocaleAvailable(deLocale)) {
            NumberFormat nf2 = NumberFormat.getInstance(deLocale);
            out = nf2.format(-1234567890.0123456789);
            // use de_CH instead
            // assertEquals("Wrong result for double : " + out, "1,234,567,890.012-",
            //         out.toString());
            assertEquals("Wrong result for double : " + out, "-1'234'567'890.012", out.toString());
        }

        out = nf1.format(1.0001);
        assertEquals("Wrong result for for double: " + out, "1", out.toString());

        out = nf1.format(5.0);
        assertEquals("Wrong result for for double: " + out, "5", out.toString());
        // END android-changed
    }

    public void test_formatLlong() {
        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);

        String out = nf1.format(Long.MAX_VALUE);
        assertEquals("Wrong result for double : " + out,
                "9,223,372,036,854,775,807", out.toString());

        out = nf1.format(Long.MIN_VALUE);
        assertEquals("Wrong result for double : " + out,
                "-9,223,372,036,854,775,808", out.toString());

        Locale deLocale = new Locale("de", "CH");
        if (Support_Locale.isLocaleAvailable(deLocale)) {
            NumberFormat nf2 = NumberFormat.getInstance(deLocale);
            out = nf2.format(-1234567890);
            // use de_CH instead
            // assertEquals("Wrong result for double : " + out, "-1 234 567 890", out
            //         .toString());
            assertEquals("Wrong result for double : " + out, "-1'234'567'890", out.toString());
        }

        // the Locale data of icu uses \uc2a0
        out = nf1.format(1);
        assertEquals("Wrong result for for double: " + out, "1", out.toString());

        out = nf1.format(0);
        assertEquals("Wrong result for for double: " + out, "0", out.toString());
        // END android-changed
    }

    public void test_getAvailableLocales() {

        Locale[] l = NumberFormat.getAvailableLocales();
        assertFalse("returned Locale array is null", l == null);
        assertTrue("returned Locale length <= 0", l.length > 0);
        Locale[] resl = Locale.getAvailableLocales();
        assertTrue("returned Locale arrays are different",
                l.length == resl.length);
        boolean isUS = false;
        for (int i = 0; i < resl.length; i++) {
            assertEquals("elements " + i + " are not equal: ", resl[i], l[i]);
            if (l[i].equals(Locale.US))
                isUS = true;
        }
        assertTrue("there is no Locale.US", isUS);
    }

    public void test_getCurrencyInstance() {

        Locale.setDefault(Locale.US);
        NumberFormat format = NumberFormat.getCurrencyInstance();

        assertNotSame("Instance is null", null, format);
        assertTrue("Object is not instance of NumberFormat",
                format instanceof NumberFormat);

        assertEquals(
                "Test1: NumberFormat.getCurrencyInstance().format(35.76) returned wrong value",
                "$35.76", format.format(35.76));
        assertEquals(
                "Test2: NumberFormat.getCurrencyInstance().format(123456.789) returned wrong value",
                "$123,456.79", format.format(123456.789));
        assertEquals(
                "Test3: NumberFormat.getCurrencyInstance().format(0.1) returned wrong value",
                "$0.10", format.format(0.1));
        assertEquals(
                "Test4: NumberFormat.getCurrencyInstance().format(0.999) returned wrong value",
                "$1.00", format.format(0.999));
    }

    public void test_getCurrencyInstanceLjava_util_Locale() {
        Locale usLocale = Locale.US;
        NumberFormat format = NumberFormat.getCurrencyInstance(usLocale);

        assertNotSame("Instance is null", null, format);
        assertTrue(format instanceof NumberFormat);

        assertEquals("$35.76", format.format(35.76));
        assertEquals("$123,456.79", format.format(123456.789));
        assertEquals("$0.10", format.format(0.1));
        assertEquals("$1.00", format.format(0.999));

        Locale atLocale = new Locale("de", "AT");
        if (Support_Locale.isLocaleAvailable(atLocale)) {
            format = NumberFormat.getCurrencyInstance(atLocale);
            // BEGIN android-changed: ICU uses non-breaking space after the euro sign; the RI uses ' '.
            assertEquals("\u20ac\u00a035,76", format.format(35.76));
            assertEquals("\u20ac\u00a0123.456,79", format.format(123456.789));
            assertEquals("\u20ac\u00a00,10", format.format(0.1));
            assertEquals("\u20ac\u00a01,00", format.format(0.999));
            try {
                NumberFormat.getCurrencyInstance(null);
                fail("java.lang.NullPointerException is not thrown");
            } catch (java.lang.NullPointerException expected) {
            }
        }
    }

    public void test_getInstance() {
        Locale.setDefault(Locale.US);
        NumberFormat format = NumberFormat.getInstance();

        assertNotSame("Instance is null", null, format);
        assertTrue("Object is not instance of NumberFormat",
                format instanceof NumberFormat);

        assertEquals(
                "Test1: NumberFormat.getInstance().format(1234567890.0987654321) returned wrong value",
                "1,234,567,890.099", format.format(1234567890.0987654321));
        assertEquals(
                "Test2: ((DecimalFormat) NumberFormat.getInstance()).toPattern returned wrong value",
                "#,##0.###", ((DecimalFormat) format).toPattern());
        assertEquals(
                "Test3: NumberFormat.getInstance().format(123456789) returned wrong value",
                "123,456,789", format.format(123456789));
    }

    public void test_getInstanceLjava_util_Locale() {
        Locale testLocale = new Locale("de", "CH");
        Locale.setDefault(Locale.US);
        if (Support_Locale.isLocaleAvailable(testLocale)) {
            NumberFormat format = NumberFormat.getInstance(testLocale);

            assertNotSame(null, format);
            assertTrue(format instanceof NumberFormat);

            assertEquals("1'234'567'890.099", format.format(1234567890.0987654321));
            assertEquals("#,##0.###", ((DecimalFormat) format).toPattern());
            assertEquals("123'456'789", format.format(123456789));
        }
        try {
            NumberFormat.getInstance(null);
            fail("java.lang.NullPointerException is not thrown");
        } catch (java.lang.NullPointerException expected) {
        }
    }

    public void test_getNumberInstance() {
        Locale.setDefault(Locale.US);
        NumberFormat format = NumberFormat.getNumberInstance();

        assertNotSame("Instance is null", null, format);
        assertTrue("Object is not instance of NumberFormat",
                format instanceof NumberFormat);

        assertEquals(
                "Test1: NumberFormat.getNumberInstance().format(1234567890.0987654321) returned wrong value",
                "1,234,567,890.099", format.format(1234567890.0987654321));
        assertEquals(
                "Test2: ((DecimalFormat) NumberFormat.getNumberInstance()).toPattern returned wrong value",
                "#,##0.###", ((DecimalFormat) format).toPattern());
        assertEquals(
                "Test3: NumberFormat.getNumberInstance().format(123456789) returned wrong value",
                "123,456,789", format.format(123456789));
    }

    public void test_getNumberInstanceLjava_util_Locale() {
        Locale.setDefault(Locale.US);
        Locale deLocale = new Locale("de", "CH");
        if (Support_Locale.isLocaleAvailable(deLocale)) {
            NumberFormat format = NumberFormat.getNumberInstance(deLocale);
            assertNotSame("Instance is null", null, format);
            assertTrue("Object is not instance of NumberFormat", format instanceof NumberFormat);

            assertEquals("-1'234'567'890.099", format.format(-1234567890.0987654321));
            assertEquals("#,##0.###", ((DecimalFormat) format).toPattern());
            assertEquals("123'456'789", format.format(123456789));
        }
        try {
            NumberFormat.getInstance(null);
            fail("java.lang.NullPointerException is not thrown");
        } catch (java.lang.NullPointerException expected) {
        }
    }

    public void test_getPercentInstance() {
        Locale.setDefault(Locale.US);
        NumberFormat format = NumberFormat.getPercentInstance();

        assertNotSame("Instance is null", null, format);
        assertTrue("Object is not instance of NumberFormat",
                format instanceof NumberFormat);

        assertEquals(
                "Test1: NumberFormat.getPercentInstance().format(1234567890.0987654321) returned wrong value",
                "123,456,789,010%", format.format(1234567890.0987654321));
        assertEquals(
                "Test2: ((DecimalFormat) NumberFormat.getPercentInstance()).toPattern returned wrong value",
                "#,##0%", ((DecimalFormat) format).toPattern());
        assertEquals(
                "Test3: NumberFormat.getPercentInstance().format(123456789) returned wrong value",
                "12,345,678,900%", format.format(123456789));
    }

    public void test_getPercentInstanceLjava_util_Locale() {
        Locale csLocale = new Locale("cs", "CZ");
        Locale.setDefault(Locale.US);
        if (Support_Locale.isLocaleAvailable(csLocale)) {
            NumberFormat format = NumberFormat.getPercentInstance(csLocale);

            assertNotSame("Instance is null", null, format);
            assertTrue("Object is not instance of NumberFormat", format instanceof NumberFormat);

            assertEquals("123\u00a0456\u00a0789\u00a0010\u00a0%", format.format(1234567890.0987654321));
            assertEquals("#,##0\u00a0%", ((DecimalFormat) format).toPattern());
            assertEquals("12\u00a0345\u00a0678\u00a0900\u00a0%", format.format(123456789));
        }
        try {
            NumberFormat.getInstance(null);
            fail("java.lang.NullPointerException is not thrown");
        } catch (java.lang.NullPointerException expected) {
        }
    }

    public void test_getMaximumFractionDigits() {
        NumberFormat nf1 = NumberFormat.getInstance();

        nf1.setMaximumFractionDigits(Integer.MAX_VALUE);
        int result = nf1.getMaximumFractionDigits();
        assertTrue("getMaximumFractionDigits returns " + result
                + " instead of: " + Integer.MAX_VALUE,
                result == Integer.MAX_VALUE);

        nf1.setMaximumFractionDigits(0);
        result = nf1.getMaximumFractionDigits();
        assertTrue("getMaximumFractionDigits returns " + result
                + " instead of 0", result == 0);

        nf1.setMinimumFractionDigits(Integer.MAX_VALUE);
        result = nf1.getMaximumFractionDigits();
        assertTrue("getMaximumFractionDigits returns " + result
                + " instead of Integer.MAX_VALUE", result == Integer.MAX_VALUE);
    }

    public void test_getMinimumFractionDigits() {
        NumberFormat nf1 = NumberFormat.getInstance();
        nf1.setMinimumFractionDigits(Integer.MAX_VALUE);
        int result = nf1.getMinimumFractionDigits();
        assertTrue("getMinimumFractionDigits returns " + result
                + " instead of: " + Integer.MAX_VALUE,
                result == Integer.MAX_VALUE);

        nf1.setMaximumFractionDigits(0);
        result = nf1.getMinimumFractionDigits();
        assertTrue("getMinimumFractionDigits returns " + result
                + " instead of 0", result == 0);

        nf1.setMinimumFractionDigits(52);
        result = nf1.getMinimumFractionDigits();
        assertTrue("getMinimumFractionDigits returns " + result
                + " instead of 52", result == 52);
    }

    public void test_getMaximumIntegerDigits() {
        NumberFormat nf1 = NumberFormat.getInstance();
        nf1.setMaximumIntegerDigits(Integer.MAX_VALUE);
        int result = nf1.getMaximumIntegerDigits();
        assertTrue("getMaximumIntegerDigits returns " + result
                + " instead of: " + Integer.MAX_VALUE,
                result == Integer.MAX_VALUE);

        nf1.setMaximumIntegerDigits(0);
        result = nf1.getMaximumIntegerDigits();
        assertTrue("getMaximumIntegerDigits returns " + result
                + " instead of 0", result == 0);

        nf1.setMinimumIntegerDigits(Integer.MAX_VALUE);
        result = nf1.getMaximumIntegerDigits();
        assertTrue("getMaximumIntegerigits returns " + result
                + " instead of Integer.MAX_VALUE", result == Integer.MAX_VALUE);
    }

    public void test_getMinimumIntegernDigits() {
        NumberFormat nf1 = NumberFormat.getInstance();
        nf1.setMinimumIntegerDigits(Integer.MAX_VALUE);
        int result = nf1.getMinimumIntegerDigits();
        assertTrue("getMinimumIntegerDigits returns " + result
                + " instead of: " + Integer.MAX_VALUE,
                result == Integer.MAX_VALUE);

        nf1.setMaximumIntegerDigits(0);
        result = nf1.getMinimumIntegerDigits();
        assertTrue("getMinimumIntegerDigits returns " + result
                + " instead of 0", result == 0);

        nf1.setMinimumIntegerDigits(0x12034);
        result = nf1.getMinimumIntegerDigits();
        assertTrue("getMinimumIntegerDigits returns " + result
                + " instead of 5148", result == 73780);
    }

    public void test_hashCode() {

        NumberFormat nf1 = NumberFormat.getInstance();
        NumberFormat nf11 = NumberFormat.getInstance();
        NumberFormat nf2 = NumberFormat.getInstance(Locale.US);
        NumberFormat nf3 = NumberFormat.getPercentInstance();
        NumberFormat nf4 = NumberFormat.getCurrencyInstance();
        NumberFormat nf5 = NumberFormat
                .getNumberInstance(new Locale("mk", "MK"));
        NumberFormat nf6 = NumberFormat.getInstance(Locale.US);

        assertTrue("Hash codes are not equal: case 1", nf1.hashCode() == nf2
                .hashCode());
        assertTrue("Hash codes are not equal: case 2", nf1.hashCode() == nf11
                .hashCode());
        assertTrue("Hash codes are not equal: case 3", nf1.hashCode() == nf3
                .hashCode());
        assertFalse("Hash codes are equal: case 4", nf3.hashCode() == nf4
                .hashCode());
        assertFalse("Hash codes are equal: case 5", nf4.hashCode() == nf5
                .hashCode());
        assertTrue("Hash codes are not equal: case 6", nf5.hashCode() == nf6
                .hashCode());

        nf1.setMaximumFractionDigits(0);
        assertTrue("Hash codes are not equal: case 7", nf1.hashCode() == nf11
                .hashCode());
    }

    public void test_isGroupingUsed() {
        NumberFormat nf1 = NumberFormat.getInstance();
        assertTrue("grouping is not used for NumberFormat.getInstance", nf1
                .isGroupingUsed());

        nf1.setGroupingUsed(false);
        assertFalse(
                "grouping is used for NumberFormat.getInstance after setting false",
                nf1.isGroupingUsed());

        nf1.setGroupingUsed(true);
        assertTrue(
                "grouping is not used for NumberFormat.getInstance after setting true",
                nf1.isGroupingUsed());
    }

    public void test_setGroupingUsed() {
        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
        nf1.setGroupingUsed(false);

        assertEquals("grouping is used for 1234567890.1", "1234567890.1",
                nf1.format(1234567890.1));

        assertEquals("grouping is used for -1234567890.1", "-1234567890.1",
                nf1.format(-1234567890.1));

        nf1.setGroupingUsed(false);

        assertEquals("grouping is used for 1234567890.1", "1234567890.1",
                nf1.format(1234567890.1));

        assertEquals("grouping is used for -1234567890.1", "-1234567890.1",
                nf1.format(-1234567890.1));

        nf1.setGroupingUsed(true);

        assertEquals("grouping is not used for 1234567890.1",
                "1,234,567,890.1", nf1.format(1234567890.1));

        assertEquals("grouping is not used for -1234567890.1",
                "-1,234,567,890.1", nf1.format(-1234567890.1));

        Locale csLocale = new Locale("cs", "CZ");
        if (Support_Locale.isLocaleAvailable(csLocale)) {
            NumberFormat nf2 = NumberFormat.getPercentInstance(csLocale);
            nf2.setGroupingUsed(false);

            assertEquals("123456789010\u00a0%", nf2.format(1234567890.1));

            assertEquals("-123456789010\u00a0%", nf2.format(-1234567890.1));
            assertEquals("1,234,567,890.1", nf1.format(1234567890.1));

            nf2.setGroupingUsed(true);
            assertEquals("123\u00a0456\u00a0789\u00a0010\u00a0%", nf2.format(1234567890.1));

            assertEquals("-123\u00a0456\u00a0789\u00a0010\u00a0%", nf2.format(-1234567890.1));

            nf2.setGroupingUsed(true);
            assertEquals("123\u00a0456\u00a0789\u00a0010\u00a0%", nf2.format(1234567890.1));

            assertEquals("-123\u00a0456\u00a0789\u00a0010\u00a0%", nf2.format(-1234567890.1));
        }
    }

    public void test_isParseIntegerOnly() {
        NumberFormat nf1 = NumberFormat.getInstance();
        assertTrue("ParseIntegerOnly is not used for NumberFormat.getInstance",
                nf1.isGroupingUsed());

        nf1.setParseIntegerOnly(false);
        assertFalse(
                "ParseIntegerOnly is used for NumberFormat.getInstance after setting false",
                nf1.isParseIntegerOnly());

        nf1.setParseIntegerOnly(true);
        assertTrue(
                "ParseIntegerOnly is not used for NumberFormat.getInstance after setting true",
                nf1.isParseIntegerOnly());
    }

    public void test_setParseIntegerOnly() {
        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
        nf1.setParseIntegerOnly(true);

        assertEquals("ParseIntegerOnly is not used for 1234567890.1",
                "1,234,567,890.1", nf1.format(1234567890.1));
        assertEquals("ParseIntegerOnly is not used for -1234567890.1",
                "-1,234,567,890.1", nf1.format(-1234567890.1));
        assertEquals("ParseIntegerOnly is not used for -1234567890.",
                "-1,234,567,890", nf1.format(-1234567890.));

        nf1.setParseIntegerOnly(false);

        assertEquals("ParseIntegerOnly is not used for 1234567890.1",
                "1,234,567,890.1", nf1.format(1234567890.1));
        assertEquals("ParseIntegerOnly is not used for -1234567890.1",
                "-1,234,567,890.1", nf1.format(-1234567890.1));
        assertEquals("ParseIntegerOnly is not used for -1234567890.",
                "-1,234,567,890", nf1.format(-1234567890.));
    }

    public void test_setMaximumFractionDigits() {
        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
        nf1.setMaximumFractionDigits(Integer.MAX_VALUE);
        int result = nf1.getMaximumFractionDigits();
        assertTrue("setMaximumFractionDigits set " + result
                + " instead of Integer.MAX_VALUE", result == Integer.MAX_VALUE);
        nf1.setMaximumFractionDigits(0);
        result = nf1.getMaximumFractionDigits();
        assertTrue("setMaximumFractionDigits set " + result + " instead of 0",
                result == 0);
        assertEquals("format of 1234567890.0987654321 returns incorrect value",
                "1,234,567,890", nf1.format(1234567890.0987654321));
        nf1.setMaximumFractionDigits(5);
        result = nf1.getMaximumFractionDigits();
        assertTrue("setMaximumFractionDigits set " + result + " instead of 5",
                result == 5);
        assertEquals(
                "format of 1234567890.0987654321 returns incorrect value with MaximumFractionDigits = 5",
                "1,234,567,890.09877", nf1.format(1234567890.0987654321));
        assertEquals(
                "format of -1234567890 returns incorrect value with MaximumFractionDigits = 5",
                "-1,234,567,890", nf1.format(-1234567890));
        nf1.setMaximumFractionDigits(Integer.MIN_VALUE);
        result = nf1.getMaximumFractionDigits();
        assertTrue("setMaximumFractionDigits set " + result
                + " instead of Integer.MIN_VALUE", result == 0);
        assertEquals(
                "format of 1234567890.0987654321 returns incorrect value with MaximumFractionDigits = 5",
                "1,234,567,890", nf1.format(1234567890.0987654321));
    }

    public void test_setMinimumFractionDigits() {

        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
        nf1.setMinimumFractionDigits(Integer.MAX_VALUE);
        int result = nf1.getMinimumFractionDigits();
        assertTrue("setMinimumFractionDigits set " + result
                + " instead of Integer.MAX_VALUE", result == Integer.MAX_VALUE);
        nf1.setMinimumFractionDigits(0);
        result = nf1.getMinimumFractionDigits();
        assertTrue("setMinimumFractionDigits set " + result + " instead of 0",
                result == 0);
        nf1.setMinimumFractionDigits(5);
        result = nf1.getMinimumFractionDigits();
        assertTrue("setMinimumFractionDigits set " + result + " instead of 5",
                result == 5);
        assertEquals(
                "format of 1234567890.0987654321 returns incorrect value with MinimumFractionDigits = 5",
                "1,234,567,890.09000", nf1.format(1234567890.09));
        assertEquals(
                "format of -1234567890 returns incorrect value with MinimumFractionDigits = 5",
                "-1,234,567,890.00000", nf1.format(-1234567890));
        nf1.setMinimumFractionDigits(Integer.MIN_VALUE);
        result = nf1.getMinimumFractionDigits();
        assertTrue("setMinimumFractionDigits set " + result
                + " instead of Integer.MIN_VALUE", result == 0);
        assertEquals(
                "format of 1234567890.098 returns incorrect value with MinimumFractionDigits = 5",
                "1,234,567,890.098", nf1.format(1234567890.098));
    }

    public void test_setMinimumIntegerDigits() {

        NumberFormat nf1 = NumberFormat.getInstance(Locale.US);
        nf1.setMinimumIntegerDigits(Integer.MAX_VALUE);
        int result = nf1.getMinimumIntegerDigits();
        assertTrue("setMinimumIntegerDigits set " + result
                + " instead of Integer.MAX_VALUE", result == Integer.MAX_VALUE);
        nf1.setMinimumIntegerDigits(0);
        result = nf1.getMinimumIntegerDigits();
        assertTrue("setMinimumIntegerDigits set " + result + " instead of 0",
                result == 0);
        nf1.setMinimumIntegerDigits(5);
        result = nf1.getMinimumIntegerDigits();
        assertTrue("setMinimumIntegerDigits set " + result + " instead of 5",
                result == 5);
        assertEquals(
                "format of 123.09 returns incorrect value with MinimumIntegerDigits = 5",
                "00,123.09", nf1.format(123.09));
        assertEquals(
                "format of -123 returns incorrect value with MinimumIntegerDigits = 5",
                "-00,123", nf1.format(-123));
        nf1.setMinimumIntegerDigits(Integer.MIN_VALUE);
        result = nf1.getMinimumIntegerDigits();
        assertTrue("setMinimumIntegerDigits set " + result
                + " instead of Integer.MIN_VALUE", result == 0);
    }

    @BrokenTest("Fails in CTS, passes in CoreTestRunner")
    public void test_parseLjava_lang_String() {
        NumberFormat nf1 = NumberFormat.getInstance();
        try {
            assertEquals(
                    "Test1: NumberFormat.getInstance().parse(\"1234567890.1\") returned wrong number",
                    new Double(1234567890.1), nf1.parse("1234567890.1"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for 1234567890.1");
        }

        try {
            assertEquals(
                    "Test2: NumberFormat.getInstance().parse(\"-1234567890.1\") returned wrong number",
                    new Double(-1234567890.1), nf1.parse("-1,234,567,890.1"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for -1,234,567,890.1");
        }

        try {
            nf1.parse("@1,234,567,8901");
            fail("java.text.ParseException is not thrown for 1,234,567,890z1");
        } catch (java.text.ParseException pe) {
            // expected
        }

        nf1 = NumberFormat.getPercentInstance();
        try {
            assertEquals(
                    "Test3: NumberFormat.getPercentInstance().parse(\"-123%\") returned wrong number",
                    new Double(-1.23), nf1.parse("-123%"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for -123%");
        }

        nf1 = NumberFormat.getCurrencyInstance();
        try {
            assertEquals(
                    "Test4: NumberFormat.getCurrencyInstance().parse(\"$123\") returned wrong number",
                    new Long(123), nf1.parse("$123"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for $123");
        }

        try {
            assertEquals(
                    "Test4: NumberFormat.getCurrencyInstance().parse(\"$123abc\") returned wrong number",
                    new Long(123), nf1.parse("$123abc"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for $123");
        }

        nf1 = NumberFormat.getIntegerInstance();
        try {
            assertEquals(
                    "Test5: NumberFormat.getIntegerInstance().parse(\"-123.123\") returned wrong number",
                    nf1.parseObject("-123.123"), nf1.parse("-123.123"));
        } catch (java.text.ParseException pe) {
            fail("java.text.ParseException is thrown for $123");
        }
    }

    public void test_constructor() {
        MyNumberFormat mf = new MyNumberFormat();
        assertFalse("Greated NumberFormat object is null", mf == null);
        assertTrue(
                "Greated NumberFormat object is not instance of NumberFormat",
                mf instanceof NumberFormat);
    }

    class MyNumberFormat extends NumberFormat {
        static final long serialVersionUID = 1L;

        public MyNumberFormat() {
            super();
        }

        public StringBuffer format(double number, StringBuffer toAppendTo,
                FieldPosition pos) {

            return new StringBuffer();
        }

        public Number parse(String source, ParsePosition parsePosition) {

            return new Double(0);
        }

        public StringBuffer format(long number, StringBuffer toAppendTo,
                FieldPosition pos) {
            return new StringBuffer();
        }

    }
}
