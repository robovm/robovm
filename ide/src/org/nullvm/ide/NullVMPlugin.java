/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.ide;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
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
import org.nullvm.compiler.Logger;
import org.osgi.framework.BundleContext;

/**
 *
 * @version $Id$
 */
public class NullVMPlugin extends AbstractUIPlugin {

    public static final String PLUGIN_ID = "nullvm-ide";

    private static NullVMPlugin plugin;
    
    private boolean showConsoleOnWrite = true;
    private MessageConsole console;
    private MessageConsoleStream debugStream;
    private MessageConsoleStream infoStream;
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
        public void debug(String format, Object... args) {
            NullVMPlugin.consoleDebug(format, args);
        }
    };
    
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;

        // Set up the console
        console = new MessageConsole("NullVM Console", null);
        ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {console});
        Display display = plugin.getWorkbench().getDisplay();
        debugStream = console.newMessageStream();
        final Color debugColor = new Color(display, 0x99, 0x99, 0x99);
        infoStream = console.newMessageStream();
        errorStream = console.newMessageStream();
        final Color errorColor = new Color(display, 0xFF, 0x00, 0x00);
        display.asyncExec(new Runnable() {
            public void run() {
                debugStream.setColor(debugColor);
                errorStream.setColor(errorColor);
            }
        });
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
    
    public static File getMetadataDir() {
        return plugin.getStateLocation().toFile();
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
