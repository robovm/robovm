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

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import org.apache.harmony.security.fortress.Engine;


/**
 * This class provides the public API for <i>Message Authentication Code</i>
 * (MAC) algorithms.
 */
public class Mac implements Cloneable {

    //Used to access common engine functionality
    private static final Engine ENGINE = new Engine("Mac");

    // Store used provider
    private final Provider provider;

    // Store used spi implementation
    private final MacSpi spiImpl;

    // Store used algorithm name
    private final String algorithm;

    // Store Mac state (initialized or not initialized)
    private boolean isInitMac;

    /**
     * Creates a new {@code Mac} instance.
     *
     * @param macSpi
     *            the implementation delegate.
     * @param provider
     *            the implementation provider.
     * @param algorithm
     *            the name of the MAC algorithm.
     */
    protected Mac(MacSpi macSpi, Provider provider, String algorithm) {
        this.provider = provider;
        this.algorithm = algorithm;
        this.spiImpl = macSpi;
        this.isInitMac = false;
    }

    /**
     * Returns the name of the MAC algorithm.
     *
     * @return the name of the MAC algorithm.
     */
    public final String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the provider of this {@code Mac} instance.
     *
     * @return the provider of this {@code Mac} instance.
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * Creates a new {@code Mac} instance that provides the specified MAC
     * algorithm.
     *
     * @param algorithm
     *            the name of the requested MAC algorithm.
     * @return the new {@code Mac} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not available by any provider.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null} (instead of
     *             NoSuchAlgorithmException as in 1.4 release).
     */
    public static final Mac getInstance(String algorithm)
            throws NoSuchAlgorithmException {
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Engine.SpiAndProvider sap = ENGINE.getInstance(algorithm, null);
        return new Mac((MacSpi) sap.spi, sap.provider, algorithm);
    }

    /**
     * Creates a new {@code Mac} instance that provides the specified MAC
     * algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested MAC algorithm.
     * @param provider
     *            the name of the provider that is providing the algorithm.
     * @return the new {@code Mac} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws NoSuchProviderException
     *             if the specified provider is not available.
     * @throws IllegalArgumentException
     *             if the specified provider name is {@code null} or empty.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null} (instead of
     *             NoSuchAlgorithmException as in 1.4 release).
     */
    public static final Mac getInstance(String algorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
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
     * Creates a new {@code Mac} instance that provides the specified MAC
     * algorithm from the specified provider.
     *
     * @param algorithm
     *            the name of the requested MAC algorithm.
     * @param provider
     *            the provider that is providing the algorithm.
     * @return the new {@code Mac} instance.
     * @throws NoSuchAlgorithmException
     *             if the specified algorithm is not provided by the specified
     *             provider.
     * @throws IllegalArgumentException
     *             if {@code provider} is {@code null}.
     * @throws NullPointerException
     *             if {@code algorithm} is {@code null} (instead of
     *             NoSuchAlgorithmException as in 1.4 release).
     */
    public static final Mac getInstance(String algorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (provider == null) {
            throw new IllegalArgumentException("provider == null");
        }
        if (algorithm == null) {
            throw new NullPointerException();
        }
        Object spi = ENGINE.getInstance(algorithm, provider, null);
        return new Mac((MacSpi) spi, provider, algorithm);
    }

    /**
     * Returns the length of this MAC (in bytes).
     *
     * @return the length of this MAC (in bytes).
     */
    public final int getMacLength() {
        return spiImpl.engineGetMacLength();
    }

    /**
     * Initializes this {@code Mac} instance with the specified key and
     * algorithm parameters.
     *
     * @param key
     *            the key to initialize this algorithm.
     * @param params
     *            the parameters for this algorithm.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this
     *             algorithm, or it is null.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters cannot be used to initialize this
     *             algorithm.
     */
    public final void init(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        spiImpl.engineInit(key, params);
        isInitMac = true;
    }

    /**
     * Initializes this {@code Mac} instance with the specified key.
     *
     * @param key
     *            the key to initialize this algorithm.
     * @throws InvalidKeyException
     *             if initialization fails because the provided key is {@code
     *             null}.
     * @throws RuntimeException
     *             if the specified key cannot be used to initialize this
     *             algorithm.
     */
    public final void init(Key key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        }
        try {
            spiImpl.engineInit(key, null);
            isInitMac = true;
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates this {@code Mac} instance with the specified byte.
     *
     * @param input
     *            the byte
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final void update(byte input) throws IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        spiImpl.engineUpdate(input);
    }

    /**
     * Updates this {@code Mac} instance with the data from the specified buffer
     * {@code input} from the specified {@code offset} and length {@code len}.
     *
     * @param input
     *            the buffer.
     * @param offset
     *            the offset in the buffer.
     * @param len
     *            the length of the data in the buffer.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     * @throws IllegalArgumentException
     *             if {@code offset} and {@code len} do not specified a valid
     *             chunk in {@code input} buffer.
     */
    public final void update(byte[] input, int offset, int len) throws IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        if (input == null) {
            return;
        }
        if ((offset < 0) || (len < 0) || ((offset + len) > input.length)) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        spiImpl.engineUpdate(input, offset, len);
    }

    /**
     * Copies the buffer provided as input for further processing.
     *
     * @param input
     *            the buffer.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final void update(byte[] input) throws IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        if (input != null) {
            spiImpl.engineUpdate(input, 0, input.length);
        }
    }

    /**
     * Updates this {@code Mac} instance with the data from the specified
     * buffer, starting at {@link ByteBuffer#position()}, including the next
     * {@link ByteBuffer#remaining()} bytes.
     *
     * @param input
     *            the buffer.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final void update(ByteBuffer input) {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        if (input != null) {
            spiImpl.engineUpdate(input);
        } else {
            throw new IllegalArgumentException("input == null");
        }
    }

    /**
     * Computes the digest of this MAC based on the data previously specified in
     * {@link #update} calls.
     * <p>
     * This {@code Mac} instance is reverted to its initial state and can be
     * used to start the next MAC computation with the same parameters or
     * initialized with different parameters.
     *
     * @return the generated digest.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final byte[] doFinal() throws IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        return spiImpl.engineDoFinal();
    }

    /**
     * Computes the digest of this MAC based on the data previously specified in
     * {@link #update} calls and stores the digest in the specified {@code
     * output} buffer at offset {@code outOffset}.
     * <p>
     * This {@code Mac} instance is reverted to its initial state and can be
     * used to start the next MAC computation with the same parameters or
     * initialized with different parameters.
     *
     * @param output
     *            the output buffer
     * @param outOffset
     *            the offset in the output buffer
     * @throws ShortBufferException
     *             if the specified output buffer is either too small for the
     *             digest to be stored, the specified output buffer is {@code
     *             null}, or the specified offset is negative or past the length
     *             of the output buffer.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final void doFinal(byte[] output, int outOffset)
            throws ShortBufferException, IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        if (output == null) {
            throw new ShortBufferException("output == null");
        }
        if ((outOffset < 0) || (outOffset >= output.length)) {
            throw new ShortBufferException("Incorrect outOffset: " + outOffset);
        }
        int t = spiImpl.engineGetMacLength();
        if (t > (output.length - outOffset)) {
            throw new ShortBufferException("Output buffer is short. Needed " + t + " bytes.");
        }
        byte[] result = spiImpl.engineDoFinal();
        System.arraycopy(result, 0, output, outOffset, result.length);

    }

    /**
     * Computes the digest of this MAC based on the data previously specified on
     * {@link #update} calls and on the final bytes specified by {@code input}
     * (or based on those bytes only).
     * <p>
     * This {@code Mac} instance is reverted to its initial state and can be
     * used to start the next MAC computation with the same parameters or
     * initialized with different parameters.
     *
     * @param input
     *            the final bytes.
     * @return the generated digest.
     * @throws IllegalStateException
     *             if this MAC is not initialized.
     */
    public final byte[] doFinal(byte[] input) throws IllegalStateException {
        if (!isInitMac) {
            throw new IllegalStateException();
        }
        if (input != null) {
            spiImpl.engineUpdate(input, 0, input.length);
        }
        return spiImpl.engineDoFinal();
    }

    /**
     * Resets this {@code Mac} instance to its initial state.
     * <p>
     * This {@code Mac} instance is reverted to its initial state and can be
     * used to start the next MAC computation with the same parameters or
     * initialized with different parameters.
     */
    public final void reset() {
        spiImpl.engineReset();
    }

    /**
     * Clones this {@code Mac} instance and the underlying implementation.
     *
     * @return the cloned instance.
     * @throws CloneNotSupportedException
     *             if the underlying implementation does not support cloning.
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        MacSpi newSpiImpl = (MacSpi)spiImpl.clone();
        Mac mac = new Mac(newSpiImpl, this.provider, this.algorithm);
        mac.isInitMac = this.isInitMac;
        return mac;
    }
}
