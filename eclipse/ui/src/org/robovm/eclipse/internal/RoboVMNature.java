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

    public static final String ID = "org.robovm.eclipse.RoboVMNature";
    
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
