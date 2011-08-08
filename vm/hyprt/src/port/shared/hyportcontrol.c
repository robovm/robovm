/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

#include <string.h>
#include "hyport.h"
#include "portpriv.h"

#define _UTE_STATIC_
#include "ut_hyprt.h"

I_32 VMCALL
hyport_control (struct HyPortLibrary * portLibrary, char *key, UDATA value)
{
  /* return value of 0 is success */
  if (!strcmp (HYPORT_CTLDATA_SIG_FLAGS, key))
    {
      portLibrary->portGlobals->control.sig_flags = value;
      return 0;
    }

  if (!strcmp (HYPORT_CTLDATA_SHMEM_GROUP_PERM, key))
    {
      portLibrary->portGlobals->control.shmem_group_perm = value;
      return 0;
    }

  if (!strcmp (HYPORT_CTLDATA_TRACE_START, key) && value)
    {
      UtInterface *utIntf = (UtInterface *) value;
      utIntf->module->TraceInit (NULL, &UT_MODULE_INFO);
      Trc_PRT_PortInitStages_Event1 ();
      return 0;
    }
  if (!strcmp (HYPORT_CTLDATA_TRACE_STOP, key) && value)
    {
      UtInterface *utIntf = (UtInterface *) value;
      utIntf->module->TraceTerm (NULL, &UT_MODULE_INFO);
      return 0;
    }

  return 1;
}
