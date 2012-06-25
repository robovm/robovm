/*
 * Copyright (C) 2012 RoboVM
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
r5_offset   = 16
r6_offset   = 20
fp_offset   = 24
r8_offset   = 28
r10_offset  = 32
r11_offset  = 36
pc_offset   = 40
d8_offset   = 44
d9_offset   = 52
d10_offset  = 60
d11_offset  = 68
d12_offset  = 76
d13_offset  = 88
d14_offset  = 96
d15_offset  = 104

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
    push {lr}

    mov r2, r1
    str sp, [r2, #sp_offset]
    add r2, r2, #r4_offset
    stmia r2!, {r4-r8, r10, r11, lr}
    vstmia r2!, {d8-d15}

    /* tc->prev = env->trycatchContext; */
    /* env->trycatchContext = tc; */
    ldr r2, [r0, #Env_trycatchContext_offset]
    str r2, [r1, #prev_offset]
    str r1, [r0, #Env_trycatchContext_offset]

    @ Return 0
    mov r0, #0

    pop {pc}

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
    vldmia r1!, {d8-d15}

    mov r1, r0

    @ Remove the return address of the caller of rvmTrycatchEnter from the stack
    add sp, sp, #4

    @ Set the return value that the call to rvmTrycatchEnter will return
    ldr r0, [r1, #sel_offset]
    @ Jump to the return address from the initial call to rvmTrycatchEnter
    mov pc, lr
