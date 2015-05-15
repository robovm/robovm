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
prev_offset = 0
sel_offset  = 4
sp_offset   = 8
r4_offset   = 12

Env_trycatchContext_offset = 28

    .syntax unified
    .section    __TEXT,__text,regular,pure_instructions
    
/*
 * rvmTrycatchEnter(Env* env, TrycatchContext* tc) 
 */    
    .globl    _rvmTrycatchEnter
    .align    2
    .code     16
    .thumb_func _rvmTrycatchEnter
_rvmTrycatchEnter:
    mov r2, r1
    /* sp (r13) is not allowed to be in the reg list of stmia so we store sp separately */
    str sp, [r2, #sp_offset]
    add r2, r2, #r4_offset
    stmia r2!, {r4-r8, r10, r11, lr}
    vstmia r2, {d8-d15}

    /* tc->prev = env->trycatchContext; */
    /* env->trycatchContext = tc; */
    ldr r2, [r0, #Env_trycatchContext_offset]
    str r2, [r1, #prev_offset]
    str r1, [r0, #Env_trycatchContext_offset]

    @ Return 0
    mov r0, #0

    bx lr

/*
 * rvmTrycatchJump(TrycatchContext* tc) 
 */
    .globl    _rvmTrycatchJump
    .align    2
    .code     16
    .thumb_func _rvmTrycatchJump
_rvmTrycatchJump:
    mov r1, r0
    ldr sp, [r1, #sp_offset]
    add r1, r1, #r4_offset
    ldmia r1!, {r4-r8, r10, r11, lr}
    vldmia r1, {d8-d15}

    @ Set the return value that the call to rvmTrycatchEnter will return
    ldr r0, [r0, #sel_offset]
    @ Jump to the return address from the initial call to rvmTrycatchEnter
    bx lr
