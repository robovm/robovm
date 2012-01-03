/**
 * 
 */
package org.nullvm.ide.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.nullvm.compiler.ConsoleApp;
import org.nullvm.ide.NullVMPlugin;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchShortcut extends JavaApplicationLaunchShortcut {

    @Override
    protected ILaunchConfigurationType getConfigurationType() {
        return getLaunchManager().getLaunchConfigurationType(NullVMLaunchConfigurationDelegate.TYPE_ID);        
    }

    @Override
    protected ILaunchConfiguration createConfiguration(IType type) {
        ILaunchConfiguration config = super.createConfiguration(type);
        try {
            ILaunchConfigurationWorkingCopy wc = config.getWorkingCopy();
            wc.setAttribute(NullVMPlugin.LAUNCH_APP_CLASS, ConsoleApp.class.getName());
            wc.setAttribute(NullVMPlugin.LAUNCH_ARCH, NullVMPlugin.ARCH_AUTO);
            wc.setAttribute(NullVMPlugin.LAUNCH_OS, NullVMPlugin.OS_AUTO);
            config = wc.doSave();
        } catch (CoreException e) {
            MessageDialog.openError(NullVMPlugin.getShell(), getClass().getName() + " failed", 
                    e.getStatus().getMessage()); 
        }
        return config;
    }

    /**
     * Returns the singleton launch manager.
     * 
     * @return launch manager
     */
    private ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }
    
}
