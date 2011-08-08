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

#include "hysharedhelper.h"
#include "hyport.h"
#include "portpriv.h"
#include "ut_hyprt.h"
#include <sys/stat.h>
#include <string.h>

#define SUCCESS 0
#define FAILED -1

#if !defined(ZOS)
#define __errno2() 0
#endif

static void changeDirectoryPermission (struct HyPortLibrary *portLibrary,
                                       const char *pathname);
static IDATA createDirectory (struct HyPortLibrary *portLibrary,
                              char *pathname);

/**
 * @internal
 * @brief Shared Semaphore and Shared Memory
 *
 * This function ensure the directory which used to store shared classes file exists.
 * 
 * @param[in] portLibrary The port library
 * @return 0 for success, -1 for failure.
 *
 * @note Currently, we have fixed the base directory to be a macro in portpriv.h (HYSH_BASEDIR) 
 *	This should be common across both shared semaphore and shared memory modules.
 */

IDATA VMCALL
ensureDirectory (struct HyPortLibrary *portLibrary)
{
  I_32 rc;

  Trc_PRT_shared_ensureDirectory_Entry (HYSH_BASEDIR);

  rc = portLibrary->file_attr (portLibrary, HYSH_BASEDIR);
  switch (rc)
    {
    case HyIsFile:
      Trc_PRT_shared_ensureDirectory_Event1 ();
      break;
    case HyIsDir:
      changeDirectoryPermission (portLibrary, HYSH_BASEDIR);
      return SUCCESS;
    default:                   /* Directory is not there */
      if (FAILED != createDirectory (portLibrary, HYSH_BASEDIR))
        {
          changeDirectoryPermission (portLibrary, HYSH_BASEDIR);
          return SUCCESS;
        }
    }

  Trc_PRT_shared_ensureDirectory_Event2 (errno);
  return FAILED;
}
static void
changeDirectoryPermission (struct HyPortLibrary *portLibrary,
                           const char *pathname)
{
  if (-1 == chmod (pathname, HYSH_DIRPERM))
    {
      Trc_PRT_shared_changeDirectoryPermission_Event1 (errno);
    }
}
static IDATA
createDirectory (struct HyPortLibrary *portLibrary, char *pathname)
{

  char tempPath[HYSH_MAXPATH];
  char *current;

  if (0 == portLibrary->file_mkdir (portLibrary, pathname))
    {
      return SUCCESS;
    }
  else if (portLibrary->error_last_error_number (portLibrary) ==
           HYPORT_ERROR_FILE_EXIST)
    {
      return FAILED;
    }

  portLibrary->str_printf (portLibrary, tempPath, HYSH_MAXPATH, "%s",
                           pathname);

  current = strchr (tempPath + 1, DIR_SEPARATOR);       /* skip the first '/' */

  while (portLibrary->file_attr (portLibrary, pathname) != HyIsDir)
    {
      char *previous;

      *current = '\0';

#if defined(HYSHSEM_DEBUG)
      portLibrary->tty_printf (portLibrary, "mkdir %s\n", tempPath);
#endif

      if (-1 == portLibrary->file_mkdir (portLibrary, tempPath))
        {
          /* check to see whether the directory has already been created, if it is, it should
             return file exist error. If not we should return */
          if (portLibrary->error_last_error_number (portLibrary) !=
              HYPORT_ERROR_FILE_EXIST)
            {
              return FAILED;
            }
        }

      previous = current;
      current = strchr (current + 1, DIR_SEPARATOR);
      *previous = DIR_SEPARATOR;
    }

  return SUCCESS;
}
