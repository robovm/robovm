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

#include "JniException.h"
#include "JNIHelp.h"

bool maybeThrowIcuException(JNIEnv* env, UErrorCode error) {
    if (U_SUCCESS(error)) {
        return false;
    }
    const char* message = u_errorName(error);
    switch (error) {
    case U_ILLEGAL_ARGUMENT_ERROR:
        return jniThrowException(env, "java/lang/IllegalArgumentException", message);
    case U_INDEX_OUTOFBOUNDS_ERROR:
    case U_BUFFER_OVERFLOW_ERROR:
        return jniThrowException(env, "java/lang/ArrayIndexOutOfBoundsException", message);
    case U_UNSUPPORTED_ERROR:
        return jniThrowException(env, "java/lang/UnsupportedOperationException", message);
    default:
        return jniThrowRuntimeException(env, message);
    }
}

void jniThrowExceptionWithErrno(JNIEnv* env, const char* exceptionClassName, int error) {
    char buf[BUFSIZ];
    jniThrowException(env, exceptionClassName, jniStrError(error, buf, sizeof(buf)));
}

void jniThrowOutOfMemoryError(JNIEnv* env, const char* message) {
    jniThrowException(env, "java/lang/OutOfMemoryError", message);
}

void jniThrowSocketException(JNIEnv* env, int error) {
    jniThrowExceptionWithErrno(env, "java/net/SocketException", error);
}
