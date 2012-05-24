#ifndef ROBOVM_THREAD_H
#define ROBOVM_THREAD_H

extern jboolean nvmInitThreads(Env* env);
extern void nvmMonitorEnter(Env* env, Object* obj);
extern void nvmMonitorExit(Env* env, Object* obj);
extern void nvmMonitorNotify(Env* env, Object* obj);
extern void nvmMonitorNotifyAll(Env* env, Object* obj);
extern void nvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos);
extern jlong nvmStartThread(Env* env, Thread* thread, jint priority);
extern void nvmThreadSleep(Env* env, jlong millis, jint nanos);
extern jboolean nvmThreadHoldsLock(Env* env, Object* obj);
extern jboolean nvmThreadClearInterrupted(Env* env);
extern jboolean nvmThreadIsInterrupted(Env* env, Thread* thread);
extern void nvmThreadInterrupt(Env* env, Thread* thread);
extern void nvmThreadYield(Env* env);
extern jint nvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group);
extern jint nvmAttachCurrentThreadAsDaemon(VM* vm, Env** env, char* name, Object* group);
extern jint nvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount);
extern jint nvmGetEnv(VM* vm, Env** env);

#endif

