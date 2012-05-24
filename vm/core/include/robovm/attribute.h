#ifndef ROBOVM_ATTRIBUTE_H
#define ROBOVM_ATTRIBUTE_H

extern jboolean rvmInitAttributes(Env* env);
extern Class* rvmAttributeGetDeclaringClass(Env* env, Class* clazz);
extern Class* rvmAttributeGetEnclosingClass(Env* env, Class* clazz);
extern Method* rvmAttributeGetEnclosingMethod(Env* env, Class* clazz);
extern jboolean rvmAttributeIsAnonymousClass(Env* env, Class* clazz);
extern Object* rvmAttributeGetClassSignature(Env* env, Class* clazz);
extern Object* rvmAttributeGetMethodSignature(Env* env, Method* method);
extern Object* rvmAttributeGetFieldSignature(Env* env, Field* field);
extern Object* rvmAttributeGetAnnotationDefault(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetExceptions(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetClassRuntimeVisibleAnnotations(Env* env, Class* clazz);
extern ObjectArray* rvmAttributeGetMethodRuntimeVisibleAnnotations(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetFieldRuntimeVisibleAnnotations(Env* env, Field* field);
extern ObjectArray* rvmAttributeGetMethodRuntimeVisibleParameterAnnotations(Env* env, Method* method);
extern ObjectArray* rvmAttributeGetDeclaredClasses(Env* env, Class* clazz);
extern Object* rvmAttributeGetInnerClassName(Env* env, Class* clazz);

#endif

