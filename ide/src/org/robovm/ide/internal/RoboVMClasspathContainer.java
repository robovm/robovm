/*
 * Copyright (C) 2011 The RoboVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.robovm.ide.internal;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.robovm.ide.RoboVMPlugin;

/**
 *
 * @version $Id$
 */
public class RoboVMClasspathContainer implements IClasspathContainer {

    public static final String ID = "org.robovm.ide.ROBOVM_CONTAINER";
    public static final IPath PATH = new Path(ID);
    
    public IClasspathEntry[] getClasspathEntries() {
        File roboVMHomeDir = RoboVMPlugin.useBundledRoboVM() 
                ? RoboVMPlugin.getBundledRoboVMDir() 
                        : RoboVMPlugin.getRoboVMHomeDir();
        File f = new File(new File(roboVMHomeDir, "lib"), "robovm-rt.jar");
        return new IClasspathEntry[] {
            JavaCore.newLibraryEntry(new Path(f.getAbsolutePath()), null, null)
        };
    }

    public String getDescription() {
        return "RoboVM Runtime Library";
    }

    public int getKind() {
        return K_DEFAULT_SYSTEM;
    }

    public IPath getPath() {
        return PATH;
    }

}
