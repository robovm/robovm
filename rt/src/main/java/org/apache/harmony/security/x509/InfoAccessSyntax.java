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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with the SubjectInfoAccessSyntax and AuthorityInfoAccessSyntax 
 * which are a part of X.509 framework
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *  
 *  SubjectInfoAccessSyntax  ::=
 *      SEQUENCE SIZE (1..MAX) OF AccessDescriptions

 *  AuthorityInfoAccessSyntax  ::=
 *      SEQUENCE SIZE (1..MAX) OF AccessDescriptions
 *  
 *  AccessDescription  ::=  SEQUENCE {
 *      accessMethod          OBJECT IDENTIFIER,
 *      accessLocation        GeneralName  }
 * 
 */
public class InfoAccessSyntax extends ExtensionValue {

    private final List accessDescriptions;

    public InfoAccessSyntax(List accessDescriptions) throws IOException {
        this(accessDescriptions, null);
    }

    private InfoAccessSyntax(List accessDescriptions, byte[] encoding)
            throws IOException {
        if (accessDescriptions == null || accessDescriptions.isEmpty()) {
            // "AccessDescriptions list is null or empty"
            throw new IOException(Messages.getString("security.1A3")); //$NON-NLS-1$
        }
        this.accessDescriptions = accessDescriptions;
        this.encoding = encoding;
    }

    public List getAccessDescriptions() {
        return new ArrayList(accessDescriptions);
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 InfoAccessSyntax.
     * @return a byte array containing ASN.1 encoded form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public static InfoAccessSyntax decode(byte[] encoding) throws IOException {
        return ((InfoAccessSyntax) ASN1.decode(encoding));
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n---- InfoAccessSyntax:"); //$NON-NLS-1$
        if (accessDescriptions != null) {
            for (Iterator it = accessDescriptions.iterator(); it.hasNext();) {
                res.append('\n');
                res.append(it.next());
            }
        }
        res.append("\n---- InfoAccessSyntax END\n"); //$NON-NLS-1$
        return res.toString();
    }

    /**
     * Places the string representation of extension value
     * into the StringBuffer object.
     */
    public void dumpValue(StringBuffer buffer, String prefix) {
        buffer.append(prefix).append("AccessDescriptions:\n"); //$NON-NLS-1$
        if (accessDescriptions == null || accessDescriptions.isEmpty()) {
            buffer.append("NULL\n"); //$NON-NLS-1$
        } else {
            Iterator itr = accessDescriptions.iterator();
            while (itr.hasNext()) {
                buffer.append(itr.next().toString());
            }
        }
    }

    
    /**
     * ASN.1 DER X.509 AuthorityInfoAccessSyntax and SubjectInfoAccessSyntax 
     * encoder/decoder class.
     */
    public static final ASN1Type ASN1 = new ASN1SequenceOf(AccessDescription.ASN1) {

        public Object getDecodedObject(BerInputStream in) throws IOException {
            return new InfoAccessSyntax((List)in.content, in.getEncoded());
        }

        public Collection getValues(Object object) {
            InfoAccessSyntax aias = (InfoAccessSyntax) object;
            return aias.accessDescriptions;
        }
    };

}

