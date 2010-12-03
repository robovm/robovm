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

/**
* @author Alexander Y. Kleymenov
*/

package org.apache.harmony.security.x509;

import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.utils.Array;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the X.509 certificate. Its ASN notation is as follows
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  Certificate  ::=  SEQUENCE  {
 *      tbsCertificate       TBSCertificate,
 *      signatureAlgorithm   AlgorithmIdentifier,
 *      signatureValue       BIT STRING  
 *  }
 * </pre>
 */
public class Certificate {

    // the value of tbsCertificate field of the structure
    private final TBSCertificate tbsCertificate; 
    // the value of signatureAlgorithm field of the structure
    private final AlgorithmIdentifier signatureAlgorithm;
    // the value of signatureValue field of the structure
    private final byte[] signatureValue;
    // the ASN.1 encoded form of Certificate
    private byte[] encoding;

    /**
     * TODO
     * @param   tbsCertificate: TBSCertificate
     * @param   signatureAlgorithm: AlgorithmIdentifier
     * @param   signatureValue: byte[]
     */
    public Certificate(TBSCertificate tbsCertificate, 
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue) {
        this.tbsCertificate = tbsCertificate;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signatureValue = new byte[signatureValue.length];
        System.arraycopy(signatureValue, 0, this.signatureValue, 0, 
                                                    signatureValue.length);
    }
    
    // 
    // TODO
    // @param   tbsCertificate: TBSCertificate
    // @param   signatureAlgorithm: AlgorithmIdentifier
    // @param   signatureValue: byte[]
    // @param   encoding:   byte[]
    // 
    private Certificate(TBSCertificate tbsCertificate, 
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue, byte[] encoding) {
        this(tbsCertificate, signatureAlgorithm, signatureValue);
        this.encoding = encoding;
    }
    
    /**
     * Returns the value of tbsCertificate field of the structure.
     * @return  tbsCertificate
     */
    public TBSCertificate getTbsCertificate() {
        return tbsCertificate;
    }

    /**
     * Returns the value of signatureAlgorithm field of the structure.
     * @return  signatureAlgorithm
     */
    public AlgorithmIdentifier getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * Returns the value of signatureValue field of the structure.
     * @return  signatureValue
     */
    public byte[] getSignatureValue() {
        byte[] result = new byte[signatureValue.length];
        System.arraycopy(signatureValue, 0, result, 0, signatureValue.length);
        return result;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("X.509 Certificate:\n[\n"); //$NON-NLS-1$
        tbsCertificate.dumpValue(buffer);
        buffer.append("\n  Algorithm: ["); //$NON-NLS-1$
        signatureAlgorithm.dumpValue(buffer);
        buffer.append(']');
        buffer.append("\n  Signature Value:\n"); //$NON-NLS-1$
        buffer.append(Array.toString(signatureValue, "")); //$NON-NLS-1$
        buffer.append(']');
        return buffer.toString();
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 TBSCertificate value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = Certificate.ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * X.509 Certificate encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = 
        new ASN1Sequence(new ASN1Type[] 
                {TBSCertificate.ASN1, AlgorithmIdentifier.ASN1, ASN1BitString.getInstance()}) {

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new Certificate(
                    (TBSCertificate) values[0],
                    (AlgorithmIdentifier) values[1], 
                    ((BitString) values[2]).bytes, // FIXME keep as BitString object
                    in.getEncoded()
                    );
        }

        protected void getValues(Object object, Object[] values) {

            Certificate cert = (Certificate) object;

            values[0] = cert.tbsCertificate;
            values[1] = cert.signatureAlgorithm;
            values[2] = new BitString(cert.signatureValue, 0);
        }
    };
}

