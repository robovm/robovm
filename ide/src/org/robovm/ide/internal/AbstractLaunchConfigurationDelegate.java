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
package org.robovm.ide.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.robovm.compiler.AppCompiler;
import org.robovm.compiler.Arch;
import org.robovm.compiler.Config;
import org.robovm.compiler.LaunchParameters;
import org.robovm.compiler.OS;
import org.robovm.compiler.Target;
import org.robovm.compiler.io.OpenOnReadFileInputStream;
import org.robovm.ide.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public abstract class AbstractLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

    protected abstract Arch getArch(ILaunchConfiguration configuration, String mode);
    protected abstract OS getOS(ILaunchConfiguration configuration, String mode);
    protected abstract Config configure(Config.Builder configBuilder, ILaunchConfiguration configuration, String mode) throws IOException;
    
    protected void customizeLaunchParameters(LaunchParameters launchParameters) throws IOException {
        launchParameters.setStdoutFifo(mkfifo("stdout"));
        launchParameters.setStderrFifo(mkfifo("stderr"));
    }
    
    @Override
    public void launch(ILaunchConfiguration configuration, String mode,
            ILaunch launch, IProgressMonitor monitor) throws CoreException {

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        
        monitor.beginTask(configuration.getName() + "...", 6);
        if (monitor.isCanceled()) {
            return;
        }
        
        try {
            monitor.subTask("Verifying launch attributes"); 
            
            String mainTypeName = verifyMainTypeName(configuration);
            File workingDir = verifyWorkingDirectory(configuration);
            String[] envp = getEnvironment(configuration);
            String pgmArgs = getProgramArguments(configuration);
            String vmArgs = getVMArguments(configuration);
            String[] classpath = getClasspath(configuration);
            String[] bootclasspath = getBootpath(configuration);
            
            if (monitor.isCanceled()) {
                return;
            }
            
            // Verification done
            monitor.worked(1);
            
            RoboVMPlugin.consoleInfo("Building executable");
            
            monitor.subTask("Creating source locator"); 
            setDefaultSourceLocator(launch, configuration);
            monitor.worked(1);
            
            monitor.subTask("Creating build configuration");
            Config.Builder configBuilder = new Config.Builder();
            
            Arch arch = getArch(configuration, mode);
            OS os = getOS(configuration, mode);
            
            File tmpDir = new File(RoboVMPlugin.getMetadataDir(), getJavaProjectName(configuration));
            tmpDir = new File(tmpDir, configuration.getName());
            tmpDir = new File(new File(tmpDir, os.toString()), arch.toString());
            tmpDir = new File(tmpDir, mainTypeName);
            
            configBuilder.arch(arch);
            configBuilder.os(os);
            configBuilder.debug(true);
            configBuilder.skipInstall(false);
            configBuilder.home(RoboVMPlugin.getRoboVMHome());
            if (!RoboVMPlugin.useSystemLlvm()) {
                configBuilder.llvmHomeDir(RoboVMPlugin.getLlvmHomeDir());
            }            
            configBuilder.logger(RoboVMPlugin.getConsoleLogger());
            if (bootclasspath != null) {
                configBuilder.skipRuntimeLib(true);
                for (String p : bootclasspath) {
                    configBuilder.addBootClasspathEntry(new File(p));
                }
            }
            for (String p : classpath) {
                configBuilder.addClasspathEntry(new File(p));
            }
            configBuilder.mainClass(mainTypeName);
            configBuilder.tmpDir(tmpDir);
            configBuilder.skipInstall(true);
            
            Config config = null;
            AppCompiler compiler = null;
            Target target = null;
            try {
                config = configure(configBuilder, configuration, mode);
                target = config.getTarget();
                compiler = new AppCompiler(config);
                if (monitor.isCanceled()) {
                    return;
                }
                monitor.worked(1);
                
                monitor.subTask("Building executable");
                compiler.compile();
                if (monitor.isCanceled()) {
                    return;
                }
                monitor.worked(1);
                RoboVMPlugin.consoleInfo("Build done");
            } catch (IOException e) {
                RoboVMPlugin.consoleError("Build failed");
                throw new CoreException(new Status(IStatus.ERROR, RoboVMPlugin.PLUGIN_ID,
                        "Build failed. Check the RoboVM console for more information.", e));
            }

            try {
                RoboVMPlugin.consoleInfo("Launching executable");
                monitor.subTask("Launching executable");
                
                List<String> runArgs = new ArrayList<String>();
                runArgs.addAll(splitArgs(vmArgs));
                runArgs.addAll(splitArgs(pgmArgs));
                LaunchParameters launchParameters = new LaunchParameters();
                launchParameters.setArguments(runArgs);
                launchParameters.setWorkingDirectory(workingDir);
                launchParameters.setEnvironment(envToMap(envp));
                customizeLaunchParameters(launchParameters);
                String label = String.format("%s (%s)", mainTypeName, 
                        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(new Date()));
                
                Process process = target.launch(launchParameters);
                if (launchParameters.getStdoutFifo() != null || launchParameters.getStderrFifo() != null) {
                    InputStream stdoutStream = null;
                    InputStream stderrStream = null;
                    if (launchParameters.getStdoutFifo() != null) {
                        stdoutStream = new OpenOnReadFileInputStream(launchParameters.getStdoutFifo());
                    }
                    if (launchParameters.getStderrFifo() != null) {
                        stderrStream = new OpenOnReadFileInputStream(launchParameters.getStderrFifo());
                    }
                    process = new ProcessProxy(process, stdoutStream, stderrStream);
                }
                
                DebugPlugin.newProcess(launch, process, label);
                RoboVMPlugin.consoleInfo("Launch done");
                
                if (monitor.isCanceled()) {
                    process.destroy();
                    return;
                }
                monitor.worked(1);
            } catch (IOException e) {
                RoboVMPlugin.consoleError("Launch failed");
                throw new CoreException(new Status(IStatus.ERROR, RoboVMPlugin.PLUGIN_ID,
                        "Launch failed. Check the RoboVM console for more information.", e));
            }
            
        } finally {
            monitor.done();
        }
    }

    private Map<String, String> envToMap(String[] envp) throws IOException {
        if (envp == null) {
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < envp.length; i++) {
            int index = envp[i].indexOf('=');
            if (index != -1) {
                result.put(envp[i].substring(0, index), envp[i].substring(index + 1));
            }
        }
        return result;
    }
    
    private List<String> splitArgs(String args) {
        if (args == null || args.trim().length() == 0) {
            return Collections.emptyList();
        }
        String[] parts = CommandLine.parse("foo " + args).toStrings();
        if (parts.length <= 1) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<String>(parts.length - 1);
        for (int i = 1 ; i < parts.length; i++) {
            result.add(parts[i]);
        }
        return result;
    }
    
    protected File mkfifo(String type) throws IOException {
        File f = File.createTempFile("robovm-" + type + "-", ".fifo");
        f.delete();
        ProcessBuilder pb = new ProcessBuilder("mkfifo", "-m", "600", f.getAbsolutePath());
        try {
            int exitValue = pb.start().waitFor();
            if (exitValue != 0) {
                throw new IOException("Failed to create " + type + " fifo");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return f;
    }
    
    private static class ProcessProxy extends Process {
        private final Process target;
        private final InputStream inputStream;
        private final InputStream errorStream;
        ProcessProxy(Process target, InputStream inputStream, InputStream errorStream) {
            this.target = target;
            this.inputStream = inputStream;
            this.errorStream = errorStream;            
        }
        public void destroy() {
            target.destroy();
        }
        public boolean equals(Object obj) {
            return target.equals(obj);
        }
        public int exitValue() {
            return target.exitValue();
        }
        public InputStream getErrorStream() {
            if (errorStream != null) {
                return errorStream;
            }
            return target.getErrorStream();
        }
        public InputStream getInputStream() {
            if (inputStream != null) {
                return inputStream;
            }
            return target.getInputStream();
        }
        public OutputStream getOutputStream() {
            return target.getOutputStream();
        }
        public int hashCode() {
            return target.hashCode();
        }
        public String toString() {
            return target.toString();
        }
        public int waitFor() throws InterruptedException {
            return target.waitFor();
        }
    }
}
