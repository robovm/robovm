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
import java.util.Iterator;
import java.util.Collection;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the CRL Distribution Points which is the part of X.509 Certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  CRLDistributionPoints ::= SEQUENCE SIZE (1..MAX) OF DistributionPoint
 *
 *  DistributionPoint ::= SEQUENCE {
 *        distributionPoint       [0]     DistributionPointName OPTIONAL,
 *        reasons                 [1]     ReasonFlags OPTIONAL,
 *        cRLIssuer               [2]     GeneralNames OPTIONAL 
 *  }
 *
 *  DistributionPointName ::= CHOICE {
 *        fullName                [0]     GeneralNames,
 *        nameRelativeToCRLIssuer [1]     RelativeDistinguishedName 
 *  }
 *
 *  ReasonFlags ::= BIT STRING {
 *        unused                  (0),
 *        keyCompromise           (1),
 *        cACompromise            (2),
 *        affiliationChanged      (3),
 *        superseded              (4),
 *        cessationOfOperation    (5),
 *        certificateHold         (6),
 *        privilegeWithdrawn      (7),
 *        aACompromise            (8) 
 *  }
 * </pre>
 */
public class CRLDistributionPoints extends ExtensionValue {
    
    private List distributionPoints;
    private byte[] encoding;
    
    public CRLDistributionPoints(List distributionPoints) {
        if ((distributionPoints == null) 
                || (distributionPoints.size() == 0)) {
            throw new IllegalArgumentException(Messages.getString("security.17D")); //$NON-NLS-1$
        }
        this.distributionPoints = distributionPoints;
    }

    public CRLDistributionPoints(List distributionPoints, byte[] encoding) {
        if ((distributionPoints == null) 
                || (distributionPoints.size() == 0)) {
            throw new IllegalArgumentException(Messages.getString("security.17D")); //$NON-NLS-1$
        }
        this.distributionPoints = distributionPoints;
        this.encoding = encoding;
    }

    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public static CRLDistributionPoints decode(byte[] encoding) 
            throws IOException {
        CRLDistributionPoints cdp = (CRLDistributionPoints) ASN1.decode(encoding);
        return cdp;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("CRL Distribution Points: [\n"); //$NON-NLS-1$
        int number = 0;
        for (Iterator it=distributionPoints.iterator();
                it.hasNext();) {
            buffer.append(prefix).append("  [").append(++number).append("]\n"); //$NON-NLS-1$ //$NON-NLS-2$
            ((DistributionPoint) it.next()).dumpValue(buffer, prefix + "  "); //$NON-NLS-1$
        }
        buffer.append(prefix).append("]\n"); //$NON-NLS-1$
    }
    
    /**
     * Custom X.509 decoder.
     */
    public static final ASN1Type ASN1 = 
        new ASN1SequenceOf(DistributionPoint.ASN1) {

        public Object getDecodedObject(BerInputStream in) {
            return new CRLDistributionPoints((List)in.content, 
                    in.getEncoded());
        }

        public Collection getValues(Object object) {
            CRLDistributionPoints dps = (CRLDistributionPoints) object;
            return dps.distributionPoints;
        }
    };
}

