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

#if !defined(hyzipnls_h)
#define hyzipnls_h
#include "hyport.h"
/* 0x5a495053 = ZIPS */
#define HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL__MODULE 0x5a495053
#define HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL__ID 0
#define HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL__MODULE, HYNLS_ZIP_UNABLE_TO_OPEN_ZIP_DLL__ID
#define HYNLS_ZIP_MISSING_EXPORT__MODULE 0x5a495053
#define HYNLS_ZIP_MISSING_EXPORT__ID 1
#define HYNLS_ZIP_MISSING_EXPORT HYNLS_ZIP_MISSING_EXPORT__MODULE, HYNLS_ZIP_MISSING_EXPORT__ID
#endif
