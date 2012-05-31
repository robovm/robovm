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
 * @file thread_native_thin_monitor.c
 * @brief Hythread thin monitors related functions
 */

#undef LOG_DOMAIN
#define LOG_DOMAIN "tm.locks"

#include <open/hythread_ext.h>
#include <apr_atomic.h>
#include <port_atomic.h>
#include "port_barriers.h"
#include "port_thread.h"
#include "port_mutex.h"
#include "thread_private.h"

/** @name Thin monitors support. Implement thin-fat scheme.
 */
//@{

#if !defined (_IPF_)
// spin with try_lock SPIN_COUNT times
#define SPIN_COUNT 5
#endif // !defined (_IPF_)

/*
 * 32bit lock word
 *           |0|------15bit-------|--5bit--|1bit|--10bit------|
 * thin lock -^  threadID (owner)  recursion  ^  hashcode  
 *                                            |
 *                               reservation bit (0 - reserved)
 * inflated lock:
 *           |1|------------- 20bit -------|----11bit-----|
 *  fat lock -^            fat lock id
 */

// lockword operations
#define THREAD_ID(lockword) (lockword >> 16)
#define IS_FAT_LOCK(lockword) (lockword >> 31)
#define FAT_LOCK_ID(lockword) \
    ((lockword >> HY_FAT_LOCK_ID_OFFSET) & HY_FAT_LOCK_ID_MASK)
// lock reservation support
#define RESERVED_BITMASK ((1<<10))
#define IS_RESERVED(lockword) (0==(lockword & RESERVED_BITMASK))

#define RECURSION(lockword) ((lockword >> 11) & 31)
#define RECURSION_INC(lockword_ptr, lockword) (*lockword_ptr= lockword + (1<<11))
#define RECURSION_DEC(lockword_ptr, lockword) (*lockword_ptr=lockword - (1<<11))
#define MAX_RECURSION 31

#define FAT_LOCK(_x_) \
    lock_table->tables[((U_32)(_x_))/HY_FAT_TABLE_ENTRIES]\
                      [((U_32)(_x_))%HY_FAT_TABLE_ENTRIES]

tm_props *tm_properties = NULL;

/*
 * Lock table which holds the omapping between LockID and fat lock (OS fat_monitor) pointer.
 */

HyFatLockTable *lock_table = NULL;

IDATA VMCALL hythread_owns_thin_lock(hythread_t thread, hythread_thin_monitor_t lockword) {
    IDATA this_id = thread->thread_id;
    assert(!IS_FAT_LOCK(lockword));
#ifdef LOCK_RESERVATION
    return THREAD_ID(lockword) == this_id
        && (!IS_RESERVED(lockword) || RECURSION(lockword) !=0);
#else
    return THREAD_ID(lockword) == this_id;
#endif
}

void set_fat_lock_id(hythread_thin_monitor_t *lockword_ptr, IDATA monitor_id) {
    U_32 lockword = *lockword_ptr;
#ifdef LOCK_RESERVATION
    assert(!IS_RESERVED(lockword));
#endif
    assert((U_32)monitor_id < lock_table->size);
    lockword&=0x7FF;
    lockword|=(monitor_id << 11) | 0x80000000;
    *lockword_ptr=lockword;
    port_rw_barrier();
}

IDATA get_fat_lock_id(hythread_thin_monitor_t *lockword_ptr) {
    // this method steals the bit mask from set_fat_lock_id above
    // get_fat_lock() and set_fat_lock need cleaning up
    // the bit masks should be replaced with "#define ..."
    U_32 lockword = *lockword_ptr;
    assert(lockword & 0x80000000);  //fat lock bit better be set
    lockword &= 0x7fFFffFF;  // throw away the fat lock bit
    lockword = lockword >> 11;
    assert(lockword < lock_table->size);
    return lockword;
}

int VMCALL hythread_is_fat_lock(hythread_thin_monitor_t lockword) {
    return (int)IS_FAT_LOCK(lockword);
}

//forward declaration
hythread_monitor_t locktable_get_fat_monitor(IDATA lock_id);
IDATA locktable_put_fat_monitor(hythread_monitor_t fat_monitor);
hythread_monitor_t locktable_delete_entry(int lock_id);

//DEBUG INFO BLOCK
//char *vm_get_object_class_name(void* ptr);
int unreserve_count=0;
int inflate_contended=0;
int inflate_waited=0;
int unreserve_count_self=0;
int fat_lock2_count = 0;
int init_reserve_cout = 0;
int cas_cout = 0;
int res_lock_count = 0;

#ifdef LOCK_RESERVATION

extern osmutex_t TM_LOCK;
/*
 * Unreserves the lock already owned by this thread
 */
void unreserve_self_lock(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword = *lockword_ptr;
    U_32 lockword_new;
    CTRACE(("unreserve self_id %d lock owner %d", hythread_get_self_id(), THREAD_ID(lockword)));
    assert(hythread_get_self_id() == THREAD_ID(lockword));
    assert (!IS_FAT_LOCK(*lockword_ptr));
    assert (IS_RESERVED(lockword));
    CTRACE(("Unreserved self %d \n", ++unreserve_count_self/*, vm_get_object_class_name(lockword_ptr-1)*/));  
       
    // Set reservation bit to 1 and reduce recursion count
    lockword_new = (lockword | RESERVED_BITMASK);
    if (RECURSION(lockword_new) != 0) {
        RECURSION_DEC(lockword_ptr, lockword_new);
    } else {
        lockword_new  = lockword_new & 0x0000ffff;
        *lockword_ptr = lockword_new;
    }
    assert(!IS_RESERVED(*lockword_ptr));
    CTRACE(("unreserved self"));
}

/**
 * Used lockword
 * Thin monitor functions used java monitor.
 */
IDATA VMCALL hythread_unreserve_lock(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword = *lockword_ptr;
    U_32 lockword_new;
    uint16 lock_id;
    hythread_t owner;
    IDATA status;
    I_32 append;

    // trylock used to prevent cyclic suspend deadlock
    // the java_monitor_enter calls safe_point between attempts.
    /*status = port_mutex_trylock(&TM_LOCK);
      if (status !=TM_ERROR_NONE) {
      return status;
      }*/
    
    if (IS_FAT_LOCK(lockword)) {
        return TM_ERROR_NONE;
    }
    lock_id = THREAD_ID(lockword);
    owner = hythread_get_thread(lock_id);
    CTRACE(("Unreserved other %d \n", ++unreserve_count/*, vm_get_object_class_name(lockword_ptr-1)*/));
    if (!IS_RESERVED(lockword) || IS_FAT_LOCK(lockword)) {
        // port_mutex_unlock(&TM_LOCK);
        return TM_ERROR_NONE;
    }
    // suspend owner 
    if (owner) {
        assert(owner);
        assert(hythread_get_id(owner) == lock_id);
        assert(owner != hythread_self());
        if(owner->state
                & (TM_THREAD_STATE_TERMINATED
                    | TM_THREAD_STATE_WAITING
                    | TM_THREAD_STATE_WAITING_INDEFINITELY
                    | TM_THREAD_STATE_WAITING_WITH_TIMEOUT
                    | TM_THREAD_STATE_SLEEPING
                    | TM_THREAD_STATE_PARKED
                    | TM_THREAD_STATE_SUSPENDED
                    | TM_THREAD_STATE_IN_MONITOR_WAIT))
        {
            append = 0;
        } else {
            append = RESERVED_BITMASK;
        }

        status=hythread_suspend_other(owner);
        if (status !=TM_ERROR_NONE) {
            return status;
        }
    } else {
        append = 0;
    }

    if(!tm_properties || !tm_properties->use_soft_unreservation) {
	    append = RESERVED_BITMASK;
    }

    // prepare new unreserved lockword and try to CAS it with old one.
    while (IS_RESERVED(lockword)) {
        assert(!IS_FAT_LOCK(lockword));
        CTRACE(("unreserving lock"));
        if (RECURSION(lockword) != 0) {
            lockword_new = (lockword | RESERVED_BITMASK);
            assert(RECURSION(lockword) > 0);
            assert(RECURSION(lockword_new) > 0);
            RECURSION_DEC(&lockword_new, lockword_new);
        } else {
            lockword_new = (lockword | append);
            lockword_new =  lockword_new & 0x0000ffff; 
        }
        if (lockword == apr_atomic_cas32 (((volatile apr_uint32_t*) lockword_ptr), 
                                          (apr_uint32_t) lockword_new, lockword)) {
            CTRACE(("unreserved lock"));
            break;
        }
        lockword = *lockword_ptr;
    }

    // resume owner
    if (owner) {
        hythread_yield_other(owner);
        hythread_resume(owner);
    }

    /* status = port_mutex_unlock(&TM_LOCK);*/

    // Gregory - This lock, right after it was unreserved, may be
    // inflated by another thread and therefore instead of recursion
    // count and reserved flag it will have the fat monitor ID. The
    // assertion !IS_RESERVED(lockword) fails in this case. So it is
    // necessary to check first that monitor is not fat.
    // To avoid race condition between checking two different
    // conditions inside of assert, the lockword contents has to be
    // loaded before checking.
//    lockword = *lockword_ptr;
//    assert(IS_FAT_LOCK(lockword) || !IS_RESERVED(lockword));
    return TM_ERROR_NONE;
}
#else
IDATA VMCALL hythread_unreserve_lock(hythread_thin_monitor_t* lockword_ptr) {
    return TM_ERROR_NONE;
}
#endif 

/**
 * Initializes a thin monitor at the given address.
 * Thin monitor is a version of monitor which is optimized for space and
 * single-threaded usage.
 *
 * @param[in] lockword_ptr monitor addr 
 */
IDATA hythread_thin_monitor_create(hythread_thin_monitor_t *lockword_ptr) {
    //clear anything but hashcode
    // 000000000000000000011111111111
    *lockword_ptr = *lockword_ptr & 0x3FF; 
    return TM_ERROR_NONE;
}

/**
 * Attempts to lock thin monitor.
 * If the monitor is already locked, this call returns immediately with TM_BUSY.  
 * 
 * @param[in] lockword_ptr monitor addr 
 */
IDATA hythread_thin_monitor_try_enter(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword;
    // warkaround strange intel compiler bug 
#if defined (__INTEL_COMPILER) && defined (LINUX)
    volatile
#endif
	IDATA this_id = tm_self_tls->thread_id;
    IDATA lock_id;
    IDATA status;
    hythread_monitor_t fat_monitor;
    int UNUSED i;
    assert(!hythread_is_suspend_enabled());
    assert((UDATA)lockword_ptr > 4);    
    assert(tm_self_tls);
    
    // By DRLVM design rules lockword (see description in thin locks paper)
    // is only modified without compare-and-exchange by owner thread. If tools
    // like Intel Thread Checker find a bug about this line, it may actually be a 
    // false-positive.
    
    lockword = *lockword_ptr;       
    lock_id = THREAD_ID(lockword);
    //CTRACE(("try lock %x %d", this_id, RECURSION(lockword)));
    
    // Check if the lock is already reserved or owned by this thread
    if (lock_id == this_id) {    
        if (RECURSION(lockword) == MAX_RECURSION) {
            //inflate lock in case of recursion overflow
            fat_monitor = hythread_inflate_lock(lockword_ptr);

            if (fat_monitor == NULL) {
                return TM_ERROR_OUT_OF_MEMORY; 
            }
            return hythread_monitor_try_enter(fat_monitor);
            //break FAT_LOCK;
        } else {
            CTRACE(("try lock %x count:%d", this_id, res_lock_count++)); 
            // increase recursion
            RECURSION_INC(lockword_ptr, lockword);
            return TM_ERROR_NONE;
        }        
    } 

    // Fast path didn't work, someoneelse is holding the monitor (or it isn't reserved yet):   

    // DO SPIN FOR A WHILE, this will decrease the number of fat locks.
#ifdef SPIN_COUNT
    for (i = SPIN_COUNT; i >=0; i--, lockword = *lockword_ptr, lock_id = THREAD_ID(lockword)) { 
#endif

        // Check if monitor is free and thin
        if (lock_id == 0) {
            // Monitor is free
            assert( RECURSION(lockword) < 1);
            assert(this_id > 0 && this_id < 0x8000); 
            // Acquire monitor
            if (0 != port_atomic_cas16 (((volatile apr_uint16_t*) lockword_ptr)+1, 
                                        (apr_uint16_t) this_id, 0)) {

#ifdef SPIN_COUNT
                continue; 
#else
                return TM_ERROR_EBUSY;
#endif
            }

#ifdef LOCK_RESERVATION
            //lockword = *lockword_ptr; // this reloading of lockword may be odd, need to investigate;
            if (IS_RESERVED(lockword)) {
                CTRACE(("initially reserve lock %x count: %d ", *lockword_ptr, init_reserve_cout++));
                RECURSION_INC(lockword_ptr, *lockword_ptr);
            }
#endif
            CTRACE(("CAS lock %x count: %d ", *lockword_ptr, cas_cout++));
            return TM_ERROR_NONE;
        } else 

            // Fat monitor
            if (IS_FAT_LOCK(lockword)) {
                CTRACE(("FAT MONITOR %d \n", ++fat_lock2_count/*, vm_get_object_class_name(lockword_ptr-1)*/));  
                fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword)); //  find fat_monitor in lock table
            
                status = hythread_monitor_try_enter(fat_monitor);
#ifdef SPIN_COUNT
                if (status == TM_ERROR_EBUSY) {
                    continue; 
                }
#endif
                return status;
            }

#ifdef LOCK_RESERVATION
        // unreserved busy lock
            else if (IS_RESERVED(lockword)) {
                status = hythread_unreserve_lock(lockword_ptr);
                if (status != TM_ERROR_NONE) {
#ifdef SPIN_COUNT
                    if (status == TM_ERROR_EBUSY) {
                        continue;
                    }
#endif //SPIN_COUNT
                    return status;
                }
                return hythread_thin_monitor_try_enter(lockword_ptr);
            }
#endif 
#ifdef SPIN_COUNT
        hythread_yield();
    }
#endif
    return TM_ERROR_EBUSY;
}


/**
 * Locks thin monitor.
 * 
 * @param[in] lockword_ptr monitor addr 
 */
IDATA hythread_thin_monitor_enter(hythread_thin_monitor_t *lockword_ptr) {
    hythread_monitor_t fat_monitor;
    IDATA status; 
    int saved_disable_count;

    assert(lockword_ptr);    

    if (hythread_thin_monitor_try_enter(lockword_ptr) == TM_ERROR_NONE) {
        return TM_ERROR_NONE;
    }

    while (hythread_thin_monitor_try_enter(lockword_ptr) == TM_ERROR_EBUSY) {
        if (IS_FAT_LOCK(*lockword_ptr)) {
            fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(*lockword_ptr)); //  find fat_monitor in lock table
            CTRACE((" lock %d\n", FAT_LOCK_ID(*lockword_ptr)));
            saved_disable_count = hythread_reset_suspend_disable();
            status = hythread_monitor_enter(fat_monitor);
            hythread_set_suspend_disable(saved_disable_count);
            return status; // lock fat_monitor
        } 
        //hythread_safe_point();
        hythread_yield();
    }
    if (IS_FAT_LOCK(*lockword_ptr)) {
        // lock already inflated
        return TM_ERROR_NONE;
    }
    CTRACE(("inflate_contended  thin_lcok%d\n", ++inflate_contended));   
    fat_monitor = hythread_inflate_lock(lockword_ptr);

    if (fat_monitor == NULL) {
        return TM_ERROR_OUT_OF_MEMORY; 
    }
    return TM_ERROR_NONE;
}

/**
 * Unlocks thin monitor.
 * 
 * @param[in] lockword_ptr monitor addr 
 */
IDATA VMCALL hythread_thin_monitor_exit(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword = *lockword_ptr;
    hythread_monitor_t fat_monitor;
    IDATA this_id = tm_self_tls->thread_id; // obtain current thread id   
    assert(this_id > 0 && this_id < 0xffff);
    assert(!hythread_is_suspend_enabled());

    if (THREAD_ID(lockword) == this_id) {
        if (RECURSION(lockword)==0) {
#ifdef LOCK_RESERVATION
            if (IS_RESERVED(lockword)) {
                CTRACE(("ILLEGAL_STATE %x\n", lockword));
                return TM_ERROR_ILLEGAL_STATE;
            }
#endif
            *lockword_ptr = lockword & 0xffff;
        } else {
            RECURSION_DEC(lockword_ptr, lockword);
            //CTRACE(("recursion_dec: 0x%x", *lockword_ptr)); 
        }
        //CTRACE(("unlocked: 0x%x id: %d\n", *lockword_ptr, THREAD_ID(*lockword_ptr)));
        //hythread_safe_point();
        return TM_ERROR_NONE;     
    }  else if (IS_FAT_LOCK(lockword)) {
        CTRACE(("exit fat monitor %d thread: %d\n", FAT_LOCK_ID(lockword), tm_self_tls->thread_id));
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword)); // find fat_monitor
        return hythread_monitor_exit(fat_monitor); // unlock fat_monitor
    }
    CTRACE(("ILLEGAL_STATE %d\n", FAT_LOCK_ID(lockword)));
    return TM_ERROR_ILLEGAL_STATE;
}

/**
 * Completely releases the ownership over monitor.
 */
IDATA VMCALL hythread_thin_monitor_release(hythread_thin_monitor_t *lockword_ptr)
{
    IDATA status;
    U_32 lockword = *lockword_ptr;
    hythread_t self = hythread_self();

    if (self != hythread_thin_monitor_get_owner(lockword_ptr)) {
        // nothing to do, thread is not an owner of monitor
        return TM_ERROR_NONE;
    }
    if (IS_FAT_LOCK(lockword)) {
        // this is fat monitor
        hythread_monitor_t monitor =
            locktable_get_fat_monitor(FAT_LOCK_ID(lockword));
        monitor->recursion_count = 0;
        status = port_mutex_unlock(&monitor->mutex);
        assert(status == TM_ERROR_NONE);
    } else {
        // this is thin monitor
        while (RECURSION(lockword)) {
            RECURSION_DEC(lockword_ptr, lockword);
            lockword = *lockword_ptr;
        }
        *lockword_ptr = lockword & 0xffff;
    }
    return TM_ERROR_NONE;
}

IDATA thin_monitor_wait_impl(hythread_thin_monitor_t *lockword_ptr, I_64 ms, IDATA nano, IDATA interruptable) {
    // get this thread
    hythread_t this_thread = tm_self_tls;    
    U_32 lockword = *lockword_ptr;
    hythread_monitor_t fat_monitor;
  
    if (!IS_FAT_LOCK(lockword)) {
        // check if the current thread owns lock
        if (!hythread_owns_thin_lock(this_thread, lockword)) {
            CTRACE(("ILLEGAL_STATE %wait d\n", FAT_LOCK_ID(lockword)));
            return TM_ERROR_ILLEGAL_STATE;  
        }    
        CTRACE(("inflate_wait%d\n", ++inflate_waited));  
        // if it is not a thin lock, inflate it
        fat_monitor = hythread_inflate_lock(lockword_ptr);
        if (fat_monitor == NULL) {
            return TM_ERROR_OUT_OF_MEMORY; 
        }
    } else {
        // otherwise, get the appropriate fat lock
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword));
    }
    assert(fat_monitor == locktable_get_fat_monitor(FAT_LOCK_ID(*lockword_ptr)));
    return monitor_wait_impl(fat_monitor,ms,nano, interruptable);    
}

/**
 * Atomically releases a thin monitor and causes the calling 
 * thread to wait on the given condition variable.
 *
 * Calling thread must own the monitor. After notification is received, the monitor
 * is locked again, restoring the original recursion.
 *
 * @param[in] lockword_ptr monitor addr 
 * @return  
 *      TM_NO_ERROR on success 
 */
IDATA VMCALL hythread_thin_monitor_wait(hythread_thin_monitor_t *lockword_ptr) {
    return thin_monitor_wait_impl(lockword_ptr, 0, 0, WAIT_NONINTERRUPTABLE);    
}       

/**
 * Atomically releases a thin monitor and causes the calling 
 * thread to wait for signal.
 *
 * Calling thread must own the monitor. After notification is received or time out, the monitor
 * is locked again, restoring the original recursion.
 *
 * @param[in] lockword_ptr monitor addr
 * @param[in] ms timeout millis
 * @param[in] nano timeout nanos
 * @return  
 *      TM_NO_ERROR on success 
 *      TM_ERROR_TIMEOUT in case of time out.
 */
IDATA VMCALL hythread_thin_monitor_wait_timed(hythread_thin_monitor_t *lockword_ptr, I_64 ms, IDATA nano) {
    return thin_monitor_wait_impl(lockword_ptr, ms, nano, WAIT_NONINTERRUPTABLE);    
}

/**
 * Atomically releases a thin monitor and causes the calling 
 * thread to wait for signal.
 *
 * Calling thread must own the monitor. After notification is received or time out, the monitor
 * is locked again, restoring the original recursion.
 *
 * @param[in] lockword_ptr monitor addr
 * @param[in] ms timeout millis
 * @param[in] nano timeout nanos
 * @return  
 *      TM_NO_ERROR on success 
 *      TM_THREAD_INTERRUPTED in case thread was interrupted during wait.
 *      TM_ERROR_TIMEOUT in case of time out.
 */
IDATA VMCALL hythread_thin_monitor_wait_interruptable(hythread_thin_monitor_t *lockword_ptr, I_64 ms, IDATA nano) {
    return thin_monitor_wait_impl(lockword_ptr, ms, nano, WAIT_INTERRUPTABLE);    
}


/**
 * Signals a single thread that is blocking on the given monitor to wake up. 
 *
 * @param[in] lockword_ptr monitor addr
 */
IDATA hythread_thin_monitor_notify(hythread_thin_monitor_t *lockword_ptr) {
    hythread_monitor_t fat_monitor;
    hythread_thin_monitor_t lockword = *lockword_ptr;
    if (IS_FAT_LOCK(lockword)) {
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword));
        assert(fat_monitor);
        return hythread_monitor_notify(fat_monitor); 
    }
    // check if the current thread owns lock
    if (!hythread_owns_thin_lock(tm_self_tls, lockword)) {
        return TM_ERROR_ILLEGAL_STATE;  
    }    
  
    return TM_ERROR_NONE;
}

/**
 * Signals all threads blocking on the given thin monitor.
 * 
 * @param[in] lockword_ptr monitor addr
 */
IDATA hythread_thin_monitor_notify_all(hythread_thin_monitor_t *lockword_ptr) {
    hythread_monitor_t fat_monitor;
    hythread_thin_monitor_t lockword = *lockword_ptr;
    if (IS_FAT_LOCK(lockword)) {
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword));
        assert(fat_monitor);
        return hythread_monitor_notify_all(fat_monitor); 
    }
    // check if the current thread owns lock
    if (!hythread_owns_thin_lock(tm_self_tls, lockword)) {
        return TM_ERROR_ILLEGAL_STATE;  
    }    
    return TM_ERROR_NONE;
}

/**
 * Destroys the thin monitor and releases any associated resources.
 *
 * @param[in] lockword_ptr monitor addr 
 */
IDATA hythread_thin_monitor_destroy(hythread_thin_monitor_t *lockword_ptr) {
    hythread_monitor_t fat_monitor;
    hythread_thin_monitor_t lockword = *lockword_ptr;
    
    if (IS_FAT_LOCK(lockword)) {
        fat_monitor = locktable_delete_entry(FAT_LOCK_ID(lockword));
        assert(fat_monitor);
        return hythread_monitor_destroy(fat_monitor); 
    }
    return TM_ERROR_NONE;
}

/*
 * Inflates the compressed lockword into fat fat_monitor
 */
hythread_monitor_t VMCALL hythread_inflate_lock(hythread_thin_monitor_t *lockword_ptr) {
    hythread_monitor_t fat_monitor;
    IDATA status;
    IDATA fat_monitor_id;
    U_32 lockword;
    int i;

    // we don't need to write lock on lock_table during all this function because
    // the only invariant we need is 'fat lock is not in the fat lock table before we put it'

    // however this invariant is true because we hold monitor->mutex during this function
    // so it cannot be called twice for the signle monitor concurrently

    lockword = *lockword_ptr;
    if (IS_FAT_LOCK (lockword)) {
        return locktable_get_fat_monitor(FAT_LOCK_ID(lockword));
    }
#ifdef LOCK_RESERVATION
    // unreserve lock first
    if (IS_RESERVED(lockword)) {
        unreserve_self_lock(lockword_ptr);
        lockword = *lockword_ptr;
    }
    assert(!IS_RESERVED(lockword));
#endif 

    assert(hythread_owns_thin_lock(tm_self_tls, lockword));
    assert(!hythread_is_suspend_enabled());

    CTRACE(("inflation begin for %x thread: %d", lockword, tm_self_tls->thread_id));
    status = hythread_monitor_init(&fat_monitor, 0); // allocate fat fat_monitor    
    //assert(status == TM_ERROR_NONE);  
    if (status != TM_ERROR_NONE) {
        return NULL;
    } 
    status = hythread_monitor_enter(fat_monitor);
    if (status != TM_ERROR_NONE) {
        return NULL;
    } 
    
    for (i = RECURSION(lockword); i > 0; i--) {
        CTRACE(("inflate recursion monitor"));
        status = hythread_monitor_enter(fat_monitor);  // transfer recursion count to fat fat_monitor   
        assert(status == TM_ERROR_NONE);     
    }     
    fat_monitor_id = locktable_put_fat_monitor(fat_monitor); // put fat_monitor into lock table
    set_fat_lock_id(lockword_ptr, fat_monitor_id);
    CTRACE(("hythread_inflate_lock  %d thread: %d\n", FAT_LOCK_ID(*lockword_ptr), tm_self_tls->thread_id));
    //assert(FAT_LOCK_ID(*lockword_ptr) != 2);
    CTRACE(("FAT ID : 0x%x", *lockword_ptr));
#ifdef LOCK_RESERVATION
    assert(!IS_RESERVED(*lockword_ptr));
#endif
    return fat_monitor;
}


/*
 * Deflates the fat lock back into compressed lock word.
 * Not yet implemented.
 */
void deflate_lock(hythread_monitor_t fat_monitor, hythread_thin_monitor_t *lockword) {
    /*
      ;;// put thread_id into lockword
      //... TODO
      RECURSION_COUNT(lockword) = fat_monitor->recursion_count; // Transfer recursion count from fat lock back to thin lock
      IS_FAT_LOCK(lockword) = 0; // Set contention bit indicating that the lock is now thin
      hythread_monitor_destroy(fat_monitor); // Deallocate fat_monitor
      locktable_delete_entry(lock_id); // delete entry in lock able

    */
}



// Lock table implementation

/*
 * Enter locktable read section
 */
static void locktable_reader_enter() {
    IDATA status = port_mutex_lock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);

    if (lock_table->state == HYTHREAD_LOCKTABLE_IDLE
        || (lock_table->state == HYTHREAD_LOCKTABLE_READING
            && lock_table->writers_waiting == 0))
    {
        lock_table->state = HYTHREAD_LOCKTABLE_READING;
        lock_table->readers_reading++;
    } else {
        lock_table->readers_waiting++;
        hycond_wait_timed_raw(&lock_table->read, &lock_table->mutex, 0, 0);

        // We are asserting here that we exited wait with the correct state
        assert(lock_table->state == HYTHREAD_LOCKTABLE_READING);
    }
    status = port_mutex_unlock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);
}

/*
 * Exit locktable read section
 */
static void locktable_reader_exit() {
    IDATA status = port_mutex_lock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);

    lock_table->readers_reading--;

    if (lock_table->readers_reading == 0) {
        if (lock_table->writers_waiting > 0) {
            lock_table->state = HYTHREAD_LOCKTABLE_WRITING;
            hycond_notify(&lock_table->write);
        } else {
            lock_table->state = HYTHREAD_LOCKTABLE_IDLE;
        }
    }

    status = port_mutex_unlock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);
}

/*
 * Enter locktable write section
 */
static void locktable_writer_enter() {
    IDATA status = port_mutex_lock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);

    if (lock_table->state != HYTHREAD_LOCKTABLE_IDLE) {
        lock_table->writers_waiting++;
        hycond_wait_timed_raw(&lock_table->write, &lock_table->mutex, 0, 0);

        // We are asserting here that we exited wait with the correct state
        assert(lock_table->state == HYTHREAD_LOCKTABLE_WRITING);

        lock_table->writers_waiting--;
    } else {
        lock_table->state = HYTHREAD_LOCKTABLE_WRITING;
    }        

    status = port_mutex_unlock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);
}

/*
 * Exit locktable write section
 */
static void locktable_writer_exit() {
    IDATA status = port_mutex_lock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);

    if (lock_table->readers_reading > 0) {
        lock_table->readers_reading = lock_table->readers_waiting;
        lock_table->readers_waiting = 0;
        lock_table->state = HYTHREAD_LOCKTABLE_READING;
        hycond_notify_all(&lock_table->read);
    } else if (lock_table->writers_waiting > 0) {
        hycond_notify(&lock_table->write);
    } else {
        lock_table->state = HYTHREAD_LOCKTABLE_IDLE;
    }

    status = port_mutex_unlock(&lock_table->mutex);
    assert(status == TM_ERROR_NONE);
}


/*
 * Returns the OS fat_monitor associated with the given lock id.
 */
hythread_monitor_t locktable_get_fat_monitor(IDATA lock_id) {
    hythread_monitor_t fat_monitor;
    CTRACE(("LOCK ID in table %x\n", lock_id));

    assert(lock_id >=0 && (U_32)lock_id < lock_table->size);
    // we don't need to protect this read, because monitor can't vanish or
    // be moved in lock table while we are doing get_fat_monitor
    fat_monitor = FAT_LOCK(lock_id);
    return fat_monitor;
}

/*
 * Sets the value of the specific entry in the lock table
 */

IDATA locktable_put_fat_monitor(hythread_monitor_t fat_monitor) {
    U_32 i = 0;
    U_32 mon_index;
    short free_slot_found = 0;

    if (lock_table == 0) { 
        DIE (("Lock table not initialized!"));
    }

    locktable_writer_enter();
    mon_index = lock_table->array_cursor;

    for(i =0; i < lock_table->size; i++) {
        hythread_monitor_t* table;

        if (mon_index == lock_table->size) 
            mon_index = 0;

        table = lock_table->tables[mon_index / HY_FAT_TABLE_ENTRIES];

        if (table[mon_index % HY_FAT_TABLE_ENTRIES] == 0) {
            assert(lock_table->live_objs[mon_index] == 0);
            table[mon_index % HY_FAT_TABLE_ENTRIES] = fat_monitor;
            free_slot_found = 1;
            break;
        }
        ++mon_index;
    }   

    if(!free_slot_found) {
        U_32 old_size;
        hythread_monitor_t* table;

        if (lock_table->size >= HY_MAX_FAT_LOCKS) {
            DIE (("Fat monitor table is exceeded!"));
        }
        
        old_size = lock_table->size;
        lock_table->size += HY_FAT_TABLE_ENTRIES;
        table = (hythread_monitor_t *)calloc(HY_FAT_TABLE_ENTRIES,
                                               sizeof(hythread_monitor_t));
        assert(table);
        lock_table->tables[old_size / HY_FAT_TABLE_ENTRIES] = table;
        
        lock_table->live_objs = realloc(lock_table->live_objs,
					lock_table->size * sizeof(unsigned char));
        assert(lock_table->live_objs);

        memset(lock_table->live_objs + old_size, 0,
               HY_FAT_TABLE_ENTRIES * sizeof(unsigned char));
      
        table[0] = fat_monitor;
        mon_index = old_size;
    }

    lock_table->array_cursor = mon_index + 1;
    locktable_writer_exit();
    return mon_index;
}

// flag that is set when major collection happens
static unsigned char live_objs_were_reported = 0; 


void VMCALL hythread_native_resource_is_live(U_32 lockword)
{
    IDATA index = 0;
    index = get_fat_lock_id( (hythread_thin_monitor_t *) &lockword);
    locktable_writer_enter();
    lock_table->live_objs[index] = 1; // mark the fat lock entry as still alive
    live_objs_were_reported = 1;
    locktable_writer_exit();
}

void VMCALL hythread_reclaim_resources()
{
    U_32 i = 0;

#ifdef DEBUG_NATIVE_RESOURCE_COLLECTION
    int old_slots_occupied = 0;
    int new_slots_occupied = 0;
    
    for (;i < lock_table->size; i++) {
        if (FAT_LOCK(i))
            old_slots_occupied++;
    }
#endif

    locktable_reader_enter();
    // If major collection didn't happen, do nothing
    // reset the flag (major collection happened)

    if (!live_objs_were_reported) {
        locktable_reader_exit();
        return;
    }    
    locktable_reader_exit();

    locktable_writer_enter();
    live_objs_were_reported = 0;  

    for(i = 0; i < lock_table->size; i++) {
        if (lock_table->live_objs[i]) {
            assert(FAT_LOCK(i));
#ifdef DEBUG_NATIVE_RESOURCE_COLLECTION
            new_slots_occupied++;
#endif

            // reset the live array for the next major GC cycle
            lock_table->live_objs[i] = 0;  
        } else {
            if (FAT_LOCK(i)) {
                hythread_monitor_destroy(FAT_LOCK(i));
                FAT_LOCK(i)  = 0;
            }
        }
    }
    locktable_writer_exit();
#ifdef DEBUG_NATIVE_RESOURCE_COLLECTION
    CTRACE(("hythread_reclaim_resources(): old = %d, new = %d\n", 
           old_slots_occupied,
           new_slots_occupied));
#endif
}

/*
 * Deletes the entry in the lock table with the given lock_id
 */
hythread_monitor_t  locktable_delete_entry(int lock_id) {
    hythread_monitor_t  m;
    DIE(("shouldn't get here"));
    assert(lock_id >=0 && (U_32)lock_id < lock_table->size);
    m = FAT_LOCK(lock_id);
    FAT_LOCK(lock_id) = NULL;
    return m;
}

/**
 * Returns the owner of the given thin monitor.
 *
 * @param[in] lockword_ptr monitor addr 
 */
hythread_t VMCALL hythread_thin_monitor_get_owner(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword;
    hythread_monitor_t fat_monitor;
    
    assert(lockword_ptr);    
    lockword = *lockword_ptr;       
    if (IS_FAT_LOCK(lockword)) {
        // find fat_monitor in lock table
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword)); 
        return fat_monitor->owner;
    }

    if (THREAD_ID(lockword)== 0) {
        return NULL;
    }

#ifdef LOCK_RESERVATION
    if (RECURSION(lockword)==0 && IS_RESERVED(lockword)) {
        return NULL;
    }
#endif
    return hythread_get_thread(THREAD_ID(lockword));
}

/**
 * Returns the recursion count of the given monitor.
 *
 * @param[in] lockword_ptr monitor addr 
 */
IDATA VMCALL hythread_thin_monitor_get_recursion(hythread_thin_monitor_t *lockword_ptr) {
    U_32 lockword;
    hythread_monitor_t fat_monitor;
    
    assert(lockword_ptr);    
    lockword = *lockword_ptr;       
    if (IS_FAT_LOCK(lockword)) {
        //  find fat_monitor in lock table
        fat_monitor = locktable_get_fat_monitor(FAT_LOCK_ID(lockword)); 
        return fat_monitor->recursion_count+1;
    }
    if (THREAD_ID(lockword) == 0) {
        return 0;
    }
#ifdef LOCK_RESERVATION
    if (IS_RESERVED(lockword)) {
        return RECURSION(lockword);
    }
#endif
    return RECURSION(lockword)+1;
}

//@}
