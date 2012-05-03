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

class VMThread {
    Thread thread;
    int vmData;

    VMThread(Thread t) {
        thread = t;
    }

    native static void create(Thread t, long stackSize);

    static native Thread currentThread();
    static native boolean interrupted();
    static native void sleep (long msec, int nsec) throws InterruptedException;
    static native void yield();

    native void interrupt();

    native boolean isInterrupted();

    /**
     *  Starts the VMThread (and thus the Java Thread) with the given
     *  stack size.
     */
    void start(long stackSize) {
        VMThread.create(thread, stackSize);
    }

    /**
     * Queries whether this Thread holds a monitor lock on the
     * given object.
     */
    native boolean holdsLock(Object object);

    native void setPriority(int newPriority);
    native int getStatus();

    /**
     * Holds a mapping from native Thread statuses to Java one. Required for
     * translating back the result of getStatus().
     */
    static final Thread.State[] STATE_MAP = new Thread.State[] {
        Thread.State.TERMINATED,     // ZOMBIE
        Thread.State.RUNNABLE,       // RUNNING
        Thread.State.TIMED_WAITING,  // TIMED_WAIT
        Thread.State.BLOCKED,        // MONITOR
        Thread.State.WAITING,        // WAIT
        Thread.State.NEW,            // INITIALIZING
        Thread.State.NEW,            // STARTING
        Thread.State.RUNNABLE,       // NATIVE
        Thread.State.WAITING,        // VMWAIT
        Thread.State.RUNNABLE        // SUSPENDED
    };

    /**
     * Tell the VM that the thread's name has changed.  This is useful for
     * DDMS, which would otherwise be oblivious to Thread.setName calls.
     */
    native void nameChanged(String newName);
}
