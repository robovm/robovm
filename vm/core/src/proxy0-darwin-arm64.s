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

# Local storage used by proxy0.
proxy0_stack_size     = (CallInfo_size)

# On Darwin ARM64 the stack should always be 16 byte aligned when calling a function.
proxy0_stack_size_aligned = ((((proxy0_stack_size) + 16 - 1) & ~(16 - 1)))

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __proxy0
    .align    2
__proxy0:
    stp fp, lr, [sp, #-16]!
    mov fp, sp

    sub sp, sp, proxy0_stack_size_aligned  // Make room for a CallInfo struct on the stack

    stp x0, x1, [sp, intArgs_offset + 16 * 0] // intArgs[0] = x0, intArgs[1] = x1
    stp x2, x3, [sp, intArgs_offset + 16 * 1] // intArgs[2] = x2, intArgs[3] = x3
    stp x4, x5, [sp, intArgs_offset + 16 * 2] // intArgs[4] = x4, intArgs[5] = x5
    stp x6, x7, [sp, intArgs_offset + 16 * 3] // intArgs[6] = x6, intArgs[7] = x7

    stp d0, d1, [sp, fpArgs_offset + 16 * 0]  // fpArgs[0] = d0, fpArgs[1] = d1
    stp d2, d3, [sp, fpArgs_offset + 16 * 1]  // fpArgs[2] = d2, fpArgs[3] = d3
    stp d4, d5, [sp, fpArgs_offset + 16 * 2]  // fpArgs[4] = d4, fpArgs[5] = d5
    stp d6, d7, [sp, fpArgs_offset + 16 * 3]  // fpArgs[6] = d6, fpArgs[7] = d7

    mov x0, #0
    str w0, [sp, intArgsIndex_offset]      // intArgsIndex = 0
    str w0, [sp, fpArgsIndex_offset]       // fpArgsIndex_offset = 0
    str w0, [sp, stackArgsIndex_offset]	   // stackArgsIndex = 0

    add x0, sp, proxy0_stack_size_aligned+16
    str x0, [sp, stackArgs_offset]

    mov x0, sp
    bl __rvmProxyHandler

    // Return value (if any) is now in x0 (int/long) or in d0 (float/double)
    ldr x0, [sp, returnValue_offset]
    ldr d0, [sp, returnValue_offset]

    mov sp, fp
    ldp fp, lr, [sp], #16
    ret
