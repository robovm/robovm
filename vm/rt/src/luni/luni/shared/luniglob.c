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

/* HarmonyDoxygen */
/**
 * @file
 * @ingroup HarmonyNatives
 * @brief Harmony LUNI natives initialization API.
 */
#include <search.h>
#include <string.h>
#include <stdlib.h>
#include "vmi.h"
#include "harmonyglob.h"
#include "hyport.h"
#include "strhelp.h"
#include "hycomp.h"
#include "helpers.h"

static UDATA keyInitCount = 0;

void *HARMONY_ID_CACHE = NULL;

static int props_compare(const void *arg1, const void *arg2);
static jint readClassPathFromPropertiesFile (VMInterface *vmInterface);
static void freeReferences (JNIEnv * env);

/**
 * This DLL is being loaded, do any initialization required.
 * This may be called more than once.
 */
JNIEXPORT jint JNICALL
JNI_OnLoad_LUNI (JavaVM * vm, void *reserved)
{
  JniIDCache *idCache;
  JNIEnv *env;
  void *keyInitCountPtr = GLOBAL_DATA (keyInitCount);
  void **harmonyIdCache = GLOBAL_DATA (HARMONY_ID_CACHE);
  VMInterface *vmInterface;
  char *bootPath = NULL;
  char *propVal = NULL;
#define CHARSETBUFF 64
  char charset[CHARSETBUFF];
  vmiError propRes;

  /* Query the VM interface */
  vmInterface = VMI_GetVMIFromJavaVM (vm);
  if (!vmInterface)
    {
      goto fail;
    }

  if ((*vm)->GetEnv (vm, (void **) &env, JNI_VERSION_1_2) == JNI_OK)
    {
      PORT_ACCESS_FROM_ENV (env);

      if (HY_VMLS_FNTBL (env)->
          HYVMLSAllocKeys (env, keyInitCountPtr, harmonyIdCache, NULL))
        {
          goto fail;
        }

      idCache = (JniIDCache *) hymem_allocate_memory (sizeof (JniIDCache));
      if (!idCache)
        goto fail2;

      memset (idCache, 0, sizeof (JniIDCache));
      HY_VMLS_SET (env, *harmonyIdCache, idCache);

      HARMONY_CACHE_SET (env, realPortArray, NULL);
      HARMONY_CACHE_SET (env, synthPortArray, NULL);
      HARMONY_CACHE_SET (env, portListLen, 0);

      /* Attach to the common library */
      if (JNI_OK != ClearLibAttach (env))
        {
          goto fail2;
        }

       return JNI_VERSION_1_2;
    }

fail2:
  HY_VMLS_FNTBL (env)->HYVMLSFreeKeys (env, keyInitCountPtr, harmonyIdCache, NULL);
fail:
  return 0;
}

/**
 * This DLL is being unloaded, do any clean up required.
 * This may be called more than once!!
 */
JNIEXPORT void JNICALL
JNI_OnUnload_LUNI (JavaVM * vm, void *reserved)
{
  JNIEnv *env;
  void *keyInitCountPtr = GLOBAL_DATA (keyInitCount);
  void **harmonyIdCache = GLOBAL_DATA (HARMONY_ID_CACHE);

  int i, listlen;
  char **portArray, **portArray2;

  if ((*vm)->GetEnv (vm, (void **) &env, JNI_VERSION_1_2) == JNI_OK)
    {
      JniIDCache *idCache = (JniIDCache *) HY_VMLS_GET (env, *harmonyIdCache);

      if (idCache)
        {
          PORT_ACCESS_FROM_ENV (env);

          /* Detach from the common library */
          ClearLibDetach (env);

          /*free the arrays of available portnames */
          portArray = HARMONY_CACHE_GET (env, realPortArray);
          if (portArray != NULL)
            {
              portArray2 = HARMONY_CACHE_GET (env, synthPortArray);
              listlen = HARMONY_CACHE_GET (env, portListLen);
              for (i = 0; i < listlen; i++)
                {
                  if (portArray[i] != NULL)
                    hymem_free_memory (portArray[i]);
                  if (portArray2[i] != NULL)
                    hymem_free_memory (portArray2[i]);
                }
              hymem_free_memory (portArray);
              hymem_free_memory (portArray2);
              HARMONY_CACHE_SET (env, realPortArray, NULL);
              HARMONY_CACHE_SET (env, synthPortArray, NULL);
              HARMONY_CACHE_SET (env, portListLen, 0);
            }

          /* Free any global references */
          freeReferences (env);

          /* Free VMLS keys */
          idCache = (JniIDCache *) HY_VMLS_GET (env, *harmonyIdCache);
          HY_VMLS_FNTBL (env)->HYVMLSFreeKeys (env, keyInitCountPtr,
                                              harmonyIdCache, NULL);
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
  classRef = HARMONY_CACHE_GET (env, CLS_java_lang_Boolean);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_lang_Byte);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_lang_Integer);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_net_InetAddress);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_net_Socket);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_lang_Long);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_java_net_Inet6Address);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);

  classRef = HARMONY_CACHE_GET (env, CLS_array_of_byte);
  if (classRef)
    (*env)->DeleteGlobalRef (env, classRef);
}
