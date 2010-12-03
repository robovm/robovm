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

import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with the AccessDescription which is a part of X.509 framework
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *  
 *  AccessDescription  ::=  SEQUENCE {
 *      accessMethod          OBJECT IDENTIFIER,
 *      accessLocation        GeneralName  }
 * 
 */
public class AccessDescription {
    
    // the value of access method
    private final String accessMethod;
    
    // the value of accessLocation
    private final GeneralName accessLocation;
    
    private byte [] encoding;
    
    public AccessDescription(String accessMethod, GeneralName accessLocation) {
        this.accessMethod = accessMethod;
        this.accessLocation = accessLocation;
    }
    
    private AccessDescription(String accessMethod, GeneralName accessLocation,
            byte[] encoding) {
        this.accessMethod = accessMethod;
        this.accessLocation = accessLocation;
        this.encoding = encoding;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 AccessDescription.
     * @return a byte array containing ASN.1 encoded form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n-- AccessDescription:"); //$NON-NLS-1$
        res.append("\naccessMethod:  "); //$NON-NLS-1$
        res.append(accessMethod);
        res.append("\naccessLocation:  "); //$NON-NLS-1$
        res.append(accessLocation);
        res.append("\n-- AccessDescription END\n"); //$NON-NLS-1$
        return res.toString();
    }

    /**
     * @return Returns the accessLocation.
     */
    public GeneralName getAccessLocation() {
        return accessLocation;
    }

    /**
     * @return Returns the accessMethod.
     */
    public String getAccessMethod() {
        return accessMethod;
    }
    
    /**
     * Custom AccessDescription DER encoder/decoder
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Oid.getInstance(), 
            GeneralName.ASN1 }) {

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new AccessDescription(
                    ObjectIdentifier.toString((int[]) values[0]), 
                    (GeneralName) values[1], in.getEncoded());
        }

        protected void getValues(Object object, Object[] values) {

            AccessDescription ad = (AccessDescription) object;

            values[0] = ObjectIdentifier.toIntArray(ad.accessMethod);
            values[1] = ad.accessLocation;
        }
    };

}

