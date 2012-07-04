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

#define _GNU_SOURCE
#include <robovm.h>
#include <signal.h>
#include <errno.h>
#include "private.h"

#define LOG_TAG "core.signal"

#define ALT_STACK_SIZE (64 * 1024)

static InstanceField* stackStateField = NULL;

static void signalHandler(int signum, siginfo_t* info, void* context);

jboolean rvmInitSignals(Env* env) {
    stackStateField = rvmGetInstanceField(env, java_lang_Throwable, "stackState", "J");
    if (!stackStateField) return FALSE;
    return TRUE;
}

jboolean rvmSetupSignals(Env* env) {
    stack_t sigalt;
    struct sigaction sa;

    sigalt.ss_sp = rvmAllocateMemoryUncollectable(env, ALT_STACK_SIZE);
    if (sigalt.ss_sp == NULL) {
        return FALSE;
    }
    sigalt.ss_size = ALT_STACK_SIZE;
    sigalt.ss_flags = 0;
    if (sigaltstack(&sigalt, NULL) != 0) {
        rvmThrowInternalErrorErrno(env, errno);
        rvmTearDownSignals(env);
        return FALSE;
    }

    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_SIGINFO | SA_ONSTACK;
    sa.sa_sigaction = &signalHandler;

#if defined(DARWIN)
    // On Darwin SIGBUS is used for stack overflows
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

    pthread_sigmask(0, NULL, &env->currentThread->signalMask);

    return TRUE;
}

void rvmRestoreSignalMask(Env* env) {
    pthread_sigmask(SIG_SETMASK, &env->currentThread->signalMask, NULL);
}

void rvmTearDownSignals(Env* env) {
    stack_t sigalt;
    if (sigaltstack(NULL, &sigalt) == 0) {
        rvmFreeMemory(sigalt.ss_sp);
    }
}

static inline void* getFramePointer(ucontext_t* context) {
#if defined(DARWIN)
#   if defined(RVM_X86)
        return (ptrdiff_t) context->uc_mcontext->__ss.__ebp;
#   elif defined(RVM_THUMBV7)
        return (ptrdiff_t) context->uc_mcontext->__ss.__fp;
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
        return (ptrdiff_t) context->uc_mcontext->__ss.__eip;
#   elif defined(RVM_THUMBV7)
        return (ptrdiff_t) context->uc_mcontext->__ss.__pc;
#   endif
#elif defined(LINUX)
#   if defined(RVM_X86)
        return (void*) (ptrdiff_t) context->uc_mcontext.gregs[REG_EIP];
#   endif
#endif
}

static void signalHandler(int signum, siginfo_t* info, void* context) {
    Env* env = rvmGetEnv();
    if (env && rvmIsNonNativeFrame(env)) {
        void* faultAddr = info ? info->si_addr : NULL;
        void* stackAddr = env->currentThread->stackAddr;
        Class* exClass = NULL;
        if (!faultAddr) {
            // NullPointerException
            exClass = java_lang_NullPointerException;
        } else if (faultAddr < stackAddr && faultAddr >= (void*) (stackAddr - THREAD_STACK_GUARD_SIZE)) {
            // StackOverflowError
            exClass = java_lang_StackOverflowError;
        }

        if (exClass) {
            Object* throwable = rvmAllocateObject(env, exClass);
            if (!throwable) {
                throwable = rvmExceptionClear(env);
            }
            Frame fakeFrame;
            fakeFrame.prev = (Frame*) getFramePointer((ucontext_t*) context);
            fakeFrame.returnAddress = getPC((ucontext_t*) context);
            CallStack* callStack = rvmCaptureCallStack(env, &fakeFrame);
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
