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

package tests.security.cert;

import junit.framework.TestCase;

import tests.support.resource.Support_Resources;

import tests.support.Support_GetResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;
import java.util.List;

public class CertificateFactory4Test extends TestCase {

    private static final String BASE_URL = Support_GetResource
            .getResourceURL("/../internalres/");

    private static final String[] CERTIFICATE_URLS = new String[] {
            "Bug93891-PEM.cer", "Bug93891-DER.cer", "Bug94404-PKCS7.cer" };

    private static final String[] CRL_URLS = new String[] { "Bug93893-1.crl",
            "Bug93893-2.crl", "Bug94404-DER.crl" };

    private static final String[] CRLCOLLECTION_URLS = new String[] { "Bug94404-PKCS7.crl" };

    /**
     * java.security.cert.CertificateFactory#generateCertificate(java.io.InputStream)
     */
    public void test_generateCertificateLjava_io_InputStream() throws Exception {
        // Test 1
        // Test for method java.security.cert.Certificate
        // java.security.cert.CertificateFactory.generateCertificate(java.io.InputStream)
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        for (int i = 0; i < CERTIFICATES_ENCODED_X509.length; i++) {
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    CERTIFICATES_ENCODED_X509[i].getBytes());
            fact.generateCertificate(bais);

            // try again with generateCertificates()
            bais = new ByteArrayInputStream(CERTIFICATES_ENCODED_X509[i]
                    .getBytes());
            fact.generateCertificate(bais);
        }

        // Test 2
        InputStream is = Support_Resources.getResourceStream("hyts_badpem.cer");
        try {
            fact.generateCertificate(is);
            fail("Test2: CertificateException not thrown");
        } catch (CertificateException e) {} finally {
            try {
                is.close();
            } catch (IOException ignore) {}
        }
    }

    /**
     * java.security.cert.CertificateFactory#generateCertificates(java.io.InputStream)
     */
    public void test_generateCertificatesLjava_io_InputStream()
            throws Exception {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        for (int i = 0; i < CERTIFICATE_URLS.length; i++) {
            URL certUrl = new URL(BASE_URL + CERTIFICATE_URLS[i]);
            try {
                InputStream is = certUrl.openStream();
                Collection<? extends Certificate> certs = fact.generateCertificates(is);
                assertNotNull("The certificates in \""
                        + certUrl.toExternalForm()
                        + "\" were not parsed correctly", certs);
            } catch (IOException e) {
                // the certificate could not be found, skip it
            } catch (CertificateException e) {
                fail("An exception was thrown while parsing \""
                        + certUrl.toExternalForm() + "\": " + e.getMessage());
            }
        }
    }

    /**
     * java.security.cert.CertificateFactory#generateCRL(java.io.InputStream)
     */
    public void test_generateCRLLjava_io_InputStream() throws Exception {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        for (int i = 0; i < CRL_URLS.length; i++) {
            URL certUrl = new URL(BASE_URL + CRL_URLS[i]);
            try {
                InputStream is = certUrl.openStream();
                CRL crl = fact.generateCRL(is);
                assertNotNull("The CRL in \"" + certUrl.toExternalForm()
                        + "\" were not parsed correctly", crl);
            } catch (IOException e) {
                // the certificate could not be found, skip it
            } catch (CRLException e) {
                fail("An exception was thrown while parsing \""
                        + certUrl.toExternalForm() + "\": " + e.getMessage());
            }
        }
    }

    /**
     * java.security.cert.CertificateFactory#generateCRLs(java.io.InputStream)
     */
    public void test_generateCRLsLjava_io_InputStream() throws Exception {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        for (int i = 0; i < CRLCOLLECTION_URLS.length; i++) {
            URL certUrl = new URL(BASE_URL + CRLCOLLECTION_URLS[i]);
            try {
                InputStream is = certUrl.openStream();
                Collection<? extends CRL> crls = fact.generateCRLs(is);
                assertTrue("The CRLs in \"" + certUrl.toExternalForm()
                        + "\" were not parsed correctly", crls != null
                        && crls.size() > 0);
            } catch (IOException e) {
                // the certificate could not be found, skip it
            }
        }
    }

    /**
     * java.security.cert.CertificateFactory#getInstance(java.lang.String)
     */
    public void test_getInstanceLjava_lang_String() throws Exception {
        // Test for method java.security.cert.CertificateFactory
        // java.security.cert.CertificateFactory.getInstance(java.lang.String)
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        assertTrue("factory is null", fact != null);
    }

    /**
     * java.security.cert.CertificateFactory#getInstance(java.lang.String,
     *        java.lang.String)
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String()
            throws Exception {
        // Test for method java.security.cert.CertificateFactory
        // java.security.cert.CertificateFactory.getInstance(java.lang.String,
        // java.lang.String)
        Provider[] providers = Security
                .getProviders("CertificateFactory.X.509");

        if (providers != null) {
            for (int i = 0; i < providers.length; i++) {
                CertificateFactory fact = CertificateFactory.getInstance(
                        "X.509", providers[i].getName());
                assertNotNull("factory is null", fact);
            }// end for
        } else {
            fail("No providers support CertificateFactory.X.509");
        }

        // exception case
        try {
            CertificateFactory.getInstance("X.509", "IHaventBeenConfigured");
            fail("Should have thrown NoSuchProviderException");
        } catch (NoSuchProviderException e) {
            // Expected
        }
    }

    /**
     * java.security.cert.CertificateFactory#getProvider()
     */
    public void test_getProvider() throws Exception {
        // Test for method java.security.Provider
        // java.security.cert.CertificateFactory.getProvider()
        Provider p = CertificateFactory.getInstance("X.509").getProvider();
        assertNotNull("provider is null", p);
    }

    /**
     * java.security.cert.CertificateFactory#generateCRLs(InputStream
     *        inStream)
     */
    public void testGenerateCRLs2() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCRL(
                    (InputStream) null);
            fail("CRLException was not thrown");
        } catch (CRLException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCertificate(InputStream
     *        inStream)
     */
    public void testGenerateCertificate() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCertificate(null);
            fail("CertificateException was not thrown");
        } catch (CertificateException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCertificates(InputStream
     *        inStream)
     */
    public void testGenerateCertificates2() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCertificates(null);
            fail("CertificateException was not thrown");
        } catch (CertificateException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCertPath(InputStream
     *        inStream, String encoding)
     */
    public void testGenerateCertPath1() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCertPath(
                    (InputStream) null, "PkiPath");
            fail("CertificateException was not thrown");
        } catch (CertificateException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCertPath(List<?
     *        extends Certificate> certificates)
     */
    public void testGenerateCertPath2() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCertPath(
                    (List<Certificate>) null);
            fail("NullPointerException was not thrown");
        } catch (NullPointerException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCertPath(InputStream
     *        inStream)
     */
    public void testGenerateCertPath3() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCertPath(
                    (InputStream) null);
            fail("CertificateException was not thrown");
        } catch (CertificateException e) {}
    }

    /**
     * java.security.cert.CertificateFactory#generateCRL(InputStream
     *        inStream)
     */
    public void testGenerateCRL() throws Exception {
        // Regression for HARMONY-814
        try {
            CertificateFactory.getInstance("X.509").generateCRL(
                    (InputStream) null);
            fail("CRLException was not thrown");
        } catch (CRLException e) {}
    }

    private static final String[] CERTIFICATES_ENCODED_X509 = {
            // CERTIFICATES_ENCODED_X509[0]
            "-----BEGIN CERTIFICATE-----\n"
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
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[1]
            "-----BEGIN CERTIFICATE-----\n"
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
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[2]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIID6TCCA1KgAwIBAgIBGjANBgkqhkiG9w0BAQUFADBhMQswCQYDVQQGEwJVUzEY\n"
                    + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
                    + "A1BLSTEcMBoGA1UEAxMTRG9EIFBLSSBNZWQgUm9vdCBDQTAeFw05ODA4MDIxNjQ1\n"
                    + "MzhaFw0wMzA4MDIxNjQ1MzhaMFYxCzAJBgNVBAYTAlVTMRgwFgYDVQQKEw9VLlMu\n"
                    + "IEdvdmVybm1lbnQxDDAKBgNVBAsTA0RvRDEMMAoGA1UECxMDUEtJMREwDwYDVQQD\n"
                    + "EwhNZWQgQ0EtMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAyUcrw1k6QKdB\n"
                    + "WjgtGOk1AVaqJNI8acDGglhqQQ94QYfqZKuo1wwdnYehrgo5QcGkw9XcjBYegLFs\n"
                    + "v4NCCwZ2pgsfYJlvHcSGPhT/wROUYEpXKelfXiEeaGhFl8zGcqteI2+EBbOZnFpj\n"
                    + "Y4y+25dQcjYnrRGMAQ98qGwQtogDiDcCAwEAAaOCAbowggG2MBYGA1UdIAQPMA0w\n"
                    + "CwYJYIZIAWUCAQsDMB8GA1UdIwQYMBaAFFa5mEepve9NXB0N6+ZF8hu87AjdMAwG\n"
                    + "A1UdJAQFMAOAAQAwHQYDVR0OBBYEFDM6FOgJZ2GIZSQg7HlwQtemkx72MA4GA1Ud\n"
                    + "DwEB/wQEAwIBhjB+BgNVHRIEdzB1hnNsZGFwOi8vZHMtMS5jaGFtYi5kaXNhLm1p\n"
                    + "bC9jbiUzZERvRCUyMFBLSSUyME1lZCUyMFJvb3QlMjBDQSUyY291JTNkUEtJJTIg\n"
                    + "Y291JTNkRG9EJTJjbyUzZFUuUy4lMjBHb3Zlcm5tZW50JTJjYyUzZFVTMA8GA1Ud\n"
                    + "EwEB/wQFMAMBAf8wgawGA1UdHwSBpDCBoTCBnqCBm6CBmIaBlWxkYXA6Ly9kcy0x\n"
                    + "LmNoYW1iLmRpc2EubWlsL2NuJTNkRG9EJTIwUEtJJTIwTWVkJTIwUm9vdCUyMENB\n"
                    + "JTJjb3UlM2RQS0klMmNvdSUzZERvRCUyY28lM2RVLlMuJTIwR292ZXJubWVudCUy\n"
                    + "Y2MlM2RVUz9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0JTNiYmluYXJ5MA0GCSqG\n"
                    + "SIb3DQEBBQUAA4GBAFo5/Tu0dsy8tyhJVvxaKFNMfP3LLaspl+Or8oCpncKdpKyj\n"
                    + "7ZO6uJ0n7oqvEaUThm8jgXSNgyttlYPwoNBxEsTq/lBDV3+y/c61psw3qM2boB1H\n"
                    + "Oi3xXnRY+etG33TN9yydzrZ52XM0hnJZd4xIfoAgqs4T2rgqg8hx0ydU7o4o\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[3]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIDVjCCAr+gAwIBAgIBRTANBgkqhkiG9w0BAQUFADBWMQswCQYDVQQGEwJVUzEY\n"
                    + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
                    + "A1BLSTERMA8GA1UEAxMITWVkIENBLTEwHhcNOTgwODAyMTcxMzI5WhcNMDEwODAy\n"
                    + "MTcxMzI5WjBwMQswCQYDVQQGEwJVUzEYMBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50\n"
                    + "MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsTA1BLSTENMAsGA1UECxMEVVNBRjEcMBoG\n"
                    + "A1UEAxMTZHMtMS5jaXN0dy5zYWljLmNvbTCBnTANBgkqhkiG9w0BAQEFAAOBiwAw\n"
                    + "gYcCgYEA19oJsspSHnWDi1/NTthbLrnicDogg3c63ZHPedU1YD90L1ogkYzxSA2t\n"
                    + "MgsVZpNejBglE972mXKPqEGDojnDgltRgRLtLqisIs0DSFQrJrhA8egMH/pHAo9H\n"
                    + "fH0n9rQUYBI3dsLxQkGVUSbB4P83VHi0sQO0dWsq5mEZd9G+MfsCAQOjggEaMIIB\n"
                    + "FjAWBgNVHSAEDzANMAsGCWCGSAFlAgELAzAfBgNVHSMEGDAWgBQzOhToCWdhiGUk\n"
                    + "IOx5cELXppMe9jAdBgNVHQ4EFgQUcQaYO8EEjje+VI3vfBIlDC6HNj0wDgYDVR0P\n"
                    + "AQH/BAQDAgUgMAwGA1UdEwEB/wQCMAAwgZ0GA1UdHwSBlTCBkjCBj6CBjKCBiYaB\n"
                    + "hmxkYXA6Ly9kcy0xLmNoYW1iLmRpc2EubWlsL2NuJTNkTWVkJTIwQ0ElMmQxJTJj\n"
                    + "b3UlM2RQS0klMmNvdSUzZERvRCUyY28lM2RVLlMuJTIwR292ZXJubWVudCUyY2Ml\n"
                    + "M2RVUz9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0JTNiYmluYXJ5MA0GCSqGSIb3\n"
                    + "DQEBBQUAA4GBAEg7AC9bad2KZzyX4cqLU/mv2cbVg6tES2PIeST7nk8CQcv9a8IO\n"
                    + "3K4uhrKoTsQfqs9p6+6s0VbgH3PKvOAIF4DAp5Yq1zz3fB+hsaFleHqtDNuldm1+\n"
                    + "3XA2Oqa5aRFkb6Krut0EEOV4c/GEAPOrRGUTzYmOp4SEc8TEaD/75A7R\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[4]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIDXDCCAsWgAwIBAgIBSjANBgkqhkiG9w0BAQUFADBWMQswCQYDVQQGEwJVUzEY\n"
                    + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
                    + "A1BLSTERMA8GA1UEAxMITWVkIENBLTEwHhcNOTgwODAyMTgwMjQwWhcNMDEwODAy\n"
                    + "MTgwMjQwWjB0MQswCQYDVQQGEwJVUzEYMBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50\n"
                    + "MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsTA1BLSTENMAsGA1UECxMEVVNBRjEgMB4G\n"
                    + "A1UEAxMXR3VtYnkuSm9zZXBoLjAwMDAwMDUwNDQwgZ8wDQYJKoZIhvcNAQEBBQAD\n"
                    + "gY0AMIGJAoGBALT/R7bPqs1c1YqXAg5HNpZLgW2HuAc7RCaP06cE4R44GBLw/fQc\n"
                    + "VRNLn5pgbTXsDnjiZVd8qEgYqjKFQka4/tNhaF7No2tBZB+oYL/eP0IWtP+h/W6D\n"
                    + "KR5+UvIIdgmx7k3t9jp2Q51JpHhhKEb9WN54trCO9Yu7PYU+LI85jEIBAgMBAAGj\n"
                    + "ggEaMIIBFjAWBgNVHSAEDzANMAsGCWCGSAFlAgELAzAfBgNVHSMEGDAWgBQzOhTo\n"
                    + "CWdhiGUkIOx5cELXppMe9jAdBgNVHQ4EFgQUkLBJl+ayKgzOp/wwBX9M1lSkCg4w\n"
                    + "DgYDVR0PAQH/BAQDAgbAMAwGA1UdEwEB/wQCMAAwgZ0GA1UdHwSBlTCBkjCBj6CB\n"
                    + "jKCBiYaBhmxkYXA6Ly9kcy0xLmNoYW1iLmRpc2EubWlsL2NuJTNkTWVkJTIwQ0El\n"
                    + "MmQxJTJjb3UlM2RQS0klMmNvdSUzZERvRCUyY28lM2RVLlMuJTIwR292ZXJubWVu\n"
                    + "dCUyY2MlM2RVUz9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0JTNiYmluYXJ5MA0G\n"
                    + "CSqGSIb3DQEBBQUAA4GBAFjapuDHMvIdUeYRyEYdShBR1JZC20tJ3MQnyBQveddz\n"
                    + "LGFDGpIkRAQU7T/5/ne8lMexyxViC21xOlK9LdbJCbVyywvb9uEm/1je9wieQQtr\n"
                    + "kjykuB+WB6qTCIslAO/eUmgzfzIENvnH8O+fH7QTr2PdkFkiPIqBJYHvw7F3XDqy\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[5]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIDlDCCAv2gAwIBAgIBGTANBgkqhkiG9w0BAQUFADBcMQswCQYDVQQGEwJVUzEY\n"
                    + "MBYGA1UEChMPVS5TLiBHb3Zlcm5tZW50MQwwCgYDVQQLEwNEb0QxDDAKBgNVBAsT\n"
                    + "A1BLSTEXMBUGA1UEAxMOTWVkIEVtYWlsIENBLTEwHhcNOTgwODAyMTgwNjM0WhcN\n"
                    + "MDAwODAyMTgwNjM0WjCBmTELMAkGA1UEBhMCVVMxGDAWBgNVBAoTD1UuUy4gR292\n"
                    + "ZXJubWVudDEMMAoGA1UECxMDRG9EMQwwCgYDVQQLEwNQS0kxDTALBgNVBAsTBFVT\n"
                    + "QUYxIDAeBgNVBAMTF0d1bWJ5Lkpvc2VwaC4wMDAwMDA1MDQ0MSMwIQYJKoZIhvcN\n"
                    + "AQkBFhRndW1ieUBjaXN0dy5zYWljLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAw\n"
                    + "gYkCgYEAvU4LMoOnF9bmhHvxZz8wCc9eRJ0j4RB0SmVYoq9iPrSJBwROeuxuU8VS\n"
                    + "JBL/u+RB5t6UJVNjXFmB1wS0foxpOHFQBFWyvWmuOiRUNaRxTVCrO4eG+nmM/U89\n"
                    + "DKAc9FV4bZ8dPE9PlK9oq0J8Y2DIKz1+gIeM1sTPMnDtFgfGyH8CAwEAAaOCASYw\n"
                    + "ggEiMBYGA1UdIAQPMA0wCwYJYIZIAWUCAQsDMB8GA1UdIwQYMBaAFJcrSHN/a+aN\n"
                    + "L5DK1NpJUIvX+bVnMB0GA1UdDgQWBBR50N97AxK0G6U17EP1iu38LiLTBzAOBgNV\n"
                    + "HQ8BAf8EBAMCBaAwDAYDVR0TAQH/BAIwADCBqQYDVR0fBIGhMIGeMIGboIGYoIGV\n"
                    + "hoGSbGRhcDovL2RzLTEuY2hhbWIuZGlzYS5taWw6MzkwL2NuJTNkTWVkJTIwRW1h\n"
                    + "aWwlMjBDQSUyZDElMmNvdSUzZFBLSSUyY291JTNkRG9EJTJjbyUzZFUuUy4lMjBH\n"
                    + "b3Zlcm5tZW50JTJjYyUzZFVTP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3QlM2Ji\n"
                    + "aW5hcnkwDQYJKoZIhvcNAQEFBQADgYEAA9z8h7K4v0CuOyvmALNl8TQt0inf0w52\n"
                    + "JJUvw/3FLA622IHe/vC9VHyIF0ibSEljWeOBuRjoMELAZGXCwRu43o2LDRqHr4Pc\n"
                    + "WlG0uUtgHTPxbZpaUwueIZCBZg57f7Zhlub7Ag+AjeOybFj3FYqDB7TYqWJgAs/7\n"
                    + "g5WfNEVAEwc=\n" + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[6]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIEEjCCA7ygAwIBAgIIEt4r4gAAAlIwDQYJKoZIhvcNAQEEBQAwgZMxCzAJBgNV\n"
                    + "BAYTAlVTMQswCQYDVQQIEwJXQTEQMA4GA1UEBxMHUmVkbW9uZDETMBEGA1UEChMK\n"
                    + "V2luZG93cyBOVDEbMBkGA1UECxMSRGlzdHJpYnV0ZWQgU3lzdGVtMTMwMQYDVQQD\n"
                    + "EypNaWNyb3NvZnQgQ2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0EwHhcN\n"
                    + "OTcxMTI1MTkwNDIyWhcNOTgxMDE0MTgxMTI4WjBuMQswCQYDVQQGEwJVUzELMAkG\n"
                    + "A1UECBMCV0ExEDAOBgNVBAcTB1JlZG1vbmQxHjAcBgNVBAoTFU1pY3Jvc29mdCBD\n"
                    + "b3Jwb3JhdGlvbjENMAsGA1UECxMERFNZUzERMA8GA1UEAxMIQ2VydFRlc3QwWzAN\n"
                    + "BgkqhkiG9w0BAQEFAANKADBHAkB6hKgbYme8gtCf1Vy74KVNLf2o/R1kQVDSZiNm\n"
                    + "lBSEsHAJSLXuuVdYsKo/hzarr9gGmI/gUzmargY1xJGQYbazAgMBAAGjggIXMIIC\n"
                    + "EzCBzwYDVR0jBIHHMIHEgBS3hTIRuBZaOibht1DZjnTUg/IiRaGBmaSBljCBkzEL\n"
                    + "MAkGA1UEBhMCVVMxCzAJBgNVBAgTAldBMRAwDgYDVQQHEwdSZWRtb25kMRMwEQYD\n"
                    + "VQQKEwpXaW5kb3dzIE5UMRswGQYDVQQLExJEaXN0cmlidXRlZCBTeXN0ZW0xMzAx\n"
                    + "BgNVBAMTKk1pY3Jvc29mdCBDZXJ0aWZpY2F0ZSBTZXJ2ZXIgVGVzdCBHcm91cCBD\n"
                    + "QYIQERNhAKoA/oUR0US54igUYzCBvQYDVR0fBIG1MIGyMFagVKBShlBodHRwOi8v\n"
                    + "Q0VSVFNSVi9DZXJ0U3J2L0NlcnRFbnJvbGwvTWljcm9zb2Z0IENlcnRpZmljYXRl\n"
                    + "IFNlcnZlciBUZXN0IEdyb3VwIENBLmNybDBYoFagVIZSZmlsZTovL1xcQ0VSVFNS\n"
                    + "VlxDZXJ0U3J2XENlcnRFbnJvbGxcTWljcm9zb2Z0IENlcnRpZmljYXRlIFNlcnZl\n"
                    + "ciBUZXN0IEdyb3VwIENBLmNybDAJBgNVHRMEAjAAMHQGCCsGAQUFBwEBBGgwZjBk\n"
                    + "BggrBgEFBQcwAoZYaHR0cDovL0NFUlRTUlYvQ2VydFNydi9DZXJ0RW5yb2xsL0NF\n"
                    + "UlRTUlZfTWljcm9zb2Z0IENlcnRpZmljYXRlIFNlcnZlciBUZXN0IEdyb3VwIENB\n"
                    + "LmNydDANBgkqhkiG9w0BAQQFAANBAFbEj4j/3Nv6WcAvq24C7yw8L0FcyE4dtLLX\n"
                    + "U+04P0POe/doyTT6UngXNXp9RXpqDSiIHBRTshpvR+N2vweR5qA=\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[7]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIEYTCCBAugAwIBAgIIFViWmwAAAlowDQYJKoZIhvcNAQEEBQAwgZMxCzAJBgNV\n"
                    + "BAYTAlVTMQswCQYDVQQIEwJXQTEQMA4GA1UEBxMHUmVkbW9uZDETMBEGA1UEChMK\n"
                    + "V2luZG93cyBOVDEbMBkGA1UECxMSRGlzdHJpYnV0ZWQgU3lzdGVtMTMwMQYDVQQD\n"
                    + "EypNaWNyb3NvZnQgQ2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0EwHhcN\n"
                    + "OTcxMTI2MDYzNzE4WhcNOTgxMDE0MTgxMTI4WjCBmjEjMCEGCSqGSIb3DQEJARYU\n"
                    + "YWxsYW5jQG1pY3Jvc29mdC5jb20xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJXQTEQ\n"
                    + "MA4GA1UEBxMHUmVkbW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9u\n"
                    + "MQ0wCwYDVQQLEwREU1lTMRgwFgYDVQQDEw9BbGxhbiBKLiBDb29wZXIwWzANBgkq\n"
                    + "hkiG9w0BAQEFAANKADBHAkB1951uZLyJXjSZTc8Z1QnuXvKBAqm2WT4OFaFySF/F\n"
                    + "WnMbIlAE0pvszDkKZ+N7hBzcc5pVIjezPfZ8cPh//jVPAgMBAAGjggI5MIICNTAL\n"
                    + "BgNVHQ8EBAMCADgwEwYDVR0lBAwwCgYIKwYBBQUHAwQwgc8GA1UdIwSBxzCBxIAU\n"
                    + "t4UyEbgWWjom4bdQ2Y501IPyIkWhgZmkgZYwgZMxCzAJBgNVBAYTAlVTMQswCQYD\n"
                    + "VQQIEwJXQTEQMA4GA1UEBxMHUmVkbW9uZDETMBEGA1UEChMKV2luZG93cyBOVDEb\n"
                    + "MBkGA1UECxMSRGlzdHJpYnV0ZWQgU3lzdGVtMTMwMQYDVQQDEypNaWNyb3NvZnQg\n"
                    + "Q2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0GCEBETYQCqAP6FEdFEueIo\n"
                    + "FGMwgb0GA1UdHwSBtTCBsjBWoFSgUoZQaHR0cDovL0NFUlRTUlYvQ2VydFNydi9D\n"
                    + "ZXJ0RW5yb2xsL01pY3Jvc29mdCBDZXJ0aWZpY2F0ZSBTZXJ2ZXIgVGVzdCBHcm91\n"
                    + "cCBDQS5jcmwwWKBWoFSGUmZpbGU6Ly9cXENFUlRTUlZcQ2VydFNydlxDZXJ0RW5y\n"
                    + "b2xsXE1pY3Jvc29mdCBDZXJ0aWZpY2F0ZSBTZXJ2ZXIgVGVzdCBHcm91cCBDQS5j\n"
                    + "cmwwCQYDVR0TBAIwADB0BggrBgEFBQcBAQRoMGYwZAYIKwYBBQUHMAKGWGh0dHA6\n"
                    + "Ly9DRVJUU1JWL0NlcnRTcnYvQ2VydEVucm9sbC9DRVJUU1JWX01pY3Jvc29mdCBD\n"
                    + "ZXJ0aWZpY2F0ZSBTZXJ2ZXIgVGVzdCBHcm91cCBDQS5jcnQwDQYJKoZIhvcNAQEE\n"
                    + "BQADQQA1TYsk07tW0dhU6bHPK7NXHUFFiZ2fAtC0epLY9G6yuYb1lozPv5sDnCl1\n"
                    + "A2fZPgawvAqCvK9xkv5L4j2F+v4U\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[8]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIEYjCCBAygAwIBAgIIFVsHaQAAAlwwDQYJKoZIhvcNAQEEBQAwgZMxCzAJBgNV\n"
                    + "BAYTAlVTMQswCQYDVQQIEwJXQTEQMA4GA1UEBxMHUmVkbW9uZDETMBEGA1UEChMK\n"
                    + "V2luZG93cyBOVDEbMBkGA1UECxMSRGlzdHJpYnV0ZWQgU3lzdGVtMTMwMQYDVQQD\n"
                    + "EypNaWNyb3NvZnQgQ2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0EwHhcN\n"
                    + "OTcxMTI2MDYzOTU4WhcNOTgxMDE0MTgxMTI4WjCBmjEjMCEGCSqGSIb3DQEJARYU\n"
                    + "YWxsYW5jQG1pY3Jvc29mdC5jb20xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJXQTEQ\n"
                    + "MA4GA1UEBxMHUmVkbW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9u\n"
                    + "MQ0wCwYDVQQLEwREU1lTMRgwFgYDVQQDEw9BbGxhbiBKLiBDb29wZXIwXDANBgkq\n"
                    + "hkiG9w0BAQEFAANLADBIAkEA0T1td8kfsJgwm8Qj+jtrq29tqc/DIkIbAPcyygKG\n"
                    + "1XEUvzQEQumVQx5lCD2LOOJs0eOuv4h6ngvLW+goDaidNQIDAQABo4ICOTCCAjUw\n"
                    + "CwYDVR0PBAQDAgDAMBMGA1UdJQQMMAoGCCsGAQUFBwMIMIHPBgNVHSMEgccwgcSA\n"
                    + "FLeFMhG4Flo6JuG3UNmOdNSD8iJFoYGZpIGWMIGTMQswCQYDVQQGEwJVUzELMAkG\n"
                    + "A1UECBMCV0ExEDAOBgNVBAcTB1JlZG1vbmQxEzARBgNVBAoTCldpbmRvd3MgTlQx\n"
                    + "GzAZBgNVBAsTEkRpc3RyaWJ1dGVkIFN5c3RlbTEzMDEGA1UEAxMqTWljcm9zb2Z0\n"
                    + "IENlcnRpZmljYXRlIFNlcnZlciBUZXN0IEdyb3VwIENBghARE2EAqgD+hRHRRLni\n"
                    + "KBRjMIG9BgNVHR8EgbUwgbIwVqBUoFKGUGh0dHA6Ly9DRVJUU1JWL0NlcnRTcnYv\n"
                    + "Q2VydEVucm9sbC9NaWNyb3NvZnQgQ2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3Jv\n"
                    + "dXAgQ0EuY3JsMFigVqBUhlJmaWxlOi8vXFxDRVJUU1JWXENlcnRTcnZcQ2VydEVu\n"
                    + "cm9sbFxNaWNyb3NvZnQgQ2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0Eu\n"
                    + "Y3JsMAkGA1UdEwQCMAAwdAYIKwYBBQUHAQEEaDBmMGQGCCsGAQUFBzAChlhodHRw\n"
                    + "Oi8vQ0VSVFNSVi9DZXJ0U3J2L0NlcnRFbnJvbGwvQ0VSVFNSVl9NaWNyb3NvZnQg\n"
                    + "Q2VydGlmaWNhdGUgU2VydmVyIFRlc3QgR3JvdXAgQ0EuY3J0MA0GCSqGSIb3DQEB\n"
                    + "BAUAA0EAUPXt2pOY3YwRUHzD7Dtgyx5G7KxKtLan1wFBFjhv406v2Utb+2+wTQlS\n"
                    + "ulWemcm8eOdG64nspv0oqSJnA8f4xg==\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[9]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIICiTCCAfICAQAwDQYJKoZIhvcNAQEEBQAwgYwxCzAJBgNVBAYTAlVTMRUwEwYD\n"
                    + "VQQIEwxOb3J0aCBEYWtvdGExFDASBgNVBAcTC0dyYW5kIEZvcmtzMRYwFAYDVQQK\n"
                    + "Ew1VTkQgQWVyb3NwYWNlMRgwFgYDVQQDFA9yb290QGNzLnVuZC5lZHUxHjAcBgkq\n"
                    + "hkiG9w0BCQEWD3Jvb3RAY3MudW5kLmVkdTAeFw05OTAzMDIyMDU4NDRaFw0wOTAy\n"
                    + "MjcyMDU4NDRaMIGMMQswCQYDVQQGEwJVUzEVMBMGA1UECBMMTm9ydGggRGFrb3Rh\n"
                    + "MRQwEgYDVQQHEwtHcmFuZCBGb3JrczEWMBQGA1UEChMNVU5EIEFlcm9zcGFjZTEY\n"
                    + "MBYGA1UEAxQPcm9vdEBjcy51bmQuZWR1MR4wHAYJKoZIhvcNAQkBFg9yb290QGNz\n"
                    + "LnVuZC5lZHUwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALmlQJg5Nl2VsQZ1\n"
                    + "/B8fW4YDdk09SlVc7vyWcbcmbWRBJee0jcH448XdRP/m/MXIRsVKyhLA5f01+VPy\n"
                    + "E2aDkx2WiU4DpqbMbGGAytuXBNudJQmBXjWEFiAGe7dYgDNGKK7Yo1k49Q6qGg9q\n"
                    + "5did3+ppsyfzbeaiDCH0LO5gegNvAgMBAAEwDQYJKoZIhvcNAQEEBQADgYEAJnok\n"
                    + "1gvj4KC9HeUX/R4Q/f5tbJ2jLeQATIHtUx9QSKSq7IsdY0zz9EnKOsc9pr8JfBTL\n"
                    + "cAwrxqvl5QuoCFVR2tQq8DtBQY8vp7bEF2CZVoxZJXMIKKiD/Hjb0oypbq5wF0SY\n"
                    + "xN5DUfG5sShi+vPIAwE62tZ1P1I1N8DQpDYiXkw=\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[10]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIICWDCCAgICAQAwDQYJKoZIhvcNAQEEBQAwgbYxCzAJBgNVBAYTAlpBMRUwEwYD\n"
                    + "VQQIEwxXZXN0ZXJuIENhcGUxEjAQBgNVBAcTCUNhcGUgVG93bjEdMBsGA1UEChMU\n"
                    + "VGhhd3RlIENvbnN1bHRpbmcgY2MxHzAdBgNVBAsTFkNlcnRpZmljYXRpb24gU2Vy\n"
                    + "dmljZXMxFzAVBgNVBAMTDnd3dy50aGF3dGUuY29tMSMwIQYJKoZIhvcNAQkBFhR3\n"
                    + "ZWJtYXN0ZXJAdGhhd3RlLmNvbTAeFw05NjExMTQxNzE1MjVaFw05NjEyMTQxNzE1\n"
                    + "MjVaMIG2MQswCQYDVQQGEwJaQTEVMBMGA1UECBMMV2VzdGVybiBDYXBlMRIwEAYD\n"
                    + "VQQHEwlDYXBlIFRvd24xHTAbBgNVBAoTFFRoYXd0ZSBDb25zdWx0aW5nIGNjMR8w\n"
                    + "HQYDVQQLExZDZXJ0aWZpY2F0aW9uIFNlcnZpY2VzMRcwFQYDVQQDEw53d3cudGhh\n"
                    + "d3RlLmNvbTEjMCEGCSqGSIb3DQEJARYUd2VibWFzdGVyQHRoYXd0ZS5jb20wXDAN\n"
                    + "BgkqhkiG9w0BAQEFAANLADBIAkEAmpIl7aR3aSPUUwUrHzpVMrsm3gpI2PzIwMh3\n"
                    + "9l1h/RszI0/0qC2WRMlfwm5FapohoyjTJ6ZyGUUenICllKyKZwIDAQABMA0GCSqG\n"
                    + "SIb3DQEBBAUAA0EAfI57WLkOKEyQqyCDYZ6reCukVDmAe7nZSbOyKv6KUvTCiQ5c\n"
                    + "e5L4y3c/ViKdlou5BcQYAbxA7rwO/vz4m51w4w==\n"
                    + "-----END CERTIFICATE-----\n",

            // CERTIFICATES_ENCODED_X509[11]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIDGjCCAtgCBDaRW4swCwYHKoZIzjgEAwUAMHMxCzAJBgNVBAYTAlVTMRAwDgYDVQQIEwdGbG9y\n"
                    + "aWRhMRQwEgYDVQQHEwtHYWluZXN2aWxsZTEQMA4GA1UEChMHVW5rbm93bjEQMA4GA1UECxMHVW5r\n"
                    + "bm93bjEYMBYGA1UEAxMPUm9iZXJ0IEx5YmFyZ2VyMB4XDTk5MDEwNTAwMjMzOVoXDTk5MDQwNTAw\n"
                    + "MjMzOVowczELMAkGA1UEBhMCVVMxEDAOBgNVBAgTB0Zsb3JpZGExFDASBgNVBAcTC0dhaW5lc3Zp\n"
                    + "bGxlMRAwDgYDVQQKEwdVbmtub3duMRAwDgYDVQQLEwdVbmtub3duMRgwFgYDVQQDEw9Sb2JlcnQg\n"
                    + "THliYXJnZXIwggG3MIIBLAYHKoZIzjgEATCCAR8CgYEA/X9TgR11EilS30qcLuzk5/YRt1I870QA\n"
                    + "wx4/gLZRJmlFXUAiUftZPY1Y+r/F9bow9subVWzXgTuAHTRv8mZgt2uZUKWkn5/oBHsQIsJPu6nX\n"
                    + "/rfGG/g7V+fGqKYVDwT7g/bTxR7DAjVUE1oWkTL2dfOuK2HXKu/yIgMZndFIAccCFQCXYFCPFSML\n"
                    + "zLKSuYKi64QL8Fgc9QKBgQD34aCF1ps93su8q1w2uFe5eZSvu/o66oL5V0wLPQeCZ1FZV4661FlP\n"
                    + "5nEHEIGAtEkWcSPoTCgWE7fPCTKMyKbhPBZ6i1R8jSjgo64eK7OmdZFuo38L+iE1YvH7YnoBJDvM\n"
                    + "pPG+qFGQiaiD3+Fa5Z8GkotmXoB7VSVkAUw7/s9JKgOBhAACgYBMhs/XcF0LAjbuhoAY4EOmxd4U\n"
                    + "U0w4nSJQ2vKcgpyHU1Sv/tbUr3xEm6Yyx49j1eNp9jVwM1a6NYX8BO8fCSHIiUVvJVFlCcoO7Qb8\n"
                    + "Px7drfbFAFt8mFE1mjYCuj21ePHhs1DlZKJwu2ElC6GaRwtBk3+oCMDAnLuySd0+fAohdDALBgcq\n"
                    + "hkjOOAQDBQADLwAwLAIUddbqC3woMcABg/r1GPW9eVNStGwCFCBGySvdXK0i4aLVC4Ptbc3PQFjp\n"
                    + "-----END CERTIFICATE-----\n",

            //CERTIFICATES_ENCODED_X509[12]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIICyzCCAjQCAQAwDQYJKoZIhvcNAQEEBQAwga0xCzAJBgNVBAYTAlVTMREwDwYD\n"
                    + "VQQIEwhOZXcgWW9yazEWMBQGA1UEBxMNTmV3IFlvcmsgQ2l0eTEcMBoGA1UEChMT\n"
                    + "Q29sdW1iaWEgVW5pdmVyc2l0eTENMAsGA1UECxMEQWNJUzEfMB0GA1UEAxMWQ29s\n"
                    + "dW1iaWEgVW5pdmVyc2l0eSBDQTElMCMGCSqGSIb3DQEJARYWY2VydC1hdXRoQGNv\n"
                    + "bHVtYmlhLmVkdTAeFw05NzA0MjgxNDQxMDNaFw0wMDA0MjgxNDQxMDNaMIGtMQsw\n"
                    + "CQYDVQQGEwJVUzERMA8GA1UECBMITmV3IFlvcmsxFjAUBgNVBAcTDU5ldyBZb3Jr\n"
                    + "IENpdHkxHDAaBgNVBAoTE0NvbHVtYmlhIFVuaXZlcnNpdHkxDTALBgNVBAsTBEFj\n"
                    + "SVMxHzAdBgNVBAMTFkNvbHVtYmlhIFVuaXZlcnNpdHkgQ0ExJTAjBgkqhkiG9w0B\n"
                    + "CQEWFmNlcnQtYXV0aEBjb2x1bWJpYS5lZHUwgZ8wDQYJKoZIhvcNAQEBBQADgY0A\n"
                    + "MIGJAoGBANiod6flzM72CbsK/3gzHzcdjpoozRDD/wgq31jEeDdfKY+ljAwxaZS9\n"
                    + "mt7S1g7lL+55fx7FjfJxvJRXvS9UbDU46PDDyJloWYobg84bK5ZcV5UnIPZmGHW/\n"
                    + "/xVDUtIGhc4T+Xm5p4F+4AcgewF2s4TbKWxfC98FJfepc31KjkGbAgMBAAEwDQYJ\n"
                    + "KoZIhvcNAQEEBQADgYEAI/e6xC+osVM4eMkSUUWgihuocQlRL9ixTlGqW9fvNlI1\n"
                    + "q58fELU5bcFko7d02S9Egac/9ckkt/sbHMv9zQhfnvpol8BN+LivGu+09IiOW4yq\n"
                    + "c9xT58Pv9gwZ/Ei5VS+FXvzHIr91yWIlwLsnKfgYDrmQowG5FkHSG1ZotUdl7Oo=\n"
                    + "-----END CERTIFICATE-----\n",

            //CERTIFICATES_ENCODED_X509[13]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIICnjCCAgcCAQMwDQYJKoZIhvcNAQEEBQAwgaAxCzAJBgNVBAYTAklUMR4wHAYD\n"
                    + "VQQKExVQb2xpdGVjbmljbyBkaSBUb3Jpbm8xIzAhBgNVBAsTGklDRS1URUwgSXRh\n"
                    + "bGlhbiBDQSBmb3IgV1dXMSAwHgYDVQQDExdDZXJ0aWZpY2F0aW9uIEF1dGhvcml0\n"
                    + "eTEqMCgGCSqGSIb3DQEJARYbd3d3LWNhLWl0QGljZS10ZWwucG9saXRvLml0MB4X\n"
                    + "DTk2MTAwMjExNDQ0NFoXDTk3MTIwMTExNDQ0NFowgY0xCzAJBgNVBAYTAklUMQ8w\n"
                    + "DQYDVQQHEwZUb3Jpbm8xHjAcBgNVBAoTFVBvbGl0ZWNuaWNvIGRpIFRvcmlubzEO\n"
                    + "MAwGA1UECxMFQ2VTSVQxGTAXBgNVBAMTEHVsaXNzZS5wb2xpdG8uaXQxIjAgBgkq\n"
                    + "hkiG9w0BCQEWE3dlYm1hc3RlckBwb2xpdG8uaXQwgZ8wDQYJKoZIhvcNAQEBBQAD\n"
                    + "gY0AMIGJAoGBAMUq/FdrxbSfGtGZq/FTTgC1JqxO4iiHiyxtgRT1oEvJIUjajVRN\n"
                    + "dtBVUhW6JmhHje/qnMop09XcF7b89a9ahtG9jM70S03biXVmg66pWOpy6P7znAQj\n"
                    + "VFPoCRR7BqUiGq0419a101Acaqkxi/4DdqiTPee4H7mcDZYu+fDPNQaHAgMBAAEw\n"
                    + "DQYJKoZIhvcNAQEEBQADgYEAt15bzk0XO+ZM+Q6275VTQIon6KQQHnv9NflIFOoW\n"
                    + "fgGRmoyiJFrjU1sIS8ctF03DH2xR20CuKd98fBpKnoOLd7eTKAGzGFPml36TPVj+\n"
                    + "YYWdrWqnIzQn6F0OKR/U3Y+ot5fUNuqN36Q1wsVvpPJlOMx8D8OQy8ainHgG3YYA\n"
                    + "TJk=\n" + "-----END CERTIFICATE-----\n",

            //CERTIFICATES_ENCODED_X509[14]
            "-----BEGIN CERTIFICATE-----\n"
                    + "MIIC1TCCAj6gAwIBAgIBBDANBgkqhkiG9w0BAQQFADBZMQswCQYDVQQGEwJVUzEf\n"
                    + "MB0GA1UEChMWVW5pdmVyc2l0eSBvZiBDb2xvcmFkbzEWMBQGA1UECxMNU3lzdGVt\n"
                    + "IE9mZmljZTERMA8GA1UEAxMIVU1TIENBLTEwHhcNOTgwNTExMjEwMjU0WhcNMDgw\n"
                    + "NTEwMjEwMjU0WjBZMQswCQYDVQQGEwJVUzEfMB0GA1UEChMWVW5pdmVyc2l0eSBv\n"
                    + "ZiBDb2xvcmFkbzEWMBQGA1UECxMNU3lzdGVtIE9mZmljZTERMA8GA1UEAxMIVU1T\n"
                    + "IENBLTEwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALGf8Ny+kHlXqLTi3uIf\n"
                    + "mkxxwDJd14sOg+hv85pxsqzCRJEgOx5YDUt05hJ7N0s4rJ/gNUcJaKR6ul+qLGbW\n"
                    + "+Zb4S1YFbnKaO16zggvgckwpLGYRBbmee9+E47W8VEOZWrJXUkpJ/ZV8GAnesrvn\n"
                    + "XTEKfm7bX9s6R7FQfDoHNnQfAgMBAAGjgawwgakwEQYJYIZIAYb4QgEBBAQDAgD2\n"
                    + "MB8GA1UdIwQYMBaAFOqlID9Er6dI09n9Lvnby+FISi7oMFQGCWCGSAGG+EIBDQRH\n"
                    + "FkVFeHRlbmRlZCBDQSBDZXJ0aWZpY2F0ZSBmcm9tIDI0IE1vbnRocyB0byAxMjAg\n"
                    + "TW9udGhzLiAgRFRHID0gMDUxMTE5OTgwHQYDVR0OBBYEFOqlID9Er6dI09n9Lvnb\n"
                    + "y+FISi7oMA0GCSqGSIb3DQEBBAUAA4GBAFNFo27JeeIgsMqS7Na//6gJQRilxwVS\n"
                    + "Bfx6J43YX47EgNDLn4J7B9Tst+2bDZDAk1lZyu4y2WCLrnfg/e6B1KYBhCt/Srsc\n"
                    + "r+WomFcw19k1jBtBaYxVwh/9N4ppZGdKILACciXbxfoLbbNgSDx5+KbE2c2m9is7\n"
                    + "MIZgRexTvnJa\n" + "-----END CERTIFICATE-----\n" };
}
