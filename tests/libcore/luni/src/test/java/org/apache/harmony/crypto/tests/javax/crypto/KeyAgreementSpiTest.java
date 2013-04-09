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

package org.apache.harmony.crypto.tests.javax.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

import junit.framework.TestCase;

import org.apache.harmony.crypto.tests.support.MyKeyAgreementSpi;

/**
 * Tests for <code>KeyAgreementSpi</code> class constructors and methods.
 *
 */
public class KeyAgreementSpiTest extends TestCase {
    class Mock_KeyAgreementSpi extends MyKeyAgreementSpi {

        @Override
        protected Key engineDoPhase(Key key, boolean lastPhase) throws InvalidKeyException,
                IllegalStateException {
            return super.engineDoPhase(key, lastPhase);
        }

        @Override
        protected byte[] engineGenerateSecret() throws IllegalStateException {
            return super.engineGenerateSecret();
        }

        @Override
        protected SecretKey engineGenerateSecret(String algorithm) throws IllegalStateException,
                NoSuchAlgorithmException, InvalidKeyException {
            return super.engineGenerateSecret(algorithm);
        }

        @Override
        protected int engineGenerateSecret(byte[] sharedSecret, int offset)
                throws IllegalStateException, ShortBufferException {
            return super.engineGenerateSecret(sharedSecret, offset);
        }

        @Override
        protected void engineInit(Key key, SecureRandom random) throws InvalidKeyException {
            super.engineInit(key, random);
        }

        @Override
        protected void engineInit(Key key, AlgorithmParameterSpec params, SecureRandom random)
                throws InvalidKeyException, InvalidAlgorithmParameterException {
            super.engineInit(key, params, random);
        }

    }

    /**
     * Test for <code>KeyAgreementSpi</code> constructor Assertion: constructs
     * KeyAgreementSpi
     */
    public void testKeyAgreementSpi01() throws InvalidKeyException,
            ShortBufferException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        Mock_KeyAgreementSpi kaSpi = new Mock_KeyAgreementSpi();

        assertNull("Not null result", kaSpi.engineDoPhase(null, true));
        try {
            kaSpi.engineDoPhase(null, false);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {
        }
        byte[] bb = kaSpi.engineGenerateSecret();
        assertEquals("Length is not 0", bb.length, 0);
        assertEquals("Returned integer is not 0", kaSpi.engineGenerateSecret(new byte[1], 10), -1);
        assertNull("Not null result", kaSpi.engineGenerateSecret("aaa"));
        try {
            kaSpi.engineGenerateSecret("");
            fail("NoSuchAlgorithmException must be thrown");
        } catch (NoSuchAlgorithmException e) {
        }
        Key key = null;
        try {
            kaSpi.engineInit(key, new SecureRandom());
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
        }
        AlgorithmParameterSpec params = null;
        try {
            kaSpi.engineInit(key, params, new SecureRandom());
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
        }
    }
}
