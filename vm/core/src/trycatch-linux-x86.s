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
fp_offset   = 8
pc_offset   = 12
esp_offset  = 16
ebx_offset  = 20
esi_offset  = 24
edi_offset  = 28

Env_trycatchContext_offset = 28

    .text

/*
 * rvmTrycatchEnter(Env* env, TrycatchContext* tc) 
 */
    .globl rvmTrycatchEnter
    
    .align    16, 0x90
    .type    rvmTrycatchEnter, @function
rvmTrycatchEnter:
.LrvmTrycatchEnterBegin:
    mov   8(%esp), %eax          # %eax = tc
    mov   %ebp, fp_offset(%eax)
    mov   (%esp), %ecx           # %ecx = return address
    mov   %ecx, pc_offset(%eax)
    mov   %esp, esp_offset(%eax)
    mov   %ebx, ebx_offset(%eax)
    mov   %esi, esi_offset(%eax)
    mov   %edi, edi_offset(%eax)

    # tc->prev = env->trycatchContext;
    # env->trycatchContext = tc;
    mov   4(%esp), %ecx         # %ecx = env
    mov   Env_trycatchContext_offset(%ecx), %edx
    mov   %edx, prev_offset(%eax)
    mov   %eax, Env_trycatchContext_offset(%ecx)

    # Return 0
    mov   $0, %eax

    ret

    .size rvmTrycatchEnter, . - .LrvmTrycatchEnterBegin
.LrvmTrycatchEnterEnd:

/*
 * rvmTrycatchJump(TrycatchContext* tc) 
 */
    .globl rvmTrycatchJump
    
    .align    16, 0x90
    .type    rvmTrycatchJump, @function
rvmTrycatchJump:
.LrvmTrycatchJumpBegin:
    mov   4(%esp), %ecx        # %ecx = tc
    mov   fp_offset(%ecx), %ebp
    mov   esp_offset(%ecx), %esp
    mov   ebx_offset(%ecx), %ebx
    mov   esi_offset(%ecx), %esi
    mov   edi_offset(%ecx), %edi

    # Remove the return address of the caller of rvmTrycatchJump from the stack
    pop   %eax

    # Set the return value that the call to rvmTrycatchEnter will return
    mov   sel_offset(%ecx), %eax
    # Jump to the return address from the initial call to rvmTrycatchEnter
    jmp   *pc_offset(%ecx)

    .size rvmTrycatchJump, . - .LrvmTrycatchJumpBegin
.LrvmTrycatchJumpEnd:
