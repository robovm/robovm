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
package org.robovm.eclipse.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.robovm.eclipse.RoboVMPlugin;

/**
 *
 * @version $Id$
 */
public class RoboVMCocoaTouchClasspathContainer implements IClasspathContainer {

    public static final String ID = "org.robovm.eclipse.ROBOVM_COCOA_TOUCH_CONTAINER";
    public static final IPath PATH = new Path(ID);
    
    public IClasspathEntry[] getClasspathEntries() {
        File f = RoboVMPlugin.getRoboVMHome().getRtPath();
        List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
        if (f.isFile()) {
            // ROBOVM_DEV_ROOT not set (rtPath points to $ROBOVM_HOME/lib/robovm-rt.jar).
            File libDir = f.getParentFile();
            entries.add(JavaCore.newLibraryEntry(
                    new Path(new File(libDir, "robovm-objc.jar").getAbsolutePath()), 
                    new Path(new File(libDir, "robovm-objc-sources.jar").getAbsolutePath()), 
                    new Path(""), new IAccessRule[] {}, new IClasspathAttribute[] {}, false));
            entries.add(JavaCore.newLibraryEntry(
                    new Path(new File(libDir, "robovm-cocoatouch.jar").getAbsolutePath()), 
                    new Path(new File(libDir, "robovm-cocoatouch-sources.jar").getAbsolutePath()), 
                    new Path(""), new IAccessRule[] {}, new IClasspathAttribute[] {}, false));
        } else {
            // ROBOVM_DEV_ROOT has been set (rtPath points to $ROBOVM_DEV_ROOT/rt/target/classes).
            File rootDir = f.getParentFile().getParentFile().getParentFile();
            entries.add(JavaCore.newLibraryEntry(
                    new Path(new File(rootDir, "objc/target/classes").getAbsolutePath()), 
                    new Path(new File(rootDir, "objc/src/main/java").getAbsolutePath()), 
                    new Path(""), new IAccessRule[] {}, new IClasspathAttribute[] {}, false));
            entries.add(JavaCore.newLibraryEntry(
                    new Path(new File(rootDir, "cocoatouch/target/classes").getAbsolutePath()), 
                    new Path(new File(rootDir, "cocoatouch/src/main/java").getAbsolutePath()), 
                    new Path(""), new IAccessRule[] {}, new IClasspathAttribute[] {}, false));
        }
        return entries.toArray(new IClasspathEntry[entries.size()]);
    }

    public String getDescription() {
        return "RoboVM CocoaTouch Library";
    }

    public int getKind() {
        return K_APPLICATION;
    }

    public IPath getPath() {
        return PATH;
    }

}
