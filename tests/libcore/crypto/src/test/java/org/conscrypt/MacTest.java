/*
 * Copyright (C) 2012 The Android Open Source Project
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

package org.conscrypt;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.TestCase;

public class MacTest extends TestCase {
    public void test_getInstance_OpenSSL_ENGINE() throws Exception {
        final String secret = "-HMAC-test1";
        final byte[] testString = "testing123".getBytes();

        Provider p = Security.getProvider(OpenSSLProvider.PROVIDER_NAME);
        NativeCryptoTest.loadTestEngine();
        OpenSSLEngine engine = OpenSSLEngine.getInstance(NativeCryptoTest.TEST_ENGINE_ID);

        /*
         * The "-HMAC-" prefix is a special prefix recognized by
         * test_openssl_engine.cpp
         */
        SecretKey key1 = engine.getSecretKeyById(secret, "HmacSHA256");
        SecretKey key1dupe = engine.getSecretKeyById(secret, "HmacSHA256");

        /* Non-ENGINE-based SecretKey */
        SecretKey key2 = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

        /* The one that is ENGINE-based can't be equal to a non-ENGINE one. */
        assertFalse(key1.equals(key2));
        assertEquals(key1, key1dupe);
        assertNull(key1.getFormat());
        assertNull(key1.getEncoded());
        assertEquals("RAW", key2.getFormat());
        assertEquals(Arrays.toString(secret.getBytes()), Arrays.toString(key2.getEncoded()));

        Mac mac1 = Mac.getInstance("HmacSHA256", p);
        mac1.init(key1);
        mac1.update(testString);
        byte[] output1 = mac1.doFinal();
        assertEquals(mac1.getMacLength(), output1.length);

        Mac mac2 = Mac.getInstance("HmacSHA256", p);
        mac2.init(key2);
        mac2.update(testString);
        byte[] output2 = mac2.doFinal();

        assertEquals(Arrays.toString(output2), Arrays.toString(output1));
    }
}
