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

/*
 * =============================================================================
 * DESCRIPTION: Universal Trace Engine module header file
 * =============================================================================
 */
#if !defined(_UTE_MODULE_H)
#define _UTE_MODULE_H

#define UT_ENV_ARG void *env
#define UT_CALL_TYPE

#include "jni.h"
#include "ute_pd.h"
#if defined(__cplusplus)
extern "C"
{
#endif
/*
 * =============================================================================
 *   Forward declarations
 * =============================================================================
 */
  struct utServerInterface;
  typedef struct utServerInterface UtServerInterface;
  struct utClientInterface;
  typedef struct utClientInterface UtClientInterface;
  struct utModuleInterface;
  typedef struct utModuleInterface UtModuleInterface;

/*
 * =============================================================================
 *   The combined UT server/client/module interface
 * =============================================================================
 */
  typedef struct utInterface
  {
    UtServerInterface *server;
    UtClientInterface *client;
    UtModuleInterface *module;
  } UtInterface;
/*
 * =============================================================================
 *  UT module info
 * =============================================================================
 */
  typedef struct utModuleInfo
  {
    char *name;
    UT_I32 namelength;
    UtModuleInterface *intf;
    char *properties;
    UT_I32 *stateTrace;
  } UtModuleInfo;
/*
 * =============================================================================
 *   The module interface for indirect calls into UT
 * =============================================================================
 */
  struct utModuleInterface
  {
    void (UT_CALL_TYPE * Trace) (UT_ENV_ARG, UtModuleInfo * modInfo,
                                 UT_U32 traceId, const char *spec, ...);
    void (UT_CALL_TYPE * TraceInit) (UT_ENV_ARG, UtModuleInfo * mod);
    void (UT_CALL_TYPE * TraceTerm) (UT_ENV_ARG, UtModuleInfo * mod);
  };
#if defined(__cplusplus)
}
#endif

#endif  /* !_UTE_MODULE_H */
