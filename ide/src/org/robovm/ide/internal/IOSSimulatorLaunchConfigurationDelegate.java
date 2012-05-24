/**
 * 
 */
package org.robovm.ide.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.robovm.compiler.Arch;
import org.robovm.compiler.Config;
import org.robovm.compiler.IOSSimulatorTarget;
import org.robovm.compiler.LaunchParameters;
import org.robovm.compiler.OS;

/**
 * @author niklas
 *
 */
public class IOSSimulatorLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.robovm.ide.IOSSimulatorLaunchConfigurationType";
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
