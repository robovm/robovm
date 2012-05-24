/*
 * Copyright (C) 2011 The RoboVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.robovm.ide.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.JavaCore;

/**
 *
 * @version $Id$
 */
public class RoboVMNature implements IProjectNature {

    public static final String ID = "org.robovm.ide.RoboVMNature";
    
    private IProject project;
    
    public void configure() throws CoreException {
        addClassBuilder();
    }

    public void deconfigure() throws CoreException {
        removeBuilder(RoboVMClassBuilder.ID);
    }

    public IProject getProject() {
        return project;
    }

    public void setProject(IProject project) {
        this.project = project;
    }

    public static synchronized void configureNatures(IProject project, 
            IProgressMonitor monitor) throws CoreException {
        
        if (project == null || !project.isOpen()) {
            return;
        }
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        addNature(project, JavaCore.NATURE_ID, monitor);
        addNature(project, RoboVMNature.ID, monitor);
    }

    private static void addNature(IProject project, String natureId,
            IProgressMonitor monitor) throws CoreException {
        
        if (!project.hasNature(natureId)) {
            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = natureId;
            description.setNatureIds(newNatures);
            project.setDescription(description, new SubProgressMonitor(monitor, 10));
        }
    }
    
    private void addClassBuilder() throws CoreException {
        IProjectDescription desc = project.getDescription();
        List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));

        for (ICommand command : commands) {
            if (RoboVMClassBuilder.ID.equals(command.getBuilderName())) {
                return;
            }
        }

        for (int i = 0; i < commands.size(); i++) {
            if (JavaCore.BUILDER_ID.equals(commands.get(i).getBuilderName())) {
                ICommand command = desc.newCommand();
                command.setBuilderName(RoboVMClassBuilder.ID);
                commands.add(i + 1, command);
                desc.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
                project.setDescription(desc, null);
                return;
            }
        }
        
        // TODO: Throw an exception here?
    }
    
    private void removeBuilder(String id) throws CoreException {
        IProjectDescription desc = project.getDescription();
        List<ICommand> commands = new ArrayList<ICommand>(Arrays.asList(desc.getBuildSpec()));
        for (Iterator<ICommand> it = commands.iterator(); it.hasNext();) {
            ICommand command = it.next();
            if (id.equals(command.getBuilderName())) {
                it.remove();
                desc.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
                project.setDescription(desc, null);
                return;
            }
        }
    }
}
