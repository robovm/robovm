/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import junit.framework.TestCase;

public abstract class KeyFactoryTest<PublicKeySpec extends KeySpec, PrivateKeySpec extends KeySpec>
        extends TestCase {

    private final String algorithmName;
    private final Class<PublicKeySpec> publicKeySpecClass;
    private final Class<PrivateKeySpec> privateKeySpecClass;
    private KeyFactory factory;

    public KeyFactoryTest(String algorithmName,
            Class<PublicKeySpec> publicKeySpecClass,
            Class<PrivateKeySpec> privateKeySpecClass) {
        this.algorithmName = algorithmName;
        this.publicKeySpecClass = publicKeySpecClass;
        this.privateKeySpecClass = privateKeySpecClass;
    }

    protected void setUp() throws Exception {
        super.setUp();
        factory = getFactory();
    }

    private KeyFactory getFactory() throws Exception {
        return KeyFactory.getInstance(algorithmName);
    }

    public void testKeyFactory() throws Exception {
        PrivateKeySpec privateKeySpec = factory.getKeySpec(DefaultKeys.getPrivateKey(algorithmName),
                                                           privateKeySpecClass);
        PrivateKey privateKey =  factory.generatePrivate(privateKeySpec);
        PublicKeySpec publicKeySpec = factory.getKeySpec(DefaultKeys.getPublicKey(algorithmName),
                                                         publicKeySpecClass);
        PublicKey publicKey = factory.generatePublic(publicKeySpec);
        check(new KeyPair(publicKey, privateKey));
    }

    protected void check(KeyPair keyPair) {}
}
