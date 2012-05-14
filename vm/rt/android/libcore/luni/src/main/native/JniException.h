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

#ifndef JNI_EXCEPTION_H_included
#define JNI_EXCEPTION_H_included

#include "jni.h"
#include "unicode/utypes.h" // For UErrorCode.

void jniThrowExceptionWithErrno(JNIEnv* env, const char* exceptionClassName, int error);

void jniThrowOutOfMemoryError(JNIEnv* env, const char* message);
void jniThrowSocketException(JNIEnv* env, int error);

bool maybeThrowIcuException(JNIEnv* env, UErrorCode error);

#endif  // JNI_EXCEPTION_H_included
