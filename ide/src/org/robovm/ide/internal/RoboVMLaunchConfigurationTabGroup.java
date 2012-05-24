/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaMainTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaSourceLookupTab;

/**
 * @author niklas
 *
 */
public class RoboVMLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

    @Override
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        setTabs(new ILaunchConfigurationTab[] {
                new JavaMainTab(),
                new JavaArgumentsTab(),
                new JavaClasspathTab(),
                new JavaSourceLookupTab(),
                new EnvironmentTab(),
                new CommonTab()
        });
    }

}
