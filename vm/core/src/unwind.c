#include <nullvm.h>
#include "uthash.h"
#include "private.h"

typedef struct Frame {
    struct Frame* prev;
    void* returnAddress;
} Frame;

struct UnwindContext {
    Frame* fp;
    void* pc;
    Frame* newFrame;
};

typedef struct UnwindCallStackData {
    jboolean (*it)(Env*, void*, ProxyMethod*, void*);
    Env* env;
    GatewayFrame* gatewayFrames;
    void* data;
} UnwindCallStackData;

void* unwindGetIP(UnwindContext* context) {
    return context->pc;
}

void unwindBacktrace(jboolean (*it)(UnwindContext*, void*), void* data) {
    UnwindContext context = {0};
    context.fp = (Frame*) __builtin_frame_address(0);
    context.pc = context.fp->returnAddress;
    context.fp = context.fp->prev;
    // fp now points to the frame of the caller of unwindBacktrace
    // pc now points to the instruction which called unwindBacktrace
    while (context.fp && it(&context, data)) {
        if (context.newFrame) {
            // Drop to context->newFrame->prev
            context.fp = context.newFrame;
            context.newFrame = NULL;
        }
        context.pc = context.fp->returnAddress;
        context.fp = context.fp->prev;
    }
}

static jboolean unwindCallStack(UnwindContext* context, void* _data) {
    UnwindCallStackData* data = (UnwindCallStackData*) _data;
    Env* env = data->env;
    void* frameAddress = context->fp;
    void* pc = context->pc;

    if (data->gatewayFrames->frameAddress == frameAddress) {
        // GatewayFrames are pushed when entering and leaving native code. We've hit a frame 
        // matching the top most GatewayFrame. This is always a frame from a call leaving
        // native code (or a call to unwindIterateCallStack()). The GatewayFrame below the
        // top one is the frame at which we entered native code. Skip all frames down to this
        // frame and then iterate through all frames until we hit the third GatewayFrame from
        // the top. 

        data->gatewayFrames = data->gatewayFrames->prev;
        if (!data->gatewayFrames) return FALSE;

        // data->gatewayFrames now points to the frame which called into native code.
        context->newFrame = data->gatewayFrames->frameAddress;

        if (data->gatewayFrames->proxyMethod) {
            if (!data->it(env, NULL, data->gatewayFrames->proxyMethod, data->data)) return FALSE;
        }

        data->gatewayFrames = data->gatewayFrames->prev;
        // data->gatewayFrames now points to the frame which called into non-native code.
        return data->gatewayFrames ? TRUE : FALSE;
    }

    return data->it(env, pc, NULL, data->data);
}

void unwindIterateCallStack(Env* env, jboolean (*it)(Env*, void*, ProxyMethod*, void*), void* data) {
    nvmPushGatewayFrame(env);
    UnwindCallStackData d = {it, env, env->gatewayFrames, data};
    unwindBacktrace(unwindCallStack, &d);
    nvmPopGatewayFrame(env);
}
