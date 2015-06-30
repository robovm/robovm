/*
 * Copyright (c) 2014 arconsis IT-Solutions GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef RoboVMFramework_RvmHelper_h
#define RoboVMFramework_RvmHelper_h

#include "robovm.h"

#ifdef __cplusplus
extern "C" {
#endif
    
    extern void initRvmClassloader(ClassLoader* classloader);
    extern void initRvmEnv(Env **rvmEnv);
    extern Class* findRvmClazz(Env** rvmEnv, const char* clazzName);
    extern jlong getRvmHandle(Env** rvmEnv, Object *obj, Class *clazz);
    
#endif
    
#ifdef __cplusplus
}
#endif
