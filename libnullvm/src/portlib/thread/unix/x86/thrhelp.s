#  Licensed to the Apache Software Foundation (ASF) under one or more
#  contributor license agreements.  See the NOTICE file distributed with
#  this work for additional information regarding copyright ownership.
#  The ASF licenses this file to You under the Apache License, Version 2.0
#  (the "License"); you may not use this file except in compliance with
#  the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

eq_hy_null = 0
eq_HyThreadMonitor_pinCount = 28
eq_pointer_size = 4
eqS_current_stack_depth = 16
eqS_hythread_monitor_pin = 16
eqS_hythread_monitor_unpin = 16
eqSR_current_stack_depth = 4
eqSR_hythread_monitor_pin = 4
eqSR_hythread_monitor_unpin = 4
eqSRS_current_stack_depth = 16
eqSRS_hythread_monitor_pin = 16
eqSRS_hythread_monitor_unpin = 16
eqSS_current_stack_depth = 64
eqSS_hythread_monitor_pin = 64
eqSS_hythread_monitor_unpin = 64
       	#CODE32 SEGMENT FLAT PUBLIC 'CODE'
       	#assume cs:flat,ds:flat,ss:flat
       	#CODE32 ends
       	#CODE32 SEGMENT FLAT PUBLIC 'CODE'
       	#assume cs:flat,ds:flat,ss:flat
        .globl hythread_monitor_pin
        .type hythread_monitor_pin,@function
        .globl current_stack_depth
        .type current_stack_depth,@function
        .globl hythread_monitor_unpin
        .type hythread_monitor_unpin,@function

        .text
        .align 4
current_stack_depth:
        push %ebp
        mov %esp, %ebp
        push %esi
        push %edi
        push %ebx
        sub $64, %esp
        movl %ebp, %ebx
        jmp .L2
.L1:
        movl %ecx, %ebx
.L2:
        movl (%ebx), %ecx
        testl %ecx, %ecx                         ## setFlags: true
        jnz .L1
        subl %ebp, %ebx
        movl %ebx, %ecx
        movl %ebx, %eax                          ## RegReg opt
        add $64, %esp
        pop %ebx
        pop %edi
        pop %esi
        pop %ebp
        ret
END_current_stack_depth:
        .size current_stack_depth,END_current_stack_depth - current_stack_depth

## Prototype: void hythread_monitor_pin( hythread_monitor_t monitor, hythread_t osThread);
## Defined in: #THREAD Args: 2

        .text
        .align 4
hythread_monitor_pin:
        push %ebp
        mov %esp, %ebp
        push %esi
        push %edi
        push %ebx
        sub $64, %esp
        movl (eqSRS_hythread_monitor_pin+0+8+eqSS_hythread_monitor_pin)(%esp), %ebx
        movl (eqSRS_hythread_monitor_pin+0+4+eqSS_hythread_monitor_pin)(%esp), %ebx
        lock 
        incl eq_HyThreadMonitor_pinCount(%ebx)   ##  (Converted add 1 to inc)
        add $64, %esp
        pop %ebx
        pop %edi
        pop %esi
        pop %ebp
        ret
END_hythread_monitor_pin:
        .size hythread_monitor_pin,END_hythread_monitor_pin - hythread_monitor_pin

## Prototype: void hythread_monitor_unpin( hythread_monitor_t monitor, hythread_t osThread);
## Defined in: #THREAD Args: 2

        .text
        .align 4
hythread_monitor_unpin:
        push %ebp
        mov %esp, %ebp
        push %esi
        push %edi
        push %ebx
        sub $64, %esp
        movl (eqSS_hythread_monitor_unpin+0+8+eqSRS_hythread_monitor_unpin)(%esp), %ebx
        movl (eqSS_hythread_monitor_unpin+0+eqSRS_hythread_monitor_unpin+4)(%esp), %ebx
        lock 
        decl eq_HyThreadMonitor_pinCount(%ebx)   ##  (Converted subtract 1 to dec)
        add $64, %esp
        pop %ebx
        pop %edi
        pop %esi
        pop %ebp
        ret
END_hythread_monitor_unpin:
        .size hythread_monitor_unpin,END_hythread_monitor_unpin - hythread_monitor_unpin

       	#CODE32 ends
        # end of file
