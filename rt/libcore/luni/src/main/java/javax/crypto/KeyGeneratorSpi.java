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
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * The <i>Service Provider Interface</i> (<b>SPI</b>) definition for the
 * {@code KeyGenerator} class.
 *
 * @see KeyGenerator
 */
public abstract class KeyGeneratorSpi {

    /**
     * Creates a new {@code KeyGeneratorSpi} instance.
     */
    public KeyGeneratorSpi() {
    }

    /**
     * Generates a secret key.
     *
     * @return the generated secret key.
     */
    protected abstract SecretKey engineGenerateKey();

    /**
     * Initializes this {@code KeyGeneratorSpi} instance with the specified
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
    protected abstract void engineInit(AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidAlgorithmParameterException;

    /**
     * Initializes this {@code KeyGenerator} instance for the specified key
     * size (in bits) using the specified randomness source.
     *
     * @param keysize
     *            the size of the key (in bits).
     * @param random
     *            the randomness source for any random bytes.
     */
    protected abstract void engineInit(int keysize, SecureRandom random);

    /**
     * Initializes this {@code KeyGenerator} with the specified randomness
     * source.
     *
     * @param random
     *            the randomness source for any random bytes.
     */
    protected abstract void engineInit(SecureRandom random);
}