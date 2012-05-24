/**
 * 
 */
package org.robovm.ide.internal;

import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.robovm.compiler.Arch;
import org.robovm.compiler.Config;
import org.robovm.compiler.ConsoleTarget;
import org.robovm.compiler.OS;
import org.robovm.ide.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.robovm.ide.ConsoleLaunchConfigurationType";
    public static final String TYPE_NAME = "Console Application";

    @Override
    protected Arch getArch(ILaunchConfiguration configuration, String mode) {
        return RoboVMPlugin.getDefaultArch();
    }

    @Override
    protected OS getOS(ILaunchConfiguration configuration, String mode) {
        return RoboVMPlugin.getDefaultOS();
    }

    @Override
    protected Config configure(Config.Builder configBuilder,
            ILaunchConfiguration configuration, String mode) throws IOException {
        
        configBuilder.arch(RoboVMPlugin.getDefaultArch());
        configBuilder.os(RoboVMPlugin.getDefaultOS());
        ConsoleTarget.Builder targetBuilder = new ConsoleTarget.Builder();
        configBuilder.targetBuilder(targetBuilder);
        
        return configBuilder.build();
    }
    
}
