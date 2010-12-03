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

#if !defined(hythreadinspect_h)
#define hythreadinspect_h

#if defined(HYVM_OUT_OF_PROCESS)
/* redefine thread functions */
#define hythread_monitor_walk dbg_hythread_monitor_walk
#define hythread_tls_get dbg_hythread_tls_get
#define hythread_get_priority dbg_hythread_get_priority
#define hythread_get_flags dbg_hythread_get_flags
#define hythread_monitor_get_name dbg_hythread_monitor_get_name
#define hythread_monitor_get_tracing dbg_hythread_monitor_get_tracing
#define getVMThreadStatus dbgGetVMThreadStatus
#endif

#endif /* hythreadinspect_h */

/* Note: This section is NOT protected by #ifdefs.
 * It may be safely included more than once.
 */
UDATA VMCALL hythread_get_flags
PROTOTYPE ((hythread_t thread, hythread_monitor_t * blocker));
HyThreadMonitorTracing *VMCALL hythread_monitor_get_tracing
PROTOTYPE ((hythread_monitor_t monitor));
UDATA VMCALL hythread_get_priority PROTOTYPE ((hythread_t thread));
void *VMCALL hythread_tls_get
PROTOTYPE ((hythread_t thread, hythread_tls_key_t key));
const char *VMCALL hythread_monitor_get_name
PROTOTYPE ((hythread_monitor_t monitor));
hythread_monitor_t VMCALL hythread_monitor_walk
PROTOTYPE ((hythread_monitor_t monitor));
