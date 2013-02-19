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

import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;

/**
 * Caches client sessions. Indexes by host and port. Users are typically
 * looking to reuse any session for a given host and port.
 */
public class ClientSessionContext extends AbstractSessionContext {

    /**
     * Sessions indexed by host and port. Protect from concurrent
     * access by holding a lock on sessionsByHostAndPort.
     */
    final Map<HostAndPort, SSLSession> sessionsByHostAndPort
        = new HashMap<HostAndPort, SSLSession>();

    private SSLClientSessionCache persistentCache;

    public ClientSessionContext() {
        super(10, 0);
    }

    public int size() {
        return sessionsByHostAndPort.size();
    }

    public void setPersistentCache(SSLClientSessionCache persistentCache) {
        this.persistentCache = persistentCache;
    }

    protected void sessionRemoved(SSLSession session) {
        String host = session.getPeerHost();
        int port = session.getPeerPort();
        if (host == null) {
            return;
        }
        HostAndPort hostAndPortKey = new HostAndPort(host, port);
        synchronized (sessionsByHostAndPort) {
            sessionsByHostAndPort.remove(hostAndPortKey);
        }
    }

    /**
     * Finds a cached session for the given host name and port.
     *
     * @param host of server
     * @param port of server
     * @return cached session or null if none found
     */
    public SSLSession getSession(String host, int port) {
        if (host == null) {
            return null;
        }
        SSLSession session;
        HostAndPort hostAndPortKey = new HostAndPort(host, port);
        synchronized (sessionsByHostAndPort) {
            session = sessionsByHostAndPort.get(hostAndPortKey);
        }
        if (session != null && session.isValid()) {
            return session;
        }

        // Look in persistent cache.
        if (persistentCache != null) {
            byte[] data = persistentCache.getSessionData(host, port);
            if (data != null) {
                session = toSession(data, host, port);
                if (session != null && session.isValid()) {
                    super.putSession(session);
                    synchronized (sessionsByHostAndPort) {
                        sessionsByHostAndPort.put(hostAndPortKey, session);
                    }
                    return session;
                }
            }
        }

        return null;
    }

    @Override
    public void putSession(SSLSession session) {
        super.putSession(session);

        String host = session.getPeerHost();
        int port = session.getPeerPort();
        if (host == null) {
            return;
        }

        HostAndPort hostAndPortKey = new HostAndPort(host, port);
        synchronized (sessionsByHostAndPort) {
            sessionsByHostAndPort.put(hostAndPortKey, session);
        }

        // TODO: This in a background thread.
        if (persistentCache != null) {
            byte[] data = toBytes(session);
            if (data != null) {
                persistentCache.putSessionData(session, data);
            }
        }
    }

    static class HostAndPort {
        final String host;
        final int port;

        HostAndPort(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override public int hashCode() {
            return host.hashCode() * 31 + port;
        }

        @Override public boolean equals(Object o) {
            if (!(o instanceof HostAndPort)) {
                return false;
            }
            HostAndPort lhs = (HostAndPort) o;
            return host.equals(lhs.host) && port == lhs.port;
        }
    }
}
