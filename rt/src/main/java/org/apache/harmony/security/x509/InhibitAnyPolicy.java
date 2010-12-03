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
import java.math.BigInteger;
import org.apache.harmony.security.asn1.ASN1Integer;

/**
 * InhibitAnyPolicy Certificate Extension (OID = 2.5.29.54)
 * Its ASN.1 notation is as follows:
 * <pre>
 *  id-ce-inhibitAnyPolicy OBJECT IDENTIFIER ::=  { id-ce 54 }
 *
 *  InhibitAnyPolicy ::= SkipCerts
 *
 *  SkipCerts ::= INTEGER (0..MAX)
 * </pre>
 * (as specified in RFC 3280 http://www.ietf.org/rfc/rfc3280.txt).
 */
public class InhibitAnyPolicy extends ExtensionValue {

    // the value of the extension
    private int skipCerts;

    /**
     * Create the object on the base of SkipCerts value.
     */
    public InhibitAnyPolicy(int skipCerts) {
        this.skipCerts = skipCerts;
    }

    /**
     * Creates an object on the base of its encoded form.
     */
    public InhibitAnyPolicy(byte[] encoding) throws IOException {
        super(encoding);
        this.skipCerts = new BigInteger((byte[])
                ASN1Integer.getInstance().decode(encoding)).intValue();
    }

    /**
     * Return the value of the extension.
     */
    public int getSkipCerts() {
        return skipCerts;
    }

    /**
     * Returns ASN.1 encoded form of the object.
     * @return a byte array containing ASN.1 encoded form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1Integer.getInstance()
                .encode(ASN1Integer.fromIntValue(skipCerts));
        }
        return encoding;
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("Inhibit Any-Policy: ") //$NON-NLS-1$
            .append(skipCerts).append('\n');
    }
}

