/**
 * 
 */
package org.nullvm.compiler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ConsoleApp implements App {
    private Config config;

    ConsoleApp() {
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
    
    public void install() throws IOException {
        config.getLogger().debug("Installing executable to %s", config.getInstallDir());
        install(config.getInstallDir());
    }
    
    protected void install(File installDir) throws IOException {
        if (!config.getTmpDir().equals(installDir)) {
            FileUtils.copyFileToDirectory(new File(config.getTmpDir(), config.getTarget()), installDir);
            new File(installDir, config.getTarget()).setExecutable(true, false);
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
        
        return runTarget(runArgs);
    }

    public Map<String, String> modifyEnv(Map<String, String> defaults) {
        Map<String, String> env = new HashMap<String, String>();
        env.putAll(defaults);
        String ldLibraryPathVar = "LD_LIBRARY_PATH";
        if (config.getOs() == OS.darwin) {
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
                new File(dir, config.getTarget()).getAbsolutePath(), 
                runArgs.toArray(new Object[runArgs.size()]));
    }
    
    public String[] generateCommandLine(List<String> runArgs) {
        return doGenerateCommandLine(runArgs).toStrings();
    }
    
    @SuppressWarnings({ "unchecked" })
    private int runTarget(List<String> runArgs) throws IOException {
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
    
    protected App build(Config config) {
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
    
    public static class Builder implements App.Builder {
        private ConsoleApp app = new ConsoleApp();

        public void setup(Config.Builder configBuilder) {
        }
        
        public App build(Config config) {
            app.config = config;
            return app.build(config);
        }
        
    }
}
