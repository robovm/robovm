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
import java.security.SecureRandom;
import libcore.io.Streams;

/**
 *
 * Represents server hello message.
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.1.3.
 * Server hello.</a>
 */
public class ServerHello extends Message {

    /**
     * Server version
     */
    byte[] server_version = new byte[2];

    /**
     * Random bytes
     */
    byte[] random = new byte[32];

    /**
     * Session id
     */
    byte[] session_id;

    /**
     * Selected cipher suite
     */
    CipherSuite cipher_suite;

    /**
     * Selected compression method
     */
    byte compression_method;

    /**
     * Creates outbound message
     * @param sr
     * @param server_version
     * @param session_id
     * @param cipher_suite
     * @param compression_method
     */
    public ServerHello(SecureRandom sr, byte[] server_version,
            byte[] session_id, CipherSuite cipher_suite, byte compression_method) {
        long gmt_unix_time = new java.util.Date().getTime() / 1000;
        sr.nextBytes(random);
        random[0] = (byte) ((gmt_unix_time & 0xFF000000) >>> 24);
        random[1] = (byte) ((gmt_unix_time & 0xFF0000) >>> 16);
        random[2] = (byte) ((gmt_unix_time & 0xFF00) >>> 8);
        random[3] = (byte) (gmt_unix_time & 0xFF);
        this.session_id = session_id;
        this.cipher_suite = cipher_suite;
        this.compression_method = compression_method;
        this.server_version = server_version;
        length = 38 + session_id.length;
    }

    /**
     * Creates inbound message
     * @param in
     * @param length
     * @throws IOException
     */
    public ServerHello(HandshakeIODataStream in, int length) throws IOException {

        server_version[0] = (byte) in.read();
        server_version[1] = (byte) in.read();
        Streams.readFully(in, random);
        int size = in.readUint8();
        session_id = new byte[size];
        in.read(session_id, 0, size);
        byte b0 = (byte) in.read();
        byte b1 = (byte) in.read();
        cipher_suite = CipherSuite.getByCode(b0, b1);
        compression_method = (byte) in.read();
        this.length = 38 + session_id.length;
        if (this.length != length) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect ServerHello");
        }

    }

    /**
     * Sends message
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
        out.write(server_version);
        out.write(random);
        out.writeUint8(session_id.length);
        out.write(session_id);
        out.write(cipher_suite.toBytes());
        out.write(compression_method);
        length = 38 + session_id.length;
    }

    /**
     * Returns server random
     * @return
     */
    public byte[] getRandom() {
        return random;
    }

    /**
     * Returns message type
     * @return
     */
    @Override
    public int getType() {
        return Handshake.SERVER_HELLO;
    }
}
