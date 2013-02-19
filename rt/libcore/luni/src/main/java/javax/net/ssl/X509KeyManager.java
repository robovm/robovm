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

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * A Key Manager for X509 certificate-based key pairs.
 */
public interface X509KeyManager extends KeyManager {

    /**
     * Chooses an alias for the client side of an SSL connection to authenticate
     * it with the specified public key type and certificate issuers.
     *
     * @param keyType
     *            the list of public key algorithm names.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @param socket
     *            the socket for the connection, or {@code null} if
     *            the alias selected does not depend on a specific socket.
     * @return the alias name of a matching key or {@code null} if there are no
     *         matches.
     */
    public String chooseClientAlias(String[] keyType, Principal[] issuers,
            Socket socket);

    /**
     * Chooses an alias for the server side of an SSL connection to authenticate
     * it with the specified public key type and certificate issuers.
     *
     * @param keyType
     *            the list of public key algorithm type names.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @param socket
     *            the socket for the connection, or {@code null} if
     *            the alias selected does not depend on a specific socket.
     * @return the alias name of a matching key or {@code null} if there are no
     *         matches.
     */
    public String chooseServerAlias(String keyType, Principal[] issuers,
            Socket socket);

    /**
     * Returns the certificate chain for the specified alias.
     *
     * @param alias
     *            the alias to get the certificate chain for.
     * @return the certificate chain for the specified alias, or {@code null} if
     *         the alias cannot be found.
     */
    public X509Certificate[] getCertificateChain(String alias);

    /**
     * Returns the client aliases for the specified public key type and list of
     * certificate issuers.
     *
     * @param keyType
     *            the public key algorithm type name.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @return the client aliases for the specified public key type, or
     *         {@code null} if there are no matching aliases.
     */
    public String[] getClientAliases(String keyType, Principal[] issuers);

    /**
     * Returns the server aliases for the specified public key type and list of
     * certificate issuers.
     *
     * @param keyType
     *            the public key algorithm type name.
     * @param issuers
     *            the list of certificate issuers, or {@code null} if any issuer
     *            will do.
     * @return the client aliases for the specified public key type, or
     *         {@code null} if there are no matching aliases.
     */
    public String[] getServerAliases(String keyType, Principal[] issuers);

    /**
     * Returns the private key for the specified alias.
     *
     * @param alias
     *            the alias to get the private key for.
     * @return the private key for the specified alias, or {@code null} if the
     *         alias cannot be found.
     */
    public PrivateKey getPrivateKey(String alias);
}
