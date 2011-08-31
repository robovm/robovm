    .section    __TEXT,__text,regular,pure_instructions
    .globl    __nvmProxy0
    .align    4, 0x90
__nvmProxy0:
LnvmProxy0Begin:
    sub   $120, %rsp            # Make room for a ProxyInfo struct on the stack
LnvmProxy0CFI0:

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

    leaq  120(%rsp), %rax 
    mov   %rax, 112(%rsp)      # stackArgs = first stack arg

    leaq  (%rsp), %rdi
    callq  __nvmProxyHandler

    mov   0(%rsp), %rax         # _nvmProxyHandler writes the return value here
    movsd 0(%rsp), %xmm0        # _nvmProxyHandler writes the return value here

    addq  $120, %rsp
    ret
LnvmProxy0End:
    
    .globl    __nvmCall0
    .align    4, 0x90
__nvmCall0:
LnvmCall0Begin:
    push  %rbp
LnvmCall0CFI0:
    mov   %rsp, %rbp
LnvmCall0CFI1:
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
LsetStackArgsNext:
    cmp   $0, %r10
    je    LsetStackArgsDone
    sub   $0x1, %r10
    mov   128(%rax), %r11       # %r11 = stackArgs
    lea   (%r11, %r10, 8), %r11 # %r11 = stackArgs + %r10 * 8
    push  (%r11)
    jmp   LsetStackArgsNext
LsetStackArgsDone:

LnvmCall0TryCatchStart:
    call  *(%rax)
LnvmCall0TryCatchEnd:
LnvmCall0TryCatchLandingPad:

    leave
    ret
LnvmCall0End:

    .section    __TEXT,__gcc_except_tab
    .align    2
GCC_except_table1:
Lexception1:
    .byte    255                     ## @LPStart Encoding = omit
    .byte    155                     ## @TType Encoding = indirect pcrel sdata4
    .byte    158                     ## @TType base offset
    .space    2,128
    .space    1
    .byte    3                       ## Call site Encoding = udata4
    .byte    26                      ## Call site table length
    .long    LnvmCall0TryCatchStart - LnvmCall0Begin         # Region start
    .long    LnvmCall0TryCatchEnd - LnvmCall0TryCatchStart   # Region length
    .long    LnvmCall0TryCatchLandingPad - LnvmCall0Begin    # Landing pad
    .byte    1                                               # Action
    .long    LnvmCall0TryCatchEnd - LnvmCall0Begin           # Region start
    .long    LnvmCall0End - LnvmCall0TryCatchEnd             # Region length
    .long    0                       ## Landing pad
    .byte    0                       ## Action
                                        ## -- Action Record Table --
                                        ## Action Record
    .byte    127                     ##   TypeInfo index
    .byte    0                       ##   Next action
                                        ## -- Filter IDs --
    .byte    0
    .align    2

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

    .globl    __nvmProxy0.eh
__nvmProxy0.eh:
    .long    LnvmProxy0eh_frame_end0 - LnvmProxy0eh_frame_begin0 ## Length of Frame Information Entry
LnvmProxy0eh_frame_begin0:
    .long    LnvmProxy0eh_frame_begin0 - Leh_frame_common0 ## FDE CIE offset
    .quad    LnvmProxy0Begin - .        ## FDE initial location
    .quad    LnvmProxy0End - LnvmProxy0Begin   ## FDE address range
    .uleb128 8                       ## Augmentation size
    .quad    0                       ## Language Specific Data Area
    # Advance to LnvmProxy0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    LnvmProxy0CFI0 - LnvmProxy0Begin
    # CFA is now in %rsp+128
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 128                     ## Offset
    .align   3
LnvmProxy0eh_frame_end0:

    .globl    __nvmCall0.eh
__nvmCall0.eh:
    .long    LnvmCall0eh_frame_end0 - LnvmCall0eh_frame_begin0 ## Length of Frame Information Entry
LnvmCall0eh_frame_begin0:
    .long    LnvmCall0eh_frame_begin0 - Leh_frame_common0 ## FDE CIE offset
    .quad    LnvmCall0Begin - .        ## FDE initial location
    .quad    LnvmCall0End - LnvmCall0Begin   ## FDE address range
    .uleb128 8                       ## Augmentation size
    .quad    Lexception1 - .         ## Language Specific Data Area
    # Advance to LnvmCall0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    LnvmCall0CFI0 - LnvmCall0Begin
    # CFA is now in %rsp+16
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 16                      ## Offset
    # Value of %rbp is now at CFA-16
    .byte    134                     ## DW_CFA_offset + Reg (6)
    .uleb128 2                       ## Offset
    # Advance to LnvmCall0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    LnvmCall0CFI1 - LnvmCall0CFI0
    # CFA is now in %rbp+16
    .byte    13                      ## DW_CFA_def_cfa_register
    .uleb128 6                       ## Register
    .align   3
LnvmCall0eh_frame_end0:

    .section    __TEXT,__eh_frame,coalesced,no_toc+strip_static_syms+live_support


.subsections_via_symbols
