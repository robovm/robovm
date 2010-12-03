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

#if !defined(_UTE_PD_H)
#define _UTE_PD_H

#include "hycomp.h"
#include "jni.h"
#if defined(__cplusplus)
extern "C" {
#endif
/*
 * =============================================================================
 *  Platform dependent primitives
 * =============================================================================
 */
typedef I_64                UT_I64;            /* Signed 64bit integer        */
typedef I_32                UT_I32;            /* Signed 32bit integer        */
typedef I_16                UT_I16;            /* Signed 16bit integer        */
typedef I_8                 UT_I8;             /* Signed 8bit integer         */
typedef IDATA               UT_IPTR;           /* Signed ptr-sized integer    */
typedef U_64                UT_U64;            /* Unsigned 64bit integer      */
typedef U_32                UT_U32;            /* Unsigned 32bit integer      */
typedef U_16                UT_U16;            /* Unsigned 16bit integer      */
typedef U_8                 UT_U8;             /* Unsigned 8bit integer       */
typedef UDATA               UT_UPTR;           /* Unsigned ptr-sized integer  */
typedef I_32                UT_BOOL;           /* Boolean                     */
typedef I_32                UT_FD;             /* File descriptor             */
/*
 * =============================================================================
 *  Platform dependent return codes - Use JNI return codes for this application
 *  Note that all error codes must be negative
 * =============================================================================
 */
#define UTE_OK          JNI_OK
#define UTE_ERROR       JNI_ERR
#define UTE_BADVERSION  JNI_EVERSION
#define UTE_OUTOFMEMORY JNI_ENOMEM
#define UTE_INVALID     JNI_EINVAL
/*
 * =============================================================================
 *  Platform dependent calling conventions
 * =============================================================================
 */
#define UTECALL     JNICALL
#define UTEEXPORT   JNIEXPORT
/*
 * =============================================================================
 *  RAS event semaphore - platform dependent portion
 * =============================================================================
 */
#if !defined(UT_EVENT_SEM)
#define UT_EVENT_SEM void *
#endif
typedef struct  utEventSem_pd {
    UT_EVENT_SEM  sem;
    volatile int flags;
} UtEventSem_pd;
#define UT_SEM_WAITING 1
#define UT_SEM_POSTED  2
/*
 * =============================================================================
 * Constants for CPU type etc
 * =============================================================================
 */
#define UT_HASTSC       0x0010
#define UT_FAMILY       0x0F00
#define UT_IS586        0x0500
#define UT_FAMILY_MODEL 0x0FF0
#define UT_PIII_MODEL7  0x0670
#define UT_P4_MODEL     0x0F00
#define UT_EXT_FAMILY   0x0F00000
#if defined(__cplusplus)
}
#endif

#endif /* !_UTE_PD_H */
