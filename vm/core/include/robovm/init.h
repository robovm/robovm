#ifndef ROBOVM_INIT_H
#define ROBOVM_INIT_H

extern ClassLoader* systemClassLoader;

extern jboolean rvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreRvmArgs);
extern Env* rvmStartup(Options* options);
extern VM* rvmCreateVM(Options* options);
extern Env* rvmCreateEnv(VM* vm);
extern jboolean rvmRun(Env* env);
extern void rvmShutdown(Env* env, jint code);
extern void rvmAbort(char* format, ...);

extern DynamicLib* rvmOpenDynamicLib(Env* env, const char* file);
extern void rvmCloseDynamicLib(Env* env, DynamicLib* lib);
extern jboolean rvmHasDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void rvmAddDynamicLib(Env* env, DynamicLib* lib, DynamicLib** libs);
extern void rvmRemoveDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void* rvmFindDynamicLibSymbol(Env* env, DynamicLib* first, const char* symbol, jboolean searchAll);

#endif

