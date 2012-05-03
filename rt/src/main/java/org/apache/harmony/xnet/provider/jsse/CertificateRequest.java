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
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.security.auth.x500.X500Principal;
import libcore.io.Streams;

/**
 *
 * Represents certificate request message
 * @see <a href="http://www.ietf.org/rfc/rfc2246.txt">TLS 1.0 spec., 7.4.4.
 * Certificate request</a>
 */
public class CertificateRequest extends Message {

    /**
     * Requested certificate types
     */
    final byte[] certificate_types;

    /**
     * Certificate authorities
     */
    final X500Principal[] certificate_authorities;

    /**
     * Requested certificate types as Strings
     * ("RSA", "DSA", "DH_RSA" or "DH_DSA")
     */
    private String[] types;

    /**
     * Encoded form of certificate authorities
     */
    private byte[][] encoded_principals;

    /**
     * Creates outbound message
     *
     * @param certificate_types
     * @param accepted - array of certificate authority certificates
     */
    public CertificateRequest(byte[] certificate_types,
                              X509Certificate[] accepted) {

        if (accepted == null) {
            fatalAlert(AlertProtocol.INTERNAL_ERROR,
                    "CertificateRequest: array of certificate authority certificates is null");
        }
        this.certificate_types = certificate_types;

        int totalPrincipalsLength = 0;
        certificate_authorities = new X500Principal[accepted.length];
        encoded_principals = new byte[accepted.length][];
        for (int i = 0; i < accepted.length; i++) {
            certificate_authorities[i] = accepted[i].getIssuerX500Principal();
            encoded_principals[i] = certificate_authorities[i].getEncoded();
            totalPrincipalsLength += encoded_principals[i].length + 2;
        }

        length = 3 + certificate_types.length + totalPrincipalsLength;
    }

    /**
     * Creates inbound message
     *
     * @param in
     * @param length
     * @throws IOException
     */
    public CertificateRequest(HandshakeIODataStream in, int length) throws IOException {
        int size = in.readUint8();
        certificate_types = new byte[size];
        Streams.readFully(in, certificate_types);
        size = in.readUint16();
        int totalPrincipalsLength = 0;
        int principalLength = 0;
        ArrayList<X500Principal> principals = new ArrayList<X500Principal>();
        while (totalPrincipalsLength < size) {
            principalLength = in.readUint16(); // encoded X500Principal size
            principals.add(new X500Principal(in));
            totalPrincipalsLength += 2;
            totalPrincipalsLength += principalLength;
        }
        certificate_authorities = principals.toArray(new X500Principal[principals.size()]);
        this.length = 3 + certificate_types.length + totalPrincipalsLength;
        if (this.length != length) {
            fatalAlert(AlertProtocol.DECODE_ERROR, "DECODE ERROR: incorrect CertificateRequest");
        }
    }

    /**
     * Sends message
     *
     * @param out
     */
    @Override
    public void send(HandshakeIODataStream out) {

        out.writeUint8(certificate_types.length);
        for (int i = 0; i < certificate_types.length; i++) {
            out.write(certificate_types[i]);
        }
        int authoritiesLength = 0;
        for (int i = 0; i < certificate_authorities.length; i++) {
            authoritiesLength += encoded_principals[i].length +2;
        }
        out.writeUint16(authoritiesLength);
        for (int i = 0; i < certificate_authorities.length; i++) {
            out.writeUint16(encoded_principals[i].length);
            out.write(encoded_principals[i]);
        }
    }

    /**
     * Returns message type
     *
     * @return
     */
    @Override
    public int getType() {
        return Handshake.CERTIFICATE_REQUEST;
    }

    /**
     * Returns requested certificate types as array of strings
     */
    public String[] getTypesAsString() {
        if (types == null) {
            types = new String[certificate_types.length];
            for (int i = 0; i < types.length; i++) {
                String type = CipherSuite.getClientKeyType(certificate_types[i]);
                if (type == null) {
                    fatalAlert(AlertProtocol.DECODE_ERROR,
                            "DECODE ERROR: incorrect CertificateRequest");
                }
                types[i] = type;
            }
        }
        return types;
    }

}
