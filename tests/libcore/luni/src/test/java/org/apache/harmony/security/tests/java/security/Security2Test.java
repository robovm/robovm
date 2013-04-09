/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import java.security.InvalidParameterException;
import java.security.Provider;
import java.security.Security;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import tests.support.Support_ProviderTrust;
import tests.support.Support_TestProvider;

public class Security2Test extends junit.framework.TestCase {

    /**
     * java.security.Security#getProviders(java.lang.String)
     */
    public void test_getProvidersLjava_lang_String() {
        // Test for method void
        // java.security.Security.getProviders(java.lang.String)

        Hashtable<String, Integer> allSupported = new Hashtable<String, Integer>();
        Provider[] allProviders = Security.getProviders();

        // Add all non-alias entries to allSupported
        for (int i = 0; i < allProviders.length; i++) {
            Provider provider = allProviders[i];
            Iterator it = provider.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                // No aliases and no provider data
                if (!isAlias(key) && !isProviderData(key)) {
                    addOrIncrementTable(allSupported, key);
                }
            }// end while more entries
        }// end for all providers

        // Now walk through aliases. If an alias has actually been added
        // to the allSupported table then increment the count of the
        // entry that is being aliased.
        for (int i = 0; i < allProviders.length; i++) {
            Provider provider = allProviders[i];
            Iterator it = provider.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                if (isAlias(key)) {
                    String aliasVal = key.substring("ALG.ALIAS.".length());
                    String aliasKey = aliasVal.substring(0, aliasVal
                            .indexOf(".") + 1)
                            + entry.getValue();
                    // Skip over nonsense alias declarations where alias and
                    // aliased are identical. Such entries can occur.
                    if (!aliasVal.equals(aliasKey)) {
                        // Has a real entry been added for aliasValue ?
                        if (allSupported.containsKey(aliasVal)) {
                            // Add 1 to the provider count of the thing being
                            // aliased
                            addOrIncrementTable(allSupported, aliasKey);
                        }
                    }
                }
            }// end while more entries
        }// end for all providers

        Provider provTest[] = null;
        Iterator it = allSupported.keySet().iterator();
        while (it.hasNext()) {
            String filterString = (String) it.next();
            try {
                provTest = Security.getProviders(filterString);
                int expected = ((Integer) allSupported.get(filterString))
                        .intValue();
                assertEquals(
                        "Unexpected number of providers returned for filter "
                                + filterString, expected, provTest.length);
            } catch (InvalidParameterException e) {
                // NO OP
            }
        }// end while

        // exception
        try {
            provTest = Security.getProviders("Signature.SHA1withDSA :512");
            fail("InvalidParameterException should be thrown <Signature.SHA1withDSA :512>");
        } catch (InvalidParameterException e) {
            // Expected
        }
    }

    /**
     * @param key
     * @return
     */
    private boolean isProviderData(String key) {
        return key.toUpperCase().startsWith("PROVIDER.");
    }

    /**
     * @param key
     * @return
     */
    private boolean isAlias(String key) {
        return key.toUpperCase().startsWith("ALG.ALIAS.");
    }

    /**
     * @param table
     * @param key
     */
    private void addOrIncrementTable(Hashtable<String, Integer> table, String key) {
        if (table.containsKey(key)) {
            Integer before = (Integer) table.get(key);
            table.put(key, new Integer(before.intValue() + 1));
        } else {
            table.put(key, new Integer(1));
        }
    }

    /**
     * @param filterMap
     * @return
     */
    private int getProvidersCount(Map filterMap) {
        int result = 0;
        Provider[] allProviders = Security.getProviders();

        // for each provider
        for (int i = 0; i < allProviders.length; i++) {
            Provider provider = allProviders[i];
            Set allProviderKeys = provider.keySet();
            boolean noMatchFoundForFilterEntry = false;

            // for each filter item
            Set allFilterKeys = filterMap.keySet();
            Iterator fkIter = allFilterKeys.iterator();
            while (fkIter.hasNext()) {
                String filterString = ((String) fkIter.next()).trim();

                // Remove any "=" characters that may be on the end of the
                // map keys (no, I don't know why they might be there either
                // but I have seen them)
                if (filterString.endsWith("=")) {
                    filterString = filterString.substring(0, filterString
                            .length() - 1);
                }

                if (filterString != null) {
                    if (filterString.indexOf(" ") == -1) {
                        // Is this filter string in the keys of the
                        // current provider ?
                        if (!allProviderKeys.contains(filterString)) {
                            // Check that the key is not contained as an
                            // alias.
                            if (!allProviderKeys.contains("Alg.Alias."
                                    + filterString)) {
                                noMatchFoundForFilterEntry = true;
                                break; // out of while loop
                            }
                        }
                    } else {
                        // handle filter strings with attribute names
                        if (allProviderKeys.contains(filterString)) {
                            // Does the corresponding values match ?
                            String filterVal = (String) filterMap
                                    .get(filterString);
                            String providerVal = (String) provider
                                    .get(filterString);
                            if (providerVal == null
                                    || !providerVal.equals(filterVal)) {
                                noMatchFoundForFilterEntry = true;
                                break; // out of while loop
                            }
                        }// end if filter string with named attribute is
                        // found
                    }// end else
                }// end if non-null key
            }// end while there are more filter strings for current map

            if (!noMatchFoundForFilterEntry) {
                // Current provider is a match for the filterMap
                result++;
            }
        }// end for each provider

        return result;
    }

    /**
     * java.security.Security#getProviders(java.util.Map)
     */
    public void test_getProvidersLjava_util_Map() {
        // Test for method void
        // java.security.Security.getProviders(java.util.Map)

        Map<String, String> filter = new Hashtable<String, String>();
        filter.put("KeyStore.BKS", "");
        filter.put("Signature.SHA1withDSA", "");
        Provider provTest[] = Security.getProviders(filter);
        if (provTest == null) {
            assertEquals("Filter : <KeyStore.BKS>,<Signature.SHA1withDSA>",
                    0, getProvidersCount(filter));
        } else {
            assertEquals("Filter : <KeyStore.BKS>,<Signature.SHA1withDSA>",
                    getProvidersCount(filter), provTest.length);
        }

        filter = new Hashtable<String, String>();
        filter.put("MessageDigest.SHA-384", "");
        filter.put("CertificateFactory.X.509", "");
        filter.put("KeyFactory.RSA", "");
        provTest = Security.getProviders(filter);
        if (provTest == null) {
            assertEquals("Filter : <MessageDigest.SHA-384>,<CertificateFactory.X.509>,<KeyFactory.RSA>",
                    0, getProvidersCount(filter));
        } else {
            assertEquals(
                    "Filter : <MessageDigest.SHA-384>,<CertificateFactory.X.509>,<KeyFactory.RSA>",
                    getProvidersCount(filter), provTest.length);
        }

        filter = new Hashtable<String, String>();
        filter.put("MessageDigest.SHA1", "");
        filter.put("TrustManagerFactory.X509", "");
        provTest = Security.getProviders(filter);
        if (provTest == null) {
            assertEquals("Filter : <MessageDigest.SHA1><TrustManagerFactory.X509>",
                    0, getProvidersCount(filter));
        } else {
            assertEquals(
                    "Filter : <MessageDigest.SHA1><TrustManagerFactory.X509>",
                    getProvidersCount(filter), provTest.length);
        }

        filter = new Hashtable<String, String>();
        filter.put("CertificateFactory.X509", "");
        provTest = Security.getProviders(filter);
        if (provTest == null) {
            assertEquals("Filter : <CertificateFactory.X509>",
                    0, getProvidersCount(filter));
        } else {
            assertEquals("Filter : <CertificateFactory.X509>",
                    getProvidersCount(filter), provTest.length);
        }

        filter = new Hashtable<String, String>();
        filter.put("Provider.id name", "DRLCertFactory");
        provTest = Security.getProviders(filter);
        assertNull("Filter : <Provider.id name, DRLCertFactory >",
                provTest);

        // exception - no attribute name after the service.algorithm yet we
        // still supply an expected value. This is not valid.
        try {
            filter = new Hashtable<String, String>();
            filter.put("Signature.SHA1withDSA", "512");
            provTest = Security.getProviders(filter);
            fail("InvalidParameterException should be thrown <Signature.SHA1withDSA><512>");
        } catch (InvalidParameterException e) {
            // Expected
        }

        // exception - space character in the service.algorithm pair. Not valid.
        try {
            filter = new Hashtable<String, String>();
            filter.put("Signature. KeySize", "512");
            provTest = Security.getProviders(filter);
            fail("InvalidParameterException should be thrown <Signature. KeySize><512>");
        } catch (InvalidParameterException e) {
            // Expected
        }
    }

    /**
     * java.security.Security#removeProvider(java.lang.String)
     */
    public void test_removeProviderLjava_lang_String() {
        // Test for method void
        // java.security.Security.removeProvider(java.lang.String)
        Provider test = new Support_TestProvider();
        Provider entrust = new Support_ProviderTrust();
        try {
            // Make sure provider not already loaded. Should do nothing
            // if not already loaded.
            Security.removeProvider(test.getName());

            // Now add it
            int addResult = Security.addProvider(test);
            assertTrue("Failed to add provider", addResult != -1);

            Security.removeProvider(test.getName());
            assertNull(
                    "the provider TestProvider is found after it was removed",
                    Security.getProvider(test.getName()));

            // Make sure entrust provider not already loaded. Should do nothing
            // if not already loaded.
            Security.removeProvider(entrust.getName());

            // Now add entrust
            addResult = Security.addProvider(entrust);
            assertTrue("Failed to add provider", addResult != -1);

            Security.removeProvider(entrust.getName());
            Provider provTest[] = Security.getProviders();
            for (int i = 0; i < provTest.length; i++) {
                assertTrue(
                        "the provider entrust is found after it was removed",
                        provTest[i].getName() != entrust.getName());
            }
        } finally {
            // Tidy up - the following calls do nothing if the providers were
            // already removed above.
            Security.removeProvider(test.getName());
            Security.removeProvider(entrust.getName());
        }
    }
}
