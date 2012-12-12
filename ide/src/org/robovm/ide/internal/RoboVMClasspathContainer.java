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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
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
        File f = RoboVMPlugin.getRoboVMHome().getRtPath();
        return new IClasspathEntry[] {
            JavaCore.newLibraryEntry(new Path(f.getAbsolutePath()), null, null,
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
