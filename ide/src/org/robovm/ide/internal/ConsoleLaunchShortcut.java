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
package org.robovm.ide.internal;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * @author niklas
 *
 */
public class ConsoleLaunchShortcut extends AbstractLaunchShortcut {

    @Override
    protected String getConfigurationTypeId() {
        return ConsoleLaunchConfigurationDelegate.TYPE_ID;
    }

    @Override
    protected String getConfigurationTypeName() {
        return ConsoleLaunchConfigurationDelegate.TYPE_NAME;
    }

    @Override
    protected void customizeConfiguration(ILaunchConfigurationWorkingCopy wc) {
    }
    
}
