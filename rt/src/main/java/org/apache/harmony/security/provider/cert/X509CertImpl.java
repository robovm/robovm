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
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.x509.Certificate;
import org.apache.harmony.security.x509.Extension;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.TBSCertificate;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSignature;

/**
 * This class is an implementation of X509Certificate. It wraps
 * the instance of org.apache.harmony.security.x509.Certificate
 * built on the base of provided ASN.1 DER encoded form of
 * Certificate structure (as specified in RFC 3280
 * http://www.ietf.org/rfc/rfc3280.txt).
 * @see org.apache.harmony.security.x509.Certificate
 * @see java.security.cert.X509Certificate
 */
public final class X509CertImpl extends X509Certificate {

    /** @serial */
    private static final long serialVersionUID = 2972248729446736154L;

    /** the core object to be wrapped in X509Certificate */
    private final Certificate certificate;

    // to speed up access to the info, the following fields
    // cache values retrieved from the certificate object,
    // initialized using the "single-check idiom".
    private final TBSCertificate tbsCert;
    private final Extensions extensions;
    private volatile long notBefore = -1;
    private volatile long notAfter = -1;
    private volatile BigInteger serialNumber;
    private volatile X500Principal issuer;
    private volatile X500Principal subject;
    private volatile byte[] tbsCertificate;
    private volatile byte[] signature;
    private volatile String sigAlgName;
    private volatile String sigAlgOID;
    private volatile byte[] sigAlgParams;
    // indicates whether the signature algorithm parameters are null
    private volatile boolean nullSigAlgParams;
    private volatile PublicKey publicKey;

    // encoding of the certificate
    private volatile byte[] encoding;

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

    public void checkValidity()
            throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(System.currentTimeMillis());
    }

    public void checkValidity(Date date)
            throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(date.getTime());
    }

    private void checkValidity(long time)
            throws CertificateExpiredException, CertificateNotYetValidException {
        if (time < getNotBeforeInternal()) {
            throw new CertificateNotYetValidException("current time: " + new Date(time)
                + ", validation time: " + new Date(getNotBeforeInternal()));
        }
        if (time > getNotAfterInternal()) {
            throw new CertificateExpiredException("current time: " + new Date(time)
                + ", expiration time: " + new Date(getNotAfterInternal()));
        }
    }

    public int getVersion() {
        return tbsCert.getVersion() + 1;
    }

    public BigInteger getSerialNumber() {
        BigInteger result = serialNumber;
        if (result == null) {
            serialNumber = result = tbsCert.getSerialNumber();
        }
        return result;
    }

    public Principal getIssuerDN() {
        return getIssuerX500Principal();
    }

    public X500Principal getIssuerX500Principal() {
        X500Principal result = issuer;
        if (result == null) {
            // retrieve the issuer's principal
            issuer = result = tbsCert.getIssuer().getX500Principal();
        }
        return result;
    }

    public Principal getSubjectDN() {
        return getSubjectX500Principal();
    }

    public X500Principal getSubjectX500Principal() {
        X500Principal result = subject;
        if (result == null) {
            // retrieve the subject's principal
            subject = result = tbsCert.getSubject().getX500Principal();
        }
        return result;
    }

    public Date getNotBefore() {
        return new Date(getNotBeforeInternal());
    }

    private long getNotBeforeInternal() {
        long result = notBefore;
        if (result == -1) {
            notBefore = result = tbsCert.getValidity().getNotBefore().getTime();
        }
        return result;
    }

    public Date getNotAfter() {
        return new Date(getNotAfterInternal());
    }

    private long getNotAfterInternal() {
        long result = notAfter;
        if (result == -1) {
            notAfter = result = tbsCert.getValidity().getNotAfter().getTime();
        }
        return result;
    }

    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return getTbsCertificateInternal().clone();
    }

    private byte[] getTbsCertificateInternal() {
        byte[] result = tbsCertificate;
        if (result == null) {
            tbsCertificate = result = tbsCert.getEncoded();
        }
        return result;
    }

    public byte[] getSignature() {
        return getSignatureInternal().clone();
    }

    private byte[] getSignatureInternal() {
        byte[] result = signature;
        if (result == null) {
            signature = result = certificate.getSignatureValue();
        }
        return result;
    }

    public String getSigAlgName() {
        String result = sigAlgName;
        if (result == null) {
            String sigAlgOIDLocal = getSigAlgOID();
            // retrieve the name of the signing algorithm
            result = AlgNameMapper.map2AlgName(sigAlgOIDLocal);
            if (result == null) {
                // if could not be found, use OID as a name
                result = sigAlgOIDLocal;
            }
            sigAlgName = result;
        }
        return result;
    }

    public String getSigAlgOID() {
        String result = sigAlgOID;
        if (result == null) {
            // if info was not retrieved (and cached), do it:
            sigAlgOID = result = tbsCert.getSignature().getAlgorithm();
        }
        return result;
    }

    public byte[] getSigAlgParams() {
        if (nullSigAlgParams) {
            return null;
        }
        byte[] result = sigAlgParams;
        if (result == null) {
            result = tbsCert.getSignature().getParameters();
            if (result == null) {
                nullSigAlgParams = true;
                return null;
            }
            sigAlgParams = result;
        }
        return result;
    }

    public boolean[] getIssuerUniqueID() {
        return tbsCert.getIssuerUniqueID();
    }

    public boolean[] getSubjectUniqueID() {
        return tbsCert.getSubjectUniqueID();
    }

    public boolean[] getKeyUsage() {
        if (extensions == null) {
            return null;
        }
        return extensions.valueOfKeyUsage();
    }

    public List<String> getExtendedKeyUsage()
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

    public int getBasicConstraints() {
        if (extensions == null) {
            return Integer.MAX_VALUE;
        }
        return extensions.valueOfBasicConstrains();
    }

    public Collection<List<?>> getSubjectAlternativeNames() throws CertificateParsingException {
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
    public Collection<List<?>> getIssuerAlternativeNames() throws CertificateParsingException {
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

    @Override public byte[] getEncoded() throws CertificateEncodingException {
        return getEncodedInternal().clone();
    }
    private byte[] getEncodedInternal() throws CertificateEncodingException {
        byte[] result = encoding;
        if (encoding == null) {
            encoding = result = certificate.getEncoded();
        }
        return result;
    }

    @Override public PublicKey getPublicKey() {
        PublicKey result = publicKey;
        if (result == null) {
            publicKey = result = tbsCert.getSubjectPublicKeyInfo().getPublicKey();
        }
        return result;
    }

    @Override public String toString() {
        return certificate.toString();
    }

    @Override public void verify(PublicKey key)
            throws CertificateException, NoSuchAlgorithmException, InvalidKeyException,
            NoSuchProviderException, SignatureException {

        Signature signature;
        try {
            signature = OpenSSLSignature.getInstance(getSigAlgName());
        } catch (NoSuchAlgorithmException ignored) {
            signature = Signature.getInstance(getSigAlgName());
        }
        signature.initVerify(key);
        // retrieve the encoding of the TBSCertificate structure
        byte[] tbsCertificateLocal = getTbsCertificateInternal();
        // compute and verify the signature
        signature.update(tbsCertificateLocal, 0, tbsCertificateLocal.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException("Signature was not verified");
        }
    }

    @Override public void verify(PublicKey key, String sigProvider)
            throws CertificateException, NoSuchAlgorithmException, InvalidKeyException,
            NoSuchProviderException, SignatureException {

        Signature signature;
        try {
            if (sigProvider == null) {
                signature = OpenSSLSignature.getInstance(getSigAlgName());
            } else {
                signature = Signature.getInstance(getSigAlgName(), sigProvider);
            }
        } catch (NoSuchAlgorithmException ignored) {
            signature = Signature.getInstance(getSigAlgName(), sigProvider);
        }
        signature.initVerify(key);
        // retrieve the encoding of the TBSCertificate structure
        byte[] tbsCertificateLocal = getTbsCertificateInternal();
        // compute and verify the signature
        signature.update(tbsCertificateLocal, 0, tbsCertificateLocal.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException("Signature was not verified");
        }
    }

    @Override public Set<String> getNonCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        return extensions.getNonCriticalExtensions();
    }

    @Override public Set<String> getCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        return extensions.getCriticalExtensions();
    }

    @Override public byte[] getExtensionValue(String oid) {
        if (extensions == null) {
            return null;
        }
        // retrieve the info from the cached extensions object
        Extension ext = extensions.getExtensionByOID(oid);
        return (ext == null) ? null : ext.getRawExtnValue();
    }

    @Override public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        // retrieve the info from the cached extensions object
        return extensions.hasUnsupportedCritical();
    }

}
