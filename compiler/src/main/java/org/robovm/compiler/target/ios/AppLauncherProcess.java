/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.compiler.target.ios;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.robovm.compiler.log.ErrorOutputStream;
import org.robovm.compiler.log.Logger;
import org.robovm.compiler.target.LaunchParameters;
import org.robovm.compiler.target.Launcher;
import org.robovm.compiler.util.io.OpenOnWriteFileOutputStream;
import org.robovm.libimobiledevice.util.AppLauncher;

/**
 * {@link Process} implementation which runs an app on a device using an
 * {@link AppLauncher}.
 */
class AppLauncherProcess extends Process implements Launcher {
    private final AtomicInteger threadCounter = new AtomicInteger();
    private final Logger log;
    private final AppLauncher launcher;
    private final WaitInputStream in = new WaitInputStream();
    private final WaitInputStream err = new WaitInputStream();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private Thread launcherThread;
    private volatile boolean finished = false;
    private volatile int exitCode = -1;
    private OutputStream errStream;

    AppLauncherProcess(Logger log, AppLauncher launcher, LaunchParameters launchParameters) {
        this.log = log;
        this.launcher = launcher;
        if (launchParameters.getStderrFifo() != null) {
            this.errStream = new OpenOnWriteFileOutputStream(launchParameters.getStderrFifo());
        }
    }

    @Override
    public Process execAsync() throws IOException {
        launcher.install();
        this.launcherThread = new Thread("AppLauncherThread-" + threadCounter.getAndIncrement()) {
            @Override
            public void run() {
                try {
                    exitCode = launcher.launch();
                } catch (Throwable t) {
                    log.error("AppLauncher failed with an exception:", t.getMessage());
                    t.printStackTrace(new PrintStream(new ErrorOutputStream(log), true));
                } finally {
                    IOUtils.closeQuietly(errStream);
                    finished = true;
                    countDownLatch.countDown();
                }
            }
        };
        this.launcherThread.start();
        return this;
    }
    
    @Override
    public OutputStream getOutputStream() {
        return new NullOutputStream();
    }

    @Override
    public InputStream getInputStream() {
        return in;
    }

    @Override
    public InputStream getErrorStream() {
        return err;
    }

    @Override
    public int waitFor() throws InterruptedException {
        countDownLatch.await();
        return exitCode;
    }

    @Override
    public int exitValue() {
        if (!finished) {
            throw new IllegalThreadStateException("Not terminated");
        }
        return exitCode;
    }

    @Override
    public void destroy() {
        launcher.kill();
    }

    private class WaitInputStream extends InputStream {

        @Override
        public int read() throws IOException {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new InterruptedIOException();
            }
            return -1;
        }
        
    }
}
