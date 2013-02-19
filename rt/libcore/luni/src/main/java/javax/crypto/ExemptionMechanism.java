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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import org.apache.harmony.security.fortress.Engine;

/**
 * This class implements the functionality of an exemption mechanism such as
 * <i>key recovery</i>, <i>key weakening</i>, or <i>key escrow</i>.
 */
public class ExemptionMechanism {

    // Used to access common engine functionality
    private static final Engine ENGINE = new Engine("ExemptionMechanism");

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final ExemptionMechanismSpi spiImpl;

    // Store mechanism name
    private final String mechanism;

    // Store state (initialized or not)
    private boolean isInit;

    // Store initKey value
    private Key initKey;

    // Indicates if blob generated successfully
    private boolean generated;

    /**
     * Creates a {@code ExemptionMechanism} instance.
     *
     * @param exmechSpi
     *            the implementation delegate.
     * @param provider
     *            the associated provider.
     * @param mechanism
     *            the name of the mechanism.
     */
    protected ExemptionMechanism(ExemptionMechanismSpi exmechSpi,
            Provider provider, String mechanism) {
        this.mechanism = mechanism;
        this.spiImpl = exmechSpi;
        this.provider = provider;
        isInit = false;
    }

    /**
     * Returns the name of this {@code ExemptionMechanism}.
     *
     * @return the name of this {@code ExemptionMechanism}.
     */
    public final String getName() {
        return mechanism;
    }

    /**
     * Returns a new {@code ExemptionMechanism} instance that provides the
     * specified exemption mechanism algorithm.
     *
     * @param algorithm
     *            the name of the requested exemption mechanism.
     * @return the new {@code ExemptionMechanism} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available by any provider.
     * @throws NullPointerException
     *             if the algorithm parameter is {@code null}.
     */
    public static final ExemptionMechanism getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Engine.SpiAndProvider sap = ENGINE.getInstance(algorithm, null);
        return new ExemptionMechanism((ExemptionMechanismSpi) sap.spi, sap.provider, algorithm);
    }

    /**
     * Returns a new {@code ExemptionMechansm} instance that provides the
     * specified exemption mechanism algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested exemption mechanism.
     * @param provider
     *            the name of the provider that is providing the algorithm.
     * @return the new {@code ExemptionMechanism} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws NoSuchProviderException
     *             if the specified provider is not available.
     * @throws NullPointerException
     *             if the algorithm parameter is {@code null}.
     * @throws IllegalArgumentException
     *             if the provider parameter is {@code null}.
     */
    public static final ExemptionMechanism getInstance(String algorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        if (algorithm == null) {
            throw new NullPointerException();
        }
        return getInstance(algorithm, impProvider);
    }

    /**
     * Returns a new {@code ExemptionMechanism} instance that provides the
     * specified exemption mechanism algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested exemption mechanism.
     * @param provider
     *            the provider that is providing the algorithm.
     * @return the new {@code ExemptionMechanism} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws NullPointerException
     *             if the algorithm parameter is {@code null}.
     * @throws IllegalArgumentException
     *             if the provider parameter is {@code null}.
     */
    public static final ExemptionMechanism getInstance(String algorithm,
            Provider provider) throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        Object spi = ENGINE.getInstance(algorithm, provider, null);
        return new ExemptionMechanism((ExemptionMechanismSpi) spi, provider, algorithm);
    }

    /**
     * Returns the provider of this {@code ExemptionMechanism} instance.
     *
     * @return the provider of this {@code ExemptionMechanism} instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Returns whether the result blob for this {@code ExemptionMechanism}
     * instance has been generated successfully and that the specified key is
     * the same as the one that was used to initialize and generate.
     *
     * @param key
     *            the key to verify.
     * @return whether the result blob for this {@code ExemptionMechanism}
     *         instance has been generated successfully.
     * @throws ExemptionMechanismException
     *             if an error occurs while determining whether the result blob
     *             has been generated successfully.
     */
    public final boolean isCryptoAllowed(Key key)
            throws ExemptionMechanismException {

        if (generated
                && (initKey.equals(key) || Arrays.equals(initKey.getEncoded(),
                        key.getEncoded()))) {
            return true;
        }
        return false;
    }

    /**
     * Returns the size in bytes for the output buffer needed to hold the output
     * of the next {@link #genExemptionBlob} call, given the specified {@code
     * inputLen} (in bytes).
     *
     * @param inputLen
     *            the specified input length (in bytes).
     * @return the size in bytes for the output buffer.
     * @throws IllegalStateException
     *             if this {@code ExemptionMechanism} instance is not
     *             initialized.
     */
    public final int getOutputSize(int inputLen) throws IllegalStateException {
        if (!isInit) {
            throw new IllegalStateException("ExemptionMechanism is not initialized");
        }
        return spiImpl.engineGetOutputSize(inputLen);
    }

    /**
     * Initializes this {@code ExemptionMechanism} instance with the
     * specified key.
     *
     * @param key
     *            the key to initialize this instance with.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    public final void init(Key key) throws InvalidKeyException,
            ExemptionMechanismException {
        generated = false;
        spiImpl.engineInit(key);
        initKey = key;
        isInit = true;
    }

    /**
     * Initializes this {@code ExemptionMechanism} instance with the
     * specified key and algorithm parameters.
     *
     * @param key
     *            the key to initialize this instance with.
     * @param param
     *            the parameters for this exemption mechanism algorithm.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws InvalidAlgorithmParameterException
     *             if the parameters cannot be used to initialize this
     *             mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    public final void init(Key key, AlgorithmParameters param)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        generated = false;
        spiImpl.engineInit(key, param);
        initKey = key;
        isInit = true;
    }

    /**
     * Initializes this {@code ExemptionMechanism} instance with the
     * specified key and algorithm parameters.
     *
     * @param key
     *            the key to initialize this instance with.
     * @param param
     *            the parameters for this exemption mechanism algorithm.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws InvalidAlgorithmParameterException
     *             the the parameters cannot be used to initialize this
     *             mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    public final void init(Key key, AlgorithmParameterSpec param)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        generated = false;
        spiImpl.engineInit(key, param);
        initKey = key;
        isInit = true;
    }

    /**
     * Generates the result key blob for this exemption mechanism.
     *
     * @return the result key blob for this exemption mechanism.
     * @throws IllegalStateException
     *             if this {@code ExemptionMechanism} instance is not
     *             initialized.
     * @throws ExemptionMechanismException
     *             if error(s) occur during generation.
     */
    public final byte[] genExemptionBlob() throws IllegalStateException,
            ExemptionMechanismException {
        if (!isInit) {
            throw new IllegalStateException("ExemptionMechanism is not initialized");
        }
        generated = false;
        byte[] result = spiImpl.engineGenExemptionBlob();
        generated = true;
        return result;
    }

    /**
     * Generates the result key blob for this exemption mechanism and stores it
     * into the {@code output} buffer.
     *
     * @param output
     *            the output buffer for the result key blob.
     * @return the number of bytes written to the {@code output} buffer.
     * @throws IllegalStateException
     *             if this {@code ExemptionMechanism} instance is not
     *             initialized.
     * @throws ShortBufferException
     *             if the provided buffer is too small for the result key blob.
     * @throws ExemptionMechanismException
     *             if error(s) occur during generation.
     */
    public final int genExemptionBlob(byte[] output)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        return genExemptionBlob(output, 0);
    }

    /**
     * Generates the result key blob for this exemption mechanism and stores it
     * into the {@code output} buffer at offset {@code outputOffset}.
     *
     * @param output
     *            the output buffer for the result key blob.
     * @param outputOffset
     *            the offset in the output buffer to start.
     * @return the number of bytes written to the {@code output} buffer.
     * @throws IllegalStateException
     *             if this {@code ExemptionMechanism} instance is not
     *             initialized.
     * @throws ShortBufferException
     *             if the provided buffer is too small for the result key blob.
     * @throws ExemptionMechanismException
     *             if error(s) occur during generation.
     */
    public final int genExemptionBlob(byte[] output, int outputOffset)
            throws IllegalStateException, ShortBufferException,
            ExemptionMechanismException {
        if (!isInit) {
            throw new IllegalStateException("ExemptionMechanism is not initialized");
        }
        generated = false;
        int len = spiImpl.engineGenExemptionBlob(output, outputOffset);
        generated = true;
        return len;
    }

    /**
     * Override to clear any key state in the instance.
     */
    @Override protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable t) {
            // for consistency with the RI, we must override Object.finalize() to
            // remove the throws clause.
            throw new AssertionError(t);
        }
    }
}
