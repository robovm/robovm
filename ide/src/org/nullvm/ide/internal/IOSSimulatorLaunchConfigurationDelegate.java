/**
 * 
 */
package org.nullvm.ide.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Config;
import org.nullvm.compiler.IOSSimulatorTarget;
import org.nullvm.compiler.LaunchParameters;
import org.nullvm.compiler.OS;

/**
 * @author niklas
 *
 */
public class IOSSimulatorLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.nullvm.ide.IOSSimulatorLaunchConfigurationType";
    public static final String TYPE_NAME = "iOS Simulator App";

    @Override
    protected Arch getArch(ILaunchConfiguration configuration, String mode) {
        return Arch.x86;
    }

    @Override
    protected OS getOS(ILaunchConfiguration configuration, String mode) {
        return OS.ios;
    }
    
    @Override
    protected Config configure(Config.Builder configBuilder,
            ILaunchConfiguration configuration, String mode) throws IOException {
        
        IOSSimulatorTarget.Builder targetBuilder = new IOSSimulatorTarget.Builder();
        targetBuilder.iosSimBinPath(new File("/usr/local/bin/ios-sim"));
        configBuilder.targetBuilder(targetBuilder);
        
        return configBuilder.build();
    }
    
    @Override
    protected void customizeLaunchParameters(LaunchParameters launchParameters) throws IOException {
        launchParameters.setStdoutFifo(mkfifo("stdout"));
        launchParameters.setStderrFifo(mkfifo("stderr"));
    }
}
