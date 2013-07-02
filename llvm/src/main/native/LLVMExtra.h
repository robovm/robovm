#ifndef LLVM_EXTRA_H
#define LLVM_EXTRA_H

#include <llvm-c/Core.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef enum LLVMFloatABIType {
    LLVMFloatABITypeDefault,
    LLVMFloatABITypeSoft,
    LLVMFloatABITypeHard
} LLVMFloatABIType;

typedef enum LLVMFPOpFusionMode {
    LLVMFPOpFusionModeFast,
    LLVMFPOpFusionModeStandard,
    LLVMFPOpFusionModeStrict
} LLVMFPOpFusionMode;

typedef struct LLVMOpaqueTargetOptions *LLVMTargetOptionsRef;

extern const char *llvmHostTriple;

LLVMBool LLVMParseIR(LLVMMemoryBufferRef MemBuf,
                          LLVMModuleRef *OutModule, char **OutMessage);

LLVMBool LLVMParseIRInContext(LLVMContextRef ContextRef,
                                   LLVMMemoryBufferRef MemBuf,
                                   LLVMModuleRef *OutModule,
                                   char **OutMessage);

LLVMTargetRef LLVMLookupTarget(const char *Triple, char **ErrorMessage);

LLVMBool LLVMTargetMachineHasMCRelaxAll(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCRelaxAll(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineHasMCSaveTempLabels(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCSaveTempLabels(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineHasMCNoExecStack(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCNoExecStack(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineHasMCUseLoc(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCUseLoc(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineHasMCUseCFI(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCUseCFI(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineHasMCUseDwarfDirectory(LLVMTargetMachineRef T);
void LLVMTargetMachineSetMCUseDwarfDirectory(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineGetAsmVerbosityDefault(LLVMTargetMachineRef T);
void LLVMTargetMachineSetAsmVerbosityDefault(LLVMTargetMachineRef T, LLVMBool Value);
LLVMBool LLVMTargetMachineGetDataSections(LLVMTargetMachineRef T);
LLVMBool LLVMTargetMachineGetFunctionSections(LLVMTargetMachineRef T);
void LLVMTargetMachineSetDataSections(LLVMTargetMachineRef T, LLVMBool Value);
void LLVMTargetMachineSetFunctionSections(LLVMTargetMachineRef T, LLVMBool Value);

LLVMTargetOptionsRef LLVMGetTargetMachineTargetOptions(LLVMTargetMachineRef T);

LLVMBool LLVMTargetOptionsGetPrintMachineCode(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetPrintMachineCode(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetNoFramePointerElim(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetNoFramePointerElim(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetNoFramePointerElimNonLeaf(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetNoFramePointerElimNonLeaf(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetLessPreciseFPMADOption(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetLessPreciseFPMADOption(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetUnsafeFPMath(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetUnsafeFPMath(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetNoInfsFPMath(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetNoInfsFPMath(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetNoNaNsFPMath(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetNoNaNsFPMath(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetHonorSignDependentRoundingFPMathOption(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetHonorSignDependentRoundingFPMathOption(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetUseSoftFloat(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetUseSoftFloat(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetNoZerosInBSS(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetNoZerosInBSS(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetJITEmitDebugInfo(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetJITEmitDebugInfo(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetJITEmitDebugInfoToDisk(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetJITEmitDebugInfoToDisk(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetGuaranteedTailCallOpt(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetGuaranteedTailCallOpt(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetDisableTailCalls(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetDisableTailCalls(LLVMTargetOptionsRef O, LLVMBool V);
unsigned LLVMTargetOptionsGetStackAlignmentOverride(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetStackAlignmentOverride(LLVMTargetOptionsRef O, unsigned V);
LLVMBool LLVMTargetOptionsGetRealignStack(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetRealignStack(LLVMTargetOptionsRef O, LLVMBool V);
unsigned LLVMTargetOptionsGetSSPBufferSize(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetSSPBufferSize(LLVMTargetOptionsRef O, unsigned V);
LLVMBool LLVMTargetOptionsGetEnableFastISel(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetEnableFastISel(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetPositionIndependentExecutable(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetPositionIndependentExecutable(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetEnableSegmentedStacks(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetEnableSegmentedStacks(LLVMTargetOptionsRef O, LLVMBool V);
LLVMBool LLVMTargetOptionsGetUseInitArray(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetUseInitArray(LLVMTargetOptionsRef O, LLVMBool V);
LLVMFloatABIType LLVMTargetOptionsGetFloatABIType(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetFloatABIType(LLVMTargetOptionsRef O, LLVMFloatABIType V);
LLVMFPOpFusionMode LLVMTargetOptionsGetAllowFPOpFusion(LLVMTargetOptionsRef O);
void LLVMTargetOptionsSetAllowFPOpFusion(LLVMTargetOptionsRef O, LLVMFPOpFusionMode V);

void *AllocOutputStreamWrapper(JNIEnv *env, jobject OutputStream);
void FreeOutputStreamWrapper(void *p);
int LLVMTargetMachineAssembleToOutputStream(LLVMTargetMachineRef TM, LLVMMemoryBufferRef Mem, void *OutputStream, 
    LLVMBool RelaxAll, LLVMBool NoExecStack, char **ErrorMessage);
LLVMBool LLVMTargetMachineEmitToOutputStream(LLVMTargetMachineRef T, LLVMModuleRef M,
    void *OutputStream, LLVMCodeGenFileType codegen, char** ErrorMessage);
#ifdef __cplusplus
}
#endif

#endif
