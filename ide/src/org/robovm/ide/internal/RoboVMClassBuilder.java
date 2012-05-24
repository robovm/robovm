/*
 * Copyright (C) 2011 The RoboVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.robovm.ide.internal;

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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.robovm.compiler.ClassCompiler;
import org.robovm.compiler.Config;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.ide.RoboVMPlugin;

/**
 *
 * @version $Id$
 */
public class RoboVMClassBuilder extends IncrementalProjectBuilder {

    public static final String ID = "org.robovm.ide.RoboVMClassBuilder";

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
        
        Config.Builder configBuilder = new Config.Builder();
        configBuilder.skipLinking(true);
        configBuilder.skipRuntimeLib(true);
        configBuilder.debug(true);
        configBuilder.arch(RoboVMPlugin.getArch(getProject()));
        configBuilder.os(RoboVMPlugin.getOS(getProject()));
        if (RoboVMPlugin.useBundledRoboVM()) {
            configBuilder.roboVMHomeDir(RoboVMPlugin.getBundledRoboVMDir());
        } else {
            configBuilder.roboVMHomeDir(RoboVMPlugin.getRoboVMHomeDir());
        }
        if (!RoboVMPlugin.useSystemLlvm()) {
            configBuilder.llvmHomeDir(RoboVMPlugin.getLlvmHomeDir());
        }
        configBuilder.logger(RoboVMPlugin.getConsoleLogger());
        
        for (IClasspathEntry entry : javaProject.getResolvedClasspath(false)) {
            if (entry.getEntryKind() != IClasspathEntry.CPE_SOURCE) {
                IResource member = root.findMember(entry.getPath());
                if (member != null) {
                    configBuilder.addClasspathEntry(member.getLocation().toFile());
                } else {
                    if (entry.getPath().toString().contains("/robovm-rt.jar")) {
                        configBuilder.addBootClasspathEntry(entry.getPath().toFile());
                    } else {
                        configBuilder.addClasspathEntry(entry.getPath().toFile());
                    }
                }
            }
        }
        for (IPath outputPath : outputPaths) {
            configBuilder.addClasspathEntry(outputPath.toFile());
        }
        
        try {
            Config config = configBuilder.build();
            RoboVMPlugin.consoleInfo("Building %d changed classes for target %s (%s)", 
                    changedClasses.size(), config.getOs(), config.getArch());
            ClassCompiler compiler = new ClassCompiler(config);
            for (String c : changedClasses) {
                Clazz clazz = config.getClazzes().load(c.replace('.', '/'));
                compiler.compile(clazz);
            }
            RoboVMPlugin.consoleInfo("Build done");
        } catch (IOException e) {
            RoboVMPlugin.consoleError("Build failed");
            throw new CoreException(new Status(IStatus.ERROR, RoboVMPlugin.PLUGIN_ID,
                    "Build failed. Check the RoboVM console for more information.", e));
        }
        
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
