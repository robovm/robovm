/*
 * Copyright (C) 2011 The Android Open Source Project
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

#ifndef SCOPED_STRING_CHARS_H_included
#define SCOPED_STRING_CHARS_H_included

#include "JNIHelp.h"

// A smart pointer that provides access to a jchar* given a JNI jstring.
class ScopedStringChars {
public:
    ScopedStringChars(JNIEnv* env, jstring s) : mEnv(env), mString(s), mSize(0) {
        mChars = env->GetStringChars(mString, NULL);
        if (mChars != NULL) {
            mSize = env->GetStringLength(mString);
        }
    }

    ~ScopedStringChars() {
        mEnv->ReleaseStringChars(mString, mChars);
    }

    const jchar* get() const { return mChars; }
    const jchar& operator[](size_t n) const { return mChars[n]; }
    size_t size() const { return mSize; }

private:
    JNIEnv* mEnv;
    jstring mString;
    const jchar* mChars;
    size_t mSize;

    // Disallow copy and assignment.
    ScopedStringChars(const ScopedStringChars&);
    void operator=(const ScopedStringChars&);
};

#endif  // SCOPED_STRING_CHARS_H_included
