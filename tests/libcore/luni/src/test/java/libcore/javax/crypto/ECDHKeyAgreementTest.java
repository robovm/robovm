/*
 * Copyright (C) 2013 The Android Open Source Project
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

import static libcore.java.security.SignatureTest.hexToBytes;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Comparator;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

/**
 * Tests for all registered Elliptic Curve Diffie-Hellman {@link KeyAgreement} providers.
 */
public class ECDHKeyAgreementTest extends TestCase {
    // Two key pairs and the resulting shared secret for the Known Answer Test
    private static final byte[] KAT_PUBLIC_KEY1_X509 = hexToBytes(
            "3059301306072a8648ce3d020106082a8648ce3d030107034200049fc2f71f85446b1371244491d83"
            + "9cf97b5d27cedbb04d2c0058b59709df3a216e6b4ca1b2d622588c5a0e6968144a8965e816a600c"
            + "05305a1da3df2bf02b41d1");
    private static final byte[] KAT_PRIVATE_KEY1_PKCS8 = hexToBytes(
            "308193020100301306072a8648ce3d020106082a8648ce3d030107047930770201010420e1e683003"
            + "c8b963a92742e5f955ce7fddc81d0c3ae9b149d6af86a0cacb2271ca00a06082a8648ce3d030107"
            + "a144034200049fc2f71f85446b1371244491d839cf97b5d27cedbb04d2c0058b59709df3a216e6b"
            + "4ca1b2d622588c5a0e6968144a8965e816a600c05305a1da3df2bf02b41d1");

    private static final byte[] KAT_PUBLIC_KEY2_X509 = hexToBytes(
            "3059301306072a8648ce3d020106082a8648ce3d03010703420004358efb6d91e5bbcae21774af3f6"
            + "d85d0848630e7e61dbeb5ac9e47036ed0f8d38c7a1d1bb249f92861c7c9153fff33f45ab5b171eb"
            + "e8cad741125e6bb4fc6b07");
    private static final byte[] KAT_PRIVATE_KEY2_PKCS8 = hexToBytes(
            "308193020100301306072a8648ce3d020106082a8648ce3d0301070479307702010104202b1810a69"
            + "e12b74d50bf0343168f705f0104f76299855268aa526fdb31e6eec0a00a06082a8648ce3d030107"
            + "a14403420004358efb6d91e5bbcae21774af3f6d85d0848630e7e61dbeb5ac9e47036ed0f8d38c7"
            + "a1d1bb249f92861c7c9153fff33f45ab5b171ebe8cad741125e6bb4fc6b07");

    private static final byte[] KAT_SECRET =
            hexToBytes("4faa0594c0e773eb26c8df2163af2443e88aab9578b9e1f324bc61e42d222783");

    private static final ECPublicKey KAT_PUBLIC_KEY1;
    private static final ECPrivateKey KAT_PRIVATE_KEY1;
    private static final ECPublicKey KAT_PUBLIC_KEY2;
    private static final ECPrivateKey KAT_PRIVATE_KEY2;
    static {
        try {
            KAT_PUBLIC_KEY1 = getPublicKey(KAT_PUBLIC_KEY1_X509);
            KAT_PRIVATE_KEY1 = getPrivateKey(KAT_PRIVATE_KEY1_PKCS8);
            KAT_PUBLIC_KEY2 = getPublicKey(KAT_PUBLIC_KEY2_X509);
            KAT_PRIVATE_KEY2 = getPrivateKey(KAT_PRIVATE_KEY2_PKCS8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode KAT key pairs using default provider", e);
        }
    }

    /**
     * Performs a known-answer test of the shared secret for all permutations of {@code Providers}
     * of: first key pair, second key pair, and the {@code KeyAgreement}. This is to check that
     * the {@code KeyAgreement} instances work with keys of all registered providers.
     */
    public void testKnownAnswer() throws Exception {
        for (Provider keyFactoryProvider1 : getKeyFactoryProviders()) {
            ECPrivateKey privateKey1 = getPrivateKey(KAT_PRIVATE_KEY1_PKCS8, keyFactoryProvider1);
            ECPublicKey publicKey1 = getPublicKey(KAT_PUBLIC_KEY1_X509, keyFactoryProvider1);
            for (Provider keyFactoryProvider2 : getKeyFactoryProviders()) {
                ECPrivateKey privateKey2 =
                        getPrivateKey(KAT_PRIVATE_KEY2_PKCS8, keyFactoryProvider2);
                ECPublicKey publicKey2 =
                        getPublicKey(KAT_PUBLIC_KEY2_X509, keyFactoryProvider2);
                for (Provider keyAgreementProvider : getKeyAgreementProviders()) {
                    try {
                        testKnownAnswer(publicKey1, privateKey1, publicKey2, privateKey2,
                                keyAgreementProvider);
                    } catch (Throwable e) {
                        throw new RuntimeException(getClass().getSimpleName() + ".testKnownAnswer("
                                + keyFactoryProvider1.getName()
                                + ", " + keyFactoryProvider2.getName()
                                + ", " + keyAgreementProvider.getName() + ")",
                                e);
                    }
                }
            }
        }
    }

    void testKnownAnswer(
            ECPublicKey publicKey1, ECPrivateKey privateKey1,
            ECPublicKey publicKey2, ECPrivateKey privateKey2,
            Provider keyAgreementProvider) throws Exception {
        assertTrue(Arrays.equals(
                KAT_SECRET, generateSecret(keyAgreementProvider, privateKey1, publicKey2)));
        assertTrue(Arrays.equals(
                KAT_SECRET, generateSecret(keyAgreementProvider, privateKey2, publicKey1)));
    }

    public void testGetAlgorithm() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGetAlgorithm(Provider provider) throws Exception {
        assertEquals("ECDH", getKeyAgreement(provider).getAlgorithm());
    }

    public void testGetProvider() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGetProvider(Provider provider) throws Exception {
        assertSame(provider, getKeyAgreement(provider).getProvider());
    }

    public void testInit_withNullPrivateKey() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testInit_withNullPrivateKey(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        try {
            keyAgreement.init(null);
            fail();
        } catch (InvalidKeyException expected) {}
    }

    public void testInit_withUnsupportedPrivateKeyType() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testInit_withUnsupportedPrivateKeyType(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        try {
            keyAgreement.init(KAT_PUBLIC_KEY1);
            fail();
        } catch (InvalidKeyException expected) {}
    }

    public void testInit_withUnsupportedAlgorithmParameterSpec() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testInit_withUnsupportedAlgorithmParameterSpec(Provider provider) throws Exception {
        try {
            getKeyAgreement(provider).init(KAT_PRIVATE_KEY1, new ECGenParameterSpec("prime256v1"));
            fail();
        } catch (InvalidAlgorithmParameterException expected) {}
    }

    public void testDoPhase_whenNotInitialized() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testDoPhase_whenNotInitialized(Provider provider) throws Exception {
        try {
            getKeyAgreement(provider).doPhase(KAT_PUBLIC_KEY1, true);
            fail();
        } catch (IllegalStateException expected) {}
    }

    public void testDoPhaseReturnsNull() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testDoPhaseReturnsNull(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        assertNull(keyAgreement.doPhase(KAT_PUBLIC_KEY2, true));
    }

    public void testDoPhase_withPhaseWhichIsNotLast() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testDoPhase_withPhaseWhichIsNotLast(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        try {
            keyAgreement.doPhase(KAT_PUBLIC_KEY2, false);
            fail();
        } catch (IllegalStateException expected) {}
    }

    public void testDoPhase_withNullKey() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testDoPhase_withNullKey(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        try {
            keyAgreement.doPhase(null, true);
            fail();
        } catch (InvalidKeyException expected) {}
    }

    public void testDoPhase_withInvalidKeyType() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testDoPhase_withInvalidKeyType(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        try {
            keyAgreement.doPhase(KAT_PRIVATE_KEY1, true);
            fail();
        } catch (InvalidKeyException expected) {}
    }

    public void testGenerateSecret_withNullOutputBuffer() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withNullOutputBuffer(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        keyAgreement.doPhase(KAT_PUBLIC_KEY2, true);
        try {
            keyAgreement.generateSecret(null, 0);
            fail();
        } catch (NullPointerException expected) {}
    }

    public void testGenerateSecret_withBufferOfTheRightSize() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withBufferOfTheRightSize(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        keyAgreement.doPhase(KAT_PUBLIC_KEY2, true);

        byte[] buffer = new byte[KAT_SECRET.length];
        int secretLengthBytes = keyAgreement.generateSecret(buffer, 0);
        assertEquals(KAT_SECRET.length, secretLengthBytes);
        assertTrue(Arrays.equals(KAT_SECRET, buffer));
    }

    public void testGenerateSecret_withLargerThatNeededBuffer() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withLargerThatNeededBuffer(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        keyAgreement.doPhase(KAT_PUBLIC_KEY2, true);

        // Place the shared secret in the middle of the larger buffer and check that only that
        // part of the buffer is affected.
        byte[] buffer = new byte[KAT_SECRET.length + 2];
        buffer[0] = (byte) 0x85; // arbitrary canary value
        buffer[buffer.length - 1] = (byte) 0x3b; // arbitrary canary value
        int secretLengthBytes = keyAgreement.generateSecret(buffer, 1);
        assertEquals(KAT_SECRET.length, secretLengthBytes);
        assertEquals((byte) 0x85, buffer[0]);
        assertEquals((byte) 0x3b, buffer[buffer.length - 1]);
        byte[] secret = new byte[KAT_SECRET.length];
        System.arraycopy(buffer, 1, secret, 0, secret.length);
        assertTrue(Arrays.equals(KAT_SECRET, secret));
    }

    public void testGenerateSecret_withSmallerThanNeededBuffer() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withSmallerThanNeededBuffer(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY1);
        keyAgreement.doPhase(KAT_PUBLIC_KEY2, true);
        try {
            // Although the buffer is big enough (1024 bytes) the shared secret should be placed
            // at offset 1020 thus leaving only 4 bytes for the secret, which is not enough.
            keyAgreement.generateSecret(new byte[1024], 1020);
            fail();
        } catch (ShortBufferException expected) {}
    }

    public void testGenerateSecret_withoutBuffer() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withoutBuffer(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY2);
        keyAgreement.doPhase(KAT_PUBLIC_KEY1, true);

        byte[] secret = keyAgreement.generateSecret();
        assertTrue(Arrays.equals(KAT_SECRET, secret));
    }

    public void testGenerateSecret_withAlgorithm() throws Exception {
        invokeCallingMethodForEachKeyAgreementProvider();
    }

    void testGenerateSecret_withAlgorithm(Provider provider) throws Exception {
        KeyAgreement keyAgreement = getKeyAgreement(provider);
        keyAgreement.init(KAT_PRIVATE_KEY2);
        keyAgreement.doPhase(KAT_PUBLIC_KEY1, true);

        SecretKey key = keyAgreement.generateSecret("AES");
        assertEquals("AES", key.getAlgorithm());
        // The check below will need to change if it's a hardware-backed key.
        // We'll have to encrypt a known plaintext and check that the ciphertext is as
        // expected.
        assertTrue(Arrays.equals(KAT_SECRET, key.getEncoded()));
    }

    private void invokeCallingMethodForEachKeyAgreementProvider() throws Exception {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callingMethodName = null;
        for (int i = 0; i < stackTrace.length; i++) {
            if ("invokeCallingMethodForEachKeyAgreementProvider".equals(
                    stackTrace[i].getMethodName())) {
                callingMethodName = stackTrace[i + 1].getMethodName();
            }
        }
        if (callingMethodName == null) {
            throw new RuntimeException("Failed to deduce calling method name from stack trace");
        }

        String invokedMethodName = callingMethodName;
        Method method;
        try {
            method = getClass().getDeclaredMethod(invokedMethodName, Provider.class);
        } catch (NoSuchMethodError e) {
            throw new AssertionFailedError("Failed to find per-Provider test method "
                    + getClass().getSimpleName() + "." + invokedMethodName + "(Provider)");
        }

        for (Provider provider : getKeyAgreementProviders()) {
            try {
                method.invoke(this, provider);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(getClass().getSimpleName() + "." + invokedMethodName
                        + "(provider: " + provider.getName() + ") failed",
                        e.getCause());
            }
        }
    }

    private static Provider[] getKeyAgreementProviders() {
        Provider[] providers = Security.getProviders("KeyAgreement.ECDH");
        if (providers == null) {
            return new Provider[0];
        }
        // Sort providers by name to guarantee non-determinism in the order in which providers are
        // used in the tests.
        return sortByName(providers);
    }

    private static Provider[] getKeyFactoryProviders() {
        Provider[] providers = Security.getProviders("KeyFactory.EC");
        if (providers == null) {
            return new Provider[0];
        }
        // Sort providers by name to guarantee non-determinism in the order in which providers are
        // used in the tests.
        return sortByName(providers);
    }

    private static ECPrivateKey getPrivateKey(byte[] pkcs8EncodedKey, Provider provider)
            throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("EC", provider);
        return (ECPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));
    }

    private static ECPublicKey getPublicKey(byte[] x509EncodedKey, Provider provider)
            throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("EC", provider);
        return (ECPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(x509EncodedKey));
    }

    private static ECPrivateKey getPrivateKey(byte[] pkcs8EncodedKey)
            throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return (ECPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));
    }

    private static ECPublicKey getPublicKey(byte[] x509EncodedKey)
            throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return (ECPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(x509EncodedKey));
    }

    private static KeyAgreement getKeyAgreement(Provider provider) throws NoSuchAlgorithmException {
        return KeyAgreement.getInstance("ECDH", provider);
    }

    private static byte[] generateSecret(
            Provider keyAgreementProvider, PrivateKey privateKey, PublicKey publicKey)
            throws GeneralSecurityException {
        KeyAgreement keyAgreement = getKeyAgreement(keyAgreementProvider);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret();
    }

    private static Provider[] sortByName(Provider[] providers) {
        Arrays.sort(providers, new Comparator<Provider>() {
            @Override
            public int compare(Provider lhs, Provider rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        return providers;
    }
}
