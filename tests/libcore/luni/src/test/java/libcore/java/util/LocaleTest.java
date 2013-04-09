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

public class LocaleTest extends junit.framework.TestCase {
    // http://b/2611311; if there's no display language/country/variant, use the raw codes.
    public void test_getDisplayName_raw() throws Exception {
        Locale weird = new Locale("AaBbCc", "DdEeFf", "GgHhIi");
        assertEquals("aabbcc", weird.getLanguage());
        assertEquals("", weird.getDisplayLanguage());
        assertEquals("DDEEFF", weird.getCountry());
        assertEquals("", weird.getDisplayCountry());
        assertEquals("GgHhIi", weird.getVariant());
        assertEquals("", weird.getDisplayVariant());
        assertEquals("aabbcc (DDEEFF,GgHhIi)", weird.getDisplayName());
    }

    public void test_getDisplayName_easy() throws Exception {
        assertEquals("English", Locale.ENGLISH.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("German", Locale.GERMAN.getDisplayLanguage(Locale.ENGLISH));
        assertEquals("Englisch", Locale.ENGLISH.getDisplayLanguage(Locale.GERMAN));
        assertEquals("Deutsch", Locale.GERMAN.getDisplayLanguage(Locale.GERMAN));
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
}
