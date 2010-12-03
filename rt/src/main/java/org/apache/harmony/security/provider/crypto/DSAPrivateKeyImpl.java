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
  * 1. The class extends the PrivateKeyImpl class in "org.apache.harmony.security" package.
  *
  * 2. See a compatibility with RI comments
  *    in the below "DSAPrivateKeyImpl(PKCS8EncodedKeySpec keySpec)" constructor.
  */


package org.apache.harmony.security.provider.crypto;

import java.io.IOException;
import java.io.NotActiveException;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.harmony.security.PrivateKeyImpl;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.internal.nls.Messages;
import org.apache.harmony.security.pkcs8.PrivateKeyInfo;
import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.AlgorithmIdentifier;

/**
 * The class provides DSAPrivateKey functionality by extending a class implementing PrivateKey
 * and implementing methods defined in both interfaces, DSAKey and DSAPrivateKey
 */
public class DSAPrivateKeyImpl extends PrivateKeyImpl implements DSAPrivateKey {

    /**
     * @serial
     */
    private static final long serialVersionUID = -4716227614104950081L;

    private BigInteger x, g, p, q;

    private transient DSAParams params;

    /**
     * Creates object from DSAPrivateKeySpec.
     *
     * @param keySpec - a DSAPrivateKeySpec object
     */
    public DSAPrivateKeyImpl(DSAPrivateKeySpec keySpec) {

        super("DSA"); //$NON-NLS-1$

        PrivateKeyInfo pki;

        g = keySpec.getG();
        p = keySpec.getP();
        q = keySpec.getQ();

        ThreeIntegerSequence threeInts = new ThreeIntegerSequence(p
                .toByteArray(), q.toByteArray(), g.toByteArray());

        AlgorithmIdentifier ai = new AlgorithmIdentifier(AlgNameMapper
                .map2OID("DSA"), //$NON-NLS-1$
                threeInts.getEncoded());
        x = keySpec.getX();

        pki = new PrivateKeyInfo(0, ai, ASN1Integer.getInstance().encode(
                x.toByteArray()), null);

        setEncoding(pki.getEncoded());

        params = new DSAParameterSpec(p, q, g);
    }

    /**
     * Creates object from PKCS8EncodedKeySpec.
     *
     * @param keySpec - a XPKCS8EncodedKeySpec object
     *
     * @throws InvalidKeySpecException - if key data cannot be obtain from encoded format
     */
    public DSAPrivateKeyImpl(PKCS8EncodedKeySpec keySpec)
            throws InvalidKeySpecException {

        super("DSA"); //$NON-NLS-1$

        AlgorithmIdentifier ai;
        ThreeIntegerSequence threeInts = null;

        String alg, algName;

        byte encoding[] = keySpec.getEncoded();

        PrivateKeyInfo privateKeyInfo = null;

        try {
            privateKeyInfo = (PrivateKeyInfo) PrivateKeyInfo.ASN1
                    .decode(encoding);
        } catch (IOException e) {
            throw new InvalidKeySpecException(Messages.getString(
                    "security.19A", e)); //$NON-NLS-1$
        }

        try {
            x = new BigInteger((byte[]) ASN1Integer.getInstance().decode(
                    privateKeyInfo.getPrivateKey()));
        } catch (IOException e) {
            throw new InvalidKeySpecException(Messages.getString(
                    "security.19B", e)); //$NON-NLS-1$
        }

        ai = privateKeyInfo.getAlgorithmIdentifier();
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
        params = new DSAParameterSpec(p, q, g);
        setEncoding(encoding);

        /* 
         * the following code implements RI behavior
         */
        alg = ai.getAlgorithm();
        algName = AlgNameMapper.map2AlgName(alg);
        setAlgorithm(algName == null ? alg : algName);
    }

    public BigInteger getX() {
        return x;
    }

    public DSAParams getParams() {
        return params;
    }
    
    private void readObject(java.io.ObjectInputStream in) throws NotActiveException, IOException, ClassNotFoundException {
    	in.defaultReadObject();
    	params = new DSAParameterSpec(p, q, g);    	
    }

}
