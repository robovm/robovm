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

package tests.api.org.apache.harmony.kernel.dalvik;

import java.lang.reflect.Field;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import junit.framework.TestCase;
import sun.misc.Unsafe;

/**
 * Tests for the <code>park()</code> functionality of {@link Unsafe}.
 */
public class ThreadsTest extends TestCase {
    private static Unsafe UNSAFE = null;
    static {
        /*
         * Set up {@link #UNSAFE}. This subverts the access check to
         * get the unique Unsafe instance. We can do this because
         * there's no security manager installed when running the
         * test.
         */
        try {
            Field field = Unsafe.class.getDeclaredField("THE_ONE");
            field.setAccessible(true);

            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /** Test the case where the park times out. */
    public void test_parkFor_1() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Parker parker = new Parker(barrier, false, 500);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(barrier, 1000, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(500);
        waiterThread.join();
        parkerThread.join();
    }

    /** Test the case where the unpark happens before the timeout. */
    public void test_parkFor_2() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Parker parker = new Parker(barrier, false, 1000);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(barrier, 300, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(300);
        waiterThread.join();
        parkerThread.join();
    }

    /** Test the case where the thread is preemptively unparked. */
    public void test_parkFor_3() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(1);
        Parker parker = new Parker(barrier, false, 1000);
        Thread parkerThread = new Thread(parker);

        UNSAFE.unpark(parkerThread);
        parkerThread.start();
        parker.assertDurationIsInRange(0);
        parkerThread.join();
    }

    /** Test the case where the park times out. */
    public void test_parkUntil_1() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Parker parker = new Parker(barrier, true, 500);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(barrier, 1000, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(500);
        waiterThread.join();
        parkerThread.join();
    }

    /** Test the case where the unpark happens before the timeout. */
    public void test_parkUntil_2() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Parker parker = new Parker(barrier, true, 1000);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(barrier, 300, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(300);
        waiterThread.join();
        parkerThread.join();
    }

    /** Test the case where the thread is preemptively unparked. */
    public void test_parkUntil_3() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(1);
        Parker parker = new Parker(barrier, true, 1000);
        Thread parkerThread = new Thread(parker);

        UNSAFE.unpark(parkerThread);
        parkerThread.start();
        parker.assertDurationIsInRange(0);
        parkerThread.join();
    }

    // TODO: Add more tests.

    /**
     * Helper <code>Runnable</code> for tests, which parks for or until
     * the indicated value, noting the duration of time actually parked.
     */
    private static class Parker implements Runnable {

        private final CyclicBarrier barrier;

        /** whether {@link #amount} is milliseconds to wait in an
         * absolute fashion (<code>true</code>) or nanoseconds to wait
         * in a relative fashion (<code>false</code>) */
        private final boolean absolute;

        /** amount to wait (see above) */
        private final long amount;

        /** whether the run has completed */
        private boolean completed;

        /** recorded start time */
        private long startMillis;

        /** recorded end time */
        private long endMillis;

        /**
         * Construct an instance.
         *
         * @param absolute whether to use an absolute time or not; in
         * either case, this constructor takes a duration to park for
         * @param parkMillis the number of milliseconds to be parked
         */
        public Parker(CyclicBarrier barrier, boolean absolute, long parkMillis) {
            this.barrier = barrier;
            this.absolute = absolute;

            // Multiply by 1000000 because parkFor() takes nanoseconds.
            this.amount = absolute ? parkMillis : parkMillis * 1000000;
        }

        public void run() {
            try {
                barrier.await(60, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
            boolean absolute = this.absolute;
            long amount = this.amount;
            long startNanos = System.nanoTime();
            long start = System.currentTimeMillis();

            if (absolute) {
                UNSAFE.park(true, start + amount);
            } else {
                UNSAFE.park(false, amount);
            }

            long endNanos = System.nanoTime();

            synchronized (this) {
                startMillis = startNanos / 1000000;
                endMillis = endNanos / 1000000;
                completed = true;
                notifyAll();
            }
        }

        /**
         * Wait for the test to complete and return the duration.
         *
         * @param maxWaitMillis the maximum amount of time to
         * wait for the test to complete
         * @return the duration in milliseconds
         */
        public long getDurationMillis(long maxWaitMillis) {
            synchronized (this) {
                if (! completed) {
                    try {
                        wait(maxWaitMillis);
                    } catch (InterruptedException ignored) {
                    }
                    if (! completed) {
                        Assert.fail("parker hung for more than " + maxWaitMillis + " ms");
                    }
                }

                return endMillis - startMillis;
            }
        }

        /**
         * Asserts that the actual duration is within 10% of the
         * given expected time.
         *
         * @param expectedMillis the expected duration, in milliseconds
         */
        public void assertDurationIsInRange(long expectedMillis) {
            /*
             * Allow a bit more slop for the maximum on "expected
             * instantaneous" results.
             */
            long minimum = (long) ((double) expectedMillis * 0.90);
            long maximum =
                Math.max((long) ((double) expectedMillis * 1.10), 10);
            long waitMillis = Math.max(expectedMillis * 10, 10);
            long duration = getDurationMillis(waitMillis);

            if (duration < minimum) {
                Assert.fail("expected duration: " + expectedMillis +
                            " minimum duration: " + minimum +
                            " actual duration too short: " + duration);
            } else if (duration > maximum) {
                Assert.fail("expected duration: " + expectedMillis +
                            " maximum duration: " + maximum +
                            " actual duration too long: " + duration);
            }
        }
    }

    /**
     * Helper <code>Runnable</code> for tests, which waits for the
     * specified amount of time and then unparks an indicated thread.
     */
    private static class WaitAndUnpark implements Runnable {
        private final CyclicBarrier barrier;
        private final long waitMillis;
        private final Thread thread;

        public WaitAndUnpark(CyclicBarrier barrier, long waitMillis, Thread thread) {
            this.barrier = barrier;
            this.waitMillis = waitMillis;
            this.thread = thread;
        }

        public void run() {
            try {
                barrier.await(60, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
            try {
                Thread.sleep(waitMillis);
            } catch (InterruptedException ex) {
                throw new RuntimeException("shouldn't happen", ex);
            }

            UNSAFE.unpark(thread);
        }
    }
}
