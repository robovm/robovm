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
function_offset       = 0  @ void*
regArgsIndex_offset   = 4  @ jint
regArgs_offset        = 8  @ jint[MAX_REG_ARGS], MAX_REG_ARGS = 4
stackArgsSize_offset  = 24 @ jint
stackArgsIndex_offset = 28 @ jint
stackArgs_offset      = 32 @ void**
returnValue_offset    = 36 @ FpIntValue
returnType_offset     = 44 @ jint
CallInfo_size         = 48

    .syntax unified
    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .align    2
    .code     16
    .thumb_func __call0
__call0:
    push {r4, r7, lr}

    @ Save frame pointer
    add r7, sp, #4

    @ Save CallInfo* to r4
    mov r4, r0  @ r4 = (CallInfo*) r0

    ldr r0, [r4, #stackArgsSize_offset] @ r0 = ((CallInfo*) r4)->stackArgsSize
    ldr r1, [r4, #stackArgs_offset]     @ r1 = ((CallInfo*) r4)->stackArgs
    add r1, r1, r0, lsl #2               @ r1 = r1 + (r0 << 2)

    teq r0, #0
    beq LsetStackArgsDone
LsetStackArgsNext:
    sub r1, r1, #4
    ldr r2, [r1]
    push {r2}
    subs r0, r0, #1
    bne LsetStackArgsNext
LsetStackArgsDone:

    ldr r0, [r4, #regArgs_offset]    @ r0 = ((CallInfo*) r4)->regArgs[0]
    ldr r1, [r4, #regArgs_offset+4]  @ r1 = ((CallInfo*) r4)->regArgs[1]
    ldr r2, [r4, #regArgs_offset+8]  @ r2 = ((CallInfo*) r4)->regArgs[2]
    ldr r3, [r4, #regArgs_offset+12] @ r3 = ((CallInfo*) r4)->regArgs[3]

    ldr r9, [r4, #function_offset]   @ r9 = ((CallInfo*) r4)->function
    blx  r9

    @ Restore sp to what it was before we pushed the stack args
    sub sp, r7, #4

    pop {r4, r7, pc}
