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

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class implements the parameters for the {@code PKIX CertPathValidator}.
 * <p>
 * The parameters must be created with <i>trusted</i> certificate authorities
 * (trust anchors).
 *
 * @see CertPathValidator
 * @see CertPathParameters
 */
public class PKIXParameters implements CertPathParameters {
    // Set of trust anchors - most trusted CAs
    private Set<TrustAnchor> trustAnchors;
    // Set of acceptable initial policy identifiers (OID strings)
    private Set<String> initialPolicies;
    // List of cert stores that used to find certificates and CRLs
    private List<CertStore> certStores;
    // Time for which the validity of the certification
    // patch should be determined
    private Date date;
    // List of certification patch checkers (PKIXCertPathChecker)
    private List<PKIXCertPathChecker> certPathCheckers;
    // Preferred signature provider name
    private String sigProvider;
    // Required constraints on the target certificate
    private CertSelector targetCertConstraints;
    // Indicates whether cert revocation is enabled or not
    private boolean revocationEnabled = true;
    // Indicates whether explicit policy required or not
    private boolean explicitPolicyRequired = false;
    // Indicates whether policy mapping inhibited or not
    private boolean policyMappingInhibited = false;
    // Indicates whether any policy inhibited or not
    private boolean anyPolicyInhibited = false;
    // Indicates whether certificates that include policy
    // qualifiers in a certificate policies extension that
    // is marked critical must be rejected or not
    private boolean policyQualifiersRejected = true;

    /**
     * Creates a new {@code PKIXParameters} instance with the specified set of
     * <i>trusted</i> certificate authorities.
     *
     * @param trustAnchors
     *            the trusted CAs.
     * @throws InvalidAlgorithmParameterException
     *             if {@code trustAnchors} is empty.
     */
    public PKIXParameters(Set<TrustAnchor> trustAnchors)
        throws InvalidAlgorithmParameterException {
        if (trustAnchors == null) {
            throw new NullPointerException("trustAnchors == null");
        }
        checkTrustAnchors(trustAnchors);
        this.trustAnchors = new HashSet<TrustAnchor>(trustAnchors);
    }

    /**
     * Creates a new {@code PKIXParameters} instance with the trusted {@code
     * X509Certificate} entries from the specified {@code KeyStore}.
     *
     * @param keyStore
     *            the key store containing trusted certificates.
     * @throws KeyStoreException
     *             if the {@code keyStore} is not initialized.
     * @throws InvalidAlgorithmParameterException
     *             if {@code keyStore} does not contained any trusted
     *             certificate entry.
     */
    public PKIXParameters(KeyStore keyStore)
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        if (keyStore == null) {
            throw new NullPointerException("keyStore == null");
        }
        // Will throw KeyStoreException if
        // keyStore has not been initialized (loaded)
        if (keyStore.size() == 0) {
            throw new InvalidAlgorithmParameterException("keyStore.size() == 0");
        }
        // keyStore is not null and loaded
        trustAnchors = new HashSet<TrustAnchor>();
        for (Enumeration i = keyStore.aliases(); i.hasMoreElements();) {
            String alias = (String) i.nextElement();
            if (keyStore.isCertificateEntry(alias)) {
                // this is trusted certificate entry
                // check if it is X509Certificate
                Certificate c = keyStore.getCertificate(alias);
                // add only X509Certificate
                // ignore all other types
                if (c instanceof X509Certificate) {
                    trustAnchors.add(new TrustAnchor((X509Certificate)c, null));
                }
            }
        }
        checkTrustAnchors(trustAnchors);
    }

    /**
     * Returns a unmodifiable set of the <i>trusted</i> certificate authorities.
     *
     * @return a unmodifiable set of the <i>trusted</i> certificate authorities.
     */
    public Set<TrustAnchor> getTrustAnchors() {
        return Collections.unmodifiableSet(trustAnchors);
    }

    /**
     * Sets the set of <i>trusted</i> certificate authorities.
     *
     * @param trustAnchors
     *            the set of <i>trusted</i> certificate authorities.
     * @throws InvalidAlgorithmParameterException
     *             if {@code trustAnchors} is empty.
     */
    public void setTrustAnchors(Set<TrustAnchor> trustAnchors)
        throws InvalidAlgorithmParameterException {
        if (trustAnchors == null) {
            throw new NullPointerException("trustAnchors == null");
        }
        checkTrustAnchors(trustAnchors);
        // make shallow copy
        this.trustAnchors = new HashSet<TrustAnchor>(trustAnchors);
    }

    /**
     * Returns whether the <i>any policy OID</i> will be inhibited if it's
     * included in a certificate.
     *
     * @return {@code true} if the <i>any policy OID</i> will be inhibited,
     *         otherwise {@code false}.
     */
    public boolean isAnyPolicyInhibited() {
        return anyPolicyInhibited;
    }

    /**
     * Sets whether the <i>any policy OID</i> should be inhibited if it's
     * included in a certificate.
     *
     * @param anyPolicyInhibited
     *            {@code true} if the <i>any policy OID</i> should be inhibited,
     *            otherwise {@code false}.
     */
    public void setAnyPolicyInhibited(boolean anyPolicyInhibited) {
        this.anyPolicyInhibited = anyPolicyInhibited;
    }

    /**
     * Returns the list of checkers for the certification path.
     * <p>
     * The list is unmodifiable and the entries in the list are cloned.
     *
     * @return the list of checkers for the certification path.
     */
    public List<PKIXCertPathChecker> getCertPathCheckers() {
        if (certPathCheckers == null) {
            // set to empty List if has not been set yet
            certPathCheckers = new ArrayList<PKIXCertPathChecker>();
        }
        if (certPathCheckers.isEmpty()) {
            // no content - no need to copy,
            // just return immutable view of the same
            // empty List each time
            return Collections.unmodifiableList(certPathCheckers);
        }
        // List is not empty - do deep copy
        ArrayList<PKIXCertPathChecker> modifiableList = new ArrayList<PKIXCertPathChecker>();
        for (PKIXCertPathChecker certPathChecker : certPathCheckers) {
            modifiableList.add((PKIXCertPathChecker) certPathChecker.clone());
        }
        return Collections.unmodifiableList(modifiableList);
    }

    /**
     * Sets the list of checkers for the certification path.
     * <p>
     * The list is copied and the entries are cloned.
     *
     * @param certPathCheckers
     *            the list of checkers for the certification path, or {@code
     *            null} to clear the checkers.
     */
    public void setCertPathCheckers(List<PKIXCertPathChecker> certPathCheckers) {
        if (certPathCheckers == null || certPathCheckers.isEmpty()) {
            // empty list or null provided
            if (this.certPathCheckers != null &&
               !this.certPathCheckers.isEmpty()) {
                // discard non-empty list
                this.certPathCheckers = null;
            }
            return;
        }
        // non-empty list provided - do deep copy
        this.certPathCheckers = new ArrayList<PKIXCertPathChecker>();
        for (PKIXCertPathChecker certPathChecker : certPathCheckers) {
            this.certPathCheckers.add((PKIXCertPathChecker) certPathChecker.clone());
        }
    }

    /**
     * Adds the specified {@code PKIXCertPathChecker} to the list of
     * certification path checkers.
     *
     * @param checker
     *            the {@code PKIXCertPathChecker} to add, if {@code null}, it
     *            will be ignored.
     */
    public void addCertPathChecker(PKIXCertPathChecker checker) {
        if (checker == null) {
            // do nothing if null provided
            return;
        }
        if (certPathCheckers == null) {
            // set to empty List if has not been set yet
            certPathCheckers = new ArrayList<PKIXCertPathChecker>();
        }
        // add a copy to avoid possible modifications
        certPathCheckers.add((PKIXCertPathChecker) checker.clone());
    }

    /**
     * Returns the list of certificate stores that are used to find certificates
     * and CRLs.
     *
     * @return an immutable list of certificate stores.
     */
    public List<CertStore> getCertStores() {
        if (certStores == null) {
            // set to empty List if has not been set yet
            certStores = new ArrayList<CertStore>();
        }
        if (certStores.isEmpty()) {
            // no content - no need to copy,
            // just return immutable view of the same
            // empty List each time
            return Collections.unmodifiableList(certStores);
        }
        // List is not empty - do shallow copy
        ArrayList<CertStore> modifiableList
            = new ArrayList<CertStore>(certStores);
        return Collections.unmodifiableList(modifiableList);
    }

    /**
     * Set the list of certificate stores that are used to find certificates and
     * CRLs.
     *
     * @param certStores the list of certificate stores.
     */
    public void setCertStores(List<CertStore> certStores) {
        if (certStores == null || certStores.isEmpty()) {
            // empty list or null provided
            if (this.certStores != null && !this.certStores.isEmpty()) {
                // discard non-empty list
                this.certStores = null;
            }
            return;
        }
        // non-empty list provided - do shallow copy
        this.certStores = new ArrayList<CertStore>(certStores);
    }

    /**
     * Adds a certificate store to the list of certificate stores that are used
     * to find certificates and CRLs.
     *
     * @param store
     *            the store to add, if {@code null}, it will be ignored.
     */
    public void addCertStore(CertStore store) {
        if (store == null) {
            // do nothing if null provided
            return;
        }
        if (certStores == null) {
            // set to empty List if has not been set yet
            certStores = new ArrayList<CertStore>();
        }
        // add store
        certStores.add(store);
    }

    /**
     * Returns the time for which the validation of the certification path
     * should be evaluated.
     *
     * @return the time for the validation, or {@code null} for the current
     *         time.
     */
    public Date getDate() {
        return date == null ? null : (Date)date.clone();
    }

    /**
     * Sets the time for which the validation of the certification path should be
     * evaluated.
     *
     * @param date
     *            the time for the validation, or {@code null} for the current
     *            time.
     */
    public void setDate(Date date) {
        this.date = (date == null ? null : new Date(date.getTime()));
    }

    /**
     * Returns whether an acceptable policy needs to be explicit identified in
     * every certificate.
     *
     * @return {@code true} if an explicit policy is required, otherwise {@code
     *         false}.
     */
    public boolean isExplicitPolicyRequired() {
        return explicitPolicyRequired;
    }

    /**
     * Sets whether an an acceptable policy needs to be explicit identified in
     * every certificate.
     *
     * @param explicitPolicyRequired
     *            {@code true} if an explicit policy is required, otherwise
     *            {@code false}.
     */
    public void setExplicitPolicyRequired(boolean explicitPolicyRequired) {
        this.explicitPolicyRequired = explicitPolicyRequired;
    }

    /**
     * Returns the list of policies (as OID strings) that would be acceptable
     * for the purpose of certification path processing.
     *
     * @return the unmodifiable list of policies, or an empty set if any policy
     *         is acceptable.
     */
    public Set<String> getInitialPolicies() {
        if (initialPolicies == null) {
            // set to empty Set if has not been set yet
            initialPolicies = new HashSet<String>();
        }
        if (initialPolicies.isEmpty()) {
            // no content - no need to copy,
            // just return immutable view of the same
            // empty Set each time
            return Collections.unmodifiableSet(initialPolicies);
        }
        // List is not empty - do shallow copy
        HashSet<String> modifiableSet = new HashSet<String>(initialPolicies);
        return Collections.unmodifiableSet(modifiableSet);
    }

    /**
     * Sets the list of policies (as OID strings) that would be acceptable for
     * the purpose of certification path processing.
     *
     * @param initialPolicies
     *            the list of policies, or an empty set or {@code null} if any
     *            policy is acceptable.
     */
    public void setInitialPolicies(Set<String> initialPolicies) {
        if (initialPolicies == null || initialPolicies.isEmpty()) {
            // empty list or null provided
            if (this.initialPolicies != null && !this.initialPolicies.isEmpty()) {
                // discard non-empty list
                this.initialPolicies = null;
            }
            return;
        }
        // non-empty list provided - do shallow copy
        this.initialPolicies = new HashSet<String>(initialPolicies);
    }

    /**
     * Returns whether policy mapping is inhibited.
     *
     * @return {@code true} if policy mapping is inhibited, otherwise {@code
     *         false}.
     */
    public boolean isPolicyMappingInhibited() {
        return policyMappingInhibited;
    }

    /**
     * Sets whether policy mapping is to be inhibited.
     *
     * @param policyMappingInhibited
     *            {@code true} if policy mapping is to be inhibited, otherwise
     *            {@code false}.
     */
    public void setPolicyMappingInhibited(boolean policyMappingInhibited) {
        this.policyMappingInhibited = policyMappingInhibited;
    }

    /**
     * Returns whether certificates are rejected that include policy
     * qualifiers in a certificate policy extension that is marked as critical.
     *
     * @return {@code true} if the certificates should be rejected, otherwise
     *         {@code false}.
     */
    public boolean getPolicyQualifiersRejected() {
        return policyQualifiersRejected;
    }

    /**
     * Sets whether certificates should be rejected that include policy
     * qualifiers in a certificate policy extension that is marked as critical.
     *
     * @param policyQualifiersRejected
     *            {@code true} if the certificates should be rejected, otherwise
     *            {@code false}.
     */
    public void setPolicyQualifiersRejected(boolean policyQualifiersRejected) {
        this.policyQualifiersRejected = policyQualifiersRejected;
    }

    /**
     * Returns whether the default revocation checking mechanism of the
     * underlying service provider is used.
     *
     * @return {@code true} if the default revocation checking mechanism is
     *         used, otherwise {@code false}.
     */
    public boolean isRevocationEnabled() {
        return revocationEnabled;
    }

    /**
     * Sets whether the default revocation checking mechanism of the underlying
     * service provider should be used.
     *
     * @param revocationEnabled
     *            {@code true} id the default revocation checking mechanism
     *            should be used, otherwise {@code false}.
     */
    public void setRevocationEnabled(boolean revocationEnabled) {
        this.revocationEnabled = revocationEnabled;
    }

    /**
     * Returns the name of the signature provider.
     *
     * @return the name of the signature provider, or {@code null} if none is
     *         set.
     */
    public String getSigProvider() {
        return sigProvider;
    }

    /**
     * Sets the name of the preferred signature provider.
     * <p>
     * If set, the specified provider will be preferred for creating signatures.
     * If not set, the first provider found supporting creation of signatures
     * will be used.
     *
     * @param sigProvider
     *            the name of the preferred signature provider, or {@code null}
     *            if none is preferred.
     */
    public void setSigProvider(String sigProvider) {
        this.sigProvider = sigProvider;
    }

    /**
     * Returns the constraints that are required for the target certificate.
     *
     * @return the constraints for the target certificate, or {@code null} if
     *         none are set.
     */
    public CertSelector getTargetCertConstraints() {
        return (targetCertConstraints == null ? null
                :(CertSelector)targetCertConstraints.clone());
    }

    /**
     * Sets the constraints that are required for the target certificate.
     *
     * @param targetCertConstraints
     *            the constraints for the target certificate, or {@code null} if
     *            none should be used.
     */
    public void setTargetCertConstraints(CertSelector targetCertConstraints) {
        this.targetCertConstraints = (targetCertConstraints == null ? null
                : (CertSelector)targetCertConstraints.clone());
    }

    /**
     * Clones this {@code PKIXParameters} instance.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        try {
            // do shallow copy first
            PKIXParameters ret = (PKIXParameters)super.clone();
            // copy fields containing references to mutable objects
            if (this.certStores != null) {
                ret.certStores = new ArrayList<CertStore>(this.certStores);
            }
            if (this.certPathCheckers != null) {
                ret.certPathCheckers = new ArrayList<PKIXCertPathChecker>(this.certPathCheckers);
            }
            return ret;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns a string representation of this {@code PKIXParameters} instance.
     *
     * @return a string representation of this {@code PKIXParameters} instance.
     */
    public String toString() {
        StringBuilder sb =
            new StringBuilder("[\n Trust Anchors: ");
        sb.append(trustAnchors);
        sb.append("\n Revocation Enabled: ");
        sb.append(revocationEnabled);
        sb.append("\n Explicit Policy Required: ");
        sb.append(explicitPolicyRequired);
        sb.append("\n Policy Mapping Inhibited: ");
        sb.append(policyMappingInhibited);
        sb.append("\n Any Policy Inhibited: ");
        sb.append(anyPolicyInhibited);
        sb.append("\n Policy Qualifiers Rejected: ");
        sb.append(policyQualifiersRejected);
        sb.append("\n Initial Policy OIDs: ");
        sb.append((initialPolicies == null || initialPolicies.isEmpty())
                ? "any" : initialPolicies.toString());
        sb.append("\n Cert Stores: ");
        sb.append((certStores==null||certStores.isEmpty())?
                "no":certStores.toString());
        sb.append("\n Validity Date: ");
        sb.append(date);
        sb.append("\n Cert Path Checkers: ");
        sb.append((certPathCheckers==null||certPathCheckers.isEmpty())?
                "no":certPathCheckers.toString());
        sb.append("\n Signature Provider: ");
        sb.append(sigProvider);
        sb.append("\n Target Certificate Constraints: ");
        sb.append(targetCertConstraints);
        sb.append("\n]");
        return sb.toString();
    }

    /**
     * Checks that {@code trustAnchors} contains only {@code
     * TrustAnchor} instances.
     *
     * @throws InvalidAlgorithmParameterException if trustAnchors set is empty.
     */
    private void checkTrustAnchors(Set<TrustAnchor> trustAnchors)
            throws InvalidAlgorithmParameterException {
        if (trustAnchors.isEmpty()) {
            throw new InvalidAlgorithmParameterException("trustAnchors.isEmpty()");
        }
    }
}
