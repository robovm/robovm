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

#define LOG_TAG "TimeZoneNames"

#include "IcuUtilities.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedJavaUnicodeString.h"
#include "ScopedLocalRef.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "unicode/calendar.h"
#include "unicode/timezone.h"
#include "unicode/tznames.h"

static bool isUtc(const UnicodeString& id) {
  static const UnicodeString kEtcUct("Etc/UCT", 7, US_INV);
  static const UnicodeString kEtcUtc("Etc/UTC", 7, US_INV);
  static const UnicodeString kEtcUniversal("Etc/Universal", 13, US_INV);
  static const UnicodeString kEtcZulu("Etc/Zulu", 8, US_INV);

  static const UnicodeString kUct("UCT", 3, US_INV);
  static const UnicodeString kUtc("UTC", 3, US_INV);
  static const UnicodeString kUniversal("Universal", 9, US_INV);
  static const UnicodeString kZulu("Zulu", 4, US_INV);

  return id == kEtcUct || id == kEtcUtc || id == kEtcUniversal || id == kEtcZulu ||
      id == kUct || id == kUtc || id == kUniversal || id == kZulu;
}

static void setStringArrayElement(JNIEnv* env, jobjectArray array, int i, const UnicodeString& s) {
  // Fill in whatever we got. We don't use the display names if they're "GMT[+-]xx:xx"
  // because icu4c doesn't use the up-to-date time zone transition data, so it gets these
  // wrong. TimeZone.getDisplayName creates accurate names on demand.
  // TODO: investigate whether it's worth doing that work once in the Java wrapper instead of on-demand.
  static const UnicodeString kGmt("GMT", 3, US_INV);
  if (!s.isBogus() && !s.startsWith(kGmt)) {
    ScopedLocalRef<jstring> javaString(env, env->NewString(s.getBuffer(), s.length()));
    env->SetObjectArrayElement(array, i, javaString.get());
  }
}

extern "C" void Java_libcore_icu_TimeZoneNames_fillZoneStrings(JNIEnv* env, jclass, jstring localeName, jobjectArray result) {
  Locale locale = getLocale(env, localeName);

  UErrorCode status = U_ZERO_ERROR;
  UniquePtr<TimeZoneNames> names(TimeZoneNames::createInstance(locale, status));
  if (maybeThrowIcuException(env, "TimeZoneNames::createInstance", status)) {
    return;
  }

  const UDate now(Calendar::getNow());

  static const UnicodeString kUtc("UTC", 3, US_INV);
  static const UnicodeString pacific_apia("Pacific/Apia", 12, US_INV);

  size_t id_count = env->GetArrayLength(result);
  for (size_t i = 0; i < id_count; ++i) {
    ScopedLocalRef<jobjectArray> java_row(env,
                                          reinterpret_cast<jobjectArray>(env->GetObjectArrayElement(result, i)));
    ScopedLocalRef<jstring> java_zone_id(env,
                                         reinterpret_cast<jstring>(env->GetObjectArrayElement(java_row.get(), 0)));
    ScopedJavaUnicodeString zone_id(env, java_zone_id.get());
    if (!zone_id.valid()) {
      return;
    }

    UnicodeString long_std;
    names->getDisplayName(zone_id.unicodeString(), UTZNM_LONG_STANDARD, now, long_std);
    UnicodeString short_std;
    names->getDisplayName(zone_id.unicodeString(), UTZNM_SHORT_STANDARD, now, short_std);
    UnicodeString long_dst;
    names->getDisplayName(zone_id.unicodeString(), UTZNM_LONG_DAYLIGHT, now, long_dst);
    UnicodeString short_dst;
    names->getDisplayName(zone_id.unicodeString(), UTZNM_SHORT_DAYLIGHT, now, short_dst);

    if (isUtc(zone_id.unicodeString())) {
      // ICU doesn't have names for the UTC zones; it just says "GMT+00:00" for both
      // long and short names. We don't want this. The best we can do is use "UTC"
      // for everything (since we don't know how to say "Universal Coordinated Time" in
      // every language).
      // TODO: check CLDR doesn't actually have this somewhere.
      long_std = short_std = long_dst = short_dst = kUtc;
    } else if (zone_id.unicodeString() == pacific_apia) {
      // icu4c 50 doesn't know Samoa has DST yet. http://b/7955614
      if (long_dst.isBogus()) {
        long_dst = "Samoa Daylight Time";
      }
    }

    setStringArrayElement(env, java_row.get(), 1, long_std);
    setStringArrayElement(env, java_row.get(), 2, short_std);
    setStringArrayElement(env, java_row.get(), 3, long_dst);
    setStringArrayElement(env, java_row.get(), 4, short_dst);
  }
}
