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
 * A persistent {@link javax.net.ssl.SSLSession} cache used by
 * {@link javax.net.ssl.SSLSessionContext} to share client-side SSL sessions
 * across processes. For example, this cache enables applications to
 * persist and reuse sessions across restarts.
 *
 * <p>The {@code SSLSessionContext} implementation converts
 * {@code SSLSession}s into raw bytes and vice versa. The exact makeup of the
 * session data is dependent upon the caller's implementation and is opaque to
 * the {@code SSLClientSessionCache} implementation.
 */
public interface SSLClientSessionCache {

  /**
   * Gets data from a pre-existing session for a given server host and port.
   *
   * @param host from {@link javax.net.ssl.SSLSession#getPeerHost()}
   * @param port from {@link javax.net.ssl.SSLSession#getPeerPort()}
   * @return the session data or null if none is cached
   * @throws NullPointerException if host is null
   */
  public byte[] getSessionData(String host, int port);

  /**
   * Stores session data for the given session.
   *
   * @param session to cache data for
   * @param sessionData to cache
   * @throws NullPointerException if session, result of
   *  {@code session.getPeerHost()} or data is null
   */
  public void putSessionData(SSLSession session, byte[] sessionData);
}