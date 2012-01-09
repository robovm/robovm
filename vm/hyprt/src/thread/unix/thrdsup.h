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

#if !defined(thrdsup_h)
#define thrdsup_h
#define HY_POSIX_THREADS
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <errno.h>
#include <setjmp.h>
#include "hycomp.h"

#if defined(LINUX) || defined(DARWIN)
#include <sys/time.h>
#endif

#include "hymutex.h"
/* ostypes */
typedef pthread_t OSTHREAD;
typedef pthread_key_t TLSKEY;
typedef pthread_cond_t COND;
#define WRAPPER_TYPE void*
typedef void *WRAPPER_ARG;
#define WRAPPER_RETURN() return NULL
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#include <semaphore.h>
typedef sem_t OSSEMAPHORE;
#else

typedef IDATA OSSEMAPHORE;

#endif
#include "thrtypes.h"
#include "priority.h"
#include "thrcreate.h"

int linux_pthread_cond_timedwait
PROTOTYPE ((pthread_cond_t * cond, pthread_mutex_t * mutex,
            const struct timespec * abstime));
IDATA VMCALL hythread_sigthreadmask_sigQuit PROTOTYPE ((void));
IDATA init_thread_library PROTOTYPE ((void));
IDATA VMCALL hythread_signalThread_sigQuit
PROTOTYPE ((hythread_t sigQuitThread));
IDATA VMCALL hythread_sigwait_sigQuit PROTOTYPE ((int *sig));
IDATA nto_cond_init PROTOTYPE ((pthread_cond_t * cond));
extern struct HyThreadLibrary default_library;
/* priority_map */
#if defined(HY_PRIORITY_MAP)
extern const int priority_map[];
#else
extern int priority_map[];
#endif

/* SETUP_TIMEOUT */

#define HYDIV_T div_t
#define HYDIV div
/* do we really need nanosecond clock accuracy even on platforms which support gettime? */

#if defined(LINUX) || defined(DARWIN)
#define SETUP_TIMEOUT(ts_, millis, nanos) {								\
		struct timeval tv_;											\
		HYDIV_T secs_ = HYDIV(millis, 1000);					\
		int nanos_ = secs_.rem * 1000000 + nanos;				\
		gettimeofday(&tv_, NULL);								\
		nanos_ += tv_.tv_usec * 1000;						\
		if (nanos_ >= 1000000000) {							\
			ts_.tv_sec = tv_.tv_sec + secs_.quot + 1;		\
			ts_.tv_nsec = nanos_ - 1000000000;			\
		} else {														\
			ts_.tv_sec = tv_.tv_sec + secs_.quot;			\
			ts_.tv_nsec = nanos_;								\
		} }
#elif defined(HYOSE)
#define SETUP_TIMEOUT(ts_, millis, nanos) { \
		struct TimePair tvp; \
		HYDIV_T secs_ = HYDIV(millis, 1000); \
		int nanos_ = secs_.rem * 1000000 + nanos; \
		rtc_get_time(&tvp); \
		nanos_ += tvp.micros * 1000; \
		if (nanos_ >= 1000000000) { \
			ts_.tv_sec = tvp.seconds + secs_.quot + 1; \
			ts_.tv_nsec = nanos_ - 1000000000; \
		} else { \
			ts_.tv_sec = tvp.seconds + secs_.quot; \
			ts_.tv_nsec = nanos_; \
		} }
#else
#define SETUP_TIMEOUT(ts_, millis, nanos) {								\
		HYDIV_T secs_ = HYDIV(millis, 1000);					\
		int nanos_ = secs_.rem * 1000000 + nanos;				\
 		clock_gettime(CLOCK_REALTIME, &ts_);		\
		nanos_ += ts_.tv_nsec;									\
		if (nanos_ >= 1000000000) {							\
			ts_.tv_sec += secs_.quot + 1;						\
			ts_.tv_nsec = nanos_ - 1000000000;			\
		} else {														\
			ts_.tv_sec += secs_.quot;							\
			ts_.tv_nsec = nanos_;								\
		} }
#endif

/* COND_DESTROY */
#define COND_DESTROY(cond) pthread_cond_destroy(&(cond))

/* TLS_GET */
#define TLS_GET(key) (pthread_getspecific(key))

/* TLS_ALLOC */
#define TLS_ALLOC(key) (pthread_key_create(&key, NULL))

/* TLS_SET */
#define TLS_SET(key, value) (pthread_setspecific(key, value))

/* COND_WAIT */
/* NOTE: the calling thread must already own mutex */
/* NOTE: a timeout less than zero indicates infinity */
#define COND_WAIT(cond, mutex) \
	do {	\
		pthread_cond_wait(&(cond), &(mutex))
#define COND_WAIT_LOOP()	} while(1)

/* THREAD_SELF */
#define THREAD_SELF() (pthread_self())

/* THREAD_YIELD */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define THREAD_YIELD() (sched_yield())
#endif

#if defined(LINUX) && defined(HARDHAT)
#undef THREAD_YIELD             /* undo the one defined above */
#define THREAD_YIELD() (usleep(0))
#endif

/* last chance. If it's not defined by now, use yield */
#if !defined(THREAD_YIELD)
#define THREAD_YIELD() (yield())
#endif

/* THREAD_CREATE */
#define THREAD_CREATE(thread, stacksize, priority, entrypoint, entryarg)	\
	(create_pthread(&(thread)->handle, (stacksize), priority_map[(priority)], (entrypoint), (entryarg)) == 0)

/* THREAD_CANCEL */
/* pthread_cancel is asynchronous. Use join to wait for it to complete */
#define THREAD_CANCEL(thread) (pthread_cancel(thread) || pthread_join(thread, NULL))

/* COND_NOTIFY_ALL */
#define COND_NOTIFY_ALL(cond) pthread_cond_broadcast(&(cond))

/* COND_WAIT_IF_TIMEDOUT */
/* NOTE: the calling thread must already own the mutex! */
#if defined(LINUX) && defined(HYX86)
#define PTHREAD_COND_TIMEDWAIT(x,y,z) linux_pthread_cond_timedwait(x,y,z)
#else
#define PTHREAD_COND_TIMEDWAIT(x,y,z) pthread_cond_timedwait(x,y,z)
#endif

#define COND_WAIT_RC_TIMEDOUT ETIMEDOUT

#define COND_WAIT_IF_TIMEDOUT(cond, mutex, millis, nanos) 											\
	do {																													\
		struct timespec ts_;																							\
		SETUP_TIMEOUT(ts_, millis, nanos);																						\
		while (1) {																										\
				if (PTHREAD_COND_TIMEDWAIT(&(cond), &(mutex), &ts_) == COND_WAIT_RC_TIMEDOUT)

#define COND_WAIT_TIMED_LOOP()		}	} while(0)

/* COND_INIT */
#define COND_INIT(cond) (pthread_cond_init(&(cond), NULL) == 0)

/* TLS_DESTROY */
#define TLS_DESTROY(key) (pthread_key_delete(key))

/* THREAD_EXIT */
#define THREAD_EXIT() pthread_exit(NULL)

/* THREAD_DETACH */
#define THREAD_DETACH(thread) pthread_detach(thread)

/* THREAD_SET_PRIORITY */
#define THREAD_SET_PRIORITY(thread, priority) set_pthread_priority((thread), (priority))

/* SEM_CREATE */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_CREATE(initValue) thread_malloc(NULL, sizeof(OSSEMAPHORE))
#else
#define SEM_CREATE(initValue)
#endif

/* SEM_INIT */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_INIT(sm, pshrd, inval)	(sem_init((sem_t*)sm, pshrd, inval))
#else
#define SEM_INIT(sm,pshrd,inval)
#endif

/* SEM_DESTROY */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_DESTROY(sm)	(sem_destroy((sem_t*)sm))
#else
#define SEM_DESTROY(sm)
#endif

/* SEM_FREE */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define	SEM_FREE(s)  	thread_free(NULL, (sem_t*)s);
#endif

/* SEM_POST */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_POST(smP)	(sem_post((sem_t*)smP))
#else
#define SEM_POST(sm)
#endif

/* SEM_WAIT */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_WAIT(smP)	(sem_wait((sem_t*)smP))
#else
#define SEM_WAIT(sm)
#endif

/* SEM_TRYWAIT */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_TRYWAIT(smP)	(sem_trywait(smP))
#else
#define SEM_TRYWAIT(sm)
#endif

/* SEM_GETVALUE */
#if defined(LINUX) || defined(FREEBSD) || defined(DARWIN)
#define SEM_GETVALUE(smP, intP)	(sem_getvalue(smP, intP))
#else
#define SEM_GETVALUE(sm)
#endif

#endif /* thrdsup_h */
