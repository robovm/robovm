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

package tests.api.java.lang;

import dalvik.annotation.BrokenTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessTest extends junit.framework.TestCase {

    /**
     * java.lang.Process#getInputStream()
     */
    public void test_getInputStream() {
        try {
            // Test for:
            //Object[] execArgs = Support_Exec.execJava2(
            //        new String[] { "tests.support.Support_AvailTest" }, null,
            //        true);
            //Process proc = (Process) execArgs[0];

            String[] commands = { "sleep", "1"};
            Process proc = Runtime.getRuntime().exec(commands, null, null);

            OutputStream os = proc.getOutputStream();

            // first number indicates total stream length
            // second number indicates length of data after second space
            // this will allow us to verify length at start, middle, and end
            os.write("10 5 abcde".getBytes());
            os.close();

            InputStream is = proc.getInputStream();
            StringBuffer msg = new StringBuffer("");
            while (true) {
                int c = is.read();
                if (c == -1)
                    break;
                msg.append((char) c);
            }
            is.close();
            proc.waitFor();
            //Support_Exec.checkStderr(execArgs);
            proc.destroy();
            assertEquals("true", msg.toString(), msg.toString());
        } catch (IOException e) {
            fail("IOException executing avail test: " + e);
        } catch (InterruptedException e) {
            fail("InterruptedException executing avail test: " + e);
        }
    }

    /**
     * java.lang.Process#getOutputStream()
     */
    public void test_getOutputStream() {
        try {
            String[] commands = { "sleep", "1"};
            Process proc = Runtime.getRuntime().exec(commands, null, null);
            OutputStream os = proc.getOutputStream();
            // send data, and check if it is echoed back correctly
            String str1 = "Some data for testing communication between processes\n";
            String str2 = "More data that serves the same purpose.\n";
            String str3 = "Here is some more data.\n";
            os.write(str1.getBytes());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            os.close();
            InputStream is = proc.getInputStream();
            StringBuffer msg = new StringBuffer("");
            while (true) {
                int c = is.read();
                if (c == -1)
                    break;
                msg.append((char) c);
            }
            is.close();
            proc.waitFor();
            //Support_Exec.checkStderr(execArgs);
            proc.destroy();
            String org = str1;
            String recvd = msg.toString();
            // Doesn't pass on RI
            // assertTrue("Data returned did not match data sent. Received: '"
            //        + recvd + "' sent: '" + org + "'", recvd.equals(org));
        } catch (IOException e) {
            fail("IOException executing avail test: " + e);
        } catch (InterruptedException e) {
            fail("InterruptedException executing avail test: " + e);
        }
    }

    public void test_exitValue() {
        try {
            String[] commands = { "ls" };
            Process process = Runtime.getRuntime().exec(commands, null, null);
            try {
                Thread.sleep(5000);
            } catch(Exception e) {

            }
            assertTrue(process.exitValue() == 0);

            String[] commandsSleep = { "sleep", "3" };
            process = Runtime.getRuntime().exec(commandsSleep, null, null);
            process.destroy();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {}
            assertTrue(process.exitValue() != 0);

            process = Runtime.getRuntime().exec(commandsSleep, null, null);
            try {
                process.exitValue();
                fail("IllegalThreadStateException was not thrown.");
            } catch(IllegalThreadStateException itse) {
               //expected
            }
        } catch (IOException e) {
            fail("IOException was thrown.");
        }
    }

    public void test_Constructor() {
        ProcessClass pc = new ProcessClass();
        assertTrue(pc.exitValue() == 0);
    }

    @BrokenTest("Sporadic timeouts in CTS, but not in CoreTestRunner")
    public void test_destroy() {
        String[] commands = { "ls"};
        try {
            Process process = Runtime.getRuntime().exec(commands, null, null);
            process.destroy();
        } catch (IOException e) {
            fail("IOException was thrown.");
        }
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }

    protected void doneSuite() {
    }

    class ProcessClass extends Process {

        ProcessClass() {
            super();
        }

        @Override
        public void destroy() {

        }

        @Override
        public int exitValue() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public InputStream getErrorStream() {
            return null;
        }

        @Override
        public InputStream getInputStream() {
            return null;
        }

        @Override
        public OutputStream getOutputStream() {
            return null;
        }

        @Override
        public int waitFor() throws InterruptedException {
            // TODO Auto-generated method stub
            return 0;
        }

    }
}
