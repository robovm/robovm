/*
 * Copyright (C) 2012 Trillian Mobile AB
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
#include <robovm.h>
#include <sys/time.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <limits.h>
#include <pwd.h>
#include <sys/utsname.h>
#include <string.h>

#if defined(DARWIN)
    void readDarwinOSVersionFromSystemVersionPList(char* buffer);
    void getDarwinLocaleParts(char* lang, char* country, char* variant);
#endif

jint Java_java_lang_System_identityHashCode(Env* env, Class* c, Object* o) {
    return (jint) PTR_TO_LONG(o);
}

ObjectArray* Java_java_lang_System_robovmSpecialProperties(Env* env, Class* c) {
    char* osArch = NULL;
#if defined(RVM_X86)
    osArch = "os.arch=x86";
#elif defined(RVM_X86_64)
    osArch = "os.arch=x86_64";
#elif defined(RVM_THUMBV7)
    osArch = "os.arch=arm";
#elif defined(RVM_ARM64)
    osArch = "os.arch=aarch64";
#else
#   error Unsupported arch
#endif

#if defined(DARWIN)
    char* osName = NULL;
#   if defined(IOS) && (defined(RVM_X86) || defined(RVM_X86_64))
    osName = "os.name=iOS Simulator";
#   elif defined(IOS)
    osName = "os.name=iOS";
#   elif defined(MACOSX)
    osName = "os.name=Mac OS X";
#   endif

    char osVersion[64];
    memset(osVersion, 0, sizeof(osVersion));
    strcat(osVersion, "os.version=");
    readDarwinOSVersionFromSystemVersionPList(osVersion);

    char userLanguage[64] = "user.language=";
    char userRegion[64] = "user.region=";
    char userVariant[64] = "user.variant=";
    getDarwinLocaleParts(userLanguage, userRegion, userVariant);
    jboolean hasLocale = strcmp(userLanguage, "user.language=") != 0;

    ObjectArray* result = rvmNewObjectArray(env, hasLocale ? 6 : 3, java_lang_String, NULL, NULL);
    if (!result) return NULL;

    result->values[0] = rvmNewStringUTF(env, osName, -1);
    if (!result->values[0]) return NULL;
    result->values[1] = rvmNewStringUTF(env, osVersion, -1);
    if (!result->values[1]) return NULL;
    result->values[2] = rvmNewStringUTF(env, osArch, -1);
    if (!result->values[2]) return NULL;
    if (hasLocale) {
        result->values[3] = rvmNewStringUTF(env, userLanguage, -1);
        if (!result->values[3]) return NULL;
        result->values[4] = rvmNewStringUTF(env, userRegion, -1);
        if (!result->values[4]) return NULL;
        result->values[5] = rvmNewStringUTF(env, userVariant, -1);
        if (!result->values[5]) return NULL;
    }

    return result;
#else
    ObjectArray* result = rvmNewObjectArray(env, 1, java_lang_String, NULL, NULL);
    if (!result) return NULL;
    result->values[0] = rvmNewStringUTF(env, osArch, -1);
    if (!result->values[0]) return NULL;
    return result;
#endif
}
