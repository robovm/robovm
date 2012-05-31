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

#undef __USE_XOPEN
#include <sys/types.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
#include <signal.h>
#include <pthread.h>
#include <semaphore.h>

#include "port_mutex.h"
#include "port_atomic.h"
#include "port_thread_internal.h"

volatile port_shared_data_t* port_shared_data = NULL;

static pthread_mutex_t g_shared_data_mutex = PTHREAD_MUTEX_INITIALIZER;
static port_shared_data_t* g_port_shared_data = NULL;
static port_shared_data_t g_port_shared_data_struct;

#ifdef _EM64T_
#define MEM_PROTECT_SIZE 0x400
#elif defined (_IPF_)
#define MEM_PROTECT_SIZE 0
#else /* _IA32_ */
#define MEM_PROTECT_SIZE 0x100
#endif

static int init_psd_structure(port_shared_data_t* data)
{
    int err;
    pthread_attr_t pthread_attr;
    size_t stack_size, guard_size;

    memset(data, 0, sizeof(port_shared_data_t));

    if ((err = pthread_key_create(&data->tls_key, NULL)) != 0)
        return err;

    if ((err = pthread_mutex_init(&data->suspend_mutex, NULL)) != 0)
        return err;

    if ((err = pthread_mutex_init(&data->suspend_init_mutex, NULL)) != 0)
        return err;

    pthread_attr_init(&pthread_attr);
    err = pthread_attr_getstacksize(&pthread_attr, &stack_size);
    pthread_attr_destroy(&pthread_attr);

    if (err != 0)
        return err;

    pthread_attr_init(&pthread_attr);
    err = pthread_attr_getguardsize(&pthread_attr, &guard_size);
    pthread_attr_destroy(&pthread_attr);

    if (err != 0)
        return err;

    if (sem_init(&data->yield_sem, 0, 0) != 0)
        return err;

    data->foreign_stack_size = stack_size;
    data->guard_page_size = guard_size;
    data->mem_protect_size = MEM_PROTECT_SIZE;
    data->req_type = THREADREQ_NONE;
    data->signal_set = FALSE;

    data->guard_stack_size = 64*1024;

    if (data->guard_stack_size < MINSIGSTKSZ)
        data->guard_stack_size =
            (MINSIGSTKSZ + guard_size - 1) & ~(guard_size - 1);

    return 0;
}

int port_init_shared_data(volatile port_shared_data_t** p_psd)
{
    int err = 0;

    if (*p_psd)
        return 0;

    /* Serialize access to avoid multiple initialization */
    err = pthread_mutex_lock(&g_shared_data_mutex);

    if (err != 0)
        return err;

    /* If another thread had filled the pointer */
    if (*p_psd)
    {
        pthread_mutex_unlock(&g_shared_data_mutex);
        return 0;
    }

    /* If structure is already initialized */
    if (!g_port_shared_data)
    {
        err = init_psd_structure(&g_port_shared_data_struct);

        if (err == 0)
            g_port_shared_data = &g_port_shared_data_struct;
    }

    if (g_port_shared_data)
        *p_psd = g_port_shared_data;

    pthread_mutex_unlock(&g_shared_data_mutex);
    return err;
}


int init_port_shared_data()
{
    if (port_shared_data)
        return 0;

    return port_init_shared_data(&port_shared_data);
}
