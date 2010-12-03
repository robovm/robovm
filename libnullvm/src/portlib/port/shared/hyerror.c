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
 * @brief Error Handling
 *
 * If an OS system call fails the error code reported by the OS needs to be saved for future reference.
 * The OS error code as well as the corresponding portable error code are stored in the per thread
 * buffers @ref hyportptb.h via a call the @ref hyerror_set_last_error or @ref hyerror_set_last_error_with_message.
 * These functions are meant for private use by the port library, to set the error related to a system call failure.
 * They are in the port library table as people overriding port library functions may need to set the error
 * message accordingly.
 *
 * The majority of applications are not interested in the human readable error message corresponding to
 * the error.  As a result the error message is not stored at time of the reported error, but can be looked
 * up at a later time.
 */

#include <stdlib.h>
#include <string.h>
#include "portpriv.h"
#include "portnls.h"
#include "hyportptb.h"

static const char *
swapMessageBuffer (PortlibPTBuffers_t ptBuffers, const char *message)
{
  char *tempBuffer = ptBuffers->reportedMessageBuffer;
  U_32 tempBufferSize = ptBuffers->reportedMessageBufferSize;

  if (message == NULL)
    {
      return "";
    }

  /* Can't swap unknown message buffer */
  if (message != ptBuffers->errorMessageBuffer)
    {
      return message;
    }

  /* Save reported information */
  ptBuffers->reportedErrorCode = ptBuffers->portableErrorCode;
  ptBuffers->reportedMessageBuffer = ptBuffers->errorMessageBuffer;
  ptBuffers->reportedMessageBufferSize = ptBuffers->errorMessageBufferSize;

  if (tempBufferSize > 0)
    {
      tempBuffer[0] = '\0';
    }

  /* Clear pending fields ready for next error */
  ptBuffers->portableErrorCode = 0;
  ptBuffers->errorMessageBuffer = tempBuffer;
  ptBuffers->errorMessageBufferSize = tempBufferSize;

  return ptBuffers->reportedMessageBuffer;
}

/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the error handling operations may be created here.  All resources created here should be destroyed
 * in @ref hyerror_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_ERROR
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyerror_startup (struct HyPortLibrary * portLibrary)
{
  return 0;
}

/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyerror_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyerror_shutdown (struct HyPortLibrary *portLibrary)
{
}

/**
 *  @brief Error Handling
 *
 * Return the error message as reported by the OS for the last saved error.
 * If no error has been saved then an empty message is returned.
 *
 * @param[in] portLibrary The port library
 *
 * @return The error message for the last OS failure, empty message on failure.
 */
const char *VMCALL
hyerror_last_error_message (struct HyPortLibrary *portLibrary)
{
  PortlibPTBuffers_t ptBuffers;

  /* Was an error saved ? */
  ptBuffers = hyport_tls_peek (portLibrary);
  if (NULL == ptBuffers)
    {
      return "";
    }

  /* New error ? */
  if (ptBuffers->portableErrorCode != 0)
    {
      const char *message = NULL;

      /* Customized message stored ? */
      if (ptBuffers->errorMessageBufferSize > 0)
        {
          if ('\0' != ptBuffers->errorMessageBuffer[0])
            {
              message = ptBuffers->errorMessageBuffer;
            }
        }

      /* Call a helper to get the last message from the OS.  */
      if (message == NULL)
        {
          message = errorMessage (portLibrary, ptBuffers->platformErrorCode);
        }

      /* Avoid overwrite by internal portlib errors */
      return swapMessageBuffer (ptBuffers, message);
    }

  /* Previous message stored ? */
  if (ptBuffers->reportedMessageBufferSize > 0)
    {
      if ('\0' != ptBuffers->reportedMessageBuffer[0])
        {
          return ptBuffers->reportedMessageBuffer;
        }
    }

  /* No error.  */
  return "";
}

/**
 * @brief Error Handling
 *
 * Look up the reason for the last stored failure.  If no error code has been
 * stored then failure is returned.
 *
 * @param[in] portLibrary The port library
 *
 * @return The negative portable error code on success, 0 on failure.
 *
 * @note Does not clear the reason for the last failure.
 */
I_32 VMCALL
hyerror_last_error_number (struct HyPortLibrary * portLibrary)
{
  PortlibPTBuffers_t ptBuffers;

  /* get the buffers, return failure if not present */
  ptBuffers = hyport_tls_peek (portLibrary);
  if (NULL == ptBuffers)
    {
      return 0;
    }

  /* New error ? */
  if (ptBuffers->portableErrorCode != 0)
    {
      return ptBuffers->portableErrorCode;
    }
  else
    {
      return ptBuffers->reportedErrorCode;
    }
}

/**
 * @brief Error Handling
 *
 * Save the platform specific error code and the portable error code for future reference.  Once stored an 
 * application may obtain the error message describing the last stored error by calling hyerror_last_error_message.  
 * Likewise the last portable error code can be obtained by calling hyerror_last_error_number. 
 *
 * @param[in] portLibrary The port library
 * @param[in] platformCode The error code reported by the OS
 * @param[in] portableCode The corresponding portable error code as determined by the caller
 *
 * @return portable error code
 *
 * @note There is no way to access the last platform specific error code.
 *
 * @note If per thread buffers @see hyportptb.h are not available the error code is not stored.
 * This event would only occur if the per thread buffers could not be allocated, which is highly unlikely.  In this 
 * case an application will receive a generic message/errorCode when querying for the last stored values.
 */
I_32 VMCALL
hyerror_set_last_error (struct HyPortLibrary * portLibrary, I_32 platformCode,
                        I_32 portableCode)
{
  PortlibPTBuffers_t ptBuffers;

  /* get the buffers, allocate if necessary.
   * Silently return if not present, what else would the caller do anyway? 
   */
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return portableCode;
    }

  /* Save the last error */
  ptBuffers->platformErrorCode = platformCode;
  ptBuffers->portableErrorCode = portableCode;

  /* Overwrite any customized messages stored */
  if (ptBuffers->errorMessageBufferSize > 0)
    {
      ptBuffers->errorMessageBuffer[0] = '\0';
    }

  return portableCode;
}

/**
 * @brief Error Handling
 *
 * Save the platform specific error code and the portable error code for future reference.  Once stored an 
 * application may obtain the error message describing the last stored error by calling hyerror_last_error_message.  
 * Likewise the last portable error code can be obtained by calling hyerror_last_error_number. 
 *
 * @param[in] portLibrary The port library
 * @param[in] portableCode The corresponding portable error code as determined by the caller
 * @param[in] errorMessage The customized error message to be stored
 *
 * @return portable error code
 *
 * @note There is no way to access the last platform specific error code.
 *
 * @note If per thread buffers @see hyportptb.h are not available the error code is not stored.
 * This event would only occur if the per thread buffers could not be allocated, which is highly unlikely.  In this 
 * case an application will receive a generic message/errorCode when querying for the last stored values.
 */
I_32 VMCALL
hyerror_set_last_error_with_message (struct HyPortLibrary * portLibrary,
                                     I_32 portableCode,
                                     const char *errorMessage)
{
  PortlibPTBuffers_t ptBuffers;
  U_32 requiredSize;

  /* get the buffers, allocate if necessary.
   * Silently return if not present, what else would the caller do anyway? 
   */
  ptBuffers = hyport_tls_get (portLibrary);
  if (NULL == ptBuffers)
    {
      return portableCode;
    }

  /* Save the last error */
  ptBuffers->platformErrorCode = -1;
  ptBuffers->portableErrorCode = portableCode;

  /* Store the message, allocate a bigger buffer if required.  Keep the old buffer around
   * just in case memory can not be allocated
   */
  requiredSize = strlen (errorMessage) + 1;
  requiredSize =
    requiredSize <
    HYERROR_DEFAULT_BUFFER_SIZE ? HYERROR_DEFAULT_BUFFER_SIZE : requiredSize;
  if (requiredSize > ptBuffers->errorMessageBufferSize)
    {
      char *newBuffer =
        portLibrary->mem_allocate_memory (portLibrary, requiredSize);
      if (NULL != newBuffer)
        {
          if (ptBuffers->errorMessageBuffer != NULL)
            {
              portLibrary->mem_free_memory (portLibrary,
                                            ptBuffers->errorMessageBuffer);
            }
          ptBuffers->errorMessageBuffer = newBuffer;
          ptBuffers->errorMessageBufferSize = requiredSize;
        }
    }

  /* Save the message */
  if (ptBuffers->errorMessageBufferSize > 0)
    {
      portLibrary->str_printf (portLibrary, ptBuffers->errorMessageBuffer,
                               ptBuffers->errorMessageBufferSize,
                               "%s",
                               errorMessage);
      ptBuffers->errorMessageBuffer[ptBuffers->errorMessageBufferSize - 1] =
        '\0';
    }

  return portableCode;
}
