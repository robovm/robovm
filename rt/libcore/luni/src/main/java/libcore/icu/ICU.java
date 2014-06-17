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
import libcore.util.BasicLruCache;

/**
 * Makes ICU data accessible to Java.
 */
public final class ICU {
  private static final BasicLruCache<String, String> CACHED_PATTERNS =
      new BasicLruCache<String, String>(8);

  private static Locale[] availableLocalesCache;

  private static String[] isoCountries;

  private static String[] isoLanguages;

  /**
   * Returns an array of two-letter ISO 639-1 language codes, either from ICU or our cache.
   */
  public static String[] getISOLanguages() {
    if (isoLanguages == null) {
      isoLanguages = getISOLanguagesNative();
    }
    return isoLanguages.clone();
  }

  /**
   * Returns an array of two-letter ISO 3166 country codes, either from ICU or our cache.
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
      String language = localeName.substring(0, first);
      String country = localeName.substring(first + 1);
      return new Locale(language, country);
    } else {
      // Language and country and variant ("ja_JP_TRADITIONAL").
      String language = localeName.substring(0, first);
      String country = localeName.substring(first + 1, second);
      String variant = localeName.substring(second + 1);
      return new Locale(language, country, variant);
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

  public static String getBestDateTimePattern(String skeleton, String localeName) {
    String key = skeleton + "\t" + localeName;
    synchronized (CACHED_PATTERNS) {
      String pattern = CACHED_PATTERNS.get(key);
      if (pattern == null) {
        pattern = getBestDateTimePatternNative(skeleton, localeName);
        CACHED_PATTERNS.put(key, pattern);
      }
      return pattern;
    }
  }

  private static native String getBestDateTimePatternNative(String skeleton, String localeName);

  public static char[] getDateFormatOrder(String pattern) {
    char[] result = new char[3];
    int resultIndex = 0;
    boolean sawDay = false;
    boolean sawMonth = false;
    boolean sawYear = false;

    for (int i = 0; i < pattern.length(); ++i) {
      char ch = pattern.charAt(i);
      if (ch == 'd' || ch == 'L' || ch == 'M' || ch == 'y') {
        if (ch == 'd' && !sawDay) {
          result[resultIndex++] = 'd';
          sawDay = true;
        } else if ((ch == 'L' || ch == 'M') && !sawMonth) {
          result[resultIndex++] = 'M';
          sawMonth = true;
        } else if ((ch == 'y') && !sawYear) {
          result[resultIndex++] = 'y';
          sawYear = true;
        }
      } else if (ch == 'G') {
        // Ignore the era specifier, if present.
      } else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
        throw new IllegalArgumentException("Bad pattern character '" + ch + "' in " + pattern);
      } else if (ch == '\'') {
        if (i < pattern.length() - 1 && pattern.charAt(i + 1) == '\'') {
          ++i;
        } else {
          i = pattern.indexOf('\'', i + 1);
          if (i == -1) {
            throw new IllegalArgumentException("Bad quoting in " + pattern);
          }
          ++i;
        }
      } else {
        // Ignore spaces and punctuation.
      }
    }
    return result;
  }

  /**
   * Returns the version of the CLDR data in use, such as "22.1.1".
   */
  public static native String getCldrVersion();

  /**
   * Returns the icu4c version in use, such as "50.1.1".
   */
  public static native String getIcuVersion();

  /**
   * Returns the Unicode version our ICU supports, such as "6.2".
   */
  public static native String getUnicodeVersion();

  // --- Case mapping.

  public static native String toLowerCase(String s, String localeName);
  public static native String toUpperCase(String s, String localeName);

  // --- Errors.

  // Just the subset of error codes needed by CharsetDecoderICU/CharsetEncoderICU.
  public static final int U_ZERO_ERROR = 0;
  public static final int U_INVALID_CHAR_FOUND = 10;
  public static final int U_TRUNCATED_CHAR_FOUND = 11;
  public static final int U_ILLEGAL_CHAR_FOUND = 12;
  public static final int U_BUFFER_OVERFLOW_ERROR = 15;

  public static boolean U_FAILURE(int error) {
    return error > U_ZERO_ERROR;
  }

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

  static native boolean initLocaleDataNative(String locale, LocaleData result);
}
