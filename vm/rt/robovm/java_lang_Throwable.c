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
    CallStackFrame* frame = rvmGetNextCallStackMethod(env, callStack, &index);
    if (frame && frame->method->clazz == java_lang_Throwable && !strcmp(frame->method->name, "nativeFillInStackTrace")) {
        // Skip Throwable.nativeFillInStackTrace()
        rvmGetNextCallStackMethod(env, callStack, &index); // Skip Throwable.fillInStackTrace()
        frame = rvmGetNextCallStackMethod(env, callStack, &index);
        first = index;
        if (frame) {
            Class* clazz = frame->method->clazz;
            if (clazz == java_lang_Throwable && METHOD_IS_CONSTRUCTOR(frame->method)) {
                // fillInStackTrace() was called from the constructor of Throwable
                // Skip all constructors until the constructor of thiz->clazz
                Class* superclass = java_lang_Object;
                while (frame && METHOD_IS_CONSTRUCTOR(frame->method) && clazz != thiz->clazz && clazz->superclass == superclass) {
                    frame = rvmGetNextCallStackMethod(env, callStack, &index);
                    if (frame && frame->method->clazz != clazz) {
                        superclass = clazz;
                        clazz = frame->method->clazz;
                    }
                    first = index - 1;
                }
                // We're now at the constructor of thiz->clazz which called super(). 
                // Skip all constructors belonging to thiz->clazz to get to the method which created the throwable
                while (frame && METHOD_IS_CONSTRUCTOR(frame->method) && clazz == thiz->clazz) {
                    frame = rvmGetNextCallStackMethod(env, callStack, &index);
                    if (frame) clazz = frame->method->clazz;
                    first = index - 1;
                }
            }
        }
    }

    return rvmCallStackToStackTraceElements(env, callStack, first);
}
