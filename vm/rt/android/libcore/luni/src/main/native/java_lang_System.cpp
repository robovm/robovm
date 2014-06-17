/*
 * Copyright (C) 2008 The Android Open Source Project
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

#define LOG_TAG "System"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedUtfChars.h"
#include "cutils/log.h"
#include "openssl/opensslv.h"
#include "toStringArray.h"
#include "zlib.h"

#include <string>
#include <vector>

#include <limits.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#ifdef __APPLE__
// RoboVM note: Needed for mach_absolute_time() on Darwin
#   include <mach/mach_time.h>
#endif

extern "C" void Java_java_lang_System_log(JNIEnv* env, jclass, jchar type, jstring javaMessage, jthrowable exception) {
    ScopedUtfChars message(env, javaMessage);
    if (message.c_str() == NULL) {
        // Since this function is used for last-gasp debugging output, be noisy on failure.
        ALOGE("message.c_str() == NULL");
        return;
    }
    int priority;
    switch (type) {
    case 'D': case 'd': priority = ANDROID_LOG_DEBUG;   break;
    case 'E': case 'e': priority = ANDROID_LOG_ERROR;   break;
    case 'F': case 'f': priority = ANDROID_LOG_FATAL;   break;
    case 'I': case 'i': priority = ANDROID_LOG_INFO;    break;
    case 'S': case 's': priority = ANDROID_LOG_SILENT;  break;
    case 'V': case 'v': priority = ANDROID_LOG_VERBOSE; break;
    case 'W': case 'w': priority = ANDROID_LOG_WARN;    break;
    default:            priority = ANDROID_LOG_DEFAULT; break;
    }
    LOG_PRI(priority, LOG_TAG, "%s", message.c_str());
    if (exception != NULL) {
        jniLogException(env, priority, LOG_TAG, exception);
    }
}

// Sets a field via JNI. Used for the standard streams, which are read-only otherwise.
extern "C" void Java_java_lang_System_setFieldImpl(JNIEnv* env, jclass clazz,
        jstring javaName, jstring javaSignature, jobject object) {
    ScopedUtfChars name(env, javaName);
    if (name.c_str() == NULL) {
        return;
    }
    ScopedUtfChars signature(env, javaSignature);
    if (signature.c_str() == NULL) {
        return;
    }
    jfieldID fieldID = env->GetStaticFieldID(clazz, name.c_str(), signature.c_str());
    env->SetStaticObjectField(clazz, fieldID, object);
}

extern "C" jobjectArray Java_java_lang_System_specialProperties(JNIEnv* env, jclass) {
    std::vector<std::string> properties;

    char path[PATH_MAX];
    properties.push_back(std::string("user.dir=") + getcwd(path, sizeof(path)));

    properties.push_back("android.zlib.version=" ZLIB_VERSION);
    properties.push_back("android.openssl.version=" OPENSSL_VERSION_TEXT);

    return toStringArray(env, properties);
}

extern "C" jlong Java_java_lang_System_currentTimeMillis(JNIEnv*, jclass) {
    timeval now;
    gettimeofday(&now, NULL);
    jlong when = now.tv_sec * 1000LL + now.tv_usec / 1000;
    return when;
}

extern "C" jlong Java_java_lang_System_nanoTime(JNIEnv*, jclass) {
// RoboVM note: Darwin doesn't have CLOCK_MONOTONIC
#if defined(__APPLE__)
    mach_timebase_info_data_t info;
    mach_timebase_info(&info);
    uint64_t t = mach_absolute_time();
    t *= info.numer;
    t /= info.denom;
    return (jlong) t;
#else
    timespec now;
    clock_gettime(CLOCK_MONOTONIC, &now);
    return now.tv_sec * 1000000000LL + now.tv_nsec;
#endif
}

// RoboVM note: Renamed by appending '0'
extern "C" jstring Java_java_lang_System_mapLibraryName0(JNIEnv* env, jclass, jstring javaName) {
    ScopedUtfChars name(env, javaName);
    if (name.c_str() == NULL) {
        return NULL;
    }
    char* mappedName = NULL;
    asprintf(&mappedName, OS_SHARED_LIB_FORMAT_STR, name.c_str());
    jstring result = env->NewStringUTF(mappedName);
    free(mappedName);
    return result;
}
