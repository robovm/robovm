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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.crypto.internal;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

/**
 * CipherSpi implementation for javax.crypto.NullCipher
 *
 */
public class NullCipherSpi extends CipherSpi {

    @Override
    public void engineSetMode(String arg0) throws NoSuchAlgorithmException {
        // Do nothing
    }

    @Override
    public void engineSetPadding(String arg0) throws NoSuchPaddingException {
        // Do nothing
    }

    @Override
    public int engineGetBlockSize() {
        return 1;
    }

    @Override
    public int engineGetOutputSize(int inputLen) {
        return inputLen;
    }

    @Override
    public byte[] engineGetIV() {
        return new byte[8]; // compatible with RI
    }

    @Override
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override
    public void engineInit(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException {
        // Do nothing
    }

    @Override
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    @Override
    public void engineInit(int opmode, Key key, AlgorithmParameters params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    @Override
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        if (input == null) {
            return null;
        }
        byte[] result = new byte[inputLen];
        System.arraycopy(input, inputOffset, result, 0, inputLen);
        return result;
    }

    @Override
    public int engineUpdate(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException {
        if (input == null) {
            return 0;
        }
        System.arraycopy(input, inputOffset, output, outputOffset, inputLen);
        return inputLen;
    }

    @Override
    public int engineUpdate(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        if (input == null || output == null) {
            throw new NullPointerException();
        }
        int result = input.limit() - input.position();
        try {
            output.put(input);
        } catch (java.nio.BufferOverflowException e) {
            throw new ShortBufferException("output buffer too small");
        }
        return result;
    }

    @Override
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        if (input == null) {
            return null;
        }
        return engineUpdate(input, inputOffset, inputLen);
    }

    @Override
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        int result = engineUpdate(input, inputOffset, inputLen, output,
                outputOffset);
        return result;
    }

    @Override
    public int engineDoFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        return engineUpdate(input, output);
    }

    @Override
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }
}
