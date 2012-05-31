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
 * @author Nikolay Kuznetsov
 */  

/**
 * @file thread_native_groups.c
 * @brief Hythread group related functions
 */

#include <open/hythread_ext.h>
#include "thread_private.h"

extern hythread_group_t group_list;  // list of thread groups
extern IDATA groups_count;        // number of thread groups

   
/** @name Thread groups support
 */
//@{

/**
 * Creates the thread group.
 *
 * @param[out] group thread group created
 */
IDATA VMCALL hythread_group_create(hythread_group_t *group) {
    hythread_t dummy;
    apr_pool_t  *group_pool;
    apr_status_t apr_status;
    IDATA status;
    
    apr_status = apr_pool_create(&(group_pool), TM_POOL);
    if (apr_status != APR_SUCCESS) return CONVERT_ERROR(apr_status);
    
    status=hythread_global_lock();
    if (status != TM_ERROR_NONE) return status;
    
    (*group) = (hythread_group_t )apr_palloc(group_pool, sizeof(HyThreadGroup));
    if (!*group) {
       hythread_global_unlock();
       return TM_ERROR_OUT_OF_MEMORY;
    }

    (*group)->pool         = group_pool;
    (*group)->next         = group_list;
    (*group)->prev         = group_list->prev;

    group_list->prev->next = (*group);
    group_list->prev       = (*group);
    
    //dummy thread as the head of the group
    ////
    dummy = (hythread_t)apr_pcalloc(group_pool, sizeof(HyThread));
    if (!dummy) {
        hythread_global_unlock();
        return TM_ERROR_OUT_OF_MEMORY;
    }
    dummy->next = dummy->prev = dummy;
    dummy->group = (*group);
    dummy->thread_id = 0;
    
    (*group)->thread_list  = dummy;
    (*group)->threads_count = 0;
    
    groups_count++;
    
    status=hythread_global_unlock();

    return status;
}

/**
 * Releases the thread group.
 *
 * @param[out] group thread group created
 */

IDATA VMCALL hythread_group_release(hythread_group_t group) {
        IDATA status;
    if (group->threads_count > 0) return TM_ERROR_RUNNING_THREADS;
    
    status=hythread_global_lock();
        if (status != TM_ERROR_NONE) return status;
    groups_count--;
    group->prev->next = group->next;
    group->next->prev = group->prev;
    
    apr_pool_destroy(group->pool);
    status=hythread_global_unlock();
    
    return status;
}

/**
 * Returns all thread groups as array.
 *
 * @param[out] list thread group array
 * @param[out] size array size
 */
 IDATA VMCALL hythread_group_get_list(hythread_group_t **list, int* size) {
     hythread_group_t cur;
     int i=0;
     hythread_group_t *array_for_list;
     IDATA status;
     status=hythread_global_lock();
     if (status != TM_ERROR_NONE) 
         return status;
     (*size) = groups_count;
     array_for_list=(hythread_group_t*)malloc(sizeof(hythread_group_t)*groups_count);
     if (array_for_list==NULL)
     { 
         status=hythread_global_unlock();
         return TM_ERROR_OUT_OF_MEMORY;
     }
     for (cur = group_list->next; cur != group_list; cur = cur->next)
     {
         array_for_list[i++]=cur;
     }
     (*list)=array_for_list;
     status=hythread_global_unlock();
     return status;   
}

//@}
