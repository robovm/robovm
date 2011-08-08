#include "hycomp.h"
#include "hymutex.h"
#include "thrdsup.h"
#include "thrtypes.h"

void hythread_monitor_pin(hythread_monitor_t monitor, hythread_t osThread)
{
    __sync_fetch_and_add(&monitor->pinCount, 1);
}

void hythread_monitor_unpin(hythread_monitor_t monitor, hythread_t osThread)
{
    __sync_fetch_and_sub(&monitor->pinCount, 1);
}

