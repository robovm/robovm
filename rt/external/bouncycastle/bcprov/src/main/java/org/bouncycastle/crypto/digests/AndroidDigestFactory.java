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

package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Digest;

/**
 * Level of indirection to let us select OpenSSLDigest implementations
 * for libcore but fallback to BouncyCastle ones on the RI.
 */
public final class AndroidDigestFactory {
    private static final String OpenSSLFactoryClassName
            = AndroidDigestFactory.class.getName() + "OpenSSL";
    private static final String BouncyCastleFactoryClassName
            = AndroidDigestFactory.class.getName() + "BouncyCastle";

    private static final AndroidDigestFactoryInterface FACTORY;
    static {
        Class factoryImplementationClass;
        try {
            factoryImplementationClass = Class.forName(OpenSSLFactoryClassName);
            // Double check for NativeCrypto in case we are running on RI for testing
            Class.forName("com.android.org.conscrypt.NativeCrypto");
        } catch (ClassNotFoundException e1) {
            try {
                factoryImplementationClass = Class.forName(BouncyCastleFactoryClassName);
            } catch (ClassNotFoundException e2) {
                AssertionError e = new AssertionError("Failed to load "
                                         + "AndroidDigestFactoryInterface "
                                         + "implementation. Looked for "
                                         + OpenSSLFactoryClassName + " and "
                                         + BouncyCastleFactoryClassName);
                e.initCause(e1);
                throw e;
            }
        }
        if (!AndroidDigestFactoryInterface.class.isAssignableFrom(factoryImplementationClass)) {
            throw new AssertionError(factoryImplementationClass
                                     + "does not implement AndroidDigestFactoryInterface");
        }
        try {
            FACTORY = (AndroidDigestFactoryInterface) factoryImplementationClass.newInstance();
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public static Digest getMD5() {
        return FACTORY.getMD5();
    }

    public static Digest getSHA1() {
        return FACTORY.getSHA1();
    }

    public static Digest getSHA256() {
        return FACTORY.getSHA256();
    }

    public static Digest getSHA384() {
        return FACTORY.getSHA384();
    }

    public static Digest getSHA512() {
        return FACTORY.getSHA512();
    }
}
