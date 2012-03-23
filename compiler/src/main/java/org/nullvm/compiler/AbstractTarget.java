/**
 * 
 */
package org.nullvm.compiler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.nullvm.compiler.clazz.Path;

/**
 * @author niklas
 *
 */
public abstract class AbstractTarget implements Target {
    protected Config config;

    protected AbstractTarget() {
    }
    
    public String getInstallRelativeArchivePath(Path path) {
        String name = config.getArchiveName(path);
        if (path.isInBootClasspath()) {
            return "lib" + File.separator + "boot" + File.separator + name;
        }
        return "lib" + File.separator + name;
    }
    
    public boolean canLaunchInPlace() {
        return true;
    }
    
    public void build(List<File> objectFiles, Map<String, String> aliases) throws IOException {
        File outFile = new File(config.getTmpDir(), config.getExecutable());
        
        config.getLogger().debug("Building executable %s", outFile);

        String ccPath = (config.getOs().getFamily() == OS.Family.darwin) ? "clang" : "gcc";
        if (config.getCcBinPath() != null) {
            ccPath = config.getCcBinPath().getAbsolutePath();
        }
        
        LinkedList<String> ccArgs = new LinkedList<String>();
        LinkedList<String> libArgs = new LinkedList<String>();
        
        libArgs.addAll(Arrays.asList(
                "-lnullvm-bc", 
                "-Wl,--whole-archive", "-lnullvm-rt", "-Wl,--no-whole-archive", 
                "-lnullvm-core", 
                "-lnullvm-hyprt", 
                "-lnullvm-hythr", 
                "-lnullvm-hypool", 
                "-lnullvm-hycommon", 
                "-lgc",
                "-lpthread", "-ldl", "-lm", "-lz"));
        
        ccArgs.add("-L");
        ccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
        if (config.getOs().getFamily() == OS.Family.linux) {
            // Create a linker script with all aliases
            File aliasFile = new File(config.getTmpDir(), "aliases");
            PrintWriter w = null;
            try {
                w = new PrintWriter(aliasFile, "ASCII");
                for (Entry<String, String> alias : aliases.entrySet()) {
                    w.print(alias.getKey());
                    w.print(" = ");
                    w.print(alias.getValue());
                    w.print(";\n");
                }
            } finally {
                IOUtils.closeQuietly(w);
            }
            ccArgs.add("-Wl,-rpath=$ORIGIN");
            ccArgs.add("-Wl,--script=" + aliasFile.getAbsolutePath());
            ccArgs.add("-Wl,--gc-sections");
//            ccArgs.add("-Wl,--print-gc-sections");
            // This prevents _nvmBcPersonality and _nvmPersonality from being removed by the linker
            // when garbage collecting unreferenced sections.
            ccArgs.add("-Wl,-u,_nvmPersonality");
            ccArgs.add("-Wl,-u,_nvmBcPersonality");
        } else if (config.getOs().getFamily() == OS.Family.darwin) {
            File unexportedSymbolsFile = new File(config.getTmpDir(), "unexported_symbols");
            FileUtils.writeStringToFile(unexportedSymbolsFile, "*\n", "ASCII");
            ccArgs.add("-unexported_symbols_list");
            ccArgs.add(unexportedSymbolsFile.getAbsolutePath());
            
            ccArgs.add("-arch");            
            ccArgs.add(config.getArch().toString());
            ccArgs.add("-Wl,-no_implicit_dylibs");
            // Needed on Mac OS X >= 10.6 to prevent linker from compacting unwind info which breaks _Unwind_FindEnclosingFunction
            ccArgs.add("-Wl,-no_compact_unwind");
        }
     
        doBuild(ccPath, outFile, ccArgs, objectFiles, libArgs);
    }
    
    protected void doBuild(String ccPath, File outFile, List<String> ccArgs, List<File> objectFiles, 
            List<String> libArgs) throws IOException {
        
        File wd = config.getCacheDir();
        String wdAbsPath = wd.getCanonicalPath();
        List<String> relFiles = new ArrayList<String>();
        for (File f : objectFiles) {
            if (f.getCanonicalPath().startsWith(wdAbsPath)) {
                relFiles.add(f.getCanonicalPath().substring(wdAbsPath.length() + 1));
            } else {
                relFiles.add(f.getCanonicalPath());
            }
        }
        
        CompilerUtil.exec(config, wd, ccPath, "-o", outFile, "-g", ccArgs, relFiles, libArgs);        
    }
    
    public void install() throws IOException {
        config.getLogger().debug("Installing executable to %s", config.getInstallDir());
        doInstall(config.getInstallDir());
    }
    
    protected void doInstall(File installDir) throws IOException {
        if (!config.getTmpDir().equals(installDir)) {
            FileUtils.copyFileToDirectory(new File(config.getTmpDir(), config.getExecutable()), installDir);
            new File(installDir, config.getExecutable()).setExecutable(true, false);
        }
        for (File f : config.getOsArchDepLibDir().listFiles()) {
            if (f.getName().matches(".*\\.(so|dylib)(\\.1)?")) {
                FileUtils.copyFileToDirectory(f, installDir);
            }
        }
        stripArchives();
    }

    public int launch(List<String> runArgs) throws IOException {
        if (config.isSkipLinking()) {
            throw new IllegalStateException("Cannot skip linking if target should be run");
        }
        
        return doLaunch(runArgs);
    }

    public Map<String, String> modifyEnv(Map<String, String> defaults) {
        Map<String, String> env = new HashMap<String, String>();
        env.putAll(defaults);
        String ldLibraryPathVar = "LD_LIBRARY_PATH";
        if (config.getOs().getFamily() == OS.Family.darwin) {
            ldLibraryPathVar = "DY" + ldLibraryPathVar;
        }
        String ldLibraryPath = env.get(ldLibraryPathVar);
        ldLibraryPath = ldLibraryPath == null ? "" : ":" + ldLibraryPath;
        env.put(ldLibraryPathVar, config.getOsArchDepLibDir().getAbsolutePath() + ldLibraryPath);
        return env;
    }
    
    protected CommandLine doGenerateCommandLine(List<String> runArgs) {
        File dir = config.getTmpDir();
        if (!config.isSkipInstall()) {
            dir = config.getInstallDir();
        }
        return CompilerUtil.createCommandLine(
                new File(dir, config.getExecutable()).getAbsolutePath(), 
                runArgs.toArray(new Object[runArgs.size()]));
    }
    
    public String[] generateCommandLine(List<String> runArgs) {
        return doGenerateCommandLine(runArgs).toStrings();
    }
    
    @SuppressWarnings({ "unchecked" })
    protected int doLaunch(List<String> runArgs) throws IOException {
        Map<String, String> env = modifyEnv(EnvironmentUtils.getProcEnvironment());
        try {
            return CompilerUtil.execWithEnv(config, new File("."), env, doGenerateCommandLine(runArgs));
        } catch (ExecuteException e) {
            return e.getExitValue();
        }
    }
    
    protected void stripArchives() throws IOException {
        for (Path path : config.getClazzes().getPaths()) {
            File destJar = new File(config.getInstallDir(), getInstallRelativeArchivePath(path));
            if (!destJar.getParentFile().exists()) {
                destJar.getParentFile().mkdirs();
            }
            stripArchive(config.getArchivePath(path), destJar);
        }
    }
    
    protected Target build(Config config) {
        return this;
    }
    
    protected void stripArchive(File input, File output) throws IOException {
        
        if (!config.isClean() && output.exists() && output.lastModified() >= input.lastModified()) {
            config.getLogger().debug("Not stripping unchanged archive file %s", input);
            return;
        }
        
        ZipFile archive = null;
        try {
            archive = new ZipFile(input);
        
            File strippedFile = output;
            config.getLogger().debug("Creating stripped archive file %s", strippedFile);
            
            ZipOutputStream out = null;
            try {
                out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(strippedFile)));
                
                Enumeration<? extends ZipEntry> entries = archive.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().toLowerCase().endsWith(".class")) {
                        continue;
                    }
                    ZipEntry newEntry = new ZipEntry(entry.getName());
                    newEntry.setTime(entry.getTime());
                    out.putNextEntry(newEntry);
                    InputStream in = null;
                    try {
//                        if (!entry.getName().toLowerCase().endsWith(".class")) {
                            in = archive.getInputStream(entry);
//                        } else {
//                            in = new ByteArrayInputStream(new byte[0]);
//                        }
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
}
