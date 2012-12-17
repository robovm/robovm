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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public abstract class AbstractLaunchShortcut extends JavaApplicationLaunchShortcut {

    protected abstract String getConfigurationTypeId();
    protected abstract String getConfigurationTypeName();
    protected abstract void customizeConfiguration(ILaunchConfigurationWorkingCopy wc);

    @Override
    protected ILaunchConfigurationType getConfigurationType() {
        return getLaunchManager().getLaunchConfigurationType(getConfigurationTypeId());        
    }

    @Override
    protected String getTypeSelectionTitle() {
        return "Select " + getConfigurationTypeName();
    }
    
    @Override
    protected ILaunchConfiguration createConfiguration(IType type) {
        ILaunchConfiguration config = super.createConfiguration(type);
        try {
            ILaunchConfigurationWorkingCopy wc = config.getWorkingCopy();
            customizeConfiguration(wc);
            config = wc.doSave();
        } catch (CoreException e) {
            MessageDialog.openError(RoboVMPlugin.getShell(), getConfigurationTypeName() + " failed", 
                    e.getStatus().getMessage()); 
        }
        return config;
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }
    
}
