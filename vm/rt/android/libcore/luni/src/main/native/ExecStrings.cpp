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

#define LOG_TAG "ExecStrings"

#include "ExecStrings.h"

#include <stdlib.h>

#include "cutils/log.h"
#include "ScopedLocalRef.h"

ExecStrings::ExecStrings(JNIEnv* env, jobjectArray java_string_array)
    : env_(env), java_array_(java_string_array), array_(NULL) {
  if (java_array_ == NULL) {
    return;
  }

  jsize length = env_->GetArrayLength(java_array_);
  array_ = new char*[length + 1];
  array_[length] = NULL;
  for (jsize i = 0; i < length; ++i) {
    ScopedLocalRef<jstring> java_string(env_, reinterpret_cast<jstring>(env_->GetObjectArrayElement(java_array_, i)));
    // We need to pass these strings to const-unfriendly code.
    char* string = const_cast<char*>(env_->GetStringUTFChars(java_string.get(), NULL));
    array_[i] = string;
  }
}

ExecStrings::~ExecStrings() {
  if (array_ == NULL) {
    return;
  }

  // Temporarily clear any pending exception so we can clean up.
  jthrowable pending_exception = env_->ExceptionOccurred();
  if (pending_exception != NULL) {
    env_->ExceptionClear();
  }

  jsize length = env_->GetArrayLength(java_array_);
  for (jsize i = 0; i < length; ++i) {
    ScopedLocalRef<jstring> java_string(env_, reinterpret_cast<jstring>(env_->GetObjectArrayElement(java_array_, i)));
    env_->ReleaseStringUTFChars(java_string.get(), array_[i]);
  }
  delete[] array_;

  // Re-throw any pending exception.
  if (pending_exception != NULL) {
    if (env_->Throw(pending_exception) < 0) {
      ALOGE("Error rethrowing exception!");
    }
  }
}

char** ExecStrings::get() {
  return array_;
}
