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

import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;

import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.internal.nls.Messages;


/**
 * This class implements the functionality of a builder for an unverified
 * <i>Certification Path</i>s from a specified certificate to a trust anchor.
 */
public class CertPathBuilder {

    // Store CertPathBuilder service name
    private static final String SERVICE = "CertPathBuilder"; //$NON-NLS-1$

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // Store default property name
    private static final String PROPERTYNAME = "certpathbuilder.type"; //$NON-NLS-1$

    // Default value of CertPathBuilder type. It returns if certpathbuild.type
    // property is not defined in java.security file
    private static final String DEFAULTPROPERTY = "PKIX"; //$NON-NLS-1$

    // Store used provider
    private final Provider provider;

    // Store spi implementation
    private CertPathBuilderSpi spiImpl;

    // Store algorithm name
    private final String algorithm;

    /**
     * Creates a new {@code CertPathBuilder}.
     *
     * @param builderSpi
     *            the implementation delegate.
     * @param provider
     *            the provider.
     * @param algorithm
     *            the desired algorithm available at the provider.
     */
    protected CertPathBuilder(CertPathBuilderSpi builderSpi, Provider provider,
            String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = builderSpi;
    }

    /**
     * Returns the algorithm name of this instance.
     *
     * @return the algorithm name of this instance.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider of this instance.
     *
     * @return the provider of this instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Creates a new {@code CertPathBuilder} instance with the specified
     * algorithm.
     * 
     * @param algorithm
     *            the name of the algorithm.
     * @return a builder for the requested algorithm.
     * @throws NullPointerException
     *             if the algorithm is {@code null}.
     * @throws NoSuchAlgorithmException
     *             if no installed provider can provide the algorithm.
     */
    public static CertPathBuilder getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("security.01")); //$NON-NLS-1$
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new CertPathBuilder((CertPathBuilderSpi) engine.spi,
                    engine.provider, algorithm);
        }
    }

    /**
     * Creates a new {@code CertPathBuilder} instance from the specified
     * provider providing the specified algorithm.
     * 
     * @param algorithm
     *            the name of the algorithm.
     * @param provider
     *            the name of the provider.
     * @return a builder for the requested algorithm.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the algorithm.
     * @throws NoSuchProviderException
     *             if no provider with the specified name can be found.
     * @throws NullPointerException
     *             if algorithm is {@code null}.
     * @throws IllegalArgumentException
     *             if provider is {@code null} or empty.
     */
    public static CertPathBuilder getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(Messages.getString("security.02")); //$NON-NLS-1$
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(algorithm, impProvider);

    }

    /**
     * Creates a new {@code CertPathBuilder} instance from the specified
     * provider providing the specified algorithm.
     * 
     * @param algorithm
     *            the name of the algorithm.
     * @param provider
     *            the provider.
     * @return a builder for the requested algorithm
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the algorithm.
     * @throws IllegalArgumentException
     *             if provider is {@code null}.
     * @throws NullPointerException
     *             if algorithm is {@code null}.
     */
    public static CertPathBuilder getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException(Messages.getString("security.04")); //$NON-NLS-1$
        }
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("security.01")); //$NON-NLS-1$
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new CertPathBuilder((CertPathBuilderSpi) engine.spi, provider,
                    algorithm);
        }
    }

    /**
     * Builds a certification path with the specified algorithm parameters.
     *
     * @param params
     *            the algorithm parameters.
     * @return the built certification path.
     * @throws CertPathBuilderException
     *             if the build fails.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters cannot be used to build with this
     *             builder.
     * @see CertPathBuilderResult
     */
    public final CertPathBuilderResult build(CertPathParameters params)
            throws CertPathBuilderException, InvalidAlgorithmParameterException {
        return spiImpl.engineBuild(params);
    }

    /**
     * Returns the default {@code CertPathBuilder} type from the <i>Security
     * Properties</i>.
     *
     * @return the default {@code CertPathBuilder} type from the <i>Security
     *         Properties</i>, or the string "{@code PKIX}" if it cannot be
     *         determined.
     */
    public static final String getDefaultType() {
        String defaultType = AccessController
                .doPrivileged(new java.security.PrivilegedAction<String>() {
                    public String run() {
                        return Security.getProperty(PROPERTYNAME);
                    }
                });
        return (defaultType != null ? defaultType : DEFAULTPROPERTY);
    }
}