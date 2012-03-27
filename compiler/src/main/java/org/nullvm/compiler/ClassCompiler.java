/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Access.*;
import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.FunctionAttribute.*;
import static org.nullvm.compiler.llvm.Linkage.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.ClazzInfo;
import org.nullvm.compiler.clazz.ClazzInfo.FieldInfo;
import org.nullvm.compiler.clazz.ClazzInfo.MethodInfo;
import org.nullvm.compiler.llvm.And;
import org.nullvm.compiler.llvm.Bitcast;
import org.nullvm.compiler.llvm.Br;
import org.nullvm.compiler.llvm.Call;
import org.nullvm.compiler.llvm.Constant;
import org.nullvm.compiler.llvm.ConstantAdd;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Getelementptr;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.GlobalRef;
import org.nullvm.compiler.llvm.Icmp;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.Inttoptr;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.Load;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.PackedStructureConstantBuilder;
import org.nullvm.compiler.llvm.PointerType;
import org.nullvm.compiler.llvm.Ptrtoint;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.Store;
import org.nullvm.compiler.llvm.StructureConstant;
import org.nullvm.compiler.llvm.StructureConstantBuilder;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.trampoline.ExceptionMatch;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.Modifier;
import soot.PrimType;
import soot.RefType;
import soot.ShortType;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.VoidType;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.internal.JReturnVoidStmt;
import soot.tagkit.ConstantValueTag;
import soot.tagkit.Tag;

/**
 *
 * @version $Id$
 */
public class ClassCompiler {
    private static final VariableRef ENV = new VariableRef("env", ENV_PTR);
    private static final Global THE_CLASS = new Global("class", Linkage._private, new NullConstant(CLASS_PTR));
    
    public static final int CI_FLAGS_BITS = 10;
    public static final int CI_INTERFACE_COUNT_BITS = 6;
    public static final int CI_INTERFACE_COUNT_MASK = (1 << CI_INTERFACE_COUNT_BITS) - 1;
    public static final int CI_FIELD_COUNT_BITS = 8;
    public static final int CI_FIELD_COUNT_MASK = (1 << CI_FIELD_COUNT_BITS) - 1;
    public static final int CI_METHOD_COUNT_BITS = 8;
    public static final int CI_METHOD_COUNT_MASK = (1 << CI_METHOD_COUNT_BITS) - 1;
    public static final int CI_PUBLIC = 0x1;
    public static final int CI_FINAL = 0x2;
    public static final int CI_INTERFACE = 0x4;
    public static final int CI_ABSTRACT = 0x8;
    public static final int CI_SYNTHETIC = 0x10;
    public static final int CI_ANNOTATION = 0x20;
    public static final int CI_ENUM = 0x40;
    public static final int CI_ATTRIBUTES = 0x80;
    public static final int CI_ERROR = 0x100;
    public static final int CI_INITIALIZED = 0x200;

    public static final int CI_ERROR_TYPE_NONE = 0x0;
    public static final int CI_ERROR_TYPE_NO_CLASS_DEF_FOUND = 0x1;
    public static final int CI_ERROR_TYPE_ILLEGAL_ACCESS = 0x2;
    public static final int CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE = 0x3;
    
    public static final int FI_ACCESS_MASK = 0x3;
    public static final int FI_PUBLIC = 0x1;
    public static final int FI_PRIVATE = 0x2;
    public static final int FI_PROTECTED = 0x3;
    public static final int FI_STATIC = 0x4;
    public static final int FI_FINAL = 0x8;
    public static final int FI_VOLATILE = 0x10;
    public static final int FI_TRANSIENT = 0x20;
    public static final int FI_SYNTHETIC = 0x40;
    public static final int FI_ENUM = 0x80;
    public static final int FI_ATTRIBUTES = 0x100;
    
    public static final int MI_ACCESS_MASK = 0x3;
    public static final int MI_PUBLIC = 0x1;
    public static final int MI_PRIVATE = 0x2;
    public static final int MI_PROTECTED = 0x3;
    public static final int MI_STATIC = 0x4;
    public static final int MI_FINAL = 0x8;
    public static final int MI_SYNCHRONIZED = 0x10;
    public static final int MI_BRIDGE = 0x20;
    public static final int MI_VARARGS = 0x40;
    public static final int MI_NATIVE = 0x80;
    public static final int MI_ABSTRACT = 0x100;
    public static final int MI_STRICT = 0x200;
    public static final int MI_SYNTHETIC = 0x400;
    public static final int MI_ATTRIBUTES = 0x800;
    public static final int MI_BRO_BRIDGE = 0x1000;
    public static final int MI_BRO_CALLBACK = 0x2000;
    public static final int MI_COMPACT_DESC = 0x4000;
    
    public static final int DESC_B = 1;
    public static final int DESC_C = 2;
    public static final int DESC_D = 3;
    public static final int DESC_F = 4;
    public static final int DESC_I = 5;
    public static final int DESC_J = 6;
    public static final int DESC_S = 7;
    public static final int DESC_Z = 8;
    public static final int DESC_V = 9;
    
    private SootClass sootClass;
    
    private ModuleBuilder mb;
    private Set<Trampoline> trampolines;
    /**
     * Contains the class fields of the class being compiled.
     */
    private List<SootField> classFields;
    /**
     * Contains the instance fields of the class being compiled.
     */
    private List<SootField> instanceFields;
    
    private StructureType classFieldsType;
    private StructureType instanceFieldsType;
    
    private final Config config;
    private final MethodCompiler methodCompiler;
    private final NativeMethodCompiler nativeMethodCompiler;
    private final AttributesEncoder attributesEncoder;
    private final TrampolineCompiler trampolineResolver;
    
    public ClassCompiler(Config config) {
        this.config = config;
        this.methodCompiler = new MethodCompiler(config);
        this.nativeMethodCompiler = new NativeMethodCompiler(config);
        this.attributesEncoder = new AttributesEncoder();
        this.trampolineResolver = new TrampolineCompiler(config);
    }
    
    public boolean compile(Clazz clazz) throws IOException {
        return compile(clazz, false);
    }
    
    public boolean compile(Clazz clazz, boolean force) throws IOException {
        reset();        
        
        File llFile = config.getLlFile(clazz);
        File bcFile = config.getBcFile(clazz);
        File sFile = config.getSFile(clazz);
        File oFile = config.getOFile(clazz);
        llFile.getParentFile().mkdirs();
        bcFile.getParentFile().mkdirs();
        sFile.getParentFile().mkdirs();
        oFile.getParentFile().mkdirs();
        
        boolean recompiled = false;
        
        if (force || config.isClean() || !oFile.exists() || oFile.lastModified() < clazz.lastModified()) {
            OutputStream out = null;
            try {
                config.getLogger().debug("Compiling %s", clazz);
                out = new FileOutputStream(llFile);
                compile(clazz, out);
            } catch (Throwable t) {
                FileUtils.deleteQuietly(llFile);
                if (t instanceof IOException) {
                    throw (IOException) t;
                }
                if (t instanceof RuntimeException) {
                    throw (RuntimeException) t;
                }
                throw new RuntimeException(t);
            } finally {
                IOUtils.closeQuietly(out);
            }

            config.getLogger().debug("Optimizing %s", clazz);
            CompilerUtil.opt(config, llFile, bcFile, "-mem2reg", "-always-inline");

            config.getLogger().debug("Generating %s assembly for %s", config.getArch(), clazz);
            CompilerUtil.llc(config, bcFile, sFile);

            config.getLogger().debug("Assembling %s", clazz);
            CompilerUtil.assemble(config, sFile, oFile);

            recompiled = true;
        }
        
        return recompiled;
    }
    
    private void reset() {
        sootClass = null;
        mb = null;
        trampolines = null;
        classFields = null;
        instanceFields = null;
        classFieldsType = null;
        instanceFieldsType = null;
    }
    
    private void compile(Clazz clazz, OutputStream out) throws IOException {
        sootClass = clazz.getSootClass();
        mb = new ModuleBuilder();
        trampolines = new HashSet<Trampoline>();
        classFields = getClassFields(sootClass);
        instanceFields = getInstanceFields(sootClass);
        classFieldsType = getStructureType(classFields);
        instanceFieldsType = getStructureType(instanceFields);
        
        attributesEncoder.encode(mb, sootClass);
        
        // Add a <clinit> method if the class has ConstantValueTags but no <clinit>.
        // This has to be done before createInfoStruct() is called otherwise the
        // ClassInfoHeader->initializer value will become NULL and constant static fields
        // will never be initialized.
        if (!sootClass.declaresMethodByName("<clinit>") && hasConstantValueTags(classFields)) {
            SootMethod clinit = new SootMethod("<clinit>", Collections.EMPTY_LIST, VoidType.v(), Modifier.STATIC);
            JimpleBody body = Jimple.v().newBody(clinit);
            clinit.setActiveBody(body);
            body.getUnits().add(new JReturnVoidStmt());
            this.sootClass.addMethod(clinit);
        }

        Global classInfoStruct = new Global(mangleClass(sootClass) + "_info_struct", 
                linker_private_weak, createClassInfoStruct());
        
        if (isStruct(this.sootClass)) {
            enhanceStructClass(this.sootClass);
        }
        
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));
        
//        if (classFieldsType != null) {
//            module.addType(classFieldsType);
//        }
//        if (instanceFieldsType != null) {
//            module.addType(instanceFieldsType);
//        }

//        List<SootField> allFields = new ArrayList<SootField>();
//        allFields.addAll(classFields);
//        allFields.addAll(instanceFields);
//        for (SootField field : allFields) {
//            if (!field.isPrivate()) {
//                // Only non private fields need getters
//                fieldGetter(field);
//                if (!field.isFinal()) {
//                    // Final fields can only be set from the class that declares it
//                    // and the declaring class can always access the field directly
//                    fieldSetter(field);
//                }
//            }
//        }
//        

        Function allocator = createAllocator();
        mb.addFunction(allocator);
        mb.addFunction(createClassInitWrapperFunction(allocator.ref()));
        Function instanceof_ = createInstanceof();
        mb.addFunction(instanceof_);
        mb.addFunction(createClassInitWrapperFunction(instanceof_.ref()));
        Function checkcast = createCheckcast();
        mb.addFunction(checkcast);
        mb.addFunction(createClassInitWrapperFunction(checkcast.ref()));
        Function ldcClass = createLdcClass();
        mb.addFunction(ldcClass);
        mb.addFunction(createClassInitWrapperFunction(ldcClass.ref()));
        
        for (SootField f : sootClass.getFields()) {
            Function getter = createFieldGetter(f);
            Function setter = createFieldSetter(f);
            mb.addFunction(getter);
            mb.addFunction(setter);
            if (f.isStatic() && !f.isPrivate()) {
                mb.addFunction(createClassInitWrapperFunction(getter.ref()));
                if (!f.isFinal()) {
                    mb.addFunction(createClassInitWrapperFunction(setter.ref()));
                }
            }
        }
        
        for (SootMethod method : sootClass.getMethods()) {
            if (isBridge(method)) {
                nativeBridgeMethod(method);
            } else if (method.isNative()) {
                if (isStruct(this.sootClass)) {
                    if ("_sizeOf".equals(method.getName()) || "sizeOf".equals(method.getName())) {
                        structSizeOf(method);
                    } else if (isStructMember(method)) {
                        structMember(method);
                    } else {
                        nativeMethod(method);
                    }
                } else {
                    nativeMethod(method);
                }
            } else if (!method.isAbstract()) {
                method(method);
                if (isCallback(method)) {
                    nativeCallbackMethod(method);
                }
            }
//            if (!method.isStatic() && !method.isPrivate() && !Modifier.isFinal(method.getModifiers())) {
//                // Virtual method. If not defined in a superclass we need to create a virtual lookup function now.
//                if (!ancestorDeclaresMethod(sootClass, method)) {
//                    virtualLookupFunction(method);
//                }
//            }
            if (!method.getName().equals("<clinit>") && !method.getName().equals("<init>") 
                    && !method.isPrivate() && !method.isStatic() 
                    && !Modifier.isFinal(method.getModifiers()) 
                    && !Modifier.isFinal(sootClass.getModifiers())) {
                
                createLookupFunction(method);
            }
            if (method.isStatic()) {
                String fnName = mangleMethod(method);
                if (method.isSynchronized()) {
                    fnName += "_synchronized";
                }
                FunctionRef fn = new FunctionRef(fnName, getFunctionType(method));
                mb.addFunction(createClassInitWrapperFunction(fn));
            }
        }
        
        for (Trampoline trampoline : trampolines) {
            trampolineResolver.compile(mb, trampoline);
        }

        StructureConstant classInfoErrorStruct = createClassInfoErrorStruct();
        if (classInfoErrorStruct != null) {
            // The class cannot be loaded at runtime. Replace the ClassInfo struct
            // with a ClassInfoError struct with details of why.
            classInfoStruct = new Global(mangleClass(sootClass) + "_info_struct", 
                    linker_private_weak, classInfoErrorStruct);
        }
        if (sootClass.hasSuperclass() && !"java.lang.Object".equals(sootClass.getSuperclass().getName())) {
            // Assume that java.lang.Object has no instance fields
            mb.addGlobal(new Global(mangleClass(sootClass) + "_offset", 
                    _private, alignedOffset(sootClass), true));
        }
        mb.addGlobal(classInfoStruct);
        
        Function infoFn = new Function(external, new FunctionAttribute[] {alwaysinline, optsize}, 
                getInfoStructFn(getInternalName(sootClass)));
        infoFn.add(new Ret(new ConstantBitcast(classInfoStruct.ref(), I8_PTR_PTR)));
        mb.addFunction(infoFn);
        
        out.write(mb.build().toString().getBytes("UTF-8"));
        
        ClazzInfo clazzInfo = clazz.resetClazzInfo();
        clazzInfo.setModifiers(sootClass.getModifiers());
        clazzInfo.setName(clazz.getInternalName());
        if (sootClass.hasSuperclass() && !sootClass.isInterface()) {
            clazzInfo.setSuperclass(sootClass.getSuperclass().getName().replace('.', '/'));
        } else {
            clazzInfo.setSuperclass(null);
        }
        List<String> interfaces = new ArrayList<String>();
        for (SootClass iface : sootClass.getInterfaces()) {
            interfaces.add(iface.getName().replace('.', '/'));
        }
        clazzInfo.setInterfaces(interfaces);
        List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
        for (SootField f : sootClass.getFields()) {
            fieldInfos.add(new FieldInfo(f.getModifiers(), f.getName(), getDescriptor(f)));
        }
        clazzInfo.setFields(fieldInfos);
        List<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
        for (SootMethod m : sootClass.getMethods()) {
            methodInfos.add(new MethodInfo(m.getModifiers(), m.getName(), getDescriptor(m)));
        }
        clazzInfo.setMethods(methodInfos);
        clazzInfo.setStruct(isStruct(this.sootClass));
        clazzInfo.setAttributeDependencies(attributesEncoder.getDependencies());
        clazzInfo.setTrampolines(trampolines);
        clazz.commitClazzInfo();
        
//        for (Entry<Trampoline, FunctionRef> entry : trampolines.entrySet()) {
//            trampoline(entry.getKey(), entry.getValue());
//        }
        
//        classLoaderFunction();
        
//        for (Global global : throwables.values()) {
//            module.addGlobal(global);
//        }
    }
    
    private void createLookupFunction(SootMethod m) {
        // TODO: This should use a virtual method table or interface method table.
        String name = mangleMethod(m) + "_lookup";
        Function function = new Function(Linkage.external, name, getFunctionType(m));
        mb.addFunction(function);

        Variable reserved0 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved0, function.getParameterRef(0), 0, 4));
        Variable reserved1 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved1, function.getParameterRef(0), 0, 5));
        function.add(new Store(getString(m.getName()), reserved0.ref()));
        function.add(new Store(getString(getDescriptor(m)), reserved1.ref()));
        
        Value lookupFn = sootClass.isInterface() 
                ? NVM_BC_LOOKUP_INTERFACE_METHOD : NVM_BC_LOOKUP_VIRTUAL_METHOD;
        List<Value> args = new ArrayList<Value>();
        args.add(function.getParameterRef(0));
        if (sootClass.isInterface()) {
            Value info = getInfoStruct(function);
            args.add(info);
        }
        args.add(function.getParameterRef(1));
        args.add(getString(m.getName()));
        args.add(getString(getDescriptor(m)));
        Value fptr = call(function, lookupFn, args);
        Variable f = function.newVariable(function.getType());
        function.add(new Bitcast(f, fptr, f.getType()));
        Value result = call(function, f.ref(), function.getParameterRefs());
        function.add(new Ret(result));
    }
    
    private StructureConstant createClassInfoErrorStruct() {
        /*
         * Check that clazz can be loaded, i.e. that the superclass 
         * and interfaces of the class exist and are accessible to the
         * class. Also check that any exception the class uses in catch
         * clauses exist and is accessible to the class. If the class
         * cannot be loaded we override the ClassInfoHeader struct
         * produced by the ClassCompiler for the class with one which
         * tells the code in bc.c to throw an appropriate exception
         * whenever clazz is accessed.
         */
        
        int errorType = ClassCompiler.CI_ERROR_TYPE_NONE;
        String errorMessage = null;
        if (!sootClass.isInterface() && sootClass.hasSuperclass()) {
            // Check superclass
            SootClass superclazz = sootClass.getSuperclass();
            if (superclazz.isPhantom()) {
                errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                errorMessage = superclazz.getName();
            } else if (!checkClassAccessible(superclazz, sootClass)) {
                errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, superclazz, sootClass);
            } else if (superclazz.isInterface()) {
                errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                errorMessage = String.format("class %s has interface %s as super class", sootClass, superclazz);
            }
            // No need to check for ClassCircularityError. Soot doesn't handle 
            // such problems so the compilation will fail earlier.
        }
        
        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check interfaces
            for (SootClass interfaze :  sootClass.getInterfaces()) {
                if (interfaze.isPhantom()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                    errorMessage = interfaze.getName();
                    break;
                } else if (!checkClassAccessible(interfaze, sootClass)) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                    errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, interfaze, sootClass);
                    break;
                } else if (!interfaze.isInterface()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                    errorMessage = String.format("class %s tries to implement class %s as interface", 
                            sootClass, interfaze);
                    break;
                }
            }
        }
        
        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check exceptions used in catch clauses. I cannot find any info in
            // the VM spec specifying that this has to be done when the class is loaded.
            // However, this is how it's done in other VMs so we do it too.
            for (Trampoline t : trampolines) {
                if (t instanceof ExceptionMatch) {
                    Clazz ex = config.getClazzes().load(t.getTarget());
                    if (ex == null || ex.getSootClass().isPhantom()) {
                        errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                        errorMessage = t.getTarget();
                        break;
                    } else if (!checkClassAccessible(ex.getSootClass(), sootClass)) {
                        errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                        errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, ex, sootClass);
                        break;
                    }
                }
            }
        }
        
        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            return null;
        }
        
        // Create a ClassInfoError struct
        StructureConstantBuilder error = new StructureConstantBuilder();
        error.add(new NullConstant(I8_PTR)); // Points to the runtime Class struct
        error.add(new IntegerConstant(ClassCompiler.CI_ERROR));
        error.add(getString(getInternalName(sootClass)));
        error.add(new IntegerConstant(errorType));
        error.add(getString(errorMessage));
        return error.build();
    }
    
    private StructureConstant createClassInfoStruct() {
        int flags = 0;
        
        flags <<= CI_INTERFACE_COUNT_BITS;
        if (sootClass.getInterfaceCount() < CI_INTERFACE_COUNT_MASK) {
            flags |= sootClass.getInterfaceCount();
        } else {
            flags |= CI_INTERFACE_COUNT_MASK;
        }
        
        flags <<= CI_FIELD_COUNT_BITS;
        if (sootClass.getFieldCount() < CI_FIELD_COUNT_MASK) {
            flags |= sootClass.getFieldCount();
        } else {
            flags |= CI_FIELD_COUNT_MASK;
        }
        
        flags <<= CI_METHOD_COUNT_BITS;
        if (sootClass.getMethodCount() < CI_METHOD_COUNT_MASK) {
            flags |= sootClass.getMethodCount();
        } else {
            flags |= CI_METHOD_COUNT_MASK;
        }
        
        flags <<= CI_FLAGS_BITS;
        if (Modifier.isPublic(sootClass.getModifiers())) {
            flags |= CI_PUBLIC;
        }
        if (Modifier.isFinal(sootClass.getModifiers())) {
            flags |= CI_FINAL;
        }
        if (Modifier.isInterface(sootClass.getModifiers())) {
            flags |= CI_INTERFACE;
        }
        if (Modifier.isAbstract(sootClass.getModifiers())) {
            flags |= CI_ABSTRACT;
        }
        if ((sootClass.getModifiers() & 0x1000) > 0) {
            flags |= CI_SYNTHETIC;
        }
        if (Modifier.isAnnotation(sootClass.getModifiers())) {
            flags |= CI_ANNOTATION;
        }
        if (Modifier.isEnum(sootClass.getModifiers())) {
            flags |= CI_ENUM;
        }
        if (attributesEncoder.classHasAttributes()) {
            flags |= CI_ATTRIBUTES;
        }
        
        // Create the ClassInfoHeader structure.
        StructureConstantBuilder header = new StructureConstantBuilder();
        header.add(new NullConstant(I8_PTR)); // Points to the runtime Class struct
        header.add(new IntegerConstant(flags));
        header.add(getString(getInternalName(sootClass)));
        if (sootClass.declaresMethod("<clinit>", Collections.emptyList(), VoidType.v())) {
            SootMethod method = sootClass.getMethod("<clinit>", Collections.emptyList(), VoidType.v());
            header.add(new FunctionRef(mangleMethod(method), getFunctionType(method)));            
        } else {
            header.add(new NullConstant(I8_PTR));
        }
        header.add(classFieldsType == null ? new IntegerConstant(0) : alignedSizeof(classFieldsType));
        header.add(instanceFieldsType == null ? new IntegerConstant(0) : alignedSizeof(instanceFieldsType));

        PackedStructureConstantBuilder body = new PackedStructureConstantBuilder();
        if (sootClass.getInterfaceCount() >= CI_INTERFACE_COUNT_MASK) {
            body.add(new IntegerConstant((short) sootClass.getInterfaceCount()));
        }
        if (sootClass.getFieldCount() >= CI_FIELD_COUNT_MASK) {
            body.add(new IntegerConstant((short) sootClass.getFieldCount()));
        }
        if (sootClass.getMethodCount() >= CI_METHOD_COUNT_MASK) {
            body.add(new IntegerConstant((short) sootClass.getMethodCount()));
        }
        
        if (!sootClass.isInterface()) {
            body.add(getStringOrNull(sootClass.hasSuperclass() ? getInternalName(sootClass.getSuperclass()) : null));
        }

        if (attributesEncoder.classHasAttributes()) {
            body.add(new ConstantBitcast(attributesEncoder.getClassAttributes().ref(), I8_PTR));
        }
        
        for (SootClass s : sootClass.getInterfaces()) {
            body.add(getString(getInternalName(s)));
        }
        
        int classFieldCounter = 0;
        int instanceFieldCounter = 0;
        for (SootField f : sootClass.getFields()) {
            flags = 0;
            soot.Type t = f.getType();
            if (t instanceof PrimType) {
                if (t.equals(BooleanType.v())) {
                    flags |= DESC_Z;
                } else if (t.equals(ByteType.v())) {
                    flags |= DESC_B;
                } else if (t.equals(ShortType.v())) {
                    flags |= DESC_S;
                } else if (t.equals(CharType.v())) {
                    flags |= DESC_C;
                } else if (t.equals(IntType.v())) {
                    flags |= DESC_I;
                } else if (t.equals(LongType.v())) {
                    flags |= DESC_J;
                } else if (t.equals(FloatType.v())) {
                    flags |= DESC_F;
                } else if (t.equals(DoubleType.v())) {
                    flags |= DESC_D;
                }
                flags <<= 12;
            }
            if (Modifier.isPublic(f.getModifiers())) {
                flags |= FI_PUBLIC;
            } else if (Modifier.isPrivate(f.getModifiers())) {
                flags |= FI_PRIVATE;
            } else if (Modifier.isProtected(f.getModifiers())) {
                flags |= FI_PROTECTED;
            }
            if (Modifier.isStatic(f.getModifiers())) {
                flags |= FI_STATIC;
            }
            if (Modifier.isFinal(f.getModifiers())) {
                flags |= FI_FINAL;
            }
            if (Modifier.isVolatile(f.getModifiers())) {
                flags |= FI_VOLATILE;
            }
            if (Modifier.isTransient(f.getModifiers())) {
                flags |= FI_TRANSIENT;
            }
            if ((f.getModifiers() & 0x1000) > 0) {
                flags |= FI_SYNTHETIC;
            }
            if (Modifier.isEnum(f.getModifiers())) {
                flags |= FI_ENUM;
            }
            if (attributesEncoder.fieldHasAttributes(f)) {
                flags |= FI_ATTRIBUTES;
            }
            body.add(new IntegerConstant((short) flags));
            body.add(getString(f.getName()));
            if (!(t instanceof PrimType)) {
                body.add(getString(getDescriptor(f)));
            }
            if (f.isStatic()) {
                body.add(offsetof(classFieldsType, classFieldCounter++));
            } else {
                body.add(offsetof(instanceFieldsType, instanceFieldCounter++));
            }
            if (attributesEncoder.fieldHasAttributes(f)) {
                body.add(new ConstantBitcast(attributesEncoder.getFieldAttributes(f).ref(), I8_PTR));
            }
        }
        
        for (SootMethod m : sootClass.getMethods()) {
            soot.Type t = m.getReturnType();
            flags = 0;
            if (Modifier.isPublic(m.getModifiers())) {
                flags |= MI_PUBLIC;
            } else if (Modifier.isPrivate(m.getModifiers())) {
                flags |= MI_PRIVATE;
            } else if (Modifier.isProtected(m.getModifiers())) {
                flags |= MI_PROTECTED;
            }
            if (Modifier.isStatic(m.getModifiers())) {
                flags |= MI_STATIC;
            }
            if (Modifier.isFinal(m.getModifiers())) {
                flags |= MI_FINAL;
            }
            if (Modifier.isSynchronized(m.getModifiers())) {
                flags |= MI_SYNCHRONIZED;
            }
            if ((m.getModifiers() & 0x0040) > 0) {
                flags |= MI_BRIDGE;
            }
            if ((m.getModifiers() & 0x0080) > 0) {
                flags |= MI_VARARGS;
            }
            if (Modifier.isNative(m.getModifiers())) {
                flags |= MI_NATIVE;
            }
            if (Modifier.isAbstract(m.getModifiers())) {
                flags |= MI_ABSTRACT;
            }
            if (Modifier.isStrictFP(m.getModifiers())) {
                flags |= MI_STRICT;
            }
            if ((m.getModifiers() & 0x1000) > 0) {
                flags |= MI_SYNTHETIC;
            }
            if (attributesEncoder.methodHasAttributes(m)) {
                flags |= MI_ATTRIBUTES;
            }
            if (isBridge(m)) {
                flags |= MI_BRO_BRIDGE;
            }
            if (isCallback(m)) {
                flags |= MI_BRO_CALLBACK;
            }
            if ((t instanceof PrimType || t == VoidType.v()) && m.getParameterCount() == 0) {
                flags |= MI_COMPACT_DESC;
            }
            body.add(new IntegerConstant((short) flags));            

            body.add(getString(m.getName()));
            
            if ((flags & MI_COMPACT_DESC) > 0) {
                int desc = 0;
                if (t.equals(BooleanType.v())) {
                    desc = DESC_Z;
                } else if (t.equals(ByteType.v())) {
                    desc = DESC_B;
                } else if (t.equals(ShortType.v())) {
                    desc = DESC_S;
                } else if (t.equals(CharType.v())) {
                    desc = DESC_C;
                } else if (t.equals(IntType.v())) {
                    desc = DESC_I;
                } else if (t.equals(LongType.v())) {
                    desc = DESC_J;
                } else if (t.equals(FloatType.v())) {
                    desc = DESC_F;
                } else if (t.equals(DoubleType.v())) {
                    desc = DESC_D;
                } else if (t.equals(VoidType.v())) {
                    desc = DESC_V;
                }
                body.add(new IntegerConstant((byte) desc));            
            } else {
                body.add(getString(getDescriptor(m)));
            }
            if (attributesEncoder.methodHasAttributes(m)) {
                body.add(new ConstantBitcast(attributesEncoder.getMethodAttributes(m).ref(), I8_PTR));
            }
            if (!sootClass.isInterface() && !m.isAbstract()) {
                body.add(new ConstantBitcast(new FunctionRef(mangleMethod(m), getFunctionType(m)), I8_PTR));
                if (m.isSynchronized()) {
                    body.add(new ConstantBitcast(new FunctionRef(mangleMethod(m) + "_synchronized", getFunctionType(m)), I8_PTR));
                }
            }
//            if (!m.getName().equals("<clinit>") && !m.getName().equals("<init>") && !m.isPrivate() && !m.isStatic()) {
//                FunctionRef lookupFn = new FunctionRef(mangleMethod(m) + "_lookup", getFunctionType(m));
//                body.add(new ConstantBitcast(lookupFn, I8_PTR));
//            }

        }
        
        // Return the struct {header, body}. To be compatible with the C code in classinfo.c 
        // it is important that the header is padded the same as in C so that the body starts
        // after sizeof(ClassInfoHeader) bytes.
        return new StructureConstantBuilder().add(header.build()).add(body.build()).build();
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
    
    private void enhanceStructClass(SootClass clazz) {
        if (!Modifier.isFinal(clazz.getModifiers())) {
            throw new IllegalArgumentException("Struct class must be final");
        }
        SootMethod _sizeOf = new SootMethod("_sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PROTECTED | Modifier.NATIVE);
        clazz.addMethod(_sizeOf);
        SootMethod sizeOf = new SootMethod("sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PUBLIC | Modifier.NATIVE | Modifier.STATIC);
        clazz.addMethod(sizeOf);
    }
    
    private void structSizeOf(SootMethod method) {
        StructureType type = getStructType(sootClass);
        if (type == null) {
            throw new IllegalArgumentException("Struct class " + sootClass + " has no @StructMember annotated methods");
        }
        Function function = createFunction(method);
        function.add(new Ret(sizeof(type)));
    }
    
    private void structMember(SootMethod method) {
        Map<String, Integer> indexes = new HashMap<String, Integer>();
        StructureType structType = getStructType(sootClass, indexes);
        if (structType == null) {
            throw new IllegalArgumentException("Struct class " + sootClass + " has not @StructMember annotated methods");
        }
        Function function = createFunction(method);
        
        // Get the value of the handle field in the Struct base class and cast it to a <structType>*
        SootField handleField = sootClass.getSuperclass().getFieldByName("handle");
        Variable handleI64 = function.newVariable(I64);
        function.add(new Load(handleI64, getInstanceFieldPtr(function, new VariableRef("this", OBJECT_PTR), handleField)));
        Variable handlePtr = function.newVariable(new PointerType(structType));
        function.add(new Inttoptr(handlePtr, handleI64.ref(), handlePtr.getType()));
        
        String name = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
        int index = indexes.get(name);        
        Type memberType = structType.getTypeAt(index);
        Variable memberPtr = function.newVariable(new PointerType(memberType));
        function.add(new Getelementptr(memberPtr, handlePtr.ref(), 0, index));
        
        if (method.getName().startsWith("get")) {
            Variable result = null;
            if (memberType instanceof StructureType) {
                result = function.newVariable(OBJECT_PTR);
                Constant structClassName = getString(getInternalName(method.getReturnType()));
                Value caller = getCaller(function);
                Variable memberI8Ptr = function.newVariable(I8_PTR);
                function.add(new Bitcast(memberI8Ptr, memberPtr.ref(), I8_PTR));
                function.add(new Call(result, NVM_BC_NEW_STRUCT, ENV, structClassName, caller, memberI8Ptr.ref()));
            } else {
                result = function.newVariable(memberType);
                function.add(new Load(result, memberPtr.ref()));
                if (result.getType() == I8_PTR) {
                    if (method.getReturnType().equals(LongType.v())) {
                        Variable tmp = function.newVariable(I64);
                        function.add(new Ptrtoint(tmp, result.ref(), I64));
                        result = tmp;
                    } else {
                        // Must be pointer to struct type
                        Variable tmp = function.newVariable(OBJECT_PTR);
                        Constant structClassName = getString(getInternalName(method.getReturnType()));
                        Value caller = getCaller(function);
                        function.add(new Call(tmp, NVM_BC_NEW_STRUCT, ENV, structClassName, caller, result.ref()));
                        result = tmp;
                    }
                }
            }
            function.add(new Ret(result.ref()));
        } else {
            VariableRef p = function.getParameterRef(2); // 'env' is parameter 0, 'this' is at 1, the value we're interested in is at index 2
            if (memberType instanceof StructureType) {
                Variable objectPtr = function.newVariable(OBJECT_PTR);
                function.add(new Bitcast(objectPtr, p, OBJECT_PTR));
                Variable memberI8Ptr = function.newVariable(I8_PTR);
                function.add(new Bitcast(memberI8Ptr, memberPtr.ref(), I8_PTR));
                function.add(new Call(NVM_BC_COPY_STRUCT, ENV, objectPtr.ref(), memberI8Ptr.ref(), sizeof((StructureType) memberType)));
            } else {
                if (memberType == I8_PTR) {
                    Variable tmp = function.newVariable(I8_PTR);
                    if (method.getParameterType(0).equals(LongType.v())) {
                        function.add(new Inttoptr(tmp, p, I8_PTR));
                        p = tmp.ref();
                    } else {
                        // Must be pointer to struct type
                        Variable objectPtr = function.newVariable(OBJECT_PTR);
                        function.add(new Bitcast(objectPtr, p, OBJECT_PTR));
                        function.add(new Call(tmp, NVM_BC_GET_STRUCT_HANDLE, ENV, objectPtr.ref()));
                        p = tmp.ref();
                    }
                }
                function.add(new Store(p, memberPtr.ref()));
            }
            function.add(new Ret());
        }
    }
    
    private void nativeMethod(SootMethod method) {
        nativeMethodCompiler.compile(mb, method);
        trampolines.addAll(nativeMethodCompiler.getTrampolines());
    }
    
    private void nativeBridgeMethod(SootMethod method) {

    }
    
    private void nativeCallbackMethod(SootMethod method) {
//        if (!method.isStatic()) {
//            throw new IllegalArgumentException("@Callback annotated method must be static: " + method);
//        }
//        if (!method.getReturnType().equals(VoidType.v()) && !(method.getReturnType() instanceof PrimType)) {
//            throw new IllegalArgumentException("@Callback annotated method must return void or primitive type");
//        }
//        for (int i = 0; i < method.getParameterCount(); i++) {
//            if (!(method.getParameterType(i) instanceof PrimType)) {
//                throw new IllegalArgumentException("@Callback annotated method must take only primitive type arguments");
//            }            
//        }
//        
//        FunctionType targetFunctionType = getFunctionType(method);
//        FunctionRef targetFunctionRef = new FunctionRef(mangleMethod(method.makeRef()), targetFunctionType);
//        
//        FunctionType callbackFunctionType = getBridgeOrCallbackFunctionType(method);
//        Type[] parameterTypes = callbackFunctionType.getParameterTypes();
//        String[] parameterNames = new String[callbackFunctionType.getParameterTypes().length];
//        for (int i = 0; i < parameterNames.length; i++) {
//            parameterNames[i] = "p" + i;
//        }
//
//        Function callbackFunction = module.newFunction(internal, new FunctionAttribute[] {noinline, optsize}, 
//                mangleMethod(method.makeRef()) + "_callback", callbackFunctionType, parameterNames);
//
//        // Increase the attach count for the current thread (attaches the thread if not attached)
//        Variable env = callbackFunction.newVariable(ENV.getName().substring(1), ENV_PTR);
//        callbackFunction.add(new Call(env, NVM_BC_ATTACH_THREAD_FROM_CALLBACK, new Value[0]));
//        
//        ArrayList<Value> args = new ArrayList<Value>();
//        args.add(env.ref());
//        for (int i = 0; i < parameterTypes.length; i++) {
//            VariableRef ref = new VariableRef(parameterNames[i], parameterTypes[i]);
//            if (ref.getType() == I8_PTR) {
//                Variable tmp = callbackFunction.newVariable(I64);
//                callbackFunction.add(new Ptrtoint(tmp, ref, I64));
//                ref = tmp.ref();
//            }
//            args.add(ref);
//        }
//        
//        // TODO: What if an uncaught exception is thrown? We need to detach the thread in such circumstances too.
//        
//        if (callbackFunction.getType().getReturnType() == VOID) {
//            callbackFunction.add(new Call(targetFunctionRef, args.toArray(new Value[args.size()])));
//            // Decrease the attach count for the current thread (detaches the thread if the count reaches 0)
//            callbackFunction.add(new Call(NVM_BC_DETACH_THREAD_FROM_CALLBACK, env.ref()));
//            callbackFunction.add(new Ret());
//        } else {
//            Variable result = callbackFunction.newVariable(targetFunctionType.getReturnType());
//            callbackFunction.add(new Call(result, targetFunctionRef, args.toArray(new Value[args.size()])));
//            if (callbackFunctionType.getReturnType() == I8_PTR) {
//                Variable resultI8Ptr = callbackFunction.newVariable(I8_PTR);
//                callbackFunction.add(new Inttoptr(resultI8Ptr, result.ref(), I8_PTR));
//                // Decrease the attach count for the current thread (detaches the thread if the count reaches 0)
//                callbackFunction.add(new Call(NVM_BC_DETACH_THREAD_FROM_CALLBACK, env.ref()));
//                callbackFunction.add(new Ret(resultI8Ptr.ref()));
//            } else {
//                // Decrease the attach count for the current thread (detaches the thread if the count reaches 0)
//                callbackFunction.add(new Call(NVM_BC_DETACH_THREAD_FROM_CALLBACK, env.ref()));
//                callbackFunction.add(new Ret(result.ref()));
//            }
//        }
    }
    
    private void method(SootMethod method) {
        methodCompiler.compile(mb, method);
        trampolines.addAll(methodCompiler.getTrampolines());
    }

    private Function createFunction(SootMethod method) {
        return createFunction(mangleMethod(method.makeRef()), method, false);
    }
    
    private Function createFunction(String name, SootMethod method) {
        return createFunction(name, method, false);
    }
        
    private Function createFunction(String name, SootMethod method, boolean skipEnv) {
        FunctionType functionType = getFunctionType(method.makeRef());
        String[] parameterNames = new String[functionType.getParameterTypes().length];
        int i = 0;
        if (!skipEnv) {
            parameterNames[i++] = "env";
        }
        if (!method.isStatic()) {
            parameterNames[i++] = "this";
        }
        for (int j = 0; j < method.getParameterCount(); j++) {
            parameterNames[i++] = "p" + j;
        }
            
        return new Function(internal, new FunctionAttribute[] {noinline, optsize}, 
                name, functionType, parameterNames);
    }
    

//    private void classLoaderFunction() {
//        String name = "NullVM_" + mangleString(getInternalName(sootClass));
//        Function function = module.newFunction(null, 
//                new FunctionAttribute[] {noinline, optsize}, 
//                name, new FunctionType(CLASS_PTR, ENV_PTR, OBJECT_PTR), 
//                "env", "classLoader");
//        
//        for (Entry<SootClass, Global> entry : throwables.entrySet()) {
//            Variable t1 = function.newVariable(OBJECT_PTR);
//            function.add(new Call(t1, NVM_BC_FIND_CLASS_IN_LOADER, 
//                    ENV, getString(getInternalName(entry.getKey())),
//                    new VariableRef("classLoader", OBJECT_PTR)));
//            Variable t2 = function.newVariable(CLASS_PTR);            
//            function.add(new Bitcast(t2, t1.ref(), CLASS_PTR));
//            function.add(new Store(t2.ref(), entry.getValue().ref()));
//        }
//        
//        Variable clazz = function.newVariable("clazz", CLASS_PTR);
//        Value superclassName = null;
//        if (sootClass.hasSuperclass() && !sootClass.isInterface()) {
//            superclassName = getString(getInternalName(sootClass.getSuperclass()));
//        } else {
//            superclassName = new NullConstant(I8_PTR);
//        }
//        
//        function.add(new Call(clazz, NVM_BC_ALLOCATE_CLASS, 
//                ENV, 
//                getString(getInternalName(sootClass)), 
//                superclassName,
//                new VariableRef("classLoader", OBJECT_PTR),
//                new IntegerConstant(sootClass.getModifiers()),
//                sizeof(classFieldsType), sizeof(instanceFieldsType)));
//
//        Constant classAttributes = encodeAttributes(sootClass);
//        if (classAttributes != null) {
//            Global g = module.newGlobal(classAttributes, true);
//            function.add(new Call(NVM_BC_SET_CLASS_ATTRIBUTES, ENV, clazz.ref(), new ConstantBitcast(g.ref(), I8_PTR)));
//        }
//        
//        for (SootClass iface : sootClass.getInterfaces()) {
//            function.add(new Call(NVM_BC_ADD_INTERFACE, ENV, clazz.ref(), getString(getInternalName(iface))));
//        }
//        
//        for (SootField field : classFields) {
//            Constant getter = new NullConstant(new FunctionType(getType(field.getType()), ENV_PTR));
//            Constant setter = new NullConstant(new FunctionType(VOID, ENV_PTR, getType(field.getType())));
//            if (!field.isPrivate()) {
//                getter = new FunctionRef(mangleField(field) + "_getter", (FunctionType) getter.getType());
//                if (!field.isFinal()) {
//                    setter = new FunctionRef(mangleField(field) + "_setter", (FunctionType) setter.getType());
//                }
//            }
//            Variable fieldPtr = function.newVariable(FIELD_PTR);
//            function.add(new Call(fieldPtr, NVM_BC_ADD_FIELD, ENV, clazz.ref(),
//                    getString(field.getName()),
//                    getString(getDescriptor(field.getType())),
//                    new IntegerConstant(field.getModifiers()),
//                    offsetof(classFieldsType, classFields.indexOf(field)),
//                    new ConstantBitcast(getter, I8_PTR), 
//                    new ConstantBitcast(setter, I8_PTR)));
//            
//            Constant fieldAttributes = encodeAttributes(field);
//            if (fieldAttributes != null) {
//                Global g = module.newGlobal(fieldAttributes, true);
//                function.add(new Call(NVM_BC_SET_FIELD_ATTRIBUTES, ENV, fieldPtr.ref(), new ConstantBitcast(g.ref(), I8_PTR)));
//            }
//        }
//        for (SootField field : instanceFields) {
//            Constant getter = new NullConstant(new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR));
//            Constant setter = new NullConstant(new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())));
//            if (!field.isPrivate()) {
//                getter = new FunctionRef(mangleField(field) + "_getter", (FunctionType) getter.getType());
//                if (!field.isFinal()) {
//                    setter = new FunctionRef(mangleField(field) + "_setter", (FunctionType) setter.getType());
//                }
//            }
//            Variable fieldPtr = function.newVariable(FIELD_PTR);
//            function.add(new Call(fieldPtr, NVM_BC_ADD_FIELD, ENV, clazz.ref(),
//                    getString(field.getName()),
//                    getString(getDescriptor(field.getType())),
//                    new IntegerConstant(field.getModifiers()),
//                    offsetof(instanceFieldsType, instanceFields.indexOf(field)),
//                    new ConstantBitcast(getter, I8_PTR), 
//                    new ConstantBitcast(setter, I8_PTR)));
//            
//            Constant fieldAttributes = encodeAttributes(field);
//            if (fieldAttributes != null) {
//                Global g = module.newGlobal(fieldAttributes, true);
//                function.add(new Call(NVM_BC_SET_FIELD_ATTRIBUTES, ENV, fieldPtr.ref(), new ConstantBitcast(g.ref(), I8_PTR)));
//            }
//        }
//        
//        if (!sootClass.declaresMethodByName("<clinit>") && hasConstantValueTags(classFields)) {
//            Value functionRef = new ConstantBitcast(
//                    new FunctionRef(mangleMethod(getInternalName(sootClass), "<clinit>", 
//                                new ArrayList<soot.Type>(), soot.VoidType.v()), 
//                            new FunctionType(VOID, ENV_PTR)), I8_PTR);
//            Variable methodPtr = function.newVariable(METHOD_PTR);
//            function.add(new Call(methodPtr, NVM_BC_ADD_METHOD, ENV, clazz.ref(),
//                    getString("<clinit>"),
//                    getString("()V"),
//                    new IntegerConstant(Modifier.STATIC),
//                    functionRef,
//                    new NullConstant(I8_PTR),
//                    new NullConstant(I8_PTR)));
//        }
//
//        for (SootMethod method : sootClass.getMethods()) {
//            Value functionRef = new NullConstant(I8_PTR);
//            Value synchronizedRef = new NullConstant(I8_PTR);
//            Value lookup = new NullConstant(I8_PTR);
//            if (!method.isAbstract()) {
//                functionRef = new ConstantBitcast(new FunctionRef(mangleMethod(method), 
//                        getFunctionType(method)), I8_PTR);
//            }
//            if (!method.isAbstract() && method.isSynchronized()) {
//                synchronizedRef = new ConstantBitcast(new FunctionRef(mangleMethod(method) + "_synchronized", 
//                        getFunctionType(method)), I8_PTR);
//            }            
//            if (!method.isStatic() && !"<init>".equals(method.getName()) 
//                    && !method.isPrivate() && !Modifier.isFinal(method.getModifiers())) {
//                // Virtual method. If not defined in a superclass we need to create a virtual lookup function now.
//                if (!ancestorDeclaresMethod(sootClass, method)) {
//                    lookup = new ConstantBitcast(new FunctionRef(mangleMethod(method) + "_lookup", 
//                            getFunctionType(method)), I8_PTR);
//                }
//            }
//            Variable methodPtr = function.newVariable(METHOD_PTR);
//            if (isBridge(method)) {
//                GlobalRef targetFunction = new GlobalRef(mangleMethod(method) + "_ptr", getBridgeOrCallbackFunctionType(method));
//                function.add(new Call(methodPtr, NVM_BC_ADD_BRIDGE_METHOD, ENV, clazz.ref(),
//                        getString(method.getName()),
//                        getString(getDescriptor(method)),
//                        new IntegerConstant(method.getModifiers() | Modifier.NATIVE),
//                        functionRef,
//                        synchronizedRef,
//                        lookup,
//                        new ConstantBitcast(targetFunction, I8_PTR)));
//            } else if (isCallback(method)) {
//                Value callbackRef = new ConstantBitcast(new FunctionRef(mangleMethod(method) + "_callback", 
//                        getBridgeOrCallbackFunctionType(method)), I8_PTR);
//                function.add(new Call(methodPtr, NVM_BC_ADD_CALLBACK_METHOD, ENV, clazz.ref(),
//                        getString(method.getName()),
//                        getString(getDescriptor(method)),
//                        new IntegerConstant(method.getModifiers() | Modifier.NATIVE),
//                        functionRef,
//                        synchronizedRef,
//                        lookup,
//                        callbackRef));
//            } else {
//                function.add(new Call(methodPtr, NVM_BC_ADD_METHOD, ENV, clazz.ref(),
//                        getString(method.getName()),
//                        getString(getDescriptor(method)),
//                        new IntegerConstant(method.getModifiers()),
//                        functionRef,
//                        synchronizedRef,
//                        lookup));
//            }
//            
//            Constant methodAttributes = encodeAttributes(method);
//            if (methodAttributes != null) {
//                Global g = module.newGlobal(methodAttributes, true);
//                function.add(new Call(NVM_BC_SET_METHOD_ATTRIBUTES, ENV, methodPtr.ref(), new ConstantBitcast(g.ref(), I8_PTR)));
//            }
//        }
//        
//        function.add(new Call(NVM_BC_REGISTER_CLASS, ENV, clazz.ref()));
//        
//        function.add(new Store(clazz.ref(), THE_CLASS.ref()));
//        
//        function.add(new Ret(clazz.ref()));
//    }
    
    private Function createAllocator() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_allocator", new FunctionType(OBJECT_PTR, ENV_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, NVM_BC_ALLOCATE, fn.getParameterRef(0), info);
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createInstanceof() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_instanceof", new FunctionType(I32, ENV_PTR, OBJECT_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, NVM_BC_INSTANCEOF, fn.getParameterRef(0), info, fn.getParameterRef(1));
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createCheckcast() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_checkcast", new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, NVM_BC_CHECKCAST, fn.getParameterRef(0), info, fn.getParameterRef(1));
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createLdcClass() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_ldc", new FunctionType(OBJECT_PTR, ENV_PTR));
        Value info = getInfoStruct(fn);
        Variable infoObjectPtr = fn.newVariable(new PointerType(OBJECT_PTR));
        fn.add(new Bitcast(infoObjectPtr, info, infoObjectPtr.getType()));
        Variable result = fn.newVariable(OBJECT_PTR);
        fn.add(new Load(result, infoObjectPtr.ref()));
        fn.add(new Ret(result.ref()));
        return fn;
    }
    
    private Function createFieldGetter(SootField field) {
        String name = mangleField(field) + "_getter";
        Function fn = null;
        Value fieldPtr = null;
        if (field.isStatic()) {
            fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(getType(field.getType()), ENV_PTR), "env");
            fieldPtr = getClassFieldPtr(fn, field);
        } else {
            fn = new Function(field.isPrivate() ? _private : external, 
                    new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(getType(field.getType()), ENV_PTR, OBJECT_PTR), "env", "this");
            fieldPtr = getInstanceFieldPtr(fn, new VariableRef("this", OBJECT_PTR), field);
        }
        Variable result = fn.newVariable(getType(field.getType()));
        fn.add(new Load(result, fieldPtr));
        fn.add(new Ret(new VariableRef(result)));
        return fn;
    }
    
    private Function createFieldSetter(SootField field) {
        String name = mangleField(field) + "_setter";
        Function fn = null;
        Value fieldPtr = null;
        if (field.isStatic()) {
            fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(VOID, ENV_PTR, getType(field.getType())), "env", "value");
            fieldPtr = getClassFieldPtr(fn, field);
        } else {
            fn = new Function(field.isPrivate() || field.isFinal() ? _private : external, 
                    new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())), "env", "this", "value");
            fieldPtr = getInstanceFieldPtr(fn, new VariableRef("this", OBJECT_PTR), field);
        }
        fn.add(new Store(new VariableRef("value", getType(field.getType())), fieldPtr));
        fn.add(new Ret());
        return fn;
    }
    
    private Function createClassInitWrapperFunction(FunctionRef targetFn) {
        String fnName = targetFn.getName() + "_clinit";
        Function fn = new Function(external, new FunctionAttribute[] {noinline, optsize},
                fnName, targetFn.getType());
        Value info = getInfoStruct(fn);
        Variable infoHeader = fn.newVariable(new PointerType(new StructureType(I8_PTR, I32)));
        fn.add(new Bitcast(infoHeader, info, infoHeader.getType()));
        Variable infoHeaderFlags = fn.newVariable(new PointerType(I32));
        fn.add(new Getelementptr(infoHeaderFlags, infoHeader.ref(), 0, 1));
        Variable flags = fn.newVariable(I32);
        fn.add(new Load(flags, infoHeaderFlags.ref()));
        Variable initializedFlag = fn.newVariable(I32);
        fn.add(new And(initializedFlag, flags.ref(), new IntegerConstant(CI_INITIALIZED)));
        Variable initialized = fn.newVariable(I1);
        fn.add(new Icmp(initialized, Icmp.Condition.eq, initializedFlag.ref(), new IntegerConstant(CI_INITIALIZED)));
        Label trueLabel = new Label();
        Label falseLabel = new Label();
        fn.add(new Br(initialized.ref(), fn.newBasicBlockRef(trueLabel), fn.newBasicBlockRef(falseLabel)));
        fn.newBasicBlock(trueLabel);
        Value result = call(fn, targetFn, fn.getParameterRefs());
        fn.add(new Ret(result));
        fn.newBasicBlock(falseLabel);
        call(fn, NVM_BC_INITIALIZE_CLASS, fn.getParameterRef(0), info);
        fn.add(new Br(fn.newBasicBlockRef(trueLabel)));
        return fn;
    }
    
    private static List<SootField> getFields(SootClass clazz, boolean ztatic) {
        List<SootField> l = new ArrayList<SootField>();
        for (SootField f : clazz.getFields()) {
            if (ztatic == f.isStatic()) {
                l.add(f);
            }
        }
        return l;
    }
    
    private static List<SootField> getClassFields(SootClass clazz) {
        return getFields(clazz, true);
    }
    
    private static List<SootField> getInstanceFields(SootClass clazz) {
        return getFields(clazz, false);
    }
    
    private static boolean hasConstantValueTags(List<SootField> classFields) {
        for (SootField field : classFields) {
            for (Tag tag : field.getTags()) {
                if (tag instanceof ConstantValueTag) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void validateStructMember(SootMethod method) {
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
    
    private static StructureType getStructureType(List<SootField> fields) {
        List<Type> types = new ArrayList<Type>();
        for (SootField field : fields) {
            types.add(getType(field.getType()));
        }
        if (!types.isEmpty()) {
            return new StructureType(types.toArray(new Type[types.size()]));
        }
        return null;
    }
    
    private Constant getString(String string) {
        return mb.getString(string);
    }
    
    private Constant getStringOrNull(String string) {
        return mb.getStringOrNull(string);
    }
    
    private Value getCaller(Function f) {
        Variable caller = f.newVariable(CLASS_PTR);
        f.add(new Load(caller, THE_CLASS.ref()));            
        return caller.ref();
    }
    
    private Value getClassFieldPtr(Function f, SootField field) {
        Value info = getInfoStruct(f);        
        Variable base = f.newVariable(I8_PTR);
        f.add(new Load(base, info));
        return getFieldPtr(f, new VariableRef(base), alignedSizeof(CLASS), field, classFields.indexOf(field), classFieldsType);
    }

    private Value getInfoStruct(Function f) {
        return Functions.getInfoStruct(f, sootClass);
    }
    
    private Value getInstanceFieldPtr(Function f, Value base, SootField field) {
        Value offset = new IntegerConstant(0);
        if (sootClass.hasSuperclass() && !"java.lang.Object".equals(sootClass.getSuperclass().getName())) {
            // Assume that java.lang.Object has no instance fields
            Variable v = f.newVariable(I32);
            f.add(new Load(v, new GlobalRef(mangleClass(sootClass) + "_offset", I32)));
            offset = v.ref();
        }
        Variable baseI8Ptr = f.newVariable(I8_PTR);
        f.add(new Bitcast(baseI8Ptr, base, I8_PTR));
        Variable newBase = f.newVariable(I8_PTR);
        f.add(new Getelementptr(newBase, baseI8Ptr.ref(), offset));        
        return getFieldPtr(f, newBase.ref(), sizeof(OBJECT), field, instanceFields.indexOf(field), instanceFieldsType);
    }
    
    private Value getFieldPtr(Function f, Value base, Constant baseOffset, SootField field, int index, StructureType fieldsType) {
        Value offset = new ConstantAdd(baseOffset, offsetof(fieldsType, index));
        Variable baseI8Ptr = f.newVariable(I8_PTR);
        f.add(new Bitcast(baseI8Ptr, base, I8_PTR));
        Variable fieldI8Ptr = f.newVariable(I8_PTR);
        f.add(new Getelementptr(fieldI8Ptr, baseI8Ptr.ref(), offset));
        Variable fieldPtr = f.newVariable(new PointerType(getType(field.getType())));
        f.add(new Bitcast(fieldPtr, fieldI8Ptr.ref(), fieldPtr.getType()));
        return fieldPtr.ref();
    }
}
