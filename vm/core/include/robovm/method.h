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
#ifndef ROBOVM_METHOD_H
#define ROBOVM_METHOD_H

#define METHOD_ACCESS_MASK   0x0000FFFF
#define METHOD_TYPE_BRIDGE   0x20000000
#define METHOD_TYPE_CALLBACK 0x40000000
#define METHOD_TYPE_PROXY    0x80000000

#define METHOD_IS_PUBLIC(m) (IS_PUBLIC((m)->access))
#define METHOD_IS_PRIVATE(m) (IS_PRIVATE((m)->access))
#define METHOD_IS_PROTECTED(m) (IS_PROTECTED((m)->access))
#define METHOD_IS_STATIC(m) (IS_STATIC((m)->access))
#define METHOD_IS_FINAL(m) (IS_FINAL((m)->access))
#define METHOD_IS_SYNCHRONIZED(m) (IS_SYNCHRONIZED((m)->access))
#define METHOD_IS_NATIVE(m) (IS_NATIVE((m)->access))
#define METHOD_IS_ABSTRACT(m) (IS_ABSTRACT((m)->access))
#define METHOD_IS_PACKAGE_PRIVATE(m) (IS_PACKAGE_PRIVATE((m)->access))
#define METHOD_IS_CONSTRUCTOR(m) (!strcmp("<init>", (m)->name))
#define METHOD_IS_CLASS_INITIALIZER(m) (!strcmp("<clinit>", (m)->name))

// The maximum number of CallStackFrames returned by rvmCaptureCallStack() including native frames
#define MAX_CALL_STACK_LENGTH 2048

extern jboolean rvmInitMethods(Env* env);
extern const char* rvmGetReturnType(const char* desc);
extern const char* rvmGetNextParameterType(const char** desc);
extern jint rvmGetParameterCount(Method* method);
extern Method* rvmGetMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern jboolean rvmHasMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern Method* rvmGetClassMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern Method* rvmGetClassInitializer(Env* env, Class* clazz);
extern Method* rvmGetInstanceMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern jboolean rvmRegisterNative(Env* env, NativeMethod* method, void* impl);
extern jboolean rvmUnregisterNative(Env* env, NativeMethod* method);
extern void* rvmResolveNativeMethodImpl(Env* env, NativeMethod* method, const char* shortMangledName, const char* longMangledName, ClassLoader* classLoader, void** ptr);
extern jboolean rvmLoadNativeLibrary(Env* env, const char* path, ClassLoader* classLoader);
extern Method* rvmFindMethodAtAddress(Env* env, void* address);
extern Method* rvmGetCallingMethod(Env* env);
extern CallStack* rvmCaptureCallStack(Env* env);
extern CallStack* rvmCaptureCallStackForThread(Env* env, Thread* thread);
extern CallStackFrame* rvmResolveCallStackFrame(Env* env, CallStackFrame* frame);
extern ObjectArray* rvmCallStackToStackTraceElements(Env* env, CallStack* callStack, jint first);
extern void rvmCallVoidInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern void rvmCallVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern void rvmCallVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern Object* rvmCallObjectInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern Object* rvmCallObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern Object* rvmCallObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jboolean rvmCallBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jboolean rvmCallBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jboolean rvmCallBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jbyte rvmCallByteInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jbyte rvmCallByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jbyte rvmCallByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jchar rvmCallCharInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jchar rvmCallCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jchar rvmCallCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jshort rvmCallShortInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jshort rvmCallShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jshort rvmCallShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jint rvmCallIntInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jint rvmCallIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jint rvmCallIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jlong rvmCallLongInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jlong rvmCallLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jlong rvmCallLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jfloat rvmCallFloatInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jfloat rvmCallFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jfloat rvmCallFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jdouble rvmCallDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jdouble rvmCallDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jdouble rvmCallDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern void rvmCallNonvirtualVoidInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern void rvmCallNonvirtualVoidInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern void rvmCallNonvirtualVoidInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern Object* rvmCallNonvirtualObjectInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern Object* rvmCallNonvirtualObjectInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern Object* rvmCallNonvirtualObjectInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jboolean rvmCallNonvirtualBooleanInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jboolean rvmCallNonvirtualBooleanInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jboolean rvmCallNonvirtualBooleanInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jbyte rvmCallNonvirtualByteInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jbyte rvmCallNonvirtualByteInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jbyte rvmCallNonvirtualByteInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jchar rvmCallNonvirtualCharInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jchar rvmCallNonvirtualCharInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jchar rvmCallNonvirtualCharInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jshort rvmCallNonvirtualShortInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jshort rvmCallNonvirtualShortInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jshort rvmCallNonvirtualShortInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jint rvmCallNonvirtualIntInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jint rvmCallNonvirtualIntInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jint rvmCallNonvirtualIntInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jlong rvmCallNonvirtualLongInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jlong rvmCallNonvirtualLongInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jlong rvmCallNonvirtualLongInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jfloat rvmCallNonvirtualFloatInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jfloat rvmCallNonvirtualFloatInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jfloat rvmCallNonvirtualFloatInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern jdouble rvmCallNonvirtualDoubleInstanceMethod(Env* env, Object* obj, Method* method, ...);
extern jdouble rvmCallNonvirtualDoubleInstanceMethodA(Env* env, Object* obj, Method* method, jvalue* args);
extern jdouble rvmCallNonvirtualDoubleInstanceMethodV(Env* env, Object* obj, Method* method, va_list args);
extern void rvmCallVoidClassMethod(Env* env, Class* clazz, Method* method, ...);
extern void rvmCallVoidClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern void rvmCallVoidClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern Object* rvmCallObjectClassMethod(Env* env, Class* clazz, Method* method, ...);
extern Object* rvmCallObjectClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern Object* rvmCallObjectClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jboolean rvmCallBooleanClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jboolean rvmCallBooleanClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jboolean rvmCallBooleanClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jbyte rvmCallByteClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jbyte rvmCallByteClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jbyte rvmCallByteClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jchar rvmCallCharClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jchar rvmCallCharClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jchar rvmCallCharClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jshort rvmCallShortClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jshort rvmCallShortClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jshort rvmCallShortClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jint rvmCallIntClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jint rvmCallIntClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jint rvmCallIntClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jlong rvmCallLongClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jlong rvmCallLongClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jlong rvmCallLongClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jfloat rvmCallFloatClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jfloat rvmCallFloatClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jfloat rvmCallFloatClassMethodV(Env* env, Class* clazz, Method* method, va_list args);
extern jdouble rvmCallDoubleClassMethod(Env* env, Class* clazz, Method* method, ...);
extern jdouble rvmCallDoubleClassMethodA(Env* env, Class* clazz, Method* method, jvalue* args);
extern jdouble rvmCallDoubleClassMethodV(Env* env, Class* clazz, Method* method, va_list args);


static inline CallStackFrame* rvmGetNextCallStackMethod(Env* env, CallStack* callStack, jint* index) {
    while (*index < callStack->length) {
        CallStackFrame* frame = rvmResolveCallStackFrame(env, &callStack->frames[*index]);
        *index += 1;
        if (frame && frame->method) {
            return frame;
        }
    }
    return NULL;
}

#endif

