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

package org.apache.harmony.luni.net;

import java.io.FileDescriptor;
import java.net.SocketException;

import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.net.PlainSocketImpl;

/**
 * This class overrides create to call natives that set the options required
 * for server sockets.
 */
public class PlainServerSocketImpl extends PlainSocketImpl {

    public PlainServerSocketImpl() {
        super();
    }

    public PlainServerSocketImpl(FileDescriptor fd) {
        super();
        this.fd = fd;
    }

    @Override
    protected void create(boolean isStreaming) throws SocketException {
        streaming = isStreaming;
        if (isStreaming) {
            netImpl.createServerStreamSocket(fd, NetUtil.preferIPv4Stack());
        } else {
            netImpl.createDatagramSocket(fd, NetUtil.preferIPv4Stack());
        }
    }
}
