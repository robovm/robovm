/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.robovm.ide.RoboVMPlugin;

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
