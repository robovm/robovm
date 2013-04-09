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

/**
 * @author Boris V. Kuznetsov
 * @version $Revision$
 */

package org.apache.harmony.security.tests.java.security;

import java.security.InvalidParameterException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;


public class SecurityTest extends TestCase {

    /**
     * java.security.Security#insertProviderAt(Provider, int)
     */
    public final void test_insertProviderAtLjava_security_ProviderLI() {

        try {
            Security.insertProviderAt(null, 1);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        Provider p = new MyProvider();
        int initNum = Security.getProviders().length; // initial number of
        // providers
        Provider initialSecondProviderName = Security.getProviders()[1];

        try {

            // Insert at position -1, the provider is inserted at the end
            assertEquals(initNum + 1, Security.insertProviderAt(p, -1));
            assertSame(p, Security.getProviders()[initNum]);

            // A provider cannot be added if it is already installed
            assertEquals(-1, Security.insertProviderAt(p, 1));

            Security.removeProvider(p.getName());

            // insert at the end
            assertEquals(initNum + 1, Security.insertProviderAt(p,
                    initNum + 100));
            assertSame(p, Security.getProviders()[initNum]);

            Security.removeProvider(p.getName());

            // insert at the first position
            assertEquals(1, Security.insertProviderAt(p, 1));
            assertSame(p, Security.getProviders()[0]);
            assertSame(initialSecondProviderName, // provider shifted down
                    Security.getProviders()[2]);
        } finally { // clean up
            Security.removeProvider(p.getName());
        }
    }

    /**
     * java.security.Security#addProvider(Provider)
     */
    public final void test_addProviderLjava_security_Provider() {

        try {
            Security.addProvider(null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        Provider p = new MyProvider();
        int initNum = Security.getProviders().length; // initial number of
        // providers

        try {
            // add
            assertEquals(initNum + 1, Security.addProvider(p));
            assertSame(p, Security.getProviders()[initNum]);

            // A provider cannot be added if it is already installed
            assertEquals(-1, Security.addProvider(p));
        } finally { // clean up
            Security.removeProvider(p.getName());
        }
    }

    /**
     * java.security.Security#getAlgorithmProperty(String algName, String
     *        propName)
     * @disabled because Security.getAlgorithmProperty looks for
     *           "propName.algName" instead of "Alg.propName.algName"
     */
    @SuppressWarnings("deprecation")
    public final void testGetAlgorithmPropertyLjava_lang_String_java_lang_String() {

        Provider provider = new MyProvider();
        Map<String, String> m = new HashMap<String, String>();
        m.clear();
        m.put("Alg.propName.algName", "value");
        provider.putAll(m);

        try {
            Security.addProvider(provider);

            assertNotNull(Security.getAlgorithmProperty("algName", "propName"));

            assertNull(Security.getAlgorithmProperty("DSA", null));
            assertNull(Security.getAlgorithmProperty("DSA", "propName"));
        } finally {
            Security.removeProvider(provider.getName());
        }
    }

    /**
     * java.security.Security#getAlgorithms(String serviceName)
     */
    public final void testGetAlgorithmsLjava_lang_String() {
        String[] servicesNames = { "Signature", "MessageDigest", "Cipher",
                "Mac", "KeyStore" };

        String[] invalidServiceNames = { "Rubbish", "", null };

        for (int i = 0; i < servicesNames.length; i++) {
            Set<String> algs = Security.getAlgorithms(servicesNames[i]);
            assertTrue("no services with specified name: " + servicesNames[i], algs.size() > 0);
        }

        for (int i = 0; i < invalidServiceNames.length; i++) {
            Set<String> algs = Security.getAlgorithms(invalidServiceNames[i]);
            assertTrue("services with specified name: " + invalidServiceNames[i], algs.size() == 0);
        }
    }

    public final void testRemoveProvider() {
        Provider[] providers;
        Provider[] providers1;

        providers = Security.getProviders();

        try {
            for (int i = 0; i < providers.length; i++) {
                Security.removeProvider(providers[i].getName());
            }
            assertEquals("Providers not removed", 0,
                    Security.getProviders().length);
        } finally {    // restore providers
            for (int i = 0; i < providers.length; i++) {
                Security.addProvider(providers[i]);
            }
            providers1 = Security.getProviders();
            for (int i = 0; i < providers1.length; i++) {
                assertEquals("Providers not restored correctly", providers[i],
                        providers1[i]);
            }
        }
    }

    /**
     * java.security.Security#getProvider(String)
     */
    public final void test_getProviderLjava_lang_String() {

        // Returns null if no provider with the specified name is installed
        assertNull(Security.getProvider("SOMEINCORRECTPROVIDERNAME"));

        // Returns null if name is null
        assertNull(Security.getProvider(null));

        // test for existing providers
        Provider[] providers = Security.getProviders();
        assertTrue("getProviders returned zero length array",
                providers.length > 0);
        for (Provider p : providers) {
            String providerName = p.getName();
            assertSame(p, Security.getProvider(providerName));
        }

        // test for newly installed provider
        Provider p = new MyProvider();
        try {
            Security.addProvider(p);

            assertSame(p, Security.getProvider(p.getName()));
        } finally { // clean up
            Security.removeProvider(p.getName());
        }
    }

    /**
     * java.security.Security#getProviders(String)
     */
    public void test_getProvidersLjava_lang_String() {
        try {
            Security.getProviders("");
            fail("No expected InvalidParameterException");
        } catch (InvalidParameterException e) {
        }

        try {
            Security.getProviders((String) null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        testGetProviders(Locale.US);
        testGetProviders(new Locale("tr", "TR"));
    }

    /**
     * Test that Security.getProviders does case sensitive operations
     * independent of its locale.
     */
    private void testGetProviders(Locale locale) {
        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(locale);
        Provider p = new MyProvider();
        try {
            Security.addProvider(p);

            String filter = "MyService.MyAlgorithm";
            assertTrue(filter, Arrays.equals(new Provider[] { p }, Security
                    .getProviders(filter)));

            filter = "MyService.MyAlgorithm KeySize:512";
            assertTrue(filter, Arrays.equals(new Provider[] { p }, Security
                    .getProviders(filter)));

            filter = "MyService.MyAlgorithm KeySize:1025";
            assertNull(filter, Security.getProviders(filter));

            // attribute name and value are case insensitive
            filter = "MyService.MyAlgorithm imPLementedIn:softWARE";
            assertTrue(filter, Arrays.equals(new Provider[] { p }, Security
                    .getProviders(filter)));
            filter = "MyService.MyAlgorithm ATTribute:attributeVALUE";
            assertTrue(filter, Arrays.equals(new Provider[] { p }, Security
                    .getProviders(filter)));
            filter = "MyService.MyAlgorithm \u0130mPLemented\u0131n:softWARE"; // Turkish dotless i
            assertTrue(filter, Arrays.equals(new Provider[] { p }, Security
                    .getProviders(filter)));

            // Regression for HARMONY-2761
            filter = "MyService.NoKeySize KeySize:512";
            assertNull(filter, Security.getProviders(filter));

            filter = "MyService.NoImplementedIn ImplementedIn:Software";
            assertNull(filter, Security.getProviders(filter));

            filter = "ABCService.NoAttribute Attribute:ABC";
            assertNull(filter, Security.getProviders(filter));
        } finally { // clean up
            Security.removeProvider(p.getName());
            Locale.setDefault(defaultLocale);
        }
    }

    /**
     * java.security.Security#getProviders(java.util.Map)
     */
    public void test_getProvidersLjava_util_Map() {

        Map<String, String> m = new HashMap<String, String>();
        Security.getProviders(m);

        assertNull("Not null result on empty map", Security.getProviders(m));

        try {
            Security.getProviders((Map<String, String>) null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        m.put("AAA.BBB.CCC", "aaaa"); // key has dot instead of space
        try {
            Security.getProviders(m);
            fail("No expected InvalidParameterException");
        } catch (InvalidParameterException e) {
        }

        Provider p = new MyProvider();
        try {
            Security.addProvider(p);

            m.clear();
            m.put("MyService.MyAlgorithm", "");
            m.put("MessageDigest.SHA-1", "");
            assertTrue("MyService.MyAlgorithm", Arrays.equals(
                    new Provider[] { p }, Security.getProviders(m)));

            m.clear();
            m.put("MyService.MyAlgorithm KeySize", "512");
            m.put("MessageDigest.SHA-1", "");
            assertTrue("MyService.MyAlgorithm KeySize:512", Arrays.equals(
                    new Provider[] { p }, Security.getProviders(m)));

            m.clear();
            m.put("MyService.MyAlgorithm KeySize", "1025");
            m.put("MessageDigest.SHA-1", "");
            assertNull("MyService.MyAlgorithm KeySize:1025", Security
                    .getProviders(m));

            // attribute name and value are case insensitive
            m.clear();
            m.put("MyService.MyAlgorithm imPLementedIn", "softWARE");
            assertTrue(Arrays.equals(new Provider[] { p }, Security
                    .getProviders(m)));
            m.clear();
            m.put("MyService.MyAlgorithm ATTribute", "attributeVALUE");
            assertTrue(Arrays.equals(new Provider[] { p }, Security
                    .getProviders(m)));

            // Regression for HARMONY-2761
            m.clear();
            m.put("MyService.NoKeySize KeySize", "512");
            assertNull("No KeySize attribute", Security.getProviders(m));

            m.clear();
            m.put("MyService.NoImplementedIn ImplementedIn", "Software");
            assertNull("No ImplementedIn attribute", Security.getProviders(m));

            m.clear();
            m.put("ABCService.NoAttribute Attribute", "ABC");
            assertNull(Security.getProviders(m));
        } finally { // clean up
            Security.removeProvider(p.getName());
        }
    }

    /**
     * java.security.Security#getProviders()
     */
    public void test_getProviders() {
        Provider[] prv;

        MyProvider provider = new MyProvider();
        try {
            prv = Security.getProviders();
            int len1 = prv.length;
            if (len1 == 0) {
                fail("Array of providers is ampty");
            }
            Security.addProvider(provider);
            prv = Security.getProviders();
            int len2 = prv.length;
            if ((len2 == len1 + 1) && (prv[len2-1].toString().equals("MyProvider version 1.0"))) {
                // ok
            } else {
                fail("Method getProviders() returned incorrect values");
            }
        } catch (Exception ex) {
            fail("Unexpected exception");
        }
        finally {
            Security.removeProvider(provider.getName());
        }
    }

    /**
     * java.security.Security#getProperty(String)
     */
    public void test_getPropertyLjava_lang_String() {

        try {
            Security.getProperty(null);
            fail("No expected NullPointerException.");
        } catch (NullPointerException e) {
        }

        Security.setProperty("myprop","test white space    ");
        assertEquals("test white space", Security.getProperty("myprop"));
    }

    /**
     * java.security.Security#setProperty(String,String)
     */
    public void test_setPropertyLjava_lang_StringLjava_lang_String() {

        try {
            Security.setProperty(null, "");
            fail("No expected NullPointerException.");
        } catch (NullPointerException e) {
        }

        try {
            Security.setProperty("", null);
            fail("No expected NullPointerException.");
        } catch (NullPointerException e) {
        }

        Security.setProperty("", "");
        assertEquals("Empty property", "", Security.getProperty(""));

        Security.setProperty("My Test Property", "My property value");
        assertEquals("My property value", Security
                .getProperty("My Test Property"));
    }

    @SuppressWarnings("serial")
    class MyProvider extends Provider {
        MyProvider() {
            super("MyProvider", 1.0, "Provider for testing");
            put("MessageDigest.SHA-1", "SomeClassName");
            put("MyService.MyAlgorithm", "SomeClassName");
            put("MyService.MyAlgorithm KeySize", "1024");
            put("MyService.MyAlgorithm ImplementedIn", "Software");
            put("MyService.MyAlgorithm Attribute", "AttributeValue");

            // service has no KeySize attribute
            put("MyService.NoKeySize", "SomeClassName");

            // service has no ImplementedIn attribute
            put("MyService.NoImplementedIn", "SomeClassName");

            // service has no 'Attribute' attribute
            put("ABCService.NoAttribute", "SomeClassName");
        }
    }
}
