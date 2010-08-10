#ifndef NULLVM_H
#define NULLVM_H

#define GC_THREADS
#include <gc.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include <stdarg.h>
#include "jni_types.h"

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

#define IS_PRIVATE(access) (access & ACC_PRIVATE)
#define IS_PROTECTED(access) (access & ACC_PROTECTED)
#define IS_PUBLIC(access) (access & ACC_PUBLIC)
#define IS_PACKAGE_PRIVATE(access) (!IS_PRIVATE(access) && !IS_PROTECTED(access) && !IS_PUBLIC(access))
#define IS_FINAL(access) (access & ACC_FINAL)
#define IS_NATIVE(access) (access & ACC_NATIVE)
#define IS_SYNCHRONIZED(access) (access & ACC_SYNCHRONIZED)
#define IS_ABSTRACT(access) (access & ACC_ABSTRACT)

/*typedef int64_t jlong;
typedef int32_t jint;
typedef int16_t jshort;
typedef int8_t jbyte;
typedef uint16_t jchar;
typedef uint8_t jboolean;
typedef float jfloat;
typedef double jdouble;*/

struct _Object;
typedef struct _Object Object;
struct _Class;
typedef struct _Class Class;
struct _Method;
typedef struct _Method Method;
struct _Field;
typedef struct _Field Field;
struct _Array;
typedef struct _Array Array;

struct _Method {
  Method* next;
  char* name;
  char* desc;
  jint access;
  void* impl;
  void* wrapper;
  int vtableIndex;
};

struct _Field {
  Field* next;
  char* name;
  char* desc;
  jint access;
  jint offset;
  void* getter;
  void* setter;
};

struct _Class {
  char* name;         // The name in UTF-8.
  char* packageName;         // The package name in UTF-8.
  Class* superclass;  // Superclass pointer. Only java.lang.Object has NULL here.
  jint access;
  Object* (*newInstance)(void);
  void (*checkcast)(Object*);
  jint (*instanceof)(Object*);
  Class** interfaces; // List of interfaces or NULL if there are no interfaces.
  jint interfaceCount; // Number of interfaces implemented by this class.
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

struct _Object {
  Class* clazz;
  void* data[0];
};

struct _Array {
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

extern void* j_get_method_impl(Class* clazz, char* name, char* desc, Class* caller);
extern jint j_get_vtable_index(Class* clazz, char* name, char* desc, Class* caller);
extern Object* j_ldc_string_asciiz(char* s);
extern Object* j_ldc_string_utf8z(char* s);

/*
 * init.c
 */
extern void nvmStartup(void);
extern void* nvmAllocateMemory(int size);
extern void* nvmAllocateExecutableMemory(int size);
extern void nvmAbort(char* format, ...);
extern void* nvmGetCurrentJNIEnv(void);

/*
 * array.c
 */

extern Array* nvmNewArray(jint type, jint length);
extern Array* nvmANewArray(char* type, jint length);
extern Array* nvmMultiANewarray(char* type, jint dims, jint* lengths);

/*
 * exception.c
 */

extern void nvmThrow(Object* e);
extern void nvmThrowNoClassDefFoundError(char* name);
extern void nvmThrowIllegalAccessError(void);
extern void nvmThrowIllegalAccessErrorField(Class* clazz, char* name, char* desc, Class* caller);
extern void nvmThrowIllegalAccessErrorMethod(Class* clazz, char* name, char* desc, Class* caller);
extern void nvmThrowNoSuchFieldError(char* name);
extern void nvmThrowNoSuchMethodError(char* name);
extern void nvmThrowIncompatibleClassChangeErrorClassField(Class* clazz, char* name, char* desc);
extern void nvmThrowIncompatibleClassChangeErrorInstanceField(Class* clazz, char* name, char* desc);
extern void nvmThrowIncompatibleClassChangeErrorMethod(Class* clazz, char* name, char* desc);
extern void nvmThrowClassCastException(Class* expectedClass, Class* actualClass);
extern void nvmThrowNullPointerException(void);
extern void nvmThrowAbstractMethodError(void);
extern void nvmThrowArrayIndexOutOfBoundsException(jint index);
extern void nvmThrowNegativeArraySizeException(void);
extern void nvmThrowClassNotFoundException(char* name);
extern void nvmThrowUnsatisfiedLinkError(void);

/*
 * class.c
 */

extern Class* nvmAllocateClass(char* class_name, Class* superclass, Class** interfaces, jint access, jint classDataSize, jint instanceDataSize);
extern void nvmAddField(Class* clazz, char* name, char* desc, jint access, jint offset);
extern void nvmAddMethod(Class* clazz, char* name, char* desc, jint access, void* impl);
extern void nvmRegisterClass(Class* clazz);

/**
 * Returns the class with the specified name. Locates and loads it if it hasn't
 * already been loaded. Pass NULL caller to disable access checks.
 *
 * @throws ClassNotFoundException
 * @throws IllegalAccessError if the class isn't accessible to the caller class.
 */
extern Class* nvmGetClass(char* className, char* mangledClassName, Class* caller);

extern Class* nvmGetArrayClass(char* className);


/**
 * Creates a new instance of the specified class.
 */
extern Object* nvmNewInstance(Class* clazz);

extern int nvmIsSubClass(Class* superclass, Class* clazz);
extern int nvmIsSamePackage(Class* c1, Class* c2);

extern void nvmCheckcast(Object* o, Class* clazz);
extern jboolean nvmInstanceof(Object* o, Class* clazz);

extern void* nvmGetCheckcastFunction(char* className, char* mangledClassName, Class* caller, void** functionPtr);
extern void* nvmGetInstanceofFunction(char* className, char* mangledClassName, Class* caller, void** functionPtr);
extern void* nvmGetNewInstanceFunction(char* className, char* mangledClassName, Class* caller, void** functionPtr);

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
extern Field* nvmGetField(Class* clazz, char* name, char* desc, Class* caller);

/**
 * Returns the class field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field hasn't got the ACC_STATIC modifier.
 */
extern Field* nvmGetClassField(Class* clazz, char* name, char* desc, Class* caller);

/**
 * Returns the instance field with the specified name and descriptor defined by the specified
 * class or one of its super classes.
 *
 * @throws NoSuchFieldError
 * @throws IllegalAccessError if the field isn't accessible to the caller class.
 * @throws IncompatibleClassChangeError if the field has got the ACC_STATIC modifier.
 */
extern Field* nvmGetInstanceField(Class* clazz, char* name, char* desc, Class* caller);

extern void* nvmGetClassFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetClassFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetInstanceFieldGetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmGetInstanceFieldSetter(char* className, char* mangledClassName, char* fieldName, char* fieldDesc, void* caller, void** functionPtr);
extern void* nvmCreateFieldGetter(Class* clazz, Field* field);
extern void* nvmCreateFieldSetter(Class* clazz, Field* field);
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
extern jbyte (*nvmCreateInstanceFieldGetter8(jint offset))(Object*);
extern jshort (*nvmCreateInstanceFieldGetter16(jint offset))(Object*);
extern jint (*nvmCreateInstanceFieldGetter32(jint offset))(Object*);
extern void (*nvmCreateInstanceFieldSetter8(jint offset))(Object*, jbyte);
extern void (*nvmCreateInstanceFieldSetter16(jint offset))(Object*, jshort);
extern void (*nvmCreateInstanceFieldSetter32(jint offset))(Object*, jint);

/*
 * method.c
 */
extern Method* nvmGetMethod(Class* clazz, char* name, char* desc, Class* caller);
extern void* nvmGetNativeMethodImpl(char* shortMangledName, char* longMangledName, void** functionPtr);
extern void* nvmGetInvokeStaticFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr);
extern void* nvmGetInvokeVirtualFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr);
extern void* nvmGetInvokeInterfaceFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr);
extern void* nvmGetInvokeSpecialFunction(char* className, char* mangledClassName, char* methodName, char* methodDesc, void* caller, void** functionPtr);
extern void* nvmCreateMethodWrapper(Class* clazz, Method* method);

#endif

