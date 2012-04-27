/**
 * 
 */
package org.nullvm.ide.internal;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * @author niklas
 *
 */
public class IOSDeviceLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return IOSDeviceLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return IOSDeviceLaunchConfigurationDelegate.TYPE_NAME;
    }

    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
    }
    
}
