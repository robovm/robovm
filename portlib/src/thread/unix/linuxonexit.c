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

#if defined(LINUX)
#include "linuxonexit.h"

#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

#define __USE_GNU
#include <dlfcn.h>
#undef __USE_GNU

static void linux_on_exit_sig_handler PROTOTYPE ((int signum));
static void linux_on_exit_hook PROTOTYPE ((int exit_code, void *opaque));
static int linux_on_exit_code = -1;
static void
linux_on_exit_sig_handler (int signum)
{
  /* exit without trying to cleanup (ala exit()) since thats what got us into 
   * this mess in the first place */
  _exit (linux_on_exit_code);
}

static void
linux_on_exit_hook (int exit_code, void *opaque)
{
  sigset_t set;
  struct sigaction act;
        /**
         * Hook handler registered via on_exit(). Added as a resultion
         * for buggy linux pthread lock handling on thread exit. 
         *
         * Note that this fix should be revisited/reversed on systems running NPTL
         * threads. Ie glibc 2.3+ and 2.6 series kernels. It _is_ feasible that
         * such a system could still use old linuxthreads depending on distribution.
         */
  /* Kill any previously set alarms */
  alarm (0);

  sigemptyset (&set);
  act.sa_handler = linux_on_exit_sig_handler;
  act.sa_flags = 0;
  act.sa_mask = set;

  sigaction (SIGALRM, &act, NULL);

  /* Unblock SIGALRM */
  sigemptyset (&set);
  sigaddset (&set, SIGALRM);
  sigprocmask (SIG_UNBLOCK, &set, NULL);

  /* remember the correct exit code, to be passed to _exit() in case we dead lock */
  linux_on_exit_code = exit_code;

  /* signal an alarm in N seconds, ie thats how long we've got to exit */
  alarm (5);
}

void
linux_set_on_exit_hook (void)
{
  Dl_info dl_info;
  int dl_result;
  return;
  dl_result = dladdr ((void *) linux_on_exit_hook, &dl_info);
  if (dl_result)
    {
      /* best effort case, try to dlopen the .so where our on exit hook code resides.
       * This is done in order to prevent it from ever being unloaded once the .so usage 
       * reference counter drops to zero. This dlopen will incrament it (we never close it)
       * thus never allowing an unload to occur */
      dlopen (dl_info.dli_fname, RTLD_LAZY);
    }
  on_exit (linux_on_exit_hook, NULL);
}
#endif

static void
toAvoidCompilerWarnings (void)
{
  /* As stated. This is a common file for Unix platforms.
     The alternative is to change all makefile generators */
  return;
}
