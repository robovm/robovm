#  Licensed to the Apache Software Foundation (ASF) under one or more
#  contributor license agreements.  See the NOTICE file distributed with
#  this work for additional information regarding copyright ownership.
#  The ASF licenses this file to You under the Apache License, Version 2.0
#  (the "License"); you may not use this file except in compliance with
#  the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

#
# Makefile for module 'thread'
#

include $(HY_HDK)/build/make/defines.mk

BUILDFILES = \
	$(SHAREDSUB)thread_copyright.o $(HY_ARCH)/thrhelp.o $(HY_ARCH)/thrspinlock.o \
	$(SHAREDSUB)hythread.o $(SHAREDSUB)hythreadinspect.o linuxonexit.o priority.o \
	rasthrsup.o $(SHAREDSUB)rwmutex.o thrcreate.o thrdsup.o $(SHAREDSUB)thrprof.o

OSLIBS += -lpthread
MDLLIBFILES = $(LIBPATH)libhypool.a $(LIBPATH)libhycommon.a
DLLNAME = ../libhythr$(HY_SHLIB_SUFFIX)
EXPNAME = HYTHR_0.1
HY_CAN_LINK_DEBUG=no

include $(HY_HDK)/build/make/rules.mk
