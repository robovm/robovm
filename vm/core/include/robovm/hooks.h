/*
 * Copyright (C) 2014 Trillian Mobile AB
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
#ifndef ROBOVM_HOOKS_H
#define ROBOVM_HOOKS_H

void _rvmHookBeforeAppEntryPoint(Env* env, Class* clazz, Method* method, ObjectArray* args);
void _rvmHookBeforeMainThreadAttached(Env* env);
void _rvmHookThreadCreated(Env* env, JavaThread* threadObj);
void _rvmHookThreadAttached(Env* env, JavaThread* threadObj, Thread* thread);
void _rvmHookThreadStarting(Env* env, JavaThread* threadObj, Thread* thread);
void _rvmHookThreadDetaching(Env* env, JavaThread* threadObj, Thread* thread, Object* throwable);
jboolean _rvmHookSetupTCPChannel(Options* options);
jboolean _rvmHookHandshake(Options* options);

void rvmInstrument(void* method, jint lineBit);
void rvmHookWaitForAttach(Options* options);
void rvmHookDebuggerAttached(Options* options);
static inline void rvmHookBeforeAppEntryPoint(Env* env, Class* clazz, Method* method, ObjectArray* args) {
    if (env->vm->options->enableHooks) {
        _rvmHookBeforeAppEntryPoint(env, clazz, method, args);
    }
}
static inline void rvmHookBeforeMainThreadAttached(Env* env) {
    if (env->vm->options->enableHooks) {
        _rvmHookBeforeMainThreadAttached(env);
    }
}
static inline void rvmHookThreadCreated(Env* env, JavaThread* threadObj) {
    if (env->vm->options->enableHooks) {
        _rvmHookThreadCreated(env, threadObj);
    }
}
static inline void rvmHookThreadAttached(Env* env, JavaThread* threadObj, Thread* thread) {
    if (env->vm->options->enableHooks) {
        _rvmHookThreadAttached(env, threadObj, thread);
    }
}
static inline void rvmHookThreadStarting(Env* env, JavaThread* threadObj, Thread* thread) {
    if (env->vm->options->enableHooks) {
        _rvmHookThreadStarting(env, threadObj, thread);
    }
}
static inline void rvmHookThreadDetaching(Env* env, JavaThread* threadObj, Thread* thread, Object* throwable) {
    if (env->vm->options->enableHooks) {
        _rvmHookThreadDetaching(env, threadObj, thread, throwable);
    }
}
static inline jboolean rvmHookSetupTCPChannel(Options* options) {
    if(options->enableHooks) {
        return _rvmHookSetupTCPChannel(options);
    } else {
        return TRUE;
    }
}
static inline jboolean rvmHookHandshake(Options* options) {
    if(options->enableHooks) {
        return _rvmHookHandshake(options);
    } else {
        return FALSE;
    }
}

#endif
