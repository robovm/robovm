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
 * @brief CPU Control.
 *
 * Functions setting CPU attributes.
 */

#undef CDEV_CURRENT_FUNCTION

#include <stdlib.h>
#if defined(HYPPC32) || defined(HYPPC64)
#include <string.h>
#endif


#include "hyport.h"
#if defined(HYPPC32) || defined(HYPPC64)
#include "portpriv.h"
#include "hyportpg.h"
#endif


#if (__IBMC__||__IBMCPP__)
void dcbf (unsigned char *);
void icbi (unsigned char *);
void sync (void);
void isync (void);
void dcbz (void *);
#pragma mc_func dcbf  {"7c0018ac"}
#pragma mc_func icbi  {"7c001fac"}
#pragma mc_func sync  {"7c0004ac"}
#pragma mc_func isync {"4c00012c"}
#pragma mc_func dcbz {"7c001fec"}
#pragma reg_killed_by dcbf
#pragma reg_killed_by dcbz
#pragma reg_killed_by icbi
#pragma reg_killed_by sync
#pragma reg_killed_by isync
#endif


#define CDEV_CURRENT_FUNCTION hycpu_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the exit operations may be created here.  All resources created here should be destroyed
 * in @ref hycpu_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_CPU
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hycpu_startup (struct HyPortLibrary *portLibrary)
{
  /* initialize the ppc level 1 cache line size */
#if defined(HYPPC32) || defined(HYPPC64)
  int ppcCacheLineSize;
  int i;
  int input1 = 20;
  char buf[1024];
  memset (buf, 255, 1024);
#if (__IBMC__||__IBMCPP__)
  dcbz ((void *) &buf[512]);
#elif defined(LINUX)
__asm__ ("dcbz 0, %0":         /* no outputs */
:"r" ((void *) &buf[512]));
#endif
for (i = 0, ppcCacheLineSize = 0; i < 1024; i++)
    {
      if (buf[i] == 0)
        {
          ppcCacheLineSize++;
        }
    }
  PPG_mem_ppcCacheLineSize = ppcCacheLineSize;
#endif


  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hycpu_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hycpu_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hycpu_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hycpu_flush_icache
/**
 * @brief CPU Control operations.
 *
 * Flush the instruction cache to memory.
 *
 * @param[in] portLibrary The port library
 * @param[in] memoryPointer The base address of memory to flush.
 * @param[in] byteAmount Number of bytes to flush.
 */
void VMCALL
hycpu_flush_icache (struct HyPortLibrary *portLibrary, void *memoryPointer,
                    UDATA byteAmount)
{
	
#if defined(LINUX) && defined(ARMGNU)
  privateFlushICache (memoryPointer, byteAmount);

#elif defined(HYPPC32) || defined(HYPPC64)
  int cacheLineSize = PPG_mem_ppcCacheLineSize;
  unsigned char *addr;
  unsigned char *limit;
  limit =
    (unsigned char
     *) (((unsigned long) memoryPointer + (unsigned int) byteAmount +
          (cacheLineSize - 1)) / cacheLineSize * cacheLineSize);
  /* for each cache line, do a data cache block flush */
  for (addr = (unsigned char *) memoryPointer; addr < limit;
       addr += cacheLineSize)
    {
#if (__IBMC__||__IBMCPP__)
      dcbf (addr);
#elif defined(LINUX)
    __asm__ ("dcbf 0,%0":      /* no outputs */
    :"r" (addr));
#endif
}
#if (__IBMC__||__IBMCPP__)
  sync ();
#elif defined(LINUX)
  __asm__ ("sync");
#endif
/* for each cache line  do an icache block invalidate */
  for (addr = (unsigned char *) memoryPointer; addr < limit;
       addr += cacheLineSize)
    {
#if (__IBMC__||__IBMCPP__)
      icbi (addr);
#elif defined(LINUX)
    __asm__ ("icbi 0,%0":      /* no outputs */
    :"r" (addr));
#endif
}
#if (__IBMC__||__IBMCPP__)
  isync ();
#elif defined(LINUX)
  __asm__ ("isync");
#endif

#endif /* HYPPC32 || HYPPC64 */


}

#undef CDEV_CURRENT_FUNCTION
