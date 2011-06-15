#ifndef NULLVM_ATTRIBUTE_H
#define NULLVM_ATTRIBUTE_H

extern jboolean nvmInitAttributes(Env* env);
extern Class* nvmAttributeGetDeclaringClass(Env* env, Class* clazz);
extern Class* nvmAttributeGetEnclosingClass(Env* env, Class* clazz);
extern Method* nvmAttributeGetEnclosingMethod(Env* env, Class* clazz);
extern jboolean nvmAttributeIsAnonymousClass(Env* env, Class* clazz);
extern Object* nvmAttributeGetClassSignature(Env* env, Class* clazz);
extern Object* nvmAttributeGetMethodSignature(Env* env, Class* method);
extern Object* nvmAttributeGetFieldSignature(Env* env, Class* field);
extern Object* nvmAttributeGetAnnotationDefault(Env* env, Method* method);
extern ObjectArray* nvmAttributeGetExceptions(Env* env, Method* method);

#endif

