#include "hycomp.h"
#include "hymutex.h"
#include "thrdsup.h"
#include "thrtypes.h"

IDATA hythread_spinlock_acquire(hythread_t self, hythread_monitor_t monitor)
{
    UDATA volatile spinCount3 = monitor->spinCount3;

    do {
        UDATA volatile spinCount2 = monitor->spinCount2;

        do {
            if (__sync_bool_compare_and_swap(&monitor->spinlockState, HYTHREAD_MONITOR_SPINLOCK_UNOWNED, HYTHREAD_MONITOR_SPINLOCK_OWNED)) {
                return 0;
            }

            // PAUSE
            asm volatile ("pause");

            UDATA volatile spinCount1 = monitor->spinCount1;
            do {
                spinCount1--;
            } while (spinCount1 > 0);

            spinCount2--;
        } while (spinCount2 > 0);

        hythread_yield();

        spinCount3--;
    } while (spinCount3 > 0);

    return -1;
}

UDATA hythread_spinlock_swapState(hythread_monitor_t monitor, UDATA newState)
{
    return __sync_lock_test_and_set(&monitor->spinlockState, newState);
}

