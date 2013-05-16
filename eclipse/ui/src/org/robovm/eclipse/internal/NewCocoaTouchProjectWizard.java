/*
 * Copyright (C) 2013 Trillian AB
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

import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.OS;

/**
 * 
 */
public class NewCocoaTouchProjectWizard extends NewProjectWizard {

    public NewCocoaTouchProjectWizard() {
        super();
        setWindowTitle("New RoboVM Cocoa Touch Project");
    }
    
    @Override
    protected String getDefaultArch() {
        return Arch.x86.toString();
    }
    
    @Override
    protected String getDefaultOs() {
        return OS.ios.toString();
    }
    
    @Override
    protected List<IClasspathEntry> customizeClasspath(List<IClasspathEntry> classpath) {
        classpath.add(JavaCore.newContainerEntry(new Path(RoboVMCocoaTouchClasspathContainer.ID)));
        return super.customizeClasspath(classpath);
    }
    
}
