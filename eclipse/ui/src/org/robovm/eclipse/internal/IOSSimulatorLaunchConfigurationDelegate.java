/*
 * Copyright (C) 2012 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.eclipse.internal;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.Config.TargetType;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.target.LaunchParameters;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters.Family;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class IOSSimulatorLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.robovm.eclipse.IOSSimulatorLaunchConfigurationType";
    public static final String TYPE_NAME = "iOS Simulator App";
    public static final String ATTR_IOS_SIM_FAMILY = RoboVMPlugin.PLUGIN_ID + ".IOS_SIM_FAMILY";
    public static final String ATTR_IOS_SIM_SDK = RoboVMPlugin.PLUGIN_ID + ".IOS_SIM_SDK";

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
        
        configBuilder.targetType(TargetType.ios);
        
        return configBuilder.build();
    }
    
    @Override
    protected void customizeLaunchParameters(LaunchParameters launchParameters,
            ILaunchConfiguration configuration, String mode) throws IOException, CoreException {

        super.customizeLaunchParameters(launchParameters, configuration, mode);
        
        IOSSimulatorLaunchParameters lp = (IOSSimulatorLaunchParameters) launchParameters;
        String family = configuration.getAttribute(ATTR_IOS_SIM_FAMILY, (String) null);
        if (family != null) {
            lp.setFamily(Family.valueOf(family));
        }
        String sdk = configuration.getAttribute(ATTR_IOS_SIM_SDK, "latest");
        if (!"latest".equals(sdk)) {
            lp.setSdk(sdk);
        }
    }
}
