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

jint rvmThrow(Env* env, Object* e) {
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
    return 0;
}

jint rvmThrowNew(Env* env, Class* clazz, const char* message) {
    Method* constructor = rvmGetInstanceMethod(env, clazz, "<init>", "(Ljava/lang/String;)V");
    if (!constructor) return 1;
    Object* string = NULL;
    // TODO: Check that clazz != NULL?
    if (message) {
        string = rvmNewStringUTF(env, message, -1);
        if (!string) return 2;
    }
    Object* e = rvmNewObject(env, clazz, constructor, string);
    if (!e) return 3;
    return rvmThrow(env, e);
}

jint rvmThrowOutOfMemoryError(Env* env) {
    return rvmThrowNew(env, java_lang_OutOfMemoryError, "");
}

jint rvmThrowNoClassDefFoundError(Env* env, const char* name) {
    // TODO: Message should look like "java.lang.NoClassDefFoundError: a/C"
    return rvmThrowNew(env, java_lang_NoClassDefFoundError, "");
}

jint rvmThrowLinkageError(Env* env) {
    return rvmThrowNew(env, java_lang_LinkageError, "");
}

jint rvmThrowIllegalAccessError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalAccessError, message);
}

jint rvmThrowIllegalAccessErrorClass(Env* env, Class* clazz, Class* caller) {
    // TODO: Message should look like ?
    return rvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint rvmThrowIllegalAccessErrorField(Env* env, Class* clazz, const char* name, const char* desc, Class* caller) {
    // TODO: Message should look like "java.lang.IllegalAccessError: tried to access field a.A.x from class b.B"
    return rvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint rvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, const char* name, const char* desc, Class* caller) {
    // TODO: Message should look like ?
    return rvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint rvmThrowNoSuchFieldError(Env* env, const char* name) {
    // TODO: Message should look like "java.lang.NoSuchFieldError: x"
    // TODO: Cache java.lang.NoSuchFieldError at startup
    return rvmThrowNew(env, java_lang_NoSuchFieldError, "");
}

jint rvmThrowNoSuchMethodError(Env* env, const char* name) {
    // TODO: Message should look like "java.lang.NoSuchMethodError: x"
    return rvmThrowNew(env, java_lang_NoSuchMethodError, "");
}

jint rvmThrowIncompatibleClassChangeError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, message);
}

jint rvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected static field a.C.x"
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint rvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected non-static field a.C.x"
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint rvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, const char* name, const char* desc) {
    // TODO: Message should look like ?
    return rvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint rvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass) {
    // TODO: Message should look like "java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String"
    // TODO: Cache java.lang.ClassCastException at startup
    return rvmThrowNew(env, java_lang_ClassCastException, "");
}

jint rvmThrowNullPointerException(Env* env) {
    return rvmThrowNew(env, java_lang_NullPointerException, "");
}

jint rvmThrowAbstractMethodError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_AbstractMethodError, message);
}

jint rvmThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    // TODO: Set index on exception
    return rvmThrowNew(env, java_lang_ArrayIndexOutOfBoundsException, "");
}

jint rvmThrowArrayStoreException(Env* env) {
    return rvmThrowNew(env, java_lang_ArrayStoreException, "");
}

jint rvmThrowClassNotFoundException(Env* env, const char* className) {
    char* msg = rvmToBinaryClassName(env, className);
    if (!msg) return 1;
    return rvmThrowNew(env, java_lang_ClassNotFoundException, msg);
}

jint rvmThrowNegativeArraySizeException(Env* env) {
    return rvmThrowNew(env, java_lang_NegativeArraySizeException, "");
}

jint rvmThrowUnsatisfiedLinkError(Env* env) {
    // TODO: Message should look like "java.lang.UnsatisfiedLinkError: Foo.nativeFunction()V"
    return rvmThrowNew(env, java_lang_UnsatisfiedLinkError, "");
}

jint rvmThrowIllegalArgumentException(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_IllegalArgumentException, message);
}

jint rvmThrowVerifyError(Env* env, const char* msg) {
    return rvmThrowNew(env, java_lang_VerifyError, msg);
}

jint rvmThrowArithmeticException(Env* env) {
    return rvmThrowNew(env, java_lang_ArithmeticException, NULL);
}

jint rvmThrowIllegalMonitorStateException(Env* env) {
    return rvmThrowNew(env, java_lang_IllegalMonitorStateException, NULL);
}

jint rvmThrowInterruptedException(Env* env) {
    return rvmThrowNew(env, java_lang_InterruptedException, NULL);
}

jint rvmThrowInstantiationError(Env* env, const char* message) {
    return rvmThrowNew(env, java_lang_InstantiationError, message);
}

