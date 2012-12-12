/*
 * Copyright (C) 2012 Trillian AB
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
#include "uthash.h"
#include "private.h"

typedef struct UnwindCallStackData {
    jboolean (*it)(Env*, void*, ProxyMethod*, void*);
    Env* env;
    GatewayFrame* gatewayFrames;
    void* data;
} UnwindCallStackData;

void* unwindGetIP(UnwindContext* context) {
    return context->pc;
}

void unwindBacktrace(void* fp, jboolean (*it)(UnwindContext*, void*), void* data) {
    UnwindContext context = {0};
    context.fp = (Frame*) (fp ? fp : __builtin_frame_address(0));
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

void unwindIterateCallStack(Env* env, void* fp, jboolean (*it)(Env*, void*, ProxyMethod*, void*), void* data) {
    jboolean calledFromNative = !rvmIsNonNativeFrame(env);
    if (calledFromNative) {
        rvmPushGatewayFrame(env);
    }
    UnwindCallStackData d = {it, env, env->gatewayFrames, data};
    unwindBacktrace(fp, unwindCallStack, &d);
    if (calledFromNative) {
        rvmPopGatewayFrame(env);
    }
}
