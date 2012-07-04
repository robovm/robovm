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
#include <string.h>
#include <robovm.h>
#include <unwind.h>

jlong Java_java_lang_Throwable_nativeFillInStackTrace(Env* env, Object* thiz) {
    return PTR_TO_LONG(rvmCaptureCallStack(env, NULL));
}

ObjectArray* Java_java_lang_Throwable_nativeGetStackTrace(Env* env, Object* thiz, jlong stackState) {
    Class* java_lang_StackTraceElement = rvmFindClassUsingLoader(env, "java/lang/StackTraceElement", NULL);
    if (!java_lang_StackTraceElement) return NULL;

    CallStack* callStack = (CallStack*) LONG_TO_PTR(stackState);
    if (!callStack) return rvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);;

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

    // Count the number of methods remaining
    index = first;
    jint length = 0;
    while (rvmGetNextCallStackMethod(env, callStack, &index)) {
        length++;
    }

    ObjectArray* array = rvmNewObjectArray(env, length, java_lang_StackTraceElement, NULL, NULL);
    if (!array) return NULL;

    if (length > 0) {
        Method* steConstructor = rvmGetInstanceMethod(env, java_lang_StackTraceElement, "<init>", 
                                      "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V");
        if (!steConstructor) return NULL;

        jvalue args[4];
        index = first;
        jint i;
        for (i = 0; i < length; i++) {
            m = rvmGetNextCallStackMethod(env, callStack, &index);
            args[0].l = (jobject) m->clazz;
            args[1].l = (jobject) rvmNewStringUTF(env, m->name, -1);
            if (!args[1].l) return NULL;
            args[2].l = NULL; // TODO: File names
            args[3].i = METHOD_IS_NATIVE(m) ? -2 : -1; // TODO: Line numbers
            array->values[i] = rvmNewObjectA(env, java_lang_StackTraceElement, steConstructor, args);
            if (!array->values[i]) return NULL;
        }
    }

    return array;
}
