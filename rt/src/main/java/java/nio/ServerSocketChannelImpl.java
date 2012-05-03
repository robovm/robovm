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

package java.nio;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.PlainServerSocketImpl;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import libcore.io.IoUtils;

/**
 * The default ServerSocketChannel.
 */
final class ServerSocketChannelImpl extends ServerSocketChannel implements FileDescriptorChannel {

    private final ServerSocketAdapter socket;
    private final SocketImpl impl;

    private boolean isBound = false;

    private final Object acceptLock = new Object();

    public ServerSocketChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        this.socket = new ServerSocketAdapter(this);
        this.impl = socket.getImpl$();
    }

    @Override public ServerSocket socket() {
        return socket;
    }

    @Override public SocketChannel accept() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!isBound) {
            throw new NotYetBoundException();
        }

        // Create an empty socket channel. This will be populated by ServerSocketAdapter.accept.
        SocketChannelImpl result = new SocketChannelImpl(provider(), false);
        boolean connected = false;
        try {
            begin();
            synchronized (acceptLock) {
                synchronized (blockingLock()) {
                    do {
                        try {
                            socket.implAccept(result);
                            // select successfully, break out immediately.
                            break;
                        } catch (SocketTimeoutException e) {
                            // continue to accept if the channel is in blocking mode.
                            // TODO: does this make sense? why does blocking imply no timeouts?
                        }
                    } while (isBlocking());
                }
            }
        } finally {
            end(result.socket().isConnected());
        }
        return result.socket().isConnected() ? result : null;
    }

    @Override protected void implConfigureBlocking(boolean blocking) throws IOException {
        synchronized (blockingLock()) {
            IoUtils.setBlocking(impl.getFD$(), blocking);
        }
    }

    synchronized protected void implCloseSelectableChannel() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public FileDescriptor getFD() {
        return impl.getFD$();
    }

    private static class ServerSocketAdapter extends ServerSocket {
        private final ServerSocketChannelImpl channelImpl;

        ServerSocketAdapter(ServerSocketChannelImpl aChannelImpl) throws IOException {
            this.channelImpl = aChannelImpl;
        }

        @Override public void bind(SocketAddress localAddress, int backlog) throws IOException {
            super.bind(localAddress, backlog);
            channelImpl.isBound = true;
        }

        @Override public Socket accept() throws IOException {
            if (!channelImpl.isBound) {
                throw new IllegalBlockingModeException();
            }
            SocketChannel sc = channelImpl.accept();
            if (sc == null) {
                throw new IllegalBlockingModeException();
            }
            return sc.socket();
        }

        public Socket implAccept(SocketChannelImpl clientSocketChannel) throws IOException {
            Socket clientSocket = clientSocketChannel.socket();
            boolean connectOK = false;
            try {
                synchronized (this) {
                    super.implAccept(clientSocket);
                    clientSocketChannel.setConnected();
                    clientSocketChannel.setBound(true);
                    clientSocketChannel.finishAccept();
                }
                connectOK = true;
            } finally {
                if (!connectOK) {
                    clientSocket.close();
                }
            }
            return clientSocket;
        }

        @Override public ServerSocketChannel getChannel() {
            return channelImpl;
        }

        @Override public boolean isBound() {
            return channelImpl.isBound;
        }

        @Override public void bind(SocketAddress localAddress) throws IOException {
            super.bind(localAddress);
            channelImpl.isBound = true;
        }

        @Override public void close() throws IOException {
            synchronized (channelImpl) {
                if (channelImpl.isOpen()) {
                    channelImpl.close();
                } else {
                    super.close();
                }
            }
        }
    }
}
