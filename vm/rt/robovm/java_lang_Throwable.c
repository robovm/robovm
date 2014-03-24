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
#include <string.h>
#include <robovm.h>

jlong Java_java_lang_Throwable_nativeFillInStackTrace(Env* env, Object* thiz) {
    if (rvmIsCriticalOutOfMemoryError(env, thiz)) {
        // nativeFillInStackTrace() was called on the shared criticalOutOfMemoryError. 
        // Don't try to capture the call stack since it will most likely just 
        // lead to another OOM and more recursion.
        return 0;
    }
    return PTR_TO_LONG(rvmCaptureCallStack(env));
}

ObjectArray* Java_java_lang_Throwable_nativeGetStackTrace(Env* env, Object* thiz, jlong stackState) {
    CallStack* callStack = (CallStack*) LONG_TO_PTR(stackState);
    if (!callStack) {
        return rvmCallStackToStackTraceElements(env, NULL, 0);
    }

    jint index = 0;
    jint first = 0;
    Method* m = rvmGetNextCallStackMethod(env, callStack, &index);
    if (m && m->clazz == java_lang_Throwable && !strcmp(m->name, "nativeFillInStackTrace")) {
        // Skip Throwable.nativeFillInStackTrace()
        rvmGetNextCallStackMethod(env, callStack, &index); // Skip Throwable.fillInStackTrace()
        m = rvmGetNextCallStackMethod(env, callStack, &index);
        first = index;
        if (m) {
            Class* clazz = m->clazz;
            if (clazz == java_lang_Throwable && METHOD_IS_CONSTRUCTOR(m)) {
                // fillInStackTrace() was called from the constructor of Throwable
                // Skip all constructors until the constructor of thiz->clazz
                Class* superclass = java_lang_Object;
                while (m && METHOD_IS_CONSTRUCTOR(m) && clazz != thiz->clazz && clazz->superclass == superclass) {
                    m = rvmGetNextCallStackMethod(env, callStack, &index);
                    if (m && m->clazz != clazz) {
                        superclass = clazz;
                        clazz = m->clazz;
                    }
                    first = index - 1;
                }
                // We're now at the constructor of thiz->clazz which called super(). 
                // Skip all constructors belonging to thiz->clazz to get to the method which created the throwable
                while (m && METHOD_IS_CONSTRUCTOR(m) && clazz == thiz->clazz) {
                    m = rvmGetNextCallStackMethod(env, callStack, &index);
                    if (m) clazz = m->clazz;
                    first = index - 1;
                }
            }
        }
    }

    return rvmCallStackToStackTraceElements(env, callStack, first);
}
