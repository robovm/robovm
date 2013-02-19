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
 * Represents Finished message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.9.
 * Finished</a>
 *
 */
public class Finished extends Message {

    // verify data
    private byte[] data;

    /**
     * Creates outbound message
     * @param bytes
     */
    public Finished(byte[] bytes) {
        data = bytes;
        length = data.length;
    }

    /**
     * Creates inbound message
     * @param in
     * @param length
     * @throws IOException
     */
    public Finished(HandshakeIODataStream in, int length)
            throws IOException {
        if (length == 12 || length == 36) {
            data = in.read(length);
            this.length = data.length;
        } else {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect Finished");
        }
    }

    @Override
    public void send(HandshakeIODataStream out) {
        out.write(data);
    }

    /**
     * Returns message type
     * @return
     */
    @Override
    public int getType() {
        return Handshake.FINISHED;
    }

    /**
     * Returns verify data
     * @return
     */
    public byte[] getData() {
        return data;
    }
}
