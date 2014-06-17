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

#define LOG_TAG "DateIntervalFormat"

#include "IcuUtilities.h"
#include "JniConstants.h"
#include "ScopedJavaUnicodeString.h"
#include "UniquePtr.h"
#include "cutils/log.h"
#include "unicode/dtitvfmt.h"

extern "C" jlong Java_libcore_icu_DateIntervalFormat_createDateIntervalFormat(JNIEnv* env, jclass, jstring javaSkeleton, jstring javaLocaleName, jstring javaTzName) {
  Locale locale = getLocale(env, javaLocaleName);

  ScopedJavaUnicodeString skeletonHolder(env, javaSkeleton);
  if (!skeletonHolder.valid()) {
    return 0;
  }

  UErrorCode status = U_ZERO_ERROR;
  DateIntervalFormat* formatter(DateIntervalFormat::createInstance(skeletonHolder.unicodeString(), locale, status));
  if (maybeThrowIcuException(env, "DateIntervalFormat::createInstance", status)) {
    return 0;
  }

  ScopedJavaUnicodeString tzNameHolder(env, javaTzName);
  if (!tzNameHolder.valid()) {
    return 0;
  }
  formatter->adoptTimeZone(TimeZone::createTimeZone(tzNameHolder.unicodeString()));

  return reinterpret_cast<uintptr_t>(formatter);
}

extern "C" void Java_libcore_icu_DateIntervalFormat_destroyDateIntervalFormat(JNIEnv*, jclass, jlong address) {
  delete reinterpret_cast<DateIntervalFormat*>(address);
}

extern "C" jstring Java_libcore_icu_DateIntervalFormat_formatDateInterval(JNIEnv* env, jclass, jlong address, jlong fromDate, jlong toDate) {
  DateIntervalFormat* formatter(reinterpret_cast<DateIntervalFormat*>(address));
  DateInterval date_interval(fromDate, toDate);

  UnicodeString s;
  FieldPosition pos = 0;
  UErrorCode status = U_ZERO_ERROR;
  formatter->format(&date_interval, s, pos, status);
  if (maybeThrowIcuException(env, "DateIntervalFormat::format", status)) {
    return NULL;
  }

  return env->NewString(s.getBuffer(), s.length());
}
