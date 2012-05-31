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
 * @file thread_init.c
 * @brief hythread init/shutdown functions
 */

#undef LOG_DOMAIN
#define LOG_DOMAIN "tm.init"

#include <open/hythread_ext.h>
#include "port_mutex.h"
#include "thread_private.h"

#ifdef HY_NO_THR
#include "thread_classlib_defs.h"
#endif /* HY_NO_THR */

//global constants:

// Global pointer to the threading library
hythread_library_t TM_LIBRARY = NULL;

//Thread manager memory pool
apr_pool_t *TM_POOL = NULL;

//TLS key
apr_threadkey_t *TM_THREAD_KEY;

//Thread manager global lock
osmutex_t TM_START_LOCK;
static int hythread_library_state = TM_LIBRARY_STATUS_NOT_INITIALIZED;
#define GLOBAL_MONITOR_NAME "global_monitor"
hythread_monitor_t p_global_monitor;

//group for the threads created/attached to the NULL group
hythread_group_t TM_DEFAULT_GROUP;
hythread_group_t group_list;

IDATA groups_count;

static IDATA init_group_list();
static IDATA destroy_group_list();

#ifdef WIN32
#include <windows.h>
BOOL WINAPI DllMain(HINSTANCE hInstance, DWORD dwReason, LPVOID lpres) {
  if (dwReason == DLL_PROCESS_ATTACH) {
     hythread_lib_create(&TM_LIBRARY);
   }
   return TRUE;
}
#else
void hythread_library_init(void) {
    hythread_lib_create(&TM_LIBRARY);
}
#endif


#ifdef HY_NO_THR

/**
 * Determine the size of the thread library.
 * 
 * Given a thread library version, return the size of the structure in bytes
 * required to be allocated.
 * 
 * @param[in] version The HyThreadLibraryVersion structure.
 * @return size of thread library on success, zero on failure
 */
UDATA VMCALL
hythread_getSize (struct HyThreadLibraryVersion * version)
{
    /* Can't initialize a structure that is not understood by this version of the thread library */
    if (HYTHREAD_MAJOR_VERSION_NUMBER != version->majorVersionNumber)
    {
        return 0;
    }

    return sizeof (HyThreadLibrary);
}

/*
 * Stub startup function
 */
HY_CFUNC THREXPORT I_32 VMCALL
hythread_startup_library (struct HyThreadLibraryInternal *threadLibrary)
{
    /* Do nothing here - thread library initialised by hythread_allocate_library() */
    return 0;
}

/*
 * Shutdown the thread library - forwards call to hythread_shutdown()
 */
HY_CFUNC THREXPORT I_32 VMCALL
hythread_shutdown_library (struct HyThreadLibraryInternal *threadLibrary)
{
    hythread_shutdown();
	return 0;
}

/*
 * Allocate the thread library function table and return it's pointer
 */
HY_CFUNC THREXPORT I_32 VMCALL
hythread_allocate_library (struct HyThreadLibraryVersion *expectedVersion,
                           struct HyThreadLibrary **threadLibraryFuncs)
{
    UDATA size = hythread_getSize (expectedVersion);
    HyThreadLibrary *threadLib;

    if (0 == size) 
    {
        return -1;
    }

    hythread_lib_create(&TM_LIBRARY);

    /* Allocate memory for the function table */
    *threadLibraryFuncs = NULL;
    threadLib = (HyThreadLibrary*) apr_palloc(TM_POOL, sizeof(HyThreadLibrary));
    if (NULL == threadLib)
    {
        return -1;
    }

    /* Null and initialize the table passed in */
	memset(threadLib, 0, size);
	memcpy(threadLib, &MasterThreadLibraryTable, size);

    /* Set version numbers */
	threadLib->threadVersion.majorVersionNumber = expectedVersion->majorVersionNumber;
	threadLib->threadVersion.minorVersionNumber = expectedVersion->minorVersionNumber;
	threadLib->threadVersion.capabilities = HYTHREAD_CAPABILITY_MASK;

    threadLib->self_handle = threadLib;
    *threadLibraryFuncs = threadLib;

    return 0;
}

#endif /* HY_NO_THR */

/**
 * Creates and initializes a threading library.
 *
 * @param[out] lib pointer to the created thread library
 * @return The thread library's initStatus will be set to 0 on success or 
 * a negative value on failure.
 */
IDATA VMCALL hythread_lib_create(hythread_library_t * lib) {
    apr_status_t apr_status;

    // Current implementation doesn't support more than one library instance.
    if (TM_LIBRARY) {
        *lib = TM_LIBRARY;
        return TM_ERROR_NONE;
    }
    
    apr_status = apr_initialize();
    assert(apr_status == APR_SUCCESS);

    apr_status = apr_pool_create(&TM_POOL, NULL);
    if (apr_status != APR_SUCCESS) return CONVERT_ERROR(apr_status);

    *lib = (hythread_library_t) apr_palloc(TM_POOL, sizeof(HyThreadLibraryInternal));
    if (*lib == NULL) return TM_ERROR_OUT_OF_MEMORY;

    hythread_init(*lib);
    return TM_ERROR_NONE;
}

/**
 * Shut down the threading library.
 * 
 * @param lib the library
 * @return none
 * 
 * @see hythread_lib_create
 */
void VMCALL hythread_lib_destroy(hythread_library_t lib) {
    apr_pool_destroy(TM_POOL);
}

/**
 * Initialize a threading library.
 * 
 * @note This must only be called once.
 * 
 * If any OS threads were created before calling this function, they must be attached using
 * hythread_attach before accessing any thread library functions. 
 * 
 * @param[in] lib pointer to the thread library to be initialized (non-NULL)
 * @return The thread library's initStatus will be set to 0 on success or 
 * a negative value on failure.
 */
void VMCALL hythread_init(hythread_library_t lib) {
    apr_status_t apr_status;
    IDATA status;
    hythread_monitor_t *mon;

    // Current implementation doesn't support more than one library instance.
    if (TM_LIBRARY == NULL) {
        TM_LIBRARY = lib;
    }
    assert(TM_LIBRARY == lib);

    if (hythread_library_state != TM_LIBRARY_STATUS_NOT_INITIALIZED)
        return;
    hythread_library_state = TM_LIBRARY_STATUS_INITIALIZED;
     
    apr_status = apr_initialize();
    assert(apr_status == APR_SUCCESS);
    // TM_POOL will be NULL if hythread_lib_create was not used to create the library
    if (TM_POOL == NULL) {
        apr_status = apr_pool_create(&TM_POOL, NULL);
        assert(apr_status == APR_SUCCESS);
    }

    apr_status = apr_threadkey_private_create(&TM_THREAD_KEY, NULL, TM_POOL);
    assert(apr_status == APR_SUCCESS);
    
    status = port_mutex_create(&lib->TM_LOCK, APR_THREAD_MUTEX_NESTED);
    assert(status == TM_ERROR_NONE);
    status = port_mutex_create(&TM_START_LOCK, APR_THREAD_MUTEX_NESTED);
    assert(status == TM_ERROR_NONE);
     
    status = init_group_list();
    assert(status == TM_ERROR_NONE);

    // Create default group - hosts any thread crated with NULL group
    status = hythread_group_create(&TM_DEFAULT_GROUP);
    assert(status == TM_ERROR_NONE);

    //nondaemon thread barrier
    ////
    lib->nondaemon_thread_count = 0;
    status = hycond_create(&lib->nondaemon_thread_cond);
    assert(status == TM_ERROR_NONE);
 
    // init global monitor
    status=hythread_monitor_init_with_name(&p_global_monitor, 0, "Thread Global Monitor");
    assert(status == TM_ERROR_NONE);

    mon = (hythread_monitor_t*)hythread_global(GLOBAL_MONITOR_NAME);
    *mon = p_global_monitor;
    assert(mon);
}

/**
 * Prepares to shutdown the hythread library.
 *
 * @return none
 *
 * @see hythread_init
 */
void VMCALL hythread_shutdowning() {
    hythread_library_state = TM_LIBRARY_STATUS_SHUTDOWN;
}

/**
 * Returns hythread library state
 */
int VMCALL hythread_lib_state() {
    return hythread_library_state;
}

/**
 * Shut down the threading library associated with the current thread.
 * 
 * @return none
 * 
 * @see hythread_init
 */
void VMCALL hythread_shutdown() {
    hythread_lib_destroy(hythread_self()->library);
}

/**
 * Acquires global lock of the library associated with the current thread.
 *
 * @param[in] self current thread
 */
void VMCALL hythread_lib_lock(hythread_t self) {
    IDATA status;
    
    assert(self == hythread_self());
    status = port_mutex_lock(&self->library->TM_LOCK);
    assert(status == TM_ERROR_NONE);
}

/**
 * Releases global lock of the library associated with the current thread.
 *
 * @param[in] self current thread
 */
void VMCALL hythread_lib_unlock(hythread_t self) {
    IDATA status;

    assert(self == hythread_self());
    status = port_mutex_unlock(&self->library->TM_LOCK);
    assert(status == TM_ERROR_NONE);
}

/**
 * Acquires the lock over threading subsystem.
 * 
 * The lock blocks new thread creation and thread exit operations. 
 */
IDATA VMCALL hythread_global_lock() {
    IDATA status;
    hythread_t self = hythread_self();

    // we need not care about suspension if the thread
    // is not even attached to hythread
    if (self == NULL) {
        return port_mutex_lock(&TM_LIBRARY->TM_LOCK);
    }

    // disable_count must be 0 on potentially
    // blocking operation to prevent suspension deadlocks,
    // meaning that the thread is safe for suspension
    assert(hythread_is_suspend_enabled());

    status = port_mutex_lock(&TM_LIBRARY->TM_LOCK);
    assert(status == TM_ERROR_NONE);

    // make sure we do not get a global thread lock
    // while being requested to suspend
    while (self->suspend_count) {
        // give up global thread lock before safepoint,
        // because this thread can be suspended at a safepoint
        status = port_mutex_unlock(&TM_LIBRARY->TM_LOCK);
        assert(status == TM_ERROR_NONE);
        hythread_safe_point();
        status = port_mutex_lock(&TM_LIBRARY->TM_LOCK);
        assert(status == TM_ERROR_NONE);
    }
    return TM_ERROR_NONE;
}

/**
 * Releases the lock over threading subsystem.
 * 
 */
IDATA VMCALL hythread_global_unlock() {
    IDATA status;
    assert(!hythread_self() || hythread_is_suspend_enabled());
    status = port_mutex_unlock(&TM_LIBRARY->TM_LOCK);
    assert(status == TM_ERROR_NONE);
    return TM_ERROR_NONE;
}

hythread_group_t VMCALL get_java_thread_group(void) {
    return TM_DEFAULT_GROUP;
}

static IDATA init_group_list() {
    // Initial group, does not contain any actual group, but serves 
    //as a head and a tail of this list;
    hythread_group_t dummy;

    
    //this group will exist as long as TM lives, so it's ok to have 
    //the same pool for them
    ////
    dummy = (hythread_group_t)apr_pcalloc(TM_POOL, sizeof(HyThreadGroup));
    assert(dummy);

    dummy->next = dummy->prev = dummy;
    group_list = dummy;
    groups_count = 0;

    lock_table = (HyFatLockTable *) malloc (sizeof(HyFatLockTable));
    memset(lock_table, 0, sizeof(HyFatLockTable));
    lock_table->tables[0] = (hythread_monitor_t *)calloc(HY_FAT_TABLE_ENTRIES,
                                              sizeof(hythread_monitor_t));
    lock_table->live_objs = (unsigned char *)calloc(HY_FAT_TABLE_ENTRIES,
                                         sizeof(unsigned char));
    lock_table->size = HY_FAT_TABLE_ENTRIES;
    lock_table->array_cursor = 0;

    assert (lock_table);
    assert (lock_table->tables[0]);
    assert (lock_table->live_objs);
    
    if (port_mutex_create(&lock_table->mutex, APR_THREAD_MUTEX_NESTED)) {
        return TM_ERROR_OUT_OF_MEMORY;
    }

    if (hycond_create(&lock_table->write)) {
        return TM_ERROR_OUT_OF_MEMORY;
    }

    if (hycond_create(&lock_table->read)) {
        return TM_ERROR_OUT_OF_MEMORY;
    }
    
    lock_table->readers_reading = 0;
    lock_table->readers_waiting = 0;
    lock_table->writers_waiting = 0;
    lock_table->state = HYTHREAD_LOCKTABLE_IDLE;

    return TM_ERROR_NONE;
}

static IDATA destroy_group_list() {
    hythread_group_t cur;
    IDATA status,status2;
    int i;

    // This method works only if there are no running threads.
    // there is no good way to kill running threads 
    status=hythread_global_lock();
    if (status != TM_ERROR_NONE) return status;

    cur = group_list->next;
    status = TM_ERROR_NONE;
    
    while (cur != group_list) {
        if (hythread_group_release(cur) == TM_ERROR_NONE) {
            cur = group_list->next;
        } else {
            status = TM_ERROR_RUNNING_THREADS;
            cur = cur->next;
        }
    }

    free(lock_table->live_objs);

    for (i = 0; i < HY_MAX_FAT_TABLES && lock_table->tables[i]; i++) {
        free(lock_table->tables[i]);
    }

    port_mutex_destroy(&lock_table->mutex);
    hycond_destroy(&lock_table->write);
    hycond_destroy(&lock_table->read);
    
    free(lock_table);

    status2=hythread_global_unlock();
    if (status2 != TM_ERROR_NONE) return status2;

    return status;
}

IDATA acquire_start_lock() {
    return port_mutex_lock(&TM_START_LOCK);
}

IDATA release_start_lock() {
    return port_mutex_unlock(&TM_START_LOCK);
}

/*
// very simple Map implementation
// current scenario use only one global so it works well
// need to be hashtable in the future
*/
#define TABLE_SIZE 256
const char *names[TABLE_SIZE];
UDATA data[TABLE_SIZE];
int size = 0;

/*
 * return index in array if found, -1 otherwise
 */
int find_entry (const char* name) {
    // quick pass
    int i;
    for (i = 0; i < size; i++) {
        if (names[i] == name) {
            return i;
        }
    }
    // strcmp pass.
    for (i = 0; i < size; i++) {
        if (strcmp(names[i], name) == 0) {
            return i;
        }
    }
    return -1;
}
//add entry to the end of the array
// return new entry index,  -1 if failed.
int add_entry(const char* name) {
    int index = size++;
    if (index >= TABLE_SIZE-1) {
        return -1;
    }
    names[index] = name;
    data[index] = 0;
    return index;
}

/** 
 * Fetch or create a 'named global'.
 *
 * Return a pointer to the data associated with a named global with the specified name.<br>
 * A new named global is created if a named global with the specified name can't be found.
 *
 * @param[in] name name of named global to read/create
 * @return a pointer to a UDATA associated with name<br>
 * 0 on failure.
 * 
 */
UDATA* VMCALL hythread_global (const char* name) {
    //hythread_monitor_enter(*p_global_monitor);
    int index = find_entry(name);
    if (index == -1) {
        index = add_entry(name);
        assert(index >=0);
        if (index < 0) {
            //hythread_monitor_exit(*p_global_monitor);
            return NULL;
        }
    }
    //hythread_monitor_exit(*p_global_monitor);
    return data+index;
}

