stackArgsIndex_offset = 8
stackArgs_offset = 12
returnValue_offset = 16
returnType_offset = 24
CallInfo_size = 28

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
    subl  $CallInfo_size, %esp # Make room for a CallInfo struct on the stack
.Lproxy0CFI0:

    movl  $0, stackArgsIndex_offset(%esp)      # stackArgsIndex = 0
    leal  CallInfo_size(%esp), %eax 
    addl  $4, %eax                             # Skip return address
    movl  %eax, stackArgs_offset(%esp)         # stackArgs = first stack arg

    pushl %esp
.Lproxy0CFI1:
    calll  _nvmProxyHandler

    addl  $4, %esp
.Lproxy0CFI2:

    # For simplicity we always copy returnValue to eax and 
    # returnValue>>32 to edx even if float or double is returned.
    mov   returnValue_offset(%esp), %eax
    mov   returnValue_offset+4(%esp), %edx

    # Only float or double is returned we 
    mov   returnType_offset(%esp), %ecx
    cmp   $RETURN_TYPE_FLOAT, %ecx
    jne   .LreturnTypeNotFloat
    flds  returnValue_offset(%esp)
    jmp   .LreturnTypeNotDouble
.LreturnTypeNotFloat:
    cmp   $RETURN_TYPE_DOUBLE, %ecx
    fldl  returnValue_offset(%esp)
    jne   .LreturnTypeNotDouble
.LreturnTypeNotDouble:

    addl  $CallInfo_size, %esp
    ret

    .size _proxy0, . - .Lproxy0Begin
.Lproxy0End:

    .section    .eh_frame,"aw",@progbits
.LEH_frame0:
.Lsection_eh_frame0:
.Leh_frame_common0:
    .long    .Leh_frame_common_end0-.Leh_frame_common_begin0 # Length of Common Information Entry
.Leh_frame_common_begin0:
    .long    0                       # CIE Identifier Tag
    .byte    1                       # DW_CIE_VERSION
    .asciz   "zPL"                   # CIE Augmentation
    .uleb128 1                       # CIE Code Alignment Factor
    .sleb128 -4                      # CIE Data Alignment Factor
    .byte    8                       # CIE Return Address Column
    .uleb128 6                       # Augmentation Size
    .byte    0                       # Personality Encoding = absptr
    .long    _nvmPersonality         # Personality
    .byte    0                       # LSDA Encoding = absptr
    # CFA is in %esp+4 when entering a function
    .byte    12                      # DW_CFA_def_cfa
    .byte    4                       # Register
    .byte    4                       # Offset
    # Return address is at CFA-4
    .byte    136                     # DW_CFA_offset + Reg (8)
    .uleb128 1                       # Offset
    .align    4
.Leh_frame_common_end0:
    
.Lproxy0.eh:
    .long    .Lproxy0eh_frame_end0 - .Lproxy0eh_frame_begin0 ## Length of Frame Information Entry
.Lproxy0eh_frame_begin0:
    .long    .Lproxy0eh_frame_begin0 - .Leh_frame_common0 ## FDE CIE offset
    .long    .Lproxy0Begin            ## FDE initial location
    .long    .Lproxy0End - .Lproxy0Begin   ## FDE address range
    .uleb128 4                       ## Augmentation size
    .quad    0                       ## Language Specific Data Area
    # Advance to Lproxy0CFI0
    .byte    4                       ## DW_CFA_advance_loc4
    .long    .Lproxy0CFI0 - .Lproxy0Begin
    # CFA is now in %esp + CallInfo_size + 4
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 CallInfo_size + 4       ## Offset
    # Advance to Lproxy0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    .Lproxy0CFI1 - .Lproxy0Begin
    # CFA is now in %esp + CallInfo_size + 8
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 CallInfo_size + 8       ## Offset
    # Advance to Lproxy0CFI1
    .byte    4                       ## DW_CFA_advance_loc4
    .long    .Lproxy0CFI2 - .Lproxy0Begin
    # CFA is now in %esp + CallInfo_size + 4
    .byte    14                      ## DW_CFA_def_cfa_offset
    .uleb128 CallInfo_size + 4       ## Offset
    .align   4
.Lproxy0eh_frame_end0:

