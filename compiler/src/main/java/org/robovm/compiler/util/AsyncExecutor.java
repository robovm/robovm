/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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
package org.robovm.compiler.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.ProcessDestroyer;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.launcher.CommandLauncher;
import org.apache.commons.exec.launcher.CommandLauncherFactory;

/**
 * The default class to start a subprocess. The implementation
 * allows to
 * <ul>
 *  <li>set a current working directory for the subprocess</li>
 *  <li>provide a set of environment variables passed to the subprocess</li>
 *  <li>capture the subprocess output of stdout and stderr using an ExecuteStreamHandler</li>
 *  <li>kill long-running processes using an ExecuteWatchdog</li>
 *  <li>define a set of expected exit values</li>
 *  <li>terminate any started processes when the main process is terminating using a ProcessDestroyer</li>
 * </ul>
 *
 * The following example shows the basic usage:
 *
 * <pre>
 * Executor exec = new DefaultExecutor();
 * CommandLine cl = new CommandLine("ls -l");
 * int exitvalue = exec.execute(cl);
 * </pre>
 */
public class AsyncExecutor {

    private static final AtomicInteger threadCounter = new AtomicInteger();
    
    /** taking care of output and error stream */
    private ExecuteStreamHandler streamHandler;

    /** the working directory of the process */
    private File workingDirectory;

    /** monitoring of long running processes */
    private ExecuteWatchdog watchdog;

    /** the exit values considerd to be successful */
    private int[] exitValues;

    /** launches the command in a new process */
    private final CommandLauncher launcher;

    /** optional cleanup of started processes */ 
    private ProcessDestroyer processDestroyer;

    /**
     * Default Constrctor
     */
    public AsyncExecutor() {
        this.streamHandler = new PumpStreamHandler();
        this.launcher = CommandLauncherFactory.createVMLauncher();
        this.exitValues = new int[0];
    }

    /**
     * Get the StreamHandler used for providing input and
     * retriving the output.
     * 
     * @return the StreamHandler 
     */
    public ExecuteStreamHandler getStreamHandler() {
        return streamHandler;
    }

    /**
     * Set the StreamHandler used for providing input and
     * retriving the output.
     *
     * @param streamHandler the StreamHandler
     */
    public void setStreamHandler(ExecuteStreamHandler streamHandler) {
        this.streamHandler = streamHandler;
    }

    /**
     * Get the watchdog used to kill of processes running,
     * typically, too long time.
     *
     * @return the watchdog
     */
    public ExecuteWatchdog getWatchdog() {
        return watchdog;
    }

    /**
     * Set the watchdog used to kill of processes running, 
     * typically, too long time.
     *
     * @param watchDog the watchdog
     */
    public void setWatchdog(ExecuteWatchdog watchDog) {
        this.watchdog = watchDog;
    }

    /**
     * Set the handler for cleanup of started processes if the main process
     * is going to terminate.
     *
     * @return the ProcessDestroyer
     */
    public ProcessDestroyer getProcessDestroyer() {
      return this.processDestroyer;
    }

    /**
     * Get the handler for cleanup of started processes if the main process
     * is going to terminate.
     *
     * @param processDestroyer the ProcessDestroyer
     */
    public void setProcessDestroyer(ProcessDestroyer processDestroyer) {
      this.processDestroyer = processDestroyer;
    }

    /**
     * Get the working directory of the created process.
     *
     * @return the working directory
     */
    public File getWorkingDirectory() {
        return workingDirectory;
    }

    /**
     * Set the working directory of the created process. The
     * working directory must exist when you start the process.
     *
     * @param dir the working directory
     */
    public void setWorkingDirectory(File dir) {
        this.workingDirectory = dir;
    }

    /**
     * Methods for starting asynchronous execution. The child process inherits
     * all environment variables of the parent process.
     *
     * @param command the command to execute
     * @return the launched {@link Process}
     * @throws ExecuteException execution of subprocess failed
     */
    public Process execute(final CommandLine command) throws IOException {
        return execute(command, (Map<String, String>) null);
    }

    /**
     * Methods for starting asynchronous execution.
     *
     * @param command the command to execute
     * @param environment The environment for the new process. If null, the
     *          environment of the current process is used.
     * @return the launched {@link Process}
     * @throws ExecuteException execution of subprocess failed
     */
    public Process execute(final CommandLine command, Map<String, String> environment)
            throws IOException {

        if (workingDirectory != null && !workingDirectory.exists()) {
            throw new IOException(workingDirectory + " doesn't exist.");
        }
        
        return executeInternal(command, environment, workingDirectory, streamHandler, null);
    }

    /**
     * Methods for starting asynchronous execution. The child process inherits
     * all environment variables of the parent process. Result provided to
     * callback handler.
     *
     * @param command the command to execute
     * @param handler capture process termination and exit code
     * @return the launched {@link Process}
     * @throws ExecuteException execution of subprocess failed
     */
    public Process execute(final CommandLine command, ExecuteResultHandler handler)
            throws IOException {
        return execute(command, null, handler);
    }

    /**
     * Methods for starting asynchronous execution. The child process inherits
     * all environment variables of the parent process. Result provided to
     * callback handler.
     *
     * @param command the command to execute
     * @param environment The environment for the new process. If null, the
     *          environment of the current process is used.
     * @param handler capture process termination and exit code 
     * @return the launched {@link Process}
     * @throws ExecuteException execution of subprocess failed     
     */
    public Process execute(final CommandLine command, final Map<String, String> environment,
            final ExecuteResultHandler handler) throws IOException {

        if (workingDirectory != null && !workingDirectory.exists()) {
            throw new IOException(workingDirectory + " doesn't exist.");
        }

        return executeInternal(command, environment, workingDirectory, streamHandler, handler);
    }


    /**
     * Define the exit code of the process to considered
     * successful.
     *
     * @param value the exit code representing successful execution
     */
    public void setExitValue(final int value) {
        this.setExitValues(new int[] {value});
    }


    /**
     * Define the exit code of the process to considered
     * successful. The caller can pass one of the following values
     * <ul>
     *  <li>an array of exit values to be considered successful</li>
     *  <li>an empty array for auto-detect of successful exit codes</li>
     *  <li>null to indicate to skip checking of exit codes</li>
     * </ul>
     *
     * @param values a list of the exit codes
     */
    public void setExitValues(final int[] values) {
        this.exitValues = (values == null ? null : (int[]) values.clone());
    }

    /**
     * Checks whether <code>exitValue</code> signals a failure. If no
     * exit values are set than the default conventions of the OS is
     * used. e.g. most OS regard an exit code of '0' as successful
     * execution and everything else as failure.
     *
     * @param exitValue the exit value (return code) to be checked
     * @return <code>true</code> if <code>exitValue</code> signals a failure
     */
    public boolean isFailure(final int exitValue) {

        if(this.exitValues == null) {
            return false;
        }
        else if(this.exitValues.length == 0) {
            return this.launcher.isFailure(exitValue);
        }
        else {
            for(int i=0; i<this.exitValues.length; i++) {
                if(this.exitValues[i] == exitValue) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a process that runs a command.
     *
     * @param command
     *            the command to run
     * @param env
     *            the environment for the command
     * @param dir
     *            the working directory for the command
     * @return the process started
     * @throws IOException
     *             forwarded from the particular launcher used
     */
    protected Process launch(final CommandLine command, final Map<String, String> env,
            final File dir) throws IOException {

        if (this.launcher == null) {
            throw new IllegalStateException("CommandLauncher can not be null");
        }

        if (dir != null && !dir.exists()) {
            throw new IOException(dir + " doesn't exist.");
        }
        return this.launcher.exec(command, env, dir);
    }

    /**
     * Close the streams belonging to the given Process. In the
     * original implementation all exceptions were dropped which
     * is probably not a good thing. On the other hand the signature
     * allows throwing an IOException so the curent implementation
     * might be quite okay.
     * 
     * @param process the <CODE>Process</CODE>.
     * @throws IOException closing one of the three streams failed
     */
    private void closeStreams(final Process process) throws IOException {

        IOException caught = null;

        try {
            process.getInputStream().close();
        }
        catch(IOException e) {
            caught = e;
        }

        try {
            process.getOutputStream().close();
        }
        catch(IOException e) {
            caught = e;
        }

        try {
            process.getErrorStream().close();
        }
        catch(IOException e) {
            caught = e;
        }

        if(caught != null) {
            throw caught;
        }
    }

    /**
     * Execute an internal process.
     *
     * @param command the command to execute
     * @param environment the execution enviroment
     * @param dir the working directory
     * @param streams process the streams (in, out, err) of the process
     * @return the launched {@link Process}
     * @throws IOException executing the process failed
     */
    private Process executeInternal(final CommandLine command, final Map<String, String> environment,
            final File dir, final ExecuteStreamHandler streams, 
            final ExecuteResultHandler handler) throws IOException {

        final Process process = this.launch(command, environment, dir);

        try {
            streams.setProcessInputStream(process.getOutputStream());
            streams.setProcessOutputStream(process.getInputStream());
            streams.setProcessErrorStream(process.getErrorStream());
        } catch (IOException e) {
            process.destroy();
            throw e;
        }

        streams.start();

        // add the process to the list of those to destroy if the VM exits
        if(this.getProcessDestroyer() != null) {
          this.getProcessDestroyer().add(process);
        }

        if (watchdog != null) {
            watchdog.start(process);
        }
            
        Thread waitForThread = new Thread(AsyncExecutor.class.getSimpleName() 
                + ".waitFor-" + threadCounter.incrementAndGet()) {
            
            public void run() {
                int exitValue = Executor.INVALID_EXITVALUE;
                try {                    
                    try {
                        exitValue = process.waitFor();
                    } catch (InterruptedException e) {
                        process.destroy();
                    }

                    if (watchdog != null) {
                        watchdog.stop();
                    }
                    streams.stop();
                    closeStreams(process);

                    if (watchdog != null) {
                        try {
                            watchdog.checkException();
                        } catch (Exception e) {
                            throw new IOException(e.getMessage());
                        }
                    }
                
                    if (handler != null) {
                        handler.onProcessComplete(exitValue);
                    }
                } catch (ExecuteException e) {
                    if (handler != null) {
                        handler.onProcessFailed(e);
                    }
                } catch(Exception e) {
                    if (handler != null) {
                        handler.onProcessFailed(new ExecuteException("Execution failed", exitValue, e));
                    }
                } finally {
                    // remove the process to the list of those to destroy if the VM exits
                    if(AsyncExecutor.this.getProcessDestroyer() != null) {
                        AsyncExecutor.this.getProcessDestroyer().remove(process);
                    }
                }

            }
        };
        waitForThread.setDaemon(true);
        waitForThread.start();

        return process;
    }
}
