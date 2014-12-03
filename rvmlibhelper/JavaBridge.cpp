//
//  JavaBridge.cpp
//
//  Created by Wolfgang Frank on 15.07.14.
//  Copyright (c) 2014 arconsis IT-Solutions GmbH. All rights reserved.
//

#include "JavaBridge.h"
#include <stdio.h>
#include <string.h>

#include "RvmHelper.h"


void initRoboVM(const char* appPath)
{
    static const char* path = appPath;
    
    char *argv[] = { (char*)path };
    initLib(1, argv);
    Env* rvmEnv = rvmGetEnv();
    ClassLoader *loader = rvmGetSystemClassLoader(rvmEnv);
    initRvmClassloader(loader);
    
    printf("initRoboVM() done\n");
}