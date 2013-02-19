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

import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import libcore.util.EmptyArray;

public final class SSLSessionImpl implements SSLSession, Cloneable  {

    /** Session object reporting an invalid cipher suite of "SSL_NULL_WITH_NULL_NULL" */
    public static final SSLSessionImpl NULL_SESSION = new SSLSessionImpl(null);

    private long creationTime;
    private boolean isValid = true;
    private final Map<String, Object> values = new HashMap<String, Object>();

    byte[] id;
    long lastAccessedTime;
    ProtocolVersion protocol;
    CipherSuite cipherSuite;
    SSLSessionContext context;
    X509Certificate[] localCertificates;
    X509Certificate[] peerCertificates;
    private String peerHost;
    private int peerPort = -1;
    byte[] master_secret;
    byte[] clientRandom;
    byte[] serverRandom;
    final boolean isServer;

    public SSLSessionImpl(CipherSuite cipher_suite, SecureRandom secureRandom) {
        creationTime = System.currentTimeMillis();
        lastAccessedTime = creationTime;
        if (cipher_suite == null) {
            this.cipherSuite = CipherSuite.SSL_NULL_WITH_NULL_NULL;
            id = EmptyArray.BYTE;
            isServer = false;
            isValid = false;
        } else {
            this.cipherSuite = cipher_suite;
            id = new byte[32];
            secureRandom.nextBytes(id);
            long time = creationTime / 1000;
            id[28] = (byte) ((time & 0xFF000000) >>> 24);
            id[29] = (byte) ((time & 0x00FF0000) >>> 16);
            id[30] = (byte) ((time & 0x0000FF00) >>> 8);
            id[31] = (byte) ((time & 0x000000FF));
            isServer = true;
        }

    }

    public SSLSessionImpl(SecureRandom secureRandom) {
        this(null, secureRandom);
    }

    public int getApplicationBufferSize() {
        return SSLRecordProtocol.MAX_DATA_LENGTH;
    }

    public String getCipherSuite() {
        return cipherSuite.getName();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public byte[] getId() {
        return id;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public Certificate[] getLocalCertificates() {
        return localCertificates;
    }

    public Principal getLocalPrincipal() {
        if (localCertificates != null && localCertificates.length > 0) {
            return localCertificates[0].getSubjectX500Principal();
        }
        return null;
    }

    public int getPacketBufferSize() {
        return SSLRecordProtocol.MAX_SSL_PACKET_SIZE;
    }

    public javax.security.cert.X509Certificate[] getPeerCertificateChain()
            throws SSLPeerUnverifiedException {
        if (peerCertificates == null) {
            throw new SSLPeerUnverifiedException("No peer certificate");
        }
        javax.security.cert.X509Certificate[] certs = new javax.security.cert.X509Certificate[peerCertificates.length];
        for (int i = 0; i < certs.length; i++) {
            try {
                certs[i] = javax.security.cert.X509Certificate.getInstance(peerCertificates[i]
                        .getEncoded());
            } catch (javax.security.cert.CertificateException ignored) {
            } catch (CertificateEncodingException ignored) {
            }
        }
        return certs;
    }

    public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
        if (peerCertificates == null) {
            throw new SSLPeerUnverifiedException("No peer certificate");
        }
        return peerCertificates;
    }

    public String getPeerHost() {
        return peerHost;
    }

    public int getPeerPort() {
        return peerPort;
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        if (peerCertificates == null) {
            throw new SSLPeerUnverifiedException("No peer certificate");
        }
        return peerCertificates[0].getSubjectX500Principal();
    }

    public String getProtocol() {
        return (protocol == null) ? "NONE" : protocol.name;
    }

    public SSLSessionContext getSessionContext() {
        return context;
    }

    public Object getValue(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name == null");
        }
        return values.get(name);
    }

    public String[] getValueNames() {
        return values.keySet().toArray(new String[values.size()]);
    }

    public void invalidate() {
        isValid = false;
        context = null;
    }

    public boolean isValid() {
        if (isValid && context != null && context.getSessionTimeout() != 0
                && lastAccessedTime + context.getSessionTimeout() > System.currentTimeMillis()) {
            isValid = false;
        }
        return isValid;
    }

    public void putValue(String name, Object value) {
        if (name == null || value == null) {
            throw new IllegalArgumentException("name == null || value == null");
        }
        Object old = values.put(name, value);
        if (value instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) value).valueBound(new SSLSessionBindingEvent(this, name));
        }
        if (old instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) old).valueUnbound(new SSLSessionBindingEvent(this, name));
        }

    }

    public void removeValue(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name == null");
        }
        Object old = values.remove(name);
        if (old instanceof SSLSessionBindingListener) {
            SSLSessionBindingListener listener = (SSLSessionBindingListener) old;
            listener.valueUnbound(new SSLSessionBindingEvent(this, name));
        }
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    void setPeer(String peerHost, int peerPort) {
        this.peerHost = peerHost;
        this.peerPort = peerPort;
    }
}
