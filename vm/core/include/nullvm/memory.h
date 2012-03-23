#ifndef NULLVM_MEMORY_H
#define NULLVM_MEMORY_H

extern void* nvmAllocateMemory(Env* env, int size);
extern void* nvmCopyMemory(Env* env, const void* src, int size);
extern void* nvmCopyMemoryZ(Env* env, const char* src);
extern jboolean nvmCompareAndSwapInt(jint* ptr, jint oldval, jint newval);
extern jboolean nvmCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval);
extern jboolean nvmCompareAndSwapPtr(void** ptr, void* oldval, void* newval);

#endif

