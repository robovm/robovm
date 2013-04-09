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

import junit.framework.Assert;
import junit.framework.TestCase;
import sun.misc.Unsafe;

/**
 * Tests for the <code>park()</code> functionality of {@link Unsafe}.
 */
public class ThreadsTest extends TestCase {
    private static Unsafe UNSAFE = null;
    private static RuntimeException INITIALIZEFAILED = null;

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
            INITIALIZEFAILED = new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            INITIALIZEFAILED = new RuntimeException(ex);
        }
    }

    /** Test the case where the park times out. */
    public void test_parkFor_1() {
        Parker parker = new Parker(false, 500);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(1000, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(500);
    }

    /** Test the case where the unpark happens before the timeout. */
    public void test_parkFor_2() {
        Parker parker = new Parker(false, 1000);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(300, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(300);
    }

    /** Test the case where the thread is preemptively unparked. */
    public void test_parkFor_3() {
        Parker parker = new Parker(false, 1000);
        Thread parkerThread = new Thread(parker);

        UNSAFE.unpark(parkerThread);
        parkerThread.start();
        parker.assertDurationIsInRange(0);
    }

    /** Test the case where the park times out. */
    public void test_parkUntil_1() {
        Parker parker = new Parker(true, 500);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(1000, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(500);
    }

    /** Test the case where the unpark happens before the timeout. */
    public void test_parkUntil_2() {
        Parker parker = new Parker(true, 1000);
        Thread parkerThread = new Thread(parker);
        Thread waiterThread =
            new Thread(new WaitAndUnpark(300, parkerThread));

        parkerThread.start();
        waiterThread.start();
        parker.assertDurationIsInRange(300);
    }

    /** Test the case where the thread is preemptively unparked. */
    public void test_parkUntil_3() {
        Parker parker = new Parker(true, 1000);
        Thread parkerThread = new Thread(parker);

        UNSAFE.unpark(parkerThread);
        parkerThread.start();
        parker.assertDurationIsInRange(0);
    }

    // TODO: Add more tests.

    /**
     * Helper <code>Runnable</code> for tests, which parks for or until
     * the indicated value, noting the duration of time actually parked.
     */
    private static class Parker implements Runnable {
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
        public Parker(boolean absolute, long parkMillis) {
            this.absolute = absolute;

            // Multiply by 1000000 because parkFor() takes nanoseconds.
            this.amount = absolute ? parkMillis : parkMillis * 1000000;
        }

        public void run() {
            boolean absolute = this.absolute;
            long amount = this.amount;
            long start = System.currentTimeMillis();

            if (absolute) {
                UNSAFE.park(true, start + amount);
            } else {
                UNSAFE.park(false, amount);
            }

            long end = System.currentTimeMillis();

            synchronized (this) {
                startMillis = start;
                endMillis = end;
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
                    } catch (InterruptedException ex) {
                        // Ignore it.
                    }
                    if (! completed) {
                        Assert.fail("parker hanging");
                    }
                }

                return endMillis - startMillis;
            }
        }

        /**
         * Asserts that the actual duration is within 5% of the
         * given expected time.
         *
         * @param expectedMillis the expected duration, in milliseconds
         */
        public void assertDurationIsInRange(long expectedMillis) {
            /*
             * Allow a bit more slop for the maximum on "expected
             * instantaneous" results.
             */
            long minimum = (long) ((double) expectedMillis * 0.95);
            long maximum =
                Math.max((long) ((double) expectedMillis * 1.05), 10);
            long waitMillis = Math.max(expectedMillis * 10, 10);
            long duration = getDurationMillis(waitMillis);

            if (duration < minimum) {
                Assert.fail("expected duration: " + expectedMillis +
                        "; actual too short: " + duration);
            } else if (duration > maximum) {
                Assert.fail("expected duration: " + expectedMillis +
                        "; actual too long: " + duration);
            }
        }
    }

    /**
     * Helper <code>Runnable</code> for tests, which waits for the
     * specified amount of time and then unparks an indicated thread.
     */
    private static class WaitAndUnpark implements Runnable {
        private final long waitMillis;
        private final Thread thread;

        public WaitAndUnpark(long waitMillis, Thread thread) {
            this.waitMillis = waitMillis;
            this.thread = thread;
        }

        public void run() {
            try {
                Thread.sleep(waitMillis);
            } catch (InterruptedException ex) {
                throw new RuntimeException("shouldn't happen", ex);
            }

            UNSAFE.unpark(thread);
        }
    }

    @Override
    protected void setUp() throws Exception {
        if (INITIALIZEFAILED != null) {
            throw INITIALIZEFAILED;
        }
    }
}
