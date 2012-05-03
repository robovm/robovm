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

package javax.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import org.apache.harmony.security.fortress.Engine;


/**
 * The public API for {@code SecretKeyFactory} implementations.
 * <p>
 * Secret key factories provide the following functionality:
 * <ul>
 * <li>convert {@link SecretKey} objects to and from {@link KeySpec} objects</li>
 * <li>translate {@link SecretKey} objects from one provider implementation to
 * another</li>
 * </ul>
 * Which key specifications are supported by the {@link #generateSecret} and
 * {@link #getKeySpec} is provider dependent.
 */
public class SecretKeyFactory {

    // Used to access common engine functionality
    private static final Engine ENGINE = new Engine("SecretKeyFactory");

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final SecretKeyFactorySpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    /**
     * Creates a new {@code SecretKeyFactory}
     *
     * @param keyFacSpi
     *            the SPI delegate.
     * @param provider
     *            the provider providing this key factory.
     * @param algorithm
     *            the algorithm name for the secret key.
     */
    protected SecretKeyFactory(SecretKeyFactorySpi keyFacSpi,
            Provider provider, String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyFacSpi;
    }

    /**
     * Returns the name of the secret key algorithm.
     *
     * @return the name of the secret key algorithm.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider for this {@code SecretKeyFactory} instance.
     *
     * @return the provider for this {@code SecretKeyFactory} instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Creates a new {@code SecretKeyFactory} instance for the specified key
     * algorithm.
     *
     * @param algorithm
     *            the name of the key algorithm.
     * @return a secret key factory for the specified key algorithm.
     * @throws NoSuchAlgorithmException
     *             if no installed provider can provide the requested algorithm.
     * @throws NullPointerException
     *             if the specified algorithm is {@code null}.
     */
    public static final SecretKeyFactory getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Engine.SpiAndProvider sap = ENGINE.getInstance(algorithm, null);
        return new SecretKeyFactory((SecretKeyFactorySpi) sap.spi, sap.provider, algorithm);
    }

    /**
     * Creates a new {@code SecretKeyFactory} instance for the specified key
     * algorithm from the specified {@code provider}.
     *
     * @param algorithm
     *            the name of the key algorithm.
     * @param provider
     *            the name of the provider that provides the requested
     *            algorithm.
     * @return a secret key factory for the specified key algorithm from the
     *         specified provider.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the requested
     *             algorithm.
     * @throws NoSuchProviderException
     *             if the specified provider does not exist.
     * @throws IllegalArgumentException
     *             if the specified provider name is {@code null} or empty.
     */
    public static final SecretKeyFactory getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (provider == null || provider.isEmpty()) {
            throw new IllegalArgumentException("Provider is null or empty");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(algorithm, impProvider);
    }

    /**
     * Creates a new {@code SecretKeyFactory} instance for the specified key
     * algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the key algorithm.
     * @param provider
     *            the provider that provides the requested algorithm.
     * @return a secret key factory for the specified key algorithm from the
     *         specified provider.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provider the requested
     *             algorithm.
     * @throws IllegalArgumentException
     *             if the specified provider is {@code null}.
     * @throws NullPointerException
     *             is the specified algorithm name is {@code null}.
     */
    public static final SecretKeyFactory getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Object spi = ENGINE.getInstance(algorithm, provider, null);
        return new SecretKeyFactory((SecretKeyFactorySpi) spi, provider, algorithm);
    }

    /**
     * Generate a secret key from the specified key specification.
     *
     * @param keySpec
     *            the key specification.
     * @return a secret key.
     * @throws InvalidKeySpecException
     *             if the specified key specification cannot be used to generate
     *             a secret key.
     */
    public final SecretKey generateSecret(KeySpec keySpec)
            throws InvalidKeySpecException {
        return spiImpl.engineGenerateSecret(keySpec);
    }

    /**
     * Returns the key specification of the specified secret key.
     *
     * @param key
     *            the secret key to get the specification from.
     * @param keySpec
     *            the target key specification class.
     * @return an instance of the specified key specification class.
     * @throws InvalidKeySpecException
     *             if the specified secret key cannot be transformed into the
     *             requested key specification.
     */
    @SuppressWarnings("unchecked")
    public final KeySpec getKeySpec(SecretKey key, Class keySpec)
            throws InvalidKeySpecException {
        return spiImpl.engineGetKeySpec(key, keySpec);
    }

    /**
     * Translates the specified secret key into an instance of the corresponding
     * key from the provider of this key factory.
     *
     * @param key
     *            the secret key to translate.
     * @return the corresponding translated key.
     * @throws InvalidKeyException
     *             if the specified key cannot be translated using this key
     *             factory.
     */
    public final SecretKey translateKey(SecretKey key)
            throws InvalidKeyException {
        return spiImpl.engineTranslateKey(key);

    }
}
