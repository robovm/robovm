#include <llvm-c/Core.h>
#include <llvm-c/TargetMachine.h>
#include <llvm/IRReader/IRReader.h>
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>
#include <llvm/IR/DataLayout.h>
#include <llvm/MC/MCAsmBackend.h>
#include <llvm/MC/MCAsmInfo.h>
#include <llvm/MC/MCContext.h>
#include <llvm/MC/MCInstPrinter.h>
#include <llvm/MC/MCInstrInfo.h>
#include <llvm/MC/MCObjectFileInfo.h>
#include <llvm/MC/MCParser/AsmLexer.h>
#include <llvm/MC/MCRegisterInfo.h>
#include <llvm/MC/MCSectionMachO.h>
#include <llvm/MC/MCStreamer.h>
#include <llvm/MC/MCSubtargetInfo.h>
#include <llvm/MC/MCTargetAsmParser.h>
#include <llvm/PassManager.h>
#include <llvm/Target/TargetMachine.h>
#include <llvm/Target/TargetOptions.h>
#include <llvm/Support/FormattedStream.h>
#include <llvm/Support/TargetRegistry.h>
#include <llvm/Support/MemoryBuffer.h>
#include <llvm/Support/SourceMgr.h>
#include <llvm/Support/raw_ostream.h>
#include <llvm/Support/ToolOutputFile.h>
#include <cstring>
#include <string>
#include <stdio.h>
#include <locale.h>
#ifdef __APPLE__
#include <xlocale.h>
#endif

#include <jni.h>

#include "LLVMExtra.h"

using namespace llvm;

DEFINE_SIMPLE_CONVERSION_FUNCTIONS(Target, LLVMTargetRef)
DEFINE_SIMPLE_CONVERSION_FUNCTIONS(TargetMachine, LLVMTargetMachineRef)
DEFINE_SIMPLE_CONVERSION_FUNCTIONS(TargetOptions, LLVMTargetOptionsRef)

const char *llvmHostTriple = LLVM_HOST_TRIPLE;

class raw_java_ostream : public raw_ostream {
  JNIEnv *m_env;
  jobject m_target;
  uint64_t m_pos;

  virtual void write_impl(const char *Ptr, size_t Size) LLVM_OVERRIDE {
    JNIEnv *env = this->m_env;
    if (env->ExceptionCheck()) return;

    // The method id will not change during the time this library is
    // loaded, so it can be cached.
    static jmethodID mid = 0;
    if (mid == 0) {
      jclass clazz = env->FindClass("java/io/OutputStream");
      if (env->ExceptionCheck())
        return;

      mid = env->GetMethodID(clazz, "write", "([B)V");
      if (env->ExceptionCheck() || mid == 0)
        return;

      env->DeleteLocalRef(clazz);
    }

    // convert the data to a Java byte array
    jbyteArray data = env->NewByteArray((jsize) Size);
    if (env->ExceptionCheck())
      return;

    env->SetByteArrayRegion(data, 0, (jsize) Size, (const jbyte *) Ptr);
    if (env->ExceptionCheck())
      return;

    // write the data
    env->CallObjectMethod(this->m_target, mid, data);
    if (env->ExceptionCheck())
      return;

    env->DeleteLocalRef(data);

    m_pos += Size;
  }
  virtual uint64_t current_pos() const LLVM_OVERRIDE { return m_pos; }
public:
  explicit raw_java_ostream(JNIEnv *env, jobject target) : m_env(env), m_target(target), m_pos(0) {}
  ~raw_java_ostream() {}
};

void *AllocOutputStreamWrapper(JNIEnv *env, jobject jOutputStream) {
  return new raw_java_ostream(env, jOutputStream);
}
void FreeOutputStreamWrapper(void *p) {
  delete (raw_java_ostream*) p;
}

LLVMBool LLVMParseIR(LLVMMemoryBufferRef MemBuf,
                          LLVMModuleRef *OutModule, char **OutMessage) {
  return LLVMParseIRInContext(wrap(&getGlobalContext()), MemBuf, OutModule, OutMessage);
}

LLVMBool LLVMParseIRInContext(LLVMContextRef ContextRef,
                                   LLVMMemoryBufferRef MemBuf,
                                   LLVMModuleRef *OutModule,
                                   char **OutMessage) {

  SMDiagnostic Err;
  *OutModule = wrap(ParseIR(unwrap(MemBuf), Err, *unwrap(ContextRef)));
  if (!*OutModule) {
    if (OutMessage) {
      std::string s("");
      raw_string_ostream os(s);
      Err.print(NULL, os, false);
      *OutMessage = strdup(os.str().data());
    }
    return 1;
  }

  return 0;
}

LLVMTargetRef LLVMLookupTarget(const char *Triple, char **ErrorMessage) {
  std::string Error;
  const Target *TheTarget = TargetRegistry::lookupTarget(std::string(Triple), Error);
  if (!TheTarget) {
    *ErrorMessage = strdup(Error.c_str());
    return NULL;
  }
  return wrap(TheTarget);
}

LLVMBool LLVMTargetMachineHasMCRelaxAll(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCRelaxAll();
}

void LLVMTargetMachineSetMCRelaxAll(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCRelaxAll(Value != 0);
}

LLVMBool LLVMTargetMachineHasMCSaveTempLabels(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCSaveTempLabels();
}

void LLVMTargetMachineSetMCSaveTempLabels(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCSaveTempLabels(Value != 0);
}

LLVMBool LLVMTargetMachineHasMCNoExecStack(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCNoExecStack();
}

void LLVMTargetMachineSetMCNoExecStack(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCNoExecStack(Value != 0);
}

LLVMBool LLVMTargetMachineHasMCUseLoc(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCUseLoc();
}

void LLVMTargetMachineSetMCUseLoc(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCUseLoc(Value != 0);
}

LLVMBool LLVMTargetMachineHasMCUseCFI(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCUseCFI();
}

void LLVMTargetMachineSetMCUseCFI(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCUseCFI(Value != 0);
}

LLVMBool LLVMTargetMachineHasMCUseDwarfDirectory(LLVMTargetMachineRef T) {
    return unwrap(T)->hasMCUseDwarfDirectory();
}

void LLVMTargetMachineSetMCUseDwarfDirectory(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setMCUseDwarfDirectory(Value != 0);
}

//Reloc::Model getRelocationModel() const;
//CodeModel::Model getCodeModel() const;
//CodeGenOpt::Level getOptLevel() const;

LLVMBool LLVMTargetMachineGetAsmVerbosityDefault(LLVMTargetMachineRef T) {
    return unwrap(T)->getAsmVerbosityDefault();
}

void LLVMTargetMachineSetAsmVerbosityDefault(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setAsmVerbosityDefault(Value != 0);
}

LLVMBool LLVMTargetMachineGetDataSections(LLVMTargetMachineRef T) {
    return unwrap(T)->getDataSections();
}

LLVMBool LLVMTargetMachineGetFunctionSections(LLVMTargetMachineRef T) {
    return unwrap(T)->getFunctionSections();
}

void LLVMTargetMachineSetDataSections(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setDataSections(Value != 0);
}

void LLVMTargetMachineSetFunctionSections(LLVMTargetMachineRef T, LLVMBool Value) {
    unwrap(T)->setFunctionSections(Value != 0);
}

LLVMTargetOptionsRef LLVMGetTargetMachineTargetOptions(LLVMTargetMachineRef T) {
    TargetMachine *TM = unwrap(T);
    return wrap(&(TM->Options));
}

LLVMBool LLVMTargetOptionsGetPrintMachineCode(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->PrintMachineCode; }
void LLVMTargetOptionsSetPrintMachineCode(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->PrintMachineCode = V; }

LLVMBool LLVMTargetOptionsGetNoFramePointerElim(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->NoFramePointerElim; }
void LLVMTargetOptionsSetNoFramePointerElim(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->NoFramePointerElim = V; }

LLVMBool LLVMTargetOptionsGetNoFramePointerElimNonLeaf(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->NoFramePointerElimNonLeaf; }
void LLVMTargetOptionsSetNoFramePointerElimNonLeaf(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->NoFramePointerElimNonLeaf = V; }

LLVMBool LLVMTargetOptionsGetLessPreciseFPMADOption(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->LessPreciseFPMADOption; }
void LLVMTargetOptionsSetLessPreciseFPMADOption(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->LessPreciseFPMADOption = V; }

LLVMBool LLVMTargetOptionsGetUnsafeFPMath(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->UnsafeFPMath; }
void LLVMTargetOptionsSetUnsafeFPMath(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->UnsafeFPMath = V; }

LLVMBool LLVMTargetOptionsGetNoInfsFPMath(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->NoInfsFPMath; }
void LLVMTargetOptionsSetNoInfsFPMath(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->NoInfsFPMath = V; }

LLVMBool LLVMTargetOptionsGetNoNaNsFPMath(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->NoNaNsFPMath; }
void LLVMTargetOptionsSetNoNaNsFPMath(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->NoNaNsFPMath = V; }

LLVMBool LLVMTargetOptionsGetHonorSignDependentRoundingFPMathOption(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->HonorSignDependentRoundingFPMathOption; }
void LLVMTargetOptionsSetHonorSignDependentRoundingFPMathOption(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->HonorSignDependentRoundingFPMathOption = V; }

LLVMBool LLVMTargetOptionsGetUseSoftFloat(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->UseSoftFloat; }
void LLVMTargetOptionsSetUseSoftFloat(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->UseSoftFloat = V; }

LLVMBool LLVMTargetOptionsGetNoZerosInBSS(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->NoZerosInBSS; }
void LLVMTargetOptionsSetNoZerosInBSS(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->NoZerosInBSS = V; }

LLVMBool LLVMTargetOptionsGetJITEmitDebugInfo(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->JITEmitDebugInfo; }
void LLVMTargetOptionsSetJITEmitDebugInfo(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->JITEmitDebugInfo = V; }

LLVMBool LLVMTargetOptionsGetJITEmitDebugInfoToDisk(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->JITEmitDebugInfoToDisk; }
void LLVMTargetOptionsSetJITEmitDebugInfoToDisk(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->JITEmitDebugInfoToDisk = V; }

LLVMBool LLVMTargetOptionsGetGuaranteedTailCallOpt(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->GuaranteedTailCallOpt; }
void LLVMTargetOptionsSetGuaranteedTailCallOpt(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->GuaranteedTailCallOpt = V; }

LLVMBool LLVMTargetOptionsGetDisableTailCalls(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->DisableTailCalls; }
void LLVMTargetOptionsSetDisableTailCalls(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->DisableTailCalls = V; }

unsigned LLVMTargetOptionsGetStackAlignmentOverride(LLVMTargetOptionsRef O) { return (unsigned) unwrap(O)->StackAlignmentOverride; }
void LLVMTargetOptionsSetStackAlignmentOverride(LLVMTargetOptionsRef O, unsigned V) { unwrap(O)->StackAlignmentOverride = V; }

LLVMBool LLVMTargetOptionsGetRealignStack(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->RealignStack; }
void LLVMTargetOptionsSetRealignStack(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->RealignStack = V; }

unsigned LLVMTargetOptionsGetSSPBufferSize(LLVMTargetOptionsRef O) { return (unsigned) unwrap(O)->SSPBufferSize; }
void LLVMTargetOptionsSetSSPBufferSize(LLVMTargetOptionsRef O, unsigned V) { unwrap(O)->SSPBufferSize = V; }

LLVMBool LLVMTargetOptionsGetEnableFastISel(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->EnableFastISel; }
void LLVMTargetOptionsSetEnableFastISel(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->EnableFastISel = V; }

LLVMBool LLVMTargetOptionsGetPositionIndependentExecutable(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->PositionIndependentExecutable; }
void LLVMTargetOptionsSetPositionIndependentExecutable(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->PositionIndependentExecutable = V; }

LLVMBool LLVMTargetOptionsGetEnableSegmentedStacks(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->EnableSegmentedStacks; }
void LLVMTargetOptionsSetEnableSegmentedStacks(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->EnableSegmentedStacks = V; }

LLVMBool LLVMTargetOptionsGetUseInitArray(LLVMTargetOptionsRef O) { return (LLVMBool) unwrap(O)->UseInitArray; }
void LLVMTargetOptionsSetUseInitArray(LLVMTargetOptionsRef O, LLVMBool V) { unwrap(O)->UseInitArray = V; }

LLVMFloatABIType LLVMTargetOptionsGetFloatABIType(LLVMTargetOptionsRef O) { return (LLVMFloatABIType) unwrap(O)->FloatABIType; }
void LLVMTargetOptionsSetFloatABIType(LLVMTargetOptionsRef O, LLVMFloatABIType V) { unwrap(O)->FloatABIType = (FloatABI::ABIType) V; }

LLVMFPOpFusionMode LLVMTargetOptionsGetAllowFPOpFusion(LLVMTargetOptionsRef O) { return (LLVMFPOpFusionMode) unwrap(O)->AllowFPOpFusion; }
void LLVMTargetOptionsSetAllowFPOpFusion(LLVMTargetOptionsRef O, LLVMFPOpFusionMode V) { unwrap(O)->AllowFPOpFusion = (FPOpFusion::FPOpFusionMode) V; }

static void assembleDiagHandler(const SMDiagnostic &Diag, void *Context) {
  raw_string_ostream *OS = (raw_string_ostream*) Context;
  Diag.print(0, *OS, false);
}

int LLVMTargetMachineAssembleToOutputStream(LLVMTargetMachineRef TM, LLVMMemoryBufferRef Mem, void *JOStream, LLVMBool RelaxAll, LLVMBool NoExecStack, char **ErrorMessage) {
  *ErrorMessage = NULL;

  locale_t loc = newlocale(LC_ALL_MASK, "C", 0);
  locale_t oldLoc = uselocale(loc);

  TargetMachine *TheTargetMachine = unwrap(TM);
  const Target *TheTarget = &(TheTargetMachine->getTarget());

  std::string TripleName = TheTargetMachine->getTargetTriple().str();
  std::string MCPU = TheTargetMachine->getTargetCPU().str();
  std::string FeaturesStr = TheTargetMachine->getTargetFeatureString().str();
  Reloc::Model RelocModel = TheTargetMachine->getRelocationModel();
  CodeModel::Model CMModel = TheTargetMachine->getCodeModel();

  MemoryBuffer *Buffer = unwrap(Mem);

  std::string DiagStr;
  raw_string_ostream DiagStream(DiagStr);
  SourceMgr SrcMgr;
  SrcMgr.setDiagHandler(assembleDiagHandler, &DiagStream);

  // Tell SrcMgr about this buffer, which is what the parser will pick up.
  SrcMgr.AddNewSourceBuffer(Buffer, SMLoc());

  // Record the location of the include directories so that the lexer can find
  // it later.
//  SrcMgr.setIncludeDirs(IncludeDirs);

  OwningPtr<MCAsmInfo> MAI(TheTarget->createMCAsmInfo(TripleName));
  OwningPtr<MCRegisterInfo> MRI(TheTarget->createMCRegInfo(TripleName));
  OwningPtr<MCObjectFileInfo> MOFI(new MCObjectFileInfo());
  MCContext Ctx(*MAI, *MRI, MOFI.get(), &SrcMgr);
  MOFI->InitMCObjectFileInfo(TripleName, RelocModel, CMModel, Ctx);

  OwningPtr<MCInstrInfo> MCII(TheTarget->createMCInstrInfo());
  OwningPtr<MCSubtargetInfo> STI(TheTarget->createMCSubtargetInfo(TripleName, MCPU, FeaturesStr));

  raw_java_ostream& Out = *((raw_java_ostream*) JOStream);

  OwningPtr<MCStreamer> Str;
  MCCodeEmitter *CE = TheTarget->createMCCodeEmitter(*MCII, *MRI, *STI, Ctx);
  MCAsmBackend *MAB = TheTarget->createMCAsmBackend(TripleName, MCPU);
  Str.reset(TheTarget->createMCObjectStreamer(TripleName, Ctx, *MAB,
                                              Out, CE, RelaxAll != 0,
                                              NoExecStack != 0));


  OwningPtr<MCAsmParser> Parser(createMCAsmParser(SrcMgr, Ctx, *Str, *MAI));
  OwningPtr<MCTargetAsmParser> TAP(TheTarget->createMCAsmParser(*STI, *Parser));
  if (!TAP) {
    *ErrorMessage = strdup("this target does not support assembly parsing");
    goto done;
  }

  Parser->setTargetParser(*TAP.get());

  if (Parser->Run(false)) {
    *ErrorMessage = strdup(DiagStream.str().c_str());
    goto done;
  }
  Out.flush();

done:
  uselocale(oldLoc);
  freelocale(loc);
  return *ErrorMessage ? 1 : 0;
}

static LLVMBool LLVMTargetMachineEmit(LLVMTargetMachineRef T, LLVMModuleRef M,
  formatted_raw_ostream &OS, LLVMCodeGenFileType codegen, char **ErrorMessage) {
  TargetMachine* TM = unwrap(T);
  Module* Mod = unwrap(M);

  PassManager pass;

  std::string error;

  const DataLayout* td = TM->getDataLayout();

  if (!td) {
    error = "No DataLayout in TargetMachine";
    *ErrorMessage = strdup(error.c_str());
    return true;
  }
  pass.add(new DataLayout(*td));

  TargetMachine::CodeGenFileType ft;
  switch (codegen) {
    case LLVMAssemblyFile:
      ft = TargetMachine::CGFT_AssemblyFile;
      break;
    default:
      ft = TargetMachine::CGFT_ObjectFile;
      break;
  }
  if (TM->addPassesToEmitFile(pass, OS, ft)) {
    error = "TargetMachine can't emit a file of this type";
    *ErrorMessage = strdup(error.c_str());
    return true;
  }

  pass.run(*Mod);

  OS.flush();
  return false;
}

LLVMBool LLVMTargetMachineEmitToOutputStream(LLVMTargetMachineRef T, LLVMModuleRef M,
  void *JOStream, LLVMCodeGenFileType codegen, char** ErrorMessage) {

  locale_t loc = newlocale(LC_ALL_MASK, "C", 0);
  locale_t oldLoc = uselocale(loc);

  formatted_raw_ostream Out(*((raw_java_ostream*) JOStream));
  bool Result = LLVMTargetMachineEmit(T, M, Out, codegen, ErrorMessage);
  Out.flush();

  uselocale(oldLoc);
  freelocale(loc);

  return Result;
}
