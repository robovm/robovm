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

#if !defined(hyshmem_h)
#define hyshmem_h
#include <sys/types.h>
typedef struct hyshmem_handle
{
  I_32 shmid;
  char *baseFileName;
  void *regionStart;
  I_64 timestamp;
  I_32 baseFilefd;
} hyshmem_handle;
typedef struct hyshmem_controlFileFormat
{
  I_32 version;
  I_32 modlevel;
  key_t ftok_key;
  I_32 proj_id;
  I_32 shmid;
  I_64 size;
  I_32 uid;
  I_32 gid;
} hyshmem_controlFileFormat;
#endif /* hyshmem_h */
