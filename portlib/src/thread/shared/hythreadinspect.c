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
 * @ingroup Thread
 */

/*
 * This file contains thread routines which are compiled twice -- once for in-process, and
 * once for out-of-process uses (e.g. debug extensions).
 * The APIs in this file are only used for inspecting threads -- not for modifying them
 */

#if defined(HYVM_OUT_OF_PROCESS)
#include "hydbgext.h"
#endif

#include "threaddef.h"

#if defined(HYVM_OUT_OF_PROCESS)
#define READU(field) dbgReadUDATA((UDATA*)&(field))
#define READP(field) ((void*)dbgReadUDATA((UDATA*)&(field)))
#undef MUTEX_ENTER
#define MUTEX_ENTER(a)
#undef MUTEX_EXIT
#define MUTEX_EXIT(a)
#undef GLOBAL_LOCK
#define GLOBAL_LOCK(a, b)
#undef GLOBAL_UNLOCK
#define GLOBAL_UNLOCK(a)
#else /* defined (HYVM_OUT_OF_PROCESS) */
#define READU(field) ((UDATA)(field))
#define READP(field) (field)
#endif

#define CDEV_CURRENT_FUNCTION _prototypes_private
static hythread_monitor_pool_t pool_for_monitor
PROTOTYPE ((hythread_library_t lib, hythread_monitor_t monitor));
static hythread_library_t get_default_library PROTOTYPE ((void));

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_walk
/** 
 * Walk all active monitors.
 *
 * @param[in] monitor If NULL, the first monitor is returned and the monitor pool is locked (thread lib is globally locked)<br> 
 * If non-NULL, the next monitor is returned.
 * @return a pointer to a monitor, or NULL if all monitors walked (and thread lib is globally unlocked).
 * 
 * @note As this is currently implemented, this must be called to walk ALL monitors. It can't
 * be used to look for a specific monitor and then quit.
 * 
 */
hythread_monitor_t VMCALL
hythread_monitor_walk (hythread_monitor_t monitor)
{
  hythread_monitor_pool_t pool;
  hythread_library_t lib = get_default_library ();

  ASSERT (lib);
  ASSERT (lib->monitor_pool);
  ASSERT (lib->monitor_pool->entries);
  ASSERT (MACRO_SELF () != 0);

  if (monitor == NULL)
    {
      GLOBAL_LOCK (MACRO_SELF (), CALLER_MONITOR_WALK);
      pool = READP (lib->monitor_pool);
      monitor = &pool->entries[0];
      if (READU (monitor->count) != FREE_TAG)
        return monitor;
    }
  else
    {
      pool = pool_for_monitor (lib, monitor);
      if (pool == NULL)
        {
          /* should never happen */
          GLOBAL_UNLOCK (MACRO_SELF ());
          return NULL;
        }
    }

  do
    {
      if (monitor >= &pool->entries[MONITOR_POOL_SIZE - 1])
        {
          if ((pool = READP (pool->next)) == NULL)
            {
              /* we've walked all monitors */
              GLOBAL_UNLOCK (MACRO_SELF ());
              return NULL;
            }
          monitor = &pool->entries[0];
        }
      else
        {
          monitor++;
        }
    }
  while (READU (monitor->count) == FREE_TAG);

  return monitor;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_tls_get
/** 
 * Get a thread's thread local storage (TLS) value.
 *
 * @param[in] thread a thread
 * @param[in] key key to have TLS value returned (value returned by hythread_tls_alloc)
 * @return pointer to location of TLS or NULL on failure.
 *  
 */
void *VMCALL
hythread_tls_get (hythread_t thread, hythread_tls_key_t key)
{
  return (void *) READU (thread->tls[key - 1]);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION pool_for_monitor
/* 
 * Return the monitor pool holding a monitor.
 * 
 * @param[in] lib threading library (non-NULL)
 * @param[in] monitor
 * @return pointer to pool on success, NULL on failure (invalid monitor?)
 */
static hythread_monitor_pool_t
pool_for_monitor (hythread_library_t lib, hythread_monitor_t monitor)
{
  hythread_monitor_pool_t pool = READP (lib->monitor_pool);

  /* find out which pool the monitor is from (cache this, maybe?) 
     (NOTE: technically, this search invokes undefined behaviour (comparing pointers from different
     malloc's).  But it should work on every platform with a flat memory model. */

  ASSERT (lib);
  ASSERT (monitor);
  ASSERT (pool);

  while (monitor < &pool->entries[0]
         || monitor > &pool->entries[MONITOR_POOL_SIZE - 1])
    {
      if ((pool = READP (pool->next)) == NULL)
        {
          break;
        }
    }

  return pool;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_priority
/** 
 * Return a thread's scheduling priority.
 * 
 * @param[in] thread (non-NULL)
 * @return scheduling priority
 * @see hythread_create, hythread_set_priority
 *
 */
UDATA VMCALL
hythread_get_priority (hythread_t thread)
{
  ASSERT (thread);
  return READU (thread->priority);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_flags
/** 
 * Return a thread's flags.
 * 
 * @param[in] thread (non-NULL)
 * @param[in] blocker if non-NULL, will be set to the monitor on which the thread is blocked (if any)
 * @return flags
 * 
 */
UDATA VMCALL
hythread_get_flags (hythread_t thread, hythread_monitor_t * blocker)
{
  UDATA flags;

  ASSERT (thread);

  MUTEX_ENTER (thread->mutex);

  if (blocker)
    {
      *blocker = READP (thread->monitor);
    }
  flags = READU (thread->flags);

  MUTEX_EXIT (thread->mutex);

  return flags;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_get_name
/**
 * Return a monitor's name.
 * 
 * @param[in] monitor (non-NULL)
 * @return pointer to the monitor's name (may be NULL)
 * 
 * @see hythread_monitor_init_with_name
 * 
 */
const char *VMCALL
hythread_monitor_get_name (hythread_monitor_t monitor)
{
  ASSERT (monitor);
  return READP (monitor->name);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_monitor_get_tracing

/*
 * Return a monitor's tracing information.
 * 
 * @param[in] monitor (non-NULL)
 * @return pointer to the monitor's tracing information (may be NULL)
 * 
 */
HyThreadMonitorTracing *VMCALL
hythread_monitor_get_tracing (hythread_monitor_t monitor)
{
  ASSERT (monitor);

  return READP (monitor->tracing);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getDefaultLibrary

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION get_default_library
/* 
 * Return the default threading library.
 * 
 * @return pointer to the default threading library
 * 
 */
static hythread_library_t
get_default_library (void)
{
#if defined(HYVM_OUT_OF_PROCESS)
  return dbgGetThreadLibrary ();
#else
  return GLOBAL_DATA (default_library);
#endif

}

#undef CDEV_CURRENT_FUNCTION
