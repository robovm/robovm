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

/* HarmonyDoxygen */
/**
 * @file
 * @ingroup HarmonyNatives
 * @brief Harmony Archive natives initialization API.
 */

#include "vmi.h"
#include "jclglob.h"
#include "zip.h"
#include <string.h>

static UDATA keyInitCount = 0;

void *JCL_ID_CACHE = NULL;

static void freeReferences (JNIEnv * env);

/**
 * This DLL is being loaded, do any initialization required.
 * This may be called more than once.
 */
JNIEXPORT jint JNICALL
JNI_OnLoad_Archive (JavaVM * vm, void *reserved)
{
  JniIDCache *idCache;
  JNIEnv *env;
  void *keyInitCountPtr = GLOBAL_DATA (keyInitCount);
  void **jclIdCache = GLOBAL_DATA (JCL_ID_CACHE);

  if ((*vm)->GetEnv (vm, (void **) &env, JNI_VERSION_1_2) == JNI_OK)
    {
      PORT_ACCESS_FROM_ENV (env);

      if (HY_VMLS_FNTBL (env)->
          HYVMLSAllocKeys (env, keyInitCountPtr, jclIdCache, NULL))
        {
          goto fail;
        }

      /* This allocate must actually be done by hymem_allocate_memory. */
      idCache = (JniIDCache *) hymem_allocate_memory (sizeof (JniIDCache));
      if (!idCache)
        goto fail2;

      memset (idCache, 0, sizeof (JniIDCache));
      HY_VMLS_SET (env, *jclIdCache, idCache);

      /* Attach to the common library */
      if (JNI_OK != ClearLibAttach (env))
        {
          goto fail2;
        }

      return JNI_VERSION_1_2;
    }

fail2:
  HY_VMLS_FNTBL (env)->HYVMLSFreeKeys (env, keyInitCountPtr, jclIdCache, NULL);
fail:
  return 0;
}

/**
 * This DLL is being unloaded, do any clean up required.
 * This may be called more than once!!
 */
JNIEXPORT void JNICALL
JNI_OnUnload_Archive (JavaVM * vm, void *reserved)
{
  JNIEnv *env;
  void *keyInitCountPtr = GLOBAL_DATA (keyInitCount);
  void **jclIdCache = GLOBAL_DATA (JCL_ID_CACHE);

  if ((*vm)->GetEnv (vm, (void **) &env, JNI_VERSION_1_2) == JNI_OK)
    {
      JniIDCache *idCache = (JniIDCache *) HY_VMLS_GET (env, *jclIdCache);

      if (idCache)
        {
          JCLZipFileLink *zipfileHandles;
          JCLZipFile *jclZipFile;

          PORT_ACCESS_FROM_ENV (env);
#ifdef HY_ZIP_API
          VMI_ACCESS_FROM_ENV(env);
          VMIZipFunctionTable *zipFuncs = (*VMI)->GetZipFunctions(VMI);
#endif /* HY_ZIP_API */

          /* Detach from the common library */
          ClearLibDetach (env);

          /* Close and free the HyZipFile handles */
          zipfileHandles = JCL_CACHE_GET (env, zipfile_handles);
          if (zipfileHandles != NULL)
            {
              jclZipFile = zipfileHandles->next;
              while (jclZipFile != NULL)
                {
                  JCLZipFile *next = jclZipFile->next;
#ifndef HY_ZIP_API
                  zip_closeZipFile (PORTLIB, &jclZipFile->hyZipFile);
#else /* HY_ZIP_API */
                  zipFuncs->zip_closeZipFile (VMI, &jclZipFile->hyZipFile);
#endif /* HY_ZIP_API */
                  jclmem_free_memory (env, jclZipFile);
                  jclZipFile = next;
                }
              MUTEX_DESTROY (zipfileHandles->mutex);
              jclmem_free_memory (env, zipfileHandles);
            }

          /* Free any global references */
          freeReferences (env);

          /* Free VMLS keys */
          idCache = (JniIDCache *) HY_VMLS_GET (env, *jclIdCache);
          HY_VMLS_FNTBL (env)->HYVMLSFreeKeys (env, keyInitCountPtr,
                                              jclIdCache, NULL);
          hymem_free_memory (idCache);
        }
    }
}

/**
 * @internal
 */
static void
freeReferences (JNIEnv * env)
{
  jclass classRef;

  /* clean up class references */
  classRef = JCL_CACHE_GET (env, CLS_java_util_zip_ZipEntry);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);
}
