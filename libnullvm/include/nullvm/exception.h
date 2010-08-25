#ifndef NULLVM_EXCEPTION_H
#define NULLVM_EXCEPTION_H

extern Object* nvmExceptionOccurred(Env* env);
extern jboolean nvmExceptionCheck(Env* env);
extern Object* nvmExceptionClear(Env* env);
extern void nvmThrow(Env* env, Object* e);
extern void nvmThrowOutOfMemoryError(Env* env);
extern void nvmThrowNoClassDefFoundError(Env* env, char* name);
extern void nvmThrowIllegalAccessError(Env* env);
extern void nvmThrowIllegalAccessErrorField(Env* env, Class* clazz, char* name, char* desc, Class* caller);
extern void nvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, char* name, char* desc, Class* caller);
extern void nvmThrowNoSuchFieldError(Env* env, char* name);
extern void nvmThrowNoSuchMethodError(Env* env, char* name);
extern void nvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, char* name, char* desc);
extern void nvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, char* name, char* desc);
extern void nvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, char* name, char* desc);
extern void nvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass);
extern void nvmThrowNullPointerException(Env* env);
extern void nvmThrowAbstractMethodError(Env* env);
extern void nvmThrowArrayIndexOutOfBoundsException(Env* env, jint index);
extern void nvmThrowNegativeArraySizeException(Env* env);
extern void nvmThrowClassNotFoundException(Env* env, char* name);
extern void nvmThrowUnsatisfiedLinkError(Env* env);
extern void nvmRaiseException(Env* env, Object* e);

#endif

