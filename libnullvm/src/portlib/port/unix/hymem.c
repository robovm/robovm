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

#define CDEV_CURRENT_FUNCTION _comment_
/**
 * @file
 * @ingroup Port
 * @brief Memory Utilities
 */

#undef CDEV_CURRENT_FUNCTION

/* 
 * This file contains code for the portability library memory management.
 */
#include <stdlib.h>
#include <string.h>
#include "hyport.h"
#include "portpriv.h"
#include "hyportpg.h"
#include "ut_hyprt.h"

#if defined(DEBUG_MALLOC_FREE_LEAK)
#include <stdio.h>
static UDATA DEBUG_TOTAL_ALLOCATED_MEMORY = 0;
#endif


#define CDEV_CURRENT_FUNCTION hymem_allocate_memory
/**
 * Allocate memory.
 *
 * @param[in] portLibrary The port library
 * @param[in] byteAmount Number of bytes to allocate.
 *
 * @return pointer to memory on success, NULL on error.
 * @return Memory is not guaranteed to be zeroed as part of this call
 *
 * @internal @warning Do not call error handling code @ref hyerror upon error as 
 * the error handling code uses per thread buffers to store the last error.  If memory
 * can not be allocated the result would be an infinite loop.
 */
void *VMCALL
hymem_allocate_memory (struct HyPortLibrary *portLibrary, UDATA byteAmount)
{
  void *pointer = NULL;
#if defined(DEBUG_MALLOC_FREE_LEAK)
  void *mem;
#endif

  Trc_PRT_mem_hymem_allocate_memory_Entry (byteAmount);
#if defined(DEBUG_MALLOC_FREE_LEAK)
  if (byteAmount == 0)
    {                           /* prevent malloc from failing causing allocate to return null */
      byteAmount = 1;
    }
  DEBUG_TOTAL_ALLOCATED_MEMORY += byteAmount;
  portLibrary->tty_printf (portLibrary,
                           "\nallocate of %u bytes (new total is %u bytes)\n",
                           byteAmount, DEBUG_TOTAL_ALLOCATED_MEMORY);
  mem = (void *) malloc (byteAmount + sizeof (UDATA));
#if defined(HYS390)
  mem = (void *) (((UDATA) mem) & 0x7FFFFFFF);
#endif /* HYS390 */
  *((UDATA *) mem) = byteAmount;
  pointer = ((UDATA) mem + sizeof (UDATA));
#else
  if (byteAmount == 0)
    {                           /* prevent malloc from failing causing allocate to return null */
      byteAmount = 1;
    }

  pointer = malloc (byteAmount);
#if defined(HYS390)
  pointer = (void *) (((UDATA) pointer) & 0x7FFFFFFF);
#endif /* HYS390 */
#endif


  Trc_PRT_mem_hymem_allocate_memory_Exit (pointer);
  return pointer;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_free_memory
/**
 * Deallocate memory.
 *
 * @param[in] portLibrary The port library
 * @param[in] memoryPointer Base address of memory to be deallocated.
 */
void VMCALL
hymem_free_memory (struct HyPortLibrary *portLibrary, void *memoryPointer)
{
  Trc_PRT_mem_hymem_free_memory_Entry (memoryPointer);
#if defined(DEBUG_MALLOC_FREE_LEAK)
  DEBUG_TOTAL_ALLOCATED_MEMORY -=
    *((UDATA *) ((UDATA) memoryPointer - sizeof (UDATA)));
  portLibrary->tty_printf (portLibrary,
                           "\nfree of %u bytes (new total is %u bytes)\n",
                           *((UDATA *) ((UDATA) memoryPointer -
                                        sizeof (UDATA))),
                           DEBUG_TOTAL_ALLOCATED_MEMORY);
  free ((void *) ((UDATA) memoryPointer - sizeof (UDATA)));
#else
  free (memoryPointer);
#endif


  Trc_PRT_mem_hymem_free_memory_Exit ();
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_reallocate_memory
/**
 * Re-allocate memory.
 *
 * @param[in] portLibrary The port library
 * @param[in] memoryPointer Base address of memory to be re-allocated.
 * @param[in] byteAmount Number of bytes to re-allocated.
 *
 * @return pointer to memory on success, NULL on error.
 *
 * @internal @warning Do not call error handling code @ref hyerror upon error as 
 * the error handling code uses per thread buffers to store the last error.  If memory
 * can not be allocated the result would be an infinite loop.
 */
void *VMCALL
hymem_reallocate_memory (struct HyPortLibrary *portLibrary,
                         void *memoryPointer, UDATA byteAmount)
{
  void *ptr = NULL;

  Trc_PRT_mem_hymem_reallocate_memory_Entry (memoryPointer, byteAmount);

  ptr = realloc (memoryPointer, byteAmount);
#if defined(HYS390)
  ptr = (void *) (((UDATA) ptr) & 0x7FFFFFFF);
#endif

  Trc_PRT_mem_hymem_reallocate_memory_Exit (ptr);
  return ptr;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hymem_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Must deallocate portGlobals.
 * @note Most implementations will just deallocate portGlobals.
 */
void VMCALL
hymem_shutdown (struct HyPortLibrary *portLibrary)
{
  portLibrary->mem_free_memory (portLibrary, portLibrary->portGlobals);
  portLibrary->portGlobals = NULL;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the memory operations may be created here.  All resources created here should be destroyed
 * in @ref hymem_shutdown.
 *
 * @param[in] portLibrary The port library
 * @param[in] portGlobalSize Size of the global data structure to allocate
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_MEM
 *.
 * @note Must allocate portGlobals.
 * @note Most implementations will just allocate portGlobals.
 *
 * @internal @note portLibrary->portGlobals must point to an aligned structure
 */
I_32 VMCALL
hymem_startup (struct HyPortLibrary *portLibrary, UDATA portGlobalSize)
{
  portLibrary->portGlobals =
    portLibrary->mem_allocate_memory (portLibrary, portGlobalSize);
  if (!portLibrary->portGlobals)
    {
      return HYPORT_ERROR_STARTUP_MEM;
    }
  memset (portLibrary->portGlobals, 0, portGlobalSize);

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_allocate_memory_callSite
/**
 * Allocate memory.
 *
 * @param[in] portLibrary The port library
 * @param[in] byteAmount Number of bytes to allocate.
 * @param[in] callSite String describing callsite, usually file and line number.
 *
 * @return pointer to memory on success, NULL on error.
 *
 * @internal @warning Do not call error handling code @ref hyerror upon error as 
 * the error handling code uses per thread buffers to store the last error.  If memory
 * can not be allocated the result would be an infinite loop.
 */
void *VMCALL
hymem_allocate_memory_callSite (struct HyPortLibrary *portLibrary,
                                UDATA byteAmount, const char *callSite)
{
  void *ptr = NULL;

  Trc_PRT_mem_hymem_allocate_memory_callSite_Entry (byteAmount, callSite);
  ptr = portLibrary->mem_allocate_memory (portLibrary, byteAmount);
  Trc_PRT_mem_hymem_allocate_memory_callSite_Exit (ptr);
  return ptr;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_allocate_portLibrary
/**
 * @internal Allocate memory for a portLibrary.
 *
 * @param[in] byteAmount Number of bytes to allocate.
 *
 * @return pointer to memory on success, NULL on error.
 * @note This function is called prior to the portLibrary being initialized
 * @note Must be implemented for all platforms.
 */
void *VMCALL
hymem_allocate_portLibrary (UDATA byteAmount)
{
  return (void *) malloc (byteAmount);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hymem_deallocate_portLibrary
/**
 * @internal Free memory for a portLibrary.
 *
 * @param[in] memoryPointer Base address to be deallocated.
 *
 * @note Must be implemented for all platforms.
 */
void VMCALL
hymem_deallocate_portLibrary (void *memoryPointer)
{
  free (memoryPointer);
}

#undef CDEV_CURRENT_FUNCTION
