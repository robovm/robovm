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

package org.apache.harmony.security.provider.crypto;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DSAKeyFactoryImpl extends KeyFactorySpi {

    /**
     * This method generates a DSAPrivateKey object from the provided key specification.
     *
     * @param
     *    keySpec - the specification (key material) for the DSAPrivateKey.
     *
     * @return
     *    a DSAPrivateKey object
     *
     * @throws InvalidKeySpecException
     *     if "keySpec" is neither DSAPrivateKeySpec nor PKCS8EncodedKeySpec
     */
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
            throws InvalidKeySpecException {

        if (keySpec != null) {
            if (keySpec instanceof DSAPrivateKeySpec) {

                return new DSAPrivateKeyImpl((DSAPrivateKeySpec) keySpec);
            }
            if (keySpec instanceof PKCS8EncodedKeySpec) {

                return new DSAPrivateKeyImpl((PKCS8EncodedKeySpec) keySpec);
            }
        }
        throw new InvalidKeySpecException("'keySpec' is neither DSAPrivateKeySpec nor PKCS8EncodedKeySpec");
    }

    /**
     * This method generates a DSAPublicKey object from the provided key specification.
     *
     * @param
     *    keySpec - the specification (key material) for the DSAPublicKey.
     *
     * @return
     *    a DSAPublicKey object
     *
     * @throws InvalidKeySpecException
     *     if "keySpec" is neither DSAPublicKeySpec nor X509EncodedKeySpec
     */
    protected PublicKey engineGeneratePublic(KeySpec keySpec)
            throws InvalidKeySpecException {

        if (keySpec != null) {
            if (keySpec instanceof DSAPublicKeySpec) {

                return new DSAPublicKeyImpl((DSAPublicKeySpec) keySpec);
            }
            if (keySpec instanceof X509EncodedKeySpec) {

                return new DSAPublicKeyImpl((X509EncodedKeySpec) keySpec);
            }
        }
        throw new InvalidKeySpecException("'keySpec' is neither DSAPublicKeySpec nor X509EncodedKeySpec");
    }

    /**
     * This method returns a specification for the supplied key.
     *
     * The specification will be returned in the form of an object of the type
     * specified by keySpec.
     *
     * @param key -
     *            either DSAPrivateKey or DSAPublicKey
     * @param keySpec -
     *            either DSAPrivateKeySpec.class or DSAPublicKeySpec.class
     *
     * @return either a DSAPrivateKeySpec or a DSAPublicKeySpec
     *
     * @throws InvalidKeySpecException
     *             if "keySpec" is not a specification for DSAPublicKey or
     *             DSAPrivateKey
     */
    protected <T extends KeySpec> T engineGetKeySpec(Key key, Class<T> keySpec)
            throws InvalidKeySpecException {

        BigInteger p, q, g, x, y;

        if (key != null) {
            if (keySpec == null) {
                throw new NullPointerException("keySpec == null");
            }
            if (key instanceof DSAPrivateKey) {
                DSAPrivateKey privateKey = (DSAPrivateKey) key;

                if (keySpec.equals(DSAPrivateKeySpec.class)) {

                    x = privateKey.getX();

                    DSAParams params = privateKey.getParams();

                    p = params.getP();
                    q = params.getQ();
                    g = params.getG();

                    return (T) (new DSAPrivateKeySpec(x, p, q, g));
                }

                if (keySpec.equals(PKCS8EncodedKeySpec.class)) {
                    return (T) (new PKCS8EncodedKeySpec(key.getEncoded()));
                }

                throw new InvalidKeySpecException("'keySpec' is neither DSAPrivateKeySpec nor PKCS8EncodedKeySpec");
            }

            if (key instanceof DSAPublicKey) {
                DSAPublicKey publicKey = (DSAPublicKey) key;

                if (keySpec.equals(DSAPublicKeySpec.class)) {

                    y = publicKey.getY();

                    DSAParams params = publicKey.getParams();

                    p = params.getP();
                    q = params.getQ();
                    g = params.getG();

                    return (T) (new DSAPublicKeySpec(y, p, q, g));
                }

                if (keySpec.equals(X509EncodedKeySpec.class)) {
                    return (T) (new X509EncodedKeySpec(key.getEncoded()));
                }

                throw new InvalidKeySpecException("'keySpec' is neither DSAPublicKeySpec nor X509EncodedKeySpec");
            }
        }
        throw new InvalidKeySpecException("'key' is neither DSAPublicKey nor DSAPrivateKey");
    }

    /**
     * The method generates a DSAPublicKey object from the provided key.
     *
     * @param
     *    key - a DSAPublicKey object or DSAPrivateKey object.
     *
     * @return
     *    object of the same type as the "key" argument
     *
     * @throws InvalidKeyException
     *     if "key" is neither DSAPublicKey nor DSAPrivateKey
     */
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {

        if (key != null) {
            if (key instanceof DSAPrivateKey) {

                DSAPrivateKey privateKey = (DSAPrivateKey) key;
                DSAParams params = privateKey.getParams();

                try {
                    return engineGeneratePrivate(new DSAPrivateKeySpec(
                            privateKey.getX(), params.getP(), params.getQ(),
                            params.getG()));
                } catch (InvalidKeySpecException e) {
                    // Actually this exception shouldn't be thrown
                    throw new InvalidKeyException("ATTENTION: InvalidKeySpecException: " + e);
                }
            }

            if (key instanceof DSAPublicKey) {

                DSAPublicKey publicKey = (DSAPublicKey) key;
                DSAParams params = publicKey.getParams();

                try {
                    return engineGeneratePublic(new DSAPublicKeySpec(publicKey
                            .getY(), params.getP(), params.getQ(), params
                            .getG()));
                } catch (InvalidKeySpecException e) {
                    // Actually this exception shouldn't be thrown
                    throw new InvalidKeyException("ATTENTION: InvalidKeySpecException: " + e);
                }
            }
        }
        throw new InvalidKeyException("'key' is neither DSAPublicKey nor DSAPrivateKey");
    }

}
