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

import java.security.IdentityScope;
import java.security.KeyManagementException;
import java.security.Signer;

/**
 * Stub for abstract class Signer, necessary for testing purposes
 *
 */
@SuppressWarnings("deprecation")
public class SignerStub extends Signer {

    /**
     * Default constructor
     */
    public SignerStub() {
        super();
    }

    /**
     * Constructor, sets given name
     *
     * @param name
     */
    public SignerStub(String name) {
        super(name);
    }

    /**
     * Constructor, sets given name and scope
     *
     * @param name
     * @param scope
     * @throws KeyManagementException
     */
    public SignerStub(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

}
