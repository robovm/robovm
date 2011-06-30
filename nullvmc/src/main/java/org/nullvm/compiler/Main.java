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
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

/**
 *
 * @version $Id$
 */
public class Main {

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
    private String arch = null;
    private String cpu = null;
    private boolean debug = false;
    private boolean nort = false;
    private boolean verbose = false;
    private File jarFile;
    private List<File> includeDirs = new ArrayList<File>();
    private List<File> libDirs = new ArrayList<File>();
    private boolean run = false;
    private String mainClass;
    private List<String> runArgs = new ArrayList<String>();
    
    private File mainCFile;
    private File symbolsMapFile;
    private List<File> bootClassPathFiles = new ArrayList<File>();
    private List<File> classPathFiles = new ArrayList<File>();
    private PrintStream stdout = System.out;

    private File tmpFile;
    
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
        System.err.println("  -gcc-opt <opt>        Option to pass to gcc");
        System.err.println("  -home <dir>           Directory where NullVM runtime has been installed and \n" 
                         + "                        where compiled class files will be cached. Default is \n" 
                         + "                        ~/.nullvm");
        System.err.println("  -jar <path>           Use main class as specified by the manifest in this JAR \n" 
                         + "                        archive.");
        System.err.println("  -llc-opt <opt         Option to pass to llc");
        System.err.println("  -llvm-bin-dir <path>  Path where the LLVM binaries can be found");
        System.err.println("  -o <name>             The name of the target executable or library");
        System.err.println("  -opt-opt <opt>        Option to pass to opt");
        System.err.println("  -arch <name>          The name of the LLVM arch to compile for. Default is \n" 
                         + "                        autodetected using llc.");
        System.err.println("  -cpu <name>           The name of the LLVM cpu to compile for. The LLVM default\n" 
                         + "                        is used by default.");
        System.err.println("  -debug                Generates debug information");
        System.err.println("  -nort                 Do not add default nullvm-rt.jar to bootclasspath");
        System.err.println("  -verbose              Output messages about what the compiler is doing");
        System.err.println("  -help, -?             Display this information");
        
        System.exit(errorMessage != null ? 1 : 0);
    }

    private List<Clazz> getChangedClasses(List<ClasspathEntry> bootcp, List<ClasspathEntry> cp) {
        List<ClasspathEntry> all = new ArrayList<ClasspathEntry>();
        all.addAll(bootcp);
        all.addAll(cp);
        
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
    
    private File processClassFile(ClasspathEntry entry, Clazz clazz) throws IOException {
        OutputStream out = null;
        File outFile = null;
        try {
            String className = clazz.getInternalName();
            outFile = new File(entry.getLlvmCacheDir(), className.replace('/', File.separatorChar) + "" + ".class.ll");
            outFile.getParentFile().mkdirs();
            if (!clean && outFile.exists() && outFile.lastModified() >= clazz.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged class file: " + clazz.getFileName());
                }
                return processIrFile(entry, outFile);
            }
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
        return processIrFile(entry, outFile);
    }

    private List<File> processClassFiles(ClasspathEntry entry) throws IOException {
        List<File> objectFiles = new ArrayList<File>();
        for (Clazz clazz : entry.getPath().list()) {
            objectFiles.add(processClassFile(entry, clazz));
        }
        return objectFiles;
    }
    
    private void processClasspathEntry(ClasspathEntry entry) throws IOException {
        buildDynamicLibrary(entry);
    }
    
    private File processIrFile(ClasspathEntry entry, File f) throws IOException {
        File outFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".bc");
        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM IR file: " + f);
            }
            return processBcFile(entry, outFile);
        }
        if (verbose) {
            stdout.format("Compiling LLVM IR file '%s' to LLVM bitcode file '%s'\n", f, outFile);
        }
        
        String llvmAsPath = "llvm-as";
        if (llvmBinDir != null) {
            llvmAsPath = new File(llvmBinDir, "llvm-as").getAbsolutePath();
        }
        
        exec(llvmAsPath, "-o=" + outFile.toString(), f);
        return processBcFile(entry, outFile);
    }
    
    private File processBcFile(ClasspathEntry entry, File f) throws IOException {
        File outFile = changeExt(rebase(f, entry.getLlvmCacheDir(), entry.getObjectCacheDir()), "s");
        
        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM bitcode file: " + f);
            }
            return processGccFile(entry, outFile);
        }
        if (verbose) {
            stdout.format("Compiling LLVM bitcode file '%s' to assembler file '%s'\n", f, outFile);
        }
            
        outFile.getParentFile().mkdirs();
        
        String llcPath = "llc";
        String optPath = "opt";
        if (llvmBinDir != null) {
            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
        }
        
        List<String> opts = null;
        
        File outOptedFile = changeExt(f, (debug ? "debug" : "release") + ".opted.bc");
        if (clean || !outOptedFile.exists() || outOptedFile.lastModified() < f.lastModified()) {
            opts = new ArrayList<String>(optOpts);
            opts.add("-mem2reg");
            opts.add("-always-inline");
            if (!debug) {
                // -std-compile-opts minus -inline and -tailcallelim 
                opts.addAll(Arrays.asList(
                       ("-preverify -domtree -verify -lowersetjmp -globalopt -ipsccp " 
                      + "-deadargelim -instcombine -simplifycfg -basiccg -prune-eh " 
                      + "-functionattrs -argpromotion -domtree -domfrontier " 
                      + "-scalarrepl -simplify-libcalls -instcombine -lazy-value-info " 
                      + "-jump-threading -simplifycfg -instcombine " 
                      + "-simplifycfg -reassociate -domtree -loops -loopsimplify " 
                      + "-lcssa -loop-rotate -licm -lcssa -loop-unswitch -instcombine " 
                      + "-scalar-evolution -loopsimplify -lcssa -iv-users -indvars " 
                      + "-loop-deletion -loop-unroll -instcombine -memdep -gvn -memdep " 
                      + "-memcpyopt -sccp -instcombine -lazy-value-info -jump-threading " 
                      + "-correlated-propagation -domtree -memdep -dse -adce -simplifycfg " 
                      + "-strip-dead-prototypes -print-used-types -deadtypeelim " 
                      + "-globaldce -constmerge -preverify -domtree -verify").split(" ")));
            }
        
            exec(optPath, opts, "-o=" + outOptedFile.toString(), f);
        }
        
        opts = new ArrayList<String>(llcOpts);
        opts.add("-relocation-model=pic");
        opts.add("-march=" + arch);
        if (cpu != null) {
            opts.add("-mcpu=" + cpu);
        }

        exec(llcPath, opts, "-o=" + outFile.toString(), outOptedFile);
            
        return processGccFile(entry, outFile);
    }
    
    private File processGccFile(ClasspathEntry entry, File f) throws IOException {
//        String hash = f.getParentFile().getName();
//        File outFile = new File(f.getParentFile().getParentFile().getParentFile(), f.getName().substring(0, f.getName().lastIndexOf('.')) + "." + hash + ".o");
        File outFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().lastIndexOf('.')) + ".o");
        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged GCC input file: " + f);
            }
            return outFile;
        }
        if (verbose) {
            stdout.format("Compiling '%s' to '%s'\n", f, outFile);
        }

        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }
        
        List<String> opts = new ArrayList<String>(gccOpts);
        if (debug) {
            opts.add("-g");
        }
        
        exec(gccPath, "-c", "-o", outFile, gccOpts, f);
        
        return outFile;
    }
    
    private File buildDynamicLibrary(ClasspathEntry entry) throws IOException {
        File outFile = entry.getDynamicLibrary();

        if (!clean && outFile.exists() && !entry.hasChangedAfter(outFile.lastModified())) {
            if (verbose) {
                stdout.println("Skipping unchanged classpath entry: " + entry);
            }
            return outFile;
        }
        
        if (verbose) {
            stdout.format("Building dynamic library '%s'\n", outFile);
        }
            
        List<File> files = processClassFiles(entry);

        outFile.getParentFile().mkdirs();
        
        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }
        
        List<String> ldArgs = new ArrayList<String>();
        for (File f : libDirs) {
            ldArgs.add("-L");
            ldArgs.add(f.getAbsolutePath());
        }
        ldArgs.add("-L");
        ldArgs.add(new File(home, "lib").getAbsolutePath());

        exec(gccPath, files, "-o", outFile, "-g", "-shared", "-Wl,-soname," + outFile.getName(), 
                "-Wl,--version-script", symbolsMapFile, 
                gccOpts, ldArgs, "-lnullvm", "-lm");
        
        return outFile;
    }
    
    private void buildExecutable() throws IOException {
        File outFile = new File(output, target);
        
        if (verbose) {
            stdout.format("Building executable '%s'\n", outFile);
        }
        
        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }
        
        List<String> gccArgs = new ArrayList<String>();
        for (File f : includeDirs) {
            gccArgs.add("-I");
            gccArgs.add(f.getAbsolutePath());
        }
        gccArgs.add("-I");
        gccArgs.add(new File(home, "include").getAbsolutePath());
        gccArgs.add("-I");
        gccArgs.add(new File(new File(home, "gc"), "include").getAbsolutePath());
        gccArgs.add("-I");
        gccArgs.add(new File(new File(home, "include"), "harmony").getAbsolutePath());
        gccArgs.add("-L");
        gccArgs.add(new File(home, "lib").getAbsolutePath());
        gccArgs.add("-L");
        gccArgs.add(new File(new File(home, "gc"), "lib").getAbsolutePath());
        
//        File mainObjectFile = new File(mainCFile.getParentFile(), "main.o");
        exec(gccPath, "-o", outFile, 
                mainClass != null ? "-DNULLVM_MAIN_CLASS=" + mainClass.replace('.', '/') : "-DNULLVM_NO_MAIN_CLASS",
                "-DLINUX", "-DLINUX_X86_64", "-DHYX86_64", "-DIPv6_FUNCTION_SUPPORT", "-DHYPORT_LIBRARY_DEFINE",
                "-g", gccOpts, gccArgs, "-lnullvm", "-lm", "-ldl", "-lpthread", mainCFile);
//        files.add(mainObjectFile);
//
//        List<String> ldArgs = new ArrayList<String>();
//        for (File f : libDirs) {
//            ldArgs.add("-L");
//            ldArgs.add(f.getAbsolutePath());
//        }
//        ldArgs.add("-L");
//        ldArgs.add(new File(home, "lib").getAbsolutePath());
//        ldArgs.add("-L");
//        ldArgs.add(new File(new File(home, "gc"), "lib").getAbsolutePath());
//        
////        String ldPath = "ld";
////        if (ldBin != null) {
////            ldPath = ldBin.getAbsolutePath();
////        }
//        
//        List<File> objectFileArgs = new ArrayList<File>();
//        List<String> libArgs = new ArrayList<String>();
//        for (File f : files) {
//            if (f.getName().endsWith(".a")) {
//                ldArgs.add("-L");
//                ldArgs.add(f.getParentFile().getAbsolutePath());                
//                libArgs.add("-l:" + f.getName());
//            } else {
//                objectFileArgs.add(f);
//            }
//        }
//        
//        exec(gccPath, "-o", outFile, "-g", "-Wl,--version-script", symbolsMapFile, 
//                gccOpts, "-rdynamic", ldArgs, objectFileArgs, "-lnullvm", "-lm", "-ldl", "-lpthread",
//                "-Wl,--whole-archive", libArgs, "-Wl,--no-whole-archive", "-Wl,-Bstatic", "-lgc", "-Wl,-Bdynamic");
//
////        exec(ldPath, "-o", outFile, "--version-script", symbolsMapFile, 
////                ldOpts, ldArgs, objectFileArgs, "-lnullvm", "-lm", "-ldl", "-lpthread",
////                "--whole-archive", libArgs, "--no-whole-archive", "-Bstatic", "-lgc", "-Bdynamic");
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
                } else if ("-llvm-bin-dir".equals(args[i])) {
                    llvmBinDir = new File(args[++i]);
                } else if ("-llc-opt".equals(args[i])) {
                    llcOpts.add(args[++i]);
                } else if ("-opt-opt".equals(args[i])) {
                    optOpts.add(args[++i]);
                } else if ("-arch".equals(args[i])) {
                    arch = args[++i];
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
        
        cache.mkdirs();
        llvmCache = new File(cache, "llvm");
        llvmCache.mkdirs();
        objectCache = new File(cache, "object");
        objectCache.mkdirs();

        if (target == null) {
            target = mainClass;
        }
        
        if (!nort) {
            bootClassPathFiles.add(new File(home, "lib/nullvm-rt.jar"));
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
        mainCFile = new File(tmpFile, "main.c");
        FileUtils.copyURLToFile(getClass().getResource("/main.c"), mainCFile);
        symbolsMapFile = new File(tmpFile, "symbols.map");
        FileUtils.copyURLToFile(getClass().getResource("/symbols.map"), symbolsMapFile);

        Clazzes clazzes = new Clazzes(bootClassPathFiles, classPathFiles);
        List<ClasspathEntry> bootclasspathObjects = new ArrayList<ClasspathEntry>();
        List<ClasspathEntry> classpathObjects = new ArrayList<ClasspathEntry>();
        for (Path path : clazzes.getBootclasspathPaths()) {
            bootclasspathObjects.add(createClasspathEntry(path));
        }
        for (Path path : clazzes.getClasspathPaths()) {
            classpathObjects.add(createClasspathEntry(path));
        }

        SootClassCompiler.init(clazzes, getChangedClasses(bootclasspathObjects, classpathObjects));
        
        for (ClasspathEntry entry : bootclasspathObjects) {
            processClasspathEntry(entry);
        }
        for (ClasspathEntry entry : classpathObjects) {
            processClasspathEntry(entry);
        }
        
        output.mkdirs();
        
        buildExecutable();
        
        if (run) {
            // Run in place
            runTarget(bootclasspathObjects, classpathObjects);
        } else {
            FileUtils.copyFileToDirectory(new File(new File(home, "lib"), "libnullvm.so"), output);
            FileUtils.copyFileToDirectory(new File(new File(home, "lib"), "libnullvm-rt.so"), output);
            FileUtils.copyFileToDirectory(new File(new File(home, "lib"), "libgc.so.1"), output);
            
            File libBoot = new File(new File(output, "lib"), "boot");
            libBoot.mkdirs();
            File libMain = new File(new File(output, "lib"), "main");
            libMain.mkdirs();
            
//            String bootDirString = libBoot.getAbsolutePath().substring(output.getAbsolutePath().length() + 1);
//            String mainDirString = libMain.getAbsolutePath().substring(output.getAbsolutePath().length() + 1);
            
            writeClasspathEntries(bootclasspathObjects, output, libBoot, new File(output, "bootclasspath"));
            writeClasspathEntries(classpathObjects, output, libMain, new File(output, "classpath"));
            
//            Properties bootEntriesProps = new Properties();
//            for (ClasspathEntry entry : bootclasspathObjects) {
//                bootEntriesProps.setProperty(bootDirString + File.separator + entry.getArchive().getName(), 
//                        bootDirString + File.separator + entry.getDynamicLibrary().getName());
//                stripArchive(entry.getArchive(), new File(libBoot, entry.getArchive().getName()));
//                FileUtils.copyFileToDirectory(entry.getDynamicLibrary(), libBoot);
//            }
//            Properties mainEntriesProps = new Properties();
//            for (ClasspathEntry entry : classpathObjects) {
//                mainEntriesProps.setProperty(mainDirString + File.separator + entry.getArchive().getName(), 
//                        mainDirString + File.separator + entry.getDynamicLibrary().getName());
//                stripArchive(entry.getArchive(), new File(libMain, entry.getArchive().getName()));
//                FileUtils.copyFileToDirectory(entry.getDynamicLibrary(), libMain);
//            }
//            
//            writeProperties(bootEntriesProps, new File(output, "bootclasspath"));
//            writeProperties(mainEntriesProps, new File(output, "classpath"));
            
//            List<String> bootJars = processPathObjectFiles(bootclasspathObjects, libBoot);
//            List<String> mainJars = processPathObjectFiles(classpathObjects, libMain);
//            
//            FileUtils.writeStringToFile(new File(libBoot, "files"), StringUtils.join(bootJars, ':'), "UTF-8");
//            FileUtils.writeStringToFile(new File(libMain, "files"), StringUtils.join(mainJars, ':'), "UTF-8");
        }        
    }

    private void writeClasspathEntries(List<ClasspathEntry> entries, File file) throws IOException {
        writeClasspathEntries(entries, null, null, file);
    }
    private void writeClasspathEntries(List<ClasspathEntry> entries, File basePath, File installPath, File file) throws IOException {
        // The result is similar to a properties file but the order of entries is important so we cannot use Properties.store()
        // TODO: Escape = characters
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            for (ClasspathEntry entry: entries) {
                String left = entry.getArchive().getCanonicalPath();
                String right = entry.getDynamicLibrary().getCanonicalPath();
                if (installPath != null) {
                    String dir = installPath.getAbsolutePath().substring(basePath.getAbsolutePath().length() + 1);
                    stripArchive(entry.getArchive(), new File(installPath, entry.getArchive().getName()));
                    FileUtils.copyFileToDirectory(entry.getDynamicLibrary(), installPath);
                    left = dir + File.separator + entry.getArchive().getName();
                    right = dir + File.separator + entry.getDynamicLibrary().getName();
                }
                writer.write(left);
                writer.write('=');
                writer.write(right);
                writer.write('\n');
            }
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
    
    @SuppressWarnings("rawtypes")
    private String detectArch() throws IOException {
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
            String s = m.group(1).trim();
            if (s.matches("^(x86.64|amd64).*")) {
                return "x86-64";
            }
            if (s.matches("^(x86|i\\d86).*")) {
                return "x86";
            }
            if (s.matches("^arm.*")) {
                return "arm";
            }
            throw new IllegalArgumentException("Unrecognized Host string: " + s);
        }
        throw new IllegalArgumentException("Failed to autodetect arch using command: " + command);
    }
    
    @SuppressWarnings({ "unchecked" })
    private void runTarget(List<ClasspathEntry> bootEntries, List<ClasspathEntry> mainEntries) throws IOException {
        Properties bootEntriesProps = new Properties();
        for (ClasspathEntry entry : bootEntries) {
            bootEntriesProps.setProperty(entry.getArchive().getCanonicalPath(), 
                    entry.getDynamicLibrary().getCanonicalPath());
        }
        Properties mainEntriesProps = new Properties();
        for (ClasspathEntry entry : mainEntries) {
            mainEntriesProps.setProperty(entry.getArchive().getCanonicalPath(), 
                    entry.getDynamicLibrary().getCanonicalPath());
        }
        
        writeClasspathEntries(bootEntries, new File(output, "bootclasspath"));
        writeClasspathEntries(mainEntries, new File(output, "classpath"));
        
        Map<String, String> env = new HashMap<String, String>();
        env.putAll(EnvironmentUtils.getProcEnvironment());
        String ldLibraryPath = env.get("LD_LIBRARY_PATH");
        ldLibraryPath = ldLibraryPath == null ? "" : ":" + ldLibraryPath;
        env.put("LD_LIBRARY_PATH", new File(home, "lib").getAbsolutePath() + ldLibraryPath);
        try {
            execWithEnv(output, env, new File(output, target).getAbsolutePath());
        } catch (ExecuteException e) {
            System.exit(e.getExitValue());
        }
    }
    
    private ClasspathEntry createClasspathEntry(Path path) {
        if (path instanceof ZipFilePath) {
            return new ZipFilePathClasspathEntry((ZipFilePath) path);
        }
        return new DirectoryPathClasspathEntry((DirectoryPath) path);
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

    void setArch(String arch) {
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
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return new File(f.getParent(), name + "." + newExt);
        }
        return new File(f.getParent(), name.substring(0, index) + "." + newExt);
    }
    
    interface ClasspathEntry {
        Path getPath();
        File getDynamicLibrary();
        File getLlvmCacheDir();
        File getObjectCacheDir();
        File getArchive();
        boolean hasChangedAfter(long timestamp);
    }
    
    class ZipFilePathClasspathEntry implements ClasspathEntry {
        private final ZipFilePath path;
        private File llvmCacheDir = null;
        private File objectCacheDir = null;
        
        public ZipFilePathClasspathEntry(ZipFilePath path) {
            this.path = path;
        }
        public Path getPath() {
            return path;
        }
        public File getDynamicLibrary() {
            return new File(getObjectCacheDir().getParentFile(), "lib" + path.getFile().getName() + ".so");
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
                    File base = new File(new File(new File(objectCache, debug ? "debug" : "release"), arch), cpu == null ? "default" : cpu);
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
        @Override
        public String toString() {
            return path.getFile().getAbsolutePath();
        }
    }
    
    class DirectoryPathClasspathEntry implements ClasspathEntry {
        private final DirectoryPath path;
        private File archive = null;
        private File llvmCacheDir = null;
        private File objectCacheDir = null;
        private final String jarName;
        
        public DirectoryPathClasspathEntry(DirectoryPath path) {
            this.path = path;
            jarName = "classes" + path.getIndex() + ".jar";
        }
        public Path getPath() {
            return path;
        }
        public File getDynamicLibrary() {
            return new File(getObjectCacheDir().getParentFile(), "lib" + jarName + ".so");
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
                    File base = new File(new File(new File(objectCache, debug ? "debug" : "release"), arch), cpu == null ? "default" : cpu);
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
        @Override
        public String toString() {
            return path.getFile().getAbsolutePath();
        }
    }
}
