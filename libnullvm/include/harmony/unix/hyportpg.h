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

#if !defined(hyportpg_h)
#define hyportpg_h
/** Number of pageSizes supported.  There is always 1 for the default size, and 1 for the 0 terminator.
 * The number of large pages supported determines the remaining size.
 * Responsibility of the implementation of hyvmem to initialize this table correctly.
 */
#define HYPORT_VMEM_PAGESIZE_COUNT 3
typedef struct HyPortPlatformGlobals
{
#if defined(HYPPC32) || defined(HYPPC64)
  int mem_ppcCacheLineSize;
#endif
  char *si_osType;
  char *si_osVersion;
  UDATA vmem_pageSize[HYPORT_VMEM_PAGESIZE_COUNT];       /** <0 terminated array of supported page sizes */
} HyPortPlatformGlobals;
#if defined(HYPPC32) || defined(HYPPC64)
#define PPG_mem_ppcCacheLineSize (portLibrary->portGlobals->platformGlobals.mem_ppcCacheLineSize)
#endif
#define PPG_si_osType (portLibrary->portGlobals->platformGlobals.si_osType)
#define PPG_si_osVersion (portLibrary->portGlobals->platformGlobals.si_osVersion)
#define PPG_vmem_pageSize (portLibrary->portGlobals->platformGlobals.vmem_pageSize)
#endif /* hyportpg_h */
