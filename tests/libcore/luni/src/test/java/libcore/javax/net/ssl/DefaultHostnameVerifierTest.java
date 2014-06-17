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

package libcore.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.DefaultHostnameVerifier;
import javax.security.auth.x500.X500Principal;
import junit.framework.TestCase;

public final class DefaultHostnameVerifierTest extends TestCase {
    private static final int ALT_UNKNOWN = 0;
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;

    private final DefaultHostnameVerifier verifier = new DefaultHostnameVerifier();

    public void testVerify() {
        assertTrue(verifier.verify("imap.g.com", new StubX509Certificate("cn=imap.g.com")));
        assertFalse(verifier.verify("imap.g.com", new StubX509Certificate("cn=imap2.g.com")));
        assertFalse(verifier.verify("imap.g.com", new StubX509Certificate("cn=sub.imap.g.com")));
    }

    /**
     * If a subjectAltName extension of type ALT_DNS_NAME is present, that MUST
     * be used as the identity and the CN should be ignored.
     */
    public void testSubjectAltNameAndCn() {
        assertFalse(verifier.verify("imap.g.com", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.y.com")));
        assertFalse(verifier.verify("imap.g.com", new StubX509Certificate("cn=imap.g.com")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.y.com")));
        assertTrue(verifier.verify("imap.g.com", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_DNS_NAME, "imap.g.com")));
    }

    public void testSubjectAltNameWithWildcard() {
        assertTrue(verifier.verify("imap.g.com", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_DNS_NAME, "*.g.com")));
    }

    public void testSubjectAltNameWithIpAddress() {
        assertTrue(verifier.verify("1.2.3.4", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_IPA_NAME, "1.2.3.4")));
        assertFalse(verifier.verify("1.2.3.5", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_IPA_NAME, "1.2.3.4")));
        assertTrue(verifier.verify("192.168.100.1", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_IPA_NAME, "1.2.3.4")
                .addSubjectAlternativeName(ALT_IPA_NAME, "192.168.100.1")));
    }

    public void testUnknownSubjectAltName() {
        // Has unknown subject alternative names
        assertTrue(verifier.verify("imap.g.com", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 1")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 2")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.b.c.d")
                .addSubjectAlternativeName(ALT_DNS_NAME, "*.google.com")
                .addSubjectAlternativeName(ALT_DNS_NAME, "imap.g.com")
                .addSubjectAlternativeName(ALT_IPA_NAME, "2.33.44.55")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 3")));
        assertTrue(verifier.verify("2.33.44.55", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 1")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 2")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.b.c.d")
                .addSubjectAlternativeName(ALT_DNS_NAME, "*.google.com")
                .addSubjectAlternativeName(ALT_DNS_NAME, "imap.g.com")
                .addSubjectAlternativeName(ALT_IPA_NAME, "2.33.44.55")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 3")));
        assertFalse(verifier.verify("g.com", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 1")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 2")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.b.c.d")
                .addSubjectAlternativeName(ALT_DNS_NAME, "*.google.com")
                .addSubjectAlternativeName(ALT_DNS_NAME, "imap.g.com")
                .addSubjectAlternativeName(ALT_IPA_NAME, "2.33.44.55")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 3")));
        assertFalse(verifier.verify("2.33.44.1", new StubX509Certificate("")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 1")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 2")
                .addSubjectAlternativeName(ALT_DNS_NAME, "a.b.c.d")
                .addSubjectAlternativeName(ALT_DNS_NAME, "*.google.com")
                .addSubjectAlternativeName(ALT_DNS_NAME, "imap.g.com")
                .addSubjectAlternativeName(ALT_IPA_NAME, "2.33.44.55")
                .addSubjectAlternativeName(ALT_UNKNOWN, "random string 3")));
    }

    public void testWildcardMatchesWildcardSuffix() {
        assertTrue(verifier.verifyHostName("b.c.d", "*.b.c.d"));
        assertTrue(verifier.verifyHostName("imap.google.com", "*.imap.google.com"));
    }

    public void testWildcardMatchingSubstring() {
        assertTrue(verifier.verifyHostName("b.c.d", "b*.c.d"));
        assertTrue(verifier.verifyHostName("imap.google.com", "ima*.google.com"));
    }

    public void testWildcardMatchingEmptySubstring() {
        assertTrue(verifier.verifyHostName("imap.google.com", "imap*.google.com"));
    }

    public void testWildcardMatchesChildDomain() {
        assertFalse(verifier.verifyHostName("a.b.c.d", "*.c.d"));
    }

    public void testVerifyHostName() {
        assertTrue(verifier.verifyHostName("a.b.c.d", "a.b.c.d"));
        assertTrue(verifier.verifyHostName("a.b.c.d", "*.b.c.d"));
        assertFalse(verifier.verifyHostName("a.b.c.d", "*.*.c.d"));
        assertTrue(verifier.verifyHostName("imap.google.com", "imap.google.com"));
        assertFalse(verifier.verifyHostName("imap2.google.com", "imap.google.com"));
        assertTrue(verifier.verifyHostName("imap.google.com", "*.google.com"));
        assertTrue(verifier.verifyHostName("imap2.google.com", "*.google.com"));
        assertFalse(verifier.verifyHostName("imap.google.com", "*.googl.com"));
        assertFalse(verifier.verifyHostName("imap2.google2.com", "*.google3.com"));
        assertFalse(verifier.verifyHostName("imap.google.com", "a*.google.com"));
        assertFalse(verifier.verifyHostName("imap.google.com", "ix*.google.com"));
        assertTrue(verifier.verifyHostName("imap.google.com", "iMap.Google.Com"));
    }

    public void testSubjectOnlyCert() throws Exception {
        // subject: C=JP, CN=www.example.com
        // subject alt names: n/a
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIIC0TCCAbmgAwIBAgIJANCQbJPPw31SMA0GCSqGSIb3DQEBBQUAMCcxCzAJBgNV\n"
                + "BAYTAkpQMRgwFgYDVQQDEw93d3cuZXhhbXBsZS5jb20wIBcNMTAwMTEyMjA1ODE4\n"
                + "WhgPMjA2NDEwMTUyMDU4MThaMCcxCzAJBgNVBAYTAkpQMRgwFgYDVQQDEw93d3cu\n"
                + "ZXhhbXBsZS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDsdUJk\n"
                + "4KxADA3vlDHxNbyC27Ozw4yiSVzPTHUct471YmdDRW3orO2P5a5hRnUGV70gjH9X\n"
                + "MU4oeOdWYAgXB9pxfLyr6621k1+uNrmaZtzp0ECH9twcwxNJJFDZsN7o9vt7V6Ej\n"
                + "NN9weeqDr/aeQXo07a12vyVfR6jWO8jHB0e4aemwZNoYjNvM69fivQTse2ZoRVfj\n"
                + "eSHhjRTX6I8ry4a31Hwt+fT1QiWWNN6o7+WOtpJAhX3eg4smhSD1svi2kOT8tdUe\n"
                + "NS4hWlmXmumU9G4tI8PBurcLNTm7PB2lUlbn/IV18WavqKE/Uy/1WgAx+a1EJNdp\n"
                + "i07AG1PsqaONKkf1AgMBAAEwDQYJKoZIhvcNAQEFBQADggEBAJrNsuL7fZZNC8gL\n"
                + "BdePJ7DYW2e7mXANU3bCBe2BZqmXKQxKwibZnEsqA+yMLqcSd8uxISlyHY2tw9wT\n"
                + "4wB9KPIttfNLbwn/rk+MbOTHpvyF60d9WhJJVUkPBl8D4VuPSl+VnlA54kU9dtZN\n"
                + "+ZYdxYbNtSsI/Flz9SCoOV79W9GhN+uYJhv6RwyIMIHeMpZpyX1xSUVx5dZlmerQ\n"
                + "WAUvghDH3fFRt2ZdnA4OXoKkTAaM3Pv7PUMsnah8bux6MQi0AuLMWFWOI1H34koH\n"
                + "rs2oQLwOLnuifH52ey9+tJguabo+brlYYigAuWWFEzJfBzikDkIwnE/L7wlrypIk\n"
                + "taXDWI4=\n"
                + "-----END CERTIFICATE-----");
        assertTrue(verifier.verify("www.example.com", cert));
        assertFalse(verifier.verify("www2.example.com", cert));
    }

    public void testSubjectAltOnlyCert() throws Exception {
        // subject: C=JP (no CN)
        // subject alt names: DNS:www.example.com
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIICvTCCAaWgAwIBAgIJALbA0TZk2YmNMA0GCSqGSIb3DQEBBQUAMA0xCzAJBgNV\n"
                + "BAYTAkpQMCAXDTEwMDExMjIwNTg1NFoYDzIwNjQxMDE1MjA1ODU0WjANMQswCQYD\n"
                + "VQQGEwJKUDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMEg6acVC9V4\n"
                + "xNGoLNVLPbqBc8IvMvcsc88dF6MW3d9VagX3aeWU8c79tI/KOV/1AOakH7WYxw/w\n"
                + "yD8aOX7+9BK1Hu0qKKKbSM+ycqaMthXd6xytrNDsIx5WiGUz8zTko0Gk3orIR7p7\n"
                + "rPcNzB/zwtESkscqPv85aEn7S/yClNkzLfEzm3CtaYOc0tfhBMyzi/ipXzGMxUmx\n"
                + "PvOLr3v/Oz5pZEQw7Kxlm4+tAtn7bJlHziQ1UW4WPIy+T3hySBEpODFiqZi7Ok3X\n"
                + "Zjxdii62fgo5B2Ee7q5Amo0mUIwcQTDjJ2CLAqzYnSh3tpiPJGjEIjmRyCoMQ1bx\n"
                + "7D+y7nSPIq8CAwEAAaMeMBwwGgYDVR0RBBMwEYIPd3d3LmV4YW1wbGUuY29tMA0G\n"
                + "CSqGSIb3DQEBBQUAA4IBAQBsGEh+nHc0l9FJTzWqvG3qs7i6XoJZdtThCDx4HjKJ\n"
                + "8GMrJtreNN4JvIxn7KC+alVbnILjzCRO+c3rsnpxKBi5cp2imjuw5Kf/x2Seimb9\n"
                + "UvZbaJvBVOzy4Q1IGef9bLy3wZzy2/WfBFyvPTAkgkRaX7LN2jnYOYVhNoNFrwqe\n"
                + "EWxkA6fzrpyseUEFeGFFjGxRSRCDcQ25Eq6d9rkC1x21zNtt4QwZBO0wHrTy155M\n"
                + "JPRynf9244Pn0Sr/wsnmdsTRFIFYynrc51hQ7DkwbUxpcaewkZzilru/SwZ3+pPT\n"
                + "9JSqm5hJ1pg5WDlPkW7c/1VA0/141N52Q8MIU+2ZpuOj\n"
                + "-----END CERTIFICATE-----");
        assertTrue(verifier.verify("www.example.com", cert));
        assertFalse(verifier.verify("www2.example.com", cert));
    }

    public void testSubjectWithAltNamesCert() throws Exception {
        // subject: C=JP, CN=www.example.com
        // subject alt names: DNS:www2.example.com, DNS:www3.example.com
        // * Subject should be ignored, because it has subject alt names.
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIIDBDCCAeygAwIBAgIJALv14qjcuhw9MA0GCSqGSIb3DQEBBQUAMCcxCzAJBgNV\n"
                + "BAYTAkpQMRgwFgYDVQQDEw93d3cuZXhhbXBsZS5jb20wIBcNMTAwMTEyMjA1OTM4\n"
                + "WhgPMjA2NDEwMTUyMDU5MzhaMCcxCzAJBgNVBAYTAkpQMRgwFgYDVQQDEw93d3cu\n"
                + "ZXhhbXBsZS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCiTVgU\n"
                + "kBO9KNYZZLmiPR0eBrk8u61CLnm35BGKW8EFpDaINLbbIFIQvqOMekURON/N+xFY\n"
                + "D8roo7aFZVuHWAUqFcOJ4e6NmviK5qocLihtzAexsw4f4AzZxM3A8kcLlWLyAt7e\n"
                + "EVLxhcMHogY7GaF6q+33Z8p+zp6x3tj07mwyPrriCLse2PeRsRunZl/fp/VvRlr6\n"
                + "YbC7CbRrhnIv5nqohs8BsbBiiFpxQftsMQmiXhY2LUzqY2RXUIOw24fHjoQkHTL2\n"
                + "4z5nUM3b6ueQe+CBnobUS6fzK/36Nct4dRpev9i/ORdRLuIDKJ+QR16G1V/BJYBR\n"
                + "dAK+3iXvg6z8vP1XAgMBAAGjMTAvMC0GA1UdEQQmMCSCEHd3dzIuZXhhbXBsZS5j\n"
                + "b22CEHd3dzMuZXhhbXBsZS5jb20wDQYJKoZIhvcNAQEFBQADggEBAJQNf38uXm3h\n"
                + "0vsF+Yd6/HqM48Su7tWnTDAfTXnQZZkzjzITq3JXzquMXICktAVN2cLnT9zPfRAE\n"
                + "8V8A3BNO5zXiR5W3o/mJP5HQ3/WxpzBGM2N+YmDCJyBoQrIVaAZaXAZUaBBvn5A+\n"
                + "kEVfGWquwIFuvA67xegbJOCRLD4eUzRdNsn5+NFiakWO1tkFqEzqyQ0PNPviRjgu\n"
                + "z9NxdPvd1JQOhydkucsPKJzlEBbGyL5QL/Jkot3Qy+FOeuNzgQUfAGtQgzRrsZDK\n"
                + "hrTVypLSoRXuTB2aWilu4p6aNh84xTdyqo2avtNr2MiQMZIcdamBq8LdBIAShFXI\n"
                + "h5G2eVGXH/Y=\n"
                + "-----END CERTIFICATE-----");
        assertFalse(verifier.verify("www.example.com", cert));
        assertTrue(verifier.verify("www2.example.com", cert));
        assertTrue(verifier.verify("www3.example.com", cert));
        assertFalse(verifier.verify("www4.example.com", cert));
    }

    public void testSubjectWithWildAltNamesCert() throws Exception {
        // subject: C=JP, CN=www.example.com
        // subject alt names: DNS:*.example2.com
        // * Subject should be ignored, because it has subject alt names.
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIIC8DCCAdigAwIBAgIJAL/oWJ64VAdXMA0GCSqGSIb3DQEBBQUAMCcxCzAJBgNV\n"
                + "BAYTAkpQMRgwFgYDVQQDEw93d3cuZXhhbXBsZS5jb20wIBcNMTAwMTEyMjEwMDAx\n"
                + "WhgPMjA2NDEwMTUyMTAwMDFaMCcxCzAJBgNVBAYTAkpQMRgwFgYDVQQDEw93d3cu\n"
                + "ZXhhbXBsZS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCbx1QB\n"
                + "92iea7VybLYICA4MX4LWipYrRsgXUXQrcIQ3YLTQ9rH0VwScrHL4O4JDxgXCQnR+\n"
                + "4VOzD42q1KXHJAqzqGUYCNPyvZEzkGCnQ4FBIUEmxZd5SNEefJVH3Z6GizYJomTh\n"
                + "p78yDcoqymD9umxRC2cWFu8GscfFGMVyhsqLlOofu7UWOs22mkXPo43jDx+VOAoV\n"
                + "n48YP3P57a2Eo0gcd4zVL00y62VegqBO/1LW38aTS7teiCBFc1TkNYa5I40yN9lP\n"
                + "rB9ICHYQWyzf/7OxU9iauEK2w6DmSsQoLs9JzEhgeNZddkcc77ciSUCo2Hx0VpOJ\n"
                + "BFyf2rbryJeAk+FDAgMBAAGjHTAbMBkGA1UdEQQSMBCCDiouZXhhbXBsZTIuY29t\n"
                + "MA0GCSqGSIb3DQEBBQUAA4IBAQA2a14pRL+4laJ8sscQlucaDB/oSdb0cwhk4IkE\n"
                + "kKl/ZKr6rKwPZ81sJRgzvI4imLbUAKt4AJHdpI9cIQUq1gw9bzil7LKwmFtFSPmC\n"
                + "MYb1iadaYrvp7RE4yXrWCcSbU0hup9JQLHTrHLlqLtRuU48NHMvWYThBcS9Q/hQp\n"
                + "nJ/JxYy3am99MHALWLAfuRxQXhE4C5utDmBwI2KD6A8SA30s+CnuegmkYScuSqBu\n"
                + "Y3R0HZvKzNIU3pwAm69HCJoG+/9MZEIDJb0WJc5UygxDT45XE9zQMQe4dBOTaNXT\n"
                + "+ntgaB62kE10HzrzpqXAgoAWxWK4RzFcUpBWw9qYq9xOCewJ\n"
                + "-----END CERTIFICATE-----");
        assertFalse(verifier.verify("www.example.com", cert));
        assertFalse(verifier.verify("www2.example.com", cert));
        assertTrue(verifier.verify("www.example2.com", cert));
        assertTrue(verifier.verify("abc.example2.com", cert));
        assertFalse(verifier.verify("www.example3.com", cert));
    }

    public void testWildAltNameOnlyCert() throws Exception {
        // subject: C=JP
        // subject alt names: DNS:*.example.com
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIICuzCCAaOgAwIBAgIJAP82tgcvmAGxMA0GCSqGSIb3DQEBBQUAMA0xCzAJBgNV\n"
                + "BAYTAkpQMCAXDTEwMDExMjIxMDAyN1oYDzIwNjQxMDE1MjEwMDI3WjANMQswCQYD\n"
                + "VQQGEwJKUDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALs528EQbcB1\n"
                + "x4BwxthQBZrgDJzoO7KPV3dhGYoeP8EnRjapZm+T/sj9P/O4HvfxjnB+fsjYSdmE\n"
                + "WWUtnFrP7wtG9DUC748Ea2PMV8WFhOG58dqBNIko5XzkHB7SxkNZD5S/0KQYMGLr\n"
                + "rchDsDlmsEf2Qb6qiqpNEU70aSkExZJcH+B9nWdeBpsVFu7wtezwSWEc2NUa2bhW\n"
                + "gcXQ/aafwHZ4o2PyGwy0sgS/UifqO9tEllC2tPleSNJOmYsVudv5Bz4Q0GG38BSz\n"
                + "Pc0IcOoln0ZWpXbGr03V2vlXWCwzaFAl3I1T3O7YVqDiaSWoP+d0tHZzmw8aJLXd\n"
                + "B+KaUUGxRPsCAwEAAaMcMBowGAYDVR0RBBEwD4INKi5leGFtcGxlLmNvbTANBgkq\n"
                + "hkiG9w0BAQUFAAOCAQEAJbVan4QgJ0cvpJnK9UWIVJNC+UbP87RC5go2fQiTnmGv\n"
                + "prOrIuMqz1+vGcpIheLTLctJRHPoadXq0+UbQEIaU3pQbY6C4nNdfl+hcvmJeqrt\n"
                + "kOCcvmIamO68iNvTSeszuHuu4O38PefrW2Xd0nn7bjFZrzBzHFhTudmnqNliP3ue\n"
                + "KKQpqkUt5lCytnH8V/u/UCWdvVx5LnUa2XFGVLi3ongBIojW5fvF+yxn9ADqxdrI\n"
                + "va++ow5r1VxQXFJc0ZPzsDo+6TlktoDHaRQJGMqQomqHWT4i7F5UZgf6BHGfEUPU\n"
                + "qep+GsF3QRHSBtpObWkVDZNFvky3a1iZ2q25+hFIqQ==\n"
                + "-----END CERTIFICATE-----");
        assertTrue(verifier.verify("www.example.com", cert));
        assertTrue(verifier.verify("www2.example.com", cert));
        assertFalse(verifier.verify("www.example2.com", cert));
    }

    public void testAltIpOnlyCert() throws Exception {
        // subject: C=JP
        // subject alt names: IP Address:192.168.10.1
        X509Certificate cert = parseCertificate("-----BEGIN CERTIFICATE-----\n"
                + "MIICsjCCAZqgAwIBAgIJALrC37YAXFIeMA0GCSqGSIb3DQEBBQUAMA0xCzAJBgNV\n"
                + "BAYTAkpQMCAXDTEwMDExMjIxMzk0NloYDzIwNjQxMDE1MjEzOTQ2WjANMQswCQYD\n"
                + "VQQGEwJKUDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALr8s/4Abpby\n"
                + "IYks5YCJE2nbWH7kj6XbwnRzsVP9RVC33bPoQ1M+2ZY24HqkigjQS/HEXR0s0bYh\n"
                + "dewNUnTj1uGyGs6cYzsbu7x114vmVYqjxUo3hKjwfYiPeF6f3IE1vpLI7I2G32gq\n"
                + "Zwm9c1/vXNHIdWQxCpFcuPA8P3YGfoApFX4pQPFplBUNAQqnjdmA68cbxxMC+1F3\n"
                + "mX42D7iIEVwyVpah5HjyxjIZQlf3X7QBj0bCmkL+ibIHTALrkNNwNM6i4xzYLz/5\n"
                + "14GkN9ncHY87eSOk6r53ptER6mQMhCe9qPRjSHnpWTTyj6IXTaYe+dDQw657B80w\n"
                + "cSHL7Ed25zUCAwEAAaMTMBEwDwYDVR0RBAgwBocEwKgKATANBgkqhkiG9w0BAQUF\n"
                + "AAOCAQEAgrwrtOWZT3fbi1AafpGaAiOBWSJqYqRhtQy0AfiZBxv1U0XaYqmZmpnq\n"
                + "DVAqr0NkljowD28NBrxIFO5gBNum2ZOPDl2/5vjFn+IirUCJ9u9wS7zYkTCW2lQR\n"
                + "xE7Ic3mfWv7wUbKDfjlWqP1IDHUxwkrBTAl+HnwOPiaKKk1ttwcrgS8AHlqASe03\n"
                + "mlwnvJ+Stk54IneRaegL0L93sNAy63RZqnPCTxGz7eHcFwX8Jdr4sbxTxQqV6pIc\n"
                + "WPjHQcWfpkFzAF5wyOq0kveVfx0g5xPhOVDd+U+q7WastbXICpCoHp9FxISmZVik\n"
                + "sAyifp8agkYdzaSh55fFmKXlFnRsQw==\n"
                + "-----END CERTIFICATE-----");
        assertTrue(verifier.verify("192.168.10.1", cert));
        assertFalse(verifier.verify("192.168.10.2", cert));
    }

    X509Certificate parseCertificate(String encoded) throws Exception {
        InputStream in = new ByteArrayInputStream(encoded.getBytes(StandardCharsets.US_ASCII));
        return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(in);
    }

    private static class StubX509Certificate extends X509Certificate {
        private final X500Principal subjectX500Principal;
        private Collection<List<?>> subjectAlternativeNames;

        public StubX509Certificate(String subjectDn) {
            subjectX500Principal = new X500Principal(subjectDn);
            subjectAlternativeNames = null;
        }

        public StubX509Certificate addSubjectAlternativeName(int type, String name) {
            if (subjectAlternativeNames == null) {
                subjectAlternativeNames = new ArrayList<List<?>>();
            }
            LinkedList<Object> entry = new LinkedList<Object>();
            entry.add(type);
            entry.add(name);
            subjectAlternativeNames.add(entry);
            return this;
        }

        @Override public Collection<List<?>> getSubjectAlternativeNames() {
            return subjectAlternativeNames;
        }

        @Override public X500Principal getSubjectX500Principal() {
            return subjectX500Principal;
        }

        @Override public void checkValidity() {
            throw new UnsupportedOperationException();
        }

        @Override public void checkValidity(Date date) {
            throw new UnsupportedOperationException();
        }

        @Override public int getBasicConstraints() {
            throw new UnsupportedOperationException();
        }

        @Override public Principal getIssuerDN() {
            throw new UnsupportedOperationException();
        }

        @Override public boolean[] getIssuerUniqueID() {
            throw new UnsupportedOperationException();
        }

        @Override public boolean[] getKeyUsage() {
            throw new UnsupportedOperationException();
        }

        @Override public Date getNotAfter() {
            throw new UnsupportedOperationException();
        }

        @Override public Date getNotBefore() {
            throw new UnsupportedOperationException();
        }

        @Override public BigInteger getSerialNumber() {
            throw new UnsupportedOperationException();
        }

        @Override public String getSigAlgName() {
            throw new UnsupportedOperationException();
        }

        @Override public String getSigAlgOID() {
            throw new UnsupportedOperationException();
        }

        @Override public byte[] getSigAlgParams() {
            throw new UnsupportedOperationException();
        }

        @Override public byte[] getSignature() {
            throw new UnsupportedOperationException();
        }

        @Override public Principal getSubjectDN() {
            throw new UnsupportedOperationException();
        }

        @Override public boolean[] getSubjectUniqueID() {
            throw new UnsupportedOperationException();
        }

        @Override public byte[] getTBSCertificate() {
            throw new UnsupportedOperationException();
        }

        @Override public int getVersion() {
            throw new UnsupportedOperationException();
        }

        @Override public byte[] getEncoded() {
            throw new UnsupportedOperationException();
        }

        @Override public PublicKey getPublicKey() {
            throw new UnsupportedOperationException();
        }

        @Override public String toString() {
            throw new UnsupportedOperationException();
        }

        @Override public void verify(PublicKey key) {
            throw new UnsupportedOperationException();
        }

        @Override public void verify(PublicKey key, String sigProvider) {
            throw new UnsupportedOperationException();
        }

        @Override public Set<String> getCriticalExtensionOIDs() {
            throw new UnsupportedOperationException();
        }

        @Override public byte[] getExtensionValue(String oid) {
            throw new UnsupportedOperationException();
        }

        @Override public Set<String> getNonCriticalExtensionOIDs() {
            throw new UnsupportedOperationException();
        }

        @Override public boolean hasUnsupportedCriticalExtension() {
            throw new UnsupportedOperationException();
        }
    }
}
