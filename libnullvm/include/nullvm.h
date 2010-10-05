#ifndef NULLVM_H
#define NULLVM_H

#define GC_THREADS
#include <gc.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
#include <stdarg.h>

#ifdef __cplusplus
extern "C" {
#endif

#include <nullvm/types.h>
#include <nullvm/map.h>
#include <nullvm/access.h>
#include <nullvm/init.h>
#include <nullvm/method.h>
#include <nullvm/field.h>
#include <nullvm/class.h>
#include <nullvm/array.h>
#include <nullvm/exception.h>
#include <nullvm/string.h>
#include <nullvm/thread.h>
#include <nullvm/native.h>

#define LOG(format, args...)  \
  fprintf(stderr, format , ## args)

#ifdef __cplusplus
}
#endif

#endif

