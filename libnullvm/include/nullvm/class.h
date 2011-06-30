#ifndef NULLVM_CLASS_H
#define NULLVM_CLASS_H

#define CLASS_IS_INTERFACE(c) (IS_INTERFACE((c)->access))
#define CLASS_IS_PUBLIC(c) (IS_PUBLIC((c)->access))
#define CLASS_IS_STATIC(c) (IS_STATIC((c)->access))
#define CLASS_IS_FINAL(c) (IS_FINAL((c)->access))
#define CLASS_IS_ABSTRACT(c) (IS_ABSTRACT((c)->access))
#define CLASS_IS_PACKAGE_PRIVATE(c) (IS_PACKAGE_PRIVATE((c)->access))
#define CLASS_IS_PRIMITIVE(c) ((c)->primitive)
#define CLASS_IS_ARRAY(c) ((c)->name[0] == '[')
#define CLASS_IS_ARRAY_OF_PRIMITIVE(c) ((c)->name[0] == '[' && (c)->name[2] == '\0')
#define CLASS_IS_ENUM(c) (IS_ENUM((c)->access))
#define CLASS_IS_ANNOTATION(c) (IS_ANNOTATION((c)->access))

extern Class* java_lang_Object;
extern Class* java_lang_Class;
extern Class* java_lang_ClassLoader;
extern Class* java_lang_String;
extern Class* java_lang_Boolean;
extern Class* java_lang_Byte;
extern Class* java_lang_Character;
extern Class* java_lang_Short;
extern Class* java_lang_Integer;
extern Class* java_lang_Long;
extern Class* java_lang_Float;
extern Class* java_lang_Double;
extern Class* java_lang_Enum;
extern Class* java_lang_Cloneable;
extern Class* java_lang_Thread;
extern Class* java_lang_ThreadGroup;
extern Class* java_io_Serializable;

extern Class* java_lang_Error;
extern Class* java_lang_Throwable;
extern Class* java_lang_OutOfMemoryError;
extern Class* java_lang_NoClassDefFoundError;
extern Class* java_lang_IllegalAccessError;
extern Class* java_lang_NoSuchFieldError;
extern Class* java_lang_NoSuchMethodError;
extern Class* java_lang_IncompatibleClassChangeError;
extern Class* java_lang_AbstractMethodError;
extern Class* java_lang_UnsatisfiedLinkError;
extern Class* java_lang_VerifyError;
extern Class* java_lang_LinkageError;
extern Class* java_lang_InstantiationError;

extern Class* java_lang_RuntimeException;
extern Class* java_lang_ClassCastException;
extern Class* java_lang_NullPointerException;
extern Class* java_lang_ArrayIndexOutOfBoundsException;
extern Class* java_lang_ArrayStoreException;
extern Class* java_lang_ClassNotFoundException;
extern Class* java_lang_NegativeArraySizeException;
extern Class* java_lang_IllegalArgumentException;
extern Class* java_lang_ArithmeticException;
extern Class* java_lang_UnsupportedOperationException;
extern Class* java_lang_IllegalMonitorStateException;
extern Class* java_lang_InstantiationException;
extern Class* java_lang_InterruptedException;

extern Class* prim_Z;
extern Class* prim_B;
extern Class* prim_C;
extern Class* prim_S;
extern Class* prim_I;
extern Class* prim_J;
extern Class* prim_F;
extern Class* prim_D;
extern Class* prim_V;

extern Class* array_Z;
extern Class* array_B;
extern Class* array_C;
extern Class* array_S;
extern Class* array_I;
extern Class* array_J;
extern Class* array_F;
extern Class* array_D;

extern jboolean nvmInitClasses(Env* env);
extern jboolean nvmInitPrimitiveWrapperClasses(Env* env);

extern Class* nvmAllocateClass(Env* env, char* className, Class* superclass, ClassLoader* classLoader, jint access, jint classDataSize, jint instanceDataSize);
extern jboolean nvmAddInterface(Env* env, Class* clazz, Class* interface);
extern Field* nvmAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset, void* getter, void* setter);
extern Method* nvmAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl, void* synchronizedImpl, void* lookup);
extern void nvmAddMethodException(Env* env, Method* method, char* className);
extern jboolean nvmRegisterClass(Env* env, Class* clazz);

extern Class* nvmFindClass(Env* env, char* className);
extern Class* nvmFindClassInClasspathForLoader(Env* env, char* className, ClassLoader* classLoader);
extern Class* nvmFindClassUsingLoader(Env* env, char* className, ClassLoader* classLoader);
extern Class* nvmFindClassByDescriptor(Env* env, char* desc, ClassLoader* classLoader);
extern Class* nvmFindLoadedClass(Env* env, char* className, ClassLoader* classLoader);

extern Class* nvmGetComponentType(Env* env, Class* arrayClass);

extern void nvmIterateLoadedClasses(Env* env, jboolean (*f)(Class*, void*), void* data);

/**
 * Creates a new instance of the specified class.
 */
extern Object* nvmAllocateObject(Env* env, Class* clazz);
extern void nvmInitialize(Env* env, Class* clazz);
extern Object* nvmNewObject(Env* env, Class* clazz, Method* method, ...);
extern Object* nvmNewObjectA(Env* env, Class* clazz, Method* method, jvalue* args);
extern Object* nvmNewObjectV(Env* env, Class* clazz, Method* method, va_list args);

extern Boolean* nvmNewBoolean(Env* env, jboolean value);
extern Byte* nvmNewByte(Env* env, jbyte value);
extern Short* nvmNewShort(Env* env, jshort value);
extern Character* nvmNewCharacter(Env* env, jchar value);
extern Integer* nvmNewInteger(Env* env, jint value);
extern Long* nvmNewLong(Env* env, jlong value);
extern Float* nvmNewFloat(Env* env, jfloat value);
extern Double* nvmNewDouble(Env* env, jdouble value);
extern Object* nvmWrapPrimitive(Env* env, Class* type, jvalue* value);

extern Object* nvmCloneObject(Env* env, Object* obj);

extern char* nvmToBinaryClassName(Env* env, char* className);
extern char* nvmFromBinaryClassName(Env* env, char* binaryClassName);

extern jboolean nvmIsSubClass(Class* superclass, Class* clazz);
extern jboolean nvmIsSamePackage(Class* c1, Class* c2);

extern jboolean nvmIsAssignableFrom(Env* env, Class* sub, Class* sup);
extern jboolean nvmIsInstanceOf(Env* env, Object* obj, Class* clazz);

#endif

