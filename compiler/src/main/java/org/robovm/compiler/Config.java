/*
 * Copyright (C) 2012 RoboVM
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
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
    private Home home = null;
    private File cacheDir = new File(System.getProperty("user.home"), ".robovm/cache");
    private File llvmHomeDir = null;
    private File ccBinPath = null;
    
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
    
    public Home getHome() {
        return home;
    }
    
    public File getInstallDir() {
        return installDir;
    }

    public String getExecutable() {
        return executable;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getCcBinPath() {
        return ccBinPath;
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
        if (tmpDir == null) {
            try {
                tmpDir = File.createTempFile("robovm", ".tmp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tmpDir.delete();
            tmpDir.mkdirs();
        }
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
    
    private static Map<Object, Object> getManifestAttributes(File jarFile) throws IOException {
        JarFile jf = null;
        try {
            jf = new JarFile(jarFile);
            return new HashMap<Object, Object>(jf.getManifest().getMainAttributes());
        } finally {
            jf.close();
        }
    }
    
    private static String getImplementationVersion(File jarFile) throws IOException {
        return (String) getManifestAttributes(jarFile).get(Attributes.Name.IMPLEMENTATION_VERSION);
    }

    private static String getMainClass(File jarFile) throws IOException {
        return (String) getManifestAttributes(jarFile).get(Attributes.Name.MAIN_CLASS);
    }
    
    private Config build() throws IOException {
        if (home == null) {
            home = Home.find();
        }
        
        if (mainJar != null) {
            mainClass = getMainClass(mainJar);
            classpath.add(mainJar);
        }
        
        if (!skipLinking && executable == null && mainClass == null) {
            throw new IllegalArgumentException("No target and no main class specified");
        }

        if (!skipLinking && classpath.isEmpty()) {
            throw new IllegalArgumentException("No classpath specified");
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
        
        osArchDepLibDir = new File(new File(new File(home.libVmDir, os.toString()), 
                arch.toString()), debug ? "Debug" : "Release");
        
        File osDir = new File(cacheDir, os.toString());
        File archDir = new File(osDir, arch.toString());
        cacheDir = new File(archDir, cpu == null ? "default" : cpu);
        cacheDir.mkdirs();

        if (executable == null) {
            executable = mainClass;
        }
        
        if (!skipRuntimeLib) {
            bootclasspath.add(0, home.rtPath);
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
    
    public static class Home {
        private File binDir = null;
        private File libVmDir = null;
        private File rtPath = null;

        public Home(File homeDir) {
            validate(homeDir);
            binDir = new File(homeDir, "bin");
            libVmDir = new File(homeDir, "lib/vm");
            rtPath = new File(homeDir, "lib/robovm-rt.jar");        
        }
        
        private Home(File binDir, File libVmDir, File rtPath) {
            this.binDir = binDir;
            this.libVmDir = libVmDir;
            this.rtPath = rtPath;
        }

        public File getBinDir() {
            return binDir;
        }
        
        public File getLibVmDir() {
            return libVmDir;
        }
        
        public File getRtPath() {
            return rtPath;
        }
        
        public static Home find() {
            // Check if ROBOVM_DEV_ROOT has been set. If set it should be pointing
            // at the root of a complete RoboVM source tree.
            if (System.getenv("ROBOVM_DEV_ROOT") != null) {
                File dir = new File(System.getenv("ROBOVM_DEV_ROOT"));
                validateDevRootDir(dir);
                return new Home(new File(dir, "bin"), 
                                 new File(dir, "vm/binaries"), 
                                 new File(dir, "rt/target/classes"));
            }
            
            if (System.getenv("ROBOVM_HOME") != null) {
                File dir = new File(System.getenv("ROBOVM_HOME"));
                return new Home(dir);
            }
            
            List<File> candidates = new ArrayList<File>();
            File userHome = new File(System.getProperty("user.home"));
            candidates.add(new File(userHome, "Applications/robovm"));
            candidates.add(new File(userHome, ".robovm/home"));
            candidates.add(new File("/usr/local/lib/robovm"));
            candidates.add(new File("/opt/robovm"));
            candidates.add(new File("/usr/lib/robovm"));
            
            for (File dir : candidates) {
                if (dir.exists()) {
                    return new Home(dir);
                }
            }
            
            throw new IllegalArgumentException("ROBOVM_HOME not set and no RoboVM " 
                    + "installation found in " + candidates);
        }
        
        public static void validate(File dir) {
            String error = "Path " + dir + " is not a valid RoboVM install directory: ";
            // Check for required dirs and match the compiler version
            // with our version.
            if (!dir.exists()) {
                throw new IllegalArgumentException(error + "no such path");
            }
            
            if (!dir.isDirectory()) {
                throw new IllegalArgumentException(error + "not a directory");
            }
            
            File libDir = new File(dir, "lib");
            if (!libDir.exists() || !libDir.isDirectory()) {
                throw new IllegalArgumentException(error + "lib/ missing or invalid");
            }
            File binDir = new File(dir, "bin");
            if (!binDir.exists() || !binDir.isDirectory()) {
                throw new IllegalArgumentException(error + "bin/ missing or invalid");
            }
            File libVmDir = new File(libDir, "vm");
            if (!libVmDir.exists() || !libVmDir.isDirectory()) {
                throw new IllegalArgumentException(error + "lib/vm/ missing or invalid");
            }
            File compilerJarFile = new File(libDir, "robovm-compiler.jar");
            if (!compilerJarFile.exists() || !compilerJarFile.isFile()) {
                throw new IllegalArgumentException(error 
                        + "lib/robovm-compiler.jar missing or invalid");
            }
            File rtJarFile = new File(libDir, "robovm-rt.jar");
            if (!rtJarFile.exists() || !rtJarFile.isFile()) {
                throw new IllegalArgumentException(error 
                        + "lib/robovm-rt.jar missing or invalid");
            }
            
            // Compare the version of this compiler with the version of the
            // robovm-compiler.jar.
            try {
                String thisVersion = Config.class.getPackage().getImplementationVersion();
                String thatVersion = getImplementationVersion(compilerJarFile);
                if (thisVersion == null || thatVersion == null || !thisVersion.equals(thatVersion)) {
                    throw new IllegalArgumentException(error + "compiler version mismatch (expected: " 
                            + thisVersion + ", was: " + thatVersion + ")");
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(error 
                        + "failed to get version of compiler jar", e);
            }
        }

        private static void validateDevRootDir(File dir) {
            String error = "Path " + dir + " is not a valid RoboVM source tree: ";
            // Check for required dirs.
            if (!dir.exists()) {
                throw new IllegalArgumentException(error + "no such path");
            }
            
            if (!dir.isDirectory()) {
                throw new IllegalArgumentException(error + "not a directory");
            }
            
            File vmBinariesDir = new File(dir, "vm/binaries");
            if (!vmBinariesDir.exists() || !vmBinariesDir.isDirectory()) {
                throw new IllegalArgumentException(error + "vm/binaries/ missing or invalid");
            }
            File binDir = new File(dir, "bin");
            if (!binDir.exists() || !binDir.isDirectory()) {
                throw new IllegalArgumentException(error + "bin/ missing or invalid");
            }

            File rtTargetClasses = new File(dir, "rt/target/classes");
            if (!rtTargetClasses.exists() || !rtTargetClasses.isDirectory()) {
                throw new IllegalArgumentException(error 
                        + "rt/target/classes/ missing or invalid");
            }
        }
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

        public Builder home(Home home) {
            config.home = home;
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
