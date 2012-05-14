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
        return env->NewStringUTF("None");
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
        return env->NewStringUTF("None");
    }

    int32_t charCount;
    const jchar* chars = ures_getString(currencyId.get(), &charCount, &status);
    return (charCount == 0) ? env->NewStringUTF("None") : env->NewString(chars, charCount);
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

static bool getDayIntVector(JNIEnv*, UResourceBundle* gregorian, int* values) {
    // get the First day of week and the minimal days in first week numbers
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle gregorianElems(ures_getByKey(gregorian, "DateTimeElements", NULL, &status));
    if (U_FAILURE(status)) {
        return false;
    }

    int intVectSize;
    const int* result = ures_getIntVector(gregorianElems.get(), &intVectSize, &status);
    if (U_FAILURE(status) || intVectSize != 2) {
        return false;
    }

    values[0] = result[0];
    values[1] = result[1];
    return true;
}

// This allows you to leave extra space at the beginning or end of the array to support the
// month names and day names arrays.
static jobjectArray toStringArray(JNIEnv* env, UResourceBundle* rb, size_t size, int capacity, size_t offset) {
    if (capacity == -1) {
        capacity = size;
    }
    jobjectArray result = env->NewObjectArray(capacity, JniConstants::stringClass, NULL);
    if (result == NULL) {
        return NULL;
    }
    UErrorCode status = U_ZERO_ERROR;
    for (size_t i = 0; i < size; ++i) {
        int charCount;
        const jchar* chars = ures_getStringByIndex(rb, i, &charCount, &status);
        if (U_FAILURE(status)) {
            return NULL;
        }
        ScopedLocalRef<jstring> s(env, env->NewString(chars, charCount));
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(result, offset + i, s.get());
        if (env->ExceptionCheck()) {
            return NULL;
        }
    }
    return result;
}

static jobjectArray getAmPmMarkers(JNIEnv* env, UResourceBundle* gregorian) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle amPmMarkers(ures_getByKey(gregorian, "AmPmMarkers", NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }
    return toStringArray(env, amPmMarkers.get(), ures_getSize(amPmMarkers.get()), -1, 0);
}

static jobjectArray getEras(JNIEnv* env, UResourceBundle* gregorian) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle eras(ures_getByKey(gregorian, "eras", NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }
    ScopedResourceBundle abbreviatedEras(ures_getByKey(eras.get(), "abbreviated", NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }
    return toStringArray(env, abbreviatedEras.get(), ures_getSize(abbreviatedEras.get()), -1, 0);
}

enum NameType { REGULAR, STAND_ALONE };
enum NameWidth { LONG, SHORT };
static jobjectArray getNames(JNIEnv* env, UResourceBundle* namesBundle, bool months, NameType type, NameWidth width) {
    const char* typeKey = (type == REGULAR) ? "format" : "stand-alone";
    const char* widthKey = (width == LONG) ? "wide" : "abbreviated";
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle formatBundle(ures_getByKey(namesBundle, typeKey, NULL, &status));
    ScopedResourceBundle valuesBundle(ures_getByKey(formatBundle.get(), widthKey, NULL, &status));
    if (U_FAILURE(status)) {
        return NULL;
    }

    // The months array has a trailing empty string. The days array has a leading empty string.
    int count = ures_getSize(valuesBundle.get());
    int offset = months ? 0 : 1;
    jobjectArray result = toStringArray(env, valuesBundle.get(), count, count + 1, offset);
    ScopedLocalRef<jstring> emptyString(env, env->NewStringUTF(""));
    env->SetObjectArrayElement(result, months ? count : 0, emptyString.get());
    return result;
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

static void setStringField(JNIEnv* env, jobject obj, const char* fieldName, UResourceBundle* bundle, int index) {
    UErrorCode status = U_ZERO_ERROR;
    int charCount;
    const UChar* chars = ures_getStringByIndex(bundle, index, &charCount, &status);
    if (U_SUCCESS(status)) {
        setStringField(env, obj, fieldName, env->NewString(chars, charCount));
    } else {
        LOGE("Error setting String field %s from ICU resource: %s", fieldName, u_errorName(status));
    }
}

static bool setStringField(JNIEnv* env, jobject obj, const char* key, const char* fieldName, UResourceBundle* bundle) {
    if (bundle == NULL) {
        return false;
    }
    UErrorCode status = U_ZERO_ERROR;
    int charCount;
    const UChar* chars = ures_getStringByKey(bundle, key, &charCount, &status);
    if (U_SUCCESS(status)) {
        setStringField(env, obj, fieldName, env->NewString(chars, charCount));
        return true;
    } else {
        // Missing item in current resource bundle but not an error.
        return false;
    }
}

static void setStringField(JNIEnv* env, jobject obj, const char* key, const char* fieldName,
    UResourceBundle* bundle, UResourceBundle* fallbackBundle) {
    if (!setStringField(env, obj, key, fieldName, bundle) && fallbackBundle != NULL) {
        setStringField(env, obj, key, fieldName, fallbackBundle);
    }
}

static bool setCharField(JNIEnv* env, jobject obj, const char* key, const char* fieldName,
    UResourceBundle* bundle) {
    if (bundle == NULL) {
        return false;
    }
    UErrorCode status = U_ZERO_ERROR;
    int charCount;
    const UChar* chars = ures_getStringByKey(bundle, key, &charCount, &status);
    if (U_SUCCESS(status)) {
        jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, fieldName, "C");
        env->SetCharField(obj, fid, chars[0]);
        return true;
    } else {
        // Missing item in current resource bundle but not an error.
        return false;
    }
}

static void setCharField(JNIEnv* env, jobject obj, const char* key, const char* fieldName,
    UResourceBundle* bundle, UResourceBundle* fallbackBundle) {
    if (!setCharField(env, obj, key, fieldName, bundle) && fallbackBundle != NULL) {
        setCharField(env, obj, key, fieldName, fallbackBundle);
    }
}

static void setNumberSymbols(JNIEnv* env, jobject obj, UResourceBundle* numberSymbols, UResourceBundle* fallbackNumberSymbols) {
    setCharField(env, obj, "decimal", "decimalSeparator", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "group", "groupingSeparator", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "list", "patternSeparator", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "percentSign", "percent", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "perMille", "perMill", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "decimal", "monetarySeparator", numberSymbols, fallbackNumberSymbols);
    setCharField(env, obj, "minusSign", "minusSign", numberSymbols, fallbackNumberSymbols);
    setStringField(env, obj, "exponential", "exponentSeparator", numberSymbols, fallbackNumberSymbols);
    setStringField(env, obj, "infinity", "infinity", numberSymbols, fallbackNumberSymbols);
    setStringField(env, obj, "nan", "NaN", numberSymbols, fallbackNumberSymbols);
}

static void setZeroDigitToDefault(JNIEnv* env, jobject obj) {
    static jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, "zeroDigit", "C");
    env->SetCharField(obj, fid, '0');
}

static void setZeroDigit(JNIEnv* env, jobject obj, bool isLatn, char* buffer) {
    if (isLatn || buffer == NULL || buffer[0] == '\0') {
        return setZeroDigitToDefault(env, obj);
    }
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle numSystemRoot(ures_openDirect(NULL, "numberingSystems", &status));
    if (U_FAILURE(status)) {
        return setZeroDigitToDefault(env, obj);
    }
    ScopedResourceBundle numSystem(ures_getByKey(numSystemRoot.get(), "numberingSystems", NULL, &status));
    if (U_FAILURE(status)) {
        return setZeroDigitToDefault(env, obj);
    }
    ScopedResourceBundle nonLatnSystem(ures_getByKey(numSystem.get(), buffer, NULL, &status));
    if (U_FAILURE(status)) {
        return setZeroDigitToDefault(env, obj);
    }
    int32_t charCount = 0;
    const UChar* chars = ures_getStringByKey(nonLatnSystem.get(), "desc", &charCount, &status);
    if (charCount == 0) {
        setZeroDigitToDefault(env, obj);
    } else {
        static jfieldID fid = env->GetFieldID(JniConstants::localeDataClass, "zeroDigit", "C");
        env->SetCharField(obj, fid, chars[0]);
    }
}

static void setNumberElements(JNIEnv* env, jobject obj, UResourceBundle* numberElements) {
    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle latnNumberRB(ures_getByKey(numberElements, "latn", NULL, &status));
    if (U_FAILURE(status)) {
        LOGW("Error getting ICU latn number elements system value: %s", u_errorName(status));
        return;
    }
    ScopedResourceBundle patternsRB(ures_getByKey(latnNumberRB.get(), "patterns", NULL, &status));
    if (U_FAILURE(status)) {
        LOGW("Error getting ICU latn number patterns value: %s", u_errorName(status));
        return;
    }
    // Get the patterns from the 'latn' numberElements
    // This is a temporary workaround for ICU ticket#8611.
    UResourceBundle* bundle = patternsRB.get();
    setStringField(env, obj, "currencyFormat", "currencyPattern", bundle);
    setStringField(env, obj, "decimalFormat", "numberPattern", bundle);
    setStringField(env, obj, "percentFormat", "percentPattern", bundle);

    status = U_ZERO_ERROR;
    bool isLatn = false;
    char buffer[256];
    buffer[0] = '\0';
    ScopedResourceBundle defaultNumberElem(ures_getByKey(numberElements, "default", NULL, &status));
    if (U_SUCCESS(status)) {
        int32_t charCount = 256;
        ures_getUTF8String(defaultNumberElem.get(), buffer, &charCount, true, &status);
        buffer[charCount] = '\0';
        if (U_FAILURE(status)) {
            LOGW("Error getting ICU default number element system value: %s", u_errorName(status));
            // Use latn number symbols instead.
            isLatn = true;
        } else {
            isLatn = (strcmp(buffer, "latn") == 0);
        }
    } else {
        // Not default data, fallback to latn number elements.
        isLatn = true;
    }

    status = U_ZERO_ERROR;
    setZeroDigit(env, obj, isLatn, buffer);
    if (isLatn) {
        ScopedResourceBundle symbolsRB(ures_getByKey(latnNumberRB.get(), "symbols", NULL, &status));
        if (U_SUCCESS(status)) {
            setNumberSymbols(env, obj, symbolsRB.get(), NULL);
        } else {
            LOGW("Missing ICU latn symbols system value: %s", u_errorName(status));
        }
    } else {
        // Get every symbol item from default numbering system first. If it does not
        // exist, get the symbol from latn numbering system.
        ScopedResourceBundle defaultNumberRB(ures_getByKey(numberElements, (const char*)buffer, NULL, &status));
        ScopedResourceBundle defaultSymbolsRB(ures_getByKey(defaultNumberRB.get(), "symbols", NULL, &status));
        if (U_FAILURE(status)) {
            LOGW("Missing ICU %s symbols system value: %s", buffer, u_errorName(status));
            isLatn = true;  // Fallback to latn symbols.
            status = U_ZERO_ERROR;
        }
        ScopedResourceBundle latnSymbolsRB(ures_getByKey(latnNumberRB.get(), "symbols", NULL, &status));
        if (isLatn && U_FAILURE(status)) {
            return;
        }
        setNumberSymbols(env, obj, defaultSymbolsRB.get(), latnSymbolsRB.get());
    }
}

extern "C" jboolean Java_libcore_icu_ICU_initLocaleDataImpl(JNIEnv* env, jclass, jstring locale, jobject localeData) {
    ScopedUtfChars localeName(env, locale);
    if (localeName.c_str() == NULL) {
        return JNI_FALSE;
    }

    UErrorCode status = U_ZERO_ERROR;
    ScopedResourceBundle root(ures_open(NULL, localeName.c_str(), &status));
    if (U_FAILURE(status)) {
        LOGE("Error getting ICU resource bundle: %s", u_errorName(status));
        return JNI_FALSE;
    }

    ScopedResourceBundle calendar(ures_getByKey(root.get(), "calendar", NULL, &status));
    if (U_FAILURE(status)) {
        LOGE("Error getting ICU calendar resource bundle: %s", u_errorName(status));
        return JNI_FALSE;
    }

    ScopedResourceBundle gregorian(ures_getByKey(calendar.get(), "gregorian", NULL, &status));
    if (U_FAILURE(status)) {
        LOGE("Error getting ICU gregorian resource bundle: %s", u_errorName(status));
        return JNI_FALSE;
    }

    int firstDayVals[] = { 0, 0 };
    if (getDayIntVector(env, gregorian.get(), firstDayVals)) {
        setIntegerField(env, localeData, "firstDayOfWeek", firstDayVals[0]);
        setIntegerField(env, localeData, "minimalDaysInFirstWeek", firstDayVals[1]);
    }

    jobjectArray amPmMarkers = getAmPmMarkers(env, gregorian.get());
    setStringArrayField(env, localeData, "amPm", amPmMarkers);
    env->DeleteLocalRef(amPmMarkers);

    jobjectArray eras = getEras(env, gregorian.get());
    setStringArrayField(env, localeData, "eras", eras);
    env->DeleteLocalRef(eras);

    ScopedResourceBundle dayNames(ures_getByKey(gregorian.get(), "dayNames", NULL, &status));
    ScopedResourceBundle monthNames(ures_getByKey(gregorian.get(), "monthNames", NULL, &status));

    // Get the regular month and weekday names.
    jobjectArray longMonthNames = getNames(env, monthNames.get(), true, REGULAR, LONG);
    jobjectArray shortMonthNames = getNames(env, monthNames.get(), true, REGULAR, SHORT);
    jobjectArray longWeekdayNames = getNames(env, dayNames.get(), false, REGULAR, LONG);
    jobjectArray shortWeekdayNames = getNames(env, dayNames.get(), false, REGULAR, SHORT);
    setStringArrayField(env, localeData, "longMonthNames", longMonthNames);
    setStringArrayField(env, localeData, "shortMonthNames", shortMonthNames);
    setStringArrayField(env, localeData, "longWeekdayNames", longWeekdayNames);
    setStringArrayField(env, localeData, "shortWeekdayNames", shortWeekdayNames);

    // Get the stand-alone month and weekday names. If they're not available (as they aren't for
    // English), we reuse the regular names. If we returned null to Java, the usual fallback
    // mechanisms would come into play and we'd end up with the bogus stand-alone names from the
    // root locale ("1" for January, and so on).
    jobjectArray longStandAloneMonthNames = getNames(env, monthNames.get(), true, STAND_ALONE, LONG);
    if (longStandAloneMonthNames == NULL) {
        longStandAloneMonthNames = longMonthNames;
    }
    jobjectArray shortStandAloneMonthNames = getNames(env, monthNames.get(), true, STAND_ALONE, SHORT);
    if (shortStandAloneMonthNames == NULL) {
        shortStandAloneMonthNames = shortMonthNames;
    }
    jobjectArray longStandAloneWeekdayNames = getNames(env, dayNames.get(), false, STAND_ALONE, LONG);
    if (longStandAloneWeekdayNames == NULL) {
        longStandAloneWeekdayNames = longWeekdayNames;
    }
    jobjectArray shortStandAloneWeekdayNames = getNames(env, dayNames.get(), false, STAND_ALONE, SHORT);
    if (shortStandAloneWeekdayNames == NULL) {
        shortStandAloneWeekdayNames = shortWeekdayNames;
    }
    setStringArrayField(env, localeData, "longStandAloneMonthNames", longStandAloneMonthNames);
    setStringArrayField(env, localeData, "shortStandAloneMonthNames", shortStandAloneMonthNames);
    setStringArrayField(env, localeData, "longStandAloneWeekdayNames", longStandAloneWeekdayNames);
    setStringArrayField(env, localeData, "shortStandAloneWeekdayNames", shortStandAloneWeekdayNames);

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
    }
    status = U_ZERO_ERROR;

    // For numberPatterns and symbols.
    ScopedResourceBundle numberElements(ures_getByKey(root.get(), "NumberElements", NULL, &status));
    if (U_SUCCESS(status)) {
        setNumberElements(env, localeData, numberElements.get());
    }
    status = U_ZERO_ERROR;

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

int register_libcore_icu_ICU(JNIEnv* env) {
    std::string path;
    path = u_getDataDirectory();
    path += "/";
    path += U_ICUDATA_NAME;
    path += ".dat";

    #define FAIL_WITH_STRERROR(s) \
        LOGE("Couldn't " s " '%s': %s", path.c_str(), strerror(errno)); \
        return -1;
    #define MAYBE_FAIL_WITH_ICU_ERROR(s) \
        if (status != U_ZERO_ERROR) {\
            LOGE("Couldn't initialize ICU (" s "): %s (%s)", u_errorName(status), path.c_str()); \
            return -1; \
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
