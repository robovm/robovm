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

#if !defined(libglob_h)
#define libglob_h
#include "vmi.h"
#include "hyvmls.h"
#define JCL_CACHE_GET(env,x) \
	(((JniIDCache*) HY_VMLS_GET((env), JCL_ID_CACHE))->x)
#define JCL_CACHE_SET(env,x,v) \
	(((JniIDCache*) HY_VMLS_GET((env), JCL_ID_CACHE))->x = (v))
#define HARMONY_CACHE_GET(env,x) \
	(((JniIDCache*) HY_VMLS_GET((env), HARMONY_ID_CACHE))->x)
#define HARMONY_CACHE_SET(env,x,v) \
	(((JniIDCache*) HY_VMLS_GET((env), HARMONY_ID_CACHE))->x = (v))	
#define jclmem_allocate_memory(env, byteAmount) \
	hymem_allocate_memory(byteAmount)
#define jclmem_free_memory(env, buf) \
	hymem_free_memory(buf)
jint JNICALL ClearLibAttach (JNIEnv * env);
void JNICALL ClearLibDetach (JNIEnv * env);
#endif /* libglob_h */
