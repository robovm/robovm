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

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .align    4, 0x90
__call0:
Lcall0Begin:
    push  %rbp
Lcall0CFI0:
    mov   %rsp, %rbp
Lcall0CFI1:
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
LsetStackArgsNext:
    test  %r10, %r10
    je    LsetStackArgsDone
    dec   %r10
    mov   stackArgs_offset(%rax), %r11         # %r11 = stackArgs
    lea   (%r11, %r10, 8), %r11                # %r11 = stackArgs + %r10 * 8
    push  (%r11)
    jmp   LsetStackArgsNext
LsetStackArgsDone:

Lcall0TryCatchStart:
    call  *function_offset(%rax)
Lcall0TryCatchEnd:
Lcall0TryCatchLandingPad:

    leave
    ret
Lcall0End:

    .section    __TEXT,__gcc_except_tab
    .align    2
GCC_except_table1:
Lexception1:
    .byte    255                     ## @LPStart Encoding = omit
    .byte    155                     ## @TType Encoding = indirect pcrel sdata4
    .byte    158                     ## @TType base offset
    .space   2,128
    .space   1
    .byte    3                       ## Call site Encoding = udata4
    .byte    26                      ## Call site table length
    .long    Lcall0TryCatchStart - Lcall0Begin         # Region start
    .long    Lcall0TryCatchEnd - Lcall0TryCatchStart   # Region length
    .long    Lcall0TryCatchLandingPad - Lcall0Begin    # Landing pad
    .byte    1                                               # Action
    .long    Lcall0TryCatchEnd - Lcall0Begin           # Region start
    .long    Lcall0End - Lcall0TryCatchEnd             # Region length
    .long    0                       ## Landing pad
    .byte    0                       ## Action
                                        ## -- Action Record Table --
                                        ## Action Record
    .byte    127                     ##   TypeInfo index
    .byte    0                       ##   Next action
                                        ## -- Filter IDs --
    .byte    0
    .align   2

    .section    __TEXT,__eh_frame,coalesced,no_toc+strip_static_syms+live_support
EH_frame0:
Lsection_eh_frame0:
Leh_frame_common0:
    .long    Leh_frame_common_end0 - Leh_frame_common_begin0 ## Length of Common Information Entry
Leh_frame_common_begin0:
    .long    0                       ## CIE Identifier Tag
    .byte    1                       ## DW_CIE_VERSION
    .asciz   "zPLR"                  ## CIE Augmentation
    .uleb128 1                       ## CIE Code Alignment Factor
    .sleb128 -8                      ## CIE Data Alignment Factor
    .byte    16                      ## CIE Return Address Column
    .uleb128 7                       ## Augmentation Size
    .byte    155                     ## Personality Encoding = indirect pcrel sdata4
    .long    __nvmPersonality@GOTPCREL+4 ## Personality
    .byte    16                      ## LSDA Encoding = pcrel
    .byte    16                      ## FDE Encoding = pcrel
    # CFA is in %rsp+8 when entering a function
    .byte    12                      ## DW_CFA_def_cfa
    .byte    7                       ## Register
    .byte    8                       ## Offset
    # Return address is at CFA-8
    .byte    144                     ## DW_CFA_offset + Reg (16)
    .uleb128 1                       ## Offset
    .align    3
Leh_frame_common_end0:

    .globl    __call0.eh
__call0.eh:
    .long    Lcall0eh_frame_end0 - Lcall0eh_frame_begin0 ## Length of Frame Information Entry
Lcall0eh_frame_begin0:
    .long    Lcall0eh_frame_begin0 - Leh_frame_common0 ## FDE CIE offset
    .quad    Lcall0Begin - .        ## FDE initial location
    .quad    Lcall0End - Lcall0Begin   ## FDE address range
    .uleb128 8                       ## Augmentation size
    .quad    Lexception1 - .         ## Language Specific Data Area
    # Advance to Lcall0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lcall0CFI0 - Lcall0Begin
    # CFA is now in %rsp+16
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 16                      ## Offset
    # Value of %rbp is now at CFA-16
    .byte    134                     ## DW_CFA_offset + Reg (6)
    .uleb128 2                       ## Offset
    # Advance to Lcall0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lcall0CFI1 - Lcall0CFI0
    # CFA is now in %rbp+16
    .byte    13                      ## DW_CFA_def_cfa_register
    .uleb128 6                       ## Register
    .align   3
Lcall0eh_frame_end0:
