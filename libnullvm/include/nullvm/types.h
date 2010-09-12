#ifndef NULLVM_TYPES_H
#define NULLVM_TYPES_H

#include "../jni_types.h"
#include "../jni.h"

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
typedef struct ObjectHeader ObjectHeader;
typedef struct Interface Interface;
typedef struct Class Class;
typedef struct Object Object;
typedef struct DataObject DataObject;
typedef struct Array Array;

struct Field {
  Field* next;
  char* name;
  char* desc;
  jint access;
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
  char* name;
  char* desc;
  jint access;
  void* impl;
  int vtableIndex;
  char* callInfo;
};

struct Interface {
  Interface* next;
  Class* interface;
};

struct Object {
  Class* clazz;
  /* void* lock */
};

/* 
 * Represents a java.lang.Class instance
 */
struct Class {
  Object object;
  jint _data[1];       // TODO: Reserve the memory needed to store the instance fields for java.lang.Class
  jint id;
  char* name;         // The name in UTF-8.
  char* packageName;         // The package name in UTF-8.
  Class* superclass;  // Superclass pointer. Only java.lang.Object and interfaces have NULL here.
  jint state;
  jint access;
  Object* (*newInstance)(void);
  void (*checkcast)(Object*);
  jint (*instanceof)(Object*);
  Interface* interfaces; // Linked list of interfaces or NULL if there are no interfaces.
  Field* fields; // Linked list of fields.
  Method* methods;       // Method pointers.
  jint classDataSize;
  jint instanceDataOffset; // The offset from the base of Object->data
                            // where the instance fields of this class can be found.
  jint instanceDataSize;   // The number of bytes needed to store the instance fields of this class.
                            // instanceDataOffset + instanceDataSize gives the total number of bytes
                            // needed to store the instance data for instances of this class (and superclasses).
  int vtableSize;
  void** vtable;
  void* data[0];          // This is where static fields are stored for the class
};

struct DataObject {
  Object object;
  void* data[0];
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

typedef struct Options {
    char* mainClass;
} Options;

typedef struct Env {
    struct JNINativeInterface_ jni;
    Object* throwable;
} Env;

#endif

