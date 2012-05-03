/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.security.pkcs10;

import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.x501.AttributeTypeAndValue;
import org.apache.harmony.security.x501.Name;
import org.apache.harmony.security.x509.SubjectPublicKeyInfo;

/**
 * CertificationRequestInfo ::= SEQUENCE {
 *   version Version,
 *   subject Name,
 *   subjectPublicKeyInfo SubjectPublicKeyInfo,
 *   attributes [0] IMPLICIT Attributes }
 *
 * Version ::= INTEGER
 *
 * Attributes ::= SET OF Attribute
 */
public final class CertificationRequestInfo {
    private final int version;

    /** the value of subject field of the structure */
    private final Name subject;

    /** the value of subjectPublicKeyInfo field of the structure */
    private final SubjectPublicKeyInfo subjectPublicKeyInfo;

    /** the value of attributes field of the structure */
    private final List<?> attributes;

    /** the ASN.1 encoded form of CertificationRequestInfo */
    private byte[] encoding;

    private CertificationRequestInfo(int version, Name subject,
            SubjectPublicKeyInfo subjectPublicKeyInfo, List<?> attributes, byte [] encoding) {
        this.version = version;
        this.subject = subject;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.attributes = attributes;
        this.encoding = encoding;
    }

    public Name getSubject() {
        return subject;
    }

    public int getVersion() {
        return version;
    }

    /**
     * Returns ASN.1 encoded form of this CertificationRequestInfo.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    @Override public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("-- CertificationRequestInfo:");
        res.append("\n version: ");
        res.append(version);
        res.append("\n subject: ");
        res.append(subject.getName(X500Principal.CANONICAL));
        res.append("\n subjectPublicKeyInfo: ");
        res.append("\n\t algorithm: ");
        res.append(subjectPublicKeyInfo.getAlgorithmIdentifier().getAlgorithm());
        res.append("\n\t public key: ").append(subjectPublicKeyInfo.getPublicKey());
        res.append("\n attributes: ");
        if (attributes != null) {
            res.append(attributes.toString());
        } else {
            res.append("none");
        }
        res.append("\n-- CertificationRequestInfo End\n");
        return res.toString();
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(),              // version
            Name.ASN1,                              // subject
            SubjectPublicKeyInfo.ASN1,              // subjectPublicKeyInfo
            new ASN1Implicit(0, new ASN1SetOf(
                    AttributeTypeAndValue.ASN1))    // attributes
            }) {

        @Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new CertificationRequestInfo(
                    ASN1Integer.toIntValue(values[0]),
                    (Name) values[1],
                    (SubjectPublicKeyInfo) values[2],
                    (List<?>) values[3],
                    in.getEncoded());
        }

        @Override protected void getValues(Object object, Object[] values) {
            CertificationRequestInfo certReqInfo = (CertificationRequestInfo) object;
            values[0] = ASN1Integer.fromIntValue(certReqInfo.version);
            values[1] = certReqInfo.subject;
            values[2] = certReqInfo.subjectPublicKeyInfo;
            values[3] = certReqInfo.attributes;
        }
    };
}

