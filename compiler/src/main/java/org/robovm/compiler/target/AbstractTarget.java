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
package org.robovm.compiler.target;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.CompilerUtil;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.OS.Family;
import org.robovm.compiler.log.DebugOutputStream;
import org.robovm.compiler.log.ErrorOutputStream;
import org.robovm.compiler.util.AsyncExecutor;
import org.simpleframework.xml.Transient;

/**
 * @author niklas
 *
 */
public abstract class AbstractTarget implements Target {
    @Transient
    protected Config config;

    protected AbstractTarget() {
    }
    
    @Override
    public void init(Config config) {
        this.config = config;
    }
    
    @Override
    public LaunchParameters createLaunchParameters() {
        return new LaunchParameters();
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
    
    public void build(List<File> objectFiles) throws IOException {
        File outFile = new File(config.getTmpDir(), config.getExecutableName());
        
        config.getLogger().debug("Building executable %s", outFile);
        
        LinkedList<String> ccArgs = new LinkedList<String>();
        LinkedList<String> libs = new LinkedList<String>();
        
        String libSuffix = config.isUseDebugLibs() ? "-dbg" : "";
        
        libs.add("-lrobovm-bc" + libSuffix); 
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-force_load");
            libs.add(new File(config.getOsArchDepLibDir(), "librobovm-rt" + libSuffix + ".a").getAbsolutePath());
        } else {
            libs.addAll(Arrays.asList("-Wl,--whole-archive", "-lrobovm-rt" + libSuffix, "-Wl,--no-whole-archive"));            
        }
        libs.addAll(Arrays.asList(
                "-lrobovm-core" + libSuffix, "-lgc" + libSuffix, "-lpthread", "-ldl", "-lm", "-lstdc++"));
        if (config.getOs().getFamily() == OS.Family.linux) {
            libs.add("-lrt");
        }
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-liconv");
            libs.add("-lsqlite3");
            libs.add("-framework");
            libs.add("Foundation");
        }
        
        ccArgs.add("-L");
        ccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
        if (config.getOs().getFamily() == OS.Family.linux) {
            // Create a linker script with all aliases
            ccArgs.add("-Wl,-rpath=$ORIGIN");
            ccArgs.add("-Wl,--gc-sections");
//            ccArgs.add("-Wl,--print-gc-sections");
        } else if (config.getOs().getFamily() == OS.Family.darwin) {
            File exportedSymbolsFile = new File(config.getTmpDir(), "exported_symbols");
            FileUtils.writeStringToFile(exportedSymbolsFile, "_catch_exception_raise\n", "ASCII");
            ccArgs.add("-exported_symbols_list");
            ccArgs.add(exportedSymbolsFile.getAbsolutePath());
            ccArgs.add("-Wl,-no_implicit_dylibs");
            ccArgs.add("-Wl,-dead_strip");
        }
        
        if (config.getOs().getFamily() == OS.Family.darwin && !config.getFrameworks().isEmpty()) {
            for (String p : config.getFrameworks()) {
                libs.add("-framework");
                libs.add(p);
            }
        }
        
        if (!config.getLibs().isEmpty()) {
            objectFiles = new ArrayList<File>(objectFiles);
            for (String p : config.getLibs()) {
                if (p.endsWith(".o")) {
                    objectFiles.add(new File(p));
                } else if(p.endsWith(".a")) {
                    // .a file
                    if (config.getOs().getFamily() == OS.Family.darwin) {
                        libs.add("-force_load");
                        libs.add(new File(p).getAbsolutePath());
                    } else {
                        libs.addAll(Arrays.asList("-Wl,--whole-archive", new File(p).getAbsolutePath(), "-Wl,--no-whole-archive"));            
                    }
                } else {
                    // link via -l if suffix is omitted
                    libs.add("-l" + p);
                }
            }
        }
     
        doBuild(outFile, ccArgs, objectFiles, libs);
    }
    
    protected void doBuild(File outFile, List<String> ccArgs, List<File> objectFiles, 
            List<String> libs) throws IOException {
        
        CompilerUtil.link(config, ccArgs, objectFiles, libs, outFile);        
    }
    
    protected void copyResources(File destDir) throws IOException {
        for (File f : config.getResources()) {
            if (!f.exists()) {
                config.getLogger().warn("Resource %s not found", f);
            } else if (f.isDirectory()) {
                config.getLogger().debug("Copying resource dir %s to %s", f, destDir);
                FileUtils.copyDirectory(f, new File(destDir, f.getName()));
            } else {
                config.getLogger().debug("Copying resource %s to %s", f, destDir);
                FileUtils.copyFileToDirectory(f, destDir, true);
            }
        }
    }
    
    public void install() throws IOException {
        config.getLogger().debug("Installing executable to %s", config.getInstallDir());
        config.getInstallDir().mkdirs();
        doInstall(config.getInstallDir(), config.getExecutableName());
    }
    
    protected void doInstall(File installDir, String executable) throws IOException {
        if (!config.getTmpDir().equals(installDir) || !executable.equals(config.getExecutableName())) {
            File destFile = new File(installDir, executable);
            FileUtils.copyFile(new File(config.getTmpDir(), config.getExecutableName()), destFile);
            destFile.setExecutable(true, false);
        }
        for (File f : config.getOsArchDepLibDir().listFiles()) {
            if (f.getName().matches(".*\\.(so|dylib)(\\.1)?")) {
                FileUtils.copyFileToDirectory(f, installDir);
            }
        }
        stripArchives(installDir);
        copyResources(installDir);
    }

    public Process launch(LaunchParameters launchParameters) throws IOException {
        if (config.isSkipLinking()) {
            throw new IllegalStateException("Cannot skip linking if target should be run");
        }
        
        return doLaunch(launchParameters);
    }
    
    protected CommandLine doGenerateCommandLine(LaunchParameters launchParameters) throws IOException {
        File dir = config.getTmpDir();
        if (!config.isSkipInstall()) {
            dir = config.getInstallDir();
        }
        return CompilerUtil.createCommandLine(
                new File(dir, config.getExecutableName()).getAbsolutePath(), 
                launchParameters.getArguments());
    }
    
    @SuppressWarnings("unchecked")
    protected Process doLaunch(LaunchParameters launchParameters) throws IOException {
        CommandLine commandLine = doGenerateCommandLine(launchParameters);
        
        CompilerUtil.debug(config.getLogger(), commandLine);
            
        AsyncExecutor executor = new AsyncExecutor();
        executor.setWorkingDirectory(launchParameters.getWorkingDirectory());
        executor.setExitValue(0);
        initStreams(executor, launchParameters);
        Map<String, String> env = launchParameters.getEnvironment();
        if (env == null) {
            env = EnvironmentUtils.getProcEnvironment();
        }
        return executor.execute(commandLine, env);
    }
    
    protected void initStreams(AsyncExecutor executor, LaunchParameters launchParameters) throws IOException {
        if (launchParameters.isRedirectStreamsToLogger()) {
            executor.setStreamHandler(
                new PumpStreamHandler(
                    new DebugOutputStream(config.getLogger()), 
                    new ErrorOutputStream(config.getLogger())));
        }        
    }
    
    protected Target build(Config config) {
        return this;
    }
    
    protected void stripArchives(File installDir) throws IOException {
        List<Path> allPaths = new ArrayList<Path>();
        allPaths.addAll(config.getClazzes().getPaths());
        allPaths.addAll(config.getResourcesPaths());
        for (Path path : allPaths) {
            File destJar = new File(installDir, getInstallRelativeArchivePath(path));
            if (!destJar.getParentFile().exists()) {
                destJar.getParentFile().mkdirs();
            }
            stripArchive(path, destJar);
        }
    }
    
    protected void stripArchive(Path path, File output) throws IOException {
        
        if (!config.isClean() && output.exists() && !path.hasChangedSince(output.lastModified())) {
            config.getLogger().debug("Not creating stripped archive file %s for unchanged path %s", 
                    output, path.getFile());
            return;
        }
        
        config.getLogger().debug("Creating stripped archive file %s", output);

        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)));

            if (path.getFile().isFile()) {
                ZipFile archive = null;
                try {
                    archive = new ZipFile(path.getFile());
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
                            in = archive.getInputStream(entry);
                            IOUtils.copy(in, out);
                            out.closeEntry();
                        } finally {
                            IOUtils.closeQuietly(in);
                        }
                    }
                } finally {
                    try {
                        archive.close();
                    } catch (Throwable t) {}
                }
            } else {
                String basePath = path.getFile().getAbsolutePath();
                @SuppressWarnings("unchecked")
                Collection<File> files = FileUtils.listFiles(path.getFile(), null, true);
                for (File f : files) {
                    if (f.getName().toLowerCase().endsWith(".class")) {
                        continue;
                    }
                    ZipEntry newEntry = new ZipEntry(f.getAbsolutePath().substring(basePath.length() + 1));
                    newEntry.setTime(f.lastModified());
                    out.putNextEntry(newEntry);
                    InputStream in = null;
                    try {
                        in = new FileInputStream(f);
                        IOUtils.copy(in, out);
                        out.closeEntry();
                    } finally {
                        IOUtils.closeQuietly(in);
                    }
                }
            }
        } catch (IOException e) {
            IOUtils.closeQuietly(out);
            output.delete();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
