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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import libcore.util.CollectionUtils;

/**
 * {@code ThreadGroup} is a means of organizing threads into a hierarchical structure.
 * This class is obsolete. See <i>Effective Java</i> Item 73, "Avoid thread groups" for details.
 * @see Thread
 */
public class ThreadGroup implements Thread.UncaughtExceptionHandler {

    // Name of this ThreadGroup
    // VM needs this field name for debugging.
    private String name;

    // Maximum priority for Threads inside this ThreadGroup
    private int maxPriority = Thread.MAX_PRIORITY;

    // The ThreadGroup to which this ThreadGroup belongs
    // VM needs this field name for debugging.
    final ThreadGroup parent;

    /**
     * Weak references to the threads in this group.
     * Access is guarded by synchronizing on this field.
     */
    private final List<WeakReference<Thread>> threadRefs = new ArrayList<WeakReference<Thread>>(5);

    /**
     * View of the threads.
     * Access is guarded by synchronizing on threadRefs.
     */
    private final Iterable<Thread> threads = CollectionUtils.dereferenceIterable(threadRefs, true);

    /**
     * Thread groups. Access is guarded by synchronizing on this field.
     */
    private final List<ThreadGroup> groups = new ArrayList<ThreadGroup>(3);

    // Whether this ThreadGroup is a daemon ThreadGroup or not
    private boolean isDaemon;

    // Whether this ThreadGroup has already been destroyed or not
    private boolean isDestroyed;

    /* the VM uses these directly; do not rename */
    static final ThreadGroup mSystem = new ThreadGroup();
    static final ThreadGroup mMain = new ThreadGroup(mSystem, "main");

    /**
     * Constructs a new {@code ThreadGroup} with the given name. The new {@code ThreadGroup}
     * will be child of the {@code ThreadGroup} to which the calling thread belongs.
     *
     * @param name the name
     * @see Thread#currentThread
     */
    public ThreadGroup(String name) {
        this(Thread.currentThread().getThreadGroup(), name);
    }

    /**
     * Constructs a new {@code ThreadGroup} with the given name, as a child of the
     * given {@code ThreadGroup}.
     *
     * @param parent the parent
     * @param name the name
     * @throws NullPointerException if {@code parent == null}
     * @throws IllegalThreadStateException if {@code parent} has been
     *         destroyed already
     */
    public ThreadGroup(ThreadGroup parent, String name) {
        if (parent == null) {
            throw new NullPointerException("parent == null");
        }
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.add(this);
            this.setMaxPriority(parent.getMaxPriority());
            if (parent.isDaemon()) {
                this.setDaemon(true);
            }
        }
    }

    /**
     * Initialize the special "system" ThreadGroup. Was "main" in Harmony,
     * but we have an additional group above that in Android.
     */
    private ThreadGroup() {
        this.name = "system";
        this.parent = null;
    }

    /**
     * Returns the number of running {@code Thread}s which are children of this thread group,
     * directly or indirectly.
     *
     * @return the number of children
     */
    public int activeCount() {
        int count = 0;
        synchronized (threadRefs) {
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    count++;
                }
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                count += group.activeCount();
            }
        }
        return count;
    }

    /**
     * Returns the number of {@code ThreadGroup}s which are children of this group,
     * directly or indirectly.
     *
     * @return the number of children
     */
    public int activeGroupCount() {
        int count = 0;
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                // One for this group & the subgroups
                count += 1 + group.activeGroupCount();
            }
        }
        return count;
    }

    /**
     * Adds a {@code ThreadGroup} to this thread group.
     *
     * @param g ThreadGroup to add
     * @throws IllegalThreadStateException if this group has been destroyed already
     */
    private void add(ThreadGroup g) throws IllegalThreadStateException {
        synchronized (groups) {
            if (isDestroyed) {
                throw new IllegalThreadStateException();
            }
            groups.add(g);
        }
    }

    /**
     * Does nothing. The definition of this method depends on the deprecated
     * method {@link #suspend()}. The exact behavior of this call was never
     * specified.
     *
     * @param b Used to control low memory implicit suspension
     * @return {@code true} (always)
     *
     * @deprecated Required deprecated method suspend().
     */
    @Deprecated
    public boolean allowThreadSuspension(boolean b) {
        // Does not apply to this VM, no-op
        return true;
    }

    /**
     * Does nothing.
     */
    public final void checkAccess() {
    }

    /**
     * Destroys this thread group and recursively all its subgroups. It is only legal
     * to destroy a {@code ThreadGroup} that has no threads in it. Any daemon
     * {@code ThreadGroup} is destroyed automatically when it becomes empty (no threads
     * or thread groups in it).
     *
     * @throws IllegalThreadStateException if this thread group or any of its
     *         subgroups has been destroyed already or if it still contains
     *         threads.
     */
    public final void destroy() {
        synchronized (threadRefs) {
            synchronized (groups) {
                if (isDestroyed) {
                    throw new IllegalThreadStateException(
                            "Thread group was already destroyed: "
                            + (this.name != null ? this.name : "n/a"));
                }
                if (threads.iterator().hasNext()) {
                    throw new IllegalThreadStateException(
                            "Thread group still contains threads: "
                            + (this.name != null ? this.name : "n/a"));
                }
                // Call recursively for subgroups
                while (!groups.isEmpty()) {
                    // We always get the first element - remember, when the
                    // child dies it removes itself from our collection. See
                    // below.
                    groups.get(0).destroy();
                }

                if (parent != null) {
                    parent.remove(this);
                }

                // Now that the ThreadGroup is really destroyed it can be tagged as so
                this.isDestroyed = true;
            }
        }
    }

    /*
     * Auxiliary method that destroys this thread group and recursively all its
     * subgroups if this is a daemon ThreadGroup.
     *
     * @see #destroy
     * @see #setDaemon
     * @see #isDaemon
     */
    private void destroyIfEmptyDaemon() {
        // Has to be non-destroyed daemon to make sense
        synchronized (threadRefs) {
            if (isDaemon && !isDestroyed && !threads.iterator().hasNext()) {
                synchronized (groups) {
                    if (groups.isEmpty()) {
                        destroy();
                    }
                }
            }
        }
    }

    /**
     * Iterates over all active threads in this group (and its sub-groups) and
     * stores the threads in the given array. Returns when the array is full or
     * no more threads remain, whichever happens first.
     *
     * <p>Note that this method will silently ignore any threads that don't fit in the
     * supplied array.
     *
     * @param threads the array into which the {@code Thread}s will be copied
     * @return the number of {@code Thread}s that were copied
     */
    public int enumerate(Thread[] threads) {
        return enumerate(threads, true);
    }

    /**
     * Iterates over all active threads in this group (and, optionally, its
     * sub-groups) and stores the threads in the given array. Returns when the
     * array is full or no more threads remain, whichever happens first.
     *
     * <p>Note that this method will silently ignore any threads that don't fit in the
     * supplied array.
     *
     * @param threads the array into which the {@code Thread}s will be copied
     * @param recurse indicates whether {@code Thread}s in subgroups should be
     *        recursively copied as well
     * @return the number of {@code Thread}s that were copied
     */
    public int enumerate(Thread[] threads, boolean recurse) {
        return enumerateGeneric(threads, recurse, 0, true);
    }

    /**
     * Iterates over all thread groups in this group (and its sub-groups) and
     * and stores the groups in the given array. Returns when the array is full
     * or no more groups remain, whichever happens first.
     *
     * <p>Note that this method will silently ignore any thread groups that don't fit in the
     * supplied array.
     *
     * @param groups the array into which the {@code ThreadGroup}s will be copied
     * @return the number of {@code ThreadGroup}s that were copied
     */
    public int enumerate(ThreadGroup[] groups) {
        return enumerate(groups, true);
    }

    /**
     * Iterates over all thread groups in this group (and, optionally, its
     * sub-groups) and stores the groups in the given array. Returns when
     * the array is full or no more groups remain, whichever happens first.
     *
     * <p>Note that this method will silently ignore any thread groups that don't fit in the
     * supplied array.
     *
     * @param groups the array into which the {@code ThreadGroup}s will be copied
     * @param recurse indicates whether {@code ThreadGroup}s in subgroups should be
     *        recursively copied as well or not
     * @return the number of {@code ThreadGroup}s that were copied
     */
    public int enumerate(ThreadGroup[] groups, boolean recurse) {
        return enumerateGeneric(groups, recurse, 0, false);
    }

    /**
     * Copies into <param>enumeration</param> starting at
     * <param>enumerationIndex</param> all Threads or ThreadGroups in the
     * receiver. If <param>recurse</param> is true, recursively enumerate the
     * elements in subgroups.
     *
     * If the array passed as parameter is too small no exception is thrown -
     * the extra elements are simply not copied.
     *
     * @param enumeration array into which the elements will be copied
     * @param recurse Indicates whether subgroups should be enumerated or not
     * @param enumerationIndex Indicates in which position of the enumeration
     *        array we are
     * @param enumeratingThreads Indicates whether we are enumerating Threads or
     *        ThreadGroups
     * @return How many elements were enumerated/copied over
     */
    private int enumerateGeneric(Object[] enumeration, boolean recurse, int enumerationIndex,
            boolean enumeratingThreads) {
        if (enumeratingThreads) {
            synchronized (threadRefs) {
                // walk the references directly so we can iterate in reverse order
                for (int i = threadRefs.size() - 1; i >= 0; --i) {
                    Thread thread = threadRefs.get(i).get();
                    if (thread != null && thread.isAlive()) {
                        if (enumerationIndex >= enumeration.length) {
                            return enumerationIndex;
                        }
                        enumeration[enumerationIndex++] = thread;
                    }
                }
            }
        } else {
            synchronized (groups) {
                for (int i = groups.size() - 1; i >= 0; --i) {
                    if (enumerationIndex >= enumeration.length) {
                        return enumerationIndex;
                    }
                    enumeration[enumerationIndex++] = groups.get(i);
                }
            }
        }

        if (recurse) {
            synchronized (groups) {
                for (ThreadGroup group : groups) {
                    if (enumerationIndex >= enumeration.length) {
                        return enumerationIndex;
                    }
                    enumerationIndex = group.enumerateGeneric(enumeration, recurse,
                            enumerationIndex, enumeratingThreads);
                }
            }
        }
        return enumerationIndex;
    }

    /**
     * Returns the maximum allowed priority for a {@code Thread} in this thread group.
     *
     * @return the maximum priority
     *
     * @see #setMaxPriority
     */
    public final int getMaxPriority() {
        return maxPriority;
    }

    /**
     * Returns the name of this thread group.
     *
     * @return the group's name
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns this thread group's parent {@code ThreadGroup}. It can be null if this
     * is the the root ThreadGroup.
     *
     * @return the parent
     */
    public final ThreadGroup getParent() {
        return parent;
    }

    /**
     * Interrupts every {@code Thread} in this group and recursively in all its
     * subgroups.
     *
     * @see Thread#interrupt
     */
    public final void interrupt() {
        synchronized (threadRefs) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                group.interrupt();
            }
        }
    }

    /**
     * Checks whether this thread group is a daemon {@code ThreadGroup}.
     *
     * @return true if this thread group is a daemon {@code ThreadGroup}
     *
     * @see #setDaemon
     * @see #destroy
     */
    public final boolean isDaemon() {
        return isDaemon;
    }

    /**
     * Checks whether this thread group has already been destroyed.
     *
     * @return true if this thread group has already been destroyed
     * @see #destroy
     */
    public synchronized boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Outputs to {@code System.out} a text representation of the
     * hierarchy of {@code Thread}s and {@code ThreadGroup}s in this thread group (and recursively).
     * Proper indentation is used to show the nesting of groups inside groups
     * and threads inside groups.
     */
    public void list() {
        // We start in a fresh line
        System.out.println();
        list(0);
    }

    /*
     * Outputs to {@code System.out}a text representation of the
     * hierarchy of Threads and ThreadGroups in this thread group (and recursively).
     * The indentation will be four spaces per level of nesting.
     *
     * @param levels How many levels of nesting, so that proper indentation can
     * be output.
     */
    private void list(int levels) {
        indent(levels);
        System.out.println(this.toString());

        ++levels;
        synchronized (threadRefs) {
            for (Thread thread : threads) {
                indent(levels);
                System.out.println(thread);
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                group.list(levels);
            }
        }
    }

    private void indent(int levels) {
        for (int i = 0; i < levels; i++) {
            System.out.print("    "); // 4 spaces for each level
        }
    }

    /**
     * Checks whether this thread group is a direct or indirect parent group of a
     * given {@code ThreadGroup}.
     *
     * @param g the potential child {@code ThreadGroup}
     * @return true if this thread group is parent of {@code g}
     */
    public final boolean parentOf(ThreadGroup g) {
        while (g != null) {
            if (this == g) {
                return true;
            }
            g = g.parent;
        }
        return false;
    }

    /**
     * Removes an immediate subgroup.
     *
     * @param g ThreadGroup to remove
     *
     * @see #add(Thread)
     * @see #add(ThreadGroup)
     */
    private void remove(ThreadGroup g) {
        synchronized (groups) {
            for (Iterator<ThreadGroup> i = groups.iterator(); i.hasNext(); ) {
                ThreadGroup threadGroup = i.next();
                if (threadGroup.equals(g)) {
                    i.remove();
                    break;
                }
            }
        }
        destroyIfEmptyDaemon();
    }

    /**
     * Resumes every thread in this group and recursively in all its
     * subgroups.
     *
     * @see Thread#resume
     * @see #suspend
     *
     * @deprecated Requires deprecated method Thread.resume().
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public final void resume() {
        synchronized (threadRefs) {
            for (Thread thread : threads) {
                thread.resume();
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                group.resume();
            }
        }
    }

    /**
     * Sets whether this is a daemon {@code ThreadGroup} or not. Daemon
     * thread groups are automatically destroyed when they become empty.
     *
     * @param isDaemon the new value
     * @see #isDaemon
     * @see #destroy
     */
    public final void setDaemon(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    /**
     * Configures the maximum allowed priority for a {@code Thread} in this group and
     * recursively in all its subgroups.
     *
     * <p>A caller can never increase the maximum priority of a thread group.
     * Such an attempt will not result in an exception, it will
     * simply leave the thread group with its current maximum priority.
     *
     * @param newMax the new maximum priority to be set
     *
     * @throws IllegalArgumentException if the new priority is greater than
     *         Thread.MAX_PRIORITY or less than Thread.MIN_PRIORITY
     *
     * @see #getMaxPriority
     */
    public final void setMaxPriority(int newMax) {
        if (newMax <= this.maxPriority) {
            if (newMax < Thread.MIN_PRIORITY) {
                newMax = Thread.MIN_PRIORITY;
            }

            int parentPriority = parent == null ? newMax : parent.getMaxPriority();
            this.maxPriority = parentPriority <= newMax ? parentPriority : newMax;
            synchronized (groups) {
                for (ThreadGroup group : groups) {
                    group.setMaxPriority(newMax);
                }
            }
        }
    }

    /**
     * Stops every thread in this group and recursively in all its subgroups.
     *
     * @see Thread#stop()
     * @see Thread#stop(Throwable)
     * @see ThreadDeath
     *
     * @deprecated Requires deprecated method Thread.stop().
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public final void stop() {
        if (stopHelper()) {
            Thread.currentThread().stop();
        }
    }

    @SuppressWarnings("deprecation")
    private boolean stopHelper() {
        boolean stopCurrent = false;
        synchronized (threadRefs) {
            Thread current = Thread.currentThread();
            for (Thread thread : threads) {
                if (thread == current) {
                    stopCurrent = true;
                } else {
                    thread.stop();
                }
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                stopCurrent |= group.stopHelper();
            }
        }
        return stopCurrent;
    }

    /**
     * Suspends every thread in this group and recursively in all its
     * subgroups.
     *
     * @see Thread#suspend
     * @see #resume
     *
     * @deprecated Requires deprecated method Thread.suspend().
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public final void suspend() {
        if (suspendHelper()) {
            Thread.currentThread().suspend();
        }
    }

    @SuppressWarnings("deprecation")
    private boolean suspendHelper() {
        boolean suspendCurrent = false;
        synchronized (threadRefs) {
            Thread current = Thread.currentThread();
            for (Thread thread : threads) {
                if (thread == current) {
                    suspendCurrent = true;
                } else {
                    thread.suspend();
                }
            }
        }
        synchronized (groups) {
            for (ThreadGroup group : groups) {
                suspendCurrent |= group.suspendHelper();
            }
        }
        return suspendCurrent;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + getName()
                + ",maxPriority=" + getMaxPriority() + "]";
    }

    /**
     * Handles uncaught exceptions. Any uncaught exception in any {@code Thread}
     * is forwarded to the thread's {@code ThreadGroup} by invoking this
     * method.
     *
     * <p>New code should use {@link Thread#setUncaughtExceptionHandler} instead of thread groups.
     *
     * @param t the Thread that terminated with an uncaught exception
     * @param e the uncaught exception itself
     */
    public void uncaughtException(Thread t, Throwable e) {
        if (parent != null) {
            parent.uncaughtException(t, e);
        } else if (Thread.getDefaultUncaughtExceptionHandler() != null) {
            // TODO The spec is unclear regarding this. What do we do?
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(t, e);
        } else if (!(e instanceof ThreadDeath)) {
            // No parent group, has to be 'system' Thread Group
            e.printStackTrace(System.err);
        }
    }

    /**
     * Called by the Thread constructor.
     */
    final void addThread(Thread thread) throws IllegalThreadStateException {
        synchronized (threadRefs) {
            if (isDestroyed) {
                throw new IllegalThreadStateException();
            }
            threadRefs.add(new WeakReference<Thread>(thread));
        }
    }

    /**
     * Called by the VM when a Thread dies.
     */
    final void removeThread(Thread thread) throws IllegalThreadStateException {
        synchronized (threadRefs) {
            for (Iterator<Thread> i = threads.iterator(); i.hasNext(); ) {
                if (i.next().equals(thread)) {
                    i.remove();
                    break;
                }
            }
        }
        destroyIfEmptyDaemon();
    }
}
