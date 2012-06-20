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
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;

import org.robovm.compiler.llvm.Argument;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;

import soot.PrimType;
import soot.SootMethod;
import soot.VoidType;

/**
 * @author niklas
 *
 */
public class BridgeMethodCompiler extends AbstractMethodCompiler {

    public BridgeMethodCompiler(Config config) {
        super(config);
    }

    private void validateBridgeMethod(SootMethod method) {
        soot.Type sootRetType = method.getReturnType();
        if (!sootRetType.equals(VoidType.v()) && !(sootRetType instanceof PrimType) && !isStruct(sootRetType)) {
            throw new IllegalArgumentException("@Bridge annotated method must return void or a primitive or Struct type");
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            soot.Type t = method.getParameterType(i);
            if (!(t instanceof PrimType) && !isStruct(t)) {
                throw new IllegalArgumentException("@Bridge annotated method must take only primitive or Struct type arguments");
            }            
        }
    }
    
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        validateBridgeMethod(method);

        Function outerFn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(outerFn);
        Function innerFn = FunctionBuilder.bridgeInner(method);
        moduleBuilder.addFunction(innerFn);
        
        Type[] parameterTypes = innerFn.getType().getParameterTypes();
        String[] parameterNames = innerFn.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        Value resultOuter = callWithArguments(outerFn, innerFn.ref(), args);
        outerFn.add(new Ret(resultOuter));

        FunctionType targetFnType = getBridgeFunctionType(method);

        // Remove Env* from args
        args.remove(0);
        if (!method.isStatic()) {
            // Remove receiver from args
            args.remove(0);
        }
        Type[] targetParameterTypes = targetFnType.getParameterTypes();
        for (int i = 0; i < targetParameterTypes.length; i++) {
            if (targetParameterTypes[i] instanceof PointerType) {
                PointerType type = (PointerType) targetParameterTypes[i];
                if (type.getBase() instanceof StructureType) {
                    Value tmp = call(innerFn, BC_BY_VALUE_GET_STRUCT_HANDLE, 
                            innerFn.getParameterRef(0), args.get(i).getValue());
                    Variable arg = innerFn.newVariable(type);
                    innerFn.add(new Bitcast(arg, tmp, arg.getType()));
                    args.set(i, new Argument(arg.ref(), ParameterAttribute.byval));
                } else if (type == I8_PTR) {
                    // Convert arg at index i from i64 to i8*
                    Variable arg = innerFn.newVariable(I8_PTR);
                    innerFn.add(new Inttoptr(arg, args.get(i).getValue(), I8_PTR));
                    if (hasStructRetAnnotation(method, i)) {
                        args.set(i, new Argument(arg.ref(), ParameterAttribute.sret));
                    } else {
                        args.set(i, new Argument(arg.ref()));                    
                    }
                }
            }
        }
        
        Variable targetFn = innerFn.newVariable(targetFnType);
        Global targetFnPtr = new Global(getTargetFnPtrName(method), 
                _private, new NullConstant(I8_PTR));
        moduleBuilder.addGlobal(targetFnPtr);
        innerFn.add(new Load(targetFn, new ConstantBitcast(targetFnPtr.ref(), new PointerType(targetFnType))));

        Label nullLabel = new Label();
        Label notNullLabel = new Label();
        Variable nullCheck = innerFn.newVariable(I1);
        innerFn.add(new Icmp(nullCheck, Condition.eq, targetFn.ref(), new NullConstant(targetFnType)));
        innerFn.add(new Br(nullCheck.ref(), innerFn.newBasicBlockRef(nullLabel), innerFn.newBasicBlockRef(notNullLabel)));
        innerFn.newBasicBlock(nullLabel);
        call(innerFn, BC_THROW_UNSATISIFED_LINK_ERROR, innerFn.getParameterRef(0),
                moduleBuilder.getString(String.format("Bridge method %s.%s%s not bound", className,
                        method.getName(), getDescriptor(method))));
        innerFn.add(new Unreachable());
        innerFn.newBasicBlock(notNullLabel);
        
        pushNativeFrame(innerFn);
        Value resultInner = callWithArguments(innerFn, targetFn.ref(), args);
        popNativeFrame(innerFn);

        if (targetFnType.getReturnType() == I8_PTR) {
            Variable resultI64 = innerFn.newVariable(I64);
            innerFn.add(new Ptrtoint(resultI64, resultInner, I64));
            resultInner = resultI64.ref();
        }
        innerFn.add(new Ret(resultInner));
    }
    
    public static String getTargetFnPtrName(SootMethod method) {
        return "bridge_" + mangleMethod(method) + "_ptr";
    }
}
