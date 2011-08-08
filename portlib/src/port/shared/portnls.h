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

#if !defined(portnls_h)
#define portnls_h
#include "hyport.h"
/* 0x504f5254 = PORT */
#define HYNLS_PORT_NLS_FAILURE__PREFIX "PORT000"

#define HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE__MODULE 0x504f5254
#define HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE__ID 1
#define HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE__MODULE, HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE__ID

#define HYNLS_PORT_SL_UNKOWN_ERROR__MODULE 0x504f5254
#define HYNLS_PORT_SL_UNKOWN_ERROR__ID 2
#define HYNLS_PORT_SL_UNKOWN_ERROR HYNLS_PORT_SL_UNKOWN_ERROR__MODULE, HYNLS_PORT_SL_UNKOWN_ERROR__ID

#define HYNLS_PORT_ERROR_OPERATION_FAILED__MODULE 0x504f5254
#define HYNLS_PORT_ERROR_OPERATION_FAILED__ID 17
#define HYNLS_PORT_ERROR_OPERATION_FAILED HYNLS_PORT_ERROR_OPERATION_FAILED__MODULE, HYNLS_PORT_ERROR_OPERATION_FAILED__ID

#define HYNLS_PORT_SL_UNABLE_TO_RESOLVE_REFERENCES__MODULE 0x504f5254
#define HYNLS_PORT_SL_UNABLE_TO_RESOLVE_REFERENCES__ID 15
#define HYNLS_PORT_SL_UNABLE_TO_RESOLVE_REFERENCES HYNLS_PORT_SL_UNABLE_TO_RESOLVE_REFERENCES__MODULE, HYNLS_PORT_SL_UNABLE_TO_RESOLVE_REFERENCES__ID

#define HYNLS_PORT_SL_INTERNAL_ERROR__MODULE 0x504f5254
#define HYNLS_PORT_SL_INTERNAL_ERROR__ID 16
#define HYNLS_PORT_SL_INTERNAL_ERROR HYNLS_PORT_SL_INTERNAL_ERROR__MODULE, HYNLS_PORT_SL_INTERNAL_ERROR__ID

#endif

