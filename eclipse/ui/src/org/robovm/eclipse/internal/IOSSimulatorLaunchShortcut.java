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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jdt.core.IType;
import org.robovm.compiler.IOSSimulatorLaunchParameters.Family;
import org.robovm.eclipse.RoboVMPlugin;


/**
 * @author niklas
 *
 */
public abstract class IOSSimulatorLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return IOSSimulatorLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return IOSSimulatorLaunchConfigurationDelegate.TYPE_NAME;
    }
    
    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
        wc.setAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_FAMILY, getFamily().toString());
    }
    
    protected abstract Family getFamily();
    
    private List<ILaunchConfiguration> filterConfigs(List<ILaunchConfiguration> configs) {
        try {
            List<ILaunchConfiguration> result = new ArrayList<ILaunchConfiguration>();
            String family = getFamily().toString();
            for (ILaunchConfiguration config : configs) {
                if (family.equals(config.getAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_FAMILY, (String) null))) {
                    result.add(config);
                }
            }
            return result;
        } catch (CoreException e) {
            RoboVMPlugin.log(e);
            return Collections.emptyList();
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected ILaunchConfiguration chooseConfiguration(List configList) {
        // Filter out configs with non-matching device family and only call
        // super.chooseConfiguration() if there are more than 1 after the
        // filtering.
        configList = filterConfigs(configList);
        if (configList.isEmpty()) {
            return null;
        }
        if (configList.size() == 1) {
            return (ILaunchConfiguration) configList.get(0);
        }
        return super.chooseConfiguration(configList);
    }
    
    @Override
    protected ILaunchConfiguration findLaunchConfiguration(IType type,
            ILaunchConfigurationType configType) {

        // super.findLaunchConfiguration() will search for configs with same project
        // and main class. If it finds 1 it will be returned. If more than 1 is found
        // it will call chooseConfiguration() which brings up a dialog asking the
        // user to choose one config.
        ILaunchConfiguration config = super.findLaunchConfiguration(type, configType);
        if (config != null) {
            List<ILaunchConfiguration> configs = filterConfigs(Collections.singletonList(config));
            if (configs.size() == 0) {
                config = null;
            }
        }
        return config;
    }
    
    @Override
    protected void launch(IType type, String mode) {
        // We override this to be able to filter out existing launch configurations
        // with different device family.
        ILaunchConfiguration config = findLaunchConfiguration(type, getConfigurationType());
        if (config == null) {
            config = createConfiguration(type);
        }
        if (config != null) {
            DebugUITools.launch(config, mode);
        }
    }
}
