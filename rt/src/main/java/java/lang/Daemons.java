/*
 * Copyright (C) 2011 The Android Open Source Project
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

import dalvik.system.VMRuntime;
import java.lang.ref.FinalizerReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeoutException;
import libcore.util.EmptyArray;

/**
 * Calls Object.finalize() on objects in the finalizer reference queue. The VM
 * will abort if any finalize() call takes more than the maximum finalize time
 * to complete.
 *
 * @hide
 */
public final class Daemons {
    private static final int NANOS_PER_MILLI = 1000000;
    private static final long MAX_FINALIZE_MILLIS = 10L * 1000L; // 10 seconds

    public static void start() {
        ReferenceQueueDaemon.INSTANCE.start();
        FinalizerDaemon.INSTANCE.start();
        FinalizerWatchdogDaemon.INSTANCE.start();
    }

    public static void stop() {
        ReferenceQueueDaemon.INSTANCE.stop();
        FinalizerDaemon.INSTANCE.stop();
        FinalizerWatchdogDaemon.INSTANCE.stop();
    }

    /**
     * A background task that provides runtime support to the application.
     * Daemons can be stopped and started, but only so that the zygote can be a
     * single-threaded process when it forks.
     */
    private static abstract class Daemon implements Runnable {
        private Thread thread;

        public synchronized void start() {
            if (thread != null) {
                throw new IllegalStateException("already running");
            }
            thread = new Thread(this, getClass().getSimpleName());
            thread.setDaemon(true);
            thread.start();
        }

        public abstract void run();

        /**
         * Returns true while the current thread should continue to run; false
         * when it should return.
         */
        protected synchronized boolean isRunning() {
            return thread != null;
        }

        public synchronized void interrupt() {
            if (thread == null) {
                throw new IllegalStateException("not running");
            }
            thread.interrupt();
        }

        /**
         * Waits for the runtime thread to stop. This interrupts the thread
         * currently running the runnable and then waits for it to exit.
         */
        public void stop() {
            Thread threadToStop;
            synchronized (this) {
                threadToStop = thread;
                thread = null;
            }
            if (threadToStop == null) {
                throw new IllegalStateException("not running");
            }
            threadToStop.interrupt();
            while (true) {
                try {
                    threadToStop.join();
                    return;
                } catch (InterruptedException ignored) {
                }
            }
        }

        /**
         * Returns the current stack trace of the thread, or an empty stack trace
         * if the thread is not currently running.
         */
        public synchronized StackTraceElement[] getStackTrace() {
            return thread != null ? thread.getStackTrace() : EmptyArray.STACK_TRACE_ELEMENT;
        }
    }

    /**
     * This heap management thread moves elements from the garbage collector's
     * pending list to the managed reference queue.
     */
    private static class ReferenceQueueDaemon extends Daemon {
        private static final ReferenceQueueDaemon INSTANCE = new ReferenceQueueDaemon();

        @Override public void run() {
            while (isRunning()) {
                Reference<?> list;
                try {
                    synchronized (ReferenceQueue.class) {
                        while (ReferenceQueue.unenqueued == null) {
                            ReferenceQueue.class.wait();
                        }
                        list = ReferenceQueue.unenqueued;
                        ReferenceQueue.unenqueued = null;
                    }
                } catch (InterruptedException e) {
                    continue;
                }
                enqueue(list);
            }
        }

        private void enqueue(Reference<?> list) {
            while (list != null) {
                Reference<?> reference;
                // pendingNext is owned by the GC so no synchronization is required
                if (list == list.pendingNext) {
                    reference = list;
                    reference.pendingNext = null;
                    list = null;
                } else {
                    reference = list.pendingNext;
                    list.pendingNext = reference.pendingNext;
                    reference.pendingNext = null;
                }
                reference.enqueueInternal();
            }
        }
    }

    private static class FinalizerDaemon extends Daemon {
        private static final FinalizerDaemon INSTANCE = new FinalizerDaemon();
        private final ReferenceQueue<Object> queue = FinalizerReference.queue;
        private volatile Object finalizingObject;
        private volatile long finalizingStartedNanos;

        @Override public void run() {
            while (isRunning()) {
                // Take a reference, blocking until one is ready or the thread should stop
                try {
                    doFinalize((FinalizerReference<?>) queue.remove());
                } catch (InterruptedException ignored) {
                }
            }
        }

        @FindBugsSuppressWarnings("FI_EXPLICIT_INVOCATION")
        private void doFinalize(FinalizerReference<?> reference) {
            FinalizerReference.remove(reference);
            Object object = reference.get();
            reference.clear();
            try {
                finalizingStartedNanos = System.nanoTime();
                finalizingObject = object;
                object.finalize();
            } catch (Throwable ex) {
                // The RI silently swallows these, but Android has always logged.
                System.logE("Uncaught exception thrown by finalizer", ex);
            } finally {
                finalizingObject = null;
            }
        }
    }

    /**
     * The watchdog exits the VM if the finalizer ever gets stuck. We consider
     * the finalizer to be stuck if it spends more than MAX_FINALIZATION_MILLIS
     * on one instance.
     */
    private static class FinalizerWatchdogDaemon extends Daemon {
        private static final FinalizerWatchdogDaemon INSTANCE = new FinalizerWatchdogDaemon();

        @Override public void run() {
            while (isRunning()) {
                Object object = FinalizerDaemon.INSTANCE.finalizingObject;
                long startedNanos = FinalizerDaemon.INSTANCE.finalizingStartedNanos;

                long sleepMillis = MAX_FINALIZE_MILLIS;
                if (object != null) {
                    long elapsedMillis = (System.nanoTime() - startedNanos) / NANOS_PER_MILLI;
                    sleepMillis -= elapsedMillis;
                }

                if (sleepMillis > 0) {
                    try {
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e) {
                        continue;
                    }
                }

                if (object == null
                        || object != FinalizerDaemon.INSTANCE.finalizingObject
                        || VMRuntime.getRuntime().isDebuggerActive()) {
                    continue;
                }

                // The current object has exceeded the finalization deadline; abort!
                long elapsedMillis = (System.nanoTime() - startedNanos) / NANOS_PER_MILLI;
                Exception syntheticException = new TimeoutException();
                syntheticException.setStackTrace(FinalizerDaemon.INSTANCE.getStackTrace());
                System.logE(object.getClass().getName() + ".finalize() timed out after "
                        + elapsedMillis + " ms; limit is " + MAX_FINALIZE_MILLIS + " ms",
                        syntheticException);
                System.exit(2);
            }
        }
    }
}
