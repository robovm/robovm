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

#ifndef SCOPED_JAVA_UNICODE_STRING_H_included
#define SCOPED_JAVA_UNICODE_STRING_H_included

#include "JNIHelp.h"
#include "unicode/unistr.h"

// A smart pointer that provides access to an ICU UnicodeString given a JNI
// jstring. We give ICU a direct pointer to the characters on the Java heap.
// It's clever enough to copy-on-write if necessary.
class ScopedJavaUnicodeString {
 public:
  ScopedJavaUnicodeString(JNIEnv* env, jstring s) : mEnv(env), mString(s) {
    if (s == NULL) {
      jniThrowNullPointerException(mEnv, NULL);
    } else {
      mChars = env->GetStringChars(mString, NULL);
      const int32_t charCount = env->GetStringLength(mString);
      mUnicodeString.setTo(false, mChars, charCount);
    }
  }

  ~ScopedJavaUnicodeString() {
    if (mString != NULL) {
      mEnv->ReleaseStringChars(mString, mChars);
    }
  }

  bool valid() const {
    return (mString != NULL);
  }

  const UnicodeString& unicodeString() const {
    return mUnicodeString;
  }

  UnicodeString& unicodeString() {
    return mUnicodeString;
  }

 private:
  JNIEnv* mEnv;
  jstring mString;
  const UChar* mChars;
  UnicodeString mUnicodeString;

  // Disallow copy and assignment.
  ScopedJavaUnicodeString(const ScopedJavaUnicodeString&);
  void operator=(const ScopedJavaUnicodeString&);
};

#endif  // SCOPED_JAVA_UNICODE_STRING_H_included
