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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.robovm.compiler.config.Config;
import org.robovm.eclipse.RoboVMPlugin;

/**
 *
 * @version $Id$
 */
public class RoboVMClasspathContainer implements IClasspathContainer {

    public static final String ID = "org.robovm.eclipse.ROBOVM_CONTAINER";
    public static final IPath PATH = new Path(ID);
    
    public IClasspathEntry[] getClasspathEntries() {
        Config.Home home = RoboVMPlugin.getRoboVMHome();
        File f = home.getRtPath();
        IPath sourceAttachment = null;
        if (!home.isDev()) {
            // robovm-rt.jar. Use robovm-rt-sources.jar as source attachment.
            sourceAttachment = new Path(new File(f.getParentFile(), "robovm-rt-sources.jar").getAbsolutePath());
        } else {
            // ROBOVM_DEV_ROOT has been set and rtPath is $ROBOVM_DEV_ROOT/rt/target/robovm-rt-<version>.jar. Use
            // $ROBOVM_DEV_ROOT/rt/target/robovm-rt-<version>-sources.jar as source attachment.
            sourceAttachment = new Path(f.getAbsolutePath().replaceAll("\\.jar$", "-sources.jar"));
        }
        return new IClasspathEntry[] {
            JavaCore.newLibraryEntry(new Path(f.getAbsolutePath()), sourceAttachment, new Path(""),
                    new IAccessRule[] {}, new IClasspathAttribute[] {}, false)
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
