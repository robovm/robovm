#include <robovm.h>
#include <string.h>

void* rvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmCopyMemory(Env* env, const void* src, int size) {
    void* dest = rvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* rvmCopyMemoryZ(Env* env, const char* src) {
    return rvmCopyMemory(env, src, strlen(src) + 1);
}

jboolean rvmCompareAndSwapInt(jint* ptr, jint oldval, jint newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

jboolean rvmCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

jboolean rvmCompareAndSwapPtr(void** ptr, void* oldval, void* newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

