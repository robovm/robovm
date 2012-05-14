/*
 * Copyright (C) 2010 The Android Open Source Project
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

#define LOG_TAG "NativeIDN"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedStringChars.h"
#include "unicode/uidna.h"

static bool isLabelSeparator(const UChar ch) {
    switch (ch) {
    case 0x3002: // ideographic full stop
    case 0xff0e: // fullwidth full stop
    case 0xff61: // halfwidth ideographic full stop
        return true;
    default:
        return false;
    }
}

extern "C" jstring Java_libcore_icu_NativeIDN_convertImpl(JNIEnv* env, jclass, jstring javaSrc, jint flags, jboolean toAscii) {
    ScopedStringChars src(env, javaSrc);
    if (src.get() == NULL) {
        return NULL;
    }
    UChar dst[256];
    UErrorCode status = U_ZERO_ERROR;
    size_t resultLength = toAscii
        ? uidna_IDNToASCII(src.get(), src.size(), &dst[0], sizeof(dst), flags, NULL, &status)
        : uidna_IDNToUnicode(src.get(), src.size(), &dst[0], sizeof(dst), flags, NULL, &status);
    if (U_FAILURE(status)) {
        jniThrowException(env, "java/lang/IllegalArgumentException", u_errorName(status));
        return NULL;
    }
    if (!toAscii) {
        // ICU only translates separators to ASCII for toASCII.
        // Java expects the translation for toUnicode too.
        // We may as well do this here, while the string is still mutable.
        for (size_t i = 0; i < resultLength; ++i) {
            if (isLabelSeparator(dst[i])) {
                dst[i] = '.';
            }
        }
    }
    return env->NewString(&dst[0], resultLength);
}

