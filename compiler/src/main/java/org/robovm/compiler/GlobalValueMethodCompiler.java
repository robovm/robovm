/*
 * Copyright (C) 2012 RoboVM AB
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
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import org.robovm.compiler.Bro.MarshalerFlags;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;

import soot.SootMethod;
import soot.VoidType;
import soot.tagkit.AnnotationTag;

/**
 * @author niklas
 *
 */
public class GlobalValueMethodCompiler extends BroMethodCompiler {

    public GlobalValueMethodCompiler(Config config) {
        super(config);
    }

    private void validateGlobalValueMethod(SootMethod method, AnnotationTag globalValueAnnotation) {
        if (!method.isStatic()) {
            throw new IllegalArgumentException("@GlobalValue annotated method " 
                    + method + " must be static");
        }
        if (!method.isNative()) {
            throw new IllegalArgumentException("@GlobalValue annotated method " 
                    + method + " must be native");
        }
        if (!(method.getReturnType() != VoidType.v() && method.getParameterCount() == 0 
                || method.getReturnType() == VoidType.v() && method.getParameterCount() == 1)) {
            throw new IllegalArgumentException("Invalid signature for " 
                + "@GlobalValue annotated method " + method 
                + ". It should either take 0 arguments and return a non-void " 
                + "type or take 1 argument and return void");
        }
        // dereference can only be false for getters
        if (method.getReturnType() == VoidType.v()) {
            // Setter
            boolean dereference = readBooleanElem(globalValueAnnotation, "dereference", true);
            if (!dereference) {
                throw new IllegalArgumentException("Only @GlobalValue getter methods " 
                        + "are allowed to have dereference=false");
            }
        }
    }
    
    protected Function doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        AnnotationTag globalValueAnnotation = getAnnotation(method, GLOBAL_VALUE);
        
        validateGlobalValueMethod(method, globalValueAnnotation);
        
        boolean optional = readBooleanElem(globalValueAnnotation, "optional", false);
        boolean dereference = readBooleanElem(globalValueAnnotation, "dereference", true);

        Function fn = createMethodFunction(method);
        moduleBuilder.addFunction(fn);

        Type valueType = getStructMemberType(method);

        // Load the address of the resolved @GlobalValue method
        Variable valuePtr = fn.newVariable(new PointerType(valueType));
        Global valuePtrPtr = new Global(Symbols.globalValuePtrSymbol(method), 
                _private, new NullConstant(I8_PTR));
        moduleBuilder.addGlobal(valuePtrPtr);
        fn.add(new Load(valuePtr, new ConstantBitcast(valuePtrPtr.ref(), new PointerType(valuePtr.getType()))));

        Label nullLabel = new Label();
        Label notNullLabel = new Label();
        Variable nullCheck = fn.newVariable(I1);
        fn.add(new Icmp(nullCheck, Condition.eq, valuePtr.ref(), new NullConstant(valuePtr.getType())));
        fn.add(new Br(nullCheck.ref(), fn.newBasicBlockRef(nullLabel), fn.newBasicBlockRef(notNullLabel)));
        fn.newBasicBlock(nullLabel);
        VariableRef env = fn.getParameterRef(0);
        call(fn, BC_THROW_UNSATISIFED_LINK_ERROR, env,
                moduleBuilder.getString(String.format((optional ? "Optional " : "") 
                        + "@GlobalValue method %s.%s%s not bound", className,
                        method.getName(), getDescriptor(method))));
        fn.add(new Unreachable());
        fn.newBasicBlock(notNullLabel);
        
        if (method.getParameterCount() == 0) {
            // Getter
            Value result = loadValueForGetter(method, fn, valueType, valuePtr.ref(), 
                    env, dereference, MarshalerFlags.CALL_TYPE_GLOBAL_VALUE);
            fn.add(new Ret(result));
        } else {
            // Setter
            Value value = fn.getParameterRef(1); // 'env' is parameter 0, the value we're interested in is at index 1
            storeValueForSetter(method, fn, valueType, valuePtr.ref(), env, 
                    value, MarshalerFlags.CALL_TYPE_GLOBAL_VALUE);
            fn.add(new Ret());
        }
        
        return fn;
    }
}
