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
function_offset       = 0   # void*
intArgsIndex_offset   = 8   # jint
intArgs_offset        = 16  # IntValue[MAX_INT_ARGS], MAX_INT_ARGS = 6
fpArgsIndex_offset    = 64  # jint
fpArgs_offset         = 72  # FpValue[MAX_FP_ARGS], MAX_FP_ARGS = 8
stackArgsSize_offset  = 136 # jint
stackArgsIndex_offset = 140 # jint
stackArgs_offset      = 144 # void**
returnValue_offset    = 152 # FpIntValue
returnType_offset     = 160 # jint
CallInfo_size         = 168

# Local storage used by proxy0.
proxy0_stack_size     = (CallInfo_size)

# On Darwin x86_64 the stack should always be 16 byte aligned when calling a function.
# On function entry the return address has been pushed and we push %rbp so the stack is
# already 16 byte aligned after we've pushed %rbp.
proxy0_stack_size_aligned = ((((proxy0_stack_size) + 16 - 1) & ~(16 - 1)))

    .section    __TEXT,__text,regular,pure_instructions

    .globl    __proxy0
    .align    4, 0x90
__proxy0:
    push  %rbp
    mov   %rsp, %rbp

    sub   $proxy0_stack_size, %rsp             # Make room for a CallInfo struct on the stack

    mov   %rdi, intArgs_offset+0(%rsp)         # intArgs[0] = %rdi
    mov   %rsi, intArgs_offset+8(%rsp)         # intArgs[1] = %rsi
    mov   %rdx, intArgs_offset+16(%rsp)        # intArgs[2] = %rdx
    mov   %rcx, intArgs_offset+24(%rsp)        # intArgs[3] = %rcx
    mov   %r8, intArgs_offset+32(%rsp)         # intArgs[4] = %r8
    mov   %r9, intArgs_offset+40(%rsp)         # intArgs[5] = %r9

    movsd %xmm0, fpArgs_offset+0(%rsp)         # fpArgs[0] = %xmm0
    movsd %xmm1, fpArgs_offset+8(%rsp)         # fpArgs[1] = %xmm1
    movsd %xmm2, fpArgs_offset+16(%rsp)        # fpArgs[2] = %xmm2
    movsd %xmm3, fpArgs_offset+24(%rsp)        # fpArgs[3] = %xmm3
    movsd %xmm4, fpArgs_offset+32(%rsp)        # fpArgs[4] = %xmm4
    movsd %xmm5, fpArgs_offset+40(%rsp)        # fpArgs[5] = %xmm5
    movsd %xmm6, fpArgs_offset+48(%rsp)        # fpArgs[6] = %xmm6
    movsd %xmm7, fpArgs_offset+56(%rsp)        # fpArgs[7] = %xmm7

    movl  $0, intArgsIndex_offset(%rsp)        # intArgsIndex = 0
    movl  $0, fpArgsIndex_offset(%rsp)         # fpArgsIndex = 0
    movl  $0, stackArgsIndex_offset(%rsp)      # stackArgsIndex = 0

    leaq  16(%rbp), %rax                       # $rax = first stack arg
    mov   %rax, stackArgs_offset(%rsp)         # stackArgs = first stack arg

    leaq  (%rsp), %rdi
    callq  __rvmProxyHandler

    mov   returnValue_offset(%rsp), %rax       # if return value is int or long
    movsd returnValue_offset(%rsp), %xmm0      # if return value is float or double

    leave
    ret
