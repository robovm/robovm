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

package java.security.cert;

/**
 * The parameters to initialize a LDAP {@code CertStore} instance.
 */
public class LDAPCertStoreParameters implements CertStoreParameters {
    // Default LDAP server name
    private static final String DEFAULT_LDAP_SERVER_NAME = "localhost";
    // Default LDAP server port number
    private static final int DEFAULT_LDAP_PORT  = 389;

    // LDAP server name for this cert store
    private final String serverName;
    // LDAP server port number for this cert store
    private final int port;

    /**
     * Creates a new {@code LDAPCertStoreParameters} instance with the specified
     * server name and port.
     *
     * @param serverName
     *            the LDAP server name.
     * @param port
     *            the port.
     * @throws NullPointerException
     *             is {@code serverName} is {@code null}.
     */
    public LDAPCertStoreParameters(String serverName, int port) {
        this.port = port;
        this.serverName = serverName;
        if (this.serverName == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Creates a new {@code LDAPCertStoreParameters} instance with default
     * parameters.
     * <p>
     * The default parameters are server name "localhost" and port 389.
     */
    public LDAPCertStoreParameters() {
        this.serverName = DEFAULT_LDAP_SERVER_NAME;
        this.port = DEFAULT_LDAP_PORT;
    }

    /**
     * Creates a new {@code LDAPCertStoreParameters} instance with the specified
     * server name and default port 389.
     *
     * @param serverName
     *            the LDAP server name.
     * @throws NullPointerException
     *             if {@code serverName} is {@code null}.
     */
    public LDAPCertStoreParameters(String serverName) {
        this.port = DEFAULT_LDAP_PORT;
        this.serverName = serverName;
        if (this.serverName == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Clones this {@code LDAPCertStoreParameters} instance.
     *
     * @return the cloned instance.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns the LDAP server port.
     *
     * @return the LDAP server port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns the LDAP server name.
     *
     * @return the LDAP server name.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Returns the string representation of this {@code LDAPCertStoreParameters}
     * instance.
     *
     * @return the string representation of this {@code LDAPCertStoreParameters}
     *         instance.
     */
    public String toString() {
        StringBuilder sb =
            new StringBuilder("LDAPCertStoreParameters: [\n serverName: ");
        sb.append(getServerName());
        sb.append("\n port: ");
        sb.append(getPort());
        sb.append("\n]");
        return sb.toString();
    }
}
