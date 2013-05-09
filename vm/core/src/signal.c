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

#define _GNU_SOURCE
#include <robovm.h>
#include <signal.h>
#if defined(DARWIN)
#   include <mach/mach.h>
#   include <mach/mach_error.h>
#   include <mach/exception.h>
#   include <mach/task.h>
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

#define DUMP_THREAD_STACK_TRACE_SIGNAL SIGUSR2

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

static InstanceField* stackStateField = NULL;
static CallStack* dumpThreadStackTraceCallStack = NULL;
static sem_t dumpThreadStackTraceCallSemaphore;

static void signalHandler_npe_so(int signum, siginfo_t* info, void* context);
static void signalHandler_dump_thread(int signum, siginfo_t* info, void* context);

#if defined(DARWIN)
/*
 * Install a mach exception handler which intercepts EXC_BAD_ACCESS and prevents GDB from
 * seeing it. If we don't do this GDB will not pass the EXC_BAD_ACCESS along to the OS so
 * that it can be converted into SIGBUS/SIGSEGV and handled by our signal handler. Instead
 * the EXC_BAD_ACCESS will just be raised again and again and again...
 * The code here has been inspired by the GC's code in os_dep.c and the mailing list post at
 * http://lists.apple.com/archives/darwin-dev/2006/Oct/msg00122.html.
 */
extern boolean_t exc_server(mach_msg_header_t* request, mach_msg_header_t* reply);
static mach_port_t mach_ex_port = MACH_PORT_NULL;

kern_return_t catch_exception_raise(mach_port_t exception_port, mach_port_t thread, mach_port_t task,
        exception_type_t exception, exception_data_t code, mach_msg_type_number_t code_count) __attribute__((visibility("default")));
/*
 * This is called by exc_server. exc_server uses dlsym to find this function so it must be public and exported during linking. The
 * AbstractTarget class in the compiler makes sure this is exported when linking.
 */
kern_return_t catch_exception_raise(mach_port_t exception_port, mach_port_t thread, mach_port_t task,
        exception_type_t exception, exception_data_t code, mach_msg_type_number_t code_count) {
    return KERN_FAILURE;
}

static void* exceptionHandlerEntryPoint(void* arg) {
    // mach_msg_server() never returns
    mach_msg_server(exc_server, sizeof(mach_msg_base_t) + 1024, mach_ex_port,  0);
    return NULL;
}

static void registerDarwinExceptionHandler(void) {
    // Create the Mach exception port
    mach_port_t self = mach_task_self();
    assert(mach_port_allocate(self, MACH_PORT_RIGHT_RECEIVE, &mach_ex_port) == KERN_SUCCESS);
    assert(mach_port_insert_right(self, mach_ex_port, mach_ex_port, MACH_MSG_TYPE_MAKE_SEND) == KERN_SUCCESS);
    
    // Create the thread which receives the exceptions
    pthread_t thread;
    pthread_attr_t attr;
    assert(!pthread_attr_init(&attr));
    assert(!pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED));
    assert(!pthread_create(&thread, &attr, exceptionHandlerEntryPoint, NULL));
    pthread_attr_destroy(&attr);
    
    assert(task_set_exception_ports(self, EXC_MASK_BAD_ACCESS, mach_ex_port, EXCEPTION_DEFAULT, MACHINE_THREAD_STATE) == KERN_SUCCESS);
}
#endif

jboolean rvmInitSignals(Env* env) {
    stackStateField = rvmGetInstanceField(env, java_lang_Throwable, "stackState", "J");
    if (!stackStateField) return FALSE;
    if (sem_init(&dumpThreadStackTraceCallSemaphore, 0, 0) != 0) {
        return FALSE;
    }
#if defined(DARWIN)
    registerDarwinExceptionHandler();
#endif
    return TRUE;
}

static jboolean installSignalHandlers(Env* env) {
    struct sigaction sa;

    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_SIGINFO | SA_ONSTACK;
    sa.sa_sigaction = &signalHandler_npe_so;

#if defined(DARWIN)
    // On Darwin SIGBUS is generated when dereferencing NULL pointers
    if (sigaction(SIGBUS, &sa, NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        rvmTearDownSignals(env);
        return FALSE;
    }
#endif

    if (sigaction(SIGSEGV, &sa, NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        rvmTearDownSignals(env);
        return FALSE;
    }

    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_SIGINFO | SA_ONSTACK;
    sa.sa_sigaction = &signalHandler_dump_thread;

    if (sigaction(DUMP_THREAD_STACK_TRACE_SIGNAL, &sa, NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        rvmTearDownSignals(env);
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
    if (!installSignalHandlers(env)) {
        return FALSE;
    }
    return TRUE;
}

void rvmRestoreSignalMask(Env* env) {
    pthread_sigmask(SIG_SETMASK, &env->currentThread->signalMask, NULL);
}

void rvmTearDownSignals(Env* env) {
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
    // rvmGetEnv() uses pthread_getspecific() which isn't listed as 
    // async-signal-safe. Others (e.g. mono) do this too so we assume it is safe 
    // in practice.
    Env* env = rvmGetEnv();
    if (env && rvmIsNonNativeFrame(env)) {
        // We now know the fault occurred in non-native code and not in our 
        // native code or in any non-async-signal-safe system function. It 
        // should be safe to do things here that would normally be unsafe to do
        // in a signal handler.
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
            Object* throwable = rvmAllocateObject(env, exClass);
            if (!throwable) {
                throwable = rvmExceptionClear(env);
            }
            Frame fakeFrame;
            fakeFrame.prev = (Frame*) getFramePointer((ucontext_t*) context);
            fakeFrame.returnAddress = getPC((ucontext_t*) context);
            CallStack* callStack = captureCallStackFromFrame(env, &fakeFrame);
            rvmSetLongInstanceFieldValue(env, throwable, stackStateField, PTR_TO_LONG(callStack));
            rvmRaiseException(env, throwable);
        }
    }

    struct sigaction sa;
    sa.sa_flags = 0;
    sa.sa_handler = SIG_DFL;
    sigaction(signum, &sa, NULL);
    kill(0, signum);
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
