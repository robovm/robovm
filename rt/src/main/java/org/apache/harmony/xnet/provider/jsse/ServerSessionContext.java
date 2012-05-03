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

import javax.net.ssl.SSLSession;

/**
 * Caches server sessions. Indexes by session ID. Users typically look up
 * sessions using the ID provided by an SSL client.
 */
public class ServerSessionContext extends AbstractSessionContext {

    private SSLServerSessionCache persistentCache;

    public ServerSessionContext() {
        super(100, 0);

        // TODO make sure SSL_CTX does not automaticaly clear sessions we want it to cache
        // SSL_CTX_set_session_cache_mode(sslCtxNativePointer, SSL_SESS_CACHE_NO_AUTO_CLEAR);

        // TODO remove SSL_CTX session cache limit so we can manage it
        // SSL_CTX_sess_set_cache_size(sslCtxNativePointer, 0);

        // TODO override trimToSize and removeEldestEntry to use
        // SSL_CTX_sessions to remove from native cache
    }

    public void setPersistentCache(SSLServerSessionCache persistentCache) {
        this.persistentCache = persistentCache;
    }

    protected void sessionRemoved(SSLSession session) {}

    @Override
    public SSLSession getSession(byte[] sessionId) {
        SSLSession session = super.getSession(sessionId);
        if (session != null) {
            return session;
        }

        // Check persistent cache.
        if (persistentCache != null) {
            byte[] data = persistentCache.getSessionData(sessionId);
            if (data != null) {
                session = toSession(data, null, -1);
                if (session != null && session.isValid()) {
                    super.putSession(session);
                    return session;
                }
            }
        }

        return null;
    }

    @Override
    void putSession(SSLSession session) {
        super.putSession(session);

        // TODO: In background thread.
        if (persistentCache != null) {
            byte[] data = toBytes(session);
            if (data != null) {
                persistentCache.putSessionData(session, data);
            }
        }
    }
}
