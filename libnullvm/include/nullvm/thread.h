#ifndef NULLVM_THREAD_H
#define NULLVM_THREAD_H

extern jboolean nvmInitThreads(Env* env);
extern void nvmMonitorEnter(Env* env, Object* obj);
extern void nvmMonitorExit(Env* env, Object* obj);
extern void nvmMonitorNotify(Env* env, Object* obj);
extern void nvmMonitorNotifyAll(Env* env, Object* obj);
extern void nvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos);
extern jlong nvmStartThread(Env* env, Thread* thread, jint priority);
extern void nvmThreadSleep(Env* env, jlong millis, jint nanos);
extern jboolean nvmThreadHoldsLock(Env* env, Object* obj);

#endif

