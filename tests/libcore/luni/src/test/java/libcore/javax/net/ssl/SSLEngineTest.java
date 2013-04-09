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

import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;
import libcore.java.security.TestKeyStore;

public class SSLEngineTest extends TestCase {

    public void assertConnected(TestSSLEnginePair e) {
        assertConnected(e.client, e.server);
    }

    public void assertNotConnected(TestSSLEnginePair e) {
        assertNotConnected(e.client, e.server);
    }

    public void assertConnected(SSLEngine a, SSLEngine b) {
        assertTrue(connected(a, b));
    }

    public void assertNotConnected(SSLEngine a, SSLEngine b) {
        assertFalse(connected(a, b));
    }

    public boolean connected(SSLEngine a, SSLEngine b) {
        return (a.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING
                && b.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING
                && a.getSession() != null
                && b.getSession() != null
                && !a.isInboundDone()
                && !b.isInboundDone()
                && !a.isOutboundDone()
                && !b.isOutboundDone());
    }

    public void test_SSLEngine_getSupportedCipherSuites_names() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        String[] cipherSuites = e.getSupportedCipherSuites();
        StandardNames.assertSupportedCipherSuites(StandardNames.CIPHER_SUITES_SSLENGINE,
                                                  cipherSuites);
        assertNotSame(cipherSuites, e.getSupportedCipherSuites());
        c.close();
    }

    public void test_SSLEngine_getSupportedCipherSuites_connect() throws Exception {
        // note the rare usage of non-RSA keys
        TestKeyStore testKeyStore = new TestKeyStore.Builder()
                .keyAlgorithms("RSA", "DSA", "EC", "EC_RSA")
                .aliasPrefix("rsa-dsa-ec")
                .ca(true)
                .build();
        test_SSLEngine_getSupportedCipherSuites_connect(testKeyStore, false);
        if (StandardNames.IS_RI) {
            test_SSLEngine_getSupportedCipherSuites_connect(testKeyStore, true);
        }
    }
    private void test_SSLEngine_getSupportedCipherSuites_connect(TestKeyStore testKeyStore,
                                                                 boolean secureRenegotiation)
            throws Exception {
        TestSSLContext c = TestSSLContext.create(testKeyStore, testKeyStore);
        String[] cipherSuites = c.clientContext.createSSLEngine().getSupportedCipherSuites();
        for (String cipherSuite : cipherSuites) {
            boolean errorExpected = StandardNames.IS_RI && cipherSuite.endsWith("_SHA256");
            try {
                /*
                 * TLS_EMPTY_RENEGOTIATION_INFO_SCSV cannot be used on
                 * its own, but instead in conjunction with other
                 * cipher suites.
                 */
                if (cipherSuite.equals(StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION)) {
                    continue;
                }
                /*
                 * Kerberos cipher suites require external setup. See "Kerberos Requirements" in
                 * https://java.sun.com/j2se/1.5.0/docs/guide/security/jsse/JSSERefGuide.html
                 * #KRBRequire
                 */
                if (cipherSuite.startsWith("TLS_KRB5_")) {
                    continue;
                }

                final String[] cipherSuiteArray
                        = (secureRenegotiation
                           ? new String[] { cipherSuite,
                                            StandardNames.CIPHER_SUITE_SECURE_RENEGOTIATION }
                           : new String[] { cipherSuite });
                assertConnected(TestSSLEnginePair.create(c, new TestSSLEnginePair.Hooks() {
                        @Override
                                void beforeBeginHandshake(SSLEngine client, SSLEngine server) {
                            client.setEnabledCipherSuites(cipherSuiteArray);
                            server.setEnabledCipherSuites(cipherSuiteArray);
                        }
                    }));
                assertFalse(errorExpected);
            } catch (Exception maybeExpected) {
                if (!errorExpected) {
                    throw new Exception("Problem trying to connect cipher suite " + cipherSuite,
                                        maybeExpected);
                }
            }
        }
        c.close();
    }

    public void test_SSLEngine_getEnabledCipherSuites() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        String[] cipherSuites = e.getEnabledCipherSuites();
        StandardNames.assertValidCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);
        assertNotSame(cipherSuites, e.getEnabledCipherSuites());
        c.close();
    }

    public void test_SSLEngine_setEnabledCipherSuites() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();

        try {
            e.setEnabledCipherSuites(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            e.setEnabledCipherSuites(new String[1]);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            e.setEnabledCipherSuites(new String[] { "Bogus" } );
            fail();
        } catch (IllegalArgumentException expected) {
        }

        e.setEnabledCipherSuites(new String[0]);
        e.setEnabledCipherSuites(e.getEnabledCipherSuites());
        e.setEnabledCipherSuites(e.getSupportedCipherSuites());
        c.close();
    }

    public void test_SSLEngine_getSupportedProtocols() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        String[] protocols = e.getSupportedProtocols();
        StandardNames.assertSupportedProtocols(StandardNames.SSL_SOCKET_PROTOCOLS_SSLENGINE,
                                               protocols);
        assertNotSame(protocols, e.getSupportedProtocols());
        c.close();
    }

    public void test_SSLEngine_getEnabledProtocols() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        String[] protocols = e.getEnabledProtocols();
        StandardNames.assertValidProtocols(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);
        assertNotSame(protocols, e.getEnabledProtocols());
        c.close();
    }

    public void test_SSLEngine_setEnabledProtocols() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();

        try {
            e.setEnabledProtocols(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            e.setEnabledProtocols(new String[1]);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            e.setEnabledProtocols(new String[] { "Bogus" } );
            fail();
        } catch (IllegalArgumentException expected) {
        }
        e.setEnabledProtocols(new String[0]);
        e.setEnabledProtocols(e.getEnabledProtocols());
        e.setEnabledProtocols(e.getSupportedProtocols());
        c.close();
    }

    public void test_SSLEngine_getSession() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        SSLSession session = e.getSession();
        assertNotNull(session);
        assertFalse(session.isValid());
        c.close();
    }

    public void test_SSLEngine_beginHandshake() throws Exception {
        TestSSLContext c = TestSSLContext.create();

        try {
            c.clientContext.createSSLEngine().beginHandshake();
            fail();
        } catch (IllegalStateException expected) {
        }

        assertConnected(TestSSLEnginePair.create(null));

        c.close();
    }

    public void test_SSLEngine_beginHandshake_noKeyStore() throws Exception {
        TestSSLContext c = TestSSLContext.create(null, null, null, null, null, null, null, null,
                                                 SSLContext.getDefault(), SSLContext.getDefault());
        try {
            // TODO Fix KnownFailure AlertException "NO SERVER CERTIFICATE FOUND"
            // ServerHandshakeImpl.selectSuite should not select a suite without a required cert
            TestSSLEnginePair.connect(c, null);
            fail();
        } catch (SSLHandshakeException expected) {
        }
        c.close();
    }

    public void test_SSLEngine_beginHandshake_noClientCertificate() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine[] engines = TestSSLEnginePair.connect(c, null);
        assertConnected(engines[0], engines[1]);
        c.close();
    }

    public void test_SSLEngine_getUseClientMode() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        assertFalse(c.clientContext.createSSLEngine().getUseClientMode());
        assertFalse(c.clientContext.createSSLEngine(null, -1).getUseClientMode());
        c.close();
    }

    public void test_SSLEngine_setUseClientMode() throws Exception {
        // client is client, server is server
        assertConnected(test_SSLEngine_setUseClientMode(true, false));

        // client is server, server is client
        assertConnected(test_SSLEngine_setUseClientMode(false, true));

        // both are client
        assertNotConnected(test_SSLEngine_setUseClientMode(true, true));

        // both are server
        assertNotConnected(test_SSLEngine_setUseClientMode(false, false));
    }

    public void test_SSLEngine_setUseClientMode_afterHandshake() throws Exception {

        // can't set after handshake
        TestSSLEnginePair pair = TestSSLEnginePair.create(null);
        try {
            pair.server.setUseClientMode(false);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            pair.client.setUseClientMode(false);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    private TestSSLEnginePair test_SSLEngine_setUseClientMode(final boolean clientClientMode,
                                                              final boolean serverClientMode)
            throws Exception {
        TestSSLContext c;
        if (!clientClientMode && serverClientMode) {
            c = TestSSLContext.create(TestKeyStore.getServer(), TestKeyStore.getClient());
        } else {
            c = TestSSLContext.create();
        }

        return TestSSLEnginePair.create(c, new TestSSLEnginePair.Hooks() {
            @Override
            void beforeBeginHandshake(SSLEngine client, SSLEngine server) {
                client.setUseClientMode(clientClientMode);
                server.setUseClientMode(serverClientMode);
            }
        });
    }

    public void test_SSLEngine_clientAuth() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();

        assertFalse(e.getWantClientAuth());
        assertFalse(e.getNeedClientAuth());

        // confirm turning one on by itself
        e.setWantClientAuth(true);
        assertTrue(e.getWantClientAuth());
        assertFalse(e.getNeedClientAuth());

        // confirm turning setting on toggles the other
        e.setNeedClientAuth(true);
        assertFalse(e.getWantClientAuth());
        assertTrue(e.getNeedClientAuth());

        // confirm toggling back
        e.setWantClientAuth(true);
        assertTrue(e.getWantClientAuth());
        assertFalse(e.getNeedClientAuth());

        // TODO Fix KnownFailure "init - invalid private key"
        TestSSLContext clientAuthContext
                = TestSSLContext.create(TestKeyStore.getClientCertificate(),
                                        TestKeyStore.getServer());
        TestSSLEnginePair p = TestSSLEnginePair.create(clientAuthContext,
                                                       new TestSSLEnginePair.Hooks() {
            @Override
                    void beforeBeginHandshake(SSLEngine client, SSLEngine server) {
                server.setWantClientAuth(true);
            }
        });
        assertConnected(p);
        assertNotNull(p.client.getSession().getLocalCertificates());
        TestKeyStore.assertChainLength(p.client.getSession().getLocalCertificates());
        TestSSLContext.assertClientCertificateChain(clientAuthContext.clientTrustManager,
                                                    p.client.getSession().getLocalCertificates());
        clientAuthContext.close();
        c.close();
    }

    public void test_SSLEngine_getEnableSessionCreation() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        assertTrue(e.getEnableSessionCreation());
        c.close();
    }

    public void test_SSLEngine_setEnableSessionCreation_server() throws Exception {
        TestSSLEnginePair p = TestSSLEnginePair.create(new TestSSLEnginePair.Hooks() {
            @Override
            void beforeBeginHandshake(SSLEngine client, SSLEngine server) {
                server.setEnableSessionCreation(false);
            }
        });
        assertNotConnected(p);
    }

    public void test_SSLEngine_setEnableSessionCreation_client() throws Exception {
        try {
            TestSSLEnginePair.create(new TestSSLEnginePair.Hooks() {
                @Override
                void beforeBeginHandshake(SSLEngine client, SSLEngine server) {
                    client.setEnableSessionCreation(false);
                }
            });
            fail();
        } catch (SSLException expected) {
        }
    }

    public void test_SSLEngine_getSSLParameters() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();

        SSLParameters p = e.getSSLParameters();
        assertNotNull(p);

        String[] cipherSuites = p.getCipherSuites();
        StandardNames.assertValidCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);
        assertNotSame(cipherSuites, e.getEnabledCipherSuites());
        assertEquals(Arrays.asList(cipherSuites), Arrays.asList(e.getEnabledCipherSuites()));

        String[] protocols = p.getProtocols();
        StandardNames.assertValidProtocols(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);
        assertNotSame(protocols, e.getEnabledProtocols());
        assertEquals(Arrays.asList(protocols), Arrays.asList(e.getEnabledProtocols()));

        assertEquals(p.getWantClientAuth(), e.getWantClientAuth());
        assertEquals(p.getNeedClientAuth(), e.getNeedClientAuth());

        c.close();
    }

    public void test_SSLEngine_setSSLParameters() throws Exception {
        TestSSLContext c = TestSSLContext.create();
        SSLEngine e = c.clientContext.createSSLEngine();
        String[] defaultCipherSuites = e.getEnabledCipherSuites();
        String[] defaultProtocols = e.getEnabledProtocols();
        String[] supportedCipherSuites = e.getSupportedCipherSuites();
        String[] supportedProtocols = e.getSupportedProtocols();

        {
            SSLParameters p = new SSLParameters();
            e.setSSLParameters(p);
            assertEquals(Arrays.asList(defaultCipherSuites),
                         Arrays.asList(e.getEnabledCipherSuites()));
            assertEquals(Arrays.asList(defaultProtocols),
                         Arrays.asList(e.getEnabledProtocols()));
        }

        {
            SSLParameters p = new SSLParameters(supportedCipherSuites,
                                                supportedProtocols);
            e.setSSLParameters(p);
            assertEquals(Arrays.asList(supportedCipherSuites),
                         Arrays.asList(e.getEnabledCipherSuites()));
            assertEquals(Arrays.asList(supportedProtocols),
                         Arrays.asList(e.getEnabledProtocols()));
        }
        {
            SSLParameters p = new SSLParameters();

            p.setNeedClientAuth(true);
            assertFalse(e.getNeedClientAuth());
            assertFalse(e.getWantClientAuth());
            e.setSSLParameters(p);
            assertTrue(e.getNeedClientAuth());
            assertFalse(e.getWantClientAuth());

            p.setWantClientAuth(true);
            assertTrue(e.getNeedClientAuth());
            assertFalse(e.getWantClientAuth());
            e.setSSLParameters(p);
            assertFalse(e.getNeedClientAuth());
            assertTrue(e.getWantClientAuth());

            p.setWantClientAuth(false);
            assertFalse(e.getNeedClientAuth());
            assertTrue(e.getWantClientAuth());
            e.setSSLParameters(p);
            assertFalse(e.getNeedClientAuth());
            assertFalse(e.getWantClientAuth());
        }
        c.close();
    }

    public void test_TestSSLEnginePair_create() throws Exception {
        TestSSLEnginePair test = TestSSLEnginePair.create(null);
        assertNotNull(test.c);
        assertNotNull(test.server);
        assertNotNull(test.client);
        assertConnected(test);
    }
}
