/*
 * Copyright (C) 2012 RoboVM
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
#ifndef ROBOVM_H
#define ROBOVM_H

#ifndef GC_THREADS
#define GC_THREADS
#endif

#include <gc.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include <stdarg.h>

#ifdef __cplusplus
extern "C" {
#endif

#include <robovm/types.h>
#include <robovm/access.h>
#include <robovm/memory.h>
#include <robovm/init.h>
#include <robovm/method.h>
#include <robovm/field.h>
#include <robovm/class.h>
#include <robovm/array.h>
#include <robovm/exception.h>
#include <robovm/string.h>
#include <robovm/thread.h>
#include <robovm/attribute.h>
#include <robovm/native.h>
#include <robovm/proxy.h>
#include <robovm/vminterface.h>
#include <robovm/log.h>

#ifdef __cplusplus
}
#endif

#endif

