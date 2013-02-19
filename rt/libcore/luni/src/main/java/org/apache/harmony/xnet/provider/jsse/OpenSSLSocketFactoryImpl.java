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

package org.apache.harmony.xnet.provider.jsse;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;

public class OpenSSLSocketFactoryImpl extends javax.net.ssl.SSLSocketFactory {

    private final SSLParametersImpl sslParameters;
    private final IOException instantiationException;

    public OpenSSLSocketFactoryImpl() {
        SSLParametersImpl sslParametersLocal = null;
        IOException instantiationExceptionLocal = null;
        try {
            sslParametersLocal = SSLParametersImpl.getDefault();
        } catch (KeyManagementException e) {
            instantiationExceptionLocal = new IOException("Delayed instantiation exception:");
            instantiationExceptionLocal.initCause(e);
        }
        this.sslParameters = sslParametersLocal;
        this.instantiationException = instantiationExceptionLocal;
    }

    public OpenSSLSocketFactoryImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = sslParameters;
        this.instantiationException = null;
    }

    public String[] getDefaultCipherSuites() {
        return NativeCrypto.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    public Socket createSocket() throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new OpenSSLSocketImpl((SSLParametersImpl) sslParameters.clone());
    }

    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return new OpenSSLSocketImpl(host, port, (SSLParametersImpl) sslParameters.clone());
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
            throws IOException, UnknownHostException {
        return new OpenSSLSocketImpl(host,
                                     port,
                                     localHost,
                                     localPort,
                                     (SSLParametersImpl) sslParameters.clone());
    }

    public Socket createSocket(InetAddress host, int port) throws IOException {
        return new OpenSSLSocketImpl(host, port, (SSLParametersImpl) sslParameters.clone());
    }

    public Socket createSocket(InetAddress address,
                               int port,
                               InetAddress localAddress,
                               int localPort)
            throws IOException {
        return new OpenSSLSocketImpl(address,
                                     port,
                                     localAddress,
                                     localPort,
                                     (SSLParametersImpl) sslParameters.clone());
    }

    public Socket createSocket(Socket s, String host, int port, boolean autoClose)
            throws IOException {
        return new OpenSSLSocketImplWrapper(s,
                                            host,
                                            port,
                                            autoClose,
                                            (SSLParametersImpl) sslParameters.clone());
    }
}
