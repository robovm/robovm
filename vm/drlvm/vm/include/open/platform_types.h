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

#ifndef _PLATFORM_TYPES_H_
#define _PLATFORM_TYPES_H_

#include "common.h"
#include "hycomp.h"

#define MAX_UINT32 0xffffffff
/**
 * DLL stuff
 */
#if defined(PLATFORM_POSIX) || (defined(USE_STATIC_GC) && defined(BUILDING_GC))

#define VMEXPORT
#define VMIMPORT
#define JITEXPORT
#define EMEXPORT

#else  // !PLATFORM_POSIX

#if defined(STATIC_BUILD)
#define VMEXPORT
#define JITEXPORT
#define EMEXPORT
#define VMIMPORT
#elif defined(BUILDING_VM)
#define VMEXPORT __declspec(dllexport)
#define JITEXPORT __declspec(dllimport)
#define EMEXPORT __declspec(dllimport)
#define VMIMPORT __declspec(dllimport)
#else  // !BUILDING_VM & !STATIC_BUILD
#define VMEXPORT __declspec(dllimport)
#define JITEXPORT __declspec(dllexport)
#define EMEXPORT __declspec(dllexport)
#define VMIMPORT __declspec(dllexport)
#endif // !BUILDING_VM & !STATIC_BUILD

#endif // !PLATFORM_POSIX

/**
 * Various Numeric Types
 */

// Boolean, uint16, int16, uint64, int64,
// POINTER_SIZE_INT

// We can't use bool in non-C++ code
#ifndef TRUE
#define TRUE  1
#endif
#ifndef FALSE
#define FALSE 0
#endif

// RoboVM note: This used to be called Boolean but we already use Boolean in RoboVM.
typedef unsigned boolean;

//::For long long int, add the LL 
#ifndef __WORDSIZE // exclude remark #193: zero used for undefined preprocessing identifier
#define __WORDSIZE 0
#endif
#if !defined(__INT64_C)
# if __WORDSIZE == 64 || defined(WIN32)
#  define __INT64_C(c)  c ## L
#  define __UINT64_C(c) c ## UL
# else
#  define __INT64_C(c)  c ## LL
#  define __UINT64_C(c) c ## ULL
# endif
# endif

#ifdef PLATFORM_POSIX
typedef unsigned short uint16;
typedef unsigned long long uint64;

typedef   signed short int16;
typedef   signed long long int64;

#else //!PLATFORM_POSIX

#ifndef __INSURE__
// these give Insure++ problems:
typedef unsigned __int16 uint16;
typedef   signed __int16 int16;
#else
// so use these definitions instead with Insure++:
typedef unsigned short uint16;
typedef   signed short int16;
#endif
typedef unsigned __int64 uint64;
typedef   signed __int64 int64;

#endif //!PLATFORM_POSIX

/**
 * printf format specifier for 64-bit integers
 */
#ifdef _WIN32
#define FMT64 "I64"
#else 
#define FMT64 "ll"
#endif


/**
 * The integer datatypes on the platform that can hold a pointer.
 */
#ifdef POINTER64
#define POINTER_SIZE_INT uint64
#define POINTER_SIZE_SINT int64
#ifdef PLATFORM_NT
#define PI_FMT "I64"
#else 
#define PI_FMT "ll"
#endif
#define W_PI_FMT "016"PI_FMT"x"
#else
#define POINTER_SIZE_INT U_32
#define POINTER_SIZE_SINT I_32
#define PI_FMT ""
#define W_PI_FMT "08"PI_FMT"x"
#endif // POINTER64


/**
 * The register context structure
 */
#ifdef _IPF_

struct Registers {

    uint64 gr[32];
    uint64 fp[128];
    uint64 br[8];
    uint64 preds;
    uint64 nats;
    uint64 pfs;
    uint64 *bsp;
    uint64 ip;

#ifdef __cplusplus
    void  reset_ip() { ip = 0; }
    void* get_ip() { return (void*)ip; }
    void  set_ip(void* src_ip) { ip = (uint64)src_ip; }
    void* get_sp() { return (void*)bsp; }
    void  set_sp(void* src_sp) { }
#endif
}; //Registers

#else  // !_IPF_

#ifdef _EM64T_

struct Registers {
    uint64 rsp;
    uint64 rbp;
    uint64 rip;
    // callee-saved
    uint64 rbx;
    uint64 r12;
    uint64 r13;
    uint64 r14;
    uint64 r15;
    // scratched
    uint64 rax;
    uint64 rcx;
    uint64 rdx;
    uint64 rsi;
    uint64 rdi;
    uint64 r8;
    uint64 r9;
    uint64 r10;
    uint64 r11;

    U_32 eflags;

#ifdef __cplusplus
    void  reset_ip() { rip = 0; }
    void* get_ip() { return (void*)rip; }
    void  set_ip(void* src_ip) { rip = (uint64)src_ip; }
    void* get_sp() { return (void*)rsp; }
    void  set_sp(void* src_sp) { rsp = (uint64)src_sp; }
#endif
}; //Registers

#else // ! _EM64T_

struct Registers {
    U_32 eax;
    U_32 ebx;
    U_32 ecx;
    U_32 edx;
    U_32 edi;
    U_32 esi;
    U_32 ebp;
    U_32 esp;
    U_32 eip;
    U_32 eflags;

#ifdef __cplusplus
    void  reset_ip() { eip = 0; }
    void* get_ip() { return (void*)eip; }
    void  set_ip(void* src_ip) { eip = (U_32)src_ip; }
    void* get_sp() { return (void*)esp; }
    void  set_sp(void* src_sp) { esp = (U_32)src_sp; }
#endif
}; //Registers

#endif // _EM64T_

#endif //!_IPF_


#endif //!_PLATFORM_TYPES_H_
