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
#ifndef ROBOVM_TYPES_H
#define ROBOVM_TYPES_H

#include <pthread.h>
#include <jni_types.h>
#include <jni.h>
#include <limits.h>

#undef FALSE
#undef TRUE
#define FALSE JNI_FALSE
#define TRUE JNI_TRUE

#define PTR_TO_LONG(p) ((jlong) (intptr_t) p)
#define LONG_TO_PTR(l) ((void*) (intptr_t) l)

typedef struct Field Field;
typedef struct ClassField ClassField;
typedef struct InstanceField InstanceField;
typedef struct Method Method;
typedef struct NativeMethod NativeMethod;
typedef struct BridgeMethod BridgeMethod;
typedef struct CallbackMethod CallbackMethod;
typedef struct ProxyMethod ProxyMethod;
typedef struct ObjectHeader ObjectHeader;
typedef struct Interface Interface;
typedef struct Exception Exception;
typedef struct Class Class;
typedef struct Object Object;
typedef struct ClassLoader ClassLoader;
typedef struct DataObject DataObject;
typedef struct Thread Thread;
typedef struct JavaThread JavaThread;
typedef struct Monitor Monitor;
typedef struct Array Array;
typedef struct EnclosingMethod EnclosingMethod;
typedef struct InnerClass InnerClass;
typedef pthread_mutex_t Mutex;

struct Field {
  Field* next;
  Class* clazz;
  const char* name;
  const char* desc;
  jint access;
  char* attributes;
};

struct ClassField {
  Field field;
  void* address;
};

struct InstanceField {
  Field field;
  jint offset;
};

struct Method {
  Method* next;
  Class* clazz;
  const char* name;
  const char* desc;
  jint access;
  jint size;
  void* attributes;
  void* impl;
  void* synchronizedImpl;
};

struct NativeMethod {
  Method method;
  void* nativeImpl;
};

struct BridgeMethod {
  Method method;
  void** targetFnPtr;
};

struct CallbackMethod {
  Method method;
  void* callbackImpl;
};

struct ProxyMethod {
    Method method;
    Method* proxiedMethod;
};

struct Interface {
  Interface* next;
  Class* interface;
};

struct Exception {
  Exception* next;
  char* name;
};

struct Object {
  Class* clazz;
  uint32_t lock;
};

/* 
 * Represents a java.lang.Class instance
 */
struct Class {
  Object object;
  void* _data;             // Reserve the memory needed to store the instance fields for java.lang.Class. 
                           // java.lang.Class has a single field, (SoftReference<ClassCache<T>> cacheRef).
                           // void* gives enough space to store that reference.
  const char* name;        // The name in modified UTF-8.
  ClassLoader* classLoader;
  Class* superclass;       // Superclass pointer. Only java.lang.Object, primitive classes and interfaces have NULL here.
  Class* componentType;
  void* initializer;       // Points to the <clinit> method implementation of the class. NULL if there is no <clinit>.
  jint flags;
  Interface* _interfaces;  // Lazily loaded linked list of interfaces. Use rvmGetInterfaces() to get this value.
  Field* _fields;          // Lazily loaded linked list of fields. Use rvmGetFields() to get this value.
  Method* _methods;        // Lazily loaded linked list of methods. Use rvmGetMethods() to get this value.
  void* attributes;
  jint classDataSize;
  jint instanceDataOffset; // The offset from the base of Object->data
                           // where the instance fields of this class can be found.
  jint instanceDataSize;   // The number of bytes needed to store the instance fields declared by this class.
                           // instanceDataOffset + instanceDataSize gives the total number of bytes
                           // needed to store the instance data for instances of this class including superclasses.
  void* data[0];           // This is where static fields are stored for the class
};

struct ClassLoader {
  Object object;
  ClassLoader* parent;
  Object* packages;
};

struct DataObject {
  Object object;
  void* data[0];
};

struct InnerClass {
  InnerClass* next;
  char* innerClass;
  char* outerClass;
  char* innerName;
  jint innerClassAccess;
};

struct EnclosingMethod {
  char* className;
  char* methodName;
  char* methodDesc;
};

struct Monitor {
  Thread*     owner;          /* which thread currently owns the lock? */
  int         lockCount;      /* owner's recursive lock depth */
  Object*     obj;            /* what object are we part of [debug only] */

  Thread*     waitSet;  /* threads currently waiting on this monitor */
  Monitor*    next;
  Mutex lock;
};

// NOTE: The compiler sorts fields by size so the order of the fields here don't match the order in Thread.java
struct JavaThread {
  Object object;
  jlong threadPtr; // Points to the Thread
  jlong id;
  jlong stackSize;
  Object* group;
  Object* name;
  jint priority;
  Object* target;
  Object* interruptActions;
  ClassLoader* contextClassLoader;
  Object* uncaughtHandler;
  Object* localValues;
  Object* inheritableValues;
  jint parkState;
  Object* parkBlocker;
  Object* lock;
  jboolean daemon;
  jboolean started;
};

struct Thread {
  pthread_t pThread;
  JavaThread* threadObj;
  jint threadId;
  void* stackAddr;
  jboolean interrupted;
  Monitor* waitMonitor;
  Mutex waitMutex;
  jint status;
  pthread_cond_t waitCond;
  struct Thread* waitNext;
  struct Thread* prev;
  struct Thread* next;
};

struct Array {
  Object object;
  jint length;
  void* values[0];
};

#define MAKE_ARRAY(T, N) \
typedef struct _ ## N ## Array { \
  Object object; \
  jint length; \
  T values[0]; \
} N ## Array;

MAKE_ARRAY(Object*, Object)
MAKE_ARRAY(jlong, Long)
MAKE_ARRAY(jint, Int)
MAKE_ARRAY(jbyte, Byte)
MAKE_ARRAY(jshort, Short)
MAKE_ARRAY(jchar, Char)
MAKE_ARRAY(jboolean, Boolean)
MAKE_ARRAY(jfloat, Float)
MAKE_ARRAY(jdouble, Double)

typedef struct Boolean {
  Object object;
  jboolean value;
} Boolean;

typedef struct Byte {
  Object object;
  jbyte value;
} Byte;

typedef struct Short {
  Object object;
  jshort value;
} Short;

typedef struct Character {
  Object object;
  jchar value;
} Character;

typedef struct Integer {
  Object object;
  jint value;
} Integer;

typedef struct Long {
  Object object;
  jlong value;
} Long;

typedef struct Float {
  Object object;
  jfloat value;
} Float;

typedef struct Double {
  Object object;
  jdouble value;
} Double;

typedef struct DynamicLib DynamicLib;
struct DynamicLib {
    DynamicLib* next;
    void* handle;
    char path[PATH_MAX];
};

typedef struct ClasspathEntry ClasspathEntry;
struct ClasspathEntry {
    ClasspathEntry* next;
    char jarPath[PATH_MAX];
};

struct Env;
typedef struct Env Env;
struct TrycatchContext;
typedef struct TrycatchContext TrycatchContext;

typedef struct Options {
    char* mainClass;
    char** commandLineArgs;
    jint commandLineArgsCount;
    jint logLevel;
    char basePath[PATH_MAX];
    char executablePath[PATH_MAX];
    char** rawBootclasspath; 
    char** rawClasspath; 
    ClasspathEntry* bootclasspath;
    ClasspathEntry* classpath;
    Class* (*loadBootClass)(Env*, const char*, ClassLoader*);
    Class* (*loadUserClass)(Env*, const char*, ClassLoader*);
    void (*classInitialized)(Env*, Class*);
    void (*loadInterfaces)(Env*, Class*);
    void (*loadFields)(Env*, Class*);
    void (*loadMethods)(Env*, Class*);
    Class* (*findClassAt)(Env*, void*);
    jboolean (*exceptionMatch)(Env*, TrycatchContext*);
} Options;

typedef struct VM {
    JavaVM javaVM;
    Options* options;
} VM;

typedef struct GatewayFrame {
    /* 
     * Whenever we enter (through a call to a native, bridge or proxy method) or leave (through a call to  _call0 or
     * a callback method) native code we push the address of the stack frame of the function making the call. This 
     * information is used in unwind.c to avoid native frames when unwinding.
     */
    struct GatewayFrame* prev;
    void* frameAddress;
    ProxyMethod* proxyMethod; // Whenever we call a dynamic proxy we push the ProxyMethod* here. This is used when generating stack traces.
} GatewayFrame;

#define rvmPushGatewayFrame0(env, f, address, pm)  \
    (f)->prev = env->gatewayFrames;                    \
    (f)->frameAddress = address;                       \
    (f)->proxyMethod = pm;                             \
    env->gatewayFrames = (f)
#define rvmPushGatewayFrame1(env, f, address, pm)  \
    GatewayFrame f;                                       \
    rvmPushGatewayFrame0(env, &f, address, pm)

#define rvmPushGatewayFrameAddress(env, address) rvmPushGatewayFrame1(env, __gwFrame##__COUNTER__, address, NULL)
#define rvmPushGatewayFrameProxy(env, pm) rvmPushGatewayFrame1(env, __gwFrame##__COUNTER__, __builtin_frame_address(0), pm)
#define rvmPushGatewayFrame(env) rvmPushGatewayFrame1(env, __gwFrame##__COUNTER__, __builtin_frame_address(0), NULL)
#define rvmPopGatewayFrame(env) env->gatewayFrames = env->gatewayFrames->prev

struct TrycatchContext {
    struct TrycatchContext* prev;
    jint sel;
#if defined(RVM_X86)
    void* fp;
    void* pc;
    void* esp;
    void* ebx;
    void* esi;
    void* edi;
#elif defined(DARWIN) && defined(RVM_THUMBV7)
    void* sp; // r13
    void* r4;
    void* r5;
    void* r6;
    void* fp; // r7
    void* r8;
    void* r10;
    void* r11;
    void* pc; // r14 (lr)
    double d8;
    double d9;
    double d10;
    double d11;
    double d12;
    double d13;
    double d14;
    double d15;
#endif
};

struct Env {
    JNIEnv jni;
    VM* vm;
    Object* throwable;
    Thread* currentThread;
    void* reserved0; // Used internally
    void* reserved1; // Used internally
    GatewayFrame* gatewayFrames;
    TrycatchContext* trycatchContext;
    jint attachCount;
};

typedef struct CallStackEntry CallStackEntry;
struct CallStackEntry {
    CallStackEntry* next;
    CallStackEntry* prev;
    Method* method;
    jint offset;
};

#endif

