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
import java.util.Collection;

/**
 * The <i>Service Provider Interface</i> (<b>SPI</b>) definition for the {@code
 * CertStore} class to be implemented by security providers.
 */
public abstract class CertStoreSpi {

    /**
     * Creates a new {@code CertStoreSpi}.
     *
     * @param params
     *            the initialization parameters.
     * @throws InvalidAlgorithmParameterException
     *             if the specified initialization parameters cannot be used to
     *             initialize this instance.
     */
    public CertStoreSpi(CertStoreParameters params)
            throws InvalidAlgorithmParameterException {
    }

    /**
     * Returns the list of {@code Certificate}s for the specified {@code
     * CertSelector} from this instance.
     *
     * @param selector
     *            the selector containing the criteria to search for
     *            certificates in this instance.
     * @return the list of {@code Certificate}s that match the criteria of the
     *         specified selector.
     * @throws CertStoreException
     *             if error(s) occur.
     */
    public abstract Collection<? extends Certificate> engineGetCertificates(CertSelector selector)
            throws CertStoreException;

    /**
     * Returns the list of {@code CRL}s for the specified {@code CRLSelector}
     * from this instance.
     *
     * @param selector
     *            the selector containing the criteria to search for certificate
     *            revocation lists in instance.
     * @return the list of {@code CRL}s that match the criteria of the specified
     *         selector
     * @throws CertStoreException
     *             if error(s) occur.
     */
    public abstract Collection<? extends CRL> engineGetCRLs(CRLSelector selector)
            throws CertStoreException;
}
