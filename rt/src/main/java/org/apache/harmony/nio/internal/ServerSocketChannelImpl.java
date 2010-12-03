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

package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
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

import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.net.PlainServerSocketImpl;
import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.Platform;

/*
 * The default implementation class of java.nio.channels.ServerSocketChannel.
 */
public class ServerSocketChannelImpl extends ServerSocketChannel implements
        FileDescriptorHandler {

    // The fd to interact with native code
    private final FileDescriptor fd;

    // The internal ServerSocket
    private final ServerSocket socket;

    private final SocketImpl impl;

    // whether the socket is bound
    boolean isBound = false;

    // lock for accept
    private static class AcceptLock {}
    private final Object acceptLock = new AcceptLock();

    /*
     * Constructor
     */
    public ServerSocketChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        fd = new FileDescriptor();
        Platform.getNetworkSystem().createStreamSocket(fd,
                NetUtil.preferIPv4Stack());
        impl = new PlainServerSocketImpl(fd);
        socket = new ServerSocketAdapter(impl, this);
    }
    
    // for native call
    @SuppressWarnings("unused")
    private ServerSocketChannelImpl() throws IOException {
        super(SelectorProvider.provider());
        fd = new FileDescriptor();
        impl = new PlainServerSocketImpl(fd);
        socket = new ServerSocketAdapter(impl, this);
        isBound = false;
    }

    /*
     * Getting the internal Socket If we have not the socket, we create a new
     * one.
     */
    public ServerSocket socket() {
        return socket;
    }

    /*
     * 
     * @see java.nio.channels.ServerSocketChannel#accept()
     */
    public SocketChannel accept() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!isBound) {
            throw new NotYetBoundException();
        }

        SocketChannel sockChannel = new SocketChannelImpl(SelectorProvider.provider(), false);
        Socket socketGot = sockChannel.socket();

        try {
            begin();

            synchronized (acceptLock) {
                    boolean isBlocking = isBlocking();
                    if (!isBlocking) {
                        // for non blocking mode, use select to see whether
                        // there are any pending connections.
                        int[] tryResult = new int[1];
                        boolean success = Platform.getNetworkSystem().select(
                                new FileDescriptor[] { this.fd },
                                new FileDescriptor[0], 1, 0, 0, tryResult);
                        if (!success || 0 == tryResult[0]) {
                            // no pending connections, returns immediately.
                            return null;
                        }
                    }
                    // do accept.
                    do {
                        try {
                            ((ServerSocketAdapter) socket).accept(socketGot,
                                    (SocketChannelImpl) sockChannel);
                            // select successfully, break out immediately.
                            break;
                        } catch (SocketTimeoutException e) {
                            // continue to accept if the channel is in blocking
                            // mode.
                        }
                    } while (isBlocking);
            }
        } finally {
            end(socketGot.isConnected());
        }
        return sockChannel;
    }

    /*
     * @see java.nio.channels.spi.AbstractSelectableChannel#implConfigureBlocking
     * 
     * (boolean)
     */
    protected void implConfigureBlocking(boolean blockingMode)
            throws IOException {
        // Do nothing here. For real accept() operation in nonblocking mode,
        // it uses INetworkSystem.select. Whether a channel is blocking can be
        // decided by isBlocking() method.
    }

    /*
     * 
     * @see java.nio.channels.spi.AbstractSelectableChannel#implCloseSelectableChannel()
     */
    synchronized protected void implCloseSelectableChannel() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    /*
     * Gets the FileDescriptor
     */
    public FileDescriptor getFD() {
        return fd;
    }

    /*
     * The adapter class of ServerSocket.
     */
    private class ServerSocketAdapter extends ServerSocket {
        /*
         * The related ServerSocketChannel.
         */
        ServerSocketChannelImpl channelImpl;

        /*
         * The Constructor.
         */
        ServerSocketAdapter(SocketImpl impl,
                ServerSocketChannelImpl aChannelImpl) {
            super(impl);
            this.channelImpl = aChannelImpl;
        }

        /*
         * 
         * @see java.net.ServerSocket#bind(java.net.SocketAddress, int)
         */
        public void bind(SocketAddress localAddr, int backlog)
                throws IOException {
            super.bind(localAddr, backlog);
            channelImpl.isBound = true;
        }

        /*
         * @see java.net.ServerSocket#accept()
         * 
         * If the channel is in non-blocking mode and there is no connection
         * ready to be accepted, invoking this method will cause an
         * IllegalBlockingModeException.
         */
        public Socket accept() throws IOException {
            if (!isBound) {
                throw new IllegalBlockingModeException();
            }
            SocketChannel sc = channelImpl.accept();
            if (null == sc) {
                throw new IllegalBlockingModeException();
            }
            return sc.socket();
        }

        /*
         * do the accept.
         */
        private Socket accept(Socket aSocket, SocketChannelImpl sockChannel)
                throws IOException {
            // a new socket is pass in so we do not need to "Socket aSocket =
            // new Socket();"
            boolean connectOK = false;
            try {
                synchronized (this) {
                    super.implAccept(aSocket);
                    sockChannel.setConnected();
                    sockChannel.setBound(true);
                }
                SecurityManager sm = System.getSecurityManager();
                if (sm != null) {
                    sm.checkAccept(aSocket.getInetAddress().getHostAddress(),
                            aSocket.getPort());
                }
                connectOK = true;
            } finally {
                if (!connectOK) {
                    aSocket.close();
                }
            }
            return aSocket;
        }

        /*
         * getting internal channel.
         */
        public ServerSocketChannel getChannel() {
            return channelImpl;
        }

        /*
         * 
         * @see java.net.ServerSocket#isBound()
         */
        public boolean isBound() {
            return channelImpl.isBound;
        }

        /*
         * 
         * @see java.net.ServerSocket#bind(java.net.SocketAddress)
         */
        public void bind(SocketAddress localAddr) throws IOException {
            super.bind(localAddr);
            channelImpl.isBound = true;
        }

        /*
         * @see java.net.ServerSocket#close()
         */
        public void close() throws IOException {
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
