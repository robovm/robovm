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

package java.security.cert;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

/**
 * Abstract base class for entries in a certificate revocation list (CRL).
 *
 * @see X509CRL
 */
public abstract class X509CRLEntry implements X509Extension {

    /**
     * Creates a new {@code X509CRLEntry} instance.
     */
    public X509CRLEntry() {}

    /**
     * Returns whether the specified object equals to this instance.
     *
     * @param other
     *            the object to compare.
     * @return {@code true} if the specified object equals to this instance,
     *         otherwise {@code false}.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof X509CRLEntry)) {
            return false;
        }
        X509CRLEntry obj = (X509CRLEntry) other;
        try {
            return Arrays.equals(getEncoded(), obj.getEncoded());
        } catch (CRLException e) {
            return false;
        }
    }

    /**
     * Returns the hashcode of this instance.
     *
     * @return the hashcode of this instance.
     */
    public int hashCode() {
        int res = 0;
        try {
            byte[] array = getEncoded();
            for (int i=0; i<array.length; i++) {
                res += array[i] & 0xFF;
            }
        } catch (CRLException e) {
        }
        return res;
    }

    /**
     * Returns this entry in ASN.1 DER encoded form.
     *
     * @return the encoded form of this entry.
     * @throws CRLException
     *             if encoding fails.
     */
    public abstract byte[] getEncoded() throws CRLException;

    /**
     * Returns the serial number of the revoked certificate.
     *
     * @return the serial number of the revoked certificate.
     */
    public abstract BigInteger getSerialNumber();

    /**
     * Returns the issuer of the revoked certificate.
     *
     * @return the issuer of the revoked certificate, or {@code null} if the
     *         issuer is equal to the CRL issuer.
     */
    public X500Principal getCertificateIssuer() {
        return null;
    }

    /**
     * Returns the date when the certificate is revoked.
     *
     * @return the date when the certificate is revoked.
     */
    public abstract Date getRevocationDate();

    /**
     * Returns whether this CRL entry has extensions.
     *
     * @return {@code true} is this CRL entry has extensions, otherwise {@code
     *         false}.
     */
    public abstract boolean hasExtensions();

    /**
     * Returns a string representation of this instance.
     *
     * @return a string representation of this instance.
     */
    public abstract String toString();
}

