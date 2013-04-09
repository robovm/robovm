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

package org.apache.harmony.security.tests.support;

import java.security.Identity;
import java.security.IdentityScope;
import java.security.KeyManagementException;
import java.security.PublicKey;
import java.util.Enumeration;

/**
 * This is stub implementation of IdentityScope for testing purposes
 */
@SuppressWarnings("deprecation")
public class IdentityScopeStub extends IdentityScope {


    /**
     * Stub constructor
     */
    public IdentityScopeStub() {
        super();
    }

    /**
     * Stub constructor
     * @param name
     */
    public IdentityScopeStub(String name) {
        super(name);
    }

    /**
     * Stub constructor
     * @param name
     * @param scope
     * @throws KeyManagementException
     */
    public IdentityScopeStub(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Stub - returns 0
     * @see java.security.IdentityScope#size()
     */
    public int size() {

        return 0;
    }

    /**
     * Stub - returns <code>this</code>
     * @see java.security.IdentityScope#getIdentity(java.lang.String)
     */
    public Identity getIdentity(String name) {

        return this;
    }

    /**
     * Stub - returns <code>this</code>
     * @see java.security.IdentityScope#getIdentity(java.security.PublicKey)
     */
    public Identity getIdentity(PublicKey key) {
        return this;
    }

    /**
     * Stub - does nothing
     * @see java.security.IdentityScope#addIdentity(java.security.Identity)
     */
    public void addIdentity(Identity identity) throws KeyManagementException {


    }

    /**
     * Stub - does nothing
     * @see java.security.IdentityScope#removeIdentity(java.security.Identity)
     */
    public void removeIdentity(Identity identity) throws KeyManagementException {


    }

    /**
     * Stub - returns <code>null</code>
     * @see java.security.IdentityScope#identities()
     */
    public Enumeration identities() {
        return null;
    }

    /**
     * Sets the system's identity scope
     * @param scope
     */
    public static void mySetSystemScope(IdentityScope scope) {

        IdentityScope.setSystemScope(scope);
    }

}
