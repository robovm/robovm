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

/*
 * @file
 * @ingroup Thread
 */


/*
 *	the following must come before the standard includes because thrdsup.h 
 *	includes windows.h in Win32.
 */
#include "thrdsup.h"

#include "hythread.h"
#include "thrtypes.h"

#define CDEV_CURRENT_FUNCTION _prototypes_private

void paint_stack PROTOTYPE ((hythread_t thread));

#undef CDEV_CURRENT_FUNCTION

extern UDATA current_stack_depth PROTOTYPE ((void));

#define STACK_PATTERN 0xBAADF00D

#define CDEV_CURRENT_FUNCTION hythread_get_flags
#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_cpu_time
/**
 * Return the amount of CPU time used by a thread.
 * 
 * @param[in] thread
 * @return actual time on CPU used by thread (nanoseconds) or
 * negative value if not supported.
 */
I_64 VMCALL
hythread_get_cpu_time (hythread_t thread)
{

#if defined(WIN32)
  FILETIME creationTime, exitTime, kernelTime, userTime;
  I_64 totalTime;
  /* WARNING! Not supported on Win95!  Need to test to ensure this fails gracefully */
  if (GetThreadTimes
      (thread->handle, &creationTime, &exitTime, &kernelTime, &userTime))
    {
      totalTime =
	((I_64) kernelTime.
	 dwLowDateTime | ((I_64) kernelTime.dwHighDateTime << 32)) +
	((I_64) userTime.
	 dwLowDateTime | ((I_64) userTime.dwHighDateTime << 32));
      /* totalTime is in 100's of nanos.  Convert to nanos */
      return totalTime * 100;
    }
#endif /* WIN32 */

  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_handle
/** 
 * Return the OS handle for a thread.
 * 
 * @param thread a thread
 * @return OS handle
 */
UDATA VMCALL
hythread_get_handle (hythread_t thread)
{
  return (UDATA) thread->handle;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_enable_stack_usage
/**
 * Enable or disable monitoring of stack usage.
 * 
 * @param[in] enable 0 to disable or non-zero to enable.
 * @return none
 * 
 */
void VMCALL
hythread_enable_stack_usage (UDATA enable)
{
  hythread_library_t lib = GLOBAL_DATA (default_library);
  lib->stack_usage = enable;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_stack_usage
/** 
 * Return the approximate stack usage by a thread
 * 
 * @param[in] thread a thread 
 * @return 0 if the stack has not been painted<br>
 * (UDATA)-1 if the stack has overflowed<br>
 *  otherwise the approximate maximum number of bytes used on the stack
 */
UDATA VMCALL
hythread_get_stack_usage (hythread_t thread)
{
#if defined(LINUX)
  return 0;
#else
  UDATA *tos = thread->tos;
  UDATA count = thread->stacksize;
  if (tos == NULL || count == 0)
    {
      return 0;
    }
  if (*tos != STACK_PATTERN)
    {
      return (UDATA) - 1;
    }
  while (*tos++ == STACK_PATTERN)
    {
      count -= sizeof (UDATA);
    }
  return count;
#endif
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION paint_stack
/*
 * Paint a thread's stack.
 * 
 * Attempt to paint the stack region with STACK_PATTERN so we can
 * detect stack usage.  Sets thread->tos to the maximum stack
 * address.
 * @note This won't work on PA-RISC because of backwards stacks
 * 
 * @param thread a thread
 * @return none
 */
void
paint_stack (hythread_t thread)
{
#if defined(LINUX)
  /* z/OS and Linux don't let us set the stack size, so we can't paint the stack safely */
#elif defined(WIN32)
  MEMORY_BASIC_INFORMATION memInfo;
  SYSTEM_INFO sysInfo;
  UDATA *curr;
  UDATA *stack = (UDATA *) & stack;
  /* Find out where the stack starts. */
  VirtualQuery (stack, &memInfo, sizeof (MEMORY_BASIC_INFORMATION));
  /* Start painting. Skip the top 32 slots (to protect this stack frame) */
  curr = stack - 32;
  __try
  {
    while (curr > (UDATA *) memInfo.AllocationBase)
      *curr-- = STACK_PATTERN;
  }
  __except (1)
  {
    /* Ran off the end of the stack. Stop */
  }
  thread->tos = curr + 1;
  /* Round up to the system page size. */
  GetSystemInfo (&sysInfo);
  thread->stacksize =
    ((UDATA) stack - (UDATA) thread->tos +
     sysInfo.dwPageSize) & ~(sysInfo.dwPageSize - 1);
#else
  IDATA maxStack, stackSize, index;
  UDATA *stack = (UDATA *) & stack;
  stackSize = thread->stacksize - current_stack_depth ();
  maxStack = stackSize / sizeof (UDATA) - 32;
  if (maxStack <= 0)
    {
      return;
    }
  thread->tos = stack - maxStack;
  /* don't paint the top 32 slots (to protect this stack frame) */
  for (index = 32; index <= maxStack; index++)
    {
      *(stack - index) = STACK_PATTERN;
    }
#endif
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_stack_size
/**
 * Returns a thread's stack size.
 * 
 * @param[in] thread a thread
 * @return 0 if the thread is an attached thread
 * or the initial size of the thread's stack,
 * 
 */
UDATA VMCALL
hythread_get_stack_size (hythread_t thread)
{
  return thread->stacksize;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_os_priority
/**
 * Return the OS's scheduling policy and priority for a thread.
 *
 * Query the OS to determine the actual priority of the specified thread.
 * The priority and scheduling policy are stored in the pointers provided.
 * On Windows the "policy" contains the thread's priority class.
 * On POSIX systems it contains the scheduling policy
 * On OS/2 no information is available.  0 is stored in both pointers.
 *
 * @param[in] thread a thread
 * @param[in] policy pointer to location where policy will be stored (non-NULL)
 * @param[in] priority pointer to location where priority will be stored (non-NULL)
 * @return 0 on success or negative value on failure
 */
IDATA VMCALL
hythread_get_os_priority (hythread_t thread, IDATA * policy, IDATA * priority)
{
#if defined(HY_POSIX_THREADS)
  struct sched_param sched_param;
  int osPolicy, rc;
  rc = pthread_getschedparam (thread->handle, &osPolicy, &sched_param);
  if (rc)
    return -1;
  *priority = sched_param.sched_priority;
  *policy = osPolicy;
#else
#if defined(WIN32)
  *priority = GetThreadPriority (thread->handle);
  if (*priority == THREAD_PRIORITY_ERROR_RETURN)
    return -1;

  *policy = GetPriorityClass (thread->handle);
  if (*policy == 0)
    return -1;
#else

#error Unknown platform

#endif /* HY_POSIX_THREADS */
#endif /* HYEPOC32 */

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hythread_get_user_time
/**
 * Return the amount of USER time used by a thread.
 * 
 * @param[in] thread
 * @return actual time on USER used by thread (nanoseconds) or
 * negative value if not supported.
 */
I_64 VMCALL
hythread_get_user_time (hythread_t thread)
{

#if defined(WIN32)
  FILETIME creationTime, exitTime, kernelTime, userTime;
  I_64 totalTime;
  /* WARNING! Not supported on Win95!  Need to test to ensure this fails gracefully */
  if (GetThreadTimes
      (thread->handle, &creationTime, &exitTime, &kernelTime, &userTime))
    {
      totalTime =
	((I_64) userTime.
	 dwLowDateTime | ((I_64) userTime.dwHighDateTime << 32));
      /* totalTime is in 100's of nanos.  Convert to nanos */
      return totalTime * 100;
    }
#endif /* WIN32 */

  return -1;
}

#undef CDEV_CURRENT_FUNCTION
