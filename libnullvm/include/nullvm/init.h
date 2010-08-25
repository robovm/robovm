#ifndef NULLVM_INIT_H
#define NULLVM_INIT_H

extern jint nvmParseArgs(int argc, char* argv[], Options* options);
extern Env* nvmStartup(Options* options);
extern jboolean nvmRun(Env* env, Options* options);
extern void nvmShutdown(void);
extern void nvmAbort(char* format, ...);

#endif

