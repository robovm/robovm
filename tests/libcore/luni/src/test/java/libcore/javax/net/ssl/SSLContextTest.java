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

import java.security.KeyManagementException;
import java.security.Provider;
import libcore.java.security.StandardNames;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import junit.framework.TestCase;

public class SSLContextTest extends TestCase {

    public void test_SSLContext_getDefault() throws Exception {
        SSLContext sslContext = SSLContext.getDefault();
        assertNotNull(sslContext);
        try {
            sslContext.init(null, null, null);
        } catch (KeyManagementException expected) {
        }
    }

    public void test_SSLContext_setDefault() throws Exception {
        try {
            SSLContext.setDefault(null);
        } catch (NullPointerException expected) {
        }

        SSLContext defaultContext = SSLContext.getDefault();
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext oldContext = SSLContext.getDefault();
            assertNotNull(oldContext);
            SSLContext newContext = SSLContext.getInstance(protocol);
            assertNotNull(newContext);
            assertNotSame(oldContext, newContext);
            SSLContext.setDefault(newContext);
            assertSame(newContext, SSLContext.getDefault());
        }
        SSLContext.setDefault(defaultContext);
    }

    public void test_SSLContext_getInstance() throws Exception {
        try {
            SSLContext.getInstance(null);
            fail();
        } catch (NullPointerException expected) {
        }
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            assertNotNull(SSLContext.getInstance(protocol));
            assertNotSame(SSLContext.getInstance(protocol),
                          SSLContext.getInstance(protocol));
        }

        try {
            SSLContext.getInstance(null, (String) null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            SSLContext.getInstance(null, "");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            try {
                SSLContext.getInstance(protocol, (String) null);
                fail();
            } catch (IllegalArgumentException expected) {
            }
        }
        try {
            SSLContext.getInstance(null, StandardNames.JSSE_PROVIDER_NAME);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    public void test_SSLContext_getProtocol() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            String protocolName = SSLContext.getInstance(protocol).getProtocol();
            assertNotNull(protocolName);
            assertTrue(protocol.startsWith(protocolName));
        }
    }

    public void test_SSLContext_getProvider() throws Exception {
        Provider provider = SSLContext.getDefault().getProvider();
        assertNotNull(provider);
        assertEquals(StandardNames.JSSE_PROVIDER_NAME, provider.getName());
    }

    public void test_SSLContext_init() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            if (protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                try {
                    sslContext.init(null, null, null);
                } catch (KeyManagementException expected) {
                }
            } else {
                sslContext.init(null, null, null);
            }
        }
    }

    public void test_SSLContext_getSocketFactory() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            if (protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                SSLContext.getInstance(protocol).getSocketFactory();
            } else {
                try {
                    SSLContext.getInstance(protocol).getSocketFactory();
                    fail();
                } catch (IllegalStateException expected) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance(protocol);
            if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                sslContext.init(null, null, null);
            }
            SocketFactory sf = sslContext.getSocketFactory();
            assertNotNull(sf);
            assertTrue(SSLSocketFactory.class.isAssignableFrom(sf.getClass()));
        }
    }

    public void test_SSLContext_getServerSocketFactory() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            if (protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                SSLContext.getInstance(protocol).getServerSocketFactory();
            } else {
                try {
                    SSLContext.getInstance(protocol).getServerSocketFactory();
                    fail();
                } catch (IllegalStateException expected) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance(protocol);
            if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                sslContext.init(null, null, null);
            }
            ServerSocketFactory ssf = sslContext.getServerSocketFactory();
            assertNotNull(ssf);
            assertTrue(SSLServerSocketFactory.class.isAssignableFrom(ssf.getClass()));
        }
    }

    public void test_SSLContext_createSSLEngine() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {

            if (protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                SSLContext.getInstance(protocol).createSSLEngine();
            } else {
                try {
                    SSLContext.getInstance(protocol).createSSLEngine();
                    fail();
                } catch (IllegalStateException expected) {
                }
            }

            if (protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                SSLContext.getInstance(protocol).createSSLEngine(null, -1);
            } else {
                try {
                    SSLContext.getInstance(protocol).createSSLEngine(null, -1);
                    fail();
                } catch (IllegalStateException expected) {
                }
            }

            {
                SSLContext sslContext = SSLContext.getInstance(protocol);
                if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                    sslContext.init(null, null, null);
                }
                SSLEngine se = sslContext.createSSLEngine();
                assertNotNull(se);
            }

            {
                SSLContext sslContext = SSLContext.getInstance(protocol);
                if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                    sslContext.init(null, null, null);
                }
                SSLEngine se = sslContext.createSSLEngine(null, -1);
                assertNotNull(se);
            }
        }
    }

    public void test_SSLContext_getServerSessionContext() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            SSLSessionContext sessionContext = sslContext.getServerSessionContext();
            assertNotNull(sessionContext);

            if (!StandardNames.IS_RI &&
                    protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                assertSame(SSLContext.getInstance(protocol).getServerSessionContext(),
                           sessionContext);
            } else {
                assertNotSame(SSLContext.getInstance(protocol).getServerSessionContext(),
                              sessionContext);
            }
        }
    }

    public void test_SSLContext_getClientSessionContext() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            SSLSessionContext sessionContext = sslContext.getClientSessionContext();
            assertNotNull(sessionContext);

            if (!StandardNames.IS_RI &&
                    protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                assertSame(SSLContext.getInstance(protocol).getClientSessionContext(),
                           sessionContext);
            } else {
                assertNotSame(SSLContext.getInstance(protocol).getClientSessionContext(),
                              sessionContext);
            }
        }
    }

    public void test_SSLContext_getDefaultSSLParameters() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                sslContext.init(null, null, null);
            }

            SSLParameters p = sslContext.getDefaultSSLParameters();
            assertNotNull(p);

            String[] cipherSuites = p.getCipherSuites();
            assertNotNull(cipherSuites);
            StandardNames.assertValidCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);

            String[] protocols = p.getProtocols();
            assertNotNull(protocols);
            StandardNames.assertValidCipherSuites(StandardNames.SSL_SOCKET_PROTOCOLS, protocols);

            assertFalse(p.getWantClientAuth());
            assertFalse(p.getNeedClientAuth());
        }
    }

    public void test_SSLContext_getSupportedSSLParameters() throws Exception {
        for (String protocol : StandardNames.SSL_CONTEXT_PROTOCOLS) {
            SSLContext sslContext = SSLContext.getInstance(protocol);
            if (!protocol.equals(StandardNames.SSL_CONTEXT_PROTOCOLS_DEFAULT)) {
                sslContext.init(null, null, null);
            }

            SSLParameters p = sslContext.getSupportedSSLParameters();
            assertNotNull(p);

            String[] cipherSuites = p.getCipherSuites();
            assertNotNull(cipherSuites);
            StandardNames.assertSupportedCipherSuites(StandardNames.CIPHER_SUITES, cipherSuites);

            String[] protocols = p.getProtocols();
            assertNotNull(protocols);
            StandardNames.assertSupportedProtocols(StandardNames.SSL_SOCKET_PROTOCOLS,
                                                   protocols);

            assertFalse(p.getWantClientAuth());
            assertFalse(p.getNeedClientAuth());
        }
    }

    public void test_SSLContextTest_TestSSLContext_create() {
        TestSSLContext testContext = TestSSLContext.create();
        assertNotNull(testContext);
        assertNotNull(testContext.clientKeyStore);
        assertNull(testContext.clientStorePassword);
        assertNotNull(testContext.serverKeyStore);
        assertEquals(StandardNames.IS_RI, testContext.serverStorePassword != null);
        assertNotNull(testContext.clientKeyManager);
        assertNotNull(testContext.serverKeyManager);
        assertNotNull(testContext.clientTrustManager);
        assertNotNull(testContext.serverTrustManager);
        assertNotNull(testContext.clientContext);
        assertNotNull(testContext.serverContext);
        assertNotNull(testContext.serverSocket);
        assertNotNull(testContext.host);
        assertTrue(testContext.port != 0);
        testContext.close();
    }
}
