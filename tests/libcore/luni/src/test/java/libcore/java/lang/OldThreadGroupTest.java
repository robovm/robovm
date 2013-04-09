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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;

public class OldThreadGroupTest extends TestCase implements Thread.UncaughtExceptionHandler {

    class MyThread extends Thread {
        public volatile int heartBeat = 0;

        public MyThread(ThreadGroup group, String name)
                throws SecurityException, IllegalThreadStateException {
            super(group, name);
        }

        @Override
        public void run() {
            while (true) {
                heartBeat++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        public boolean isActivelyRunning() {
            long MAX_WAIT = 100;
            return isActivelyRunning(MAX_WAIT);
        }

        public boolean isActivelyRunning(long maxWait) {
            int beat = heartBeat;
            long start = System.currentTimeMillis();
            do {
                Thread.yield();
                int beat2 = heartBeat;
                if (beat != beat2) {
                    return true;
                }
            } while (System.currentTimeMillis() - start < maxWait);
            return false;
        }

    }

    private ThreadGroup initialThreadGroup = null;

    public void test_activeGroupCount() {
        ThreadGroup tg = new ThreadGroup("group count");
        assertEquals("Incorrect number of groups",
                0, tg.activeGroupCount());
        Thread t1 = new Thread(tg, new Runnable() {
            public void run() {

            }
        });
        assertEquals("Incorrect number of groups",
                0, tg.activeGroupCount());
        t1.start();
        assertEquals("Incorrect number of groups",
                0, tg.activeGroupCount());
        new ThreadGroup(tg, "test group 1");
        assertEquals("Incorrect number of groups",
                1, tg.activeGroupCount());
        new ThreadGroup(tg, "test group 2");
        assertEquals("Incorrect number of groups",
                2, tg.activeGroupCount());
    }

    @SuppressWarnings("deprecation")
    public void test_allowThreadSuspensionZ() {
        ThreadGroup tg = new ThreadGroup("thread suspension");
        assertTrue("Thread suspention can not be changed",
                tg.allowThreadSuspension(false));
        assertTrue("Thread suspention can not be changed",
                tg.allowThreadSuspension(true));
    }

    /*
     * Checks whether the current Thread is in the given list.
     */
    private boolean inListOfThreads(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            if (Thread.currentThread() == threads[i]) {
                return true;
            }
        }

        return false;
    }

    public void test_enumerateLThreadArray() {
        int numThreads = initialThreadGroup.activeCount();
        Thread[] listOfThreads = new Thread[numThreads];

        int countThread = initialThreadGroup.enumerate(listOfThreads);
        assertEquals(numThreads, countThread);
        assertTrue("Current thread must be in enumeration of threads",
                inListOfThreads(listOfThreads));
    }

    public void test_enumerateLThreadArrayLZtest_enumerateLThreadArrayLZ() throws Exception {
        // capture the initial condition
        int initialThreadCount = initialThreadGroup.activeCount();
        Thread[] initialThreads = new Thread[initialThreadCount];
        assertEquals(initialThreadCount, initialThreadGroup.enumerate(initialThreads, false));
        assertEquals(initialThreadCount, initialThreadGroup.enumerate(initialThreads, true));
        assertTrue(inListOfThreads(initialThreads));

        // start some the threads and see how the count changes
        ThreadGroup group = new ThreadGroup(initialThreadGroup, "enumerateThreadArray");
        int groupSize = 3;
        List<MyThread> newThreads = populateGroupsWithThreads(group, groupSize);
        assertEquals(initialThreadCount, initialThreadGroup.enumerate(initialThreads, true));
        assertTrue(inListOfThreads(initialThreads));
        for(MyThread thread : newThreads) {
            thread.start();
        }
        Thread.sleep(500); // starting threads isn't instant!
        int afterStartCount = initialThreadGroup.activeCount();
        Set<Thread> initialPlusNew = new HashSet<Thread>();
        initialPlusNew.addAll(Arrays.asList(initialThreads));
        initialPlusNew.addAll(newThreads);
        Thread[] afterStartThreads = new Thread[afterStartCount];
        assertEquals(afterStartCount, initialThreadGroup.enumerate(afterStartThreads, true));
        assertEquals(initialPlusNew, new HashSet<Thread>(Arrays.asList(afterStartThreads)));
        assertTrue(inListOfThreads(afterStartThreads));

        // kill the threads and count 'em again
        for(MyThread thread : newThreads) {
            thread.interrupt();
        }
        Thread.sleep(500); // killing threads isn't instant
        int afterDeathCount = initialThreadGroup.activeCount();
        Thread[] afterDeathThreads = new Thread[afterDeathCount];
        assertEquals(afterDeathCount, initialThreadGroup.enumerate(afterDeathThreads, false));
        assertEquals(Arrays.asList(initialThreads), Arrays.asList(afterDeathThreads));
        assertTrue(inListOfThreads(afterDeathThreads));
    }

    public void test_enumerateLThreadGroupArray() {
        int numChildGroupsBefore = initialThreadGroup.activeGroupCount();
        ThreadGroup childGroup = new ThreadGroup(initialThreadGroup, "child group");

        int numChildGroupsAfter = initialThreadGroup.activeGroupCount();
        assertTrue(initialThreadGroup.toString(), numChildGroupsAfter == numChildGroupsBefore + 1);
        ThreadGroup[] listOfGroups = new ThreadGroup[numChildGroupsAfter];

        int countGroupThread = initialThreadGroup.enumerate(listOfGroups);
        assertEquals(numChildGroupsAfter, countGroupThread);
        assertTrue(Arrays.asList(listOfGroups).contains(childGroup));

        ThreadGroup[] listOfGroups1 = new ThreadGroup[numChildGroupsAfter + 1];
        countGroupThread = initialThreadGroup.enumerate(listOfGroups1);
        assertEquals(numChildGroupsAfter, countGroupThread);
        assertNull(listOfGroups1[listOfGroups1.length - 1]);

        ThreadGroup[] listOfGroups2 = new ThreadGroup[numChildGroupsAfter - 1];
        countGroupThread = initialThreadGroup.enumerate(listOfGroups2);
        assertEquals(numChildGroupsAfter - 1, countGroupThread);

        ThreadGroup thrGroup1 = new ThreadGroup("Test Group 1");
        countGroupThread = thrGroup1.enumerate(listOfGroups);
        assertEquals(0, countGroupThread);

        childGroup.destroy();
        assertTrue(initialThreadGroup.activeGroupCount() == numChildGroupsBefore + 1);
     }

    public void test_enumerateLThreadGroupArrayLZ() {
        ThreadGroup thrGroup = new ThreadGroup("Test Group 1");
        List<MyThread> subThreads = populateGroupsWithThreads(thrGroup, 3);
        int numGroupThreads = thrGroup.activeGroupCount();
        ThreadGroup[] listOfGroups = new ThreadGroup[numGroupThreads];

        assertEquals(0, thrGroup.enumerate(listOfGroups, true));
        assertEquals(0, thrGroup.enumerate(listOfGroups, false));

        for(MyThread thr:subThreads) {
            thr.start();
        }

        numGroupThreads = thrGroup.activeGroupCount();
        listOfGroups = new ThreadGroup[numGroupThreads];

        assertEquals(0, thrGroup.enumerate(listOfGroups, true));
        assertEquals(0, thrGroup.enumerate(listOfGroups, false));

        ThreadGroup subGroup1 = new ThreadGroup(thrGroup, "Test Group 2");
        List<MyThread> subThreads1 = populateGroupsWithThreads(subGroup1, 3);
        numGroupThreads = thrGroup.activeGroupCount();
        listOfGroups = new ThreadGroup[numGroupThreads];

        assertEquals(1, thrGroup.enumerate(listOfGroups, true));
        assertEquals(1, thrGroup.enumerate(listOfGroups, false));

        for(MyThread thr:subThreads1) {
            thr.start();
        }
        numGroupThreads = thrGroup.activeGroupCount();
        listOfGroups = new ThreadGroup[numGroupThreads];

        assertEquals(1, thrGroup.enumerate(listOfGroups, true));
        assertEquals(1, thrGroup.enumerate(listOfGroups, false));

        for(MyThread thr:subThreads) {
            thr.interrupt();
         }

        ThreadGroup subGroup2 = new ThreadGroup(subGroup1, "Test Group 3");
        List<MyThread> subThreads2 = populateGroupsWithThreads(subGroup2, 3);
        numGroupThreads = thrGroup.activeGroupCount();
        listOfGroups = new ThreadGroup[numGroupThreads];

        assertEquals(2, thrGroup.enumerate(listOfGroups, true));
        assertEquals(1, thrGroup.enumerate(listOfGroups, false));
        
        // RoboVM note: Cleanup. Wait for the threads to finish.
        List<MyThread> allThreads = new ArrayList<MyThread>();
        allThreads.addAll(subThreads);
        allThreads.addAll(subThreads1);
        allThreads.addAll(subThreads2);
        for(MyThread thr:allThreads) {
            thr.interrupt();
            try {
                thr.join(5000);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * java.lang.ThreadGroup#interrupt()
     */
    private static boolean interrupted = false;
    public void test_interrupt() {

        Thread.setDefaultUncaughtExceptionHandler(this);
        ThreadGroup tg = new ThreadGroup("interrupt");
        Thread t1 = new Thread(tg, new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    fail("ok");
                }
            }
        });
        assertFalse("Incorrect state of thread", interrupted);
        t1.start();
        assertFalse("Incorrect state of thread", interrupted);
        t1.interrupt();
        try {
            t1.join();
        } catch (InterruptedException e) {
        }
        assertTrue("Incorrect state of thread", interrupted);
        tg.destroy();
    }

    public void test_isDestroyed() {
        final ThreadGroup originalCurrent = getInitialThreadGroup();
        final ThreadGroup testRoot = new ThreadGroup(originalCurrent,
                "Test group");
        assertFalse("Test group is not destroyed yet",
                testRoot.isDestroyed());
        testRoot.destroy();
        assertTrue("Test group already destroyed",
                testRoot.isDestroyed());
    }

    @SuppressWarnings("deprecation")
    public void test_resume() {
        ThreadGroup group = new ThreadGroup("Foo");

        Thread thread = launchFiveSecondDummyThread(group);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }

        try {
            group.resume();
            fail();
        } catch (UnsupportedOperationException expected) {
        }
        
        try {
            // RoboVM note: Cleanup. Wait for the thread to finish.
            thread.join(5000);
        } catch (InterruptedException e) {
        }
    }

    private Thread launchFiveSecondDummyThread(ThreadGroup group) {
        Thread thread = new Thread(group, "Bar") {
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

    /*
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
     */
    public void uncaughtException(Thread t, Throwable e) {
        interrupted = true;
        Thread.setDefaultUncaughtExceptionHandler(null);
    }

    @Override
    protected void setUp() {
        initialThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup rootThreadGroup = initialThreadGroup;
        while (rootThreadGroup.getParent() != null) {
            rootThreadGroup = rootThreadGroup.getParent();
        }
    }

    @Override
    protected void tearDown() {
        try {
            // Give the threads a chance to die.
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    private ThreadGroup getInitialThreadGroup() {
        return initialThreadGroup;
    }

    private ThreadGroup[] groups(ThreadGroup parent) {
        // No API to get the count of immediate children only
        int count = parent.activeGroupCount();
        ThreadGroup[] all = new ThreadGroup[count];
        parent.enumerate(all, false);
        // Now we may have nulls in the array, we must find the actual size
        int actualSize = 0;
        for (; actualSize < all.length; actualSize++) {
            if (all[actualSize] == null) {
                break;
            }
        }
        return Arrays.copyOfRange(all, 0, actualSize);
    }

    private List<MyThread> populateGroupsWithThreads(ThreadGroup group, int threadCount) {
        List<MyThread> result = new ArrayList<MyThread>();
        populateGroupsWithThreads(group, threadCount, result);
        return result;
    }

    private void populateGroupsWithThreads(ThreadGroup group, int threadCount, List<MyThread> out) {
        for (int i = 0; i < threadCount; i++) {
            out.add(new MyThread(group, "MyThread " + i + " of " + threadCount));
        }

        // Recursively for subgroups (if any)
        ThreadGroup[] children = groups(group);
        for (ThreadGroup element : children) {
            populateGroupsWithThreads(element, threadCount, out);
        }
    }
}
