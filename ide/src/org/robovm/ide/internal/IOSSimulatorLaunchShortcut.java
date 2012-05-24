/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * @author niklas
 *
 */
public class IOSSimulatorLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return IOSSimulatorLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return IOSSimulatorLaunchConfigurationDelegate.TYPE_NAME;
    }

    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
    }
    
}
