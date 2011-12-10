/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.ide.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.nullvm.compiler.AppCompiler;

/**
 *
 * @version $Id$
 */
public class NullVMClassBuilder extends IncrementalProjectBuilder {

    public static final String ID = "org.nullvm.ide.NullVMClassBuilder";

    @Override
    protected IProject[] build(int kind, Map<String, String> args,
            IProgressMonitor monitor) throws CoreException {

        List<IPath> changedClassFiles = new ArrayList<IPath>();
        findChangedClassFiles(getProject(), kind == FULL_BUILD, changedClassFiles);
        if (changedClassFiles.isEmpty()) {
            return null;
        }
        
        IJavaProject javaProject = JavaCore.create(getProject());
        List<IPath> outputPaths = new ArrayList<IPath>();
        IWorkspaceRoot root = getProject().getWorkspace().getRoot();
        if (javaProject.getOutputLocation() != null) {
            outputPaths.add(root.getFile(javaProject.getOutputLocation()).getLocation());
        }
        for (IClasspathEntry entry : javaProject.getResolvedClasspath(false)) {
            if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                if (entry.getOutputLocation() != null) {
                    outputPaths.add(root.getFile(entry.getOutputLocation()).getLocation());
                }
            }
        }

        List<String> changedClasses = new ArrayList<String>();
        for (IPath f : changedClassFiles) {
            for (IPath outputPath : outputPaths) {
                if (outputPath.isPrefixOf(f)) {
                    String className = f.makeRelativeTo(outputPath).toString();
                    className = className.substring(0, className.length() - ".class".length());
                    className = className.replace('/', '.');
                    changedClasses.add(className);
                }
            }
        }
        
        List<File> bootclasspath = new ArrayList<File>();
        List<File> classpath = new ArrayList<File>();
        for (IClasspathEntry entry : javaProject.getResolvedClasspath(false)) {
            if (entry.getEntryKind() != IClasspathEntry.CPE_SOURCE) {
                IResource member = root.findMember(entry.getPath());
                if (member != null) {
                    classpath.add(member.getLocation().toFile());
                } else {
                    if (entry.getPath().toString().contains("/nullvm-rt.jar")) {
                        bootclasspath.add(entry.getPath().toFile());
                    } else {
                        classpath.add(entry.getPath().toFile());
                    }
                }
            }
        }
        for (IPath outputPath : outputPaths) {
            classpath.add(outputPath.toFile());
        }
        
        System.out.println(classpath);
        
        try {
            AppCompiler.main(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        Main compiler = new Main();
//        compiler.setBootclasspath(bootclasspath);
//        compiler.setClasspath(classpath);
//        compiler.setNort(true);
//        compiler.setNolink(true);
//        try {
//            compiler.run();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        
        return null;
    }
    
    private void findChangedClassFiles(IProject project, boolean full, final List<IPath> files) throws CoreException {
        IResourceDelta delta = full ? null : getDelta(project);
        final IResourceVisitor visitor = new IResourceVisitor() {
            public boolean visit(IResource resource) throws CoreException {
                if ("class".equals(resource.getFileExtension()) && resource.exists()) {
                    // TODO: Handle deleted class files?
                    files.add(resource.getLocation());
                }
                return true;
            }
        };
        if (delta == null) {
            project.accept(visitor);
        } else {
            delta.accept(new IResourceDeltaVisitor() {
                public boolean visit(IResourceDelta d) throws CoreException {
                    return visitor.visit(d.getResource());
                }
            });
        }
    }
}
