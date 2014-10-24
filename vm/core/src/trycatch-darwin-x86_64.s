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

prev_offset  = 0
sel_offset   = 8
fp_offset    = 16
pc_offset    = 24
rbx_offset   = 32
rsp_offset   = 40
r12_offset   = 48
r13_offset   = 56
r14_offset   = 64
r15_offset   = 72
mxcsr_offset = 80
fpucw_offset = 84

Env_trycatchContext_offset = 56

    .section    __TEXT,__text,regular,pure_instructions

/*
 * rvmTrycatchEnter(Env* env, TrycatchContext* tc) 
 */
    .globl _rvmTrycatchEnter
    .align 4, 0x90
_rvmTrycatchEnter:
    # env is in %rdi
    # tc is in %rsi
    mov %rbp, fp_offset(%rsi)       # tc->fp = %rbp
    mov 0(%rsp), %rdx
    mov %rdx, pc_offset(%rsi)       # tc->pc = return address
    mov %rbx, rbx_offset(%rsi)      # tc->rbx = %rbx
    mov %rsp, rsp_offset(%rsi)      # tc->rsp = %rsp
    mov %r12, r12_offset(%rsi)      # tc->fp = %rbp
    mov %r13, r13_offset(%rsi)      # tc->fp = %rbp
    mov %r14, r14_offset(%rsi)      # tc->fp = %rbp
    mov %r15, r15_offset(%rsi)      # tc->fp = %rbp
    stmxcsr mxcsr_offset(%rsi)      # tc->mxcsr = %mxcsr
    fnstcw  fpucw_offset(%rsi)      # tc->fpucw = %fpucw

    # tc->prev = env->trycatchContext;
    # env->trycatchContext = tc;
    mov   Env_trycatchContext_offset(%rdi), %rdx
    mov   %rdx, prev_offset(%rsi)
    mov   %rsi, Env_trycatchContext_offset(%rdi)

    # Return 0
    xor %rax, %rax
    ret

/*
 * rvmTrycatchJump(TrycatchContext* tc) 
 */
    .globl _rvmTrycatchJump
    .align 4, 0x90
_rvmTrycatchJump:
    # tc is in %rdi
    mov fp_offset(%rdi), %rbp       # %rbp = tc->fp
    mov rbx_offset(%rdi), %rbx      # %rbx = tc->rbx
    mov rsp_offset(%rdi), %rsp      # %rsp = tc->rsp
    mov r12_offset(%rdi), %r12      # %r12 = tc->r12
    mov r13_offset(%rdi), %r13      # %r13 = tc->r13
    mov r14_offset(%rdi), %r14      # %r14 = tc->r14
    mov r15_offset(%rdi), %r15      # %r15 = tc->r15
    ldmxcsr mxcsr_offset(%rdi)      # %mxcsr = tc->mxcsr
    fldcw fpucw_offset(%rdi)        # %fpucw = tc->fpucw

    # Remove the return address of the caller of rvmTrycatchEnter from the stack
    pop %rax

    # Set the return value that the call to rvmTrycatchEnter will return
    mov sel_offset(%rdi), %rax
    # Jump to the return address from the initial call to rvmTrycatchEnter
    jmp *pc_offset(%rdi)
