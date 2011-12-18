/**
 * 
 */
package org.nullvm.ide.internal;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.processing.ProcessingEnvironment;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.nullvm.compiler.App;
import org.nullvm.compiler.AppCompiler;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Config;
import org.nullvm.compiler.OS;
import org.nullvm.ide.NullVMPlugin;

/**
 * @author niklas
 *
 */
public class NullVMLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {

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
            
            File nullVMHomeDir = new File(System.getProperty("user.home"), ".nullvm");
            File llvmHomeDir = new File("/home/niklas/Applications/clang+llvm-2.9-x86_64-linux");
            
            Arch arch = Arch.getDefaultArch(llvmHomeDir);
            OS os = OS.getDefaultOS(llvmHomeDir);
            
            File installDir = new File(NullVMPlugin.getMetadataDir(), getJavaProjectName(configuration));
            installDir = new File(new File(installDir, os.toString()), arch.toString());
            installDir = new File(installDir, mainTypeName);
            
            // Verification done
            monitor.worked(1);
            
            NullVMPlugin.consoleInfo("Building executable");
            
            monitor.subTask("Creating source locator"); 
            setDefaultSourceLocator(launch, configuration);
            monitor.worked(1);
            
            monitor.subTask("Creating build configuration");
            Config.Builder configBuilder = new Config.Builder();
            configBuilder.arch(arch);
            configBuilder.os(os);
            configBuilder.debug(true);
            configBuilder.skipInstall(false);
            configBuilder.nullVMHomeDir(nullVMHomeDir);
            configBuilder.llvmHomeDir(llvmHomeDir);
            configBuilder.logger(NullVMPlugin.getConsoleLogger());
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
            configBuilder.installDir(installDir);
            
            Config config = null;
            AppCompiler compiler = null;
            App app = null;
            try {
                config = configBuilder.build();
                app = config.getApp();
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
                NullVMPlugin.consoleInfo("Build done");
            } catch (IOException e) {
                NullVMPlugin.consoleError("Build failed");
                throw new CoreException(new Status(IStatus.ERROR, NullVMPlugin.PLUGIN_ID,
                        "Build failed", e));
            }

            try {
                NullVMPlugin.consoleInfo("Installing executable to %s", installDir);
                monitor.subTask("Installing executable");
                app.install();
                if (monitor.isCanceled()) {
                    return;
                }
                monitor.worked(1);
                NullVMPlugin.consoleInfo("Install done");
            } catch (IOException e) {
                NullVMPlugin.consoleError("Install failed");
                throw new CoreException(new Status(IStatus.ERROR, NullVMPlugin.PLUGIN_ID,
                        "Install failed", e));
            }
            
            try {
                NullVMPlugin.consoleInfo("Launching executable");
                monitor.subTask("Launching executable");
                
                List<String> runArgs = new ArrayList<String>();
                runArgs.addAll(splitArgs(vmArgs));
                runArgs.addAll(splitArgs(pgmArgs));
                String[] cmdLine = config.getApp().generateCommandLine(runArgs);
                String label = String.format("%s (%s)", cmdLine[0], 
                        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(new Date()));
                Process process = DebugPlugin.exec(cmdLine, workingDir, 
                        envFromMap(config.getApp().modifyEnv(envToMap(envp))));
                DebugPlugin.newProcess(launch, process, label);
                NullVMPlugin.consoleInfo("Launch done");
                
                if (monitor.isCanceled()) {
                    process.destroy();
                    return;
                }
                monitor.worked(1);
            } catch (IOException e) {
                NullVMPlugin.consoleError("Launch failed");
                throw new CoreException(new Status(IStatus.ERROR, NullVMPlugin.PLUGIN_ID,
                        "Launch failed", e));
            }
            
        } finally {
            monitor.done();
        }
    }

    private Map<String, String> envToMap(String[] envp) throws IOException {
        if (envp == null) {
            return EnvironmentUtils.getProcEnvironment();
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
    
    private String[] envFromMap(Map<String, String> env) {
        String[] result = new String[env.size()];
        int i = 0;
        for (Entry<String, String> entry : env.entrySet()) {
            result[i++] = entry.getKey() + "=" + entry.getValue();
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
}
