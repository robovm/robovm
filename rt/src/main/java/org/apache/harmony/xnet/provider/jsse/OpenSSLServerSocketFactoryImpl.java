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
import java.net.ServerSocket;
import java.security.KeyManagementException;

public class OpenSSLServerSocketFactoryImpl extends javax.net.ssl.SSLServerSocketFactory {

    private SSLParametersImpl sslParameters;
    private IOException instantiationException;

    public OpenSSLServerSocketFactoryImpl() {
        try {
            this.sslParameters = SSLParametersImpl.getDefault();
            this.sslParameters.setUseClientMode(false);
        } catch (KeyManagementException e) {
            instantiationException =
                new IOException("Delayed instantiation exception:");
            instantiationException.initCause(e);
        }
    }

    public OpenSSLServerSocketFactoryImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = (SSLParametersImpl) sslParameters.clone();
        this.sslParameters.setUseClientMode(false);
    }

    public String[] getDefaultCipherSuites() {
        return NativeCrypto.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    public ServerSocket createServerSocket() throws IOException {
        return new OpenSSLServerSocketImpl((SSLParametersImpl) sslParameters.clone());
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        return new OpenSSLServerSocketImpl(port, (SSLParametersImpl) sslParameters.clone());
    }

    public ServerSocket createServerSocket(int port, int backlog)
            throws IOException {
        return new OpenSSLServerSocketImpl(port,
                                           backlog,
                                           (SSLParametersImpl) sslParameters.clone());
    }

    public ServerSocket createServerSocket(int port,
                                           int backlog,
                                           InetAddress iAddress) throws IOException {
        return new OpenSSLServerSocketImpl(port,
                                           backlog,
                                           iAddress,
                                           (SSLParametersImpl) sslParameters.clone());
    }
}
