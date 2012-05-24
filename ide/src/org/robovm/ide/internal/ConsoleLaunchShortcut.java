/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return ConsoleLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return ConsoleLaunchConfigurationDelegate.TYPE_NAME;
    }

    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
    }
    
}
