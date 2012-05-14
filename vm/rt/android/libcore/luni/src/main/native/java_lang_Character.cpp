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

#define LOG_TAG "Character"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedUtfChars.h"
#include "unicode/uchar.h"
#include <math.h>
#include <stdlib.h>

extern "C" jint Java_java_lang_Character_digitImpl(JNIEnv*, jclass, jint codePoint, jint radix) {
    return u_digit(codePoint, radix);
}

extern "C" jint Java_java_lang_Character_getTypeImpl(JNIEnv*, jclass, jint codePoint) {
    return u_charType(codePoint);
}

extern "C" jbyte Java_java_lang_Character_getDirectionalityImpl(JNIEnv*, jclass, jint codePoint) {
    return u_charDirection(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isMirroredImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isMirrored(codePoint);
}

extern "C" jstring Java_java_lang_Character_getNameImpl(JNIEnv* env, jclass, jint codePoint) {
    // U_UNICODE_CHAR_NAME gives us the modern names for characters. For control characters,
    // we need U_EXTENDED_CHAR_NAME to get "NULL" rather than "BASIC LATIN 0" and so on.
    // We could just use U_EXTENDED_CHAR_NAME except that it returns strings for characters
    // that aren't unassigned but that don't have names, and those strings aren't in the form
    // Java specifies.
    bool isControl = (codePoint <= 0x1f || (codePoint >= 0x7f && codePoint <= 0x9f));
    UCharNameChoice nameType = isControl ? U_EXTENDED_CHAR_NAME : U_UNICODE_CHAR_NAME;
    UErrorCode status = U_ZERO_ERROR;
    char buf[BUFSIZ]; // TODO: is there a more sensible upper bound?
    int32_t byteCount = u_charName(codePoint, nameType, &buf[0], sizeof(buf), &status);
    return (U_FAILURE(status) || byteCount == 0) ? NULL : env->NewStringUTF(buf);
}

extern "C" jint Java_java_lang_Character_getNumericValueImpl(JNIEnv*, jclass, jint codePoint) {
    double result = u_getNumericValue(codePoint);
    if (result == U_NO_NUMERIC_VALUE) {
        return -1;
    } else if (result < 0 || floor(result + 0.5) != result) {
        return -2;
    }
    return static_cast<jint>(result);
}

extern "C" jboolean Java_java_lang_Character_isDefinedImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isdefined(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isDigitImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isdigit(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isIdentifierIgnorableImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isIDIgnorable(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isLetterImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isalpha(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isLetterOrDigitImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isalnum(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isSpaceCharImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isJavaSpaceChar(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isTitleCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_istitle(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isUnicodeIdentifierPartImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isIDPart(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isUnicodeIdentifierStartImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isIDStart(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isWhitespaceImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isWhitespace(codePoint);
}

extern "C" jint Java_java_lang_Character_toLowerCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_tolower(codePoint);
}

extern "C" jint Java_java_lang_Character_toTitleCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_totitle(codePoint);
}

extern "C" jint Java_java_lang_Character_toUpperCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_toupper(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isUpperCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_isupper(codePoint);
}

extern "C" jboolean Java_java_lang_Character_isLowerCaseImpl(JNIEnv*, jclass, jint codePoint) {
    return u_islower(codePoint);
}

extern "C" int Java_java_lang_Character_forNameImpl(JNIEnv* env, jclass, jstring javaBlockName) {
    ScopedUtfChars blockName(env, javaBlockName);
    if (blockName.c_str() == NULL) {
        return 0;
    }
    return u_getPropertyValueEnum(UCHAR_BLOCK, blockName.c_str());
}

extern "C" int Java_java_lang_Character_ofImpl(JNIEnv*, jclass, jint codePoint) {
    return ublock_getCode(codePoint);
}

