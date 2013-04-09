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

import dalvik.annotation.AndroidOnly;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tests.support.Support_Exec;
import static tests.support.Support_Exec.javaProcessBuilder;

public class Process2Test extends junit.framework.TestCase {
    /**
     * java.lang.Process#getInputStream(),
     *        java.lang.Process#getErrorStream()
     *        java.lang.Process#getOutputStream()
     * Tests if these methods return buffered streams.
     */
    @AndroidOnly("dalvikvm specific")
    public void test_streams()
            throws IOException, InterruptedException {
        Process p = javaProcessBuilder().start();
        assertNotNull(p.getInputStream());
        assertNotNull(p.getErrorStream());
        assertNotNull(p.getOutputStream());
    }

    public void test_getErrorStream() {
        String[] commands = {"ls"};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(commands, null, null);
            InputStream is = process.getErrorStream();
            StringBuffer msg = new StringBuffer("");
            while (true) {
                int c = is.read();
                if (c == -1)
                    break;
                msg.append((char) c);
            }
            assertEquals("", msg.toString());
        } catch (IOException e) {
            fail("IOException was thrown.");
        } finally {
            process.destroy();
        }

        String[] unknownCommands = {"mkdir", "-u", "test"};
        Process erProcess = null;
        try {
            erProcess = Runtime.getRuntime().exec(unknownCommands, null, null);
            InputStream is = erProcess.getErrorStream();
            StringBuffer msg = new StringBuffer("");
            while (true) {
                int c = is.read();
                if (c == -1)
                    break;
                msg.append((char) c);
            }
            assertTrue("Error stream should not be empty",
                                                !"".equals(msg.toString()));
        } catch (IOException e) {
            fail("IOException was thrown.");
        } finally {
            erProcess.destroy();
        }
    }
}
