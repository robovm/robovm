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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import org.apache.harmony.security.fortress.Engine;

/**
 * This class provides the functionality for a key exchange protocol. This
 * enables two or more parties to agree on a secret key for symmetric
 * cryptography.
 */
public class KeyAgreement {

    // Used to access common engine functionality
    private static final Engine ENGINE = new Engine("KeyAgreement");

    // Store SecureRandom
    private static final SecureRandom RANDOM = new SecureRandom();

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final KeyAgreementSpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    /**
     * Creates a new {@code KeyAgreement} instance.
     *
     * @param keyAgreeSpi
     *            the <b>SPI</b> delegate.
     * @param provider
     *            the provider providing this KeyAgreement.
     * @param algorithm
     *            the name of the key agreement algorithm.
     */
    protected KeyAgreement(KeyAgreementSpi keyAgreeSpi, Provider provider,
            String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = keyAgreeSpi;
    }

    /**
     * Returns the name of the key agreement algorithm.
     *
     * @return the name of the key agreement algorithm.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider for this {@code KeyAgreement} instance.
     *
     * @return the provider for this {@code KeyAgreement} instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Creates a new {@code KeyAgreement} for the specified algorithm.
     *
     * @param algorithm
     *            the name of the key agreement algorithm to create.
     * @return a key agreement for the specified algorithm.
     * @throws NoSuchAlgorithmException
     *             if no installed provider can provide the requested algorithm.
     * @throws NullPointerException
     *             if the specified algorithm is {@code null}.
     */
    public static final KeyAgreement getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Engine.SpiAndProvider sap = ENGINE.getInstance(algorithm, null);
        return new KeyAgreement((KeyAgreementSpi) sap.spi, sap.provider, algorithm);
    }

    /**
     * Creates a new {@code KeyAgreement} for the specified algorithm from the
     * specified provider.
     *
     * @param algorithm
     *            the name of the key agreement algorithm to create.
     * @param provider
     *            the name of the provider that provides the requested
     *            algorithm.
     * @return a key agreement for the specified algorithm from the specified
     *         provider.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the requested
     *             algorithm.
     * @throws NoSuchProviderException
     *             if the specified provider does not exist.
     * @throws IllegalArgumentException
     *             if the specified provider name is {@code null} or empty.
     */
    public static final KeyAgreement getInstance(String algorithm,
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
     * Create a new {@code KeyAgreement} for the specified algorithm from the
     * specified provider.
     *
     * @param algorithm
     *            the name of the key agreement algorithm to create.
     * @param provider
     *            the provider that provides the requested algorithm.
     * @return a key agreement for the specified algorithm from the specified
     *         provider.
     * @throws NoSuchAlgorithmException
     *             if the specified provider cannot provide the requested
     *             algorithm.
     * @throws IllegalArgumentException
     *             if the specified provider is {@code null}.
     * @throws NullPointerException
     *             if the specified algorithm name is {@code null}.
     */
    public static final KeyAgreement getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Object spi = ENGINE.getInstance(algorithm, provider, null);
        return new KeyAgreement((KeyAgreementSpi) spi, provider, algorithm);
    }

    /**
     * Initializes this {@code KeyAgreement} with the specified key.
     *
     * @param key
     *            the key to initialize this key agreement.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this key
     *             agreement.
     */
    public final void init(Key key) throws InvalidKeyException {
        spiImpl.engineInit(key, RANDOM);//new SecureRandom());
    }

    /**
     * Initializes this {@code KeyAgreement} with the specified key and the
     * specified randomness source.
     *
     * @param key
     *            the key to initialize this key agreement.
     * @param random
     *            the source for any randomness needed.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this key
     *             agreement.
     */
    public final void init(Key key, SecureRandom random)
            throws InvalidKeyException {
        spiImpl.engineInit(key, random);
    }

    /**
     * Initializes this {@code KeyAgreement} with the specified key and the
     * algorithm parameters.
     *
     * @param key
     *            the key to initialize this key agreement.
     * @param params
     *            the parameters for this key agreement algorithm.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this key
     *             agreement.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters are invalid for this key
     *             agreement algorithm.
     */
    public final void init(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        spiImpl.engineInit(key, params, RANDOM);//new SecureRandom());
    }

    /**
     * Initializes this {@code KeyAgreement} with the specified key, algorithm
     * parameters and randomness source.
     *
     * @param key
     *            the key to initialize this key agreement.
     * @param params
     *            the parameters for this key agreement algorithm.
     * @param random
     *            the source for any randomness needed.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this key
     *             agreement.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters are invalid for this key
     *             agreement algorithm.
     */
    public final void init(Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        spiImpl.engineInit(key, params, random);
    }

    /**
     * Does the next (or the last) phase of the key agreement, using the
     * specified key.
     *
     * @param key
     *            the key received from the other party for this phase.
     * @param lastPhase
     *            set to {@code true} if this is the last phase of this key
     *            agreement.
     * @return the intermediate key from this phase or {@code null} if there is
     *         no intermediate key for this phase.
     * @throws InvalidKeyException
     *             if the specified key cannot be used in this key agreement or
     *             this phase,
     * @throws IllegalStateException
     *             if this instance has not been initialized.
     */
    public final Key doPhase(Key key, boolean lastPhase)
            throws InvalidKeyException, IllegalStateException {
        return spiImpl.engineDoPhase(key, lastPhase);
    }

    /**
     * Generates the shared secret.
     *
     * @return the generated shared secret.
     * @throws IllegalStateException
     *             if this key agreement is not complete.
     */
    public final byte[] generateSecret() throws IllegalStateException {
        return spiImpl.engineGenerateSecret();
    }

    /**
     * Generates the shared secret and stores it into the buffer {@code
     * sharedSecred} at {@code offset}.
     *
     * @param sharedSecret
     *            the buffer to store the shared secret.
     * @param offset
     *            the offset in the buffer.
     * @return the number of bytes stored in the buffer.
     * @throws IllegalStateException
     *             if this key agreement is not complete.
     * @throws ShortBufferException
     *             if the specified buffer is too small for the shared secret.
     */
    public final int generateSecret(byte[] sharedSecret, int offset)
            throws IllegalStateException, ShortBufferException {
        return spiImpl.engineGenerateSecret(sharedSecret, offset);
    }

    /**
     * Generates the shared secret.
     *
     * @param algorithm
     *            the algorithm to for the {@code SecretKey}
     * @return the shared secret as a {@code SecretKey} of the specified
     *         algorithm.
     * @throws IllegalStateException
     *             if this key agreement is not complete.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm for the secret key does not
     *             exists.
     * @throws InvalidKeyException
     *             if a {@code SecretKey} with the specified algorithm cannot be
     *             created using the generated shared secret.
     */
    public final SecretKey generateSecret(String algorithm)
            throws IllegalStateException, NoSuchAlgorithmException,
            InvalidKeyException {
        return spiImpl.engineGenerateSecret(algorithm);
    }

}
