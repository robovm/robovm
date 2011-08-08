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

/**
 * @file
 * @ingroup VMLS
 * @brief VM Local Storage Header
 */

#if !defined(HY_VMLS_H)
#define HY_VMLS_H

#if defined(__cplusplus)
extern "C"
{
#endif

#include "hycomp.h"
#include "jni.h"

#define HY_VMLS_MAX_KEYS 256

/**
 * @struct HyVMLSFunctionTable
 * The VM local storage function table.
 */
typedef struct HyVMLSFunctionTable
  {
    UDATA (JNICALL * HYVMLSAllocKeys) (JNIEnv * env, UDATA * pInitCount, ...);
    void (JNICALL * HYVMLSFreeKeys) (JNIEnv * env, UDATA * pInitCount, ...);
    void *(JNICALL * HyVMLSGet) (JNIEnv * env, void *key);
    void *(JNICALL * HyVMLSSet) (JNIEnv * env, void **pKey, void *value);
  } HyVMLSFunctionTable;


#if defined(USING_VMI)
#define HY_VMLS_FNTBL(env) (*VMI_GetVMIFromJNIEnv(env))->GetVMLSFunctions(VMI_GetVMIFromJNIEnv(env))
#else
#define HY_VMLS_FNTBL(env) ((HyVMLSFunctionTable *) ((((void ***) (env))[offsetof(HyVMThread,javaVM)/sizeof(UDATA)])[offsetof(HyJavaVM,vmLocalStorageFunctions)/sizeof(UDATA)]))
#endif

#define HY_VMLS_GET(env, key) (HY_VMLS_FNTBL(env)->HyVMLSGet(env, (key)))
#define HY_VMLS_SET(env, key, value) (HY_VMLS_FNTBL(env)->HyVMLSSet(env, &(key), (void *) (value)))

#if defined(__cplusplus)
}
#endif

#endif /* HY_VMLS_H */
