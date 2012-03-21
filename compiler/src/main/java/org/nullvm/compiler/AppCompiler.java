/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.exec.ExecuteException;
import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.Package;
import org.nullvm.compiler.clazz.Path;

/**
 *
 * @version $Id$
 */
public class AppCompiler {
    private final Config config;
    private final ClassCompiler classCompiler;
    private final Linker linker;
    
    public AppCompiler(Config config) {
        this.config = config;
        this.classCompiler = new ClassCompiler(config);
        this.linker = new Linker(config);
    }

    private void linkStatic(File wd, List<File> files, File outFile) throws IOException {
        List<String> relFiles = new ArrayList<String>(files.size());
        for (File f : files) {
            relFiles.add(f.getAbsolutePath().substring(wd.getAbsolutePath().length() + 1));
        }
        
        String arPath = "ar";
        if (config.getArBinPath() != null) {
            arPath = config.getArBinPath().getAbsolutePath();
        }

        outFile.getParentFile().mkdirs();
        
        List<List<String>> parts = new ArrayList<List<String>>();
        parts.add(relFiles);
        
        while (true) {
            outFile.delete();
            try {
                for (List<String> l : parts) {
                    CompilerUtil.exec(config, wd, arPath, "rcs", outFile, l);
                }
                break;
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().contains("Argument list too long")) {
                    config.getLogger().debug("Got 'Argument list too long' error when running ar. " 
                                + "Will try again using %d calls to ar.", parts.size() * 2);
                    List<List<String>> oldParts = parts;
                    parts = new ArrayList<List<String>>();
                    for (List<String> l : oldParts) {
                        int size = l.size();
                        if (size > 1) {
                            parts.add(l.subList(0, size / 2));
                            parts.add(l.subList(size / 2, size));
                        } else {
                            parts.add(l);
                        }
                    }
                    if (parts.size() == relFiles.size()) {
                        // We have split as much as possible but the argument list is still too long.
                        throw e;
                    }
                } else {
                    throw e;
                }
            }
        }
    }

    private boolean rebuildStructDependents(Collection<String> dirtyStructs, 
            Collection<Clazz> compiled) throws IOException {
        
        boolean done = true;
        for (Clazz clazz : config.getClazzes().listClasses()) {
            if (clazz.getClazzInfo().hasStructDependencies() 
                    && !compiled.contains(clazz.getInternalName())) {
                
                Set<String> structDependencies = 
                        new HashSet<String>(clazz.getClazzInfo().getStructDependencies());
                structDependencies.retainAll(dirtyStructs);
                
                if (!structDependencies.isEmpty()) {
                    config.getLogger().debug("Recompiling class %s which depends on " 
                            + "changed @Struct classes: ", clazz, structDependencies);
                    classCompiler.compile(clazz, true);
                    compiled.add(clazz);
                    if (clazz.getClazzInfo().isStruct()) {
                        /*
                         * Record struct classes. Other classes which depend on
                         * this class may have to be recompiled. 
                         */
                        dirtyStructs.add(clazz.getInternalName());
                        done = false;
                    }
                }
            }
        }
        return done;
    }
    
    private void compile(Collection<String> dirtyStructs, 
            Collection<Clazz> compiled) throws IOException {

        for (Path path : config.getClazzes().getPaths()) {
            for (Clazz clazz : path.listClasses()) {
                if (classCompiler.compile(clazz)) {
                    compiled.add(clazz);
                    if (clazz.getClazzInfo().isStruct()) {
                        /*
                         * Record struct classes. Other classes which depend on
                         * this class may have to be recompiled. 
                         */
                        dirtyStructs.add(clazz.getInternalName());
                    }
                }
            }
        }
    }
    
    public void compile() throws IOException {
        Set<String> dirtyStructs = new HashSet<String>();
        Set<Clazz> compiled = new HashSet<Clazz>();
        // First pass. Compile any changed classes.
        compile(dirtyStructs, compiled);
        // Second pass. Compile classes which depend on just compiled @Struct classes.
        while (!rebuildStructDependents(dirtyStructs, compiled)) {
        }
        
        if (!config.isSkipLinking()) {
            
//            Set<Path> paths = new HashSet<Path>();
//            for (Clazz clazz : compiled) {
//                paths.add(clazz.getPath());
//            }
//            
//            for (Path path : paths) {
//                for (Package pack : path.listPackages()) {
//                    File libFile = config.getStaticLibrary(pack);
//                    List<File> objectFiles = new ArrayList<File>();
//                    for (Clazz clazz : pack.listClasses()) {
//                        objectFiles.add(config.getOFile(clazz));
//                    }
//                    linkStatic(config.getCacheDir(path), objectFiles, libFile);
//                }
//            }
            
            linker.link();
        }
//        if (config.isDebug()) {
//            List<File> libFiles = new ArrayList<File>();
//            for (Path path : paths) {
//                buildClasspathEntry(path);
////                if (libFile != null) {
////                    libFiles.add(libFile);
////                }
//            }
////            if (!config.isSkipLinking()) {
////                buildExecutable(paths, libFiles);
////            }
//        } else {
//            throw new IllegalArgumentException("Release build not yet implemented");
//        }
    }
        
//    private void buildExecutable(List<Path> paths, List<File> libFiles) throws IOException {
//        Module module = new Module();
//        
//        List<Value> bootClasspathValues = new ArrayList<Value>();
//        List<Value> classpathValues = new ArrayList<Value>();
//        for (Path path : paths) {
//            String entryName = null;
//            if (config.isSkipInstall() && config.getTarget().canLaunchInPlace()) {
//                entryName = path.getFile().getAbsolutePath();
//            } else {
//                entryName = config.getTarget().getInstallRelativeArchivePath(path);
//            }
//            byte[] modUtf8 = Strings.stringToModifiedUtf8(entryName);
//            Global var = new Global(Strings.getStringVarName(modUtf8), Linkage.linker_private_weak, 
//                    new StringConstant(modUtf8), true);
//            module.addGlobal(var);
//            if (path.isInBootClasspath()) {
//                bootClasspathValues.add(new ConstantGetelementptr(var.ref(), 0, 0));
//            } else {
//                classpathValues.add(new ConstantGetelementptr(var.ref(), 0, 0));
//            }
//        }
//        bootClasspathValues.add(new NullConstant(Type.I8_PTR));
//        classpathValues.add(new NullConstant(Type.I8_PTR));
//        Global gBootClasspathValues = module.newGlobal(new ArrayConstant(
//                new ArrayType(bootClasspathValues.size(), Type.I8_PTR), 
//                bootClasspathValues.toArray(new Value[0])), true);
//        module.addGlobal(new Global("_nvmBcBootclasspath", new ConstantGetelementptr(gBootClasspathValues.ref(), 0, 0)));
//        Global gClasspathValues = module.newGlobal(new ArrayConstant(
//                new ArrayType(classpathValues.size(), Type.I8_PTR), 
//                classpathValues.toArray(new Value[0])), true);
//        module.addGlobal(new Global("_nvmBcClasspath", new ConstantGetelementptr(gClasspathValues.ref(), 0, 0)));
//            
//        List<Value> bootClassesValues = new ArrayList<Value>();
//        List<Value> classesValues = new ArrayList<Value>();
//        Set<String> seenClasses = new HashSet<String>();
//        for (Path path : paths) {
//            for (Clazz c : path.list()) {
//                if (seenClasses.contains(c.getInternalName())) {
//                    continue;
//                }
//                seenClasses.add(c.getInternalName());
//                
//                byte[] modUtf8 = Strings.stringToModifiedUtf8(c.getInternalName());
//                Global var = new Global(Strings.getStringVarName(modUtf8), Linkage.linker_private_weak, 
//                        new StringConstant(modUtf8), true);
//                module.addGlobal(var);
//                String funcName = "NullVM_" + Mangler.mangleString(c.getInternalName());
//                FunctionDeclaration func = new FunctionDeclaration(funcName, new FunctionType(Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
//                module.addFunctionDeclaration(func);
//                Value value = new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), 
//                        new ConstantGetelementptr(var.ref(), 0, 0),
//                        new ConstantBitcast(func.ref(), Type.I8_PTR));
//                if (path.isInBootClasspath()) {
//                    bootClassesValues.add(value);
//                } else {
//                    classesValues.add(value);
//                }
//            }
//        }
//        bootClassesValues.add(new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), new NullConstant(Type.I8_PTR), new NullConstant(Type.I8_PTR)));
//        classesValues.add(new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), new NullConstant(Type.I8_PTR), new NullConstant(Type.I8_PTR)));
//        Global gBootClassesValues = module.newGlobal(new ArrayConstant(
//                new ArrayType(bootClassesValues.size(), new StructureType(Type.I8_PTR, Type.I8_PTR)), 
//                bootClassesValues.toArray(new Value[0])), true);
//        module.addGlobal(new Global("_nvmBcBootClasses", new ConstantGetelementptr(gBootClassesValues.ref(), 0, 0)));
//        Global gClassesValues = module.newGlobal(new ArrayConstant(
//                new ArrayType(classesValues.size(), new StructureType(Type.I8_PTR, Type.I8_PTR)), 
//                classesValues.toArray(new Value[0])), true);
//        module.addGlobal(new Global("_nvmBcClasses", new ConstantGetelementptr(gClassesValues.ref(), 0, 0)));
//        
//        if (config.getMainClass() != null) {
//            Global g = module.newGlobal(new StringConstant(Strings.stringToModifiedUtf8(config.getMainClass())), true);
//            module.addGlobal(new Global("_nvmBcMainClass", new ConstantGetelementptr(g.ref(), 0, 0)));
//        }
//        
//        File configLl = new File(config.getTmpDir(), "config.ll");
//        FileUtils.writeStringToFile(configLl, module.toString(), "UTF-8");
//        File configS = new File(config.getTmpDir(), "config.s");
//        CompilerUtil.llc(config, configLl, configS);
//        
//        config.getTarget().build(Collections.singletonList(configS), libFiles);
//    }

    public static void main(String[] args) throws IOException {
        
        AppCompiler compiler = null;
        
        boolean verbose = false;
        boolean run = false;
        List<String> runArgs = new ArrayList<String>();
        try {
            Config.Builder builder = new Config.Builder();
            
            int i = 0;
            while (i < args.length) {
                if ("-cp".equals(args[i]) || "-classpath".equals(args[i])) {
                    for (String p : args[++i].split(File.pathSeparator)) {
                        builder.addClasspathEntry(new File(p));
                    }
                } else if ("-bcp".equals(args[i]) || "-bootcp".equals(args[i]) || "-bootclasspath".equals(args[i])) {
                    for (String p : args[++i].split(File.pathSeparator)) {
                        builder.addBootClasspathEntry(new File(p));
                    }
                } else if ("-jar".equals(args[i])) {
                    builder.mainJar(new File(args[++i]));
                } else if ("-o".equals(args[i])) {
                    builder.executable(args[++i]);
                } else if ("-d".equals(args[i])) {
                    builder.installDir(new File(args[++i]));
                } else if ("-cache".equals(args[i])) {
                    builder.cacheDir(new File(args[++i]));
                } else if ("-home".equals(args[i])) {
                    builder.nullVMHomeDir(new File(args[++i]));
                } else if ("-tmp".equals(args[i])) {
                    builder.tmpDir(new File(args[++i]));
                } else if ("-run".equals(args[i])) {
                    run = true;
                } else if ("-verbose".equals(args[i])) {
                    verbose = true;
                } else if ("-debug".equals(args[i])) {
                    builder.debug(true);
                } else if ("-skiprt".equals(args[i])) {
                    builder.skipRuntimeLib(true);
                } else if ("-skiplink".equals(args[i])) {
                    builder.skipLinking(true);
                } else if ("-clean".equals(args[i])) {
                    builder.clean(true);
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-cc-bin".equals(args[i])) {
                    builder.ccBinPath(new File(args[++i]));
                } else if ("-ar-bin".equals(args[i])) {
                    builder.arBinPath(new File(args[++i]));
                } else if ("-llvm-home".equals(args[i])) {
                    builder.llvmHomeDir(new File(args[++i]));
                } else if ("-os".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        builder.os(OS.valueOf(s));
                    }
                } else if ("-arch".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        builder.arch(Arch.valueOf(s));
                    }
                } else if ("-cpu".equals(args[i])) {
                    builder.cpu(args[++i]);
                } else if ("-roots".equals(args[i])) {
                    for (String p : args[++i].split(":")) {
                        builder.addRoot(p);
                    }
                } else if (args[i].startsWith("-D")) {
                } else if (args[i].startsWith("-X")) {
                } else if (args[i].startsWith("-")) {
                    throw new IllegalArgumentException("Unrecognized option: " + args[i]);
                } else {
                    builder.mainClass(args[i++]);
                    break;
                }
                i++;
            }
            
            while (i < args.length) {
                runArgs.add(args[i++]);
            }
            
            if (verbose) {
                builder.logger(new Logger() {
                    public void info(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                    public void error(String format, Object... args) {
                        System.err.format(format, args);
                        System.err.println();
                    }
                    public void warn(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                    public void debug(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                });
            }
            
            builder.skipInstall(run);
            
            compiler = new AppCompiler(builder.build());
        } catch (Throwable t) {
            String message = t.getMessage();
            if (t instanceof StringIndexOutOfBoundsException) {
                message = "Missing argument";
            }
            if (verbose && !(t instanceof StringIndexOutOfBoundsException) && !(t instanceof IllegalArgumentException)) {
                t.printStackTrace();
            }
            printUsageAndExit(message);            
        }
        
        try {
            compiler.compile();
            if (run) {
                compiler.config.getTarget().launch(runArgs);
            } else {
                compiler.compile();
                compiler.config.getTarget().install();
            }
        } catch (Throwable t) {
            String message = t.getMessage();
            if (verbose && !(t instanceof ExecuteException)) {
                t.printStackTrace();
            }
            printUsageAndExit(message);
        }
    }
    
    private static void printUsageAndExit(String errorMessage) {
        if (errorMessage != null) {
            System.err.format("nullvm: %s\n", errorMessage);
        }
        System.err.println("Usage: nullvm [-options] class [run-args]");
        System.err.println("   or  nullvm [-options] -jar jarfile [run-args]");
        System.err.println("   or  nullvm [-options] -skiplink");
        System.err.println("Options:");
        
        System.err.println("  -bootclasspath <list> ");
        System.err.println("  -bootcp <list>        ");
        System.err.println("  -bcp <list>           : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files. Used to locate the \n" 
                         + "                        java.* and javax.* classes. Default is \n"
                         + "                        $NULLVM_HOME/lib/nullvm-rt.jar.");
        System.err.println("  -cp <list>            ");
        System.err.println("  -classpath <list>     : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files.");
        System.err.println("  -cache <dir>          Directory where cached compiled class files will be placed.\n" 
                         + "                        Default is ~/.nullvm/cache");
        System.err.println("  -clean                Compile class files even if a compiled version already \n" 
                         + "                        exists in the cache.");
        System.err.println("  -d <dir>              Install the generated executable and other files in <dir>.\n" 
                         + "                        Default is <wd>/<class>");
        System.err.println("  -cc-bin <path>        Path to the c compiler binary. gcc and clang are supported.");
        System.err.println("  -ar-bin <path>        Path to the ar binary");
        System.err.println("  -home <dir>           Directory where NullVM runtime has been installed.\n"
        		         + "                        Default is $NULLVM_HOME");
        System.err.println("  -tmp <dir>            Directory where temporary files will be placed during "
        		         + "                        compilation. By default a new dir will be created under\n" 
        		         + "                        ${java.io.tmpdir}.");
        System.err.println("  -jar <path>           Use main class as specified by the manifest in this JAR \n" 
                         + "                        archive.");
        System.err.println("  -llvm-home <path>     Path where LLVM has been installed");
        System.err.println("  -o <name>             The name of the target executable or library");
        System.err.println("  -os <name>            The name of the OS to build for. Allowed values are \n" 
                         + "                        'auto', 'linux' and 'darwin'. Default is 'auto' which\n" 
                         + "                        means autodetect.");
        System.err.println("  -arch <name>          The name of the LLVM arch to compile for. Allowed values\n" 
                         + "                        are 'auto', 'i386' and 'x86_64' Default is 'auto' which \n" 
                         + "                        means autodetect.");
        System.err.println("  -cpu <name>           The name of the LLVM cpu to compile for. The LLVM default\n" 
                         + "                        is used by default. Use llc to determine allowed values.");
        System.err.println("  -roots <list>         : separated list of class patterns matching\n" 
                         + "                        classes that must be included when determinig the required\n" 
                         + "                        classes. If a main class is specified it will automatically\n" 
                         + "                        become a root. If no main class is specified and no roots\n" 
                         + "                        all classes will be included. A pattern is an ANT style\n" 
                         + "                        path pattern, e.g. com.foo.**.bar.*.Main.");
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -skiprt               Do not add default nullvm-rt.jar to bootclasspath");
        System.err.println("  -skiplink             Do not link the final executable");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -help, -?             Display this information");
        
        System.exit(errorMessage != null ? 1 : 0);
    }
    
}
