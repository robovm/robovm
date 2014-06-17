/*
 * Copyright 2013 The Android Open Source Project
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

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;

import junit.framework.TestCase;
import libcore.java.security.TestKeyStore;

public class X509KeyManagerTest extends TestCase {
    /**
     * Tests whether the key manager will select the right key when the CA is of
     * one key type and the client is of a possibly different key type.
     *
     * <p>There was a bug where EC was being interpreted as EC_EC and only
     * accepting EC signatures when it should accept any signature type.
     */
    public void testChooseClientAlias_Combinations() throws Exception {
        test_ChooseClientAlias_KeyType("RSA", "RSA", "RSA", true);
        test_ChooseClientAlias_KeyType("RSA", "EC", "RSA", true);
        test_ChooseClientAlias_KeyType("RSA", "EC", "EC", false);

        test_ChooseClientAlias_KeyType("EC", "RSA", "EC_RSA", true);
        test_ChooseClientAlias_KeyType("EC", "EC", "EC_RSA", false);

        test_ChooseClientAlias_KeyType("EC", "EC", "EC_EC", true);
        test_ChooseClientAlias_KeyType("EC", "RSA", "EC_EC", false);

        test_ChooseClientAlias_KeyType("EC", "RSA", "RSA", false);
    }

    private void test_ChooseClientAlias_KeyType(String clientKeyType, String caKeyType,
            String selectedKeyType, boolean succeeds) throws Exception {
        TestKeyStore ca = new TestKeyStore.Builder()
                .keyAlgorithms(caKeyType)
                .build();
        TestKeyStore client = new TestKeyStore.Builder().keyAlgorithms(clientKeyType)
                .signer(ca.getPrivateKey(caKeyType, caKeyType))
                .build();

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                .getDefaultAlgorithm());
        kmf.init(client.keyStore, client.keyPassword);

        String[] keyTypes = new String[] { selectedKeyType };
        KeyManager[] managers = kmf.getKeyManagers();
        for (KeyManager manager : managers) {
            if (manager instanceof X509KeyManager) {
                String alias = ((X509KeyManager) manager).chooseClientAlias(keyTypes, null, null);
                if (succeeds) {
                    assertNotNull(alias);
                } else {
                    assertNull(alias);
                }
            }
        }
    }
}
