//
//  RvmHelper.c
//
//  Created by Wolfgang Frank on 18.09.14.
//  Copyright (c) 2014 arconsis IT-Solutions GmbH. All rights reserved.
//

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
