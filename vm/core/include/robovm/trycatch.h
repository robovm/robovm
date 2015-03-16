/*
 * Copyright (C) 2012 RoboVM AB
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
#ifndef ROBOVM_TRYCATCH_H
#define ROBOVM_TRYCATCH_H

#define CATCH_ALL_SEL -1

extern jint rvmTrycatchEnter(Env* env, TrycatchContext* tc) __attribute__((returns_twice));
extern void rvmTrycatchJump(TrycatchContext* tc) __attribute__((noreturn));

static inline TrycatchContext* rvmTrycatchLeave(Env* env) {
    TrycatchContext* tc = env->trycatchContext;
    env->trycatchContext = env->trycatchContext->prev;
    return tc;
}

#endif
