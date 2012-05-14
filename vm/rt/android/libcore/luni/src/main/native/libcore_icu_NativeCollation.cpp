/**
*******************************************************************************
* Copyright (C) 1996-2005, International Business Machines Corporation and    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*
*******************************************************************************
*/

#define LOG_TAG "NativeCollation"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedStringChars.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "ucol_imp.h"
#include "unicode/ucol.h"
#include "unicode/ucoleitr.h"

static UCollator* toCollator(jint address) {
    return reinterpret_cast<UCollator*>(static_cast<uintptr_t>(address));
}

static UCollationElements* toCollationElements(jint address) {
    return reinterpret_cast<UCollationElements*>(static_cast<uintptr_t>(address));
}

extern "C" void Java_libcore_icu_NativeCollation_closeCollator(JNIEnv*, jclass, jint address) {
    ucol_close(toCollator(address));
}

extern "C" void Java_libcore_icu_NativeCollation_closeElements(JNIEnv*, jclass, jint address) {
    ucol_closeElements(toCollationElements(address));
}

extern "C" jint Java_libcore_icu_NativeCollation_compare(JNIEnv* env, jclass, jint address, jstring javaLhs, jstring javaRhs) {
    ScopedStringChars lhs(env, javaLhs);
    if (lhs.get() == NULL) {
        return 0;
    }
    ScopedStringChars rhs(env, javaRhs);
    if (rhs.get() == NULL) {
        return 0;
    }
    return ucol_strcoll(toCollator(address), lhs.get(), lhs.size(), rhs.get(), rhs.size());
}

extern "C" jint Java_libcore_icu_NativeCollation_getAttribute(JNIEnv* env, jclass, jint address, jint type) {
    UErrorCode status = U_ZERO_ERROR;
    jint result = ucol_getAttribute(toCollator(address), (UColAttribute) type, &status);
    maybeThrowIcuException(env, status);
    return result;
}

extern "C" jint Java_libcore_icu_NativeCollation_getCollationElementIterator(JNIEnv* env, jclass, jint address, jstring javaSource) {
    ScopedStringChars source(env, javaSource);
    if (source.get() == NULL) {
        return -1;
    }
    UErrorCode status = U_ZERO_ERROR;
    UCollationElements* result = ucol_openElements(toCollator(address), source.get(), source.size(), &status);
    maybeThrowIcuException(env, status);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(result));
}

extern "C" jint Java_libcore_icu_NativeCollation_getMaxExpansion(JNIEnv*, jclass, jint address, jint order) {
    return ucol_getMaxExpansion(toCollationElements(address), order);
}

extern "C" jint Java_libcore_icu_NativeCollation_getOffset(JNIEnv*, jclass, jint address) {
    return ucol_getOffset(toCollationElements(address));
}

extern "C" jstring Java_libcore_icu_NativeCollation_getRules(JNIEnv* env, jclass, jint address) {
    int32_t length = 0;
    const UChar* rules = ucol_getRules(toCollator(address), &length);
    return env->NewString(rules, length);
}

extern "C" jbyteArray Java_libcore_icu_NativeCollation_getSortKey(JNIEnv* env, jclass, jint address, jstring javaSource) {
    ScopedStringChars source(env, javaSource);
    if (source.get() == NULL) {
        return NULL;
    }
    const UCollator* collator  = toCollator(address);
    uint8_t byteArray[UCOL_MAX_BUFFER * 2];
    UniquePtr<uint8_t[]> largerByteArray;
    uint8_t* usedByteArray = byteArray;
    size_t byteArraySize = ucol_getSortKey(collator, source.get(), source.size(), usedByteArray, sizeof(byteArray) - 1);
    if (byteArraySize > sizeof(byteArray) - 1) {
        // didn't fit, try again with a larger buffer.
        largerByteArray.reset(new uint8_t[byteArraySize + 1]);
        usedByteArray = largerByteArray.get();
        byteArraySize = ucol_getSortKey(collator, source.get(), source.size(), usedByteArray, byteArraySize);
    }
    if (byteArraySize == 0) {
        return NULL;
    }
    jbyteArray result = env->NewByteArray(byteArraySize);
    env->SetByteArrayRegion(result, 0, byteArraySize, reinterpret_cast<jbyte*>(usedByteArray));
    return result;
}

extern "C" jint Java_libcore_icu_NativeCollation_next(JNIEnv* env, jclass, jint address) {
    UErrorCode status = U_ZERO_ERROR;
    jint result = ucol_next(toCollationElements(address), &status);
    maybeThrowIcuException(env, status);
    return result;
}

extern "C" jint Java_libcore_icu_NativeCollation_openCollator(JNIEnv* env, jclass, jstring localeName) {
    ScopedUtfChars localeChars(env, localeName);
    if (localeChars.c_str() == NULL) {
        return 0;
    }
    UErrorCode status = U_ZERO_ERROR;
    UCollator* c = ucol_open(localeChars.c_str(), &status);
    maybeThrowIcuException(env, status);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(c));
}

extern "C" jint Java_libcore_icu_NativeCollation_openCollatorFromRules(JNIEnv* env, jclass, jstring javaRules, jint mode, jint strength) {
    ScopedStringChars rules(env, javaRules);
    if (rules.get() == NULL) {
        return -1;
    }
    UErrorCode status = U_ZERO_ERROR;
    UCollator* c = ucol_openRules(rules.get(), rules.size(),
            UColAttributeValue(mode), UCollationStrength(strength), NULL, &status);
    maybeThrowIcuException(env, status);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(c));
}

extern "C" jint Java_libcore_icu_NativeCollation_previous(JNIEnv* env, jclass, jint address) {
    UErrorCode status = U_ZERO_ERROR;
    jint result = ucol_previous(toCollationElements(address), &status);
    maybeThrowIcuException(env, status);
    return result;
}

extern "C" void Java_libcore_icu_NativeCollation_reset(JNIEnv*, jclass, jint address) {
    ucol_reset(toCollationElements(address));
}

extern "C" jint Java_libcore_icu_NativeCollation_safeClone(JNIEnv* env, jclass, jint address) {
    UErrorCode status = U_ZERO_ERROR;
    jint bufferSize = U_COL_SAFECLONE_BUFFERSIZE;
    UCollator* c = ucol_safeClone(toCollator(address), NULL, &bufferSize, &status);
    maybeThrowIcuException(env, status);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(c));
}

extern "C" void Java_libcore_icu_NativeCollation_setAttribute(JNIEnv* env, jclass, jint address, jint type, jint value) {
    UErrorCode status = U_ZERO_ERROR;
    ucol_setAttribute(toCollator(address), (UColAttribute)type, (UColAttributeValue)value, &status);
    maybeThrowIcuException(env, status);
}

extern "C" void Java_libcore_icu_NativeCollation_setOffset(JNIEnv* env, jclass, jint address, jint offset) {
    UErrorCode status = U_ZERO_ERROR;
    ucol_setOffset(toCollationElements(address), offset, &status);
    maybeThrowIcuException(env, status);
}

extern "C" void Java_libcore_icu_NativeCollation_setText(JNIEnv* env, jclass, jint address, jstring javaSource) {
    ScopedStringChars source(env, javaSource);
    if (source.get() == NULL) {
        return;
    }
    UErrorCode status = U_ZERO_ERROR;
    ucol_setText(toCollationElements(address), source.get(), source.size(), &status);
    maybeThrowIcuException(env, status);
}

