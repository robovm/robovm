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
 * @file thread_ncai_common.c
 * @brief NCAI basic thread functions
 */

#include <open/hythread_ext.h>
#include <open/ncai_thread.h>

#include "thread_private.h"
#include "port_thread.h"


/**
 * Suspend thread as OS native thread.
 *
 * @param[in] thread The thread to suspend.
 */
IDATA VMCALL hythread_suspend_thread_native(hythread_t thread)
{
    if (thread == NULL)
        return TM_ERROR_NULL_POINTER;

    if (!hythread_is_alive(thread))
        return TM_ERROR_INTERNAL;

    assert(thread->os_handle);

    return port_thread_suspend(thread->os_handle);
}

/**
 * Resume thread as OS native thread.
 *
 * @param[in] thread The thread to resume.
 */
IDATA VMCALL hythread_resume_thread_native(hythread_t thread)
{
    if (thread == NULL)
        return TM_ERROR_NULL_POINTER;

    if (!hythread_is_alive(thread))
        return TM_ERROR_INTERNAL;

    assert(thread->os_handle);

    return port_thread_resume(thread->os_handle);
}

/**
 * Returns suspend count for given thread.
 *
 * @param[in] thread The thread to process.
 * @return -1 if error have occured
 */
int VMCALL hythread_get_suspend_count_native(hythread_t thread)
{
    if (thread == NULL)
        return -1;

    if (!hythread_is_alive(thread))
        return -1;

    assert(thread->os_handle);

    return port_thread_get_suspend_count(thread->os_handle);
}

/**
 * Returns the platform-dependent thread context.
 *
 * @param[in] thread to get context.
 * @param[out] pointer to context structure.
 */
IDATA VMCALL hythread_get_thread_context(hythread_t thread, thread_context_t* pcontext)
{
    if (pcontext == NULL || thread == NULL)
        return TM_ERROR_NULL_POINTER;

    if (!hythread_is_alive(thread))
        return TM_ERROR_INTERNAL;

    assert(thread->os_handle);

    return port_thread_get_context(thread->os_handle, pcontext);
}

/**
 * Sets the context for given thread.
 *
 * @param[in] thread to set context.
 * @param[in] pointer to context structure.
 */
IDATA VMCALL hythread_set_thread_context(hythread_t thread, thread_context_t* pcontext)
{
    if (pcontext == NULL || thread == NULL)
        return TM_ERROR_NULL_POINTER;

    if (!hythread_is_alive(thread))
        return TM_ERROR_INTERNAL;

    assert(thread->os_handle);

    return port_thread_get_context(thread->os_handle, pcontext);
}
