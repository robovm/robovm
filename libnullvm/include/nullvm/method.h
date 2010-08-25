#ifndef NULLVM_METHOD_H
#define NULLVM_METHOD_H

extern Method* nvmGetMethod(Env* env, Class* clazz, char* name, char* desc);
extern Method* nvmGetClassMethod(Env* env, Class* clazz, char* name, char* desc);
extern Method* nvmGetInstanceMethod(Env* env, Class* clazz, char* name, char* desc);
extern void* nvmGetNativeMethod(Env* env, char* shortMangledName, char* longMangledName);

#endif

