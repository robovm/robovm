#ifndef NULLVM_TYPES_H
#define NULLVM_TYPES_H

#include "jni_types.h"

#define FALSE 0
#define TRUE 1

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
typedef struct Method Method;
typedef struct Class Class;
typedef struct Object Object;
typedef struct Array Array;

struct Field {
  Field* next;
  char* name;
  char* desc;
  jint access;
  jint offset;
  void* getter;
  void* setter;
};

struct Method {
  Method* next;
  char* name;
  char* desc;
  jint access;
  void* impl;
  void* wrapper;
  int vtableIndex;
};

struct Class {
  jint id;
  char* name;         // The name in UTF-8.
  char* packageName;         // The package name in UTF-8.
  Class* superclass;  // Superclass pointer. Only java.lang.Object has NULL here.
  jint access;
  Object* (*newInstance)(void);
  void (*checkcast)(Object*);
  jint (*instanceof)(Object*);
  Map* interfaces; // Map of interfaces or NULL if there are no interfaces. Interface IDs are used as keys.
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
  void* data[0];
};

struct Object {
  Class* clazz;
  void* data[0];
};

struct Array {
  Class* clazz;
  jint length;
};

#define MAKE_ARRAY(T, N) \
typedef struct _ ## N ## Array { \
  Class* clazz; \
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
    Object* throwable;
} Env;

#endif

