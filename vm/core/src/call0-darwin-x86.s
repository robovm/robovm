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
function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
CallInfo_size         = 28

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .globl    __call0_end
    .align    4, 0x90
__call0:
Lcall0Begin:
    pushl %ebp
    mov   %esp, %ebp
    mov   8(%ebp), %eax         # %eax = First arg (CallInfo*)

    # Make sure the stack is 16-byte aligned after we have pushed all stack args
    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
    shl   $2, %ecx              # %ecx <<= 2   // %ecx equals the number of bytes needed by the stack args
    add   $8, %ecx              # Add 4 bytes for %ebp pushed in the prologue and 4 bytes for return address
    # Now %ecx is n in (-(n & 15) + 16) & 15)
    and   $15, %ecx             # n &= 15
    neg   %ecx                  # n = -n
    add   $16, %ecx             # n += 16
    and   $15, %ecx             # n &= 15
    sub   %ecx, %esp            # %esp -= %ecx  // Adjust %esp

    # Now copy stack args    
    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
LsetStackArgsNext:
    test  %ecx, %ecx
    je    LsetStackArgsDone
    dec   %ecx
    mov   stackArgs_offset(%eax), %edx     # %edx = stackArgs
    lea   (%edx, %ecx, 4), %edx  # %edx = stackArgs + %ecx * 4
    push  (%edx)
    jmp   LsetStackArgsNext
LsetStackArgsDone:

    call  *function_offset(%eax)

    leave
    ret
Lcall0End:
    # Extra ret here avoids "sectionForAddress(0x2F) address not in any section for architecture i386" linker error
    ret
