#ifndef NULLVM_TYPES_H
#define NULLVM_TYPES_H

#include <jni_types.h>
#include <jni.h>
#include <limits.h>

#undef FALSE
#undef TRUE
#define FALSE JNI_FALSE
#define TRUE JNI_TRUE

#define CLASS_ALLOCATED 0
#define CLASS_LOADED 1
#define CLASS_VERIFIED 2
#define CLASS_PREPARED 3
#define CLASS_INITIALIZING 4
#define CLASS_INITIALIZED 5
#define CLASS_ERROR 6

struct HyThreadMonitor;

typedef struct Field Field;
typedef struct ClassField ClassField;
typedef struct InstanceField InstanceField;
typedef struct Method Method;
typedef struct Methods Methods;
typedef struct ObjectHeader ObjectHeader;
typedef struct Interface Interface;
typedef struct Exception Exception;
typedef struct Class Class;
typedef struct Object Object;
typedef struct ClassLoader ClassLoader;
typedef struct DataObject DataObject;
typedef struct HyThreadMonitor Monitor;
typedef struct Thread Thread;
typedef struct Array Array;
typedef struct EnclosingMethod EnclosingMethod;
typedef struct InnerClass InnerClass;

struct Field {
  Field* next;
  Class* clazz;
  char* name;
  char* desc;
  jint access;
  char* attributes;
  void* getter;
  void* setter;
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
  char* name;
  char* desc;
  jint access;
  void* attributes;
  void* impl;
  void* synchronizedImpl;
  void* lookup;
  jint vtableIndex;
};

struct Methods {
  Method* first;
  void* lo;
  void* hi;
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
  Monitor* monitor;
};

/* 
 * Represents a java.lang.Class instance
 */
struct Class {
  Object object;
  void* _data;             // Reserve the memory needed to store the instance fields for java.lang.Class. 
                           // java.lang.Class has a single field, (SoftReference<ClassCache<T>> cacheRef).
                           // void* gives enough space to store that reference.
  jint id;
  char* name;              // The name in modified UTF-8.
  ClassLoader* classLoader;
  Class* superclass;       // Superclass pointer. Only java.lang.Object, primitive classes and interfaces have NULL here.
  jboolean primitive;      // If true this represents a primitive type class.
  jint state;
  jint access;
  Interface* interfaces;   // Linked list of interfaces or NULL if there are no interfaces.
  Field* fields;           // Linked list of fields.
  Methods* methods;        // Linked list of methods.
  void* attributes;
  jint classDataSize;
  jint instanceDataOffset; // The offset from the base of Object->data
                           // where the instance fields of this class can be found.
  jint instanceDataSize;   // The number of bytes needed to store the instance fields declared by this class.
                           // instanceDataOffset + instanceDataSize gives the total number of bytes
                           // needed to store the instance data for instances of this class including superclasses.
  jint vtableSize;
  void** vtable;
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

struct Thread {
  Object object;
  jlong threadPtr;
  Object* threadGroup;
  jlong id;
  Object* name;
  jboolean daemon;
  jint priority;
  Object* target;
  Object* action;
  ClassLoader* contextClassLoader;
  Object* uncaughtExceptionHandler;
  jboolean started;
  Object* localValues;
  Object* inheritableValues;
};

struct Array {
  Object object;
  jint length;
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

typedef struct Options {
    char* mainClass;
    char** commandLineArgs;
    jint commandLineArgsCount;
    jint logLevel;
    char basePath[PATH_MAX];
    char executablePath[PATH_MAX];
    char bootLibPath[PATH_MAX];
    char mainLibPath[PATH_MAX];
    ClasspathEntry* bootclasspath;
    ClasspathEntry* classpath;
    Class* (*bootclasspathFunc)(Env*, char*, ClassLoader*);
    Class* (*classpathFunc)(Env*, char*, ClassLoader*);
} Options;

typedef struct VM {
    Options* options;
} VM;

struct Env {
    JNIEnv jni;
    VM* vm;
    Object* throwable;
    Thread* currentThread;
    void* reserved0; // Used internally
    void* reserved1; // Used internally
    void* reserved2; // Used internally
};

typedef struct CallStackEntry CallStackEntry;
struct CallStackEntry {
    CallStackEntry* next;
    CallStackEntry* prev;
    Method* method;
    jint offset;
};

#endif

