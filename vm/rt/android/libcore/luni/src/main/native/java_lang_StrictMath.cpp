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

#define LOG_TAG "StrictMath"

#include "../../external/fdlibm/fdlibm.h"

#include "jni.h"
#include "JNIHelp.h"
#include "JniConstants.h"

extern "C" jdouble Java_java_lang_StrictMath_sin(JNIEnv*, jclass, jdouble a) {
    return ieee_sin(a);
}

extern "C" jdouble Java_java_lang_StrictMath_cos(JNIEnv*, jclass, jdouble a) {
    return ieee_cos(a);
}

extern "C" jdouble Java_java_lang_StrictMath_tan(JNIEnv*, jclass, jdouble a) {
    return ieee_tan(a);
}

extern "C" jdouble Java_java_lang_StrictMath_asin(JNIEnv*, jclass, jdouble a) {
    return ieee_asin(a);
}

extern "C" jdouble Java_java_lang_StrictMath_acos(JNIEnv*, jclass, jdouble a) {
    return ieee_acos(a);
}

extern "C" jdouble Java_java_lang_StrictMath_atan(JNIEnv*, jclass, jdouble a) {
    return ieee_atan(a);
}

extern "C" jdouble Java_java_lang_StrictMath_exp(JNIEnv*, jclass, jdouble a) {
    return ieee_exp(a);
}

extern "C" jdouble Java_java_lang_StrictMath_log(JNIEnv*, jclass, jdouble a) {
    return ieee_log(a);
}

extern "C" jdouble Java_java_lang_StrictMath_sqrt(JNIEnv*, jclass, jdouble a) {
    return ieee_sqrt(a);
}

extern "C" jdouble Java_java_lang_StrictMath_IEEEremainder(JNIEnv*, jclass, jdouble a, jdouble b) {
    return ieee_remainder(a, b);
}

extern "C" jdouble Java_java_lang_StrictMath_floor(JNIEnv*, jclass, jdouble a) {
    return ieee_floor(a);
}

extern "C" jdouble Java_java_lang_StrictMath_ceil(JNIEnv*, jclass, jdouble a) {
    return ieee_ceil(a);
}

extern "C" jdouble Java_java_lang_StrictMath_rint(JNIEnv*, jclass, jdouble a) {
    return ieee_rint(a);
}

extern "C" jdouble Java_java_lang_StrictMath_atan2(JNIEnv*, jclass, jdouble a, jdouble b) {
    return ieee_atan2(a, b);
}

extern "C" jdouble Java_java_lang_StrictMath_pow(JNIEnv*, jclass, jdouble a, jdouble b) {
    return ieee_pow(a,b);
}

extern "C" jdouble Java_java_lang_StrictMath_sinh(JNIEnv*, jclass, jdouble a) {
    return ieee_sinh(a);
}

extern "C" jdouble Java_java_lang_StrictMath_tanh(JNIEnv*, jclass, jdouble a) {
    return ieee_tanh(a);
}

extern "C" jdouble Java_java_lang_StrictMath_cosh(JNIEnv*, jclass, jdouble a) {
    return ieee_cosh(a);
}

extern "C" jdouble Java_java_lang_StrictMath_log10(JNIEnv*, jclass, jdouble a) {
    return ieee_log10(a);
}

extern "C" jdouble Java_java_lang_StrictMath_cbrt(JNIEnv*, jclass, jdouble a) {
    return ieee_cbrt(a);
}

extern "C" jdouble Java_java_lang_StrictMath_expm1(JNIEnv*, jclass, jdouble a) {
    return ieee_expm1(a);
}

extern "C" jdouble Java_java_lang_StrictMath_hypot(JNIEnv*, jclass, jdouble a, jdouble b) {
    return ieee_hypot(a, b);
}

extern "C" jdouble Java_java_lang_StrictMath_log1p(JNIEnv*, jclass, jdouble a) {
    return ieee_log1p(a);
}

extern "C" jdouble Java_java_lang_StrictMath_nextafter(JNIEnv*, jclass, jdouble a, jdouble b) {
    return ieee_nextafter(a, b);
}

