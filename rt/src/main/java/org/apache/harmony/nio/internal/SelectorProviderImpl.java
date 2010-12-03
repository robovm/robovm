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

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/*
 * Internal implementation of SelectorProvider.
 */
public class SelectorProviderImpl extends SelectorProvider {

    /*
     * Constructor for this class.
     */
    public SelectorProviderImpl() {
        super();
    }

    /**
     * @see java.nio.channels.spi.SelectorProvider#openDatagramChannel()
     */
    public DatagramChannel openDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this);
    }

    /**
     * @see java.nio.channels.spi.SelectorProvider#openPipe()
     */
    public Pipe openPipe() throws IOException {
        return new PipeImpl();
    }

    /**
     * @see java.nio.channels.spi.SelectorProvider#openSelector()
     */
    public AbstractSelector openSelector() throws IOException {
        return new SelectorImpl(this);
    }

    /**
     * @see java.nio.channels.spi.SelectorProvider#openServerSocketChannel()
     */
    public ServerSocketChannel openServerSocketChannel() throws IOException {
        return new ServerSocketChannelImpl(this);
    }

    /**
     * @see java.nio.channels.spi.SelectorProvider#openSocketChannel()
     */
    public SocketChannel openSocketChannel() throws IOException {
        return new SocketChannelImpl(this);
    }
}
