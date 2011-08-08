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

/**
 * @file
 * @ingroup Port
 * @brief Port Library
 */
#include <string.h>
#include "hyport.h"
#include "portpriv.h"
#include "hyportpg.h"
#ifdef ZOS
#include "atoe.h"
#endif

/**
 * Initialize the port library.
 * 
 * Given a pointer to a port library and the required version,
 * populate the port library table with the appropriate functions
 * and then call the startup function for the port library.
 * 
 * @param[in] portLibrary The port library.
 * @param[in] version The required version of the port library.
 * @param[in] size Size of the port library.
 *
 * @return 0 on success, negative return value on failure
 */
I_32 VMCALL
hyport_init_library (struct HyPortLibrary *portLibrary,
                     struct HyPortLibraryVersion *version, UDATA size)
{
  /* return value of 0 is success */
  I_32 rc;

#if defined(ZOS)
  /* Initialise the ascii2ebcdic functions if it has not already been done */
  rc = iconv_init();

  if (rc ==0)
#endif
  {
  rc = hyport_create_library (portLibrary, version, size);
  }
  
  if (rc == 0)
    {
      rc = hyport_startup_library (portLibrary);
    }

  if (rc == 0)
    {
      // try and initialise the nls data before any nls_* calls
      initNLSCatalog(portLibrary);
    }
  return rc;
}

/**
 * PortLibrary shutdown.
 *
 * Shutdown the port library, de-allocate resources required by the components of the portlibrary.
 * Any resources that werer created by @ref hyport_startup_library should be destroyed here.
 *
 * @param[in] portLibrary The portlibrary.
 *
 * @return 0 on success, negative return code on failure
 */
I_32 VMCALL
hyport_shutdown_library (struct HyPortLibrary * portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);

#endif /* HY_NO_THR */
  portLibrary->shmem_shutdown (portLibrary);
  portLibrary->shsem_shutdown (portLibrary);

  portLibrary->str_shutdown (portLibrary);
  portLibrary->sl_shutdown (portLibrary);
  portLibrary->sysinfo_shutdown (portLibrary);
  portLibrary->exit_shutdown (portLibrary);
  portLibrary->gp_shutdown (portLibrary);
  portLibrary->time_shutdown (portLibrary);

  /* Shutdown the socket library before the tty, so it can write to the tty if required */
  portLibrary->sock_shutdown (portLibrary);

  portLibrary->nls_shutdown (portLibrary);
  portLibrary->ipcmutex_shutdown (portLibrary);
  portLibrary->mmap_shutdown (portLibrary);

  portLibrary->tty_shutdown (portLibrary);
  portLibrary->file_shutdown (portLibrary);

  portLibrary->vmem_shutdown (portLibrary);
  portLibrary->cpu_shutdown (portLibrary);
  portLibrary->error_shutdown (portLibrary);
  hyport_tls_shutdown (portLibrary);
  portLibrary->mem_shutdown (portLibrary);

  hythread_detach (portLibrary->attached_thread);

  /* Last thing to do.  If this port library was self allocated free this memory */
  if (NULL != portLibrary->self_handle)
    {
      hymem_deallocate_portLibrary (portLibrary);
    }

  return (I_32) 0;
}

/**
 * Create the port library.
 * 
 * Given a pointer to a port library and the required version,
 * populate the port library table with the appropriate functions
 * 
 * @param[in] portLibrary The port library.
 * @param[in] version The required version of the port library.
 * @param[in] size Size of the port library.
 *
 * @return 0 on success, negative return value on failure
 * @note The portlibrary version must be compatabile with the that which we are compiled against
 */
I_32 VMCALL
hyport_create_library (struct HyPortLibrary * portLibrary,
                       struct HyPortLibraryVersion * version, UDATA size)
{
  UDATA versionSize = hyport_getSize (version);

  if (HYPORT_MAJOR_VERSION_NUMBER != version->majorVersionNumber)
    {
      return -1;
    }

  if (versionSize > size)
    {
      return -1;
    }

  /* Ensure required functionality is there */
  if ((version->capabilities & HYPORT_CAPABILITY_MASK) !=
      version->capabilities)
    {
      return -1;
    }

  /* Null and initialize the table passed in */
  memset (portLibrary, 0, size);
  memcpy (portLibrary, &MasterPortLibraryTable, versionSize);

  /* Reset capabilities to be what is actually there, not what was requested */
  portLibrary->portVersion.majorVersionNumber = version->majorVersionNumber;
  portLibrary->portVersion.minorVersionNumber = version->minorVersionNumber;
  portLibrary->portVersion.capabilities = HYPORT_CAPABILITY_MASK;

  return 0;
}

/**
 * PortLibrary startup.
 *
 * Start the port library, allocate resources required by the components of the portlibrary.
 * All resources created here should be destroyed in @ref hyport_shutdown_library.
 *
 * @param[in] portLibrary The portlibrary.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_THREAD
 * \arg HYPORT_ERROR_STARTUP_MEM
 * \arg HYPORT_ERROR_STARTUP_TLS
 * \arg HYPORT_ERROR_STARTUP_TLS_ALLOC
 * \arg HYPORT_ERROR_STARTUP_TLS_MUTEX
 * \arg HYPORT_ERROR_STARTUP_ERROR
 * \arg HYPORT_ERROR_STARTUP_CPU
 * \arg HYPORT_ERROR_STARTUP_VMEM
 * \arg HYPORT_ERROR_STARTUP_FILE
 * \arg HYPORT_ERROR_STARTUP_TTY
 * \arg HYPORT_ERROR_STARTUP_TTY_HANDLE
 * \arg HYPORT_ERROR_STARTUP_TTY_CONSOLE
 * \arg HYPORT_ERROR_STARTUP_MMAP
 * \arg HYPORT_ERROR_STARTUP_IPCMUTEX
 * \arg HYPORT_ERROR_STARTUP_NLS
 * \arg HYPORT_ERROR_STARTUP_SOCK
 * \arg HYPORT_ERROR_STARTUP_TIME
 * \arg HYPORT_ERROR_STARTUP_GP
 * \arg HYPORT_ERROR_STARTUP_EXIT
 * \arg HYPORT_ERROR_STARTUP_SYSINFO
 * \arg HYPORT_ERROR_STARTUP_SL
 * \arg HYPORT_ERROR_STARTUP_STR
 * \arg HYPORT_ERROR_STARTUP_SHSEM
 * \arg HYPORT_ERROR_STARTUP_SHMEM
 * \arg HYPORT_ERROR_STARTUP_SIGNAL
 *
 * @note The port library memory is deallocated if it was created by @ref hyport_allocate_library
 */
I_32 VMCALL
hyport_startup_library (struct HyPortLibrary * portLibrary)
{
  I_32 rc = 0;
#ifdef HY_NO_THR
  HyThreadLibrary *hyThreadLibrary;
  HyThreadLibraryVersion hyThreadLibraryVersion;
  
  HYTHREAD_SET_VERSION(&hyThreadLibraryVersion, HYTHREAD_CAPABILITY_MASK);
  rc = hythread_allocate_library(&hyThreadLibraryVersion, &hyThreadLibrary);
  if ( 0 != rc ) {
  	 rc = HYPORT_ERROR_STARTUP_THREAD;
  	  goto cleanup;
    }

  rc = hythread_startup_library(hyThreadLibrary);
  if ( 0 != rc )
    {
  	  rc = HYPORT_ERROR_STARTUP_THREAD;
  	  goto cleanup;
    }
#endif /* HY_NO_THR */

  /* NLS uses the thread library */
#ifndef HY_NO_THR
  rc = hythread_attach (&portLibrary->attached_thread);
#else /* HY_NO_THR */
  rc = hyThreadLibrary->thread_attach (hyThreadLibrary, &portLibrary->attached_thread);
#endif /* HY_NO_THR */
  if (0 != rc)
    {
      /* Reassign return code as hythread_attach only returns -1 on error */
      rc = HYPORT_ERROR_STARTUP_THREAD;
      goto cleanup;
    }

  /* Must not access anything in portGlobals, as this allocates them */
  rc =
    portLibrary->mem_startup (portLibrary, sizeof (HyPortLibraryGlobalData));
  if (0 != rc)
    {
      goto cleanup;
    }

#ifdef HY_NO_THR
  /* Store threadLibrary in port globals */
  portLibrary->portGlobals->threadLibrary = hyThreadLibrary;
  
#endif /* HY_NO_THR */
  /* Create the tls buffers as early as possible */
  rc = hyport_tls_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  /* Start error handling as early as possible, require TLS buffers */
  rc = portLibrary->error_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->cpu_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->vmem_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->file_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->tty_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->mmap_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->ipcmutex_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->nls_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->sock_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->time_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->gp_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->exit_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->sysinfo_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->sl_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->str_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->shsem_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  rc = portLibrary->shmem_startup (portLibrary);
  if (0 != rc)
    {
      goto cleanup;
    }

  return rc;

cleanup:
  /* TODO: should call shutdown, but need to make all shutdown functions
   *  safe if the corresponding startup was not called.  No worse than the existing
   * code 
   */

  /* If this port library was self allocated free this memory */
  if (NULL != portLibrary->self_handle)
    {
      hymem_deallocate_portLibrary (portLibrary);
    }
  return rc;
}

/**
 * Determine the size of the port library.
 * 
 * Given a port library version, return the size of the structure in bytes
 * required to be allocated.
 * 
 * @param[in] version The HyPortLibraryVersion structure.
 *
 * @return size of port library on success, zero on failure
 *
 * @note The portlibrary version must be compatabile with the that which we are compiled against
 */
UDATA VMCALL
hyport_getSize (struct HyPortLibraryVersion * version)
{
  /* Can't initialize a structure that is not understood by this version of the port library       */
  if (HYPORT_MAJOR_VERSION_NUMBER != version->majorVersionNumber)
    {
      return 0;
    }

  /* The size of the portLibrary table is determined by the majorVersion number
   * and the presence/absense of the HYPORT_CAPABILITY_STANDARD capability 
   */
  if (0 != (version->capabilities & HYPORT_CAPABILITY_STANDARD))
    {
      return sizeof (HyPortLibrary);
    }
  else
    {
      return offsetof (HyPortLibrary, attached_thread) + sizeof (void *);
    }
}

/**
 * Determine the version of the port library.
 * 
 * Given a port library return the version of that instance.
 * 
 * @param[in] portLibrary The port library.
 * @param[in,out] version The HyPortLibraryVersion structure to be populated.
 *
 * @return 0 on success, negative return value on failure
 * @note If portLibrary is NULL, version is populated with the version in the linked DLL
 */
I_32 VMCALL
hyport_getVersion (struct HyPortLibrary *portLibrary,
                   struct HyPortLibraryVersion *version)
{
  if (NULL == version)
    {
      return -1;
    }

  if (portLibrary)
    {
      version->majorVersionNumber =
        portLibrary->portVersion.majorVersionNumber;
      version->minorVersionNumber =
        portLibrary->portVersion.minorVersionNumber;
      version->capabilities = portLibrary->portVersion.capabilities;
    }
  else
    {
      version->majorVersionNumber = HYPORT_MAJOR_VERSION_NUMBER;
      version->minorVersionNumber = HYPORT_MINOR_VERSION_NUMBER;
      version->capabilities = HYPORT_CAPABILITY_MASK;
    }

  return 0;
}

/**
 * Determine port library compatibility.
 * 
 * Given the minimum version of the port library that the application requires determine
 * if the current port library meets that requirements.
 *
 * @param[in] expectedVersion The version the application requires as a minimum.
 *
 * @return 1 if compatible, 0 if not compatible
 */
I_32 VMCALL
hyport_isCompatible (struct HyPortLibraryVersion * expectedVersion)
{
  /* Position of functions, signature of functions.
   * Major number incremented when existing functions change positions or signatures
   */
  if (HYPORT_MAJOR_VERSION_NUMBER != expectedVersion->majorVersionNumber)
    {
      return 0;
    }

  /* Size of table, it's ok to have more functions at end of table that are not used.
   * Minor number incremented when new functions added to the end of the table
   */
  if (HYPORT_MINOR_VERSION_NUMBER < expectedVersion->minorVersionNumber)
    {
      return 0;
    }

  /* Functionality supported */
  return (HYPORT_CAPABILITY_MASK & expectedVersion->capabilities) ==
    expectedVersion->capabilities;
}

/**
 * Query the port library.
 * 
 * Given a pointer to the port library and an offset into the table determine if
 * the function at that offset has been overridden from the default value expected. 
 *
 * @param[in] portLibrary The port library.
 * @param[in] offset The offset of the function to be queried.
 * 
 * @return 1 if the function is overriden, else 0.
 *
 * hyport_isFunctionOverridden(portLibrary, offsetof(HyPortLibrary, mem_allocate_memory));
 */
I_32 VMCALL
hyport_isFunctionOverridden (struct HyPortLibrary * portLibrary, UDATA offset)
{
  UDATA requiredSize;

  requiredSize = hyport_getSize (&(portLibrary->portVersion));
  if (requiredSize < offset)
    {
      return 0;
    }

  return *((UDATA *) & (((U_8 *) portLibrary)[offset])) !=
    *((UDATA *) & (((U_8 *) & MasterPortLibraryTable)[offset]));
}

/**
 * Allocate a port library.
 * 
 * Given a pointer to the required version of the port library allocate and initialize the structure.
 * The startup function is not called (@ref hyport_startup_library) allowing the application to override
 * any functions they desire.  In the event @ref hyport_startup_library fails when called by the application 
 * the port library memory will be freed.  
 * 
 * @param[in] version The required version of the port library.
 * @param[out] portLibrary Pointer to the allocated port library table.
 *
 * @return 0 on success, negative return value on failure
 *
 * @note portLibrary will be NULL on failure
 * @note The portlibrary version must be compatabile with the that which we are compiled against
 * @note @ref hyport_shutdown_library will deallocate this memory as part of regular shutdown
 */
I_32 VMCALL
hyport_allocate_library (struct HyPortLibraryVersion * version,
                         struct HyPortLibrary ** portLibrary)
{
  UDATA size = hyport_getSize (version);
  HyPortLibrary *portLib;
  I_32 rc;

  /* Allocate the memory */
  *portLibrary = NULL;
  if (0 == size)
    {
      return -1;
    }
  else
    {
      portLib = hymem_allocate_portLibrary (size);
      if (NULL == portLib)
        {
          return -1;
        }
    }

  /* Initialize with default values */
  rc = hyport_create_library (portLib, version, size);
  if (0 == rc)
    {
      /* Record this was self allocated */
      portLib->self_handle = portLib;
      *portLibrary = portLib;
    }
  else
    {
      hymem_deallocate_portLibrary (portLib);
    }
  return rc;
}

#ifdef HY_NO_THR
HyThreadLibrary * VMCALL
hyport_get_thread_library(HyPortLibrary * portLib)
{
  return portLib->portGlobals->threadLibrary;
}

#endif /* HY_NO_THR */
/* Set up the NLS catalog. This must be called prior to attempting 
 * any nls_printf() calls on the port library.
 */
void
initNLSCatalog (HyPortLibrary * portLib)
{
  char *endPathPtr = NULL;
  char *launcherName = NULL;
  const char **pathSet = portLib->mem_allocate_memory (portLib, sizeof(char*)*2);
  char *vmPath = NULL;

  hysysinfo_get_executable_name (portLib, NULL, &launcherName);

  if (launcherName == NULL) { 
    /* TOFIX: This is really a hack to work around the fact that the
     * hysysinfo_get_executable_name function currently only works
     * when argv[0] is passed (not NULL as above) and/or when running
     * on Linux (with /proc mounted).
     */
    portLib->nls_set_catalog (portLib, (const char **) NULL, 0, "harmony", "properties");
  } else {
    endPathPtr = strrchr (launcherName, DIR_SEPARATOR);
    endPathPtr[1] = '\0';
    // launcherName now holds the name of the launcher's home directory
    vmPath = portLib->mem_allocate_memory (portLib, strlen(launcherName) + 9);
    strcpy(vmPath, launcherName);
    strcat(vmPath, "default");
    vmPath[strlen(vmPath)+1] = '\0';
    vmPath[strlen(vmPath)] = DIR_SEPARATOR;
    pathSet[0] = launcherName;
    pathSet[1] = vmPath;
    portLib->nls_set_catalog (portLib, pathSet, 2, "harmony", "properties");
    // Free memory for launcherName -- necessary ??
    portLib->mem_free_memory (portLib, launcherName);
    portLib->mem_free_memory (portLib, (void*) pathSet);
    portLib->mem_free_memory (portLib, vmPath);
  }
}
