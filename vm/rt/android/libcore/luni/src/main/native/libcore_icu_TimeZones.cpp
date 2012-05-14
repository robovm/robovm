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

#define LOG_TAG "TimeZones"

#include <map>
#include <vector>

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedJavaUnicodeString.h"
#include "ScopedLocalRef.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "unicode/smpdtfmt.h"
#include "unicode/timezone.h"

extern Locale getLocale(JNIEnv* env, jstring localeName);

extern "C" jobjectArray Java_libcore_icu_TimeZones_forCountryCode(JNIEnv* env, jclass, jstring countryCode) {
    ScopedUtfChars countryChars(env, countryCode);
    if (countryChars.c_str() == NULL) {
        return NULL;
    }

    UniquePtr<StringEnumeration> ids(TimeZone::createEnumeration(countryChars.c_str()));
    if (ids.get() == NULL) {
        return NULL;
    }
    UErrorCode status = U_ZERO_ERROR;
    int32_t idCount = ids->count(status);
    if (maybeThrowIcuException(env, status)) {
        return NULL;
    }

    jobjectArray result = env->NewObjectArray(idCount, JniConstants::stringClass, NULL);
    for (int32_t i = 0; i < idCount; ++i) {
        const UnicodeString* id = ids->snext(status);
        if (maybeThrowIcuException(env, status)) {
            return NULL;
        }
        ScopedLocalRef<jstring> idString(env, env->NewString(id->getBuffer(), id->length()));
        env->SetObjectArrayElement(result, i, idString.get());
    }
    return result;
}

struct TimeZoneNames {
    TimeZone* tz;

    UnicodeString longStd;
    UnicodeString shortStd;
    UnicodeString longDst;
    UnicodeString shortDst;

    UDate standardDate;
    UDate daylightSavingDate;
};

static void setStringArrayElement(JNIEnv* env, jobjectArray array, int i, const UnicodeString& s) {
    ScopedLocalRef<jstring> javaString(env, env->NewString(s.getBuffer(), s.length()));
    env->SetObjectArrayElement(array, i, javaString.get());
}

static bool isUtc(const UnicodeString& id) {
    static UnicodeString etcUct("Etc/UCT", 7, US_INV);
    static UnicodeString etcUtc("Etc/UTC", 7, US_INV);
    static UnicodeString etcUniversal("Etc/Universal", 13, US_INV);
    static UnicodeString etcZulu("Etc/Zulu", 8, US_INV);

    static UnicodeString uct("UCT", 3, US_INV);
    static UnicodeString utc("UTC", 3, US_INV);
    static UnicodeString universal("Universal", 9, US_INV);
    static UnicodeString zulu("Zulu", 4, US_INV);

    return id == etcUct || id == etcUtc || id == etcUniversal || id == etcZulu ||
            id == uct || id == utc || id == universal || id == zulu;
}

extern "C" jobjectArray Java_libcore_icu_TimeZones_getZoneStringsImpl(JNIEnv* env, jclass, jstring localeName, jobjectArray timeZoneIds) {
    Locale locale = getLocale(env, localeName);

    // We could use TimeZone::getDisplayName, but that's even slower
    // because it creates a new SimpleDateFormat each time.
    // We're better off using SimpleDateFormat directly.

    // We can't use DateFormatSymbols::getZoneStrings because that
    // uses its own set of time zone ids and contains empty strings
    // instead of GMT offsets (a pity, because it's a bit faster than this code).

    UErrorCode status = U_ZERO_ERROR;
    UnicodeString longPattern("zzzz", 4, US_INV);
    SimpleDateFormat longFormat(longPattern, locale, status);
    // 'z' only uses "common" abbreviations. 'V' allows all known abbreviations.
    // For example, "PST" is in common use in en_US, but "CET" isn't.
    UnicodeString commonShortPattern("z", 1, US_INV);
    SimpleDateFormat shortFormat(commonShortPattern, locale, status);
    UnicodeString allShortPattern("V", 1, US_INV);
    SimpleDateFormat allShortFormat(allShortPattern, locale, status);

    UnicodeString utc("UTC", 3, US_INV);

    // TODO: use of fixed dates prevents us from using the correct historical name when formatting dates.
    // TODO: use of dates not in the current year could cause us to output obsoleted names.
    // 15th January 2008
    UDate date1 = 1203105600000.0;
    // 15th July 2008
    UDate date2 = 1218826800000.0;

    // In the first pass, we get the long names for the time zone.
    // We also get any commonly-used abbreviations.
    std::vector<TimeZoneNames> table;
    typedef std::map<UnicodeString, UnicodeString*> AbbreviationMap;
    AbbreviationMap usedAbbreviations;
    size_t idCount = env->GetArrayLength(timeZoneIds);
    for (size_t i = 0; i < idCount; ++i) {
        ScopedLocalRef<jstring> javaZoneId(env,
                reinterpret_cast<jstring>(env->GetObjectArrayElement(timeZoneIds, i)));
        ScopedJavaUnicodeString zoneId(env, javaZoneId.get());
        UnicodeString id(zoneId.unicodeString());

        TimeZoneNames row;
        if (isUtc(id)) {
            // ICU doesn't have names for the UTC zones; it just says "GMT+00:00" for both
            // long and short names. We don't want this. The best we can do is use "UTC"
            // for everything (since we don't know how to say "Universal Coordinated Time").
            row.tz = NULL;
            row.longStd = row.shortStd = row.longDst = row.shortDst = utc;
            table.push_back(row);
            usedAbbreviations[utc] = &utc;
            continue;
        }

        row.tz = TimeZone::createTimeZone(id);
        longFormat.setTimeZone(*row.tz);
        shortFormat.setTimeZone(*row.tz);

        int32_t daylightOffset;
        int32_t rawOffset;
        row.tz->getOffset(date1, false, rawOffset, daylightOffset, status);
        if (daylightOffset != 0) {
            // The TimeZone is reporting that we are in daylight time for the winter date.
            // The dates are for the wrong hemisphere, so swap them.
            row.standardDate = date2;
            row.daylightSavingDate = date1;
        } else {
            row.standardDate = date1;
            row.daylightSavingDate = date2;
        }

        longFormat.format(row.standardDate, row.longStd);
        shortFormat.format(row.standardDate, row.shortStd);
        if (row.tz->useDaylightTime()) {
            longFormat.format(row.daylightSavingDate, row.longDst);
            shortFormat.format(row.daylightSavingDate, row.shortDst);
        } else {
            row.longDst = row.longStd;
            row.shortDst = row.shortStd;
        }

        table.push_back(row);
        usedAbbreviations[row.shortStd] = &row.longStd;
        usedAbbreviations[row.shortDst] = &row.longDst;
    }

    // In the second pass, we create the Java String[][].
    // We also look for any uncommon abbreviations that don't conflict with ones we've already seen.
    jobjectArray result = env->NewObjectArray(idCount, JniConstants::stringArrayClass, NULL);
    UnicodeString gmt("GMT", 3, US_INV);
    for (size_t i = 0; i < table.size(); ++i) {
        TimeZoneNames& row(table[i]);
        // Did we get a GMT offset instead of an abbreviation?
        if (row.shortStd.length() > 3 && row.shortStd.startsWith(gmt)) {
            // See if we can do better...
            UnicodeString uncommonStd, uncommonDst;
            allShortFormat.setTimeZone(*row.tz);
            allShortFormat.format(row.standardDate, uncommonStd);
            if (row.tz->useDaylightTime()) {
                allShortFormat.format(row.daylightSavingDate, uncommonDst);
            } else {
                uncommonDst = uncommonStd;
            }

            // If this abbreviation isn't already in use, we can use it.
            AbbreviationMap::iterator it = usedAbbreviations.find(uncommonStd);
            if (it == usedAbbreviations.end() || *(it->second) == row.longStd) {
                row.shortStd = uncommonStd;
                usedAbbreviations[row.shortStd] = &row.longStd;
            }
            it = usedAbbreviations.find(uncommonDst);
            if (it == usedAbbreviations.end() || *(it->second) == row.longDst) {
                row.shortDst = uncommonDst;
                usedAbbreviations[row.shortDst] = &row.longDst;
            }
        }
        // Fill in whatever we got.
        ScopedLocalRef<jobjectArray> javaRow(env, env->NewObjectArray(5, JniConstants::stringClass, NULL));
        ScopedLocalRef<jstring> id(env, reinterpret_cast<jstring>(env->GetObjectArrayElement(timeZoneIds, i)));
        env->SetObjectArrayElement(javaRow.get(), 0, id.get());
        setStringArrayElement(env, javaRow.get(), 1, row.longStd);
        setStringArrayElement(env, javaRow.get(), 2, row.shortStd);
        setStringArrayElement(env, javaRow.get(), 3, row.longDst);
        setStringArrayElement(env, javaRow.get(), 4, row.shortDst);
        env->SetObjectArrayElement(result, i, javaRow.get());
        delete row.tz;
    }

    return result;
}

