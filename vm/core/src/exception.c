/*
 * Copyright (C) 2012 RoboVM AB
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
#include <robovm.h>
#include <stddef.h>
#include <string.h>
#include "private.h"

#define LOG_TAG "core.exception"
#define THROW_FORMAT_BUF_SIZE 512

static InstanceField* stackStateField = NULL;
static Method* printStackTraceMethod = NULL;

jboolean rvmInitExceptions(Env* env) {
    stackStateField = rvmGetInstanceField(env, java_lang_Throwable, "stackState", "J");
    if (!stackStateField) return FALSE;
    printStackTraceMethod = rvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
    if (!printStackTraceMethod) return FALSE;
    return TRUE;
}

void rvmRaiseException(Env* env, Object* e) {
    if (env->throwable != e) {
        rvmThrow(env, e);
    }
    jboolean (*exceptionMatch)(Env*, TrycatchContext*) = env->vm->options->exceptionMatch;
    TrycatchContext* tc = env->trycatchContext;
    while (tc) {
        if (tc->sel != 0 && (tc->sel == CATCH_ALL_SEL || exceptionMatch(env, tc))) {
            rvmRestoreThreadSignalMask(env);
            rvmHookExceptionRaised(env, e, tc->prev? TRUE: FALSE);
            rvmTrycatchJump(tc);
            // unreachable
        }
        rvmTrycatchLeave(env);
        tc = env->trycatchContext;
    }

    /*
     * We only end up here if Java was called into from native without a
     * TrycatchContext being set up first. This only happens for @Callback
     * methods. The only sane thing to do here is to terminate the app. But
     * first we want to detach the current thread which will report the
     * uncaught exception to the uncaught exception handler.
     */
    env->gatewayFrames = NULL; // Needed to avoid the "Cannot detach thread when there are non native frames on the call stack" error
    rvmDetachCurrentThread(env->vm, TRUE, TRUE);
    rvmAbort("Unhandled exception (probably in a @Callback method called from native code): %s", e->clazz->name);
}

void rvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f) {
    // TODO: Write the stack trace to the FILE*
    fprintf(stderr, "Exception occurred: %s\n", e->clazz->name);
}

void rvmPrintStackTrace(Env* env, Object* throwable) {
    jvalue args[1];
    args[0].l = (jobject) throwable;
    rvmCallVoidInstanceMethodA(env, (Object*) env->currentThread, printStackTraceMethod, args);
}

void rvmThrow(Env* env, Object* e) {
    if (!env->vm->initialized) {
        rvmAbort("%s thrown during VM initialization", e && e->clazz ? e->clazz->name : "?");
    }

    // TODO: Check that e != NULL?
    if (env->throwable) {
        rvmAbort("rvmThrow() called with env->throwable already set");
    }
    if (IS_TRACE_ENABLED) {
        jlong stackState = rvmGetLongInstanceFieldValue(env, e, stackStateField);
        CallStack* callStack = (CallStack*) LONG_TO_PTR(stackState);
        if (!callStack || callStack->length == 0) {
            TRACEF("Throwing a %s with empty call stack", e->clazz->name);
        } else {
            TRACEF("Throwing a %s. Call stack:", e->clazz->name);
            CallStackFrame* frame;
            jint index = 0;
            while ((frame = rvmGetNextCallStackMethod(env, callStack, &index)) != NULL) {
                Method* m = frame->method;
                TRACEF("    %s.%s%s", m->clazz->name, m->name, m->desc);
            }
        }
    }
    env->throwable = e;
}

jboolean rvmThrowNew(Env* env, Class* clazz, const char* message) {
    if (!env->vm->initialized) {
        rvmAbort("%s thrown during VM initialization: %s", clazz ? clazz->name : "?", message);
    }

    Method* constructor = rvmGetInstanceMethod(env, clazz, "<init>", "(Ljava/lang/String;)V");
    if (!constructor) return FALSE;
    Object* string = NULL;
    // TODO: Check that clazz != NULL?
    if (message) {
        string = rvmNewStringUTF(env, message, -1);
        if (!string) return FALSE;
    }
    jvalue args[1];
    args[0].l = (jobject) string;
    Object* e = rvmNewObjectA(env, clazz, constructor, args);
    if (!e) return FALSE;
    rvmThrow(env, e);
    return TRUE;
}

jboolean rvmThrowNewf(Env* env, Class* clazz, const char* format, ...) {
    va_list ap;
    char message[THROW_FORMAT_BUF_SIZE];
    va_start(ap, format);
    vsnprintf(message, THROW_FORMAT_BUF_SIZE, format, ap);
    va_end(ap);
    return rvmThrowNew(env, clazz, message);
}

jboolean rvmThrowNewfv(Env* env, Class* clazz, const char* format, va_list ap) {
    char message[THROW_FORMAT_BUF_SIZE];
    vsnprintf(message, THROW_FORMAT_BUF_SIZE, format, ap);
    return rvmThrowNew(env, clazz, message);
}

jboolean rvmThrowInternalErrorErrno(Env* env, int errnum) {
    char message[THROW_FORMAT_BUF_SIZE];
    if (strerror_r(errnum, message, THROW_FORMAT_BUF_SIZE) == 0) {
        return rvmThrowNew(env, java_lang_InternalError, message);
    } else {
        return rvmThrowNew(env, java_lang_InternalError, NULL);
    }
}

jboolean rvmThrowOutOfMemoryError(Env* env) {
    // Don't run the constructor on OutOfMemoryError instances since that will
    // likely cause more OOM.
    Object* e = rvmAllocateObject(env, java_lang_OutOfMemoryError);
    if (!e) {
        return FALSE;
    }
    rvmThrow(env, e);
    return TRUE;
}

jboolean rvmThrowNoClassDefFoundError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_NoClassDefFoundError, message);
}

jboolean rvmThrowLinkageError(Env* env) {
    return rvmThrowNew(env, java_lang_LinkageError, "");
}

jboolean rvmThrowIllegalAccessError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalAccessError, message);
}

jboolean rvmThrowNoSuchFieldError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_NoSuchFieldError, message);
}

jboolean rvmThrowNoSuchMethodError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_NoSuchMethodError, message);
}

jboolean rvmThrowIncompatibleClassChangeError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, message);
}

jboolean rvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected static field a.C.x"
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jboolean rvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected non-static field a.C.x"
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jboolean rvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like ?
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jboolean rvmThrowClassCastException(Env* env, Class* expectedType, Class* actualType) {
    const char* expectedTypeName = rvmGetHumanReadableClassName(env, expectedType);
    if (!expectedTypeName) return FALSE;
    const char* actualTypeName = rvmGetHumanReadableClassName(env, actualType);
    if (!actualTypeName) return FALSE;
    return rvmThrowNewf(env, java_lang_ClassCastException, "%s cannot be cast to %s", actualTypeName, expectedTypeName);
}

jboolean rvmThrowNullPointerException(Env* env) {
    return rvmThrowNew(env, java_lang_NullPointerException, NULL);
}

jboolean rvmThrowAbstractMethodError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_AbstractMethodError, message);
}

jboolean rvmThrowArrayIndexOutOfBoundsException(Env* env, jint length, jint index) {
    return rvmThrowNewf(env, java_lang_ArrayIndexOutOfBoundsException, "length=%d; index=%d", length, index);
}

jboolean rvmThrowArrayStoreException(Env* env, Class* elemType, Class* arrayType) {
    const char* elemTypeName = rvmGetHumanReadableClassName(env, elemType);
    if (!elemTypeName) return FALSE;
    const char* arrayTypeName = rvmGetHumanReadableClassName(env, arrayType);
    if (!arrayTypeName) return FALSE;
    return rvmThrowNewf(env, java_lang_ArrayStoreException, 
        "%s cannot be stored in an array of type %s", elemTypeName, arrayTypeName);
}

jboolean rvmThrowClassNotFoundException(Env* env, const char* className) {
    char* msg = rvmToBinaryClassName(env, className);
    if (!msg) return FALSE;
    return rvmThrowNew(env, java_lang_ClassNotFoundException, msg);
}

jboolean rvmThrowNegativeArraySizeException(Env* env) {
    return rvmThrowNew(env, java_lang_NegativeArraySizeException, "");
}

jboolean rvmThrowUnsatisfiedLinkError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_UnsatisfiedLinkError, message);
}

jboolean rvmThrowIllegalArgumentException(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalArgumentException, message);
}

jboolean rvmThrowArithmeticException(Env* env) {
    return rvmThrowNew(env, java_lang_ArithmeticException, NULL);
}

jboolean rvmThrowIllegalMonitorStateException(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalMonitorStateException, message);
}

jboolean rvmThrowInterruptedException(Env* env) {
    return rvmThrowNew(env, java_lang_InterruptedException, NULL);
}

jboolean rvmThrowIllegalStateException(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalStateException, message);
}

jboolean rvmThrowInstantiationError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_InstantiationError, message);
}

