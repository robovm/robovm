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
 * @brief System information
 */

#undef CDEV_CURRENT_FUNCTION

#include <stdlib.h>
#include <sys/utsname.h>

#include <sys/stat.h>
#include <limits.h>
#include <dirent.h>
#include <errno.h>
#include <string.h>
#include <pwd.h>
#include <sys/types.h>

#if defined(LINUX)
#include <sys/sysinfo.h>
#endif
#if defined(FREEBSD) || defined(DARWIN)
#include <sys/types.h>
#include <sys/sysctl.h>
#include <dlfcn.h>
#endif

#include <unistd.h>

#include "portpriv.h"
#include "hyportpg.h"

#if defined(ZOS)
#include <sys/ps.h>
#include <sys/types.h>
#include "atoe.h"

#if !defined(PATH_MAX) 
/* This is a somewhat arbitrarily selected fixed buffer size. */
#define PATH_MAX 1024
#endif

#endif

#define CDEV_CURRENT_FUNCTION _prototypes_private
#if !defined(FREEBSD)
static IDATA readSymbolicLink (struct HyPortLibrary *portLibrary,
                               char *linkFilename, char **result);
#if !defined(LINUX)
static BOOLEAN isSymbolicLink (struct HyPortLibrary *portLibrary,
                               char *filename);
static IDATA cwdname (struct HyPortLibrary *portLibrary, char **result);
static IDATA searchSystemPath (struct HyPortLibrary *portLibrary,
                               char *filename, char **result);
#endif
#endif

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION _prototypes_public
IDATA VMCALL hysysinfo_get_executable_name (struct HyPortLibrary *portLibrary,
                                            char *argv0, char **result);
const char *VMCALL hysysinfo_get_OS_type (struct HyPortLibrary *portLibrary);
U_64 VMCALL hysysinfo_get_physical_memory (struct HyPortLibrary *portLibrary);
UDATA VMCALL hysysinfo_DLPAR_max_CPUs (struct HyPortLibrary *portLibrary);
UDATA VMCALL hysysinfo_get_number_CPUs (struct HyPortLibrary *portLibrary);
UDATA VMCALL hysysinfo_get_processing_capacity (struct HyPortLibrary
                                                *portLibrary);
const char *VMCALL hysysinfo_get_CPU_architecture (struct HyPortLibrary
                                                   *portLibrary);
const char *VMCALL hysysinfo_get_OS_version (struct HyPortLibrary
                                             *portLibrary);
I_32 VMCALL hysysinfo_startup (struct HyPortLibrary *portLibrary);
UDATA VMCALL hysysinfo_DLPAR_enabled (struct HyPortLibrary *portLibrary);
void VMCALL hysysinfo_shutdown (struct HyPortLibrary *portLibrary);
UDATA VMCALL hysysinfo_get_pid (struct HyPortLibrary *portLibrary);
U_16 VMCALL hysysinfo_get_classpathSeparator (struct HyPortLibrary
                                              *portLibrary);
IDATA VMCALL hysysinfo_get_username (struct HyPortLibrary *portLibrary,
                                     char *buffer, UDATA length);
UDATA VMCALL hysysinfo_weak_memory_consistency (struct HyPortLibrary
                                                *portLibrary);
IDATA VMCALL hysysinfo_get_env (struct HyPortLibrary *portLibrary,
                                char *envVar, char *infoString,
                                UDATA bufSize);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_CPU_architecture
/**
 * Determine the CPU architecture.
 *
 * @param[in] portLibrary The port library.
 *
 * @return A null-terminated string describing the CPU architecture of the hardware, NULL on error.
 * 
 * @note portLibrary is responsible for allocation/deallocation of returned buffer.
 * @note See http://www.tolstoy.com/samizdat/sysprops.html for good values to return.
 */
const char *VMCALL
hysysinfo_get_CPU_architecture (struct HyPortLibrary *portLibrary)
{
#if defined(HYPPC32)
  return HYPORT_ARCH_PPC;
#elif defined(HYPPC64)
  return HYPORT_ARCH_PPC64;
#elif defined(HYS390)
  return HYPORT_ARCH_S390;
#elif defined(HYS390X)
  return HYPORT_ARCH_S390X;
#elif defined(HYX86)
  return HYPORT_ARCH_X86;
#elif defined(HYX86_64)
  return HYPORT_ARCH_X86_64;
#elif defined(ARMGNU)
  return HYPORT_ARCH_ARM;
#else
  return "unknown";
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_env
/**
 * Query the operating system for environment variables.
 *
 * Obtain the value of the environment variable specified by envVar from the operating system 
 * and write out to supplied buffer.
 *
 * @param[in] portLibrary The port library.
 * @param[in] envVar Environment variable to query.
 * @param[out] infoString Buffer for value string describing envVar.
 * @param[in] bufSize Size of buffer to hold returned string.
 *
 * @return 0 on success, number of bytes required to hold the 
 *	information if the output buffer was too small, -1 on failure.
 *
 * @note infoString is undefined on error or when supplied buffer was too small.
 */
IDATA VMCALL
hysysinfo_get_env (struct HyPortLibrary * portLibrary, char *envVar,
                   char *infoString, UDATA bufSize)
{
  char *value = (char *) getenv (envVar);
  UDATA len;

  if (NULL == value)
    {
      return -1;
    }
  else if ((len = strlen (value)) >= bufSize)
    {
      return len + 1;
    }
  else
    {
      strcpy (infoString, value);
      return 0;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_OS_type
/**
 * Determine the OS type.
 * 
 * @param[in] portLibrary The port library.
 *
 * @return OS type string (NULL terminated) on success, NULL on error.
 *
 * @note portLibrary is responsible for allocation/deallocation of returned buffer.
 */
const char *VMCALL
hysysinfo_get_OS_type (struct HyPortLibrary *portLibrary)
{

  if (NULL == PPG_si_osType)
    {
      int rc;
      int len;
      char *buffer;
      struct utsname sysinfo;

#if !defined(ZOS)
      rc = uname (&sysinfo);
#else /* !defined(ZOS) */
      rc = __osname(&sysinfo);
#endif /* !defined(ZOS) */

      if (rc >= 0)
        {
          len = strlen (sysinfo.sysname) + 1;
          buffer = portLibrary->mem_allocate_memory (portLibrary, len);
          if (NULL == buffer)
            {
              return NULL;
            }
          /* copy and null terminte (just in case) */
          strncpy (buffer, sysinfo.sysname, len - 1);
          buffer[len - 1] = '\0';
          PPG_si_osType = buffer;
        }
    }
  return PPG_si_osType;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_OS_version
/**
 * Determine version information from the operating system.
 *
 * @param[in] portLibrary The port library.
 *
 * @return OS version string (NULL terminated) on success, NULL on error.
 *
 * @note portLibrary is responsible for allocation/deallocation of returned buffer.
 */
const char *VMCALL
hysysinfo_get_OS_version (struct HyPortLibrary *portLibrary)
{

  if (NULL == PPG_si_osVersion)
    {
      int rc;
      struct utsname sysinfo;

#if !defined(ZOS)
      rc = uname (&sysinfo);
#else /* !defined(ZOS) */
      rc = __osname(&sysinfo);
#endif /* !defined(ZOS) */

      if (rc >= 0)
        {
          int len;
          char *buffer;

#if !defined(ZOS)
          len = strlen (sysinfo.release) + 1;
          buffer = portLibrary->mem_allocate_memory (portLibrary, len);
          if (NULL == buffer)
            {
              return NULL;
            }
          strncpy (buffer, sysinfo.release, len);
          buffer[len - 1] = '\0';
#else /* !defined(ZOS) */
			len = strlen(sysinfo.version) + strlen(sysinfo.release) + 2; /* "." and terminating null character */
			buffer = portLibrary->mem_allocate_memory(portLibrary, len);
			if (NULL == buffer) {
				return NULL;
			}
			sprintf(buffer, "%s.%s", sysinfo.version, sysinfo.release);
#endif /* !defined(ZOS) */

          PPG_si_osVersion = buffer;
        }
    }
  return PPG_si_osVersion;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_pid
/**
 * Determine the process ID of the calling process.
 *
 * @param[in] portLibrary The port library.
 *
 * @return the PID.
 */
UDATA VMCALL
hysysinfo_get_pid (struct HyPortLibrary * portLibrary)
{

  return getpid ();

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_classpathSeparator
/**
 * Determine the character used to separate entries on the classpath.
 *
 * @param[in] portLibrary The port library.
 *
 * @return the classpathSeparator character.
 */
U_16 VMCALL
hysysinfo_get_classpathSeparator (struct HyPortLibrary * portLibrary)
{
  return ':';
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_executable_name
/**
 * Determines an absolute pathname for the executable.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] argv0 argv[0] value
 * @param[out] result Null terminated pathname string
 * 
 * @return 0 on success, -1 on error (or information is not available).
 *
 * @note Caller is responsible for de-allocating memory for result buffer with @ref hymem_free_memory.
 */
IDATA VMCALL
hysysinfo_get_executable_name (struct HyPortLibrary * portLibrary,
                               char *argv0, char **result)
{
#if defined(LINUX)
  return readSymbolicLink (portLibrary, "/proc/self/exe", result);
#else
#if defined(FREEBSD)
  extern int main (int argc, char **argv, char **envp);
  Dl_info info;
  if (dladdr( (const void*)&main, &info) == 0) {
    return -1;
  }
  *result =
    (portLibrary->mem_allocate_memory) (portLibrary,
                                        strlen (info.dli_fname) + 1);

  if (!*result) {
    return -1;
  }
  strcpy (*result, info.dli_fname);
  return 0;
  
#else
  IDATA retval = -1;
  IDATA length;
  char *p;
  char *currentName = NULL;
  char *currentPath = NULL;
  char *originalWorkingDirectory = NULL;

#if !defined(ZOS)
  if (!argv0)
    {
      return -1;
    }
  currentPath =
    (portLibrary->mem_allocate_memory) (portLibrary, strlen (argv0) + 1);
  if (currentPath)
    {
      strcpy (currentPath, argv0);
    }
#else /* !defined(ZOS) */
    char *e2aName = NULL;
	int token = 0;
	W_PSPROC buf;
	pid_t mypid = getpid();

	memset(&buf, 0x00, sizeof(buf));
	buf.ps_pathptr   = portLibrary->mem_allocate_memory(portLibrary, buf.ps_pathlen = PS_PATHBLEN);
	if (buf.ps_pathptr   == NULL) {
		retval = -1;
		goto cleanup;
	}
	while ((token = w_getpsent(token, &buf, sizeof(buf))) > 0) {
		if (buf.ps_pid == mypid) {
            e2aName = e2a_func(buf.ps_pathptr, strlen(buf.ps_pathptr)+1);
			break;
		}
	}

    /* Return val of w_getpsent == -1 indicates error, == 0 indicates no more processes */
	if (token <= 0) {
		retval = -1;
		goto cleanup;
	}

	currentPath = (portLibrary->mem_allocate_memory) (portLibrary, strlen(e2aName) + 1);
	if (currentPath) {
		strcpy(currentPath, e2aName);
	}
    portLibrary->mem_free_memory(portLibrary, buf.ps_pathptr);
    free(e2aName);
#endif /* !defined(ZOS) */

  if (!currentPath)
    {
      retval = -1;
      goto cleanup;
    }
  retval = cwdname (portLibrary, &originalWorkingDirectory);
  if (retval)
    {
      retval = -1;
      goto cleanup;
    }
gotPathName:
  /* split path into directory part and filename part. */
  p = strrchr (currentPath, '/');
  if (p)
    {
      *p++ = '\0';
      currentName =
        (portLibrary->mem_allocate_memory) (portLibrary, strlen (p) + 1);
      if (!currentName)
        {
          retval = -1;
          goto cleanup;
        }
      strcpy (currentName, p);
    }
  else
    {
      currentName = currentPath;
      currentPath = NULL;
      retval = searchSystemPath (portLibrary, currentName, &currentPath);
      if (retval)
        {
          retval = -1;
          goto cleanup;
        }
    }
  /* go there */
  if (currentPath)
    {
      if (currentPath[0])
        {
          if (0 != chdir (currentPath))
            {
              retval = -1;
              goto cleanup;
            }
        }
      (portLibrary->mem_free_memory) (portLibrary, currentPath);
      currentPath = NULL;
    }
  if (isSymbolicLink (portLibrary, currentName))
    {
      /* try to follow the link. */
      retval = readSymbolicLink (portLibrary, currentName, &currentPath);
#if defined(DEBUG)
      (portLibrary->tty_printf) (portLibrary, "read cp=%s\n", currentPath);
#endif
      if (retval)
        {
          retval = -1;
          goto cleanup;
        }
      (portLibrary->mem_free_memory) (portLibrary, currentName);
      currentName = NULL;
      goto gotPathName;
    }
  retval = cwdname (portLibrary, &currentPath);
  if (retval)
    {
      retval = -1;
      goto cleanup;
    }
  /* Put name and path back together */
  *result =
    (portLibrary->mem_allocate_memory) (portLibrary,
                                        strlen (currentPath) +
                                        strlen (currentName) + 2);
  if (!*result)
    {
      retval = -1;
      goto cleanup;
    }
  strcpy (*result, currentPath);
  if (currentPath[0] && (currentPath[strlen (currentPath) - 1] != '/'))
    {
      strcat (*result, "/");
    }
  strcat (*result, currentName);
  /* Finished. */
  retval = 0;
cleanup:
  if (originalWorkingDirectory)
    {
      chdir (originalWorkingDirectory);
      (portLibrary->mem_free_memory) (portLibrary, originalWorkingDirectory);
      originalWorkingDirectory = NULL;
    }
  if (currentPath)
    {
      (portLibrary->mem_free_memory) (portLibrary, currentPath);
      currentPath = NULL;
    }
  if (currentName)
    {
      (portLibrary->mem_free_memory) (portLibrary, currentName);
      currentName = NULL;
    }
  return retval;
#endif
#endif

}

#undef CDEV_CURRENT_FUNCTION

#if !defined(FREEBSD)
#define CDEV_CURRENT_FUNCTION readSymbolicLink
/**
 * @internal  Attempts to read the contents of a symbolic link.  (The contents are the relative pathname of
 * the thing linked to).  A buffer large enough to hold the result (and the terminating NUL) is
 * allocated with portLibrary->mem_allocate_memory.  The caller should free this buffer with
 * portLibrary->mem_free_memory when it is no longer needed.
 * On success, returns 0.  On error, returns -1.
 */
static IDATA
readSymbolicLink (struct HyPortLibrary *portLibrary, char *linkFilename,
                  char **result)
{
  /* TODO: remove this ifdef and find out what other builds break (if any) */
#if defined(LINUX)
  char fixedBuffer[PATH_MAX + 1];
  int size = readlink (linkFilename, fixedBuffer, sizeof (fixedBuffer) - 1);
#if defined(DEBUG)
  portLibrary->tty_printf (portLibrary, "readSymbolicLink: \"%s\"\n%i\n",
                           linkFilename, size);
#endif
  if (size <= 0)
    {
      return -1;
    }
  fixedBuffer[size++] = '\0';
  *result = (portLibrary->mem_allocate_memory) (portLibrary, size);
  if (!*result)
    {
      return -1;
    }
  strcpy (*result, fixedBuffer);
  return 0;
#else
  return -1;
#endif

}

#undef CDEV_CURRENT_FUNCTION

#if !defined(LINUX)
#define CDEV_CURRENT_FUNCTION cwdname
/**
 * @internal  Returns the current working directory.  
 *
 * @return 0 on success, -1 on failure.
 *
 * @note The buffer to hold this string (including its terminating NUL) is allocated with 
 * portLibrary->mem_allocate_memory.  The caller should free this memory with 
 * portLibrary->mem_free_memory when it is no longer needed. 
 */
static IDATA
cwdname (struct HyPortLibrary *portLibrary, char **result)
{
  char *cwd;
  int allocSize = 256;

doAlloc:
  cwd = (portLibrary->mem_allocate_memory) (portLibrary, allocSize);
  if (!cwd)
    {
      return -1;
    }
  if (!getcwd (cwd, allocSize - 1))
    {
      (portLibrary->mem_free_memory) (portLibrary, cwd);
      if (errno == ERANGE)
        {
          allocSize += 256;
          goto doAlloc;
        }
      return -1;
    }
  *result = cwd;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION isSymbolicLink
/**
 * @internal  Examines the named file to determine if it is a symbolic link.  On platforms which don't have
 * symbolic links (or where we can't tell) or if an unexpected error occurs, just answer FALSE.
 */
static BOOLEAN
isSymbolicLink (struct HyPortLibrary *portLibrary, char *filename)
{
  struct stat statbuf;
#if defined(DEBUG)
  portLibrary->tty_printf (portLibrary, "isSymbolicLink(\"%s\")\n", filename);
#endif

  if (!lstat (filename, &statbuf))
    {
      if (S_ISLNK (statbuf.st_mode))
        {
#if defined(DEBUG)
          portLibrary->tty_printf (portLibrary, "TRUE\n");
#endif

          return TRUE;
        }
    }
#if defined(DEBUG)
  portLibrary->tty_printf (portLibrary, "FALSE\n");
#endif

  return FALSE;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION searchSystemPath
/**
 * @internal  Searches through the system PATH for the named file.  If found, it returns the path entry
 * which matched the file.  A buffer large enough to hold the proper path entry (without a
 * trailing slash, but with the terminating NUL) is allocated with portLibrary->mem_allocate_memory.
 * The caller should free this buffer with portLibrary->mem_free_memory when it is no longer
 * needed.  On success, returns 0.  On error (including if the file is not found), -1 is returned.
 */
static IDATA
searchSystemPath (struct HyPortLibrary *portLibrary, char *filename,
                  char **result)
{
  char *pathCurrent;
  char *pathNext;
  int length;
  DIR *sdir = NULL;
  struct dirent *dirEntry;
  /* This should be sufficient for a single entry in the PATH var, though the var itself */
  /* could be considerably longer.. */
  char temp[PATH_MAX + 1];

  /* We use getenv() instead of the portLibrary function because getenv() doesn't require */
  /* us to allocate any memory, or guess how much to allocate. */
  if (!(pathNext = getenv ("PATH")))
    {
      return -1;
    }

  while (pathNext)
    {
      pathCurrent = pathNext;
      pathNext = strchr (pathCurrent, ':');
      if (pathNext)
        {
          length = (pathNext - pathCurrent);
          pathNext += 1;
        }
      else
        {
          length = strlen (pathCurrent);
        }
      if (length > PATH_MAX)
        {
          length = PATH_MAX;
        }
      memcpy (temp, pathCurrent, length);
      temp[length] = '\0';
#if defined(DEBUG)
      (portLibrary->tty_printf) (portLibrary, "Searching path: \"%s\"\n",
                                 temp);
#endif

      if (!length)
        {                       /* empty path entry */
          continue;
        }
      if (sdir = opendir (temp))
        {
          while (dirEntry = readdir (sdir))
            {
#if defined(DEBUG)
              (portLibrary->tty_printf) (portLibrary, "dirent: \"%s\"\n",
                                         dirEntry->d_name);
#endif

              if (!strcmp (dirEntry->d_name, filename))
                {
                  closedir (sdir);
                  /* found! */
                  *result =
                    (portLibrary->mem_allocate_memory) (portLibrary,
                                                        strlen (temp) + 1);
                  if (!result)
                    {
                      return -1;
                    }
                  strcpy (*result, temp);
                  return 0;
                }
            }
          closedir (sdir);
        }
    }
  /* not found */
  return -1;
}

#undef CDEV_CURRENT_FUNCTION
#endif
#endif

#define CDEV_CURRENT_FUNCTION hysysinfo_get_number_CPUs
/**
 * Determine the number of CPUs on this platform.
 *
 * @param[in] portLibrary The port library.
 *
 * @return The number of supported CPUs.
 */
UDATA VMCALL
hysysinfo_get_number_CPUs (struct HyPortLibrary * portLibrary)
{
#if defined(LINUX) || defined(FREEBSD)
  /* returns number of online(_SC_NPROCESSORS_ONLN) processors, number configured(_SC_NPROCESSORS_CONF) may  be more than online */
  return sysconf (_SC_NPROCESSORS_ONLN);
#elif defined(DARWIN)
  /* derived from examples in the sysctl(3) man page from FreeBSD */
  int mib[2], ncpu;
  size_t len;

  mib[0] = CTL_HW;
  mib[1] = HW_NCPU;
  len = sizeof(ncpu);
  sysctl(mib, 2, &ncpu, &len, NULL, 0);
  return (UDATA)ncpu;

#else
  return 0;
#endif
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_physical_memory
/**
 * Determine the size of the total physical memory in the system, in bytes.
 * 
 * @param[in] portLibrary The port library.
 *
 * @return 0 if the information was unavailable, otherwise total physical memory in bytes.
 */
U_64 VMCALL
hysysinfo_get_physical_memory (struct HyPortLibrary * portLibrary)
{

#if defined(FREEBSD) || defined(DARWIN)
  /* derived from examples in the sysctl(3) man page from FreeBSD */
  int mib[2], mem;
  size_t len;

  mib[0] = CTL_HW;
  mib[1] = HW_PHYSMEM;
  len = sizeof(mem);
  sysctl(mib, 2, &mem, &len, NULL, 0);
  return (U_64)mem;

#elif defined(ZOS)
	/* TODO: implement. Currently this function is unused. */
	return 0;
#else
  IDATA pagesize, num_pages;

  pagesize = sysconf (_SC_PAGESIZE);
  num_pages = sysconf (_SC_PHYS_PAGES);

  if (pagesize == -1 || num_pages == -1)
    {
      return 0;
    }
  else
    {
      return (U_64) pagesize *num_pages;
    }
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_DLPAR_enabled
/**
 * Determine if DLPAR (i.e. the ability to change number of CPUs and amount of memory dynamically)
 * is enabled on this platform.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 1 if DLPAR is supported, otherwise 0.
 */
UDATA VMCALL
hysysinfo_DLPAR_enabled (struct HyPortLibrary * portLibrary)
{

  return FALSE;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_weak_memory_consistency
/**
 * Determine if the platform has weak memory consistency behaviour.
 * 
 * @param[in] portLibrary The port library.
 *
 * @return 1 if weak memory consistency, 0 otherwise.
 */
UDATA VMCALL
hysysinfo_weak_memory_consistency (struct HyPortLibrary * portLibrary)
{
  return FALSE;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hysysinfo_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hysysinfo_shutdown (struct HyPortLibrary *portLibrary)
{
  if (PPG_si_osVersion)
    {
      portLibrary->mem_free_memory (portLibrary, PPG_si_osVersion);
      PPG_si_osVersion = NULL;
    }

  if (PPG_si_osType)
    {
      portLibrary->mem_free_memory (portLibrary, PPG_si_osType);
      PPG_si_osType = NULL;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the system information operations may be created here.  All resources created here should be destroyed
 * in @ref hysysinfo_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_SYSINFO
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hysysinfo_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_DLPAR_max_CPUs
/**
 * Determine the maximum number of CPUs on this platform
 *
 * @param[in] portLibrary The port library.
 *
 * @return The maximum number of supported CPUs..
 */
UDATA VMCALL
hysysinfo_DLPAR_max_CPUs (struct HyPortLibrary * portLibrary)
{

  return portLibrary->sysinfo_get_number_CPUs (portLibrary);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_username
/**
 * Query the operating system for the name of the user associate with the current thread
 * 
 * Obtain the value of the name of the user associated with the current thread, and then write it out into the buffer
* supplied by the user
*
* @param[in] portLibrary The port Library
* @param[out] buffer Buffer for the name of the user
* @param[in,out] length The length of the buffer
*
* @return 0 on success, number of bytes required to hold the 
* information if the output buffer was too small, -1 on failure.
*
* @note buffer is undefined on error or when supplied buffer was too small.
*/
IDATA VMCALL
hysysinfo_get_username (struct HyPortLibrary * portLibrary, char *buffer,
                        UDATA length)
{
  char *remoteCopy = NULL;
#if defined(ZOS)
  char *loginID = getlogin();
  if (NULL != loginID) {
    struct passwd *userDescription = getpwnam(loginID);
    if (NULL != userDescription) {
      remoteCopy = userDescription->pw_name;
    }
  }
	/* there exist situations where one of the above calls will fail.  Fall through to the Unix solution for those cases */
#endif

  if (NULL == remoteCopy) {
  uid_t uid = getuid ();
  struct passwd *pwent = getpwuid (uid);

    if (pwent != NULL)
    {
      remoteCopy = pwent->pw_name;
    }
  }
  if (NULL == remoteCopy) {
    return -1;
  } else {
    size_t nameLen = strlen (remoteCopy);

  if ((nameLen + 1) > length)
    {
      return nameLen + 1;
    }

    portLibrary->str_printf (portLibrary, buffer, length, "%s", remoteCopy);
  }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysysinfo_get_processing_capacity
/**
 * Determine the collective processing capacity available to the VM
 * in units of 1% of a physical processor. In environments without
 * some kind of virtual partitioning, this will simply be the number
 * of CPUs * 100.
 *
 * @param[in] portLibrary The port library.
 *
 * @return The processing capacity available to the VM.
 */
UDATA VMCALL
hysysinfo_get_processing_capacity (struct HyPortLibrary * portLibrary)
{

  return portLibrary->sysinfo_get_number_CPUs (portLibrary) * 100;

}

#undef CDEV_CURRENT_FUNCTION
