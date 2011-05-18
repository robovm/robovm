/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.llvm.Type.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.Clazzes;
import org.nullvm.compiler.clazz.Path;
import org.nullvm.compiler.llvm.Add;
import org.nullvm.compiler.llvm.Alloca;
import org.nullvm.compiler.llvm.And;
import org.nullvm.compiler.llvm.ArrayType;
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
import org.nullvm.compiler.llvm.ConstantTrunc;
import org.nullvm.compiler.llvm.Fadd;
import org.nullvm.compiler.llvm.Fdiv;
import org.nullvm.compiler.llvm.FloatingPointConstant;
import org.nullvm.compiler.llvm.FloatingPointType;
import org.nullvm.compiler.llvm.Fmul;
import org.nullvm.compiler.llvm.Fpext;
import org.nullvm.compiler.llvm.Fptrunc;
import org.nullvm.compiler.llvm.Frem;
import org.nullvm.compiler.llvm.Fsub;
import org.nullvm.compiler.llvm.Function;
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
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Linkage;
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
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.llvm.Xor;
import org.nullvm.compiler.llvm.Zext;
import org.nullvm.compiler.trampoline.Checkcast;
import org.nullvm.compiler.trampoline.FieldAccessor;
import org.nullvm.compiler.trampoline.GetField;
import org.nullvm.compiler.trampoline.GetStatic;
import org.nullvm.compiler.trampoline.Instanceof;
import org.nullvm.compiler.trampoline.Invokeinterface;
import org.nullvm.compiler.trampoline.Invokespecial;
import org.nullvm.compiler.trampoline.Invokestatic;
import org.nullvm.compiler.trampoline.Invokevirtual;
import org.nullvm.compiler.trampoline.NativeCall;
import org.nullvm.compiler.trampoline.New;
import org.nullvm.compiler.trampoline.PutField;
import org.nullvm.compiler.trampoline.PutStatic;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.Body;
import soot.BodyTransformer;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.FloatType;
import soot.Immediate;
import soot.IntType;
import soot.Local;
import soot.LongType;
import soot.Modifier;
import soot.NullType;
import soot.Pack;
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
import soot.Transform;
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
import soot.jimple.LookupSwitchStmt;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.NegExpr;
import soot.jimple.NewArrayExpr;
import soot.jimple.NewExpr;
import soot.jimple.NewMultiArrayExpr;
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
import soot.jimple.TableSwitchStmt;
import soot.jimple.ThisRef;
import soot.jimple.ThrowStmt;
import soot.jimple.UshrExpr;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.XorExpr;
import soot.jimple.toolkits.annotation.tags.ArrayCheckTag;
import soot.jimple.toolkits.annotation.tags.NullCheckTag;
import soot.jimple.toolkits.typing.fast.BottomType;
import soot.options.Options;
import soot.tagkit.ConstantValueTag;
import soot.tagkit.DoubleConstantValueTag;
import soot.tagkit.FloatConstantValueTag;
import soot.tagkit.IntegerConstantValueTag;
import soot.tagkit.LongConstantValueTag;
import soot.tagkit.StringConstantValueTag;
import soot.tagkit.Tag;

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
    private static final Global THE_CLASS = new Global("class", Linkage._private, new NullConstant(CLASS_PTR));
    
    private static final FunctionRef NVM_BC_ALLOCATE_CLASS = new FunctionRef("_nvmBcAllocateClass", new FunctionType(CLASS_PTR, ENV_PTR, I8_PTR, I8_PTR, OBJECT_PTR, I32, I32, I32));
    private static final FunctionRef NVM_BC_ADD_INTERFACE = new FunctionRef("_nvmBcAddInterface", new FunctionType(VOID, ENV_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_ADD_FIELD = new FunctionRef("_nvmBcAddField", new FunctionType(VOID, ENV_PTR, CLASS_PTR, I8_PTR, I8_PTR, I32, I32, I8_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_ADD_METHOD = new FunctionRef("_nvmBcAddMethod", new FunctionType(VOID, ENV_PTR, CLASS_PTR, I8_PTR, I8_PTR, I32, I32, I8_PTR, I8_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_REGISTER_CLASS = new FunctionRef("_nvmBcRegisterClass", new FunctionType(VOID, ENV_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_FIND_CLASS_IN_LOADER = new FunctionRef("_nvmBcFindClassInLoader", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR, OBJECT_PTR));

    private static final FunctionRef NVM_BC_PERSONALITY = new FunctionRef("_nvmPersonality", new FunctionType(I8_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_MATCH = new FunctionRef("_nvmBcExceptionMatch", new FunctionType(I32, ENV_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_CLEAR = new FunctionRef("_nvmBcExceptionClear", new FunctionType(OBJECT_PTR, ENV_PTR));
    private static final FunctionRef NVM_BC_EXCEPTION_SET = new FunctionRef("_nvmBcExceptionSet", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_THROW = new FunctionRef("_nvmBcThrow", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_RETHROW = new FunctionRef("_nvmBcRethrow", new FunctionType(VOID, ENV_PTR));
    private static final FunctionRef NVM_BC_THROW_IF_EXCEPTION_OCCURRED = new FunctionRef("_nvmBcThrowIfExceptionOccurred", new FunctionType(VOID, ENV_PTR));
    private static final FunctionRef NVM_BC_NEW_BOOLEAN_ARRAY = new FunctionRef("_nvmBcNewBooleanArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_BYTE_ARRAY = new FunctionRef("_nvmBcNewByteArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_CHAR_ARRAY = new FunctionRef("_nvmBcNewCharArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_SHORT_ARRAY = new FunctionRef("_nvmBcNewShortArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_INT_ARRAY = new FunctionRef("_nvmBcNewIntArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_LONG_ARRAY = new FunctionRef("_nvmBcNewLongArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_FLOAT_ARRAY = new FunctionRef("_nvmBcNewFloatArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_NEW_DOUBLE_ARRAY = new FunctionRef("_nvmBcNewDoubleArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32));
    private static final FunctionRef NVM_BC_MONITOR_ENTER = new FunctionRef("_nvmBcMonitorEnter", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_MONITOR_EXIT = new FunctionRef("_nvmBcMonitorExit", new FunctionType(VOID, ENV_PTR, OBJECT_PTR));
    private static final FunctionRef NVM_BC_LDC_STRING = new FunctionRef("_nvmBcLdcString", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_LDC_CLASS = new FunctionRef("_nvmBcLdcClass", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_LOOKUP_VIRTUAL_METHOD = new FunctionRef("_nvmBcLookupVirtualMethod", new FunctionType(I8_PTR, ENV_PTR, CLASS_PTR, OBJECT_PTR, I8_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_LOOKUP_INTERFACE_METHOD = new FunctionRef("_nvmBcLookupInterfaceMethod", new FunctionType(I8_PTR, ENV_PTR, CLASS_PTR, OBJECT_PTR, I8_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_CHECKCAST = new FunctionRef("_nvmBcCheckcast", new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR, I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_INSTANCEOF = new FunctionRef("_nvmBcInstanceof", new FunctionType(I32, ENV_PTR, OBJECT_PTR, I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_NEW = new FunctionRef("_nvmBcNew", new FunctionType(OBJECT_PTR, ENV_PTR, I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_NEW_OBJECT_ARRAY = new FunctionRef("_nvmBcNewObjectArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32, I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_NEW_MULTI_ARRAY = new FunctionRef("_nvmBcNewMultiArray", new FunctionType(OBJECT_PTR, ENV_PTR, I32, new PointerType(I32), I8_PTR, CLASS_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_INVOKESPECIAL = new FunctionRef("_nvmBcResolveInvokespecial", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_INVOKESTATIC = new FunctionRef("_nvmBcResolveInvokestatic", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_INVOKEVIRTUAL = new FunctionRef("_nvmBcResolveInvokevirtual", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_INVOKEINTERFACE = new FunctionRef("_nvmBcResolveInvokeinterface", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_GETSTATIC = new FunctionRef("_nvmBcResolveGetstatic", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_PUTSTATIC = new FunctionRef("_nvmBcResolvePutstatic", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_GETFIELD = new FunctionRef("_nvmBcResolveGetfield", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_PUTFIELD = new FunctionRef("_nvmBcResolvePutfield", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    private static final FunctionRef NVM_BC_RESOLVE_NATIVE = new FunctionRef("_nvmBcResolveNative", new FunctionType(I8_PTR, ENV_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, I8_PTR, CLASS_PTR, I8_PTR));
    
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
    private static final FunctionRef DCMPG = new FunctionRef("dcmpg", new FunctionType(I32, DOUBLE, DOUBLE));

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
    
    private SootClass sootClass;
    
    private Module module;
    private Map<SootClass, Global> throwables;
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
    
    private Variable dims;
    
    public SootClassCompiler() {
    }
    
    public void compile(Clazz clazz, OutputStream out) throws IOException {
        this.sootClass = Scene.v().getSootClass(clazz.getClassName());
        
        module = new Module();
        throwables = new HashMap<SootClass, Global>();
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
        
        if (!sootClass.declaresMethodByName("<clinit>") && hasConstantValueTags(classFields)) {
            Function clinit = module.newFunction(Linkage._private, 
                    mangleMethod(getInternalName(sootClass), "<clinit>", 
                                new ArrayList<soot.Type>(), soot.VoidType.v()), 
                            new FunctionType(VOID, ENV_PTR), "env");
            finalClassFieldsInitializers(clinit);
            clinit.add(new Ret());
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
            if (!method.isAbstract() && method.isSynchronized()) {
                // Create a wrapper function which synchronizes on the class or instance and then calls the actual function
                synchronizedFunctionWrapper(method);
            }
        }
        
        for (Entry<Trampoline, FunctionRef> entry : trampolines.entrySet()) {
            trampoline(entry.getKey(), entry.getValue());
        }
        
        classLoaderFunction();
        
        module.addGlobal(THE_CLASS);
        
        for (Global global : throwables.values()) {
            module.addGlobal(global);
        }
        for (Global global : strings.values()) {
            module.addGlobal(global);
        }
        
        out.write(module.toString().getBytes("UTF-8"));
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
    
    private void synchronizedFunctionWrapper(SootMethod method) {
        String name = mangleMethod(method) + "_synchronized";
        Function function = createFunction(name, method);
        FunctionType functionType = function.getType();
        FunctionRef target = new FunctionRef(mangleMethod(method), functionType);
        Value monitor = null;
        if (method.isStatic()) {
            Value clazz = getCaller(function);
            Variable tmp = function.newVariable(OBJECT_PTR);
            function.add(new Bitcast(tmp, clazz, OBJECT_PTR));
            monitor = tmp.ref();
        } else {
            monitor = new VariableRef("this", OBJECT_PTR);
        }
        function.add(new Call(NVM_BC_MONITOR_ENTER, ENV, monitor));
        String[] parameterNames = function.getParameterNames();
        Type[] parameterTypes = function.getType().getParameterTypes();
        Value[] args = new Value[parameterNames.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
        }
        BasicBlockRef bbSuccess = function.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = function.newBasicBlockRef(new Label("failure"));
        if (function.getType().getReturnType() == VOID) {
            function.add(new Invoke(target, bbSuccess, bbFailure, args));
            function.newBasicBlock(new Label("success"));
            function.add(new Call(NVM_BC_MONITOR_EXIT, ENV, monitor));
            function.add(new Ret());
        } else {
            Variable result = function.newVariable(functionType.getReturnType());
            function.add(new Invoke(result, target, bbSuccess, bbFailure, args));
            function.newBasicBlock(new Label("success"));
            function.add(new Call(NVM_BC_MONITOR_EXIT, ENV, monitor));
            function.add(new Ret(result.ref()));
        }
        function.newBasicBlock(new Label("failure"));
        Variable ehptr = function.newVariable(I8_PTR);
        function.add(new Call(ehptr, LLVM_EH_EXCEPTION));
        Variable sel = function.newVariable(I32);
        function.add(new Call(sel, LLVM_EH_SELECTOR, new VariableRef(ehptr), 
                new ConstantBitcast(NVM_BC_PERSONALITY, I8_PTR), new IntegerConstant(1)));
        function.add(new Call(NVM_BC_MONITOR_EXIT, ENV, monitor));
        function.add(new Call(NVM_BC_RETHROW, ENV));
        function.add(new Unreachable());
    }

    private static boolean isTerminator(Instruction instr) {
        return instr instanceof Ret || instr instanceof Br 
            || instr instanceof Invoke || instr instanceof Unreachable 
            || instr instanceof Switch;
    }
    
    private void trampoline(Trampoline trampoline, FunctionRef functionRef) {
        String name = functionRef.getName().substring(1);
        GlobalRef ptr = new GlobalRef(name + "_ptr", functionRef.getType());

        Type[] parameterTypes = functionRef.getType().getParameterTypes();
        String[] parameterNames = new String[parameterTypes.length];
        parameterNames[0] = ENV.getName().substring(1);
        for (int i = 1; i < parameterNames.length; i++) {
            parameterNames[i] = "p" + i;
        }
        Function function = module.newFunction(Linkage._private, name, functionRef.getType(), parameterNames);
        
        Variable targetI8Ptr = function.newVariable(I8_PTR);
        if (trampoline instanceof org.nullvm.compiler.trampoline.Invoke) {
            org.nullvm.compiler.trampoline.Invoke invoke = (org.nullvm.compiler.trampoline.Invoke) trampoline;
            FunctionRef resolveFunc = null;
            List<Value> args = new ArrayList<Value>();
            args.add(ENV);
            if (invoke instanceof Invokespecial) {
                resolveFunc = NVM_BC_RESOLVE_INVOKESPECIAL;
                args.add(getString(((Invokespecial) invoke).getRuntimeClass()));
            } else if (invoke instanceof Invokestatic) {
                resolveFunc = NVM_BC_RESOLVE_INVOKESTATIC;                
            } else if (invoke instanceof Invokevirtual) {
                resolveFunc = NVM_BC_RESOLVE_INVOKEVIRTUAL;                
                args.add(getString(((Invokevirtual) invoke).getRuntimeClass()));
            } else if (invoke instanceof Invokeinterface) {
                resolveFunc = NVM_BC_RESOLVE_INVOKEINTERFACE;                
            }
            args.add(getString(invoke.getTargetClass())); 
            args.add(getString(invoke.getMethodName()));
            args.add(getString(invoke.getMethodDesc()));
            args.add(getCaller(function));
            args.add(new ConstantBitcast(ptr, I8_PTR));
            function.add(new Call(targetI8Ptr, resolveFunc, args.toArray(new Value[args.size()])));
        } else if (trampoline instanceof NativeCall) {
            NativeCall nativeCall = (NativeCall) trampoline;
            function.add(new Call(targetI8Ptr, NVM_BC_RESOLVE_NATIVE,
                    ENV,
                    getString(nativeCall.getTargetClass()), 
                    getString(nativeCall.getMethodName()),
                    getString(nativeCall.getMethodDesc()),
                    getString(mangleNativeMethod(nativeCall.getTargetClass(), 
                            nativeCall.getMethodName())),
                    getString(mangleNativeMethod(nativeCall.getTargetClass(), 
                            nativeCall.getMethodName(), nativeCall.getMethodDesc())),
                    getCaller(function),
                    new ConstantBitcast(ptr, I8_PTR)));
        } else if (trampoline instanceof FieldAccessor) {
            FieldAccessor accessor = (FieldAccessor) trampoline;
            FunctionRef resolveFunc = null;
            List<Value> args = new ArrayList<Value>();
            args.add(ENV);
            if (accessor instanceof GetStatic) {
                resolveFunc = NVM_BC_RESOLVE_GETSTATIC;
            } else if (accessor instanceof PutStatic) {
                resolveFunc = NVM_BC_RESOLVE_PUTSTATIC;                
            } else if (accessor instanceof GetField) {
                resolveFunc = NVM_BC_RESOLVE_GETFIELD;                
                args.add(getString(((GetField) accessor).getRuntimeClass()));
            } else if (accessor instanceof PutField) {
                resolveFunc = NVM_BC_RESOLVE_PUTFIELD;                
                args.add(getString(((PutField) accessor).getRuntimeClass()));
            }
            args.add(getString(accessor.getTargetClass())); 
            args.add(getString(accessor.getFieldName()));
            args.add(getString(accessor.getFieldDesc()));
            args.add(getCaller(function));
            args.add(new ConstantBitcast(ptr, I8_PTR));
            function.add(new Call(targetI8Ptr, resolveFunc, args.toArray(new Value[args.size()])));
        }

        Variable targetFuncPtr = function.newVariable(functionRef.getType());
        function.add(new Bitcast(targetFuncPtr, targetI8Ptr.ref(), functionRef.getType()));
        
        Value[] args = new Value[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
        }
        
        if (functionRef.getType().getReturnType() != VOID) {
            Variable result = function.newVariable(functionRef.getType().getReturnType());
            function.add(new Call(result, targetFuncPtr.ref(), args));
            function.add(new Ret(result.ref()));
        } else {
            function.add(new Call(targetFuncPtr.ref(), args));
            function.add(new Ret());            
        }
        
//        // TODO: X86-64 specific
//        module.addFunctionDeclaration(new FunctionDeclaration(name, functionRef.getType()));
//        module.addAsm("\t.align 16, 0x90");
//        module.addAsm(name + ":");
//        module.addAsm("\tmovq .L" + name + "_ptr(%rip), %rax");
//        module.addAsm("\tjmpq *(%rax)");
        
        
        
        module.addGlobal(new Global(name + "_ptr", Linkage._private, functionRef));
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

        Type[] parameterTypes = function.getType().getParameterTypes();
        String[] parameterNames = function.getParameterNames();
        ArrayList<Value> args = new ArrayList<Value>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new VariableRef(parameterNames[i], parameterTypes[i]));
        }

        FunctionType nativeFunctionType = function.getType();
        if (method.isStatic()) {
            // Add THE_CLASS as second parameter
            Variable tmp = function.newVariable(CLASS_PTR);
            function.add(new Load(tmp, THE_CLASS.ref()));
            args.add(1, tmp.ref());
            // Add %Class* as second parameter type
            ArrayList<Type> ptypes = new ArrayList<Type>(Arrays.asList(parameterTypes));
            ptypes.add(1, CLASS_PTR);
            nativeFunctionType = new FunctionType(function.getType().getReturnType(), 
                    ptypes.toArray(new Type[ptypes.size()]));
        }        
        
        String targetClassName = getInternalName(method.getDeclaringClass());
        String methodName = method.getName();
        String methodDesc = getDescriptor(method);
        // TODO: NativeCall should take THE_CLASS as first arg since method is 
        // always declared by the class being compiled
        Trampoline trampoline = new NativeCall(targetClassName, methodName, methodDesc);
        addTrampoline(trampoline, nativeFunctionType);
        if (function.getType().getReturnType() == VOID) {
            callTrampoline(function, trampoline, null, args.toArray(new Value[args.size()]));
            function.add(new Call(NVM_BC_THROW_IF_EXCEPTION_OCCURRED, ENV));
            function.add(new Ret());
        } else {
            Variable result = function.newVariable(function.getType().getReturnType());
            callTrampoline(function, trampoline, result, args.toArray(new Value[args.size()]));
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
        
        Set<String> seen = new HashSet<String>();
        for (Unit unit : body.getUnits()) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getLeftOp() instanceof Local) {
                    Local local = (Local) stmt.getLeftOp();
                    if (!seen.contains(local.getName())) {
                        Type type = getLocalType(local.getType());
                        ctx.f().add(new Alloca(ctx.f().newVariable(local.getName(), type), type));
                        seen.add(local.getName());
                    }
                }
            }
        }
        
//        Variable localTrampolines = ctx.f().newVariable("trampolines", new PointerType(I8_PTR));
//        ctx.f().add(new Load(localTrampolines, new GlobalRef("trampolines", new PointerType(new PointerType(I8_PTR)))));
        
        if ("<clinit>".equals(method.getName())) {
            finalClassFieldsInitializers(function);
        }
        
        PatchingChain<Unit> units = body.getUnits();
        
        int multiANewArrayMaxDims = 0;
        for (Unit unit : units) {
            if (unit instanceof DefinitionStmt) {
                DefinitionStmt stmt = (DefinitionStmt) unit;
                if (stmt.getRightOp() instanceof NewMultiArrayExpr) {
                    NewMultiArrayExpr expr = (NewMultiArrayExpr) stmt.getRightOp();
                    multiANewArrayMaxDims = Math.max(multiANewArrayMaxDims, expr.getSizeCount());
                }
            }
        }
        
        dims = null;
        if (multiANewArrayMaxDims > 0) {
//            Variable tmp = ctx.f().newVariable(new ArrayType(multiANewArrayMaxDims, I32));
            dims = ctx.f().newVariable("dims", new PointerType(new ArrayType(multiANewArrayMaxDims, I32)));
            ctx.f().add(new Alloca(dims, new ArrayType(multiANewArrayMaxDims, I32)));
//            ctx.f().add(new Bitcast(dims, tmp.ref(), dims.getType()));
        }
        
        for (Unit unit : units) {
            ctx.setCurrentUnit(unit);
            if (/*ctx.bb().getLabel().getTag() != unit 
                    &&*/ (ctx.isJumpTarget(unit) || ctx.isTrapHandler(unit))) {
                BasicBlock oldBlock = ctx.bb();
                ctx.f().newBasicBlock(new Label(unit));
                if (oldBlock != null) {
                    Instruction last = oldBlock.last();
                    if (last == null || !isTerminator(last)) {
                        oldBlock.add(new Br(ctx.f().newBasicBlockRef(new Label(unit))));
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
            } else if (unit instanceof LookupSwitchStmt) {
                lookupSwitch(ctx, (LookupSwitchStmt) unit);
            } else if (unit instanceof TableSwitchStmt) {
                tableSwitch(ctx, (TableSwitchStmt) unit);
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
            BasicBlock bb = function.newBasicBlock(new Label(traps));
            Variable ehptr = function.newVariable(I8_PTR);
            bb.add(new Call(ehptr, LLVM_EH_EXCEPTION));
            Variable sel = function.newVariable(I32);
            bb.add(new Call(sel, LLVM_EH_SELECTOR, new VariableRef(ehptr), 
                    new ConstantBitcast(NVM_BC_PERSONALITY, I8_PTR), new IntegerConstant(1)));
            for (Trap trap : traps) {
                String exName = trap.getException().getName();
                if ("java.lang.Throwable".equals(exName)) {
                    bb.add(new Br(function.newBasicBlockRef(new Label(trap.getHandlerUnit()))));
                    continue next;
                }
                Global throwable = throwables.get(trap.getException());
                if (throwable == null) {
                    throwable = new Global(exName, Linkage._private, new NullConstant(CLASS_PTR));
                    throwables.put(trap.getException(), throwable);
                }
                Variable t = function.newVariable(CLASS_PTR);
                bb.add(new Load(t, throwable.ref()));
                Variable v = function.newVariable(I32);
                bb.add(new Call(v, NVM_BC_EXCEPTION_MATCH, ENV, new VariableRef(t)));
                Variable cond = function.newVariable(I1);
                bb.add(new Trunc(cond, new VariableRef(v), I1));
                BasicBlockRef falseBlock = function.newBasicBlockRef(new Label());
                bb.add(new Br(new VariableRef(cond), function.newBasicBlockRef(new Label(trap.getHandlerUnit())), falseBlock));
                bb = function.newBasicBlock(falseBlock.getLabel());
            }
            
            bb.add(new Call(NVM_BC_RETHROW, ENV));
            bb.add(new Unreachable());
        }
    }

    /**
     * @param function
     */
    private void finalClassFieldsInitializers(Function function) {
        for (SootField field : classFields) {
            if (field.isFinal()) {
                for (Tag tag : field.getTags()) {
                    if (tag instanceof DoubleConstantValueTag) {
                        DoubleConstantValueTag dtag = (DoubleConstantValueTag) tag;
                        function.add(new Store(new FloatingPointConstant(dtag.getDoubleValue()), getClassFieldPtr(function, field)));
                    } else if (tag instanceof FloatConstantValueTag) {
                        FloatConstantValueTag ftag = (FloatConstantValueTag) tag;
                        function.add(new Store(new FloatingPointConstant(ftag.getFloatValue()), getClassFieldPtr(function, field)));
                    } else if (tag instanceof IntegerConstantValueTag) {
                        IntegerConstantValueTag itag = (IntegerConstantValueTag) tag;
                        Constant c = new IntegerConstant(itag.getIntValue());
                        IntegerType type = (IntegerType) getType(field.getType());
                        if (type.getBits() < 32) {
                            c = new ConstantTrunc(c, type);
                        }
                        function.add(new Store(c, getClassFieldPtr(function, field)));
                    } else if (tag instanceof LongConstantValueTag) {
                        LongConstantValueTag ltag = (LongConstantValueTag) tag;
                        function.add(new Store(new IntegerConstant(ltag.getLongValue()), getClassFieldPtr(function, field)));
                    } else if (tag instanceof StringConstantValueTag) {
                        String s = ((StringConstantValueTag) tag).getStringValue();
                        Value string = getString(s);
                        Variable result = function.newVariable(OBJECT_PTR);
                        function.add(new Call(result, NVM_BC_LDC_STRING, ENV, string));
                        function.add(new Store(result.ref(), getClassFieldPtr(function, field)));
                    }
                }
            }
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
            
        return module.newFunction(Linkage._private, name, functionType, parameterNames);
    }
    
    private void classLoaderFunction() {
        String name = "NullVM_" + mangleString(getInternalName(sootClass));
        Function function = module.newFunction(name, new FunctionType(CLASS_PTR, ENV_PTR, OBJECT_PTR), 
                "env", "classLoader");
        
        for (Entry<SootClass, Global> entry : throwables.entrySet()) {
            Variable t1 = function.newVariable(OBJECT_PTR);
            function.add(new Call(t1, NVM_BC_FIND_CLASS_IN_LOADER, 
                    ENV, getString(getInternalName(entry.getKey())),
                    new VariableRef("classLoader", OBJECT_PTR)));
            Variable t2 = function.newVariable(CLASS_PTR);            
            function.add(new Bitcast(t2, t1.ref(), CLASS_PTR));
            function.add(new Store(t2.ref(), entry.getValue().ref()));
        }
        
        Variable clazz = function.newVariable("clazz", CLASS_PTR);
        Value superclassName = null;
        if (sootClass.hasSuperclass() && !sootClass.isInterface()) {
            superclassName = getString(getInternalName(sootClass.getSuperclass()));
        } else {
            superclassName = new NullConstant(I8_PTR);
        }
        function.add(new Call(clazz, NVM_BC_ALLOCATE_CLASS, 
                ENV, 
                getString(getInternalName(sootClass)), 
                superclassName,
                new VariableRef("classLoader", OBJECT_PTR),
                new IntegerConstant(sootClass.getModifiers()),
                sizeof(classFieldsType), sizeof(instanceFieldsType)));
        
        for (SootClass iface : sootClass.getInterfaces()) {
            function.add(new Call(NVM_BC_ADD_INTERFACE, ENV, clazz.ref(), getString(getInternalName(iface))));
        }
        
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
        
        if (!sootClass.declaresMethodByName("<clinit>") && hasConstantValueTags(classFields)) {
            Value functionRef = new ConstantBitcast(
                    new FunctionRef(mangleMethod(getInternalName(sootClass), "<clinit>", 
                                new ArrayList<soot.Type>(), soot.VoidType.v()), 
                            new FunctionType(VOID, ENV_PTR)), I8_PTR);
            function.add(new Call(NVM_BC_ADD_METHOD, ENV, clazz.ref(),
                    getString("<clinit>"),
                    getString("()V"),
                    new IntegerConstant(Modifier.STATIC),
                    functionRef,
                    new NullConstant(I8_PTR),
                    new NullConstant(I8_PTR)));
        }

        for (SootMethod method : sootClass.getMethods()) {
            Value functionRef = new NullConstant(I8_PTR);
            Value synchronizedRef = new NullConstant(I8_PTR);
            Value lookup = new NullConstant(I8_PTR);
            if (!method.isAbstract()) {
                functionRef = new ConstantBitcast(new FunctionRef(mangleMethod(method), 
                        getFunctionType(method)), I8_PTR);
            }
            if (!method.isAbstract() && method.isSynchronized()) {
                synchronizedRef = new ConstantBitcast(new FunctionRef(mangleMethod(method) + "_synchronized", 
                        getFunctionType(method)), I8_PTR);
            }            
            if (!method.isStatic() && !"<init>".equals(method.getName()) 
                    && !method.isPrivate() && !Modifier.isFinal(method.getModifiers())) {
                // Virtual method. If not defined in a superclass we need to create a virtual lookup function now.
                if (!ancestorDeclaresMethod(sootClass, method)) {
                    lookup = new ConstantBitcast(new FunctionRef(mangleMethod(method) + "_lookup", 
                            getFunctionType(method)), I8_PTR);
                }
            }
            function.add(new Call(NVM_BC_ADD_METHOD, ENV, clazz.ref(),
                    getString(method.getName()),
                    getString(getDescriptor(method)),
                    new IntegerConstant(method.getModifiers()),
                    functionRef,
                    synchronizedRef,
                    lookup));
        }
        
        function.add(new Call(NVM_BC_REGISTER_CLASS, ENV, clazz.ref()));
        
        function.add(new Store(clazz.ref(), THE_CLASS.ref()));
        
        function.add(new Ret(clazz.ref()));
    }
    
    private void fieldGetter(SootField field) {
        String name = mangleField(field) + "_getter";
        Function function = null;
        Value fieldPtr = null;
        if (field.isStatic()) {
            function = module.newFunction(Linkage._private, name, new FunctionType(getType(field.getType()), ENV_PTR), "env");
            fieldPtr = getClassFieldPtr(function, field);
        } else {
            function = module.newFunction(Linkage._private, name, new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR), "env", "this");
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
            function = module.newFunction(Linkage._private, name, new FunctionType(VOID, ENV_PTR, getType(field.getType())), "env", "value");
            fieldPtr = getClassFieldPtr(function, field);
        } else {
            function = module.newFunction(Linkage._private, name, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())), "env", "this", "value");
            fieldPtr = getInstanceFieldPtr(function, new VariableRef("this", OBJECT_PTR), field);
        }
        function.add(new Store(new VariableRef("value", getType(field.getType())), fieldPtr));
        function.add(new Ret());
    }
    
    private void virtualLookupFunction(SootMethod method) {
        // TODO: This should use a virtual method table or interface method table.
        String name = mangleMethod(method) + "_lookup";
        Function function = createFunction(name, method);
        FunctionType functionType = function.getType();
        Variable fptr = function.newVariable(I8_PTR);
        Value nameRef = getString(method.getName());
        Value descRef = getString(getDescriptor(method.makeRef()));
        if (sootClass.isInterface()) {
            function.add(new Call(fptr, NVM_BC_LOOKUP_INTERFACE_METHOD, ENV, getCaller(function), new VariableRef("this", OBJECT_PTR), nameRef, descRef));
        } else {
            function.add(new Call(fptr, NVM_BC_LOOKUP_VIRTUAL_METHOD, ENV, getCaller(function), new VariableRef("this", OBJECT_PTR), nameRef, descRef));
        }
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
    
    private static boolean hasConstantValueTags(List<SootField> classFields) {
        for (SootField field : classFields) {
            if (field.isFinal()) {
                for (Tag tag : field.getTags()) {
                    if (tag instanceof ConstantValueTag) {
                        return true;
                    }
                }
            }
        }
        return false;
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
    
    /**
     * Resolves the {@link SootMethod} corresponding to a {@link SootMethodRef}
     * in the specified {@link SootClass}. We cannot use {@link SootMethodRef#resolve()} 
     * since it will search super classes and it will create a method with a 
     * body which throws java.lang.Error if the method doesn't exist.
     */
    private static SootMethod resolveMethod(SootClass c, SootMethodRef methodRef) {
        if (!methodRef.declaringClass().equals(c)) {
            return null;
        }
        String name = methodRef.name();
        List<?> parameterTypes = methodRef.parameterTypes();
        soot.Type returnType = methodRef.returnType();
        if (!c.declaresMethod(name, parameterTypes, returnType)) {
            return null;
        }
        return c.getMethod(name, parameterTypes, returnType);
    }
    
    private static boolean ancestorDeclaresMethod(SootClass c, SootMethod method) {
        if (c.isInterface()) {
            return false;
        }
        String name = method.getName();
        List<?> parameterTypes = method.getParameterTypes();
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
    
    private static Type getLocalType(soot.Type sootType) {
        Type t = getType(sootType);
        if (t instanceof IntegerType && ((IntegerType) t).getBits() < 32) {
            return I32;
        }
        return t;
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
        } else if (sootType instanceof soot.RefLikeType || sootType.equals(BottomType.v())) {
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
            return NVM_BC_NEW_BOOLEAN_ARRAY;
        } else if (sootType.equals(soot.ByteType.v())) {
            return NVM_BC_NEW_BYTE_ARRAY;
        } else if (sootType.equals(soot.ShortType.v())) {
            return NVM_BC_NEW_SHORT_ARRAY;
        } else if (sootType.equals(soot.CharType.v())) {
            return NVM_BC_NEW_CHAR_ARRAY;
        } else if (sootType.equals(soot.IntType.v())) {
            return NVM_BC_NEW_INT_ARRAY;
        } else if (sootType.equals(soot.LongType.v())) {
            return NVM_BC_NEW_LONG_ARRAY;
        } else if (sootType.equals(soot.FloatType.v())) {
            return NVM_BC_NEW_FLOAT_ARRAY;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return NVM_BC_NEW_DOUBLE_ARRAY;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
//    private Value cast(Context ctx, Value value, Type targetType, soot.Type sootSourceType) {
//        value = widen(ctx, value, targetType, sootSourceType);
//        if (value.isInteger() && targetType instanceof IntegerType) {
//            IntegerType t1 = (IntegerType) targetType;
//            IntegerType t2 = (IntegerType) value.getType();
//            if (t1.getBits() < t2.getBits()) {
//                Variable result = ctx.f().newVariable(targetType);
//                ctx.f().add(new Trunc(result, value, targetType));
//                return new VariableRef(result);                
//            }
//        }
//        return value;
//    }
    
//    private Value widen(Context ctx, Value value, Type targetType, soot.Type sootSourceType) {
//        return widen(ctx, value, targetType, sootSourceType.equals(CharType.v()));
//    }
//    
//    private Value widen(Context ctx, Value value, Type targetType, boolean unsigned) {
//        /*
//         * Soot only emits CastExpr for widening integer conversions when the 
//         * target type is long or if the source type doesn't fit in the target
//         * type (e.g. char -> short).
//         */
//        if (value.isInteger() && targetType instanceof IntegerType) {
//            IntegerType t1 = (IntegerType) targetType;
//            IntegerType t2 = (IntegerType) value.getType();
//            if (t1.getBits() > t2.getBits()) {
//                Variable result = ctx.f().newVariable(targetType);
//                if (unsigned) {
//                    ctx.f().add(new Zext(result, value, targetType));
//                } else {
//                    ctx.f().add(new Sext(result, value, targetType));                    
//                }
//                return new VariableRef(result);
//            }
//        }
//        return value;
//    }
    
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
            g = new Global(getStringVarName(modUtf8), Linkage.linker_private, 
                    new StringConstant(modUtf8), true);
            strings.put(string, g);
        }
        return new ConstantGetelementptr(new GlobalRef(g), 0, 0);
    }
    
    protected Value immediate(Context ctx, Immediate v) {
        // v is either a soot.Local or a soot.jimple.Constant
        if (v instanceof soot.Local) {
            Local local = (Local) v;
            Type type = getLocalType(v.getType());
            VariableRef var = new VariableRef(local.getName(), new PointerType(type));
            Variable tmp = ctx.f().newVariable(type);
            ctx.f().add(new Load(tmp, var));
            return new VariableRef(tmp);
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
            return callOrInvoke(ctx, NVM_BC_LDC_STRING, ENV, string);
        } else if (v instanceof soot.jimple.ClassConstant) {
            Value clazz = getString(((soot.jimple.ClassConstant) v).getValue());
            return callOrInvoke(ctx, NVM_BC_LDC_CLASS, ENV, clazz, getCaller(ctx));
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
        SootMethod method = resolveMethod(sootClass, methodRef);
        if (method != null) {
            if (method.isAbstract() || method.isPhantom()) {
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
    
    private Value widenToI32Value(Context ctx, Value value, boolean unsigned) {
        Type type = value.getType();
        if (type instanceof IntegerType && ((IntegerType) type).getBits() < 32) {
            Variable t = ctx.f().newVariable(I32);
            if (unsigned) {
                ctx.f().add(new Zext(t, value, I32));
            } else {
                ctx.f().add(new Sext(t, value, I32));
            }
            return t.ref();
        } else {
            return value;
        }
    }
    
    private Value narrowFromI32Value(Context ctx, Type type, Value value) {
        if (value.getType() == I32 && ((IntegerType) type).getBits() < 32) {
            Variable t = ctx.f().newVariable(type);
            ctx.f().add(new Trunc(t, value, type));
            value = t.ref();
        }
        return value;
    }
    
    private Value[] narrowFromI32Values(Context ctx, Type[] types, Value[] values) {
        Value[] newValues = new Value[values.length];
        int i = 0;
        for (Type type : types) {
            newValues[i] = narrowFromI32Value(ctx, type, values[i]);
            i++;
        }
        return newValues;
    }

    private Value call(Context ctx, Value function, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) function.getType()).getReturnType();
        if (returnType != VOID) {
            result = ctx.f().newVariable(returnType);
        }
        ctx.f().add(new Call(result, function, args));
        return result == null ? null : result.ref();
    }
    
    private Value callOrInvoke(Context ctx, Value function, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) function.getType()).getReturnType();
        if (returnType != VOID) {
            result = ctx.f().newVariable(returnType);
        }
        if (ctx.hasTrap(ctx.getCurrentUnit())) {
            Label label = new Label();
            BasicBlockRef to = ctx.f().newBasicBlockRef(label);
            BasicBlockRef unwind = ctx.f().newBasicBlockRef(new Label(ctx.getCurrentTraps()));
            ctx.f().add(new Invoke(result, function, to, unwind, args));
            ctx.f().newBasicBlock(label);
            ctx.recordCurrentTraps();
        } else {
            ctx.f().add(new Call(result, function, args));
        }
        return result == null ? null : result.ref();
    }
    
    private Value callOrInvokeTrampoline(Context ctx, Trampoline trampoline, Value ... args) {
        FunctionRef f = trampolines.get(trampoline);
        String name = f.getName().substring(1);
        Variable ptr = ctx.f().newVariable(f.getType());
        ctx.f().add(new Load(ptr, new GlobalRef(name + "_ptr", f.getType())));
        return callOrInvoke(ctx, ptr.ref(), args);
    }
    
    private void callTrampoline(Function function, Trampoline trampoline, Variable result, Value ... args) {
        FunctionRef f = trampolines.get(trampoline);
        String name = f.getName().substring(1);
        Variable ptr = function.newVariable(f.getType());
        function.add(new Load(ptr, new GlobalRef(name + "_ptr", f.getType())));
        function.add(new Call(result, ptr.ref(), args));
    }
    
    @SuppressWarnings("unchecked")
    private Value invokeExpr(Context ctx, Stmt stmt, InvokeExpr expr) {
        SootMethodRef methodRef = expr.getMethodRef();
        ArrayList<Value> args = new ArrayList<Value>();
        args.add(ENV);
        if (!(expr instanceof StaticInvokeExpr)) {
            Value base = immediate(ctx, (Immediate) ((InstanceInvokeExpr) expr).getBase());
            checkNull(ctx, base);
            args.add(base);
        }
        int i = 0;
        for (soot.Value sootArg : (List<soot.Value>) expr.getArgs())  {
            Value arg = immediate(ctx, (Immediate) sootArg);
            args.add(narrowFromI32Value(ctx, getType(methodRef.parameterType(i)), arg));
            i++;
        }
        Value result = null;
        if (canCallDirectly(ctx, expr)) {
            Value function = null;
            if (resolveMethod(sootClass, methodRef).isSynchronized()) {
                function = new FunctionRef(mangleMethod(methodRef) + "_synchronized", getFunctionType(methodRef));
            } else {
                function = new FunctionRef(mangleMethod(methodRef), getFunctionType(methodRef));
            }
            result = callOrInvoke(ctx, function, args.toArray(new Value[0]));
        } else {
            Trampoline trampoline = null;
            String targetClassName = getInternalName(methodRef.declaringClass());
            String methodName = methodRef.name();
            String methodDesc = getDescriptor(methodRef);
            if (expr instanceof SpecialInvokeExpr) {
                soot.Type runtimeType = ((SpecialInvokeExpr) expr).getBase().getType();
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                trampoline = new Invokespecial(runtimeClassName, targetClassName, methodName, methodDesc);
            } else if (expr instanceof StaticInvokeExpr) {
                trampoline = new Invokestatic(targetClassName, methodName, methodDesc);
            } else if (expr instanceof VirtualInvokeExpr) {
                soot.Type runtimeType = ((VirtualInvokeExpr) expr).getBase().getType();
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                trampoline = new Invokevirtual(runtimeClassName, targetClassName, methodName, methodDesc);
            } else if (expr instanceof InterfaceInvokeExpr) {
                trampoline = new Invokeinterface(targetClassName, methodName, methodDesc);
            }
            addTrampoline(trampoline, getFunctionType(methodRef));
            result = callOrInvokeTrampoline(ctx, trampoline, args.toArray(new Value[0]));
        }
        if (result != null) {
            return widenToI32Value(ctx, result, methodRef.returnType().equals(CharType.v()));
        } else {
            return null;
        }
    }

    private void checkNull(Context ctx, Value base) {
        Stmt stmt = (Stmt) ctx.getCurrentUnit();
        NullCheckTag nullCheckTag = (NullCheckTag) stmt.getTag("NullCheckTag");
        if (nullCheckTag == null || nullCheckTag.needCheck()) {
            callOrInvoke(ctx, CHECK_NULL, ENV, base);
        }
    }
    
    private void checkBounds(Context ctx, Value base, Value index) {
        Stmt stmt = (Stmt) ctx.getCurrentUnit();
        ArrayCheckTag arrayCheckTag = (ArrayCheckTag) stmt.getTag("ArrayCheckTag");
        if (arrayCheckTag == null || arrayCheckTag.isCheckLower()) {
            callOrInvoke(ctx, CHECK_LOWER, ENV, base, index);
        }
        if (arrayCheckTag == null || arrayCheckTag.isCheckUpper()) {
            callOrInvoke(ctx, CHECK_UPPER, ENV, base, index);
        }
    }
    
    private static Constant sizeof(StructureType type) {
        if (type == null) {
            return new IntegerConstant(0);
        }
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
    
    private Value getCaller(Context ctx) {
        return getCaller(ctx.f());
    }
    
    private Value getCaller(Function f) {
        Variable caller = f.newVariable(CLASS_PTR);
        f.add(new Load(caller, THE_CLASS.ref()));            
        return caller.ref();
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

        soot.Value rightOp = stmt.getRightOp();
//        Type rightType = getLocalType(rightOp.getType());
        Value result;

        if (rightOp instanceof Immediate) {
            Immediate immediate = (Immediate) rightOp;
            result = immediate(ctx, immediate);
        } else if (rightOp instanceof ThisRef) {
            result = new VariableRef("this", OBJECT_PTR);
        } else if (rightOp instanceof ParameterRef) {
            ParameterRef ref = (ParameterRef) rightOp;
            Value p = new VariableRef("p" + ref.getIndex(), getType(ref.getType()));
            result = widenToI32Value(ctx, p, isUnsigned(ref.getType()));
        } else if (rightOp instanceof CaughtExceptionRef) {
            result = call(ctx, NVM_BC_EXCEPTION_CLEAR, ENV);
        } else if (rightOp instanceof ArrayRef) {
            ArrayRef ref = (ArrayRef) rightOp;
            VariableRef base = (VariableRef) immediate(ctx, (Immediate) ref.getBase());
            Value index = immediate(ctx, (Immediate) ref.getIndex());
            checkNull(ctx, base);
            checkBounds(ctx, base, index);
            result = callOrInvoke(ctx, getArrayLoad(ref.getType()), base, index);
            result = widenToI32Value(ctx, result, isUnsigned(ref.getType()));
        } else if (rightOp instanceof InstanceFieldRef) {
            InstanceFieldRef ref = (InstanceFieldRef) rightOp;
            Value base = immediate(ctx, (Immediate) ref.getBase());
            checkNull(ctx, base);
            if (canAccessDirectly(ctx, ref)) {
                Variable v = ctx.f().newVariable(getType(ref.getType()));
                ctx.f().add(new Load(v, getInstanceFieldPtr(ctx.f(), base, ref.getField())));
                result = widenToI32Value(ctx, v.ref(), isUnsigned(ref.getType()));
            } else {
                soot.Type runtimeType = ref.getBase().getType();
                String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                Trampoline trampoline = new GetField(runtimeClassName, targetClassName, 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(getType(ref.getType()), ENV_PTR, OBJECT_PTR));
                result = callOrInvokeTrampoline(ctx, trampoline, ENV, base);
                result = widenToI32Value(ctx, result, isUnsigned(ref.getType()));
            }
        } else if (rightOp instanceof StaticFieldRef) {
            StaticFieldRef ref = (StaticFieldRef) rightOp;
            if (canAccessDirectly(ctx, ref)) {
                Variable v = ctx.f().newVariable(getType(ref.getType()));
                ctx.f().add(new Load(v, getClassFieldPtr(ctx.f(), ref.getField())));
                result = widenToI32Value(ctx, v.ref(), isUnsigned(ref.getType()));
            } else {
                Trampoline trampoline = new GetStatic(getInternalName(ref.getFieldRef().declaringClass()), 
                        ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                addTrampoline(trampoline, new FunctionType(getType(ref.getType()), ENV_PTR));
                result = callOrInvokeTrampoline(ctx, trampoline, ENV);
                result = widenToI32Value(ctx, result, isUnsigned(ref.getType()));
            }
        } else if (rightOp instanceof Expr) {
            if (rightOp instanceof BinopExpr) {
                BinopExpr expr = (BinopExpr) rightOp;
                Type rightType = getLocalType(expr.getType());
                Variable resultVar = ctx.f().newVariable(rightType);
                result = resultVar.ref();
                Value op1 = immediate(ctx, (Immediate) expr.getOp1());
                Value op2 = immediate(ctx, (Immediate) expr.getOp2());
                if (rightOp instanceof AddExpr) {
                    if (rightType instanceof IntegerType) {
                        ctx.f().add(new Add(resultVar, op1, op2));
                    } else {
                        ctx.f().add(new Fadd(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof AndExpr) {
                    ctx.f().add(new And(resultVar, op1, op2));
                } else if (rightOp instanceof CmpExpr) {
                    Variable t1 = ctx.f().newVariable(I1);
                    Variable t2 = ctx.f().newVariable(I1);
                    Variable t3 = ctx.f().newVariable(resultVar.getType());
                    Variable t4 = ctx.f().newVariable(resultVar.getType());
                    ctx.f().add(new Icmp(t1, Condition.slt, op1, op2));
                    ctx.f().add(new Icmp(t2, Condition.sgt, op1, op2));
                    ctx.f().add(new Zext(t3, new VariableRef(t1), resultVar.getType()));
                    ctx.f().add(new Zext(t4, new VariableRef(t2), resultVar.getType()));
                    ctx.f().add(new Sub(resultVar, new VariableRef(t4), new VariableRef(t3)));
                } else if (rightOp instanceof DivExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LDIV : IDIV;
                        result = callOrInvoke(ctx, f, ENV, op1, op2);
                    } else {
                        // float or double
                        ctx.f().add(new Fdiv(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof MulExpr) {
                    if (rightType instanceof IntegerType) {
                        ctx.f().add(new Mul(resultVar, op1, op2));
                    } else {
                        ctx.f().add(new Fmul(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof OrExpr) {
                    ctx.f().add(new Or(resultVar, op1, op2));
                } else if (rightOp instanceof RemExpr) {
                    if (rightType instanceof IntegerType) {
                        FunctionRef f = rightType == I64 ? LREM : IREM;
                        result = callOrInvoke(ctx, f, ENV, op1, op2);
                    } else {
                        // float or double
                        ctx.f().add(new Frem(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof ShlExpr || rightOp instanceof ShrExpr || rightOp instanceof UshrExpr) {
                    IntegerType type = (IntegerType) op1.getType();
                    int bits = type.getBits();
                    Variable t = ctx.f().newVariable(op2.getType());
                    ctx.f().add(new And(t, op2, new IntegerConstant(bits - 1, (IntegerType) op2.getType())));
                    Value shift = t.ref();
                    if (((IntegerType) shift.getType()).getBits() < bits) {
                        Variable tmp = ctx.f().newVariable(type);
                        ctx.f().add(new Zext(tmp, shift, type));
                        shift = tmp.ref();
                    }
                    if (rightOp instanceof ShlExpr) {
                        ctx.f().add(new Shl(resultVar, op1, shift));
                    } else if (rightOp instanceof ShrExpr) {
                        ctx.f().add(new Ashr(resultVar, op1, shift));
                    } else {
                        ctx.f().add(new Lshr(resultVar, op1, shift));
                    }
                } else if (rightOp instanceof SubExpr) {
                    if (rightType instanceof IntegerType) {
                        ctx.f().add(new Sub(resultVar, op1, op2));
                    } else {
                        ctx.f().add(new Fsub(resultVar, op1, op2));
                    }
                } else if (rightOp instanceof XorExpr) {
                    ctx.f().add(new Xor(resultVar, op1, op2));
                } else if (rightOp instanceof XorExpr) {
                    ctx.f().add(new Xor(resultVar, op1, op2));
                } else if (rightOp instanceof CmplExpr) {
                    FunctionRef f = op1.getType() == FLOAT ? FCMPL : DCMPL;
                    ctx.f().add(new Call(resultVar, f, op1, op2));
                } else if (rightOp instanceof CmpgExpr) {
                    FunctionRef f = op1.getType() == FLOAT ? FCMPG : DCMPG;
                    ctx.f().add(new Call(resultVar, f, op1, op2));
                } else {
                    throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
                }
            } else if (rightOp instanceof CastExpr) {
                Value op = immediate(ctx, (Immediate) ((CastExpr) rightOp).getOp());
                soot.Type sootTargetType = ((CastExpr) rightOp).getCastType();
                soot.Type sootSourceType = ((CastExpr) rightOp).getOp().getType();
                if (sootTargetType instanceof PrimType) {
                    Type targetType = getType(sootTargetType);
                    Type sourceType = getType(sootSourceType);
                    if (targetType instanceof IntegerType && sourceType instanceof IntegerType) {
                        // op is at least I32 and has already been widened if source type had fewer bits then I32
                        IntegerType toType = (IntegerType) targetType;
                        IntegerType fromType = (IntegerType) op.getType();
                        Variable v = ctx.f().newVariable(toType);
                        if (fromType.getBits() < toType.getBits()) {
                            // Widening
                            if (isUnsigned(sootSourceType)) {
                                ctx.f().add(new Zext(v, op, toType));
                            } else {
                                ctx.f().add(new Sext(v, op, toType));
                            }
                        } else if (fromType.getBits() == toType.getBits()) {
                            ctx.f().add(new Bitcast(v, op, toType));
                        } else {
                            // Narrow
                            ctx.f().add(new Trunc(v, op, toType));
                        }
                        result = widenToI32Value(ctx, v.ref(), isUnsigned(sootTargetType));
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof IntegerType) {
                        // we always to a signed conversion since if op is char it has already been zero extended to I32
                        Variable v = ctx.f().newVariable(targetType);
                        ctx.f().add(new Sitofp(v, op, targetType));
                        result = v.ref();
                    } else if (targetType instanceof FloatingPointType && sourceType instanceof FloatingPointType) {
                        Variable v = ctx.f().newVariable(targetType);
                        if (targetType == FLOAT && sourceType == DOUBLE) {
                            ctx.f().add(new Fptrunc(v, op, targetType));
                        } else if (targetType == DOUBLE && sourceType == FLOAT) {
                            ctx.f().add(new Fpext(v, op, targetType));
                        } else {
                            ctx.f().add(new Bitcast(v, op, targetType));
                        }
                        result = v.ref();
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
                        Variable v = ctx.f().newVariable(targetType);
                        ctx.f().add(new Call(v, f, op));
                        result = v.ref();
                    }
//                } else if (sootTargetType instanceof soot.ArrayType) {
//                    soot.ArrayType arrayType = (ArrayType) sootTargetType;
//                    if (arrayType.baseType instanceof PrimType) {
//                        
//                    }
                } else {
                    result = callOrInvoke(ctx, NVM_BC_CHECKCAST, ENV, op, getString(getInternalName(sootTargetType)), getCaller(ctx));
//                    int dimensions = 0;
//                    Trampoline trampoline = null;
//                    soot.Type checkType = sootTargetType;
//                    if (checkType instanceof soot.ArrayType) {
//                        dimensions = ((soot.ArrayType) checkType).numDimensions;
//                        trampoline = new Checkcast(getInternalName(((soot.ArrayType) checkType).baseType));
//                    } else {
//                        trampoline = new Checkcast(getInternalName(checkType));
//                    }
//                    addTrampoline(trampoline, new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR, I32));
//                    callOrInvokeTrampoline(ctx, trampoline, result, ENV, op, new IntegerConstant(dimensions));
                }
            } else if (rightOp instanceof InstanceOfExpr) {
                Value op = immediate(ctx, (Immediate) ((InstanceOfExpr) rightOp).getOp());
                soot.Type checkType = ((InstanceOfExpr) rightOp).getCheckType();
                result = callOrInvoke(ctx, NVM_BC_INSTANCEOF, ENV, op, getString(getInternalName(checkType)), getCaller(ctx));
//                int dimensions = 0;
//                Trampoline trampoline = null;
//                soot.Type checkType = ((InstanceOfExpr) rightOp).getCheckType();
//                if (checkType instanceof soot.ArrayType) {
//                    dimensions = ((soot.ArrayType) checkType).numDimensions;
//                    trampoline = new Instanceof(getInternalName(((soot.ArrayType) checkType).baseType));
//                } else {
//                    trampoline = new Instanceof(getInternalName(checkType));
//                }
//                addTrampoline(trampoline, new FunctionType(I8, ENV_PTR, OBJECT_PTR, I32));
//                callOrInvokeTrampoline(ctx, trampoline, result, ENV, op, new IntegerConstant(dimensions));
            } else if (rightOp instanceof NewExpr) {
                result = callOrInvoke(ctx, NVM_BC_NEW, ENV, getString(getInternalName(((NewExpr) rightOp).getBaseType())), getCaller(ctx));
            } else if (rightOp instanceof NewArrayExpr) {
                NewArrayExpr expr = (NewArrayExpr) rightOp;
                Value size = immediate(ctx, (Immediate) expr.getSize());
                if (expr.getBaseType() instanceof PrimType) {
                    result = callOrInvoke(ctx, getNewArray(expr.getBaseType()), ENV, size);
                } else {
                    result = callOrInvoke(ctx, NVM_BC_NEW_OBJECT_ARRAY, ENV, size, getString(getInternalName(expr.getType())), getCaller(ctx));
                }
            } else if (rightOp instanceof NewMultiArrayExpr) {
                NewMultiArrayExpr expr = (NewMultiArrayExpr) rightOp;
                for (int i = 0; i < expr.getSizeCount(); i++) {
                    Value size = immediate(ctx, (Immediate) expr.getSize(i));
                    Variable ptr = ctx.f().newVariable(new PointerType(I32));
                    ctx.f().add(new Getelementptr(ptr, dims.ref(), 0, i));
                    ctx.f().add(new Store(size, ptr.ref()));
                }
                Variable dimsI32 = ctx.f().newVariable(new PointerType(I32));
                ctx.f().add(new Bitcast(dimsI32, dims.ref(), dimsI32.getType()));
                result = callOrInvoke(ctx, NVM_BC_NEW_MULTI_ARRAY, ENV, new IntegerConstant(expr.getSizeCount()), 
                        dimsI32.ref(), getString(getInternalName(expr.getType())), getCaller(ctx));
            } else if (rightOp instanceof InvokeExpr) {
                result = invokeExpr(ctx, stmt, (InvokeExpr) rightOp);
            } else if (rightOp instanceof LengthExpr) {
                Value op = immediate(ctx, (Immediate) ((LengthExpr) rightOp).getOp());
                checkNull(ctx, op);
                Variable v = ctx.f().newVariable(I32);
                ctx.f().add(new Call(v, ARRAY_LENGTH, op));
                result = v.ref();
            } else if (rightOp instanceof NegExpr) {
                NegExpr expr = (NegExpr) rightOp;
                Value op = immediate(ctx, (Immediate) expr.getOp());
                Type rightType = op.getType();
                Variable v = ctx.f().newVariable(op.getType());
                if (rightType instanceof IntegerType) {
                    ctx.f().add(new Sub(v, new IntegerConstant(0, (IntegerType) rightType), op));
                } else {
                    ctx.f().add(new Fmul(v, new FloatingPointConstant(-1.0, (FloatingPointType) rightType), op));
                }
                result = v.ref();
            } else {
                throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
            }
        } else {
            throw new IllegalArgumentException("Unknown type for rightOp: " + rightOp.getClass());
        }

        soot.Value leftOp = stmt.getLeftOp();

        if (leftOp instanceof Local) {
            Local local = (Local) leftOp;
            VariableRef v = new VariableRef(local.getName(), new PointerType(getLocalType(leftOp.getType())));
            ctx.f().add(new Store(result, v));
        } else {
            Type leftType = getType(leftOp.getType());
            Value narrowedResult = narrowFromI32Value(ctx, leftType, result);
            if (leftOp instanceof ArrayRef) {
                ArrayRef ref = (ArrayRef) leftOp;
                VariableRef base = (VariableRef) immediate(ctx, (Immediate) ref.getBase());
                Value index = immediate(ctx, (Immediate) ref.getIndex());
                checkNull(ctx, base);
                checkBounds(ctx, base, index);
                callOrInvoke(ctx, getArrayStore(leftOp.getType()), base, index, narrowedResult);
            } else if (leftOp instanceof InstanceFieldRef) {
                InstanceFieldRef ref = (InstanceFieldRef) leftOp;
                Value base = immediate(ctx, (Immediate) ref.getBase());
                checkNull(ctx, base);
                if (canAccessDirectly(ctx, ref)) {
                    ctx.f().add(new Store(narrowedResult, getInstanceFieldPtr(ctx.f(), base, ref.getField())));
                } else {
                    soot.Type runtimeType = ref.getBase().getType();
                    String targetClassName = getInternalName(ref.getFieldRef().declaringClass());
                    String runtimeClassName = runtimeType == NullType.v() ? targetClassName : getInternalName(runtimeType);
                    Trampoline trampoline = new PutField(runtimeClassName, targetClassName, 
                            ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                    addTrampoline(trampoline, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, leftType));
                    callOrInvokeTrampoline(ctx, trampoline, ENV, base, narrowedResult);
                }
            } else if (leftOp instanceof StaticFieldRef) {
                StaticFieldRef ref = (StaticFieldRef) leftOp;
                if (canAccessDirectly(ctx, ref)) {
                    ctx.f().add(new Store(narrowedResult, getClassFieldPtr(ctx.f(), ref.getField())));
                } else {
                    Trampoline trampoline = new PutStatic(getInternalName(ref.getFieldRef().declaringClass()), 
                            ref.getFieldRef().name(), getDescriptor(ref.getFieldRef().type()));
                    addTrampoline(trampoline, new FunctionType(VOID, ENV_PTR, leftType));
                    callOrInvokeTrampoline(ctx, trampoline, ENV, narrowedResult);
                }
            } else {
                throw new IllegalArgumentException("Unknown type for leftOp: " + leftOp.getClass());
            }
        }
    }

    private static boolean isUnsigned(soot.Type type) {
        return type.equals(CharType.v());
    }
    
    private void return_(Context ctx, ReturnStmt stmt) {
        /*
         * op is an Immediate.
         */
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        Value value = narrowFromI32Value(ctx, ctx.f().getType().getReturnType(), op);
        ctx.f().add(new Ret(value));
    }
    
    private void returnVoid(Context ctx) {
        ctx.f().add(new Ret());
    }
    
    private void if_(Context ctx, IfStmt stmt) {
        ConditionExpr condition = (ConditionExpr) stmt.getCondition();
        Value op1 = immediate(ctx, (Immediate) condition.getOp1());
        Value op2 = immediate(ctx, (Immediate) condition.getOp2());
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
                ctx.f().newBasicBlockRef(new Label(stmt.getTarget())), 
                ctx.f().newBasicBlockRef(new Label(ctx.getNextUnit()))));
    }
    
    private void lookupSwitch(Context ctx, LookupSwitchStmt stmt) {
        Map<IntegerConstant, BasicBlockRef> targets = new HashMap<IntegerConstant, BasicBlockRef>();
        for (int i = 0; i < stmt.getTargetCount(); i++) {
            int value = stmt.getLookupValue(i);
            Unit target = stmt.getTarget(i);
            targets.put(new IntegerConstant(value), ctx.f().newBasicBlockRef(new Label(target)));
        }
        BasicBlockRef def = ctx.f().newBasicBlockRef(new Label(stmt.getDefaultTarget()));
        Value key = immediate(ctx, (Immediate) stmt.getKey());
        ctx.f().add(new Switch(key, def, targets));
    }
    
    private void tableSwitch(Context ctx, TableSwitchStmt stmt) {
        Map<IntegerConstant, BasicBlockRef> targets = new HashMap<IntegerConstant, BasicBlockRef>();
        for (int i = stmt.getLowIndex(); i <= stmt.getHighIndex(); i++) {
            Unit target = stmt.getTarget(i - stmt.getLowIndex());
            targets.put(new IntegerConstant(i), ctx.f().newBasicBlockRef(new Label(target)));
        }
        BasicBlockRef def = ctx.f().newBasicBlockRef(new Label(stmt.getDefaultTarget()));
        Value key = immediate(ctx, (Immediate) stmt.getKey());
        ctx.f().add(new Switch(key, def, targets));
    }
    
    private void goto_(Context ctx, GotoStmt stmt) {
        ctx.f().add(new Br(ctx.f().newBasicBlockRef(new Label(stmt.getTarget()))));
    }
    
    private void throw_(Context ctx, ThrowStmt stmt) {
        Value obj = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, obj);
        if (ctx.hasTrap(stmt)) {
            ctx.f().add(new Call(NVM_BC_EXCEPTION_SET, ENV, obj));
            ctx.f().add(new Br(ctx.f().newBasicBlockRef(new Label(ctx.getCurrentTraps()))));
            ctx.recordCurrentTraps();
        } else {
            ctx.f().add(new Call(NVM_BC_THROW, ENV, obj));
            ctx.f().add(new Unreachable());
        }
    }
    
    private void invoke(Context context, InvokeStmt stmt) {
        invokeExpr(context, stmt, stmt.getInvokeExpr());
    }
    
    private void enterMonitor(Context ctx, EnterMonitorStmt stmt) {
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, op);
        callOrInvoke(ctx,  NVM_BC_MONITOR_ENTER, ENV, op);
    }
    
    private void exitMonitor(Context ctx, ExitMonitorStmt stmt) {
        Value op = immediate(ctx, (Immediate) stmt.getOp());
        checkNull(ctx, op);
        callOrInvoke(ctx,  NVM_BC_MONITOR_EXIT, ENV, op);
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
    
    private static String getDescriptor(SootMethod method) {
        return getDescriptor(method.makeRef());
    }
     
    @SuppressWarnings("unchecked")
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
    
    private static FunctionType getFunctionType(SootMethod method) {
        return getFunctionType(method.makeRef());
    }
    
    @SuppressWarnings("unchecked")
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
    
    @SuppressWarnings("unchecked")
    private static String mangleMethod(SootMethodRef methodRef) {
        return mangleMethod(getInternalName(methodRef.declaringClass()), methodRef.name(), 
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
    
    public static String mangleNativeMethod(String owner, String name) {
        return mangleNativeMethod(owner, name, null);
    }
    
    public static String mangleNativeMethod(String owner, String name, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Java_");
        sb.append(mangleNativeString(owner));
        sb.append("_");
        sb.append(mangleNativeString(name));
        if (desc != null && !desc.startsWith("()")) {
            sb.append("__");
            sb.append(mangleNativeString(desc.substring(1, desc.lastIndexOf(')'))));
        }
        return sb.toString();
    }
    
    public static String mangleNativeString(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c == '_') {
                sb.append("_1");
            } else if (c == ';') {
                sb.append("_2");
            } else if (c == '[') {
                sb.append("_3");
            } else if (c == '/') {
                sb.append("_");
            } else if (c > 0x7f) {
                sb.append(String.format("_0%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    
    private static String mangleField(SootField field) {
        return mangleField(getInternalName(field.getDeclaringClass()), field.getName(), field.getType());
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
                sb.append(HEX_CHARS[(c >> 4) & 0xf]);
                sb.append(HEX_CHARS[c & 0xf]);
            }
        }
        return sb.toString();
    }

    
    public static class HelloWorld {
        private void a(long descriptor) throws Exception {
            if (descriptor < 0) {
                throw new IOException();
            }
        }
        public void b(long descriptor) throws Exception {
            a(descriptor);
        }
    }

    private static String getSootClasspath(Clazzes clazzes) {
        StringBuilder sb = new StringBuilder();
        for (Path path : clazzes.getPaths()) {
            if (sb.length() > 0) {
                sb.append(File.pathSeparator);
            }
            try {
                sb.append(path.getFile().getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sb.toString();
    }
    
    public static void init(Clazzes clazzes, List<Clazz> changed) {
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().setPhaseOption("jap.npc", "enabled:true");
        Options.v().setPhaseOption("wjap.ra", "enabled:true");
        Options.v().setPhaseOption("jap.abc", "enabled:true");
        Options.v().setPhaseOption("tag.an", "enabled:true");
        Options.v().set_print_tags_in_output(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(getSootClasspath(clazzes));

        for (Clazz clazz : changed) {
            Scene.v().loadClassAndSupport(clazz.getClassName()).setApplicationClass();
        }
        
        Scene.v().loadNecessaryClasses();
        
        /*
         * Hack: Remove the DeadAssignmentEliminator since it removes LDC instructions
         * which would have thrown a NoClassDefFoundError.
         * TODO: Report this to soot as a bug?
         */
        Pack pack = PackManager.v().getPack("jb");
        for (Iterator<?> it = pack.iterator(); it.hasNext();) {
            Transform t = (Transform) it.next();
            if ("jb.dae".equals(t.getPhaseName())) {
                it.remove();
            }
        }
        pack.insertAfter(new Transform("jb.dae", new BodyTransformer() {
            @SuppressWarnings("rawtypes")
            @Override
            protected void internalTransform(Body b, String phaseName, Map options) {
            }
        }), "jb.cp");
//      PackManager.v().runPacks();
    }

//    public static void main(String[] args) throws Exception {
//        List<File> bootClassPathFiles = new ArrayList<File>();
//        bootClassPathFiles.add(new File("../rt/target/nullvm-rt-0.1-SNAPSHOT-all.jar"));
//        bootClassPathFiles.add(new File("target/classes"));
//        List<File> classPathFiles = new ArrayList<File>();
////        classPathFiles.add(new File("target/classes"));
//        Clazzes clazzes = new Clazzes(bootClassPathFiles, classPathFiles);
//        SootClassCompiler.init(clazzes);
//
//        for (Path path : clazzes.getPaths()) {
//            for (Clazz clazz : path.list()) {
//                if ("org.nullvm.compiler.SootClassCompiler$HelloWorld".equals(clazz.getClassName())) {
//                    System.out.println("Compiling " + clazz.getClassName());
//                    SootClassCompiler compiler = new SootClassCompiler();
//                    compiler.compile(clazz, System.out);
//                    SootClass sootClass = Scene.v().getSootClass(clazz.getClassName());
//                }
//            }
//        }
//    }
    
}
