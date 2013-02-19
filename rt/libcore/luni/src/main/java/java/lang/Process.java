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

package java.lang;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents an external process. Enables writing to, reading from, destroying,
 * and waiting for the external process, as well as querying its exit value. Use
 * {@link ProcessBuilder} to create processes.
 *
 * <p>The child process writes its output to two streams, {@code out} and
 * {@code err}. These streams should be read by the parent process using {@link
 * #getInputStream()} and {@link #getErrorStream()} respectively. If these
 * streams are not read, the target process may block while it awaits buffer
 * space. It isn't sufficient to read the streams in sequence; to avoid blocking
 * each of the two streams must have its own reader thread. If you are not
 * interested in differentiating the out and err streams, use {@link
 * ProcessBuilder#redirectErrorStream(boolean) redirectErrorStream(true)} to
 * merge the two streams. This simplifies your reading code and makes it easier
 * to avoid blocking the target process.
 *
 * <p>Running processes hold resources. When a process is no longer used, the
 * process should be closed by calling {@link #destroy}. This will kill the
 * process and release the resources that it holds.
 *
 * <p>For example, to run {@code /system/bin/ping} to ping {@code android.com}:
 * <pre>   {@code
 *   Process process = new ProcessBuilder()
 *       .command("/system/bin/ping", "android.com")
 *       .redirectErrorStream(true)
 *       .start();
 *   try {
 *     InputStream in = process.getInputStream();
 *     OutputStream out = process.getOutputStream();
 *
 *     readStream(in);
 *
 *   } finally {
 *     process.destroy();
 *   }
 * }</pre>
 */
public abstract class Process {

    /**
     * Terminates this process and closes any associated streams.
     */
    public abstract void destroy();

    /**
     * Returns the exit value of the native process represented by this object.
     * It is available only when the native process has terminated.
     *
     * @return the exit value of this process.
     * @throws IllegalThreadStateException
     *             if this process has not terminated.
     */
    public abstract int exitValue();

    /**
     * Returns an input stream that is connected to the error stream
     * <em>(stderr)</em> of the native process represented by this object.
     *
     * @return the input stream to read from the error stream associated with
     *         the native process.
     */
    public abstract InputStream getErrorStream();

    /**
     * Returns an input stream that is connected to the standard output stream
     * <em>(stdout)</em> of the native process represented by this object.
     *
     * @return the input stream to read from the output stream associated with
     *         the native process.
     */
    public abstract InputStream getInputStream();

    /**
     * Returns an output stream that is connected to the standard input stream
     * <em>(stdin)</em> of the native process represented by this object.
     *
     * @return the output stream to write to the input stream associated with
     *         the native process.
     */
    public abstract OutputStream getOutputStream();

    /**
     * Causes the calling thread to wait for the native process associated with
     * this object to finish executing.
     *
     * @return the exit value of the native process being waited on.
     * @throws InterruptedException
     *             if the calling thread is interrupted.
     */
    public abstract int waitFor() throws InterruptedException;
}
