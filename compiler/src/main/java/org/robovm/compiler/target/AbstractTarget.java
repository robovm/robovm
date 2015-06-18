/*
 * Copyright (C) 2012 RoboVM AB
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
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.Config.TargetBinary;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.config.Resource;
import org.robovm.compiler.config.Resource.Walker;
import org.robovm.compiler.util.ToolchainUtil;
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
        final TargetBinary targetBinary = config.getTargetBinary();
        String binaryName = config.getBinaryName();
        final boolean isOSX = (config.getOs() == OS.macosx);
        final boolean isIOS = (config.getOs() == OS.ios);
        final boolean isDarwin = (config.getOs().getFamily() == OS.Family.darwin);
        final boolean isLinux  = (config.getOs().getFamily() == OS.Family.linux);
        
      	config.getLogger().debug("Building binary %s", binaryName);
        
      	File outFile = new File(config.getTmpDir(), binaryName);
        
        LinkedList<String> ccArgs = new LinkedList<String>();
        LinkedList<String> libs = new LinkedList<String>();
        
        String libSuffix = config.isUseDebugLibs() ? "-dbg" : "";
        
        libs.add("-lrobovm-bc" + libSuffix); 
        if (isDarwin) {
            libs.add("-force_load");
            libs.add(new File(config.getOsArchDepLibDir(), "librobovm-rt" + libSuffix + ".a").getAbsolutePath());
        } else {
            libs.addAll(Arrays.asList("-Wl,--whole-archive", "-lrobovm-rt" + libSuffix, "-Wl,--no-whole-archive"));            
        }
            if (config.isSkipInstall()) {
            libs.add("-lrobovm-debug" + libSuffix);
            }
            libs.addAll(Arrays.asList(
                "-lrobovm-core" + libSuffix, "-lgc" + libSuffix, "-lpthread", "-ldl", "-lm"));
        if (isLinux) {
            libs.add("-lrt");
        }
        if (isDarwin) {
            libs.add("-liconv");
            libs.add("-lsqlite3");
            libs.add("-framework");
            libs.add("Foundation");
        }
        
        ccArgs.add("-L");
        ccArgs.add(config.getOsArchDepLibDir().getAbsolutePath());
        if (config.getOs().getFamily() == OS.Family.linux) {
            ccArgs.add("-Wl,-rpath=$ORIGIN");
            ccArgs.add("-Wl,--gc-sections");
            //ccArgs.add("-Wl,--print-gc-sections");
        } else if (config.getOs().getFamily() == OS.Family.darwin) {
            ccArgs.add("-ObjC");
            File exportedSymbolsFile = new File(config.getTmpDir(), "exported_symbols");
            List<String> exportedSymbols = new ArrayList<String>();
            if (config.isSkipInstall()) {
                exportedSymbols.add("catch_exception_raise");
            }
            exportedSymbols.addAll(config.getExportedSymbols());
            for (int i = 0; i < exportedSymbols.size(); i++) {
                // On Darwin symbols are always prefixed with a '_'. We'll prepend
                // '_' to each symbol here so the user won't have to.
                exportedSymbols.set(i, "_" + exportedSymbols.get(i));
            }
            FileUtils.writeLines(exportedSymbolsFile, "ASCII", exportedSymbols);
            ccArgs.add("-exported_symbols_list");
            ccArgs.add(exportedSymbolsFile.getAbsolutePath());
            ccArgs.add("-Wl,-no_implicit_dylibs");
            ccArgs.add("-Wl,-dead_strip");
        }
        
        if (isDarwin && !config.getFrameworks().isEmpty()) {
            for (String p : config.getFrameworks()) {
                libs.add("-framework");
                libs.add(p);
            }
        }
        if (isDarwin && !config.getWeakFrameworks().isEmpty()) {
            for (String p : config.getWeakFrameworks()) {
                libs.add("-weak_framework");
                libs.add(p);
            }
        }
        if (isDarwin && !config.getFrameworkPaths().isEmpty()) {
            for (File p : config.getFrameworkPaths()) {
                ccArgs.add("-F" + p.getAbsolutePath());
            }
        }
        
        if (!config.getLibs().isEmpty()) {
            objectFiles = new ArrayList<File>(objectFiles);
            for (Config.Lib lib : config.getLibs()) {
                String p = lib.getValue();
                if (p.endsWith(".o")) {
                    objectFiles.add(new File(p));
                } else if (p.endsWith(".a")) {
                    // .a file
                    if (config.getOs().getFamily() == OS.Family.darwin) {
                        if (lib.isForce()) {
                            libs.add("-force_load");
                        }
                        libs.add(new File(p).getAbsolutePath());
                    } else {
                        if (lib.isForce()) {
                            libs.add("-Wl,--whole-archive");
                        }
                        libs.add(new File(p).getAbsolutePath());            
                        if (lib.isForce()) {
                            libs.add("-Wl,--no-whole-archive");
                        }
                    }
                } else if (p.endsWith(".dylib") || p.endsWith(".so")) {
                    libs.add(new File(p).getAbsolutePath());
                } else {
                    // link via -l if suffix is omitted
                    libs.add("-l" + p);
                }
            }
        }
        
        if (targetBinary == TargetBinary.dynamic_lib) {
        	libs.add( "-shared" );
        	ccArgs.add("-fPIC");
        }
     
        if (isOSX) {
            if (!config.getFrameworks().contains("CoreServices")) {
                libs.add("-framework");
                libs.add("CoreServices");
            }
        } else if (isIOS) {
            if (!config.getFrameworks().contains("MobileCoreServices")) {
                libs.add("-framework");
                libs.add("MobileCoreServices");
            }
        }
        
        doBuild(outFile, ccArgs, objectFiles, libs);
    }
    
    protected void doBuild(File outFile, List<String> ccArgs, List<File> objectFiles, 
            List<String> libs) throws IOException {
        
        ToolchainUtil.link(config, ccArgs, objectFiles, libs, outFile);        
    }
    
    protected void copyResources(File destDir) throws IOException {
        for (Resource res : config.getResources()) {
            res.walk(new Walker() {
                @Override
                public boolean processDir(Resource resource, File dir, File destDir) throws IOException {
                    return AbstractTarget.this.processDir(resource, dir, destDir);
                }
                @Override
                public void processFile(Resource resource, File file, File destDir)
                        throws IOException {
                    
                    copyFile(resource, file, destDir);
                }
            }, destDir);
        }
    }

    protected boolean processDir(Resource resource, File dir, File destDir) throws IOException {
        return true;
    }

    protected void copyFile(Resource resource, File file, File destDir) throws IOException {
        config.getLogger().debug("Copying resource %s to %s", file, destDir);
        FileUtils.copyFileToDirectory(file, destDir, true);
    }
    
    public void install() throws IOException {
    	if (config.getInstallDir() != null) {
	        config.getLogger().debug("Installing executable %s to %s", config.getBinaryName(), config.getInstallDir());
	        config.getInstallDir().mkdirs();
	        doInstall(config.getInstallDir(), config.getBinaryName());
    	}
    	else {
    		config.getLogger().debug("No executable to install.");
    	}
    }
    
    protected void doInstall(File installDir, String executable) throws IOException {
        if (!config.getTmpDir().equals(installDir) || !executable.equals(config.getBinaryName())) {
            File destFile = new File(installDir, executable);
            FileUtils.copyFile(new File(config.getTmpDir(), config.getBinaryName()), destFile);
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
        
        // Add -rvm:log=warn to command line arguments if no logging level has been set explicitly
        boolean add = true;
        for (String arg : launchParameters.getArguments()) {
            if (arg.startsWith("-rvm:log=")) {
                add = false;
                break;
            }
        }
        if (add) {
            List<String> args = new ArrayList<String>(launchParameters.getArguments());
            args.add(0, "-rvm:log=warn");
            launchParameters.setArguments(args);
        }

        Map<String, String> env = new HashMap<>(launchParameters.getEnvironment() != null 
                ? launchParameters.getEnvironment() : Collections.<String, String>emptyMap());
        env.put("ROBOVM_LAUNCH_MODE", config.isDebug() ? "debug" : "release");
        launchParameters.setEnvironment(env);
        
        return doLaunch(launchParameters);
    }
    
    protected Process doLaunch(LaunchParameters launchParameters) throws IOException {
        return createLauncher(launchParameters).execAsync();
    }
    
    protected abstract Launcher createLauncher(LaunchParameters launchParameters) throws IOException;
    
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
                        if (entry.getName().startsWith("META-INF/robovm/")) {
                            // Don't include anything under META-INF/robovm/
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
                    String entryName = f.getAbsolutePath().substring(basePath.length() + 1);
                    if (entryName.startsWith("META-INF/robovm/")) {
                        // Don't include anything under META-INF/robovm/
                        continue;
                    }
                    ZipEntry newEntry = new ZipEntry(entryName);
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
