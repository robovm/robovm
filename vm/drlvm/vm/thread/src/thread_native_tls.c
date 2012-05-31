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
 * @file thread_native_tls.c
 * @brief Hythread TLS related functions
 */

#include <open/hythread_ext.h>
#include "thread_private.h"
#include <jni.h>

/** @name Thread local storage support
 */
//@{

int16 tm_tls_capacity = 16;
int16 tm_tls_size = TM_THREAD_QUANTITY_OF_PREDEFINED_TLS_KEYS;

static void tls_finalizer_placeholder(void *args) {}

/**
 * Returns non-zero if a 'fast TLS access' is in use.
 *
 * The 'fast TLS access' varies depending on platform.
 *
 * On Windows, the thread manager's structure is stored in the free slot
 * of the TIB (see http://www.microsoft.com/msj/archive/S2CE.aspx).
 * On Linux, it's stored in app's TLS where it can be retrieved from.
 */
UDATA VMCALL hythread_uses_fast_tls(void) {
#ifdef HYTHREAD_FAST_TLS
    return 1;
#else
    return 0;
#endif
}

#if !defined(_WIN32) && defined(HYTHREAD_FAST_TLS)
    #if defined(_EM64T_)
        #define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        { \
            void* tmp1 = &var; \
            void* tmp2; \
            asm(" movq %%fs:0x00, %0": "=r" (tmp2)); \
            offset = tmp1 - tmp2; \
        }
        //TODO: GCC-specific, need to add ICL
        //#define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        //    { void* tmp;  __asm ("movq " #var "@GOTTPOFF(%%rip), %0" : "=r" (tmp)); offset = tmp; }
    #elif defined(_IA32_)
        #define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        { \
            void* tmp1 = &var; \
            void* tmp2; \
            asm(" movl %%gs:0x00, %0": "=r" (tmp2)); \
            offset = tmp1 - tmp2; \
        }
        //TODO: GCC-specific, need to add ICL
        //#define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        //    { __asm ("movl $" #var "@ntpoff, %0" : "=r" (offset)); }
    #elif defined(_IPF_)
        #define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        { \
            void* tmp1 = &var; \
            void* tmp2; \
            asm("mov %0 = r13;;": "=r" (tmp2)); \
            offset = tmp1 - tmp2; \
        }
        //TODO: GCC-specific, need to add ICL
        //TODO: NOTE: not even tested, may require fix.
        //#define HYTHREAD_TLS_GET_VAR_OFFSET(var,offset) \
        //    { __asm ("addl %0 = @tprel(" #var "#), r0 ;;\n" : "=r" (offset)) }
    #else
        #error "Don't know how to get the variable offset in TLS on this platfrom. Try to undef HYTHREAD_FAST_TLS or provide the macros here."
    #endif
#else
    #define HYTHREAD_TLS_GET_VAR_OFFSET(var, offset) { offset = -1; }
#endif

/**
 * Returns offset of the hythread's control structure in the TLS.
 *
 * @note Only meaningful on Linux-es \b and when hythread_uses_fast_tls() returns \c true.
 */
IDATA VMCALL hythread_get_hythread_offset_in_tls(void) {
    int threadOffset;
    HYTHREAD_TLS_GET_VAR_OFFSET(tm_self_tls, threadOffset);

    return threadOffset;
}

/**
 * Allocate a thread local storage (TLS) key.
 *
 * Create and return a new, unique key for thread local storage.
 *
 * @note The handle returned will be >=0, so it is safe to test the handle against 0 to see if it's been
 * allocated yet.
 *
 * @param[out] handle pointer to a key to be initialized with a key value
 * @return 0 on success or negative value if a key could not be allocated (i.e. all TLS has been allocated)
 *
 * @see hythread_tls_free, hythread_tls_set
 */
IDATA VMCALL hythread_tls_alloc(hythread_tls_key_t *handle) {
    return hythread_tls_alloc_with_finalizer(handle, tls_finalizer_placeholder);
}

/**
 * Allocate a thread local storage (TLS) key.
 *
 * Create and return a new, unique key for thread local storage.
 *
 * @note The handle returned will be >=0, so it is safe to test the handle against 0 to see if it's been
 * allocated yet.
 *
 * @param[out] handle pointer to a key to be initialized with a key value
 * @param[in] finalizer a finalizer function which will be invoked when a thread is
 * detached or terminates if the thread's TLS entry for this key is non-NULL
 * @return 0 on success or negative value if a key could not be allocated (i.e. all TLS has been allocated)
 *
 * @see hythread_tls_free, hythread_tls_set
 */
IDATA VMCALL hythread_tls_alloc_with_finalizer(hythread_tls_key_t *handle, hythread_tls_finalizer_t finalizer) {
    if (tm_tls_size < tm_tls_capacity - 1) {
        *handle = ++tm_tls_size;
        return TM_ERROR_NONE;
    }

    return -1;
}

/**
 * Set a thread's TLS value.
 *
 * @param[in] thread a thread
 * @param[in] key key to have TLS value set (any value returned by hythread_alloc)
 * @param[in] data value to be stored in TLS
 * @return 0 on success or negative value on failure
 *
 * @see hythread_tls_alloc, hythread_tls_free, hythread_tls_get
 */
IDATA VMCALL hythread_tls_set(hythread_t thread, hythread_tls_key_t key, void *data) {
    assert(thread->thread_local_storage);
    thread->thread_local_storage[key] = data;
    return TM_ERROR_NONE;
}

/**
 * Release a TLS key.
 *
 * Release a TLS key previously allocated by hythread_tls_alloc.
 *
 * @param[in] key TLS key to be freed
 * @return 0 on success or negative value on failure
 *
 * @see hythread_tls_alloc, hythread_tls_set
 */
IDATA VMCALL hythread_tls_free(hythread_tls_key_t key) {
    //@TODO: implement entries deletion in TLS
    return TM_ERROR_NONE;
}

/**
 * Returns 'request' field offset in HyThread struct
 */
UDATA VMCALL hythread_tls_get_request_offset() {
    return (UDATA)&((hythread_t)0)->request;
}

/**
 * Return value's offset for the given key from the HyThread struct start.
 */
UDATA VMCALL hythread_tls_get_offset(hythread_tls_key_t key) {
    assert(key < (UDATA)tm_tls_capacity);
    return ((UDATA)&((hythread_t)0)->thread_local_storage) + (key * sizeof(void*));
}

//@}
