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
 * and waiting for the external process, as well as querying its exit value.
 *
 * @see Runtime#exec
 * @see ProcessBuilder#start()
 */
public abstract class Process {

    /**
     * Terminates this process and closes any associated streams.
     */
    abstract public void destroy();

    /**
     * Returns the exit value of the native process represented by this object.
     * It is available only when the native process has terminated.
     * 
     * @return the exit value of this process.
     * @throws IllegalThreadStateException
     *             if this process has not terminated.
     */
    abstract public int exitValue();

    /**
     * Returns an input stream that is connected to the error stream
     * <em>(stderr)</em> of the native process represented by this object.
     * 
     * @return the input stream to read from the error stream associated with
     *         the native process.
     */
    abstract public InputStream getErrorStream();

    /**
     * Returns an input stream that is connected to the standard output stream
     * <em>(stdout)</em> of the native process represented by this object.
     * 
     * @return the input stream to read from the output stream associated with
     *         the native process.
     */
    abstract public InputStream getInputStream();

    /**
     * Returns an output stream that is connected to the standard input stream
     * <em>(stdin)</em> of the native process represented by this object.
     * 
     * @return the output stream to write to the input stream associated with
     *         the native process.
     */
    abstract public OutputStream getOutputStream();

    /**
     * Causes the calling thread to wait for the native process associated with
     * this object to finish executing.
     * 
     * @return the exit value of the native process being waited on.
     * @throws InterruptedException
     *             if the calling thread is interrupted.
     */
    abstract public int waitFor() throws InterruptedException;
}
