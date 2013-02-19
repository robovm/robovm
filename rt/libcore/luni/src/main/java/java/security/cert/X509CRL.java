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

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/**
 * Abstract base class for X.509 certificate revocation lists (CRL).
 * <p>
 * More information regarding CRL can be found in RFC 2459,
 * "Internet X.509 Public Key Infrastructure Certificate and CRL Profile" at <a
 * href
 * ="http://www.ietf.org/rfc/rfc2459.txt">http://www.ietf.org/rfc/rfc2459.txt
 * </a>.
 */
public abstract class X509CRL extends CRL implements X509Extension {

    /**
     * Creates a new {@code X509CRL} instance.
     */
    protected X509CRL() {
        super("X.509");
    }

    /**
     * Returns whether the specified object equals to this instance.
     *
     * @param other
     *            the object to compare.
     * @return {@code true} if the specified object is equal to this, otherwise
     *         {@code false}.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof X509CRL)) {
            return false;
        }
        X509CRL obj = (X509CRL) other;
        try {
            return Arrays.equals(getEncoded(), obj.getEncoded());
        } catch (CRLException e) {
            return false;
        }
    }

    /**
     * Returns the hashcode of this CRL instance.
     *
     * @return the hashcode.
     */
    public int hashCode() {
        try {
            int res = 0;
            byte[] array = getEncoded();
            for (int i=0; i<array.length; i++) {
                res += array[i] & 0xFF;
            }
            return res;
        } catch (CRLException e) {
            return 0;
        }
    }

    /**
     * Returns this CRL in ASN.1 DER encoded form.
     *
     * @return this CRL in ASN.1 DER encoded form.
     * @throws CRLException
     *             if encoding fails.
     */
    public abstract byte[] getEncoded() throws CRLException;


    /**
     * Verifies this CRL by verifying that this CRL was signed with the
     * corresponding private key to the specified public key.
     *
     * @param key
     *            the public key to verify this CRL with.
     * @throws CRLException
     *             if encoding or decoding fails.
     * @throws NoSuchAlgorithmException
     *             if a needed algorithm is not present.
     * @throws InvalidKeyException
     *             if the specified key is invalid.
     * @throws NoSuchProviderException
     *             if no provider can be found.
     * @throws SignatureException
     *             if errors occur on signatures.
     */
    public abstract void verify(PublicKey key)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException;

    /**
     * Verifies this CRL by verifying that this CRL was signed with the
     * corresponding private key to the specified public key. The signature
     * verification engine of the specified provider will be used.
     *
     * @param key
     *            the public key to verify this CRL with.
     * @param sigProvider
     *            the name of the provider for the signature algorithm.
     * @throws CRLException
     *             if encoding decoding fails.
     * @throws NoSuchAlgorithmException
     *             if a needed algorithm is not present.
     * @throws InvalidKeyException
     *             if the specified key is invalid.
     * @throws NoSuchProviderException
     *             if the specified provider cannot be found.
     * @throws SignatureException
     *             if errors occur on signatures.
     */
    public abstract void verify(PublicKey key, String sigProvider)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException;

    /**
     * Returns the version number of this CRL.
     *
     * @return the version number of this CRL.
     */
    public abstract int getVersion();

    /**
     * <b>Do not use</b>, use {@link #getIssuerX500Principal()} instead. Returns
     * the issuer as an implementation specific Principal object.
     *
     * @return the issuer distinguished name.
     */
    public abstract Principal getIssuerDN();

    /**
     * Returns the issuer distinguished name of this CRL.
     *
     * @return the issuer distinguished name of this CRL.
     */
    public X500Principal getIssuerX500Principal() {
        try {
            // TODO if there is no X.509 certificate provider installed
            // should we try to access Harmony X509CRLImpl via classForName?
            CertificateFactory factory = CertificateFactory
                    .getInstance("X.509");

            X509CRL crl = (X509CRL) factory
                    .generateCRL(new ByteArrayInputStream(getEncoded()));

            return crl.getIssuerX500Principal();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get X500Principal issuer", e);
        }
    }

    /**
     * Returns the {@code thisUpdate} value of this CRL.
     *
     * @return the {@code thisUpdate} value of this CRL.
     */
    public abstract Date getThisUpdate();

    /**
     * Returns the {@code nextUpdate} value of this CRL.
     *
     * @return the {@code nextUpdate} value of this CRL, or {@code null} if none
     *         is present.
     */
    public abstract Date getNextUpdate();

    /**
     * Returns the CRL entry with the specified certificate serial number.
     *
     * @param serialNumber
     *            the certificate serial number to search for a CRL entry.
     * @return the entry for the specified certificate serial number, or {@code
     *         null} if not found.
     */
    public abstract X509CRLEntry getRevokedCertificate(BigInteger serialNumber);

    /**
     * Returns the CRL entry for the specified certificate.
     *
     * @param certificate
     *            the certificate to search a CRL entry for.
     * @return the entry for the specified certificate, or {@code null} if not
     *         found.
     */
    public X509CRLEntry getRevokedCertificate(X509Certificate certificate) {
        if (certificate == null) {
            throw new NullPointerException();
        }
        return getRevokedCertificate(certificate.getSerialNumber());
    }

    /**
     * Returns the set of revoked certificates.
     *
     * @return the set of revoked certificates, or {@code null} if no revoked
     *         certificates are in this CRL.
     */
    public abstract Set<? extends X509CRLEntry> getRevokedCertificates();

    /**
     * Returns the {@code tbsCertList} information of this CRL in DER encoded
     * form.
     *
     * @return the CRL information in DER encoded form.
     * @throws CRLException
     *             if encoding fails.
     */
    public abstract byte[] getTBSCertList() throws CRLException;

    /**
     * Returns the signature bytes of this CRL.
     *
     * @return the signature bytes of this CRL.
     */
    public abstract byte[] getSignature();

    /**
     * Returns the name of the signature algorithm.
     *
     * @return the name of the signature algorithm.
     */
    public abstract String getSigAlgName();

    /**
     * Returns the OID of the signature algorithm.
     *
     * @return the OID of the signature algorithm.
     */
    public abstract String getSigAlgOID();

    /**
     * Returns the parameters of the signature algorithm in DER encoded form.
     *
     * @return the parameters of the signature algorithm in DER encoded form, or
     *         {@code null} if not present.
     */
    public abstract byte[] getSigAlgParams();
}
