function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
CallInfo_size         = 28
proxy0_stack_size     = (CallInfo_size+12) # Stack has to be 16 byte aligned on Darwin x86

RETURN_TYPE_INT    = 0
RETURN_TYPE_LONG   = 1
RETURN_TYPE_FLOAT  = 2
RETURN_TYPE_DOUBLE = 3

    .section    __TEXT,__text,regular,pure_instructions

    .globl    __proxy0
    .align    4, 0x90
__proxy0:
Lproxy0Begin:
    subl  $proxy0_stack_size, %esp # Make room for a CallInfo struct on the stack
Lproxy0CFI0:

    movl  $0, stackArgsIndex_offset(%esp)      # stackArgsIndex = 0
    leal  proxy0_stack_size(%esp), %eax 
    addl  $4, %eax                             # Skip return address
    movl  %eax, stackArgs_offset(%esp)         # stackArgs = first stack arg    

    push  %esp
Lproxy0CFI1:
    call  __nvmProxyHandler

    addl  $4, %esp
Lproxy0CFI2:

    # For simplicity we always copy returnValue to eax and 
    # returnValue>>32 to edx even if float or double is returned.
    mov   returnValue_offset(%esp), %eax
    mov   returnValue_offset+4(%esp), %edx

    # Only float or double is returned we 
    mov   returnType_offset(%esp), %ecx
    cmp   $RETURN_TYPE_FLOAT, %ecx
    jne   LreturnTypeNotFloat
    flds  returnValue_offset(%esp)
    jmp   LreturnTypeNotDouble
LreturnTypeNotFloat:
    cmp   $RETURN_TYPE_DOUBLE, %ecx
    fldl  returnValue_offset(%esp)
    jne   LreturnTypeNotDouble
LreturnTypeNotDouble:

    addl  $proxy0_stack_size, %esp
    ret
Lproxy0End:

    .section    __TEXT,__eh_frame,coalesced,no_toc+strip_static_syms+live_support
EH_frame0:
Lsection_eh_frame0:
Leh_frame_common0:
    .long    Leh_frame_common_end0 - Leh_frame_common_begin0 ## Length of Common Information Entry
Leh_frame_common_begin0:
    .long    0                       ## CIE Identifier Tag
    .byte    1                       ## DW_CIE_VERSION
    .asciz   "zR"                    ## CIE Augmentation
    .uleb128 1                       ## CIE Code Alignment Factor
    .sleb128 -4                      ## CIE Data Alignment Factor
    .byte    8                       ## CIE Return Address Column
    .byte    1                       ## Augmentation Size
    .byte    16                      ## FDE Encoding = pcrel
    .byte    12                      ## DW_CFA_def_cfa
    .byte    5                       ## Register
    .byte    4                       ## Offset
    .byte    136                     ## DW_CFA_offset + Reg (8)
    .byte    1                       ## Offset
    .align   2
Leh_frame_common_end0:
    
    .globl    __proxy0.eh
__proxy0.eh:
    .long    Lproxy0eh_frame_end0 - Lproxy0eh_frame_begin0 ## Length of Frame Information Entry
Lproxy0eh_frame_begin0:
    .long    Lproxy0eh_frame_begin0 - Leh_frame_common0 ## FDE CIE offset
    .long    Lproxy0Begin - .        ## FDE initial location
    .long    Lproxy0End - Lproxy0Begin   ## FDE address range
    .byte    0                       ## Augmentation size
    # Advance to Lproxy0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lproxy0CFI0 - Lproxy0Begin
    # CFA is now in %esp + proxy0_stack_size + 4
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 proxy0_stack_size + 4   ## Offset
    # Advance to Lproxy0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lproxy0CFI1 - Lproxy0Begin
    # CFA is now in %esp + proxy0_stack_size + 8
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 proxy0_stack_size + 8   ## Offset
    # Advance to Lproxy0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    Lproxy0CFI2 - Lproxy0Begin
    # CFA is now in %esp + proxy0_stack_size + 4
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 proxy0_stack_size + 4   ## Offset
    .align   2
Lproxy0eh_frame_end0:
