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
package org.robovm.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.robovm.compiler.clazz.Path;

public interface Target {

    OS getOS();
    Arch getArch();
    
    String getInstallRelativeArchivePath(Path path);

    boolean canLaunchInPlace();

    void build(List<File> objectFiles) throws IOException;

    void install() throws IOException;

    Process launch(LaunchParameters launchParameters) throws IOException;

    LaunchParameters createLaunchParameters();
    
    void init(Config config);
}
