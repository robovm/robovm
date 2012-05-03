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

package org.apache.harmony.xnet.provider.jsse;

import java.io.IOException;

/**
 *
 * Represents server hello done message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.5.
 * Server hello done</a>
 *
 */
public class ServerHelloDone extends Message {

    /**
     * Creates outbound message
     *
     */
    public ServerHelloDone() {
    }

    /**
     * Creates inbound message
     * @param in
     * @param length
     * @throws IOException
     */
    public ServerHelloDone(HandshakeIODataStream in, int length)
            throws IOException {
        if (length != 0) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect ServerHelloDone");
        }
    }

    /**
     * Sends message
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
    }

    /**
     * Returns message length
     * @return
     */
    @Override
    public int length() {
        return 0;
    }

    /**
     * Returns message type
     * @return
     */
    @Override
    public int getType() {
        return Handshake.SERVER_HELLO_DONE;
    }
}
