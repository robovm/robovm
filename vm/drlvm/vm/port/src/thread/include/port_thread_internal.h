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

#ifndef _PORT_THREAD_INTERNAL_H_
#define _PORT_THREAD_INTERNAL_H_

#include "port_general.h"
#include "port_thread.h"
#include "open/platform_types.h"
#include "open/hythread_ext.h" /* For windows.h */
/* UNIX-specific includes */
#ifndef WIN32
#include <semaphore.h>
#endif

/* The name of Port shared library - now crash handler module */
#define CH_SHLIB_NAME "ch"

#ifdef WIN32
typedef DWORD port_tls_key_t;
#else
typedef pthread_key_t port_tls_key_t;
#endif

typedef enum
{
    THREADREQ_NONE = 0,
    THREADREQ_SUS = 1,
    THREADREQ_RES = 2,
    THREADREQ_YIELD = 3
} port_suspend_req_t;


typedef struct port_thread_info_t port_thread_info_t;
struct port_thread_info_t
{
    osthread_t              thread;
    thread_context_t        context;
    int                     suspend_count;
#ifndef WIN32
    sem_t                   wake_sem;       /* to sem_post from signal handler */
#endif /* !WIN32 */
    port_thread_info_t*       next;
};


typedef struct
{
    port_tls_key_t tls_key;

    size_t guard_page_size;
    size_t guard_stack_size;
    size_t mem_protect_size;
    size_t foreign_stack_size;

    /* Stuff for suspend/resume handling */
    port_thread_info_t* suspended_list;
#ifdef WIN32
    CRITICAL_SECTION crit_section;
#else /* !WIN32 */
    pthread_mutex_t suspend_init_mutex;
    pthread_mutex_t suspend_mutex;
    port_suspend_req_t req_type; /* request type for signal handler */
    osthread_t suspendee; /* The thread which is processed */
    sem_t yield_sem; /* Semaphore to inform signal sender */
    boolean signal_set; /* Is SIGUSR2 handler set up */
#endif /* !WIN32 */

} port_shared_data_t;


/* Thread-specific structure */
typedef struct
{
    /* vv Threading stuff vv */
    void*  stack_addr;
    size_t stack_size;
    void*  guard_page_addr;
    size_t guard_page_size;
    void*  guard_stack_addr;
    size_t guard_stack_size;
    size_t mem_protect_size;

    boolean guard_page_set;
    boolean restore_guard_page;

    boolean foreign; /* The thread was attached */
    //boolean temp; /* Is indicated by 0ed guard_page_addr instead */

    /* vv Signal handling stuff vv */
#ifndef WIN32 /* UNIX */
    /* Flag and restart address for memory access violation detection */
    int     violation_flag;
    void*   restart_address;
#endif /* UNIX */

    /* vv Crash handling stuff vv */
    /* Previous crash handling stage to restart crash processing */
    int     crash_stage;

    /* Flag to indicate that debugger should be attached right in OS handler */
    boolean debugger;
    /* Flag to produce minidump/core on the second exception catch */
    boolean   produce_core;

} port_tls_data_t;


#ifdef __cplusplus
extern "C" {
#endif

extern volatile port_shared_data_t* port_shared_data;
#define PSD ((port_shared_data_t*)port_shared_data)

int init_port_shared_data();


PORT_INLINE port_tls_data_t* get_private_tls_data()
{
    if (!port_shared_data && init_port_shared_data() != 0)
            return NULL;
#ifdef WIN32
    return (port_tls_data_t*)TlsGetValue(port_shared_data->tls_key);
#else
    return (port_tls_data_t*)pthread_getspecific(port_shared_data->tls_key);
#endif
}

PORT_INLINE int set_private_tls_data(port_tls_data_t* data)
{
    if (!port_shared_data && init_port_shared_data() != 0)
            return -1;
#ifdef WIN32
    return TlsSetValue(port_shared_data->tls_key, data) ? 0 : -1;
#else
    return pthread_setspecific(port_shared_data->tls_key, data);
#endif
}


/* Is used in both threading and signals */
int port_thread_attach_local(port_tls_data_t* tlsdata, boolean temp,
                                boolean foreign, size_t stack_size);
/* Detaches temporarily attached thread */
int port_thread_detach_temporary();

#ifndef WIN32
/* To restore alternative stack out of signal handler on Linux */
int set_alt_stack(port_tls_data_t* tlsdata, boolean set);

#endif


#ifdef __cplusplus
}
#endif

#endif /* _PORT_THREAD_INTERNAL_H_ */
