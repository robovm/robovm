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

import com.android.org.bouncycastle.asn1.x509.KeyPurposeId;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXParameters;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Set;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import junit.framework.TestCase;
import libcore.java.security.StandardNames;
import libcore.java.security.TestKeyStore;

public class TrustManagerFactoryTest extends TestCase {

    private static final String [] KEY_TYPES = new String[] { "RSA", "DSA", "EC", "EC_RSA" };

    private static TestKeyStore TEST_KEY_STORE;

    // note the rare usage of DSA keys here in addition to RSA
    private static TestKeyStore getTestKeyStore() throws Exception {
        if (TEST_KEY_STORE == null) {
            TEST_KEY_STORE = new TestKeyStore.Builder()
                    .keyAlgorithms(KEY_TYPES)
                    .aliasPrefix("rsa-dsa-ec")
                    .build();
        }
        return TEST_KEY_STORE;
    }

    private static boolean supportsManagerFactoryParameters(String algorithm) {
        return (StandardNames.IS_RI && algorithm.equals("PKIX"));
    }

    public void test_TrustManagerFactory_getDefaultAlgorithm() throws Exception {
        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        assertEquals(StandardNames.TRUST_MANAGER_FACTORY_DEFAULT, algorithm);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
        test_TrustManagerFactory(tmf);
    }

    private static class UselessManagerFactoryParameters implements ManagerFactoryParameters {}

    private void test_TrustManagerFactory(TrustManagerFactory tmf)
            throws Exception {
        assertNotNull(tmf);
        assertNotNull(tmf.getAlgorithm());
        assertNotNull(tmf.getProvider());

        // before init
        try {
            tmf.getTrustManagers();
            fail();
        } catch (IllegalStateException expected) {
        }

        // init with null ManagerFactoryParameters
        try {
            tmf.init((ManagerFactoryParameters) null);
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // init with useless ManagerFactoryParameters
        try {
            tmf.init(new UselessManagerFactoryParameters());
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // init with PKIXParameters ManagerFactoryParameters
        try {
            PKIXParameters pp = new PKIXParameters(getTestKeyStore().keyStore);
            CertPathTrustManagerParameters cptmp = new CertPathTrustManagerParameters(pp);
            tmf.init(cptmp);
            fail();
        } catch (InvalidAlgorithmParameterException expected) {
        }

        // init with PKIXBuilderParameters ManagerFactoryParameters
        X509CertSelector xcs = new X509CertSelector();
        PKIXBuilderParameters pbp = new PKIXBuilderParameters(getTestKeyStore().keyStore, xcs);
        CertPathTrustManagerParameters cptmp = new CertPathTrustManagerParameters(pbp);
        if (supportsManagerFactoryParameters(tmf.getAlgorithm())) {
            tmf.init(cptmp);
            test_TrustManagerFactory_getTrustManagers(tmf);
        } else {
            try {
                tmf.init(cptmp);
                fail();
            } catch (InvalidAlgorithmParameterException expected) {
            }
        }

        // init with null for default KeyStore
        tmf.init((KeyStore) null);
        test_TrustManagerFactory_getTrustManagers(tmf);

        // init with specific key store
        tmf.init(getTestKeyStore().keyStore);
        test_TrustManagerFactory_getTrustManagers(tmf);
    }

    private void test_TrustManagerFactory_getTrustManagers(TrustManagerFactory tmf)
            throws Exception {
        TrustManager[] trustManagers = tmf.getTrustManagers();
        assertNotNull(trustManagers);
        assertTrue(trustManagers.length > 0);
        for (TrustManager trustManager : trustManagers) {
            assertNotNull(trustManager);
            if (trustManager instanceof X509TrustManager) {
                test_X509TrustManager((X509TrustManager) trustManager);
            }
        }
    }

    private void test_X509TrustManager(X509TrustManager tm) throws Exception {
        for (String keyType : KEY_TYPES) {
            X509Certificate[] issuers = tm.getAcceptedIssuers();
            assertNotNull(issuers);
            assertTrue(issuers.length > 1);
            assertNotSame(issuers, tm.getAcceptedIssuers());
            boolean defaultTrustManager
                    // RI de-duplicates certs from TrustedCertificateEntry and PrivateKeyEntry
                    = issuers.length > (StandardNames.IS_RI ? 1 : 2) * KEY_TYPES.length;

            String keyAlgName = TestKeyStore.keyAlgorithm(keyType);
            String sigAlgName = TestKeyStore.signatureAlgorithm(keyType);
            PrivateKeyEntry pke = getTestKeyStore().getPrivateKey(keyAlgName, sigAlgName);
            X509Certificate[] chain = (X509Certificate[]) pke.getCertificateChain();
            if (defaultTrustManager) {
                try {
                    tm.checkClientTrusted(chain, keyType);
                    fail();
                } catch (CertificateException expected) {
                }
                try {
                    tm.checkServerTrusted(chain, keyType);
                    fail();
                } catch (CertificateException expected) {
                }
            } else {
                tm.checkClientTrusted(chain, keyType);
                tm.checkServerTrusted(chain, keyType);
            }

        }
    }

    public void test_TrustManagerFactory_getInstance() throws Exception {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                String type = service.getType();
                if (!type.equals("TrustManagerFactory")) {
                    continue;
                }
                String algorithm = service.getAlgorithm();
                {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
                    assertEquals(algorithm, tmf.getAlgorithm());
                    test_TrustManagerFactory(tmf);
                }

                {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm,
                                                                          provider);
                    assertEquals(algorithm, tmf.getAlgorithm());
                    assertEquals(provider, tmf.getProvider());
                    test_TrustManagerFactory(tmf);
                }

                {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm,
                                                                          provider.getName());
                    assertEquals(algorithm, tmf.getAlgorithm());
                    assertEquals(provider, tmf.getProvider());
                    test_TrustManagerFactory(tmf);
                }
            }
        }
    }

    public void test_TrustManagerFactory_intermediate() throws Exception {
        // chain should be server/intermediate/root
        PrivateKeyEntry pke = TestKeyStore.getServer().getPrivateKey("RSA", "RSA");
        X509Certificate[] chain = (X509Certificate[])pke.getCertificateChain();
        assertEquals(3, chain.length);

        // keyStore should contain only the intermediate CA so we can
        // test proper validation even if there are extra certs after
        // the trusted one (in this case the original root is "extra")
        KeyStore keyStore = TestKeyStore.createKeyStore();
        keyStore.setCertificateEntry("alias", chain[1]);

        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                String type = service.getType();
                if (!type.equals("TrustManagerFactory")) {
                    continue;
                }
                String algorithm = service.getAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
                tmf.init(keyStore);
                TrustManager[] trustManagers = tmf.getTrustManagers();
                for (TrustManager trustManager : trustManagers) {
                    if (!(trustManager instanceof X509TrustManager)) {
                        continue;
                    }
                    X509TrustManager tm = (X509TrustManager) trustManager;
                    tm.checkClientTrusted(chain, "RSA");
                    tm.checkServerTrusted(chain, "RSA");
                }
            }
        }
    }

    public void test_TrustManagerFactory_keyOnly() throws Exception {
        // create a KeyStore containing only a private key with chain.
        // unlike PKIXParameters(KeyStore), the cert chain of the key should be trusted.
        KeyStore ks = TestKeyStore.createKeyStore();
        KeyStore.PrivateKeyEntry pke = getTestKeyStore().getPrivateKey("RSA", "RSA");
        ks.setKeyEntry("key", pke.getPrivateKey(), "pw".toCharArray(), pke.getCertificateChain());

        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
        tmf.init(ks);
        X509TrustManager trustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        trustManager.checkServerTrusted((X509Certificate[]) pke.getCertificateChain(), "RSA");
    }

    public void test_TrustManagerFactory_extendedKeyUsage() throws Exception {
        // anyExtendedKeyUsage should work for client or server
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.anyExtendedKeyUsage, false, true, true);
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.anyExtendedKeyUsage, true,  true, true);

        // critical clientAuth should work for client
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_clientAuth,    false, true, false);
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_clientAuth,    true,  true, false);

        // critical serverAuth should work for server
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_serverAuth,    false, false, true);
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_serverAuth,    true,  false, true);

        // codeSigning should not work
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_codeSigning,   false, false, false);
        test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId.id_kp_codeSigning,   true,  false, false);
    }

    private void test_TrustManagerFactory_extendedKeyUsage(KeyPurposeId keyPurposeId,
                                                           boolean critical,
                                                           boolean client,
                                                           boolean server)
            throws Exception {
        String algorithm = "RSA";
        TestKeyStore intermediateCa = TestKeyStore.getIntermediateCa();
        TestKeyStore leaf = new TestKeyStore.Builder()
                .keyAlgorithms(new String[] { algorithm })
                .aliasPrefix("criticalCodeSigning")
                .signer(intermediateCa.getPrivateKey("RSA", "RSA"))
                .rootCa(intermediateCa.getRootCertificate("RSA"))
                .addExtendedKeyUsage(keyPurposeId, critical)
                .build();
        // leaf.dump("test_TrustManagerFactory_criticalCodeSigning");
        PrivateKeyEntry privateKeyEntry = leaf.getPrivateKey(algorithm, algorithm);
        X509Certificate[] chain = (X509Certificate[]) privateKeyEntry.getCertificateChain();

        TestKeyStore rootCa = TestKeyStore.getRootCa();
        X509TrustManager trustManager = (X509TrustManager) rootCa.trustManagers[0];
        try {
            trustManager.checkClientTrusted(chain, algorithm);
            assertTrue(client);
        } catch (Exception e) {
            assertFalse(client);
        }
        try {
            trustManager.checkServerTrusted(chain, algorithm);
            assertTrue(server);
        } catch (Exception e) {
            assertFalse(server);
        }
    }
}
