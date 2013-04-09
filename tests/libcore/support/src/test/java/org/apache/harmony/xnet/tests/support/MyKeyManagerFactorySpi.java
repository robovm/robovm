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

package org.apache.harmony.xnet.tests.support;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;

/**
 * Class for verification KeyManagerFactorySpi and KeyManagerFactory
 * functionality
 *
 */

public class MyKeyManagerFactorySpi extends KeyManagerFactorySpi {

    protected void engineInit(KeyStore ks, char[] password)
            throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverableKeyException {
        if (password == null) {
            throw new KeyStoreException("Incorrect password");
        }
        if (ks == null) {
            throw new UnrecoverableKeyException("Incorrect keystore");
        }
    }

    protected void engineInit(ManagerFactoryParameters spec)
            throws InvalidAlgorithmParameterException {
        if (spec == null) {
            throw new InvalidAlgorithmParameterException("Incorrect parameter");
        }
        if (spec instanceof Parameters) {
            try {
                engineInit(((Parameters)spec).getKeyStore(),
                        ((Parameters)spec).getPassword());
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException(e.toString());
            }
        } else {
            throw new InvalidAlgorithmParameterException("Invalid parameter");
        }
    }

    protected KeyManager[] engineGetKeyManagers() {
        return null;
    }
    public static class Parameters implements ManagerFactoryParameters {
        private KeyStore keyStore;
        private char[] passWD;
        public Parameters (KeyStore ks, char[] pass) {
            this.keyStore = ks;
            this.passWD = pass;
        }
        public KeyStore getKeyStore() {
            return keyStore;
        }
        public char[] getPassword() {
            return passWD;
        }
    }}
