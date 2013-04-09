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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import junit.framework.Assert;

public class AlgorithmParameterSignatureHelper<T extends AlgorithmParameterSpec>
        extends TestHelper<AlgorithmParameters> {

    private final String algorithmName;
    private final String plainData = "some data do sign and verify";
    private final Class<T> parameterSpecClass;

    public AlgorithmParameterSignatureHelper(String algorithmName, Class<T> parameterSpecCla1ss) {
        this.algorithmName = algorithmName;
        this.parameterSpecClass = parameterSpecCla1ss;
    }

    @Override
    public void test(AlgorithmParameters parameters) {

        Signature signature = null;
        try {
            signature = Signature.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        }


        T parameterSpec = null;
        try {
            parameterSpec = parameters.getParameterSpec(parameterSpecClass);
        } catch (InvalidParameterSpecException e) {
            Assert.fail(e.getMessage());
        }

        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            Assert.fail(e.getMessage());
        }

        try {
            generator.initialize(parameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            Assert.fail(e.getMessage());
        }

        KeyPair keyPair = generator.genKeyPair();

        try {
            signature.initSign(keyPair.getPrivate());
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.update(plainData.getBytes());
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        byte[] signed = null;
        try {
            signed = signature.sign();
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.initVerify(keyPair.getPublic());
        } catch (InvalidKeyException e) {
            Assert.fail(e.getMessage());
        }

        try {
            signature.update(plainData.getBytes());
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }

        try {
            Assert.assertTrue("signature could not be verified", signature
                    .verify(signed));
        } catch (SignatureException e) {
            Assert.fail(e.getMessage());
        }
    }
}
