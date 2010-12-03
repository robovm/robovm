.data

.text

    .globl _nvmEmptyFunction
    .globl _nvmBcGetStatic8
    .globl _nvmBcGetStatic16
    .globl _nvmBcGetStatic32
    .globl _nvmBcGetStatic64
    .globl _nvmBcGetStaticFloat
    .globl _nvmBcGetStaticDouble
    .globl _nvmBcPutStatic8
    .globl _nvmBcPutStatic16
    .globl _nvmBcPutStatic32
    .globl _nvmBcPutStatic64
    .globl _nvmBcPutStaticFloat
    .globl _nvmBcPutStaticDouble
    .globl _nvmBcGetField8
    .globl _nvmBcGetField16
    .globl _nvmBcGetField32
    .globl _nvmBcGetField64
    .globl _nvmBcGetFieldFloat
    .globl _nvmBcGetFieldDouble
    .globl _nvmBcPutField8
    .globl _nvmBcPutField16
    .globl _nvmBcPutField32
    .globl _nvmBcPutField64
    .globl _nvmBcPutFieldFloat
    .globl _nvmBcPutFieldDouble
    .globl _nvmBcResolveClassForNew0
    .globl _nvmBcResolveClassForCheckcast0
    .globl _nvmBcResolveClassForInstanceof0
    .globl _nvmBcResolveFieldForGetStatic0
    .globl _nvmBcResolveFieldForPutStatic0
    .globl _nvmBcResolveFieldForGetField0
    .globl _nvmBcResolveFieldForPutField0
    .globl _nvmBcResolveMethodForInvokeStatic0
    .globl _nvmBcResolveMethodForInvokeVirtual0
    .globl _nvmBcResolveMethodForInvokeSpecial0
    .globl _nvmBcResolveMethodForInvokeInterface0
    .globl _nvmCall0
    .globl _nvmBcInvokeVirtual0
    .globl _nvmBcInvokeInterface0


    .align    16, 0x90
    .type    _nvmEmptyFunction, @function
_nvmEmptyFunction:
.LnvmEmptyFunctionBegin:
    ret

    .size _nvmEmptyFunction, . - .LnvmEmptyFunctionBegin
.LnvmEmptyFunctionEnd:
    

/* ... _nvmCall0(CallInfo* callInfo) */
    .align    16, 0x90
    .type    _nvmCall0, @function
_nvmCall0:
.LnvmCall0Begin:
    push  %rbp
    mov   %rsp, %rbp

    sub   $0, %rsp

    mov   %rdi, %rax

    mov   8(%rax), %rdi         # %rdi = intArgs[0]
    mov   16(%rax), %rsi        # %rsi = intArgs[1]
    mov   24(%rax), %rdx        # %rdx = intArgs[2]
    mov   32(%rax), %rcx        # %rcx = intArgs[3]
    mov   40(%rax), %r8         # %r8 = intArgs[4]
    mov   48(%rax), %r9         # %r9 = intArgs[5]

    movsd 56(%rax), %xmm0       # %xmm0 = fpArgs[0]
    movsd 64(%rax), %xmm1       # %xmm1 = fpArgs[1]
    movsd 72(%rax), %xmm2       # %xmm2 = fpArgs[2]
    movsd 80(%rax), %xmm3       # %xmm3 = fpArgs[3]
    movsd 88(%rax), %xmm4       # %xmm4 = fpArgs[4]
    movsd 96(%rax), %xmm5       # %xmm5 = fpArgs[5]
    movsd 104(%rax), %xmm6      # %xmm6 = fpArgs[6]
    movsd 112(%rax), %xmm7      # %xmm7 = fpArgs[7]

    mov   120(%rax), %r10       # %r10 = stackArgsCount
.LsetStackArgsNext:
    cmp   $0, %r10
    je    .LsetStackArgsDone
    sub   $0x1, %r10
    mov   128(%rax), %r11       # %r11 = stackArgs
    lea   (%r11, %r10, 8), %r11 # %r11 = stackArgs + %r10 * 8
    push  (%r11)
    jmp   .LsetStackArgsNext
.LsetStackArgsDone:

.LnvmCall0TryCatchStart:
    call  *(%rax)
.LnvmCall0TryCatchEnd:
.LnvmCall0TryCatchLandingPad:

    leave
    ret

    .size _nvmCall0, . - .LnvmCall0Begin
.LnvmCall0End:


    .macro getStatic name, ins, reg
    .align    16, 0x90
    .type    \name, @function
\name:
.L\name\()Begin:
    mov     24(%rdi), %rax # GetStatic->address is at offset 24
    \ins    (%rax), \reg
    ret
.L\name\()End:
    .endm


    .macro putStatic name, ins, reg
    .align    16, 0x90
    .type    \name, @function
\name:
.L\name\()Begin:
    mov     24(%rdi), %rax # PutStatic->address is at offset 24
    \ins    \reg, (%rax)
    ret
    .size \name, . - .L\name\()Begin
.L\name\()End:
    .endm


    .macro getField name, ins, reg
    .align    16, 0x90
    .type    \name, @function
\name:
.L\name\()Begin:
    xor     %rax, %rax
    mov     24(%rdi), %eax # GetField->offset is at offset 24
    add     %rax, %rdx     # %rdx points to Object. Add offset to pointer.
    \ins    (%rdx), \reg
    ret
.L\name\()End:
    .endm


    .macro putField name, ins, reg
    .align    16, 0x90
    .type    \name, @function
\name:
.L\name\()Begin:
    xor     %rax, %rax
    mov     24(%rdi), %eax # PutField->offset is at offset 24
    add     %rax, %rdx     # %rdx points to Object. Add offset to pointer.
    \ins    \reg, (%rdx)
    ret
.L\name\()End:
    .endm


    getStatic _nvmBcGetStatic8,  movb, %al
    getStatic _nvmBcGetStatic16, movw, %ax
    getStatic _nvmBcGetStatic32, movl, %eax
    getStatic _nvmBcGetStatic64, movq, %rax
    getStatic _nvmBcGetStaticFloat, movss, %xmm0
    getStatic _nvmBcGetStaticDouble, movsd, %xmm0

    putStatic _nvmBcPutStatic8,  movb, %dl
    putStatic _nvmBcPutStatic16, movw, %dx
    putStatic _nvmBcPutStatic32, movl, %edx
    putStatic _nvmBcPutStatic64, movq, %rdx
    putStatic _nvmBcPutStaticFloat, movss, %xmm0
    putStatic _nvmBcPutStaticDouble, movsd, %xmm0

    getField _nvmBcGetField8,  movb, %al
    getField _nvmBcGetField16, movw, %ax
    getField _nvmBcGetField32, movl, %eax
    getField _nvmBcGetField64, movq, %rax
    getField _nvmBcGetFieldFloat, movss, %xmm0
    getField _nvmBcGetFieldDouble, movsd, %xmm0

    putField _nvmBcPutField8,  movb, %cl
    putField _nvmBcPutField16, movw, %cx
    putField _nvmBcPutField32, movl, %ecx
    putField _nvmBcPutField64, movq, %rcx
    putField _nvmBcPutFieldFloat, movss, %xmm0
    putField _nvmBcPutFieldDouble, movsd, %xmm0


/* ... _nvmBcResolve0(Invoke(Static|Special|Virtual|Interface)|(Get|Put)(Static|Field)* i, Env* env, ...) */

    .align    16, 0x90
    .type    _nvmBcResolve0, @function
_nvmBcResolve0:
.LnvmBcResolve0Begin:
    .cfi_startproc

    push  %rbp
    .cfi_def_cfa_offset     16
    .cfi_offset             rbp, -16
    mov   %rsp, %rbp
    .cfi_def_cfa_register   rbp

    sub   $120, %rsp

    /* Save the original register args */
    mov   %rdi, (%rsp)
    mov   %rsi, 8(%rsp)
    mov   %rdx, 16(%rsp)
    mov   %rcx, 24(%rsp)
    mov   %r8, 32(%rsp)
    mov   %r9, 40(%rsp)
    movsd %xmm0, 48(%rsp)
    movsd %xmm1, 56(%rsp)
    movsd %xmm2, 64(%rsp)
    movsd %xmm3, 72(%rsp)
    movsd %xmm4, 80(%rsp)
    movsd %xmm5, 88(%rsp)
    movsd %xmm6, 96(%rsp)
    movsd %xmm7, 104(%rsp)

    /* %rax points to the local resolve function */
    mov   %rax, 112(%rsp)

    /* 
      Resolve method/field
        i->common->resolve(i->common, env);
     */
    mov   8(%rdi), %rdi   # %rdi = i->common
    call  *(%rdi)         # (%rdi) = i->common->resolve

    /* Common resolution succeeded */

    /* Now do the local resolution which also does access checks */
    mov   (%rsp), %rdi    # %rdi = i
    mov   8(%rsp), %rsi   # %rsi = env
    call  *112(%rsp)

    /* Restore the original register args */
    mov   (%rsp), %rdi
    mov   8(%rsp), %rsi
    mov   16(%rsp), %rdx
    mov   24(%rsp), %rcx
    mov   32(%rsp), %r8
    mov   40(%rsp), %r9
    movsd 48(%rsp), %xmm0
    movsd 56(%rsp), %xmm1
    movsd 64(%rsp), %xmm2
    movsd 72(%rsp), %xmm3
    movsd 80(%rsp), %xmm4
    movsd 88(%rsp), %xmm5
    movsd 96(%rsp), %xmm6
    movsd 104(%rsp), %xmm7

    /* Restore the stack */
    mov  %rbp, %rsp
    pop  %rbp

    .cfi_def_cfa            rsp, 8

    /* Call the function i->function */
    jmp  *(%rdi)

    .cfi_endproc

    .size _nvmBcResolve0, . - .LnvmBcResolve0Begin
.LnvmBcResolve0End:

    .macro resolve name, f
    .align    16, 0x90
    .type    \name, @function
\name:
.L\name\()Begin:
    mov   \f@GOTPCREL(%rip), %rax
    jmp  _nvmBcResolve0
    .size \name, . - .L\name\()Begin
.L\name\()End:
    .endm

    resolve _nvmBcResolveClassForNew0, _nvmBcResolveClassForNew
    resolve _nvmBcResolveClassForCheckcast0, _nvmBcResolveClassForCheckcast
    resolve _nvmBcResolveClassForInstanceof0, _nvmBcResolveClassForInstanceof
    resolve _nvmBcResolveFieldForGetStatic0, _nvmBcResolveFieldForGetStatic
    resolve _nvmBcResolveFieldForPutStatic0, _nvmBcResolveFieldForPutStatic
    resolve _nvmBcResolveFieldForGetField0, _nvmBcResolveFieldForGetField
    resolve _nvmBcResolveFieldForPutField0, _nvmBcResolveFieldForPutField
    resolve _nvmBcResolveMethodForInvokeStatic0, _nvmBcResolveMethodForInvokeStatic
    resolve _nvmBcResolveMethodForInvokeVirtual0, _nvmBcResolveMethodForInvokeVirtual
    resolve _nvmBcResolveMethodForInvokeSpecial0, _nvmBcResolveMethodForInvokeSpecial
    resolve _nvmBcResolveMethodForInvokeInterface0, _nvmBcResolveMethodForInvokeInterface
    

/* ... _nvmBcInvokeVirtual0(InvokeVirtual* i, Env* env, Object* obj, ...) */

# TODO: Optimize me using virtual method table

    .align    16, 0x90
    .type    _nvmBcInvokeVirtual0, @function
_nvmBcInvokeVirtual0:
.LnvmBcInvokeVirtual0Begin:
    .cfi_startproc

    push  %rbp
    .cfi_def_cfa_offset     16
    .cfi_offset             rbp, -16
    mov   %rsp, %rbp
    .cfi_def_cfa_register   rbp

    sub   $112, %rsp

    /* Save the original register args */
    mov   %rdi, (%rsp)
    mov   %rsi, 8(%rsp)
    mov   %rdx, 16(%rsp)
    mov   %rcx, 24(%rsp)
    mov   %r8, 32(%rsp)
    mov   %r9, 40(%rsp)
    movsd %xmm0, 48(%rsp)
    movsd %xmm1, 56(%rsp)
    movsd %xmm2, 64(%rsp)
    movsd %xmm3, 72(%rsp)
    movsd %xmm4, 80(%rsp)
    movsd %xmm5, 88(%rsp)
    movsd %xmm6, 96(%rsp)
    movsd %xmm7, 104(%rsp)

    call *_nvmBcGetMethodImplForInvokeVirtual@GOTPCREL(%rip) # %rax = method->impl

    /* Restore the original register args */
    mov   (%rsp), %rdi
    mov   8(%rsp), %rsi
    mov   16(%rsp), %rdx
    mov   24(%rsp), %rcx
    mov   32(%rsp), %r8
    mov   40(%rsp), %r9
    movsd 48(%rsp), %xmm0
    movsd 56(%rsp), %xmm1
    movsd 64(%rsp), %xmm2
    movsd 72(%rsp), %xmm3
    movsd 80(%rsp), %xmm4
    movsd 88(%rsp), %xmm5
    movsd 96(%rsp), %xmm6
    movsd 104(%rsp), %xmm7

    /* Restore the stack */
    mov  %rbp, %rsp
    pop  %rbp

    .cfi_def_cfa            rsp, 8

    /* Call the function method->impl */
    jmp  *%rax

    .cfi_endproc

    .size _nvmBcInvokeVirtual0, . - .LnvmBcInvokeVirtual0Begin
.LnvmBcInvokeVirtual0End:


/* ... _nvmBcInvokeInterface0(InvokeVirtual* i, Env* env, Object* obj, ...) */

# TODO: Optimize me using interface method table

    .align    16, 0x90
    .type    _nvmBcInvokeInterface0, @function
_nvmBcInvokeInterface0:
.LnvmBcInvokeInterface0Begin:
    .cfi_startproc

    push  %rbp
    .cfi_def_cfa_offset     16
    .cfi_offset             rbp, -16
    mov   %rsp, %rbp
    .cfi_def_cfa_register   rbp

    sub   $112, %rsp

    /* Save the original register args */
    mov   %rdi, (%rsp)
    mov   %rsi, 8(%rsp)
    mov   %rdx, 16(%rsp)
    mov   %rcx, 24(%rsp)
    mov   %r8, 32(%rsp)
    mov   %r9, 40(%rsp)
    movsd %xmm0, 48(%rsp)
    movsd %xmm1, 56(%rsp)
    movsd %xmm2, 64(%rsp)
    movsd %xmm3, 72(%rsp)
    movsd %xmm4, 80(%rsp)
    movsd %xmm5, 88(%rsp)
    movsd %xmm6, 96(%rsp)
    movsd %xmm7, 104(%rsp)

    call *_nvmBcGetMethodImplForInvokeInterface@GOTPCREL(%rip) # %rax = method->impl

    /* Restore the original register args */
    mov   (%rsp), %rdi
    mov   8(%rsp), %rsi
    mov   16(%rsp), %rdx
    mov   24(%rsp), %rcx
    mov   32(%rsp), %r8
    mov   40(%rsp), %r9
    movsd 48(%rsp), %xmm0
    movsd 56(%rsp), %xmm1
    movsd 64(%rsp), %xmm2
    movsd 72(%rsp), %xmm3
    movsd 80(%rsp), %xmm4
    movsd 88(%rsp), %xmm5
    movsd 96(%rsp), %xmm6
    movsd 104(%rsp), %xmm7

    /* Restore the stack */
    mov  %rbp, %rsp
    pop  %rbp

    .cfi_def_cfa            rsp, 8

    /* Call the function method->impl */
    jmp  *%rax

    .cfi_endproc

    .size _nvmBcInvokeInterface0, . - .LnvmBcInvokeInterface0Begin
.LnvmBcInvokeInterface0End:


/*

    Template used generate the exception handling tables used by this function:

    %Env = type opaque
    %Object = type opaque

    declare i8* @f(%Env*)
    declare %Object* @j_get_throwable(i8*)
    declare i8* @llvm.eh.exception() nounwind
    declare i64 @llvm.eh.selector.i64(i8*, i8*, ...) nounwind
    declare i32 @j_eh_personality(i32, i32, i64, i8*, i8*)

    define i8* @catchAll(%Env* %env) {
        %res = invoke i8* @f(%Env* %env) to label %success unwind label %failure
    success:
        ret i8* %res
    failure:
        %ehptr = call i8* @llvm.eh.exception()
        %throwable = call %Object* @j_get_throwable(i8* %ehptr)
        %sel = call i64 (i8*, i8*, ...)* @llvm.eh.selector.i64(i8* %ehptr, i8* bitcast (i32 (i32, i32, i64, i8*, i8*)* @j_eh_personality to i8*), i32 1)
        ret i8* null
    }

 */

/*
    .align    16, 0x90
    .type    _nvmCallAndCatchAll, @function
_nvmCallAndCatchAll:
.LnvmCallAndCatchAllBegin:
    push  %rbp
    mov   %rsp, %rbp
    sub   $24, %rsp
    mov   %rdi, (%rsp)
    mov   %rsi, 8(%rsp)
    mov   %rcx, 16(%rsp)

    xor   %rcx, %rcx
    mov   8(%rdi), %ecx   # number of bytes of stack space the called function uses for its args
#  TODO: branch if %ecx == 0?
    mov   %rbp, %rsi
    add   $16, %rsi        # %rsi now points to the left most of the function's arguments
    mov   %rsp, %rdi
    sub   %rcx, %rdi      # %rdi now points to place on the stack where the new frame starts
    mov   %rdi, %rax      # Save the new frame pointer temporarily in %rax
    shr   $3, %ecx        # number of 64-bit values to copy to the new frame
    rep   movsq           # copy the args

    mov   (%rsp), %rdi    # restore %rdi
    mov   8(%rsp), %rsi   # restore %rsi
    mov   16(%rsp), %rcx  # restore %rcx
    mov   %rax, %rsp      # set new frame pointer

.LcallStart:
    call  *(%rdi)
.LcalLnvmCallAndCatchAllEnd:
.Lcatch:

    leave
    ret

    .size _nvmCallAndCatchAll, . - .LnvmCallAndCatchAllBegin
.LnvmCallAndCatchAllEnd:
    .section    .gcc_except_table,"a",@progbits
    .align    4
*/

/* TODO: as in GCC 4.4 has support for unwind information using the .cfi directives. Maybe we should use those for exception handling? */
GCC_except_table1:
.Lexception1:
    .byte    255                     # @LPStart Encoding = omit
    .byte    3                       # @TType Encoding = udata4
    .byte    158                     # @TType base offset
    .zero    2, 128
    .zero    1
    .byte    3                       # Call site Encoding = udata4
    .uleb128 26                      # Call site table length
    .set     .Lset1eh, .LnvmCall0TryCatchStart - .LnvmCall0Begin
    .long    .Lset1eh                # Region start
    .set     .Lset2eh, .LnvmCall0TryCatchEnd - .LnvmCall0TryCatchStart
    .long    .Lset2eh                # Region length
    .set     .Lset3eh,.LnvmCall0TryCatchLandingPad - .LnvmCall0Begin
    .long    .Lset3eh                # Landing pad
    .uleb128 1                       # Action
    .set     .Lset4eh, .LnvmCall0TryCatchEnd - .LnvmCall0Begin
    .long    .Lset4eh                # Region start
    .set     .Lset5eh, .LnvmCall0End - .LnvmCall0TryCatchEnd
    .long    .Lset5eh                # Region length
    .long    0                       # Landing pad
    .uleb128 0                       # Action
                                     # -- Action Record Table --
                                       # Action Record:
    .sleb128 -1                        #   TypeInfo index
    .sleb128 0                         #   Next action
                                     # -- Filter IDs --
    .uleb128 0
    .align   4

    .section    .eh_frame,"aw",@progbits
.LEH_frame0:
.Lsection_eh_frame:
.Leh_frame_common:
    .set    .Lset6eh,.Leh_frame_common_end-.Leh_frame_common_begin
    .long    .Lset6eh                # Length of Common Information Entry
.Leh_frame_common_begin:
    .long    0                       # CIE Identifier Tag
    .byte    1                       # DW_CIE_VERSION
    .asciz   "zPLR"
                                        # CIE Augmentation
    .uleb128 1               # CIE Code Alignment Factor
    .sleb128 -8              # CIE Data Alignment Factor
    .byte    16
                                        # CIE Return Address Column
    .uleb128 7               # Augmentation Size
    .byte    3                       # Personality Encoding = udata4
    .long    j_eh_personality        # Personality
    .byte    3                       # LSDA Encoding = udata4
    .byte    3                       # FDE Encoding = udata4
    .byte    12                      # CFA_def_cfa
    .uleb128 7               # Register
    .uleb128 8               # Offset
    .byte    144                     # DW_CFA_offset + Reg (16)
    .uleb128 1               # Offset
    .align   8
.Leh_frame_common_end:

.LnvmCall0.eh:
    .set     .Lset7eh,.Leh_frame_end1-.Leh_frame_begin1
    .long    .Lset7eh                # Length of Frame Information Entry
.Leh_frame_begin1:
    .long    .Leh_frame_begin1-.Leh_frame_common # FDE CIE offset
    .long    .LnvmCall0Begin        # FDE initial location
    .set     .Lset8eh,.LnvmCall0End - .LnvmCall0Begin
    .long    .Lset8eh                # FDE address range
    .uleb128 4               # Augmentation size
    .long    .Lexception1            # Language Specific Data Area
    .byte    4                       # CFA_advance_loc4
    .set    .Lset9eh,.LnvmCall0TryCatchStart - .LnvmCall0Begin
    .long    .Lset9eh
    .byte    14                      # CFA_def_cfa_offset
    .uleb128 16              # Offset
    .align   8
.Leh_frame_end1:

