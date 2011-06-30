#ifndef NULLVM_MEMORY_H
#define NULLVM_MEMORY_H

extern void* nvmAllocateMemory(Env* env, int size);
extern void* nvmCopyMemory(Env* env, void* src, int size);
extern void* nvmCopyMemoryZ(Env* env, char* src);

#endif

