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

eq_HyThreadAbstractMonitor_spinCount1 = 96
eq_HyThreadAbstractMonitor_spinCount2 = 104
eq_HyThreadAbstractMonitor_spinCount3 = 112
eq_HyThreadAbstractMonitor_spinlockState = 80
eq_pointer_size = 4
eqS_hythread_spinlock_acquire = 18
eqS_hythread_spinlock_swapState = 16
eqSR_hythread_spinlock_acquire = 4
eqSR_hythread_spinlock_swapState = 4
eqSRS_hythread_spinlock_acquire = 16
eqSRS_hythread_spinlock_swapState = 16
eqSS_hythread_spinlock_acquire = 72
eqSS_hythread_spinlock_swapState = 64
HYTHREAD_MONITOR_SPINLOCK_OWNED = 1
HYTHREAD_MONITOR_SPINLOCK_UNOWNED = 0
       	#CODE32 SEGMENT FLAT PUBLIC 'CODE'
       	#assume cs:flat,ds:flat,ss:flat
       	#CODE32 ends
       	#CODE32 SEGMENT FLAT PUBLIC 'CODE'
       	#assume cs:flat,ds:flat,ss:flat
        .globl hythread_yield # an extern
        .globl hythread_spinlock_acquire
        .type hythread_spinlock_acquire,@function
        .globl hythread_spinlock_swapState
        .type hythread_spinlock_swapState,@function
## Prototype: IDATA hythread_spinlock_acquire(hythread_t self, hythread_monitor_t monitor);
## Defined in: #THREAD Args: 2

        .text
        .align 8
hythread_spinlock_acquire:
        mov eq_HyThreadAbstractMonitor_spinCount3(%rsi), %rcx
.L2:
        mov eq_HyThreadAbstractMonitor_spinCount2(%rsi), %rdi
.L3:
## Try to cmpxchg 0 into the target field (-1 indicates free)
        cmpl $HYTHREAD_MONITOR_SPINLOCK_UNOWNED, eq_HyThreadAbstractMonitor_spinlockState(%rsi) ## setFlags: true
        jne .L10
        xor %rax, %rax
        mov $HYTHREAD_MONITOR_SPINLOCK_OWNED, %rdx
        lock 
        cmpxchg %rdx, eq_HyThreadAbstractMonitor_spinlockState(%rsi)
        test %rax, %rax                         ## setFlags: true
        jnz .L10
        xor %rdi, %rdi
        jmp .L1
.L10:
        .word 37107                              ## PAUSE
## begin tight loop
        mov eq_HyThreadAbstractMonitor_spinCount1(%rsi), %rax
.L11:
## inside tight loop
        dec %rax                                ## setFlags: true(Converted subtract 1 to dec)
        jnz .L11
## end tight loop
        dec %rdi                                ## setFlags: true(Converted subtract 1 to dec)
        jnz .L3
        mov %rcx, %r12                      ## save VMtemp3_1_3_(HyThreadAbstractMonitor->spinCount3)
        mov %rsi, %r13                      ## save VMtemp3_1_2_(struct HyThreadAbstractMonitor*) in_HyVMThreadSpinlocks>>#hythread_spinlock_acquire
        call hythread_yield@PLT
        mov %r12, %rcx                      ## load VMtemp3_1_3_(HyThreadAbstractMonitor->spinCount3)
        dec %rcx                                ## setFlags: true(Converted subtract 1 to dec)
        mov %r13, %rsi                      ## load VMtemp3_1_2_(struct HyThreadAbstractMonitor*) in_HyVMThreadSpinlocks>>#hythread_spinlock_acquire
        jnz .L2
        mov $-1, %rdi
.L1:
        mov %rdi, %rax
        ret
END_hythread_spinlock_acquire:
        .size hythread_spinlock_acquire,END_hythread_spinlock_acquire - hythread_spinlock_acquire

## Prototype: UDATA hythread_spinlock_swapState(hythread_monitor_t monitor, UDATA newState);
## Defined in: #THREAD Args: 2

        .text
        .align 8
hythread_spinlock_swapState:
## If we are writing in UNOWNED, we are exiting the critical section, therefore
## have to finish up any writes
        test %rsi, %rsi                         ## setFlags: true
        ## memory barrier (no code necessary for write barriers)
        xchg %rsi, eq_HyThreadAbstractMonitor_spinlockState(%rdi)
## if we entered the critical section, (i.e. we swapped out UNOWNED) then
## we have to issue a readBarrier
        test %rsi, %rsi                         ## setFlags: true
        mov %rsi, %rax
        ret
END_hythread_spinlock_swapState:
        .size hythread_spinlock_swapState,END_hythread_spinlock_swapState - hythread_spinlock_swapState

       	#CODE32 ends
        # end of file
