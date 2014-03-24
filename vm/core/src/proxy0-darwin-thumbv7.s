/*
 * Copyright (C) 2012 Trillian Mobile AB
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

RETURN_TYPE_INT    = 0
RETURN_TYPE_LONG   = 1
RETURN_TYPE_FLOAT  = 2
RETURN_TYPE_DOUBLE = 3

    .syntax unified
    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __proxy0
    .align    2
    .code     16
    .thumb_func __proxy0
__proxy0:
    push {r4, r7, lr}

    @ Save frame pointer
    add r7, sp, #4

    sub sp, sp, #CallInfo_size              @ Make room for a CallInfo struct on the stack

    str r0, [sp, #regArgs_offset+0]         @ regArgs[0] = r0
    str r1, [sp, #regArgs_offset+4]         @ regArgs[1] = r1
    str r2, [sp, #regArgs_offset+8]         @ regArgs[2] = r2
    str r3, [sp, #regArgs_offset+12]        @ regArgs[3] = r3

    mov r0, #0
    str r0, [sp, #regArgsIndex_offset]		@ regArgsIndex = 0
    str r0, [sp, #stackArgsIndex_offset]	@ stackArgsIndex = 0

    @ Address of first stack arg (if any) is at r7+8
    add r0, r7, #8
    str r0, [sp, #stackArgs_offset]

    mov r0, sp
    blx __rvmProxyHandler

    @ Return value (if any) is now in r0 (int/float) or in r0:r1 (long/double)
    ldr r0, [sp, #returnValue_offset]
    ldr r1, [sp, #returnValue_offset+4]

    add sp, sp, #CallInfo_size              @ Restore stack pointer

    pop {r4, r7, pc}
