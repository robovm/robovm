/*
 * Copyright (C) 2012 Trillian Mobile AB
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

#define _GNU_SOURCE
#include <robovm.h>
#include <signal.h>
#if defined(DARWIN)
#   include <mach/mach.h>
#   include <mach/semaphore.h>
#else
#   include <semaphore.h>
#endif
#include <errno.h>
#include "private.h"

#if defined(DARWIN)
// Darwin doesn't implement sem_init(). Use Mach semaphores instead.
typedef semaphore_t sem_t;
static inline int sem_init(sem_t* sem, int pshared, unsigned int value) {
    return semaphore_create(mach_task_self(), sem, SYNC_POLICY_FIFO, value);
}
static inline int sem_wait(sem_t* sem) {
    return semaphore_wait(*sem);
}
static inline int sem_post(sem_t* sem) {
    return semaphore_signal(*sem);
}
#endif

#define LOG_TAG "core.signal"

#define DUMP_THREAD_STACK_TRACE_SIGNAL SIGUSR1
// The signal used in libcore's AsynchronousSocketCloseMonitor.cpp
#if defined(__APPLE__)
#define BLOCKED_THREAD_SIGNAL SIGUSR2
#else
#define BLOCKED_THREAD_SIGNAL (SIGRTMIN + 2)
#endif

typedef struct {
    struct sigaction blockedThreadSignal;
} SavedSignals;

/*
 * The common way to implement stack overflow detection is to catch SIGSEGV and see if the
 * address that generated the fault is in the current thread's stack guard page. Since the stack
 * has been consumed at this point one usually uses an alternate signal stack for the signal
 * handler to run on using sigaltstack(). Unfortunately sigaltstack() seems to be broken on
 * iOS. Even if it completes without errors the alternate stack won't be used by the signal
 * handler. In RoboVM we work around this bug by inserting code in the prologue of
 * compiled methods which tries to read from the stack area at sp-64k. If this read hits the 
 * guard page a fault will occur but we'll still have about 64k left of stack space to run the signal 
 * handler on.
 */

static Method* throwableInitMethod = NULL;
static CallStack* dumpThreadStackTraceCallStack = NULL;
static sem_t dumpThreadStackTraceCallSemaphore;
#if defined(DARWIN)
static struct sigaction sigbusFallback;
#endif
static struct sigaction sigsegvFallback;

static void signalHandler_npe_so_nochaining(int signum, siginfo_t* info, void* context);
static void signalHandler_npe_so_chaining(int signum, siginfo_t* info, void* context);
static void signalHandler_dump_thread(int signum, siginfo_t* info, void* context);

#if defined(DARWIN)
// Weak stub for the function in vm/debug/src/debug.c. If librobovm-debug.a isn't
// linked in this version will be used and won't do anything.
void registerDarwinExceptionHandler(void) __attribute__ ((weak));
void registerDarwinExceptionHandler(void) {
}
#endif

jboolean rvmInitSignals(Env* env) {
    throwableInitMethod = rvmGetClassMethod(env, java_lang_Throwable, "init", "(Ljava/lang/Throwable;J)V");
    if (!throwableInitMethod) return FALSE;
    if (sem_init(&dumpThreadStackTraceCallSemaphore, 0, 0) != 0) {
        return FALSE;
    }
#if defined(DARWIN)
    registerDarwinExceptionHandler();
#endif
    return TRUE;
}

static struct sigaction create_sigaction(void (*f)(int, siginfo_t*, void*)) {
    struct sigaction sa;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_SIGINFO | SA_ONSTACK;
    sa.sa_sigaction = f;
    return sa;
}

static int installSignalHandlerIfNeeded(int signum, struct sigaction sa, struct sigaction* savedsa) {
    struct sigaction oldsa;

    sigaction(signum, NULL, &oldsa);

    if (oldsa.sa_sigaction != sa.sa_sigaction) {
        int res = sigaction(signum, &sa, NULL);
        if (res == 0 && savedsa) {
            *savedsa = oldsa;
        }
        return res;
    }
    return 0;
}

static jboolean installChainingSignals(Env* env) {
#if defined(DARWIN)
    // On Darwin SIGBUS is generated when dereferencing NULL pointers
    if (installSignalHandlerIfNeeded(SIGBUS, create_sigaction(&signalHandler_npe_so_chaining), &sigbusFallback) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }
#endif

    if (installSignalHandlerIfNeeded(SIGSEGV, create_sigaction(&signalHandler_npe_so_chaining), &sigsegvFallback) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }

    return TRUE;
}

static jboolean reinstallSavedSignals(Env* env, SavedSignals* savedSignals) {
    if (installSignalHandlerIfNeeded(DUMP_THREAD_STACK_TRACE_SIGNAL, create_sigaction(&signalHandler_dump_thread), NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }

    if (installSignalHandlerIfNeeded(BLOCKED_THREAD_SIGNAL, savedSignals->blockedThreadSignal, NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }

    return TRUE;
}

static jboolean installSignals(Env* env) {
#if defined(DARWIN)
    // On Darwin SIGBUS is generated when dereferencing NULL pointers
    if (installSignalHandlerIfNeeded(SIGBUS, create_sigaction(&signalHandler_npe_so_nochaining), NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }
#endif

    if (installSignalHandlerIfNeeded(SIGSEGV, create_sigaction(&signalHandler_npe_so_nochaining), NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }

    if (installSignalHandlerIfNeeded(DUMP_THREAD_STACK_TRACE_SIGNAL, create_sigaction(&signalHandler_dump_thread), NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        return FALSE;
    }

    int err;
    if ((err = pthread_sigmask(0, NULL, &env->currentThread->signalMask)) != 0) {
        rvmThrowInternalErrorErrno(env, err);
        rvmTearDownSignals(env);
        return FALSE;        
    }

    return TRUE;
}

jboolean rvmSetupSignals(Env* env) {
    if (!installSignals(env)) {
        return FALSE;
    }
    return TRUE;
}

void rvmRestoreSignalMask(Env* env) {
    pthread_sigmask(SIG_SETMASK, &env->currentThread->signalMask, NULL);
}

void rvmTearDownSignals(Env* env) {
}

void rvmInstallChainingSignals(Env* env) {
    static jboolean called = FALSE;
    if (called) {
        FATAL("rvmInstallChainingSignals() called twice");
        abort();
    }
    installChainingSignals(env);
    called = TRUE;
}

void rvmReinstallSavedSignals(Env* env, void* state) {
    SavedSignals* savedSignals = (SavedSignals*) state;
    reinstallSavedSignals(env, savedSignals);
    free(savedSignals);
}

void* rvmSaveSignals(Env* env) {
    SavedSignals* state = malloc(sizeof(SavedSignals));
    if (!state) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return state;
}

void dumpThreadStackTrace(Env* env, Thread* thread, CallStack* callStack) {
    // NOTE: This function must not be called concurrently. It uses global 
    // variables to transfer data to/from a signal handler.

    rvmAtomicStorePtr((void**) &dumpThreadStackTraceCallStack, callStack);
    if (pthread_kill(thread->pThread, DUMP_THREAD_STACK_TRACE_SIGNAL) != 0) {
        // The thread is probably not alive
        return;
    }

    while (sem_wait(&dumpThreadStackTraceCallSemaphore) == EINTR) {
    }
}

static inline void* getFramePointer(ucontext_t* context) {
#if defined(DARWIN)
#   if defined(RVM_X86)
        return (void*) (ptrdiff_t) context->uc_mcontext->__ss.__ebp;
#   elif defined(RVM_THUMBV7)
        return (void*) (ptrdiff_t) context->uc_mcontext->__ss.__r[7];
#   endif
#elif defined(LINUX)
#   if defined(RVM_X86)
        return (void*) (ptrdiff_t) context->uc_mcontext.gregs[REG_EBP];
#   endif
#endif
}

static inline void* getPC(ucontext_t* context) {
#if defined(DARWIN)
#   if defined(RVM_X86)
        return (void*) (ptrdiff_t) context->uc_mcontext->__ss.__eip;
#   elif defined(RVM_THUMBV7)
        return (void*) (ptrdiff_t) context->uc_mcontext->__ss.__pc;
#   endif
#elif defined(LINUX)
#   if defined(RVM_X86)
        return (void*) (ptrdiff_t) context->uc_mcontext.gregs[REG_EIP];
#   endif
#endif
}

static void signalHandler_npe_so(int signum, siginfo_t* info, void* context) {
    // SIGSEGV/SIGBUS are synchronous signals so we shouldn't have to worry about only calling
    // async-signal-safe functions here.
    Env* env = rvmGetEnv();
    if (env && rvmIsNonNativeFrame(env)) {
        // We now know the fault occurred in non-native code.
        void* faultAddr = info->si_addr;
        void* stackAddr = env->currentThread->stackAddr;
        Class* exClass = NULL;
        if (faultAddr < stackAddr && faultAddr >= (void*) (stackAddr - THREAD_STACK_GUARD_SIZE)) {
            // StackOverflowError
            exClass = java_lang_StackOverflowError;
        } else {
            // At least on Linux x86 it seems like si_addr isn't always 0x0 even
            // if a read of address 0x0 triggered SIGSEGV so we assume 
            // everything that isn't a stack overflow is a read of address 0x0
            // and throw NullPointerException.
            exClass = java_lang_NullPointerException;
        }

        if (exClass) {
            Frame fakeFrame;
            fakeFrame.prev = (Frame*) getFramePointer((ucontext_t*) context);
            fakeFrame.returnAddress = getPC((ucontext_t*) context);
            Object* throwable = NULL;
            CallStack* callStack = captureCallStackFromFrame(env, &fakeFrame);
            if (callStack) {
                throwable = rvmAllocateObject(env, exClass);
                if (throwable) {
                    rvmCallVoidClassMethod(env, exClass, throwableInitMethod, throwable, PTR_TO_LONG(callStack));
                    if (rvmExceptionCheck(env)) {
                        throwable = NULL;
                    }
                }
            }
            if (!throwable) {
                throwable = rvmExceptionClear(env);
            }
            rvmRaiseException(env, throwable); // Never returns!
        }
    }
}

// Signal handler used by default. Does not chain to the previously installed handler. Just delegates to SIG_DFL
// in case a SIGSEGV/SIGBUS is cused by something other than an NPE or SOE.
static void signalHandler_npe_so_nochaining(int signum, siginfo_t* info, void* context) {
    signalHandler_npe_so(signum, info, context);
    // If we come this far it means that the cause of the signal wasn't an NPE or SOE but something
    // fatal happened in native code. Delegate to the default handler.
    signal(signum, SIG_DFL);
    raise(signum);
}

// Signal handler which chains to the previous handler in case a SIGSEGV/SIGBUS is cused by something other than an NPE or SOE.
static void signalHandler_npe_so_chaining(int signum, siginfo_t* info, void* context) {
    signalHandler_npe_so(signum, info, context);
    // If we come this far it means that the cause of the signal wasn't an NPE or SOE but something
    // fatal happened in native code. Chained to the previous handler.
    struct sigaction* sa = &sigsegvFallback;
#if defined(DARWIN)
    if (signum == SIGBUS) {
        sa = &sigbusFallback;
    }
#endif
    if (sa->sa_flags & SA_SIGINFO) {
        sa->sa_sigaction(signum, info, context);
    } else if (sa->sa_handler != SIG_DFL) {
        sa->sa_handler(signum);
    } else {
        signal(signum, SIG_DFL);
        raise(signum);
    }
}

static void signalHandler_dump_thread(int signum, siginfo_t* info, void* context) {
    Env* env = rvmGetEnv();
    if (env) {
        Frame fakeFrame;
        if (rvmIsNonNativeFrame(env)) {
            // Signalled in non-native code
            fakeFrame.prev = (Frame*) getFramePointer((ucontext_t*) context);
            fakeFrame.returnAddress = getPC((ucontext_t*) context);
        } else {
            // The thread was signalled while in native code, possibly a system 
            // function. We cannot trust that this code uses proper frame 
            // pointers so we cannot use the context's frame pointer. The top
            // most GatewayFrame in env was pushed when native code was entered.
            // Use its frame as frame pointer.
            fakeFrame = *(Frame*) env->gatewayFrames->frameAddress;
        }

        captureCallStack(env, &fakeFrame, dumpThreadStackTraceCallStack, MAX_CALL_STACK_LENGTH);
    }
    sem_post(&dumpThreadStackTraceCallSemaphore);
}
