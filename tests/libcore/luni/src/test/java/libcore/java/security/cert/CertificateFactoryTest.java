/*
 * Copyright (C) 2010 The Android Open Source Project
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

import com.android.org.bouncycastle.asn1.x509.BasicConstraints;
import com.android.org.bouncycastle.asn1.x509.X509Extensions;
import com.android.org.bouncycastle.x509.X509V3CertificateGenerator;
import com.android.org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import com.android.org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.security.auth.x500.X500Principal;

import junit.framework.TestCase;
import libcore.java.security.StandardNames;

public class CertificateFactoryTest extends TestCase {

    private static final String VALID_CERTIFICATE_PEM =
            "-----BEGIN CERTIFICATE-----\n"
            + "MIIDITCCAoqgAwIBAgIQL9+89q6RUm0PmqPfQDQ+mjANBgkqhkiG9w0BAQUFADBM\n"
            + "MQswCQYDVQQGEwJaQTElMCMGA1UEChMcVGhhd3RlIENvbnN1bHRpbmcgKFB0eSkg\n"
            + "THRkLjEWMBQGA1UEAxMNVGhhd3RlIFNHQyBDQTAeFw0wOTEyMTgwMDAwMDBaFw0x\n"
            + "MTEyMTgyMzU5NTlaMGgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlh\n"
            + "MRYwFAYDVQQHFA1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKFApHb29nbGUgSW5jMRcw\n"
            + "FQYDVQQDFA53d3cuZ29vZ2xlLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkC\n"
            + "gYEA6PmGD5D6htffvXImttdEAoN4c9kCKO+IRTn7EOh8rqk41XXGOOsKFQebg+jN\n"
            + "gtXj9xVoRaELGYW84u+E593y17iYwqG7tcFR39SDAqc9BkJb4SLD3muFXxzW2k6L\n"
            + "05vuuWciKh0R73mkszeK9P4Y/bz5RiNQl/Os/CRGK1w7t0UCAwEAAaOB5zCB5DAM\n"
            + "BgNVHRMBAf8EAjAAMDYGA1UdHwQvMC0wK6ApoCeGJWh0dHA6Ly9jcmwudGhhd3Rl\n"
            + "LmNvbS9UaGF3dGVTR0NDQS5jcmwwKAYDVR0lBCEwHwYIKwYBBQUHAwEGCCsGAQUF\n"
            + "BwMCBglghkgBhvhCBAEwcgYIKwYBBQUHAQEEZjBkMCIGCCsGAQUFBzABhhZodHRw\n"
            + "Oi8vb2NzcC50aGF3dGUuY29tMD4GCCsGAQUFBzAChjJodHRwOi8vd3d3LnRoYXd0\n"
            + "ZS5jb20vcmVwb3NpdG9yeS9UaGF3dGVfU0dDX0NBLmNydDANBgkqhkiG9w0BAQUF\n"
            + "AAOBgQCfQ89bxFApsb/isJr/aiEdLRLDLE5a+RLizrmCUi3nHX4adpaQedEkUjh5\n"
            + "u2ONgJd8IyAPkU0Wueru9G2Jysa9zCRo1kNbzipYvzwY4OA8Ys+WAi0oR1A04Se6\n"
            + "z5nRUP8pJcA2NhUzUnC+MY+f6H/nEQyNv4SgQhqAibAxWEEHXw==\n"
            + "-----END CERTIFICATE-----\n";

    private static final String VALID_CERTIFICATE_PEM_CRLF =
            "-----BEGIN CERTIFICATE-----\r\n"
            + "MIIDITCCAoqgAwIBAgIQL9+89q6RUm0PmqPfQDQ+mjANBgkqhkiG9w0BAQUFADBM\r\n"
            + "MQswCQYDVQQGEwJaQTElMCMGA1UEChMcVGhhd3RlIENvbnN1bHRpbmcgKFB0eSkg\r\n"
            + "THRkLjEWMBQGA1UEAxMNVGhhd3RlIFNHQyBDQTAeFw0wOTEyMTgwMDAwMDBaFw0x\r\n"
            + "MTEyMTgyMzU5NTlaMGgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlh\r\n"
            + "MRYwFAYDVQQHFA1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKFApHb29nbGUgSW5jMRcw\r\n"
            + "FQYDVQQDFA53d3cuZ29vZ2xlLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkC\r\n"
            + "gYEA6PmGD5D6htffvXImttdEAoN4c9kCKO+IRTn7EOh8rqk41XXGOOsKFQebg+jN\r\n"
            + "gtXj9xVoRaELGYW84u+E593y17iYwqG7tcFR39SDAqc9BkJb4SLD3muFXxzW2k6L\r\n"
            + "05vuuWciKh0R73mkszeK9P4Y/bz5RiNQl/Os/CRGK1w7t0UCAwEAAaOB5zCB5DAM\r\n"
            + "BgNVHRMBAf8EAjAAMDYGA1UdHwQvMC0wK6ApoCeGJWh0dHA6Ly9jcmwudGhhd3Rl\r\n"
            + "LmNvbS9UaGF3dGVTR0NDQS5jcmwwKAYDVR0lBCEwHwYIKwYBBQUHAwEGCCsGAQUF\r\n"
            + "BwMCBglghkgBhvhCBAEwcgYIKwYBBQUHAQEEZjBkMCIGCCsGAQUFBzABhhZodHRw\r\n"
            + "Oi8vb2NzcC50aGF3dGUuY29tMD4GCCsGAQUFBzAChjJodHRwOi8vd3d3LnRoYXd0\r\n"
            + "ZS5jb20vcmVwb3NpdG9yeS9UaGF3dGVfU0dDX0NBLmNydDANBgkqhkiG9w0BAQUF\r\n"
            + "AAOBgQCfQ89bxFApsb/isJr/aiEdLRLDLE5a+RLizrmCUi3nHX4adpaQedEkUjh5\r\n"
            + "u2ONgJd8IyAPkU0Wueru9G2Jysa9zCRo1kNbzipYvzwY4OA8Ys+WAi0oR1A04Se6\r\n"
            + "z5nRUP8pJcA2NhUzUnC+MY+f6H/nEQyNv4SgQhqAibAxWEEHXw==\r\n"
            + "-----END CERTIFICATE-----\r\n";

    private static final byte[] VALID_CERTIFICATE_PEM_HEADER = "-----BEGIN CERTIFICATE-----\n"
            .getBytes();

    private static final byte[] VALID_CERTIFICATE_PEM_DATA =
             ("MIIDITCCAoqgAwIBAgIQL9+89q6RUm0PmqPfQDQ+mjANBgkqhkiG9w0BAQUFADBM"
            + "MQswCQYDVQQGEwJaQTElMCMGA1UEChMcVGhhd3RlIENvbnN1bHRpbmcgKFB0eSkg"
            + "THRkLjEWMBQGA1UEAxMNVGhhd3RlIFNHQyBDQTAeFw0wOTEyMTgwMDAwMDBaFw0x"
            + "MTEyMTgyMzU5NTlaMGgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlh"
            + "MRYwFAYDVQQHFA1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKFApHb29nbGUgSW5jMRcw"
            + "FQYDVQQDFA53d3cuZ29vZ2xlLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkC"
            + "gYEA6PmGD5D6htffvXImttdEAoN4c9kCKO+IRTn7EOh8rqk41XXGOOsKFQebg+jN"
            + "gtXj9xVoRaELGYW84u+E593y17iYwqG7tcFR39SDAqc9BkJb4SLD3muFXxzW2k6L"
            + "05vuuWciKh0R73mkszeK9P4Y/bz5RiNQl/Os/CRGK1w7t0UCAwEAAaOB5zCB5DAM"
            + "BgNVHRMBAf8EAjAAMDYGA1UdHwQvMC0wK6ApoCeGJWh0dHA6Ly9jcmwudGhhd3Rl"
            + "LmNvbS9UaGF3dGVTR0NDQS5jcmwwKAYDVR0lBCEwHwYIKwYBBQUHAwEGCCsGAQUF"
            + "BwMCBglghkgBhvhCBAEwcgYIKwYBBQUHAQEEZjBkMCIGCCsGAQUFBzABhhZodHRw"
            + "Oi8vb2NzcC50aGF3dGUuY29tMD4GCCsGAQUFBzAChjJodHRwOi8vd3d3LnRoYXd0"
            + "ZS5jb20vcmVwb3NpdG9yeS9UaGF3dGVfU0dDX0NBLmNydDANBgkqhkiG9w0BAQUF"
            + "AAOBgQCfQ89bxFApsb/isJr/aiEdLRLDLE5a+RLizrmCUi3nHX4adpaQedEkUjh5"
            + "u2ONgJd8IyAPkU0Wueru9G2Jysa9zCRo1kNbzipYvzwY4OA8Ys+WAi0oR1A04Se6"
            + "z5nRUP8pJcA2NhUzUnC+MY+f6H/nEQyNv4SgQhqAibAxWEEHXw==").getBytes();

    private static final byte[] VALID_CERTIFICATE_PEM_FOOTER = "\n-----END CERTIFICATE-----\n"
            .getBytes();

    private static final String INVALID_CERTIFICATE_PEM =
            "-----BEGIN CERTIFICATE-----\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n"
            + "AAAAAAAA\n"
            + "-----END CERTIFICATE-----";

    public void test_generateCertificate() throws Exception {
        Provider[] providers = Security.getProviders("CertificateFactory.X509");
        for (Provider p : providers) {
            CertificateFactory cf = CertificateFactory.getInstance("X509", p);
            try {
                test_generateCertificate(cf);
                test_generateCertificate_InputStream_Offset_Correct(cf);
                test_generateCertificate_InputStream_Empty(cf);
                test_generateCertificate_InputStream_InvalidStart_Failure(cf);
                test_generateCertificate_AnyLineLength_Success(cf);
            } catch (Throwable e) {
                throw new Exception("Problem testing " + p.getName(), e);
            }
        }
    }

    private void test_generateCertificate(CertificateFactory cf) throws Exception {
        {
            byte[] valid = VALID_CERTIFICATE_PEM.getBytes();
            Certificate c = cf.generateCertificate(new ByteArrayInputStream(valid));
            assertNotNull(c);
        }

        {
            byte[] valid = VALID_CERTIFICATE_PEM_CRLF.getBytes();
            Certificate c = cf.generateCertificate(new ByteArrayInputStream(valid));
            assertNotNull(c);
        }

        try {
            byte[] invalid = INVALID_CERTIFICATE_PEM.getBytes();
            cf.generateCertificate(new ByteArrayInputStream(invalid));
            fail();
        } catch (CertificateException expected) {
        }
    }

    /*
     * Checks all possible line lengths for PEM input data.
     */
    private void test_generateCertificate_AnyLineLength_Success(CertificateFactory cf)
            throws Exception {
        // RI barfs on this
        if (StandardNames.IS_RI) {
            return;
        }

        int lineLength = 1;
        int maxLineLength = VALID_CERTIFICATE_PEM_DATA.length;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(VALID_CERTIFICATE_PEM_HEADER);
        int offset = 0;
        while (lineLength < (maxLineLength - 4)) {
            int end = offset + lineLength;
            if (end > VALID_CERTIFICATE_PEM_DATA.length) {
                end = VALID_CERTIFICATE_PEM_DATA.length;
            }
            baos.write(Arrays.copyOfRange(VALID_CERTIFICATE_PEM_DATA, offset, end));
            baos.write('\n');
            offset += lineLength;
            if (offset >= maxLineLength) {
                baos.write(VALID_CERTIFICATE_PEM_FOOTER);
                try {
                    Certificate c =
                            cf.generateCertificate(new ByteArrayInputStream(baos.toByteArray()));
                    assertNotNull(c);
                } catch (Exception e) {
                    throw new Exception("Fail at line length " + lineLength, e);
                }
                baos.reset();
                baos.write(VALID_CERTIFICATE_PEM_HEADER);
                offset = 0;
            } else {
                lineLength++;
            }
        }

    }

    private void test_generateCertificate_InputStream_Empty(CertificateFactory cf) throws Exception {
        try {
            Certificate c = cf.generateCertificate(new ByteArrayInputStream(new byte[0]));
            if (!"BC".equals(cf.getProvider().getName())) {
                fail("should throw CertificateException: " + cf.getProvider().getName());
            }
            assertNull(c);
        } catch (CertificateException e) {
            if ("BC".equals(cf.getProvider().getName())) {
                fail("should return null: " + cf.getProvider().getName());
            }
        }
    }

    private void test_generateCertificate_InputStream_InvalidStart_Failure(CertificateFactory cf)
            throws Exception {
        try {
            Certificate c = cf.generateCertificate(new ByteArrayInputStream(
                    "-----BEGIN CERTIFICATE-----".getBytes()));
            if (!"BC".equals(cf.getProvider().getName())) {
                fail("should throw CertificateException: " + cf.getProvider().getName());
            }
            assertNull(c);
        } catch (CertificateException expected) {
            if ("BC".equals(cf.getProvider().getName())) {
                fail("should return null: " + cf.getProvider().getName());
            }
        }
    }

    private void test_generateCertificate_InputStream_Offset_Correct(CertificateFactory cf)
            throws Exception {
        byte[] valid = VALID_CERTIFICATE_PEM.getBytes();

        byte[] doubleCertificateData = new byte[valid.length * 2];
        System.arraycopy(valid, 0, doubleCertificateData, 0, valid.length);
        System.arraycopy(valid, 0, doubleCertificateData, valid.length, valid.length);
        MeasuredInputStream certStream = new MeasuredInputStream(new ByteArrayInputStream(
                doubleCertificateData));
        Certificate certificate = cf.generateCertificate(certStream);
        assertNotNull(certificate);
        assertEquals(valid.length, certStream.getCount());
    }

    /**
     * Proxy that counts the number of bytes read from an InputStream.
     */
    private static class MeasuredInputStream extends InputStream {
        private long mCount = 0;

        private long mMarked = 0;

        private InputStream mStream;

        public MeasuredInputStream(InputStream is) {
            mStream = is;
        }

        public long getCount() {
            return mCount;
        }

        @Override
        public int read() throws IOException {
            int nextByte = mStream.read();
            mCount++;
            return nextByte;
        }

        @Override
        public int read(byte[] buffer) throws IOException {
            int count = mStream.read(buffer);
            mCount += count;
            return count;
        }

        @Override
        public int read(byte[] buffer, int offset, int length) throws IOException {
            int count = mStream.read(buffer, offset, length);
            mCount += count;
            return count;
        }

        @Override
        public long skip(long byteCount) throws IOException {
            long count = mStream.skip(byteCount);
            mCount += count;
            return count;
        }

        @Override
        public int available() throws IOException {
            return mStream.available();
        }

        @Override
        public void close() throws IOException {
            mStream.close();
        }

        @Override
        public void mark(int readlimit) {
            mMarked = mCount;
            mStream.mark(readlimit);
        }

        @Override
        public boolean markSupported() {
            return mStream.markSupported();
        }

        @Override
        public synchronized void reset() throws IOException {
            mCount = mMarked;
            mStream.reset();
        }
    }

    /* CertPath tests */
    public void testGenerateCertPath() throws Exception {
        KeyHolder ca = generateCertificate(true, null);
        KeyHolder cert1 = generateCertificate(true, ca);
        KeyHolder cert2 = generateCertificate(false, cert1);
        KeyHolder cert3 = generateCertificate(false, cert2);

        List<X509Certificate> certs = new ArrayList<X509Certificate>();
        certs.add(cert3.certificate);
        certs.add(cert2.certificate);
        certs.add(cert1.certificate);

        List<X509Certificate> duplicatedCerts = new ArrayList<X509Certificate>(certs);
        duplicatedCerts.add(cert2.certificate);

        Provider[] providers = Security.getProviders("CertificateFactory.X509");
        for (Provider p : providers) {
            final CertificateFactory cf = CertificateFactory.getInstance("X.509", p);

            // Duplicate certificates can cause an exception.
            {
                final CertPath duplicatedPath = cf.generateCertPath(duplicatedCerts);
                try {
                    duplicatedPath.getEncoded();
                    if (StandardNames.IS_RI) {
                        fail("duplicate certificates should cause failure: " + p.getName());
                    }
                } catch (CertificateEncodingException expected) {
                    if (!StandardNames.IS_RI) {
                        fail("duplicate certificates should pass: " + p.getName());
                    }
                }
            }

            testCertPathEncoding(cf, certs, null);

            /* Make sure all encoding entries are the same. */
            final Iterator<String> it1 = cf.getCertPathEncodings();
            final Iterator<String> it2 = cf.generateCertPath(certs).getEncodings();
            for (;;) {
                assertEquals(p.getName(), it1.hasNext(), it2.hasNext());
                if (!it1.hasNext()) {
                    break;
                }

                String encoding = it1.next();
                assertEquals(p.getName(), encoding, it2.next());

                try {
                    it1.remove();
                    fail("Should not be able to remove from iterator");
                } catch (UnsupportedOperationException expected) {
                }

                try {
                    it2.remove();
                    fail("Should not be able to remove from iterator");
                } catch (UnsupportedOperationException expected) {
                }

                /* Now test using this encoding. */
                testCertPathEncoding(cf, certs, encoding);
            }
        }
    }

    private void testCertPathEncoding(CertificateFactory cf, List<X509Certificate> expectedCerts,
            String encoding) throws Exception {
        final String providerName = cf.getProvider().getName() + "[" + encoding + "]";

        final CertPath pathFromList = cf.generateCertPath(expectedCerts);

        // Create a copy we can modify and discard.
        final byte[] encodedCopy;
        if (encoding == null) {
            encodedCopy = pathFromList.getEncoded();
            assertNotNull(providerName, encodedCopy);

            // check idempotence
            assertEquals(providerName, Arrays.toString(pathFromList.getEncoded()),
                    Arrays.toString(encodedCopy));
        } else {
            encodedCopy = pathFromList.getEncoded(encoding);
            assertNotNull(providerName, encodedCopy);

            // check idempotence
            assertEquals(providerName, Arrays.toString(pathFromList.getEncoded(encoding)),
                    Arrays.toString(encodedCopy));
        }

        // Try to modify byte array.
        encodedCopy[0] ^= (byte) 0xFF;

        // Get a real copy we will use if the test proceeds.
        final byte[] encoded;
        if (encoding == null) {
            encoded = pathFromList.getEncoded();
            assertNotNull(providerName, encodedCopy);

            // check idempotence
            assertEquals(providerName, Arrays.toString(pathFromList.getEncoded()),
                    Arrays.toString(encoded));
        } else {
            encoded = pathFromList.getEncoded(encoding);
            assertNotNull(providerName, encodedCopy);

            // check idempotence
            assertEquals(providerName, Arrays.toString(pathFromList.getEncoded(encoding)),
                    Arrays.toString(encoded));
        }
        assertFalse(providerName, Arrays.toString(encoded).equals(Arrays.toString(encodedCopy)));

        encodedCopy[0] ^= (byte) 0xFF;
        assertEquals(providerName, Arrays.toString(encoded), Arrays.toString(encodedCopy));

        final CertPath actualPath;
        if (encoding == null) {
            actualPath = cf.generateCertPath(new ByteArrayInputStream(encoded));
        } else {
            actualPath = cf.generateCertPath(new ByteArrayInputStream(encoded), encoding);
        }

        // PKCS7 certificate bags are not guaranteed to be in order.
        final List<? extends Certificate> actualCerts;
        if (!"PKCS7".equals(encoding)) {
            actualCerts = actualPath.getCertificates();
            assertEquals(providerName, expectedCerts, actualCerts);
        } else {
            actualCerts = pathFromList.getCertificates();
        }

        try {
            actualCerts.remove(0);
            fail("List of certificate should be immutable");
        } catch (UnsupportedOperationException expected) {
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(actualPath);
        oos.close();

        byte[] serialized = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(serialized);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object output = ois.readObject();
        assertTrue(providerName, output instanceof CertPath);

        assertEquals(providerName, actualPath, (CertPath) output);
    }

    public static class KeyHolder {
        public X509Certificate certificate;

        public PrivateKey privateKey;
    }

    @SuppressWarnings("deprecation")
    private static KeyHolder generateCertificate(boolean isCa, KeyHolder issuer) throws Exception {
        Date startDate = new Date();

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.set(2100, 0, 1, 0, 0, 0); // Jan 1, 2100 UTC
        Date expiryDate = cal.getTime();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = kpg.generateKeyPair();

        BigInteger serial;
        X500Principal issuerPrincipal;
        X500Principal subjectPrincipal;
        PrivateKey caKey;
        if (issuer != null) {
            serial = issuer.certificate.getSerialNumber().add(BigInteger.ONE);
            subjectPrincipal = new X500Principal("CN=Test Certificate Serial #" + serial.toString());
            issuerPrincipal = issuer.certificate.getSubjectX500Principal();
            caKey = issuer.privateKey;
        } else {
            serial = BigInteger.ONE;
            subjectPrincipal = new X500Principal("CN=Test CA, O=Tests, C=US");
            issuerPrincipal = subjectPrincipal;
            caKey = keyPair.getPrivate();
        }

        BasicConstraints basicConstraints;
        if (isCa) {
            basicConstraints = new BasicConstraints(10 - serial.intValue());
        } else {
            basicConstraints = new BasicConstraints(false);
        }

        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

        certGen.setSerialNumber(serial);
        certGen.setIssuerDN(issuerPrincipal);
        certGen.setNotBefore(startDate);
        certGen.setNotAfter(expiryDate);
        certGen.setSubjectDN(subjectPrincipal);
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA1withRSA");

        if (issuer != null) {
            certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
                    new AuthorityKeyIdentifierStructure(issuer.certificate));
        } else {
            certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
                    new AuthorityKeyIdentifierStructure(keyPair.getPublic()));
        }

        certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false,
                new SubjectKeyIdentifierStructure(keyPair.getPublic()));
        certGen.addExtension(X509Extensions.BasicConstraints, true, basicConstraints);

        X509Certificate cert = certGen.generate(caKey);

        KeyHolder holder = new KeyHolder();
        holder.certificate = cert;
        holder.privateKey = keyPair.getPrivate();

        return holder;
    }
}
