/*
 * Copyright (C) 2011 The RoboVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.robovm.ide.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 *
 * @version $Id$
 */
public class RoboVMClasspathContainerInitializer extends ClasspathContainerInitializer {

    @Override
    public void initialize(IPath containerPath, IJavaProject project) throws CoreException {
        JavaCore.setClasspathContainer(containerPath, new IJavaProject[] {project}, 
                new IClasspathContainer[] {new RoboVMClasspathContainer()}, 
                new NullProgressMonitor());
    }

}
