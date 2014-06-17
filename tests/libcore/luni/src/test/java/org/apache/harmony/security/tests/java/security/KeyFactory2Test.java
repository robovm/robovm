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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyFactorySpi;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;
import libcore.java.security.StandardNames;

public class KeyFactory2Test extends junit.framework.TestCase {

    private static final String KEYFACTORY_ID = "KeyFactory.";

    private String[] keyfactAlgs = null;

    private String providerName = null;

    static class KeepAlive extends Thread {
        int sleepTime, iterations;

        public KeepAlive(int sleepTime, int iterations) {
            this.sleepTime = sleepTime;
            this.iterations = iterations;
        }

        public void run() {
            synchronized (this) {
                this.notify();
            }
            for (int i = 0; i < iterations; i++) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private KeepAlive createKeepAlive(String alg) {
        if (alg.equals("RSA")) {
            // 32 minutes
            KeepAlive keepalive = new KeepAlive(240000, 8);
            synchronized (keepalive) {
                keepalive.start();
                try {
                    keepalive.wait();
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            return keepalive;
        }
        return null;
    }

    public void test_constructor() throws Exception {
        KeyFactorySpi kfs = new KeyFactorySpiStub();

        new KeyFactoryStub(null, null, null);

        Provider[] providers = Security.getProviders("KeyFactory.DSA");
        if (providers != null) {
            for (int i = 0; i < providers.length; i++) {
                KeyFactoryStub kf = new KeyFactoryStub(kfs, providers[i],
                        "algorithm name");
                assertEquals("algorithm name", kf.getAlgorithm());
                assertEquals(providers[i], kf.getProvider());
            }
        } else {
            fail("No providers support KeyFactory.DSA");
        }
    }

    public void test_generatePrivateLjava_security_spec_KeySpec() throws Exception {
        // Test for method java.security.PrivateKey
        // java.security.KeyFactory.generatePrivate(java.security.spec.KeySpec)
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i], providerName);
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(keyfactAlgs[i]);
            SecureRandom random = new SecureRandom(); // We don't use
            // getInstance
            keyGen.initialize(StandardNames.getMinimumKeySize(keyfactAlgs[i]), random);
            KeepAlive keepalive = createKeepAlive(keyfactAlgs[i]);
            KeyPair keys = keyGen.generateKeyPair();
            if (keepalive != null) {
                keepalive.interrupt();
            }

            KeySpec privateKeySpec = fact.getKeySpec(keys.getPrivate(),
                    StandardNames.getPrivateKeySpecClass(keyfactAlgs[i]));
            PrivateKey privateKey = fact.generatePrivate(privateKeySpec);
            assertEquals("generatePrivate generated different key for algorithm " + keyfactAlgs[i],
                    Arrays.toString(keys.getPrivate().getEncoded()),
                    Arrays.toString(privateKey.getEncoded()));
            privateKey = fact.generatePrivate(new PKCS8EncodedKeySpec(keys.getPrivate().getEncoded()));
            assertEquals("generatePrivate generated different key for algorithm " + keyfactAlgs[i],
                    Arrays.toString(keys.getPrivate().getEncoded()),
                    Arrays.toString(privateKey.getEncoded()));
        }
    }

    public void test_generatePublicLjava_security_spec_KeySpec() throws Exception {
        // Test for method java.security.PublicKey
        // java.security.KeyFactory.generatePublic(java.security.spec.KeySpec)
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i],
                    providerName);
            KeyPairGenerator keyGen = KeyPairGenerator
                    .getInstance(keyfactAlgs[i]);
            // We don't use getInstance
            SecureRandom random = new SecureRandom();
            keyGen.initialize(StandardNames.getMinimumKeySize(keyfactAlgs[i]), random);
            KeepAlive keepalive = createKeepAlive(keyfactAlgs[i]);
            KeyPair keys = keyGen.generateKeyPair();
            if (keepalive != null) {
                keepalive.interrupt();
            }
            KeySpec publicKeySpec = fact.getKeySpec(keys.getPublic(),
                    StandardNames.getPublicKeySpecClass(keyfactAlgs[i]));
            PublicKey publicKey = fact.generatePublic(publicKeySpec);
            assertEquals(
                    "generatePublic generated different key for algorithm "
                            + keyfactAlgs[i] + " (provider="
                            + fact.getProvider().getName() + ")",
                            Arrays.toString(keys.getPublic().getEncoded()),
                            Arrays.toString(publicKey.getEncoded()));
        }
    }

    public void test_getAlgorithm() throws Exception {
        // Test for method java.lang.String
        // java.security.KeyFactory.getAlgorithm()
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i],
                    providerName);
            assertTrue("getAlgorithm ok for algorithm " + keyfactAlgs[i],
                    fact.getAlgorithm().equals(keyfactAlgs[i]));
        }
    }

    public void test_getInstanceLjava_lang_String() throws Exception {
        // Test for method java.security.KeyFactory
        // java.security.KeyFactory.getInstance(java.lang.String)
        for (int i = 0; i < keyfactAlgs.length; i++) {
            assertNotNull(KeyFactory.getInstance(keyfactAlgs[i]));
        }
    }

    public void test_getInstanceLjava_lang_StringLjava_lang_String() throws Exception {
        // Test1: Test for method java.security.KeyFactory
        // java.security.KeyFactory.getInstance(java.lang.String,
        // java.lang.String)
        Provider[] providers = Security.getProviders("KeyFactory.DSA");
        assertNotNull(providers);

        for (int i = 0; i < providers.length; i++) {
            KeyFactory.getInstance("DSA", providers[i].getName());
        }


        // Test2: Test with null provider name
        try {
            KeyFactory.getInstance("DSA", (String) null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, got " + e);
        }
    }

    public void test_getInstanceLjava_lang_StringLjava_security_Provider() throws Exception {
        // Test1: Test for method java.security.KeyFactory
        // java.security.KeyFactory.getInstance(java.lang.String,
        // java.security.Provider)
        Provider[] providers = Security.getProviders("KeyFactory.DSA");
        assertNotNull(providers);

        for (int i = 0; i < providers.length; i++) {
            KeyFactory.getInstance("DSA", providers[i]);
        }

        // Test2: Test with null provider name
        try {
            KeyFactory.getInstance("DSA", (Provider) null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, got " + e);
        }
    }

    public void test_getKeySpecLjava_security_KeyLjava_lang_Class() throws Exception {
        // Test for method java.security.spec.KeySpec
        // java.security.KeyFactory.getKeySpec(java.security.Key,
        // java.lang.Class)
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i],
                    providerName);
            KeyPairGenerator keyGen = KeyPairGenerator
                    .getInstance(keyfactAlgs[i]);

            // We don't use getInstance
            SecureRandom random = new SecureRandom();
            keyGen.initialize(StandardNames.getMinimumKeySize(keyfactAlgs[i]), random);
            KeepAlive keepalive = createKeepAlive(keyfactAlgs[i]);
            KeyPair keys = keyGen.generateKeyPair();
            if (keepalive != null) {
                keepalive.interrupt();
            }
            KeySpec privateKeySpec = fact.getKeySpec(keys.getPrivate(),
                    StandardNames.getPrivateKeySpecClass(keyfactAlgs[i]));
            KeySpec publicKeySpec = fact.getKeySpec(keys.getPublic(),
                    StandardNames.getPublicKeySpecClass(keyfactAlgs[i]));
            PrivateKey privateKey = fact.generatePrivate(privateKeySpec);
            PublicKey publicKey = fact.generatePublic(publicKeySpec);
            assertEquals(
                    "generatePrivate generated different key for algorithm "
                            + keyfactAlgs[i] + " (provider="
                            + fact.getProvider().getName() + ")",
                            Arrays.toString(keys.getPrivate().getEncoded()),
                            Arrays.toString(privateKey.getEncoded()));
            assertEquals(
                    "generatePublic generated different key for algorithm "
                            + keyfactAlgs[i] + " (provider="
                            + fact.getProvider().getName() + ")",
                            Arrays.toString(keys.getPublic().getEncoded()),
                            Arrays.toString(publicKey.getEncoded()));
            KeySpec encodedSpec = fact.getKeySpec(keys.getPublic(),
                    X509EncodedKeySpec.class);
            assertTrue("improper key spec for encoded public key",
                    encodedSpec.getClass().equals(X509EncodedKeySpec.class));
            encodedSpec = fact.getKeySpec(keys.getPrivate(),
                    PKCS8EncodedKeySpec.class);
            assertTrue("improper key spec for encoded private key",
                    encodedSpec.getClass()
                            .equals(PKCS8EncodedKeySpec.class));
        }
    }

    public void test_getProvider() throws Exception {
        // Test for method java.security.Provider
        // java.security.KeyFactory.getProvider()
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i]);
            Provider p = fact.getProvider();
            assertNotNull("provider is null for algorithm " + keyfactAlgs[i], p);
        }
    }

    public void test_translateKeyLjava_security_Key() throws Exception {
        // Test for method java.security.Key
        // java.security.KeyFactory.translateKey(java.security.Key)
        for (int i = 0; i < keyfactAlgs.length; i++) {
            KeyFactory fact = KeyFactory.getInstance(keyfactAlgs[i],
                    providerName);
            KeyPairGenerator keyGen = KeyPairGenerator
                    .getInstance(keyfactAlgs[i]);

            // We don't use getInstance
            SecureRandom random = new SecureRandom();
            keyGen.initialize(StandardNames.getMinimumKeySize(keyfactAlgs[i]), random);
            KeepAlive keepalive = createKeepAlive(keyfactAlgs[i]);
            KeyPair keys = keyGen.generateKeyPair();
            if (keepalive != null) {
                keepalive.interrupt();
            }
            fact.translateKey(keys.getPrivate());
        }
    }

    protected void setUp() {
        if (keyfactAlgs == null) {
            Provider[] providers = Security.getProviders();
            // Arbitrarily use the first provider that supports
            // KeyFactory algorithms
            for (Provider provider : providers) {
                providerName = provider.getName();
                keyfactAlgs = getKeyFactoryAlgorithms(providerName);
                if (keyfactAlgs.length != 0) {
                    break;
                }
            }
        }
    }

    /*
     * Returns the key algorithms that the given provider supports.
     */
    private String[] getKeyFactoryAlgorithms(String providerName) {
        Vector<String> algs = new Vector<String>();

        Provider provider = Security.getProvider(providerName);
        if (provider == null)
            return new String[0];
        Enumeration<Object> e = provider.keys();
        while (e.hasMoreElements()) {
            String algorithm = (String) e.nextElement();
            if (algorithm.startsWith(KEYFACTORY_ID) && !algorithm.contains(" ")) {
                algs.addElement(algorithm.substring(KEYFACTORY_ID.length()));
            }
        }

        return algs.toArray(new String[algs.size()]);
    }

    public class KeyFactoryStub extends KeyFactory {
        public KeyFactoryStub(KeyFactorySpi keyFacSpi, Provider provider,
                String algorithm) {
            super(keyFacSpi, provider, algorithm);
        }
    }

    public class KeyFactorySpiStub extends KeyFactorySpi {
        public KeyFactorySpiStub() {
            super();
        }

        public PrivateKey engineGeneratePrivate(KeySpec keySpec) {
            return null;
        }

        public PublicKey engineGeneratePublic(KeySpec keySpec) {
            return null;
        }

        public <T extends KeySpec> T engineGetKeySpec(Key key, Class<T> keySpec) {
            return null;
        }

        public Key engineTranslateKey(Key key) {
            return null;
        }
    }
}
