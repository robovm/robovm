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

# Local storage used by proxy0.
proxy0_stack_size     = (4+CallInfo_size)
# Offset of CallInfo struct on the stack
CallInfo_offset       = 4

# On Linux x86 with GCC 4.5+ the stack should always be 16 byte aligned when calling a function.
# On function entry the return address has been pushed and we push %ebp. These 8 bytes 
# need to be taken into account when calculating proxy0_stack_size_aligned.
proxy0_stack_size_aligned = ((((proxy0_stack_size + 8) + 16 - 1) & ~(16 - 1)) - 8)

RETURN_TYPE_INT    = 0
RETURN_TYPE_LONG   = 1
RETURN_TYPE_FLOAT  = 2
RETURN_TYPE_DOUBLE = 3

    .text

    .globl _proxy0

    .align    16, 0x90
    .type    _proxy0, @function
_proxy0:
.Lproxy0Begin:
    pushl %ebp
    mov   %esp, %ebp

    sub   $proxy0_stack_size_aligned, %esp       # Make room for local variables on the stack

    movl  $0, CallInfo_offset+stackArgsIndex_offset(%esp)      # stackArgsIndex = 0

    lea   8(%ebp), %eax                          # $rax = first stack arg
    mov   %eax, CallInfo_offset+stackArgs_offset(%esp)         # stackArgs = first stack arg

    # Call _rvmProxyHandler with the CallInfo as first argument
    lea   CallInfo_offset(%esp), %eax
    movl  %eax, (%esp)
    call  _rvmProxyHandler

    # For simplicity we always copy returnValue to eax and 
    # returnValue>>32 to edx even if float or double is returned.
    mov   CallInfo_offset+returnValue_offset(%esp), %eax
    mov   CallInfo_offset+returnValue_offset+4(%esp), %edx

    # Only touch the x87 fp stack if a float or double is returned
    mov   CallInfo_offset+returnType_offset(%esp), %ecx
    cmp   $RETURN_TYPE_FLOAT, %ecx
    jne   .LreturnTypeNotFloat
    flds  CallInfo_offset+returnValue_offset(%esp)
    jmp   .LreturnTypeNotDouble
.LreturnTypeNotFloat:
    cmp   $RETURN_TYPE_DOUBLE, %ecx
    jne   .LreturnTypeNotDouble
    fldl  CallInfo_offset+returnValue_offset(%esp)
.LreturnTypeNotDouble:

    leave
    ret

    .size _proxy0, . - .Lproxy0Begin
.Lproxy0End:

