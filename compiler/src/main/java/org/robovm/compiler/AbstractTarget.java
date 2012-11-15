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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.robovm.compiler.clazz.Path;

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
    
    public void build(List<File> objectFiles) throws IOException {
        File outFile = new File(config.getTmpDir(), config.getExecutable());
        
        config.getLogger().debug("Building executable %s", outFile);
        
        LinkedList<String> ccArgs = new LinkedList<String>();
        LinkedList<String> libs = new LinkedList<String>();
        
        libs.add("-lrobovm-bc"); 
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-force_load");
            libs.add(new File(config.getOsArchDepLibDir(), "librobovm-rt.a").getAbsolutePath());
        } else {
            libs.addAll(Arrays.asList("-Wl,--whole-archive", "-lrobovm-rt", "-Wl,--no-whole-archive"));            
        }
        libs.addAll(Arrays.asList(
                "-lrobovm-core", "-lgc", "-lpthread", "-ldl", "-lm", "-lstdc++"));
        if (config.getOs().getFamily() == OS.Family.linux) {
            libs.add("-lrt");
        }
        if (config.getOs().getFamily() == OS.Family.darwin) {
            libs.add("-liconv");
        }
        
        ccArgs.add("-L");
        ccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
        if (config.getOs().getFamily() == OS.Family.linux) {
            // Create a linker script with all aliases
            ccArgs.add("-Wl,-rpath=$ORIGIN");
            ccArgs.add("-Wl,--gc-sections");
//            ccArgs.add("-Wl,--print-gc-sections");
        } else if (config.getOs().getFamily() == OS.Family.darwin) {
            File unexportedSymbolsFile = new File(config.getTmpDir(), "unexported_symbols");
            FileUtils.writeStringToFile(unexportedSymbolsFile, "*\n", "ASCII");
            ccArgs.add("-unexported_symbols_list");
            ccArgs.add(unexportedSymbolsFile.getAbsolutePath());
            ccArgs.add("-Wl,-no_implicit_dylibs");
            ccArgs.add("-Wl,-dead_strip");
        }
     
        doBuild(outFile, ccArgs, objectFiles, libs);
    }
    
    protected void doBuild(File outFile, List<String> ccArgs, List<File> objectFiles, 
            List<String> libs) throws IOException {
        
        CompilerUtil.link(config, ccArgs, objectFiles, libs, outFile);        
    }
    
    public void install() throws IOException {
        config.getLogger().debug("Installing executable to %s", config.getInstallDir());
        config.getInstallDir().mkdirs();
        doInstall(config.getInstallDir(), config.getExecutable());
    }
    
    protected void doInstall(File installDir, String executable) throws IOException {
        if (!config.getTmpDir().equals(installDir) || !executable.equals(config.getExecutable())) {
            File destFile = new File(installDir, executable);
            FileUtils.copyFile(new File(config.getTmpDir(), config.getExecutable()), destFile);
            destFile.setExecutable(true, false);
        }
        for (File f : config.getOsArchDepLibDir().listFiles()) {
            if (f.getName().matches(".*\\.(so|dylib)(\\.1)?")) {
                FileUtils.copyFileToDirectory(f, installDir);
            }
        }
        stripArchives(installDir);
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
                new File(dir, config.getExecutable()).getAbsolutePath(), 
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
        for (Path path : config.getClazzes().getPaths()) {
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
