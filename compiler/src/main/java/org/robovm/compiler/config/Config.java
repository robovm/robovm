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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.robovm.compiler.ITable;
import org.robovm.compiler.VTable;
import org.robovm.compiler.Version;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.clazz.Clazzes;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.target.ConsoleTarget;
import org.robovm.compiler.target.Target;
import org.robovm.compiler.target.ios.IOSTarget;
import org.robovm.compiler.target.ios.ProvisioningProfile;
import org.robovm.compiler.target.ios.SigningIdentity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * @author niklas
 *
 */
@Root
public class Config {
    
    public enum Cacerts { full };
    public enum TargetType { console, ios };
    
    @Element(required = false)
    private File installDir = null;
    @Element(required = false)
    private String executableName = null;
    @Element(required = false)
    private Boolean useDynamicJni = null;
    @Element(required = false)
    private Boolean skipRuntimeLib = null;
    @Element(required = false)
    private File mainJar;
    @Element(required = false)
    private String mainClass;
    @Element(required = false)
    private Cacerts cacerts = null;
    @Element(required = false)
    private OS os = null;
    @Element(required = false)
    private Arch arch = null;
    @ElementList(required = false, entry = "root")
    private ArrayList<String> roots;
    @ElementList(required = false, entry = "pattern")
    private ArrayList<String> forceLinkClasses;
    @ElementList(required = false, entry = "lib")
    private ArrayList<Lib> libs;
    @ElementList(required = false, entry = "symbol")
    private ArrayList<String> exportedSymbols;
    @ElementList(required = false, entry = "framework")
    private ArrayList<String> frameworks;
    @ElementList(required = false, entry = "framework")
    private ArrayList<String> weakFrameworks;
    @ElementList(required = false, entry = "resource")
    private ArrayList<Resource> resources;
    @ElementList(required = false, entry = "classpathentry")
    private ArrayList<File> bootclasspath;
    @ElementList(required = false, entry = "classpathentry")
    private ArrayList<File> classpath;
    @Element(required = false, name = "target")
    private TargetType targetType;
    
    @Element(required = false)
    private String iosSdkVersion;
    @Element(required = false)
    private File iosInfoPList = null;
    @Element(required = false)
    private File iosResourceRulesPList;
    @Element(required = false)
    private File iosEntitlementsPList;

    private SigningIdentity iosSignIdentity;
    private ProvisioningProfile iosProvisioningProfile;
    
    private Target target = null;
    private Properties properties = new Properties();
    
    private Home home = null;
    private File cacheDir = new File(System.getProperty("user.home"), ".robovm/cache");
    private File ccBinPath = null;
    
    private boolean clean = false;
    private boolean debug = true;
    private boolean useDebugLibs = false;
    private boolean skipLinking = false;
    private boolean skipInstall = false;
    
    private File osArchDepLibDir;
    private File tmpDir;
    private Clazzes clazzes;
    private VTable.Cache vtableCache;
    private ITable.Cache itableCache;
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
        return os;
    }

    public Arch getArch() {
        return arch;
    }

    public String getTriple() {
        return arch.getLlvmName() + "-unknown-" + os;
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
        return skipRuntimeLib != null && skipRuntimeLib.booleanValue();
    }

    public boolean isSkipLinking() {
        return skipLinking;
    }

    public boolean isSkipInstall() {
        return skipInstall;
    }
    
    public boolean isUseDynamicJni() {
        return useDynamicJni != null && useDynamicJni.booleanValue();
    }
    
    public File getMainJar() {
        return mainJar;
    }

    public String getMainClass() {
        return mainClass;
    }

    public Cacerts getCacerts() {
        return cacerts == null ? Cacerts.full : cacerts;
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

    public List<String> getForceLinkClasses() {
        return forceLinkClasses == null ? Collections.<String>emptyList() 
                : Collections.unmodifiableList(forceLinkClasses);
    }
    
    public List<String> getExportedSymbols() {
        return exportedSymbols == null ? Collections.<String>emptyList() 
                : Collections.unmodifiableList(exportedSymbols);
    }
    
    public List<String> getLibs() {
        List<String> result = new ArrayList<String>();
        if (libs != null) {
            for (Lib lib : libs) {
                result.add(lib.getValue());
            }
        }
        return Collections.unmodifiableList(result);
    }
    
    public List<String> getFrameworks() {
        return frameworks == null ? Collections.<String>emptyList() 
                : Collections.unmodifiableList(frameworks);
    }
    
    public List<String> getWeakFrameworks() {
        return weakFrameworks == null ? Collections.<String>emptyList() 
                : Collections.unmodifiableList(weakFrameworks);
    }
    
    public List<Resource> getResources() {
        return resources == null ? Collections.<Resource>emptyList() 
                : Collections.unmodifiableList(resources);
    }
    
    public File getOsArchDepLibDir() {
        return osArchDepLibDir;
    }
    
    public Clazzes getClazzes() {
        return clazzes;
    }

    public VTable.Cache getVTableCache() {
        return vtableCache;
    }
    
    public ITable.Cache getITableCache() {
        return itableCache;
    }
    
    public List<File> getBootclasspath() {
        return bootclasspath;
    }
    
    public List<File> getClasspath() {
        return classpath;
    }
    
    public Properties getProperties() {
        return properties;
    }
    
    public Logger getLogger() {
        return logger;
    }
    
    public Target getTarget() {
        return target;
    }
    
    public String getIosSdkVersion() {
        return iosSdkVersion;
    }

    public File getIosInfoPList() {
        return iosInfoPList;
    }

    public File getIosResourceRulesPList() {
        return iosResourceRulesPList;
    }

    public File getIosEntitlementsPList() {
        return iosEntitlementsPList;
    }

    public SigningIdentity getIosSignIdentity() {
        return iosSignIdentity;
    }

    public ProvisioningProfile getIosProvisioningProfile() {
        return iosProvisioningProfile;
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
    
    public File getInfoFile(Clazz clazz) {
        String baseName = clazz.getInternalName().replace('/', File.separatorChar);
        return new File(getCacheDir(clazz.getPath()), baseName + ".class.info");
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
        
        if (bootclasspath == null) {
            bootclasspath = new ArrayList<File>();
        }
        if (classpath == null) {
            classpath = new ArrayList<File>();
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

        List<File> realBootclasspath = bootclasspath == null ? new ArrayList<File>() : bootclasspath;
        if (!isSkipRuntimeLib()) {
            realBootclasspath = new ArrayList<File>(bootclasspath);
            realBootclasspath.add(0, home.rtPath);
        }

        this.clazzes = new Clazzes(this, realBootclasspath, classpath);
        this.vtableCache = new VTable.Cache();
        this.itableCache = new ITable.Cache();
        
        if (!skipInstall) {
            if (installDir == null) {
                installDir = new File(".", executableName);
            }
            installDir.mkdirs();
        }

        if (targetType == TargetType.console) {
            target = new ConsoleTarget();
        } else if (targetType == TargetType.ios) {
            target = new IOSTarget();
        } else {
            // Auto
            if (os == OS.ios) {
                target = new IOSTarget();
            } else {
                target = new ConsoleTarget();
            }
        }
        target.init(this);
        
        os = target.getOs();
        arch = target.getArch();
        
        osArchDepLibDir = new File(new File(home.libVmDir, os.toString()), 
                arch.toString());
        
        File osDir = new File(cacheDir, os.toString());
        File archDir = new File(osDir, arch.toString());
        cacheDir = new File(archDir, "default");
        cacheDir.mkdirs();
        
        return this;
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
            File rtJarFile = new File(libDir, "robovm-rt.jar");
            if (!rtJarFile.exists() || !rtJarFile.isFile()) {
                throw new IllegalArgumentException(error 
                        + "lib/robovm-rt.jar missing or invalid");
            }
            
            // Compare the version of this compiler with the version of the
            // robovm-rt.jar in the home dir. They have to match.
            try {
                String thisVersion = Version.getVersion();
                String thatVersion = getImplementationVersion(rtJarFile);
                if (thisVersion == null || thatVersion == null || !thisVersion.equals(thatVersion)) {
                    throw new IllegalArgumentException(error + "version mismatch (expected: " 
                            + thisVersion + ", was: " + thatVersion + ")");
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(error 
                        + "failed to get version of rt jar", e);
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
            File rtClasses = new File(dir, "rt/target/classes/");
            File rtSource = rtJar;
            if (!rtJar.exists() || rtJar.isDirectory()) {
            	if(!rtClasses.exists() || rtClasses.isFile()) {
                throw new IllegalArgumentException(error 
                        + "rt/target/" + rtJarName + " missing or invalid");
            	} else {
            		rtSource = rtClasses;
            	}
            }

            return new Home(dir, binDir, vmBinariesDir, rtSource);
        }
    }
    
    public static class Builder {
        final Config config;
        
        public Builder() {
            this.config = new Config();
        }
        
        public Builder os(OS os) {
            config.os = os;
            return this;
        }
        
        public Builder arch(Arch arch) {
            config.arch = arch;
            return this;
        }
        
        public Builder clearClasspathEntries() {
            if (config.classpath != null) {
                config.classpath.clear();
            }
            return this;
        }

        public Builder addClasspathEntry(File f) {
            if (config.classpath == null) {
                config.classpath = new ArrayList<File>();
            }
            config.classpath.add(f);
            return this;
        }
        
        public Builder clearBootClasspathEntries() {
            if (config.bootclasspath != null) {
                config.bootclasspath.clear();
            }
            return this;
        }

        public Builder addBootClasspathEntry(File f) {
            if (config.bootclasspath == null) {
                config.bootclasspath = new ArrayList<File>();
            }
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

        public Builder clearForceLinkClasses() {
            if (config.forceLinkClasses != null) {
                config.forceLinkClasses.clear();
            }
            return this;
        }

        public Builder addForceLinkClass(String pattern) {
            if (config.forceLinkClasses == null) {
                config.forceLinkClasses = new ArrayList<String>();
            }
            config.forceLinkClasses.add(pattern);
            return this;
        }

        public Builder clearExportedSymbols() {
            if (config.exportedSymbols != null) {
                config.exportedSymbols.clear();
            }
            return this;
        }

        public Builder addExportedSymbol(String symbol) {
            if (config.exportedSymbols == null) {
                config.exportedSymbols = new ArrayList<String>();
            }
            config.exportedSymbols.add(symbol);
            return this;
        }
        
        public Builder clearLibs() {
            if (config.libs != null) {
                config.libs.clear();
            }
            return this;
        }

        public Builder addLib(String lib) {
            if (config.libs == null) {
                config.libs = new ArrayList<Lib>();
            }
            config.libs.add(new Lib(lib));
            return this;
        }
        
        public Builder clearFrameworks() {
            if (config.frameworks != null) {
                config.frameworks.clear();
            }
            return this;
        }
        
        public Builder addFramework(String framework) {
            if (config.frameworks == null) {
                config.frameworks = new ArrayList<String>();
            }
            config.frameworks.add(framework);
            return this;
        }

        public Builder clearWeakFrameworks() {
            if (config.weakFrameworks != null) {
                config.weakFrameworks.clear();
            }
            return this;
        }
        
        public Builder addWeakFramework(String framework) {
            if (config.weakFrameworks == null) {
                config.weakFrameworks = new ArrayList<String>();
            }
            config.weakFrameworks.add(framework);
            return this;
        }

        public Builder clearResources() {
            if (config.resources != null) {
                config.resources.clear();
            }
            return this;
        }
        
        public Builder addResource(Resource resource) {
            if (config.resources == null) {
                config.resources = new ArrayList<Resource>();
            }
            config.resources.add(resource);
            return this;
        }

        public Builder targetType(TargetType targetType) {
            config.targetType = targetType;
            return this;
        }
        
        public Builder clearProperties() {
            config.properties.clear();
            return this;
        }
        
        public Builder addProperties(Properties properties) {
            config.properties.putAll(properties);
            return this;
        }
        
        public Builder addProperties(File file) throws IOException {
            Properties props = new Properties();
            Reader reader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(file), "utf-8");
                props.load(reader);
                addProperties(props);
            } finally {
                IOUtils.closeQuietly(reader);
            }
            return this;
        }
        
        public Builder addProperty(String name, String value) {
            config.properties.put(name, value);
            return this;
        }
        
        public Builder cacerts(Cacerts cacerts) {
            config.cacerts = cacerts;
            return this;
        }
        
        public Builder iosSdkVersion(String sdkVersion) {
            config.iosSdkVersion = sdkVersion;
            return this;
        }
        
        public Builder iosInfoPList(File infoPList) {
            config.iosInfoPList = infoPList;
            return this;
        }
        
        public Builder iosEntitlementsPList(File entitlementsPList) {
            config.iosEntitlementsPList = entitlementsPList;
            return this;
        }

        public Builder iosResourceRulesPList(File resourceRulesPList) {
            config.iosResourceRulesPList = resourceRulesPList;
            return this;
        }
        
        public Builder iosSignIdentity(SigningIdentity signIdentity) {
            config.iosSignIdentity = signIdentity;
            return this;
        }

        public Builder iosProvisioningProfile(ProvisioningProfile iosProvisioningProfile) {
            config.iosProvisioningProfile = iosProvisioningProfile;
            return this;
        }

        public Config build() throws IOException {
            return config.build();
        }
        
        public void read(File file) throws Exception {
            Reader reader = null;
            try {
                reader = new InputStreamReader(new FileInputStream(file), "utf-8");
                read(reader, file.getAbsoluteFile().getParentFile());
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        
        public void read(Reader reader, File wd) throws Exception {
            Serializer serializer = createSerializer(wd);
            serializer.read(config, reader);
            // <roots> was renamed to <forceLinkClasses> but we still support <roots>. We need to
            // copy <roots> to <forceLinkClasses> and set <roots> to null.
            if (config.roots != null && !config.roots.isEmpty()) {
                if (config.forceLinkClasses == null) {
                    config.forceLinkClasses = new ArrayList<String>();
                }
                config.forceLinkClasses.addAll(config.roots);
                config.roots = null;
            }
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
            Serializer serializer = createSerializer(wd);
            serializer.write(config, writer);
        }

        private Serializer createSerializer(final File wd) throws Exception {
            RelativeFileConverter fileConverter = new RelativeFileConverter(wd);
            
            Serializer resourceSerializer = new Persister(
                    new RegistryStrategy(new Registry().bind(File.class, fileConverter)), 
                    new PlatformFilter(config.properties), new Format(2));
            
            Registry registry = new Registry();
            RegistryStrategy registryStrategy = new RegistryStrategy(registry);
            Serializer serializer = new Persister(registryStrategy, 
                    new PlatformFilter(config.properties), new Format(2));


            registry.bind(File.class, fileConverter);
            registry.bind(Lib.class, new RelativeLibConverter(fileConverter));
            registry.bind(Resource.class, new ResourceConverter(fileConverter, resourceSerializer));
            
            return serializer;
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
            String value = node.getValue();
            if (value == null) {
                return null;
            }
            if (value.endsWith(".a") || value.endsWith(".o")) {
                return new Lib(fileConverter.read(value).getAbsolutePath());
            } else {
                return new Lib(value);
            }
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
            if (prefix.endsWith(File.separator)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            wdPrefix = prefix;
        }
        
        File read(String value) {
            if (value == null) {
                return null;
            }
            File file = new File(value);
            if (!file.isAbsolute()) {
                file = new File(wdPrefix, value);
            }
            return file;
        }
        
        @Override
        public File read(InputNode node) throws Exception {
            return read(node.getValue());
        }

        @Override
        public void write(OutputNode node, File value) throws Exception {
            String path = value.getAbsolutePath();
            if (path.equals(wdPrefix)) {
                if ("directory".equals(node.getName())) {
                    // Skip
                    node.remove();
                } else {
                    node.setValue("");
                }
            } else if (path.startsWith(wdPrefix) && path.charAt(wdPrefix.length()) == File.separatorChar) {
                node.setValue(path.substring(wdPrefix.length() + 1));
            } else {
                node.setValue(path);
            }
        }
    }
    
    private static final class ResourceConverter implements Converter<Resource> {
        private final RelativeFileConverter fileConverter;
        private final Serializer serializer;

        public ResourceConverter(RelativeFileConverter fileConverter, Serializer serializer) {
            this.fileConverter = fileConverter;
            this.serializer = serializer;
        }

        @Override
        public Resource read(InputNode node) throws Exception {
            String value = node.getValue();
            if (value != null && value.trim().length() > 0) {
                return new Resource(fileConverter.read(value));
            }
            return serializer.read(Resource.class, node);
        }

        @Override
        public void write(OutputNode node, Resource resource) throws Exception {
            File path = resource.getPath();
            if (path != null) {
                fileConverter.write(node, path);
            } else {
                node.remove();
                serializer.write(resource, node.getParent());
            }
        }
    }
}
