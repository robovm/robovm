#ifndef NULLVM_INIT_H
#define NULLVM_INIT_H

extern jboolean nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs);
extern Env* nvmStartup(Options* options);
extern VM* nvmCreateVM(Options* options);
extern Env* nvmCreateEnv(VM* vm);
extern jboolean nvmRun(Env* env);
extern void nvmShutdown(Env* env, jint code);
extern void nvmAbort(char* format, ...);
extern DynamicLib* nvmLoadDynamicLib(Env* env, char* file, DynamicLib** first);
extern void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* first, char* symbol);

#endif

