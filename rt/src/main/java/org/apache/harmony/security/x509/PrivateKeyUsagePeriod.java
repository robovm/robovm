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
* @version $Revision$
*/

package org.apache.harmony.security.x509;

import java.util.Date;
import org.apache.harmony.security.asn1.ASN1GeneralizedTime;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work
 * with the following certificate extension (OID: 2.5.29.16)
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 * PrivateKeyUsagePeriod ::= SEQUENCE {
 *      notBefore       [0]     GeneralizedTime OPTIONAL,
 *      notAfter        [1]     GeneralizedTime OPTIONAL
 * }
 * </pre>
 */
public final class PrivateKeyUsagePeriod {
    /** the value of notBeforeDate field of the structure */
    private final Date notBeforeDate;
    /** the value of notAfterDate field of the structure */
    private final Date notAfterDate;
    /** the ASN.1 encoded form of PrivateKeyUsagePeriod */
    private byte[] encoding;

    public PrivateKeyUsagePeriod(Date notBeforeDate, Date notAfterDate) {
        this(notBeforeDate, notAfterDate, null);
    }

    private PrivateKeyUsagePeriod(Date notBeforeDate, Date notAfterDate, byte[] encoding) {
        this.notBeforeDate = notBeforeDate;
        this.notAfterDate = notAfterDate;
        this.encoding = encoding;
    }

    /**
     * Returns the value of notBefore field of the structure.
     */
    public Date getNotBefore() {
        return notBeforeDate;
    }

    /**
     * Returns the value of notAfter field of the structure.
     */
    public Date getNotAfter() {
        return notAfterDate;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 PrivateKeyUsagePeriod value.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * ASN.1 DER X.509 PrivateKeyUsagePeriod encoder/decoder class.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            new ASN1Implicit(0, ASN1GeneralizedTime.getInstance()),
            new ASN1Implicit(1, ASN1GeneralizedTime.getInstance()) }) {
        {
            setOptional(0);
            setOptional(1);
        }

        @Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[])in.content;
            return new PrivateKeyUsagePeriod((Date) values[0], (Date) values[1], in.getEncoded());
        }

        @Override protected void getValues(Object object, Object[] values) {
            PrivateKeyUsagePeriod pkup = (PrivateKeyUsagePeriod) object;
            values[0] = pkup.notBeforeDate;
            values[1] = pkup.notAfterDate;
        }
    };
}

