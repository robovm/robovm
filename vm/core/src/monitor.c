/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2012 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * RoboVM note: This code was ported from Android's dalvik/vm/Sync.cpp
 */

#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#include <sys/time.h>
#include <errno.h>
#include <assert.h>

#include <robovm.h>
#include "private.h"

#define LOG_TAG "core.monitor"

typedef uint32_t u4;
typedef int32_t s4;
typedef uint64_t u8;
typedef int64_t s8;

#if defined(RVM_X86_64) || defined(RVM_ARM64)
# define LW_TYPE uint64_t
#else
# define LW_TYPE uint32_t
#endif

#if defined(RVM_X86) || defined(RVM_X86_64)
static inline void android_atomic_release_store(LW_TYPE value, volatile LW_TYPE *ptr)
{
    __asm__ __volatile__ ("mfence" : : : "memory");
    *ptr = value;
}
static inline int android_atomic_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr) {
    return __sync_val_compare_and_swap(ptr, old_value, new_value) != old_value;
}
static inline int android_atomic_acquire_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr)
{
    /* Loads are not reordered with other loads. */
    return android_atomic_cas(old_value, new_value, ptr);
}
#elif defined(RVM_THUMBV7)
static inline void android_memory_barrier(void) {
    __asm__ __volatile__ ("dmb" : : : "memory");
}
static inline void android_atomic_release_store(LW_TYPE value, volatile LW_TYPE *ptr)
{
    android_memory_barrier();
    *ptr = value;
}
static inline int android_atomic_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr) {
    return __sync_val_compare_and_swap(ptr, old_value, new_value) != old_value;
}
static inline int android_atomic_acquire_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr) {
    int status = android_atomic_cas(old_value, new_value, ptr);
    android_memory_barrier();
    return status;
}
#else
static inline void android_memory_barrier(void) {
    __sync_synchronize();
}
static inline void android_atomic_release_store(LW_TYPE value, volatile LW_TYPE *ptr)
{
    android_memory_barrier();
    *ptr = value;
}
static inline int android_atomic_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr) {
    return __sync_val_compare_and_swap(ptr, old_value, new_value) != old_value;
}
static inline int android_atomic_acquire_cas(LW_TYPE old_value, LW_TYPE new_value, volatile LW_TYPE *ptr) {
    int status = android_atomic_cas(old_value, new_value, ptr);
    android_memory_barrier();
    return status;
}
#endif

/*
 * Every Object has a monitor associated with it, but not every Object is
 * actually locked.  Even the ones that are locked do not need a
 * full-fledged monitor until a) there is actual contention or b) wait()
 * is called on the Object.
 *
 * For Dalvik, we have implemented a scheme similar to the one described
 * in Bacon et al.'s "Thin locks: featherweight synchronization for Java"
 * (ACM 1998).  Things are even easier for us, though, because we have
 * a full 32 bits to work with.
 *
 * The two states of an Object's lock are referred to as "thin" and
 * "fat".  A lock may transition from the "thin" state to the "fat"
 * state and this transition is referred to as inflation.  Once a lock
 * has been inflated it remains in the "fat" state indefinitely.
 *
 * The lock value itself is stored in Object.lock.  The LSB of the
 * lock encodes its state.  When cleared, the lock is in the "thin"
 * state and its bits are formatted as follows:
 *
 *    [31 ---- 19] [18 ---- 3] [2 ---- 1] [0]
 *     lock count   thread id  hash state  0
 *
 * When set, the lock is in the "fat" state and its bits are formatted
 * as follows:
 *
 *    [31 ---- 3] [2 ---- 1] [0]
 *      pointer   hash state  1
 *
 * For an in-depth description of the mechanics of thin-vs-fat locking,
 * read the paper referred to above.
 */

/*
 * Stuff copied from dalvik/vm/Sync.h
 */
/*
 * Monitor shape field.  Used to distinguish immediate thin locks from
 * indirecting fat locks.
 */
#define LW_SHAPE_THIN 0
#define LW_SHAPE_FAT 1
#define LW_SHAPE_MASK 0x1
#define LW_SHAPE(x) ((x) & LW_SHAPE_MASK)

/*
 * Hash state field.  Used to signify that an object has had its
 * identity hash code exposed or relocated.
 */
#define LW_HASH_STATE_UNHASHED 0
#define LW_HASH_STATE_HASHED 1
#define LW_HASH_STATE_HASHED_AND_MOVED 3
#define LW_HASH_STATE_MASK 0x3
#define LW_HASH_STATE_SHIFT 1
#define LW_HASH_STATE(x) (((x) >> LW_HASH_STATE_SHIFT) & LW_HASH_STATE_MASK)

/*
 * Monitor accessor.  Extracts a monitor structure pointer from a fat
 * lock.  Performs no error checking.
 */
#define LW_MONITOR(x) \
  ((Monitor*)((x) & ~((LW_HASH_STATE_MASK << LW_HASH_STATE_SHIFT) | \
                      LW_SHAPE_MASK)))

/*
 * Lock owner field.  Contains the thread id of the thread currently
 * holding the lock.
 */
#define LW_LOCK_OWNER_MASK 0xffff
#define LW_LOCK_OWNER_SHIFT 3
#define LW_LOCK_OWNER(x) (((x) >> LW_LOCK_OWNER_SHIFT) & LW_LOCK_OWNER_MASK)

/*
 * Lock recursion count field.  Contains a count of the numer of times
 * a lock has been recursively acquired.
 */
#define LW_LOCK_COUNT_MASK 0x1fff
#define LW_LOCK_COUNT_SHIFT 19
#define LW_LOCK_COUNT(x) (((x) >> LW_LOCK_COUNT_SHIFT) & LW_LOCK_COUNT_MASK)

/*
 * Returns TRUE if the lock has been fattened.
 */
#define IS_LOCK_FAT(lock)   ((LW_SHAPE(*(lock)) == LW_SHAPE_FAT) ? TRUE : FALSE)

/*
 * Monitors provide:
 *  - mutually exclusive access to resources
 *  - a way for multiple threads to wait for notification
 *
 * In effect, they fill the role of both mutexes and condition variables.
 *
 * Only one thread can own the monitor at any time.  There may be several
 * threads waiting on it (the wait call unlocks it).  One or more waiting
 * threads may be getting interrupted or notified at any given time.
 *
 * TODO: the various members of monitor are not SMP-safe.
 */

static Monitor* threadSleepMonitor;
static void freeMonitorCleanupHandler(Env* env, Object* object);

jboolean rvmInitMonitors(Env* env) {
    threadSleepMonitor = rvmCreateMonitor(env, NULL);
    return TRUE;
}

/*
 * Create and initialize a monitor.
 */
Monitor* rvmCreateMonitor(Env* env, Object* obj) {
    Monitor* mon;

    mon = (Monitor*) rvmAllocateMemoryAtomicUncollectable(env, sizeof(Monitor));
    if (mon == NULL) {
        rvmAbort("Unable to allocate monitor");
    }
    if (((LW_TYPE)mon & 7) != 0) {
        rvmAbort("Misaligned monitor: %p", mon);
    }
    mon->obj = obj;
    rvmInitMutex(&mon->lock);

    if (obj) {
        registerCleanupHandler(env, obj, freeMonitorCleanupHandler);
    }

    return mon;
}

/*
 * Get the object that a monitor is part of.
 */
Object* rvmGetMonitorObject(Monitor* mon) {
    if (mon == NULL)
        return NULL;
    else
        return mon->obj;
}

/*
 * Returns the thread id of the thread owning the given lock.
 */
static u4 lockOwner(Object* obj) {
    Thread *owner;
    LW_TYPE lock;

    assert(obj != NULL);
    /*
     * Since we're reading the lock value multiple times, latch it so
     * that it doesn't change out from under us if we get preempted.
     */
    lock = obj->lock;
    if (LW_SHAPE(lock) == LW_SHAPE_THIN) {
        return LW_LOCK_OWNER(lock);
    } else {
        owner = LW_MONITOR(lock)->owner;
        return owner ? owner->threadId : 0;
    }
}

/*
 * Get the thread that holds the lock on the specified object.  The
 * object may be unlocked, thin-locked, or fat-locked.
 *
 * The caller must lock the thread list before calling here.
 */
Thread* rvmGetObjectLockHolder(Env* env, Object* obj) {
    u4 threadId = lockOwner(obj);

    if (threadId == 0)
        return NULL;
    return rvmGetThreadByThreadId(env, threadId);
}

/*
 * Checks whether the given thread holds the given
 * objects's lock.
 */
jboolean rvmHoldsLock(Env* env, Thread* thread, Object* obj) {
    if (thread == NULL || obj == NULL) {
        return FALSE;
    } else {
        return thread->threadId == lockOwner(obj) ? TRUE : FALSE;
    }
}

/*
 * Free the monitor associated with an object and make the object's lock
 * thin again.  This is called during garbage collection.
 */
static void freeMonitor(Monitor *mon) {
    assert(mon != NULL);
    assert(mon->obj != NULL);
    assert(LW_SHAPE(mon->obj->lock) == LW_SHAPE_FAT);

    /* This lock is associated with an object
     * that's being swept.  The only possible way
     * anyone could be holding this lock would be
     * if some JNI code locked but didn't unlock
     * the object, in which case we've got some bad
     * native code somewhere.
     */
    assert(rvmTryLockMutex(&mon->lock) == 0);
    assert(rvmUnlockMutex(&mon->lock) == 0);
    rvmDestroyMutex(&mon->lock);
}

static void freeMonitorCleanupHandler(Env* env, Object* object) {
    if (LW_SHAPE(object->lock) == LW_SHAPE_FAT) {
        Monitor* mon = LW_MONITOR(object->lock);
        freeMonitor(mon);
        object->lock = 0;
        rvmFreeMemoryUncollectable(env, mon);
    }
}

/*
 * Lock a monitor.
 */
static void lockMonitor(Env* env, Thread* self, Monitor* mon) {
    jint oldStatus;

    if (mon->owner == self) {
        mon->lockCount++;
        return;
    }
    if (rvmTryLockMutex(&mon->lock) != 0) {
        oldStatus = rvmChangeThreadStatus(env, self, THREAD_MONITOR);
        rvmLockMutex(&mon->lock);
        rvmChangeThreadStatus(env, self, oldStatus);
    }
    mon->owner = self;
    assert(mon->lockCount == 0);
}

/*
 * Unlock a monitor.
 *
 * Returns TRUE if the unlock succeeded.
 * If the unlock failed, an exception will be pending.
 */
static jboolean unlockMonitor(Env* env, Thread* self, Monitor* mon) {
    assert(self != NULL);
    assert(mon != NULL);
    if (mon->owner == self) {
        /*
         * We own the monitor, so nobody else can be in here.
         */
        if (mon->lockCount == 0) {
            mon->owner = NULL;
            rvmUnlockMutex(&mon->lock);
        } else {
            mon->lockCount--;
        }
    } else {
        /*
         * We don't own this, so we're not allowed to unlock it.
         * The JNI spec says that we should throw IllegalMonitorStateException
         * in this case.
         */
        rvmThrowIllegalMonitorStateException(env, "unlock of unowned monitor");
        return FALSE;
    }
    return TRUE;
}

/*
 * Links a thread into a monitor's wait set.  The monitor lock must be
 * held by the caller of this routine.
 */
static void waitSetAppend(Env* env, Monitor *mon, Thread *thread) {
    Thread *elt;

    assert(mon != NULL);
    assert(mon->owner == env->currentThread);
    assert(thread != NULL);
    assert(thread->waitNext == NULL);
    if (mon->waitSet == NULL) {
        mon->waitSet = thread;
        return;
    }
    elt = mon->waitSet;
    while (elt->waitNext != NULL) {
        elt = elt->waitNext;
    }
    elt->waitNext = thread;
}

/*
 * Unlinks a thread from a monitor's wait set.  The monitor lock must
 * be held by the caller of this routine.
 */
static void waitSetRemove(Env* env, Monitor *mon, Thread *thread) {
    Thread *elt;

    assert(mon != NULL);
    assert(mon->owner == env->currentThread);
    assert(thread != NULL);
    if (mon->waitSet == NULL) {
        return;
    }
    if (mon->waitSet == thread) {
        mon->waitSet = thread->waitNext;
        thread->waitNext = NULL;
        return;
    }
    elt = mon->waitSet;
    while (elt->waitNext != NULL) {
        if (elt->waitNext == thread) {
            elt->waitNext = thread->waitNext;
            thread->waitNext = NULL;
            return;
        }
        elt = elt->waitNext;
    }
}

/*
 * Converts the given relative waiting time into an absolute time.
 */
static void absoluteTime(jlong msec, jint nsec, struct timespec *ts) {
    jlong endSec;

#ifdef HAVE_TIMEDWAIT_MONOTONIC
    clock_gettime(CLOCK_MONOTONIC, ts);
#else
    {
        struct timeval tv;
        gettimeofday(&tv, NULL);
        ts->tv_sec = tv.tv_sec;
        ts->tv_nsec = tv.tv_usec * 1000;
    }
#endif
    endSec = ts->tv_sec + msec / 1000;
    if (endSec >= 0x7fffffff) {
        TRACE("NOTE: end time exceeds epoch");
        endSec = 0x7ffffffe;
    }
    ts->tv_sec = endSec;
    ts->tv_nsec = (ts->tv_nsec + (msec % 1000) * 1000000) + nsec;

    /* catch rollover */
    if (ts->tv_nsec >= 1000000000L) {
        ts->tv_sec++;
        ts->tv_nsec -= 1000000000L;
    }
}

/*
 * Wait on a monitor until timeout, interrupt, or notification.  Used for
 * Object.wait() and (somewhat indirectly) Thread.sleep() and Thread.join().
 *
 * If another thread calls Thread.interrupt(), we throw InterruptedException
 * and return immediately if one of the following are true:
 *  - blocked in wait(), wait(long), or wait(long, int) methods of Object
 *  - blocked in join(), join(long), or join(long, int) methods of Thread
 *  - blocked in sleep(long), or sleep(long, int) methods of Thread
 * Otherwise, we set the "interrupted" flag.
 *
 * Checks to make sure that "nsec" is in the range 0-999999
 * (i.e. fractions of a millisecond) and throws the appropriate
 * exception if it isn't.
 *
 * The spec allows "spurious wakeups", and recommends that all code using
 * Object.wait() do so in a loop.  This appears to derive from concerns
 * about pthread_cond_wait() on multiprocessor systems.  Some commentary
 * on the web casts doubt on whether these can/should occur.
 *
 * Since we're allowed to wake up "early", we clamp extremely long durations
 * to return at the end of the 32-bit time epoch.
 */
static void waitMonitor(Env* env, Thread* self, Monitor* mon, jlong msec, jint nsec,
    jboolean interruptShouldThrow) {
    struct timespec ts;
    jboolean wasInterrupted = FALSE;
    jboolean timed;
    int ret;

    assert(self != NULL);
    assert(mon != NULL);

    /* Make sure that we hold the lock. */
    if (mon->owner != self) {
        rvmThrowIllegalMonitorStateException(env, 
            "object not locked by thread before wait()");
        return;
    }

    /*
     * Enforce the timeout range.
     */
    if (msec < 0 || nsec < 0 || nsec > 999999) {
        rvmThrowIllegalArgumentException(env, "timeout arguments out of range");
        return;
    }

    /*
     * Compute absolute wakeup time, if necessary.
     */
    if (msec == 0 && nsec == 0) {
        timed = FALSE;
    } else {
        absoluteTime(msec, nsec, &ts);
        timed = TRUE;
    }

    /*
     * Add ourselves to the set of threads waiting on this monitor, and
     * release our hold.  We need to let it go even if we're a few levels
     * deep in a recursive lock, and we need to restore that later.
     *
     * We append to the wait set ahead of clearing the count and owner
     * fields so the subroutine can check that the calling thread owns
     * the monitor.  Aside from that, the order of member updates is
     * not order sensitive as we hold the pthread mutex.
     */
    waitSetAppend(env, mon, self);
    int prevLockCount = mon->lockCount;
    mon->lockCount = 0;
    mon->owner = NULL;

    /*
     * Update thread status.  If the GC wakes up, it'll ignore us, knowing
     * that we won't touch any references in this state, and we'll check
     * our suspend mode before we transition out.
     */
    if (timed)
        rvmChangeThreadStatus(env, self, THREAD_TIMED_WAIT);
    else
        rvmChangeThreadStatus(env, self, THREAD_WAIT);

    rvmLockMutex(&self->waitMutex);

    /*
     * Set waitMonitor to the monitor object we will be waiting on.
     * When waitMonitor is non-NULL a notifying or interrupting thread
     * must signal the thread's waitCond to wake it up.
     */
    assert(self->waitMonitor == NULL);
    self->waitMonitor = mon;

    /*
     * Handle the case where the thread was interrupted before we called
     * wait().
     */
    if (self->interrupted) {
        wasInterrupted = TRUE;
        self->waitMonitor = NULL;
        rvmUnlockMutex(&self->waitMutex);
        goto done;
    }

    /*
     * Release the monitor lock and wait for a notification or
     * a timeout to occur.
     */
    rvmUnlockMutex(&mon->lock);

    /*
     * NOTE: According to POSIX pthread_cond_wait() and
     * pthread_cond_timedwait() must never return EINTR. The OS X man page
     * doesn't mention EINTR at all and some old Linux man pages specifically
     * say that EINTR *can* be returned. So we do the safe thing and allow
     * EINTR.
     */

    if (!timed) {
        do {
            ret = pthread_cond_wait(&self->waitCond, &self->waitMutex);
        } while (!self->interrupted && self->waitMonitor != NULL && (ret == 0 || ret == EINTR));
        assert(ret == 0);
    } else {
        do {
#ifdef HAVE_TIMEDWAIT_MONOTONIC
            ret = pthread_cond_timedwait_monotonic(&self->waitCond, &self->waitMutex, &ts);
#else
            ret = pthread_cond_timedwait(&self->waitCond, &self->waitMutex, &ts);
#endif
        } while (!self->interrupted && self->waitMonitor != NULL && ret != ETIMEDOUT && (ret == 0 || ret == EINTR));
        assert(ret == 0 || ret == ETIMEDOUT);
    }
    if (self->interrupted) {
        wasInterrupted = TRUE;
    }

    self->interrupted = FALSE;
    self->waitMonitor = NULL;

    rvmUnlockMutex(&self->waitMutex);

    /* Reacquire the monitor lock. */
    lockMonitor(env, self, mon);

done:
    /*
     * We remove our thread from wait set after restoring the count
     * and owner fields so the subroutine can check that the calling
     * thread owns the monitor. Aside from that, the order of member
     * updates is not order sensitive as we hold the pthread mutex.
     */
    mon->owner = self;
    mon->lockCount = prevLockCount;
    waitSetRemove(env, mon, self);

    /* set self->status back to THREAD_RUNNING, and self-suspend if needed */
    rvmChangeThreadStatus(env, self, THREAD_RUNNING);

    if (wasInterrupted) {
        /*
         * We were interrupted while waiting, or somebody interrupted an
         * un-interruptible thread earlier and we're bailing out immediately.
         *
         * The doc sayeth: "The interrupted status of the current thread is
         * cleared when this exception is thrown."
         */
        self->interrupted = FALSE;
        if (interruptShouldThrow) {
            rvmThrowInterruptedException(env);
        }
    }
}

/*
 * Notify one thread waiting on this monitor.
 */
static void notifyMonitor(Env* env, Thread* self, Monitor* mon) {
    Thread* thread;

    assert(self != NULL);
    assert(mon != NULL);

    /* Make sure that we hold the lock. */
    if (mon->owner != self) {
        rvmThrowIllegalMonitorStateException(env, 
            "object not locked by thread before notify()");
        return;
    }
    /* Signal the first waiting thread in the wait set. */
    while (mon->waitSet != NULL) {
        thread = mon->waitSet;
        mon->waitSet = thread->waitNext;
        thread->waitNext = NULL;
        rvmLockMutex(&thread->waitMutex);
        /* Check to see if the thread is still waiting. */
        if (thread->waitMonitor != NULL) {
            thread->waitMonitor = NULL; /* Makes the thread exit its wait loop */
            pthread_cond_signal(&thread->waitCond);
            rvmUnlockMutex(&thread->waitMutex);
            return;
        }
        rvmUnlockMutex(&thread->waitMutex);
    }
}

/*
 * Notify all threads waiting on this monitor.
 */
static void notifyAllMonitor(Env* env, Thread* self, Monitor* mon) {
    Thread* thread;

    assert(self != NULL);
    assert(mon != NULL);

    /* Make sure that we hold the lock. */
    if (mon->owner != self) {
        rvmThrowIllegalMonitorStateException(env, 
            "object not locked by thread before notifyAll()");
        return;
    }
    /* Signal all threads in the wait set. */
    while (mon->waitSet != NULL) {
        thread = mon->waitSet;
        mon->waitSet = thread->waitNext;
        thread->waitNext = NULL;
        rvmLockMutex(&thread->waitMutex);
        /* Check to see if the thread is still waiting. */
        if (thread->waitMonitor != NULL) {
            thread->waitMonitor = NULL; /* Makes the thread exit its wait loop */
            pthread_cond_signal(&thread->waitCond);
        }
        rvmUnlockMutex(&thread->waitMutex);
    }
}

/*
 * Changes the shape of a monitor from thin to fat, preserving the
 * internal lock state.  The calling thread must own the lock.
 */
static void inflateMonitor(Env* env, Thread *self, Object* obj) {
    Monitor *mon;
    LW_TYPE thin;

    assert(self != NULL);
    assert(obj != NULL);
    assert(LW_SHAPE(obj->lock) == LW_SHAPE_THIN);
    assert(LW_LOCK_OWNER(obj->lock) == self->threadId);
    /* Allocate and acquire a new monitor. */
    mon = rvmCreateMonitor(env, obj);
    lockMonitor(env, self, mon);
    /* Propagate the lock state. */
    thin = obj->lock;
    mon->lockCount = LW_LOCK_COUNT(thin);
    thin &= LW_HASH_STATE_MASK << LW_HASH_STATE_SHIFT;
    thin |= (LW_TYPE)mon | LW_SHAPE_FAT;
    /* Publish the updated lock word. */
    android_atomic_release_store(thin, (LW_TYPE *)&obj->lock);
}

/*
 * Implements monitorenter for "synchronized" stuff.
 *
 * This does not fail or throw an exception (unless deadlock prediction
 * is enabled and set to "err" mode).
 */
void rvmLockObject(Env* env, Object* obj) {
    Thread* self = env->currentThread;
    volatile LW_TYPE *thinp;
    jint oldStatus;
    struct timespec tm;
    long sleepDelayNs;
    long minSleepDelayNs = 1000000;  /* 1 millisecond */
    long maxSleepDelayNs = 1000000000;  /* 1 second */
    LW_TYPE thin, newThin;
    u4 threadId;

    assert(self != NULL);
    assert(obj != NULL);
    threadId = self->threadId;
    thinp = &obj->lock;
retry:
    thin = *thinp;
    if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
        /*
         * The lock is a thin lock.  The owner field is used to
         * determine the acquire method, ordered by cost.
         */
        if (LW_LOCK_OWNER(thin) == threadId) {
            /*
             * The calling thread owns the lock.  Increment the
             * value of the recursion count field.
             */
            obj->lock += 1 << LW_LOCK_COUNT_SHIFT;
            if (LW_LOCK_COUNT(obj->lock) == LW_LOCK_COUNT_MASK) {
                /*
                 * The reacquisition limit has been reached.  Inflate
                 * the lock so the next acquire will not overflow the
                 * recursion count field.
                 */
                inflateMonitor(env, self, obj);
            }
        } else if (LW_LOCK_OWNER(thin) == 0) {
            /*
             * The lock is unowned.  Install the thread id of the
             * calling thread into the owner field.  This is the
             * common case.  In performance critical code the JIT
             * will have tried this before calling out to the VM.
             */
            newThin = thin | (threadId << LW_LOCK_OWNER_SHIFT);
            if (android_atomic_acquire_cas(thin, newThin,
                    (LW_TYPE*)thinp) != 0) {
                /*
                 * The acquire failed.  Try again.
                 */
                goto retry;
            }
        } else {
            TRACEF("(%d) spin on lock %p: %#x (%#x) %#x",
                 threadId, &obj->lock, 0, *thinp, thin);
            /*
             * The lock is owned by another thread.  Notify the VM
             * that we are about to wait.
             */
            oldStatus = rvmChangeThreadStatus(env, self, THREAD_MONITOR);
            /*
             * Spin until the thin lock is released or inflated.
             */
            sleepDelayNs = 0;
            for (;;) {
                thin = *thinp;
                /*
                 * Check the shape of the lock word.  Another thread
                 * may have inflated the lock while we were waiting.
                 */
                if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
                    if (LW_LOCK_OWNER(thin) == 0) {
                        /*
                         * The lock has been released.  Install the
                         * thread id of the calling thread into the
                         * owner field.
                         */
                        newThin = thin | (threadId << LW_LOCK_OWNER_SHIFT);
                        if (android_atomic_acquire_cas(thin, newThin,
                                (LW_TYPE *)thinp) == 0) {
                            /*
                             * The acquire succeed.  Break out of the
                             * loop and proceed to inflate the lock.
                             */
                            break;
                        }
                    } else {
                        /*
                         * The lock has not been released.  Yield so
                         * the owning thread can run.
                         */
                        if (sleepDelayNs == 0) {
                            sched_yield();
                            sleepDelayNs = minSleepDelayNs;
                        } else {
                            tm.tv_sec = 0;
                            tm.tv_nsec = sleepDelayNs;
                            nanosleep(&tm, NULL);
                            /*
                             * Prepare the next delay value.  Wrap to
                             * avoid once a second polls for eternity.
                             */
                            if (sleepDelayNs < maxSleepDelayNs / 2) {
                                sleepDelayNs *= 2;
                            } else {
                                sleepDelayNs = minSleepDelayNs;
                            }
                        }
                    }
                } else {
                    /*
                     * The thin lock was inflated by another thread.
                     * Let the VM know we are no longer waiting and
                     * try again.
                     */
                    TRACEF("(%d) lock %p surprise-fattened",
                             threadId, &obj->lock);
                    rvmChangeThreadStatus(env, self, oldStatus);
                    goto retry;
                }
            }
            TRACEF("(%d) spin on lock done %p: %#x (%#x) %#x",
                 threadId, &obj->lock, 0, *thinp, thin);
            /*
             * We have acquired the thin lock.  Let the VM know that
             * we are no longer waiting.
             */
            rvmChangeThreadStatus(env, self, oldStatus);
            /*
             * Fatten the lock.
             */
            inflateMonitor(env, self, obj);
            TRACEF("(%d) lock %p fattened", threadId, &obj->lock);
        }
    } else {
        /*
         * The lock is a fat lock.
         */
        assert(LW_MONITOR(obj->lock) != NULL);
        lockMonitor(env, self, LW_MONITOR(obj->lock));
    }
}

/*
 * Implements monitorexit for "synchronized" stuff.
 *
 * On failure, throws an exception and returns "FALSE".
 */
jboolean rvmUnlockObject(Env* env, Object* obj) {
    Thread* self = env->currentThread;
    LW_TYPE thin;

    assert(self != NULL);
    assert(self->status == THREAD_RUNNING);
    assert(obj != NULL);
    /*
     * Cache the lock word as its value can change while we are
     * examining its state.
     */
    thin = *(volatile LW_TYPE *)&obj->lock;
    if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
        /*
         * The lock is thin.  We must ensure that the lock is owned
         * by the given thread before unlocking it.
         */
        if (LW_LOCK_OWNER(thin) == self->threadId) {
            /*
             * We are the lock owner.  It is safe to update the lock
             * without CAS as lock ownership guards the lock itself.
             */
            if (LW_LOCK_COUNT(thin) == 0) {
                /*
                 * The lock was not recursively acquired, the common
                 * case.  Unlock by clearing all bits except for the
                 * hash state.
                 */
                thin &= (LW_HASH_STATE_MASK << LW_HASH_STATE_SHIFT);
                android_atomic_release_store(thin, (LW_TYPE*)&obj->lock);
            } else {
                /*
                 * The object was recursively acquired.  Decrement the
                 * lock recursion count field.
                 */
                obj->lock -= 1 << LW_LOCK_COUNT_SHIFT;
            }
        } else {
            /*
             * We do not own the lock.  The JVM spec requires that we
             * throw an exception in this case.
             */
            rvmThrowIllegalMonitorStateException(env, "unlock of unowned monitor");
            return FALSE;
        }
    } else {
        /*
         * The lock is fat.  We must check to see if unlockMonitor has
         * raised any exceptions before continuing.
         */
        assert(LW_MONITOR(obj->lock) != NULL);
        if (!unlockMonitor(env, self, LW_MONITOR(obj->lock))) {
            /*
             * An exception has been raised.  Do not fall through.
             */
            return FALSE;
        }
    }
    return TRUE;
}

/*
 * Object.wait().  Also called for class init.
 */
void rvmObjectWait(Env* env, Object* obj, jlong msec, jint nsec, jboolean interruptShouldThrow) {
    Thread* self = env->currentThread;
    Monitor* mon;
    LW_TYPE thin = *(volatile LW_TYPE *)&obj->lock;

    /* If the lock is still thin, we need to fatten it.
     */
    if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
        /* Make sure that 'self' holds the lock.
         */
        if (LW_LOCK_OWNER(thin) != self->threadId) {
            rvmThrowIllegalMonitorStateException(env, 
                "object not locked by thread before wait()");
            return;
        }

        /* This thread holds the lock.  We need to fatten the lock
         * so 'self' can block on it.  Don't update the object lock
         * field yet, because 'self' needs to acquire the lock before
         * any other thread gets a chance.
         */
        inflateMonitor(env, self, obj);
        TRACEF("(%d) lock %p fattened by wait()", self->threadId, &obj->lock);
    }
    mon = LW_MONITOR(obj->lock);
    waitMonitor(env, self, mon, msec, nsec, interruptShouldThrow);
}

/*
 * Object.notify().
 */
void rvmObjectNotify(Env* env, Object* obj) {
    Thread* self = env->currentThread;
    LW_TYPE thin = *(volatile LW_TYPE *)&obj->lock;

    /* If the lock is still thin, there aren't any waiters;
     * waiting on an object forces lock fattening.
     */
    if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
        /* Make sure that 'self' holds the lock.
         */
        if (LW_LOCK_OWNER(thin) != self->threadId) {
            rvmThrowIllegalMonitorStateException(env, 
                "object not locked by thread before notify()");
            return;
        }

        /* no-op;  there are no waiters to notify.
         */
    } else {
        /* It's a fat lock.
         */
        notifyMonitor(env, self, LW_MONITOR(thin));
    }
}

/*
 * Object.notifyAll().
 */
void rvmObjectNotifyAll(Env* env, Object* obj) {
    Thread* self = env->currentThread;
    LW_TYPE thin = *(volatile LW_TYPE *)&obj->lock;

    /* If the lock is still thin, there aren't any waiters;
     * waiting on an object forces lock fattening.
     */
    if (LW_SHAPE(thin) == LW_SHAPE_THIN) {
        /* Make sure that 'self' holds the lock.
         */
        if (LW_LOCK_OWNER(thin) != self->threadId) {
            rvmThrowIllegalMonitorStateException(env, 
                "object not locked by thread before notifyAll()");
            return;
        }

        /* no-op;  there are no waiters to notify.
         */
    } else {
        /* It's a fat lock.
         */
        notifyAllMonitor(env, self, LW_MONITOR(thin));
    }
}

/*
 * This implements java.lang.Thread.sleep(long msec, int nsec).
 *
 * The sleep is interruptible by other threads, which means we can't just
 * plop into an OS sleep call.  (We probably could if we wanted to send
 * signals around and rely on EINTR, but that's inefficient and relies
 * on native code respecting our signal mask.)
 *
 * We have to do all of this stuff for Object.wait() as well, so it's
 * easiest to just sleep on a private Monitor.
 *
 * It appears that we want sleep(0,0) to go through the motions of sleeping
 * for a very short duration, rather than just returning.
 */
void rvmThreadSleep(Env* env, jlong msec, jint nsec) {
    Thread* self = env->currentThread;
    Monitor* mon = threadSleepMonitor;

    /* sleep(0,0) wakes up immediately, wait(0,0) means wait forever; adjust */
    if (msec == 0 && nsec == 0)
        nsec++;

    lockMonitor(env, self, mon);
    waitMonitor(env, self, mon, msec, nsec, TRUE);
    unlockMonitor(env, self, mon);
}

/*
 * Implement java.lang.Thread.interrupt().
 */
void rvmThreadInterrupt(Env* env, Thread* thread) {
    assert(thread != NULL);

    rvmLockMutex(&thread->waitMutex);

    /*
     * If the interrupted flag is already set no additional action is
     * required.
     */
    if (thread->interrupted == TRUE) {
        rvmUnlockMutex(&thread->waitMutex);
        return;
    }

    /*
     * Raise the "interrupted" flag.  This will cause it to bail early out
     * of the next wait() attempt, if it's not currently waiting on
     * something.
     */
    thread->interrupted = TRUE;

    /*
     * Is the thread waiting?
     *
     * Note that fat vs. thin doesn't matter here;  waitMonitor
     * is only set when a thread actually waits on a monitor,
     * which implies that the monitor has already been fattened.
     */
    if (thread->waitMonitor != NULL) {
        thread->waitMonitor = NULL; /* Makes the thread exit its wait loop */
        pthread_cond_signal(&thread->waitCond);
    }

    rvmUnlockMutex(&thread->waitMutex);
}
