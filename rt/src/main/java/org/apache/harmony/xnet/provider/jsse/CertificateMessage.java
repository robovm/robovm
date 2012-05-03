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
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * Represents server/client certificate message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS
 * 1.0 spec., 7.4.2. Server certificate; 7.4.6. Client certificate</a>
 *
 */
public class CertificateMessage extends Message {

    /**
     * Certificates
     */
    X509Certificate[] certs;

    /**
     * Certificates in encoded form
     */
    byte[][] encoded_certs;

    /**
     * Creates inbound message
     *
     * @param in
     * @param length
     * @throws IOException
     */
    public CertificateMessage(HandshakeIODataStream in, int length) throws IOException {
        int l = in.readUint24(); // total_length
        if (l == 0) {  // message contais no certificates
            if (length != 3) { // no more bytes after total_length
                fatalAlert(AlertProtocol.DECODE_ERROR,
                        "DECODE ERROR: incorrect CertificateMessage");
            }
            certs = new X509Certificate[0];
            encoded_certs = new byte[0][0];
            this.length = 3;
            return;
        }
        CertificateFactory cf;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR", e);
            return;
        }
        ArrayList<X509Certificate> certsList = new ArrayList<X509Certificate>();
        int size = 0;
        int enc_size = 0;
        while (l > 0) {
            size = in.readUint24();
            l -= 3;
            try {
                certsList.add((X509Certificate) cf.generateCertificate(in));
            } catch (CertificateException e) {
                fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR", e);
            }
            l -= size;
            enc_size += size;
        }
        certs = certsList.toArray(new X509Certificate[certsList.size()]);
        this.length = 3 + 3 * certs.length + enc_size;
        if (this.length != length) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect CertificateMessage");
        }
    }

    /**
     * Creates outbound message
     *
     * @param certs
     */
    public CertificateMessage(X509Certificate[] certs) {
        if (certs == null) {
            this.certs = new X509Certificate[0];
            encoded_certs = new byte[0][0];
            length = 3;
            return;
        }
        this.certs = certs;
        if (encoded_certs == null) {
            encoded_certs = new byte[certs.length][];
            for (int i = 0; i < certs.length; i++) {
                try {
                    encoded_certs[i] = certs[i].getEncoded();
                } catch (CertificateEncodingException e) {
                    fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR",
                            e);
                }
            }
        }
        length = 3 + 3 * encoded_certs.length;
        for (int i = 0; i < encoded_certs.length; i++) {
            length += encoded_certs[i].length;
        }
    }

    /**
     * Sends message
     *
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {

        int total_length = 0;
        if (encoded_certs == null) {
            encoded_certs = new byte[certs.length][];
            for (int i = 0; i < certs.length; i++) {
                try {
                    encoded_certs[i] = certs[i].getEncoded();
                } catch (CertificateEncodingException e) {
                    fatalAlert(AlertProtocol.INTERNAL_ERROR, "INTERNAL ERROR",
                            e);
                }
            }
        }
        total_length = 3 * encoded_certs.length;
        for (int i = 0; i < encoded_certs.length; i++) {
            total_length += encoded_certs[i].length;
        }
        out.writeUint24(total_length);
        for (int i = 0; i < encoded_certs.length; i++) {
            out.writeUint24(encoded_certs[i].length);
            out.write(encoded_certs[i]);
        }

    }

    public String getAuthType() {
        return certs[0].getPublicKey().getAlgorithm();
    }

    /**
     * Returns message type
     *
     * @return
     */
    @Override
    public int getType() {
        return Handshake.CERTIFICATE;
    }

}
