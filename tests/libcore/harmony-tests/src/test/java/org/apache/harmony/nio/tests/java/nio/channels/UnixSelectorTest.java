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

package org.apache.harmony.nio.tests.java.nio.channels;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import junit.framework.TestCase;

public class UnixSelectorTest extends TestCase {
    static class Server {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket socket = null;

        Server() throws Exception {
            serverChannel.configureBlocking(false);
        }

        public void initialize() throws Exception {
            this.socket = serverChannel.socket();
            socket.bind(null);
        }

        public void accept() {
            Thread serverThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (serverChannel.accept() == null) {
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {}
                }
            });
            serverThread.start();
        }

        public void close() throws Exception{
            serverChannel.close();
        }
    }

    public void testSelectorAcceptAndRead() throws Exception {
        Selector sel0 = Selector.open();
        Selector sel1 = Selector.open();
        Server server = new Server();
        SelectionKey mkey0 = server.serverChannel.register(sel0, SelectionKey.OP_ACCEPT);
        server.serverChannel.register(sel1, SelectionKey.OP_ACCEPT);

        // HUP is treating as acceptable
        assertEquals(1, sel0.select(100));
        assertEquals(true, sel0.selectedKeys().contains(mkey0));
        server.initialize();
        // after bind can not accept
        assertEquals(0, sel1.select(100));
        server.accept();
        Thread.sleep(1000);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector sel2 = Selector.open();
        socketChannel.register(sel2, SelectionKey.OP_WRITE);
        boolean isConnected = socketChannel.connect(server.socket.getLocalSocketAddress());
        if (!isConnected) {
            socketChannel.finishConnect();
        }

        assertEquals(true, socketChannel.isConnected());
        server.close();
        Thread.sleep(3000);
        assertEquals(true, socketChannel.isConnected());
        assertEquals(1, sel2.select(100));
    }

    public void testSelectUnConnectedChannel() throws Exception {
        SocketChannel socketChannel2 = SocketChannel.open();
        socketChannel2.configureBlocking(false);
        Selector sel3 = Selector.open();
        SelectionKey mkey3 = socketChannel2.register(sel3, SelectionKey.OP_WRITE);
        // HUP is also treating as writable
        assertEquals(1, sel3.select(100));
        assertEquals(false, mkey3.isConnectable());
        // even the channel is not connected, the selector could be writable
        assertEquals(false, socketChannel2.isConnected());
        assertEquals(true, mkey3.isWritable());

        Selector sel4 = Selector.open();
        SelectionKey mkey4 = socketChannel2.register(sel4, SelectionKey.OP_CONNECT);
        assertEquals(1, sel4.select(100));
        assertEquals(false, mkey4.isWritable());
        assertEquals(true, mkey4.isConnectable());

        Selector sel5 = Selector.open();
        SelectionKey mkey5 = socketChannel2.register(sel5, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);
        assertEquals(1, sel5.select(100));
        assertEquals(true, mkey5.isWritable());
        assertEquals(true, mkey5.isConnectable());
    }
}
