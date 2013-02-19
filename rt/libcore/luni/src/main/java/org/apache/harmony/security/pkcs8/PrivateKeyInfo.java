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


package org.apache.harmony.security.pkcs8;

import java.util.List;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.x501.AttributeTypeAndValue;
import org.apache.harmony.security.x509.AlgorithmIdentifier;

/**
 * The class implements the ASN.1 DER encoding and decoding of the PKCS#8
 * PrivateKeyInfo having the following ASN.1 notation:
 *
 *  PrivateKeyInfo ::= SEQUENCE {
 *      version Version,
 *      privateKeyAlgorithm PrivateKeyAlgorithmIdentifier,
 *      privateKey PrivateKey,
 *      attributes [0] IMPLICIT Attributes OPTIONAL }
 *
 *  Version ::= INTEGER
 *
 *  PrivateKeyAlgorithmIdentifier ::= AlgorithmIdentifier
 *
 *  PrivateKey ::= OCTET STRING
 *
 *  Attributes ::= SET OF Attribute
 */
public final class PrivateKeyInfo {
    private final int version;
    private final AlgorithmIdentifier privateKeyAlgorithm;
    private final byte[] privateKey;
    private final List<?> attributes;
    private byte[] encoding;

    public PrivateKeyInfo(int version, AlgorithmIdentifier privateKeyAlgorithm,
            byte[] privateKey, List attributes) {
        this.version = version;
        this.privateKeyAlgorithm = privateKeyAlgorithm;
        this.privateKey = privateKey;
        this.attributes = attributes;
    }

    private PrivateKeyInfo(int version,
            AlgorithmIdentifier privateKeyAlgorithm, byte[] privateKey,
            List attributes, byte[] encoding) {
        this(version, privateKeyAlgorithm, privateKey, attributes);
        this.encoding = encoding;
    }

    public int getVersion() {
        return version;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return privateKeyAlgorithm;
    }

    public List getAttributes() {
        return attributes;
    }

    /**
     * Returns the OCTET STRING.
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Returns ASN.1 encoded form of this PrivateKeyInfo.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {

    ASN1Integer.getInstance(), // version
            AlgorithmIdentifier.ASN1, // AlgorithmIdentifier
            ASN1OctetString.getInstance(), // privateKey

            new ASN1Implicit(0, new ASN1SetOf(AttributeTypeAndValue.ASN1)) // attributes
            }) {

        {
            setOptional(3); // attributes are OPTIONAL
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new PrivateKeyInfo(ASN1Integer.toIntValue(values[0]),
                    (AlgorithmIdentifier) values[1], (byte[]) values[2],
                    (List) values[3], in.getEncoded());
        }

        protected void getValues(Object object, Object[] values) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) object;
            values[0] = ASN1Integer.fromIntValue(privateKeyInfo.version);
            values[1] = privateKeyInfo.privateKeyAlgorithm;
            values[2] = privateKeyInfo.privateKey;
            values[3] = privateKeyInfo.attributes;
        }
    };
}
