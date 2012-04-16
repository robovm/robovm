#ifndef NULLVM_CLASS_H
#define NULLVM_CLASS_H

#define CLASS_ACCESS_MASK        0x0000FFFF
#define CLASS_STATE_MASK         0x00070000
#define CLASS_STATE_ALLOCATED    0x00000000
#define CLASS_STATE_LOADED       0x00010000
#define CLASS_STATE_INITIALIZING 0x00020000
#define CLASS_STATE_INITIALIZED  0x00030000
#define CLASS_STATE_ERROR        0x00040000
#define CLASS_FLAG_PRIMITIVE     0x10000000
#define CLASS_FLAG_ARRAY         0x20000000
#define CLASS_FLAG_PROXY         0x40000000

#define CLASS_IS_INTERFACE(c) (IS_INTERFACE((c)->flags))
#define CLASS_IS_PUBLIC(c) (IS_PUBLIC((c)->flags))
#define CLASS_IS_STATIC(c) (IS_STATIC((c)->flags))
#define CLASS_IS_FINAL(c) (IS_FINAL((c)->flags))
#define CLASS_IS_ABSTRACT(c) (IS_ABSTRACT((c)->flags))
#define CLASS_IS_PACKAGE_PRIVATE(c) (IS_PACKAGE_PRIVATE((c)->flags))
#define CLASS_IS_PRIMITIVE(c) ((c)->flags & CLASS_FLAG_PRIMITIVE)
#define CLASS_IS_ARRAY(c) ((c)->flags & CLASS_FLAG_ARRAY)
#define CLASS_IS_PROXY(c) ((c)->flags & CLASS_FLAG_PROXY)
#define CLASS_IS_ENUM(c) (IS_ENUM((c)->flags))
#define CLASS_IS_ANNOTATION(c) (IS_ANNOTATION((c)->flags))

#define CLASS_IS_STATE_ALLOCATED(c) (((c)->flags & CLASS_STATE_MASK) == CLASS_STATE_ALLOCATED)
#define CLASS_IS_STATE_LOADED(c) (((c)->flags & CLASS_STATE_MASK) == CLASS_STATE_LOADED)
#define CLASS_IS_STATE_INITIALIZING(c) (((c)->flags & CLASS_STATE_MASK) == CLASS_STATE_INITIALIZING)
#define CLASS_IS_STATE_INITIALIZED(c) (((c)->flags & CLASS_STATE_MASK) == CLASS_STATE_INITIALIZED)
#define CLASS_IS_STATE_ERROR(c) (((c)->flags & CLASS_STATE_MASK) == CLASS_STATE_ERROR)

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

extern Class* nvmAllocateClass(Env* env, const char* className, Class* superclass, ClassLoader* classLoader, jint flags, jint classDataSize, jint instanceDataSize, void* attributes, void* initializer);
extern jboolean nvmAddInterface(Env* env, Class* clazz, Class* interface);
extern Field* nvmAddField(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint offset, void* attributes);
extern Method* nvmAddMethod(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint size, void* impl, void* synchronizedImpl, void* attributes);
extern BridgeMethod* nvmAddBridgeMethod(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint size, void* impl, 
        void* synchronizedImpl, void** targetFnPtr, void* attributes);
extern CallbackMethod* nvmAddCallbackMethod(Env* env, Class* clazz, const char* name, const char* desc, jint access, jint size, void* impl, 
		void* synchronizedImpl, void* callbackImpl, void* attributes);
extern jboolean nvmRegisterClass(Env* env, Class* clazz);

extern Class* nvmFindClass(Env* env, const char* className);
extern Class* nvmFindClassInClasspathForLoader(Env* env, const char* className, ClassLoader* classLoader);
extern Class* nvmFindClassUsingLoader(Env* env, const char* className, ClassLoader* classLoader);
extern Class* nvmFindClassByDescriptor(Env* env, const char* desc, ClassLoader* classLoader);
extern Class* nvmFindLoadedClass(Env* env, const char* className, ClassLoader* classLoader);
extern ClassLoader* nvmGetSystemClassLoader(Env* env);

extern Interface* nvmGetInterfaces(Env* env, Class* clazz);
extern Field* nvmGetFields(Env* env, Class* clazz);
extern Method* nvmGetMethods(Env* env, Class* clazz);

extern void nvmIterateLoadedClasses(Env* env, jboolean (*f)(Env*, Class*, void*), void* data);

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

extern char* nvmToBinaryClassName(Env* env, const char* className);
extern char* nvmFromBinaryClassName(Env* env, const char* binaryClassName);

extern jboolean nvmIsSubClass(Class* superclass, Class* clazz);
extern jboolean nvmIsSamePackage(Class* c1, Class* c2);

extern jboolean nvmIsAssignableFrom(Env* env, Class* sub, Class* sup);
extern jboolean nvmIsInstanceOf(Env* env, Object* obj, Class* clazz);

#endif

