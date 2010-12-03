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

import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BerOutputStream;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the following part of X.509 CRL
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
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
 *  </pre>
 */
public class ReasonFlags {
    
    /**
     * The names of the reasons.
     */
    static final String[] REASONS = {
        "unused", //$NON-NLS-1$
        "keyCompromise", //$NON-NLS-1$
        "cACompromise", //$NON-NLS-1$
        "affiliationChanged", //$NON-NLS-1$
        "superseded", //$NON-NLS-1$
        "cessationOfOperation", //$NON-NLS-1$
        "certificateHold", //$NON-NLS-1$
        "privilegeWithdrawn", //$NON-NLS-1$
        "aACompromise" //$NON-NLS-1$
    };

    // the value of extension
    private boolean[] flags;
    
    /**
     * Creates the extension object corresponding to the given flags.
     */
    public ReasonFlags(boolean[] flags) {
        this.flags = flags;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix);
        buffer.append("ReasonFlags [\n"); //$NON-NLS-1$
        for (int i=0; i<flags.length; i++) {
            if (flags[i]) {
                buffer.append(prefix).append("  ") //$NON-NLS-1$
                    .append(REASONS[i]).append('\n');
            }
        }
        buffer.append(prefix);
        buffer.append("]\n"); //$NON-NLS-1$
    }
    
    /**
     * ASN.1 Encoder/Decoder.
     */
    public static final ASN1BitString ASN1 = 
                            new ASN1BitString.ASN1NamedBitList(REASONS.length) {
        public Object getDecodedObject(BerInputStream in) throws IOException {
            return new ReasonFlags((boolean[]) super.getDecodedObject(in));
        }
        
        public void setEncodingContent(BerOutputStream out) {
            out.content = ((ReasonFlags) out.content).flags;
            super.setEncodingContent(out);
        }
    };
}

