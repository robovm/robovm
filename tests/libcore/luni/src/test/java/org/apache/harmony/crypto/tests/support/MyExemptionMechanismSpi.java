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
* @version $Revision$
*/

package org.apache.harmony.crypto.tests.support;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.ExemptionMechanismException;
import javax.crypto.ExemptionMechanismSpi;
import javax.crypto.ShortBufferException;

/**
 * Additional class for verification ExemptionMechanismSpi
 * and ExemptionMechanism classes
 *
 */

public class MyExemptionMechanismSpi  extends ExemptionMechanismSpi {

    private static final int byteArrayLength = 5;

    public static final int getLength() {
        return byteArrayLength;
    }
    @Override
    protected byte[] engineGenExemptionBlob()
            throws ExemptionMechanismException {
        return new byte[byteArrayLength];
    }

    @Override
    protected int engineGenExemptionBlob(byte[] output, int outputOffset)
            throws ShortBufferException, ExemptionMechanismException {
        return byteArrayLength;
    }

    @Override
    protected int engineGetOutputSize(int inputLen) {
        return 10;
    }

    @Override
    protected void engineInit(Key key) throws InvalidKeyException,
            ExemptionMechanismException {
        if (key == null) {
            throw new InvalidKeyException("key is null");
        }
        if (!(key instanceof tmpKey)) {
            throw new ExemptionMechanismException("Incorrect key");
        }
    }

    @Override
    protected void engineInit(Key key, AlgorithmParameters params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        if (key == null) {
            throw new InvalidKeyException("key is null");
        }
        if (!(key instanceof tmpKey)) {
            throw new ExemptionMechanismException("Incorrect key");
        }
        if (params == null) {
            throw new InvalidAlgorithmParameterException("params is null");
        }
    }

    @Override
    protected void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException,
            ExemptionMechanismException {
        if (key == null) {
            throw new InvalidKeyException("key is null");
        }
        if (!(key instanceof tmpKey)) {
            throw new ExemptionMechanismException("Incorrect key");
        }
        if (params == null) {
            throw new InvalidAlgorithmParameterException("params is null");
        }
    }

    @SuppressWarnings("serial")
    public class tmpKey implements Key {
        private String alg;
        private byte[] enc;
        public tmpKey(String alg, byte[] enc) {
            this.alg = alg;
            this.enc = enc;
        }
        public String getFormat() {
            return "tmpKey";
        }
        public String getAlgorithm() {
            return alg;
        }
        public byte[] getEncoded() {
            return enc;
        }
    }
    @SuppressWarnings("serial")
    public class tmp1Key implements Key {
        private byte[] enc;
        public tmp1Key(String alg, byte[] enc) {
            this.enc = enc;
        }
        public String getAlgorithm() {
            return "tmp1Key";
        }
        public String getFormat() {
            return "tmp1Key";
        }
        public byte[] getEncoded() {
            return enc;
        }
    }
}
