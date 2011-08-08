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
 * @internal 
 * @file
 * @ingroup Port
 * @brief Error Handling
 *
 * Helper utilities for @ref hyerror.c.  This file reduces the amount of code duplication as formatting of messages
 * from the OS is the only part of error handling that can not be handled in a generic manner.
 *
 * These functions are not accessible via the port library function table.
 */

#undef CDEV_CURRENT_FUNCTION

#include <errno.h>
#include "portpriv.h"
#include "hyportptb.h"

/* Ensure we get the recommended XSI-compliant strerror_r() */
#define _XOPEN_SOURCE 600
#undef _GNU_SOURCE
#ifndef __USE_XOPEN2K
#define __USE_XOPEN2K
#endif
#undef __USE_GNU
#include <string.h>

#define CDEV_CURRENT_FUNCTION errorMessage
/**
 * @internal
 * @brief Error Handling
 *
 * Given an error code save the OS error message to the ptBuffers and return
 * a reference to the saved message.
 *
 * @param[in] portLibrary The port library
 * @param[in] errorCode The platform specific error code to look up.
 *
 * @note By the time this function is called it is known that ptBuffers are not NULL.  It is possible, however, that the
 * buffer to hold the error message has not yet been allocated..
 *
 * @note Buffer is managed by the port library, do not free
 */
const char *VMCALL
errorMessage (struct HyPortLibrary *portLibrary, I_32 errorCode)
{
  PortlibPTBuffers_t ptBuffers;

  ptBuffers = hyport_tls_peek (portLibrary);
  if (0 == ptBuffers->errorMessageBufferSize)
    {
      ptBuffers->errorMessageBuffer =
        portLibrary->mem_allocate_memory (portLibrary,
                                          HYERROR_DEFAULT_BUFFER_SIZE);
      if (NULL == ptBuffers->errorMessageBuffer)
        {
          return "";
        }
      ptBuffers->errorMessageBufferSize = HYERROR_DEFAULT_BUFFER_SIZE;
    }

  /* Copy from OS to ptBuffers */
#if !defined(ZOS)
  strerror_r(errorCode,
             ptBuffers->errorMessageBuffer, ptBuffers->errorMessageBufferSize);
#else
  /* Do not have strerror_r on z/OS so use port library function instead */
  portLibrary->str_printf(portLibrary, ptBuffers->errorMessageBuffer, ptBuffers->errorMessageBufferSize, strerror(errorCode));
#endif /* ZOS */
  ptBuffers->errorMessageBuffer[ptBuffers->errorMessageBufferSize - 1] = '\0';
  return ptBuffers->errorMessageBuffer;
}

#undef CDEV_CURRENT_FUNCTION
