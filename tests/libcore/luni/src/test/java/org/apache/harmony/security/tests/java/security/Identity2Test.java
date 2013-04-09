/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import dalvik.annotation.AndroidOnly;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Certificate;
import java.security.Identity;
import java.security.IdentityScope;
import java.security.KeyManagementException;
import java.security.KeyPairGenerator;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.apache.harmony.security.tests.java.security.IdentityScope2Test.IdentityScopeSubclass;

@SuppressWarnings("deprecation")
public class Identity2Test extends junit.framework.TestCase {

    private static PublicKey PUB_KEY;
    private static PublicKey getPubKey() throws Exception {
        if (PUB_KEY == null) {
            PUB_KEY = KeyPairGenerator.getInstance("DSA").genKeyPair().getPublic();
        }
        return PUB_KEY;
    }

    public static class CertificateImpl implements java.security.Certificate {

        X509Certificate cert;

        public CertificateImpl(X509Certificate cert) {
            this.cert = cert;
        }

        public Principal getGuarantor() {
            return cert.getIssuerDN();
        }

        public void encode(OutputStream out) {
        }

        public void decode(InputStream in) {
        }

        public String toString() {
            return "";
        }

        public String toString(boolean b) {
            return "";
        }

        public String getFormat() {
            return cert.getType();
        }

        public Principal getPrincipal() {
            return cert.getSubjectDN();
        }

        public PublicKey getPublicKey() {
            return cert.getPublicKey();
        }
    }

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

    ByteArrayInputStream certArray = new ByteArrayInputStream(certificate.getBytes());

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

    ByteArrayInputStream certArray2 = new ByteArrayInputStream(certificate2.getBytes());


    public static class IdentitySubclass extends Identity {
        private static final long serialVersionUID = 1L;

        public IdentitySubclass() {
            super();
        }

        public IdentitySubclass(String name) {
            super(name);
        }

        public IdentitySubclass(String name, IdentityScope scope)
                throws KeyManagementException {
            super(name, scope);
        }
    }

    /**
     * java.security.Identity#Identity()
     */
    public void test_Constructor() {
        new IdentitySubclass();
    }

    /**
     * java.security.Identity#Identity(java.lang.String)
     */
    public void test_ConstructorLjava_lang_String() {
        String[] str = {"test", "", null};
        IdentitySubclass is;
        for (int i = 0; i < str.length; i++) {
            try {
                is = new IdentitySubclass(str[i]);
                assertNotNull(is);
                assertTrue(is instanceof Identity);
            } catch (Exception e) {
                fail("Unexpected exception for Identity(java.lang.String) with parameter "
                     + str[i]);
            }
        }
    }

    /**
     * java.security.Identity#Identity(java.lang.String,
     *        java.security.IdentityScope)
     */
    public void test_ConstructorLjava_lang_StringLjava_security_IdentityScope() throws Exception {
        String nameNull = null;
        String[] str = {"test", "", "!@#$%^&*()", "identity name"};
        IdentityScopeSubclass iss = new IdentityScopeSubclass("name");
        IdentitySubclass is;

        for (int i = 0; i < str.length; i++) {
            try {
                is = new IdentitySubclass(str[i], new IdentityScopeSubclass());
                assertNotNull(is);
                assertTrue(is instanceof Identity);
            } catch (Exception e) {
                System.out.println(e);
                fail("Unexpected exception for parameter " + str[i]);
            }
        }

        try {
            is = new IdentitySubclass(nameNull, new IdentityScopeSubclass());
        } catch (NullPointerException npe) {
        } catch (Exception e) {
            fail("Incorrect exception " + e + " was thrown");
        }

        try {
            is = new IdentitySubclass("test", iss);
            is = new IdentitySubclass("test", iss);
            fail("KeyManagementException was not thrown");
        } catch (KeyManagementException expected) {
        }
    }

    /**
     * java.security.Identity#getScope()
     */
    public void test_getScope() throws Exception {
        IdentityScope scope = new IdentityScopeSubclass();
        IdentitySubclass sub = new IdentitySubclass("test", scope);
        IdentityScope returnedScope = sub.getScope();
        assertEquals("Wrong Scope returned", scope, returnedScope);
    }

    /**
     * java.security.Identity#getPublicKey()
     */
    public void test_getPublicKey() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test",
                                                    new IdentityScopeSubclass());
        sub.setPublicKey(getPubKey());
        PublicKey returnedPubKey = sub.getPublicKey();
        assertEquals("Wrong PublicKey returned", getPubKey(), returnedPubKey);
    }

    /**
     * java.security.Identity#getName()
     */
    public void test_getName() throws Exception {
        String name = "test";
        IdentitySubclass sub = new IdentitySubclass(name,
                                                    new IdentityScopeSubclass());
        assertEquals("Wrong Name returned", name, sub.getName());
    }

    /**
     * java.security.Identity#getInfo()
     */
    public void test_getInfo() throws Exception {
        String info = "This is the general information.";
        IdentitySubclass sub = new IdentitySubclass("test",
                                                    new IdentityScopeSubclass());
        sub.setInfo(info);
        assertEquals("Wrong Info returned", info, sub.getInfo());
    }

    /**
     * java.security.Identity#certificates()
     */
    public void test_certificates() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test",
                                                    new IdentityScopeSubclass());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[1];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        sub.setPublicKey(cert[0].getPublicKey());
        CertificateImpl certImpl = new CertificateImpl(cert[0]);
        sub.addCertificate(certImpl);
        java.security.Certificate[] certs = sub.certificates();
        assertEquals("Certificate not contained in the identity",
                     certs[0], certImpl);
    }

    /**
     * java.security.Identity#removeCertificate(java.security.Certificate)
     */
    @AndroidOnly("Spec says: Removing unknown certificates throw an exception. "
            + "The RI ignores unknown certificates.")
    public void test_removeCertificateLjava_security_Certificate() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test",
                new IdentityScopeSubclass());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[1];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        sub.setPublicKey(cert[0].getPublicKey());
        CertificateImpl certImpl = new CertificateImpl(cert[0]);
        sub.addCertificate(certImpl);

        try {
            sub.removeCertificate(null);
            fail("Test 1: KeyManagementException expected.");
        } catch (KeyManagementException expected) {
        }
        assertEquals("Test 2: Certificate should not have been removed.",
                1, sub.certificates().length);

        sub.removeCertificate(certImpl);
        assertEquals("Test 3: Certificate has not been removed.",
                0, sub.certificates().length);

        // Removing the same certificate a second time should fail.
        try {
            sub.removeCertificate(certImpl);
            fail("Test 4: KeyManagementException expected.");
        } catch (KeyManagementException expected) {
        }

    }

    /**
     * java.security.Identity#equals(java.lang.Object)
     */
    public void test_equalsLjava_lang_Object() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test",
                                                    new IdentityScopeSubclass());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[1];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        sub.setPublicKey(cert[0].getPublicKey());
        CertificateImpl certImpl = new CertificateImpl(cert[0]);
        sub.addCertificate(certImpl);
        IdentitySubclass sub2 = new IdentitySubclass("test",
                                                     new IdentityScopeSubclass());
        IdentitySubclass sub3 = new IdentitySubclass("identity name",
                                                     new IdentityScopeSubclass());
        assertEquals("the two Identity objects are not equal", sub2, sub);
        assertEquals(sub, sub2);
        assertFalse(sub.equals(sub3));
    }

    /**
     * java.security.Identity#identityEquals(java.security.Identity)
     */
    public void test_identityEqualsLjava_security_Identity() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test", null);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert[] = new X509Certificate[1];
        cert[0] = (X509Certificate) cf.generateCertificate(certArray);
        sub.setPublicKey(cert[0].getPublicKey());
        CertificateImpl certImpl = new CertificateImpl(cert[0]);
        sub.addCertificate(certImpl);
        IdentitySubclass sub2 = new IdentitySubclass("test", null);
        sub2.setPublicKey(cert[0].getPublicKey());
        assertEquals("the two Identity objects are not identity-equal",
                     sub2, sub);
    }

    /**
     * java.security.Identity#toString()
     */
    public void test_toString() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test", null);
        assertNotNull(sub.toString());
        assertTrue("The String returned is not valid", sub.toString().length() > 0);
        // Regression for HARMONY-1566
        assertNotNull(new IdentitySubclass().toString());
    }

    /**
     * java.security.Identity#toString(boolean)
     */
    public void test_toStringZ() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test", null);
        assertNotNull(sub.toString(true));
        assertTrue("The String returned is not valid", sub.toString(true).length() > 0);
    }

    /**
     * java.security.Identity#hashCode()
     */
    public void test_hashCode() throws Exception {
        IdentitySubclass sub = new IdentitySubclass("test", null);
        IdentitySubclass sub2 = new IdentitySubclass("test", null);
        assertEquals("The 2 hash codes are not equal", sub.hashCode(), sub2.hashCode());
    }

    /**
     * java.security.Identity#setInfo(String)
     */
    public void testSetInfo() throws Exception {
        String[] info = {"This is the general information.", "test", "", null};
        IdentitySubclass sub = new IdentitySubclass("test", new IdentityScopeSubclass());

        for (int i = 0; i < info.length; i++) {
            try {
                sub.setInfo(info[i]);
                assertEquals("Wrong Info returned", info[i], sub.getInfo());
            } catch (Exception e) {
                fail("Unexpected exception for parameter " + info[i]);
            }
        }

    }

    /**
     * java.security.Identity#setPublicKey(PublicKey key)
     */
    public void testSetPublicKey() throws Exception{
        IdentitySubclass sub = new IdentitySubclass("test",
                                                    new IdentityScopeSubclass());
        sub.setPublicKey(getPubKey());
        PublicKey returnedPubKey = sub.getPublicKey();
        assertEquals("Wrong PublicKey returned", getPubKey(), returnedPubKey);

        sub.setPublicKey(null);
        assertEquals("Wrong PublicKey returned", null, sub.getPublicKey());
    }

}
