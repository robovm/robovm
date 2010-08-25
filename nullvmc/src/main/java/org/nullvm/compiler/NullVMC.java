/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

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
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
public class NullVMC {

    @Option(name = "-o", metaVar = "<file>", required = true,
            usage = "Place the output into <file>")
    private File output = null;
    
    @Option(name = "-lib", usage = "Links against the specified library")
    private List<String> libs = new ArrayList<String>();
    
    @Option(name = "-build-lib", usage = "If set a static library will be built instead of an executable")
    private boolean buildLib = false;

    @Option(name = "-skip-main-stub", usage = "If set no default main() function will be generated but has to be provided")
    private boolean skipMainStub = false;

    @Option(name = "-main-class", metaVar = "<class>",
            usage = "Use the main() method in the specified class as the program entry method")
    private String mainClass = null;
    
    @Option(name = "-work", metaVar = "<directory>",
            usage = "Directory where temporary files will be placed")
    private File work = new File(System.getProperty("java.io.tmpdir"), "nullvmc");
    
    @Option(name = "-clean", usage = "Clean the work and target directories before compiling")
    private boolean clean = false;
    
    @Option(name = "-llvm-bin-dir", usage = "Path where the LLVM binaries can be found")
    private File llvmBinDir = null;    

    @Option(name = "-llc-opt", usage = "Option to pass to llc")
    private List<String> llcOpts = new ArrayList<String>();
    
    @Option(name = "-opt-opt", usage = "Option to pass to opt")
    private List<String> optOpts = new ArrayList<String>();
    
    @Option(name = "-gcc-opt", usage = "Option to pass to gcc")
    private List<String> gccOpts = new ArrayList<String>();
    
    @Option(name = "-gcc-bin", usage = "Path to the gcc binary")
    private File gccBin = null;    
    
    @Option(name = "-ar-bin", usage = "Path to the ar binary")
    private File arBin = null;    
    
    @Option(name = "-help", usage = "Display this information")
    private boolean help = false;
    
    @Option(name = "-verbose", usage = "Output messages about what the compiler is doing")
    private boolean verbose = false;
    
    @Option(name = "-skip-rt-lib", usage = "Skip linking with the nullvm-rt which provides the java.* packages")
    private boolean skipRtLib = false;
    
    @Option(name = "-pattern", metaVar = "<pattern>", 
            usage = "Regular expression used to match supported file types", 
            handler = PatternOptionHandler.class)
    private Pattern pattern = Pattern.compile("(?i).*\\.(class|jar|ll|bc|c|cpp|cc|cxx|m|s|o)$");
    
    @Option(name = "-I", metaVar = "<directory>", usage = "Extra include directory passed to GCC")
    private List<File> includeDirs = new ArrayList<File>();
    
    @Option(name = "-L", metaVar = "<directory>", usage = "Extra lib directory passed to GCC")
    private List<File> libDirs = new ArrayList<File>();
    
    @Argument
    private List<File> inputs = new ArrayList<File>();
    
    private PrintStream stdout = System.out;
    
    private void printUsageAndExit(CmdLineParser parser, String errorMessage) {
        if (errorMessage != null) {
            System.err.format("nullvmc: %s\n", errorMessage);
        }
        System.err.println("Usage: nullvmc [options] file...");
        System.err.println("Options:");
        parser.printUsage(System.err);
        System.err.println();
        System.err.println(
  "The files specified can be Java class or JAR files (.class, .jar), LLVM IR or \n" 
+ "bitcode files (.ll, .bc) or any file supported by GCC (e.g.: .c, .cpp, .cxx,\n" 
+ ".cc, .m, .s, .o). If a directory is specified it will be searched recursively\n" 
+ "and all files matching the <pattern> option will be included.");
        System.exit(errorMessage != null ? 1 : 0);
    }
    
    private List<File> findFiles(File dir) {
        List<File> result = new ArrayList<File>();
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                result.addAll(findFiles(f));
            } else if (pattern.matcher(f.getName()).matches()) {
                result.add(f);
            }
        }
        return result;
    }
    
    private List<File> processClassFiles(List<File> files) throws IOException {
        List<File> result = new ArrayList<File>();
        for (File f : files) {
            if (!f.getName().toLowerCase().endsWith(".class")) {
                result.add(f);
                continue;
            }
            InputStream in = null;
            OutputStream out = null;
            File outFile = null;
            try {
                in = new FileInputStream(f);
                ClassReader cr = new ClassReader(in);
                outFile = new File(work, cr.getClassName().replace('/', '_') + ".ll");
                if (outFile.exists() && outFile.lastModified() >= f.lastModified()) {
                    if (verbose) {
                        stdout.println("Skipping unchanged class file: " + f);
                    }
                    result.add(outFile);
                    continue;
                }
                if (verbose) {
                    stdout.format("Compiling class file '%s' to LLVM IR file '%s'\n", f, outFile);
                }
                out = new FileOutputStream(outFile);
                new Compiler().compile(cr, out);
                result.add(outFile);
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
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        }
        return result;
    }
    
    private List<File> processIrFiles(List<File> files) throws IOException {
        List<File> result = new ArrayList<File>();
        for (File f : files) {
            if (!f.getName().toLowerCase().endsWith(".ll")) {
                result.add(f);
                continue;
            }
            
            File outFile = new File(work, f.getName().substring(0, f.getName().length() - 3) + ".bc");
            if (outFile.exists() && outFile.lastModified() >= f.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged LLVM IR file: " + f);
                }
                result.add(outFile);
                continue;
            }
            if (verbose) {
                stdout.format("Compiling LLVM IR file '%s' to LLVM bitcode file '%s'\n", f, outFile);
            }
            
            String llvmAsPath = "llvm-as";
            if (llvmBinDir != null) {
                llvmAsPath = new File(llvmBinDir, "llvm-as").getAbsolutePath();
            }
            
            exec(llvmAsPath, "-o=" + outFile.toString(), f);
            result.add(outFile);
        }
        return result;
    }
    
    private List<File> processBcFiles(List<File> files) throws IOException {
        List<File> result = new ArrayList<File>();
        for (File f : files) {
            if (!f.getName().toLowerCase().endsWith(".bc")) {
                result.add(f);
                continue;
            }
            
            File outFile = new File(work, f.getName().substring(0, f.getName().length() - 3) + ".s");
            if (outFile.exists() && outFile.lastModified() >= f.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged LLVM bitcode file: " + f);
                }
                result.add(outFile);
                continue;
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
            
            File outLinkedFile = new File(work, f.getName().substring(0, f.getName().length() - 3) + ".linked.bc");
            exec(llvmLinkPath, "-o=" + outLinkedFile.toString(), f, new File(work, "opcodes.ll"));
            
            File outOptedFile = new File(work, f.getName().substring(0, f.getName().length() - 3) + ".opted.bc");
            exec(optPath, optOpts, "-mem2reg", "-always-inline", "-o=" + outOptedFile.toString(), outLinkedFile);
            
            exec(llcPath, llcOpts, "-o=" + outFile.toString(), outOptedFile);
            
            result.add(outFile);
        }
        return result;
    }
    
    private List<File> processGccFiles(List<File> files) throws IOException {
        List<File> result = new ArrayList<File>();
        for (File f : files) {

            File outFile = new File(work, f.getName().substring(0, f.getName().lastIndexOf('.')) + ".o");
            if (outFile.exists() && outFile.lastModified() >= f.lastModified()) {
                if (verbose) {
                    stdout.println("Skipping unchanged GCC input file: " + f);
                }
                result.add(outFile);
                continue;
            }
            if (verbose) {
                stdout.format("Compiling '%s' to '%s'\n", f, outFile);
            }

            List<String> args = new ArrayList<String>();
            for (File include : includeDirs) {
                args.add("-I");
                args.add(include.getAbsolutePath());
            }
            
            args.add("-I");
            args.add(new File(System.getProperty("user.home"), ".nullvm/include").getAbsolutePath());
            
            String gccPath = "gcc";
            if (gccBin != null) {
                gccPath = gccBin.getAbsolutePath();
            }
            
            exec(gccPath, "-c", "-o", outFile, "-g", gccOpts, args, f);
            result.add(outFile);
        }
        return result;
    }
    
    private void buildLibrary(List<File> files) throws IOException {
        if (verbose) {
            stdout.format("Building library '%s'\n", output);
        }
        
        String arPath = "ar";
        if (arBin != null) {
            arPath = arBin.getAbsolutePath();
        }

        exec(arPath, "rcs", output, files);
    }

    private void buildExecutable(List<File> files) throws IOException {
        if (verbose) {
            stdout.format("Building executable '%s'\n", output);
        }
        
        List<String> args = new ArrayList<String>();
        for (File f : includeDirs) {
            args.add("-I");
            args.add(f.getAbsolutePath());
        }
        for (File f : libDirs) {
            args.add("-L");
            args.add(f.getAbsolutePath());
        }
        args.add("-I");
        args.add(new File(System.getProperty("user.home"), ".nullvm/include").getAbsolutePath());
        args.add("-L");
        args.add(new File(System.getProperty("user.home"), ".nullvm/lib").getAbsolutePath());
        
        List<String> libArgs = new ArrayList<String>();
        if (!skipRtLib) {
            libArgs.add("-lnullvm-rt");
        }
        for (String lib : libs) {
            args.add("-l" + lib);
        }
        
        String gccPath = "gcc";
        if (gccBin != null) {
            gccPath = gccBin.getAbsolutePath();
        }

        if (!skipMainStub) {
            if (mainClass != null) {
                args.add("-DNULLVM_MAIN_CLASS=" + mainClass);
            }
            files.add(new File(work, "main.c"));
        }
        
        exec(gccPath, "-o", output, "-g", "-Wl,--version-script", new File(work, "symbols.map"), 
                gccOpts, "-rdynamic", args, files, "-lnullvm", "-lm", "-ldl", "-lpthread",
                "-Wl,--whole-archive", libArgs, "-Wl,--no-whole-archive", "-Wl,-Bstatic", "-lgc", "-Wl,-Bdynamic");
    }
    
    private void processFiles(List<File> files) throws IOException {
        /*
         * Copy files to work directory
         */
        FileUtils.copyURLToFile(getClass().getResource("/opcodes.ll"), new File(work, "opcodes.ll"));
        FileUtils.copyURLToFile(getClass().getResource("/symbols.map"), new File(work, "symbols.map"));
        FileUtils.copyURLToFile(getClass().getResource("/main.c"), new File(work, "main.c"));
        
//            files = processJarFiles(files);
        files = processClassFiles(files);
        files = processIrFiles(files);
        files = processBcFiles(files);
        if (buildLib) {
            files = processGccFiles(files);
            buildLibrary(files);
        } else {
            buildExecutable(files);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void exec(String cmd, Object ... args) throws IOException {
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
        
        stdout.println(commandLine.toString());
        
        Executor executor = new DefaultExecutor();
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
            t.printStackTrace();
            printUsageAndExit(parser, t.getMessage());
        }
    }

    public void run() throws IOException {
        if (inputs.isEmpty()) {
            throw new RuntimeException("No input files specified");
        }
        
        /*
         * Check that the files are either existing files or directories. 
         */
        List<File> files = new ArrayList<File>();
        for (File f : inputs) {
            if (!f.exists()) {
                throw new RuntimeException("Input file '"+ f + "' not found");
            }
            if (f.isDirectory()) {
                if (verbose) {
                    stdout.format("Searching dir '%s' for supported file types...\n", f);
                }
                files.addAll(findFiles(f));
            } else {
                files.add(f);
            }
        }
        
        if (files.isEmpty()) {
            throw new RuntimeException("No input files found");
        }
        
        stdout.println("Using work directory: " + work);
        
        if (clean && work.exists()) {
            if (verbose) {
                stdout.println("Cleaning work directory...");
            }
            FileUtils.deleteDirectory(work);
        }
        work.mkdirs();
        
        processFiles(files);
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void setWork(File work) {
        this.work = work;
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

    public void setSkipRtLib(boolean skipRtLib) {
        this.skipRtLib = skipRtLib;
    }
    
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setIncludeDirs(List<File> includeDirs) {
        this.includeDirs = includeDirs;
    }

    public void addIncludeDirs(File ... dirs) {
        this.includeDirs.addAll(Arrays.asList(dirs));
    }
    
    public void addIncludeDir(File dir) {
        this.includeDirs.add(dir);
    }
    
    public void setInputs(List<File> inputs) {
        this.inputs = inputs;
    }

    public void addInputs(File ... files) {
        this.inputs.addAll(Arrays.asList(files));
    }
    
    public void addInput(File f) {
        this.inputs.add(f);
    }
    
    public void setArBin(File arBin) {
        this.arBin = arBin;
    }
    
    public void setLibs(List<String> libs) {
        this.libs = libs;
    }

    public void setBuildLib(boolean buildLib) {
        this.buildLib = buildLib;
    }

    public void setSkipMainStub(boolean skipMainStub) {
        this.skipMainStub = skipMainStub;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }
    
    public void setGccBin(File gccBin) {
        this.gccBin = gccBin;
    }

    public void setLibDirs(List<File> libDirs) {
        this.libDirs = libDirs;
    }

    public void addLibDirs(File ... dirs) {
        this.libDirs.addAll(Arrays.asList(dirs));
    }
    
    public void addLibDir(File dir) {
        this.libDirs.add(dir);
    }
    
    public void setStdout(PrintStream stdout) {
        this.stdout = stdout;
    }

    public static void main(String[] args) throws IOException {
        new NullVMC().run(args);
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
