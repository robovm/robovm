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
#include <robovm.h>
#include <unwind.h>
#include "uthash.h"
#include "private.h"

extern _Unwind_Reason_Code __gcc_personality_v0(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

typedef struct UnwindInfo {
    struct _Unwind_Exception exception_info;
    _Unwind_Ptr landing_pad;
} UnwindInfo;

_Unwind_Reason_Code _rvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, 
        struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {

    UnwindInfo* info = (UnwindInfo*) exception_info;
    if (actions & _UA_SEARCH_PHASE) {
        _Unwind_Ptr saved_ip = _Unwind_GetIP(context);
        _Unwind_Reason_Code urc = __gcc_personality_v0(version, _UA_CLEANUP_PHASE, exception_class, exception_info, context);
        if (urc == _URC_INSTALL_CONTEXT) {
            info->landing_pad = _Unwind_GetIP(context);
            _Unwind_SetIP(context, saved_ip);
            return _URC_HANDLER_FOUND;
        }
        return urc;
    } else if (actions & _UA_HANDLER_FRAME) {
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (0), (_Unwind_Ptr) exception_info);
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (1), 0);
        _Unwind_SetIP(context, info->landing_pad); 
        return _URC_INSTALL_CONTEXT;
    }

    return _URC_CONTINUE_UNWIND;
}

jint unwindRaiseException(Env* env) {
    UnwindInfo* u = rvmAllocateMemory(env, sizeof(UnwindInfo));
    u->exception_info.exception_class = UNWIND_EXCEPTION_CLASS;
    _Unwind_Reason_Code urc = _Unwind_RaiseException(&u->exception_info);
    return urc == _URC_END_OF_STACK ? UNWIND_UNHANDLED_EXCEPTION : UNWIND_FATAL_ERROR;
}

jint unwindReraiseException(Env* env, void* exInfo) {
    _Unwind_Reason_Code urc = _Unwind_RaiseException(exInfo);
    return urc == _URC_END_OF_STACK ? UNWIND_UNHANDLED_EXCEPTION : UNWIND_FATAL_ERROR;
}
