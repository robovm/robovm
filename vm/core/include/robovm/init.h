#ifndef ROBOVM_INIT_H
#define ROBOVM_INIT_H

extern ClassLoader* systemClassLoader;

extern jboolean nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs);
extern Env* nvmStartup(Options* options);
extern VM* nvmCreateVM(Options* options);
extern Env* nvmCreateEnv(VM* vm);
extern jboolean nvmRun(Env* env);
extern void nvmShutdown(Env* env, jint code);
extern void nvmAbort(char* format, ...);

extern DynamicLib* nvmOpenDynamicLib(Env* env, const char* file);
extern void nvmCloseDynamicLib(Env* env, DynamicLib* lib);
extern jboolean nvmHasDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void nvmAddDynamicLib(Env* env, DynamicLib* lib, DynamicLib** libs);
extern void nvmRemoveDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* first, const char* symbol, jboolean searchAll);

#endif

