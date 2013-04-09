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

import javax.security.auth.x500.X500Principal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.apache.harmony.security.tests.support.cert.TestUtils;

/**
 * Tests for <code>X500Principal</code> class constructors and methods.
 *
 */
public class X500PrincipalTest extends TestCase {

    /**
     * javax.security.auth.x500.X500Principal#X500Principal(String name)
     */
    public void test_X500Principal_01() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";

        try {
            X500Principal xpr = new X500Principal(name);
            assertNotNull("Null object returned", xpr);
            String resName = xpr.getName();
            assertEquals(name, resName);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            X500Principal xpr = new X500Principal((String)null);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of NullPointerException");
        }

        try {
            X500Principal xpr = new X500Principal("X500PrincipalName");
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#X500Principal(InputStream is)
     */
    public void test_X500Principal_02() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";
        byte[] ba = getByteArray(TestUtils.getX509Certificate_v1());
        ByteArrayInputStream is = new ByteArrayInputStream(ba);
        InputStream isNull = null;

        try {
            X500Principal xpr = new X500Principal(is);
            assertNotNull("Null object returned", xpr);
            byte[] resArray = xpr.getEncoded();
            assertEquals(ba.length, resArray.length);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            X500Principal xpr = new X500Principal(isNull);
            fail("NullPointerException wasn't thrown");
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of NullPointerException");
        }

        is = new ByteArrayInputStream(name.getBytes());
        try {
            X500Principal xpr = new X500Principal(is);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#X500Principal(byte[] name)
     */
    public void test_X500Principal_03() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";
        byte[] ba = getByteArray(TestUtils.getX509Certificate_v1());
        byte[] baNull = null;

        try {
            X500Principal xpr = new X500Principal(ba);
            assertNotNull("Null object returned", xpr);
            byte[] resArray = xpr.getEncoded();
            assertEquals(ba.length, resArray.length);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            X500Principal xpr = new X500Principal(baNull);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }

        ba = name.getBytes();
        try {
            X500Principal xpr = new X500Principal(ba);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException npe) {
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalArgumentException");
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#getName()
     */
    public void test_getName() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";
        X500Principal xpr = new X500Principal(name);
        try {
            String resName = xpr.getName();
            assertEquals(name, resName);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#getName(String format)
     */
    public void test_getName_Format() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";
        String expectedName = "cn=duke,ou=javasoft,o=sun microsystems,c=us";
        X500Principal xpr = new X500Principal(name);
        try {
            String resName = xpr.getName(X500Principal.CANONICAL);
            assertEquals(expectedName, resName);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        expectedName = "CN=Duke, OU=JavaSoft, O=Sun Microsystems, C=US";
        try {
            String resName = xpr.getName(X500Principal.RFC1779);
            assertEquals(expectedName, resName);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            String resName = xpr.getName(X500Principal.RFC2253);
            assertEquals(name, resName);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }

        try {
            String resName = xpr.getName(null);
            fail("IllegalArgumentException  wasn't thrown");
        } catch (IllegalArgumentException  iae) {
        }
        try {
            String resName = xpr.getName("RFC2254");
            fail("IllegalArgumentException  wasn't thrown");
        } catch (IllegalArgumentException  iae) {
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#hashCode()
     */
    public void test_hashCode() {
        String name = "CN=Duke,OU=JavaSoft,O=Sun Microsystems,C=US";
        X500Principal xpr = new X500Principal(name);
        try {
            int res = xpr.hashCode();
            assertNotNull(res);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#toString()
     */
    public void test_toString() {
        String name = "CN=Duke, OU=JavaSoft, O=Sun Microsystems, C=US";
        X500Principal xpr = new X500Principal(name);
        try {
            String res = xpr.toString();
            assertNotNull(res);
            assertEquals(name, res);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#getEncoded()
     */
    public void test_getEncoded() {
        byte[] ba = getByteArray(TestUtils.getX509Certificate_v1());
        X500Principal xpr = new X500Principal(ba);
        try {
            byte[] res = xpr.getEncoded();
            assertNotNull(res);
            assertEquals(ba.length, res.length);
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    /**
     * javax.security.auth.x500.X500Principal#equals(Object o)
     */
    public void test_equals() {
        String name1 = "CN=Duke, OU=JavaSoft, O=Sun Microsystems, C=US";
        String name2 = "cn=duke,ou=javasoft,o=sun microsystems,c=us";
        String name3 = "CN=Alex Astapchuk, OU=SSG, O=Intel ZAO, C=RU";
        X500Principal xpr1 = new X500Principal(name1);
        X500Principal xpr2 = new X500Principal(name2);
        X500Principal xpr3 = new X500Principal(name3);
        try {
            assertTrue("False returned", xpr1.equals(xpr2));
            assertFalse("True returned", xpr1.equals(xpr3));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    private byte[] getByteArray(byte[] array) {
        byte[] x = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(array);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(is);
            X500Principal xx = cert.getIssuerX500Principal();
            x = xx.getEncoded();
        } catch (Exception e) {
            return null;
        }
        return x;
    }
}

