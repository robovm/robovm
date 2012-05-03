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

/**
* @author Aleksei Y. Semenov
* @version $Revision$
*/

package org.apache.harmony.security;

import java.security.Identity;
import java.security.IdentityScope;
import java.security.KeyManagementException;
import java.security.PublicKey;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @see java.security.IdentityScope
 */

public class SystemScope extends IdentityScope {

    /**
     * @serial
     */
    private static final long serialVersionUID = -4810285697932522607L;

    // Identities hash: key is the identity name
    private Hashtable names = new Hashtable();

    // Identities hash: key is the public key
    private Hashtable keys = new Hashtable();

    /**
     * @see java.security.IdentityScope#IdentityScope()
     */
    public SystemScope() {
    }

    /**
     * @see java.security.IdentityScope#IdentityScope(String)
     */
    public SystemScope(String name) {
        super(name);
    }

    /**
     * @see java.security.IdentityScope#IdentityScope(String, IdentityScope)
     */
    public SystemScope(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * @see java.security.IdentityScope#size()
     */
    public int size() {
        return names.size();
    }

    /**
     * @see java.security.IdentityScope#getIdentity(java.lang.String)
     */
    public synchronized Identity getIdentity(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        return (Identity) names.get(name);
    }

    /**
     * @see java.security.IdentityScope#getIdentity(java.security.PublicKey)
     */
    public synchronized Identity getIdentity(PublicKey key) {
        if (key == null) {
            return null;
        }
        return (Identity) keys.get(key);
    }

    /**
     * @see java.security.IdentityScope#addIdentity(java.security.Identity)
     */
    public synchronized void addIdentity(Identity identity) throws KeyManagementException {
        if (identity == null) {
            throw new NullPointerException("identity == null");
        }

        String name = identity.getName();
        if (names.containsKey(name)) {
            throw new KeyManagementException("name '" + name + "' is already used");
        }

        PublicKey key = identity.getPublicKey();
        if (key != null && keys.containsKey(key)) {
            throw new KeyManagementException("key '" + key + "' is already used");
        }

        names.put(name, identity);
        if (key != null) {
            keys.put(key, identity);
        }
    }

    /**
     * @see java.security.IdentityScope#removeIdentity(java.security.Identity)
     */
    public synchronized void removeIdentity(Identity identity)
            throws KeyManagementException {

        //Exception caught = null;
        if (identity == null) {
            throw new NullPointerException("identity == null");
        }

        String name = identity.getName();
        if (name == null) {
            throw new NullPointerException("name == null");
        }

        boolean contains = names.containsKey(name);
        names.remove(name);

        PublicKey key = identity.getPublicKey();

        if (key != null) {
            contains = contains || keys.containsKey(key);
            keys.remove(key);
        }

        if (!contains) {
            throw new KeyManagementException("identity not found");
        }
    }

    /**
     * @see java.security.IdentityScope#identities()
     */
    public Enumeration identities() {
        return names.elements();
    }
}
