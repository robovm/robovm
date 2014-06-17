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

#define LOG_TAG "Transliterator"

#include "IcuUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedJavaUnicodeString.h"
#include "unicode/translit.h"

static Transliterator* fromPeer(jlong peer) {
  return reinterpret_cast<Transliterator*>(static_cast<uintptr_t>(peer));
}

extern "C" jlong Java_libcore_icu_Transliterator_create(JNIEnv* env, jclass, jstring javaId) {
  ScopedJavaUnicodeString id(env, javaId);
  if (!id.valid()) {
    return 0;
  }
  UErrorCode status = U_ZERO_ERROR;
  Transliterator* t = Transliterator::createInstance(id.unicodeString(), UTRANS_FORWARD, status);
  if (maybeThrowIcuException(env, "Transliterator::createInstance", status)) {
    return 0;
  }
  return reinterpret_cast<uintptr_t>(t);
}

extern "C" void Java_libcore_icu_Transliterator_destroy(JNIEnv*, jclass, jlong peer) {
  delete fromPeer(peer);
}

extern "C" jobjectArray Java_libcore_icu_Transliterator_getAvailableIDs(JNIEnv* env, jclass) {
  UErrorCode status = U_ZERO_ERROR;
  StringEnumeration* e = Transliterator::getAvailableIDs(status);
  return fromStringEnumeration(env, status, "Transliterator::getAvailableIDs", e);
}

extern "C" jstring Java_libcore_icu_Transliterator_transliterate(JNIEnv* env, jclass, jlong peer, jstring javaString) {
  Transliterator* t = fromPeer(peer);
  ScopedJavaUnicodeString string(env, javaString);
  if (!string.valid()) {
    return NULL;
  }

  UnicodeString& s(string.unicodeString());
  t->transliterate(s);
  return env->NewString(s.getBuffer(), s.length());
}
