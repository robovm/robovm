/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.DSAParameterSpec;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.DSAParams;

public class KeyPairGenerator4Test extends junit.framework.TestCase {

    /**
     * java.security.KeyPairGenerator#genKeyPair()
     */
    public void test_genKeyPair() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
        gen.initialize(1024);
        assertNotNull("KeyPair is null", gen.genKeyPair());
    }

    /**
     * java.security.KeyPairGenerator#getAlgorithm()
     */
    public void test_getAlgorithm() throws Exception {
        String alg = KeyPairGenerator.getInstance("DSA").getAlgorithm();
        assertEquals("getAlgorithm returned unexpected value", "DSA", alg);
    }

    /**
     * java.security.KeyPairGenerator#getInstance(java.lang.String)
     */
    public void test_getInstanceLjava_lang_String() throws Exception {
        KeyPairGenerator.getInstance("DSA");
    }

    /**
     * java.security.KeyPairGenerator#getInstance(java.lang.String,
     *        java.lang.String)
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String()
            throws Exception {

        // Test1: Test with named provider
        Provider[] providers = Security.getProviders("KeyPairGenerator.DSA");

        for (int i = 0; i < providers.length; i++) {
            KeyPairGenerator.getInstance("DSA", providers[i].getName());
        }// end for

        // Test2: Test with empty provider name - should raise
        // IllegalArgumentException
        try {
            KeyPairGenerator.getInstance("DSA", "");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * java.security.KeyPairGenerator#getProvider()
     */
    public void test_getProvider() throws Exception {
        Provider p = KeyPairGenerator.getInstance("DSA").getProvider();
        assertNotNull("provider is null", p);
    }

    /**
     * java.security.KeyPairGenerator#initialize(int)
     */
    public void test_initializeI() throws Exception {
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("DSA");
        keyPair.initialize(1024);
    }

    /**
     * java.security.KeyPairGenerator#initialize(int,
     *        java.security.SecureRandom)
     */
    public void test_initializeILjava_security_SecureRandom() throws Exception {
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("DSA");
        keyPair.initialize(1024, new SecureRandom());
    }


    /**
     * java.security.KeyPairGenerator#initialize(java.security.spec.AlgorithmParameterSpec)
     */
    public void test_initializeLjava_security_spec_AlgorithmParameterSpec()
            throws Exception {
        // create DSAParams
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        DSAPublicKey key = (DSAPublicKey) keyPairGenerator.genKeyPair()
                .getPublic();
        DSAParams params = key.getParams();

        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("DSA");
        keyPair.initialize(new DSAParameterSpec(params.getP(), params.getQ(),
                params.getG()));
    }

    /**
     * java.security.KeyPairGenerator#initialize(java.security.spec.AlgorithmParameterSpec,
     *        java.security.SecureRandom)
     */
    public void test_initializeLjava_security_spec_AlgorithmParameterSpecLjava_security_SecureRandom()
            throws Exception {
        // create DSAParams
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        DSAPublicKey key = (DSAPublicKey) keyPairGenerator.genKeyPair()
                .getPublic();
        DSAParams params = key.getParams();

        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("DSA");
        keyPair.initialize(new DSAParameterSpec(params.getP(), params.getQ(),
                params.getG()), new SecureRandom());
    }
}
