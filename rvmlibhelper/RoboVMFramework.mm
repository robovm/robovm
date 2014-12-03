//
//  RoboVMFramework.m
//
//  Created by Wolfgang Frank on 17.09.14.
//  Copyright (c) 2014 arconsis IT-Solutions GmbH. All rights reserved.
//

#import "DiaxRoboVMFramework.h"
#import "JavaBridge.h"


@implementation RoboVMFramework

+ (void)startRVM:(NSString *)appPath
{
    initRoboVM(appPath.UTF8String);
}

@end
