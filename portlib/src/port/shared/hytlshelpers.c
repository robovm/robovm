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
 * @ingroup Port
 * @brief Per Thread Buffer Support
 *
 * Per thread buffers are used to store information that is not sharable among the threads.  
 * For example when an OS system call fails the error code associated with that error is
 * relevant to the thread that called the OS function; it has no meaning to any other thread.
 *
 * This file contains the functions supported by the port library for creating, accessing and
 * destroying per thread buffers.@see hyportptb.h for details on the per thread buffer structure.
 *
 * Only the function @ref hyport_tls_free is available via the port library function table.  The rest of
 * the functions are helpers for the port library only.
 */
#include <string.h>
#include "hyport.h"
#include "portpriv.h"
#include "hythread.h"
#include "hyportptb.h"

/**
 * @internal
 * @brief Per Thread Buffer Support
 *
 * Get a per thread buffer.
 *
 * @param[in] portLibrary The port library.
 *
 * @return The per thread buffer on success, NULL on failure.
 */
void *VMCALL
hyport_tls_get (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  PortlibPTBuffers_t ptBuffers;

  ptBuffers =
    hythread_tls_get (hythread_self (), portLibrary->portGlobals->tls_key);
  if (NULL == ptBuffers)
    {
      MUTEX_ENTER (portLibrary->portGlobals->tls_mutex);

      ptBuffers =
        portLibrary->mem_allocate_memory (portLibrary,
                                          sizeof (PortlibPTBuffers_struct));
      if (NULL != ptBuffers)
        {
          if (0 ==
              hythread_tls_set (hythread_self (),
                                portLibrary->portGlobals->tls_key, ptBuffers))
            {
              memset (ptBuffers, 0, sizeof (PortlibPTBuffers_struct));
              ptBuffers->next = portLibrary->portGlobals->buffer_list;
              if (portLibrary->portGlobals->buffer_list)
                {
                  ((PortlibPTBuffers_t) portLibrary->portGlobals->
                   buffer_list)->previous = ptBuffers;
                }
              portLibrary->portGlobals->buffer_list = ptBuffers;
            }
          else
            {
              portLibrary->mem_free_memory (portLibrary, ptBuffers);
              ptBuffers = NULL;
            }
        }

      MUTEX_EXIT (portLibrary->portGlobals->tls_mutex);
    }
  return ptBuffers;
}

/**
 * @brief Per Thread Buffer Support
 * 
 * Free the per thread buffers.
 *
 * @param[in] portLibrary The port library.
 */
void VMCALL
hyport_tls_free (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  PortlibPTBuffers_t ptBuffers;

  MUTEX_ENTER (portLibrary->portGlobals->tls_mutex);
  ptBuffers =
    hythread_tls_get (hythread_self (), portLibrary->portGlobals->tls_key);
  if (ptBuffers)
    {
      hythread_tls_set (hythread_self (), portLibrary->portGlobals->tls_key,
                        NULL);

      /* Unlink */
      if (ptBuffers->next)
        {
          ptBuffers->next->previous = ptBuffers->previous;
        }
      if (portLibrary->portGlobals->buffer_list == ptBuffers)
        {
          portLibrary->portGlobals->buffer_list = ptBuffers->next;
        }
      else
        {
          if (ptBuffers->previous)
            {
              ptBuffers->previous->next = ptBuffers->next;
            }
        }

      hyport_free_ptBuffer (portLibrary, ptBuffers);
    }
  MUTEX_EXIT (portLibrary->portGlobals->tls_mutex);
}

/**
 * @internal
 * @brief PortLibrary startup.
 * 
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the portl library thread local storage operations may be created here.  All resources created here should be destroyed
 * in @ref hyport_tls_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_TLS
 * \arg HYPORT_ERROR_STARTUP_TLS_ALLOC
 * \arg HYPORT_ERROR_STARTUP_TLS_MUTEX
 */
I_32 VMCALL
hyport_tls_startup (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  if (hythread_tls_alloc (&portLibrary->portGlobals->tls_key))
    {
      return HYPORT_ERROR_STARTUP_TLS_ALLOC;
    }

  if (!MUTEX_INIT (portLibrary->portGlobals->tls_mutex))
    {
      return HYPORT_ERROR_STARTUP_TLS_MUTEX;
    }

  return 0;
}

/**
 * @internal
 * @brief PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by 
 * @ref hyport_tls_startup should be destroyed here.
 *
 * @param[in] HyPortLibrary The port library
 */
void VMCALL
hyport_tls_shutdown (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  PortlibPTBuffers_t ptBuffers, next;

  /* Free all remaining buffer sets */
  MUTEX_ENTER (portLibrary->portGlobals->tls_mutex);
  ptBuffers = portLibrary->portGlobals->buffer_list;
  while (NULL != ptBuffers)
    {
      next = ptBuffers->next;
      hyport_free_ptBuffer (portLibrary, ptBuffers);
      ptBuffers = next;
    }
  portLibrary->portGlobals->buffer_list = NULL;
  MUTEX_EXIT (portLibrary->portGlobals->tls_mutex);

  /* Now dispose of the tls_key and the mutex */
  hythread_tls_free (portLibrary->portGlobals->tls_key);
  MUTEX_DESTROY (portLibrary->portGlobals->tls_mutex);
}

/**
 * @internal
 * @brief Per Thread Buffer Support
 * 
 * Get the per thread buffer for a thread. If the buffer has not been allocated do not allocate a new
 * one, the function @ref hyport_tls_get is used for that purpose. This function is not exported outside 
 * the port library as most applications will want a per thread buffer created to store their data.  This 
 * function is used to access existing data in the per thread buffers.
 *
 * @param[in] portLibrary The port library.
 *
 * @return The per thread buffer, may be NULL.
 */
void *VMCALL
hyport_tls_peek (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  return hythread_tls_get (hythread_self (),
                           portLibrary->portGlobals->tls_key);
}
