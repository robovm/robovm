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

package org.apache.harmony.security.tests.support;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactorySpi;

/**
 * Class for vertifying TrustManagerFactorySpi and TrustManagerFactory
 * functionality
 *
 */

public class MyTrustManagerFactorySpi extends TrustManagerFactorySpi {
    protected void engineInit(KeyStore ks) throws KeyStoreException {
        if (ks == null) {
            throw new KeyStoreException("Not supported operation for null KeyStore");
        }
    }

    protected void engineInit(ManagerFactoryParameters spec)
            throws InvalidAlgorithmParameterException {
        if (spec == null) {
            throw new InvalidAlgorithmParameterException("Null parameter");
        }
        if (spec instanceof Parameters) {
            try {
                engineInit(((Parameters)spec).getKeyStore());
            } catch (KeyStoreException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new InvalidAlgorithmParameterException("Invalid parameter");
        }
    }

    protected TrustManager[] engineGetTrustManagers() {
        return null;
    }


    public static class Parameters implements ManagerFactoryParameters {
        private KeyStore keyStore;
        public Parameters (KeyStore ks) {
            this.keyStore = ks;
        }
        public KeyStore getKeyStore() {
            return keyStore;
        }
    }
}
