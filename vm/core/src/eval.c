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

#include <robovm.h>

#define LOG_TAG "eval"

/*
 * These functions are used by the debugger to run functions and methods in
 * the inferior. The rvmEvalAll() function will be exported for debug builds
 * which means that this function and all functions it references won't be
 * stripped out of the executable. For release builds none of these functions
 * will be preserved in the executable.
 */

#define EVAL(T, N) \
typedef struct {Object* throwable; T result;} ResultEval ## N; \
void rvmEval ## N(T (*f)(void), ResultEval ## N *r) __attribute__((visibility ("default"))); \
void rvmEval ## N(T (*f)(void), ResultEval ## N *r) { \
    Env* env = rvmGetEnv(); \
    assert(env != NULL); \
    Env envCopy = *env; \
    env->throwable = NULL; \
    TrycatchContext tc = {0}; \
    tc.sel = CATCH_ALL_SEL; \
    T result = 0; \
    if (rvmIsNonNativeFrame(env)) { \
        if (!rvmTrycatchEnter(env, &tc)) { \
            result = f(); \
        } \
        rvmTrycatchLeave(env); \
    } else { \
        rvmPushGatewayFrame(env); \
        if (!rvmTrycatchEnter(env, &tc)) { \
            result = f(); \
        } \
        rvmTrycatchLeave(env); \
        rvmPopGatewayFrame(env); \
    } \
    r->result = result; \
    r->throwable = env->throwable; \
    *env = envCopy; \
}

EVAL(void*, Void)
EVAL(void*, Pointer)
EVAL(Object*, Object)
EVAL(Object*, Array)
EVAL(jboolean, Boolean)
EVAL(jbyte, Byte)
EVAL(jshort, Short)
EVAL(jchar, Char)
EVAL(jint, Int)
EVAL(jlong, Long)
EVAL(jfloat, Float)
EVAL(jdouble, Double)

/**
 * Hack to make all the rvmEval* functions get included if we just export
 * rvmEvalAll.
 */
void rvmEvalAll(void) __attribute__((visibility ("default")));
void rvmEvalAll(void) {
    rvmEvalVoid(NULL, NULL);
    rvmEvalPointer(NULL, NULL);
    rvmEvalObject(NULL, NULL);
    rvmEvalArray(NULL, NULL);
    rvmEvalBoolean(NULL, NULL);
    rvmEvalByte(NULL, NULL);
    rvmEvalShort(NULL, NULL);
    rvmEvalChar(NULL, NULL);
    rvmEvalInt(NULL, NULL);
    rvmEvalLong(NULL, NULL);
    rvmEvalFloat(NULL, NULL);
    rvmEvalDouble(NULL, NULL);
}
