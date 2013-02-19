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

import java.util.Collection;
import java.util.Set;

/**
 * The class specifying the interface to extend the certification path
 * validation algorithm by checks to perform on an {@code X509Certificate}.
 * <p>
 * The checks are added to a certification path validation using the
 * {@link PKIXParameters#setCertPathCheckers(java.util.List)
 * setCertPathCheckers} or
 * {@link PKIXBuilderParameters#addCertPathChecker(PKIXCertPathChecker)
 * addCertPathChecker} of the {@code PKIXParameters} and {@code
 * PKIXBuilderParameters} class respectively. The
 * {@link #check(Certificate, Collection) check} method will be called for each
 * certificate processed by a {@code CertPathBuilder} of {@code
 * CertPathValidator}.
 * <p>
 * A {@code PKIXCertPathChecker} implementation <u>must</u> support reverse
 * checking (from trusted CA to target) and <u>may</u> support forward checking
 * (from target to trusted CA). The return value of {@code
 * isForwardCheckingSupported} indicates whether forward checking is supported.
 */
public abstract class PKIXCertPathChecker implements Cloneable {

    /**
     * Creates a new {@code PKIXCertPathChecker} instance.
     */
    protected PKIXCertPathChecker() {}

    /**
     * Clones this {@code PKIXCertPathChecker} instance.
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
     * Initializes this {@code PKIXCertPathChecker} instance for specified
     * <i>checking direction</i>.
     *
     * @param forward
     *            the direction of the certification path processing, {@code
     *            true} if the certificates are processed in forward direction
     *            (from target to trusted CA), {@code false} if processed in
     *            reverse direction (from trusted CA to target).
     * @throws CertPathValidatorException
     *             if initialization of this {@code PKIXCertPathChecker}
     *             instance fails, or if it cannot process certificates in the
     *             specified order.
     */
    public abstract void init(boolean forward)
        throws CertPathValidatorException;

    /**
     * Returns whether this {@code PKIXCertPathChecker} instance supports
     * <i>forward checking</i>.
     *
     * @return {@code true} if this {@code PKIXCertPathChecker} instance
     *         supports forward checking, otherwise {@code false}.
     */
    public abstract boolean isForwardCheckingSupported();

    /**
     * Returns the list of extensions of X.509 certificates that this {@code
     * PKIXCertPathChecker} is able to process.
     *
     * @return the list of extensions of X.509 certificates that this {@code
     *         PKIXCertPathChecker} is able to process, or {@code null} if there
     *         are none.
     */
    public abstract Set<String> getSupportedExtensions();

    /**
     * Checks the specified certificate and removes the processed critical
     * extensions from the specified list of X.509 extension <i>OID</i>s.
     *
     * @param cert
     *            the certificate.
     * @param unresolvedCritExts
     *            the list of critical X.509 extension OID strings.
     * @throws CertPathValidatorException
     *             if check(s) fail on the specified certificate.
     */
    public abstract void check(Certificate cert, Collection<String> unresolvedCritExts)
        throws CertPathValidatorException;
}
