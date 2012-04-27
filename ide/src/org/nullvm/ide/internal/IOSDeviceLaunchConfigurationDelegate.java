/**
 * 
 */
package org.nullvm.ide.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Config;
import org.nullvm.compiler.IOSDeviceTarget;
import org.nullvm.compiler.LaunchParameters;
import org.nullvm.compiler.OS;

/**
 * @author niklas
 *
 */
public class IOSDeviceLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.nullvm.ide.IOSDeviceLaunchConfigurationType";
    public static final String TYPE_NAME = "iOS Device App";

    @Override
    protected Arch getArch(ILaunchConfiguration configuration, String mode) {
        return Arch.thumbv7;
    }

    @Override
    protected OS getOS(ILaunchConfiguration configuration, String mode) {
        return OS.ios;
    }
    
    @Override
    protected Config configure(Config.Builder configBuilder,
            ILaunchConfiguration configuration, String mode) throws IOException {
        
        IOSDeviceTarget.Builder targetBuilder = new IOSDeviceTarget.Builder();
        targetBuilder.fruitstrapBinPath(new File("/usr/local/bin/fruitstrap"));
        configBuilder.targetBuilder(targetBuilder);
        
        return configBuilder.build();
    }
    
    @Override
    protected void customizeLaunchParameters(LaunchParameters launchParameters) throws IOException {
        launchParameters.setStdoutFifo(mkfifo("stdout"));
        launchParameters.setStderrFifo(mkfifo("stderr"));
    }
}
