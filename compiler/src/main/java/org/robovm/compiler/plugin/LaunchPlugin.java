/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.compiler.plugin;

import org.robovm.compiler.config.Config;
import org.robovm.compiler.target.LaunchParameters;

/**
 * Plugin interface which makes it possible to hook into the compilation
 * process and modify classes and methods during the compilation.
 */
public abstract class LaunchPlugin extends Plugin {
    /**
     * Called before the launch of a RoboVM application
     */
    public abstract void beforeLaunch(Config config, LaunchParameters parameters);
    
    /**
     * Called after the launch of a RoboVM application
     */
    public abstract void afterLaunch(Config config, LaunchParameters parameters, Process process);
    
    /**
     * Called when the launch failed
     */
    public abstract void launchFailed(Config config, LaunchParameters parameters);
}
