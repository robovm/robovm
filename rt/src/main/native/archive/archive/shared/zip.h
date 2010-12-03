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

#if !defined(zip_h)
#define zip_h

#ifndef HY_ZIP_API
#include "zipsup.h"
#else /* HY_ZIP_API */
#include "vmizip.h"
#endif /* HY_ZIP_API */

#include "hymutex.h"

typedef struct JCLZipFile
{
  struct JCLZipFile *last;
  struct JCLZipFile *next;
#ifndef HY_ZIP_API
  HyZipFile hyZipFile;
#else
  VMIZipFile hyZipFile;
#endif
} JCLZipFile;

/* Fake JCLZipFile entry. last, next must be in the same position as JCLZipFile */
typedef struct JCLZipFileLink
{
  JCLZipFile *last;
  JCLZipFile *next;
  MUTEX mutex;
} JCLZipFileLink;

#define THROW_ZIP_EXCEPTION(env, err, type)            \
  if (err == Z_MEM_ERROR) {                            \
    throwNewOutOfMemoryError(env, "");                 \
  } else {                                             \
    throwNew##type(env, (const char*) zError(err));    \
  }

void throwNewIllegalStateException PROTOTYPE((JNIEnv* env,
                                              const char* message));
void throwNewIllegalArgumentException PROTOTYPE((JNIEnv* env,
                                                 const char* message));

#endif /* zip_h */
