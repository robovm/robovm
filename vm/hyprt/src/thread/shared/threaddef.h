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

#if !defined(threaddef_h)
#define threaddef_h

#include <string.h>

#include "thrdsup.h"
#include "rasthrsup.h"
#include "hythread.h"
#undef hythread_monitor_init
#undef hythread_monitor_init_with_name
#include "thrtypes.h"
#include "hypool.h"

/*
   Define this to force a thread to be spawned when
   interrupting a waiting thread 
	 (it's a debug thing)
*/
#undef ALWAYS_SPAWN_THREAD_TO_INTERRUPT

/* You got to know what time it is */
typedef U_64 hytime_t;
typedef I_64 hytime_delta_t;
/* ASSERT and Debug */

#if !defined(STATIC_ASSERT)
#define STATIC_ASSERT(x) do { typedef int failed_assert[(x) ? 1 : -1]; } while(0)
#endif /* STATIC_ASSERT */

#if defined(THREAD_ASSERTS)

#define UNOWNED ((hythread_t)-1)
extern hythread_t global_lock_owner;
#undef NDEBUG
#include <assert.h>
#if !defined(ASSERT)
#define ASSERT(x) assert((x))
#endif /* !ASSERT */

#if !defined(ASSERT_DEBUG)
#define ASSERT_DEBUG(x) assert((x))
#endif

#else /* THREAD_ASSERTS */

#if !defined(ASSERT)
#define ASSERT(ignore) ((void)0)
#endif /* !ASSERT */

#if !defined(ASSERT_DEBUG)
#define ASSERT_DEBUG(ignore) ((void)0)
#endif /* !ASSERT_DEBUG */

#endif /* THREAD_ASSERTS */

#undef  DEBUG
#define DEBUG (0)

/* Helper defines for notify_thread() */
#define SET_NOTIFIED_FLAG (1)
#define DONT_SET_NOTIFIED_FLAG (0)

void hythread_monitor_pin
PROTOTYPE ((hythread_monitor_t monitor, hythread_t self));
void hythread_monitor_unpin
PROTOTYPE ((hythread_monitor_t monitor, hythread_t self));
void paint_stack PROTOTYPE ((hythread_t thread));

/*
 * constants for profiling
 */
#define MAX_CALLER_INDEX 63
enum
{
  CALLER_ATTACH = 0,
  CALLER_DESTROY,
  CALLER_SUSPEND,
  CALLER_RESUME,
  CALLER_CLEAR_INTERRUPTED,
  CALLER_THREAD_WRAPPER,
  CALLER_NOTIFY_ONE_OR_ALL,
  CALLER_SLEEP_INTERRUPTABLE,
  CALLER_TRY_ENTER_USING,
  CALLER_SLEEP,
  CALLER_EXIT_MONITOR,
  CALLER_DETACH,
  CALLER_CLEAR_PRIORITY_INTERRUPTED,
  CALLER_INTERNAL_EXIT1,
  CALLER_INTERNAL_EXIT2,
  CALLER_MONITOR_ENTER1,
  CALLER_MONITOR_ENTER2,
  CALLER_MONITOR_ENTER_THREE_TIER1,
  CALLER_MONITOR_ENTER_THREE_TIER2,
  CALLER_MONITOR_ENTER_THREE_TIER3,
  CALLER_MONITOR_EXIT1,
  CALLER_MONITOR_WAIT1,
  CALLER_MONITOR_WAIT2,
  CALLER_INTERRUPT_THREAD,
  CALLER_MONITOR_NUM_WAITING,
  CALLER_MONITOR_DESTROY,
  CALLER_GLOBAL_LOCK,
  CALLER_MONITOR_ACQUIRE,
  CALLER_INTERRUPT_SERVER,
  CALLER_RESET_TRACING,
  CALLER_LIB_SET_FLAGS,
  CALLER_LIB_CLEAR_FLAGS,
  CALLER_PARK,
  CALLER_UNPARK,
  CALLER_LAST_INDEX
};

/* helper defines for local functions */
#define WAIT_INTERRUPTABLE   (1)
#define WAIT_UNINTERRUPTABLE (0)
#define NOTIFY_ONE (0)
#define NOTIFY_ALL (1)
#define GLOBAL_NOT_LOCKED (0)
#define GLOBAL_IS_LOCKED  (1)

/* MACRO_SELF */
#define MACRO_SELF() ((hythread_t)TLS_GET( ((hythread_library_t)GLOBAL_DATA(default_library))->self_ptr))

/* GLOBAL_LOCK */
#if defined(THREAD_ASSERTS)
#define GLOBAL_LOCK(self,caller) { ASSERT(global_lock_owner != self); MUTEX_ENTER((self)->library->monitor_mutex); ASSERT(UNOWNED == global_lock_owner); global_lock_owner = self; }
#else
#define GLOBAL_LOCK(self, caller) MUTEX_ENTER(self->library->monitor_mutex)
#endif

/* GLOBAL_UNLOCK */
#if defined(THREAD_ASSERTS)
#define GLOBAL_UNLOCK(self) { ASSERT (self == global_lock_owner); global_lock_owner = UNOWNED; MUTEX_EXIT(self->library->monitor_mutex); }
#else
#define GLOBAL_UNLOCK(self) MUTEX_EXIT(self->library->monitor_mutex)
#endif

/*
 * GLOBAL_LOCK_SIMPLE
 * locking when you don't have a thread, just a lib
 */
#if defined(THREAD_ASSERTS)
#define GLOBAL_LOCK_SIMPLE(lib) { hythread_t self = MACRO_SELF(); ASSERT (self != global_lock_owner); MUTEX_ENTER(lib->monitor_mutex); ASSERT(UNOWNED == global_lock_owner); global_lock_owner = self; }
#else
#define GLOBAL_LOCK_SIMPLE(lib) MUTEX_ENTER(lib->monitor_mutex)
#endif

/*
 * GLOBAL_UNLOCK_SIMPLE
 * unlocking when you don't have a thread, just a lib
 */
#if defined(THREAD_ASSERTS)
#define GLOBAL_UNLOCK_SIMPLE(lib) { ASSERT (MACRO_SELF() == global_lock_owner); global_lock_owner = UNOWNED; MUTEX_EXIT(lib->monitor_mutex); }
#else
#define GLOBAL_UNLOCK_SIMPLE(lib) MUTEX_EXIT(lib->monitor_mutex)
#endif

/* THREAD_LOCK */
#define THREAD_LOCK(self, thread, caller) MUTEX_ENTER(thread->mutex)

/* THREAD_UNLOCK */
#define THREAD_UNLOCK(self, thread) MUTEX_EXIT(thread->mutex)

/* MONITOR_LOCK */
#define MONITOR_LOCK(self, monitor, caller) MUTEX_ENTER(monitor->mutex)

/* MONITOR_TRY_LOCK */
#if defined(FORCE_TO_USE_IS_THREAD)
/*
 * Force the use of the interruptServer (IS) thread by always failing
 * when trying to enter a monitor without blocking
 */
#define MONITOR_TRY_LOCK(monitor)  (-1)
#else
#define MONITOR_TRY_LOCK(monitor) (MUTEX_TRY_ENTER(monitor->mutex))
#endif

/* MONITOR_UNLOCK */
#define MONITOR_UNLOCK(self, monitor) MUTEX_EXIT(monitor->mutex)

/* IS_JLM_ENABLED */
#define IS_JLM_ENABLED(thread) ((thread)->library->flags & HYTHREAD_FLAG_JLM_ENABLED)

/* IS_JLM_TS_ENABLED */
#define IS_JLM_TS_ENABLED(thread) ((thread)->library->flags & HYTHREAD_FLAG_JLMTS_ENABLED)

/* IS_JLM_HST_ENABLED */
#define IS_JLM_HST_ENABLED(thread) ((thread)->library->flags & HYTHREAD_FLAG_JLMHST_ENABLED)

/* ACCUMULATE_SPIN_TIME */
#define ACCUMULATE_SPIN_TIME(spinTime, endSpinTime, startSpinTime, endPauseSpinTime, startPauseSpinTime) \
 \
	/* accumulate spin interval in monitor:                                                                     \
	     - again delta could be negative if CPU clocks not sync, and thread has moved to another processor      \
	     - also the spin start time will be zero if no spinning has occurred. This is a convention              \
	*/                                                                                                          \
	if ((startSpinTime) > 0) {                                                                                  \
		/* must be declared as a local variable in the C function from which this is called */                    \
		deltaTime = (TIME_DELTA) ((endSpinTime) - (startSpinTime));                                               \
	                                                                                                            \
		if (deltaTime > 0) {                                           													                  \
			(spinTime) += (TIME) deltaTime;                                                             \
			deltaTime = (TIME_DELTA) ((endPauseSpinTime) - (startPauseSpinTime));                       \
			if ((spinTime) > deltaTime) {                                                               \
				(spinTime) -= deltaTime; /* assumes gc can't run once spinning on try_enter starts */   \
			}                                                                                           \
		}

#endif /* threaddef_h */
