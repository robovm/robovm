/*
 * Copyright (C) 2013 The Android Open Source Project
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

#ifndef ICU_UTILITIES_H_included
#define ICU_UTILITIES_H_included

#define U_HAVE_STD_STRING 1 // For UnicodeString::toUTF8String(std::string&).

#include "jni.h"
#include "ustrenum.h" // For UStringEnumeration.
#include "unicode/utypes.h" // For UErrorCode.
#include "unicode/locid.h" // For Locale.

extern Locale getLocale(JNIEnv* env, jstring localeName);
extern jobjectArray fromStringEnumeration(JNIEnv* env, UErrorCode& status, const char* provider, StringEnumeration*);
bool maybeThrowIcuException(JNIEnv* env, const char* function, UErrorCode error);

#endif  // ICU_UTILITIES_H_included
