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

import java.io.IOException;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLSession;
import junit.framework.Assert;

/**
 * TestSSLEnginePair is a convenience class for other tests that want
 * a pair of connected and handshaked client and server SSLEngines for
 * testing.
 */
public final class TestSSLEnginePair extends Assert {
    public final TestSSLContext c;
    public final SSLEngine server;
    public final SSLEngine client;

    private TestSSLEnginePair(TestSSLContext c,
                              SSLEngine server,
                              SSLEngine client) {
        this.c = c;
        this.server = server;
        this.client = client;
    }

    public static TestSSLEnginePair create(Hooks hooks) throws IOException {
        return create(TestSSLContext.create(), hooks);
    }

    public static TestSSLEnginePair create(TestSSLContext c, Hooks hooks) throws IOException {
        SSLEngine[] engines = connect(c, hooks);
        return new TestSSLEnginePair(c, engines[0], engines[1]);
    }

    /**
     * Create a new connected server/client engine pair within a
     * existing SSLContext. Optionally specify clientCipherSuites to
     * allow forcing new SSLSession to test SSLSessionContext
     * caching. Optionally specify serverCipherSuites for testing
     * cipher suite negotiation.
     */
    public static SSLEngine[] connect(final TestSSLContext c,
                                      Hooks hooks) throws IOException {
        if (hooks == null) {
            hooks = new Hooks();
        }

        SSLSession session = c.clientContext.createSSLEngine().getSession();

        int packetBufferSize = session.getPacketBufferSize();
        ByteBuffer clientToServer = ByteBuffer.allocate(packetBufferSize);
        ByteBuffer serverToClient = ByteBuffer.allocate(packetBufferSize);

        int applicationBufferSize = session.getApplicationBufferSize();
        ByteBuffer scratch = ByteBuffer.allocate(applicationBufferSize);

        SSLEngine client = c.clientContext.createSSLEngine();
        SSLEngine server = c.serverContext.createSSLEngine();
        client.setUseClientMode(true);
        server.setUseClientMode(false);
        hooks.beforeBeginHandshake(client, server);
        client.beginHandshake();
        server.beginHandshake();

        while (true) {
            boolean clientDone = client.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING;
            boolean serverDone = server.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING;
            if (clientDone && serverDone) {
                break;
            }

            boolean progress = false;
            if (!clientDone) {
                progress |= handshakeCompleted(client,
                                               clientToServer,
                                               serverToClient,
                                               scratch);
            }
            if (!serverDone) {
                progress |= handshakeCompleted(server,
                                               serverToClient,
                                               clientToServer,
                                               scratch);
            }
            if (!progress) {
                // let caller detect the problem, but don't just hang here
                break;
            }
        }

        return new SSLEngine[] { server, client };
    }

    public static class Hooks {
        void beforeBeginHandshake(SSLEngine client, SSLEngine server) {}
    }

    private static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocate(0);

    private static boolean handshakeCompleted(SSLEngine engine,
                                              ByteBuffer output,
                                              ByteBuffer input,
                                              ByteBuffer scratch) throws IOException {
        try {
            // make the other side's output into our input
            input.flip();

            HandshakeStatus status = engine.getHandshakeStatus();
            switch (status) {

                case NEED_TASK:
                    boolean progress = false;
                    while (true) {
                        Runnable runnable = engine.getDelegatedTask();
                        if (runnable == null) {
                            return progress;
                        }
                        runnable.run();
                        progress = true;
                    }

                case NEED_UNWRAP:
                    // avoid underflow
                    if (input.remaining() == 0) {
                        return false;
                    }
                    SSLEngineResult unwrapResult = engine.unwrap(input, scratch);
                    assertEquals(SSLEngineResult.Status.OK, unwrapResult.getStatus());
                    assertEquals(0, scratch.position());
                    return true;

                case NEED_WRAP:
                    // avoid possible overflow
                    if (output.remaining() != output.capacity()) {
                        return false;
                    }
                    SSLEngineResult wrapResult = engine.wrap(EMPTY_BYTE_BUFFER, output);
                    assertEquals(SSLEngineResult.Status.OK, wrapResult.getStatus());
                    return true;

                case NOT_HANDSHAKING:
                    // should have been checked by caller before calling
                case FINISHED:
                    // only returned by wrap/unrap status, not getHandshakeStatus
                    throw new IllegalStateException("Unexpected HandshakeStatus = " + status);
                default:
                    throw new IllegalStateException("Unknown HandshakeStatus = " + status);
            }
        } finally {
            // shift consumed input, restore to output mode
            input.compact();
        }
    }
}
