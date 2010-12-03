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

package java.lang;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import org.apache.harmony.luni.internal.process.SystemProcess;

/**
 * Allows Java applications to interface with the environment in which they are
 * running. Applications can not create an instance of this class, but they can
 * get a singleton instance by invoking {@link #getRuntime()}.
 *
 * @see System
 */
public class Runtime {

    private static final Runtime runtime = new Runtime();
    
    /*
    * This class, with the exception of the exec() APIs, must be implemented by the
    * VM vendor. The exec() APIs must first do any required security checks, and
    * then call org.apache.harmony.luni.internal.process.SystemProcess.create().
    * The Runtime interface.
    */

    /**
     * Prevent this class from being instantiated
     */
    private Runtime(){
        //do nothing
    }

    /**
     * Executes the specified command and its arguments in a separate platform
     * process. The new process inherits the environment of the caller. Calling
     * this method is equivalent to calling {@code exec(progArray, null, null)}.
     *
     * @param progArray
     *            the array containing the program to execute as well as any
     *            arguments to the program.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String[] progArray) throws java.io.IOException {
        return exec(progArray, null, null);
    }

    /**
     * Executes the specified command and its arguments in a separate platform
     * process. The new process uses the environment provided in {@code envp}.
     * Calling this method is equivalent to calling
     * {@code exec(progArray, envp, null)}.
     *
     * @param progArray
     *            the array containing the program to execute as well as any
     *            arguments to the program.
     * @param envp
     *            the array containing the environment to start the new process
     *            in.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String[] progArray, String[] envp) throws java.io.IOException {
        return exec(progArray, envp);
    }

    /**
     * Executes the specified command and its arguments in a separate platform
     * process. The new process uses the environment provided in {@code envp}
     * and the working directory specified by {@code directory}.
     *
     * @param progArray
     *            the array containing the program to execute as well as any
     *            arguments to the program.
     * @param envp
     *            the array containing the environment to start the new process
     *            in.
     * @param directory
     *            the directory in which to execute the program. If {@code null},
     *            execute if in the same directory as the parent process.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String[] progArray, String[] envp, File directory)
            throws java.io.IOException {
        if (envp == null) {
            envp = new String[0];
        }
        return SystemProcess.create(progArray, envp, directory);
    }

    /**
     * Executes the specified program in a separate platform process. The new
     * process inherits the environment of the caller. Calling this method is
     * equivalent to calling {@code exec(prog, null, null)}.
     *
     * @param prog
     *            the name of the program to execute.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String prog) throws java.io.IOException {
        return null;
    }

    /**
     * Executes the specified program in a separate platform process. The new
     * process uses the environment provided in {@code envp}. Calling this
     * method is equivalent to calling {@code exec(prog, envp, null)}.
     *
     * @param prog
     *            the name of the program to execute.
     * @param envp
     *            the array containing the environment to start the new process
     *            in.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String prog, String[] envp) throws java.io.IOException {
        return null;
    }

    /**
     * Executes the specified program in a separate platform process. The new
     * process uses the environment provided in {@code envp} and the working
     * directory specified by {@code directory}.
     *
     * @param prog
     *            the name of the program to execute.
     * @param envp
     *            the array containing the environment to start the new process
     *            in.
     * @param directory
     *            the directory in which to execute the program. If {@code null},
     *            execute if in the same directory as the parent process.
     * @return the new {@code Process} object that represents the platform
     *         process.
     * @throws java.io.IOException
     *             if the requested program can not be executed.
     * @throws SecurityException
     *             if the current {@code SecurityManager} disallows program
     *             execution.
     * @see SecurityManager#checkExec
     */
    public Process exec(String prog, String[] envp, File directory) throws java.io.IOException {
        // Start (C) Android
        if (prog == null) {
            throw new NullPointerException();
        }
        if (prog.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (envp != null) {
            if (envp.length != 0) {
                for (int i = 0; i < envp.length; i++) {
                    if (envp[i] == null) {
                        throw new NullPointerException("An element of envp shouldn't be empty.");
                    }
                }
            } else {
                envp = null;
            }
        }

        StringTokenizer st = new StringTokenizer(prog);
        String[] cmdarray = new String[st.countTokens()];
        int i = 0;

        while (st.hasMoreTokens()) {
            cmdarray[i++] = st.nextToken();

        }

        return exec(cmdarray, envp, directory);
        // End (C) Android
    }

    /**
     * Causes the virtual machine to stop running and the program to exit. If
     * {@link #runFinalizersOnExit(boolean)} has been previously invoked with a
     * {@code true} argument, then all objects will be properly
     * garbage-collected and finalized first.
     *
     * @param code
     *            the return code. By convention, non-zero return codes indicate
     *            abnormal terminations.
     * @throws SecurityException
     *             if the current {@code SecurityManager} does not allow the
     *             running thread to terminate the virtual machine.
     * @see SecurityManager#checkExit
     */
    public native void exit(int code);

    /**
     * Returns the amount of free memory resources which are available to the
     * running program.
     * 
     * @return the approximate amount of free memory, measured in bytes.
     */
    public native long freeMemory();

    /**
     * Indicates to the virtual machine that it would be a good time to run the
     * garbage collector. Note that this is a hint only. There is no guarantee
     * that the garbage collector will actually be run.
     */
    public native void gc();

    /**
     * Returns the single {@code Runtime} instance.
     * 
     * @return the {@code Runtime} object for the current application.
     */
    public static Runtime getRuntime() {
        return runtime;
    }

    /**
     * Loads and links the dynamic library that is identified through the
     * specified path. This method is similar to {@link #loadLibrary(String)},
     * but it accepts a full path specification whereas {@code loadLibrary} just
     * accepts the name of the library to load.
     *
     * @param pathName
     *            the absolute (platform dependent) path to the library to load.
     * @throws UnsatisfiedLinkError
     *             if the library can not be loaded.
     * @throws SecurityException
     *             if the current {@code SecurityManager} does not allow to load
     *             the library.
     * @see SecurityManager#checkLink
     */
    public native void load(String pathName);

    /**
     * Loads and links the library with the specified name. The mapping of the
     * specified library name to the full path for loading the library is
     * implementation-dependent.
     *
     * @param libName
     *            the name of the library to load.
     * @throws UnsatisfiedLinkError
     *             if the library can not be loaded.
     * @throws SecurityException
     *             if the current {@code SecurityManager} does not allow to load
     *             the library.
     * @see SecurityManager#checkLink
     */
    public native void loadLibrary(String libName);

    /**
     * Provides a hint to the virtual machine that it would be useful to attempt
     * to perform any outstanding object finalizations.
     */
    public native void runFinalization();

    /**
     * Sets the flag that indicates whether all objects are finalized when the
     * virtual machine is about to exit. Note that all finalization which occurs
     * when the system is exiting is performed after all running threads have
     * been terminated.
     *
     * @param run
     *            {@code true} to enable finalization on exit, {@code false} to
     *            disable it.
     * @deprecated This method is unsafe.
     */
    @Deprecated
    public native static void runFinalizersOnExit(boolean run);

    /**
     * Returns the total amount of memory which is available to the running
     * program.
     *
     * @return the total amount of memory, measured in bytes.
     */
    public native long totalMemory();

    /**
     * Switches the output of debug information for instructions on or off.
     *
     * @param enable
     *            {@code true} to switch tracing on, {@code false} to switch it
     *            off.
     */
    public void traceInstructions(boolean enable) {
        return;
    }

    /**
     * Switches the output of debug information for methods on or off.
     *
     * @param enable
     *            {@code true} to switch tracing on, {@code false} to switch it
     *            off.
     */
    public void traceMethodCalls(boolean enable) {
        return;
    }

    /**
     * Returns the localized version of the specified input stream. The input
     * stream that is returned automatically converts all characters from the
     * local character set to Unicode after reading them from the underlying
     * stream.
     *
     * @param stream
     *            the input stream to localize.
     * @return the localized input stream.
     * @deprecated Use {@link java.io.InputStreamReader}.
     */
    @Deprecated
    public native InputStream getLocalizedInputStream(InputStream stream);

    /**
     * Returns the localized version of the specified output stream. The output
     * stream that is returned automatically converts all characters from
     * Unicode to the local character set before writing them to the underlying
     * stream.
     *
     * @param stream
     *            the output stream to localize.
     * @return the localized output stream.
     * @deprecated Use {@link java.io.OutputStreamWriter}.
     */
    @Deprecated
    public native OutputStream getLocalizedOutputStream(OutputStream stream);

    /**
     * Registers a virtual-machine shutdown hook. A shutdown hook is a
     * {@code Thread} that is ready to run, but has not yet been started. All
     * registered shutdown hooks will be executed once the virtual machine shuts
     * down properly. A proper shutdown happens when either the
     * {@link #exit(int)} method is called or the surrounding system decides to
     * terminate the application, for example in response to a {@code CTRL-C} or
     * a system-wide shutdown. A termination of the virtual machine due to the
     * {@link #halt(int)} method, an {@link Error} or a {@code SIGKILL}, in
     * contrast, is not considered a proper shutdown. In these cases the
     * shutdown hooks will not be run.
     * <p>
     * Shutdown hooks are run concurrently and in an unspecified order. Hooks
     * failing due to an unhandled exception are not a problem, but the stack
     * trace might be printed to the console. Once initiated, the whole shutdown
     * process can only be terminated by calling {@code halt()}.
     * <p>
     * If {@link #runFinalizersOnExit(boolean)} has been called with a {@code
     * true} argument, garbage collection and finalization will take place after
     * all hooks are either finished or have failed. Then the virtual machine
     * terminates.
     * <p>
     * It is recommended that shutdown hooks do not do any time-consuming
     * activities, in order to not hold up the shutdown process longer than
     * necessary.
     *
     * @param hook
     *            the shutdown hook to register.
     * @throws IllegalArgumentException
     *             if the hook has already been started or if it has already
     *             been registered.
     * @throws IllegalStateException
     *             if the virtual machine is already shutting down.
     * @throws SecurityException
     *             if a SecurityManager is registered and the calling code
     *             doesn't have the RuntimePermission("shutdownHooks").
     */
    public native void addShutdownHook(Thread hook); /* {
        // Check hook for null
        if (hook == null)
            throw new NullPointerException("null is not allowed here");
                
        return;
    }*/

    /**
     * Unregisters a previously registered virtual machine shutdown hook.
     *
     * @param hook
     *            the shutdown hook to remove.
     * @return {@code true} if the hook has been removed successfully; {@code
     *         false} otherwise.
     * @throws IllegalStateException
     *             if the virtual machine is already shutting down.
     * @throws SecurityException
     *             if a SecurityManager is registered and the calling code
     *             doesn't have the RuntimePermission("shutdownHooks").
     */
    public native boolean removeShutdownHook(Thread hook);/* {
        // Check hook for null
        if (hook == null)
            throw new NullPointerException("null is not allowed here");
                
        return false;
    }*/

    /**
     * Causes the virtual machine to stop running, and the program to exit.
     * Neither shutdown hooks nor finalizers are run before.
     *
     * @param code
     *            the return code. By convention, non-zero return codes indicate
     *            abnormal terminations.
     * @throws SecurityException
     *             if the current {@code SecurityManager} does not allow the
     *             running thread to terminate the virtual machine.
     * @see SecurityManager#checkExit
     * @see #addShutdownHook(Thread)
     * @see #removeShutdownHook(Thread)
     * @see #runFinalizersOnExit(boolean)
     */
    public native void halt(int code);

    /**
     * Returns the number of processors available to the virtual machine.
     *
     * @return the number of available processors, at least 1.
     */
    public native int availableProcessors();

    /**
     * Returns the maximum amount of memory that may be used by the virtual
     * machine, or {@code Long.MAX_VALUE} if there is no such limit.
     *
     * @return the maximum amount of memory that the virtual machine will try to
     *         allocate, measured in bytes.
     */
    public native long maxMemory();

}
