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

import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.x509.AlgorithmIdentifier;

/**
 * The class implements the ASN.1 DER encoding and decoding of the PKCS#10
 * Certificate Signing Request (CSR). Its ASN notation is as follows:
 * 
 * CertificationRequest ::= SEQUENCE {
 *   certificationRequestInfo CertificationRequestInfo,
 *   signatureAlgorithm SignatureAlgorithmIdentifier,
 *   signature Signature 
 * }
 * 
 * SignatureAlgorithmIdentifier ::= AlgorithmIdentifier
 * 
 * Signature ::= BIT STRING
 */
public class CertificationRequest {
    
    // the value of certificationRequestInfo field of the structure
    private CertificationRequestInfo info;

    // the value of signatureAlgorithm field of the structure
    private AlgorithmIdentifier algId;

    // the value of signature field of the structure
    private byte[] signature;

    // the ASN.1 encoded form of CertificationRequest
    private byte[] encoding;

    public CertificationRequest(CertificationRequestInfo info,
            AlgorithmIdentifier algId, byte[] signature) {
        this.info = info;
        this.algId = algId;
        this.signature = new byte[signature.length];
        System.arraycopy(signature, 0, this.signature, 0, signature.length);
    }
    
    // private constructor with encoding given
    private CertificationRequest(CertificationRequestInfo info,
            AlgorithmIdentifier algId, byte[] signature, byte[] encoding) {
        this(info, algId, signature);
        this.encoding = encoding;
    }

    /**
     * @return Returns the algId.
     */
    public AlgorithmIdentifier getAlgId() {
        return algId;
    }

    /**
     * @return Returns the info.
     */
    public CertificationRequestInfo getInfo() {
        return info;
    }

    /**
     * @return Returns the signature.
     */
    public byte[] getSignature() {
        byte[] result = new byte[signature.length];
        System.arraycopy(signature, 0, result, 0, signature.length);
        return result;
    }

    /**
     * Returns ASN.1 encoded form of this CertificationRequest value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = CertificationRequest.ASN1.encode(this);
        }
        return encoding;
    }
    
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            CertificationRequestInfo.ASN1,  // info
            AlgorithmIdentifier.ASN1,       // signatureAlgorithm
            ASN1BitString.getInstance() })  // signature
    {

        public Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            return new CertificationRequest(
                    (CertificationRequestInfo) values[0],
                    (AlgorithmIdentifier) values[1],
                    ((BitString) values[2]).bytes, 
                    in.getEncoded());
        }

        protected void getValues(Object object, Object[] values) {
            CertificationRequest certReq = (CertificationRequest) object;

            values[0] = certReq.info;
            values[1] = certReq.algId;
            values[2] = new BitString(certReq.signature, 0);
        }
    };
}

