/*
 * Copyright (C) 2012 Trillian AB
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

#define THREAD_SIGNAL_STACK_SIZE (64 * 1024) // We need 64k of stack for the signal handler in signal.c.
#define THREAD_MIN_STACK_SIZE (64 * 1024)
#define THREAD_DEFAULT_STACK_SIZE ((512 * 1024) - THREAD_SIGNAL_STACK_SIZE)
#define THREAD_STACK_SIZE_MULTIPLE (4 * 1024)
#define THREAD_STACK_GUARD_SIZE 4096 // 4k seems to be the standard guard size on both Linux, Mac OS X and iOS

enum {
    THREAD_UNDEFINED    = -1,       /* makes enum compatible with int32_t */

    /* these match up with JDWP values */
    THREAD_ZOMBIE       = 0,        /* TERMINATED */
    THREAD_RUNNING      = 1,        /* RUNNABLE or running now */
    THREAD_TIMED_WAIT   = 2,        /* TIMED_WAITING in Object.wait() */
    THREAD_MONITOR      = 3,        /* BLOCKED on a monitor */
    THREAD_WAIT         = 4,        /* WAITING in Object.wait() */
    /* non-JDWP states */
    THREAD_INITIALIZING = 5,        /* allocated, not yet running */
    THREAD_STARTING     = 6,        /* started, not yet on thread list */
    THREAD_NATIVE       = 7,        /* off in a JNI native method */
    THREAD_VMWAIT       = 8,        /* waiting on a VM resource */
    THREAD_SUSPENDED    = 9,        /* suspended, usually by GC or debugger */
};

/* thread priorities, from java.lang.Thread */
enum {
    THREAD_MIN_PRIORITY     = 1,
    THREAD_NORM_PRIORITY    = 5,
    THREAD_MAX_PRIORITY     = 10,
};

extern jboolean rvmInitThreads(Env* env);
extern void rvmLockThreadsList();
extern void rvmUnlockThreadsList();
extern jlong rvmStartThread(Env* env, JavaThread* threadObj);
extern void rvmThreadYield(Env* env);
extern jint rvmAttachCurrentThread(VM* vm, Env** env, char* name, Object* group);
extern jint rvmAttachCurrentThreadAsDaemon(VM* vm, Env** env, char* name, Object* group);
extern jint rvmDetachCurrentThread(VM* vm, jboolean ignoreAttachCount, jboolean unregisterGC);
extern void rvmJoinNonDaemonThreads(Env* env);
extern Env* rvmGetEnv();
extern Thread* rvmGetThreadByThreadId(Env* env, uint32_t threadId);
extern jint rvmChangeThreadStatus(Env* env, Thread* thread, jint newStatus);
extern void rvmChangeThreadPriority(Env* env, Thread* thread, jint priority);
extern void rvmThreadNameChanged(Env* env, Thread* thread);

#endif

