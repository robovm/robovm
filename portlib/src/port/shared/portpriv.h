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

#if !defined(portlibraryprivatedefines_h)
#define portlibraryprivatedefines_h

#include "hycomp.h"
#include "hyportpg.h"
#include "hyportptb.h"
#include "hyport.h"
#ifdef HY_NO_THR
#include "hythread.h"
#endif /* HY_NO_THR */
#include "hymutex.h"

/* The following defines are used by hyshmem and hyshsem */
#define HYSH_MAXPATH HyMaxPath
#define HyVersionMajor 0
#define HyVersionMinor 1
#define HYSH_VERSION (HyVersionMajor*100 + HyVersionMinor)
#define HYSH_MODLEVEL 1
#define HYSH_ADDRMODE 32
#define VERSION_STRING_SPEC "C%dD%dA%d"
#define GET_VERSION_STRING(portLib, versionStr)\
											 portLib->str_printf(portLib, versionStr, 30, VERSION_STRING_SPEC,\
											 HYSH_VERSION, HYSH_MODLEVEL, HYSH_ADDRMODE)
#define HYSH_DIRPERM (01777)
#define HYSH_BASEFILEPERM (0640)
/* Maximum id we should try when we do ftok */
#define HYSH_MAX_PROJ_ID 20
/*
 * We are going to fix the "base directory", where all the token files are created.
 * For Windows it does not need to be in a valid path, however on Unix it should
 * be somewhere that is public and world writable.
 * In future we may move it into a static variable of some sort, and changable at startup
 */
#if defined(WIN32)
#define HYSH_BASEDIR "javasharedresources\\"
#else
#define HYSH_BASEDIR "/tmp/javasharedresources/"
#endif
/*end hyshsem and hyshmem common module */
typedef struct HyPortControlData
{
  UDATA sig_flags;
  UDATA shmem_group_perm;
} HyPortControlData;
typedef struct HyNLSDataCache
{
  char *baseCatalogPaths[4];
  UDATA nPaths;
  char *baseCatalogName;
  char *baseCatalogExtension;
  char *catalogues[4];
  char language[4];
  char region[4];
  char variant[32];
  struct HyThreadMonitor *monitor;
  struct HyNLSHashEntry *hash_buckets[256];
  struct HyNLSHashEntry *old_hashEntries;
} HyNLSDataCache;
#define HYNLS_NUM_HASH_BUCKETS  0x100
typedef struct HyNLSHashEntry
{
  U_32 module_name;
  U_32 message_num;
  struct HyNLSHashEntry *next;
  char message[8];
} HyNLSHashEntry;
typedef struct HyPortLibraryGlobalData
{
  struct HyPortControlData control;
  struct HyNLSDataCache nls_data;
  hythread_tls_key_t tls_key;
  MUTEX tls_mutex;
  void *buffer_list;
#ifdef HY_NO_THR
  struct HyThreadLibrary *threadLibrary;
#endif /* HY_NO_THR */
  struct HyPortPlatformGlobals platformGlobals;
} HyPortLibraryGlobalData;
/* HySourceHyCPUControl*/
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hycpu_flush_icache
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *memoryPointer,
            UDATA byteAmount));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hycpu_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hycpu_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyError*/
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hyerror_last_error_message PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyerror_set_last_error
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 platformCode,
            I_32 portableCode));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyerror_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyerror_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyerror_set_last_error_with_message
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 portableCode,
            const char *errorMessage));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyerror_last_error_number PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyErrorHelpers*/
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  errorMessage
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 errorCode));
/* HySourceHyExit*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyexit_get_exit_code PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyexit_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyexit_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyexit_shutdown_and_exit
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 exitCode));
/* HySourceHyFile*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_unlink
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_close PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_mkdir
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyfile_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_set_length
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, I_64 newLength));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_findnext
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA findhandle,
            char *resultbuf));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL hyfile_sync
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyfile_vprintf
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, const char *format,
            va_list args));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyfile_printf
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, const char *format,
            ...));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_unlinkdir
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyfile_open
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path, I_32 flags,
            I_32 mode));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hyfile_findfirst
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path,
            char *resultbuf));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_move
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *pathExist,
            const char *pathNew));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyfile_attr
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyfile_findclose
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA findhandle));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyfile_read
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, void *buf,
            IDATA nbytes));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyfile_write
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, const void *buf,
            IDATA nbytes));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hyfile_error_message PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_64 VMCALL
  hyfile_seek
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, I_64 offset,
            I_32 whence));
struct HyPortLibrary;
extern HY_CFUNC I_64 VMCALL
  hyfile_length
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
struct HyPortLibrary;
extern HY_CFUNC I_64 VMCALL
  hyfile_lastmod
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path));
/* HySourceHyFileText*/
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyfile_write_text
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, const char *buf,
            IDATA nbytes));
struct HyPortLibrary;
extern HY_CFUNC char *VMCALL
  hybuf_write_text
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *buf,
           IDATA nbytes));
struct HyPortLibrary;
extern HY_CFUNC char *VMCALL
  hyfile_read_text
PROTOTYPE ((struct HyPortLibrary * portLibrary, IDATA fd, char *buf,
            IDATA nbytes));
/* HySourceHyGP*/
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hygp_register_handler
PROTOTYPE ((struct HyPortLibrary * portLibrary, handler_fn fn,
            void *aUserData));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hygp_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hygp_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hygp_protect
PROTOTYPE ((struct HyPortLibrary * portLibrary, protected_fn fn, void *arg));
struct HyPortLibrary;
extern HY_CFUNC U_32 VMCALL
  hygp_info_count
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *info, U_32 category));
struct HyPortLibrary;
extern HY_CFUNC U_32 VMCALL
  hygp_info
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *info, U_32 category,
            I_32 index, const char **name, void **value));
/* HySourceHyIPCMutex*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyipcmutex_release
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *name));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyipcmutex_acquire
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *name));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyipcmutex_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyipcmutex_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyMem*/
extern HY_CFUNC void VMCALL
  hymem_deallocate_portLibrary PROTOTYPE ((void *memoryPointer));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hymem_allocate_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA byteAmount));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hymem_startup
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA portGlobalSize));
extern HY_CFUNC void *VMCALL hymem_allocate_portLibrary
PROTOTYPE ((UDATA byteAmount));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hymem_reallocate_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *memoryPointer,
            UDATA byteAmount));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hymem_allocate_memory_callSite
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA byteAmount,
            const char *callSite));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hymem_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hymem_free_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *memoryPointer));
/* HySourceHyMemoryMap*/
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hymmap_unmap_file
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hymmap_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hymmap_capabilities PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hymmap_map_file
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *path,
            void **handle));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hymmap_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyNLS*/
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hynls_get_language PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hynls_get_variant PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hynls_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hynls_vprintf
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA flags, U_32 module_name,
            U_32 message_num, va_list args));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hynls_set_catalog
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char **paths,
            const int nPaths, const char *baseName, const char *extension));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hynls_set_locale
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *lang,
            const char *region, const char *variant));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hynls_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hynls_printf
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA flags, U_32 module_name,
            U_32 message_num, ...));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hynls_lookup_message
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA flags, U_32 module_name,
            U_32 message_num, const char *default_string));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hynls_get_region PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyNLSHelpers*/
struct HyPortLibrary;
extern HY_CFUNC void
  nls_determine_locale PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyOSDump*/
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hydump_create
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *filename,
            char *dumpType, void *userData));
/* HySourceHyPortControl*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_control
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *key, UDATA value));
/* HySourceHyPortPerThreadBuffer*/
/* HySourceHyPortTLSHelpers*/
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyport_tls_free PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_tls_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hyport_tls_peek PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void *VMCALL
  hyport_tls_get PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyport_tls_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHySharedMemory*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyshmem_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyshmem_findnext
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA findhandle,
            char *resultbuf));
struct hyshmem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshmem_detach
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshmem_handle ** handle));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyshmem_findclose
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA findhandle));
struct hyshmem_handle;
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyshmem_close
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshmem_handle ** handle));
struct HyPortLibrary;
struct hyshmem_handle;
extern HY_CFUNC void *VMCALL
  hyshmem_attach
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshmem_handle * handle));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hyshmem_findfirst
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *resultbuf));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyshmem_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortShmemStatistic;
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hyshmem_stat
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *name,
            struct HyPortShmemStatistic * statbuf));
struct hyshmem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshmem_destroy
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshmem_handle ** handle));
struct hyshmem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshmem_open
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshmem_handle ** handle, const char *rootname, I_32 size,
            I_32 perm));
/* HySourceHySharedSemaphore*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyshsem_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
struct hyshsem_handle;
extern HY_CFUNC void VMCALL
  hyshsem_close
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle ** handle));
struct hyshsem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshsem_post
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle * handle, UDATA semset, UDATA flag));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyshsem_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
struct hyshsem_handle;
extern HY_CFUNC IDATA VMCALL
  hyshsem_destroy
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle ** handle));
struct hyshsem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshsem_setVal
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle * handle, UDATA semset, IDATA value));
struct hyshsem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshsem_open
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle ** handle, const char *semname, int setSize,
            int permission));
struct hyshsem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshsem_getVal
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle * handle, UDATA semset));
struct hyshsem_handle;
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hyshsem_wait
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyshsem_handle * handle, UDATA semset, UDATA flag));
/* HySourceHySI*/
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hysysinfo_get_executable_name
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *argv0, char **result));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hysysinfo_get_OS_type PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC U_64 VMCALL
  hysysinfo_get_physical_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_DLPAR_max_CPUs PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_get_number_CPUs PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hysysinfo_get_CPU_architecture
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_get_processing_capacity
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hysysinfo_get_OS_version PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysysinfo_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_DLPAR_enabled PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hysysinfo_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_get_pid PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC U_16 VMCALL
  hysysinfo_get_classpathSeparator
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hysysinfo_get_username
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *buffer, UDATA length));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysysinfo_weak_memory_consistency
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hysysinfo_get_env
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *envVar,
            char *infoString, UDATA bufSize));
/* HySourceHySL*/
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_lookup_name
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor, const char *name,
            UDATA * func, const char *argSignature));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_close_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysl_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_open_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *name,
            UDATA * descriptor, BOOLEAN decorate));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hysl_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyStr*/
struct HyPortLibrary;
extern HY_CFUNC U_32 VMCALL
  hystr_vprintf
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *buf, U_32 bufLen,
            const char *format, va_list args));
struct HyPortLibrary;
extern HY_CFUNC U_32 VMCALL
  hystr_printf
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *buf, U_32 bufLen,
            const char *format, ...));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hystr_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hystr_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyStrFTime*/
struct HyPortLibrary;
extern HY_CFUNC U_32 VMCALL
  hystrftime
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *buf, U_32 bufLen,
            const char *format));
/* HySourceHyTime*/
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hytime_usec_clock PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC U_64 VMCALL
  hytime_hires_clock PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC U_64 VMCALL
  hytime_hires_delta
PROTOTYPE ((struct HyPortLibrary * portLibrary, U_64 startTime, U_64 endTime,
            UDATA requiredResolution));
struct HyPortLibrary;
extern HY_CFUNC U_64 VMCALL
  hytime_hires_frequency PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hytime_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_64 VMCALL
  hytime_current_time_millis PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytime_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hytime_msec_clock PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourceHyTTY*/
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytty_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hytty_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytty_err_printf
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *format, ...));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hytty_get_chars
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *s, UDATA length));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytty_printf
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *format, ...));
struct HyPortLibrary;
extern HY_CFUNC IDATA VMCALL
  hytty_available PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytty_vprintf
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *format,
            va_list args));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hytty_err_vprintf
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *format,
            va_list args));
/* HySourceHyVMem*/
struct HyPortLibrary;
struct HyPortVmemIdentifier;
extern HY_CFUNC void *VMCALL
  hyvmem_commit_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *address,
            UDATA byteAmount, struct HyPortVmemIdentifier * identifier));
struct HyPortLibrary;
struct HyPortVmemIdentifier;
extern HY_CFUNC IDATA VMCALL
  hyvmem_decommit_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *address,
            UDATA byteAmount, struct HyPortVmemIdentifier * identifier));
struct HyPortLibrary;
struct HyPortVmemIdentifier;
extern HY_CFUNC void *VMCALL
  hyvmem_reserve_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *address,
            UDATA byteAmount, struct HyPortVmemIdentifier * identifier,
            UDATA mode, UDATA pageSize));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hyvmem_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC UDATA *VMCALL
  hyvmem_supported_page_sizes
PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
struct HyPortVmemIdentifier;
extern HY_CFUNC I_32 VMCALL
  hyvmem_free_memory
PROTOTYPE ((struct HyPortLibrary * portLibrary, void *address,
            UDATA byteAmount, struct HyPortVmemIdentifier * identifier));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyvmem_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
/* HySourcePort*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_shutdown_library PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibraryVersion;
extern HY_CFUNC UDATA VMCALL
  hyport_getSize PROTOTYPE ((struct HyPortLibraryVersion * version));
struct HyPortLibraryVersion;
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_getVersion
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct HyPortLibraryVersion * version));
struct HyPortLibraryVersion;
extern HY_CFUNC I_32 VMCALL
  hyport_isCompatible
PROTOTYPE ((struct HyPortLibraryVersion * expectedVersion));
struct HyPortLibraryVersion;
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_allocate_library
PROTOTYPE ((struct HyPortLibraryVersion * version,
            struct HyPortLibrary ** portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_isFunctionOverridden
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA offset));
struct HyPortLibraryVersion;
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_init_library
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct HyPortLibraryVersion * version, UDATA size));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_startup_library PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibraryVersion;
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hyport_create_library
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct HyPortLibraryVersion * version, UDATA size));
/* HySourceSockets*/
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_hostent_addrlist
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyhostent_t handle,
            U_32 index));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *name,
            hyaddrinfo_t hints, hyaddrinfo_t result));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_int
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, I_32 * optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_shutdown PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_hostent_aliaslist
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyhostent_t handle,
            char ***aliasList));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_fdset_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo_create_hints
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t * result,
            I_16 family, I_32 socktype, I_32 protocol, I_32 flags));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_connect_with_timeout
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock,
            hysockaddr_t addr, U_32 timeout, U_32 step, U_8 ** context));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getpeername
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t handle,
            hysockaddr_t addrHandle));
struct HyPortLibrary;
extern HY_CFUNC const char *VMCALL
  hysock_error_message PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getnameinfo
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t in_addr,
            I_32 sockaddr_size, char *name, I_32 name_length, int flags));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_ipv6_mreq
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hyipv6_mreq_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_inetaddr
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *addrStr, U_32 * addr));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_bind
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock,
            hysockaddr_t addr));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_connect
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock,
            hysockaddr_t addr));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_listen
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock,
            I_32 backlog));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_bool
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, BOOLEAN * optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_readfrom
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
            I_32 nbyte, I_32 flags, hysockaddr_t addrHandle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getopt_bool
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, BOOLEAN * optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_fdset_size
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_ipmreq
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hyipmreq_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_writeto
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
            I_32 nbyte, I_32 flags, hysockaddr_t addrHandle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_linger_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, hylinger_t handle,
            I_32 enabled, U_16 timeout));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_select_read
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t hysocketP,
            I_32 secTime, I_32 uSecTime, BOOLEAN accept));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle,
            const char *addrStr, U_16 port));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_gethostbyaddr
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *addr, I_32 length,
            I_32 type, hyhostent_t handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_inetntoa
PROTOTYPE ((struct HyPortLibrary * portLibrary, char **addrStr,
            U_32 nipAddr));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_linger_enabled
PROTOTYPE ((struct HyPortLibrary * portLibrary, hylinger_t handle,
            BOOLEAN * enabled));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_select
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 nfds, hyfdset_t readfds,
            hyfdset_t writefds, hyfdset_t exceptfds, hytimeval_t timeout));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_startup PROTOTYPE ((struct HyPortLibrary * portLibrary));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_linger_linger
PROTOTYPE ((struct HyPortLibrary * portLibrary, hylinger_t handle,
            U_16 * linger));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo_name
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t handle,
            char *name, int index));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr_address
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr_address6
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle,
            U_8 * address, U_32 * length, U_32 * scope_id));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_write
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
            I_32 nbyte, I_32 flags));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_shutdown_input
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock));
struct hyNetworkInterfaceArray_struct;
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_free_network_interface_struct
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyNetworkInterfaceArray_struct * array));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_read
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock, U_8 * buf,
            I_32 nbyte, I_32 flags));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_socket
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t * handle,
            I_32 family, I_32 socktype, I_32 protocol));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_close
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t * sock));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_gethostname
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *buffer, I_32 length));
struct HyPortLibrary;
extern HY_CFUNC U_16 VMCALL
  hysock_sockaddr_port
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_gethostbyname
PROTOTYPE ((struct HyPortLibrary * portLibrary, const char *name,
            hyhostent_t handle));
struct HyPortLibrary;
struct hyNetworkInterfaceArray_struct;
extern HY_CFUNC I_32 VMCALL
  hysock_get_network_interfaces
PROTOTYPE ((struct HyPortLibrary * portLibrary,
            struct hyNetworkInterfaceArray_struct * array,
            BOOLEAN preferIPv4Stack));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo_length
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t handle,
            I_32 * length));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_ipv6_mreq_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyipv6_mreq_t handle,
            U_8 * ipmcast_addr, U_32 ipv6mr_interface));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_htonl PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 val));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getsockname
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t handle,
            hysockaddr_t addrHandle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_freeaddrinfo
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t handle));
struct HyPortLibrary;
extern HY_CFUNC U_16 VMCALL
  hysock_htons PROTOTYPE ((struct HyPortLibrary * portLibrary, U_16 val));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle,
            I_16 family, U_32 nipAddr, U_16 nPort));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo_address
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t handle,
            U_8 * address, int index, U_32 * scope_id));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_socketIsValid
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t handle));
struct HyPortLibrary;
extern HY_CFUNC U_16 VMCALL
  hysock_ntohs PROTOTYPE ((struct HyPortLibrary * portLibrary, U_16 val));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_ntohl PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 val));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr_init6
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysockaddr_t handle,
            U_8 * addr, I_32 addrlength, I_16 family, U_16 nPort,
            U_32 flowinfo, U_32 scope_id, hysocket_t sock));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getaddrinfo_family
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyaddrinfo_t handle,
            I_32 * family, int index));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_sockaddr
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hysockaddr_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_linger
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hylinger_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getopt_linger
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hylinger_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getopt_sockaddr
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, hysockaddr_t optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setflag
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_32 flag, I_32 * arg));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_shutdown_output
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t sock));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_hostent_hostname
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyhostent_t handle,
            char **hostName));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_accept
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t serverSock,
            hysockaddr_t addrHandle, hysocket_t * sockHandle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_sockaddr_family
PROTOTYPE ((struct HyPortLibrary * portLibrary, I_16 * family,
            hysockaddr_t handle));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_ipmreq_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyipmreq_t handle,
            U_32 nipmcast, U_32 nipinterface));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_timeval_init
PROTOTYPE ((struct HyPortLibrary * portLibrary, U_32 secTime, U_32 uSecTime,
            hytimeval_t timeP));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_set_nonblocking
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            BOOLEAN nonblocking));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getopt_int
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, I_32 * optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_setopt_byte
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, U_8 * optval));
struct HyPortLibrary;
extern HY_CFUNC I_32 VMCALL
  hysock_getopt_byte
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t socketP,
            I_32 optlevel, I_32 optname, U_8 * optval));
/* HySourceStaticSL*/
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_up_open_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *name,
            UDATA * descriptor, BOOLEAN decorate));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_split_close_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_up_close_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_split_open_shared_library
PROTOTYPE ((struct HyPortLibrary * portLibrary, char *name,
            UDATA * descriptor, BOOLEAN decorate));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_split_lookup_name
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor, char *name,
            UDATA * func, const char *argSignature));
struct HyPortLibrary;
extern HY_CFUNC UDATA VMCALL
  hysl_up_lookup_name
PROTOTYPE ((struct HyPortLibrary * portLibrary, UDATA descriptor, char *name,
            UDATA * func, const char *argSignature));
#ifdef HY_NO_THR
extern HY_CFUNC HyThreadLibrary * VMCALL
  hyport_get_thread_library
PROTOTYPE ((HyPortLibrary * portLib));
#endif /* HY_NO_THR */
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hysock_fdset_zero
PROTOTYPE ((struct HyPortLibrary * portLibrary, hyfdset_t fdset));
struct HyPortLibrary;
extern HY_CFUNC void VMCALL
  hysock_fdset_set
PROTOTYPE ((struct HyPortLibrary * portLibrary, hysocket_t aSocket, hyfdset_t fdset));
static HyPortLibrary MasterPortLibraryTable = {
  {HYPORT_MAJOR_VERSION_NUMBER, HYPORT_MINOR_VERSION_NUMBER, 0, HYPORT_CAPABILITY_MASK},        /* portVersion */
  NULL,                         /* portGlobals */
  hyport_shutdown_library,      /* port_shutdown_library */
  hyport_isFunctionOverridden,  /* port_isFunctionOverridden */
  hyport_tls_free,              /* port_tls_free */
  hyerror_startup,              /* error_startup */
  hyerror_shutdown,             /* error_shutdown */
  hyerror_last_error_number,    /* error_last_error_number */
  hyerror_last_error_message,   /* error_last_error_message */
  hyerror_set_last_error,       /* error_set_last_error */
  hyerror_set_last_error_with_message,  /* error_set_last_error_with_message */
  hytime_startup,               /* time_startup */
  hytime_shutdown,              /* time_shutdown */
  hytime_msec_clock,            /* time_msec_clock */
  hytime_usec_clock,            /* time_usec_clock */
  hytime_current_time_millis,   /* time_current_time_millis */
  hytime_hires_clock,           /* time_hires_clock */
  hytime_hires_frequency,       /* time_hires_frequency */
  hytime_hires_delta,           /* time_hires_delta */
  hysysinfo_startup,            /* sysinfo_startup */
  hysysinfo_shutdown,           /* sysinfo_shutdown */
  hysysinfo_get_pid,            /* sysinfo_get_pid */
  hysysinfo_get_physical_memory,        /* sysinfo_get_physical_memory */
  hysysinfo_get_OS_version,     /* sysinfo_get_OS_version */
  hysysinfo_get_env,            /* sysinfo_get_env */
  hysysinfo_get_CPU_architecture,       /* sysinfo_get_CPU_architecture */
  hysysinfo_get_OS_type,        /* sysinfo_get_OS_type */
  hysysinfo_get_classpathSeparator,     /* sysinfo_get_classpathSeparator */
  hysysinfo_get_executable_name,        /* sysinfo_get_executable_name */
  hysysinfo_get_number_CPUs,    /* sysinfo_get_number_CPUs */
  hysysinfo_get_username,       /* sysinfo_get_username */
  hyfile_startup,               /* file_startup */
  hyfile_shutdown,              /* file_shutdown */
  hyfile_write,                 /* file_write */
  hyfile_write_text,            /* file_write_text */
  hyfile_vprintf,               /* file_vprintf */
  hyfile_printf,                /* file_printf */
  hyfile_open,                  /* file_open */
  hyfile_close,                 /* file_close */
  hyfile_seek,                  /* file_seek */
  hyfile_read,                  /* file_read */
  hyfile_unlink,                /* file_unlink */
  hyfile_attr,                  /* file_attr */
  hyfile_lastmod,               /* file_lastmod */
  hyfile_length,                /* file_length */
  hyfile_set_length,            /* file_set_length */
  hyfile_sync,                  /* file_sync */
  hysl_startup,                 /* sl_startup */
  hysl_shutdown,                /* sl_shutdown */
#if defined(HYVM_STATIC_LINKAGE)
  hysl_split_close_shared_library,      /* sl_close_shared_library */
  hysl_split_open_shared_library,       /* sl_open_shared_library */
  hysl_split_lookup_name,       /* sl_lookup_name */
#else
  hysl_close_shared_library,    /* sl_close_shared_library */
  hysl_open_shared_library,     /* sl_open_shared_library */
  hysl_lookup_name,             /* sl_lookup_name */
#endif /* HYVM_STATIC_LINKAGE */
hytty_startup,                /* tty_startup */
  hytty_shutdown,               /* tty_shutdown */
  hytty_printf,                 /* tty_printf */
  hytty_vprintf,                /* tty_vprintf */
  hytty_get_chars,              /* tty_get_chars */
  hytty_err_printf,             /* tty_err_printf */
  hytty_err_vprintf,            /* tty_err_vprintf */
  hytty_available,              /* tty_available */
  hymem_startup,                /* mem_startup */
  hymem_shutdown,               /* mem_shutdown */
  hymem_allocate_memory,        /* mem_allocate_memory */
  hymem_allocate_memory_callSite,       /* mem_allocate_memory_callSite */
  hymem_free_memory,            /* mem_free_memory */
  hymem_reallocate_memory,      /* mem_reallocate_memory */
  hycpu_startup,                /* cpu_startup */
  hycpu_shutdown,               /* cpu_shutdown */
  hycpu_flush_icache,           /* cpu_flush_icache */
  hyvmem_startup,               /* vmem_startup */
  hyvmem_shutdown,              /* vmem_shutdown */
  hyvmem_commit_memory,         /* vmem_commit_memory */
  hyvmem_decommit_memory,       /* vmem_decommit_memory */
  hyvmem_free_memory,           /* vmem_free_memory */
  hyvmem_reserve_memory,        /* vmem_reserve_memory */
  hyvmem_supported_page_sizes,  /* vmem_supported_page_sizes */
  hysock_startup,               /* sock_startup */
  hysock_shutdown,              /* sock_shutdown */
  hysock_htons,                 /* sock_htons */
  hysock_write,                 /* sock_write */
  hysock_sockaddr,              /* sock_sockaddr */
  hysock_read,                  /* sock_read */
  hysock_socket,                /* sock_socket */
  hysock_close,                 /* sock_close */
  hysock_connect,               /* sock_connect */
  hysock_inetaddr,              /* sock_inetaddr */
  hysock_gethostbyname,         /* sock_gethostbyname */
  hysock_hostent_addrlist,      /* sock_hostent_addrlist */
  hysock_sockaddr_init,         /* sock_sockaddr_init */
  hysock_linger_init,           /* sock_linger_init */
  hysock_setopt_linger,         /* sock_setopt_linger */
  hygp_startup,                 /* gp_startup */
  hygp_shutdown,                /* gp_shutdown */
  hygp_protect,                 /* gp_protect */
  hygp_register_handler,        /* gp_register_handler */
  hygp_info,                    /* gp_info */
  hygp_info_count,              /* gp_info_count */
  NULL,                         /* gp_handler_function DANGER: only initialized on SEH platforms, and is done in hygp.c */
  hystr_startup,                /* str_startup */
  hystr_shutdown,               /* str_shutdown */
  hystr_printf,                 /* str_printf */
  hystr_vprintf,                /* str_vprintf */
  hyexit_startup,               /* exit_startup */
  hyexit_shutdown,              /* exit_shutdown */
  hyexit_get_exit_code,         /* exit_get_exit_code */
  hyexit_shutdown_and_exit,     /* exit_shutdown_and_exit */
  NULL,                         /* self_handle */
  hydump_create,                /* dump_create */
  hynls_startup,                /* nls_startup */
  hynls_shutdown,               /* nls_shutdown */
  hynls_set_catalog,            /* nls_set_catalog */
  hynls_set_locale,             /* nls_set_locale */
  hynls_get_language,           /* nls_get_language */
  hynls_get_region,             /* nls_get_region */
  hynls_get_variant,            /* nls_get_variant */
  hynls_printf,                 /* nls_printf */
  hynls_vprintf,                /* nls_vprintf */
  hynls_lookup_message,         /* nls_lookup_message */
  hyipcmutex_startup,           /* ipcmutex_startup */
  hyipcmutex_shutdown,          /* ipcmutex_shutdown */
  hyipcmutex_acquire,           /* ipcmutex_acquire */
  hyipcmutex_release,           /* ipcmutex_release */
  hyport_control,               /* port_control */
  NULL,                /* sig_startup */
  NULL,               /* sig_shutdown */
  NULL,                /* sig_protect */
  NULL,            /* sig_can_protect */
  NULL,       /* sig_set_async_signal_handler */
  NULL,                   /* sig_info */
  NULL,             /* sig_info_count */
  NULL,            /* sig_set_options */
  NULL,            /* sig_get_options */
  NULL,                         /* attached_thread */
  hysysinfo_DLPAR_enabled,      /* sysinfo_DLPAR_enabled */
  hysysinfo_DLPAR_max_CPUs,     /* sysinfo_DLPAR_max_CPUs */
  hysysinfo_weak_memory_consistency,    /* sysinfo_weak_memory_consistency */
  hyfile_read_text,             /* file_read_text */
  hyfile_mkdir,                 /* file_mkdir */
  hyfile_move,                  /* file_move */
  hyfile_unlinkdir,             /* file_unlinkdir */
  hyfile_findfirst,             /* file_findfirst */
  hyfile_findnext,              /* file_findnext */
  hyfile_findclose,             /* file_findclose */
  hyfile_error_message,         /* file_error_message */
  hysock_htonl,                 /* sock_htonl */
  hysock_bind,                  /* sock_bind */
  hysock_accept,                /* sock_accept */
  hysock_shutdown_input,        /* sock_shutdown_input */
  hysock_shutdown_output,       /* sock_shutdown_output */
  hysock_listen,                /* sock_listen */
  hysock_ntohl,                 /* sock_ntohl */
  hysock_ntohs,                 /* sock_ntohs */
  hysock_getpeername,           /* sock_getpeername */
  hysock_getsockname,           /* sock_getsockname */
  hysock_readfrom,              /* sock_readfrom */
  hysock_select,                /* sock_select */
  hysock_writeto,               /* sock_writeto */
  hysock_inetntoa,              /* sock_inetntoa */
  hysock_gethostbyaddr,         /* sock_gethostbyaddr */
  hysock_gethostname,           /* sock_gethostname */
  hysock_hostent_aliaslist,     /* sock_hostent_aliaslist */
  hysock_hostent_hostname,      /* sock_hostent_hostname */
  hysock_sockaddr_port,         /* sock_sockaddr_port */
  hysock_sockaddr_address,      /* sock_sockaddr_address */
  hysock_fdset_init,            /* sock_fdset_init */
  hysock_fdset_size,            /* sock_fdset_size */
  hysock_timeval_init,          /* sock_timeval_init */
  hysock_getopt_int,            /* sock_getopt_int */
  hysock_setopt_int,            /* sock_setopt_int */
  hysock_getopt_bool,           /* sock_getopt_bool */
  hysock_setopt_bool,           /* sock_setopt_bool */
  hysock_getopt_byte,           /* sock_getopt_byte */
  hysock_setopt_byte,           /* sock_setopt_byte */
  hysock_getopt_linger,         /* sock_getopt_linger */
  hysock_getopt_sockaddr,       /* sock_getopt_sockaddr */
  hysock_setopt_sockaddr,       /* sock_setopt_sockaddr */
  hysock_setopt_ipmreq,         /* sock_setopt_ipmreq */
  hysock_linger_enabled,        /* sock_linger_enabled */
  hysock_linger_linger,         /* sock_linger_linger */
  hysock_ipmreq_init,           /* sock_ipmreq_init */
  hysock_setflag,               /* sock_setflag */
  hysock_freeaddrinfo,          /* sock_freeaddrinfo */
  hysock_getaddrinfo,           /* sock_getaddrinfo */
  hysock_getaddrinfo_address,   /* sock_getaddrinfo_address */
  hysock_getaddrinfo_create_hints,      /* sock_getaddrinfo_create_hints */
  hysock_getaddrinfo_family,    /* sock_getaddrinfo_family */
  hysock_getaddrinfo_length,    /* sock_getaddrinfo_length */
  hysock_getaddrinfo_name,      /* sock_getaddrinfo_name */
  hysock_getnameinfo,           /* sock_getnameinfo */
  hysock_ipv6_mreq_init,        /* sock_ipv6_mreq_init */
  hysock_setopt_ipv6_mreq,      /* sock_setopt_ipv6_mreq */
  hysock_sockaddr_address6,     /* sock_sockaddr_address6 */
  hysock_sockaddr_family,       /* sock_sockaddr_family */
  hysock_sockaddr_init6,        /* sock_sockaddr_init6 */
  hysock_socketIsValid,         /* sock_socketIsValid */
  hysock_select_read,           /* sock_select_read */
  hysock_set_nonblocking,       /* sock_set_nonblocking */
  hysock_error_message,         /* sock_error_message */
  hysock_get_network_interfaces,        /* sock_get_network_interfaces */
  hysock_free_network_interface_struct, /* sock_free_network_interface_struct */
  hysock_connect_with_timeout,  /* sock_connect_with_timeout */
  hystrftime,                   /* str_ftime */
  hymmap_startup,               /* mmap_startup */
  hymmap_shutdown,              /* mmap_shutdown */
  hymmap_capabilities,          /* mmap_capabilities */
  hymmap_map_file,              /* mmap_map_file */
  hymmap_unmap_file,            /* mmap_unmap_file */
  hyshsem_startup,              /* shsem_startup */
  hyshsem_shutdown,             /* shsem_shutdown */
  hyshsem_open,                 /* shsem_open */
  hyshsem_post,                 /* shsem_post */
  hyshsem_wait,                 /* shsem_wait */
  hyshsem_getVal,               /* shsem_getVal */
  hyshsem_setVal,               /* shsem_setVal */
  hyshsem_close,                /* shsem_close */
  hyshsem_destroy,              /* shsem_destroy */
  hyshmem_startup,              /* shmem_startup */
  hyshmem_shutdown,             /* shmem_shutdown */
  hyshmem_open,                 /*shmem_open */
  hyshmem_attach,               /*shmem_attach */
  hyshmem_detach,               /*shmem_detach */
  hyshmem_close,                /*shmem_close */
  hyshmem_destroy,              /*shmem_destroy */
  hyshmem_findfirst,            /*shmem_findfirst */
  hyshmem_findnext,             /* shmem_findnext */
  hyshmem_findclose,            /* shmem_findclose */
  hyshmem_stat,                 /* shmem_stat */
  hysysinfo_get_processing_capacity,    /* sysinfo_get_processing_capacity */
  hybuf_write_text,            /* buf_write_text */
#ifdef HY_NO_THR
  hyport_get_thread_library,    /* port_get_thread_library */
#endif /* HY_NO_THR */
  hysock_fdset_zero,
  hysock_fdset_set,
};
#endif

