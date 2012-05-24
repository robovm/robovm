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

