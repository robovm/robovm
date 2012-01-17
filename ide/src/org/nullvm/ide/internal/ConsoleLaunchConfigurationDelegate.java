/**
 * 
 */
package org.nullvm.ide.internal;

import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Config;
import org.nullvm.compiler.ConsoleTarget;
import org.nullvm.compiler.OS;
import org.nullvm.ide.NullVMPlugin;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.nullvm.ide.ConsoleLaunchConfigurationType";
    public static final String TYPE_NAME = "NullVM Console Application";

    @Override
    protected Arch getArch(ILaunchConfiguration configuration, String mode) {
        return NullVMPlugin.getDefaultArch();
    }

    @Override
    protected OS getOS(ILaunchConfiguration configuration, String mode) {
        return NullVMPlugin.getDefaultOS();
    }

    @Override
    protected Config configure(Config.Builder configBuilder,
            ILaunchConfiguration configuration, String mode) throws IOException {
        
        configBuilder.arch(NullVMPlugin.getDefaultArch());
        configBuilder.os(NullVMPlugin.getDefaultOS());
        ConsoleTarget.Builder targetBuilder = new ConsoleTarget.Builder();
        configBuilder.targetBuilder(targetBuilder);
        
        return configBuilder.build();
    }
    
}
