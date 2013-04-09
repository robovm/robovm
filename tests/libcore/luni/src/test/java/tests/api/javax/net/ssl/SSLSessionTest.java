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

package tests.api.javax.net.ssl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import junit.framework.TestCase;
import libcore.io.Base64;
import tests.api.javax.net.ssl.HandshakeCompletedEventTest.MyHandshakeListener;
import tests.api.javax.net.ssl.HandshakeCompletedEventTest.TestTrustManager;
import tests.support.Support_PortManager;

/**
 * Tests for SSLSession class
 *
 */
public class SSLSessionTest extends TestCase {

    // set to true if on Android, false if on RI
    boolean useBKS = true;

    /**
     * javax.net.ssl.SSLSession#getPeerHost()
     * javax.net.ssl.SSLSession#getPeerPort()
     */
    public void test_getPeerHost() throws Exception {
        SSLSession s = clientSession;
        assertEquals(InetAddress.getLocalHost().getHostName(), s.getPeerHost());
        assertEquals(port, s.getPeerPort());
    }

    /**
     * javax.net.ssl.SSLSession#invalidate()
     * javax.net.ssl.SSLSession#isValid()
     */
    public void test_invalidate() {
        SSLSession s = clientSession;
        assertTrue(s.isValid());
        s.invalidate();
        assertFalse(s.isValid());
    }

    /**
     * javax.net.ssl.SSLSession#getPeerPrincipal()
     */
    public void test_getPeerPrincipal() throws Exception {
        Principal p1 = clientSession.getPeerPrincipal();
        KeyStore store = server.getStore();
        X509Certificate cert = (X509Certificate)store.getCertificate("mykey");
        Principal p2 = cert.getSubjectX500Principal();
        assertEquals(p1, p2);
    }

    /**
     * javax.net.ssl.SSLSession#getApplicationBufferSize()
     */
    public void test_getApplicationBufferSize() {
        assertTrue(clientSession.getApplicationBufferSize() > 0);
    }

    /**
     * javax.net.ssl.SSLSession#getCipherSuite()
     */
    public void test_getCipherSuite() {
        assertEquals(CIPHER_SUITE, clientSession.getCipherSuite());
    }

    /**
     * javax.net.ssl.SSLSession#getCreationTime()
     */
    public void test_getCreationTime() {
        // check if creation time was in the last 10 seconds
        long currentTime = System.currentTimeMillis();
        long sessionTime = clientSession.getCreationTime();
        long diff = currentTime - sessionTime;
        assertTrue("diff between " + currentTime + " and " + sessionTime + " should be < 10000",
                   diff < 10000);
    }

    /**
     * javax.net.ssl.SSLSession#getId()
     */
    public void test_getId() {
        byte[] id = clientSession.getId();
        SSLSession sess = clientSslContext.getClientSessionContext().getSession(id);
        assertNotNull("Could not find session for id " + id, sess);
        assertEquals(clientSession, sess);
    }

    /**
     * javax.net.ssl.SSLSession#getLastAccessedTime()
     */
    public void test_getLastAccessedTime() {
        // check if last access time was in the last 10 seconds
        long currentTime = System.currentTimeMillis();
        long sessionTime = clientSession.getLastAccessedTime();
        long diff = currentTime - sessionTime;
        assertTrue("diff between " + currentTime + " and " + sessionTime + " should be < 10000",
                   diff < 10000);
        assertTrue ("diff should be < 10000 but is " + diff, diff < 10000);
    }

    /**
     * javax.net.ssl.SSLSession#getLocalCertificates()
     */
    public void test_getLocalCertificates() throws Exception {
        KeyStore store = client.getStore();
        Certificate cert = store.getCertificate("mykey");
        Certificate[] certs = clientSession.getLocalCertificates();
        assertEquals(cert, certs[0]);
    }

    /**
     * javax.net.ssl.SSLSession#getLocalPrincipal()
     */
    public void test_getLocalPrincipal() throws Exception {
        Principal p1 = clientSession.getLocalPrincipal();
        KeyStore store = client.getStore();
        X509Certificate cert = (X509Certificate)store.getCertificate("mykey");
        Principal p2 = cert.getSubjectX500Principal();
        assertEquals(p1, p2);
    }

    /**
     * javax.net.ssl.SSLSession#getPacketBufferSize()
     */
    public void test_getPacketBufferSize() {
        assertTrue(clientSession.getPacketBufferSize() > 0);
    }

    /**
     * javax.net.ssl.SSLSession#getPeerCertificates()
     */
    public void test_getPeerCertificates() throws Exception {
        Certificate[] res = clientSession.getPeerCertificates();
        assertTrue(res.length > 0);
    }

    /**
     * javax.net.ssl.SSLSession#getPeerCertificateChain()
     */
    public void test_getPeerCertificateChain() throws Exception {
        javax.security.cert.X509Certificate[] res = clientSession.getPeerCertificateChain();
        assertTrue(res.length > 0);
    }

    /**
     * javax.net.ssl.SSLSession#getProtocol()
     */
    public void test_getProtocol() {
        assertEquals("TLSv1", clientSession.getProtocol());
    }

    /**
     * javax.net.ssl.SSLSession#getSessionContext()
     */
    public void test_getSessionContext() {
        assertEquals(clientSession.getSessionContext(),
                     clientSslContext.getClientSessionContext());
    }

    /**
     * javax.net.ssl.SSLSession#putValue(String name, Object value)
     * javax.net.ssl.SSLSession#removeValue(String name)
     * javax.net.ssl.SSLSession#getValueNames()
     */
    public void test_putValue() {
        SSLSession s = clientSession;
        mySSLSessionBindingListener sbl = new mySSLSessionBindingListener();
        assertNotNull(s.getValueNames());
        assertEquals(0, s.getValueNames().length);
        s.putValue("Name_01", sbl);
        s.putValue("Name_02", sbl);
        s.putValue("Name_03", sbl);
        assertEquals(3, s.getValueNames().length);
        s.removeValue("Name_01");
        assertEquals(2, s.getValueNames().length);

        try {
            s.putValue(null, null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
            // expected
        }
        try {
            s.putValue("ABC", null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
            // expected
        }
        try {
            s.putValue(null, sbl);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
            // expected
        }

        try {
            s.removeValue(null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    /**
     * javax.net.ssl.SSLSession#getValue(String name)
     */
    public void test_getValue() {
        SSLSession s = clientSession;
        mySSLSessionBindingListener sbl = new mySSLSessionBindingListener();

        try {
            s.getValue(null);
            fail("IllegalArgumentException wasn't thrown");
        } catch (IllegalArgumentException expected) {
            // expected
        }

        s.putValue("Name", sbl);
        Object obj = s.getValue("Name");
        assertTrue(obj instanceof SSLSessionBindingListener);
    }

    Thread serverThread, clientThread;
    TestServer server;
    TestClient client;

    @Override
    protected void setUp() {
        port = Support_PortManager.getNextPort();
        String serverKeys = (useBKS ? SERVER_KEYS_BKS : SERVER_KEYS_JKS);
        String clientKeys = (useBKS ? CLIENT_KEYS_BKS : CLIENT_KEYS_JKS);
        server = new TestServer(true,
                TestServer.CLIENT_AUTH_WANTED, serverKeys);
        client = new TestClient(true, clientKeys);

        serverThread = new Thread(server);
        clientThread = new Thread(client);

        serverThread.start();
        try {
            Thread.currentThread().sleep(1000);
            clientThread.start();
        } catch (InterruptedException e) {
            fail("Could not create server or cient " + e.getMessage());
        }
        while (clientSession == null
                && server.exception == null
                && client.exception == null) {
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                fail("couldn't create session");
            }
        }
        if (server.exception != null) {
            server.exception.printStackTrace();
        }
        assertNull("server thread has a pending exception: " + server.exception,
                server.exception);
        if (client.exception != null) {
            client.exception.printStackTrace();
        }
        assertNull("client thread has a pending exception: " + client.exception,
                client.exception);
        assertNotNull("Could not initialize session", clientSession);
    }

    @Override
    protected void tearDown() {
        notFinished = false;
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            clientThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // The server must have completed without an exception.
        if (server.getException() != null) {
            throw new RuntimeException(server.getException());
        }

        // The client must have completed without an exception.
        if (client.getException() != null) {
            throw new RuntimeException(client.getException());
        }
    }

    public class mySSLSessionBindingListener implements
            SSLSessionBindingListener {
        mySSLSessionBindingListener() {
        }
        public void valueBound(SSLSessionBindingEvent event) {}
        public void valueUnbound(SSLSessionBindingEvent event) {}
    }

    /**
     * Defines the keystore contents for the server, BKS version. Holds just a
     * single self-generated key. The subject name is "Test Server".
     */
    private static final String SERVER_KEYS_BKS =
            "AAAAAQAAABQDkebzoP1XwqyWKRCJEpn/t8dqIQAABDkEAAVteWtleQAAARpYl20nAAAAAQAFWC41"
            + "MDkAAAJNMIICSTCCAbKgAwIBAgIESEfU1jANBgkqhkiG9w0BAQUFADBpMQswCQYDVQQGEwJVUzET"
            + "MBEGA1UECBMKQ2FsaWZvcm5pYTEMMAoGA1UEBxMDTVRWMQ8wDQYDVQQKEwZHb29nbGUxEDAOBgNV"
            + "BAsTB0FuZHJvaWQxFDASBgNVBAMTC1Rlc3QgU2VydmVyMB4XDTA4MDYwNTExNTgxNFoXDTA4MDkw"
            + "MzExNTgxNFowaTELMAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExDDAKBgNVBAcTA01U"
            + "VjEPMA0GA1UEChMGR29vZ2xlMRAwDgYDVQQLEwdBbmRyb2lkMRQwEgYDVQQDEwtUZXN0IFNlcnZl"
            + "cjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA0LIdKaIr9/vsTq8BZlA3R+NFWRaH4lGsTAQy"
            + "DPMF9ZqEDOaL6DJuu0colSBBBQ85hQTPa9m9nyJoN3pEi1hgamqOvQIWcXBk+SOpUGRZZFXwniJV"
            + "zDKU5nE9MYgn2B9AoiH3CSuMz6HRqgVaqtppIe1jhukMc/kHVJvlKRNy9XMCAwEAATANBgkqhkiG"
            + "9w0BAQUFAAOBgQC7yBmJ9O/eWDGtSH9BH0R3dh2NdST3W9hNZ8hIa8U8klhNHbUCSSktZmZkvbPU"
            + "hse5LI3dh6RyNDuqDrbYwcqzKbFJaq/jX9kCoeb3vgbQElMRX8D2ID1vRjxwlALFISrtaN4VpWzV"
            + "yeoHPW4xldeZmoVtjn8zXNzQhLuBqX2MmAAAAqwAAAAUvkUScfw9yCSmALruURNmtBai7kQAAAZx"
            + "4Jmijxs/l8EBaleaUru6EOPioWkUAEVWCxjM/TxbGHOi2VMsQWqRr/DZ3wsDmtQgw3QTrUK666sR"
            + "MBnbqdnyCyvM1J2V1xxLXPUeRBmR2CXorYGF9Dye7NkgVdfA+9g9L/0Au6Ugn+2Cj5leoIgkgApN"
            + "vuEcZegFlNOUPVEs3SlBgUF1BY6OBM0UBHTPwGGxFBBcetcuMRbUnu65vyDG0pslT59qpaR0TMVs"
            + "P+tcheEzhyjbfM32/vwhnL9dBEgM8qMt0sqF6itNOQU/F4WGkK2Cm2v4CYEyKYw325fEhzTXosck"
            + "MhbqmcyLab8EPceWF3dweoUT76+jEZx8lV2dapR+CmczQI43tV9btsd1xiBbBHAKvymm9Ep9bPzM"
            + "J0MQi+OtURL9Lxke/70/MRueqbPeUlOaGvANTmXQD2OnW7PISwJ9lpeLfTG0LcqkoqkbtLKQLYHI"
            + "rQfV5j0j+wmvmpMxzjN3uvNajLa4zQ8l0Eok9SFaRr2RL0gN8Q2JegfOL4pUiHPsh64WWya2NB7f"
            + "V+1s65eA5ospXYsShRjo046QhGTmymwXXzdzuxu8IlnTEont6P4+J+GsWk6cldGbl20hctuUKzyx"
            + "OptjEPOKejV60iDCYGmHbCWAzQ8h5MILV82IclzNViZmzAapeeCnexhpXhWTs+xDEYSKEiG/camt"
            + "bhmZc3BcyVJrW23PktSfpBQ6D8ZxoMfF0L7V2GQMaUg+3r7ucrx82kpqotjv0xHghNIm95aBr1Qw"
            + "1gaEjsC/0wGmmBDg1dTDH+F1p9TInzr3EFuYD0YiQ7YlAHq3cPuyGoLXJ5dXYuSBfhDXJSeddUkl"
            + "k1ufZyOOcskeInQge7jzaRfmKg3U94r+spMEvb0AzDQVOKvjjo1ivxMSgFRZaDb/4qw=";

    /**
     * Defines the keystore contents for the client, BKS version. Holds just a
     * single self-generated key. The subject name is "Test Client".
     */
    private static final String CLIENT_KEYS_BKS =
            "AAAAAQAAABT4Rka6fxbFps98Y5k2VilmbibNkQAABfQEAAVteWtleQAAARpYl+POAAAAAQAFWC41"
            + "MDkAAAJNMIICSTCCAbKgAwIBAgIESEfU9TANBgkqhkiG9w0BAQUFADBpMQswCQYDVQQGEwJVUzET"
            + "MBEGA1UECBMKQ2FsaWZvcm5pYTEMMAoGA1UEBxMDTVRWMQ8wDQYDVQQKEwZHb29nbGUxEDAOBgNV"
            + "BAsTB0FuZHJvaWQxFDASBgNVBAMTC1Rlc3QgQ2xpZW50MB4XDTA4MDYwNTExNTg0NVoXDTA4MDkw"
            + "MzExNTg0NVowaTELMAkGA1UEBhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExDDAKBgNVBAcTA01U"
            + "VjEPMA0GA1UEChMGR29vZ2xlMRAwDgYDVQQLEwdBbmRyb2lkMRQwEgYDVQQDEwtUZXN0IENsaWVu"
            + "dDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEApUvmWsQDHPpbDKK13Yez2/q54tTOmRml/qva"
            + "2K6dZjkjSTW0iRuk7ztaVEvdJpfVIDv1oBsCI51ttyLHROy1epjF+GoL74mJb7fkcd0VOoSOTjtD"
            + "+3GgZkHPAm5YmUYxiJXqxKKJJqMCTIW46eJaA2nAep9QIwZ14/NFAs4ObV8CAwEAATANBgkqhkiG"
            + "9w0BAQUFAAOBgQCJrCr3hZQFDlLIfsSKI1/w+BLvyf4fubOid0pBxfklR8KBNPTiqjSmu7pd/C/F"
            + "1FR8CdZUDoPflZHCOU+fj5r5KUC1HyigY/tEUvlforBpfB0uCF+tXW4DbUfOWhfMtLV4nCOJOOZg"
            + "awfZLJWBJouLKOp427vDftxTSB+Ks8YjlgAAAqwAAAAU+NH6TtrzjyDdCXm5B6Vo7xX5G4YAAAZx"
            + "EAUkcZtmykn7YdaYxC1jRFJ+GEJpC8nZVg83QClVuCSIS8a5f8Hl44Bk4oepOZsPzhtz3RdVzDVi"
            + "RFfoyZFsrk9F5bDTVJ6sQbb/1nfJkLhZFXokka0vND5AXMSoD5Bj1Fqem3cK7fSUyqKvFoRKC3XD"
            + "FQvhqoam29F1rbl8FaYdPvhhZo8TfZQYUyUKwW+RbR44M5iHPx+ykieMe/C/4bcM3z8cwIbYI1aO"
            + "gjQKS2MK9bs17xaDzeAh4sBKrskFGrDe+2dgvrSKdoakJhLTNTBSG6m+rzqMSCeQpafLKMSjTSSz"
            + "+KoQ9bLyax8cbvViGGju0SlVhquloZmKOfHr8TukIoV64h3uCGFOVFtQjCYDOq6NbfRvMh14UVF5"
            + "zgDIGczoD9dMoULWxBmniGSntoNgZM+QP6Id7DBasZGKfrHIAw3lHBqcvB5smemSu7F4itRoa3D8"
            + "N7hhUEKAc+xA+8NKmXfiCBoHfPHTwDvt4IR7gWjeP3Xv5vitcKQ/MAfO5RwfzkYCXQ3FfjfzmsE1"
            + "1IfLRDiBj+lhQSulhRVStKI88Che3M4JUNGKllrc0nt1pWa1vgzmUhhC4LSdm6trTHgyJnB6OcS9"
            + "t2furYjK88j1AuB4921oxMxRm8c4Crq8Pyuf+n3YKi8Pl2BzBtw++0gj0ODlgwut8SrVj66/nvIB"
            + "jN3kLVahR8nZrEFF6vTTmyXi761pzq9yOVqI57wJGx8o3Ygox1p+pWUPl1hQR7rrhUbgK/Q5wno9"
            + "uJk07h3IZnNxE+/IKgeMTP/H4+jmyT4mhsexJ2BFHeiKF1KT/FMcJdSi+ZK5yoNVcYuY8aZbx0Ef"
            + "lHorCXAmLFB0W6Cz4KPP01nD9YBB4olxiK1t7m0AU9zscdivNiuUaB5OIEr+JuZ6dNw=";

    private static final String CIPHER_SUITE = "SSL_RSA_WITH_RC4_128_MD5";
    /**
     * Defines the keystore contents for the server, JKS version. Holds just a
     * single self-generated key. The subject name is "Test Server".
     */
    private static final String SERVER_KEYS_JKS =
            "/u3+7QAAAAIAAAABAAAAAQAFbXlrZXkAAAEaWFfBeAAAArowggK2MA4GCisGAQQBKgIRAQEFAASC"
            + "AqI2kp5XjnF8YZkhcF92YsJNQkvsmH7zqMM87j23zSoV4DwyE3XeC/gZWq1ToScIhoqZkzlbWcu4"
            + "T/Zfc/DrfGk/rKbBL1uWKGZ8fMtlZk8KoAhxZk1JSyJvdkyKxqmzUbxk1OFMlN2VJNu97FPVH+du"
            + "dvjTvmpdoM81INWBW/1fZJeQeDvn4mMbbe0IxgpiLnI9WSevlaDP/sm1X3iO9yEyzHLL+M5Erspo"
            + "Cwa558fOu5DdsICMXhvDQxjWFKFhPHnKtGe+VvwkG9/bAaDgx3kfhk0w5zvdnkKb+8Ed9ylNRzdk"
            + "ocAa/mxlMTOsTvDKXjjsBupNPIIj7OP4GNnZaxkJjSs98pEO67op1GX2qhy6FSOPNuq8k/65HzUc"
            + "PYn6voEeh6vm02U/sjEnzRevQ2+2wXoAdp0EwtQ/DlMe+NvcwPGWKuMgX4A4L93DZGb04N2VmAU3"
            + "YLOtZwTO0LbuWrcCM/q99G/7LcczkxIVrO2I/rh8RXVczlf9QzcrFObFv4ATuspWJ8xG7DhsMbnk"
            + "rT94Pq6TogYeoz8o8ZMykesAqN6mt/9+ToIemmXv+e+KU1hI5oLwWMnUG6dXM6hIvrULY6o+QCPH"
            + "172YQJMa+68HAeS+itBTAF4Clm/bLn6reHCGGU6vNdwU0lYldpiOj9cB3t+u2UuLo6tiFWjLf5Zs"
            + "EQJETd4g/EK9nHxJn0GAKrWnTw7pEHQJ08elzUuy04C/jEEG+4QXU1InzS4o/kR0Sqz2WTGDoSoq"
            + "ewuPRU5bzQs/b9daq3mXrnPtRBL6HfSDAdpTK76iHqLCGdqx3avHjVSBm4zFvEuYBCev+3iKOBmg"
            + "yh7eQRTjz4UOWfy85omMBr7lK8PtfVBDzOXpasxS0uBgdUyBDX4tO6k9jZ8a1kmQRQAAAAEABVgu"
            + "NTA5AAACSDCCAkQwggGtAgRIR8SKMA0GCSqGSIb3DQEBBAUAMGkxCzAJBgNVBAYTAlVTMRMwEQYD"
            + "VQQIEwpDYWxpZm9ybmlhMQwwCgYDVQQHEwNNVFYxDzANBgNVBAoTBkdvb2dsZTEQMA4GA1UECxMH"
            + "QW5kcm9pZDEUMBIGA1UEAxMLVGVzdCBTZXJ2ZXIwHhcNMDgwNjA1MTA0ODQyWhcNMDgwOTAzMTA0"
            + "ODQyWjBpMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEMMAoGA1UEBxMDTVRWMQ8w"
            + "DQYDVQQKEwZHb29nbGUxEDAOBgNVBAsTB0FuZHJvaWQxFDASBgNVBAMTC1Rlc3QgU2VydmVyMIGf"
            + "MA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwoC6chqCI84rj1PrXuJgbiit4EV909zR6N0jNlYfg"
            + "itwB39bP39wH03rFm8T59b3mbSptnGmCIpLZn25KPPFsYD3JJ+wFlmiUdEP9H05flfwtFQJnw9uT"
            + "3rRIdYVMPcQ3RoZzwAMliGr882I2thIDbA6xjGU/1nRIdvk0LtxH3QIDAQABMA0GCSqGSIb3DQEB"
            + "BAUAA4GBAJn+6YgUlY18Ie+0+Vt8oEi81DNi/bfPrAUAh63fhhBikx/3R9dl3wh09Z6p7cIdNxjW"
            + "n2ll+cRW9eqF7z75F0Omm0C7/KAEPjukVbszmzeU5VqzkpSt0j84YWi+TfcHRrfvhLbrlmGITVpY"
            + "ol5pHLDyqGmDs53pgwipWqsn/nEXEBgj3EoqPeqHbDf7YaP8h/5BSt0=";

    /**
     * Defines the keystore contents for the client, JKS version. Holds just a
     * single self-generated key. The subject name is "Test Client".
     */
    private static final String CLIENT_KEYS_JKS =
            "/u3+7QAAAAIAAAABAAAAAQAFbXlrZXkAAAEaWFhyMAAAArkwggK1MA4GCisGAQQBKgIRAQEFAASC"
            + "AqGVSfXolBStZy4nnRNn4fAr+S7kfU2BS23wwW8uB2Ru3GvtLzlK9q08Gvq/LNqBafjyFTVL5FV5"
            + "SED/8YomO5a98GpskSeRvytCiTBLJdgGhws5TOGekgIAcBROPGIyOtJPQ0HfOQs+BqgzGDHzHQhw"
            + "u/8Tm6yQwiP+W/1I9B1QnaEztZA3mhTyMMJsmsFTYroGgAog885D5Cmzd8sYGfxec3R6I+xcmBAY"
            + "eibR5kGpWwt1R+qMvRrtBqh5r6WSKhCBNax+SJVbtUNRiKyjKccdJg6fGqIWWeivwYTy0OhjA6b4"
            + "NiZ/ZZs5pxFGWUj/Rlp0RYy8fCF6aw5/5s4Bf4MI6dPSqMG8Hf7sJR91GbcELyzPdM0h5lNavgit"
            + "QPEzKeuDrGxhY1frJThBsNsS0gxeu+OgfJPEb/H4lpYX5IvuIGbWKcxoO9zq4/fimIZkdA8A+3eY"
            + "mfDaowvy65NBVQPJSxaOyFhLHfeLqOeCsVENAea02vA7andZHTZehvcrqyKtm+z8ncHGRC2H9H8O"
            + "jKwKHfxxrYY/jMAKLl00+PBb3kspO+BHI2EcQnQuMw/zr83OR9Meq4TJ0TMuNkApZELAeFckIBbS"
            + "rBr8NNjAIfjuCTuKHhsTFWiHfk9ZIzigxXagfeDRiyVc6khOuF/bGorj23N2o7Rf3uLoU6PyXWi4"
            + "uhctR1aL6NzxDoK2PbYCeA9hxbDv8emaVPIzlVwpPK3Ruvv9mkjcOhZ74J8bPK2fQmbplbOljcZi"
            + "tZijOfzcO/11JrwhuJZRA6wanTqHoujgChV9EukVrmbWGGAcewFnAsSbFXIik7/+QznXaDIt5NgL"
            + "H/Bcz4Z/fdV7Ae1eUaxKXdPbI//4J+8liVT/d8awjW2tldIaDlmGMR3aoc830+3mAAAAAQAFWC41"
            + "MDkAAAJIMIICRDCCAa0CBEhHxLgwDQYJKoZIhvcNAQEEBQAwaTELMAkGA1UEBhMCVVMxEzARBgNV"
            + "BAgTCkNhbGlmb3JuaWExDDAKBgNVBAcTA01UVjEPMA0GA1UEChMGR29vZ2xlMRAwDgYDVQQLEwdB"
            + "bmRyb2lkMRQwEgYDVQQDEwtUZXN0IENsaWVudDAeFw0wODA2MDUxMDQ5MjhaFw0wODA5MDMxMDQ5"
            + "MjhaMGkxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMQwwCgYDVQQHEwNNVFYxDzAN"
            + "BgNVBAoTBkdvb2dsZTEQMA4GA1UECxMHQW5kcm9pZDEUMBIGA1UEAxMLVGVzdCBDbGllbnQwgZ8w"
            + "DQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAIK3Q+KiFbmCGg422TAo4gggdhMH6FJhiuz8DxRyeMKR"
            + "UAfP4MK0wtc8N42waZ6OKvxpBFUy0BRfBsX0GD4Ku99yu9/tavSigTraeJtwV3WWRRjIqk7L3wX5"
            + "cmgS2KSD43Y0rNUKrko26lnt9N4qiYRBSj+tcAN3Lx9+ptqk1LApAgMBAAEwDQYJKoZIhvcNAQEE"
            + "BQADgYEANb7Q1GVSuy1RPJ0FmiXoMYCCtvlRLkmJphwxovK0cAQK12Vll+yAzBhHiQHy/RA11mng"
            + "wYudC7u3P8X/tBT8GR1Yk7QW3KgFyPafp3lQBBCraSsfrjKj+dCLig1uBLUr4f68W8VFWZWWTHqp"
            + "NMGpCX6qmjbkJQLVK/Yfo1ePaUexPSOX0G9m8+DoV3iyNw6at01NRw==";


    int port;
    SSLSocket serverSocket;
    MyHandshakeListener listener;
    String host = "localhost";
    boolean notFinished = true;
    SSLSession clientSession = null;
    SSLContext clientSslContext = null;
    String testData = "PING";

    private String PASSWORD = "android";

    /**
     * Implements a test SSL socket server. It waits for a connection on a given
     * port, requests client authentication (if specified), reads from the socket,
     * and writes to the socket.
     */
    class TestServer implements Runnable {

        public static final int CLIENT_AUTH_NONE = 0;

        public static final int CLIENT_AUTH_WANTED = 1;

        public static final int CLIENT_AUTH_NEEDED = 2;

        private TestTrustManager trustManager;

        private Exception exception;

        String keys;

        private int clientAuth;

        private boolean provideKeys;

        private KeyStore store;

        public TestServer(boolean provideKeys, int clientAuth, String keys) {
            this.keys = keys;
            this.clientAuth = clientAuth;
            this.provideKeys = provideKeys;

            trustManager = new TestTrustManager();
        }

        public void run() {
            try {
                store = provideKeys ? getKeyStore(keys) : null;
                KeyManager[] keyManagers = store != null ? getKeyManagers(store) : null;
                TrustManager[] trustManagers = new TrustManager[] { trustManager };

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(keyManagers, trustManagers, null);

                SSLServerSocket serverSocket = (SSLServerSocket)sslContext
                        .getServerSocketFactory().createServerSocket();

                if (clientAuth == CLIENT_AUTH_WANTED) {
                    serverSocket.setWantClientAuth(true);
                } else if (clientAuth == CLIENT_AUTH_NEEDED) {
                    serverSocket.setNeedClientAuth(true);
                } else {
                    serverSocket.setWantClientAuth(false);
                }

                serverSocket.bind(new InetSocketAddress(port));

                SSLSocket clientSocket = (SSLSocket)serverSocket.accept();

                InputStream istream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                istream.read(buffer);

                OutputStream ostream = clientSocket.getOutputStream();
                ostream.write(testData.getBytes());
                ostream.flush();

                while (notFinished) {
                    Thread.currentThread().sleep(500);
                }

                clientSocket.close();
                serverSocket.close();

            } catch (Exception ex) {
                exception = ex;
            }
        }

        public Exception getException() {
            return exception;
        }

        public javax.security.cert.X509Certificate[] getChain() {
            return trustManager.getChain();
        }

        public KeyStore getStore() {
            return store;
        }

    }

    /**
     * Implements a test SSL socket client. It opens a connection to localhost on
     * a given port, writes to the socket, and reads from the socket.
     */
    class TestClient implements Runnable {

        private TestTrustManager trustManager;

        private Exception exception;

        private String keys;

        private boolean provideKeys;

        private KeyStore store;

        public TestClient(boolean provideKeys, String keys) {
            this.keys = keys;
            this.provideKeys = provideKeys;

            trustManager = new TestTrustManager();
        }

        public void run() {
            try {
                store = provideKeys ? getKeyStore(keys) : null;
                KeyManager[] keyManagers = store != null ? getKeyManagers(store) : null;
                TrustManager[] trustManagers = new TrustManager[] { trustManager };

                clientSslContext = SSLContext.getInstance("TLS");
                clientSslContext.init(keyManagers, trustManagers, null);

                SSLSocket socket = (SSLSocket)clientSslContext.getSocketFactory().createSocket();

                socket.connect(new InetSocketAddress(port));
                OutputStream ostream = socket.getOutputStream();
                ostream.write(testData.getBytes());
                ostream.flush();

                InputStream istream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                istream.read(buffer);

                clientSession = socket.getSession();
                while (notFinished) {
                    Thread.currentThread().sleep(500);
                }
                socket.close();

            } catch (Exception ex) {
                exception = ex;
            }
        }

        public Exception getException() {
            return exception;
        }

        public javax.security.cert.X509Certificate[] getChain() {
            return trustManager.getChain();
        }

        public KeyStore getStore() {
            return store;
        }
    }

    /**
     * Loads a keystore from a base64-encoded String. Returns the KeyManager[]
     * for the result.
     */
    private KeyStore getKeyStore(String keys) throws Exception {
        byte[] bytes = Base64.decode(keys.getBytes());
        InputStream inputStream = new ByteArrayInputStream(bytes);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(inputStream, PASSWORD.toCharArray());
        inputStream.close();
        return keyStore;
    }

    /**
     * Loads a keystore from a base64-encoded String. Returns the KeyManager[]
     * for the result.
     */
    private KeyManager[] getKeyManagers(KeyStore keyStore) throws Exception {
        String algorithm = KeyManagerFactory.getDefaultAlgorithm();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(algorithm);
        keyManagerFactory.init(keyStore, PASSWORD.toCharArray());

        return keyManagerFactory.getKeyManagers();
    }
}
