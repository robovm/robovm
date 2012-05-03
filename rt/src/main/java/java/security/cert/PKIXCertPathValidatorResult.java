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

import java.security.PublicKey;

/**
 * The implementation of the result of the PKIX certification path validation.
 *
 * @see CertPathValidator
 * @see CertPathValidator#validate(CertPath, CertPathParameters)
 */
public class PKIXCertPathValidatorResult implements CertPathValidatorResult {
    // A trust anchor used during validation of certification path
    private final TrustAnchor trustAnchor;
    // Valid policy tree resulting from PKIX
    // certification path validation algorithm
    private final PolicyNode policyTree;
    // Public key of the subject (target) certificate
    private final PublicKey subjectPublicKey;

    /**
     * Creates a new {@code PKIXCertPathValidatorResult} with the specified
     * trust anchor, the valid policy tree and the subject public key.
     *
     * @param trustAnchor
     *            the trust anchor describing the certification authority (CA)
     *            that served as trust anchor for the certification path.
     * @param policyTree
     *            the valid policy tree from the validation.
     * @param subjectPublicKey
     *            the subject public key from the validation.
     */
    public PKIXCertPathValidatorResult(TrustAnchor trustAnchor,
            PolicyNode policyTree, PublicKey subjectPublicKey) {
        this.trustAnchor = trustAnchor;
        this.policyTree = policyTree;
        this.subjectPublicKey = subjectPublicKey;
        if (this.trustAnchor == null) {
            throw new NullPointerException("trustAnchor == null");
        }
        if (this.subjectPublicKey == null) {
            throw new NullPointerException("subjectPublicKey == null");
        }
    }

    /**
     * Returns the valid policy tree from the validation.
     *
     * @return the valid policy tree from the validation.
     */
    public PolicyNode getPolicyTree() {
        return policyTree;
    }

    /**
     * Returns the subject public key from the validation.
     *
     * @return the subject public key from the validation.
     */
    public PublicKey getPublicKey() {
        return subjectPublicKey;
    }

    /**
     * Returns the trust anchor describing the certification authority (CA) that
     * served as trust anchor for this certification path.
     *
     * @return the trust anchor.
     */
    public TrustAnchor getTrustAnchor() {
        return trustAnchor;
    }

    /**
     * Clones this {@code PKIXCertPathValidatorResult} instance.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns a string representation for this {@code
     * PKIXCertPathValidatorResult} instance.
     *
     * @return a string representation for this {@code
     *         PKIXCertPathValidatorResult} instance.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(": [\n Trust Anchor: ");
        sb.append(trustAnchor.toString());
        sb.append("\n Policy Tree: ");
        sb.append(policyTree == null ? "no valid policy tree\n"
                                     : policyTree.toString());
        sb.append("\n Subject Public Key: ");
        sb.append(subjectPublicKey.toString());
        sb.append("\n]");
        return sb.toString();
    }
}
