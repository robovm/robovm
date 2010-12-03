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

import java.math.BigInteger;
import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.x501.Name;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with TBSCertificate structure which is the part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  TBSCertificate  ::=  SEQUENCE  {
 *       version         [0]  EXPLICIT Version DEFAULT v1,
 *       serialNumber         CertificateSerialNumber,
 *       signature            AlgorithmIdentifier,
 *       issuer               Name,
 *       validity             Validity,
 *       subject              Name,
 *       subjectPublicKeyInfo SubjectPublicKeyInfo,
 *       issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL,
 *                            -- If present, version MUST be v2 or v3
 *       subjectUniqueID [2]  IMPLICIT UniqueIdentifier OPTIONAL,
 *                            -- If present, version MUST be v2 or v3
 *       extensions      [3]  EXPLICIT Extensions OPTIONAL
 *                            -- If present, version MUST be v3
 *  }
 * </pre>
 */
public class TBSCertificate {

    // the value of version field of the structure
    private final int version; 
    // the value of serialNumber field of the structure
    private final BigInteger serialNumber;
    // the value of signature field of the structure
    private final AlgorithmIdentifier signature; 
    // the value of issuer field of the structure
    private final Name issuer;
    // the value of validity field of the structure
    private final Validity validity;
    // the value of subject field of the structure
    private final Name subject;
    // the value of subjectPublicKeyInfo field of the structure
    private final SubjectPublicKeyInfo subjectPublicKeyInfo;
    // the value of issuerUniqueID field of the structure
    private final boolean[] issuerUniqueID;
    // the value of subjectUniqueID field of the structure
    private final boolean[] subjectUniqueID;
    // the value of extensions field of the structure
    private final Extensions extensions;
    // the ASN.1 encoded form of TBSCertificate
    byte[] encoding;

    /**
     * Constructs the instance of TBSCertificate without optional
     * fields (issuerUniqueID, subjectUniqueID, extensions)
     * @param   version :   int
     * @param   serialNumber    :   BigInteger
     * @param   signature   :   AlgorithmIdentifier
     * @param   issuer  :   Name
     * @param   validity    :   Validity
     * @param   subject :   Name
     * @param   subjectPublicKeyInfo    :   SubjectPublicKeyInfo
     */
    public TBSCertificate(int version, BigInteger serialNumber, 
                          AlgorithmIdentifier signature, Name issuer,
                          Validity validity, Name subject, 
                          SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this(version, serialNumber, signature, issuer, validity, subject, 
             subjectPublicKeyInfo, null, null, null);
    }

    /**
     * TODO
     * @param   version:    int
     * @param   serialNumber:   BigInteger
     * @param   signature:  AlgorithmIdentifier
     * @param   issuer: Name
     * @param   validity:   Validity
     * @param   subject:    Name
     * @param   subjectPublicKeyInfo:   SubjectPublicKeyInfo
     * @param   issuerUniqueID: byte[]
     * @param   subjectUniqueID:    byte[]
     * @param   extensions: Extensions
     */
    public TBSCertificate(int version, BigInteger serialNumber, 
                          AlgorithmIdentifier signature, Name issuer,
                          Validity validity, Name subject, 
                          SubjectPublicKeyInfo subjectPublicKeyInfo,
                          boolean[] issuerUniqueID, boolean[] subjectUniqueID,
                          Extensions extensions) {
        this.version = version; 
        this.serialNumber = serialNumber;
        this.signature = signature; 
        this.issuer = issuer;
        this.validity = validity;
        this.subject = subject;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.issuerUniqueID = issuerUniqueID;
        this.subjectUniqueID = subjectUniqueID;
        this.extensions = extensions;
    }
        
    // 
    // TODO
    // @param   version:    int
    // @param   serialNumber:   BigInteger
    // @param   signature:  AlgorithmIdentifier
    // @param   issuer: Name
    // @param   validity:   Validity
    // @param   subject:    Name
    // @param   subjectPublicKeyInfo:   SubjectPublicKeyInfo
    // @param   issuerUniqueID: byte[]
    // @param   subjectUniqueID:    byte[]
    // @param   extensions: Extensions
    // @param   encoding:   byte[]
    // 
    private TBSCertificate(int version, BigInteger serialNumber, 
                          AlgorithmIdentifier signature, Name issuer,
                          Validity validity, Name subject, 
                          SubjectPublicKeyInfo subjectPublicKeyInfo,
                          boolean[] issuerUniqueID, boolean[] subjectUniqueID,
                          Extensions extensions, byte[] encoding) {
        this(version, serialNumber, signature, issuer, validity, subject, 
             subjectPublicKeyInfo, issuerUniqueID, subjectUniqueID, extensions);
        this.encoding = encoding;
    }
        
    /**
     * Returns the value of version field of the structure.
     * @return  version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Returns the value of serialNumber field of the structure.
     * @return  serialNumber
     */
    public BigInteger getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * Returns the value of signature field of the structure.
     * @return  signature
     */
    public AlgorithmIdentifier getSignature() {
        return signature;
    }
    
    /**
     * Returns the value of issuer field of the structure.
     * @return  issuer
     */
    public Name getIssuer() {
        return issuer;
    }
    
    /**
     * Returns the value of validity field of the structure.
     * @return  validity
     */
    public Validity getValidity() {
        return validity;
    }
    
    /**
     * Returns the value of subject field of the structure.
     * @return  subject
     */
    public Name getSubject() {
        return subject;
    }
    
    /**
     * Returns the value of subjectPublicKeyInfo field of the structure.
     * @return  subjectPublicKeyInfo
     */
    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return subjectPublicKeyInfo;
    }
    
    /**
     * Returns the value of issuerUniqueID field of the structure.
     * @return  issuerUniqueID
     */
    public boolean[] getIssuerUniqueID() {
        return issuerUniqueID;
    }
    
    /**
     * Returns the value of subjectUniqueID field of the structure.
     * @return  subjectUniqueID
     */
    public boolean[] getSubjectUniqueID() {
        return subjectUniqueID;
    }
    
    /**
     * Returns the value of extensions field of the structure.
     * @return  extensions
     */
    public Extensions getExtensions() {
        return extensions;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 TBSCertificate value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * Places the string representation into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer) {
        buffer.append('[');
        buffer.append("\n  Version: V").append(version+1); //$NON-NLS-1$
        buffer.append("\n  Subject: ") //$NON-NLS-1$
            .append(subject.getName(X500Principal.RFC2253));
        buffer.append("\n  Signature Algorithm: "); //$NON-NLS-1$
        signature.dumpValue(buffer);
        buffer.append("\n  Key: "); //$NON-NLS-1$
        buffer.append(subjectPublicKeyInfo.getPublicKey().toString());
        buffer.append("\n  Validity: [From: "); //$NON-NLS-1$
        buffer.append(validity.getNotBefore());
        buffer.append("\n               To: "); //$NON-NLS-1$
        buffer.append(validity.getNotAfter()).append(']');
        buffer.append("\n  Issuer: "); //$NON-NLS-1$
        buffer.append(issuer.getName(X500Principal.RFC2253));
        buffer.append("\n  Serial Number: "); //$NON-NLS-1$
        buffer.append(serialNumber);
        if (issuerUniqueID != null) {
            buffer.append("\n  Issuer Id: "); //$NON-NLS-1$
            for (int i=0; i<issuerUniqueID.length; i++) {
                buffer.append(issuerUniqueID[i] ? '1' : '0');
            }
        }
        if (subjectUniqueID != null) {
            buffer.append("\n  Subject Id: "); //$NON-NLS-1$
            for (int i=0; i<subjectUniqueID.length; i++) {
                buffer.append(subjectUniqueID[i] ? '1' : '0');
            }
        }
        if (extensions != null) {
            buffer.append("\n\n  Extensions: "); //$NON-NLS-1$
            buffer.append("[\n"); //$NON-NLS-1$
            extensions.dumpValue(buffer, "    "); //$NON-NLS-1$
            buffer.append("  ]"); //$NON-NLS-1$
        }
        buffer.append("\n]"); //$NON-NLS-1$
    }

    /**
     * X.509 TBSCertificate encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            new ASN1Explicit(0, ASN1Integer.getInstance()), ASN1Integer.getInstance(), 
            AlgorithmIdentifier.ASN1, Name.ASN1,
            Validity.ASN1, Name.ASN1, SubjectPublicKeyInfo.ASN1, 
            new ASN1Implicit(1, ASN1BitString.getInstance()), 
            new ASN1Implicit(2, ASN1BitString.getInstance()), 
            new ASN1Explicit(3, Extensions.ASN1)}) {
        {
            setDefault(new byte[] {0}, 0);
            setOptional(7);
            setOptional(8);
            setOptional(9);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;

            boolean[] issuerUniqueID = (values[7] == null) 
                ? null : ((BitString) values[7]).toBooleanArray();
            boolean[] subjectUniqueID = (values[8] == null) 
                ? null : ((BitString) values[8]).toBooleanArray();
            return new TBSCertificate(
                        ASN1Integer.toIntValue(values[0]),
                        new BigInteger((byte[]) values[1]), 
                        (AlgorithmIdentifier) values[2],
                        (Name) values[3], 
                        (Validity) values[4], 
                        (Name) values[5],
                        (SubjectPublicKeyInfo) values[6], 
                        issuerUniqueID,
                        subjectUniqueID,
                        (Extensions) values[9],
                        in.getEncoded()
                    );
        }

        protected void getValues(Object object, Object[] values) {
            TBSCertificate tbs = (TBSCertificate) object;
            values[0] = ASN1Integer.fromIntValue(tbs.version);
            values[1] = tbs.serialNumber.toByteArray();
            values[2] = tbs.signature;
            values[3] = tbs.issuer; 
            values[4] = tbs.validity;
            values[5] = tbs.subject;
            values[6] = tbs.subjectPublicKeyInfo;
            if (tbs.issuerUniqueID != null) {
                values[7] = new BitString(tbs.issuerUniqueID);
            }
            if (tbs.subjectUniqueID != null) {
                values[8] = new BitString(tbs.subjectUniqueID);
            }
            values[9] = tbs.extensions;
        }
    };
}
