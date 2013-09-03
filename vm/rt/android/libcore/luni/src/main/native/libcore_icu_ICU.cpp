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

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedFd.h"
#include "ScopedJavaUnicodeString.h"
#include "ScopedLocalRef.h"
#include "ScopedStringChars.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "cutils/log.h"
#include "toStringArray.h"
#include "unicode/calendar.h"
#include "unicode/datefmt.h"
#include "unicode/dcfmtsym.h"
#include "unicode/decimfmt.h"
#include "unicode/dtfmtsym.h"
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

class ScopedResourceBundle {
public:
    ScopedResourceBundle(UResourceBundle* bundle) : mBundle(bundle) {
    }

    ~ScopedResourceBundle() {
        if (mBundle != NULL) {
            ures_close(mBundle);
        }
    }

    UResourceBundle* get() {
        return mBundle;
    }

private:
    UResourceBundle* mBundle;

    // Disallow copy and assignment.
    ScopedResourceBundle(const ScopedResourceBundle&);
    void operator=(const ScopedResourceBundle&);
};

Locale getLocale(JNIEnv* env, jstring localeName) {
    return Locale::createFromName(ScopedUtfChars(env, localeName).c_str());
}

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
    UnicodeString icuCurrencyCode(currencyCode.unicodeString());
    UErrorCode status = U_ZERO_ERROR;
    return ucurr_getDefaultFractionDigits(icuCurrencyCode.getTerminatedBuffer(), &status);
}

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

extern "C" jstring Java_libcore_icu_ICU_getCurrencyDisplayName(JNIEnv* env, jclass, jstring javaLocaleName, jstring javaCurrencyCode) {
    ScopedUtfChars localeName(env, javaLocaleName);
    ScopedJavaUnicodeString currencyCode(env, javaCurrencyCode);
    UnicodeString icuCurrencyCode(currencyCode.unicodeString());
    UErrorCode status = U_ZERO_ERROR;
    UBool isChoiceFormat;
    int32_t charCount;
    const UChar* chars = ucurr_getName(icuCurrencyCode.getTerminatedBuffer(), localeName.c_str(),
            UCURR_LONG_NAME, &isChoiceFormat, &charCount, &status);
    if (status == U_USING_DEFAULT_WARNING) {
        // ICU's default is English. We want the ISO 4217 currency code instead.
        chars = icuCurrencyCode.getBuffer();
        charCount = icuCurrencyCode.length();
    }
    return (charCount == 0) ? NULL : env->NewString(chars, charCount);
}

extern "C" jstring Java_libcore_icu_ICU_getCurrencySymbol(JNIEnv* env, jclass, jstring locale, jstring currencyCode) {
    // We can't use ucurr_getName because it doesn't distinguish between using data root from
    // the root locale and parroting back the input because it's never heard of the currency code.
    ScopedUtfChars localeName(env, locale);
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle currLoc(ures_open(U_ICUDATA_CURR, localeName.c_str(), &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    ScopedResourceBundle currencies(ures_getByKey(currLoc.get(), "Currencies", NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    ScopedUtfChars currency(env, currencyCode);
    ScopedResourceBundle currencyElems(ures_getByKey(currencies.get(), currency.c_str(), NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    int32_t charCount;
    const jchar* chars = ures_getStringByIndex(currencyElems.get(), 0, &charCount, &status);
    if (U_FAILURE(status)) {
        return NULL;
    }
    return (charCount == 0) ? NULL : env->NewString(chars, charCount);
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
        ALOGE("Error setting String field %s from ICU resource: %s", fieldName, u_errorName(status));
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

static void setNumberPatterns(JNIEnv* env, jobject obj, jstring locale) {
    UErrorCode status = U_ZERO_ERROR;
    Locale localeObj = getLocale(env, locale);

    UnicodeString pattern;
    UniquePtr<DecimalFormat> fmt(static_cast<DecimalFormat*>(NumberFormat::createInstance(localeObj, UNUM_CURRENCY, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "currencyPattern", pattern);

    fmt.reset(static_cast<DecimalFormat*>(NumberFormat::createInstance(localeObj, UNUM_DECIMAL, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "numberPattern", pattern);

    fmt.reset(static_cast<DecimalFormat*>(NumberFormat::createInstance(localeObj, UNUM_PERCENT, status)));
    pattern = fmt->toPattern(pattern.remove());
    setStringField(env, obj, "percentPattern", pattern);
}

static void setDecimalFormatSymbolsData(JNIEnv* env, jobject obj, jstring locale) {
    UErrorCode status = U_ZERO_ERROR;
    Locale localeObj = getLocale(env, locale);
    DecimalFormatSymbols dfs(localeObj, status);

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

extern "C" jboolean Java_libcore_icu_ICU_initLocaleDataImpl(JNIEnv* env, jclass, jstring locale, jobject localeData) {
    ScopedUtfChars localeName(env, locale);
    if (localeName.c_str() == NULL) {
        return JNI_FALSE;
    }

    // Get DateTimePatterns
    UErrorCode status;
    char currentLocale[ULOC_FULLNAME_CAPACITY];
    int32_t localeNameLen = 0;
    if (localeName.size() >= ULOC_FULLNAME_CAPACITY) {
        return JNI_FALSE;  // Exceed ICU defined limit of the whole locale ID.
    }
    strcpy(currentLocale, localeName.c_str());
    do {
        status = U_ZERO_ERROR;
        ScopedResourceBundle root(ures_open(NULL, currentLocale, &status));
        if (U_FAILURE(status)) {
            if (localeNameLen == 0) {
                break;  // No parent locale, report this error outside the loop.
            } else {
                status = U_ZERO_ERROR;
                continue;  // get parent locale.
            }
        }
        ScopedResourceBundle calendar(ures_getByKey(root.get(), "calendar", NULL, &status));
        if (U_FAILURE(status)) {
            status = U_ZERO_ERROR;
            continue;  // get parent locale.
        }

        ScopedResourceBundle gregorian(ures_getByKey(calendar.get(), "gregorian", NULL, &status));
        if (U_FAILURE(status)) {
            status = U_ZERO_ERROR;
            continue;  // get parent locale.
        }
        ScopedResourceBundle dateTimePatterns(ures_getByKey(gregorian.get(), "DateTimePatterns", NULL, &status));
        if (U_SUCCESS(status)) {
            setStringField(env, localeData, "fullTimeFormat", dateTimePatterns.get(), 0);
            setStringField(env, localeData, "longTimeFormat", dateTimePatterns.get(), 1);
            setStringField(env, localeData, "mediumTimeFormat", dateTimePatterns.get(), 2);
            setStringField(env, localeData, "shortTimeFormat", dateTimePatterns.get(), 3);
            setStringField(env, localeData, "fullDateFormat", dateTimePatterns.get(), 4);
            setStringField(env, localeData, "longDateFormat", dateTimePatterns.get(), 5);
            setStringField(env, localeData, "mediumDateFormat", dateTimePatterns.get(), 6);
            setStringField(env, localeData, "shortDateFormat", dateTimePatterns.get(), 7);
            break;
        } else {
            status = U_ZERO_ERROR;  // get parent locale.
        }
    } while((localeNameLen = uloc_getParent(currentLocale, currentLocale, sizeof(currentLocale), &status)) >= 0);
    if (U_FAILURE(status)) {
        ALOGE("Error getting ICU resource bundle: %s", u_errorName(status));
        return JNI_FALSE;
    }

    status = U_ZERO_ERROR;
    Locale localeObj = getLocale(env, locale);

    UniquePtr<Calendar> cal(Calendar::createInstance(localeObj, status));
    if (U_FAILURE(status)) {
        return JNI_FALSE;
    }
    setIntegerField(env, localeData, "firstDayOfWeek", cal->getFirstDayOfWeek());
    setIntegerField(env, localeData, "minimalDaysInFirstWeek", cal->getMinimalDaysInFirstWeek());

    // Get DateFormatSymbols
    status = U_ZERO_ERROR;
    DateFormatSymbols dateFormatSym(localeObj, status);
    if (U_FAILURE(status)) {
        return JNI_FALSE;
    }
    int32_t count = 0;
    // Get AM/PM marker
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
    const UnicodeString* longWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::FORMAT, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longWeekdayNames", longWeekdayNames, count);
    const UnicodeString* shortWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::FORMAT, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortWeekdayNames", shortWeekdayNames, count);

    const UnicodeString* longStandAloneMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longStandAloneMonthNames", longStandAloneMonthNames, count);
    const UnicodeString* shortStandAloneMonthNames =
        dateFormatSym.getMonths(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortStandAloneMonthNames", shortStandAloneMonthNames, count);
    const UnicodeString* longStandAloneWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::WIDE);
    setStringArrayField(env, localeData, "longStandAloneWeekdayNames", longStandAloneWeekdayNames, count);
    const UnicodeString* shortStandAloneWeekdayNames =
        dateFormatSym.getWeekdays(count, DateFormatSymbols::STANDALONE, DateFormatSymbols::ABBREVIATED);
    setStringArrayField(env, localeData, "shortStandAloneWeekdayNames", shortStandAloneWeekdayNames, count);

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
        currencySymbol = Java_libcore_icu_ICU_getCurrencySymbol(env, NULL, locale, internationalCurrencySymbol);
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
    UnicodeString& s(scopedString.unicodeString());
    UnicodeString original(s);
    s.toLower(Locale::createFromName(ScopedUtfChars(env, localeName).c_str()));
    return s == original ? javaString : env->NewString(s.getBuffer(), s.length());
}

extern "C" jstring Java_libcore_icu_ICU_toUpperCase(JNIEnv* env, jclass, jstring javaString, jstring localeName) {
    ScopedJavaUnicodeString scopedString(env, javaString);
    UnicodeString& s(scopedString.unicodeString());
    UnicodeString original(s);
    s.toUpper(Locale::createFromName(ScopedUtfChars(env, localeName).c_str()));
    return s == original ? javaString : env->NewString(s.getBuffer(), s.length());
}

extern "C" jstring versionString(JNIEnv* env, const UVersionInfo& version) {
    char versionString[U_MAX_VERSION_STRING_LENGTH];
    u_versionToString(const_cast<UVersionInfo&>(version), &versionString[0]);
    return env->NewStringUTF(versionString);
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


struct EnumerationCounter {
    const size_t count;
    EnumerationCounter(size_t count) : count(count) {}
    size_t operator()() { return count; }
};
struct EnumerationGetter {
    UEnumeration* e;
    UErrorCode* status;
    EnumerationGetter(UEnumeration* e, UErrorCode* status) : e(e), status(status) {}
    const UChar* operator()(int32_t* charCount) { return uenum_unext(e, charCount, status); }
};
extern "C" jobject Java_libcore_icu_ICU_getAvailableCurrencyCodes(JNIEnv* env, jclass) {
    UErrorCode status = U_ZERO_ERROR;
    UEnumeration* e(ucurr_openISOCurrencies(UCURR_COMMON|UCURR_NON_DEPRECATED, &status));
    EnumerationCounter counter(uenum_count(e, &status));
    EnumerationGetter getter(e, &status);
    jobject result = toStringArray16(env, &counter, &getter);
    maybeThrowIcuException(env, status);
    uenum_close(e);
    return result;
}

// RoboVM note: The code below was added to support different ICU .dat files and 
// determine which one to use at runtime rather than at compile time.
// iOS 5.0/5.1 use ICU 4.8 while iOS 6.0 uses ICU 49.
// We had to add a hack to icu4c/common/udata.cpp to intercept lookups in the data
// file and modify the entry names.

// Paths of supported ICU versions in order of preference
const char * const supportedIcuPaths[] = { "icudt48l", "icudt49l", "icudt46l", "icudt51l", NULL };
// The ICU path we're using
static const char *icuPath = NULL;

extern void (*icuModTocEntryNameFunc)(const char *, char *);

static void modTocEntryName(const char *oldTocEntryName, char *newTocEntryName) {
    if (!strncmp(oldTocEntryName, U_ICUDATA_NAME, sizeof(U_ICUDATA_NAME) - 1)) {
        strcpy(newTocEntryName, icuPath);
        strcat(newTocEntryName, &oldTocEntryName[sizeof(U_ICUDATA_NAME) - 1]);
    } else {
        strcpy(newTocEntryName, oldTocEntryName);
    }
}

int register_libcore_icu_ICU(JNIEnv* env) {
    std::string path;
    for (unsigned int i = 0; supportedIcuPaths[i] != NULL; i++) {
        path = u_getDataDirectory();
        path += "/";
        path += supportedIcuPaths[i];
        path += ".dat";
        if (!access(path.c_str(), R_OK)) {
            icuPath = supportedIcuPaths[i];
            break;
        }
    }

    if (!icuPath) {
        // None of the supported paths found.
        ALOGE("No supported ICU data file found in %s", u_getDataDirectory()); \
        abort();
    }

    icuModTocEntryNameFunc = modTocEntryName;

// RoboVM note: This ends the changes made to support different ICU versions.

    #define FAIL_WITH_STRERROR(s) \
        ALOGE("Couldn't " s " '%s': %s", path.c_str(), strerror(errno)); \
        abort();
    #define MAYBE_FAIL_WITH_ICU_ERROR(s) \
        if (status != U_ZERO_ERROR) {\
            ALOGE("Couldn't initialize ICU (" s "): %s (%s)", u_errorName(status), path.c_str()); \
            abort(); \
        }

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
