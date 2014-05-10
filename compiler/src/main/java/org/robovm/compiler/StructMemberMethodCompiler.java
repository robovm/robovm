/*
 * Copyright (C) 2012 Trillian Mobile AB
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


import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import org.robovm.compiler.Bro.MarshalerFlags;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;

import soot.SootMethod;
import soot.VoidType;

/**
 * @author niklas
 *
 */
public class StructMemberMethodCompiler extends BroMethodCompiler {

    private StructureType structType;
    
    public StructMemberMethodCompiler(Config config) {
        super(config);
    }

    @Override
    public void reset(Clazz clazz) {
        super.reset(clazz);
        structType = null;
        if (isStruct(sootClass)) {
            structType = getStructType(sootClass);
        }
    }
    
    @Override
    protected Function doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        if ("_sizeOf".equals(method.getName()) || "sizeOf".equals(method.getName())) {
            return structSizeOf(moduleBuilder, method);
        } else {
            return structMember(moduleBuilder, method);
        }
    }
    
    private Function structSizeOf(ModuleBuilder moduleBuilder, SootMethod method) {
        Function fn = FunctionBuilder.structSizeOf(method);
        moduleBuilder.addFunction(fn);
        fn.add(new Ret(sizeof(structType)));
        return fn;
    }

    private Function structMember(ModuleBuilder moduleBuilder, SootMethod method) {
        Function function = FunctionBuilder.structMember(method);
        moduleBuilder.addFunction(function);
        
        // Get the value of the handle field in the Struct base class and cast it to a <structType>*
        Variable handleI64 = function.newVariable(I64);
        function.add(new Load(handleI64, getFieldPtr(function, function.getParameterRef(1), 
                offsetof(new StructureType(DATA_OBJECT, new StructureType(I64)), 1, 0), I64)));
        Variable handlePtr = function.newVariable(new PointerType(structType));
        function.add(new Inttoptr(handlePtr, handleI64.ref(), handlePtr.getType()));
        
        int offset = getStructMemberOffset(method) + 1; // Add 1 since the first type in structType is the superclass type or {}.      
        Type memberType = getStructMemberType(method);
        Variable memberPtr = function.newVariable(new PointerType(memberType));
        if (memberType != structType.getTypeAt(offset)) {
            // Several @StructMembers of different types have this offset (union)
            Variable tmp = function.newVariable(new PointerType(structType.getTypeAt(offset)));
            function.add(new Getelementptr(tmp, handlePtr.ref(), 0, offset));
            function.add(new Bitcast(memberPtr, tmp.ref(), memberPtr.getType()));
        } else {
            function.add(new Getelementptr(memberPtr, handlePtr.ref(), 0, offset));
        }
        
        VariableRef env = function.getParameterRef(0);
        if (method.getParameterCount() == 0) {
            // Getter
            Value result = loadValueForGetter(method, function, memberType, memberPtr.ref(),
                    function.getParameterRef(0), MarshalerFlags.CALL_TYPE_STRUCT_MEMBER);
            function.add(new Ret(result));
            
        } else {
            // Setter
            
            Value value = function.getParameterRef(2); // 'env' is parameter 0, 'this' is at 1, the value we're interested in is at index 2
            storeValueForSetter(method, function, memberType, memberPtr.ref(), env,
                    value, MarshalerFlags.CALL_TYPE_STRUCT_MEMBER);
            
            if (method.getReturnType().equals(VoidType.v())) {
                function.add(new Ret());
            } else {
                function.add(new Ret(function.getParameterRef(1)));
            }
        }
        
        return function;
    }
}
