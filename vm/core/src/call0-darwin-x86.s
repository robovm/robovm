function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
CallInfo_size         = 28

    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .globl    __call0_end
    .align    4, 0x90
__call0:
    .cfi_startproc
    .cfi_personality 155, L__nvmPersonality$non_lazy_ptr
    .cfi_lsda 16, Lexception1
    .cfi_def_cfa %esp, 4
Lcall0Begin:
    pushl %ebp
    .cfi_def_cfa_offset 8
    .cfi_offset %ebp, -8
    mov   %esp, %ebp
    .cfi_def_cfa_offset 8
    .cfi_def_cfa %ebp, 8
    mov   8(%ebp), %eax         # %eax = First arg (CallInfo*)

    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
    
    shl   $2, %ecx              # %ecx equals number of bytes needed by the stack args
    and   $15, %ecx             # %ecx mod 16
    neg   %ecx
    add   $16, %ecx             # %ecx = 16 - %ecx
    sub   %ecx, %esp            # Adjust %esp
    sub   $8, %esp              # 4 bytes for %ebp pushed in the prologue and 4 bytes for return address
    
    mov   stackArgsSize_offset(%eax), %ecx # %ecx = stackArgsSize
    
LsetStackArgsNext:
    test  %ecx, %ecx
    je    LsetStackArgsDone
    dec   %ecx
    mov   stackArgs_offset(%eax), %edx     # %edx = stackArgs
    lea   (%edx, %ecx, 4), %edx  # %edx = stackArgs + %ecx * 4
    push  (%edx)
    jmp   LsetStackArgsNext
LsetStackArgsDone:

Lcall0TryCatchStart:
    call  *function_offset(%eax)
Lcall0TryCatchEnd:
Lcall0TryCatchLandingPad:

    leave
    ret
    .cfi_endproc
Lcall0End:
    # Extra ret here avoids "sectionForAddress(0x2F) address not in any section for architecture i386" linker error
    ret

    .section    __TEXT,__gcc_except_tab
    .align    2
GCC_except_table1:
Lexception1:
    .byte    255                     # @LPStart Encoding = omit
    .byte    155                     # @TType Encoding = indirect pcrel sdata4
    .byte    158                     # @TType base offset
    .space   2,128
    .space   1
    .byte    3                       # Call site Encoding = udata4
    .uleb128 26                      # Call site table length
    .long    Lcall0TryCatchStart - Lcall0Begin        # Region start
    .long    Lcall0TryCatchEnd - Lcall0TryCatchStart  # Region length
    .long    Lcall0TryCatchLandingPad - Lcall0Begin   # Landing pad
    .uleb128 1                                                # Action
    .long    Lcall0TryCatchEnd - Lcall0Begin          # Region start
    .long    Lcall0End - Lcall0TryCatchEnd            # Region length
    .long    0                       # Landing pad
    .uleb128 0                       # Action
                                       # -- Action Record Table --
                                       # Action Record
    .sleb128 -1                      #   TypeInfo index
    .sleb128 0                       #   Next action
                                       # -- Filter IDs --
    .uleb128 0
    .align   2

    .section    __IMPORT,__pointers,non_lazy_symbol_pointers
L__nvmPersonality$non_lazy_ptr:
    .indirect_symbol __nvmPersonality
    .long   0
