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
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.internal.nls.Messages;
import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.Certificate;
import org.apache.harmony.security.x509.Extension;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.TBSCertificate;

/**
 * This class is an implementation of X509Certificate. It wraps
 * the instance of org.apache.harmony.security.x509.Certificate
 * built on the base of provided ASN.1 DER encoded form of
 * Certificate structure (as specified in RFC 3280
 * http://www.ietf.org/rfc/rfc3280.txt).
 * @see org.apache.harmony.security.x509.Certificate
 * @see java.security.cert.X509Certificate
 */
public class X509CertImpl extends X509Certificate {

    /**
     * @serial
     */
    private static final long serialVersionUID = 2972248729446736154L;

    // the core object to be wrapped in X509Certificate
    private final Certificate certificate;

    // to speed up access to the info, the following fields
    // cache values retrieved from the certificate object
    private final TBSCertificate tbsCert;
    private final Extensions extensions;
    private long notBefore = -1;
    private long notAfter;
    private BigInteger serialNumber;
    private X500Principal issuer;
    private X500Principal subject;
    private byte[] tbsCertificate;
    private byte[] signature;
    private String sigAlgName;
    private String sigAlgOID;
    private byte[] sigAlgParams;
    // indicates whether the signature algorithm parameters are null
    private boolean nullSigAlgParams;
    private PublicKey publicKey;

    // encoding of the certificate
    private byte[] encoding;

    //
    // ---------------------- Constructors -------------------------------
    //

    /**
     * Constructs the instance on the base of ASN.1 encoded
     * form of X.509 certificate provided via stream parameter.
     * @param in input stream containing ASN.1 encoded form of certificate.
     * @throws CertificateException if some decoding problems occur.
     */
    public X509CertImpl(InputStream in) throws CertificateException {
        try {
            // decode the Certificate object
            this.certificate = (Certificate) Certificate.ASN1.decode(in);
            // cache the values of TBSCertificate and Extensions
            this.tbsCert = certificate.getTbsCertificate();
            this.extensions = tbsCert.getExtensions();
        } catch (IOException e) {
            throw new CertificateException(e);
        }
    }

    /**
     * Constructs the instance on the base of existing Certificate object to
     * be wrapped.
     */
    public X509CertImpl(Certificate certificate) {
        this.certificate = certificate;
        // cache the values of TBSCertificate and Extensions
        this.tbsCert = certificate.getTbsCertificate();
        this.extensions = tbsCert.getExtensions();
    }

    /**
     * Constructs the instance on the base of ASN.1 encoded
     * form of X.509 certificate provided via array of bytes.
     * @param encoding byte array containing ASN.1 encoded form of certificate.
     * @throws IOException if some decoding problems occur.
     */
    public X509CertImpl(byte[] encoding) throws IOException {
        this((Certificate) Certificate.ASN1.decode(encoding));
    }

    //
    // ----------------- Public methods implementations ------------------
    //

    /**
     * @see java.security.cert.X509Certificate#checkValidity()
     * method documentation for more information.
     */
    public void checkValidity() throws CertificateExpiredException,
                                       CertificateNotYetValidException {
        if (notBefore == -1) {
            // retrieve and cache the value of validity period
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        long time = System.currentTimeMillis();
        if (time < notBefore) {
            throw new CertificateNotYetValidException();
        }
        if (time > notAfter) {
            throw new CertificateExpiredException();
        }
    }

    /**
     * @see java.security.cert.X509Certificate#checkValidity(Date)
     * method documentation for more information.
     */
    public void checkValidity(Date date)
                                throws CertificateExpiredException,
                                       CertificateNotYetValidException {
        if (notBefore == -1) {
            // retrieve and cache the value of validity period
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        long time = date.getTime();
        if (time < notBefore) {
            throw new CertificateNotYetValidException();
        }
        if (time > notAfter) {
            throw new CertificateExpiredException();
        }
    }

    /**
     * @see java.security.cert.X509Certificate#getVersion()
     * method documentation for more information.
     */
    public int getVersion() {
        return tbsCert.getVersion() + 1;
    }

    /**
     * @see java.security.cert.X509Certificate#getSerialNumber()
     * method documentation for more information.
     */
    public BigInteger getSerialNumber() {
        if (serialNumber == null) {
            serialNumber = tbsCert.getSerialNumber();
        }
        return serialNumber;
    }

    /**
     * @see java.security.cert.X509Certificate#getIssuerDN()
     * method documentation for more information.
     */
    public Principal getIssuerDN() {
        if (issuer == null) {
            // retrieve the issuer's principal
            issuer = tbsCert.getIssuer().getX500Principal();
        }
        return issuer;
    }

    /**
     * @see java.security.cert.X509Certificate#getIssuerX500Principal()
     * method documentation for more information.
     */
    public X500Principal getIssuerX500Principal() {
        if (issuer == null) {
            // retrieve the issuer's principal
            issuer = tbsCert.getIssuer().getX500Principal();
        }
        return issuer;
    }

    /**
     * @see java.security.cert.X509Certificate#getSubjectDN()
     * method documentation for more information.
     */
    public Principal getSubjectDN() {
        if (subject == null) {
            // retrieve the subject's principal
            subject = tbsCert.getSubject().getX500Principal();
        }
        return subject;
    }

    /**
     * @see java.security.cert.X509Certificate#getSubjectX500Principal()
     * method documentation for more information.
     */
    public X500Principal getSubjectX500Principal() {
        if (subject == null) {
            // retrieve the subject's principal
            subject = tbsCert.getSubject().getX500Principal();
        }
        return subject;
    }

    /**
     * @see java.security.cert.X509Certificate#getNotBefore()
     * method documentation for more information.
     */
    public Date getNotBefore() {
        if (notBefore == -1) {
            // the value was not retrieved from the certificate, do it:
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        return new Date(notBefore);
    }

    /**
     * @see java.security.cert.X509Certificate#getNotAfter()
     * method documentation for more information.
     */
    public Date getNotAfter() {
        if (notBefore == -1) {
            // the value was not retrieved from the certificate, do it:
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        return new Date(notAfter);
    }

    /**
     * @see java.security.cert.X509Certificate#getTBSCertificate()
     * method documentation for more information.
     */
    public byte[] getTBSCertificate()
                        throws CertificateEncodingException {
        if (tbsCertificate == null) {
            // retrieve the encoded form of the TBSCertificate structure
            tbsCertificate = tbsCert.getEncoded();
        }
        byte[] result = new byte[tbsCertificate.length];
        System.arraycopy(tbsCertificate, 0, result, 0, tbsCertificate.length);
        return result;
    }

    /**
     * @see java.security.cert.X509Certificate#getSignature()
     * method documentation for more information.
     */
    public byte[] getSignature() {
        if (signature == null) {
            // retrieve the value of the signature
            signature = certificate.getSignatureValue();
        }
        byte[] result = new byte[signature.length];
        System.arraycopy(signature, 0, result, 0, signature.length);
        return result;
    }

    /**
     * @see java.security.cert.X509Certificate#getSigAlgName()
     * method documentation for more information.
     */
    public String getSigAlgName() {
        if (sigAlgOID == null) {
            // if info was not retrieved (and cached), do it:
            sigAlgOID = tbsCert.getSignature().getAlgorithm();
            // retrieve the name of the signing algorithm
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                // if could not be found, use OID as a name
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgName;
    }

    /**
     * @see java.security.cert.X509Certificate#getSigAlgOID()
     * method documentation for more information.
     */
    public String getSigAlgOID() {
        if (sigAlgOID == null) {
            // if info was not retrieved (and cached), do it:
            sigAlgOID = tbsCert.getSignature().getAlgorithm();
            // retrieve the name of the signing algorithm
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                // if could not be found, use OID as a name
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgOID;
    }

    /**
     * @see java.security.cert.X509Certificate#getSigAlgParams()
     * method documentation for more information.
     */
    public byte[] getSigAlgParams() {
        if (nullSigAlgParams) {
            return null;
        }
        if (sigAlgParams == null) {
            sigAlgParams = tbsCert.getSignature().getParameters();
            if (sigAlgParams == null) {
                nullSigAlgParams = true;
                return null;
            }
        }
        return sigAlgParams;
    }

    /**
     * @see java.security.cert.X509Certificate#getIssuerUniqueID()
     * method documentation for more information.
     */
    public boolean[] getIssuerUniqueID() {
        return tbsCert.getIssuerUniqueID();
    }

    /**
     * @see java.security.cert.X509Certificate#getSubjectUniqueID()
     * method documentation for more information.
     */
    public boolean[] getSubjectUniqueID() {
        return tbsCert.getSubjectUniqueID();
    }

    /**
     * @see java.security.cert.X509Certificate#getKeyUsage()
     * method documentation for more information.
     */
    public boolean[] getKeyUsage() {
        if (extensions == null) {
            return null;
        }
        return extensions.valueOfKeyUsage();
    }

    /**
     * @see java.security.cert.X509Certificate#getExtendedKeyUsage()
     * method documentation for more information.
     */
    public List/*<String>*/ getExtendedKeyUsage()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            return extensions.valueOfExtendedKeyUsage();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    /**
     * @see java.security.cert.X509Certificate#getBasicConstraints()
     * method documentation for more information.
     */
    public int getBasicConstraints() {
        if (extensions == null) {
            return Integer.MAX_VALUE;
        }
        return extensions.valueOfBasicConstrains();
    }

    /**
     * @see java.security.cert.X509Certificate#getSubjectAlternativeNames()
     * method documentation for more information.
     */
    public Collection/*<List<?>>*/ getSubjectAlternativeNames()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            // Retrieve the extension value from the cached extensions object
            // This extension is not checked for correctness during
            // certificate generation, so now it can throw exception
            return extensions.valueOfSubjectAlternativeName();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    /**
     * @see java.security.cert.X509Certificate#getIssuerAlternativeNames()
     * method documentation for more information.
     */
    public Collection/*FIXME <List<?>>*/ getIssuerAlternativeNames()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            // Retrieve the extension value from the cached extensions object
            // This extension is not checked for correctness during
            // certificate generation, so now it can throw exception
            return extensions.valueOfIssuerAlternativeName();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    //
    // ----- java.security.cert.Certificate methods implementations ------
    //

    /**
     * @see java.security.cert.Certificate#getEncoded()
     * method documentation for more information.
     */
    public byte[] getEncoded() throws CertificateEncodingException {
        if (encoding == null) {
            encoding = certificate.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    /**
     * @see java.security.cert.Certificate#getPublicKey()
     * method documentation for more information.
     */
    public PublicKey getPublicKey() {
        if (publicKey == null) {
            // retrieve the public key from SubjectPublicKeyInfo
            // substructure of X.509 certificate
            publicKey = tbsCert.getSubjectPublicKeyInfo().getPublicKey();
        }
        return publicKey;
    }

    /**
     * @see java.security.cert.Certificate#toString()
     * method documentation for more information.
     */
    public String toString() {
        return certificate.toString();
    }

    /**
     * Verifies the signature of the certificate.
     * @see java.security.cert.Certificate#verify(PublicKey)
     * method documentation for more information.
     */
    public void verify(PublicKey key)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException {
        Signature signature = Signature.getInstance(getSigAlgName());
        signature.initVerify(key);
        // retrieve the encoding of the TBSCertificate structure
        if (tbsCertificate == null) {
            tbsCertificate = tbsCert.getEncoded();
        }
        // compute and verify the signature
        signature.update(tbsCertificate, 0, tbsCertificate.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException(Messages.getString("security.15C")); //$NON-NLS-1$
        }
    }

    /**
     * Verifies the signature of the certificate.
     * @see java.security.cert.Certificate#verify(PublicKey,String)
     * method documentation for more information.
     */
    public void verify(PublicKey key, String sigProvider)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException {
        Signature signature =
            Signature.getInstance(getSigAlgName(), sigProvider);
        signature.initVerify(key);
        // retrieve the encoding of the TBSCertificate structure
        if (tbsCertificate == null) {
            tbsCertificate = tbsCert.getEncoded();
        }
        // compute and verify the signature
        signature.update(tbsCertificate, 0, tbsCertificate.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException(Messages.getString("security.15C")); //$NON-NLS-1$
        }
    }

    //
    // ----- java.security.cert.X509Extension methods implementations ----
    //

    /**
     * @see java.security.cert.X509Extension#getNonCriticalExtensionOIDs()
     * method documentation for more information.
     */
    public Set getNonCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        return extensions.getNonCriticalExtensions();
    }

    /**
     * @see java.security.cert.X509Extension#getCriticalExtensionOIDs()
     * method documentation for more information.
     */
    public Set getCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        return extensions.getCriticalExtensions();
    }

    /**
     * @see java.security.cert.X509Extension#getExtensionValue(String)
     * method documentation for more information.
     */
    public byte[] getExtensionValue(String oid) {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        Extension ext = extensions.getExtensionByOID(oid);
        return (ext == null) ? null : ext.getRawExtnValue();
    }

    /**
     * @see java.security.cert.X509Extension#hasUnsupportedCriticalExtension()
     * method documentation for more information.
     */
    public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        // retrieve the info from the cached extensions object
        return extensions.hasUnsupportedCritical();
    }

}

