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

import static org.robovm.compiler.Access.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.ClazzInfo;
import org.robovm.compiler.clazz.ClazzInfo.FieldInfo;
import org.robovm.compiler.clazz.ClazzInfo.MethodInfo;
import org.robovm.compiler.llvm.And;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Fence;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionAttribute;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Ordering;
import org.robovm.compiler.llvm.PackedStructureConstantBuilder;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureConstant;
import org.robovm.compiler.llvm.StructureConstantBuilder;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.ExceptionMatch;
import org.robovm.compiler.trampoline.Trampoline;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.Modifier;
import soot.PrimType;
import soot.RefLikeType;
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
    private static final int DUMMY_METHOD_SIZE = 0x01abcdef;
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
    
    private StructureType classType;
    private StructureType instanceType;
    
    private final Config config;
    private final MethodCompiler methodCompiler;
    private final BridgeMethodCompiler bridgeMethodCompiler;
    private final NativeMethodCompiler nativeMethodCompiler;
    private final StructMemberMethodCompiler structMemberMethodCompiler;
    private final AttributesEncoder attributesEncoder;
    private final TrampolineCompiler trampolineResolver;
    
    public ClassCompiler(Config config) {
        this.config = config;
        this.methodCompiler = new MethodCompiler(config);
        this.bridgeMethodCompiler = new BridgeMethodCompiler(config);
        this.nativeMethodCompiler = new NativeMethodCompiler(config);
        this.structMemberMethodCompiler = new StructMemberMethodCompiler(config);
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

            patchAsmWithFunctionSizes(clazz, sFile);
            
            config.getLogger().debug("Assembling %s", clazz);
            CompilerUtil.assemble(config, sFile, oFile);

            recompiled = true;
        }
        
        return recompiled;
    }
    
    private void patchAsmWithFunctionSizes(Clazz clazz, File sFile) throws IOException {
        Set<String> functionNames = new HashSet<String>();
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            if (!method.isAbstract()) {
                String name = mangleMethod(method);
                if (config.getOs().getFamily() == OS.Family.darwin) {
                    name = "_" + name;
                }                
                functionNames.add(name);
            }
        }
        
        String localLabelPrefix = ".L";
        String prefix = mangleClass(clazz.getInternalName());
        if (config.getOs().getFamily() == OS.Family.darwin) {
            localLabelPrefix = "L";
            prefix = "_" + prefix;
        }
        String infoStructLabel = prefix + "_info_struct";
        Pattern methodImplPattern = Pattern.compile("\\s*\\.(?:quad|long)\\s+(" + Pattern.quote(prefix) + "[^\\s]+).*");
        
        File outFile = new File(sFile.getParentFile(), sFile.getName() + ".tmp");
        
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(sFile), "UTF-8"));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            String line = null;
            String currentFunction = null;
            while ((line = in.readLine()) != null) {
                if (currentFunction == null) {
                    out.write(line);
                    out.write('\n');
                    if (line.startsWith(prefix)) {
                        int colon = line.indexOf(':');
                        if (colon == -1) {
                            continue;
                        }
                        String label = line.substring(0, colon);
                        if (functionNames.contains(label)) {
                            currentFunction = label;
                        } else if (label.equals(infoStructLabel)) {
                            break;
                        }
                    }
                } else if (line.trim().equals(".cfi_endproc") || line.trim().startsWith(".section") || line.trim().startsWith(".globl")) {
                    out.write(localLabelPrefix);
                    out.write(currentFunction);
                    out.write("_end:\n\n");
                    currentFunction = null;
                    out.write(line);
                    out.write('\n');
                } else {
                    out.write(line);
                    out.write('\n');
                }
            }
            
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.write('\n');
                if (line.contains(prefix)) {
                    Matcher matcher = methodImplPattern.matcher(line);
                    if (matcher.matches()) {
                        String functionName = matcher.group(1);
                        if (functionNames.contains(functionName)) {
                            line = in.readLine();
                            if (line.contains(String.valueOf(DUMMY_METHOD_SIZE))) {
                                out.write("\t.long\t");
                                out.write(localLabelPrefix + functionName + "_end - " + functionName);
                                out.write('\n');
                            } else {
                                out.write(line);
                                out.write('\n');                                
                            }
                        }
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
        
        sFile.renameTo(new File(sFile.getParentFile(), sFile.getName() + ".orig"));
        outFile.renameTo(sFile);
    }
    
    private void reset() {
        sootClass = null;
        mb = null;
        trampolines = null;
        classFields = null;
        instanceFields = null;
        classType = null;
        instanceType = null;
    }
    
    private void compile(Clazz clazz, OutputStream out) throws IOException {
        sootClass = clazz.getSootClass();
        mb = new ModuleBuilder();
        trampolines = new HashSet<Trampoline>();
        classFields = getClassFields(sootClass);
        instanceFields = getInstanceFields(sootClass);
        classType = getClassType(sootClass);
        instanceType = getInstanceType(sootClass);
        
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

        if (isStruct(sootClass)) {
            if (!Modifier.isFinal(sootClass.getModifiers())) {
                throw new IllegalArgumentException("Struct class must be final");
            }
            SootMethod _sizeOf = new SootMethod("_sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PROTECTED);
            sootClass.addMethod(_sizeOf);
            SootMethod sizeOf = new SootMethod("sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PUBLIC | Modifier.STATIC);
            sootClass.addMethod(sizeOf);
        }
        
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));

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
            String name = method.getName();
            if (isBridge(method)) {
                bridgeMethod(method);
            } else if (isStruct(sootClass) && ("_sizeOf".equals(name) 
                        || "sizeOf".equals(name) || isStructMember(method))) {
                structMember(method);
            } else if (method.isNative()) {
                nativeMethod(method);
            } else if (!method.isAbstract()) {
                method(method);
            }
            if (!name.equals("<clinit>") && !name.equals("<init>") 
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

        Global classInfoStruct = null;
        StructureConstant classInfoErrorStruct = createClassInfoErrorStruct();
        if (classInfoErrorStruct != null) {
            // The class cannot be loaded at runtime. Replace the ClassInfo struct
            // with a ClassInfoError struct with details of why.
            classInfoStruct = new Global(mangleClass(sootClass) + "_info_struct", classInfoErrorStruct);
        } else {
            classInfoStruct = new Global(mangleClass(sootClass) + "_info_struct", createClassInfoStruct());
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
    }
    
    private void createLookupFunction(SootMethod m) {
        // TODO: This should use a virtual method table or interface method table.
        String name = mangleMethod(m) + "_lookup";
        Function function = new Function(external, name, getFunctionType(m));
        mb.addFunction(function);

        Variable reserved0 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved0, function.getParameterRef(0), 0, 4));
        Variable reserved1 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved1, function.getParameterRef(0), 0, 5));
        function.add(new Store(getString(m.getName()), reserved0.ref()));
        function.add(new Store(getString(getDescriptor(m)), reserved1.ref()));
        
        Value lookupFn = sootClass.isInterface() 
                ? BC_LOOKUP_INTERFACE_METHOD : BC_LOOKUP_VIRTUAL_METHOD;
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
        header.add(sizeof(classType));
        header.add(sizeof(instanceType));
        if (!instanceFields.isEmpty()) {
            header.add(offsetof(instanceType, 1, 1));
        } else {
            header.add(sizeof(instanceType));
        }

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
        List<SootField> fields = new ArrayList<SootField>();
        fields.addAll(classFields);
        fields.addAll(instanceFields);
        for (SootField f : fields) {
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
                body.add(offsetof(classType, 1, classFieldCounter++));
            } else {
                body.add(offsetof(instanceType, 1, 1 + instanceFieldCounter++));
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
                if (!isStruct(sootClass) && !isStructMember(m)) {
                    flags |= MI_NATIVE;
                }
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
            if (!m.isAbstract()) {
                body.add(new ConstantBitcast(new FunctionRef(mangleMethod(m), getFunctionType(m)), I8_PTR));
                body.add(new IntegerConstant(DUMMY_METHOD_SIZE)); // Size of function. This value will be modified later by patching the .s file.
                if (m.isSynchronized()) {
                    body.add(new ConstantBitcast(new FunctionRef(mangleMethod(m) + "_synchronized", getFunctionType(m)), I8_PTR));
                }
            }
            if (isBridge(m)) {
                body.add(new GlobalRef(BridgeMethodCompiler.getTargetFnPtrName(m), I8_PTR));
            }
            if (isCallback(m)) {
                body.add(new ConstantBitcast(new FunctionRef(mangleMethod(m) + "_callback", getCallbackFunctionType(m)), I8_PTR));
            }
        }
        
        // Return the struct {header, body}. To be compatible with the C code in classinfo.c 
        // it is important that the header is padded the same as in C so that the body starts
        // after sizeof(ClassInfoHeader) bytes.
        return new StructureConstantBuilder().add(header.build()).add(body.build()).build();
    }
    
    private void nativeMethod(SootMethod method) {
        nativeMethodCompiler.compile(mb, method);
        trampolines.addAll(nativeMethodCompiler.getTrampolines());
    }
    
    private void bridgeMethod(SootMethod method) {
        bridgeMethodCompiler.compile(mb, method);
        trampolines.addAll(bridgeMethodCompiler.getTrampolines());
    }
    
    private void structMember(SootMethod method) {
        structMemberMethodCompiler.compile(mb, method);
        trampolines.addAll(structMemberMethodCompiler.getTrampolines());
    }
    
    private void method(SootMethod method) {
        methodCompiler.compile(mb, method);
        trampolines.addAll(methodCompiler.getTrampolines());
    }
    
    private Function createAllocator() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_allocator", new FunctionType(OBJECT_PTR, ENV_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, BC_ALLOCATE, fn.getParameterRef(0), info);
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createInstanceof() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_instanceof", new FunctionType(I32, ENV_PTR, OBJECT_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, BC_INSTANCEOF, fn.getParameterRef(0), info, fn.getParameterRef(1));
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createCheckcast() {
        Function fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                mangleClass(sootClass) + "_checkcast", new FunctionType(OBJECT_PTR, ENV_PTR, OBJECT_PTR));
        Value info = getInfoStruct(fn);        
        Value result = call(fn, BC_CHECKCAST, fn.getParameterRef(0), info, fn.getParameterRef(1));
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
        if (Modifier.isVolatile(field.getModifiers())) {
            fn.add(new Fence(Ordering.seq_cst));
            if (LongType.v().equals(field.getType())) {
                fn.add(new Load(result, fieldPtr, Ordering.unordered, 8));
            } else {
                fn.add(new Load(result, fieldPtr));
            }
        } else {
            fn.add(new Load(result, fieldPtr));
        }
        fn.add(new Ret(new VariableRef(result)));
        return fn;
    }
    
    private Function createFieldSetter(SootField field) {
        String name = mangleField(field) + "_setter";
        Function fn = null;
        Value fieldPtr = null;
        Value value = null;
        if (field.isStatic()) {
            fn = new Function(_private, new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(VOID, ENV_PTR, getType(field.getType())), "env", "value");
            fieldPtr = getClassFieldPtr(fn, field);
            value = fn.getParameterRef(1);
        } else {
            fn = new Function(field.isPrivate() || field.isFinal() ? _private : external, 
                    new FunctionAttribute[] {alwaysinline, optsize}, 
                    name, new FunctionType(VOID, ENV_PTR, OBJECT_PTR, getType(field.getType())), "env", "this", "value");
            fieldPtr = getInstanceFieldPtr(fn, new VariableRef("this", OBJECT_PTR), field);
            value = fn.getParameterRef(2);
        }
        if (Modifier.isVolatile(field.getModifiers()) || !field.isStatic() && Modifier.isFinal(field.getModifiers())) {
            if (LongType.v().equals(field.getType())) {
                fn.add(new Store(value, fieldPtr, Ordering.unordered, 8));
            } else {
                fn.add(new Store(value, fieldPtr));
            }
            fn.add(new Fence(Ordering.seq_cst));
        } else {
            fn.add(new Store(value, fieldPtr));
        }
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
        call(fn, BC_INITIALIZE_CLASS, fn.getParameterRef(0), info);
        fn.add(new Br(fn.newBasicBlockRef(trueLabel)));
        return fn;
    }

    private static int getFieldSize(SootField f) {
        if (LongType.v().equals(f.getType()) || DoubleType.v().equals(f.getType())) {
            return 8;
        }
        if (IntType.v().equals(f.getType()) || FloatType.v().equals(f.getType())) {
            return 4;
        }
        if (f.getType() instanceof RefLikeType) {
            // Assume pointers are 32-bit
            return 4;
        }
        if (ShortType.v().equals(f.getType()) || CharType.v().equals(f.getType())) {
            return 2;
        }
        return 1;
    }
    
    private static List<SootField> getFields(SootClass clazz, boolean ztatic) {
        List<SootField> l = new ArrayList<SootField>();
        for (SootField f : clazz.getFields()) {
            if (ztatic == f.isStatic()) {
                l.add(f);
            }
        }
        // sort the fields by size. longs/doubles come first.
        Collections.sort(l, new Comparator<SootField>() {
            @Override
            public int compare(SootField o1, SootField o2) {
                int size1 = getFieldSize(o1);
                int size2 = getFieldSize(o2);
                if (size1 > size2) {
                    return -1;
                }
                if (size1 < size2) {
                    return 1;
                }
                return 0;
            }
        });
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
    
    private static StructureType getClassType(SootClass clazz) {
        List<Type> types = new ArrayList<Type>();
        for (SootField field : getClassFields(clazz)) {
            types.add(getType(field.getType()));
        }
        return new StructureType(CLASS, new StructureType(types.toArray(new Type[types.size()])));
    }
    
    private static StructureType getInstanceType0(SootClass clazz, int alignment) {
        List<Type> types = new ArrayList<Type>();
        List<SootField> fields = getInstanceFields(clazz);
        int superAlignment = 1;
        if (!fields.isEmpty()) {
            // Pad the super type so that the first field is aligned properly
            SootField field = fields.get(0);
            superAlignment = getFieldSize(field);
        }
        if (clazz.hasSuperclass()) {
            types.add(getInstanceType0(clazz.getSuperclass(), superAlignment));
        }
        int size = 0;
        for (SootField field : fields) {
            size += getFieldSize(field);
            types.add(getType(field.getType()));
        }
        if (size > 0 && alignment > 1) {
            int padding = alignment - size % alignment;
            for (int i = 0; i < padding; i++) {
                types.add(I8);
            }
        }
        return new StructureType(types.toArray(new Type[types.size()]));
    }
    
    private static StructureType getInstanceType(SootClass clazz) {
        return new StructureType(OBJECT, getInstanceType0(clazz, 1));
    }
    
    private Constant getString(String string) {
        return mb.getString(string);
    }
    
    private Constant getStringOrNull(String string) {
        return mb.getStringOrNull(string);
    }
    
    private Value getClassFieldPtr(Function f, SootField field) {
        Value info = getInfoStruct(f);        
        Variable base = f.newVariable(I8_PTR);
        f.add(new Load(base, info));
        return getFieldPtr(f, new VariableRef(base), offsetof(classType, 1, 
                classFields.indexOf(field)), getType(field.getType()));
    }

    private Value getInfoStruct(Function f) {
        return Functions.getInfoStruct(f, sootClass);
    }
    
    private Value getInstanceFieldPtr(Function f, Value base, SootField field) {
        return getFieldPtr(f, base, offsetof(instanceType, 1, 
                1 + instanceFields.indexOf(field)), getType(field.getType()));
    }
}
