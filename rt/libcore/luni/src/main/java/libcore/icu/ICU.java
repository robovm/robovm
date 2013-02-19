/*
 * Copyright (C) 2008 The Android Open Source Project
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

package libcore.icu;

import java.util.LinkedHashSet;
import java.util.Locale;

/**
 * Makes ICU data accessible to Java.
 */
public final class ICU {
    /**
     * Cache for ISO language names.
     */
    private static String[] isoLanguages;

    /**
     * Cache for ISO country names.
     */
    private static String[] isoCountries;

    /**
     * Returns an array of ISO language names (two-letter codes), fetched either
     * from ICU's database or from our memory cache.
     *
     * @return The array.
     */
    public static String[] getISOLanguages() {
        if (isoLanguages == null) {
            isoLanguages = getISOLanguagesNative();
        }
        return isoLanguages.clone();
    }

    /**
     * Returns an array of ISO country names (two-letter codes), fetched either
     * from ICU's database or from our memory cache.
     *
     * @return The array.
     */
    public static String[] getISOCountries() {
        if (isoCountries == null) {
            isoCountries = getISOCountriesNative();
        }
        return isoCountries.clone();
    }

    /**
     * Returns the appropriate {@code Locale} given a {@code String} of the form returned
     * by {@code toString}. This is very lenient, and doesn't care what's between the underscores:
     * this method can parse strings that {@code Locale.toString} won't produce.
     * Used to remove duplication.
     */
    public static Locale localeFromString(String localeName) {
        int first = localeName.indexOf('_');
        int second = localeName.indexOf('_', first + 1);
        if (first == -1) {
            // Language only ("ja").
            return new Locale(localeName);
        } else if (second == -1) {
            // Language and country ("ja_JP").
            return new Locale(localeName.substring(0, first), localeName.substring(first + 1));
        } else {
            // Language and country and variant ("ja_JP_TRADITIONAL").
            return new Locale(localeName.substring(0, first), localeName.substring(first + 1, second), localeName.substring(second + 1));
        }
    }

    public static Locale[] localesFromStrings(String[] localeNames) {
        // We need to remove duplicates caused by the conversion of "he" to "iw", et cetera.
        // Java needs the obsolete code, ICU needs the modern code, but we let ICU know about
        // both so that we never need to convert back when talking to it.
        LinkedHashSet<Locale> set = new LinkedHashSet<Locale>();
        for (String localeName : localeNames) {
            set.add(localeFromString(localeName));
        }
        return set.toArray(new Locale[set.size()]);
    }

    private static Locale[] availableLocalesCache;
    public static Locale[] getAvailableLocales() {
        if (availableLocalesCache == null) {
            availableLocalesCache = localesFromStrings(getAvailableLocalesNative());
        }
        return availableLocalesCache.clone();
    }

    public static Locale[] getAvailableBreakIteratorLocales() {
        return localesFromStrings(getAvailableBreakIteratorLocalesNative());
    }

    public static Locale[] getAvailableCalendarLocales() {
        return localesFromStrings(getAvailableCalendarLocalesNative());
    }

    public static Locale[] getAvailableCollatorLocales() {
        return localesFromStrings(getAvailableCollatorLocalesNative());
    }

    public static Locale[] getAvailableDateFormatLocales() {
        return localesFromStrings(getAvailableDateFormatLocalesNative());
    }

    public static Locale[] getAvailableDateFormatSymbolsLocales() {
        return getAvailableDateFormatLocales();
    }

    public static Locale[] getAvailableDecimalFormatSymbolsLocales() {
        return getAvailableNumberFormatLocales();
    }

    public static Locale[] getAvailableNumberFormatLocales() {
        return localesFromStrings(getAvailableNumberFormatLocalesNative());
    }

    /**
     * Returns the ICU version in use. This is "4.4" for gingerbread, for example.
     */
    public static native String getIcuVersion();

    /**
     * Returns the Unicode version our ICU supports. This is "5.2" for gingerbread, for example.
     */
    public static native String getUnicodeVersion();

    // --- Case mapping.

    public static native String toLowerCase(String s, String localeName);
    public static native String toUpperCase(String s, String localeName);

    // --- Native methods accessing ICU's database.

    private static native String[] getAvailableBreakIteratorLocalesNative();
    private static native String[] getAvailableCalendarLocalesNative();
    private static native String[] getAvailableCollatorLocalesNative();
    private static native String[] getAvailableDateFormatLocalesNative();
    private static native String[] getAvailableLocalesNative();
    private static native String[] getAvailableNumberFormatLocalesNative();

    public static native String[] getAvailableCurrencyCodes();
    public static native String getCurrencyCode(String countryCode);
    public static native String getCurrencyDisplayName(String locale, String currencyCode);
    public static native int getCurrencyFractionDigits(String currencyCode);
    public static native String getCurrencySymbol(String locale, String currencyCode);

    public static native String getDisplayCountryNative(String countryCode, String locale);
    public static native String getDisplayLanguageNative(String languageCode, String locale);
    public static native String getDisplayVariantNative(String variantCode, String locale);

    public static native String getISO3CountryNative(String locale);
    public static native String getISO3LanguageNative(String locale);

    public static native String addLikelySubtags(String locale);
    public static native String getScript(String locale);

    private static native String[] getISOLanguagesNative();
    private static native String[] getISOCountriesNative();

    static native boolean initLocaleDataImpl(String locale, LocaleData result);
}
