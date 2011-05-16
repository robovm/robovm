#ifndef NULLVM_THREAD_H
#define NULLVM_THREAD_H

extern jboolean nvmInitThreads(Env* env);
extern void nvmMonitorEnter(Env* env, Object* obj);
extern void nvmMonitorExit(Env* env, Object* obj);
extern void nvmMonitorNotify(Env* env, Object* obj);
extern void nvmMonitorNotifyAll(Env* env, Object* obj);

#endif

