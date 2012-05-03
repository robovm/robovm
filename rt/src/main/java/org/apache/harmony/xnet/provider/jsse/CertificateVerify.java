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
 * Represents certificate verify message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.8.
 * Certificate verify</a>
 */
public class CertificateVerify extends Message {

    /**
     * Signature
     */
    byte[] signedHash;

    /**
     * Creates outbound message
     *
     * @param hash
     */
    public CertificateVerify(byte[] hash) {
        if (hash == null || hash.length == 0) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR,
                    "INTERNAL ERROR: incorrect certificate verify hash");
        }
        this.signedHash = hash;
        length = hash.length + 2;
    }

    /**
     * Creates inbound message
     *
     * @param in
     * @param length
     * @throws IOException
     */
    public CertificateVerify(HandshakeIODataStream in, int length)
            throws IOException {
        if (length == 0) {
            fatalAlert(AlertProtocol.DECODE_ERROR,
                    "DECODE ERROR: incorrect CertificateVerify");
        } else {
            if (in.readUint16() != length - 2) {
                fatalAlert(AlertProtocol.DECODE_ERROR,
                        "DECODE ERROR: incorrect CertificateVerify");
            }
            signedHash = in.read(length -2);
        }
        this.length = length;
    }

    /**
     * Sends message
     *
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {
        if (signedHash.length != 0) {
            out.writeUint16(signedHash.length);
            out.write(signedHash);
        }
    }

    /**
     * Returns message type
     *
     * @return
     */
    @Override
    public int getType() {
        return Handshake.CERTIFICATE_VERIFY;
    }
}
