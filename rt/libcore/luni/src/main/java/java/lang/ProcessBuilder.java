/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Creates operating system processes. See {@link Process} for documentation and
 * example usage.
 */
public final class ProcessBuilder {

    private List<String> command;
    private File directory;
    private Map<String, String> environment;
    private boolean redirectErrorStream;

    /**
     * Constructs a new {@code ProcessBuilder} instance with the specified
     * operating system program and its arguments.
     *
     * @param command
     *            the requested operating system program and its arguments.
     */
    public ProcessBuilder(String... command) {
        this(new ArrayList<String>(Arrays.asList(command)));
    }

    /**
     * Constructs a new {@code ProcessBuilder} instance with the specified
     * operating system program and its arguments. Note that the list passed to
     * this constructor is not copied, so any subsequent updates to it are
     * reflected in this instance's state.
     *
     * @param command
     *            the requested operating system program and its arguments.
     * @throws NullPointerException
     *             if {@code command} is {@code null}.
     */
    public ProcessBuilder(List<String> command) {
        if (command == null) {
            throw new NullPointerException("command == null");
        }
        this.command = command;

        // use a hashtable to prevent nulls from sneaking in
        this.environment = new Hashtable<String, String>(System.getenv());
    }

    /**
     * Returns this process builder's current program and arguments. Note that
     * the returned list is not a copy and modifications to it will change the
     * state of this instance.
     *
     * @return this process builder's program and arguments.
     */
    public List<String> command() {
        return command;
    }

    /**
     * Changes the program and arguments of this process builder.
     *
     * @param command
     *            the new operating system program and its arguments.
     * @return this process builder instance.
     */
    public ProcessBuilder command(String... command) {
        return command(new ArrayList<String>(Arrays.asList(command)));
    }

    /**
     * Changes the program and arguments of this process builder. Note that the
     * list passed to this method is not copied, so any subsequent updates to it
     * are reflected in this instance's state.
     *
     * @param command
     *            the new operating system program and its arguments.
     * @return this process builder instance.
     * @throws NullPointerException
     *             if {@code command} is {@code null}.
     */
    public ProcessBuilder command(List<String> command) {
        if (command == null) {
            throw new NullPointerException("command == null");
        }
        this.command = command;
        return this;
    }

    /**
     * Returns the working directory of this process builder. If {@code null} is
     * returned, then the working directory of the Java process is used when a
     * process is started.
     *
     * @return the current working directory, may be {@code null}.
     */
    public File directory() {
        return directory;
    }

    /**
     * Changes the working directory of this process builder. If the specified
     * directory is {@code null}, then the working directory of the Java
     * process is used when a process is started.
     *
     * @param directory
     *            the new working directory for this process builder.
     * @return this process builder instance.
     */
    public ProcessBuilder directory(File directory) {
        this.directory = directory;
        return this;
    }

    /**
     * Returns this process builder's current environment. When a process
     * builder instance is created, the environment is populated with a copy of
     * the environment, as returned by {@link System#getenv()}. Note that the
     * map returned by this method is not a copy and any changes made to it are
     * reflected in this instance's state.
     *
     * @return the map containing this process builder's environment variables.
     */
    public Map<String, String> environment() {
        return environment;
    }

    /**
     * Indicates whether the standard error should be redirected to standard
     * output. If redirected, the {@link Process#getErrorStream()} will always
     * return end of stream and standard error is written to
     * {@link Process#getInputStream()}.
     *
     * @return {@code true} if the standard error is redirected; {@code false}
     *         otherwise.
     */
    public boolean redirectErrorStream() {
        return redirectErrorStream;
    }

    /**
     * Changes the state of whether or not standard error is redirected to
     * standard output.
     *
     * @param redirectErrorStream
     *            {@code true} to redirect standard error, {@code false}
     *            otherwise.
     * @return this process builder instance.
     */
    public ProcessBuilder redirectErrorStream(boolean redirectErrorStream) {
        this.redirectErrorStream = redirectErrorStream;
        return this;
    }

    /**
     * Starts a new process based on the current state of this process builder.
     *
     * @return the new {@code Process} instance.
     * @throws NullPointerException
     *             if any of the elements of {@link #command()} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@link #command()} is empty.
     * @throws IOException
     *             if an I/O error happens.
     */
    public Process start() throws IOException {
        // We push responsibility for argument checking into ProcessManager.
        String[] cmdArray = command.toArray(new String[command.size()]);
        String[] envArray = new String[environment.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : environment.entrySet()) {
            envArray[i++] = entry.getKey() + "=" + entry.getValue();
        }
        return ProcessManager.getInstance().exec(cmdArray, envArray, directory, redirectErrorStream);
    }
}
