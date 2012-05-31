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

#ifndef _PORT_BARRIERS_H_
#define _PORT_BARRIERS_H_

/**
* @file
* Memory barriers
*/

#include "open/platform_types.h"
#include "port_general.h"

/**
 * @defgroup port_barriers Memory read/write barriers
 */

#ifdef _WIN32
#   if (_MSC_VER >= 1400)
#       include <intrin.h>
#       include <emmintrin.h>
#   endif
#endif

#ifdef __cplusplus
extern "C" {
#endif

 
#if defined(_IPF_)
#if defined (PLATFORM_POSIX)

void port_rw_barrier(void);
void port_write_barrier(void);

#else /* POSIX */
#error "Windows/IPF is not supported!"
#endif /* POSIX */
#endif /* _IPF_ */


#if defined (PLATFORM_POSIX)

#if !defined(_IPF_)
PORT_INLINE void port_rw_barrier(void)
{
#if defined(_EM64T_)
    asm volatile ("mfence" : : : "memory");
#else /* General x86 case */
    /*
     * This code must use a lock-prefixed assembly instruction, so that 
     * we can support P3 processors (SSE2 only). With P4 and SSE3, we 
     * could use 'mfence'. 
     * References:
     * Java Memory Model cookbook 
     *      - http://gee.cs.oswego.edu/dl/jmm/cookbook.html
     * Linux Kernel, mb() function 
     *      - http://lxr.linux.no/source/include/asm-i386/system.h
     * This is a GCC inline assembly command. The final bit, "memory", will
     * clobber all of the memory registers.
     */
    asm volatile ("lock; addl $0,0(%%esp)" : : : "memory");
#endif
}

PORT_INLINE void port_write_barrier(void)
{
    /* General x86 and x86_64 case */
    /*
     * We could use the same lock-prefixed assembly instruction above,
     * but since we have support for P3 processors (SSE2) we'll just 
     * use 'sfence'.
     */
     asm volatile ("sfence" : : : "memory");
}
#endif /* !defined(_IPF_) */

#else /* !defined (POSIX) */


/* MSVC barrier intrinsics setup */
#if _MSC_VER < 1400
    /* VC++ 2003 */
    void _ReadWriteBarrier(void);
    void _WriteBarrier(void);
    void _mm_mfence(void);
    void _mm_sfence(void);
#endif

#if !defined(__INTEL_COMPILER)
#   pragma intrinsic (_ReadWriteBarrier)
#   pragma intrinsic (_WriteBarrier)
#else
#   define _ReadWriteBarrier __memory_barrier
#   define _WriteBarrier __memory_barrier
#endif


PORT_INLINE void port_rw_barrier(void)
{
#ifdef _EM64T_
    /* if x86_64/x64/EM64T, then use an mfence to flush memory caches */
    _mm_mfence();
#else
    /* otherwise, we assume this is an x86, so insert an inline assembly 
     * macro to insert a lock instruction
     *
     * the lock is what's needed, so the 'add' is setup, essentially, as a no-op
     */
    __asm {lock add [esp], 0 }
#endif
    _ReadWriteBarrier();
}

PORT_INLINE void port_write_barrier(void)
{
    _mm_sfence();
    _WriteBarrier();
}


#endif /* !defined (POSIX) */


#ifdef __cplusplus
}
#endif

#endif /* _PORT_BARRIERS_H_ */
