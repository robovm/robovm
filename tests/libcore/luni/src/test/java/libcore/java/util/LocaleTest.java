/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.util;

import java.text.BreakIterator;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.MissingResourceException;

public class LocaleTest extends junit.framework.TestCase {
    // http://b/2611311; if there's no display language/country/variant, use the raw codes.
    public void test_getDisplayName_invalid() throws Exception {
        Locale invalid = new Locale("AaBbCc", "DdEeFf", "GgHhIi");

        assertEquals("aabbcc", invalid.getLanguage());
        assertEquals("DDEEFF", invalid.getCountry());
        assertEquals("GgHhIi", invalid.getVariant());

        // Android using icu4c < 49.2 returned empty strings for display language, country,
        // and variant, but a display name made up of the raw strings.
        // Newer releases return slightly different results, but no less unreasonable.
        assertEquals("aabbcc", invalid.getDisplayLanguage());
        assertEquals("", invalid.getDisplayCountry());
        assertEquals("DDEEFF_GGHHII", invalid.getDisplayVariant());
        assertEquals("aabbcc (DDEEFF,DDEEFF_GGHHII)", invalid.getDisplayName());
    }

    // http://b/2611311; if there's no display language/country/variant, use the raw codes.
    public void test_getDisplayName_unknown() throws Exception {
        Locale unknown = new Locale("xx", "YY", "Traditional");
        assertEquals("xx", unknown.getLanguage());
        assertEquals("YY", unknown.getCountry());
        assertEquals("Traditional", unknown.getVariant());

        assertEquals("xx", unknown.getDisplayLanguage());
        assertEquals("YY", unknown.getDisplayCountry());
        assertEquals("TRADITIONAL", unknown.getDisplayVariant());
        assertEquals("xx (YY,TRADITIONAL)", unknown.getDisplayName());
    }

    public void test_getDisplayName_easy() throws Exception {
        assertEquals("English", Locale.ENGLISH.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("German", Locale.GERMAN.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("Englisch", Locale.ENGLISH.getDisplayLanguage(Locale.GERMAN));
        assertEquals("Deutsch", Locale.GERMAN.getDisplayLanguage(Locale.GERMAN));
    }

    public void test_getDisplayCountry_8870289() throws Exception {
        assertEquals("Hong Kong", new Locale("", "HK").getDisplayCountry(Locale.US));
        assertEquals("Macau", new Locale("", "MO").getDisplayCountry(Locale.US));
        assertEquals("Palestine", new Locale("", "PS").getDisplayCountry(Locale.US));

        assertEquals("Cocos [Keeling] Islands", new Locale("", "CC").getDisplayCountry(Locale.US));
        assertEquals("Congo [DRC]", new Locale("", "CD").getDisplayCountry(Locale.US));
        assertEquals("Congo [Republic]", new Locale("", "CG").getDisplayCountry(Locale.US));
        assertEquals("Falkland Islands [Islas Malvinas]", new Locale("", "FK").getDisplayCountry(Locale.US));
        assertEquals("Macedonia [FYROM]", new Locale("", "MK").getDisplayCountry(Locale.US));
        assertEquals("Myanmar [Burma]", new Locale("", "MM").getDisplayCountry(Locale.US));
        assertEquals("Taiwan", new Locale("", "TW").getDisplayCountry(Locale.US));
    }

    public void test_tl() throws Exception {
        // In jb-mr1, we had a last-minute hack to always return "Filipino" because
        // icu4c 4.8 didn't have any localizations for fil. (http://b/7291355)
        Locale tl = new Locale("tl");
        Locale tl_PH = new Locale("tl", "PH");
        assertEquals("Filipino", tl.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("Filipino", tl_PH.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("Filipino", tl.getDisplayLanguage(tl));
        assertEquals("Filipino", tl_PH.getDisplayLanguage(tl_PH));

        // After the icu4c 4.9 upgrade, we could localize "fil" correctly, though we
        // needed another hack to supply "fil" instead of "tl" to icu4c. (http://b/8023288)
        Locale es_MX = new Locale("es", "MX");
        assertEquals("filipino", tl.getDisplayLanguage(es_MX));
        assertEquals("filipino", tl_PH.getDisplayLanguage(es_MX));
      }

    // http://b/3452611; Locale.getDisplayLanguage fails for the obsolete language codes.
    public void test_getDisplayName_obsolete() throws Exception {
        // he (new) -> iw (obsolete)
        assertObsolete("he", "iw", "עברית");
        // id (new) -> in (obsolete)
        assertObsolete("id", "in", "Bahasa Indonesia");
    }

    private static void assertObsolete(String newCode, String oldCode, String displayName) {
        // Either code should get you the same locale.
        Locale newLocale = new Locale(newCode);
        Locale oldLocale = new Locale(oldCode);
        assertEquals(newLocale, oldLocale);

        // No matter what code you used to create the locale, you should get the old code back.
        assertEquals(oldCode, newLocale.getLanguage());
        assertEquals(oldCode, oldLocale.getLanguage());

        // Check we get the right display name.
        assertEquals(displayName, newLocale.getDisplayLanguage(newLocale));
        assertEquals(displayName, oldLocale.getDisplayLanguage(newLocale));
        assertEquals(displayName, newLocale.getDisplayLanguage(oldLocale));
        assertEquals(displayName, oldLocale.getDisplayLanguage(oldLocale));

        // Check that none of the 'getAvailableLocales' methods are accidentally returning two
        // equal locales (because to ICU they're different, but we mangle one into the other).
        assertOnce(newLocale, BreakIterator.getAvailableLocales());
        assertOnce(newLocale, Calendar.getAvailableLocales());
        assertOnce(newLocale, Collator.getAvailableLocales());
        assertOnce(newLocale, DateFormat.getAvailableLocales());
        assertOnce(newLocale, DateFormatSymbols.getAvailableLocales());
        assertOnce(newLocale, NumberFormat.getAvailableLocales());
        assertOnce(newLocale, Locale.getAvailableLocales());
    }

    private static void assertOnce(Locale element, Locale[] array) {
        int count = 0;
        for (Locale l : array) {
            if (l.equals(element)) {
                ++count;
            }
        }
        assertEquals(1, count);
    }

    public void test_getISO3Country() {
        // Empty country code.
        assertEquals("", new Locale("en", "").getISO3Country());

        // Invalid country code.
        try {
            assertEquals("", new Locale("en", "XX").getISO3Country());
            fail();
        } catch (MissingResourceException expected) {
            assertEquals("FormatData_en_XX", expected.getClassName());
            assertEquals("ShortCountry", expected.getKey());
        }

        // Valid country code.
        assertEquals("CAN", new Locale("", "CA").getISO3Country());
        assertEquals("CAN", new Locale("en", "CA").getISO3Country());
        assertEquals("CAN", new Locale("xx", "CA").getISO3Country());
    }

    public void test_getISO3Language() {
        // Empty language code.
        assertEquals("", new Locale("", "US").getISO3Language());

        // Invalid language code.
        try {
            assertEquals("", new Locale("xx", "US").getISO3Language());
            fail();
        } catch (MissingResourceException expected) {
            assertEquals("FormatData_xx_US", expected.getClassName());
            assertEquals("ShortLanguage", expected.getKey());
        }

        // Valid language code.
        assertEquals("eng", new Locale("en", "").getISO3Language());
        assertEquals("eng", new Locale("en", "CA").getISO3Language());
        assertEquals("eng", new Locale("en", "XX").getISO3Language());
    }
  }
