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
import java.math.BigInteger;
import libcore.io.Streams;
import libcore.util.EmptyArray;

/**
 * Represents client key exchange message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.7.
 * Client key exchange message</a>
 *
 */
public class ClientKeyExchange extends Message {

    /**
     * Exchange keys
     */
    final byte[] exchange_keys;

    /**
     * Equals true if TLS1.0 protocol is used
     */
    boolean isTLS;

    /**
     * Equals true if key exchange algorithm is RSA
     */
    final boolean isRSA;

    /**
     * Creates outbound message
     * @param encrypted_pre_master_secret
     * @param isTLS
     */
    public ClientKeyExchange(byte[] encrypted_pre_master_secret, boolean isTLS) {
        this.exchange_keys = encrypted_pre_master_secret;
        length = this.exchange_keys.length;
        if (isTLS) {
            length += 2;
        }
        this.isTLS = isTLS;
        isRSA = true;
    }

    /**
     * Creates outbound message
     * @param dh_Yc
     */
    public ClientKeyExchange(BigInteger dh_Yc) {
        byte[] bb = dh_Yc.toByteArray();
        if (bb[0] == 0) {
            exchange_keys = new byte[bb.length-1];
            System.arraycopy(bb, 1, exchange_keys, 0, exchange_keys.length);
        } else {
            exchange_keys = bb;
        }
        length = exchange_keys.length +2;
        isRSA = false;
    }

    /**
     * Creates empty message
     *
     */
    public ClientKeyExchange() {
        exchange_keys = EmptyArray.BYTE;
        length = 0;
        isRSA = false;
    }

    /**
     * Creates inbound message
     * @param length
     * @param isTLS
     * @param isRSA
     * @throws IOException
     */
    public ClientKeyExchange(HandshakeIODataStream in, int length, boolean isTLS, boolean isRSA)
            throws IOException {
        this.isTLS = isTLS;
        this.isRSA = isRSA;
        if (length == 0) {
            this.length = 0;
            exchange_keys = EmptyArray.BYTE;
        } else {
            int size;
            if (isRSA && !isTLS) {// SSL3.0 RSA
                size = length;
                this.length = size;
            } else { // DH or TLSv1 RSA
                size = in.readUint16();
                this.length = 2 + size;
            }
            exchange_keys = new byte[size];
            Streams.readFully(in, exchange_keys);
            if (this.length != length) {
                fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect ClientKeyExchange");
            }
        }
    }

    /**
     * Sends message
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
        if (exchange_keys.length != 0) {
            if (!isRSA || isTLS) {// DH or TLSv1 RSA
                out.writeUint16(exchange_keys.length);
            }
            out.write(exchange_keys);
        }
    }

    /**
     * Returns message type
     * @return
     */
    @Override
    public int getType() {
        return Handshake.CLIENT_KEY_EXCHANGE;
    }

    /**
     * Returns true if the message is empty (in case of implicit DH Yc)
     * @return
     */
    public boolean isEmpty() {
        return (exchange_keys.length == 0);
    }
}
