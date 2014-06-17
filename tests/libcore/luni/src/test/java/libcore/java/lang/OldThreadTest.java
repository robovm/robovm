/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package libcore.java.lang;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;
import libcore.java.lang.ref.FinalizationTester;

public class OldThreadTest extends junit.framework.TestCase {

    static class SimpleThread implements Runnable {
        int delay;

        public void run() {
            try {
                synchronized (this) {
                    this.notify();
                    this.wait(delay);
                }
            } catch (InterruptedException e) {
                return;
            }

        }

        public SimpleThread(int d) {
            if (d >= 0)
                delay = d;
        }
    }

    public void test_ConstructorLjava_lang_ThreadGroupLjava_lang_RunnableLjava_lang_StringL$L() {
        ThreadGroup tg = new ThreadGroup("Test Group2");
        st = new Thread(tg, new SimpleThread(1), "SimpleThread3", 1);
        assertTrue("Constructed incorrect thread", (st.getThreadGroup() == tg)
                && st.getName().equals("SimpleThread3"));
        st.start();
        try {
            st.join();
        } catch (InterruptedException e) {
        }
        tg.destroy();

        try {
            new Thread(tg, new SimpleThread(1), "SimpleThread3",
                    Integer.MAX_VALUE);
            fail("StackOverflowError/OutOfMemoryError is not thrown.");
        } catch(IllegalThreadStateException itse) {
            //expected
        }

    }

    public void test_dumpStack() {
        try {
            PrintStream savedErr = System.err;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setErr(new PrintStream(baos));
            Thread.dumpStack();
            System.setErr(savedErr);

            String s = new String(baos.toByteArray());

            assertTrue(s.contains("java.lang.Thread.dumpStack"));

        } catch(Exception e) {
            fail("Unexpected exception was thrown: " + e.toString());
        }
    }

    class MonitoredClass {
        public synchronized void enterLocked() {
            boolean b = Thread.holdsLock(this);
            assertTrue("Thread should hold lock for object", b);
        }

        public void enterNonLocked() {
            boolean b = Thread.holdsLock(this);
            assertFalse("Thread should not hold lock for object", b);
        }

    }

    boolean wasInterrupted = false;

    public void test_joinWithSpuriousInterruption() throws InterruptedException {
        final Thread parker = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // we used to get spurious wakeups upon unparking
                    LockSupport.park();
                }
            }
        };
        Thread unparker = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                        LockSupport.unpark(parker);
                    } catch (InterruptedException expected) {
                    }
                }
            }
        };

        long startNanos = System.nanoTime();
        parker.start();
        unparker.start();
        parker.join(500, 500000);
        long netWaitTime = System.nanoTime() - startNanos;
        assertTrue("Expected to wait at least 500000000ns, but was " + netWaitTime + "ns",
                netWaitTime > 500000000);
    }

    public void test_setContextClassLoader() {
        ClassLoader pcl = new ClassLoader() {};
        st = new Thread();
        st.setContextClassLoader(pcl);
        assertEquals(pcl, st.getContextClassLoader());

        st.setContextClassLoader(null);
        assertNull(st.getContextClassLoader());
    }

    public void test_setDaemonZ() {
        st = new Thread(new SimpleThread(5));
        st.start();
        try {
            st.setDaemon(false);
            fail("setDaemon() must throw exception for started thread");
        } catch (IllegalThreadStateException ex) {
            // We expect this one.
        }
    }

    private Thread launchFiveSecondDummyThread() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
        };

        thread.start();

        return thread;
    }

    /**
     * java.lang.Thread#sleep(long)
     */
    public void test_sleepJ() {
        // Note: Not too much we can test here that can be reliably measured.

        // Check that basic behavior is about right (with some tolerance)
        long stime = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        long ftime = System.currentTimeMillis();

        assertTrue("Failed to sleep long enough", (ftime - stime) >= 500);
        assertTrue("Failed to wake up early enough", (ftime - stime) <= 1500);

        // Check that interrupt works
        st = new Thread() {
            public void run() {
                try {
                    sleep(10000);
                } catch(InterruptedException ie) {
                    wasInterrupted = true;
                }
            }
        };

        st.start();

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        st.interrupt();

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        assertTrue(wasInterrupted);
    }

    public void test_sleepJI() {
        // Note: Not too much we can test here that can be reliably measured.

        // Check that basic behavior is about right (with some tolerance)
        long stime = System.currentTimeMillis();

        try {
            Thread.sleep(1000, 99999);
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        long ftime = System.currentTimeMillis();

        assertTrue("Failed to sleep long enough", (ftime - stime) >= 500);
        assertTrue("Failed to wake up early enough", (ftime - stime) <= 1500);

        // Check that interrupt works
        st = new Thread() {
            public void run() {
                try {
                    sleep(10000, 99999);
                } catch(InterruptedException ie) {
                    wasInterrupted = true;
                }
            }
        };

        st.start();

        try {
            Thread.sleep(5000, 99999);
        } catch(InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        st.interrupt();

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            fail("Unexpected InterruptedException was thrown");
        }

        assertTrue(wasInterrupted);
    }

    public void test_yield() {

        Counter [] countersNotYeld = new Counter[10];

        for(int i = 0; i < 10; i++) {
            countersNotYeld[i] = new Counter(false);
        }
        Counter countersYeld = new Counter(true);
        try {
            Thread.sleep(11000);
        } catch(InterruptedException ie) {}

        for(Counter c:countersNotYeld) {
            assertTrue(countersYeld.counter == c.counter);
        }
    }

    class Counter extends Thread {
        public int counter = 0;
        boolean isDoYield = false;

        public Counter(boolean isDoYield) {
            this.isDoYield = isDoYield;
            start();
        }

        public void run() {
            for(int i = 0; i < 10000; i++) {
                if(isDoYield)
                    yield();
                counter ++;
            }
        }
    }


    public void test_getState() throws InterruptedException {
        Thread.State state = Thread.currentThread().getState();
        assertNotNull(state);
        assertEquals(Thread.State.RUNNABLE, state);

        run = true;
        final Semaphore sem = new Semaphore(0);
        final Object lock = new Object();
        Thread th = new Thread() {
            @Override
            public void run() {
                  while (!sem.hasQueuedThreads()) {}
                  sem.release();

                  // RUNNABLE
                  while (run) {}

                  try {
                      // WAITING
                      sem.acquire();
                  } catch (InterruptedException e) {
                      fail("InterruptedException was thrown.");
                  }

                  // BLOCKED
                  synchronized (lock) {
                      lock.equals(new Object());
                  }
                  synchronized (lock) {
                      try {
                        sem.release();

                        // TIMED_WAITING
                        lock.wait(Long.MAX_VALUE);
                      } catch (InterruptedException e) {
                          // expected
                      }
                  }

                  // TERMINATED upon return
            }
        };
        assertEquals(Thread.State.NEW, th.getState());
        th.start();
        sem.acquire();
        assertEquals(Thread.State.RUNNABLE, th.getState());
        run = false;

        Thread.sleep(200);

        assertEquals(Thread.State.WAITING, th.getState());
        synchronized (lock) {
            sem.release();
            long start = System.currentTimeMillis();
            while(start + 1000 > System.currentTimeMillis()) {}
            assertEquals(Thread.State.BLOCKED, th.getState());
        }

        sem.acquire();

        synchronized (lock) {
            assertEquals(Thread.State.TIMED_WAITING, th.getState());
            th.interrupt();
        }

        th.join(1000);
        assertEquals(Thread.State.TERMINATED, th.getState());
    }
    volatile boolean run;

    public void test_holdsLock() {
        MonitoredClass monitor = new MonitoredClass();

        monitor.enterLocked();
        monitor.enterNonLocked();

        try {
            Thread.holdsLock(null);
            fail("NullPointerException was not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    @SuppressWarnings("deprecation")
    public void test_stop() {
        Thread thread = launchFiveSecondDummyThread();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }

        try {
            thread.stop();
            fail();
        } catch (UnsupportedOperationException expected) {
        }
    }

    public void test_start() {
        Thread thr = new Thread();
        thr.start();
        try {
            thr.start();
        } catch(IllegalThreadStateException itse){
            //expected
        }
    }

    @SuppressWarnings("deprecation")
    public void test_stopLjava_lang_Throwable_subtest0() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }

        try {
            thread.stop(new Exception("Oops!"));
            fail();
        } catch (UnsupportedOperationException expected) {
        }
    }

    @SuppressWarnings("deprecation")
    public void test_suspend() {
        Thread thread = launchFiveSecondDummyThread();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }

        try {
            thread.suspend();
            fail();
        } catch (UnsupportedOperationException expected) {
        }
    }

    Thread st, ct, spinner;

    @Override
    protected void tearDown() {
        try {
            if (st != null)
                st.interrupt();
        } catch (Exception e) {
        }
        try {
            if (spinner != null)
                spinner.interrupt();
        } catch (Exception e) {
        }
        try {
            if (ct != null)
                ct.interrupt();
        } catch (Exception e) {
        }

        try {
            spinner = null;
            st = null;
            ct = null;
            FinalizationTester.induceFinalization();
        } catch (Exception e) {
        }
    }
}
