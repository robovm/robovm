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

package tests.api.javax.security.auth;

import junit.framework.TestCase;

import javax.security.auth.callback.PasswordCallback;

/**
 * Tests for <code>PasswordCallback</code> class constructors and methods.
 *
 */
public class PasswordCallbackTest extends TestCase {

    /**
     * javax.security.auth.callback.PasswordCallback#PasswordCallback(String prompt, boolean echoOn)
     * javax.security.auth.callback.PasswordCallback#getPrompt()
     * javax.security.auth.callback.PasswordCallback#isEchoOn()
     */
    public void test_PasswordCallback() {
        String prompt = "promptTest";

        try {
            PasswordCallback pc = new PasswordCallback(prompt, true);
            assertNotNull("Null object returned", pc);
            assertEquals(prompt, pc.getPrompt());
            assertEquals(true, pc.isEchoOn());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            PasswordCallback pc = new PasswordCallback(prompt, false);
            assertNotNull("Null object returned", pc);
            assertEquals(prompt, pc.getPrompt());
            assertEquals(false, pc.isEchoOn());
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            PasswordCallback pc = new PasswordCallback(null, true);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        }

        try {
            PasswordCallback pc = new PasswordCallback("", true);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        }
    }

    /**
     * javax.security.auth.callback.PasswordCallback#getPassword()
     * javax.security.auth.callback.PasswordCallback#setPassword(char[] password)
     * javax.security.auth.callback.PasswordCallback#clearPassword()
     */
    public void test_Password() {
        String prompt = "promptTest";
        char[] psw1 = "testPassword".toCharArray();
        char[] psw2 = "newPassword".toCharArray();
        PasswordCallback pc = new PasswordCallback(prompt, true);

        try {
            assertNull(pc.getPassword());
            pc.setPassword(psw1);
            assertEquals(psw1.length, pc.getPassword().length);
            pc.setPassword(null);
            assertNull(pc.getPassword());
            pc.setPassword(psw2);
            char[] res = pc.getPassword();
            assertEquals(psw2.length, res.length);
            for (int i = 0; i < res.length; i++) {
                assertEquals("Incorrect password was returned", psw2[i], res[i]);
            }
            pc.clearPassword();
            res = pc.getPassword();
            if (res.equals(psw2)) {
                fail("Incorrect password was returned after clear");
            }
            pc.setPassword(psw1);
            res = pc.getPassword();
            assertEquals(psw1.length, res.length);
            for (int i = 0; i < res.length; i++) {
                assertEquals("Incorrect result", psw1[i], res[i]);
            }
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
}
