#ifndef ROBOVM_EXCEPTION_H
#define ROBOVM_EXCEPTION_H

#include <unwind.h>

#if defined(DARWIN)
#define _Unwind_Ptr uintptr_t
#define _Unwind_Word uintptr_t
#define _Unwind_Exception_Class uint64_t
#endif

extern Object* rvmExceptionOccurred(Env* env);
extern jboolean rvmExceptionCheck(Env* env);
extern Object* rvmExceptionClear(Env* env);
extern void rvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f);
extern jint rvmThrow(Env* env, Object* e);
extern jint rvmThrowNew(Env* env, Class* clazz, const char* message);
extern jint rvmThrowOutOfMemoryError(Env* env);
extern jint rvmThrowNoClassDefFoundError(Env* env, const char* name);
extern jint rvmThrowIllegalAccessError(Env* env, const char* message);
extern jint rvmThrowIllegalAccessErrorClass(Env* env, Class* clazz, Class* caller);
extern jint rvmThrowIllegalAccessErrorField(Env* env, Class* clazz, const char* name, const char* desc, Class* caller);
extern jint rvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, const char* name, const char* desc, Class* caller);
extern jint rvmThrowNoSuchFieldError(Env* env, const char* name);
extern jint rvmThrowNoSuchMethodError(Env* env, const char* name);
extern jint rvmThrowIncompatibleClassChangeError(Env* env,const char* message);
extern jint rvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, const char* name, const char* desc);
extern jint rvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, const char* name, const char* desc);
extern jint rvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern jint rvmThrowInstantiationError(Env* env, const char* message);
extern jint rvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass);
extern jint rvmThrowNullPointerException(Env* env);
extern jint rvmThrowAbstractMethodError(Env* env, const char* message);
extern jint rvmThrowArrayIndexOutOfBoundsException(Env* env, jint index);
extern jint rvmThrowArrayStoreException(Env* env);
extern jint rvmThrowNegativeArraySizeException(Env* env);
extern jint rvmThrowClassNotFoundException(Env* env, const char* name);
extern jint rvmThrowUnsatisfiedLinkError(Env* env);
extern jint rvmThrowIllegalArgumentException(Env* env, const char* message);
extern jint rvmThrowVerifyError(Env* env, const char* message);
extern jint rvmThrowArithmeticException(Env* env);
extern jint rvmThrowLinkageError(Env* env);
extern jint rvmThrowIllegalMonitorStateException(Env* env);
extern jint rvmThrowInterruptedException(Env* env);
extern void rvmRaiseException(Env* env, Object* e);
extern void rvmReraiseException(Env* env, void* exInfo);

#endif

