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

#define  _GNU_SOURCE
#include <assert.h>
#include <sched.h>        // sched_param
#include <semaphore.h>
#include <signal.h>
#include <unistd.h>
#include <sys/mman.h>
#include <stdlib.h>

#include "port_malloc.h"
#include "port_thread.h"
#include "port_thread_internal.h"

// Linux/FreeBSD defines
#if defined(FREEBSD)
#define STACK_MMAP_ATTRS \
    (MAP_FIXED | MAP_PRIVATE | MAP_ANON | MAP_STACK)
#else
#ifdef _IPF_
#define STACK_MMAP_ATTRS \
    (MAP_PRIVATE | MAP_ANONYMOUS)
#else /* !_IPF_ */
#define STACK_MMAP_ATTRS \
    (MAP_FIXED | MAP_PRIVATE | MAP_ANONYMOUS | MAP_GROWSDOWN)
#endif /* _IPF_ */
#endif

#ifdef _IPF_
#define STACK_MAPPING_ACCESS (PROT_READ | PROT_WRITE)
#else /* !_IPF_ */
#define STACK_MAPPING_ACCESS (PROT_READ | PROT_WRITE | PROT_EXEC)
#endif /* _IPF_ */


/* Forward declarations */
static int suspend_init();
static int suspend_init_lock();
static port_thread_info_t* init_susres_list_item();
static port_thread_info_t* suspend_add_thread(osthread_t thread);
static void suspend_remove_thread(osthread_t thread);
static port_thread_info_t* suspend_find_thread(osthread_t thread);
static void sigusr2_handler(int signum, siginfo_t* info, void* context);


/**
 * Calculates absolute time in future for sem_timedwait timeout.
 * @param ptime The pointer to time structure to fill
 * @param delay Desired timeout in ns; not greater than 10^9 (1s)
 */
static inline __attribute__((always_inline))
void get_exceed_time(struct timespec* ptime, long delay)
{
    clock_gettime(CLOCK_REALTIME, ptime);

    ptime->tv_nsec += delay;
    if (ptime->tv_nsec >= 1000000000L) // overflow
    {
        ptime->tv_nsec -= 1000000000L;
        ++ptime->tv_sec;
    }
}

typedef void* (PORT_CDECL *pthread_func_t)(void*);

typedef struct
{
    port_threadfunc_t   fun;
    void*               arg;
    size_t              stack_size;
} thread_start_struct_t;

static PORT_CDECL int thread_start_func(void* arg)
{
    int err, result;
    port_tls_data_t* tlsdata = NULL;
    thread_start_struct_t* ptr = (thread_start_struct_t*)arg;
    port_threadfunc_t fun = ptr->fun;
    size_t stack_size = ptr->stack_size;
    arg = ptr->arg;
    STD_FREE(ptr);

    if (port_shared_data)
    {
        tlsdata = (port_tls_data_t*)STD_ALLOCA(sizeof(port_tls_data_t));
        err = port_thread_attach_local(tlsdata, FALSE, FALSE, stack_size);
        assert(err == 0);
    }

    result = fun(arg);

    if (tlsdata)
        port_thread_detach();

    return result;
}

int port_thread_create(/* out */osthread_t* phandle, size_t stacksize, int priority,
        port_threadfunc_t func, void *data)
{
    pthread_t thread;
    pthread_attr_t attr;
    pthread_attr_t attr_nosched;
    struct sched_param param;
    thread_start_struct_t* startstr;
    int res;

    if (!port_shared_data)
    {
        res = init_port_shared_data();
        /* assert(res); */
        /* It's OK to have an error here when Port shared library
           is not available yet; only signals/crash handling will
           not be available for the thread */
        /* return res; */
    }

    if (!func)
        return EINVAL;

    startstr =
        (thread_start_struct_t*)STD_MALLOC(sizeof(thread_start_struct_t));

    if (!startstr)
        return ENOMEM;

    pthread_attr_init(&attr);
    pthread_attr_init(&attr_nosched);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
    pthread_attr_setdetachstate(&attr_nosched, PTHREAD_CREATE_DETACHED);

    if (stacksize != 0)
    {
        if (stacksize < MINSIGSTKSZ)
            stacksize = MINSIGSTKSZ;

        if (port_shared_data)
        {
            size_t min_stacksize =
                /* Let's get alt stack size for normal stack and add guard page size */
                ((2*port_shared_data->guard_stack_size + port_shared_data->guard_page_size)
                /* Roung up to alt stack size */
                    + port_shared_data->guard_stack_size - 1) & ~(port_shared_data->guard_stack_size - 1);

            if (stacksize < min_stacksize)
                stacksize = min_stacksize;
        }

        res = pthread_attr_setstacksize(&attr, stacksize);
        if (res == 0)
            res = pthread_attr_setstacksize(&attr_nosched, stacksize);
        if (res)
        {
            pthread_attr_destroy(&attr);
            pthread_attr_destroy(&attr_nosched);
            STD_FREE(startstr);
            return res;
        }
    }

    if (priority)
    {
        res = pthread_attr_setschedpolicy(&attr, SCHED_FIFO);
        if (res == 0)
        {
            param.sched_priority = priority;
            res = pthread_attr_setschedparam(&attr, &param);
        }
        /* This does not work anyway on some Linuses
        if (res != 0)
        {
            pthread_attr_destroy(&attr);
            pthread_attr_destroy(&attr_nosched);
            STD_FREE(startstr);
            return res;
        }*/
    }

    startstr->fun = func;
    startstr->arg = data;
    startstr->stack_size = stacksize;

    res = pthread_create(&thread, &attr, (pthread_func_t)thread_start_func, startstr);

    if (res == EPERM) // EPERM relates to scheduling only
        res = pthread_create(&thread, &attr_nosched, (pthread_func_t)thread_start_func, startstr);

    pthread_attr_destroy(&attr);
    pthread_attr_destroy(&attr_nosched);

    if (res == 0)
    {
        *phandle = thread;
        return 0;
    }

    STD_FREE(startstr);
    return res;
}

int set_alt_stack(port_tls_data_t* tlsdata, boolean set)
{
    stack_t sigalt;

    // sets alternative stack
    sigalt.ss_sp = tlsdata->guard_stack_addr;
    sigalt.ss_size = tlsdata->guard_stack_size;
//#if defined(FREEBSD)
    sigalt.ss_flags = set ? 0 : SS_DISABLE;
//#else
//    sigalt.ss_flags = set ? SS_ONSTACK : SS_DISABLE;
//#endif
    if (sigaltstack(&sigalt, NULL) != 0)
        return errno;

    return 0;
}

static int set_guard_page(port_tls_data_t* tlsdata, boolean set)
{
    int res;

    if (!tlsdata)
        tlsdata = get_private_tls_data();

    if (!tlsdata)
        return -1;

    if (!tlsdata->guard_page_addr)
        return 0;

    if ((set && tlsdata->guard_page_set) ||
        (!set && !tlsdata->guard_page_set))
        return 0; // Already in needed state

    res = mprotect(tlsdata->guard_page_addr, tlsdata->guard_page_size,
                    set ? PROT_NONE : (PROT_READ | PROT_WRITE | PROT_EXEC));

    if (res != 0)
        return errno;

    if (set)
    {
        res = set_alt_stack(tlsdata, TRUE);

        if (res != 0)
            return res;
    }

    tlsdata->guard_page_set = set;
    return 0;
}

int port_thread_restore_guard_page()
{
    return set_guard_page(NULL, TRUE);
}

int port_thread_clear_guard_page()
{
    return set_guard_page(NULL, FALSE);
}

void port_thread_postpone_guard_page()
{
    port_tls_data_t* tlsdata = get_private_tls_data();

    if (!tlsdata || !tlsdata->guard_page_addr)
        return;

    tlsdata->restore_guard_page = FALSE;
}

void* port_thread_get_stack_address()
{
    port_tls_data_t* tlsdata = get_private_tls_data();
    return tlsdata ? tlsdata->stack_addr : NULL;
}

size_t port_thread_get_stack_size()
{
    port_tls_data_t* tlsdata = get_private_tls_data();
    return tlsdata ? tlsdata->stack_size : 0;
}

size_t port_thread_get_effective_stack_size()
{
    port_tls_data_t* tlsdata = get_private_tls_data();

    if (!tlsdata)
        return 0;

    if (!tlsdata->guard_page_addr || !tlsdata->guard_page_set)
        return tlsdata->stack_size
               - PSD->guard_page_size
               - PSD->mem_protect_size;

    return tlsdata->stack_size - 2*PSD->guard_page_size
           - PSD->guard_stack_size - PSD->mem_protect_size;
}

static int setup_stack(port_tls_data_t* tlsdata)
{
    int res;
    void* ptr;
    stack_t sigalt;
    size_t /*current_page_addr,*/ mapping_addr, mapping_size;

    if (!port_shared_data)
        return -1;

//    current_page_addr = ((size_t)&res) & ~(PSD->guard_page_size - 1);
    // leave place for mmap work
//    mapping_addr = current_page_addr - PSD->guard_page_size;
    // found size of the stack area which should be maped
//    mapping_size = tlsdata->stack_size
//            - ((size_t)tlsdata->stack_addr - mapping_addr);

    if ((size_t)(&res) - PSD->mem_protect_size
            < (size_t)tlsdata->guard_page_addr + tlsdata->guard_page_size)
        return EINVAL;

    // maps unmapped part of the stack
    mapping_addr = (size_t)tlsdata->stack_addr - tlsdata->stack_size;
    mapping_size =
        (tlsdata->guard_stack_size + tlsdata->mem_protect_size + 2*PSD->guard_page_size - 1) &
            ~(PSD->guard_page_size - 1);
    ptr = (char*)mmap((void*)mapping_addr, mapping_size,
            STACK_MAPPING_ACCESS, STACK_MMAP_ATTRS, -1, 0);

    if (ptr == MAP_FAILED)
        return errno;

    res = set_guard_page(tlsdata, TRUE);

    if (res != 0)
        return res;

    return 0;
}

inline int find_stack_addr_size(void** paddr, size_t* psize)
{
    int err;
    pthread_attr_t pthread_attr;
    void* stack_addr;
    size_t stack_size;
    pthread_t thread = pthread_self();

    if (!paddr) return EINVAL;

    err = pthread_attr_init(&pthread_attr);
    if (err != 0) return err;

#if defined(FREEBSD)
    err = pthread_attr_get_np(thread, &pthread_attr);
#else
    err = pthread_getattr_np(thread, &pthread_attr);
#endif
    if (err != 0) return err;

    err = pthread_attr_getstack(&pthread_attr, &stack_addr, &stack_size);
    if (err != 0) return err;

    pthread_attr_destroy(&pthread_attr);
    *paddr = (void*)((size_t)stack_addr + stack_size);
    *psize = stack_size;
    return 0;
}

static int init_stack(port_tls_data_t* tlsdata, size_t stack_size, boolean temp)
{
    int err;
    size_t stack_begin;

    if (!port_shared_data)
        return -1;

    err = find_stack_addr_size(&tlsdata->stack_addr, &tlsdata->stack_size);
    if (err != 0) {fprintf(stderr, "init_stack:CP#1\n");return err;}

    // Workaround for incorrect stack_size returned by pthread_attr_getstack
    // for main thread, while stack_addr + stack_size gives correct stack top
    if (tlsdata->foreign)
        tlsdata->stack_size = PSD->foreign_stack_size;

    if (stack_size != 0 && !tlsdata->foreign)
        tlsdata->stack_size = stack_size;

    tlsdata->guard_page_size = PSD->guard_page_size;
    tlsdata->guard_stack_size = PSD->guard_stack_size;
    tlsdata->mem_protect_size = PSD->mem_protect_size;

    if (temp)
        return 0;

    stack_begin = (size_t)tlsdata->stack_addr - tlsdata->stack_size;
    tlsdata->guard_stack_addr = (void*)(stack_begin + tlsdata->guard_page_size);
    tlsdata->guard_page_addr =
        (void*)((size_t)tlsdata->guard_stack_addr + tlsdata->guard_stack_size);

    return setup_stack(tlsdata);
}

int port_thread_attach_local(port_tls_data_t* tlsdata, boolean temp,
                                    boolean foreign, size_t stack_size)
{
    int res;

    memset(tlsdata, 0, sizeof(port_tls_data_t));

    tlsdata->foreign = foreign;
    res = init_stack(tlsdata, stack_size, temp);
    if (res != 0) return res;
/* They are already zeroed
    tlsdata->violation_flag = 0;
    tlsdata->debugger = FALSE;
    tlsdata->produce_core = FALSE;*/

    res = set_private_tls_data(tlsdata);

    if (res != 0)
        set_guard_page(tlsdata, FALSE);

    return res;
}

int port_thread_attach()
{
    int res;
    port_tls_data_t* tlsdata;

    if (!port_shared_data && (res = init_port_shared_data()) != 0)
        return res;

    if (get_private_tls_data())
        return 0;

    tlsdata = (port_tls_data_t*)STD_MALLOC(sizeof(port_tls_data_t));

    if (!tlsdata)
        return ENOMEM;

    res = port_thread_attach_local(tlsdata, FALSE, TRUE, 0);

    if (res != 0)
        STD_FREE(tlsdata);

    return res;
}

int port_thread_detach_temporary()
{
    port_tls_data_t* tlsdata = get_private_tls_data();

    if (!tlsdata || tlsdata->guard_page_addr)
        return -1;

    return set_private_tls_data(NULL);
}

int port_thread_detach()
{
    port_tls_data_t* tlsdata;
    int res;

    if (!port_shared_data && (res = init_port_shared_data()) != 0)
        return res;

    tlsdata = get_private_tls_data();

    if (!tlsdata)
        return 0;

    if (port_thread_detach_temporary() == 0)
        return 0;

    res = set_guard_page(tlsdata, FALSE);

    if (res != 0)
        return res;

    size_t mapping_addr = (size_t)tlsdata->stack_addr - tlsdata->stack_size;
    size_t mapping_size =
        (tlsdata->guard_stack_size + tlsdata->mem_protect_size + 2*PSD->guard_page_size - 1) &
            ~(PSD->guard_page_size - 1);
    munmap((void*)mapping_addr, mapping_size);

    if (tlsdata->foreign)
        STD_FREE(tlsdata);

    return set_private_tls_data(NULL);
}

int port_thread_set_priority(osthread_t os_thread, int priority)
{
#if defined(FREEBSD)
    /* Not sure why we don't just use this on linux? - MRH */
    struct sched_param param;
    int policy;
    int r = pthread_getschedparam(os_thread, &policy, &param);
    if (r == 0) {
        param.sched_priority = priority;
        r = pthread_setschedparam(os_thread, policy, &param);
    }
    return r;
#else
    // setting thread priority on linux is only supported for current thread
    if (os_thread == pthread_self()) {
        int r;
        struct sched_param param;
        pid_t self = gettid();
        param.sched_priority = priority;
        r = sched_setparam(self, &param);
        return r ? errno : 0;
    } else {
        // setting other thread priority not supported on linux
        return 0;
    }
#endif
}

osthread_t port_thread_current()
{
    return pthread_self();
}

int port_thread_free_handle(osthread_t os_thread)
{
    return 0;
}

int port_thread_join(osthread_t os_thread)
{
    int error;

    do {
        // FIXME - somehow pthread_join returns before thread is terminated
        error = pthread_join(os_thread, NULL);
    } while (error != ESRCH && error != EINVAL && error != EDEADLK);
    return 0;
}

int port_thread_cancel(osthread_t os_thread)
{
    int status;
    port_thread_info_t* pinfo;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(os_thread);

    if (os_thread == pthread_self())
    {
        if (pinfo && status == 0)
            suspend_remove_thread(os_thread);
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return pthread_cancel(os_thread);
    }

    status = pthread_cancel(os_thread);

    if (pinfo && status == 0)
        suspend_remove_thread(os_thread);

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return status;
}

void port_thread_exit(int status)
{
    pthread_exit((void*)(size_t)status);
}

int port_get_thread_times(osthread_t os_thread, int64* pkernel, int64* puser)
{
    clockid_t clock_id;
    struct timespec tp;
    int r;
#ifdef FREEBSD
    return EINVAL; /* TOFIX: Implement */
#else

    r = pthread_getcpuclockid(os_thread, &clock_id);
    if (r) return r;

    r = clock_gettime(clock_id, &tp);
    if (r) return r;

    *puser = tp.tv_sec * 1000000000ULL + tp.tv_nsec;
    return 0;
#endif
}

void port_thread_yield_other(osthread_t os_thread) {
    struct timespec timeout;
    port_thread_info_t* pinfo;
    int err = -1;

    if (!suspend_init_lock())
        return;

    pinfo = suspend_find_thread(os_thread);

    if (pinfo && pinfo->suspend_count > 0) {
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return;
    }

    PSD->req_type = THREADREQ_YIELD;

    assert(os_thread);
    if (pthread_kill(os_thread, SIGUSR2) == 0) {
        // signal sent, let's do timed wait to make sure the signal
        // was actually delivered
        get_exceed_time(&timeout, 10000000L);
        err = sem_timedwait(&PSD->yield_sem, &timeout);
//        sem_wait(&PSD->yield_sem);
    } else {
        if (pinfo)
            suspend_remove_thread(os_thread);
    }

    if (err != 0)
        PSD->req_type = THREADREQ_NONE;

    pthread_mutex_unlock(&PSD->suspend_mutex);
}


int port_thread_suspend(osthread_t thread)
{
    int status;
    port_thread_info_t* pinfo;

    if (!thread)
        return -1;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(thread);

    if (!pinfo)
        pinfo = suspend_add_thread(thread);

    if (!pinfo)
    {
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return -1;
    }

    if (pinfo->suspend_count > 0)
    {
        ++pinfo->suspend_count;
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return 0;
    }

    PSD->suspendee = thread;
    PSD->req_type = THREADREQ_SUS;

    if (pthread_kill(thread, SIGUSR2) != 0)
    {
        suspend_remove_thread(thread);
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return -1;
    }

    /* Waiting for suspendee response */
    sem_wait(&pinfo->wake_sem);
    /* Check result */
    status = (pinfo->suspend_count > 0) ? 0 : -1;

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return status;
}

int port_thread_resume(osthread_t thread)
{
    int status;
    port_thread_info_t* pinfo;

    if (!thread)
        return -1;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(thread);

    if (!pinfo)
    {
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return -1;
    }

    if (pinfo->suspend_count > 1)
    {
        --pinfo->suspend_count;
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return 0;
    }

    PSD->suspendee = thread;
    PSD->req_type = THREADREQ_RES;

    if ((status = pthread_kill(thread, SIGUSR2)) != 0)
    {
        suspend_remove_thread(thread);
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return status;
    }

    /* Waiting for resume notification */
    sem_wait(&pinfo->wake_sem);

    suspend_remove_thread(thread);

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return 0;
}

int port_thread_get_suspend_count(osthread_t thread)
{
    port_thread_info_t* pinfo;
    int suspend_count;

    if (!thread)
        return -1;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(thread);

    suspend_count = pinfo ? pinfo->suspend_count : 0;

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return suspend_count;
}

int port_thread_get_context(osthread_t thread, thread_context_t *context)
{
    int status = -1;
    port_thread_info_t* pinfo;

    if (!thread || !context)
        return -1;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(thread);

    if (!pinfo)
    {
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return status;
    }

    if (pinfo->suspend_count > 0)
    {
        *context = pinfo->context;
        status = -1;
    }

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return status;
}

int port_thread_set_context(osthread_t thread, thread_context_t *context)
{
    int status = -1;
    port_thread_info_t* pinfo;

    if (!thread || !context)
        return -1;

    if (!suspend_init_lock())
        return -1;

    pinfo = suspend_find_thread(thread);

    if (!pinfo)
    {
        pthread_mutex_unlock(&PSD->suspend_mutex);
        return status;
    }

    if (pinfo->suspend_count > 0)
    {
        pinfo->context = *context;
        status = 0;
    }

    pthread_mutex_unlock(&PSD->suspend_mutex);
    return status;
}


static int suspend_init()
{
    struct sigaction sa;
    int result = 1;

    if (port_shared_data && PSD->signal_set)
        return 1;

    if (!port_shared_data && init_port_shared_data() != 0)
        return 0;

    pthread_mutex_lock(&PSD->suspend_init_mutex);

    if (!PSD->signal_set)
    {
        /* set signal handler */
        sigemptyset(&sa.sa_mask);
        sa.sa_flags = SA_SIGINFO | SA_RESTART;
        sa.sa_sigaction = sigusr2_handler;

        if (sigaction(SIGUSR2, &sa, NULL) != 0)
            result = 0;
        else
            PSD->signal_set = TRUE;
    }

    pthread_mutex_unlock(&PSD->suspend_init_mutex);
    return result;
}

static int suspend_init_lock()
{
    if (!suspend_init())
        return 0;

    if (pthread_mutex_lock(&PSD->suspend_mutex) != 0)
        return 0;

    return 1;
}

static port_thread_info_t* init_susres_list_item()
{
    port_thread_info_t* pinfo =
        (port_thread_info_t*)malloc(sizeof(port_thread_info_t));

    if (pinfo == NULL)
        return NULL;

    pinfo->suspend_count = 0;

    int status = sem_init(&pinfo->wake_sem, 0, 0);

    if (status != 0)
    {
        free(pinfo);
        return NULL;
    }

    return pinfo;
}

static port_thread_info_t* suspend_add_thread(osthread_t thread)
{
    port_thread_info_t* pinfo = init_susres_list_item();

    if (pinfo == NULL)
        return NULL;

    pinfo->thread = thread;
    pinfo->next = PSD->suspended_list;
    PSD->suspended_list = pinfo;
    return pinfo;
}

static void suspend_remove_thread(osthread_t thread)
{
    port_thread_info_t** pprev = &PSD->suspended_list;
    port_thread_info_t* pinfo;

    for (pinfo = PSD->suspended_list; pinfo; pinfo = pinfo->next)
    {
        if (pinfo->thread == thread)
            break;

        pprev = &pinfo->next;
    }

    if (pinfo != NULL)
    {
        sem_destroy(&pinfo->wake_sem);
        *pprev = pinfo->next;
        free(pinfo);
    }
}

static port_thread_info_t* suspend_find_thread(osthread_t thread)
{
    port_thread_info_t* pinfo;
    int status;

    for (pinfo = PSD->suspended_list; pinfo; pinfo = pinfo->next)
    {
        if (pinfo->thread == thread)
            break;
    }

    return pinfo;
}


static void sigusr2_handler(int signum, siginfo_t* info, void* context)
{
    int status;
    port_thread_info_t* pinfo;

    if (!PSD)
        return;

    /* We have suspend_mutex locked already */

    if (PSD->req_type == THREADREQ_YIELD)
    {
        PSD->req_type = THREADREQ_NONE;
        /* Inform requester */
        sem_post(&PSD->yield_sem);
        return;
    }

    if (PSD->req_type == THREADREQ_NONE)
        return;

    if (!suspend_init() ||
        (pinfo = suspend_find_thread(PSD->suspendee)) == NULL)
    {
        return; /* Return to interrupted THREADREQ_SUS handler */
    }

    if (PSD->req_type == THREADREQ_SUS)
    {
        pinfo->suspend_count++;
        PSD->req_type = THREADREQ_NONE;
        memcpy(&pinfo->context, context, sizeof(ucontext_t));
        /* Inform suspender */
        sem_post(&pinfo->wake_sem);

        do
        {
            sigset_t sig_set;
            sigemptyset(&sig_set);
            sigsuspend(&sig_set);

        } while (pinfo->suspend_count > 0);

        /* We have returned from THREADREQ_RES handler */
        memcpy(context, &pinfo->context, sizeof(ucontext_t));
        /* Inform suspender */
        sem_post(&pinfo->wake_sem);
        return;
    }
    else if (PSD->req_type == THREADREQ_RES)
    {
        pinfo->suspend_count--;
        PSD->req_type = THREADREQ_NONE;
        return; /* Return to interrupted THREADREQ_SUS handler */
    }
}
