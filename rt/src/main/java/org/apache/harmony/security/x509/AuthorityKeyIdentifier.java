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
import java.math.BigInteger;

import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.utils.Array;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with Authority Key Identifier Extension (OID = 2.5.29.35). 
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *   id-ce-authorityKeyIdentifier OBJECT IDENTIFIER ::=  { id-ce 35 }
 *
 *   AuthorityKeyIdentifier ::= SEQUENCE {
 *      keyIdentifier             [0] KeyIdentifier           OPTIONAL,
 *      authorityCertIssuer       [1] GeneralNames            OPTIONAL,
 *      authorityCertSerialNumber [2] CertificateSerialNumber OPTIONAL  }
 *
 *   KeyIdentifier ::= OCTET STRING
 * </pre>
 */
public class AuthorityKeyIdentifier extends ExtensionValue {
   
    private final byte[] keyIdentifier;
    private final GeneralNames authorityCertIssuer;
    private final BigInteger authorityCertSerialNumber;
    
    public AuthorityKeyIdentifier(byte[] keyIdentifier, 
            GeneralNames authorityCertIssuer, 
            BigInteger authorityCertSerialNumber) {
        this.keyIdentifier = keyIdentifier;
        this.authorityCertIssuer = authorityCertIssuer;
        this.authorityCertSerialNumber = authorityCertSerialNumber;
    }
    
    public static AuthorityKeyIdentifier decode(byte[] encoding) 
            throws IOException {
        AuthorityKeyIdentifier aki =
            (AuthorityKeyIdentifier) ASN1.decode(encoding);
        aki.encoding = encoding;
        return aki;
    } 

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
        buffer.append(prefix).append("AuthorityKeyIdentifier [\n"); //$NON-NLS-1$
        if (keyIdentifier != null) {
            buffer.append(prefix).append("  keyIdentifier:\n"); //$NON-NLS-1$
            buffer.append(Array.toString(keyIdentifier, prefix + "    ")); //$NON-NLS-1$
        }
        if (authorityCertIssuer != null) {
            buffer.append(prefix).append("  authorityCertIssuer: [\n"); //$NON-NLS-1$
            authorityCertIssuer.dumpValue(buffer, prefix + "    "); //$NON-NLS-1$
            buffer.append(prefix).append("  ]\n"); //$NON-NLS-1$
        }
        if (authorityCertSerialNumber != null) {
            buffer.append(prefix).append("  authorityCertSerialNumber: ") //$NON-NLS-1$
                .append(authorityCertSerialNumber).append('\n');
        }
        buffer.append(prefix).append("]\n"); //$NON-NLS-1$
    }

    public static final ASN1Type ASN1 = new ASN1Sequence(
            new ASN1Type[] {
                new ASN1Implicit(0, ASN1OctetString.getInstance()),
                new ASN1Implicit(1, GeneralNames.ASN1),
                new ASN1Implicit(2, ASN1Integer.getInstance()),
            }) {
        {
            setOptional(0);
            setOptional(1);
            setOptional(2);
        }

        protected Object getDecodedObject(BerInputStream in) throws IOException {
            Object[] values = (Object[]) in.content;

            byte[] enc = (byte[]) values[2];
            BigInteger authorityCertSerialNumber = null;
            if (enc != null) {
                authorityCertSerialNumber = new BigInteger(enc);
            }

            return new AuthorityKeyIdentifier((byte[]) values[0],
                    (GeneralNames) values[1], authorityCertSerialNumber);
        }

        protected void getValues(Object object, Object[] values) {

            AuthorityKeyIdentifier akid = (AuthorityKeyIdentifier) object;

            values[0] = akid.keyIdentifier;
            values[1] = akid.authorityCertIssuer;
            if (akid.authorityCertSerialNumber != null) {
                values[2] = akid.authorityCertSerialNumber.toByteArray();
            }
        }
    };
}

