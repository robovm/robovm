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

#define CDEV_CURRENT_FUNCTION _comment_
/** 
 * @file
 * @ingroup Port
 * @brief Timer utilities
 */

#undef CDEV_CURRENT_FUNCTION

#include <time.h>
#include <sys/types.h>
#include <sys/time.h>
#include "hyport.h"

/* Frequency is microseconds / second */
#define HYTIME_HIRES_CLOCK_FREQUENCY ((U_64) 1000000)

#define CDEV_CURRENT_FUNCTION _prototypes_private

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_msec_clock
/**
 * Query OS for timestamp.
 * Retrieve the current value of system clock and convert to milliseconds.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on failure, time value in milliseconds on success.
 * @deprecated Use @ref hytime_hires_clock and @ref hytime_hires_delta
 */
/*  technically, this should return I_64 since both timeval.tv_sec and timeval.tv_usec are long */

UDATA VMCALL
hytime_msec_clock (struct HyPortLibrary * portLibrary)
{
  struct timeval tp;
  struct timezone tzp;

  gettimeofday (&tp, &tzp);
  return (tp.tv_sec * 1000) + (tp.tv_usec / 1000);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_usec_clock
/**
 * Query OS for timestamp.
 * Retrieve the current value of system clock and convert to microseconds.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on failure, time value in microseconds on success.
 * @deprecated Use @ref hytime_hires_clock and @ref hytime_hires_delta
 */
UDATA VMCALL
hytime_usec_clock (struct HyPortLibrary * portLibrary)
{
  struct timeval tp;
  struct timezone tzp;

  gettimeofday (&tp, &tzp);
  return (tp.tv_sec * 1000000) + tp.tv_usec;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_current_time_millis
/**
 * Query OS for timestamp.
 * Retrieve the current value of system clock and convert to milliseconds since
 * January 1st 1970.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on failure, time value in milliseconds on success.
 */
I_64 VMCALL
hytime_current_time_millis (struct HyPortLibrary * portLibrary)
{
  struct timeval tp;

  gettimeofday (&tp, 0);
  return ((I_64) tp.tv_sec) * 1000 + tp.tv_usec / 1000;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_hires_clock
/**
 * Query OS for timestamp.
 * Retrieve the current value of the high-resolution performance counter.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on failure, time value on success.
 */
U_64 VMCALL
hytime_hires_clock (struct HyPortLibrary * portLibrary)
{
  struct timeval tp;
  struct timezone tzp;

  gettimeofday (&tp, &tzp);
  return ((U_64) tp.tv_sec * 1000000) + (U_64) tp.tv_usec;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_hires_frequency
/**
 * Query OS for clock frequency
 * Retrieves the frequency of the high-resolution performance counter.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on failure, number of ticks per second on success.
 */
U_64 VMCALL
hytime_hires_frequency (struct HyPortLibrary * portLibrary)
{
  return HYTIME_HIRES_CLOCK_FREQUENCY;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_hires_delta
/**
 * Calculate time difference between two hires clock timer values @ref hytime_hires_clock.
 *
 * Given a start and end time determine how much time elapsed.  Return the value as
 * requested by the required resolution
 *
 * @param[in] portLibrary The port library.
 * @param[in] startTime Timer value at start of timing interval
 * @param[in] endTime Timer value at end of timing interval
 * @param[in] requiredResolution Returned timer resolution as a fraction of a second.  For example: 
 *  \arg 1 to report elapsed time in seconds
 *  \arg 1,000 to report elapsed time in milliseconds
 *  \arg 1,000,000 to report elapsed time in microseconds
 *
 * @return 0 on failure, time difference on success.
 *
 * @note helper macros are available for commonly requested resolution
 *  \arg HYPORT_TIME_DELTA_IN_SECONDS return timer value in seconds.
 *  \arg HYPORT_TIME_DELTA_IN_MILLISECONDS return timer value in milliseconds.
 *  \arg HYPORT_TIME_DELTA_IN_MICROSECONDS return timer value in micoseconds.
 *  \arg HYPORT_TIME_DELTA_IN_NANOSECONDS return timer value in nanoseconds.
 */
U_64 VMCALL
hytime_hires_delta (struct HyPortLibrary * portLibrary, U_64 startTime,
                    U_64 endTime, UDATA requiredResolution)
{
  U_64 ticks;

  /* modular arithmetic saves us, answer is always ... */
  ticks = endTime - startTime;

  if (HYTIME_HIRES_CLOCK_FREQUENCY == requiredResolution)
    {
      return ticks;
    }

  if (HYTIME_HIRES_CLOCK_FREQUENCY < requiredResolution)
    {
      return (ticks * requiredResolution) / HYTIME_HIRES_CLOCK_FREQUENCY;
    }

  return ticks / (HYTIME_HIRES_CLOCK_FREQUENCY / requiredResolution);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hytime_startup
 * should be destroyed here.
 *
 * @param[in] portLib The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hytime_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytime_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the time operations may be created here.  All resources created here should be destroyed
 * in @ref hytime_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_TIME
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hytime_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION

#undef CDEV_CURRENT_FUNCTION
