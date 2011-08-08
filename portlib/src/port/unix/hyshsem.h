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

#if !defined(hyshsem_h)
#define hyshsem_h
#include <sys/types.h>
#include <errno.h>
#if defined(_SEM_SEMUN_UNDEFINED) || defined(AIX) || defined(ZOS)
/* according to X/OPEN we have to define it ourselves */
union semun
{
  int val;                      /* value for SETVAL */
  struct semid_ds *buf;         /* buffer for IPC_STAT, IPC_SET */
  unsigned short int *array;    /* array for GETALL, SETALL */
  struct seminfo *__buf;        /* buffer for IPC_INFO */
};
#endif
/* This is the control file contents */
typedef struct hyshsem_baseFileFormat
{
  I_32 version;
  I_32 modlevel;
  I_32 timeout;
  I_32 proj_id;
  key_t ftok_key;
  I_32 semid;
  I_32 creator_pid;
  I_32 semsetSize;
} hyshsem_baseFileFormat;
typedef struct hyshsem_handle
{
  I_32 semid;
  I_32 nsems;
  char *baseFile;
  I_64 timestamp;
  I_32 baseFilefd;
  /* TODO: do we need the length of baseFile? */
} hyshsem_handle;
#define HYSHSEM_SEMFLAGS (IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR)
#define HYSHSEM_SEMFLAGS_GROUP (IPC_CREAT | IPC_EXCL | S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP)
#define HYSHSEM_SEMMARKER 769
/* for zOS we have a function to get extended error code - useful for debugging */
#if !defined(ZOS)
#define __errno2() 0
#endif /* ZOS */
#endif /* hyshsem_h */
