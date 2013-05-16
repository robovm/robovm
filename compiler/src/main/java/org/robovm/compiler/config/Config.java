/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.robovm.compiler.Version;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.target.ConsoleTarget;
import org.robovm.compiler.target.IOSDeviceTarget;
import org.robovm.compiler.target.Target;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * @author niklas
 *
 */
@Root
public class Config {
    
    public enum Cacerts { full };
    
    @Element(required = false)
    private File installDir = null;
    @Element(required = false)
    private String executableName = null;
    @Element(required = false)
    private boolean useDynamicJni = false;
    @Element(required = false)
    private boolean skipRuntimeLib = false;
    @Element(required = false)
    private File mainJar;
    @Element(required = false)
    private String mainClass;
    @Element(required = false)
    private Cacerts cacerts = Cacerts.full;
    @ElementList(required = false, entry = "root")
    private ArrayList<String> roots = new ArrayList<String>();
    @ElementList(required = false, entry = "lib")
    private ArrayList<Lib> libs = new ArrayList<Lib>();
    @ElementList(required = false, entry = "framework")
    private ArrayList<String> frameworks = new ArrayList<String>();
    @ElementList(required = false, entry = "resource")
    private ArrayList<File> resources = new ArrayList<File>();
    @ElementList(required = false, entry = "classpathentry")
    private ArrayList<File> bootclasspath = new ArrayList<File>();
    @ElementList(required = false, entry = "classpathentry")
    private ArrayList<File> classpath = new ArrayList<File>();
    @org.simpleframework.xml.Path("target")
    @ElementUnion({
        @Element(name = "console", type = ConsoleTarget.class),
        @Element(name = "ios", type = IOSDeviceTarget.class)
    })
    private Target target = null;
    
    private Home home = null;
    private File cacheDir = new File(System.getProperty("user.home"), ".robovm/cache");
    private File llvmHomeDir = null;
    private File ccBinPath = null;
    
    private boolean clean = false;
    private boolean debug = true;
    private boolean useDebugLibs = false;
    private boolean skipLinking = false;
    private boolean skipInstall = false;
    
    private File osArchDepLibDir;
    private File tmpDir;
    private Clazzes clazzes;
    private Logger logger = Logger.NULL_LOGGER;
    private List<Path> resourcesPaths = new ArrayList<Path>();

    Config() {
    }
    
    public Home getHome() {
        return home;
    }
    
    public File getInstallDir() {
        return installDir;
    }

    public String getExecutableName() {
        return executableName;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public File getCcBinPath() {
        return ccBinPath;
    }

    public OS getOs() {
        return target.getOS();
    }

    public Arch getArch() {
        return target.getArch();
    }

    public boolean isClean() {
        return clean;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isUseDebugLibs() {
        return useDebugLibs;
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
    
    public boolean isUseDynamicJni() {
        return useDynamicJni;
    }
    
    public File getMainJar() {
        return mainJar;
    }

    public String getMainClass() {
        return mainClass;
    }

    public Cacerts getCacerts() {
        return cacerts;
    }
    
    public List<Path> getResourcesPaths() {
        return resourcesPaths;
    }
    
    public void addResourcesPath(Path path) {
        resourcesPaths.add(path);
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
    
    public List<String> getLibs() {
        List<String> result = new ArrayList<String>();
        for (Lib lib : libs) {
            result.add(lib.getValue());
        }
        return result;
    }
    
    public List<String> getFrameworks() {
        return frameworks;
    }
    
    public List<File> getResources() {
        return resources;
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
    
    public String getArchiveName(Path path) {
        if (path.getFile().isFile()) {
            return path.getFile().getName();
        } else {
            return "classes" + path.getIndex() + ".jar";
        }
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
    
    public File getDepsFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.deps");
    }
    
    public File getCacheDir(Path path) {
        File srcRoot = path.getFile().getParentFile();
        String name = path.getFile().getName();
        try {
            return new File(makeFileRelativeTo(cacheDir, srcRoot.getCanonicalFile()), name);
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
        
        if (llvmHomeDir == null) {
            llvmHomeDir = findLlvmHomeDir();
        }
        
        if (mainJar != null) {
            mainClass = getMainClass(mainJar);
            classpath.add(mainJar);
        }
        
        if (!skipLinking && executableName == null && mainClass == null) {
            throw new IllegalArgumentException("No target and no main class specified");
        }

        if (!skipLinking && classpath.isEmpty()) {
            throw new IllegalArgumentException("No classpath specified");
        }
        
        if (skipLinking) {
            skipInstall = true;
        }
        
        if (executableName == null) {
            executableName = mainClass;
        }

        List<File> realBootclasspath = bootclasspath;
        if (!skipRuntimeLib) {
            realBootclasspath = new ArrayList<File>(bootclasspath);
            realBootclasspath.add(0, home.rtPath);
        }

        this.clazzes = new Clazzes(this, realBootclasspath, classpath);
        
        if (!skipInstall) {
            if (installDir == null) {
                installDir = new File(".", executableName);
            }
            installDir.mkdirs();
        }

        if (target == null) {
            target = new ConsoleTarget();
        }
        target.init(this);
        
        OS os = target.getOS();
        Arch arch = target.getArch();
        
        osArchDepLibDir = new File(new File(home.libVmDir, os.toString()), 
                arch.toString());
        
        File osDir = new File(cacheDir, os.toString());
        File archDir = new File(osDir, arch.toString());
        cacheDir = new File(archDir, "default");
        cacheDir.mkdirs();
        
        return this;
    }

    static File findLlvmHomeDir() {
        // Look for llc in the paths in the PATH environment variable
        String path = System.getenv("PATH");
        boolean found = false;
        if (path != null) {
            for (String part : path.split(File.pathSeparator)) {
                if (new File(part, "llc").exists()) {
                    return new File(part).getParentFile();
                }
            }
        }
        if (!found) {
            if (new File("/opt/llvm/bin/llc").exists()) {
                return new File("/opt/llvm");
            } else if (new File("/usr/local/llvm/bin/llc").exists()) {
                return new File("/usr/local/llvm");
            }
        }
        return null;
    }
    
    public static class Home {
        private File binDir = null;
        private File libVmDir = null;
        private File rtPath = null;
        private Map<Cacerts, File> cacertsPath = null;
        private boolean dev = false;

        public Home(File homeDir) {
            validate(homeDir);
            binDir = new File(homeDir, "bin");
            libVmDir = new File(homeDir, "lib/vm");
            rtPath = new File(homeDir, "lib/robovm-rt.jar");
            cacertsPath = new HashMap<Cacerts, File>();
            cacertsPath.put(Cacerts.full, new File(homeDir, "lib/robovm-cacerts-full.jar"));
        }
        
        private Home(File devDir, File binDir, File libVmDir, File rtPath) {
            this.binDir = binDir;
            this.libVmDir = libVmDir;
            this.rtPath = rtPath;
            cacertsPath = new HashMap<Cacerts, File>();
            cacertsPath.put(Cacerts.full, new File(devDir, 
                    "cacerts/full/target/robovm-cacerts-full-" + Version.getVersion() + ".jar"));
            this.dev = true;
        }

        public boolean isDev() {
            return dev;
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
        
        public File getCacertsPath(Cacerts cacerts) {
            return cacertsPath.get(cacerts);
        }
        
        public static Home find() {
            // Check if ROBOVM_DEV_ROOT has been set. If set it should be pointing
            // at the root of a complete RoboVM source tree.
            if (System.getenv("ROBOVM_DEV_ROOT") != null) {
                File dir = new File(System.getenv("ROBOVM_DEV_ROOT"));
                return validateDevRootDir(dir);
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
                String thisVersion = Version.getVersion();
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

        private static Home validateDevRootDir(File dir) {
            String error = "Path " + dir + " is not a valid RoboVM source tree: ";
            // Check for required dirs.
            if (!dir.exists()) {
                throw new IllegalArgumentException(error + "no such path");
            }
            
            if (!dir.isDirectory()) {
                throw new IllegalArgumentException(error + "not a directory");
            }
            
            File vmBinariesDir = new File(dir, "vm/target/binaries");
            if (!vmBinariesDir.exists() || !vmBinariesDir.isDirectory()) {
                throw new IllegalArgumentException(error + "vm/target/binaries/ missing or invalid");
            }
            File binDir = new File(dir, "bin");
            if (!binDir.exists() || !binDir.isDirectory()) {
                throw new IllegalArgumentException(error + "bin/ missing or invalid");
            }

            String rtJarName = "robovm-rt-" + Version.getVersion() + ".jar";
            File rtJar = new File(dir, "rt/target/" + rtJarName);
            if (!rtJar.exists() || rtJar.isDirectory()) {
                throw new IllegalArgumentException(error 
                        + "rt/target/" + rtJarName + " missing or invalid");
            }

            return new Home(dir, binDir, vmBinariesDir, rtJar);
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

        public Builder executableName(String executableName) {
            config.executableName = executableName;
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

        public Builder debug(boolean b) {
            config.debug = b;
            return this;
        }

        public Builder useDebugLibs(boolean b) {
            config.useDebugLibs = b;
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
        
        public Builder useDynamicJni(boolean b) {
            config.useDynamicJni = b;
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

        public Builder addLib(String lib) {
            config.libs.add(new Lib(lib));
            return this;
        }
        
        public Builder addFramework(String framework) {
            config.frameworks.add(framework);
            return this;
        }
        
        public Builder addResource(File path) {
            config.resources.add(path);
            return this;
        }
        
        public Builder target(Target target) {
            config.target = target;
            return this;
        }
        
        public Builder cacerts(Cacerts cacerts) {
            config.cacerts = cacerts;
            return this;
        }
        
        public Config build() throws IOException {
            return config.build();
        }
        
        public void write(File file) throws Exception {
            Writer writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
                write(writer, file.getAbsoluteFile().getParentFile());
            } finally {
                IOUtils.closeQuietly(writer);
            }
        }
        
        public void write(Writer writer, File wd) throws Exception {
            Registry registry = new Registry();
            RelativeFileConverter fileConverter = new RelativeFileConverter(wd);
            registry.bind(File.class, fileConverter);
            registry.bind(Lib.class, new RelativeLibConverter(fileConverter));
            Serializer serializer = new Persister(new RegistryStrategy(registry));
            serializer.write(config, writer);
        }
    }

    private static final class Lib {
        private final String value;

        public Lib(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    private static final class RelativeLibConverter implements Converter<Lib> {
        private final RelativeFileConverter fileConverter;

        public RelativeLibConverter(RelativeFileConverter fileConverter) {
            this.fileConverter = fileConverter;
        }

        @Override
        public Lib read(InputNode node) throws Exception {
            return null;
        }

        @Override
        public void write(OutputNode node, Lib lib) throws Exception {
            String value = lib.getValue();
            if (value.endsWith(".a") || value.endsWith(".o")) {
                fileConverter.write(node, new File(value));
            } else {
                node.setValue(value);
            }
        }
    }
    
    private static final class RelativeFileConverter implements Converter<File> {
        private final String wdPrefix;
        
        public RelativeFileConverter(File wd) {
            if (wd.isFile()) {
                wd = wd.getParentFile();
            }
            String prefix = wd.getAbsolutePath();
            if (!prefix.endsWith(File.separator)) {
                prefix = prefix + File.separator;
            }
            wdPrefix = prefix;
        }
        
        @Override
        public File read(InputNode node) throws Exception {
            return null;
        }

        @Override
        public void write(OutputNode node, File value) throws Exception {
            String path = value.getAbsolutePath();
            if (path.startsWith(wdPrefix)) {
                node.setValue(path.substring(wdPrefix.length()));
            } else {
                node.setValue(path);
            }
        }
    }
}
