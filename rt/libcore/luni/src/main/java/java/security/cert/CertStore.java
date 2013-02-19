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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.Collection;
import org.apache.harmony.security.fortress.Engine;

/**
 * This class provides the functionality to retrieve {@code Certificate}s and
 * {@code CRL}s from a read-only repository. This repository may be very large
 * and may store trusted as well as untrusted certificates.
 */
public class CertStore {

    // Store spi implementation service name
    private static final String SERVICE = "CertStore";

    // Used to access common engine functionality
    private static final Engine ENGINE = new Engine(SERVICE);

    // Store default property name
    private static final String PROPERTYNAME = "certstore.type";

    // Default value of CertStore type. It returns if certpathbuild.type
    // property is not defined in java.security file
    private static final String DEFAULTPROPERTY = "LDAP";

    // Store used provider
    private final Provider provider;

    // Store CertStoreSpi implementation
    private final CertStoreSpi spiImpl;

    // Store used type
    private final String type;

    // Store used parameters
    private final CertStoreParameters certStoreParams;

    /**
     * Creates a new {@code CertStore} instance.
     *
     * @param storeSpi
     *            the implementation delegate.
     * @param provider
     *            the security provider.
     * @param type
     *            the certificate store type.
     * @param params
     *            the certificate store parameters (may be {@code null}.
     */
    protected CertStore(CertStoreSpi storeSpi, Provider provider, String type,
            CertStoreParameters params) {
        this.provider = provider;
        this.type = type;
        this.spiImpl = storeSpi;
        this.certStoreParams = params;
    }

    /**
     * Creates a new {@code CertStore} instance with the specified type and
     * initialized with the specified parameters.
     *
     * @param type
     *            the certificate store type.
     * @param params
     *            the certificate store parameters (may be {@code null}).
     * @return the new certificate store instance.
     * @throws NoSuchAlgorithmException
     *             if no provider can provide the specified certificate store
     *             type.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters cannot be used to initialize this
     *             certificate store instance.
     * @throws NullPointerException if {@code type == null}
     */
    public static CertStore getInstance(String type, CertStoreParameters params)
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        if (type == null) {
            throw new NullPointerException();
        }
        try {
            Engine.SpiAndProvider sap = ENGINE.getInstance(type, params);
            return new CertStore((CertStoreSpi) sap.spi, sap.provider, type, params);
        } catch (NoSuchAlgorithmException e) {
            Throwable th = e.getCause();
            if (th == null) {
                throw e;
            } else {
                throw new InvalidAlgorithmParameterException(e.getMessage(), th);
            }
        }
    }

    /**
     * Creates a new {@code CertStore} instance from the specified provider with
     * the specified type and initialized with the specified parameters.
     *
     * @param type
     *            the certificate store type.
     * @param params
     *            the certificate store parameters (may be {@code null}).
     * @param provider
     *            the name of the provider.
     * @return the new certificate store instance.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the requested
     *             certificate store type.
     * @throws NoSuchProviderException
     *             if no provider with the specified name can be found.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters cannot be used to initialize this
     *             certificate store instance.
     * @throws IllegalArgumentException if {@code provider == null || provider.isEmpty()}
     * @throws NullPointerException
     *             if {@code type} is {@code null}.
     */
    public static CertStore getInstance(String type,
            CertStoreParameters params, String provider)
            throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException {
        if (provider == null || provider.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(type, params, impProvider);
    }

    /**
     * Creates a new {@code CertStore} instance from the specified provider with
     * the specified type and initialized with the specified parameters.
     * @param type
     *            the certificate store type.
     * @param params
     *            the certificate store parameters (may be {@code null}).
     * @param provider
     *            the name of the provider.
     * @return the new certificate store instance.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the requested
     *             certificate store type.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters cannot be used to initialize this
     *             certificate store instance.
     * @throws IllegalArgumentException if {@code provider == null}
     * @throws NullPointerException if {@code type == null}
     */
    public static CertStore getInstance(String type,
            CertStoreParameters params, Provider provider)
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        if (provider == null) {
            throw new IllegalArgumentException();
        }
        if (type == null) {
            throw new NullPointerException();
        }
        try {
            Object spi = ENGINE.getInstance(type, provider, params);
            return new CertStore((CertStoreSpi) spi, provider, type, params);
        } catch (NoSuchAlgorithmException e) {
            Throwable th = e.getCause();
            if (th == null) {
                throw e;
            } else {
                throw new InvalidAlgorithmParameterException(e.getMessage(), th);
            }
        }
    }

    /**
     * Returns the certificate store type.
     *
     * @return the certificate store type.
     */
    public final String getType() {
        return type;
    }

    /**
     * Returns the security provider.
     *
     * @return the security provider.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Returns a copy of the certificate store parameters that were used to
     * initialize this instance.
     *
     * @return a copy of the certificate store parameters or {@code null} if
     *         none were specified.
     */
    public final CertStoreParameters getCertStoreParameters() {
        if (certStoreParams == null) {
            return null;
        } else {
            return (CertStoreParameters) certStoreParams.clone();
        }
    }

    /**
     * Returns the list of {@code Certificate}s for the specified {@code
     * CertSelector} from this certificate store.
     *
     * @param selector
     *            the selector containing the criteria to search for
     *            certificates in this certificate store.
     * @return the list of {@code Certificate}s that match the criteria of the
     *         specified selector.
     * @throws CertStoreException
     *             if error(s) occur.
     */
    public final Collection<? extends Certificate> getCertificates(CertSelector selector)
            throws CertStoreException {
        return spiImpl.engineGetCertificates(selector);
    }

    /**
     * Returns the list of {@code CRL}s for the specified {@code CRLSelector}
     * from this certificate store.
     *
     * @param selector
     *            the selector containing the criteria to search for certificate
     *            revocation lists in this store.
     * @return the list of {@code CRL}s that match the criteria of the specified
     *         selector
     * @throws CertStoreException
     *             if error(s) occur.
     */
    public final Collection<? extends CRL> getCRLs(CRLSelector selector)
            throws CertStoreException {
        return spiImpl.engineGetCRLs(selector);
    }

    /**
     * Returns the default {@code CertStore} type from the <i>Security
     * Properties</i>.
     *
     * @return the default {@code CertStore} type from the <i>Security
     *         Properties</i>, or the string {@code "LDAP"} if it cannot be
     *         determined.
     */
    public static final String getDefaultType() {
        String defaultType = Security.getProperty(PROPERTYNAME);
        return (defaultType == null ? DEFAULTPROPERTY : defaultType);
    }
}
