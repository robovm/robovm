/*
 * Copyright (C) 2012 Trillian AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef ROBOVM_EXCEPTION_H
#define ROBOVM_EXCEPTION_H

extern jboolean rvmInitExceptions(Env* env);
extern Object* rvmExceptionOccurred(Env* env);
extern jboolean rvmExceptionCheck(Env* env);
extern Object* rvmExceptionClear(Env* env);
extern void rvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f);
extern void rvmPrintStackTrace(Env* env, Object* throwable);
extern void rvmThrow(Env* env, Object* e);
extern jboolean rvmThrowNew(Env* env, Class* clazz, const char* message);
extern jboolean rvmThrowNewf(Env* env, Class* clazz, const char* format, ...);
extern jboolean rvmThrowNewfv(Env* env, Class* clazz, const char* format, va_list ap);
extern jboolean rvmThrowInternalErrorErrno(Env* env, int errnum);
extern jboolean rvmThrowOutOfMemoryError(Env* env);
extern jboolean rvmThrowNoClassDefFoundError(Env* env, const char* message);
extern jboolean rvmThrowIllegalAccessError(Env* env, const char* message);
extern jboolean rvmThrowNoSuchFieldError(Env* env, const char* message);
extern jboolean rvmThrowNoSuchMethodError(Env* env, const char* message);
extern jboolean rvmThrowIncompatibleClassChangeError(Env* env,const char* message);
extern jboolean rvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, const char* name, const char* desc);
extern jboolean rvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, const char* name, const char* desc);
extern jboolean rvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, const char* name, const char* desc);
extern jboolean rvmThrowInstantiationError(Env* env, const char* message);
extern jboolean rvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass);
extern jboolean rvmThrowNullPointerException(Env* env);
extern jboolean rvmThrowAbstractMethodError(Env* env, const char* message);
extern jboolean rvmThrowArrayIndexOutOfBoundsException(Env* env, jint length, jint index);
extern jboolean rvmThrowArrayStoreException(Env* env);
extern jboolean rvmThrowNegativeArraySizeException(Env* env);
extern jboolean rvmThrowClassNotFoundException(Env* env, const char* className);
extern jboolean rvmThrowUnsatisfiedLinkError(Env* env, const char* message);
extern jboolean rvmThrowIllegalArgumentException(Env* env, const char* message);
extern jboolean rvmThrowArithmeticException(Env* env);
extern jboolean rvmThrowLinkageError(Env* env);
extern jboolean rvmThrowIllegalMonitorStateException(Env* env, const char* message);
extern jboolean rvmThrowInterruptedException(Env* env);
extern jboolean rvmThrowIllegalStateException(Env* env, const char* message);
extern void rvmRaiseException(Env* env, Object* e);

#endif

