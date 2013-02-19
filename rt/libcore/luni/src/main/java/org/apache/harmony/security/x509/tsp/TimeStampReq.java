/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.harmony.security.x509.tsp;

import java.math.BigInteger;
import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.x509.Extensions;

/**
 * As defined in Time-Stamp Protocol (TSP)
 * (http://www.ietf.org/rfc/rfc3161.txt)
 *
 * TimeStampReq ::= SEQUENCE  {
 *    version                      INTEGER  { v1(1) },
 *    messageImprint               MessageImprint,
 *      --a hash algorithm OID and the hash value of the data to be
 *      --time-stamped
 *    reqPolicy             TSAPolicyId              OPTIONAL,
 *    nonce                 INTEGER                  OPTIONAL,
 *    certReq               BOOLEAN                  DEFAULT FALSE,
 *    extensions            [0] IMPLICIT Extensions  OPTIONAL
 *  }
 *
 *  TSAPolicyId ::= OBJECT IDENTIFIER
 */
public class TimeStampReq {
    private final int version;

    private final MessageImprint messageImprint;

    private final String reqPolicy;

    private final BigInteger nonce;

    private final Boolean certReq;

    private final Extensions extensions;

    private byte [] encoding;

    public TimeStampReq(int version, MessageImprint messageImprint,
            String reqPolicy, BigInteger nonce, Boolean certReq,
            Extensions extensions) {
        this.version = version;
        this.messageImprint = messageImprint;
        this.reqPolicy = reqPolicy;
        this.nonce = nonce;
        this.certReq = certReq;
        this.extensions = extensions;
    }

    private TimeStampReq(int version, MessageImprint messageImprint,
            String reqPolicy, BigInteger nonce, Boolean certReq,
            Extensions extensions, byte [] encoding) {
        this (version, messageImprint, reqPolicy, nonce, certReq, extensions);
        this.encoding = encoding;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("-- TimeStampReq:");
        res.append("\nversion : ");
        res.append(version);
        res.append("\nmessageImprint:  ");
        res.append(messageImprint);
        res.append("\nreqPolicy:  ");
        res.append(reqPolicy);
        res.append("\nnonce:  ");
        res.append(nonce);
        res.append("\ncertReq:  ");
        res.append(certReq);
        res.append("\nextensions:  ");
        res.append(extensions);
        res.append("\n-- TimeStampReq End\n");
        return res.toString();
    }

    /**
     * Returns ASN.1 encoded form of this TimeStampReq.
     * @return a byte array containing ASN.1 encoded form.
     */
    public byte [] getEncoded(){
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * @return Returns the certReq.
     */
    public Boolean getCertReq() {
        return certReq;
    }

    /**
     * @return Returns the extensions.
     */
    public Extensions getExtensions() {
        return extensions;
    }

    /**
     * @return Returns the messageImprint.
     */
    public MessageImprint getMessageImprint() {
        return messageImprint;
    }

    /**
     * @return Returns the nonce.
     */
    public BigInteger getNonce() {
        return nonce;
    }

    /**
     * @return Returns the reqPolicy.
     */
    public String getReqPolicy() {
        return reqPolicy;
    }

    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(),              // version
            MessageImprint.ASN1,                    // messageImprint
            ASN1Oid.getInstance(),                  // reqPolicy
            ASN1Integer.getInstance(),              // nonce
            ASN1Boolean.getInstance(),              // certReq
            new ASN1Implicit(0, Extensions.ASN1)}) {// extensions

        {
            setDefault(Boolean.FALSE, 4);
            setOptional(2);
            setOptional(3);
            setOptional(5);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            String objID = (values[2] == null) ? null : ObjectIdentifier
                    .toString((int[]) values[2]);
            BigInteger nonce = (values[3] == null) ? null : new BigInteger(
                    (byte[]) values[3]);

            if (values[5] == null) {
                return new TimeStampReq(
                        ASN1Integer.toIntValue(values[0]),
                        (MessageImprint) values[1],
                        objID,
                        nonce,
                        (Boolean) values[4],
                        null,
                        in.getEncoded()
                   );
            } else {
                return new TimeStampReq(
                        ASN1Integer.toIntValue(values[0]),
                        (MessageImprint) values[1],
                        objID,
                        nonce,
                        (Boolean) values[4],
                        (Extensions) values[5],
                        in.getEncoded()
                   );
            }
        }

        protected void getValues(Object object, Object[] values) {
            TimeStampReq req = (TimeStampReq) object;
            values[0] = ASN1Integer.fromIntValue(req.version);
            values[1] = req.messageImprint;
            values[2] = (req.reqPolicy == null) ? null : ObjectIdentifier
                    .toIntArray(req.reqPolicy);
            values[3] = (req.nonce == null) ? null : req.nonce.toByteArray();
            values[4] = (req.certReq == null) ? Boolean.FALSE : req.certReq;
            values[5] = req.extensions;
        }
    };

}

