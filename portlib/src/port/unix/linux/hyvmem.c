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
 * @brief Virtual memory
 */

#undef CDEV_CURRENT_FUNCTION

#include "hyport.h"
#include "portpriv.h"
#include "hyportpg.h"
#include "ut_hyprt.h"

#include <sys/types.h>
#include <sys/mman.h>
#include <string.h>
#include <stdlib.h>

#include <sys/shm.h>
#include <unistd.h>

#if !defined(MAP_FAILED)
#define MAP_FAILED -1
#endif

#define VMEM_MEMINFO_SIZE_MAX	1024
#define VMEM_PROC_MEMINFO_FNAME	"/proc/meminfo"

typedef struct vmem_hugepage_info_t
{
  UDATA enabled;                /*!< boolean enabling hy large page support */
  UDATA pages_total;            /*!< total number of pages maintained by the kernel */
  UDATA pages_free;             /*!< number of free pages that may be allocated by us */
  UDATA page_size;              /*!< page size in bytes */
} vmem_hugepage_info_t;

#define CDEV_CURRENT_FUNCTION _prototypes_private
void *VMCALL default_pageSize_reserve_memory (struct HyPortLibrary
                                              *portLibrary, void *address,
                                              UDATA byteAmount,
                                              struct HyPortVmemIdentifier
                                              *identifier, UDATA mode,
                                              UDATA pageSize);
static UDATA get_hugepages_info (struct HyPortLibrary *portLibrary,
                                 vmem_hugepage_info_t * page_info);
void VMCALL update_vmemIdentifier (HyPortVmemIdentifier * identifier,
                                   void *address, void *handle,
                                   UDATA byteAmount, UDATA mode,
                                   UDATA pageSize);
int VMCALL get_protectionBits (UDATA mode);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyvmem_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyvmem_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the virtual memory operations may be created here.  All resources created here should be destroyed
 * in @ref hyvmem_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_VMEM
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyvmem_startup (struct HyPortLibrary *portLibrary)
{
  vmem_hugepage_info_t vmem_page_info;

  /* clear page info data, this has the effect of starting off in a standard state */
  memset (&vmem_page_info, 0x00, sizeof (vmem_hugepage_info_t));
  get_hugepages_info (portLibrary, &vmem_page_info);

  /* 0 terminate the table */
  memset (PPG_vmem_pageSize, 0, HYPORT_VMEM_PAGESIZE_COUNT * sizeof (UDATA));

  /* First the default page size */
  PPG_vmem_pageSize[0] = (UDATA) getpagesize ();

  /* Now the large pages */
  if (vmem_page_info.enabled)
    {
      PPG_vmem_pageSize[1] = vmem_page_info.page_size;
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_commit_memory
/**
 * Commit memory in virtual address space.
 *
 * @param[in] portLibrary The port library.
 * @param[in] address The page aligned starting address of the memory to commit.
 * @param[in] byteAmount The number of bytes to commit.
 * @param[in] identifier Descriptor for virtual memory block.
 *
 * @return pointer to the allocated memory on success, NULL on failure.
 */
void *VMCALL
hyvmem_commit_memory (struct HyPortLibrary *portLibrary, void *address,
                      UDATA byteAmount,
                      struct HyPortVmemIdentifier *identifier)
{
  Trc_PRT_vmem_hyvmem_commit_memory_Entry (address, byteAmount);

  /* Default page size */
  if (PPG_vmem_pageSize[0] == identifier->pageSize)
    {
      if (0 ==
          mprotect (address, byteAmount,
                    get_protectionBits (identifier->mode)))
        {
          Trc_PRT_vmem_hyvmem_commit_memory_Exit (address);
          return address;
        }
    }
  if (PPG_vmem_pageSize[1] == identifier->pageSize)
    {
      Trc_PRT_vmem_hyvmem_commit_memory_Exit (address);
      return address;
    }

  Trc_PRT_vmem_hyvmem_commit_memory_Exit (NULL);
  return NULL;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_decommit_memory
/**
 * Decommit memory in virtual address space.
 *
 * Decommits physical storage of the size specified starting at the address specified.
 *
 * @param[in] portLibrary The port library.
 * @param[in] address The starting address of the memory to be decommitted.
 * @param[in] byteAmount The number of bytes to be decommitted.
 * @param[in] identifier Descriptor for virtual memory block.
 *
 * @return 0 on success, non zero on failure.
 */
IDATA VMCALL
hyvmem_decommit_memory (struct HyPortLibrary * portLibrary, void *address,
                        UDATA byteAmount,
                        struct HyPortVmemIdentifier * identifier)
{
  Trc_PRT_vmem_hyvmem_decommit_memory_Entry (address, byteAmount);
  Trc_PRT_vmem_hyvmem_decommit_memory_Exit (0);
  return (IDATA) 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_free_memory
/**
 * Free memory in virtual address space.
 *
 * Frees physical storage of the size specified starting at the address specified.
 *
 * @param[in] portLibrary The port library.
 * @param[in] address The starting address of the memory to be de-allocated.
 * @param[in] byteAmount The number of bytes to be allocated.
 * @param[in] identifier Descriptor for virtual memory block.
 *
 * @return 0 on success, non zero on failure.
 */
I_32 VMCALL
hyvmem_free_memory (struct HyPortLibrary * portLibrary, void *address,
                    UDATA byteAmount,
                    struct HyPortVmemIdentifier * identifier)
{
  Trc_PRT_vmem_hyvmem_free_memory_Entry (address, byteAmount);

  /* Default page Size */
  if (PPG_vmem_pageSize[0] == identifier->pageSize)
    {
      I_32 ret = 0;

      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      ret = (I_32) munmap (address, (size_t) byteAmount);
      Trc_PRT_vmem_hyvmem_free_memory_Exit (ret);
      return ret;
    }
  if (PPG_vmem_pageSize[1] == identifier->pageSize)
    {
      shmdt (identifier->address);
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      Trc_PRT_vmem_hyvmem_free_memory_Exit (0);
      return 0;
    }

  Trc_PRT_vmem_hyvmem_free_memory_Exit (-1);
  return -1;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_reserve_memory
/**
 * Reserve memory in virtual address space.
 *
 * Reserves a range of  virtual address space without allocating any actual physical storage.
 * The memory is not available for use until committed @ref hyvmem_commit_memory.
 * The memory may not be used by other memory allocation routines until it is explicitly released.
 *
 * @param[in] portLibrary The port library.
 * @param[in] address The starting address of the memory to be reserved.
 * @param[in] byteAmount The number of bytes to be reserved.
 * @param[in] identifier Descriptor for virtual memory block.
 * @param[in] mode Bitmap indicating how memory is to be reserved.  Expected values combination of:
 * \arg HYPORT_VMEM_MEMORY_MODE_READ memory is readable
 * \arg HYPORT_VMEM_MEMORY_MODE_WRITE memory is writable
 * \arg HYPORT_VMEM_MEMORY_MODE_EXECUTE memory is executable
 * \arg HYPORT_VMEM_MEMORY_MODE_COMMIT commits memory as part of the reserve
 * @param[in] pageSize Size of the page requested, a value returned by @ref hyvmem_supported_page_sizes,
 * or the constant HYPORT_VMEM_PAGE_SIZE_DEFAULT for the system default page size.
 *
 * @return pointer to the reserved memory on success, NULL on failure.
 *
 * @internal @warning Do not call error handling code @ref hyerror upon error as 
 * the error handling code uses per thread buffers to store the last error.  If memory
 * can not be allocated the result would be an infinite loop.
 */
void *VMCALL
hyvmem_reserve_memory (struct HyPortLibrary *portLibrary, void *address,
                       UDATA byteAmount,
                       struct HyPortVmemIdentifier *identifier, UDATA mode,
                       UDATA pageSize)
{

  Trc_PRT_vmem_hyvmem_reserve_memory_Entry (address, byteAmount);
  /* Invalid input */
  if (0 == pageSize)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      Trc_PRT_vmem_hyvmem_reserve_memory_Exit1 ();
      return NULL;
    }

  /* Handle default page size differently, don't use shmget */
  if ((HYPORT_VMEM_PAGE_SIZE_DEFAULT == pageSize)
      || (PPG_vmem_pageSize[0] == pageSize))
    {
      void *defptr =
        default_pageSize_reserve_memory (portLibrary, address, byteAmount,
                                         identifier, mode,
                                         PPG_vmem_pageSize[0]);
      Trc_PRT_vmem_hyvmem_reserve_memory_Exit2 (defptr);
      return defptr;
    }

  /* If the pageSize is not one of the large page sizes supported, error */
  if (PPG_vmem_pageSize[1] != pageSize)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      Trc_PRT_vmem_hyvmem_reserve_memory_Exit3 (pageSize);
      return NULL;
    }

  /* The address should usually be passed in as NULL in order to let the kernel find and assign a 
   * large enough window of virtual memory 
   *
   * Requesting HUGETLB pages via shmget has the effect of "reserving" them. They have to be attached to become allocated for use
   * The execute flags are ignored by shmget
   */
  key_t addressKey;
  void *baseAddress;
#if defined(FREEBSD)
/*
 * TODO: This does not work but it does compile.  Need a real fix.  Anyone
 * know how to reserve memory on FreeBSD?
 */
#define SHM_HUGETLB 0
#endif
  int shmgetFlags = SHM_HUGETLB | IPC_CREAT;
  UDATA largePageSize;
  void *ptr = NULL;

  largePageSize = PPG_vmem_pageSize[1];

  if (0 != (HYPORT_VMEM_MEMORY_MODE_READ & mode))
    {
      shmgetFlags |= SHM_R;
    }
  if (0 != (HYPORT_VMEM_MEMORY_MODE_WRITE & mode))
    {
      shmgetFlags |= SHM_W;
    }

  addressKey = shmget (IPC_PRIVATE, (size_t) byteAmount, shmgetFlags);
  if (-1 == addressKey)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      Trc_PRT_vmem_hyvmem_reserve_memory_Exit4 ();
      return NULL;
    }

  /* Attach the HUGETLB pages to a virtual address choosen by the kernel, this call may fail in case a large enough window of virual memory is not available */
  baseAddress = shmat (addressKey, address, 0);
  shmctl (addressKey, IPC_RMID, NULL);  /* release when complete, protect from ^C or crash */

  if (baseAddress == MAP_FAILED)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      Trc_PRT_vmem_hyvmem_reserve_memory_Exit4 ();
      return NULL;
    }

  /* Update identifier and commit memory if required, else return reserved memory */
  update_vmemIdentifier (identifier, baseAddress, baseAddress,
                         byteAmount, mode, largePageSize);
  if (0 != (HYPORT_VMEM_MEMORY_MODE_COMMIT & mode))
    {
      ptr =
        portLibrary->vmem_commit_memory (portLibrary, baseAddress, byteAmount,
                                         identifier);
    }
  else
    {
      ptr = baseAddress;
    }
  Trc_PRT_vmem_hyvmem_reserve_memory_Exit (ptr);
  return ptr;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyvmem_supported_page_sizes
/**
 * Determine the page sizes supported.
 *
 * @param[in] portLibrary The port library.
 *
 * @return A 0 terminated array of supported page sizes.  The first entry is the default page size, other entries
 * are the large page sizes supported.
 */
UDATA *VMCALL
hyvmem_supported_page_sizes (struct HyPortLibrary * portLibrary)
{
  return PPG_vmem_pageSize;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION get_hugepages_info
static UDATA
get_hugepages_info (struct HyPortLibrary *portLibrary,
                    vmem_hugepage_info_t * page_info)
{
  int fd;
  int bytes_read;
  char *line_ptr, read_buf[VMEM_MEMINFO_SIZE_MAX];
  char token_name[128];
  int token_value, tokens_assigned;

  fd = hyfile_open (portLibrary, VMEM_PROC_MEMINFO_FNAME, HyOpenRead, 0);
  if (fd < 0)
    {
      return 0;
    }

  bytes_read =
    hyfile_read (portLibrary, fd, read_buf, VMEM_MEMINFO_SIZE_MAX - 1);
  if (bytes_read <= 0)
    {
      hyfile_close (portLibrary, fd);
      return 0;
    }

  /* make sure its null terminated */
  read_buf[bytes_read] = 0;

        /** Why this is data is not available via a well defined system call remains a mystery. Meanwhile
	 *  we have to parse /proc/meminfo to figure out how many pages are available/free as well as
	 *  their size
	 */
  line_ptr = read_buf;
  while (line_ptr && *line_ptr)
    {

      tokens_assigned =
        sscanf (line_ptr, "%127s %d %*s", token_name, &token_value);

#if defined(LPDEBUG)
      portLibrary->tty_printf (portLibrary, "/proc/meminfo => %s [%d] %d\n",
                               token_name, token_value, tokens_assigned);
#endif

      if (tokens_assigned)
        {
          if (!strcmp (token_name, "HugePages_Total:"))
            {
              page_info->pages_total = token_value;
            }
          else if (!strcmp (token_name, "HugePages_Free:"))
            {
              page_info->pages_free = token_value;
            }
          else if (!strcmp (token_name, "Hugepagesize:"))
            {
              page_info->page_size = token_value * 1024;        /* value is in KB, convert to bytes */
            }
        }

      line_ptr = strpbrk (line_ptr, "\n");
      if (line_ptr && *line_ptr)
        {
          line_ptr++;           /* skip the \n if we are not done yet */
        }
    }

  hyfile_close (portLibrary, fd);

  /* "Enable" large page support if the system has been found to be initialized */
  if (page_info->pages_total)
    {
      page_info->enabled = 1;
    }

  return 1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION default_pageSize_reserve_memory
void *VMCALL
default_pageSize_reserve_memory (struct HyPortLibrary *portLibrary,
                                 void *address, UDATA byteAmount,
                                 struct HyPortVmemIdentifier *identifier,
                                 UDATA mode, UDATA pageSize)
{
  /* This function is cloned in HySourceUnixHyVMem (hyvmem_reserve_memory).  
   * Any changes made here may need to be reflected in that version .
   */
  int fd = -1;
  int flags = MAP_PRIVATE;
  void *result;
  int protectionFlags = PROT_NONE;

#if defined(MAP_ANONYMOUS)
  flags |= MAP_ANONYMOUS;
#elif defined(MAP_ANON)
  flags |= MAP_ANON;
#else
  fd =
    portLibrary->file_open (portLibrary, "/dev/zero",
                            HyOpenRead | HyOpenWrite, 0);
  if (fd == -1)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      return NULL;
    }
#endif

  if (0 != (HYPORT_VMEM_MEMORY_MODE_COMMIT & mode))
    {
      protectionFlags = get_protectionBits (mode);
    }
  else
    {
      flags |= MAP_NORESERVE;
    }
  result = mmap (address, (size_t) byteAmount, protectionFlags, flags, fd, 0);

#if !defined(MAP_ANONYMOUS) && !defined(MAP_ANON)
  portLibrary->file_close (portLibrary, fd);
#endif

  if (MAP_FAILED == result)
    {
      update_vmemIdentifier (identifier, NULL, NULL, 0, 0, 0);
      return NULL;
    }
  else
    {
      /* Update identifier and commit memory if required, else return reserved memory */
      update_vmemIdentifier (identifier, result, result, byteAmount, mode,
                             pageSize);
      if (0 != (HYPORT_VMEM_MEMORY_MODE_COMMIT & mode))
        {
          portLibrary->vmem_commit_memory (portLibrary, result, byteAmount,
                                           identifier);
        }
      return result;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION update_vmemIdentifier
/**
 * @internal
 * Update HyPortVmIdentifier structure
 *
 * @param[in] identifier The structure to be updated
 * @param[in] address Base address
 * @param[in] handle Platform specific handle for reserved memory
 * @param[in] byteAmount Size of allocated area
 * @param[in] mode Access Mode
 * @param[in] pageSize Constant describing pageSize
 */
void VMCALL
update_vmemIdentifier (HyPortVmemIdentifier * identifier, void *address,
                       void *handle, UDATA byteAmount, UDATA mode,
                       UDATA pageSize)
{
  identifier->address = address;
  identifier->handle = handle;
  identifier->size = byteAmount;
  identifier->pageSize = pageSize;
  identifier->mode = mode;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION get_protectionBits
int VMCALL
get_protectionBits (UDATA mode)
{
  int protectionFlags = 0;

  if (0 != (HYPORT_VMEM_MEMORY_MODE_EXECUTE & mode))
    {
      protectionFlags |= PROT_EXEC;
    }
  if (0 != (HYPORT_VMEM_MEMORY_MODE_READ & mode))
    {
      protectionFlags |= PROT_READ;
    }
  if (0 != (HYPORT_VMEM_MEMORY_MODE_WRITE & mode))
    {
      protectionFlags |= PROT_WRITE;
    }
  if (0 == protectionFlags)
    {
      protectionFlags = PROT_NONE;
    }

  return protectionFlags;
}

#undef CDEV_CURRENT_FUNCTION
