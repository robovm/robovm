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
* @author Intel, Evgueni Brevnov
*/  

#ifndef _PORT_ATOMIC_H_
#define _PORT_ATOMIC_H_

/**
* @file
* Atomic operations
*/

#include "open/types.h"
#include "port_general.h"

/**
 * @defgroup port_atomic Atomic operations
 *
 * All atomic operations are perfomance critical,
 * thus they are defined as inlined for most platforms in this file.
 * @ingroup port_apr
 * @{
 */

#ifdef __cplusplus
extern "C" {
#endif

 
#if defined(_IPF_) || defined(DOXYGEN)

/**
* The atomic compare and exchange operation on <code>U_8</code>.
* The function compares the current value of the specified <i>data</i>
* with the <i>comp</i> value and if they match, swaps the <i>data</i>
* with the <i>value</i>.
* @param[in, out] data  - the pointer to the value
* @param[in] value      - the new value
* @param[in] comp       - the value to compare with
* @return The old value.
*/
APR_DECLARE(U_8) port_atomic_cas8(volatile U_8 * data, 
                                               U_8 value, U_8 comp);

/**  
* The atomic compare and exchange operation on uint16. 
* The function compares the current value of the specified <i>data</i>
* with the <i>comp</i> value and if they match, swaps the <i>data</i>
* with the <i>value</i>.
* @param[in, out] data - the pointer to the value
* @param[in] value     - the new value
* @param[in] comp      - the value to compare with
* @return The old value.
*/
APR_DECLARE(uint16) port_atomic_cas16(volatile uint16 * data, 
                                                 uint16 value, uint16 comp);

/**  
* The atomic compare and exchange operation on uint64. 
* The function compares the current value of the specified <i>data</i>
* with the <i>comp</i> value and if they match, swaps the <i>data</i>
* with the <i>value</i>.
* @param[in, out] data - the pointer to the value
* @param[in] value     - the new value
* @param[in] comp      - the value to compare with
* @return The old value.
*/
APR_DECLARE(uint64) port_atomic_cas64(volatile uint64 * data, 
                                                 uint64 value, uint64 comp);

/**  
* The atomic compare and exchange operation on a pointer. 
* The function compares the current value of the specified <i>data</i>
* with the <i>comp</i> value and if they match, swaps the <i>data</i>
* with the <i>value</i>.
* @param[in, out] data - the pointer to the value
* @param[in] value     - the new value
* @param[in] comp      - the value to compare with
* @return The old value.
*/
APR_DECLARE(void *) port_atomic_casptr(volatile void ** data, 
                                       void * value, const void * comp);
/** @} */

#elif defined(_WIN32) && !defined(_WIN64)

PORT_INLINE U_8 port_atomic_cas8(volatile U_8 * data , U_8 value, U_8 comp) {
    __asm {
        mov al,  comp
        mov dl,  value
        mov ecx, data
        lock cmpxchg [ecx], dl
        mov comp, al
    }
    return comp;
}

PORT_INLINE uint16 port_atomic_cas16(volatile uint16 * data , uint16 value, uint16 comp) {
    __asm {
        mov ax,  comp
        mov dx,  value
        mov ecx, data
        lock cmpxchg [ecx], dx
        mov comp, ax
    }
    return comp;
}

PORT_INLINE uint64 port_atomic_cas64(volatile uint64 * data , uint64 value, uint64 comp) {
    __asm {
        lea esi, comp
        mov eax, [esi]
        mov edx, [esi] + 4
            
        lea esi, value
        mov ebx, [esi]
        mov ecx, [esi] + 4
            
        mov esi, data
        lock cmpxchg8b [esi]
        
        lea esi, comp
        mov [esi], eax
        mov [esi] + 4, edx
    }
    return comp;
}

PORT_INLINE void * port_atomic_casptr(volatile void ** data, void * value, const void * comp) {
    return InterlockedCompareExchangePointer((volatile PVOID *) data, value, (PVOID) comp);
}

#elif defined(_EM64T_) && defined (_WIN64)

#pragma intrinsic(_InterlockedCompareExchange16)
#pragma intrinsic(_InterlockedCompareExchange64)

PORT_INLINE U_8 port_atomic_cas8(volatile U_8 * data, 
                                               U_8 value, U_8 comp);

PORT_INLINE uint16 port_atomic_cas16(volatile uint16 * data, 
                                                 uint16 value, uint16 comp)
{
    return _InterlockedCompareExchange16((volatile SHORT *)data, value, comp);
}    

PORT_INLINE uint64 port_atomic_cas64(volatile uint64 * data, 
                                                 uint64 value, uint64 comp)
{
    return _InterlockedCompareExchange64((volatile LONG64 *)data, value, comp);
}

PORT_INLINE void * port_atomic_casptr(volatile void ** data, void * value, const void * comp) {
    return InterlockedCompareExchangePointer((volatile PVOID *) data, value, (PVOID) comp);
}

#elif defined (PLATFORM_POSIX)  

PORT_INLINE U_8 port_atomic_cas8(volatile U_8 * data , U_8 value, U_8 comp) {
#if defined(_IA32_) || defined(_EM64T_)
    __asm__ __volatile__(
        "lock cmpxchgb %1, (%2)"
        :"=a"(comp)
        :"d"(value), "r"(data), "0"(comp)
    );
    return comp;
#else
    ABORT("Not supported");
#endif
}

PORT_INLINE uint16 port_atomic_cas16(volatile uint16 * data , uint16 value, uint16 comp) {
    uint16 ret;
#if defined(_IA32_) || defined(_EM64T_)
    __asm__ __volatile__(
        "lock cmpxchgw %w1, %2"
        :"=a"(ret)
        :"q"(value), "m"(*data), "0"(comp)
        : "memory"
    );
    return ret;
#else
    ABORT("Not supported");
#endif
}

PORT_INLINE uint64 port_atomic_cas64(volatile uint64 * data , uint64 value, uint64 comp) {
#if defined(_IA32_)
    __asm__ __volatile__(
        "push %%ebx;\n\t"
        "lea %0, %%esi;\n\t"
        "mov (%%esi), %%eax;\n\t"
        "mov 4(%%esi), %%edx;\n\t"
        "lea %1, %%esi;\n\t"
        "mov (%%esi), %%ebx;\n\t"
        "mov 4(%%esi), %%ecx;\n\t"
        "mov %2, %%esi;\n\t"
        "lock cmpxchg8b (%%esi);\n\t"
        "lea %0, %%esi;\n\t"
        "mov %%eax, (%%esi);\n\t"
        "mov %%edx, 4(%%esi);\n\t"
        "pop %%ebx"
        : /* no outputs (why not comp?)*/
        :"m"(comp), "m"(value), "m"(data) /* inputs */
        :"%eax", "%ecx", "%edx", "%esi", "memory" /* clobbers */
    );
    return comp;
#elif defined(_EM64T_) // defined(_IA32_)
    __asm__ __volatile__(
        "lock cmpxchgq %1, (%2)"
        :"=a"(comp) /* outputs */
        :"d"(value), "r"(data), "a"(comp)
    );
    return comp;
#else
    ABORT("Not supported");
#endif
}

PORT_INLINE void * port_atomic_casptr(volatile void ** data, void * value, const void * comp) {
#if defined(_IA32_)
    U_32 Exchange = (U_32)value;
    U_32 Comperand = (U_32)comp;
    __asm__ __volatile__(
        "lock cmpxchgl %1, (%2)"
        :"=a"(Comperand)
        :"d"(Exchange), "r"(data), "a"(Comperand) 
        );
    return (void*)Comperand;

#elif defined(_EM64T_) // defined(_IA32_)
    uint64 Exchange = (uint64)value;
    uint64 Comperand = (uint64)comp;
    __asm__(
        "lock cmpxchgq %1, (%2)"
        :"=a"(Comperand)
        :"d"(Exchange), "r"(data), "a"(Comperand) 
        );
    return (void *)Comperand;

#else // defined(_EM64T_)
    ABORT("Not supported");
#endif
}

#endif //defined (POSIX)



#ifdef __cplusplus
}
#endif

#endif /* _PORT_ATOMIC_H_ */
