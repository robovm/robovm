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

import java.util.Arrays;

import org.apache.harmony.security.asn1.ASN1Any;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.utils.AlgNameMapper;


/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with the Algorithm Identifier which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  AlgorithmIdentifier ::= SEQUENCE {
 *      algorithm OBJECT IDENTIFIER,
 *      parameters ANY DEFINED BY algorithm OPTIONAL 
 *  }
 * </pre>
 */
public class AlgorithmIdentifier {

    // the value of algorithm field
    private String algorithm;
    // the name of the algorithm
    private String algorithmName;
    // the value of parameters field
    private byte[] parameters;
    // the encoding of AlgorithmIdentifier value
    private byte[] encoding;
    
    /**
     * TODO
     * @param   algorithm:  String
     */
    public AlgorithmIdentifier(String algorithm) {
        this(algorithm, null, null);
    }
    
    /**
     * TODO
     * @param   algorithm:  String
     * @param   parameters: byte[]
     */
    public AlgorithmIdentifier(String algorithm, byte[] parameters) {
        this(algorithm, parameters, null);
    }

    // 
    // TODO
    // @param   algorithm:  String
    // @param   parameters: byte[]
    // @param   encoding:   byte[]
    // 
    private AlgorithmIdentifier(String algorithm, byte[] parameters, 
                                byte[] encoding) {
        this.algorithm = algorithm;
        this.parameters = parameters;
        this.encoding = encoding;
    }
        
    /**
     * Returns the value of algorithm field of the structure.
     * @return  algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the name of the algorithm corresponding to
     * its OID. If there is no the such correspondence,
     * algorithm OID is returned.
     * @return  algorithm
     */
    public String getAlgorithmName() {
        if (algorithmName == null) {
            algorithmName = AlgNameMapper.map2AlgName(algorithm);
            if (algorithmName == null) {
                algorithmName = algorithm;
            }
        }
        return algorithmName;
    }

    /**
     * Returns the value of parameters field of the structure.
     * @return  parameters
     */
    public byte[] getParameters() {
        return parameters;
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 AlgorithmIdentifier value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }
    
    public boolean equals(Object ai) {
        if (!(ai instanceof AlgorithmIdentifier)) {
            return false;
        }
        AlgorithmIdentifier algid = (AlgorithmIdentifier) ai;
        return (algorithm.equals(algid.algorithm))
            && ((parameters == null)
                    ? algid.parameters == null
                    : Arrays.equals(parameters, algid.parameters));
    }
    
    public int hashCode() {
    	return algorithm.hashCode() * 37 + 
    		(parameters != null ? parameters.hashCode() : 0);
    }
    
    /**
     * Places the string representation into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer) {
        buffer.append(getAlgorithmName());
        if (parameters == null) {
            buffer.append(", no params, "); //$NON-NLS-1$
        } else {
            buffer.append(", params unparsed, "); //$NON-NLS-1$
        }
        buffer.append("OID = "); //$NON-NLS-1$
        buffer.append(getAlgorithm());
    }

    /**
     * Custom AlgorithmIdentifier DER encoder/decoder
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Oid.getInstance(), ASN1Any.getInstance() }) {
        {
            setOptional(1); // parameters are optional
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new AlgorithmIdentifier(ObjectIdentifier
                    .toString((int[]) values[0]), (byte[]) values[1]);
        }

        protected void getValues(Object object, Object[] values) {

            AlgorithmIdentifier aID = (AlgorithmIdentifier) object;

            values[0] = ObjectIdentifier.toIntArray(aID.getAlgorithm());
            values[1] = aID.getParameters();
        }
    };

}

