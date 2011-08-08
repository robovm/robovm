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

#if !defined(hygp_h)
#define hygp_h
void VMCALL hygp_register_handler (struct HyPortLibrary *portLibrary,
                                   handler_fn fn, void *aUserData);
I_32 VMCALL hygp_startup (struct HyPortLibrary *portLibrary);
void VMCALL hygp_shutdown (struct HyPortLibrary *portLibrary);
UDATA VMCALL hygp_protect (struct HyPortLibrary *portLibrary, protected_fn fn,
                           void *arg);
U_32 VMCALL hygp_info_count (struct HyPortLibrary *portLibrary, void *info,
                             U_32 category);
U_32 VMCALL hygp_info (struct HyPortLibrary *portLibrary, void *info,
                       U_32 category, I_32 index, const char **name,
                       void **value);
#endif /* hygp_h */
