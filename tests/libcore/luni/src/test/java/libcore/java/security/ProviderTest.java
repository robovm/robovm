/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.security;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.TestCase;

public class ProviderTest extends TestCase {

    /**
     * Makes sure all all expected implementations (but not aliases)
     * and that there are no extras, according to what we expect from
     * StandardNames
     */
    public void test_Provider_getServices() throws Exception {

        // build set of expected algorithms
        Map<String,Set<String>> remaining
                = new HashMap<String,Set<String>>(StandardNames.PROVIDER_ALGORITHMS);
        for (Entry<String,Set<String>> entry : remaining.entrySet()) {
            entry.setValue(new HashSet<String>(entry.getValue()));
        }

        List<String> extra = new ArrayList();
        List<String> missing = new ArrayList();

        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            String providerName = provider.getName();
            // ignore BouncyCastle provider if it is installed on the RI
            if (StandardNames.IS_RI && providerName.equals("BC")) {
                continue;
            }
            Set<Provider.Service> services = provider.getServices();
            assertNotNull(services);
            assertFalse(services.isEmpty());

            for (Provider.Service service : services) {
                String type = service.getType();
                String algorithm = service.getAlgorithm().toUpperCase();
                String className = service.getClassName();
                if (false) {
                    System.out.println(providerName
                                       + " " + type
                                       + " " + algorithm
                                       + " " + className);
                }

                // remove from remaining, assert unknown if missing
                Set<String> algorithms = remaining.get(type);
                if (algorithms == null || !algorithms.remove(algorithm)) {
                    // seems to be missing, but sometimes the same
                    // algorithm is available from multiple providers
                    // (e.g. KeyFactory RSA is available from
                    // SunRsaSign and SunJSSE), so double check in
                    // original source before giving error
                    if (!(StandardNames.PROVIDER_ALGORITHMS.containsKey(type)
                            && StandardNames.PROVIDER_ALGORITHMS.get(type).contains(algorithm))) {
                        extra.add("Unknown " + type + " " + algorithm + " " + providerName + "\n");
                    }
                }
                if (algorithms != null && algorithms.isEmpty()) {
                    remaining.remove(type);
                }

                // make sure class exists and can be initialized
                try {
                    assertNotNull(Class.forName(className,
                                                true,
                                                provider.getClass().getClassLoader()));
                } catch (ClassNotFoundException e) {
                    // Sun forgot their own class
                    if (!className.equals("sun.security.pkcs11.P11MAC")) {
                        missing.add(className);
                    }
                }
            }
        }

        // assert that we don't have any extra in the implementation
        Collections.sort(extra); // sort so that its grouped by type
        assertEquals("Extra algorithms", Collections.EMPTY_LIST, extra);

        // assert that we don't have any missing in the implementation
        assertEquals("Missing algorithms", Collections.EMPTY_MAP, remaining);

        // assert that we don't have any missing classes
        Collections.sort(missing); // sort it for readability
        assertEquals("Missing classes", Collections.EMPTY_LIST, missing);
    }

    private static final Pattern alias = Pattern.compile("Alg\\.Alias\\.([^.]*)\\.(.*)");

    /**
     * Makes sure all provider properties either point to a class
     * implementation that exists or are aliases to known algorithms.
     */
    public void test_Provider_Properties() throws Exception {
        /*
         * A useful reference on Provider properties
         * <a href="http://java.sun.com/javase/6/docs/technotes/guides/security/crypto/HowToImplAProvider.html>
         * How to Implement a Provider in the Java &trade; Cryptography Architecture
         * </a>
         */

        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            // check Provider.id proprieties
            assertEquals(provider.getName(),
                         provider.get("Provider.id name"));
            assertEquals(String.valueOf(provider.getVersion()),
                         provider.get("Provider.id version"));
            assertEquals(provider.getInfo(),
                         provider.get("Provider.id info"));
            assertEquals(provider.getClass().getName(),
                         provider.get("Provider.id className"));

            // build map of all known aliases and implementations
            Map<String,String> aliases = new HashMap<String,String>();
            Map<String,String> implementations = new HashMap<String,String>();
            for (Entry<Object,Object> entry : provider.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                assertEquals(String.class, k.getClass());
                assertEquals(String.class, v.getClass());
                String key = (String)k;
                String value = (String)v;

                // skip Provider.id keys, we check well known ones values above
                if (key.startsWith("Provider.id ")) {
                    continue;
                }

                // skip property settings such as: "Signature.SHA1withDSA ImplementedIn" "Software"
                if (key.indexOf(' ') != -1) {
                    continue;
                }

                Matcher m = alias.matcher(key);
                if (m.find()) {
                    String type = m.group(1);
                    aliases.put(key, type + "." + value);
                } else {
                    implementations.put(key, value);
                }
            }

            // verify implementation classes are available
            for (Entry<String,String> entry : implementations.entrySet()) {
                String typeAndAlgorithm = entry.getKey();
                String className = entry.getValue();
                try {
                    assertNotNull(Class.forName(className,
                                                true,
                                                provider.getClass().getClassLoader()));
                } catch (ClassNotFoundException e) {
                    // Sun forgot their own class
                    if (!className.equals("sun.security.pkcs11.P11MAC")) {
                        fail("Could not find class " + className + " for " + typeAndAlgorithm);
                    }
                }
            }

            // make sure all aliases point to some known implementation
            for (Entry<String,String> entry : aliases.entrySet()) {
                String alias  = entry.getKey();
                String actual = entry.getValue();
                assertTrue("Could not find implementation " + actual + " for alias " + alias,
                           implementations.containsKey(actual));
            }
        }
    }
}
