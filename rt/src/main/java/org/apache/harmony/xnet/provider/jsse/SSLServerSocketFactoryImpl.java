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

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import javax.net.ssl.SSLServerSocketFactory;
import libcore.util.EmptyArray;

/**
 * Implementation of SSLServerSocketFactory.
 */
public class SSLServerSocketFactoryImpl extends SSLServerSocketFactory {

    private SSLParametersImpl sslParameters;
    private IOException instantiationException;

    /**
     * Constructor.
     */
    public SSLServerSocketFactoryImpl() {
        try {
            this.sslParameters = SSLParametersImpl.getDefault();
            this.sslParameters.setUseClientMode(false);
        } catch (KeyManagementException e) {
            instantiationException =
                new IOException("Delayed instantiation exception:");
            instantiationException.initCause(e);
        }
    }

    /**
     * Constructor.
     */
    protected SSLServerSocketFactoryImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = (SSLParametersImpl) sslParameters.clone();
        this.sslParameters.setUseClientMode(false);
    }

    /**
     * @see javax.net.ssl.SSLServerSocketFactory#getDefaultCipherSuites()
     */
    @Override
    public String[] getDefaultCipherSuites() {
        if (instantiationException != null) {
            return EmptyArray.STRING;
        }
        return sslParameters.getEnabledCipherSuites();
    }

    /**
     * @see javax.net.ssl.SSLServerSocketFactory#getSupportedCipherSuites()
     */
    @Override
    public String[] getSupportedCipherSuites() {
        if (instantiationException != null) {
            return EmptyArray.STRING;
        }
        return CipherSuite.getSupportedCipherSuiteNames();
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket()
     */
    @Override
    public ServerSocket createServerSocket() throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl((SSLParametersImpl) sslParameters.clone());
    }


    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int)
     */
    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port,
                (SSLParametersImpl) sslParameters.clone());
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int,int)
     */
    @Override
    public ServerSocket createServerSocket(int port, int backlog)
            throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port, backlog,
                (SSLParametersImpl) sslParameters.clone());
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int,int,InetAddress)
     */
    @Override
    public ServerSocket createServerSocket(int port, int backlog,
            InetAddress iAddress) throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port, backlog, iAddress,
                (SSLParametersImpl) sslParameters.clone());
    }
}
