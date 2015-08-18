/*
 * Copyright (C) 2015 RoboVM AB
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
#ifndef ROBOVM_RT_H
#define ROBOVM_RT_H

/*
 * The core VM native code should not make any assupmtions on the existence of
 * non-standard classes, methods or fields in the runtime library. This file
 * defines an interface which is used by the core VM native code whenever it
 * would otherwise have needed to rely on non-standard properties of the
 * runtime library. It is the responsibility of the runtime library native
 * code to provide an implementation of this interface.
 *
 * All functions/global variables must be prefixed with 'rvmRT'.
 */

/**
 * Returns the name of the runtime library.
 */
extern const char* rvmRTGetName(void);

/**
 * Initializes the runtime library. Returns TRUE on success.
 */
extern jboolean rvmRTInit(Env* env);

/**
 * Creates a new java.lang.String instance from the characters at the
 * specified offset and length in the specified char array. The char array
 * will not be copied unless necessary.
 */
extern Object* rvmRTNewString(Env* env, CharArray* value, jint offset, jint length);

/**
 * Returns the length of the specified java.lang.String instance
 */
extern jint rvmRTGetStringLength(Env* env, Object* str);

/**
 * Returns a pointer to the first jchar used by the specified java.lang.String
 * instance.
 */
extern jchar* rvmRTGetStringChars(Env* env, Object* str);

/**
 * Initializes the java.lang.Thread object which will be associated with the
 * specified native Thread being attached to the VM. threadObj has been
 * allocated but no constructor has been called yet.
 */
extern void rvmRTInitAttachedThread(Env* env, Object* threadObj, Thread* thread, Object* threadName, Object* group, jboolean daemon);

/**
 * Sets the context ClassLoader of the specified java.lang.Thread instance.
 */
extern void rvmRTSetThreadContextClassLoader(Env* env, Object* threadObj, Object* classLoader);

/**
 * Returns the native Thread pointer associated with the specified
 * java.lang.Thread instance.
 */
extern Thread* rvmRTGetNativeThread(Env* env, Object* threadObj);

/**
 * Sets the native Thread pointer associated with the specified
 * java.lang.Thread instance.
 */
extern void rvmRTSetNativeThread(Env* env, Object* threadObj, Thread* thread);

/**
 * Sets the native Thread pointer associated with the specified
 * java.lang.Thread instance to NULL. Must be done atomically!
 */
extern void rvmRTClearNativeThread(Env* env, Object* threadObj);

/**
 * Returns the java.lang.ThreadGroup the specified java.lang.Thread instance
 * belongs to or NULL if the thread isn't in any group.
 */
extern Object* rvmRTGetThreadGroup(Env* env, Object* threadObj);

/**
 * Returns whether the specified java.lang.Thread is a daemon thread.
 */
extern jboolean rvmRTIsDaemonThread(Env* env, Object* threadObj);

/**
 * Returns the priority of the specified java.lang.Thread instance.
 */
extern jint rvmRTGetThreadPriority(Env* env, Object* threadObj);

/**
 * Returns the stack size of the specified java.lang.Thread instance.
 */
extern jint rvmRTGetThreadStackSize(Env* env, Object* threadObj);

/**
 * Returns the id of the specified java.lang.Thread instance.
 */
extern jlong rvmRTGetThreadId(Env* env, Object* threadObj);

/**
 * Returns the id of the specified java.lang.Thread instance.
 */
extern jlong rvmRTGetThreadLock(Env* env, Object* threadObj);

/**
 * Resumes threads joining on the specified java.lang.Thread instance.
 */
extern void rvmRTResumeJoiningThreads(Env* env, Object* threadObj);

/**
 * Returns the parent ClassLoader of the specified ClassLoader or NULL if it
 * has no parent.
 */
extern Object* rvmRTGetParentClassLoader(Env* env, Object* classLoader);


#endif
