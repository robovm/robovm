function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
CallInfo_size         = 28

    .text

    .globl _call0
    
    .align    16, 0x90
    .type    _call0, @function
_call0:
    .cfi_startproc
    .cfi_personality 0, _nvmPersonality
    .cfi_lsda 16, .Lexception1
    .cfi_def_cfa %esp, 4
.Lcall0Begin:
    push  %ebp
    .cfi_def_cfa_offset 8
    .cfi_offset %ebp, -8
    mov   %esp, %ebp
    .cfi_def_cfa_offset 8
    .cfi_def_cfa %ebp, 8
    mov   8(%ebp), %eax         # %eax = First arg (CallInfo*)

    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
.LsetStackArgsNext:
    test  %ecx, %ecx
    je    .LsetStackArgsDone
    dec   %ecx
    mov   stackArgs_offset(%eax), %edx     # %edx = stackArgs
    lea   (%edx, %ecx, 4), %edx  # %edx = stackArgs + %ecx * 4
    push  (%edx)
    jmp   .LsetStackArgsNext
.LsetStackArgsDone:

.Lcall0TryCatchStart:
    call  *function_offset(%eax)
.Lcall0TryCatchEnd:
.Lcall0TryCatchLandingPad:

    leave
    ret
    .cfi_endproc

    .size _call0, . - .Lcall0Begin
.Lcall0End:

    .section    .gcc_except_table,"a",@progbits
    .align    4
GCC_except_table1:
.Lexception1:
    .byte    255                     # @LPStart Encoding = omit
    .byte    155                     # @TType Encoding = indirect pcrel sdata4
    .byte    158                     # @TType base offset
    .zero    2,128
    .zero    1
    .byte    3                       # Call site Encoding = udata4
    .uleb128 26                      # Call site table length
    .long    .Lcall0TryCatchStart - .Lcall0Begin        # Region start
    .long    .Lcall0TryCatchEnd - .Lcall0TryCatchStart  # Region length
    .long    .Lcall0TryCatchLandingPad - .Lcall0Begin   # Landing pad
    .uleb128 1                                                # Action
    .long    .Lcall0TryCatchEnd - .Lcall0Begin          # Region start
    .long    .Lcall0End - .Lcall0TryCatchEnd            # Region length
    .long    0                       # Landing pad
    .uleb128 0                       # Action
                                       # -- Action Record Table --
                                       # Action Record
    .sleb128 -1                      #   TypeInfo index
    .sleb128 0                       #   Next action
                                       # -- Filter IDs --
    .uleb128 0
    .align   4

