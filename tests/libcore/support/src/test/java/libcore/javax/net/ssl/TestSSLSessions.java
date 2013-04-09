/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.javax.net.ssl;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * TestSSLSessions is a convenience class for other tests that want
 * precreated SSLSessions for testing. It contains a connected
 * client/server pair of SSLSession as well as an invalid SSLSession.
 */
public final class TestSSLSessions {

    /**
     * An invalid session that is not connected
     */
    public final SSLSession invalid;

    /**
     * The server side of a connected session
     */
    public final SSLSession server;

    /**
     * The client side of a connected session
     */
    public final SSLSession client;

    /**
     * The associated SSLSocketTest.Helper that is the source of
     * the client and server SSLSessions.
     */
    public final TestSSLSocketPair s;

    private TestSSLSessions(SSLSession invalid,
                            SSLSession server,
                            SSLSession client,
                            TestSSLSocketPair s) {
        this.invalid = invalid;
        this.server = server;
        this.client = client;
        this.s = s;
    }

    public void close() {
        s.close();
    }

    public static final TestSSLSessions create() {
        try {
            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket ssl = (SSLSocket) sf.createSocket();
            SSLSession invalid = ssl.getSession();
            TestSSLSocketPair s = TestSSLSocketPair.create();
            return new TestSSLSessions(invalid, s.server.getSession(), s.client.getSession(), s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
