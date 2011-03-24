/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.llvm.Type.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.nullvm.compiler.llvm.Add;
import org.nullvm.compiler.llvm.Alloca;
import org.nullvm.compiler.llvm.And;
import org.nullvm.compiler.llvm.Ashr;
import org.nullvm.compiler.llvm.BasicBlock;
import org.nullvm.compiler.llvm.BasicBlockRef;
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Br;
import org.nullvm.compiler.llvm.Call;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantAdd;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.ConstantGetelementptr;
import org.nullvm.compiler.llvm.ConstantPtrtoint;
import org.nullvm.compiler.llvm.ConstantSub;
import org.nullvm.compiler.llvm.Fdiv;
import org.nullvm.compiler.llvm.FloatingPointConstant;
import org.nullvm.compiler.llvm.FloatingPointType;
import org.nullvm.compiler.llvm.Fpext;
import org.nullvm.compiler.llvm.Fptrunc;
import org.nullvm.compiler.llvm.Frem;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionDeclaration;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Getelementptr;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.GlobalRef;
import org.nullvm.compiler.llvm.Icmp;
import org.nullvm.compiler.llvm.Icmp.Condition;
import org.nullvm.compiler.llvm.Instruction;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.IntegerType;
import org.nullvm.compiler.llvm.Invoke;
import org.nullvm.compiler.llvm.Load;
import org.nullvm.compiler.llvm.Lshr;
import org.nullvm.compiler.llvm.Module;
import org.nullvm.compiler.llvm.Mul;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.OpaqueType;
import org.nullvm.compiler.llvm.Or;
import org.nullvm.compiler.llvm.PointerType;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.Sext;
import org.nullvm.compiler.llvm.Shl;
import org.nullvm.compiler.llvm.Sitofp;
import org.nullvm.compiler.llvm.Store;
import org.nullvm.compiler.llvm.StringConstant;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Sub;
import org.nullvm.compiler.llvm.Switch;
import org.nullvm.compiler.llvm.Trunc;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Uitofp;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.llvm.Xor;
import org.nullvm.compiler.llvm.Zext;
import org.nullvm.compiler.trampoline.Checkcast;
import org.nullvm.compiler.trampoline.GetField;
import org.nullvm.compiler.trampoline.GetStatic;
import org.nullvm.compiler.trampoline.Instanceof;
import org.nullvm.compiler.trampoline.Invokeinterface;
import org.nullvm.compiler.trampoline.Invokespecial;
import org.nullvm.compiler.trampoline.Invokestatic;
import org.nullvm.compiler.trampoline.Invokevirtual;
import org.nullvm.compiler.trampoline.New;
import org.nullvm.compiler.trampoline.PutField;
import org.nullvm.compiler.trampoline.PutStatic;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.Body;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.FloatType;
import soot.Immediate;
import soot.IntType;
import soot.Local;
import soot.LongType;
import soot.Modifier;
import soot.PackManager;
import soot.PatchingChain;
import soot.PrimType;
import soot.RefType;
import soot.ResolutionFailedException;
import soot.Scene;
import soot.ShortType;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Trap;
import soot.Unit;
import soot.VoidType;
import soot.jimple.AddExpr;
import soot.jimple.AndExpr;
import soot.jimple.ArrayRef;
import soot.jimple.BinopExpr;
import soot.jimple.CastExpr;
import soot.jimple.CaughtExceptionRef;
import soot.jimple.CmpExpr;
import soot.jimple.CmpgExpr;
import soot.jimple.CmplExpr;
import soot.jimple.ConditionExpr;
import soot.jimple.DefinitionStmt;
import soot.jimple.DivExpr;
import soot.jimple.EnterMonitorStmt;
import soot.jimple.EqExpr;
import soot.jimple.ExitMonitorStmt;
import soot.jimple.Expr;
import soot.jimple.FieldRef;
import soot.jimple.GeExpr;
import soot.jimple.GotoStmt;
import soot.jimple.GtExpr;
import soot.jimple.IfStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InstanceOfExpr;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LeExpr;
import soot.jimple.LengthExpr;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.NewArrayExpr;
import soot.jimple.NewExpr;
import soot.jimple.OrExpr;
import soot.jimple.ParameterRef;
import soot.jimple.RemExpr;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.ShlExpr;
import soot.jimple.ShrExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.StaticFieldRef;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.SubExpr;
import soot.jimple.ThisRef;
import soot.jimple.ThrowStmt;
import soot.jimple.UshrExpr;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.XorExpr;
import soot.jimple.toolkits.annotation.tags.ArrayCheckTag;
import soot.jimple.toolkits.annotation.tags.NullCheckTag;
import soot.options.Options;

/**
 *
 * @version $Id$
 */
public class SootClassCompiler {
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    private static final Type ENV_PTR = new PointerType(new OpaqueType("Env"));
    // Dummy Class type definition. The real one is in header.ll
    private static final StructureType CLASS = new StructureType("Class", I8_PTR);
    private static final Type CLASS_PTR = new PointerType(CLASS);
    // Dummy Object type definition. The real one is in header.ll
    private static final StructureType OBJECT = new StructureType("Object", CLASS_PTR);
    private static final Type OBJECT_PTR = new PointerType(OBJECT);
    
    private static final VariableRef ENV = new VariableRef("env", ENV_PTR);
    private static final Global THE_CLASS = new Global("class", new NullConstant(CLASS_PTR));
    
    //_nvmBcExceptionClear
    private static final FunctionRef NVM_BC_ALLOCATE_CLASS = new FunctionRef("_nvmBcAllocateClass", new FunctionType(CLASS_PTR, ENV_PTR, I8_PTR, I8_PTR, I32, I32, I32));
    private static final FunctionRef NVM_BC_ADD_FIELD = new FunctionRef("_nvmBcAddField", new FunctionType(VOID, ENV_PTR, I8_PTR, I8_PTR, I32, I32, I8_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_PERSONALITY = new FunctionRef("_nvmPersonality", new FunctionType(I8_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_MATCH = new FunctionRef("_nvmBcExceptionMatch", new FunctionType(I32, ENV_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_CLEAR = new FunctionRef("_nvmBcExceptionClear", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_SET = new FunctionRef("_nvmBcExceptionSet", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_THROW = new FunctionRef("_nvmBcThrow", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_RETHROW = new FunctionRef("_nvmBcRethrow", new FunctionType(VOID, ENV_PTR));
    private static final FunctionRef NVM_BC_THROW_IF_EXCEPTION_OCCURRED = new FunctionRef("_nvmBcThrowIfExceptionOccurred", new FunctionType(VOID, ENV_PTR));
    private static final FunctionRef NVM_BC_NEWARRAYZ = new FunctionRef("_nvmBcNewArrayZ", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYB = new FunctionRef("_nvmBcNewArrayB", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYC = new FunctionRef("_nvmBcNewArrayC", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYS = new FunctionRef("_nvmBcNewArrayS", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYI = new FunctionRef("_nvmBcNewArrayI", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYJ = new FunctionRef("_nvmBcNewArrayJ", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYF = new FunctionRef("_nvmBcNewArrayF", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEWARRAYD = new FunctionRef("_nvmBcNewArrayD", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_ENTER_MONITOR = new FunctionRef("_nvmBcEnterMonitor", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_EXIT_MONITOR = new FunctionRef("_nvmBcExitMonitor", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_LDC_STRING = new FunctionRef("_nvmBcLdcString", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_LOOKUP_VIRTUAL_METHOD = new FunctionRef("_nvmBcLookupVirtualMethod", new FunctionType(I8_PTR, ENV_PTR, OBJECT_PTR, I8_PTR, I8_PTR));
    
    private static final FunctionRef LLVM_EH_EXCEPTION = new FunctionRef("llvm.eh.exception", new FunctionType(I8_PTR));
    private static final FunctionRef LLVM_EH_SELECTOR = new FunctionRef("llvm.eh.selector", new FunctionType(I32, true, I8_PTR, I8_PTR));
    
    private static final FunctionRef CHECK_NULL = new FunctionRef("checknull", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef CHECK_LOWER = new FunctionRef("checklower", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32));
    private static final FunctionRef CHECK_UPPER = new FunctionRef("checkupper", new FunctionType(VOID, ENV_PTR, OBJECT_PTR, I32));
    private static final FunctionRef ARRAY_LENGTH = new FunctionRef("arraylength", new FunctionType(I32, OBJECT_PTR));
    private static final FunctionRef BALOAD = new FunctionRef("baload", new FunctionType(I8, OBJECT_PTR, I32));
    private static final FunctionRef SALOAD = new FunctionRef("saload", new FunctionType(I16, OBJECT_PTR, I32));
    private static final FunctionRef CALOAD = new FunctionRef("caload", new FunctionType(I16, OBJECT_PTR, I32));
    private static final FunctionRef IALOAD = new FunctionRef("iaload", new FunctionType(I32, OBJECT_PTR, I32));
    private static final FunctionRef LALOAD = new FunctionRef("laload", new FunctionType(I64, OBJECT_PTR, I32));
    private static final FunctionRef FALOAD = new FunctionRef("faload", new FunctionType(FLOAT, OBJECT_PTR, I32));
    private static final FunctionRef DALOAD = new FunctionRef("daload", new FunctionType(DOUBLE, OBJECT_PTR, I32));
    private static final FunctionRef AALOAD = new FunctionRef("aaload", new FunctionType(OBJECT_PTR, OBJECT_PTR, I32));
    private static final FunctionRef BASTORE = new FunctionRef("bastore", new FunctionType(VOID, OBJECT_PTR, I32, I8));
    private static final FunctionRef SASTORE = new FunctionRef("sastore", new FunctionType(VOID, OBJECT_PTR, I32, I16));
    private static final FunctionRef CASTORE = new FunctionRef("castore", new FunctionType(VOID, OBJECT_PTR, I32, I16));
    private static final FunctionRef IASTORE = new FunctionRef("iastore", new FunctionType(VOID, OBJECT_PTR, I32, I32));
    private static final FunctionRef LASTORE = new FunctionRef("lastore", new FunctionType(VOID, OBJECT_PTR, I32, I64));
    private static final FunctionRef FASTORE = new FunctionRef("fastore", new FunctionType(VOID, OBJECT_PTR, I32, FLOAT));
    private static final FunctionRef DASTORE = new FunctionRef("dastore", new FunctionType(VOID, OBJECT_PTR, I32, DOUBLE));
    private static final FunctionRef AASTORE = new FunctionRef("aastore", new FunctionType(VOID, OBJECT_PTR, I32, OBJECT_PTR));
    private static final FunctionRef F2I = new FunctionRef("f2i", new FunctionType(I32, FLOAT));
    private static final FunctionRef F2L = new FunctionRef("f2l", new FunctionType(I64, FLOAT));
    private static final FunctionRef D2I = new FunctionRef("d2i", new FunctionType(I32, DOUBLE));
    private static final FunctionRef D2L = new FunctionRef("d2l", new FunctionType(I64, DOUBLE));
    private static final FunctionRef IDIV = new FunctionRef("idiv", new FunctionType(I32, ENV_PTR, I32, I32));
    private static final FunctionRef LDIV = new FunctionRef("ldiv", new FunctionType(I64, ENV_PTR, I64, I64));
    private static final FunctionRef IREM = new FunctionRef("irem", new FunctionType(I32, ENV_PTR, I32, I32));
    private static final FunctionRef LREM = new FunctionRef("lrem", new FunctionType(I64, ENV_PTR, I64, I64));
    private static final FunctionRef FCMPL = new FunctionRef("fcmpl", new FunctionType(I32, FLOAT, FLOAT));
    private static final FunctionRef FCMPG = new FunctionRef("fcmpg", new FunctionType(I32, FLOAT, FLOAT));
    private static final FunctionRef DCMPL = new FunctionRef("dcmpl", new FunctionType(I32, DOUBLE, DOUBLE));
    private static final FunctionRef DCMPG = new FunctionRef("dcmpl", new FunctionType(I32, DOUBLE, DOUBLE));

    private static final Map<Class<? extends Trampoline>, Integer> TRAMPOLINE_TYPES;
    
    static {
        TRAMPOLINE_TYPES = new HashMap<Class<? extends Trampoline>, Integer>();
        TRAMPOLINE_TYPES.put(New.class, 1);
        TRAMPOLINE_TYPES.put(Checkcast.class, 2);
        TRAMPOLINE_TYPES.put(Instanceof.class, 3);
        TRAMPOLINE_TYPES.put(GetField.class, 4);
        TRAMPOLINE_TYPES.put(GetStatic.class, 5);
        TRAMPOLINE_TYPES.put(PutField.class, 6);
        TRAMPOLINE_TYPES.put(PutStatic.class, 7);
        TRAMPOLINE_TYPES.put(Invokespecial.class, 8);
        TRAMPOLINE_TYPES.put(Invokestatic.class, 9);
        TRAMPOLINE_TYPES.put(Invokevirtual.class, 10);
        TRAMPOLINE_TYPES.put(Invokeinterface.class, 11);
    }
    
    private final SootClass sootClass;
    
    private Module module;
    private Map<String, Global> throwables;
    private Map<Trampoline, FunctionRef> trampolines;
    private Map<String, Global> strings;
    /**
     * Contains the class fields of the class being compiled.
     */
    private List<SootField> classFields;
    /**
     * Contains the instance fields of the class being compiled.
     */
    private List<SootField> instanceFields;
    /**
     * Contains the instance fields of the class being compiled and of all of its
     * ancestor classes.
     */
    private List<SootField> allInstanceFields;
    
    private StructureType classFieldsType;
    private StructureType instanceFieldsType;
    
    public SootClassCompiler(SootClass sootClass) {
        this.sootClass = sootClass;
    }
    
    public Module compile() {
        module = new Module();
        throwables = new TreeMap<String, Global>();
        trampolines = new HashMap<Trampoline, FunctionRef>();
        strings = new HashMap<String, Global>();
        
        module.addInclude(getClass().getClassLoader().getResource("header.ll"));

        classFields = getClassFields(sootClass, false);
        instanceFields = getInstanceFields(sootClass, false);
        allInstanceFields = getInstanceFields(sootClass, true);
        
        classFieldsType = getType("ClassFields", classFields);
        if (classFieldsType != null) {
            module.addType(classFieldsType);
        }
        instanceFieldsType = getType("InstanceFields", allInstanceFields);
        if (instanceFieldsType != null) {
            module.addType(instanceFieldsType);
        }

        List<SootField> allFields = new ArrayList<SootField>();
        allFields.addAll(classFields);
        allFields.addAll(instanceFields);
        for (SootField field : allFields) {
            // TODO: Don't create accessors for private fields?
            fieldGetter(field);
            fieldSetter(field);
        }
        
        for (SootMethod method : sootClass.getMethods()) {
            if (isNative(method)) {
                nativeMethod(method);
            } else if (!method.isAbstract()) {
                Body body = method.retrieveActiveBody();
                PackManager.v().getPack("jtp").apply(body);
                PackManager.v().getPack("jop").apply(body);
                PackManager.v().getPack("jap").apply(body);
                method(method);
            }
            if (!method.isStatic() && !method.isPrivate() && !Modifier.isFinal(method.getModifiers())) {
                // Virtual method. If not defined in a superclass we need to create a virtual lookup function now.
                if (!ancestorDeclaresMethod(sootClass, method)) {
                    virtualLookupFunction(method);
                }
            }
        }
        
        for (FunctionRef ref : trampolines.values()) {
            trampoline(ref);
        }
        
        classLoaderFunction();
        
        module.addGlobal(THE_CLASS);
        
        for (Global global : throwables.values()) {
            module.addGlobal(global);
        }
        for (Global global : strings.values()) {
            module.addGlobal(global);
        }
        
        return module;
    }
    
//    private void createTrampolinesResolver() {
//        Type descType = new PointerType(new StructureType(I32, I8_PTR, I8_PTR, I8_PTR));
//        VariableRef desc = new VariableRef("desc", descType);
//        Function f = module.newFunction("resolver", new FunctionType(VOID, ENV_PTR, I32, descType), "env", "index", "desc");
//        TreeMap<IntegerConstant, BasicBlockRef> alt = new TreeMap<IntegerConstant, BasicBlockRef>();
//        for (int index = 0; index < trampolines.size(); index++) {
//            alt.put(new IntegerConstant(index), f.newBasicBlockRef(index));
//        }
//        f.newBasicBlock(new Object());
//        f.add(new Switch(new VariableRef("index", I32), f.newBasicBlockRef(trampolines.size()), alt));
//        for (int index = 0; index < trampolines.size(); index++) {
//            Trampoline t = trampolines.get(index);
//            f.newBasicBlock(index);
//            
//            int type = TRAMPOLINE_TYPES.get(t.getClass());
//            Variable typePtr = f.newVariable(new PointerType(I32));
//            f.add(new Getelementptr(typePtr, desc, 0, 0));
//            f.add(new Store(new IntegerConstant(type), new VariableRef(typePtr)));
//            
//            String targetClass = t.getTargetClass();
//            Variable targetClassPtr = f.newVariable(new PointerType(I8_PTR));
//            f.add(new Getelementptr(targetClassPtr, desc, 0, 1));
//            f.add(new Store(new ConstantBitcast(new GlobalRef(addString(targetClass)), I8_PTR), new VariableRef(targetClassPtr)));
//
//            String methodOrFieldName = null;
//            String methodOrFieldDesc = null;
//            if (t instanceof org.nullvm.compiler.trampoline.Invoke) {
//                methodOrFieldName = ((org.nullvm.compiler.trampoline.Invoke) t).getMethodName();
//                methodOrFieldDesc = ((org.nullvm.compiler.trampoline.Invoke) t).getMethodDesc();
//            } else if (t instanceof FieldAccessor) {
//                methodOrFieldName = ((FieldAccessor) t).getFieldName();
//                methodOrFieldName = ((FieldAccessor) t).getFieldDesc();
//            }
//            
//            if (methodOrFieldName != null) {
//                Variable ptr = f.newVariable(new PointerType(I8_PTR));
//                f.add(new Getelementptr(ptr, desc, 0, 2));
//                f.add(new Store(new ConstantBitcast(new GlobalRef(addString(methodOrFieldName)), I8_PTR), new VariableRef(ptr)));
//            }
//            if (methodOrFieldDesc != null) {
//                Variable ptr = f.newVariable(new PointerType(I8_PTR));
//                f.add(new Getelementptr(ptr, desc, 0, 3));
//                f.add(new Store(new ConstantBitcast(new GlobalRef(addString(methodOrFieldDesc)), I8_PTR), new VariableRef(ptr)));
//            }
//            
//            f.add(new Ret());
//        }
//        f.newBasicBlock(trampolines.size());
//        f.add(new Ret());
//    }
    
    private static boolean isTerminator(Instruction instr) {
        return instr instanceof Ret || instr instanceof Br 
            || instr instanceof Invoke || instr instanceof Unreachable 
            || instr instanceof Switch;
    }
    
    private void trampoline(FunctionRef functionRef) {
        // TODO: X86-64 specific
        String name = functionRef.getName().substring(1);
        module.addFunctionDeclaration(new FunctionDeclaration(name, functionRef.getType()));
//        module.addAsm(name + "_ptr:");
//        module.addAsm("\t.quad 0");
        module.addAsm(name + ":");
        module.addAsm("\tmovq " + name + "_ptr@GOTPCREL(%rip), %rax");
        module.addAsm("\tjmpq *(%rax)");
        module.addGlobal(new Global(name + "_ptr", new NullConstant(I8_PTR)));
//        String[] parameterNames = new String[functionRef.getType().getParameterTypes().length];
//        for (int i = 0; i < parameterNames.length; i++) {
//            parameterNames[i] = "p" + i;
//        }
//        Function function = module.newFunction(functionRef.getName().substring(1), functionRef.getType(), parameterNames);
//        FunctionType functionType = function.getType();
//        function.newBasicBlock(new Object());
//        Global functionPtr = new Global(function.getName().substring(1) + "_ptr", new NullConstant(functionType));
//        module.addGlobal(functionPtr);
//        Variable f = function.newVariable(functionType);
//        function.add(new Load(f, new GlobalRef(functionPtr)));
//        Type[] parameterTypes = function.getType().getParameterTypes();
//        Value[] args = new Value[parameterNames.length];
//        for (int i = 0; i < args.length; i++) {
//            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
//        }
//        if (function.getType().getReturnType() == VOID) {
//            function.add(new Call(new VariableRef(f), args));
//            function.add(new Ret());
//        } else {
//            Variable result = function.newVariable(functionType.getReturnType());
//            function.add(new Call(result, new VariableRef(f), args));
//            function.add(new Ret(new VariableRef(result)));
//        }
    }
    
    private void nativeMethod(SootMethod method) {
        Function function = createFunction(method);
        FunctionType functionType = function.getType();
        function.newBasicBlock(new Object());
        Global functionPtr = new Global(function.getName().substring(1) + "_native_ptr", new NullConstant(functionType));
        module.addGlobal(functionPtr);
        Variable f = function.newVariable(functionType);
        function.add(new Load(f, new GlobalRef(functionPtr)));
        Type[] parameterTypes = function.getType().getParameterTypes();
        String[] parameterNames = function.getParameterNames();
        Value[] args = new Value[parameterNames.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
        }
        if (function.getType().getReturnType() == VOID) {
            function.add(new Call(new VariableRef(f), args));
            function.add(new Call(NVM_BC_THROW_IF_EXCEPTION_OCCURRED, ENV));
            function.add(new Ret());
        } else {
            Variable result = function.newVariable(functionType.getReturnType());
            function.add(new Call(result, new VariableRef(f), args));
            function.add(new Call(NVM_BC_THROW_IF_EXCEPTION_OCCURRED, ENV));
            function.add(new Ret(new VariableRef(result)));
        }
    }
    
    private void method(SootMethod method) {

        Context ctx = new Context();
        
        Body body = method.retrieveActiveBody();
        
        Function function = createFunction(method);

        ctx.setCurrentMethod(method);
        ctx.setCurrentBody(body);
        ctx.setCurrentFunction(function);
        
        ctx.f().newBasicBlock(new Object());
        Set<String> seen = new HashSet<String>();
        for (Unit unit : body.getUnits()) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getLeftOp() instanceof Local) {
                    Local local = (Local) stmt.getLeftOp();
                    if (!seen.contains(local.getName())) {
                        Type type = getType(local.getType());
                        ctx.f().add(new Alloca(ctx.f().newVariable(local.getName(), type), type));
                        seen.add(local.getName());
                    }
                }
            }
        }
        
//        Variable localTrampolines = ctx.f().newVariable("trampolines", new PointerType(I8_PTR));
//        ctx.f().add(new Load(localTrampolines, new GlobalRef("trampolines", new PointerType(new PointerType(I8_PTR)))));
        
        PatchingChain<Unit> units = body.getUnits();
        for (Unit unit : units) {
            ctx.setCurrentUnit(unit);
            if (ctx.bb() == null 
                    || ctx.bb().getTag() != unit 
                    && (ctx.isJumpTarget(unit) || ctx.isTrapHandler(unit))) {
                BasicBlock oldBlock = ctx.bb();
                ctx.f().newBasicBlock(unit);
                if (oldBlock != null) {
                    Instruction last = oldBlock.last();
                    if (last == null || !isTerminator(last)) {
                        oldBlock.add(new Br(ctx.f().newBasicBlockRef(unit)));
                    }
                }
            }
            if (unit instanceof DefinitionStmt) {
                assign(ctx, (DefinitionStmt) unit);
            } else if (unit instanceof ReturnStmt) {
                return_(ctx, (ReturnStmt) unit);
            } else if (unit instanceof ReturnVoidStmt) {
                returnVoid(ctx);
            } else if (unit instanceof IfStmt) {
                if_(ctx, (IfStmt) unit);
            } else if (unit instanceof GotoStmt) {
                goto_(ctx, (GotoStmt) unit);
            } else if (unit instanceof ThrowStmt) {
                throw_(ctx, (ThrowStmt) unit);
            } else if (unit instanceof InvokeStmt) {
                invoke(ctx, (InvokeStmt) unit);
            } else if (unit instanceof EnterMonitorStmt) {
                enterMonitor(ctx, (EnterMonitorStmt) unit);
            } else if (unit instanceof ExitMonitorStmt) {
                exitMonitor(ctx, (ExitMonitorStmt) unit);
            } else {
                throw new IllegalArgumentException("Unknown Unit type: " + unit.getClass());
            }
        }

        next: for (List<Trap> traps : ctx.getRecordedTraps()) {
            BasicBlock bb = function.newBasicBlock(traps);
            Variable ehptr = function.newVariable(I8_PTR);
            bb.add(new Call(ehptr, LLVM_EH_EXCEPTION));
            Variable sel = function.newVariable(I32);
            bb.add(new Call(sel, LLVM_EH_SELECTOR, new VariableRef(ehptr), 
                    new ConstantBitcast(NVM_BC_PERSONALITY, I8_PTR), new IntegerConstant(1)));
            for (Trap trap : traps) {
                String exName = trap.getException().getName();
                if ("java.lang.Throwable".equals(exName)) {
                    bb.add(new Br(function.newBasicBlockRef(trap.getHandlerUnit())));
                    continue next;
                }
                Global throwable = throwables.get(exName);
                if (throwable == null) {
                    throwable = new Global(exName, new NullConstant(CLASS_PTR));
                    throwables.put(exName, throwable);
                }
                Variable t = function.newVariable(CLASS_PTR);
                bb.add(new Load(t, new GlobalRef(throwable)));
                Variable v = function.newVariable(I32);
                bb.add(new Call(v, NVM_BC_EXCEPTION_MATCH, ENV, new VariableRef(t)));
                Variable cond = function.newVariable(I1);
                bb.add(new Trunc(cond, new VariableRef(v), I1));
                BasicBlockRef falseBlock = function.newBasicBlockRef(new Object());
                bb.add(new Br(new VariableRef(cond), function.newBasicBlockRef(trap.getHandlerUnit()), falseBlock));
                bb = function.newBasicBlock(falseBlock.getTag());
            }
            
            bb.add(new Call(NVM_BC_RETHROW, ENV));
            bb.add(new Unreachable());
        }
    }

    private Function createFunction(SootMethod method) {
        return createFunction(mangleMethod(method.makeRef()), method);
    }
    
    private Function createFunction(String name, SootMethod method) {
        FunctionType functionType = getFunctionType(method.makeRef());
        String[] parameterNames = new String[functionType.getParameterTypes().length];
        int i = 0;
        parameterNames[i++] = "env";
        if (!method.isStatic()) {
            parameterNames[i++] = "this";
        }
        for (int j = 0; j < method.getParameterCount(); j++) {
            parameterNames[i++] = "p" + j;
        }
            
        return module.newFunction(name, functionType, parameterNames);
    }
    
    private void classLoaderFunction() {
        String name = "NullVM_" + mangleString(sootClass.getName());
        Function function = module.newFunction(name, new FunctionType(CLASS_PTR, ENV_PTR), "env");
        function.newBasicBlock(new Object());
        
        Variable clazz = function.newVariable("clazz", CLASS_PTR);
        Value superclassName = null;
        if (sootClass.hasSuperclass()) {
            superclassName = getString(sootClass.getSuperclass().getName());
        } else {
            superclassName = new NullConstant(I8_PTR);
        }
        function.add(new Call(clazz, NVM_BC_ALLOCATE_CLASS, 
                ENV, 
                getString(sootClass.getName()), 
                superclassName, new IntegerConstant(sootClass.getModifiers()),
                sizeof(classFieldsType), sizeof(instanceFieldsType)));
        for (SootField field : classFields) {
            FunctionRef getter = new FunctionRef(mangleField(field) + "_getter", 
                    new FunctionType(getType(field.getType()), ENV_PTR));
            FunctionRef setter = new FunctionRef(mangleField(field) + "_setter", 
                    new FunctionType(VOID, ENV_PTR, getType(field.getType())));
            function.add(new Call(NVM_BC_ADD_FIELD, ENV, clazz.ref(),
                    getString(field.getName()),
                    getString(getDescriptor(field.getType())),
                    new IntegerConstant(field.getModifiers()),
                    offsetof(classFieldsType, classFields.indexOf(field)),
                    new ConstantBitcast(getter, I8_PTR), 
                    new ConstantBitcast(setter, I8_PTR)));
        }
        for (SootField field : instanceFields) {
            FunctionRef getter = new FunctionRef(mangleField(field) + "_getter", 
                    new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR));
            FunctionRef setter = new FunctionRef(mangleField(field) + "_setter", 
                    new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())));
            function.add(new Call(NVM_BC_ADD_FIELD, ENV, clazz.ref(),
                    getString(field.getName()),
                    getString(getDescriptor(field.getType())),
                    new IntegerConstant(field.getModifiers()),
                    offsetof(instanceFieldsType, instanceFields.indexOf(field)),
                    new ConstantBitcast(getter, I8_PTR), 
                    new ConstantBitcast(setter, I8_PTR)));
        }
        
        function.add(new Ret(clazz.ref()));
    }
    
    private void fieldGetter(SootField field) {
        String name = mangleField(field) + "_getter";
        Function function = null;
        Value fieldPtr = null;
        if (field.isStatic()) {
            function = module.newFunction(name, new FunctionType(getType(field.getType()), ENV_PTR), "env");
            function.newBasicBlock(new Object());
            fieldPtr = getClassFieldPtr(function, field);
        } else {
            function = module.newFunction(name, new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR), "env", "this");
            function.newBasicBlock(new Object());
            fieldPtr = getInstanceFieldPtr(function, new VariableRef("this", OBJECT_PTR), field);
        }
        Variable result = function.newVariable(getType(field.getType()));
        function.add(new Load(result, fieldPtr));
        function.add(new Ret(new VariableRef(result)));
    }
    
    private void fieldSetter(SootField field) {
        String name = mangleField(field) + "_setter";
        Function function = null;
        Value fieldPtr = null;
        if (field.isStatic()) {
            function = module.newFunction(name, new FunctionType(VOID, ENV_PTR, getType(field.getType())), "env", "value");
            function.newBasicBlock(new Object());
            fieldPtr = getClassFieldPtr(function, field);
        } else {
            function = module.newFunction(name, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())), "env", "this", "value");
            function.newBasicBlock(new Object());
            fieldPtr = getInstanceFieldPtr(function, new VariableRef("this", OBJECT_PTR), field);
        }
        function.add(new Store(new VariableRef("value", getType(field.getType())), fieldPtr));
        function.add(new Ret());
    }
    
    private void virtualLookupFunction(SootMethod method) {
        String name = mangleMethod(method) + "_lookup";
        Function function = createFunction(name, method);
        FunctionType functionType = function.getType();
        function.newBasicBlock(new Object());
        Variable fptr = function.newVariable(I8_PTR);
        Value nameRef = getString(method.getName());
        Value descRef = getString(getDescriptor(method.makeRef()));
        function.add(new Call(fptr, NVM_BC_LOOKUP_VIRTUAL_METHOD, ENV, new VariableRef("this", OBJECT_PTR), nameRef, descRef));
        Variable f = function.newVariable(functionType);
        function.add(new Bitcast(f, fptr.ref(), function.getType()));
        String[] parameterNames = function.getParameterNames();
        Type[] parameterTypes = function.getType().getParameterTypes();
        Value[] args = new Value[parameterNames.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
        }
        if (function.getType().getReturnType() == VOID) {
            function.add(new Call(f.ref(), args));
            function.add(new Ret());
        } else {
            Variable result = function.newVariable(functionType.getReturnType());
            function.add(new Call(result, f.ref(), args));
            function.add(new Ret(result.ref()));
        }
    }
    
    private static byte[] stringToModifiedUtf8(String unicode) {
        List<Byte> s = new ArrayList<Byte>();
        for (int i = 0; i < unicode.length(); i++) {
            int ch = unicode.charAt(i);
            if (ch == 0) {
                s.add((byte) 0xc0);
                s.add((byte) 0x80);
            } else if (ch < 0x80) {
                s.add((byte) ch);
            } else if(ch < 0x800) {
                int b5_0 = ch & 0x3f;
                int b10_6 = (ch >> 6) & 0x1f;
                s.add((byte) (0xc0 | b10_6));
                s.add((byte) (0x80 | b5_0));
            } else {
                int b5_0 = ch & 0x3f;
                int b11_6 = (ch >> 6) & 0x3f;
                int b15_12 = (ch >> 12) & 0xf;
                s.add((byte) (0xe0 | b15_12));
                s.add((byte) (0x80 | b11_6));
                s.add((byte) (0x80 | b5_0));
            }
        }
        s.add((byte) 0);
        byte[] result = new byte[s.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.get(i);
        }
        return result;
    }
    
    private static List<SootField> getFields(SootClass clazz, boolean ztatic, boolean includeSuper) {
        List<SootField> l = new ArrayList<SootField>();
        if (includeSuper && clazz.hasSuperclass()) {
            l.addAll(getFields(clazz.getSuperclass(), ztatic, true));
        }
        for (SootField f : clazz.getFields()) {
            if (ztatic == f.isStatic()) {
                l.add(f);
            }
        }
        return l;
    }
    
    private static List<SootField> getClassFields(SootClass clazz, boolean includeSuper) {
        return getFields(clazz, true, includeSuper);
    }
    
    private static List<SootField> getInstanceFields(SootClass clazz, boolean includeSuper) {
        return getFields(clazz, false, includeSuper);
    }
    
    private static StructureType getType(String alias, List<SootField> fields) {
        List<Type> types = new ArrayList<Type>();
        for (SootField field : fields) {
            types.add(getType(field.getType()));
        }
        if (!types.isEmpty()) {
            return new StructureType(alias, types.toArray(new Type[types.size()]));
        }
        return null;
    }
    
    private static StructureType getFieldsType(String alias, SootClass clazz, boolean ztatic, boolean includeSuper) {
        List<Type> types = new ArrayList<Type>();
        for (SootField field : getFields(clazz, ztatic, includeSuper)) {
            types.add(getType(field.getType()));
        }
        if (!types.isEmpty()) {
            return new StructureType(alias, types.toArray(new Type[types.size()]));
        }
        return null;
    }
    
    private static boolean ancestorDeclaresMethod(SootClass c, SootMethod method) {
        String name = method.getName();
        List parameterTypes = method.getParameterTypes();
        soot.Type returnType = method.getReturnType();
        while (c.hasSuperclass()) {
            if (c.getSuperclass().declaresMethod(name, parameterTypes, returnType)) {
                return true;
            }
            c = c.getSuperclass();
        }
        return false;
    }
    
    private static Type getType(String desc) {
        switch (desc.charAt(0)) {
        case 'Z': return I8;
        case 'B': return I8;
        case 'S': return I16;
        case 'C': return I16;
        case 'I': return I32;
        case 'J': return I64;
        case 'F': return FLOAT;
        case 'D': return DOUBLE;
        case 'V': return VOID;
        case 'L': return OBJECT_PTR;
        case '[': return OBJECT_PTR;
        }
        throw new IllegalArgumentException();
    }
    
    private static Type getType(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return Type.I8;
        } else if (sootType.equals(soot.ByteType.v())) {
            return Type.I8;
        } else if (sootType.equals(soot.ShortType.v())) {
            return Type.I16;
        } else if (sootType.equals(soot.CharType.v())) {
            return Type.I16;
        } else if (sootType.equals(soot.IntType.v())) {
            return Type.I32;
        } else if (sootType.equals(soot.LongType.v())) {
            return Type.I64;
        } else if (sootType.equals(soot.FloatType.v())) {
            return Type.FLOAT;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return Type.DOUBLE;
        } else if (sootType.equals(soot.VoidType.v())) {
            return Type.VOID;
        } else if (sootType instanceof soot.RefLikeType) {
            return OBJECT_PTR;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    private static FunctionRef getArrayLoad(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SALOAD;
        } else if (sootType.equals(soot.CharType.v())) {
            return CALOAD;
        } else if (sootType.equals(soot.IntType.v())) {
            return IALOAD;
        } else if (sootType.equals(soot.LongType.v())) {
            return LALOAD;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FALOAD;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DALOAD;
        } else if (sootType instanceof soot.RefLikeType) {
            return AALOAD;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    private static FunctionRef getArrayStore(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SASTORE;
        } else if (sootType.equals(soot.CharType.v())) {
            return CASTORE;
        } else if (sootType.equals(soot.IntType.v())) {
            return IASTORE;
        } else if (sootType.equals(soot.LongType.v())) {
            return LASTORE;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FASTORE;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DASTORE;
        } else if (sootType instanceof soot.RefLikeType) {
            return AASTORE;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    private static FunctionRef getNewArray(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return NVM_BC_NEWARRAYZ;
        } else if (sootType.equals(soot.ByteType.v())) {
            return NVM_BC_NEWARRAYB;
        } else if (sootType.equals(soot.ShortType.v())) {
            return NVM_BC_NEWARRAYS;
        } else if (sootType.equals(soot.CharType.v())) {
            return NVM_BC_NEWARRAYC;
        } else if (sootType.equals(soot.IntType.v())) {
            return NVM_BC_NEWARRAYI;
        } else if (sootType.equals(soot.LongType.v())) {
            return NVM_BC_NEWARRAYJ;
        } else if (sootType.equals(soot.FloatType.v())) {
            return NVM_BC_NEWARRAYF;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return NVM_BC_NEWARRAYD;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    private Value cast(Context ctx, Value value, Type targetType, soot.Type sootSourceType) {
        value = widen(ctx, value, targetType, sootSourceType);
        if (value.isInteger() && targetType instanceof IntegerType) {
            IntegerType t1 = (IntegerType) targetType;
            IntegerType t2 = (IntegerType) value.getType();
            if (t1.getBits() < t2.getBits()) {
                Variable result = ctx.f().newVariable(targetType);
                ctx.f().add(new Trunc(result, value, targetType));
                return new VariableRef(result);                
            }
        }
        return value;
    }
    
    private Value widen(Context ctx, Value value, Type targetType, soot.Type sootSourceType) {
        /*
         * Soot only emits CastExpr for widening integer conversions when the 
         * target type is long or if the source type doesn't fit in the target
         * type (e.g. char -> short).
         */
        if (value.isInteger() && targetType instanceof IntegerType) {
            IntegerType t1 = (IntegerType) targetType;
            IntegerType t2 = (IntegerType) value.getType();
            if (t1.getBits() > t2.getBits()) {
                Variable result = ctx.f().newVariable(targetType);
                if (sootSourceType.equals(CharType.v())) {
                    ctx.f().add(new Zext(result, value, targetType));
                } else {
                    ctx.f().add(new Sext(result, value, targetType));                    
                }
                return new VariableRef(result);
            }
        }
        return value;
    }
    
    private void addTrampoline(Trampoline trampoline, FunctionType functionType) {
        if (!trampolines.containsKey(trampoline)) {
            String functionName = trampoline.getClass().getSimpleName() + "_" 
                                + mangleString(trampoline.toString());
            FunctionRef ref = new FunctionRef(functionName, functionType);
            trampolines.put(trampoline, ref);
        }
    }
    
    private static String getStringVarName(byte[] bytes) {
        StringBuilder sb = new StringBuilder("\"str_");
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b < ' ' || b > '~' || b == '"' || b == '\\' || b == '%' || b == '@') {
                sb.append(String.format("%%%02X", b));
            } else {
                sb.append((char) b);
            }
        }
        sb.append("\"");
        return sb.toString();
    }
    
    private Constant getString(String string) {
        Global g = strings.get(string);
        if (g == null) {
            byte[] modUtf8 = stringToModifiedUtf8(string);
            g = new Global(getStringVarName(modUtf8), new StringConstant(modUtf8));
            strings.put(string, g);
        }
        return new ConstantGetelementptr(new GlobalRef(g), 0, 0);
    }
    
    protected Value immediate(Context ctx, Immediate v) {
        // v is either a soot.Local or a soot.jimple.Constant
        if (v instanceof soot.Local) {
            Local local = (Local) v;
            VariableRef var = new VariableRef(local.getName(), new PointerType(getType(v.getType())));
            Variable tmp = ctx.f().newVariable(getType(v.getType()));
            ctx.f().add(new Load(tmp, var));
            return new VariableRef(tmp);
//            return new VariableRef(((soot.Local) v).getName(), getType(v.getType()));
        } else if (v instanceof soot.jimple.IntConstant) {
            return new IntegerConstant(((soot.jimple.IntConstant) v).value);
        } else if (v instanceof soot.jimple.LongConstant) {
            return new IntegerConstant(((soot.jimple.LongConstant) v).value);
        } else if (v instanceof soot.jimple.FloatConstant) {
            return new FloatingPointConstant(((soot.jimple.FloatConstant) v).value);
        } else if (v instanceof soot.jimple.DoubleConstant) {
            return new FloatingPointConstant(((soot.jimple.DoubleConstant) v).value);
        } else if (v instanceof soot.jimple.NullConstant) {
            return new NullConstant(OBJECT_PTR);
        } else if (v instanceof soot.jimple.StringConstant) {
            String s = ((soot.jimple.StringConstant) v).value;
            Value string = getString(s);
            Variable tmp = ctx.f().newVariable(OBJECT_PTR);
            callOrInvoke(ctx, new Object(), tmp, NVM_BC_LDC_STRING, ENV, string);
            return new VariableRef(tmp);
        }
        throw new IllegalArgumentException("Unknown Immediate type: " + v.getClass());
    }

    private boolean canAccessDirectly(Context context, FieldRef ref) {
        if (ref.getFieldRef().declaringClass().equals(sootClass)) {
            /* 
             * The field ref refers to the current class. Resolve the field and
             * check that it is actually declared in the current class.
             */
            SootField field;
            try {
                field = ref.getField();
            } catch (ResolutionFailedException e) {
                return false;
            }
            if (!field.getDeclaringClass().equals(sootClass) || field.isPhantom()) {
                return false;
            }
            if (field.isStatic()) {
                // Static fields have to be accessed using getstatic/putstatic.
                // If not we want an exception to be thrown so we need a trampoline.
                return ref instanceof StaticFieldRef;
            }
            // Instance fields have to be accessed using getfield/putfield.
            // If not we want an exception to be thrown so we need a trampoline.
            return ref instanceof InstanceFieldRef;
        }
        return false;
    }
    
    private boolean canCallDirectly(Context context, InvokeExpr expr) {
        SootMethodRef methodRef = expr.getMethodRef();
        if (methodRef.declaringClass().equals(sootClass)) {
            /* 
             * The method ref refers to the current class. Resolve the method and
             * check that it is actually declared in the current class.
             */
            SootMethod method;
            try {
                method = methodRef.resolve();
            } catch (ResolutionFailedException e) {
                return false;
            }
            if (!method.getDeclaringClass().equals(sootClass) || method.isAbstract() || method.isPhantom()) {
                return false;
            }
            /*
             * The method exists and isn't abstract. Non virtual (invokespecial) 
             * as well as static calls and calls to final methods can be done directly.
             */
            if (method.isStatic()) {
                // Static methods must be called using invokestatic. If not we 
                // want an exception to be thrown so we need a trampoline.
                return expr instanceof StaticInvokeExpr;
            }
            if (expr instanceof SpecialInvokeExpr) {
                return true;
            }
            if (expr instanceof VirtualInvokeExpr) {
                // Either the class or the method have to be final
                return Modifier.isFinal(sootClass.getModifiers()) || Modifier.isFinal(method.getModifiers());
            }
        }
        return false;
    }
    
    private void callOrInvoke(Context context, Variable result, Value function, Value ... args) {
        callOrInvoke(context, context.getNextUnit(), result, function, args);
    }
    
    private void callOrInvoke(Context ctx, Object tag, Variable result, Value function, Value ... args) {
        if (ctx.hasTrap(ctx.getCurrentUnit())) {
            BasicBlockRef to = ctx.f().newBasicBlockRef(tag);
            BasicBlockRef unwind = ctx.f().newBasicBlockRef(ctx.getCurrentTraps());
            ctx.f().add(new Invoke(result, function, to, unwind, args));
            ctx.f().newBasicBlock(tag);
            ctx.recordCurrentTraps();
        } else {
            ctx.f().add(new Call(result, function, args));
        }
    }
    
    private void callOrInvokeTrampoline(Context ctx, Trampoline trampoline, Variable result, Value ... args) {
        FunctionRef f = trampolines.get(trampoline);

        List<Value> newArgs = new ArrayList<Value>(args.length + 1);
        newArgs.add(ENV);
        newArgs.addAll(Arrays.asList(args));
        callOrInvoke(ctx, result, f, newArgs.toArray(new Value[newArgs.size()]));
        
//        Variable p2 = ctx.f().newVariable(new PointerType(I8_PTR));
//        ctx.f().add(new Getelementptr(p2, new VariableRef("trampolines", new PointerType(I8_PTR)), index));
//        Variable p3 = ctx.f().newVariable(I8_PTR);
//        ctx.f().add(new Load(p3, new VariableRef(p2)));
//        Variable p4 = ctx.f().newVariable(functionType);
//        ctx.f().add(new Bitcast(p4, new VariableRef(p3), p4.getType()));
//        Variable p5 = ctx.f().newVariable(I8_PTR);
//        ctx.f().add(new Bitcast(p5, new VariableRef(p2), p5.getType()));
//        List<Value> newArgs = new ArrayList<Value>(args.length + 2);
//        newArgs.add(new VariableRef(p5));
//        newArgs.add(ENV);
//        newArgs.addAll(Arrays.asList(args));
//        callOrInvoke(ctx, result, new VariableRef(p4), newArgs.toArray(new Value[newArgs.size()]));
    }
    
    private void invokeExpr(Context ctx, Variable result, Stmt stmt, InvokeExpr expr) {
        SootMethodRef methodRef = expr.getMethodRef();
        ArrayList<Value> args = new ArrayList<Value>();
        if (!(expr instanceof StaticInvokeExpr)) {
            Value base = immediate(ctx, (Immediate) ((InstanceInvokeExpr) expr).getBase());
            checkNull(ctx, base);
            args.add(base);
        }
        int paramIndex = 0;
        for (soot.Value sootArg : (List<soot.Value>) expr.getArgs())  {
            Value arg = immediate(ctx, (Immediate) sootArg);
            soot.Type paramType = methodRef.parameterType(paramIndex++);
            arg = cast(ctx, arg, getType(paramType), sootArg.getType());
            args.add(arg);
        }
        if (canCallDirectly(ctx, expr)) {
            args.add(0, ENV);
            Value function = new FunctionRef(mangleMethod(methodRef), getFunctionType(methodRef));
            callOrInvoke(ctx, result, function, args.toArray(new Value[0]));
        } else {
            Trampoline trampoline = null;
            String targetClassName = getInternalName(methodRef.declaringClass());
            String methodName = methodRef.name();
            String methodDesc = getDescriptor(methodRef);
            if (expr instanceof SpecialInvokeExpr) {
                trampoline = new Invokespecial(targetClassName, methodName, methodDesc);
            } else if (expr instanceof StaticInvokeExpr) {
                trampoline = new Invokestatic(targetClassName, methodName, methodDesc);
            } else if (expr instanceof VirtualInvokeExpr) {
                trampoline = new Invokevirtual(targetClassName, methodName, methodDesc);
            } else if (expr instanceof InterfaceInvokeExpr) {
                trampoline = new Invokeinterface(targetClassName, methodName, methodDesc);
            }
            addTrampoline(trampoline, getFunctionType(methodRef));
            callOrInvokeTrampoline(ctx, trampoline, result, args.toArray(new Value[0]));
        }
    }

    private void checkNull(Context ctx, Value base) {
        Stmt stmt = (Stmt) ctx.getCurrentUnit();
        NullCheckTag nullCheckTag = (NullCheckTag) stmt.getTag("NullCheckTag");
        if (nullCheckTag == null || nullCheckTag.needCheck()) {
            callOrInvoke(ctx, new Object(), null, CHECK_NULL, ENV, base);
        }
    }
    
    private void checkBounds(Context ctx, Value base, Value index) {
        Stmt stmt = (Stmt) ctx.getCurrentUnit();
        ArrayCheckTag arrayCheckTag = (ArrayCheckTag) stmt.getTag("ArrayCheckTag");
        if (arrayCheckTag == null || arrayCheckTag.isCheckLower()) {
            callOrInvoke(ctx, new Object(), null, CHECK_LOWER, ENV, base, index);
        }
        if (arrayCheckTag == null || arrayCheckTag.isCheckUpper()) {
            callOrInvoke(ctx, new Object(), null, CHECK_UPPER, ENV, base, index);
        }
    }
    
    private static Constant sizeof(StructureType type) {
        return new ConstantPtrtoint(
                new ConstantGetelementptr(new NullConstant(
                        new PointerType(type)), 1), I32);
    }
    
    private static Constant offsetof(StructureType type, int index) {
        return new ConstantPtrtoint(
                new ConstantGetelementptr(new NullConstant(
                        new PointerType(type)), 0, index), I32);
    }
    
    private static Constant neg(Constant constant) {
        return new ConstantSub(new IntegerConstant(0), constant);
    }
    
    private Value getClassFieldPtr(Function f, SootField field) {
        Variable base = f.newVariable(CLASS_PTR);
        f.add(new Load(base, new GlobalRef(THE_CLASS)));
        return getFieldPtr(f, new VariableRef(base), sizeof(CLASS), field, classFields, classFieldsType);
    }
    
    private Value getInstanceFieldPtr(Function f, Value base, SootField field) {
        return getFieldPtr(f, base, sizeof(OBJECT), field, allInstanceFields, instanceFieldsType);
    }
    
    private Value getFieldPtr(Function f, Value base, Constant baseOffset, SootField field, List<SootField> fields, StructureType fieldsType) {
        int index = fields.indexOf(field);
        Value offset = new ConstantAdd(baseOffset, offsetof(fieldsType, index));
        Variable baseI8Ptr = f.newVariable(I8_PTR);
        f.add(new Bitcast(baseI8Ptr, base, I8_PTR));
        Variable fieldI8Ptr = f.newVariable(I8_PTR);
        f.add(new Getelementptr(fieldI8Ptr, new VariableRef(baseI8Ptr), offset));
        Variable fieldPtr = f.newVariable(new PointerType(getType(field.getType())));
        f.add(new Bitcast(fieldPtr, new VariableRef(fieldI8Ptr), fieldPtr.getType()));
        return new VariableRef(fieldPtr);
    }
    
    private void assign(Context ctx, DefinitionStmt stmt) {
        /*
         * leftOp is either a Local, an ArrayRef or a FieldRef
         * rightOp is either a Local, a Ref, or an Expr
         */

        soot.Value leftOp = stmt.getLeftOp();
        Type leftType = getType(leftOp.getType());
        soot.Value rightOp = stmt.getRightOp();
        Type rightType = getType(rightOp.getType());
        Variable result = null;
//        if (leftOp instanceof Local && rightType.equals(leftType)) {
//            result = new Variable(((Local) leftOp).getName(), leftType);
//        } else {
            result = ctx.f().newVariable(rightType);
//        }

        if (rightOp instanceof Immediate) {
            Immediate immediate = (Immediate) rightOp;
            ctx.f().add(new Bitcast(result, immediate(ctx, immediate), rightType));
        } else if (rightOp instanceof ThisRef) {
            Value thiz = new VariableRef("this", rightType);
            ctx.f().add(new Bitcast(result, thiz, rightType));
        } else if (rightOp instanceof ParameterRef) {
            Value p = new VariableRef("p" + ((ParameterRef) rightOp).getIndex(), rightType);
            ctx.f().add(new Bitcast(result, p, rightType));
        } else if (rightOp instanceof CaughtExceptionRef) {
            ctx.f().add(new Call(result, NVM_BC_EXCEPTION_CLEAR, ENV));
        } else if (rightOp instanceof ArrayRef) {
            ArrayRef ref = (ArrayRef) rightOp;
            VariableRef base = (VariableRef) immediate(ctx, (Immediate) ref.getBase());
            Value index = widen(ctx, immediate(ctx, (Immediate) ref.getIndex()), I32, ref.getIndex().getType());
            checkNull(ctx, base);
            checkBounds(ctx, base, index);
            callOrInvoke(ctx, result, getArrayLoad(rightOp.getType()), base, index);
        } else if (rightOp instanceof InstanceFieldRef) {
            InstanceFieldRef ref = (InstanceFieldRef) rightOp;
            Value base = immediate(ctx, (Immediate) ref.getBase());
            checkNull(ctx, base);
            if (canAccessDirectly(ctx, ref)) {
                ctx.f().add(new Load(result, getInstanceFieldPtr(ctx.f(), base, ref.getField())));
            } else {
                Trampoline trampoline = new GetField(getInternalName(ref.getFieldRef().declaringClass()), 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(rightType, ENV_PTR, OBJECT_PTR));
                callOrInvokeTrampoline(ctx, trampoline, result, base);
            }
        } else if (rightOp instanceof StaticFieldRef) {
            StaticFieldRef ref = (StaticFieldRef) rightOp;
            if (canAccessDirectly(ctx, ref)) {
                ctx.f().add(new Load(result, getClassFieldPtr(ctx.f(), ref.getField())));
            } else {
                Trampoline trampoline = new GetStatic(getInternalName(ref.getFieldRef().declaringClass()), 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(rightType, ENV_PTR));
                callOrInvokeTrampoline(ctx, trampoline, result);
            }
        } else if (rightOp instanceof Expr) {
            if (rightOp instanceof BinopExpr) {
                BinopExpr expr = (BinopExpr) rightOp;
                Value op1 = immediate(ctx, (Immediate) expr.getOp1());
                Value op2 = immediate(ctx, (Immediate) expr.getOp2());
                op1 = widen(ctx, op1, rightType, expr.getOp1().getType());
                op2 = widen(ctx, op2, rightType, expr.getOp2().getType());
                if (rightOp instanceof AddExpr) {
                    ctx.f().add(new Add(result, op1, op2));
                } else if (rightOp instanceof AndExpr) {
                    ctx.f().add(new And(result, op1, op2));
                } else if (rightOp instanceof CmpExpr) {
                    Variable t1 = ctx.f().newVariable(I1);
                    Variable t2 = ctx.f().newVariable(I1);
                    Variable t3 = ctx.f().newVariable(result.getType());
                    Variable t4 = ctx.f().newVariable(result.getType());
                    ctx.f().add(new Icmp(t1, Condition.slt, op1, op2));
                    ctx.f().add(new Icmp(t2, Condition.sgt, op1, op2));
                    ctx.f().add(new Sext(t3, new VariableRef(t1), result.getType()));
                    ctx.f().add(new Sext(t4, new VariableRef(t2), result.getType()));
                    ctx.f().add(new Sub(result, new VariableRef(t4), new VariableRef(t3)));
                } else if (rightOp instanceof DivExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LDIV : IDIV;
                        callOrInvoke(ctx, result, f, ENV, op1, op2);
                    } else {
                        // float or double
                        ctx.f().add(new Fdiv(result, op1, op2));
                    }
                } else if (rightOp instanceof MulExpr) {
                    ctx.f().add(new Mul(result, op1, op2));
                } else if (rightOp instanceof OrExpr) {
                    ctx.f().add(new Or(result, op1, op2));
                } else if (rightOp instanceof RemExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LREM : IREM;
                        callOrInvoke(ctx, result, f, ENV, op1, op2);
                    } else {
                        // float or double
                        ctx.f().add(new Frem(result, op1, op2));
                    }
                } else if (rightOp instanceof ShlExpr || rightOp instanceof ShrExpr  || rightOp instanceof UshrExpr) {
                    // leftOp is either int or long
                    IntegerType type = (IntegerType) leftType;
                    int bits = type.getBits();
                    op1 = widen(ctx, op1, type, expr.getOp1().getType());
                    op2 = widen(ctx, op2, type, expr.getOp2().getType());
                    Variable t = ctx.f().newVariable(type);
                    ctx.f().add(new And(t, op2, new IntegerConstant(bits - 1, type)));
                    if (rightOp instanceof ShlExpr) {
                        ctx.f().add(new Shl(result, op1, new VariableRef(t)));
                    } else if (rightOp instanceof ShrExpr) {
                        ctx.f().add(new Ashr(result, op1, new VariableRef(t)));
                    } else {
                        ctx.f().add(new Lshr(result, op1, new VariableRef(t)));
                    }
                } else if (rightOp instanceof SubExpr) {
                    ctx.f().add(new Sub(result, op1, op2));
                } else if (rightOp instanceof XorExpr) {
                    ctx.f().add(new Xor(result, op1, op2));
                } else if (rightOp instanceof XorExpr) {
                    ctx.f().add(new Xor(result, op1, op2));
                } else if (rightOp instanceof CmplExpr) {
                    FunctionRef f = rightType == FLOAT ? FCMPL : DCMPL;
                    ctx.f().add(new Call(result, f, op1, op2));
                } else if (rightOp instanceof CmpgExpr) {
                    FunctionRef f = rightType == FLOAT ? FCMPG : DCMPG;
                    ctx.f().add(new Call(result, f, op1, op2));
                } else {
                    throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
                }
//            } else if (rightOp instanceof PhiExpr) {
//                PhiExpr pexpr = (PhiExpr) rightOp;
//                List<VariableRef> vars = new ArrayList<VariableRef>(pexpr.getValues().size());
//                for (soot.Value v : pexpr.getValues()) {
//                    vars.add(new VariableRef(((Local) v).getName(), getType(v.getType())));
//                }
//                ctx.f().add(new Phi(result, vars.toArray(new VariableRef[vars.size()])));
//              } else if (rightOp instanceof UnopExpr) {
//              
            } else if (rightOp instanceof CastExpr) {
                Value op = immediate(ctx, (Immediate) ((CastExpr) rightOp).getOp());
                soot.Type sootTargetType = ((CastExpr) rightOp).getCastType();
                soot.Type sootSourceType = ((CastExpr) rightOp).getOp().getType();
                if (sootTargetType instanceof PrimType) {
                    Type targetType = getType(sootTargetType);
                    Type sourceType = getType(sootSourceType);
                    if (targetType instanceof IntegerType && sourceType instanceof IntegerType) {
                        IntegerType targetIType = (IntegerType) targetType;
                        IntegerType sourceIType = (IntegerType) sourceType;
                        if (sourceIType.getBits() < targetIType.getBits()) {
                            // Widening
                            if (sootSourceType.equals(CharType.v())) {
                                ctx.f().add(new Zext(result, op, targetIType));
                            } else {
                                ctx.f().add(new Sext(result, op, targetIType));
                            }
                        } else if (sourceIType.getBits() == targetIType.getBits()) {
                            ctx.f().add(new Bitcast(result, op, targetIType));
                        } else {
                            // Narrow
                            ctx.f().add(new Trunc(result, op, targetIType));
                        }
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof IntegerType) {
                        if (sootSourceType.equals(CharType.v())) {
                            ctx.f().add(new Uitofp(result, op, targetType));
                        } else {
                            ctx.f().add(new Sitofp(result, op, targetType));
                        }
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof FloatingPointType) {
                        if (targetType == FLOAT && sourceType == DOUBLE) {
                            ctx.f().add(new Fptrunc(result, op, targetType));
                        } else if (targetType == DOUBLE && sourceType == FLOAT) {
                            ctx.f().add(new Fpext(result, op, targetType));
                        } else {
                            ctx.f().add(new Bitcast(result, op, targetType));
                        }
                    } else {
                        // F2I, F2L, D2I, D2L
                        FunctionRef f = null;
                        if (targetType == I32 && sourceType == FLOAT) {
                            f = F2I;
                        } else if (targetType == I64 && sourceType == FLOAT) {
                            f = F2L;
                        } else if (targetType == I32 && sourceType == DOUBLE) {
                            f = D2I;
                        } else if (targetType == I64 && sourceType == DOUBLE) {
                            f = D2L;
                        }
                        ctx.f().add(new Call(result, f, op));
                    }
                } else {
                    Trampoline trampoline = new Checkcast(getInternalName(sootTargetType));
                    addTrampoline(trampoline, new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR));
                    callOrInvokeTrampoline(ctx, trampoline, result, op);
                }
            } else if (rightOp instanceof InstanceOfExpr) {
                Value op = immediate(ctx, (Immediate) ((InstanceOfExpr) rightOp).getOp());
                Trampoline trampoline = new Instanceof(getInternalName(((InstanceOfExpr) rightOp).getCheckType()));
                addTrampoline(trampoline, new FunctionType(I8, ENV_PTR, OBJECT_PTR));
                callOrInvokeTrampoline(ctx, trampoline, result, op);
            } else if (rightOp instanceof NewExpr) {
                Trampoline trampoline = new New(getInternalName(((NewExpr) rightOp).getBaseType()));
                addTrampoline(trampoline, new FunctionType(OBJECT_PTR, ENV_PTR));
                callOrInvokeTrampoline(ctx, trampoline, result);
            } else if (rightOp instanceof NewArrayExpr) {
                NewArrayExpr expr = (NewArrayExpr) rightOp;
                Value size = immediate(ctx, (Immediate) expr.getSize());
                callOrInvoke(ctx, result, getNewArray(expr.getBaseType()), ENV, size);
            } else if (rightOp instanceof InvokeExpr) {
                invokeExpr(ctx, result, stmt, (InvokeExpr) rightOp);
            } else if (rightOp instanceof LengthExpr) {
                Value op = immediate(ctx, (Immediate) ((LengthExpr) rightOp).getOp());
                checkNull(ctx, op);
                ctx.f().add(new Call(result, ARRAY_LENGTH, op));
            } else {
                throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
            }
        } else {
            throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
        }

        Value resultRef = new VariableRef(result);
        resultRef = cast(ctx, resultRef, leftType, rightOp.getType());
        
        if (leftOp instanceof Local) {
//            if (!rightType.equals(leftType)) {
            Local local = (Local) leftOp;
            VariableRef v = new VariableRef(local.getName(), new PointerType(leftType));
//            ctx.f().add(new Bitcast(v, resultRef, leftType));
            ctx.f().add(new Store(resultRef, v));
//            }
        } else if (leftOp instanceof ArrayRef) {
            ArrayRef ref = (ArrayRef) leftOp;
            VariableRef base = (VariableRef) immediate(ctx, (Immediate) ref.getBase());
            Value index = widen(ctx, immediate(ctx, (Immediate) ref.getIndex()), I32, ref.getIndex().getType());
            checkNull(ctx, base);
            checkBounds(ctx, base, index);
            callOrInvoke(ctx, null, getArrayStore(leftOp.getType()), base, index, resultRef);
        } else if (leftOp instanceof InstanceFieldRef) {
            InstanceFieldRef ref = (InstanceFieldRef) leftOp;
            Value base = immediate(ctx, (Immediate) ref.getBase());
            checkNull(ctx, base);
            if (canAccessDirectly(ctx, ref)) {
                ctx.f().add(new Store(resultRef, getInstanceFieldPtr(ctx.f(), base, ref.getField())));
            } else {
                Trampoline trampoline = new PutField(getInternalName(ref.getFieldRef().declaringClass()), 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, leftType));
                callOrInvokeTrampoline(ctx, trampoline, null, base, resultRef);
            }
        } else if (leftOp instanceof StaticFieldRef) {
            StaticFieldRef ref = (StaticFieldRef) leftOp;
            if (canAccessDirectly(ctx, ref)) {
                ctx.f().add(new Store(resultRef, getClassFieldPtr(ctx.f(), ref.getField())));
            } else {
                Trampoline trampoline = new PutStatic(getInternalName(ref.getFieldRef().declaringClass()), 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(VOID, ENV_PTR, leftType));
                callOrInvokeTrampoline(ctx, trampoline, null, resultRef);
            }
        } else {
            throw new IllegalArgumentException("Unknown type for leftOp: " + leftOp.getClass());
        }
    }
    
    private void return_(Context ctx, ReturnStmt stmt) {
        /*
         * op is an Immediate.
         */
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        Value value = cast(ctx, op, ctx.f().getType().getReturnType(), stmt.getOp().getType());
        ctx.f().add(new Ret(value));
    }
    
    private void returnVoid(Context ctx) {
        ctx.f().add(new Ret());
    }
    
    private void if_(Context ctx, IfStmt stmt) {
        ConditionExpr condition = (ConditionExpr) stmt.getCondition();
        Value op1 = immediate(ctx, (Immediate) condition.getOp1());
        Value op2 = immediate(ctx, (Immediate) condition.getOp2());
        op1 = widen(ctx, op1, Type.I32, condition.getOp2().getType());
        op2 = widen(ctx, op2, Type.I32, condition.getOp1().getType());
        Icmp.Condition c = null;
        if (condition instanceof EqExpr) {
            c = Icmp.Condition.eq;
        } else if (condition instanceof NeExpr) {
            c = Icmp.Condition.ne;
        } else if (condition instanceof GtExpr) {
            c = Icmp.Condition.sgt;
        } else if (condition instanceof LtExpr) {
            c = Icmp.Condition.slt;
        } else if (condition instanceof GeExpr) {
            c = Icmp.Condition.sge;
        } else if (condition instanceof LeExpr) {
            c = Icmp.Condition.sle;
        }
        Variable result = ctx.f().newVariable(Type.I1);
        ctx.f().add(new Icmp(result, c, op1, op2));
        ctx.f().add(new Br(new VariableRef(result), 
                ctx.f().newBasicBlockRef(stmt.getTarget()), 
                ctx.f().newBasicBlockRef(ctx.getNextUnit())));
        ctx.f().newBasicBlock(ctx.getNextUnit());
    }
    
    private void goto_(Context ctx, GotoStmt stmt) {
        ctx.f().add(new Br(ctx.f().newBasicBlockRef(stmt.getTarget())));
    }
    
    private void throw_(Context ctx, ThrowStmt stmt) {
        Value obj = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, obj);
        if (ctx.hasTrap(stmt)) {
            ctx.f().add(new Call(NVM_BC_EXCEPTION_SET, ENV, obj));
            ctx.f().add(new Br(ctx.f().newBasicBlockRef(ctx.getCurrentTraps())));
        } else {
            ctx.f().add(new Call(NVM_BC_THROW, ENV, obj));
            ctx.f().add(new Unreachable());
        }
    }
    
    private void invoke(Context context, InvokeStmt stmt) {
        invokeExpr(context, null, stmt, stmt.getInvokeExpr());
    }
    
    private void enterMonitor(Context ctx, EnterMonitorStmt stmt) {
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, op);
        callOrInvoke(ctx, null, NVM_BC_ENTER_MONITOR, ENV, op);
    }
    
    private void exitMonitor(Context ctx, ExitMonitorStmt stmt) {
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, op);
        callOrInvoke(ctx, null, NVM_BC_EXIT_MONITOR, ENV, op);
    }
    
    private static String getInternalName(soot.Type t) {
        if (t instanceof soot.ArrayType) {
            return getDescriptor(t);
        } else if (t instanceof soot.RefType) {
            RefType rt = (RefType) t;
            return rt.getClassName().replace('.', '/');
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    private static String getInternalName(SootClass sc) {
        return sc.getName().replace('.', '/');
    }
    
    private static String getDescriptor(soot.Type t) {
        if (t instanceof PrimType) {
            if (t.equals(BooleanType.v())) {
                return "Z";
            } else if (t.equals(ByteType.v())) {
                return "B";
            } else if (t.equals(ShortType.v())) {
                return "S";
            } else if (t.equals(CharType.v())) {
                return "C";
            } else if (t.equals(IntType.v())) {
                return "I";
            } else if (t.equals(LongType.v())) {
                return "J";
            } else if (t.equals(FloatType.v())) {
                return "F";
            } else {
                // DoubleType
                return "D";
            }
        } else if (t.equals(VoidType.v())) {
            return "V";
        } else if (t instanceof soot.ArrayType) {
            soot.ArrayType at = (soot.ArrayType) t;
            return "[" + getDescriptor(at.getElementType());
        } else {
            // RefType
            RefType rt = (RefType) t;
            return "L" + rt.getClassName().replace('.', '/') + ";";
        }
    }
    
    private static String getDescriptor(SootMethodRef methodRef) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (soot.Type t : (List<soot.Type>) methodRef.parameterTypes()) {
            sb.append(getDescriptor(t));
        }
        sb.append(')');
        sb.append(getDescriptor(methodRef.returnType()));
        return sb.toString();
    }
    
    private static boolean isNative(SootMethod sm) {
        if (sm.isNative()) {
            return true;
        }
        // TODO: Check for @Native annotation
        return false;
    }
    
    private static FunctionType getFunctionType(String desc) {
        List<Type> paramTypes = new ArrayList<Type>();
        int i = 1;
        while (true) {
            char c = desc.charAt(i);
            paramTypes.add(getType(desc.substring(i, 1)));
            while (c == '[') {
                c = desc.charAt(++i);
            }
            if (c == 'L') {
                while (c != ';') {
                    c = desc.charAt(++i);
                }
            }
            i++;
        }
    }
    
    private static FunctionType getFunctionType(SootMethodRef methodRef) {
        Type returnType = getType(methodRef.returnType());
        Type[] paramTypes = new Type[(methodRef.isStatic() ? 1 : 2) + methodRef.parameterTypes().size()];
        int i = 0;
        paramTypes[i++] = ENV_PTR;
        if (!methodRef.isStatic()) {
            paramTypes[i++] = OBJECT_PTR;
        }
        for (soot.Type t : (List<soot.Type>) methodRef.parameterTypes()) {
            paramTypes[i++] = getType(t);
        }
        return new FunctionType(returnType, paramTypes);
    }
    
    private static String mangleMethod(SootMethod method) {
        return mangleMethod(method.makeRef());
    }
    
    private static String mangleMethod(SootMethodRef methodRef) {
        return mangleMethod(methodRef.declaringClass().getName(), methodRef.name(), 
                methodRef.parameterTypes(), methodRef.returnType());
    }
    
    private static String mangleMethod(String owner, String name, List<soot.Type> parameterTypes, soot.Type returnType) {
        StringBuilder sb = new StringBuilder();
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        if (!parameterTypes.isEmpty()) {
            sb.append("__");
            for (soot.Type parameterType : parameterTypes) {
                sb.append(mangleString(getDescriptor(parameterType)));
            }
        }
        sb.append("__");
        sb.append(mangleString(getDescriptor(returnType)));
        return sb.toString();
    }
    
    private static String mangleField(SootField field) {
        return mangleField(field.getDeclaringClass().getName(), field.getName(), field.getType());
    }
    
    private static String mangleField(String owner, String name, soot.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        sb.append("__");
        sb.append(mangleString(getDescriptor(type)));
        return sb.toString();
    }
    
    private static String mangleString(String name) {
        byte[] s;
        try {
            s = name.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            byte c = s[i];
            if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                sb.append((char) c);
            } else if (c == '/') {
                sb.append('_');
            } else {
                sb.append('$');
                sb.append(HEX_CHARS[(c >> 8) & 0xf]);
                sb.append(HEX_CHARS[c & 0xf]);
            }
        }
        return sb.toString();
    }

    
    public static class HelloWorld {
        private static int foo = 10;
        private long l = 0;
        public static int getFoo() {
            return foo;
        }
        public static long f(byte x) {
            return (100 / x);
        }
        public void setL(long l) {
            this.l = l;
        }
        public long getL() {
            return l;
        }
    }
    
    public static void main(String[] args) throws Exception {
//        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().setPhaseOption("jap.npc", "enabled:true");
        Options.v().setPhaseOption("wjap.ra", "enabled:true");
        Options.v().setPhaseOption("jap.abc", "enabled:true");
        Options.v().setPhaseOption("tag.an", "enabled:true");
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath("../rt/target/classes:target/classes");
//        Options.v().set_soot_classpath("target/classes");

        Scene.v().loadClassAndSupport("org.nullvm.compiler.SootClassCompiler$HelloWorld").setApplicationClass();
//        Scene.v().loadClassAndSupport("java.lang.Double").setApplicationClass();
        Scene.v().loadNecessaryClasses();
        PackManager.v().runPacks();
        
        SootClass sootClass = Scene.v().getSootClass("org.nullvm.compiler.SootClassCompiler$HelloWorld");
//        SootClass sootClass = Scene.v().getSootClass("java.lang.Double");
        SootClassCompiler compiler = new SootClassCompiler(sootClass);
        Module module = compiler.compile();
        System.out.println(module.toString());
    }
}
