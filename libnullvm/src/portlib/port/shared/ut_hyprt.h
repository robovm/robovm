/* Licensed to the Apache Software Foundation (ASF) under one or more
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

#if !defined(_UTE_MODULE_HEADER_)
#define _UTE_MODULE_HEADER_
#include "ute_module.h"
#if !defined(UT_TRACE_OVERHEAD)
#define UT_TRACE_OVERHEAD 0
#endif

#if !defined(UT_THREAD)
#define UT_THREAD(thr) (void *)thr
#endif /* UT_THREAD */

#if defined(__cplusplus)
extern "C"
{
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_PortInitStages_Event1() do { \
	if ((unsigned char) hyprt_UtActive[0] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((0 << 8) | hyprt_UtActive[0]), NULL);} \
	} while(0)
#else
#define Trc_PRT_PortInitStages_Event1() /* tracepoint name: hyprt.0 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[1] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((1 << 8) | hyprt_UtActive[1]), "\377\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_file_open_Entry(P1, P2, P3)     /* tracepoint name: hyprt.1 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[2] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((2 << 8) | hyprt_UtActive[2]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_file_open_Exit(P1)      /* tracepoint name: hyprt.2 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Exit1(P1) do { \
	if ((unsigned char) hyprt_UtActive[3] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((3 << 8) | hyprt_UtActive[3]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_file_open_Exit1(P1)     /* tracepoint name: hyprt.3 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Exit2(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[4] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((4 << 8) | hyprt_UtActive[4]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_file_open_Exit2(P1, P2) /* tracepoint name: hyprt.4 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Exit3(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[5] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((5 << 8) | hyprt_UtActive[5]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_file_open_Exit3(P1, P2) /* tracepoint name: hyprt.5 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_file_open_Exit4() do { \
	if ((unsigned char) hyprt_UtActive[6] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((6 << 8) | hyprt_UtActive[6]), NULL);} \
	} while(0)
#else
#define Trc_PRT_file_open_Exit4()       /* tracepoint name: hyprt.6 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createFile_Entry() do { \
	if ((unsigned char) hyprt_UtActive[7] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((7 << 8) | hyprt_UtActive[7]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createFile_Entry()        /* tracepoint name: hyprt.7 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createFile_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[8] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((8 << 8) | hyprt_UtActive[8]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createFile_Exit1()        /* tracepoint name: hyprt.8 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createFile_Exit2(P1) do { \
	if ((unsigned char) hyprt_UtActive[9] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((9 << 8) | hyprt_UtActive[9]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_createFile_Exit2(P1)      /* tracepoint name: hyprt.9 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createFile_Exit() do { \
	if ((unsigned char) hyprt_UtActive[10] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((10 << 8) | hyprt_UtActive[10]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createFile_Exit() /* tracepoint name: hyprt.10 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Entry() do { \
	if ((unsigned char) hyprt_UtActive[11] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((11 << 8) | hyprt_UtActive[11]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Entry()        /* tracepoint name: hyprt.11 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_ftok(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[12] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((12 << 8) | hyprt_UtActive[12]), "\377\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_ftok(P1, P2)   /* tracepoint name: hyprt.12 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[13] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((13 << 8) | hyprt_UtActive[13]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit1()        /* tracepoint name: hyprt.13 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[14] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((14 << 8) | hyprt_UtActive[14]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit2()        /* tracepoint name: hyprt.14 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit3() do { \
	if ((unsigned char) hyprt_UtActive[15] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((15 << 8) | hyprt_UtActive[15]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit3()        /* tracepoint name: hyprt.15 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit4() do { \
	if ((unsigned char) hyprt_UtActive[16] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((16 << 8) | hyprt_UtActive[16]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit4()        /* tracepoint name: hyprt.16 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_shmget(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[17] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((17 << 8) | hyprt_UtActive[17]), "\4\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_shmget(P1, P2, P3)     /* tracepoint name: hyprt.17 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_shmget_Event1(P1) do { \
	if ((unsigned char) hyprt_UtActive[18] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((18 << 8) | hyprt_UtActive[18]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_shmget_Event1(P1)      /* tracepoint name: hyprt.18 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit5(P1) do { \
	if ((unsigned char) hyprt_UtActive[19] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((19 << 8) | hyprt_UtActive[19]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit5(P1)      /* tracepoint name: hyprt.19 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit6(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[20] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((20 << 8) | hyprt_UtActive[20]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit6(P1, P2)  /* tracepoint name: hyprt.20 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Event1(P1) do { \
	if ((unsigned char) hyprt_UtActive[21] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((21 << 8) | hyprt_UtActive[21]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Event1(P1)     /* tracepoint name: hyprt.21 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit7() do { \
	if ((unsigned char) hyprt_UtActive[22] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((22 << 8) | hyprt_UtActive[22]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit7()        /* tracepoint name: hyprt.22 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit8() do { \
	if ((unsigned char) hyprt_UtActive[23] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((23 << 8) | hyprt_UtActive[23]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit8()        /* tracepoint name: hyprt.23 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_createSharedMemory_Exit() do { \
	if ((unsigned char) hyprt_UtActive[24] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((24 << 8) | hyprt_UtActive[24]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_createSharedMemory_Exit() /* tracepoint name: hyprt.24 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_attach_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[25] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((25 << 8) | hyprt_UtActive[25]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_attach_Entry(P1)  /* tracepoint name: hyprt.25 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_attach_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[26] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((26 << 8) | hyprt_UtActive[26]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_attach_Exit(P1)   /* tracepoint name: hyprt.26 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_attach_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[27] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((27 << 8) | hyprt_UtActive[27]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_attach_Exit1()    /* tracepoint name: hyprt.27 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_attach_Exit2(P1) do { \
	if ((unsigned char) hyprt_UtActive[28] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((28 << 8) | hyprt_UtActive[28]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_attach_Exit2(P1)  /* tracepoint name: hyprt.28 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_attach_Debug1(P1) do { \
	if ((unsigned char) hyprt_UtActive[29] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((29 << 8) | hyprt_UtActive[29]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_attach_Debug1(P1) /* tracepoint name: hyprt.29 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_close_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[30] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((30 << 8) | hyprt_UtActive[30]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_close_Entry(P1)   /* tracepoint name: hyprt.30 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_close_Exit() do { \
	if ((unsigned char) hyprt_UtActive[31] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((31 << 8) | hyprt_UtActive[31]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_close_Exit()      /* tracepoint name: hyprt.31 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_destroy_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[32] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((32 << 8) | hyprt_UtActive[32]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_destroy_Entry(P1) /* tracepoint name: hyprt.32 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_destroy_Exit() do { \
	if ((unsigned char) hyprt_UtActive[33] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((33 << 8) | hyprt_UtActive[33]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_destroy_Exit()    /* tracepoint name: hyprt.33 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_destroy_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[34] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((34 << 8) | hyprt_UtActive[34]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_destroy_Exit1()   /* tracepoint name: hyprt.34 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_destroy_Debug1(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[35] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((35 << 8) | hyprt_UtActive[35]), "\377\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_destroy_Debug1(P1, P2, P3)        /* tracepoint name: hyprt.35 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_detach_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[36] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((36 << 8) | hyprt_UtActive[36]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_detach_Entry(P1)  /* tracepoint name: hyprt.36 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_detach_Exit() do { \
	if ((unsigned char) hyprt_UtActive[37] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((37 << 8) | hyprt_UtActive[37]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_detach_Exit()     /* tracepoint name: hyprt.37 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_detach_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[38] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((38 << 8) | hyprt_UtActive[38]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_detach_Exit1()    /* tracepoint name: hyprt.38 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findclose_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[39] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((39 << 8) | hyprt_UtActive[39]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findclose_Entry(P1)       /* tracepoint name: hyprt.39 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findclose_Exit() do { \
	if ((unsigned char) hyprt_UtActive[40] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((40 << 8) | hyprt_UtActive[40]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findclose_Exit()  /* tracepoint name: hyprt.40 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findfirst_Entry() do { \
	if ((unsigned char) hyprt_UtActive[41] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((41 << 8) | hyprt_UtActive[41]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findfirst_Entry() /* tracepoint name: hyprt.41 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findfirst_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[42] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((42 << 8) | hyprt_UtActive[42]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findfirst_Exit1() /* tracepoint name: hyprt.42 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findfirst_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[43] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((43 << 8) | hyprt_UtActive[43]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findfirst_Exit2() /* tracepoint name: hyprt.43 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findfirst_Exit() do { \
	if ((unsigned char) hyprt_UtActive[44] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((44 << 8) | hyprt_UtActive[44]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findfirst_Exit()  /* tracepoint name: hyprt.44 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findnext_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[45] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((45 << 8) | hyprt_UtActive[45]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findnext_Entry(P1)        /* tracepoint name: hyprt.45 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findnext_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[46] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((46 << 8) | hyprt_UtActive[46]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findnext_Exit1()  /* tracepoint name: hyprt.46 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findnext_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[47] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((47 << 8) | hyprt_UtActive[47]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findnext_Exit2()  /* tracepoint name: hyprt.47 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_findnext_Exit() do { \
	if ((unsigned char) hyprt_UtActive[48] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((48 << 8) | hyprt_UtActive[48]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_findnext_Exit()   /* tracepoint name: hyprt.48 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[49] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((49 << 8) | hyprt_UtActive[49]), "\377\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Entry(P1, P2, P3)    /* tracepoint name: hyprt.49 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Event1(P1) do { \
	if ((unsigned char) hyprt_UtActive[50] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((50 << 8) | hyprt_UtActive[50]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Event1(P1)   /* tracepoint name: hyprt.50 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Event2(P1) do { \
	if ((unsigned char) hyprt_UtActive[51] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((51 << 8) | hyprt_UtActive[51]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Event2(P1)   /* tracepoint name: hyprt.51 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Event3(P1) do { \
	if ((unsigned char) hyprt_UtActive[52] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((52 << 8) | hyprt_UtActive[52]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Event3(P1)   /* tracepoint name: hyprt.52 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[53] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((53 << 8) | hyprt_UtActive[53]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Exit1()      /* tracepoint name: hyprt.53 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[54] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((54 << 8) | hyprt_UtActive[54]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Exit2()      /* tracepoint name: hyprt.54 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Exit(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[55] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((55 << 8) | hyprt_UtActive[55]), "\4\6", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Exit(P1, P2) /* tracepoint name: hyprt.55 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_stat_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[56] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((56 << 8) | hyprt_UtActive[56]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_stat_Entry(P1)    /* tracepoint name: hyprt.56 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_stat_Exit1(P1) do { \
	if ((unsigned char) hyprt_UtActive[57] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((57 << 8) | hyprt_UtActive[57]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_stat_Exit1(P1)    /* tracepoint name: hyprt.57 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_stat_Exit2(P1) do { \
	if ((unsigned char) hyprt_UtActive[58] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((58 << 8) | hyprt_UtActive[58]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_stat_Exit2(P1)    /* tracepoint name: hyprt.58 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_stat_Exit3(P1) do { \
	if ((unsigned char) hyprt_UtActive[59] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((59 << 8) | hyprt_UtActive[59]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_stat_Exit3(P1)    /* tracepoint name: hyprt.59 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_stat_Exit() do { \
	if ((unsigned char) hyprt_UtActive[60] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((60 << 8) | hyprt_UtActive[60]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_stat_Exit()       /* tracepoint name: hyprt.60 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[61] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((61 << 8) | hyprt_UtActive[61]), "\377\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Entry(P1, P2, P3)    /* tracepoint name: hyprt.61 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Debug1(P1) do { \
	if ((unsigned char) hyprt_UtActive[62] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((62 << 8) | hyprt_UtActive[62]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Debug1(P1)   /* tracepoint name: hyprt.62 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Event1(P1) do { \
	if ((unsigned char) hyprt_UtActive[63] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((63 << 8) | hyprt_UtActive[63]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Event1(P1)   /* tracepoint name: hyprt.63 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Event2(P1) do { \
	if ((unsigned char) hyprt_UtActive[64] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((64 << 8) | hyprt_UtActive[64]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Event2(P1)   /* tracepoint name: hyprt.64 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Event3(P1) do { \
	if ((unsigned char) hyprt_UtActive[65] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((65 << 8) | hyprt_UtActive[65]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Event3(P1)   /* tracepoint name: hyprt.65 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[66] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((66 << 8) | hyprt_UtActive[66]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Exit1()      /* tracepoint name: hyprt.66 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[67] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((67 << 8) | hyprt_UtActive[67]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Exit2()      /* tracepoint name: hyprt.67 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Exit(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[68] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((68 << 8) | hyprt_UtActive[68]), "\4\6", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Exit(P1, P2) /* tracepoint name: hyprt.68 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_close_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[69] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((69 << 8) | hyprt_UtActive[69]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_close_Entry(P1)   /* tracepoint name: hyprt.69 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_close_Exit() do { \
	if ((unsigned char) hyprt_UtActive[70] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((70 << 8) | hyprt_UtActive[70]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_close_Exit()      /* tracepoint name: hyprt.70 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_destroy_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[71] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((71 << 8) | hyprt_UtActive[71]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_destroy_Entry(P1) /* tracepoint name: hyprt.71 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_destroy_Exit() do { \
	if ((unsigned char) hyprt_UtActive[72] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((72 << 8) | hyprt_UtActive[72]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_destroy_Exit()    /* tracepoint name: hyprt.72 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_destroy_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[73] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((73 << 8) | hyprt_UtActive[73]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_destroy_Exit1()   /* tracepoint name: hyprt.73 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_destroy_Debug1(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[74] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((74 << 8) | hyprt_UtActive[74]), "\377\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_destroy_Debug1(P1, P2, P3)        /* tracepoint name: hyprt.74 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_getVal_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[75] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((75 << 8) | hyprt_UtActive[75]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_getVal_Entry(P1, P2)      /* tracepoint name: hyprt.75 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_getVal_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[76] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((76 << 8) | hyprt_UtActive[76]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_getVal_Exit1()    /* tracepoint name: hyprt.76 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_getVal_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[77] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((77 << 8) | hyprt_UtActive[77]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_getVal_Exit2()    /* tracepoint name: hyprt.77 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_getVal_Exit3(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[78] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((78 << 8) | hyprt_UtActive[78]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_getVal_Exit3(P1, P2)      /* tracepoint name: hyprt.78 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_getVal_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[79] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((79 << 8) | hyprt_UtActive[79]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_getVal_Exit(P1)   /* tracepoint name: hyprt.79 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_post_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[80] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((80 << 8) | hyprt_UtActive[80]), "\6\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_post_Entry(P1, P2, P3)    /* tracepoint name: hyprt.80 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_post_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[81] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((81 << 8) | hyprt_UtActive[81]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_post_Exit1()      /* tracepoint name: hyprt.81 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_post_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[82] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((82 << 8) | hyprt_UtActive[82]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_post_Exit2()      /* tracepoint name: hyprt.82 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_post_Exit3(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[83] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((83 << 8) | hyprt_UtActive[83]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_post_Exit3(P1, P2)        /* tracepoint name: hyprt.83 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_post_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[84] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((84 << 8) | hyprt_UtActive[84]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_post_Exit(P1)     /* tracepoint name: hyprt.84 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_setVal_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[85] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((85 << 8) | hyprt_UtActive[85]), "\6\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_setVal_Entry(P1, P2, P3)  /* tracepoint name: hyprt.85 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_setVal_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[86] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((86 << 8) | hyprt_UtActive[86]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_setVal_Exit1()    /* tracepoint name: hyprt.86 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_setVal_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[87] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((87 << 8) | hyprt_UtActive[87]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_setVal_Exit2()    /* tracepoint name: hyprt.87 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_setVal_Exit3(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[88] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((88 << 8) | hyprt_UtActive[88]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_setVal_Exit3(P1, P2)      /* tracepoint name: hyprt.88 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_setVal_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[89] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((89 << 8) | hyprt_UtActive[89]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_setVal_Exit(P1)   /* tracepoint name: hyprt.89 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_wait_Entry(P1, P2, P3) do { \
	if ((unsigned char) hyprt_UtActive[90] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((90 << 8) | hyprt_UtActive[90]), "\6\4\4", P1, P2, P3);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_wait_Entry(P1, P2, P3)    /* tracepoint name: hyprt.90 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_wait_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[91] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((91 << 8) | hyprt_UtActive[91]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_wait_Exit1()      /* tracepoint name: hyprt.91 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_wait_Exit2() do { \
	if ((unsigned char) hyprt_UtActive[92] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((92 << 8) | hyprt_UtActive[92]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_wait_Exit2()      /* tracepoint name: hyprt.92 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_wait_Exit3(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[93] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((93 << 8) | hyprt_UtActive[93]), "\4\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_wait_Exit3(P1, P2)        /* tracepoint name: hyprt.93 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_wait_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[94] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((94 << 8) | hyprt_UtActive[94]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_wait_Exit(P1)     /* tracepoint name: hyprt.94 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_ensureDirectory_path(P1) do { \
	if ((unsigned char) hyprt_UtActive[95] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((95 << 8) | hyprt_UtActive[95]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_ensureDirectory_path(P1)  /* tracepoint name: hyprt.95 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[96] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((96 << 8) | hyprt_UtActive[96]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_Entry(P1)     /* tracepoint name: hyprt.96 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[97] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((97 << 8) | hyprt_UtActive[97]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_Exit(P1)      /* tracepoint name: hyprt.97 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_free_memory_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[98] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((98 << 8) | hyprt_UtActive[98]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_free_memory_Entry(P1) /* tracepoint name: hyprt.98 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_free_memory_Exit() do { \
	if ((unsigned char) hyprt_UtActive[99] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((99 << 8) | hyprt_UtActive[99]), NULL);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_free_memory_Exit()    /* tracepoint name: hyprt.99 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_callSite_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[100] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((100 << 8) | hyprt_UtActive[100]), "\4\377", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_callSite_Entry(P1, P2)        /* tracepoint name: hyprt.100 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_callSite_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[101] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((101 << 8) | hyprt_UtActive[101]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_callSite_Exit(P1)     /* tracepoint name: hyprt.101 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_reallocate_memory_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[102] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((102 << 8) | hyprt_UtActive[102]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_reallocate_memory_Entry(P1, P2)       /* tracepoint name: hyprt.102 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_reallocate_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[103] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((103 << 8) | hyprt_UtActive[103]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_mem_hymem_reallocate_memory_Exit(P1)    /* tracepoint name: hyprt.103 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[104] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((104 << 8) | hyprt_UtActive[104]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Entry(P1, P2)        /* tracepoint name: hyprt.104 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit1() do { \
	if ((unsigned char) hyprt_UtActive[105] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((105 << 8) | hyprt_UtActive[105]), NULL);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit1()      /* tracepoint name: hyprt.105 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit2(P1) do { \
	if ((unsigned char) hyprt_UtActive[106] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((106 << 8) | hyprt_UtActive[106]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit2(P1)    /* tracepoint name: hyprt.106 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit3(P1) do { \
	if ((unsigned char) hyprt_UtActive[107] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((107 << 8) | hyprt_UtActive[107]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit3(P1)    /* tracepoint name: hyprt.107 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit4() do { \
	if ((unsigned char) hyprt_UtActive[108] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((108 << 8) | hyprt_UtActive[108]), NULL);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit4()      /* tracepoint name: hyprt.108 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[109] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((109 << 8) | hyprt_UtActive[109]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_reserve_memory_Exit(P1)     /* tracepoint name: hyprt.109 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_free_memory_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[110] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((110 << 8) | hyprt_UtActive[110]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_free_memory_Entry(P1, P2)   /* tracepoint name: hyprt.110 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_free_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[111] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((111 << 8) | hyprt_UtActive[111]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_free_memory_Exit(P1)        /* tracepoint name: hyprt.111 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_commit_memory_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[112] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((112 << 8) | hyprt_UtActive[112]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_commit_memory_Entry(P1, P2) /* tracepoint name: hyprt.112 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_commit_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[113] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((113 << 8) | hyprt_UtActive[113]), "\6", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_commit_memory_Exit(P1)      /* tracepoint name: hyprt.113 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_decommit_memory_Entry(P1, P2) do { \
	if ((unsigned char) hyprt_UtActive[114] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((114 << 8) | hyprt_UtActive[114]), "\6\4", P1, P2);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_decommit_memory_Entry(P1, P2)       /* tracepoint name: hyprt.114 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_vmem_hyvmem_decommit_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[115] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((115 << 8) | hyprt_UtActive[115]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_vmem_hyvmem_decommit_memory_Exit(P1)    /* tracepoint name: hyprt.115 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shared_ensureDirectory_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[116] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((116 << 8) | hyprt_UtActive[116]), "\377", P1);} \
	} while(0)
#else
#define Trc_PRT_shared_ensureDirectory_Entry(P1)        /* tracepoint name: hyprt.116 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shared_changeDirectoryPermission_Event1(P1) do { \
	if ((unsigned char) hyprt_UtActive[117] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((117 << 8) | hyprt_UtActive[117]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shared_changeDirectoryPermission_Event1(P1)     /* tracepoint name: hyprt.117 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shared_ensureDirectory_Event1() do { \
	if ((unsigned char) hyprt_UtActive[118] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((118 << 8) | hyprt_UtActive[118]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shared_ensureDirectory_Event1() /* tracepoint name: hyprt.118 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shared_ensureDirectory_Event2(P1) do { \
	if ((unsigned char) hyprt_UtActive[119] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((119 << 8) | hyprt_UtActive[119]), "\4", P1);} \
	} while(0)
#else
#define Trc_PRT_shared_ensureDirectory_Event2(P1)       /* tracepoint name: hyprt.119 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shmem_hyshmem_open_Exit3() do { \
	if ((unsigned char) hyprt_UtActive[120] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((120 << 8) | hyprt_UtActive[120]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shmem_hyshmem_open_Exit3()      /* tracepoint name: hyprt.120 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_shsem_hyshsem_open_Exit3() do { \
	if ((unsigned char) hyprt_UtActive[121] != 0){ \
		hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((121 << 8) | hyprt_UtActive[121]), NULL);} \
	} while(0)
#else
#define Trc_PRT_shsem_hyshsem_open_Exit3()      /* tracepoint name: hyprt.121 */
#endif

#if defined(_UTE_STATIC_)
  unsigned char hyprt_UtActive[122] = {
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0,
    0, 0
  };
  unsigned char hyprt_UtLevels[122] = {
    1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 4, 4, 1, 1, 4, 1, 1, 4,
    4, 4, 1, 1, 5,
    4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 1, 1,
    1, 4, 1, 1, 1,
    4, 4, 4, 4, 4, 4, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1,
    4, 1, 1, 1, 1,
    4, 1, 1, 1, 1, 4, 10, 10, 10, 10, 8, 8, 4, 4, 4, 2, 2, 2, 2, 4, 4, 4, 4,
    4, 4, 4, 4, 1, 1, 1,
    1, 1
  };
  UtModuleInfo hyprt_UtModuleInfo =
    { "hyprt", 5, NULL, NULL, NULL };
#else                           /* !_UTE_STATIC_ */
  extern UtModuleInfo hyprt_UtModuleInfo;
  extern unsigned char hyprt_UtActive[122];
#endif                          /* !_UTE_STATIC_ */

#if !defined(UT_MODULE_INFO)
#define UT_MODULE_INFO hyprt_UtModuleInfo
#endif                          /* UT_MODULE_INFO */

#if defined(__cplusplus)
}                               /* extern "C" */
#endif
#endif                          /* !_UTE_MODULE_HEADER_ */


