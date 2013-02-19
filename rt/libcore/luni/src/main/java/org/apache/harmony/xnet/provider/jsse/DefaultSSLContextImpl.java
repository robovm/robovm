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

package org.apache.harmony.xnet.provider.jsse;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Support class for this package.
 */
public final class DefaultSSLContextImpl extends OpenSSLContextImpl {

    /**
     * Accessed by SSLContextImpl(DefaultSSLContextImpl) holding the
     * DefaultSSLContextImpl.class monitor
     */
    private static KeyManager[] KEY_MANAGERS;

    /**
     * Accessed by SSLContextImpl(DefaultSSLContextImpl) holding the
     * DefaultSSLContextImpl.class monitor
     */
    private static TrustManager[] TRUST_MANAGERS;

    /**
     * DefaultSSLContextImpl delegates the work to the super class
     * since there is no way to put a synchronized around both the
     * call to super and the rest of this constructor to guarantee
     * that we don't have races in creating the state shared between
     * all default SSLContexts.
     */
    public DefaultSSLContextImpl() throws GeneralSecurityException, IOException {
        super(null);
    }

    // TODO javax.net.ssl.keyStoreProvider system property
    KeyManager[] getKeyManagers () throws GeneralSecurityException, IOException {
        if (KEY_MANAGERS != null) {
            return KEY_MANAGERS;
        }
        // find KeyStore, KeyManagers
        String keystore = System.getProperty("javax.net.ssl.keyStore");
        if (keystore == null) {
            return null;
        }
        String keystorepwd = System.getProperty("javax.net.ssl.keyStorePassword");
        char[] pwd = (keystorepwd == null) ? null : keystorepwd.toCharArray();

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(keystore));
            ks.load(is, pwd);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        String kmfAlg = Security.getProperty("ssl.KeyManagerFactory.algorithm");
        if (kmfAlg == null) {
            kmfAlg = "SunX509";
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(kmfAlg);
        kmf.init(ks, pwd);
        KEY_MANAGERS = kmf.getKeyManagers();
        return KEY_MANAGERS;
    }

    // TODO javax.net.ssl.trustStoreProvider system property
    TrustManager[] getTrustManagers() throws GeneralSecurityException, IOException {
        if (TRUST_MANAGERS != null) {
            return TRUST_MANAGERS;
        }

        // find TrustStore, TrustManagers
        String keystore = System.getProperty("javax.net.ssl.trustStore");
        if (keystore == null) {
            return null;
        }
        String keystorepwd = System.getProperty("javax.net.ssl.trustStorePassword");
        char[] pwd = (keystorepwd == null) ? null : keystorepwd.toCharArray();

        // TODO Defaults: jssecacerts; cacerts
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(keystore));
            ks.load(is, pwd);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        String tmfAlg = Security.getProperty("ssl.TrustManagerFactory.algorithm");
        if (tmfAlg == null) {
            tmfAlg = "PKIX";
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlg);
        tmf.init(ks);
        TRUST_MANAGERS = tmf.getTrustManagers();
        return TRUST_MANAGERS;
    }

    @Override
    public void engineInit(KeyManager[] kms, TrustManager[] tms,
            SecureRandom sr) throws KeyManagementException {
        throw new KeyManagementException("Do not init() the default SSLContext ");
    }
}
