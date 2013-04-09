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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package tests.security.cert;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

import org.apache.harmony.security.tests.support.cert.TestUtils;

/**
 * Tests for <code>PKIXBuilderParameters</code> fields and methods
 *
 */
public class PKIXBuilderParametersTest extends TestCase {
    String certificate = "-----BEGIN CERTIFICATE-----\n"
            + "MIICZTCCAdICBQL3AAC2MA0GCSqGSIb3DQEBAgUAMF8xCzAJBgNVBAYTAlVTMSAw\n"
            + "HgYDVQQKExdSU0EgRGF0YSBTZWN1cml0eSwgSW5jLjEuMCwGA1UECxMlU2VjdXJl\n"
            + "IFNlcnZlciBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAeFw05NzAyMjAwMDAwMDBa\n"
            + "Fw05ODAyMjAyMzU5NTlaMIGWMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZv\n"
            + "cm5pYTESMBAGA1UEBxMJUGFsbyBBbHRvMR8wHQYDVQQKExZTdW4gTWljcm9zeXN0\n"
            + "ZW1zLCBJbmMuMSEwHwYDVQQLExhUZXN0IGFuZCBFdmFsdWF0aW9uIE9ubHkxGjAY\n"
            + "BgNVBAMTEWFyZ29uLmVuZy5zdW4uY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCB\n"
            + "iQKBgQCofmdY+PiUWN01FOzEewf+GaG+lFf132UpzATmYJkA4AEA/juW7jSi+LJk\n"
            + "wJKi5GO4RyZoyimAL/5yIWDV6l1KlvxyKslr0REhMBaD/3Z3EsLTTEf5gVrQS6sT\n"
            + "WMoSZAyzB39kFfsB6oUXNtV8+UKKxSxKbxvhQn267PeCz5VX2QIDAQABMA0GCSqG\n"
            + "SIb3DQEBAgUAA34AXl3at6luiV/7I9MN5CXYoPJYI8Bcdc1hBagJvTMcmlqL2uOZ\n"
            + "H9T5hNMEL9Tk6aI7yZPXcw/xI2K6pOR/FrMp0UwJmdxX7ljV6ZtUZf7pY492UqwC\n"
            + "1777XQ9UEZyrKJvF5ntleeO0ayBqLGVKCWzWZX9YsXCpv47FNLZbupE=\n"
            + "-----END CERTIFICATE-----\n";

    String certificate2 = "-----BEGIN CERTIFICATE-----\n"
            + "MIICZzCCAdCgAwIBAgIBGzANBgkqhkiG9w0BAQUFADBhMQswCQYDVQQGEwJVUzEY\n"
            + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
            + "A1BLSTEcMBoGA1UEAxMTRG9EIFBLSSBNZWQgUm9vdCBDQTAeFw05ODA4MDMyMjAy\n"
            + "MjlaFw0wODA4MDQyMjAyMjlaMGExCzAJBgNVBAYTAlVTMRgwFgYDVQQKEw9VLlMu\n"
            + "IEdvdmVybm1lbnQxDDAKBgNVBAsTA0RvRDEMMAoGA1UECxMDUEtJMRwwGgYDVQQD\n"
            + "ExNEb0QgUEtJIE1lZCBSb290IENBMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKB\n"
            + "gQDbrM/J9FrJSX+zxFUbsI9Vw5QbguVBIa95rwW/0M8+sM0r5gd+DY6iubm6wnXk\n"
            + "CSvbfQlFEDSKr4WYeeGp+d9WlDnQdtDFLdA45tCi5SHjnW+hGAmZnld0rz6wQekF\n"
            + "5xQaa5A6wjhMlLOjbh27zyscrorMJ1O5FBOWnEHcRv6xqQIDAQABoy8wLTAdBgNV\n"
            + "HQ4EFgQUVrmYR6m9701cHQ3r5kXyG7zsCN0wDAYDVR0TBAUwAwEB/zANBgkqhkiG\n"
            + "9w0BAQUFAAOBgQDVX1Y0YqC7vekeZjVxtyuC8Mnxbrz6D109AX07LEIRzNYzwZ0w\n"
            + "MTImSp9sEzWW+3FueBIU7AxGys2O7X0qmN3zgszPfSiocBuQuXIYQctJhKjF5KVc\n"
            + "VGQRYYlt+myhl2vy6yPzEVCjiKwMEb1Spu0irCf+lFW2hsdjvmSQMtZvOw==\n"
            + "-----END CERTIFICATE-----\n";

    /**
     * Test #1 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXBuilderParametersSetCertSelector01()
        throws InvalidAlgorithmParameterException {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": not performed (could not create test TrustAnchor set)");
        }
        // both parameters are valid and non-null
        PKIXParameters p =
            new PKIXBuilderParameters(taSet, new X509CertSelector());
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNotNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #2 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXBuilderParametersSetCertSelector02()
        throws InvalidAlgorithmParameterException {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": not performed (could not create test TrustAnchor set)");
        }
        // both parameters are valid but CertSelector is null
        PKIXParameters p = new PKIXBuilderParameters(taSet, null);
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #3 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: ... the <code>Set</code> is copied to protect against
     * subsequent modifications
     * @throws InvalidAlgorithmParameterException
     */
    @SuppressWarnings("unchecked")
    public final void testPKIXBuilderParametersSetCertSelector03()
        throws InvalidAlgorithmParameterException {
        Set<TrustAnchor> taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": not performed (could not create test TrustAnchor set)");
        }
        HashSet<TrustAnchor> originalSet = (HashSet<TrustAnchor>) taSet;
        HashSet<TrustAnchor> originalSetCopy = (HashSet<TrustAnchor>) originalSet
                .clone();
        // create test object using originalSet
        PKIXBuilderParameters pp =
            new PKIXBuilderParameters(originalSetCopy, null);
        // modify originalSet
        originalSetCopy.clear();
        // check that test object's internal state
        // has not been affected by the above modification
        Set returnedSet = pp.getTrustAnchors();
        assertEquals(originalSet, returnedSet);
    }

    /**
     * Test #4 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the specified <code>Set</code> is null
     */
    public final void testPKIXBuilderParametersSetCertSelector04() throws Exception {
        try {
            // pass null
            new PKIXBuilderParameters((Set<TrustAnchor>) null, null);
            fail("NPE expected");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test #5 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the specified <code>Set</code> is empty
     * (<code>trustAnchors.isEmpty() == true</code>)
     */
    public final void testPKIXBuilderParametersSetCertSelector05() {
        try {
            // use empty set
            new PKIXBuilderParameters(new HashSet<TrustAnchor>(), null);
            fail("InvalidAlgorithmParameterException expected");
        } catch (InvalidAlgorithmParameterException e) {
        }
    }

    /**
     * Test #1 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>NullPointerException</code> - if the
     * <code>keystore</code> is <code>null</code>
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector01()
            throws Exception {
        try {
            new PKIXBuilderParameters((KeyStore) null, new X509CertSelector());
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * Test #2 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>KeyStoreException</code> - if the
     * <code>keystore</code> has not been initialized
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector02()
            throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            new PKIXBuilderParameters(keyTest, null);
            fail("KeyStoreException expected");
        } catch (KeyStoreException e) {
            // expected
        }
    }

    /**
     * Test #3 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> - if the
     * <code>keystore</code> does not contain at least one trusted certificate
     * entry
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector03()
            throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        keyTest.load(null, null);
        try {
            new PKIXBuilderParameters(keyTest, new X509CertSelector());
            fail("InvalidAlgorithmParameterException expected");
        } catch (InvalidAlgorithmParameterException e) {
            // expected
        }
    }

    /**
     * Test #4 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the <code>keystore</code> is <code>null</code>
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector04()
            throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        keyTest.load(null, null);

        ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
                .getBytes());

        ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
                .getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[2];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        cert[1] = (X509Certificate) cf.generateCertificate(certArray2);

        keyTest.setCertificateEntry("alias1", cert[0]);
        keyTest.setCertificateEntry("alias2", cert[0]);
        keyTest.setCertificateEntry("alias3", cert[1]);

        try {
            PKIXBuilderParameters p = new PKIXBuilderParameters(keyTest,
                    new X509CertSelector());
            assertEquals(3, p.getTrustAnchors().size());
            assertEquals(5, p.getMaxPathLength());
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

    /**
     * Test for <code>getMaxPathLength()</code>
     */
    public final void testGetMaxPathLength() throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        keyTest.load(null, null);

        ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
                .getBytes());

        ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
                .getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[2];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        cert[1] = (X509Certificate) cf.generateCertificate(certArray2);

        keyTest.setCertificateEntry("alias1", cert[0]);
        keyTest.setCertificateEntry("alias2", cert[0]);
        keyTest.setCertificateEntry("alias3", cert[1]);

        PKIXBuilderParameters p = new PKIXBuilderParameters(keyTest,
                new X509CertSelector());
        assertEquals(5, p.getMaxPathLength());
        p.setMaxPathLength(10);
        assertEquals(10, p.getMaxPathLength());
    }

    /**
     * Test for <code>setMaxPathLength()</code>
     */
    public final void testSetMaxPathLength() throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        keyTest.load(null, null);

        ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
                .getBytes());

        ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
                .getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[2];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        cert[1] = (X509Certificate) cf.generateCertificate(certArray2);

        keyTest.setCertificateEntry("alias1", cert[0]);
        keyTest.setCertificateEntry("alias2", cert[0]);
        keyTest.setCertificateEntry("alias3", cert[1]);

        PKIXBuilderParameters p = new PKIXBuilderParameters(keyTest,
                new X509CertSelector());

        assertEquals(5, p.getMaxPathLength());
        p.setMaxPathLength(10);
        assertEquals(10, p.getMaxPathLength());
        p.setMaxPathLength(0);
        assertEquals(0, p.getMaxPathLength());
        p.setMaxPathLength(-1);
        assertEquals(-1, p.getMaxPathLength());

        int[] maxPathLength = {-2, -10, Integer.MIN_VALUE};
        for (int i = 0; i < maxPathLength.length; i++) {
            try {
                p.setMaxPathLength(maxPathLength[i]);
                fail("InvalidParameterException expected ");
            } catch (InvalidParameterException e) {
                // expected
            }
        }
    }

    /**
     * Test for <code>toString()</code>
     */
    public final void testToString() throws Exception {
        KeyStore keyTest = KeyStore.getInstance(KeyStore.getDefaultType());
        keyTest.load(null, null);

        ByteArrayInputStream certArray = new ByteArrayInputStream(certificate
                .getBytes());

        ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2
                .getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[2];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        cert[1] = (X509Certificate) cf.generateCertificate(certArray2);

        keyTest.setCertificateEntry("alias1", cert[0]);
        keyTest.setCertificateEntry("alias2", cert[0]);
        keyTest.setCertificateEntry("alias3", cert[1]);

        PKIXBuilderParameters p = new PKIXBuilderParameters(keyTest,
                new X509CertSelector());
        assertNotNull(p.toString());

    }
}
