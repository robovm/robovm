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

#define LOG_TAG "ICU"

#include "IcuUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedFd.h"
#include "ScopedJavaUnicodeString.h"
#include "ScopedLocalRef.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "cutils/log.h"
#include "toStringArray.h"
#include "unicode/calendar.h"
#include "unicode/datefmt.h"
#include "unicode/dcfmtsym.h"
#include "unicode/decimfmt.h"
#include "unicode/dtfmtsym.h"
#include "unicode/dtptngen.h"
#include "unicode/gregocal.h"
#include "unicode/locid.h"
#include "unicode/numfmt.h"
#include "unicode/strenum.h"
#include "unicode/ubrk.h"
#include "unicode/ucal.h"
#include "unicode/uclean.h"
#include "unicode/ucol.h"
#include "unicode/ucurr.h"
#include "unicode/udat.h"
#include "unicode/uloc.h"
#include "unicode/ulocdata.h"
#include "unicode/ustring.h"
#include "ureslocs.h"
#include "valueOf.h"

#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

// RoboVM note: Start change.
#if defined(__APPLE__)
#include <mach-o/dyld.h> // for _NSGetExecutablePath()
#include <libgen.h>      // for dirname()
#endif
#include "icudt51l.dat.gz.h"
#include "zlib.h"
// RoboVM note: End chage.

// TODO: put this in a header file and use it everywhere!
// DISALLOW_COPY_AND_ASSIGN disallows the copy and operator= functions.
// It goes in the private: declarations in a class.
#define DISALLOW_COPY_AND_ASSIGN(TypeName) \
    TypeName(const TypeName&); \
    void operator=(const TypeName&)

class ScopedResourceBundle {
 public:
  ScopedResourceBundle(UResourceBundle* bundle) : bundle_(bundle) {
  }

  ~ScopedResourceBundle() {
    if (bundle_ != NULL) {
      ures_close(bundle_);
    }
  }

  UResourceBundle* get() {
    return bundle_;
  }

  bool hasKey(const char* key) {
    UErrorCode status = U_ZERO_ERROR;
    ures_getStringByKey(bundle_, key, NULL, &status);
    return U_SUCCESS(status);
  }

 private:
  UResourceBundle* bundle_;
  DISALLOW_COPY_AND_ASSIGN(ScopedResourceBundle);
};

extern "C" jstring Java_libcore_icu_ICU_addLikelySubtags(JNIEnv* env, jclass, jstring javaLocale) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedUtfChars localeID(env, javaLocale);
    char maximizedLocaleID[ULOC_FULLNAME_CAPACITY];
    uloc_addLikelySubtags(localeID.c_str(), maximizedLocaleID, sizeof(maximizedLocaleID), &status);
    if (U_FAILURE(status)) {
        return javaLocale;
    }
    return env->NewStringUTF(maximizedLocaleID);
}

extern "C" jstring Java_libcore_icu_ICU_getScript(JNIEnv* env, jclass, jstring javaLocale) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedUtfChars localeID(env, javaLocale);
    char script[ULOC_SCRIPT_CAPACITY];
    uloc_getScript(localeID.c_str(), script, sizeof(script), &status);
    if (U_FAILURE(status)) {
        return NULL;
    }
    return env->NewStringUTF(script);
}

extern "C" jint Java_libcore_icu_ICU_getCurrencyFractionDigits(JNIEnv* env, jclass, jstring javaCurrencyCode) {
  ScopedJavaUnicodeString currencyCode(env, javaCurrencyCode);
  if (!currencyCode.valid()) {
    return 0;
  }
  UnicodeString icuCurrencyCode(currencyCode.unicodeString());
  UErrorCode status = U_ZERO_ERROR;
  return ucurr_getDefaultFractionDigits(icuCurrencyCode.getTerminatedBuffer(), &status);
}

// TODO: rewrite this with int32_t ucurr_forLocale(const char* locale, UChar* buff, int32_t buffCapacity, UErrorCode* ec)...
extern "C" jstring Java_libcore_icu_ICU_getCurrencyCode(JNIEnv* env, jclass, jstring javaCountryCode) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle supplData(ures_openDirect(U_ICUDATA_CURR, "supplementalData", &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    ScopedResourceBundle currencyMap(ures_getByKey(supplData.get(), "CurrencyMap", NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    ScopedUtfChars countryCode(env, javaCountryCode);
    ScopedResourceBundle currency(ures_getByKey(currencyMap.get(), countryCode.c_str(), NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    ScopedResourceBundle currencyElem(ures_getByIndex(currency.get(), 0, NULL, &status));
    if (U_FAILURE(status)) {
        return env->NewStringUTF("XXX");
    }

    // Check if there's a 'to' date. If there is, the currency isn't used anymore.
    ScopedResourceBundle currencyTo(ures_getByKey(currencyElem.get(), "to", NULL, &status));
    if (!U_FAILURE(status)) {
        return NULL;
    }
    // Ignore the failure to find a 'to' date.
    status = U_ZERO_ERROR;

    ScopedResourceBundle currencyId(ures_getByKey(currencyElem.get(), "id", NULL, &status));
    if (U_FAILURE(status)) {
        // No id defined for this country
        return env->NewStringUTF("XXX");
    }

    int32_t charCount;
    const jchar* chars = ures_getString(currencyId.get(), &charCount, &status);
    return (charCount == 0) ? env->NewStringUTF("XXX") : env->NewString(chars, charCount);
}

static jstring getCurrencyName(JNIEnv* env, jstring javaLocaleName, jstring javaCurrencyCode, UCurrNameStyle nameStyle) {
  ScopedUtfChars localeName(env, javaLocaleName);
  if (localeName.c_str() == NULL) {
    return NULL;
  }
  ScopedJavaUnicodeString currencyCode(env, javaCurrencyCode);
  if (!currencyCode.valid()) {
    return NULL;
  }
  UnicodeString icuCurrencyCode(currencyCode.unicodeString());
  UErrorCode status = U_ZERO_ERROR;
  UBool isChoiceFormat = false;
  int32_t charCount;
  const UChar* chars = ucurr_getName(icuCurrencyCode.getTerminatedBuffer(), localeName.c_str(),
                                     nameStyle, &isChoiceFormat, &charCount, &status);
  if (status == U_USING_DEFAULT_WARNING) {
    if (nameStyle == UCURR_SYMBOL_NAME) {
      // ICU doesn't distinguish between falling back to the root locale and meeting a genuinely
      // unknown currency. The Currency class does.
      if (!ucurr_isAvailable(icuCurrencyCode.getTerminatedBuffer(), U_DATE_MIN, U_DATE_MAX, &status)) {
        return NULL;
      }
    }
    if (nameStyle == UCURR_LONG_NAME) {
      // ICU's default is English. We want the ISO 4217 currency code instead.
      chars = icuCurrencyCode.getBuffer();
      charCount = icuCurrencyCode.length();
    }
  }
  return (charCount == 0) ? NULL : env->NewString(chars, charCount);
}

extern "C" jstring Java_libcore_icu_ICU_getCurrencyDisplayName(JNIEnv* env, jclass, jstring javaLocaleName, jstring javaCurrencyCode) {
  return getCurrencyName(env, javaLocaleName, javaCurrencyCode, UCURR_LONG_NAME);
}

extern "C" jstring Java_libcore_icu_ICU_getCurrencySymbol(JNIEnv* env, jclass, jstring javaLocaleName, jstring javaCurrencyCode) {
  return getCurrencyName(env, javaLocaleName, javaCurrencyCode, UCURR_SYMBOL_NAME);
}

extern "C" jstring Java_libcore_icu_ICU_getDisplayCountryNative(JNIEnv* env, jclass, jstring targetLocale, jstring locale) {
    Locale loc = getLocale(env, locale);
    Locale targetLoc = getLocale(env, targetLocale);
    UnicodeString str;
    targetLoc.getDisplayCountry(loc, str);
    return env->NewString(str.getBuffer(), str.length());
}

extern "C" jstring Java_libcore_icu_ICU_getDisplayLanguageNative(JNIEnv* env, jclass, jstring targetLocale, jstring locale) {
    Locale loc = getLocale(env, locale);
    Locale targetLoc = getLocale(env, targetLocale);
    UnicodeString str;
    targetLoc.getDisplayLanguage(loc, str);
    return env->NewString(str.getBuffer(), str.length());
}

extern "C" jstring Java_libcore_icu_ICU_getDisplayVariantNative(JNIEnv* env, jclass, jstring targetLocale, jstring locale) {
    Locale loc = getLocale(env, locale);
    Locale targetLoc = getLocale(env, targetLocale);
    UnicodeString str;
    targetLoc.getDisplayVariant(loc, str);
    return env->NewString(str.getBuffer(), str.length());
}

extern "C" jstring Java_libcore_icu_ICU_getISO3CountryNative(JNIEnv* env, jclass, jstring locale) {
    Locale loc = getLocale(env, locale);
    return env->NewStringUTF(loc.getISO3Country());
}

extern "C" jstring Java_libcore_icu_ICU_getISO3LanguageNative(JNIEnv* env, jclass, jstring locale) {
    Locale loc = getLocale(env, locale);
    return env->NewStringUTF(loc.getISO3Language());
}

extern "C" jobjectArray Java_libcore_icu_ICU_getISOCountriesNative(JNIEnv* env, jclass) {
    return toStringArray(env, Locale::getISOCountries());
}

extern "C" jobjectArray Java_libcore_icu_ICU_getISOLanguagesNative(JNIEnv* env, jclass) {
    return toStringArray(env, Locale::getISOLanguages());
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, uloc_countAvailable, uloc_getAvailable);
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableBreakIteratorLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, ubrk_countAvailable, ubrk_getAvailable);
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableCalendarLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, ucal_countAvailable, ucal_getAvailable);
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableCollatorLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, ucol_countAvailable, ucol_getAvailable);
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableDateFormatLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, udat_countAvailable, udat_getAvailable);
}

extern "C" jobjectArray Java_libcore_icu_ICU_getAvailableNumberFormatLocalesNative(JNIEnv* env, jclass) {
    return toStringArray(env, unum_countAvailable, unum_getAvailable);
}

static void setIntegerField(JNIEnv* env, jobject obj, const char* fieldName, int value) {
    ScopedLocalRef<jobject> integerValue(env, integerValueOf(env, value));
    jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, fieldName, "Ljava/lang/Integer;");
    env->SetObjectField(obj, fid, integerValue.get());
}

static void setStringField(JNIEnv* env, jobject obj, const char* fieldName, jstring value) {
    jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, fieldName, "Ljava/lang/String;");
    env->SetObjectField(obj, fid, value);
    env->DeleteLocalRef(value);
}

static void setStringArrayField(JNIEnv* env, jobject obj, const char* fieldName, jobjectArray value) {
    jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, fieldName, "[Ljava/lang/String;");
    env->SetObjectField(obj, fid, value);
}

static void setStringArrayField(JNIEnv* env, jobject obj, const char* fieldName, const UnicodeString* valueArray, int32_t size) {
    ScopedLocalRef<jobjectArray> result(env, env->NewObjectArray(size, JniConstants::stringClass, NULL));
    for (int32_t i = 0; i < size ; i++) {
        ScopedLocalRef<jstring> s(env, env->NewString(valueArray[i].getBuffer(),valueArray[i].length()));
        if (env->ExceptionCheck()) {
            return;
        }
        env->SetObjectArrayElement(result.get(), i, s.get());
        if (env->ExceptionCheck()) {
            return;
        }
    }
    setStringArrayField(env, obj, fieldName, result.get());
}

static void setStringField(JNIEnv* env, jobject obj, const char* fieldName, UResourceBundle* bundle, int index) {
  UErrorCode status = U_ZERO_ERROR;
  int charCount;
  const UChar* chars = ures_getStringByIndex(bundle, index, &charCount, &status);
  if (U_SUCCESS(status)) {
    setStringField(env, obj, fieldName, env->NewString(chars, charCount));
  } else {
    ALOGE("Error setting String field %s from ICU resource (index %d): %s", fieldName, index, u_errorName(status));
  }
}

static void setStringField(JNIEnv* env, jobject obj, const char* fieldName, UResourceBundle* bundle, const char* key) {
  UErrorCode status = U_ZERO_ERROR;
  int charCount;
  const UChar* chars = ures_getStringByKey(bundle, key, &charCount, &status);
  if (U_SUCCESS(status)) {
    setStringField(env, obj, fieldName, env->NewString(chars, charCount));
  } else {
    ALOGE("Error setting String field %s from ICU resource (key %s): %s", fieldName, key, u_errorName(status));
  }
}

static void setCharField(JNIEnv* env, jobject obj, const char* fieldName, const UnicodeString& value) {
    if (value.length() == 0) {
        return;
    }
    jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, fieldName, "C");
    env->SetCharField(obj, fid, value.charAt(0));
}

static void setStringField(JNIEnv* env, jobject obj, const char* fieldName, const UnicodeString& value) {
    const UChar* chars = value.getBuffer();
    setStringField(env, obj, fieldName, env->NewString(chars, value.length()));
}

static void setNumberPatterns(JNIEnv* env, jobject obj, Locale& locale) {
    UErrorCode status = U_ZERO_ERROR;

    UnicodeString pattern;
    UniquePtr<DecimalFormat> fmt(static_cast<DecimalFormat*>(NumberFormat::createInstance(locale, UNUM_CURRENCY, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "currencyPattern", pattern);

    fmt.reset(static_cast<DecimalFormat*>(NumberFormat::createInstance(locale, UNUM_DECIMAL, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "numberPattern", pattern);

    fmt.reset(static_cast<DecimalFormat*>(NumberFormat::createInstance(locale, UNUM_PERCENT, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "percentPattern", pattern);
}

static void setDecimalFormatSymbolsData(JNIEnv* env, jobject obj, Locale& locale) {
    UErrorCode status = U_ZERO_ERROR;
    DecimalFormatSymbols dfs(locale, status);

    setCharField(env, obj, "decimalSeparator", dfs.getSymbol(DecimalFormatSymbols::kDecimalSeparatorSymbol));
    setCharField(env, obj, "groupingSeparator", dfs.getSymbol(DecimalFormatSymbols::kGroupingSeparatorSymbol));
    setCharField(env, obj, "patternSeparator", dfs.getSymbol(DecimalFormatSymbols::kPatternSeparatorSymbol));
    setCharField(env, obj, "percent", dfs.getSymbol(DecimalFormatSymbols::kPercentSymbol));
    setCharField(env, obj, "perMill", dfs.getSymbol(DecimalFormatSymbols::kPerMillSymbol));
    setCharField(env, obj, "monetarySeparator", dfs.getSymbol(DecimalFormatSymbols::kMonetarySeparatorSymbol));
    setCharField(env, obj, "minusSign", dfs.getSymbol(DecimalFormatSymbols:: kMinusSignSymbol));
    setStringField(env, obj, "exponentSeparator", dfs.getSymbol(DecimalFormatSymbols::kExponentialSymbol));
    setStringField(env, obj, "infinity", dfs.getSymbol(DecimalFormatSymbols::kInfinitySymbol));
    setStringField(env, obj, "NaN", dfs.getSymbol(DecimalFormatSymbols::kNaNSymbol));
    setCharField(env, obj, "zeroDigit", dfs.getSymbol(DecimalFormatSymbols::kZeroDigitSymbol));
}


// Iterates up through the locale hierarchy. So "en_US" would return "en_US", "en", "".
class LocaleNameIterator {
 public:
  LocaleNameIterator(const char* locale_name, UErrorCode& status) : status_(status), has_next_(true) {
    strcpy(locale_name_, locale_name);
    locale_name_length_ = strlen(locale_name_);
  }

  const char* Get() {
      return locale_name_;
  }

  bool HasNext() {
    return has_next_;
  }

  void Up() {
    if (locale_name_length_ == 0) {
      has_next_ = false;
    } else {
      locale_name_length_ = uloc_getParent(locale_name_, locale_name_, sizeof(locale_name_), &status_);
    }
  }

 private:
  UErrorCode& status_;
  bool has_next_;
  char locale_name_[ULOC_FULLNAME_CAPACITY];
  int32_t locale_name_length_;

  DISALLOW_COPY_AND_ASSIGN(LocaleNameIterator);
};

static bool getDateTimePatterns(JNIEnv* env, jobject localeData, const char* locale_name) {
  UErrorCode status = U_ZERO_ERROR;
  ScopedResourceBundle root(ures_open(NULL, locale_name, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle calendar(ures_getByKey(root.get(), "calendar", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle gregorian(ures_getByKey(calendar.get(), "gregorian", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle dateTimePatterns(ures_getByKey(gregorian.get(), "DateTimePatterns", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  setStringField(env, localeData, "fullTimeFormat", dateTimePatterns.get(), 0);
  setStringField(env, localeData, "longTimeFormat", dateTimePatterns.get(), 1);
  setStringField(env, localeData, "mediumTimeFormat", dateTimePatterns.get(), 2);
  setStringField(env, localeData, "shortTimeFormat", dateTimePatterns.get(), 3);
  setStringField(env, localeData, "fullDateFormat", dateTimePatterns.get(), 4);
  setStringField(env, localeData, "longDateFormat", dateTimePatterns.get(), 5);
  setStringField(env, localeData, "mediumDateFormat", dateTimePatterns.get(), 6);
  setStringField(env, localeData, "shortDateFormat", dateTimePatterns.get(), 7);
  return true;
}

static bool getYesterdayTodayAndTomorrow(JNIEnv* env, jobject localeData, const char* locale_name) {
  UErrorCode status = U_ZERO_ERROR;
  ScopedResourceBundle root(ures_open(NULL, locale_name, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle fields(ures_getByKey(root.get(), "fields", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle day(ures_getByKey(fields.get(), "day", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  ScopedResourceBundle relative(ures_getByKey(day.get(), "relative", NULL, &status));
  if (U_FAILURE(status)) {
    return false;
  }
  // bn_BD only has a "-2" entry.
  if (relative.hasKey("-1") && relative.hasKey("0") && relative.hasKey("1")) {
    setStringField(env, localeData, "yesterday", relative.get(), "-1");
    setStringField(env, localeData, "today", relative.get(), "0");
    setStringField(env, localeData, "tomorrow", relative.get(), "1");
    return true;
  }
  return false;
}

extern "C" jboolean Java_libcore_icu_ICU_initLocaleDataNative(JNIEnv* env, jclass, jstring javaLocaleName, jobject localeData) {
    ScopedUtfChars localeName(env, javaLocaleName);
    if (localeName.c_str() == NULL) {
        return JNI_FALSE;
    }
    if (localeName.size() >= ULOC_FULLNAME_CAPACITY) {
        return JNI_FALSE; // ICU has a fixed-length limit.
    }

    // Get the DateTimePatterns.
    UErrorCode status = U_ZERO_ERROR;
    bool foundDateTimePatterns = false;
    for (LocaleNameIterator it(localeName.c_str(), status); it.HasNext(); it.Up()) {
      if (getDateTimePatterns(env, localeData, it.Get())) {
          foundDateTimePatterns = true;
          break;
      }
    }
    if (!foundDateTimePatterns) {
        ALOGE("Couldn't find ICU DateTimePatterns for %s", localeName.c_str());
        return JNI_FALSE;
    }

    // Get the "Yesterday", "Today", and "Tomorrow" strings.
    bool foundYesterdayTodayAndTomorrow = false;
    for (LocaleNameIterator it(localeName.c_str(), status); it.HasNext(); it.Up()) {
      if (getYesterdayTodayAndTomorrow(env, localeData, it.Get())) {
        foundYesterdayTodayAndTomorrow = true;
        break;
      }
    }
    if (!foundYesterdayTodayAndTomorrow) {
      ALOGE("Couldn't find ICU yesterday/today/tomorrow for %s", localeName.c_str());
      return JNI_FALSE;
    }

    status = U_ZERO_ERROR;
    Locale locale = getLocale(env, javaLocaleName);
    UniquePtr<Calendar> cal(Calendar::createInstance(locale, status));
    if (U_FAILURE(status)) {
        return JNI_FALSE;
    }

    setIntegerField(env, localeData, "firstDayOfWeek", cal->getFirstDayOfWeek());
    setIntegerField(env, localeData, "minimalDaysInFirstWeek", cal->getMinimalDaysInFirstWeek());

    // Get DateFormatSymbols.
    status = U_ZERO_ERROR;
    DateFormatSymbols dateFormatSym(locale, status);
    if (U_FAILURE(status)) {
        return JNI_FALSE;
    }

    // Get AM/PM and BC/AD.
    int32_t count = 0;
    const UnicodeString* amPmStrs = dateFormatSym.getAmPmStrings(count);
    setStringArrayField(env, localeData, "amPm", amPmStrs, count);
    const UnicodeString* erasStrs = dateFormatSym.getEras(count);
    setStringArrayField(env, localeData, "eras", erasStrs, count);

    const UnicodeString* longMonthNames =
       dateFormatSym.getMonths(count, DateFormatSymbols::FORMAT, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longMonthNames", longMonthNames, count);
    const UnicodeString* shortMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::FORMAT, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortMonthNames", shortMonthNames, count);
    const UnicodeString* tinyMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::FORMAT, DateFormatSymbols::NARROW);
    setStringArrayField(env, localeData, "tinyMonthNames", tinyMonthNames, count);
    const UnicodeString* longWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::FORMAT, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longWeekdayNames", longWeekdayNames, count);
    const UnicodeString* shortWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::FORMAT, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortWeekdayNames", shortWeekdayNames, count);
    const UnicodeString* tinyWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::FORMAT, DateFormatSymbols::NARROW);
    setStringArrayField(env, localeData, "tinyWeekdayNames", tinyWeekdayNames, count);

    const UnicodeString* longStandAloneMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longStandAloneMonthNames", longStandAloneMonthNames, count);
    const UnicodeString* shortStandAloneMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortStandAloneMonthNames", shortStandAloneMonthNames, count);
    const UnicodeString* tinyStandAloneMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::NARROW);
    setStringArrayField(env, localeData, "tinyStandAloneMonthNames", tinyStandAloneMonthNames, count);
    const UnicodeString* longStandAloneWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longStandAloneWeekdayNames", longStandAloneWeekdayNames, count);
    const UnicodeString* shortStandAloneWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortStandAloneWeekdayNames", shortStandAloneWeekdayNames, count);
    const UnicodeString* tinyStandAloneWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::NARROW);
    setStringArrayField(env, localeData, "tinyStandAloneWeekdayNames", tinyStandAloneWeekdayNames, count);

    status = U_ZERO_ERROR;

    // For numberPatterns and symbols.
    setNumberPatterns(env, localeData, locale);
    setDecimalFormatSymbolsData(env, localeData, locale);

    jstring countryCode = env->NewStringUTF(Locale::createFromName(localeName.c_str()).getCountry());
    jstring internationalCurrencySymbol = Java_libcore_icu_ICU_getCurrencyCode(env, NULL, countryCode);
    env->DeleteLocalRef(countryCode);
    countryCode = NULL;

    jstring currencySymbol = NULL;
    if (internationalCurrencySymbol != NULL) {
        currencySymbol = Java_libcore_icu_ICU_getCurrencySymbol(env, NULL, javaLocaleName, internationalCurrencySymbol);
    } else {
        internationalCurrencySymbol = env->NewStringUTF("XXX");
    }
    if (currencySymbol == NULL) {
        // This is the UTF-8 encoding of U+00A4 (CURRENCY SIGN).
        currencySymbol = env->NewStringUTF("\xc2\xa4");
    }
    setStringField(env, localeData, "currencySymbol", currencySymbol);
    setStringField(env, localeData, "internationalCurrencySymbol", internationalCurrencySymbol);

    return JNI_TRUE;
}

extern "C" jstring Java_libcore_icu_ICU_toLowerCase(JNIEnv* env, jclass, jstring javaString, jstring localeName) {
  ScopedJavaUnicodeString scopedString(env, javaString);
  if (!scopedString.valid()) {
    return NULL;
  }
  UnicodeString& s(scopedString.unicodeString());
  UnicodeString original(s);
  s.toLower(Locale::createFromName(ScopedUtfChars(env, localeName).c_str()));
  return s == original ? javaString : env->NewString(s.getBuffer(), s.length());
}

extern "C" jstring Java_libcore_icu_ICU_toUpperCase(JNIEnv* env, jclass, jstring javaString, jstring localeName) {
  ScopedJavaUnicodeString scopedString(env, javaString);
  if (!scopedString.valid()) {
    return NULL;
  }
  UnicodeString& s(scopedString.unicodeString());
  UnicodeString original(s);
  s.toUpper(Locale::createFromName(ScopedUtfChars(env, localeName).c_str()));
  return s == original ? javaString : env->NewString(s.getBuffer(), s.length());
}

static jstring versionString(JNIEnv* env, const UVersionInfo& version) {
    char versionString[U_MAX_VERSION_STRING_LENGTH];
    u_versionToString(const_cast<UVersionInfo&>(version), &versionString[0]);
    return env->NewStringUTF(versionString);
}

extern "C" jstring Java_libcore_icu_ICU_getCldrVersion(JNIEnv* env, jclass) {
  UErrorCode status = U_ZERO_ERROR;
  UVersionInfo cldrVersion;
  ulocdata_getCLDRVersion(cldrVersion, &status);
  return versionString(env, cldrVersion);
}

extern "C" jstring Java_libcore_icu_ICU_getIcuVersion(JNIEnv* env, jclass) {
    UVersionInfo icuVersion;
    u_getVersion(icuVersion);
    return versionString(env, icuVersion);
}

extern "C" jstring Java_libcore_icu_ICU_getUnicodeVersion(JNIEnv* env, jclass) {
    UVersionInfo unicodeVersion;
    u_getUnicodeVersion(unicodeVersion);
    return versionString(env, unicodeVersion);
}

extern "C" jobject Java_libcore_icu_ICU_getAvailableCurrencyCodes(JNIEnv* env, jclass) {
  UErrorCode status = U_ZERO_ERROR;
  UStringEnumeration e(ucurr_openISOCurrencies(UCURR_COMMON|UCURR_NON_DEPRECATED, &status));
  return fromStringEnumeration(env, status, "ucurr_openISOCurrencies", &e);
}

extern "C" jstring Java_libcore_icu_ICU_getBestDateTimePatternNative(JNIEnv* env, jclass, jstring javaSkeleton, jstring javaLocaleName) {
  Locale locale = getLocale(env, javaLocaleName);
  UErrorCode status = U_ZERO_ERROR;
  UniquePtr<DateTimePatternGenerator> generator(DateTimePatternGenerator::createInstance(locale, status));
  if (maybeThrowIcuException(env, "DateTimePatternGenerator::createInstance", status)) {
    return NULL;
  }

  ScopedJavaUnicodeString skeletonHolder(env, javaSkeleton);
  if (!skeletonHolder.valid()) {
    return NULL;
  }
  UnicodeString result(generator->getBestPattern(skeletonHolder.unicodeString(), status));
  if (maybeThrowIcuException(env, "DateTimePatternGenerator::getBestPattern", status)) {
    return NULL;
  }

  return env->NewString(result.getBuffer(), result.length());
}

// RoboVM note: Start change.
#if (__APPLE__)
static std::string getExecutablePath() {
    std::string exePath;
    uint32_t size = 0;
    char empty[] = ""; 
    char* buf = empty;
    _NSGetExecutablePath(buf, &size);
    buf = (char*) alloca(size);
    if (_NSGetExecutablePath(buf, &size) == -1) {
        abort();
    }
    buf = realpath(buf, NULL);
    if (!buf) {
        abort();
    }
    exePath = buf;
    free(buf);
    return exePath;
}
#endif

static bool findCustomICUData(std::string& result) {
#if (__APPLE__)
    std::string exePath = getExecutablePath();
    std::string basePath = dirname((char*) exePath.c_str());
#else
    // TODO: Implement custom ICU data lookup on Linux.
    std::string basePath = "/usr/share/icu";
#endif
    std::string icuDataPath = basePath;
    icuDataPath += "/";
    icuDataPath += U_ICUDATA_NAME;
    icuDataPath += ".dat";
    if (!access(icuDataPath.c_str(), R_OK)) {
        result = icuDataPath;
        return true;
    }
    return false;
}

static int inflate(const void *src, int srcLen, void *dst, int dstLen) {
    z_stream strm  = {0};
    strm.total_in  = strm.avail_in  = srcLen;
    strm.total_out = strm.avail_out = dstLen;
    strm.next_in   = (Bytef *) src;
    strm.next_out  = (Bytef *) dst;

    strm.zalloc = Z_NULL;
    strm.zfree  = Z_NULL;
    strm.opaque = Z_NULL;

    int err = -1;
    int ret = -1;

    err = inflateInit2(&strm, (15 + 16)); // 15 window bits, gzipped data (16)
    if (err == Z_OK) {
        err = inflate(&strm, Z_FINISH);
        if (err == Z_STREAM_END) {
            ret = strm.total_out;
        } else {
            inflateEnd(&strm);
            return err;
        }
    } else {
        inflateEnd(&strm);
        return err;
    }

    inflateEnd(&strm);
    return ret;
}
// RoboVM note: End change.

int register_libcore_icu_ICU(JNIEnv* env) {
    std::string path;

    #define FAIL_WITH_STRERROR(s) \
        ALOGE("Couldn't " s " '%s': %s", path.c_str(), strerror(errno)); \
        abort();
    #define MAYBE_FAIL_WITH_ICU_ERROR(s) \
        if (status != U_ZERO_ERROR) {\
            ALOGE("Couldn't initialize ICU (" s "): %s (%s)", u_errorName(status), path.c_str()); \
            abort(); \
        }

    // RoboVM note: Start change. Look for custom ICU data and fall back to builtin minimal data if not found.
    if (!findCustomICUData(path)) {
        ALOGI("Using builtin minimal ICU data");
        void* data = malloc(out_icudt51l_dat_len);
        if (!data) {
            ALOGE("Failed to allocate %d bytes for builtin ICU data", out_icudt51l_dat_len);
            abort();
        }
        int ret = inflate(out_icudt51l_dat_gz, out_icudt51l_dat_gz_len, data, out_icudt51l_dat_len);
        if (ret != out_icudt51l_dat_len) {
            ALOGE("Failed to inflate %d->%d bytes of builtin ICU data: %d", 
                out_icudt51l_dat_gz_len, out_icudt51l_dat_len, ret);
            abort();
        }

        UErrorCode status = U_ZERO_ERROR;
        udata_setCommonData(data, &status);
        MAYBE_FAIL_WITH_ICU_ERROR("udata_setCommonData");
        // Tell ICU it can *only* use our data.
        udata_setFileAccess(UDATA_NO_FILES, &status);
        MAYBE_FAIL_WITH_ICU_ERROR("udata_setFileAccess");
        // Force initialization up front so we can report a nice clear error and bail.
        u_init(&status);
        MAYBE_FAIL_WITH_ICU_ERROR("u_init");

        return 0;
    }

    ALOGI("Using custom ICU data file: '%s'", path.c_str());
    // RoboVM note: End change.

    // Open the file and get its length.
    ScopedFd fd(open(path.c_str(), O_RDONLY));
    if (fd.get() == -1) {
        FAIL_WITH_STRERROR("open");
    }
    struct stat sb;
    if (fstat(fd.get(), &sb) == -1) {
        FAIL_WITH_STRERROR("stat");
    }

    // Map it.
    void* data = mmap(NULL, sb.st_size, PROT_READ, MAP_SHARED, fd.get(), 0);
    if (data == MAP_FAILED) {
        FAIL_WITH_STRERROR("mmap");
    }

    // Tell the kernel that accesses are likely to be random rather than sequential.
    if (madvise(data, sb.st_size, MADV_RANDOM) == -1) {
        FAIL_WITH_STRERROR("madvise(MADV_RANDOM)");
    }

    // Tell ICU to use our memory-mapped data.
    UErrorCode status = U_ZERO_ERROR;
    udata_setCommonData(data, &status);
    MAYBE_FAIL_WITH_ICU_ERROR("udata_setCommonData");
    // Tell ICU it can *only* use our memory-mapped data.
    udata_setFileAccess(UDATA_NO_FILES, &status);
    MAYBE_FAIL_WITH_ICU_ERROR("udata_setFileAccess");

    // Failures to find the ICU data tend to be somewhat obscure because ICU loads its data on first
    // use, which can be anywhere. Force initialization up front so we can report a nice clear error
    // and bail.
    u_init(&status);
    MAYBE_FAIL_WITH_ICU_ERROR("u_init");
    return 0;
}
