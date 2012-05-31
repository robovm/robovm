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
 * @author Evgueni Brevnov, Andrey Yakushev
 */
#ifndef _PORT_MALLOC_H_
#define _PORT_MALLOC_H_

#include "port_general.h"
#ifdef WIN32
    #include <malloc.h>
#else
    #include <stdlib.h>
#endif

#ifdef __cplusplus
    #define INLINE inline
#else
    #ifdef WIN32
        #define INLINE __forceinline
    #else
        #define INLINE static
    #endif
#endif

/**
 * If defined then debug variants of memory allocation functions (<code>malloc</code>,
 * <code>free</code> etc.) are used.
 * They provides additional functionaliy, like detailed statistic and
 * memory leackage control. 
 */
//#define _MEMMGR

/** Pool name for java.lang.management usage */
#define NATIVE_POOL_NAME "Native Memory Pool"

/** Defailt file name for feporting the memory usage informaion */
#define MALLOC_LOG_FILE_NAME "malloc.log"

#ifdef _MEMMGR
    /** If defined then all native allocations and deallocations will be loged in MALLOC_LOG_FILE_NAME */
    #define _MEMMGR_LOG
    /** If defined then report of currently unfreed memory will be loged in MALLOC_LOG_FILE_NAME */
    #define _MEMMGR_REPORT

    #ifdef __cplusplus
    extern "C" {
    #endif

    /**
     * Wrapper for standard <code>free</code> function. Used if _MEMMGR is defined.
     * Provides checking for suquential call with the same address, and memory leackage.
     * @param[in] APTR        - address of memory block which would be checked ant passed
     *                          to <code>free</code> function
     * @param[in] file_name   - file name where <code>port_free</code> is called
     * @param[in] file_line   - file line number where <code>port_free</code> is called
     */
    APR_DECLARE(void) port_free(void *APTR, char *file_name, int file_line);

    /**
     * Wrapper for standard <code>malloc</code> function. Used if _MEMMGR is defined.
     * Provides storing arguments info about this call for statistics and additinal control, like
     * memory leackage.
     * @param[in] NBYTES      - size in bytes passed to <code>malloc</code> function
     * @param[in] file_name   - file name where <code>port_malloc</code> is called
     * @param[in] file_line   - file line number where <code>port_malloc</code> is called
     */
    APR_DECLARE(void*) port_malloc(size_t NBYTES, char *file_name, int file_line);

    /**
     * Initiates memory control system. Used if _MEMMGR is defined. All memory allocation
     * and releasing calls would be monitored futher. Explicit initialization is requirred
     * for controling memory management during defined execution range.
     */
    void start_monitor_malloc();

    /**
     * Stops memory control system. Used if _MEMMGR is defined. Provides generation of detailed
     * statistics at this place.
     */
    void report_leaked_malloc();

    /**
     * Returns size of memory in bytes currently used by <code>port_malloc</code>. 
     * @return size of memory in bytes currently used by <code>port_malloc</code>.
     */
    size_t port_mem_used_size();

    /**
     * Returns size of memory in bytes currently reserved by <code>port_malloc</code>. 
     * @return size of memory in bytes currently reserved by <code>port_malloc</code>.
     */
    size_t port_mem_reserved_size();

    /**
     * Returns size of committed memory in bytes currently used by <code>port_malloc</code>. 
     * @return size of committed memory in bytes currently used by <code>port_malloc</code>.
     */
    size_t port_mem_committed_size();

    /**
     * Returns maximum size of memory in bytes which could be used by <code>port_malloc</code>. 
     * @return maximum size of memory in bytes which could be used by <code>port_malloc</code>.
     */
    size_t port_mem_max_size();

    #ifdef _MEMMGR_LOG

    #define _MEMMGR_LOG_OR_REPORT
        #define MEMMGR_LOG(m) fprintf m;
    #else
        #define MEMMGR_LOG(m)
    #endif

    #ifdef _MEMMGR_REPORT
        #define _MEMMGR_LOG_OR_REPORT
    #endif

    #ifdef __cplusplus
    }
    #endif


    #ifdef STRESS_MALLOC
        // TODO: implement here small preallocated malloc for out of memory conditions
        #define STD_FREE(p) free(p)
        #define STD_MALLOC(s) malloc(s)
        #define STD_CALLOC(n, s) calloc(n, s)
        #define STD_REALLOC(p, s) realloc(p, s)
        #define STD_ALLOCA(s) alloca(s)
    #else // not defined STRESS_MALLOC
        #define STD_FREE(p) port_free(p, __FILE__, __LINE__)
        #define STD_MALLOC(s) port_malloc(s, __FILE__, __LINE__)
        #define STD_CALLOC(n, s) calloc(n, s)
        #define STD_REALLOC(p, s) realloc(p, s)
        #define STD_ALLOCA(s) alloca(s)
    #endif //STRESS_MALLOC
#else // not defined _MEMMGR
    #define STD_FREE(p) free(p)
    #define STD_MALLOC(s) malloc(s)
    #define STD_CALLOC(n, s) calloc(n, s)
    #define STD_REALLOC(p, s) realloc(p, s)
    #define STD_ALLOCA(s) alloca(s)
#endif // _MEMMGR

#define STD_PCALLOC_STRUCT(pool, type, name) \
    type* name = (type*) apr_pcalloc(pool, sizeof(type)); \
    if (NULL == name) { \
        return ENOMEM; \
    }

#endif // _PORT_MALLOC_H_
