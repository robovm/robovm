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
 * with the X.509 CRL. Its ASN notation is as follows
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  CertificateList  ::=  SEQUENCE  {
 *       tbsCertList          TBSCertList,
 *       signatureAlgorithm   AlgorithmIdentifier,
 *       signatureValue       BIT STRING  
 *  }
 * </pre>
 */
public class CertificateList {
    
    // the value of tbsCertList field of the structure
    private final TBSCertList tbsCertList; 
    // the value of signatureAlgorithm field of the structure
    private final AlgorithmIdentifier signatureAlgorithm;
    // the value of signatureValue field of the structure
    private final byte[] signatureValue;
    // the ASN.1 encoded form of CertList
    private byte[] encoding;

    /**
     * TODO
     * @param   tbsCertList: TBSCertList
     * @param   signatureAlgorithm: AlgorithmIdentifier
     * @param   signatureValue: byte[]
     */
    public CertificateList(TBSCertList tbsCertList, 
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue) {
        this.tbsCertList = tbsCertList;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signatureValue = new byte[signatureValue.length];
        System.arraycopy(signatureValue, 0, this.signatureValue, 0, 
                                                    signatureValue.length);
    }
    
    // 
    // TODO
    // @param   tbsCertList: TBSCertList
    // @param   signatureAlgorithm: AlgorithmIdentifier
    // @param   signatureValue: byte[]
    // @param   encoding:   byte[]
    // 
    private CertificateList(TBSCertList tbsCertList, 
                       AlgorithmIdentifier signatureAlgorithm,
                       byte[] signatureValue, byte[] encoding) {
        this(tbsCertList, signatureAlgorithm, signatureValue);
        this.encoding = encoding;
    }
    
    /**
     * Returns the value of tbsCertList field of the structure.
     * @return  tbsCertList
     */
    public TBSCertList getTbsCertList() {
        return tbsCertList;
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
        StringBuffer res = new StringBuffer();
        tbsCertList.dumpValue(res);
        res.append("\nSignature Value:\n"); //$NON-NLS-1$
        res.append(Array.toString(signatureValue, "")); //$NON-NLS-1$
        return res.toString();
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 TBSCertList value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = CertificateList.ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * X.509 CertList encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = 
        new ASN1Sequence(new ASN1Type[] 
                {TBSCertList.ASN1, AlgorithmIdentifier.ASN1, 
                    ASN1BitString.getInstance()}) {

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new CertificateList(
                    (TBSCertList) values[0],
                    (AlgorithmIdentifier) values[1], 
                    ((BitString) values[2]).bytes, // FIXME keep as BitString object
                    in.getEncoded()
                    );
        }

        protected void getValues(Object object, Object[] values) {

            CertificateList certlist = (CertificateList) object;

            values[0] = certlist.tbsCertList;
            values[1] = certlist.signatureAlgorithm;
            values[2] = new BitString(certlist.signatureValue, 0);
        }
    };
}

