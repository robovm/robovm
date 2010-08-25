.data

.text

    .globl _nvmCallMethod
    
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

    .align    16, 0x90
    .type    _nvmCallMethod, @function
_nvmCallMethod:
.Lbegin:
    push  %rbp
    mov   %rsp, %rbp
    sub   $24, %rsp
    mov   %rdi, (%rsp)
    mov   %rsi, 8(%rsp)
    mov   %rcx, 16(%rsp)

    xor   %rcx, %rcx
    mov   8(%rdi), %ecx   # number of bytes of stack space the called function uses for its args
/* TODO: branch if %ecx == 0? */
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
.LcallEnd:
.Lcatch:

    leave
    ret

    .size _nvmCallMethod, . - .Lbegin
.Lend:
    .section    .gcc_except_table,"a",@progbits
    .align    4
GCC_except_table1:
.Lexception1:
    .byte    255                     # @LPStart Encoding = omit
    .byte    3                       # @TType Encoding = udata4
    .byte    158                     # @TType base offset
    .zero    2, 128
    .zero    1
    .byte    3                       # Call site Encoding = udata4
    .uleb128 26                      # Call site table length
    .set     .Lset1eh, .LcallStart - .Lbegin
    .long    .Lset1eh                # Region start
    .set     .Lset2eh, .LcallEnd - .LcallStart
    .long    .Lset2eh                # Region length
    .set     .Lset3eh,.Lcatch - .Lbegin
    .long    .Lset3eh                # Landing pad
    .uleb128 1                       # Action
    .set     .Lset4eh, .LcallEnd - .Lbegin
    .long    .Lset4eh                # Region start
    .set     .Lset5eh, .Lend - .LcallEnd
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

.LcatchAll.eh:
    .set     .Lset7eh,.Leh_frame_end1-.Leh_frame_begin1
    .long    .Lset7eh                # Length of Frame Information Entry
.Leh_frame_begin1:
    .long    .Leh_frame_begin1-.Leh_frame_common # FDE CIE offset
    .long    .Lbegin        # FDE initial location
    .set     .Lset8eh,.Lend - .Lbegin
    .long    .Lset8eh                # FDE address range
    .uleb128 4               # Augmentation size
    .long    .Lexception1            # Language Specific Data Area
    .byte    4                       # CFA_advance_loc4
    .set    .Lset9eh,.LcallStart - .Lbegin
    .long    .Lset9eh
    .byte    14                      # CFA_def_cfa_offset
    .uleb128 16              # Offset
    .align   8
.Leh_frame_end1:

