/*
 * Copyright (C) 2006 The Android Open Source Project
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

#define LOG_TAG "NativeDecimalFormat"

#include "IcuUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedJavaUnicodeString.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedStringChars.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "cutils/log.h"
#include "digitlst.h"
#include "unicode/decimfmt.h"
#include "unicode/fmtable.h"
#include "unicode/numfmt.h"
#include "unicode/unum.h"
#include "unicode/ustring.h"
#include "valueOf.h"
#include <stdlib.h>
#include <string.h>

static DecimalFormat* toDecimalFormat(jlong addr) {
    return reinterpret_cast<DecimalFormat*>(static_cast<uintptr_t>(addr));
}

static UNumberFormat* toUNumberFormat(jlong addr) {
    return reinterpret_cast<UNumberFormat*>(static_cast<uintptr_t>(addr));
}

static DecimalFormatSymbols* makeDecimalFormatSymbols(JNIEnv* env,
        jstring currencySymbol0, jchar decimalSeparator, jchar digit, jstring exponentSeparator0,
        jchar groupingSeparator0, jstring infinity0,
        jstring internationalCurrencySymbol0, jchar minusSign,
        jchar monetaryDecimalSeparator, jstring nan0, jchar patternSeparator,
        jchar percent, jchar perMill, jchar zeroDigit) {
    ScopedJavaUnicodeString currencySymbol(env, currencySymbol0);
    ScopedJavaUnicodeString exponentSeparator(env, exponentSeparator0);
    ScopedJavaUnicodeString infinity(env, infinity0);
    ScopedJavaUnicodeString internationalCurrencySymbol(env, internationalCurrencySymbol0);
    ScopedJavaUnicodeString nan(env, nan0);
    UnicodeString groupingSeparator(groupingSeparator0);

    DecimalFormatSymbols* result = new DecimalFormatSymbols;
    result->setSymbol(DecimalFormatSymbols::kCurrencySymbol, currencySymbol.unicodeString());
    result->setSymbol(DecimalFormatSymbols::kDecimalSeparatorSymbol, UnicodeString(decimalSeparator));
    result->setSymbol(DecimalFormatSymbols::kDigitSymbol, UnicodeString(digit));
    result->setSymbol(DecimalFormatSymbols::kExponentialSymbol, exponentSeparator.unicodeString());
    result->setSymbol(DecimalFormatSymbols::kGroupingSeparatorSymbol, groupingSeparator);
    result->setSymbol(DecimalFormatSymbols::kMonetaryGroupingSeparatorSymbol, groupingSeparator);
    result->setSymbol(DecimalFormatSymbols::kInfinitySymbol, infinity.unicodeString());
    result->setSymbol(DecimalFormatSymbols::kIntlCurrencySymbol, internationalCurrencySymbol.unicodeString());
    result->setSymbol(DecimalFormatSymbols::kMinusSignSymbol, UnicodeString(minusSign));
    result->setSymbol(DecimalFormatSymbols::kMonetarySeparatorSymbol, UnicodeString(monetaryDecimalSeparator));
    result->setSymbol(DecimalFormatSymbols::kNaNSymbol, nan.unicodeString());
    result->setSymbol(DecimalFormatSymbols::kPatternSeparatorSymbol, UnicodeString(patternSeparator));
    result->setSymbol(DecimalFormatSymbols::kPercentSymbol, UnicodeString(percent));
    result->setSymbol(DecimalFormatSymbols::kPerMillSymbol, UnicodeString(perMill));
    // java.text.DecimalFormatSymbols just uses a zero digit,
    // but ICU >= 4.6 has a field for each decimal digit.
    result->setSymbol(DecimalFormatSymbols::kZeroDigitSymbol, UnicodeString(zeroDigit + 0));
    result->setSymbol(DecimalFormatSymbols::kOneDigitSymbol, UnicodeString(zeroDigit + 1));
    result->setSymbol(DecimalFormatSymbols::kTwoDigitSymbol, UnicodeString(zeroDigit + 2));
    result->setSymbol(DecimalFormatSymbols::kThreeDigitSymbol, UnicodeString(zeroDigit + 3));
    result->setSymbol(DecimalFormatSymbols::kFourDigitSymbol, UnicodeString(zeroDigit + 4));
    result->setSymbol(DecimalFormatSymbols::kFiveDigitSymbol, UnicodeString(zeroDigit + 5));
    result->setSymbol(DecimalFormatSymbols::kSixDigitSymbol, UnicodeString(zeroDigit + 6));
    result->setSymbol(DecimalFormatSymbols::kSevenDigitSymbol, UnicodeString(zeroDigit + 7));
    result->setSymbol(DecimalFormatSymbols::kEightDigitSymbol, UnicodeString(zeroDigit + 8));
    result->setSymbol(DecimalFormatSymbols::kNineDigitSymbol, UnicodeString(zeroDigit + 9));
    return result;
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_setDecimalFormatSymbols(JNIEnv* env, jclass, jlong addr,
        jstring currencySymbol, jchar decimalSeparator, jchar digit, jstring exponentSeparator,
        jchar groupingSeparator, jstring infinity,
        jstring internationalCurrencySymbol, jchar minusSign,
        jchar monetaryDecimalSeparator, jstring nan, jchar patternSeparator,
        jchar percent, jchar perMill, jchar zeroDigit) {
    DecimalFormatSymbols* symbols = makeDecimalFormatSymbols(env,
            currencySymbol, decimalSeparator, digit, exponentSeparator, groupingSeparator,
            infinity, internationalCurrencySymbol, minusSign,
            monetaryDecimalSeparator, nan, patternSeparator, percent, perMill,
            zeroDigit);
    toDecimalFormat(addr)->adoptDecimalFormatSymbols(symbols);
}

extern "C" jlong Java_libcore_icu_NativeDecimalFormat_open(JNIEnv* env, jclass, jstring pattern0,
        jstring currencySymbol, jchar decimalSeparator, jchar digit, jstring exponentSeparator,
        jchar groupingSeparator, jstring infinity,
        jstring internationalCurrencySymbol, jchar minusSign,
        jchar monetaryDecimalSeparator, jstring nan, jchar patternSeparator,
        jchar percent, jchar perMill, jchar zeroDigit) {
    UErrorCode status = U_ZERO_ERROR;
    UParseError parseError;
    ScopedJavaUnicodeString pattern(env, pattern0);
    if (!pattern.valid()) {
      return 0;
    }
    DecimalFormatSymbols* symbols = makeDecimalFormatSymbols(env,
            currencySymbol, decimalSeparator, digit, exponentSeparator, groupingSeparator,
            infinity, internationalCurrencySymbol, minusSign,
            monetaryDecimalSeparator, nan, patternSeparator, percent, perMill,
            zeroDigit);
    DecimalFormat* fmt = new DecimalFormat(pattern.unicodeString(), symbols, parseError, status);
    if (fmt == NULL) {
        delete symbols;
    }
    maybeThrowIcuException(env, "DecimalFormat::DecimalFormat", status);
    return reinterpret_cast<uintptr_t>(fmt);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_close(JNIEnv*, jclass, jlong addr) {
    delete toDecimalFormat(addr);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_setRoundingMode(JNIEnv*, jclass, jlong addr, jint mode, jdouble increment) {
    DecimalFormat* fmt = toDecimalFormat(addr);
    fmt->setRoundingMode(static_cast<DecimalFormat::ERoundingMode>(mode));
    fmt->setRoundingIncrement(increment);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_setSymbol(JNIEnv* env, jclass, jlong addr, jint javaSymbol, jstring javaValue) {
    ScopedStringChars value(env, javaValue);
    if (value.get() == NULL) {
        return;
    }
    UErrorCode status = U_ZERO_ERROR;
    UNumberFormatSymbol symbol = static_cast<UNumberFormatSymbol>(javaSymbol);
    unum_setSymbol(toUNumberFormat(addr), symbol, value.get(), value.size(), &status);
    maybeThrowIcuException(env, "unum_setSymbol", status);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_setAttribute(JNIEnv*, jclass, jlong addr, jint javaAttr, jint value) {
    UNumberFormatAttribute attr = static_cast<UNumberFormatAttribute>(javaAttr);
    unum_setAttribute(toUNumberFormat(addr), attr, value);
}

extern "C" jint Java_libcore_icu_NativeDecimalFormat_getAttribute(JNIEnv*, jclass, jlong addr, jint javaAttr) {
    UNumberFormatAttribute attr = static_cast<UNumberFormatAttribute>(javaAttr);
    return unum_getAttribute(toUNumberFormat(addr), attr);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_setTextAttribute(JNIEnv* env, jclass, jlong addr, jint javaAttr, jstring javaValue) {
    ScopedStringChars value(env, javaValue);
    if (value.get() == NULL) {
        return;
    }
    UErrorCode status = U_ZERO_ERROR;
    UNumberFormatTextAttribute attr = static_cast<UNumberFormatTextAttribute>(javaAttr);
    unum_setTextAttribute(toUNumberFormat(addr), attr, value.get(), value.size(), &status);
    maybeThrowIcuException(env, "unum_setTextAttribute", status);
}

extern "C" jstring Java_libcore_icu_NativeDecimalFormat_getTextAttribute(JNIEnv* env, jclass, jlong addr, jint javaAttr) {
    UErrorCode status = U_ZERO_ERROR;
    UNumberFormat* fmt = toUNumberFormat(addr);
    UNumberFormatTextAttribute attr = static_cast<UNumberFormatTextAttribute>(javaAttr);

    // Find out how long the result will be...
    UniquePtr<UChar[]> chars;
    uint32_t charCount = 0;
    uint32_t desiredCount = unum_getTextAttribute(fmt, attr, chars.get(), charCount, &status);
    if (status == U_BUFFER_OVERFLOW_ERROR) {
        // ...then get it.
        status = U_ZERO_ERROR;
        charCount = desiredCount + 1;
        chars.reset(new UChar[charCount]);
        charCount = unum_getTextAttribute(fmt, attr, chars.get(), charCount, &status);
    }
    return maybeThrowIcuException(env, "unum_getTextAttribute", status) ? NULL : env->NewString(chars.get(), charCount);
}

extern "C" void Java_libcore_icu_NativeDecimalFormat_applyPatternImpl(JNIEnv* env, jclass, jlong addr, jboolean localized, jstring pattern0) {
    ScopedJavaUnicodeString pattern(env, pattern0);
    if (!pattern.valid()) {
      return;
    }
    DecimalFormat* fmt = toDecimalFormat(addr);
    UErrorCode status = U_ZERO_ERROR;
    const char* function;
    if (localized) {
        function = "DecimalFormat::applyLocalizedPattern";
        fmt->applyLocalizedPattern(pattern.unicodeString(), status);
    } else {
        function = "DecimalFormat::applyPattern";
        fmt->applyPattern(pattern.unicodeString(), status);
    }
    maybeThrowIcuException(env, function, status);
}

extern "C" jstring Java_libcore_icu_NativeDecimalFormat_toPatternImpl(JNIEnv* env, jclass, jlong addr, jboolean localized) {
    DecimalFormat* fmt = toDecimalFormat(addr);
    UnicodeString pattern;
    if (localized) {
        fmt->toLocalizedPattern(pattern);
    } else {
        fmt->toPattern(pattern);
    }
    return env->NewString(pattern.getBuffer(), pattern.length());
}

static jcharArray formatResult(JNIEnv* env, const UnicodeString &str, FieldPositionIterator* fpi, jobject fpIter) {
    static jmethodID gFPI_setData = env->GetMethodID(JniConstants::fieldPositionIteratorClass, "setData", "([I)V");

    if (fpi != NULL) {
        int len = fpi->getData(NULL, 0);
        jintArray data = NULL;
        if (len) {
            data = env->NewIntArray(len);
            ScopedIntArrayRW ints(env, data);
            if (ints.get() == NULL) {
                return NULL;
            }
            fpi->getData(ints.get(), len);
        }
        env->CallVoidMethod(fpIter, gFPI_setData, data);
    }

    jcharArray result = env->NewCharArray(str.length());
    if (result != NULL) {
        env->SetCharArrayRegion(result, 0, str.length(), str.getBuffer());
    }
    return result;
}

template <typename T>
static jcharArray format(JNIEnv* env, jlong addr, jobject fpIter, T val) {
    UErrorCode status = U_ZERO_ERROR;
    UnicodeString str;
    DecimalFormat* fmt = toDecimalFormat(addr);
    FieldPositionIterator fpi;
    FieldPositionIterator* pfpi = fpIter ? &fpi : NULL;
    fmt->format(val, str, pfpi, status);
    return formatResult(env, str, pfpi, fpIter);
}

extern "C" jcharArray Java_libcore_icu_NativeDecimalFormat_formatLong(JNIEnv* env, jclass, jlong addr, jlong value, jobject fpIter) {
    return format(env, addr, fpIter, value);
}

extern "C" jcharArray Java_libcore_icu_NativeDecimalFormat_formatDouble(JNIEnv* env, jclass, jlong addr, jdouble value, jobject fpIter) {
    return format(env, addr, fpIter, value);
}

extern "C" jcharArray Java_libcore_icu_NativeDecimalFormat_formatDigitList(JNIEnv* env, jclass, jlong addr, jstring value, jobject fpIter) {
    ScopedUtfChars chars(env, value);
    if (chars.c_str() == NULL) {
        return NULL;
    }
    StringPiece sp(chars.c_str());
    return format(env, addr, fpIter, sp);
}

static jobject newBigDecimal(JNIEnv* env, const char* value, jsize len) {
    static jmethodID gBigDecimal_init = env->GetMethodID(JniConstants::bigDecimalClass, "<init>", "(Ljava/lang/String;)V");

    // this is painful...
    // value is a UTF-8 string of invariant characters, but isn't guaranteed to be
    // null-terminated.  NewStringUTF requires a terminated UTF-8 string.  So we copy the
    // data to jchars using UnicodeString, and call NewString instead.
    UnicodeString tmp(value, len, UnicodeString::kInvariant);
    jobject str = env->NewString(tmp.getBuffer(), tmp.length());
    return env->NewObject(JniConstants::bigDecimalClass, gBigDecimal_init, str);
}

extern "C" jobject Java_libcore_icu_NativeDecimalFormat_parse(JNIEnv* env, jclass, jlong addr, jstring text,
        jobject position, jboolean parseBigDecimal) {

    static jmethodID gPP_getIndex = env->GetMethodID(JniConstants::parsePositionClass, "getIndex", "()I");
    static jmethodID gPP_setIndex = env->GetMethodID(JniConstants::parsePositionClass, "setIndex", "(I)V");
    static jmethodID gPP_setErrorIndex = env->GetMethodID(JniConstants::parsePositionClass, "setErrorIndex", "(I)V");

    // make sure the ParsePosition is valid. Actually icu4c would parse a number
    // correctly even if the parsePosition is set to -1, but since the RI fails
    // for that case we have to fail too
    int parsePos = env->CallIntMethod(position, gPP_getIndex, NULL);
    if (parsePos < 0 || parsePos > env->GetStringLength(text)) {
        return NULL;
    }

    Formattable res;
    ParsePosition pp(parsePos);
    ScopedJavaUnicodeString src(env, text);
    if (!src.valid()) {
      return NULL;
    }
    DecimalFormat* fmt = toDecimalFormat(addr);
    fmt->parse(src.unicodeString(), res, pp);

    if (pp.getErrorIndex() == -1) {
        env->CallVoidMethod(position, gPP_setIndex, pp.getIndex());
    } else {
        env->CallVoidMethod(position, gPP_setErrorIndex, pp.getErrorIndex());
        return NULL;
    }

    if (parseBigDecimal) {
        UErrorCode status = U_ZERO_ERROR;
        StringPiece str = res.getDecimalNumber(status);
        if (U_SUCCESS(status)) {
            int len = str.length();
            const char* data = str.data();
            if (strncmp(data, "NaN", 3) == 0 ||
                strncmp(data, "Inf", 3) == 0 ||
                strncmp(data, "-Inf", 4) == 0) {
                double resultDouble = res.getDouble(status);
                return doubleValueOf(env, resultDouble);
            }
            return newBigDecimal(env, data, len);
        }
        return NULL;
    }

    switch (res.getType()) {
        case Formattable::kDouble: return doubleValueOf(env, res.getDouble());
        case Formattable::kLong:   return longValueOf(env, res.getLong());
        case Formattable::kInt64:  return longValueOf(env, res.getInt64());
        default:                   return NULL;
    }
}

extern "C" jint Java_libcore_icu_NativeDecimalFormat_cloneImpl(JNIEnv*, jclass, jlong addr) {
    DecimalFormat* fmt = toDecimalFormat(addr);
    return reinterpret_cast<uintptr_t>(fmt->clone());
}

