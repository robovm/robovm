#include <nullvm.h>
#include <unwind.h>

#define EXCEPTION_CLASS 0x4A4A4A4A4A4A4A4A // "JJJJJJJJ"

typedef struct UnwindInfo {
    struct _Unwind_Exception exception_info;
    void* throwable;
    _Unwind_Ptr landing_pad;
} UnwindInfo;

extern _Unwind_Reason_Code __gcc_personality_v0(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

_Unwind_Reason_Code _nvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {

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

jint nvmUnwindRaiseException(Env* env) {
    UnwindInfo* u = nvmAllocateMemory(env, sizeof(UnwindInfo));
    u->exception_info.exception_class = EXCEPTION_CLASS;
    u->throwable = env->throwable;
    _Unwind_Reason_Code urc = _Unwind_RaiseException(&u->exception_info);
    return urc == _URC_END_OF_STACK ? UNWIND_UNHANDLED_EXCEPTION : UNWIND_FATAL_ERROR;
}

static _Unwind_Reason_Code unwindCallStack(struct _Unwind_Context* ctx, void* _d) {
    jboolean (*iterator)(Env*, void*, jint, void*) = ((void**) _d)[0];
    Env* env = ((void**) _d)[1];
    void* data = ((void**) _d)[2];

    void* address = (void*) _Unwind_GetIP(ctx);
    void* function = _Unwind_FindEnclosingFunction(address);
    if (function == nvmUnwindIterateCallStack) return _URC_NO_REASON;
    jint offset = address - function;
    return iterator(env, function, offset, data) ? _URC_NO_REASON : _URC_NORMAL_STOP;
}

typedef struct GetCallerAtDepthData {
    jint depth;
    void* function;
    jint offset;
} GetCallerAtDepthData;

static jboolean getCallerAtDepthIterator(Env* env, void* function, jint offset, void* _data) {
    GetCallerAtDepthData* data = _data;
    if (data->depth <= 0) {
        data->function = function;
        data->offset = offset;
        return FALSE; // Stop iterating
    }
    data->depth--;
    return TRUE;
}

void nvmUnwindIterateCallStack(Env* env, jboolean (*iterator)(Env*, void*, jint, void*), void* data) {
    void* d[3] = {iterator, env, data};
    _Unwind_Backtrace(unwindCallStack, d);
}

void* nvmUnwindGetCallerAtDepth(Env* env, jint depth, jint* offset) {
    GetCallerAtDepthData data = {depth + 2, NULL, 0};
    nvmUnwindIterateCallStack(env, getCallerAtDepthIterator, &data);
    if (offset) *offset = data.offset;
    return data.function;
}

void* nvmUnwindGetCaller(Env* env, jint* offset) {
    return nvmUnwindGetCallerAtDepth(env, 1, offset);
}

