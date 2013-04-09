/*
 * Copyright (C) 2011 The Android Open Source Project
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

package libcore.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import junit.framework.TestCase;

/**
 * Test that interrupting a thread blocked on I/O causes that thread to throw
 * an InterruptedIOException.
 */
public final class InterruptedStreamTest extends TestCase {

    private static final int BUFFER_SIZE = 1024 * 1024;

    private Socket[] sockets;

    @Override protected void tearDown() throws Exception {
        if (sockets != null) {
            sockets[0].close();
            sockets[1].close();
        }
        Thread.interrupted(); // clear interrupted bit
        super.tearDown();
    }

    public void testInterruptPipedInputStream() throws Exception {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);
        testInterruptInputStream(in);
    }

    public void testInterruptPipedOutputStream() throws Exception {
        PipedOutputStream out = new PipedOutputStream();
        new PipedInputStream(out);
        testInterruptOutputStream(out);
    }

    public void testInterruptPipedReader() throws Exception {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader(writer);
        testInterruptReader(reader);
    }

    public void testInterruptPipedWriter() throws Exception {
        final PipedWriter writer = new PipedWriter();
        new PipedReader(writer);
        testInterruptWriter(writer);
    }

    public void testInterruptReadablePipeChannel() throws Exception {
        testInterruptReadableChannel(Pipe.open().source());
    }

    public void testInterruptWritablePipeChannel() throws Exception {
        testInterruptWritableChannel(Pipe.open().sink());
    }

    public void testInterruptReadableSocketChannel() throws Exception {
        sockets = newSocketChannelPair();
        testInterruptReadableChannel(sockets[0].getChannel());
    }

    public void testInterruptWritableSocketChannel() throws Exception {
        sockets = newSocketChannelPair();
        testInterruptReadableChannel(sockets[0].getChannel());
    }

    /**
     * Returns a pair of connected sockets backed by NIO socket channels.
     */
    private Socket[] newSocketChannelPair() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(0));
        SocketChannel clientSocketChannel = SocketChannel.open();
        clientSocketChannel.connect(serverSocketChannel.socket().getLocalSocketAddress());
        SocketChannel server = serverSocketChannel.accept();
        serverSocketChannel.close();
        return new Socket[] { clientSocketChannel.socket(), server.socket() };
    }

    private void testInterruptInputStream(final InputStream in) throws Exception {
        interruptMeLater();
        try {
            in.read();
            fail();
        } catch (InterruptedIOException expected) {
        }
    }

    private void testInterruptReader(final PipedReader reader) throws Exception {
        interruptMeLater();
        try {
            reader.read();
            fail();
        } catch (InterruptedIOException expected) {
        }
    }

    private void testInterruptReadableChannel(final ReadableByteChannel channel) throws Exception {
        interruptMeLater();
        try {
            channel.read(ByteBuffer.allocate(BUFFER_SIZE));
            fail();
        } catch (ClosedByInterruptException expected) {
        }
    }

    private void testInterruptOutputStream(final OutputStream out) throws Exception {
        interruptMeLater();
        try {
            // this will block when the receiving buffer fills up
            while (true) {
                out.write(new byte[BUFFER_SIZE]);
            }
        } catch (InterruptedIOException expected) {
        }
    }

    private void testInterruptWriter(final PipedWriter writer) throws Exception {
        interruptMeLater();
        try {
            // this will block when the receiving buffer fills up
            while (true) {
                writer.write(new char[BUFFER_SIZE]);
            }
        } catch (InterruptedIOException expected) {
        }
    }

    private void testInterruptWritableChannel(final WritableByteChannel channel) throws Exception {
        interruptMeLater();
        try {
            // this will block when the receiving buffer fills up
            while (true) {
                channel.write(ByteBuffer.allocate(BUFFER_SIZE));
            }
        } catch (ClosedByInterruptException expected) {
        }
    }

    private void interruptMeLater() throws Exception {
        final Thread toInterrupt = Thread.currentThread();
        new Thread(new Runnable () {
            @Override public void run() {
                toInterrupt.interrupt();
            }
        }).start();
    }
}
