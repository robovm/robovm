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
import java.util.Date;
import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1GeneralizedTime;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.GeneralName;

/**
 * As defined in Time-Stamp Protocol (TSP)
 * (http://www.ietf.org/rfc/rfc3161.txt)
 *
 * TSTInfo ::= SEQUENCE  {
 *    version                      INTEGER  { v1(1) },
 *    policy                       TSAPolicyId,
 *    messageImprint               MessageImprint,
 *      -- MUST have the same value as the similar field in
 *      -- TimeStampReq
 *    serialNumber                 INTEGER,
 *     -- Time-Stamping users MUST be ready to accommodate integers
 *     -- up to 160 bits.
 *    genTime                      GeneralizedTime,
 *    accuracy                     Accuracy                 OPTIONAL,
 *    ordering                     BOOLEAN             DEFAULT FALSE,
 *    nonce                        INTEGER                  OPTIONAL,
 *      -- MUST be present if the similar field was present
 *      -- in TimeStampReq.  In that case it MUST have the same value.
 *    tsa                          [0] GeneralName          OPTIONAL,
 *    extensions                   [1] IMPLICIT Extensions   OPTIONAL
 * }
 *
 * TSAPolicyId ::= OBJECT IDENTIFIER
 *
 * "tsa [0] GeneralName OPTIONAL" is EXPLICIT and the word EXPLICIT is omitted.
 */
public class TSTInfo {

    private final int version;

    private final String policy;

    private final MessageImprint messageImprint;

    private final BigInteger serialNumber;

    private final Date genTime;

    private final int [] accuracy;

    private final Boolean ordering;

    private final BigInteger nonce;

    private final GeneralName tsa;

    private final Extensions extensions;

    public TSTInfo(int version, String policy, MessageImprint messageImprint,
            BigInteger serialNumber, Date genTime, int[] accuracy,
            Boolean ordering, BigInteger nonce, GeneralName tsa,
            Extensions extensions) {
        this.version = version;
        this.policy = policy;
        this.messageImprint = messageImprint;
        this.serialNumber = serialNumber;
        this.genTime = genTime;
        this.accuracy = accuracy;
        this.ordering = ordering;
        this.nonce = nonce;
        this.tsa = tsa;
        this.extensions = extensions;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("-- TSTInfo:");
        res.append("\nversion:  ");
        res.append(version);
        res.append("\npolicy:  ");
        res.append(policy);
        res.append("\nmessageImprint:  ");
        res.append(messageImprint);
        res.append("\nserialNumber:  ");
        res.append(serialNumber);
        res.append("\ngenTime:  ");
        res.append(genTime);
        res.append("\naccuracy:  ");
        if (accuracy != null) {
            res.append(accuracy[0] + " sec, " + accuracy[1] + " millis, "
                    + accuracy[2] + " micros");
        }
        res.append("\nordering:  ");
        res.append(ordering);
        res.append("\nnonce:  ");
        res.append(nonce);
        res.append("\ntsa:  ");
        res.append(tsa);
        res.append("\nextensions:  ");
        res.append(extensions);
        res.append("\n-- TSTInfo End\n");
        return res.toString();
    }

    /**
     * @return Returns the accuracy.
     */
    public int[] getAccuracy() {
        return accuracy;
    }

    /**
     * @return Returns the extensions.
     */
    public Extensions getExtensions() {
        return extensions;
    }

    /**
     * @return Returns the genTime.
     */
    public Date getGenTime() {
        return genTime;
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
     * @return Returns the ordering.
     */
    public Boolean getOrdering() {
        return ordering;
    }

    /**
     * @return Returns the policy.
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * @return Returns the serialNumber.
     */
    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    /**
     * @return Returns the tsa.
     */
    public GeneralName getTsa() {
        return tsa;
    }

    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }

    /**
         Accuracy ::= SEQUENCE {
                seconds        INTEGER           OPTIONAL,
                millis     [0] INTEGER  (1..999) OPTIONAL,
                micros     [1] INTEGER  (1..999) OPTIONAL  }
     */
    public static final ASN1Sequence ACCURACY
            = new ASN1Sequence(new ASN1Type[] {
                    ASN1Integer.getInstance(),
                    ASN1Integer.getInstance(),
                    ASN1Integer.getInstance()
            }) {
        {
            setOptional(0);
            setOptional(1);
            setOptional(2);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            int [] accuracy = new int [3];
            for (int i = 0; i < 3; i++) {
                if (values[i] != null) {
                    accuracy[i] = ASN1Integer.toIntValue(values[i]);
                    if (i > 0 && (accuracy[i] < 0 || accuracy[i] > 999)) {
                        throw new RuntimeException("Time-stamp accuracy value is incorrect: " + accuracy[i]);
                    }
                }
            }
            return accuracy;
        }

        protected void getValues(Object object, Object[] values) {
            int [] accuracy = (int []) object;
            for (int i = 0; i < 3; i++) {
                if (i > 0 && (accuracy[i] < 0 || accuracy[i] > 999)) {
                    throw new RuntimeException("Time-stamp accuracy value is incorrect: " + accuracy[i]);
                }
                values[i] = BigInteger.valueOf(accuracy[i]).toByteArray();
            }
        }
    };

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(),              // version
            ASN1Oid.getInstance(),                  // policy
            MessageImprint.ASN1,                    // messageImprint
            ASN1Integer.getInstance(),              // serialNumber
            ASN1GeneralizedTime.getInstance(),      // genTime
            ACCURACY,                               // accuracy
            ASN1Boolean.getInstance(),              // ordering
            ASN1Integer.getInstance(),              // nonce
            new ASN1Explicit(0, GeneralName.ASN1),  // tsa
            new ASN1Implicit(1, Extensions.ASN1) }) {// extensions
        {
            setOptional(5);
            setDefault(Boolean.FALSE, 6);
            setOptional(7);
            setOptional(8);
            setOptional(9);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            BigInteger nonce = (values[7] == null) ? null : new BigInteger(
                    (byte[]) values[7]);

            return new TSTInfo(
                    ASN1Integer.toIntValue(values[0]),
                    ObjectIdentifier.toString((int[]) values[1]),
                    (MessageImprint) values[2],
                    new BigInteger((byte[]) values[3]),
                    (Date) values[4],
                    (int []) values[5],
                    (Boolean) values[6],
                    nonce,
                    (GeneralName) values[8],
                    (Extensions) values[9]);
        }

        protected void getValues(Object object, Object[] values) {
            TSTInfo info = (TSTInfo) object;

            values[0] = ASN1Integer.fromIntValue(info.version);
            values[1] = ObjectIdentifier.toIntArray(info.policy);
            values[2] = info.messageImprint;
            values[3] = info.serialNumber.toByteArray();
            values[4] = info.genTime;
            values[5] = info.accuracy;
            values[6] = info.ordering;
            values[7] = (info.nonce == null) ? null : info.nonce.toByteArray();
            values[8] = info.tsa;
            values[9] = info.extensions;
        }
    };
}
