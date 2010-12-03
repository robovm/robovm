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
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.ASN1BitString;

/**
 * Key Usage Extension (OID = 2.5.29.15).
 *
 * The ASN.1 definition for Key Usage Extension is:
 *
 * <pre>
 * id-ce-keyUsage OBJECT IDENTIFIER ::=  { id-ce 15 }
 *
 * KeyUsage ::= BIT STRING {
 *     digitalSignature        (0),
 *     nonRepudiation          (1),
 *     keyEncipherment         (2),
 *     dataEncipherment        (3),
 *     keyAgreement            (4),
 *     keyCertSign             (5),
 *     cRLSign                 (6),
 *     encipherOnly            (7),
 *     decipherOnly            (8)
 * }
 * </pre>
 * (as specified in RFC 3280 http://www.ietf.org/rfc/rfc3280.txt)
 */
public class KeyUsage extends ExtensionValue {

    /**
     * The names of the usages.
     */
    private static final String[] USAGES = {
        "digitalSignature", //$NON-NLS-1$
        "nonRepudiation", //$NON-NLS-1$
        "keyEncipherment", //$NON-NLS-1$
        "dataEncipherment", //$NON-NLS-1$
        "keyAgreement", //$NON-NLS-1$
        "keyCertSign", //$NON-NLS-1$
        "cRLSign", //$NON-NLS-1$
        "encipherOnly", //$NON-NLS-1$
        "decipherOnly", //$NON-NLS-1$
    };

    // the value of extension
    private final boolean[] keyUsage;

    /**
     * Creates the extension object corresponding to the given key usage.
     */
    public KeyUsage(boolean[] keyUsage) {
        this.keyUsage = keyUsage;
    }

    /**
     * Creates the extension object on the base of its encoded form.
     */
    public KeyUsage(byte[] encoding) throws IOException {
        super(encoding);
        this.keyUsage = (boolean[]) ASN1.decode(encoding);
    }

    public boolean[] getKeyUsage() {
        return keyUsage;
    }

    /**
     * Returns the encoded of the object.
     * @return a byte array containing ASN.1 encoded form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(keyUsage);
        }
        return encoding;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("KeyUsage [\n"); //$NON-NLS-1$
        for (int i=0; i<keyUsage.length; i++) {
            if (keyUsage[i]) {
                buffer.append(prefix).append("  ") //$NON-NLS-1$
                    .append(USAGES[i]).append('\n');
            }
        }
        buffer.append(prefix).append("]\n"); //$NON-NLS-1$
    }

    /**
     * X.509 Extension value encoder/decoder.
     */
    private static final ASN1Type ASN1 = new ASN1BitString.ASN1NamedBitList(9);

}
