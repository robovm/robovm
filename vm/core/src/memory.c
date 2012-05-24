#include <robovm.h>
#include <string.h>

void* nvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        nvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* nvmCopyMemory(Env* env, const void* src, int size) {
    void* dest = nvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* nvmCopyMemoryZ(Env* env, const char* src) {
    return nvmCopyMemory(env, src, strlen(src) + 1);
}

jboolean nvmCompareAndSwapInt(jint* ptr, jint oldval, jint newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

jboolean nvmCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

jboolean nvmCompareAndSwapPtr(void** ptr, void* oldval, void* newval) {
    return __sync_bool_compare_and_swap(ptr, oldval, newval) ? TRUE : FALSE;
}

