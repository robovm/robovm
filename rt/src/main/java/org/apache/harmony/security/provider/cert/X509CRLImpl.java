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

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.CertificateList;
import org.apache.harmony.security.x509.Extension;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.TBSCertList;

/**
 * This class is an implementation of X509CRL. It wraps
 * the instance of org.apache.harmony.security.x509.CertificateList
 * built on the base of provided ASN.1 DER encoded form of
 * CertificateList structure (as specified in RFC 3280
 * http://www.ietf.org/rfc/rfc3280.txt).
 * Implementation supports work with indirect CRLs.
 * @see org.apache.harmony.security.x509.CertificateList
 * @see java.security.cert.X509CRL
 */
public class X509CRLImpl extends X509CRL {

    // the core object to be wrapped in X509CRL
    private final CertificateList crl;

    // To speed up access to the info, the following fields
    // cache values retrieved from the CertificateList object
    private final TBSCertList tbsCertList;
    private byte[] tbsCertListEncoding;
    private final Extensions extensions;
    private X500Principal issuer;
    private ArrayList entries;
    private int entriesSize;
    private byte[] signature;
    private String sigAlgOID;
    private String sigAlgName;
    private byte[] sigAlgParams;

    // encoded form of crl
    private byte[] encoding;

    // indicates whether the signature algorithm parameters are null
    private boolean nullSigAlgParams;
    // indicates whether the crl entries have already been retrieved
    // from CertificateList object (crl)
    private boolean entriesRetrieved;

    // indicates whether this X.509 CRL is direct or indirect
    // (see rfc 3280 http://www.ietf.org/rfc/rfc3280.txt, p 5.)
    private boolean isIndirectCRL;
    // if crl is indirect, this field holds an info about how
    // many of the leading certificates in the list are issued
    // by the same issuer as CRL.
    private int nonIndirectEntriesSize;

    /**
     * Creates X.509 CRL by wrapping of the specified CertificateList object.
     */
    public X509CRLImpl(CertificateList crl) {
        this.crl = crl;
        this.tbsCertList = crl.getTbsCertList();
        this.extensions = tbsCertList.getCrlExtensions();
    }

    /**
     * Creates X.509 CRL on the base of ASN.1 DER encoded form of
     * the CRL (CertificateList structure described in RFC 3280)
     * provided via input stream.
     * @throws CRLException if decoding errors occur.
     */
    public X509CRLImpl(InputStream in) throws CRLException {
        try {
            // decode CertificateList structure
            this.crl = (CertificateList) CertificateList.ASN1.decode(in);
            this.tbsCertList = crl.getTbsCertList();
            this.extensions = tbsCertList.getCrlExtensions();
        } catch (IOException e) {
            throw new CRLException(e);
        }
    }

    /**
     * Creates X.509 CRL on the base of ASN.1 DER encoded form of
     * the CRL (CertificateList structure described in RFC 3280)
     * provided via array of bytes.
     * @throws IOException if decoding errors occur.
     */
    public X509CRLImpl(byte[] encoding) throws IOException {
        this((CertificateList) CertificateList.ASN1.decode(encoding));
    }

    // ---------------------------------------------------------------------
    // ----- java.security.cert.X509CRL abstract method implementations ----
    // ---------------------------------------------------------------------

    /**
     * @see java.security.cert.X509CRL#getEncoded()
     * method documentation for more info
     */
    public byte[] getEncoded() throws CRLException {
        if (encoding == null) {
            encoding = crl.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    /**
     * @see java.security.cert.X509CRL#getVersion()
     * method documentation for more info
     */
    public int getVersion() {
        return tbsCertList.getVersion();
    }

    /**
     * @see java.security.cert.X509CRL#getIssuerDN()
     * method documentation for more info
     */
    public Principal getIssuerDN() {
        if (issuer == null) {
            issuer = tbsCertList.getIssuer().getX500Principal();
        }
        return issuer;
    }

    /**
     * @see java.security.cert.X509CRL#getIssuerX500Principal()
     * method documentation for more info
     */
    public X500Principal getIssuerX500Principal() {
        if (issuer == null) {
            issuer = tbsCertList.getIssuer().getX500Principal();
        }
        return issuer;
    }

    /**
     * @see java.security.cert.X509CRL#getThisUpdate()
     * method documentation for more info
     */
    public Date getThisUpdate() {
        return tbsCertList.getThisUpdate();
    }

    /**
     * @see java.security.cert.X509CRL#getNextUpdate()
     * method documentation for more info
     */
    public Date getNextUpdate() {
        return tbsCertList.getNextUpdate();
    }

    /*
     * Retrieves the crl entries (TBSCertList.RevokedCertificate objects)
     * from the TBSCertList structure and converts them to the
     * X509CRLEntryImpl objects
     */
    private void retrieveEntries() {
        entriesRetrieved = true;
        List rcerts = tbsCertList.getRevokedCertificates();
        if (rcerts == null) {
            return;
        }
        entriesSize = rcerts.size();
        entries = new ArrayList(entriesSize);
        // null means that revoked certificate issuer is the same as CRL issuer
        X500Principal rcertIssuer = null;
        for (int i=0; i<entriesSize; i++) {
            TBSCertList.RevokedCertificate rcert =
                (TBSCertList.RevokedCertificate) rcerts.get(i);
            X500Principal iss = rcert.getIssuer();
            if (iss != null) {
                // certificate issuer differs from CRL issuer
                // and CRL is indirect.
                rcertIssuer = iss;
                isIndirectCRL = true;
                // remember how many leading revoked certificates in the
                // list are issued by the same issuer as issuer of CRL
                // (these certificates are first in the list)
                nonIndirectEntriesSize = i;
            }
            entries.add(new X509CRLEntryImpl(rcert, rcertIssuer));
        }
    }

    /**
     * Searches for certificate in CRL.
     * This method supports indirect CRLs: if CRL is indirect method takes
     * into account serial number and issuer of the certificate,
     * if CRL issued by CA (i.e. it is not indirect) search is done only
     * by serial number of the specified certificate.
     * @see java.security.cert.X509CRL#getRevokedCertificate(X509Certificate)
     * method documentation for more info
     */
    public X509CRLEntry getRevokedCertificate(X509Certificate certificate) {
        if (certificate == null) {
            throw new NullPointerException();
        }
        if (!entriesRetrieved) {
            retrieveEntries();
        }
        if (entries == null) {
            return null;
        }
        BigInteger serialN = certificate.getSerialNumber();
        if (isIndirectCRL) {
            // search in indirect crl
            X500Principal certIssuer = certificate.getIssuerX500Principal();
            if (certIssuer.equals(getIssuerX500Principal())) {
                // certificate issuer is CRL issuer
                certIssuer = null;
            }
            for (int i=0; i<entriesSize; i++) {
                X509CRLEntry entry = (X509CRLEntry) entries.get(i);
                // check the serial number of revoked certificate
                if (serialN.equals(entry.getSerialNumber())) {
                    // revoked certificate issuer
                    X500Principal iss = entry.getCertificateIssuer();
                    // check the issuer of revoked certificate
                    if (certIssuer != null) {
                        // certificate issuer is not a CRL issuer, so
                        // check issuers for equality
                        if (certIssuer.equals(iss)) {
                            return entry;
                        }
                    } else if (iss == null) {
                        // both certificates was issued by CRL issuer
                        return entry;
                    }
                }
            }
        } else {
            // search in CA's (non indirect) crl: just look up the serial number
            for (int i=0; i<entriesSize; i++) {
                X509CRLEntry entry = (X509CRLEntry) entries.get(i);
                if (serialN.equals(entry.getSerialNumber())) {
                    return entry;
                }
            }
        }
        return null;
    }

    /**
     * Method searches for CRL entry with specified serial number.
     * The method will search only certificate issued by CRL's issuer.
     * @see java.security.cert.X509CRL#getRevokedCertificate(BigInteger)
     * method documentation for more info
     */
    public X509CRLEntry getRevokedCertificate(BigInteger serialNumber) {
        if (!entriesRetrieved) {
            retrieveEntries();
        }
        if (entries == null) {
            return null;
        }
        for (int i=0; i<nonIndirectEntriesSize; i++) {
            X509CRLEntry entry = (X509CRLEntry) entries.get(i);
            if (serialNumber.equals(entry.getSerialNumber())) {
                return entry;
            }
        }
        return null;
    }

    /**
     * @see java.security.cert.X509CRL#getRevokedCertificates()
     * method documentation for more info
     */
    public Set<? extends X509CRLEntry> getRevokedCertificates() {
        if (!entriesRetrieved) {
            retrieveEntries();
        }
        if (entries == null) {
            return null;
        }
        return new HashSet(entries);
    }

    /**
     * @see java.security.cert.X509CRL#getTBSCertList()
     * method documentation for more info
     */
    public byte[] getTBSCertList() throws CRLException {
        if (tbsCertListEncoding == null) {
            tbsCertListEncoding = tbsCertList.getEncoded();
        }
        byte[] result = new byte[tbsCertListEncoding.length];
        System.arraycopy(tbsCertListEncoding, 0,
                result, 0, tbsCertListEncoding.length);
        return result;
    }

    /**
     * @see java.security.cert.X509CRL#getSignature()
     * method documentation for more info
     */
    public byte[] getSignature() {
        if (signature == null) {
            signature = crl.getSignatureValue();
        }
        byte[] result = new byte[signature.length];
        System.arraycopy(signature, 0, result, 0, signature.length);
        return result;
    }

    /**
     * @see java.security.cert.X509CRL#getSigAlgName()
     * method documentation for more info
     */
    public String getSigAlgName() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCertList.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgName;
    }

    /**
     * @see java.security.cert.X509CRL#getSigAlgOID()
     * method documentation for more info
     */
    public String getSigAlgOID() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCertList.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgOID;
    }

    /**
     * @see java.security.cert.X509CRL#getSigAlgParams()
     * method documentation for more info
     */
    public byte[] getSigAlgParams() {
        if (nullSigAlgParams) {
            return null;
        }
        if (sigAlgParams == null) {
            sigAlgParams = tbsCertList.getSignature().getParameters();
            if (sigAlgParams == null) {
                nullSigAlgParams = true;
                return null;
            }
        }
        return sigAlgParams;
    }

    /**
     * @see java.security.cert.X509CRL#verify(PublicKey key)
     * method documentation for more info
     */
    public void verify(PublicKey key)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException {
        Signature signature = Signature.getInstance(getSigAlgName());
        signature.initVerify(key);
        byte[] tbsEncoding = tbsCertList.getEncoded();
        signature.update(tbsEncoding, 0, tbsEncoding.length);
        if (!signature.verify(crl.getSignatureValue())) {
            throw new SignatureException("Signature was not verified");
        }
    }

    /**
     * @see java.security.cert.X509CRL#verify(PublicKey key, String sigProvider)
     * method documentation for more info
     */
    public void verify(PublicKey key, String sigProvider)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException {
        Signature signature = Signature.getInstance(
                                            getSigAlgName(), sigProvider);
        signature.initVerify(key);
        byte[] tbsEncoding = tbsCertList.getEncoded();
        signature.update(tbsEncoding, 0, tbsEncoding.length);
        if (!signature.verify(crl.getSignatureValue())) {
            throw new SignatureException("Signature was not verified");
        }
    }

    // ---------------------------------------------------------------------
    // ------ java.security.cert.CRL abstract method implementations -------
    // ---------------------------------------------------------------------

    /**
     * @see java.security.cert.CRL#isRevoked(Certificate)
     * method documentation for more info
     */
    public boolean isRevoked(Certificate cert) {
        if (!(cert instanceof X509Certificate)) {
            return false;
        }
        return getRevokedCertificate((X509Certificate) cert) != null;
    }

    /**
     * @see java.security.cert.CRL#toString()
     * method documentation for more info
     */
    public String toString() {
        return crl.toString();
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
