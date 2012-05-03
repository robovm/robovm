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
import java.util.Arrays;
import libcore.io.Streams;
import libcore.util.EmptyArray;

/**
 * Represents Client Hello message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.1.2.
 * Client hello</a>
 *
 */
public class ClientHello extends Message {

    /**
     * Client version
     */
    final byte[] client_version;

    /**
     * Random bytes
     */
    final byte[] random = new byte[32];

    /**
     * Session id
     */
    final byte[] session_id;

    /**
     * Cipher suites supported by the client
     */
    final CipherSuite[] cipher_suites;

    /**
     * Compression methods supported by the client
     */
    final byte[] compression_methods;

    /**
     * Creates outbound message
     * @param sr
     * @param version
     * @param ses_id
     * @param cipher_suite
     */
    public ClientHello(SecureRandom sr, byte[] version, byte[] ses_id,
            CipherSuite[] cipher_suite) {
        client_version = version;
        long gmt_unix_time = System.currentTimeMillis()/1000;
        sr.nextBytes(random);
        random[0] = (byte) (gmt_unix_time & 0xFF000000 >>> 24);
        random[1] = (byte) (gmt_unix_time & 0xFF0000 >>> 16);
        random[2] = (byte) (gmt_unix_time & 0xFF00 >>> 8);
        random[3] = (byte) (gmt_unix_time & 0xFF);
        session_id = ses_id;
        this.cipher_suites = cipher_suite;
        compression_methods = new byte[] { 0 }; // CompressionMethod.null
        length = 38 + session_id.length + (this.cipher_suites.length << 1)
                + compression_methods.length;
    }

    /**
     * Creates inbound message
     * @param in
     * @param length
     * @throws IOException
     */
    public ClientHello(HandshakeIODataStream in, int length) throws IOException {
        client_version = new byte[2];
        client_version[0] = (byte) in.readUint8();
        client_version[1] = (byte) in.readUint8();
        Streams.readFully(in, random);
        int size = in.read();
        session_id = new byte[size];
        in.read(session_id, 0, size);
        int l = in.readUint16();
        if ((l & 0x01) == 0x01) { // cipher suites length must be an even number
            fatalAlert(AlertProtocol.DECODE_ERROR,
                    "DECODE ERROR: incorrect ClientHello");
        }
        size = l >> 1;
        cipher_suites = new CipherSuite[size];
        for (int i = 0; i < size; i++) {
            byte b0 = (byte) in.read();
            byte b1 = (byte) in.read();
            cipher_suites[i] = CipherSuite.getByCode(b0, b1);
        }
        size = in.read();
        compression_methods = new byte[size];
        in.read(compression_methods, 0, size);
        this.length = 38 + session_id.length + (cipher_suites.length << 1)
                + compression_methods.length;
        if (this.length > length) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect ClientHello");
        }
        // for forward compatibility, extra data is permitted;
        // must be ignored
        if (this.length < length) {
            in.skip(length - this.length);
            this.length = length;
        }
    }
    /**
     * Parse V2ClientHello
     * @param in
     * @throws IOException
     */
    public ClientHello(HandshakeIODataStream in) throws IOException {
        if (in.readUint8() != 1) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect V2ClientHello");
        }
        client_version = new byte[2];
        client_version[0] = (byte) in.readUint8();
        client_version[1] = (byte) in.readUint8();
        int cipher_spec_length = in.readUint16();
        if (in.readUint16() != 0) { // session_id_length
            // as client already knows the protocol known to a server it should
            // initiate the connection in that native protocol
            fatalAlert(AlertProtocol.DECODE_ERROR,
                    "DECODE ERROR: incorrect V2ClientHello, cannot be used for resuming");
        }
        int challenge_length = in.readUint16();
        if (challenge_length < 16) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect V2ClientHello, short challenge data");
        }
        session_id = EmptyArray.BYTE;
        cipher_suites = new CipherSuite[cipher_spec_length/3];
        for (int i = 0; i < cipher_suites.length; i++) {
            byte b0 = (byte) in.read();
            byte b1 = (byte) in.read();
            byte b2 = (byte) in.read();
            cipher_suites[i] = CipherSuite.getByCode(b0, b1, b2);
        }
        compression_methods = new byte[] { 0 }; // CompressionMethod.null

        if (challenge_length < 32) {
            Arrays.fill(random, 0, 32 - challenge_length, (byte)0);
            System.arraycopy(in.read(challenge_length), 0, random, 32 - challenge_length, challenge_length);
        } else if (challenge_length == 32) {
            System.arraycopy(in.read(32), 0, random, 0, 32);
        } else {
            System.arraycopy(in.read(challenge_length), challenge_length - 32, random, 0, 32);
        }
        if (in.available() > 0) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect V2ClientHello, extra data");
        }
        this.length = 38 + session_id.length + (cipher_suites.length << 1)
                + compression_methods.length;
    }

    /**
     * Sends message
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
        out.write(client_version);
        out.write(random);
        out.writeUint8(session_id.length);
        out.write(session_id);
        int size = cipher_suites.length << 1;
        out.writeUint16(size);
        for (int i = 0; i < cipher_suites.length; i++) {
            out.write(cipher_suites[i].toBytes());
        }
        out.writeUint8(compression_methods.length);
        for (int i = 0; i < compression_methods.length; i++) {
            out.write(compression_methods[i]);
        }
    }

    /**
     * Returns client random
     * @return client random
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
        return Handshake.CLIENT_HELLO;
    }
}
