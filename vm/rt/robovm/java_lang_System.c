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

Object* Java_java_lang_System_mapLibraryName(Env* env, Class* c, Object* userLibName) {
    if (!userLibName) return NULL;
    char* libName = rvmGetStringUTFChars(env, userLibName);
    if (!libName) return NULL;
    char* result = rvmAllocateMemory(env, strlen(libName) + strlen(DSO_PREFIX) + strlen(DSO_EXT) + 1);
    if (!result) return NULL;
    strcpy(result, DSO_PREFIX);
    strcat(result, libName);
    strcat(result, DSO_EXT);
    return rvmNewStringUTF(env, result, -1);
}

