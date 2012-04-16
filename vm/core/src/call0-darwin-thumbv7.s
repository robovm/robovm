function_offset       = 0  @ void*
regArgsIndex_offset   = 4  @ jint
regArgs_offset        = 8  @ jint[MAX_REG_ARGS], MAX_REG_ARGS = 4
stackArgsSize_offset  = 24 @ jint
stackArgsIndex_offset = 28 @ jint
stackArgs_offset      = 32 @ void**
returnValue_offset    = 36 @ FpIntValue
returnType_offset     = 44 @ jint
CallInfo_size         = 48

    .syntax unified
    .section    __TEXT,__text,regular,pure_instructions
    
    .globl    __call0
    .align    2
    .code     16
    .thumb_func __call0
__call0:
    push {r4, r5, r7, lr}

    @ Save frame pointer
    add r7, sp, #8

    @ Save CallInfo* to r4
    mov r4, r0  @ r4 = (CallInfo*) r0

    @ Make room for the _Unwind_FunctionContext struct on the stack
    sub sp, sp, #44

    @ _Unwind_FunctionContext->resumeLocation = 0 (Call site #1)
    mov r0, #0
    str r0, [sp, #4]

    @ Set _Unwind_FunctionContext->lsda to NULL
    mov r0, #0
    str r0, [sp, #28]

    @ Save personality function to _Unwind_FunctionContext->personality
    movw r0, :lower16:(L__nvmPersonality$non_lazy_ptr-(LPC1+4))
    movt r0, :upper16:(L__nvmPersonality$non_lazy_ptr-(LPC1+4))
LPC1:
    add r0, pc
    ldr r0, [r0]
    str r0, [sp, #24]

    @ Save r7 to _Unwind_FunctionContext->jbuf[0]
    str r7, [sp, #32]

    @ Save address of landingpad to _Unwind_FunctionContext->jbuf[1]
    movw r0, :lower16:(Lcall0TryCatchLandingPad-(LPC2+4))
    movt r0, :upper16:(Lcall0TryCatchLandingPad-(LPC2+4))
LPC2:
    add r0, pc
    orr r0, r0, #1    @ Make sure LSB is set to trigger thumb mode when unwind_phase2 jumps to the landingpad
    str r0, [sp, #36]

    @ Save sp to _Unwind_FunctionContext->jbuf[2]
    str sp, [sp, #40]

    mov r0, sp
    blx __Unwind_SjLj_Register

    ldr r0, [r4, #stackArgsSize_offset] @ r0 = ((CallInfo*) r4)->stackArgsSize
    ldr r1, [r4, #stackArgs_offset]     @ r1 = ((CallInfo*) r4)->stackArgs
    add r1, r1, r0, lsl #2               @ r1 = r1 + (r0 << 2)

    teq r0, #0
    beq LsetStackArgsDone
LsetStackArgsNext:
    sub r1, r1, #4
    ldr r2, [r1]
    sub sp, sp, #4
    str r2, [sp]
    subs r0, r0, #1
    bne LsetStackArgsNext
LsetStackArgsDone:

    ldr r0, [r4, #regArgs_offset]    @ r0 = ((CallInfo*) r4)->regArgs[0]
    ldr r1, [r4, #regArgs_offset+4]  @ r1 = ((CallInfo*) r4)->regArgs[1]
    ldr r2, [r4, #regArgs_offset+8]  @ r2 = ((CallInfo*) r4)->regArgs[2]
    ldr r3, [r4, #regArgs_offset+12] @ r3 = ((CallInfo*) r4)->regArgs[3]

    ldr r9, [r4, #function_offset]   @ r9 = ((CallInfo*) r4)->function
    blx  r9

    @ Save return value in r4:r5
    mov r4, r0
    mov r5, r1

Lcall0TryCatchLandingPad:

    @ Restore sp to what it was before we pushed the stack args
    subs r9, r7, #8
    sub r9, r9, #44
    mov sp, r9

    mov r0, sp
    blx __Unwind_SjLj_Unregister

    add sp, sp, #44

    @ Return value
    mov r0, r4
    mov r1, r5

    pop {r4, r5, r7, pc}

    .section    __DATA,__nl_symbol_ptr,non_lazy_symbol_pointers
    .align  2
L__nvmPersonality$non_lazy_ptr:
    .indirect_symbol    __nvmPersonality
    .long   0
