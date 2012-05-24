/**
 * 
 */
package org.robovm.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Path;

/**
 * @author niklas
 *
 */
public class Config {
    private File installDir = null;
    private String executable = null;
    private File roboVMHomeDir = null;
    private File cacheDir = new File(System.getProperty("user.home"), ".robovm/cache");
    private File llvmHomeDir = null;
    private File ccBinPath = null;
    private File arBinPath = null;
    
    private OS os = null;
    private Arch arch = null;
    private String cpu = null;
    
    private boolean clean = false;
    private boolean debug = true;
    private boolean skipRuntimeLib = false;
    private boolean skipLinking = false;
    private boolean skipInstall = false;
    private File mainJar;
    private String mainClass;
    private List<String> roots = new ArrayList<String>();
    
    private File osArchDepLibDir;
    private List<File> bootclasspath = new ArrayList<File>();
    private List<File> classpath = new ArrayList<File>();
    private File tmpDir;
    private Clazzes clazzes;
    private Logger logger = Logger.NULL_LOGGER;
    private Target.Builder targetBuilder = new ConsoleTarget.Builder();
    private Target target = null;

    Config() {
    }
    
    public File getInstallDir() {
        return installDir;
    }

    public String getExecutable() {
        return executable;
    }

    public File getRoboVMHomeDir() {
        return roboVMHomeDir;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getCcBinPath() {
        return ccBinPath;
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

    public boolean isSkipInstall() {
        return skipInstall;
    }
    
    public File getMainJar() {
        return mainJar;
    }

    public String getMainClass() {
        return mainClass;
    }

    public File getTmpDir() {
        return tmpDir;
    }

    public List<String> getRoots() {
        return roots;
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
    
    public Target getTarget() {
        return target;
    }
    
    private static File makeFileRelativeTo(File dir, File f) {
        if (f.getParentFile() == null) {
            return dir;
        }
        return new File(makeFileRelativeTo(dir, f.getParentFile()), f.getName());
    }
    
    @SuppressWarnings("unchecked")
    private void createArchive(File dir, File output, boolean skipClassFiles) throws IOException {
        logger.debug("Creating archive file '%s' from files in directory '%s'", output, dir);
        
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
        File archive = new File(getCacheDir(path).getParentFile(), getArchiveName(path));
        if (!archive.exists() || path.hasChangedSince(archive.lastModified())) {
            try {
                createArchive(path.getFile(), archive, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return archive;
    }
    
    public File getLlFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.ll");
    }
    
    public File getBcFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.bc");
    }
    
    public File getSFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.s");
    }
    
    public File getOFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.o");
    }
    
    public File getInfoFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.info");
    }
    
    public File getCacheDir(Path path) {
        File srcRoot = path.getFile();
        if (path.getFile().isFile()) {
            srcRoot = srcRoot.getParentFile();
        }
        try {
            return new File(makeFileRelativeTo(cacheDir, 
                    srcRoot.getCanonicalFile()), 
                    getArchiveName(path) + ".classes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Config build() throws IOException {
        
        if (roboVMHomeDir == null) {
            if (System.getenv("ROBOVM_HOME") == null) {
                throw new IllegalArgumentException("Environment variable ROBOVM_HOME not set");
            }
            roboVMHomeDir = new File(System.getenv("ROBOVM_HOME"));
            if (!roboVMHomeDir.exists()) {
                throw new IllegalArgumentException("ROBOVM_HOME directory " 
                        + roboVMHomeDir.getAbsolutePath() + " not found");
            }
            if (!roboVMHomeDir.isDirectory()) {
                throw new IllegalArgumentException("Value of ROBOVM_HOME environment " 
                        + "variable does not point to a directory");
            }
        }
        
        if (mainJar != null) {
            //mainClass = readMainClassFromManifest(jarFile);
            classpath.add(mainJar);
        }
        
        if (classpath.isEmpty()) {
            throw new IllegalArgumentException("No classpath specified");
        }
        
        if (!skipLinking && executable == null && mainClass == null) {
            throw new IllegalArgumentException("No target and no main class specified");
        }

        if (os == null) {
            os = OS.getDefaultOS(llvmHomeDir);
        }
        
        if (arch == null) {
            arch = Arch.getDefaultArch(llvmHomeDir);
        }
        
        if (skipLinking) {
            skipInstall = true;
        }
        
        File homeLib = new File(roboVMHomeDir, "lib");
        osArchDepLibDir = new File(new File(new File(homeLib, os.toString()), arch.toString()), debug ? "Debug" : "Release");
        
        File osDir = new File(cacheDir, os.toString());
        File archDir = new File(osDir, arch.toString());
        cacheDir = new File(archDir, cpu == null ? "default" : cpu);
        cacheDir.mkdirs();

        if (executable == null) {
            executable = mainClass;
        }
        
        if (!skipRuntimeLib) {
            bootclasspath.add(0, new File(homeLib, "robovm-rt.jar"));
        }

        /*
         * Create tmp directory
         */
        if (tmpDir == null) {
            tmpDir = File.createTempFile("robovm", ".tmp");
            tmpDir.delete();
            tmpDir.mkdirs();
        }

        this.clazzes = new Clazzes(this, bootclasspath, classpath);
        
        if (!skipInstall) {
            if (installDir == null) {
                installDir = new File(".", executable);
            }
            installDir.mkdirs();
        }

        target = targetBuilder.build(this);
        
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
        
        public Builder mainJar(File f) {
            config.mainJar = f;
            return this;
        }

        public Builder installDir(File installDir) {
            config.installDir = installDir;
            return this;
        }

        public Builder executable(String executable) {
            config.executable = executable;
            return this;
        }

        public Builder roboVMHomeDir(File roboVMHomeDir) {
            config.roboVMHomeDir = roboVMHomeDir;
            return this;
        }

        public Builder cacheDir(File cacheDir) {
            config.cacheDir = cacheDir;
            return this;
        }

        public Builder clean(boolean b) {
            config.clean = b;
            return this;
        }

        public Builder llvmHomeDir(File llvmHomeDir) {
            config.llvmHomeDir = llvmHomeDir;
            return this;
        }

        public Builder ccBinPath(File ccBinPath) {
            config.ccBinPath = ccBinPath;
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

        public Builder debug(boolean b) {
            config.debug = b;
            return this;
        }

        public Builder skipRuntimeLib(boolean b) {
            config.skipRuntimeLib = b;
            return this;
        }

        public Builder skipLinking(boolean b) {
            config.skipLinking = b;
            return this;
        }

        public Builder skipInstall(boolean b) {
            config.skipInstall = b;
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

        public Builder addRoot(String pattern) {
            config.roots.add(pattern);
            return this;
        }

        public Builder targetBuilder(Target.Builder targetBuilder) {
            config.targetBuilder = targetBuilder;
            return this;
        }
        
        public Config build() throws IOException {
            config.targetBuilder.setup(this);
            return config.build();
        }
    }
}
