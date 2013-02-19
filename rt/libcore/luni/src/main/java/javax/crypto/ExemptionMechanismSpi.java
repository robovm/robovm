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
import java.security.spec.AlgorithmParameterSpec;

/**
 * The <i>Service Provider Interface</i> (<b>SPI</b>) definition for the {@code
 * ExemptionMechanism} class.
 */
public abstract class ExemptionMechanismSpi {

    /**
     * Creates a new {@code ExemptionMechanismSpi} instance.
     */
    public ExemptionMechanismSpi() {
    }

    /**
     * Generates the result key blob for this exemption mechanism.
     *
     * @return the result key blob for this exemption mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during generation.
     */
    protected abstract byte[] engineGenExemptionBlob()
            throws ExemptionMechanismException;

    /**
     * Generates the result key blob for this exemption mechanism and stores it
     * into the {@code output} buffer at offset {@code outputOffset}.
     *
     * @param output
     *            the output buffer for the result key blob.
     * @param outputOffset
     *            the offset in the output buffer to start.
     * @return the number of bytes written to the {@code output} buffer.
     * @throws ShortBufferException
     *             if the provided buffer is too small for the result key blob.
     * @throws ExemptionMechanismException
     *             if error(s) occur during generation.
     */
    protected abstract int engineGenExemptionBlob(byte[] output,
            int outputOffset) throws ShortBufferException,
            ExemptionMechanismException;

    /**
     * Returns the size in bytes for the output buffer needed to hold the output
     * of the next {@link #engineGenExemptionBlob} call, given the specified
     * {@code inputLen} (in bytes).
     *
     * @param inputLen
     *            the specified input length (in bytes).
     * @return the size in bytes for the output buffer.
     */
    protected abstract int engineGetOutputSize(int inputLen);

    /**
     * Initializes this {@code ExemptionMechanism} instance with the specified
     * key.
     *
     * @param key
     *            the key to initialize this instance with.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    protected abstract void engineInit(Key key) throws InvalidKeyException,
            ExemptionMechanismException;

    /**
     * Initializes this {@code ExemptionMechanism} instance with the specified
     * key and algorithm parameters.
     *
     * @param key
     *            the key to initialize this instance with.
     * @param params
     *            the parameters for this exemption mechanism algorithm.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws InvalidAlgorithmParameterException
     *             if the parameters cannot be used to initialize this
     *             mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    protected abstract void engineInit(Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException;

    /**
     * Initializes this {@code ExemptionMechanism} instance with the specified
     * key and algorithm parameters.
     *
     * @param key
     *            the key to initialize this instance with.
     * @param params
     *            the parameters for this exemption mechanism algorithm.
     * @throws InvalidKeyException
     *             if the key cannot be used to initialize this mechanism.
     * @throws InvalidAlgorithmParameterException
     *             the the parameters cannot be used to initialize this
     *             mechanism.
     * @throws ExemptionMechanismException
     *             if error(s) occur during initialization.
     */
    protected abstract void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException;
}