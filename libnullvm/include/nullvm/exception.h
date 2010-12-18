#ifndef NULLVM_EXCEPTION_H
#define NULLVM_EXCEPTION_H

extern Object* nvmExceptionOccurred(Env* env);
extern jboolean nvmExceptionCheck(Env* env);
extern Object* nvmExceptionClear(Env* env);
extern void nvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f);
extern jint nvmThrow(Env* env, Object* e);
extern jint nvmThrowNew(Env* env, Class* clazz, char* message);
extern jint nvmThrowOutOfMemoryError(Env* env);
extern jint nvmThrowNoClassDefFoundError(Env* env, char* name);
extern jint nvmThrowIllegalAccessError(Env* env);
extern jint nvmThrowIllegalAccessErrorField(Env* env, Class* clazz, char* name, char* desc, Class* caller);
extern jint nvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, char* name, char* desc, Class* caller);
extern jint nvmThrowNoSuchFieldError(Env* env, char* name);
extern jint nvmThrowNoSuchMethodError(Env* env, char* name);
extern jint nvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, char* name, char* desc);
extern jint nvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, char* name, char* desc);
extern jint nvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, char* name, char* desc);
extern jint nvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass);
extern jint nvmThrowNullPointerException(Env* env);
extern jint nvmThrowAbstractMethodError(Env* env);
extern jint nvmThrowArrayIndexOutOfBoundsException(Env* env, jint index);
extern jint nvmThrowArrayStoreException(Env* env);
extern jint nvmThrowNegativeArraySizeException(Env* env);
extern jint nvmThrowClassNotFoundException(Env* env, char* name);
extern jint nvmThrowUnsatisfiedLinkError(Env* env);
extern jint nvmThrowIllegalArgumentException(Env* env, char* message);
extern jint nvmThrowVerifyError(Env* env, char* message);
extern void nvmRaiseException(Env* env, Object* e);

#endif

