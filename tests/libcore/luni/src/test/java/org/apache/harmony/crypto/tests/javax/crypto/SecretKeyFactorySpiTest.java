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

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.SecretKey;
import org.apache.harmony.crypto.tests.support.MySecretKeyFactorySpi;

import junit.framework.TestCase;

/**
 * Tests for <code>SecretKeyFactorySpi</code> class constructors and methods.
 */
public class SecretKeyFactorySpiTest extends TestCase {
    class Mock_SecretKeyFactorySpi extends MySecretKeyFactorySpi {

        @Override
        protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
            return super.engineGenerateSecret(keySpec);
        }

        @Override
        protected KeySpec engineGetKeySpec(SecretKey key, Class keySpec)
                throws InvalidKeySpecException {
            return super.engineGetKeySpec(key, keySpec);
        }

        @Override
        protected SecretKey engineTranslateKey(SecretKey key) throws InvalidKeyException {
            return super.engineTranslateKey(key);
        }

    }

    /**
     * Test for <code>SecretKeyFactorySpi</code> constructor Assertion:
     * constructs SecretKeyFactorySpi
     */
    public void testSecretKeyFactorySpi01() throws InvalidKeyException, InvalidKeySpecException {
        Mock_SecretKeyFactorySpi skfSpi = new Mock_SecretKeyFactorySpi();
        SecretKey sk = null;
        assertNull("Not null result", skfSpi.engineTranslateKey(sk));

        KeySpec kspec = null;
        assertNull("Not null result", skfSpi.engineGenerateSecret(kspec));

        assertNull("Not null result", skfSpi.engineGetKeySpec(sk, null));
    }
}
