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
        // Skip to the most recent GatewayFrame
        if (data->gatewayFrames->proxyMethod) {
            if (!data->it(env, NULL, data->gatewayFrames->proxyMethod, data->data)) return FALSE;
        }
        context->newFrame = data->gatewayFrames->frameAddress;
        data->gatewayFrames = data->gatewayFrames->prev;
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
