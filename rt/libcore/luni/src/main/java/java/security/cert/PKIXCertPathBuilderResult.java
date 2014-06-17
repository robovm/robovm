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
 * The result of the PKIX certification path builder, returned by
 * {@link CertPathBuilder#build(CertPathParameters)}.
 */
public class PKIXCertPathBuilderResult extends PKIXCertPathValidatorResult
        implements CertPathBuilderResult {
    // Built and validated certification path
    private final CertPath certPath;

    /**
     * Creates a new {@code PKIXCertPathBuilderResult} instance with the
     * specified validated certification path, the trust anchor of the
     * certification path, the policy tree and the public key of the subject.
     *
     * @param certPath
     *            the validated certification path.
     * @param trustAnchor
     *            the trust anchor.
     * @param policyTree
     *            the policy tree (or {@code null} if not used).
     * @param subjectPublicKey
     *            the public key.
     * @throws NullPointerException
     *             if the {@code cerPath}, {@code trustAnchor} or {@code
     *             subjectPolicyKey} is {@code null}.
     */
    public PKIXCertPathBuilderResult(CertPath certPath, TrustAnchor trustAnchor,
            PolicyNode policyTree, PublicKey subjectPublicKey) {
        super(trustAnchor, policyTree, subjectPublicKey);
        if (certPath == null) {
            throw new NullPointerException("certPath == null");
        }
        this.certPath = certPath;
    }

    /**
     * Returns the validated certification path.
     *
     * @return the validated certification path.
     */
    public CertPath getCertPath() {
        return certPath;
    }

    /**
     * Returns a string representation of this {@code PKIXCertPathBuilderResult}
     * instance.
     *
     * @return a string representation of this {@code PKIXCertPathBuilderResult}
     *         instance.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n Certification Path: ");
        sb.append(certPath.toString());
        sb.append("\n]");
        return sb.toString();
    }
}
