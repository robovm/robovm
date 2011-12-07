/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.ide;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 *
 * @version $Id$
 */
public class NullVMPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "nullvm-ide"; //$NON-NLS-1$

    // The shared instance
    private static NullVMPlugin plugin;
    
    /**
     * The constructor
     */
    public NullVMPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        System.out.println("start");
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        System.out.println("stop");
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static NullVMPlugin getDefault() {
        return plugin;
    }
    
}
