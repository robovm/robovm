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

package org.apache.harmony.xnet.provider.jsse;

import org.apache.harmony.xnet.provider.jsse.TrustManagerImpl;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/**
 * The instances of this class encapsulate all the info
 * about enabled cipher suites and protocols,
 * as well as the information about client/server mode of
 * ssl socket, whether it require/want client authentication or not,
 * and controls whether new SSL sessions may be established by this
 * socket or not.
 */
public class SSLParametersImpl implements Cloneable {

    // default source of authentication keys
    private static volatile X509KeyManager defaultKeyManager;
    // default source of authentication trust decisions
    private static volatile X509TrustManager defaultTrustManager;
    // default source of random numbers
    private static volatile SecureRandom defaultSecureRandom;
    // default SSL parameters
    private static volatile SSLParametersImpl defaultParameters;

    // client session context contains the set of reusable
    // client-side SSL sessions
    private final ClientSessionContext clientSessionContext;
    // server session context contains the set of reusable
    // server-side SSL sessions
    private final ServerSessionContext serverSessionContext;
    // source of authentication keys
    private X509KeyManager keyManager;
    // source of authentication trust decisions
    private X509TrustManager trustManager;
    // source of random numbers
    private SecureRandom secureRandom;

    // cipher suites available for SSL connection
    private CipherSuite[] enabledCipherSuites;
    // string representations of available cipher suites
    private String[] enabledCipherSuiteNames = null;

    // protocols available for SSL connection
    private String[] enabledProtocols = ProtocolVersion.supportedProtocols;

    // if the peer with this parameters tuned to work in client mode
    private boolean client_mode = true;
    // if the peer with this parameters tuned to require client authentication
    private boolean need_client_auth = false;
    // if the peer with this parameters tuned to request client authentication
    private boolean want_client_auth = false;
    // if the peer with this parameters allowed to cteate new SSL session
    private boolean enable_session_creation = true;

    protected CipherSuite[] getEnabledCipherSuitesMember() {
        if (enabledCipherSuites == null) {
            this.enabledCipherSuites = CipherSuite.DEFAULT_CIPHER_SUITES;
        }
        return enabledCipherSuites;
    }

    /**
     * Initializes the parameters. Naturally this constructor is used
     * in SSLContextImpl.engineInit method which directly passes its
     * parameters. In other words this constructor holds all
     * the functionality provided by SSLContext.init method.
     * See {@link javax.net.ssl.SSLContext#init(KeyManager[],TrustManager[],
     * SecureRandom)} for more information
     */
    protected SSLParametersImpl(KeyManager[] kms, TrustManager[] tms,
            SecureRandom sr, ClientSessionContext clientSessionContext,
            ServerSessionContext serverSessionContext)
            throws KeyManagementException {
        this.serverSessionContext = serverSessionContext;
        this.clientSessionContext = clientSessionContext;

        // It's not described by the spec of SSLContext what should happen
        // if the arrays of length 0 are specified. This implementation
        // behave as for null arrays (i.e. use installed security providers)

        // initialize keyManager
        if ((kms == null) || (kms.length == 0)) {
            keyManager = getDefaultKeyManager();
        } else {
            keyManager = findX509KeyManager(kms);
        }
        if (keyManager == null) {
            throw new KeyManagementException("No X509KeyManager found");
        }

        // initialize trustManager
        if ((tms == null) || (tms.length == 0)) {
            trustManager = getDefaultTrustManager();
        } else {
            trustManager = findX509TrustManager(tms);
        }
        if (trustManager == null) {
            throw new KeyManagementException("No X509TrustManager found");
        }
        // initialize secure random
        // BEGIN android-removed
        // if (sr == null) {
        //     if (defaultSecureRandom == null) {
        //         defaultSecureRandom = new SecureRandom();
        //     }
        //     secureRandom = defaultSecureRandom;
        // } else {
        //     secureRandom = sr;
        // }
        // END android-removed
        // BEGIN android-added
        // We simply use the SecureRandom passed in by the caller. If it's
        // null, we don't replace it by a new instance. The native code below
        // then directly accesses /dev/urandom. Not the most elegant solution,
        // but faster than going through the SecureRandom object.
        secureRandom = sr;
        // END android-added
    }

    protected static SSLParametersImpl getDefault() throws KeyManagementException {
        SSLParametersImpl result = defaultParameters;
        if (result == null) {
            // single-check idiom
            defaultParameters = result = new SSLParametersImpl(null,
                                                               null,
                                                               null,
                                                               new ClientSessionContext(),
                                                               new ServerSessionContext());
        }
        return (SSLParametersImpl) result.clone();
    }

    /**
     * @return server session context
     */
    protected ServerSessionContext getServerSessionContext() {
        return serverSessionContext;
    }

    /**
     * @return client session context
     */
    protected ClientSessionContext getClientSessionContext() {
        return clientSessionContext;
    }

    /**
     * @return key manager
     */
    protected X509KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * @return trust manager
     */
    protected X509TrustManager getTrustManager() {
        return trustManager;
    }

    /**
     * @return secure random
     */
    protected SecureRandom getSecureRandom() {
        if (secureRandom != null) {
            return secureRandom;
        }
        SecureRandom result = defaultSecureRandom;
        if (result == null) {
            // single-check idiom
            defaultSecureRandom = result = new SecureRandom();
        }
        secureRandom = result;
        return secureRandom;
    }

    /**
     * @return the secure random member reference, even it is null
     */
    protected SecureRandom getSecureRandomMember() {
        return secureRandom;
    }

    /**
     * @return the names of enabled cipher suites
     */
    protected String[] getEnabledCipherSuites() {
        if (enabledCipherSuiteNames == null) {
            CipherSuite[] enabledCipherSuites = getEnabledCipherSuitesMember();
            enabledCipherSuiteNames = new String[enabledCipherSuites.length];
            for (int i = 0; i< enabledCipherSuites.length; i++) {
                enabledCipherSuiteNames[i] = enabledCipherSuites[i].getName();
            }
        }
        return enabledCipherSuiteNames.clone();
    }

    /**
     * Sets the set of available cipher suites for use in SSL connection.
     * @param   suites: String[]
     * @return
     */
    protected void setEnabledCipherSuites(String[] suites) {
        if (suites == null) {
            throw new IllegalArgumentException("suites == null");
        }
        CipherSuite[] cipherSuites = new CipherSuite[suites.length];
        for (int i=0; i<suites.length; i++) {
            String suite = suites[i];
            if (suite == null) {
                throw new IllegalArgumentException("suites[" + i + "] == null");
            }
            cipherSuites[i] = CipherSuite.getByName(suite);
            if (cipherSuites[i] == null || !cipherSuites[i].supported) {
                throw new IllegalArgumentException(suite + " is not supported.");
            }
        }
        enabledCipherSuites = cipherSuites;
        enabledCipherSuiteNames = suites;
    }

    /**
     * @return the set of enabled protocols
     */
    protected String[] getEnabledProtocols() {
        return enabledProtocols.clone();
    }

    /**
     * Sets the set of available protocols for use in SSL connection.
     * @param protocols String[]
     */
    protected void setEnabledProtocols(String[] protocols) {
        if (protocols == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        for (int i=0; i<protocols.length; i++) {
            String protocol = protocols[i];
            if (protocol == null) {
                throw new IllegalArgumentException("protocols[" + i + "] == null");
            }
            if (!ProtocolVersion.isSupported(protocol)) {
                throw new IllegalArgumentException("Protocol " + protocol + " is not supported.");
            }
        }
        enabledProtocols = protocols;
    }

    /**
     * Tunes the peer holding this parameters to work in client mode.
     * @param   mode if the peer is configured to work in client mode
     */
    protected void setUseClientMode(boolean mode) {
        client_mode = mode;
    }

    /**
     * Returns the value indicating if the parameters configured to work
     * in client mode.
     */
    protected boolean getUseClientMode() {
        return client_mode;
    }

    /**
     * Tunes the peer holding this parameters to require client authentication
     */
    protected void setNeedClientAuth(boolean need) {
        need_client_auth = need;
        // reset the want_client_auth setting
        want_client_auth = false;
    }

    /**
     * Returns the value indicating if the peer with this parameters tuned
     * to require client authentication
     */
    protected boolean getNeedClientAuth() {
        return need_client_auth;
    }

    /**
     * Tunes the peer holding this parameters to request client authentication
     */
    protected void setWantClientAuth(boolean want) {
        want_client_auth = want;
        // reset the need_client_auth setting
        need_client_auth = false;
    }

    /**
     * Returns the value indicating if the peer with this parameters
     * tuned to request client authentication
     * @return
     */
    protected boolean getWantClientAuth() {
        return want_client_auth;
    }

    /**
     * Allows/disallows the peer holding this parameters to
     * create new SSL session
     */
    protected void setEnableSessionCreation(boolean flag) {
        enable_session_creation = flag;
    }

    /**
     * Returns the value indicating if the peer with this parameters
     * allowed to cteate new SSL session
     */
    protected boolean getEnableSessionCreation() {
        return enable_session_creation;
    }

    /**
     * Returns the clone of this object.
     * @return the clone.
     */
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    private static X509KeyManager getDefaultKeyManager() {
        X509KeyManager result = defaultKeyManager;
        if (result == null) {
            // single-check idiom
            defaultKeyManager = result = createDefaultKeyManager();
        }
        return result;
    }
    private static X509KeyManager createDefaultKeyManager() {
        try {
            String algorithm = KeyManagerFactory.getDefaultAlgorithm();
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
            kmf.init(null, null);
            KeyManager[] kms = kmf.getKeyManagers();
            return findX509KeyManager(kms);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyStoreException e) {
            return null;
        } catch (UnrecoverableKeyException e) {
            return null;
        }
    }
    private static X509KeyManager findX509KeyManager(KeyManager[] kms) {
        for (KeyManager km : kms) {
            if (km instanceof X509KeyManager) {
                return (X509KeyManager)km;
            }
        }
        return null;
    }

    /**
     * Gets the default trust manager.
     *
     * TODO: Move this to a published API under dalvik.system.
     */
    public static X509TrustManager getDefaultTrustManager() {
        X509TrustManager result = defaultTrustManager;
        if (result == null) {
            // single-check idiom
            defaultTrustManager = result = createDefaultTrustManager();
        }
        return result;
    }
    private static X509TrustManager createDefaultTrustManager() {
        try {
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
            tmf.init((KeyStore) null);
            TrustManager[] tms = tmf.getTrustManagers();
            X509TrustManager trustManager = findX509TrustManager(tms);
            return trustManager;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyStoreException e) {
            return null;
        }
    }
    private static X509TrustManager findX509TrustManager(TrustManager[] tms) {
        for (TrustManager tm : tms) {
            if (tm instanceof X509TrustManager) {
                return (X509TrustManager)tm;
            }
        }
        return null;
    }
}
