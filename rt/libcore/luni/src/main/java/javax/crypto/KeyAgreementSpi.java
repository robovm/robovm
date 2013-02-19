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
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * The <i>Service Provider Interface</i> (<b>SPI</b>) definition for the
 * {@code KeyAgreement} class.
 */
public abstract class KeyAgreementSpi {

    /**
     * Creates a new {@code KeyAgreementSpi} instance.
     */
    public KeyAgreementSpi() {
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
     * @return the intermediate key from this phase or null if there is no
     *         intermediate key for this phase.
     * @throws InvalidKeyException
     *             if the specified key cannot be used in this key agreement or
     *             this phase,
     * @throws IllegalStateException
     *             if this instance has not been initialized.
     */
    protected abstract Key engineDoPhase(Key key, boolean lastPhase)
            throws InvalidKeyException, IllegalStateException;

    /**
     * Generates the shared secret.
     *
     * @return the generated shared secret.
     * @throws IllegalStateException
     *             if this key agreement is not complete.
     */
    protected abstract byte[] engineGenerateSecret()
            throws IllegalStateException;

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
    protected abstract int engineGenerateSecret(byte[] sharedSecret, int offset)
            throws IllegalStateException, ShortBufferException;

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
    protected abstract SecretKey engineGenerateSecret(String algorithm)
            throws IllegalStateException, NoSuchAlgorithmException,
            InvalidKeyException;

    /**
     * Initializes this {@code KeyAgreementSpi} with the specified key and the
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
    protected abstract void engineInit(Key key, SecureRandom random)
            throws InvalidKeyException;

    /**
     * Initializes this {@code KeyAgreementSpi} with the specified key,
     * algorithm parameters and randomness source.
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
    protected abstract void engineInit(Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException;
}