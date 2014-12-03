//
//  RvmHelper.h
//
//  Created by Wolfgang Frank on 18.09.14.
//  Copyright (c) 2014 arconsis IT-Solutions GmbH. All rights reserved.
//

#ifndef DiaxRoboVMFramework_RvmHelper_h
#define DiaxRoboVMFramework_RvmHelper_h

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
