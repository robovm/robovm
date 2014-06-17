/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.conscrypt;

import org.apache.harmony.security.utils.AlgNameMapper;
import org.conscrypt.OpenSSLX509CertificateFactory.ParsingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.security.auth.x500.X500Principal;

public class OpenSSLX509CRL extends X509CRL {
    private final long mContext;

    private OpenSSLX509CRL(long ctx) {
        mContext = ctx;
    }

    public static OpenSSLX509CRL fromX509DerInputStream(InputStream is) throws ParsingException {
        final OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(is);

        try {
            final long crlCtx = NativeCrypto.d2i_X509_CRL_bio(bis.getBioContext());
            if (crlCtx == 0) {
                return null;
            }
            return new OpenSSLX509CRL(crlCtx);
        } catch (Exception e) {
            throw new ParsingException(e);
        } finally {
            NativeCrypto.BIO_free(bis.getBioContext());
        }
    }

    public static List<OpenSSLX509CRL> fromPkcs7DerInputStream(InputStream is)
            throws ParsingException {
        OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(is);

        final long[] certRefs;
        try {
            certRefs = NativeCrypto.d2i_PKCS7_bio(bis.getBioContext(), NativeCrypto.PKCS7_CRLS);
        } catch (Exception e) {
            throw new ParsingException(e);
        } finally {
            NativeCrypto.BIO_free(bis.getBioContext());
        }

        final List<OpenSSLX509CRL> certs = new ArrayList<OpenSSLX509CRL>(certRefs.length);
        for (int i = 0; i < certRefs.length; i++) {
            if (certRefs[i] == 0) {
                continue;
            }
            certs.add(new OpenSSLX509CRL(certRefs[i]));
        }
        return certs;
    }

    public static OpenSSLX509CRL fromX509PemInputStream(InputStream is) throws ParsingException {
        final OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(is);

        try {
            final long crlCtx = NativeCrypto.PEM_read_bio_X509_CRL(bis.getBioContext());
            if (crlCtx == 0) {
                return null;
            }
            return new OpenSSLX509CRL(crlCtx);
        } catch (Exception e) {
            throw new ParsingException(e);
        } finally {
            NativeCrypto.BIO_free(bis.getBioContext());
        }
    }

    public static List<OpenSSLX509CRL> fromPkcs7PemInputStream(InputStream is)
            throws ParsingException {
        OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(is);

        final long[] certRefs;
        try {
            certRefs = NativeCrypto.PEM_read_bio_PKCS7(bis.getBioContext(),
                    NativeCrypto.PKCS7_CRLS);
        } catch (Exception e) {
            throw new ParsingException(e);
        } finally {
            NativeCrypto.BIO_free(bis.getBioContext());
        }

        final List<OpenSSLX509CRL> certs = new ArrayList<OpenSSLX509CRL>(certRefs.length);
        for (int i = 0; i < certRefs.length; i++) {
            if (certRefs[i] == 0) {
                continue;
            }
            certs.add(new OpenSSLX509CRL(certRefs[i]));
        }
        return certs;
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        String[] critOids =
                NativeCrypto.get_X509_CRL_ext_oids(mContext, NativeCrypto.EXTENSION_TYPE_CRITICAL);

        /*
         * This API has a special case that if there are no extensions, we
         * should return null. So if we have no critical extensions, we'll check
         * non-critical extensions.
         */
        if ((critOids.length == 0)
                && (NativeCrypto.get_X509_CRL_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_NON_CRITICAL).length == 0)) {
            return null;
        }

        return new HashSet<String>(Arrays.asList(critOids));
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return NativeCrypto.X509_CRL_get_ext_oid(mContext, oid);
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        String[] nonCritOids =
                NativeCrypto.get_X509_CRL_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_NON_CRITICAL);

        /*
         * This API has a special case that if there are no extensions, we
         * should return null. So if we have no non-critical extensions, we'll
         * check critical extensions.
         */
        if ((nonCritOids.length == 0)
                && (NativeCrypto.get_X509_CRL_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_CRITICAL).length == 0)) {
            return null;
        }

        return new HashSet<String>(Arrays.asList(nonCritOids));
    }

    @Override
    public boolean hasUnsupportedCriticalExtension() {
        final String[] criticalOids =
                NativeCrypto.get_X509_CRL_ext_oids(mContext, NativeCrypto.EXTENSION_TYPE_CRITICAL);
        for (String oid : criticalOids) {
            final long extensionRef = NativeCrypto.X509_CRL_get_ext(mContext, oid);
            if (NativeCrypto.X509_supported_extension(extensionRef) != 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public byte[] getEncoded() throws CRLException {
        return NativeCrypto.i2d_X509_CRL(mContext);
    }

    private void verifyOpenSSL(OpenSSLKey pkey) throws CRLException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchProviderException, SignatureException {
        NativeCrypto.X509_CRL_verify(mContext, pkey.getPkeyContext());
    }

    private void verifyInternal(PublicKey key, String sigProvider) throws CRLException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
            SignatureException {
        String sigAlg = getSigAlgName();
        if (sigAlg == null) {
            sigAlg = getSigAlgOID();
        }

        final Signature sig;
        if (sigProvider == null) {
            sig = Signature.getInstance(sigAlg);
        } else {
            sig = Signature.getInstance(sigAlg, sigProvider);
        }

        sig.initVerify(key);
        sig.update(getTBSCertList());
        if (!sig.verify(getSignature())) {
            throw new SignatureException("signature did not verify");
        }
    }

    @Override
    public void verify(PublicKey key) throws CRLException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchProviderException, SignatureException {
        if (key instanceof OpenSSLKeyHolder) {
            OpenSSLKey pkey = ((OpenSSLKeyHolder) key).getOpenSSLKey();
            verifyOpenSSL(pkey);
            return;
        }

        verifyInternal(key, null);
    }

    @Override
    public void verify(PublicKey key, String sigProvider) throws CRLException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
            SignatureException {
        verifyInternal(key, sigProvider);
    }

    @Override
    public int getVersion() {
        return (int) NativeCrypto.X509_CRL_get_version(mContext) + 1;
    }

    @Override
    public Principal getIssuerDN() {
        return getIssuerX500Principal();
    }

    @Override
    public X500Principal getIssuerX500Principal() {
        final byte[] issuer = NativeCrypto.X509_CRL_get_issuer_name(mContext);
        return new X500Principal(issuer);
    }

    @Override
    public Date getThisUpdate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.MILLISECOND, 0);
        NativeCrypto.ASN1_TIME_to_Calendar(NativeCrypto.X509_CRL_get_lastUpdate(mContext),
                calendar);
        return calendar.getTime();
    }

    @Override
    public Date getNextUpdate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.MILLISECOND, 0);
        NativeCrypto.ASN1_TIME_to_Calendar(NativeCrypto.X509_CRL_get_nextUpdate(mContext),
                calendar);
        return calendar.getTime();
    }

    @Override
    public X509CRLEntry getRevokedCertificate(BigInteger serialNumber) {
        final long revokedRef = NativeCrypto.X509_CRL_get0_by_serial(mContext,
                serialNumber.toByteArray());
        if (revokedRef == 0) {
            return null;
        }

        return new OpenSSLX509CRLEntry(NativeCrypto.X509_REVOKED_dup(revokedRef));
    }

    @Override
    public X509CRLEntry getRevokedCertificate(X509Certificate certificate) {
        if (certificate instanceof OpenSSLX509Certificate) {
            OpenSSLX509Certificate osslCert = (OpenSSLX509Certificate) certificate;
            final long x509RevokedRef = NativeCrypto.X509_CRL_get0_by_cert(mContext,
                    osslCert.getContext());

            if (x509RevokedRef == 0) {
                return null;
            }

            return new OpenSSLX509CRLEntry(NativeCrypto.X509_REVOKED_dup(x509RevokedRef));
        }

        return getRevokedCertificate(certificate.getSerialNumber());
    }

    @Override
    public Set<? extends X509CRLEntry> getRevokedCertificates() {
        final long[] entryRefs = NativeCrypto.X509_CRL_get_REVOKED(mContext);
        if (entryRefs == null || entryRefs.length == 0) {
            return null;
        }

        final Set<OpenSSLX509CRLEntry> crlSet = new HashSet<OpenSSLX509CRLEntry>();
        for (long entryRef : entryRefs) {
            crlSet.add(new OpenSSLX509CRLEntry(entryRef));
        }

        return crlSet;
    }

    @Override
    public byte[] getTBSCertList() throws CRLException {
        return NativeCrypto.get_X509_CRL_crl_enc(mContext);
    }

    @Override
    public byte[] getSignature() {
        return NativeCrypto.get_X509_CRL_signature(mContext);
    }

    @Override
    public String getSigAlgName() {
        return AlgNameMapper.map2AlgName(getSigAlgOID());
    }

    @Override
    public String getSigAlgOID() {
        return NativeCrypto.get_X509_CRL_sig_alg_oid(mContext);
    }

    @Override
    public byte[] getSigAlgParams() {
        return NativeCrypto.get_X509_CRL_sig_alg_parameter(mContext);
    }

    @Override
    public boolean isRevoked(Certificate cert) {
        if (!(cert instanceof X509Certificate)) {
            return false;
        }

        final OpenSSLX509Certificate osslCert;
        if (cert instanceof OpenSSLX509Certificate) {
            osslCert = (OpenSSLX509Certificate) cert;
        } else {
            try {
                osslCert = OpenSSLX509Certificate.fromX509DerInputStream(new ByteArrayInputStream(
                        cert.getEncoded()));
            } catch (Exception e) {
                throw new RuntimeException("cannot convert certificate", e);
            }
        }

        final long x509RevokedRef = NativeCrypto.X509_CRL_get0_by_cert(mContext,
                osslCert.getContext());

        return x509RevokedRef != 0;
    }

    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        final long bioCtx = NativeCrypto.create_BIO_OutputStream(os);
        try {
            NativeCrypto.X509_CRL_print(bioCtx, mContext);
            return os.toString();
        } finally {
            NativeCrypto.BIO_free(bioCtx);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (mContext != 0) {
                NativeCrypto.X509_CRL_free(mContext);
            }
        } finally {
            super.finalize();
        }
    }

}
