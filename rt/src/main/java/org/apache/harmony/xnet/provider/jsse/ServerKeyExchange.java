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
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 *
 * Represents server key exchange message.
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.3.
 * Server key exchange message.</a>
 *
 */
public class ServerKeyExchange extends Message {

                           //          ServerRSAParams        ServerDHParams
    final BigInteger par1; //            rsa_modulus               dh_p
    final byte[] bytes1;

    final BigInteger par2; //            rsa_exponent              dh_g
    final byte[] bytes2;

    final BigInteger par3; //                                      dh_Ys
    final byte[] bytes3;

    /**
     * Signature
     */
    final byte[] hash;

    private RSAPublicKey key;

    /**
     * Creates outbound message
     * @param par1 rsa_modulus or dh_p
     * @param par2 rsa_exponent or dh_g
     * @param par3 dh_Ys for ServerDHParams; should be null for ServerRSAParams
     * @param hash should be null for anonymous SignatureAlgorithm
     */
    public ServerKeyExchange(BigInteger par1, BigInteger par2, BigInteger par3,
            byte[] hash) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.hash = hash;

        bytes1 = toUnsignedByteArray(this.par1);

        bytes2 = toUnsignedByteArray(this.par2);

        length = 4 + bytes1.length + bytes2.length;
        if (hash != null) {
            length += 2 + hash.length;
        }
        if (par3 == null) {
            bytes3 = null;
            return;
        }
        bytes3 = toUnsignedByteArray(this.par3);
        length += 2 + bytes3.length;
    }

    /**
     * Remove first byte if 0. Needed because BigInteger.toByteArray() sometimes
     * returns a zero prefix.
     */
    public static byte[] toUnsignedByteArray(BigInteger bi) {
        if (bi == null) {
            return null;
        }
        byte[] bb = bi.toByteArray();
        // bb is not null, and has at least 1 byte - ZERO is represented as [0]
        if (bb[0] == 0) {
            byte[] noZero = new byte[bb.length - 1];
            System.arraycopy(bb, 1, noZero, 0, noZero.length);
            return noZero;
        } else {
            return bb;
        }
    }

    /**
     * Creates inbound message
     * @param in
     * @param length
     * @param keyExchange
     * @throws IOException
     */
    public ServerKeyExchange(HandshakeIODataStream in, int length,
            int keyExchange) throws IOException {

        int size = in.readUint16();
        bytes1 = in.read(size);
        par1 = new BigInteger(1, bytes1);
        this.length = 2 + bytes1.length;
        size = in.readUint16();
        bytes2 = in.read(size);
        par2 = new BigInteger(1, bytes2);
        this.length += 2 + bytes2.length;
        if (keyExchange != CipherSuite.KEY_EXCHANGE_RSA_EXPORT) {
            size = in.readUint16();
            bytes3 = in.read(size);
            par3 = new BigInteger(1, bytes3);
            this.length += 2 + bytes3.length;
        } else {
            par3 = null;
            bytes3 = null;
        }
        if (keyExchange != CipherSuite.KEY_EXCHANGE_DH_anon_EXPORT
                && keyExchange != CipherSuite.KEY_EXCHANGE_DH_anon) {
            size = in.readUint16();
            hash = in.read(size);
            this.length += 2 + hash.length;
        } else {
            hash = null;
        }
        if (this.length != length) {
            fatalAlert(AlertProtocol.DECODE_ERROR,
                    "DECODE ERROR: incorrect ServerKeyExchange");
        }
    }

    /**
     * Sends message
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
        out.writeUint16(bytes1.length);
        out.write(bytes1);
        out.writeUint16(bytes2.length);
        out.write(bytes2);
        if (bytes3 != null) {
            out.writeUint16(bytes3.length);
            out.write(bytes3);
        }
        if (hash != null) {
            out.writeUint16(hash.length);
            out.write(hash);
        }
    }

    /**
     * Returns RSAPublicKey generated using ServerRSAParams
     * (rsa_modulus and rsa_exponent).
     *
     * @return
     */
    public RSAPublicKey getRSAPublicKey() {
        if (key != null) {
            return key;
        }
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            key = (RSAPublicKey) kf.generatePublic(new RSAPublicKeySpec(par1,
                    par2));
        } catch (Exception e) {
            return null;
        }
        return key;
    }

    /**
     * Returns message type
     * @return
     */
    @Override
    public int getType() {
        return Handshake.SERVER_KEY_EXCHANGE;
    }

}
