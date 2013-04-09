/*
 * Copyright (C) 2011 The Android Open Source Project
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

package org.apache.harmony.xnet.provider.jsse;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import junit.framework.TestCase;
import libcore.java.security.TestKeyStore;

public class TrustManagerImplTest extends TestCase {

    /**
     * Ensure that our non-standard behavior of learning to trust new
     * intermediate CAs does not regress. http://b/3404902
     */
    public void testLearnIntermediate() throws Exception {

        // chain3 should be server/intermediate/root
        KeyStore.PrivateKeyEntry pke = TestKeyStore.getServer().getPrivateKey("RSA", "RSA");
        X509Certificate[] chain3 = (X509Certificate[])pke.getCertificateChain();
        X509Certificate root = chain3[2];
        X509Certificate intermediate = chain3[1];
        X509Certificate server = chain3[0];
        X509Certificate[] chain2 =  new X509Certificate[] { server, intermediate };
        X509Certificate[] chain1 =  new X509Certificate[] { server };

        // Normal behavior
        assertValid(chain3,   trustManager(root));
        assertValid(chain2,   trustManager(root));
        assertInvalid(chain1, trustManager(root));
        assertValid(chain3,   trustManager(intermediate));
        assertValid(chain2,   trustManager(intermediate));
        assertValid(chain1,   trustManager(intermediate));
        assertValid(chain3,   trustManager(server));
        assertValid(chain2,   trustManager(server));
        assertValid(chain1,   trustManager(server));

        // non-standard behavior
        X509TrustManager tm = trustManager(root);
        // fail on short chain with only root trusted
        assertInvalid(chain1, tm);
        // succeed on longer chain, learn intermediate
        assertValid(chain2, tm);
        // now we can validate the short chain
        assertValid(chain1, tm);
    }

    private X509TrustManager trustManager(X509Certificate ca) throws Exception {
        KeyStore keyStore = TestKeyStore.createKeyStore();
        keyStore.setCertificateEntry("alias", ca);

        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
        tmf.init(keyStore);
        return (X509TrustManager) tmf.getTrustManagers()[0];
    }

    private void assertValid(X509Certificate[] chain, X509TrustManager tm) throws Exception {
        tm.checkClientTrusted(chain, "RSA");
        tm.checkServerTrusted(chain, "RSA");
    }
    private void assertInvalid(X509Certificate[] chain, X509TrustManager tm) {
        try {
            tm.checkClientTrusted(chain, "RSA");
            fail();
        } catch (CertificateException expected) {
        }
        try {
            tm.checkServerTrusted(chain, "RSA");
            fail();
        } catch (CertificateException expected) {
        }
    }
}
