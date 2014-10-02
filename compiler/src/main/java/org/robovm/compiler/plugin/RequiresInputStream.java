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

import java.io.InputStream;

import org.robovm.compiler.plugin.LaunchPlugin;

/**
 * Implemented by LaunchPlugins that require an input stream
 * (keyboard input) to operate. The method of this interface
 * is supposed to be called after {@link LaunchPlugin#beforeLaunch(org.robovm.compiler.config.Config, org.robovm.compiler.target.LaunchParameters)}
 * and before {@link LaunchPlugin#afterLaunch(org.robovm.compiler.config.Config, org.robovm.compiler.target.LaunchParameters, Process)}
 * @author badlogic
 *
 */
public interface RequiresInputStream {
    public void setInputStream(InputStream in);
}
