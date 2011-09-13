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
returnAddress_offset  = 168 # void*
CallInfo_size         = 176

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
    sub   $CallInfo_size, %rsp                 # Make room for a CallInfo struct on the stack
.Lproxy0CFI0:

    mov   %rdi, intArgs_offset+0(%rsp)         # intArgs[0] = %rdi
    mov   %rsi, intArgs_offset+8(%rsp)         # intArgs[1] = %rsi
    mov   %rdx, intArgs_offset+16(%rsp)        # intArgs[2] = %rdx
    mov   %rcx, intArgs_offset+24(%rsp)        # intArgs[3] = %rcx
    mov   %r8, intArgs_offset+32(%rsp)         # intArgs[4] = %r8
    mov   %r9, intArgs_offset+40(%rsp)         # intArgs[5] = %r9

    movsd %xmm0, fpArgs_offset+0(%rsp)         # fpArgs[0] = %xmm0
    movsd %xmm1, fpArgs_offset+8(%rsp)         # fpArgs[1] = %xmm1
    movsd %xmm2, fpArgs_offset+16(%rsp)        # fpArgs[2] = %xmm2
    movsd %xmm3, fpArgs_offset+24(%rsp)        # fpArgs[3] = %xmm3
    movsd %xmm4, fpArgs_offset+32(%rsp)        # fpArgs[4] = %xmm4
    movsd %xmm5, fpArgs_offset+40(%rsp)        # fpArgs[5] = %xmm5
    movsd %xmm6, fpArgs_offset+48(%rsp)        # fpArgs[6] = %xmm6
    movsd %xmm7, fpArgs_offset+56(%rsp)        # fpArgs[7] = %xmm7

    movl  $0, intArgsIndex_offset(%rsp)        # intArgsIndex = 0
    movl  $0, fpArgsIndex_offset(%rsp)         # fpArgsIndex = 0
    movl  $0, stackArgsIndex_offset(%rsp)      # stackArgsIndex = 0

    mov   CallInfo_size(%rsp), %rax            # $rax = return address
    mov   %rax, returnAddress_offset(%rsp)

    leaq  CallInfo_size+8(%rsp), %rax          # $rax = first stack arg (+8 to skip return address)
    mov   %rax, stackArgs_offset(%rsp)         # stackArgs = first stack arg

    leaq  (%rsp), %rdi
    callq  _nvmProxyHandler@PLT

    mov   returnValue_offset(%rsp), %rax       # if return value is int or long
    movsd returnValue_offset(%rsp), %xmm0      # if return value is float or double

    addq  $CallInfo_size, %rsp
    ret

    .size _proxy0, . - .Lproxy0Begin
.Lproxy0End:
    

    .section    .eh_frame,"aw",@progbits
.LEH_frame0:
.Lsection_eh_frame0:
.Leh_frame_common0:
    .long    .Leh_frame_common_end0 - .Leh_frame_common_begin0 # Length of Common Information Entry
.Leh_frame_common_begin0:
    .long    0                       # CIE Identifier Tag
    .byte    1                       # DW_CIE_VERSION
    .asciz   "zPLR"                  # CIE Augmentation
    .uleb128 1                       # CIE Code Alignment Factor
    .sleb128 -8                      # CIE Data Alignment Factor
    .byte    16                      # CIE Return Address Column
    .uleb128 7                       # Augmentation Size
    .byte    155                     # Personality Encoding = indirect pcrel sdata4
    .long    .L_nvmPersonality.DW.stub - . # Personality
    .byte    27                      # LSDA Encoding = pcrel sdata4
    .byte    27                      # FDE Encoding = pcrel sdata4
    # CFA is in %rsp+8 when entering a function
    .byte    12                      # DW_CFA_def_cfa
    .uleb128 7                       # Register
    .uleb128 8                       # Offset
    # Return address is at CFA-8
    .byte    144                     # DW_CFA_offset + Reg (16)
    .uleb128 1                       # Offset
    .align    8
.Leh_frame_common_end0:

.Lproxy0.eh:
    .long    .Lproxy0eh_frame_end0 - .Lproxy0eh_frame_begin0 ## Length of Frame Information Entry
.Lproxy0eh_frame_begin0:
    .long    .Lproxy0eh_frame_begin0 - .Leh_frame_common0 ## FDE CIE offset
    .long    .Lproxy0Begin - .        ## FDE initial location
    .long    .Lproxy0End - .Lproxy0Begin   ## FDE address range
    .uleb128 4                       ## Augmentation size
    .long    0                       ## Language Specific Data Area
    # Advance to Lproxy0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    .Lproxy0CFI0 - .Lproxy0Begin
    # CFA is now in %rsp + CallInfo_size + 8
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 CallInfo_size + 8       ## Offset
    .align   8
.Lproxy0eh_frame_end0:


    .section    .data.rel,"aw",@progbits
.L_nvmPersonality.DW.stub:
    .quad    _nvmPersonality




