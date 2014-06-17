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

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

public class SignatureTest extends TestCase {

    // 20 bytes for DSA
    private final byte[] DATA = new byte[20];

    public void test_getInstance() throws Exception {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                String type = service.getType();
                if (!type.equals("Signature")) {
                    continue;
                }
                String algorithm = service.getAlgorithm();
                try {
                    KeyPair kp = keyPair(algorithm, provider.getName());
                    // Signature.getInstance(String)
                    Signature sig1 = Signature.getInstance(algorithm);
                    assertEquals(algorithm, sig1.getAlgorithm());
                    test_Signature(sig1, kp);

                    // Signature.getInstance(String, Provider)
                    Signature sig2 = Signature.getInstance(algorithm, provider);
                    assertEquals(algorithm, sig2.getAlgorithm());
                    assertEquals(provider, sig2.getProvider());
                    test_Signature(sig2, kp);

                    // Signature.getInstance(String, String)
                    Signature sig3 = Signature.getInstance(algorithm, provider.getName());
                    assertEquals(algorithm, sig3.getAlgorithm());
                    assertEquals(provider, sig3.getProvider());
                    test_Signature(sig3, kp);
                } catch (Exception e) {
                    throw new Exception("Problem testing Signature." + algorithm, e);
                }
            }
        }
    }

    private final Map<String, KeyPair> keypairAlgorithmToInstance
            = new HashMap<String, KeyPair>();

    private KeyPair keyPair(String sigAlgorithm, String providerName) throws Exception {
        if (sigAlgorithm.endsWith("Encryption")) {
            sigAlgorithm = sigAlgorithm.substring(0, sigAlgorithm.length()-"Encryption".length());
        }

        String kpAlgorithm;
        // note ECDSA must be before DSA
        if (sigAlgorithm.endsWith("ECDSA")) {
            kpAlgorithm = "EC";
        } else if (sigAlgorithm.endsWith("DSA")) {
            kpAlgorithm = "DSA";
        } else if (sigAlgorithm.endsWith("RSA")) {
            kpAlgorithm = "RSA";
        } else {
            throw new Exception("Unknown KeyPair algorithm for Signature algorithm "
                                + sigAlgorithm);
        }

        KeyPair kp = keypairAlgorithmToInstance.get(kpAlgorithm);
        if (kp == null) {
            kp = KeyPairGenerator.getInstance(kpAlgorithm).generateKeyPair();
            keypairAlgorithmToInstance.put(sigAlgorithm, kp);
        }
        return kp;
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

        /*
         * The RI appears to clear out the input data in RawDSA while calling
         * verify a second time.
         */
        if (StandardNames.IS_RI && "NONEwithDSA".equalsIgnoreCase(sig.getAlgorithm())) {
            try {
                sig.verify(signature);
                fail("Expected RI to have a NONEwithDSA bug");
            } catch (SignatureException bug) {
            }
        } else {
            // Calling Signature.verify a second time should not throw
            // http://code.google.com/p/android/issues/detail?id=34933
            sig.verify(signature);
        }
    }

    private static final byte[] PK_BYTES = hexToBytes(
            "30819f300d06092a864886f70d010101050003818d0030818902818100cd769d178f61475fce3001"
            + "2604218320c77a427121d3b41dd76756c8fc0c428cd15cb754adc85466f47547b1c85623d9c17fc6"
            + "4f202fca21099caf99460c824ad657caa8c2db34996838d32623c4f23c8b6a4e6698603901262619"
            + "4840e0896b1a6ec4f6652484aad04569bb6a885b822a10d700224359c632dc7324520cbb3d020301"
            + "0001");
    private static final byte[] CONTENT = hexToBytes(
            "f2fa9d73656e00fa01edc12e73656e2e7670632e6432004867268c46dd95030b93ce7260423e5c00"
            + "fabd4d656d6265727300fa018dc12e73656e2e7670632e643100d7c258dc00fabd44657669636573"
            + "00faa54b65797300fa02b5c12e4d2e4b009471968cc68835f8a68dde10f53d19693d480de767e5fb"
            + "976f3562324006372300fabdfd04e1f51ef3aa00fa8d00000001a203e202859471968cc68835f8a6"
            + "8dde10f53d19693d480de767e5fb976f356232400637230002bab504e1f51ef5810002c29d28463f"
            + "0003da8d000001e201eaf2fa9d73656e00fa01edc12e73656e2e7670632e6432004867268c46dd95"
            + "030b93ce7260423e5c00fabd4d656d6265727300fa018dc12e73656e2e7670632e643100d7c258dc"
            + "00fabd4465766963657300faa54b65797300fa02b5c12e4d2e4b009471968cc68835f8a68dde10f5"
            + "3d19693d480de767e5fb976f3562324006372300fabdfd04e1f51ef3aa000003e202859471968cc6"
            + "8835f8a68dde10f53d19693d480de767e5fb976f3562324006372300000000019a0a9530819f300d"
            + "06092a864886f70d010101050003818d0030818902818100cd769d178f61475fce30012604218320"
            + "c77a427121d3b41dd76756c8fc0c428cd15cb754adc85466f47547b1c85623d9c17fc64f202fca21"
            + "099caf99460c824ad657caa8c2db34996838d32623c4f23c8b6a4e66986039012626194840e0896b"
            + "1a6ec4f6652484aad04569bb6a885b822a10d700224359c632dc7324520cbb3d020301000100");
    private static final byte[] SIGNATURE = hexToBytes(
            "b4016456148cd2e9f580470aad63d19c1fee52b38c9dcb5b4d61a7ca369a7277497775d106d86394"
            + "a69229184333b5a3e6261d5bcebdb02530ca9909f4d790199eae7c140f7db39dee2232191bdf0bfb"
            + "34fdadc44326b9b3f3fa828652bab07f0362ac141c8c3784ebdec44e0b156a5e7bccdc81a56fe954"
            + "56ac8c0e4ae12d97");

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                  + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    // http://code.google.com/p/android/issues/detail?id=18566
    // http://b/5038554
    public void test18566() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(PK_BYTES);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pk = keyFactory.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(pk);
        sig.update(CONTENT);
        assertTrue(sig.verify(SIGNATURE));
    }

    /*
     * Test vectors generated with this private key:
     *
     * -----BEGIN RSA PRIVATE KEY-----
     * MIIEpAIBAAKCAQEA4Ec+irjyKE/rnnQv+XSPoRjtmGM8kvUq63ouvg075gMpvnZq
     * 0Q62pRXQ0s/ZvqeTDwwwZTeJn3lYzT6FsB+IGFJNMSWEqUslHjYltUFB7b/uGYgI
     * 4buX/Hy0m56qr2jpyY19DtxTu8D6ADQ1bWMF+7zDxwAUBThqu8hzyw8+90JfPTPf
     * ezFa4DbSoLZq/UdQOxab8247UWJRW3Ff2oPeryxYrrmr+zCXw8yd2dvl7ylsF2E5
     * Ao6KZx5jBW1F9AGI0sQTNJCEXeUsJTTpxrJHjAe9rpKII7YtBmx3cPn2Pz26JH9T
     * CER0e+eqqF2FO4vSRKzsPePImrRkU6tNJMOsaQIDAQABAoIBADd4R3al8XaY9ayW
     * DfuDobZ1ZOZIvQWXz4q4CHGG8macJ6nsvdSA8Bl6gNBzCebGqW+SUzHlf4tKxvTU
     * XtpFojJpwJ/EKMB6Tm7fc4oV3sl/q9Lyu0ehTyDqcvz+TDbgGtp3vRN82NTaELsW
     * LpSkZilx8XX5hfoYjwVsuX7igW9Dq503R2Ekhs2owWGWwwgYqZXshdOEZ3kSZ7O/
     * IfJzcQppJYYldoQcW2cSwS1L0govMpmtt8E12l6VFavadufK8qO+gFUdBzt4vxFi
     * xIrSt/R0OgI47k0lL31efmUzzK5kzLOTYAdaL9HgNOw65c6cQIzL8OJeQRQCFoez
     * 3UdUroECgYEA9UGIS8Nzeyki1BGe9F4t7izUy7dfRVBaFXqlAJ+Zxzot8HJKxGAk
     * MGMy6omBd2NFRl3G3x4KbxQK/ztzluaomUrF2qloc0cv43dJ0U6z4HXmKdvrNYMz
     * im82SdCiZUp6Qv2atr+krE1IHTkLsimwZL3DEcwb4bYxidp8QM3s8rECgYEA6hp0
     * LduIHO23KIyH442GjdekCdFaQ/RF1Td6C1cx3b/KLa8oqOE81cCvzsM0fXSjniNa
     * PNljPydN4rlPkt9DgzkR2enxz1jyfeLgj/RZZMcg0+whOdx8r8kSlTzeyy81Wi4s
     * NaUPrXVMs7IxZkJLo7bjESoriYw4xcFe2yOGkzkCgYBRgo8exv2ZYCmQG68dfjN7
     * pfCvJ+mE6tiVrOYr199O5FoiQInyzBUa880XP84EdLywTzhqLNzA4ANrokGfVFeS
     * YtRxAL6TGYSj76Bb7PFBV03AebOpXEqD5sQ/MhTW3zLVEt4ZgIXlMeYWuD/X3Z0f
     * TiYHwzM9B8VdEH0dOJNYcQKBgQDbT7UPUN6O21P/NMgJMYigUShn2izKBIl3WeWH
     * wkQBDa+GZNWegIPRbBZHiTAfZ6nweAYNg0oq29NnV1toqKhCwrAqibPzH8zsiiL+
     * OVeVxcbHQitOXXSh6ajzDndZufwtY5wfFWc+hOk6XvFQb0MVODw41Fy9GxQEj0ch
     * 3IIyYQKBgQDYEUWTr0FfthLb8ZI3ENVNB0hiBadqO0MZSWjA3/HxHvD2GkozfV/T
     * dBu8lkDkR7i2tsR8OsEgQ1fTsMVbqShr2nP2KSlvX6kUbYl2NX08dR51FIaWpAt0
     * aFyCzjCQLWOdck/yTV4ulAfuNO3tLjtN9lqpvP623yjQe6aQPxZXaA==
     * -----END RSA PRIVATE KEY-----
     *
     */

    private static final BigInteger RSA_2048_modulus = new BigInteger(new byte[] {
        (byte) 0x00, (byte) 0xe0, (byte) 0x47, (byte) 0x3e, (byte) 0x8a, (byte) 0xb8, (byte) 0xf2, (byte) 0x28,
        (byte) 0x4f, (byte) 0xeb, (byte) 0x9e, (byte) 0x74, (byte) 0x2f, (byte) 0xf9, (byte) 0x74, (byte) 0x8f,
        (byte) 0xa1, (byte) 0x18, (byte) 0xed, (byte) 0x98, (byte) 0x63, (byte) 0x3c, (byte) 0x92, (byte) 0xf5,
        (byte) 0x2a, (byte) 0xeb, (byte) 0x7a, (byte) 0x2e, (byte) 0xbe, (byte) 0x0d, (byte) 0x3b, (byte) 0xe6,
        (byte) 0x03, (byte) 0x29, (byte) 0xbe, (byte) 0x76, (byte) 0x6a, (byte) 0xd1, (byte) 0x0e, (byte) 0xb6,
        (byte) 0xa5, (byte) 0x15, (byte) 0xd0, (byte) 0xd2, (byte) 0xcf, (byte) 0xd9, (byte) 0xbe, (byte) 0xa7,
        (byte) 0x93, (byte) 0x0f, (byte) 0x0c, (byte) 0x30, (byte) 0x65, (byte) 0x37, (byte) 0x89, (byte) 0x9f,
        (byte) 0x79, (byte) 0x58, (byte) 0xcd, (byte) 0x3e, (byte) 0x85, (byte) 0xb0, (byte) 0x1f, (byte) 0x88,
        (byte) 0x18, (byte) 0x52, (byte) 0x4d, (byte) 0x31, (byte) 0x25, (byte) 0x84, (byte) 0xa9, (byte) 0x4b,
        (byte) 0x25, (byte) 0x1e, (byte) 0x36, (byte) 0x25, (byte) 0xb5, (byte) 0x41, (byte) 0x41, (byte) 0xed,
        (byte) 0xbf, (byte) 0xee, (byte) 0x19, (byte) 0x88, (byte) 0x08, (byte) 0xe1, (byte) 0xbb, (byte) 0x97,
        (byte) 0xfc, (byte) 0x7c, (byte) 0xb4, (byte) 0x9b, (byte) 0x9e, (byte) 0xaa, (byte) 0xaf, (byte) 0x68,
        (byte) 0xe9, (byte) 0xc9, (byte) 0x8d, (byte) 0x7d, (byte) 0x0e, (byte) 0xdc, (byte) 0x53, (byte) 0xbb,
        (byte) 0xc0, (byte) 0xfa, (byte) 0x00, (byte) 0x34, (byte) 0x35, (byte) 0x6d, (byte) 0x63, (byte) 0x05,
        (byte) 0xfb, (byte) 0xbc, (byte) 0xc3, (byte) 0xc7, (byte) 0x00, (byte) 0x14, (byte) 0x05, (byte) 0x38,
        (byte) 0x6a, (byte) 0xbb, (byte) 0xc8, (byte) 0x73, (byte) 0xcb, (byte) 0x0f, (byte) 0x3e, (byte) 0xf7,
        (byte) 0x42, (byte) 0x5f, (byte) 0x3d, (byte) 0x33, (byte) 0xdf, (byte) 0x7b, (byte) 0x31, (byte) 0x5a,
        (byte) 0xe0, (byte) 0x36, (byte) 0xd2, (byte) 0xa0, (byte) 0xb6, (byte) 0x6a, (byte) 0xfd, (byte) 0x47,
        (byte) 0x50, (byte) 0x3b, (byte) 0x16, (byte) 0x9b, (byte) 0xf3, (byte) 0x6e, (byte) 0x3b, (byte) 0x51,
        (byte) 0x62, (byte) 0x51, (byte) 0x5b, (byte) 0x71, (byte) 0x5f, (byte) 0xda, (byte) 0x83, (byte) 0xde,
        (byte) 0xaf, (byte) 0x2c, (byte) 0x58, (byte) 0xae, (byte) 0xb9, (byte) 0xab, (byte) 0xfb, (byte) 0x30,
        (byte) 0x97, (byte) 0xc3, (byte) 0xcc, (byte) 0x9d, (byte) 0xd9, (byte) 0xdb, (byte) 0xe5, (byte) 0xef,
        (byte) 0x29, (byte) 0x6c, (byte) 0x17, (byte) 0x61, (byte) 0x39, (byte) 0x02, (byte) 0x8e, (byte) 0x8a,
        (byte) 0x67, (byte) 0x1e, (byte) 0x63, (byte) 0x05, (byte) 0x6d, (byte) 0x45, (byte) 0xf4, (byte) 0x01,
        (byte) 0x88, (byte) 0xd2, (byte) 0xc4, (byte) 0x13, (byte) 0x34, (byte) 0x90, (byte) 0x84, (byte) 0x5d,
        (byte) 0xe5, (byte) 0x2c, (byte) 0x25, (byte) 0x34, (byte) 0xe9, (byte) 0xc6, (byte) 0xb2, (byte) 0x47,
        (byte) 0x8c, (byte) 0x07, (byte) 0xbd, (byte) 0xae, (byte) 0x92, (byte) 0x88, (byte) 0x23, (byte) 0xb6,
        (byte) 0x2d, (byte) 0x06, (byte) 0x6c, (byte) 0x77, (byte) 0x70, (byte) 0xf9, (byte) 0xf6, (byte) 0x3f,
        (byte) 0x3d, (byte) 0xba, (byte) 0x24, (byte) 0x7f, (byte) 0x53, (byte) 0x08, (byte) 0x44, (byte) 0x74,
        (byte) 0x7b, (byte) 0xe7, (byte) 0xaa, (byte) 0xa8, (byte) 0x5d, (byte) 0x85, (byte) 0x3b, (byte) 0x8b,
        (byte) 0xd2, (byte) 0x44, (byte) 0xac, (byte) 0xec, (byte) 0x3d, (byte) 0xe3, (byte) 0xc8, (byte) 0x9a,
        (byte) 0xb4, (byte) 0x64, (byte) 0x53, (byte) 0xab, (byte) 0x4d, (byte) 0x24, (byte) 0xc3, (byte) 0xac,
        (byte) 0x69,
    });

    private static final BigInteger RSA_2048_privateExponent = new BigInteger(new byte[] {
        (byte) 0x37, (byte) 0x78, (byte) 0x47, (byte) 0x76, (byte) 0xa5, (byte) 0xf1, (byte) 0x76, (byte) 0x98,
        (byte) 0xf5, (byte) 0xac, (byte) 0x96, (byte) 0x0d, (byte) 0xfb, (byte) 0x83, (byte) 0xa1, (byte) 0xb6,
        (byte) 0x75, (byte) 0x64, (byte) 0xe6, (byte) 0x48, (byte) 0xbd, (byte) 0x05, (byte) 0x97, (byte) 0xcf,
        (byte) 0x8a, (byte) 0xb8, (byte) 0x08, (byte) 0x71, (byte) 0x86, (byte) 0xf2, (byte) 0x66, (byte) 0x9c,
        (byte) 0x27, (byte) 0xa9, (byte) 0xec, (byte) 0xbd, (byte) 0xd4, (byte) 0x80, (byte) 0xf0, (byte) 0x19,
        (byte) 0x7a, (byte) 0x80, (byte) 0xd0, (byte) 0x73, (byte) 0x09, (byte) 0xe6, (byte) 0xc6, (byte) 0xa9,
        (byte) 0x6f, (byte) 0x92, (byte) 0x53, (byte) 0x31, (byte) 0xe5, (byte) 0x7f, (byte) 0x8b, (byte) 0x4a,
        (byte) 0xc6, (byte) 0xf4, (byte) 0xd4, (byte) 0x5e, (byte) 0xda, (byte) 0x45, (byte) 0xa2, (byte) 0x32,
        (byte) 0x69, (byte) 0xc0, (byte) 0x9f, (byte) 0xc4, (byte) 0x28, (byte) 0xc0, (byte) 0x7a, (byte) 0x4e,
        (byte) 0x6e, (byte) 0xdf, (byte) 0x73, (byte) 0x8a, (byte) 0x15, (byte) 0xde, (byte) 0xc9, (byte) 0x7f,
        (byte) 0xab, (byte) 0xd2, (byte) 0xf2, (byte) 0xbb, (byte) 0x47, (byte) 0xa1, (byte) 0x4f, (byte) 0x20,
        (byte) 0xea, (byte) 0x72, (byte) 0xfc, (byte) 0xfe, (byte) 0x4c, (byte) 0x36, (byte) 0xe0, (byte) 0x1a,
        (byte) 0xda, (byte) 0x77, (byte) 0xbd, (byte) 0x13, (byte) 0x7c, (byte) 0xd8, (byte) 0xd4, (byte) 0xda,
        (byte) 0x10, (byte) 0xbb, (byte) 0x16, (byte) 0x2e, (byte) 0x94, (byte) 0xa4, (byte) 0x66, (byte) 0x29,
        (byte) 0x71, (byte) 0xf1, (byte) 0x75, (byte) 0xf9, (byte) 0x85, (byte) 0xfa, (byte) 0x18, (byte) 0x8f,
        (byte) 0x05, (byte) 0x6c, (byte) 0xb9, (byte) 0x7e, (byte) 0xe2, (byte) 0x81, (byte) 0x6f, (byte) 0x43,
        (byte) 0xab, (byte) 0x9d, (byte) 0x37, (byte) 0x47, (byte) 0x61, (byte) 0x24, (byte) 0x86, (byte) 0xcd,
        (byte) 0xa8, (byte) 0xc1, (byte) 0x61, (byte) 0x96, (byte) 0xc3, (byte) 0x08, (byte) 0x18, (byte) 0xa9,
        (byte) 0x95, (byte) 0xec, (byte) 0x85, (byte) 0xd3, (byte) 0x84, (byte) 0x67, (byte) 0x79, (byte) 0x12,
        (byte) 0x67, (byte) 0xb3, (byte) 0xbf, (byte) 0x21, (byte) 0xf2, (byte) 0x73, (byte) 0x71, (byte) 0x0a,
        (byte) 0x69, (byte) 0x25, (byte) 0x86, (byte) 0x25, (byte) 0x76, (byte) 0x84, (byte) 0x1c, (byte) 0x5b,
        (byte) 0x67, (byte) 0x12, (byte) 0xc1, (byte) 0x2d, (byte) 0x4b, (byte) 0xd2, (byte) 0x0a, (byte) 0x2f,
        (byte) 0x32, (byte) 0x99, (byte) 0xad, (byte) 0xb7, (byte) 0xc1, (byte) 0x35, (byte) 0xda, (byte) 0x5e,
        (byte) 0x95, (byte) 0x15, (byte) 0xab, (byte) 0xda, (byte) 0x76, (byte) 0xe7, (byte) 0xca, (byte) 0xf2,
        (byte) 0xa3, (byte) 0xbe, (byte) 0x80, (byte) 0x55, (byte) 0x1d, (byte) 0x07, (byte) 0x3b, (byte) 0x78,
        (byte) 0xbf, (byte) 0x11, (byte) 0x62, (byte) 0xc4, (byte) 0x8a, (byte) 0xd2, (byte) 0xb7, (byte) 0xf4,
        (byte) 0x74, (byte) 0x3a, (byte) 0x02, (byte) 0x38, (byte) 0xee, (byte) 0x4d, (byte) 0x25, (byte) 0x2f,
        (byte) 0x7d, (byte) 0x5e, (byte) 0x7e, (byte) 0x65, (byte) 0x33, (byte) 0xcc, (byte) 0xae, (byte) 0x64,
        (byte) 0xcc, (byte) 0xb3, (byte) 0x93, (byte) 0x60, (byte) 0x07, (byte) 0x5a, (byte) 0x2f, (byte) 0xd1,
        (byte) 0xe0, (byte) 0x34, (byte) 0xec, (byte) 0x3a, (byte) 0xe5, (byte) 0xce, (byte) 0x9c, (byte) 0x40,
        (byte) 0x8c, (byte) 0xcb, (byte) 0xf0, (byte) 0xe2, (byte) 0x5e, (byte) 0x41, (byte) 0x14, (byte) 0x02,
        (byte) 0x16, (byte) 0x87, (byte) 0xb3, (byte) 0xdd, (byte) 0x47, (byte) 0x54, (byte) 0xae, (byte) 0x81,
    });

    private static final BigInteger RSA_2048_publicExponent = new BigInteger(new byte[] {
        (byte) 0x01, (byte) 0x00, (byte) 0x01,
    });

    private static final BigInteger RSA_2048_primeP = new BigInteger(new byte[] {
        (byte) 0x00, (byte) 0xf5, (byte) 0x41, (byte) 0x88, (byte) 0x4b, (byte) 0xc3, (byte) 0x73, (byte) 0x7b,
        (byte) 0x29, (byte) 0x22, (byte) 0xd4, (byte) 0x11, (byte) 0x9e, (byte) 0xf4, (byte) 0x5e, (byte) 0x2d,
        (byte) 0xee, (byte) 0x2c, (byte) 0xd4, (byte) 0xcb, (byte) 0xb7, (byte) 0x5f, (byte) 0x45, (byte) 0x50,
        (byte) 0x5a, (byte) 0x15, (byte) 0x7a, (byte) 0xa5, (byte) 0x00, (byte) 0x9f, (byte) 0x99, (byte) 0xc7,
        (byte) 0x3a, (byte) 0x2d, (byte) 0xf0, (byte) 0x72, (byte) 0x4a, (byte) 0xc4, (byte) 0x60, (byte) 0x24,
        (byte) 0x30, (byte) 0x63, (byte) 0x32, (byte) 0xea, (byte) 0x89, (byte) 0x81, (byte) 0x77, (byte) 0x63,
        (byte) 0x45, (byte) 0x46, (byte) 0x5d, (byte) 0xc6, (byte) 0xdf, (byte) 0x1e, (byte) 0x0a, (byte) 0x6f,
        (byte) 0x14, (byte) 0x0a, (byte) 0xff, (byte) 0x3b, (byte) 0x73, (byte) 0x96, (byte) 0xe6, (byte) 0xa8,
        (byte) 0x99, (byte) 0x4a, (byte) 0xc5, (byte) 0xda, (byte) 0xa9, (byte) 0x68, (byte) 0x73, (byte) 0x47,
        (byte) 0x2f, (byte) 0xe3, (byte) 0x77, (byte) 0x49, (byte) 0xd1, (byte) 0x4e, (byte) 0xb3, (byte) 0xe0,
        (byte) 0x75, (byte) 0xe6, (byte) 0x29, (byte) 0xdb, (byte) 0xeb, (byte) 0x35, (byte) 0x83, (byte) 0x33,
        (byte) 0x8a, (byte) 0x6f, (byte) 0x36, (byte) 0x49, (byte) 0xd0, (byte) 0xa2, (byte) 0x65, (byte) 0x4a,
        (byte) 0x7a, (byte) 0x42, (byte) 0xfd, (byte) 0x9a, (byte) 0xb6, (byte) 0xbf, (byte) 0xa4, (byte) 0xac,
        (byte) 0x4d, (byte) 0x48, (byte) 0x1d, (byte) 0x39, (byte) 0x0b, (byte) 0xb2, (byte) 0x29, (byte) 0xb0,
        (byte) 0x64, (byte) 0xbd, (byte) 0xc3, (byte) 0x11, (byte) 0xcc, (byte) 0x1b, (byte) 0xe1, (byte) 0xb6,
        (byte) 0x31, (byte) 0x89, (byte) 0xda, (byte) 0x7c, (byte) 0x40, (byte) 0xcd, (byte) 0xec, (byte) 0xf2,
        (byte) 0xb1,
    });

    private static final BigInteger RSA_2048_primeQ = new BigInteger(new byte[] {
        (byte) 0x00, (byte) 0xea, (byte) 0x1a, (byte) 0x74, (byte) 0x2d, (byte) 0xdb, (byte) 0x88, (byte) 0x1c,
        (byte) 0xed, (byte) 0xb7, (byte) 0x28, (byte) 0x8c, (byte) 0x87, (byte) 0xe3, (byte) 0x8d, (byte) 0x86,
        (byte) 0x8d, (byte) 0xd7, (byte) 0xa4, (byte) 0x09, (byte) 0xd1, (byte) 0x5a, (byte) 0x43, (byte) 0xf4,
        (byte) 0x45, (byte) 0xd5, (byte) 0x37, (byte) 0x7a, (byte) 0x0b, (byte) 0x57, (byte) 0x31, (byte) 0xdd,
        (byte) 0xbf, (byte) 0xca, (byte) 0x2d, (byte) 0xaf, (byte) 0x28, (byte) 0xa8, (byte) 0xe1, (byte) 0x3c,
        (byte) 0xd5, (byte) 0xc0, (byte) 0xaf, (byte) 0xce, (byte) 0xc3, (byte) 0x34, (byte) 0x7d, (byte) 0x74,
        (byte) 0xa3, (byte) 0x9e, (byte) 0x23, (byte) 0x5a, (byte) 0x3c, (byte) 0xd9, (byte) 0x63, (byte) 0x3f,
        (byte) 0x27, (byte) 0x4d, (byte) 0xe2, (byte) 0xb9, (byte) 0x4f, (byte) 0x92, (byte) 0xdf, (byte) 0x43,
        (byte) 0x83, (byte) 0x39, (byte) 0x11, (byte) 0xd9, (byte) 0xe9, (byte) 0xf1, (byte) 0xcf, (byte) 0x58,
        (byte) 0xf2, (byte) 0x7d, (byte) 0xe2, (byte) 0xe0, (byte) 0x8f, (byte) 0xf4, (byte) 0x59, (byte) 0x64,
        (byte) 0xc7, (byte) 0x20, (byte) 0xd3, (byte) 0xec, (byte) 0x21, (byte) 0x39, (byte) 0xdc, (byte) 0x7c,
        (byte) 0xaf, (byte) 0xc9, (byte) 0x12, (byte) 0x95, (byte) 0x3c, (byte) 0xde, (byte) 0xcb, (byte) 0x2f,
        (byte) 0x35, (byte) 0x5a, (byte) 0x2e, (byte) 0x2c, (byte) 0x35, (byte) 0xa5, (byte) 0x0f, (byte) 0xad,
        (byte) 0x75, (byte) 0x4c, (byte) 0xb3, (byte) 0xb2, (byte) 0x31, (byte) 0x66, (byte) 0x42, (byte) 0x4b,
        (byte) 0xa3, (byte) 0xb6, (byte) 0xe3, (byte) 0x11, (byte) 0x2a, (byte) 0x2b, (byte) 0x89, (byte) 0x8c,
        (byte) 0x38, (byte) 0xc5, (byte) 0xc1, (byte) 0x5e, (byte) 0xdb, (byte) 0x23, (byte) 0x86, (byte) 0x93,
        (byte) 0x39,
    });

    /* Test data is: "Android.\n" */
    private static final byte[] Vector1Data = new byte[] {
        (byte) 0x41, (byte) 0x6e, (byte) 0x64, (byte) 0x72, (byte) 0x6f, (byte) 0x69, (byte) 0x64, (byte) 0x2e,
        (byte) 0x0a,
    };

    private static final byte[] SHA1withRSA_Vector1Signature = {
        (byte) 0x6d, (byte) 0x5b, (byte) 0xff, (byte) 0x68, (byte) 0xda, (byte) 0x18, (byte) 0x98, (byte) 0x72,
        (byte) 0x5c, (byte) 0x1f, (byte) 0x46, (byte) 0x51, (byte) 0x77, (byte) 0x15, (byte) 0x11, (byte) 0xcb,
        (byte) 0xe0, (byte) 0xb9, (byte) 0x3b, (byte) 0x7d, (byte) 0xf5, (byte) 0x96, (byte) 0x98, (byte) 0x24,
        (byte) 0x85, (byte) 0x9d, (byte) 0x3e, (byte) 0xed, (byte) 0x9b, (byte) 0xb2, (byte) 0x8a, (byte) 0x91,
        (byte) 0xfb, (byte) 0xf6, (byte) 0x85, (byte) 0x64, (byte) 0x74, (byte) 0x18, (byte) 0xb5, (byte) 0x1c,
        (byte) 0xb3, (byte) 0x8d, (byte) 0x99, (byte) 0x0d, (byte) 0xdf, (byte) 0xaa, (byte) 0xa6, (byte) 0xa1,
        (byte) 0xc3, (byte) 0xb6, (byte) 0x25, (byte) 0xb3, (byte) 0x06, (byte) 0xe0, (byte) 0xef, (byte) 0x28,
        (byte) 0xb0, (byte) 0x4d, (byte) 0x50, (byte) 0xc7, (byte) 0x75, (byte) 0x39, (byte) 0xb9, (byte) 0x2c,
        (byte) 0x47, (byte) 0xb5, (byte) 0xe2, (byte) 0x96, (byte) 0xf8, (byte) 0xf6, (byte) 0xcb, (byte) 0xa0,
        (byte) 0x58, (byte) 0xc9, (byte) 0x3e, (byte) 0xd5, (byte) 0xfc, (byte) 0x26, (byte) 0xd9, (byte) 0x55,
        (byte) 0x73, (byte) 0x39, (byte) 0x75, (byte) 0xb3, (byte) 0xb0, (byte) 0x0a, (byte) 0x5f, (byte) 0x5e,
        (byte) 0x3b, (byte) 0x4a, (byte) 0x2e, (byte) 0xb1, (byte) 0x0e, (byte) 0x7d, (byte) 0xe5, (byte) 0xcc,
        (byte) 0x04, (byte) 0x2c, (byte) 0xd1, (byte) 0x0a, (byte) 0x32, (byte) 0xaa, (byte) 0xd9, (byte) 0x8d,
        (byte) 0x1f, (byte) 0xcb, (byte) 0xe3, (byte) 0x7f, (byte) 0x63, (byte) 0x12, (byte) 0xb1, (byte) 0x98,
        (byte) 0x46, (byte) 0x46, (byte) 0x07, (byte) 0xd9, (byte) 0x49, (byte) 0xd2, (byte) 0xbf, (byte) 0xb5,
        (byte) 0xbc, (byte) 0xbb, (byte) 0xfd, (byte) 0x1c, (byte) 0xd7, (byte) 0x11, (byte) 0x94, (byte) 0xaa,
        (byte) 0x5f, (byte) 0x7b, (byte) 0xb2, (byte) 0x0c, (byte) 0x5d, (byte) 0x94, (byte) 0x53, (byte) 0x5e,
        (byte) 0x81, (byte) 0x5c, (byte) 0xbb, (byte) 0x1d, (byte) 0x4f, (byte) 0x30, (byte) 0xcd, (byte) 0xf8,
        (byte) 0xd7, (byte) 0xa5, (byte) 0xfa, (byte) 0x5e, (byte) 0xe0, (byte) 0x19, (byte) 0x3f, (byte) 0xa4,
        (byte) 0xaa, (byte) 0x56, (byte) 0x4e, (byte) 0xec, (byte) 0xeb, (byte) 0xee, (byte) 0xa2, (byte) 0x6c,
        (byte) 0xc9, (byte) 0x4f, (byte) 0xc2, (byte) 0xcc, (byte) 0x2a, (byte) 0xbc, (byte) 0x5b, (byte) 0x09,
        (byte) 0x10, (byte) 0x73, (byte) 0x61, (byte) 0x0c, (byte) 0x04, (byte) 0xb6, (byte) 0xb7, (byte) 0x2c,
        (byte) 0x37, (byte) 0xd2, (byte) 0xca, (byte) 0x2d, (byte) 0x54, (byte) 0xf2, (byte) 0xf7, (byte) 0x77,
        (byte) 0xe1, (byte) 0xba, (byte) 0x9f, (byte) 0x29, (byte) 0x07, (byte) 0xa2, (byte) 0x74, (byte) 0xc6,
        (byte) 0xe9, (byte) 0x1e, (byte) 0xde, (byte) 0xd7, (byte) 0x9c, (byte) 0x4b, (byte) 0xb7, (byte) 0x66,
        (byte) 0x52, (byte) 0xe8, (byte) 0xac, (byte) 0xf6, (byte) 0x76, (byte) 0xab, (byte) 0x16, (byte) 0x82,
        (byte) 0x96, (byte) 0x87, (byte) 0x40, (byte) 0x0f, (byte) 0xad, (byte) 0x2d, (byte) 0x46, (byte) 0xa6,
        (byte) 0x28, (byte) 0x04, (byte) 0x13, (byte) 0xc2, (byte) 0xce, (byte) 0x50, (byte) 0x56, (byte) 0x6d,
        (byte) 0xbe, (byte) 0x0c, (byte) 0x91, (byte) 0xd0, (byte) 0x8e, (byte) 0x80, (byte) 0x9e, (byte) 0x91,
        (byte) 0x8f, (byte) 0x62, (byte) 0xb3, (byte) 0x57, (byte) 0xd6, (byte) 0xae, (byte) 0x53, (byte) 0x91,
        (byte) 0x83, (byte) 0xe9, (byte) 0x38, (byte) 0x77, (byte) 0x8f, (byte) 0x20, (byte) 0xdd, (byte) 0x13,
        (byte) 0x7d, (byte) 0x15, (byte) 0x44, (byte) 0x7e, (byte) 0xb5, (byte) 0x00, (byte) 0xd6, (byte) 0x45,
    };

    private static final byte[] Vector2Data = new byte[] {
        (byte) 0x54, (byte) 0x68, (byte) 0x69, (byte) 0x73, (byte) 0x20, (byte) 0x69, (byte) 0x73, (byte) 0x20,
        (byte) 0x61, (byte) 0x20, (byte) 0x73, (byte) 0x69, (byte) 0x67, (byte) 0x6e, (byte) 0x65, (byte) 0x64,
        (byte) 0x20, (byte) 0x6d, (byte) 0x65, (byte) 0x73, (byte) 0x73, (byte) 0x61, (byte) 0x67, (byte) 0x65,
        (byte) 0x20, (byte) 0x66, (byte) 0x72, (byte) 0x6f, (byte) 0x6d, (byte) 0x20, (byte) 0x4b, (byte) 0x65,
        (byte) 0x6e, (byte) 0x6e, (byte) 0x79, (byte) 0x20, (byte) 0x52, (byte) 0x6f, (byte) 0x6f, (byte) 0x74,
        (byte) 0x2e, (byte) 0x0a,
    };

    private static final byte[] SHA1withRSA_Vector2Signature = new byte[] {
        (byte) 0x2e, (byte) 0xa6, (byte) 0x33, (byte) 0xd1, (byte) 0x9d, (byte) 0xfc, (byte) 0x4e, (byte) 0x27,
        (byte) 0xb3, (byte) 0xa8, (byte) 0x9a, (byte) 0xf2, (byte) 0x48, (byte) 0x62, (byte) 0x15, (byte) 0xa2,
        (byte) 0xce, (byte) 0x5f, (byte) 0x2b, (byte) 0x0e, (byte) 0xc5, (byte) 0x26, (byte) 0xba, (byte) 0xd9,
        (byte) 0x0f, (byte) 0x60, (byte) 0xeb, (byte) 0xf0, (byte) 0xd5, (byte) 0x5c, (byte) 0x6b, (byte) 0x23,
        (byte) 0x11, (byte) 0x95, (byte) 0xa4, (byte) 0xbd, (byte) 0x11, (byte) 0x68, (byte) 0xe7, (byte) 0x3a,
        (byte) 0x37, (byte) 0x3d, (byte) 0x79, (byte) 0xb8, (byte) 0x4f, (byte) 0xe9, (byte) 0xa1, (byte) 0x88,
        (byte) 0xfb, (byte) 0xa9, (byte) 0x8b, (byte) 0x34, (byte) 0xa1, (byte) 0xe0, (byte) 0xca, (byte) 0x11,
        (byte) 0xdd, (byte) 0xd0, (byte) 0x83, (byte) 0x7f, (byte) 0xc1, (byte) 0x0b, (byte) 0x16, (byte) 0x61,
        (byte) 0xac, (byte) 0x09, (byte) 0xa2, (byte) 0xdd, (byte) 0x40, (byte) 0x5b, (byte) 0x8c, (byte) 0x7a,
        (byte) 0xb2, (byte) 0xb4, (byte) 0x02, (byte) 0x7c, (byte) 0xd4, (byte) 0x9a, (byte) 0xe6, (byte) 0xa5,
        (byte) 0x1a, (byte) 0x27, (byte) 0x77, (byte) 0x70, (byte) 0xe3, (byte) 0xe3, (byte) 0x71, (byte) 0xc7,
        (byte) 0x59, (byte) 0xc7, (byte) 0x9f, (byte) 0xb8, (byte) 0xef, (byte) 0xe7, (byte) 0x15, (byte) 0x02,
        (byte) 0x0d, (byte) 0x70, (byte) 0xdc, (byte) 0x2c, (byte) 0xe9, (byte) 0xf7, (byte) 0x63, (byte) 0x2a,
        (byte) 0xb5, (byte) 0xee, (byte) 0x9f, (byte) 0x29, (byte) 0x56, (byte) 0x86, (byte) 0x99, (byte) 0xb3,
        (byte) 0x0f, (byte) 0xe5, (byte) 0x1f, (byte) 0x76, (byte) 0x22, (byte) 0x3b, (byte) 0x7f, (byte) 0xa9,
        (byte) 0x9e, (byte) 0xd4, (byte) 0xc4, (byte) 0x83, (byte) 0x5d, (byte) 0x57, (byte) 0xcc, (byte) 0x37,
        (byte) 0xcb, (byte) 0x9a, (byte) 0x9e, (byte) 0x73, (byte) 0x44, (byte) 0x93, (byte) 0xb4, (byte) 0xf1,
        (byte) 0x6b, (byte) 0x98, (byte) 0xa0, (byte) 0x57, (byte) 0xbb, (byte) 0x5e, (byte) 0x8f, (byte) 0x89,
        (byte) 0x5b, (byte) 0x97, (byte) 0x26, (byte) 0xe4, (byte) 0xd0, (byte) 0x51, (byte) 0x0a, (byte) 0x5a,
        (byte) 0xb7, (byte) 0x12, (byte) 0x1a, (byte) 0x6d, (byte) 0xb0, (byte) 0x79, (byte) 0x30, (byte) 0x51,
        (byte) 0x83, (byte) 0x2e, (byte) 0xe2, (byte) 0x7a, (byte) 0x67, (byte) 0x66, (byte) 0xd3, (byte) 0x95,
        (byte) 0xca, (byte) 0xfc, (byte) 0xcb, (byte) 0x92, (byte) 0x79, (byte) 0x32, (byte) 0x26, (byte) 0x86,
        (byte) 0xe1, (byte) 0x0d, (byte) 0xd8, (byte) 0x19, (byte) 0xfa, (byte) 0x65, (byte) 0x37, (byte) 0xc9,
        (byte) 0x4c, (byte) 0x2a, (byte) 0xe1, (byte) 0x42, (byte) 0xc7, (byte) 0xd4, (byte) 0xb7, (byte) 0xeb,
        (byte) 0x1f, (byte) 0xc3, (byte) 0x53, (byte) 0x64, (byte) 0x6f, (byte) 0x2b, (byte) 0x78, (byte) 0x18,
        (byte) 0x03, (byte) 0xda, (byte) 0x8d, (byte) 0x62, (byte) 0x24, (byte) 0x70, (byte) 0xab, (byte) 0xe6,
        (byte) 0x16, (byte) 0x13, (byte) 0x24, (byte) 0x6b, (byte) 0x5f, (byte) 0xd3, (byte) 0xec, (byte) 0xc1,
        (byte) 0x58, (byte) 0x64, (byte) 0xbd, (byte) 0x30, (byte) 0x98, (byte) 0x5e, (byte) 0x33, (byte) 0xce,
        (byte) 0x87, (byte) 0x64, (byte) 0x14, (byte) 0x07, (byte) 0x85, (byte) 0x43, (byte) 0x3e, (byte) 0x9f,
        (byte) 0x27, (byte) 0x9f, (byte) 0x63, (byte) 0x66, (byte) 0x9d, (byte) 0x26, (byte) 0x19, (byte) 0xc0,
        (byte) 0x02, (byte) 0x08, (byte) 0x15, (byte) 0xcb, (byte) 0xb4, (byte) 0xaa, (byte) 0x4a, (byte) 0xc8,
        (byte) 0xc0, (byte) 0x09, (byte) 0x15, (byte) 0x7d, (byte) 0x8a, (byte) 0x21, (byte) 0xbc, (byte) 0xa3,
    };

    private static final byte[] SHA256withRSA_Vector2Signature = new byte[] {
        (byte) 0x18, (byte) 0x6e, (byte) 0x31, (byte) 0x1f, (byte) 0x1d, (byte) 0x44, (byte) 0x09, (byte) 0x3e,
        (byte) 0xa0, (byte) 0xc4, (byte) 0x3d, (byte) 0xb4, (byte) 0x1b, (byte) 0xf2, (byte) 0xd8, (byte) 0xa4,
        (byte) 0x59, (byte) 0xab, (byte) 0xb5, (byte) 0x37, (byte) 0x28, (byte) 0xb8, (byte) 0x94, (byte) 0x6b,
        (byte) 0x6f, (byte) 0x13, (byte) 0x54, (byte) 0xff, (byte) 0xac, (byte) 0x15, (byte) 0x84, (byte) 0xd0,
        (byte) 0xc9, (byte) 0x15, (byte) 0x5b, (byte) 0x69, (byte) 0x05, (byte) 0xf1, (byte) 0x44, (byte) 0xfd,
        (byte) 0xde, (byte) 0xe8, (byte) 0xb4, (byte) 0x12, (byte) 0x59, (byte) 0x9e, (byte) 0x4c, (byte) 0x0b,
        (byte) 0xd5, (byte) 0x49, (byte) 0x33, (byte) 0x28, (byte) 0xe0, (byte) 0xcb, (byte) 0x87, (byte) 0x85,
        (byte) 0xd8, (byte) 0x18, (byte) 0x6f, (byte) 0xfe, (byte) 0xa2, (byte) 0x23, (byte) 0x82, (byte) 0xf0,
        (byte) 0xe5, (byte) 0x39, (byte) 0x1b, (byte) 0x8c, (byte) 0x93, (byte) 0x11, (byte) 0x49, (byte) 0x72,
        (byte) 0x2a, (byte) 0x5b, (byte) 0x25, (byte) 0xff, (byte) 0x4e, (byte) 0x88, (byte) 0x70, (byte) 0x9d,
        (byte) 0x9d, (byte) 0xff, (byte) 0xe2, (byte) 0xc0, (byte) 0x7e, (byte) 0xc8, (byte) 0x03, (byte) 0x40,
        (byte) 0xbe, (byte) 0x44, (byte) 0x09, (byte) 0xeb, (byte) 0x9e, (byte) 0x8e, (byte) 0x88, (byte) 0xe4,
        (byte) 0x98, (byte) 0x82, (byte) 0x06, (byte) 0xa4, (byte) 0x9d, (byte) 0x63, (byte) 0x88, (byte) 0x65,
        (byte) 0xa3, (byte) 0x8e, (byte) 0x0d, (byte) 0x22, (byte) 0xf3, (byte) 0x33, (byte) 0xf2, (byte) 0x40,
        (byte) 0xe8, (byte) 0x91, (byte) 0x67, (byte) 0x72, (byte) 0x29, (byte) 0x1c, (byte) 0x08, (byte) 0xff,
        (byte) 0x54, (byte) 0xa0, (byte) 0xcc, (byte) 0xad, (byte) 0x84, (byte) 0x88, (byte) 0x4b, (byte) 0x3b,
        (byte) 0xef, (byte) 0xf9, (byte) 0x5e, (byte) 0xb3, (byte) 0x41, (byte) 0x6a, (byte) 0xbd, (byte) 0x94,
        (byte) 0x16, (byte) 0x7d, (byte) 0x9d, (byte) 0x53, (byte) 0x77, (byte) 0xf1, (byte) 0x6a, (byte) 0x95,
        (byte) 0x57, (byte) 0xad, (byte) 0x65, (byte) 0x9d, (byte) 0x75, (byte) 0x95, (byte) 0xf6, (byte) 0x6a,
        (byte) 0xd2, (byte) 0x88, (byte) 0xea, (byte) 0x5b, (byte) 0xa2, (byte) 0x94, (byte) 0x8f, (byte) 0x5e,
        (byte) 0x84, (byte) 0x18, (byte) 0x19, (byte) 0x46, (byte) 0x83, (byte) 0x0b, (byte) 0x6d, (byte) 0x5b,
        (byte) 0xb9, (byte) 0xdb, (byte) 0xa4, (byte) 0xe5, (byte) 0x17, (byte) 0x02, (byte) 0x9e, (byte) 0x11,
        (byte) 0xed, (byte) 0xd9, (byte) 0x7b, (byte) 0x83, (byte) 0x87, (byte) 0x89, (byte) 0xf3, (byte) 0xe4,
        (byte) 0xbf, (byte) 0x0e, (byte) 0xe8, (byte) 0xdc, (byte) 0x55, (byte) 0x9c, (byte) 0xf7, (byte) 0xc9,
        (byte) 0xc3, (byte) 0xe2, (byte) 0x2c, (byte) 0xf7, (byte) 0x8c, (byte) 0xaa, (byte) 0x17, (byte) 0x1f,
        (byte) 0xd1, (byte) 0xc7, (byte) 0x74, (byte) 0xc7, (byte) 0x8e, (byte) 0x1c, (byte) 0x5b, (byte) 0xd2,
        (byte) 0x31, (byte) 0x74, (byte) 0x43, (byte) 0x9a, (byte) 0x52, (byte) 0xbf, (byte) 0x89, (byte) 0xc5,
        (byte) 0xb4, (byte) 0x80, (byte) 0x6a, (byte) 0x9e, (byte) 0x05, (byte) 0xdb, (byte) 0xbb, (byte) 0x07,
        (byte) 0x8c, (byte) 0x08, (byte) 0x61, (byte) 0xba, (byte) 0xa4, (byte) 0xbc, (byte) 0x80, (byte) 0x3a,
        (byte) 0xdd, (byte) 0x3b, (byte) 0x1a, (byte) 0x8c, (byte) 0x21, (byte) 0xd8, (byte) 0xa3, (byte) 0xc0,
        (byte) 0xc7, (byte) 0xd1, (byte) 0x08, (byte) 0xe1, (byte) 0x34, (byte) 0x99, (byte) 0xc0, (byte) 0xcf,
        (byte) 0x80, (byte) 0xff, (byte) 0xfa, (byte) 0x07, (byte) 0xef, (byte) 0x5c, (byte) 0x45, (byte) 0xe5,
    };

    private static final byte[] SHA384withRSA_Vector2Signature = new byte[] {
        (byte) 0xaf, (byte) 0xf7, (byte) 0x7a, (byte) 0xc2, (byte) 0xbb, (byte) 0xb8, (byte) 0xbd, (byte) 0xe3,
        (byte) 0x42, (byte) 0xaa, (byte) 0x16, (byte) 0x8a, (byte) 0x52, (byte) 0x6c, (byte) 0x99, (byte) 0x66,
        (byte) 0x08, (byte) 0xbe, (byte) 0x15, (byte) 0xd9, (byte) 0x7c, (byte) 0x60, (byte) 0x2c, (byte) 0xac,
        (byte) 0x4d, (byte) 0x4c, (byte) 0xf4, (byte) 0xdf, (byte) 0xbc, (byte) 0x16, (byte) 0x58, (byte) 0x0a,
        (byte) 0x4e, (byte) 0xde, (byte) 0x8d, (byte) 0xb3, (byte) 0xbd, (byte) 0x03, (byte) 0x4e, (byte) 0x23,
        (byte) 0x40, (byte) 0xa5, (byte) 0x80, (byte) 0xae, (byte) 0x83, (byte) 0xb4, (byte) 0x0f, (byte) 0x99,
        (byte) 0x44, (byte) 0xc3, (byte) 0x5e, (byte) 0xdb, (byte) 0x59, (byte) 0x1d, (byte) 0xea, (byte) 0x7b,
        (byte) 0x4d, (byte) 0xf3, (byte) 0xd2, (byte) 0xad, (byte) 0xbd, (byte) 0x21, (byte) 0x9f, (byte) 0x8e,
        (byte) 0x87, (byte) 0x8f, (byte) 0x12, (byte) 0x13, (byte) 0x33, (byte) 0xf1, (byte) 0xc0, (byte) 0x9d,
        (byte) 0xe7, (byte) 0xec, (byte) 0x6e, (byte) 0xad, (byte) 0xea, (byte) 0x5d, (byte) 0x69, (byte) 0xbb,
        (byte) 0xab, (byte) 0x5b, (byte) 0xd8, (byte) 0x55, (byte) 0x56, (byte) 0xc8, (byte) 0xda, (byte) 0x81,
        (byte) 0x41, (byte) 0xfb, (byte) 0xd3, (byte) 0x11, (byte) 0x6c, (byte) 0x97, (byte) 0xa7, (byte) 0xc3,
        (byte) 0xf1, (byte) 0x31, (byte) 0xbf, (byte) 0xbe, (byte) 0x3f, (byte) 0xdb, (byte) 0x35, (byte) 0x85,
        (byte) 0xb7, (byte) 0xb0, (byte) 0x75, (byte) 0x7f, (byte) 0xaf, (byte) 0xfb, (byte) 0x65, (byte) 0x61,
        (byte) 0xc7, (byte) 0x0e, (byte) 0x63, (byte) 0xb5, (byte) 0x7d, (byte) 0x95, (byte) 0xe9, (byte) 0x16,
        (byte) 0x9d, (byte) 0x6a, (byte) 0x00, (byte) 0x9f, (byte) 0x5e, (byte) 0xcd, (byte) 0xff, (byte) 0xa6,
        (byte) 0xbc, (byte) 0x71, (byte) 0xf2, (byte) 0x2c, (byte) 0xd3, (byte) 0x68, (byte) 0xb9, (byte) 0x3f,
        (byte) 0xaa, (byte) 0x06, (byte) 0xf1, (byte) 0x9c, (byte) 0x7e, (byte) 0xca, (byte) 0x4a, (byte) 0xfe,
        (byte) 0xb1, (byte) 0x73, (byte) 0x19, (byte) 0x80, (byte) 0x05, (byte) 0xa6, (byte) 0x85, (byte) 0x14,
        (byte) 0xda, (byte) 0x7a, (byte) 0x16, (byte) 0x7a, (byte) 0xc2, (byte) 0x46, (byte) 0x57, (byte) 0xa7,
        (byte) 0xc0, (byte) 0xbf, (byte) 0xcd, (byte) 0xdc, (byte) 0x2f, (byte) 0x64, (byte) 0xf6, (byte) 0x6d,
        (byte) 0xdc, (byte) 0xcb, (byte) 0x5a, (byte) 0x29, (byte) 0x95, (byte) 0x1c, (byte) 0xfe, (byte) 0xf2,
        (byte) 0xda, (byte) 0x7e, (byte) 0xcb, (byte) 0x26, (byte) 0x12, (byte) 0xc6, (byte) 0xb0, (byte) 0xba,
        (byte) 0x84, (byte) 0x9b, (byte) 0x4f, (byte) 0xba, (byte) 0x1b, (byte) 0x78, (byte) 0x25, (byte) 0xb8,
        (byte) 0x8f, (byte) 0x2e, (byte) 0x51, (byte) 0x5f, (byte) 0x9e, (byte) 0xfc, (byte) 0x40, (byte) 0xbc,
        (byte) 0x85, (byte) 0xcd, (byte) 0x86, (byte) 0x7f, (byte) 0x88, (byte) 0xc5, (byte) 0xaa, (byte) 0x2b,
        (byte) 0x78, (byte) 0xb1, (byte) 0x9c, (byte) 0x51, (byte) 0x9a, (byte) 0xe1, (byte) 0xe1, (byte) 0xc0,
        (byte) 0x40, (byte) 0x47, (byte) 0xcb, (byte) 0xa4, (byte) 0xb7, (byte) 0x6c, (byte) 0x31, (byte) 0xf2,
        (byte) 0xc8, (byte) 0x9a, (byte) 0xad, (byte) 0x0b, (byte) 0xd3, (byte) 0xf6, (byte) 0x85, (byte) 0x9a,
        (byte) 0x8f, (byte) 0x4f, (byte) 0xc9, (byte) 0xd8, (byte) 0x33, (byte) 0x7c, (byte) 0x45, (byte) 0x30,
        (byte) 0xea, (byte) 0x17, (byte) 0xd3, (byte) 0xe3, (byte) 0x90, (byte) 0x2c, (byte) 0xda, (byte) 0xde,
        (byte) 0x41, (byte) 0x17, (byte) 0x3f, (byte) 0x08, (byte) 0xb9, (byte) 0x34, (byte) 0xc0, (byte) 0xd1,
    };

    private static final byte[] SHA512withRSA_Vector2Signature = new byte[] {
        (byte) 0x19, (byte) 0xe2, (byte) 0xe5, (byte) 0xf3, (byte) 0x18, (byte) 0x83, (byte) 0xec, (byte) 0xf0,
        (byte) 0xab, (byte) 0x50, (byte) 0x05, (byte) 0x4b, (byte) 0x5f, (byte) 0x22, (byte) 0xfc, (byte) 0x82,
        (byte) 0x6d, (byte) 0xca, (byte) 0xe7, (byte) 0xbe, (byte) 0x23, (byte) 0x94, (byte) 0xfa, (byte) 0xf9,
        (byte) 0xa4, (byte) 0x8a, (byte) 0x95, (byte) 0x4d, (byte) 0x14, (byte) 0x08, (byte) 0x8b, (byte) 0x5e,
        (byte) 0x03, (byte) 0x1b, (byte) 0x74, (byte) 0xde, (byte) 0xc1, (byte) 0x45, (byte) 0x9c, (byte) 0xce,
        (byte) 0x1d, (byte) 0xac, (byte) 0xab, (byte) 0xd3, (byte) 0xa8, (byte) 0xc3, (byte) 0xca, (byte) 0x67,
        (byte) 0x80, (byte) 0xf6, (byte) 0x03, (byte) 0x46, (byte) 0x65, (byte) 0x77, (byte) 0x59, (byte) 0xbb,
        (byte) 0xb8, (byte) 0x83, (byte) 0xee, (byte) 0xc2, (byte) 0x3e, (byte) 0x78, (byte) 0xdd, (byte) 0x89,
        (byte) 0xcd, (byte) 0x9b, (byte) 0x78, (byte) 0x35, (byte) 0xa9, (byte) 0x09, (byte) 0xc8, (byte) 0x77,
        (byte) 0xdd, (byte) 0xd3, (byte) 0xa0, (byte) 0x64, (byte) 0xb0, (byte) 0x74, (byte) 0x48, (byte) 0x51,
        (byte) 0x4f, (byte) 0xa0, (byte) 0xae, (byte) 0x33, (byte) 0xb3, (byte) 0x28, (byte) 0xb0, (byte) 0xa8,
        (byte) 0x78, (byte) 0x8f, (byte) 0xa2, (byte) 0x32, (byte) 0xa6, (byte) 0x0a, (byte) 0xaa, (byte) 0x09,
        (byte) 0xb5, (byte) 0x8d, (byte) 0x4c, (byte) 0x44, (byte) 0x46, (byte) 0xb4, (byte) 0xd2, (byte) 0x06,
        (byte) 0x6b, (byte) 0x8c, (byte) 0x51, (byte) 0x6e, (byte) 0x9c, (byte) 0xfa, (byte) 0x1f, (byte) 0x94,
        (byte) 0x3e, (byte) 0x19, (byte) 0x9c, (byte) 0x63, (byte) 0xfe, (byte) 0xa9, (byte) 0x9a, (byte) 0xe3,
        (byte) 0x6c, (byte) 0x82, (byte) 0x64, (byte) 0x5f, (byte) 0xca, (byte) 0xc2, (byte) 0x8d, (byte) 0x66,
        (byte) 0xbe, (byte) 0x12, (byte) 0x6e, (byte) 0xb6, (byte) 0x35, (byte) 0x6d, (byte) 0xaa, (byte) 0xed,
        (byte) 0x4b, (byte) 0x50, (byte) 0x08, (byte) 0x1c, (byte) 0xbf, (byte) 0x07, (byte) 0x70, (byte) 0x78,
        (byte) 0xc0, (byte) 0xbb, (byte) 0xc5, (byte) 0x8d, (byte) 0x6c, (byte) 0x8d, (byte) 0x35, (byte) 0xff,
        (byte) 0x04, (byte) 0x81, (byte) 0xd8, (byte) 0xf4, (byte) 0xd2, (byte) 0x4a, (byte) 0xc3, (byte) 0x05,
        (byte) 0x23, (byte) 0xcb, (byte) 0xeb, (byte) 0x20, (byte) 0xb1, (byte) 0xd4, (byte) 0x2d, (byte) 0xd8,
        (byte) 0x7a, (byte) 0xd4, (byte) 0x7e, (byte) 0xf6, (byte) 0xa9, (byte) 0xe8, (byte) 0x72, (byte) 0x69,
        (byte) 0xfe, (byte) 0xab, (byte) 0x54, (byte) 0x4d, (byte) 0xd1, (byte) 0xf4, (byte) 0x6b, (byte) 0x83,
        (byte) 0x31, (byte) 0x17, (byte) 0xed, (byte) 0x26, (byte) 0xe9, (byte) 0xd2, (byte) 0x5b, (byte) 0xad,
        (byte) 0x42, (byte) 0x42, (byte) 0xa5, (byte) 0x8f, (byte) 0x98, (byte) 0x7c, (byte) 0x1b, (byte) 0x5c,
        (byte) 0x8e, (byte) 0x88, (byte) 0x56, (byte) 0x20, (byte) 0x8e, (byte) 0x48, (byte) 0xf9, (byte) 0x4d,
        (byte) 0x82, (byte) 0x91, (byte) 0xcb, (byte) 0xc8, (byte) 0x1c, (byte) 0x7c, (byte) 0xa5, (byte) 0x69,
        (byte) 0x1b, (byte) 0x40, (byte) 0xc2, (byte) 0x4c, (byte) 0x25, (byte) 0x16, (byte) 0x4f, (byte) 0xfa,
        (byte) 0x09, (byte) 0xeb, (byte) 0xf5, (byte) 0x6c, (byte) 0x55, (byte) 0x3c, (byte) 0x6e, (byte) 0xf7,
        (byte) 0xc0, (byte) 0xc1, (byte) 0x34, (byte) 0xd1, (byte) 0x53, (byte) 0xa3, (byte) 0x69, (byte) 0x64,
        (byte) 0xee, (byte) 0xf4, (byte) 0xf9, (byte) 0xc7, (byte) 0x96, (byte) 0x60, (byte) 0x84, (byte) 0x87,
        (byte) 0xb4, (byte) 0xc7, (byte) 0x3c, (byte) 0x26, (byte) 0xa7, (byte) 0x3a, (byte) 0xbf, (byte) 0x95,
    };

    private static final byte[] MD5withRSA_Vector2Signature = new byte[] {
        (byte) 0x04, (byte) 0x17, (byte) 0x83, (byte) 0x10, (byte) 0xe2, (byte) 0x6e, (byte) 0xdf, (byte) 0xa9,
        (byte) 0xae, (byte) 0xd2, (byte) 0xdc, (byte) 0x5f, (byte) 0x70, (byte) 0x1d, (byte) 0xaf, (byte) 0x54,
        (byte) 0xc0, (byte) 0x5f, (byte) 0x0b, (byte) 0x2c, (byte) 0xe6, (byte) 0xd0, (byte) 0x00, (byte) 0x18,
        (byte) 0x4c, (byte) 0xf6, (byte) 0x8f, (byte) 0x18, (byte) 0x10, (byte) 0x74, (byte) 0x90, (byte) 0x99,
        (byte) 0xa9, (byte) 0x90, (byte) 0x3c, (byte) 0x5a, (byte) 0x38, (byte) 0xd3, (byte) 0x3d, (byte) 0x48,
        (byte) 0xcf, (byte) 0x31, (byte) 0xaf, (byte) 0x12, (byte) 0x98, (byte) 0xfb, (byte) 0x66, (byte) 0xe8,
        (byte) 0x58, (byte) 0xec, (byte) 0xca, (byte) 0xe1, (byte) 0x42, (byte) 0xf9, (byte) 0x84, (byte) 0x17,
        (byte) 0x6f, (byte) 0x4c, (byte) 0x3e, (byte) 0xc4, (byte) 0x40, (byte) 0xc6, (byte) 0x70, (byte) 0xb0,
        (byte) 0x38, (byte) 0xf3, (byte) 0x47, (byte) 0xeb, (byte) 0x6f, (byte) 0xcb, (byte) 0xea, (byte) 0x21,
        (byte) 0x41, (byte) 0xf3, (byte) 0xa0, (byte) 0x3e, (byte) 0x42, (byte) 0xad, (byte) 0xa5, (byte) 0xad,
        (byte) 0x5d, (byte) 0x2c, (byte) 0x1a, (byte) 0x8e, (byte) 0x3e, (byte) 0xb3, (byte) 0xa5, (byte) 0x78,
        (byte) 0x3d, (byte) 0x56, (byte) 0x09, (byte) 0x93, (byte) 0xc9, (byte) 0x93, (byte) 0xd3, (byte) 0xd2,
        (byte) 0x9a, (byte) 0xc5, (byte) 0xa5, (byte) 0x2e, (byte) 0xb2, (byte) 0xd8, (byte) 0x37, (byte) 0xc7,
        (byte) 0x13, (byte) 0x1a, (byte) 0x0b, (byte) 0xda, (byte) 0x50, (byte) 0x28, (byte) 0x6d, (byte) 0x47,
        (byte) 0x65, (byte) 0x52, (byte) 0xcd, (byte) 0xe7, (byte) 0xec, (byte) 0x57, (byte) 0x00, (byte) 0x41,
        (byte) 0x34, (byte) 0x28, (byte) 0xb9, (byte) 0x8b, (byte) 0x03, (byte) 0x41, (byte) 0xb6, (byte) 0xd5,
        (byte) 0xa8, (byte) 0xef, (byte) 0xd3, (byte) 0xdd, (byte) 0x80, (byte) 0xd5, (byte) 0x69, (byte) 0xe4,
        (byte) 0xf0, (byte) 0x4d, (byte) 0xa4, (byte) 0x7d, (byte) 0x60, (byte) 0x2f, (byte) 0xef, (byte) 0x79,
        (byte) 0x07, (byte) 0x75, (byte) 0xeb, (byte) 0xf7, (byte) 0x4b, (byte) 0x43, (byte) 0x41, (byte) 0xdb,
        (byte) 0x33, (byte) 0xad, (byte) 0x9c, (byte) 0x7b, (byte) 0x78, (byte) 0x83, (byte) 0x34, (byte) 0x77,
        (byte) 0xe4, (byte) 0x80, (byte) 0xbe, (byte) 0xe6, (byte) 0x6f, (byte) 0xdd, (byte) 0xac, (byte) 0xa5,
        (byte) 0x37, (byte) 0xcf, (byte) 0xb5, (byte) 0x44, (byte) 0x11, (byte) 0x77, (byte) 0x96, (byte) 0x45,
        (byte) 0xf9, (byte) 0xae, (byte) 0x48, (byte) 0xa6, (byte) 0xbe, (byte) 0x30, (byte) 0x32, (byte) 0xeb,
        (byte) 0x43, (byte) 0x6f, (byte) 0x66, (byte) 0x39, (byte) 0x57, (byte) 0xf8, (byte) 0xe6, (byte) 0x60,
        (byte) 0x31, (byte) 0xd0, (byte) 0xfc, (byte) 0xcf, (byte) 0x9f, (byte) 0xe5, (byte) 0x3d, (byte) 0xcf,
        (byte) 0xbd, (byte) 0x7b, (byte) 0x13, (byte) 0x20, (byte) 0xce, (byte) 0x11, (byte) 0xfd, (byte) 0xe5,
        (byte) 0xff, (byte) 0x90, (byte) 0x85, (byte) 0xdf, (byte) 0xca, (byte) 0x3d, (byte) 0xd9, (byte) 0x44,
        (byte) 0x16, (byte) 0xc2, (byte) 0x32, (byte) 0x28, (byte) 0xc7, (byte) 0x01, (byte) 0x6d, (byte) 0xea,
        (byte) 0xcb, (byte) 0x0d, (byte) 0x85, (byte) 0x08, (byte) 0x6f, (byte) 0xcb, (byte) 0x41, (byte) 0x6a,
        (byte) 0x3c, (byte) 0x0f, (byte) 0x3d, (byte) 0x38, (byte) 0xb5, (byte) 0x61, (byte) 0xc5, (byte) 0x64,
        (byte) 0x64, (byte) 0x81, (byte) 0x4c, (byte) 0xcd, (byte) 0xd1, (byte) 0x6a, (byte) 0x87, (byte) 0x28,
        (byte) 0x02, (byte) 0xaf, (byte) 0x8f, (byte) 0x59, (byte) 0xe5, (byte) 0x67, (byte) 0x25, (byte) 0x00,
    };

    /*
     * openssl rsautl -raw -sign -inkey rsa.key | recode ../x1 | sed 's/0x/(byte) 0x/g'
     */
    private static final byte[] NONEwithRSA_Vector1Signature = new byte[] {
        (byte) 0x35, (byte) 0x43, (byte) 0x38, (byte) 0x44, (byte) 0xAD, (byte) 0x3F,
        (byte) 0x97, (byte) 0x02, (byte) 0xFB, (byte) 0x59, (byte) 0x1F, (byte) 0x4A,
        (byte) 0x2B, (byte) 0xB9, (byte) 0x06, (byte) 0xEC, (byte) 0x66, (byte) 0xE6,
        (byte) 0xD2, (byte) 0xC5, (byte) 0x8B, (byte) 0x7B, (byte) 0xE3, (byte) 0x18,
        (byte) 0xBF, (byte) 0x07, (byte) 0xD6, (byte) 0x01, (byte) 0xF9, (byte) 0xD9,
        (byte) 0x89, (byte) 0xC4, (byte) 0xDB, (byte) 0x00, (byte) 0x68, (byte) 0xFF,
        (byte) 0x9B, (byte) 0x43, (byte) 0x90, (byte) 0xF2, (byte) 0xDB, (byte) 0x83,
        (byte) 0xF4, (byte) 0x7E, (byte) 0xC6, (byte) 0x81, (byte) 0x01, (byte) 0x3A,
        (byte) 0x0B, (byte) 0xE5, (byte) 0xED, (byte) 0x08, (byte) 0x73, (byte) 0x3E,
        (byte) 0xE1, (byte) 0x3F, (byte) 0xDF, (byte) 0x1F, (byte) 0x07, (byte) 0x6D,
        (byte) 0x22, (byte) 0x8D, (byte) 0xCC, (byte) 0x4E, (byte) 0xE3, (byte) 0x9A,
        (byte) 0xBC, (byte) 0xCC, (byte) 0x8F, (byte) 0x9E, (byte) 0x9B, (byte) 0x02,
        (byte) 0x48, (byte) 0x00, (byte) 0xAC, (byte) 0x9F, (byte) 0xA4, (byte) 0x8F,
        (byte) 0x87, (byte) 0xA1, (byte) 0xA8, (byte) 0xE6, (byte) 0x9D, (byte) 0xCD,
        (byte) 0x8B, (byte) 0x05, (byte) 0xE9, (byte) 0xD2, (byte) 0x05, (byte) 0x8D,
        (byte) 0xC9, (byte) 0x95, (byte) 0x16, (byte) 0xD0, (byte) 0xCD, (byte) 0x43,
        (byte) 0x25, (byte) 0x8A, (byte) 0x11, (byte) 0x46, (byte) 0xD7, (byte) 0x74,
        (byte) 0x4C, (byte) 0xCF, (byte) 0x58, (byte) 0xF9, (byte) 0xA1, (byte) 0x30,
        (byte) 0x84, (byte) 0x52, (byte) 0xC9, (byte) 0x01, (byte) 0x5F, (byte) 0x24,
        (byte) 0x4C, (byte) 0xB1, (byte) 0x9F, (byte) 0x7D, (byte) 0x12, (byte) 0x38,
        (byte) 0x27, (byte) 0x0F, (byte) 0x5E, (byte) 0xFF, (byte) 0xE0, (byte) 0x55,
        (byte) 0x8B, (byte) 0xA3, (byte) 0xAD, (byte) 0x60, (byte) 0x35, (byte) 0x83,
        (byte) 0x58, (byte) 0xAF, (byte) 0x99, (byte) 0xDE, (byte) 0x3F, (byte) 0x5D,
        (byte) 0x80, (byte) 0x80, (byte) 0xFF, (byte) 0x9B, (byte) 0xDE, (byte) 0x5C,
        (byte) 0xAB, (byte) 0x97, (byte) 0x43, (byte) 0x64, (byte) 0xD9, (byte) 0x9F,
        (byte) 0xFB, (byte) 0x67, (byte) 0x65, (byte) 0xA5, (byte) 0x99, (byte) 0xE7,
        (byte) 0xE6, (byte) 0xEB, (byte) 0x05, (byte) 0x95, (byte) 0xFC, (byte) 0x46,
        (byte) 0x28, (byte) 0x4B, (byte) 0xD8, (byte) 0x8C, (byte) 0xF5, (byte) 0x0A,
        (byte) 0xEB, (byte) 0x1F, (byte) 0x30, (byte) 0xEA, (byte) 0xE7, (byte) 0x67,
        (byte) 0x11, (byte) 0x25, (byte) 0xF0, (byte) 0x44, (byte) 0x75, (byte) 0x74,
        (byte) 0x94, (byte) 0x06, (byte) 0x78, (byte) 0xD0, (byte) 0x21, (byte) 0xF4,
        (byte) 0x3F, (byte) 0xC8, (byte) 0xC4, (byte) 0x4A, (byte) 0x57, (byte) 0xBE,
        (byte) 0x02, (byte) 0x3C, (byte) 0x93, (byte) 0xF6, (byte) 0x95, (byte) 0xFB,
        (byte) 0xD1, (byte) 0x77, (byte) 0x8B, (byte) 0x43, (byte) 0xF0, (byte) 0xB9,
        (byte) 0x7D, (byte) 0xE0, (byte) 0x32, (byte) 0xE1, (byte) 0x72, (byte) 0xB5,
        (byte) 0x62, (byte) 0x3F, (byte) 0x86, (byte) 0xC3, (byte) 0xD4, (byte) 0x5F,
        (byte) 0x5E, (byte) 0x54, (byte) 0x1B, (byte) 0x5B, (byte) 0xE6, (byte) 0x74,
        (byte) 0xA1, (byte) 0x0B, (byte) 0xE5, (byte) 0x18, (byte) 0xD2, (byte) 0x4F,
        (byte) 0x93, (byte) 0xF3, (byte) 0x09, (byte) 0x58, (byte) 0xCE, (byte) 0xF0,
        (byte) 0xA3, (byte) 0x61, (byte) 0xE4, (byte) 0x6E, (byte) 0x46, (byte) 0x45,
        (byte) 0x89, (byte) 0x50, (byte) 0xBD, (byte) 0x03, (byte) 0x3F, (byte) 0x38,
        (byte) 0xDA, (byte) 0x5D, (byte) 0xD0, (byte) 0x1B, (byte) 0x1F, (byte) 0xB1,
        (byte) 0xEE, (byte) 0x89, (byte) 0x59, (byte) 0xC5,
    };

    public void testGetCommonInstances_Success() throws Exception {
        assertNotNull(Signature.getInstance("SHA1withRSA"));
        assertNotNull(Signature.getInstance("SHA256withRSA"));
        assertNotNull(Signature.getInstance("SHA384withRSA"));
        assertNotNull(Signature.getInstance("SHA512withRSA"));
        assertNotNull(Signature.getInstance("NONEwithRSA"));
        assertNotNull(Signature.getInstance("MD5withRSA"));
        assertNotNull(Signature.getInstance("SHA1withDSA"));
    }

    public void testVerify_SHA1withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector1Data);

        assertTrue("Signature must match expected signature",
                sig.verify(SHA1withRSA_Vector1Signature));
    }

    public void testVerify_SHA256withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector2Data);

        assertTrue("Signature must match expected signature",
                sig.verify(SHA256withRSA_Vector2Signature));
    }

    public void testVerify_SHA384withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA384withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector2Data);

        assertTrue("Signature must match expected signature",
                sig.verify(SHA384withRSA_Vector2Signature));
    }

    public void testVerify_SHA512withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA512withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector2Data);

        assertTrue("Signature must match expected signature",
                sig.verify(SHA512withRSA_Vector2Signature));
    }

    public void testVerify_MD5withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector2Data);

        assertTrue("Signature must match expected signature",
                sig.verify(MD5withRSA_Vector2Signature));
    }

    public void testVerify_SHA1withRSA_Key_InitSignThenInitVerify_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(privKeySpec);

        Signature sig = Signature.getInstance("SHA1withRSA");

        // Start a signing operation
        sig.initSign(privKey);
        sig.update(Vector2Data);

        // Switch to verify
        sig.initVerify(pubKey);
        sig.update(Vector1Data);

        assertTrue("Signature must match expected signature",
                sig.verify(SHA1withRSA_Vector1Signature));
    }

    public void testVerify_SHA1withRSA_Key_TwoMessages_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(pubKey);

        sig.update(Vector1Data);
        assertTrue("First signature must match expected signature",
                sig.verify(SHA1withRSA_Vector1Signature));

        sig.update(Vector2Data);
        assertTrue("Second signature must match expected signature",
                sig.verify(SHA1withRSA_Vector2Signature));
    }

    public void testVerify_SHA1withRSA_Key_WrongExpectedSignature_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(RSA_2048_modulus, RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(keySpec);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(pubKey);
        sig.update(Vector1Data);

        assertFalse("Signature should fail to verify", sig.verify(SHA1withRSA_Vector2Signature));
    }

    public void testSign_SHA1withRSA_CrtKeyWithPublicExponent_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent, RSA_2048_privateExponent, null, null, null, null, null);

        // The RI fails on this key which is totally unreasonable.
        final PrivateKey privKey;
        try {
            privKey = kf.generatePrivate(keySpec);
        } catch (NullPointerException e) {
            if (StandardNames.IS_RI) {
                return;
            } else {
                fail("Private key should be created");
                return;
            }
        }

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privKey);
        sig.update(Vector1Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, SHA1withRSA_Vector1Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector1Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_SHA1withRSA_CrtKey_NoPrivateExponent_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent, null, RSA_2048_primeP, RSA_2048_primeQ, null, null, null);

        // Failing on this key early is okay.
        final PrivateKey privKey;
        try {
            privKey = kf.generatePrivate(keySpec);
        } catch (NullPointerException e) {
            return;
        } catch (InvalidKeySpecException e) {
            return;
        }

        Signature sig = Signature.getInstance("SHA1withRSA");

        try {
            sig.initSign(privKey);
            fail("Should throw error when private exponent is not available");
        } catch (InvalidKeyException expected) {
        }
    }

    public void testSign_SHA1withRSA_CrtKey_NoModulus_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(null, RSA_2048_publicExponent,
                RSA_2048_privateExponent, RSA_2048_primeP, RSA_2048_primeQ, null, null, null);

        // Failing on this key early is okay.
        final PrivateKey privKey;
        try {
            privKey = kf.generatePrivate(keySpec);
        } catch (NullPointerException e) {
            return;
        } catch (InvalidKeySpecException e) {
            return;
        }

        Signature sig = Signature.getInstance("SHA1withRSA");

        try {
            sig.initSign(privKey);
            fail("Should throw error when modulus is not available");
        } catch (InvalidKeyException expected) {
        }
    }

    public void testSign_SHA1withRSA_Key_EmptyKey_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(null, null);

        // Failing on this key early is okay.
        final PrivateKey privKey;
        try {
            privKey = kf.generatePrivate(keySpec);
        } catch (NullPointerException e) {
            return;
        } catch (InvalidKeySpecException e) {
            return;
        }

        Signature sig = Signature.getInstance("SHA1withRSA");

        try {
            sig.initSign(privKey);
            fail("Should throw error when key is empty");
        } catch (InvalidKeyException expected) {
        }
    }

    public void testSign_SHA1withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privKey);
        sig.update(Vector1Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, SHA1withRSA_Vector1Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector1Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_SHA256withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);

        final PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privKey);
        sig.update(Vector2Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, SHA256withRSA_Vector2Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_SHA384withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("SHA384withRSA");
        sig.initSign(privKey);
        sig.update(Vector2Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, SHA384withRSA_Vector2Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_SHA512withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("SHA512withRSA");
        sig.initSign(privKey);
        sig.update(Vector2Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, SHA512withRSA_Vector2Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_MD5withRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initSign(privKey);
        sig.update(Vector2Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, MD5withRSA_Vector2Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testSign_NONEwithRSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initSign(privKey);
        sig.update(Vector1Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);
        assertTrue("Signature should match expected",
                Arrays.equals(signature, NONEwithRSA_Vector1Signature));

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector1Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testVerify_NONEwithRSA_Key_WrongSignature_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initVerify(pubKey);
        sig.update(Vector1Data);
        assertFalse("Invalid signature must not verify", sig.verify("Invalid".getBytes()));
    }

    public void testSign_NONEwithRSA_Key_DataTooLarge_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initSign(privKey);

        final int oneTooBig = RSA_2048_modulus.bitLength() - 10;
        for (int i = 0; i < oneTooBig; i++) {
            sig.update((byte) i);
        }

        try {
            sig.sign();
            fail("Should throw exception when data is too large");
        } catch (SignatureException expected) {
        }
    }

    public void testSign_NONEwithRSA_Key_DataTooLarge_SingleByte_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(RSA_2048_modulus,
                RSA_2048_privateExponent);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initSign(privKey);

        // This should make it two bytes too big.
        final int oneTooBig = RSA_2048_modulus.bitLength() - 10;
        for (int i = 0; i < oneTooBig; i++) {
            sig.update((byte) i);
        }

        try {
            sig.sign();
            fail("Should throw exception when data is too large");
        } catch (SignatureException expected) {
        }
    }

    public void testVerify_NONEwithRSA_Key_DataTooLarge_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initVerify(pubKey);

        // This should make it one bytes too big.
        final int oneTooBig = RSA_2048_modulus.bitLength() + 1;
        final byte[] vector = new byte[oneTooBig];
        for (int i = 0; i < oneTooBig; i++) {
            vector[i] = (byte) Vector1Data[i % Vector1Data.length];
        }
        sig.update(vector);

        assertFalse("Should not verify when signature is too large",
                sig.verify(NONEwithRSA_Vector1Signature));
    }

    public void testVerify_NONEwithRSA_Key_DataTooLarge_SingleByte_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initVerify(pubKey);

        // This should make it twice as big as it should be.
        final int tooBig = RSA_2048_modulus.bitLength() * 2;
        for (int i = 0; i < tooBig; i++) {
            sig.update((byte) Vector1Data[i % Vector1Data.length]);
        }

        assertFalse("Should not verify when signature is too large",
                sig.verify(NONEwithRSA_Vector1Signature));
    }

    public void testVerify_NONEwithRSA_Key_SignatureTooSmall_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initVerify(pubKey);
        sig.update(Vector1Data);

        assertFalse("Invalid signature should not verify", sig.verify("Invalid sig".getBytes()));
    }

    public void testVerify_NONEwithRSA_Key_SignatureTooLarge_Failure() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(RSA_2048_modulus,
                RSA_2048_publicExponent);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("NONEwithRSA");
        sig.initVerify(pubKey);
        sig.update(Vector1Data);

        byte[] invalidSignature = new byte[NONEwithRSA_Vector1Signature.length * 2];
        System.arraycopy(NONEwithRSA_Vector1Signature, 0, invalidSignature, 0,
                NONEwithRSA_Vector1Signature.length);
        System.arraycopy(NONEwithRSA_Vector1Signature, 0, invalidSignature,
                NONEwithRSA_Vector1Signature.length, NONEwithRSA_Vector1Signature.length);

        try {
            sig.verify(invalidSignature);
            fail("Should throw exception when signature is too large");
        } catch (SignatureException expected) {
        }
    }

    /*
     * These tests were generated with this DSA private key:
     *
     * -----BEGIN DSA PRIVATE KEY-----
     * MIIBugIBAAKBgQCeYcKJ73epThNnZB8JAf4kE1Pgt5CoTnb+iYJ/esU8TgwgVTCV
     * QoXhQH0njwcN6NyZ77MHlDTWfP+cvmnT60Q3UO9J+OJb2NEQhJfq46UcwE5pynA9
     * eLkW5f5hXYpasyxhtgE70AF8Mo3h82kOi1jGzwCU+EkqS+raAP9L0L5AIwIVAL/u
     * qg8SNFBy+GAT2PFBARClL1dfAoGAd9R6EsyBfn7rOvvmhm1aEB2tqU+5A10hGuQw
     * lXWOzV7RvQpF7uf3a2UCYNAurz28B90rjjPAk4DZK6dxV3a8jrng1/QjjUEal08s
     * G9VLZuj60lANF6s0MT2kiNiOqKduFwO3D2h8ZHuSuGPkmmcYgSfUCxNI031O9qiP
     * VhctCFECgYAz7i1DhjRGUkCdYQd5tVaI42lhXOV71MTYPbuFOIxTL/hny7Z0PZWR
     * A1blmYE6vrArDEhzpmRvDJZSIMzMfJjUIGu1KO73zpo9siK0xY0/sw5r3QC9txP2
     * 2Mv3BUIl5TLrs9outQJ0VMwldY2fElgCLWcSVkH44qZwWir1cq+cIwIUEGPDardb
     * pNvWlWgTDD6a6ZTby+M=
     * -----END DSA PRIVATE KEY-----
     *
     */

    private static final BigInteger DSA_priv = new BigInteger(new byte[] {
        (byte) 0x10, (byte) 0x63, (byte) 0xc3, (byte) 0x6a, (byte) 0xb7, (byte) 0x5b, (byte) 0xa4, (byte) 0xdb,
        (byte) 0xd6, (byte) 0x95, (byte) 0x68, (byte) 0x13, (byte) 0x0c, (byte) 0x3e, (byte) 0x9a, (byte) 0xe9,
        (byte) 0x94, (byte) 0xdb, (byte) 0xcb, (byte) 0xe3,
    });

    private static final BigInteger DSA_pub = new BigInteger(new byte[] {
        (byte) 0x33, (byte) 0xee, (byte) 0x2d, (byte) 0x43, (byte) 0x86, (byte) 0x34, (byte) 0x46, (byte) 0x52,
        (byte) 0x40, (byte) 0x9d, (byte) 0x61, (byte) 0x07, (byte) 0x79, (byte) 0xb5, (byte) 0x56, (byte) 0x88,
        (byte) 0xe3, (byte) 0x69, (byte) 0x61, (byte) 0x5c, (byte) 0xe5, (byte) 0x7b, (byte) 0xd4, (byte) 0xc4,
        (byte) 0xd8, (byte) 0x3d, (byte) 0xbb, (byte) 0x85, (byte) 0x38, (byte) 0x8c, (byte) 0x53, (byte) 0x2f,
        (byte) 0xf8, (byte) 0x67, (byte) 0xcb, (byte) 0xb6, (byte) 0x74, (byte) 0x3d, (byte) 0x95, (byte) 0x91,
        (byte) 0x03, (byte) 0x56, (byte) 0xe5, (byte) 0x99, (byte) 0x81, (byte) 0x3a, (byte) 0xbe, (byte) 0xb0,
        (byte) 0x2b, (byte) 0x0c, (byte) 0x48, (byte) 0x73, (byte) 0xa6, (byte) 0x64, (byte) 0x6f, (byte) 0x0c,
        (byte) 0x96, (byte) 0x52, (byte) 0x20, (byte) 0xcc, (byte) 0xcc, (byte) 0x7c, (byte) 0x98, (byte) 0xd4,
        (byte) 0x20, (byte) 0x6b, (byte) 0xb5, (byte) 0x28, (byte) 0xee, (byte) 0xf7, (byte) 0xce, (byte) 0x9a,
        (byte) 0x3d, (byte) 0xb2, (byte) 0x22, (byte) 0xb4, (byte) 0xc5, (byte) 0x8d, (byte) 0x3f, (byte) 0xb3,
        (byte) 0x0e, (byte) 0x6b, (byte) 0xdd, (byte) 0x00, (byte) 0xbd, (byte) 0xb7, (byte) 0x13, (byte) 0xf6,
        (byte) 0xd8, (byte) 0xcb, (byte) 0xf7, (byte) 0x05, (byte) 0x42, (byte) 0x25, (byte) 0xe5, (byte) 0x32,
        (byte) 0xeb, (byte) 0xb3, (byte) 0xda, (byte) 0x2e, (byte) 0xb5, (byte) 0x02, (byte) 0x74, (byte) 0x54,
        (byte) 0xcc, (byte) 0x25, (byte) 0x75, (byte) 0x8d, (byte) 0x9f, (byte) 0x12, (byte) 0x58, (byte) 0x02,
        (byte) 0x2d, (byte) 0x67, (byte) 0x12, (byte) 0x56, (byte) 0x41, (byte) 0xf8, (byte) 0xe2, (byte) 0xa6,
        (byte) 0x70, (byte) 0x5a, (byte) 0x2a, (byte) 0xf5, (byte) 0x72, (byte) 0xaf, (byte) 0x9c, (byte) 0x23,
    });

    private static final BigInteger DSA_P = new BigInteger(new byte[] {
        (byte) 0x00, (byte) 0x9e, (byte) 0x61, (byte) 0xc2, (byte) 0x89, (byte) 0xef, (byte) 0x77, (byte) 0xa9,
        (byte) 0x4e, (byte) 0x13, (byte) 0x67, (byte) 0x64, (byte) 0x1f, (byte) 0x09, (byte) 0x01, (byte) 0xfe,
        (byte) 0x24, (byte) 0x13, (byte) 0x53, (byte) 0xe0, (byte) 0xb7, (byte) 0x90, (byte) 0xa8, (byte) 0x4e,
        (byte) 0x76, (byte) 0xfe, (byte) 0x89, (byte) 0x82, (byte) 0x7f, (byte) 0x7a, (byte) 0xc5, (byte) 0x3c,
        (byte) 0x4e, (byte) 0x0c, (byte) 0x20, (byte) 0x55, (byte) 0x30, (byte) 0x95, (byte) 0x42, (byte) 0x85,
        (byte) 0xe1, (byte) 0x40, (byte) 0x7d, (byte) 0x27, (byte) 0x8f, (byte) 0x07, (byte) 0x0d, (byte) 0xe8,
        (byte) 0xdc, (byte) 0x99, (byte) 0xef, (byte) 0xb3, (byte) 0x07, (byte) 0x94, (byte) 0x34, (byte) 0xd6,
        (byte) 0x7c, (byte) 0xff, (byte) 0x9c, (byte) 0xbe, (byte) 0x69, (byte) 0xd3, (byte) 0xeb, (byte) 0x44,
        (byte) 0x37, (byte) 0x50, (byte) 0xef, (byte) 0x49, (byte) 0xf8, (byte) 0xe2, (byte) 0x5b, (byte) 0xd8,
        (byte) 0xd1, (byte) 0x10, (byte) 0x84, (byte) 0x97, (byte) 0xea, (byte) 0xe3, (byte) 0xa5, (byte) 0x1c,
        (byte) 0xc0, (byte) 0x4e, (byte) 0x69, (byte) 0xca, (byte) 0x70, (byte) 0x3d, (byte) 0x78, (byte) 0xb9,
        (byte) 0x16, (byte) 0xe5, (byte) 0xfe, (byte) 0x61, (byte) 0x5d, (byte) 0x8a, (byte) 0x5a, (byte) 0xb3,
        (byte) 0x2c, (byte) 0x61, (byte) 0xb6, (byte) 0x01, (byte) 0x3b, (byte) 0xd0, (byte) 0x01, (byte) 0x7c,
        (byte) 0x32, (byte) 0x8d, (byte) 0xe1, (byte) 0xf3, (byte) 0x69, (byte) 0x0e, (byte) 0x8b, (byte) 0x58,
        (byte) 0xc6, (byte) 0xcf, (byte) 0x00, (byte) 0x94, (byte) 0xf8, (byte) 0x49, (byte) 0x2a, (byte) 0x4b,
        (byte) 0xea, (byte) 0xda, (byte) 0x00, (byte) 0xff, (byte) 0x4b, (byte) 0xd0, (byte) 0xbe, (byte) 0x40,
        (byte) 0x23,
    });

    private static final BigInteger DSA_Q = new BigInteger(new byte[] {
        (byte) 0x00, (byte) 0xbf, (byte) 0xee, (byte) 0xaa, (byte) 0x0f, (byte) 0x12, (byte) 0x34, (byte) 0x50,
        (byte) 0x72, (byte) 0xf8, (byte) 0x60, (byte) 0x13, (byte) 0xd8, (byte) 0xf1, (byte) 0x41, (byte) 0x01,
        (byte) 0x10, (byte) 0xa5, (byte) 0x2f, (byte) 0x57, (byte) 0x5f,
    });

    private static final BigInteger DSA_G = new BigInteger(new byte[] {
        (byte) 0x77, (byte) 0xd4, (byte) 0x7a, (byte) 0x12, (byte) 0xcc, (byte) 0x81, (byte) 0x7e, (byte) 0x7e,
        (byte) 0xeb, (byte) 0x3a, (byte) 0xfb, (byte) 0xe6, (byte) 0x86, (byte) 0x6d, (byte) 0x5a, (byte) 0x10,
        (byte) 0x1d, (byte) 0xad, (byte) 0xa9, (byte) 0x4f, (byte) 0xb9, (byte) 0x03, (byte) 0x5d, (byte) 0x21,
        (byte) 0x1a, (byte) 0xe4, (byte) 0x30, (byte) 0x95, (byte) 0x75, (byte) 0x8e, (byte) 0xcd, (byte) 0x5e,
        (byte) 0xd1, (byte) 0xbd, (byte) 0x0a, (byte) 0x45, (byte) 0xee, (byte) 0xe7, (byte) 0xf7, (byte) 0x6b,
        (byte) 0x65, (byte) 0x02, (byte) 0x60, (byte) 0xd0, (byte) 0x2e, (byte) 0xaf, (byte) 0x3d, (byte) 0xbc,
        (byte) 0x07, (byte) 0xdd, (byte) 0x2b, (byte) 0x8e, (byte) 0x33, (byte) 0xc0, (byte) 0x93, (byte) 0x80,
        (byte) 0xd9, (byte) 0x2b, (byte) 0xa7, (byte) 0x71, (byte) 0x57, (byte) 0x76, (byte) 0xbc, (byte) 0x8e,
        (byte) 0xb9, (byte) 0xe0, (byte) 0xd7, (byte) 0xf4, (byte) 0x23, (byte) 0x8d, (byte) 0x41, (byte) 0x1a,
        (byte) 0x97, (byte) 0x4f, (byte) 0x2c, (byte) 0x1b, (byte) 0xd5, (byte) 0x4b, (byte) 0x66, (byte) 0xe8,
        (byte) 0xfa, (byte) 0xd2, (byte) 0x50, (byte) 0x0d, (byte) 0x17, (byte) 0xab, (byte) 0x34, (byte) 0x31,
        (byte) 0x3d, (byte) 0xa4, (byte) 0x88, (byte) 0xd8, (byte) 0x8e, (byte) 0xa8, (byte) 0xa7, (byte) 0x6e,
        (byte) 0x17, (byte) 0x03, (byte) 0xb7, (byte) 0x0f, (byte) 0x68, (byte) 0x7c, (byte) 0x64, (byte) 0x7b,
        (byte) 0x92, (byte) 0xb8, (byte) 0x63, (byte) 0xe4, (byte) 0x9a, (byte) 0x67, (byte) 0x18, (byte) 0x81,
        (byte) 0x27, (byte) 0xd4, (byte) 0x0b, (byte) 0x13, (byte) 0x48, (byte) 0xd3, (byte) 0x7d, (byte) 0x4e,
        (byte) 0xf6, (byte) 0xa8, (byte) 0x8f, (byte) 0x56, (byte) 0x17, (byte) 0x2d, (byte) 0x08, (byte) 0x51,
    });

    /**
     * A possible signature using SHA1withDSA of Vector2Data. Note that DSS is
     * randomized, so this won't be the exact signature you'll get out of
     * another signing operation unless you use a fixed RNG.
     */
    public static final byte[] SHA1withDSA_Vector2Signature = new byte[] {
        (byte) 0x30, (byte) 0x2d, (byte) 0x02, (byte) 0x15, (byte) 0x00, (byte) 0x88, (byte) 0xef, (byte) 0xac,
        (byte) 0x2b, (byte) 0x8b, (byte) 0xe2, (byte) 0x61, (byte) 0xc6, (byte) 0x2b, (byte) 0xea, (byte) 0xd5,
        (byte) 0x96, (byte) 0xbc, (byte) 0xb0, (byte) 0xa1, (byte) 0x30, (byte) 0x0c, (byte) 0x1f, (byte) 0xed,
        (byte) 0x11, (byte) 0x02, (byte) 0x14, (byte) 0x15, (byte) 0xc4, (byte) 0xfc, (byte) 0x82, (byte) 0x6f,
        (byte) 0x17, (byte) 0xdc, (byte) 0x87, (byte) 0x82, (byte) 0x75, (byte) 0x23, (byte) 0xd4, (byte) 0x58,
        (byte) 0xdc, (byte) 0x73, (byte) 0x3d, (byte) 0xf3, (byte) 0x51, (byte) 0xc0, (byte) 0x57,
    };

    public void testSign_SHA1withDSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("DSA");
        DSAPrivateKeySpec keySpec = new DSAPrivateKeySpec(DSA_priv, DSA_P, DSA_Q, DSA_G);
        PrivateKey privKey = kf.generatePrivate(keySpec);

        Signature sig = Signature.getInstance("SHA1withDSA");
        sig.initSign(privKey);
        sig.update(Vector2Data);

        byte[] signature = sig.sign();
        assertNotNull("Signature must not be null", signature);

        DSAPublicKeySpec pubKeySpec = new DSAPublicKeySpec(DSA_pub, DSA_P, DSA_Q, DSA_G);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(signature));
    }

    public void testVerify_SHA1withDSA_Key_Success() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("DSA");
        DSAPublicKeySpec pubKeySpec = new DSAPublicKeySpec(DSA_pub, DSA_P, DSA_Q, DSA_G);
        PublicKey pubKey = kf.generatePublic(pubKeySpec);

        Signature sig = Signature.getInstance("SHA1withDSA");
        sig.initVerify(pubKey);
        sig.update(Vector2Data);
        assertTrue("Signature must verify correctly", sig.verify(SHA1withDSA_Vector2Signature));
    }

    // NetscapeCertRequest looks up Signature algorithms by OID from
    // BC but BC version 1.47 had registration bugs and MD5withRSA was
    // overlooked.  http://b/7453821
    public void testGetInstanceFromOID() throws Exception {
        assertBouncyCastleSignatureFromOID("1.2.840.113549.1.1.4");  // MD5withRSA
        assertBouncyCastleSignatureFromOID("1.2.840.113549.1.1.5");  // SHA1withRSA
        assertBouncyCastleSignatureFromOID("1.3.14.3.2.29");         // SHA1withRSA
        assertBouncyCastleSignatureFromOID("1.2.840.113549.1.1.11"); // SHA256withRSA
        assertBouncyCastleSignatureFromOID("1.2.840.113549.1.1.12"); // SHA384withRSA
        assertBouncyCastleSignatureFromOID("1.2.840.113549.1.1.13"); // SHA512withRSA
        assertBouncyCastleSignatureFromOID("1.2.840.10040.4.3");     // SHA1withDSA
    }

    private void assertBouncyCastleSignatureFromOID(String oid) throws Exception {
        Signature signature = Signature.getInstance(oid, "BC");
        assertNotNull(oid, signature);
        assertEquals(oid, signature.getAlgorithm());
    }
}
