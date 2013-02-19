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

package org.apache.harmony.security.provider.cert;

import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.x509.Extension;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.TBSCertList;

/**
 * Implementation of X509CRLEntry. It wraps the instance
 * of org.apache.harmony.security.x509.TBSCertList.RevokedCertificate
 * obtained during the decoding of TBSCertList substructure
 * of the CertificateList structure which is an X.509 form of CRL.
 * (see RFC 3280 at http://www.ietf.org/rfc/rfc3280.txt)
 * Normally the instances of this class are constructed by involving
 * X509CRLImpl object.
 * @see org.apache.harmony.security.x509.TBSCertList
 * @see org.apache.harmony.security.provider.cert.X509CRLImpl
 * @see java.security.cert.X509CRLEntry
 */
public class X509CRLEntryImpl extends X509CRLEntry {

    // the crl entry object to be wrapped in X509CRLEntry
    private final TBSCertList.RevokedCertificate rcert;
    // the extensions of the entry
    private final Extensions extensions;
    // issuer of the revoked certificate described by this crl entry
    private final X500Principal issuer;

    // encoded form of this revoked certificate entry
    private byte[] encoding;

    /**
     * Creates an instance on the base of existing
     * <code>TBSCertList.RevokedCertificate</code> object and
     * information about the issuer of revoked certificate.
     * If specified issuer is null, it is supposed that issuer
     * of the revoked certificate is the same as for involving CRL.
     */
    public X509CRLEntryImpl(TBSCertList.RevokedCertificate rcert,
            X500Principal issuer) {
        this.rcert = rcert;
        this.extensions = rcert.getCrlEntryExtensions();
        this.issuer = issuer;
    }

    // ---------------------------------------------------------------------
    // ------ java.security.cert.X509CRLEntry method implementations -------
    // ---------------------------------------------------------------------

    /**
     * @see java.security.cert.X509CRLEntry#getEncoded()
     * method documentation for more info
     */
    public byte[] getEncoded() throws CRLException {
        if (encoding == null) {
            encoding = rcert.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    /**
     * @see java.security.cert.X509CRLEntry#getSerialNumber()
     * method documentation for more info
     */
    public BigInteger getSerialNumber() {
        return rcert.getUserCertificate();
    }

    /**
     * @see java.security.cert.X509CRLEntry#getCertificateIssuer()
     * method documentation for more info
     */
    public X500Principal getCertificateIssuer() {
        return issuer;
    }

    /**
     * @see java.security.cert.X509CRLEntry#getRevocationDate()
     * method documentation for more info
     */
    public Date getRevocationDate() {
        return rcert.getRevocationDate();
    }

    /**
     * @see java.security.cert.X509CRLEntry#hasExtensions()
     * method documentation for more info
     */
    public boolean hasExtensions() {
        return (extensions != null) && (extensions.size() != 0);
    }

    /**
     * @see java.security.cert.X509CRLEntry#toString()
     * method documentation for more info
     */
    public String toString() {
        return "X509CRLEntryImpl: "+rcert.toString();
    }

    // ---------------------------------------------------------------------
    // ------ java.security.cert.X509Extension method implementations ------
    // ---------------------------------------------------------------------

    /**
     * @see java.security.cert.X509Extension#getNonCriticalExtensionOIDs()
     * method documentation for more info
     */
    public Set getNonCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getNonCriticalExtensions();
    }

    /**
     * @see java.security.cert.X509Extension#getCriticalExtensionOIDs()
     * method documentation for more info
     */
    public Set getCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getCriticalExtensions();
    }

    /**
     * @see java.security.cert.X509Extension#getExtensionValue(String)
     * method documentation for more info
     */
    public byte[] getExtensionValue(String oid) {
        if (extensions == null) {
            return null;
        }
        Extension ext = extensions.getExtensionByOID(oid);
        return (ext == null) ? null : ext.getRawExtnValue();
    }

    /**
     * @see java.security.cert.X509Extension#hasUnsupportedCriticalExtension()
     * method documentation for more info
     */
    public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        return extensions.hasUnsupportedCritical();
    }
}

