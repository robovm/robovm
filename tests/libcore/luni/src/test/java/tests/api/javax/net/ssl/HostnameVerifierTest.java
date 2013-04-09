/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package tests.api.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.x500.X500Principal;
import junit.framework.TestCase;
import org.apache.harmony.xnet.tests.support.mySSLSession;

public class HostnameVerifierTest extends TestCase implements
        CertificatesToPlayWith {

    /**
     * javax.net.ssl.HostnameVerifier#verify(String hostname, SSLSession
     *        session)
     */
    public final void test_verify() throws Exception {
        mySSLSession session = new mySSLSession("localhost", 1080, null);
        HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
        assertFalse(hv.verify("localhost", session));
    }

    // copied and modified from apache http client test suite.
    public void testVerify() throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in;
        X509Certificate x509;
        in = new ByteArrayInputStream(X509_FOO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] {x509});

        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
        assertTrue(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        assertFalse(verifier.verify("bar.com", session));

        in = new ByteArrayInputStream(X509_HANAKO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("\u82b1\u5b50.co.jp", session));
        assertFalse(verifier.verify("a.\u82b1\u5b50.co.jp", session));

        in = new ByteArrayInputStream(X509_FOO_BAR);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertFalse(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        assertTrue(verifier.verify("bar.com", session));
        assertFalse(verifier.verify("a.bar.com", session));

        in = new ByteArrayInputStream(X509_FOO_BAR_HANAKO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        // these checks test alternative subjects. The test data contains an
        // alternative subject starting with a japanese kanji character. This is
        // not supported by Android because the underlying implementation from
        // harmony follows the definition from rfc 1034 page 10 for alternative
        // subject names. This causes the code to drop all alternative subjects.
        // assertTrue(verifier.verify("bar.com", session));
        // assertFalse(verifier.verify("a.bar.com", session));
        // assertFalse(verifier.verify("a.\u82b1\u5b50.co.jp", session));

        in = new ByteArrayInputStream(X509_NO_CNS_FOO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));

        in = new ByteArrayInputStream(X509_NO_CNS_FOO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));

        in = new ByteArrayInputStream(X509_THREE_CNS_FOO_BAR_HANAKO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertFalse(verifier.verify("foo.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        assertFalse(verifier.verify("bar.com", session));
        assertFalse(verifier.verify("a.bar.com", session));
        assertTrue(verifier.verify("\u82b1\u5b50.co.jp", session));
        assertFalse(verifier.verify("a.\u82b1\u5b50.co.jp", session));

        in = new ByteArrayInputStream(X509_WILD_FOO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("foo.com", session));
        assertTrue(verifier.verify("www.foo.com", session));
        assertTrue(verifier.verify("\u82b1\u5b50.foo.com", session));
        assertFalse(verifier.verify("a.b.foo.com", session));

        in = new ByteArrayInputStream(X509_WILD_CO_JP);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        assertTrue(verifier.verify("foo.co.jp", session));
        assertTrue(verifier.verify("\u82b1\u5b50.co.jp", session));

        in = new ByteArrayInputStream(X509_WILD_FOO_BAR_HANAKO);
        x509 = (X509Certificate) cf.generateCertificate(in);
        session = new mySSLSession(new X509Certificate[] {x509});
        // try the foo.com variations
        assertTrue(verifier.verify("foo.com", session));
        assertTrue(verifier.verify("www.foo.com", session));
        assertTrue(verifier.verify("\u82b1\u5b50.foo.com", session));
        assertFalse(verifier.verify("a.b.foo.com", session));
        // these checks test alternative subjects. The test data contains an
        // alternative subject starting with a japanese kanji character. This is
        // not supported by Android because the underlying implementation from
        // harmony follows the definition from rfc 1034 page 10 for alternative
        // subject names. This causes the code to drop all alternative subjects.
        // assertFalse(verifier.verify("bar.com", session));
        // assertTrue(verifier.verify("www.bar.com", session));
        // assertTrue(verifier.verify("\u82b1\u5b50.bar.com", session));
        // assertTrue(verifier.verify("a.b.bar.com", session));
    }

    public void testSubjectAlt() throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(X509_MULTIPLE_SUBJECT_ALT);
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] {x509});

        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
        assertEquals(new X500Principal("CN=localhost"), x509.getSubjectX500Principal());

        assertTrue(verifier.verify("localhost", session));
        assertTrue(verifier.verify("localhost.localdomain", session));
        assertFalse(verifier.verify("local.host", session));
    }

    public void testVerifyIpAddress() throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(X509_MULTIPLE_SUBJECT_ALT);
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] { x509 });
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();

        assertTrue(verifier.verify("127.0.0.1", session));
        assertFalse(verifier.verify("127.0.0.2", session));
    }

    public void testWildcardsCannotMatchIpAddresses() throws Exception {
        // openssl req -x509 -nodes -days 36500 -subj '/CN=*.0.0.1' -newkey rsa:512 -out cert.pem
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIBkjCCATygAwIBAgIJAMdemqOwd/BEMA0GCSqGSIb3DQEBBQUAMBIxEDAOBgNV\n"
                + "BAMUByouMC4wLjEwIBcNMTAxMjIwMTY0NDI1WhgPMjExMDExMjYxNjQ0MjVaMBIx\n"
                + "EDAOBgNVBAMUByouMC4wLjEwXDANBgkqhkiG9w0BAQEFAANLADBIAkEAqY8c9Qrt\n"
                + "YPWCvb7lclI+aDHM6fgbJcHsS9Zg8nUOh5dWrS7AgeA25wyaokFl4plBbbHQe2j+\n"
                + "cCjsRiJIcQo9HwIDAQABo3MwcTAdBgNVHQ4EFgQUJ436TZPJvwCBKklZZqIvt1Yt\n"
                + "JjEwQgYDVR0jBDswOYAUJ436TZPJvwCBKklZZqIvt1YtJjGhFqQUMBIxEDAOBgNV\n"
                + "BAMUByouMC4wLjGCCQDHXpqjsHfwRDAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEB\n"
                + "BQUAA0EAk9i88xdjWoewqvE+iMC9tD2obMchgFDaHH0ogxxiRaIKeEly3g0uGxIt\n"
                + "fl2WRY8hb4x+zRrwsFaLEpdEvqcjOQ==\n"
                + "-----END CERTIFICATE-----";
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(cert.getBytes("UTF-8"));
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] { x509 });
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();

        assertFalse(verifier.verify("127.0.0.1", session));
    }

    /**
     * Earlier implementations of Android's hostname verifier required that
     * wildcard names wouldn't match "*.com" or similar. This was a nonstandard
     * check that we've since dropped. It is the CA's responsibility to not hand
     * out certificates that match so broadly.
     */
    public void testWildcardsDoesNotNeedTwoDots() throws Exception {
        // openssl req -x509 -nodes -days 36500 -subj '/CN=*.com' -newkey rsa:512 -out cert.pem
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIBjDCCATagAwIBAgIJAOVulXCSu6HuMA0GCSqGSIb3DQEBBQUAMBAxDjAMBgNV\n"
                + "BAMUBSouY29tMCAXDTEwMTIyMDE2NDkzOFoYDzIxMTAxMTI2MTY0OTM4WjAQMQ4w\n"
                + "DAYDVQQDFAUqLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDJd8xqni+h7Iaz\n"
                + "ypItivs9kPuiJUqVz+SuJ1C05SFc3PmlRCvwSIfhyD67fHcbMdl+A/LrIjhhKZJe\n"
                + "1joO0+pFAgMBAAGjcTBvMB0GA1UdDgQWBBS4Iuzf5w8JdCp+EtBfdFNudf6+YzBA\n"
                + "BgNVHSMEOTA3gBS4Iuzf5w8JdCp+EtBfdFNudf6+Y6EUpBIwEDEOMAwGA1UEAxQF\n"
                + "Ki5jb22CCQDlbpVwkruh7jAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA0EA\n"
                + "U6LFxmZr31lFyis2/T68PpjAppc0DpNQuA2m/Y7oTHBDi55Fw6HVHCw3lucuWZ5d\n"
                + "qUYo4ES548JdpQtcLrW2sA==\n"
                + "-----END CERTIFICATE-----";
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(cert.getBytes("UTF-8"));
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] { x509 });
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();

        assertTrue(verifier.verify("google.com", session));
    }

    public void testSubjectAltName() throws Exception {
        /*
         * $ cat ./cert.cnf
         * [req]
         * distinguished_name=distinguished_name
         * req_extensions=req_extensions
         * x509_extensions=x509_extensions
         * [distinguished_name]
         * [req_extensions]
         * [x509_extensions]
         * subjectAltName=DNS:bar.com,DNS:baz.com
         *
         * $ openssl req -x509 -nodes -days 36500 -subj '/CN=foo.com' -config ./cert.cnf \
         *     -newkey rsa:512 -out cert.pem
         */
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIBPTCB6KADAgECAgkA7zoHaaqNGHQwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n"
                + "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODM5MzZaGA8yMTEwMTEyNjE4MzkzNlowEjEQ\n"
                + "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQC+gmoSxF+8\n"
                + "hbV+rgRQqHIJd50216OWQJbU3BvdlPbca779NYO4+UZWTFdBM8BdQqs3H4B5Agvp\n"
                + "y7HeSff1F7XRAgMBAAGjHzAdMBsGA1UdEQQUMBKCB2Jhci5jb22CB2Jhei5jb20w\n"
                + "DQYJKoZIhvcNAQEFBQADQQBXpZZPOY2Dy1lGG81JTr8L4or9jpKacD7n51eS8iqI\n"
                + "oTznPNuXHU5bFN0AAGX2ij47f/EahqTpo5RdS95P4sVm\n"
                + "-----END CERTIFICATE-----";
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(cert.getBytes("UTF-8"));
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] { x509 });
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();

        assertFalse(verifier.verify("foo.com", session));
        assertTrue(verifier.verify("bar.com", session));
        assertTrue(verifier.verify("baz.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        assertFalse(verifier.verify("quux.com", session));
    }

    public void testSubjectAltNameWithWildcard() throws Exception {
        /*
         * $ cat ./cert.cnf
         * [req]
         * distinguished_name=distinguished_name
         * req_extensions=req_extensions
         * x509_extensions=x509_extensions
         * [distinguished_name]
         * [req_extensions]
         * [x509_extensions]
         * subjectAltName=DNS:bar.com,DNS:*.baz.com
         *
         * $ openssl req -x509 -nodes -days 36500 -subj '/CN=foo.com' -config ./cert.cnf \
         *     -newkey rsa:512 -out cert.pem
         */
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIBPzCB6qADAgECAgkAnv/7Jv5r7pMwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n"
                + "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODQ2MDFaGA8yMTEwMTEyNjE4NDYwMVowEjEQ\n"
                + "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDAz2YXnyog\n"
                + "YdYLSFr/OEgSumtwqtZKJTB4wqTW/eKbBCEzxnyUMxWZIqUGu353PzwfOuWp2re3\n"
                + "nvVV+QDYQlh9AgMBAAGjITAfMB0GA1UdEQQWMBSCB2Jhci5jb22CCSouYmF6LmNv\n"
                + "bTANBgkqhkiG9w0BAQUFAANBAB8yrSl8zqy07i0SNYx2B/FnvQY734pxioaqFWfO\n"
                + "Bqo1ZZl/9aPHEWIwBrxYNVB0SGu/kkbt/vxqOjzzrkXukmI=\n"
                + "-----END CERTIFICATE-----";
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(cert.getBytes("UTF-8"));
        X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
        mySSLSession session = new mySSLSession(new X509Certificate[] { x509 });
        HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();

        assertFalse(verifier.verify("foo.com", session));
        assertTrue(verifier.verify("bar.com", session));
        assertTrue(verifier.verify("a.baz.com", session));
        assertTrue(verifier.verify("baz.com", session));
        assertFalse(verifier.verify("a.foo.com", session));
        assertFalse(verifier.verify("a.bar.com", session));
        assertFalse(verifier.verify("quux.com", session));
    }
}
