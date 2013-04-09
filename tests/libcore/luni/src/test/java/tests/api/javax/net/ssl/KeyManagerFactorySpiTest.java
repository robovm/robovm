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

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;

import junit.framework.TestCase;

import org.apache.harmony.xnet.tests.support.KeyManagerFactorySpiImpl;

public class KeyManagerFactorySpiTest extends TestCase {

    /**
     * javax.net.ssl.KeyManagerFactorySpi#KeyManagerFactorySpi()
     */
    public void test_Constructor() {
        try {
            KeyManagerFactorySpiImpl kmf = new KeyManagerFactorySpiImpl();
            assertTrue(kmf instanceof KeyManagerFactorySpi);
        } catch (Exception e) {
            fail("Unexpected Exception " + e.toString());
        }
    }

    /**
     * javax.net.ssl.KeyManagerFactorySpi#KengineInit(KeyStore ks, char[] password)
     */
    public void test_engineInit_01() {
        KeyManagerFactorySpiImpl kmf = new KeyManagerFactorySpiImpl();
        KeyStore ks;
        char[] psw = "password".toCharArray();

        try {
            kmf.engineInit(null, null);
            fail("NoSuchAlgorithmException wasn't thrown");
        } catch (NoSuchAlgorithmException kse) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of NoSuchAlgorithmException");
        }

        try {
            kmf.engineInit(null, psw);
            fail("KeyStoreException wasn't thrown");
        } catch (KeyStoreException uke) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of KeyStoreException");
        }

        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            kmf.engineInit(ks, null);
            fail("UnrecoverableKeyException wasn't thrown");
        } catch (UnrecoverableKeyException uke) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of UnrecoverableKeyException");
        }

        try {
            KeyStore kst = KeyStore.getInstance(KeyStore.getDefaultType());
            kst.load(null, null);
            kmf.engineInit(kst, psw);
        } catch (Exception e) {
            fail("Unexpected exception " + e);
        }
    }

    /**
     * javax.net.ssl.KeyManagerFactorySpi#KengineInit(ManagerFactoryParameters spec)
     */
    public void test_engineInit_02() {
        KeyManagerFactorySpiImpl kmf = new KeyManagerFactorySpiImpl();

        try {
            kmf.engineInit(null);
            fail("InvalidAlgorithmParameterException wasn't thrown");
        } catch (InvalidAlgorithmParameterException iape) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of InvalidAlgorithmParameterException");
        }

        try {
            char[] psw = "password".toCharArray();
            Parameters pr = new Parameters(psw);
            kmf.engineInit(pr);
        } catch (Exception e) {
            fail(e + " unexpected exception was thrown");
        }
    }

    /**
     * javax.net.ssl.KeyManagerFactorySpi#engineGetKeyManagers()
     */
    public void test_engineGetKeyManagers() {
        KeyManagerFactorySpiImpl kmf = new KeyManagerFactorySpiImpl();

        try {
            KeyManager[] km = kmf.engineGetKeyManagers();
            fail("IllegalStateException wasn't thrown");
        } catch (IllegalStateException ise) {
            //expected
        } catch (Exception e) {
            fail(e + " was thrown instead of IllegalStateException");
        }

        try {
            char[] psw = "password".toCharArray();
            Parameters pr = new Parameters(psw);
            kmf.engineInit(pr);
            KeyManager[] km = kmf.engineGetKeyManagers();
            assertNull("Object is not NULL", km);
        } catch (Exception e) {
            fail(e + " unexpected exception was thrown");
        }
    }

    public class Parameters implements ManagerFactoryParameters {
        private char[] passWD;

        public Parameters (char[] pass) {
            this.passWD = pass;
        }
        public char[] getPassword() {
            return passWD;
        }
    }

}
