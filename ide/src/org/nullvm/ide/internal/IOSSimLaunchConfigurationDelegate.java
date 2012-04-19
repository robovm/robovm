/**
 * 
 */
package org.nullvm.ide.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.Config;
import org.nullvm.compiler.IOSSimTarget;
import org.nullvm.compiler.LaunchParameters;
import org.nullvm.compiler.OS;

/**
 * @author niklas
 *
 */
public class IOSSimLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.nullvm.ide.IOSSimLaunchConfigurationType";
    public static final String TYPE_NAME = "NullVM iOS-sim Application";

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
        
        IOSSimTarget.Builder targetBuilder = new IOSSimTarget.Builder();
        targetBuilder.iosSimBinPath(new File("/usr/local/bin/ios-sim"));
        configBuilder.targetBuilder(targetBuilder);
        
        return configBuilder.build();
    }
    
    @Override
    protected void customizeLaunchParameters(LaunchParameters launchParameters) throws IOException {
        launchParameters.setRedirectStreamsToLogger(true);
        launchParameters.setStdoutFifo(mkfifo("stdout"));
        launchParameters.setStderrFifo(mkfifo("stderr"));
    }
}
