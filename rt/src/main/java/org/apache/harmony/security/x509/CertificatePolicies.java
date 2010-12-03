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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with Certificate Policies structure which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 * 
 * <pre>
 *   certificatePolicies ::= SEQUENCE SIZE (1..MAX) OF PolicyInformation
 * </pre>
 * 
 */

public class CertificatePolicies extends ExtensionValue {

    // the values of policyInformation field of the structure
    private List policyInformations;
    // the ASN.1 encoded form of CertificatePolicies
    private byte[] encoding;
    
    /**
     * Constructs an object representing the value of CertificatePolicies.
     */
    public CertificatePolicies() {}
    
    /**
     * TODO
     * @param   policyInformations: List
     */
    public CertificatePolicies(List policyInformations) {
        this.policyInformations = policyInformations;
    }

    public static CertificatePolicies decode(byte[] encoding) 
            throws IOException {
        CertificatePolicies cps = ((CertificatePolicies) ASN1.decode(encoding));
        cps.encoding = encoding;
        return cps;
    }
    
    // 
    // TODO
    // @param   policyInformations: List
    // @param   encoding:   byte[]
    // 
    private CertificatePolicies(List policyInformations, byte[] encoding) {
        this.policyInformations = policyInformations;
        this.encoding = encoding;
    }

    /**
     * Returns the values of policyInformation field of the structure.
     * @return  policyInformations
     */
    public List getPolicyInformations() {
        return new ArrayList(policyInformations);
    }

    /**
     * TODO
     * @param   policyInformation:  PolicyInformation
     * @return
     */
    public CertificatePolicies addPolicyInformation(
            PolicyInformation policyInformation) {
        encoding = null;
        if (policyInformations == null) {
            policyInformations = new ArrayList();
        }
        policyInformations.add(policyInformation);
        return this;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 CertificatePolicies value.
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
        buffer.append(prefix).append("CertificatePolicies [\n"); //$NON-NLS-1$
        for (Iterator it=policyInformations.iterator(); it.hasNext();) {
            buffer.append(prefix);
            buffer.append("  "); //$NON-NLS-1$
            ((PolicyInformation) it.next()).dumpValue(buffer);
            buffer.append('\n');
        }
        buffer.append(prefix).append("]\n"); //$NON-NLS-1$
    }

    /**
     * ASN.1 DER X.509 CertificatePolicies encoder/decoder class.
     */
    public static final ASN1Type ASN1 = 
        new ASN1SequenceOf(PolicyInformation.ASN1) {

        public Object getDecodedObject(BerInputStream in) {
            return new CertificatePolicies((List) in.content, in.getEncoded());
        }

        public Collection getValues(Object object) {
            CertificatePolicies cps = (CertificatePolicies) object;
            return cps.policyInformations;
        }
    };
}

