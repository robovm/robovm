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
/*
 * Copyright (C) 2008 The Android Open Source Project
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
/*
 * Copyright (C) 2012 RoboVM AB
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libcore.util.EmptyArray;

/**
 * A {@code Thread} is a concurrent unit of execution. It has its own call stack
 * for methods being invoked, their arguments and local variables. Each application
 * has at least one thread running when it is started, the main thread, in the main
 * {@link ThreadGroup}. The runtime keeps its own threads in the system thread
 * group.
 *
 * <p>There are two ways to execute code in a new thread.
 * You can either subclass {@code Thread} and overriding its {@link #run()} method,
 * or construct a new {@code Thread} and pass a {@link Runnable} to the constructor.
 * In either case, the {@link #start()} method must be called to actually execute
 * the new {@code Thread}.
 *
 * <p>Each {@code Thread} has an integer priority that affect how the thread is
 * scheduled by the OS. A new thread inherits the priority of its parent.
 * A thread's priority can be set using the {@link #setPriority(int)} method.
 */
public class Thread implements Runnable {
    private static final int NANOS_PER_MILLI = 1000000;

    /** Park states */
    private static class ParkState {
        /** park state indicating unparked */
        private static final int UNPARKED = 1;

        /** park state indicating preemptively unparked */
        private static final int PREEMPTIVELY_UNPARKED = 2;

        /** park state indicating parked */
        private static final int PARKED = 3;
    }

    /**
     * A representation of a thread's state. A given thread may only be in one
     * state at a time.
     */
    public enum State {
        /**
         * The thread has been created, but has never been started.
         */
        NEW,
        /**
         * The thread may be run.
         */
        RUNNABLE,
        /**
         * The thread is blocked and waiting for a lock.
         */
        BLOCKED,
        /**
         * The thread is waiting.
         */
        WAITING,
        /**
         * The thread is waiting for a specified amount of time.
         */
        TIMED_WAITING,
        /**
         * The thread has been terminated.
         */
        TERMINATED
    }

    /**
     * The maximum priority value allowed for a thread.
     */
    public static final int MAX_PRIORITY = 10;

    /**
     * The minimum priority value allowed for a thread.
     */
    public static final int MIN_PRIORITY = 1;

    /**
     * The normal (default) priority value assigned to threads.
     */
    public static final int NORM_PRIORITY = 5;

    private static long count = 0;
    private static UncaughtExceptionHandler defaultUncaughtHandler = null;
    
    private volatile long threadPtr = 0;
    private ThreadGroup group = null;

    /**
     * Holds the thread's ID. We simply count upwards, so
     * each Thread has a unique ID.
     */
    private long id = 0;
    private String name = null;
    private long stackSize;
    private boolean daemon = false;
    private int priority = NORM_PRIORITY;
    private Runnable target = null;

    /** Callbacks to run on interruption. */
    private final List<Runnable> interruptActions = new ArrayList<Runnable>();

    private ClassLoader contextClassLoader = null;
    private UncaughtExceptionHandler uncaughtHandler = null;
    boolean started = false;

    /**
     * Normal thread local values.
     */
    ThreadLocal.Values localValues;

    /**
     * Inheritable thread local values.
     */
    ThreadLocal.Values inheritableValues;

    /** the park state of the thread */
    private int parkState = ParkState.UNPARKED;

    /** The synchronization object responsible for this thread parking. */
    private Object parkBlocker;

    /** The object used to implement join() and parking. */
    private Object lock = new Object();
    
    /**
     * Constructs a new {@code Thread} with no {@code Runnable} object and a
     * newly generated name. The new {@code Thread} will belong to the same
     * {@code ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread() {
        create(null, null, null, 0);
    }

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object and a
     * newly generated name. The new {@code Thread} will belong to the same
     * {@code ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @param runnable
     *            a {@code Runnable} whose method <code>run</code> will be
     *            executed by the new {@code Thread}
     *
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(Runnable runnable) {
        create(null, runnable, null, 0);
    }

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object and name
     * provided. The new {@code Thread} will belong to the same {@code
     * ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @param runnable
     *            a {@code Runnable} whose method <code>run</code> will be
     *            executed by the new {@code Thread}
     * @param threadName
     *            the name for the {@code Thread} being created
     *
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(Runnable runnable, String threadName) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }

        create(null, runnable, threadName, 0);
    }

    /**
     * Constructs a new {@code Thread} with no {@code Runnable} object and the
     * name provided. The new {@code Thread} will belong to the same {@code
     * ThreadGroup} as the {@code Thread} calling this constructor.
     *
     * @param threadName
     *            the name for the {@code Thread} being created
     *
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     *
     */
    public Thread(String threadName) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }

        create(null, null, threadName, 0);
    }

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object and a
     * newly generated name. The new {@code Thread} will belong to the {@code
     * ThreadGroup} passed as parameter.
     *
     * @param group
     *            {@code ThreadGroup} to which the new {@code Thread} will
     *            belong
     * @param runnable
     *            a {@code Runnable} whose method <code>run</code> will be
     *            executed by the new {@code Thread}
     * @throws IllegalThreadStateException
     *             if <code>group.destroy()</code> has already been done
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(ThreadGroup group, Runnable runnable) {
        create(group, runnable, null, 0);
    }

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object, the given
     * name and belonging to the {@code ThreadGroup} passed as parameter.
     *
     * @param group
     *            ThreadGroup to which the new {@code Thread} will belong
     * @param runnable
     *            a {@code Runnable} whose method <code>run</code> will be
     *            executed by the new {@code Thread}
     * @param threadName
     *            the name for the {@code Thread} being created
     * @throws IllegalThreadStateException
     *             if <code>group.destroy()</code> has already been done
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(ThreadGroup group, Runnable runnable, String threadName) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }

        create(group, runnable, threadName, 0);
    }

    /**
     * Constructs a new {@code Thread} with no {@code Runnable} object, the
     * given name and belonging to the {@code ThreadGroup} passed as parameter.
     *
     * @param group
     *            {@code ThreadGroup} to which the new {@code Thread} will belong
     * @param threadName
     *            the name for the {@code Thread} being created
     * @throws IllegalThreadStateException
     *             if <code>group.destroy()</code> has already been done
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(ThreadGroup group, String threadName) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }

        create(group, null, threadName, 0);
    }

    /**
     * Constructs a new {@code Thread} with a {@code Runnable} object, the given
     * name and belonging to the {@code ThreadGroup} passed as parameter.
     *
     * @param group
     *            {@code ThreadGroup} to which the new {@code Thread} will
     *            belong
     * @param runnable
     *            a {@code Runnable} whose method <code>run</code> will be
     *            executed by the new {@code Thread}
     * @param threadName
     *            the name for the {@code Thread} being created
     * @param stackSize
     *            a stack size for the new {@code Thread}. This has a highly
     *            platform-dependent interpretation. It may even be ignored
     *            completely.
     * @throws IllegalThreadStateException
     *             if <code>group.destroy()</code> has already been done
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    public Thread(ThreadGroup group, Runnable runnable, String threadName, long stackSize) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }
        create(group, runnable, threadName, stackSize);
    }

    /**
     * Package-scope method invoked by Dalvik VM to create "internal"
     * threads or attach threads created externally.
     *
     * Don't call Thread.currentThread(), since there may not be such
     * a thing (e.g. for Main).
     */
    Thread(long threadPtr, String name, ThreadGroup group, boolean daemon) {
        // NOTE: Must set threadPtr before the synchronized block below
        this.threadPtr = threadPtr;
        this.daemon = daemon;
        this.group = group == null ? ThreadGroup.mMain : group;
        synchronized (Thread.class) {
            id = ++count;
        }
        this.name = name == null ? "Thread-" + this.id : name;
        this.priority = NORM_PRIORITY;        
        this.started = true;
        this.group.addThread(this);
    }

    /**
     * Initializes a new, existing Thread object with a runnable object,
     * the given name and belonging to the ThreadGroup passed as parameter.
     * This is the method that the several public constructors delegate their
     * work to.
     *
     * @param group ThreadGroup to which the new Thread will belong
     * @param runnable a java.lang.Runnable whose method <code>run</code> will
     *        be executed by the new Thread
     * @param threadName Name for the Thread being created
     * @param stackSize Platform dependent stack size
     * @throws IllegalThreadStateException if <code>group.destroy()</code> has
     *         already been done
     * @see java.lang.ThreadGroup
     * @see java.lang.Runnable
     */
    private void create(ThreadGroup group, Runnable runnable, String threadName, long stackSize) {
        Thread currentThread = Thread.currentThread();
        if (group == null) {
            group = currentThread.getThreadGroup();
        }

        if (group.isDestroyed()) {
            throw new IllegalThreadStateException("Group already destroyed");
        }

        this.group = group;

        synchronized (Thread.class) {
            id = ++Thread.count;
        }

        if (threadName == null) {
            this.name = "Thread-" + id;
        } else {
            this.name = threadName;
        }

        this.target = runnable;
        this.stackSize = stackSize;

        this.priority = currentThread.getPriority();

        this.contextClassLoader = currentThread.contextClassLoader;

        // Transfer over InheritableThreadLocals.
        if (currentThread.inheritableValues != null) {
            inheritableValues = new ThreadLocal.Values(currentThread.inheritableValues);
        }

        // add ourselves to our ThreadGroup of choice
        this.group.addThread(this);

        // Signal to the VM that a new Thread instance was created
        hookThreadCreated(this);
    }

    private static native void hookThreadCreated(Thread thread);

    /**
     * Returns the number of active {@code Thread}s in the running {@code
     * Thread}'s group and its subgroups.
     *
     * @return the number of {@code Thread}s
     */
    public static int activeCount() {
        return currentThread().getThreadGroup().activeCount();
    }

    /**
     * Does nothing.
     */
    public final void checkAccess() {
    }

    /**
     * Returns the number of stack frames in this thread.
     *
     * @return Number of stack frames
     * @deprecated The results of this call were never well defined. To make
     *             things worse, it would depend on whether the Thread was
     *             suspended or not, and suspend was deprecated too.
     */
    @Deprecated
    public int countStackFrames() {
        return getStackTrace().length;
    }

    /**
     * Returns the Thread of the caller, that is, the current Thread.
     *
     * @return the current Thread.
     */
    public native static Thread currentThread();

    /**
     * Throws {@code UnsupportedOperationException}.
     * @deprecated Not implemented.
     */
    @Deprecated
    public void destroy() {
        throw new UnsupportedOperationException();
    }

    /**
     * Prints to the standard error stream a text representation of the current
     * stack for this Thread.
     *
     * @see Throwable#printStackTrace()
     */
    public static void dumpStack() {
        new Throwable("stack dump").printStackTrace();
    }

    final void printStackTrace(Throwable t) {
        System.err.print("Exception in thread \"");
        System.err.print(name);
        System.err.print("\" ");
        t.printStackTrace(System.err);
        System.err.flush();
    }
    
    /**
     * Copies an array with all Threads which are in the same ThreadGroup as the
     * receiver - and subgroups - into the array <code>threads</code> passed as
     * parameter. If the array passed as parameter is too small no exception is
     * thrown - the extra elements are simply not copied.
     *
     * @param threads
     *            array into which the Threads will be copied
     * @return How many Threads were copied over
     */
    public static int enumerate(Thread[] threads) {
        Thread thread = Thread.currentThread();
        return thread.getThreadGroup().enumerate(threads);
    }

    /**
     * Returns a map of all the currently live threads to their stack traces.
     */
    public static Map<Thread, StackTraceElement[]> getAllStackTraces() {
        Map<Thread, StackTraceElement[]> map = new HashMap<Thread, StackTraceElement[]>();

        // Find out how many live threads we have. Allocate a bit more
        // space than needed, in case new ones are just being created.
        int count = ThreadGroup.mMain.activeCount();
        Thread[] threads = new Thread[count + count / 2];

        // Enumerate the threads and collect the stacktraces.
        count = ThreadGroup.mMain.enumerate(threads);
        for (int i = 0; i < count; i++) {
            map.put(threads[i], threads[i].getStackTrace());
        }

        return map;
    }

    /**
     * Returns the context ClassLoader for this Thread.
     *
     * @return ClassLoader The context ClassLoader
     * @see java.lang.ClassLoader
     * @see #getContextClassLoader()
     */
    public ClassLoader getContextClassLoader() {
        return contextClassLoader;
    }

    /**
     * Returns the default exception handler that's executed when uncaught
     * exception terminates a thread.
     *
     * @return an {@link UncaughtExceptionHandler} or <code>null</code> if
     *         none exists.
     */
    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return defaultUncaughtHandler;
    }

    /**
     * Returns the thread's identifier. The ID is a positive <code>long</code>
     * generated on thread creation, is unique to the thread, and doesn't change
     * during the lifetime of the thread; the ID may be reused after the thread
     * has been terminated.
     *
     * @return the thread's ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the Thread.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the priority of the Thread.
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * Returns an array of {@link StackTraceElement} representing the current thread's stack.
     */
    public StackTraceElement[] getStackTrace() {
        if (threadPtr == 0) {
            return EmptyArray.STACK_TRACE_ELEMENT;
        }
        return internalGetStackTrace(this);
    }
    private static native StackTraceElement[] internalGetStackTrace(Thread thread);

    /**
     * Returns the current state of the Thread. This method is useful for
     * monitoring purposes.
     *
     * @return a {@link State} value.
     */
    public State getState() {
        if (threadPtr != 0) {
            int s = internalGetState(this);
            switch (s) {
            case 0: return State.TERMINATED;    // ZOMBIE
            case 1: return State.RUNNABLE;      // RUNNING
            case 2: return State.TIMED_WAITING; // TIMED_WAIT
            case 3: return State.BLOCKED;       // MONITOR
            case 4: return State.WAITING;       // WAIT
            case 5: return State.NEW;           // INITIALIZING
            case 6: return State.NEW;           // STARTING
            case 7: return State.RUNNABLE;      // NATIVE
            case 8: return State.WAITING;       // VMWAIT
            case 9: return State.RUNNABLE;      // SUSPENDED
            }
        }
        return started ? State.TERMINATED : State.NEW;
    }
    private static native int internalGetState(Thread thread);

    /**
     * Returns the ThreadGroup to which this Thread belongs.
     *
     * @return the Thread's ThreadGroup
     */
    public final ThreadGroup getThreadGroup() {
        // TODO This should actually be done at native termination.
        if (getState() == Thread.State.TERMINATED) {
            return null;
        } else {
            return group;
        }
    }

    /**
     * Returns the thread's uncaught exception handler. If not explicitly set,
     * then the ThreadGroup's handler is returned. If the thread is terminated,
     * then <code>null</code> is returned.
     *
     * @return an {@link UncaughtExceptionHandler} instance or {@code null}.
     */
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        if (uncaughtHandler != null)
            return uncaughtHandler;
        else
            return group;           // ThreadGroup is instance of UEH
    }

    /**
     * Posts an interrupt request to this {@code Thread}. The behavior depends on
     * the state of this {@code Thread}:
     * <ul>
     * <li>
     * {@code Thread}s blocked in one of {@code Object}'s {@code wait()} methods
     * or one of {@code Thread}'s {@code join()} or {@code sleep()} methods will
     * be woken up, their interrupt status will be cleared, and they receive an
     * {@link InterruptedException}.
     * <li>
     * {@code Thread}s blocked in an I/O operation of an
     * {@link java.nio.channels.InterruptibleChannel} will have their interrupt
     * status set and receive an
     * {@link java.nio.channels.ClosedByInterruptException}. Also, the channel
     * will be closed.
     * <li>
     * {@code Thread}s blocked in a {@link java.nio.channels.Selector} will have
     * their interrupt status set and return immediately. They don't receive an
     * exception in this case.
     * <ul>
     *
     * @see Thread#interrupted
     * @see Thread#isInterrupted
     */
    public void interrupt() {
        // Interrupt this thread before running actions so that other
        // threads that observe the interrupt as a result of an action
        // will see that this thread is in the interrupted state.
        if (threadPtr != 0) {
            internalInterrupt(this);
        }

        synchronized (interruptActions) {
            for (int i = interruptActions.size() - 1; i >= 0; i--) {
                interruptActions.get(i).run();
            }
        }
    }
    private static native void internalInterrupt(Thread thread);

    /**
     * Returns a <code>boolean</code> indicating whether the current Thread (
     * <code>currentThread()</code>) has a pending interrupt request (<code>
     * true</code>) or not (<code>false</code>). It also has the side-effect of
     * clearing the flag.
     *
     * @return a <code>boolean</code> indicating the interrupt status
     * @see Thread#currentThread
     * @see Thread#interrupt
     * @see Thread#isInterrupted
     */
    public static boolean interrupted() {
        return internalInterrupted();
    }
    private static native boolean internalInterrupted();

    /**
     * Returns <code>true</code> if the receiver has already been started and
     * still runs code (hasn't died yet). Returns <code>false</code> either if
     * the receiver hasn't been started yet or if it has already started and run
     * to completion and died.
     *
     * @return a <code>boolean</code> indicating the liveness of the Thread
     * @see Thread#start
     */
    public final boolean isAlive() {
        return threadPtr != 0;
    }

    /**
     * Tests whether this is a daemon thread.
     * A daemon thread only runs as long as there are non-daemon threads running.
     * When the last non-daemon thread ends, the runtime will exit. This is not
     * normally relevant to applications with a UI.
     */
    public final boolean isDaemon() {
        return daemon;
    }

    /**
     * Returns a <code>boolean</code> indicating whether the receiver has a
     * pending interrupt request (<code>true</code>) or not (
     * <code>false</code>)
     *
     * @return a <code>boolean</code> indicating the interrupt status
     * @see Thread#interrupt
     * @see Thread#interrupted
     */
    public boolean isInterrupted() {
        if (threadPtr != 0) {
            return internalIsInterrupted(this);
        }

        return false;
    }
    private static native boolean internalIsInterrupted(Thread thread);

    /**
     * Blocks the current Thread (<code>Thread.currentThread()</code>) until
     * the receiver finishes its execution and dies.
     *
     * @throws InterruptedException if <code>interrupt()</code> was called for
     *         the receiver while it was in the <code>join()</code> call
     * @see Object#notifyAll
     * @see java.lang.ThreadDeath
     */
    public final void join() throws InterruptedException {
        if (threadPtr == 0) {
            return;
        }

        synchronized (lock) {
            while (isAlive()) {
                lock.wait();
            }
        }
    }

    /**
     * Blocks the current Thread (<code>Thread.currentThread()</code>) until
     * the receiver finishes its execution and dies or the specified timeout
     * expires, whatever happens first.
     *
     * @param millis The maximum time to wait (in milliseconds).
     * @throws InterruptedException if <code>interrupt()</code> was called for
     *         the receiver while it was in the <code>join()</code> call
     * @see Object#notifyAll
     * @see java.lang.ThreadDeath
     */
    public final void join(long millis) throws InterruptedException {
        join(millis, 0);
    }

    /**
     * Blocks the current Thread (<code>Thread.currentThread()</code>) until
     * the receiver finishes its execution and dies or the specified timeout
     * expires, whatever happens first.
     *
     * @param millis The maximum time to wait (in milliseconds).
     * @param nanos Extra nanosecond precision
     * @throws InterruptedException if <code>interrupt()</code> was called for
     *         the receiver while it was in the <code>join()</code> call
     * @see Object#notifyAll
     * @see java.lang.ThreadDeath
     */
    public final void join(long millis, int nanos) throws InterruptedException {
        if (millis < 0 || nanos < 0 || nanos >= NANOS_PER_MILLI) {
            throw new IllegalArgumentException("bad timeout: millis=" + millis + ",nanos=" + nanos);
        }

        // avoid overflow: if total > 292,277 years, just wait forever
        boolean overflow = millis >= (Long.MAX_VALUE - nanos) / NANOS_PER_MILLI;
        boolean forever = (millis | nanos) == 0;
        if (forever | overflow) {
            join();
            return;
        }

        if (threadPtr == 0) {
            return;
        }

        synchronized (lock) {
            if (!isAlive()) {
                return;
            }

            // guaranteed not to overflow
            long nanosToWait = millis * NANOS_PER_MILLI + nanos;

            // wait until this thread completes or the timeout has elapsed
            long start = System.nanoTime();
            while (true) {
                lock.wait(millis, nanos);
                if (!isAlive()) {
                    break;
                }
                long nanosElapsed = System.nanoTime() - start;
                long nanosRemaining = nanosToWait - nanosElapsed;
                if (nanosRemaining <= 0) {
                    break;
                }
                millis = nanosRemaining / NANOS_PER_MILLI;
                nanos = (int) (nanosRemaining - millis * NANOS_PER_MILLI);
            }
        }
    }

    /**
     * Throws {@code UnsupportedOperationException}.
     * @deprecated Only useful in conjunction with deprecated method {@link Thread#suspend}.
     */
    @Deprecated
    public final void resume() {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls the <code>run()</code> method of the Runnable object the receiver
     * holds. If no Runnable is set, does nothing.
     *
     * @see Thread#start
     */
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    /**
     * Set the context ClassLoader for the receiver.
     *
     * @param cl The context ClassLoader
     * @see #getContextClassLoader()
     */
    public void setContextClassLoader(ClassLoader cl) {
        contextClassLoader = cl;
    }

    /**
     * Marks this thread as a daemon thread.
     * A daemon thread only runs as long as there are non-daemon threads running.
     * When the last non-daemon thread ends, the runtime will exit. This is not
     * normally relevant to applications with a UI.
     * @throws IllegalThreadStateException - if this thread has already started.
     */
    public final void setDaemon(boolean isDaemon) {
        checkNotStarted();
        if (threadPtr == 0) {
            daemon = isDaemon;
        }
    }

    private void checkNotStarted() {
        if (started) {
            throw new IllegalThreadStateException("Thread already started");
        }
    }

    /**
     * Sets the default uncaught exception handler. This handler is invoked in
     * case any Thread dies due to an unhandled exception.
     *
     * @param handler
     *            The handler to set or null.
     */
    public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler handler) {
        Thread.defaultUncaughtHandler = handler;
    }

    /**
     * Adds a runnable to be invoked upon interruption. If this thread has
     * already been interrupted, the runnable will be invoked immediately. The
     * action should be idempotent as it may be invoked multiple times for a
     * single interruption.
     *
     * <p>Each call to this method must be matched with a corresponding call to
     * {@link #popInterruptAction$}.
     *
     * @hide used by NIO
     */
    public final void pushInterruptAction$(Runnable interruptAction) {
        synchronized (interruptActions) {
            interruptActions.add(interruptAction);
        }

        if (interruptAction != null && isInterrupted()) {
            interruptAction.run();
        }
    }

    /**
     * Removes {@code interruptAction} so it is not invoked upon interruption.
     *
     * @param interruptAction the pushed action, used to check that the call
     *     stack is correctly nested.
     *
     * @hide used by NIO
     */
    public final void popInterruptAction$(Runnable interruptAction) {
        synchronized (interruptActions) {
            Runnable removed = interruptActions.remove(interruptActions.size() - 1);
            if (interruptAction != removed) {
                throw new IllegalArgumentException(
                        "Expected " + interruptAction + " but was " + removed);
            }
        }
    }

    /**
     * Sets the name of the Thread.
     *
     * @param threadName the new name for the Thread
     * @see Thread#getName
     */
    public final void setName(String threadName) {
        if (threadName == null) {
            throw new NullPointerException("threadName == null");
        }

        name = threadName;
        if (threadPtr != 0) {
            /* notify the VM that the thread name has changed */
            internalSetName(this, threadName);
        }
    }
    private static native void internalSetName(Thread thread, String threadName);

    /**
     * Sets the priority of this thread. If the requested priority is greater than the
     * parent thread group's {@link java.lang.ThreadGroup#getMaxPriority}, the group's maximum
     * priority will be used instead.
     *
     * @throws IllegalArgumentException - if the new priority is greater than {@link #MAX_PRIORITY}
     *     or less than {@link #MIN_PRIORITY}
     */
    public final void setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException("Priority out of range: " + priority);
        }

        if (priority > group.getMaxPriority()) {
            priority = group.getMaxPriority();
        }

        this.priority = priority;

        if (threadPtr != 0) {
            internalSetPriority(this, priority);
        }
    }
    private static native void internalSetPriority(Thread thread, int priority);

    /**
     * <p>
     * Sets the uncaught exception handler. This handler is invoked in case this
     * Thread dies due to an unhandled exception.
     * </p>
     *
     * @param handler
     *            The handler to set or <code>null</code>.
     */
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler handler) {
        uncaughtHandler = handler;
    }

    /**
     * Causes the thread which sent this message to sleep for the given interval
     * of time (given in milliseconds). The precision is not guaranteed - the
     * Thread may sleep more or less than requested.
     *
     * @param time
     *            The time to sleep in milliseconds.
     * @throws InterruptedException
     *             if <code>interrupt()</code> was called for this Thread while
     *             it was sleeping
     * @see Thread#interrupt()
     */
    public static void sleep(long time) throws InterruptedException {
        Thread.sleep(time, 0);
    }

    /**
     * Causes the thread which sent this message to sleep for the given interval
     * of time (given in milliseconds and nanoseconds). The precision is not
     * guaranteed - the Thread may sleep more or less than requested.
     *
     * @param millis
     *            The time to sleep in milliseconds.
     * @param nanos
     *            Extra nanosecond precision
     * @throws InterruptedException
     *             if <code>interrupt()</code> was called for this Thread while
     *             it was sleeping
     * @see Thread#interrupt()
     */
    public static void sleep(long millis, int nanos) throws InterruptedException {
        internalSleep(millis, nanos);
    }
    private static native void internalSleep(long millis, int nanos) throws InterruptedException;

    /**
     * Starts the new Thread of execution. The <code>run()</code> method of
     * the receiver will be called by the receiver Thread itself (and not the
     * Thread calling <code>start()</code>).
     *
     * @throws IllegalThreadStateException - if this thread has already started.
     * @see Thread#run
     */
    public synchronized void start() {
        checkNotStarted();

        started = true;

        internalStart(this);
    }
    private static native void internalStart(Thread t);

    /**
     * Requests the receiver Thread to stop and throw ThreadDeath. The Thread is
     * resumed if it was suspended and awakened if it was sleeping, so that it
     * can proceed to throw ThreadDeath.
     *
     * @deprecated Stopping a thread in this manner is unsafe and can
     * leave your application and the VM in an unpredictable state.
     */
    @Deprecated
    public final void stop() {
        stop(new ThreadDeath());
    }

    /**
     * Throws {@code UnsupportedOperationException}.
     * @deprecated Stopping a thread in this manner is unsafe and can
     * leave your application and the VM in an unpredictable state.
     */
    @Deprecated
    public final synchronized void stop(Throwable throwable) {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@code UnsupportedOperationException}.
     * @deprecated May cause deadlocks.
     */
    @Deprecated
    public final void suspend() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string containing a concise, human-readable description of the
     * Thread. It includes the Thread's name, priority, and group name.
     *
     * @return a printable representation for the receiver.
     */
    @Override
    public String toString() {
        return "Thread[" + name + "," + priority + "," + group.getName() + "]";
    }

    /**
     * Causes the calling Thread to yield execution time to another Thread that
     * is ready to run. The actual scheduling is implementation-dependent.
     */
    public static void yield() {
        internalYield();
    }
    private static native void internalYield();

    /**
     * Indicates whether the current Thread has a monitor lock on the specified
     * object.
     *
     * @param object the object to test for the monitor lock
     * @return true if the current thread has a monitor lock on the specified
     *         object; false otherwise
     */
    public static boolean holdsLock(Object object) {
        return internalHoldsLock(object);
    }
    private static native boolean internalHoldsLock(Object object);

    /**
     * Implemented by objects that want to handle cases where a thread is being
     * terminated by an uncaught exception. Upon such termination, the handler
     * is notified of the terminating thread and causal exception. If there is
     * no explicit handler set then the thread's group is the default handler.
     */
    public static interface UncaughtExceptionHandler {
        /**
         * The thread is being terminated by an uncaught exception. Further
         * exceptions thrown in this method are prevent the remainder of the
         * method from executing, but are otherwise ignored.
         *
         * @param thread the thread that has an uncaught exception
         * @param ex the exception that was thrown
         */
        void uncaughtException(Thread thread, Throwable ex);
    }

    /**
     * Unparks this thread. This unblocks the thread it if it was
     * previously parked, or indicates that the thread is "preemptively
     * unparked" if it wasn't already parked. The latter means that the
     * next time the thread is told to park, it will merely clear its
     * latent park bit and carry on without blocking.
     *
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * @hide for Unsafe
     */
    public void unpark() {
        if (threadPtr == 0) {
            /*
             * vmThread is null before the thread is start()ed. In
             * this case, we just go ahead and set the state to
             * PREEMPTIVELY_UNPARKED. Since this happens before the
             * thread is started, we don't have to worry about
             * synchronizing with it.
             */
            parkState = ParkState.PREEMPTIVELY_UNPARKED;
            return;
        }

        synchronized (lock) {
            switch (parkState) {
                case ParkState.PREEMPTIVELY_UNPARKED: {
                    /*
                     * Nothing to do in this case: By definition, a
                     * preemptively unparked thread is to remain in
                     * the preemptively unparked state if it is told
                     * to unpark.
                     */
                    break;
                }
                case ParkState.UNPARKED: {
                    parkState = ParkState.PREEMPTIVELY_UNPARKED;
                    break;
                }
                default /*parked*/: {
                    parkState = ParkState.UNPARKED;
                    lock.notifyAll();
                    break;
                }
            }
        }
    }

    /**
     * Parks the current thread for a particular number of nanoseconds, or
     * indefinitely. If not indefinitely, this method unparks the thread
     * after the given number of nanoseconds if no other thread unparks it
     * first. If the thread has been "preemptively unparked," this method
     * cancels that unparking and returns immediately. This method may
     * also return spuriously (that is, without the thread being told to
     * unpark and without the indicated amount of time elapsing).
     *
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * <p>This method must only be called when <code>this</code> is the current
     * thread.
     *
     * @param nanos number of nanoseconds to park for or <code>0</code>
     * to park indefinitely
     * @throws IllegalArgumentException thrown if <code>nanos &lt; 0</code>
     *
     * @hide for Unsafe
     */
    public void parkFor(long nanos) {
        if (threadPtr == 0) {
            // Running threads should always have an associated threadPtr.
            throw new AssertionError();
        }

        synchronized (lock) {
            switch (parkState) {
                case ParkState.PREEMPTIVELY_UNPARKED: {
                    parkState = ParkState.UNPARKED;
                    break;
                }
                case ParkState.UNPARKED: {
                    long millis = nanos / NANOS_PER_MILLI;
                    nanos %= NANOS_PER_MILLI;

                    parkState = ParkState.PARKED;
                    try {
                        lock.wait(millis, (int) nanos);
                    } catch (InterruptedException ex) {
                        interrupt();
                    } finally {
                        /*
                         * Note: If parkState manages to become
                         * PREEMPTIVELY_UNPARKED before hitting this
                         * code, it should left in that state.
                         */
                        if (parkState == ParkState.PARKED) {
                            parkState = ParkState.UNPARKED;
                        }
                    }
                    break;
                }
                default /*parked*/: {
                    throw new AssertionError(
                            "shouldn't happen: attempt to repark");
                }
            }
        }
    }

    /**
     * Parks the current thread until the specified system time. This
     * method attempts to unpark the current thread immediately after
     * <code>System.currentTimeMillis()</code> reaches the specified
     * value, if no other thread unparks it first. If the thread has
     * been "preemptively unparked," this method cancels that
     * unparking and returns immediately. This method may also return
     * spuriously (that is, without the thread being told to unpark
     * and without the indicated amount of time elapsing).
     *
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * <p>This method must only be called when <code>this</code> is the
     * current thread.
     *
     * @param time the time after which the thread should be unparked,
     * in absolute milliseconds-since-the-epoch
     *
     * @hide for Unsafe
     */
    public void parkUntil(long time) {
        if (threadPtr == 0) {
            // Running threads should always have an associated threadPtr.
            throw new AssertionError();
        }

        synchronized (lock) {
            /*
             * Note: This conflates the two time bases of "wall clock"
             * time and "monotonic uptime" time. However, given that
             * the underlying system can only wait on monotonic time,
             * it is unclear if there is any way to avoid the
             * conflation. The downside here is that if, having
             * calculated the delay, the wall clock gets moved ahead,
             * this method may not return until well after the wall
             * clock has reached the originally designated time. The
             * reverse problem (the wall clock being turned back)
             * isn't a big deal, since this method is allowed to
             * spuriously return for any reason, and this situation
             * can safely be construed as just such a spurious return.
             */
            long delayMillis = time - System.currentTimeMillis();

            if (delayMillis <= 0) {
                parkState = ParkState.UNPARKED;
            } else {
                parkFor(delayMillis * NANOS_PER_MILLI);
            }
        }
    }
}
