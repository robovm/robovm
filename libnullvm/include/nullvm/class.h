#ifndef NULLVM_CLASS_H
#define NULLVM_CLASS_H

extern Class* array_Z;
extern Class* array_B;
extern Class* array_C;
extern Class* array_S;
extern Class* array_I;
extern Class* array_J;
extern Class* array_F;
extern Class* array_D;
extern Class* java_lang_Object;
extern Class* java_lang_String;
extern Class* java_lang_OutOfMemoryError;
extern Class* java_lang_NoClassDefFoundError;
extern Class* java_lang_IllegalAccessError;
extern Class* java_lang_NoSuchFieldError;
extern Class* java_lang_IncompatibleClassChangeError;
extern Class* java_lang_ClassCastException;
extern Class* java_lang_NullPointerException;
extern Class* java_lang_AbstractMethodError;
extern Class* java_lang_ArrayIndexOutOfBoundsException;
extern Class* java_lang_ClassNotFoundException;
extern Class* java_lang_NegativeArraySizeException;
extern Class* java_lang_UnsatisfiedLinkError;

extern jboolean nvmInitClasses(Env* env);

extern Class* nvmAllocateClass(Env* env, char* className, Class* superclass, jint access, jint classDataSize, jint instanceDataSize);
extern jboolean nvmAddInterface(Env* env, Class* clazz, Class* interface);
extern jboolean nvmAddField(Env* env, Class* clazz, char* name, char* desc, jint access, jint offset);
extern jboolean nvmAddMethod(Env* env, Class* clazz, char* name, char* desc, jint access, void* impl);
extern jboolean nvmRegisterClass(Env* env, Class* clazz);

extern Class* nvmFindClass(Env* env, char* className);

/**
 * Creates a new instance of the specified class.
 */
extern Object* nvmAllocateObject(Env* env, Class* clazz);

extern int nvmIsSubClass(Class* superclass, Class* clazz);
extern int nvmIsSamePackage(Class* c1, Class* c2);

#endif

