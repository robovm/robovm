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

#if !defined(hymutex_h)
#define hymutex_h

#include <pthread.h>
#include <stdlib.h>

typedef pthread_mutex_t MUTEX;

/* MUTEX_INIT */
#define MUTEX_INIT(mutex) (pthread_mutex_init(&(mutex), NULL) == 0)

/* MUTEX_DESTROY */
#define MUTEX_DESTROY(mutex) pthread_mutex_destroy(&(mutex))

/* MUTEX_ENTER */
#define MUTEX_ENTER(mutex) pthread_mutex_lock(&(mutex))

/*
 *  MUTEX_TRY_ENTER 
 *  returns 0 on success
 */
#define MUTEX_TRY_ENTER(mutex) pthread_mutex_trylock(&(mutex))

/* MUTEX_EXIT */
#define MUTEX_EXIT(mutex) pthread_mutex_unlock(&(mutex))

#endif     /* hymutex_h */
