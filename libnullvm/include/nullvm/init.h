#ifndef NULLVM_INIT_H
#define NULLVM_INIT_H

extern jint nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs);
extern Env* nvmStartup(Options* options);
extern jboolean nvmRun(Env* env);
extern void nvmShutdown(void);
extern void nvmAbort(char* format, ...);
extern void* nvmAllocateMemory(Env* env, int size);

#endif

