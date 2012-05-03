/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.text.DateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Passes locale-specific from ICU native code to Java.
 * <p>
 * Note that you share these; you must not alter any of the fields, nor their array elements
 * in the case of arrays. If you ever expose any of these things to user code, you must give
 * them a clone rather than the original.
 */
public final class LocaleData {
    // A cache for the locale-specific data.
    private static final HashMap<String, LocaleData> localeDataCache = new HashMap<String, LocaleData>();
    static {
        // Ensure that we pull in the locale data for the root locale, en_US, and the
        // user's default locale. (All devices must support the root locale and en_US,
        // and they're used for various system things like HTTP headers.) Pre-populating
        // the cache is especially useful on Android because we'll share this via the Zygote.
        get(Locale.ROOT);
        get(Locale.US);
        get(Locale.getDefault());
    }

    // Used by Calendar.
    public Integer firstDayOfWeek;
    public Integer minimalDaysInFirstWeek;

    // Used by DateFormatSymbols.
    public String[] amPm;
    public String[] eras;

    public String[] longMonthNames;
    public String[] shortMonthNames;
    public String[] longStandAloneMonthNames;
    public String[] shortStandAloneMonthNames;

    public String[] longWeekdayNames;
    public String[] shortWeekdayNames;
    public String[] longStandAloneWeekdayNames;
    public String[] shortStandAloneWeekdayNames;

    public String fullTimeFormat;
    public String longTimeFormat;
    public String mediumTimeFormat;
    public String shortTimeFormat;

    public String fullDateFormat;
    public String longDateFormat;
    public String mediumDateFormat;
    public String shortDateFormat;

    // Used by DecimalFormatSymbols.
    public char zeroDigit;
    public char decimalSeparator;
    public char groupingSeparator;
    public char patternSeparator;
    public char percent;
    public char perMill;
    public char monetarySeparator;
    public char minusSign;
    public String exponentSeparator;
    public String infinity;
    public String NaN;
    // Also used by Currency.
    public String currencySymbol;
    public String internationalCurrencySymbol;

    // Used by DecimalFormat and NumberFormat.
    public String numberPattern;
    public String integerPattern;
    public String currencyPattern;
    public String percentPattern;

    private LocaleData() {
    }

    /**
     * Returns a shared LocaleData for the given locale.
     */
    public static LocaleData get(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String localeName = locale.toString();
        synchronized (localeDataCache) {
            LocaleData localeData = localeDataCache.get(localeName);
            if (localeData != null) {
                return localeData;
            }
        }
        LocaleData newLocaleData = makeLocaleData(locale);
        synchronized (localeDataCache) {
            LocaleData localeData = localeDataCache.get(localeName);
            if (localeData != null) {
                return localeData;
            }
            localeDataCache.put(localeName, newLocaleData);
            return newLocaleData;
        }
    }

    private static LocaleData makeLocaleData(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        // Start with data from the parent (next-most-specific) locale...
        LocaleData result = new LocaleData();
        if (!variant.isEmpty()) {
            result.overrideWithDataFrom(get(new Locale(language, country, "")));
        } else if (!country.isEmpty()) {
            result.overrideWithDataFrom(get(new Locale(language, "", "")));
        } else if (!language.isEmpty()) {
            result.overrideWithDataFrom(get(Locale.ROOT));
        }
        // Override with data from this locale.
        result.overrideWithDataFrom(initLocaleData(locale));
        return result;
    }

    @Override public String toString() {
        return "LocaleData[" +
                "firstDayOfWeek=" + firstDayOfWeek + "," +
                "minimalDaysInFirstWeek=" + minimalDaysInFirstWeek + "," +
                "amPm=" + Arrays.toString(amPm) + "," +
                "eras=" + Arrays.toString(eras) + "," +
                "longMonthNames=" + Arrays.toString(longMonthNames) + "," +
                "shortMonthNames=" + Arrays.toString(shortMonthNames) + "," +
                "longStandAloneMonthNames=" + Arrays.toString(longStandAloneMonthNames) + "," +
                "shortStandAloneMonthNames=" + Arrays.toString(shortStandAloneMonthNames) + "," +
                "longWeekdayNames=" + Arrays.toString(longWeekdayNames) + "," +
                "shortWeekdayNames=" + Arrays.toString(shortWeekdayNames) + "," +
                "longStandAloneWeekdayNames=" + Arrays.toString(longStandAloneWeekdayNames) + "," +
                "shortStandAloneWeekdayNames=" + Arrays.toString(shortStandAloneWeekdayNames) + "," +
                "fullTimeFormat=" + fullTimeFormat + "," +
                "longTimeFormat=" + longTimeFormat + "," +
                "mediumTimeFormat=" + mediumTimeFormat + "," +
                "shortTimeFormat=" + shortTimeFormat + "," +
                "fullDateFormat=" + fullDateFormat + "," +
                "longDateFormat=" + longDateFormat + "," +
                "mediumDateFormat=" + mediumDateFormat + "," +
                "shortDateFormat=" + shortDateFormat + "," +
                "zeroDigit=" + zeroDigit + "," +
                "decimalSeparator=" + decimalSeparator + "," +
                "groupingSeparator=" + groupingSeparator + "," +
                "patternSeparator=" + patternSeparator + "," +
                "percent=" + percent + "," +
                "perMill=" + perMill + "," +
                "monetarySeparator=" + monetarySeparator + "," +
                "minusSign=" + minusSign + "," +
                "exponentSeparator=" + exponentSeparator + "," +
                "infinity=" + infinity + "," +
                "NaN=" + NaN + "," +
                "currencySymbol=" + currencySymbol + "," +
                "internationalCurrencySymbol=" + internationalCurrencySymbol + "," +
                "numberPattern=" + numberPattern + "," +
                "integerPattern=" + integerPattern + "," +
                "currencyPattern=" + currencyPattern + "," +
                "percentPattern=" + percentPattern + "]";
    }

    private void overrideWithDataFrom(LocaleData overrides) {
        if (overrides.firstDayOfWeek != null) {
            firstDayOfWeek = overrides.firstDayOfWeek;
        }
        if (overrides.minimalDaysInFirstWeek != null) {
            minimalDaysInFirstWeek = overrides.minimalDaysInFirstWeek;
        }
        if (overrides.amPm != null) {
            amPm = overrides.amPm;
        }
        if (overrides.eras != null) {
            eras = overrides.eras;
        }
        if (overrides.longMonthNames != null) {
            longMonthNames = overrides.longMonthNames;
        }
        if (overrides.shortMonthNames != null) {
            shortMonthNames = overrides.shortMonthNames;
        }
        if (overrides.longStandAloneMonthNames != null) {
            longStandAloneMonthNames = overrides.longStandAloneMonthNames;
        }
        if (overrides.shortStandAloneMonthNames != null) {
            shortStandAloneMonthNames = overrides.shortStandAloneMonthNames;
        }
        if (overrides.longWeekdayNames != null) {
            longWeekdayNames = overrides.longWeekdayNames;
        }
        if (overrides.shortWeekdayNames != null) {
            shortWeekdayNames = overrides.shortWeekdayNames;
        }
        if (overrides.longStandAloneWeekdayNames != null) {
            longStandAloneWeekdayNames = overrides.longStandAloneWeekdayNames;
        }
        if (overrides.shortStandAloneWeekdayNames != null) {
            shortStandAloneWeekdayNames = overrides.shortStandAloneWeekdayNames;
        }
        if (overrides.fullTimeFormat != null) {
            fullTimeFormat = overrides.fullTimeFormat;
        }
        if (overrides.longTimeFormat != null) {
            longTimeFormat = overrides.longTimeFormat;
        }
        if (overrides.mediumTimeFormat != null) {
            mediumTimeFormat = overrides.mediumTimeFormat;
        }
        if (overrides.shortTimeFormat != null) {
            shortTimeFormat = overrides.shortTimeFormat;
        }
        if (overrides.fullDateFormat != null) {
            fullDateFormat = overrides.fullDateFormat;
        }
        if (overrides.longDateFormat != null) {
            longDateFormat = overrides.longDateFormat;
        }
        if (overrides.mediumDateFormat != null) {
            mediumDateFormat = overrides.mediumDateFormat;
        }
        if (overrides.shortDateFormat != null) {
            shortDateFormat = overrides.shortDateFormat;
        }
        if (overrides.zeroDigit != '\0') {
            zeroDigit = overrides.zeroDigit;
        }
        if (overrides.decimalSeparator != '\0') {
            decimalSeparator = overrides.decimalSeparator;
        }
        if (overrides.groupingSeparator != '\0') {
            groupingSeparator = overrides.groupingSeparator;
        }
        if (overrides.patternSeparator != '\0') {
            patternSeparator = overrides.patternSeparator;
        }
        if (overrides.percent != '\0') {
            percent = overrides.percent;
        }
        if (overrides.perMill != '\0') {
            perMill = overrides.perMill;
        }
        if (overrides.monetarySeparator != '\0') {
            monetarySeparator = overrides.monetarySeparator;
        }
        if (overrides.minusSign != '\0') {
            minusSign = overrides.minusSign;
        }
        if (overrides.exponentSeparator != null) {
            exponentSeparator = overrides.exponentSeparator;
        }
        if (overrides.NaN != null) {
            NaN = overrides.NaN;
        }
        if (overrides.infinity != null) {
            infinity = overrides.infinity;
        }
        if (overrides.currencySymbol != null) {
            currencySymbol = overrides.currencySymbol;
        }
        if (overrides.internationalCurrencySymbol != null) {
            internationalCurrencySymbol = overrides.internationalCurrencySymbol;
        }
        if (overrides.numberPattern != null) {
            numberPattern = overrides.numberPattern;
        }
        if (overrides.integerPattern != null) {
            integerPattern = overrides.integerPattern;
        }
        if (overrides.currencyPattern != null) {
            currencyPattern = overrides.currencyPattern;
        }
        if (overrides.percentPattern != null) {
            percentPattern = overrides.percentPattern;
        }
    }

    public String getDateFormat(int style) {
        switch (style) {
        case DateFormat.SHORT:
            return shortDateFormat;
        case DateFormat.MEDIUM:
            return mediumDateFormat;
        case DateFormat.LONG:
            return longDateFormat;
        case DateFormat.FULL:
            return fullDateFormat;
        }
        throw new AssertionError();
    }

    public String getTimeFormat(int style) {
        switch (style) {
        case DateFormat.SHORT:
            return shortTimeFormat;
        case DateFormat.MEDIUM:
            return mediumTimeFormat;
        case DateFormat.LONG:
            return longTimeFormat;
        case DateFormat.FULL:
            return fullTimeFormat;
        }
        throw new AssertionError();
    }

    private static LocaleData initLocaleData(Locale locale) {
        LocaleData localeData = new LocaleData();
        if (!ICU.initLocaleDataImpl(locale.toString(), localeData)) {
            throw new AssertionError("couldn't initialize LocaleData for locale " + locale);
        }
        if (localeData.fullTimeFormat != null) {
            // There are some full time format patterns in ICU that use the pattern character 'v'.
            // Java doesn't accept this, so we replace it with 'z' which has about the same result
            // as 'v', the timezone name.
            // 'v' -> "PT", 'z' -> "PST", v is the generic timezone and z the standard tz
            // "vvvv" -> "Pacific Time", "zzzz" -> "Pacific Standard Time"
            localeData.fullTimeFormat = localeData.fullTimeFormat.replace('v', 'z');
        }
        if (localeData.numberPattern != null) {
            // The number pattern might contain positive and negative subpatterns. Arabic, for
            // example, might look like "#,##0.###;#,##0.###-" because the minus sign should be
            // written last. Macedonian supposedly looks something like "#,##0.###;(#,##0.###)".
            // (The negative subpattern is optional, though, and not present in most locales.)
            // By only swallowing '#'es and ','s after the '.', we ensure that we don't
            // accidentally eat too much.
            localeData.integerPattern = localeData.numberPattern.replaceAll("\\.[#,]*", "");
        }
        return localeData;
    }
}
