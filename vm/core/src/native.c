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
#include <robovm.h>
#include <string.h>

extern struct JNINativeInterface_ jni;
extern struct JNIInvokeInterface_ javaVM;

void rvmInitJavaVM(VM* vm) {
    vm->javaVM = &javaVM;
}

void rvmInitJNIEnv(Env* env) {
    env->jni = &jni;
}

static void throwUnsupportedOperationException(Env* env, char* msg) {
    Class* clazz = rvmFindClassUsingLoader(env, "java/lang/UnsupportedOperationException", NULL);
    if (!clazz) return;
    rvmThrowNew(env, clazz, msg);
}

static jint DestroyJavaVM(JavaVM* vm) {
    return JNI_ERR;
}

static jint AttachCurrentThread(JavaVM* vm, void** penv, void* args) {
    char* name = NULL;
    Object* group = NULL;
    if (args) {
        name = ((JavaVMAttachArgs*) args)->name;
        group = (Object*) ((JavaVMAttachArgs*) group)->name;
    }
    return rvmAttachCurrentThread((VM*) vm, (Env**) penv, name, group);
}

static jint DetachCurrentThread(JavaVM* vm) {
    return rvmDetachCurrentThread((VM*) vm, TRUE, TRUE);
}

static jint GetEnv(JavaVM* vm, void** penv, jint ver) {
    // TODO: Check version?
    Env* env = rvmGetEnv();
    if (env) {
        *penv = env;
        return JNI_OK;
    }
    return JNI_EDETACHED;
}

static jint AttachCurrentThreadAsDaemon(JavaVM* vm, void** penv, void* args) {
    char* name = NULL;
    Object* group = NULL;
    if (args) {
        name = ((JavaVMAttachArgs*) args)->name;
        group = (Object*) ((JavaVMAttachArgs*) group)->name;
    }
    return rvmAttachCurrentThreadAsDaemon((VM*) vm, (Env**) penv, name, group);
}

static jint GetVersion(JNIEnv* env) {
    return JNI_VERSION_1_4;
}

static jclass DefineClass(JNIEnv* env, const char* name, jobject loader, const jbyte* buf, jsize len) {
    throwUnsupportedOperationException((Env*) env, "DefineClass");
    return NULL;
}

static jclass FindClass(JNIEnv* env, const char* name) {
    Class* clazz = rvmFindClass((Env*) env, (char*) name);
    if (!clazz) {
        return NULL;
    }
    if (((Env*) env)->vm->initialized) {
        rvmInitialize((Env*) env, clazz);
        if (rvmExceptionOccurred((Env*) env)) {
            return NULL;
        }
    }
    return (jclass) clazz;
}

static jmethodID FromReflectedMethod(JNIEnv* env, jobject method) {
    throwUnsupportedOperationException((Env*) env, "FromReflectedMethod");
    return NULL;
}

static jfieldID FromReflectedField(JNIEnv* env, jobject field) {
    throwUnsupportedOperationException((Env*) env, "FromReflectedField");
    return NULL;
}

static jobject ToReflectedMethod(JNIEnv* env, jclass cls, jmethodID methodID, jboolean isStatic) {
    throwUnsupportedOperationException((Env*) env, "ToReflectedMethod");
    return NULL;
}

static jweak NewWeakGlobalRef(JNIEnv* env, jobject obj) {
    throwUnsupportedOperationException((Env*) env, "NewWeakGlobalRef");
    return NULL;
}

static void DeleteWeakGlobalRef(JNIEnv* env, jweak obj) {
    throwUnsupportedOperationException((Env*) env, "DeleteWeakGlobalRef");
}

static jint GetJavaVM(JNIEnv* env, JavaVM** vm) {
    *vm = (JavaVM*) ((Env*) env)->vm;
    return 0;
}

static jint RegisterNatives(JNIEnv* env, jclass clazz, const JNINativeMethod* methods, jint nMethods) {
    NativeMethod* nativeMethods[nMethods];
    jint i;
    for (i = 0; i < nMethods; i++) {
        nativeMethods[i] = (NativeMethod*) rvmGetMethod((Env*) env, (Class*) clazz, methods[i].name, methods[i].signature);
        if (nativeMethods[i] == NULL || !METHOD_IS_NATIVE(&nativeMethods[i]->method)) {
            rvmThrowNoSuchMethodError((Env*) env, methods[i].name);
            return JNI_ERR;
        }
    }
    for (i = 0; i < nMethods; i++) {
        if (!rvmRegisterNative((Env*) env, nativeMethods[i], methods[i].fnPtr)) {
            return JNI_ERR;
        }
    }
    return 0;
}

static jint UnregisterNatives(JNIEnv* env, jclass clazz) {
    throwUnsupportedOperationException((Env*) env, "UnregisterNatives");
    return -1;
}

static jclass GetSuperclass(JNIEnv* env, jclass sub) {
    return (jclass) ((Class*) sub)->superclass;
}

static jboolean IsAssignableFrom(JNIEnv* env, jclass sub, jclass sup) {
    return rvmIsAssignableFrom((Env*) env, (Class*) sub, (Class*) sup);
}

static jobject ToReflectedField(JNIEnv* env, jclass cls, jfieldID fieldID, jboolean isStatic) {
    throwUnsupportedOperationException((Env*) env, "ToReflectedField");
    return NULL;
}

static jint Throw(JNIEnv* env, jthrowable obj) {
    rvmThrow((Env*) env, (Object*) obj);
    return 0;
}

static jint ThrowNew(JNIEnv* env, jclass clazz, const char* msg) {
    return rvmThrowNew((Env*) env, (Class*) clazz, msg) ? 0 : -1;
}

static jthrowable ExceptionOccurred(JNIEnv* env) {
    return (jthrowable) rvmExceptionOccurred((Env*) env);
}

static void ExceptionDescribe(JNIEnv* env) {
    // TODO: Implement me properly
    Object* e = rvmExceptionOccurred((Env*) env);
    rvmExceptionPrintStackTrace((Env*) env, e, stderr);
}

static void ExceptionClear(JNIEnv* env) {
    rvmExceptionClear((Env*) env);
}

static void FatalError(JNIEnv* env, const char* msg) {
    rvmAbort((char*) msg);
}

static jint PushLocalFrame(JNIEnv* env, jint cap) {
    return 0;
}

static jobject PopLocalFrame(JNIEnv* env, jobject res) {
    return res;
}

static jobject NewGlobalRef(JNIEnv* env, jobject lobj) {
    return lobj;
}

static void DeleteGlobalRef(JNIEnv* env, jobject gref) {
}

static void DeleteLocalRef(JNIEnv* env, jobject obj) {
}

static jboolean IsSameObject(JNIEnv* env, jobject obj1, jobject obj2) {
    return obj1 == obj2 ? TRUE : FALSE;
}

static jobject NewLocalRef(JNIEnv* env, jobject ref) {
    return ref;
}

static jint EnsureLocalCapacity(JNIEnv* env, jint capacity) {
    return 0;
}

static jobject AllocObject(JNIEnv* env, jclass clazz) {
    return (jobject) rvmAllocateObject((Env*) env, (Class*) clazz);
}

static jobject NewObjectV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return (jobject) rvmNewObjectV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jobject NewObjectA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return (jobject) rvmNewObjectA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jobject NewObject(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jobject o = NewObjectV(env, clazz, methodID, args);
    va_end(args);
    return o;
}

static jclass GetObjectClass(JNIEnv* env, jobject obj) {
    return (jclass) ((Object*) obj)->clazz;
}

static jboolean IsInstanceOf(JNIEnv* env, jobject obj, jclass clazz) {
    return rvmIsInstanceOf((Env*) env, (Object*) obj, (Class*) clazz);
}

static jmethodID GetMethodID(JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return (jmethodID) rvmGetInstanceMethod((Env*) env, (Class*) clazz, (char*) name, (char*) sig);
}

static jobject CallObjectMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return (jobject) rvmCallObjectInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jobject CallObjectMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return (jobject) rvmCallObjectInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jobject CallObjectMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jobject o = CallObjectMethodV(env, obj, methodID, args);
    va_end(args);
    return o;
}

static jboolean CallBooleanMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallBooleanInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jboolean CallBooleanMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue*  args) {
    return rvmCallBooleanInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jboolean CallBooleanMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jboolean b = CallBooleanMethodV(env, obj, methodID, args);
    va_end(args);
    return b;
}

static jbyte CallByteMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallByteInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jbyte CallByteMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallByteInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jbyte CallByteMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jbyte b = CallByteMethodV(env, obj, methodID, args);
    va_end(args);
    return b;
}

static jchar CallCharMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallCharInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jchar CallCharMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallCharInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jchar CallCharMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jchar c = CallCharMethodV(env, obj, methodID, args);
    va_end(args);
    return c;
}

static jshort CallShortMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallShortInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jshort CallShortMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallShortInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jshort CallShortMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jshort s = CallShortMethodV(env, obj, methodID, args);
    va_end(args);
    return s;
}

static jint CallIntMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallIntInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jint CallIntMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallIntInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jint CallIntMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jint i = CallIntMethodV(env, obj, methodID, args);
    va_end(args);
    return i;
}

static jlong CallLongMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallLongInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jlong CallLongMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallLongInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jlong CallLongMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jlong l = CallLongMethodV(env, obj, methodID, args);
    va_end(args);
    return l;
}

static jfloat CallFloatMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallFloatInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jfloat CallFloatMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallFloatInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jfloat CallFloatMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jfloat f = CallFloatMethodV(env, obj, methodID, args);
    va_end(args);
    return f;
}

static jdouble CallDoubleMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    return rvmCallDoubleInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jdouble CallDoubleMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue* args) {
    return rvmCallDoubleInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jdouble CallDoubleMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jdouble d = CallDoubleMethodV(env, obj, methodID, args);
    va_end(args);
    return d;
}

static void CallVoidMethodV(JNIEnv* env, jobject obj, jmethodID methodID, va_list args) {
    rvmCallVoidInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static void CallVoidMethodA(JNIEnv* env, jobject obj, jmethodID methodID, jvalue*  args) {
    rvmCallVoidInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static void CallVoidMethod(JNIEnv* env, jobject obj, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    CallVoidMethodV(env, obj, methodID, args);
    va_end(args);
}

static jobject CallNonvirtualObjectMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return (jobject) rvmCallNonvirtualObjectInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jobject CallNonvirtualObjectMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue*  args) {
    return (jobject) rvmCallNonvirtualObjectInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jobject CallNonvirtualObjectMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jobject o = CallNonvirtualObjectMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return o;
}

static jboolean CallNonvirtualBooleanMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualBooleanInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jboolean CallNonvirtualBooleanMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue*  args) {
    return rvmCallNonvirtualBooleanInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jboolean CallNonvirtualBooleanMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jboolean b = CallNonvirtualBooleanMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return b;
}

static jbyte CallNonvirtualByteMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualByteInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jbyte CallNonvirtualByteMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualByteInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jbyte CallNonvirtualByteMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jbyte b = CallNonvirtualByteMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return b;
}

static jchar CallNonvirtualCharMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualCharInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jchar CallNonvirtualCharMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualCharInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jchar CallNonvirtualCharMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jchar c = CallNonvirtualCharMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return c;
}

static jshort CallNonvirtualShortMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualShortInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jshort CallNonvirtualShortMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualShortInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jshort CallNonvirtualShortMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jshort s = CallNonvirtualShortMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return s;
}

static jint CallNonvirtualIntMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualIntInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jint CallNonvirtualIntMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualIntInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jint CallNonvirtualIntMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jint i = CallNonvirtualIntMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return i;
}

static jlong CallNonvirtualLongMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualLongInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jlong CallNonvirtualLongMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualLongInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jlong CallNonvirtualLongMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jlong l = CallNonvirtualLongMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return l;
}

static jfloat CallNonvirtualFloatMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualFloatInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jfloat CallNonvirtualFloatMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualFloatInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jfloat CallNonvirtualFloatMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jfloat f = CallNonvirtualFloatMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return f;
}

static jdouble CallNonvirtualDoubleMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallNonvirtualDoubleInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jdouble CallNonvirtualDoubleMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallNonvirtualDoubleInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static jdouble CallNonvirtualDoubleMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jdouble d = CallNonvirtualDoubleMethodV(env, obj, clazz, methodID, args);
    va_end(args);
    return d;
}

static void CallNonvirtualVoidMethodV(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, va_list args) {
    rvmCallNonvirtualVoidInstanceMethodV((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static void CallNonvirtualVoidMethodA(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, jvalue*  args) {
    rvmCallNonvirtualVoidInstanceMethodA((Env*) env, (Object*) obj, (Method*) methodID, args);
}

static void CallNonvirtualVoidMethod(JNIEnv* env, jobject obj, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    CallNonvirtualVoidMethodV(env, obj, clazz, methodID, args);
    va_end(args);
}

static jfieldID GetFieldID(JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return (jfieldID) rvmGetInstanceField((Env*) env, (Class*) clazz, (char*) name, (char*) sig);
}

static jobject GetObjectField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return (jobject) rvmGetObjectInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jboolean GetBooleanField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetBooleanInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jbyte GetByteField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetByteInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jchar GetCharField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetCharInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jshort GetShortField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetShortInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jint GetIntField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetIntInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jlong GetLongField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetLongInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jfloat GetFloatField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetFloatInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static jdouble GetDoubleField(JNIEnv* env, jobject obj, jfieldID fieldID) {
    return rvmGetDoubleInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID);
}

static void SetObjectField(JNIEnv* env, jobject obj, jfieldID fieldID, jobject val) {
    rvmSetObjectInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, (Object*) val);
}

static void SetBooleanField(JNIEnv* env, jobject obj, jfieldID fieldID, jboolean val) {
    rvmSetBooleanInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetByteField(JNIEnv* env, jobject obj, jfieldID fieldID, jbyte val) {
    rvmSetByteInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetCharField(JNIEnv* env, jobject obj, jfieldID fieldID, jchar val) {
    rvmSetCharInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetShortField(JNIEnv* env, jobject obj, jfieldID fieldID, jshort val) {
    rvmSetShortInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetIntField(JNIEnv* env, jobject obj, jfieldID fieldID, jint val) {
    rvmSetIntInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetLongField(JNIEnv* env, jobject obj, jfieldID fieldID, jlong val) {
    rvmSetLongInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetFloatField(JNIEnv* env, jobject obj, jfieldID fieldID, jfloat val) {
    rvmSetFloatInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static void SetDoubleField(JNIEnv* env, jobject obj, jfieldID fieldID, jdouble val) {
    rvmSetDoubleInstanceFieldValue((Env*) env, (Object*) obj, (InstanceField*) fieldID, val);
}

static jmethodID GetStaticMethodID(JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return (jmethodID) rvmGetClassMethod((Env*) env, (Class*) clazz, (char*) name, (char*) sig);
}

static jobject CallStaticObjectMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return (jobject) rvmCallObjectClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jobject CallStaticObjectMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return (jobject) rvmCallObjectClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jobject CallStaticObjectMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jobject o = CallStaticObjectMethodV(env, clazz, methodID, args);
    va_end(args);
    return o;
}

static jboolean CallStaticBooleanMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallBooleanClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jboolean CallStaticBooleanMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallBooleanClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jboolean CallStaticBooleanMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jboolean b = CallStaticBooleanMethodV(env, clazz, methodID, args);
    va_end(args);
    return b;
}

static jbyte CallStaticByteMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallByteClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jbyte CallStaticByteMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallByteClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jbyte CallStaticByteMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jbyte b = CallStaticByteMethodV(env, clazz, methodID, args);
    va_end(args);
    return b;
}

static jchar CallStaticCharMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallCharClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jchar CallStaticCharMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallCharClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jchar CallStaticCharMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jchar c = CallStaticCharMethodV(env, clazz, methodID, args);
    va_end(args);
    return c;
}

static jshort CallStaticShortMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallShortClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jshort CallStaticShortMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallShortClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jshort CallStaticShortMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jshort s = CallStaticShortMethodV(env, clazz, methodID, args);
    va_end(args);
    return s;
}

static jint CallStaticIntMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallIntClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jint CallStaticIntMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallIntClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jint CallStaticIntMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jint i = CallStaticIntMethodV(env, clazz, methodID, args);
    va_end(args);
    return i;
}

static jlong CallStaticLongMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallLongClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jlong CallStaticLongMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallLongClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jlong CallStaticLongMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jlong l = CallStaticLongMethodV(env, clazz, methodID, args);
    va_end(args);
    return l;
}

static jfloat CallStaticFloatMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallFloatClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jfloat CallStaticFloatMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallFloatClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jfloat CallStaticFloatMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jfloat f = CallStaticFloatMethodV(env, clazz, methodID, args);
    va_end(args);
    return f;
}

static jdouble CallStaticDoubleMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    return rvmCallDoubleClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jdouble CallStaticDoubleMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue* args) {
    return rvmCallDoubleClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static jdouble CallStaticDoubleMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    jdouble d = CallStaticDoubleMethodV(env, clazz, methodID, args);
    va_end(args);
    return d;
}

static void CallStaticVoidMethodV(JNIEnv* env, jclass clazz, jmethodID methodID, va_list args) {
    rvmCallVoidClassMethodV((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static void CallStaticVoidMethodA(JNIEnv* env, jclass clazz, jmethodID methodID, jvalue*  args) {
    rvmCallVoidClassMethodA((Env*) env, (Class*) clazz, (Method*) methodID, args);
}

static void CallStaticVoidMethod(JNIEnv* env, jclass clazz, jmethodID methodID, ...) {
    va_list args;
    va_start(args, methodID);
    CallStaticVoidMethodV(env, clazz, methodID, args);
    va_end(args);
}

static jfieldID GetStaticFieldID(JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return (jfieldID) rvmGetClassField((Env*) env, (Class*) clazz, (char*) name, (char*) sig);
}

static jobject GetStaticObjectField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return (jobject) rvmGetObjectClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jboolean GetStaticBooleanField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetBooleanClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jbyte GetStaticByteField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetByteClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jchar GetStaticCharField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetCharClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jshort GetStaticShortField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetShortClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jint GetStaticIntField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetIntClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jlong GetStaticLongField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetLongClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jfloat GetStaticFloatField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetFloatClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static jdouble GetStaticDoubleField(JNIEnv* env, jclass clazz, jfieldID fieldID) {
    return rvmGetDoubleClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID);
}

static void SetStaticObjectField(JNIEnv* env, jclass clazz, jfieldID fieldID, jobject val) {
    rvmSetObjectClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, (Object*) val);
}

static void SetStaticBooleanField(JNIEnv* env, jclass clazz, jfieldID fieldID, jboolean val) {
    rvmSetBooleanClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticByteField(JNIEnv* env, jclass clazz, jfieldID fieldID, jbyte val) {
    rvmSetByteClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticCharField(JNIEnv* env, jclass clazz, jfieldID fieldID, jchar val) {
    rvmSetCharClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticShortField(JNIEnv* env, jclass clazz, jfieldID fieldID, jshort val) {
    rvmSetShortClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticIntField(JNIEnv* env, jclass clazz, jfieldID fieldID, jint val) {
    rvmSetIntClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticLongField(JNIEnv* env, jclass clazz, jfieldID fieldID, jlong val) {
    rvmSetLongClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticFloatField(JNIEnv* env, jclass clazz, jfieldID fieldID, jfloat val) {
    rvmSetFloatClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static void SetStaticDoubleField(JNIEnv* env, jclass clazz, jfieldID fieldID, jdouble val) {
    rvmSetDoubleClassFieldValue((Env*) env, (Class*) clazz, (ClassField*) fieldID, val);
}

static jstring NewString(JNIEnv* env, const jchar* unicode, jsize len) {
    return (jstring) rvmNewString((Env*) env, (jchar*) unicode, len);
}

static jsize GetStringLength(JNIEnv* env, jstring str) {
    return rvmGetStringLength((Env*) env, (Object*) str);
}

static const jchar* GetStringChars(JNIEnv* env, jstring str, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_TRUE;
    return rvmGetStringChars((Env*) env, (Object*) str);
}

static void ReleaseStringChars(JNIEnv* env, jstring str, const jchar* chars) {
}
  
static jstring NewStringUTF(JNIEnv* env, const char* utf) {
    return (jstring) rvmNewStringUTF((Env*) env, (char*) utf, -1);
}

static jsize GetStringUTFLength(JNIEnv* env, jstring str) {
    return rvmGetStringUTFLength((Env*) env, (Object*) str);
}

static const char* GetStringUTFChars(JNIEnv* env, jstring str, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_TRUE;
    return rvmGetStringUTFChars((Env*) env, (Object*) str);
}

static void ReleaseStringUTFChars(JNIEnv* env, jstring str, const char* chars) {
} 

static jsize GetArrayLength(JNIEnv* env, jarray array) {
    return ((Array*) array)->length;
}

static jobjectArray NewObjectArray(JNIEnv* env, jsize len, jclass clazz, jobject init) {
    return (jobjectArray) rvmNewObjectArray((Env*) env, len, (Class*) clazz, NULL, (Object*) init);
}

static jobject GetObjectArrayElement(JNIEnv* env, jobjectArray array, jsize index) {
    return (jobject) ((ObjectArray*) array)->values[index];
}

static void SetObjectArrayElement(JNIEnv* env, jobjectArray array, jsize index, jobject val) {
    ((ObjectArray*) array)->values[index] = (Object*) val;
}

static jbooleanArray NewBooleanArray(JNIEnv* env, jsize len) {
    return (jbooleanArray) rvmNewBooleanArray((Env*) env, len);
}

static jbyteArray NewByteArray(JNIEnv* env, jsize len) {
    return (jbyteArray) rvmNewByteArray((Env*) env, len);
}

static jcharArray NewCharArray(JNIEnv* env, jsize len) {
    return (jcharArray) rvmNewCharArray((Env*) env, len);
}

static jshortArray NewShortArray(JNIEnv* env, jsize len) {
    return (jshortArray) rvmNewShortArray((Env*) env, len);
}

static jintArray NewIntArray(JNIEnv* env, jsize len) {
    return (jintArray) rvmNewIntArray((Env*) env, len);
}

static jlongArray NewLongArray(JNIEnv* env, jsize len) {
    return (jlongArray) rvmNewLongArray((Env*) env, len);
}

static jfloatArray NewFloatArray(JNIEnv* env, jsize len) {
    return (jfloatArray) rvmNewFloatArray((Env*) env, len);
}

static jdoubleArray NewDoubleArray(JNIEnv* env, jsize len) {
    return (jdoubleArray) rvmNewDoubleArray((Env*) env, len);
}

static jboolean* GetBooleanArrayElements(JNIEnv* env, jbooleanArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((BooleanArray*) array)->values;
}

static jbyte* GetByteArrayElements(JNIEnv* env, jbyteArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((ByteArray*) array)->values;
}

static jchar* GetCharArrayElements(JNIEnv* env, jcharArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((CharArray*) array)->values;
}

static jshort* GetShortArrayElements(JNIEnv* env, jshortArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((ShortArray*) array)->values;
}

static jint* GetIntArrayElements(JNIEnv* env, jintArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((IntArray*) array)->values;
}

static jlong* GetLongArrayElements(JNIEnv* env, jlongArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((LongArray*) array)->values;
}

static jfloat* GetFloatArrayElements(JNIEnv* env, jfloatArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((FloatArray*) array)->values;
}

static jdouble* GetDoubleArrayElements(JNIEnv* env, jdoubleArray array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    return ((DoubleArray*) array)->values;
}


static void ReleaseBooleanArrayElements(JNIEnv* env, jbooleanArray array, jboolean* elems, jint mode) {
}

static void ReleaseByteArrayElements(JNIEnv* env, jbyteArray array, jbyte* elems, jint mode) {
}

static void ReleaseCharArrayElements(JNIEnv* env, jcharArray array, jchar* elems, jint mode) {
}

static void ReleaseShortArrayElements(JNIEnv* env, jshortArray array, jshort* elems, jint mode) {
}

static void ReleaseIntArrayElements(JNIEnv* env, jintArray array, jint* elems, jint mode) {
}

static void ReleaseLongArrayElements(JNIEnv* env, jlongArray array, jlong* elems, jint mode) {
}

static void ReleaseFloatArrayElements(JNIEnv* env, jfloatArray array, jfloat* elems, jint mode) {
}

static void ReleaseDoubleArrayElements(JNIEnv* env, jdoubleArray array, jdouble* elems, jint mode) {
}

static jboolean checkBounds(Env* env, Array* array, jint start, jint len) {
    jsize length = array->length;
    jsize end = start + len;
    if (start < 0 || len < 0 || end > length) {
        rvmThrowArrayIndexOutOfBoundsException(env, length, start);
        return FALSE;
    }
    return TRUE;
}

static void GetBooleanArrayRegion(JNIEnv* env, jbooleanArray array, jsize start, jsize len, jboolean* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((BooleanArray*) array)->values + start, sizeof(jboolean) * len);
}

static void GetByteArrayRegion(JNIEnv* env, jbyteArray array, jsize start, jsize len, jbyte* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((ByteArray*) array)->values + start, sizeof(jbyte) * len);
}

static void GetCharArrayRegion(JNIEnv* env, jcharArray array, jsize start, jsize len, jchar* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((CharArray*) array)->values + start, sizeof(jchar) * len);
}

static void GetShortArrayRegion(JNIEnv* env, jshortArray array, jsize start, jsize len, jshort* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((ShortArray*) array)->values + start, sizeof(jshort) * len);
}

static void GetIntArrayRegion(JNIEnv* env, jintArray array, jsize start, jsize len, jint* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((IntArray*) array)->values + start, sizeof(jint) * len);
}

static void GetLongArrayRegion(JNIEnv* env, jlongArray array, jsize start, jsize len, jlong* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((LongArray*) array)->values + start, sizeof(jlong) * len);
}

static void GetFloatArrayRegion(JNIEnv* env, jfloatArray array, jsize start, jsize len, jfloat* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((FloatArray*) array)->values + start, sizeof(jfloat) * len);
}

static void GetDoubleArrayRegion(JNIEnv* env, jdoubleArray array, jsize start, jsize len, jdouble* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(buf, ((DoubleArray*) array)->values + start, sizeof(jdouble) * len);
}

static void SetBooleanArrayRegion(JNIEnv* env, jbooleanArray array, jsize start, jsize len, jboolean* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((BooleanArray*) array)->values + start, buf, sizeof(jboolean) * len);
}

static void SetByteArrayRegion(JNIEnv* env, jbyteArray array, jsize start, jsize len, jbyte* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((ByteArray*) array)->values + start, buf, sizeof(jbyte) * len);
}

static void SetCharArrayRegion(JNIEnv* env, jcharArray array, jsize start, jsize len, jchar* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((CharArray*) array)->values + start, buf, sizeof(jchar) * len);
}

static void SetShortArrayRegion(JNIEnv* env, jshortArray array, jsize start, jsize len, jshort* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((ShortArray*) array)->values + start, buf, sizeof(jshort) * len);
}

static void SetIntArrayRegion(JNIEnv* env, jintArray array, jsize start, jsize len, jint* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((IntArray*) array)->values + start, buf, sizeof(jint) * len);
}

static void SetLongArrayRegion(JNIEnv* env, jlongArray array, jsize start, jsize len, jlong* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((LongArray*) array)->values + start, buf, sizeof(jlong) * len);
}

static void SetFloatArrayRegion(JNIEnv* env, jfloatArray array, jsize start, jsize len, jfloat* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((FloatArray*) array)->values + start, buf, sizeof(jfloat) * len);
}

static void SetDoubleArrayRegion(JNIEnv* env, jdoubleArray array, jsize start, jsize len, jdouble* buf) {
    if (!checkBounds((Env*) env, (Array*) array, start, len)) return;
    memcpy(((DoubleArray*) array)->values + start, buf, sizeof(jdouble) * len);
}

static jint MonitorEnter(JNIEnv* env, jobject obj) {
    rvmLockObject((Env*) env, (Object*) obj);
    if (rvmExceptionOccurred((Env*) env)) return -1;
    return 0;
}

static jint MonitorExit(JNIEnv* env, jobject obj) {
    rvmUnlockObject((Env*) env, (Object*) obj);
    if (rvmExceptionOccurred((Env*) env)) return -1;
    return 0;
}
 
static void GetStringRegion(JNIEnv* env, jstring str, jsize start, jsize len, jchar* buf) {
    rvmGetStringRegion((Env*) env, (Object*) str, start, len, buf);
}

static void GetStringUTFRegion(JNIEnv *env, jstring str, jsize start, jsize len, char* buf) {
    rvmGetStringUTFRegion((Env*) env, (Object*) str, start, len, buf);
}

static void* GetPrimitiveArrayCritical(JNIEnv* env, jarray _array, jboolean* isCopy) {
    if (isCopy) *isCopy = JNI_FALSE;
    Array* array = (Array*) _array;
    switch (array->object.clazz->name[1]) {
    case 'Z':
        return ((BooleanArray*) array)->values;
    case 'B':
        return ((ByteArray*) array)->values;
    case 'C':
        return ((CharArray*) array)->values;
    case 'S':
        return ((ShortArray*) array)->values;
    case 'I':
        return ((IntArray*) array)->values;
    case 'J':
        return ((LongArray*) array)->values;
    case 'F':
        return ((FloatArray*) array)->values;
    case 'D':
        return ((DoubleArray*) array)->values;
    }
    return NULL;
}

static void ReleasePrimitiveArrayCritical(JNIEnv* env, jarray array, void* carray, jint mode) {
}

static const jchar* GetStringCritical(JNIEnv* env, jstring str, jboolean* isCopy) {
    return GetStringChars(env, str, isCopy);
}

static void ReleaseStringCritical(JNIEnv* env, jstring str, const jchar* chars) {
    ReleaseStringChars(env, str, chars);
}

static jboolean ExceptionCheck(JNIEnv* env) {
    return rvmExceptionCheck((Env*) env);
}

static jobject NewDirectByteBuffer(JNIEnv* env, void* address, jlong capacity) {
    return (jobject) rvmNewDirectByteBuffer((Env*) env, address, capacity);
}

static void* GetDirectBufferAddress(JNIEnv* env, jobject buf) {
    return rvmGetDirectBufferAddress((Env*) env, (Object*) buf);
}

static jlong GetDirectBufferCapacity(JNIEnv* env, jobject buf) {
    return rvmGetDirectBufferCapacity((Env*) env, (Object*) buf);
}

struct JNIInvokeInterface_ javaVM = {
    NULL,
    NULL,
    NULL,
    &DestroyJavaVM,
    &AttachCurrentThread,
    &DetachCurrentThread,
    &GetEnv,
    &AttachCurrentThreadAsDaemon
};

struct JNINativeInterface_ jni = {
    NULL,
    NULL,
    NULL,
    NULL,
    &GetVersion,
    &DefineClass,
    &FindClass,
    &FromReflectedMethod,
    &FromReflectedField,
    &ToReflectedMethod,
    &GetSuperclass,
    &IsAssignableFrom,
    &ToReflectedField,
    &Throw,
    &ThrowNew,
    &ExceptionOccurred,
    &ExceptionDescribe,
    &ExceptionClear,
    &FatalError,
    &PushLocalFrame,
    &PopLocalFrame,
    &NewGlobalRef,
    &DeleteGlobalRef,
    &DeleteLocalRef,
    &IsSameObject,
    &NewLocalRef,
    &EnsureLocalCapacity,
    &AllocObject,
    &NewObject,
    &NewObjectV,
    &NewObjectA,
    &GetObjectClass,
    &IsInstanceOf,
    &GetMethodID,
    &CallObjectMethod,
    &CallObjectMethodV,
    &CallObjectMethodA,
    &CallBooleanMethod,
    &CallBooleanMethodV,
    &CallBooleanMethodA,
    &CallByteMethod,
    &CallByteMethodV,
    &CallByteMethodA,
    &CallCharMethod,
    &CallCharMethodV,
    &CallCharMethodA,
    &CallShortMethod,
    &CallShortMethodV,
    &CallShortMethodA,
    &CallIntMethod,
    &CallIntMethodV,
    &CallIntMethodA,
    &CallLongMethod,
    &CallLongMethodV,
    &CallLongMethodA,
    &CallFloatMethod,
    &CallFloatMethodV,
    &CallFloatMethodA,
    &CallDoubleMethod,
    &CallDoubleMethodV,
    &CallDoubleMethodA,
    &CallVoidMethod,
    &CallVoidMethodV,
    &CallVoidMethodA,
    &CallNonvirtualObjectMethod,
    &CallNonvirtualObjectMethodV,
    &CallNonvirtualObjectMethodA,
    &CallNonvirtualBooleanMethod,
    &CallNonvirtualBooleanMethodV,
    &CallNonvirtualBooleanMethodA,
    &CallNonvirtualByteMethod,
    &CallNonvirtualByteMethodV,
    &CallNonvirtualByteMethodA,
    &CallNonvirtualCharMethod,
    &CallNonvirtualCharMethodV,
    &CallNonvirtualCharMethodA,
    &CallNonvirtualShortMethod,
    &CallNonvirtualShortMethodV,
    &CallNonvirtualShortMethodA,
    &CallNonvirtualIntMethod,
    &CallNonvirtualIntMethodV,
    &CallNonvirtualIntMethodA,
    &CallNonvirtualLongMethod,
    &CallNonvirtualLongMethodV,
    &CallNonvirtualLongMethodA,
    &CallNonvirtualFloatMethod,
    &CallNonvirtualFloatMethodV,
    &CallNonvirtualFloatMethodA,
    &CallNonvirtualDoubleMethod,
    &CallNonvirtualDoubleMethodV,
    &CallNonvirtualDoubleMethodA,
    &CallNonvirtualVoidMethod,
    &CallNonvirtualVoidMethodV,
    &CallNonvirtualVoidMethodA,
    &GetFieldID,
    &GetObjectField,
    &GetBooleanField,
    &GetByteField,
    &GetCharField,
    &GetShortField,
    &GetIntField,
    &GetLongField,
    &GetFloatField,
    &GetDoubleField,
    &SetObjectField,
    &SetBooleanField,
    &SetByteField,
    &SetCharField,
    &SetShortField,
    &SetIntField,
    &SetLongField,
    &SetFloatField,
    &SetDoubleField,
    &GetStaticMethodID,
    &CallStaticObjectMethod,
    &CallStaticObjectMethodV,
    &CallStaticObjectMethodA,
    &CallStaticBooleanMethod,
    &CallStaticBooleanMethodV,
    &CallStaticBooleanMethodA,
    &CallStaticByteMethod,
    &CallStaticByteMethodV,
    &CallStaticByteMethodA,
    &CallStaticCharMethod,
    &CallStaticCharMethodV,
    &CallStaticCharMethodA,
    &CallStaticShortMethod,
    &CallStaticShortMethodV,
    &CallStaticShortMethodA,
    &CallStaticIntMethod,
    &CallStaticIntMethodV,
    &CallStaticIntMethodA,
    &CallStaticLongMethod,
    &CallStaticLongMethodV,
    &CallStaticLongMethodA,
    &CallStaticFloatMethod,
    &CallStaticFloatMethodV,
    &CallStaticFloatMethodA,
    &CallStaticDoubleMethod,
    &CallStaticDoubleMethodV,
    &CallStaticDoubleMethodA,
    &CallStaticVoidMethod,
    &CallStaticVoidMethodV,
    &CallStaticVoidMethodA,
    &GetStaticFieldID,
    &GetStaticObjectField,
    &GetStaticBooleanField,
    &GetStaticByteField,
    &GetStaticCharField,
    &GetStaticShortField,
    &GetStaticIntField,
    &GetStaticLongField,
    &GetStaticFloatField,
    &GetStaticDoubleField,
    &SetStaticObjectField,
    &SetStaticBooleanField,
    &SetStaticByteField,
    &SetStaticCharField,
    &SetStaticShortField,
    &SetStaticIntField,
    &SetStaticLongField,
    &SetStaticFloatField,
    &SetStaticDoubleField,
    &NewString,
    &GetStringLength,
    &GetStringChars,
    &ReleaseStringChars,
    &NewStringUTF,
    &GetStringUTFLength,
    &GetStringUTFChars,
    &ReleaseStringUTFChars,
    &GetArrayLength,
    &NewObjectArray,
    &GetObjectArrayElement,
    &SetObjectArrayElement,
    &NewBooleanArray,
    &NewByteArray,
    &NewCharArray,
    &NewShortArray,
    &NewIntArray,
    &NewLongArray,
    &NewFloatArray,
    &NewDoubleArray,
    &GetBooleanArrayElements,
    &GetByteArrayElements,
    &GetCharArrayElements,
    &GetShortArrayElements,
    &GetIntArrayElements,
    &GetLongArrayElements,
    &GetFloatArrayElements,
    &GetDoubleArrayElements,
    &ReleaseBooleanArrayElements,
    &ReleaseByteArrayElements,
    &ReleaseCharArrayElements,
    &ReleaseShortArrayElements,
    &ReleaseIntArrayElements,
    &ReleaseLongArrayElements,
    &ReleaseFloatArrayElements,
    &ReleaseDoubleArrayElements,
    &GetBooleanArrayRegion,
    &GetByteArrayRegion,
    &GetCharArrayRegion,
    &GetShortArrayRegion,
    &GetIntArrayRegion,
    &GetLongArrayRegion,
    &GetFloatArrayRegion,
    &GetDoubleArrayRegion,
    &SetBooleanArrayRegion,
    &SetByteArrayRegion,
    &SetCharArrayRegion,
    &SetShortArrayRegion,
    &SetIntArrayRegion,
    &SetLongArrayRegion,
    &SetFloatArrayRegion,
    &SetDoubleArrayRegion,
    &RegisterNatives,
    &UnregisterNatives,
    &MonitorEnter,
    &MonitorExit,
    &GetJavaVM,
    &GetStringRegion,
    &GetStringUTFRegion,
    &GetPrimitiveArrayCritical,
    &ReleasePrimitiveArrayCritical,
    &GetStringCritical,
    &ReleaseStringCritical,
    &NewWeakGlobalRef,
    &DeleteWeakGlobalRef,
    &ExceptionCheck,
    &NewDirectByteBuffer,
    &GetDirectBufferAddress,
    &GetDirectBufferCapacity
};

