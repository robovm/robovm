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

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.ClazzInfo;
import org.robovm.compiler.clazz.Dependency;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.llvm.AliasRef;
import org.robovm.compiler.llvm.And;
import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Fence;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionDeclaration;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.Getelementptr;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Linkage;
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
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.plugin.CompilerPlugin;
import org.robovm.compiler.trampoline.FieldAccessor;
import org.robovm.compiler.trampoline.Invoke;
import org.robovm.compiler.trampoline.Trampoline;
import org.robovm.llvm.Context;
import org.robovm.llvm.LineInfo;
import org.robovm.llvm.Module;
import org.robovm.llvm.ObjectFile;
import org.robovm.llvm.PassManager;
import org.robovm.llvm.Symbol;
import org.robovm.llvm.Target;
import org.robovm.llvm.TargetMachine;
import org.robovm.llvm.binding.CodeGenFileType;
import org.robovm.llvm.binding.CodeGenOptLevel;

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
    public static final int CI_FINALIZABLE = 0x400;

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
    private Set<String> catches;
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
    private final CallbackMethodCompiler callbackMethodCompiler;
    private final NativeMethodCompiler nativeMethodCompiler;
    private final StructMemberMethodCompiler structMemberMethodCompiler;
    private final GlobalValueMethodCompiler globalValueMethodCompiler;
    private final AttributesEncoder attributesEncoder;
    private final TrampolineCompiler trampolineResolver;
    
    private final ByteArrayOutputStream output = new ByteArrayOutputStream(256 * 1024);
    
    public ClassCompiler(Config config) {
        this.config = config;
        this.methodCompiler = new MethodCompiler(config);
        this.bridgeMethodCompiler = new BridgeMethodCompiler(config);
        this.callbackMethodCompiler = new CallbackMethodCompiler(config);
        this.nativeMethodCompiler = new NativeMethodCompiler(config);
        this.structMemberMethodCompiler = new StructMemberMethodCompiler(config);
        this.globalValueMethodCompiler = new GlobalValueMethodCompiler(config);
        this.attributesEncoder = new AttributesEncoder();
        this.trampolineResolver = new TrampolineCompiler(config);
    }
    
    public boolean mustCompile(Clazz clazz) {
        File oFile = config.getOFile(clazz);
        if (!oFile.exists() || oFile.lastModified() < clazz.lastModified() || oFile.length() == 0) {
            return true;
        }
        
        ClazzInfo ci = clazz.getClazzInfo();
        if (ci == null) {
            return true;
        }
        
        Set<Dependency> dependencies = ci.getDependencies();
        for (Dependency dep : dependencies) {
            Clazz depClazz = config.getClazzes().load(dep.getClassName());
            if (depClazz == null) {
                if (dep.getPath() != null) {
                    // depClazz was available the last time clazz was compiled but is now gone
                    return true;
                }
            } else {
                if (dep.getPath() == null) {
                    // depClazz was not available the last time clazz was compiled but is now available
                    return true;
                }
                if (!dep.getPath().equals(depClazz.getPath().getFile().getAbsolutePath())) {
                    // depClazz was located in another place the last time clazz was built
                    return true;
                }
                if (depClazz.isInBootClasspath() != dep.isInBootClasspath()) {
                    // depClazz has moved to/from the bootclasspath since the last time clazz was built
                    return true;
                }
                if (depClazz.lastModified() > oFile.lastModified()) {
                    // depClazz has been changed since the last time clazz was built 
                    return true;
                }
            }
        }
        
        // No class or interface has zero dependencies (we always add java.lang.Object as a dependency)
        // If dependencies is empty it probably means that an error occurred while reading the
        // serialized dependencies. By returning true here in that case the class will be recompiled
        // and the dependencies regenerated.
        return dependencies.isEmpty();
    }
    
    public void compile(Clazz clazz, Executor executor, ClassCompilerListener listener) throws IOException {
        reset();        
        
        Arch arch = config.getArch();
        OS os = config.getOs();

        try {
            config.getLogger().debug("Compiling %s (%s %s %s)", clazz, os, arch, config.isDebug() ? "debug" : "release");
            output.reset();
            compile(clazz, output);
        } catch (Throwable t) {
            if (t instanceof IOException) {
                throw (IOException) t;
            }
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            throw new RuntimeException(t);
        }

        scheduleMachineCodeGeneration(executor, listener, config, clazz, output.toByteArray());
    }

    private static void scheduleMachineCodeGeneration(Executor executor, final ClassCompilerListener listener,
            final Config config, final Clazz clazz, final byte[] llData) {
        
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        generateMachineCode(config, clazz, llData);
                        listener.success(clazz);
                    } catch (Throwable t) {
                        listener.failure(clazz, t);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            // Ignore. The executor has been shutdown for some reason.
        }
    }
    
    private static void generateMachineCode(Config config, Clazz clazz, byte[] llData) throws IOException {

        if (config.isDumpIntermediates()) {
            File llFile = config.getLlFile(clazz);
            llFile.getParentFile().mkdirs();
            FileUtils.writeByteArrayToFile(llFile, llData);
        }

        try (Context context = new Context()) {
            try (Module module = Module.parseIR(context, llData, clazz.getClassName())) {
                try (PassManager passManager = createPassManager(config)) {
                    passManager.run(module);
                }

                if (config.isDumpIntermediates()) {
                    File bcFile = config.getBcFile(clazz);
                    bcFile.getParentFile().mkdirs();
                    module.writeBitcode(bcFile);
                }

                String triple = config.getTriple();
                Target target = Target.lookupTarget(triple);
                try (TargetMachine targetMachine = target.createTargetMachine(triple,
                        "generic", null, config.isDebug()? CodeGenOptLevel.CodeGenLevelNone: null, null, null)) {
                    targetMachine.setAsmVerbosityDefault(true);
                    targetMachine.setFunctionSections(true);
                    targetMachine.setDataSections(true);
                    targetMachine.getOptions().setNoFramePointerElim(true);                    

                    ByteArrayOutputStream output = new ByteArrayOutputStream(256 * 1024);
                    targetMachine.emit(module, output, CodeGenFileType.AssemblyFile);

                    byte[] asm = output.toByteArray();
                    output.reset();
                    patchAsmWithFunctionSizes(config, clazz, new ByteArrayInputStream(asm), output);
                    asm = output.toByteArray();

                    if (config.isDumpIntermediates()) {
                        File sFile = config.getSFile(clazz);
                        sFile.getParentFile().mkdirs();
                        FileUtils.writeByteArrayToFile(sFile, asm);
                    }

                    File oFile = config.getOFile(clazz);
                    oFile.getParentFile().mkdirs();
                    try (BufferedOutputStream oOut = new BufferedOutputStream(new FileOutputStream(oFile))) {
                        targetMachine.assemble(asm, clazz.getClassName(), oOut);
                    }

                    /*
                     * Read out line number info from the .o file if any and
                     * assemble into a separate .o file.
                     */
                    String symbolPrefix = config.getOs().getFamily() == OS.Family.darwin ? "_" : "";
                    symbolPrefix += Symbols.EXTERNAL_SYMBOL_PREFIX;
                    ModuleBuilder linesMb = null;
                    try (ObjectFile objectFile = ObjectFile.load(oFile)) {
                        for (Symbol symbol : objectFile.getSymbols()) {
                            if (symbol.getSize() > 0 && symbol.getName().startsWith(symbolPrefix)) {
                                List<LineInfo> lineInfos = objectFile.getLineInfos(symbol);
                                if (!lineInfos.isEmpty()) {
                                    Collections.sort(lineInfos, new Comparator<LineInfo>() {
                                        public int compare(LineInfo o1, LineInfo o2) {
                                            return Long.compare(o1.getAddress(), o2.getAddress());
                                        }
                                    });
                                    
                                    // The base address of the method which will be used to calculate offsets into the method
                                    long baseAddress = symbol.getAddress();
                                    // The first line number in the method. All other line numbers in the table will be deltas against this.
                                    int firstLineNumber = lineInfos.get(0).getLineNumber();
                                    // Calculate the max address and line number offsets
                                    long maxAddressOffset = 0;
                                    long maxLineOffset = 0;
                                    for (LineInfo lineInfo : lineInfos) {
                                        maxAddressOffset = Math.max(maxAddressOffset, lineInfo.getAddress() - baseAddress);
                                        maxLineOffset = Math.max(maxLineOffset, lineInfo.getLineNumber() - firstLineNumber);
                                    }

                                    // Calculate the number of bytes needed to represent the highest offsets.
                                    // Either 1, 2 or 4 bytes will be used.
                                    int addressOffsetSize = (maxAddressOffset & ~0xff) == 0 ? 1 : ((maxAddressOffset & ~0xffff) == 0 ? 2 : 4);
                                    int lineOffsetSize = (maxLineOffset & ~0xff) == 0 ? 1 : ((maxLineOffset & ~0xffff) == 0 ? 2 : 4);
                                    
                                    // The size of the address offsets table. We skip the first LineInfo as its offset is always 0.
                                    int addressOffsetTableSize = addressOffsetSize * (lineInfos.size() - 1);
                                    // Pad size of address offset table to make sure line offsets are aligned properly 
                                    int addressOffsetPadding = (lineOffsetSize - (addressOffsetTableSize & (lineOffsetSize - 1))) & (lineOffsetSize - 1);
                                    addressOffsetTableSize += addressOffsetPadding;
                                    
                                    // The first 32 bits of the line number info contains the number of line numbers
                                    // minus the first. The 4 most significant bits are used to store the number of
                                    // bytes needed by each entry in each table.
                                    int flags = 0;
                                    flags = addressOffsetSize - 1;
                                    flags <<= 2;
                                    flags |= lineOffsetSize - 1;
                                    flags <<= 28;
                                    flags |= (lineInfos.size() - 1) & 0x0fffffff;

                                    StructureConstantBuilder builder = new StructureConstantBuilder();
                                    builder
                                        .add(new IntegerConstant(flags))
                                        .add(new IntegerConstant(firstLineNumber));
                                    
                                    for (LineInfo lineInfo : lineInfos.subList(1, lineInfos.size())) {
                                        if (addressOffsetSize == 1) {
                                            builder.add(new IntegerConstant((byte) (lineInfo.getAddress() - baseAddress)));
                                        } else if (addressOffsetSize == 2) {
                                            builder.add(new IntegerConstant((short) (lineInfo.getAddress() - baseAddress)));
                                        } else {
                                            builder.add(new IntegerConstant((int) (lineInfo.getAddress() - baseAddress)));
                                        }
                                    }

                                    // Padding
                                    for (int i = 0; i < addressOffsetPadding; i++) {
                                        builder.add(new IntegerConstant((byte) 0));
                                    }

                                    for (LineInfo lineInfo : lineInfos.subList(1, lineInfos.size())) {
                                        if (lineOffsetSize == 1) {
                                            builder.add(new IntegerConstant((byte) (lineInfo.getLineNumber() - firstLineNumber)));
                                        } else if (lineOffsetSize == 2) {
                                            builder.add(new IntegerConstant((short) (lineInfo.getLineNumber() - firstLineNumber)));
                                        } else {
                                            builder.add(new IntegerConstant((int) (lineInfo.getLineNumber() - firstLineNumber)));
                                        }
                                    }

                                    // Extract the method's name and descriptor from the symbol and
                                    // build the linetable symbol name.
                                    String methodName = symbol.getName().substring(symbol.getName().lastIndexOf('.') + 1);
                                    methodName = methodName.substring(0, methodName.indexOf('('));
                                    String methodDesc = symbol.getName().substring(symbol.getName().lastIndexOf('('));
                                    String linetableSymbol = Symbols.linetableSymbol(clazz.getInternalName(), methodName, methodDesc);
                                    if (linesMb == null) {
                                        linesMb = new ModuleBuilder();
                                    }
                                    linesMb.addGlobal(new Global(linetableSymbol, builder.build(), true));
                                }
                            }
                        }
                    }
                    if (linesMb != null) {
                        byte[] linesData = linesMb.build().toString().getBytes("UTF-8");
                        if (config.isDumpIntermediates()) {
                            File linesLlFile = config.getLinesLlFile(clazz);
                            linesLlFile.getParentFile().mkdirs();
                            FileUtils.writeByteArrayToFile(linesLlFile, linesData);
                        }
                        try (Module linesModule = Module.parseIR(context, linesData, clazz.getClassName() + ".lines")) {
                            File linesOFile = config.getLinesOFile(clazz);
                            targetMachine.emit(linesModule, linesOFile, CodeGenFileType.ObjectFile);
                        }
                    } else {
                        // Make sure there's no stale lines.o file lingering
                        File linesOFile = config.getLinesOFile(clazz);
                        if (linesOFile.exists()) {
                            linesOFile.delete();
                        }
                    }
                }
            }
        }
    }

    private static PassManager createPassManager(Config config) {
        PassManager passManager = new PassManager();
        
        if (config.isDebug()) {
            // Minimal passes. Just inline functions marked 'alwaysinline'.
            passManager.addAlwaysInlinerPass();
        } else {
            // Sets up the passes we would get with PassManagerBuilder (see PassManagerBuilder.cpp) at 
            // O2 level except the TailCallEliminationPass which promotes all calls to tail calls which
            // we don't want since it messes up stack traces.
        
            passManager.addAlwaysInlinerPass();
            passManager.addPromoteMemoryToRegisterPass();
    
            passManager.addTypeBasedAliasAnalysisPass();
            passManager.addBasicAliasAnalysisPass();
            passManager.addGlobalOptimizerPass();
            passManager.addIPSCCPPass();
            passManager.addDeadArgEliminationPass();
            passManager.addInstructionCombiningPass();
            passManager.addCFGSimplificationPass();
            passManager.addPruneEHPass();
            passManager.addFunctionInliningPass();
            passManager.addFunctionAttrsPass();
//            if (optLevel > 2) {
//                passManager.addArgumentPromotionPass();
//            }
            passManager.addScalarReplAggregatesPass();
            
            passManager.addEarlyCSEPass();
            passManager.addSimplifyLibCallsPass();
            passManager.addJumpThreadingPass();
            passManager.addCorrelatedValuePropagationPass();
            passManager.addCFGSimplificationPass();
            passManager.addInstructionCombiningPass();
            
            //passManager.addTailCallEliminationPass();
            passManager.addCFGSimplificationPass();
            passManager.addReassociatePass();
            passManager.addCFGSimplificationPass();
            passManager.addReassociatePass();
            passManager.addLoopRotatePass();
            passManager.addLICMPass();
            passManager.addLoopUnswitchPass();
            passManager.addInstructionCombiningPass();
            passManager.addIndVarSimplifyPass();
            passManager.addLoopIdiomPass();
            passManager.addLoopDeletionPass();
            
            passManager.addLoopVectorizePass();
            
            passManager.addLoopUnrollPass();
            
            passManager.addGVNPass();
            passManager.addMemCpyOptPass();
            passManager.addSCCPPass();
            
            passManager.addInstructionCombiningPass();
            passManager.addJumpThreadingPass();
            passManager.addCorrelatedValuePropagationPass();
            passManager.addDeadStoreEliminationPass();
    
            passManager.addSLPVectorizePass();
            
            passManager.addAggressiveDCEPass();
            passManager.addCFGSimplificationPass();
            passManager.addInstructionCombiningPass();
    
            passManager.addStripDeadPrototypesPass();
    
            passManager.addGlobalDCEPass();
            passManager.addConstantMergePass();
        }
        
        return passManager;
    }
    
    private static void patchAsmWithFunctionSizes(Config config, Clazz clazz, InputStream inStream, OutputStream outStream) throws IOException {
        String labelPrefix = config.getOs().getFamily() == OS.Family.darwin ? "_" : "";
        String localLabelPrefix = config.getOs().getFamily() == OS.Family.darwin ? "L" : ".L";
        
        Set<String> functionNames = new HashSet<String>();
        for (SootMethod method : clazz.getSootClass().getMethods()) {
            if (!method.isAbstract()) {
                String name = labelPrefix + Symbols.methodSymbol(method);
                functionNames.add(name);
            }
        }
        
        String infoStructLabel = labelPrefix + Symbols.infoStructSymbol(clazz.getInternalName());
        Pattern methodImplPattern = Pattern.compile("\\s*\\.(?:quad|long)\\s+\"?(" 
                + Pattern.quote(labelPrefix + Symbols.methodSymbolPrefix(clazz.getInternalName())) 
                + "[^\\s\"]+)\"?.*");
        
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
            out = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
            String line = null;
            String currentFunction = null;
            while ((line = in.readLine()) != null) {
                if (currentFunction == null) {
                    out.write(line);
                    out.write('\n');
                    int colon = line.indexOf(':');
                    if (colon != -1) {
                        String label = line.substring(0, colon);
                        if (label.startsWith("\"") && label.endsWith("\"")) {
                            label = label.substring(1, label.length() - 1);
                        }
                        if (functionNames.contains(label)) {
                            currentFunction = label;
                        } else if (label.equals(infoStructLabel)) {
                            break;
                        }
                    }
                } else if (line.trim().equals(".cfi_endproc") || line.trim().startsWith(".section") || line.trim().startsWith(".globl")) {
                    out.write("\"");
                    out.write(localLabelPrefix);
                    out.write(currentFunction);
                    out.write("_end\":\n\n");
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
                Matcher matcher = methodImplPattern.matcher(line);
                if (matcher.matches()) {
                    String functionName = matcher.group(1);
                    if (functionNames.contains(functionName)) {
                        line = in.readLine();
                        if (line.contains(String.valueOf(DUMMY_METHOD_SIZE))) {
                            out.write("\t.long\t");
                            out.write("\"" + localLabelPrefix + functionName + "_end\" - \"" + functionName + "\"");
                            out.write('\n');
                        } else {
                            out.write(line);
                            out.write('\n');
                        }
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }
    
    private void reset() {
        output.reset();
        sootClass = null;
        mb = null;
        trampolines = null;
        catches = null;
        classFields = null;
        instanceFields = null;
        classType = null;
        instanceType = null;
    }
    
    private void compile(Clazz clazz, OutputStream out) throws IOException {
        methodCompiler.reset(clazz);
        bridgeMethodCompiler.reset(clazz);
        callbackMethodCompiler.reset(clazz);
        nativeMethodCompiler.reset(clazz);
        structMemberMethodCompiler.reset(clazz);
        globalValueMethodCompiler.reset(clazz);
        
        ClazzInfo ci = clazz.resetClazzInfo();

        mb = new ModuleBuilder();
        
        for (CompilerPlugin compilerPlugin : config.getCompilerPlugins()) {
            compilerPlugin.beforeClass(config, clazz, mb);
        }
        
        sootClass = clazz.getSootClass();
        trampolines = new HashSet<Trampoline>();
        catches = new HashSet<String>();
        classFields = getClassFields(config.getOs(), config.getArch(),sootClass);
        instanceFields = getInstanceFields(config.getOs(), config.getArch(),sootClass);
        classType = getClassType(config.getOs(), config.getArch(),sootClass);
        instanceType = getInstanceType(config.getOs(), config.getArch(),sootClass);
        
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
            SootMethod _sizeOf = new SootMethod("_sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PROTECTED);
            sootClass.addMethod(_sizeOf);
            SootMethod sizeOf = new SootMethod("sizeOf", Collections.EMPTY_LIST, IntType.v(), Modifier.PUBLIC | Modifier.STATIC);
            sootClass.addMethod(sizeOf);
        }
        
        mb.addInclude(getClass().getClassLoader().getResource(String.format("header-%s-%s.ll", config.getOs().getFamily(), config.getArch())));
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));

        mb.addFunction(createLdcClass());
        mb.addFunction(createLdcClassWrapper());
        Function allocator = createAllocator();
        mb.addFunction(allocator);
        mb.addFunction(createClassInitWrapperFunction(allocator.ref()));
        
        for (SootField f : sootClass.getFields()) {
            Function getter = createFieldGetter(f, classFields, classType, instanceFields, instanceType);
            Function setter = createFieldSetter(f, classFields, classType, instanceFields, instanceType);
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
            
            for (CompilerPlugin compilerPlugin : config.getCompilerPlugins()) {
                compilerPlugin.beforeMethod(config, clazz, method, mb);
            }
            
            String name = method.getName();
            Function function = null;
            if (hasBridgeAnnotation(method)) {
                function = bridgeMethod(method);
            } else if (hasGlobalValueAnnotation(method)) {
                function = globalValueMethod(method);
            } else if (isStruct(sootClass) && ("_sizeOf".equals(name) 
                        || "sizeOf".equals(name) || hasStructMemberAnnotation(method))) {
                function = structMember(method);
            } else if (method.isNative()) {
                function = nativeMethod(method);
            } else if (!method.isAbstract()) {
                function = method(method);
            }
            if (hasCallbackAnnotation(method)) {
                callbackMethod(method);
            }
            if (!name.equals("<clinit>") && !name.equals("<init>") 
                    && !method.isPrivate() && !method.isStatic() 
                    && !Modifier.isFinal(method.getModifiers()) 
                    && !Modifier.isFinal(sootClass.getModifiers())) {
                
                createLookupFunction(method);
            }
            if (method.isStatic()) {
                String fnName = method.isSynchronized() 
                        ? Symbols.synchronizedWrapperSymbol(method) 
                        : Symbols.methodSymbol(method);
                FunctionRef fn = new FunctionRef(fnName, getFunctionType(method));
                mb.addFunction(createClassInitWrapperFunction(fn));
            }
            
            for (CompilerPlugin compilerPlugin : config.getCompilerPlugins()) {
                if (function != null) {
                    compilerPlugin.afterMethod(config, clazz, method, mb, function);
                }
            }
        }
        
        Set<String> trampolineDependencies = new HashSet<String>();
        for (Trampoline trampoline : trampolines) {
            trampolineResolver.compile(mb, trampoline);
            trampolineDependencies.addAll(trampolineResolver.getDependencies());
        }

        Global classInfoStruct = null;
        try {
            if (!sootClass.isInterface()) {
                config.getVTableCache().get(sootClass);
            }
            classInfoStruct = new Global(Symbols.infoStructSymbol(clazz.getInternalName()), Linkage.weak, createClassInfoStruct());
        } catch (IllegalArgumentException e) {
            // VTable throws this if any of the superclasses of the class is actually an interface.
            // Shouldn't happen frequently but the DRLVM test suite has some tests for this.
            // The Linker will take care of making sure the class cannot be loaded at runtime.
            classInfoStruct = new Global(Symbols.infoStructSymbol(clazz.getInternalName()), I8_PTR, true);
        }
        mb.addGlobal(classInfoStruct);
        
        Function infoFn = FunctionBuilder.infoStruct(sootClass);
        infoFn.add(new Ret(new ConstantBitcast(classInfoStruct.ref(), I8_PTR_PTR)));
        mb.addFunction(infoFn);
        
        for (CompilerPlugin compilerPlugin : config.getCompilerPlugins()) {
            compilerPlugin.afterClass(config, clazz, mb);
        }
        
        OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
        mb.build().write(writer);
        writer.flush();

        ci.setCatchNames(catches);
        ci.setTrampolines(trampolines);
        
        ci.addDependency("java/lang/Object"); // Make sure no class or interface has zero dependencies
        if (sootClass.hasSuperclass() && !sootClass.isInterface()) {
            ci.addDependency(getInternalName(sootClass.getSuperclass()));
        }
        for (SootClass iface : sootClass.getInterfaces()) {
            ci.addDependency(getInternalName(iface));
        }
        for (SootField f : sootClass.getFields()) {
            addDependencyIfNeeded(clazz, f.getType());
        }
        for (SootMethod m : sootClass.getMethods()) {
            addDependencyIfNeeded(clazz, m.getReturnType());
            @SuppressWarnings("unchecked")
            List<soot.Type> paramTypes = (List<soot.Type>) m.getParameterTypes();
            for (soot.Type type : paramTypes) {
                addDependencyIfNeeded(clazz, type);
            }
        }
        ci.addDependencies(attributesEncoder.getDependencies());
        ci.addDependencies(trampolineDependencies);
        ci.addDependencies(catches);
        
        for (Trampoline t : trampolines) {
            {
                String desc = t.getTarget();
                if (desc.charAt(0) == 'L' || desc.charAt(0) == '[') {
                    // Target is a descriptor
                    addDependencyIfNeeded(clazz, desc);
                } else {
                    ci.addDependency(t.getTarget());
                }
            }
            if (t instanceof FieldAccessor) {
                addDependencyIfNeeded(clazz, ((FieldAccessor) t).getFieldDesc());
            } else if (t instanceof Invoke) {
                String methodDesc = ((Invoke) t).getMethodDesc();
                addDependencyIfNeeded(clazz, getReturnTypeDescriptor(methodDesc));
                for (String desc : getParameterDescriptors(methodDesc)) {
                    addDependencyIfNeeded(clazz, desc);
                }
            }
        }
        clazz.saveClazzInfo();
    }

    private static void addDependencyIfNeeded(Clazz clazz, soot.Type type) {
        if (type instanceof RefLikeType) {
            addDependencyIfNeeded(clazz, getDescriptor(type));
        }
    }

    private static void addDependencyIfNeeded(Clazz clazz, String desc) {
        if (!isPrimitive(desc) && (!isArray(desc) || !isPrimitiveBaseType(desc))) {
            String internalName = isArray(desc) ? getBaseType(desc) : getInternalNameFromDescriptor(desc);
            if (!clazz.getInternalName().equals(internalName)) {
                clazz.getClazzInfo().addDependency(internalName);
            }
        }
    }
    
    private void createLookupFunction(SootMethod m) {
        // TODO: This should use a virtual method table or interface method table.
        Function function = FunctionBuilder.lookup(m, true);
        mb.addFunction(function);

        Variable reserved0 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved0, function.getParameterRef(0), 0, 4));
        Variable reserved1 = function.newVariable(I8_PTR_PTR);
        function.add(new Getelementptr(reserved1, function.getParameterRef(0), 0, 5));
        function.add(new Store(getString(m.getName()), reserved0.ref()));
        function.add(new Store(getString(getDescriptor(m)), reserved1.ref()));
        
        if (!sootClass.isInterface()) {
            int vtableIndex = 0;
            try {
                VTable vtable = config.getVTableCache().get(sootClass);
                vtableIndex = vtable.getEntry(m).getIndex();
            } catch (IllegalArgumentException e) {
                // VTable throws this if any of the superclasses of the class is actually an interface.
                // Shouldn't happen frequently but the DRLVM test suite has some tests for this.
                // Use 0 as vtableIndex since this lookup function will never be called anyway.
            }
            Value classPtr = call(function, OBJECT_CLASS, function.getParameterRef(1));
            Value vtablePtr = call(function, CLASS_VITABLE, classPtr);
            Variable funcPtrPtr = function.newVariable(I8_PTR_PTR);
            function.add(new Getelementptr(funcPtrPtr, vtablePtr, 0, 1, vtableIndex));
            Variable funcPtr = function.newVariable(I8_PTR);
            function.add(new Load(funcPtr, funcPtrPtr.ref()));
            Variable f = function.newVariable(function.getType());
            function.add(new Bitcast(f, funcPtr.ref(), f.getType()));
            Value result = tailcall(function, f.ref(), function.getParameterRefs());
            function.add(new Ret(result));
        } else {
            ITable itable = config.getITableCache().get(sootClass);
            ITable.Entry entry = itable.getEntry(m);
            List<Value> args = new ArrayList<Value>();
            args.add(function.getParameterRef(0));
            args.add(getInfoStruct(function, sootClass));
            args.add(function.getParameterRef(1));
            args.add(new IntegerConstant(entry.getIndex()));
            Value fptr = call(function, BC_LOOKUP_INTERFACE_METHOD_IMPL, args);
            Variable f = function.newVariable(function.getType());
            function.add(new Bitcast(f, fptr, f.getType()));
            Value result = tailcall(function, f.ref(), function.getParameterRefs());
            function.add(new Ret(result));
        }
    }
    
    private Constant createVTableStruct() {
        VTable vtable = config.getVTableCache().get(sootClass);
        String name = Symbols.vtableSymbol(getInternalName(sootClass));
        for (VTable.Entry entry : vtable.getEntries()) {
            FunctionRef fref = entry.getFunctionRef();
            if (fref != null && !mb.hasSymbol(fref.getName())) {
                mb.addFunctionDeclaration(new FunctionDeclaration(fref));
            }
        }
        Global vtableStruct = new Global(name, Linkage._private, vtable.getStruct(), true);
        mb.addGlobal(vtableStruct);
        return new ConstantBitcast(vtableStruct.ref(), I8_PTR);
    }
    
    private Constant createITableStruct() {
        ITable itable = config.getITableCache().get(sootClass);
        String name = Symbols.itableSymbol(getInternalName(sootClass));
        Global itableStruct = new Global(name, Linkage._private, itable.getStruct(), true);
        mb.addGlobal(itableStruct);
        return new ConstantBitcast(itableStruct.ref(), I8_PTR);
    }
    
    private Constant createITablesStruct() {
        if (!sootClass.isInterface()) {
            HashSet<SootClass> interfaces = new HashSet<SootClass>();
            collectInterfaces(sootClass, interfaces);
            List<Constant> tables = new ArrayList<Constant>();
            int i = 0;
            for (SootClass ifs : interfaces) {
                ITable itable = config.getITableCache().get(ifs);
                if (itable.size() > 0) {
                    String name = Symbols.itableSymbol(getInternalName(sootClass), i++);
                    String typeInfoName = Symbols.typeInfoSymbol(getInternalName(ifs));
                    if (!mb.hasSymbol(typeInfoName)) {
                        mb.addGlobal(new Global(typeInfoName, Linkage.external, I8_PTR, true));
                    }
                    Global itableStruct = new Global(name, Linkage._private,
                            new StructureConstantBuilder()
                                .add(mb.getGlobalRef(typeInfoName))
                                .add(itable.getStruct(mb, sootClass)).build(), true);
                    mb.addGlobal(itableStruct);
                    tables.add(new ConstantBitcast(itableStruct.ref(), I8_PTR));
                }
            }
            
            if (tables.isEmpty()) {
                return new NullConstant(I8_PTR);
            } else {
                Global itablesStruct = new Global(Symbols.itablesSymbol(getInternalName(sootClass)), Linkage._private,
                        new StructureConstantBuilder()
                            .add(new IntegerConstant((short) tables.size()))
                            .add(tables.get(0)) // cache value must never be null
                            .add(new ArrayConstantBuilder(I8_PTR).add(tables).build())
                            .build());
                mb.addGlobal(itablesStruct);
                return new ConstantBitcast(itablesStruct.ref(), I8_PTR);
            }
            
        } else {
            return new NullConstant(I8_PTR);
        }
    }
    
    private StructureConstant createClassInfoStruct() {
        int flags = 0;
        
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
        if (hasFinalizer(sootClass)) {
            flags |= CI_FINALIZABLE;
        }
        
        // Create the ClassInfoHeader structure.
        StructureConstantBuilder header = new StructureConstantBuilder();
        header.add(new NullConstant(I8_PTR)); // Points to the runtime Class struct
        header.add(new IntegerConstant(flags));
        header.add(getString(getInternalName(sootClass)));
        if (sootClass.declaresMethod("<clinit>", Collections.emptyList(), VoidType.v())) {
            SootMethod method = sootClass.getMethod("<clinit>", Collections.emptyList(), VoidType.v());
            header.add(new FunctionRef(Symbols.methodSymbol(method), getFunctionType(method)));            
        } else {
            header.add(new NullConstant(I8_PTR));
        }
        mb.addGlobal(new Global(Symbols.typeInfoSymbol(getInternalName(sootClass)), Linkage.external, I8_PTR, true));
        header.add(new GlobalRef(Symbols.typeInfoSymbol(getInternalName(sootClass)), I8_PTR)); // TypeInfo* generated by Linker

        if (!sootClass.isInterface()) {
            header.add(createVTableStruct());
        } else {
            header.add(createITableStruct());
        }
        header.add(createITablesStruct());
        
        header.add(sizeof(classType));
        header.add(sizeof(instanceType));
        if (!instanceFields.isEmpty()) {
            header.add(offsetof(instanceType, 1, 1));
        } else {
            header.add(sizeof(instanceType));
        }
        header.add(new IntegerConstant((short) countReferences(classFields)));
        header.add(new IntegerConstant((short) countReferences(instanceFields)));

        PackedStructureConstantBuilder body = new PackedStructureConstantBuilder();
        body.add(new IntegerConstant((short) sootClass.getInterfaceCount()));
        body.add(new IntegerConstant((short) sootClass.getFieldCount()));
        body.add(new IntegerConstant((short) sootClass.getMethodCount()));
        
        if (!sootClass.isInterface()) {
            body.add(getStringOrNull(sootClass.hasSuperclass() ? getInternalName(sootClass.getSuperclass()) : null));
        }

        if (attributesEncoder.classHasAttributes()) {
            body.add(new ConstantBitcast(attributesEncoder.getClassAttributes().ref(), I8_PTR));
        }
        
        for (SootClass s : sootClass.getInterfaces()) {
            body.add(getString(getInternalName(s)));
        }
        
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
                int index = classFields.indexOf(f);
                body.add(offsetof(classType, 1, index, 1));
            } else {
                int index = instanceFields.indexOf(f);
                body.add(offsetof(instanceType, 1, 1 + index, 1));
            }
            if (attributesEncoder.fieldHasAttributes(f)) {
                body.add(new ConstantBitcast(attributesEncoder.getFieldAttributes(f).ref(), I8_PTR));
            }
        }
        
        VTable vtable = !sootClass.isInterface() ? config.getVTableCache().get(sootClass) : null;
        ITable itable = sootClass.isInterface() ? config.getITableCache().get(sootClass) : null;;

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
                if (!isStruct(sootClass) && !hasStructMemberAnnotation(m)) {
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
            if (hasBridgeAnnotation(m) || hasGlobalValueAnnotation(m)) {
                flags |= MI_BRO_BRIDGE;
            }
            if (hasCallbackAnnotation(m)) {
                flags |= MI_BRO_CALLBACK;
            }
            if ((t instanceof PrimType || t == VoidType.v()) && m.getParameterCount() == 0) {
                flags |= MI_COMPACT_DESC;
            }
            body.add(new IntegerConstant((short) flags));            

            Constant viTableIndex = new IntegerConstant((short) -1);
            if (vtable != null) {
                VTable.Entry entry = vtable.getEntry(m);
                if (entry != null) {
                    viTableIndex = new IntegerConstant((short) entry.getIndex());
                }
            } else {
                ITable.Entry entry = itable.getEntry(m);
                if (entry != null) {
                    viTableIndex = new IntegerConstant((short) entry.getIndex());
                }
            }
            body.add(viTableIndex);            
            
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
                body.add(new ConstantBitcast(new FunctionRef(Symbols.methodSymbol(m), getFunctionType(m)), I8_PTR));
                body.add(new IntegerConstant(DUMMY_METHOD_SIZE)); // Size of function. This value will be modified later by patching the .s file.
                if (m.isSynchronized()) {
                    body.add(new ConstantBitcast(new FunctionRef(Symbols.synchronizedWrapperSymbol(m), getFunctionType(m)), I8_PTR));
                }
                if ((flags & MI_NATIVE) == 0) {
                    // Cannot use m.isNative() in the condition above since methods which are native in the
                    // Java class file may have been changed to non-native by the RoboVM compiler 
                    // (e.g. @StructMember methods). The native code which parses the info structs will see 
                    // the method as non-native.

                    // Add a weak linetable pointer which points to a -1 value which will be interpreted as 0 linenumbers in the table
                    Global linetableGlobal = new Global(Symbols.linetableSymbol(m), Linkage.weak, new IntegerConstant(-1));
                    mb.addGlobal(linetableGlobal);
                    body.add(linetableGlobal.ref());
                }
            }
            if (hasBridgeAnnotation(m)) {
                if (!readBooleanElem(getAnnotation(m, BRIDGE), "dynamic", false)) {
                    body.add(new GlobalRef(Symbols.bridgePtrSymbol(m), I8_PTR));
                } else {
                    body.add(new NullConstant(I8_PTR));
                }
            } else if (hasGlobalValueAnnotation(m)) {
                body.add(new GlobalRef(Symbols.globalValuePtrSymbol(m), I8_PTR));
            }
            if (hasCallbackAnnotation(m)) {
                body.add(new AliasRef(Symbols.callbackPtrSymbol(m), I8_PTR));
            }
        }
        
        // Return the struct {header, body}. To be compatible with the C code in classinfo.c 
        // it is important that the header is padded the same as in C so that the body starts
        // after sizeof(ClassInfoHeader) bytes.
        return new StructureConstantBuilder().add(header.build()).add(body.build()).build();
    }
    
    private Function nativeMethod(SootMethod method) {
        Function fn = nativeMethodCompiler.compile(mb, method);
        trampolines.addAll(nativeMethodCompiler.getTrampolines());
        catches.addAll(nativeMethodCompiler.getCatches());
        return fn;
    }
    
    private Function bridgeMethod(SootMethod method) {
        Function fn = bridgeMethodCompiler.compile(mb, method);
        trampolines.addAll(bridgeMethodCompiler.getTrampolines());
        catches.addAll(bridgeMethodCompiler.getCatches());
        return fn;
    }
    
    private Function callbackMethod(SootMethod method) {
        Function fn = callbackMethodCompiler.compile(mb, method);
        trampolines.addAll(callbackMethodCompiler.getTrampolines());
        catches.addAll(callbackMethodCompiler.getCatches());
        return fn;
    }
    
    private Function structMember(SootMethod method) {
        Function fn = structMemberMethodCompiler.compile(mb, method);
        trampolines.addAll(structMemberMethodCompiler.getTrampolines());
        catches.addAll(structMemberMethodCompiler.getCatches());
        return fn;
    }

    private Function globalValueMethod(SootMethod method) {
        Function fn = globalValueMethodCompiler.compile(mb, method);
        trampolines.addAll(globalValueMethodCompiler.getTrampolines());
        catches.addAll(globalValueMethodCompiler.getCatches());
        return fn;
    }

    private Function method(SootMethod method) {
        Function fn = methodCompiler.compile(mb, method);
        trampolines.addAll(methodCompiler.getTrampolines());
        catches.addAll(methodCompiler.getCatches());
        return fn;
    }
    
    private Function createAllocator() {
        Function fn = FunctionBuilder.allocator(sootClass);
        Value info = getInfoStruct(fn, sootClass);        
        Value result = call(fn, BC_ALLOCATE, fn.getParameterRef(0), info);
        fn.add(new Ret(result));
        return fn;
    }

    private Function createLdcClass() {
        Function fn = FunctionBuilder.ldcInternal(sootClass);
        Value info = getInfoStruct(fn, sootClass);
        Value result = call(fn, BC_LDC_CLASS, fn.getParameterRef(0), info);
        fn.add(new Ret(result));
        return fn;
    }
    
    private Function createLdcClassWrapper() {
        Function fn = FunctionBuilder.ldcExternal(sootClass);
        Value info = getInfoStruct(fn, sootClass);
        Value result = call(fn, LDC_CLASS_WRAPPER, fn.getParameterRef(0), info);
        fn.add(new Ret(result));
        return fn;
    }
    
    static Function createFieldGetter(SootField field, List<SootField> classFields, 
            StructureType classType, List<SootField> instanceFields, StructureType instanceType) {
        
        Function fn = FunctionBuilder.getter(field);
        return createFieldGetter(fn, field, classFields, classType, instanceFields, instanceType);
    }
    
    static Function createFieldGetter(Function fn, SootField field, List<SootField> classFields, 
            StructureType classType, List<SootField> instanceFields, StructureType instanceType) {
        
        Value fieldPtr = null;
        if (field.isStatic()) {
            fieldPtr = getClassFieldPtr(fn, field, classFields, classType);
        } else {
            fieldPtr = getInstanceFieldPtr(fn, fn.getParameterRef(1), field, instanceFields, instanceType);
        }
        Variable result = fn.newVariable(getType(field.getType()));
        if (Modifier.isVolatile(field.getModifiers())) {
            fn.add(new Fence(Ordering.seq_cst));
            if (LongType.v().equals(field.getType())) {
                fn.add(new Load(result, fieldPtr, false, Ordering.unordered, 8));
            } else {
                fn.add(new Load(result, fieldPtr));
            }
        } else {
            fn.add(new Load(result, fieldPtr));
        }
        fn.add(new Ret(new VariableRef(result)));
        return fn;
    }
    
    static Function createFieldSetter(SootField field, List<SootField> classFields, 
            StructureType classType, List<SootField> instanceFields, StructureType instanceType) {
        
        Function fn = FunctionBuilder.setter(field);
        return createFieldSetter(fn, field, classFields, classType, instanceFields, instanceType);
    }
    
    static Function createFieldSetter(Function fn, SootField field, List<SootField> classFields, 
            StructureType classType, List<SootField> instanceFields, StructureType instanceType) {

        Value fieldPtr = null;
        Value value = null;
        if (field.isStatic()) {
            fieldPtr = getClassFieldPtr(fn, field, classFields, classType);
            value = fn.getParameterRef(1);
        } else {
            fieldPtr = getInstanceFieldPtr(fn, fn.getParameterRef(1), field, instanceFields, instanceType);
            value = fn.getParameterRef(2);
        }
        if (Modifier.isVolatile(field.getModifiers()) || !field.isStatic() && Modifier.isFinal(field.getModifiers())) {
            if (LongType.v().equals(field.getType())) {
                fn.add(new Store(value, fieldPtr, false, Ordering.unordered, 8));
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
        Function fn = FunctionBuilder.clinitWrapper(targetFn);
        Value info = getInfoStruct(fn, sootClass);
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

    private static int countReferences(List<SootField> l) {
        int count = 0;
        for (SootField f : l) {
            if (f.getType() instanceof RefLikeType) {
                count++;
            }
        }
        return count;
    }
    
    private static void collectInterfaces(SootClass clazz, Set<SootClass> interfaces) {
        if (clazz.hasSuperclass()) {
            collectInterfaces(clazz.getSuperclass(), interfaces);
        }
        if (clazz.isInterface()) {
            interfaces.add(clazz);
        }
        for (SootClass sc : clazz.getInterfaces()) {
            collectInterfaces(sc, interfaces);
        }
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
    
    private static boolean hasFinalizer(SootClass clazz) {
        // Don't search interfaces or java.lang.Object
        if (clazz.isInterface() || !clazz.hasSuperclass()) {
            return false;
        }
        return clazz.declaresMethod("finalize", Collections.emptyList(), VoidType.v());
    }
    
    private Constant getString(String string) {
        return mb.getString(string);
    }
    
    private Constant getStringOrNull(String string) {
        return mb.getStringOrNull(string);
    }
    
    static Value getClassFieldPtr(Function f, SootField field, List<SootField> classFields, 
            StructureType classType) {
        
        Value info = getInfoStruct(f, field.getDeclaringClass());        
        Variable base = f.newVariable(I8_PTR);
        f.add(new Load(base, info));
        return getFieldPtr(f, new VariableRef(base), offsetof(classType, 1, 
                classFields.indexOf(field), 1), getType(field.getType()));
    }

    static Value getInfoStruct(Function f, SootClass sootClass) {
        return call(f, FunctionBuilder.infoStruct(sootClass).ref());
    }
    
    static Value getInstanceFieldPtr(Function f, Value base, SootField field, 
            List<SootField> instanceFields, StructureType instanceType) {
        return getFieldPtr(f, base, offsetof(instanceType, 1, 
                1 + instanceFields.indexOf(field), 1), getType(field.getType()));
    }
}
