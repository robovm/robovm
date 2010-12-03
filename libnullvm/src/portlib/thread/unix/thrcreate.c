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

#include "thrcreate.h"

#include <stdio.h>              /* for printf */
#include <stdlib.h>             /* for abort */
#include <limits.h>             /* for PTHREAD_STACK_MIN */
#if defined(LINUX)
#include "linuxonexit.h"
#endif

#include "hythread.h"

IDATA
create_pthread (pthread_t * handle, UDATA stacksize, UDATA priority,
                entrypoint_t entrypoint, void *entryarg)
{
  pthread_attr_t attr;
  struct sched_param sched_param;
  IDATA retCode;
#if defined(LINUX)
  static int linux_on_exit_hook_set = 0;
#endif

  if (pthread_attr_init (&attr) != 0)
    return -1;

  /* verify that there are no extra fields in sched_param! This should be optimized out by any half decent compiler */
  if (sizeof (sched_param) != sizeof (sched_param.sched_priority))
    {
      printf ("Assertion failed %s:%d\n", __FILE__, __LINE__);
      abort ();
    }

  sched_param.sched_priority = priority;
  pthread_attr_setschedparam (&attr, &sched_param);

/* Linux allocates 2MB if you ask for a stack smaller than STACK_MIN */
#if defined(LINUX)
  if (stacksize < PTHREAD_STACK_MIN)
    stacksize = PTHREAD_STACK_MIN;
#endif

  pthread_attr_setstacksize (&attr, stacksize);

  retCode = pthread_create (handle, &attr, entrypoint, entryarg);

#if defined(LINUX)
  if (!linux_on_exit_hook_set)
    {
      linux_set_on_exit_hook ();
      linux_on_exit_hook_set = 1;
    }
#endif

  return retCode;
}
