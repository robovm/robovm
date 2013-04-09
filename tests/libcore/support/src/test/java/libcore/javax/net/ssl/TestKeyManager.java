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

import java.io.PrintStream;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
import libcore.java.io.NullPrintStream;
import libcore.java.security.StandardNames;

/**
 * TestKeyManager is a simple proxy class that wraps an existing
 * X509ExtendedKeyManager to provide debug logging and recording of
 * values.
 */
public final class TestKeyManager extends X509ExtendedKeyManager {

    private static final boolean LOG = false;
    private static final PrintStream out = LOG ? System.out : new NullPrintStream();

    private final X509ExtendedKeyManager keyManager;

    public static KeyManager[] wrap(KeyManager[] keyManagers) {
        KeyManager[] result = keyManagers.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] = wrap(result[i]);
        }
        return result;
    }

    public static KeyManager wrap(KeyManager keyManager) {
        if (!(keyManager instanceof X509ExtendedKeyManager)) {
            return keyManager;
        }
        return new TestKeyManager((X509ExtendedKeyManager) keyManager);
    }

    public TestKeyManager(X509ExtendedKeyManager keyManager) {
        out.println("TestKeyManager.<init> keyManager=" + keyManager);
        this.keyManager = keyManager;
    }

    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        out.print("TestKeyManager.chooseClientAlias");
        out.print(" | keyTypes: ");
        for (String keyType : keyTypes) {
            out.print(keyType);
            out.print(' ');
        }
        dumpIssuers(issuers);
        dumpSocket(socket);
        assertKeyTypes(keyTypes);
        return dumpAlias(keyManager.chooseClientAlias(keyTypes, issuers, socket));
    }

    private void assertKeyTypes(String[] keyTypes) {
        for (String keyType : keyTypes) {
            assertKeyType(keyType);
        }
    }

    private void assertKeyType(String keyType) {
        if (!StandardNames.KEY_TYPES.contains(keyType)) {
            throw new AssertionError("Unexpected key type " + keyType);
        }
    }

    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        out.print("TestKeyManager.chooseServerAlias");
        out.print(" | keyType: ");
        out.print(keyType);
        out.print(' ');
        dumpIssuers(issuers);
        dumpSocket(socket);
        assertKeyType(keyType);
        return dumpAlias(keyManager.chooseServerAlias(keyType, issuers, socket));
    }

    private void dumpSocket(Socket socket) {
        out.print(" | socket: ");
        out.print(String.valueOf(socket));
    }

    private void dumpIssuers(Principal[] issuers) {
        out.print(" | issuers: ");
        if (issuers == null) {
            out.print("null");
            return;
        }
        for (Principal issuer : issuers) {
            out.print(issuer);
            out.print(' ');
        }
    }

    private String dumpAlias(String alias) {
        out.print(" => ");
        out.println(alias);
        return alias;
    }

    public X509Certificate[] getCertificateChain(String alias) {
        out.print("TestKeyManager.getCertificateChain");
        out.print(" | alias: ");
        out.print(alias);
        return dumpCerts(keyManager.getCertificateChain(alias));
    }

    private X509Certificate[] dumpCerts(X509Certificate[] certs) {
        out.print(" => ");
        for (X509Certificate cert : certs) {
            out.print(cert.getSubjectDN());
            out.print(' ');
        }
        out.println();
        return certs;
    }

    public String[] getClientAliases(String keyType, Principal[] issuers) {
        out.print("TestKeyManager.getClientAliases");
        out.print(" | keyType: ");
        out.print(keyType);
        dumpIssuers(issuers);
        assertKeyType(keyType);
        return dumpAliases(keyManager.getClientAliases(keyType, issuers));
    }

    public String[] getServerAliases(String keyType, Principal[] issuers) {
        out.print("TestKeyManager.getServerAliases");
        out.print(" | keyType: ");
        out.print(keyType);
        dumpIssuers(issuers);
        assertKeyType(keyType);
        return dumpAliases(keyManager.getServerAliases(keyType, issuers));
    }

    private String[] dumpAliases(String[] aliases) {
        out.print(" => ");
        for (String alias : aliases) {
            out.print(alias);
            out.print(' ');
        }
        out.println();
        return aliases;
    }

    public PrivateKey getPrivateKey(String alias) {
        out.print("TestKeyManager.getPrivateKey");
        out.print(" | alias: ");
        out.print(alias);
        PrivateKey pk = keyManager.getPrivateKey(alias);
        out.print(" => ");
        out.println(String.valueOf(pk));
        return pk;
    }

    public String chooseEngineClientAlias(String[] keyTypes, Principal[] issuers, SSLEngine e) {
        out.print("TestKeyManager.chooseEngineClientAlias");
        out.print(" | keyTypes: ");
        for (String keyType : keyTypes) {
            out.print(keyType);
            out.print(' ');
        }
        dumpIssuers(issuers);
        dumpEngine(e);
        assertKeyTypes(keyTypes);
        return dumpAlias(keyManager.chooseEngineClientAlias(keyTypes, issuers, e));
    }

    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine e) {
        out.print("TestKeyManager.chooseEngineServerAlias");
        out.print(" | keyType: ");
        out.print(keyType);
        out.print(' ');
        dumpIssuers(issuers);
        dumpEngine(e);
        assertKeyType(keyType);
        return dumpAlias(keyManager.chooseEngineServerAlias(keyType, issuers, e));
    }

    private void dumpEngine(SSLEngine engine) {
        out.print(" | engine: ");
        out.print(String.valueOf(engine));
    }
}

