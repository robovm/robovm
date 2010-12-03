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

package javax.net.ssl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.SecureRandom;

import javax.net.SocketFactory;

public abstract class SSLContextSpi {
    /**
     * Creates a new {@code SSLContextSpi} instance.
     */
    public SSLContextSpi() {
        super();
    }

    /**
     * Initializes this {@code SSLContext} instance. All of the arguments are
     * optional, and the security providers will be searched for the required
     * implementations of the needed algorithms.
     *
     * @param km
     *            the key sources or {@code null}.
     * @param tm
     *            the trust decision sources or {@code null}.
     * @param sr
     *            the randomness source or {@code null.}
     * @throws KeyManagementException
     *             if initializing this instance fails.
     */
    protected abstract void engineInit(KeyManager[] km, TrustManager[] tm, SecureRandom sr)
            throws KeyManagementException;

    /**
     * Returns a socket factory for this instance.
     *
     * @return a socket factory for this instance.
     */
    protected abstract SSLSocketFactory engineGetSocketFactory();

    /**
     * Returns a server socket factory for this instance.
     *
     * @return a server socket factory for this instance.
     */
    protected abstract SSLServerSocketFactory engineGetServerSocketFactory();

    /**
     * Creates an {@code SSLEngine} instance from this context with the
     * specified hostname and port.
     *
     * @param host
     *            the name of the host
     * @param port
     *            the port
     * @return an {@code SSLEngine} instance from this context.
     * @throws UnsupportedOperationException
     *             if the provider does not support the operation.
     */
    protected abstract SSLEngine engineCreateSSLEngine(String host, int port);

    /**
     * Creates an {@code SSLEngine} instance from this context.
     *
     * @return an {@code SSLEngine} instance from this context.
     * @throws UnsupportedOperationException
     *             if the provider does not support the operation.
     */
    protected abstract SSLEngine engineCreateSSLEngine();

    /**
     * Returns the SSL session context that encapsulates the set of SSL sessions
     * that can be used for the server side of the SSL handshake.
     *
     * @return the SSL server session context for this context or {@code null}
     *         if the underlying provider does not provide an implementation of
     *         the {@code SSLSessionContext} interface.
     */
    protected abstract SSLSessionContext engineGetServerSessionContext();

    /**
     * Returns the SSL session context that encapsulates the set of SSL sessions
     * that can be used for the client side of the SSL handshake.
     *
     * @return the SSL client session context for this context or {@code null}
     *         if the underlying provider does not provide an implementation of
     *         the {@code SSLSessionContext} interface.
     */
    protected abstract SSLSessionContext engineGetClientSessionContext();

    protected SSLParameters engineGetDefaultSSLParameters() {
        // Initially, a default set of cipher suites will be enabled on a new
        // socket that represents the minimum suggested configuration
        SSLParameters defaultSSLParameters = new SSLParameters();
        SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket();
            if (sslSocket == null)
                return null;
            defaultSSLParameters.setCipherSuites(sslSocket
                    .getEnabledCipherSuites());
            defaultSSLParameters.setProtocols(sslSocket.getEnabledProtocols());
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new UnsupportedOperationException(
                    "the default SSL parameters could not be obtained");
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultSSLParameters;
    };

    protected SSLParameters engineGetSupportedSSLParameters() {
        SSLParameters supportSSLParameters = new SSLParameters();
        SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
        if (sslSocketFactory == null)
            return null;
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) sslSocketFactory.createSocket();
            if (sslSocket == null)
                return null;
            supportSSLParameters.setCipherSuites(sslSocket
                    .getSupportedCipherSuites());
            supportSSLParameters
                    .setProtocols(sslSocket.getSupportedProtocols());
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new UnsupportedOperationException(
                    "the supported SSL parameters could not be obtained");
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return supportSSLParameters;
    };
}
