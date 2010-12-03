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

import org.apache.harmony.xnet.provider.jsse.SSLSessionContextImpl;

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
public class SSLParameters {

    // default source of authentication keys
    private static X509KeyManager defaultKeyManager;
    // default source of authentication trust decisions
    private static X509TrustManager defaultTrustManager;
    // default source of random numbers
    private static SecureRandom defaultSecureRandom;
    // default SSL parameters
    private static SSLParameters defaultParameters;

    // client session context contains the set of reusable
    // client-side SSL sessions
    private SSLSessionContextImpl clientSessionContext;
    // server session context contains the set of reusable
    // server-side SSL sessions
    private SSLSessionContextImpl serverSessionContext;
    // source of authentication keys
    private X509KeyManager keyManager;
    // source of authentication trust decisions
    private X509TrustManager trustManager;
    // source of random numbers
    private SecureRandom secureRandom;

    // cipher suites available for SSL connection
    protected CipherSuite[] enabledCipherSuites;
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

    /**
     * Creates an instance of SSLParameters.
     */
    private SSLParameters() {
        this.enabledCipherSuites = CipherSuite.defaultCipherSuites;
    }

    /**
     * Initializes the parameters. Naturally this constructor is used
     * in SSLContextImpl.engineInit method which dirrectly passes its 
     * parameters. In other words this constructor holds all
     * the functionality provided by SSLContext.init method.
     * @see SSLContext.init(KeyManager,TrustManager,SecureRandom)
     * for more information
     */
    protected SSLParameters(KeyManager[] kms, TrustManager[] tms,
            SecureRandom sr, SSLSessionContextImpl clientSessionContext,
            SSLSessionContextImpl serverSessionContext)
            throws KeyManagementException {
        this();
        this.serverSessionContext = serverSessionContext;
        this.clientSessionContext = clientSessionContext;
    	try {
            // initialize key manager
            boolean initialize_default = false;
            // It's not described by the spec of SSLContext what should happen 
            // if the arrays of length 0 are specified. This implementation
            // behave as for null arrays (i.e. use installed security providers)
            if ((kms == null) || (kms.length == 0)) {
                if (defaultKeyManager == null) {
                    KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                            KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(null, null);            	
                    kms = kmf.getKeyManagers();
                    // tell that we are trying to initialize defaultKeyManager
                    initialize_default = true;
                } else {
                    keyManager = defaultKeyManager;
                }
            }
            if (keyManager == null) { // was not initialized by default
                for (int i = 0; i < kms.length; i++) {
                    if (kms[i] instanceof X509KeyManager) {
                        keyManager = (X509KeyManager)kms[i];
                        break;
                    }
                }
                if (keyManager == null) {
                    throw new KeyManagementException("No X509KeyManager found");
                }
                if (initialize_default) {
                    // found keyManager is default key manager
                    defaultKeyManager = keyManager;
                }
            }
            
            // initialize trust manager
            initialize_default = false;
            if ((tms == null) || (tms.length == 0)) {
                if (defaultTrustManager == null) {
                    TrustManagerFactory tmf = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    tmf.init((KeyStore)null);
                    tms = tmf.getTrustManagers();
                    initialize_default = true;
                } else {
                    trustManager = defaultTrustManager;
                }
            }
            if (trustManager == null) { // was not initialized by default
                for (int i = 0; i < tms.length; i++) {
                    if (tms[i] instanceof X509TrustManager) {
                        trustManager = (X509TrustManager)tms[i];
                        break;
                    }
                }
                if (trustManager == null) {
                    throw new KeyManagementException("No X509TrustManager found");
                }
                if (initialize_default) {
                    // found trustManager is default trust manager
                    defaultTrustManager = trustManager;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new KeyManagementException(e);
        } catch (KeyStoreException e) {
            throw new KeyManagementException(e);
        } catch (UnrecoverableKeyException e) {
            throw new KeyManagementException(e);        	
        }
        // initialize secure random
        if (sr == null) {
            if (defaultSecureRandom == null) {
        	    defaultSecureRandom = new SecureRandom();
            }
            secureRandom = defaultSecureRandom;
        } else {
            secureRandom = sr;
        }
    }

    protected static SSLParameters getDefault() throws KeyManagementException {
        if (defaultParameters == null) {
            defaultParameters = new SSLParameters(null, null, null,
                    new SSLSessionContextImpl(), new SSLSessionContextImpl());
        }
        return (SSLParameters) defaultParameters.clone();
    }
    
    /**
     * @return server session context
     */
    protected SSLSessionContextImpl getServerSessionContext() {
        return serverSessionContext;
    }

    /**
     * @return client session context
     */
    protected SSLSessionContextImpl getClientSessionContext() {
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
        return secureRandom;
    }

    /**
     * @return the names of enabled cipher suites
     */
    protected String[] getEnabledCipherSuites() {
        if (enabledCipherSuiteNames == null) {
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
            throw new IllegalArgumentException("Provided parameter is null");
        }
        CipherSuite[] cipherSuites = new CipherSuite[suites.length];
        for (int i=0; i<suites.length; i++) {
            cipherSuites[i] = CipherSuite.getByName(suites[i]);
            if (cipherSuites[i] == null || !cipherSuites[i].supported) {
                throw new IllegalArgumentException(suites[i] +
                        " is not supported.");
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
     * @param   suites: String[]
     */
    protected void setEnabledProtocols(String[] protocols) {
        if (protocols == null) {
            throw new IllegalArgumentException("Provided parameter is null");
        }
        for (int i=0; i<protocols.length; i++) {
            if (!ProtocolVersion.isSupported(protocols[i])) {
                throw new IllegalArgumentException("Protocol " + protocols[i] +
                        " is not supported.");
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
        SSLParameters parameters = new SSLParameters();

        parameters.clientSessionContext = clientSessionContext;
        parameters.serverSessionContext = serverSessionContext;
        parameters.keyManager = keyManager;
        parameters.trustManager = trustManager;
        parameters.secureRandom = secureRandom;

        parameters.enabledCipherSuites = enabledCipherSuites;
        parameters.enabledProtocols = enabledProtocols;

        parameters.client_mode = client_mode;
        parameters.need_client_auth = need_client_auth;
        parameters.want_client_auth = want_client_auth;
        parameters.enable_session_creation = enable_session_creation;

        return parameters;
    }
}

