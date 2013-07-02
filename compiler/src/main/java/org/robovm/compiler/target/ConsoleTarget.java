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
package org.robovm.compiler.target;

import java.io.IOException;
import java.io.OutputStream;

import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.util.Executor;
import org.robovm.compiler.util.io.OpenOnWriteFileOutputStream;


/**
 * Console {@link Target} implementation.
 */
public class ConsoleTarget extends AbstractTarget {
    private OS os;
    private Arch arch;

    public ConsoleTarget() {
    }
    
    public OS getOs() {
        return os;
    }

    public Arch getArch() {
        return arch;
    }
    
    @Override
    protected Executor createExecutor(LaunchParameters launchParameters)
            throws IOException {

        OutputStream out = System.out;
        OutputStream err = System.err;
        if (launchParameters.getStdoutFifo() != null) {
            out = new OpenOnWriteFileOutputStream(launchParameters.getStdoutFifo());
        }
        if (launchParameters.getStderrFifo() != null) {
            err = new OpenOnWriteFileOutputStream(launchParameters.getStderrFifo());
        }
        return super.createExecutor(launchParameters).out(out).err(err).closeOutputStreams(true);
    }

    public void init(Config config) {
        super.init(config);
        os = config.getOs();
        if (os == null) {
            os = OS.getDefaultOS();
        }
        arch = config.getArch();
        if (arch == null) {
            arch = Arch.getDefaultArch();
        }
    }
}
