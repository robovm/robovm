/*
 * Copyright (C) 2012 RoboVM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef ROBOVM_THREAD_H
#define ROBOVM_THREAD_H

enum {
    THREAD_STATE_NEW,
    THREAD_STATE_RUNNABLE,
    THREAD_STATE_BLOCKED,
    THREAD_STATE_WAITING,
    THREAD_STATE_TIMED_WAITING,
    THREAD_STATE_TERMINATED
};

extern jboolean rvmInitThreads(Env* env);
extern void rvmMonitorEnter(Env* env, Object* obj);
extern void rvmMonitorExit(Env* env, Object* obj);
extern void rvmMonitorNotify(Env* env, Object* obj);
extern void rvmMonitorNotifyAll(Env* env, Object* obj);
extern void rvmMonitorWait(Env* env, Object* obj, jlong millis, jint nanos);
extern jlong rvmStartThread(Env* env, Thread* thread);
extern void rvmThreadSleep(Env* env, jlong millis, jint nanos);
extern jboolean rvmThreadHoldsLock(Env* env, Object* obj);
extern jboolean rvmThreadClearInterrupted(Env* env, Thread* thread);
extern jboolean rvmThreadIsInterrupted(Env* env, Thread* thread);
extern void rvmThreadInterrupt(Env* env, Thread* thread);
extern void rvmThreadYield(Env* env);
extern jint rvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group);
extern jint rvmAttachCurrentThreadAsDaemon(VM* vm, Env** env, char* name, Object* group);
extern jint rvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount);
extern jint rvmGetEnv(VM* vm, Env** env);
extern jint rvmThreadGetState(Env* env, Thread* thread);

#endif

