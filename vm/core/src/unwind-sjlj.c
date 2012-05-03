#include <nullvm.h>
#include <unwind.h>
#include "private.h"

_Unwind_Reason_Code _nvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exClass, 
        struct _Unwind_Exception* exInfo, struct _Unwind_Context* context) {

    if (exClass == UNWIND_EXCEPTION_CLASS) {
        // When using SJLJ exceptions IP really means the index of the call site in the LSDA.
        // Call site > 0 means that the exception should be handled by the current function
        uintptr_t ip = _Unwind_GetIP(context);
        if ((int) ip > 0) {
            if (actions & _UA_SEARCH_PHASE) {
                return _URC_HANDLER_FOUND;
            } else if (actions & _UA_HANDLER_FRAME) {
                // The value at index 0 will be the value returned by the LLVM landingpad instruction.
                _Unwind_SetGR(context, 0, (uintptr_t) exInfo);
                // Set IP to ip-1. I think the reason why we need to do this is that the index returned 
                // by Unwind_GetIP() is 1-based while the call site indices in the LSDA are 0-based.
                _Unwind_SetIP(context, ip - 1);
                return _URC_INSTALL_CONTEXT;
            }
        }
    }

    return _URC_CONTINUE_UNWIND;
}

jint unwindRaiseException(Env* env) {
    struct _Unwind_Exception* u = nvmAllocateMemory(env, sizeof(struct _Unwind_Exception));
    u->exception_class = UNWIND_EXCEPTION_CLASS;
    _Unwind_Reason_Code urc = _Unwind_SjLj_RaiseException(u);
    return urc == _URC_END_OF_STACK ? UNWIND_UNHANDLED_EXCEPTION : UNWIND_FATAL_ERROR;
}

jint unwindReraiseException(Env* env, void* exInfo) {
    _Unwind_SjLj_Unregister((_Unwind_FunctionContext_t) ((struct _Unwind_Exception*) exInfo)->private_2);
    _Unwind_Reason_Code urc = _Unwind_SjLj_RaiseException(exInfo);
    return urc == _URC_END_OF_STACK ? UNWIND_UNHANDLED_EXCEPTION : UNWIND_FATAL_ERROR;
}
