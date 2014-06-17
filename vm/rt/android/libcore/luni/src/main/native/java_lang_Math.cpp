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

#define LOG_TAG "Math"

#include "jni.h"
#include "JNIHelp.h"
#include "JniConstants.h"

#include <stdlib.h>
#include <math.h>

extern "C" jdouble Java_java_lang_Math_sin(JNIEnv*, jclass, jdouble a) {
    return sin(a);
}

extern "C" jdouble Java_java_lang_Math_cos(JNIEnv*, jclass, jdouble a) {
    return cos(a);
}

extern "C" jdouble Java_java_lang_Math_tan(JNIEnv*, jclass, jdouble a) {
    return tan(a);
}

extern "C" jdouble Java_java_lang_Math_asin(JNIEnv*, jclass, jdouble a) {
    return asin(a);
}

extern "C" jdouble Java_java_lang_Math_acos(JNIEnv*, jclass, jdouble a) {
    return acos(a);
}

extern "C" jdouble Java_java_lang_Math_atan(JNIEnv*, jclass, jdouble a) {
    return atan(a);
}

extern "C" jdouble Java_java_lang_Math_exp(JNIEnv*, jclass, jdouble a) {
    return exp(a);
}

extern "C" jdouble Java_java_lang_Math_log(JNIEnv*, jclass, jdouble a) {
    return log(a);
}

extern "C" jdouble Java_java_lang_Math_IEEEremainder(JNIEnv*, jclass, jdouble a, jdouble b) {
    return remainder(a, b);
}

extern "C" jdouble Java_java_lang_Math_floor(JNIEnv*, jclass, jdouble a) {
    return floor(a);
}

extern "C" jdouble Java_java_lang_Math_ceil(JNIEnv*, jclass, jdouble a) {
    return ceil(a);
}

extern "C" jdouble Java_java_lang_Math_rint(JNIEnv*, jclass, jdouble a) {
    return rint(a);
}

extern "C" jdouble Java_java_lang_Math_atan2(JNIEnv*, jclass, jdouble a, jdouble b) {
    return atan2(a, b);
}

extern "C" jdouble Java_java_lang_Math_pow(JNIEnv*, jclass, jdouble a, jdouble b) {
    return pow(a, b);
}

extern "C" jdouble Java_java_lang_Math_sinh(JNIEnv*, jclass, jdouble a) {
    return sinh(a);
}

extern "C" jdouble Java_java_lang_Math_tanh(JNIEnv*, jclass, jdouble a) {
    return tanh(a);
}

extern "C" jdouble Java_java_lang_Math_cosh(JNIEnv*, jclass, jdouble a) {
    return cosh(a);
}

extern "C" jdouble Java_java_lang_Math_log10(JNIEnv*, jclass, jdouble a) {
    return log10(a);
}

extern "C" jdouble Java_java_lang_Math_cbrt(JNIEnv*, jclass, jdouble a) {
    return cbrt(a);
}

extern "C" jdouble Java_java_lang_Math_sqrt(JNIEnv*, jclass, jdouble a) {
    return sqrt(a);
}

extern "C" jdouble Java_java_lang_Math_expm1(JNIEnv*, jclass, jdouble a) {
    return expm1(a);
}

extern "C" jdouble Java_java_lang_Math_hypot(JNIEnv*, jclass, jdouble a, jdouble b) {
    return hypot(a, b);
}

extern "C" jdouble Java_java_lang_Math_log1p(JNIEnv*, jclass, jdouble a) {
// RoboVM note: log1p(-0.0) on Darwin x86 returns +0.0 even though the Apple docs say -0.0.
#if defined(__APPLE__) && defined(__i386__)
    if (*((jlong*) &a) == 0x8000000000000000) return -0.0;
#endif
    return log1p(a);
}

extern "C" jdouble Java_java_lang_Math_nextafter(JNIEnv*, jclass, jdouble a, jdouble b) {
    return nextafter(a, b);
}

