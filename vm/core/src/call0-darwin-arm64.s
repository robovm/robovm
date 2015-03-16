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
function_offset       = 0   // void*
intArgsIndex_offset   = 8   // jint
intArgs_offset        = 16  // IntValue[MAX_INT_ARGS], MAX_INT_ARGS = 8
fpArgsIndex_offset    = 80  // jint
fpArgs_offset         = 88  // FpValue[MAX_FP_ARGS], MAX_FP_ARGS = 8
stackArgsSize_offset  = 152 // jint
stackArgsIndex_offset = 156 // jint
stackArgs_offset      = 160 // char*
returnValue_offset    = 168 // FpIntValue
returnType_offset     = 176 // jint
//padding             = 180 // jint
CallInfo_size         = 184

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .align    2
__call0:
    stp fp, lr, [sp, #-16]!
    mov fp, sp
    str x19, [sp, #-16]!

    // Save CallInfo* to x19
    mov x19, x0  // x19 = (CallInfo*) r0

    // Copy stack args if there are any. The stackArgsSize is assumed to be 
    // divisable by 2 so that stackArgsSize<<3 is properly 16-byte aligned
    ldr w2, [x19, stackArgsSize_offset]
    cbz w2, LsetStackArgsDone
    lsl x2, x2, #3
    sub sp, sp, x2
    mov x0, sp
    ldr x1, [x19, stackArgs_offset]
    bl _memcpy
LsetStackArgsDone:

    ldp x0, x1, [x19, intArgs_offset + 16 * 0] // x0 = ((CallInfo*) x19)->intArgs[0], x1 = ((CallInfo*) x19)->intArgs[1]
    ldp x2, x3, [x19, intArgs_offset + 16 * 1] // x2 = ((CallInfo*) x19)->intArgs[2], x3 = ((CallInfo*) x19)->intArgs[3]
    ldp x4, x5, [x19, intArgs_offset + 16 * 2] // x4 = ((CallInfo*) x19)->intArgs[4], x5 = ((CallInfo*) x19)->intArgs[5]
    ldp x6, x7, [x19, intArgs_offset + 16 * 3] // x6 = ((CallInfo*) x19)->intArgs[6], x7 = ((CallInfo*) x19)->intArgs[7]
    ldp d0, d1, [x19, fpArgs_offset + 16 * 0]  // d0 = ((CallInfo*) x19)->fpArgs[0], d1 = ((CallInfo*) x19)->fpArgs[1]
    ldp d2, d3, [x19, fpArgs_offset + 16 * 1]  // d2 = ((CallInfo*) x19)->fpArgs[2], d3 = ((CallInfo*) x19)->fpArgs[3]
    ldp d4, d5, [x19, fpArgs_offset + 16 * 2]  // d4 = ((CallInfo*) x19)->fpArgs[4], d5 = ((CallInfo*) x19)->fpArgs[5]
    ldp d6, d7, [x19, fpArgs_offset + 16 * 3]  // d6 = ((CallInfo*) x19)->fpArgs[6], d7 = ((CallInfo*) x19)->fpArgs[7]

    ldr x19, [x19, function_offset]   // x19 = ((CallInfo*) x19)->function
    blr  x19

    mov sp, fp
    ldr x19, [sp, #-16]
    ldp fp, lr, [sp], #16
    ret
