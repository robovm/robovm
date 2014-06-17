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

package tests.security.cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import junit.framework.TestCase;
import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.tests.support.TestKeyPair;
import org.apache.harmony.security.tests.support.cert.MyCRL;
import org.apache.harmony.security.tests.support.cert.TestUtils;
import org.apache.harmony.security.x501.Name;
import org.apache.harmony.security.x509.CertificatePolicies;
import org.apache.harmony.security.x509.GeneralName;
import org.apache.harmony.security.x509.GeneralNames;
import org.apache.harmony.security.x509.NameConstraints;
import org.apache.harmony.security.x509.ORAddress;
import org.apache.harmony.security.x509.OtherName;
import org.apache.harmony.security.x509.PolicyInformation;
import org.apache.harmony.security.x509.PrivateKeyUsagePeriod;

/**
 * X509CertSelectorTest
 */
public class X509CertSelectorTest extends TestCase {

    byte[][] constraintBytes = new byte[][] {
            {
                    48, 34, -96, 15, 48, 13, -127, 8, 56, 50, 50, 46, 78,
                    97, 109, 101, -128, 1, 0, -95, 15, 48, 13, -127, 8, 56,
                    50, 50, 46, 78, 97, 109, 101, -128, 1, 0},
            {
                    48, 42, -96, 19, 48, 17, -127, 12, 114, 102, 99, 64,
                    56, 50, 50, 46, 78, 97, 109, 101, -128, 1, 0, -95, 19,
                    48, 17, -127, 12, 114, 102, 99, 64, 56, 50, 50, 46, 78,
                    97, 109, 101, -128, 1, 0},
            {
                    48, 34, -96, 15, 48, 13, -126, 8, 78, 97, 109, 101, 46,
                    111, 114, 103, -128, 1, 0, -95, 15, 48, 13, -126, 8,
                    78, 97, 109, 101, 46, 111, 114, 103, -128, 1, 0},
            {
                    48, 42, -96, 19, 48, 17, -126, 12, 100, 78, 83, 46, 78,
                    97, 109, 101, 46, 111, 114, 103, -128, 1, 0, -95, 19,
                    48, 17, -126, 12, 100, 78, 83, 46, 78, 97, 109, 101,
                    46, 111, 114, 103, -128, 1, 0},
            {
                    48, 54, -96, 25, 48, 23, -122, 18, 104, 116, 116, 112,
                    58, 47, 47, 82, 101, 115, 111, 117, 114, 99, 101, 46,
                    73, 100, -128, 1, 0, -95, 25, 48, 23, -122, 18, 104,
                    116, 116, 112, 58, 47, 47, 82, 101, 115, 111, 117, 114,
                    99, 101, 46, 73, 100, -128, 1, 0},
            {
                    48, 70, -96, 33, 48, 31, -122, 26, 104, 116, 116, 112,
                    58, 47, 47, 117, 110, 105, 102, 111, 114, 109, 46, 82,
                    101, 115, 111, 117, 114, 99, 101, 46, 73, 100, -128, 1,
                    0, -95, 33, 48, 31, -122, 26, 104, 116, 116, 112, 58,
                    47, 47, 117, 110, 105, 102, 111, 114, 109, 46, 82, 101,
                    115, 111, 117, 114, 99, 101, 46, 73, 100, -128, 1, 0},
            {
                    48, 26, -96, 11, 48, 9, -121, 4, 1, 1, 1, 1, -128, 1,
                    0, -95, 11, 48, 9, -121, 4, 1, 1, 1, 1, -128, 1, 0},
            {
                    48, 50, -96, 23, 48, 21, -121, 16, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, -128, 1, 0, -95, 23, 48, 21,
                    -121, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, -128, 1, 0}};

    /**
     * java.security.cert.X509CertSelector#addSubjectAlternativeName(int, byte[])
     */
    public void test_addSubjectAlternativeNameLintLbyte_array() throws IOException {
        // Regression for HARMONY-2487
        int[] types = { GeneralName.OTHER_NAME,
                        GeneralName.RFC822_NAME,
                        GeneralName.DNS_NAME,
                        GeneralName.X400_ADDR,
                        GeneralName.DIR_NAME,
                        GeneralName.EDIP_NAME,
                        GeneralName.UR_ID,
                        GeneralName.IP_ADDR,
                        GeneralName.REG_ID };
        for (int i = 0; i < types.length; i++) {
            try {
                new X509CertSelector().addSubjectAlternativeName(types[i],
                        (byte[]) null);
                fail("No expected NullPointerException for type: " + types[i]);
            } catch (NullPointerException expected) {
            }
        }
    }

    /**
     * java.security.cert.X509CertSelector#addSubjectAlternativeName(int, String)
     */
    public void test_addSubjectAlternativeNameLintLjava_lang_String() {
        // Regression for HARMONY-727
        int[] types = { GeneralName.OTHER_NAME,
                        // GeneralName.RFC822_NAME,
                        GeneralName.DNS_NAME,
                        GeneralName.X400_ADDR,
                        GeneralName.DIR_NAME,
                        GeneralName.EDIP_NAME,
                        GeneralName.UR_ID,
                        GeneralName.IP_ADDR,
                        GeneralName.REG_ID };
        for (int i = 0; i < types.length; i++) {
            try {
                new X509CertSelector().addSubjectAlternativeName(types[i],
                        "-0xDFRF");
                fail("IOException expected for type: " + types[i]);
            } catch (IOException expected) {
            }
        }
    }

    /**
     * java.security.cert.X509CertSelector#addPathToName(int, byte[])
     */
    public void test_addPathToNameLintLbyte_array() throws IOException {
        // Regression for HARMONY-2487
        int[] types = { GeneralName.OTHER_NAME,
                        GeneralName.RFC822_NAME,
                        GeneralName.DNS_NAME,
                        GeneralName.X400_ADDR,
                        GeneralName.DIR_NAME,
                        GeneralName.EDIP_NAME,
                        GeneralName.UR_ID,
                        GeneralName.IP_ADDR,
                        GeneralName.REG_ID };
        for (int i = 0; i < types.length; i++) {
            try {
                new X509CertSelector().addPathToName(types[i], (byte[]) null);
                fail("No expected NullPointerException for type: " + types[i]);
            } catch (NullPointerException expected) {
            }
        }
    }

    /**
     * java.security.cert.X509CertSelector#addPathToName(int, String)
     */
    public void test_addPathToNameLintLjava_lang_String() {
        // Regression for HARMONY-724
        for (int type = 0; type <= 8; type++) {
            try {
                new X509CertSelector().addPathToName(type, (String) null);
                fail();
            } catch (IOException expected) {
            }
        }


    }

    /**
     * java.security.cert.X509CertSelector#X509CertSelector()
     */
    public void test_X509CertSelector() {
        X509CertSelector selector = new X509CertSelector();
        assertEquals(-1, selector.getBasicConstraints());
        assertTrue(selector.getMatchAllSubjectAltNames());
    }

    /**
     * java.security.cert.X509CertSelector#clone()
     */
    public void test_clone() throws Exception {
        X509CertSelector selector = new X509CertSelector();
        X509CertSelector selector1 = (X509CertSelector) selector.clone();

        assertEquals(selector.getMatchAllSubjectAltNames(), selector1.getMatchAllSubjectAltNames());
        assertEquals(selector.getAuthorityKeyIdentifier(), selector1.getAuthorityKeyIdentifier());
        assertEquals(selector.getBasicConstraints(), selector1.getBasicConstraints());
        assertEquals(selector.getCertificate(), selector1.getCertificate());
        assertEquals(selector.getCertificateValid(), selector1.getCertificateValid());
        assertEquals(selector.getExtendedKeyUsage(), selector1.getExtendedKeyUsage());
        assertEquals(selector.getIssuer(), selector1.getIssuer());
        assertEquals(selector.getIssuerAsBytes(), selector1.getIssuerAsBytes());
        assertEquals(selector.getIssuerAsString(), selector1.getIssuerAsString());
        assertEquals(selector.getKeyUsage(), selector1.getKeyUsage());
        assertEquals(selector.getNameConstraints(), selector1.getNameConstraints());
        assertEquals(selector.getPathToNames(), selector1.getPathToNames());
        assertEquals(selector.getPolicy(), selector1.getPolicy());
        assertEquals(selector.getPrivateKeyValid(), selector1.getPrivateKeyValid());
        assertEquals(selector.getSerialNumber(), selector1.getSerialNumber());
        assertEquals(selector.getSubject(), selector1.getSubject());
        assertEquals(selector.getSubjectAlternativeNames(), selector1.getSubjectAlternativeNames());
        assertEquals(selector.getSubjectAsBytes(), selector1.getSubjectAsBytes());
        assertEquals(selector.getSubjectAsString(), selector1.getSubjectAsString());
        assertEquals(selector.getSubjectKeyIdentifier(), selector1.getSubjectKeyIdentifier());
        assertEquals(selector.getSubjectPublicKey(), selector1.getSubjectPublicKey());
        assertEquals(selector.getSubjectPublicKeyAlgID(), selector1.getSubjectPublicKeyAlgID());

        selector = null;
        try {
            selector.clone();
            fail();
        } catch (NullPointerException expected) {
        }
    }

    /**
     * java.security.cert.X509CertSelector#getAuthorityKeyIdentifier()
     */
    public void test_getAuthorityKeyIdentifier() {
        byte[] akid1 = new byte[] { 4, 5, 1, 2, 3, 4, 5 }; // random value
        byte[] akid2 = new byte[] { 4, 5, 5, 4, 3, 2, 1 }; // random value
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null",
                   selector.getAuthorityKeyIdentifier());
        assertFalse("The returned keyID should be equal to specified",
                   Arrays.equals(akid1, selector.getAuthorityKeyIdentifier()));
        selector.setAuthorityKeyIdentifier(akid1);
        assertTrue("The returned keyID should be equal to specified",
                   Arrays.equals(akid1, selector.getAuthorityKeyIdentifier()));
        assertFalse("The returned keyID should differ",
                    Arrays.equals(akid2, selector.getAuthorityKeyIdentifier()));
    }

    /**
     * java.security.cert.X509CertSelector#getBasicConstraints()
     */
    public void test_getBasicConstraints() {
        X509CertSelector selector = new X509CertSelector();
        int[] validValues = { 2, 1, 0, 1, 2, 3, 10, 20 };
        for (int i = 0; i < validValues.length; i++) {
            selector.setBasicConstraints(validValues[i]);
            assertEquals(validValues[i], selector.getBasicConstraints());
        }
    }

    /**
     * java.security.cert.X509CertSelector#getCertificate()
     */
    public void test_getCertificate() throws Exception {
        X509CertSelector selector = new X509CertSelector();
        CertificateFactory certFact = CertificateFactory.getInstance("X509");
        X509Certificate cert1 = (X509Certificate)
                certFact.generateCertificate(new ByteArrayInputStream(
                        TestUtils.getX509Certificate_v3()));

        X509Certificate cert2 = (X509Certificate)
                certFact.generateCertificate(new ByteArrayInputStream(
                        TestUtils.getX509Certificate_v1()));

        selector.setCertificate(cert1);
        assertEquals(cert1, selector.getCertificate());

        selector.setCertificate(cert2);
        assertEquals(cert2, selector.getCertificate());

        selector.setCertificate(null);
        assertNull(selector.getCertificate());
    }

    /**
     * java.security.cert.X509CertSelector#getCertificateValid()
     */
    public void test_getCertificateValid() {
        Date date1 = new Date(100);
        Date date2 = new Date(200);
        Date date3 = Calendar.getInstance().getTime();
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null",
                   selector.getCertificateValid());
        selector.setCertificateValid(date1);
        assertTrue("The returned date should be equal to specified",
                   date1.equals(selector.getCertificateValid()));
        selector.getCertificateValid().setTime(200);
        assertTrue("The returned date should be equal to specified",
                   date1.equals(selector.getCertificateValid()));
        assertFalse("The returned date should differ",
                    date2.equals(selector.getCertificateValid()));
        selector.setCertificateValid(date3);
        assertTrue("The returned date should be equal to specified",
                   date3.equals(selector.getCertificateValid()));
        selector.setCertificateValid(null);
        assertNull(selector.getCertificateValid());
    }

    /**
     * java.security.cert.X509CertSelector#getExtendedKeyUsage()
     */
    public void test_getExtendedKeyUsage() throws Exception {
        HashSet<String> ku = new HashSet<String>(Arrays.asList(new String[] {
            "1.3.6.1.5.5.7.3.1",
            "1.3.6.1.5.5.7.3.2",
            "1.3.6.1.5.5.7.3.3",
            "1.3.6.1.5.5.7.3.4",
            "1.3.6.1.5.5.7.3.8",
            "1.3.6.1.5.5.7.3.9",
            "1.3.6.1.5.5.7.3.5",
            "1.3.6.1.5.5.7.3.6",
            "1.3.6.1.5.5.7.3.7"
        }));
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getExtendedKeyUsage());
        selector.setExtendedKeyUsage(ku);
        assertTrue("The returned extendedKeyUsage should be equal to specified",
                   ku.equals(selector.getExtendedKeyUsage()));
        try {
            selector.getExtendedKeyUsage().add("KRIBLEGRABLI");
            fail("The returned Set should be immutable.");
        } catch (UnsupportedOperationException expected) {
        }
    }

    /**
     * java.security.cert.X509CertSelector#getIssuer()
     */
    public void test_getIssuer() {
        X500Principal iss1 = new X500Principal("O=First Org.");
        X500Principal iss2 = new X500Principal("O=Second Org.");
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getIssuer());
        selector.setIssuer(iss1);
        assertEquals("The returned issuer should be equal to specified",
                     iss1, selector.getIssuer());
        assertFalse("The returned issuer should differ",
                    iss2.equals(selector.getIssuer()));
    }

    /**
     * java.security.cert.X509CertSelector#getIssuerAsBytes()
     */
    public void test_getIssuerAsBytes() throws Exception {
        byte[] name1 = new byte[]
        // manually obtained DER encoding of "O=First Org." issuer name;
        { 48, 21, 49, 19, 48, 17, 6, 3, 85, 4, 10, 19, 10, 70, 105, 114, 115,
                116, 32, 79, 114, 103, 46 };

        byte[] name2 = new byte[]
        // manually obtained DER encoding of "O=Second Org." issuer name;
        { 48, 22, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 83, 101, 99, 111,
                110, 100, 32, 79, 114, 103, 46 };
        X500Principal iss1 = new X500Principal(name1);
        X500Principal iss2 = new X500Principal(name2);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getIssuerAsBytes());
        selector.setIssuer(iss1);
        assertTrue("The returned issuer should be equal to specified",
                   Arrays.equals(name1, selector.getIssuerAsBytes()));
        assertFalse("The returned issuer should differ", name2.equals(selector.getIssuerAsBytes()));
        selector.setIssuer(iss2);
        assertTrue("The returned issuer should be equal to specified",
                   Arrays.equals(name2, selector.getIssuerAsBytes()));
    }

    /**
     * java.security.cert.X509CertSelector#getIssuerAsString()
     */
    public void test_getIssuerAsString() {
        String name1 = "O=First Org.";
        String name2 = "O=Second Org.";
        X500Principal iss1 = new X500Principal(name1);
        X500Principal iss2 = new X500Principal(name2);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getIssuerAsString());
        selector.setIssuer(iss1);
        assertEquals("The returned issuer should be equal to specified", name1,
                     selector.getIssuerAsString());
        assertFalse("The returned issuer should differ",
                    name2.equals(selector.getIssuerAsString()));
        selector.setIssuer(iss2);
        assertEquals("The returned issuer should be equal to specified", name2,
                     selector.getIssuerAsString());
    }

    /**
     * java.security.cert.X509CertSelector#getKeyUsage()
     */
    public void test_getKeyUsage() {
        boolean[] ku = new boolean[] { true, false, true, false, true, false,
                true, false, true };
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getKeyUsage());
        selector.setKeyUsage(ku);
        assertTrue("The returned date should be equal to specified",
                   Arrays.equals(ku, selector.getKeyUsage()));
        boolean[] result = selector.getKeyUsage();
        result[0] = !result[0];
        assertTrue("The returned keyUsage should be equal to specified",
                   Arrays.equals(ku, selector.getKeyUsage()));
    }

    /**
     * java.security.cert.X509CertSelector#getMatchAllSubjectAltNames()
     */
    public void test_getMatchAllSubjectAltNames() {
        X509CertSelector selector = new X509CertSelector();
        assertTrue("The matchAllNames initially should be true",
                   selector.getMatchAllSubjectAltNames());
        selector.setMatchAllSubjectAltNames(false);
        assertFalse("The value should be false",
                    selector.getMatchAllSubjectAltNames());
    }

    /**
     * java.security.cert.X509CertSelector#getNameConstraints()
     */
    public void test_getNameConstraints() throws IOException {

// Used to generate following byte array
//        GeneralName[] name_constraints = new GeneralName[] {
//                new GeneralName(1, "822.Name"),
//                new GeneralName(1, "rfc@822.Name"),
//                new GeneralName(2, "Name.org"),
//                new GeneralName(2, "dNS.Name.org"),
//
//                new GeneralName(6, "http://Resource.Id"),
//                new GeneralName(6, "http://uniform.Resource.Id"),
//                new GeneralName(7, "1.1.1.1"),
//
//                new GeneralName(new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//                        1, 1, 1, 1, 1 }), };
//
//        constraintBytes = new byte[name_constraints.length][];
//
//        for (int i = 0; i < name_constraints.length; i++) {
//            GeneralSubtree subtree = new GeneralSubtree(name_constraints[i]);
//            GeneralSubtrees subtrees = new GeneralSubtrees();
//            subtrees.addSubtree(subtree);
//            NameConstraints constraints = new NameConstraints(subtrees,
//                    subtrees);
//            constraintBytes[i] = constraints.getEncoded();
//        }
//        System.out.println("XXX"+Arrays.deepToString(constraintBytes)+"XXX");

        X509CertSelector selector = new X509CertSelector();

        for (int i = 0; i < constraintBytes.length; i++) {
            selector.setNameConstraints(constraintBytes[i]);
            assertTrue(Arrays.equals(constraintBytes[i],
                                     selector.getNameConstraints()));
        }
    }

    /**
     * java.security.cert.X509CertSelector#getPathToNames()
     */
    public void test_getPathToNames() throws Exception {
        GeneralName san0 = new GeneralName(new OtherName("1.2.3.4.5",
                                                         new byte[] { 1, 2, 0, 1 }));
        GeneralName san1 = new GeneralName(1, "rfc@822.Name");
        GeneralName san2 = new GeneralName(2, "dNSName");
        GeneralName san3 = new GeneralName(new ORAddress());
        GeneralName san4 = new GeneralName(new Name("O=Organization"));
        GeneralName san6 = new GeneralName(6, "http://uniform.Resource.Id");
        GeneralName san7 = new GeneralName(7, "1.1.1.1");
        GeneralName san8 = new GeneralName(8, "1.2.3.4444.55555");

        GeneralNames sans1 = new GeneralNames();
        sans1.addName(san0);
        sans1.addName(san1);
        sans1.addName(san2);
        sans1.addName(san3);
        sans1.addName(san4);
        sans1.addName(san6);
        sans1.addName(san7);
        sans1.addName(san8);
        GeneralNames sans2 = new GeneralNames();
        sans2.addName(san0);

        TestCert cert1 = new TestCert(sans1);
        TestCert cert2 = new TestCert(sans2);
        X509CertSelector selector = new X509CertSelector();
        selector.setMatchAllSubjectAltNames(true);

        selector.setPathToNames(null);
        assertTrue("Any certificate should match in the case of null "
                   + "subjectAlternativeNames criteria.",
                   selector.match(cert1) && selector.match(cert2));

        Collection<List<?>> sans = sans1.getPairsList();

        selector.setPathToNames(sans);
        selector.getPathToNames();
    }

    /**
     * java.security.cert.X509CertSelector#getPolicy()
     */
    public void test_getPolicy() throws IOException {
        String[] policies1 = new String[] {
            "1.3.6.1.5.5.7.3.1",
            "1.3.6.1.5.5.7.3.2",
            "1.3.6.1.5.5.7.3.3",
            "1.3.6.1.5.5.7.3.4",
            "1.3.6.1.5.5.7.3.8",
            "1.3.6.1.5.5.7.3.9",
            "1.3.6.1.5.5.7.3.5",
            "1.3.6.1.5.5.7.3.6",
            "1.3.6.1.5.5.7.3.7"
        };

        String[] policies2 = new String[] { "1.3.6.7.3.1" };

        HashSet<String> p1 = new HashSet<String>(Arrays.asList(policies1));
        HashSet<String> p2 = new HashSet<String>(Arrays.asList(policies2));

        X509CertSelector selector = new X509CertSelector();

        selector.setPolicy(null);
        assertNull(selector.getPolicy());

        selector.setPolicy(p1);
        assertEquals("The returned date should be equal to specified", p1, selector.getPolicy());

        selector.setPolicy(p2);
        assertEquals("The returned date should be equal to specified", p2, selector.getPolicy());
    }

    /**
     * java.security.cert.X509CertSelector#getPrivateKeyValid()
     */
    public void test_getPrivateKeyValid() {
        Date date1 = new Date(100);
        Date date2 = new Date(200);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getPrivateKeyValid());
        selector.setPrivateKeyValid(date1);
        assertTrue("The returned date should be equal to specified",
                   date1.equals(selector.getPrivateKeyValid()));
        selector.getPrivateKeyValid().setTime(200);
        assertTrue("The returned date should be equal to specified",
                   date1.equals(selector.getPrivateKeyValid()));
        assertFalse("The returned date should differ",
                    date2.equals(selector.getPrivateKeyValid()));
    }

    /**
     * java.security.cert.X509CertSelector#getSerialNumber()
     */
    public void test_getSerialNumber() {
        BigInteger ser1 = new BigInteger("10000");
        BigInteger ser2 = new BigInteger("10001");
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getSerialNumber());
        selector.setSerialNumber(ser1);
        assertEquals("The returned serial number should be equal to specified",
                     ser1, selector.getSerialNumber());
        assertFalse("The returned serial number should differ",
                    ser2.equals(selector.getSerialNumber()));
    }

    /**
     * java.security.cert.X509CertSelector#getSubject()
     */
    public void test_getSubject() {
        X500Principal sub1 = new X500Principal("O=First Org.");
        X500Principal sub2 = new X500Principal("O=Second Org.");
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getSubject());
        selector.setSubject(sub1);
        assertEquals("The returned subject should be equal to specified", sub1,
                     selector.getSubject());
        assertFalse("The returned subject should differ",
                    sub2.equals(selector.getSubject()));
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectAlternativeNames()
     */
    public void test_getSubjectAlternativeNames() throws Exception {
        GeneralName san1 = new GeneralName(1, "rfc@822.Name");
        GeneralName san2 = new GeneralName(2, "dNSName");

        GeneralNames sans = new GeneralNames();
        sans.addName(san1);
        sans.addName(san2);

        TestCert cert_1 = new TestCert(sans);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null",
                   selector.getSubjectAlternativeNames());

        selector.setSubjectAlternativeNames(sans.getPairsList());
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert_1));
        selector.getSubjectAlternativeNames().clear();
        assertTrue("The modification of initialization object "
                   + "should not affect the modification "
                   + "of internal object.",
                   selector.match(cert_1));
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectAsBytes()
     */
    public void test_getSubjectAsBytes() throws Exception {
        byte[] name1 = new byte[]
        // manually obtained DER encoding of "O=First Org." issuer name;
                { 48, 21, 49, 19, 48, 17, 6, 3, 85, 4, 10, 19, 10, 70, 105, 114, 115,
                  116, 32, 79, 114, 103, 46 };
        byte[] name2 = new byte[]
        // manually obtained DER encoding of "O=Second Org." issuer name;
                { 48, 22, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 83, 101, 99, 111,
                  110, 100, 32, 79, 114, 103, 46 };

        X500Principal sub1 = new X500Principal(name1);
        X500Principal sub2 = new X500Principal(name2);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null",
                   selector.getSubjectAsBytes());
        selector.setSubject(sub1);
        assertTrue("The returned issuer should be equal to specified",
                   Arrays.equals(name1, selector.getSubjectAsBytes()));
        assertFalse("The returned issuer should differ",
                    name2.equals(selector.getSubjectAsBytes()));
        selector.setSubject(sub2);
        assertTrue("The returned issuer should be equal to specified",
                   Arrays.equals(name2, selector.getSubjectAsBytes()));
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectAsString()
     */
    public void test_getSubjectAsString() {
        String name1 = "O=First Org.";
        String name2 = "O=Second Org.";
        X500Principal sub1 = new X500Principal(name1);
        X500Principal sub2 = new X500Principal(name2);
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getSubjectAsString());
        selector.setSubject(sub1);
        assertEquals("The returned subject should be equal to specified",
                     name1, selector.getSubjectAsString());
        assertFalse("The returned subject should differ",
                    name2.equals(selector.getSubjectAsString()));
        selector.setSubject(sub2);
        assertEquals("The returned subject should be equal to specified",
                     name2, selector.getSubjectAsString());
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectKeyIdentifier()
     */
    public void test_getSubjectKeyIdentifier() {
        byte[] skid1 = new byte[] { 1, 2, 3, 4, 5 }; // random value
        byte[] skid2 = new byte[] { 4, 5, 5, 4, 3, 2, 1 }; // random value
        X509CertSelector selector = new X509CertSelector();

        assertNull("Selector should return null", selector.getSubjectKeyIdentifier());
        selector.setSubjectKeyIdentifier(skid1);
        assertTrue("The returned keyID should be equal to specified",
                   Arrays.equals(skid1, selector.getSubjectKeyIdentifier()));
        selector.getSubjectKeyIdentifier()[0]++;
        assertTrue("The returned keyID should be equal to specified",
                   Arrays.equals(skid1, selector.getSubjectKeyIdentifier()));
        assertFalse("The returned keyID should differ",
                    Arrays.equals(skid2, selector.getSubjectKeyIdentifier()));
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectPublicKey()
     */
    public void test_getSubjectPublicKey() throws Exception {

        // SubjectPublicKeyInfo ::= SEQUENCE {
        // algorithm AlgorithmIdentifier,
        // subjectPublicKey BIT STRING }
        byte[] enc = { 0x30, 0x0E, // SEQUENCE
                0x30, 0x07, // SEQUENCE
                0x06, 0x02, 0x03, 0x05,// OID
                0x01, 0x01, 0x07, // ANY
                0x03, 0x03, 0x01, 0x01, 0x06, // subjectPublicKey
        };

        X509CertSelector selector = new X509CertSelector();

        selector.setSubjectPublicKey(enc);
        PublicKey key = selector.getSubjectPublicKey();
        assertEquals("0.3.5", key.getAlgorithm());
        assertEquals("X.509", key.getFormat());
        assertTrue(Arrays.equals(enc, key.getEncoded()));
        assertNotNull(key.toString());

        key = new MyPublicKey();

        selector.setSubjectPublicKey(key);
        PublicKey keyActual = selector.getSubjectPublicKey();
        assertEquals(key, keyActual);
        assertEquals(key.getAlgorithm(), keyActual.getAlgorithm());
    }

    /**
     * java.security.cert.X509CertSelector#getSubjectPublicKeyAlgID()
     */
    public void test_getSubjectPublicKeyAlgID() throws Exception {

        X509CertSelector selector = new X509CertSelector();
        String[] validOIDs = { "0.0.20", "1.25.0", "2.0.39", "0.2.10", "1.35.15", "2.17.89" };

        assertNull("Selector should return null", selector.getSubjectPublicKeyAlgID());

        for (int i = 0; i < validOIDs.length; i++) {
            try {
                selector.setSubjectPublicKeyAlgID(validOIDs[i]);
                assertEquals(validOIDs[i], selector.getSubjectPublicKeyAlgID());
            } catch (IOException e) {
                System.out.println("t = " + e.getMessage());
                //fail("Unexpected exception " + e.getMessage());
            }
        }

        String pkaid1 = "1.2.840.113549.1.1.1"; // RSA encryption
        String pkaid2 = "1.2.840.113549.1.1.4"; // MD5 with RSA encryption

        selector.setSubjectPublicKeyAlgID(pkaid1);
        assertTrue("The returned oid should be equal to specified",
                   pkaid1.equals(selector.getSubjectPublicKeyAlgID()));
        assertFalse("The returned oid should differ",
                    pkaid2.equals(selector.getSubjectPublicKeyAlgID()));
    }

    /**
     * java.security.cert.X509CertSelector#match(java.security.cert.Certificate)
     */
    public void test_matchLjava_security_cert_Certificate() throws Exception {
        X509CertSelector selector = new X509CertSelector();
        assertFalse(selector.match(null));

        CertificateFactory certFact = CertificateFactory.getInstance("X509");
        X509Certificate cert1 = (X509Certificate)
                certFact.generateCertificate(new ByteArrayInputStream(
                        TestUtils.getX509Certificate_v3()));

        X509Certificate cert2 = (X509Certificate)
                certFact.generateCertificate(new ByteArrayInputStream(
                        TestUtils.getX509Certificate_v1()));

        selector.setCertificate(cert1);
        assertTrue(selector.match(cert1));
        assertFalse(selector.match(cert2));

        selector.setCertificate(cert2);
        assertFalse(selector.match(cert1));
        assertTrue(selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setAuthorityKeyIdentifier(byte[])
     */
    public void test_setAuthorityKeyIdentifierLB$() throws Exception {
        X509CertSelector selector = new X509CertSelector();

        byte[] akid1 = new byte[] { 1, 2, 3, 4, 5 }; // random value
        byte[] akid2 = new byte[] { 5, 4, 3, 2, 1 }; // random value
        TestCert cert1 = new TestCert(akid1);
        TestCert cert2 = new TestCert(akid2);

        selector.setAuthorityKeyIdentifier(null);
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert1));
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert2));
        assertNull(selector.getAuthorityKeyIdentifier());

        selector.setAuthorityKeyIdentifier(akid1);
        assertTrue("The certificate should not match the selection criteria.",
                selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                selector.match(cert2));
        selector.setAuthorityKeyIdentifier(akid2);
        assertFalse("The certificate should not match the selection criteria.",
                selector.match(cert1));
        assertTrue("The certificate should not match the selection criteria.",
                selector.match(cert2));

        akid2[0]++;
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setBasicConstraints(int)
     */
    public void test_setBasicConstraintsLint() {
        X509CertSelector selector = new X509CertSelector();
        int[] invalidValues = { -3, -4, -5, 1000000000 };
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                selector.setBasicConstraints(-3);
            } catch (IllegalArgumentException expected) {
            }
        }

        int[] validValues = { -2, -1, 0, 1, 2, 3, 10, 20 };
        for (int i = 0; i < validValues.length; i++) {
            selector.setBasicConstraints(validValues[i]);
            assertEquals(validValues[i], selector.getBasicConstraints());
        }
    }

    /**
     * java.security.cert.X509CertSelector#setCertificate(java.security.cert.Certificate)
     */
    public void test_setCertificateLjava_security_cert_X509Certificate()
            throws Exception {

        TestCert cert1 = new TestCert("same certificate");
        TestCert cert2 = new TestCert("other certificate");
        X509CertSelector selector = new X509CertSelector();

        selector.setCertificate(null);
        assertTrue("Any certificates should match in the case of null "
                + "certificateEquals criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setCertificate(cert1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setCertificate(cert2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
        selector.setCertificate(null);
        assertNull(selector.getCertificate());
    }

    /**
     * java.security.cert.X509CertSelector#setCertificateValid(java.util.Date)
     */
    public void test_setCertificateValidLjava_util_Date()
            throws Exception {
        X509CertSelector selector = new X509CertSelector();

        Date date1 = new Date(100);
        Date date2 = new Date(200);
        TestCert cert1 = new TestCert(date1);
        TestCert cert2 = new TestCert(date2);

        selector.setCertificateValid(null);
        assertNull(selector.getCertificateValid());
        selector.setCertificateValid(date1);
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                selector.match(cert2));
        selector.setCertificateValid(date2);
        date2.setTime(300);
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setExtendedKeyUsage(Set<String>)
     */
    public void test_setExtendedKeyUsageLjava_util_Set() throws Exception {
        HashSet<String> ku1 = new HashSet<String>(Arrays.asList(new String[] {
            "1.3.6.1.5.5.7.3.1",
            "1.3.6.1.5.5.7.3.2",
            "1.3.6.1.5.5.7.3.3",
            "1.3.6.1.5.5.7.3.4",
            "1.3.6.1.5.5.7.3.8",
            "1.3.6.1.5.5.7.3.9",
            "1.3.6.1.5.5.7.3.5",
            "1.3.6.1.5.5.7.3.6",
            "1.3.6.1.5.5.7.3.7"
        }));
        HashSet<String> ku2 = new HashSet<String>(Arrays.asList(new String[] {
            "1.3.6.1.5.5.7.3.1",
            "1.3.6.1.5.5.7.3.2",
            "1.3.6.1.5.5.7.3.3",
            "1.3.6.1.5.5.7.3.4",
            "1.3.6.1.5.5.7.3.8",
            "1.3.6.1.5.5.7.3.9",
            "1.3.6.1.5.5.7.3.5",
            "1.3.6.1.5.5.7.3.6"
        }));
        TestCert cert1 = new TestCert(ku1);
        TestCert cert2 = new TestCert(ku2);

        X509CertSelector selector = new X509CertSelector();

        selector.setExtendedKeyUsage(null);
        assertTrue("Any certificate should match in the case of null "
                   + "extendedKeyUsage criteria.",
                   selector.match(cert1)&& selector.match(cert2));
        selector.setExtendedKeyUsage(ku1);
        assertEquals(ku1, selector.getExtendedKeyUsage());

        selector.setExtendedKeyUsage(ku2);
        assertEquals(ku2, selector.getExtendedKeyUsage());
    }

    /**
     * java.security.cert.X509CertSelector#setIssuer(byte[])
     */
    public void test_setIssuerLB$() throws Exception {
        byte[] name1 = new byte[]
        // manually obtained DER encoding of "O=First Org." issuer name;
        { 48, 21, 49, 19, 48, 17, 6, 3, 85, 4, 10, 19, 10, 70, 105, 114, 115,
                116, 32, 79, 114, 103, 46 };
        byte[] name2 = new byte[]
        // manually obtained DER encoding of "O=Second Org." issuer name;
        { 48, 22, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 83, 101, 99, 111,
                110, 100, 32, 79, 114, 103, 46 };
        X500Principal iss1 = new X500Principal(name1);
        X500Principal iss2 = new X500Principal(name2);
        TestCert cert1 = new TestCert(iss1);
        TestCert cert2 = new TestCert(iss2);

        X509CertSelector selector = new X509CertSelector();

        selector.setIssuer((byte[]) null);
        assertTrue("Any certificates should match "
                   + "in the case of null issuer criteria.", selector.match(cert1)
                   && selector.match(cert2));
        selector.setIssuer(name1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setIssuer(name2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setIssuer(java.lang.String)
     */
    public void test_setIssuerLjava_lang_String() throws Exception {

        String name1 = "O=First Org.";
        String name2 = "O=Second Org.";
        X500Principal iss1 = new X500Principal(name1);
        X500Principal iss2 = new X500Principal(name2);
        TestCert cert1 = new TestCert(iss1);
        TestCert cert2 = new TestCert(iss2);

        X509CertSelector selector = new X509CertSelector();

        selector.setIssuer((String) null);
        assertTrue("Any certificates should match "
                   + "in the case of null issuer criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setIssuer(name1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setIssuer(name2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setIssuer(javax.security.auth.x500.X500Principal)
     */
    public void test_setIssuerLjavax_security_auth_x500_X500Principal()
            throws Exception {
        X500Principal iss1 = new X500Principal("O=First Org.");
        X500Principal iss2 = new X500Principal("O=Second Org.");
        TestCert cert1 = new TestCert(iss1);
        TestCert cert2 = new TestCert(iss2);
        X509CertSelector selector = new X509CertSelector();

        selector.setIssuer((X500Principal) null);
        assertTrue("Any certificates should match "
                   + "in the case of null issuer criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setIssuer(iss1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setIssuer(iss2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setKeyUsage(boolean)
     */
    public void test_setKeyUsageZ() throws Exception {
        boolean[] ku1 = new boolean[] { true, true, true, true, true, true,
                true, true, true };
        // decipherOnly is disallowed
        boolean[] ku2 = new boolean[] { true, true, true, true, true, true,
                true, true, false };
        TestCert cert1 = new TestCert(ku1);
        TestCert cert2 = new TestCert(ku2);
        TestCert cert3 = new TestCert((boolean[]) null);

        X509CertSelector selector = new X509CertSelector();

        selector.setKeyUsage(null);
        assertTrue("Any certificate should match in the case of null keyUsage criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setKeyUsage(ku1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        assertTrue("The certificate which does not have a keyUsage extension "
                   + "implicitly allows all keyUsage values.",
                   selector.match(cert3));
        selector.setKeyUsage(ku2);
        ku2[0] = !ku2[0];
        assertTrue("The certificate should match the selection criteria.",
                selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setMatchAllSubjectAltNames(boolean)
     */
    public void test_setMatchAllSubjectAltNamesZ() {
        TestCert cert = new TestCert();
        X509CertSelector selector = new X509CertSelector();

        assertTrue(selector.match(cert));

        assertFalse(selector.match(null));
    }

    /**
     * java.security.cert.X509CertSelector#setNameConstraints(byte[]
     *        bytes)
     */
    public void test_setNameConstraintsLB$() throws IOException {
//        GeneralName[] name_constraints = new GeneralName[] {
//                new GeneralName(1, "822.Name"),
//                new GeneralName(1, "rfc@822.Name"),
//                new GeneralName(2, "Name.org"),
//                new GeneralName(2, "dNS.Name.org"),
//
//                new GeneralName(6, "http://Resource.Id"),
//                new GeneralName(6, "http://uniform.Resource.Id"),
//                new GeneralName(7, "1.1.1.1"),
//
//                new GeneralName(new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//                        1, 1, 1, 1, 1 }), };
//
//        for (int i = 0; i < name_constraints.length; i++) {
//            GeneralSubtree subtree = new GeneralSubtree(name_constraints[i]);
//            GeneralSubtrees subtrees = new GeneralSubtrees();
//            subtrees.addSubtree(subtree);
//            NameConstraints constraints = new NameConstraints(subtrees,
//                    subtrees);
//        }
        X509CertSelector selector = new X509CertSelector();

        for (int i = 0; i < constraintBytes.length; i++) {
            selector.setNameConstraints(constraintBytes[i]);
            assertTrue(Arrays.equals(constraintBytes[i], selector.getNameConstraints()));
        }
    }

    /**
     * java.security.cert.X509CertSelector#setPathToNames(Collection<List<?>>)
     */
    public void test_setPathToNamesLjava_util_Collection() throws Exception {
        GeneralName san0 = new GeneralName(new OtherName("1.2.3.4.5",
                                                         new byte[] { 1, 2, 0, 1 }));
        GeneralName san1 = new GeneralName(1, "rfc@822.Name");
        GeneralName san2 = new GeneralName(2, "dNSName");
        GeneralName san3 = new GeneralName(new ORAddress());
        GeneralName san4 = new GeneralName(new Name("O=Organization"));
        GeneralName san6 = new GeneralName(6, "http://uniform.Resource.Id");
        GeneralName san7 = new GeneralName(7, "1.1.1.1");
        GeneralName san8 = new GeneralName(8, "1.2.3.4444.55555");

        GeneralNames sans1 = new GeneralNames();
        sans1.addName(san0);
        sans1.addName(san1);
        sans1.addName(san2);
        sans1.addName(san3);
        sans1.addName(san4);
        sans1.addName(san6);
        sans1.addName(san7);
        sans1.addName(san8);
        GeneralNames sans2 = new GeneralNames();
        sans2.addName(san0);

        TestCert cert1 = new TestCert(sans1);
        TestCert cert2 = new TestCert(sans2);
        X509CertSelector selector = new X509CertSelector();
        selector.setMatchAllSubjectAltNames(true);

        selector.setPathToNames(null);
        assertTrue("Any certificate should match in the case of null "
                   + "subjectAlternativeNames criteria.",
                   selector.match(cert1) && selector.match(cert2));

        Collection<List<?>> sans = sans1.getPairsList();

        selector.setPathToNames(sans);
        selector.getPathToNames();
    }

    /**
     * java.security.cert.X509CertSelector#setPolicy(Set<String>)
     */
    public void test_setPolicyLjava_util_Set() throws IOException {
        String[] policies1 = new String[] { 
            "1.3.6.1.5.5.7.3.1",
            "1.3.6.1.5.5.7.3.2",
            "1.3.6.1.5.5.7.3.3",
            "1.3.6.1.5.5.7.3.4",
            "1.3.6.1.5.5.7.3.8",
            "1.3.6.1.5.5.7.3.9",
            "1.3.6.1.5.5.7.3.5",
            "1.3.6.1.5.5.7.3.6",
            "1.3.6.1.5.5.7.3.7"
        };

        String[] policies2 = new String[] { "1.3.6.7.3.1" };

        HashSet<String> p1 = new HashSet<String>(Arrays.asList(policies1));
        HashSet<String> p2 = new HashSet<String>(Arrays.asList(policies2));

        X509CertSelector selector = new X509CertSelector();

        TestCert cert1 = new TestCert(policies1);
        TestCert cert2 = new TestCert(policies2);

        selector.setPolicy(null);
        assertTrue("Any certificate should match in the case of null "
                + "privateKeyValid criteria.",
                   selector.match(cert1) && selector.match(cert2));

        selector.setPolicy(p1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));

        selector.setPolicy(p2);
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert1));
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setPrivateKeyValid(java.util.Date)
     */
    public void test_setPrivateKeyValidLjava_util_Date()
            throws Exception {
        Date date1 = new Date(100000000);
        Date date2 = new Date(200000000);
        Date date3 = new Date(300000000);
        Date date4 = new Date(150000000);
        Date date5 = new Date(250000000);
        TestCert cert1 = new TestCert(date1, date2);
        TestCert cert2 = new TestCert(date2, date3);

        X509CertSelector selector = new X509CertSelector();

        selector.setPrivateKeyValid(null);
        assertTrue("Any certificate should match in the case of null "
                + "privateKeyValid criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setPrivateKeyValid(date4);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setPrivateKeyValid(date5);
        date5.setTime(date4.getTime());
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSerialNumber(java.math.BigInteger)
     */
    public void test_setSerialNumberLjava_math_BigInteger()
            throws Exception {
        BigInteger ser1 = new BigInteger("10000");
        BigInteger ser2 = new BigInteger("10001");
        TestCert cert1 = new TestCert(ser1);
        TestCert cert2 = new TestCert(ser2);
        X509CertSelector selector = new X509CertSelector();

        selector.setSerialNumber(null);
        assertTrue("Any certificate should match in the case of null "
                   + "serialNumber criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSerialNumber(ser1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSerialNumber(ser2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubject(byte[])
     */
    public void test_setSubjectLB$() throws Exception {
        byte[] name1 = new byte[]
        // manually obtained DER encoding of "O=First Org." issuer name;
        { 48, 21, 49, 19, 48, 17, 6, 3, 85, 4, 10, 19, 10, 70, 105, 114, 115,
                116, 32, 79, 114, 103, 46 };
        byte[] name2 = new byte[]
        // manually obtained DER encoding of "O=Second Org." issuer name;
        { 48, 22, 49, 20, 48, 18, 6, 3, 85, 4, 10, 19, 11, 83, 101, 99, 111,
                110, 100, 32, 79, 114, 103, 46 };
        X500Principal sub1 = new X500Principal(name1);
        X500Principal sub2 = new X500Principal(name2);
        TestCert cert1 = new TestCert(sub1);
        TestCert cert2 = new TestCert(sub2);

        X509CertSelector selector = new X509CertSelector();

        selector.setSubject((byte[]) null);
        assertTrue("Any certificates should match "
                   + "in the case of null issuer criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSubject(name1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubject(name2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubject(java.lang.String)
     */
    public void test_setSubjectLjava_lang_String() throws Exception {
        String name1 = "O=First Org.";
        String name2 = "O=Second Org.";
        X500Principal sub1 = new X500Principal(name1);
        X500Principal sub2 = new X500Principal(name2);
        TestCert cert1 = new TestCert(sub1);
        TestCert cert2 = new TestCert(sub2);
        X509CertSelector selector = new X509CertSelector();

        selector.setSubject((String) null);
        assertTrue("Any certificates should match "
                   + "in the case of null subject criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSubject(name1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubject(name2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubject(javax.security.auth.x500.X500Principal)
     */
    public void test_setSubjectLjavax_security_auth_x500_X500Principal()
            throws Exception {
        X500Principal sub1 = new X500Principal("O=First Org.");
        X500Principal sub2 = new X500Principal("O=Second Org.");
        TestCert cert1 = new TestCert(sub1);
        TestCert cert2 = new TestCert(sub2);
        X509CertSelector selector = new X509CertSelector();

        selector.setSubject((X500Principal) null);
        assertTrue("Any certificates should match "
                   + "in the case of null subjcet criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSubject(sub1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubject(sub2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubjectAlternativeNames(Collection<List<?>>)
     */
    public void test_setSubjectAlternativeNamesLjava_util_Collection() throws Exception {

        GeneralName san0 = new GeneralName(new OtherName("1.2.3.4.5",
                                                         new byte[] { 1, 2, 0, 1 }));
        GeneralName san1 = new GeneralName(1, "rfc@822.Name");
        GeneralName san2 = new GeneralName(2, "dNSName");
        GeneralName san3 = new GeneralName(new ORAddress());
        GeneralName san4 = new GeneralName(new Name("O=Organization"));
        GeneralName san6 = new GeneralName(6, "http://uniform.Resource.Id");
        GeneralName san7 = new GeneralName(7, "1.1.1.1");
        GeneralName san8 = new GeneralName(8, "1.2.3.4444.55555");

        GeneralNames sans1 = new GeneralNames();
        sans1.addName(san0);
        sans1.addName(san1);
        sans1.addName(san2);
        sans1.addName(san3);
        sans1.addName(san4);
        sans1.addName(san6);
        sans1.addName(san7);
        sans1.addName(san8);
        GeneralNames sans2 = new GeneralNames();
        sans2.addName(san0);

        TestCert cert1 = new TestCert(sans1);
        TestCert cert2 = new TestCert(sans2);
        X509CertSelector selector = new X509CertSelector();
        selector.setMatchAllSubjectAltNames(true);

        selector.setSubjectAlternativeNames(null);
        assertTrue("Any certificate should match in the case of null "
                   + "subjectAlternativeNames criteria.",
                   selector.match(cert1) && selector.match(cert2));

        Collection<List<?>> sans = sans1.getPairsList();

        selector.setSubjectAlternativeNames(sans);

        selector.getSubjectAlternativeNames();
    }

    /**
     * java.security.cert.X509CertSelector#setSubjectKeyIdentifier(byte[])
     */
    public void test_setSubjectKeyIdentifierLB$() throws Exception {
        byte[] skid1 = new byte[] { 1, 2, 3, 4, 5 }; // random value
        byte[] skid2 = new byte[] { 5, 4, 3, 2, 1 }; // random value
        TestCert cert1 = new TestCert(skid1);
        TestCert cert2 = new TestCert(skid2);
        X509CertSelector selector = new X509CertSelector();

        selector.setSubjectKeyIdentifier(null);
        assertTrue("Any certificate should match in the case of null "
                + "serialNumber criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSubjectKeyIdentifier(skid1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubjectKeyIdentifier(skid2);
        skid2[0]++;
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubjectPublicKey(byte[])
     */
    public void test_setSubjectPublicKeyLB$() throws Exception {

        //SubjectPublicKeyInfo  ::=  SEQUENCE  {
        //    algorithm            AlgorithmIdentifier,
        //    subjectPublicKey     BIT STRING  }
        byte[] enc = { 0x30, 0x0E, // SEQUENCE
                0x30, 0x07, // SEQUENCE
                0x06, 0x02, 0x03, 0x05,//OID
                0x01, 0x01, 0x07, //ANY
                0x03, 0x03, 0x01, 0x01, 0x06, // subjectPublicKey
        };

        X509CertSelector selector = new X509CertSelector();

        selector.setSubjectPublicKey(enc);
        PublicKey key = selector.getSubjectPublicKey();
        assertEquals("0.3.5", key.getAlgorithm());
        assertEquals("X.509", key.getFormat());
        assertTrue(Arrays.equals(enc, key.getEncoded()));
        assertNotNull(key.toString());
    }

    /**
     * java.security.cert.X509CertSelector#setSubjectPublicKey(java.security.PublicKey key)
     */
    public void test_setSubjectPublicKeyLjava_security_PublicKey()
            throws Exception {
        PublicKey pkey1 = new TestKeyPair("RSA").getPublic();
        PublicKey pkey2 = new TestKeyPair("DSA").getPublic();

        TestCert cert1 = new TestCert(pkey1);
        TestCert cert2 = new TestCert(pkey2);
        X509CertSelector selector = new X509CertSelector();

        selector.setSubjectPublicKey((PublicKey) null);
        assertTrue("Any certificate should match in the case of null "
                   + "subjectPublicKey criteria.",
                   selector.match(cert1) && selector.match(cert2));
        selector.setSubjectPublicKey(pkey1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubjectPublicKey(pkey2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#setSubjectPublicKeyAlgID(java.lang.String)
     */
    public void test_setSubjectPublicKeyAlgIDLjava_lang_String() throws Exception {

        X509CertSelector selector = new X509CertSelector();
        String pkaid1 = "1.2.840.113549.1.1.1"; // RSA (source:
        // http://asn1.elibel.tm.fr)
        String pkaid2 = "1.2.840.10040.4.1"; // DSA (source:
        // http://asn1.elibel.tm.fr)
        PublicKey pkey1 = new TestKeyPair("RSA").getPublic();;
        PublicKey pkey2 = new TestKeyPair("DSA").getPublic();;

        TestCert cert1 = new TestCert(pkey1);
        TestCert cert2 = new TestCert(pkey2);

        selector.setSubjectPublicKeyAlgID(null);
        assertTrue("Any certificate should match in the case of null "
                   + "subjectPublicKeyAlgID criteria.",
                   selector.match(cert1) && selector.match(cert2));

        String[] validOIDs = {
            "0.0.20",
            "1.25.0",
            "2.0.39",
            "0.2.10",
            "1.35.15",
            "2.17.89",
            "2.5.29.16",
            "2.5.29.17",
            "2.5.29.30",
            "2.5.29.32",
            "2.5.29.37"
        };

        for (int i = 0; i < validOIDs.length; i++) {
            selector.setSubjectPublicKeyAlgID(validOIDs[i]);
            assertEquals(validOIDs[i], selector.getSubjectPublicKeyAlgID());
        }

        String[] invalidOIDs = { "0.20", "1.25", "2.39", "3.10" };
        for (int i = 0; i < invalidOIDs.length; i++) {
            try {
                selector.setSubjectPublicKeyAlgID(invalidOIDs[i]);
                fail("IOException wasn't thrown for " + invalidOIDs[i]);
            } catch (IOException expected) {
            }
        }

        selector.setSubjectPublicKeyAlgID(pkaid1);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert1));
        assertFalse("The certificate should not match the selection criteria.",
                    selector.match(cert2));
        selector.setSubjectPublicKeyAlgID(pkaid2);
        assertTrue("The certificate should match the selection criteria.",
                   selector.match(cert2));
    }

    /**
     * java.security.cert.X509CertSelector#toString()
     */
    public void test_toString() {
        X509CertSelector selector = new X509CertSelector();
        assertNotNull(selector.toString());
    }

    public class MyPublicKey implements PublicKey {
        private static final long serialVersionUID = 2899528375354645752L;

        public MyPublicKey() {
            super();
        }

        public String getAlgorithm() {
            return "PublicKey";
        }

        public String getFormat() {
            return "Format";
        }

        public byte[] getEncoded() {
            return new byte[0];
        }

        public long getSerVerUID() {
            return serialVersionUID;
        }
    }

    private class TestCert extends X509Certificate {

        private static final long serialVersionUID = 176676115254260405L;

        /* Stuff fields */
        protected String equalCriteria = null; // to simplify method equals()

        protected BigInteger serialNumber = null;

        protected X500Principal issuer = null;

        protected X500Principal subject = null;

        protected byte[] keyIdentifier = null;

        protected Date date = null;

        protected Date notBefore = null;

        protected Date notAfter = null;

        protected PublicKey key = null;

        protected boolean[] keyUsage = null;

        protected List<String> extKeyUsage = null;

        protected int pathLen = 1;

        protected GeneralNames sans = null;

        protected byte[] encoding = null;

        protected String[] policies = null;

        protected Collection<List<?>> collection = null;

        protected NameConstraints nameConstraints = null;

        /* Stuff methods */
        public TestCert() {
        }

        public TestCert(GeneralNames sans) {
            setSubjectAlternativeNames(sans);
        }

        public TestCert(NameConstraints nameConstraints) {
            this.nameConstraints = nameConstraints;
        }

        public TestCert(Collection<List<?>> collection) {
            setCollection(collection);
        }

        public TestCert(String equalCriteria) {
            setEqualCriteria(equalCriteria);
        }

        public TestCert(String[] policies) {
            setPolicies(policies);
        }

        public TestCert(BigInteger serial) {
            setSerialNumber(serial);
        }

        public TestCert(X500Principal principal) {
            setIssuer(principal);
            setSubject(principal);
        }

        public TestCert(byte[] array) {
            setKeyIdentifier(array);
        }

        public TestCert(Date date) {
            setDate(date);
        }

        public TestCert(Date notBefore, Date notAfter) {
            setPeriod(notBefore, notAfter);
        }

        public TestCert(PublicKey key) {
            setPublicKey(key);
        }

        public TestCert(boolean[] keyUsage) {
            setKeyUsage(keyUsage);
        }

        public TestCert(Set<String> extKeyUsage) {
            setExtendedKeyUsage(extKeyUsage);
        }

        public TestCert(int pathLen) {
            this.pathLen = pathLen;
        }

        public void setSubjectAlternativeNames(GeneralNames sans) {
            this.sans = sans;
        }

        public void setCollection(Collection<List<?>> collection) {
            this.collection = collection;
        }

        public void setPolicies(String[] policies) {
            this.policies = policies;
        }

        public void setExtendedKeyUsage(Set<String> extKeyUsage) {
            this.extKeyUsage = (extKeyUsage == null) ? null : new ArrayList<String>(extKeyUsage);
        }

        public void setKeyUsage(boolean[] keyUsage) {
            this.keyUsage = (keyUsage == null) ? null : (boolean[]) keyUsage.clone();
        }

        public void setPublicKey(PublicKey key) {
            this.key = key;
        }

        public void setPeriod(Date notBefore, Date notAfter) {
            this.notBefore = notBefore;
            this.notAfter = notAfter;
        }

        public void setSerialNumber(BigInteger serial) {
            this.serialNumber = serial;
        }

        public void setEqualCriteria(String equalCriteria) {
            this.equalCriteria = equalCriteria;
        }

        public void setIssuer(X500Principal issuer) {
            this.issuer = issuer;
        }

        public void setSubject(X500Principal subject) {
            this.subject = subject;
        }

        public void setKeyIdentifier(byte[] subjectKeyID) {
            this.keyIdentifier = (byte[]) subjectKeyID.clone();
        }

        public void setDate(Date date) {
            this.date = new Date(date.getTime());
        }

        public void setEncoding(byte[] encoding) {
            this.encoding = encoding;
        }

        /* Method implementations */
        public boolean equals(Object cert) {
            if (cert == null) {
                return false;
            }
            if ((equalCriteria == null)
                    || (((TestCert) cert).equalCriteria == null)) {
                return false;
            } else {
                return equalCriteria.equals(((TestCert) cert).equalCriteria);
            }
        }

        public String toString() {
            if (equalCriteria != null) {
                return equalCriteria;
            }
            return "";
        }

        public void checkValidity() throws CertificateExpiredException,
                CertificateNotYetValidException {
        }

        public void checkValidity(Date date)
                throws CertificateExpiredException,
                CertificateNotYetValidException {
            if (this.date == null) {
                throw new CertificateExpiredException();
            }
            int result = this.date.compareTo(date);
            if (result > 0) {
                throw new CertificateExpiredException();
            }
            if (result < 0) {
                throw new CertificateNotYetValidException();
            }
        }

        public int getVersion() {
            return 3;
        }

        public BigInteger getSerialNumber() {
            return (serialNumber == null) ? new BigInteger("1111")
                    : serialNumber;
        }

        public Principal getIssuerDN() {
            return issuer;
        }

        public X500Principal getIssuerX500Principal() {
            return issuer;
        }

        public Principal getSubjectDN() {
            return subject;
        }

        public X500Principal getSubjectX500Principal() {
            return subject;
        }

        public Date getNotBefore() {
            return null;
        }

        public Date getNotAfter() {
            return null;
        }

        public byte[] getTBSCertificate() throws CertificateEncodingException {
            return null;
        }

        public byte[] getSignature() {
            return null;
        }

        public String getSigAlgName() {
            return null;
        }

        public String getSigAlgOID() {
            return null;
        }

        public byte[] getSigAlgParams() {
            return null;
        }

        public boolean[] getIssuerUniqueID() {
            return null;
        }

        public boolean[] getSubjectUniqueID() {
            return null;
        }

        public boolean[] getKeyUsage() {
            return keyUsage;
        }

        public List<String> getExtendedKeyUsage()
                throws CertificateParsingException {
            return extKeyUsage;
        }

        public int getBasicConstraints() {
            return pathLen;
        }

        public void verify(PublicKey key) throws CertificateException,
                NoSuchAlgorithmException, InvalidKeyException,
                NoSuchProviderException, SignatureException {
        }

        public void verify(PublicKey key, String sigProvider)
                throws CertificateException, NoSuchAlgorithmException,
                InvalidKeyException, NoSuchProviderException,
                SignatureException {
        }

        public PublicKey getPublicKey() {
            return key;
        }

        public byte[] getEncoded() throws CertificateEncodingException {
            return encoding;
        }

        public Set<String> getNonCriticalExtensionOIDs() {
            return null;
        }

        public Set<String> getCriticalExtensionOIDs() {
            return null;
        }

        public byte[] getExtensionValue(String oid) {

            if (("2.5.29.14".equals(oid)) || ("2.5.29.35".equals(oid))) {
                // Extension value is represented as an OctetString
                return ASN1OctetString.getInstance().encode(keyIdentifier);
            }
            if ("2.5.29.16".equals(oid)) {
                PrivateKeyUsagePeriod pkup = new PrivateKeyUsagePeriod(
                        notBefore, notAfter);
                byte[] encoded = pkup.getEncoded();
                return ASN1OctetString.getInstance().encode(encoded);
            }
            if (("2.5.29.37".equals(oid)) && (extKeyUsage != null)) {
                ASN1Oid[] oa = new ASN1Oid[extKeyUsage.size()];
                String[] val = new String[extKeyUsage.size()];
                Iterator it = extKeyUsage.iterator();
                int id = 0;
                while (it.hasNext()) {
                    oa[id] = ASN1Oid.getInstanceForString();
                    val[id++] = (String) it.next();
                }
                return ASN1OctetString.getInstance().encode(
                        new ASN1Sequence(oa).encode(val));
            }
            if ("2.5.29.19".equals(oid)) {
                return ASN1OctetString.getInstance().encode(
                        new ASN1Sequence(new ASN1Type[] {
                                ASN1Boolean.getInstance(),
                                ASN1Integer.getInstance() })
                                .encode(new Object[] {
                                        new Boolean(pathLen != 1),
                                        BigInteger.valueOf(pathLen).toByteArray() }));
            }
            if ("2.5.29.17".equals(oid) && (sans != null)) {
                if (sans.getNames() == null) {
                    return null;
                }
                return ASN1OctetString.getInstance().encode(
                        GeneralNames.ASN1.encode(sans));
            }
            if ("2.5.29.32".equals(oid) && (policies != null)
                    && (policies.length > 0)) {
                // Certificate Policies Extension (as specified in rfc 3280)
                CertificatePolicies certificatePolicies = new CertificatePolicies();
                for (int i = 0; i < policies.length; i++) {
                    PolicyInformation policyInformation = new PolicyInformation(
                            policies[i]);
                    certificatePolicies.addPolicyInformation(policyInformation);
                }
                return ASN1OctetString.getInstance().encode(
                        certificatePolicies.getEncoded());
            }
            if ("2.5.29.30".equals(oid) && (nameConstraints != null)) { //
                // Name
                // Constraints
                // Extension
                // (as
                // specified
                // in
                // rfc
                // 3280)
                return ASN1OctetString.getInstance().encode(
                        nameConstraints.getEncoded());
            }

            return null;
        }

        public boolean hasUnsupportedCriticalExtension() {
            return false;
        }

    }

    public X509Certificate rootCertificate;

    public X509Certificate endCertificate;

    public MyCRL crl;

    private X509CertSelector theCertSelector;

    private CertPathBuilder builder;

    private void setupEnvironment() throws Exception {
        // create certificates and CRLs
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream bi = new ByteArrayInputStream(TestUtils.rootCert.getBytes());
        rootCertificate = (X509Certificate) cf.generateCertificate(bi);
        bi = new ByteArrayInputStream(TestUtils.endCert.getBytes());
        endCertificate = (X509Certificate) cf.generateCertificate(bi);

        BigInteger revokedSerialNumber = BigInteger.valueOf(1);
        crl = new MyCRL("X.509");
//        X509CRL rootCRL = X509CRL;
//        X509CRL interCRL = X509CRLExample.createCRL(interCert,
//                                                    interPair.getPrivate(),
//                                                    revokedSerialNumber);

        // create CertStore to support path building
        List<Object> list = new ArrayList<Object>();

        list.add(rootCertificate);
        list.add(endCertificate);

//        CollectionCertStoreParameters params = new CollectionCertStoreParameters(list);
//        CertStore store = CertStore.getInstance("Collection", params);
//
        theCertSelector = new X509CertSelector();
        theCertSelector.setCertificate(endCertificate);
        theCertSelector.setIssuer(endCertificate.getIssuerX500Principal().getEncoded());

        // build the path
        builder = CertPathBuilder.getInstance("PKIX");

    }

    private CertPath buildCertPath() throws InvalidAlgorithmParameterException {
        PKIXCertPathBuilderResult result = null;
        PKIXBuilderParameters buildParams = new PKIXBuilderParameters(
                Collections.singleton(new TrustAnchor(rootCertificate, null)),
                theCertSelector);
        try {
        result = (PKIXCertPathBuilderResult) builder.build(buildParams);
        } catch(CertPathBuilderException e) {
            return null;
        }
        return result.getCertPath();
    }

    /**
     * java.security.cert.X509CertSelector#addPathToName(int, byte[])
     */
    public void test_addPathToNameLintLbyte_array2() throws Exception {
        TestUtils.initCertPathSSCertChain();
        setupEnvironment();
        byte[] bytes, bytesName;
        // GeneralName name = new GeneralName(1, "822.Name");
        // bytes = name.getEncoded();
        // bytesName = name.getEncodedName();
        bytes = new byte[] {-127, 8, 56, 50, 50, 46, 78, 97, 109, 101};
        bytesName = new byte[] {22, 8, 56, 50, 50, 46, 78, 97, 109, 101};
        bytes[bytes.length-3] = (byte) 200;

        try {
            theCertSelector.addPathToName(1, bytes);
        } catch (IOException e) {
            // ok
        }

        theCertSelector.setPathToNames(null);

        theCertSelector.addPathToName(1, bytesName);
        assertNotNull(theCertSelector.getPathToNames());
        CertPath p = buildCertPath();
        assertNull(p);

        theCertSelector.setPathToNames(null);

//        name = new GeneralName(new Name("O=Android"));
//        theCertSelector.addPathToName(4, endCertificate.getSubjectDN().getName());
        theCertSelector.addPathToName(4, TestUtils.rootCertificateSS.getIssuerX500Principal().getEncoded());
        assertNotNull(theCertSelector.getPathToNames());
        p = TestUtils.buildCertPathSSCertChain();
        assertNotNull(p);
    }

    /**
     * java.security.cert.X509CertSelector#addPathToName(int, String)
     */
    public void test_addPathToNameLintLjava_lang_String2() throws Exception {
        setupEnvironment();
        byte[] bytes, bytesName;
        // GeneralName name = new GeneralName(1, "822.Name");
        // bytes = name.getEncoded();
        // bytesName = name.getEncodedName();
        bytes = new byte[] {-127, 8, 56, 50, 50, 46, 78, 97, 109, 101};
        bytesName = new byte[] {22, 8, 56, 50, 50, 46, 78, 97, 109, 101};
        assertNotNull(bytes);
        byte[] b = new byte[bytes.length];
        b = bytes;
        b[bytes.length-3] = (byte) 200;

        try {
        theCertSelector.addPathToName(1, new String(b));
        } catch (IOException e) {
            // ok
        }

        theCertSelector.setPathToNames(null);

        theCertSelector.addPathToName(1, new String(bytesName));
        assertNotNull(theCertSelector.getPathToNames());

        CertPath p = buildCertPath();
        assertNull(p);

        theCertSelector.setPathToNames(null);
        theCertSelector.addPathToName(1, rootCertificate.getIssuerX500Principal().getName());
        assertNotNull(theCertSelector.getPathToNames());
        //p = buildCertPath();
        //assertNotNull(p);
    }

    /**
     * java.security.cert.X509CertSelector#addSubjectAlternativeName(int, byte[])
     */
    public void test_addSubjectAlternativeNameLintLbyte_array2()
            throws Exception {


        GeneralName san0 = new GeneralName(new OtherName("1.2.3.4.5",
                new byte[] {1, 2, 0, 1}));
        GeneralName san1 = new GeneralName(1, "rfc@822.Name");
        GeneralName san2 = new GeneralName(2, "dNSName");

        GeneralNames sans1 = new GeneralNames();
        sans1.addName(san0);
        sans1.addName(san1);
        sans1.addName(san2);

        X509CertSelector selector = new X509CertSelector();

        selector.addSubjectAlternativeName(0, san0.getEncodedName());
        selector.addSubjectAlternativeName(1, san1.getEncodedName());
        selector.addSubjectAlternativeName(2, san2.getEncodedName());

        GeneralNames sans2 = new GeneralNames();
        sans2.addName(san0);

        TestCert cert1 = new TestCert(sans1);
        TestCert cert2 = new TestCert(sans2);

        assertTrue(selector.match(cert1));
        assertFalse(selector.match(cert2));

        selector.setSubjectAlternativeNames(null);

        GeneralName name = new GeneralName(new Name("O=Android"));
        try {
            selector.addSubjectAlternativeName(0, name.getEncodedName());
        } catch (IOException e) {
            // ok
        }

    }

    /**
     * java.security.cert.X509CertSelector#addSubjectAlternativeName(int, String)
     */
    public void test_addSubjectAlternativeNameLintLjava_lang_String2() throws Exception{
        GeneralName san6 = new GeneralName(6, "http://uniform.Resource.Id");
        GeneralName san2 = new GeneralName(2, "dNSName");

        GeneralNames sans1 = new GeneralNames();
        sans1.addName(san6);
        sans1.addName(san2);

        X509CertSelector selector = new X509CertSelector();

        selector.addSubjectAlternativeName(6, "http://uniform.Resource.Id");
        selector.addSubjectAlternativeName(2, "dNSName");

        GeneralNames sans2 = new GeneralNames();
        sans2.addName(san2);

        TestCert cert1 = new TestCert(sans1);
        TestCert cert2 = new TestCert(sans2);

        assertTrue(selector.match(cert1));
        assertFalse(selector.match(cert2));

        selector.setSubjectAlternativeNames(null);

        GeneralName name = new GeneralName(new Name("O=Android"));
        try {
            selector.addSubjectAlternativeName(0, (name.toString()));
        } catch (IOException e) {
            // ok
        }

    }
}
