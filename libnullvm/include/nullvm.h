#ifndef NULLVM_H
#define NULLVM_H

#define GC_THREADS
#include <gc.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include <stdarg.h>

#define LOG(format, args...)  \
  fprintf(stderr, format , ## args)

#define ACC_PUBLIC 0x0001
#define ACC_PRIVATE 0x0002
#define ACC_PROTECTED 0x0004
#define ACC_STATIC 0x0008
#define ACC_FINAL 0x0010
#define ACC_SUPER 0x0020
#define ACC_SYNCHRONIZED 0x0020
#define ACC_VOLATILE 0x0040
#define ACC_BRIDGE 0x0040
#define ACC_VARARGS 0x0080
#define ACC_TRANSIENT 0x0080
#define ACC_NATIVE 0x0100
#define ACC_INTERFACE 0x0200
#define ACC_ABSTRACT 0x0400
#define ACC_STRICT 0x0800
#define ACC_SYNTHETIC 0x1000
#define ACC_ANNOTATION 0x2000
#define ACC_ENUM 0x4000

typedef int64_t jlong;
typedef int32_t jint;
typedef int16_t jshort;
typedef int8_t jbyte;
typedef uint16_t jchar;
typedef int8_t jboolean;
typedef float jfloat;
typedef double jdouble;

struct _jmethod;
typedef struct _jmethod jmethod;
struct _jmethod {
  jmethod* next;
  char* name;
  char* desc;
  jint access;
  void* impl;
  int vtableIndex;
};

struct _jfield;
typedef struct _jfield jfield;
struct _jfield {
  jfield* next;
  char* name;
  char* desc;
  jint access;
  jint offset;
  void* getter;
  void* setter;
};

struct _jclass;
typedef struct _jclass jclass;
struct _jclass {
  char* name;         // The name in UTF-8.
  char* packageName;         // The package name in UTF-8.
  jclass* superclass;  // Superclass pointer. Only java.lang.Object has NULL here.
//  jclass* interfaces; // List of interfaces or NULL if there are no interfaces.
//  int interfaces_count; // Number of interfaces in interface_count.
  jfield* fields; // Linked list of fields.
  jmethod* methods;       // Method pointers.
  jint classDataSize;
  jint instanceDataOffset; // The offset from the base of jobject->data
                            // where the instance fields of this class can be found.
  jint instanceDataSize;   // The number of bytes needed to store the instance fields of this class.
                            // instanceDataOffset + instanceDataSize gives the total number of bytes
                            // needed to store the instance data for instances of this class (and superclasses).
  int vtableSize;
  void** vtable;
  void* data[0];
};

struct _jobject;
typedef struct _jobject jobject;
struct _jobject {
  jclass* clazz;
  void* data[0];
};

typedef struct _jarray {
  jclass* clazz;
  jint length;
} jarray;
typedef struct _jobject_array {
  jclass* clazz;
  jint length;
  jobject* values[0];
} jobject_array;

#define MAKE_ARRAY(T) \
typedef struct _ ## T ## _array { \
  jclass* clazz; \
  jint length; \
  T values[0]; \
} T ## _array;

MAKE_ARRAY(jlong)
MAKE_ARRAY(jint)
MAKE_ARRAY(jbyte)
MAKE_ARRAY(jshort)
MAKE_ARRAY(jchar)
MAKE_ARRAY(jboolean)
MAKE_ARRAY(jfloat)
MAKE_ARRAY(jdouble)

extern void* j_get_method_impl(jclass* clazz, char* name, char* desc, jclass* caller);
extern jint j_get_vtable_index(jclass* clazz, char* name, char* desc, jclass* caller);
extern jobject* j_ldc_string_asciiz(char* s);
extern jobject* j_ldc_string_utf8z(char* s);

/*
 * init.c
 */
extern void nvmStartup(void);
extern void nvmAbort(char* format, ...);

/*
 * array.c
 */

extern jarray* nvmNewArray(jint type, jint length);
extern jarray* nvmANewArray(char* type, jint length);
extern jarray* nvmMultiANewarray(char* type, jint dims, jint* lengths);

/*
 * exception.c
 */

extern void nvmThrow(jobject* e);
extern void nvmThrowNoClassDefFoundError(char* name);
extern void nvmThrowIllegalAccessError(void);
extern void nvmThrowIllegalAccessErrorField(jclass* clazz, char* name, char* desc, jclass* caller);
extern void nvmThrowNoSuchFieldError(char* name);
extern void nvmThrowIncompatibleClassChangeErrorClassField(jclass* clazz, char* name, char* desc);
extern void nvmThrowIncompatibleClassChangeErrorInstanceField(jclass* clazz, char* name, char* desc);
extern void nvmThrowClassCastException(jclass* expectedClass, jclass* actualClass);
extern void nvmThrowNullPointerException(void);
extern void nvmThrowAbstractMethodError(void);
extern void nvmThrowArrayIndexOutOfBoundsException(jint index);
extern void nvmThrowNegativeArraySizeException(void);
extern void nvmThrowClassNotFoundException(char* name);

/*
 * class.c
 */

extern jclass* nvmAllocateClass(char* class_name, jclass* superclass, jint classDataSize, jint instanceDataSize);
extern void nvmAddField(jclass* clazz, char* name, char* desc, jint access, jint offset);
extern void nvmAddInterface(jclass* clazz, char* interface_name);
extern void nvmAddMethod(jclass* clazz, char* name, char* desc, jint access, void* impl);
extern void nvmRegisterClass(jclass* clazz);


/**
 * Returns the class with the specified name. Locates and loads it if it hasn't
 * already been loaded. Pass NULL caller to disable access checks.
 *
 * @throws ClassNotFoundException
 * @throws IllegalAccessError if the class isn't accessible to the caller class.
 */
extern jclass* nvmGetClass(char* className, char* mangledClassName, jclass* caller);

extern jclass* nvmGetArrayClass(char* className);


/**
 * Creates a new instance of the specified class.
 */
extern jobject* nvmNewInstance(jclass* clazz);

extern int nvmIsSubClass(jclass* superclass, jclass* clazz);
extern int nvmIsSamePackage(jclass* c1, jclass* c2);

extern void nvmCheckcast(jobject* o, jclass* clazz);
extern jboolean nvmInstanceof(jobject* o, jclass* clazz);

/*
 * field.c
 */

/**
 * Returns the field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 */
extern jfield* nvmGetField(jclass* clazz, char* name, char* desc, jclass* caller);

/**
 * Returns the class field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field hasn't got the ACC_STATIC modifier.
 */
extern jfield* nvmGetClassField(jclass* clazz, char* name, char* desc, jclass* caller);

/**
 * Returns the instance field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field has got the ACC_STATIC modifier.
 */
extern jfield* nvmGetInstanceField(jclass* clazz, char* name, char* desc, jclass* caller);

extern void* nvmGetClassFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetClassFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetInstanceFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetInstanceFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmCreateFieldGetter(jclass* clazz, jfield* field);
extern void* nvmCreateFieldSetter(jclass* clazz, jfield* field);
extern jbyte (*nvmCreateClassFieldGetter8(jbyte* ptr))(void);
extern jshort (*nvmCreateClassFieldGetter16(jshort* ptr))(void);
extern jint (*nvmCreateClassFieldGetter32(int* ptr))(void);
extern jlong (*nvmCreateClassFieldGetter64(jlong* ptr))(void);
extern jfloat (*nvmCreateClassFieldGetterFloat(jfloat* ptr))(void);
extern jdouble (*nvmCreateClassFieldGetterDouble(jdouble* ptr))(void);
extern void (*nvmCreateClassFieldSetter8(jbyte* ptr))(jbyte);
extern void (*nvmCreateClassFieldSetter16(jshort* ptr))(jshort);
extern void (*nvmCreateClassFieldSetter32(jint* ptr))(jint);
extern void (*nvmCreateClassFieldSetter64(jlong* ptr))(jlong);
extern void (*nvmCreateClassFieldSetterFloat(jfloat* ptr))(jfloat);
extern void (*nvmCreateClassFieldSetterDouble(jdouble* ptr))(jdouble);
extern jbyte (*nvmCreateInstanceFieldGetter8(jint offset))(jobject*);
extern jshort (*nvmCreateInstanceFieldGetter16(jint offset))(jobject*);
extern jint (*nvmCreateInstanceFieldGetter32(jint offset))(jobject*);
extern void (*nvmCreateInstanceFieldSetter8(jint offset))(jobject*, jbyte);
extern void (*nvmCreateInstanceFieldSetter16(jint offset))(jobject*, jshort);
extern void (*nvmCreateInstanceFieldSetter32(jint offset))(jobject*, jint);

#endif

