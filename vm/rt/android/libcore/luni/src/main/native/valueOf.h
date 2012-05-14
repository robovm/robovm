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

#ifndef VALUE_OF_H_included
#define VALUE_OF_H_included

#include "JNIHelp.h"

jobject booleanValueOf(JNIEnv* env, jboolean b);
jobject doubleValueOf(JNIEnv* env, jdouble d);
jobject integerValueOf(JNIEnv* env, jint i);
jobject longValueOf(JNIEnv* env, jlong l);

// Note that these aren't equivalent to the Number methods: the jobject MUST be of the exact
// type specified. This is less general but faster (and currently sufficient).
jboolean booleanValue(JNIEnv* env, jobject javaLangBoolean);
jint intValue(JNIEnv* env, jobject javaLangInteger);

#endif  // VALUE_OF_H_included
