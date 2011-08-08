#ifndef NULLVM_UNWIND_H
#define NULLVM_UNWIND_H

#define UNWIND_UNHANDLED_EXCEPTION 1
#define UNWIND_FATAL_ERROR 2

extern void nvmUnwindIterateCallStack(Env* env, jboolean (*iterator)(Env*, void*, jint, void*), void* data);
extern void* nvmUnwindGetCallerAtDepth(Env* env, jint depth, jint* offset);
extern void* nvmUnwindGetCaller(Env* env, jint* offset);

#endif

