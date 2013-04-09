/*
 * Copyright (C) 2007 The Android Open Source Project
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
package tests.api.javax.net.ssl;

import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;

import junit.framework.TestCase;
import org.apache.harmony.xnet.tests.support.TrustManagerFactorySpiImpl;
import org.apache.harmony.xnet.tests.support.MyTrustManagerFactorySpi.Parameters;

public class TrustManagerFactorySpiTest extends TestCase {

    private TrustManagerFactorySpiImpl factory = new TrustManagerFactorySpiImpl();
    /**
     * javax.net.ssl.TrustManagerFactorySpi#TrustManagerFactorySpi()
     */
    public void test_Constructor() {
        try {
            TrustManagerFactorySpiImpl tmf = new TrustManagerFactorySpiImpl();
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * javax.net.ssl.TrustManagerFactorySpi#engineInit(KeyStore ks)
     */
    public void test_engineInit_01() throws NoSuchAlgorithmException,
            KeyStoreException {
        factory.reset();
        Provider provider = new MyProvider();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("MyTMF",
                provider);
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            tmf.init(ks);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
        assertTrue(factory.isEngineInitCalled());
        assertEquals(ks, factory.getKs());
        factory.reset();
        tmf.init((KeyStore) null);
        assertTrue(factory.isEngineInitCalled());
        assertNull(factory.getKs());
    }

    /**
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * javax.net.ssl.TrustManagerFactorySpi#engineInit(ManagerFactoryParameters spec)
     */
    public void test_engineInit_02() throws InvalidAlgorithmParameterException,
            NoSuchAlgorithmException {
        factory.reset();
        Provider provider = new MyProvider();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("MyTMF",
                provider);
        Parameters pr = null;
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            pr = new Parameters(ks);
            tmf.init(pr);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
        assertTrue(factory.isEngineInitCalled());
        assertEquals(pr, factory.getSpec());
        factory.reset();
        tmf.init((ManagerFactoryParameters) null);
        assertTrue(factory.isEngineInitCalled());
        assertNull(factory.getSpec());
    }

    /**
     * @throws NoSuchAlgorithmException
     * javax.net.ssl.TrustManagerFactorySpi#engineGetTrustManagers()
     */
    public void test_engineGetTrustManagers() throws NoSuchAlgorithmException {
        factory.reset();
        Provider provider = new MyProvider();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("MyTMF",
                provider);
        TrustManager[] tm = tmf.getTrustManagers();
        assertTrue(factory.isEngineGetTrustManagersCalled());
        factory.reset();
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            tmf.init(ks);
            tm = tmf.getTrustManagers();
            assertTrue(factory.isEngineGetTrustManagersCalled());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }
}

class MyProvider extends Provider {

    public MyProvider() {
        super("MyProvider", 1.0, "My Test Provider");
        AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
            public Void run() {
                put("TrustManagerFactory.MyTMF",
                        "org.apache.harmony.xnet.tests.support.TrustManagerFactorySpiImpl");
                return null;
            }
        });
    }
}
