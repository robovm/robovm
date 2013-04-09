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

package org.apache.harmony.xnet.tests.support;

import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Additional class for verification of SSLContextSpi and SSLContext
 * functionality
 *
 */

public class MySSLContextSpi extends SSLContextSpi {
    private boolean init = false;
    protected void engineInit(KeyManager[] km, TrustManager[] tm,
            SecureRandom sr) throws KeyManagementException {
        if (sr == null) {
            throw new KeyManagementException(
                    "secureRandom is null");
        }
        init = true;
    }

    protected SSLSocketFactory engineGetSocketFactory() {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return null;
    }

    protected SSLServerSocketFactory engineGetServerSocketFactory() {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return null;
    }

    protected SSLSessionContext engineGetServerSessionContext() {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return null;
    }

    protected SSLSessionContext engineGetClientSessionContext() {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return null;
    }

    protected SSLParameters engineGetDefaultSSLParameters() {
        engineGetSocketFactory();
        return null;
    }

    protected SSLParameters engineGetSupportedSSLParameters() {
        engineGetSocketFactory();
        return null;
    }

    /*
     * FIXME: add these methods
     */
    protected SSLEngine engineCreateSSLEngine(String host, int port) {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return new tmpSSLEngine(host, port);
    }

    protected SSLEngine engineCreateSSLEngine() {
        if (!init) {
            throw new RuntimeException("Not initialiazed");
        }
        return new tmpSSLEngine();
    }

    public class tmpSSLEngine extends SSLEngine {
        String tmpHost;
        int tmpPort;
        public tmpSSLEngine() {
            tmpHost = null;
            tmpPort = 0;
        }
        public tmpSSLEngine(String host, int port) {
            tmpHost = host;
            tmpPort = port;
        }
        public String getPeerHost() {
            return tmpHost;
        }
        public int getPeerPort() {
            return tmpPort;
        }
        public void beginHandshake() throws SSLException { }
        public void closeInbound() throws SSLException { }
        public void closeOutbound() {}
        public Runnable getDelegatedTask() { return null; }
        public String[] getEnabledCipherSuites() { return null; }
        public String[] getEnabledProtocols() {return null; }
        public boolean getEnableSessionCreation() { return true; }
        public SSLEngineResult.HandshakeStatus getHandshakeStatus() { return null; }
        public boolean getNeedClientAuth() { return true; }
        public SSLSession getSession() { return null; }
        public String[] getSupportedCipherSuites()  { return null; }
        public String[] getSupportedProtocols()  { return null; }
        public boolean getUseClientMode()  { return true; }
        public boolean getWantClientAuth()  { return true; }
        public boolean isInboundDone()  { return true; }
        public boolean isOutboundDone()  { return true; }
        public void setEnabledCipherSuites(String[] suites) { }
        public void setEnabledProtocols(String[] protocols) { }
        public void setEnableSessionCreation(boolean flag) { }
        public void setNeedClientAuth(boolean need) { }
        public void setUseClientMode(boolean mode) { }
        public void setWantClientAuth(boolean want) { }
        public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts,
                int offset, int length) throws SSLException {
            return null;
        }
        public SSLEngineResult wrap(ByteBuffer[] srcs, int offset,
                int length, ByteBuffer dst) throws SSLException {
            return null;
        }
    }
}
