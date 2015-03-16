/*
 * Copyright (C) 2014 RoboVM AB
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
prev_offset = 0
sel_offset  = 8
sp_offset   = 16

/*    void* sp; // x31
    void* x19;
    void* x20;
    void* x21;
    void* x22;
    void* x23;
    void* x24;
    void* x25;
    void* x26;
    void* x27;
    void* x28;
    void* fp; // x29
    void* pc; // x30
    double d8;
    double d9;
    double d10;
    double d11;
    double d12;
    double d13;
    double d14;
    double d15; */

Env_trycatchContext_offset = 56

    .section    __TEXT,__text,regular,pure_instructions
    
/*
 * rvmTrycatchEnter(Env* env, TrycatchContext* tc) 
 */    
    .globl    _rvmTrycatchEnter
    .align    2
_rvmTrycatchEnter:

    mov x9, sp
    stp  x9, x19, [x1, sp_offset + 16 * 0]
    stp x20, x21, [x1, sp_offset + 16 * 1]
    stp x22, x23, [x1, sp_offset + 16 * 2]
    stp x24, x25, [x1, sp_offset + 16 * 3]
    stp x26, x27, [x1, sp_offset + 16 * 4]
    stp x28,  fp, [x1, sp_offset + 16 * 5]
    str x30,      [x1, sp_offset + 16 * 6]
    str  d8,      [x1, sp_offset + 16 * 6 + 8]
    stp  d9, d10, [x1, sp_offset + 16 * 7]
    stp d11, d12, [x1, sp_offset + 16 * 8]
    stp d13, d14, [x1, sp_offset + 16 * 9]
    str d15,      [x1, sp_offset + 16 * 10]

    /* tc->prev = env->trycatchContext; */
    /* env->trycatchContext = tc; */
    ldr x2, [x0, #Env_trycatchContext_offset]
    str x2, [x1, #prev_offset]
    str x1, [x0, #Env_trycatchContext_offset]

    // Return 0
    mov w0, #0

    ret

/*
 * rvmTrycatchJump(TrycatchContext* tc) 
 */
    .globl    _rvmTrycatchJump
    .align    2
_rvmTrycatchJump:
    ldp  x9, x19, [x0, sp_offset + 16 * 0]
    ldp x20, x21, [x0, sp_offset + 16 * 1]
    ldp x22, x23, [x0, sp_offset + 16 * 2]
    ldp x24, x25, [x0, sp_offset + 16 * 3]
    ldp x26, x27, [x0, sp_offset + 16 * 4]
    ldp x28,  fp, [x0, sp_offset + 16 * 5]
    ldr x30,      [x0, sp_offset + 16 * 6]
    ldr  d8,      [x0, sp_offset + 16 * 6 + 8]
    ldp  d9, d10, [x0, sp_offset + 16 * 7]
    ldp d11, d12, [x0, sp_offset + 16 * 8]
    ldp d13, d14, [x0, sp_offset + 16 * 9]
    ldr d15,      [x0, sp_offset + 16 * 10]

    mov sp, x9

    // Set the return value that the call to rvmTrycatchEnter will return
    ldr x0, [x0, #sel_offset]
    // Jump to the return address from the initial call to rvmTrycatchEnter
    br lr
