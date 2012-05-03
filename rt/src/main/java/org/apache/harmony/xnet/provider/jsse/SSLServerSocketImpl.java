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
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;

/**
 * SSLServerSocket implementation
 * @see javax.net.ssl.SSLServerSocket class documentation for more information.
 */
public class SSLServerSocketImpl extends SSLServerSocket {

    // the sslParameters object encapsulates all the info
    // about supported and enabled cipher suites and protocols,
    // as well as the information about client/server mode of
    // ssl socket, whether it require/want client authentication or not,
    // and controls whether new SSL sessions may be established by this
    // socket or not.
    private final SSLParametersImpl sslParameters;

    // logger
    private Logger.Stream logger = Logger.getStream("ssocket");

    /**
     * Ctor
     * @param   sslParameters:  SSLParameters
     * @throws  IOException
     */
    protected SSLServerSocketImpl(SSLParametersImpl sslParameters) throws IOException {
        this.sslParameters = sslParameters;
    }

    /**
     * Ctor
     * @param   port:   int
     * @param   sslParameters:  SSLParameters
     * @throws  IOException
     */
    protected SSLServerSocketImpl(int port, SSLParametersImpl sslParameters)
        throws IOException {
        super(port);
        this.sslParameters = sslParameters;
    }

    /**
     * Ctor
     * @param   port:   int
     * @param   backlog:    int
     * @param   sslParameters:  SSLParameters
     * @throws  IOException
     */
    protected SSLServerSocketImpl(int port, int backlog,
            SSLParametersImpl sslParameters) throws IOException {
        super(port, backlog);
        this.sslParameters = sslParameters;
    }

    /**
     * Ctor
     * @param   port:   int
     * @param   backlog:    int
     * @param   iAddress:   InetAddress
     * @param   sslParameters:  SSLParameters
     * @throws  IOException
     */
    protected SSLServerSocketImpl(int port, int backlog,
                                InetAddress iAddress,
                                SSLParametersImpl sslParameters)
        throws IOException {
        super(port, backlog, iAddress);
        this.sslParameters = sslParameters;
    }

    // --------------- SSLParameters based methods ---------------------

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getSupportedCipherSuites()
     * method documentation for more information
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return CipherSuite.getSupportedCipherSuiteNames();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getEnabledCipherSuites()
     * method documentation for more information
     */
    @Override
    public String[] getEnabledCipherSuites() {
        return sslParameters.getEnabledCipherSuites();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setEnabledCipherSuites(String[])
     * method documentation for more information
     */
    @Override
    public void setEnabledCipherSuites(String[] suites) {
        sslParameters.setEnabledCipherSuites(suites);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getSupportedProtocols()
     * method documentation for more information
     */
    @Override
    public String[] getSupportedProtocols() {
        return ProtocolVersion.supportedProtocols.clone();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getEnabledProtocols()
     * method documentation for more information
     */
    @Override
    public String[] getEnabledProtocols() {
        return sslParameters.getEnabledProtocols();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setEnabledProtocols(String[])
     * method documentation for more information
     */
    @Override
    public void setEnabledProtocols(String[] protocols) {
        sslParameters.setEnabledProtocols(protocols);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setUseClientMode(boolean)
     * method documentation for more information
     */
    @Override
    public void setUseClientMode(boolean mode) {
        sslParameters.setUseClientMode(mode);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getUseClientMode()
     * method documentation for more information
     */
    @Override
    public boolean getUseClientMode() {
        return sslParameters.getUseClientMode();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setNeedClientAuth(boolean)
     * method documentation for more information
     */
    @Override
    public void setNeedClientAuth(boolean need) {
        sslParameters.setNeedClientAuth(need);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getNeedClientAuth()
     * method documentation for more information
     */
    @Override
    public boolean getNeedClientAuth() {
        return sslParameters.getNeedClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setWantClientAuth(boolean)
     * method documentation for more information
     */
    @Override
    public void setWantClientAuth(boolean want) {
        sslParameters.setWantClientAuth(want);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getWantClientAuth()
     * method documentation for more information
     */
    @Override
    public boolean getWantClientAuth() {
        return sslParameters.getWantClientAuth();
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#setEnableSessionCreation(boolean)
     * method documentation for more information
     */
    @Override
    public void setEnableSessionCreation(boolean flag) {
        sslParameters.setEnableSessionCreation(flag);
    }

    /**
     * This method works according to the specification of implemented class.
     * @see javax.net.ssl.SSLServerSocket#getEnableSessionCreation()
     * method documentation for more information
     */
    @Override
    public boolean getEnableSessionCreation() {
        return sslParameters.getEnableSessionCreation();
    }


    // ------------- ServerSocket's methods overridings ----------------

    /**
     * This method works according to the specification of implemented class.
     * @see java.net.ServerSocket#accept()
     * method documentation for more information
     */
    @Override
    public Socket accept() throws IOException {
        if (logger != null) {
            logger.println("SSLServerSocketImpl.accept ..");
        }
        SSLSocketImpl s = new SSLSocketImpl(
                (SSLParametersImpl) sslParameters.clone());
        implAccept(s);
        s.init();
        if (logger != null) {
            logger.println("SSLServerSocketImpl: accepted, initialized");
        }
        return s;
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        return "[SSLServerSocketImpl]";
    }

    // -----------------------------------------------------------------
}
