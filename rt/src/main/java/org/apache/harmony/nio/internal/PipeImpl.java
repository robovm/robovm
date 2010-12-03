/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.apache.harmony.luni.platform.FileDescriptorHandler;

/*
 * default implementation of Pipe
 * 
 */

final class PipeImpl extends Pipe {

    private SinkChannelImpl sink;

    private SourceChannelImpl source;

    private int serverPort;

    public PipeImpl() throws IOException {
        super();
        sink = new SinkChannelImpl(SelectorProvider.provider());
        source = new SourceChannelImpl(SelectorProvider.provider());
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws Exception {
                    sink.finishConnect();
                    source.accept();
                    source.closeServer();
                    return null;
                }
            });
        } catch (PrivilegedActionException e) {
            reset();
            Exception ex = e.getException();
            if (ex instanceof IOException) {
                throw (IOException) ex;
            }
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            }
            throw new RuntimeException(ex);
        }
    }

    private void reset(){
        if(sink != null){
            try {
                sink.close();
            } catch (Exception e) {
            }
        }
        if(source != null){
            try {
                source.closeServer();
            } catch (Exception e) {
            }
            try {
                source.close();
            } catch (Exception e) {
            }
        }
    }

    /*
     * @see java.nio.channels.Pipe#sink()
     */
    public SinkChannel sink() {
        return sink;
    }

    /*
     * @see java.nio.channels.Pipe#source()
     */
    public SourceChannel source() {
        return source;
    }

    /*
     * default implementation of SourceChannel
     */
    private class SourceChannelImpl extends Pipe.SourceChannel implements
            FileDescriptorHandler {

        private SocketChannelImpl sourceSocket;

        private ServerSocketChannel sourceServer;

        /*
         * constructor
         */
        protected SourceChannelImpl(SelectorProvider provider)
                throws IOException {
            super(provider);
            sourceServer = provider.openServerSocketChannel();
            sourceServer.socket().bind(
                new InetSocketAddress(InetAddress.getByName(null), 0));
            serverPort = sourceServer.socket().getLocalPort();
        }

        void closeServer() throws IOException {
            sourceServer.close();
        }

        void accept() throws IOException {
            sourceSocket = (SocketChannelImpl) sourceServer.accept();
        }

        protected void implCloseSelectableChannel() throws IOException {
            sourceSocket.close();
        }

        protected void implConfigureBlocking(boolean blockingMode)
                throws IOException {
            sourceSocket.configureBlocking(blockingMode);
        }

        public int read(ByteBuffer buffer) throws IOException {
            return sourceSocket.read(buffer);
        }

        public long read(ByteBuffer[] buffers) throws IOException {
            return read(buffers, 0, buffers.length);
        }

        public long read(ByteBuffer[] buffers, int offset, int length)
                throws IOException {
            return sourceSocket.read(buffers, offset, length);
        }

        public FileDescriptor getFD() {
            return sourceSocket.getFD();
        }
    }

    /*
     * default implementation of SinkChannel
     */
    private class SinkChannelImpl extends Pipe.SinkChannel implements
            FileDescriptorHandler {

        private SocketChannelImpl sinkSocket;

        protected SinkChannelImpl(SelectorProvider provider) throws IOException {
            super(provider);
            sinkSocket = (SocketChannelImpl) provider.openSocketChannel();
        }

        public boolean finishConnect() throws IOException {
            return sinkSocket.connect(
                new InetSocketAddress(InetAddress.getByName(null), serverPort));
        }

        protected void implCloseSelectableChannel() throws IOException {
            sinkSocket.close();
        }

        protected void implConfigureBlocking(boolean blockingMode)
                throws IOException {
            sinkSocket.configureBlocking(blockingMode);
        }

        public int write(ByteBuffer buffer) throws IOException {
            return sinkSocket.write(buffer);
        }

        public long write(ByteBuffer[] buffers) throws IOException {
            return write(buffers, 0, buffers.length);
        }

        public long write(ByteBuffer[] buffers, int offset, int length)
                throws IOException {
            return sinkSocket.write(buffers, offset, length);
        }

        public FileDescriptor getFD() {
            return sinkSocket.getFD();
        }
    }
}
