/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import libcore.io.ErrnoException;
import libcore.io.IoUtils;
import libcore.io.Libcore;
import libcore.util.MutableInt;
import static libcore.io.OsConstants.*;

/**
 * Manages child processes.
 */
final class ProcessManager {
    /**
     * Map from pid to Process. We keep weak references to the Process objects
     * and clean up the entries when no more external references are left. The
     * process objects themselves don't require much memory, but file
     * descriptors (associated with stdin/stdout/stderr in this case) can be
     * a scarce resource.
     */
    private final Map<Integer, ProcessReference> processReferences
            = new HashMap<Integer, ProcessReference>();

    /** Keeps track of garbage-collected Processes. */
    private final ProcessReferenceQueue referenceQueue = new ProcessReferenceQueue();

    private ProcessManager() {
        // Spawn a thread to listen for signals from child processes.
        Thread reaperThread = new Thread(ProcessManager.class.getName()) {
            @Override public void run() {
                watchChildren();
            }
        };
        reaperThread.setDaemon(true);
        reaperThread.start();
    }

    /**
     * Cleans up after garbage collected processes. Requires the lock on the
     * map.
     */
    private void cleanUp() {
        ProcessReference reference;
        while ((reference = referenceQueue.poll()) != null) {
            synchronized (processReferences) {
                processReferences.remove(reference.processId);
            }
        }
    }

    /**
     * Loops indefinitely and calls ProcessManager.onExit() when children exit.
     */
    private void watchChildren() {
        MutableInt status = new MutableInt(-1);
        while (true) {
            try {
                // Wait for children in our process group.
                int pid = Libcore.os.waitpid(0, status, 0);

                // Work out what onExit wants to hear.
                int exitValue;
                if (WIFEXITED(status.value)) {
                    exitValue = WEXITSTATUS(status.value);
                } else if (WIFSIGNALED(status.value)) {
                    exitValue = WTERMSIG(status.value);
                } else if (WIFSTOPPED(status.value)) {
                    exitValue = WSTOPSIG(status.value);
                } else {
                    throw new AssertionError("unexpected status from waitpid: " + status.value);
                }

                onExit(pid, exitValue);
            } catch (ErrnoException errnoException) {
                if (errnoException.errno == ECHILD) {
                    // Expected errno: there are no children to wait for.
                    // onExit will sleep until it is informed of another child coming to life.
                    waitForMoreChildren();
                    continue;
                } else {
                    throw new AssertionError(errnoException);
                }
            }
        }
    }

    /**
     * Called by {@link #watchChildren()} when a child process exits.
     *
     * @param pid ID of process that exited
     * @param exitValue value the process returned upon exit
     */
    private void onExit(int pid, int exitValue) {
        ProcessReference processReference = null;
        synchronized (processReferences) {
            cleanUp();
            processReference = processReferences.remove(pid);
        }
        if (processReference != null) {
            ProcessImpl process = processReference.get();
            if (process != null) {
                process.setExitValue(exitValue);
            }
        }
    }

    private void waitForMoreChildren() {
        synchronized (processReferences) {
            if (processReferences.isEmpty()) {
                // There are no eligible children; wait for one to be added.
                // This wait will return because of the notifyAll call in exec.
                try {
                    processReferences.wait();
                } catch (InterruptedException ex) {
                    // This should never happen.
                    throw new AssertionError("unexpected interrupt");
                }
            } else {
                /*
                 * A new child was spawned just before we entered
                 * the synchronized block. We can just fall through
                 * without doing anything special and land back in
                 * the native waitpid().
                 */
            }
        }
    }

    /**
     * Executes a native process. Fills in in, out, and err and returns the
     * new process ID upon success.
     */
    private static native int exec(String[] command, String[] environment,
            String workingDirectory, FileDescriptor in, FileDescriptor out,
            FileDescriptor err, boolean redirectErrorStream) throws IOException;

    /**
     * Executes a process and returns an object representing it.
     */
    public Process exec(String[] taintedCommand, String[] taintedEnvironment, File workingDirectory,
            boolean redirectErrorStream) throws IOException {
        // Make sure we throw the same exceptions as the RI.
        if (taintedCommand == null) {
            throw new NullPointerException();
        }
        if (taintedCommand.length == 0) {
            throw new IndexOutOfBoundsException();
        }

        // Handle security and safety by copying mutable inputs and checking them.
        String[] command = taintedCommand.clone();
        String[] environment = taintedEnvironment != null ? taintedEnvironment.clone() : null;

        // Check we're not passing null Strings to the native exec.
        for (String arg : command) {
            if (arg == null) {
                throw new NullPointerException();
            }
        }
        // The environment is allowed to be null or empty, but no element may be null.
        if (environment != null) {
            for (String env : environment) {
                if (env == null) {
                    throw new NullPointerException();
                }
            }
        }

        FileDescriptor in = new FileDescriptor();
        FileDescriptor out = new FileDescriptor();
        FileDescriptor err = new FileDescriptor();

        String workingPath = (workingDirectory == null)
                ? null
                : workingDirectory.getPath();

        // Ensure onExit() doesn't access the process map before we add our
        // entry.
        synchronized (processReferences) {
            int pid;
            try {
                pid = exec(command, environment, workingPath, in, out, err, redirectErrorStream);
            } catch (IOException e) {
                IOException wrapper = new IOException("Error running exec()."
                        + " Command: " + Arrays.toString(command)
                        + " Working Directory: " + workingDirectory
                        + " Environment: " + Arrays.toString(environment));
                wrapper.initCause(e);
                throw wrapper;
            }
            ProcessImpl process = new ProcessImpl(pid, in, out, err);
            ProcessReference processReference = new ProcessReference(process, referenceQueue);
            processReferences.put(pid, processReference);

            /*
             * This will wake up the child monitor thread in case there
             * weren't previously any children to wait on.
             */
            processReferences.notifyAll();

            return process;
        }
    }

    static class ProcessImpl extends Process {
        private final int pid;

        private final InputStream errorStream;

        /** Reads output from process. */
        private final InputStream inputStream;

        /** Sends output to process. */
        private final OutputStream outputStream;

        /** The process's exit value. */
        private Integer exitValue = null;
        private final Object exitValueMutex = new Object();

        ProcessImpl(int pid, FileDescriptor in, FileDescriptor out, FileDescriptor err) {
            this.pid = pid;

            this.errorStream = new ProcessInputStream(err);
            this.inputStream = new ProcessInputStream(in);
            this.outputStream = new ProcessOutputStream(out);
        }

        public void destroy() {
            try {
                Libcore.os.kill(pid, SIGKILL);
            } catch (ErrnoException e) {
                System.logI("Failed to destroy process " + pid, e);
            }
            IoUtils.closeQuietly(inputStream);
            IoUtils.closeQuietly(errorStream);
            IoUtils.closeQuietly(outputStream);
        }

        public int exitValue() {
            synchronized (exitValueMutex) {
                if (exitValue == null) {
                    throw new IllegalThreadStateException(
                            "Process has not yet terminated.");
                }

                return exitValue;
            }
        }

        public InputStream getErrorStream() {
            return this.errorStream;
        }

        public InputStream getInputStream() {
            return this.inputStream;
        }

        public OutputStream getOutputStream() {
            return this.outputStream;
        }

        public int waitFor() throws InterruptedException {
            synchronized (exitValueMutex) {
                while (exitValue == null) {
                    exitValueMutex.wait();
                }
                return exitValue;
            }
        }

        void setExitValue(int exitValue) {
            synchronized (exitValueMutex) {
                this.exitValue = exitValue;
                exitValueMutex.notifyAll();
            }
        }

        @Override
        public String toString() {
            return "Process[pid=" + pid + "]";
        }
    }

    static class ProcessReference extends WeakReference<ProcessImpl> {

        final int processId;

        public ProcessReference(ProcessImpl referent, ProcessReferenceQueue referenceQueue) {
            super(referent, referenceQueue);
            this.processId = referent.pid;
        }
    }

    static class ProcessReferenceQueue extends ReferenceQueue<ProcessImpl> {

        @Override
        public ProcessReference poll() {
            // Why couldn't they get the generics right on ReferenceQueue? :(
            Object reference = super.poll();
            return (ProcessReference) reference;
        }
    }

    private static final ProcessManager instance = new ProcessManager();

    /** Gets the process manager. */
    public static ProcessManager getInstance() {
        return instance;
    }

    /** Automatically closes fd when collected. */
    private static class ProcessInputStream extends FileInputStream {

        private FileDescriptor fd;

        private ProcessInputStream(FileDescriptor fd) {
            super(fd);
            this.fd = fd;
        }

        @Override
        public void close() throws IOException {
            try {
                super.close();
            } finally {
                synchronized (this) {
                    try {
                        IoUtils.close(fd);
                    } finally {
                        fd = null;
                    }
                }
            }
        }
    }

    /** Automatically closes fd when collected. */
    private static class ProcessOutputStream extends FileOutputStream {

        private FileDescriptor fd;

        private ProcessOutputStream(FileDescriptor fd) {
            super(fd);
            this.fd = fd;
        }

        @Override
        public void close() throws IOException {
            try {
                super.close();
            } finally {
                synchronized (this) {
                    try {
                        IoUtils.close(fd);
                    } finally {
                        fd = null;
                    }
                }
            }
        }
    }
}
