/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.ide;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Logger;
import org.nullvm.compiler.OS;
import org.osgi.framework.BundleContext;

/**
 *
 * @version $Id$
 */
public class NullVMPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "org.nullvm.ide";
    public static final String PREFERENCE_USE_SYSTEM_LLVM = PLUGIN_ID + ".prefs.useSystemLlvm";
    public static final String PREFERENCE_LLVM_HOME_DIR = PLUGIN_ID + ".prefs.llvmHomeDir";
    public static final String PREFERENCE_USE_BUNDLED_NULLVM = PLUGIN_ID + ".prefs.useBundledNullVM";
    public static final String PREFERENCE_NULLVM_HOME_DIR = PLUGIN_ID + ".prefs.nullVMHomeDir";
    public static final String PREFERENCE_USE_SYSTEM_GCC = PLUGIN_ID + ".prefs.useSystemGcc";
    public static final String PREFERENCE_GCC_BIN_DIR = PLUGIN_ID + ".prefs.gccBinDir";
    public static final String PREFERENCE_USE_SYSTEM_BINUTILS = PLUGIN_ID + ".prefs.useSystemBinutils";
    public static final String PREFERENCE_BINUTILS_BIN_DIR = PLUGIN_ID + ".prefs.binutilsBinDir";
    public static final String PREFERENCE_INCREMENTAL_BUILD_ARCH = PLUGIN_ID + ".prefs.incrementalBuildArch";
    public static final String PREFERENCE_INCREMENTAL_BUILD_OS = PLUGIN_ID + ".prefs.incrementalBuildOs";
    public static final String LAUNCH_ARCH = PLUGIN_ID + ".launch.arch";
    public static final String LAUNCH_OS = PLUGIN_ID + ".launch.os";
    public static final String ARCH_AUTO = "auto";
    public static final String OS_AUTO = "auto";

    private static NullVMPlugin plugin;
    
    private boolean showConsoleOnWrite = true;
    private MessageConsole console;
    private MessageConsoleStream debugStream;
    private MessageConsoleStream infoStream;
    private MessageConsoleStream warnStream;
    private MessageConsoleStream errorStream;
    
    private static Logger consoleLogger = new Logger() {
        @Override
        public void info(String format, Object... args) {
            NullVMPlugin.consoleInfo(format, args);
        }
        @Override
        public void error(String format, Object... args) {
            NullVMPlugin.consoleError(format, args);
        }
        @Override
        public void warn(String format, Object... args) {
            NullVMPlugin.consoleWarn(format, args);
        }
        @Override
        public void debug(String format, Object... args) {
            NullVMPlugin.consoleDebug(format, args);
        }
    };
    
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        // Set up the console
        console = new MessageConsole("NullVM Console", null);
        console.setWaterMarks(40000, 80000);
        ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {console});
        Display display = getDisplay();
        debugStream = console.newMessageStream();
        final Color debugColor = new Color(display, 0x99, 0x99, 0x99);
        infoStream = console.newMessageStream();
        final Color infoColor = new Color(display, 0x00, 0x99, 0x00);
        warnStream = console.newMessageStream();
        final Color warnColor = new Color(display, 0xFF, 0x00, 0xFF);
        errorStream = console.newMessageStream();
        final Color errorColor = new Color(display, 0xFF, 0x00, 0x00);
        display.asyncExec(new Runnable() {
            public void run() {
                debugStream.setColor(debugColor);
                infoStream.setColor(infoColor);
                warnStream.setColor(warnColor);
                errorStream.setColor(errorColor);
            }
        });
        
        // Extract bundled nullvm
        String version = getBundle().getHeaders().get("Bundle-Version");
        File versionFile = new File(getMetadataDir(), "version");
        if (!versionFile.exists() || !FileUtils.readFileToString(versionFile, "UTF8").equals(version)) {
            File lib = new File(getBundledNullVMDir(), "lib");
            try {
                FileUtils.deleteDirectory(lib);
            } catch (IOException ignored) {
            }
            lib.mkdirs();
            FileUtils.copyURLToFile(getBundle().getResource("/lib/nullvm-rt.jar"), new File(lib, "nullvm-rt.jar"));
            unzip(getBundle().getResource("/lib/binaries.zip"), lib);
            FileUtils.writeStringToFile(versionFile, version, "UTF8");
        }
    }

    private static void unzip(URL zipResource, File targetDir) throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(zipResource.openStream());
            ZipEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                File f = new File(targetDir, entry.getName());
                if (entry.isDirectory()) {
                    f.mkdirs();
                } else {
                    f.getParentFile().mkdirs();
                    BufferedOutputStream out = null;
                    try {
                        out = new BufferedOutputStream(new FileOutputStream(f));
                        IOUtils.copy(in, out);
                    } finally {
                        IOUtils.closeQuietly(out);
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        
        synchronized (NullVMPlugin.class) {
            plugin = null;
        }
    }

    public static Logger getConsoleLogger() {
        return consoleLogger;
    }
    
    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static NullVMPlugin getDefault() {
        return plugin;
    }
    
    public static synchronized Display getDisplay() {
        if (plugin != null) {
            IWorkbench workbench = plugin.getWorkbench();
            if (workbench != null) {
                return workbench.getDisplay();
            }
        }
        return null;
    }
    
    public static synchronized Shell getShell() {
        Display display = getDisplay();
        if (display != null) {
            return display.getActiveShell();
        }
        return null;
    }
    
    public static File getMetadataDir() {
        return plugin.getStateLocation().toFile();
    }

    public static File getBundledNullVMDir() {
        return new File(getMetadataDir(), "nullvm-home");
    }
    
    public static boolean useBundledNullVM() {
        IPreferencesService prefs = Platform.getPreferencesService();
        return prefs.getBoolean(PLUGIN_ID, PREFERENCE_USE_BUNDLED_NULLVM, true, null);
    }

    public static File getNullVMHomeDir() {
        if (!useBundledNullVM()) {
            IPreferencesService prefs = Platform.getPreferencesService();
            return new File(prefs.getString(PLUGIN_ID, PREFERENCE_NULLVM_HOME_DIR, null, null));
        }
        return null;
    }

    public static boolean useSystemLlvm() {
        IPreferencesService prefs = Platform.getPreferencesService();
        return prefs.getBoolean(PLUGIN_ID, PREFERENCE_USE_SYSTEM_LLVM, true, null);
    }
    
    public static File getLlvmHomeDir() {
        if (!useSystemLlvm()) {
            IPreferencesService prefs = Platform.getPreferencesService();
            return new File(prefs.getString(PLUGIN_ID, PREFERENCE_LLVM_HOME_DIR, null, null));
        }
        return null;
    }
    
    public static String getIncrementalBuildArch(IProject project) {
        IPreferencesService prefs = Platform.getPreferencesService();
        IScopeContext[] contexts = new IScopeContext[] {new ProjectScope(project), 
                InstanceScope.INSTANCE, DefaultScope.INSTANCE}; 
        return prefs.getString(PLUGIN_ID, PREFERENCE_INCREMENTAL_BUILD_ARCH, ARCH_AUTO, contexts);
    }
    
    public static String getIncrementalBuildOS(IProject project) {
        IPreferencesService prefs = Platform.getPreferencesService();
        IScopeContext[] contexts = new IScopeContext[] {new ProjectScope(project), 
                InstanceScope.INSTANCE, DefaultScope.INSTANCE}; 
        return prefs.getString(PLUGIN_ID, PREFERENCE_INCREMENTAL_BUILD_OS, OS_AUTO, contexts);
    }
    
    public static Arch getDefaultArch() {
        return Arch.getDefaultArch(getLlvmHomeDir());
    }
    
    public static Arch getArch(IProject project) {
        return getArch(getIncrementalBuildArch(project));
    }
    
    public static Arch getArch(String s) {
        if (ARCH_AUTO.equals(s)) {
            return getDefaultArch();
        }
        return Arch.valueOf(s);
    }
    
    public static OS getDefaultOS() {
        return OS.getDefaultOS(getLlvmHomeDir());
    }
    
    public static OS getOS(IProject project) {
        return getOS(getIncrementalBuildOS(project));
    }
    
    public static OS getOS(String s) {
        if (OS_AUTO.equals(s)) {
            return getDefaultOS();
        }
        return OS.valueOf(s);
    }
    
    private static String now() {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
        return df.format(new Date());
    }
    
    public static synchronized void consoleDebug(String format, Object ... args) {
        if (plugin != null) {
            String msg = String.format(format, args);
            plugin.debugStream.println(now() + ": [DEBUG] " + msg);
            showConsoleIfFirstWrite();
        }
    }
    
    public static synchronized void consoleInfo(String format, Object ... args) {
        if (plugin != null) {
            String msg = String.format(format, args);
            plugin.infoStream.println(now() + ": [ INFO] " + msg);
            showConsoleIfFirstWrite();
        }
    }

    public static synchronized void consoleWarn(String format, Object ... args) {
        if (plugin != null) {
            String msg = String.format(format, args);
            plugin.warnStream.println(now() + ": [ WARN] " + msg);
            showConsole();
        }
    }
    
    public static synchronized void consoleError(String format, Object ... args) {
        if (plugin != null) {
            String msg = String.format(format, args);
            plugin.errorStream.println(now() + ": [ERROR] " + msg);
            showConsole();
        }
    }
    
    private static void showConsoleIfFirstWrite() {
        if (plugin.showConsoleOnWrite) {
            showConsole();
        }
    }
    
    private static void showConsole() {
        plugin.showConsoleOnWrite = false;
        try {
            IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null) {
                IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                if (activePage != null) {
                    activePage.showView(IConsoleConstants.ID_CONSOLE_VIEW, null, IWorkbenchPage.VIEW_VISIBLE);
                }
            }
        } catch (PartInitException partEx) {
        }
        ConsolePlugin.getDefault().getConsoleManager().showConsoleView(plugin.console);
    }    
}
