/**
 * 
 */
package org.nullvm.ide.internal;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * @author niklas
 *
 */
public class IOSSimLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return IOSSimLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return IOSSimLaunchConfigurationDelegate.TYPE_NAME;
    }

    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
    }
    
}
