#ifndef ROBOVM_THREAD_H
#define ROBOVM_THREAD_H

extern jboolean rvmInitThreads(Env* env);
extern void rvmMonitorEnter(Env* env, Object* obj);
extern void rvmMonitorExit(Env* env, Object* obj);
extern void rvmMonitorNotify(Env* env, Object* obj);
extern void rvmMonitorNotifyAll(Env* env, Object* obj);
extern void rvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos);
extern jlong rvmStartThread(Env* env, Thread* thread, jint priority);
extern void rvmThreadSleep(Env* env, jlong millis, jint nanos);
extern jboolean rvmThreadHoldsLock(Env* env, Object* obj);
extern jboolean rvmThreadClearInterrupted(Env* env);
extern jboolean rvmThreadIsInterrupted(Env* env, Thread* thread);
extern void rvmThreadInterrupt(Env* env, Thread* thread);
extern void rvmThreadYield(Env* env);
extern jint rvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group);
extern jint rvmAttachCurrentThreadAsDaemon(VM* vm, Env** env, char* name, Object* group);
extern jint rvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount);
extern jint rvmGetEnv(VM* vm, Env** env);

#endif

