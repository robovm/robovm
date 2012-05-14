/**
*******************************************************************************
* Copyright (C) 1996-2006, International Business Machines Corporation and    *
* others. All Rights Reserved.                                                *
*******************************************************************************
*
*
*******************************************************************************
*/
/*
 * (C) Copyright IBM Corp. 2000 - All Rights Reserved
 *  A JNI wrapper to ICU native converter Interface
 * @author: Ram Viswanadha
 */

#define LOG_TAG "NativeConverter"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedLocalRef.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedStringChars.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "cutils/log.h"
#include "toStringArray.h"
#include "unicode/ucnv.h"
#include "unicode/ucnv_cb.h"
#include "unicode/uniset.h"
#include "unicode/ustring.h"
#include "unicode/utypes.h"

#include <vector>

#include <stdlib.h>
#include <string.h>

#define NativeConverter_REPORT 0
#define NativeConverter_IGNORE 1
#define NativeConverter_REPLACE 2

#define MAX_REPLACEMENT_LENGTH 32 // equivalent to UCNV_ERROR_BUFFER_LENGTH

struct DecoderCallbackContext {
    UChar replacementChars[MAX_REPLACEMENT_LENGTH];
    size_t replacementCharCount;
    UConverterToUCallback onUnmappableInput;
    UConverterToUCallback onMalformedInput;
};

struct EncoderCallbackContext {
    char replacementBytes[MAX_REPLACEMENT_LENGTH];
    size_t replacementByteCount;
    UConverterFromUCallback onUnmappableInput;
    UConverterFromUCallback onMalformedInput;
};

struct UConverterDeleter {
    void operator()(UConverter* p) const {
        ucnv_close(p);
    }
};
typedef UniquePtr<UConverter, UConverterDeleter> UniqueUConverter;

static UConverter* toUConverter(jlong address) {
    return reinterpret_cast<UConverter*>(static_cast<uintptr_t>(address));
}

extern "C" jlong Java_libcore_icu_NativeConverter_openConverter(JNIEnv* env, jclass, jstring converterName) {
    ScopedUtfChars converterNameChars(env, converterName);
    if (converterNameChars.c_str() == NULL) {
        return 0;
    }
    UErrorCode status = U_ZERO_ERROR;
    UConverter* cnv = ucnv_open(converterNameChars.c_str(), &status);
    maybeThrowIcuException(env, status);
    return reinterpret_cast<uintptr_t>(cnv);
}

extern "C" void Java_libcore_icu_NativeConverter_closeConverter(JNIEnv*, jclass, jlong address) {
    ucnv_close(toUConverter(address));
}

extern "C" jint Java_libcore_icu_NativeConverter_encode(JNIEnv* env, jclass, jlong address,
        jcharArray source, jint sourceEnd, jbyteArray target, jint targetEnd,
        jintArray data, jboolean flush) {

    UConverter* cnv = toUConverter(address);
    if (cnv == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedCharArrayRO uSource(env, source);
    if (uSource.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedByteArrayRW uTarget(env, target);
    if (uTarget.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedIntArrayRW myData(env, data);
    if (myData.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }

    // Do the conversion.
    jint* sourceOffset = &myData[0];
    jint* targetOffset = &myData[1];
    const jchar* mySource = uSource.get() + *sourceOffset;
    const UChar* mySourceLimit= uSource.get() + sourceEnd;
    char* cTarget = reinterpret_cast<char*>(uTarget.get() + *targetOffset);
    const char* cTargetLimit = reinterpret_cast<const char*>(uTarget.get() + targetEnd);
    UErrorCode errorCode = U_ZERO_ERROR;
    ucnv_fromUnicode(cnv , &cTarget, cTargetLimit, &mySource, mySourceLimit, NULL, (UBool) flush, &errorCode);
    *sourceOffset = (mySource - uSource.get()) - *sourceOffset;
    *targetOffset = (reinterpret_cast<jbyte*>(cTarget) - uTarget.get()) - *targetOffset;

    // If there was an error, count the problematic characters.
    if (errorCode == U_ILLEGAL_CHAR_FOUND || errorCode == U_INVALID_CHAR_FOUND) {
        int8_t invalidUCharCount = 32;
        UChar invalidUChars[32];
        UErrorCode minorErrorCode = U_ZERO_ERROR;
        ucnv_getInvalidUChars(cnv, invalidUChars, &invalidUCharCount, &minorErrorCode);
        if (U_SUCCESS(minorErrorCode)) {
            myData[2] = invalidUCharCount;
        }
    }
    return errorCode;
}

extern "C" jint Java_libcore_icu_NativeConverter_decode(JNIEnv* env, jclass, jlong address,
        jbyteArray source, jint sourceEnd, jcharArray target, jint targetEnd,
        jintArray data, jboolean flush) {

    UConverter* cnv = toUConverter(address);
    if (cnv == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedByteArrayRO uSource(env, source);
    if (uSource.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedCharArrayRW uTarget(env, target);
    if (uTarget.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    ScopedIntArrayRW myData(env, data);
    if (myData.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }

    // Do the conversion.
    jint* sourceOffset = &myData[0];
    jint* targetOffset = &myData[1];
    const char* mySource = reinterpret_cast<const char*>(uSource.get() + *sourceOffset);
    const char* mySourceLimit = reinterpret_cast<const char*>(uSource.get() + sourceEnd);
    UChar* cTarget = uTarget.get() + *targetOffset;
    const UChar* cTargetLimit = uTarget.get() + targetEnd;
    UErrorCode errorCode = U_ZERO_ERROR;
    ucnv_toUnicode(cnv, &cTarget, cTargetLimit, &mySource, mySourceLimit, NULL, flush, &errorCode);
    *sourceOffset = mySource - reinterpret_cast<const char*>(uSource.get()) - *sourceOffset;
    *targetOffset = cTarget - uTarget.get() - *targetOffset;

    // If there was an error, count the problematic bytes.
    if (errorCode == U_ILLEGAL_CHAR_FOUND || errorCode == U_INVALID_CHAR_FOUND) {
        int8_t invalidByteCount = 32;
        char invalidBytes[32] = {'\0'};
        UErrorCode minorErrorCode = U_ZERO_ERROR;
        ucnv_getInvalidChars(cnv, invalidBytes, &invalidByteCount, &minorErrorCode);
        if (U_SUCCESS(minorErrorCode)) {
            myData[2] = invalidByteCount;
        }
    }

    return errorCode;
}

extern "C" void Java_libcore_icu_NativeConverter_resetByteToChar(JNIEnv*, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    if (cnv) {
        ucnv_resetToUnicode(cnv);
    }
}

extern "C" void Java_libcore_icu_NativeConverter_resetCharToByte(JNIEnv*, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    if (cnv) {
        ucnv_resetFromUnicode(cnv);
    }
}

extern "C" jint Java_libcore_icu_NativeConverter_getMaxBytesPerChar(JNIEnv*, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    return (cnv != NULL) ? ucnv_getMaxCharSize(cnv) : -1;
}

extern "C" jint Java_libcore_icu_NativeConverter_getMinBytesPerChar(JNIEnv*, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    return (cnv != NULL) ? ucnv_getMinCharSize(cnv) : -1;
}

extern "C" jfloat Java_libcore_icu_NativeConverter_getAveBytesPerChar(JNIEnv*, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    return (cnv != NULL) ? ((ucnv_getMaxCharSize(cnv) + ucnv_getMinCharSize(cnv)) / 2.0) : -1;
}

extern "C" jboolean Java_libcore_icu_NativeConverter_canEncode(JNIEnv*, jclass, jlong address, jint codeUnit) {
    UErrorCode errorCode = U_ZERO_ERROR;
    UConverter* cnv = toUConverter(address);
    if (cnv == NULL) {
        return JNI_FALSE;
    }

    UChar srcBuffer[3];
    const UChar* src = &srcBuffer[0];
    const UChar* srcLimit = (codeUnit < 0x10000) ? &src[1] : &src[2];

    char dstBuffer[5];
    char* dst = &dstBuffer[0];
    const char* dstLimit = &dstBuffer[4];

    int i = 0;
    UTF_APPEND_CHAR(&srcBuffer[0], i, 2, codeUnit);

    ucnv_fromUnicode(cnv, &dst, dstLimit, &src, srcLimit, NULL, TRUE, &errorCode);
    return U_SUCCESS(errorCode);
}

/*
 * If a charset listed in the IANA Charset Registry is supported by an implementation
 * of the Java platform then its canonical name must be the name listed in the registry.
 * Many charsets are given more than one name in the registry, in which case the registry
 * identifies one of the names as MIME-preferred. If a charset has more than one registry
 * name then its canonical name must be the MIME-preferred name and the other names in
 * the registry must be valid aliases. If a supported charset is not listed in the IANA
 * registry then its canonical name must begin with one of the strings "X-" or "x-".
 */
static jstring getJavaCanonicalName(JNIEnv* env, const char* icuCanonicalName) {
    UErrorCode status = U_ZERO_ERROR;

    // NullVM change: Fix IllegalCharsetNameException on iOS
    if (!strcmp("UTF-16,version=1", icuCanonicalName) || !strcmp("UTF-16,version=2", icuCanonicalName)) {
        return env->NewStringUTF("x-JavaUnicode");
    }

    // Check to see if this is a well-known MIME or IANA name.
    const char* cName = NULL;
    if ((cName = ucnv_getStandardName(icuCanonicalName, "MIME", &status)) != NULL) {
        return env->NewStringUTF(cName);
    } else if ((cName = ucnv_getStandardName(icuCanonicalName, "IANA", &status)) != NULL) {
        return env->NewStringUTF(cName);
    }

    // Check to see if an alias already exists with "x-" prefix, if yes then
    // make that the canonical name.
    int32_t aliasCount = ucnv_countAliases(icuCanonicalName, &status);
    for (int i = 0; i < aliasCount; ++i) {
        const char* name = ucnv_getAlias(icuCanonicalName, i, &status);
        if (name != NULL && name[0] == 'x' && name[1] == '-') {
            return env->NewStringUTF(name);
        }
    }

    // As a last resort, prepend "x-" to any alias and make that the canonical name.
    status = U_ZERO_ERROR;
    const char* name = ucnv_getStandardName(icuCanonicalName, "UTR22", &status);
    if (name == NULL && strchr(icuCanonicalName, ',') != NULL) {
        name = ucnv_getAlias(icuCanonicalName, 1, &status);
    }
    // If there is no UTR22 canonical name then just return the original name.
    if (name == NULL) {
        name = icuCanonicalName;
    }
    UniquePtr<char[]> result(new char[2 + strlen(name) + 1]);
    strcpy(&result[0], "x-");
    strcat(&result[0], name);
    return env->NewStringUTF(&result[0]);
}

extern "C" jobjectArray Java_libcore_icu_NativeConverter_getAvailableCharsetNames(JNIEnv* env, jclass) {
    int32_t num = ucnv_countAvailable();
    jobjectArray result = env->NewObjectArray(num, JniConstants::stringClass, NULL);
    if (result == NULL) {
        return NULL;
    }
    for (int i = 0; i < num; ++i) {
        const char* name = ucnv_getAvailableName(i);
        ScopedLocalRef<jstring> javaCanonicalName(env, getJavaCanonicalName(env, name));
        if (javaCanonicalName.get() == NULL) {
            return NULL;
        }
        env->SetObjectArrayElement(result, i, javaCanonicalName.get());
        if (env->ExceptionCheck()) {
            return NULL;
        }
    }
    return result;
}

static jobjectArray getAliases(JNIEnv* env, const char* icuCanonicalName) {
    // Get an upper bound on the number of aliases...
    const char* myEncName = icuCanonicalName;
    UErrorCode error = U_ZERO_ERROR;
    size_t aliasCount = ucnv_countAliases(myEncName, &error);
    if (aliasCount == 0 && myEncName[0] == 'x' && myEncName[1] == '-') {
        myEncName = myEncName + 2;
        aliasCount = ucnv_countAliases(myEncName, &error);
    }
    if (!U_SUCCESS(error)) {
        return NULL;
    }

    // Collect the aliases we want...
    std::vector<std::string> aliases;
    for (size_t i = 0; i < aliasCount; ++i) {
        const char* name = ucnv_getAlias(myEncName, i, &error);
        if (!U_SUCCESS(error)) {
            return NULL;
        }
        // TODO: why do we ignore these ones?
        if (strchr(name, '+') == 0 && strchr(name, ',') == 0) {
            aliases.push_back(name);
        }
    }
    return toStringArray(env, aliases);
}

static const char* getICUCanonicalName(const char* name) {
    UErrorCode error = U_ZERO_ERROR;
    const char* canonicalName = NULL;

    // NullVM change: Fix IllegalCharsetNameException on iOS
    if (!strcmp("x-JavaUnicode", name)) {
        return "UTF-16,version=1";
    }

    if ((canonicalName = ucnv_getCanonicalName(name, "MIME", &error)) != NULL) {
        return canonicalName;
    } else if((canonicalName = ucnv_getCanonicalName(name, "IANA", &error)) != NULL) {
        return canonicalName;
    } else if((canonicalName = ucnv_getCanonicalName(name, "", &error)) != NULL) {
        return canonicalName;
    } else if((canonicalName =  ucnv_getAlias(name, 0, &error)) != NULL) {
        /* we have some aliases in the form x-blah .. match those first */
        return canonicalName;
    } else if (strstr(name, "x-") == name) {
        /* check if the converter can be opened with the name given */
        error = U_ZERO_ERROR;
        UniqueUConverter cnv(ucnv_open(name + 2, &error));
        if (cnv.get() != NULL) {
            return name + 2;
        }
    }
    return NULL;
}

static void CHARSET_ENCODER_CALLBACK(const void* rawContext, UConverterFromUnicodeArgs* args,
        const UChar* codeUnits, int32_t length, UChar32 codePoint, UConverterCallbackReason reason,
        UErrorCode* status) {
    if (!rawContext) {
        return;
    }
    const EncoderCallbackContext* ctx = reinterpret_cast<const EncoderCallbackContext*>(rawContext);
    switch(reason) {
    case UCNV_UNASSIGNED:
        ctx->onUnmappableInput(ctx, args, codeUnits, length, codePoint, reason, status);
        return;
    case UCNV_ILLEGAL:
    case UCNV_IRREGULAR:
        ctx->onMalformedInput(ctx, args, codeUnits, length, codePoint, reason, status);
        return;
    case UCNV_CLOSE:
        delete ctx;
        return;
    default:
        *status = U_ILLEGAL_ARGUMENT_ERROR;
        return;
    }
}

static void encoderReplaceCallback(const void* rawContext,
        UConverterFromUnicodeArgs* fromArgs, const UChar*, int32_t, UChar32,
        UConverterCallbackReason, UErrorCode * err) {
    if (rawContext == NULL) {
        return;
    }
    const EncoderCallbackContext* context = reinterpret_cast<const EncoderCallbackContext*>(rawContext);
    *err = U_ZERO_ERROR;
    ucnv_cbFromUWriteBytes(fromArgs, context->replacementBytes, context->replacementByteCount, 0, err);
}

static UConverterFromUCallback getFromUCallback(int32_t mode) {
    switch(mode) {
    case NativeConverter_IGNORE: return UCNV_FROM_U_CALLBACK_SKIP;
    case NativeConverter_REPLACE: return encoderReplaceCallback;
    case NativeConverter_REPORT: return UCNV_FROM_U_CALLBACK_STOP;
    }
    abort();
}

extern "C" jint Java_libcore_icu_NativeConverter_setCallbackEncode(JNIEnv* env, jclass, jlong address,
        jint onMalformedInput, jint onUnmappableInput, jbyteArray javaReplacement) {
    UConverter* cnv = toUConverter(address);
    if (!cnv) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }

    UConverterFromUCallback oldCallback = NULL;
    const void* oldCallbackContext = NULL;
    ucnv_getFromUCallBack(cnv, &oldCallback, const_cast<const void**>(&oldCallbackContext));

    EncoderCallbackContext* callbackContext = const_cast<EncoderCallbackContext*>(
            reinterpret_cast<const EncoderCallbackContext*>(oldCallbackContext));
    if (callbackContext == NULL) {
        callbackContext = new EncoderCallbackContext;
    }

    callbackContext->onMalformedInput = getFromUCallback(onMalformedInput);
    callbackContext->onUnmappableInput = getFromUCallback(onUnmappableInput);

    ScopedByteArrayRO replacementBytes(env, javaReplacement);
    if (replacementBytes.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    memcpy(callbackContext->replacementBytes, replacementBytes.get(), replacementBytes.size());
    callbackContext->replacementByteCount = replacementBytes.size();

    UErrorCode errorCode = U_ZERO_ERROR;
    ucnv_setFromUCallBack(cnv, CHARSET_ENCODER_CALLBACK, callbackContext, NULL, NULL, &errorCode);
    return errorCode;
}

static void decoderIgnoreCallback(const void*, UConverterToUnicodeArgs*, const char*, int32_t, UConverterCallbackReason, UErrorCode* err) {
    // The icu4c UCNV_FROM_U_CALLBACK_SKIP callback requires that the context is NULL, which is
    // never true for us.
    *err = U_ZERO_ERROR;
}

static void decoderReplaceCallback(const void* rawContext,
        UConverterToUnicodeArgs* toArgs, const char*, int32_t, UConverterCallbackReason,
        UErrorCode* err) {
    if (!rawContext) {
        return;
    }
    const DecoderCallbackContext* context = reinterpret_cast<const DecoderCallbackContext*>(rawContext);
    *err = U_ZERO_ERROR;
    ucnv_cbToUWriteUChars(toArgs,context->replacementChars, context->replacementCharCount, 0, err);
}

static UConverterToUCallback getToUCallback(int32_t mode) {
    switch (mode) {
    case NativeConverter_IGNORE: return decoderIgnoreCallback;
    case NativeConverter_REPLACE: return decoderReplaceCallback;
    case NativeConverter_REPORT: return UCNV_TO_U_CALLBACK_STOP;
    }
    abort();
}

static void CHARSET_DECODER_CALLBACK(const void* rawContext, UConverterToUnicodeArgs* args,
        const char* codeUnits, int32_t length,
        UConverterCallbackReason reason, UErrorCode* status) {
    if (!rawContext) {
        return;
    }
    const DecoderCallbackContext* ctx = reinterpret_cast<const DecoderCallbackContext*>(rawContext);
    switch(reason) {
    case UCNV_UNASSIGNED:
        ctx->onUnmappableInput(ctx, args, codeUnits, length, reason, status);
        return;
    case UCNV_ILLEGAL:
    case UCNV_IRREGULAR:
        ctx->onMalformedInput(ctx, args, codeUnits, length, reason, status);
        return;
    case UCNV_CLOSE:
        delete ctx;
        return;
    default:
        *status = U_ILLEGAL_ARGUMENT_ERROR;
        return;
    }
}

extern "C" jint Java_libcore_icu_NativeConverter_setCallbackDecode(JNIEnv* env, jclass, jlong address,
        jint onMalformedInput, jint onUnmappableInput, jstring javaReplacement) {
    UConverter* cnv = toUConverter(address);
    if (cnv == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }

    UConverterToUCallback oldCallback;
    const void* oldCallbackContext;
    ucnv_getToUCallBack(cnv, &oldCallback, &oldCallbackContext);

    DecoderCallbackContext* callbackContext = const_cast<DecoderCallbackContext*>(
            reinterpret_cast<const DecoderCallbackContext*>(oldCallbackContext));
    if (callbackContext == NULL) {
        callbackContext = new DecoderCallbackContext;
    }

    callbackContext->onMalformedInput = getToUCallback(onMalformedInput);
    callbackContext->onUnmappableInput = getToUCallback(onUnmappableInput);

    ScopedStringChars replacement(env, javaReplacement);
    if (replacement.get() == NULL) {
        return U_ILLEGAL_ARGUMENT_ERROR;
    }
    u_strncpy(callbackContext->replacementChars, replacement.get(), replacement.size());
    callbackContext->replacementCharCount = replacement.size();

    UErrorCode errorCode = U_ZERO_ERROR;
    ucnv_setToUCallBack(cnv, CHARSET_DECODER_CALLBACK, callbackContext, NULL, NULL, &errorCode);
    return errorCode;
}

extern "C" jfloat Java_libcore_icu_NativeConverter_getAveCharsPerByte(JNIEnv* env, jclass, jlong handle) {
    return (1 / (jfloat) Java_libcore_icu_NativeConverter_getMaxBytesPerChar(env, NULL, handle));
}

extern "C" jbyteArray Java_libcore_icu_NativeConverter_getSubstitutionBytes(JNIEnv* env, jclass, jlong address) {
    UConverter* cnv = toUConverter(address);
    if (cnv == NULL) {
        return NULL;
    }
    UErrorCode status = U_ZERO_ERROR;
    char replacementBytes[MAX_REPLACEMENT_LENGTH];
    int8_t len = sizeof(replacementBytes);
    ucnv_getSubstChars(cnv, replacementBytes, &len, &status);
    if (!U_SUCCESS(status)) {
        return env->NewByteArray(0);
    }
    jbyteArray result = env->NewByteArray(len);
    if (result == NULL) {
        return NULL;
    }
    env->SetByteArrayRegion(result, 0, len, reinterpret_cast<jbyte*>(replacementBytes));
    return result;
}

extern "C" jboolean Java_libcore_icu_NativeConverter_contains(JNIEnv* env, jclass, jstring name1, jstring name2) {
    ScopedUtfChars name1Chars(env, name1);
    if (name1Chars.c_str() == NULL) {
        return JNI_FALSE;
    }
    ScopedUtfChars name2Chars(env, name2);
    if (name2Chars.c_str() == NULL) {
        return JNI_FALSE;
    }

    UErrorCode errorCode = U_ZERO_ERROR;
    UniqueUConverter converter1(ucnv_open(name1Chars.c_str(), &errorCode));
    UnicodeSet set1;
    ucnv_getUnicodeSet(converter1.get(), set1.toUSet(), UCNV_ROUNDTRIP_SET, &errorCode);

    UniqueUConverter converter2(ucnv_open(name2Chars.c_str(), &errorCode));
    UnicodeSet set2;
    ucnv_getUnicodeSet(converter2.get(), set2.toUSet(), UCNV_ROUNDTRIP_SET, &errorCode);

    return U_SUCCESS(errorCode) && set1.containsAll(set2);
}

extern "C" jobject Java_libcore_icu_NativeConverter_charsetForName(JNIEnv* env, jclass, jstring charsetName) {
    ScopedUtfChars charsetNameChars(env, charsetName);
    if (charsetNameChars.c_str() == NULL) {
        return NULL;
    }
    // Get ICU's canonical name for this charset.
    const char* icuCanonicalName = getICUCanonicalName(charsetNameChars.c_str());
    if (icuCanonicalName == NULL) {
        return NULL;
    }
    // Get Java's canonical name for this charset.
    jstring javaCanonicalName = getJavaCanonicalName(env, icuCanonicalName);
    if (env->ExceptionOccurred()) {
        return NULL;
    }

    // Check that this charset is supported.
    // ICU doesn't offer any "isSupported", so we just open and immediately close.
    // We ignore the UErrorCode because ucnv_open returning NULL is all the information we need.
    UErrorCode dummy = U_ZERO_ERROR;
    UniqueUConverter cnv(ucnv_open(icuCanonicalName, &dummy));
    if (cnv.get() == NULL) {
        return NULL;
    }
    cnv.reset();

    // Get the aliases for this charset.
    jobjectArray aliases = getAliases(env, icuCanonicalName);
    if (env->ExceptionOccurred()) {
        return NULL;
    }

    // Construct the CharsetICU object.
    jmethodID charsetConstructor = env->GetMethodID(JniConstants::charsetICUClass, "<init>",
            "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V");
    if (env->ExceptionOccurred()) {
        return NULL;
    }
    return env->NewObject(JniConstants::charsetICUClass, charsetConstructor,
            javaCanonicalName, env->NewStringUTF(icuCanonicalName), aliases);
}

