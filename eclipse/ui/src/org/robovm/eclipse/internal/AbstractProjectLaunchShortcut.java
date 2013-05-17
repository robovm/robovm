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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * Abstract {@link ILaunchShortcut} used to launch an app by right-clicking the project.
 */
public abstract class AbstractProjectLaunchShortcut implements ILaunchShortcut {

    protected abstract String getConfigurationTypeId();
    protected abstract void customizeConfiguration(ILaunchConfigurationWorkingCopy wc);
    protected List<ILaunchConfiguration> filterConfigs(List<ILaunchConfiguration> configs) throws CoreException {
        return configs;
    }
    
    @Override
    public void launch(ISelection selection, String mode) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structSelect = (IStructuredSelection)selection;
            Object o = structSelect.getFirstElement();

            if (o instanceof IAdaptable) {
                IResource r = (IResource)((IAdaptable)o).getAdapter(IResource.class);

                if (r != null) {
                    IProject project = r.getProject();

                    if (project != null)  {
                        launch(project, mode);
                    }
                }
            }
        }
    }

    @Override
    public void launch(IEditorPart editor, String mode) {
        // Never called
    }

    private ILaunchConfiguration chooseConfiguration(List<ILaunchConfiguration> configList) {
        IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
        ElementListSelectionDialog dialog= new ElementListSelectionDialog(RoboVMPlugin.getShell(), labelProvider);
        dialog.setElements(configList.toArray());
        dialog.setTitle("");  
        dialog.setMessage("");
        dialog.setMultipleSelection(false);
        int result = dialog.open();
        labelProvider.dispose();
        if (result == Window.OK) {
            return (ILaunchConfiguration) dialog.getFirstResult();
        }
        return null;            
    }
    
    private ILaunchConfiguration findConfig(ILaunchManager manager, ILaunchConfigurationType configType, IProject project) {
        try {
            ILaunchConfiguration[] configs = manager.getLaunchConfigurations(configType);
            
            List<ILaunchConfiguration> matchingConfigs = new ArrayList<ILaunchConfiguration>();
            for (ILaunchConfiguration config : configs) {
                if (config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "").equals(project.getName())) {
                    matchingConfigs.add(config);
                }
            }
            
            matchingConfigs = filterConfigs(matchingConfigs);
            
            if (matchingConfigs.size() == 1) {
                return matchingConfigs.get(0);
            }
            if (matchingConfigs.size() > 1) {
                return chooseConfiguration(matchingConfigs);
            }
            
        } catch (CoreException e) {
            RoboVMPlugin.log(e);
        }
        return null;
    }
    
    private void launch(IProject project, String mode) {
        ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfigurationType configType = manager.getLaunchConfigurationType(getConfigurationTypeId());
        ILaunchConfiguration config = findConfig(manager, configType, project);
        
        if (config == null) {
            ILaunchConfigurationWorkingCopy wc = null;
            try {
                wc = configType.newInstance(null, manager.generateLaunchConfigurationName(project.getName()));
                wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
                customizeConfiguration(wc);
                config = wc.doSave();
            } catch (CoreException e) {
                RoboVMPlugin.log(e);
            }
        }

        if (config != null) {
            DebugUITools.launch(config, mode);
        }
    }
}
