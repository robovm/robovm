#include <clang/CodeGen/CodeGenAction.h>
#include <clang/Frontend/CompilerInstance.h>
#include <clang/Frontend/CompilerInvocation.h>
#include <clang/Basic/DiagnosticOptions.h>
#include <clang/Frontend/TextDiagnosticPrinter.h>
#include <llvm/ADT/IntrusiveRefCntPtr.h>
#include <llvm/IR/Module.h>
#include <llvm-c/Core.h>

#include "ClangExtra.h"

using namespace std;
using namespace llvm;
using namespace clang;

DEFINE_SIMPLE_CONVERSION_FUNCTIONS(llvm::LLVMContext, LLVMContextRef)

LLVMModuleRef ClangCompileFile(LLVMContextRef Context, char* Data, char* FileName, char* Triple, char **ErrorMessage)
{
    std::string error;
    raw_string_ostream error_os(error);

    // The compiler invocation needs a DiagnosticsEngine so it can report problems
    IntrusiveRefCntPtr<DiagnosticOptions> DiagOpts = new DiagnosticOptions();
    DiagOpts->ShowCarets = false;
    TextDiagnosticPrinter *DiagClient =
        new TextDiagnosticPrinter(error_os, &*DiagOpts);

    IntrusiveRefCntPtr<DiagnosticIDs> DiagID(new DiagnosticIDs());
    DiagnosticsEngine Diags(DiagID, &*DiagOpts, DiagClient);

    // Create the compiler invocation
    std::unique_ptr<CompilerInvocation> CI(new CompilerInvocation);
    CompilerInvocation::CreateFromArgs(*CI, 0, 0, Diags);
    CI->getPreprocessorOpts().addRemappedFile(FileName, MemoryBuffer::getMemBuffer(Data).release());
    CI->getFrontendOpts().Inputs.clear();
    CI->getFrontendOpts().Inputs.push_back(FrontendInputFile(FileName, IK_C));
    if (Triple) {
        CI->getTargetOpts().Triple = Triple;
    }

    // Create the compiler instance
    CompilerInstance Clang;
    Clang.setInvocation(CI.release());
    
    // Create the compilers actual diagnostics engine.
    Clang.createDiagnostics(DiagClient, false);
    if (!Clang.hasDiagnostics())
        return NULL;

    Clang.getDiagnosticOpts().ShowCarets = false;

    // Create and execute the frontend to generate an LLVM bitcode module.
    std::unique_ptr<CodeGenAction> Act(new EmitLLVMOnlyAction(unwrap(Context)));
    if (!Clang.ExecuteAction(*Act)) {
        *ErrorMessage = strdup(error.c_str());
        return NULL;
    }

    *ErrorMessage = strdup(error.c_str());
    
    return wrap((*Act).takeModule().release());
}
