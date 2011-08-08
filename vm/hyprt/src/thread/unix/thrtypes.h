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

#if !defined(THRTYPES_H)
#define THRTYPES_H
#if defined(__cplusplus)
extern "C"
{
#endif
#include "hythread.h"
  typedef struct HyThread
  {
    struct HyThreadLibrary *library;
    UDATA attachcount;
    UDATA priority;
    struct HyThreadMonitor *monitor;
    struct HyThread *next;
    void *tls[128];
    hythread_entrypoint_t entrypoint;
    void *entryarg;
    UDATA flags;
    UDATA tid;
    struct HyThread *interrupter;
    OSTHREAD handle;
    COND condition;
    MUTEX mutex;
    UDATA stacksize;
    UDATA *tos;
    void *jumpBuffer;
  } HyThread;
  typedef struct HyThreadMonitor
  {
    UDATA count;
    struct HyThread *owner;
    struct HyThread *waiting;
    UDATA flags;
    UDATA userData;
    struct HyThreadMonitorTracing *tracing;
    const char *name;
    UDATA pinCount;
    UDATA antiDeflationCount;
    UDATA proDeflationCount;
    UDATA spinlockState;
    UDATA lockingWord;
    UDATA spinCount1;
    UDATA spinCount2;
    UDATA spinCount3;
    struct HyThread *blocking;
    MUTEX mutex;
  } HyThreadMonitor;
  typedef struct HyThreadMonitorPool
  {
    struct HyThreadMonitorPool *next;
    struct HyThreadMonitor *next_free;
    struct HyThreadMonitor entries[64];
  } HyThreadMonitorPool;
#define MONITOR_POOL_SIZE  64
  typedef struct HyThreadGlobal
  {
    struct HyThreadGlobal *next;
    const char *name;
    UDATA data;
  } HyThreadGlobal;
  typedef struct HyThreadLibrary
  {
    UDATA spinlock;
    struct HyThreadMonitorPool *monitor_pool;
    struct HyPool *thread_pool;
    UDATA threadCount;
    UDATA stack_usage;
    IDATA initStatus;
    UDATA flags;
    struct HyThreadMonitorTracing *gc_lock_tracing;
    struct HyThreadGlobal *globals;
    struct HyPool *global_pool;
    MUTEX global_mutex;
    TLSKEY self_ptr;
    MUTEX monitor_mutex;
    MUTEX tls_mutex;
    hythread_tls_finalizer_t tls_finalizers[128];
    char *thread_weight;
    struct HyPool *monitor_tracing_pool;
    struct HyPool *thread_tracing_pool;
  } HyThreadLibrary;
#define HYTHREAD_LIB_FLAG_JLMHST_ENABLED  0x10000
#define HYTHREAD_LIB_FLAG_JLM_ENABLED  0x4000
#define HYTHREAD_LIB_FLAG_JLM_ENABLED_ALL  0x1C000
#define HYTHREAD_LIB_FLAG_JLM_HAS_BEEN_ENABLED  0x20000
#define HYTHREAD_LIB_FLAG_JLMTS_ENABLED  0x8000
  typedef struct HySemaphore
  {
    OSSEMAPHORE sem;
  } HySemaphore;
#define STACK_DEFAULT_SIZE  0x8000
#define FREE_TAG ((UDATA)-1)
  typedef struct HyThreadMonitorPool *hythread_monitor_pool_t;
  typedef struct HyThreadLibrary *hythread_library_t;
#if defined(__cplusplus)
}
#endif
#endif                          /* THRTYPES_H */
