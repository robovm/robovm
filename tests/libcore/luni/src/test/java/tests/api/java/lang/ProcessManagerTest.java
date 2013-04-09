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

package tests.api.java.lang;

import dalvik.annotation.BrokenTest;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ProcessManagerTest extends TestCase {

    Thread thread = null;
    Process process = null;
    boolean isThrown = false;

    public void testCat() throws IOException, InterruptedException {
        String[] commands = { "cat" };
        Process process = Runtime.getRuntime().exec(commands, null, null);

        OutputStream out = process.getOutputStream();
        String greeting = "Hello, World!";
        out.write(greeting.getBytes());
        out.write('\n');
        out.close();

        assertEquals(greeting, readLine(process));
    }

    @BrokenTest("Sporadic failures in CTS, but not in CoreTestRunner")
    public void testSleep() throws IOException {
        String[] commands = { "sleep", "1" };
        process = Runtime.getRuntime().exec(commands, null, null);
        try {
            assertEquals(0, process.waitFor());

        } catch(InterruptedException ie) {
            fail("InterruptedException was thrown.");
        }

        isThrown = false;
        thread = new Thread() {
            public void run() {
                String[] commands = { "sleep", "1000"};
                try {
                    process = Runtime.getRuntime().exec(commands, null, null);
                } catch (IOException e1) {
                    fail("IOException was thrown.");
                }
                try {
                    process.waitFor();
                    fail("InterruptedException was not thrown.");
                } catch(InterruptedException ie) {
                    isThrown = true;
                }
            }
        };

        Thread interruptThread = new Thread() {
            public void run() {
                try {
                    sleep(10);
                } catch(InterruptedException ie) {
                    fail("InterruptedException was thrown in " +
                            "the interruptThread.");
                }
                thread.interrupt();
            }
        };
        thread.start();
        interruptThread.start();
        try {
            interruptThread.join();
        } catch (InterruptedException e) {
            fail("InterruptedException was thrown.");
        }
        try {
            Thread.sleep(100);
        } catch(InterruptedException ie) {

        }

        thread.interrupt();
        //process.destroy();
        try {
            Thread.sleep(100);
        } catch(InterruptedException ie) {

        }

        assertTrue(isThrown);
    }

    public void testPwd() throws IOException, InterruptedException {
        String[] commands = { "sh", "-c", "pwd" };
        Process process = Runtime.getRuntime().exec(
                commands, null, new File("/"));
        logErrors(process);
        assertEquals("/", readLine(process));
    }

    public void testEnvironment() throws IOException, InterruptedException {
        String[] commands = { "sh", "-c", "echo $FOO" };

        // Remember to set the path so we can find sh.
        String[] environment = { "FOO=foo", "PATH=" + System.getenv("PATH") };
        Process process = Runtime.getRuntime().exec(
                commands, environment, null);
        logErrors(process);
        assertEquals("foo", readLine(process));
    }

    String readLine(Process process) throws IOException {
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }

    void logErrors(final Process process) throws IOException {
        Thread thread = new Thread() {
            public void run() {
                InputStream in = process.getErrorStream();
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(in));
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        System.err.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void testHeavyLoad() {
        int i;
        for (i = 0; i < 100; i++)
            stuff();
    }

    private static void stuff() {
        Runtime rt = Runtime.getRuntime();
        try {
            Process proc = rt.exec("ls");
            proc.waitFor();
            proc = null;
        } catch (Exception ex) {
            System.err.println("Failure: " + ex);
            throw new RuntimeException(ex);
        }
        rt.gc();
        rt = null;
    }

    InputStream in;

    public void testCloseNonStandardFds()
            throws IOException, InterruptedException {
        String[] commands = { "ls", "/proc/self/fd" };

        Process process = Runtime.getRuntime().exec(commands, null, null);
        int before = countLines(process);

        // Open a new fd.
        this.in = new FileInputStream("/proc/version");

        try {
            process = Runtime.getRuntime().exec(commands, null, null);
            int after = countLines(process);

            // Assert that the new fd wasn't open in the second run.
            assertEquals(before, after);
        } finally {
            this.in = null;
        }
    }

    /**
     * Counts lines of input from the given process. Equivalent to "wc -l".
     */
    private int countLines(Process process) throws IOException {
        logErrors(process);
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        return count;
    }

    public void testInvalidCommand()
            throws IOException, InterruptedException {
        try {
            String[] commands = { "doesnotexist" };
            Runtime.getRuntime().exec(commands, null, null);
        } catch (IOException e) { /* expected */ }
    }
}
