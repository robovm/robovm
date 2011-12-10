/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.Path;
import org.nullvm.compiler.llvm.ArrayConstant;
import org.nullvm.compiler.llvm.ArrayType;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.ConstantGetelementptr;
import org.nullvm.compiler.llvm.FunctionDeclaration;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Global;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.Module;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.StringConstant;
import org.nullvm.compiler.llvm.StructureConstant;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Value;

/**
 *
 * @version $Id$
 */
public class AppCompiler {
    private final Config config;
    
    public AppCompiler(Config config) {
        this.config = config;
    }
    
    private void classToIr(Clazz clazz, File outFile) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(outFile);
            new ClassCompiler().compile(clazz, out);
        } catch (Throwable t) {
            FileUtils.deleteQuietly(outFile);
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
    }

    private void llvmAs(File inFile, File outFile) throws IOException {
        String llvmAsPath = "llvm-as";
        if (config.getLlvmBinDir() != null) {
            llvmAsPath = new File(config.getLlvmBinDir(), "llvm-as").getAbsolutePath();
        }
        
        outFile.getParentFile().mkdirs();
        exec(llvmAsPath, "-o=" + outFile.toString(), inFile);
    }    

    private void opt(File inFile, File outFile, String ... options) throws IOException {
        String optPath = "opt";
        if (config.getLlvmBinDir() != null) {
            optPath = new File(config.getLlvmBinDir(), "opt").getAbsolutePath();
        }

        outFile.getParentFile().mkdirs();
        exec(optPath, options, "-o=" + outFile.toString(), inFile);
    }
    
    private void llc(File inFile, File outFile) throws IOException {
        String llcPath = "llc";
        if (config.getLlvmBinDir() != null) {
            llcPath = new File(config.getLlvmBinDir(), "llc").getAbsolutePath();
        }
  
        ArrayList<String> opts = new ArrayList<String>();
        opts.add("-march=" + config.getArch().getLlvmName());
        if (config.getCpu() != null) {
            opts.add("-mcpu=" + config.getCpu());
        }
    
        outFile.getParentFile().mkdirs();
        exec(llcPath, opts, "-o=" + outFile.toString(), inFile);
    }
    
    private void gcc(File inFile, File outFile, String ... options) throws IOException {
        String gccPath = "gcc";
        if (config.getGccBinPath() != null) {
            gccPath = config.getGccBinPath().getAbsolutePath();
        }

        List<String> opts = new ArrayList<String>();
        if (config.isDebug()) {
            opts.add("-g");
        }
        if (config.getOs() == OS.darwin) {
            opts.add("-arch");            
            opts.add(config.getArch().toString());            
        }
        opts.addAll(Arrays.asList(options));

        outFile.getParentFile().mkdirs();
        exec(gccPath, "-c", "-o", outFile, opts, inFile);
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
                    exec(wd, arPath, "rcs", outFile, l);
                }
                break;
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().contains("Argument list too long")) {
                    config.getLogger().debug("Got 'Argument list too long' error when running ar. " 
                                + "Will try again using %d calls to ar.\n", parts.size() * 2);
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
    
    private File buildClassFileDebug(Path path, Clazz clazz) throws IOException {
        String className = clazz.getInternalName();
        File outFile = new File(config.getObjectCacheDir(path), className.replace('/', File.separatorChar) + ".class.o");
        File llFile = new File(config.getLlvmCacheDir(path), className.replace('/', File.separatorChar) + ".class.ll");
        
        if (!config.isClean() && outFile.exists() && outFile.lastModified() >= clazz.lastModified()) {
            config.getLogger().debug("Skipping unchanged class file: " + clazz.getFileName());
            return outFile;
        }
        
        llFile.getParentFile().mkdirs();
        outFile.getParentFile().mkdirs();
        config.getLogger().debug("Compiling class file '%s' to object file '%s'", clazz.getFileName(), outFile);
        
        classToIr(clazz, llFile);
        File bcFile = changeExt(llFile, "bc");
        opt(llFile, bcFile, "-mem2reg", "-always-inline");
        File sFile = changeExt(outFile, "s");
        llc(bcFile, sFile);
        gcc(sFile, outFile);
        return outFile;
    }    

    private File buildClasspathEntry(Path path) throws IOException {
        File outFile = config.getStaticLibrary(path);
        
        if (!config.isClean() && outFile.exists() && !path.hasChangedSince(outFile.lastModified())) {
            config.getLogger().debug("Skipping unchanged classpath entry: " + path);
            return outFile;
        }

        List<File> objectFiles = new ArrayList<File>();
        for (Clazz clazz : path.list()) {
            objectFiles.add(buildClassFileDebug(path, clazz));
        }
        
        if (objectFiles.isEmpty()) {
            config.getLogger().debug("Skipping empty classpath entry: " + path);
            return null;
        }

        config.getLogger().debug("Building static library '%s' for classpath entry: %s", outFile, path);
        
        linkStatic(config.getObjectCacheDir(path), objectFiles, outFile);
        return outFile;
    }
    
    private void build(List<Path> paths) throws IOException {
        if (config.isDebug()) {
            List<File> libFiles = new ArrayList<File>();
            for (Path path : paths) {
                File libFile = buildClasspathEntry(path);
                if (libFile != null) {
                    libFiles.add(libFile);
                }
            }
            if (!config.isSkipLinking()) {
                buildExecutable(paths, libFiles);
            }
        } else {
            throw new IllegalArgumentException("Release build not yet implemented");
        }
    }
        
    private void buildExecutable(List<Path> paths, List<File> libFiles) throws IOException {
        File outFile = new File(config.getTargetDir(), config.getTarget());
        
        config.getLogger().debug("Building executable '%s'", outFile);
        
        Module module = new Module();
        List<Value> bootcpValues = new ArrayList<Value>();
        List<Value> cpValues = new ArrayList<Value>();
        Set<String> seenClasses = new HashSet<String>();
        for (Path path : paths) {
            for (Clazz c : path.list()) {
                if (seenClasses.contains(c.getInternalName())) {
                    continue;
                }
                seenClasses.add(c.getInternalName());
                
                byte[] modUtf8 = ClassCompiler.stringToModifiedUtf8(c.getInternalName());
                Global var = new Global(ClassCompiler.getStringVarName(modUtf8), Linkage.linker_private_weak, 
                        new StringConstant(modUtf8), true);
                module.addGlobal(var);
                String funcName = "NullVM_" + ClassCompiler.mangleString(c.getInternalName());
                FunctionDeclaration func = new FunctionDeclaration(funcName, new FunctionType(Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
                module.addFunctionDeclaration(func);
                Value value = new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), 
                        new ConstantGetelementptr(var.ref(), 0, 0),
                        new ConstantBitcast(func.ref(), Type.I8_PTR));
                if (path.isInBootClasspath()) {
                    bootcpValues.add(value);
                } else {
                    cpValues.add(value);
                }
            }
        }
        bootcpValues.add(new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), new NullConstant(Type.I8_PTR), new NullConstant(Type.I8_PTR)));
        cpValues.add(new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), new NullConstant(Type.I8_PTR), new NullConstant(Type.I8_PTR)));
        Global gbcp = module.newGlobal(new ArrayConstant(
                new ArrayType(bootcpValues.size(), new StructureType(Type.I8_PTR, Type.I8_PTR)), 
                bootcpValues.toArray(new Value[0])), true);
        module.addGlobal(new Global("_nvmBcBootclasspathEntries", new ConstantGetelementptr(gbcp.ref(), 0, 0)));
        Global gcp = module.newGlobal(new ArrayConstant(
                new ArrayType(cpValues.size(), new StructureType(Type.I8_PTR, Type.I8_PTR)), 
                cpValues.toArray(new Value[0])), true);
        module.addGlobal(new Global("_nvmBcClasspathEntries", new ConstantGetelementptr(gcp.ref(), 0, 0)));
        if (config.getMainClass() != null) {
            Global g = module.newGlobal(new StringConstant(ClassCompiler.stringToModifiedUtf8(config.getMainClass())), true);
            module.addGlobal(new Global("_nvmBcMainClass", new ConstantGetelementptr(g.ref(), 0, 0)));
        }
        
        File configLl = new File(config.getTmpDir(), "config.ll");
        FileUtils.writeStringToFile(configLl, module.toString(), "UTF-8");
        File configS = new File(config.getTmpDir(), "config.s");
        llc(configLl, configS);
        
        String gccPath = "gcc";
        if (config.getGccBinPath() != null) {
            gccPath = config.getGccBinPath().getAbsolutePath();
        }
        
        List<String> gccArgs = new ArrayList<String>();
        List<String> libArgs = new ArrayList<String>();
        
        libArgs.addAll(Arrays.asList("-lnullvm-bc", "-lm", "-lnullvm-core", "-lnullvm-hyprt"));
        
        gccArgs.add("-L");
        gccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
//        gccArgs.add("-Xlinker");
//        gccArgs.add("--gc-sections");
        if (config.getOs() == OS.linux) {
            libArgs.add("-l:libgc.so.1");
            gccArgs.add("-Xlinker");
            gccArgs.add("-rpath=$ORIGIN");
        } else if (config.getOs() == OS.darwin) {
            libArgs.add("-lgc");
            File unexportedSymbolsFile = new File(config.getTmpDir(), "unexported_symbols");
            FileUtils.writeStringToFile(unexportedSymbolsFile, "*\n", "ASCII");
            gccArgs.add("-unexported_symbols_list");
            gccArgs.add(unexportedSymbolsFile.getAbsolutePath());
            
            // Needed on Mac OS X >= 10.6 to prevent linker from compacting unwind info which breaks _Unwind_FindEnclosingFunction
            gccArgs.add("-Xlinker");
            gccArgs.add("-no_compact_unwind");
            
            gccArgs.add("-arch");            
            gccArgs.add(config.getArch().toString());            
        }
        
        exec(gccPath, "-o", outFile, "-g", gccArgs, configS, libFiles, libArgs);
    }
    
    private void stripArchive(File input, File output) throws IOException {
        
        if (!config.isClean() && output.exists() && output.lastModified() >= input.lastModified()) {
            config.getLogger().debug("Not stripping unchanged archive file '%s'", input);
            return;
        }
        
        ZipFile archive = null;
        try {
            archive = new ZipFile(input);
        
            File strippedFile = output;
            config.getLogger().debug("Creating stripped archive file '%s'", strippedFile);
            
            ZipOutputStream out = null;
            try {
                out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(strippedFile)));
                
                Enumeration<? extends ZipEntry> entries = archive.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    ZipEntry newEntry = new ZipEntry(entry.getName());
                    newEntry.setTime(entry.getTime());
                    out.putNextEntry(newEntry);
                    InputStream in = null;
                    try {
                        if (!entry.getName().toLowerCase().endsWith(".class")) {
                            in = archive.getInputStream(entry);
                        } else {
                            in = new ByteArrayInputStream(new byte[0]);
                        }
                        IOUtils.copy(in, out);
                        out.closeEntry();
                    } finally {
                        IOUtils.closeQuietly(in);
                    }
                }
            } finally {
                IOUtils.closeQuietly(out);
            }
        } finally {
            try {
                archive.close();
            } catch (Throwable t) {}
        }
    }
        
    private void exec(String cmd, Object ... args) throws IOException {
        exec(null, cmd, args);
    }
    
    private void exec(File wd, String cmd, Object ... args) throws IOException {
        execWithEnv(wd, null, cmd, args);
    }

    @SuppressWarnings("rawtypes")
    private void execWithEnv(File wd, Map env, String cmd, Object ... args) throws IOException {
        execWithEnv(wd, env, null, cmd, args);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void execWithEnv(File wd, Map env, ExecuteStreamHandler streamHandler, 
            String cmd, Object ... args) throws IOException {
        
        CommandLine commandLine = CommandLine.parse(cmd);
        for (Object a : args) {
            if (a instanceof Collection) {
                for (Object o : (Collection<Object>) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString());
                }
            } else if (a instanceof Object[]) {
                for (Object o : (Object[]) a) {
                    commandLine.addArgument(o instanceof File ? ((File) o).getAbsolutePath() : o.toString());
                }
            } else {
                commandLine.addArgument(a instanceof File ? ((File) a).getAbsolutePath() : a.toString());
            }
        }
        
        config.getLogger().debug("  %s", commandLine);
        
        Executor executor = new DefaultExecutor();
        if (wd != null) {
            executor.setWorkingDirectory(wd);
        }
        if (streamHandler != null) {
            executor.setStreamHandler(streamHandler);
        }
        if (env == null) {
            env = EnvironmentUtils.getProcEnvironment();
        }
        executor.setExitValue(0);
        executor.execute(commandLine, env);
    }
    
    public void compile() throws IOException {
        ClassCompiler.init(config.getClazzes());
        build(config.getClazzes().getPaths());
    }
    
    public void run() throws IOException {
        compile();
        
        if (!config.isSkipLinking()) {
            if (config.isRun()) {
                // Run in place
                List<String> bootclasspathContents = new ArrayList<String>();
                List<String> classpathContents = new ArrayList<String>();
                for (Path path : config.getClazzes().getPaths()) {
                    if (path.isInBootClasspath()) {
                        bootclasspathContents.add(path.getFile().getAbsolutePath());
                    } else {
                        classpathContents.add(path.getFile().getAbsolutePath());
                    }
                }
                FileUtils.writeStringToFile(new File(config.getTargetDir(), "bootclasspath"), join(bootclasspathContents, "\r\n"), "UTF-8");
                FileUtils.writeStringToFile(new File(config.getTargetDir(), "classpath"), join(classpathContents, "\r\n"), "UTF-8");
    
                runTarget();
            } else {
                
                for (File f : config.getOsArchDepLibDir().listFiles()) {
                    if (f.getName().matches(".*\\.(so|dylib)(\\.1)?")) {
                        FileUtils.copyFileToDirectory(f, config.getTargetDir());
                    }
                }
                
                File libBoot = new File(new File(config.getTargetDir(), "lib"), "boot");
                libBoot.mkdirs();
                File libMain = new File(new File(config.getTargetDir(), "lib"), "main");
                libMain.mkdirs();
    
                List<String> bootclasspathContents = new ArrayList<String>();
                List<String> classpathContents = new ArrayList<String>();
                for (Path path : config.getClazzes().getPaths()) {
                    File dest = null;
                    if (path.isInBootClasspath()) {
                        dest = new File(libBoot, config.getArchiveName(path));
                        bootclasspathContents.add("lib/boot/" + dest.getName());
                    } else {
                        dest = new File(libMain, config.getArchiveName(path));
                        classpathContents.add("lib/main/" + dest.getName());
                    }
                    stripArchive(config.getArchivePath(path), dest);
                }
                
                FileUtils.writeStringToFile(new File(config.getTargetDir(), "bootclasspath"), join(bootclasspathContents, "\r\n"), "UTF-8");
                FileUtils.writeStringToFile(new File(config.getTargetDir(), "classpath"), join(classpathContents, "\r\n"), "UTF-8");
            }
        }
    }

    @SuppressWarnings({ "unchecked" })
    private void runTarget() throws IOException {
        Map<String, String> env = new HashMap<String, String>();
        env.putAll(EnvironmentUtils.getProcEnvironment());
        String ldLibraryPathVar = "LD_LIBRARY_PATH";
        if (config.getOs() == OS.darwin) {
            ldLibraryPathVar = "DY" + ldLibraryPathVar;
        }
        String ldLibraryPath = env.get(ldLibraryPathVar);
        ldLibraryPath = ldLibraryPath == null ? "" : ":" + ldLibraryPath;
        env.put(ldLibraryPathVar, config.getOsArchDepLibDir().getAbsolutePath() + ldLibraryPath);
        try {
            execWithEnv(config.getTargetDir(), env, new File(config.getTargetDir(), config.getTarget()).getAbsolutePath());
        } catch (ExecuteException e) {
            System.exit(e.getExitValue());
        }
    }

    public static void main(String[] args) throws IOException {
        
        AppCompiler main = null;
        
        boolean verbose = false;
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
                    builder.target(args[++i]);
                } else if ("-d".equals(args[i])) {
                    builder.targetDir(new File(args[++i]));
                } else if ("-cache".equals(args[i])) {
                    builder.cacheDir(new File(args[++i]));
                } else if ("-home".equals(args[i])) {
                    builder.nullVMHomeDir(new File(args[++i]));
                } else if ("-run".equals(args[i])) {
                    builder.run();
                } else if ("-verbose".equals(args[i])) {
                    verbose = true;
                } else if ("-debug".equals(args[i])) {
                    builder.debug();
                } else if ("-skiprt".equals(args[i])) {
                    builder.skipRuntimeLib();
                } else if ("-skiplink".equals(args[i])) {
                    builder.skipLinking();
                } else if ("-clean".equals(args[i])) {
                    builder.clean();
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-gcc-bin".equals(args[i])) {
                    builder.gccBinPath(new File(args[++i]));
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
                builder.addRunArg(args[i++]);
            }
            
            if (verbose) {
                builder.logger(new Logger() {
                    public void warn(String format, Object... args) {
                        System.err.format(format, args);
                        System.err.println();
                    }
                    public void info(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                    public void error(String format, Object... args) {
                        System.err.format(format, args);
                        System.err.println();
                    }
                    public void debug(String format, Object... args) {
                        System.out.format(format, args);
                        System.out.println();
                    }
                });
            }
            
            main = new AppCompiler(builder.build());
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
            main.run();
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
        System.err.println("   or  nullvm [-options] -nolink");
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
        System.err.println("  -d <dir>              Place the generated executable and other files in <dir>.");
        System.err.println("  -gcc-bin <path>       Path to the gcc binary");
        System.err.println("  -ar-bin <path>        Path to the ar binary");
        System.err.println("  -home <dir>           Directory where NullVM runtime has been installed.\n"
        		         + "                        Default is $NULLVM_HOME");
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
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -skiprt               Do not add default nullvm-rt.jar to bootclasspath");
        System.err.println("  -skiplink             Do not link the final executable");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -help, -?             Display this information");
        
        System.exit(errorMessage != null ? 1 : 0);
    }
    
    private static File changeExt(File f, String newExt) {
        String name = f.getName();
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return new File(f.getParent(), name + "." + newExt);
        }
        return new File(f.getParent(), name.substring(0, index) + "." + newExt);
    }
    
    private static String join(Collection<String> coll, String sep) {
        StringBuilder sb = new StringBuilder();
        for (String s : coll) {
            if (sb.length() > 0) {
                sb.append(sep);
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
}
