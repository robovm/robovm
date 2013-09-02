%module LLVM
%{

#include <llvm-c/Core.h>
#include <llvm-c/BitReader.h>
#include <llvm-c/BitWriter.h>
#include <llvm-c/Transforms/IPO.h>
#include <llvm-c/Transforms/PassManagerBuilder.h>
#include <llvm-c/Transforms/Scalar.h>
#include <llvm-c/Transforms/Vectorize.h>
#include <llvm-c/Target.h>
#include <llvm-c/TargetMachine.h>
#include "../native/LLVMExtra.h"

typedef char* charp;
%}

%include "carrays.i"
%include "enums.swg"

%javaconst(1);
SWIG_JAVABODY_METHODS(protected, protected, SWIGTYPE)

%rename("%(strip:[LLVM])s") "";
//%rename("%(regex:/LLVM(Reloc|CodeModel|CodeGenLevel)?(.*)/\\2/)s", %$isenumitem) "";
//%rename LLVMRelocMode RelocModel;

%define OUT_CLASS(TYPE,NAME,CLEANUP...)
    %{
        typedef struct NAME {
            TYPE value;
        } NAME;
    %}
    typedef struct NAME {
    %immutable;
        TYPE value;
    } NAME;
    %extend NAME {
        NAME() {
          return (NAME *) calloc(1,sizeof(TYPE));
        }
        ~NAME() {
          CLEANUP;
          free(self);
        }
    };
    %types(NAME = TYPE);
%enddef

%define OUT_ARG(javatype, pattern)
    %typemap(jni) pattern "jlong"
    %typemap(jtype) pattern "long"
    %typemap(jstype) pattern "javatype"
    %typemap(javain) pattern "javatype.getCPtr($javainput)"
%enddef

%define ARRAY_CLASS(TYPE,NAME)
    %{
        typedef TYPE NAME;
    %}
    typedef struct NAME {
    } NAME;
    %extend NAME {
        NAME(int nelements) {
          return (TYPE *) calloc(nelements,sizeof(TYPE));
        }
        ~NAME() {
          free(self);
        }
        TYPE get(int index) {
          return self[index];
        }
        void set(int index, TYPE value) {
          self[index] = value;
        }
    };
    %types(NAME = TYPE);
%enddef

%define ARRAY_ARG(javatype, pattern)
    %typemap(jstype) pattern "javatype"
    %typemap(javain) pattern "javatype.getCPtr($javainput)"
%enddef

typedef char* charp;

OUT_CLASS(LLVMMemoryBufferRef, MemoryBufferRefOut)
OUT_CLASS(LLVMModuleRef, ModuleRefOut)
OUT_CLASS(LLVMModuleProviderRef, ModuleProviderRefOut)
OUT_CLASS(charp, StringOut, if (self->value) free(self->value))
OUT_CLASS(jint, IntOut)
OUT_ARG(MemoryBufferRefOut, LLVMMemoryBufferRef *OutMemBuf)
OUT_ARG(ModuleRefOut, LLVMModuleRef *OutM)
OUT_ARG(ModuleRefOut, LLVMModuleRef *OutModule)
OUT_ARG(ModuleProviderRefOut, LLVMModuleProviderRef *OutMP)
OUT_ARG(StringOut, char **OutMessage)
OUT_ARG(StringOut, char **ErrorMessage)
OUT_ARG(IntOut, unsigned *Len)

ARRAY_CLASS(LLVMTypeRef, TypeRefArray)
ARRAY_CLASS(LLVMBasicBlockRef, BasicBlockRefArray)
ARRAY_CLASS(LLVMValueRef, ValueRefArray)
ARRAY_CLASS(jlong, LongArray)
ARRAY_CLASS(jint, IntArray)
ARRAY_ARG(TypeRefArray, LLVMTypeRef *)
ARRAY_ARG(BasicBlockRefArray, LLVMBasicBlockRef *)
ARRAY_ARG(ValueRefArray, LLVMValueRef *)
ARRAY_ARG(LongArray, jlong Words[])
ARRAY_ARG(IntArray, unsigned *IdxList)

// Combine (char*, size_t) into a single parameter of type byte[]
%typemap(jtype) (char *ARRAY, size_t ARRAYSIZE) "byte[]"
%typemap(jstype) (char *ARRAY, size_t ARRAYSIZE) "byte[]"
%typemap(jni) (char *ARRAY, size_t ARRAYSIZE) "jbyteArray"
%typemap(javain) (char *ARRAY, size_t ARRAYSIZE) "$javainput"
%typemap(in, numinputs=1) (char *ARRAY, size_t ARRAYSIZE) {
  if (!$input) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, NULL);
    return $null;
  }
  $1 = JCALL2(GetByteArrayElements, jenv, $input, NULL);
  if (!$1) return $null;
  $2 = JCALL1(GetArrayLength, jenv, $input);
}
%typemap(freearg) (char *ARRAY, size_t ARRAYSIZE) {
  JCALL3(ReleaseByteArrayElements, jenv, $input, $1, 0); 
}

// Combine (char*, size_t) into a single parameter of type String
%typemap(jtype) (char *STRING, size_t STRINGSIZE) "String"
%typemap(jstype) (char *STRING, size_t STRINGSIZE) "String"
%typemap(jni) (char *STRING, size_t STRINGSIZE) "jstring"
%typemap(javain) (char *STRING, size_t STRINGSIZE) "$javainput"
%typemap(in, numinputs=1) (char *STRING, size_t STRINGSIZE) {
  if (!$input) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, NULL);
    return $null;
  }
  $1 = ($1_ltype) JCALL2(GetStringUTFChars, jenv, $input, NULL);
  if (!$1) return $null;
  $2 = strlen($1);
}
%typemap(freearg) (char *STRING, size_t STRINGSIZE) {
  JCALL2(ReleaseStringUTFChars, jenv, $input, $1); 
}

%typemap(jtype) void *OutputStream "java.io.OutputStream"
%typemap(jstype) void *OutputStream "java.io.OutputStream"
%typemap(jni) void *OutputStream "jobject"
%typemap(javain) void *OutputStream "$javainput"
%typemap(in) void *OutputStream {
  if (!$input) {
    SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, NULL);
    return $null;
  }
  $1 = ($1_ltype) AllocOutputStreamWrapper(jenv, $input);
  if (!$1) return $null;
}
%typemap(freearg) void *OutputStream {
  FreeOutputStreamWrapper($1);
}

%apply (char *ARRAY, size_t ARRAYSIZE) {(const char *InputData, size_t InputDataLength)};
%apply (char *STRING, size_t STRINGSIZE) {(const char *Name, unsigned SLen)};
%apply (char *STRING, size_t STRINGSIZE) {(const char *Str, unsigned Length)};
%apply (char *STRING, size_t STRINGSIZE) {(const char *Str, unsigned SLen)};

%typemap(jstype) unsigned "int"
%typemap(jtype) unsigned "int"
%typemap(jni) unsigned "jint"

// Insert equals() and hashCode() based on swigCPtr.
%typemap(javacode) SWIGTYPE, SWIGTYPE *, SWIGTYPE &, SWIGTYPE [], SWIGTYPE (CLASS::*) %{
  public int hashCode() {
    return 31 + (int) (swigCPtr ^ (swigCPtr >>> 32));
  }

  public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    $javaclassname other = ($javaclassname) obj;
    return swigCPtr == other.swigCPtr;
  }
%}

typedef jboolean LLVMBool;
typedef jbyte uint8_t;
typedef jlong uint64_t;

// Deprecated functions
%ignore LLVMGetBitcodeModuleProviderInContext;
%ignore LLVMGetBitcodeModuleProvider;
%ignore LLVMWriteBitcodeToFileHandle;

// Take String and size but String already has size so these don't make sense.
// There are versions that only take a String.
%ignore LLVMConstIntOfStringAndSize;
%ignore LLVMConstRealOfStringAndSize;

// This is inlined and based on macros and will only work as expected when
// called on the same platform as the llvm/Config/llvm-config.h file was 
// generated for.
%ignore LLVMInitializeNativeTarget;

%ignore AllocOutputStreamWrapper;
%ignore FreeOutputStreamWrapper;

// Prevent arguments named ContextRef to interfere with the type named ContextRef
#define ContextRef contextRef

%immutable llvmHostTriple;

// These return char* which the caller must free.
%newobject LLVMGetTargetMachineTriple;
%newobject LLVMGetTargetMachineCPU;
%newobject LLVMGetTargetMachineFeatureString;
%newobject LLVMCopyStringRepOfTargetData;

%include "llvm-c/Core.h"
%include "llvm-c/BitReader.h"
%include "llvm-c/BitWriter.h"
%include "llvm-c/Transforms/IPO.h"
%include "llvm-c/Transforms/PassManagerBuilder.h"
%include "llvm-c/Transforms/Scalar.h"
%include "llvm-c/Transforms/Vectorize.h"
%include "llvm-c/Target.h"
%include "llvm-c/TargetMachine.h"
%include "../native/LLVMExtra.h"

%pragma(java) jniclasscode=%{
  static {
    org.robovm.llvm.NativeLibrary.load();
  }
%}
