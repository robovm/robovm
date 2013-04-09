/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.java.lang;

import java.util.concurrent.atomic.AtomicInteger;
import junit.framework.Assert;
import junit.framework.TestCase;
import libcore.java.lang.ref.FinalizationTester;

public final class ThreadTest extends TestCase {

    /**
     * getContextClassLoader returned a non-application class loader.
     * http://code.google.com/p/android/issues/detail?id=5697
     */
    public void testJavaContextClassLoader() throws Exception {
        Assert.assertNotNull("Must have a Java context ClassLoader",
                Thread.currentThread().getContextClassLoader());
    }

    public void testLeakingStartedThreads() {
        final AtomicInteger finalizedThreadsCount = new AtomicInteger();
        for (int i = 0; true; i++) {
            try {
                newThread(finalizedThreadsCount, 1024 << i).start();
            } catch (OutOfMemoryError expected) {
                break;
            }
        }
        FinalizationTester.induceFinalization();
        assertTrue("Started threads were never finalized!", finalizedThreadsCount.get() > 0);
    }

    public void testLeakingUnstartedThreads() {
        final AtomicInteger finalizedThreadsCount = new AtomicInteger();
        for (int i = 0; true; i++) {
            try {
                newThread(finalizedThreadsCount, 1024 << i);
            } catch (OutOfMemoryError expected) {
                break;
            }
        }
        FinalizationTester.induceFinalization();
        assertTrue("Unstarted threads were never finalized!", finalizedThreadsCount.get() > 0);
    }

    private Thread newThread(final AtomicInteger finalizedThreadsCount, final int size) {
        return new Thread() {
            byte[] memoryPressure = new byte[size];
            @Override protected void finalize() throws Throwable {
                super.finalize();
                finalizedThreadsCount.incrementAndGet();
            }
        };
    }

    /**
     * Thread.getStackTrace() is broken. http://b/1252043
     */
    public void testGetStackTrace() throws Exception {
        Thread t1 = new Thread("t1") {
            @Override public void run() {
                doSomething();
            }
            public void doSomething() {
                for (int i = 0; i < 20; i++) { // RoboVM note: Added missing i++
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        StackTraceElement[] traces = t1.getStackTrace();
        StackTraceElement trace = traces[traces.length - 2];

        // Expect to find MyThread.doSomething in the trace
        assertTrue(trace.getClassName().contains("ThreadTest")
                && trace.getMethodName().equals("doSomething"));
    }

    public void testGetAllStackTracesIncludesAllGroups() throws Exception {
        final AtomicInteger visibleTraces = new AtomicInteger();
        ThreadGroup group = new ThreadGroup("1");
        Thread t2 = new Thread(group, "t2") {
            @Override public void run() {
                visibleTraces.set(Thread.getAllStackTraces().size());
            }
        };
        t2.start();
        t2.join();

        // Expect to see the traces of all threads (not just t2)
        assertTrue("Must have traces for all threads", visibleTraces.get() > 1);
    }
}
