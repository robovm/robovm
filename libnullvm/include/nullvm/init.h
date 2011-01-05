#ifndef NULLVM_INIT_H
#define NULLVM_INIT_H

extern jint nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs);
extern Env* nvmStartup(Options* options);
extern jboolean nvmRun(Env* env);
extern void nvmShutdown(Env* env, jint code);
extern void nvmAbort(char* format, ...);
extern void* nvmAllocateMemory(Env* env, int size);
extern DynamicLib** nvmGetNativeLibs(Env* env);
extern DynamicLib* nvmInitDynamicLib(Env* env, char* file, DynamicLib** first);
extern jboolean nvmLoadDynamicLib(Env* env, DynamicLib* dlib);
extern void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* first, DynamicLib* last, char* symbol);

#endif

