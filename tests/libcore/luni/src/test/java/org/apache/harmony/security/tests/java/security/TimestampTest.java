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
 * @author Alexander V. Astapchuk
 * @version $Revision$
 */

package org.apache.harmony.security.tests.java.security;

import java.security.Timestamp;
import java.security.cert.CertPath;
import java.util.Date;

import org.apache.harmony.security.tests.support.cert.MyCertPath;

import junit.framework.TestCase;

/**
 * Tests for <code>Timestamp</code> class fields and methods
 *
 */

public class TimestampTest extends TestCase {
    private Date now = new Date();

    private static final byte[] encoding = { 1, 2, 3 };

    private CertPath cpath = new MyCertPath(encoding);

    public void testTimestamp() {
        try {
            new Timestamp(null, cpath);
            fail("null was accepted");
        } catch (NullPointerException ex) { /* ok */
        }

        try {
            new Timestamp(now, null);
            fail("null was accepted");
            return;
        } catch (NullPointerException ex) { /* ok */
        }

        Timestamp timestamp = new Timestamp(now, cpath);
        assertEquals("not expected value", now, timestamp.getTimestamp());
        assertEquals("not expected cert path", cpath, timestamp.getSignerCertPath());
    }

    /*
     * Class under test for boolean equals(Object)
     */
    public void testEqualsObject() {
        Timestamp one = new Timestamp(now, cpath);
        Timestamp two = new Timestamp(now, cpath);

        assertTrue(one.equals(one));
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
        assertFalse(one.equals(null));
        assertFalse(one.equals(new Object()));

        Timestamp two1 = new Timestamp(new Date(9999), cpath);
        assertFalse(one.equals(two1));
        assertTrue(two1.equals(two1));
    }

    public void testGetSignerCertPath() {
        assertSame(new Timestamp(now, cpath).getSignerCertPath(), cpath);
    }

    public void testGetTimestamp() {
    	Timestamp t = new Timestamp(now, cpath);
        assertEquals(now, t.getTimestamp());
        assertNotSame(now, t.getTimestamp());
    }

    /*
     * Class under test for String toString()
     */
    public void testToString() {
        try {
            String tt = new Timestamp(now, cpath).toString();
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    /*
     * Class under test for String hashCode()
     */
    public void testHashCode() {
        Timestamp one = new Timestamp(now, cpath);
        Timestamp two = new Timestamp(now, cpath);
        Timestamp three = new Timestamp(now, new MyCertPath(new byte[] { 10,
                20, 30 }));
        Timestamp four = null;

        assertTrue(one.hashCode() == two.hashCode());
        assertTrue(one.hashCode() != three.hashCode());
        assertTrue(two.hashCode() != three.hashCode());

        try {
            four.hashCode();
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }

    }
}
