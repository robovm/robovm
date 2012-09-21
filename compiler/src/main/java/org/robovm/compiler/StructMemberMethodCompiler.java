/*
 * Copyright (C) 2012 RoboVM
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler;


import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import org.robovm.compiler.Bro.StructMemberPair;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.BooleanConstant;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

import soot.SootClass;
import soot.SootMethod;
import soot.VoidType;

/**
 * @author niklas
 *
 */
public class StructMemberMethodCompiler extends AbstractMethodCompiler {

    public StructMemberMethodCompiler(Config config) {
        super(config);
    }

    @Override
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        if ("_sizeOf".equals(method.getName()) || "sizeOf".equals(method.getName())) {
            structSizeOf(moduleBuilder, method);
        } else {
            structMember(moduleBuilder, method);
        }
    }
    
    private void structSizeOf(ModuleBuilder moduleBuilder, SootMethod method) {
        SootClass sootClass = method.getDeclaringClass();
        StructureType type = getStructType(sootClass);
        if (type == null) {
            throw new IllegalArgumentException("Struct class " + sootClass + " has no @StructMember annotated methods");
        }
        Function fn = FunctionBuilder.structSizeOf(method);
        moduleBuilder.addFunction(fn);
        fn.add(new Ret(sizeof(type)));
    }

    private void structMember(ModuleBuilder moduleBuilder, SootMethod method) {
        SootClass sootClass = method.getDeclaringClass();
        StructureType structType = getStructType(sootClass);
        if (structType == null) {
            throw new IllegalArgumentException("Struct class " + sootClass + " has no @StructMember annotated methods");
        }
        Function function = FunctionBuilder.structMember(method);
        moduleBuilder.addFunction(function);
        
        // Get the value of the handle field in the Struct base class and cast it to a <structType>*
        Variable handleI64 = function.newVariable(I64);
        function.add(new Load(handleI64, getFieldPtr(function, function.getParameterRef(1), offsetof(new StructureType(OBJECT, new StructureType(I64)), 1, 0), I64)));
        Variable handlePtr = function.newVariable(new PointerType(structType));
        function.add(new Inttoptr(handlePtr, handleI64.ref(), handlePtr.getType()));
        
        int offset = getStructMemberOffset(method);      
        Type memberType = structType.getTypeAt(offset);
        Variable memberPtr = function.newVariable(new PointerType(memberType));
        function.add(new Getelementptr(memberPtr, handlePtr.ref(), 0, offset));
        
        StructMemberPair pair = getStructMemberPair(sootClass, offset);
        
        if (method == pair.getGetter()) {
            
            // Marshal the return value
            if (needsMarshaler(method.getReturnType())) {
                Value result = null;
                if (isPassByValue(method)) {
                    // Return by value for Structs means that the member is a 
                    // child struct contained in the current struct and not a 
                    // pointer to a struct 
                    result = memberPtr.ref();
                } else {
                    // Pointer
                    Variable tmp = function.newVariable(memberType);
                    function.add(new Load(tmp, memberPtr.ref()));
                    result = tmp.ref();
                }
                
                // Load the declared Class of the return value
                String targetClassName = getInternalName(method.getReturnType());
                FunctionRef ldcClassFn = null;
                if (targetClassName.equals(this.className)) {
                    ldcClassFn = FunctionBuilder.ldcInternal(method.getDeclaringClass()).ref();
                } else {
                    Trampoline trampoline = new LdcClass(className, targetClassName);
                    trampolines.add(trampoline);
                    ldcClassFn = trampoline.getFunctionRef();
                }
                Value returnClass = call(function, ldcClassFn, function.getParameterRef(0));
                
                // Call the Marshaler's toObject() method
                String marshalerClassName = getMarshalerClassName(method);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "toObject", "(Ljava/lang/Class;JZ)Ljava/lang/Object;");
                trampolines.add(invokestatic);
                Variable resultI64 = function.newVariable(I64);
                function.add(new Ptrtoint(resultI64, result, I64));
                result = call(function, invokestatic.getFunctionRef(), 
                        function.getParameterRef(0), returnClass, resultI64.ref(), new IntegerConstant((byte) 0));
                function.add(new Ret(result));
            } else if (hasPointerAnnotation(method)) {
                // @Pointer long
                Variable tmp = function.newVariable(I8_PTR);
                function.add(new Load(tmp, memberPtr.ref()));
                Variable result = function.newVariable(I64);
                function.add(new Ptrtoint(result, tmp.ref(), I64));
                function.add(new Ret(result.ref()));
            } else {
                Variable result = function.newVariable(memberType);
                function.add(new Load(result, memberPtr.ref()));
                function.add(new Ret(result.ref()));
            }

        } else {
            
            Value p = function.getParameterRef(2); // 'env' is parameter 0, 'this' is at 1, the value we're interested in is at index 2
            if (needsMarshaler(method.getParameterType(0))) {

                if (isPassByValue(method, 0)) {
                    // The parameter must not be null. We assume that Structs 
                    // never have a NULL handle so we just check that the Java
                    // Object isn't null.
                    call(function, CHECK_NULL, function.getParameterRef(0), p);
                }
                
                // Call the Marshaler's toNative() method
                String marshalerClassName = getMarshalerClassName(method, 0);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "toNative", "(Ljava/lang/Object;)J");
                trampolines.add(invokestatic);
                Value ptrI64 = call(function, invokestatic.getFunctionRef(), 
                        function.getParameterRef(0), p);

                // Convert the returned i64 to i8*
                Variable ptr = function.newVariable(I8_PTR);
                function.add(new Inttoptr(ptr, ptrI64, I8_PTR));
                
                if (isPassByValue(method, 0)) {
                    // Copy the struct
                    Variable memberI8Ptr = function.newVariable(I8_PTR);
                    function.add(new Bitcast(memberI8Ptr, memberPtr.ref(), I8_PTR));
                    call(function, LLVM_MEMCPY, memberI8Ptr.ref(), ptr.ref(), 
                            sizeof((StructureType) memberType), new IntegerConstant(1), 
                            BooleanConstant.FALSE);
                } else {
                    function.add(new Store(ptr.ref(), memberPtr.ref()));                    
                }
            } else if (hasPointerAnnotation(method, 0)) {
                // @Pointer long. Convert from i64 to i8*
                Variable ptr = function.newVariable(I8_PTR);
                function.add(new Inttoptr(ptr, p, I8_PTR));
                function.add(new Store(ptr.ref(), memberPtr.ref()));                    
            } else {
                function.add(new Store(p, memberPtr.ref()));                    
            }
            
            if (method.getReturnType().equals(VoidType.v())) {
                function.add(new Ret());
            } else {
                function.add(new Ret(function.getParameterRef(1)));
            }
        }
    }
}
