#ifndef ROBOVM_MEMORY_H
#define ROBOVM_MEMORY_H

extern void* rvmAllocateMemory(Env* env, int size);
extern void* rvmCopyMemory(Env* env, const void* src, int size);
extern void* rvmCopyMemoryZ(Env* env, const char* src);
extern jboolean rvmCompareAndSwapInt(jint* ptr, jint oldval, jint newval);
extern jboolean rvmCompareAndSwapLong(jlong* ptr, jlong oldval, jlong newval);
extern jboolean rvmCompareAndSwapPtr(void** ptr, void* oldval, void* newval);

#endif

