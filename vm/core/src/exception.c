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
#include <robovm.h>
#include <stddef.h>
#include "private.h"

#define LOG_TAG "core.exception"
#define THROW_FORMAT_BUF_SIZE 512

void rvmRaiseException(Env* env, Object* e) {
    if (env->throwable != e) {
        rvmThrow(env, e);
    }
    jint result = unwindRaiseException(env);
    if (result == UNWIND_UNHANDLED_EXCEPTION) {
        rvmAbort("Unhandled exception: %s", e->clazz->name);
    }
    rvmAbort("Fatal error in exception handler: %d", result);
}

void rvmReraiseException(Env* env, void* exInfo) {
    jint result = unwindReraiseException(env, exInfo);
    if (result == UNWIND_UNHANDLED_EXCEPTION) {
        rvmAbort("Unhandled exception: %s", env->throwable->clazz->name);
    }
    rvmAbort("Fatal error in exception handler: %d", result);
}

jboolean rvmExceptionCheck(Env* env) {
    return env->throwable ? TRUE : FALSE;
}

Object* rvmExceptionOccurred(Env* env) {
    return env->throwable;
}

void rvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f) {
    // TODO: Write the stack trace to the FILE*
    fprintf(stderr, "Exception occurred: %s\n", e->clazz->name);
}

Object* rvmExceptionClear(Env* env) {
    Object* e = env->throwable;
    env->throwable = NULL;
    return e;
}

void rvmThrow(Env* env, Object* e) {
    // TODO: Check that e != NULL?
    if (env->throwable) {
        rvmAbort("rvmThrow() called with env->throwable already set");
    }
    if (IS_TRACE_ENABLED) {
        InstanceField* field = rvmGetInstanceField(env, java_lang_Throwable, "stackState", "J");
        jlong stackState = rvmGetLongInstanceFieldValue(env, e, field);
        CallStackEntry* first = (CallStackEntry*) LONG_TO_PTR(stackState);
        if (!first) {
            TRACEF("Throwing a %s with empty call stack", e->clazz->name);
        } else {
            TRACEF("Throwing a %s. Call stack:", e->clazz->name);
            while (first) {
                Method* m = first->method;
                TRACEF("    %s.%s%s", m->clazz->name, m->name, m->desc);
                first = first->next;
            }
        }
    }
    env->throwable = e;
}

jboolean rvmThrowNew(Env* env, Class* clazz, const char* message) {
    Method* constructor = rvmGetInstanceMethod(env, clazz, "<init>", "(Ljava/lang/String;)V");
    if (!constructor) return FALSE;
    Object* string = NULL;
    // TODO: Check that clazz != NULL?
    if (message) {
        string = rvmNewStringUTF(env, message, -1);
        if (!string) return FALSE;
    }
    Object* e = rvmNewObject(env, clazz, constructor, string);
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

jboolean rvmThrowOutOfMemoryError(Env* env) {
    return rvmThrowNew(env, java_lang_OutOfMemoryError, NULL);
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

jboolean rvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass) {
    // TODO: Message should look like "java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String"
    return rvmThrowNew(env, java_lang_ClassCastException, "");
}

jboolean rvmThrowNullPointerException(Env* env) {
    return rvmThrowNew(env, java_lang_NullPointerException, "");
}

jboolean rvmThrowAbstractMethodError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_AbstractMethodError, message);
}

jboolean rvmThrowArrayIndexOutOfBoundsException(Env* env, jint length, jint index) {
    return rvmThrowNewf(env, java_lang_ArrayIndexOutOfBoundsException, "length=%d; index=%d", length, index);
}

jboolean rvmThrowArrayStoreException(Env* env) {
    return rvmThrowNew(env, java_lang_ArrayStoreException, "");
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
    return rvmThrowNew(env, java_lang_UnsatisfiedLinkError, "");
}

jboolean rvmThrowIllegalArgumentException(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalArgumentException, message);
}

jboolean rvmThrowArithmeticException(Env* env) {
    return rvmThrowNew(env, java_lang_ArithmeticException, NULL);
}

jboolean rvmThrowIllegalMonitorStateException(Env* env) {
    return rvmThrowNew(env, java_lang_IllegalMonitorStateException, NULL);
}

jboolean rvmThrowInterruptedException(Env* env) {
    return rvmThrowNew(env, java_lang_InterruptedException, NULL);
}

jboolean rvmThrowInstantiationError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_InstantiationError, message);
}

