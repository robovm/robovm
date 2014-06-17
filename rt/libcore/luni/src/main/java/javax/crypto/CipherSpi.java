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
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>) for
 * cryptographic ciphers.
 * <p>
 * Implementers of cryptographic ciphers must implement all the abstract methods
 * for every cipher they implement. {@code CipherSpi} instances are created
 * along with ciphers when the {@link Cipher#getInstance} method is called. A
 * {@code Cipher} is referenced by a <i>transformation</i>, which is a string
 * that describes the operation (or set of operations), always consisting of the
 * cipher's name and optionally followed by a mode and a padding, in the form:
 * <ul>
 * <li>"algorithm"</li>or
 * <li>"algorithm/mode/padding"</li>
 * </ul>
 * The following behavior should be implemented for obtaining {@code Cipher}
 * instances.
 * <p>
 * When one of the {@link Cipher#getInstance} factory methods is called with a
 * <i>transformation</i> that is only an <i>algorithm</i>, check if the provider
 * defines a {@code CipherSpi} for "algorithm", if so: return it, otherwise
 * throw a {@link NoSuchAlgorithmException}.
 * <p>
 * The following rules apply when a <i>transformation</i> is of the form
 * "algorithm/mode/padding":
 * <ul>
 * 1. The Provider has a {@code CipherSpi} subclass registered for
 * "algorithm/mode/padding": return it, otherwise go to step 2.
 * </ul>
 * <ul>
 * 2. The Provider has a {@code CipherSpi} subclass registered for
 * "algorithm/mode": instantiate it, call
 * {@link CipherSpi#engineSetPadding(String) engineSetPadding(String)} for the
 * padding name and return it, otherwise go to step 3.
 * </ul>
 * <ul>
 * 3. The Provider has a {@code CipherSpi} subclass registered for
 * "algorithm//padding": instantiate it, call
 * {@link CipherSpi#engineSetMode(String) engineSetMode(String)} for the mode
 * name and return it, otherwise go to step 4.
 * </ul>
 * <ul>
 * 4. The Provider has a {@code CipherSpi} subclass registered for "algorithm":
 * instantiate it, call {@link CipherSpi#engineSetMode(String)
 * engineSetMode(String)} for the mode name , call
 * {@link CipherSpi#engineSetPadding(String) engineSetPadding(String)} for the
 * padding name and return it, otherwise throw a
 * {@link NoSuchAlgorithmException}.
 * </ul>
 *
 * @see Cipher
 */
public abstract class CipherSpi {

    /**
     * Creates a new {@code CipherSpi} instance.
     */
    public CipherSpi() {
    }

    /**
     * Sets the mode for this cipher.
     *
     * @param mode
     *            the name of the cipher mode.
     * @throws NoSuchAlgorithmException
     *             if the specified cipher mode is not supported by this
     *             provider.
     */
    protected abstract void engineSetMode(String mode)
            throws NoSuchAlgorithmException;

    /**
     * Sets the padding method for this cipher.
     *
     * @param padding
     *            the name of the padding method.
     * @throws NoSuchPaddingException
     *             if the specified padding method is not supported by this
     *             cipher.
     */
    protected abstract void engineSetPadding(String padding)
            throws NoSuchPaddingException;

    /**
     * Returns the block size of this cipher (in bytes)
     *
     * @return the block size of this cipher, or zero if this cipher is not a
     *         block cipher.
     */
    protected abstract int engineGetBlockSize();

    /**
     * Returns the size for a buffer (in bytes), that the next call to {@code
     * update} of {@code doFinal} would return, taking into account any buffered
     * data from previous {@code update} calls and padding.
     * <p>
     * The actual output length of the next call to {@code update} or {@code
     * doFinal} may be smaller than the length returned by this method.
     *
     * @param inputLen
     *            the length of the input (in bytes).
     * @return the size for a buffer (in bytes).
     */
    protected abstract int engineGetOutputSize(int inputLen);

    /**
     * Returns the Initialization Vector (IV) that was used to initialize this
     * cipher or {@code null} if none was used.
     *
     * @return the Initialization Vector (IV), or {@code null} if none was used.
     */
    protected abstract byte[] engineGetIV();

    /**
     * Returns the parameters that where used to create this cipher instance.
     * <p>
     * These may be a the same parameters that were used to create this cipher
     * instance, or may be a combination of default and random parameters,
     * depending on the underlying cipher implementation.
     *
     * @return the parameters that where used to create this cipher instance, or
     *         {@code null} if this cipher instance does not have any parameters
     *         at all.
     */
    protected abstract AlgorithmParameters engineGetParameters();

    /**
     * Initializes this cipher instance with the specified key and a source of
     * randomness.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters or random values
     * that the specified key cannot provide, the underlying implementation of
     * this cipher is supposed to generate the required parameters (using its
     * provider or random values). Random values will be generated using {@code
     * random};
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this cipher
     *             instance.
     */
    protected abstract void engineInit(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException;

    /**
     * Initializes this cipher instance with the specified key, algorithm
     * parameters and a source of randomness.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values). Random values are generated using {@code random}.
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this cipher
     *             instance.
     * @throws InvalidAlgorithmParameterException
     *             it the specified parameters are inappropriate for this
     *             cipher.
     */
    protected abstract void engineInit(int opmode, Key key,
            AlgorithmParameterSpec params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    /**
     * Initializes this cipher instance with the specified key, algorithm
     * parameters and a source of randomness.
     * <p>
     * The cipher will be initialized for the specified operation (one of:
     * encryption, decryption, key wrapping or key unwrapping) depending on
     * {@code opmode}.
     * <p>
     * If this cipher instance needs any algorithm parameters and {@code params}
     * is {@code null}, the underlying implementation of this cipher is supposed
     * to generate the required parameters (using its provider or random
     * values). Random values are generated using {@code random}.
     * <p>
     * When a cipher instance is initialized by a call to any of the {@code
     * init} methods, the state of the instance is overridden, means it is
     * equivalent to creating a new instance and calling it {@code init} method.
     *
     * @param opmode
     *            the operation this cipher instance should be initialized for
     *            (one of: {@code ENCRYPT_MODE}, {@code DECRYPT_MODE}, {@code
     *            WRAP_MODE} or {@code UNWRAP_MODE}).
     * @param key
     *            the input key for the operation.
     * @param params
     *            the algorithm parameters.
     * @param random
     *            the source of randomness to use.
     * @throws InvalidKeyException
     *             if the specified key cannot be used to initialize this cipher
     *             instance.
     * @throws InvalidAlgorithmParameterException
     *             if the specified parameters are inappropriate for this
     *             cipher.
     */
    protected abstract void engineInit(int opmode, Key key,
            AlgorithmParameters params, SecureRandom random)
            throws InvalidKeyException, InvalidAlgorithmParameterException;

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are returned.
     *
     * @param input
     *            the input bytes to transform.
     * @param inputOffset
     *            the offset in the input to start.
     * @param inputLen
     *            the length of the input to transform.
     * @return the transformed bytes in a new buffer, or {@code null} if the
     *         input has zero length.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if the input is null, or if {@code inputOffset} and {@code
     *             inputLen} do not specify a valid chunk in the input buffer.
     */
    protected abstract byte[] engineUpdate(byte[] input, int inputOffset,
            int inputLen);

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * transformed bytes are stored in the {@code output} buffer.
     * <p>
     * If the size of the {@code output} buffer is too small to hold the result,
     * a {@code ShortBufferException} is thrown. Use
     * {@link Cipher#getOutputSize getOutputSize} to check for the size of the
     * output buffer.
     *
     * @param input
     *            the input bytes to transform.
     * @param inputOffset
     *            the offset in the input to start.
     * @param inputLen
     *            the length of the input to transform.
     * @param output
     *            the output buffer.
     * @param outputOffset
     *            the offset in the output buffer.
     * @return the number of bytes placed in output.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     */
    protected abstract int engineUpdate(byte[] input, int inputOffset,
            int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException;

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * {@code input.remaining()} bytes starting at {@code input.position()} are
     * transformed and stored in the {@code output} buffer.
     * <p>
     * If the {@code output.remaining()} is too small to hold the transformed
     * bytes a {@code ShortBufferException} is thrown. Use
     * {@link Cipher#getOutputSize getOutputSize} to check for the size of the
     * output buffer.
     *
     * @param input
     *            the input buffer to transform.
     * @param output
     *            the output buffer to store the result within.
     * @return the number of bytes stored in the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     */
    protected int engineUpdate(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        if (input == null) {
            throw new NullPointerException("input == null");
        }
        if (output == null) {
            throw new NullPointerException("output == null");
        }
        int position = input.position();
        int limit = input.limit();
        if ((limit - position) <= 0) {
            return 0;
        }
        byte[] bInput;
        byte[] bOutput;
        if (input.hasArray()) {
            bInput = input.array();
            int offset = input.arrayOffset();
            bOutput = engineUpdate(bInput, offset + position, limit - position);
            input.position(limit);
        } else {
            bInput = new byte[limit - position];
            input.get(bInput);
            bOutput = engineUpdate(bInput, 0, limit - position);
        }
        if (bOutput == null) {
            return 0;
        }
        if (output.remaining() < bOutput.length) {
            throw new ShortBufferException("output buffer too small");
        }
        try {
            output.put(bOutput);
        } catch (java.nio.BufferOverflowException e) {
            throw new ShortBufferException("output buffer too small");
        }
        return bOutput.length;
    }

    /**
     * Continues a multi-part transformation (encryption or decryption) with
     * Authenticated Additional Data (AAD). AAD may only be added after the
     * {@code Cipher} is initialized and before any data is passed to the
     * instance.
     * <p>
     * This is only usable with cipher modes that support Authenticated
     * Encryption with Additional Data (AEAD) such as Galois/Counter Mode (GCM).
     *
     * @param input bytes of AAD to use with the cipher
     * @param inputOffset offset within bytes of additional data to add to cipher
     * @param inputLen length of bytes of additional data to add to cipher
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     * @throws IllegalArgumentException
     *             if {@code input} is {@code null}, or if {@code inputOffset} and
     *             {@code inputLen} do not specify a valid chunk in the input
     *             buffer.
     * @throws UnsupportedOperationException if the cipher does not support AEAD
     * @since 1.7
     */
    protected void engineUpdateAAD(byte[] input, int inputOffset, int inputLen) {
        throw new UnsupportedOperationException(
                "This cipher does not support Authenticated Encryption with Additional Data");
    }

    /**
     * Continues a multi-part transformation (encryption or decryption). The
     * {@code input.remaining()} bytes starting at {@code input.position()} are
     * used for the Additional Authenticated Data (AAD). AAD may only be added
     * after the {@code Cipher} is initialized and before any data is passed to
     * the instance.
     * <p>
     * This is only usable with cipher modes that support Authenticated
     * Encryption with Additional Data (AEAD) such as Galois/Counter Mode (GCM).
     *
     * @param input the input buffer to transform.
     * @since 1.7
     */
    protected void engineUpdateAAD(ByteBuffer input) {
        if (input == null) {
            throw new NullPointerException("input == null");
        }
        int position = input.position();
        int limit = input.limit();
        if ((limit - position) <= 0) {
            return;
        }
        byte[] bInput;
        if (input.hasArray()) {
            bInput = input.array();
            int offset = input.arrayOffset();
            engineUpdateAAD(bInput, offset + position, limit - position);
            input.position(limit);
        } else {
            int len = limit - position;
            bInput = new byte[len];
            input.get(bInput);
            engineUpdateAAD(bInput, 0, len);
        }
    }

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code inputLen} bytes in {@code input} buffer at {@code
     * inputOffset}, and any bytes that have been buffered in previous {@code
     * update} calls.
     *
     * @param input
     *            the input buffer.
     * @param inputOffset
     *            the offset in the input buffer.
     * @param inputLen
     *            the length of the input.
     * @return the final bytes from the transformation.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     */
    protected abstract byte[] engineDoFinal(byte[] input, int inputOffset,
            int inputLen) throws IllegalBlockSizeException, BadPaddingException;

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code inputLen} bytes in {@code input} buffer at
     * {@code inputOffset}, and any bytes that have been buffered in previous
     * {@code update} calls.
     *
     * @param input
     *            the input buffer.
     * @param inputOffset
     *            the offset in the input buffer.
     * @param inputLen
     *            the length of the input.
     * @param output
     *            the output buffer for the transformed bytes.
     * @param outputOffset
     *            the offset in the output buffer.
     * @return the number of bytes placed in the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     */
    protected abstract int engineDoFinal(byte[] input, int inputOffset,
            int inputLen, byte[] output, int outputOffset)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException;

    /**
     * Finishes a multi-part transformation (encryption or decryption).
     * <p>
     * Processes the {@code input.remaining()} bytes in {@code input} buffer at
     * {@code input.position()}, and any bytes that have been buffered in
     * previous {@code update} calls. The transformed bytes are placed into
     * {@code output} buffer.
     *
     * @param input
     *            the input buffer.
     * @param output
     *            the output buffer.
     * @return the number of bytes placed into the output buffer.
     * @throws ShortBufferException
     *             if the size of the {@code output} buffer is too small.
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws BadPaddingException
     *             if the padding of the data does not match the padding scheme.
     * @throws IllegalArgumentException
     *             if the input buffer and the output buffer are the same
     *             object.
     * @throws IllegalStateException
     *             if this cipher instance is not initialized for encryption or
     *             decryption.
     */
    protected int engineDoFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        if (input == null) {
            throw new NullPointerException("input == null");
        }
        if (output == null) {
            throw new NullPointerException("output == null");
        }
        int position = input.position();
        int limit = input.limit();

        if ((limit - position) <= 0) {
            return 0;
        }
        byte[] bInput;
        byte[] bOutput;

        if (input.hasArray()) {
            bInput = input.array();
            int offset = input.arrayOffset();
            bOutput = engineDoFinal(bInput, offset + position, limit - position);
            input.position(limit);
        } else {
            bInput = new byte[limit - position];
            input.get(bInput);
            bOutput = engineDoFinal(bInput, 0, limit - position);
        }
        if (output.remaining() < bOutput.length) {
            throw new ShortBufferException("output buffer too small");
        }
        try {
            output.put(bOutput);
        } catch (java.nio.BufferOverflowException e) {
            throw new ShortBufferException("output buffer too small");
        }
        return bOutput.length;
    }

    /**
     * Wraps a key using this cipher instance. This method has been added to
     * this class (for backwards compatibility, it cannot be abstract). If this
     * method is not overridden, it throws an {@code
     * UnsupportedOperationException}.
     *
     * @param key
     *            the key to wrap.
     * @return the wrapped key
     * @throws IllegalBlockSizeException
     *             if the size of the resulting bytes is not a multiple of the
     *             cipher block size.
     * @throws InvalidKeyException
     *             if this cipher instance cannot wrap this key.
     */
    protected byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    /**
     * Unwraps a key using this cipher instance.
     * <p>
     * This method has been added to this class (for backwards compatibility, it
     * cannot be abstract). If this method is not overridden, it throws an
     * {@code UnsupportedOperationException}.
     *
     * @param wrappedKey
     *            the wrapped key to unwrap.
     * @param wrappedKeyAlgorithm
     *            the algorithm for the wrapped key.
     * @param wrappedKeyType
     *            the type of the wrapped key (one of: {@code SECRET_KEY},
     *            {@code PRIVATE_KEY} or {@code PUBLIC_KEY})
     * @return the unwrapped key.
     * @throws InvalidKeyException
     *             if the {@code wrappedKey} cannot be unwrapped to a key of
     *             type {@code wrappedKeyType} for the {@code
     *             wrappedKeyAlgorithm}.
     * @throws NoSuchAlgorithmException
     *             if no provider can be found that can create a key of type
     *             {@code wrappedKeyType} for the {@code wrappedKeyAlgorithm}.
     */
    protected Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException, NoSuchAlgorithmException {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the size of a specified key object in bits. This method has been
     * added to this class (for backwards compatibility, it cannot be abstract).
     * If this method is not overridden, it throws an {@code
     * UnsupportedOperationException}.
     *
     * @param key
     *            the key to get the size for.
     * @return the size of a specified key object in bits.
     * @throws InvalidKeyException
     *             if the size of the key cannot be determined by this
     *             implementation.
     */
    protected int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }
}
