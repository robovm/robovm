#ifndef NULLVM_TYPES_H
#define NULLVM_TYPES_H

#include "../jni_types.h"
#include "../jni.h"
#include <hythread.h>
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

union _MapKey;
typedef union _MapKey MapKey;
union _MapKey {
    void* p;
    jint i;
};

struct _MapEntry;
typedef struct _MapEntry MapEntry;
struct _MapEntry {
    MapEntry* next;
    jint hash;
    MapKey key;
    void* value;
};

struct _Map {
    jint (*hashFunction)(MapKey key);
    jint (*equalsFunction)(MapKey a, MapKey b);
    jint size;
    jint capacity;
    MapEntry** buckets;
};
typedef struct _Map Map;

typedef struct Field Field;
typedef struct ClassField ClassField;
typedef struct InstanceField InstanceField;
typedef struct Method Method;
typedef struct Methods Methods;
typedef struct ObjectHeader ObjectHeader;
typedef struct Interface Interface;
typedef struct Class Class;
typedef struct Object Object;
typedef struct ClassLoader ClassLoader;
typedef struct DataObject DataObject;
typedef struct Array Array;

struct Field {
  Field* next;
  Class* clazz;
  char* name;
  char* desc;
  jint access;
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
  char** exceptions;
  void* impl;
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

struct Object {
  Class* clazz;
  hythread_monitor_t monitor;
};

/* 
 * Represents a java.lang.Class instance
 */
struct Class {
  Object object;
  void* _data;             // Reserve the memory needed to store the instance fields for java.lang.Class. 
                           // java.lang.Class has a single field, name, which is of type String.
                           // void* gives enough space to store that reference.
  jint id;
  char* name;              // The name in modified UTF-8.
  ClassLoader* classLoader;
  Class* superclass;       // Superclass pointer. Only java.lang.Object and interfaces have NULL here.
  jboolean primitive;      // If true this represents a primitive type class.
  jint state;
  jint access;
  Interface* interfaces;   // Linked list of interfaces or NULL if there are no interfaces.
  Field* fields;           // Linked list of fields.
  Methods* methods;        // Linked list of methods.
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

#define LOG_LEVEL_TRACE 1
#define LOG_LEVEL_WARN 2
#define LOG_LEVEL_ERROR 3
#define LOG_LEVEL_NONE 4

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
    char soPath[PATH_MAX];
};

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
} Options;

typedef struct Thread {
    hythread_t hyThread;
    Object* threadObj;
} Thread;

typedef struct Env {
    JNIEnv jni;
    Object* throwable;
    Options* options;
    Thread* currentThread;
} Env;

typedef struct CallStackEntry CallStackEntry;
struct CallStackEntry {
    CallStackEntry* next;
    Method* method;
    jint offset;
};

#endif

