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

package libcore.javax.net.ssl;

import junit.framework.Assert;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * {@link X509ExtendedKeyManager} which forwards all calls to a delegate while substituting
 * the returned private key with its own randomly generated keys of the same type (and parameters).
 */
public class RandomPrivateKeyX509ExtendedKeyManager extends ForwardingX509ExtendedKeyManager {

  private final Map<String, PrivateKey> cachedKeys = new HashMap<String, PrivateKey>();

  public RandomPrivateKeyX509ExtendedKeyManager(X509ExtendedKeyManager delegate) {
    super(delegate);
  }

  @Override
  public PrivateKey getPrivateKey(String alias) {
    PrivateKey originalPrivateKey = super.getPrivateKey(alias);
    if (originalPrivateKey == null) {
      return null;
    }

    PrivateKey result;
    String keyAlgorithm = originalPrivateKey.getAlgorithm();
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
      if ("RSA".equals(keyAlgorithm)) {
        RSAPrivateKeySpec originalKeySpec =
            keyFactory.getKeySpec(originalPrivateKey, RSAPrivateKeySpec.class);
        int keyLengthBits = originalKeySpec.getModulus().bitLength();
        // Use a cache because RSA key generation is slow.
        String cacheKey = keyAlgorithm + "-" + keyLengthBits;
        result = cachedKeys.get(cacheKey);
        if (result == null) {
          KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
          keyPairGenerator.initialize(keyLengthBits);
          result = keyPairGenerator.generateKeyPair().getPrivate();
          cachedKeys.put(cacheKey, result);
        }
      } else if ("DSA".equals(keyAlgorithm)) {
        DSAPrivateKeySpec originalKeySpec =
            keyFactory.getKeySpec(originalPrivateKey, DSAPrivateKeySpec.class);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
        keyPairGenerator.initialize(new DSAParameterSpec(
            originalKeySpec.getP(), originalKeySpec.getQ(), originalKeySpec.getG()));
        result = keyPairGenerator.generateKeyPair().getPrivate();
      } else {
        Assert.fail("Unsupported key algorithm: " + originalPrivateKey.getAlgorithm());
        result = null;
      }
    } catch (GeneralSecurityException e) {
      Assert.fail("Failed to generate private key: " + e);
      result = null;
    }

    return result;
  }
}
