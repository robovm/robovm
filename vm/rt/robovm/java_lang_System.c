/*
 * Copyright (C) 2012 Trillian AB
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
#ifdef DARWIN
#   include <mach/mach_time.h>
#endif

#define DSO_PREFIX "lib"
#define DSO_EXT ".so"
#if defined(DARWIN)
    #undef DSO_EXT
    #define DSO_EXT ".dylib"
#endif

#if defined(DARWIN)
    void readDarwinOSVersionFromSystemVersionPList(char* buffer);
#endif

jint Java_java_lang_System_identityHashCode(Env* env, Class* c, Object* o) {
    return (jint) PTR_TO_LONG(o);
}

jlong Java_java_lang_System_currentTimeMillis(Env* env, Class* c) {
    struct timeval tv;
    gettimeofday(&tv, (struct timezone *) NULL);
    jlong millis = tv.tv_sec * 1000LL + tv.tv_usec / 1000;
    return millis;
}

jlong Java_java_lang_System_nanoTime(JNIEnv* env, jclass clazz) {
#if defined(DARWIN)
    mach_timebase_info_data_t info;
    mach_timebase_info(&info);
    uint64_t t = mach_absolute_time();
    t *= info.numer;
    t /= info.denom;
    return (jlong) t;
#else
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return (jlong) ts.tv_sec * 1000000000LL + ts.tv_nsec;
#endif
}

Object* Java_java_lang_System_mapLibraryName0(Env* env, Class* c, Object* userLibName) {
    if (!userLibName) return NULL;
    char* libName = rvmGetStringUTFChars(env, userLibName);
    if (!libName) return NULL;
    char* result = rvmAllocateMemoryAtomic(env, strlen(libName) + strlen(DSO_PREFIX) + strlen(DSO_EXT) + 1);
    if (!result) return NULL;
    strcpy(result, DSO_PREFIX);
    strcat(result, libName);
    strcat(result, DSO_EXT);
    return rvmNewStringUTF(env, result, -1);
}

ObjectArray* Java_java_lang_System_robovmSpecialProperties(Env* env, Class* c) {
    char* osArch = NULL;
#if defined(RVM_X86)
    osArch = "os.arch=x86";
#elif defined(RVM_ARMV6) || defined(RVM_ARMV7) || defined(RVM_THUMBV6) || defined(RVM_THUMBV7)
    osArch = "os.arch=arm";
#endif

#if defined(DARWIN)
    char* osName = NULL;
#   if defined(IOS) && defined(RVM_X86)
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

    ObjectArray* result = rvmNewObjectArray(env, 3, java_lang_String, NULL, NULL);
    if (!result) return NULL;

    result->values[0] = rvmNewStringUTF(env, osName, -1);
    if (!result->values[0]) return NULL;
    result->values[1] = rvmNewStringUTF(env, osVersion, -1);
    if (!result->values[1]) return NULL;
    result->values[2] = rvmNewStringUTF(env, osArch, -1);
    if (!result->values[2]) return NULL;

    return result;
#else
    ObjectArray* result = rvmNewObjectArray(env, 1, java_lang_String, NULL, NULL);
    if (!result) return NULL;
    result->values[0] = rvmNewStringUTF(env, osArch, -1);
    if (!result->values[0]) return NULL;
    return result;
#endif
}
