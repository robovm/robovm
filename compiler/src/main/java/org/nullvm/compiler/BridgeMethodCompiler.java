/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Annotations.*;
import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.ArrayList;

import org.nullvm.compiler.llvm.Argument;
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Br;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.Icmp;
import org.nullvm.compiler.llvm.Icmp.Condition;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.Inttoptr;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Linkage;
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
import org.nullvm.compiler.trampoline.BridgeCall;
import org.nullvm.compiler.trampoline.Trampoline;

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

    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
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

        Function outerFunction = createFunction(method, Linkage.external, 
                FunctionAttribute.noinline);
        moduleBuilder.addFunction(outerFunction);
        Function innerFunction = createFunction(mangleMethod(method.makeRef()) + "_inner", 
                method, Linkage.internal, FunctionAttribute.noinline);
        moduleBuilder.addFunction(innerFunction);
        
        String targetClassName = getInternalName(method.getDeclaringClass());
        String methodName = method.getName();
        String methodDesc = getDescriptor(method);
        Trampoline trampoline = new BridgeCall(this.className, targetClassName, methodName, methodDesc, method.isStatic());
        trampolines.add(trampoline);        
        
        Type[] parameterTypes = innerFunction.getType().getParameterTypes();
        String[] parameterNames = innerFunction.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        Value resultOuter = callWithArguments(outerFunction, innerFunction.ref(), args);
        outerFunction.add(new Ret(resultOuter));

        FunctionType targetFunctionType = getBridgeFunctionType(method);

        // Remove Env* from args
        args.remove(0);
        if (!method.isStatic()) {
            // Remove receiver from args
            args.remove(0);
        }
        Type[] targetParameterTypes = targetFunctionType.getParameterTypes();
        for (int i = 0; i < targetParameterTypes.length; i++) {
            if (targetParameterTypes[i] instanceof PointerType && ((PointerType) targetParameterTypes[i]).getBase() instanceof StructureType) {
                Value tmp = call(innerFunction, NVM_BC_BY_VALUE_GET_STRUCT_HANDLE, ENV, args.get(i).getValue());
                Variable arg = innerFunction.newVariable(targetParameterTypes[i]);
                innerFunction.add(new Bitcast(arg, tmp, arg.getType()));
                args.set(i, new Argument(arg.ref(), ParameterAttribute.byval));
            } else if (targetParameterTypes[i] == I8_PTR) {
                // Convert arg at index i from i64 to i8*
                Variable arg = innerFunction.newVariable(I8_PTR);
                innerFunction.add(new Inttoptr(arg, args.get(i).getValue(), I8_PTR));
                if (hasStructRetAnnotation(method, i)) {
                    args.set(i, new Argument(arg.ref(), ParameterAttribute.sret));
                } else {
                    args.set(i, new Argument(arg.ref()));                    
                }
            }
        }
        
        Variable targetFunction = innerFunction.newVariable(targetFunctionType);
        Global targetFunctionPtr = new Global(outerFunction.getName().substring(1) + "_ptr", Linkage._private, new NullConstant(targetFunctionType));
        moduleBuilder.addGlobal(targetFunctionPtr);
        innerFunction.add(new Load(targetFunction, targetFunctionPtr.ref()));

        Label nullLabel = new Label();
        Label notNullLabel = new Label();
        Variable nullCheck = innerFunction.newVariable(I1);
        innerFunction.add(new Icmp(nullCheck, Condition.eq, targetFunction.ref(), new NullConstant(targetFunctionType)));
        innerFunction.add(new Br(nullCheck.ref(), innerFunction.newBasicBlockRef(nullLabel), innerFunction.newBasicBlockRef(notNullLabel)));
        innerFunction.newBasicBlock(nullLabel);
        call(innerFunction, NVM_BC_THROW_UNSATISIFED_LINK_ERROR, ENV);
        innerFunction.add(new Unreachable());
        innerFunction.newBasicBlock(notNullLabel);
        
        Value frameAddress = call(innerFunction, LLVM_FRAMEADDRESS, new IntegerConstant(0));
        call(innerFunction, NVM_BC_PUSH_NATIVE_FRAME, ENV, frameAddress);
        Value resultInner = callWithArguments(innerFunction, targetFunction.ref(), args);
        call(innerFunction, NVM_BC_POP_NATIVE_FRAME, ENV);

        if (targetFunctionType.getReturnType() == I8_PTR) {
            Variable resultI64 = innerFunction.newVariable(I64);
            innerFunction.add(new Ptrtoint(resultI64, resultInner, I64));
            resultInner = resultI64.ref();
        }
        innerFunction.add(new Ret(resultInner));
    }
}
