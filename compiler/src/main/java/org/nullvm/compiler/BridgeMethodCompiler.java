/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Annotations.*;
import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.FunctionAttribute.*;
import static org.nullvm.compiler.llvm.Linkage.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.ArrayList;

import org.nullvm.compiler.llvm.Argument;
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Br;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.Icmp;
import org.nullvm.compiler.llvm.Icmp.Condition;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.Inttoptr;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Load;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.ParameterAttribute;
import org.nullvm.compiler.llvm.PointerType;
import org.nullvm.compiler.llvm.Ptrtoint;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;

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

        Function outerFn = createFunction(method, external, noinline);
        moduleBuilder.addFunction(outerFn);
        Function innerFn = createFunction(mangleMethod(method) + "_inner", 
                method, internal, noinline);
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
                    Value tmp = call(innerFn, NVM_BC_BY_VALUE_GET_STRUCT_HANDLE, ENV, args.get(i).getValue());
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
        call(innerFn, NVM_BC_THROW_UNSATISIFED_LINK_ERROR, ENV);
        innerFn.add(new Unreachable());
        innerFn.newBasicBlock(notNullLabel);
        
        Value frameAddress = call(innerFn, LLVM_FRAMEADDRESS, new IntegerConstant(0));
        call(innerFn, NVM_BC_PUSH_NATIVE_FRAME, ENV, frameAddress);
        Value resultInner = callWithArguments(innerFn, targetFn.ref(), args);
        call(innerFn, NVM_BC_POP_NATIVE_FRAME, ENV);

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
