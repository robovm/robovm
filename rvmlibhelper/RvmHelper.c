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

#include <stdio.h>
#include "RvmHelper.h"

ClassLoader* diaxClassloader = NULL;


void initRvmClassloader(ClassLoader* classloader)
{
    diaxClassloader = classloader;
}

void initRvmEnv(Env **rvmEnv)
{
    *rvmEnv = rvmGetEnv();
}

Class* findRvmClazz(Env** rvmEnv, const char * clazzName)
{
    Class* clazz = rvmFindClassUsingLoader(*rvmEnv, clazzName, diaxClassloader);
    if (clazz == NULL) {
        printf("Reference to class (%s) not created!", clazzName);
        return NULL;
    }
    return clazz;
}


jlong getRvmHandle(Env** rvmEnv, Object *obj, Class *clazz)
{
    Method* getHandle = rvmGetMethod(*rvmEnv, clazz, "getHandle", "()J");
    jlong handle = rvmCallLongInstanceMethod(*rvmEnv, obj, getHandle);
    
    return handle;
}
