#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
JAVAOUT="$BASE/src/main/java/org/robovm/llvm/binding"
COUT="$BASE/src/main/native"

function rename {
    from=$1
    to=$2
    if [ $(uname) == 'Darwin' ]; then
    	sed -i '' -e "s/[[:<:]]$from[[:>:]]/$to/g" "$JAVAOUT"/*.java
    else
    	sed -i -e "s/\b$from\b/$to/g" "$JAVAOUT"/*.java
    fi
    mv "$JAVAOUT/$from.java" "$JAVAOUT/$to.java" 
}

mkdir -p "$JAVAOUT"
mkdir -p "$COUT"
swig -includeall -I"$BASE/src/main/swig/include" -outdir "$JAVAOUT" -o "$COUT"/LLVM_wrap.c -java -package org.robovm.llvm.binding -fakeversion 2.0.4 "$BASE/src/main/swig/LLVM.i"

rename SWIGTYPE_p_LLVMOpaqueBasicBlock BasicBlockRef
rename SWIGTYPE_p_LLVMOpaqueBuilder BuilderRef
rename SWIGTYPE_p_LLVMOpaqueContext ContextRef
rename SWIGTYPE_p_LLVMOpaqueMemoryBuffer MemoryBufferRef
rename SWIGTYPE_p_LLVMOpaqueModule ModuleRef
rename SWIGTYPE_p_LLVMOpaqueModuleProvider ModuleProviderRef
rename SWIGTYPE_p_LLVMOpaquePassManager PassManagerRef
rename SWIGTYPE_p_LLVMOpaquePassManagerBuilder PassManagerBuilderRef
rename SWIGTYPE_p_LLVMOpaquePassRegistry PassRegistryRef
rename SWIGTYPE_p_LLVMOpaqueType TypeRef
rename SWIGTYPE_p_LLVMOpaqueUse UseRef
rename SWIGTYPE_p_LLVMOpaqueValue ValueRef
rename SWIGTYPE_p_LLVMOpaqueTargetData TargetDataRef
rename SWIGTYPE_p_LLVMOpaqueTargetLibraryInfotData TargetLibraryInfoRef
rename SWIGTYPE_p_LLVMOpaqueTargetMachine TargetMachineRef
rename SWIGTYPE_p_LLVMTarget TargetRef
rename SWIGTYPE_p_LLVMOpaqueTargetOptions TargetOptionsRef
