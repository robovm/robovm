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

import org.eclipse.debug.core.ILaunchConfiguration;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.target.ConsoleTarget;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchConfigurationDelegate extends AbstractLaunchConfigurationDelegate {

    public static final String TYPE_ID = "org.robovm.eclipse.ConsoleLaunchConfigurationType";
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
        
        ConsoleTarget target = new ConsoleTarget();
        configBuilder.target(target);
        
        return configBuilder.build();
    }
    
}
