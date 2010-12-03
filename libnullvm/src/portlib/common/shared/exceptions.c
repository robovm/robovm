/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

#include "exceptions.h"
#include "jclglob.h"

void throwNewExceptionByName(JNIEnv* env,
                             const char* name, const char* message)
{
  jclass exceptionClass = (*env)->FindClass(env, name);
  if (exceptionClass == NULL) { 
    /* Just return if we can't load the exception class. */
    return;
  }
  (*env)->ThrowNew(env, exceptionClass, message);
}  

/**
  * Throw java.io.IOException with the message provided
  */
void throwJavaIoIOException(JNIEnv* env, const char* message)
{
  throwNewExceptionByName(env, "java/io/IOException", message);
}

/**
  * Throw java.io.IOException with the "File closed" message
  * Consolidate all through here so message is consistent.
  */
void throwJavaIoIOExceptionClosed(JNIEnv* env)
{
  throwJavaIoIOException(env, "File closed");
}

/**
  * Throw java.lang.IndexOutOfBoundsException
  */
void throwIndexOutOfBoundsException(JNIEnv* env)
{
  throwNewExceptionByName(env, "java/lang/IndexOutOfBoundsException", "");
}

/**
  * Throw java.lang.NullPointerException with the message provided
  * Note: This is not named throwNullPointerException because it conflicts
  * with a VM function of that same name and this causes problems on
  * some platforms.
  */
void throwNPException(JNIEnv* env, const char* message)
{
  throwNewExceptionByName(env, "java/lang/NullPointerException", message);
}

/**
  * Throw java.lang.OutOfMemoryError
  */
void throwNewOutOfMemoryError(JNIEnv* env, const char* message)
{
  throwNewExceptionByName(env, "java/lang/OutOfMemoryError", message);
}
