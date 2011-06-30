#include <nullvm.h>
#include <string.h>

void* nvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        nvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* nvmCopyMemory(Env* env, void* src, int size) {
    void* dest = nvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* nvmCopyMemoryZ(Env* env, char* src) {
    return nvmCopyMemory(env, src, strlen(src));
}

