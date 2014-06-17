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

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Set;
import junit.framework.TestCase;

public class SignatureTest extends TestCase {
    // 20 bytes for DSA
    private final byte[] DATA = new byte[20];

    public void test_getInstance_OpenSSL_ENGINE() throws Exception {
        final String pem_private = "-----BEGIN RSA PRIVATE KEY-----\n"
                + "MIICXAIBAAKBgQDpm4KamxulJnycEzNONGM7p0CvAaoZxJEd5Dvio5b6BROdCtRN\n"
                + "lEsB+9vtB5thkyDVC7N+IW0AjtyDE6h2QP+AWa+c4dh0RM2uNVXkUWPrA8C++GHv\n"
                + "EDlxZzRGiQEMuippYfIyBVkO+4+GRvnkG4dKjzxrQYPqKUK3C4PgFW2FewIDAQAB\n"
                + "AoGAGUTSBsk6X03fcr588TundD9uNr/2V1002Ufj1msdnKPJ8FXIiy+8QVWt/2Cw\n"
                + "RQi2J3VhkAYrlUDex2rr8Qas3E9uuwKgg/MZ4EsJbnKKgkd7uBZfmZ2ogcNJ82u7\n"
                + "teVijFpdsVLDa9aczEppt5sZzyTaBrovrRb+AIRDpMw3I0ECQQD3JkWeQUA9Is1V\n"
                + "z0X/ly/kaQKQLlrwYNdiKF0qOpyTLAguI7asAS72Zj7fThk5bHLM+mmgYwkicIIb\n"
                + "67J32GQbAkEA8fkXqEnwMFYSkRmT9M/qUkwWUsMW12/AoZFI5gwKNDHZYxytGGLw\n"
                + "mC//0qKnyeUG00vz06vLApe4/Sq4ODe6IQJBALEGastF9ZtUuDsEciD2y8kRJlLb\n"
                + "wSt4Ug3u13yN6uTHnzxdPFTLrDW1WsdcC1lEQp5rpwjIpxxR9f/FvVl2V40CQHOY\n"
                + "F6EhkUjGFaCTo4b0PHCMQK3Q3PyWOmP0z+p2HfnJRpx+eoKH4YASjhfF9HoSmywd\n"
                + "wKGCFD1s1ca7vb29gYECQH86GmYZsDoLNWurEVJbkmCr7X1+xwim6umdrNKR27P7\n"
                + "F1y0Sa3YY+LiiRb+IRSWE/onlP+28LIzWGF4lcTfDMc=\n"
                + "-----END RSA PRIVATE KEY-----";

        final byte[] der_public = new byte[] {
                (byte) 0x30, (byte) 0x81, (byte) 0x9F, (byte) 0x30, (byte) 0x0D, (byte) 0x06,
                (byte) 0x09, (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7,
                (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x05, (byte) 0x00,
                (byte) 0x03, (byte) 0x81, (byte) 0x8D, (byte) 0x00, (byte) 0x30, (byte) 0x81,
                (byte) 0x89, (byte) 0x02, (byte) 0x81, (byte) 0x81, (byte) 0x00, (byte) 0xE9,
                (byte) 0x9B, (byte) 0x82, (byte) 0x9A, (byte) 0x9B, (byte) 0x1B, (byte) 0xA5,
                (byte) 0x26, (byte) 0x7C, (byte) 0x9C, (byte) 0x13, (byte) 0x33, (byte) 0x4E,
                (byte) 0x34, (byte) 0x63, (byte) 0x3B, (byte) 0xA7, (byte) 0x40, (byte) 0xAF,
                (byte) 0x01, (byte) 0xAA, (byte) 0x19, (byte) 0xC4, (byte) 0x91, (byte) 0x1D,
                (byte) 0xE4, (byte) 0x3B, (byte) 0xE2, (byte) 0xA3, (byte) 0x96, (byte) 0xFA,
                (byte) 0x05, (byte) 0x13, (byte) 0x9D, (byte) 0x0A, (byte) 0xD4, (byte) 0x4D,
                (byte) 0x94, (byte) 0x4B, (byte) 0x01, (byte) 0xFB, (byte) 0xDB, (byte) 0xED,
                (byte) 0x07, (byte) 0x9B, (byte) 0x61, (byte) 0x93, (byte) 0x20, (byte) 0xD5,
                (byte) 0x0B, (byte) 0xB3, (byte) 0x7E, (byte) 0x21, (byte) 0x6D, (byte) 0x00,
                (byte) 0x8E, (byte) 0xDC, (byte) 0x83, (byte) 0x13, (byte) 0xA8, (byte) 0x76,
                (byte) 0x40, (byte) 0xFF, (byte) 0x80, (byte) 0x59, (byte) 0xAF, (byte) 0x9C,
                (byte) 0xE1, (byte) 0xD8, (byte) 0x74, (byte) 0x44, (byte) 0xCD, (byte) 0xAE,
                (byte) 0x35, (byte) 0x55, (byte) 0xE4, (byte) 0x51, (byte) 0x63, (byte) 0xEB,
                (byte) 0x03, (byte) 0xC0, (byte) 0xBE, (byte) 0xF8, (byte) 0x61, (byte) 0xEF,
                (byte) 0x10, (byte) 0x39, (byte) 0x71, (byte) 0x67, (byte) 0x34, (byte) 0x46,
                (byte) 0x89, (byte) 0x01, (byte) 0x0C, (byte) 0xBA, (byte) 0x2A, (byte) 0x69,
                (byte) 0x61, (byte) 0xF2, (byte) 0x32, (byte) 0x05, (byte) 0x59, (byte) 0x0E,
                (byte) 0xFB, (byte) 0x8F, (byte) 0x86, (byte) 0x46, (byte) 0xF9, (byte) 0xE4,
                (byte) 0x1B, (byte) 0x87, (byte) 0x4A, (byte) 0x8F, (byte) 0x3C, (byte) 0x6B,
                (byte) 0x41, (byte) 0x83, (byte) 0xEA, (byte) 0x29, (byte) 0x42, (byte) 0xB7,
                (byte) 0x0B, (byte) 0x83, (byte) 0xE0, (byte) 0x15, (byte) 0x6D, (byte) 0x85,
                (byte) 0x7B, (byte) 0x02, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x01
        };

        // We only need to test this on the OpenSSL provider.
        Provider p = Security.getProvider(OpenSSLProvider.PROVIDER_NAME);

        /* ENGINE-based private key */
        NativeCryptoTest.loadTestEngine();
        OpenSSLEngine engine = OpenSSLEngine.getInstance(NativeCryptoTest.TEST_ENGINE_ID);
        PrivateKey privKey = engine.getPrivateKeyById(pem_private);
        assertTrue(privKey instanceof RSAPrivateKey);

        /* Non-ENGINE-based public key */
        KeyFactory kf = KeyFactory.getInstance("RSA", p);
        PublicKey pubKey = kf.generatePublic(new X509EncodedKeySpec(der_public));

        KeyPair kp = new KeyPair(pubKey, privKey);

        Set<Provider.Service> services = p.getServices();
        for (Provider.Service service : services) {
            if ("Signature".equals(service.getType()) && service.getAlgorithm().contains("RSA")) {
                Signature sig1 = Signature.getInstance(service.getAlgorithm(), p);
                test_Signature(sig1, kp);
            }
        }
    }

    private void test_Signature(Signature sig, KeyPair keyPair) throws Exception {
        sig.initSign(keyPair.getPrivate());
        sig.update(DATA);
        byte[] signature = sig.sign();
        assertNotNull(sig.getAlgorithm(), signature);
        assertTrue(sig.getAlgorithm(), signature.length > 0);

        sig.initVerify(keyPair.getPublic());
        sig.update(DATA);
        assertTrue(sig.getAlgorithm(), sig.verify(signature));

        // After verify, should be reusable as if we are after initVerify
        sig.update(DATA);
        assertTrue(sig.getAlgorithm(), sig.verify(signature));

        // Calling Signature.verify a second time should not throw
        // http://code.google.com/p/android/issues/detail?id=34933
        sig.verify(signature);
    }
}
