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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    protected Launcher createLauncher(LaunchParameters launchParameters) throws IOException {
        File dir = config.isSkipInstall() ? config.getTmpDir() : config.getInstallDir();
        OutputStream out = System.out;
        OutputStream err = System.err;
        if (launchParameters.getStdoutFifo() != null) {
            out = new OpenOnWriteFileOutputStream(launchParameters.getStdoutFifo());
        }
        if (launchParameters.getStderrFifo() != null) {
            err = new OpenOnWriteFileOutputStream(launchParameters.getStderrFifo());
        }
        
        return createExecutor(launchParameters, new File(dir, 
                config.getExecutableName()).getAbsolutePath(), 
                launchParameters.getArguments())
                .out(out).err(err).closeOutputStreams(true);
    }
    
    protected Executor createExecutor(LaunchParameters launchParameters, String cmd, List<? extends Object> args) throws IOException {
        Map<String, String> env = launchParameters.getEnvironment();
        return new Executor(config.getLogger(), cmd)
            .args(args)
            .wd(launchParameters.getWorkingDirectory())
            .inheritEnv(env == null)
            .env(env == null ? Collections.<String, String>emptyMap() : env);
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
