#ifndef NULLVM_THREAD_H
#define NULLVM_THREAD_H

extern jboolean nvmInitThreads(Env* env);
extern jint nvmMonitorEnter(Env* env, Object* obj);
extern jint nvmMonitorExit(Env* env, Object* obj);

#endif

