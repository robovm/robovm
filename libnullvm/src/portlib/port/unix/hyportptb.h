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

#if !defined(hyportptb_h)
#define hyportptb_h
/**
 * @file
 * @ingroup Port
 * @brief Per Thread Buffers
 *
 * Per thread buffers are used to store information that is not sharable among the threads.  
 * For example when an OS system call fails the error code associated with that error is
 * relevant to the thread that called the OS function; it has no meaning to any other thread.
 *
 * This file contains the structure of the per thread buffers.  @see hytlshelpers.c for details on accessing
 * these buffers..
 */
#include "hyport.h"
#include "hysocket.h"
#include "hysock.h"

#define HYERROR_DEFAULT_BUFFER_SIZE 256 /**< default customized error message size if we need to create one */
/**
 * @typedef struct PortlibPTBuffers_struct
 * @brief The per thread buffer
 * Storage for data related to the threads state.
 */
typedef struct PortlibPTBuffers_struct
{
  struct PortlibPTBuffers_struct *next;       /**< Next per thread buffer */
  struct PortlibPTBuffers_struct *previous;       /**< Previous per thread buffer */
  I_32 platformErrorCode;       /**< error code as reported by the OS */
  I_32 portableErrorCode;       /**< error code translated to portable format by application */
  char *errorMessageBuffer;       /**< last saved error message, either customized or from OS */
  U_32 errorMessageBufferSize;       /**< error message buffer size */
  I_32 reportedErrorCode;       /**< last reported error code */
  char *reportedMessageBuffer;       /**< last reported error message, either customized or from OS */
  U_32 reportedMessageBufferSize;       /**< reported message buffer size */
  hyfdset_t fdset;       /**< file descriptor set */
  char ntoa[NTOA_SIZE];
  hyaddrinfo_struct addr_info_hints;
#if HOSTENT_DATA_R||GLIBC_R||OTHER_R||NO_R
  OSHOSTENT hostent;
#endif

#if HOSTENT_DATA_R
  OSHOSTENT_DATA *hostent_data;
#elif GLIBC_R || OTHER_R || NO_R
  int gethostBufferSize;
  char *gethostBuffer;
#endif                          /* HOSTENT_DATA_R */

} PortlibPTBuffers_struct;
/**
 * @typedef struct PortlibPTBuffers_struct
 * @brief The per thread buffer
 * Storage for data related to the threads state.
 */
typedef struct PortlibPTBuffers_struct *PortlibPTBuffers_t;
void VMCALL hyport_free_ptBuffer (struct HyPortLibrary *portLibrary,
                                  PortlibPTBuffers_t ptBuffer);
#endif /* hyportptb_h */
