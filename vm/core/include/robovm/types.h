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
#ifndef ROBOVM_TYPES_H
#define ROBOVM_TYPES_H

#include <pthread.h>
#include <jni_types.h>
#include <jni.h>
#include <limits.h>
#include <signal.h>

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
typedef struct ProxyMethodException ProxyMethodException;
typedef struct ObjectHeader ObjectHeader;
typedef struct Interface Interface;
typedef struct Exception Exception;
typedef struct Class Class;
typedef struct Object Object;
typedef struct VITable VITable;
typedef struct ITables ITables;
typedef struct ITable ITable;
typedef struct TypeInfo TypeInfo;
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
  jint vitableIndex;
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
  ProxyMethodException* allowedExceptions;
};

struct ProxyMethodException {
  ProxyMethodException* next;
  Class* clazz;
};

struct Interface {
  Interface* next;
  Class* interface;
};

struct Object {
  Class* clazz;
  uint32_t lock;
};

struct VITable {
  uint16_t size;
  void* table[0];
};

struct ITable {
  TypeInfo* typeInfo;
  VITable table;
};

struct ITables {
  uint16_t count;
  ITable* cache;
  ITable* table[0];
};

struct TypeInfo {
  uint32_t id;
  uint32_t offset;
  uint32_t cache;
  uint32_t classCount;
  uint32_t interfaceCount;
  uint32_t types[0];
};

/* 
 * Represents a java.lang.Class instance
 */
struct Class {
  Object object;
  void* _data;             // Reserve the memory needed to store the instance fields for java.lang.Class. 
                           // java.lang.Class has a single field, (SoftReference<ClassCache<T>> cacheRef).
                           // void* gives enough space to store that reference.
  TypeInfo* typeInfo;      // Info on all types this class implements.
  VITable* vitable;
  ITables* itables;
  const char* name;        // The name in modified UTF-8.
  ClassLoader* classLoader;
  Class* superclass;       // Superclass pointer. Only java.lang.Object, primitive classes and interfaces have NULL here.
  Class* componentType;
  void* initializer;       // Points to the <clinit> method implementation of the class. NULL if there is no <clinit>.
  jint flags;              // Low 16-bits are access flags. High 16-bits are RoboVM specific flags defined in class.h.
  Thread* initThread;      // The Thread which is currently initializing this class.
  Interface* _interfaces;  // Lazily loaded linked list of interfaces. Use rvmGetInterfaces() to get this value.
  Field* _fields;          // Lazily loaded linked list of fields. Use rvmGetFields() to get this value.
  Method* _methods;        // Lazily loaded linked list of methods. Use rvmGetMethods() to get this value.
  void* attributes;
  jint classDataSize;
  jint instanceDataOffset; // The offset from the base of Object->data
                           // where the instance fields of this class can be found.
  jint instanceDataSize;   // The total number of bytes needed to store instances of this class.
  unsigned short classRefCount;
  unsigned short instanceRefCount;
  void* data[0] __attribute__ ((aligned (8)));  // This is where static fields are stored for the class. Must be 8-byte aligned.
};

// NOTE: The compiler sorts fields by type (ref, volatile long, double, long, float, int, char, short, boolean, byte) and then by name
// so the order of the fields here don't match the order in ClassLoader.java
struct ClassLoader {
  Object object;
  Object* packages;
  ClassLoader* parent;
};

struct DataObject {
  Object object;
  void* data[0] __attribute__ ((aligned (8)));
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

// NOTE: The compiler sorts fields. References first, then by alignment and then by name.
// So the order of the fields here don't match the order in Thread.java
struct JavaThread {
  Object object;
  ClassLoader* contextClassLoader;
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
  // volatile long is 8-byte aligned on ARM
  /*volatile*/ jlong threadPtr __attribute__ ((aligned (8))); // Points to the Thread
  jlong id;
  jlong stackSize;
#else
  jlong id;
  jlong stackSize;
  /*volatile*/ jlong threadPtr; // Points to the Thread
#endif
  jint parkState;
  jint priority;
  jboolean daemon;
  jboolean started;
};

struct Thread {
  jint threadId;
  JavaThread* threadObj;
  struct Thread* waitNext;
  struct Thread* prev;
  struct Thread* next;
  Monitor* waitMonitor;
  pthread_t pThread;
  void* stackAddr;
  jboolean interrupted;
  Mutex waitMutex;
  jint status;
  pthread_cond_t waitCond;
  sigset_t signalMask;
};

struct Array {
  Object object;
  jint length __attribute__ ((aligned (8)));
  void* values[0];
};

#define MAKE_ARRAY(T, N) \
typedef struct _ ## N ## Array { \
  Object object; \
  jint length __attribute__ ((aligned (8))); \
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
    jlong maxHeapSize;
    jlong initialHeapSize;
    char basePath[PATH_MAX];
    char executablePath[PATH_MAX];
    char** rawBootclasspath; 
    char** rawClasspath; 
    ClasspathEntry* bootclasspath;
    ClasspathEntry* classpath;
    jboolean dynamicJNI;
    Class* (*loadBootClass)(Env*, const char*, ClassLoader*);
    Class* (*loadUserClass)(Env*, const char*, ClassLoader*);
    void (*classInitialized)(Env*, Class*);
    Interface* (*loadInterfaces)(Env*, Class*);
    Field* (*loadFields)(Env*, Class*);
    Method* (*loadMethods)(Env*, Class*);
    Class* (*findClassAt)(Env*, void*);
    jboolean (*exceptionMatch)(Env*, TrycatchContext*);
} Options;

typedef struct VM {
    JavaVM javaVM;
    Options* options;
    jboolean initialized;
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
    uint32_t mxcsr;
    uint16_t fpucw;
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

typedef struct {
    void* pc;
    Method* method;
} CallStackFrame;
typedef struct {
    jint length;
    CallStackFrame frames[0];
} CallStack;

static inline jboolean rvmIsNonNativeFrame(Env* env) {
    // Count the number of GatewayFrames. If the number is odd we're in
    // non native code.
    jint count = 0;
    GatewayFrame* gw = env->gatewayFrames;
    while (gw) {
        count++;
        gw = gw->prev;
    }
    return (count & 1);
}

#endif

