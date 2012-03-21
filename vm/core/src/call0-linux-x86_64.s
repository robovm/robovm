function_offset       = 0   # void*
intArgsIndex_offset   = 8   # jint
intArgs_offset        = 16  # IntValue[MAX_INT_ARGS], MAX_INT_ARGS = 6
fpArgsIndex_offset    = 64  # jint
fpArgs_offset         = 72  # FpValue[MAX_FP_ARGS], MAX_FP_ARGS = 8
stackArgsSize_offset  = 136 # jint
stackArgsIndex_offset = 140 # jint
stackArgs_offset      = 144 # void**
returnValue_offset    = 152 # FpIntValue
returnType_offset     = 160 # jint

RETURN_TYPE_INT    = 0
RETURN_TYPE_LONG   = 1
RETURN_TYPE_FLOAT  = 2
RETURN_TYPE_DOUBLE = 3

    .text

    .globl _call0

    .align    16, 0x90
    .type    _call0, @function
_call0:
    .cfi_startproc
    .cfi_personality 3, _nvmPersonality
    .cfi_lsda 3, .Lexception1
    .cfi_def_cfa %rsp, 8
.Lcall0Begin:
    push  %rbp
    .cfi_def_cfa %rsp, 16
    .cfi_offset %rbp, -16
    mov   %rsp, %rbp
    .cfi_def_cfa %rbp, 16
    mov   %rdi, %rax

    mov   intArgs_offset+0(%rax), %rdi         # %rdi = intArgs[0]
    mov   intArgs_offset+8(%rax), %rsi         # %rsi = intArgs[1]
    mov   intArgs_offset+16(%rax), %rdx        # %rdx = intArgs[2]
    mov   intArgs_offset+24(%rax), %rcx        # %rcx = intArgs[3]
    mov   intArgs_offset+32(%rax), %r8         # %r8 = intArgs[4]
    mov   intArgs_offset+40(%rax), %r9         # %r9 = intArgs[5]

    movsd fpArgs_offset+0(%rax), %xmm0         # %xmm0 = fpArgs[0]
    movsd fpArgs_offset+8(%rax), %xmm1         # %xmm1 = fpArgs[1]
    movsd fpArgs_offset+16(%rax), %xmm2        # %xmm2 = fpArgs[2]
    movsd fpArgs_offset+24(%rax), %xmm3        # %xmm3 = fpArgs[3]
    movsd fpArgs_offset+32(%rax), %xmm4        # %xmm4 = fpArgs[4]
    movsd fpArgs_offset+40(%rax), %xmm5        # %xmm5 = fpArgs[5]
    movsd fpArgs_offset+48(%rax), %xmm6        # %xmm6 = fpArgs[6]
    movsd fpArgs_offset+56(%rax), %xmm7        # %xmm7 = fpArgs[7]

    movl  stackArgsSize_offset(%rax), %r10d    # %r10 = stackArgsSize
.LsetStackArgsNext:
    test  %r10, %r10
    je    .LsetStackArgsDone
    dec   %r10
    mov   stackArgs_offset(%rax), %r11         # %r11 = stackArgs
    lea   (%r11, %r10, 8), %r11                # %r11 = stackArgs + %r10 * 8
    push  (%r11)
    jmp   .LsetStackArgsNext
.LsetStackArgsDone:

.Lcall0TryCatchStart:
    call  *function_offset(%rax)
.Lcall0TryCatchEnd:
.Lcall0TryCatchLandingPad:

    leave
    ret

    .size _call0, . - .Lcall0Begin
    .cfi_endproc
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
    .long    .Lcall0TryCatchStart - .Lcall0Begin       # Region start
    .long    .Lcall0TryCatchEnd - .Lcall0TryCatchStart # Region length
    .long    .Lcall0TryCatchLandingPad - .Lcall0Begin  # Landing pad
    .uleb128 1                                         # Action
    .long    .Lcall0TryCatchEnd - .Lcall0Begin         # Region start
    .long    .Lcall0End - .Lcall0TryCatchEnd           # Region length
    .long    0                       # Landing pad
    .uleb128 0                       # Action
                                       # -- Action Record Table --
                                       # Action Record
    .sleb128 -1                      #   TypeInfo index
    .sleb128 0                       #   Next action
                                       # -- Filter IDs --
    .uleb128 0
    .align    4

