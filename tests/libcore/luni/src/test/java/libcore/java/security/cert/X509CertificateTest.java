/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.security.cert;

import tests.support.resource.Support_Resources;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import junit.framework.TestCase;
import libcore.java.security.StandardNames;

public class X509CertificateTest extends TestCase {
    private Provider[] mX509Providers;

    private static final String CERT_RSA = "x509/cert-rsa.der";

    private static final String CERT_DSA = "x509/cert-dsa.der";

    private static final String CERT_EC = "x509/cert-ec.der";

    private static final String CERT_KEYUSAGE_EXTRALONG = "x509/cert-keyUsage-extraLong.der";

    private static final String CERT_EXTENDEDKEYUSAGE = "x509/cert-extendedKeyUsage.der";

    private final static String CERT_RSA_TBS = "x509/cert-rsa-tbs.der";

    private final static String CERT_RSA_SIGNATURE = "x509/cert-rsa-sig.der";

    private static final String CERT_USERWITHPATHLEN = "x509/cert-userWithPathLen.der";

    private static final String CERT_CA = "x509/cert-ca.der";

    private static final String CERT_CAWITHPATHLEN = "x509/cert-caWithPathLen.der";

    private static final String CERT_INVALIDIP = "x509/cert-invalidip.der";

    private static final String CERT_IPV6 = "x509/cert-ipv6.der";

    private static final String CERT_ALT_OTHER = "x509/cert-alt-other.der";

    private static final String CERT_ALT_EMAIL = "x509/cert-alt-email.der";

    private static final String CERT_ALT_DNS = "x509/cert-alt-dns.der";

    private static final String CERT_ALT_DIRNAME = "x509/cert-alt-dirname.der";

    private static final String CERT_ALT_URI = "x509/cert-alt-uri.der";

    private static final String CERT_ALT_RID = "x509/cert-alt-rid.der";

    private static final String CERT_ALT_NONE = "x509/cert-alt-none.der";

    private static final String CERT_UNSUPPORTED = "x509/cert-unsupported.der";

    private static final String CERT_SIGOPT = "x509/cert-sigopt.der";

    private static final String CERTS_X509_PEM = "x509/certs.pem";

    private static final String CERTS_X509_DER = "x509/certs.der";

    private static final String CERTS_PKCS7_PEM = "x509/certs-pk7.pem";

    private static final String CERTS_PKCS7_DER = "x509/certs-pk7.der";

    /** A list of certs that are all slightly different. */
    private static final String[] VARIOUS_CERTS = new String[] {
            CERT_RSA, CERT_DSA, CERT_EC,
    };

    private final X509Certificate getCertificate(CertificateFactory f, String name)
            throws Exception {
        final InputStream is = Support_Resources.getStream(name);
        assertNotNull("File does not exist: " + name, is);
        try {
            return (X509Certificate) f.generateCertificate(is);
        } finally {
            try {
                is.close();
            } catch (IOException ignored) {
            }
        }
    }

    private final Collection<? extends X509Certificate> getCertificates(CertificateFactory f, String name)
            throws Exception {
        final InputStream is = Support_Resources.getStream(name);
        assertNotNull("File does not exist: " + name, is);
        try {
            return (Collection<? extends X509Certificate>) f.generateCertificates(is);
        } finally {
            try {
                is.close();
            } catch (IOException ignored) {
            }
        }
    }

    private PublicKey getRsaCertificatePublicKey() throws Exception {
        final InputStream ris = Support_Resources.getStream("x509/cert-rsa-pubkey.der");
        try {
            final int size = ris.available();
            final DataInputStream is = new DataInputStream(ris);
            final byte[] keyBytes = new byte[size];
            is.readFully(keyBytes);

            final KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(new X509EncodedKeySpec(keyBytes));
        } finally {
            try {
                ris.close();
            } catch (IOException ignored) {
            }
        }
    }

    private Date[] getRsaCertificateDates() throws Exception {
        final InputStream ris = Support_Resources.getStream("x509/cert-rsa-dates.txt");
        try {
            // notBefore=Dec 26 00:19:14 2012 GMT
            final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss yyyy zzz");

            final BufferedReader buf = new BufferedReader(new InputStreamReader(ris));
            String line = buf.readLine();
            int index = line.indexOf('=');
            assertEquals("notBefore", line.substring(0, index));
            final Date startDate = sdf.parse(line.substring(index + 1));

            line = buf.readLine();
            index = line.indexOf('=');
            assertEquals("notAfter", line.substring(0, index));
            final Date endDate = sdf.parse(line.substring(index + 1));

            assertTrue(startDate.before(endDate));
            assertTrue(endDate.after(startDate));

            return new Date[] { startDate, endDate };
        } finally {
            try {
                ris.close();
            } catch (IOException ignored) {
            }
        }
    }

    private BigInteger getRsaCertificateSerial() throws Exception {
        final InputStream ris = Support_Resources.getStream("x509/cert-rsa-serial.txt");
        try {
            final BufferedReader buf = new BufferedReader(new InputStreamReader(ris));

            String line = buf.readLine();
            int index = line.indexOf('=');
            assertEquals("serial", line.substring(0, index));

            return new BigInteger(line.substring(index + 1), 16);
        } finally {
            try {
                ris.close();
            } catch (IOException ignored) {
            }
        }
    }

    private byte[] getResourceAsBytes(String name) throws Exception {
        final InputStream ris = Support_Resources.getStream(name);
        try {
            DataInputStream dis = new DataInputStream(ris);
            byte[] buf = new byte[ris.available()];
            dis.readFully(buf);
            return buf;
        } finally {
            try {
                ris.close();
            } catch (IOException ignored) {
            }
        }
    }

    private byte[] getRsaCertificateSignature() throws Exception {
        return getResourceAsBytes(CERT_RSA_SIGNATURE);
    }

    private byte[] getRsaCertificateTbs() throws Exception {
        return getResourceAsBytes(CERT_RSA_TBS);
    }

    public void test_Provider() throws Exception {
        final ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(errBuffer);

        for (Provider p : mX509Providers) {
            try {
                CertificateFactory f = CertificateFactory.getInstance("X.509", p);
                getPublicKey(f);
                getType(f);
                check_equals(f);
                check_toString(f);
                check_hashCode(f);
                checkValidity(f);
                getVersion(f);
                getSerialNumber(f);
                getIssuerDN(f);
                getIssuerX500Principal(f);
                getSubjectDN(f);
                getSubjectUniqueID(f);
                getSubjectX500Principal(f);
                getNotBeforeAndNotAfterDates(f);
                getSigAlgName(f);
                getSigAlgOID(f);
                getSigAlgParams(f);
                getIssuerUniqueID(f);
                getSubjectUniqueID(f);
                getKeyUsage(f);
                getExtendedKeyUsage(f);
                getBasicConstraints(f);
                getSubjectAlternativeNames(f);
                getSubjectAlternativeNames_IPV6(f);
                getSubjectAlternativeNames_InvalidIP(f);
                getSubjectAlternativeNames_Other(f);
                getSubjectAlternativeNames_Email(f);
                getSubjectAlternativeNames_DNS(f);
                getSubjectAlternativeNames_DirName(f);
                getSubjectAlternativeNames_URI(f);
                getSubjectAlternativeNames_RID(f);
                getSubjectAlternativeNames_None(f);
                getIssuerAlternativeNames(f);
                getTBSCertificate(f);
                getSignature(f);
                hasUnsupportedCriticalExtension(f);
                getEncoded(f);
                verify(f);
                generateCertificate_PEM_TrailingData(f);
                generateCertificate_DER_TrailingData(f);
                generateCertificates_X509_PEM(f);
                generateCertificates_X509_DER(f);
                generateCertificates_PKCS7_PEM(f);
                generateCertificates_PKCS7_DER(f);
                generateCertificates_Empty(f);
                generateCertificates_X509_PEM_TrailingData(f);
                generateCertificates_X509_DER_TrailingData(f);
                generateCertificates_PKCS7_PEM_TrailingData(f);
                generateCertificates_PKCS7_DER_TrailingData(f);
                test_Serialization(f);
            } catch (Throwable e) {
                out.append("Error encountered checking " + p.getName() + "\n");
                e.printStackTrace(out);
            }
        }

        out.flush();
        if (errBuffer.size() > 0) {
            throw new Exception("Errors encountered:\n\n" + errBuffer.toString() + "\n\n");
        }
    }

    private void getPublicKey(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        PublicKey expected = getRsaCertificatePublicKey();

        PublicKey actual = c.getPublicKey();
        assertEquals(expected, actual);
        assertEquals(Arrays.toString(expected.getEncoded()),
                     Arrays.toString(actual.getEncoded()));
    }

    private void getType(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        assertEquals("X.509", c.getType());
    }

    private void verify(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        PublicKey signer = getRsaCertificatePublicKey();

        c.verify(signer);

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair pair = kpg.generateKeyPair();
        PublicKey invalidKey = pair.getPublic();

        try {
            c.verify(invalidKey);
            fail("RSA signature should not verify");
        } catch (SignatureException expected) {
        }

        Provider[] providers = Security.getProviders("Signature." + c.getSigAlgName());
        for (Provider p : providers) {
            c.verify(signer, p.getName());

            try {
                c.verify(invalidKey, p.getName());
                fail("RSA signature should not verify");
            } catch (SignatureException expected) {
            }
        }
    }

    private void check_equals(CertificateFactory f) throws Exception {
        X509Certificate c1 = getCertificate(f, CERT_RSA);
        X509Certificate c2 = getCertificate(f, CERT_RSA);

        assertEquals(c1, c2);

        X509Certificate c3 = getCertificate(f, CERT_DSA);
        assertFalse(c1.equals(c3));
        assertFalse(c3.equals(c1));
    }

    private void check_toString(CertificateFactory f) throws Exception {
        X509Certificate c1 = getCertificate(f, CERT_RSA);

        String output1 = c1.toString();
        assertNotNull(output1);
        assertTrue(output1.length() > 0);

        X509Certificate c2 = getCertificate(f, CERT_RSA);
        assertEquals(c1.toString(), c2.toString());

        X509Certificate c3 = getCertificate(f, CERT_DSA);
        assertFalse(c3.toString().equals(c1.toString()));
    }

    private void check_hashCode(CertificateFactory f) throws Exception {
        X509Certificate c1 = getCertificate(f, CERT_RSA);
        X509Certificate c2 = getCertificate(f, CERT_RSA);

        assertEquals(c1.hashCode(), c2.hashCode());

        X509Certificate c3 = getCertificate(f, CERT_DSA);
        assertFalse(c3.hashCode() == c1.hashCode());
    }

    private void checkValidity(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        Calendar cal = Calendar.getInstance();
        Date[] dates = getRsaCertificateDates();

        /*
         * The certificate validity periods in the test certificate MUST lie
         * within the tested period. The API doesn't appear to allow any other
         * way to test this code path as an unprivileged user.
         */
        Date now = new Date();
        assertTrue(now.after(dates[0]));
        assertTrue(now.before(dates[1]));

        /* This assumes the script makes a long-lived cert. */
        c.checkValidity();

        /* A day after the start date. */
        cal.setTime(dates[0]);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        c.checkValidity(cal.getTime());

        /* A second before the start date. */
        cal.setTime(dates[1]);
        cal.add(Calendar.SECOND, -1);
        c.checkValidity(cal.getTime());

        try {
            cal.setTime(dates[0]);
            cal.add(Calendar.SECOND, -1);
            c.checkValidity(cal.getTime());
            fail();
        } catch (CertificateNotYetValidException expected) {
        }

        try {
            cal.setTime(dates[0]);
            cal.add(Calendar.MONTH, -6);
            c.checkValidity(cal.getTime());
            fail();
        } catch (CertificateNotYetValidException expected) {
        }

        try {
            cal.setTime(dates[1]);
            cal.add(Calendar.SECOND, 1);
            c.checkValidity(cal.getTime());
            fail();
        } catch (CertificateExpiredException expected) {
        }

        try {
            cal.setTime(dates[1]);
            cal.add(Calendar.YEAR, 1);
            c.checkValidity(cal.getTime());
            fail();
        } catch (CertificateExpiredException expected) {
        }
    }

    private void getVersion(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        assertEquals(3, c.getVersion());
    }

    private void getSerialNumber(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        BigInteger actual = getRsaCertificateSerial();

        assertEquals(actual, c.getSerialNumber());
    }

    private void getIssuerDN(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        Principal princ = c.getIssuerDN();
        if (StandardNames.IS_RI) {
            assertEquals("OU=NetOps, O=Genius.com Inc, L=San Mateo, ST=California, C=US",
                         princ.getName());
        } else {
            if ("BC".equals(f.getProvider().getName())) {
                // TODO: is it acceptable to have this in reverse order?
                assertEquals(f.getProvider().getName(),
                             "C=US,ST=California,L=San Mateo,O=Genius.com Inc,OU=NetOps",
                             princ.getName());
            } else {
                assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                             princ.getName());
            }
        }

        X509Certificate c2 = getCertificate(f, CERT_RSA);
        assertEquals(princ, c2.getIssuerDN());
    }

    private void getIssuerX500Principal(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        final byte[] expected = new byte[] {
                0x30, 0x60, 0x31, 0x0b, 0x30, 0x09, 0x06, 0x03, 0x55, 0x04, 0x06, 0x13,
                0x02, 0x55, 0x53, 0x31, 0x13, 0x30, 0x11, 0x06, 0x03, 0x55, 0x04, 0x08,
                0x13, 0x0a, 0x43, 0x61, 0x6c, 0x69, 0x66, 0x6f, 0x72, 0x6e, 0x69, 0x61,
                0x31, 0x12, 0x30, 0x10, 0x06, 0x03, 0x55, 0x04, 0x07, 0x13, 0x09, 0x53,
                0x61, 0x6e, 0x20, 0x4d, 0x61, 0x74, 0x65, 0x6f, 0x31, 0x17, 0x30, 0x15,
                0x06, 0x03, 0x55, 0x04, 0x0a, 0x13, 0x0e, 0x47, 0x65, 0x6e, 0x69, 0x75,
                0x73, 0x2e, 0x63, 0x6f, 0x6d, 0x20, 0x49, 0x6e, 0x63, 0x31, 0x0f, 0x30,
                0x0d, 0x06, 0x03, 0x55, 0x04, 0x0b, 0x13, 0x06, 0x4e, 0x65, 0x74, 0x4f,
                0x70, 0x73
        };
        X500Principal princ = c.getIssuerX500Principal();
        assertEquals(Arrays.toString(expected),
                     Arrays.toString(princ.getEncoded()));
        assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                     princ.getName());
        assertEquals("ou=netops,o=genius.com inc,l=san mateo,st=california,c=us",
                     princ.getName(X500Principal.CANONICAL));
        assertEquals("OU=NetOps, O=Genius.com Inc, L=San Mateo, ST=California, C=US",
                     princ.getName(X500Principal.RFC1779));
        assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                     princ.getName(X500Principal.RFC2253));

        X509Certificate c2 = getCertificate(f, CERT_RSA);
        assertEquals(princ, c2.getIssuerX500Principal());
    }

    private void getSubjectDN(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        Principal princ = c.getSubjectDN();
        if (StandardNames.IS_RI) {
            assertEquals("OU=NetOps, O=Genius.com Inc, L=San Mateo, ST=California, C=US",
                         princ.getName());
        } else {
            if ("BC".equals(f.getProvider().getName())) {
                // TODO: is it acceptable to have this in reverse order?
                assertEquals(f.getProvider().getName(),
                             "C=US,ST=California,L=San Mateo,O=Genius.com Inc,OU=NetOps",
                             princ.getName());
            } else {
                assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                             princ.getName());
            }
        }

        X509Certificate c2 = getCertificate(f, CERT_RSA);
        assertEquals(princ, c2.getSubjectDN());
    }

    private void getSubjectUniqueID(CertificateFactory f) throws Exception {
        /* This certificate has no unique ID. */
        X509Certificate c = getCertificate(f, CERT_RSA);
        assertNull(c.getSubjectUniqueID());

        // TODO: generate certificate that has a SubjectUniqueID field.
    }

    private void getIssuerUniqueID(CertificateFactory f) throws Exception {
        /* This certificate has no unique ID. */
        X509Certificate c = getCertificate(f, CERT_RSA);
        assertNull(c.getIssuerUniqueID());

        // TODO: generate certificate that has a IssuerUniqueID field.
    }

    private void getSubjectX500Principal(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        final byte[] expected = new byte[] {
                0x30, 0x60, 0x31, 0x0b, 0x30, 0x09, 0x06, 0x03, 0x55, 0x04, 0x06, 0x13,
                0x02, 0x55, 0x53, 0x31, 0x13, 0x30, 0x11, 0x06, 0x03, 0x55, 0x04, 0x08,
                0x13, 0x0a, 0x43, 0x61, 0x6c, 0x69, 0x66, 0x6f, 0x72, 0x6e, 0x69, 0x61,
                0x31, 0x12, 0x30, 0x10, 0x06, 0x03, 0x55, 0x04, 0x07, 0x13, 0x09, 0x53,
                0x61, 0x6e, 0x20, 0x4d, 0x61, 0x74, 0x65, 0x6f, 0x31, 0x17, 0x30, 0x15,
                0x06, 0x03, 0x55, 0x04, 0x0a, 0x13, 0x0e, 0x47, 0x65, 0x6e, 0x69, 0x75,
                0x73, 0x2e, 0x63, 0x6f, 0x6d, 0x20, 0x49, 0x6e, 0x63, 0x31, 0x0f, 0x30,
                0x0d, 0x06, 0x03, 0x55, 0x04, 0x0b, 0x13, 0x06, 0x4e, 0x65, 0x74, 0x4f,
                0x70, 0x73
        };
        X500Principal princ = c.getSubjectX500Principal();
        assertEquals(Arrays.toString(expected),
                     Arrays.toString(princ.getEncoded()));
        assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                     princ.getName());
        assertEquals("ou=netops,o=genius.com inc,l=san mateo,st=california,c=us",
                     princ.getName(X500Principal.CANONICAL));
        assertEquals("OU=NetOps, O=Genius.com Inc, L=San Mateo, ST=California, C=US",
                     princ.getName(X500Principal.RFC1779));
        assertEquals("OU=NetOps,O=Genius.com Inc,L=San Mateo,ST=California,C=US",
                     princ.getName(X500Principal.RFC2253));

        X509Certificate c2 = getCertificate(f, CERT_RSA);
        assertEquals(princ, c2.getSubjectX500Principal());
    }

    private static void assertDateEquals(Date date1, Date date2) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

        String result1 = formatter.format(date1);
        String result2 = formatter.format(date2);

        assertEquals(result1, result2);
    }

    private void getNotBeforeAndNotAfterDates(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        Date[] dates = getRsaCertificateDates();

        assertDateEquals(dates[0], c.getNotBefore());
        assertDateEquals(dates[1], c.getNotAfter());
    }

    private void getSigAlgName(CertificateFactory f) throws Exception {
        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_RSA);
            assertEquals("SHA1WITHRSA", c.getSigAlgName().toUpperCase(Locale.US));
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_DSA);
            assertEquals("SHA1WITHDSA", c.getSigAlgName().toUpperCase(Locale.US));
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_EC);
            if (StandardNames.IS_RI) {
                assertEquals("SHA1WITHECDSA", c.getSigAlgName().toUpperCase(Locale.US));
            } else {
                assertEquals("ECDSA", c.getSigAlgName().toUpperCase(Locale.US));
            }
        }
    }

    private void getSigAlgOID(CertificateFactory f) throws Exception {
        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_RSA);
            assertEquals("1.2.840.113549.1.1.5", c.getSigAlgOID());
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_DSA);
            assertEquals("1.2.840.10040.4.3", c.getSigAlgOID());
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_EC);
            assertEquals("1.2.840.10045.4.1", c.getSigAlgOID());
        }
    }

    private void getSigAlgParams(CertificateFactory f) throws Exception {
        {
            X509Certificate c = getCertificate(f, CERT_RSA);
            // RI appears to disagree
            if (StandardNames.IS_RI) {
                assertNull(f.getProvider().getName(), c.getSigAlgParams());
            } else {
                assertNotNull(f.getProvider().getName(), c.getSigAlgParams());
            }
        }

        {
            X509Certificate c = getCertificate(f, CERT_DSA);
            assertNull(f.getProvider().getName(), c.getSigAlgParams());
        }

        {
            X509Certificate c = getCertificate(f, CERT_EC);
            assertNull(f.getProvider().getName(), c.getSigAlgParams());
        }

        {
            X509Certificate c = getCertificate(f, CERT_SIGOPT);

            /* SEQUENCE, INTEGER 1 */
            final byte[] expected = new byte[] {
                    /* SEQUENCE, constructed, len=5 */
                    (byte) 0x30, (byte) 0x05,
                    /* Type=2, constructed, context-specific, len=3 */
                    (byte) 0xA2, (byte) 0x03,
                    /* INTEGER, len=1, value=1 */
                    (byte) 0x02, (byte) 0x01, (byte) 0x01,
            };

            final byte[] params = c.getSigAlgParams();
            assertNotNull(f.getProvider().getName(), params);
            assertEquals(Arrays.toString(expected), Arrays.toString(params));
        }
    }

    private void getKeyUsage(CertificateFactory f) throws Exception {
        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_RSA);
            boolean[] expected = new boolean[] {
                    true,  /* digitalSignature (0) */
                    true,  /* nonRepudiation   (1) */
                    true,  /* keyEncipherment  (2) */
                    false, /* dataEncipherment (3) */
                    false, /* keyAgreement     (4) */
                    false, /* keyCertSign      (5) */
                    false, /* cRLSign          (6) */
                    false, /* encipherOnly     (7) */
                    false, /* decipherOnly     (8) */
            };
            assertEquals(Arrays.toString(expected), Arrays.toString(c.getKeyUsage()));
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_DSA);
            boolean[] expected = new boolean[] {
                    false, /* digitalSignature (0) */
                    false, /* nonRepudiation   (1) */
                    true,  /* keyEncipherment  (2) */
                    true,  /* dataEncipherment (3) */
                    false, /* keyAgreement     (4) */
                    true,  /* keyCertSign      (5) */
                    true,  /* cRLSign          (6) */
                    true,  /* encipherOnly     (7) */
                    false, /* decipherOnly     (8) */
            };
            boolean[] actual = c.getKeyUsage();
            assertEquals(9, actual.length);
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }

        {
            /* The test certificate is sha1WithRSAEncryption */
            X509Certificate c = getCertificate(f, CERT_EC);
            boolean[] expected = new boolean[] {
                    false, /* digitalSignature (0) */
                    false, /* nonRepudiation   (1) */
                    false, /* keyEncipherment  (2) */
                    false, /* dataEncipherment (3) */
                    true,  /* keyAgreement     (4) */
                    false, /* keyCertSign      (5) */
                    false, /* cRLSign          (6) */
                    false, /* encipherOnly     (7) */
                    true,  /* decipherOnly     (8) */
            };
            boolean[] actual = c.getKeyUsage();
            assertEquals(9, actual.length);
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }

        {
            /* All the bits are set in addition to some extra ones. */
            X509Certificate c = getCertificate(f, CERT_KEYUSAGE_EXTRALONG);
            boolean[] expected = new boolean[] {
                    true,  /* digitalSignature (0) */
                    true,  /* nonRepudiation   (1) */
                    true,  /* keyEncipherment  (2) */
                    true,  /* dataEncipherment (3) */
                    true,  /* keyAgreement     (4) */
                    true,  /* keyCertSign      (5) */
                    true,  /* cRLSign          (6) */
                    true,  /* encipherOnly     (7) */
                    true,  /* decipherOnly     (8) */
                    true,  /* ?????            (9) */
                    true,  /* ?????           (10) */
            };
            boolean[] actual = c.getKeyUsage();
            assertEquals(11, actual.length);
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
    }

    private void getExtendedKeyUsage(CertificateFactory f) throws Exception {
        {
            /* No ExtendedKeyUsage section */
            final X509Certificate c = getCertificate(f, CERT_RSA);
            List<String> actual = c.getExtendedKeyUsage();
            assertNull(actual);
        }

        {
            /* ExtendedKeyUsage section with one entry of OID 1.2.3.4 */
            final X509Certificate c = getCertificate(f, CERT_EXTENDEDKEYUSAGE);
            List<String> actual = c.getExtendedKeyUsage();
            assertNotNull(actual);
            assertEquals(1, actual.size());
            assertEquals("1.2.3.4", actual.get(0));
        }
    }

    private void getBasicConstraints(CertificateFactory f) throws Exception {
        /* Non-CA cert with no pathLenConstraint */
        {
            final X509Certificate c = getCertificate(f, CERT_RSA);
            assertEquals(f.getProvider().getName(), -1, c.getBasicConstraints());
        }

        /* Non-CA cert with pathLenConstraint */
        {
            final X509Certificate c = getCertificate(f, CERT_USERWITHPATHLEN);
            assertEquals(f.getProvider().getName(), -1, c.getBasicConstraints());
        }

        /* CA cert with no pathLenConstraint */
        {
            final X509Certificate c = getCertificate(f, CERT_CA);
            assertEquals(f.getProvider().getName(), Integer.MAX_VALUE, c.getBasicConstraints());
        }

        /* CA cert with pathLenConstraint=10 */
        {
            final X509Certificate c = getCertificate(f, CERT_CAWITHPATHLEN);
            assertEquals(f.getProvider().getName(), 10, c.getBasicConstraints());
        }
    }

    /** Encoding of:  OID:1.2.3.4, UTF8:test1 */
    private static byte[] getOIDTestBytes() {
        if (StandardNames.IS_RI) {
            return new byte[] { 0x30, 0x10, 0x06, 0x03, 0x2a, 0x03, 0x04, (byte) 0xa0,
                    0x09, (byte) 0xa0, 0x07, 0x0c, 0x05, 0x74, 0x65, 0x73, 0x74, 0x31 };
        } else {
            return new byte[] { (byte) 0xa0, 0x0e, 0x06, 0x03, 0x2a, 0x03, 0x04,
                    (byte) 0xa0, 0x07, 0x0c, 0x05, 0x74, 0x65, 0x73, 0x74, 0x31 };
        }
    }

    private void getSubjectAlternativeNames(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        checkAlternativeNames(f, col);
    }

    private void checkAlternativeNames(CertificateFactory f, Collection<List<?>> col) throws Exception {
        assertNotNull(col);

        /* Check to see that the Collection is unmodifiable. */
        {
            try {
                col.add(new ArrayList<Object>());
                fail("should be an unmodifiable list");
            } catch (UnsupportedOperationException expected) {
            }
        }

        /*
         * There should be 9 types of alternative names in this test
         * certificate.
         */
        boolean[] typesFound = new boolean[9];

        for (List<?> item : col) {
            /* Check to see that the List is unmodifiable. */
            {
                try {
                    item.remove(0);
                    fail("should be an unmodifiable list");
                } catch (UnsupportedOperationException expected) {
                }
            }

            assertTrue(item.get(0) instanceof Integer);
            int type = (Integer) item.get(0);
            typesFound[type] = true;

            switch (type) {
            case 0: /* OtherName */
                final byte[] der = getOIDTestBytes();
                assertEquals(Arrays.toString(der), Arrays.toString((byte[]) item.get(1)));
                break;
            case 1: /* rfc822Name: IA5String */
                assertEquals("x509@example.com", (String) item.get(1));
                break;
            case 2: /* dNSName: IA5String */
                assertEquals("x509.example.com", (String) item.get(1));
                break;
            case 3: /* x400Address: ORAddress */
                assertEquals("UNSUPPORTED", (String) item.get(1));
                break;
            case 4: /* directoryName: Name */
                if ("BC".equals(f.getProvider().getName())) {
                    // Bouncycastle doesn't parse T61String as UTF-8 like the RI, libcore, or OpenSSL.
                    byte[] bytes = "CN=∆ƒ,OU=Über Frîends,O=Awesome Dudes,C=US".getBytes("UTF-8");
                    String string = new String(bytes, 0);
                    assertEquals(string, (String) item.get(1));
                } else {
                    assertEquals("CN=∆ƒ,OU=Über Frîends,O=Awesome Dudes,C=US", (String) item.get(1));
                }
                break;
            case 5: /* ediPartyName */
                assertEquals("UNSUPPORTED", Arrays.toString((byte[]) item.get(1)));
                break;
            case 6: /* uniformResourceIdentifier: IA5String */
                assertEquals("http://www.example.com/?q=awesomeness", (String) item.get(1));
                break;
            case 7: /* iPAddress */
                assertEquals("192.168.0.1", (String) item.get(1));
                break;
            case 8:
                assertEquals("1.2.3.4", (String) item.get(1));
                break;
            }
        }

        Set<Integer> missing = new HashSet<Integer>();
        for (int i = 0; i < typesFound.length; i++) {
            if (!typesFound[i]) {
                missing.add(i);
            }
        }

        // TODO: fix X.400 names and ediPartyName
        missing.remove(3);
        missing.remove(5);

        if (!missing.isEmpty()) {
            fail("Missing types: " + Arrays.toString(missing.toArray(new Integer[missing.size()])));
        }
    }

    private void getSubjectAlternativeNames_IPV6(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_IPV6);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(7 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        // RI doesn't apply all the IPv6 shortening rules
        if (StandardNames.IS_RI) {
            assertEquals("2001:db8:0:0:0:ff00:42:8329", (String) item.get(1));
        } else {
            assertEquals("2001:db8::ff00:42:8329", (String) item.get(1));
        }
    }

    private void getSubjectAlternativeNames_InvalidIP(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_INVALIDIP);
        Collection<List<?>> col = c.getSubjectAlternativeNames();
        assertNull(col);
    }

    private void getSubjectAlternativeNames_Other(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_OTHER);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(0 == (Integer) item.get(0));

        /* OID:1.2.3.4, UTF8:test1 */
        final byte[] der = getOIDTestBytes();
        final byte[] actual = (byte[]) item.get(1);
        assertEquals(Arrays.toString(der), Arrays.toString(actual));

        /* Make sure the byte[] array isn't modified by our test. */
        {
            actual[0] ^= (byte) 0xFF;
            byte[] actual2 = (byte[]) c.getSubjectAlternativeNames().iterator().next().get(1);

            if (!StandardNames.IS_RI) {
                assertEquals(Arrays.toString(der), Arrays.toString(actual2));
            } else {
                /* RI is broken here. */
                assertEquals(Arrays.toString(actual), Arrays.toString(actual2));
            }
        }
    }

    private void getSubjectAlternativeNames_Email(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_EMAIL);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(1 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        assertEquals("x509@example.com", (String) item.get(1));
    }

    private void getSubjectAlternativeNames_DNS(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_DNS);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(2 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        assertEquals("x509.example.com", (String) item.get(1));
    }

    private void getSubjectAlternativeNames_DirName(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_DIRNAME);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(String.valueOf((Integer) item.get(0)), 4 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        if ("BC".equals(f.getProvider().getName())) {
            // Bouncycastle doesn't parse T61String as UTF-8 like the RI, libcore, or OpenSSL.
            byte[] bytes = "CN=∆ƒ,OU=Über Frîends,O=Awesome Dudes,C=US".getBytes("UTF-8");
            String string = new String(bytes, 0);
            assertEquals(string, (String) item.get(1));
        } else {
            assertEquals("CN=∆ƒ,OU=Über Frîends,O=Awesome Dudes,C=US", (String) item.get(1));
        }
    }

    private void getSubjectAlternativeNames_URI(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_URI);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(6 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        assertEquals("http://www.example.com/?q=awesomeness", (String) item.get(1));
    }

    private void getSubjectAlternativeNames_RID(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_RID);
        Collection<List<?>> col = c.getSubjectAlternativeNames();

        assertNotNull(f.getProvider().getName(), col);

        assertEquals(1, col.size());
        List<?> item = col.iterator().next();

        assertTrue(item.get(0) instanceof Integer);
        assertTrue(8 == (Integer) item.get(0));

        assertTrue(item.get(1) instanceof String);
        assertEquals("1.2.3.4", (String) item.get(1));
    }

    private void getSubjectAlternativeNames_None(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_ALT_NONE);
        Collection<List<?>> col = c.getSubjectAlternativeNames();
        assertNull(col);
    }

    private void getIssuerAlternativeNames(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        Collection<List<?>> col = c.getIssuerAlternativeNames();

        checkAlternativeNames(f, col);
    }

    private void getSignature(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        assertEquals(Arrays.toString(getRsaCertificateSignature()),
                     Arrays.toString(c.getSignature()));
    }

    private void getTBSCertificate(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        assertEquals(Arrays.toString(getRsaCertificateTbs()),
                     Arrays.toString(c.getTBSCertificate()));
    }

    private void hasUnsupportedCriticalExtension(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);
        assertFalse(c.hasUnsupportedCriticalExtension());

        X509Certificate unsupported = getCertificate(f, CERT_UNSUPPORTED);
        assertTrue(unsupported.hasUnsupportedCriticalExtension());
    }

    private void getEncoded(CertificateFactory f) throws Exception {
        X509Certificate c = getCertificate(f, CERT_RSA);

        byte[] cBytes = getResourceAsBytes(CERT_RSA);

        assertEquals(Arrays.toString(cBytes), Arrays.toString(c.getEncoded()));
    }

    private void generateCertificate_PEM_TrailingData(CertificateFactory f) throws Exception {
        byte[] certsBytes = getResourceAsBytes(CERTS_X509_PEM);
        byte[] certsTwice = new byte[certsBytes.length * 2];
        System.arraycopy(certsBytes, 0, certsTwice, 0, certsBytes.length);
        System.arraycopy(certsBytes, 0, certsTwice, certsBytes.length, certsBytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(certsTwice);

        assertEquals(certsBytes.length * 2, bais.available());
        X509Certificate cert1 = (X509Certificate) f.generateCertificate(bais);
        // TODO: If we had a single PEM certificate, we could know exact bytes.
        assertTrue(certsBytes.length < bais.available());
    }

    private void generateCertificate_DER_TrailingData(CertificateFactory f) throws Exception {
        byte[] cert1Bytes = getResourceAsBytes(CERT_RSA);
        byte[] cert1WithTrailing = new byte[cert1Bytes.length * 2];
        System.arraycopy(cert1Bytes, 0, cert1WithTrailing, 0, cert1Bytes.length);
        System.arraycopy(cert1Bytes, 0, cert1WithTrailing, cert1Bytes.length, cert1Bytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(cert1WithTrailing);

        assertEquals(cert1Bytes.length * 2, bais.available());
        X509Certificate cert1 = (X509Certificate) f.generateCertificate(bais);
        assertEquals(cert1Bytes.length, bais.available());
    }

    private void generateCertificates_X509_DER(CertificateFactory f) throws Exception {
        /* DER-encoded list of certificates */
        Collection<? extends X509Certificate> certs = getCertificates(f, CERTS_X509_DER);
        assertNotNull(certs);
        assertEquals(2, certs.size());
    }

    private void generateCertificates_X509_PEM(CertificateFactory f) throws Exception {
        /* PEM-encoded list of certificates */
        Collection<? extends X509Certificate> certs = getCertificates(f, CERTS_X509_PEM);
        assertNotNull(certs);
        assertEquals(2, certs.size());
    }

    private void generateCertificates_PKCS7_PEM(CertificateFactory f) throws Exception {
        /* PEM-encoded PKCS7 bag of certificates */
        Collection<? extends X509Certificate> certs = getCertificates(f, CERTS_PKCS7_PEM);
        assertNotNull(certs);
        if ("BC".equals(f.getProvider().getName())) {
            // Bouncycastle is broken
            assertEquals(0, certs.size());
        } else {
            assertEquals(2, certs.size());
        }
    }

    private void generateCertificates_PKCS7_DER(CertificateFactory f) throws Exception {
        /* DER-encoded PKCS7 bag of certificates */
        Collection<? extends X509Certificate> certs = getCertificates(f, CERTS_PKCS7_DER);
        assertNotNull(certs);
        assertEquals(2, certs.size());
    }

    private void generateCertificates_Empty(CertificateFactory f) throws Exception {
        final InputStream is = new ByteArrayInputStream(new byte[0]);

        final Collection<? extends Certificate> certs = f.generateCertificates(is);

        assertNotNull(certs);
        assertEquals(0, certs.size());
    }

    private void generateCertificates_X509_PEM_TrailingData(CertificateFactory f) throws Exception {
        byte[] certBytes = getResourceAsBytes(CERTS_X509_PEM);
        byte[] certsPlusExtra = new byte[certBytes.length + 4096];
        System.arraycopy(certBytes, 0, certsPlusExtra, 0, certBytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(certsPlusExtra);

        assertEquals(certsPlusExtra.length, bais.available());

        // RI is broken
        try {
            Collection<? extends X509Certificate> certs = (Collection<? extends X509Certificate>)
                    f.generateCertificates(bais);
            if (StandardNames.IS_RI) {
                fail("RI fails on this test.");
            }
        } catch (CertificateParsingException e) {
            if (StandardNames.IS_RI) {
                return;
            }
            throw e;
        }

        // Bouncycastle is broken
        if ("BC".equals(f.getProvider().getName())) {
            assertEquals(0, bais.available());
        } else {
            assertEquals(4096, bais.available());
        }
    }

    private void generateCertificates_X509_DER_TrailingData(CertificateFactory f) throws Exception {
        byte[] certBytes = getResourceAsBytes(CERTS_X509_DER);
        byte[] certsPlusExtra = new byte[certBytes.length + 4096];
        System.arraycopy(certBytes, 0, certsPlusExtra, 0, certBytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(certsPlusExtra);

        assertEquals(certsPlusExtra.length, bais.available());

        // RI is broken
        try {
            Collection<? extends X509Certificate> certs = (Collection<? extends X509Certificate>)
                    f.generateCertificates(bais);
            if (StandardNames.IS_RI) {
                fail("RI fails on this test.");
            }
        } catch (CertificateParsingException e) {
            if (StandardNames.IS_RI) {
                return;
            }
            throw e;
        }

        // Bouncycastle is broken
        if ("BC".equals(f.getProvider().getName())) {
            assertEquals(0, bais.available());
        } else {
            assertEquals(4096, bais.available());
        }
    }

    private void generateCertificates_PKCS7_PEM_TrailingData(CertificateFactory f) throws Exception {
        byte[] certBytes = getResourceAsBytes(CERTS_PKCS7_PEM);
        byte[] certsPlusExtra = new byte[certBytes.length + 4096];
        System.arraycopy(certBytes, 0, certsPlusExtra, 0, certBytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(certsPlusExtra);

        assertEquals(certsPlusExtra.length, bais.available());
        Collection<? extends X509Certificate> certs = (Collection<? extends X509Certificate>)
                f.generateCertificates(bais);

        // Bouncycastle is broken
        if ("BC".equals(f.getProvider().getName())) {
            assertEquals(0, bais.available());
        } else {
            assertEquals(4096, bais.available());
        }
    }

    private void generateCertificates_PKCS7_DER_TrailingData(CertificateFactory f) throws Exception {
        byte[] certBytes = getResourceAsBytes(CERTS_PKCS7_DER);
        byte[] certsPlusExtra = new byte[certBytes.length + 4096];
        System.arraycopy(certBytes, 0, certsPlusExtra, 0, certBytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(certsPlusExtra);

        assertEquals(certsPlusExtra.length, bais.available());
        Collection<? extends X509Certificate> certs = (Collection<? extends X509Certificate>)
                f.generateCertificates(bais);

        // RI is broken
        if (StandardNames.IS_RI) {
            assertEquals(0, bais.available());
        } else {
            assertEquals(4096, bais.available());
        }
    }

    private void test_Serialization(CertificateFactory f) throws Exception {
        for (String certName : VARIOUS_CERTS) {
            X509Certificate expected = getCertificate(f, certName);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            try {
                oos.writeObject(expected);
            } finally {
                oos.close();
            }

            byte[] certBytes = baos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(certBytes);
            try {
                ObjectInputStream ois = new ObjectInputStream(bais);

                X509Certificate actual = (X509Certificate) ois.readObject();

                assertEquals(certName, expected, actual);
            } finally {
                bais.close();
            }
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mX509Providers = Security.getProviders("CertificateFactory.X509");
    }
}
