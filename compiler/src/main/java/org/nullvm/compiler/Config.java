/**
 * 
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.nullvm.compiler.clazz.Clazzes;
import org.nullvm.compiler.clazz.Path;

/**
 * @author niklas
 *
 */
public class Config {
    private File targetDir = null;
    private String target = null;
    private File nullVMHomeDir = null;
    private File cacheDir = new File(System.getProperty("user.home"), ".nullvm/cache");
    private File llvmCacheDir = null;
    private File objectCacheDir = null;
    private File llvmHomeDir = null;
    private File gccBinPath = null;
    private File arBinPath = null;
    
    private OS os = null;
    private Arch arch = null;
    private String cpu = null;
    
    private boolean clean = false;
    private boolean debug = false;
    private boolean skipRuntimeLib = false;
    private boolean skipLinking = false;
    private boolean verbose = false;
    private File mainJar;
    private boolean run = false;
    private String mainClass;
    private List<String> runArgs = new ArrayList<String>();
    
    private File osArchDepLibDir;
    private List<File> bootclasspath = new ArrayList<File>();
    private List<File> classpath = new ArrayList<File>();
    private Set<String> classes = new HashSet<String>();
    private File tmpDir;
    private String host;
    private Clazzes clazzes;
    private Logger logger = Logger.NULL_LOGGER;

    Config() {
    }
    
    public File getTargetDir() {
        return targetDir;
    }

    public String getTarget() {
        return target;
    }

    public File getNullVMHomeDir() {
        return nullVMHomeDir;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getGccBinPath() {
        return gccBinPath;
    }

    public File getArBinPath() {
        return arBinPath;
    }

    public OS getOs() {
        return os;
    }

    public Arch getArch() {
        return arch;
    }

    public String getCpu() {
        return cpu;
    }

    public boolean isClean() {
        return clean;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isSkipRuntimeLib() {
        return skipRuntimeLib;
    }

    public boolean isSkipLinking() {
        return skipLinking;
    }

    public File getMainJar() {
        return mainJar;
    }

    public boolean isRun() {
        return run;
    }

    public String getMainClass() {
        return mainClass;
    }

    public List<String> getRunArgs() {
        return runArgs;
    }

    public Set<String> getClasses() {
        return classes;
    }

    public File getTmpDir() {
        return tmpDir;
    }

    public File getLlvmHomeDir() {
        return llvmHomeDir;
    }

    public File getLlvmBinDir() {
        return llvmHomeDir == null ? null : new File(llvmHomeDir, "bin");
    }
    
    public File getOsArchDepLibDir() {
        return osArchDepLibDir;
    }
    
    public Clazzes getClazzes() {
        return clazzes;
    }

    public Logger getLogger() {
        return logger;
    }
    
    private static File makeFileRelativeTo(File dir, File f) {
        if (f.getParentFile() == null) {
            return dir;
        }
        return new File(makeFileRelativeTo(dir, f.getParentFile()), f.getName());
    }
    
    @SuppressWarnings("unchecked")
    private void createArchive(File dir, File output, boolean skipClassFiles) throws IOException {
        logger.debug("Creating archive file '%s' from files in directory '%s'\n", output, dir);
        
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

    
    public String getArchiveName(Path path) {
        if (path.getFile().isFile()) {
            return path.getFile().getName();
        } else {
            return "classes" + path.getIndex() + ".jar";
        }
    }
    
    public File getArchivePath(Path path) {
        if (path.getFile().isFile()) {
            return path.getFile();
        }
        File archive = new File(getLlvmCacheDir(path).getParentFile(), getArchiveName(path));
        if (!archive.exists() || path.hasChangedSince(archive.lastModified())) {
            try {
                createArchive(path.getFile(), archive, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return archive;
    }
    
    public File getBcLibrary(Path path) {
        return new File(getLlvmCacheDir(path).getParentFile(), "lib" + getArchiveName(path) + ".a");
    }

    public File getStaticLibrary(Path path) {
        return new File(getObjectCacheDir(path).getParentFile(), "lib" + getArchiveName(path) + ".a");
    }
    
    public File getLlvmCacheDir(Path path) {
        try {
            File srcRoot = path.getFile();
            if (path.getFile().isFile()) {
                srcRoot = srcRoot.getParentFile();
            }
            return new File(makeFileRelativeTo(llvmCacheDir, 
                    srcRoot.getCanonicalFile()), 
                    getArchiveName(path) + ".classes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public File getObjectCacheDir(Path path) {
        File srcRoot = path.getFile();
        if (path.getFile().isFile()) {
            srcRoot = srcRoot.getParentFile();
        }
        File rootDir = new File(objectCacheDir, debug ? "debug" : "release");
        File osDir = new File(rootDir, os.toString());
        File archDir = new File(osDir, arch.toString());
        File cpuDir = new File(archDir, cpu == null ? "default" : cpu);
        try {
            return new File(makeFileRelativeTo(cpuDir, 
                    srcRoot.getCanonicalFile()), 
                    getArchiveName(path) + ".classes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("rawtypes")
    private String getHost() throws IOException {
        if (this.host != null) {
            return this.host;
        }
        String llcPath = "llc";
        if (llvmHomeDir != null) {
            llcPath = new File(getLlvmBinDir(), "llc").getAbsolutePath();
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
    
    private Config build() throws IOException {
        
        if (nullVMHomeDir == null) {
            if (System.getenv("NULLVM_HOME") == null) {
                throw new IllegalArgumentException("Environment variable NULLVM_HOME not set");
            }
            nullVMHomeDir = new File(System.getenv("NULLVM_HOME"));
            if (!nullVMHomeDir.exists()) {
                throw new IllegalArgumentException("NULLVM_HOME directory " 
                        + nullVMHomeDir.getAbsolutePath() + " not found");
            }
            if (!nullVMHomeDir.isDirectory()) {
                throw new IllegalArgumentException("Value of NULLVM_HOME environment " 
                        + "variable does not point to a directory");
            }
        }
        
        if (mainJar != null) {
            //mainClass = readMainClassFromManifest(jarFile);
            classpath.add(mainJar);
        }
        
        if (!skipLinking && targetDir == null) {
            throw new IllegalArgumentException("No target directory specified");
        }
        if (classpath.isEmpty()) {
            throw new IllegalArgumentException("No classpath specified");
        }
        
        if (!skipLinking && target == null && mainClass == null) {
            throw new IllegalArgumentException("No target and no main class specified");
        }

        if (os == null) {
            os = detectOS();
            logger.debug("Autodetected OS: %s", os);
        }
        
        if (arch == null) {
            arch = detectArch();
            logger.debug("Autodetected arch: %s", arch);
        }
        
        if (verbose) {
            if (mainClass != null) {
                logger.debug("Using main class: %s", mainClass);
            }
            if (run) {
                logger.debug("Run arguments: %s", runArgs);
            }
        }
        
        File homeLib = new File(nullVMHomeDir, "lib");
        osArchDepLibDir = new File(new File(homeLib, os.toString()), arch.toString());
        
        cacheDir.mkdirs();
        llvmCacheDir = new File(cacheDir, "llvm");
        llvmCacheDir.mkdirs();
        objectCacheDir = new File(cacheDir, "object");
        objectCacheDir.mkdirs();

        if (target == null) {
            target = mainClass;
        }
        
        if (!skipRuntimeLib) {
            bootclasspath.add(0, new File(homeLib, "nullvm-rt.jar"));
        }

        /*
         * Create tmp directory
         */
        if (tmpDir == null) {
            tmpDir = File.createTempFile("nullvm", ".tmp");
            tmpDir.delete();
            tmpDir.mkdirs();
        }

        this.clazzes = new Clazzes(bootclasspath, classpath);
        
        targetDir.mkdirs();

        return this;
    }
    
    public static class Builder {
        private final Config config;
        public Builder() {
            this.config = new Config();
        }
        
        public Builder addClasspathEntry(File f) {
            config.classpath.add(f);
            return this;
        }
        
        public Builder addBootClasspathEntry(File f) {
            config.bootclasspath.add(f);
            return this;
        }
        
        public Builder addRunArg(String s) {
            config.runArgs.add(s);
            return this;
        }
        
        public Builder mainJar(File f) {
            config.mainJar = f;
            return this;
        }

        public Builder targetDir(File targetDir) {
            config.targetDir = targetDir;
            return this;
        }

        public Builder target(String target) {
            config.target = target;
            return this;
        }

        public Builder nullVMHomeDir(File nullVMHomeDir) {
            config.nullVMHomeDir = nullVMHomeDir;
            return this;
        }

        public Builder cacheDir(File cacheDir) {
            config.cacheDir = cacheDir;
            return this;
        }

        public Builder llvmCacheDir(File llvmCacheDir) {
            config.llvmCacheDir = llvmCacheDir;
            return this;
        }

        public Builder clean() {
            config.clean = true;
            return this;
        }

        public Builder llvmHomeDir(File llvmHomeDir) {
            config.llvmHomeDir = llvmHomeDir;
            return this;
        }

        public Builder gccBinPath(File gccBinPath) {
            config.gccBinPath = gccBinPath;
            return this;
        }

        public Builder arBinPath(File arBinPath) {
            config.arBinPath = arBinPath;
            return this;
        }

        public Builder os(OS os) {
            config.os = os;
            return this;
        }

        public Builder arch(Arch arch) {
            config.arch = arch;
            return this;
        }

        public Builder cpu(String cpu) {
            config.cpu = cpu;
            return this;
        }

        public Builder debug() {
            config.debug = true;
            return this;
        }

        public Builder skipRuntimeLib() {
            config.skipRuntimeLib = true;
            return this;
        }

        public Builder skipLinking() {
            config.skipLinking = true;
            return this;
        }

        public Builder run() {
            config.run = true;
            return this;
        }

        public Builder mainClass(String mainClass) {
            config.mainClass = mainClass;
            return this;
        }

        public Builder tmpDir(File tmpDir) {
            config.tmpDir = tmpDir;
            return this;
        }
        
        public Builder logger(Logger logger) {
            config.logger = logger;
            return this;
        }
        
        public Config build() throws IOException {
            return config.build();
        }
    }
}
