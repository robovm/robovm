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

package org.conscrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import libcore.io.EventLogger;

/**
 *
 * TrustManager implementation. The implementation is based on CertPathValidator
 * PKIX and CertificateFactory X509 implementations. This implementations should
 * be provided by some certification provider.
 *
 * @see javax.net.ssl.X509TrustManager
 */
public final class TrustManagerImpl implements X509TrustManager {

    /**
     * The AndroidCAStore if non-null, null otherwise.
     */
    private final KeyStore rootKeyStore;

    /**
     * The CertPinManager, which validates the chain against a host-to-pin mapping
     */
    private CertPinManager pinManager;

    /**
     * The backing store for the AndroidCAStore if non-null. This will
     * be null when the rootKeyStore is null, implying we are not
     * using the AndroidCAStore.
     */
    private final TrustedCertificateStore trustedCertificateStore;

    private final CertPathValidator validator;

    /**
     * An index of TrustAnchor instances that we've seen. Unlike the
     * TrustedCertificateStore, this may contain intermediate CAs.
     */
    private final TrustedCertificateIndex trustedCertificateIndex;

    /**
     * This is lazily initialized in the AndroidCAStore case since it
     * forces us to bring all the CAs into memory. In the
     * non-AndroidCAStore, we initialize this as part of the
     * constructor.
     */
    private final X509Certificate[] acceptedIssuers;

    private final Exception err;
    private final CertificateFactory factory;

    /**
     * Creates X509TrustManager based on a keystore
     *
     * @param ks
     */
    public TrustManagerImpl(KeyStore keyStore) {
        this(keyStore, null);
    }

    /**
     * For testing only
     */
    public TrustManagerImpl(KeyStore keyStore, CertPinManager manager) {
        CertPathValidator validatorLocal = null;
        CertificateFactory factoryLocal = null;
        KeyStore rootKeyStoreLocal = null;
        TrustedCertificateStore trustedCertificateStoreLocal = null;
        TrustedCertificateIndex trustedCertificateIndexLocal = null;
        X509Certificate[] acceptedIssuersLocal = null;
        Exception errLocal = null;
        try {
            validatorLocal = CertPathValidator.getInstance("PKIX");
            factoryLocal = CertificateFactory.getInstance("X509");

            // if we have an AndroidCAStore, we will lazily load CAs
            if ("AndroidCAStore".equals(keyStore.getType())) {
                rootKeyStoreLocal = keyStore;
                trustedCertificateStoreLocal = new TrustedCertificateStore();
                acceptedIssuersLocal = null;
                trustedCertificateIndexLocal = new TrustedCertificateIndex();
            } else {
                rootKeyStoreLocal = null;
                trustedCertificateStoreLocal = null;
                acceptedIssuersLocal = acceptedIssuers(keyStore);
                trustedCertificateIndexLocal
                        = new TrustedCertificateIndex(trustAnchors(acceptedIssuersLocal));
            }

        } catch (Exception e) {
            errLocal = e;
        }

        if (manager != null) {
            this.pinManager = manager;
        } else {
            try {
                pinManager = new CertPinManager(trustedCertificateStoreLocal);
            } catch (PinManagerException e) {
                throw new SecurityException("Could not initialize CertPinManager", e);
            }
        }

        this.rootKeyStore = rootKeyStoreLocal;
        this.trustedCertificateStore = trustedCertificateStoreLocal;
        this.validator = validatorLocal;
        this.factory = factoryLocal;
        this.trustedCertificateIndex = trustedCertificateIndexLocal;
        this.acceptedIssuers = acceptedIssuersLocal;
        this.err = errLocal;
    }

    private static X509Certificate[] acceptedIssuers(KeyStore ks) {
        try {
            // Note that unlike the PKIXParameters code to create a Set of
            // TrustAnchors from a KeyStore, this version takes from both
            // TrustedCertificateEntry and PrivateKeyEntry, not just
            // TrustedCertificateEntry, which is why TrustManagerImpl
            // cannot just use an PKIXParameters(KeyStore)
            // constructor.

            // TODO remove duplicates if same cert is found in both a
            // PrivateKeyEntry and TrustedCertificateEntry
            List<X509Certificate> trusted = new ArrayList<X509Certificate>();
            for (Enumeration<String> en = ks.aliases(); en.hasMoreElements();) {
                final String alias = en.nextElement();
                final X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                if (cert != null) {
                    trusted.add(cert);
                }
            }
            return trusted.toArray(new X509Certificate[trusted.size()]);
        } catch (KeyStoreException e) {
            return new X509Certificate[0];
        }
    }

    private static Set<TrustAnchor> trustAnchors(X509Certificate[] certs) {
        Set<TrustAnchor> trustAnchors = new HashSet<TrustAnchor>(certs.length);
        for (X509Certificate cert : certs) {
            trustAnchors.add(new TrustAnchor(cert, null));
        }
        return trustAnchors;
    }

    @Override public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        checkTrusted(chain, authType, null, true);
    }

    @Override public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        checkTrusted(chain, authType, null, false);
    }

    /**
     * Validates whether a server is trusted. If hostname is given and non-null it also checks if
     * chain is pinned appropriately for that host. If null, it does not check for pinned certs.
     * The return value is a list of the certificates used for making the trust decision.
     */
    public List<X509Certificate> checkServerTrusted(X509Certificate[] chain, String authType,
                                                    String host) throws CertificateException {
        return checkTrusted(chain, authType, host, false);
    }

    public void handleTrustStorageUpdate() {
        if (acceptedIssuers == null) {
            trustedCertificateIndex.reset();
        } else {
            trustedCertificateIndex.reset(trustAnchors(acceptedIssuers));
        }
    }

    private List<X509Certificate> checkTrusted(X509Certificate[] chain, String authType,
                                               String host, boolean clientAuth)
            throws CertificateException {
        if (chain == null || chain.length == 0 || authType == null || authType.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        }
        if (err != null) {
            throw new CertificateException(err);
        }

        // get the cleaned up chain and trust anchor
        Set<TrustAnchor> trustAnchor = new HashSet<TrustAnchor>(); // there can only be one!
        X509Certificate[] newChain = cleanupCertChainAndFindTrustAnchors(chain, trustAnchor);

        // add the first trust anchor to the chain, which may be an intermediate
        List<X509Certificate> wholeChain = new ArrayList<X509Certificate>();
        wholeChain.addAll(Arrays.asList(newChain));
        // trustAnchor is actually just a single element
        for (TrustAnchor trust : trustAnchor) {
            wholeChain.add(trust.getTrustedCert());
        }

        // add all the cached certificates from the cert index, avoiding loops
        // this gives us a full chain from leaf to root, which we use for cert pinning and pass
        // back out to callers when we return.
        X509Certificate last = wholeChain.get(wholeChain.size() - 1);
        while (true) {
            TrustAnchor cachedTrust = trustedCertificateIndex.findByIssuerAndSignature(last);
            // the cachedTrust can be null if there isn't anything in the index or if a user has
            // trusted a non-self-signed cert.
            if (cachedTrust == null) {
                break;
            }

            // at this point we have a cached trust anchor, but don't know if its one we got from
            // the server. Extract the cert, compare it to the last element in the chain, and add it
            // if we haven't seen it before.
            X509Certificate next = cachedTrust.getTrustedCert();
            if (next != last) {
                wholeChain.add(next);
                last = next;
            } else {
                // if next == last then we found a self-signed cert and the chain is done
                break;
            }
        }

        // build the cert path from the array of certs sans trust anchors
        CertPath certPath = factory.generateCertPath(Arrays.asList(newChain));

        if (host != null) {
            boolean chainIsNotPinned = true;
            try {
                chainIsNotPinned = pinManager.chainIsNotPinned(host, wholeChain);
            } catch (PinManagerException e) {
                throw new CertificateException(e);
            }
            if (chainIsNotPinned) {
                throw new CertificateException(new CertPathValidatorException(
                        "Certificate path is not properly pinned.", null, certPath, -1));
            }
        }

        if (newChain.length == 0) {
            // chain was entirely trusted, skip the validator
            return wholeChain;
        }

        if (trustAnchor.isEmpty()) {
            throw new CertificateException(new CertPathValidatorException(
                    "Trust anchor for certification path not found.", null, certPath, -1));
        }

        // There's no point in checking trust anchors here, and it will throw off the MD5 check,
        // so we just hand it the chain without anchors
        ChainStrengthAnalyzer.check(newChain);

        try {
            PKIXParameters params = new PKIXParameters(trustAnchor);
            params.setRevocationEnabled(false);
            params.addCertPathChecker(new ExtendedKeyUsagePKIXCertPathChecker(clientAuth,
                                                                              newChain[0]));
            validator.validate(certPath, params);
            // Add intermediate CAs to the index to tolerate sites
            // that assume that the browser will have cached these.
            // The server certificate is skipped by skipping the
            // zeroth element of new chain and note that the root CA
            // will have been removed in
            // cleanupCertChainAndFindTrustAnchors.  http://b/3404902
            for (int i = 1; i < newChain.length; i++) {
                trustedCertificateIndex.index(newChain[i]);
            }
        } catch (InvalidAlgorithmParameterException e) {
            throw new CertificateException(e);
        } catch (CertPathValidatorException e) {
            throw new CertificateException(e);
        }

        return wholeChain;
    }

    /**
     * Clean up the certificate chain, returning a cleaned up chain,
     * which may be a new array instance if elements were removed.
     * Theoretically, we shouldn't have to do this, but various web
     * servers in practice are mis-configured to have out-of-order
     * certificates, expired self-issued root certificate, or CAs with
     * unsupported signature algorithms such as
     * md2WithRSAEncryption. This also handles removing old certs
     * after bridge CA certs.
     */
    private X509Certificate[] cleanupCertChainAndFindTrustAnchors(X509Certificate[] chain,
                                                                  Set<TrustAnchor> trustAnchors) {
        X509Certificate[] original = chain;

        // 1. Clean the received certificates chain.
        int currIndex;
        // Start with the first certificate in the chain, assuming it
        // is the leaf certificate (server or client cert).
        for (currIndex = 0; currIndex < chain.length; currIndex++) {
            // Walk the chain to find a "subject" matching
            // the "issuer" of the current certificate. In a properly
            // ordered chain this should be the next cert and be fast.
            // If not, we reorder things to be as the validator will
            // expect.
            boolean foundNext = false;
            for (int nextIndex = currIndex + 1; nextIndex < chain.length; nextIndex++) {
                if (chain[currIndex].getIssuerDN().equals(chain[nextIndex].getSubjectDN())) {
                    foundNext = true;
                    // Exchange certificates so that 0 through currIndex + 1 are in proper order
                    if (nextIndex != currIndex + 1) {
                        // don't mutuate original chain, which may be directly from an SSLSession
                        if (chain == original) {
                            chain = original.clone();
                        }
                        X509Certificate tempCertificate = chain[nextIndex];
                        chain[nextIndex] = chain[currIndex + 1];
                        chain[currIndex + 1] = tempCertificate;
                    }
                    break;
                }
            }
            // If we can't find the next in the chain, just give up
            // and use what we found so far. This drops unrelated
            // certificates that have nothing to do with the cert
            // chain.
            if (!foundNext) {
                break;
            }
        }

        // 2. Find the trust anchor in the chain, if any
        int anchorIndex;
        for (anchorIndex = 0; anchorIndex <= currIndex; anchorIndex++) {
            // If the current cert is a TrustAnchor, we can ignore the rest of the chain.
            // This avoids including "bridge" CA certs that added for legacy compatibility.
            TrustAnchor trustAnchor = findTrustAnchorBySubjectAndPublicKey(chain[anchorIndex]);
            if (trustAnchor != null) {
                trustAnchors.add(trustAnchor);
                break;
            }
        }

        // 3. If the chain is now shorter, copy to an appropriately sized array.
        int chainLength = anchorIndex;
        X509Certificate[] newChain = ((chainLength == chain.length)
                                      ? chain
                                      : Arrays.copyOf(chain, chainLength));

        // 4. If we didn't find a trust anchor earlier, look for one now
        if (trustAnchors.isEmpty()) {
            TrustAnchor trustAnchor = findTrustAnchorByIssuerAndSignature(newChain[anchorIndex-1]);
            if (trustAnchor != null) {
                trustAnchors.add(trustAnchor);
            }
        }
        return newChain;
    }

    /**
     * If an EKU extension is present in the end-entity certificate,
     * it MUST contain an appropriate key usage. For servers, this
     * includes anyExtendedKeyUsage, serverAuth, or the historical
     * Server Gated Cryptography options of nsSGC or msSGC.  For
     * clients, this includes anyExtendedKeyUsage and clientAuth.
     */
    private static class ExtendedKeyUsagePKIXCertPathChecker extends PKIXCertPathChecker {

        private static final String EKU_OID = "2.5.29.37";

        private static final String EKU_anyExtendedKeyUsage = "2.5.29.37.0";
        private static final String EKU_clientAuth = "1.3.6.1.5.5.7.3.2";
        private static final String EKU_serverAuth = "1.3.6.1.5.5.7.3.1";
        private static final String EKU_nsSGC = "2.16.840.1.113730.4.1";
        private static final String EKU_msSGC = "1.3.6.1.4.1.311.10.3.3";

        private static final Set<String> SUPPORTED_EXTENSIONS
                = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(EKU_OID)));

        private final boolean clientAuth;
        private final X509Certificate leaf;

        private ExtendedKeyUsagePKIXCertPathChecker(boolean clientAuth, X509Certificate leaf) {
            this.clientAuth = clientAuth;
            this.leaf = leaf;
        }

        @Override public void init(boolean forward) throws CertPathValidatorException {
        }

        @Override public boolean isForwardCheckingSupported() {
            return true;
        }

        @Override public Set<String> getSupportedExtensions() {
            return SUPPORTED_EXTENSIONS;
        }

        @Override public void check(Certificate c, Collection<String> unresolvedCritExts)
                throws CertPathValidatorException {
            // We only want to validate the EKU on the leaf certificate.
            if (c != leaf) {
                return;
            }
            List<String> ekuOids;
            try {
                ekuOids = leaf.getExtendedKeyUsage();
            } catch (CertificateParsingException e) {
                // A malformed EKU is bad news, consider it fatal.
                throw new CertPathValidatorException(e);
            }
            // We are here to check EKU, but there is none.
            if (ekuOids == null) {
                return;
            }

            boolean goodExtendedKeyUsage = false;
            for (String ekuOid : ekuOids) {
                // anyExtendedKeyUsage for clients and servers
                if (ekuOid.equals(EKU_anyExtendedKeyUsage)) {
                    goodExtendedKeyUsage = true;
                    break;
                }

                // clients
                if (clientAuth) {
                    if (ekuOid.equals(EKU_clientAuth)) {
                        goodExtendedKeyUsage = true;
                        break;
                    }
                    continue;
                }

                // servers
                if (ekuOid.equals(EKU_serverAuth)) {
                    goodExtendedKeyUsage = true;
                    break;
                }
                if (ekuOid.equals(EKU_nsSGC)) {
                    goodExtendedKeyUsage = true;
                    break;
                }
                if (ekuOid.equals(EKU_msSGC)) {
                    goodExtendedKeyUsage = true;
                    break;
                }
            }
            if (goodExtendedKeyUsage) {
                // Mark extendedKeyUsage as resolved if present.
                unresolvedCritExts.remove(EKU_OID);
            } else {
                throw new CertPathValidatorException("End-entity certificate does not have a valid "
                                                     + "extendedKeyUsage.");
            }
        }
    }

    private TrustAnchor findTrustAnchorByIssuerAndSignature(X509Certificate lastCert) {
        TrustAnchor trustAnchor = trustedCertificateIndex.findByIssuerAndSignature(lastCert);
        if (trustAnchor != null) {
            return trustAnchor;
        }
        if (trustedCertificateStore == null) {
            return null;
        }
        // we have a KeyStore and the issuer of the last cert in
        // the chain seems to be missing from the
        // TrustedCertificateIndex, check the KeyStore for a hit
        X509Certificate issuer = trustedCertificateStore.findIssuer(lastCert);
        if (issuer != null) {
            return trustedCertificateIndex.index(issuer);
        }
        return null;
    }

    /**
     * Check the trustedCertificateIndex for the cert to see if it is
     * already trusted and failing that check the KeyStore if it is
     * available.
     */
    private TrustAnchor findTrustAnchorBySubjectAndPublicKey(X509Certificate cert) {
        TrustAnchor trustAnchor = trustedCertificateIndex.findBySubjectAndPublicKey(cert);
        if (trustAnchor != null) {
            return trustAnchor;
        }
        if (trustedCertificateStore == null) {
            // not trusted and no TrustedCertificateStore to check
            return null;
        }
        // probe KeyStore for a cert. AndroidCAStore stores its
        // contents hashed by cert subject on the filesystem to make
        // this faster than scanning all key store entries.
        if (trustedCertificateStore.isTrustAnchor(cert)) {
            // add new TrustAnchor to params index to avoid
            // checking filesystem next time around.
            return trustedCertificateIndex.index(cert);
        }
        return null;
    }

    @Override public X509Certificate[] getAcceptedIssuers() {
        return (acceptedIssuers != null) ? acceptedIssuers.clone() : acceptedIssuers(rootKeyStore);
    }
}
