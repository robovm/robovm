# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# Makefile for module 'nio_char'
#

include $(HY_HDK)/build/make/defines.mk

# TODO: Fix the warnings see HARMONY-4959 and
# http://www.mail-archive.com/dev@harmony.apache.org/msg10469.html 
WARNFLAGS :=

BUILDFILES = \
  ../shared/CP_1250.o \
  ../shared/CP_1251.o \
  ../shared/CP_1252.o \
  ../shared/CP_1253.o \
  ../shared/CP_1254.o \
  ../shared/CP_1257.o \
  ../shared/ISO_8859_1.o \
  ../shared/ISO_8859_2.o \
  ../shared/ISO_8859_4.o \
  ../shared/ISO_8859_5.o \
  ../shared/ISO_8859_7.o \
  ../shared/ISO_8859_9.o \
  ../shared/ISO_8859_13.o \
  ../shared/ISO_8859_15.o \
  ../shared/KOI8_R.o \
  ../shared/US_ASCII.o \
  ../shared/IBM866.o \
  ../shared/additional/IBM00858.o \
  ../shared/additional/IBM01140.o \
  ../shared/additional/IBM01141.o \
  ../shared/additional/IBM01142.o \
  ../shared/additional/IBM01143.o \
  ../shared/additional/IBM01144.o \
  ../shared/additional/IBM01145.o \
  ../shared/additional/IBM01146.o \
  ../shared/additional/IBM01147.o \
  ../shared/additional/IBM01148.o \
  ../shared/additional/IBM01149.o \
  ../shared/additional/IBM037.o \
  ../shared/additional/IBM1026.o \
  ../shared/additional/IBM1047.o \
  ../shared/additional/IBM273.o \
  ../shared/additional/IBM277.o \
  ../shared/additional/IBM278.o \
  ../shared/additional/IBM280.o \
  ../shared/additional/IBM284.o \
  ../shared/additional/IBM285.o \
  ../shared/additional/IBM297.o \
  ../shared/additional/IBM420.o \
  ../shared/additional/IBM424.o \
  ../shared/additional/IBM437.o \
  ../shared/additional/IBM500.o \
  ../shared/additional/IBM775.o \
  ../shared/additional/IBM850.o \
  ../shared/additional/IBM852.o \
  ../shared/additional/IBM855.o \
  ../shared/additional/IBM857.o \
  ../shared/additional/IBM860.o \
  ../shared/additional/IBM861.o \
  ../shared/additional/IBM862.o \
  ../shared/additional/IBM863.o \
  ../shared/additional/IBM864.o \
  ../shared/additional/IBM865.o \
  ../shared/additional/IBM868.o \
  ../shared/additional/IBM869.o \
  ../shared/additional/IBM870.o \
  ../shared/additional/IBM871.o \
  ../shared/additional/IBM918.o \
  ../shared/additional/IBM_Thai.o \
  ../shared/additional/ISO_8859_3.o \
  ../shared/additional/ISO_8859_6.o \
  ../shared/additional/ISO_8859_8.o \
  ../shared/additional/windows_1255.o \
  ../shared/additional/windows_1256.o \
  ../shared/additional/x_IBM1006.o \
  ../shared/additional/x_IBM1025.o \
  ../shared/additional/x_IBM1097.o \
  ../shared/additional/x_IBM1098.o \
  ../shared/additional/x_IBM1112.o \
  ../shared/additional/x_IBM1122.o \
  ../shared/additional/x_IBM1123.o \
  ../shared/additional/x_IBM1124.o \
  ../shared/additional/x_IBM737.o \
  ../shared/additional/x_IBM856.o \
  ../shared/additional/x_IBM874.o \
  ../shared/additional/x_IBM875.o \
  ../shared/additional/x_IBM922.o \
  ../shared/additional/x_MacCyrillic.o \
  ../shared/additional/x_MacGreek.o \
  ../shared/additional/x_MacTurkish.o \
  ../shared/additional/Big5.o \
  ../shared/additional/Big5_HKSCS.o \
  ../shared/additional/EUC_KR.o \
  ../shared/additional/GB2312.o \
  ../shared/additional/GBK.o \
  ../shared/additional/GB18030.o \
  ../shared/additional/windows_31j.o \
  ../shared/additional/x_MS950_HKSCS.o \
  ../shared/additional/x_windows_949.o 

DLLNAME = $(DLLPATH)libhyniochar$(HY_SHLIB_SUFFIX)
MDLLIBFILES += $(LIBPATH)libvmi$(HY_LINKLIB_SUFFIX)

include $(HY_HDK)/build/make/rules.mk
