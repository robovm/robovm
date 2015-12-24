/*
 * Copyright (C) 2015 RoboVM AB
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

#define LOG_TAG "rt"

// NOTE: The compiler sorts fields. References first, then by alignment and then by name.
// So the order of the fields here don't match the order in Thread.java
typedef struct {
  Object object;
  Object* contextClassLoader;
  Object* group;
  Object* inheritableValues;
  Object* interruptActions;
  Object* localValues;
  Object* lock;
  Object* name;
  Object* parkBlocker;
  Object* target;
  Object* uncaughtHandler;
#if defined(RVM_THUMBV7)
  jlong id __attribute__ ((aligned (8))); // The compiler 8-byte aligns all long fields on ARM 32-bit.
#else
  jlong id;
#endif
  jlong stackSize;
  /*volatile*/ jlong threadPtr; // Points to the Thread
  jint parkState;
  jint priority;
  jboolean daemon;
  jboolean started;
} JavaThread;

// NOTE: The compiler sorts fields by type (ref, volatile long, double, long, float, int, char, short, boolean, byte) and then by name
// so the order of the fields here don't match the order in ClassLoader.java
typedef struct ClassLoader ClassLoader;
struct ClassLoader {
  Object object;
  Object* packages;
  ClassLoader* parent;
};


extern int registerCoreLibrariesJni(JNIEnv* env);

LAZY_CLASS(class_java_lang_String, "java/lang/String");
LAZY_CLASS(class_java_lang_Thread, "java/lang/Thread");
LAZY_INSTANCE_METHOD(method_java_lang_String_init, class_java_lang_String, "<init>", "(II[C)V");
LAZY_INSTANCE_METHOD(method_java_lang_Thread_init, class_java_lang_Thread, "<init>", "(JLjava/lang/String;Ljava/lang/ThreadGroup;Z)V");
LAZY_INSTANCE_FIELD(field_java_lang_String_value, class_java_lang_String, "value", "[C");
LAZY_INSTANCE_FIELD(field_java_lang_String_offset, class_java_lang_String, "offset", "I");
LAZY_INSTANCE_FIELD(field_java_lang_String_count, class_java_lang_String, "count", "I");

Object* rvmRTNewString(Env* env, CharArray* value, jint offset, jint length) {
    jvalue args[3];
    args[0].i = offset;
    args[1].i = length;
    args[2].l = (jobject) value;
    return rvmNewObjectA(env, class_java_lang_String(env), method_java_lang_String_init(env), args);
}

jint rvmRTGetStringLength(Env* env, Object* str) {
    return rvmGetIntInstanceFieldValue(env, str, field_java_lang_String_count(env));
}

jchar* rvmRTGetStringChars(Env* env, Object* str) {
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, field_java_lang_String_value(env));
    jint offset = rvmGetIntInstanceFieldValue(env, str, field_java_lang_String_offset(env));
    return value->values + offset;
}

void rvmRTInitAttachedThread(Env* env, Object* threadObj, Thread* thread, Object* threadName, Object* group, jboolean daemon) {
    ((JavaThread*) threadObj)->threadPtr = PTR_TO_LONG(thread);
    rvmCallNonvirtualVoidInstanceMethod(env, (Object*) threadObj, method_java_lang_Thread_init(env), PTR_TO_LONG(thread), threadName, group, daemon);
}

void rvmRTSetThreadContextClassLoader(Env* env, Object* threadObj, Object* classLoader) {
    ((JavaThread*) threadObj)->contextClassLoader = classLoader;
}

Thread* rvmRTGetNativeThread(Env* env, Object* threadObj) {
    return (Thread*) LONG_TO_PTR(((JavaThread*) threadObj)->threadPtr);
}

void rvmRTClearNativeThread(Env* env, Object* threadObj) {
    rvmAtomicStoreLong(&((JavaThread*) threadObj)->threadPtr, 0);
}

void rvmRTSetNativeThread(Env* env, Object* threadObj, Thread* thread) {
    ((JavaThread*) threadObj)->threadPtr = PTR_TO_LONG(thread);
}

Object* rvmRTGetThreadGroup(Env* env, Object* threadObj) {
    return ((JavaThread*) threadObj)->group;
}

jboolean rvmRTIsDaemonThread(Env* env, Object* threadObj) {
    return ((JavaThread*) threadObj)->daemon;
}

jint rvmRTGetThreadPriority(Env* env, Object* threadObj) {
    return ((JavaThread*) threadObj)->priority;
}

jint rvmRTGetThreadStackSize(Env* env, Object* threadObj) {
    return ((JavaThread*) threadObj)->stackSize;
}

jlong rvmRTGetThreadId(Env* env, Object* threadObj) {
    return ((JavaThread*) threadObj)->id;
}

void rvmRTResumeJoiningThreads(Env* env, Object* threadObj) {
    // Notify anyone waiting on this thread (using Thread.join())
    Object* lock = ((JavaThread*) threadObj)->lock;
    rvmLockObject(env, lock);
    rvmObjectNotifyAll(env, lock);
    rvmUnlockObject(env, lock);
}

Object* rvmRTGetParentClassLoader(Env* env, Object* classLoader) {
    return (Object*) ((ClassLoader*) classLoader)->parent;
}

const char* rvmRTGetName(void) {
    return "Android";
}

jboolean rvmRTInit(Env* env) {
    registerCoreLibrariesJni((JNIEnv*) env);
    return TRUE;
}
