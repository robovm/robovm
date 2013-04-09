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

import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.KeyStoreBuilderParameters;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class KeyStoreBuilderParametersTest extends TestCase {

    /**
     * javax.net.ssl.KeyStoreBuilderParameters#KeyStoreBuilderParameters(KeyStore.Builder builder)
     */
    public void test_Constructor01() {
        // Null parameter
        try {
            new KeyStoreBuilderParameters((KeyStore.Builder) null);
            fail();
        } catch (NullPointerException expected) {
        }

        // Not null parameter
        KeyStore.ProtectionParameter pp = new ProtectionParameterImpl();
        KeyStore.Builder bld = KeyStore.Builder.newInstance("testType", null, pp);
        assertNotNull("Null object KeyStore.Builder", bld);
        KeyStoreBuilderParameters ksp = new KeyStoreBuilderParameters(bld);
        assertNotNull(ksp.getParameters());
    }

    /**
     * javax.net.ssl.KeyStoreBuilderParameters#KeyStoreBuilderParameters(List parameters)
     */
    public void test_Constructor02() {

        // Null parameter
        try {
            KeyStoreBuilderParameters ksp = new KeyStoreBuilderParameters((List) null);
            fail();
        } catch (NullPointerException expected) {
        }

        // Empty parameter
        List lsEmpty = new ArrayList<String>();
        try {
            KeyStoreBuilderParameters ksp = new KeyStoreBuilderParameters(lsEmpty);
            fail();
        } catch (IllegalArgumentException expected) {
        }

        // Not null parameter
        List lsFiled = new ArrayList<String>();
        lsFiled.add("Parameter1");
        lsFiled.add("Parameter2");
        new KeyStoreBuilderParameters(lsFiled);
    }

    /**
     * javax.net.ssl.KeyStoreBuilderParameters#getParameters()
     */
    public void test_getParameters() {
        String[] param = {"Parameter1", "Parameter2", "Parameter3"};
        List ls = new ArrayList<String>();
        for (int i = 0; i < param.length; i++) {
            ls.add(param[i]);
        }
        KeyStoreBuilderParameters ksp = new KeyStoreBuilderParameters(ls);
        List res_list = ksp.getParameters();
        try {
            res_list.add("test");
            fail();
        } catch (UnsupportedOperationException expected) {
        }
        Object[] res = res_list.toArray();
        assertEquals(res.length, param.length);
        for (int i = 0; i < res.length; i++) {
            assertEquals(param[i], res[i]);
        }
    }

    private static class ProtectionParameterImpl implements KeyStore.ProtectionParameter {
        private ProtectionParameterImpl() {}
    }
}

