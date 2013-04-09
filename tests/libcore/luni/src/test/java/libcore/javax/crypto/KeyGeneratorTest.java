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

package libcore.javax.crypto;

import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import junit.framework.TestCase;

public class KeyGeneratorTest extends TestCase {

    private static boolean isUnsupported(KeyGenerator kg) {
        // Don't bother testing "Sun..." KeyGenerators
        return (kg.getAlgorithm().startsWith("Sun"));
    }

    public void test_getInstance() throws Exception {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                String type = service.getType();
                if (!type.equals("KeyGenerator")) {
                    continue;
                }
                String algorithm = service.getAlgorithm();
                try {
                    // KeyGenerator.getInstance(String)
                    KeyGenerator kg1 = KeyGenerator.getInstance(algorithm);
                    assertEquals(algorithm, kg1.getAlgorithm());
                    test_KeyGenerator(kg1);

                    // KeyGenerator.getInstance(String, Provider)
                    KeyGenerator kg2 = KeyGenerator.getInstance(algorithm, provider);
                    assertEquals(algorithm, kg2.getAlgorithm());
                    assertEquals(provider, kg2.getProvider());
                    test_KeyGenerator(kg2);

                    // KeyGenerator.getInstance(String, String)
                    KeyGenerator kg3 = KeyGenerator.getInstance(algorithm, provider.getName());
                    assertEquals(algorithm, kg3.getAlgorithm());
                    assertEquals(provider, kg3.getProvider());
                    test_KeyGenerator(kg3);
                } catch (Exception e) {
                    throw new Exception("Problem testing KeyPairGenerator." + algorithm, e);
                }
            }
        }
    }

    private static final Map<String, List<Integer>> KEY_SIZES
            = new HashMap<String, List<Integer>>();
    private static void putKeySize(String algorithm, int keySize) {
        algorithm = algorithm.toUpperCase();
        List<Integer> keySizes = KEY_SIZES.get(algorithm);
        if (keySizes == null) {
            keySizes = new ArrayList<Integer>();
            KEY_SIZES.put(algorithm, keySizes);
        }
        keySizes.add(keySize);
    }
    private static List<Integer> getKeySizes(String algorithm) throws Exception {
        algorithm = algorithm.toUpperCase();
        List<Integer> keySizes = KEY_SIZES.get(algorithm);
        if (keySizes == null) {
            throw new Exception("Unknown key sizes for KeyGenerator." + algorithm);
        }
        return keySizes;
    }
    static {
        putKeySize("AES", 128);
        putKeySize("AES", 192);
        putKeySize("AES", 256);
        putKeySize("ARC4", 1024);
        putKeySize("ARC4", 40);
        putKeySize("ARC4", 41);
        putKeySize("ARCFOUR", 1024);
        putKeySize("ARCFOUR", 40);
        putKeySize("ARCFOUR", 41);
        putKeySize("Blowfish", 32);
        putKeySize("Blowfish", 32+8);
        putKeySize("Blowfish", 448);
        putKeySize("DES", 56);
        putKeySize("DESede", 112);
        putKeySize("DESede", 168);
        putKeySize("RC2", 40);
        putKeySize("RC2", 41);
        putKeySize("RC2", 1024);
        putKeySize("RC4", 40);
        putKeySize("RC4", 41);
        putKeySize("RC4", 1024);
        putKeySize("HmacMD5", 1);
        putKeySize("HmacMD5", 1025);
        putKeySize("HmacSHA1", 1);
        putKeySize("HmacSHA1", 1025);
        putKeySize("HmacSHA256", 40);
        putKeySize("HmacSHA256", 1025);
        putKeySize("HmacSHA384", 40);
        putKeySize("HmacSHA384", 1025);
        putKeySize("HmacSHA512", 40);
        putKeySize("HmacSHA512", 1025);
    }

    private void test_KeyGenerator(KeyGenerator kg) throws Exception {
        if (isUnsupported(kg)) {
            return;
        }

        kg.init((SecureRandom) null);
        test_SecretKey(kg, kg.generateKey());

        kg.init(new SecureRandom());
        test_SecretKey(kg, kg.generateKey());

        String algorithm = kg.getAlgorithm();
        List<Integer> keySizes = getKeySizes(algorithm);
        for (int keySize : keySizes) {
            kg.init(keySize);
            test_SecretKey(kg, kg.generateKey());

            kg.init(keySize, (SecureRandom) null);
            test_SecretKey(kg, kg.generateKey());

            kg.init(keySize, new SecureRandom());
            test_SecretKey(kg, kg.generateKey());
        }
    }

    private void test_SecretKey(KeyGenerator kg, SecretKey sk) throws Exception {
        assertNotNull(sk);
        assertEquals(kg.getAlgorithm().toUpperCase(), sk.getAlgorithm().toUpperCase());
        assertNotNull(sk.getEncoded());
        assertNotNull(sk.getFormat());
    }
}
