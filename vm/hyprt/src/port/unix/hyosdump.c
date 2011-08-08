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
 * @brief Dump formatting
 */

#undef CDEV_CURRENT_FUNCTION

#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#if defined(LINUX)
#include <sys/mman.h>
#endif

#include "hyport.h"

#include <signal.h>

#define CDEV_CURRENT_FUNCTION _prototypes_private

static void markAllPagesWritable (struct HyPortLibrary *portLibrary);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION appendCoreName

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hydump_create
/**
 * Create a dump file of the OS state.
 *
 * @param[in] portLibrary The port library.
 * @param[in] filename Buffer for filename optionally containing the filename where dump is to be output.
 * @param[out] filename filename used for dump file or error message.
 * @param[in] dumpType Type of dump to perform.
 * @param[in] userData Implementation specific data.
 *
 * @return 0 on success, non-zero otherwise.
 *
 * @note filename buffer can not be NULL.
 * @note user allocates and frees filename buffer.
 * @note filename buffer length is platform dependent, assumed to be HyMaxPath/MAX_PATH
 *
 * @note if filename buffer is empty, a filename will be generated.
 * @note if HYUNIQUE_DUMPS is set, filename will be unique.
 */
UDATA VMCALL
hydump_create (struct HyPortLibrary *portLibrary, char *filename,
               char *dumpType, void *userData)
{

  char *lastSep = filename ? strrchr (filename, DIR_SEPARATOR) : NULL;
  IDATA pid;

  /* dump a core file */
  if ((pid = fork ()) == 0)
    {

      /* Move to specified folder before dumping? */
      if (lastSep != NULL)
        {
          lastSep[1] = '\0';
          if (0 != chdir (filename))
            {
              return -1;
            }
        }

      /* Ensure we get default action (core) - reset primary&app handlers */
      signal (SIGABRT, SIG_DFL);

#if defined(LINUX)
      /* on Linux, shared library pages don't appear in core files by default. Mark 
       * all pages writable to force these pages to appear 
       */
      markAllPagesWritable (portLibrary);
#endif

      abort ();

    }

  portLibrary->tty_err_printf (portLibrary,
                               "Note: dump may be truncated if \"ulimit -c\" is set too low\n");

  /* guess typical filename */
  if (lastSep != NULL)
    {
      lastSep[1] = '\0';
      strcat (filename, "{default OS core name}");
    }
  else if (filename != NULL)
    {
      strcpy (filename, "{default OS core name}");
    }

  return 0;

}

#undef CDEV_CURRENT_FUNCTION

#if defined(LINUX)
#define CDEV_CURRENT_FUNCTION markAllPagesWritable
/**
 * @internal  read /proc/self/maps to determine what pages are mapped for this process.
 * Modify each page to be writable. This will cause it to be included in core dumps.
 *
 * The format of /proc/self/maps is:
 *   40000000-40013000 r-xp 00000000 03:03 1161002    /lib/ld-2.2.5.so
 *   40013000-40014000 rw-p 00013000 03:03 1161002    /lib/ld-2.2.5.so
 *   40014000-40016000 rw-p 00000000 00:00 0
 * ...
 *
 * This implementation expects the format to be strictly conformed to.
 * If deviations in the format are discovered, we'll have to make this routine 
 * more robust.
 */
static void
markAllPagesWritable (struct HyPortLibrary *portLibrary)
{
  I_32 fd =
    portLibrary->file_open (portLibrary, "/proc/self/maps", HyOpenRead, 0);
  if (fd != -1)
    {
      /* sizeof(void*) * 2 is the number of hex digits to represent a pointer */
      char buf[sizeof (void *) * 4 + sizeof ("-")];
      while (portLibrary->
             file_read (portLibrary, fd, buf,
                        sizeof (buf) - 1) == sizeof (buf) - 1)
        {
          char *next;
          U_8 *start, *end;
          int rc;
          /* we've now read "40000000-40013000 " into buf */
          /* NUL terminate buf for extra safety */
          buf[sizeof (buf) - 1] = '\0';
          /* use strtoull for correctness on 64-bit platforms and gratuitous extra precision on 32-bit platforms */
          start = (U_8 *) (UDATA) strtoull (buf, &next, 16);
          /* skip the '-' */
          next += 1;
          end = (U_8 *) (UDATA) strtoull (next, NULL, 16);
          /* mark the pages as writable (and readable and executable, just in case they already were) */
          rc =
            mprotect (start, end - start, PROT_READ | PROT_WRITE | PROT_EXEC);
          /* skip to the next line */
          while ((portLibrary->file_read (portLibrary, fd, buf, 1) == 1)
                 && buf[0] != '\n');
        }
      portLibrary->file_close (portLibrary, fd);
    }
}

#undef CDEV_CURRENT_FUNCTION
#endif

#define CDEV_CURRENT_FUNCTION

#undef CDEV_CURRENT_FUNCTION
