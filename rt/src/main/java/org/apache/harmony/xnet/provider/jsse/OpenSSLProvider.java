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

package org.apache.harmony.xnet.provider.jsse;

import java.security.Provider;

public final class OpenSSLProvider extends Provider {

    public OpenSSLProvider() {
        super("AndroidOpenSSL", 1.0, "Android's OpenSSL-backed security provider");

        put("SSLContext.SSL", OpenSSLContextImpl.class.getName());
        put("SSLContext.SSLv3", OpenSSLContextImpl.class.getName());
        put("SSLContext.TLS", OpenSSLContextImpl.class.getName());
        put("SSLContext.TLSv1", OpenSSLContextImpl.class.getName());
        put("SSLContext.Default", DefaultSSLContextImpl.class.getName());

        put("MessageDigest.SHA-1",
            "org.apache.harmony.xnet.provider.jsse.OpenSSLMessageDigestJDK$SHA1");
        put("Alg.Alias.MessageDigest.SHA1", "SHA-1");
        put("Alg.Alias.MessageDigest.SHA", "SHA-1");
        put("Alg.Alias.MessageDigest.1.3.14.3.2.26", "SHA-1");

        put("MessageDigest.SHA-256",
            "org.apache.harmony.xnet.provider.jsse.OpenSSLMessageDigestJDK$SHA256");
        put("Alg.Alias.MessageDigest.SHA256", "SHA-256");
        put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.1", "SHA-256");

        put("MessageDigest.SHA-384",
            "org.apache.harmony.xnet.provider.jsse.OpenSSLMessageDigestJDK$SHA384");
        put("Alg.Alias.MessageDigest.SHA384", "SHA-384");
        put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.2", "SHA-384");

        put("MessageDigest.SHA-512",
            "org.apache.harmony.xnet.provider.jsse.OpenSSLMessageDigestJDK$SHA512");
        put("Alg.Alias.MessageDigest.SHA512", "SHA-512");
        put("Alg.Alias.MessageDigest.2.16.840.1.101.3.4.2.3", "SHA-512");

        put("MessageDigest.MD5",
            "org.apache.harmony.xnet.provider.jsse.OpenSSLMessageDigestJDK$MD5");
        put("Alg.Alias.MessageDigest.1.2.840.113549.2.5", "MD5");

        // TODO Flush out implementation of OpenSSLSignature so it can be registered here
    }
}
