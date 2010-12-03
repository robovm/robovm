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
 * @brief Static shared library
 */
#include <string.h>
#include <stdlib.h>
#include "hyport.h"

#if defined(HYVM_STATIC_LINKAGE)
#include "hystatic.h"
extern HyPrimitiveTable HyLinkedNatives;
void *
upLookupName (char *name, HyPrimitiveTableSlot * table)
{
  void *result;
  while (!((table->name == (char *) 0) && (table->funcOrTable == (void *) 0)))
    {
      if (table->name == (char *) 0)
        {
          result =
            upLookupName (name,
                          (HyPrimitiveTableSlot *) (table->funcOrTable));
          if (result != (void *) 0 /*HyDefaultFailPrimitive */ )
            return result;
        }
      else
        {
          if (strcmp (name, table->name) == 0)
            return table->funcOrTable;
        }
      table++;
    }
  return (void *) 0 /*HyDefaultFailPrimitive */ ;
}

/**
 * Close a static library.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Static library handle to close.
 *
 * @return 0 on success, any other value on failure.
 */
UDATA VMCALL
hysl_up_close_shared_library (struct HyPortLibrary *portLibrary,
                              UDATA descriptor)
{
  return 0;
}

/** 
 * Opens a static library .
 *
 * @param[in] portLibrary The port library.
 * @param[in] name path Null-terminated string containing the static library.
 * @param[out] descriptor Pointer to memory which is filled in with static-library handle on success.
 * @param[in] decorate Boolean value indicates whether name should be decorated if it contains path information and cannot be found.
 *
 * @return 0 on success, any other value on failure.
 *
 * @note contents of descriptor are undefined on failure.
 */
UDATA VMCALL
hysl_up_open_shared_library (struct HyPortLibrary * portLibrary, char *name,
                             UDATA * descriptor, BOOLEAN decorate)
{
  void *result;
  if (result = upLookupName (name, &HyLinkedNatives[0]))
    {
      *descriptor = (UDATA) result;
      return 0;
    }
  else
    {
      return portLibrary->error_set_last_error_with_message (portLibrary,
                                                             HYPORT_SL_NOT_FOUND,
                                                             "Not statically linked");
    }
}

/**
 * Search for a function named 'name' taking argCount in the static library 'descriptor'.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Static library to search.
 * @param[in] name Function to look up.
 * @param[out] func Pointer to the function.
 * @param[in] argSignature Argument signature.
 *
 * @return 0 on success, any other value on failure.
 *
 * @note contents of func are undefined on failure.
 */
UDATA VMCALL
hysl_up_lookup_name (struct HyPortLibrary * portLibrary, UDATA descriptor,
                     char *name, UDATA * func, const char *argSignature)
{
  void *result;
  if (result = upLookupName (name, (HyPrimitiveTableSlot *) descriptor))
    {
      *func = (UDATA) result;
      return 0;
    }
  else
    {
      return 1;
    }
}
typedef struct
{
  UDATA descriptor;
  UDATA tag;
} hysplit_sl_handle;
#define HYFLAG_SL_LINKED 0
#define HYFLAG_SL_LOADED 1
/* support BOTH types of libraries */
/** 
 * Opens a static/shared library .
 *
 * @param[in] portLibrary The port library.
 * @param[in] name path Null-terminated string containing the library.
 * @param[out] descriptor Pointer to memory which is filled in with library handle on success.
 * @param[in] decorate Boolean value indicates whether name should be decorated if it contains path information and cannot be found.
 *
 * @return 0 on success, any other value on failure.
 *
 * @note contents of descriptor are undefined on failure.
 */
UDATA VMCALL
hysl_split_open_shared_library (struct HyPortLibrary *portLibrary, char *name,
                                UDATA * descriptor, BOOLEAN decorate)
{
  UDATA result;
  UDATA tag;
  tag = HYFLAG_SL_LINKED;
  result =
    hysl_up_open_shared_library (portLibrary, name, descriptor, decorate);
  if (result == HYPORT_SL_NOT_FOUND)
    {
      result =
        hysl_open_shared_library (portLibrary, name, descriptor, decorate);
      tag = HYFLAG_SL_LOADED;
    }
  if (result == 0)
    {
      hysplit_sl_handle *handle;
      handle =
        portLibrary->mem_allocate_memory (portLibrary,
                                          sizeof (hysplit_sl_handle));
      if (handle == NULL)
        {
          if (tag == HYFLAG_SL_LOADED)
            {
              hysl_close_shared_library (portLibrary, *descriptor);
            }
          return portLibrary->error_set_last_error_with_message (portLibrary,
                                                                 HYPORT_SL_INVALID,
                                                                 "Out of memory");
        }
      handle->descriptor = *descriptor;
      handle->tag = tag;
      *descriptor = (UDATA) handle;
    }
  return result;
}

/**
 * Close a static or shared library.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Library handle to close.
 *
 * @return 0 on success, any other value on failure.
 */
UDATA VMCALL
hysl_split_close_shared_library (struct HyPortLibrary * portLibrary,
                                 UDATA descriptor)
{
  hysplit_sl_handle *handle = (hysplit_sl_handle *) descriptor;
  UDATA result;
  if (handle->tag == HYFLAG_SL_LOADED)
    {
      result = hysl_close_shared_library (portLibrary, handle->descriptor);
    }
  else
    {
      result = hysl_up_close_shared_library (portLibrary, handle->descriptor);
    }
  portLibrary->mem_free_memory (portLibrary, handle);
  return result;
}

/**
 * Search for a function named 'name' taking argCount in the static/shared library 'descriptor'.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Library to search.
 * @param[in] name Function to look up.
 * @param[out] func Pointer to the function.
 * @param[in] argSignature Argument signature.
 *
 * @return 0 on success, any other value on failure.
 *
 * @note contents of func are undefined on failure.
 */
UDATA VMCALL
hysl_split_lookup_name (struct HyPortLibrary * portLibrary, UDATA descriptor,
                        char *name, UDATA * func, const char *argSignature)
{
  hysplit_sl_handle *handle = (hysplit_sl_handle *) descriptor;
  if (handle->tag == HYFLAG_SL_LOADED)
    {
      return hysl_lookup_name (portLibrary, handle->descriptor, name, func,
                               argSignature);
    }
  else
    {
      return hysl_up_lookup_name (portLibrary, handle->descriptor, name, func,
                                  argSignature);
    }
}
#endif
