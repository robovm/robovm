/*
 * Copyright (C) 2007 The Android Open Source Project
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

package org.conscrypt;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import javax.net.ssl.SSLException;

/**
 * OpenSSL-based implementation of server sockets.
 */
public class OpenSSLServerSocketImpl extends javax.net.ssl.SSLServerSocket {
    private final SSLParametersImpl sslParameters;
    private String[] enabledProtocols = NativeCrypto.getSupportedProtocols();
    private String[] enabledCipherSuites = NativeCrypto.getDefaultCipherSuites();
    private boolean channelIdEnabled;

    protected OpenSSLServerSocketImpl(SSLParametersImpl sslParameters) throws IOException {
        this.sslParameters = sslParameters;
    }

    protected OpenSSLServerSocketImpl(int port, SSLParametersImpl sslParameters)
        throws IOException {
        super(port);
        this.sslParameters = sslParameters;
    }

    protected OpenSSLServerSocketImpl(int port, int backlog, SSLParametersImpl sslParameters)
        throws IOException {
        super(port, backlog);
        this.sslParameters = sslParameters;
    }

    protected OpenSSLServerSocketImpl(int port,
                                      int backlog,
                                      InetAddress iAddress,
                                      SSLParametersImpl sslParameters)
        throws IOException {
        super(port, backlog, iAddress);
        this.sslParameters = sslParameters;
    }

    @Override
    public boolean getEnableSessionCreation() {
        return sslParameters.getEnableSessionCreation();
    }

    @Override
    public void setEnableSessionCreation(boolean flag) {
        sslParameters.setEnableSessionCreation(flag);
    }

    /**
     * The names of the protocols' versions that may be used on this SSL
     * connection.
     * @return an array of protocols names
     */
    @Override
    public String[] getSupportedProtocols() {
        return NativeCrypto.getSupportedProtocols();
    }

    /**
     * The names of the protocols' versions that in use on this SSL connection.
     *
     * @return an array of protocols names
     */
    @Override
    public String[] getEnabledProtocols() {
        return enabledProtocols.clone();
    }

    /**
     * This method enables the protocols' versions listed by
     * getSupportedProtocols().
     *
     * @param protocols names of all the protocols to enable.
     *
     * @throws IllegalArgumentException when one or more of the names in the
     *             array are not supported, or when the array is null.
     */
    @Override
    public void setEnabledProtocols(String[] protocols) {
        enabledProtocols = NativeCrypto.checkEnabledProtocols(protocols);
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    @Override
    public String[] getEnabledCipherSuites() {
        return enabledCipherSuites.clone();
    }

    /**
     * Enables/disables the TLS Channel ID extension for this server socket.
     */
    public void setChannelIdEnabled(boolean enabled) {
      channelIdEnabled = enabled;
    }

    /**
     * Checks whether the TLS Channel ID extension is enabled for this server socket.
     */
    public boolean isChannelIdEnabled() {
      return channelIdEnabled;
    }

    /**
     * This method enables the cipher suites listed by
     * getSupportedCipherSuites().
     *
     * @param suites the names of all the cipher suites to enable
     * @throws IllegalArgumentException when one or more of the ciphers in array
     *         suites are not supported, or when the array is null.
     */
    @Override
    public void setEnabledCipherSuites(String[] suites) {
        enabledCipherSuites = NativeCrypto.checkEnabledCipherSuites(suites);
    }

    @Override
    public boolean getWantClientAuth() {
        return sslParameters.getWantClientAuth();
    }

    @Override
    public void setWantClientAuth(boolean want) {
        sslParameters.setWantClientAuth(want);
    }

    @Override
    public boolean getNeedClientAuth() {
        return sslParameters.getNeedClientAuth();
    }

    @Override
    public void setNeedClientAuth(boolean need) {
        sslParameters.setNeedClientAuth(need);
    }

    @Override
    public void setUseClientMode(boolean mode) {
        sslParameters.setUseClientMode(mode);
    }

    @Override
    public boolean getUseClientMode() {
        return sslParameters.getUseClientMode();
    }

    @Override
    public Socket accept() throws IOException {

        if (!sslParameters.getUseClientMode()) {
            checkEnabledCipherSuites();
        }

        OpenSSLSocketImpl socket = new OpenSSLSocketImpl(sslParameters,
                                                         enabledProtocols.clone(),
                                                         enabledCipherSuites.clone());
        socket.setChannelIdEnabled(channelIdEnabled);
        implAccept(socket);
        return socket;
    }

    /**
     * Check if any of the enabled cipher suites has a chance to work.
     * Not 100% accurate, just a useful diagnostic that the RI does.
     */
    private void checkEnabledCipherSuites() throws SSLException {
        /* Loop over all enabled cipher suites. If we find a problem,
         * we just continue to the next one. If we find one that could
         * work, we return. This basically makes sure the caller has
         * configured some appropriate certificate/key unless
         * an anonymous cipher is picked.
         */
        for (String enabledCipherSuite : enabledCipherSuites) {
            if (enabledCipherSuite.equals(NativeCrypto.TLS_EMPTY_RENEGOTIATION_INFO_SCSV)) {
                continue;
            }
            String keyType = CipherSuite.getByName(enabledCipherSuite).getServerKeyType();
            if (keyType == null) {
                // anonymous always work
                return;
            }
            if (keyType.equals(CipherSuite.KEY_TYPE_RSA)
                    || keyType.equals(CipherSuite.KEY_TYPE_DH_RSA)) {
                if (checkForPrivateKey(keyType, RSAPrivateKey.class)) {
                    return;
                }
                continue;
            }
            if (keyType.equals(CipherSuite.KEY_TYPE_DSA)
                    || keyType.equals(CipherSuite.KEY_TYPE_DH_DSA)) {
                if (checkForPrivateKey(keyType, DSAPrivateKey.class)) {
                    return;
                }
                continue;
            }
            if (keyType.equals(CipherSuite.KEY_TYPE_EC)
                    || keyType.equals(CipherSuite.KEY_TYPE_EC_RSA)
                    || keyType.equals(CipherSuite.KEY_TYPE_EC_EC)) {
                if (checkForPrivateKey(keyType, ECPrivateKey.class)) {
                    return;
                }
                continue;
            }
            throw new IllegalStateException("Unknown key type " + keyType);
        }
        throw new SSLException("Could not find any key store entries "
                               + "to support the enabled cipher suites.");
    }

    private boolean checkForPrivateKey(String keyType, Class<?> keyClass) {
        String alias = sslParameters.getKeyManager().chooseServerAlias(keyType, null, null);
        if (alias == null) {
            return false;
        }
        PrivateKey key = sslParameters.getKeyManager().getPrivateKey(alias);
        return (key != null && keyClass.isAssignableFrom(key.getClass()));
    }
}
