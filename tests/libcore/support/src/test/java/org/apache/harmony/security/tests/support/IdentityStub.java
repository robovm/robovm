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

/**
 * Stub for abstract class Identity
 *
 */
@SuppressWarnings("deprecation")
public class IdentityStub extends Identity {

    /**
     *  Default constructor
     */
    @SuppressWarnings("deprecation")
    public IdentityStub() {
        super();
    }

    /**
     * TODO Put ctor description here
     *
     * @param name
     */
    @SuppressWarnings("deprecation")
    public IdentityStub(String name) {
        super(name);
    }

    /**
     * TODO Put ctor description here
     *
     * @param name
     * @param scope
     * @throws KeyManagementException
     */
    @SuppressWarnings("deprecation")
    public IdentityStub(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Auxiliary constructor
     * @param name
     * @param key
     * @throws KeyManagementException
     */
    @SuppressWarnings("deprecation")
    public IdentityStub(String name, PublicKey key) throws KeyManagementException{
        this(name);
        setPublicKey(key);
    }

    @SuppressWarnings("deprecation")
    public boolean identityEquals(Identity identity) {
        return super.identityEquals(identity);
    }
}
