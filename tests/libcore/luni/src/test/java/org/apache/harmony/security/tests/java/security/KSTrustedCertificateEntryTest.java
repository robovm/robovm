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

import java.security.KeyStore;
import java.security.cert.Certificate;

import org.apache.harmony.security.tests.support.cert.MyCertificate;


import junit.framework.TestCase;

/**
 * Tests for <code>KeyStore.TrustedCertificateEntry</code> class constructor and methods
 *
 */

public class KSTrustedCertificateEntryTest extends TestCase {

    /**
     * Test for <codfe>KeyStore.TrustedCertificateEntry(Certificate trustCert)</code>
     * constructor
     * Assertion: throws NullPointerException when trustCert is null
     */
    public void testTrustedCertificateEntry() {
        Certificate cert = null;
        try {
            new KeyStore.TrustedCertificateEntry(cert);
            fail("NullPointerException must be thrown when trustCert is null");
        } catch (NullPointerException e) {
        }

        cert = new MyCertificate("TEST", new byte[10]);
        try {
            KeyStore.TrustedCertificateEntry ksTCE = new KeyStore.TrustedCertificateEntry(cert);
            assertNotNull(ksTCE);
            assertTrue(ksTCE instanceof KeyStore.TrustedCertificateEntry);
        } catch (Exception e) {
            fail("Unexpected exception was thrown when trustCert is not null");
        }
    }

    /**
     * Test for <codfe>getTrustedCertificate()</code> method
     * Assertion: returns trusted Certificate from goven entry
     */
    public void testGetTrustedCertificate() {
        Certificate cert = new MyCertificate("TEST", new byte[10]);
        KeyStore.TrustedCertificateEntry ksTCE =
                new KeyStore.TrustedCertificateEntry(cert);
        assertEquals("Incorrect certificate", cert, ksTCE.getTrustedCertificate());
    }

    /**
     * Test for <codfe>toString()</code> method
     * Assertion: returns non null string
     */
    public void testToString() {
        Certificate cert = new MyCertificate("TEST", new byte[10]);
        KeyStore.TrustedCertificateEntry ksTCE =
                new KeyStore.TrustedCertificateEntry(cert);
        assertNotNull("toString() returns null string", ksTCE.toString());
    }
}
