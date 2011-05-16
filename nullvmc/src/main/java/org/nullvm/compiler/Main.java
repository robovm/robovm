/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.nullvm.compiler.ClassCompiler.VerifyWhen;
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
    private boolean clean = false;
    private File llvmBinDir = null;    
    private List<String> llcOpts = new ArrayList<String>();
    private List<String> optOpts = new ArrayList<String>();
    private List<String> gccOpts = new ArrayList<String>();
    private File gccBin = null;    
    private File arBin = null;
    private List<String> arOpts = new ArrayList<String>();
    private boolean verbose = false;
    private File jarFile;
    private List<File> includeDirs = new ArrayList<File>();
    private List<File> libDirs = new ArrayList<File>();
    private boolean run = false;
    private ClassCompiler.VerifyWhen verify = ClassCompiler.VerifyWhen.SKIP;
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
        System.err.println("Usage: nullvm [-options] class");
        System.err.println("   or  nullvm [-options] -jar jarfile");
        System.err.println("Options:");
        
        System.err.println("  -ar-bin <path>       Path to the ar binary");
        //System.err.println("-bootclasspath <list>   : Classpath used to locate the java.* and javax.* classes. Default is ~/nullvm/lib/nullvm-rt.jar.");
        System.err.println("  -cache <dir>         Directory where cached compiled class files will be placed. Default is ~/.nullvm/cache");
        System.err.println("  -clean               Compile class files even if a compiled version already exists in the cache.");
        System.err.println("  -cp <list>           : separated list of directories, JAR archives, and ZIP archives to search for class files.");
        System.err.println("  -classpath <list>    : separated list of directories, JAR archives, and ZIP archives to search for class files.");
        System.err.println("  -d <dir>             Place the generated executable and other files in <dir>.");
        System.err.println("  -verify:skip         Don't verify classes (the default)");
        System.err.println("  -verify:now          Verify classes and abort compilation if verification fails");
        System.err.println("  -verify:defer        Verify classes but don't abort compilation. java.lang.VerifyError will be thrown when the class is loaded at runtime.");
        System.err.println("  -gcc-bin <path>      Path to the gcc binary");
        System.err.println("  -gcc-opt <opt>       Option to pass to gcc");
        System.err.println("  -help, -?            Display this information");
        System.err.println("  -home <dir>          Directory where NullVM runtime has been installed and where compiled class files will be cached. Default is ~/.nullvm");
        System.err.println("  -jar <path>          Use main class as specified by the manifest in this JAR archive.");
        System.err.println("  -ld-bin <path>       Path to the ld binary");
        System.err.println("  -ld-opt <opt>        Option to pass to ld");
        System.err.println("  -llc-opt <opt        Option to pass to llc");
        System.err.println("  -llvm-bin-dir <path> Path where the LLVM binaries can be found");
        System.err.println("  -o <name>            The name of the target executable or library");
        System.err.println("  -opt-opt <opt>       Option to pass to opt");
        System.err.println("  -verbose             Output messages about what the compiler is doing");
        
        System.exit(errorMessage != null ? 1 : 0);
    }

    private static String toHexString(byte[] b) {
        char[] HEX_CHARS = "0123456789abcdef".toCharArray();
        StringBuffer str = new StringBuffer(b.length * 2);
        for (int i = 0; i <b.length; i++) {
            int z = b[i] & 0xff;
            str.append(HEX_CHARS[z >> 4]);
            str.append(HEX_CHARS[z & 0xf]);
        }
        return str.toString();
    }
    
//    private static String hash(File f) {
//        InputStream in = null;
//        try {
//            in = new BufferedInputStream(new FileInputStream(f));
//            return hash(in);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            IOUtils.closeQuietly(in);
//        }
//    }
    
    private static String hash(InputStream in) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                digest.update(buffer, 0, n);
            }
            return toHexString(digest.digest());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    private String getSimpleClassName(String internalName) {
        int index = internalName.lastIndexOf('/');
        if (index == -1) {
            return internalName;
        }
        return internalName.substring(index + 1);
    }
    
    private List<Clazz> getChangedClasses(List<ClasspathEntry> bootcp, List<ClasspathEntry> cp) {
        List<ClasspathEntry> all = new ArrayList<ClasspathEntry>();
        all.addAll(bootcp);
        all.addAll(cp);
        
        List<Clazz> changed = new ArrayList<Clazz>();
        for (ClasspathEntry entry : all) {
            for (Clazz clazz : entry.getPath().list()) {
                String className = clazz.getInternalName();
                File outFile = new File(entry.getCacheDir(), className.replace('/', File.separatorChar) + "" + ".class.ll");
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
            //byte[] classData = clazz.getBytes();
            String className = clazz.getInternalName();
            //File classDir = new File(new File(classCache, className.replace('/', File.separatorChar)), hash(new ByteArrayInputStream(classData)));
            outFile = new File(entry.getCacheDir(), className.replace('/', File.separatorChar) + "" + ".class.ll");
            outFile.getParentFile().mkdirs();
            if (!clean && outFile.exists() && outFile.lastModified() >= clazz.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged class file: " + clazz.getFileName());
                }
                return processIrFile(outFile);
            }
            if (verbose) {
                stdout.format("Compiling class file '%s' to LLVM IR file '%s'\n", clazz.getFileName(), outFile);
            }
            out = new FileOutputStream(outFile);
//            new SootClassCompiler().setVerifyWhen(verify).compile(clazz, out);
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
        return processIrFile(outFile);
    }

    private List<File> processClassFiles(ClasspathEntry entry) throws IOException {
        List<File> objectFiles = new ArrayList<File>();
        for (Clazz clazz : entry.getPath().list()) {
            objectFiles.add(processClassFile(entry, clazz));
        }
        return objectFiles;
    }
    
    private void processClasspathEntry(ClasspathEntry entry) throws IOException {
        if (buildStaticLibrary(entry)) {
            buildDynamicLibrary(entry);
        }
//            return Collections.singletonList(processArchivedClassFiles((ZipFilePath) entry));
//        } else {
//            return Collections.singletonList(processClassFiles((DirectoryPath) entry));
//        }
    }
    
//    private File processArchivedClassFiles(ZipFilePath path) throws IOException {
//        File archiveFile = path.getFile();
//        return buildDynamicLibrary(archiveFile.getName(), buildStaticLibrary(processClassFilesInPath(path), path));
//    }
//    
//    private File processClassFiles(DirectoryPath path) throws IOException {
//        String baseName = path.getDir().getAbsolutePath().replace(File.separatorChar, '_') + ".jar";
//        return buildDynamicLibrary(baseName, buildStaticLibrary(processClassFilesInPath(path), path));
//    }
//    
    private File processIrFile(File f) throws IOException {
        File outFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".bc");
        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM IR file: " + f);
            }
            return processBcFile(outFile);
        }
        if (verbose) {
            stdout.format("Compiling LLVM IR file '%s' to LLVM bitcode file '%s'\n", f, outFile);
        }
        
        String llvmAsPath = "llvm-as";
        if (llvmBinDir != null) {
            llvmAsPath = new File(llvmBinDir, "llvm-as").getAbsolutePath();
        }
        
        exec(llvmAsPath, "-o=" + outFile.toString(), f);
        return processBcFile(outFile);
    }
    
    private File processBcFile(File f) throws IOException {
        File outFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".s");
        if (!clean && outFile.exists() && outFile.lastModified() >= f.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged LLVM bitcode file: " + f);
            }
            return processGccFile(outFile);
        }
        if (verbose) {
            stdout.format("Compiling LLVM bitcode file '%s' to assembler file '%s'\n", f, outFile);
        }
            
        String llcPath = "llc";
        String optPath = "opt";
        if (llvmBinDir != null) {
            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
        }
        
        File outOptedFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".opted.bc");
        exec(optPath, optOpts, "-mem2reg", "-always-inline", "-o=" + outOptedFile.toString(), f);
        
        exec(llcPath, llcOpts, "-relocation-model=pic", "-o=" + outFile.toString(), outOptedFile);
            
        return processGccFile(outFile);
    }
    
    private File processGccFile(File f) throws IOException {
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
        
        exec(gccPath, "-c", "-o", outFile, "-g", gccOpts, f);
        
        return outFile;
    }
    
    private boolean buildStaticLibrary(ClasspathEntry entry) throws IOException {
        File outFile = entry.getStaticLibrary();
//        if (path instanceof ZipFilePath) {
//            File archiveFile = ((ZipFilePath) path).getFile();
//            lastModified = archiveFile.lastModified();
//            outFile = new File(new File(new File(libCache, archiveFile.getName()), hash(archiveFile)), "lib" + archiveFile.getName() + ".a");
//        } else {
//            File dir = ((DirectoryPath) path).getDir();
//            lastModified = getDirLastModified(dir);
//            outFile = new File(makeFileRelativeTo(libCache, dir), "lib" + dir.getAbsolutePath().replace(File.separatorChar, '_') + ".a");
//        }
        
        if (!clean && outFile.exists() && !entry.hasChangedAfter(outFile.lastModified())) {
            if (verbose) {
                stdout.println("Skipping unchanged classpath entry: " + entry);
            }
            return false;
        }
        
        outFile.getParentFile().mkdirs();
        
        if (verbose) {
            stdout.format("Building library '%s'\n", outFile);
        }
        
        List<File> files = processClassFiles(entry);
        
        String arPath = "ar";
        if (arBin != null) {
            arPath = arBin.getAbsolutePath();
        }
        
        if (outFile.exists()) {
            outFile.delete();
        }
        
        exec(arPath, "rcs", outFile, files);
        
        return true;
    }
    
    private File buildDynamicLibrary(ClasspathEntry entry) throws IOException {
        File libFile = entry.getStaticLibrary();
        File outFile = entry.getDynamicLibrary(); //new File(libFile.getParentFile(), baseName + ".so");
        
        if (!clean && outFile.exists() && outFile.lastModified() >= libFile.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged library: " + libFile);
            }
            return outFile;
        }
        
        if (verbose) {
            stdout.format("Building dynamic library '%s'\n", outFile);
        }
            
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

        List<String> libArgs = new ArrayList<String>();
        ldArgs.add("-L");
        ldArgs.add(libFile.getParentFile().getAbsolutePath());
        libArgs.add("-l:" + libFile.getName());
            
        exec(gccPath, "-o", outFile, "-g", "-shared", "-Wl,-soname," + outFile.getName(), 
                "-Wl,--version-script", symbolsMapFile, 
                gccOpts, ldArgs, "-lnullvm", "-lm",
                "-Wl,--whole-archive", libArgs, "-Wl,--no-whole-archive");
        
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
    
    private void copyResources(File srcDir, File destDir) throws IOException {
        FileFilter filter = new OrFileFilter(
                new NotFileFilter(new SuffixFileFilter(".class", IOCase.INSENSITIVE)), 
                DirectoryFileFilter.INSTANCE);
        FileUtils.copyDirectory(srcDir, destDir, filter);
    }
    
    private void exec(String cmd, Object ... args) throws IOException {
        exec(null, cmd, args);
    }
    
    private void exec(File wd, String cmd, Object ... args) throws IOException {
        execWithEnv(wd, null, cmd, args);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void execWithEnv(File wd, Map env, String cmd, Object ... args) throws IOException {
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
                } else if ("-clean".equals(args[i])) {
                    clean = true;
                } else if ("-help".equals(args[i]) || "-?".equals(args[i])) {
                    printUsageAndExit(null);
                } else if ("-ar-bin".equals(args[i])) {
                    arBin = new File(args[++i]);
                } else if ("-ar-opt".equals(args[i])) {
                    arOpts.add(args[++i]);
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
                } else if (args[i].startsWith("-D")) {
                } else if (args[i].startsWith("-X")) {
                } else if (args[i].startsWith("-verify:")) {
                    try {
                        verify = VerifyWhen.valueOf(args[i].substring("-verify:".length()).toUpperCase());
                    } catch (Throwable t) {
                        throw new IllegalArgumentException("Unrecognized verify option: " + args[i]);
                    }
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
        
        if (verbose) {
            if (mainClass != null) {
                stdout.println("Using main class: " + mainClass);
            }
            stdout.println("Run arguments: " + runArgs);
        }
        
        cache.mkdirs();

        if (target == null) {
            target = mainClass;
        }
        
        bootClassPathFiles.add(new File(home, "lib/nullvm-rt.jar"));

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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
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

    public static void main(String[] args) throws IOException {
        new Main().run(args);
    }

    private static File makeFileRelativeTo(File dir, File f) {
        if (f.getParentFile() == null) {
            return dir;
        }
        return new File(makeFileRelativeTo(dir, f.getParentFile()), f.getName());
    }
    
    interface ClasspathEntry {
        Path getPath();
        File getStaticLibrary();
        File getDynamicLibrary();
        File getCacheDir();
        File getArchive();
        boolean hasChangedAfter(long timestamp);
    }
    
    class ZipFilePathClasspathEntry implements ClasspathEntry {
        private final ZipFilePath path;
        private File cacheDir = null;
        
        public ZipFilePathClasspathEntry(ZipFilePath path) {
            this.path = path;
        }
        public Path getPath() {
            return path;
        }
        public File getStaticLibrary() {
            return new File(getCacheDir().getParentFile(), "lib" + path.getFile().getName() + ".a");
        }
        public File getDynamicLibrary() {
            return new File(getCacheDir().getParentFile(), "lib" + path.getFile().getName() + ".so");
        }
        public File getCacheDir() {
            if (cacheDir == null) {
                try {
                    cacheDir = new File(makeFileRelativeTo(cache, path.getFile().getCanonicalFile().getParentFile()), 
                            path.getFile().getName() + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return cacheDir;
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
        private File cacheDir = null;
        private final String jarName;
        
        public DirectoryPathClasspathEntry(DirectoryPath path) {
            this.path = path;
            jarName = "classes" + path.getIndex() + ".jar";
        }
        public Path getPath() {
            return path;
        }
        public File getStaticLibrary() {
            return new File(getCacheDir().getParentFile(), "lib" + jarName + ".a");
        }
        public File getDynamicLibrary() {
            return new File(getCacheDir().getParentFile(), "lib" + jarName + ".so");
        }
        public File getCacheDir() {
            if (cacheDir == null) {
                try {
                    cacheDir = new File(makeFileRelativeTo(cache, path.getFile().getCanonicalFile()), jarName + ".classes");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } 
            return cacheDir;
        }        
        public File getArchive() {
            if (archive == null) {
                File a = new File(getCacheDir().getParentFile(), jarName);
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
