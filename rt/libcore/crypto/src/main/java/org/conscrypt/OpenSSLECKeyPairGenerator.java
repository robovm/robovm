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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.util.HashMap;
import java.util.Map;

public final class OpenSSLECKeyPairGenerator extends KeyPairGenerator {
    private static final String ALGORITHM = "EC";

    private static final int DEFAULT_KEY_SIZE = 192;

    private static final Map<Integer, String> SIZE_TO_CURVE_NAME = new HashMap<Integer, String>();

    static {
        /* NIST curves */
        SIZE_TO_CURVE_NAME.put(192, "prime192v1");
        SIZE_TO_CURVE_NAME.put(224, "secp224r1");
        SIZE_TO_CURVE_NAME.put(256, "prime256v1");
        SIZE_TO_CURVE_NAME.put(384, "secp384r1");
        SIZE_TO_CURVE_NAME.put(521, "secp521r1");
    }

    private OpenSSLECGroupContext group;

    public OpenSSLECKeyPairGenerator() {
        super(ALGORITHM);
    }

    @Override
    public KeyPair generateKeyPair() {
        if (group == null) {
            final String curveName = SIZE_TO_CURVE_NAME.get(DEFAULT_KEY_SIZE);
            group = OpenSSLECGroupContext.getCurveByName(curveName);
        }

        final OpenSSLKey key = new OpenSSLKey(NativeCrypto.EC_KEY_generate_key(group.getContext()));
        return new KeyPair(new OpenSSLECPublicKey(group, key), new OpenSSLECPrivateKey(group, key));
    }

    @Override
    public void initialize(int keysize, SecureRandom random) {
        final String name = SIZE_TO_CURVE_NAME.get(keysize);
        if (name == null) {
            throw new InvalidParameterException("unknown key size " + keysize);
        }

        /*
         * Store the group in a temporary variable until we know this is a valid
         * group.
         */
        final OpenSSLECGroupContext possibleGroup = OpenSSLECGroupContext.getCurveByName(name);
        if (possibleGroup == null) {
            throw new InvalidParameterException("unknown curve " + name);
        }

        group = possibleGroup;
    }

    @Override
    public void initialize(AlgorithmParameterSpec param, SecureRandom random)
            throws InvalidAlgorithmParameterException {
        if (param instanceof ECParameterSpec) {
            ECParameterSpec ecParam = (ECParameterSpec) param;

            group = OpenSSLECGroupContext.getInstance(ecParam);
        } else if (param instanceof ECGenParameterSpec) {
            ECGenParameterSpec ecParam = (ECGenParameterSpec) param;

            final String curveName = ecParam.getName();

            /*
             * Store the group in a temporary variable until we know this is a
             * valid group.
             */
            final OpenSSLECGroupContext possibleGroup = OpenSSLECGroupContext
                    .getCurveByName(curveName);
            if (possibleGroup == null) {
                throw new InvalidAlgorithmParameterException("unknown curve name: " + curveName);
            }

            group = possibleGroup;
        } else {
            throw new InvalidAlgorithmParameterException(
                    "parameter must be ECParameterSpec or ECGenParameterSpec");
        }
    }
}
