/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import org.apache.harmony.security.provider.cert.X509CertImpl;

/**
 * Supports SSL session caches.
 */
abstract class AbstractSessionContext implements SSLSessionContext {

    volatile int maximumSize;
    volatile int timeout;

    final int sslCtxNativePointer = NativeCrypto.SSL_CTX_new();

    /** Identifies OpenSSL sessions. */
    static final int OPEN_SSL = 1;

    private final Map<ByteArray, SSLSession> sessions
            = new LinkedHashMap<ByteArray, SSLSession>() {
        @Override
        protected boolean removeEldestEntry(
                Map.Entry<ByteArray, SSLSession> eldest) {
            return maximumSize > 0 && size() > maximumSize;
        }
    };

    /**
     * Constructs a new session context.
     *
     * @param maximumSize of cache
     * @param timeout for cache entries
     */
    AbstractSessionContext(int maximumSize, int timeout) {
        this.maximumSize = maximumSize;
        this.timeout = timeout;
    }

    /**
     * Returns the collection of sessions ordered from oldest to newest
     */
    private Iterator<SSLSession> sessionIterator() {
        synchronized (sessions) {
            SSLSession[] array = sessions.values().toArray(
                    new SSLSession[sessions.size()]);
            return Arrays.asList(array).iterator();
        }
    }

    public final Enumeration getIds() {
        final Iterator<SSLSession> i = sessionIterator();
        return new Enumeration<byte[]>() {
            private SSLSession next;
            public boolean hasMoreElements() {
                if (next != null) {
                    return true;
                }
                while (i.hasNext()) {
                    SSLSession session = i.next();
                    if (session.isValid()) {
                        next = session;
                        return true;
                    }
                }
                next = null;
                return false;
            }
            public byte[] nextElement() {
                if (hasMoreElements()) {
                    byte[] id = next.getId();
                    next = null;
                    return id;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public final int getSessionCacheSize() {
        return maximumSize;
    }

    public final int getSessionTimeout() {
        return timeout;
    }

    /**
     * Makes sure cache size is < maximumSize.
     */
    protected void trimToSize() {
        synchronized (sessions) {
            int size = sessions.size();
            if (size > maximumSize) {
                int removals = size - maximumSize;
                Iterator<SSLSession> i = sessions.values().iterator();
                do {
                    SSLSession session = i.next();
                    i.remove();
                    sessionRemoved(session);
                } while (--removals > 0);
            }
        }
    }

    public void setSessionTimeout(int seconds)
            throws IllegalArgumentException {
        if (seconds < 0) {
            throw new IllegalArgumentException("seconds < 0");
        }
        timeout = seconds;

        synchronized (sessions) {
            Iterator<SSLSession> i = sessions.values().iterator();
            while (i.hasNext()) {
                SSLSession session = i.next();
                // SSLSession's know their context and consult the
                // timeout as part of their validity condition.
                if (!session.isValid()) {
                    i.remove();
                    sessionRemoved(session);
                }
            }
        }
    }

    /**
     * Called when a session is removed. Used by ClientSessionContext
     * to update its host-and-port based cache.
     */
    protected abstract void sessionRemoved(SSLSession session);

    public final void setSessionCacheSize(int size)
            throws IllegalArgumentException {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0");
        }

        int oldMaximum = maximumSize;
        maximumSize = size;

        // Trim cache to size if necessary.
        if (size < oldMaximum) {
            trimToSize();
        }
    }

    /**
     * Converts the given session to bytes.
     *
     * @return session data as bytes or null if the session can't be converted
     */
    byte[] toBytes(SSLSession session) {
        // TODO: Support SSLSessionImpl, too.
        if (!(session instanceof OpenSSLSessionImpl)) {
            return null;
        }

        OpenSSLSessionImpl sslSession = (OpenSSLSessionImpl) session;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream daos = new DataOutputStream(baos);

            daos.writeInt(OPEN_SSL); // session type ID

            // Session data.
            byte[] data = sslSession.getEncoded();
            daos.writeInt(data.length);
            daos.write(data);

            // Certificates.
            Certificate[] certs = session.getPeerCertificates();
            daos.writeInt(certs.length);

            for (Certificate cert : certs) {
                data = cert.getEncoded();
                daos.writeInt(data.length);
                daos.write(data);
            }
            // TODO: local certificates?

            return baos.toByteArray();
        } catch (IOException e) {
            log(e);
            return null;
        } catch (CertificateEncodingException e) {
            log(e);
            return null;
        }
    }

    /**
     * Creates a session from the given bytes.
     *
     * @return a session or null if the session can't be converted
     */
    SSLSession toSession(byte[] data, String host, int port) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dais = new DataInputStream(bais);
        try {
            int type = dais.readInt();
            if (type != OPEN_SSL) {
                log(new AssertionError("Unexpected type ID: " + type));
                return null;
            }

            int length = dais.readInt();
            byte[] sessionData = new byte[length];
            dais.readFully(sessionData);

            int count = dais.readInt();
            X509CertImpl[] certs = new X509CertImpl[count];
            for (int i = 0; i < count; i++) {
                length = dais.readInt();
                byte[] certData = new byte[length];
                dais.readFully(certData);
                certs[i] = new X509CertImpl(certData);
            }

            return new OpenSSLSessionImpl(sessionData, host, port, certs, this);
        } catch (IOException e) {
            log(e);
            return null;
        }
    }

    public SSLSession getSession(byte[] sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("sessionId == null");
        }
        ByteArray key = new ByteArray(sessionId);
        SSLSession session;
        synchronized (sessions) {
            session = sessions.get(key);
        }
        if (session != null && session.isValid()) {
            return session;
        }
        return null;
    }

    void putSession(SSLSession session) {
        byte[] id = session.getId();
        if (id.length == 0) {
            return;
        }
        ByteArray key = new ByteArray(id);
        synchronized (sessions) {
            sessions.put(key, session);
        }
    }

    static void log(Throwable t) {
        System.logW("Error converting session.", t);
    }

    @Override protected void finalize() throws Throwable {
        try {
            NativeCrypto.SSL_CTX_free(sslCtxNativePointer);
        } finally {
            super.finalize();
        }
    }
}
