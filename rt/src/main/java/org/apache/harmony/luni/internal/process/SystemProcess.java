/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.internal.process;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.harmony.luni.util.Util;

/**
 * This class provides the {@link Runtime#exec(String[], String[], File)}
 * implementation.
 * 
 * Instances of class Process provide control of and access to platform
 * processes. This is the concrete implementation of class java.lang.Process.
 * 
 * @see java.lang.Runtime
 */
public class SystemProcess extends Process {

    // Fill in the JNI id caches
    private static native void oneTimeInitialization();

    static {
        oneTimeInitialization();
    }

    /**
     * Constructs a process connected to an OS process.
     * 
     * @param progArray the array containing the program to execute as well as
     *        any arguments to the program.
     * @param envp the array containing the environment to start the new process
     *        in.
     * @param directory the directory to start the process in, or null
     * 
     * @return The new process.
     * 
     * @throws IOException when a problem occurs
     * @throws NullPointerException when progArray or envp are null, or contain
     *         a null element in the array.
     */
    public static Process create(String[] progArray, String[] envp, final File directory)
            throws IOException {
        final byte[][] progBytes, envBytes;
        progBytes = new byte[progArray.length][];
        for (int i = 0; i < progArray.length; i++) {
            progBytes[i] = Util.getBytes(progArray[i]);
        }
        envBytes = new byte[envp.length][];
        for (int i = 0; i < envp.length; i++) {
            envBytes[i] = envp[i].getBytes();
        }

        final SystemProcess p = new SystemProcess();

        p.lock = new Object();

        Runnable waitingThread = new Runnable() {
            public void run() {
                long[] procVals = null;
                try {
                    procVals = createImpl(p, progBytes, envBytes, directory == null ? null
                            : Util.getBytes(directory.getPath()));
                } catch (Throwable e) {
                    /* Creation errors need to be passed to the user thread. */
                    synchronized (p.lock) {
                        p.exception = e;
                        p.waiterStarted = true;
                        p.lock.notifyAll();
                    }
                    return;
                }
                p.handle = procVals[0];
                p.in = new BufferedOutputStream(new ProcessOutputStream(procVals[1]));
                p.out = new BufferedInputStream(new ProcessInputStream(procVals[2]));
                p.err = new BufferedInputStream(new ProcessInputStream(procVals[3]));

                synchronized (p.lock) {
                    p.waiterStarted = true;
                    p.lock.notifyAll();
                }

                p.exitCode = p.waitForCompletionImpl();
                synchronized (p.lock) {
                    p.closeImpl();
                    p.handle = -1;
                    p.exitCodeAvailable = true;
                    try {
                        p.in.close();
                    } catch (IOException e) {
                    }
                    p.lock.notifyAll();
                }
            }
        };
        Thread wait = new Thread(waitingThread);
        wait.setDaemon(true);
        wait.start();

        synchronized (p.lock) {
            boolean interrupted = false;
            while (!p.waiterStarted) {
                try {
                    p.lock.wait();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            if (p.exception != null) {
                /* Re-throw exception that originated in the helper thread */
                p.exception.fillInStackTrace();
                if (p.exception instanceof IOException) {
                    throw (IOException) p.exception;
                } else if (p.exception instanceof Error) {
                    throw (Error) p.exception;
                } else {
                    throw (RuntimeException) p.exception;
                }
            }
        }

        return p;
    }

    protected synchronized static native long[] createImpl(Process p, byte[][] progArray,
            byte[][] envp, byte[] dir) throws IOException;

    private InputStream err; // STDERR for the process

    private InputStream out; // STDOUT for the process

    private OutputStream in; // STDIN for the process

    private long handle = -1; // Handle to OS process struct

    /**
     * When exitCodeAvailable == 1, exitCode has a meaning. When exitCode is
     * available, it means that the underlying process had finished (for sure).
     */
    private boolean exitCodeAvailable;

    private int exitCode;

    private static class Lock {}
    private Object lock;

    private boolean waiterStarted;

    private Throwable exception;

    /**
     * Prevents this class from being instantiated outside of this class.
     */
    private SystemProcess() {
        super();
    }

    /**
     * Internal implementation of the code that stops the process associated
     * with the receiver.
     */
    private native void destroyImpl();

    /**
     * Internal implementation of the code that closes the handle.
     */
    native void closeImpl();

    /**
     * Internal implementation of the code that waits for the process to
     * complete.
     * 
     * @return int The exit value of the process.
     */
    native int waitForCompletionImpl();

    @Override
    public void destroy() {
        synchronized (lock) {
            if (handle != -1) {
                destroyImpl();
            }
        }
    }

    @Override
    public int exitValue() {
        synchronized (lock) {
            if (!exitCodeAvailable) {
                throw new IllegalThreadStateException();
            }
            return exitCode;
        }
    }

    @Override
    public InputStream getErrorStream() {
        return err;
    }

    @Override
    public InputStream getInputStream() {
        return out;
    }

    @Override
    public OutputStream getOutputStream() {
        return in;
    }

    @Override
    public int waitFor() throws InterruptedException {
        synchronized (lock) {
            /*
             * if the exitCode is available, it means that the underlying OS
             * process is already dead, so the exitCode is just returned without
             * any other OS checks
             */
            while (!exitCodeAvailable) {
                lock.wait();
            }
            return exitCode;
        }
    }
}
