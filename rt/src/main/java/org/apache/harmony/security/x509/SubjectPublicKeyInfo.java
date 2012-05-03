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
* @version $Revision$
*/

package org.apache.harmony.security.x509;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.utils.AlgNameMapper;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with the following structure which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  SubjectPublicKeyInfo  ::=  SEQUENCE  {
 *      algorithm            AlgorithmIdentifier,
 *      subjectPublicKey     BIT STRING
 *  }
 * </pre>
 */
public final class SubjectPublicKeyInfo {
    /** the value of algorithmID field of the structure */
    private AlgorithmIdentifier algorithmID;
    /** the value of subjectPublicKey field of the structure */
    private byte[] subjectPublicKey;
    /** the public key corresponding to this SubjectPublicKeyInfo */
    private PublicKey publicKey;
    /** the value of unusedBits field of the structure */
    private int unusedBits;
    /** the ASN.1 encoded form of SubjectPublicKeyInfo */
    private byte[] encoding;

    public SubjectPublicKeyInfo(AlgorithmIdentifier algID, byte[] subjectPublicKey) {
        this(algID, subjectPublicKey, 0);
    }

    public SubjectPublicKeyInfo(AlgorithmIdentifier algID, byte[] subjectPublicKey, int unused) {
        this(algID, subjectPublicKey, 0, null);
    }

    private SubjectPublicKeyInfo(AlgorithmIdentifier algID,
                                 byte[] subjectPublicKey, int unused,
                                 byte[] encoding) {
        this.algorithmID = algID;
        this.subjectPublicKey = subjectPublicKey;
        this.unusedBits = unused;
        this.encoding = encoding;
    }

    /**
     * Returns the value of algorithmIdentifier field of the structure.
     */
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return algorithmID;
    }

    /**
     * Returns the value of subjectPublicKey field of the structure.
     */
    public byte[] getSubjectPublicKey() {
        return subjectPublicKey;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 SubjectPublicKeyInfo value.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * Returns The PublicKey corresponding to this SubjectPublicKeyInfo
     * instance.
     */
    public PublicKey getPublicKey() {
        if (publicKey == null) {
            String alg_oid = algorithmID.getAlgorithm();
            try {
                String alg =
                    AlgNameMapper.map2AlgName(alg_oid);

                if (alg == null) {
                    alg = alg_oid;
                }
                publicKey = KeyFactory.getInstance(alg)
                    .generatePublic(new X509EncodedKeySpec(getEncoded()));
            } catch (InvalidKeySpecException ignored) {
            } catch (NoSuchAlgorithmException ignored) {
            }
            if (publicKey == null) {
                publicKey = new X509PublicKey(alg_oid, getEncoded(),
                        subjectPublicKey);
            }
        }
        return publicKey;
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            AlgorithmIdentifier.ASN1, ASN1BitString.getInstance() }) {
        @Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new SubjectPublicKeyInfo(
                    (AlgorithmIdentifier) values[0],
                    ((BitString) values[1]).bytes,
                    ((BitString) values[1]).unusedBits,
                    in.getEncoded());
        }

        @Override protected void getValues(Object object, Object[] values) {
            SubjectPublicKeyInfo spki = (SubjectPublicKeyInfo) object;
            values[0] = spki.algorithmID;
            values[1] = new BitString(spki.subjectPublicKey, spki.unusedBits);
        }
    };
}

