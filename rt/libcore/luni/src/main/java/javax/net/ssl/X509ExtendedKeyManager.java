/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.net.ssl;

import java.security.Principal;

/**
 * The abstract extension for the {@code X509KeyManager} interface.
 */
public abstract class X509ExtendedKeyManager implements X509KeyManager {

    /**
     * To be used by subclasses only.
     * <p>
     * Creates a new {@code X509ExtendedKeyManager} instance.
     */
    protected X509ExtendedKeyManager() {
    }

    /**
     * Chooses an alias for the client side of an SSL connection to authenticate
     * it with the specified public key type and certificate issuers.
     *
     * @param keyType
     *            the list of public key algorithm names.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @param engine
     *            the {@code SSLEngine} for the connection, or {@code null} if
     *            no engine is predefined.
     * @return the alias name of a matching key or {@code null} if there are no
     *         matches.
     */
    public String chooseEngineClientAlias(String[] keyType,
            Principal[] issuers, SSLEngine engine) {
        return null;
    }

    /**
     * Chooses an alias for the server side of an SSL connection to authenticate
     * it with the specified public key type and certificate issuers.
     *
     * @param keyType
     *            the list of public key algorithm names.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @param engine
     *            the {@code SSLEngine} for the connection, or {@code null} if
     *            no engine is predefined.
     * @return the alias name of a matching key or {@code null} if there are no
     *         matches.
     */
    public String chooseEngineServerAlias(String keyType, Principal[] issuers,
            SSLEngine engine) {
        return null;
    }

}
