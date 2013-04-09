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

/**
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;

import java.security.UnrecoverableEntryException;

import junit.framework.TestCase;

/**
 * Tests for <code>UnrecoverableEntryException</code> class
 *
 */
public class UnrecoverableEntryExceptionTest extends TestCase {

    static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    static String errNotExc = "Not UnrecoverableEntryException object";

    /*
     * Class under test for void UnrecoverableEntryException()
     */
    public void testUnrecoverableEntryException() {
        UnrecoverableEntryException tE = new UnrecoverableEntryException();
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /*
     * Class under test for void UnrecoverableEntryException(String)
     */
    public void testUnrecoverableEntryExceptionString() {
        UnrecoverableEntryException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new UnrecoverableEntryException(msgs[i]);
            assertEquals("getMessage() must return: ".concat(msgs[i]), tE
                    .getMessage(), msgs[i]);
            assertNull("getCause() must return null", tE.getCause());
        }

        try {
            tE = new UnrecoverableEntryException(null);
        } catch (Exception e) {
            fail("Exception " + e + " was thrown for NULL parameter");
        }
    }
}
