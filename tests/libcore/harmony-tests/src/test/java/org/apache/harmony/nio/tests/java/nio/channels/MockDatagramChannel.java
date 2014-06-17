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

package org.apache.harmony.nio.tests.java.nio.channels;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.spi.SelectorProvider;

class MockDatagramChannel extends DatagramChannel {

    public MockDatagramChannel(SelectorProvider arg0) {
        super(arg0);
    }

    public DatagramSocket socket() {
        return null;
    }

    public boolean isConnected() {
        return false;
    }

    public DatagramChannel connect(SocketAddress arg0) throws IOException {
        return null;
    }

    public DatagramChannel disconnect() throws IOException {
        return null;
    }

    public SocketAddress receive(ByteBuffer arg0) throws IOException {
        return null;
    }

    public int send(ByteBuffer arg0, SocketAddress arg1) throws IOException {
        return 0;
    }

    public int read(ByteBuffer arg0) throws IOException {
        return 0;
    }

    public long read(ByteBuffer[] arg0, int arg1, int arg2) throws IOException {
        return 0;
    }

    public int write(ByteBuffer arg0) throws IOException {
        return 0;
    }

    public long write(ByteBuffer[] arg0, int arg1, int arg2) throws IOException {
        return 0;
    }

    protected void implCloseSelectableChannel() throws IOException {
        // empty
    }

    protected void implConfigureBlocking(boolean arg0) throws IOException {
        // empty
    }

}
