/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "iohelp.h"
#include "exceptions.h"
#include "jclglob.h"
#include "zip.h"

void zfree PROTOTYPE ((void *opaque, void *address));
void *zalloc PROTOTYPE ((void *opaque, U_32 items, U_32 size));


#ifdef HY_ZIP_API
/*
	ZLib interface to hymem_allocate_memory.
*/
void *
zalloc (void *opaque, U_32 items, U_32 size)
{
  PORT_ACCESS_FROM_PORT (((HyPortLibrary *) opaque));

  return hymem_allocate_memory (items * size);
}


/*
	ZLib interface to hymem_free_memory.
*/
void
zfree (void *opaque, void *address)
{
  PORT_ACCESS_FROM_PORT ((HyPortLibrary *) opaque);

  hymem_free_memory (address);
}

#endif /* HY_ZIP_API */

/**
  * Throw java.lang.InternalError
  */
void
throwNewInternalError (JNIEnv * env, const char *message)
{
  throwNewExceptionByName(env, "java/lang/InternalError", message);
}

/**
  * Throw java.util.zip.ZipException with the message provided
  */
void
throwJavaZIOException (JNIEnv * env, const char *message)
{
  throwNewExceptionByName(env, "java/util/zip/ZipException", message);
}

/**
  * Throw java.lang.IllegalStateException
  */
void
throwNewIllegalStateException (JNIEnv * env, const char *message)
{
  throwNewExceptionByName(env, "java/lang/IllegalStateException", message);
}

/**
  * Throw java.lang.IllegalArgumentException
  */
void
throwNewIllegalArgumentException (JNIEnv * env, const char *message)
{
  throwNewExceptionByName(env, "java/lang/IllegalArgumentException", message);
}


