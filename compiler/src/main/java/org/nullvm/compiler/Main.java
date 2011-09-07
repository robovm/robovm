/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.nullvm.compiler.clazz.Clazz;
import org.nullvm.compiler.clazz.Clazzes;
import org.nullvm.compiler.clazz.DirectoryPath;
import org.nullvm.compiler.clazz.Path;
import org.nullvm.compiler.clazz.ZipFilePath;
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
public class Main {
    public enum OS {linux, darwin}
    public enum Arch {i386, x86_64, arm}

    private String classpath = null;
    private String bootclasspath = null;
    private File output = null;
    private String target = null;
    private File home = new File(System.getProperty("user.home"), ".nullvm");
    private File cache = new File(home, "cache");
    private File llvmCache = null;
    private File objectCache = null;
    private boolean clean = false;
    private File llvmBinDir = null;    
    private List<String> llcOpts = new ArrayList<String>();
    private List<String> optOpts = new ArrayList<String>();
    private List<String> gccOpts = new ArrayList<String>();
    private File gccBin = null;    
    private File arBin = null;    
    private OS os = null;
    private Arch arch = null;
    private String cpu = null;
    private boolean debug = false;
    private boolean nort = false;
    private boolean verbose = false;
    private File jarFile;
    private boolean run = false;
    private String mainClass;
    private List<String> runArgs = new ArrayList<String>();
    
    private File homeLib;
    private File homeLibOsArch;
    private List<File> bootClassPathFiles = new ArrayList<File>();
    private List<File> classPathFiles = new ArrayList<File>();
    private PrintStream stdout = System.out;
    private File tmpFile;
    private String host;
    
    private void printUsageAndExit(String errorMessage) {
        if (errorMessage != null) {
            System.err.format("nullvm: %s\n", errorMessage);
        }
        System.err.println("Usage: nullvm [-options] class [run-args]");
        System.err.println("   or  nullvm [-options] -jar jarfile [run-args]");
        System.err.println("Options:");
        
        System.err.println("  -bootclasspath <list> ");
        System.err.println("  -bootcp <list>        ");
        System.err.println("  -bcp <list>           : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files. Used to locate the \n" 
                         + "                        java.* and javax.* classes. Default is \n"
                         + "                        ~/nullvm/lib/nullvm-rt.jar.");
        System.err.println("  -cache <dir>          Directory where cached compiled class files will be placed.\n" 
                         + "                        Default is ~/.nullvm/cache");
        System.err.println("  -clean                Compile class files even if a compiled version already \n" 
                         + "                        exists in the cache.");
        System.err.println("  -cp <list>            ");
        System.err.println("  -classpath <list>     : separated list of directories, JAR archives, and ZIP \n" 
                         + "                        archives to search for class files.");
        System.err.println("  -d <dir>              Place the generated executable and other files in <dir>.");
        System.err.println("  -gcc-bin <path>       Path to the gcc binary");
        System.err.println("  -gcc-opt <opt>        Extra option to pass to gcc");
        System.err.println("  -ar-bin <path>        Path to the ar binary");
        System.err.println("  -home <dir>           Directory where NullVM runtime has been installed and \n" 
                         + "                        where compiled class files will be cached. Default is \n" 
                         + "                        ~/.nullvm");
        System.err.println("  -jar <path>           Use main class as specified by the manifest in this JAR \n" 
                         + "                        archive.");
        System.err.println("  -llc-opt <opt         Extra option to pass to llc");
        System.err.println("  -llvm-bin-dir <path>  Path where the LLVM binaries can be found");
        System.err.println("  -o <name>             The name of the target executable or library");
        System.err.println("  -opt-opt <opt>        Extra option to pass to opt");
        System.err.println("  -os <name>            The name of the OS to build for. Allowed values are \n" 
                         + "                        'auto', 'linux' and 'darwin'. Default is 'auto' which\n" 
                         + "                        means autodetect.");
        System.err.println("  -arch <name>          The name of the LLVM arch to compile for. Allowed values\n" 
                         + "                        are 'auto', 'i386' and 'x86_64' Default is 'auto' which \n" 
                         + "                        means autodetect.");
        System.err.println("  -cpu <name>           The name of the LLVM cpu to compile for. The LLVM default\n" 
                         + "                        is used by default. Use llc to determine allowed values.");
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -nort                 Do not add default nullvm-rt.jar to bootclasspath");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -help, -?             Display this information");
        
        System.exit(errorMessage != null ? 1 : 0);
    }

    private List<Clazz> getChangedClasses(List<ClasspathEntry> all) {
        List<Clazz> changed = new ArrayList<Clazz>();
        for (ClasspathEntry entry : all) {
            for (Clazz clazz : entry.getPath().list()) {
                String className = clazz.getInternalName();
                File outFile = new File(entry.getLlvmCacheDir(), className.replace('/', File.separatorChar) + "" + ".class.ll");
                if (clean || !outFile.exists() || outFile.lastModified() < clazz.lastModified()) {
                    changed.add(clazz);
                }
            }
        }
        return changed;
    }
    
    private void classToIr(Clazz clazz, File outFile) throws IOException {
        OutputStream out = null;
        try {
            if (!clean && outFile.exists() && outFile.lastModified() >= clazz.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged class file: " + clazz.getFileName());
                }
                return;
            }
            outFile.getParentFile().mkdirs();
            if (verbose) {
                stdout.format("Compiling class file '%s' to LLVM IR file '%s'\n", clazz.getFileName(), outFile);
            }
            out = new FileOutputStream(outFile);
            new SootClassCompiler().compile(clazz, out);
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
        if (!clean && outFile.exists() && outFile.lastModified() >= inFile.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM IR file: " + inFile);
            }
            return;
        }
        if (verbose) {
            stdout.format("Compiling LLVM IR file '%s' to LLVM bitcode file '%s'\n", inFile, outFile);
        }

        String llvmAsPath = "llvm-as";
        if (llvmBinDir != null) {
            llvmAsPath = new File(llvmBinDir, "llvm-as").getAbsolutePath();
        }
        
        outFile.getParentFile().mkdirs();
        exec(llvmAsPath, "-o=" + outFile.toString(), inFile);
    }    

    private void opt(File inFile, File outFile, String ... options) throws IOException {
        if (!clean && outFile.exists() && outFile.lastModified() >= inFile.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM bitcode file: " + inFile);
            }
            return;
        }
        
        String optPath = "opt";
        if (llvmBinDir != null) {
            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
        }

        outFile.getParentFile().mkdirs();
        exec(optPath, options, "-o=" + outFile.toString(), inFile);
    }
    
    private void llc(File inFile, File outFile) throws IOException {
        if (!clean && outFile.exists() && outFile.lastModified() >= inFile.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM bitcode file: " + inFile);
            }
            return;
        }
        if (verbose) {
            stdout.format("Compiling LLVM bitcode file '%s' to assembler file '%s'\n", inFile, outFile);
        }
      
        String llcPath = "llc";
        if (llvmBinDir != null) {
            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
        }
  
        ArrayList<String> opts = new ArrayList<String>(llcOpts);
//        opts.add("-relocation-model=pic");
        if (arch == Arch.x86_64) {
            opts.add("-march=x86-64");
        } else if (arch == Arch.i386) {
            opts.add("-march=x86");
        }
        if (cpu != null) {
            opts.add("-mcpu=" + cpu);
        }
    
        outFile.getParentFile().mkdirs();
        exec(llcPath, opts, "-o=" + outFile.toString(), inFile);
    }
    
    private void gcc(File inFile, File outFile, String ... options) throws IOException {
        if (!clean && outFile.exists() && outFile.lastModified() >= inFile.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged GCC input file: " + inFile);
            }
            return;
        }
        if (verbose) {
            stdout.format("Compiling '%s' to '%s'\n", inFile, outFile);
        }

        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }

        List<String> opts = new ArrayList<String>(gccOpts);
        if (debug) {
            opts.add("-g");
        }
        if (os == OS.darwin) {
            opts.add("-arch");            
            opts.add(arch.toString());            
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
        if (arBin != null) {
            arPath = arBin.getAbsolutePath();
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
                    if (verbose) {
                        stdout.printf("Got 'Argument list too long' error when running ar. " 
                                + "Will try again using %d calls to ar.\n", parts.size() * 2);
                    }
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
    
    private File buildClassFileDebug(ClasspathEntry entry, Clazz clazz) throws IOException {
        String className = clazz.getInternalName();
        File llFile = new File(entry.getLlvmCacheDir(), className.replace('/', File.separatorChar) + "" + ".class.ll");
        classToIr(clazz, llFile);
        File tmpBcFile = changeExt(llFile, "tmp.bc");
        llvmAs(llFile, tmpBcFile);
        File bcFile = changeExt(tmpBcFile, "bc");
        opt(tmpBcFile, bcFile, "-mem2reg", "-always-inline");
        File sFile = changeExt(rebase(bcFile, entry.getLlvmCacheDir(), entry.getObjectCacheDir()), "s");
        llc(bcFile, sFile);
        File oFile = changeExt(sFile, "o");
        gcc(sFile, oFile);
        return oFile;
    }    

    private File buildClasspathEntry(ClasspathEntry entry) throws IOException {
        File outFile = entry.getStaticLibrary();
        
        if (!clean && outFile.exists() && !entry.hasChangedAfter(outFile.lastModified())) {
            if (verbose) {
                stdout.println("Skipping unchanged classpath entry: " + entry);
            }
            return outFile;
        }

        List<File> objectFiles = new ArrayList<File>();
        for (Clazz clazz : entry.getPath().list()) {
            objectFiles.add(buildClassFileDebug(entry, clazz));
        }
        
        if (objectFiles.isEmpty()) {
            if (verbose) {
                stdout.format("Skipping empty classpath entry: " + entry);
            }
            return null;
        }

        if (verbose) {
            stdout.format("Building static library '%s' for classpath entry\n", outFile, entry);
        }
        
        linkStatic(entry.getObjectCacheDir(), objectFiles, outFile);
        return outFile;
    }
    
//    private void processClasspathEntry(ClasspathEntry entry) throws IOException {
//        buildBcLibrary(entry);
//    }
    
    private void build(List<ClasspathEntry> entries) throws IOException {
        if (debug) {
            List<File> libFiles = new ArrayList<File>();
            for (ClasspathEntry entry : entries) {
                File libFile = buildClasspathEntry(entry);
                if (libFile != null) {
                    libFiles.add(libFile);
                }
            }
            buildExecutable(entries, libFiles);
        } else {
            throw new IllegalArgumentException("Release build not yet implemented");
        }
    }
    
//    private File processBcFile(ClasspathEntry entry, File f) throws IOException {
//        File outFile = changeExt(rebase(f, entry.getLlvmCacheDir(), entry.getObjectCacheDir()), "s");
//        
//        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
//            if (verbose) {
//                stdout.println("Skipping unchanged LLVM bitcode file: " + f);
//            }
//            return processGccFile(entry, outFile);
//        }
//        if (verbose) {
//            stdout.format("Compiling LLVM bitcode file '%s' to assembler file '%s'\n", f, outFile);
//        }
//            
//        outFile.getParentFile().mkdirs();
//        
//        String llcPath = "llc";
//        String optPath = "opt";
//        if (llvmBinDir != null) {
//            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
//            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
//        }
//        
//        List<String> opts = null;
//        
//        File outOptedFile = changeExt(f, (debug ? "debug" : "release") + ".opted.bc");
//        if (clean || !outOptedFile.exists() || outOptedFile.lastModified() < f.lastModified()) {
//            opts = new ArrayList<String>(optOpts);
//            opts.add("-mem2reg");
//            opts.add("-always-inline");
//            if (!debug) {
//                // -std-compile-opts minus -inline and -tailcallelim 
//                opts.addAll(Arrays.asList(
//                       ("-preverify -domtree -verify -lowersetjmp -globalopt -ipsccp " 
//                      + "-deadargelim -instcombine -simplifycfg -basiccg -prune-eh " 
//                      + "-functionattrs -argpromotion -domtree -domfrontier " 
//                      + "-scalarrepl -simplify-libcalls -instcombine -lazy-value-info " 
//                      + "-jump-threading -simplifycfg -instcombine " 
//                      + "-simplifycfg -reassociate -domtree -loops -loop-simplify " 
//                      + "-lcssa -loop-rotate -licm -lcssa -loop-unswitch -instcombine " 
//                      + "-scalar-evolution -loop-simplify -lcssa -iv-users -indvars " 
//                      + "-loop-deletion -loop-unroll -instcombine -memdep -gvn -memdep " 
//                      + "-memcpyopt -sccp -instcombine -lazy-value-info -jump-threading " 
//                      + "-correlated-propagation -domtree -memdep -dse -adce -simplifycfg " 
//                      + "-strip-dead-prototypes -print-used-types -deadtypeelim " 
//                      + "-globaldce -constmerge -preverify -domtree -verify").split(" ")));
//            }
//        
//            exec(optPath, opts, "-o=" + outOptedFile.toString(), f);
//        }
//        
//        opts = new ArrayList<String>(llcOpts);
//        opts.add("-relocation-model=pic");
//        opts.add("-march=" + (arch == Arch.x86_64 ? "x86-64" : arch.toString()));
//        if (cpu != null) {
//            opts.add("-mcpu=" + cpu);
//        }
//
//        exec(llcPath, opts, "-o=" + outFile.toString(), outOptedFile);
//            
//        return processGccFile(entry, outFile);
//    }
    
//    private File buildBcLibrary(ClasspathEntry entry) throws IOException {
//        File outFile = entry.getBcLibrary();
//
//        if (!clean && outFile.exists() && !entry.hasChangedAfter(outFile.lastModified())) {
//            if (verbose) {
//                stdout.println("Skipping unchanged classpath entry: " + entry);
//            }
//            return outFile;
//        }
//        
//        List<File> files = processClassFiles(entry);
//        List<String> relFiles = new ArrayList<String>(files.size());
//        for (File f: files) {
//            relFiles.add(f.getAbsolutePath().substring(entry.getLlvmCacheDir().getAbsolutePath().length() + 1));
//        }
//
//        if (verbose) {
//            stdout.format("Building bitcode library '%s'\n", outFile);
//        }
//        
//        String llvmLinkPath = "llvm-link";
//        String optPath = "opt";
//        if (llvmBinDir != null) {
//            llvmLinkPath = new File(llvmBinDir, "llvm-link").getAbsolutePath();
//            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
//        }
//
//        File tmpFile = changeExt(outFile, "tmp.bc");
//        exec(entry.getLlvmCacheDir(), llvmLinkPath, "-o=" + tmpFile.toString(), relFiles);
//        exec(optPath, "-mem2reg", "-always-inline", "-o=" + outFile.toString(), tmpFile);
//        
//        return outFile;
//    }
    
    private void buildExecutable(List<ClasspathEntry> entries, List<File> libFiles) throws IOException {
        File outFile = new File(output, target);
        
        if (verbose) {
            stdout.format("Building executable '%s'\n", outFile);
        }
        
        Module module = new Module();
        List<Value> bootcpValues = new ArrayList<Value>();
        List<Value> cpValues = new ArrayList<Value>();
        Set<String> seenClasses = new HashSet<String>();
        for (ClasspathEntry entry : entries) {
            for (Clazz c : entry.getPath().list()) {
                if (seenClasses.contains(c.getInternalName())) {
                    continue;
                }
                seenClasses.add(c.getInternalName());
                
                byte[] modUtf8 = SootClassCompiler.stringToModifiedUtf8(c.getInternalName());
                Global var = new Global(SootClassCompiler.getStringVarName(modUtf8), Linkage.linker_private_weak, 
                        new StringConstant(modUtf8), true);
                module.addGlobal(var);
                String funcName = "NullVM_" + SootClassCompiler.mangleString(c.getInternalName());
                FunctionDeclaration func = new FunctionDeclaration(funcName, new FunctionType(Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
                module.addFunctionDeclaration(func);
                Value value = new StructureConstant(new StructureType(Type.I8_PTR, Type.I8_PTR), 
                        new ConstantGetelementptr(var.ref(), 0, 0),
                        new ConstantBitcast(func.ref(), Type.I8_PTR));
                if (entry.isInBootClasspath()) {
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
        if (mainClass != null) {
            Global g = module.newGlobal(new StringConstant(SootClassCompiler.stringToModifiedUtf8(mainClass)), true);
            module.addGlobal(new Global("_nvmBcMainClass", new ConstantGetelementptr(g.ref(), 0, 0)));
        }
        
        File configLl = new File(tmpFile, "config.ll");
        FileUtils.writeStringToFile(configLl, module.toString(), "UTF-8");
        File configS = new File(tmpFile, "config.s");
        llc(configLl, configS);
        
        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }
        
        List<String> gccArgs = new ArrayList<String>();
        List<String> libArgs = new ArrayList<String>();
        
        libArgs.addAll(Arrays.asList("-lm", "-lnullvm-core", "-lnullvm-bc", "-lnullvm-hyprt"));
        
        gccArgs.add("-L");
        gccArgs.add(homeLibOsArch.getAbsolutePath());
        if (os == OS.linux) {
            libArgs.add("-l:libgc.so.1");
            gccArgs.add("-Xlinker");
            gccArgs.add("-rpath=$ORIGIN");
        }
        if (os == OS.darwin) {
            libArgs.add("-lgc");
            File unexportedSymbolsFile = new File(tmpFile, "unexported_symbols");
            FileUtils.writeStringToFile(unexportedSymbolsFile, "*\n", "ASCII");
            gccArgs.add("-unexported_symbols_list");
            gccArgs.add(unexportedSymbolsFile.getAbsolutePath());
            
            // Needed on Mac OS X >= 10.6 to prevent linker from compacting unwind info which breaks _Unwind_FindEnclosingFunction
            gccArgs.add("-Xlinker");
            gccArgs.add("-no_compact_unwind");
            
            gccArgs.add("-arch");            
            gccArgs.add(arch.toString());            
        }
        
        exec(gccPath, "-o", outFile, "-g", gccOpts, gccArgs, configS, libFiles, libArgs);
    }
    
    private void stripArchive(File input, File output) throws IOException {
        
        if (!clean && output.exists() && output.lastModified() >= input.lastModified()) {
            if (verbose) {
                stdout.format("Not stripping unchanged archive file '%s'\n", input);
            }
            return;
        }
        
        ZipFile archive = null;
        try {
            archive = new ZipFile(input);
        
            File strippedFile = output;
            if (verbose) {
                stdout.format("Creating stripped archive file '%s'\n", strippedFile);
            }
            
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
    
    @SuppressWarnings("unchecked")
    private void createArchive(File dir, File output, boolean skipClassFiles) throws IOException {
        if (verbose) {
            stdout.format("Creating archive file '%s' from files in directory '%s'\n", output, dir);
        }
        
        output.getParentFile().mkdirs();
        
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
            
            for (File f : (Collection<File>) FileUtils.listFiles(dir, null, true)) {
                ZipEntry newEntry = new ZipEntry(f.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1).replace(File.separatorChar, '/'));
                newEntry.setTime(f.lastModified());
                out.putNextEntry(newEntry);
                InputStream in = null;
                try {
                    if (!skipClassFiles || !f.getName().toLowerCase().endsWith(".class")) {
                        in = new BufferedInputStream(new FileInputStream(f));
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
        
        if (verbose) {
            stdout.println(commandLine.toString());
        }
        
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
    
    private void run(String[] args) {
                
        try {
            int i = 0;
            while (i < args.length) {
                if ("-cp".equals(args[i]) || "-classpath".equals(args[i])) {
                    classpath = args[++i];
                } else if ("-bcp".equals(args[i]) || "-bootcp".equals(args[i]) || "-bootclasspath".equals(args[i])) {
                    bootclasspath = args[++i];
                } else if ("-jar".equals(args[i])) {
                    jarFile = new File(args[++i]);
                } else if ("-o".equals(args[i])) {
                    target = args[++i];
                } else if ("-d".equals(args[i])) {
                    output = new File(args[++i]);
                } else if ("-cache".equals(args[i])) {
                    cache = new File(args[++i]);
                } else if ("-home".equals(args[i])) {
                    home = new File(args[++i]);
                } else if ("-run".equals(args[i])) {
                    run = true;
                } else if ("-verbose".equals(args[i])) {
                    verbose = true;
                } else if ("-debug".equals(args[i])) {
                    debug = true;
                } else if ("-nort".equals(args[i])) {
                    nort = true;
                } else if ("-clean".equals(args[i])) {
                    clean = true;
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-gcc-bin".equals(args[i])) {
                    gccBin = new File(args[++i]);
                } else if ("-gcc-opt".equals(args[i])) {
                    gccOpts.add(args[++i]);
                } else if ("-ar-bin".equals(args[i])) {
                    arBin = new File(args[++i]);
                } else if ("-llvm-bin-dir".equals(args[i])) {
                    llvmBinDir = new File(args[++i]);
                } else if ("-llc-opt".equals(args[i])) {
                    llcOpts.add(args[++i]);
                } else if ("-opt-opt".equals(args[i])) {
                    optOpts.add(args[++i]);
                } else if ("-os".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        os = OS.valueOf(s);
                    }
                } else if ("-arch".equals(args[i])) {
                    String s = args[++i];
                    if (!"auto".equals(s)) {
                        arch = Arch.valueOf(s);
                    }
                } else if ("-cpu".equals(args[i])) {
                    cpu = args[++i];
                } else if (args[i].startsWith("-D")) {
                } else if (args[i].startsWith("-X")) {
                } else if (args[i].startsWith("-")) {
                    throw new IllegalArgumentException("Unrecognized option: " + args[i]);
                } else {
                    mainClass = args[i++];
                    break;
                }
                i++;
            }
            
            while (i < args.length) {
                runArgs.add(args[i++]);
            }
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
            run();
        } catch (Throwable t) {
            String message = t.getMessage();
            if (verbose) {
                t.printStackTrace();
            }
            printUsageAndExit(message);
        }
    }

//    private List<File> processPathObjectFiles(List<ClasspathEntry> pofs, File dir) throws IOException {
//        List<File> jarNames = new ArrayList<String>();
//        int count = 0;
//        for (ClasspathEntry pof : pofs) {
//            String jarName = null;
//            if (pof.getPath() instanceof ZipFilePath) {
//                ZipFilePath path = (ZipFilePath) pof.getPath();
//                jarName = path.getFile().getName();
//                stripArchive(path.getFile(), new File(dir, jarName));
//            } else {
//                DirectoryPath path = (DirectoryPath) pof.getPath();
//                jarName = "classes" + (count++) + ".jar";
//                createArchive(path.getDir(), new File(dir, jarName), true);
//            }
//            jarNames.add(jarName);
//            
//            File outFile = new File(dir, jarName + ".so");
//            
//            if (verbose) {
//                stdout.format("Building dynamic library '%s'\n", outFile);
//            }
//            
//            String gccPath = "gcc";
//            if (gccBin != null) {
//                gccPath = gccBin.getAbsolutePath();
//            }
//            
//            List<String> ldArgs = new ArrayList<String>();
//            for (File f : libDirs) {
//                ldArgs.add("-L");
//                ldArgs.add(f.getAbsolutePath());
//            }
//            ldArgs.add("-L");
//            ldArgs.add(new File(home, "lib").getAbsolutePath());
//
//            List<File> objectFileArgs = new ArrayList<File>();
//            List<String> libArgs = new ArrayList<String>();
//            for (File f : pof.getObjectFiles()) {
//                if (f.getName().endsWith(".a")) {
//                    ldArgs.add("-L");
//                    ldArgs.add(f.getParentFile().getAbsolutePath());
//                    libArgs.add("-l:" + f.getName());
//                } else {
//                    objectFileArgs.add(f);
//                }
//            }
//            
//            exec(gccPath, "-o", outFile, "-g", "-shared", "-Wl,-soname," + outFile.getName(), 
//                    "-Wl,--version-script", symbolsMapFile, 
//                    gccOpts, ldArgs, objectFileArgs, "-lnullvm", "-lm",
//                    "-Wl,--whole-archive", libArgs, "-Wl,--no-whole-archive");
//        }
//        
//        return jarNames;
//    }
    
    public void run() throws IOException {
        
        if (jarFile != null) {
            //mainClass = readMainClassFromManifest(jarFile);
            classPathFiles.add(jarFile);
        }
        
        if (output == null) {
            throw new IllegalArgumentException("No output dir specified");
        }
        if (classpath == null) {
            throw new IllegalArgumentException("No classpath specified");
        }
        
        if (target == null && mainClass == null) {
            throw new IllegalArgumentException("No target and no main class specified");
        }

        if (os == null) {
            os = detectOS();
            if (verbose) {
                System.out.println("Autodetected OS: " + os);
            }
        }
        
        if (arch == null) {
            arch = detectArch();
            if (verbose) {
                System.out.println("Autodetected arch: " + arch);
            }
        }
        
        if (verbose) {
            if (mainClass != null) {
                stdout.println("Using main class: " + mainClass);
            }
            stdout.println("Run arguments: " + runArgs);
        }
        
        homeLib = new File(home, "lib");
        homeLibOsArch = new File(new File(homeLib, os.toString()), arch.toString());
        
        cache.mkdirs();
        llvmCache = new File(cache, "llvm");
        llvmCache.mkdirs();
        objectCache = new File(cache, "object");
        objectCache.mkdirs();

        if (target == null) {
            target = mainClass;
        }
        
        if (!nort) {
            bootClassPathFiles.add(new File(homeLib, "nullvm-rt.jar"));
        }

        if (bootclasspath != null) {
            for (String p : bootclasspath.split(File.pathSeparator)) {
                bootClassPathFiles.add(new File(p));
            }
        }
        if (classpath != null) {
            for (String p : classpath.split(File.pathSeparator)) {
                classPathFiles.add(new File(p));
            }
        }
        
        /*
         * Copy files to tmp directory
         */
        tmpFile = File.createTempFile("nullvm", ".tmp");
        tmpFile.delete();
        tmpFile.mkdirs();

        Clazzes clazzes = new Clazzes(bootClassPathFiles, classPathFiles);
        List<ClasspathEntry> classpathEntries = new ArrayList<ClasspathEntry>();
        for (Path path : clazzes.getBootclasspathPaths()) {
            classpathEntries.add(createClasspathEntry(path, true));
        }
        for (Path path : clazzes.getClasspathPaths()) {
            classpathEntries.add(createClasspathEntry(path, false));
        }

        SootClassCompiler.init(clazzes, getChangedClasses(classpathEntries));
        
        
        output.mkdirs();
        build(classpathEntries);
        
        if (run) {
            // Run in place
            List<String> bootclasspathContents = new ArrayList<String>();
            List<String> classpathContents = new ArrayList<String>();
            for (ClasspathEntry entry : classpathEntries) {
                if (entry.isInBootClasspath()) {
                    bootclasspathContents.add(entry.getPath().getFile().getAbsolutePath());
                } else {
                    classpathContents.add(entry.getPath().getFile().getAbsolutePath());
                }
            }
            FileUtils.writeStringToFile(new File(output, "bootclasspath"), join(bootclasspathContents, "\r\n"), "UTF-8");
            FileUtils.writeStringToFile(new File(output, "classpath"), join(classpathContents, "\r\n"), "UTF-8");

            runTarget();
        } else {
            
            for (File f : homeLibOsArch.listFiles()) {
                if (f.getName().matches(".*\\.(so|dylib)(\\.1)?")) {
                    FileUtils.copyFileToDirectory(f, output);
                }
            }
            
            File libBoot = new File(new File(output, "lib"), "boot");
            libBoot.mkdirs();
            File libMain = new File(new File(output, "lib"), "main");
            libMain.mkdirs();

            List<String> bootclasspathContents = new ArrayList<String>();
            List<String> classpathContents = new ArrayList<String>();
            for (ClasspathEntry entry : classpathEntries) {
                File dest = null;
                if (entry.isInBootClasspath()) {
                    dest = new File(libBoot, entry.getArchive().getName());
                    bootclasspathContents.add("lib/boot/" + entry.getArchive().getName());
                } else {
                    dest = new File(libMain, entry.getArchive().getName());
                    classpathContents.add("lib/main/" + entry.getArchive().getName());
                }
                stripArchive(entry.getArchive(), dest);
            }
            
            FileUtils.writeStringToFile(new File(output, "bootclasspath"), join(bootclasspathContents, "\r\n"), "UTF-8");
            FileUtils.writeStringToFile(new File(output, "classpath"), join(classpathContents, "\r\n"), "UTF-8");
        }        
    }

    @SuppressWarnings("rawtypes")
    private String getHost() throws IOException {
        if (this.host != null) {
            return this.host;
        }
        String llcPath = "llc";
        if (llvmBinDir != null) {
            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(baos);
        Executor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        Map env = EnvironmentUtils.getProcEnvironment();
        executor.setExitValues(new int[] {0, 1});
        CommandLine command = CommandLine.parse(llcPath).addArgument("--version");
        executor.execute(command, env);
        String output = new String(baos.toByteArray());
        Matcher m = Pattern.compile("(?m)Host:\\s*(.*)$").matcher(output);
        if (m.find()) {
            this.host = m.group(1).trim();
            return this.host;
        }
        throw new IllegalArgumentException("Failed to get Host string using command: " + command);
    }

    private OS detectOS() throws IOException {
        String host = getHost();
        if (host.contains("linux")) {
            return OS.linux;
        }
        if (host.contains("darwin")) {
            return OS.darwin;
        }
        throw new IllegalArgumentException("Unrecognized OS in Host string: " + host);
    }
    
    private Arch detectArch() throws IOException {
        String host = getHost();
        if (host.matches("^(x86.64|amd64).*")) {
            return Arch.x86_64;
        }
        if (host.matches("^(x86|i\\d86).*")) {
            return Arch.i386;
        }
        if (host.matches("^arm.*")) {
            return Arch.arm;
        }
        throw new IllegalArgumentException("Unrecognized arch in Host string: " + host);
    }
    
    @SuppressWarnings({ "unchecked" })
    private void runTarget() throws IOException {
        Map<String, String> env = new HashMap<String, String>();
        env.putAll(EnvironmentUtils.getProcEnvironment());
        String ldLibraryPath = env.get("LD_LIBRARY_PATH");
        ldLibraryPath = ldLibraryPath == null ? "" : ":" + ldLibraryPath;
        if (os == OS.linux) {
            env.put("LD_LIBRARY_PATH", homeLibOsArch.getAbsolutePath() + ldLibraryPath);
        }
        if (os == OS.darwin) {
            env.put("DYLD_LIBRARY_PATH", homeLibOsArch.getAbsolutePath() + ldLibraryPath);
        }
        try {
            execWithEnv(output, env, new File(output, target).getAbsolutePath());
        } catch (ExecuteException e) {
            System.exit(e.getExitValue());
        }
    }
    
    private ClasspathEntry createClasspathEntry(Path path, boolean inBootClasspath) {
        if (path instanceof ZipFilePath) {
            return new ZipFilePathClasspathEntry((ZipFilePath) path, inBootClasspath);
        }
        return new DirectoryPathClasspathEntry((DirectoryPath) path, inBootClasspath);
    }
    
    public void setOutput(File output) {
        this.output = output;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public void setLlvmBinDir(File llvmBinDir) {
        this.llvmBinDir = llvmBinDir;
    }

    public void setLlcOpts(List<String> llcOpts) {
        this.llcOpts = llcOpts;
    }

    public void setOptOpts(List<String> optOpts) {
        this.optOpts = optOpts;
    }

    public void setGccOpts(List<String> gccOpts) {
        this.gccOpts = gccOpts;
    }

    public void setGccBinDir(File gccBinDir) {
        this.gccBin = gccBinDir;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setGccBin(File gccBin) {
        this.gccBin = gccBin;
    }

    public void setStdout(PrintStream stdout) {
        this.stdout = stdout;
    }

    void setTarget(String target) {
        this.target = target;
    }

    public void setOS(OS os) {
        this.os = os;
    }
    
    void setArch(Arch arch) {
        this.arch = arch;
    }

    void setCpu(String cpu) {
        this.cpu = cpu;
    }

    void setRun(boolean run) {
        this.run = run;
    }

    public static void main(String[] args) throws IOException {
        new Main().run(args);
    }

    private static File makeFileRelativeTo(File dir, File f) {
        if (f.getParentFile() == null) {
            return dir;
        }
        return new File(makeFileRelativeTo(dir, f.getParentFile()), f.getName());
    }

    private static File rebase(File f, File oldBase, File newBase) {
        try {
            return new File(newBase, f.getCanonicalPath().substring(oldBase.getCanonicalPath().length()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File changeExt(File f, String newExt) {
        String name = f.getName();
        int index = name.indexOf('.');
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
    
    interface ClasspathEntry {
        Path getPath();
        File getBcLibrary();
        File getStaticLibrary();
        File getLlvmCacheDir();
        File getObjectCacheDir();
        File getArchive();
        boolean hasChangedAfter(long timestamp);
        boolean isInBootClasspath();
    }
    
    class ZipFilePathClasspathEntry implements ClasspathEntry {
        private final ZipFilePath path;
        private final boolean inBootClasspath;
        private File llvmCacheDir = null;
        private File objectCacheDir = null;
        
        public ZipFilePathClasspathEntry(ZipFilePath path, boolean inBootClasspath) {
            this.path = path;
            this.inBootClasspath = inBootClasspath;
        }
        public Path getPath() {
            return path;
        }
        public File getBcLibrary() {
            return new File(getLlvmCacheDir().getParentFile(), path.getFile().getName() + ".bc");
        }
        public File getStaticLibrary() {
            return new File(getObjectCacheDir().getParentFile(), "lib" + path.getFile().getName() + ".a");
        }
        public File getLlvmCacheDir() {
            if (llvmCacheDir == null) {
                try {
                    llvmCacheDir = new File(makeFileRelativeTo(llvmCache, path.getFile().getCanonicalFile().getParentFile()), 
                            path.getFile().getName() + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return llvmCacheDir;
        }
        public File getObjectCacheDir() {
            if (objectCacheDir == null) {
                try {
                    File base = new File(new File(new File(objectCache, debug ? "debug" : "release"), arch.toString()), cpu == null ? "default" : cpu);
                    objectCacheDir = new File(makeFileRelativeTo(base, path.getFile().getCanonicalFile().getParentFile()), 
                            path.getFile().getName() + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return objectCacheDir;
        }
        public File getArchive() {
            return path.getFile();
        }
        public boolean hasChangedAfter(long timestamp) {
            return path.getFile().lastModified() > timestamp;
        }
        public boolean isInBootClasspath() {
            return inBootClasspath;
        }
        @Override
        public String toString() {
            return path.getFile().getAbsolutePath();
        }
    }
    
    class DirectoryPathClasspathEntry implements ClasspathEntry {
        private final DirectoryPath path;
        private final boolean inBootClasspath;
        private File archive = null;
        private File llvmCacheDir = null;
        private File objectCacheDir = null;
        private final String jarName;
        
        public DirectoryPathClasspathEntry(DirectoryPath path, boolean inBootClasspath) {
            this.path = path;
            this.inBootClasspath = inBootClasspath;
            jarName = "classes" + path.getIndex() + ".jar";
        }
        public Path getPath() {
            return path;
        }
        public File getBcLibrary() {
            return new File(getLlvmCacheDir().getParentFile(), jarName + ".bc");
        }
        public File getStaticLibrary() {
            return new File(getObjectCacheDir().getParentFile(), "lib" + jarName + ".a");
        }
        public File getLlvmCacheDir() {
            if (llvmCacheDir == null) {
                try {
                    llvmCacheDir = new File(makeFileRelativeTo(llvmCache, path.getFile().getCanonicalFile()), jarName + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return llvmCacheDir;
        }        
        public File getObjectCacheDir() {
            if (objectCacheDir == null) {
                try {
                    File base = new File(new File(new File(objectCache, debug ? "debug" : "release"), arch.toString()), cpu == null ? "default" : cpu);
                    objectCacheDir = new File(makeFileRelativeTo(base, 
                            path.getFile().getCanonicalFile()), jarName + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return objectCacheDir;
        }              
        public File getArchive() {
            if (archive == null) {
                File a = new File(getLlvmCacheDir().getParentFile(), jarName);
                if (!a.exists() || hasChangedAfter(a.lastModified())) {
                    try {
                        createArchive(path.getFile(), a, false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                archive = a;
            }
            return archive;
        }
        private boolean hasChangedAfter(File dir, long timestamp) {
            for (File f : dir.listFiles()) {
                if (f.isFile()) {
                    if (f.lastModified() > timestamp) {
                        return true;
                    }
                } else {
                    if (hasChangedAfter(f, timestamp)) {
                        return true;
                    }
                }
            }
            return false;
        }
        public boolean hasChangedAfter(long timestamp) {
            return hasChangedAfter(path.getFile(), timestamp);
        }
        public boolean isInBootClasspath() {
            return inBootClasspath;
        }
        @Override
        public String toString() {
            return path.getFile().getAbsolutePath();
        }
    }
}
