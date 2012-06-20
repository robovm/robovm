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

import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.HashMap;
import java.util.Map;

import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Invokespecial;
import org.robovm.compiler.trampoline.New;
import org.robovm.compiler.trampoline.Trampoline;

import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;

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

    private void validateStructMember(SootMethod method) {
        if (!method.isNative() && !method.isStatic()) {
            throw new IllegalArgumentException("@StructMember annotated method must be native and not static");
        }
        if (!method.getName().startsWith("get") && !method.getName().startsWith("set") || method.getName().length() == 3) {
            throw new IllegalArgumentException("@StructMember annotated method has invalid name");
        }
        if (method.getName().startsWith("get") && method.getParameterCount() != 0) {
            throw new IllegalArgumentException("@StructMember annotated getter method must have no arguments");
        }
        if (method.getName().startsWith("set") && method.getParameterCount() != 1) {
            throw new IllegalArgumentException("@StructMember annotated setter method must take a single argument");
        }
        boolean getter = method.getName().startsWith("get");
        soot.Type t = getter ? method.getReturnType() : method.getParameterType(0);
        if (!(t instanceof PrimType || t instanceof RefType && isStruct(t))) {
            if (getter) {
                throw new IllegalArgumentException("@StructMember annotated getter method must return primitive or Struct type");
            } else {
                throw new IllegalArgumentException("@StructMember annotated setter method must take a single primitive or Struct type argument");
            }
        }
    }

    private void structMember(ModuleBuilder moduleBuilder, SootMethod method) {
        validateStructMember(method);
        
        SootClass sootClass = method.getDeclaringClass();
        Map<String, Integer> indexes = new HashMap<String, Integer>();
        StructureType structType = getStructType(sootClass, indexes);
        if (structType == null) {
            throw new IllegalArgumentException("Struct class " + sootClass + " has not @StructMember annotated methods");
        }
        Function function = FunctionBuilder.structMember(method);
        moduleBuilder.addFunction(function);
        
        // Get the value of the handle field in the Struct base class and cast it to a <structType>*
        Variable handleI64 = function.newVariable(I64);
        function.add(new Load(handleI64, getFieldPtr(function, function.getParameterRef(1), offsetof(new StructureType(OBJECT, new StructureType(I64)), 1, 0), I64)));
        Variable handlePtr = function.newVariable(new PointerType(structType));
        function.add(new Inttoptr(handlePtr, handleI64.ref(), handlePtr.getType()));
        
        String name = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
        int index = indexes.get(name);        
        Type memberType = structType.getTypeAt(index);
        Variable memberPtr = function.newVariable(new PointerType(memberType));
        function.add(new Getelementptr(memberPtr, handlePtr.ref(), 0, index));
        
        if (method.getName().startsWith("get")) {
            if (memberType instanceof StructureType) {
                Value result = newStruct(function, getInternalName(method.getReturnType()), memberPtr.ref());
                function.add(new Ret(result));
            } else {
                Variable result = function.newVariable(memberType);
                function.add(new Load(result, memberPtr.ref()));
                if (memberType == I8_PTR) {
                    if (method.getReturnType().equals(LongType.v())) {
                        Variable resultI64 = function.newVariable(I64);
                        function.add(new Ptrtoint(resultI64, result.ref(), I64));
                        function.add(new Ret(resultI64.ref()));
                    } else {
                        // Must be pointer to struct type
                        Variable isNull = function.newVariable(I1);
                        function.add(new Icmp(isNull, Icmp.Condition.ne, result.ref(), new NullConstant(I8_PTR)));
                        Label success = new Label();
                        Label failure = new Label();
                        function.add(new Br(isNull.ref(), function.newBasicBlockRef(success), function.newBasicBlockRef(failure)));
                        function.newBasicBlock(success);
                        Value result2 = newStruct(function, getInternalName(method.getReturnType()), result.ref());
                        function.add(new Ret(result2));
                        function.newBasicBlock(failure);
                        function.add(new Ret(new NullConstant(OBJECT_PTR)));
                    }
                } else {
                    function.add(new Ret(result.ref()));
                }
            }
        } else {
            Value p = function.getParameterRef(2); // 'env' is parameter 0, 'this' is at 1, the value we're interested in is at index 2
            if (memberType instanceof StructureType) {
                Variable objectPtr = function.newVariable(OBJECT_PTR);
                function.add(new Bitcast(objectPtr, p, OBJECT_PTR));
                Variable memberI8Ptr = function.newVariable(I8_PTR);
                function.add(new Bitcast(memberI8Ptr, memberPtr.ref(), I8_PTR));
                call(function, BC_COPY_STRUCT, function.getParameterRef(0), 
                        objectPtr.ref(), memberI8Ptr.ref(), sizeof((StructureType) memberType));
            } else {
                if (memberType == I8_PTR) {
                    if (method.getParameterType(0).equals(LongType.v())) {
                        Variable tmp = function.newVariable(I8_PTR);
                        function.add(new Inttoptr(tmp, p, I8_PTR));
                        p = tmp.ref();
                    } else {
                        // Must be pointer to struct type
                        Variable objectPtr = function.newVariable(OBJECT_PTR);
                        function.add(new Bitcast(objectPtr, p, OBJECT_PTR));
                        p = call(function, BC_GET_STRUCT_HANDLE, 
                                function.getParameterRef(0), objectPtr.ref());
                    }
                }
                function.add(new Store(p, memberPtr.ref()));
            }
            function.add(new Ret());
        }
    }
    
    private Value newStruct(Function function, String targetClassName, Value handle) {
        FunctionRef fn = null;
        if (targetClassName.equals(this.className)) {
            fn = new FunctionRef(mangleClass(this.className) + "_allocator", 
                    new FunctionType(OBJECT_PTR, ENV_PTR));
        } else {
            Trampoline trampoline = new New(this.className, targetClassName);
            trampolines.add(trampoline);
            fn = trampoline.getFunctionRef();
        }
        Value result = call(function, fn, function.getParameterRef(0));
        Variable handleI64 = function.newVariable(I64);
        function.add(new Ptrtoint(handleI64, handle, I64));
        Invokespecial constructor = new Invokespecial(this.className, targetClassName, "<init>", "(J)V", targetClassName);
        trampolines.add(constructor);
        call(function, constructor.getFunctionRef(), function.getParameterRef(0), result, handleI64.ref());
        return result;
    }
}
