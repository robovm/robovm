.data

.text

    .globl _nvmProxy0
    .globl _nvmCall0


    .align    16, 0x90
    .type    _nvmProxy0, @function
_nvmProxy0:
.LnvmProxy0Begin:
    .cfi_startproc

    push  %rbp
    .cfi_def_cfa_offset	16
    .cfi_offset		rbp,-16
    mov   %rsp, %rbp
    .cfi_def_cfa_register	rbp

    sub   $120, %rsp            # Make room for a ProxyInfo struct on the stack

    mov   %rdi, 0(%rsp)         # intArgs[0] = %rdi
    mov   %rsi, 8(%rsp)         # intArgs[1] = %rsi
    mov   %rdx, 16(%rsp)        # intArgs[2] = %rdx
    mov   %rcx, 24(%rsp)        # intArgs[3] = %rcx
    mov   %r8, 32(%rsp)         # intArgs[4] = %r8
    mov   %r9, 40(%rsp)         # intArgs[5] = %r9

    movsd %xmm0, 48(%rsp)       # fpArgs[0] = %xmm0
    movsd %xmm1, 56(%rsp)       # fpArgs[1] = %xmm1
    movsd %xmm2, 64(%rsp)       # fpArgs[2] = %xmm2
    movsd %xmm3, 72(%rsp)       # fpArgs[3] = %xmm3
    movsd %xmm4, 80(%rsp)       # fpArgs[4] = %xmm4
    movsd %xmm5, 88(%rsp)       # fpArgs[5] = %xmm5
    movsd %xmm6, 96(%rsp)       # fpArgs[6] = %xmm6
    movsd %xmm7, 104(%rsp)      # fpArgs[7] = %xmm7

    mov   %rbp, %rax
    add   $16, %rax
    mov   %rax,  112(%rsp)      # stackArgs = first stack arg

    mov   %rsp, %rdi
    call  _nvmProxyHandler@PLT

    mov   0(%rsp), %rax         # _nvmProxyHandler writes the return value here
    movsd 0(%rsp), %xmm0        # _nvmProxyHandler writes the return value here

    leave
    .cfi_def_cfa		rsp,8
    ret

    .cfi_endproc

    .size _nvmProxy0, . - .LnvmProxy0Begin
.LnvmProxy0End:
    

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

#    resolve _nvmBcResolveClassForNew0, _nvmBcResolveClassForNew
#    resolve _nvmBcResolveClassForCheckcast0, _nvmBcResolveClassForCheckcast
#    resolve _nvmBcResolveClassForInstanceof0, _nvmBcResolveClassForInstanceof
    

/*

    Template used generate the exception handling tables used by this function:

    %Env = type opaque
    %Object = type opaque

    declare i8* @f(%Env*)
    declare %Object* @j_get_throwable(i8*)
    declare i8* @llvm.eh.exception() nounwind
    declare i64 @llvm.eh.selector.i64(i8*, i8*, ...) nounwind
    declare i32 @_nvmPersonality(i32, i32, i64, i8*, i8*)

    define i8* @catchAll(%Env* %env) {
        %res = invoke i8* @f(%Env* %env) to label %success unwind label %failure
    success:
        ret i8* %res
    failure:
        %ehptr = call i8* @llvm.eh.exception()
        %throwable = call %Object* @j_get_throwable(i8* %ehptr)
        %sel = call i64 (i8*, i8*, ...)* @llvm.eh.selector.i64(i8* %ehptr, i8* bitcast (i32 (i32, i32, i64, i8*, i8*)* @_nvmPersonality to i8*), i32 1)
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

	.section	.gcc_except_table,"a",@progbits
	.align	4
GCC_except_table1:
.Lexception1:
	.byte	255                     # @LPStart Encoding = omit
	.byte	155                     # @TType Encoding = indirect pcrel sdata4
	.byte	158                     # @TType base offset
	.zero	2,128
	.zero	1
	.byte	3                       # Call site Encoding = udata4
	.uleb128	26                  # Call site table length
.Lset0 = .LnvmCall0TryCatchStart - .LnvmCall0Begin       # Region start
	.long	.Lset0
.Lset1 = .LnvmCall0TryCatchEnd - .LnvmCall0TryCatchStart # Region length
	.long	.Lset1
.Lset2 = .LnvmCall0TryCatchLandingPad - .LnvmCall0Begin  # Landing pad
	.long	.Lset2
	.uleb128	1                                            # Action
.Lset3 = .LnvmCall0TryCatchEnd - .LnvmCall0Begin         # Region start
	.long	.Lset3
.Lset4 = .LnvmCall0End - .LnvmCall0TryCatchEnd           # Region length
	.long	.Lset4
	.long	0                       # Landing pad
	.uleb128	0                   # Action
                                # -- Action Record Table --
                                  # Action Record
	.sleb128	-1                    #   TypeInfo index
	.sleb128	0                     #   Next action
                                # -- Filter IDs --
	.uleb128	0
	.align	4

/*
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
*/


	.section	.eh_frame,"aw",@progbits
.LEH_frame0:
.Lsection_eh_frame0:
.Leh_frame_common0:
.Lset5 = .Leh_frame_common_end0-.Leh_frame_common_begin0 # Length of Common Information Entry
	.long	.Lset5
.Leh_frame_common_begin0:
	.long	0                       # CIE Identifier Tag
	.byte	1                       # DW_CIE_VERSION
	.asciz	 "zPLR"                 # CIE Augmentation
	.uleb128	1               # CIE Code Alignment Factor
	.sleb128	-8              # CIE Data Alignment Factor
	.byte	16                      # CIE Return Address Column
	.uleb128	7               # Augmentation Size
	.byte	155                     # Personality Encoding = indirect pcrel sdata4
.Ltmp5:                                 # Personality
	.long	.L_nvmPersonality.DW.stub-.Ltmp5
	.byte	27                      # LSDA Encoding = pcrel sdata4
	.byte	27                      # FDE Encoding = pcrel sdata4
	.byte	12                      # DW_CFA_def_cfa
	.uleb128	7               # Register
	.uleb128	8               # Offset
	.byte	144                     # DW_CFA_offset + Reg (16)
	.uleb128	1               # Offset
	.align	8
.Leh_frame_common_end0:

.LnvmCall0.eh:
.Lset6 = .Leh_frame_end0-.Leh_frame_begin0 # Length of Frame Information Entry
	.long	.Lset6
.Leh_frame_begin0:
.Lset7 = .Leh_frame_begin0-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset7
.Ltmp6:                                 # FDE initial location
	.long	.LnvmCall0Begin-.Ltmp6
.Lset8 = .LnvmCall0End - .LnvmCall0Begin # FDE address range
	.long	.Lset8
	.uleb128	4               # Augmentation size
.Ltmp7:                                 # Language Specific Data Area
	.long	.Lexception1-.Ltmp7
	.byte	4                       # DW_CFA_advance_loc4
.Lset9 = .LnvmCall0TryCatchStart - .LnvmCall0Begin
	.long	.Lset9
	.byte	14                      # DW_CFA_def_cfa_offset
	.uleb128	16              # Offset
	.align	8
.Leh_frame_end0:


	.section	.data.rel,"aw",@progbits
.L_nvmPersonality.DW.stub:
	.quad	_nvmPersonality

/*
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
    .long    _nvmPersonality         # Personality
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
*/
