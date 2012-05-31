/*
 *  Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
 * @author Ilya Berezhniuk
 */

#ifndef OPEN_NCAI_THREAD_H
#define OPEN_NCAI_THREAD_H

/**
 * @file ncai_thread.h
 * @brief NCAI thread support
 * @details
 * Support for NCAI threading functionality.
 * Is needed because NCAI must gether some OS-specific information,
 * encapsulated within thread manager.
 * All functions start with hythread_* prefix.
 */

#include "jvmti_types.h"
#include "hythread_ext.h"
#include "port_thread.h"


#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */


/** @name Threads manipulation and information
 */
//@{

extern HY_CFUNC IDATA VMCALL
hythread_suspend_thread_native PROTOTYPE((hythread_t thread));
extern HY_CFUNC IDATA VMCALL
hythread_resume_thread_native PROTOTYPE((hythread_t thread));
extern HY_CFUNC int VMCALL
hythread_get_suspend_count_native PROTOTYPE((hythread_t thread));

extern HY_CFUNC IDATA VMCALL
hythread_get_thread_context PROTOTYPE((hythread_t thread, thread_context_t* pcontext));
extern HY_CFUNC IDATA VMCALL
hythread_set_thread_context PROTOTYPE((hythread_t thread, thread_context_t* pcontext));

//@}

#ifdef __cplusplus
}
#endif

#endif  /* OPEN_NCAI_THREAD_H */
