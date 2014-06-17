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

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * {@link X509ExtendedKeyManager} which delegates all calls to the provided
 * {@code X509ExtendedKeyManager} instance.
 */
public class ForwardingX509ExtendedKeyManager extends X509ExtendedKeyManager {
  private final X509ExtendedKeyManager delegate;

  public ForwardingX509ExtendedKeyManager(X509ExtendedKeyManager delegate) {
    this.delegate = delegate;
  }

  @Override
  public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
    return delegate.chooseClientAlias(keyType, issuers, socket);
  }

  @Override
  public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
    return delegate.chooseServerAlias(keyType, issuers, socket);
  }

  @Override
  public X509Certificate[] getCertificateChain(String alias) {
    return delegate.getCertificateChain(alias);
  }

  @Override
  public String[] getClientAliases(String keyType, Principal[] issuers) {
    return delegate.getClientAliases(keyType, issuers);
  }

  @Override
  public String[] getServerAliases(String keyType, Principal[] issuers) {
    return delegate.getServerAliases(keyType, issuers);
  }

  @Override
  public String chooseEngineClientAlias(String[] keyType, Principal[] issuers, SSLEngine engine) {
    return delegate.chooseEngineClientAlias(keyType, issuers, engine);
  }

  @Override
  public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine) {
    return delegate.chooseEngineServerAlias(keyType, issuers, engine);
  }

  @Override
  public PrivateKey getPrivateKey(String alias) {
    return delegate.getPrivateKey(alias);
  }
}
