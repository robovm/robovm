#ifndef NULLVM_EXCEPTION_H
#define NULLVM_EXCEPTION_H

#include <unwind.h>

#if defined(DARWIN)
#define _Unwind_Ptr uintptr_t
#define _Unwind_Word uintptr_t
#define _Unwind_Exception_Class uint64_t
#endif

extern Object* nvmExceptionOccurred(Env* env);
extern jboolean nvmExceptionCheck(Env* env);
extern Object* nvmExceptionClear(Env* env);
extern void nvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f);
extern jint nvmThrow(Env* env, Object* e);
extern jint nvmThrowNew(Env* env, Class* clazz, const char* message);
extern jint nvmThrowOutOfMemoryError(Env* env);
extern jint nvmThrowNoClassDefFoundError(Env* env, const char* name);
extern jint nvmThrowIllegalAccessError(Env* env, const char* message);
extern jint nvmThrowIllegalAccessErrorClass(Env* env, Class* clazz, Class* caller);
extern jint nvmThrowIllegalAccessErrorField(Env* env, Class* clazz, const char* name, const char* desc, Class* caller);
extern jint nvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, const char* name, const char* desc, Class* caller);
extern jint nvmThrowNoSuchFieldError(Env* env, const char* name);
extern jint nvmThrowNoSuchMethodError(Env* env, const char* name);
extern jint nvmThrowIncompatibleClassChangeError(Env* env,const char* message);
extern jint nvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, const char* name, const char* desc);
extern jint nvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, const char* name, const char* desc);
extern jint nvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern jint nvmThrowInstantiationError(Env* env, const char* message);
extern jint nvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass);
extern jint nvmThrowNullPointerException(Env* env);
extern jint nvmThrowAbstractMethodError(Env* env, const char* message);
extern jint nvmThrowArrayIndexOutOfBoundsException(Env* env, jint index);
extern jint nvmThrowArrayStoreException(Env* env);
extern jint nvmThrowNegativeArraySizeException(Env* env);
extern jint nvmThrowClassNotFoundException(Env* env, const char* name);
extern jint nvmThrowUnsatisfiedLinkError(Env* env);
extern jint nvmThrowIllegalArgumentException(Env* env, const char* message);
extern jint nvmThrowVerifyError(Env* env, const char* message);
extern jint nvmThrowArithmeticException(Env* env);
extern jint nvmThrowLinkageError(Env* env);
extern jint nvmThrowIllegalMonitorStateException(Env* env);
extern jint nvmThrowInterruptedException(Env* env);
extern void nvmRaiseException(Env* env, Object* e);
extern void nvmReraiseException(Env* env, void* exInfo);

#endif

