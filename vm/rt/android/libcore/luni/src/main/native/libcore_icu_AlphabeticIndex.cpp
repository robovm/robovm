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

#define LOG_TAG "AlphabeticIndex"

#include "IcuUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedJavaUnicodeString.h"
#include "unicode/alphaindex.h"
#include "unicode/uniset.h"

static AlphabeticIndex* fromPeer(jlong peer) {
  return reinterpret_cast<AlphabeticIndex*>(static_cast<uintptr_t>(peer));
}

extern "C" jlong Java_libcore_icu_AlphabeticIndex_create(JNIEnv* env, jclass, jstring javaLocale) {
  UErrorCode status = U_ZERO_ERROR;
  AlphabeticIndex* ai = new AlphabeticIndex(getLocale(env, javaLocale), status);
  if (maybeThrowIcuException(env, "AlphabeticIndex", status)) {
    return 0;
  }
  return reinterpret_cast<uintptr_t>(ai);
}

extern "C" void Java_libcore_icu_AlphabeticIndex_destroy(JNIEnv*, jclass, jlong peer) {
  delete fromPeer(peer);
}

extern "C" jint Java_libcore_icu_AlphabeticIndex_getMaxLabelCount(JNIEnv*, jclass, jlong peer) {
  AlphabeticIndex* ai = fromPeer(peer);
  return ai->getMaxLabelCount();
}

extern "C" void Java_libcore_icu_AlphabeticIndex_setMaxLabelCount(JNIEnv* env, jclass, jlong peer, jint count) {
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  ai->setMaxLabelCount(count, status);
  maybeThrowIcuException(env, "AlphabeticIndex::setMaxLabelCount", status);
}

extern "C" void Java_libcore_icu_AlphabeticIndex_addLabels(JNIEnv* env, jclass, jlong peer, jstring javaLocale) {
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  ai->addLabels(getLocale(env, javaLocale), status);
  maybeThrowIcuException(env, "AlphabeticIndex::addLabels", status);
}

extern "C" void Java_libcore_icu_AlphabeticIndex_addLabelRange(JNIEnv* env, jclass, jlong peer,
                                          jint codePointStart, jint codePointEnd) {
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  ai->addLabels(UnicodeSet(codePointStart, codePointEnd), status);
  maybeThrowIcuException(env, "AlphabeticIndex::addLabels", status);
}

extern "C" jint Java_libcore_icu_AlphabeticIndex_getBucketCount(JNIEnv* env, jclass, jlong peer) {
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  jint result = ai->getBucketCount(status);
  if (maybeThrowIcuException(env, "AlphabeticIndex::getBucketCount", status)) {
    return -1;
  }
  return result;
}

extern "C" jint Java_libcore_icu_AlphabeticIndex_getBucketIndex(JNIEnv* env, jclass, jlong peer, jstring javaString) {
  AlphabeticIndex* ai = fromPeer(peer);
  ScopedJavaUnicodeString string(env, javaString);
  if (!string.valid()) {
    return -1;
  }
  UErrorCode status = U_ZERO_ERROR;
  jint result = ai->getBucketIndex(string.unicodeString(), status);
  if (maybeThrowIcuException(env, "AlphabeticIndex::getBucketIndex", status)) {
    return -1;
  }
  return result;
}

extern "C" jstring Java_libcore_icu_AlphabeticIndex_getBucketLabel(JNIEnv* env, jclass, jlong peer, jint index) {
  if (index < 0) {
    jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException", "Invalid index: %d", index);
    return NULL;
  }

  // Iterate to the nth bucket.
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  ai->resetBucketIterator(status);
  if (maybeThrowIcuException(env, "AlphabeticIndex::resetBucketIterator", status)) {
    return NULL;
  }
  for (jint i = 0; i <= index; ++i) {
    if (!ai->nextBucket(status)) {
      jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException", "Invalid index: %d", index);
      return NULL;
    }
    if (maybeThrowIcuException(env, "AlphabeticIndex::nextBucket", status)) {
      return NULL;
    }
  }

  // Return "" for the underflow/inflow/overflow buckets.
  if (ai->getBucketLabelType() != U_ALPHAINDEX_NORMAL) {
    return env->NewStringUTF("");
  }

  const UnicodeString& label(ai->getBucketLabel());
  return env->NewString(label.getBuffer(), label.length());
}

extern "C" jlong Java_libcore_icu_AlphabeticIndex_buildImmutableIndex(JNIEnv* env, jclass, jlong peer) {
  AlphabeticIndex* ai = fromPeer(peer);
  UErrorCode status = U_ZERO_ERROR;
  AlphabeticIndex::ImmutableIndex* ii = ai->buildImmutableIndex(status);
  if (maybeThrowIcuException(env, "AlphabeticIndex::buildImmutableIndex", status)) {
    return 0;
  }
  return reinterpret_cast<uintptr_t>(ii);
}

static AlphabeticIndex::ImmutableIndex* immutableIndexFromPeer(jlong peer) {
  return reinterpret_cast<AlphabeticIndex::ImmutableIndex*>(static_cast<uintptr_t>(peer));
}

extern "C" jint Java_libcore_icu_AlphabeticIndex_00024ImmutableIndex_getBucketCount(JNIEnv*, jclass, jlong peer) {
  AlphabeticIndex::ImmutableIndex* ii = immutableIndexFromPeer(peer);
  return ii->getBucketCount();
}

extern "C" jint Java_libcore_icu_AlphabeticIndex_00024ImmutableIndex_getBucketIndex(JNIEnv* env, jclass, jlong peer, jstring javaString) {
  AlphabeticIndex::ImmutableIndex* ii = immutableIndexFromPeer(peer);
  ScopedJavaUnicodeString string(env, javaString);
  if (!string.valid()) {
    return -1;
  }
  UErrorCode status = U_ZERO_ERROR;
  jint result = ii->getBucketIndex(string.unicodeString(), status);
  if (maybeThrowIcuException(env, "AlphabeticIndex::ImmutableIndex::getBucketIndex", status)) {
    return -1;
  }
  return result;
}

extern "C" jstring Java_libcore_icu_AlphabeticIndex_00024ImmutableIndex_getBucketLabel(JNIEnv* env, jclass, jlong peer, jint index) {
  AlphabeticIndex::ImmutableIndex* ii = immutableIndexFromPeer(peer);
  const AlphabeticIndex::Bucket* bucket = ii->getBucket(index);
  if (bucket == NULL) {
    jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException", "Invalid index: %d", index);
    return NULL;
  }

  // Return "" for the underflow/inflow/overflow buckets.
  if (bucket->getLabelType() != U_ALPHAINDEX_NORMAL) {
    return env->NewStringUTF("");
  }

  const UnicodeString& label(bucket->getLabel());
  return env->NewString(label.getBuffer(), label.length());
}
