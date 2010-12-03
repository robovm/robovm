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

#include "hyportptb.h"

void VMCALL hyport_free_ptBuffer (struct HyPortLibrary *portLibrary,
                                  PortlibPTBuffers_t ptBuffer);

/**
 * @internal
 * @brief  Per Thread Buffer Support
 *
 * Free all memory associated with a per thread buffer, including any memory it may
 * have allocated.
 *
 * @param[in] portLibrary The port library.
 * @param[in] ptBuffers pointer to the PortlibPTBuffers struct that contains the buffers
 */
void VMCALL
hyport_free_ptBuffer (struct HyPortLibrary *portLibrary,
                      PortlibPTBuffers_t ptBuffer)
{
  if (NULL != ptBuffer)
    {
      if (NULL != ptBuffer->errorMessageBuffer)
        {
          portLibrary->mem_free_memory (portLibrary,
                                        ptBuffer->errorMessageBuffer);
          ptBuffer->errorMessageBufferSize = 0;
        }
      if (NULL != ptBuffer->reportedMessageBuffer)
        {
          portLibrary->mem_free_memory (portLibrary,
                                        ptBuffer->reportedMessageBuffer);
          ptBuffer->reportedMessageBufferSize = 0;
        }
      if (NULL != ptBuffer->fdset)
        {
          portLibrary->mem_free_memory (portLibrary, ptBuffer->fdset);
        }
#if defined(IPv6_FUNCTION_SUPPORT)
      if (NULL != ptBuffer->addr_info_hints.addr_info)
        {
          portLibrary->mem_free_memory (portLibrary,
                                        ptBuffer->addr_info_hints.addr_info);
        }
#endif

#if HOSTENT_DATA_R
      if (NULL != ptBuffer->hostent_data)
        {
          portLibrary->mem_free_memory (portLibrary, ptBuffer->hostent_data);
        }
#elif GLIBC_R || OTHER_R || NO_R
      if (NULL != ptBuffer->gethostBuffer)
        {
          portLibrary->mem_free_memory (portLibrary, ptBuffer->gethostBuffer);
        }
#endif

      portLibrary->mem_free_memory (portLibrary, ptBuffer);
    }
}
