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

/**
* @author Vera Y. Petrashkova
*/

package javax.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import org.apache.harmony.crypto.internal.nls.Messages;
import org.apache.harmony.security.fortress.Engine;


/**
 * This class provides the public API for generating symmetric cryptographic
 * keys.
 */
public class KeyGenerator {

    // Used to access common engine functionality
    private static final Engine engine = new Engine("KeyGenerator"); //$NON-NLS-1$

    // Store SecureRandom
    private static final SecureRandom rndm = new SecureRandom();

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final KeyGeneratorSpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    /**
     * Creates a new {@code KeyGenerator} instance.
     *
     * @param keyGenSpi
     *            the implementation delegate.
     * @param provider
     *            the implementation provider.
     * @param algorithm
     *            the name of the algorithm.
     */
    protected KeyGenerator(KeyGeneratorSpi keyGenSpi, Provider provider,
            String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyGenSpi;
    }

    /**
     * Returns the name of the key generation algorithm.
     *
     * @return the name of the key generation algorithm.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider of this {@code KeyGenerator} instance.
     *
     * @return the provider of this {@code KeyGenerator} instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Creates a new {@code KeyGenerator} instance that provides the specified
     * key algorithm,
     *
     * @param algorithm
     *            the name of the requested key algorithm
     * @return the new {@code KeyGenerator} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available by any provider.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null}.
     */
    public static final KeyGenerator getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("crypto.02")); //$NON-NLS-1$
        }
        synchronized (engine) {
            engine.getInstance(algorithm, null);
            return new KeyGenerator((KeyGeneratorSpi) engine.spi, engine.provider,
                    algorithm);
        }
    }

    /**
     * Creates a new {@code KeyGenerator} instance that provides the specified
     * key algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested key algorithm.
     * @param provider
     *            the name of the provider that is providing the algorithm.
     * @return the new {@code KeyGenerator} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws NoSuchProviderException
     *             if the specified provider is not available.
     * @throws IllegalArgumentException
     *             if the specified provider is name is {@code null} or empty.
     * @throws NullPointerException
     *             if the specified algorithm name is {@code null}.
     */
    public static final KeyGenerator getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException(Messages.getString("crypto.03")); //$NON-NLS-1$
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(algorithm, impProvider);
    }

    /**
     * Creates a new {@code KeyGenerator} instance that provides the specified
     * key algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested key algorithm.
     * @param provider
     *            the provider that is providing the algorithm
     * @return the new {@code KeyGenerator} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws IllegalArgumentException
     *             if the specified provider is {@code null}.
     * @throws NullPointerException
     *             if the specified algorithm name is {@code null}.
     */
    public static final KeyGenerator getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException(Messages.getString("crypto.04")); //$NON-NLS-1$
        }
        if (algorithm == null) {
            throw new NullPointerException(Messages.getString("crypto.02")); //$NON-NLS-1$
        }
        synchronized (engine) {
            engine.getInstance(algorithm, provider, null);
            return new KeyGenerator((KeyGeneratorSpi) engine.spi, provider,
                    algorithm);
        }
    }

    /**
     * Generates a secret key.
     *
     * @return the generated secret key.
     */
    public final SecretKey generateKey() {
        return spiImpl.engineGenerateKey();
    }

    /**
     * Initializes this {@code KeyGenerator} instance with the specified
     * algorithm parameters.
     *
     * @param params
     *            the parameters for the key generation algorithm.
     * @throws InvalidAlgorithmParameterException
     *             if the parameters cannot be used to initialize this key
     *             generator algorithm.
     */
    public final void init(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
        spiImpl.engineInit(params, rndm);//new SecureRandom());
    }

    /**
     * Initializes this {@code KeyGenerator} instance with the specified
     * algorithm parameters and randomness source.
     *
     * @param params
     *            the parameters for the key generation algorithm.
     * @param random
     *            the randomness source for any random bytes.
     * @throws InvalidAlgorithmParameterException
     *             if the parameters cannot be uses to initialize this key
     *             generator algorithm.
     */
    public final void init(AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        spiImpl.engineInit(params, random);
    }

    /**
     * Initializes this {@code KeyGenerator} instance for the specified key size
     * (in bits).
     *
     * @param keysize
     *            the size of the key (in bits).
     */
    public final void init(int keysize) {
        spiImpl.engineInit(keysize, rndm);//new SecureRandom());
    }

    /**
     * Initializes this {@code KeyGenerator} instance for the specified key size
     * (in bits) using the specified randomness source.
     *
     * @param keysize
     *            the size of the key (in bits).
     * @param random
     *            the randomness source for any random bytes.
     */
    public final void init(int keysize, SecureRandom random) {
        spiImpl.engineInit(keysize, random);
    }

    /**
     * Initializes this {@code KeyGenerator} with the specified randomness
     * source.
     *
     * @param random
     *            the randomness source for any random bytes.
     */
    public final void init(SecureRandom random) {
        spiImpl.engineInit(random);
    }
}