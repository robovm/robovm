/**
 * 
 */
package org.nullvm.ide.internal;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaApplicationLaunchShortcut;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchShortcut extends JavaApplicationLaunchShortcut {

    @Override
    protected ILaunchConfigurationType getConfigurationType() {
        return getLaunchManager().getLaunchConfigurationType(NullVMLaunchConfigurationDelegate.TYPE_ID);        
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
