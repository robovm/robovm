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

package libcore.javax.net.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore.Builder;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyStoreBuilderParameters;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;
import libcore.java.security.TestKeyStore;

public class KeyManagerFactoryTest extends TestCase {

    private static TestKeyStore TEST_KEY_STORE;

    // note the rare usage of DSA keys here in addition to RSA
    private static TestKeyStore getTestKeyStore() throws Exception {
        if (TEST_KEY_STORE == null) {
            TEST_KEY_STORE = new TestKeyStore.Builder()
                    .keyAlgorithms("RSA", "DSA", "EC", "EC_RSA")
                    .aliasPrefix("rsa-dsa-ec")
                    .build();
        }
        return TEST_KEY_STORE;
    }

    public void test_KeyManagerFactory_getDefaultAlgorithm() throws Exception {
        String algorithm = KeyManagerFactory.getDefaultAlgorithm();
        assertEquals(StandardNames.KEY_MANAGER_FACTORY_DEFAULT, algorithm);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
        test_KeyManagerFactory(kmf);
    }

    private static class UselessManagerFactoryParameters implements ManagerFactoryParameters {}

    private static boolean supportsManagerFactoryParameters(String algorithm) {
        // Only the "New" one supports ManagerFactoryParameters
        return algorithm.equals("NewSunX509");
    }

    private static String[] keyTypes(String algorithm) {
        // Although the "New" one supports ManagerFactoryParameters,
        // it can't handle nulls in the key types array.
        return (algorithm.equals("NewSunX509")
                ? KEY_TYPES_WITH_EMPTY
                : KEY_TYPES_WITH_EMPTY_AND_NULL);
    }

    private void test_KeyManagerFactory(KeyManagerFactory kmf) throws Exception {
        assertNotNull(kmf);
        assertNotNull(kmf.getAlgorithm());
        assertNotNull(kmf.getProvider());

        // before init
        try {
            kmf.getKeyManagers();
            fail();
        } catch (IllegalStateException expected) {
        }

        // init with null ManagerFactoryParameters
        try {
            kmf.init(null);
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // init with useless ManagerFactoryParameters
        try {
            kmf.init(new UselessManagerFactoryParameters());
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // init with KeyStoreBuilderParameters ManagerFactoryParameters
        PasswordProtection pp = new PasswordProtection(getTestKeyStore().storePassword);
        Builder builder = Builder.newInstance(getTestKeyStore().keyStore, pp);
        KeyStoreBuilderParameters ksbp = new KeyStoreBuilderParameters(builder);
        if (supportsManagerFactoryParameters(kmf.getAlgorithm())) {
            kmf.init(ksbp);
            test_KeyManagerFactory_getKeyManagers(kmf, false);
        } else {
            try {
                kmf.init(ksbp);
                fail();
            } catch (InvalidAlgorithmParameterException expected) {
            }
        }

        // init with null for default behavior
        kmf.init(null, null);
        test_KeyManagerFactory_getKeyManagers(kmf, true);

        // init with specific key store and password
        kmf.init(getTestKeyStore().keyStore, getTestKeyStore().storePassword);
        test_KeyManagerFactory_getKeyManagers(kmf, false);
    }

    private void test_KeyManagerFactory_getKeyManagers(KeyManagerFactory kmf, boolean empty)
            throws Exception {
        KeyManager[] keyManagers = kmf.getKeyManagers();
        assertNotNull(keyManagers);
        assertTrue(keyManagers.length > 0);
        for (KeyManager keyManager : keyManagers) {
            assertNotNull(keyManager);
            if (keyManager instanceof X509KeyManager) {
                test_X509KeyManager((X509KeyManager) keyManager, empty, kmf.getAlgorithm());
            }
        }
    }

    private static final String[] KEY_TYPES_ONLY
            = StandardNames.KEY_TYPES.toArray(new String[StandardNames.KEY_TYPES.size()]);
    private static final String[] KEY_TYPES_WITH_EMPTY
            = new String[KEY_TYPES_ONLY.length + 1];
    private static final String[] KEY_TYPES_WITH_EMPTY_AND_NULL
            = new String[KEY_TYPES_ONLY.length + 2];
    static {
        System.arraycopy(KEY_TYPES_ONLY, 0,
                         KEY_TYPES_WITH_EMPTY, 0,
                         KEY_TYPES_ONLY.length);
        KEY_TYPES_WITH_EMPTY[KEY_TYPES_WITH_EMPTY.length-1] = "";

        System.arraycopy(KEY_TYPES_WITH_EMPTY, 0,
                         KEY_TYPES_WITH_EMPTY_AND_NULL, 0,
                         KEY_TYPES_WITH_EMPTY.length);
        // extra null at end requires no initialization
    }

    private void test_X509KeyManager(X509KeyManager km, boolean empty, String algorithm)
            throws Exception {
        String[] keyTypes = keyTypes(algorithm);
        for (String keyType : keyTypes) {
            String[] aliases = km.getClientAliases(keyType, null);
            if (empty || keyType == null || keyType.isEmpty()) {
                assertNull(keyType, aliases);
                continue;
            }
            assertNotNull(keyType, aliases);
            for (String alias : aliases) {
                test_X509KeyManager_alias(km, alias, keyType, false, empty);
            }
        }
        for (String keyType : keyTypes) {
            String[] aliases = km.getServerAliases(keyType, null);
            if (empty || keyType == null || keyType.isEmpty()) {
                assertNull(keyType, aliases);
                continue;
            }
            assertNotNull(keyType, aliases);
            for (String alias : aliases) {
                test_X509KeyManager_alias(km, alias, keyType, false, empty);
            }
        }

        String a = km.chooseClientAlias(keyTypes, null, null);
        test_X509KeyManager_alias(km, a, null, true, empty);

        for (String keyType : keyTypes) {
            String[] array = new String[] { keyType };
            String alias = km.chooseClientAlias(array, null, null);
            test_X509KeyManager_alias(km, alias, keyType, false, empty);
        }
        for (String keyType : keyTypes) {
            String alias = km.chooseServerAlias(keyType, null, null);
            test_X509KeyManager_alias(km, alias, keyType, false, empty);
        }
        if (km instanceof X509ExtendedKeyManager) {
            test_X509ExtendedKeyManager((X509ExtendedKeyManager) km, empty, algorithm);
        }
    }

    private void test_X509ExtendedKeyManager(X509ExtendedKeyManager km,
                                             boolean empty, String algorithm) throws Exception {
        String[] keyTypes = keyTypes(algorithm);
        String a = km.chooseEngineClientAlias(keyTypes, null, null);
        test_X509KeyManager_alias(km, a, null, true, empty);
        for (String keyType : keyTypes) {
            String[] array = new String[] { keyType };
            String alias = km.chooseEngineClientAlias(array, null, null);
            test_X509KeyManager_alias(km, alias, keyType, false, empty);
        }
        for (String keyType : keyTypes) {
            String alias = km.chooseEngineServerAlias(keyType, null, null);
            test_X509KeyManager_alias(km, alias, keyType, false, empty);
        }
    }

    private void test_X509KeyManager_alias(X509KeyManager km,
                                           String alias,
                                           String keyType,
                                           boolean many,
                                           boolean empty) throws Exception {
        if (empty || (!many && (keyType == null || keyType.isEmpty()))) {
            assertNull(keyType, alias);
            assertNull(keyType, km.getCertificateChain(alias));
            assertNull(keyType, km.getPrivateKey(alias));
            return;
        }
        assertNotNull(keyType, alias);

        X509Certificate[] certificateChain = km.getCertificateChain(alias);
        PrivateKey privateKey = km.getPrivateKey(alias);

        String keyAlgName;
        String sigAlgName;
        if (keyType == null) {
            keyAlgName = privateKey.getAlgorithm();
            sigAlgName = keyAlgName;
        } else {
            // potentially handle EC_EC or EC_RSA
            keyAlgName = TestKeyStore.keyAlgorithm(keyType);
            sigAlgName = TestKeyStore.signatureAlgorithm(keyType);
            X509Certificate certificate = certificateChain[0];
            assertEquals(keyType, keyAlgName, certificate.getPublicKey().getAlgorithm());
            assertEquals(keyType, keyAlgName, privateKey.getAlgorithm());
            // skip this for EC which could return EC_RSA case instead of EC_EC
            if (!keyType.equals("EC")) {
                String expectedSigAlgName = sigAlgName.toUpperCase();
                String actualSigAlgName = certificate.getSigAlgName().toUpperCase();
                String expected = actualSigAlgName + " contains " + expectedSigAlgName;
                assertTrue(expected, actualSigAlgName.contains(expectedSigAlgName));
            }
        }

        PrivateKeyEntry privateKeyEntry = getTestKeyStore().getPrivateKey(keyAlgName, sigAlgName);
        if (!"EC".equals(keyAlgName)) {
            assertEquals(keyType,
                         Arrays.<Certificate>asList(privateKeyEntry.getCertificateChain()),
                         Arrays.<Certificate>asList(certificateChain));
            assertEquals(keyType,
                         privateKeyEntry.getPrivateKey(), privateKey);
        }
    }

    public void test_KeyManagerFactory_getInstance() throws Exception {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                String type = service.getType();
                if (!type.equals("KeyManagerFactory")) {
                    continue;
                }
                String algorithm = service.getAlgorithm();
                try {
                    {
                        KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
                        assertEquals(algorithm, kmf.getAlgorithm());
                        test_KeyManagerFactory(kmf);
                    }

                    {
                        KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm,
                                                                              provider);
                        assertEquals(algorithm, kmf.getAlgorithm());
                        assertEquals(provider, kmf.getProvider());
                        test_KeyManagerFactory(kmf);
                    }

                    {
                        KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm,
                                                                              provider.getName());
                        assertEquals(algorithm, kmf.getAlgorithm());
                        assertEquals(provider, kmf.getProvider());
                        test_KeyManagerFactory(kmf);
                    }
                } catch (Exception e) {
                    throw new Exception("Problem with algorithm " + algorithm, e);
                }
            }
        }
    }
}
