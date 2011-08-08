/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "hyport.h"

#include "zlib.h"

#define CDEV_CURRENT_FUNCTION _prototypes_private

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION _prototypes_public
void *zalloc PROTOTYPE ((void *opaque, U_32 items, U_32 size));
void zfree PROTOTYPE ((void *opaque, void *address));

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zalloc

/*
	ZLib interface to hymem_allocate_memory.
*/
void *
zalloc (void *opaque, U_32 items, U_32 size)
{
  PORT_ACCESS_FROM_PORT (((HyPortLibrary *) opaque));

  return hymem_allocate_memory (items * size);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION zfree

/*
	ZLib interface to hymem_free_memory.
*/
void
zfree (void *opaque, void *address)
{
  PORT_ACCESS_FROM_PORT ((HyPortLibrary *) opaque);

  hymem_free_memory (address);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION

#undef CDEV_CURRENT_FUNCTION
