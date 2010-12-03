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

package org.apache.harmony.security.x509;

import java.io.IOException;

import org.apache.harmony.security.asn1.ASN1Enumerated;
import org.apache.harmony.security.asn1.ASN1Type;

/**
 * CRL Entry's Reason Code Extension (OID = 2.5.29.21).
 * <pre>
 *  id-ce-cRLReason OBJECT IDENTIFIER ::= { id-ce 21 }
 *
 *  -- reasonCode ::= { CRLReason }
 *  CRLReason ::= ENUMERATED {
 *       unspecified             (0),
 *       keyCompromise           (1),
 *       cACompromise            (2),
 *       affiliationChanged      (3),
 *       superseded              (4),
 *       cessationOfOperation    (5),
 *       certificateHold         (6),
 *       removeFromCRL           (8),
 *       privilegeWithdrawn      (9),
 *       aACompromise           (10)
 *  }
 * </pre>
 * (as specified in RFC 3280 http://www.ietf.org/rfc/rfc3280.txt)
 */
public class ReasonCode extends ExtensionValue {

    // predefined reason code values
    public static final byte UNSPECIFIED = 0;
    public static final byte KEY_COMPROMISE = 1;
    public static final byte CA_COMPROMISE = 2;
    public static final byte AFFILIATION_CHANGED = 3;
    public static final byte SUPERSEDED = 4;
    public static final byte CESSATION_OF_OPERATION = 5;
    public static final byte CERTIFICATE_HOLD = 6;
    public static final byte REMOVE_FROM_CRL = 8;
    public static final byte PRIVILEGE_WITHDRAWN = 9;
    public static final byte AA_COMPROMISE = 10;

    // the reason code value
    private final byte code;

    public ReasonCode(byte code) {
        this.code = code;
    }

    public ReasonCode(byte[] encoding) throws IOException {
        super(encoding);
        this.code = ((byte[]) ASN1.decode(encoding))[0];
    }

    public int getCode() {
        return code;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 ReasonCode value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(new byte[] {(byte) code});
        }
        return encoding;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("Reason Code: [ "); //$NON-NLS-1$
        switch (code) {
            case UNSPECIFIED:
                buffer.append("unspecified"); //$NON-NLS-1$
                break;
            case KEY_COMPROMISE:
                buffer.append("keyCompromise"); //$NON-NLS-1$
                break;
            case CA_COMPROMISE:
                buffer.append("cACompromise"); //$NON-NLS-1$
                break;
            case AFFILIATION_CHANGED:
                buffer.append("affiliationChanged"); //$NON-NLS-1$
                break;
            case SUPERSEDED:
                buffer.append("superseded"); //$NON-NLS-1$
                break;
            case CESSATION_OF_OPERATION:
                buffer.append("cessationOfOperation"); //$NON-NLS-1$
                break;
            case CERTIFICATE_HOLD:
                buffer.append("certificateHold"); //$NON-NLS-1$
                break;
            case REMOVE_FROM_CRL:
                buffer.append("removeFromCRL"); //$NON-NLS-1$
                break;
            case PRIVILEGE_WITHDRAWN:
                buffer.append("privilegeWithdrawn"); //$NON-NLS-1$
                break;
            case AA_COMPROMISE:
                buffer.append("aACompromise"); //$NON-NLS-1$
                break;
        }
        buffer.append(" ]\n"); //$NON-NLS-1$
    }

    /**
     * ASN.1 Encoder/Decoder.
     */
    public static final ASN1Type ASN1 = ASN1Enumerated.getInstance();
}

