function_offset       = 0  # void*
stackArgsSize_offset  = 4  # jint
stackArgsIndex_offset = 8  # jint
stackArgs_offset      = 12 # void**
returnValue_offset    = 16 # FpIntValue
returnType_offset     = 24 # jint
returnAddress_offset  = 28 # void*
CallInfo_size         = 32
proxy0_stack_size     = (CallInfo_size+8) # Stack has to be 16 byte aligned on Darwin i386

RETURN_TYPE_INT    = 0
RETURN_TYPE_LONG   = 1
RETURN_TYPE_FLOAT  = 2
RETURN_TYPE_DOUBLE = 3

    .section    __TEXT,__text,regular,pure_instructions

    .globl    __proxy0
    .align    4, 0x90
__proxy0:
    .cfi_startproc
    .cfi_def_cfa %esp, 4
Lproxy0Begin:
    sub   $proxy0_stack_size, %esp             # Make room for a CallInfo struct on the stack
    .cfi_def_cfa_offset proxy0_stack_size + 4

    movl  $0, stackArgsIndex_offset(%esp)      # stackArgsIndex = 0

    movl  proxy0_stack_size(%esp), %eax        # %eax = return address
    movl  %eax, returnAddress_offset(%esp)

    leal  proxy0_stack_size(%esp), %eax 
    addl  $4, %eax                             # Skip return address
    movl  %eax, stackArgs_offset(%esp)         # stackArgs = first stack arg    

    push  %esp
    .cfi_def_cfa_offset proxy0_stack_size + 8

    call  __nvmProxyHandler

    addl  $4, %esp
    .cfi_def_cfa_offset proxy0_stack_size + 4

    # For simplicity we always copy returnValue to eax and 
    # returnValue>>32 to edx even if float or double is returned.
    mov   returnValue_offset(%esp), %eax
    mov   returnValue_offset+4(%esp), %edx

    # Only touch the x87 fp stack if a float or double is returned
    mov   returnType_offset(%esp), %ecx
    cmp   $RETURN_TYPE_FLOAT, %ecx
    jne   LreturnTypeNotFloat
    flds  returnValue_offset(%esp)
    jmp   LreturnTypeNotDouble
LreturnTypeNotFloat:
    cmp   $RETURN_TYPE_DOUBLE, %ecx
    jne   LreturnTypeNotDouble
    fldl  returnValue_offset(%esp)
LreturnTypeNotDouble:

    addl  $proxy0_stack_size, %esp
    ret
    .cfi_endproc
Lproxy0End:
