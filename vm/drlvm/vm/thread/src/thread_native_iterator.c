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
 * @file thread_native_iterator.c
 * @brief Hythread iterator related functions
 */
#define LOG_DOMAIN "tm.iterator"

#include "thread_private.h"
#include <open/hythread_ext.h>


/**
 * Creates the iterator that can be used to retrieve all threads in the specific group
 * and acquires the thread lock.
 * 
 * @param[in] group thread group number
 */
hythread_iterator_t VMCALL hythread_iterator_create(hythread_group_t group) {
    IDATA status;
    status = hythread_global_lock();
    assert(status == TM_ERROR_NONE);
    group = (group)?group:TM_DEFAULT_GROUP;
    CTRACE(("TM iterator created: %p head: %p", group->thread_list, group->thread_list->next));
    return (hythread_iterator_t)group->thread_list->next;
}

/**
 * Releases the iterator over the specific thread group and releases the thread
 * lock.
 * 
 * @param[in] it thread group iterator
 */
IDATA VMCALL hythread_iterator_release(hythread_iterator_t *it) {
    CTRACE(("TM iterator released: %p", (*it)->group->thread_list));
    return hythread_global_unlock();
}

/**
 * Resets the iterator such that it will start from the beginning.
 * 
 * @param[in] it thread group iterator
 */
IDATA VMCALL hythread_iterator_reset(hythread_iterator_t *it) {
   (*it) = (*it)->group->thread_list->next;
   CTRACE(("TM iterator reset: %p head: %p", (*it)->group->thread_list, (*it))); 
   return TM_ERROR_NONE;   
}

/**
 * Returns the next element in the thread group traversed 
 * by the given iterator.
 * 
 * @param[in] it thread group iterator
 */
hythread_t VMCALL hythread_iterator_next(hythread_iterator_t *it) {
    hythread_t res;
    
    if ((*it) == ((hythread_t)*it)->group->thread_list) {
        CTRACE(("TM iterator %p next: NULL", (*it)->group->thread_list));
        return NULL;
    } 
    
    res = *it;
    (*it) = (*it)->next;
    CTRACE(("TM iterator %p next: %p", (*it)->group->thread_list, res));
    return res;
} 

/**
 * Returns true if there is next element in a list.
 * 
 * @param[in] it thread group iterator
 */
IDATA VMCALL hythread_iterator_has_next(hythread_iterator_t it) {
    IDATA res = (it != ((hythread_t)it)->group->thread_list)? 1 : 0 ;
    CTRACE(("TM iterator %p has next: %d", it->group->thread_list, res));
    return res;
    
}

/**
 * Returns the size of a list traversed by the given iterator.
 * 
 * @param[in] iterator thread group iterator
 */
IDATA VMCALL hythread_iterator_size(hythread_iterator_t iterator) {
    IDATA size = ((hythread_t )iterator)->group->threads_count;
    CTRACE(("TM iterator %p size: %d", iterator->group->thread_list, size));
    return size;
}
