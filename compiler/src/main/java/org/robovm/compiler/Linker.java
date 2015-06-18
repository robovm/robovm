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

import static org.robovm.compiler.Access.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Symbols.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.ClazzInfo;
import org.robovm.compiler.clazz.MethodInfo;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.Config.TargetBinary;
import org.robovm.compiler.hash.HashTableGenerator;
import org.robovm.compiler.hash.ModifiedUtf8HashFunction;
import org.robovm.compiler.llvm.Alias;
import org.robovm.compiler.llvm.ArrayConstant;
import org.robovm.compiler.llvm.ArrayConstantBuilder;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.ConstantGetelementptr;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionDeclaration;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureConstant;
import org.robovm.compiler.llvm.StructureConstantBuilder;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.plugin.CompilerPlugin;
import org.robovm.llvm.Context;
import org.robovm.llvm.Module;
import org.robovm.llvm.PassManager;
import org.robovm.llvm.Target;
import org.robovm.llvm.TargetMachine;
import org.robovm.llvm.binding.CodeGenFileType;
import org.robovm.llvm.binding.RelocMode;

/**
 *
 */
public class Linker {

    private static final TypeInfo[] EMPTY_TYPE_INFOS = new TypeInfo[0];

    private static class TypeInfo implements Comparable<TypeInfo> {
        boolean error;
        Clazz clazz;
        List<Clazz> children = new ArrayList<Clazz>();
        int id;
        /**
         * Ordered list of TypeInfos for each superclass of this class and the
         * class itself. Empty if this is an interface.
         */
        TypeInfo[] classTypes;
        /**
         * Unordered list of TypeInfos for each interface implemented by this
         * class or interface.
         */
        TypeInfo[] interfaceTypes;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TypeInfo other = (TypeInfo) obj;
            if (id != other.id)
                return false;
            return true;
        }

        @Override
        public int compareTo(TypeInfo o) {
            return id < o.id ? -1 : (id == o.id ? 0 : 1);
        }
    }

    private final Config config;
    private final Map<String, byte[]> runtimeData = new HashMap<>();

    public Linker(Config config) {
        this.config = config;
    }

    /**
     * Adds arbitrary data which will be compiled into the executable and will
     * be available at runtime using {@code VM.getRuntimeData(id)}.
     */
    public void addRuntimeData(String id, byte[] data) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(data, "data");
        runtimeData.put(id, data);
    }

    private ArrayConstant runtimeDataToBytes() throws UnsupportedEncodingException {
        // The data consists of key value pairs prefixed with the number of
        // pairs (int). In each pair the key is an UTF8 encoded string prefixed
        // with the key's length (int) and the data is a byte array prefixed
        // with the data length (int). We prefix the data with the length of the
        // data (int).

        LinkedList<byte[]> dataList = new LinkedList<>();
        int length = 4;
        for (Entry<String, byte[]> entry : runtimeData.entrySet()) {
            dataList.add(entry.getKey().getBytes("UTF8"));
            length += 4 + dataList.getLast().length;
            dataList.add(entry.getValue());
            length += 4 + dataList.getLast().length;
        }

        ByteBuffer bb = ByteBuffer.wrap(new byte[length + 4]).order(config.getArch().getByteOrder());
        bb.putInt(length);
        bb.putInt(runtimeData.size());
        for (byte[] b : dataList) {
            bb.putInt(b.length);
            bb.put(b);
        }

        return new ArrayConstantBuilder(I8).add(bb.array()).build();
    }

    public void link(Set<Clazz> classes) throws IOException {
        for (CompilerPlugin plugin : config.getCompilerPlugins()) {
            plugin.beforeLinker(config, this, classes);
        }

        Arch arch = config.getArch();
        OS os = config.getOs();

        Set<Clazz> linkClasses = new TreeSet<Clazz>(classes);
        config.getLogger().info("Linking %d classes (%s %s %s)", linkClasses.size(),
                os, arch, config.isDebug() ? "debug" : "release");

        ModuleBuilder mb = new ModuleBuilder();
        mb.addInclude(getClass().getClassLoader().getResource(String.format("header-%s-%s.ll", os.getFamily(), arch)));
        mb.addInclude(getClass().getClassLoader().getResource("header.ll"));

        mb.addGlobal(new Global("_bcRuntimeData", runtimeDataToBytes()));

        mb.addGlobal(new Global("_bcDynamicJNI", new IntegerConstant(config.isUseDynamicJni() ? (byte) 1 : (byte) 0)));
        ArrayConstantBuilder staticLibs = new ArrayConstantBuilder(I8_PTR);
        if (!config.isUseDynamicJni()) {
            for (Config.Lib lib : config.getLibs()) {
                String p = lib.getValue();
                if (p.endsWith(".a")) {
                    p = new File(p).getName();
                    String libName = p.substring(0, p.length() - 2);
                    if (libName.startsWith("lib")) {
                        libName = libName.substring(3);
                    }
                    staticLibs.add(mb.getString(libName));
                }
            }
        }
        staticLibs.add(new NullConstant(Type.I8_PTR));
        mb.addGlobal(new Global("_bcStaticLibs",
                new ConstantGetelementptr(mb.newGlobal(staticLibs.build()).ref(), 0, 0)));

        HashTableGenerator<String, Constant> bcpHashGen = new HashTableGenerator<String, Constant>(
                new ModifiedUtf8HashFunction());
        HashTableGenerator<String, Constant> cpHashGen = new HashTableGenerator<String, Constant>(
                new ModifiedUtf8HashFunction());
        int classCount = 0;
        Map<ClazzInfo, TypeInfo> typeInfos = new HashMap<ClazzInfo, TypeInfo>();
        for (Clazz clazz : linkClasses) {
            TypeInfo typeInfo = new TypeInfo();
            typeInfo.clazz = clazz;
            typeInfo.id = classCount++;
            typeInfos.put(clazz.getClazzInfo(), typeInfo);

            StructureConstant infoErrorStruct = createClassInfoErrorStruct(mb, clazz.getClazzInfo());
            Global info = null;
            if (infoErrorStruct == null) {
                info = new Global(Symbols.infoStructSymbol(clazz.getInternalName()), external, I8_PTR, false);
            } else {
                typeInfo.error = true;
                info = new Global(Symbols.infoStructSymbol(clazz.getInternalName()), infoErrorStruct);
            }
            mb.addGlobal(info);
            if (clazz.isInBootClasspath()) {
                bcpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            } else {
                cpHashGen.put(clazz.getInternalName(), new ConstantBitcast(info.ref(), I8_PTR));
            }
        }
        mb.addGlobal(new Global("_bcBootClassesHash", new ConstantGetelementptr(mb.newGlobal(bcpHashGen.generate(),
                true).ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClassesHash", new ConstantGetelementptr(mb.newGlobal(cpHashGen.generate(), true)
                .ref(), 0, 0)));

        ArrayConstantBuilder bootClasspathValues = new ArrayConstantBuilder(I8_PTR);
        ArrayConstantBuilder classpathValues = new ArrayConstantBuilder(I8_PTR);
        List<Path> allPaths = new ArrayList<Path>();
        allPaths.addAll(config.getClazzes().getPaths());
        allPaths.addAll(config.getResourcesPaths());
        for (Path path : allPaths) {
            String entryName = null;
            if (config.isSkipInstall() && config.getTarget().canLaunchInPlace()) {
                entryName = path.getFile().getAbsolutePath();
            } else {
                entryName = config.getTarget().getInstallRelativeArchivePath(path);
            }
            if (path.isInBootClasspath()) {
                bootClasspathValues.add(mb.getString(entryName));
            } else {
                classpathValues.add(mb.getString(entryName));
            }
        }
        bootClasspathValues.add(new NullConstant(Type.I8_PTR));
        classpathValues.add(new NullConstant(Type.I8_PTR));
        mb.addGlobal(new Global("_bcBootclasspath", new ConstantGetelementptr(mb.newGlobal(bootClasspathValues.build())
                .ref(), 0, 0)));
        mb.addGlobal(new Global("_bcClasspath", new ConstantGetelementptr(mb.newGlobal(classpathValues.build()).ref(),
                0, 0)));

        if (config.getMainClass() != null) {
            mb.addGlobal(new Global("_bcMainClass", mb.getString(config.getMainClass())));
        }

        ModuleBuilder[] mbs = new ModuleBuilder[config.getThreads() + 1];
        FunctionRef[] stubRefs = new FunctionRef[mbs.length];
        ArrayConstantBuilder stubRefsArray = new ArrayConstantBuilder(I8_PTR);
        mbs[0] = mb;
        for (int i = 1; i < mbs.length; i++) {
            mbs[i] = new ModuleBuilder();
            mbs[i].addInclude(getClass().getClassLoader().getResource(
                    String.format("header-%s-%s.ll", os.getFamily(), arch)));
            mbs[i].addInclude(getClass().getClassLoader().getResource("header.ll"));

            Function fn = new FunctionBuilder("_stripped_method" + i, new FunctionType(VOID, ENV_PTR))
                    .linkage(external).build();
            call(fn, BC_THROW_NO_SUCH_METHOD_ERROR, fn.getParameterRef(0),
                    mbs[i].getString("Method has been stripped out of the executable"));
            fn.add(new Unreachable());
            mbs[i].addFunction(fn);
            mb.addFunctionDeclaration(new FunctionDeclaration(fn.ref()));
            stubRefs[i] = fn.ref();
            stubRefsArray.add(new ConstantBitcast(fn.ref(), I8_PTR));
        }
        stubRefsArray.add(new NullConstant(I8_PTR));
        
        mb.addGlobal(new Global("_bcStrippedMethodStubs", stubRefsArray.build()));
        
        Random rnd = new Random();

        buildTypeInfos(typeInfos);
        
        Set<String> checkcasts = new HashSet<>();
        Set<String> instanceofs = new HashSet<>();
        Set<String> invokes = new HashSet<>();
        for (Clazz clazz : linkClasses) {
            ClazzInfo ci = clazz.getClazzInfo();
            checkcasts.addAll(ci.getCheckcasts());
            instanceofs.addAll(ci.getInstanceofs());
            invokes.addAll(ci.getInvokes());
        }

        Set<String> reachableMethods = new HashSet<>();
        for (Triple<String, String, String> node : config.getDependencyGraph().findReachableMethods()) {
            reachableMethods.add(node.getLeft() + "." + node.getMiddle() + node.getRight());
        }
        
        int totalMethodCount = 0;
        int reachableMethodCount = 0;
        for (Clazz clazz : linkClasses) {
            int mbIdx = rnd.nextInt(mbs.length - 1) + 1;
            ClazzInfo ci = clazz.getClazzInfo();

            // Create strong stubs for unused methods which override the weak
            // ones generated by ClassCompiler. This must be done before we
            // override lookup functions below otherwise we may get duplicate
            // symbols errors.
            for (MethodInfo mi : ci.getMethods()) {
                if (!mi.isAbstract()) { 
                    totalMethodCount++;
                    if (!reachableMethods.contains(clazz.getInternalName() + "." + mi.getName() + mi.getDesc())) {
                        createStrippedMethodStub(stubRefs[mbIdx], mbs[mbIdx], clazz, mi);
                    } else {
                        reachableMethodCount++;
                    }
                }
            }

            TypeInfo typeInfo = typeInfos.get(ci);
            if (typeInfo.error) {
                // Add an empty TypeInfo
                mb.addGlobal(new Global(Symbols.typeInfoSymbol(clazz.getInternalName()),
                        new StructureConstantBuilder()
                                .add(new IntegerConstant(typeInfo.id))
                                .add(new IntegerConstant(0))
                                .add(new IntegerConstant(-1))
                                .add(new IntegerConstant(0))
                                .add(new IntegerConstant(0))
                                .build()));
            } else {
                int[] classIds = new int[typeInfo.classTypes.length];
                for (int i = 0; i < typeInfo.classTypes.length; i++) {
                    classIds[i] = typeInfo.classTypes[i].id;
                }
                int[] interfaceIds = new int[typeInfo.interfaceTypes.length];
                for (int i = 0; i < typeInfo.interfaceTypes.length; i++) {
                    interfaceIds[i] = typeInfo.interfaceTypes[i].id;
                }
                mb.addGlobal(new Global(Symbols.typeInfoSymbol(clazz.getInternalName()),
                        new StructureConstantBuilder()
                                .add(new IntegerConstant(typeInfo.id))
                                .add(new IntegerConstant((typeInfo.classTypes.length - 1) * 4 + 5 * 4))
                                .add(new IntegerConstant(-1))
                                .add(new IntegerConstant(typeInfo.classTypes.length))
                                .add(new IntegerConstant(typeInfo.interfaceTypes.length))
                                .add(new ArrayConstantBuilder(I32).add(classIds).build())
                                .add(new ArrayConstantBuilder(I32).add(interfaceIds).build())
                                .build()));

                if (!config.isDebug() && !ci.isInterface() && !ci.isFinal() && typeInfo.children.isEmpty()) {
                    // Non-final class with 0 children. Override every lookup
                    // function with one
                    // which doesn't do any lookup.
                    for (MethodInfo mi : ci.getMethods()) {
                        String name = mi.getName();
                        if (!name.equals("<clinit>") && !name.equals("<init>")
                                && !mi.isPrivate() && !mi.isStatic() && !mi.isFinal() && !mi.isAbstract()) {

                            if (invokes.contains(clazz.getInternalName() + "." + name + mi.getDesc())) {
                                if (reachableMethods.contains(clazz.getInternalName() + "." + name + mi.getDesc())) {
                                    mbs[mbIdx].addFunction(createLookup(mbs[mbIdx], ci, mi));
                                }
                            }
                        }
                    }
                }
            }

            if (checkcasts.contains(clazz.getInternalName())) {
                mbs[mbIdx].addFunction(createCheckcast(mbs[mbIdx], clazz, typeInfo));
            }
            if (instanceofs.contains(clazz.getInternalName())) {
                mbs[mbIdx].addFunction(createInstanceof(mbs[mbIdx], clazz, typeInfo));
            }
        }
        config.getLogger().debug("%d methods out of %d included in the executable", reachableMethodCount, totalMethodCount);

        List<File> objectFiles = new ArrayList<File>();

        generateMachineCode(config, mbs, objectFiles);

        for (Clazz clazz : linkClasses) {
            objectFiles.add(config.getOFile(clazz));
        }

        /*
         * Assemble the lines files for all linked classes into the module.
         */
        for (Clazz clazz : linkClasses) {
            File f = config.getLinesOFile(clazz);
            if (f.exists() && f.length() > 0) {
                objectFiles.add(f);
            }
        }

        config.getTarget().build(objectFiles);
    }

    private void generateMachineCode(final Config config, ModuleBuilder[] mbs,
            final List<File> objectFiles) throws IOException {

        /*
         * Make sure the tmpDir exists before we launch the worker threads. This
         * is to prevent a race between threads to create this folder. See #631.
         */
        config.getTmpDir().mkdirs();

        Executor executor = config.getThreads() <= 1 ? AppCompiler.SAME_THREAD_EXECUTOR
                : Executors.newFixedThreadPool(config.getThreads());

        final List<Throwable> errors = Collections.synchronizedList(new ArrayList<Throwable>());
        for (int i = 0; i < mbs.length; i++) {
            final ModuleBuilder mb = mbs[i];
            final int num = i;
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        File linkerO = generateMachineCode(config, mb, num);
                        synchronized (objectFiles) {
                            objectFiles.add(linkerO);
                        }
                    } catch (Throwable t) {
                        errors.add(t);
                    }
                }
            });
        }

        // Shutdown the executor and wait for running tasks to complete.
        if (executor instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) executor;
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            } catch (InterruptedException e) {
            }
        }

        if (!errors.isEmpty()) {
            Throwable t = errors.get(0);
            if (t instanceof IOException) {
                throw (IOException) t;
            }
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            throw new CompilerException(t);
        }
    }

    private File generateMachineCode(final Config config, final ModuleBuilder mb,
            final int num) throws IOException {

        File linkerO = new File(config.getTmpDir(), "linker" + num + ".o");
        linkerO.getParentFile().mkdirs();

        try (Context context = new Context()) {
            String ir = mb.build().toString();
            if (config.isDumpIntermediates()) {
                File linkerLl = new File(config.getTmpDir(), "linker" + num + ".ll");
                FileUtils.writeStringToFile(linkerLl, ir, "utf-8");
            }
            try (Module module = Module.parseIR(context, ir, "linker" + num + ".ll")) {
                try (PassManager passManager = new PassManager()) {
                    passManager.addAlwaysInlinerPass();
                    passManager.addPromoteMemoryToRegisterPass();
                    passManager.run(module);
                }

                if (config.isDumpIntermediates()) {
                    File linkerBc = new File(config.getTmpDir(), "linker" + num + ".bc");
                    module.writeBitcode(linkerBc);
                }

                String triple = config.getTriple();
                Target target = Target.lookupTarget(triple);
                
                // Ensure to build relocatable if making a dynamic library.
                try (TargetMachine targetMachine = target.createTargetMachine(
                        triple, null, null, null, (config.getTargetBinary() != TargetBinary.dynamic_lib) 
                        ? null : RelocMode.RelocPIC, null)) {
                    targetMachine.setAsmVerbosityDefault(true);
                    targetMachine.setFunctionSections(true);
                    targetMachine.setDataSections(true);
                    targetMachine.getOptions().setNoFramePointerElim(true);
                    // NOTE: Doesn't have any effect on x86. See #503.
                    targetMachine.getOptions().setPositionIndependentExecutable(true);
                    if (config.isDumpIntermediates()) {
                        File linkerS = new File(config.getTmpDir(), "linker" + num + ".s");
                        try (OutputStream outS = new BufferedOutputStream(new FileOutputStream(linkerS))) {
                            targetMachine.emit(module, outS, CodeGenFileType.AssemblyFile);
                        }
                    }
                    try (OutputStream outO = new BufferedOutputStream(new FileOutputStream(linkerO))) {
                        targetMachine.emit(module, outO, CodeGenFileType.ObjectFile);
                    }
                }
            }
        }
        return linkerO;
    }

    private TypeInfo buildTypeInfo(TypeInfo typeInfo, Map<ClazzInfo, TypeInfo> typeInfos) {
        if (typeInfo.error || typeInfo.classTypes != null) {
            return typeInfo;
        }

        ClazzInfo ci = typeInfo.clazz.getClazzInfo();
        List<TypeInfo> clTypeInfos = new ArrayList<TypeInfo>();
        Set<TypeInfo> ifTypeInfos = new TreeSet<TypeInfo>();

        if (!ci.isInterface()) {
            if (ci.hasSuperclass()) {
                TypeInfo superTypeInfo = buildTypeInfo(typeInfos.get(ci.getSuperclass()), typeInfos);
                if (superTypeInfo.error) {
                    typeInfo.error = true;
                    return typeInfo;
                }
                clTypeInfos.addAll(Arrays.asList(superTypeInfo.classTypes));
                clTypeInfos.add(typeInfo);
                ifTypeInfos.addAll(Arrays.asList(superTypeInfo.interfaceTypes));
                superTypeInfo.children.add(typeInfo.clazz);
            } else {
                clTypeInfos.add(typeInfo);
            }
        }

        for (ClazzInfo ifCi : ci.getInterfaces()) {
            TypeInfo ifTypeInfo = buildTypeInfo(typeInfos.get(ifCi), typeInfos);
            if (ifTypeInfo.error) {
                typeInfo.error = true;
                return typeInfo;
            }
            ifTypeInfos.addAll(Arrays.asList(ifTypeInfo.interfaceTypes));
        }
        if (ci.isInterface()) {
            ifTypeInfos.add(typeInfo);
        }

        typeInfo.classTypes = EMPTY_TYPE_INFOS;
        typeInfo.interfaceTypes = EMPTY_TYPE_INFOS;
        if (!clTypeInfos.isEmpty()) {
            typeInfo.classTypes = clTypeInfos.toArray(new TypeInfo[clTypeInfos.size()]);
        }
        if (!ifTypeInfos.isEmpty()) {
            typeInfo.interfaceTypes = ifTypeInfos.toArray(new TypeInfo[ifTypeInfos.size()]);
        }

        return typeInfo;
    }

    private void buildTypeInfos(Map<ClazzInfo, TypeInfo> typeInfos) {
        for (TypeInfo typeInfo : typeInfos.values()) {
            buildTypeInfo(typeInfo, typeInfos);
        }
    }

    private StructureConstant createClassInfoErrorStruct(ModuleBuilder mb, ClazzInfo ci) {
        /*
         * Check that the class can be loaded, i.e. that the superclass and
         * interfaces of the class exist and are accessible to the class. Also
         * check that any exception the class uses in catch clauses exist and is
         * accessible to the class. If the class cannot be loaded we override
         * the ClassInfoHeader struct produced by the ClassCompiler for the
         * class with one which tells the code in bc.c to throw an appropriate
         * exception whenever the class is accessed.
         */

        int errorType = ClassCompiler.CI_ERROR_TYPE_NONE;
        String errorMessage = null;
        if (!ci.isInterface() && ci.hasSuperclass()) {
            // Check superclass
            ClazzInfo superclazz = ci.getSuperclass();
            if (superclazz.isPhantom()) {
                errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                errorMessage = superclazz.getName();
            } else if (!checkClassAccessible(superclazz, ci)) {
                errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, superclazz, ci);
            } else if (superclazz.isInterface()) {
                errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                errorMessage = String.format("class %s has interface %s as super class", ci, superclazz);
            }
            // No need to check for ClassCircularityError. Soot doesn't handle
            // such problems so the compilation will fail earlier.
        }

        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check interfaces
            for (ClazzInfo interfaze : ci.getInterfaces()) {
                if (interfaze.isPhantom()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                    errorMessage = interfaze.getName();
                    break;
                } else if (!checkClassAccessible(interfaze, ci)) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                    errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, interfaze, ci);
                    break;
                } else if (!interfaze.isInterface()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE;
                    errorMessage = String.format("class %s tries to implement class %s as interface",
                            ci, interfaze);
                    break;
                }
            }
        }

        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            // Check exceptions used in catch clauses. I cannot find any info in
            // the VM spec specifying that this has to be done when the class is
            // loaded. However, this is how it's done in other VMs so we do it
            // too.
            for (ClazzInfo ex : ci.getCatches()) {
                if (ex == null || ex.isPhantom()) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_NO_CLASS_DEF_FOUND;
                    errorMessage = ex.getInternalName();
                    break;
                } else if (!checkClassAccessible(ex, ci)) {
                    errorType = ClassCompiler.CI_ERROR_TYPE_ILLEGAL_ACCESS;
                    errorMessage = String.format(ILLEGAL_ACCESS_ERROR_CLASS, ex, ci);
                    break;
                }
            }
        }

        if (errorType == ClassCompiler.CI_ERROR_TYPE_NONE) {
            return null;
        }

        // Create a ClassInfoError struct
        StructureConstantBuilder error = new StructureConstantBuilder();
        error.add(new NullConstant(I8_PTR)); // Points to the runtime Class
                                             // struct
        error.add(new IntegerConstant(ClassCompiler.CI_ERROR));
        error.add(mb.getString(ci.getInternalName()));
        error.add(new IntegerConstant(errorType));
        error.add(mb.getString(errorMessage));
        return error.build();
    }

    private void createStrippedMethodStub(FunctionRef stubRef, ModuleBuilder mb, Clazz clazz, MethodInfo mi) {
        String symbol = methodSymbol(clazz.getInternalName(), mi.getName(), mi.getDesc());
        Alias alias = new Alias(symbol, external, stubRef);
        mb.addAlias(alias);
    }
    
    private Function createCheckcast(ModuleBuilder mb, Clazz clazz, TypeInfo typeInfo) {
        Function fn = FunctionBuilder.checkcast(clazz);
        Value info = getInfoStruct(mb, fn, clazz);
        if (typeInfo.error) {
            // This will trigger an exception
            call(fn, BC_LDC_CLASS, fn.getParameterRef(0), info);
            fn.add(new Ret(new NullConstant(Types.OBJECT_PTR)));
        } else if (!clazz.getClazzInfo().isInterface()) {
            Value result = call(fn, CHECKCAST_CLASS, fn.getParameterRef(0), info,
                    fn.getParameterRef(1),
                    new IntegerConstant((typeInfo.classTypes.length - 1) * 4 + 5 * 4),
                    new IntegerConstant(typeInfo.id));
            fn.add(new Ret(result));
        } else {
            Value result = call(fn, CHECKCAST_INTERFACE, fn.getParameterRef(0), info,
                    fn.getParameterRef(1),
                    new IntegerConstant(typeInfo.id));
            fn.add(new Ret(result));
        }
        return fn;
    }

    private Function createInstanceof(ModuleBuilder mb, Clazz clazz, TypeInfo typeInfo) {
        Function fn = FunctionBuilder.instanceOf(clazz);
        Value info = getInfoStruct(mb, fn, clazz);
        if (typeInfo.error) {
            // This will trigger an exception
            call(fn, BC_LDC_CLASS, fn.getParameterRef(0), info);
            fn.add(new Ret(new IntegerConstant(0)));
        } else if (!clazz.getClazzInfo().isInterface()) {
            Value result = call(fn, INSTANCEOF_CLASS, fn.getParameterRef(0), info,
                    fn.getParameterRef(1),
                    new IntegerConstant((typeInfo.classTypes.length - 1) * 4 + 5 * 4),
                    new IntegerConstant(typeInfo.id));
            fn.add(new Ret(result));
        } else {
            Value result = call(fn, INSTANCEOF_INTERFACE, fn.getParameterRef(0), info,
                    fn.getParameterRef(1),
                    new IntegerConstant(typeInfo.id));
            fn.add(new Ret(result));
        }
        return fn;
    }

    private Function createLookup(ModuleBuilder mb, ClazzInfo ci, MethodInfo mi) {
        Function function = FunctionBuilder.lookup(ci, mi, false);
        String targetFnName = mi.isSynchronized()
                ? Symbols.synchronizedWrapperSymbol(ci.getInternalName(), mi.getName(), mi.getDesc())
                : Symbols.methodSymbol(ci.getInternalName(), mi.getName(), mi.getDesc());

        FunctionRef fn = new FunctionRef(targetFnName, function.getType());
        if (!mb.hasSymbol(fn.getName())) {
            mb.addFunctionDeclaration(new FunctionDeclaration(fn));
        }
        Value result = tailcall(function, fn, function.getParameterRefs());
        function.add(new Ret(result));
        return function;
    }

    private Value getInfoStruct(ModuleBuilder mb, Function f, Clazz clazz) {
        String symbol = Symbols.infoStructSymbol(clazz.getInternalName());
        if (!mb.hasSymbol(symbol)) {
            Global info = new Global(symbol, external, I8_PTR, false);
            mb.addGlobal(info);
            return info.ref();
        } else {
            return new ConstantBitcast(mb.getGlobalRef(symbol), I8_PTR_PTR);
        }
    }
}
