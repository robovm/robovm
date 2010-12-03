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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;
import org.objectweb.asm.ClassReader;

/**
 *
 * @version $Id$
 */
public class Main {

    @Option(name = "-cp", aliases = "-classpath", metaVar = "<list>", required = true,
            usage = ": separated list of directories, JAR archives, and ZIP archives to" 
                + " search for class files.")
    private String classpath = null;

    @Option(name = "-bootclasspath", metaVar = "<list>", required = false,
            usage = "Classpath used to locate the java.* and javax.* classes. Default is ~/nullvm/lib/nullvm-rt.jar.")
    private String bootclasspath = null;
    
    @Option(name = "-d", metaVar = "<dir>", required = true,
            usage = "Place the generated executable and other files in <dir>.")
    private File output = null;
    
    @Option(name = "-o", metaVar = "<name>", required = false,
            usage = "The name of the target executable or library")
    private String target = null;
    
    @Option(name = "-home", metaVar = "<dir>",
            usage = "Directory where NullVM runtime has been installed and where compiled class files will be cached. Default is ~/.nullvm")
    private File home = new File(System.getProperty("user.home"), ".nullvm");

    @Option(name = "-cache", metaVar = "<dir>",
            usage = "Directory where cached compiled class files will be placed. Default is ~/.nullvm/cache")
    private File cache = new File(home, "cache");
    
    @Option(name = "-clean", usage = "Compile class files even if a compiled version already exists in the cache.")
    private boolean clean = false;
    
    @Option(name = "-llvm-bin-dir", usage = "Path where the LLVM binaries can be found")
    private File llvmBinDir = null;    

    @Option(name = "-llc-opt", usage = "Option to pass to llc")
    private List<String> llcOpts = new ArrayList<String>();
    
    @Option(name = "-opt-opt", usage = "Option to pass to opt")
    private List<String> optOpts = new ArrayList<String>();
    
    @Option(name = "-gcc-opt", usage = "Option to pass to gcc")
    private List<String> gccOpts = new ArrayList<String>();
    
    @Option(name = "-ld-opt", usage = "Option to pass to ld")
    private List<String> ldOpts = new ArrayList<String>();
    
    @Option(name = "-gcc-bin", usage = "Path to the gcc binary")
    private File gccBin = null;    
    
    @Option(name = "-ld-bin", usage = "Path to the ld binary")
    private File ldBin = null;    
    
    @Option(name = "-ar-bin", usage = "Path to the ar binary")
    private File arBin = null;
    
    @Option(name = "-help", usage = "Display this information")
    private boolean help = false;
    
    @Option(name = "-verbose", usage = "Output messages about what the compiler is doing")
    private boolean verbose = false;

    @Option(name = "-jar", usage = "Use main class as specified by the manifest in this JAR archive.")
    private File jarFile;

    @Option(name = "-skip-rt-lib", usage = "Skip linking with the nullvm-rt library")
    private boolean skipRtLib = false;

    @Option(name = "-I", metaVar = "<directory>", usage = "Extra include directory passed to gcc")
    private List<File> includeDirs = new ArrayList<File>();
    
    @Option(name = "-L", metaVar = "<directory>", usage = "Extra lib directory passed to ld")
    private List<File> libDirs = new ArrayList<File>();
    
    @Argument
    private String mainClass;
    
    private File classCache;
    private File libCache;
    private File opcodesFile;
    private File mainCFile;
    private File symbolsMapFile;
    private List<File> bootClassPathFiles = new ArrayList<File>();
    private List<File> classPathFiles = new ArrayList<File>();
    private PrintStream stdout = System.out;

    private File tmpFile;
    
    private void printUsageAndExit(CmdLineParser parser, String errorMessage) {
        if (errorMessage != null) {
            System.err.format("nullvm: %s\n", errorMessage);
        }
        System.err.println("Usage: nullvm [-options] class");
        System.err.println("   or  nullvm [-options] -jar jarfile");
        System.err.println("Options:");
        parser.printUsage(System.err);
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
    
    private static String hash(File f) throws IOException {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            return hash(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    private static String hash(InputStream in) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                digest.update(buffer, 0, n);
            }
            return toHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static <T> List<T> removeDuplicates(List<T> l) {
        List<T> result = new ArrayList<T>();
        Set<T> set = new HashSet<T>();
        for (T t : l) {
            if (!set.contains(t)) {
                result.add(t);
                set.add(t);
            }
        }
        return result;
    }

    private static boolean isArchive(File f) {
        return f.getName().matches("(?i)^.*(\\.zip|\\.jar)$");
    }
    
    private File processClassFile(String classFile, InputStream in, long lastModified) throws IOException {
        OutputStream out = null;
        File outFile = null;
        try {
            byte[] classData = IOUtils.toByteArray(in);
            ClassReader cr = new ClassReader(new ByteArrayInputStream(classData));
            String className = cr.getClassName();
            File classDir = new File(new File(classCache, className.replace('/', File.separatorChar)), hash(new ByteArrayInputStream(classData)));
            outFile = new File(classDir, className.replace('/', '_') + "" + ".class.ll");
            outFile.getParentFile().mkdirs();
            if (!clean && outFile.exists()) { // && outFile.lastModified() >= lastModified) {
                if (verbose) {
                    stdout.println("Skipping unchanged class file: " + classFile);
                }
                return processIrFile(outFile);
            }
            if (verbose) {
                stdout.format("Compiling class file '%s' to LLVM IR file '%s'\n", classFile, outFile);
            }
            out = new FileOutputStream(outFile);
            new ClassCompiler().compile(cr, out, false);
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

    private File processClassFile(File f) throws IOException {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            return processClassFile(f.getAbsolutePath(), in, f.lastModified());
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    private List<File> processClassFilesInDir(File dir) throws IOException {
        List<File> result = new ArrayList<File>();        
        for (File f : dir.listFiles()) {
            if (f.getName().toLowerCase().endsWith(".class")) {
                result.add(processClassFile(f));
            } else if (f.isDirectory()) {
                result.addAll(processClassFilesInDir(f));
            }
        }
        return result;
    }
    
    private List<File> processClassFiles(File input) throws IOException {
        if (isArchive(input)) {
            return Collections.singletonList(processArchivedClassFiles(input));
        } else {
            return processClassFilesInDir(input);
        }
    }
    
    private File processArchivedClassFiles(File input) throws IOException {
        File libFile = new File(new File(new File(libCache, input.getName()), hash(input)), "lib" + input.getName() + ".a");
        libFile.getParentFile().mkdirs();
        if (!clean && libFile.exists() && libFile.lastModified() >= input.lastModified()) {
            if (verbose) {
                stdout.println("Skipping unchanged archive: " + input);
            }
            return libFile;
        }
        
        ZipFile archive = null;
        try {
            archive = new ZipFile(input);
            List<File> objectFiles = new ArrayList<File>();
            Enumeration<? extends ZipEntry> entries = archive.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().toLowerCase().endsWith(".class")) {
                    continue;
                }
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = archive.getInputStream(entry);
                    objectFiles.add(processClassFile(entry.getName(), in, entry.getTime()));
                } catch (Throwable t) {
                    if (t instanceof IOException) {
                        throw (IOException) t;
                    }
                    if (t instanceof RuntimeException) {
                        throw (RuntimeException) t;
                    }
                    throw new RuntimeException(t);
                } finally {
                    IOUtils.closeQuietly(in);
                    IOUtils.closeQuietly(out);
                }
            }
            return buildLibrary(objectFiles, libFile);
        } finally {
            try {
                archive.close();
            } catch (Throwable t) {}
        }
    }
    
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
        String llvmLinkPath = "llvm-link";
        if (llvmBinDir != null) {
            llcPath = new File(llvmBinDir, "llc").getAbsolutePath();
            optPath = new File(llvmBinDir, "opt").getAbsolutePath();
            llvmLinkPath = new File(llvmBinDir, "llvm-link").getAbsolutePath();
        }
        
        File outLinkedFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".linked.bc");
        exec(llvmLinkPath, "-o=" + outLinkedFile.toString(), f, opcodesFile);
            
        File outOptedFile = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 3) + ".opted.bc");
        exec(optPath, optOpts, "-mem2reg", "-always-inline", "-o=" + outOptedFile.toString(), outLinkedFile);
        
        exec(llcPath, llcOpts, "-o=" + outFile.toString(), outOptedFile);
            
        return processGccFile(outFile);
    }
    
    private File processGccFile(File f) throws IOException {
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
    
    private File buildLibrary(List<File> files, File libFile) throws IOException {
        if (verbose) {
            stdout.format("Building library '%s'\n", libFile);
        }
        
        String arPath = "ar";
        if (arBin != null) {
            arPath = arBin.getAbsolutePath();
        }
        
        if (libFile.exists()) {
            libFile.delete();
        }
        
        exec(arPath, "rcs", libFile, files);
        
        return libFile;
    }
    
    private void buildExecutable(List<File> files) throws IOException {
        File outFile = new File(output, target);
        
        if (verbose) {
            stdout.format("Building executable '%s'\n", outFile);
        }
        
        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }
        
        if (mainClass != null) {
            List<String> gccArgs = new ArrayList<String>();
            for (File f : includeDirs) {
                gccArgs.add("-I");
                gccArgs.add(f.getAbsolutePath());
            }
            gccArgs.add("-I");
            gccArgs.add(new File(home, "include").getAbsolutePath());
            gccArgs.add("-I");
            gccArgs.add(new File(new File(home, "gc"), "include").getAbsolutePath());
            File mainObjectFile = new File(mainCFile.getParentFile(), "main.o");
            exec(gccPath, "-c", "-o", mainObjectFile, "-DNULLVM_MAIN_CLASS=" + mainClass, "-g", gccOpts, gccArgs, mainCFile);
            files.add(mainObjectFile);
        }

        List<String> ldArgs = new ArrayList<String>();
        for (File f : libDirs) {
            ldArgs.add("-L");
            ldArgs.add(f.getAbsolutePath());
        }
        ldArgs.add("-L");
        ldArgs.add(new File(home, "lib").getAbsolutePath());
        ldArgs.add("-L");
        ldArgs.add(new File(new File(home, "gc"), "lib").getAbsolutePath());
        
//        String ldPath = "ld";
//        if (ldBin != null) {
//            ldPath = ldBin.getAbsolutePath();
//        }
        
        List<File> objectFileArgs = new ArrayList<File>();
        List<String> libArgs = new ArrayList<String>();
        for (File f : files) {
            if (f.getName().endsWith(".a")) {
                ldArgs.add("-L");
                ldArgs.add(f.getParentFile().getAbsolutePath());                
                libArgs.add("-l:" + f.getName());
            } else {
                objectFileArgs.add(f);
            }
        }
        
        exec(gccPath, "-o", outFile, "-g", "-Wl,--version-script", symbolsMapFile, 
                gccOpts, "-rdynamic", ldArgs, objectFileArgs, "-lnullvm", "-lnullvm-rt", "-lm", "-ldl", "-lpthread",
                "-Wl,--whole-archive", libArgs, "-Wl,--no-whole-archive", "-Wl,-Bstatic", "-lgc", "-Wl,-Bdynamic");

//        exec(ldPath, "-o", outFile, "--version-script", symbolsMapFile, 
//                ldOpts, ldArgs, objectFileArgs, "-lnullvm", "-lm", "-ldl", "-lpthread",
//                "--whole-archive", libArgs, "--no-whole-archive", "-Bstatic", "-lgc", "-Bdynamic");
    }
    
    private void stripArchive(File input, File output) throws IOException {
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
    
    private void copyResources(File srcDir, File destDir) throws IOException {
        FileFilter filter = new OrFileFilter(
                new NotFileFilter(new SuffixFileFilter(".class", IOCase.INSENSITIVE)), 
                DirectoryFileFilter.INSTANCE);
        FileUtils.copyDirectory(srcDir, destDir, filter);
    }
    
//    private void processJarFile(JarFile jar) throws IOException {
//
//        List<File> files;
//        files = processClassFiles(jar);
//        files = processIrFiles(files);
//        files = processBcFiles(files);
//        files = processGccFiles(files);
//        buildLibrary(files);
//        stripJar(jar);
//    }
    
    @SuppressWarnings("unchecked")
    private void exec(String cmd, Object ... args) throws IOException {
        exec(null, cmd, args);
    }
    
    @SuppressWarnings("unchecked")
    private void exec(File wd, String cmd, Object ... args) throws IOException {
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
        executor.setExitValue(0);
        executor.execute(commandLine);
    }
    
    private void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (help) {
                printUsageAndExit(parser, null);
            }
            run();
        } catch (Throwable t) {
            if (verbose && !(t instanceof CmdLineException)) {
                t.printStackTrace();
            }
            printUsageAndExit(parser, t.getMessage());
        }
    }

    public void run() throws CmdLineException, IOException {
        
        classCache = new File(cache, "classes");
        classCache.mkdirs();
        libCache = new File(cache, "lib");
        libCache.mkdirs();

        if (target == null) {
            target = mainClass != null ? mainClass : "a.out";
        }
        
        if (!skipRtLib) {
            bootClassPathFiles.add(new File(home, "lib/nullvm-rt.jar"));
        }

        if (jarFile != null) {
            //mainClass = readMainClassFromManifest(jarFile);
            classPathFiles.add(jarFile);
        }
        
        if (mainClass == null) {
            throw new CmdLineException("No main class specified on command line or in JAR manifest");
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
        
        bootClassPathFiles = removeDuplicates(bootClassPathFiles);
        classPathFiles = removeDuplicates(classPathFiles);
        
        // Make sure all inputs exists
        List<File> inputs = new ArrayList<File>();
        inputs.addAll(bootClassPathFiles);
        inputs.addAll(classPathFiles);
        inputs = removeDuplicates(inputs);
        for (File f : inputs) {
            if (!f.exists()) {
                throw new FileNotFoundException(f.getAbsolutePath());
            }
            if (isArchive(f)) {
                if (!f.isFile()) {
                    throw new IOException("Path is not a file: " + f.getAbsolutePath());
                }
            } else {
                if (!f.isDirectory()) {
                    throw new IOException("Path is not a directory: " + f.getAbsolutePath());
                }
            }
        }
        
        /*
         * Copy files to tmp directory
         */
        tmpFile = File.createTempFile("nullvm", ".tmp");
        tmpFile.delete();
        tmpFile.mkdirs();
        opcodesFile = new File(tmpFile, "opcodes.ll");
        FileUtils.copyURLToFile(getClass().getResource("/opcodes.ll"), opcodesFile);
        mainCFile = new File(tmpFile, "main.c");
        FileUtils.copyURLToFile(getClass().getResource("/main.c"), mainCFile);
        symbolsMapFile = new File(tmpFile, "symbols.map");
        FileUtils.copyURLToFile(getClass().getResource("/symbols.map"), symbolsMapFile);

        List<File> files = new ArrayList<File>();
        for (File input : inputs) {
            files.addAll(processClassFiles(input));
        }
        
        output.mkdirs();
        buildExecutable(files);
        File bootCpClasses = new File(new File(output, "boot"), "classes");
        bootCpClasses.mkdirs();
        File bootCpLib = new File(new File(output, "boot"), "lib");
        bootCpLib.mkdirs();
        File cpClasses = new File(new File(output, "boot"), "classes");
        cpClasses.mkdirs();
        File cpLib = new File(new File(output, "boot"), "lib");
        cpLib.mkdirs();
        
        for (File f : bootClassPathFiles) {
            if (isArchive(f)) {
                stripArchive(f, new File(bootCpLib, f.getName()));
            } else {
                copyResources(f, bootCpClasses);
            }
        }
        for (File f : classPathFiles) {
            if (isArchive(f)) {
                stripArchive(f, new File(cpLib, f.getName()));
            } else {
                copyResources(f, cpClasses);
            }
        }
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

    public static class PatternOptionHandler extends OptionHandler<Pattern> {
        public PatternOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super Pattern> setter) {
            super(parser, option, setter);
        }

        @Override
        public String getDefaultMetaVariable() {
            return "VAL";
        }

        @Override
        public int parseArguments(Parameters params) throws CmdLineException {
            setter.addValue(Pattern.compile(params.getParameter(0)));
            return 1;
        }
        
    }
}
