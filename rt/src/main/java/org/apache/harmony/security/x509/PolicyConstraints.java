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
* @author Vladimir N. Molotkov, Alexander Y. Kleymenov
*/

package org.apache.harmony.security.x509;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with PolicyConstraints structure which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 * 
 * <pre>
 *
 *  PolicyConstraints ::= SEQUENCE {
 *       requireExplicitPolicy           [0] SkipCerts OPTIONAL,
 *       inhibitPolicyMapping            [1] SkipCerts OPTIONAL }
 *
 *  SkipCerts ::= INTEGER (0..MAX)
 *
 * </pre>
 * 
 * TODO: This class is not fully implemented.
 *
 * @see org.apache.harmony.security.x509.GeneralSubtree
 * @see org.apache.harmony.security.x509.GeneralName
 */
public class PolicyConstraints extends ExtensionValue {

    // the value of requireExplicitPolicy field of the structure
    private final BigInteger requireExplicitPolicy;
    // the value of inhibitPolicyMapping field of the structure
    private final BigInteger inhibitPolicyMapping;
    // the ASN.1 encoded form of PolicyConstraints;
    private byte[] encoding;

    /**
     * TODO
     */
    public PolicyConstraints() {
        this(null, null);
    }
    
    /**
     * TODO
     * @param   requireExplicitPolicy:  GeneralSubtrees
     * @param   inhibitPolicyMapping:   GeneralSubtrees
     */
    public PolicyConstraints(BigInteger requireExplicitPolicy,
            BigInteger inhibitPolicyMapping) {
        this.requireExplicitPolicy = requireExplicitPolicy;
        this.inhibitPolicyMapping = inhibitPolicyMapping;
    }

    /**
     * TODO
     * @param   requireExplicitPolicy:  GeneralSubtrees
     * @param   inhibitPolicyMapping:   GeneralSubtrees
     */
    public PolicyConstraints(int requireExplicitPolicy,
            int inhibitPolicyMapping) {
        this.requireExplicitPolicy = BigInteger.valueOf(requireExplicitPolicy);
        this.inhibitPolicyMapping = BigInteger.valueOf(inhibitPolicyMapping);
    }

    public PolicyConstraints(byte[] encoding) throws IOException {
        super(encoding);
        PolicyConstraints pc = (PolicyConstraints) ASN1.decode(encoding);
        this.requireExplicitPolicy = pc.requireExplicitPolicy;
        this.inhibitPolicyMapping = pc.inhibitPolicyMapping;
    }

    //
    // TODO
    // @param   requireExplicitPolicy:  GeneralSubtrees
    // @param   inhibitPolicyMapping:   GeneralSubtrees
    // @param   encoding:   byte[]
    //
    private PolicyConstraints(BigInteger requireExplicitPolicy, 
                            BigInteger inhibitPolicyMapping, byte[] encoding) {
        this(requireExplicitPolicy, inhibitPolicyMapping);
        this.encoding = encoding;
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 PolicyConstraints value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * Places the string representation of extension value 
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("PolicyConstraints: [\n"); //$NON-NLS-1$
        if (requireExplicitPolicy != null) {
            buffer.append(prefix).append("  requireExplicitPolicy: ") //$NON-NLS-1$
                .append(requireExplicitPolicy).append('\n');
        }
        if (inhibitPolicyMapping != null) {
            buffer.append(prefix).append("  inhibitPolicyMapping: ") //$NON-NLS-1$
                .append(inhibitPolicyMapping).append('\n');
        }
        buffer.append(prefix).append("]\n"); //$NON-NLS-1$
    }

    /**
     * X.509 PolicyConstraints encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            new ASN1Implicit(0, ASN1Integer.getInstance()), 
            new ASN1Implicit(1, ASN1Integer.getInstance()) }) {
        {
            setOptional(0);
            setOptional(1);
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            BigInteger requireExplicitPolicy = null;
            BigInteger inhibitPolicyMapping = null;
            if (values[0] != null) {
                requireExplicitPolicy = new BigInteger((byte[]) values[0]);
            }
            if (values[1] != null) {
                inhibitPolicyMapping = new BigInteger((byte[]) values[1]);
            }
            return new PolicyConstraints(
                requireExplicitPolicy, inhibitPolicyMapping,
                    in.getEncoded());
        }

        protected void getValues(Object object, Object[] values) {

            PolicyConstraints pc = (PolicyConstraints) object;

            values[0] = pc.requireExplicitPolicy.toByteArray();
            values[1] = pc.inhibitPolicyMapping.toByteArray();
        }
    };
}
