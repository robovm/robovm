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

package libcore.java.security.cert;

import junit.framework.TestCase;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.cert.PKIXParameters;
import libcore.java.security.TestKeyStore;

public class PKIXParametersTest extends TestCase {

    public void testKeyStoreConstructor() throws Exception {
        TestKeyStore server = TestKeyStore.getServer();
        KeyStore.PrivateKeyEntry pke = server.getPrivateKey("RSA", "RSA");
        char[] password = "password".toCharArray();

        // contains CA and server certificates
        assertEquals(2, new PKIXParameters(server.keyStore).getTrustAnchors().size());

        // just copy server certificates
        KeyStore ks = TestKeyStore.createKeyStore();
        ks.setKeyEntry("key", pke.getPrivateKey(), password, pke.getCertificateChain());
        ks.setCertificateEntry("cert", pke.getCertificateChain()[0]);
        assertEquals(1, new PKIXParameters(ks).getTrustAnchors().size());

        // should fail with just key, even though cert is present in key entry
        try {
            KeyStore keyOnly = TestKeyStore.createKeyStore();
            keyOnly.setKeyEntry("key", pke.getPrivateKey(), password, pke.getCertificateChain());
            new PKIXParameters(keyOnly);
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // should fail with empty KeyStore
        try {
            new PKIXParameters(TestKeyStore.createKeyStore());
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }
    }
}
