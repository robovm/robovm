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

 /*
  * TODO
  * 1. The class extends the PublicKeyImpl class in "org.apache.harmony.security" package.
  *
  * 2. The class uses methods in the auxiliary non-public "ThreeIntegerSequence" class 
  *    defined along with the "DSAPrivateKeyImpl" class.
  *
  * 3. See a compatibility with RI comments
  *    in the below "DSAPublicKeyImpl(X509EncodedKeySpec keySpec)" constructor.
  */

package org.apache.harmony.security.provider.crypto;

import java.io.IOException;
import java.io.NotActiveException;

import java.math.BigInteger;

import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.DSAParams;

import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.DSAParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.AlgorithmIdentifier;
import org.apache.harmony.security.x509.SubjectPublicKeyInfo;

import org.apache.harmony.security.asn1.ASN1Integer;

import org.apache.harmony.security.internal.nls.Messages;

import org.apache.harmony.security.PublicKeyImpl;

/**
 * The class provides DSAPublicKey functionality by extending a class implementing PublicKey
 * and implementing methods defined in both interfaces, DSAKey and DSAPublicKey
 */
public class DSAPublicKeyImpl extends PublicKeyImpl implements DSAPublicKey {

    /**
     * @serial
     */
    private static final long serialVersionUID = -2279672131310978336L;

    private BigInteger y, g, p, q;

    private transient DSAParams params;

    /**
     * Creates object from DSAPublicKeySpec.
     *
     * @param keySpec - a DSAPublicKeySpec object
     */
    public DSAPublicKeyImpl(DSAPublicKeySpec keySpec) {

        super("DSA"); //$NON-NLS-1$

        SubjectPublicKeyInfo spki;

        p = keySpec.getP();
        q = keySpec.getQ();
        g = keySpec.getG();

        ThreeIntegerSequence threeInts = new ThreeIntegerSequence(p
                .toByteArray(), q.toByteArray(), g.toByteArray());

        AlgorithmIdentifier ai = new AlgorithmIdentifier(AlgNameMapper
                .map2OID("DSA"), //$NON-NLS-1$
                threeInts.getEncoded());

        y = keySpec.getY();

        spki = new SubjectPublicKeyInfo(ai, ASN1Integer.getInstance().encode(
                y.toByteArray()));
        setEncoding(spki.getEncoded());

        params = (DSAParams) (new DSAParameterSpec(p, q, g));
    }

    /**
     * Creates object from X509EncodedKeySpec.
     *
     * @param keySpec - a X509EncodedKeySpec object
     *
     * @throws InvalidKeySpecException - if key data cannot be obtain from encoded format
     */
    public DSAPublicKeyImpl(X509EncodedKeySpec keySpec)
            throws InvalidKeySpecException {

        super("DSA"); //$NON-NLS-1$

        AlgorithmIdentifier ai;
        ThreeIntegerSequence threeInts = null;

        SubjectPublicKeyInfo subjectPublicKeyInfo = null;

        byte encoding[] = keySpec.getEncoded();

        String alg, algName;

        try {
            subjectPublicKeyInfo = (SubjectPublicKeyInfo) SubjectPublicKeyInfo.ASN1
                    .decode(encoding);
        } catch (IOException e) {
            throw new InvalidKeySpecException(Messages.getString(
                    "security.19A", e)); //$NON-NLS-1$
        }

        try {
            y = new BigInteger((byte[]) ASN1Integer.getInstance().decode(
                    subjectPublicKeyInfo.getSubjectPublicKey()));
        } catch (IOException e) {
            throw new InvalidKeySpecException(Messages.getString(
                    "security.19B", e)); //$NON-NLS-1$
        }

        ai = subjectPublicKeyInfo.getAlgorithmIdentifier();

        try {
            threeInts = (ThreeIntegerSequence) ThreeIntegerSequence.ASN1
                    .decode(ai.getParameters());
        } catch (IOException e) {
            throw new InvalidKeySpecException(Messages.getString(
                    "security.19B", e)); //$NON-NLS-1$
        }
        p = new BigInteger(threeInts.p);
        q = new BigInteger(threeInts.q);
        g = new BigInteger(threeInts.g);
        params = (DSAParams) (new DSAParameterSpec(p, q, g));

        setEncoding(encoding);

        /* 
         * the following code implements RI behavior
         */
        alg = ai.getAlgorithm();
        algName = AlgNameMapper.map2AlgName(alg);
        setAlgorithm(algName == null ? alg : algName);
    }

    /**
     * @return 
     *      a value of a public key (y).
     */
    public BigInteger getY() {
        return y;
    }

    /**
     * @return  
     *     DSA key parameters (p, q, g).
     */
    public DSAParams getParams() {
        return params;
    }
    
    private void readObject(java.io.ObjectInputStream in) throws NotActiveException, IOException, ClassNotFoundException {
    	in.defaultReadObject();
    	params = new DSAParameterSpec(p, q, g);    	
    }

}
