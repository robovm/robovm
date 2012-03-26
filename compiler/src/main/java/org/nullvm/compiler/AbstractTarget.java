/**
 * 
 */
package org.nullvm.compiler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        
        LinkedList<String> ccArgs = new LinkedList<String>();
        LinkedList<String> libs = new LinkedList<String>();
        
        libs.add("-lnullvm-bc"); 
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-lnullvm-rt");
        } else {
            libs.addAll(Arrays.asList("-Wl,--whole-archive", "-lnullvm-rt", "-Wl,--no-whole-archive"));            
        }
        libs.addAll(Arrays.asList(
                "-lnullvm-core", 
                "-lnullvm-hyprt", 
                "-lnullvm-hythr", 
                "-lnullvm-hypool", 
                "-lnullvm-hycommon", 
                "-lgc",
                "-lpthread", "-ldl", "-lm", "-lz"));
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-liconv");
        }
        
        ccArgs.add("-L");
        ccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
        File aliasFile = new File(config.getTmpDir(), "aliases");
        if (config.getOs().getFamily() == OS.Family.linux) {
            // Create a linker script with all aliases
            createGnuLdAliasFile(aliasFile, aliases);
            ccArgs.add("-Wl,--script=" + aliasFile.getAbsolutePath());
            ccArgs.add("-Wl,-rpath=$ORIGIN");
            ccArgs.add("-Wl,--gc-sections");
//            ccArgs.add("-Wl,--print-gc-sections");
            // This prevents _nvmBcPersonality and _nvmPersonality from being removed by the linker
            // when garbage collecting unreferenced sections.
            ccArgs.add("-Wl,-u,_nvmPersonality");
            ccArgs.add("-Wl,-u,_nvmBcPersonality");
        } else if (config.getOs().getFamily() == OS.Family.darwin) {
            ccArgs.add("-arch");            
            ccArgs.add(config.getArch().toString());
            File unexportedSymbolsFile = new File(config.getTmpDir(), "unexported_symbols");
            FileUtils.writeStringToFile(unexportedSymbolsFile, "*\n", "ASCII");
            ccArgs.add("-unexported_symbols_list");
            ccArgs.add(unexportedSymbolsFile.getAbsolutePath());
            createMacLdAliasFile(aliasFile, aliases);
            //ccArgs.add("-Wl,-alias_list," + aliasFile.getAbsolutePath());
            ccArgs.add("-Wl,-no_implicit_dylibs");
            // Needed on Mac OS X >= 10.6 to prevent linker from compacting unwind info which breaks _Unwind_FindEnclosingFunction
            ccArgs.add("-Wl,-no_compact_unwind");
            ccArgs.add("-Wl,-dead_strip");
        }
     
        doBuild(outFile, ccArgs, objectFiles, libs);
    }
    
    private void createGnuLdAliasFile(File aliasFile, Map<String, String> aliases) throws IOException {
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
    }
    
    private void createMacLdAliasFile(File aliasFile, Map<String, String> aliases) throws IOException {
        PrintWriter w = null;
        try {
            w = new PrintWriter(aliasFile, "ASCII");
            for (Entry<String, String> alias : aliases.entrySet()) {
                w.print("_");
                w.print(alias.getValue());
                w.print(" _");
                w.print(alias.getKey());
                w.print("\n");
            }
        } finally {
            IOUtils.closeQuietly(w);
        }
    }
    
    protected void doBuild(File outFile, List<String> ccArgs, List<File> objectFiles, 
            List<String> libs) throws IOException {
        
        CompilerUtil.link(config, ccArgs, objectFiles, libs, outFile);        
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
