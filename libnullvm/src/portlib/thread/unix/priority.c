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

#include "thrdsup.h"

#include <sched.h>              /* must be after <pthread.h> or DECUNIX kaks */

#include <stdio.h>

static int min, max;

#if defined(HY_PRIORITY_MAP)
const int priority_map[HYTHREAD_PRIORITY_MAX + 1] = HY_PRIORITY_MAP;
#else
int priority_map[HYTHREAD_PRIORITY_MAX + 1];
void
initialize_priority_map (void)
{
  int policy, rc;
  policy = HY_DEFAULT_SCHED;
  initialize_priority_range (0, HYTHREAD_PRIORITY_MAX, policy);
}

void
initialize_priority_range (int range_start, int range_end, int policy)
{
  int delta, i, tmpmax, tmpmin, mid, midrange, tailcount;
  max = sched_get_priority_max (policy);
  min = sched_get_priority_min (policy);
  if (max == min)
    {
      /* get this thread's priority and use that for all threads */
      struct sched_param schedParam;
      int currPolicy;
      int rv =
        pthread_getschedparam (pthread_self (), &currPolicy, &schedParam);
      max = schedParam.sched_priority;
      min = max;
    }
  /* give us some room to do some math */
  tmpmax = max * 1024;
  tmpmin = min * 1024;
  mid = (tmpmin + tmpmax) / 2;
  midrange = range_start + (range_end - range_start) / 2;
  priority_map[range_start] = min;
  delta = (mid - tmpmin) / midrange;
  for (i = 1; i < midrange; i++)
    {
      priority_map[midrange - i] = (mid - delta * i) / 1024;
    }
  tailcount = range_end - midrange;
  delta = (tmpmax - mid) / tailcount;
  for (i = 0; i < tailcount; i++)
    {
      priority_map[midrange + i] = (mid + delta * i) / 1024;;
    }
  priority_map[range_end] = max;
#if defined(DEBUG)
  for (i = range_start; i <= range_end; i++)
    {
      printf ("prio %d: %d\n", i, priority_map[i]);
    }
#endif
}
#endif

IDATA
set_pthread_priority (pthread_t handle, IDATA priority)
{
  struct sched_param sched_param;
  int policy, rc;

  policy = HY_DEFAULT_SCHED;
  sched_param.sched_priority = priority;
  rc = pthread_setschedparam (handle, policy, &sched_param);
  return rc;
}

void
initialize_thread_priority (hythread_t thread)
{
  int policy, priority, i;
  struct sched_param sched_param;

  /* set the default value */
  thread->priority = HYTHREAD_PRIORITY_NORMAL;

  /* are we using priorities at all? */
  if (priority_map[HYTHREAD_PRIORITY_MIN] ==
      priority_map[HYTHREAD_PRIORITY_MAX])
    return;

  if (pthread_getschedparam (thread->handle, &policy, &sched_param))
    {
      /* the call failed */
      return;
    }

  if (policy != HY_DEFAULT_SCHED)
    {
      /* printf ("Policy isn't SCHED_OTHER!\n"); */
      /* what to do?? */
      return;
    }

  /* on some platforms (i.e. Solaris) we get out of range values (e.g. 0) for threads with no explicitly set priority */
  if (sched_param.sched_priority < min || sched_param.sched_priority > max)
    {
      return;
    }

  priority = sched_param.sched_priority;
  for (i = HYTHREAD_PRIORITY_MIN; i <= HYTHREAD_PRIORITY_MAX; i++)
    {
      if (priority <= priority_map[i])
        {
          thread->priority = i;
          return;
        }
    }
}
